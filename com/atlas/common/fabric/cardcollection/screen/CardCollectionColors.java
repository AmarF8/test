/*     */ package com.atlas.common.fabric.cardcollection.screen;
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
/*     */ public final class CardCollectionColors
/*     */ {
/*     */   public static int color(int r, int g, int b, int a) {
/*  16 */     return a << 24 | r << 16 | g << 8 | b;
/*     */   } public static int withAlpha(int color, int alpha) {
/*  18 */     return color & 0xFFFFFF | alpha << 24;
/*     */   }
/*     */   public static int lighten(int color, int amount) {
/*  21 */     int a = color >> 24 & 0xFF;
/*  22 */     int r = Math.min(255, (color >> 16 & 0xFF) + amount);
/*  23 */     int g = Math.min(255, (color >> 8 & 0xFF) + amount);
/*  24 */     int b = Math.min(255, (color & 0xFF) + amount);
/*  25 */     return a << 24 | r << 16 | g << 8 | b;
/*     */   }
/*     */   
/*     */   public static int darken(int color, int amount) {
/*  29 */     int a = color >> 24 & 0xFF;
/*  30 */     int r = Math.max(0, (color >> 16 & 0xFF) - amount);
/*  31 */     int g = Math.max(0, (color >> 8 & 0xFF) - amount);
/*  32 */     int b = Math.max(0, (color & 0xFF) - amount);
/*  33 */     return a << 24 | r << 16 | g << 8 | b;
/*     */   }
/*     */ 
/*     */   
/*  37 */   public static final int SCRIM = color(0, 0, 0, 150);
/*  38 */   public static final int PANEL_BG = color(28, 22, 18, 255);
/*  39 */   public static final int PANEL_BG_DEEP = color(20, 16, 13, 255);
/*  40 */   public static final int HEADER_BG = color(46, 33, 24, 255);
/*  41 */   public static final int SECTION_BG = color(36, 28, 22, 255);
/*  42 */   public static final int CARD_BG = color(42, 33, 26, 255);
/*  43 */   public static final int CARD_HOVER_BG = color(58, 45, 34, 255);
/*  44 */   public static final int SLOT_BG = color(24, 19, 15, 255);
/*  45 */   public static final int SLOT_LOCKED_BG = color(31, 25, 21, 255);
/*  46 */   public static final int SLOT_INNER = color(18, 14, 11, 255);
/*  47 */   public static final int CARD_BACK_BG = color(74, 36, 30, 255);
/*  48 */   public static final int SPINE_BG = color(16, 12, 10, 255);
/*  49 */   public static final int PAGE_BG = color(34, 27, 21, 255);
/*     */ 
/*     */   
/*  52 */   public static final int BORDER = color(92, 66, 44, 255);
/*  53 */   public static final int BORDER_DIM = color(58, 44, 33, 255);
/*  54 */   public static final int BORDER_HIGHLIGHT = color(214, 170, 96, 255);
/*     */ 
/*     */   
/*  57 */   public static final int ACCENT_GOLD = color(214, 170, 96, 255);
/*  58 */   public static final int ACCENT_GOLD_DIM = color(150, 118, 66, 255);
/*  59 */   public static final int ACCENT_GREEN = color(120, 200, 110, 255);
/*  60 */   public static final int ACCENT_RED = color(214, 88, 78, 255);
/*     */ 
/*     */   
/*  63 */   public static final int TEXT_PRIMARY = color(238, 226, 206, 255);
/*  64 */   public static final int TEXT_DIM = color(168, 146, 118, 255);
/*  65 */   public static final int TEXT_MUTED = color(112, 94, 76, 255);
/*  66 */   public static final int TEXT_GOLD = ACCENT_GOLD;
/*  67 */   public static final int TEXT_WHITE = color(255, 255, 255, 255);
/*     */ 
/*     */   
/*  70 */   public static final int BUTTON_BG = color(60, 45, 33, 255);
/*  71 */   public static final int BUTTON_HOVER = color(78, 59, 43, 255);
/*  72 */   public static final int BUTTON_DISABLED = color(40, 32, 26, 255);
/*  73 */   public static final int BUTTON_BORDER = color(96, 72, 50, 255);
/*     */ 
/*     */ 
/*     */   
/*     */   public static int rarityColor(String rarity) {
/*  78 */     if (rarity == null) return color(170, 170, 170, 255); 
/*  79 */     switch (rarity.toLowerCase()) { case "uncommon": case "rare": case "holo_rare": case "double_rare": case "illustration_rare": case "special_illustration_rare": case "hyper_rare":  }  return 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  87 */       color(178, 170, 158, 255);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int rarityTier(String rarity) {
/*  98 */     if (rarity == null) return 0; 
/*  99 */     switch (rarity.toLowerCase()) { case "rare": case "holo_rare": case "double_rare": case "illustration_rare": case "special_illustration_rare": case "hyper_rare":  }  return 
/*     */ 
/*     */       
/* 102 */       0;
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\cardcollection\screen\CardCollectionColors.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */