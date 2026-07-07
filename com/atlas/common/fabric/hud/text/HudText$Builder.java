/*     */ package com.atlas.common.fabric.hud.text;
/*     */ 
/*     */ import java.util.Objects;
/*     */ import net.kyori.adventure.text.format.TextColor;
/*     */ import net.minecraft.class_2561;
/*     */ import org.jetbrains.annotations.NotNull;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Builder
/*     */ {
/*     */   public final String identifier;
/*     */   private class_2561 text;
/*     */   private int xPercentage;
/*     */   private int yPercentage;
/*     */   private int x;
/*     */   private int y;
/*     */   private boolean shadow = true;
/*     */   private boolean centered;
/*     */   private int maximumWidth;
/*     */   private int defaultTextColor;
/*     */   private int backgroundColor;
/*     */   
/*     */   private Builder(@NotNull String identifier, @NotNull class_2561 text) {
/*  76 */     this.identifier = Objects.<String>requireNonNull(identifier);
/*  77 */     this.text = Objects.<class_2561>requireNonNull(text);
/*  78 */     this.maximumWidth = Integer.MAX_VALUE;
/*  79 */     this.defaultTextColor = 12632256;
/*  80 */     this.backgroundColor = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Builder text(@NotNull class_2561 text) {
/*  92 */     this.text = Objects.<class_2561>requireNonNull(text);
/*  93 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Builder xPercentage(int xPercentage) {
/* 104 */     this.xPercentage = xPercentage;
/* 105 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Builder yPercentage(int yPercentage) {
/* 116 */     this.yPercentage = yPercentage;
/* 117 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Builder x(int x) {
/* 128 */     this.x = x;
/* 129 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Builder y(int y) {
/* 140 */     this.y = y;
/* 141 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Builder shadow(boolean shadow) {
/* 152 */     this.shadow = shadow;
/* 153 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Builder centered(boolean centered) {
/* 164 */     this.centered = centered;
/* 165 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Builder maximumWidth(int maximumWidth) {
/* 176 */     this.maximumWidth = maximumWidth;
/* 177 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Builder defaultTextColor(int defaultTextColor) {
/* 188 */     this.defaultTextColor = defaultTextColor;
/* 189 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Builder defaultTextColor(@NotNull TextColor defaultTextColor, float opacity) {
/* 201 */     this.defaultTextColor = defaultTextColor.value() & 0xFFFFFF | (int)(opacity * 255.0F) << 24;
/* 202 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Builder backgroundColor(int defaultBackgroundColor) {
/* 213 */     this.backgroundColor = defaultBackgroundColor;
/* 214 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Builder backgroundColor(@NotNull TextColor defaultBackgroundColor, float opacity) {
/* 226 */     this.backgroundColor = defaultBackgroundColor.value() & 0xFFFFFF | (int)(opacity * 255.0F) << 24;
/* 227 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public HudText build() {
/* 237 */     return new HudText(this.identifier, this.text, this.xPercentage, this.yPercentage, this.x, this.y, this.shadow, this.centered, this.maximumWidth, this.defaultTextColor, this.backgroundColor);
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\hud\text\HudText$Builder.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */