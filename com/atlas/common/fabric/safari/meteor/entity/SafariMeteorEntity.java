/*     */ package com.atlas.common.fabric.safari.meteor.entity;
/*     */ import com.atlas.common.fabric.safari.meteor.SafariMeteorType;
/*     */ import net.minecraft.class_1299;
/*     */ import net.minecraft.class_1314;
/*     */ import net.minecraft.class_1335;
/*     */ import net.minecraft.class_1937;
/*     */ import net.minecraft.class_2487;
/*     */ import net.minecraft.class_2561;
/*     */ import net.minecraft.class_2940;
/*     */ import net.minecraft.class_2943;
/*     */ import net.minecraft.class_2945;
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
/*     */ public final class SafariMeteorEntity extends class_1314 implements GeoEntity {
/*  27 */   private static final class_2940<String> METEOR_TYPE = class_2945.method_12791(SafariMeteorEntity.class, class_2943.field_13326);
/*  28 */   private static final class_2940<Integer> PHASE = class_2945.method_12791(SafariMeteorEntity.class, class_2943.field_13327);
/*  29 */   private static final class_2940<Float> VISUAL_SCALE = class_2945.method_12791(SafariMeteorEntity.class, class_2943.field_13320);
/*  30 */   private static final RawAnimation IDLE_ANIMATION = RawAnimation.begin().thenLoop("idle");
/*  31 */   private static final RawAnimation IMPACT_ANIMATION = RawAnimation.begin().thenLoop("impact");
/*     */   
/*  33 */   private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache((GeoAnimatable)this);
/*     */   
/*     */   public SafariMeteorEntity(class_1299<? extends class_1314> entityType, class_1937 world) {
/*  36 */     super(entityType, world);
/*  37 */     this.field_6207 = new class_1335((class_1308)this);
/*  38 */     method_5875(true);
/*  39 */     this.field_5960 = true;
/*     */   }
/*     */   
/*     */   public static class_5132.class_5133 createAttributes() {
/*  43 */     return method_26828()
/*  44 */       .method_26868(class_5134.field_23716, 1.0D)
/*  45 */       .method_26868(class_5134.field_23719, 0.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void method_5693(class_2945.class_9222 builder) {
/*  50 */     super.method_5693(builder);
/*  51 */     builder.method_56912(METEOR_TYPE, SafariMeteorType.METEOR.id());
/*  52 */     builder.method_56912(PHASE, Integer.valueOf(SafariMeteorPhase.FLIGHT.ordinal()));
/*  53 */     builder.method_56912(VISUAL_SCALE, Float.valueOf(0.7F));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void method_5959() {}
/*     */ 
/*     */   
/*     */   public void method_5773() {
/*  62 */     super.method_5773();
/*  63 */     method_18800(0.0D, 0.0D, 0.0D);
/*  64 */     method_5875(true);
/*  65 */     this.field_6017 = 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void applyMeteor(@NotNull SafariMeteorType type, @NotNull SafariMeteorPhase phase, float scale) {
/*  71 */     this.field_6011.method_12778(METEOR_TYPE, type.id());
/*  72 */     this.field_6011.method_12778(PHASE, Integer.valueOf(phase.ordinal()));
/*  73 */     this.field_6011.method_12778(VISUAL_SCALE, Float.valueOf(Math.max(0.2F, scale)));
/*  74 */     method_5665((class_2561)class_2561.method_43470(type.displayName() + " Meteor"));
/*  75 */     method_5880(false);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public SafariMeteorType getMeteorType() {
/*  80 */     return SafariMeteorType.byId((String)this.field_6011.method_12789(METEOR_TYPE));
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public SafariMeteorPhase getPhase() {
/*  85 */     int ordinal = Math.max(0, Math.min((SafariMeteorPhase.values()).length - 1, ((Integer)this.field_6011.method_12789(PHASE)).intValue()));
/*  86 */     return SafariMeteorPhase.values()[ordinal];
/*     */   }
/*     */   
/*     */   public float getVisualScale() {
/*  90 */     return ((Float)this.field_6011.method_12789(VISUAL_SCALE)).floatValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public class_4048 method_18377(class_4050 pose) {
/*  95 */     return super.method_18377(pose).method_18383(getVisualScale());
/*     */   }
/*     */ 
/*     */   
/*     */   public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
/* 100 */     controllers.add(new AnimationController((GeoAnimatable)this, "meteor_controller", 0, state -> {
/*     */             state.setAnimation((getPhase() == SafariMeteorPhase.IMPACT) ? IMPACT_ANIMATION : IDLE_ANIMATION);
/*     */             return PlayState.CONTINUE;
/*     */           }));
/*     */   }
/*     */ 
/*     */   
/*     */   public AnimatableInstanceCache getAnimatableInstanceCache() {
/* 108 */     return this.cache;
/*     */   }
/*     */ 
/*     */   
/*     */   public void method_5749(class_2487 nbt) {
/* 113 */     if (nbt.method_10545("MeteorType")) this.field_6011.method_12778(METEOR_TYPE, nbt.method_10558("MeteorType")); 
/* 114 */     if (nbt.method_10545("Phase")) this.field_6011.method_12778(PHASE, Integer.valueOf(nbt.method_10550("Phase"))); 
/* 115 */     if (nbt.method_10545("VisualScale")) this.field_6011.method_12778(VISUAL_SCALE, Float.valueOf(nbt.method_10583("VisualScale")));
/*     */   
/*     */   }
/*     */   
/*     */   public void method_5652(class_2487 nbt) {
/* 120 */     nbt.method_10582("MeteorType", getMeteorType().id());
/* 121 */     nbt.method_10569("Phase", getPhase().ordinal());
/* 122 */     nbt.method_10548("VisualScale", getVisualScale());
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\safari\meteor\entity\SafariMeteorEntity.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */