/*     */ package com.atlas.common.fabric.gocatch.client;
/*     */ 
/*     */ import com.atlas.common.fabric.gocatch.GoCatchConfig;
/*     */ import com.atlas.common.fabric.gocatch.GoCatchRingTiming;
/*     */ import com.atlas.common.fabric.gocatch.GoCatchThrowQuality;
/*     */ import com.cobblemon.mod.common.api.tags.CobblemonItemTags;
/*     */ import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
/*     */ import com.cobblemon.mod.common.pokemon.Pokemon;
/*     */ import java.util.Optional;
/*     */ import java.util.UUID;
/*     */ import net.fabricmc.api.EnvType;
/*     */ import net.fabricmc.api.Environment;
/*     */ import net.minecraft.class_1297;
/*     */ import net.minecraft.class_1657;
/*     */ import net.minecraft.class_1799;
/*     */ import net.minecraft.class_238;
/*     */ import net.minecraft.class_243;
/*     */ import net.minecraft.class_310;
/*     */ import net.minecraft.class_746;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Environment(EnvType.CLIENT)
/*     */ public final class GoCatchTargetingTracker
/*     */ {
/*  30 */   private static final class_243 DEFAULT_RING_NORMAL = new class_243(0.0D, 0.0D, 1.0D);
/*     */   
/*  32 */   private static final GoCatchTargetingTracker INSTANCE = new GoCatchTargetingTracker();
/*     */   
/*     */   public static GoCatchTargetingTracker getInstance() {
/*  35 */     return INSTANCE;
/*     */   }
/*     */   
/*  38 */   private PokemonEntity targetedPokemon = null;
/*  39 */   private double currentRingSize = 1.0D;
/*  40 */   private class_243 currentRingCenter = class_243.field_1353;
/*  41 */   private class_243 currentRingNormal = DEFAULT_RING_NORMAL;
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean cursorInRing = false;
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/*  50 */     class_310 client = class_310.method_1551();
/*  51 */     GoCatchConfig config = GoCatchConfig.getInstance();
/*  52 */     if (!config.enabled) {
/*  53 */       clearState();
/*     */       
/*     */       return;
/*     */     } 
/*  57 */     class_746 player = client.field_1724;
/*  58 */     if (player == null || client.field_1687 == null) {
/*  59 */       clearState();
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/*  64 */     if (!isHoldingPokeBall((class_1657)player)) {
/*  65 */       clearState();
/*     */       
/*     */       return;
/*     */     } 
/*  69 */     PokemonEntity best = findBestTarget(client, player, config);
/*  70 */     this.targetedPokemon = best;
/*     */     
/*  72 */     if (best != null) {
/*  73 */       Pokemon pokemonData = best.getPokemon();
/*  74 */       String tier = GoCatchFlagCache.getInstance().getTier(best.method_5628());
/*  75 */       this.currentRingSize = GoCatchRingTiming.calculateRingSize(pokemonData, tier, config, System.currentTimeMillis());
/*     */ 
/*     */       
/*  78 */       class_243 eyePos = player.method_33571();
/*  79 */       class_243 pokemonCenter = GoCatchCaptureRingGeometry.getPokemonCenter(best.method_19538(), best.method_17682());
/*  80 */       this.currentRingCenter = GoCatchCaptureRingGeometry.computeRingCenter(pokemonCenter, best.method_5829(), eyePos);
/*  81 */       this.currentRingNormal = GoCatchCaptureRingGeometry.computeRingNormal(this.currentRingCenter, eyePos);
/*  82 */       this.cursorInRing = GoCatchCaptureRingGeometry.isCursorInsideRing(eyePos, player
/*     */           
/*  84 */           .method_5828(1.0F), this.currentRingCenter, this.currentRingNormal, this.currentRingSize);
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/*  90 */       this.cursorInRing = false;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void clearState() {
/*  95 */     this.targetedPokemon = null;
/*  96 */     this.cursorInRing = false;
/*  97 */     this.currentRingCenter = class_243.field_1353;
/*  98 */     this.currentRingNormal = DEFAULT_RING_NORMAL;
/*     */   }
/*     */   
/*     */   private PokemonEntity findBestTarget(class_310 client, class_746 player, GoCatchConfig config) {
/* 102 */     class_243 eyePos = player.method_33571();
/* 103 */     class_243 lookVec = player.method_5828(1.0F);
/* 104 */     double range = config.targetingRange;
/* 105 */     double bestDist = Double.MAX_VALUE;
/* 106 */     PokemonEntity bestTarget = null;
/*     */     
/* 108 */     for (class_1297 entity : client.field_1687.method_18112()) {
/* 109 */       if (entity instanceof PokemonEntity) { PokemonEntity pokemonEntity = (PokemonEntity)entity;
/* 110 */         if (shouldSkipPokemon(pokemonEntity))
/*     */           continue; 
/* 112 */         double dist = entity.method_19538().method_1022(player.method_19538());
/* 113 */         if (dist > range) {
/*     */           continue;
/*     */         }
/* 116 */         class_238 box = entity.method_5829().method_1014(0.3D);
/* 117 */         if (box.method_1006(eyePos)) {
/* 118 */           if (dist < bestDist) {
/* 119 */             bestDist = dist;
/* 120 */             bestTarget = pokemonEntity;
/*     */           } 
/*     */           
/*     */           continue;
/*     */         } 
/*     */         
/* 126 */         class_243 endPos = eyePos.method_1019(lookVec.method_1021(range));
/* 127 */         Optional<class_243> hitResult = box.method_992(eyePos, endPos);
/* 128 */         if (hitResult.isPresent()) {
/* 129 */           double hitDist = ((class_243)hitResult.get()).method_1022(eyePos);
/* 130 */           if (hitDist < bestDist) {
/* 131 */             bestDist = hitDist;
/* 132 */             bestTarget = pokemonEntity;
/*     */           } 
/*     */         }  }
/*     */     
/*     */     } 
/* 137 */     return bestTarget;
/*     */   }
/*     */   
/*     */   private boolean isHoldingPokeBall(class_1657 player) {
/* 141 */     class_1799 mainHand = player.method_6047();
/* 142 */     class_1799 offHand = player.method_6079();
/* 143 */     if (mainHand == null || offHand == null) return false;
/*     */     
/* 145 */     if (mainHand.method_7909() instanceof com.cobblemon.mod.common.item.PokeBallItem || offHand.method_7909() instanceof com.cobblemon.mod.common.item.PokeBallItem) {
/* 146 */       return true;
/*     */     }
/*     */     try {
/* 149 */       return (mainHand.method_31573(CobblemonItemTags.POKE_BALLS) || offHand.method_31573(CobblemonItemTags.POKE_BALLS));
/* 150 */     } catch (Exception ignored) {
/* 151 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean shouldSkipPokemon(PokemonEntity pokemonEntity) {
/* 157 */     if (!GoCatchFlagCache.getInstance().isFlagged(pokemonEntity.method_5628())) return true;
/*     */ 
/*     */     
/* 160 */     if (pokemonEntity.method_5767()) return true; 
/* 161 */     if (pokemonEntity.getBeamMode() != 0) return true; 
/* 162 */     if (pokemonEntity.getPhasingTargetId() != -1) return true;
/*     */     
/* 164 */     Pokemon pokemonData = pokemonEntity.getPokemon();
/*     */ 
/*     */     
/* 167 */     if (pokemonData.getCurrentHealth() <= 0) return true;
/*     */     
/* 169 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PokemonEntity getTargetedPokemon() {
/* 176 */     return this.targetedPokemon;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public UUID getTargetedPokemonUuid() {
/* 185 */     if (this.targetedPokemon == null) return null; 
/* 186 */     return this.targetedPokemon.method_5667();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getCurrentRingSize() {
/* 193 */     return this.currentRingSize;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isCursorInRing() {
/* 200 */     return this.cursorInRing;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GoCatchThrowQuality getCurrentThrowQuality() {
/* 207 */     if (this.targetedPokemon == null || !this.cursorInRing) return GoCatchThrowQuality.NONE; 
/* 208 */     return GoCatchThrowQuality.fromRingSize(this.currentRingSize, GoCatchConfig.getInstance());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isTargeting() {
/* 215 */     return (this.targetedPokemon != null);
/*     */   }
/*     */   
/*     */   public void reset() {
/* 219 */     clearState();
/* 220 */     this.currentRingSize = 1.0D;
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\gocatch\client\GoCatchTargetingTracker.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */