/*     */ package com.atlas.common.fabric.gocatch.client;
/*     */ 
/*     */ import com.atlas.common.fabric.gocatch.GoCatchConfig;
/*     */ import com.atlas.common.fabric.gocatch.GoCatchThrowQuality;
/*     */ import com.cobblemon.mod.common.api.tags.CobblemonItemTags;
/*     */ import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
/*     */ import java.util.UUID;
/*     */ import net.fabricmc.api.EnvType;
/*     */ import net.fabricmc.api.Environment;
/*     */ import net.minecraft.class_1297;
/*     */ import net.minecraft.class_1657;
/*     */ import net.minecraft.class_1799;
/*     */ import net.minecraft.class_310;
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
/*     */ @Environment(EnvType.CLIENT)
/*     */ public final class GoCatchClientThrowTracker
/*     */ {
/*  28 */   private static final GoCatchClientThrowTracker INSTANCE = new GoCatchClientThrowTracker();
/*     */   
/*     */   public static GoCatchClientThrowTracker getInstance() {
/*  31 */     return INSTANCE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean wasHoldingPokeBall;
/*     */ 
/*     */   
/*     */   private boolean wasUsePressed;
/*     */   
/*     */   private UUID lastTargetedPokemonUuid;
/*     */ 
/*     */   
/*     */   public void startTick(class_310 client) {
/*  45 */     if (client.field_1724 == null) {
/*  46 */       this.wasHoldingPokeBall = false;
/*     */       return;
/*     */     } 
/*  49 */     this.wasHoldingPokeBall = isHoldingPokeBall((class_1657)client.field_1724);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/*  56 */     class_310 client = class_310.method_1551();
/*  57 */     if (client.field_1724 == null || client.field_1687 == null) {
/*  58 */       this.wasUsePressed = false;
/*     */       
/*     */       return;
/*     */     } 
/*  62 */     if (!(GoCatchConfig.getInstance()).enabled) {
/*  63 */       this.wasUsePressed = false;
/*     */       
/*     */       return;
/*     */     } 
/*  67 */     GoCatchTargetingTracker tracker = GoCatchTargetingTracker.getInstance();
/*  68 */     boolean usePressed = client.field_1690.field_1904.method_1434();
/*     */ 
/*     */ 
/*     */     
/*  72 */     if (this.wasHoldingPokeBall && usePressed && !this.wasUsePressed) {
/*  73 */       onThrowIntent(client, tracker);
/*     */     }
/*     */     
/*  76 */     PokemonEntity targetedPokemon = tracker.getTargetedPokemon();
/*  77 */     if (targetedPokemon != null) {
/*  78 */       this.lastTargetedPokemonUuid = targetedPokemon.method_5667();
/*     */     }
/*     */     
/*  81 */     this.wasUsePressed = usePressed;
/*     */   }
/*     */   
/*     */   private void onThrowIntent(class_310 client, GoCatchTargetingTracker tracker) {
/*     */     UUID targetUuid;
/*     */     GoCatchThrowQuality qualityToSend;
/*  87 */     GoCatchCurveBallDetector curveDetector = GoCatchCurveBallDetector.getInstance();
/*  88 */     boolean isCurveBall = curveDetector.isCurveBallGesture();
/*     */     
/*  90 */     if (isCurveBall) {
/*  91 */       GoCatchCurveBallTrailTracker.getInstance().queuePendingTrail();
/*     */     }
/*     */     
/*  94 */     if (tracker.getTargetedPokemon() != null) {
/*  95 */       targetUuid = tracker.getTargetedPokemon().method_5667();
/*  96 */       qualityToSend = tracker.getCurrentThrowQuality();
/*     */     } else {
/*  98 */       targetUuid = this.lastTargetedPokemonUuid;
/*  99 */       qualityToSend = GoCatchThrowQuality.NONE;
/*     */     } 
/*     */     
/* 102 */     if (targetUuid == null) {
/* 103 */       curveDetector.onThrow();
/*     */       
/*     */       return;
/*     */     } 
/* 107 */     if (!isPokemonPresent(client, targetUuid)) {
/* 108 */       curveDetector.onThrow();
/*     */       
/*     */       return;
/*     */     } 
/* 112 */     GoCatchClientNetworking.sendClientThrowQuality(targetUuid, qualityToSend, isCurveBall);
/* 113 */     curveDetector.onThrow();
/*     */   }
/*     */   
/*     */   private boolean isHoldingPokeBall(class_1657 player) {
/* 117 */     class_1799 mainHand = player.method_6047();
/* 118 */     class_1799 offHand = player.method_6079();
/* 119 */     if (mainHand == null || offHand == null) return false;
/*     */     
/* 121 */     if (mainHand.method_7909() instanceof com.cobblemon.mod.common.item.PokeBallItem || offHand.method_7909() instanceof com.cobblemon.mod.common.item.PokeBallItem) {
/* 122 */       return true;
/*     */     }
/*     */     
/*     */     try {
/* 126 */       return (mainHand.method_31573(CobblemonItemTags.POKE_BALLS) || offHand.method_31573(CobblemonItemTags.POKE_BALLS));
/* 127 */     } catch (Exception ignored) {
/* 128 */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean isPokemonPresent(class_310 client, UUID targetUuid) {
/* 133 */     if (client.field_1687 == null) return false; 
/* 134 */     for (class_1297 entity : client.field_1687.method_18112()) {
/* 135 */       if (entity instanceof PokemonEntity) { PokemonEntity pokemon = (PokemonEntity)entity; if (targetUuid.equals(pokemon.method_5667()))
/* 136 */           return true;  }
/*     */     
/*     */     } 
/* 139 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\gocatch\client\GoCatchClientThrowTracker.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */