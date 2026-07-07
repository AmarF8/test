/*     */ package com.atlas.common.fabric.safari.fishing.entity;
/*     */ import com.atlas.common.fabric.safari.fishing.SafariCatchProfile;
/*     */ import com.atlas.common.fabric.safari.fishing.SafariFishRarity;
/*     */ import net.minecraft.class_1299;
/*     */ import net.minecraft.class_1308;
/*     */ import net.minecraft.class_1313;
/*     */ import net.minecraft.class_1314;
/*     */ import net.minecraft.class_1335;
/*     */ import net.minecraft.class_1937;
/*     */ import net.minecraft.class_243;
/*     */ import net.minecraft.class_2487;
/*     */ import net.minecraft.class_2561;
/*     */ import net.minecraft.class_2940;
/*     */ import net.minecraft.class_2943;
/*     */ import net.minecraft.class_2945;
/*     */ import net.minecraft.class_3417;
/*     */ import net.minecraft.class_3532;
/*     */ import net.minecraft.class_4048;
/*     */ import net.minecraft.class_4050;
/*     */ import net.minecraft.class_5132;
/*     */ import net.minecraft.class_5134;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import software.bernie.geckolib.animatable.GeoAnimatable;
/*     */ import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
/*     */ import software.bernie.geckolib.animation.AnimatableManager;
/*     */ import software.bernie.geckolib.animation.AnimationController;
/*     */ import software.bernie.geckolib.animation.AnimationState;
/*     */ import software.bernie.geckolib.animation.PlayState;
/*     */ import software.bernie.geckolib.animation.RawAnimation;
/*     */ import software.bernie.geckolib.util.GeckoLibUtil;
/*     */ 
/*     */ public class SafariFishEntity extends class_1314 implements GeoEntity {
/*  33 */   private static final class_2940<String> FISH_ID = class_2945.method_12791(SafariFishEntity.class, class_2943.field_13326); private static final float PREVIEW_SCALE_MULTIPLIER = 1.35F;
/*  34 */   private static final class_2940<String> VARIANT_KEY = class_2945.method_12791(SafariFishEntity.class, class_2943.field_13326);
/*  35 */   private static final class_2940<String> DISPLAY_NAME = class_2945.method_12791(SafariFishEntity.class, class_2943.field_13326);
/*  36 */   private static final class_2940<Float> SIZE_SCALE = class_2945.method_12791(SafariFishEntity.class, class_2943.field_13320);
/*  37 */   private static final class_2940<Float> WEIGHT_KG = class_2945.method_12791(SafariFishEntity.class, class_2943.field_13320);
/*  38 */   private static final class_2940<Integer> RARITY = class_2945.method_12791(SafariFishEntity.class, class_2943.field_13327);
/*     */   
/*  40 */   private static final RawAnimation IDLE_ANIMATION = RawAnimation.begin().thenLoop("idle");
/*  41 */   private static final RawAnimation SWIM_ANIMATION = RawAnimation.begin().thenLoop("walk");
/*     */   
/*  43 */   private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache((GeoAnimatable)this);
/*  44 */   private class_243 swimTarget = class_243.field_1353;
/*     */   private int swimRetargetTicks;
/*     */   
/*     */   public SafariFishEntity(class_1299<? extends class_1314> entityType, class_1937 world) {
/*  48 */     super(entityType, world);
/*  49 */     this.field_6207 = new class_1335((class_1308)this);
/*     */   }
/*     */   
/*     */   public static class_5132.class_5133 createAttributes() {
/*  53 */     return method_26828()
/*  54 */       .method_26868(class_5134.field_23716, 12.0D)
/*  55 */       .method_26868(class_5134.field_23719, 0.18D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void method_5693(class_2945.class_9222 builder) {
/*  60 */     super.method_5693(builder);
/*  61 */     builder.method_56912(FISH_ID, "anglerfish");
/*  62 */     builder.method_56912(VARIANT_KEY, "anglerfish/black_angler_fish");
/*  63 */     builder.method_56912(DISPLAY_NAME, "Black Angler Fish");
/*  64 */     builder.method_56912(SIZE_SCALE, Float.valueOf(1.0F));
/*  65 */     builder.method_56912(WEIGHT_KG, Float.valueOf(12.0F));
/*  66 */     builder.method_56912(RARITY, Integer.valueOf(SafariFishRarity.COMMON.ordinal()));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void method_5959() {}
/*     */ 
/*     */   
/*     */   public void method_5773() {
/*  75 */     super.method_5773();
/*     */     
/*  77 */     if ((method_37908()).field_9236)
/*     */       return; 
/*  79 */     if (method_5799()) {
/*  80 */       method_5855(method_5748());
/*  81 */       method_5875(true);
/*  82 */       tickSwimming();
/*     */     } else {
/*  84 */       method_5875(false);
/*  85 */       tickLandFlop();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void tickSwimming() {
/*  90 */     if (this.swimRetargetTicks-- <= 0 || this.swimTarget.method_1027() < 0.001D) {
/*  91 */       this.swimRetargetTicks = 30 + this.field_5974.method_43048(45);
/*  92 */       this
/*     */ 
/*     */         
/*  95 */         .swimTarget = new class_243((this.field_5974.method_43058() - 0.5D) * 0.12D, (this.field_5974.method_43058() - 0.5D) * 0.05D, (this.field_5974.method_43058() - 0.5D) * 0.12D);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 101 */     class_243 velocity = method_18798().method_1019(this.swimTarget).method_18805(0.88D, 0.9D, 0.88D);
/* 102 */     method_18799(velocity);
/* 103 */     method_5784(class_1313.field_6308, velocity);
/*     */     
/* 105 */     if (velocity.method_37268() > 1.0E-4D) {
/* 106 */       float yaw = (float)(class_3532.method_15349(velocity.field_1350, velocity.field_1352) * 57.29577951308232D) - 90.0F;
/* 107 */       method_36456(yaw);
/* 108 */       method_5636(yaw);
/* 109 */       method_5847(yaw);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void tickLandFlop() {
/* 114 */     if (method_24828() && this.field_5974.method_43048(18) == 0) {
/* 115 */       method_18800((this.field_5974
/* 116 */           .method_43058() - 0.5D) * 0.25D, 0.22D, (this.field_5974
/*     */           
/* 118 */           .method_43058() - 0.5D) * 0.25D);
/*     */       
/* 120 */       method_5783(class_3417.field_14918, 0.45F, 0.95F + this.field_5974.method_43057() * 0.2F);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void applyCatchProfile(@NotNull SafariCatchProfile profile) {
/* 125 */     applyProfileData(profile, 1.0F);
/* 126 */     method_5665((class_2561)class_2561.method_43470(profile.getDisplayLabel() + " [" + profile.getDisplayLabel() + "] " + profile.rarity().getDisplayName()));
/* 127 */     method_5880(true);
/* 128 */     method_5834(false);
/* 129 */     method_5971();
/*     */   }
/*     */   
/*     */   public void applyPreviewProfile(@NotNull SafariCatchProfile profile) {
/* 133 */     applyProfileData(profile, 1.35F);
/* 134 */     method_5665(null);
/* 135 */     method_5880(false);
/* 136 */     method_5834(true);
/*     */   }
/*     */   
/*     */   private void applyProfileData(@NotNull SafariCatchProfile profile, float scaleMultiplier) {
/* 140 */     this.field_6011.method_12778(FISH_ID, profile.fishId());
/* 141 */     this.field_6011.method_12778(VARIANT_KEY, profile.variant().modelKey());
/* 142 */     this.field_6011.method_12778(DISPLAY_NAME, profile.getDisplayLabel());
/* 143 */     this.field_6011.method_12778(SIZE_SCALE, Float.valueOf((float)profile.sizeScale() * scaleMultiplier));
/* 144 */     this.field_6011.method_12778(WEIGHT_KG, Float.valueOf((float)profile.weightKg()));
/* 145 */     this.field_6011.method_12778(RARITY, Integer.valueOf(profile.rarity().ordinal()));
/*     */   }
/*     */   
/*     */   public String getVariantKey() {
/* 149 */     return (String)this.field_6011.method_12789(VARIANT_KEY);
/*     */   }
/*     */   
/*     */   public String getFishId() {
/* 153 */     return (String)this.field_6011.method_12789(FISH_ID);
/*     */   }
/*     */   
/*     */   public String getDisplayNameValue() {
/* 157 */     return (String)this.field_6011.method_12789(DISPLAY_NAME);
/*     */   }
/*     */   
/*     */   public float getSizeScale() {
/* 161 */     return ((Float)this.field_6011.method_12789(SIZE_SCALE)).floatValue();
/*     */   }
/*     */   
/*     */   public float getWeightKg() {
/* 165 */     return ((Float)this.field_6011.method_12789(WEIGHT_KG)).floatValue();
/*     */   }
/*     */   
/*     */   public SafariFishRarity getRarity() {
/* 169 */     int ordinal = class_3532.method_15340(((Integer)this.field_6011.method_12789(RARITY)).intValue(), 0, (SafariFishRarity.values()).length - 1);
/* 170 */     return SafariFishRarity.values()[ordinal];
/*     */   }
/*     */ 
/*     */   
/*     */   public class_4048 method_18377(class_4050 pose) {
/* 175 */     float scale = getSizeScale();
/* 176 */     return super.method_18377(pose).method_18383(scale);
/*     */   }
/*     */ 
/*     */   
/*     */   public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
/* 181 */     controllers.add(new AnimationController((GeoAnimatable)this, "swim_controller", 3, state -> {
/*     */             state.setAnimation(state.isMoving() ? SWIM_ANIMATION : IDLE_ANIMATION);
/*     */             return PlayState.CONTINUE;
/*     */           }));
/*     */   }
/*     */ 
/*     */   
/*     */   public AnimatableInstanceCache getAnimatableInstanceCache() {
/* 189 */     return this.cache;
/*     */   }
/*     */ 
/*     */   
/*     */   public void method_5749(class_2487 nbt) {
/* 194 */     if (nbt.method_10545("FishId")) this.field_6011.method_12778(FISH_ID, nbt.method_10558("FishId")); 
/* 195 */     if (nbt.method_10545("VariantKey")) this.field_6011.method_12778(VARIANT_KEY, nbt.method_10558("VariantKey")); 
/* 196 */     if (nbt.method_10545("DisplayName")) this.field_6011.method_12778(DISPLAY_NAME, nbt.method_10558("DisplayName")); 
/* 197 */     if (nbt.method_10545("SizeScale")) this.field_6011.method_12778(SIZE_SCALE, Float.valueOf(nbt.method_10583("SizeScale"))); 
/* 198 */     if (nbt.method_10545("WeightKg")) this.field_6011.method_12778(WEIGHT_KG, Float.valueOf(nbt.method_10583("WeightKg"))); 
/* 199 */     if (nbt.method_10545("Rarity")) this.field_6011.method_12778(RARITY, Integer.valueOf(nbt.method_10550("Rarity")));
/*     */   
/*     */   }
/*     */   
/*     */   public void method_5652(class_2487 nbt) {
/* 204 */     nbt.method_10582("FishId", getFishId());
/* 205 */     nbt.method_10582("VariantKey", getVariantKey());
/* 206 */     nbt.method_10582("DisplayName", getDisplayNameValue());
/* 207 */     nbt.method_10548("SizeScale", getSizeScale());
/* 208 */     nbt.method_10548("WeightKg", getWeightKg());
/* 209 */     nbt.method_10569("Rarity", getRarity().ordinal());
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\safari\fishing\entity\SafariFishEntity.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */