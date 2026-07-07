/*    */ package com.atlas.common.fabric.rustlingspots.particle;
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
/*    */ public final class RustlingParticleProviderRegistrar
/*    */ {
/*    */   public static void register() {
/* 16 */     ParticleFactoryRegistry registry = ParticleFactoryRegistry.getInstance();
/*    */     
/* 18 */     registry.register((class_2396)RustlingParticleTypes.GRASS_BURST, spriteSet -> RustlingBurstParticle.provider((class_4002)spriteSet, new RustlingBurstParticle.Style(1.25F, 0.15F, 14, 20, 0.78F, 1.05F, 0.0025F), "atlas:grass_burst"));
/*    */     
/* 20 */     registry.register((class_2396)RustlingParticleTypes.SAND_BURST, spriteSet -> RustlingBurstParticle.provider((class_4002)spriteSet, new RustlingBurstParticle.Style(1.18F, 0.18F, 14, 20, 0.8F, -0.0012F, 0.0025F), "atlas:sand_burst"));
/*    */     
/* 22 */     registry.register((class_2396)RustlingParticleTypes.WATER_BURST, spriteSet -> RustlingBurstParticle.provider((class_4002)spriteSet, new RustlingBurstParticle.Style(0.95F, 0.25F, 6, 17, 0.9F, 0.8F, 0.02F), "atlas:water_burst"));
/*    */     
/* 24 */     registry.register((class_2396)RustlingParticleTypes.SNOW_BURST, spriteSet -> RustlingBurstParticle.provider((class_4002)spriteSet, new RustlingBurstParticle.Style(1.18F, 0.18F, 14, 20, 0.8F, -0.0012F, 0.0025F), "atlas:snow_burst"));
/*    */     
/* 26 */     registry.register((class_2396)RustlingParticleTypes.LEAVES_BURST, spriteSet -> RustlingBurstParticle.provider((class_4002)spriteSet, new RustlingBurstParticle.Style(1.18F, 0.18F, 14, 20, 0.8F, 1.05F, 0.0025F), "atlas:leaves_burst"));
/*    */     
/* 28 */     registry.register((class_2396)RustlingParticleTypes.CAVE_BURST, spriteSet -> RustlingBurstParticle.provider((class_4002)spriteSet, new RustlingBurstParticle.Style(1.18F, 0.18F, 14, 20, 0.8F, 1.05F, 0.0025F), "atlas:cave_burst"));
/*    */     
/* 30 */     registry.register((class_2396)RustlingParticleTypes.NETHERFLAMME_BURST, spriteSet -> RustlingBurstParticle.provider((class_4002)spriteSet, new RustlingBurstParticle.Style(1.18F, 0.18F, 14, 20, 0.8F, -0.0012F, 0.0025F), "atlas:netherflamme_burst"));
/*    */     
/* 32 */     registry.register((class_2396)RustlingParticleTypes.SOULFLAME_BURST, spriteSet -> RustlingBurstParticle.provider((class_4002)spriteSet, new RustlingBurstParticle.Style(1.18F, 0.18F, 14, 20, 0.8F, -0.0012F, 0.0025F), "atlas:soulflame_burst"));
/*    */     
/* 34 */     registry.register((class_2396)RustlingParticleTypes.FLYING_BURST, spriteSet -> RustlingBurstParticle.provider((class_4002)spriteSet, new RustlingBurstParticle.Style(0.78F, 0.18F, 16, 34, 0.91F, 0.15F, 0.011F), "atlas:flying_burst"));
/*    */     
/* 36 */     registry.register((class_2396)RustlingParticleTypes.SHINY_SPARKLE_ONE, spriteSet -> RustlingBurstParticle.provider((class_4002)spriteSet, new RustlingBurstParticle.Style(0.66F, 0.08F, 10, 16, 0.88F, -0.01F, 0.001F), "atlas:shiny_sparkle_one"));
/*    */     
/* 38 */     registry.register((class_2396)RustlingParticleTypes.SHINY_SPARKLE_TWO, spriteSet -> RustlingBurstParticle.provider((class_4002)spriteSet, new RustlingBurstParticle.Style(0.9F, 0.1F, 12, 18, 0.9F, -0.012F, 0.001F), "atlas:shiny_sparkle_two"));
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\rustlingspots\particle\RustlingParticleProviderRegistrar.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */