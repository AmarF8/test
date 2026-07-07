/*     */ package com.atlas.common.fabric.gocatch.client;
/*     */ 
/*     */ import com.atlas.common.fabric.gocatch.GoCatchThrowQuality;
/*     */ import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
/*     */ import net.fabricmc.api.EnvType;
/*     */ import net.fabricmc.api.Environment;
/*     */ import net.minecraft.class_1297;
/*     */ import net.minecraft.class_1306;
/*     */ import net.minecraft.class_1657;
/*     */ import net.minecraft.class_2378;
/*     */ import net.minecraft.class_2394;
/*     */ import net.minecraft.class_2398;
/*     */ import net.minecraft.class_243;
/*     */ import net.minecraft.class_2960;
/*     */ import net.minecraft.class_310;
/*     */ import net.minecraft.class_3414;
/*     */ import net.minecraft.class_3417;
/*     */ import net.minecraft.class_3419;
/*     */ import net.minecraft.class_638;
/*     */ import net.minecraft.class_7923;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Environment(EnvType.CLIENT)
/*     */ public final class GoCatchThrowEffectsHandler
/*     */ {
/*     */   private static class_3414 ballThrowSound;
/*     */   
/*     */   public static void registerSounds() {
/*  32 */     class_2960 soundId = class_2960.method_60655("atlas", "ball_throw");
/*  33 */     ballThrowSound = (class_3414)class_2378.method_10230(class_7923.field_41172, soundId, class_3414.method_47908(soundId));
/*     */   }
/*     */   public static void playThrowEffects(int pokemonEntityId, GoCatchThrowQuality quality) {
/*     */     PokemonEntity pokemon;
/*  37 */     class_310 client = class_310.method_1551();
/*  38 */     if (client.field_1687 == null || client.field_1724 == null)
/*     */       return; 
/*  40 */     class_1297 entity = client.field_1687.method_8469(pokemonEntityId);
/*  41 */     if (entity instanceof PokemonEntity) { pokemon = (PokemonEntity)entity; }
/*     */     else { return; }
/*  43 */      class_243 pos = pokemon.method_19538().method_1031(0.0D, pokemon.method_17682() * 0.5D, 0.0D);
/*  44 */     playSound(client, quality);
/*  45 */     spawnParticles(client.field_1687, pos, quality);
/*     */   }
/*     */   
/*     */   private static void playSound(class_310 client, GoCatchThrowQuality quality) {
/*  49 */     if (client.field_1724 == null || client.field_1687 == null)
/*  50 */       return;  class_3414 sound = (ballThrowSound != null) ? ballThrowSound : class_3417.field_14709;
/*  51 */     float volume = (ballThrowSound != null) ? 1.0F : 0.5F;
/*  52 */     client.field_1687.method_8486(client.field_1724.method_23317(), client.field_1724.method_23318(), client.field_1724.method_23321(), sound, class_3419.field_15248, volume, 
/*  53 */         getPitchForQuality(quality), false);
/*     */   }
/*     */   
/*     */   private static float getPitchForQuality(GoCatchThrowQuality quality) {
/*  57 */     switch (quality) { case NICE: case GREAT: case EXCELLENT:  }  return 
/*     */ 
/*     */ 
/*     */       
/*  61 */       1.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   private static void spawnParticles(class_638 world, class_243 pos, GoCatchThrowQuality quality) {
/*  66 */     switch (quality) { case NICE:
/*  67 */         spawnNiceParticles(world, pos); break;
/*  68 */       case GREAT: spawnGreatParticles(world, pos); break;
/*  69 */       case EXCELLENT: spawnExcellentParticles(world, pos);
/*     */         break; }
/*     */   
/*     */   }
/*     */   
/*     */   private static void spawnNiceParticles(class_638 world, class_243 pos) {
/*  75 */     for (int i = 0; i < 10; i++) {
/*  76 */       double ox = world.field_9229.method_43059() * 0.3D;
/*  77 */       double oy = world.field_9229.method_43059() * 0.3D;
/*  78 */       double oz = world.field_9229.method_43059() * 0.3D;
/*  79 */       world.method_8406((class_2394)class_2398.field_11211, pos.field_1352 + ox, pos.field_1351 + oy, pos.field_1350 + oz, 0.0D, 0.05D, 0.0D);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void spawnGreatParticles(class_638 world, class_243 pos) {
/*  84 */     for (int i = 0; i < 20; i++) {
/*  85 */       double angle = 6.283185307179586D * i / 20.0D;
/*  86 */       double ox = Math.cos(angle) * 0.8D;
/*  87 */       double oz = Math.sin(angle) * 0.8D;
/*  88 */       world.method_8406((class_2394)class_2398.field_11207, pos.field_1352 + ox, pos.field_1351 + world.field_9229.method_43059() * 0.2D, pos.field_1350 + oz, ox * 0.1D, 0.05D, oz * 0.1D);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static void spawnExcellentParticles(class_638 world, class_243 pos) {
/*  94 */     for (int ring = 0; ring < 3; ring++) {
/*  95 */       double radius = 0.5D + ring * 0.4D;
/*  96 */       int count = 15 + ring * 5;
/*  97 */       for (int j = 0; j < count; j++) {
/*  98 */         double angle = 6.283185307179586D * j / count;
/*  99 */         double ox = Math.cos(angle) * radius;
/* 100 */         double oz = Math.sin(angle) * radius;
/* 101 */         world.method_8406((class_2394)class_2398.field_11220, pos.field_1352 + ox, pos.field_1351 + world.field_9229
/* 102 */             .method_43059() * 0.1D, pos.field_1350 + oz, ox * 0.15D, 0.1D, oz * 0.15D);
/*     */       } 
/*     */     } 
/* 105 */     for (int i = 0; i < 15; i++) {
/* 106 */       double ox = world.field_9229.method_43059() * 0.5D;
/* 107 */       double oy = world.field_9229.method_43059() * 0.5D;
/* 108 */       double oz = world.field_9229.method_43059() * 0.5D;
/* 109 */       world.method_8406((class_2394)class_2398.field_11207, pos.field_1352 + ox, pos.field_1351 + oy, pos.field_1350 + oz, 0.0D, 0.1D, 0.0D);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static class_243 resolveReleaseOrigin(class_1657 player) {
/* 114 */     class_243 eyePos = player.method_33571();
/* 115 */     class_243 forward = player.method_5828(1.0F).method_1029();
/* 116 */     class_243 side = resolveSideVector(forward);
/* 117 */     double handOffset = (player.method_6068() == class_1306.field_6183) ? 0.28D : -0.28D;
/* 118 */     return eyePos.method_1019(forward.method_1021(0.85D)).method_1019(side.method_1021(handOffset)).method_1031(0.0D, -0.28D, 0.0D);
/*     */   }
/*     */   
/*     */   private static class_243 resolveSideVector(class_243 forward) {
/* 122 */     class_243 side = forward.method_1036(new class_243(0.0D, 1.0D, 0.0D));
/* 123 */     if (side.method_1027() < 1.0E-6D) return new class_243(1.0D, 0.0D, 0.0D); 
/* 124 */     return side.method_1029();
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\gocatch\client\GoCatchThrowEffectsHandler.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */