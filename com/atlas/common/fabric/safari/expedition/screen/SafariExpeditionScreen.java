/*     */ package com.atlas.common.fabric.safari.expedition.screen;
/*     */ 
/*     */ import com.atlas.common.fabric.safari.expedition.SafariExpeditionClientData;
/*     */ import com.atlas.common.fabric.safari.expedition.SafariExpeditionModels;
/*     */ import com.atlas.common.fabric.safari.expedition.network.SafariExpeditionNetwork;
/*     */ import com.cobblemon.mod.common.api.gui.GuiUtilsKt;
/*     */ import com.cobblemon.mod.common.api.types.ElementalType;
/*     */ import com.cobblemon.mod.common.api.types.ElementalTypes;
/*     */ import com.cobblemon.mod.common.client.gui.TypeIcon;
/*     */ import com.cobblemon.mod.common.client.render.RenderHelperKt;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import net.minecraft.class_2561;
/*     */ import net.minecraft.class_2960;
/*     */ import net.minecraft.class_332;
/*     */ import net.minecraft.class_437;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
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
/*     */ public final class SafariExpeditionScreen
/*     */   extends class_437
/*     */ {
/*     */   private static final String NS = "atlas-common-fabric";
/*  36 */   private static final class_2960 TEX_BG = class_2960.method_60655("atlas-common-fabric", "textures/safari/expedition/expedition_command_board.png");
/*     */   
/*     */   private static final int GUI_W = 544;
/*     */   
/*     */   private static final int GUI_H = 322;
/*     */   
/*     */   private static final int HEADER_STATS_X = 240;
/*     */   
/*     */   private static final int HEADER_RIGHT = 514;
/*     */   
/*     */   private static final int LABEL_CHIP_Y = 77;
/*     */   
/*     */   private static final int LEFT_BOX_RIGHT = 266;
/*     */   
/*     */   private static final int RIGHT_BOX_RIGHT = 517;
/*     */   
/*     */   private static final int LEFT_BOX_CX = 146;
/*     */   
/*     */   private static final int LEFT_BOX_CY = 196;
/*     */   
/*     */   private static final int RIGHT_BOX_CX = 397;
/*     */   
/*     */   private static final int ACTIVE_BOX_CY = 144;
/*     */   
/*     */   private static final int RETURNED_BOX_CY = 251;
/*     */   
/*     */   private static final int FOOTER_TEXT_Y = 300;
/*     */   
/*     */   private static final int CARD_X = 26;
/*     */   
/*     */   private static final int CARD_W = 240;
/*     */   private static final int CARD_H = 45;
/*     */   private static final int CARD_GAP = 4;
/*     */   private static final int CARD_LIST_Y = 101;
/*     */   private static final int RIGHT_X = 277;
/*     */   private static final int RIGHT_W = 240;
/*     */   private static final int ACTIVE_LIST_Y = 101;
/*     */   private static final int ACTIVE_CARD_H = 42;
/*     */   private static final int ACTIVE_CARD_GAP = 4;
/*     */   private static final int RETURNED_LIST_Y = 211;
/*     */   private static final int RETURNED_CARD_H = 28;
/*     */   private static final int RETURNED_CARD_GAP = 4;
/*     */   private static final int ACTION_ROW_OFFSET = 27;
/*     */   private static final int BOOSTER_BTN_X = 142;
/*     */   private static final int BOOSTER_BTN_W = 54;
/*     */   private static final int OPEN_BTN_X = 198;
/*     */   private static final int OPEN_BTN_W = 40;
/*     */   private static final int ACTION_BTN_H = 14;
/*     */   private static final int BACKDROP = -1727591920;
/*     */   private static final int CARD_TOP = -14539217;
/*     */   private static final int CARD_BOTTOM = -15197405;
/*     */   private static final int CARD_BORDER = -13486269;
/*     */   private static final int CHIP_BG = -15723751;
/*     */   private static final int TEXT_BRIGHT = -1;
/*     */   private static final int TEXT = -1644047;
/*     */   private static final int MUTED = -5656386;
/*     */   private static final int DIM = -9537914;
/*     */   private static final int OK = -8396670;
/*     */   private static final int WARN = -800918;
/*     */   private static final int BAD = -30070;
/*     */   private static final int ACCENT = -8407297;
/*     */   private static final float SMALL_SCALE = 0.5F;
/*  98 */   private final Map<String, SafariExpeditionModels.ExpeditionBoosterType> selectedBoosters = new HashMap<>();
/*     */   private int guiLeft;
/*     */   private int guiTop;
/*     */   private int ticks;
/*     */   
/*     */   public SafariExpeditionScreen() {
/* 104 */     super((class_2561)class_2561.method_43470("Safari Expeditions"));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void method_25426() {
/* 109 */     super.method_25426();
/* 110 */     this.guiLeft = Math.max(8, (this.field_22789 - 544) / 2);
/* 111 */     this.guiTop = Math.max(8, (this.field_22790 - 322) / 2);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean method_25421() {
/* 116 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void method_25393() {
/* 121 */     super.method_25393();
/* 122 */     this.ticks++;
/* 123 */     if (this.ticks % 40 == 0) {
/* 124 */       SafariExpeditionNetwork.requestSync();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void method_25394(@NotNull class_332 context, int mouseX, int mouseY, float delta) {
/* 130 */     context.method_25294(0, 0, this.field_22789, this.field_22790, -1727591920);
/*     */     
/* 132 */     SafariExpeditionModels.ExpeditionSyncData syncData = SafariExpeditionClientData.getInstance().getSyncData();
/*     */     
/* 134 */     int x = this.guiLeft;
/* 135 */     int y = this.guiTop;
/* 136 */     drawTex(context, TEX_BG, x, y, 544, 322);
/*     */     
/* 138 */     renderHeader(context, x, y, syncData.profile());
/* 139 */     renderBalloonCards(context, x, y, syncData.opportunities(), mouseX, mouseY);
/* 140 */     renderActiveRuns(context, x, y, syncData.activeRuns());
/* 141 */     renderReturnedRuns(context, x, y, syncData.returnedRuns());
/*     */     
/* 143 */     boolean toastActive = (System.currentTimeMillis() - SafariExpeditionClientData.getInstance().getLastMessageAt() <= 5000L);
/* 144 */     if (toastActive) {
/* 145 */       renderToast(context, x, y);
/*     */     } else {
/* 147 */       renderFooterHint(context, x, y);
/*     */     } 
/*     */     
/* 150 */     super.method_25394(context, mouseX, mouseY, delta);
/*     */   }
/*     */ 
/*     */   
/*     */   public void method_25420(@NotNull class_332 context, int mouseX, int mouseY, float delta) {}
/*     */ 
/*     */   
/*     */   private void drawTex(@NotNull class_332 context, @NotNull class_2960 texture, int x, int y, int w, int h) {
/* 158 */     GuiUtilsKt.blitk(context.method_51448(), texture, Integer.valueOf(x), Integer.valueOf(y), Integer.valueOf(h), Integer.valueOf(w), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(w), Integer.valueOf(h), Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(1), Float.valueOf(1.0F), true, 1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderHeader(@NotNull class_332 context, int x, int y, @Nullable SafariExpeditionModels.ExpeditionProfileData profile) {
/* 164 */     if (profile == null) {
/* 165 */       drawSmallText(context, "Loading expedition profile...", (x + 240), (y + 24), -5656386, 274, false);
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 171 */     String rankLabel = "RANK " + profile.rank();
/* 172 */     int rankWidth = this.field_22793.method_1727(rankLabel) + 12;
/* 173 */     int barW = 190;
/* 174 */     int groupX = x + 240 + (274 - rankWidth + 8 + 190) / 2;
/* 175 */     int rowY = y + 19;
/*     */     
/* 177 */     context.method_25294(groupX, rowY, groupX + rankWidth, rowY + 12, -14405592);
/* 178 */     context.method_49601(groupX, rowY, rankWidth, 12, -8396670);
/* 179 */     context.method_51433(this.field_22793, "§l" + rankLabel, groupX + 6, rowY + 2, -8396670, false);
/*     */     
/* 181 */     int barX = groupX + rankWidth + 8;
/* 182 */     int nextXp = Math.max(1, profile.nextRankXp());
/* 183 */     int fill = Math.max(0, Math.min(188, (int)(188.0D * profile.rankXp() / nextXp)));
/* 184 */     drawExpBar(context, barX, rowY, 190, 11, fill);
/* 185 */     drawCenteredSmallText(context, "" + profile.rankXp() + " / " + profile.rankXp(), barX + 95, rowY + 4, -1);
/*     */ 
/*     */ 
/*     */     
/* 189 */     int chipY = y + 77;
/* 190 */     int chipW = 34;
/* 191 */     int chipGap = 4;
/* 192 */     int boosterX = x + 266 - 110;
/* 193 */     drawBoosterChip(context, boosterX, chipY, "P", profile.powerBoosters(), -29092);
/* 194 */     drawBoosterChip(context, boosterX + 34 + 4, chipY, "S", profile.successBoosters(), -10235137);
/* 195 */     drawBoosterChip(context, boosterX + 76, chipY, "B", profile.bountyBoosters(), -2049195);
/*     */     
/* 197 */     String slots = "" + profile.activeSlotsUsed() + "/" + profile.activeSlotsUsed();
/* 198 */     drawStatChip(context, x + 517 - statChipWidth("SLOTS", slots), chipY, "SLOTS", slots, -8407297);
/*     */   }
/*     */   
/*     */   private int statChipWidth(String label, String value) {
/* 202 */     int labelW = (int)Math.ceil((this.field_22793.method_1727(label) * 0.5F));
/* 203 */     return 5 + labelW + 4 + this.field_22793.method_1727("§l" + value) + 5;
/*     */   }
/*     */ 
/*     */   
/*     */   private int drawStatChip(@NotNull class_332 context, int x, int y, String label, String value, int valueColor) {
/* 208 */     int labelW = (int)Math.ceil((this.field_22793.method_1727(label) * 0.5F));
/* 209 */     int w = statChipWidth(label, value);
/* 210 */     context.method_25294(x, y, x + w, y + 13, -15723751);
/* 211 */     context.method_49601(x, y, w, 13, -14012872);
/* 212 */     context.method_25294(x, y, x + 2, y + 13, withAlpha(valueColor, 170));
/* 213 */     drawSmallText(context, label, (x + 5), (y + 5), -9537914, labelW + 4, false);
/* 214 */     context.method_51433(this.field_22793, "§l" + value, x + 5 + labelW + 4, y + 3, valueColor, false);
/* 215 */     return w;
/*     */   }
/*     */   
/*     */   private void drawBoosterChip(@NotNull class_332 context, int x, int y, String letter, int count, int color) {
/* 219 */     int w = 34;
/* 220 */     context.method_25294(x, y, x + 34, y + 13, -15723751);
/* 221 */     context.method_49601(x, y, 34, 13, withAlpha(color, 128));
/* 222 */     context.method_25294(x + 1, y + 1, x + 12, y + 12, color);
/* 223 */     context.method_51433(this.field_22793, "§l" + letter, x + 4, y + 3, -15723751, false);
/* 224 */     String countText = "§l" + count;
/* 225 */     int countW = this.field_22793.method_1727(countText);
/* 226 */     context.method_51433(this.field_22793, countText, x + 13 + (21 - countW) / 2, y + 3, 
/* 227 */         (count > 0) ? -1 : -9537914, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderBalloonCards(@NotNull class_332 context, int guiX, int guiY, @NotNull List<SafariExpeditionModels.BalloonOpportunityData> opportunities, int mouseX, int mouseY) {
/* 238 */     if (opportunities.isEmpty()) {
/* 239 */       renderEmptyState(context, guiX + 146, guiY + 196, "No active balloons right now", "Patrol the Safari Zone to spot one drifting overhead.");
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 245 */     int x = guiX + 26;
/* 246 */     for (int i = 0; i < Math.min(4, opportunities.size()); i++) {
/* 247 */       SafariExpeditionModels.BalloonOpportunityData data = opportunities.get(i);
/* 248 */       renderBalloonCard(context, x, guiY + 101 + i * 49, data, mouseX, mouseY);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderBalloonCard(@NotNull class_332 context, int x, int y, @NotNull SafariExpeditionModels.BalloonOpportunityData data, int mouseX, int mouseY) {
/* 258 */     SafariExpeditionModels.ExpeditionTier tier = data.balloon().expedition().tier();
/* 259 */     int tierColor = tier.color() | 0xFF000000;
/*     */     
/* 261 */     context.method_25296(x, y, x + 240, y + 45, -14539217, -15197405);
/* 262 */     context.method_49601(x, y, 240, 45, data.canStart() ? -13486269 : -10864070);
/* 263 */     context.method_25294(x, y, x + 3, y + 45, tierColor);
/* 264 */     context.method_25294(x + 3, y, x + 4, y + 45, withAlpha(tierColor, 102));
/*     */ 
/*     */     
/* 267 */     String tierLabel = tier.displayName().toUpperCase(Locale.ROOT);
/* 268 */     int tierW = this.field_22793.method_1727(tierLabel) + 8;
/* 269 */     context.method_51433(this.field_22793, "§l" + fitText(data.balloon().expedition().displayName(), 240 - tierW - 24), x + 10, y + 5, -1, false);
/*     */     
/* 271 */     context.method_25294(x + 240 - tierW - 6, y + 5, x + 240 - 6, y + 15, withAlpha(tierColor, 85));
/* 272 */     context.method_49601(x + 240 - tierW - 6, y + 5, tierW, 10, tierColor);
/* 273 */     context.method_51433(this.field_22793, "§l" + tierLabel, x + 240 - tierW - 2, y + 6, tierColor, false);
/*     */ 
/*     */     
/* 276 */     String area = data.balloon().areaName();
/* 277 */     drawSmallText(context, area, (x + 10), (y + 17), -5656386, 110, false);
/* 278 */     float areaW = this.field_22793.method_1727(area) * 0.5F;
/* 279 */     drawSmallText(context, "· " + truncate(data.balloon().locationHint(), 40), (x + 10) + areaW + 3.0F, (y + 17), -9537914, (int)(220.0F - areaW), false);
/*     */ 
/*     */ 
/*     */     
/* 283 */     int statY = y + 27 + 3;
/* 284 */     drawLabeledStat(context, x + 10, statY, "PWR", String.valueOf(data.estimatedTeamPower()), -1644047);
/* 285 */     drawLabeledStat(context, x + 64, statY, "SUC", "" + data.estimatedSuccessChance() + "%", colorForChance(data.estimatedSuccessChance()));
/* 286 */     drawLabeledStat(context, x + 108, statY, "MONS", String.valueOf(data.eligiblePokemonCount()), -1644047);
/*     */ 
/*     */     
/* 289 */     SafariExpeditionModels.ExpeditionBoosterType booster = this.selectedBoosters.getOrDefault(data.balloon().balloonId(), SafariExpeditionModels.ExpeditionBoosterType.NONE);
/* 290 */     int actY = y + 27;
/* 291 */     if (data.canStart()) {
/* 292 */       drawPrimaryButton(context, x + 142, actY, 54, 14, booster.displayName(), mouseX, mouseY, true, -14012614, -8407297);
/* 293 */       drawPrimaryButton(context, x + 198, actY, 40, 14, "OPEN ▸", mouseX, mouseY, true, -14729428, -8396670);
/*     */     } else {
/* 295 */       String label = data.statusText().isBlank() ? "Locked" : data.statusText();
/* 296 */       boolean ready = ("Ready".equalsIgnoreCase(label) || "Ready.".equalsIgnoreCase(label));
/* 297 */       int pillColor = ready ? -800918 : -30070;
/* 298 */       drawStatusPill(context, x + 142, actY, 54, 14, ready ? "READY" : truncate(label.toUpperCase(Locale.ROOT), 9), pillColor);
/* 299 */       drawPrimaryButton(context, x + 198, actY, 40, 14, "LOCKED", mouseX, mouseY, false, -12968161, -30070);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void drawStatusPill(@NotNull class_332 context, int x, int y, int w, int h, @NotNull String label, int color) {
/* 304 */     context.method_25296(x, y, x + w, y + h, withAlpha(color, 51), -15328990);
/* 305 */     context.method_49601(x, y, w, h, withAlpha(color, 170));
/* 306 */     context.method_25294(x, y, x + 3, y + h, color);
/* 307 */     String render = "§l" + label;
/* 308 */     int tw = this.field_22793.method_1727(render);
/* 309 */     context.method_51433(this.field_22793, render, x + 6 + Math.max(0, (w - 6 - tw) / 2), y + 3, color, false);
/*     */   }
/*     */ 
/*     */   
/*     */   private void drawLabeledStat(@NotNull class_332 context, int x, int y, String label, String value, int valueColor) {
/* 314 */     drawSmallText(context, label, x, (y + 2), -9537914, 24, false);
/* 315 */     float labelW = this.field_22793.method_1727(label) * 0.5F;
/* 316 */     context.method_51433(this.field_22793, "§l" + value, (int)(x + labelW + 3.0F), y, valueColor, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderActiveRuns(@NotNull class_332 context, int guiX, int guiY, @NotNull List<SafariExpeditionModels.ActiveExpeditionData> activeRuns) {
/* 325 */     if (activeRuns.isEmpty()) {
/* 326 */       renderEmptyState(context, guiX + 397, guiY + 144, "No teams deployed", "Open a balloon to launch one.");
/*     */       
/*     */       return;
/*     */     } 
/* 330 */     int x = guiX + 277;
/* 331 */     int w = 240;
/* 332 */     for (int i = 0; i < Math.min(2, activeRuns.size()); i++) {
/* 333 */       SafariExpeditionModels.ActiveExpeditionData run = activeRuns.get(i);
/* 334 */       int cy = guiY + 101 + i * 46;
/* 335 */       int tierColor = run.expedition().tier().color() | 0xFF000000;
/*     */       
/* 337 */       context.method_25296(x, cy, x + 240, cy + 42, -14539217, -15197405);
/* 338 */       context.method_49601(x, cy, 240, 42, -13486269);
/* 339 */       context.method_25294(x, cy, x + 3, cy + 42, tierColor);
/* 340 */       context.method_25294(x + 3, cy, x + 4, cy + 42, withAlpha(tierColor, 102));
/*     */ 
/*     */       
/* 343 */       context.method_51433(this.field_22793, "§l" + fitText(run.expedition().displayName(), 208), x + 9, cy + 4, -1, false);
/* 344 */       String tierLetter = run.expedition().tier().displayName().substring(0, 1).toUpperCase(Locale.ROOT);
/* 345 */       context.method_25294(x + 240 - 16, cy + 4, x + 240 - 6, cy + 12, withAlpha(tierColor, 85));
/* 346 */       context.method_49601(x + 240 - 16, cy + 4, 10, 8, tierColor);
/* 347 */       context.method_51433(this.field_22793, "§l" + tierLetter, x + 240 - 13, cy + 5, tierColor, false);
/*     */ 
/*     */       
/* 350 */       int sy = cy + 15;
/* 351 */       drawLabeledStat(context, x + 9, sy, "MONS", String.valueOf(run.teamSize()), -1644047);
/* 352 */       drawLabeledStat(context, x + 56, sy, "PWR", String.valueOf(run.teamPower()), -8407297);
/* 353 */       drawLabeledStat(context, x + 112, sy, "SUC", "" + run.successChance() + "%", colorForChance(run.successChance()));
/*     */ 
/*     */       
/* 356 */       long total = Math.max(1L, run.completesAt() - run.startedAt());
/* 357 */       long elapsed = Math.max(0L, Math.min(total, System.currentTimeMillis() - run.startedAt()));
/* 358 */       int percent = (int)Math.round(100.0D * elapsed / total);
/*     */       
/* 360 */       drawSmallText(context, "ETA", (x + 9), (cy + 28), -9537914, 16, false);
/* 361 */       context.method_51433(this.field_22793, "§l" + formatDuration(Math.max(0L, run.completesAt() - System.currentTimeMillis())), x + 21, cy + 26, -800918, false);
/*     */       
/* 363 */       drawSmallText(context, "" + percent + "%", (x + 240 - 22), (cy + 28), -5656386, 16, false);
/*     */       
/* 365 */       int bw = 222;
/* 366 */       int by = cy + 42 - 6;
/* 367 */       context.method_25294(x + 9, by, x + 9 + 222, by + 3, -15987180);
/* 368 */       int fill = Math.max(0, (int)(222.0D * elapsed / total));
/* 369 */       if (fill > 0) {
/* 370 */         context.method_25296(x + 9, by, x + 9 + fill, by + 3, -800918, -3241425);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderReturnedRuns(@NotNull class_332 context, int guiX, int guiY, @NotNull List<SafariExpeditionModels.ReturnedExpeditionData> returnedRuns) {
/* 381 */     if (returnedRuns.isEmpty()) {
/* 382 */       renderEmptyState(context, guiX + 397, guiY + 251, "Nothing waiting at spawn", (String)null);
/*     */       return;
/*     */     } 
/* 385 */     int x = guiX + 277;
/* 386 */     int w = 240;
/* 387 */     for (int i = 0; i < Math.min(2, returnedRuns.size()); i++) {
/* 388 */       SafariExpeditionModels.ReturnedExpeditionData run = returnedRuns.get(i);
/* 389 */       int cy = guiY + 211 + i * 32;
/* 390 */       int statusColor = run.success() ? -8396670 : -30070;
/*     */       
/* 392 */       context.method_25296(x, cy, x + 240, cy + 28, -14539217, -15197405);
/* 393 */       context.method_49601(x, cy, 240, 28, run.success() ? -13676998 : -11653329);
/* 394 */       context.method_25294(x, cy, x + 3, cy + 28, statusColor);
/*     */       
/* 396 */       String prefix = run.success() ? "§l✓ " : "§l✗ ";
/* 397 */       context.method_51433(this.field_22793, prefix + prefix, x + 9, cy + 4, statusColor, false);
/* 398 */       drawSmallText(context, rewardLine(run), (x + 9), (cy + 17), run.success() ? -800918 : -5656386, 176, false);
/* 399 */       drawSmallText(context, "→ /spawn", (x + 240 - 38), (cy + 17), -8407297, 32, false);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void renderEmptyState(@NotNull class_332 context, int cx, int cy, String msg, @Nullable String sub) {
/* 404 */     int msgY = cy - ((sub == null) ? 2 : 6);
/* 405 */     int halfW = (int)(this.field_22793.method_1727(msg) * 0.5F) / 2;
/* 406 */     drawCenteredSmallText(context, msg, cx, msgY, -5656386);
/*     */     
/* 408 */     context.method_25294(cx - halfW - 26, msgY + 2, cx - halfW - 8, msgY + 3, withAlpha(-9537914, 102));
/* 409 */     context.method_25294(cx + halfW + 8, msgY + 2, cx + halfW + 26, msgY + 3, withAlpha(-9537914, 102));
/* 410 */     if (sub != null) {
/* 411 */       drawCenteredSmallText(context, sub, cx, cy + 4, -9537914);
/*     */     }
/*     */   }
/*     */   
/*     */   private void renderFooterHint(@NotNull class_332 context, int x, int y) {
/* 416 */     String tip = "TIP";
/* 417 */     String msg = "Right-click an Expedition Balloon in the Safari Zone to build a team from your PC.";
/* 418 */     float tipW = this.field_22793.method_1727("TIP") * 0.5F;
/* 419 */     float msgW = this.field_22793.method_1727("Right-click an Expedition Balloon in the Safari Zone to build a team from your PC.") * 0.5F;
/* 420 */     float startX = x + (544.0F - tipW + 4.0F + msgW) / 2.0F;
/* 421 */     drawSmallText(context, "§lTIP", startX, (y + 300), -5011201, 20, false);
/* 422 */     drawSmallText(context, "Right-click an Expedition Balloon in the Safari Zone to build a team from your PC.", startX + tipW + 4.0F, (y + 300), -9537914, 484, false);
/*     */   }
/*     */   
/*     */   private void renderToast(@NotNull class_332 context, int x, int y) {
/* 426 */     boolean success = SafariExpeditionClientData.getInstance().isLastMessageSuccess();
/* 427 */     int color = success ? -8396670 : -30070;
/* 428 */     String msg = SafariExpeditionClientData.getInstance().getLastMessage();
/* 429 */     int textW = (int)(this.field_22793.method_1727(msg) * 0.5F);
/* 430 */     int boxW = Math.min(504, textW + 30);
/* 431 */     int bx = x + (544 - boxW) / 2;
/* 432 */     int by = y + 322 - 27;
/* 433 */     context.method_25296(bx, by, bx + boxW, by + 16, -300541662, -301068008);
/* 434 */     context.method_49601(bx, by, boxW, 16, color);
/* 435 */     context.method_25294(bx, by, bx + 3, by + 16, color);
/* 436 */     drawSmallText(context, (success ? "✓ " : "! ") + (success ? "✓ " : "! "), (bx + 9), (by + 6), -1, boxW - 16, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean method_25402(double mouseX, double mouseY, int button) {
/* 441 */     List<SafariExpeditionModels.BalloonOpportunityData> opportunities = SafariExpeditionClientData.getInstance().getSyncData().opportunities();
/* 442 */     int x = this.guiLeft + 26;
/* 443 */     int y = this.guiTop + 101;
/* 444 */     for (int i = 0; i < Math.min(4, opportunities.size()); i++) {
/* 445 */       SafariExpeditionModels.BalloonOpportunityData data = opportunities.get(i);
/* 446 */       int cy = y + i * 49;
/* 447 */       if (inside(mouseX, mouseY, x + 142, cy + 27, 54, 14)) {
/* 448 */         SafariExpeditionModels.ExpeditionBoosterType next = nextBooster(this.selectedBoosters.getOrDefault(data.balloon().balloonId(), SafariExpeditionModels.ExpeditionBoosterType.NONE));
/* 449 */         this.selectedBoosters.put(data.balloon().balloonId(), next);
/* 450 */         return true;
/*     */       } 
/* 452 */       if (data.canStart() && inside(mouseX, mouseY, x + 198, cy + 27, 40, 14)) {
/* 453 */         SafariExpeditionNetwork.requestOpenBuilder(data.balloon().balloonId());
/* 454 */         return true;
/*     */       } 
/*     */     } 
/* 457 */     return super.method_25402(mouseX, mouseY, button);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   private SafariExpeditionModels.ExpeditionBoosterType nextBooster(@NotNull SafariExpeditionModels.ExpeditionBoosterType boosterType) {
/* 464 */     switch (boosterType) { default: throw new MatchException(null, null);case NONE: case POWER: case SUCCESS: case BOUNTY: break; }  return 
/*     */ 
/*     */ 
/*     */       
/* 468 */       SafariExpeditionModels.ExpeditionBoosterType.NONE;
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
/*     */   private void drawPrimaryButton(@NotNull class_332 context, int x, int y, int width, int height, @NotNull String label, int mouseX, int mouseY, boolean enabled, int fillColor, int accentColor) {
/* 483 */     boolean hovered = (enabled && inside(mouseX, mouseY, x, y, width, height));
/* 484 */     context.method_25296(x, y, x + width, y + height, hovered ? brighten(fillColor) : fillColor, fillColor);
/* 485 */     context.method_49601(x, y, width, height, enabled ? (hovered ? accentColor : -13486269) : -12965334);
/* 486 */     if (enabled) {
/* 487 */       context.method_25294(x, y, x + width, y + 1, withAlpha(accentColor, 102));
/*     */     }
/* 489 */     int textWidth = this.field_22793.method_1727(label);
/* 490 */     context.method_51433(this.field_22793, label, x + (width - textWidth) / 2, y + 3, enabled ? -1 : -5656386, false);
/*     */   }
/*     */   
/*     */   private boolean inside(double mouseX, double mouseY, int x, int y, int width, int height) {
/* 494 */     return (mouseX >= x && mouseX <= (x + width) && mouseY >= y && mouseY <= (y + height));
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   private String rewardLine(@NotNull SafariExpeditionModels.ReturnedExpeditionData run) {
/* 499 */     if (!run.success()) return "Report recovered · no rewards"; 
/* 500 */     StringBuilder builder = new StringBuilder();
/* 501 */     if (run.rewardCoins() > 0) builder.append(run.rewardCoins()).append("c"); 
/* 502 */     if (run.rewardGems() > 0) {
/* 503 */       if (!builder.isEmpty()) builder.append(" · "); 
/* 504 */       builder.append(run.rewardGems()).append("g");
/*     */     } 
/* 506 */     int boosters = run.rewardPowerBoosters() + run.rewardSuccessBoosters() + run.rewardBountyBoosters();
/* 507 */     if (boosters > 0) {
/* 508 */       if (!builder.isEmpty()) builder.append(" · "); 
/* 509 */       builder.append("+").append(boosters).append(" booster");
/*     */     } 
/* 511 */     return builder.isEmpty() ? "Awaiting claim" : builder.toString();
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   private String formatDuration(long millis) {
/* 516 */     long totalSeconds = millis / 1000L;
/* 517 */     long hours = totalSeconds / 3600L;
/* 518 */     long minutes = totalSeconds % 3600L / 60L;
/* 519 */     long seconds = totalSeconds % 60L;
/* 520 */     if (hours > 0L) return String.format("%dh %02dm", new Object[] { Long.valueOf(hours), Long.valueOf(minutes) }); 
/* 521 */     return String.format("%02dm %02ds", new Object[] { Long.valueOf(minutes), Long.valueOf(seconds) });
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   private String truncate(@NotNull String text, int max) {
/* 526 */     if (text == null) return ""; 
/* 527 */     return (text.length() <= max) ? text : (text.substring(0, Math.max(0, max - 1)) + "…");
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   private String fitText(@NotNull String text, int maxWidth) {
/* 532 */     if (this.field_22793.method_1727(text) <= maxWidth) return text; 
/* 533 */     int ellipsisW = this.field_22793.method_1727("…");
/* 534 */     int end = text.length();
/* 535 */     for (; end > 0 && this.field_22793.method_1727(text.substring(0, end)) + ellipsisW > maxWidth; end--);
/* 536 */     return text.substring(0, Math.max(0, end)) + "…";
/*     */   }
/*     */   
/*     */   private int colorForChance(int chance) {
/* 540 */     if (chance >= 75) return -8396670; 
/* 541 */     if (chance >= 50) return -800918; 
/* 542 */     return -30070;
/*     */   }
/*     */   
/*     */   private void drawExpBar(@NotNull class_332 context, int x, int y, int w, int h, int fillW) {
/* 546 */     context.method_25294(x, y, x + w, y + h, -12498346);
/* 547 */     context.method_25292(x, x + w - 1, y, -1379841);
/* 548 */     context.method_25292(x, x + w - 2, y + 1, -8747885);
/* 549 */     context.method_25292(x, x + w - 1, y + h, -16250352);
/* 550 */     context.method_25301(x, y, y + h, -1379841);
/* 551 */     context.method_25301(x + w - 1, y, y + h, -16250352);
/*     */     
/* 553 */     int filledBody = Math.max(0, Math.min(w - 2, fillW));
/* 554 */     if (filledBody > 0) {
/* 555 */       context.method_25296(x + 1, y + 1, x + 1 + filledBody, y + h - 1, -9181463, -11889153);
/* 556 */       context.method_25292(x + 1, x + filledBody, y + 1, -6094849);
/* 557 */       if (filledBody >= 3) {
/* 558 */         context.method_25292(x + 1, x + filledBody - 1, y + h - 2, -13994033);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private int withAlpha(int color, int alpha) {
/* 564 */     return (alpha & 0xFF) << 24 | color & 0xFFFFFF;
/*     */   }
/*     */   
/*     */   private int brighten(int color) {
/* 568 */     int a = color >>> 24 & 0xFF;
/* 569 */     int r = Math.min(255, (color >> 16 & 0xFF) + 20);
/* 570 */     int g = Math.min(255, (color >> 8 & 0xFF) + 20);
/* 571 */     int b = Math.min(255, (color & 0xFF) + 20);
/* 572 */     return a << 24 | r << 16 | g << 8 | b;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawSmallText(@NotNull class_332 context, @NotNull String text, float x, float y, int color, int maxWidthPx, boolean centered) {
/* 582 */     int maxCharWidth = (int)(maxWidthPx / 0.5F);
/* 583 */     RenderHelperKt.drawScaledText(context, null, 
/* 584 */         class_2561.method_43470(text), 
/* 585 */         Float.valueOf(x), Float.valueOf(y), 0.5F, 
/* 586 */         Float.valueOf(1.0F), maxCharWidth, color, centered, true, null, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawCenteredSmallText(@NotNull class_332 context, @NotNull String text, int centerX, int y, int color) {
/* 596 */     int pixelW = (int)(this.field_22793.method_1727(text) * 0.5F);
/* 597 */     drawSmallText(context, text, centerX - pixelW / 2.0F, y, color, pixelW + 8, false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static ElementalType elementalTypeFor(@NotNull String typeName) {
/*     */     try {
/* 605 */       return ElementalTypes.get(typeName.toLowerCase(Locale.ROOT));
/* 606 */     } catch (Throwable t) {
/* 607 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static TypeIcon typeIconFor(int x, int y, @NotNull String typeName, boolean small) {
/* 613 */     ElementalType type = elementalTypeFor(typeName);
/* 614 */     if (type == null) return null; 
/* 615 */     return new TypeIcon(Integer.valueOf(x), Integer.valueOf(y), type, null, false, small, 15.0F, 7.5F, 1.0F);
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\safari\expedition\screen\SafariExpeditionScreen.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */