/*      */ package com.atlas.common.fabric.ranked.screen;
/*      */ 
/*      */ import com.atlas.common.fabric.ranked.data.PlayerRankedData;
/*      */ import com.atlas.common.fabric.ranked.data.RankedRank;
/*      */ import com.atlas.common.fabric.ranked.network.RankedNetwork;
/*      */ import com.mojang.authlib.GameProfile;
/*      */ import com.mojang.authlib.yggdrasil.ProfileResult;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collections;
/*      */ import java.util.HashMap;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.UUID;
/*      */ import java.util.concurrent.CompletableFuture;
/*      */ import java.util.concurrent.Executor;
/*      */ import net.minecraft.class_1109;
/*      */ import net.minecraft.class_1113;
/*      */ import net.minecraft.class_2561;
/*      */ import net.minecraft.class_2960;
/*      */ import net.minecraft.class_310;
/*      */ import net.minecraft.class_332;
/*      */ import net.minecraft.class_3417;
/*      */ import net.minecraft.class_437;
/*      */ import net.minecraft.class_640;
/*      */ import net.minecraft.class_6880;
/*      */ import net.minecraft.class_8685;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class RankedLeaderboardScreen
/*      */   extends class_437
/*      */ {
/*   38 */   private static final int PANEL_BG = RankedStyledButton.color(10, 12, 18, 255);
/*   39 */   private static final int SECTION_BG = RankedStyledButton.color(14, 16, 24, 255);
/*   40 */   private static final int BORDER_COLOR = RankedStyledButton.color(50, 60, 100, 255);
/*   41 */   private static final int BORDER_HIGHLIGHT = RankedStyledButton.color(80, 140, 220, 255);
/*   42 */   private static final int ACCENT_ORANGE = RankedStyledButton.color(80, 200, 220, 255);
/*   43 */   private static final int ACCENT_CYAN = RankedStyledButton.color(100, 160, 240, 255);
/*   44 */   private static final int ACCENT_GOLD = RankedStyledButton.color(255, 200, 80, 255);
/*   45 */   private static final int ACCENT_PURPLE = RankedStyledButton.color(200, 140, 255, 255);
/*   46 */   private static final int TEXT_PRIMARY = RankedStyledButton.color(220, 230, 245, 255);
/*   47 */   private static final int TEXT_SECONDARY = RankedStyledButton.color(160, 180, 210, 255);
/*   48 */   private static final int TEXT_DIM = RankedStyledButton.color(100, 120, 160, 255);
/*      */ 
/*      */   
/*   51 */   private static final int PODIUM_GOLD_BG = RankedStyledButton.color(255, 200, 80, 35);
/*   52 */   private static final int PODIUM_GOLD_BORDER = RankedStyledButton.color(255, 200, 80, 120);
/*   53 */   private static final int PODIUM_SILVER_BG = RankedStyledButton.color(192, 192, 210, 30);
/*   54 */   private static final int PODIUM_SILVER_BORDER = RankedStyledButton.color(192, 192, 210, 100);
/*   55 */   private static final int PODIUM_BRONZE_BG = RankedStyledButton.color(205, 140, 60, 25);
/*   56 */   private static final int PODIUM_BRONZE_BORDER = RankedStyledButton.color(205, 140, 60, 90);
/*   57 */   private static final int COLOR_GOLD = RankedStyledButton.color(255, 215, 0, 255);
/*   58 */   private static final int COLOR_SILVER = RankedStyledButton.color(192, 200, 220, 255);
/*   59 */   private static final int COLOR_BRONZE = RankedStyledButton.color(205, 140, 60, 255);
/*      */ 
/*      */   
/*   62 */   private static final int ROW_HOVER_BG = RankedStyledButton.color(80, 200, 220, 30);
/*      */   
/*   64 */   private static final class_2960 DEFAULT_SKIN = class_2960.method_60655("minecraft", "textures/entity/player/wide/steve.png");
/*      */   
/*      */   private static final int GUI_WIDTH = 390;
/*      */   
/*      */   private static final int GUI_HEIGHT = 300;
/*      */   
/*      */   private static final int FACE_SIZE_STANDARD = 14;
/*      */   
/*      */   private static final int ENTRY_H_STANDARD = 18;
/*      */   
/*      */   private static final int FACE_SIZE_FIRST = 20;
/*      */   
/*      */   private static final int ENTRY_H_FIRST = 22;
/*      */   
/*      */   private static final int FACE_SIZE_SECOND = 16;
/*      */   private static final int ENTRY_H_SECOND = 20;
/*      */   private static final int FACE_SIZE_THIRD = 16;
/*      */   private static final int ENTRY_H_THIRD = 20;
/*      */   private int guiLeft;
/*      */   private int guiTop;
/*      */   private boolean closeHovered;
/*      */   private boolean backHovered;
/*      */   private boolean pastSeasonsHovered;
/*   87 */   private final boolean[] filterTabHovered = new boolean[8];
/*   88 */   private int activeFilterIndex = 0;
/*   89 */   private int hoveredEntryIndex = -1;
/*      */   
/*      */   private boolean viewingPastSeason = false;
/*      */   
/*      */   private boolean pastSeasonListOpen = false;
/*   94 */   private int hoveredPastSeasonIndex = -1;
/*      */   
/*      */   private boolean hasPastSeasons = false;
/*      */   
/*   98 */   private String tooltipText = null;
/*      */   
/*      */   private int tooltipX;
/*      */   private int tooltipY;
/*  102 */   private int rewardScrollOffset = 0;
/*      */ 
/*      */   
/*  105 */   private int scrollOffset = 0;
/*  106 */   private final Map<UUID, class_2960> skinCache = new HashMap<>();
/*  107 */   private final Map<UUID, Boolean> skinFetchStarted = new HashMap<>();
/*      */   
/*  109 */   private static final String[] FILTER_NAMES = new String[] { "Global", "Bronze", "Silver", "Gold", "Diamond", "Master", "Champion", "Legend" };
/*  110 */   private static final String[] FILTER_KEYS = new String[] { "global", "Bronze", "Silver", "Gold", "Diamond", "Master", "Champion", "Legend" };
/*      */   
/*      */   public RankedLeaderboardScreen() {
/*  113 */     super((class_2561)class_2561.method_43471("screen.cobblemon_ranked.leaderboard"));
/*      */   }
/*      */ 
/*      */   
/*      */   protected void method_25426() {
/*  118 */     super.method_25426();
/*  119 */     this.guiLeft = (this.field_22789 - 390) / 2;
/*  120 */     this.guiTop = (this.field_22790 - 300) / 2;
/*      */     
/*  122 */     RankedNetwork.requestPastSeasons();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private class_2960 getSkinTexture(UUID uuid, String playerName) {
/*  129 */     class_2960 cached = this.skinCache.get(uuid);
/*  130 */     if (cached != null) return cached;
/*      */ 
/*      */     
/*  133 */     class_310 client = class_310.method_1551();
/*  134 */     if (client.method_1562() != null) {
/*  135 */       class_640 entry = client.method_1562().method_2871(uuid);
/*  136 */       if (entry != null) {
/*  137 */         class_2960 tex = entry.method_52810().comp_1626();
/*  138 */         if (tex != null) {
/*  139 */           this.skinCache.put(uuid, tex);
/*  140 */           return tex;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  146 */     if (!this.skinFetchStarted.containsKey(uuid)) {
/*  147 */       this.skinFetchStarted.put(uuid, Boolean.valueOf(true));
/*  148 */       CompletableFuture.supplyAsync(() -> {
/*      */             try {
/*      */               ProfileResult result = client.method_1495().fetchProfile(uuid, false);
/*      */               if (result != null) {
/*      */                 GameProfile fullProfile = result.profile();
/*      */                 class_8685 textures = client.method_1582().method_52862(fullProfile);
/*      */                 if (textures != null && textures.comp_1626() != null) {
/*      */                   return textures.comp_1626();
/*      */                 }
/*      */               } 
/*  158 */             } catch (Throwable throwable) {}
/*      */             return null;
/*  160 */           }).thenAcceptAsync(tex -> {
/*      */             if (tex != null) {
/*      */               this.skinCache.put(uuid, tex);
/*      */             }
/*      */           }(Executor)client);
/*      */     } 
/*      */     
/*  167 */     return DEFAULT_SKIN;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void drawPlayerFace(class_332 ctx, class_2960 skinTexture, int x, int y, int size) {
/*  176 */     ctx.method_25293(skinTexture, x, y, size, size, 8.0F, 8.0F, 8, 8, 64, 64);
/*      */     
/*  178 */     ctx.method_25293(skinTexture, x, y, size, size, 40.0F, 8.0F, 8, 8, 64, 64);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void method_25394(class_332 ctx, int mouseX, int mouseY, float delta) {
/*  187 */     ctx.method_25294(0, 0, this.field_22789, this.field_22790, RankedStyledButton.color(0, 0, 0, 180));
/*  188 */     drawPanel(ctx);
/*      */     
/*  190 */     this.closeHovered = this.backHovered = false;
/*  191 */     this.hoveredEntryIndex = -1;
/*  192 */     this.tooltipText = null;
/*  193 */     this.hasPastSeasons = !PlayerRankedData.getInstance().getPastSeasons().isEmpty();
/*  194 */     for (int i = 0; i < this.filterTabHovered.length; ) { this.filterTabHovered[i] = false; i++; }
/*      */     
/*  196 */     drawHeader(ctx, mouseX, mouseY);
/*  197 */     if (!this.viewingPastSeason) {
/*  198 */       drawFilterTabs(ctx, mouseX, mouseY);
/*  199 */       drawSeasonBanner(ctx);
/*      */     } 
/*  201 */     drawLeaderboardColumn(ctx, mouseX, mouseY);
/*  202 */     if (!this.viewingPastSeason) {
/*  203 */       drawSeasonRewards(ctx, mouseX, mouseY);
/*      */     }
/*  205 */     drawBottomButtons(ctx, mouseX, mouseY);
/*      */ 
/*      */     
/*  208 */     if (this.tooltipText != null) {
/*  209 */       drawTooltip(ctx, this.tooltipText, this.tooltipX, this.tooltipY);
/*      */     }
/*      */     
/*  212 */     super.method_25394(ctx, mouseX, mouseY, delta);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void drawPanel(class_332 ctx) {
/*  218 */     int x = this.guiLeft, y = this.guiTop, w = 390, h = 300;
/*  219 */     ctx.method_25294(x, y, x + w, y + h, PANEL_BG);
/*      */     
/*  221 */     for (int i = 0; i < w - 4; i++) {
/*  222 */       float p = i / (w - 4);
/*  223 */       int r = (int)(60.0F + p * 40.0F);
/*  224 */       int g = (int)(180.0F + p * 20.0F);
/*  225 */       int b = (int)(220.0F - p * 20.0F);
/*  226 */       ctx.method_25294(x + 2 + i, y, x + 3 + i, y + 2, RankedStyledButton.color(r, g, b, 200));
/*      */     } 
/*  228 */     ctx.method_25294(x, y + h - 2, x + w, y + h, RankedStyledButton.color(40, 50, 80, 255));
/*  229 */     ctx.method_25294(x, y, x + 2, y + h, BORDER_HIGHLIGHT);
/*  230 */     ctx.method_25294(x + w - 2, y, x + w, y + h, RankedStyledButton.color(40, 50, 80, 255));
/*      */     
/*  232 */     ctx.method_25294(x + 2, y + 2, x + w - 2, y + 3, RankedStyledButton.color(0, 0, 0, 100));
/*  233 */     ctx.method_25294(x + 2, y + 2, x + 3, y + h - 2, RankedStyledButton.color(0, 0, 0, 100));
/*      */   }
/*      */ 
/*      */   
/*      */   private void drawHeader(class_332 ctx, int mouseX, int mouseY) {
/*      */     String titleText;
/*  239 */     int headerY = this.guiTop + 2;
/*  240 */     int headerH = 20;
/*      */ 
/*      */     
/*  243 */     for (int row = 0; row < headerH; row++) {
/*  244 */       float p = row / headerH;
/*  245 */       ctx.method_25294(this.guiLeft + 2, headerY + row, this.guiLeft + 390 - 2, headerY + row + 1, 
/*  246 */           RankedStyledButton.color(15, 18, 30, 200 + (int)(p * 55.0F)));
/*      */     } 
/*      */ 
/*      */     
/*  250 */     PlayerRankedData data0 = PlayerRankedData.getInstance();
/*      */     
/*  252 */     if (this.viewingPastSeason) {
/*  253 */       titleText = "§l" + data0.getViewingPastSeasonName();
/*      */     } else {
/*  255 */       titleText = "§lLEADERBOARD";
/*      */     } 
/*  257 */     ctx.method_25300(this.field_22793, titleText, this.guiLeft + 195, headerY + 6, ACCENT_ORANGE);
/*      */ 
/*      */     
/*  260 */     PlayerRankedData data = PlayerRankedData.getInstance();
/*  261 */     String trophyText = " " + data.getTrophies() + " Trophies";
/*  262 */     int trophyTextW = this.field_22793.method_1727(trophyText);
/*  263 */     int trophyIconSize = 9;
/*  264 */     int totalTrophyW = trophyIconSize + trophyTextW;
/*  265 */     int trophyX = this.guiLeft + 390 - totalTrophyW - 10;
/*  266 */     int trophyY = headerY + 6;
/*  267 */     RankedIcon.TROPHY.drawTexture(ctx, trophyX, trophyY - 1, trophyIconSize, trophyIconSize);
/*  268 */     ctx.method_51433(this.field_22793, trophyText, trophyX + trophyIconSize, trophyY, ACCENT_GOLD, true);
/*      */ 
/*      */     
/*  271 */     int rankInfoX = this.guiLeft + 10;
/*  272 */     int rankInfoY = headerY + 6;
/*  273 */     RankedRank playerRankObj = RankedRank.getRankForElo(data.getElo());
/*  274 */     int playerRankColor = RankedIcon.rankColorToARGB(playerRankObj.getIconColor());
/*      */ 
/*      */     
/*  277 */     RankedIcon headerBadge = RankedIcon.badgeForRank(playerRankObj);
/*  278 */     int headerBadgeSize = 9;
/*  279 */     if (headerBadge != null) {
/*  280 */       headerBadge.drawTexture(ctx, rankInfoX, rankInfoY, headerBadgeSize, headerBadgeSize);
/*  281 */       rankInfoX += headerBadgeSize + 2;
/*      */     } 
/*      */ 
/*      */     
/*  285 */     RankedIcon headerTag = RankedIcon.tagForRank(playerRankObj);
/*  286 */     if (headerTag != null) {
/*  287 */       headerTag.drawTexture(ctx, rankInfoX, rankInfoY + 1);
/*  288 */       rankInfoX += headerTag.getWidth() + 3;
/*      */     } 
/*      */ 
/*      */     
/*  292 */     String eloInfoText = " | " + data.getElo() + " Elo";
/*  293 */     ctx.method_51433(this.field_22793, eloInfoText, rankInfoX, rankInfoY, playerRankColor, true);
/*      */ 
/*      */     
/*  296 */     int sepY = headerY + headerH - 1;
/*  297 */     for (int i = 0; i < 378; i++) {
/*  298 */       float p = i / 378.0F;
/*  299 */       int r = (int)(60.0F + p * 20.0F), g = (int)(180.0F - p * 60.0F), b = (int)(220.0F - p * 20.0F);
/*  300 */       ctx.method_25294(this.guiLeft + 6 + i, sepY, this.guiLeft + 7 + i, sepY + 1, 
/*  301 */           RankedStyledButton.color(r, g, b, 150 + (int)(50.0D * Math.sin(i * 0.1D))));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void drawFilterTabs(class_332 ctx, int mouseX, int mouseY) {
/*  308 */     int tabY = this.guiTop + 24;
/*  309 */     int tabH = 14;
/*  310 */     int tabSpacing = 1;
/*  311 */     int tabX = this.guiLeft + 6;
/*      */     
/*  313 */     for (int i = 0; i < FILTER_NAMES.length; i++) {
/*  314 */       int textColor; String name = FILTER_NAMES[i];
/*  315 */       int currentTabW = this.field_22793.method_1727(name) + 10;
/*  316 */       boolean active = (i == this.activeFilterIndex);
/*  317 */       this.filterTabHovered[i] = (mouseX >= tabX && mouseX < tabX + currentTabW && mouseY >= tabY && mouseY < tabY + tabH);
/*      */ 
/*      */       
/*  320 */       int tabBg = active ? RankedStyledButton.color(30, 40, 65, 255) : RankedStyledButton.color(15, 18, 30, 255);
/*  321 */       ctx.method_25294(tabX, tabY, tabX + currentTabW, tabY + tabH, tabBg);
/*      */ 
/*      */       
/*  324 */       int borderCol = active ? ACCENT_ORANGE : (this.filterTabHovered[i] ? BORDER_HIGHLIGHT : BORDER_COLOR);
/*  325 */       ctx.method_49601(tabX, tabY, currentTabW, tabH, borderCol);
/*      */ 
/*      */       
/*  328 */       if (active) {
/*  329 */         ctx.method_25294(tabX + 1, tabY + tabH - 2, tabX + currentTabW - 1, tabY + tabH, ACCENT_ORANGE);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  334 */       if (active) {
/*  335 */         textColor = getTierColor(i);
/*  336 */       } else if (this.filterTabHovered[i]) {
/*  337 */         textColor = TEXT_PRIMARY;
/*      */       } else {
/*  339 */         textColor = TEXT_DIM;
/*      */       } 
/*  341 */       ctx.method_25300(this.field_22793, name, tabX + currentTabW / 2, tabY + 3, textColor);
/*      */       
/*  343 */       tabX += currentTabW + tabSpacing;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private int getTierColor(int filterIndex) {
/*  349 */     switch (filterIndex) { case 0: case 1: case 2: case 3: case 4: case 5: case 6: case 7:  }  return 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  358 */       TEXT_PRIMARY;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void drawSeasonBanner(class_332 ctx) {
/*  365 */     PlayerRankedData data = PlayerRankedData.getInstance();
/*      */     
/*  367 */     int bannerX = this.guiLeft + 6;
/*  368 */     int bannerY = this.guiTop + 40;
/*  369 */     int bannerW = 378;
/*  370 */     int bannerH = 16;
/*      */     
/*  372 */     ctx.method_25294(bannerX, bannerY, bannerX + bannerW, bannerY + bannerH, RankedStyledButton.color(15, 18, 30, 240));
/*  373 */     ctx.method_25294(bannerX, bannerY + bannerH / 2, bannerX + bannerW, bannerY + bannerH, RankedStyledButton.color(12, 14, 24, 240));
/*      */     
/*  375 */     ctx.method_25294(bannerX, bannerY, bannerX + bannerW, bannerY + 1, ACCENT_ORANGE);
/*      */     
/*  377 */     ctx.method_25294(bannerX, bannerY, bannerX + 1, bannerY + bannerH, RankedStyledButton.color(40, 50, 80, 180));
/*  378 */     ctx.method_25294(bannerX + bannerW - 1, bannerY, bannerX + bannerW, bannerY + bannerH, RankedStyledButton.color(40, 50, 80, 120));
/*  379 */     ctx.method_25294(bannerX, bannerY + bannerH - 1, bannerX + bannerW, bannerY + bannerH, RankedStyledButton.color(40, 50, 80, 120));
/*      */     
/*  381 */     int bannerTextY = bannerY + (bannerH - 9) / 2 + 1;
/*      */ 
/*      */     
/*  384 */     String seasonLabel = data.getSeasonDisplayName();
/*  385 */     ctx.method_51433(this.field_22793, "§l" + seasonLabel, bannerX + 5, bannerTextY, ACCENT_ORANGE, true);
/*      */ 
/*      */     
/*  388 */     String countdown = formatCountdown(data.getSeasonEndEpoch());
/*  389 */     int countdownColor = getCountdownColor(data.getSeasonEndEpoch());
/*  390 */     String countdownLabel = "Ends in: ";
/*  391 */     int labelW = this.field_22793.method_1727(countdownLabel);
/*  392 */     int cdW = this.field_22793.method_1727(countdown);
/*  393 */     int rightEdge = bannerX + bannerW - 5;
/*  394 */     ctx.method_51433(this.field_22793, countdownLabel, rightEdge - cdW - labelW, bannerTextY, TEXT_DIM, true);
/*  395 */     ctx.method_51433(this.field_22793, countdown, rightEdge - cdW, bannerTextY, countdownColor, true);
/*      */   }
/*      */   
/*      */   private void drawLeaderboardColumn(class_332 ctx, int mouseX, int mouseY) {
/*      */     List<RankedNetwork.LeaderboardEntryData> entries;
/*      */     RankedNetwork.LeaderboardEntryData playerRank;
/*  401 */     int columnX = this.guiLeft + 6;
/*  402 */     int columnY = this.viewingPastSeason ? (this.guiTop + 24) : (this.guiTop + 58);
/*  403 */     int columnW = this.viewingPastSeason ? 378 : 240;
/*  404 */     int columnH = this.viewingPastSeason ? 248 : 216;
/*      */ 
/*      */     
/*  407 */     ctx.method_25294(columnX, columnY, columnX + columnW, columnY + columnH, SECTION_BG);
/*  408 */     for (int row = 0; row < columnH; row++) {
/*  409 */       float p = row / columnH;
/*  410 */       ctx.method_25294(columnX + 1, columnY + row, columnX + columnW - 1, columnY + row + 1, 
/*  411 */           RankedStyledButton.color(20, 30, 50, (int)(10.0F + p * 15.0F)));
/*      */     } 
/*      */ 
/*      */     
/*  415 */     ctx.method_25294(columnX, columnY, columnX + columnW, columnY + 2, ACCENT_ORANGE);
/*  416 */     ctx.method_25294(columnX, columnY + columnH - 2, columnX + columnW, columnY + columnH, RankedStyledButton.darken(ACCENT_ORANGE, 40));
/*  417 */     ctx.method_25294(columnX, columnY, columnX + 2, columnY + columnH, ACCENT_ORANGE);
/*  418 */     ctx.method_25294(columnX + columnW - 2, columnY, columnX + columnW, columnY + columnH, RankedStyledButton.darken(ACCENT_ORANGE, 40));
/*  419 */     ctx.method_49601(columnX, columnY, columnW, columnH, ACCENT_ORANGE);
/*      */ 
/*      */     
/*  422 */     String titlePrefix = "§lTop Players ";
/*  423 */     int titleY = columnY + 5;
/*  424 */     if (this.activeFilterIndex == 0) {
/*      */       
/*  426 */       ctx.method_25300(this.field_22793, "§lTop Players", columnX + columnW / 2, titleY, ACCENT_ORANGE);
/*      */     } else {
/*  428 */       String tierName = FILTER_NAMES[this.activeFilterIndex];
/*  429 */       RankedIcon tierTag = RankedIcon.tagForDisplayName(tierName);
/*      */       
/*  431 */       String highestBadgeName = tierName.equals("Legend") ? "Legend" : (tierName + " III");
/*  432 */       RankedIcon tierBadge = RankedIcon.badgeForDisplayName(highestBadgeName);
/*  433 */       int prefixW = this.field_22793.method_1727(titlePrefix);
/*  434 */       int badgeSize = 12;
/*  435 */       int totalW = prefixW;
/*  436 */       if (tierBadge != null) totalW += badgeSize + 4; 
/*  437 */       if (tierTag != null) totalW += tierTag.getWidth(); 
/*  438 */       int startX = columnX + (columnW - totalW) / 2;
/*  439 */       ctx.method_51433(this.field_22793, titlePrefix, startX, titleY, getTierColor(this.activeFilterIndex), true);
/*  440 */       int cx = startX + prefixW;
/*  441 */       if (tierBadge != null) {
/*  442 */         tierBadge.drawTexture(ctx, cx, titleY - 2, badgeSize, badgeSize);
/*  443 */         cx += badgeSize + 4;
/*      */       } 
/*  445 */       if (tierTag != null) {
/*  446 */         tierTag.drawTexture(ctx, cx, titleY + 1);
/*      */       }
/*      */     } 
/*  449 */     ctx.method_25294(columnX + 4, columnY + 17, columnX + columnW - 4, columnY + 18, BORDER_COLOR);
/*      */     
/*  451 */     PlayerRankedData data = PlayerRankedData.getInstance();
/*      */ 
/*      */     
/*  454 */     if (this.viewingPastSeason) {
/*      */       
/*  456 */       List<RankedNetwork.PastSeasonLeaderboardEntry> pastEntries = data.getPastSeasonLeaderboard();
/*  457 */       List<RankedNetwork.LeaderboardEntryData> converted = new ArrayList<>();
/*  458 */       for (RankedNetwork.PastSeasonLeaderboardEntry pe : pastEntries) {
/*  459 */         converted.add(new RankedNetwork.LeaderboardEntryData(pe.playerUUID(), pe.playerName(), pe.value(), pe.rank(), false));
/*      */       }
/*  461 */       entries = converted;
/*  462 */       playerRank = null;
/*      */     } else {
/*  464 */       entries = data.getLeaderboardEntries();
/*  465 */       playerRank = data.getPlayerLeaderboardRank();
/*      */     } 
/*      */     
/*  468 */     int listTop = columnY + 21;
/*  469 */     int listBottom = columnY + columnH - 4;
/*  470 */     boolean showPlayerBar = (playerRank != null && !isPlayerInVisibleRange(entries));
/*  471 */     if (showPlayerBar) listBottom -= 18; 
/*  472 */     int visibleH = listBottom - listTop;
/*      */     
/*  474 */     if (entries.isEmpty()) {
/*  475 */       ctx.method_25300(this.field_22793, "No entries yet", columnX + columnW / 2, columnY + columnH / 2 - 4, TEXT_DIM);
/*      */     } else {
/*      */       
/*  478 */       int maxScroll = Math.max(0, entries.size() - calculateMaxVisible(entries.size(), visibleH));
/*  479 */       if (this.scrollOffset > maxScroll) this.scrollOffset = maxScroll; 
/*  480 */       if (this.scrollOffset < 0) this.scrollOffset = 0;
/*      */       
/*  482 */       ctx.method_44379(columnX + 2, listTop, columnX + columnW - 2, listBottom);
/*      */       
/*  484 */       int cy = listTop;
/*  485 */       int drawnIndex = 0;
/*  486 */       for (int i = this.scrollOffset; i < entries.size() && cy < listBottom; i++) {
/*  487 */         int faceSize, entryH; RankedNetwork.LeaderboardEntryData entry = entries.get(i);
/*  488 */         int entryRank = entry.rank();
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  493 */         if (entryRank == 1) {
/*  494 */           faceSize = 20;
/*  495 */           entryH = 22;
/*  496 */         } else if (entryRank == 2) {
/*  497 */           faceSize = 16;
/*  498 */           entryH = 20;
/*  499 */         } else if (entryRank == 3) {
/*  500 */           faceSize = 16;
/*  501 */           entryH = 20;
/*      */         } else {
/*  503 */           faceSize = 14;
/*  504 */           entryH = 18;
/*      */         } 
/*      */         
/*  507 */         if (cy + entryH > listBottom) {
/*      */           break;
/*      */         }
/*  510 */         boolean rowHovered = (mouseX >= columnX + 2 && mouseX < columnX + columnW - 2 && mouseY >= cy && mouseY < cy + entryH);
/*      */         
/*  512 */         if (rowHovered) this.hoveredEntryIndex = i;
/*      */ 
/*      */         
/*  515 */         if (entryRank == 1) {
/*  516 */           ctx.method_25294(columnX + 2, cy, columnX + columnW - 2, cy + entryH, PODIUM_GOLD_BG);
/*  517 */           ctx.method_25294(columnX + 2, cy, columnX + 5, cy + entryH, PODIUM_GOLD_BORDER);
/*  518 */         } else if (entryRank == 2) {
/*  519 */           ctx.method_25294(columnX + 2, cy, columnX + columnW - 2, cy + entryH, PODIUM_SILVER_BG);
/*  520 */           ctx.method_25294(columnX + 2, cy, columnX + 5, cy + entryH, PODIUM_SILVER_BORDER);
/*  521 */         } else if (entryRank == 3) {
/*  522 */           ctx.method_25294(columnX + 2, cy, columnX + columnW - 2, cy + entryH, PODIUM_BRONZE_BG);
/*  523 */           ctx.method_25294(columnX + 2, cy, columnX + 5, cy + entryH, PODIUM_BRONZE_BORDER);
/*      */         } 
/*      */ 
/*      */         
/*  527 */         if (entry.isCurrentPlayer()) {
/*  528 */           ctx.method_25294(columnX + 2, cy, columnX + columnW - 2, cy + entryH, RankedStyledButton.color(80, 140, 220, 40));
/*      */         }
/*      */ 
/*      */         
/*  532 */         if (rowHovered && !entry.isCurrentPlayer()) {
/*  533 */           ctx.method_25294(columnX + 2, cy, columnX + columnW - 2, cy + entryH, ROW_HOVER_BG);
/*      */         }
/*      */ 
/*      */         
/*  537 */         String rankText = "#" + entryRank;
/*  538 */         int rankReservedW = this.field_22793.method_1727("#99");
/*  539 */         int rankColor = getPositionColor(entryRank);
/*  540 */         int textY = cy + (entryH - 8) / 2;
/*  541 */         ctx.method_51433(this.field_22793, ((entryRank <= 3) ? "§l" : "") + ((entryRank <= 3) ? "§l" : ""), columnX + 4 + rankReservedW - this.field_22793
/*  542 */             .method_1727(rankText), textY, rankColor, true);
/*      */ 
/*      */         
/*  545 */         int faceX = columnX + rankReservedW + 8;
/*  546 */         int faceY = cy + (entryH - faceSize) / 2;
/*  547 */         class_2960 skinTex = getSkinTexture(entry.playerUUID(), entry.playerName());
/*  548 */         drawPlayerFace(ctx, skinTex, faceX, faceY, faceSize);
/*      */ 
/*      */         
/*  551 */         RankedRank eloRank = RankedRank.getRankForElo(entry.elo());
/*  552 */         int entryRankColor = RankedIcon.rankColorToARGB(eloRank.getIconColor());
/*      */ 
/*      */         
/*  555 */         String eloText = String.valueOf(entry.elo());
/*  556 */         int eloTextW = this.field_22793.method_1727(eloText);
/*  557 */         int eloX = columnX + columnW - eloTextW - 6;
/*      */ 
/*      */         
/*  560 */         int badgeDrawSize = faceSize - 2;
/*  561 */         int badgeDrawX = faceX + faceSize + 3;
/*  562 */         int badgeDrawY = cy + (entryH - badgeDrawSize) / 2;
/*  563 */         RankedIcon entryBadge = RankedIcon.badgeForRank(eloRank);
/*  564 */         if (entryBadge != null) {
/*  565 */           entryBadge.drawTexture(ctx, badgeDrawX, badgeDrawY, badgeDrawSize, badgeDrawSize);
/*      */         }
/*      */ 
/*      */         
/*  569 */         String name = entry.playerName();
/*  570 */         int nameStartX = badgeDrawX + badgeDrawSize + 3;
/*  571 */         int maxNameWidth = eloX - nameStartX - 4;
/*  572 */         if (this.field_22793.method_1727(name) > maxNameWidth) {
/*  573 */           while (this.field_22793.method_1727(name + "..") > maxNameWidth && name.length() > 1)
/*  574 */             name = name.substring(0, name.length() - 1); 
/*  575 */           name = name + "..";
/*      */         } 
/*  577 */         int nameColor = entry.isCurrentPlayer() ? ACCENT_CYAN : entryRankColor;
/*  578 */         boolean boldName = (entryRank <= 3);
/*  579 */         ctx.method_51433(this.field_22793, (boldName ? "§l" : "") + (boldName ? "§l" : ""), nameStartX, textY, nameColor, true);
/*      */ 
/*      */         
/*  582 */         ctx.method_51433(this.field_22793, eloText, eloX, textY, entryRankColor, false);
/*      */ 
/*      */         
/*  585 */         if (entryRank == 3) {
/*  586 */           int sepLineY = cy + entryH;
/*  587 */           for (int px = 0; px < columnW - 12; px++) {
/*  588 */             float p = px / (columnW - 12);
/*  589 */             int alpha = (int)(80.0D * Math.sin(p * Math.PI));
/*  590 */             ctx.method_25294(columnX + 6 + px, sepLineY, columnX + 7 + px, sepLineY + 1, 
/*  591 */                 RankedStyledButton.color(255, 200, 80, alpha));
/*      */           } 
/*  593 */           cy += 2;
/*      */         } 
/*      */ 
/*      */         
/*  597 */         if (entryRank != 3) {
/*  598 */           ctx.method_25294(columnX + 6, cy + entryH - 1, columnX + columnW - 6, cy + entryH, 
/*  599 */               RankedStyledButton.color(40, 50, 80, 60));
/*      */         }
/*      */         
/*  602 */         cy += entryH;
/*  603 */         drawnIndex++;
/*      */       } 
/*      */       
/*  606 */       ctx.method_44380();
/*      */ 
/*      */       
/*  609 */       int totalVisible = calculateMaxVisible(entries.size(), visibleH);
/*  610 */       if (entries.size() > totalVisible) {
/*  611 */         int scrollTrackX = columnX + columnW - 5;
/*  612 */         int scrollTrackH = visibleH;
/*  613 */         ctx.method_25294(scrollTrackX, listTop, scrollTrackX + 3, listTop + scrollTrackH, RankedStyledButton.color(25, 30, 45, 180));
/*  614 */         int maxScrCalc = Math.max(1, entries.size() - totalVisible);
/*  615 */         float scrollRatio = this.scrollOffset / maxScrCalc;
/*  616 */         int thumbH = Math.max(10, scrollTrackH * totalVisible / entries.size());
/*  617 */         int thumbY = listTop + (int)((scrollTrackH - thumbH) * scrollRatio);
/*      */         
/*  619 */         for (int j = 0; j < thumbH; j++) {
/*  620 */           float p = j / thumbH;
/*  621 */           int r = (int)(80.0F + p * 40.0F), g = (int)(200.0F - p * 40.0F), b = (int)(220.0F - p * 20.0F);
/*  622 */           ctx.method_25294(scrollTrackX, thumbY + j, scrollTrackX + 3, thumbY + j + 1, 
/*  623 */               RankedStyledButton.color(r, g, b, 220));
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  629 */     if (showPlayerBar) {
/*  630 */       int playerY = listBottom + 3;
/*      */       
/*  632 */       for (int px = 0; px < columnW - 8; px++) {
/*  633 */         float p = px / (columnW - 8);
/*  634 */         int alpha = (int)(100.0D * Math.sin(p * Math.PI));
/*  635 */         ctx.method_25294(columnX + 4 + px, playerY - 2, columnX + 5 + px, playerY - 1, 
/*  636 */             RankedStyledButton.color(100, 160, 240, alpha));
/*      */       } 
/*      */       
/*  639 */       ctx.method_25294(columnX + 2, playerY, columnX + columnW - 2, playerY + 14, RankedStyledButton.color(80, 140, 220, 30));
/*      */       
/*  641 */       class_2960 playerSkin = getSkinTexture(playerRank.playerUUID(), playerRank.playerName());
/*  642 */       drawPlayerFace(ctx, playerSkin, columnX + 6, playerY + 1, 12);
/*      */       
/*  644 */       String yourRank = "§l#" + playerRank.rank() + "§r " + playerRank.playerName() + "  " + playerRank.elo() + " Elo";
/*  645 */       ctx.method_51433(this.field_22793, yourRank, columnX + 22, playerY + 3, ACCENT_CYAN, true);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private int calculateMaxVisible(int totalEntries, int visibleH) {
/*  651 */     int remaining = visibleH;
/*  652 */     int count = 0;
/*  653 */     for (int i = 0; i < totalEntries; i++) {
/*  654 */       int entryH, rank = i + 1;
/*      */       
/*  656 */       if (rank == 1) { entryH = 24; }
/*  657 */       else if (rank == 2) { entryH = 20; }
/*  658 */       else if (rank == 3) { entryH = 22; }
/*  659 */       else { entryH = 18; }
/*  660 */        if (remaining < entryH)
/*  661 */         break;  remaining -= entryH;
/*  662 */       count++;
/*      */     } 
/*  664 */     return Math.max(1, count);
/*      */   }
/*      */   
/*      */   private boolean isPlayerInVisibleRange(List<RankedNetwork.LeaderboardEntryData> entries) {
/*  668 */     for (int i = this.scrollOffset; i < entries.size(); i++) {
/*  669 */       if (((RankedNetwork.LeaderboardEntryData)entries.get(i)).isCurrentPlayer()) return true; 
/*      */     } 
/*  671 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void drawSeasonRewards(class_332 ctx, int mouseX, int mouseY) {
/*  677 */     int rewardX = this.guiLeft + 252;
/*  678 */     int rewardY = this.guiTop + 58;
/*  679 */     int rewardW = 132;
/*  680 */     int rewardH = 216;
/*      */ 
/*      */     
/*  683 */     ctx.method_25294(rewardX, rewardY, rewardX + rewardW, rewardY + rewardH, SECTION_BG);
/*  684 */     for (int row = 0; row < rewardH; row++) {
/*  685 */       float p = row / rewardH;
/*  686 */       ctx.method_25294(rewardX + 1, rewardY + row, rewardX + rewardW - 1, rewardY + row + 1, 
/*  687 */           RankedStyledButton.color(20, 30, 50, (int)(10.0F + p * 15.0F)));
/*      */     } 
/*      */ 
/*      */     
/*  691 */     ctx.method_25294(rewardX, rewardY, rewardX + rewardW, rewardY + 2, ACCENT_GOLD);
/*  692 */     ctx.method_25294(rewardX, rewardY + rewardH - 2, rewardX + rewardW, rewardY + rewardH, RankedStyledButton.darken(ACCENT_GOLD, 40));
/*  693 */     ctx.method_25294(rewardX, rewardY, rewardX + 2, rewardY + rewardH, ACCENT_GOLD);
/*  694 */     ctx.method_25294(rewardX + rewardW - 2, rewardY, rewardX + rewardW, rewardY + rewardH, RankedStyledButton.darken(ACCENT_GOLD, 40));
/*  695 */     ctx.method_49601(rewardX, rewardY, rewardW, rewardH, ACCENT_GOLD);
/*      */ 
/*      */     
/*  698 */     ctx.method_25300(this.field_22793, "§lRewards", rewardX + rewardW / 2, rewardY + 4, ACCENT_GOLD);
/*  699 */     if (this.activeFilterIndex == 0) {
/*  700 */       ctx.method_25300(this.field_22793, "All Ranks", rewardX + rewardW / 2, rewardY + 14, ACCENT_ORANGE);
/*      */     } else {
/*  702 */       String tierName = FILTER_NAMES[this.activeFilterIndex];
/*  703 */       RankedIcon tierTag = RankedIcon.tagForDisplayName(tierName);
/*  704 */       if (tierTag != null) {
/*  705 */         tierTag.drawTexture(ctx, rewardX + (rewardW - tierTag.getWidth()) / 2, rewardY + 15);
/*      */       } else {
/*  707 */         ctx.method_25300(this.field_22793, tierName, rewardX + rewardW / 2, rewardY + 14, getTierColor(this.activeFilterIndex));
/*      */       } 
/*      */     } 
/*  710 */     ctx.method_25294(rewardX + 4, rewardY + 24, rewardX + rewardW - 4, rewardY + 25, BORDER_COLOR);
/*      */     
/*  712 */     int drawY = rewardY + 28;
/*  713 */     int maxDrawY = rewardY + rewardH - 4;
/*  714 */     int contentW = rewardW - 14;
/*      */     
/*  716 */     List<RankedNetwork.SeasonRewardData> allSynced = PlayerRankedData.getInstance().getSeasonRewards();
/*  717 */     if (this.activeFilterIndex == 7) {
/*      */       
/*  719 */       List<RankedNetwork.SeasonRewardData> legendRewards = new ArrayList<>();
/*  720 */       for (RankedNetwork.SeasonRewardData r : allSynced) { if (r.rankName().startsWith("Legend")) legendRewards.add(r);  }
/*  721 */        for (RankedNetwork.SeasonRewardData reward : legendRewards) {
/*  722 */         if (drawY + 10 > maxDrawY) {
/*      */           break;
/*      */         }
/*  725 */         String posLabel = (reward.rankName().length() > 7) ? reward.rankName().substring(7) : reward.rankName();
/*  726 */         ctx.method_51433(this.field_22793, "§l" + posLabel, rewardX + 6, drawY, ACCENT_GOLD, true);
/*  727 */         drawY += 11;
/*      */ 
/*      */         
/*  730 */         if (!reward.description().isEmpty()) {
/*  731 */           drawY = drawWrappedText(ctx, reward.description(), rewardX + 6, drawY, contentW, maxDrawY, TEXT_SECONDARY);
/*      */         }
/*      */ 
/*      */         
/*  735 */         if (drawY + 4 <= maxDrawY) {
/*  736 */           ctx.method_25294(rewardX + 8, drawY + 1, rewardX + rewardW - 8, drawY + 2, 
/*  737 */               RankedStyledButton.color(60, 70, 100, 60));
/*      */         }
/*  739 */         drawY += 5;
/*      */       } 
/*      */     } else {
/*      */       
/*  743 */       List<RankedNetwork.SeasonRewardData> filtered, rankRewards = new ArrayList<>();
/*  744 */       for (RankedNetwork.SeasonRewardData r : allSynced) { if (!r.rankName().startsWith("Legend")) rankRewards.add(r);  }
/*      */       
/*  746 */       if (this.activeFilterIndex == 0) {
/*      */         
/*  748 */         filtered = new ArrayList<>(rankRewards);
/*  749 */         Collections.reverse(filtered);
/*      */       } else {
/*      */         
/*  752 */         String filterTier = FILTER_NAMES[this.activeFilterIndex];
/*  753 */         filtered = new ArrayList<>();
/*  754 */         for (RankedNetwork.SeasonRewardData reward : rankRewards) {
/*  755 */           if (reward.rankName().startsWith(filterTier)) {
/*  756 */             filtered.add(reward);
/*      */           }
/*      */         } 
/*  759 */         Collections.reverse(filtered);
/*      */       } 
/*      */       
/*  762 */       if (filtered.isEmpty()) {
/*  763 */         ctx.method_25300(this.field_22793, "TBD", rewardX + rewardW / 2, rewardY + rewardH / 2, TEXT_DIM);
/*      */       } else {
/*  765 */         for (RankedNetwork.SeasonRewardData reward : filtered) {
/*  766 */           if (drawY + 10 > maxDrawY) {
/*      */             break;
/*      */           }
/*  769 */           RankedIcon rewardTag = RankedIcon.tagForDisplayName(reward.rankName());
/*  770 */           if (rewardTag != null) {
/*  771 */             rewardTag.drawTexture(ctx, rewardX + 6, drawY + 1);
/*      */           } else {
/*  773 */             int rewardNameColor = getRankDisplayColor(reward.rankName());
/*  774 */             ctx.method_51433(this.field_22793, "§l" + reward.rankName(), rewardX + 6, drawY, rewardNameColor, true);
/*      */           } 
/*  776 */           drawY += 11;
/*      */ 
/*      */           
/*  779 */           if (!reward.description().isEmpty()) {
/*  780 */             drawY = drawWrappedText(ctx, reward.description(), rewardX + 6, drawY, contentW, maxDrawY, TEXT_SECONDARY);
/*      */           }
/*      */ 
/*      */           
/*  784 */           if (drawY + 4 <= maxDrawY) {
/*  785 */             ctx.method_25294(rewardX + 8, drawY + 1, rewardX + rewardW - 8, drawY + 2, 
/*  786 */                 RankedStyledButton.color(60, 70, 100, 60));
/*      */           }
/*  788 */           drawY += 5;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private int drawColoredRewards(class_332 ctx, int x, int drawY, int maxWidth, int maxDrawY, int pokespinners, int pokegems, int trophies) {
/*  797 */     if (drawY + 8 > maxDrawY) return drawY;
/*      */     
/*  799 */     int cx = x;
/*      */     
/*  801 */     String psText = "" + pokespinners + " PS";
/*  802 */     ctx.method_51433(this.field_22793, psText, cx, drawY, ACCENT_CYAN, false);
/*  803 */     cx += this.field_22793.method_1727(psText);
/*      */ 
/*      */     
/*  806 */     ctx.method_51433(this.field_22793, "  ", cx, drawY, TEXT_DIM, false);
/*  807 */     cx += this.field_22793.method_1727("  ");
/*      */ 
/*      */     
/*  810 */     String pgText = "" + pokegems + " PG";
/*  811 */     if (cx + this.field_22793.method_1727(pgText) > x + maxWidth) {
/*  812 */       drawY += 10;
/*  813 */       cx = x;
/*      */     } 
/*  815 */     ctx.method_51433(this.field_22793, pgText, cx, drawY, ACCENT_PURPLE, false);
/*  816 */     cx += this.field_22793.method_1727(pgText);
/*      */ 
/*      */     
/*  819 */     ctx.method_51433(this.field_22793, "  ", cx, drawY, TEXT_DIM, false);
/*  820 */     cx += this.field_22793.method_1727("  ");
/*      */ 
/*      */     
/*  823 */     String tText = "" + trophies + " T";
/*  824 */     if (cx + this.field_22793.method_1727(tText) > x + maxWidth) {
/*  825 */       drawY += 10;
/*  826 */       cx = x;
/*      */     } 
/*  828 */     ctx.method_51433(this.field_22793, tText, cx, drawY, ACCENT_GOLD, false);
/*      */     
/*  830 */     drawY += 10;
/*  831 */     return drawY;
/*      */   }
/*      */ 
/*      */   
/*      */   private int getRankDisplayColor(String rankDisplayName) {
/*  836 */     if (rankDisplayName.startsWith("Legend")) return RankedStyledButton.color(102, 255, 209, 255); 
/*  837 */     if (rankDisplayName.startsWith("Champion")) return RankedStyledButton.color(230, 46, 46, 255); 
/*  838 */     if (rankDisplayName.startsWith("Master")) return RankedStyledButton.color(169, 108, 223, 255); 
/*  839 */     if (rankDisplayName.startsWith("Diamond")) return RankedStyledButton.color(93, 236, 245, 255); 
/*  840 */     if (rankDisplayName.startsWith("Gold")) return RankedStyledButton.color(219, 177, 24, 255); 
/*  841 */     if (rankDisplayName.startsWith("Silver")) return RankedStyledButton.color(224, 224, 224, 255); 
/*  842 */     if (rankDisplayName.startsWith("Bronze")) return RankedStyledButton.color(166, 113, 83, 255); 
/*  843 */     return ACCENT_GOLD;
/*      */   }
/*      */   
/*      */   private int drawWrappedText(class_332 ctx, String text, int x, int startY, int maxWidth, int maxY, int color) {
/*  847 */     int drawY = startY;
/*  848 */     String remaining = text;
/*  849 */     while (!remaining.isEmpty() && drawY + 8 <= maxY) {
/*  850 */       String line = remaining;
/*  851 */       if (this.field_22793.method_1727(line) > maxWidth) {
/*  852 */         int cutoff = line.length();
/*  853 */         while (cutoff > 0 && this.field_22793.method_1727(line.substring(0, cutoff)) > maxWidth)
/*  854 */           cutoff--; 
/*  855 */         int space = line.lastIndexOf(' ', cutoff);
/*  856 */         if (space > 0) cutoff = space; 
/*  857 */         line = remaining.substring(0, cutoff);
/*  858 */         remaining = remaining.substring(cutoff).stripLeading();
/*      */       } else {
/*  860 */         remaining = "";
/*      */       } 
/*  862 */       ctx.method_51433(this.field_22793, line, x, drawY, color, false);
/*  863 */       drawY += 10;
/*      */     } 
/*  865 */     return drawY;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void drawBottomButtons(class_332 ctx, int mouseX, int mouseY) {
/*  871 */     PlayerRankedData data = PlayerRankedData.getInstance();
/*      */ 
/*      */     
/*  874 */     if (this.viewingPastSeason) {
/*  875 */       int bannerX = this.guiLeft + 6;
/*  876 */       int bannerY = this.guiTop + 300 - 40;
/*  877 */       int bannerW = 378;
/*  878 */       int bannerH = 14;
/*  879 */       ctx.method_25294(bannerX, bannerY, bannerX + bannerW, bannerY + bannerH, RankedStyledButton.color(15, 18, 30, 240));
/*  880 */       ctx.method_25294(bannerX, bannerY, bannerX + bannerW, bannerY + 1, ACCENT_ORANGE);
/*  881 */       ctx.method_25294(bannerX, bannerY + bannerH - 1, bannerX + bannerW, bannerY + bannerH, RankedStyledButton.color(40, 50, 80, 120));
/*  882 */       String pastLabel = data.getViewingPastSeasonName();
/*  883 */       ctx.method_25300(this.field_22793, "§l" + pastLabel, bannerX + bannerW / 2, bannerY + 3, ACCENT_ORANGE);
/*      */     } 
/*      */ 
/*      */     
/*  887 */     int btnY = this.guiTop + 300 - 24;
/*  888 */     int btnH = 18;
/*      */ 
/*      */     
/*  891 */     int backW = 50;
/*  892 */     String backLabel = this.viewingPastSeason ? "◀ Current" : "◀ Back";
/*  893 */     this.backHovered = drawButton(ctx, this.guiLeft + 8, btnY, backW, btnH, backLabel, mouseX, mouseY, true);
/*      */ 
/*      */     
/*  896 */     int closeW = 50;
/*  897 */     this.closeHovered = drawButton(ctx, this.guiLeft + 390 - closeW - 8, btnY, closeW, btnH, "Close", mouseX, mouseY, true);
/*      */ 
/*      */     
/*  900 */     if (!this.viewingPastSeason) {
/*  901 */       int pastW = 80;
/*  902 */       int pastX = this.guiLeft + (390 - pastW) / 2;
/*  903 */       boolean enabled = this.hasPastSeasons;
/*  904 */       this.pastSeasonsHovered = drawButton(ctx, pastX, btnY, pastW, btnH, "Past Seasons", mouseX, mouseY, enabled);
/*      */ 
/*      */       
/*  907 */       if (!enabled) {
/*  908 */         boolean hoveredDisabled = (mouseX >= pastX && mouseX < pastX + pastW && mouseY >= btnY && mouseY < btnY + btnH);
/*  909 */         if (hoveredDisabled) {
/*  910 */           this.tooltipText = "No past seasons yet";
/*  911 */           this.tooltipX = mouseX;
/*  912 */           this.tooltipY = mouseY;
/*      */         } 
/*      */       } 
/*      */     } else {
/*  916 */       this.pastSeasonsHovered = false;
/*      */     } 
/*      */ 
/*      */     
/*  920 */     if (this.pastSeasonListOpen) {
/*  921 */       drawPastSeasonList(ctx, mouseX, mouseY);
/*      */     }
/*      */   }
/*      */   
/*      */   private void drawPastSeasonList(class_332 ctx, int mouseX, int mouseY) {
/*  926 */     PlayerRankedData data = PlayerRankedData.getInstance();
/*  927 */     List<RankedNetwork.PastSeasonMeta> pastSeasons = data.getPastSeasons();
/*      */     
/*  929 */     if (pastSeasons.isEmpty()) {
/*  930 */       this.pastSeasonListOpen = false;
/*      */       
/*      */       return;
/*      */     } 
/*  934 */     int listW = 150;
/*  935 */     int pastBtnW = 80;
/*  936 */     int pastBtnX = this.guiLeft + (390 - pastBtnW) / 2;
/*  937 */     int listX = pastBtnX + (pastBtnW - listW) / 2;
/*      */     
/*  939 */     int itemH = 16;
/*  940 */     int listH = Math.min(pastSeasons.size(), 6) * itemH + 4;
/*  941 */     int listY = this.guiTop + 300 - 24 - listH - 2;
/*      */ 
/*      */     
/*  944 */     ctx.method_25294(listX, listY, listX + listW, listY + listH, RankedStyledButton.color(10, 12, 20, 240));
/*  945 */     ctx.method_49601(listX, listY, listW, listH, ACCENT_CYAN);
/*      */     
/*  947 */     this.hoveredPastSeasonIndex = -1;
/*  948 */     for (int i = 0; i < pastSeasons.size() && i < 6; i++) {
/*  949 */       RankedNetwork.PastSeasonMeta season = pastSeasons.get(i);
/*  950 */       int iy = listY + 2 + i * itemH;
/*  951 */       boolean hov = (mouseX >= listX && mouseX < listX + listW && mouseY >= iy && mouseY < iy + itemH);
/*  952 */       if (hov) {
/*  953 */         this.hoveredPastSeasonIndex = i;
/*  954 */         ctx.method_25294(listX + 1, iy, listX + listW - 1, iy + itemH, ROW_HOVER_BG);
/*      */       } 
/*  956 */       String label = season.seasonName();
/*  957 */       if (season.isBeta()) label = label + " (Beta)"; 
/*  958 */       ctx.method_51433(this.field_22793, label, listX + 4, iy + 4, hov ? TEXT_PRIMARY : TEXT_SECONDARY, true);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean drawButton(class_332 ctx, int x, int y, int w, int h, String text, int mx, int my, boolean en) {
/*  965 */     boolean hov = (en && mx >= x && mx < x + w && my >= y && my < y + h);
/*  966 */     int bg = !en ? RankedStyledButton.getButtonDisabled() : (hov ? RankedStyledButton.getButtonHover() : RankedStyledButton.getButtonBg());
/*  967 */     ctx.method_25294(x + 1, y + 1, x + w - 1, y + h - 1, bg);
/*  968 */     if (en) ctx.method_25294(x + 1, y + h - 3, x + w - 1, y + h - 1, RankedStyledButton.darken(bg, 15)); 
/*  969 */     int bc = hov ? RankedStyledButton.getButtonBorderHover() : RankedStyledButton.getButtonBorder();
/*  970 */     if (!en) bc = RankedStyledButton.color(30, 35, 50, 255); 
/*  971 */     ctx.method_25294(x + 1, y, x + w - 1, y + 1, bc);
/*  972 */     ctx.method_25294(x + 1, y + h - 1, x + w - 1, y + h, RankedStyledButton.darken(bc, 30));
/*  973 */     ctx.method_25294(x, y + 1, x + 1, y + h - 1, bc);
/*  974 */     ctx.method_25294(x + w - 1, y + 1, x + w, y + h - 1, RankedStyledButton.darken(bc, 30));
/*  975 */     if (en) {
/*  976 */       ctx.method_25294(x + 2, y + 1, x + w - 2, y + 2, RankedStyledButton.lighten(bg, 20));
/*  977 */       ctx.method_25294(x + 1, y + 2, x + 2, y + h - 3, RankedStyledButton.lighten(bg, 10));
/*      */     } 
/*  979 */     ctx.method_25294(x, y, x + 1, y + 1, 0);
/*  980 */     ctx.method_25294(x + w - 1, y, x + w, y + 1, 0);
/*  981 */     ctx.method_25294(x, y + h - 1, x + 1, y + h, 0);
/*  982 */     ctx.method_25294(x + w - 1, y + h - 1, x + w, y + h, 0);
/*  983 */     ctx.method_25300(this.field_22793, text, x + w / 2, y + (h - 8) / 2, 
/*  984 */         en ? RankedStyledButton.getTextPrimary() : RankedStyledButton.getTextDim());
/*  985 */     return hov;
/*      */   }
/*      */   
/*      */   private void drawTooltip(class_332 ctx, String text, int mx, int my) {
/*  989 */     int tw = this.field_22793.method_1727(text);
/*  990 */     int pad = 4;
/*  991 */     int ttW = tw + pad * 2;
/*  992 */     int ttH = 12 + pad;
/*  993 */     int ttX = mx + 8;
/*  994 */     int ttY = my - ttH - 4;
/*      */ 
/*      */     
/*  997 */     if (ttX + ttW > this.field_22789) ttX = mx - ttW - 4; 
/*  998 */     if (ttY < 0) ttY = my + 12;
/*      */     
/* 1000 */     ctx.method_51448().method_22903();
/* 1001 */     ctx.method_51448().method_46416(0.0F, 0.0F, 500.0F);
/* 1002 */     ctx.method_25294(ttX - 1, ttY - 1, ttX + ttW + 1, ttY + ttH + 1, RankedStyledButton.color(20, 15, 40, 240));
/* 1003 */     ctx.method_25294(ttX, ttY, ttX + ttW, ttY + ttH, RankedStyledButton.color(12, 10, 25, 250));
/* 1004 */     ctx.method_25294(ttX, ttY, ttX + ttW, ttY + 1, ACCENT_CYAN);
/* 1005 */     ctx.method_51433(this.field_22793, text, ttX + pad, ttY + pad - 1, TEXT_PRIMARY, true);
/* 1006 */     ctx.method_51448().method_22909();
/*      */   }
/*      */   
/*      */   private String formatCountdown(long endEpochMillis) {
/* 1010 */     long remaining = endEpochMillis - System.currentTimeMillis();
/* 1011 */     if (remaining <= 0L) return "Ending soon..."; 
/* 1012 */     long seconds = remaining / 1000L, minutes = seconds / 60L, hours = minutes / 60L, days = hours / 24L;
/* 1013 */     if (days > 0L) return "" + days + "d " + days + "h " + hours % 24L + "m"; 
/* 1014 */     if (hours > 0L) return "" + hours + "h " + hours + "m " + minutes % 60L + "s"; 
/* 1015 */     if (minutes > 0L) return "" + minutes + "m " + minutes + "s"; 
/* 1016 */     return "" + seconds + "s";
/*      */   }
/*      */   
/*      */   private int getCountdownColor(long endEpochMillis) {
/* 1020 */     long remaining = endEpochMillis - System.currentTimeMillis();
/* 1021 */     if (remaining <= 0L) return RankedStyledButton.color(220, 50, 50, 255); 
/* 1022 */     if (remaining < 86400000L) return RankedStyledButton.color(220, 50, 50, 255); 
/* 1023 */     if (remaining < 259200000L) return ACCENT_GOLD; 
/* 1024 */     return RankedStyledButton.color(80, 255, 150, 255);
/*      */   }
/*      */   
/*      */   private int getPositionColor(int rank) {
/* 1028 */     switch (rank) { case 1: case 2: case 3:  }  return 
/*      */ 
/*      */ 
/*      */       
/* 1032 */       TEXT_DIM;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean method_25402(double mouseX, double mouseY, int button) {
/* 1040 */     if (button == 0) {
/*      */       
/* 1042 */       if (this.pastSeasonListOpen && this.hoveredPastSeasonIndex >= 0) {
/* 1043 */         List<RankedNetwork.PastSeasonMeta> pastSeasons = PlayerRankedData.getInstance().getPastSeasons();
/* 1044 */         if (this.hoveredPastSeasonIndex < pastSeasons.size()) {
/* 1045 */           playClickSound();
/* 1046 */           RankedNetwork.PastSeasonMeta season = pastSeasons.get(this.hoveredPastSeasonIndex);
/* 1047 */           RankedNetwork.requestPastSeasonLeaderboard(season.seasonNumber());
/* 1048 */           this.viewingPastSeason = true;
/* 1049 */           this.pastSeasonListOpen = false;
/* 1050 */           this.scrollOffset = 0;
/* 1051 */           this.skinCache.clear(); this.skinFetchStarted.clear();
/* 1052 */           return true;
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/* 1057 */       if (this.pastSeasonListOpen) {
/* 1058 */         this.pastSeasonListOpen = false;
/* 1059 */         return true;
/*      */       } 
/*      */ 
/*      */       
/* 1063 */       if (this.pastSeasonsHovered && this.hasPastSeasons) {
/* 1064 */         playClickSound();
/* 1065 */         RankedNetwork.requestPastSeasons();
/* 1066 */         this.pastSeasonListOpen = true;
/* 1067 */         return true;
/*      */       } 
/*      */       
/* 1070 */       if (!this.viewingPastSeason) {
/* 1071 */         for (int i = 0; i < this.filterTabHovered.length; i++) {
/* 1072 */           if (this.filterTabHovered[i] && i != this.activeFilterIndex) {
/* 1073 */             playClickSound();
/* 1074 */             this.activeFilterIndex = i;
/* 1075 */             this.scrollOffset = 0;
/* 1076 */             this.rewardScrollOffset = 0;
/* 1077 */             this.skinCache.clear(); this.skinFetchStarted.clear();
/* 1078 */             RankedNetwork.requestLeaderboard(FILTER_KEYS[i]);
/* 1079 */             return true;
/*      */           } 
/*      */         } 
/*      */       }
/*      */       
/* 1084 */       if (this.backHovered) {
/* 1085 */         playClickSound();
/* 1086 */         if (this.viewingPastSeason) {
/* 1087 */           this.viewingPastSeason = false;
/* 1088 */           this.scrollOffset = 0;
/* 1089 */           this.skinCache.clear(); this.skinFetchStarted.clear();
/* 1090 */           RankedNetwork.requestLeaderboard(FILTER_KEYS[this.activeFilterIndex]);
/*      */         } else {
/* 1092 */           class_310.method_1551().method_1507(new RankedScreen());
/*      */         } 
/* 1094 */         return true;
/*      */       } 
/*      */       
/* 1097 */       if (this.closeHovered) {
/* 1098 */         playClickSound();
/* 1099 */         method_25419();
/* 1100 */         return true;
/*      */       } 
/*      */     } 
/*      */     
/* 1104 */     return super.method_25402(mouseX, mouseY, button);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean method_25401(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
/* 1109 */     int columnX = this.guiLeft + 6;
/* 1110 */     int columnY = this.guiTop + 58;
/* 1111 */     int columnW = 240;
/* 1112 */     int columnH = 216;
/*      */     
/* 1114 */     if (mouseX >= columnX && mouseX < (columnX + columnW) && mouseY >= columnY && mouseY < (columnY + columnH)) {
/* 1115 */       this.scrollOffset -= (int)verticalAmount * 3;
/* 1116 */       List<RankedNetwork.LeaderboardEntryData> entries = PlayerRankedData.getInstance().getLeaderboardEntries();
/* 1117 */       int visibleH = columnH - 21 - 4;
/* 1118 */       int maxVisible = calculateMaxVisible(entries.size(), visibleH);
/* 1119 */       int maxScroll = Math.max(0, entries.size() - maxVisible);
/* 1120 */       this.scrollOffset = Math.max(0, Math.min(this.scrollOffset, maxScroll));
/* 1121 */       return true;
/*      */     } 
/*      */     
/* 1124 */     return super.method_25401(mouseX, mouseY, horizontalAmount, verticalAmount);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean method_25404(int keyCode, int scanCode, int modifiers) {
/* 1129 */     if (keyCode == 256) {
/* 1130 */       method_25419();
/* 1131 */       return true;
/*      */     } 
/* 1133 */     return super.method_25404(keyCode, scanCode, modifiers);
/*      */   }
/*      */   
/*      */   private void playClickSound() {
/* 1137 */     class_310.method_1551().method_1483().method_4873((class_1113)class_1109.method_47978((class_6880)class_3417.field_15015, 1.0F));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void method_25420(class_332 context, int mouseX, int mouseY, float delta) {}
/*      */ 
/*      */   
/*      */   public boolean method_25421() {
/* 1146 */     return false;
/*      */   }
/*      */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\ranked\screen\RankedLeaderboardScreen.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */