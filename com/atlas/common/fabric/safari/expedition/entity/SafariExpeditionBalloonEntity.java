/*     */ package com.atlas.common.fabric.safari.expedition.entity;
/*     */ import com.atlas.common.fabric.safari.expedition.SafariExpeditionModels;
/*     */ import net.minecraft.class_1299;
/*     */ import net.minecraft.class_1308;
/*     */ import net.minecraft.class_1314;
/*     */ import net.minecraft.class_1335;
/*     */ import net.minecraft.class_1937;
/*     */ import net.minecraft.class_2487;
/*     */ import net.minecraft.class_2561;
/*     */ import net.minecraft.class_2940;
/*     */ import net.minecraft.class_2943;
/*     */ import net.minecraft.class_2945;
/*     */ import net.minecraft.class_3532;
/*     */ import net.minecraft.class_4048;
/*     */ import net.minecraft.class_4050;
/*     */ import net.minecraft.class_5132;
/*     */ import net.minecraft.class_5134;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import software.bernie.geckolib.animatable.GeoAnimatable;
/*     */ import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
/*     */ import software.bernie.geckolib.animation.AnimatableManager;
/*     */ import software.bernie.geckolib.util.GeckoLibUtil;
/*     */ 
/*     */ public final class SafariExpeditionBalloonEntity extends class_1314 implements GeoEntity {
/*  25 */   private static final class_2940<String> VARIANT_KEY = class_2945.method_12791(SafariExpeditionBalloonEntity.class, class_2943.field_13326);
/*  26 */   private static final class_2940<String> DISPLAY_NAME = class_2945.method_12791(SafariExpeditionBalloonEntity.class, class_2943.field_13326);
/*  27 */   private static final class_2940<Integer> TIER = class_2945.method_12791(SafariExpeditionBalloonEntity.class, class_2943.field_13327);
/*  28 */   private static final class_2940<Float> VISUAL_SCALE = class_2945.method_12791(SafariExpeditionBalloonEntity.class, class_2943.field_13320);
/*     */   
/*  30 */   private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache((GeoAnimatable)this);
/*     */   
/*     */   public SafariExpeditionBalloonEntity(class_1299<? extends class_1314> entityType, class_1937 world) {
/*  33 */     super(entityType, world);
/*  34 */     this.field_6207 = new class_1335((class_1308)this);
/*  35 */     method_5875(true);
/*  36 */     this.field_5960 = true;
/*     */   }
/*     */   
/*     */   public static class_5132.class_5133 createAttributes() {
/*  40 */     return method_26828()
/*  41 */       .method_26868(class_5134.field_23716, 1.0D)
/*  42 */       .method_26868(class_5134.field_23719, 0.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void method_5693(class_2945.class_9222 builder) {
/*  47 */     super.method_5693(builder);
/*  48 */     builder.method_56912(VARIANT_KEY, "jungle/bulbasaur");
/*  49 */     builder.method_56912(DISPLAY_NAME, "Bulbasaur Balloon");
/*  50 */     builder.method_56912(TIER, Integer.valueOf(SafariExpeditionModels.ExpeditionTier.COMMON.ordinal()));
/*  51 */     builder.method_56912(VISUAL_SCALE, Float.valueOf(1.0F));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void method_5959() {}
/*     */ 
/*     */   
/*     */   public void method_5773() {
/*  60 */     super.method_5773();
/*  61 */     method_18800(0.0D, 0.0D, 0.0D);
/*  62 */     method_5875(true);
/*  63 */     this.field_6017 = 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void applyVariant(@NotNull String variantKey, @NotNull String displayName, @NotNull SafariExpeditionModels.ExpeditionTier tier, float scale) {
/*  70 */     this.field_6011.method_12778(VARIANT_KEY, variantKey);
/*  71 */     this.field_6011.method_12778(DISPLAY_NAME, displayName);
/*  72 */     this.field_6011.method_12778(TIER, Integer.valueOf(tier.ordinal()));
/*  73 */     this.field_6011.method_12778(VISUAL_SCALE, Float.valueOf(Math.max(0.25F, scale)));
/*  74 */     method_5665((class_2561)class_2561.method_43470(displayName));
/*  75 */     method_5880(false);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public String getVariantKey() {
/*  80 */     return (String)this.field_6011.method_12789(VARIANT_KEY);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public String getDisplayNameValue() {
/*  85 */     return (String)this.field_6011.method_12789(DISPLAY_NAME);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public SafariExpeditionModels.ExpeditionTier getTier() {
/*  90 */     int ordinal = class_3532.method_15340(((Integer)this.field_6011.method_12789(TIER)).intValue(), 0, (SafariExpeditionModels.ExpeditionTier.values()).length - 1);
/*  91 */     return SafariExpeditionModels.ExpeditionTier.values()[ordinal];
/*     */   }
/*     */   
/*     */   public float getVisualScale() {
/*  95 */     return ((Float)this.field_6011.method_12789(VISUAL_SCALE)).floatValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public class_4048 method_18377(class_4050 pose) {
/* 100 */     return super.method_18377(pose).method_18383(getVisualScale());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {}
/*     */ 
/*     */   
/*     */   public AnimatableInstanceCache getAnimatableInstanceCache() {
/* 109 */     return this.cache;
/*     */   }
/*     */ 
/*     */   
/*     */   public void method_5749(class_2487 nbt) {
/* 114 */     if (nbt.method_10545("VariantKey")) this.field_6011.method_12778(VARIANT_KEY, nbt.method_10558("VariantKey")); 
/* 115 */     if (nbt.method_10545("DisplayName")) this.field_6011.method_12778(DISPLAY_NAME, nbt.method_10558("DisplayName")); 
/* 116 */     if (nbt.method_10545("Tier")) this.field_6011.method_12778(TIER, Integer.valueOf(nbt.method_10550("Tier"))); 
/* 117 */     if (nbt.method_10545("VisualScale")) this.field_6011.method_12778(VISUAL_SCALE, Float.valueOf(nbt.method_10583("VisualScale")));
/*     */   
/*     */   }
/*     */   
/*     */   public void method_5652(class_2487 nbt) {
/* 122 */     nbt.method_10582("VariantKey", getVariantKey());
/* 123 */     nbt.method_10582("DisplayName", getDisplayNameValue());
/* 124 */     nbt.method_10569("Tier", getTier().ordinal());
/* 125 */     nbt.method_10548("VisualScale", getVisualScale());
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\safari\expedition\entity\SafariExpeditionBalloonEntity.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */