/*     */ package com.atlas.common.fabric.safari.fishing;
/*     */ import com.atlas.common.fabric.AtlasMod;
/*     */ import com.atlas.common.fabric.safari.fishing.entity.AtlasSafariBobberEntity;
/*     */ import com.atlas.common.fabric.safari.fishing.entity.SafariFishEntities;
/*     */ import com.atlas.common.fabric.safari.fishing.entity.SafariFishEntity;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.class_124;
/*     */ import net.minecraft.class_1297;
/*     */ import net.minecraft.class_1657;
/*     */ import net.minecraft.class_1799;
/*     */ import net.minecraft.class_1937;
/*     */ import net.minecraft.class_2338;
/*     */ import net.minecraft.class_2394;
/*     */ import net.minecraft.class_2398;
/*     */ import net.minecraft.class_243;
/*     */ import net.minecraft.class_2561;
/*     */ import net.minecraft.class_2680;
/*     */ import net.minecraft.class_3218;
/*     */ import net.minecraft.class_3222;
/*     */ import net.minecraft.class_3244;
/*     */ import net.minecraft.class_3417;
/*     */ import net.minecraft.class_3419;
/*     */ import net.minecraft.class_3486;
/*     */ import net.minecraft.class_3532;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public final class SafariFishingManager {
/*  35 */   private static final Map<UUID, ActiveSession> SESSIONS = new HashMap<>();
/*  36 */   private static final Map<UUID, PendingCast> PENDING_CASTS = new HashMap<>();
/*  37 */   private static final Map<UUID, FishingInputState> INPUTS = new HashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void register() {
/*  43 */     ServerTickEvents.END_SERVER_TICK.register(server -> {
/*     */           for (class_3218 world : server.method_3738()) {
/*     */             tickWorld(world);
/*     */           }
/*     */         });
/*  48 */     ServerPlayConnectionEvents.DISCONNECT.register((handler, server) -> {
/*     */           ActiveSession activeSession = SESSIONS.remove(handler.field_14140.method_5667());
/*     */           PendingCast pendingCast = PENDING_CASTS.remove(handler.field_14140.method_5667());
/*     */           INPUTS.remove(handler.field_14140.method_5667());
/*     */           if (pendingCast != null) {
/*     */             AtlasSafariBobberEntity bobber = getBobber(handler.field_14140.method_51469(), pendingCast.bobberId());
/*     */             if (bobber != null) {
/*     */               bobber.method_31472();
/*     */             }
/*     */           } 
/*     */           if (activeSession != null) {
/*     */             discardVisualFish(handler.field_14140.method_51469(), activeSession, false);
/*     */             clearBobber(handler.field_14140.method_51469(), activeSession);
/*     */           } 
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean hasActiveSession(@NotNull class_1657 player) {
/*  68 */     return SESSIONS.containsKey(player.method_5667());
/*     */   }
/*     */   
/*     */   public static boolean hasPendingCast(@NotNull class_1657 player) {
/*  72 */     return PENDING_CASTS.containsKey(player.method_5667());
/*     */   }
/*     */   
/*     */   public static boolean isHookedSafariBobber(@NotNull UUID bobberId) {
/*  76 */     for (ActiveSession session : SESSIONS.values()) {
/*  77 */       if (!bobberId.equals(session.bobberId))
/*  78 */         continue;  return (session.session.getPhase() == SafariFishingSession.Phase.HOOKED);
/*     */     } 
/*  80 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean isHooked(@NotNull class_1657 player) {
/*  84 */     ActiveSession session = SESSIONS.get(player.method_5667());
/*  85 */     return (session != null && session.session.getPhase() == SafariFishingSession.Phase.HOOKED);
/*     */   }
/*     */   
/*     */   public static boolean isWaitingForBite(@NotNull class_1657 player) {
/*  89 */     ActiveSession session = SESSIONS.get(player.method_5667());
/*  90 */     return (session != null && session.session.getPhase() == SafariFishingSession.Phase.WAITING);
/*     */   }
/*     */   
/*     */   public static void handleInput(@NotNull class_3222 player, @NotNull FishingInputState input) {
/*  94 */     if (!SESSIONS.containsKey(player.method_5667()))
/*  95 */       return;  INPUTS.put(player.method_5667(), input);
/*     */   }
/*     */   
/*     */   public static void queueCast(@NotNull class_3222 player, @NotNull class_1799 rodStack, @NotNull AtlasSafariBobberEntity bobber) {
/*  99 */     if (SESSIONS.containsKey(player.method_5667()) || PENDING_CASTS.containsKey(player.method_5667())) {
/* 100 */       bobber.method_31472();
/*     */       
/*     */       return;
/*     */     } 
/* 104 */     PENDING_CASTS.put(player.method_5667(), new PendingCast(rodStack.method_7972(), bobber.method_5667(), 0));
/* 105 */     AtlasMod.LOGGER.info("Safari cast queued for {} with bobber {}", player.method_7334().getName(), bobber.method_5667());
/* 106 */     player.method_7353((class_2561)class_2561.method_43470("You cast the safari line.").method_27692(class_124.field_1062), true);
/*     */   }
/*     */   
/*     */   public static void cancel(@NotNull class_3222 player, @Nullable String reason) {
/* 110 */     PendingCast pendingCast = PENDING_CASTS.remove(player.method_5667());
/* 111 */     ActiveSession activeSession = SESSIONS.remove(player.method_5667());
/* 112 */     INPUTS.remove(player.method_5667());
/*     */     
/* 114 */     if (pendingCast != null) {
/* 115 */       AtlasSafariBobberEntity bobber = getBobber(player.method_51469(), pendingCast.bobberId());
/* 116 */       if (bobber != null) {
/* 117 */         bobber.method_31472();
/*     */       }
/*     */     } 
/*     */     
/* 121 */     if (activeSession != null) {
/* 122 */       discardVisualFish(player.method_51469(), activeSession, false);
/* 123 */       clearBobber(player.method_51469(), activeSession);
/*     */     } 
/*     */     
/* 126 */     SafariFishingNetwork.sendUpdate(player, emptyUpdate());
/* 127 */     if (reason != null && !reason.isBlank()) {
/* 128 */       player.method_7353((class_2561)class_2561.method_43470(reason).method_27692(class_124.field_1080), true);
/*     */     }
/*     */   }
/*     */   
/*     */   private static void tickWorld(@NotNull class_3218 world) {
/* 133 */     tickPendingCasts(world);
/*     */     
/* 135 */     Iterator<Map.Entry<UUID, ActiveSession>> iterator = SESSIONS.entrySet().iterator();
/* 136 */     while (iterator.hasNext()) {
/* 137 */       Map.Entry<UUID, ActiveSession> entry = iterator.next();
/* 138 */       class_3222 player = world.method_8503().method_3760().method_14602(entry.getKey());
/* 139 */       if (player == null) {
/* 140 */         discardVisualFish(world, entry.getValue(), false);
/* 141 */         clearBobber(world, entry.getValue());
/* 142 */         iterator.remove();
/* 143 */         INPUTS.remove(entry.getKey());
/*     */         continue;
/*     */       } 
/* 146 */       if (player.method_37908() != world)
/*     */         continue; 
/* 148 */       ActiveSession activeSession = entry.getValue();
/* 149 */       AtlasSafariBobberEntity bobber = getBobber(world, activeSession.bobberId);
/* 150 */       if (bobber == null || !bobber.method_5805()) {
/* 151 */         AtlasMod.LOGGER.info("Ending safari session for {} because bobber {} is gone", player.method_7334().getName(), activeSession.bobberId);
/* 152 */         discardVisualFish(world, activeSession, false);
/* 153 */         iterator.remove();
/* 154 */         INPUTS.remove(entry.getKey());
/* 155 */         SafariFishingNetwork.sendUpdate(player, emptyUpdate());
/*     */         
/*     */         continue;
/*     */       } 
/* 159 */       class_2338 waterAnchor = resolveWaterAnchor(world, bobber);
/* 160 */       if (waterAnchor != null && activeSession.session.getPhase() != SafariFishingSession.Phase.HOOKED) {
/* 161 */         activeSession.castPos = waterAnchor;
/*     */       }
/*     */       
/* 164 */       if (!world.method_8316(activeSession.castPos).method_15767(class_3486.field_15517)) {
/* 165 */         AtlasMod.LOGGER.info("Ending safari session for {} because water anchor {} is no longer water", player.method_7334().getName(), activeSession.castPos);
/* 166 */         discardVisualFish(world, activeSession, false);
/* 167 */         clearBobber(world, activeSession);
/* 168 */         iterator.remove();
/* 169 */         INPUTS.remove(entry.getKey());
/* 170 */         SafariFishingNetwork.sendUpdate(player, emptyUpdate());
/* 171 */         player.method_7353((class_2561)class_2561.method_43470("The line lost the fish.").method_27692(class_124.field_1080), true);
/*     */         
/*     */         continue;
/*     */       } 
/* 175 */       class_243 castCenter = getCastCenter(world, activeSession);
/* 176 */       if (player.method_5707(castCenter) > 576.0D) {
/* 177 */         AtlasMod.LOGGER.info("Ending safari session for {} because they moved too far from {}", player.method_7334().getName(), castCenter);
/* 178 */         discardVisualFish(world, activeSession, false);
/* 179 */         clearBobber(world, activeSession);
/* 180 */         iterator.remove();
/* 181 */         INPUTS.remove(entry.getKey());
/* 182 */         SafariFishingNetwork.sendUpdate(player, emptyUpdate());
/* 183 */         player.method_7353((class_2561)class_2561.method_43470("You moved too far from the line.").method_27692(class_124.field_1080), true);
/*     */         
/*     */         continue;
/*     */       } 
/* 187 */       activeSession.visualTicks++;
/* 188 */       SafariFishingSession session = activeSession.session;
/* 189 */       SafariFishingSession.Outcome outcome = session.tick(INPUTS.getOrDefault(entry.getKey(), FishingInputState.IDLE));
/*     */       
/* 191 */       if (outcome == SafariFishingSession.Outcome.HOOKED) {
/* 192 */         AtlasMod.LOGGER.info("Safari fish hooked for {}: {} / {} weight {}kg size {}", new Object[] { player
/*     */               
/* 194 */               .method_7334().getName(), session
/* 195 */               .getCatchProfile().fishId(), session
/* 196 */               .getCatchProfile().variant().modelKey(), 
/* 197 */               String.format("%.2f", new Object[] { Double.valueOf(session.getCatchProfile().weightKg())
/* 198 */                 }), String.format("%.2f", new Object[] { Double.valueOf(session.getCatchProfile().sizeScale()) }) });
/*     */         
/* 200 */         player.method_7353((class_2561)class_2561.method_43470("A fish hits the line!").method_27692(class_124.field_1075), true);
/* 201 */         onFishHooked(world, activeSession);
/*     */       } 
/*     */       
/* 204 */       tickVisualFish(world, player, activeSession);
/*     */       
/* 206 */       if (outcome == SafariFishingSession.Outcome.SUCCESS) {
/* 207 */         AtlasMod.LOGGER.info("Safari catch landed for {} with focus fish {}", player.method_7334().getName(), activeSession.focusFishId);
/* 208 */         landCaughtFish(player, activeSession);
/* 209 */         SafariFishingNetwork.sendResult(player, new SafariFishingNetwork.ResultPayload(true, "Catch!", session
/*     */ 
/*     */               
/* 212 */               .getCatchProfile().getDisplayLabel() + " • " + session.getCatchProfile().getDisplayLabel(), session
/* 213 */               .getCatchProfile().rarity().getColor()));
/*     */         
/* 215 */         iterator.remove();
/* 216 */         INPUTS.remove(entry.getKey());
/* 217 */         SafariFishingNetwork.sendUpdate(player, emptyUpdate()); continue;
/* 218 */       }  if (outcome == SafariFishingSession.Outcome.FAIL) {
/* 219 */         AtlasMod.LOGGER.info("Safari catch failed for {} with focus fish {}", player.method_7334().getName(), activeSession.focusFishId);
/* 220 */         discardVisualFish(world, activeSession, false);
/* 221 */         SafariFishingNetwork.sendResult(player, new SafariFishingNetwork.ResultPayload(false, "It Got Away", session
/*     */ 
/*     */               
/* 224 */               .getCatchProfile().getDisplayLabel() + " snapped the line.", -2008220));
/*     */ 
/*     */         
/* 227 */         clearBobber(world, activeSession);
/* 228 */         iterator.remove();
/* 229 */         INPUTS.remove(entry.getKey());
/* 230 */         SafariFishingNetwork.sendUpdate(player, emptyUpdate()); continue;
/*     */       } 
/* 232 */       sync(player, session);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static void tickPendingCasts(@NotNull class_3218 world) {
/* 238 */     Iterator<Map.Entry<UUID, PendingCast>> iterator = PENDING_CASTS.entrySet().iterator();
/* 239 */     while (iterator.hasNext()) {
/* 240 */       Map.Entry<UUID, PendingCast> entry = iterator.next();
/* 241 */       class_3222 player = world.method_8503().method_3760().method_14602(entry.getKey());
/* 242 */       if (player == null || player.method_37908() != world)
/*     */         continue; 
/* 244 */       PendingCast pendingCast = entry.getValue();
/* 245 */       AtlasSafariBobberEntity bobber = getBobber(world, pendingCast.bobberId());
/* 246 */       if (bobber == null || !bobber.method_5805()) {
/* 247 */         iterator.remove();
/*     */         
/*     */         continue;
/*     */       } 
/* 251 */       if (pendingCast.ageTicks() > 80) {
/* 252 */         iterator.remove();
/*     */         
/*     */         continue;
/*     */       } 
/* 256 */       class_2338 waterAnchor = resolveWaterAnchor(world, bobber);
/* 257 */       if (waterAnchor != null) {
/* 258 */         startFromBobber(player, pendingCast.rodStack(), bobber);
/* 259 */         iterator.remove();
/*     */         
/*     */         continue;
/*     */       } 
/* 263 */       entry.setValue(pendingCast.tick());
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void startFromBobber(@NotNull class_3222 player, @NotNull class_1799 rodStack, @NotNull AtlasSafariBobberEntity bobber) {
/* 268 */     SafariRodData.RodLoadout loadout = SafariRodData.getLoadout(rodStack);
/* 269 */     class_2338 castPos = resolveWaterAnchor(player.method_51469(), bobber);
/* 270 */     if (castPos == null) {
/* 271 */       AtlasMod.LOGGER.warn("Safari session could not start for {} because no water anchor was found for bobber {}", player.method_7334().getName(), bobber.method_5667());
/*     */       
/*     */       return;
/*     */     } 
/* 275 */     SafariCatchProfile profile = SafariFishCatalog.rollCatch(player.method_51469(), castPos, loadout);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 280 */     SafariFishingSession session = new SafariFishingSession(profile, loadout, SafariFishingSession.rollInitialWaitTicks(player.method_59922(), loadout), player.method_5667().getLeastSignificantBits());
/*     */ 
/*     */     
/* 283 */     ActiveSession activeSession = new ActiveSession(session, castPos, bobber.method_5667());
/* 284 */     AtlasMod.LOGGER.info("Safari session started for {} at {} with profile {} / variant {}", new Object[] { player
/*     */           
/* 286 */           .method_7334().getName(), castPos, profile
/*     */           
/* 288 */           .fishId(), profile
/* 289 */           .variant().modelKey() });
/*     */     
/* 291 */     SESSIONS.put(player.method_5667(), activeSession);
/* 292 */     INPUTS.put(player.method_5667(), FishingInputState.IDLE);
/* 293 */     sync(player, session);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private static SafariFishEntity spawnVisualFish(@NotNull class_3218 world, @NotNull class_243 center, @NotNull SafariCatchProfile profile) {
/* 298 */     SafariFishEntity entity = (SafariFishEntity)SafariFishEntities.SAFARI_FISH.method_5883((class_1937)world);
/* 299 */     if (entity == null) {
/* 300 */       AtlasMod.LOGGER.error("Failed to create safari fish entity for profile {} / variant {}", profile.fishId(), profile.variant().modelKey());
/* 301 */       return null;
/*     */     } 
/*     */     
/* 304 */     entity.applyPreviewProfile(profile);
/* 305 */     entity.method_5808(center.field_1352, center.field_1351 - 0.08D, center.field_1350, world.field_9229.method_43057() * 360.0F, 0.0F);
/* 306 */     entity.method_18799(class_243.field_1353);
/* 307 */     boolean spawned = world.method_8649((class_1297)entity);
/* 308 */     if (!spawned) {
/* 309 */       AtlasMod.LOGGER.warn("Safari preview fish spawn failed for variant {} at ({}, {}, {})", new Object[] { profile
/*     */             
/* 311 */             .variant().modelKey(), 
/* 312 */             String.format("%.2f", new Object[] { Double.valueOf(center.field_1352)
/* 313 */               }), String.format("%.2f", new Object[] { Double.valueOf(center.field_1351)
/* 314 */               }), String.format("%.2f", new Object[] { Double.valueOf(center.field_1350) }) });
/*     */       
/* 316 */       return null;
/*     */     } 
/* 318 */     AtlasMod.LOGGER.info("Spawned safari preview fish {} variant {} at ({}, {}, {})", new Object[] { entity
/*     */           
/* 320 */           .method_5667(), profile
/* 321 */           .variant().modelKey(), 
/* 322 */           String.format("%.2f", new Object[] { Double.valueOf(entity.method_23317())
/* 323 */             }), String.format("%.2f", new Object[] { Double.valueOf(entity.method_23318())
/* 324 */             }), String.format("%.2f", new Object[] { Double.valueOf(entity.method_23321()) }) });
/*     */     
/* 326 */     return entity;
/*     */   }
/*     */   
/*     */   private static void onFishHooked(@NotNull class_3218 world, @NotNull ActiveSession activeSession) {
/* 330 */     activeSession.hookSplashTicks = 12;
/* 331 */     if (activeSession.focusFishId == null) {
/* 332 */       class_243 class_243 = getCastCenter(world, activeSession);
/* 333 */       SafariFishEntity hookedFish = spawnVisualFish(world, class_243, activeSession.session.getCatchProfile());
/* 334 */       if (hookedFish != null) {
/* 335 */         activeSession.focusFishId = hookedFish.method_5667();
/* 336 */         activeSession.visualFish.add(new VisualFish(hookedFish.method_5667(), world.field_9229.method_43058() * Math.PI * 2.0D, true));
/* 337 */         AtlasMod.LOGGER.info("Spawned hooked safari fish {} for session bobber {}", hookedFish.method_5667(), activeSession.bobberId);
/*     */       } 
/*     */     } 
/*     */     
/* 341 */     SafariFishEntity focusFish = getVisualFish(world, activeSession.focusFishId);
/* 342 */     if (focusFish == null)
/*     */       return; 
/* 344 */     class_243 hookCenter = getCastCenter(world, activeSession);
/* 345 */     focusFish.method_5808(hookCenter.field_1352, hookCenter.field_1351 + 0.04D, hookCenter.field_1350, world.field_9229.method_43057() * 360.0F, 0.0F);
/* 346 */     focusFish.method_18799(class_243.field_1353);
/* 347 */     class_243 pos = focusFish.method_19538();
/* 348 */     world.method_14199((class_2394)class_2398.field_11202, pos.field_1352, pos.field_1351 + 0.15D, pos.field_1350, 12, 0.28D, 0.08D, 0.28D, 0.02D);
/* 349 */     world.method_43128(null, pos.field_1352, pos.field_1351, pos.field_1350, class_3417.field_14660, class_3419.field_15248, 0.7F, 0.92F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void tickVisualFish(@NotNull class_3218 world, @NotNull class_3222 player, @NotNull ActiveSession activeSession) {
/* 355 */     class_243 castCenter = (activeSession.session.getPhase() == SafariFishingSession.Phase.HOOKED) ? getCastCenter(activeSession.castPos) : getCastCenter(world, activeSession);
/* 356 */     boolean hooked = (activeSession.session.getPhase() == SafariFishingSession.Phase.HOOKED);
/*     */     
/* 358 */     Iterator<VisualFish> iterator = activeSession.visualFish.iterator();
/* 359 */     while (iterator.hasNext()) {
/* 360 */       VisualFish visualFish = iterator.next();
/* 361 */       SafariFishEntity entity = getVisualFish(world, visualFish.entityId);
/* 362 */       if (entity == null || !entity.method_5805()) {
/* 363 */         iterator.remove();
/*     */         
/*     */         continue;
/*     */       } 
/* 367 */       if (visualFish.focusFish) {
/* 368 */         updateFocusFish(entity, player, activeSession, world, castCenter, visualFish);
/*     */       }
/*     */     } 
/*     */     
/* 372 */     if (hooked && activeSession.hookSplashTicks-- > 0 && activeSession.visualTicks % 3 == 0) {
/* 373 */       SafariFishEntity focusFish = getVisualFish(world, activeSession.focusFishId);
/* 374 */       if (focusFish != null) {
/* 375 */         world.method_14199((class_2394)class_2398.field_11244, focusFish.method_23317(), focusFish.method_23318() + 0.1D, focusFish.method_23321(), 3, 0.15D, 0.05D, 0.15D, 0.0D);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void updateFocusFish(@NotNull SafariFishEntity entity, @NotNull class_3222 player, @NotNull ActiveSession activeSession, @NotNull class_3218 world, @NotNull class_243 castCenter, @NotNull VisualFish visualFish) {
/* 388 */     SafariFishingSession session = activeSession.session;
/* 389 */     if (session.getPhase() == SafariFishingSession.Phase.WAITING) {
/* 390 */       double orbitAngle = activeSession.visualTicks * 0.09D + visualFish.angleOffset;
/* 391 */       class_243 class_2431 = castCenter.method_1031(
/* 392 */           Math.cos(orbitAngle) * 0.72D, -0.04D + 
/* 393 */           Math.sin(activeSession.visualTicks * 0.12D + visualFish.angleOffset) * 0.06D, 
/* 394 */           Math.sin(orbitAngle) * 0.72D);
/*     */       
/* 396 */       steerFish(entity, class_2431, 0.24D, 0.78D);
/*     */       
/*     */       return;
/*     */     } 
/* 400 */     SafariCatchProfile profile = session.getCatchProfile();
/* 401 */     class_243 toPlayer = new class_243(player.method_23317() - castCenter.field_1352, 0.0D, player.method_23321() - castCenter.field_1350);
/* 402 */     class_243 playerDir = (toPlayer.method_1027() < 1.0E-4D) ? new class_243(0.0D, 0.0D, 1.0D) : toPlayer.method_1029();
/* 403 */     class_243 sideDir = new class_243(-playerDir.field_1350, 0.0D, playerDir.field_1352);
/* 404 */     class_243 playerWaterPoint = (new class_243(player.method_23317(), castCenter.field_1351, player.method_23321())).method_1020(playerDir.method_1021(1.35D));
/*     */     
/* 406 */     double sizeFactor = class_3532.method_15350((profile.sizeScale() - 0.88D) / 0.5D, 0.0D, 1.0D);
/* 407 */     double weightFactor = class_3532.method_15350(profile.weightKg() / 45.0D, 0.0D, 1.0D);
/* 408 */     double rarityFactor = profile.rarity().ordinal() / ((SafariFishRarity.values()).length - 1);
/* 409 */     double powerFactor = class_3532.method_15350(sizeFactor * 0.45D + weightFactor * 0.4D + rarityFactor * 0.15D, 0.0D, 1.0D);
/* 410 */     double landProgress = session.getProgress();
/*     */     
/* 412 */     FishingInputState input = INPUTS.getOrDefault(player.method_5667(), FishingInputState.IDLE);
/* 413 */     boolean reeling = input.reeling();
/* 414 */     boolean startedReeling = (reeling && !activeSession.wasReeling);
/* 415 */     activeSession.wasReeling = reeling;
/*     */     
/* 417 */     advanceHookedRun(activeSession, world, powerFactor, landProgress, session.isThrashing(), reeling, startedReeling);
/* 418 */     tickHookedRunSmoothing(activeSession, reeling);
/*     */ 
/*     */     
/* 421 */     double approachCurve = class_3532.method_15350(Math.pow(landProgress, 2.6D), 0.0D, 1.0D) * 0.85D;
/* 422 */     class_243 fightCenter = castCenter.method_35590(playerWaterPoint, approachCurve);
/* 423 */     double reelPull = class_3532.method_15350(Math.pow(landProgress, 2.0D) * (0.14D + powerFactor * 0.08D), 0.0D, 0.3D);
/* 424 */     double thrashRadius = 0.06D + powerFactor * 0.1D + (session.isThrashing() ? (0.08D + powerFactor * 0.05D) : 0.02D);
/* 425 */     thrashRadius += session.getTension() * (0.08D + powerFactor * 0.05D);
/* 426 */     thrashRadius *= class_3532.method_15350(1.0D - landProgress * 0.55D, 0.18D, 1.0D);
/*     */     
/* 428 */     double frequency = session.isThrashing() ? (0.1D + powerFactor * 0.07D) : (0.045D + powerFactor * 0.03D);
/* 429 */     double angle = visualFish.angleOffset + activeSession.visualTicks * frequency;
/* 430 */     double lunging = Math.sin(activeSession.visualTicks * (0.11D + powerFactor * 0.08D) + visualFish.angleOffset);
/* 431 */     double surge = session.isThrashing() ? (Math.max(0.0D, lunging) * (0.12D + powerFactor * 0.12D)) : (lunging * 0.03D);
/* 432 */     double diveDepth = activeSession.currentRunDepthOffset;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 437 */     class_243 hookPoint = fightCenter.method_1019(playerDir.method_1021(activeSession.currentRunForwardOffset + reelPull - (session.isThrashing() ? 0.04D : 0.0D))).method_1019(sideDir.method_1021(activeSession.currentRunSideOffset + Math.sin(angle) * thrashRadius)).method_1019(playerDir.method_1021(Math.cos(angle * 0.7D) * (thrashRadius * 0.32D + surge)));
/*     */ 
/*     */     
/* 440 */     double verticalThrash = -diveDepth + Math.sin(activeSession.visualTicks * (0.12D + powerFactor * 0.05D) + visualFish.angleOffset) * (0.04D + powerFactor * 0.05D);
/* 441 */     if (session.isThrashing()) {
/* 442 */       verticalThrash += Math.max(0.0D, lunging) * (0.12D + powerFactor * 0.08D);
/*     */     }
/* 444 */     if (landProgress > 0.8199999928474426D) {
/* 445 */       verticalThrash += 0.12D;
/*     */     }
/*     */     
/* 448 */     class_243 desired = new class_243(hookPoint.field_1352, fightCenter.field_1351 + verticalThrash, hookPoint.field_1350);
/*     */     
/* 450 */     if (session.isThrashing() && activeSession.visualTicks % Math.max(3, 6 - (int)Math.round(powerFactor * 2.0D)) == 0) {
/* 451 */       world.method_14199((class_2394)class_2398.field_11202, entity.method_23317(), entity.method_23318() + 0.08D, entity.method_23321(), 3, 0.16D + powerFactor * 0.08D, 0.05D, 0.16D + powerFactor * 0.08D, 0.0D);
/*     */     }
/*     */     
/* 454 */     if (activeSession.visualTicks % 7 == 0) {
/* 455 */       world.method_14199((class_2394)class_2398.field_11244, castCenter.field_1352, castCenter.field_1351 + 0.02D, castCenter.field_1350, 2, 0.1D, 0.02D, 0.1D, 0.0D);
/*     */     }
/*     */     
/* 458 */     double pullStrength = session.isThrashing() ? (0.3D + powerFactor * 0.08D) : (0.24D + powerFactor * 0.05D);
/* 459 */     double damping = session.isThrashing() ? 0.84D : 0.88D;
/* 460 */     steerFish(entity, desired, pullStrength, damping);
/* 461 */     tugBobber(entity, activeSession, world, powerFactor, session, playerDir, fightCenter);
/*     */   }
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
/*     */   private static void advanceHookedRun(@NotNull ActiveSession activeSession, @NotNull class_3218 world, double powerFactor, double landProgress, boolean thrashing, boolean reeling, boolean startedReeling) {
/* 475 */     if (startedReeling) {
/* 476 */       double d = class_3532.method_15350(1.0D - landProgress * 0.5D, 0.35D, 1.0D);
/* 477 */       activeSession.targetRunForwardOffset = -(1.1D + powerFactor * 1.1D) * d;
/* 478 */       activeSession.targetRunSideOffset = (world.field_9229.method_43058() - 0.5D) * 2.0D * (0.32D + powerFactor * 0.6D) * d;
/* 479 */       activeSession.targetRunDepthOffset = (1.05D + powerFactor * 1.25D + (thrashing ? 0.35D : 0.0D)) * d;
/* 480 */       activeSession.runTicksTotal = 26 + world.field_9229.method_43048(14) + (int)Math.round(powerFactor * 14.0D);
/* 481 */       activeSession.runTicksRemaining = activeSession.runTicksTotal;
/* 482 */       activeSession.panicDiveTicks = 18 + (int)Math.round(powerFactor * 16.0D);
/*     */       
/*     */       return;
/*     */     } 
/* 486 */     if (activeSession.panicDiveTicks > 0) activeSession.panicDiveTicks--;
/*     */     
/* 488 */     if (activeSession.runTicksRemaining-- > 0) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 494 */     double distanceScale = class_3532.method_15350(1.0D - Math.pow(landProgress, 2.2D) * 0.72D, 0.22D, 1.0D);
/* 495 */     double approachWeight = class_3532.method_15350(Math.pow(landProgress, 2.6D), 0.0D, 1.0D);
/* 496 */     double awayBias = -(0.9D + powerFactor * 1.05D) * distanceScale;
/* 497 */     double nearBias = 0.18D + landProgress * 0.9D;
/* 498 */     activeSession
/* 499 */       .targetRunForwardOffset = class_3532.method_16436(approachWeight, awayBias, nearBias) + (world.field_9229.method_43058() - 0.5D) * (0.32D + powerFactor * 0.45D) * distanceScale;
/*     */     
/* 501 */     activeSession.targetRunSideOffset = (world.field_9229.method_43058() - 0.5D) * 2.0D * (0.55D + powerFactor * 0.85D) * distanceScale;
/*     */ 
/*     */     
/* 504 */     double depthBase = 0.55D + powerFactor * 0.75D + (thrashing ? 0.22D : 0.0D);
/* 505 */     if (reeling) {
/* 506 */       depthBase += 0.4D + powerFactor * 0.55D;
/*     */       
/* 508 */       if (world.field_9229.method_43057() < 0.35F) {
/* 509 */         depthBase += 0.35D + powerFactor * 0.45D;
/* 510 */         activeSession.panicDiveTicks = Math.max(activeSession.panicDiveTicks, 14);
/*     */       } 
/*     */     } 
/* 513 */     activeSession.targetRunDepthOffset = depthBase * distanceScale;
/* 514 */     activeSession.runTicksTotal = 20 + world.field_9229.method_43048(10) + (int)Math.round(powerFactor * 12.0D);
/* 515 */     activeSession.runTicksRemaining = activeSession.runTicksTotal;
/*     */   }
/*     */   
/*     */   private static void tickHookedRunSmoothing(@NotNull ActiveSession activeSession, boolean reeling) {
/* 519 */     activeSession.currentRunForwardOffset = class_3532.method_16436(0.12D, activeSession.currentRunForwardOffset, activeSession.targetRunForwardOffset);
/* 520 */     activeSession.currentRunSideOffset = class_3532.method_16436(0.12D, activeSession.currentRunSideOffset, activeSession.targetRunSideOffset);
/*     */     
/* 522 */     double depthRate = (activeSession.panicDiveTicks > 0) ? 0.22D : (reeling ? 0.14D : 0.1D);
/* 523 */     activeSession.currentRunDepthOffset = class_3532.method_16436(depthRate, activeSession.currentRunDepthOffset, activeSession.targetRunDepthOffset);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void tugBobber(@NotNull SafariFishEntity fish, @NotNull ActiveSession activeSession, @NotNull class_3218 world, double powerFactor, @NotNull SafariFishingSession session, @NotNull class_243 playerDir, @NotNull class_243 fightCenter) {
/* 535 */     AtlasSafariBobberEntity bobber = getBobber(world, activeSession.bobberId);
/* 536 */     if (bobber == null || !bobber.method_5805()) {
/*     */       return;
/*     */     }
/*     */     
/* 540 */     class_243 mouthAnchor = getFishMouthAnchor(fish, playerDir, fightCenter, powerFactor, session);
/* 541 */     class_243 velocity = mouthAnchor.method_1020(bobber.method_19538());
/* 542 */     bobber.method_18799(velocity);
/* 543 */     bobber.method_5808(mouthAnchor.field_1352, mouthAnchor.field_1351, mouthAnchor.field_1350, bobber.method_36454(), bobber.method_36455());
/*     */     
/* 545 */     if (session.isThrashing() && activeSession.visualTicks % 4 == 0) {
/* 546 */       world.method_14199((class_2394)class_2398.field_11202, bobber.method_23317(), bobber.method_23318() + 0.02D, bobber.method_23321(), 4, 0.12D + powerFactor * 0.06D, 0.03D, 0.12D + powerFactor * 0.06D, 0.0D);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   private static class_243 getFishMouthAnchor(@NotNull SafariFishEntity fish, @NotNull class_243 playerDir, @NotNull class_243 fightCenter, double powerFactor, @NotNull SafariFishingSession session) {
/* 557 */     float yawRadians = fish.method_36454() * 0.017453292F;
/* 558 */     class_243 facing = new class_243(-class_3532.method_15374(yawRadians), 0.0D, class_3532.method_15362(yawRadians));
/* 559 */     if (facing.method_1027() < 1.0E-4D) {
/* 560 */       facing = playerDir.method_1021(-1.0D);
/*     */     } else {
/* 562 */       facing = facing.method_1029();
/*     */     } 
/*     */     
/* 565 */     double mouthForward = 0.28D + fish.getSizeScale() * 0.16D + powerFactor * 0.08D;
/* 566 */     double mouthLift = 0.04D + (session.isThrashing() ? 0.015D : 0.0D);
/* 567 */     class_243 anchor = fish.method_19538().method_1019(facing.method_1021(mouthForward)).method_1031(0.0D, mouthLift, 0.0D);
/* 568 */     return new class_243(anchor.field_1352, class_3532.method_15350(anchor.field_1351, fightCenter.field_1351 - 1.25D, fightCenter.field_1351 + 0.28D), anchor.field_1350);
/*     */   }
/*     */   
/*     */   private static void steerFish(@NotNull SafariFishEntity entity, @NotNull class_243 desired, double pullStrength, double damping) {
/* 572 */     class_243 offset = desired.method_1020(entity.method_19538());
/* 573 */     class_243 velocity = entity.method_18798().method_1021(damping).method_1019(offset.method_1021(pullStrength));
/* 574 */     entity.method_18799(velocity);
/* 575 */     entity.method_5875(true);
/* 576 */     entity.method_5855(entity.method_5748());
/*     */     
/* 578 */     if (offset.method_37268() > 1.0E-4D) {
/* 579 */       float yaw = (float)(class_3532.method_15349(velocity.field_1350, velocity.field_1352) * 57.29577951308232D) - 90.0F;
/* 580 */       entity.method_36456(yaw);
/* 581 */       entity.method_5636(yaw);
/* 582 */       entity.method_5847(yaw);
/*     */     } 
/*     */     
/* 585 */     if (offset.method_1027() > 5.0D) {
/* 586 */       entity.method_5808(desired.field_1352, desired.field_1351, desired.field_1350, entity.method_36454(), entity.method_36455());
/* 587 */       entity.method_18799(class_243.field_1353);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void landCaughtFish(@NotNull class_3222 player, @NotNull ActiveSession activeSession) {
/* 592 */     discardVisualFish(player.method_51469(), activeSession, false);
/* 593 */     clearBobber(player.method_51469(), activeSession);
/* 594 */     SafariCatchProfile profile = activeSession.session.getCatchProfile();
/* 595 */     class_243 splashPos = getCastCenter(activeSession.castPos);
/* 596 */     player.method_51469().method_14199((class_2394)class_2398.field_11202, splashPos.field_1352, splashPos.field_1351 + 0.08D, splashPos.field_1350, 12, 0.35D, 0.12D, 0.35D, 0.03D);
/* 597 */     player.method_51469().method_43128(null, splashPos.field_1352, splashPos.field_1351, splashPos.field_1350, class_3417.field_14876, class_3419.field_15248, 0.8F, 0.95F);
/*     */     
/* 599 */     player.method_7353((class_2561)class_2561.method_43470("You wrestled in a " + profile.getDisplayLabel() + " weighing " + String.format("%.1fkg", new Object[] { Double.valueOf(profile.weightKg())
/* 600 */             }) + ".").method_27692(class_124.field_1065), false);
/*     */   }
/*     */   
/*     */   private static void discardVisualFish(@NotNull class_3218 world, @NotNull ActiveSession activeSession, boolean preserveFocusFish) {
/* 604 */     for (VisualFish visualFish : activeSession.visualFish) {
/* 605 */       if (preserveFocusFish && visualFish.focusFish)
/* 606 */         continue;  SafariFishEntity entity = getVisualFish(world, visualFish.entityId);
/* 607 */       if (entity != null)
/* 608 */         entity.method_31472(); 
/*     */     } 
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private static SafariFishEntity getVisualFish(@NotNull class_3218 world, @Nullable UUID uuid) {
/*     */     SafariFishEntity fishEntity;
/* 615 */     if (uuid == null) return null; 
/* 616 */     class_1297 class_1297 = world.method_14190(uuid); if (class_1297 instanceof SafariFishEntity) { fishEntity = (SafariFishEntity)class_1297; } else { return null; }
/* 617 */      return fishEntity;
/*     */   }
/*     */   @Nullable
/*     */   private static AtlasSafariBobberEntity getBobber(@NotNull class_3218 world, @Nullable UUID uuid) {
/*     */     AtlasSafariBobberEntity bobberEntity;
/* 622 */     if (uuid == null) return null; 
/* 623 */     class_1297 class_1297 = world.method_14190(uuid); if (class_1297 instanceof AtlasSafariBobberEntity) { bobberEntity = (AtlasSafariBobberEntity)class_1297; } else { return null; }
/* 624 */      return bobberEntity;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private static class_2338 resolveWaterAnchor(@NotNull class_3218 world, @NotNull AtlasSafariBobberEntity bobber) {
/* 629 */     class_2338 origin = bobber.method_24515();
/* 630 */     if (world.method_8316(origin).method_15767(class_3486.field_15517)) {
/* 631 */       return origin.method_10062();
/*     */     }
/*     */     
/* 634 */     class_2338 below = origin.method_10074();
/* 635 */     if (world.method_8316(below).method_15767(class_3486.field_15517)) {
/* 636 */       return below.method_10062();
/*     */     }
/*     */     
/* 639 */     for (int x = -1; x <= 1; x++) {
/* 640 */       for (int z = -1; z <= 1; z++) {
/* 641 */         class_2338 nearby = origin.method_10069(x, 0, z);
/* 642 */         if (world.method_8316(nearby).method_15767(class_3486.field_15517)) {
/* 643 */           return nearby.method_10062();
/*     */         }
/*     */         
/* 646 */         class_2338 nearbyBelow = nearby.method_10074();
/* 647 */         if (world.method_8316(nearbyBelow).method_15767(class_3486.field_15517)) {
/* 648 */           return nearbyBelow.method_10062();
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 653 */     return null;
/*     */   }
/*     */   
/*     */   private static void clearBobber(@NotNull class_3218 world, @NotNull ActiveSession activeSession) {
/* 657 */     AtlasSafariBobberEntity bobber = getBobber(world, activeSession.bobberId);
/* 658 */     if (bobber != null) {
/* 659 */       bobber.method_31472();
/*     */     }
/*     */   }
/*     */   
/*     */   private static class_243 getCastCenter(@NotNull class_3218 world, @NotNull ActiveSession activeSession) {
/* 664 */     AtlasSafariBobberEntity bobber = getBobber(world, activeSession.bobberId);
/* 665 */     if (bobber != null) {
/* 666 */       return bobber.method_19538().method_1031(0.0D, -0.12D, 0.0D);
/*     */     }
/* 668 */     return getCastCenter(activeSession.castPos);
/*     */   }
/*     */   
/*     */   private static class_243 getCastCenter(@NotNull class_2338 castPos) {
/* 672 */     return new class_243(castPos.method_10263() + 0.5D, castPos.method_10264() + 0.38D, castPos.method_10260() + 0.5D);
/*     */   }
/*     */   
/*     */   private static Optional<class_2338> findWaterSpawn(@NotNull class_3218 world, @NotNull class_2338 origin) {
/* 676 */     for (int y = -2; y <= 2; y++) {
/* 677 */       for (int radius = 1; radius <= 5; radius++) {
/* 678 */         for (int x = -radius; x <= radius; x++) {
/* 679 */           for (int z = -radius; z <= radius; z++) {
/* 680 */             class_2338 pos = origin.method_10069(x, y, z);
/* 681 */             if (world.method_8316(pos).method_15767(class_3486.field_15517)) {
/* 682 */               class_2680 stateAbove = world.method_8320(pos.method_10084());
/* 683 */               if (stateAbove.method_26215())
/* 684 */                 return Optional.of(pos); 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 690 */     return Optional.empty();
/*     */   }
/*     */   
/*     */   private static void sync(@NotNull class_3222 player, @NotNull SafariFishingSession session) {
/* 694 */     SafariCatchProfile profile = session.getCatchProfile();
/* 695 */     SafariFishingNetwork.sendUpdate(player, new SafariFishingNetwork.UpdatePayload(true, session
/*     */           
/* 697 */           .getPhase().name(), profile
/* 698 */           .getDisplayLabel(), profile
/* 699 */           .rarity().getDisplayName(), profile
/* 700 */           .rarity().getColor(), 
/* 701 */           (float)profile.weightKg(), 
/* 702 */           (float)profile.sizeScale(), session
/* 703 */           .getPointer(), session
/* 704 */           .getZoneCenter(), session
/* 705 */           .getSafeZoneWidth(), session
/* 706 */           .getProgress(), session
/* 707 */           .getTension(), session
/* 708 */           .isThrashing(), session
/* 709 */           .getWaitTicksRemaining(), 
/* 710 */           formatUpgradeDisplay(session, SafariUpgradeSlot.BAIT), 
/* 711 */           formatUpgradeDisplay(session, SafariUpgradeSlot.LINE), 
/* 712 */           formatUpgradeDisplay(session, SafariUpgradeSlot.REEL)));
/*     */   }
/*     */ 
/*     */   
/*     */   private static String formatUpgradeDisplay(@NotNull SafariFishingSession session, @NotNull SafariUpgradeSlot slot) {
/* 717 */     String value = safeUpgradeName(session, slot);
/* 718 */     return value.isBlank() ? "" : SafariRodData.prettyName(value);
/*     */   }
/*     */   
/*     */   private static String safeUpgradeName(@NotNull SafariFishingSession session, @NotNull SafariUpgradeSlot slot) {
/* 722 */     switch (slot) { default: throw new MatchException(null, null);case BAIT: case LINE: case REEL: break; }  return 
/*     */ 
/*     */       
/* 725 */       emptyIfNull(session.getLoadout().reel());
/*     */   }
/*     */ 
/*     */   
/*     */   private static String emptyIfNull(@Nullable String value) {
/* 730 */     return (value == null) ? "" : value;
/*     */   }
/*     */   
/*     */   private static SafariFishingNetwork.UpdatePayload emptyUpdate() {
/* 734 */     return new SafariFishingNetwork.UpdatePayload(false, "NONE", "", "", -1, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, false, 0, "", "", "");
/*     */   }
/*     */   
/*     */   private static final class ActiveSession {
/*     */     private final SafariFishingSession session;
/*     */     private class_2338 castPos;
/*     */     @Nullable
/*     */     private final UUID bobberId;
/* 742 */     private final List<SafariFishingManager.VisualFish> visualFish = new ArrayList<>();
/*     */     private int visualTicks;
/*     */     @Nullable
/*     */     private UUID focusFishId;
/*     */     private int hookSplashTicks;
/*     */     private double targetRunForwardOffset;
/*     */     private double targetRunSideOffset;
/*     */     private double targetRunDepthOffset;
/*     */     private double currentRunForwardOffset;
/*     */     private double currentRunSideOffset;
/*     */     private double currentRunDepthOffset;
/*     */     private int runTicksRemaining;
/*     */     private int runTicksTotal;
/*     */     private int panicDiveTicks;
/*     */     private boolean wasReeling;
/*     */     
/*     */     private ActiveSession(@NotNull SafariFishingSession session, @NotNull class_2338 castPos, @Nullable UUID bobberId) {
/* 759 */       this.session = session;
/* 760 */       this.castPos = castPos;
/* 761 */       this.bobberId = bobberId;
/*     */     } } private static final class PendingCast extends Record { @NotNull
/*     */     private final class_1799 rodStack; @NotNull
/*     */     private final UUID bobberId; private final int ageTicks;
/* 765 */     private PendingCast(@NotNull class_1799 rodStack, @NotNull UUID bobberId, int ageTicks) { this.rodStack = rodStack; this.bobberId = bobberId; this.ageTicks = ageTicks; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/safari/fishing/SafariFishingManager$PendingCast;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #765	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/* 765 */       //   0	7	0	this	Lcom/atlas/common/fabric/safari/fishing/SafariFishingManager$PendingCast; } @NotNull public class_1799 rodStack() { return this.rodStack; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/safari/fishing/SafariFishingManager$PendingCast;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #765	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/safari/fishing/SafariFishingManager$PendingCast; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/safari/fishing/SafariFishingManager$PendingCast;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #765	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lcom/atlas/common/fabric/safari/fishing/SafariFishingManager$PendingCast;
/* 765 */       //   0	8	1	o	Ljava/lang/Object; } @NotNull public UUID bobberId() { return this.bobberId; } public int ageTicks() { return this.ageTicks; }
/*     */      private PendingCast tick() {
/* 767 */       return new PendingCast(this.rodStack, this.bobberId, this.ageTicks + 1);
/*     */     } }
/*     */   private static final class VisualFish extends Record { @NotNull
/*     */     private final UUID entityId; private final double angleOffset; private final boolean focusFish;
/* 771 */     private VisualFish(@NotNull UUID entityId, double angleOffset, boolean focusFish) { this.entityId = entityId; this.angleOffset = angleOffset; this.focusFish = focusFish; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/safari/fishing/SafariFishingManager$VisualFish;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #771	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/safari/fishing/SafariFishingManager$VisualFish; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/safari/fishing/SafariFishingManager$VisualFish;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #771	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/safari/fishing/SafariFishingManager$VisualFish; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/safari/fishing/SafariFishingManager$VisualFish;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #771	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lcom/atlas/common/fabric/safari/fishing/SafariFishingManager$VisualFish;
/* 771 */       //   0	8	1	o	Ljava/lang/Object; } @NotNull public UUID entityId() { return this.entityId; } public double angleOffset() { return this.angleOffset; } public boolean focusFish() { return this.focusFish; }
/*     */      }
/*     */ 
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\safari\fishing\SafariFishingManager.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */