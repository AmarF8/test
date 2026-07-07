/*    */ package com.atlas.common.fabric.block.station.entity;
/*    */ import com.atlas.common.fabric.block.AtlasBlockRegistry;
/*    */ import net.minecraft.class_2338;
/*    */ import net.minecraft.class_2591;
/*    */ import net.minecraft.class_2680;
/*    */ import net.minecraft.class_7923;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import software.bernie.geckolib.animatable.GeoAnimatable;
/*    */ import software.bernie.geckolib.animation.AnimatableManager;
/*    */ import software.bernie.geckolib.animation.AnimationController;
/*    */ import software.bernie.geckolib.animation.AnimationState;
/*    */ import software.bernie.geckolib.animation.PlayState;
/*    */ import software.bernie.geckolib.animation.RawAnimation;
/*    */ 
/*    */ public final class GenericStationBlockEntity extends StationBlockEntity {
/*    */   public GenericStationBlockEntity(@NotNull class_2338 pos, @NotNull class_2680 state) {
/* 17 */     super(getEntityTypeForBlock(state), pos, state);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public GenericStationBlockEntity(@NotNull class_2591<? extends StationBlockEntity> type, @NotNull class_2338 pos, @NotNull class_2680 state) {
/* 23 */     super(type, pos, state);
/*    */   }
/*    */   
/*    */   private static class_2591<? extends StationBlockEntity> getEntityTypeForBlock(class_2680 state) {
/* 27 */     if (state.method_26204() instanceof com.atlas.common.fabric.block.station.block.MultiBlockStationBlock) {
/* 28 */       return AtlasBlockRegistry.MULTIBLOCK_STATION_BLOCK_ENTITY_TYPE;
/*    */     }
/* 30 */     return AtlasBlockRegistry.GENERIC_STATION_BLOCK_ENTITY_TYPE;
/*    */   }
/*    */ 
/*    */   
/*    */   public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
/* 35 */     String id = class_7923.field_41175.method_10221(method_11010().method_26204()).method_12832();
/* 36 */     controllers.add(new AnimationController((GeoAnimatable)this, "controller", 0, state -> state.setAndContinue(RawAnimation.begin().thenLoop("idle"))));
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\block\station\entity\GenericStationBlockEntity.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */