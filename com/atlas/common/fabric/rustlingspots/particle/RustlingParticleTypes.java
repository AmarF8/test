/*    */ package com.atlas.common.fabric.rustlingspots.particle;
/*    */ 
/*    */ import net.fabricmc.api.EnvType;
/*    */ import net.fabricmc.api.Environment;
/*    */ import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
/*    */ import net.minecraft.class_2378;
/*    */ import net.minecraft.class_2400;
/*    */ import net.minecraft.class_2960;
/*    */ import net.minecraft.class_7923;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class RustlingParticleTypes
/*    */ {
/*    */   public static class_2400 GRASS_BURST;
/*    */   public static class_2400 SAND_BURST;
/*    */   public static class_2400 WATER_BURST;
/*    */   public static class_2400 SNOW_BURST;
/*    */   public static class_2400 LEAVES_BURST;
/*    */   public static class_2400 CAVE_BURST;
/*    */   public static class_2400 NETHERFLAMME_BURST;
/*    */   public static class_2400 SOULFLAME_BURST;
/*    */   public static class_2400 FLYING_BURST;
/*    */   public static class_2400 SHINY_SPARKLE_ONE;
/*    */   public static class_2400 SHINY_SPARKLE_TWO;
/*    */   
/*    */   public static void register() {
/* 34 */     GRASS_BURST = reg("grass_burst");
/* 35 */     SAND_BURST = reg("sand_burst");
/* 36 */     WATER_BURST = reg("water_burst");
/* 37 */     SNOW_BURST = reg("snow_burst");
/* 38 */     LEAVES_BURST = reg("leaves_burst");
/* 39 */     CAVE_BURST = reg("cave_burst");
/* 40 */     NETHERFLAMME_BURST = reg("netherflamme_burst");
/* 41 */     SOULFLAME_BURST = reg("soulflame_burst");
/* 42 */     FLYING_BURST = reg("flying_burst");
/* 43 */     SHINY_SPARKLE_ONE = reg("shiny_sparkle_one");
/* 44 */     SHINY_SPARKLE_TWO = reg("shiny_sparkle_two");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Environment(EnvType.CLIENT)
/*    */   public static void registerClient() {
/* 52 */     RustlingParticleProviderRegistrar.register();
/*    */   }
/*    */   
/*    */   private static class_2400 reg(String path) {
/* 56 */     return (class_2400)class_2378.method_10230(class_7923.field_41180, class_2960.method_60655("atlas", path), FabricParticleTypes.simple(false));
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\rustlingspots\particle\RustlingParticleTypes.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */