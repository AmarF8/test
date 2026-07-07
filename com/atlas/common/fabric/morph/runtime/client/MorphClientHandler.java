/*     */ package com.atlas.common.fabric.morph.runtime.client;
/*     */ import com.atlas.common.fabric.morph.runtime.network.MorphRuntimeNetwork;
/*     */ import com.atlas.common.fabric.morph.runtime.network.MorphRuntimeNetworkHelperClient;
/*     */ import com.cobblemon.mod.common.api.snowstorm.BedrockParticleOptions;
/*     */ import com.cobblemon.mod.common.client.particle.BedrockParticleOptionsRepository;
/*     */ import com.cobblemon.mod.common.client.particle.ParticleStorm;
/*     */ import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.UUID;
/*     */ import java.util.concurrent.CopyOnWriteArrayList;
/*     */ import net.fabricmc.api.Environment;
/*     */ import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
/*     */ import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
/*     */ import net.minecraft.class_1297;
/*     */ import net.minecraft.class_1309;
/*     */ import net.minecraft.class_1657;
/*     */ import net.minecraft.class_2394;
/*     */ import net.minecraft.class_2398;
/*     */ import net.minecraft.class_243;
/*     */ import net.minecraft.class_2561;
/*     */ import net.minecraft.class_2960;
/*     */ import net.minecraft.class_304;
/*     */ import net.minecraft.class_310;
/*     */ import net.minecraft.class_3419;
/*     */ import net.minecraft.class_3675;
/*     */ import net.minecraft.class_5498;
/*     */ import net.minecraft.class_638;
/*     */ import net.minecraft.class_746;
/*     */ import net.minecraft.class_8710;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ @Environment(EnvType.CLIENT)
/*     */ public final class MorphClientHandler {
/*     */   private static final long MIN_MOVE_LOCK_MS = 150L;
/*  36 */   private static final CopyOnWriteArrayList<ScheduledParticleSpawn> SCHEDULED_PARTICLES = new CopyOnWriteArrayList<>();
/*  37 */   private static final class_3675.class_306 MORPH_EXIT_KEY = class_3675.class_307.field_1668.method_1447(82);
/*     */   
/*     */   private static class_304 exitKey;
/*     */   private static boolean exitKeyBound;
/*  41 */   private static class_5498 savedPerspective = null; private static boolean lastAttackPressed;
/*     */   private static boolean lastUsePressed;
/*     */   @Nullable
/*     */   private static LocalMoveLock localMoveLock;
/*     */   
/*     */   public static void init() {
/*  47 */     exitKey = new class_304("key.atlas.morph.exit", class_3675.class_307.field_1668, -1, "category.atlas.morph");
/*     */ 
/*     */ 
/*     */     
/*  51 */     KeyBindingHelper.registerKeyBinding(exitKey);
/*  52 */     MorphAimRenderer.register();
/*     */     
/*  54 */     ClientTickEvents.START_CLIENT_TICK.register(MorphClientHandler::onTickStart);
/*     */     
/*  56 */     ClientPlayConnectionEvents.DISCONNECT.register((handler, client) -> {
/*     */           MorphedPlayerRegistry.get().clearAll();
/*     */           MorphMoveEffects.clear();
/*     */           SCHEDULED_PARTICLES.clear();
/*     */           MorphAimTracker.get().clear();
/*     */           lastAttackPressed = false;
/*     */           lastUsePressed = false;
/*     */           localMoveLock = null;
/*     */           syncExitKeyBinding(false);
/*     */           restorePerspective(client);
/*     */         });
/*     */   }
/*     */   
/*     */   private static void onTickStart(class_310 client) {
/*  70 */     drainScheduledParticles(client);
/*     */     
/*  72 */     class_746 class_746 = client.field_1724;
/*  73 */     if (class_746 == null)
/*     */       return; 
/*  75 */     boolean morphed = MorphedPlayerRegistry.get().isMorphed(class_746.method_5667());
/*  76 */     syncExitKeyBinding(morphed);
/*     */     
/*  78 */     if (morphed) {
/*  79 */       if (client.field_1690.method_31044() != class_5498.field_26665) {
/*  80 */         if (savedPerspective == null) savedPerspective = client.field_1690.method_31044(); 
/*  81 */         client.field_1690.method_31043(class_5498.field_26665);
/*     */       } 
/*     */     } else {
/*  84 */       MorphAimTracker.get().clear();
/*  85 */       lastAttackPressed = false;
/*  86 */       lastUsePressed = false;
/*  87 */       localMoveLock = null;
/*  88 */       restorePerspective(client);
/*     */       
/*     */       return;
/*     */     } 
/*  92 */     boolean moveLocked = applyLocalMoveLock(client, (class_1657)class_746);
/*  93 */     if (!moveLocked) {
/*  94 */       MorphAimTracker.get().update(client, (class_1657)class_746);
/*     */     } else {
/*  96 */       MorphAimTracker.get().clear();
/*  97 */       drainLockedHotbar(client);
/*  98 */       client.field_1690.field_1886.method_23481(false);
/*  99 */       client.field_1690.field_1904.method_23481(false);
/*     */     } 
/*     */     
/* 102 */     if (!moveLocked) {
/*     */       
/* 104 */       class_304[] hotbar = client.field_1690.field_1852;
/* 105 */       for (int i = 0; i < 4 && i < hotbar.length; i++) {
/* 106 */         boolean pressed = false;
/* 107 */         for (; hotbar[i].method_1436(); pressed = true);
/* 108 */         if (pressed) {
/* 109 */           fireMove((class_1657)class_746, i);
/*     */         }
/*     */       } 
/*     */       
/* 113 */       if (client.field_1755 == null) {
/* 114 */         boolean attackPressed = client.field_1690.field_1886.method_1434();
/* 115 */         if (attackPressed && !lastAttackPressed) {
/* 116 */           fireMove((class_1657)class_746, 0);
/* 117 */           client.field_1690.field_1886.method_23481(false);
/*     */         } 
/* 119 */         lastAttackPressed = attackPressed;
/*     */         
/* 121 */         boolean usePressed = client.field_1690.field_1904.method_1434();
/* 122 */         if (usePressed && !lastUsePressed) {
/* 123 */           fireMove((class_1657)class_746, 0);
/* 124 */           client.field_1690.field_1904.method_23481(false);
/*     */         } 
/* 126 */         lastUsePressed = usePressed;
/*     */       } else {
/* 128 */         lastAttackPressed = false;
/* 129 */         lastUsePressed = false;
/*     */       } 
/*     */     } else {
/* 132 */       lastAttackPressed = false;
/* 133 */       lastUsePressed = false;
/*     */     } 
/*     */     
/* 136 */     boolean exitFired = false;
/* 137 */     for (; exitKey.method_1436(); exitFired = true);
/* 138 */     if (exitFired) {
/* 139 */       MorphRuntimeNetworkHelperClient.sendToServer((class_8710)new MorphRuntimeNetwork.RequestExitPayload(0));
/*     */     }
/*     */   }
/*     */   
/*     */   private static void syncExitKeyBinding(boolean active) {
/* 144 */     if (exitKey == null || exitKeyBound == active) {
/*     */       return;
/*     */     }
/*     */     
/* 148 */     exitKey.method_1422(active ? MORPH_EXIT_KEY : class_3675.field_16237);
/* 149 */     class_304.method_1426();
/* 150 */     exitKeyBound = active;
/* 151 */     if (!active) {
/* 152 */       while (exitKey.method_1436());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void restorePerspective(class_310 client) {
/* 159 */     if (savedPerspective != null && client.field_1690.method_31044() == class_5498.field_26665) {
/* 160 */       client.field_1690.method_31043(savedPerspective);
/*     */     }
/* 162 */     savedPerspective = null;
/*     */   }
/*     */   
/*     */   private static void fireMove(class_1657 player, int slot) {
/* 166 */     class_243 aimDirection = MorphAimTracker.get().getAimDirection(player);
/* 167 */     class_243 targetPos = MorphAimTracker.get().getTargetPosIfActive();
/* 168 */     if (targetPos == null) {
/* 169 */       targetPos = player.method_19538().method_1019(aimDirection.method_1021(18.0D));
/*     */     }
/* 171 */     int targetEntityId = MorphAimTracker.get().getTargetEntityIdIfActive();
/* 172 */     MorphRuntimeNetworkHelperClient.sendToServer((class_8710)new MorphRuntimeNetwork.UseMovePayload(slot, aimDirection, targetPos, targetEntityId));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void onMoveFired(MorphRuntimeNetwork.PlayMovePayload payload) {
/* 178 */     class_310 mc = class_310.method_1551();
/* 179 */     if (mc.field_1687 == null)
/*     */       return; 
/* 181 */     MorphPokemonRenderState.triggerMoveAnimation(payload.shooterId(), payload.move());
/* 182 */     showMoveActionBar(payload, mc);
/* 183 */     beginLocalMoveLock(payload, mc);
/* 184 */     scheduleMoveParticles(payload);
/* 185 */     if (shouldRenderBeam(payload.move())) {
/* 186 */       scheduleBeam(payload);
/*     */     }
/*     */   }
/*     */   
/*     */   private static void beginLocalMoveLock(MorphRuntimeNetwork.PlayMovePayload payload, class_310 mc) {
/* 191 */     class_746 class_746 = mc.field_1724; if (class_746 instanceof class_746) { class_746 player = class_746; if (payload.shooterId().equals(player.method_5667())) {
/*     */ 
/*     */ 
/*     */         
/* 195 */         long durationMs = secondsToMillis(Math.max(0.0D, MorphMoveEffects.rootDurationSeconds(payload.move())));
/* 196 */         if (durationMs <= 0L) {
/* 197 */           durationMs = 150L;
/*     */         }
/*     */         
/* 200 */         PokemonEntity mount = MorphedPlayerRegistry.get().getMount(player.method_5667());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 213 */         localMoveLock = new LocalMoveLock(player.method_5667(), nowMillis() + durationMs, payload.rootPos(), payload.rootYaw(), payload.rootPitch(), payload.rootBodyYaw(), payload.rootHeadYaw(), (mount != null) ? payload.mountPos() : null, payload.mountYaw(), payload.mountPitch(), payload.mountBodyYaw(), payload.mountHeadYaw());
/*     */         return;
/*     */       }  }
/*     */   
/*     */   } private static void showMoveActionBar(MorphRuntimeNetwork.PlayMovePayload payload, class_310 mc) {
/* 218 */     if (mc.field_1724 == null || !payload.shooterId().equals(mc.field_1724.method_5667())) {
/*     */       return;
/*     */     }
/* 221 */     mc.field_1724.method_7353((class_2561)class_2561.method_43470("You used " + formatMoveName(payload.move()) + "."), true);
/*     */   }
/*     */   
/*     */   private static void spawnMoveActorEffects(MorphRuntimeNetwork.PlayMovePayload payload, class_310 mc, MorphMoveEffects.ParticleSpec spec) {
/* 225 */     class_638 world, class_6381 = mc.field_1687; if (class_6381 instanceof class_638) { world = class_6381; }
/*     */     else { return; }
/* 227 */      PokemonEntity source = MorphPokemonRenderState.getRenderedPokemon(payload.shooterId());
/* 228 */     if (source == null) {
/* 229 */       source = MorphedPlayerRegistry.get().getMount(payload.shooterId());
/*     */     }
/*     */     
/* 232 */     BedrockParticleOptions effect = BedrockParticleOptionsRepository.INSTANCE.getEffect(spec.effectId());
/* 233 */     if (effect == null)
/*     */       return; 
/* 235 */     boolean spawnedAtLocator = false;
/* 236 */     if (source != null) {
/* 237 */       List<ParticleStorm> storms = ParticleStorm.Companion.createAtEntity(world, effect, (class_1309)source, 
/* 238 */           spec.locators().isEmpty() ? List.<String>of("root") : spec.locators());
/* 239 */       for (ParticleStorm storm : storms) {
/* 240 */         storm.spawn();
/*     */       }
/* 242 */       spawnedAtLocator = !storms.isEmpty();
/*     */     } 
/*     */     
/* 245 */     if (!spawnedAtLocator) {
/* 246 */       ParticleStorm.Companion.createAtPosition(world, effect, payload.origin()).spawn();
/*     */     }
/*     */   }
/*     */   
/*     */   private static void spawnMoveImpact(MorphRuntimeNetwork.PlayMovePayload payload, class_310 mc, MorphMoveEffects.ParticleSpec spec) {
/* 251 */     class_638 world, class_6381 = mc.field_1687; if (class_6381 instanceof class_638) { world = class_6381; }
/*     */     else { return; }
/* 253 */      class_1297 targetEntity = (payload.targetEntityId() >= 0) ? world.method_8469(payload.targetEntityId()) : null;
/* 254 */     class_2960 effectId = spec.effectId();
/* 255 */     BedrockParticleOptions effect = BedrockParticleOptionsRepository.INSTANCE.getEffect(effectId);
/* 256 */     if (effect != null) {
/* 257 */       class_243 fallbackPosition = payload.hit();
/* 258 */       boolean spawnedOnTarget = false;
/* 259 */       if (targetEntity instanceof class_1309) { class_1309 livingTarget = (class_1309)targetEntity;
/* 260 */         if (spec.targetAnchor() == MorphMoveEffects.TargetAnchor.CENTER) {
/* 261 */           fallbackPosition = livingTarget.method_5829().method_1005();
/*     */         }
/* 263 */         if (spec.targetAnchor() != MorphMoveEffects.TargetAnchor.CENTER) {
/* 264 */           List<ParticleStorm> storms = ParticleStorm.Companion.createAtEntity(world, effect, livingTarget, 
/* 265 */               spec.locators().isEmpty() ? List.<String>of("root") : spec.locators());
/* 266 */           for (ParticleStorm storm : storms) {
/* 267 */             storm.spawn();
/*     */           }
/* 269 */           spawnedOnTarget = !storms.isEmpty();
/*     */         }  }
/*     */       
/* 272 */       if (!spawnedOnTarget) {
/* 273 */         ParticleStorm.Companion.createAtPosition(world, effect, fallbackPosition).spawn();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void fallbackBeamVfx(MorphRuntimeNetwork.PlayMovePayload payload, class_310 mc) {
/* 279 */     class_243 origin = payload.origin();
/* 280 */     class_243 hit = payload.hit();
/* 281 */     class_243 diff = hit.method_1020(origin);
/* 282 */     double length = diff.method_1033();
/* 283 */     if (length < 0.01D)
/* 284 */       return;  class_243 step = diff.method_1021(1.0D / length);
/*     */     
/* 286 */     int segments = (int)Math.min(48.0D, Math.max(4.0D, length * 3.0D));
/* 287 */     for (int i = 0; i <= segments; i++) {
/* 288 */       double t = i / segments;
/* 289 */       class_243 p = origin.method_1019(step.method_1021(length * t));
/* 290 */       mc.field_1687.method_8406((class_2394)class_2398.field_29644, p.field_1352, p.field_1351, p.field_1350, 0.0D, 0.0D, 0.0D);
/*     */     } 
/* 292 */     mc.field_1687.method_8406((class_2394)class_2398.field_11236, hit.field_1352, hit.field_1351, hit.field_1350, 0.0D, 0.0D, 0.0D);
/* 293 */     mc.field_1687.method_8486(origin.field_1352, origin.field_1351, origin.field_1350, class_3417.field_14956, class_3419.field_15248, 0.6F, 1.5F, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean applyLocalMoveLock(class_310 client, class_1657 player) {
/* 300 */     LocalMoveLock lock = localMoveLock;
/* 301 */     if (lock == null || !lock.playerId.equals(player.method_5667())) {
/* 302 */       return false;
/*     */     }
/*     */     
/* 305 */     if (lock.untilMillis <= nowMillis()) {
/* 306 */       localMoveLock = null;
/* 307 */       return false;
/*     */     } 
/*     */     
/* 310 */     zeroMovementKeys(client);
/* 311 */     class_243 playerVelocity = player.method_18798();
/* 312 */     player.method_18800(0.0D, playerVelocity.field_1351, 0.0D);
/* 313 */     player.method_5814(lock.playerPos.field_1352, player.method_23318(), lock.playerPos.field_1350);
/* 314 */     player.field_6014 = lock.playerPos.field_1352;
/* 315 */     player.field_5969 = lock.playerPos.field_1350;
/*     */     
/* 317 */     if (player instanceof class_746) { class_746 clientPlayer = (class_746)player; if (clientPlayer.field_3913 != null) {
/* 318 */         clientPlayer.field_3913.field_3905 = 0.0F;
/* 319 */         clientPlayer.field_3913.field_3907 = 0.0F;
/* 320 */         clientPlayer.field_3913.field_3904 = false;
/* 321 */         clientPlayer.field_3913.field_3903 = false;
/*     */       }  }
/*     */     
/* 324 */     PokemonEntity mount = MorphedPlayerRegistry.get().getMount(player.method_5667());
/* 325 */     if (mount != null && lock.mountPos != null) {
/* 326 */       class_243 mountVelocity = mount.method_18798();
/* 327 */       mount.method_18800(0.0D, mountVelocity.field_1351, 0.0D);
/* 328 */       mount.method_5814(lock.mountPos.field_1352, mount.method_23318(), lock.mountPos.field_1350);
/* 329 */       mount.field_6014 = lock.mountPos.field_1352;
/* 330 */       mount.field_5969 = lock.mountPos.field_1350;
/*     */     } 
/*     */     
/* 333 */     return true;
/*     */   }
/*     */   
/*     */   private static void zeroMovementKeys(class_310 client) {
/* 337 */     client.field_1690.field_1894.method_23481(false);
/* 338 */     client.field_1690.field_1881.method_23481(false);
/* 339 */     client.field_1690.field_1913.method_23481(false);
/* 340 */     client.field_1690.field_1849.method_23481(false);
/* 341 */     client.field_1690.field_1903.method_23481(false);
/* 342 */     client.field_1690.field_1832.method_23481(false);
/* 343 */     client.field_1690.field_1867.method_23481(false);
/*     */   }
/*     */   
/*     */   private static void drainLockedHotbar(class_310 client) {
/* 347 */     class_304[] hotbar = client.field_1690.field_1852;
/* 348 */     for (int i = 0; i < 4 && i < hotbar.length; i++) {
/* 349 */       while (hotbar[i].method_1436());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean shouldRenderBeam(String moveSlug) {
/* 356 */     String normalized = (moveSlug == null) ? "" : moveSlug.trim().toLowerCase(Locale.ROOT);
/* 357 */     return ("thunderbolt".equals(normalized) || "thundershock".equals(normalized));
/*     */   }
/*     */   
/*     */   private static void scheduleMoveParticles(MorphRuntimeNetwork.PlayMovePayload payload) {
/* 361 */     long now = nowMillis();
/* 362 */     for (MorphMoveEffects.ParticleSpec spec : MorphMoveEffects.actorParticles(payload.move())) {
/* 363 */       SCHEDULED_PARTICLES.add(new ScheduledParticleSpawn(now + secondsToMillis(spec.timeOffsetSeconds()), payload, spec, true));
/*     */     }
/* 365 */     for (MorphMoveEffects.ParticleSpec spec : MorphMoveEffects.targetParticles(payload.move())) {
/* 366 */       SCHEDULED_PARTICLES.add(new ScheduledParticleSpawn(now + secondsToMillis(spec.timeOffsetSeconds()), payload, spec, false));
/*     */     }
/*     */   }
/*     */   
/*     */   private static void scheduleBeam(MorphRuntimeNetwork.PlayMovePayload payload) {
/* 371 */     long dueAt = nowMillis() + secondsToMillis(MorphMoveEffects.firstTargetTimeSeconds(payload.move()));
/* 372 */     SCHEDULED_PARTICLES.add(new ScheduledParticleSpawn(dueAt, payload, null, false));
/*     */   }
/*     */   
/*     */   private static void drainScheduledParticles(class_310 client) {
/* 376 */     if (SCHEDULED_PARTICLES.isEmpty()) {
/*     */       return;
/*     */     }
/*     */     
/* 380 */     long now = nowMillis();
/* 381 */     for (ScheduledParticleSpawn scheduled : SCHEDULED_PARTICLES) {
/* 382 */       if (scheduled.dueAtMillis > now) {
/*     */         continue;
/*     */       }
/* 385 */       if (scheduled.spec == null) {
/* 386 */         fallbackBeamVfx(scheduled.payload, client);
/* 387 */       } else if (scheduled.actor) {
/* 388 */         spawnMoveActorEffects(scheduled.payload, client, scheduled.spec);
/*     */       } else {
/* 390 */         spawnMoveImpact(scheduled.payload, client, scheduled.spec);
/*     */       } 
/* 392 */       SCHEDULED_PARTICLES.remove(scheduled);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static long secondsToMillis(double seconds) {
/* 397 */     return Math.max(0L, Math.round(seconds * 1000.0D));
/*     */   }
/*     */   
/*     */   private static long nowMillis() {
/* 401 */     return System.nanoTime() / 1000000L;
/*     */   }
/*     */   
/*     */   private static String formatMoveName(String moveSlug) {
/* 405 */     if (moveSlug == null || moveSlug.isBlank()) {
/* 406 */       return "Move";
/*     */     }
/*     */     
/* 409 */     String[] parts = moveSlug.trim().replace('_', ' ').replace('-', ' ').split("\\s+");
/* 410 */     StringBuilder out = new StringBuilder();
/* 411 */     for (String part : parts) {
/* 412 */       if (!part.isEmpty()) {
/*     */ 
/*     */         
/* 415 */         if (!out.isEmpty()) {
/* 416 */           out.append(' ');
/*     */         }
/* 418 */         out.append(Character.toUpperCase(part.charAt(0)));
/* 419 */         if (part.length() > 1)
/* 420 */           out.append(part.substring(1).toLowerCase(Locale.ROOT)); 
/*     */       } 
/*     */     } 
/* 423 */     return out.isEmpty() ? "Move" : out.toString();
/*     */   }
/*     */   private static final class ScheduledParticleSpawn extends Record { private final long dueAtMillis; private final MorphRuntimeNetwork.PlayMovePayload payload; private final MorphMoveEffects.ParticleSpec spec; private final boolean actor;
/* 426 */     private ScheduledParticleSpawn(long dueAtMillis, MorphRuntimeNetwork.PlayMovePayload payload, MorphMoveEffects.ParticleSpec spec, boolean actor) { this.dueAtMillis = dueAtMillis; this.payload = payload; this.spec = spec; this.actor = actor; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/morph/runtime/client/MorphClientHandler$ScheduledParticleSpawn;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #426	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/* 426 */       //   0	7	0	this	Lcom/atlas/common/fabric/morph/runtime/client/MorphClientHandler$ScheduledParticleSpawn; } public long dueAtMillis() { return this.dueAtMillis; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/morph/runtime/client/MorphClientHandler$ScheduledParticleSpawn;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #426	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/morph/runtime/client/MorphClientHandler$ScheduledParticleSpawn; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/morph/runtime/client/MorphClientHandler$ScheduledParticleSpawn;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #426	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lcom/atlas/common/fabric/morph/runtime/client/MorphClientHandler$ScheduledParticleSpawn;
/* 426 */       //   0	8	1	o	Ljava/lang/Object; } public MorphRuntimeNetwork.PlayMovePayload payload() { return this.payload; } public MorphMoveEffects.ParticleSpec spec() { return this.spec; } public boolean actor() { return this.actor; }
/*     */      }
/*     */   private static final class LocalMoveLock extends Record { private final UUID playerId; private final long untilMillis; private final class_243 playerPos; private final float yaw; private final float pitch; private final float bodyYaw; private final float headYaw; private final class_243 mountPos; private final float mountYaw;
/*     */     private final float mountPitch;
/*     */     private final float mountBodyYaw;
/*     */     private final float mountHeadYaw;
/*     */     
/* 433 */     private LocalMoveLock(UUID playerId, long untilMillis, class_243 playerPos, float yaw, float pitch, float bodyYaw, float headYaw, class_243 mountPos, float mountYaw, float mountPitch, float mountBodyYaw, float mountHeadYaw) { this.playerId = playerId; this.untilMillis = untilMillis; this.playerPos = playerPos; this.yaw = yaw; this.pitch = pitch; this.bodyYaw = bodyYaw; this.headYaw = headYaw; this.mountPos = mountPos; this.mountYaw = mountYaw; this.mountPitch = mountPitch; this.mountBodyYaw = mountBodyYaw; this.mountHeadYaw = mountHeadYaw; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/morph/runtime/client/MorphClientHandler$LocalMoveLock;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #433	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/morph/runtime/client/MorphClientHandler$LocalMoveLock; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/morph/runtime/client/MorphClientHandler$LocalMoveLock;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #433	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/morph/runtime/client/MorphClientHandler$LocalMoveLock; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/morph/runtime/client/MorphClientHandler$LocalMoveLock;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #433	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lcom/atlas/common/fabric/morph/runtime/client/MorphClientHandler$LocalMoveLock;
/* 433 */       //   0	8	1	o	Ljava/lang/Object; } public UUID playerId() { return this.playerId; } public long untilMillis() { return this.untilMillis; } public class_243 playerPos() { return this.playerPos; } public float yaw() { return this.yaw; } public float pitch() { return this.pitch; } public float bodyYaw() { return this.bodyYaw; } public float headYaw() { return this.headYaw; } public class_243 mountPos() { return this.mountPos; } public float mountYaw() { return this.mountYaw; } public float mountPitch() { return this.mountPitch; } public float mountBodyYaw() { return this.mountBodyYaw; } public float mountHeadYaw() { return this.mountHeadYaw; }
/*     */      }
/*     */ 
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\morph\runtime\client\MorphClientHandler.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */