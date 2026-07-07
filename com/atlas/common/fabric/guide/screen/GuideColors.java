/*     */ package com.atlas.common.fabric.guide.screen;
/*     */ 
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class GuideColors
/*     */ {
/*     */   public static int color(int r, int g, int b, int a) {
/*  16 */     return a << 24 | r << 16 | g << 8 | b;
/*     */   }
/*     */   
/*     */   public static int lighten(int color, int amount) {
/*  20 */     int a = color >> 24 & 0xFF;
/*  21 */     int r = Math.min(255, (color >> 16 & 0xFF) + amount);
/*  22 */     int g = Math.min(255, (color >> 8 & 0xFF) + amount);
/*  23 */     int b = Math.min(255, (color & 0xFF) + amount);
/*  24 */     return a << 24 | r << 16 | g << 8 | b;
/*     */   }
/*     */   
/*     */   public static int darken(int color, int amount) {
/*  28 */     int a = color >> 24 & 0xFF;
/*  29 */     int r = Math.max(0, (color >> 16 & 0xFF) - amount);
/*  30 */     int g = Math.max(0, (color >> 8 & 0xFF) - amount);
/*  31 */     int b = Math.max(0, (color & 0xFF) - amount);
/*  32 */     return a << 24 | r << 16 | g << 8 | b;
/*     */   }
/*     */   
/*     */   public static int withAlpha(int color, int alpha) {
/*  36 */     return color & 0xFFFFFF | alpha << 24;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  42 */   public static final int ACCENT_EMERALD = color(46, 204, 113, 255);
/*  43 */   public static final int ACCENT_TEAL = color(26, 188, 156, 255);
/*  44 */   public static final int ACCENT_DARK_GREEN = color(34, 153, 84, 255);
/*     */ 
/*     */   
/*  47 */   public static final int PANEL_BG = color(12, 14, 20, 255);
/*  48 */   public static final int SECTION_BG = color(16, 20, 28, 255);
/*  49 */   public static final int HEADER_BG = color(14, 18, 26, 255);
/*  50 */   public static final int CARD_BG = color(20, 25, 35, 255);
/*  51 */   public static final int CARD_HOVER_BG = color(28, 35, 48, 255);
/*     */ 
/*     */   
/*  54 */   public static final int BORDER = color(40, 55, 50, 255);
/*  55 */   public static final int BORDER_HIGHLIGHT = color(46, 204, 113, 200);
/*  56 */   public static final int BORDER_DIM = color(30, 40, 38, 255);
/*     */ 
/*     */   
/*  59 */   public static final int TAB_ACTIVE_BG = color(25, 40, 35, 255);
/*  60 */   public static final int TAB_INACTIVE_BG = color(12, 14, 20, 255);
/*  61 */   public static final int TAB_HOVER_BG = color(18, 28, 25, 255);
/*     */ 
/*     */   
/*  64 */   public static final int TEXT_PRIMARY = color(220, 235, 225, 255);
/*  65 */   public static final int TEXT_DIM = color(100, 130, 120, 255);
/*  66 */   public static final int TEXT_ACCENT = color(46, 204, 113, 255);
/*  67 */   public static final int TEXT_WHITE = color(255, 255, 255, 255);
/*  68 */   public static final int TEXT_MUTED = color(70, 90, 80, 255);
/*     */ 
/*     */   
/*  71 */   public static final int BUTTON_BG = color(30, 42, 38, 255);
/*  72 */   public static final int BUTTON_HOVER = color(40, 58, 52, 255);
/*  73 */   public static final int BUTTON_DISABLED = color(22, 28, 26, 255);
/*  74 */   public static final int BUTTON_BORDER = color(50, 80, 70, 255);
/*  75 */   public static final int BUTTON_BORDER_HOVER = color(46, 204, 113, 200);
/*     */ 
/*     */   
/*  78 */   public static final int SCROLLBAR_TRACK = color(20, 25, 30, 180);
/*  79 */   public static final int SCROLLBAR_THUMB = color(46, 204, 113, 160);
/*  80 */   public static final int SCROLLBAR_THUMB_HOVER = color(46, 204, 113, 220);
/*     */ 
/*     */   
/*  83 */   public static final int SEARCH_BG = color(18, 22, 30, 255);
/*  84 */   public static final int SEARCH_BORDER = color(40, 55, 50, 255);
/*  85 */   public static final int SEARCH_BORDER_FOCUS = color(46, 204, 113, 255);
/*  86 */   public static final int SEARCH_PLACEHOLDER = color(80, 100, 90, 255);
/*     */ 
/*     */ 
/*     */   
/*  90 */   public static final Map<String, Integer> TYPE_COLORS = Map.ofEntries((Map.Entry<? extends String, ? extends Integer>[])new Map.Entry[] { 
/*  91 */         Map.entry("normal", Integer.valueOf(color(168, 168, 120, 255))), 
/*  92 */         Map.entry("fire", Integer.valueOf(color(240, 128, 48, 255))), 
/*  93 */         Map.entry("water", Integer.valueOf(color(104, 144, 240, 255))), 
/*  94 */         Map.entry("electric", Integer.valueOf(color(248, 208, 48, 255))), 
/*  95 */         Map.entry("grass", Integer.valueOf(color(120, 200, 80, 255))), 
/*  96 */         Map.entry("ice", Integer.valueOf(color(152, 216, 216, 255))), 
/*  97 */         Map.entry("fighting", Integer.valueOf(color(192, 48, 40, 255))), 
/*  98 */         Map.entry("poison", Integer.valueOf(color(160, 64, 160, 255))), 
/*  99 */         Map.entry("ground", Integer.valueOf(color(224, 192, 104, 255))), 
/* 100 */         Map.entry("flying", Integer.valueOf(color(168, 144, 240, 255))), 
/* 101 */         Map.entry("psychic", Integer.valueOf(color(248, 88, 136, 255))), 
/* 102 */         Map.entry("bug", Integer.valueOf(color(168, 184, 32, 255))), 
/* 103 */         Map.entry("rock", Integer.valueOf(color(184, 160, 56, 255))), 
/* 104 */         Map.entry("ghost", Integer.valueOf(color(112, 88, 152, 255))), 
/* 105 */         Map.entry("dragon", Integer.valueOf(color(112, 56, 248, 255))), 
/* 106 */         Map.entry("dark", Integer.valueOf(color(112, 88, 72, 255))), 
/* 107 */         Map.entry("steel", Integer.valueOf(color(184, 184, 208, 255))), 
/* 108 */         Map.entry("fairy", Integer.valueOf(color(238, 153, 172, 255))) });
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 113 */   public static final int STAT_HP = color(255, 89, 89, 255);
/* 114 */   public static final int STAT_ATK = color(245, 172, 120, 255);
/* 115 */   public static final int STAT_DEF = color(250, 224, 120, 255);
/* 116 */   public static final int STAT_SPA = color(157, 183, 245, 255);
/* 117 */   public static final int STAT_SPD = color(167, 219, 141, 255);
/* 118 */   public static final int STAT_SPE = color(250, 146, 178, 255);
/*     */   
/*     */   public static int getStatColor(int index) {
/* 121 */     switch (index) { case 0: case 1: case 2: case 3: case 4: case 5:  }  return 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 128 */       TEXT_DIM;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getStatName(int index) {
/* 133 */     switch (index) { case 0: case 1: case 2: case 3: case 4: case 5:  }  return 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 140 */       "???";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 146 */   public static final int RARITY_COMMON = color(160, 170, 165, 255);
/* 147 */   public static final int RARITY_UNCOMMON = color(46, 204, 113, 255);
/* 148 */   public static final int RARITY_RARE = color(104, 144, 240, 255);
/* 149 */   public static final int RARITY_ULTRA_RARE = color(160, 64, 160, 255);
/*     */   
/*     */   public static int getRarityColor(String rarity) {
/* 152 */     if (rarity == null) return RARITY_COMMON; 
/* 153 */     switch (rarity.toLowerCase()) { case "uncommon": case "rare": case "ultra-rare": case "ultrarare":  }  return 
/*     */ 
/*     */ 
/*     */       
/* 157 */       RARITY_COMMON;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getTypeColor(String type) {
/* 164 */     if (type == null) return TEXT_DIM; 
/* 165 */     return ((Integer)TYPE_COLORS.getOrDefault(type.toLowerCase(), Integer.valueOf(TEXT_DIM))).intValue();
/*     */   }
/*     */   
/*     */   public static int getTypeBgColor(String type) {
/* 169 */     return darken(getTypeColor(type), 60);
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\guide\screen\GuideColors.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */