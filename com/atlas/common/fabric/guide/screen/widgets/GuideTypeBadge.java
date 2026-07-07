/*     */ package com.atlas.common.fabric.guide.screen.widgets;
/*     */ 
/*     */ import com.atlas.common.fabric.guide.screen.GuideColors;
/*     */ import net.minecraft.class_327;
/*     */ import net.minecraft.class_332;
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
/*     */ public final class GuideTypeBadge
/*     */ {
/*     */   private static final int DEFAULT_HEIGHT = 11;
/*     */   private static final int HORIZONTAL_PADDING = 4;
/*     */   private static final int VERTICAL_PADDING = 1;
/*     */   
/*     */   public static int draw(class_332 ctx, class_327 textRenderer, String type, int x, int y) {
/*  30 */     String typeLower = type.toLowerCase();
/*  31 */     String displayName = typeLower.substring(0, 1).toUpperCase() + typeLower.substring(0, 1).toUpperCase();
/*  32 */     int textWidth = textRenderer.method_1727(displayName);
/*  33 */     int badgeWidth = textWidth + 8;
/*  34 */     int badgeHeight = 11;
/*     */ 
/*     */     
/*  37 */     int bgColor = GuideColors.getTypeBgColor(typeLower);
/*  38 */     ctx.method_25294(x, y, x + badgeWidth, y + badgeHeight, bgColor);
/*     */ 
/*     */     
/*  41 */     int accentColor = GuideColors.getTypeColor(typeLower);
/*  42 */     ctx.method_25294(x, y, x + badgeWidth, y + 1, accentColor);
/*     */ 
/*     */     
/*  45 */     ctx.method_25294(x, y + badgeHeight - 1, x + badgeWidth, y + badgeHeight, accentColor);
/*     */ 
/*     */     
/*  48 */     int textY = y + 1 + 1;
/*  49 */     ctx.method_51433(textRenderer, displayName, x + 4, textY, GuideColors.TEXT_WHITE, true);
/*     */     
/*  51 */     return badgeWidth;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int draw(class_332 ctx, class_327 textRenderer, String type, int x, int y, int w, int h) {
/*  60 */     String typeLower = type.toLowerCase();
/*  61 */     String displayName = typeLower.substring(0, 1).toUpperCase() + typeLower.substring(0, 1).toUpperCase();
/*     */ 
/*     */     
/*  64 */     int bgColor = GuideColors.getTypeBgColor(typeLower);
/*  65 */     ctx.method_25294(x, y, x + w, y + h, bgColor);
/*     */ 
/*     */     
/*  68 */     int accentColor = GuideColors.getTypeColor(typeLower);
/*  69 */     ctx.method_25294(x, y, x + w, y + 1, accentColor);
/*     */ 
/*     */     
/*  72 */     ctx.method_25294(x, y + h - 1, x + w, y + h, accentColor);
/*     */ 
/*     */     
/*  75 */     int textWidth = textRenderer.method_1727(displayName);
/*  76 */     int textX = x + (w - textWidth) / 2;
/*  77 */     int textY = y + (h - 8) / 2;
/*  78 */     ctx.method_51433(textRenderer, displayName, textX, textY, GuideColors.TEXT_WHITE, true);
/*     */     
/*  80 */     return w;
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
/*     */   public static int drawFilter(class_332 ctx, class_327 textRenderer, String type, int x, int y, boolean selected, boolean hovered) {
/*  92 */     String typeLower = type.toLowerCase();
/*  93 */     String displayName = typeLower.substring(0, 1).toUpperCase() + typeLower.substring(0, 1).toUpperCase();
/*  94 */     int textWidth = textRenderer.method_1727(displayName);
/*  95 */     int badgeWidth = textWidth + 8;
/*  96 */     int badgeHeight = 12;
/*     */     
/*  98 */     int accentColor = GuideColors.getTypeColor(typeLower);
/*     */     
/* 100 */     if (selected) {
/*     */       
/* 102 */       ctx.method_25294(x, y, x + badgeWidth, y + badgeHeight, accentColor);
/* 103 */       ctx.method_51433(textRenderer, displayName, x + 4, y + 2, GuideColors.TEXT_WHITE, true);
/*     */     } else {
/*     */       
/* 106 */       int bgColor = hovered ? GuideColors.withAlpha(accentColor, 40) : GuideColors.CARD_BG;
/* 107 */       ctx.method_25294(x, y, x + badgeWidth, y + badgeHeight, bgColor);
/* 108 */       ctx.method_49601(x, y, badgeWidth, badgeHeight, hovered ? accentColor : GuideColors.BORDER_DIM);
/* 109 */       ctx.method_51433(textRenderer, displayName, x + 4, y + 2, hovered ? accentColor : GuideColors.TEXT_DIM, true);
/*     */     } 
/*     */     
/* 112 */     return badgeWidth;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getWidth(class_327 textRenderer, String type) {
/* 119 */     String typeLower = type.toLowerCase();
/* 120 */     String displayName = typeLower.substring(0, 1).toUpperCase() + typeLower.substring(0, 1).toUpperCase();
/* 121 */     return textRenderer.method_1727(displayName) + 8;
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\guide\screen\widgets\GuideTypeBadge.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */