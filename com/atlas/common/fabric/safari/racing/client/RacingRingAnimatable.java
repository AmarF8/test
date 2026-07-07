/*    */ package com.atlas.common.fabric.safari.racing.client;
/*    */ 
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import software.bernie.geckolib.animatable.GeoAnimatable;
/*    */ import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
/*    */ import software.bernie.geckolib.animation.AnimatableManager;
/*    */ import software.bernie.geckolib.util.GeckoLibUtil;
/*    */ 
/*    */ 
/*    */ public final class RacingRingAnimatable
/*    */   implements GeoAnimatable
/*    */ {
/*    */   public static final double MODEL_RADIUS_BLOCKS = 2.042185625D;
/* 14 */   private static final RacingRingAnimatable YELLOW = new RacingRingAnimatable("yellow_ring");
/* 15 */   private static final RacingRingAnimatable GREEN = new RacingRingAnimatable("green_ring");
/*    */   
/*    */   @NotNull
/* 18 */   private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this); @NotNull
/*    */   private final String variantKey;
/*    */   private RacingRingAnimatable(@NotNull String variantKey) {
/* 21 */     this.variantKey = variantKey;
/*    */   }
/*    */   @NotNull
/*    */   public static RacingRingAnimatable yellow() {
/* 25 */     return YELLOW;
/*    */   }
/*    */   @NotNull
/*    */   public static RacingRingAnimatable green() {
/* 29 */     return GREEN;
/*    */   }
/*    */   @NotNull
/*    */   public String getVariantKey() {
/* 33 */     return this.variantKey;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {}
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public AnimatableInstanceCache getAnimatableInstanceCache() {
/* 43 */     return this.cache;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getTick(Object object) {
/* 48 */     return 0.0D;
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\safari\racing\client\RacingRingAnimatable.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */