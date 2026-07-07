/*     */ package com.atlas.common.fabric.hud.text;
/*     */ 
/*     */ import com.atlas.common.dictionary.DictionaryObject;
/*     */ import com.atlas.common.fabric.hud.text.packet.HudTextRemovePacket;
/*     */ import com.atlas.common.fabric.hud.text.packet.HudTextUpdateContentPacket;
/*     */ import com.atlas.common.fabric.hud.text.packet.HudTextUpdatePacket;
/*     */ import java.util.Objects;
/*     */ import net.kyori.adventure.text.format.TextColor;
/*     */ import net.minecraft.class_2561;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class HudText
/*     */   implements DictionaryObject<String>
/*     */ {
/*     */   public static final int DEFAULT_COLOR = 12632256;
/*     */   public static final int DEFAULT_BACKGROUND_COLOR = 0;
/*     */   protected final String identifier;
/*     */   protected class_2561 text;
/*     */   protected int xPercentage;
/*     */   protected int yPercentage;
/*     */   protected int x;
/*     */   protected int y;
/*     */   protected boolean shadow;
/*     */   protected boolean centered;
/*     */   protected int maximumWidth;
/*     */   protected int defaultTextColor;
/*     */   protected int backgroundColor;
/*     */   
/*     */   @NotNull
/*     */   public static Builder builder(@NotNull String identifier) {
/*  33 */     return new Builder(identifier, class_2561.method_30163(""));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static Builder builder(@NotNull String identifier, @NotNull class_2561 text) {
/*  46 */     return new Builder(identifier, text);
/*     */   }
/*     */ 
/*     */   
/*     */   public static final class Builder
/*     */   {
/*     */     public final String identifier;
/*     */     
/*     */     private class_2561 text;
/*     */     
/*     */     private int xPercentage;
/*     */     
/*     */     private int yPercentage;
/*     */     
/*     */     private int x;
/*     */     
/*     */     private int y;
/*     */     
/*     */     private boolean shadow = true;
/*     */     
/*     */     private boolean centered;
/*     */     
/*     */     private int maximumWidth;
/*     */     
/*     */     private int defaultTextColor;
/*     */     
/*     */     private int backgroundColor;
/*     */ 
/*     */     
/*     */     private Builder(@NotNull String identifier, @NotNull class_2561 text) {
/*  76 */       this.identifier = Objects.<String>requireNonNull(identifier);
/*  77 */       this.text = Objects.<class_2561>requireNonNull(text);
/*  78 */       this.maximumWidth = Integer.MAX_VALUE;
/*  79 */       this.defaultTextColor = 12632256;
/*  80 */       this.backgroundColor = 0;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public Builder text(@NotNull class_2561 text) {
/*  92 */       this.text = Objects.<class_2561>requireNonNull(text);
/*  93 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public Builder xPercentage(int xPercentage) {
/* 104 */       this.xPercentage = xPercentage;
/* 105 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public Builder yPercentage(int yPercentage) {
/* 116 */       this.yPercentage = yPercentage;
/* 117 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public Builder x(int x) {
/* 128 */       this.x = x;
/* 129 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public Builder y(int y) {
/* 140 */       this.y = y;
/* 141 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public Builder shadow(boolean shadow) {
/* 152 */       this.shadow = shadow;
/* 153 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public Builder centered(boolean centered) {
/* 164 */       this.centered = centered;
/* 165 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public Builder maximumWidth(int maximumWidth) {
/* 176 */       this.maximumWidth = maximumWidth;
/* 177 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public Builder defaultTextColor(int defaultTextColor) {
/* 188 */       this.defaultTextColor = defaultTextColor;
/* 189 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public Builder defaultTextColor(@NotNull TextColor defaultTextColor, float opacity) {
/* 201 */       this.defaultTextColor = defaultTextColor.value() & 0xFFFFFF | (int)(opacity * 255.0F) << 24;
/* 202 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public Builder backgroundColor(int defaultBackgroundColor) {
/* 213 */       this.backgroundColor = defaultBackgroundColor;
/* 214 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public Builder backgroundColor(@NotNull TextColor defaultBackgroundColor, float opacity) {
/* 226 */       this.backgroundColor = defaultBackgroundColor.value() & 0xFFFFFF | (int)(opacity * 255.0F) << 24;
/* 227 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public HudText build() {
/* 237 */       return new HudText(this.identifier, this.text, this.xPercentage, this.yPercentage, this.x, this.y, this.shadow, this.centered, this.maximumWidth, this.defaultTextColor, this.backgroundColor);
/*     */     }
/*     */   }
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
/*     */   
/*     */   public HudText(@NotNull String identifier, @NotNull class_2561 text, int xPercentage, int yPercentage, int x, int y, boolean shadow, boolean centered, int maximumWidth, int defaultTextColor, int backgroundColor) {
/* 296 */     this.identifier = Objects.<String>requireNonNull(identifier);
/* 297 */     this.text = Objects.<class_2561>requireNonNull(text);
/* 298 */     this.xPercentage = xPercentage;
/* 299 */     this.yPercentage = yPercentage;
/* 300 */     this.x = x;
/* 301 */     this.y = y;
/* 302 */     this.shadow = shadow;
/* 303 */     this.centered = centered;
/* 304 */     this.maximumWidth = maximumWidth;
/* 305 */     this.defaultTextColor = defaultTextColor;
/* 306 */     this.backgroundColor = backgroundColor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public final String getIdentifier() {
/* 315 */     return this.identifier;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public final class_2561 getText() {
/* 323 */     return this.text;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getXPercentage() {
/* 330 */     return this.xPercentage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getYPercentage() {
/* 337 */     return this.yPercentage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getX() {
/* 344 */     return this.x;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getY() {
/* 351 */     return this.y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final boolean hasShadow() {
/* 358 */     return this.shadow;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final boolean isCentered() {
/* 365 */     return this.centered;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getMaximumWidth() {
/* 372 */     return this.maximumWidth;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getDefaultTextColor() {
/* 379 */     return this.defaultTextColor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getBackgroundColor() {
/* 386 */     return this.backgroundColor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void updateText(@NotNull class_2561 text) {
/* 397 */     this.text = Objects.<class_2561>requireNonNull(text);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void handleUpdatePacket(@NotNull HudTextUpdatePacket packet) {
/* 407 */     this.text = packet.getText();
/* 408 */     this.xPercentage = packet.getXPercentage();
/* 409 */     this.yPercentage = packet.getYPercentage();
/* 410 */     this.x = packet.getX();
/* 411 */     this.y = packet.getY();
/* 412 */     this.shadow = packet.hasShadow();
/* 413 */     this.centered = packet.isCentered();
/* 414 */     this.maximumWidth = packet.getMaximumWidth();
/* 415 */     this.defaultTextColor = packet.getDefaultTextColor();
/* 416 */     this.backgroundColor = packet.getDefaultBackgroundColor();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public final Builder createBuilder() {
/* 426 */     return (new Builder(this.identifier, this.text))
/* 427 */       .xPercentage(this.xPercentage)
/* 428 */       .yPercentage(this.yPercentage)
/* 429 */       .x(this.x)
/* 430 */       .y(this.y)
/* 431 */       .maximumWidth(this.maximumWidth)
/* 432 */       .defaultTextColor(this.defaultTextColor)
/* 433 */       .backgroundColor(this.backgroundColor);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public final HudTextUpdatePacket createUpdatePacket(boolean createIfNotExists) {
/* 444 */     return new HudTextUpdatePacket(this.identifier, this.text, this.xPercentage, this.yPercentage, this.x, this.y, this.shadow, this.centered, this.maximumWidth, this.defaultTextColor, this.backgroundColor, createIfNotExists);
/*     */   }
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
/*     */   public final HudTextUpdateContentPacket createUpdateContentPacket() {
/* 467 */     return new HudTextUpdateContentPacket(this.identifier, this.text);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public HudTextRemovePacket createRemovePacket() {
/* 478 */     return new HudTextRemovePacket(this.identifier);
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\hud\text\HudText.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */