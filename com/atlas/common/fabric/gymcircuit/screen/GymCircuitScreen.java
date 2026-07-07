/*     */ package com.atlas.common.fabric.gymcircuit.screen;
/*     */ 
/*     */ import com.atlas.common.fabric.gymcircuit.network.GymCircuitNetwork;
/*     */ import com.atlas.common.fabric.ranked.screen.MiscellaneousFontIcon;
/*     */ import com.cobblemon.mod.common.api.gui.GuiUtilsKt;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import net.minecraft.class_1109;
/*     */ import net.minecraft.class_1113;
/*     */ import net.minecraft.class_124;
/*     */ import net.minecraft.class_2561;
/*     */ import net.minecraft.class_2960;
/*     */ import net.minecraft.class_310;
/*     */ import net.minecraft.class_332;
/*     */ import net.minecraft.class_3417;
/*     */ import net.minecraft.class_437;
/*     */ import net.minecraft.class_5250;
/*     */ import net.minecraft.class_5348;
/*     */ import net.minecraft.class_5481;
/*     */ import net.minecraft.class_6880;
/*     */ 
/*     */ 
/*     */ public final class GymCircuitScreen
/*     */   extends class_437
/*     */ {
/*     */   private static final String NS = "atlas-common-fabric";
/*     */   private static final int WIDTH = 540;
/*     */   private static final int HEIGHT = 364;
/*  30 */   private static final class_2960 MAIN_BG = tex("main/gym_circuit_bg_boxes.png");
/*  31 */   private static final class_2960 MAIN_TITLE = tex("main/top_empty.png");
/*  32 */   private static final class_2960 MAIN_TITLE_TEXT = tex("main/gym_circuit.png");
/*  33 */   private static final class_2960 MAIN_CLOSE = tex("main/close_button.png");
/*  34 */   private static final class_2960 MAIN_CLOSE_PRESS = tex("main/close_button_press.png");
/*  35 */   private static final class_2960 PREVIEW_BG = tex("preview/gym_circuit_bg.png");
/*  36 */   private static final class_2960 PREVIEW_TITLE = tex("preview/top_empty.png");
/*  37 */   private static final class_2960 PREVIEW_TITLE_TEXT = tex("preview/circuit_reward_preview.png");
/*  38 */   private static final class_2960 PREVIEW_BOXES = tex("preview/bg_boxes.png");
/*  39 */   private static final class_2960 PREVIEW_BACK = tex("preview/back_button.png");
/*  40 */   private static final class_2960 PREVIEW_BACK_PRESS = tex("preview/back_button_press.png");
/*  41 */   private static final class_2960 PREVIEW_SCROLLBAR = tex("preview/scrollbar.png");
/*  42 */   private static final class_2960 PREVIEW_SCROLLBAR_BG = tex("preview/scrollbar_bg.png");
/*  43 */   private static final class_2960 REWARDS_PREVIEW = tex("rewards/preview_button.png");
/*  44 */   private static final class_2960 REWARDS_PREVIEW_PRESS = tex("rewards/preview_button_press.png");
/*     */   
/*     */   private static final int TITLE_X = 11;
/*     */   
/*     */   private static final int TITLE_Y = 11;
/*     */   
/*     */   private static final int BOXES_X = 11;
/*     */   
/*     */   private static final int BOXES_Y = 53;
/*     */   
/*     */   private static final int HEADER_TEXT_X = 18;
/*     */   
/*     */   private static final int HEADER_TEXT_Y = 29;
/*     */   
/*     */   private static final int MAIN_TITLE_TEXT_X = 18;
/*     */   
/*     */   private static final int MAIN_TITLE_TEXT_Y = 16;
/*     */   
/*     */   private static final int PREVIEW_TITLE_TEXT_X = 18;
/*     */   private static final int PREVIEW_TITLE_TEXT_Y = 16;
/*     */   private static final int CLOSE_X = 491;
/*     */   private static final int CLOSE_Y = 18;
/*     */   private static final int CLOSE_W = 31;
/*     */   private static final int CLOSE_H = 20;
/*     */   private static final int PREVIEW_BACK_X = 497;
/*     */   private static final int PREVIEW_BACK_Y = 18;
/*     */   private static final int PREVIEW_BACK_W = 26;
/*     */   private static final int PREVIEW_BACK_H = 20;
/*     */   private static final int STATUS_X = 13;
/*     */   private static final int STATUS_Y = 69;
/*     */   private static final int STATUS_W = 136;
/*     */   private static final int NEXT_X = 22;
/*     */   private static final int NEXT_Y = 231;
/*     */   private static final int NEXT_W = 136;
/*     */   private static final int GYM_X = 155;
/*     */   private static final int GYM_Y = 54;
/*     */   private static final int GYM_W = 228;
/*     */   private static final int REWARDS_X = 155;
/*     */   private static final int REWARDS_Y = 262;
/*     */   private static final int REWARDS_W = 228;
/*     */   private static final int RESTRICTIONS_X = 389;
/*     */   private static final int RESTRICTIONS_Y = 73;
/*     */   private static final int RESTRICTIONS_W = 134;
/*     */   private static final int STAT_ROW = 16;
/*     */   private static final int WRAP_LINE = 11;
/*     */   private static final int BADGE_GRID_COLUMNS = 4;
/*     */   private static final int BADGE_SLOT_W = 55;
/*     */   private static final int BADGE_SLOT_H = 40;
/*     */   private static final int BADGE_SLOT_STEP_X = 55;
/*     */   private static final int BADGE_SLOT_STEP_Y = 47;
/*     */   private static final int BADGE_GRID_X = 159;
/*     */   private static final int BADGE_GRID_Y = 75;
/*     */   private static final int LEAGUE_SLOT_W = 38;
/*     */   private static final int LEAGUE_SLOT_H = 16;
/*     */   private static final int LEAGUE_SLOT_GAP = 7;
/*     */   private static final int PREVIEW_BUTTON_X = 318;
/*     */   private static final int PREVIEW_BUTTON_Y = 265;
/*     */   private static final int PREVIEW_BUTTON_W = 59;
/*     */   private static final int PREVIEW_BUTTON_H = 16;
/*     */   private static final int PREVIEW_ROWS_X = 23;
/*     */   private static final int PREVIEW_ROWS_Y = 101;
/*     */   private static final int PREVIEW_ROWS_W = 482;
/*     */   private static final int PREVIEW_ROWS_H = 250;
/*     */   private static final int PREVIEW_HEADER_Y = 78;
/*     */   private static final int PREVIEW_SCROLL_X = 515;
/*     */   private static final int PREVIEW_SCROLL_Y = 101;
/*     */   private static final int PREVIEW_SCROLL_H = 251;
/*     */   private static final int TOOLTIP_LABEL_X = 15;
/*     */   private static final int TOOLTIP_VALUE_X = 96;
/* 113 */   private static final int BG = color(11, 13, 18, 238);
/* 114 */   private static final int PANEL = color(21, 25, 32, 245);
/* 115 */   private static final int PANEL_DARK = color(15, 18, 24, 245);
/* 116 */   private static final int BORDER = color(67, 82, 107, 255);
/* 117 */   private static final int BORDER_BRIGHT = color(238, 189, 77, 255);
/* 118 */   private static final int WHITE = color(255, 255, 255, 255);
/*     */   private static final int POKE_COIN = -281343;
/*     */   private static final int POKE_GEM = -14746869;
/* 121 */   private static final int TEXT = color(240, 244, 248, 255);
/* 122 */   private static final int MUTED = color(169, 179, 193, 255);
/* 123 */   private static final int DIM = color(103, 113, 129, 255);
/* 124 */   private static final int GOOD = color(92, 214, 147, 255);
/* 125 */   private static final int WARN = color(246, 190, 84, 255);
/* 126 */   private static final int BAD = color(225, 92, 92, 255);
/* 127 */   private static final int BLUE = color(83, 174, 231, 255);
/* 128 */   private static final int CYAN = color(88, 218, 218, 255);
/* 129 */   private static final int LIME = color(139, 224, 87, 255);
/* 130 */   private static final int PINK = color(236, 119, 209, 255);
/* 131 */   private static final int PURPLE = color(177, 136, 255, 255);
/* 132 */   private static final int ORANGE = color(255, 142, 73, 255);
/*     */   
/*     */   private final GymCircuitNetwork.OpenCircuitPayload data;
/*     */   private int left;
/*     */   private int top;
/*     */   private boolean closeHovered;
/*     */   private boolean rewardPreviewHovered;
/*     */   private boolean rewardPreviewOpen;
/*     */   private boolean rewardPreviewCloseHovered;
/*     */   private boolean lastCloseHovered;
/*     */   private boolean lastRewardPreviewHovered;
/*     */   private boolean lastRewardPreviewCloseHovered;
/*     */   private int rewardPreviewScroll;
/*     */   
/*     */   public GymCircuitScreen(GymCircuitNetwork.OpenCircuitPayload data) {
/* 147 */     super((class_2561)class_2561.method_43470("Gym Circuit"));
/* 148 */     this.data = data;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void method_25426() {
/* 153 */     super.method_25426();
/* 154 */     this.left = (this.field_22789 - 540) / 2;
/* 155 */     this.top = Math.max(4, (this.field_22790 - 364) / 2);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void method_25394(class_332 context, int mouseX, int mouseY, float delta) {
/* 161 */     context.method_25294(0, 0, this.field_22789, this.field_22790, color(0, 0, 0, 180));
/*     */     
/* 163 */     context.method_51448().method_22903();
/* 164 */     context.method_51448().method_46416(0.0F, 0.0F, 200.0F);
/* 165 */     drawTex(context, MAIN_BG, this.left, this.top, 540, 364);
/* 166 */     drawTex(context, MAIN_TITLE, this.left + 11, this.top + 11, 518, 32);
/* 167 */     drawTex(context, MAIN_TITLE_TEXT, this.left + 18, this.top + 16, 69, 7);
/* 168 */     drawHeader(context, mouseX, mouseY);
/* 169 */     drawSummary(context);
/* 170 */     drawBadgePath(context, mouseX, mouseY);
/* 171 */     drawRewards(context, mouseX, mouseY);
/* 172 */     drawRestrictions(context);
/* 173 */     updateHoverSound(this.closeHovered, this.lastCloseHovered);
/* 174 */     updateHoverSound(this.rewardPreviewHovered, this.lastRewardPreviewHovered);
/* 175 */     this.lastCloseHovered = this.closeHovered;
/* 176 */     this.lastRewardPreviewHovered = this.rewardPreviewHovered;
/* 177 */     context.method_51448().method_22909();
/*     */     
/* 179 */     if (this.rewardPreviewOpen) {
/* 180 */       context.method_51448().method_22903();
/* 181 */       context.method_51448().method_46416(0.0F, 0.0F, 1000.0F);
/* 182 */       drawRewardPreviewOverlay(context, mouseX, mouseY);
/* 183 */       updateHoverSound(this.rewardPreviewCloseHovered, this.lastRewardPreviewCloseHovered);
/* 184 */       this.lastRewardPreviewCloseHovered = this.rewardPreviewCloseHovered;
/* 185 */       context.method_51448().method_22909();
/*     */     } else {
/* 187 */       this.rewardPreviewCloseHovered = false;
/* 188 */       this.lastRewardPreviewCloseHovered = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void method_25420(class_332 context, int mouseX, int mouseY, float delta) {}
/*     */ 
/*     */   
/*     */   private void drawHeader(class_332 context, int mouseX, int mouseY) {
/* 198 */     int closeX = this.left + 491;
/* 199 */     int closeY = this.top + 18;
/* 200 */     this.closeHovered = (mouseX >= closeX && mouseX < closeX + 31 && mouseY >= closeY && mouseY < closeY + 20);
/* 201 */     drawTex(context, this.closeHovered ? MAIN_CLOSE_PRESS : MAIN_CLOSE, closeX, closeY, 31, 20);
/* 202 */     drawHeaderDescription(context, "Earn badges in order, then clear the Elite 4.", 491);
/*     */   }
/*     */   
/*     */   private void drawSummary(class_332 context) {
/* 206 */     int sx = this.left + 13;
/* 207 */     int sy = this.top + 69;
/* 208 */     drawStat(context, sx, 136, sy, "Current", "Circuit " + this.data.currentCircuit(), WARN);
/* 209 */     drawStat(context, sx, 136, sy + 16, "Completed", String.valueOf(this.data.completedCircuits()), GOOD);
/* 210 */     drawStat(context, sx, 136, sy + 32, "Badges", "" + this.data.badgeCount() + "/" + this.data.badgeCount(), BLUE);
/* 211 */     drawStat(context, sx, 136, sy + 48, "League", "" + completedLeagueCount() + "/" + completedLeagueCount(), BLUE);
/*     */ 
/*     */     
/* 214 */     String state = this.data.repeatUnlocked() ? (this.data.badgeSetConsumed() ? "Ready to restart" : "Circuit active") : "First clear needed";
/* 215 */     drawStat(context, sx, 136, sy + 64, "State", state, this.data.repeatUnlocked() ? GOOD : WARN);
/*     */     
/* 217 */     drawWrapped(context, this.data.nextObjective(), this.left + 22, this.top + 231, 136, MUTED, 8);
/*     */   }
/*     */   
/*     */   private void drawLeaguePath(class_332 context, int x, int y, int w) {
/* 221 */     if (this.data.league().isEmpty()) {
/*     */       return;
/*     */     }
/*     */     
/* 225 */     int cellW = 38;
/* 226 */     int rowY = y;
/* 227 */     boolean previousCompleted = true;
/* 228 */     boolean leagueUnlocked = (this.data.badgeCount() >= this.data.totalBadges());
/* 229 */     for (int i = 0; i < this.data.league().size(); i++) {
/* 230 */       GymCircuitNetwork.LeagueEntry entry = this.data.league().get(i);
/* 231 */       int lx = x + i * (cellW + 7);
/* 232 */       boolean current = (leagueUnlocked && !entry.completed() && previousCompleted);
/* 233 */       drawTex(context, leagueSlotTexture(entry, i, current), lx, rowY, 38, 16);
/* 234 */       previousCompleted = (previousCompleted && entry.completed());
/*     */     } 
/*     */   }
/*     */   
/*     */   private void drawBadgePath(class_332 context, int mouseX, int mouseY) {
/* 239 */     for (int i = 0; i < this.data.badges().size(); i++) {
/* 240 */       GymCircuitNetwork.BadgeEntry badge = this.data.badges().get(i);
/* 241 */       int bx = this.left + 159 + i % 4 * 55;
/* 242 */       int by = this.top + 75 + i / 4 * 47;
/* 243 */       boolean hover = (mouseX >= bx && mouseX < bx + 55 && mouseY >= by && mouseY < by + 40);
/*     */       
/* 245 */       drawTex(context, badgeSlotTexture(badge, hover), bx, by, 55, 40);
/* 246 */       if (hover) {
/* 247 */         List<class_2561> tooltip = new ArrayList<>();
/* 248 */         tooltip.add(class_2561.method_43470(badge.name()).method_27692(badge.earned() ? class_124.field_1060 : class_124.field_1080));
/* 249 */         tooltip.add(class_2561.method_43470(badge.earned() ? "Earned this circuit" : "Not earned yet").method_27692(class_124.field_1080));
/* 250 */         context.method_51434(this.field_22793, tooltip, mouseX, mouseY);
/*     */       } 
/*     */     } 
/*     */     
/* 254 */     drawLeaguePath(context, this.left + 155 + 5, this.top + 190, 218);
/*     */   }
/*     */   
/*     */   private class_2960 badgeSlotTexture(GymCircuitNetwork.BadgeEntry badge, boolean hover) {
/* 258 */     String name, id = (badge.id() == null) ? "" : badge.id().toLowerCase(Locale.ROOT);
/*     */     
/* 260 */     if (id.contains("combat")) { name = "fighting"; }
/* 261 */     else if (id.contains("electric")) { name = "electric"; }
/* 262 */     else if (id.contains("fire")) { name = "fire"; }
/* 263 */     else if (id.contains("grass")) { name = "grass"; }
/* 264 */     else if (id.contains("psychic")) { name = "psychic"; }
/* 265 */     else if (id.contains("radiant")) { name = "steel"; }
/* 266 */     else if (id.contains("water")) { name = "water"; }
/* 267 */     else { name = "rock"; }
/*     */     
/* 269 */     String suffix = badge.earned() ? "_select" : (hover ? "_hover" : "");
/* 270 */     return tex("slots/" + name + suffix + ".png");
/*     */   }
/*     */   
/*     */   private class_2960 leagueSlotTexture(GymCircuitNetwork.LeagueEntry entry, int index, boolean current) {
/* 274 */     String name = entry.champion() ? "c" : String.valueOf(index + 1);
/* 275 */     String suffix = entry.completed() ? "_green" : (current ? "_orange" : "");
/* 276 */     return tex("slots/1234c/" + name + suffix + ".png");
/*     */   }
/*     */   
/*     */   private void drawRewards(class_332 context, int mouseX, int mouseY) {
/* 280 */     int x = this.left + 155;
/* 281 */     int y = this.top + 262;
/* 282 */     this.rewardPreviewHovered = (mouseX >= this.left + 318 && mouseX < this.left + 318 + 59 && mouseY >= this.top + 265 && mouseY < this.top + 265 + 16);
/*     */     
/* 284 */     drawPreviewButton(context, this.left + 318, this.top + 265, this.rewardPreviewHovered);
/*     */     
/* 286 */     int rowY = y + 22;
/* 287 */     for (GymCircuitNetwork.RewardEntry reward : this.data.rewards()) {
/* 288 */       if (rowY + 14 > this.top + 350)
/* 289 */         break;  int color = reward.milestone() ? WARN : MUTED;
/* 290 */       context.method_51433(this.field_22793, reward.label(), x + 8, rowY + 1, color, false);
/* 291 */       if (reward.label().equals("Coins")) {
/* 292 */         drawRightCurrencyValue(context, MiscellaneousFontIcon.COIN.draw(), reward.value(), x + 228 - 12, rowY + 1, -281343);
/* 293 */       } else if (reward.label().equals("PokeGems")) {
/* 294 */         drawRightCurrencyValue(context, MiscellaneousFontIcon.GEM.draw(), reward.value(), x + 228 - 12, rowY + 1, -14746869);
/*     */       } else {
/* 296 */         drawRightText(context, truncate(reward.value(), 146), x + 228 - 12, rowY + 1, rewardTextColor(reward.value(), TEXT), false);
/*     */       } 
/* 298 */       rowY += 16;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void drawRewardPreviewOverlay(class_332 context, int mouseX, int mouseY) {
/* 303 */     drawTex(context, PREVIEW_BG, this.left, this.top, 540, 364);
/* 304 */     drawTex(context, PREVIEW_TITLE, this.left + 11, this.top + 11, 518, 32);
/* 305 */     drawTex(context, PREVIEW_TITLE_TEXT, this.left + 18, this.top + 16, 141, 7);
/* 306 */     drawTex(context, PREVIEW_BOXES, this.left + 11, this.top + 53, 518, 300);
/*     */     
/* 308 */     int closeX = this.left + 497;
/* 309 */     int closeY = this.top + 18;
/* 310 */     this.rewardPreviewCloseHovered = (mouseX >= closeX && mouseX < closeX + 26 && mouseY >= closeY && mouseY < closeY + 20);
/* 311 */     drawTex(context, this.rewardPreviewCloseHovered ? PREVIEW_BACK_PRESS : PREVIEW_BACK, closeX, closeY, 26, 20);
/*     */     
/* 313 */     drawHeaderDescription(context, "Preview the rewards paid when a full Gym Circuit is completed.", 497);
/* 314 */     context.method_51433(this.field_22793, "Every circuit gives coins, PokeGems and 1x Mega Stone Randomizer.", this.left + 23, this.top + 62, MUTED, false);
/*     */     
/* 316 */     drawRewardPreviewHeader(context, this.left + 23, this.top + 78, 482);
/*     */     
/* 318 */     int listX = this.left + 23;
/* 319 */     int listY = this.top + 101;
/* 320 */     int listW = 482;
/* 321 */     int listH = 250;
/*     */     
/* 323 */     int rowH = 16;
/* 324 */     int visibleRows = Math.max(1, listH / rowH);
/* 325 */     int maxScroll = Math.max(0, this.data.rewardPreview().size() - visibleRows);
/* 326 */     this.rewardPreviewScroll = Math.max(0, Math.min(this.rewardPreviewScroll, maxScroll));
/* 327 */     int rowY = listY;
/* 328 */     GymCircuitNetwork.RewardPreviewEntry hoveredReward = null;
/* 329 */     for (int index = this.rewardPreviewScroll; index < this.data.rewardPreview().size() && rowY + rowH <= listY + listH; index++) {
/* 330 */       GymCircuitNetwork.RewardPreviewEntry entry = this.data.rewardPreview().get(index);
/* 331 */       if (drawRewardPreviewRow(context, entry, listX, rowY, listW, rowH, mouseX, mouseY)) {
/* 332 */         hoveredReward = entry;
/*     */       }
/* 334 */       rowY += rowH;
/*     */     } 
/*     */     
/* 337 */     if (maxScroll > 0) {
/* 338 */       int trackX = this.left + 515;
/* 339 */       int trackY = this.top + 101;
/* 340 */       int trackH = 251;
/* 341 */       int thumbH = Math.max(22, trackH * visibleRows / this.data.rewardPreview().size());
/* 342 */       int thumbY = trackY + (trackH - thumbH) * this.rewardPreviewScroll / maxScroll;
/* 343 */       drawTex(context, PREVIEW_SCROLLBAR_BG, trackX + 1, trackY, 4, trackH);
/* 344 */       drawTex(context, PREVIEW_SCROLLBAR, trackX, thumbY, 6, thumbH);
/*     */     } 
/*     */     
/* 347 */     if (hoveredReward != null) {
/* 348 */       drawRewardPreviewTooltip(context, hoveredReward, mouseX, mouseY);
/*     */     }
/*     */   }
/*     */   
/*     */   private void drawRewardPreviewHeader(class_332 context, int x, int y, int w) {
/* 353 */     context.method_51433(this.field_22793, "Circuit", x + 9, y + 4, MUTED, false);
/* 354 */     drawCurrencyHeader(context, MiscellaneousFontIcon.COIN.draw(), "Coins", x + 72, y + 4, -281343);
/* 355 */     drawCurrencyHeader(context, MiscellaneousFontIcon.GEM.draw(), "Gems", x + 136, y + 4, -14746869);
/* 356 */     context.method_51433(this.field_22793, "Bonus / Milestone", x + 198, y + 4, MUTED, false);
/*     */   }
/*     */   
/*     */   private boolean drawRewardPreviewRow(class_332 context, GymCircuitNetwork.RewardPreviewEntry entry, int x, int y, int w, int h, int mouseX, int mouseY) {
/* 360 */     boolean hover = (mouseX >= x && mouseX < x + w && mouseY >= y && mouseY < y + h);
/*     */ 
/*     */     
/* 363 */     int bg = entry.current() ? color(45, 38, 24, 255) : (entry.milestoneCircuit() ? color(28, 34, 28, 255) : color(17, 21, 28, 255));
/* 364 */     context.method_25294(x + 1, y, x + w - 1, y + h - 1, hover ? color(48, 62, 84, 160) : bg);
/* 365 */     if (entry.current()) {
/* 366 */       context.method_25294(x + 1, y, x + 3, y + h - 1, WARN);
/* 367 */     } else if (entry.milestoneCircuit()) {
/* 368 */       context.method_25294(x + 1, y, x + 3, y + h - 1, GOOD);
/*     */     } 
/*     */     
/* 371 */     int textColor = entry.current() ? TEXT : MUTED;
/* 372 */     context.method_51433(this.field_22793, String.valueOf(entry.circuit()), x + 18, y + 4, textColor, false);
/* 373 */     drawRightCurrencyValue(context, MiscellaneousFontIcon.COIN.draw(), entry.coins(), x + 116, y + 4, -281343);
/* 374 */     drawRightCurrencyValue(context, MiscellaneousFontIcon.GEM.draw(), entry.gems(), x + 178, y + 4, -14746869);
/* 375 */     String details = entry.bonus() + entry.bonus();
/* 376 */     context.method_51433(this.field_22793, truncate(details, w - 204), x + 198, y + 4, entry.milestoneCircuit() ? WARN : textColor, false);
/*     */     
/* 378 */     return hover;
/*     */   }
/*     */   
/*     */   private void drawRewardPreviewTooltip(class_332 context, GymCircuitNetwork.RewardPreviewEntry entry, int mouseX, int mouseY) {
/* 382 */     int padding = 7;
/* 383 */     int rowH = 11;
/* 384 */     int labelW = 58;
/* 385 */     int valueW = 178;
/* 386 */     int tooltipW = 258;
/*     */ 
/*     */     
/* 389 */     List<String> milestoneLines = entry.milestone().equals("-") ? List.<String>of("No milestone reward") : wrapRewardTooltip(entry.milestone(), 236);
/* 390 */     int tooltipH = 80 + milestoneLines.size() * 11;
/*     */     
/* 392 */     int tx = mouseX + 12;
/* 393 */     int ty = mouseY + 10;
/* 394 */     if (tx + 258 > this.field_22789 - 4) tx = mouseX - 258 - 12; 
/* 395 */     if (ty + tooltipH > this.field_22790 - 4) ty = this.field_22790 - tooltipH - 4; 
/* 396 */     if (ty < 4) ty = 4;
/*     */     
/* 398 */     context.method_51448().method_22903();
/* 399 */     context.method_51448().method_46416(0.0F, 0.0F, 80.0F);
/* 400 */     context.method_25294(tx - 1, ty - 1, tx + 258 + 1, ty + tooltipH + 1, color(238, 189, 77, 255));
/* 401 */     context.method_25294(tx, ty, tx + 258, ty + tooltipH, color(12, 15, 21, 252));
/* 402 */     context.method_25294(tx + 1, ty + 1, tx + 258 - 1, ty + 2, color(255, 255, 255, 22));
/*     */     
/* 404 */     int y = ty + 7;
/* 405 */     context.method_51433(this.field_22793, "Circuit " + entry.circuit(), tx + 7, y, entry.current() ? WARN : TEXT, true);
/* 406 */     String tag = entry.current() ? "Current" : (entry.milestoneCircuit() ? "Milestone" : "Preview");
/* 407 */     int tagColor = entry.current() ? WARN : (entry.milestoneCircuit() ? GOOD : MUTED);
/* 408 */     drawRightText(context, tag, tx + 258 - 7, y, tagColor, false);
/* 409 */     y += 13;
/*     */     
/* 411 */     context.method_25294(tx + 7, y, tx + 258 - 7, y + 1, color(67, 82, 107, 255));
/* 412 */     y += 5;
/*     */     
/* 414 */     drawRewardTooltipCurrencyRow(context, tx + 7, y, MiscellaneousFontIcon.COIN.draw(), "PokeCoins", entry.coins(), -281343);
/* 415 */     y += 11;
/* 416 */     drawRewardTooltipCurrencyRow(context, tx + 7, y, MiscellaneousFontIcon.GEM.draw(), "PokeGems", entry.gems(), -14746869);
/* 417 */     y += 11;
/* 418 */     drawRewardTooltipRow(context, tx + 7, y, "Bonus", entry.bonus(), MUTED, rewardTextColor(entry.bonus(), TEXT));
/* 419 */     y += 16;
/*     */     
/* 421 */     context.method_51433(this.field_22793, "Milestone Rewards", tx + 7, y, entry.milestone().equals("-") ? DIM : WARN, false);
/* 422 */     y += 12;
/* 423 */     for (String line : milestoneLines) {
/* 424 */       drawRewardTooltipRewardLine(context, line, tx + 7 + 4, y, entry.milestone().equals("-"));
/* 425 */       y += 11;
/*     */     } 
/* 427 */     context.method_51448().method_22909();
/*     */   }
/*     */   
/*     */   private void drawRewardTooltipRow(class_332 context, int x, int y, String label, String value, int labelColor, int valueColor) {
/* 431 */     context.method_51433(this.field_22793, label, x + 15, y, labelColor, false);
/* 432 */     context.method_51433(this.field_22793, value, x + 96, y, valueColor, false);
/*     */   }
/*     */   
/*     */   private void drawRewardTooltipCurrencyRow(class_332 context, int x, int y, class_5250 icon, String label, String value, int currencyColor) {
/* 436 */     String cleanValue = cleanCurrencyValue(value);
/* 437 */     context.method_51439(this.field_22793, (class_2561)icon, x, y, WHITE, false);
/* 438 */     context.method_51433(this.field_22793, label, x + 15, y, currencyColor, false);
/* 439 */     context.method_51433(this.field_22793, cleanValue, x + 96, y, currencyColor, false);
/*     */   }
/*     */   
/*     */   private void drawRewardTooltipRewardLine(class_332 context, String line, int x, int y, boolean dimmed) {
/* 443 */     if (dimmed) {
/* 444 */       context.method_51433(this.field_22793, line, x, y, DIM, false);
/*     */       
/*     */       return;
/*     */     } 
/* 448 */     int drawX = x;
/* 449 */     String[] rewards = line.split(", ");
/* 450 */     for (int i = 0; i < rewards.length; i++) {
/* 451 */       if (i > 0) {
/* 452 */         context.method_51433(this.field_22793, ", ", drawX, y, DIM, false);
/* 453 */         drawX += this.field_22793.method_1727(", ");
/*     */       } 
/*     */       
/* 456 */       String reward = rewards[i];
/* 457 */       context.method_51433(this.field_22793, reward, drawX, y, rewardTextColor(reward, MUTED), false);
/* 458 */       drawX += this.field_22793.method_1727(reward);
/*     */     } 
/*     */   }
/*     */   
/*     */   private int rewardTextColor(String reward, int fallback) {
/* 463 */     String lower = reward.toLowerCase(Locale.ROOT);
/* 464 */     if (lower.contains("pokegem")) return -14746869; 
/* 465 */     if (lower.contains("coin")) return -281343; 
/* 466 */     if (lower.contains("pokespinner")) return PINK; 
/* 467 */     if (lower.contains("spinner")) return LIME; 
/* 468 */     if (lower.contains("master ball")) return PURPLE; 
/* 469 */     if (lower.contains("premier key")) return WHITE; 
/* 470 */     if (lower.contains("ultra key")) return ORANGE; 
/* 471 */     if (lower.contains("victory key")) return WARN; 
/* 472 */     if (lower.contains("key")) return CYAN; 
/* 473 */     if (lower.contains("iv reroll")) return CYAN; 
/* 474 */     if (lower.contains("sizeup")) return GOOD; 
/* 475 */     if (lower.contains("sizedown")) return BAD; 
/* 476 */     if (lower.contains("mega stone")) return PURPLE; 
/* 477 */     if (lower.contains("randomizer")) return PURPLE; 
/* 478 */     return fallback;
/*     */   }
/*     */   
/*     */   private void drawCurrencyHeader(class_332 context, class_5250 icon, String label, int x, int y, int color) {
/* 482 */     context.method_51439(this.field_22793, (class_2561)icon, x, y, WHITE, false);
/* 483 */     context.method_51433(this.field_22793, label, x + this.field_22793.method_27525((class_5348)icon) + 3, y, color, false);
/*     */   }
/*     */   
/*     */   private void drawRestrictions(class_332 context) {
/* 487 */     int x = this.left + 389;
/* 488 */     int y = this.top + 73;
/* 489 */     int rowY = y;
/* 490 */     int rowH = 27;
/* 491 */     for (int i = 0; i < this.data.rules().size(); i++) {
/* 492 */       GymCircuitNetwork.RuleEntry rule = this.data.rules().get(i);
/* 493 */       int ry = rowY + i * rowH;
/* 494 */       if (ry + rowH > this.top + 349)
/* 495 */         break;  int color = rule.active() ? WARN : MUTED;
/* 496 */       context.method_51433(this.field_22793, truncate(rule.title(), 120), x + 10, ry, color, true);
/* 497 */       drawSmallText(context, truncate(rule.detail(), 240), x + 10, ry + 10, 
/* 498 */           rule.active() ? TEXT : MUTED);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void drawStat(class_332 context, int panelX, int panelW, int y, String label, String value, int valueColor) {
/* 503 */     context.method_51433(this.field_22793, label, panelX + 10, y, MUTED, false);
/* 504 */     drawRightText(context, value, panelX + panelW - 10, y, valueColor, true);
/*     */   }
/*     */   
/*     */   private void drawSectionTitle(class_332 context, String title, int x, int y) {
/* 508 */     context.method_51439(this.field_22793, (class_2561)class_2561.method_43470(title).method_27692(class_124.field_1067), x, y, TEXT, true);
/*     */   }
/*     */   
/*     */   private void drawPanel(class_332 context, int x, int y, int w, int h, int bg, int border) {
/* 512 */     context.method_25294(x, y, x + w, y + h, bg);
/* 513 */     context.method_49601(x, y, w, h, border);
/* 514 */     context.method_25294(x + 1, y + 1, x + w - 1, y + 2, color(255, 255, 255, 18));
/*     */   }
/*     */   
/*     */   private void drawTex(class_332 context, class_2960 texture, int x, int y, int w, int h) {
/* 518 */     GuiUtilsKt.blitk(context.method_51448(), texture, Integer.valueOf(x), Integer.valueOf(y), Integer.valueOf(h), Integer.valueOf(w), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(w), Integer.valueOf(h), Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(1), Float.valueOf(1.0F), true, 1.0F);
/*     */   }
/*     */   
/*     */   private void drawTex(class_332 context, class_2960 texture, int x, int y, int w, int h, int texW, int texH) {
/* 522 */     context.method_25293(texture, x, y, w, h, 0.0F, 0.0F, texW, texH, texW, texH);
/*     */   }
/*     */   
/*     */   private void drawPreviewButton(class_332 context, int x, int y, boolean hovered) {
/* 526 */     drawTex(context, hovered ? REWARDS_PREVIEW_PRESS : REWARDS_PREVIEW, x, y, 59, 16);
/*     */   }
/*     */   
/*     */   private void drawHeaderDescription(class_332 context, String text, int buttonOffset) {
/* 530 */     int maxWidth = buttonOffset - 18 - 12;
/* 531 */     context.method_51433(this.field_22793, truncate(text, maxWidth), this.left + 18, this.top + 29, MUTED, false);
/*     */   }
/*     */   
/*     */   private void drawRightText(class_332 context, String text, int rightX, int y, int color, boolean shadow) {
/* 535 */     context.method_51433(this.field_22793, text, rightX - this.field_22793.method_1727(text), y, color, shadow);
/*     */   }
/*     */   
/*     */   private void drawRightCurrencyValue(class_332 context, class_5250 icon, String value, int rightX, int y, int color) {
/* 539 */     String cleanValue = cleanCurrencyValue(value);
/* 540 */     int iconW = this.field_22793.method_27525((class_5348)icon);
/* 541 */     int valueW = this.field_22793.method_1727(cleanValue);
/* 542 */     int x = rightX - iconW - 3 - valueW;
/* 543 */     context.method_51439(this.field_22793, (class_2561)icon, x, y, WHITE, false);
/* 544 */     context.method_51433(this.field_22793, cleanValue, x + iconW + 3, y, color, false);
/*     */   }
/*     */   
/*     */   private String cleanCurrencyValue(String value) {
/* 548 */     if (value == null) return ""; 
/* 549 */     String clean = value.trim();
/* 550 */     while (clean.startsWith("$")) {
/* 551 */       clean = clean.substring(1).trim();
/*     */     }
/* 553 */     return clean;
/*     */   }
/*     */   
/*     */   private List<String> wrapRewardTooltip(String milestone, int maxWidth) {
/* 557 */     List<String> lines = new ArrayList<>();
/* 558 */     String current = "";
/* 559 */     for (String part : milestone.split(", ")) {
/* 560 */       String candidate = current.isEmpty() ? part : (current + ", " + current);
/* 561 */       if (!current.isEmpty() && this.field_22793.method_1727(candidate) > maxWidth) {
/* 562 */         lines.add(current);
/* 563 */         current = part;
/*     */       } else {
/* 565 */         current = candidate;
/*     */       } 
/*     */     } 
/* 568 */     if (!current.isEmpty()) {
/* 569 */       lines.add(current);
/*     */     }
/* 571 */     return lines;
/*     */   }
/*     */   
/*     */   private boolean drawSmallButton(class_332 context, int x, int y, int w, int h, String label, int mouseX, int mouseY, boolean enabled) {
/* 575 */     boolean hover = (enabled && mouseX >= x && mouseX < x + w && mouseY >= y && mouseY < y + h);
/* 576 */     int bg = !enabled ? color(34, 38, 46, 255) : (hover ? color(55, 66, 82, 255) : color(35, 42, 53, 255));
/* 577 */     int border = !enabled ? color(48, 54, 66, 255) : (hover ? BORDER_BRIGHT : BORDER);
/* 578 */     context.method_25294(x, y, x + w, y + h, bg);
/* 579 */     context.method_49601(x, y, w, h, border);
/* 580 */     context.method_25300(this.field_22793, label, x + w / 2, y + 3, enabled ? TEXT : DIM);
/* 581 */     return hover;
/*     */   }
/*     */   
/*     */   private void drawWrapped(class_332 context, String text, int x, int y, int maxWidth, int color, int maxLines) {
/* 585 */     List<class_5481> lines = this.field_22793.method_1728((class_5348)class_2561.method_43470(text), maxWidth);
/* 586 */     for (int i = 0; i < lines.size() && i < maxLines; i++) {
/* 587 */       context.method_51430(this.field_22793, lines.get(i), x, y + i * 11, color, false);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void drawSmallText(class_332 context, String text, int x, int y, int color) {
/* 593 */     drawScaledText(context, text, x, y, color, 0.5F);
/*     */   }
/*     */   
/*     */   private void drawScaledText(class_332 context, String text, int x, int y, int color, float scale) {
/* 597 */     context.method_51448().method_22903();
/* 598 */     context.method_51448().method_46416(x, y, 0.0F);
/* 599 */     context.method_51448().method_22905(scale, scale, 1.0F);
/* 600 */     context.method_51433(this.field_22793, text, 0, 0, color, false);
/* 601 */     context.method_51448().method_22909();
/*     */   }
/*     */   
/*     */   private void drawSmallCenteredText(class_332 context, String text, int centerX, int y, int color, int maxWidth) {
/* 605 */     float scale = 0.75F;
/* 606 */     String clipped = truncate(text, Math.round(maxWidth / 0.75F));
/* 607 */     int drawX = Math.round(centerX / 0.75F - this.field_22793.method_1727(clipped) / 2.0F);
/* 608 */     context.method_51448().method_22903();
/* 609 */     context.method_51448().method_22905(0.75F, 0.75F, 1.0F);
/* 610 */     context.method_51433(this.field_22793, clipped, drawX, Math.round(y / 0.75F), color, false);
/* 611 */     context.method_51448().method_22909();
/*     */   }
/*     */   
/*     */   private String shortBadgeName(String name) {
/* 615 */     return name.replace(" Badge", "");
/*     */   }
/*     */   
/*     */   private long completedLeagueCount() {
/* 619 */     return this.data.league().stream().filter(GymCircuitNetwork.LeagueEntry::completed).count();
/*     */   }
/*     */   
/*     */   private String truncate(String value, int maxWidth) {
/* 623 */     if (this.field_22793.method_1727(value) <= maxWidth) return value; 
/* 624 */     String result = value;
/* 625 */     while (result.length() > 1 && this.field_22793.method_1727(result + "...") > maxWidth) {
/* 626 */       result = result.substring(0, result.length() - 1);
/*     */     }
/* 628 */     return result + "...";
/*     */   }
/*     */   
/*     */   private void updateHoverSound(boolean hovered, boolean previousHovered) {
/* 632 */     if (hovered && !previousHovered) {
/* 633 */       class_310.method_1551().method_1483().method_4873(
/* 634 */           (class_1113)class_1109.method_47978((class_6880)class_3417.field_15015, 1.55F));
/*     */     }
/*     */   }
/*     */   
/*     */   private void playClickSound() {
/* 639 */     class_310.method_1551().method_1483().method_4873(
/* 640 */         (class_1113)class_1109.method_47978((class_6880)class_3417.field_15015, 1.0F));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean method_25402(double mouseX, double mouseY, int button) {
/* 645 */     if (button != 0) {
/* 646 */       return super.method_25402(mouseX, mouseY, button);
/*     */     }
/* 648 */     if (this.rewardPreviewOpen) {
/* 649 */       if (this.rewardPreviewCloseHovered) {
/* 650 */         playClickSound();
/* 651 */         this.rewardPreviewOpen = false;
/* 652 */         return true;
/*     */       } 
/* 654 */       return true;
/*     */     } 
/* 656 */     if (this.rewardPreviewHovered) {
/* 657 */       playClickSound();
/* 658 */       this.rewardPreviewOpen = true;
/* 659 */       this.rewardPreviewScroll = 0;
/* 660 */       return true;
/*     */     } 
/* 662 */     if (this.closeHovered) {
/* 663 */       playClickSound();
/* 664 */       method_25419();
/* 665 */       return true;
/*     */     } 
/* 667 */     return super.method_25402(mouseX, mouseY, button);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean method_25401(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
/* 672 */     if (this.rewardPreviewOpen) {
/* 673 */       int visibleRows = Math.max(1, 14);
/* 674 */       int maxScroll = Math.max(0, this.data.rewardPreview().size() - visibleRows);
/* 675 */       this.rewardPreviewScroll = Math.max(0, Math.min(maxScroll, this.rewardPreviewScroll - (int)Math.signum(verticalAmount) * 3));
/* 676 */       return true;
/*     */     } 
/* 678 */     return super.method_25401(mouseX, mouseY, horizontalAmount, verticalAmount);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean method_25421() {
/* 683 */     return false;
/*     */   }
/*     */   
/*     */   private static int color(int r, int g, int b, int a) {
/* 687 */     return a << 24 | r << 16 | g << 8 | b;
/*     */   }
/*     */   
/*     */   private static class_2960 tex(String path) {
/* 691 */     return class_2960.method_60655("atlas-common-fabric", "textures/gymcircuit/" + path);
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\gymcircuit\screen\GymCircuitScreen.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */