/*    */ package com.atlas.common.fabric.gocatch.particle;
/*    */ 
/*    */ import net.fabricmc.api.EnvType;
/*    */ import net.fabricmc.api.Environment;
/*    */ import net.fabricmc.fabric.api.client.particle.v1.FabricSpriteProvider;
/*    */ import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
/*    */ import net.minecraft.class_2396;
/*    */ import net.minecraft.class_4002;
/*    */ import net.minecraft.class_707;
/*    */ 
/*    */ 
/*    */ @Environment(EnvType.CLIENT)
/*    */ public final class GoCatchClientParticles
/*    */ {
/*    */   public static void registerFactories() {
/* 16 */     ParticleFactoryRegistry registry = ParticleFactoryRegistry.getInstance();
/* 17 */     registry.register((class_2396)GoCatchParticleTypes.LOW_SPARKLE, provider -> new GoCatchSparkleParticle.Factory((class_4002)provider, 0.12F, 10, 0.82F, 0.88F));
/*    */     
/* 19 */     registry.register((class_2396)GoCatchParticleTypes.MEDIUM_SPARKLE, provider -> new GoCatchSparkleParticle.Factory((class_4002)provider, 0.18F, 12, 0.86F, 0.94F));
/*    */     
/* 21 */     registry.register((class_2396)GoCatchParticleTypes.FULL_SPARKLE, provider -> new GoCatchSparkleParticle.Factory((class_4002)provider, 0.26F, 13, 0.9F, 1.0F));
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\gocatch\particle\GoCatchClientParticles.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */