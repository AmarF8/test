/*     */ package com.atlas.common.fabric.safari.racing.client;
/*     */ 
/*     */ import com.cobblemon.mod.common.client.CobblemonClient;
/*     */ import java.util.List;
/*     */ import net.fabricmc.api.EnvType;
/*     */ import net.fabricmc.api.Environment;
/*     */ import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
/*     */ import net.minecraft.class_2960;
/*     */ import net.minecraft.class_310;
/*     */ import net.minecraft.class_327;
/*     */ import net.minecraft.class_332;
/*     */ import net.minecraft.class_9779;
/*     */ 
/*     */ 
/*     */ @Environment(EnvType.CLIENT)
/*     */ public final class RacingHudRenderer
/*     */ {
/*     */   private static final String NS = "atlas-common-fabric";
/*  19 */   private static final class_2960 WAITING_TO_START_TEXTURE = class_2960.method_60655("atlas-common-fabric", "textures/safari/racing/waitingtostart.png");
/*     */   
/*     */   private static final int BG = -871559923;
/*     */   
/*     */   private static final int BG_HEADER = -870704594;
/*     */   
/*     */   private static final int ACCENT_GOLD = -10496;
/*     */   
/*     */   private static final int ACCENT_RED = -2030080;
/*     */   
/*     */   private static final int TEXT_WHITE = -1;
/*     */   
/*     */   private static final int TEXT_GRAY = -5592406;
/*     */   
/*     */   private static final int TEXT_DARK_GRAY = -10066330;
/*     */   private static final int TEXT_GREEN = -16711936;
/*     */   private static final int TEXT_GOLD = -10496;
/*     */   private static final int TEXT_PURPLE = -6283024;
/*     */   private static final int HIGHLIGHT_BG = 1090508544;
/*     */   private static final int TIMING_WIDTH = 180;
/*     */   private static final int TIMING_PAD = 6;
/*     */   private static final int LINE_H = 10;
/*     */   private static final int WAITING_TO_START_W = 127;
/*     */   private static final int WAITING_TO_START_H = 17;
/*     */   private static final int LB_WIDTH = 150;
/*     */   private static final int LB_ROW_H = 12;
/*     */   private static final int LB_PAD = 4;
/*     */   private static final int LB_MARGIN = 4;
/*     */   
/*     */   public static void register() {
/*  49 */     HudRenderCallback.EVENT.register((drawContext, tickCounter) -> {
/*     */           if ((class_310.method_1551()).field_1724 == null)
/*     */             return; 
/*     */           render(drawContext);
/*     */         });
/*     */   }
/*     */   private static void render(class_332 ctx) {
/*  56 */     class_310 client = class_310.method_1551();
/*  57 */     if (client.field_1690.field_1842)
/*  58 */       return;  if (client.method_53526().method_53536())
/*  59 */       return;  try { if (CobblemonClient.INSTANCE.getBattle() != null) return;  } catch (Exception exception) {}
/*     */     
/*  61 */     RacingHudState state = RacingHudState.getInstance();
/*  62 */     if (!state.isActive())
/*     */       return; 
/*  64 */     class_327 tr = client.field_1772;
/*  65 */     int screenW = client.method_22683().method_4486();
/*     */     
/*  67 */     ctx.method_51448().method_22903();
/*  68 */     ctx.method_51448().method_46416(0.0F, 0.0F, 200.0F);
/*     */     
/*  70 */     renderTimingPanel(ctx, tr, screenW);
/*  71 */     renderLeaderboard(ctx, tr, screenW);
/*     */     
/*  73 */     ctx.method_51448().method_22909();
/*     */   }
/*     */   private static void renderTimingPanel(class_332 ctx, class_327 tr, int screenW) {
/*     */     String timeText;
/*     */     int timeColor;
/*     */     String bestText;
/*  79 */     RacingHudState state = RacingHudState.getInstance();
/*  80 */     boolean drawWaitingBanner = (state.isWaitingForStart() && state.getLapCount() <= 0);
/*  81 */     int primaryRowHeight = drawWaitingBanner ? 17 : 10;
/*  82 */     int x = (screenW - 180) / 2;
/*  83 */     int y = 4;
/*     */ 
/*     */     
/*  86 */     int h = 6;
/*  87 */     h += primaryRowHeight;
/*  88 */     h += 2;
/*  89 */     h += 10;
/*  90 */     h += 2;
/*  91 */     h += 10;
/*  92 */     h += 2;
/*  93 */     h += 10;
/*  94 */     h += 2;
/*  95 */     h += 10;
/*  96 */     h += 6;
/*     */ 
/*     */     
/*  99 */     ctx.method_25294(x, y, x + 180, y + h, -871559923);
/*     */     
/* 101 */     ctx.method_25294(x, y, x + 180, y + 2, -2030080);
/*     */     
/* 103 */     int cy = y + 6 + 2;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 108 */     if (state.isWaitingForStart()) {
/* 109 */       if (state.getLapCount() > 0) {
/* 110 */         timeText = RacingHudState.formatTime(state.getInterpolatedLapTimeMillis()) + " ✔";
/* 111 */         timeColor = -16711936;
/*     */       } else {
/* 113 */         timeText = "";
/* 114 */         timeColor = -5592406;
/*     */       } 
/*     */     } else {
/* 117 */       timeText = RacingHudState.formatTime(state.getInterpolatedLapTimeMillis());
/* 118 */       timeColor = -1;
/*     */     } 
/* 120 */     if (drawWaitingBanner) {
/* 121 */       ctx.method_25293(WAITING_TO_START_TEXTURE, x + 26, cy, 127, 17, 0.0F, 0.0F, 127, 17, 127, 17);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 135 */       drawCentered(ctx, tr, timeText, x, cy, 180, timeColor);
/*     */     } 
/* 137 */     cy += primaryRowHeight + 2;
/*     */ 
/*     */     
/* 140 */     if (!state.isWaitingForStart()) {
/* 141 */       String cpText = "Ring " + state.getCurrentCheckpoint() + " / " + state.getTotalCheckpoints();
/* 142 */       drawCentered(ctx, tr, cpText, x, cy, 180, -5592406);
/*     */     } 
/* 144 */     cy += 12;
/*     */ 
/*     */     
/* 147 */     float splitOpacity = state.getSplitOpacity();
/* 148 */     if (splitOpacity > 0.0F && !state.getSplitText().isEmpty()) {
/* 149 */       int alpha = (int)(splitOpacity * 255.0F);
/* 150 */       int splitColor = alpha << 24 | state.getSplitColor() & 0xFFFFFF;
/* 151 */       drawCentered(ctx, tr, state.getSplitText(), x, cy, 180, splitColor);
/*     */     } 
/* 153 */     cy += 12;
/*     */ 
/*     */     
/* 156 */     long bestMillis = state.getPersonalBestMillis();
/* 157 */     int pos = state.getPlayerPosition();
/*     */     
/* 159 */     if (bestMillis > 0L) {
/* 160 */       bestText = "BEST " + RacingHudState.formatTime(bestMillis) + ((pos > 0) ? ("  #" + pos) : "");
/*     */     } else {
/* 162 */       bestText = "NO LAP SET";
/*     */     } 
/* 164 */     drawCentered(ctx, tr, bestText, x, cy, 180, (bestMillis > 0L) ? -16711936 : -10066330);
/* 165 */     cy += 12;
/*     */ 
/*     */     
/* 168 */     long eventRemaining = state.getInterpolatedEventRemainingMillis();
/* 169 */     drawCentered(ctx, tr, RacingHudState.formatTime(eventRemaining) + " REMAINING", x, cy, 180, -10066330);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void renderLeaderboard(class_332 ctx, class_327 tr, int screenW) {
/* 175 */     RacingHudState state = RacingHudState.getInstance();
/* 176 */     List<RacingLeaderboardEntry> entries = state.getLeaderboard();
/*     */     
/* 178 */     int x = screenW - 150 - 4;
/* 179 */     int y = 4;
/*     */ 
/*     */     
/* 182 */     int headerH = 16;
/* 183 */     ctx.method_25294(x, y, x + 150, y + 16, -870704594);
/* 184 */     ctx.method_25294(x, y, x + 150, y + 2, -10496);
/* 185 */     ctx.method_51433(tr, "LEADERBOARD", x + 4, y + 4, -10496, true);
/* 186 */     y += 16;
/*     */     
/* 188 */     if (entries.isEmpty()) {
/* 189 */       ctx.method_25294(x, y, x + 150, y + 12 + 4, -871559923);
/* 190 */       ctx.method_51433(tr, "No laps yet", x + 4, y + 2, -10066330, true);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 195 */     for (int i = 0; i < entries.size(); i++) {
/* 196 */       RacingLeaderboardEntry entry = entries.get(i);
/* 197 */       int rowY = y + i * 12;
/*     */ 
/*     */       
/* 200 */       int rowBg = entry.isPlayer() ? 1090508544 : -871559923;
/* 201 */       ctx.method_25294(x, rowY, x + 150, rowY + 12, rowBg);
/*     */ 
/*     */       
/* 204 */       if (i > 0) ctx.method_25294(x, rowY, x + 150, rowY + 1, 553648127);
/*     */ 
/*     */       
/* 207 */       int posColor = (i == 0) ? -10496 : -1;
/* 208 */       String posStr = "#" + i + 1;
/* 209 */       ctx.method_51433(tr, posStr, x + 4, rowY + 2, posColor, true);
/*     */ 
/*     */       
/* 212 */       String name = entry.name();
/* 213 */       if (tr.method_1727(name) > 70) {
/* 214 */         while (tr.method_1727(name + "..") > 70 && name.length() > 1) {
/* 215 */           name = name.substring(0, name.length() - 1);
/*     */         }
/* 217 */         name = name + "..";
/*     */       } 
/* 219 */       ctx.method_51433(tr, name, x + 22, rowY + 2, entry.isPlayer() ? -10496 : -1, true);
/*     */ 
/*     */       
/* 222 */       String timeStr = RacingHudState.formatTime(entry.timeMillis());
/* 223 */       int timeW = tr.method_1727(timeStr);
/* 224 */       ctx.method_51433(tr, timeStr, x + 150 - timeW - 4, rowY + 2, -16711936, true);
/*     */     } 
/*     */ 
/*     */     
/* 228 */     int bottomY = y + entries.size() * 12;
/* 229 */     ctx.method_25294(x, bottomY, x + 150, bottomY + 1, -10496);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void drawCentered(class_332 ctx, class_327 tr, String text, int panelX, int y, int panelWidth, int color) {
/* 235 */     int tw = tr.method_1727(text);
/* 236 */     ctx.method_51433(tr, text, panelX + (panelWidth - tw) / 2, y, color, true);
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\safari\racing\client\RacingHudRenderer.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */