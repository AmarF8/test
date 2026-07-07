/*    */ package com.atlas.common.fabric.gocatch.particle;
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
/*    */ public final class GoCatchParticleTypes
/*    */ {
/*    */   public static class_2400 LOW_SPARKLE;
/*    */   public static class_2400 MEDIUM_SPARKLE;
/*    */   public static class_2400 FULL_SPARKLE;
/*    */   
/*    */   public static void register() {
/* 23 */     LOW_SPARKLE = reg("low_sparkle");
/* 24 */     MEDIUM_SPARKLE = reg("medium_sparkle");
/* 25 */     FULL_SPARKLE = reg("full_sparkle");
/*    */   }
/*    */   
/*    */   @Environment(EnvType.CLIENT)
/*    */   public static void registerClient() {
/* 30 */     GoCatchClientParticles.registerFactories();
/*    */   }
/*    */   
/*    */   private static class_2400 reg(String path) {
/* 34 */     return (class_2400)class_2378.method_10230(class_7923.field_41180, class_2960.method_60655("atlas", path), FabricParticleTypes.simple());
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\gocatch\particle\GoCatchParticleTypes.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */