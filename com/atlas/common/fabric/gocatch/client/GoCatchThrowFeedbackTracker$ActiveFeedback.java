/*     */ package com.atlas.common.fabric.gocatch.client;
/*     */ 
/*     */ import net.minecraft.class_327;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class ActiveFeedback
/*     */ {
/*     */   private static final int LIFETIME_TICKS = 80;
/*     */   private static final double FLOAT_SPEED = 0.015D;
/*     */   private static final double MAX_FLOAT = 0.35D;
/*     */   private static final int FADE_IN_TICKS = 4;
/*     */   private static final int FADE_OUT_TICKS = 14;
/*     */   private static final int POP_TICKS = 8;
/*     */   private static final float POP_SCALE_PEAK = 1.9F;
/*     */   private static final float POP_SCALE_SETTLED = 1.0F;
/*     */   private final int pokemonEntityId;
/*     */   private final String text;
/*     */   private final int color;
/*     */   private final double anchorX;
/*     */   private final double anchorY;
/*     */   private final double anchorZ;
/*     */   private final float rotationDegrees;
/*     */   private int ticksRemaining;
/*     */   private final int totalTicks;
/*     */   private double floatOffset;
/*  75 */   private int cachedTextWidth = -1;
/*     */ 
/*     */   
/*     */   private ActiveFeedback(int pokemonEntityId, String text, int color, double anchorX, double anchorY, double anchorZ, float rotationDegrees) {
/*  79 */     this.pokemonEntityId = pokemonEntityId;
/*  80 */     this.text = text;
/*  81 */     this.color = color;
/*  82 */     this.anchorX = anchorX;
/*  83 */     this.anchorY = anchorY;
/*  84 */     this.anchorZ = anchorZ;
/*  85 */     this.rotationDegrees = rotationDegrees;
/*  86 */     this.ticksRemaining = 80;
/*  87 */     this.totalTicks = 80;
/*     */   }
/*     */   
/*  90 */   public int getPokemonEntityId() { return this.pokemonEntityId; }
/*  91 */   public String getText() { return this.text; }
/*  92 */   public int getColor() { return this.color; }
/*  93 */   public double getAnchorX() { return this.anchorX; }
/*  94 */   public double getAnchorY() { return this.anchorY; }
/*  95 */   public double getAnchorZ() { return this.anchorZ; }
/*  96 */   public double getFloatOffset() { return this.floatOffset; } public float getRotationDegrees() {
/*  97 */     return this.rotationDegrees;
/*     */   }
/*     */   public float getFadeAlpha() {
/* 100 */     int elapsed = this.totalTicks - this.ticksRemaining;
/* 101 */     if (elapsed < 4) return elapsed / 4.0F; 
/* 102 */     if (this.ticksRemaining < 14) return this.ticksRemaining / 14.0F; 
/* 103 */     return 1.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getPopScale() {
/* 111 */     int elapsed = this.totalTicks - this.ticksRemaining;
/* 112 */     if (elapsed >= 8) return 1.0F; 
/* 113 */     float t = elapsed / 8.0F;
/*     */     
/* 115 */     float eased = 1.0F - (1.0F - t) * (1.0F - t) * (1.0F - t);
/* 116 */     return 1.9F + -0.9F * eased;
/*     */   }
/*     */   
/*     */   public float getCenteredTextX(class_327 textRenderer) {
/* 120 */     if (this.cachedTextWidth < 0) this.cachedTextWidth = textRenderer.method_1727(this.text); 
/* 121 */     return -this.cachedTextWidth / 2.0F;
/*     */   }
/*     */   
/*     */   private boolean tickDown() {
/* 125 */     this.ticksRemaining--;
/* 126 */     if (this.floatOffset < 0.35D) this.floatOffset += 0.015D; 
/* 127 */     return (this.ticksRemaining > 0);
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\gocatch\client\GoCatchThrowFeedbackTracker$ActiveFeedback.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */