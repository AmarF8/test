/*    */ package com.atlas.common.fabric.item.armor;
/*    */ import java.util.List;
/*    */ import java.util.function.Consumer;
/*    */ import net.minecraft.class_1304;
/*    */ import net.minecraft.class_1309;
/*    */ import net.minecraft.class_1738;
/*    */ import net.minecraft.class_1740;
/*    */ import net.minecraft.class_1792;
/*    */ import net.minecraft.class_1799;
/*    */ import net.minecraft.class_2561;
/*    */ import net.minecraft.class_572;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ import software.bernie.geckolib.animatable.GeoAnimatable;
/*    */ import software.bernie.geckolib.animatable.client.GeoRenderProvider;
/*    */ import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
/*    */ import software.bernie.geckolib.animation.AnimatableManager;
/*    */ import software.bernie.geckolib.animation.AnimationController;
/*    */ import software.bernie.geckolib.animation.AnimationState;
/*    */ import software.bernie.geckolib.animation.PlayState;
/*    */ import software.bernie.geckolib.animation.RawAnimation;
/*    */ import software.bernie.geckolib.util.GeckoLibUtil;
/*    */ 
/*    */ public class PokeArmorItem extends class_1738 implements GeoItem {
/* 25 */   private static final RawAnimation IDLE = RawAnimation.begin().thenLoop("idle");
/*    */   @NotNull
/*    */   private final PokeArmorSet armorSet;
/* 28 */   private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache((GeoAnimatable)this);
/*    */   
/*    */   public PokeArmorItem(@NotNull PokeArmorSet armorSet, @NotNull class_1738.class_8051 type, @NotNull class_1792.class_1793 settings) {
/* 31 */     super(class_1740.field_21977, type, settings);
/* 32 */     this.armorSet = armorSet;
/*    */   }
/*    */   @NotNull
/*    */   public PokeArmorSet armorSet() {
/* 36 */     return this.armorSet;
/*    */   }
/*    */   
/*    */   public static boolean hasFullSet(@NotNull class_1309 entity, @NotNull PokeArmorSet armorSet) {
/* 40 */     return (hasSetPiece(entity, class_1304.field_6169, armorSet) && 
/* 41 */       hasSetPiece(entity, class_1304.field_6174, armorSet) && 
/* 42 */       hasSetPiece(entity, class_1304.field_6172, armorSet) && 
/* 43 */       hasSetPiece(entity, class_1304.field_6166, armorSet));
/*    */   }
/*    */ 
/*    */   
/*    */   public void createGeoRenderer(@NotNull Consumer<GeoRenderProvider> consumer) {
/* 48 */     consumer.accept(new GeoRenderProvider()
/*    */         {
/*    */           private PokeArmorRenderer renderer;
/*    */ 
/*    */ 
/*    */ 
/*    */           
/*    */           public <T extends class_1309> class_572<?> getGeoArmorRenderer(@Nullable T livingEntity, @NotNull class_1799 itemStack, @Nullable class_1304 equipmentSlot, @Nullable class_572<T> original) {
/* 56 */             if (this.renderer == null) {
/* 57 */               this.renderer = new PokeArmorRenderer(PokeArmorItem.this.armorSet);
/*    */             }
/* 59 */             return (class_572<?>)this.renderer;
/*    */           }
/*    */         });
/*    */   }
/*    */ 
/*    */   
/*    */   public void registerControllers(@NotNull AnimatableManager.ControllerRegistrar controllers) {
/* 66 */     controllers.add(new AnimationController((GeoAnimatable)this, 0, state -> state.setAndContinue(IDLE)));
/*    */   }
/*    */ 
/*    */   
/*    */   public AnimatableInstanceCache getAnimatableInstanceCache() {
/* 71 */     return this.cache;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void method_7851(@NotNull class_1799 stack, @NotNull class_1792.class_9635 context, @NotNull List<class_2561> tooltip, @NotNull class_1836 type) {
/* 79 */     tooltip.addAll(PokeArmorLore.getLore(this.armorSet));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private static boolean hasSetPiece(@NotNull class_1309 entity, @NotNull class_1304 slot, @NotNull PokeArmorSet armorSet) {
/* 85 */     class_1792 class_1792 = entity.method_6118(slot).method_7909(); if (class_1792 instanceof PokeArmorItem) { PokeArmorItem item = (PokeArmorItem)class_1792; if (item.armorSet == armorSet); }  return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\item\armor\PokeArmorItem.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */