/*    */ package com.atlas.common.fabric.block.animated.entity;
/*    */ 
/*    */ import net.minecraft.class_2338;
/*    */ import net.minecraft.class_2586;
/*    */ import net.minecraft.class_2591;
/*    */ import net.minecraft.class_2680;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import software.bernie.geckolib.animatable.GeoAnimatable;
/*    */ import software.bernie.geckolib.animatable.GeoBlockEntity;
/*    */ import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
/*    */ import software.bernie.geckolib.animation.AnimatableManager;
/*    */ import software.bernie.geckolib.util.GeckoLibUtil;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AnimatedBlockEntity
/*    */   extends class_2586
/*    */   implements GeoBlockEntity
/*    */ {
/* 20 */   private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache((GeoAnimatable)this);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public AnimatedBlockEntity(@NotNull class_2591<? extends AnimatedBlockEntity> blockEntityType, @NotNull class_2338 pos, @NotNull class_2680 state) {
/* 33 */     super(blockEntityType, pos, state);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void registerControllers(@NotNull AnimatableManager.ControllerRegistrar controllers) {}
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public AnimatableInstanceCache getAnimatableInstanceCache() {
/* 48 */     return this.cache;
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\block\animated\entity\AnimatedBlockEntity.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */