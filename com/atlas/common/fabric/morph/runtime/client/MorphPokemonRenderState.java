/*     */ package com.atlas.common.fabric.morph.runtime.client;
/*     */ import com.cobblemon.mod.common.CobblemonEntities;
/*     */ import com.cobblemon.mod.common.api.entity.PokemonSideDelegate;
/*     */ import com.cobblemon.mod.common.api.pokemon.PokemonSpecies;
/*     */ import com.cobblemon.mod.common.client.entity.PokemonClientDelegate;
/*     */ import com.cobblemon.mod.common.client.render.models.blockbench.PosableState;
/*     */ import com.cobblemon.mod.common.client.render.models.blockbench.animation.ActiveAnimation;
/*     */ import com.cobblemon.mod.common.entity.PoseType;
/*     */ import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
/*     */ import com.cobblemon.mod.common.pokemon.Pokemon;
/*     */ import com.cobblemon.mod.common.pokemon.Species;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Objects;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
/*     */ import net.fabricmc.api.EnvType;
/*     */ import net.fabricmc.api.Environment;
/*     */ import net.minecraft.class_1297;
/*     */ import net.minecraft.class_1937;
/*     */ import net.minecraft.class_243;
/*     */ import net.minecraft.class_2960;
/*     */ import net.minecraft.class_310;
/*     */ import net.minecraft.class_4587;
/*     */ import net.minecraft.class_4597;
/*     */ import net.minecraft.class_638;
/*     */ import net.minecraft.class_742;
/*     */ import net.minecraft.class_897;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ @Environment(EnvType.CLIENT)
/*     */ public final class MorphPokemonRenderState {
/*     */   private static final String PIKACHU_SPECIES = "cobblemon:pikachu";
/*     */   private static final String PIKACHU_GROUND_RUN_ANIMATION = "pikachu:animation.pikachu.ground_run";
/*  37 */   private static final Map<UUID, CachedPokemon> CACHE = new HashMap<>();
/*     */ 
/*     */ 
/*     */   
/*     */   public static void clear(@NotNull UUID playerId) {
/*  42 */     CACHE.remove(playerId);
/*     */   }
/*     */   
/*     */   public static void clearAll() {
/*  46 */     CACHE.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean render(@NotNull class_742 player, float yaw, float tickDelta, @NotNull class_4587 matrices, @NotNull class_4597 vertexConsumers, int light) {
/*  57 */     class_638 world = (class_310.method_1551()).field_1687;
/*  58 */     if (world == null) {
/*  59 */       return false;
/*     */     }
/*     */     
/*  62 */     MorphedPlayerRegistry.Entry entry = MorphedPlayerRegistry.get().get(player.method_5667());
/*  63 */     if (entry == null) {
/*  64 */       return false;
/*     */     }
/*     */     
/*  67 */     CachedPokemon cached = CACHE.compute(player.method_5667(), (uuid, existing) -> {
/*     */           String signature = buildSignature(entry);
/*     */           
/*     */           if (existing != null && signature.equals(existing.signature)) {
/*     */             return existing;
/*     */           }
/*     */           
/*     */           PokemonEntity entity = createEntity(entry);
/*     */           
/*     */           return (entity == null) ? null : new CachedPokemon(signature, entry.species, entity, Long.MIN_VALUE);
/*     */         });
/*     */     
/*  79 */     if (cached == null) {
/*  80 */       return false;
/*     */     }
/*     */     
/*  83 */     PokemonEntity fake = cached.entity;
/*  84 */     updatePartialTicks(fake, tickDelta);
/*  85 */     advanceAnimation(world.method_8510(), cached, fake, player);
/*  86 */     syncToPlayer(fake, player);
/*     */ 
/*     */     
/*     */     try {
/*  90 */       class_897 renderer = class_310.method_1551().method_1561().method_3953((class_1297)fake);
/*  91 */       renderer.method_3936((class_1297)fake, yaw, tickDelta, matrices, vertexConsumers, light);
/*  92 */       return true;
/*  93 */     } catch (Exception ignored) {
/*  94 */       return false;
/*     */     } 
/*     */   }
/*     */   @Nullable
/*     */   public static PokemonEntity getRenderedPokemon(@NotNull UUID playerId) {
/*  99 */     CachedPokemon cached = CACHE.get(playerId);
/* 100 */     return (cached == null) ? null : cached.entity;
/*     */   }
/*     */   public static void triggerMoveAnimation(@NotNull UUID playerId, @NotNull String moveSlug) {
/*     */     PosableState state;
/* 104 */     CachedPokemon cached = CACHE.get(playerId);
/* 105 */     if (cached != null) { PokemonSideDelegate pokemonSideDelegate = cached.entity.getDelegate(); if (pokemonSideDelegate instanceof PosableState) { state = (PosableState)pokemonSideDelegate; }
/*     */       else { return; }
/*     */        }
/*     */     else { return; }
/* 109 */      Set<String> animationChoices = MorphMoveEffects.animationChoices(moveSlug);
/* 110 */     if (!animationChoices.isEmpty()) {
/* 111 */       state.addFirstAnimation(animationChoices);
/*     */     }
/*     */   }
/*     */   
/*     */   private static void advanceAnimation(long worldTime, CachedPokemon cached, PokemonEntity fake, class_742 player) {
/* 116 */     long lastTick = cached.lastAnimatedTick;
/* 117 */     long ticksToAdvance = (lastTick == Long.MIN_VALUE) ? 1L : Math.max(0L, Math.min(5L, worldTime - lastTick));
/*     */     
/* 119 */     boolean moving = isMoving(player);
/* 120 */     boolean flying = isFlying(player);
/* 121 */     updateSprintAnimation(cached, fake, player, moving, flying);
/*     */     
/* 123 */     if (ticksToAdvance <= 0L) {
/*     */       return;
/*     */     }
/*     */     
/* 127 */     for (int i = 0; i < ticksToAdvance; i++) {
/* 128 */       fake.field_6012++;
/* 129 */       fake.setTicksLived(fake.getTicksLived() + 1);
/* 130 */       fake.setPokemonWalking(moving);
/* 131 */       fake.setPokemonFlying(flying);
/* 132 */       fake.getSchedulingTracker().update(0.05F);
/* 133 */       suppressShinyEffects(fake);
/*     */       try {
/* 135 */         fake.getDelegate().tick((class_1297)fake);
/* 136 */       } catch (Exception exception) {}
/*     */     } 
/*     */ 
/*     */     
/* 140 */     cached.lastAnimatedTick = worldTime;
/*     */   }
/*     */   
/*     */   private static void updatePartialTicks(PokemonEntity fake, float tickDelta) {
/* 144 */     PokemonSideDelegate pokemonSideDelegate = fake.getDelegate(); if (pokemonSideDelegate instanceof PosableState) { PosableState state = (PosableState)pokemonSideDelegate;
/* 145 */       state.updatePartialTicks(tickDelta); }
/*     */   
/*     */   }
/*     */   
/*     */   private static void syncToPlayer(PokemonEntity fake, class_742 player) {
/* 150 */     class_243 prevPos = new class_243(player.field_6014, player.field_6036, player.field_5969);
/* 151 */     class_243 curPos = player.method_19538();
/* 152 */     class_243 velocity = curPos.method_1020(prevPos);
/* 153 */     boolean moving = isMoving(player);
/* 154 */     boolean flying = isFlying(player);
/*     */     
/* 156 */     fake.field_6014 = player.field_6014;
/* 157 */     fake.field_6036 = player.field_6036;
/* 158 */     fake.field_5969 = player.field_5969;
/* 159 */     fake.method_23327(curPos.field_1352, curPos.field_1351, curPos.field_1350);
/* 160 */     fake.method_18799(velocity);
/*     */     
/* 162 */     fake.field_5982 = player.field_5982;
/* 163 */     fake.method_36456(player.method_36454());
/* 164 */     fake.field_6220 = player.field_6220;
/* 165 */     fake.method_5636(player.field_6283);
/* 166 */     fake.field_6259 = player.field_6259;
/* 167 */     fake.method_5847(player.field_6241);
/* 168 */     fake.field_6004 = player.field_6004;
/* 169 */     fake.method_36457(player.method_36455());
/*     */     
/* 171 */     fake.method_24830(player.method_24828());
/* 172 */     fake.setPokemonWalking(moving);
/* 173 */     fake.setPokemonFlying(flying);
/* 174 */     fake.method_5841().method_12778(PokemonEntity.getPOSE_TYPE(), flying ? (
/* 175 */         moving ? PoseType.FLY : PoseType.HOVER) : (
/* 176 */         moving ? PoseType.WALK : PoseType.STAND));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void updateSprintAnimation(CachedPokemon cached, PokemonEntity fake, class_742 player, boolean moving, boolean flying) {
/*     */     PosableState state;
/* 186 */     PokemonSideDelegate pokemonSideDelegate = fake.getDelegate(); if (pokemonSideDelegate instanceof PosableState) { state = (PosableState)pokemonSideDelegate; }
/*     */     else
/*     */     { return; }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 193 */     boolean shouldRun = (cached.speciesId.equals("cobblemon:pikachu") && moving && !flying && player.method_5624());
/*     */     
/* 195 */     if (!shouldRun) {
/* 196 */       if (cached.runAnimation != null) {
/* 197 */         state.getActiveAnimations().remove(cached.runAnimation);
/* 198 */         cached.runAnimation = null;
/*     */       } 
/*     */       
/*     */       return;
/*     */     } 
/* 203 */     if (cached.runAnimation != null || state.getCurrentModel() == null) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 208 */     ActiveAnimation runAnimation = state.getCurrentModel().getAnimation(state, "pikachu:animation.pikachu.ground_run", state.getRuntime());
/* 209 */     if (runAnimation == null) {
/*     */       return;
/*     */     }
/*     */     
/* 213 */     cached.runAnimation = runAnimation;
/* 214 */     state.getActiveAnimations().add(runAnimation);
/* 215 */     runAnimation.start(state);
/*     */   }
/*     */   @Nullable
/*     */   private static PokemonEntity createEntity(MorphedPlayerRegistry.Entry entry) {
/* 219 */     class_638 world = (class_310.method_1551()).field_1687;
/* 220 */     if (world == null) {
/* 221 */       return null;
/*     */     }
/*     */     
/* 224 */     Species species = PokemonSpecies.getByIdentifier(class_2960.method_60654(entry.species));
/* 225 */     if (species == null) {
/* 226 */       return null;
/*     */     }
/*     */     
/* 229 */     Pokemon pokemon = new Pokemon();
/* 230 */     pokemon.setUuid(UUID.randomUUID());
/* 231 */     pokemon.setSpecies(species);
/* 232 */     if (!entry.aspects.isEmpty()) {
/* 233 */       pokemon.setForcedAspects(new HashSet(Arrays.asList((Object[])entry.aspects.split(","))));
/*     */     }
/*     */     
/* 236 */     PokemonEntity entity = new PokemonEntity((class_1937)world, pokemon, CobblemonEntities.POKEMON);
/* 237 */     entity.setPokemon(pokemon);
/* 238 */     entity.method_5977(true);
/* 239 */     entity.method_5803(true);
/* 240 */     entity.field_5960 = true;
/* 241 */     entity.method_5684(true);
/* 242 */     entity.method_5841().method_12778(PokemonEntity.getUNBATTLEABLE(), Boolean.valueOf(true));
/* 243 */     entity.method_5841().method_12778(PokemonEntity.getHIDE_LABEL(), Boolean.valueOf(true));
/* 244 */     entity.method_5841().method_12778(PokemonEntity.getSHOULD_RENDER_NAME(), Boolean.valueOf(false));
/* 245 */     suppressShinyEffects(entity);
/* 246 */     return entity;
/*     */   }
/*     */   
/*     */   private static void suppressShinyEffects(PokemonEntity pokemonEntity) {
/* 250 */     PokemonSideDelegate pokemonSideDelegate = pokemonEntity.getDelegate(); if (pokemonSideDelegate instanceof PokemonClientDelegate) { PokemonClientDelegate delegate = (PokemonClientDelegate)pokemonSideDelegate;
/* 251 */       delegate.setShined(true);
/* 252 */       delegate.setLastShinyParticle(System.currentTimeMillis()); }
/*     */   
/*     */   }
/*     */   
/*     */   private static boolean isMoving(class_742 player) {
/* 257 */     return (player.method_18798().method_37268() > 1.0E-4D || 
/* 258 */       Math.abs(player.method_23317() - player.field_6014) > 1.0E-4D || 
/* 259 */       Math.abs(player.method_23321() - player.field_5969) > 1.0E-4D);
/*     */   }
/*     */   
/*     */   private static boolean isFlying(class_742 player) {
/* 263 */     PokemonEntity mount = MorphedPlayerRegistry.get().getMount(player.method_5667());
/* 264 */     if (mount != null) {
/* 265 */       return (!mount.method_24828() || (mount.method_18798()).field_1351 > 0.02D || mount.method_18798().method_37268() > 1.0E-4D);
/*     */     }
/* 267 */     return (!player.method_24828() && (player.method_18798()).field_1351 > 0.02D);
/*     */   }
/*     */   
/*     */   private static String buildSignature(MorphedPlayerRegistry.Entry entry) {
/* 271 */     return entry.species + "|" + entry.species;
/*     */   }
/*     */   
/*     */   private static final class CachedPokemon { private final String signature;
/*     */     private final String speciesId;
/*     */     private final PokemonEntity entity;
/*     */     private long lastAnimatedTick;
/*     */     private ActiveAnimation runAnimation;
/*     */     
/*     */     private CachedPokemon(String signature, String speciesId, PokemonEntity entity, long lastAnimatedTick) {
/* 281 */       this.signature = Objects.<String>requireNonNull(signature);
/* 282 */       this.speciesId = Objects.<String>requireNonNull(speciesId);
/* 283 */       this.entity = Objects.<PokemonEntity>requireNonNull(entity);
/* 284 */       this.lastAnimatedTick = lastAnimatedTick;
/*     */     } }
/*     */ 
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\morph\runtime\client\MorphPokemonRenderState.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */