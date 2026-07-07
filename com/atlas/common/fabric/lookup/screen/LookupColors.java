/*     */ package com.atlas.common.fabric.lookup.screen;public final class LookupColors { public static final int ACCENT = -10777105; public static final int ACCENT_DIM = -12951648; public static final int BG = -15789801; public static final int BG_SECONDARY = -15328732; public static final int BORDER = -14012352; public static final int BORDER_LIGHT = -12958368;
/*     */   public static final int TEXT_PRIMARY = -1511169;
/*     */   public static final int TEXT_DIM = -9930592;
/*     */   public static final int TEXT_ACCENT = -10777105;
/*     */   public static final int TEXT_WHITE = -1;
/*     */   public static final int TEXT_GOLD = -10496;
/*     */   public static final int TEXT_GREEN = -11870592;
/*     */   public static final int TEXT_RED = -1096636;
/*     */   public static final int SUCCESS = -12339839;
/*     */   public static final int WARNING = -1662404;
/*     */   public static final int DANGER = -1030329;
/*     */   
/*     */   public static int color(int r, int g, int b, int a) {
/*  14 */     return a << 24 | r << 16 | g << 8 | b;
/*     */   }
/*     */   public static final int CARD_BG = -14801866; public static final int CARD_HOVER = -14274480; public static final int TAB_ACTIVE = -10777105; public static final int TAB_INACTIVE = -12958368; public static final int TAB_HOVER = -11904384; public static final int TAB_ACTIVE_BG = -15063744; public static final int TAB_INACTIVE_BG = -15789801; public static final int TAB_HOVER_BG = -15459800; public static final int SCROLLBAR_TRACK = -15065298; public static final int SCROLLBAR_THUMB = -12958368; public static final int SCROLLBAR_THUMB_HOVER = -10777105; public static final int HEADER_BG = -15855080; public static final int BUTTON_BG = -14801354; public static final int BUTTON_HOVER = -14142392; public static final int BUTTON_BORDER = -12958368; public static final int BUTTON_BORDER_HOVER = -10777105;
/*     */   public static int lighten(int color, int amount) {
/*  18 */     int a = color >> 24 & 0xFF;
/*  19 */     int r = Math.min(255, (color >> 16 & 0xFF) + amount);
/*  20 */     int g = Math.min(255, (color >> 8 & 0xFF) + amount);
/*  21 */     int b = Math.min(255, (color & 0xFF) + amount);
/*  22 */     return a << 24 | r << 16 | g << 8 | b;
/*     */   }
/*     */   
/*     */   public static int darken(int color, int amount) {
/*  26 */     int a = color >> 24 & 0xFF;
/*  27 */     int r = Math.max(0, (color >> 16 & 0xFF) - amount);
/*  28 */     int g = Math.max(0, (color >> 8 & 0xFF) - amount);
/*  29 */     int b = Math.max(0, (color & 0xFF) - amount);
/*  30 */     return a << 24 | r << 16 | g << 8 | b;
/*     */   }
/*     */   
/*     */   public static int withAlpha(int color, int alpha) {
/*  34 */     return color & 0xFFFFFF | alpha << 24;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int actionColor(String action) {
/* 100 */     if (action == null) return -9930592; 
/* 101 */     switch (action.toLowerCase()) { case "block-break": case "block-place": case "container-insert": case "container-remove": case "container-open": case "container-close": case "item-drop": case "item-pickup": case "entity-kill": case "chat": case "command":  }  return 
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
/* 113 */       -9930592;
/*     */   } }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\lookup\screen\LookupColors.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */