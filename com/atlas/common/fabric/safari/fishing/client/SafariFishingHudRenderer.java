/*     */ package com.atlas.common.fabric.safari.fishing.client;
/*     */ 
/*     */ import net.fabricmc.api.EnvType;
/*     */ import net.fabricmc.api.Environment;
/*     */ import net.minecraft.class_310;
/*     */ import net.minecraft.class_327;
/*     */ import net.minecraft.class_332;
/*     */ import net.minecraft.class_3532;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Environment(EnvType.CLIENT)
/*     */ public final class SafariFishingHudRenderer
/*     */ {
/*     */   private static final int PANEL_BG = -1072621025;
/*     */   private static final int PANEL_OUTLINE = -9121067;
/*     */   private static final int TENSION_BG = -1438770396;
/*     */   private static final int TENSION_FILL = -1023373;
/*     */   private static final int PROGRESS_BG = -1441057493;
/*     */   private static final int PROGRESS_FILL = -9313886;
/*     */   private static final int BAR_BG = -15261143;
/*     */   private static final int BAR_TRACK = -14535618;
/*     */   private static final int POINTER = -1378305;
/*     */   
/*     */   public static void register() {}
/*     */   
/*     */   public static void renderOverlay(class_332 context) {
/*  29 */     render(context);
/*     */   }
/*     */   
/*     */   private static void render(class_332 ctx) {
/*  33 */     class_310 client = class_310.method_1551();
/*  34 */     SafariFishingHudState state = SafariFishingHudState.getInstance();
/*  35 */     state.tick();
/*     */     
/*  37 */     if (client.field_1724 == null || client.field_1690.field_1842)
/*  38 */       return;  if (!state.isActive() && state.getResultTicks() <= 0)
/*     */       return; 
/*  40 */     if (state.isActive()) {
/*  41 */       ctx.method_51448().method_22903();
/*  42 */       ctx.method_51448().method_46416(0.0F, 0.0F, 300.0F);
/*  43 */       renderActive(ctx, client.field_1772, state);
/*  44 */       ctx.method_51448().method_22909();
/*     */     } 
/*  46 */     if (state.getResultTicks() > 0) {
/*  47 */       ctx.method_51448().method_22903();
/*  48 */       ctx.method_51448().method_46416(0.0F, 0.0F, 310.0F);
/*  49 */       renderResult(ctx, client.field_1772, state);
/*  50 */       ctx.method_51448().method_22909();
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void renderActive(class_332 ctx, class_327 tr, SafariFishingHudState state) {
/*  55 */     int screenWidth = ctx.method_51421();
/*  56 */     int screenHeight = ctx.method_51443();
/*  57 */     int panelWidth = 316;
/*  58 */     int panelHeight = 92;
/*  59 */     int x = (screenWidth - panelWidth) / 2;
/*  60 */     int y = screenHeight - 142;
/*  61 */     boolean waiting = "WAITING".equals(state.getPhase());
/*     */     
/*  63 */     ctx.method_25294(x, y, x + panelWidth, y + panelHeight, -1072621025);
/*  64 */     ctx.method_49601(x, y, panelWidth, panelHeight, -9121067);
/*     */     
/*  66 */     String title = (waiting || state.getFishName().isBlank()) ? "Safari Fishing" : state.getFishName();
/*  67 */     ctx.method_51433(tr, title, x + 10, y + 8, -1, true);
/*  68 */     if (!waiting) {
/*  69 */       String catchInfo = String.format("%.1fkg  •  x%.2f", new Object[] { Float.valueOf(state.getWeightKg()), Float.valueOf(state.getSizeScale()) });
/*  70 */       ctx.method_51433(tr, catchInfo, x + 10 + tr.method_1727(title) + 10, y + 8, -2691598, false);
/*     */     } 
/*     */     
/*  73 */     String rarity = (waiting || state.getRarityName().isBlank()) ? "Waiting" : state.getRarityName();
/*  74 */     int rarityWidth = tr.method_1727(rarity) + 10;
/*  75 */     int rarityColor = waiting ? -1438690725 : (state.getRarityColor() & 0xDFFFFFFF);
/*  76 */     ctx.method_25294(x + panelWidth - rarityWidth - 10, y + 6, x + panelWidth - 10, y + 18, rarityColor);
/*  77 */     ctx.method_51433(tr, rarity, x + panelWidth - rarityWidth - 5, y + 8, -16314348, false);
/*     */     
/*  79 */     String loadout = "Bait: " + emptyAsDash(state.getBaitName()) + "  Line: " + emptyAsDash(state.getLineName()) + "  Reel: " + emptyAsDash(state.getReelName());
/*  80 */     ctx.method_51433(tr, loadout, x + 10, y + 22, -4729894, false);
/*     */     
/*  82 */     int barX = x + 14;
/*  83 */     int barY = y + 36;
/*  84 */     int barWidth = panelWidth - 28;
/*  85 */     int barHeight = 14;
/*  86 */     ctx.method_25294(barX, barY, barX + barWidth, barY + barHeight, -15261143);
/*  87 */     ctx.method_25294(barX + 1, barY + 1, barX + barWidth - 1, barY + barHeight - 1, -14535618);
/*     */     
/*  89 */     int zoneStart = barX + Math.round((state.getZoneCenter() - state.getZoneWidth() * 0.5F) * barWidth);
/*  90 */     int zoneEnd = barX + Math.round((state.getZoneCenter() + state.getZoneWidth() * 0.5F) * barWidth);
/*  91 */     ctx.method_25294(zoneStart, barY + 2, zoneEnd, barY + barHeight - 2, state.isThrashing() ? -6665158 : -13726113);
/*     */     
/*  93 */     int pointerX = barX + Math.round(state.getPointer() * barWidth);
/*  94 */     ctx.method_25294(pointerX - 1, barY - 3, pointerX + 1, barY + barHeight + 3, -1378305);
/*     */     
/*  96 */     int progressX = x + 14;
/*  97 */     int progressY = y + 65;
/*  98 */     int meterWidth = 124;
/*  99 */     ctx.method_51433(tr, "Land", progressX, progressY - 11, -1378305, false);
/* 100 */     ctx.method_25294(progressX, progressY, progressX + meterWidth, progressY + 8, -1441057493);
/* 101 */     ctx.method_25294(progressX, progressY, progressX + Math.round(meterWidth * state.getProgress()), progressY + 8, -9313886);
/*     */     
/* 103 */     int tensionX = x + panelWidth - meterWidth - 14;
/* 104 */     ctx.method_51433(tr, "Tension", tensionX, progressY - 11, -1378305, false);
/* 105 */     ctx.method_25294(tensionX, progressY, tensionX + meterWidth, progressY + 8, -1438770396);
/* 106 */     ctx.method_25294(tensionX, progressY, tensionX + Math.round(meterWidth * state.getTension()), tensionY(progressY), -1023373);
/*     */     
/* 108 */     switch (state.getPhase()) { case "WAITING": 
/*     */       case "HOOKED": 
/*     */       default:
/* 111 */         break; }  String footer = "";
/*     */     
/* 113 */     ctx.method_51433(tr, footer, x + 10, y + panelHeight - 12, -5192500, false);
/*     */     
/* 115 */     if (waiting) {
/* 116 */       String wait = String.format("~%.1fs", new Object[] { Float.valueOf(state.getWaitTicks() / 20.0F) });
/* 117 */       ctx.method_51433(tr, wait, x + panelWidth / 2 - tr.method_1727(wait) / 2, y + 54, -6366238, false);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static int tensionY(int progressY) {
/* 122 */     return progressY + 8;
/*     */   }
/*     */   
/*     */   private static void renderResult(class_332 ctx, class_327 tr, SafariFishingHudState state) {
/* 126 */     float alpha = class_3532.method_15363(state.getResultTicks() / 60.0F, 0.0F, 1.0F);
/* 127 */     int screenWidth = ctx.method_51421();
/* 128 */     int centerX = screenWidth / 2;
/* 129 */     int y = 64;
/* 130 */     int color = (int)(alpha * 255.0F) << 24 | state.getResultColor() & 0xFFFFFF;
/* 131 */     ctx.method_51433(tr, state.getResultTitle(), centerX - tr.method_1727(state.getResultTitle()) / 2, y, color, true);
/* 132 */     ctx.method_51433(tr, state.getResultSubtitle(), centerX - tr.method_1727(state.getResultSubtitle()) / 2, y + 12, -571803649, false);
/*     */   }
/*     */   
/*     */   private static String emptyAsDash(String value) {
/* 136 */     return (value == null || value.isBlank()) ? "-" : value;
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\safari\fishing\client\SafariFishingHudRenderer.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */