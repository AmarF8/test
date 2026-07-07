/*      */ package com.atlas.common.fabric.ranked.screen;
/*      */ 
/*      */ import com.atlas.common.fabric.ranked.data.PlayerRankedData;
/*      */ import com.atlas.common.fabric.ranked.data.RankedRank;
/*      */ import com.atlas.common.fabric.ranked.network.RankedNetwork;
/*      */ import com.atlas.common.fabric.screen.PokemonRenderHelper;
/*      */ import com.cobblemon.mod.common.client.render.models.blockbench.FloatingState;
/*      */ import com.mojang.authlib.GameProfile;
/*      */ import com.mojang.authlib.yggdrasil.ProfileResult;
/*      */ import java.util.HashMap;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import java.util.UUID;
/*      */ import java.util.concurrent.CompletableFuture;
/*      */ import java.util.concurrent.Executor;
/*      */ import net.minecraft.class_1109;
/*      */ import net.minecraft.class_1113;
/*      */ import net.minecraft.class_2561;
/*      */ import net.minecraft.class_2583;
/*      */ import net.minecraft.class_2960;
/*      */ import net.minecraft.class_310;
/*      */ import net.minecraft.class_332;
/*      */ import net.minecraft.class_3417;
/*      */ import net.minecraft.class_437;
/*      */ import net.minecraft.class_4587;
/*      */ import net.minecraft.class_5250;
/*      */ import net.minecraft.class_5348;
/*      */ import net.minecraft.class_640;
/*      */ import net.minecraft.class_6880;
/*      */ import net.minecraft.class_8685;
/*      */ import org.joml.Quaternionf;
/*      */ 
/*      */ 
/*      */ 
/*      */ public class RankedScreen
/*      */   extends class_437
/*      */ {
/*   39 */   private static final int PANEL_BG = c(10, 12, 18, 255);
/*   40 */   private static final int HEADER_BG = c(15, 18, 30, 255);
/*   41 */   private static final int SECTION_BG = c(14, 16, 24, 255);
/*   42 */   private static final int BORDER_COLOR = c(50, 60, 100, 255);
/*   43 */   private static final int BORDER_HIGHLIGHT = c(80, 140, 220, 255);
/*   44 */   private static final int ACCENT_RED = c(220, 50, 50, 255);
/*   45 */   private static final int ACCENT_CRIMSON = c(60, 100, 180, 255);
/*   46 */   private static final int ACCENT_ORANGE = c(80, 200, 220, 255);
/*   47 */   private static final int ACCENT_WARM = c(100, 180, 240, 255);
/*   48 */   private static final int ACCENT_CYAN = c(100, 160, 240, 255);
/*   49 */   private static final int ACCENT_GOLD = c(255, 200, 80, 255);
/*   50 */   private static final int ACCENT_GREEN = c(80, 255, 150, 255);
/*   51 */   private static final int ACCENT_PURPLE = c(200, 140, 255, 255);
/*   52 */   private static final int TEXT_PRIMARY = c(220, 230, 245, 255);
/*   53 */   private static final int TEXT_SECONDARY = c(160, 180, 210, 255);
/*   54 */   private static final int TEXT_DIM = c(100, 120, 160, 255);
/*      */ 
/*      */   
/*   57 */   private static final class_2960 DEFAULT_SKIN = class_2960.method_60655("minecraft", "textures/entity/player/wide/steve.png"); private static final int GUI_WIDTH = 390; private static final int GUI_HEIGHT = 330;
/*      */   private int guiLeft;
/*      */   private int guiTop;
/*      */   private boolean closeHovered;
/*      */   private boolean refreshGemsHovered;
/*      */   private boolean refreshFreeHovered;
/*      */   private boolean defenseTabHovered;
/*      */   private boolean leaderboardTabHovered;
/*      */   private boolean shopTabHovered;
/*      */   private boolean battleTabHovered;
/*      */   private boolean historyTabHovered;
/*   68 */   private final boolean[] challengeHovered = new boolean[3];
/*      */   
/*      */   private boolean showPopup;
/*      */   
/*   72 */   private String popupMessage = "";
/*      */   
/*      */   private boolean popupOkHovered;
/*      */   
/*      */   private boolean showRefreshConfirm;
/*      */   
/*      */   private boolean refreshConfirmIsFree;
/*      */   
/*      */   private boolean confirmYesHovered;
/*      */   private boolean confirmNoHovered;
/*   82 */   private String tooltipText = null;
/*      */   
/*      */   private int tooltipX;
/*      */   private int tooltipY;
/*   86 */   private final Map<UUID, class_2960> skinCache = new HashMap<>();
/*   87 */   private final Map<UUID, Boolean> skinFetchStarted = new HashMap<>();
/*      */ 
/*      */   
/*   90 */   private final Map<Integer, FloatingState> pokemonStates = new HashMap<>();
/*      */   private static int c(int r, int g, int b, int a) {
/*   92 */     return a << 24 | r << 16 | g << 8 | b;
/*      */   }
/*      */   public RankedScreen() {
/*   95 */     super((class_2561)class_2561.method_43471("screen.cobblemon_ranked.title"));
/*      */     
/*   97 */     for (int i = 0; i < 18; ) { this.pokemonStates.put(Integer.valueOf(i), new FloatingState()); i++; }
/*   98 */      PokemonRenderHelper.init();
/*      */   }
/*      */ 
/*      */   
/*      */   protected void method_25426() {
/*  103 */     super.method_25426();
/*  104 */     this.guiLeft = (this.field_22789 - 390) / 2;
/*  105 */     this.guiTop = (this.field_22790 - 330) / 2;
/*  106 */     RankedNetwork.requestDataSync();
/*      */   }
/*      */   
/*      */   public void handleLockInResult(boolean success, String reason) {
/*  110 */     if (!success) { this.showPopup = true; this.popupMessage = (reason != null) ? reason : "Failed"; }
/*      */   
/*      */   }
/*      */ 
/*      */   
/*      */   private void drawPlayerFace(class_332 ctx, class_2960 skinTexture, int x, int y, int size) {
/*  116 */     ctx.method_25293(skinTexture, x, y, size, size, 8.0F, 8.0F, 8, 8, 64, 64);
/*  117 */     ctx.method_25293(skinTexture, x, y, size, size, 40.0F, 8.0F, 8, 8, 64, 64);
/*      */   }
/*      */ 
/*      */   
/*      */   private class_2960 getSkinTexture(UUID uuid, String playerName) {
/*  122 */     class_2960 cached = this.skinCache.get(uuid);
/*  123 */     if (cached != null) return cached;
/*      */ 
/*      */     
/*  126 */     class_310 client = class_310.method_1551();
/*  127 */     if (client.method_1562() != null) {
/*  128 */       class_640 entry = client.method_1562().method_2871(uuid);
/*  129 */       if (entry != null) {
/*  130 */         class_2960 tex = entry.method_52810().comp_1626();
/*  131 */         if (tex != null) {
/*  132 */           this.skinCache.put(uuid, tex);
/*  133 */           return tex;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  139 */     if (!this.skinFetchStarted.containsKey(uuid)) {
/*  140 */       this.skinFetchStarted.put(uuid, Boolean.valueOf(true));
/*  141 */       CompletableFuture.supplyAsync(() -> {
/*      */             try {
/*      */               ProfileResult result = client.method_1495().fetchProfile(uuid, false);
/*      */               if (result != null) {
/*      */                 GameProfile fullProfile = result.profile();
/*      */                 class_8685 textures = client.method_1582().method_52862(fullProfile);
/*      */                 if (textures != null && textures.comp_1626() != null) {
/*      */                   return textures.comp_1626();
/*      */                 }
/*      */               } 
/*  151 */             } catch (Throwable throwable) {}
/*      */             return null;
/*  153 */           }).thenAcceptAsync(tex -> {
/*      */             if (tex != null) {
/*      */               this.skinCache.put(uuid, tex);
/*      */             }
/*      */           }(Executor)client);
/*      */     } 
/*      */     
/*  160 */     return DEFAULT_SKIN;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void method_25394(class_332 ctx, int mouseX, int mouseY, float delta) {
/*  169 */     ctx.method_25294(0, 0, this.field_22789, this.field_22790, c(0, 0, 0, 180));
/*  170 */     drawPanel(ctx, this.guiLeft, this.guiTop, 390, 330);
/*      */     
/*  172 */     this.closeHovered = this.refreshGemsHovered = this.refreshFreeHovered = false;
/*  173 */     this.defenseTabHovered = this.leaderboardTabHovered = this.shopTabHovered = this.battleTabHovered = false;
/*  174 */     for (int i = 0; i < 3; ) { this.challengeHovered[i] = false; i++; }
/*  175 */      this.tooltipText = null;
/*      */     
/*  177 */     PlayerRankedData data = PlayerRankedData.getInstance();
/*      */     
/*  179 */     drawHeader(ctx, mouseX, mouseY);
/*  180 */     drawSeasonBanner(ctx, data);
/*  181 */     drawRankCard(ctx, data);
/*  182 */     drawOpponentCards(ctx, data, mouseX, mouseY, delta);
/*  183 */     drawBottomArea(ctx, data, mouseX, mouseY);
/*      */     
/*  185 */     super.method_25394(ctx, mouseX, mouseY, delta);
/*      */ 
/*      */     
/*  188 */     if (this.tooltipText != null && !this.showRefreshConfirm && !this.showPopup) {
/*  189 */       drawTooltip(ctx, this.tooltipText, this.tooltipX, this.tooltipY);
/*      */     }
/*      */     
/*  192 */     if (this.showRefreshConfirm) { drawRefreshConfirmPopup(ctx, mouseX, mouseY); }
/*  193 */     else if (this.showPopup) { drawPopupOverlay(ctx, mouseX, mouseY); }
/*      */   
/*      */   }
/*      */ 
/*      */   
/*      */   private void drawPanel(class_332 ctx, int x, int y, int w, int h) {
/*  199 */     ctx.method_25294(x, y, x + w, y + h, PANEL_BG);
/*  200 */     ctx.method_25294(x, y, x + w, y + 2, ACCENT_ORANGE);
/*  201 */     ctx.method_25294(x, y + h - 2, x + w, y + h, ACCENT_CRIMSON);
/*  202 */     ctx.method_25294(x, y, x + 2, y + h, BORDER_HIGHLIGHT);
/*  203 */     ctx.method_25294(x + w - 2, y, x + w, y + h, ACCENT_CRIMSON);
/*  204 */     ctx.method_25294(x + 2, y + 2, x + w - 2, y + 3, c(0, 0, 0, 100));
/*  205 */     ctx.method_25294(x + 2, y + 2, x + 3, y + h - 2, c(0, 0, 0, 100));
/*  206 */     ctx.method_25294(x + 2, y + h - 3, x + w - 2, y + h - 2, c(40, 50, 80, 40));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void drawHeader(class_332 ctx, int mouseX, int mouseY) {
/*  212 */     int headerY = this.guiTop + 2;
/*  213 */     int headerH = 24;
/*      */     
/*  215 */     for (int row = 0; row < headerH; row++) {
/*  216 */       float p = row / headerH;
/*  217 */       ctx.method_25294(this.guiLeft + 2, headerY + row, this.guiLeft + 390 - 2, headerY + row + 1, 
/*  218 */           c(15, 18, 30, 200 + (int)(p * 55.0F)));
/*      */     } 
/*      */     
/*  221 */     for (int i = 0; i < 382; i++) {
/*  222 */       float p = i / 382.0F;
/*  223 */       int r = (int)(60.0F + p * 20.0F), g = (int)(180.0F - p * 60.0F), b = (int)(220.0F - p * 20.0F);
/*  224 */       ctx.method_25294(this.guiLeft + 2 + i, headerY, this.guiLeft + 3 + i, headerY + 2, 
/*  225 */           c(r, g, b, 120 + (int)(60.0D * Math.sin(i * 0.05D))));
/*      */     } 
/*      */ 
/*      */     
/*  229 */     int tabY = headerY + 5, tabH = 14, tabSpacing = 3;
/*  230 */     int tabX = this.guiLeft + 8;
/*      */     
/*  232 */     String battleText = "Battle";
/*  233 */     int battleTabW = this.field_22793.method_1727(battleText) + 12;
/*  234 */     this.battleTabHovered = (mouseX >= tabX && mouseX < tabX + battleTabW && mouseY >= tabY && mouseY < tabY + tabH);
/*  235 */     drawTabButton(ctx, tabX, tabY, battleTabW, tabH, battleText, true, this.battleTabHovered);
/*  236 */     tabX += battleTabW + tabSpacing;
/*      */     
/*  238 */     String defenseText = "Defense";
/*  239 */     int defenseTabW = this.field_22793.method_1727(defenseText) + 12;
/*  240 */     this.defenseTabHovered = (mouseX >= tabX && mouseX < tabX + defenseTabW && mouseY >= tabY && mouseY < tabY + tabH);
/*  241 */     drawTabButton(ctx, tabX, tabY, defenseTabW, tabH, defenseText, false, this.defenseTabHovered);
/*  242 */     tabX += defenseTabW + tabSpacing;
/*      */     
/*  244 */     String lbText = "Leaderboard";
/*  245 */     int lbTabW = this.field_22793.method_1727(lbText) + 12;
/*  246 */     this.leaderboardTabHovered = (mouseX >= tabX && mouseX < tabX + lbTabW && mouseY >= tabY && mouseY < tabY + tabH);
/*  247 */     drawTabButton(ctx, tabX, tabY, lbTabW, tabH, lbText, false, this.leaderboardTabHovered);
/*  248 */     tabX += lbTabW + tabSpacing;
/*      */     
/*  250 */     String shopText = "Shop";
/*  251 */     int shopTabW = this.field_22793.method_1727(shopText) + 12;
/*  252 */     this.shopTabHovered = (mouseX >= tabX && mouseX < tabX + shopTabW && mouseY >= tabY && mouseY < tabY + tabH);
/*  253 */     drawTabButton(ctx, tabX, tabY, shopTabW, tabH, shopText, false, this.shopTabHovered);
/*  254 */     tabX += shopTabW + tabSpacing;
/*      */     
/*  256 */     String historyText = "History";
/*  257 */     int historyTabW = this.field_22793.method_1727(historyText) + 12;
/*  258 */     this.historyTabHovered = (mouseX >= tabX && mouseX < tabX + historyTabW && mouseY >= tabY && mouseY < tabY + tabH);
/*  259 */     drawTabButton(ctx, tabX, tabY, historyTabW, tabH, historyText, false, this.historyTabHovered);
/*      */ 
/*      */     
/*  262 */     PlayerRankedData data = PlayerRankedData.getInstance();
/*  263 */     String trophyText = " " + data.getTrophies() + " Trophies";
/*  264 */     int trophyTextW = this.field_22793.method_1727(trophyText);
/*  265 */     int trophyIconSize = 9;
/*  266 */     int totalTrophyW = trophyIconSize + trophyTextW;
/*  267 */     int trophyX = this.guiLeft + 390 - totalTrophyW - 10;
/*  268 */     int trophyY = headerY + 8;
/*  269 */     RankedIcon.TROPHY.drawTexture(ctx, trophyX, trophyY - 1, trophyIconSize, trophyIconSize);
/*  270 */     ctx.method_51433(this.field_22793, trophyText, trophyX + trophyIconSize, trophyY, ACCENT_GOLD, true);
/*      */ 
/*      */     
/*  273 */     int sepY = headerY + headerH - 1;
/*  274 */     for (int j = 0; j < 378; j++) {
/*  275 */       float p = j / 378.0F;
/*  276 */       int r = (int)(60.0F + p * 20.0F), g = (int)(180.0F - p * 60.0F), b = (int)(220.0F - p * 20.0F);
/*  277 */       ctx.method_25294(this.guiLeft + 6 + j, sepY, this.guiLeft + 7 + j, sepY + 1, 
/*  278 */           c(r, g, b, 150 + (int)(50.0D * Math.sin(j * 0.1D))));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void drawSeasonBanner(class_332 ctx, PlayerRankedData data) {
/*  285 */     int x = this.guiLeft + 6;
/*  286 */     int y = this.guiTop + 28;
/*  287 */     int w = 378;
/*  288 */     int bannerH = 20;
/*      */     
/*  290 */     ctx.method_25294(x, y, x + w, y + bannerH, c(15, 18, 30, 240));
/*  291 */     ctx.method_25294(x, y + bannerH / 2, x + w, y + bannerH, c(12, 14, 24, 240));
/*  292 */     ctx.method_25294(x, y, x + w, y + 2, ACCENT_CRIMSON);
/*  293 */     int ga = ACCENT_CRIMSON & 0xFFFFFF | 0x1E000000;
/*  294 */     ctx.method_25294(x, y + 2, x + w, y + 4, ga);
/*  295 */     ctx.method_25294(x, y, x + 1, y + bannerH, c(40, 50, 80, 180));
/*  296 */     ctx.method_25294(x + w - 1, y, x + w, y + bannerH, c(40, 50, 80, 120));
/*  297 */     ctx.method_25294(x, y + bannerH - 1, x + w, y + bannerH, c(40, 50, 80, 120));
/*      */ 
/*      */     
/*  300 */     int textAreaTop = y + 4;
/*  301 */     int textAreaH = bannerH - 4;
/*      */     
/*  303 */     String seasonLabel = data.getSeasonDisplayName();
/*  304 */     int seasonTextY = textAreaTop + (textAreaH - 9) / 2;
/*  305 */     ctx.method_51433(this.field_22793, "§l" + seasonLabel, x + 5, seasonTextY, ACCENT_CRIMSON, true);
/*      */     
/*  307 */     String countdown = formatCountdown(data.getSeasonEndEpoch());
/*  308 */     int countdownColor = getCountdownColor(data.getSeasonEndEpoch());
/*  309 */     String countdownLabel = "Ends in: ";
/*  310 */     int labelW = this.field_22793.method_1727(countdownLabel);
/*  311 */     int cdW = this.field_22793.method_1727(countdown);
/*  312 */     int rightEdge = x + w - 5;
/*  313 */     int cdTextY = textAreaTop + (textAreaH - 9) / 2;
/*  314 */     ctx.method_51433(this.field_22793, countdownLabel, rightEdge - cdW - labelW, cdTextY, TEXT_DIM, true);
/*  315 */     ctx.method_51433(this.field_22793, countdown, rightEdge - cdW, cdTextY, countdownColor, true);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void drawRankCard(class_332 ctx, PlayerRankedData data) {
/*  321 */     int x = this.guiLeft + 6;
/*  322 */     int y = this.guiTop + 52;
/*  323 */     int w = 378;
/*  324 */     int cardH = 40;
/*      */     
/*  326 */     ctx.method_25294(x, y, x + w, y + cardH, SECTION_BG);
/*  327 */     for (int row = 0; row < cardH; row++) {
/*  328 */       float p = row / cardH;
/*  329 */       ctx.method_25294(x + 1, y + row, x + w - 1, y + row + 1, c(30, 40, 60, (int)(10.0F + p * 15.0F)));
/*      */     } 
/*      */     
/*  332 */     RankedRank rank = data.getCurrentRank();
/*  333 */     int rankColor = rankColorFromHex(rank.getIconColor());
/*      */ 
/*      */     
/*  336 */     long now = System.currentTimeMillis();
/*  337 */     float breathe = (float)Math.sin(now / 500.0D) * 0.5F + 0.5F;
/*  338 */     int glowAlpha = (int)(30.0F + breathe * 40.0F);
/*  339 */     int glowCol = rankColor & 0xFFFFFF | glowAlpha << 24;
/*  340 */     ctx.method_25294(x - 2, y - 2, x + w + 2, y - 1, glowCol);
/*  341 */     ctx.method_25294(x - 2, y + cardH + 1, x + w + 2, y + cardH + 2, glowCol);
/*  342 */     ctx.method_25294(x - 2, y - 2, x - 1, y + cardH + 2, glowCol);
/*  343 */     ctx.method_25294(x + w + 1, y - 2, x + w + 2, y + cardH + 2, glowCol);
/*      */     
/*  345 */     ctx.method_25294(x, y, x + w, y + 2, rankColor);
/*  346 */     ctx.method_25294(x, y + cardH - 1, x + w, y + cardH, RankedStyledButton.darken(rankColor, 40));
/*  347 */     ctx.method_25294(x, y, x + 1, y + cardH, rankColor);
/*  348 */     ctx.method_25294(x + w - 1, y, x + w, y + cardH, RankedStyledButton.darken(rankColor, 40));
/*      */ 
/*      */     
/*  351 */     int badgeSize = 16;
/*  352 */     int badgeX = x + 6;
/*  353 */     int badgeY = y + (cardH - badgeSize) / 2;
/*  354 */     RankedIcon badge = RankedIcon.badgeForRank(rank);
/*  355 */     if (badge != null) {
/*  356 */       badge.drawTexture(ctx, badgeX, badgeY, badgeSize, badgeSize);
/*      */     } else {
/*  358 */       drawDiamondGlow(ctx, badgeX + 8, badgeY + 8, 7, rankColor, (int)(60.0F + breathe * 40.0F));
/*  359 */       drawDiamond(ctx, badgeX + 8, badgeY + 8, 4, rankColor);
/*      */     } 
/*      */     
/*  362 */     int textX = badgeX + badgeSize + 6;
/*  363 */     String rankPrefix = "Rank: ";
/*  364 */     ctx.method_51433(this.field_22793, "§l" + rankPrefix, textX, y + 5, rankColor, true);
/*  365 */     int rankPrefixW = this.field_22793.method_1727("§l" + rankPrefix);
/*  366 */     RankedIcon rankTag = RankedIcon.tagForRank(rank);
/*  367 */     if (rankTag != null) {
/*  368 */       rankTag.drawTexture(ctx, textX + rankPrefixW, y + 6);
/*      */     } else {
/*  370 */       ctx.method_51433(this.field_22793, "§l" + rank.getDisplayName(), textX + rankPrefixW, y + 5, rankColor, true);
/*      */     } 
/*      */     
/*  373 */     String eloText = "Elo: " + data.getElo();
/*  374 */     ctx.method_51433(this.field_22793, eloText, textX, y + 17, TEXT_SECONDARY, true);
/*  375 */     String recordText = "W: " + data.getWins() + " / L: " + data.getLosses();
/*  376 */     int recordX = textX + this.field_22793.method_1727(eloText) + 12;
/*  377 */     ctx.method_51433(this.field_22793, recordText, recordX, y + 17, TEXT_DIM, true);
/*      */ 
/*      */     
/*  380 */     int barX = textX;
/*  381 */     int barY = y + 29;
/*  382 */     int barW = 120;
/*  383 */     int barH = 4;
/*      */ 
/*      */     
/*  386 */     float progress = (rank.getMaxElo() > rank.getMinElo()) ? Math.max(0.0F, Math.min(1.0F, (data.getElo() - rank.getMinElo()) / (rank.getMaxElo() - rank.getMinElo()))) : 1.0F;
/*  387 */     ctx.method_25294(barX, barY, barX + barW, barY + barH, c(20, 25, 40, 200));
/*  388 */     ctx.method_25294(barX, barY, barX + barW, barY + 1, c(30, 35, 55, 150));
/*  389 */     int fillW = (int)(barW * progress);
/*  390 */     if (fillW > 0) {
/*  391 */       ctx.method_25294(barX, barY, barX + fillW, barY + barH, rankColor);
/*  392 */       ctx.method_25294(barX, barY, barX + fillW, barY + 1, RankedStyledButton.lighten(rankColor, 40));
/*      */     } 
/*      */ 
/*      */     
/*  396 */     if (data.isInPlacement()) {
/*  397 */       String placementText = "Placement: " + data.getPlacementMatchesRemaining() + " left";
/*  398 */       int placementX = x + w - this.field_22793.method_1727(placementText) - 8;
/*  399 */       ctx.method_51433(this.field_22793, placementText, placementX, y + 5, ACCENT_GOLD, true);
/*      */     } 
/*      */     
/*  402 */     if (data.getWinStreak() > 1) {
/*  403 */       String streakText = "⭐ " + data.getWinStreak() + " Win Streak";
/*  404 */       int streakX = x + w - this.field_22793.method_1727(streakText) - 8;
/*  405 */       ctx.method_51433(this.field_22793, streakText, streakX, y + 17, ACCENT_GREEN, true);
/*  406 */     } else if (data.getLossStreak() > 1) {
/*  407 */       String streakText = "" + data.getLossStreak() + " Loss Streak";
/*  408 */       int streakX = x + w - this.field_22793.method_1727(streakText) - 8;
/*  409 */       ctx.method_51433(this.field_22793, streakText, streakX, y + 17, ACCENT_RED, true);
/*      */     } 
/*      */     
/*  412 */     if (rank.getMaxElo() < Integer.MAX_VALUE) {
/*  413 */       int needed = rank.getMaxElo() + 1 - data.getElo();
/*  414 */       if (needed > 0) {
/*  415 */         String nextText = "Next: +" + needed + " elo";
/*  416 */         int nextX = x + w - this.field_22793.method_1727(nextText) - 8;
/*  417 */         ctx.method_51433(this.field_22793, nextText, nextX, y + 29, TEXT_DIM, true);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void drawOpponentCards(class_332 ctx, PlayerRankedData data, int mouseX, int mouseY, float delta) {
/*  425 */     int x = this.guiLeft + 6;
/*  426 */     int y = this.guiTop + 96;
/*  427 */     int w = 378;
/*  428 */     int cardH = 54;
/*  429 */     int spacing = 3;
/*      */     
/*  431 */     List<RankedNetwork.OpponentPayload> opponents = data.getOpponents();
/*      */     
/*  433 */     for (int i = 0; i < 3; i++) {
/*  434 */       int cardY = y + i * (cardH + spacing);
/*  435 */       RankedNetwork.OpponentPayload opp = (i < opponents.size()) ? opponents.get(i) : null;
/*      */ 
/*      */       
/*  438 */       ctx.method_25294(x, cardY, x + w, cardY + cardH, SECTION_BG);
/*  439 */       for (int row = 0; row < cardH; row++) {
/*  440 */         float p = row / cardH;
/*  441 */         ctx.method_25294(x + 1, cardY + row, x + w - 1, cardY + row + 1, c(30, 40, 60, (int)(8.0F + p * 12.0F)));
/*      */       } 
/*      */       
/*  444 */       int cardBorder = BORDER_COLOR;
/*  445 */       if (opp != null && opp.defeated()) {
/*  446 */         cardBorder = opp.won() ? ACCENT_GREEN : ACCENT_RED;
/*      */       }
/*      */ 
/*      */       
/*  450 */       int accentColor = ACCENT_CRIMSON;
/*  451 */       if (opp != null) {
/*  452 */         RankedRank oppRank = RankedRank.getRankForElo(opp.elo());
/*  453 */         accentColor = rankColorFromHex(oppRank.getIconColor());
/*      */       } 
/*      */ 
/*      */       
/*  457 */       ctx.method_25294(x, cardY, x + w, cardY + 1, cardBorder);
/*  458 */       ctx.method_25294(x, cardY + cardH - 1, x + w, cardY + cardH, RankedStyledButton.darken(cardBorder, 30));
/*  459 */       ctx.method_25294(x + w - 1, cardY, x + w, cardY + cardH, RankedStyledButton.darken(cardBorder, 30));
/*      */ 
/*      */       
/*  462 */       ctx.method_25294(x, cardY, x + 3, cardY + cardH, accentColor);
/*      */ 
/*      */       
/*  465 */       boolean cardHovered = (opp != null && !opp.defeated() && mouseX >= x && mouseX < x + w && mouseY >= cardY && mouseY < cardY + cardH);
/*      */       
/*  467 */       if (cardHovered) {
/*  468 */         for (int g = 3; g > 0; g--) {
/*  469 */           int alpha = 20 * g;
/*  470 */           int gc = ACCENT_ORANGE & 0xFFFFFF | alpha << 24;
/*  471 */           ctx.method_25294(x - g, cardY - g, x + w + g, cardY - g + 1, gc);
/*  472 */           ctx.method_25294(x - g, cardY + cardH + g - 1, x + w + g, cardY + cardH + g, gc);
/*  473 */           ctx.method_25294(x - g, cardY - g, x - g + 1, cardY + cardH + g, gc);
/*  474 */           ctx.method_25294(x + w + g - 1, cardY - g, x + w + g, cardY + cardH + g, gc);
/*      */         } 
/*      */       }
/*      */       
/*  478 */       if (opp == null) {
/*  479 */         ctx.method_25300(this.field_22793, "No opponent", x + w / 2, cardY + cardH / 2 - 4, TEXT_DIM);
/*      */       
/*      */       }
/*      */       else {
/*      */         
/*  484 */         if (opp.defeated()) {
/*  485 */           int overlayColor = opp.won() ? c(0, 80, 0, 50) : c(80, 0, 0, 50);
/*  486 */           ctx.method_25294(x + 1, cardY + 1, x + w - 1, cardY + cardH - 1, overlayColor);
/*      */         } 
/*      */ 
/*      */         
/*  490 */         int faceX = x + 8;
/*  491 */         int faceSize = 16;
/*  492 */         int faceY = cardY + (cardH - faceSize) / 2;
/*  493 */         class_2960 skinTex = getSkinTexture(opp.uuid(), opp.name());
/*  494 */         drawPlayerFace(ctx, skinTex, faceX, faceY, faceSize);
/*      */         
/*  496 */         int infoX = faceX + faceSize + 4;
/*      */ 
/*      */         
/*  499 */         String name = opp.name();
/*  500 */         int maxNameW = 140;
/*  501 */         if (this.field_22793.method_1727(name) > maxNameW) {
/*  502 */           while (this.field_22793.method_1727(name + "...") > maxNameW && name.length() > 1)
/*  503 */             name = name.substring(0, name.length() - 1); 
/*  504 */           name = name + "...";
/*      */         } 
/*      */ 
/*      */         
/*  508 */         RankedRank oppRank = RankedRank.getRankForElo(opp.elo());
/*  509 */         int oppRankColor = rankColorFromHex(oppRank.getIconColor());
/*      */ 
/*      */         
/*  512 */         int playerElo = PlayerRankedData.getInstance().getElo();
/*  513 */         int expectedGain = estimateEloChange(playerElo, opp.elo(), true);
/*  514 */         int expectedLoss = estimateEloChange(playerElo, opp.elo(), false);
/*  515 */         int winTrophies = oppRank.getWinTrophies();
/*      */ 
/*      */         
/*  518 */         class_5250 class_52501 = class_2561.method_43470("Win: ").method_27694(s -> s.method_36139(8437952)).method_10852((class_2561)class_2561.method_43470("+" + expectedGain + " Elo").method_27694(s -> s.method_36139(5308310))).method_10852((class_2561)class_2561.method_43470(" +" + winTrophies + " ").method_27694(s -> s.method_36139(16762960)));
/*      */         
/*  520 */         class_5250 class_52502 = class_2561.method_43470("Loss: ").method_27694(s -> s.method_36139(8437952)).method_10852((class_2561)class_2561.method_43470("" + expectedLoss + " Elo").method_27694(s -> s.method_36139(14430770)));
/*      */ 
/*      */         
/*  523 */         int textBlockH = 40;
/*  524 */         int textBlockTopY = cardY + (cardH - textBlockH) / 2;
/*  525 */         ctx.method_51433(this.field_22793, "§l" + name, infoX, textBlockTopY, TEXT_PRIMARY, true);
/*      */ 
/*      */         
/*  528 */         RankedIcon oppTag = RankedIcon.tagForRank(oppRank);
/*  529 */         if (oppTag != null) {
/*  530 */           oppTag.drawTexture(ctx, infoX, textBlockTopY + 11);
/*      */         } else {
/*  532 */           ctx.method_51433(this.field_22793, oppRank.getDisplayName(), infoX, textBlockTopY + 11, oppRankColor, true);
/*      */         } 
/*      */         
/*  535 */         int winLineY = textBlockTopY + 21;
/*  536 */         ctx.method_51439(this.field_22793, (class_2561)class_52501, infoX, winLineY, TEXT_PRIMARY, true);
/*  537 */         int winLineW = this.field_22793.method_27525((class_5348)class_52501);
/*  538 */         RankedIcon.TROPHY.drawTexture(ctx, infoX + winLineW + 1, winLineY - 1, 9, 9);
/*  539 */         ctx.method_51439(this.field_22793, (class_2561)class_52502, infoX, textBlockTopY + 31, TEXT_PRIMARY, true);
/*      */ 
/*      */         
/*  542 */         int gridCellSize = 22;
/*  543 */         int gridSpacing = 1;
/*  544 */         int gridX = x + 165;
/*  545 */         int gridY = cardY + (cardH - gridCellSize) / 2;
/*      */         
/*  547 */         int visiblePartyCount = getVisiblePartyCount(oppRank.getTier());
/*  548 */         drawPartyGrid(ctx, opp, gridX, gridY, gridCellSize, gridSpacing, i, visiblePartyCount, delta, mouseX, mouseY);
/*      */ 
/*      */         
/*  551 */         if (!opp.defeated()) {
/*  552 */           int btnW = 60;
/*  553 */           int btnH = 20;
/*  554 */           int btnX = x + w - btnW - 8;
/*  555 */           int btnY = cardY + (cardH - btnH) / 2;
/*  556 */           this.challengeHovered[i] = drawButton(ctx, btnX, btnY, btnW, btnH, "§aChallenge", mouseX, mouseY, true);
/*      */         } else {
/*      */           
/*  559 */           int btnCenterX = x + w - 8 - 30;
/*  560 */           String resultLabel = opp.won() ? "WIN" : "LOSS";
/*  561 */           int resultColor = opp.won() ? ACCENT_GREEN : ACCENT_RED;
/*  562 */           int resultBg = opp.won() ? c(40, 160, 80, 100) : c(160, 40, 40, 100);
/*  563 */           int resultW = this.field_22793.method_1727(resultLabel) + 8;
/*  564 */           int resultH = 14;
/*  565 */           int resultX = btnCenterX - resultW / 2;
/*  566 */           int resultY = cardY + cardH / 2 - resultH / 2;
/*  567 */           ctx.method_25294(resultX, resultY, resultX + resultW, resultY + resultH, resultBg);
/*  568 */           ctx.method_49601(resultX, resultY, resultW, resultH, resultColor);
/*  569 */           ctx.method_25300(this.field_22793, "§l" + resultLabel, resultX + resultW / 2, resultY + 3, resultColor);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void drawPartyGrid(class_332 ctx, RankedNetwork.OpponentPayload opp, int gridX, int gridY, int cellSize, int cellSpacing, int oppIndex, int visibleCount, float delta, int mouseX, int mouseY) {
/*  580 */     List<String> party = opp.partySpecies();
/*  581 */     if (party == null) party = List.of();
/*      */     
/*  583 */     for (int j = 0; j < 6; j++) {
/*  584 */       int cx = gridX + j * (cellSize + cellSpacing);
/*  585 */       int cy = gridY;
/*  586 */       boolean hovered = (mouseX >= cx && mouseX < cx + cellSize && mouseY >= cy && mouseY < cy + cellSize);
/*      */       
/*  588 */       if (j < visibleCount && j < party.size()) {
/*      */         
/*  590 */         String species = party.get(j);
/*  591 */         ctx.method_25294(cx, cy, cx + cellSize, cy + cellSize, c(12, 15, 25, 200));
/*  592 */         ctx.method_25294(cx, cy, cx + cellSize, cy + 1, c(35, 45, 70, 200));
/*  593 */         if (hovered) {
/*  594 */           ctx.method_25294(cx, cy, cx + cellSize, cy + cellSize, c(60, 80, 120, 40));
/*      */         }
/*  596 */         renderPokemonInCell(ctx, species, cx, cy, cellSize, oppIndex * 6 + j, delta);
/*      */         
/*  598 */         if (hovered) {
/*  599 */           String label = species.contains(":") ? species.substring(species.indexOf(":") + 1) : species;
/*      */           
/*  601 */           if (!label.isEmpty()) label = label.substring(0, 1).toUpperCase() + label.substring(0, 1).toUpperCase(); 
/*  602 */           this.tooltipText = label;
/*  603 */           this.tooltipX = mouseX;
/*  604 */           this.tooltipY = mouseY;
/*      */         } 
/*      */       } else {
/*      */         
/*  608 */         ctx.method_25294(cx, cy, cx + cellSize, cy + cellSize, c(20, 22, 35, 200));
/*  609 */         ctx.method_49601(cx, cy, cellSize, cellSize, c(40, 45, 65, 150));
/*      */         
/*  611 */         class_4587 xMat = ctx.method_51448();
/*  612 */         xMat.method_22903();
/*  613 */         float xScale = 1.5F;
/*  614 */         xMat.method_22905(xScale, xScale, 1.0F);
/*  615 */         String xChar = "✖";
/*  616 */         int xCharW = this.field_22793.method_1727("§l" + xChar);
/*  617 */         int xDrawX = (int)((cx + cellSize / 2.0F + 1.0F) / xScale) - xCharW / 2;
/*  618 */         int xDrawY = (int)((cy + cellSize / 2.0F - 5.0F) / xScale);
/*  619 */         ctx.method_51433(this.field_22793, "§l" + xChar, xDrawX, xDrawY, ACCENT_RED, true);
/*  620 */         xMat.method_22909();
/*      */         
/*  622 */         if (hovered) {
/*  623 */           this.tooltipText = "Hidden";
/*  624 */           this.tooltipX = mouseX;
/*  625 */           this.tooltipY = mouseY;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int getVisiblePartyCount(String tier) {
/*  636 */     switch (tier) { case "Bronze": case "Silver": case "Gold": case "Diamond": case "Master": case "Champion": case "Legend":  }  return 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  641 */       6;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void renderPokemonInCell(class_332 ctx, String speciesStr, int sx, int sy, int ss, int stateIndex, float delta) {
/*  648 */     if (speciesStr == null || speciesStr.isEmpty() || speciesStr.equals("Unknown")) {
/*  649 */       ctx.method_25300(this.field_22793, "?", sx + ss / 2, sy + ss / 2 - 4, TEXT_DIM);
/*      */       return;
/*      */     } 
/*      */     try {
/*  653 */       class_2960 speciesId = class_2960.method_60654(speciesStr);
/*  654 */       FloatingState state = this.pokemonStates.get(Integer.valueOf(stateIndex));
/*  655 */       if (state == null)
/*  656 */         return;  state.setCurrentAspects(Set.of());
/*  657 */       class_4587 mat = ctx.method_51448();
/*  658 */       mat.method_22903();
/*  659 */       mat.method_22904(sx + ss / 2.0D, sy, 0.0D);
/*  660 */       mat.method_22905(2.5F, 2.5F, 1.0F);
/*  661 */       Quaternionf rot = new Quaternionf();
/*  662 */       rot.rotationXYZ((float)Math.toRadians(13.0D), (float)Math.toRadians(35.0D), 0.0F);
/*  663 */       if (PokemonRenderHelper.isAvailable()) {
/*  664 */         PokemonRenderHelper.draw(speciesId, mat, rot, state, delta);
/*      */       } else {
/*  666 */         drawFallback(ctx, speciesStr, sx, sy, ss);
/*      */       } 
/*  668 */       mat.method_22909();
/*  669 */     } catch (Exception e) {
/*  670 */       drawFallback(ctx, speciesStr, sx, sy, ss);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void drawFallback(class_332 ctx, String species, int x, int y, int s) {
/*  675 */     String label = species.contains(":") ? species.substring(species.indexOf(":") + 1) : species;
/*  676 */     String initial = label.isEmpty() ? "?" : label.substring(0, 1).toUpperCase();
/*  677 */     ctx.method_25300(this.field_22793, initial, x + s / 2, y + s / 2 - 4, TEXT_PRIMARY);
/*      */   }
/*      */   
/*      */   private int estimateEloChange(int playerElo, int opponentElo, boolean win) {
/*  681 */     double expected = 1.0D / (1.0D + Math.pow(10.0D, (opponentElo - playerElo) / 400.0D));
/*  682 */     double actual = win ? 1.0D : 0.0D;
/*  683 */     int K = (playerElo < 1200) ? 40 : ((playerElo < 2000) ? 32 : ((playerElo < 2800) ? 24 : 16));
/*  684 */     return (int)Math.round(K * (actual - expected));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void drawBottomArea(class_332 ctx, PlayerRankedData data, int mouseX, int mouseY) {
/*  690 */     RankedRank rank = data.getCurrentRank();
/*      */ 
/*      */     
/*  693 */     int sepY = this.guiTop + 330 - 59;
/*  694 */     drawDiamondGlow(ctx, this.guiLeft + 195, sepY, 3, ACCENT_ORANGE, 50);
/*  695 */     drawDiamond(ctx, this.guiLeft + 195, sepY, 2, ACCENT_ORANGE);
/*  696 */     for (int i = 0; i < 175; i++) {
/*  697 */       float alpha = 120.0F * (1.0F - i / 175.0F);
/*  698 */       int lineCol = ACCENT_ORANGE & 0xFFFFFF | (int)alpha << 24;
/*  699 */       ctx.method_25294(this.guiLeft + 195 + 6 + i, sepY, this.guiLeft + 195 + 7 + i, sepY + 1, lineCol);
/*  700 */       ctx.method_25294(this.guiLeft + 195 - 6 - i, sepY, this.guiLeft + 195 - 5 - i, sepY + 1, lineCol);
/*      */     } 
/*      */ 
/*      */     
/*  704 */     int infoY = this.guiTop + 330 - 51;
/*  705 */     String infoText = "Refresh: " + data.getRefreshTickets() + "/5";
/*      */ 
/*      */     
/*  708 */     String timerLabel = "";
/*  709 */     int timerColor = TEXT_SECONDARY;
/*  710 */     if (data.getRefreshTickets() < 5) {
/*  711 */       long nextRefreshIn = data.getLastFreeRefreshTime() + data.getFreeRefreshIntervalMs() - System.currentTimeMillis();
/*  712 */       if (nextRefreshIn > 0L) {
/*  713 */         long totalMin = nextRefreshIn / 60000L;
/*  714 */         long hours = totalMin / 60L;
/*  715 */         long mins = totalMin % 60L;
/*  716 */         timerLabel = "Next in: " + ((hours > 0L) ? ("" + hours + "h ") : "") + mins + "m";
/*  717 */         timerColor = (nextRefreshIn < 3600000L) ? ACCENT_GOLD : ACCENT_CYAN;
/*      */       } else {
/*  719 */         timerLabel = "Ready!";
/*  720 */         timerColor = ACCENT_GREEN;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  725 */     int centerX = this.guiLeft + 195;
/*  726 */     if (!timerLabel.isEmpty()) {
/*      */       
/*  728 */       String separator = "|";
/*  729 */       int sepW = this.field_22793.method_1727(separator);
/*  730 */       int sepX = centerX - sepW / 2 + 1;
/*  731 */       ctx.method_51433(this.field_22793, separator, sepX, infoY, TEXT_PRIMARY, true);
/*      */       
/*  733 */       int refreshX = sepX - this.field_22793.method_1727(infoText) - 4;
/*  734 */       ctx.method_51433(this.field_22793, infoText, refreshX, infoY, TEXT_SECONDARY, true);
/*      */       
/*  736 */       int timerX = sepX + sepW + 4;
/*  737 */       ctx.method_51433(this.field_22793, timerLabel, timerX, infoY, timerColor, true);
/*      */     } else {
/*      */       
/*  740 */       int infoW = this.field_22793.method_1727(infoText);
/*  741 */       ctx.method_51433(this.field_22793, infoText, centerX - infoW / 2, infoY, TEXT_SECONDARY, true);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  746 */     int sep2Y = this.guiTop + 330 - 37;
/*  747 */     for (int j = 0; j < 370; j++) {
/*  748 */       float p = j / 370.0F;
/*  749 */       float alpha = 80.0F * (float)Math.sin(p * Math.PI);
/*  750 */       int lineCol = BORDER_COLOR & 0xFFFFFF | (int)alpha << 24;
/*  751 */       ctx.method_25294(this.guiLeft + 10 + j, sep2Y, this.guiLeft + 11 + j, sep2Y + 1, lineCol);
/*      */     } 
/*      */ 
/*      */     
/*  755 */     int btnY = this.guiTop + 330 - 29;
/*  756 */     int btnH = 20;
/*      */ 
/*      */     
/*  759 */     class_5250 gemIcon = MiscellaneousFontIcon.GEM.draw();
/*  760 */     String costText = String.valueOf(rank.getRefreshCostGems());
/*  761 */     class_5250 class_52501 = class_2561.method_43470("Refresh (").method_27693(costText).method_10852((class_2561)gemIcon).method_10852((class_2561)class_2561.method_43470(")"));
/*  762 */     int gemsBtnW = this.field_22793.method_27525((class_5348)class_52501) + 10;
/*      */     
/*  764 */     this.refreshGemsHovered = drawButtonText(ctx, this.guiLeft + 8, btnY, gemsBtnW, btnH, (class_2561)class_52501, mouseX, mouseY, true);
/*      */ 
/*      */     
/*  767 */     boolean hasFreeTicket = (data.getRefreshTickets() > 0);
/*  768 */     String freeLabel = "Free Refresh";
/*  769 */     int freeBtnW = this.field_22793.method_1727(freeLabel) + 10;
/*  770 */     int freeBtnX = this.guiLeft + (390 - freeBtnW) / 2;
/*  771 */     this.refreshFreeHovered = drawButton(ctx, freeBtnX, btnY, freeBtnW, btnH, 
/*  772 */         hasFreeTicket ? ("§a" + freeLabel) : freeLabel, mouseX, mouseY, hasFreeTicket);
/*      */ 
/*      */     
/*  775 */     String closeLabel = "Close";
/*  776 */     int closeBtnW = 50;
/*  777 */     int closeBtnX = this.guiLeft + 390 - closeBtnW - 8;
/*  778 */     this.closeHovered = drawButton(ctx, closeBtnX, btnY, closeBtnW, btnH, closeLabel, mouseX, mouseY, true);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void drawRefreshConfirmPopup(class_332 ctx, int mouseX, int mouseY) {
/*  784 */     class_4587 matrices = ctx.method_51448();
/*  785 */     matrices.method_22903();
/*  786 */     matrices.method_46416(0.0F, 0.0F, 400.0F);
/*  787 */     ctx.method_25294(0, 0, this.field_22789, this.field_22790, c(0, 0, 0, 150));
/*      */     
/*  789 */     int popupW = 260;
/*  790 */     int popupH = 80;
/*  791 */     int popupX = (this.field_22789 - popupW) / 2;
/*  792 */     int popupY = (this.field_22790 - popupH) / 2;
/*      */ 
/*      */     
/*  795 */     ctx.method_25294(popupX, popupY, popupX + popupW, popupY + popupH, c(14, 16, 26, 255));
/*      */ 
/*      */     
/*  798 */     int borderCol = ACCENT_ORANGE;
/*  799 */     ctx.method_25294(popupX, popupY, popupX + popupW, popupY + 2, borderCol);
/*  800 */     ctx.method_25294(popupX, popupY + popupH - 2, popupX + popupW, popupY + popupH, borderCol);
/*  801 */     ctx.method_25294(popupX, popupY, popupX + 2, popupY + popupH, borderCol);
/*  802 */     ctx.method_25294(popupX + popupW - 2, popupY, popupX + popupW, popupY + popupH, borderCol);
/*      */ 
/*      */     
/*  805 */     PlayerRankedData data = PlayerRankedData.getInstance();
/*  806 */     if (this.refreshConfirmIsFree) {
/*  807 */       ctx.method_25300(this.field_22793, "§lUse Free Refresh?", popupX + popupW / 2, popupY + 10, ACCENT_GOLD);
/*      */       
/*  809 */       String bodyText = "Tickets remaining: " + data.getRefreshTickets() + "/5";
/*  810 */       ctx.method_25300(this.field_22793, bodyText, popupX + popupW / 2, popupY + 26, TEXT_SECONDARY);
/*      */     } else {
/*      */       
/*  813 */       ctx.method_25300(this.field_22793, "§lRefresh Opponents?", popupX + popupW / 2, popupY + 10, ACCENT_GOLD);
/*      */ 
/*      */       
/*  816 */       RankedRank rank = data.getCurrentRank();
/*  817 */       class_5250 gemIcon = MiscellaneousFontIcon.GEM.draw();
/*  818 */       class_5250 class_52501 = class_2561.method_43470("").method_10852((class_2561)gemIcon).method_27693(" " + rank.getRefreshCostGems() + " PokeGems");
/*  819 */       int costW = this.field_22793.method_27525((class_5348)class_52501);
/*  820 */       ctx.method_51439(this.field_22793, (class_2561)class_52501, popupX + (popupW - costW) / 2, popupY + 26, TEXT_SECONDARY, true);
/*      */     } 
/*      */ 
/*      */     
/*  824 */     int btnW = 60, btnH = 18, btnGap = 10;
/*  825 */     int confirmX = popupX + popupW / 2 - btnW - btnGap / 2;
/*  826 */     int cancelX = popupX + popupW / 2 + btnGap / 2;
/*  827 */     int btnBY = popupY + popupH - 24;
/*      */     
/*  829 */     this.confirmYesHovered = (mouseX >= confirmX && mouseX < confirmX + btnW && mouseY >= btnBY && mouseY < btnBY + btnH);
/*  830 */     this.confirmNoHovered = (mouseX >= cancelX && mouseX < cancelX + btnW && mouseY >= btnBY && mouseY < btnBY + btnH);
/*      */     
/*  832 */     ctx.method_25294(confirmX, btnBY, confirmX + btnW, btnBY + btnH, this.confirmYesHovered ? c(60, 200, 120, 255) : c(40, 160, 80, 255));
/*  833 */     ctx.method_25294(confirmX, btnBY, confirmX + btnW, btnBY + 1, c(80, 255, 150, 200));
/*  834 */     ctx.method_25300(this.field_22793, "Confirm", confirmX + btnW / 2, btnBY + 5, TEXT_PRIMARY);
/*      */ 
/*      */     
/*  837 */     ctx.method_25294(cancelX, btnBY, cancelX + btnW, btnBY + btnH, this.confirmNoHovered ? c(200, 60, 60, 255) : c(160, 40, 40, 255));
/*  838 */     ctx.method_25294(cancelX, btnBY, cancelX + btnW, btnBY + 1, c(255, 80, 80, 200));
/*  839 */     ctx.method_25300(this.field_22793, "Cancel", cancelX + btnW / 2, btnBY + 5, TEXT_PRIMARY);
/*      */     
/*  841 */     matrices.method_22909();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void drawPopupOverlay(class_332 ctx, int mouseX, int mouseY) {
/*  847 */     class_4587 matrices = ctx.method_51448();
/*  848 */     matrices.method_22903();
/*  849 */     matrices.method_46416(0.0F, 0.0F, 400.0F);
/*  850 */     ctx.method_25294(0, 0, this.field_22789, this.field_22790, c(0, 0, 0, 150));
/*      */     
/*  852 */     int popupW = 240;
/*  853 */     int popupH = 76;
/*  854 */     int popupX = (this.field_22789 - popupW) / 2;
/*  855 */     int popupY = (this.field_22790 - popupH) / 2;
/*      */     
/*  857 */     ctx.method_25294(popupX, popupY, popupX + popupW, popupY + popupH, c(14, 16, 26, 255));
/*  858 */     ctx.method_25294(popupX, popupY, popupX + popupW, popupY + 2, ACCENT_RED);
/*  859 */     ctx.method_25294(popupX, popupY + popupH - 2, popupX + popupW, popupY + popupH, ACCENT_RED);
/*  860 */     ctx.method_25294(popupX, popupY, popupX + 2, popupY + popupH, ACCENT_RED);
/*  861 */     ctx.method_25294(popupX + popupW - 2, popupY, popupX + popupW, popupY + popupH, ACCENT_RED);
/*      */     
/*  863 */     ctx.method_25300(this.field_22793, this.popupMessage, popupX + popupW / 2, popupY + 18, ACCENT_RED);
/*      */     
/*  865 */     int btnW = 60, btnH = 18;
/*  866 */     int btnX = popupX + (popupW - btnW) / 2;
/*  867 */     int btnY = popupY + popupH - 26;
/*  868 */     this.popupOkHovered = (mouseX >= btnX && mouseX < btnX + btnW && mouseY >= btnY && mouseY < btnY + btnH);
/*  869 */     ctx.method_25294(btnX, btnY, btnX + btnW, btnY + btnH, this.popupOkHovered ? c(255, 80, 80, 255) : ACCENT_RED);
/*  870 */     ctx.method_25294(btnX, btnY, btnX + btnW, btnY + 1, c(255, 120, 120, 200));
/*  871 */     ctx.method_25300(this.field_22793, "OK", btnX + btnW / 2, btnY + 5, TEXT_PRIMARY);
/*      */     
/*  873 */     matrices.method_22909();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void drawTabButton(class_332 ctx, int x, int y, int w, int h, String text, boolean active, boolean hov) {
/*  879 */     ctx.method_25294(x, y, x + w, y + h, c(15, 18, 30, 255));
/*  880 */     ctx.method_49601(x, y, w, h, active ? ACCENT_ORANGE : (hov ? BORDER_HIGHLIGHT : BORDER_COLOR));
/*  881 */     if (active) ctx.method_25294(x + 1, y + h - 2, x + w - 1, y + h, ACCENT_ORANGE);
/*      */     
/*  883 */     int tw = this.field_22793.method_1727(text);
/*  884 */     int tx = x + (w - tw) / 2;
/*  885 */     int ty = y + (h - 8) / 2;
/*  886 */     int cx = tx;
/*  887 */     for (int i = 0; i < text.length(); i++) {
/*  888 */       float p = i / Math.max(1, text.length() - 1);
/*  889 */       int r = (int)(60.0F + p * 20.0F), g = (int)(180.0F - p * 60.0F), b = (int)(220.0F - p * 20.0F);
/*  890 */       if (!active && !hov) { r /= 2; g /= 2; b /= 2; }
/*  891 */        ctx.method_51433(this.field_22793, String.valueOf(text.charAt(i)), cx, ty, c(r, g, b, 255), true);
/*  892 */       cx += this.field_22793.method_1727(String.valueOf(text.charAt(i)));
/*      */     } 
/*      */   }
/*      */   
/*      */   private boolean drawButton(class_332 ctx, int x, int y, int w, int h, String text, int mx, int my, boolean en) {
/*  897 */     boolean hov = (en && mx >= x && mx < x + w && my >= y && my < y + h);
/*  898 */     int bg = !en ? RankedStyledButton.getButtonDisabled() : (hov ? RankedStyledButton.getButtonHover() : RankedStyledButton.getButtonBg());
/*  899 */     ctx.method_25294(x + 1, y + 1, x + w - 1, y + h - 1, bg);
/*  900 */     if (en) ctx.method_25294(x + 1, y + h - 3, x + w - 1, y + h - 1, RankedStyledButton.darken(bg, 15)); 
/*  901 */     int bc = hov ? RankedStyledButton.getButtonBorderHover() : RankedStyledButton.getButtonBorder();
/*  902 */     if (!en) bc = c(30, 35, 50, 255); 
/*  903 */     ctx.method_25294(x + 1, y, x + w - 1, y + 1, bc);
/*  904 */     ctx.method_25294(x + 1, y + h - 1, x + w - 1, y + h, RankedStyledButton.darken(bc, 30));
/*  905 */     ctx.method_25294(x, y + 1, x + 1, y + h - 1, bc);
/*  906 */     ctx.method_25294(x + w - 1, y + 1, x + w, y + h - 1, RankedStyledButton.darken(bc, 30));
/*  907 */     if (en) {
/*  908 */       ctx.method_25294(x + 2, y + 1, x + w - 2, y + 2, RankedStyledButton.lighten(bg, 20));
/*  909 */       ctx.method_25294(x + 1, y + 2, x + 2, y + h - 3, RankedStyledButton.lighten(bg, 10));
/*      */     } 
/*  911 */     ctx.method_25294(x, y, x + 1, y + 1, 0); ctx.method_25294(x + w - 1, y, x + w, y + 1, 0);
/*  912 */     ctx.method_25294(x, y + h - 1, x + 1, y + h, 0); ctx.method_25294(x + w - 1, y + h - 1, x + w, y + h, 0);
/*  913 */     ctx.method_25300(this.field_22793, text, x + w / 2, y + (h - 8) / 2, 
/*  914 */         en ? RankedStyledButton.getTextPrimary() : RankedStyledButton.getTextDim());
/*  915 */     return hov;
/*      */   }
/*      */   
/*      */   private boolean drawButtonText(class_332 ctx, int x, int y, int w, int h, class_2561 text, int mx, int my, boolean en) {
/*  919 */     boolean hov = (en && mx >= x && mx < x + w && my >= y && my < y + h);
/*  920 */     int bg = !en ? RankedStyledButton.getButtonDisabled() : (hov ? RankedStyledButton.getButtonHover() : RankedStyledButton.getButtonBg());
/*  921 */     ctx.method_25294(x + 1, y + 1, x + w - 1, y + h - 1, bg);
/*  922 */     if (en) ctx.method_25294(x + 1, y + h - 3, x + w - 1, y + h - 1, RankedStyledButton.darken(bg, 15)); 
/*  923 */     int bc = hov ? RankedStyledButton.getButtonBorderHover() : RankedStyledButton.getButtonBorder();
/*  924 */     if (!en) bc = c(30, 35, 50, 255); 
/*  925 */     ctx.method_25294(x + 1, y, x + w - 1, y + 1, bc);
/*  926 */     ctx.method_25294(x + 1, y + h - 1, x + w - 1, y + h, RankedStyledButton.darken(bc, 30));
/*  927 */     ctx.method_25294(x, y + 1, x + 1, y + h - 1, bc);
/*  928 */     ctx.method_25294(x + w - 1, y + 1, x + w, y + h - 1, RankedStyledButton.darken(bc, 30));
/*  929 */     if (en) {
/*  930 */       ctx.method_25294(x + 2, y + 1, x + w - 2, y + 2, RankedStyledButton.lighten(bg, 20));
/*  931 */       ctx.method_25294(x + 1, y + 2, x + 2, y + h - 3, RankedStyledButton.lighten(bg, 10));
/*      */     } 
/*  933 */     ctx.method_25294(x, y, x + 1, y + 1, 0); ctx.method_25294(x + w - 1, y, x + w, y + 1, 0);
/*  934 */     ctx.method_25294(x, y + h - 1, x + 1, y + h, 0); ctx.method_25294(x + w - 1, y + h - 1, x + w, y + h, 0);
/*  935 */     int textW = this.field_22793.method_27525((class_5348)text);
/*  936 */     ctx.method_51439(this.field_22793, text, x + (w - textW) / 2, y + (h - 8) / 2, 
/*  937 */         en ? RankedStyledButton.getTextPrimary() : RankedStyledButton.getTextDim(), true);
/*  938 */     return hov;
/*      */   }
/*      */   
/*      */   private void drawTooltip(class_332 ctx, String text, int mx, int my) {
/*  942 */     int tw = this.field_22793.method_1727(text);
/*  943 */     int pad = 4;
/*  944 */     int ttW = tw + pad * 2;
/*  945 */     int ttH = 12 + pad;
/*  946 */     int ttX = mx + 8;
/*  947 */     int ttY = my - ttH - 4;
/*      */ 
/*      */     
/*  950 */     if (ttX + ttW > this.field_22789) ttX = mx - ttW - 4; 
/*  951 */     if (ttY < 0) ttY = my + 12;
/*      */     
/*  953 */     class_4587 mat = ctx.method_51448();
/*  954 */     mat.method_22903();
/*  955 */     mat.method_46416(0.0F, 0.0F, 500.0F);
/*  956 */     ctx.method_25294(ttX - 1, ttY - 1, ttX + ttW + 1, ttY + ttH + 1, c(20, 15, 40, 240));
/*  957 */     ctx.method_25294(ttX, ttY, ttX + ttW, ttY + ttH, c(12, 10, 25, 250));
/*  958 */     ctx.method_25294(ttX, ttY, ttX + ttW, ttY + 1, ACCENT_CYAN);
/*  959 */     ctx.method_51433(this.field_22793, text, ttX + pad, ttY + pad - 1, TEXT_PRIMARY, true);
/*  960 */     mat.method_22909();
/*      */   }
/*      */   private void drawDiamond(class_332 ctx, int cx, int cy, int size, int col) {
/*      */     int i;
/*  964 */     for (i = 0; i <= size; i++) {
/*  965 */       ctx.method_25294(cx - i, cy - size + i, cx + i + 1, cy - size + i + 1, col);
/*      */     }
/*  967 */     for (i = 0; i <= size; i++) {
/*  968 */       ctx.method_25294(cx - i, cy + size - i, cx + i + 1, cy + size - i + 1, col);
/*      */     }
/*      */   }
/*      */   
/*      */   private void drawDiamondGlow(class_332 ctx, int cx, int cy, int gs, int col, int ma) {
/*  973 */     for (int g = gs; g > 0; g--) {
/*  974 */       int a = ma * g / gs / 3;
/*  975 */       int gc = col & 0xFFFFFF | a << 24; int i;
/*  976 */       for (i = 0; i <= g; i++) {
/*  977 */         ctx.method_25294(cx - i, cy - g + i, cx + i + 1, cy - g + i + 1, gc);
/*      */       }
/*  979 */       for (i = 0; i <= g; i++) {
/*  980 */         ctx.method_25294(cx - i, cy + g - i, cx + i + 1, cy + g - i + 1, gc);
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   private static int rankColorFromHex(int hex) {
/*  986 */     int r = hex >> 16 & 0xFF;
/*  987 */     int g = hex >> 8 & 0xFF;
/*  988 */     int b = hex & 0xFF;
/*  989 */     return c(r, g, b, 255);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private String formatCountdown(long endEpochMillis) {
/*  995 */     long remaining = endEpochMillis - System.currentTimeMillis();
/*  996 */     if (remaining <= 0L) return "Ending soon..."; 
/*  997 */     long seconds = remaining / 1000L, minutes = seconds / 60L, hours = minutes / 60L, days = hours / 24L;
/*  998 */     if (days > 0L) return "" + days + "d " + days + "h " + hours % 24L + "m"; 
/*  999 */     if (hours > 0L) return "" + hours + "h " + hours + "m " + minutes % 60L + "s"; 
/* 1000 */     if (minutes > 0L) return "" + minutes + "m " + minutes + "s"; 
/* 1001 */     return "" + seconds + "s";
/*      */   }
/*      */   
/*      */   private int getCountdownColor(long endEpochMillis) {
/* 1005 */     long remaining = endEpochMillis - System.currentTimeMillis();
/* 1006 */     if (remaining <= 0L) return ACCENT_RED; 
/* 1007 */     if (remaining < 86400000L) return ACCENT_RED; 
/* 1008 */     if (remaining < 259200000L) return ACCENT_GOLD; 
/* 1009 */     return ACCENT_GREEN;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean method_25402(double mouseX, double mouseY, int button) {
/* 1016 */     if (button == 0) {
/*      */       
/* 1018 */       if (this.showRefreshConfirm) {
/* 1019 */         if (this.confirmYesHovered) {
/* 1020 */           playClickSound();
/* 1021 */           RankedNetwork.sendRefreshOpponents(this.refreshConfirmIsFree);
/* 1022 */           this.showRefreshConfirm = false;
/*      */         } 
/* 1024 */         if (this.confirmNoHovered) {
/* 1025 */           playClickSound();
/* 1026 */           this.showRefreshConfirm = false;
/*      */         } 
/* 1028 */         return true;
/*      */       } 
/*      */ 
/*      */       
/* 1032 */       if (this.showPopup) {
/* 1033 */         if (this.popupOkHovered) { playClickSound(); this.showPopup = false; }
/* 1034 */          return true;
/*      */       } 
/*      */       
/* 1037 */       if (this.defenseTabHovered) {
/* 1038 */         playClickSound();
/* 1039 */         class_310.method_1551().method_1507(new RankedDefenseScreen());
/* 1040 */         return true;
/*      */       } 
/* 1042 */       if (this.leaderboardTabHovered) {
/* 1043 */         playClickSound();
/* 1044 */         RankedNetwork.requestLeaderboard("global");
/* 1045 */         class_310.method_1551().method_1507(new RankedLeaderboardScreen());
/* 1046 */         return true;
/*      */       } 
/* 1048 */       if (this.shopTabHovered) {
/* 1049 */         playClickSound();
/* 1050 */         class_310.method_1551().method_1507(new RankedShopScreen());
/* 1051 */         return true;
/*      */       } 
/* 1053 */       if (this.historyTabHovered) {
/* 1054 */         playClickSound();
/* 1055 */         RankedNetwork.requestHistory();
/* 1056 */         class_310.method_1551().method_1507(new RankedHistoryScreen());
/* 1057 */         return true;
/*      */       } 
/*      */       
/* 1060 */       List<RankedNetwork.OpponentPayload> opponents = PlayerRankedData.getInstance().getOpponents();
/* 1061 */       for (int i = 0; i < 3; i++) {
/* 1062 */         if (this.challengeHovered[i] && i < opponents.size() && !((RankedNetwork.OpponentPayload)opponents.get(i)).defeated()) {
/* 1063 */           playClickSound();
/* 1064 */           RankedNetwork.sendChallenge(((RankedNetwork.OpponentPayload)opponents.get(i)).uuid());
/* 1065 */           return true;
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/* 1070 */       if (this.refreshGemsHovered) {
/* 1071 */         playClickSound();
/* 1072 */         this.showRefreshConfirm = true;
/* 1073 */         this.refreshConfirmIsFree = false;
/* 1074 */         return true;
/*      */       } 
/* 1076 */       if (this.refreshFreeHovered) {
/* 1077 */         playClickSound();
/* 1078 */         this.showRefreshConfirm = true;
/* 1079 */         this.refreshConfirmIsFree = true;
/* 1080 */         return true;
/*      */       } 
/* 1082 */       if (this.closeHovered) { playClickSound(); method_25419(); return true; }
/*      */     
/* 1084 */     }  return super.method_25402(mouseX, mouseY, button);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean method_25404(int keyCode, int scanCode, int modifiers) {
/* 1089 */     if (keyCode == 256) {
/* 1090 */       if (this.showRefreshConfirm) { this.showRefreshConfirm = false; return true; }
/* 1091 */        if (this.showPopup) { this.showPopup = false; return true; }
/* 1092 */        method_25419();
/* 1093 */       return true;
/*      */     } 
/* 1095 */     return super.method_25404(keyCode, scanCode, modifiers);
/*      */   }
/*      */   
/*      */   private void playClickSound() {
/* 1099 */     class_310.method_1551().method_1483().method_4873((class_1113)class_1109.method_47978((class_6880)class_3417.field_15015, 1.0F));
/*      */   }
/*      */   public void method_25420(class_332 context, int mouseX, int mouseY, float delta) {}
/*      */   public boolean method_25421() {
/* 1103 */     return false;
/*      */   }
/*      */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\ranked\screen\RankedScreen.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */