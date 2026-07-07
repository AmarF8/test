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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class GuideStatBar
/*     */ {
/*     */   private static final int MAX_STAT_VALUE = 255;
/*     */   private static final int LABEL_WIDTH = 30;
/*     */   private static final int VALUE_WIDTH = 22;
/*     */   private static final int BAR_HEIGHT = 4;
/*     */   private static final int ROW_HEIGHT = 12;
/*     */   
/*     */   public static void draw(class_332 ctx, class_327 textRenderer, String label, int value, int color, int x, int y, int totalWidth) {
/*  36 */     ctx.method_51433(textRenderer, label, x, y + 2, GuideColors.TEXT_DIM, true);
/*     */ 
/*     */     
/*  39 */     int valueX = x + 30;
/*  40 */     ctx.method_51433(textRenderer, String.valueOf(value), valueX, y + 2, GuideColors.TEXT_PRIMARY, true);
/*     */ 
/*     */     
/*  43 */     int barX = valueX + 22;
/*  44 */     int barW = totalWidth - 30 - 22;
/*  45 */     int barY = y + 3;
/*  46 */     ctx.method_25294(barX, barY, barX + barW, barY + 4, GuideColors.withAlpha(GuideColors.PANEL_BG, 180));
/*     */ 
/*     */     
/*  49 */     float ratio = Math.min(1.0F, value / 255.0F);
/*  50 */     int fillW = (int)(barW * ratio);
/*  51 */     if (fillW > 0) {
/*  52 */       ctx.method_25294(barX, barY, barX + fillW, barY + 4, color);
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
/*     */   public static int drawStatPanel(class_332 ctx, class_327 textRenderer, int[] stats, int x, int y, int width) {
/*  68 */     int currentY = y;
/*     */     
/*  70 */     for (int i = 0; i < 6 && i < stats.length; i++) {
/*  71 */       draw(ctx, textRenderer, GuideColors.getStatName(i), stats[i], GuideColors.getStatColor(i), x, currentY, width);
/*     */       
/*  73 */       currentY += 12;
/*     */     } 
/*     */ 
/*     */     
/*  77 */     currentY += 2;
/*  78 */     int total = 0;
/*  79 */     for (int stat : stats) total += stat;
/*     */     
/*  81 */     ctx.method_25294(x, currentY, x + width, currentY + 1, GuideColors.BORDER_DIM);
/*  82 */     currentY += 4;
/*     */     
/*  84 */     ctx.method_51433(textRenderer, "Total", x, currentY + 2, GuideColors.TEXT_ACCENT, true);
/*  85 */     ctx.method_51433(textRenderer, String.valueOf(total), x + 30, currentY + 2, GuideColors.TEXT_WHITE, true);
/*     */     
/*  87 */     return currentY - y + 12;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getStatPanelHeight() {
/*  94 */     return 90;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getRowHeight() {
/* 101 */     return 12;
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\guide\screen\widgets\GuideStatBar.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */