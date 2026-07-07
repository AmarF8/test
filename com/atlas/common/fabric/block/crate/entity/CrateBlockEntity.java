/*    */ package com.atlas.common.fabric.block.crate.entity;
/*    */ 
/*    */ import com.atlas.common.fabric.block.animated.entity.AnimatedBlockEntity;
/*    */ import net.minecraft.class_2338;
/*    */ import net.minecraft.class_2591;
/*    */ import net.minecraft.class_2680;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import software.bernie.geckolib.animatable.GeoAnimatable;
/*    */ import software.bernie.geckolib.animation.AnimatableManager;
/*    */ import software.bernie.geckolib.animation.Animation;
/*    */ import software.bernie.geckolib.animation.AnimationController;
/*    */ import software.bernie.geckolib.animation.AnimationState;
/*    */ import software.bernie.geckolib.animation.PlayState;
/*    */ import software.bernie.geckolib.animation.RawAnimation;
/*    */ 
/*    */ 
/*    */ public abstract class CrateBlockEntity
/*    */   extends AnimatedBlockEntity
/*    */ {
/*    */   @NotNull
/* 21 */   public static final RawAnimation IDLE_ANIMATION = RawAnimation.begin().thenLoop("idle"); @NotNull
/* 22 */   public static final RawAnimation SPIN_ANIMATION = RawAnimation.begin().then("open", Animation.LoopType.PLAY_ONCE);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private AnimationController<GeoAnimatable> controller;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public CrateBlockEntity(@NotNull class_2591<? extends AnimatedBlockEntity> blockEntityType, @NotNull class_2338 pos, @NotNull class_2680 state) {
/* 36 */     super(blockEntityType, pos, state);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public final void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
/* 44 */     this.controller = new AnimationController((GeoAnimatable)this, "controller", 0, state -> state.setAndContinue(IDLE_ANIMATION));
/* 45 */     this.controller.triggerableAnim("open", SPIN_ANIMATION);
/* 46 */     controllers.add(this.controller);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public final void open() {
/* 53 */     if (this.controller.isPlayingTriggeredAnimation()) this.controller.forceAnimationReset(); 
/* 54 */     this.controller.tryTriggerAnimation("open");
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\block\crate\entity\CrateBlockEntity.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */