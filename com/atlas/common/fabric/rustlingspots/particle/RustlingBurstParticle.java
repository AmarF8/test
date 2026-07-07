/*     */ package com.atlas.common.fabric.rustlingspots.particle;
/*     */ 
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.ConcurrentMap;
/*     */ import net.fabricmc.api.EnvType;
/*     */ import net.fabricmc.api.Environment;
/*     */ import net.minecraft.class_1047;
/*     */ import net.minecraft.class_2400;
/*     */ import net.minecraft.class_2960;
/*     */ import net.minecraft.class_3532;
/*     */ import net.minecraft.class_3999;
/*     */ import net.minecraft.class_4002;
/*     */ import net.minecraft.class_4003;
/*     */ import net.minecraft.class_638;
/*     */ import net.minecraft.class_703;
/*     */ import net.minecraft.class_707;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ @Environment(EnvType.CLIENT)
/*     */ public class RustlingBurstParticle
/*     */   extends class_4003
/*     */ {
/*  24 */   private static final Logger LOGGER = LoggerFactory.getLogger(RustlingBurstParticle.class);
/*  25 */   private static final ConcurrentMap<String, Boolean> LOGGED_SPRITE_FAILURES = new ConcurrentHashMap<>(); private final class_4002 sprites; private final Style style; private final boolean hasSprites; private boolean spriteReady;
/*     */   private final String debugId;
/*     */   
/*     */   public static final class Style extends Record { private final float scaleMultiplier;
/*     */     private final float scaleJitter;
/*     */     private final int minLifetime;
/*     */     private final int maxLifetime;
/*     */     private final float friction;
/*     */     private final float gravity;
/*     */     private final float riseAcceleration;
/*     */     
/*  36 */     public Style(float scaleMultiplier, float scaleJitter, int minLifetime, int maxLifetime, float friction, float gravity, float riseAcceleration) { this.scaleMultiplier = scaleMultiplier; this.scaleJitter = scaleJitter; this.minLifetime = minLifetime; this.maxLifetime = maxLifetime; this.friction = friction; this.gravity = gravity; this.riseAcceleration = riseAcceleration; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/rustlingspots/particle/RustlingBurstParticle$Style;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #36	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*  36 */       //   0	7	0	this	Lcom/atlas/common/fabric/rustlingspots/particle/RustlingBurstParticle$Style; } public float scaleMultiplier() { return this.scaleMultiplier; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/rustlingspots/particle/RustlingBurstParticle$Style;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #36	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/rustlingspots/particle/RustlingBurstParticle$Style; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/rustlingspots/particle/RustlingBurstParticle$Style;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #36	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lcom/atlas/common/fabric/rustlingspots/particle/RustlingBurstParticle$Style;
/*  36 */       //   0	8	1	o	Ljava/lang/Object; } public float scaleJitter() { return this.scaleJitter; } public int minLifetime() { return this.minLifetime; } public int maxLifetime() { return this.maxLifetime; } public float friction() { return this.friction; } public float gravity() { return this.gravity; } public float riseAcceleration() { return this.riseAcceleration; }
/*     */      }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected RustlingBurstParticle(class_638 level, double x, double y, double z, double velocityX, double velocityY, double velocityZ, class_4002 sprites, Style style, String debugId) {
/*  43 */     super(level, x, y, z, velocityX, velocityY, velocityZ);
/*  44 */     this.sprites = sprites;
/*  45 */     this.style = style;
/*  46 */     this.hasSprites = (sprites != null);
/*  47 */     this.debugId = debugId;
/*  48 */     this.field_28786 = style.friction();
/*  49 */     this.field_3844 = style.gravity();
/*  50 */     this.field_3862 = true;
/*     */     
/*  52 */     boolean needsMotion = (Math.abs(velocityX) < 1.0E-4D && Math.abs(velocityY) < 1.0E-4D && Math.abs(velocityZ) < 1.0E-4D);
/*  53 */     if (needsMotion) {
/*  54 */       this.field_3852 = (this.field_3840.method_43058() - 0.5D) * 0.08D;
/*  55 */       this.field_3850 = (this.field_3840.method_43058() - 0.5D) * 0.08D;
/*  56 */       this.field_3869 = 0.06D + this.field_3840.method_43058() * 0.04D;
/*     */     } else {
/*  58 */       this.field_3852 *= 0.65D;
/*  59 */       this.field_3869 *= 0.8D;
/*  60 */       this.field_3850 *= 0.65D;
/*     */     } 
/*     */     
/*  63 */     float jitter = (float)(style.scaleJitter() * (level.field_9229.method_43058() - 0.5D));
/*  64 */     this.field_17867 *= style.scaleMultiplier() + jitter;
/*  65 */     this.field_3847 = style.minLifetime() + level.field_9229.method_43048(style.maxLifetime() - style.minLifetime() + 1);
/*  66 */     this.field_3841 = 0.0F;
/*  67 */     this.field_3857 = level.field_9229.method_43057() * 6.2831855F;
/*  68 */     this.field_3839 = this.field_3857;
/*  69 */     this.spriteReady = ensureSpriteBound();
/*     */   }
/*     */   
/*     */   public static class_707<class_2400> provider(class_4002 sprites, Style style, String debugId) {
/*  73 */     return (type, level, x, y, z, dx, dy, dz) -> new RustlingBurstParticle(level, x, y, z, dx, dy, dz, sprites, style, debugId);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void method_3070() {
/*  79 */     super.method_3070();
/*  80 */     if (!this.spriteReady) {
/*  81 */       this.spriteReady = ensureSpriteBound();
/*     */     }
/*     */     
/*  84 */     float progress = this.field_3866 / this.field_3847;
/*  85 */     if (progress < 0.25F) {
/*  86 */       this.field_3841 = class_3532.method_15363(progress / 0.25F, 0.0F, 1.0F);
/*  87 */     } else if (progress > 0.75F) {
/*  88 */       this.field_3841 = class_3532.method_15363(1.0F - (progress - 0.75F) / 0.25F, 0.0F, 1.0F);
/*     */     } else {
/*  90 */       this.field_3841 = 1.0F;
/*     */     } 
/*     */     
/*  93 */     this.field_3869 += this.style.riseAcceleration();
/*     */   }
/*     */   
/*     */   private boolean ensureSpriteBound() {
/*  97 */     if (!this.hasSprites) {
/*  98 */       logSpriteIssue("sprite set handle is null");
/*  99 */       method_3085();
/* 100 */       return false;
/*     */     } 
/*     */     
/*     */     try {
/* 104 */       method_18141(this.sprites.method_18139(this.field_3840));
/* 105 */     } catch (Exception e) {
/* 106 */       logSpriteIssue("setSprite threw: " + e.getMessage());
/*     */     } 
/*     */     
/* 109 */     if (this.field_17886 == null) {
/* 110 */       logSpriteIssue("sprite set returned null sprite");
/* 111 */       method_3085();
/* 112 */       return false;
/*     */     } 
/*     */     
/* 115 */     class_2960 spriteName = this.field_17886.method_45851().method_45816();
/* 116 */     if (class_1047.method_4539().equals(spriteName)) {
/* 117 */       logSpriteIssue("sprite resolved to missing texture atlas sprite");
/* 118 */       method_3085();
/* 119 */       return false;
/*     */     } 
/*     */     
/* 122 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public class_3999 method_18122() {
/* 127 */     return class_3999.field_17829;
/*     */   }
/*     */   
/*     */   private void logSpriteIssue(String reason) {
/* 131 */     if (this.debugId == null)
/* 132 */       return;  LOGGED_SPRITE_FAILURES.computeIfAbsent(this.debugId, key -> {
/*     */           LOGGER.warn("Rustling Spots: {} has no usable sprite set ({}). The particle will be discarded.", key, reason);
/*     */           return Boolean.valueOf(true);
/*     */         });
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\rustlingspots\particle\RustlingBurstParticle.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */