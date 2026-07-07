/*      */ package com.atlas.common.fabric.battletower.screen;
/*      */ import com.atlas.common.fabric.AtlasMod;
/*      */ import com.atlas.common.fabric.battletower.TranslationHelper;
/*      */ import com.atlas.common.fabric.battletower.config.BPRewardsConfig;
/*      */ import com.atlas.common.fabric.battletower.data.ShopItem;
/*      */ import com.atlas.common.fabric.battletower.network.BattleTowerNetwork;
/*      */ import com.atlas.common.fabric.ranked.screen.RankedIcon;
/*      */ import com.atlas.common.fabric.screen.PokemonRenderHelper;
/*      */ import com.cobblemon.mod.common.api.abilities.AbilityPool;
/*      */ import com.cobblemon.mod.common.api.abilities.PotentialAbility;
/*      */ import com.cobblemon.mod.common.api.moves.Move;
/*      */ import com.cobblemon.mod.common.api.pokeball.PokeBalls;
/*      */ import com.cobblemon.mod.common.api.pokemon.PokemonSpecies;
/*      */ import com.cobblemon.mod.common.api.pokemon.stats.Stat;
/*      */ import com.cobblemon.mod.common.api.pokemon.stats.Stats;
/*      */ import com.cobblemon.mod.common.api.types.ElementalType;
/*      */ import com.cobblemon.mod.common.client.render.models.blockbench.FloatingState;
/*      */ import com.cobblemon.mod.common.pokeball.PokeBall;
/*      */ import com.cobblemon.mod.common.pokemon.Gender;
/*      */ import com.cobblemon.mod.common.pokemon.Pokemon;
/*      */ import com.cobblemon.mod.common.pokemon.Species;
/*      */ import com.cobblemon.mod.common.pokemon.abilities.HiddenAbility;
/*      */ import java.lang.reflect.Method;
/*      */ import java.lang.reflect.Modifier;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Objects;
/*      */ import java.util.Optional;
/*      */ import java.util.Set;
/*      */ import net.minecraft.class_1109;
/*      */ import net.minecraft.class_1113;
/*      */ import net.minecraft.class_124;
/*      */ import net.minecraft.class_1792;
/*      */ import net.minecraft.class_1799;
/*      */ import net.minecraft.class_1935;
/*      */ import net.minecraft.class_2487;
/*      */ import net.minecraft.class_2522;
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
/*      */ import net.minecraft.class_6880;
/*      */ import net.minecraft.class_7923;
/*      */ import org.joml.Quaternionf;
/*      */ 
/*      */ public class BattleTowerScreen extends class_437 {
/*   57 */   private static final int PANEL_BG = color(14, 11, 8, 255);
/*   58 */   private static final int HEADER_BG = color(25, 16, 10, 255);
/*   59 */   private static final int SECTION_BG = color(18, 14, 10, 255);
/*   60 */   private static final int BORDER_COLOR = color(100, 50, 40, 255);
/*   61 */   private static final int BORDER_HIGHLIGHT = color(220, 100, 40, 255);
/*   62 */   private static final int ACCENT_BLUE = color(80, 140, 255, 255);
/*   63 */   private static final int ACCENT_RED = color(220, 50, 50, 255);
/*   64 */   private static final int ACCENT_CRIMSON = color(180, 35, 50, 255);
/*   65 */   private static final int ACCENT_ORANGE = color(255, 140, 40, 255);
/*   66 */   private static final int ACCENT_WARM = color(240, 100, 50, 255);
/*   67 */   private static final int ACCENT_CYAN = color(100, 160, 240, 255);
/*   68 */   private static final int ACCENT_GOLD = color(255, 200, 80, 255);
/*   69 */   private static final int ACCENT_GREEN = color(80, 255, 150, 255);
/*   70 */   private static final int TEXT_PRIMARY = color(240, 230, 230, 255);
/*   71 */   private static final int TEXT_SECONDARY = color(200, 180, 160, 255);
/*   72 */   private static final int TEXT_DIM = color(150, 120, 90, 255);
/*   73 */   private static final int SLOT_FILLED = color(45, 30, 20, 255);
/*   74 */   private static final int SLOT_EMPTY = color(22, 16, 12, 255);
/*      */ 
/*      */   
/*   77 */   private static final int STAT_HP = color(255, 80, 80, 255);
/*   78 */   private static final int STAT_ATK = color(255, 160, 60, 255);
/*   79 */   private static final int STAT_DEF = color(255, 230, 80, 255);
/*   80 */   private static final int STAT_SPATK = color(100, 200, 255, 255);
/*   81 */   private static final int STAT_SPDEF = color(100, 255, 100, 255);
/*   82 */   private static final int STAT_SPD = color(200, 100, 255, 255);
/*      */   
/*      */   private static final int GUI_WIDTH = 520;
/*      */   
/*      */   private static final int GUI_HEIGHT = 360;
/*      */   
/*      */   private static final int HEADER_HEIGHT = 46;
/*      */   
/*      */   private static final int SECTION_TOP = 52;
/*      */   
/*      */   private static final int PANEL_PADDING = 4;
/*      */   private static final int SIDE_PANEL_WIDTH = 160;
/*      */   private int guiLeft;
/*      */   private int guiTop;
/*   96 */   private int currentFloor = 1;
/*   97 */   private int highestFloor = 1;
/*   98 */   private int currentStreak = 0;
/*   99 */   private int bpBalance = 0;
/*      */   
/*      */   private boolean runActive = false;
/*      */   
/*      */   private boolean partyLockedIn = false;
/*  104 */   private int currentSeason = 1;
/*  105 */   private long seasonEndEpochMillis = 0L;
/*      */   
/*  107 */   private int shopScrollOffset = 0;
/*  108 */   private int hoveredShopIndex = -1;
/*  109 */   private List<ShopItem> shopItems = new ArrayList<>();
/*  110 */   private Map<String, Integer> shoppingCart = new HashMap<>();
/*      */   
/*      */   private boolean scrollbarDragging = false;
/*  113 */   private int scrollbarDragStartY = 0;
/*  114 */   private int scrollbarDragStartOffset = 0;
/*  115 */   private int scrollbarThumbY = 0;
/*  116 */   private int scrollbarThumbHeight = 0;
/*  117 */   private int scrollbarTrackY = 0;
/*  118 */   private int scrollbarTrackHeight = 0;
/*  119 */   private int scrollbarX = 0;
/*  120 */   private int scrollbarWidth = 0;
/*      */   private boolean startBattleHovered;
/*      */   private boolean lockInHovered;
/*      */   private boolean unlockHovered;
/*      */   private boolean forfeitHovered;
/*      */   private boolean closeHovered;
/*  126 */   private Map<Integer, FloatingState> pokemonStates = new HashMap<>(); private boolean shopUpHovered; private boolean shopDownHovered;
/*      */   private boolean purchaseCartHovered;
/*  128 */   private List<BattleTowerNetwork.PartyEntry> partyData = new ArrayList<>(); private boolean clearCartHovered; private boolean towerTabHovered; private boolean leaderboardTabHovered;
/*  129 */   private final Map<Integer, TooltipCacheEntry> partyTooltipCache = new HashMap<>();
/*  130 */   private final Map<Integer, TooltipCacheEntry> towerTooltipCache = new HashMap<>();
/*  131 */   private final Map<Integer, Pokemon> pokemonCache = new HashMap<>();
/*  132 */   private List<BattleTowerNetwork.ActivePlayerData> activePlayers = new ArrayList<>();
/*  133 */   private int hoveredFloor = -1;
/*      */   
/*      */   private boolean showPopup = false;
/*      */   
/*  137 */   private String popupTitle = "";
/*  138 */   private String popupLine1 = "";
/*  139 */   private String popupLine2 = "";
/*      */   
/*      */   private boolean popupOkHovered = false;
/*      */ 
/*      */   
/*      */   private static int color(int r, int g, int b, int a) {
/*  145 */     return a << 24 | r << 16 | g << 8 | b;
/*      */   }
/*      */   
/*      */   public BattleTowerScreen() {
/*  149 */     super((class_2561)class_2561.method_43471("screen.cobblemon_battle_tower.title"));
/*  150 */     this.shopItems.clear();
/*  151 */     for (ShopItem item : ShopItem.getAllItems()) this.shopItems.add(item); 
/*  152 */     for (int i = 0; i < 6; ) { this.pokemonStates.put(Integer.valueOf(i), new FloatingState()); i++; }
/*  153 */      PokemonRenderHelper.init();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void method_25426() {
/*  160 */     super.method_25426();
/*  161 */     this.guiLeft = (this.field_22789 - 520) / 2;
/*  162 */     this.guiTop = (this.field_22790 - 360) / 2;
/*  163 */     BattleTowerNetwork.requestDataSync();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void updatePlayerData(int floor, int highest, int streak, int bp, int season, long seasonEndEpoch) {
/*  169 */     this.currentFloor = floor; this.highestFloor = highest; this.currentStreak = streak; this.bpBalance = bp;
/*  170 */     this.currentSeason = season; this.seasonEndEpochMillis = seasonEndEpoch;
/*      */   }
/*      */   
/*      */   public void updateRunState(boolean partyLockedIn, boolean runActive) {
/*  174 */     this.partyLockedIn = partyLockedIn; this.runActive = runActive;
/*      */   }
/*      */   
/*  177 */   public void updatePartyData(List<BattleTowerNetwork.PartyEntry> entries) { this.partyData = entries; this.partyTooltipCache.clear(); this.pokemonCache.clear(); } public void updateActivePlayers(List<BattleTowerNetwork.ActivePlayerData> players) {
/*  178 */     this.activePlayers = players; this.towerTooltipCache.clear();
/*      */   }
/*      */   public void refreshShopItems() {
/*  181 */     this.shopItems.clear();
/*  182 */     for (ShopItem item : ShopItem.getAllItems()) this.shopItems.add(item); 
/*      */   }
/*      */   
/*      */   public void handleLockInResult(boolean success, String reason) {
/*  186 */     if (!success) showLockInFailure(reason); 
/*      */   }
/*      */   
/*      */   private void showLockInFailure(String reason) {
/*  190 */     this.showPopup = true;
/*  191 */     switch (reason) {
/*      */       case "LEGENDARY_LIMIT":
/*  193 */         this.popupTitle = "1 Legendary Limit";
/*  194 */         this.popupLine1 = "You can only have 1 Legendary, Mythical,";
/*  195 */         this.popupLine2 = "Paradox, or Ultra Beast on your team.";
/*      */         return;
/*      */       case "DUPLICATE_ITEMS":
/*  198 */         this.popupTitle = "Duplicate Held Items";
/*  199 */         this.popupLine1 = "Each Pokemon must hold a different item.";
/*  200 */         this.popupLine2 = "Remove duplicate held items and try again.";
/*      */         return;
/*      */       case "DUPLICATE_SPECIES":
/*  203 */         this.popupTitle = "Duplicate Pokemon";
/*  204 */         this.popupLine1 = "Each Pokemon on your team must be unique.";
/*  205 */         this.popupLine2 = "Remove duplicate species and try again.";
/*      */         return;
/*      */       case "EMPTY_PARTY":
/*  208 */         this.popupTitle = "No Pokemon Selected";
/*  209 */         this.popupLine1 = "You need at least 1 Pokemon";
/*  210 */         this.popupLine2 = "to lock in a Battle Tower team.";
/*      */         return;
/*      */       case "MIN_REQUIRED":
/*  213 */         this.popupTitle = "Not Enough Pokemon";
/*  214 */         this.popupLine1 = "Your selected battle format";
/*  215 */         this.popupLine2 = "requires more Pokemon.";
/*      */         return;
/*      */       case "MAX_REQUIRED":
/*  218 */         this.popupTitle = "Too Many Pokemon";
/*  219 */         this.popupLine1 = "Your selected battle format";
/*  220 */         this.popupLine2 = "allows fewer Pokemon.";
/*      */         return;
/*      */     } 
/*  223 */     this.popupTitle = "Lock-in Failed";
/*  224 */     this.popupLine1 = (reason == null || reason.isBlank()) ? "Your team could not be locked in." : reason;
/*  225 */     this.popupLine2 = "Please adjust your team and try again.";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void method_25394(class_332 context, int mouseX, int mouseY, float delta) {
/*  234 */     context.method_25294(0, 0, this.field_22789, this.field_22790, color(0, 0, 0, 180));
/*  235 */     drawPanel(context, this.guiLeft, this.guiTop, 520, 360);
/*      */     
/*  237 */     this.startBattleHovered = this.lockInHovered = this.unlockHovered = this.forfeitHovered = this.closeHovered = false;
/*  238 */     this.shopUpHovered = this.shopDownHovered = this.purchaseCartHovered = this.clearCartHovered = false;
/*  239 */     this.towerTabHovered = this.leaderboardTabHovered = false;
/*  240 */     this.hoveredShopIndex = -1;
/*      */     
/*  242 */     drawHeader(context, mouseX, mouseY);
/*      */     
/*  244 */     int contentY = this.guiTop + 52;
/*  245 */     int contentH = 272;
/*  246 */     int partyX = this.guiLeft + 4;
/*  247 */     int shopX = this.guiLeft + 520 - 4 - 160;
/*  248 */     int towerX = partyX + 160 + 4;
/*  249 */     int towerW = shopX - towerX - 4;
/*      */     
/*  251 */     drawPartySectionPanel(context, partyX, contentY, 160, contentH, mouseX, mouseY, delta);
/*  252 */     drawTowerSectionPanel(context, towerX, contentY, towerW, contentH, mouseX, mouseY);
/*  253 */     drawShopSectionPanel(context, shopX, contentY, 160, contentH, mouseX, mouseY);
/*  254 */     drawBottomButtons(context, mouseX, mouseY);
/*      */     
/*  256 */     if (!this.partyLockedIn && isMouseOverStartBattleButton(mouseX, mouseY)) {
/*  257 */       context.method_51438(this.field_22793, (class_2561)TranslationHelper.translate("gui.tooltip.lock_team", new Object[0]), mouseX, mouseY);
/*      */     }
/*  259 */     super.method_25394(context, mouseX, mouseY, delta);
/*      */ 
/*      */     
/*  262 */     if (this.showPopup) {
/*  263 */       drawPopupOverlay(context, mouseX, mouseY);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void drawPopupOverlay(class_332 context, int mouseX, int mouseY) {
/*  269 */     class_4587 matrices = context.method_51448();
/*  270 */     matrices.method_22903();
/*  271 */     matrices.method_46416(0.0F, 0.0F, 400.0F);
/*      */ 
/*      */     
/*  274 */     context.method_25294(0, 0, this.field_22789, this.field_22790, color(0, 0, 0, 150));
/*      */ 
/*      */     
/*  277 */     int popupW = 280;
/*  278 */     int popupH = 100;
/*  279 */     int popupX = (this.field_22789 - popupW) / 2;
/*  280 */     int popupY = (this.field_22790 - popupH) / 2;
/*      */ 
/*      */     
/*  283 */     context.method_25294(popupX, popupY, popupX + popupW, popupY + popupH, color(30, 20, 15, 255));
/*      */     
/*  285 */     context.method_25294(popupX, popupY, popupX + popupW, popupY + 2, ACCENT_RED);
/*  286 */     context.method_25294(popupX, popupY + popupH - 2, popupX + popupW, popupY + popupH, ACCENT_RED);
/*  287 */     context.method_25294(popupX, popupY, popupX + 2, popupY + popupH, ACCENT_RED);
/*  288 */     context.method_25294(popupX + popupW - 2, popupY, popupX + popupW, popupY + popupH, ACCENT_RED);
/*      */ 
/*      */     
/*  291 */     context.method_27534(this.field_22793, (class_2561)class_2561.method_43470(this.popupTitle).method_27695(new class_124[] { class_124.field_1061, class_124.field_1067 }, ), popupX + popupW / 2, popupY + 15, ACCENT_RED);
/*      */ 
/*      */     
/*  294 */     context.method_27534(this.field_22793, (class_2561)class_2561.method_43470(this.popupLine1).method_27692(class_124.field_1080), popupX + popupW / 2, popupY + 35, TEXT_SECONDARY);
/*  295 */     context.method_27534(this.field_22793, (class_2561)class_2561.method_43470(this.popupLine2).method_27692(class_124.field_1080), popupX + popupW / 2, popupY + 47, TEXT_SECONDARY);
/*      */ 
/*      */     
/*  298 */     int btnW = 60;
/*  299 */     int btnH = 20;
/*  300 */     int btnX = popupX + (popupW - btnW) / 2;
/*  301 */     int btnY = popupY + popupH - 30;
/*  302 */     this.popupOkHovered = (mouseX >= btnX && mouseX < btnX + btnW && mouseY >= btnY && mouseY < btnY + btnH);
/*      */     
/*  304 */     context.method_25294(btnX, btnY, btnX + btnW, btnY + btnH, this.popupOkHovered ? ACCENT_WARM : ACCENT_RED);
/*  305 */     context.method_25294(btnX, btnY, btnX + btnW, btnY + 1, ACCENT_ORANGE);
/*  306 */     context.method_27534(this.field_22793, (class_2561)class_2561.method_43470("OK"), btnX + btnW / 2, btnY + 6, TEXT_PRIMARY);
/*      */     
/*  308 */     matrices.method_22909();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void drawPanel(class_332 context, int x, int y, int w, int h) {
/*  314 */     context.method_25294(x, y, x + w, y + h, PANEL_BG);
/*      */     
/*  316 */     context.method_25294(x, y, x + w, y + 2, ACCENT_ORANGE);
/*  317 */     context.method_25294(x, y + h - 2, x + w, y + h, ACCENT_RED);
/*  318 */     context.method_25294(x, y, x + 2, y + h, BORDER_HIGHLIGHT);
/*  319 */     context.method_25294(x + w - 2, y, x + w, y + h, ACCENT_CRIMSON);
/*      */     
/*  321 */     context.method_25294(x + 2, y + 2, x + w - 2, y + 3, color(0, 0, 0, 100));
/*  322 */     context.method_25294(x + 2, y + 2, x + 3, y + h - 2, color(0, 0, 0, 100));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void drawHeader(class_332 context, int mouseX, int mouseY) {
/*  328 */     int headerY = this.guiTop + 2;
/*  329 */     for (int row = 0; row < 46; row++) {
/*  330 */       float p = row / 46.0F;
/*  331 */       context.method_25294(this.guiLeft + 2, headerY + row, this.guiLeft + 520 - 2, headerY + row + 1, color(25, 14, 10, 200 + (int)(p * 55.0F)));
/*      */     } 
/*      */     
/*  334 */     for (int i = 0; i < 512; i++) {
/*  335 */       float p = i / 512.0F;
/*  336 */       int r = (int)(255.0F - p * 35.0F);
/*  337 */       int g = (int)(160.0F - p * 120.0F);
/*  338 */       int b = (int)(40.0F - p * 10.0F);
/*  339 */       context.method_25294(this.guiLeft + 2 + i, headerY, this.guiLeft + 3 + i, headerY + 2, 
/*  340 */           color(r, g, b, 120 + (int)(60.0D * Math.sin(i * 0.05D))));
/*      */     } 
/*      */     
/*  343 */     int tabY = headerY + 6, tabH = 16, tabSpacing = 4;
/*  344 */     String towerText = TranslationHelper.translate("gui.tab.tower", new Object[0]).getString();
/*  345 */     int towerTabW = this.field_22793.method_1727(towerText) + 14, towerTabX = this.guiLeft + 10;
/*  346 */     this.towerTabHovered = (mouseX >= towerTabX && mouseX < towerTabX + towerTabW && mouseY >= tabY && mouseY < tabY + tabH);
/*  347 */     drawTabButton(context, towerTabX, tabY, towerTabW, tabH, towerText, true, this.towerTabHovered, true);
/*      */     
/*  349 */     String lbText = TranslationHelper.translate("gui.tab.leaderboard", new Object[0]).getString();
/*  350 */     int lbTabW = this.field_22793.method_1727(lbText) + 14, lbTabX = towerTabX + towerTabW + tabSpacing;
/*  351 */     this.leaderboardTabHovered = (mouseX >= lbTabX && mouseX < lbTabX + lbTabW && mouseY >= tabY && mouseY < tabY + tabH);
/*  352 */     drawTabButton(context, lbTabX, tabY, lbTabW, tabH, lbText, false, this.leaderboardTabHovered, false);
/*      */ 
/*      */     
/*  355 */     String bpText = " " + this.bpBalance + " Battle Points";
/*  356 */     int bpTextW = this.field_22793.method_1727(bpText);
/*  357 */     int bpIconSize = 9;
/*  358 */     int totalBpW = bpIconSize + bpTextW;
/*  359 */     int bpX = this.guiLeft + 520 - totalBpW - 10;
/*  360 */     int bpY = headerY + 10;
/*  361 */     RankedIcon.BATTLE_POINTS.drawTexture(context, bpX, bpY - 1, bpIconSize, bpIconSize);
/*  362 */     context.method_51433(this.field_22793, bpText, bpX + bpIconSize, bpY, ACCENT_GOLD, true);
/*      */ 
/*      */     
/*  365 */     int topDivY = headerY + 26;
/*  366 */     for (int j = 0; j < 508; j++) {
/*  367 */       float p = j / 508.0F;
/*  368 */       int r = (int)(255.0F - p * 35.0F), g = (int)(160.0F - p * 120.0F), b = (int)(40.0F - p * 10.0F);
/*  369 */       context.method_25294(this.guiLeft + 6 + j, topDivY, this.guiLeft + 7 + j, topDivY + 1, 
/*  370 */           color(r, g, b, 150 + (int)(50.0D * Math.sin(j * 0.1D))));
/*      */     } 
/*  372 */     int centerX = this.guiLeft + 260;
/*  373 */     drawDiamondGlow(context, centerX, topDivY, 3, ACCENT_WARM, 70);
/*  374 */     drawDiamond(context, centerX, topDivY, 2, ACCENT_WARM);
/*      */     
/*  376 */     String seasonLabel = (this.currentSeason <= 0) ? "Season BETA" : ("Season " + this.currentSeason);
/*  377 */     String countdown = formatCountdown(this.seasonEndEpochMillis);
/*  378 */     int countdownColor = getCountdownColor();
/*  379 */     int seasonTextY = topDivY + 5;
/*  380 */     int guiCenterX = this.guiLeft + 260;
/*      */     
/*  382 */     String pipe = "|";
/*  383 */     int pipeW = this.field_22793.method_1727(pipe);
/*  384 */     int pipeX = guiCenterX - pipeW / 2 + 1;
/*  385 */     context.method_51433(this.field_22793, pipe, pipeX, seasonTextY, TEXT_PRIMARY, true);
/*      */     
/*  387 */     int boldSeasonW = this.field_22793.method_27525((class_5348)class_2561.method_43470(seasonLabel).method_27694(s -> s.method_10982(Boolean.valueOf(true))));
/*  388 */     int leftPad = 6;
/*  389 */     context.method_51433(this.field_22793, "§l" + seasonLabel, pipeX - leftPad - boldSeasonW, seasonTextY, ACCENT_CRIMSON, true);
/*      */     
/*  391 */     String endsLabel = "Ends in: ";
/*  392 */     int rightPad = 6;
/*  393 */     int endsX = pipeX + pipeW + rightPad;
/*  394 */     context.method_51433(this.field_22793, endsLabel, endsX, seasonTextY, TEXT_DIM, true);
/*  395 */     context.method_51433(this.field_22793, countdown, endsX + this.field_22793.method_1727(endsLabel), seasonTextY, countdownColor, true);
/*      */ 
/*      */     
/*  398 */     int botDivY = seasonTextY + 13;
/*  399 */     for (int k = 0; k < 508; k++) {
/*  400 */       float p = k / 508.0F;
/*  401 */       int r = (int)(255.0F - p * 35.0F), g = (int)(160.0F - p * 120.0F), b = (int)(40.0F - p * 10.0F);
/*  402 */       context.method_25294(this.guiLeft + 6 + k, botDivY, this.guiLeft + 7 + k, botDivY + 1, 
/*  403 */           color(r, g, b, 150 + (int)(50.0D * Math.sin(k * 0.1D))));
/*      */     } 
/*  405 */     int partyCenter = this.guiLeft + 4 + 80;
/*  406 */     int shopCenter = this.guiLeft + 520 - 4 - 80;
/*  407 */     int towerCenter = this.guiLeft + 260;
/*  408 */     drawDiamondGlow(context, partyCenter, botDivY, 3, ACCENT_ORANGE, 70);
/*  409 */     drawDiamond(context, partyCenter, botDivY, 2, ACCENT_ORANGE);
/*  410 */     drawDiamondGlow(context, towerCenter, botDivY, 4, ACCENT_WARM, 80);
/*  411 */     drawDiamond(context, towerCenter, botDivY, 3, ACCENT_WARM);
/*  412 */     drawDiamondGlow(context, shopCenter, botDivY, 3, ACCENT_RED, 70);
/*  413 */     drawDiamond(context, shopCenter, botDivY, 2, ACCENT_RED);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void drawSectionBox(class_332 context, int x, int y, int w, int h, String title, int accentColor) {
/*  419 */     context.method_25294(x, y, x + w, y + h, SECTION_BG);
/*  420 */     for (int row = 0; row < h; ) { context.method_25294(x + 1, y + row, x + w - 1, y + row + 1, color(50, 20, 10, (int)(10.0F + row / h * 15.0F))); row++; }
/*  421 */      context.method_25294(x, y, x + w, y + 2, accentColor);
/*  422 */     context.method_25294(x, y + h - 2, x + w, y + h, BattleTowerStyledButton.darken(accentColor, 40));
/*  423 */     context.method_25294(x, y, x + 2, y + h, accentColor);
/*  424 */     context.method_25294(x + w - 2, y, x + w, y + h, BattleTowerStyledButton.darken(accentColor, 40));
/*  425 */     context.method_25294(x + 2, y + 2, x + w - 2, y + 3, BattleTowerStyledButton.lighten(SECTION_BG, 30));
/*  426 */     if (title != null) {
/*  427 */       int titleW = this.field_22793.method_1727(title);
/*  428 */       context.method_25294(x + 4, y + 3, x + w - 4, y + 16, color(18, 12, 8, 220));
/*  429 */       context.method_51433(this.field_22793, "§l" + title, x + (w - titleW) / 2, y + 5, accentColor, true);
/*  430 */       context.method_25294(x + 4, y + 17, x + w - 4, y + 18, BattleTowerStyledButton.darken(accentColor, 60));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void drawPartySectionPanel(class_332 context, int x, int y, int w, int h, int mouseX, int mouseY, float delta) {
/*  437 */     String title = TranslationHelper.translate("gui.section.party", new Object[0]).getString();
/*  438 */     if (title.isEmpty()) title = "YOUR PARTY"; 
/*  439 */     drawSectionBox(context, x, y, w, h, title, ACCENT_ORANGE);
/*      */     
/*  441 */     int slotsStartY = y + 22;
/*  442 */     int slotPadX = 6;
/*  443 */     int slotPadBottom = 4;
/*  444 */     int availableH = y + h - slotsStartY - slotPadBottom;
/*  445 */     int slotSpacing = 3;
/*  446 */     int slotH = (availableH - slotSpacing * 5) / 6;
/*  447 */     int modelSize = Math.min(slotH - 6, 32);
/*  448 */     long currentTime = System.currentTimeMillis();
/*  449 */     float breathePhase = (float)Math.sin(currentTime / 400.0D) * 0.5F + 0.5F;
/*      */     
/*  451 */     for (int i = 0; i < 6; i++) {
/*  452 */       int slotY = slotsStartY + i * (slotH + slotSpacing);
/*  453 */       if (slotY + slotH > y + h)
/*      */         break; 
/*  455 */       int slotX = x + slotPadX;
/*  456 */       int slotW = w - slotPadX * 2;
/*  457 */       BattleTowerNetwork.PartyEntry pokemon = getPartySlot(i);
/*  458 */       boolean hasPokemon = (pokemon != null);
/*  459 */       boolean hovered = (mouseX >= slotX && mouseX < slotX + slotW && mouseY >= slotY && mouseY < slotY + slotH);
/*      */       
/*  461 */       int slotBg = hasPokemon ? SLOT_FILLED : SLOT_EMPTY;
/*  462 */       context.method_25294(slotX + 1, slotY + 1, slotX + slotW - 1, slotY + slotH - 1, slotBg);
/*  463 */       if (hasPokemon) {
/*  464 */         context.method_25294(slotX + 1, slotY + slotH - 4, slotX + slotW - 1, slotY + slotH - 1, BattleTowerStyledButton.darken(slotBg, 15));
/*  465 */         context.method_25294(slotX + 2, slotY + 1, slotX + slotW - 2, slotY + 2, BattleTowerStyledButton.lighten(slotBg, 15));
/*      */       } 
/*      */       
/*  468 */       int borderCol = (this.partyLockedIn && hasPokemon) ? ACCENT_GREEN : ((hovered && hasPokemon) ? ACCENT_ORANGE : BORDER_COLOR);
/*      */ 
/*      */       
/*  471 */       if (this.partyLockedIn && hasPokemon) {
/*  472 */         int ga = (int)(40.0F + breathePhase * 60.0F);
/*  473 */         int glowCol = ACCENT_GREEN & 0xFFFFFF | ga << 24;
/*  474 */         int gs = 2;
/*  475 */         context.method_25294(slotX - gs, slotY - gs, slotX + slotW + gs, slotY - gs + 1, glowCol);
/*  476 */         context.method_25294(slotX - gs, slotY + slotH + gs - 1, slotX + slotW + gs, slotY + slotH + gs, glowCol);
/*  477 */         context.method_25294(slotX - gs, slotY - gs, slotX - gs + 1, slotY + slotH + gs, glowCol);
/*  478 */         context.method_25294(slotX + slotW + gs - 1, slotY - gs, slotX + slotW + gs, slotY + slotH + gs, glowCol);
/*      */       } 
/*      */ 
/*      */       
/*  482 */       if (hovered && hasPokemon) {
/*  483 */         for (int g = 3; g > 0; g--) {
/*  484 */           int alpha = 60 * g / 3;
/*  485 */           int gc = ACCENT_ORANGE & 0xFFFFFF | alpha << 24;
/*  486 */           context.method_25294(slotX - g, slotY - g, slotX + slotW + g, slotY - g + 1, gc);
/*  487 */           context.method_25294(slotX - g, slotY + slotH + g - 1, slotX + slotW + g, slotY + slotH + g, gc);
/*  488 */           context.method_25294(slotX - g, slotY - g, slotX - g + 1, slotY + slotH + g, gc);
/*  489 */           context.method_25294(slotX + slotW + g - 1, slotY - g, slotX + slotW + g, slotY + slotH + g, gc);
/*      */         } 
/*      */       }
/*      */ 
/*      */       
/*  494 */       context.method_25294(slotX + 1, slotY, slotX + slotW - 1, slotY + 1, borderCol);
/*  495 */       context.method_25294(slotX + 1, slotY + slotH - 1, slotX + slotW - 1, slotY + slotH, BattleTowerStyledButton.darken(borderCol, 30));
/*  496 */       context.method_25294(slotX, slotY + 1, slotX + 1, slotY + slotH - 1, borderCol);
/*  497 */       context.method_25294(slotX + slotW - 1, slotY + 1, slotX + slotW, slotY + slotH - 1, BattleTowerStyledButton.darken(borderCol, 30));
/*      */       
/*  499 */       if (!hasPokemon) {
/*  500 */         context.method_25300(this.field_22793, "─", slotX + slotW / 2, slotY + slotH / 2 - 4, color(50, 35, 25, 150));
/*      */       } else {
/*      */         
/*  503 */         int modelX = slotX + 2;
/*  504 */         int modelY = slotY + 2;
/*  505 */         context.method_25294(modelX, modelY, modelX + modelSize, modelY + modelSize - 2, color(25, 18, 12, 255));
/*  506 */         context.method_25294(modelX, modelY, modelX + modelSize, modelY + 1, color(60, 40, 25, 255));
/*      */         
/*  508 */         renderPokemonInSlot(context, pokemon, modelX, modelY, modelSize, i, delta);
/*      */ 
/*      */         
/*  511 */         String ballName = pokemon.caughtBall();
/*  512 */         if (ballName == null || ballName.isEmpty()) ballName = "cobblemon:poke_ball";
/*      */         
/*  514 */         String ballNamespace = "cobblemon";
/*  515 */         String ballPath = ballName;
/*  516 */         if (ballName.contains(":")) {
/*  517 */           ballNamespace = ballName.substring(0, ballName.indexOf(":"));
/*  518 */           ballPath = ballName.substring(ballName.indexOf(":") + 1);
/*      */         } 
/*  520 */         class_2960 pokeballTex = class_2960.method_60655(ballNamespace, "textures/gui/ball/" + ballPath + ".png");
/*  521 */         class_4587 mat = context.method_51448();
/*  522 */         mat.method_22903(); mat.method_46416((modelX - 1), modelY - 1.5F, 100.0F); mat.method_22905(0.5F, 0.5F, 1.0F);
/*  523 */         context.method_25290(pokeballTex, 0, 0, 0.0F, 0.0F, 18, 22, 18, 44);
/*  524 */         mat.method_22909();
/*      */ 
/*      */         
/*  527 */         int infoX = modelX + modelSize + 4;
/*  528 */         int infoY = slotY + 4;
/*      */         
/*  530 */         class_2561 speciesName = getTranslatedSpeciesName(pokemon);
/*  531 */         String nameStr = speciesName.getString();
/*  532 */         if (this.field_22793.method_1727(nameStr) > slotW - modelSize - 14) {
/*  533 */           while (this.field_22793.method_1727(nameStr + "..") > slotW - modelSize - 14 && nameStr.length() > 1) {
/*  534 */             nameStr = nameStr.substring(0, nameStr.length() - 1);
/*      */           }
/*  536 */           nameStr = nameStr + "..";
/*      */         } 
/*  538 */         context.method_51433(this.field_22793, nameStr, infoX, infoY, TEXT_PRIMARY, true);
/*      */         
/*  540 */         String lvlText = "Lv." + pokemon.level();
/*  541 */         context.method_51433(this.field_22793, lvlText, infoX, infoY + 11, ACCENT_ORANGE, true);
/*      */ 
/*      */         
/*  544 */         if (pokemon.heldItem() != null && !pokemon.heldItem().equals("none")) {
/*      */           
/*  546 */           try { class_1792 item = (class_1792)class_7923.field_41178.method_10223(class_2960.method_60654(pokemon.heldItem()));
/*  547 */             if (item != null && item != class_1802.field_8162) {
/*  548 */               mat = context.method_51448(); mat.method_22903();
/*  549 */               mat.method_46416((modelX + modelSize - 10), (modelY + modelSize - 12), 200.0F);
/*  550 */               mat.method_22905(0.5F, 0.5F, 1.0F);
/*  551 */               context.method_51427(new class_1799((class_1935)item), 0, 0); mat.method_22909();
/*      */             }  }
/*  553 */           catch (Exception e) { AtlasMod.LOGGER.debug("Failed to render held item: {}", e.getMessage()); }
/*      */         
/*      */         }
/*      */         
/*  557 */         if (hasPokemon) {
/*  558 */           int barX = slotX + 2;
/*  559 */           int barY = slotY + slotH - 3;
/*  560 */           int barW = slotW - 4;
/*  561 */           context.method_25294(barX, barY, barX + barW, barY + 2, color(30, 20, 12, 255));
/*  562 */           int barColor = this.partyLockedIn ? ACCENT_GREEN : ACCENT_ORANGE;
/*  563 */           context.method_25294(barX, barY, barX + barW, barY + 1, barColor);
/*  564 */           context.method_25294(barX, barY + 1, barX + barW, barY + 2, BattleTowerStyledButton.darken(barColor, 30));
/*      */         } 
/*      */       } 
/*      */       
/*  568 */       if (hovered && hasPokemon) drawPartyTooltip(context, pokemon, mouseX, mouseY);
/*      */     
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void drawTowerSectionPanel(class_332 context, int x, int y, int w, int h, int mouseX, int mouseY) {
/*  575 */     String title = TranslationHelper.translate("gui.section.progress", new Object[0]).getString();
/*  576 */     if (title.isEmpty()) title = "BATTLE PROGRESS"; 
/*  577 */     drawSectionBox(context, x, y, w, h, title, ACCENT_WARM);
/*      */     
/*  579 */     int innerX = x + 4, innerY = y + 20, innerW = w - 8, innerH = h - 24;
/*      */ 
/*      */     
/*  582 */     int statsY = innerY + 2;
/*  583 */     int cardSpacing = 3;
/*  584 */     int statsX = innerX + 2;
/*  585 */     int numCards = 4;
/*  586 */     int totalSpacing = cardSpacing * (numCards - 1);
/*  587 */     int cardW = (innerW - 4 - totalSpacing) / numCards;
/*      */ 
/*      */     
/*  590 */     drawStatCardImproved(context, statsX, statsY, cardW, "Floor", String.valueOf(this.currentFloor), color(255, 130, 40, 255));
/*      */     
/*  592 */     drawStatCardImproved(context, statsX + cardW + cardSpacing, statsY, cardW, "Streak", String.valueOf(this.currentStreak), color(80, 220, 255, 255));
/*      */     
/*  594 */     drawStatCardImproved(context, statsX + (cardW + cardSpacing) * 2, statsY, cardW, "Best", String.valueOf(this.highestFloor), color(80, 255, 120, 255));
/*      */     
/*  596 */     BPRewardsConfig bpConfig = BPRewardsConfig.get();
/*  597 */     int bpReward = (bpConfig != null) ? bpConfig.getBPForFloor(this.currentFloor, false) : 1;
/*  598 */     drawStatCardImproved(context, statsX + (cardW + cardSpacing) * 3, statsY, cardW, "BP", "+" + bpReward, color(255, 215, 60, 255));
/*      */ 
/*      */     
/*  601 */     int towerAreaY = statsY + 34;
/*  602 */     int towerBottomPad = 6;
/*  603 */     int towerAreaH = innerH - towerAreaY - innerY - towerBottomPad;
/*  604 */     drawTowerFloors(context, innerX, towerAreaY, innerW, towerAreaH, mouseX, mouseY);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private String formatCountdown(long endEpochMillis) {
/*  610 */     long now = System.currentTimeMillis();
/*  611 */     long remaining = endEpochMillis - now;
/*  612 */     if (remaining <= 0L) return "Ending soon..."; 
/*  613 */     long seconds = remaining / 1000L;
/*  614 */     long minutes = seconds / 60L;
/*  615 */     long hours = minutes / 60L;
/*  616 */     long days = hours / 24L;
/*  617 */     if (days > 0L) return "" + days + "d " + days + "h " + hours % 24L + "m"; 
/*  618 */     if (hours > 0L) return "" + hours + "h " + hours + "m " + minutes % 60L + "s"; 
/*  619 */     if (minutes > 0L) return "" + minutes + "m " + minutes + "s"; 
/*  620 */     return "" + seconds + "s";
/*      */   }
/*      */   
/*      */   private int getCountdownColor() {
/*  624 */     long remaining = this.seasonEndEpochMillis - System.currentTimeMillis();
/*  625 */     if (remaining <= 0L) return ACCENT_RED; 
/*  626 */     if (remaining < 86400000L) return ACCENT_RED; 
/*  627 */     if (remaining < 259200000L) return ACCENT_GOLD; 
/*  628 */     return ACCENT_GREEN;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void drawStatCardImproved(class_332 context, int x, int y, int cardW, String label, String value, int accent) {
/*  634 */     int cardH = 26;
/*      */     
/*  636 */     context.method_25294(x, y, x + cardW, y + cardH, color(25, 18, 12, 240));
/*  637 */     context.method_25294(x, y + cardH / 2, x + cardW, y + cardH, color(20, 14, 10, 240));
/*      */     
/*  639 */     context.method_25294(x, y, x + cardW, y + 2, accent);
/*      */     
/*  641 */     int ga = accent & 0xFFFFFF | 0x1E000000;
/*  642 */     context.method_25294(x, y + 2, x + cardW, y + 4, ga);
/*      */     
/*  644 */     context.method_25294(x, y, x + 1, y + cardH, color(70, 45, 25, 180));
/*  645 */     context.method_25294(x + cardW - 1, y, x + cardW, y + cardH, color(70, 45, 25, 120));
/*  646 */     context.method_25294(x, y + cardH - 1, x + cardW, y + cardH, color(70, 45, 25, 120));
/*      */ 
/*      */     
/*  649 */     int labelW = this.field_22793.method_1727(label);
/*  650 */     context.method_51433(this.field_22793, label, x + (cardW - labelW) / 2, y + 4, TEXT_SECONDARY, true);
/*      */ 
/*      */     
/*  653 */     int valueW = this.field_22793.method_1727(value);
/*  654 */     context.method_51433(this.field_22793, value, x + (cardW - valueW) / 2, y + 15, accent, true);
/*      */   }
/*      */   
/*      */   private void drawTowerFloors(class_332 context, int areaX, int areaY, int areaW, int areaH, int mouseX, int mouseY) {
/*  658 */     int cx = areaX + areaW / 2, topY = areaY + 2, botY = areaY + areaH - 2, th = botY - topY;
/*  659 */     int floorsToShow = 10;
/*      */ 
/*      */     
/*  662 */     int middleIndex = 5;
/*  663 */     int bottomFloor = this.currentFloor - floorsToShow - 1 - middleIndex;
/*  664 */     if (bottomFloor < 1) bottomFloor = 1;
/*      */     
/*  666 */     int topFloor = bottomFloor + floorsToShow - 1;
/*      */     
/*  668 */     this.hoveredFloor = -1;
/*      */     
/*  670 */     for (int i = 0; i < floorsToShow; i++) {
/*  671 */       float vp = i / (floorsToShow - 1);
/*  672 */       int fw = 30 + (int)(vp * (areaW - 40)), fh = 12, fx = cx - fw / 2, fy = topY + (int)(vp * (th - fh));
/*  673 */       int df = bottomFloor + floorsToShow - 1 - i;
/*  674 */       if (df >= 1 && df <= 100) {
/*      */         
/*  676 */         int[] c = getFloorColors(df);
/*  677 */         context.method_25294(fx + 2, fy + fh, fx + fw + 2, fy + fh + 2, color(0, 0, 0, 120));
/*  678 */         if (df == this.currentFloor) {
/*  679 */           float pulse = (float)Math.sin(System.currentTimeMillis() / 300.0D) * 0.3F + 0.7F;
/*  680 */           for (int g = 4; g > 0; g--) {
/*  681 */             int ga = (int)(50.0F * g / 4.0F * pulse), gc = c[2] & 0xFFFFFF | ga << 24;
/*  682 */             context.method_25294(fx - g, fy - g, fx + fw + g, fy - g + 1, gc);
/*  683 */             context.method_25294(fx - g, fy + fh + g - 1, fx + fw + g, fy + fh + g, gc);
/*  684 */             context.method_25294(fx - g, fy - g, fx - g + 1, fy + fh + g, gc);
/*  685 */             context.method_25294(fx + fw + g - 1, fy - g, fx + fw + g, fy + fh + g, gc);
/*      */           } 
/*      */         } 
/*  688 */         context.method_25294(fx, fy, fx + fw, fy + fh, c[0]);
/*  689 */         context.method_25294(fx + 1, fy + 1, fx + fw - 1, fy + 2, BattleTowerStyledButton.lighten(c[0], 40));
/*  690 */         int bh = BattleTowerStyledButton.lighten(c[1], 20);
/*  691 */         context.method_25294(fx, fy, fx + fw, fy + 1, bh); context.method_25294(fx, fy, fx + 1, fy + fh, bh);
/*  692 */         context.method_25294(fx, fy + fh - 1, fx + fw, fy + fh, c[1]); context.method_25294(fx + fw - 1, fy, fx + fw, fy + fh, c[1]);
/*      */         
/*  694 */         if (i < floorsToShow - 1) {
/*  695 */           int nfy = topY + (int)((i + 1) / (floorsToShow - 1) * (th - fh)), gapH = nfy - fy + fh;
/*  696 */           if (gapH > 2) context.method_25294(fx + fw / 3, fy + fh + gapH / 2, fx + fw * 2 / 3, fy + fh + gapH / 2 + 1, color(50, 40, 30, 100)); 
/*      */         } 
/*  698 */         context.method_25300(this.field_22793, String.valueOf(df), cx, fy + 2, 
/*  699 */             (df == this.currentFloor) ? ACCENT_ORANGE : ((df < this.currentFloor) ? ACCENT_WARM : TEXT_DIM));
/*  700 */         if (mouseX >= fx && mouseX <= fx + fw && mouseY >= fy && mouseY <= fy + fh) this.hoveredFloor = df;
/*      */         
/*  702 */         List<BattleTowerNetwork.ActivePlayerData> players = getPlayersOnFloor(df);
/*  703 */         if (!players.isEmpty()) drawPlayerBadge(context, fx + fw - 11, fy + 2, 8, players.size()); 
/*      */       } 
/*  705 */     }  if (this.hoveredFloor > 0) drawFloorTooltip(context, mouseX, mouseY); 
/*      */   }
/*      */   
/*      */   private int[] getFloorColors(int floor) {
/*  709 */     boolean cl = (floor < this.currentFloor), cu = (floor == this.currentFloor);
/*  710 */     int ahead = floor - this.currentFloor;
/*      */     
/*  712 */     if (cu)
/*      */     {
/*  714 */       return new int[] { color(180, 110, 60, 255), color(255, 180, 100, 255), color(255, 180, 100, 120) }; } 
/*  715 */     if (cl) {
/*      */       
/*  717 */       int behind = this.currentFloor - floor;
/*  718 */       if (behind <= 3) return new int[] { color(60, 140, 80, 255), color(100, 200, 120, 255), color(100, 200, 120, 80) }; 
/*  719 */       if (behind <= 6) return new int[] { color(50, 120, 70, 255), color(85, 180, 100, 255), color(85, 180, 100, 60) }; 
/*  720 */       return new int[] { color(45, 105, 60, 255), color(75, 160, 90, 255), color(75, 160, 90, 50) };
/*      */     } 
/*      */     
/*  723 */     if (ahead <= 2) return new int[] { color(60, 55, 35, 255), color(100, 90, 50, 255), color(100, 90, 50, 60) }; 
/*  724 */     if (ahead <= 4) return new int[] { color(60, 45, 35, 255), color(100, 70, 50, 255), color(100, 70, 50, 60) }; 
/*  725 */     return new int[] { color(60, 35, 35, 255), color(100, 50, 50, 255), color(100, 50, 50, 60) };
/*      */   }
/*      */ 
/*      */   
/*      */   private void drawPlayerBadge(class_332 context, int bx, int by, int size, int count) {
/*  730 */     for (int g = 2; g > 0; ) { context.method_25294(bx - g, by - g, bx + size + g, by + size + g, color(200, 140, 60, 60 * g / 2)); g--; }
/*  731 */      context.method_25294(bx, by, bx + size, by + size, color(50, 30, 15, 255));
/*  732 */     context.method_49601(bx, by, size, size, color(255, 160, 60, 255));
/*  733 */     String ct = String.valueOf(count);
/*  734 */     context.method_51433(this.field_22793, ct, bx + (size - this.field_22793.method_1727(ct)) / 2, by + 1, color(255, 180, 80, 255), false);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void drawShopSectionPanel(class_332 context, int x, int y, int w, int h, int mouseX, int mouseY) {
/*  740 */     String title = TranslationHelper.translate("gui.shop.title", new Object[0]).getString();
/*  741 */     if (title.isEmpty()) title = "Battle Points Shop"; 
/*  742 */     drawSectionBox(context, x, y, w, h, title, ACCENT_RED);
/*      */     
/*  744 */     if (this.currentSeason <= 0) {
/*  745 */       int centerX = x + w / 2;
/*  746 */       int centerY = y + h / 2;
/*  747 */       context.method_25300(this.field_22793, "§lBETA SEASON", centerX, centerY - 18, ACCENT_ORANGE);
/*  748 */       context.method_25300(this.field_22793, "The shop is unavailable", centerX, centerY - 4, TEXT_SECONDARY);
/*  749 */       context.method_25300(this.field_22793, "during the beta season.", centerX, centerY + 8, TEXT_SECONDARY);
/*  750 */       context.method_25300(this.field_22793, "It will open with Season 1.", centerX, centerY + 22, TEXT_DIM);
/*      */       
/*      */       return;
/*      */     } 
/*  754 */     int itemY = y + 22, itemH = 18, vis = 10, itemAreaW = w - 24;
/*  755 */     for (int s = 0; s < vis && s + this.shopScrollOffset < this.shopItems.size(); s++) {
/*  756 */       int idx = s + this.shopScrollOffset;
/*  757 */       ShopItem item = this.shopItems.get(idx);
/*  758 */       int ix = x + 4, cy = itemY + s * itemH, iw = itemAreaW;
/*  759 */       boolean hov = (mouseX >= ix && mouseX < ix + iw && mouseY >= cy && mouseY < cy + itemH - 1);
/*  760 */       int cq = ((Integer)this.shoppingCart.getOrDefault(item.id(), Integer.valueOf(0))).intValue();
/*  761 */       boolean inCart = (cq > 0);
/*  762 */       int bg = inCart ? color(55, 40, 25, 200) : color(30, 22, 15, 180);
/*  763 */       context.method_25294(ix + 1, cy + 1, ix + iw - 1, cy + itemH - 2, bg);
/*  764 */       int bc = inCart ? ACCENT_WARM : (hov ? ACCENT_ORANGE : BORDER_COLOR);
/*  765 */       context.method_25294(ix, cy, ix + iw, cy + 1, bc); context.method_25294(ix, cy + itemH - 2, ix + iw, cy + itemH - 1, BattleTowerStyledButton.darken(bc, 25));
/*  766 */       context.method_25294(ix, cy, ix + 1, cy + itemH - 1, bc); context.method_25294(ix + iw - 1, cy, ix + iw, cy + itemH - 1, BattleTowerStyledButton.darken(bc, 25));
/*  767 */       if (hov) this.hoveredShopIndex = idx;
/*      */       
/*  769 */       boolean canAfford = (this.bpBalance >= item.bpCost());
/*  770 */       String itemName = item.displayName();
/*  771 */       class_1799 itemStack = null;
/*  772 */       if (!item.isCommandItem()) try { class_1792 mi = (class_1792)class_7923.field_41178.method_10223(item.itemId()); if (mi != null) { itemStack = new class_1799((class_1935)mi, item.quantity()); itemName = itemStack.method_7964().getString(); }  } catch (Exception exception) {} 
/*  773 */       if (itemStack != null && !itemStack.method_7960()) {
/*  774 */         class_4587 mat = context.method_51448(); mat.method_22903();
/*  775 */         if (hov) { mat.method_46416((ix + 10), (cy + 9), 0.0F); mat.method_22905(1.15F, 1.15F, 1.0F); mat.method_46416(-8.0F, -8.0F, 0.0F); } else { mat.method_46416((ix + 2), (cy + 1), 0.0F); }
/*  776 */          context.method_51427(itemStack, 0, 0); mat.method_22909();
/*      */       } 
/*  778 */       int tx = ix + 20;
/*  779 */       if (!item.isCommandItem() && item.quantity() > 1) itemName = itemName + " §7x" + itemName; 
/*  780 */       if (inCart) itemName = "§e[" + cq + "] §r" + itemName;
/*      */       
/*  782 */       String priceNum = String.valueOf(item.bpCost());
/*  783 */       int bpIconSz = 7;
/*  784 */       int priceNumW = this.field_22793.method_1727(priceNum);
/*  785 */       int bpIconGap = 3;
/*  786 */       int priceBlockW = bpIconSz + bpIconGap + priceNumW;
/*  787 */       int priceStartX = ix + iw - priceBlockW - 3;
/*  788 */       int priceColor = canAfford ? ACCENT_GOLD : color(150, 60, 60, 255);
/*  789 */       RankedIcon.BATTLE_POINTS.drawTexture(context, priceStartX, cy + 5, bpIconSz, bpIconSz);
/*  790 */       context.method_51433(this.field_22793, priceNum, priceStartX + bpIconSz + bpIconGap, cy + 5, priceColor, true);
/*      */       
/*  792 */       int maxNameW = priceStartX - tx - 4;
/*  793 */       if (this.field_22793.method_1727(itemName) > maxNameW) {
/*  794 */         while (this.field_22793.method_1727(itemName + "..") > maxNameW && itemName.length() > 1)
/*  795 */           itemName = itemName.substring(0, itemName.length() - 1); 
/*  796 */         itemName = itemName + "..";
/*      */       } 
/*  798 */       context.method_51433(this.field_22793, itemName, tx, cy + 5, canAfford ? TEXT_PRIMARY : TEXT_DIM, true);
/*      */     } 
/*      */ 
/*      */     
/*  802 */     int sbx = x + w - 18, st = y + 22;
/*  803 */     int cartDividerY = y + h - 48;
/*  804 */     int downBtnY = cartDividerY - 14;
/*  805 */     this.shopUpHovered = drawSmallButton(context, sbx, st, 12, 12, "▲", mouseX, mouseY, (this.shopScrollOffset > 0));
/*  806 */     this.shopDownHovered = drawSmallButton(context, sbx, downBtnY, 12, 12, "▼", mouseX, mouseY, (this.shopScrollOffset + vis < this.shopItems.size()));
/*      */     
/*  808 */     this.scrollbarX = sbx + 1; this.scrollbarTrackY = st + 14; this.scrollbarWidth = 10;
/*  809 */     this.scrollbarTrackHeight = downBtnY - this.scrollbarTrackY - 2;
/*  810 */     if (this.scrollbarTrackHeight < 10) this.scrollbarTrackHeight = 10; 
/*  811 */     context.method_25294(this.scrollbarX, this.scrollbarTrackY, this.scrollbarX + this.scrollbarWidth, this.scrollbarTrackY + this.scrollbarTrackHeight, color(25, 18, 12, 255));
/*  812 */     if (this.shopItems.size() > vis) {
/*  813 */       float pct = this.shopScrollOffset / (this.shopItems.size() - vis);
/*  814 */       this.scrollbarThumbHeight = Math.max(15, (int)(vis / this.shopItems.size() * this.scrollbarTrackHeight));
/*  815 */       this.scrollbarThumbY = this.scrollbarTrackY + (int)(pct * (this.scrollbarTrackHeight - this.scrollbarThumbHeight));
/*      */       
/*  817 */       if (this.scrollbarThumbY + this.scrollbarThumbHeight > this.scrollbarTrackY + this.scrollbarTrackHeight) {
/*  818 */         this.scrollbarThumbY = this.scrollbarTrackY + this.scrollbarTrackHeight - this.scrollbarThumbHeight;
/*      */       }
/*  820 */       boolean th = ((mouseX >= this.scrollbarX && mouseX < this.scrollbarX + this.scrollbarWidth && mouseY >= this.scrollbarThumbY && mouseY < this.scrollbarThumbY + this.scrollbarThumbHeight) || this.scrollbarDragging);
/*  821 */       context.method_25294(this.scrollbarX, this.scrollbarThumbY, this.scrollbarX + this.scrollbarWidth, this.scrollbarThumbY + this.scrollbarThumbHeight, th ? color(180, 100, 50, 255) : color(150, 70, 40, 255));
/*  822 */       context.method_49601(this.scrollbarX, this.scrollbarThumbY, this.scrollbarWidth, this.scrollbarThumbHeight, th ? color(220, 130, 60, 255) : color(200, 100, 50, 255));
/*      */     } 
/*      */ 
/*      */     
/*  826 */     int cartY = y + h - 48;
/*      */     
/*  828 */     for (int i = 0; i < w - 8; i++) {
/*  829 */       float p = i / (w - 8);
/*  830 */       int alpha = (int)(120.0D + 80.0D * Math.sin(p * Math.PI));
/*  831 */       context.method_25294(x + 4 + i, cartY, x + 5 + i, cartY + 1, ACCENT_WARM & 0xFFFFFF | alpha << 24);
/*      */     } 
/*      */     
/*  834 */     context.method_25294(x + 4, cartY + 1, x + w - 4, cartY + 40, color(18, 14, 10, 200));
/*      */     
/*  836 */     int totalCost = 0, cartCount = 0;
/*  837 */     for (Map.Entry<String, Integer> e : this.shoppingCart.entrySet()) { ShopItem si = getShopItemById(e.getKey()); if (si != null) { totalCost += si.bpCost() * ((Integer)e.getValue()).intValue(); cartCount += ((Integer)e.getValue()).intValue(); }
/*      */        }
/*  839 */      String itemWord = (cartCount == 1) ? TranslationHelper.translate("gui.shop.item", new Object[0]).getString() : TranslationHelper.translate("gui.shop.items", new Object[0]).getString();
/*  840 */     String cartLabel = TranslationHelper.translate("gui.shop.cart", new Object[0]).getString();
/*  841 */     String totalLabel = TranslationHelper.translate("gui.shop.total", new Object[0]).getString();
/*      */     
/*  843 */     context.method_51433(this.field_22793, cartLabel, x + 8, cartY + 3, TEXT_DIM, true);
/*  844 */     int cartCountColor = (cartCount > 0) ? ACCENT_GOLD : TEXT_DIM;
/*  845 */     context.method_51433(this.field_22793, "" + cartCount + " " + cartCount, x + 8 + this.field_22793.method_1727(cartLabel + " "), cartY + 3, cartCountColor, true);
/*      */     
/*  847 */     context.method_51433(this.field_22793, totalLabel, x + 8, cartY + 13, TEXT_DIM, true);
/*  848 */     int totalColor = (totalCost > 0) ? ((this.bpBalance >= totalCost) ? ACCENT_GOLD : ACCENT_RED) : TEXT_DIM;
/*  849 */     int totalLabelEndX = x + 8 + this.field_22793.method_1727(totalLabel + " ");
/*  850 */     RankedIcon.BATTLE_POINTS.drawTexture(context, totalLabelEndX, cartY + 13, 7, 7);
/*  851 */     context.method_51433(this.field_22793, " " + totalCost, totalLabelEndX + 7, cartY + 13, totalColor, true);
/*      */     
/*  853 */     int bY = cartY + 24, bW = 62;
/*  854 */     this.purchaseCartHovered = drawButton(context, x + 6, bY, bW, 18, "§a" + TranslationHelper.translate("gui.shop.purchase", new Object[0]).getString(), mouseX, mouseY, (!this.shoppingCart.isEmpty() && this.bpBalance >= totalCost));
/*  855 */     this.clearCartHovered = drawButton(context, x + 6 + bW + 4, bY, bW, 18, "§c" + TranslationHelper.translate("gui.shop.clear", new Object[0]).getString(), mouseX, mouseY, !this.shoppingCart.isEmpty());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void drawBottomButtons(class_332 context, int mouseX, int mouseY) {
/*  861 */     int btnY = this.guiTop + 360 - 30;
/*  862 */     int btnW = 70, btnH = 22, sp = 6;
/*      */     
/*  864 */     int totalBtnW = btnW * 3 + sp * 2;
/*  865 */     int startX = this.guiLeft + (520 - totalBtnW) / 2;
/*      */     
/*  867 */     if (!this.partyLockedIn) {
/*  868 */       this.lockInHovered = drawButton(context, startX, btnY, btnW, btnH, TranslationHelper.translate("gui.button.lock_in", new Object[0]).getString(), mouseX, mouseY, hasValidParty());
/*  869 */     } else if (!this.runActive) {
/*  870 */       this.unlockHovered = drawLockedButton(context, startX, btnY, btnW, btnH, TranslationHelper.translate("gui.button.locked", new Object[0]).getString(), mouseX, mouseY);
/*      */     } else {
/*  872 */       drawLockedButton(context, startX, btnY, btnW, btnH, TranslationHelper.translate("gui.button.locked", new Object[0]).getString(), mouseX, mouseY);
/*      */     } 
/*      */     
/*  875 */     this.startBattleHovered = drawButton(context, startX + btnW + sp, btnY, btnW, btnH, "§l" + TranslationHelper.translate("gui.button.battle", new Object[0]).getString(), mouseX, mouseY, this.partyLockedIn);
/*      */     
/*  877 */     if (this.runActive) {
/*  878 */       this.forfeitHovered = drawButton(context, startX + (btnW + sp) * 2, btnY, btnW, btnH, "§c" + TranslationHelper.translate("gui.button.forfeit", new Object[0]).getString(), mouseX, mouseY, true);
/*      */     } else {
/*  880 */       drawButton(context, startX + (btnW + sp) * 2, btnY, btnW, btnH, TranslationHelper.translate("gui.button.forfeit", new Object[0]).getString(), mouseX, mouseY, false);
/*  881 */       this.forfeitHovered = false;
/*      */     } 
/*      */     
/*  884 */     this.closeHovered = drawButton(context, this.guiLeft + 520 - 60, btnY, 52, btnH, TranslationHelper.translate("gui.button.close", new Object[0]).getString(), mouseX, mouseY, true);
/*      */   }
/*      */   
/*      */   private boolean isMouseOverStartBattleButton(int mouseX, int mouseY) {
/*  888 */     int btnY = this.guiTop + 360 - 30, btnW = 70, sp = 6;
/*  889 */     int totalBtnW = btnW * 3 + sp * 2;
/*  890 */     int bx = this.guiLeft + (520 - totalBtnW) / 2 + btnW + sp;
/*  891 */     return (mouseX >= bx && mouseX < bx + btnW && mouseY >= btnY && mouseY < btnY + 22);
/*      */   }
/*      */ 
/*      */   
/*      */   private void drawDiamond(class_332 ctx, int cx, int cy, int size, int col) {
/*      */     int i;
/*  897 */     for (i = 0; i <= size; ) { ctx.method_25294(cx - i, cy - size + i, cx + i + 1, cy - size + i + 1, col); i++; }
/*  898 */      for (i = 0; i <= size; ) { ctx.method_25294(cx - i, cy + size - i, cx + i + 1, cy + size - i + 1, col); i++; }
/*      */   
/*      */   }
/*      */   
/*  902 */   private void drawDiamondGlow(class_332 ctx, int cx, int cy, int gs, int col, int ma) { for (int g = gs; g > 0; ) { int a = ma * g / gs / 3, gc = col & 0xFFFFFF | a << 24; int i;
/*  903 */       for (i = 0; i <= g; ) { ctx.method_25294(cx - i, cy - g + i, cx + i + 1, cy - g + i + 1, gc); i++; }
/*  904 */        for (i = 0; i <= g; ) { ctx.method_25294(cx - i, cy + g - i, cx + i + 1, cy + g - i + 1, gc); i++; }
/*      */       
/*      */       g--; }
/*      */      } private void drawTabButton(class_332 ctx, int x, int y, int w, int h, String text, boolean active, boolean hov, boolean isTower) {
/*  908 */     ctx.method_25294(x, y, x + w, y + h, color(25, 15, 12, 255));
/*  909 */     ctx.method_49601(x, y, w, h, active ? ACCENT_ORANGE : (hov ? BORDER_HIGHLIGHT : BORDER_COLOR));
/*  910 */     if (active) ctx.method_25294(x + 1, y + h - 2, x + w - 1, y + h, ACCENT_ORANGE); 
/*  911 */     int tw = this.field_22793.method_1727(text), tx = x + (w - tw) / 2, ty = y + (h - 8) / 2;
/*  912 */     int cx = tx;
/*  913 */     for (int i = 0; i < text.length(); i++) {
/*  914 */       int r, g, b; float p = i / Math.max(1, text.length() - 1);
/*      */       
/*  916 */       if (isTower) { r = (int)(255.0F - p * 35.0F); g = (int)(160.0F - p * 120.0F); b = (int)(40.0F - p * 10.0F); }
/*  917 */       else { r = (int)(220.0F + p * 35.0F); g = (int)(150.0F + p * 50.0F); b = (int)(50.0F + p * 30.0F); }
/*  918 */        if (!active && !hov) { r /= 2; g /= 2; b /= 2; }
/*  919 */        ctx.method_51433(this.field_22793, String.valueOf(text.charAt(i)), cx, ty, color(r, g, b, 255), true);
/*  920 */       cx += this.field_22793.method_1727(String.valueOf(text.charAt(i)));
/*      */     } 
/*      */   }
/*      */   
/*      */   private boolean drawButton(class_332 ctx, int x, int y, int w, int h, String text, int mx, int my, boolean en) {
/*  925 */     boolean hov = (en && mx >= x && mx < x + w && my >= y && my < y + h);
/*  926 */     int bg = !en ? BattleTowerStyledButton.getButtonDisabled() : (hov ? BattleTowerStyledButton.getButtonHover() : BattleTowerStyledButton.getButtonBg());
/*  927 */     ctx.method_25294(x + 1, y + 1, x + w - 1, y + h - 1, bg);
/*  928 */     if (en) ctx.method_25294(x + 1, y + h - 3, x + w - 1, y + h - 1, BattleTowerStyledButton.darken(bg, 15)); 
/*  929 */     int bc = hov ? BattleTowerStyledButton.getButtonBorderHover() : BattleTowerStyledButton.getButtonBorder();
/*  930 */     if (!en) bc = color(50, 35, 25, 255); 
/*  931 */     ctx.method_25294(x + 1, y, x + w - 1, y + 1, bc); ctx.method_25294(x + 1, y + h - 1, x + w - 1, y + h, BattleTowerStyledButton.darken(bc, 30));
/*  932 */     ctx.method_25294(x, y + 1, x + 1, y + h - 1, bc); ctx.method_25294(x + w - 1, y + 1, x + w, y + h - 1, BattleTowerStyledButton.darken(bc, 30));
/*  933 */     if (en) { ctx.method_25294(x + 2, y + 1, x + w - 2, y + 2, BattleTowerStyledButton.lighten(bg, 20)); ctx.method_25294(x + 1, y + 2, x + 2, y + h - 3, BattleTowerStyledButton.lighten(bg, 10)); }
/*  934 */      ctx.method_25294(x, y, x + 1, y + 1, 0); ctx.method_25294(x + w - 1, y, x + w, y + 1, 0); ctx.method_25294(x, y + h - 1, x + 1, y + h, 0); ctx.method_25294(x + w - 1, y + h - 1, x + w, y + h, 0);
/*  935 */     ctx.method_25300(this.field_22793, text, x + w / 2, y + (h - 8) / 2, en ? BattleTowerStyledButton.getTextPrimary() : BattleTowerStyledButton.getTextDim());
/*  936 */     return hov;
/*      */   }
/*      */   
/*      */   private boolean drawLockedButton(class_332 ctx, int x, int y, int w, int h, String text, int mx, int my) {
/*  940 */     boolean hov = (mx >= x && mx < x + w && my >= y && my < y + h);
/*  941 */     int bg = color(40, 80, 60, 255);
/*  942 */     ctx.method_25294(x + 1, y + 1, x + w - 1, y + h - 1, bg);
/*  943 */     ctx.method_25294(x + 1, y, x + w - 1, y + 1, ACCENT_GREEN); ctx.method_25294(x + 1, y + h - 1, x + w - 1, y + h, BattleTowerStyledButton.darken(ACCENT_GREEN, 30));
/*  944 */     ctx.method_25294(x, y + 1, x + 1, y + h - 1, ACCENT_GREEN); ctx.method_25294(x + w - 1, y + 1, x + w, y + h - 1, BattleTowerStyledButton.darken(ACCENT_GREEN, 30));
/*  945 */     ctx.method_25294(x, y, x + 1, y + 1, 0); ctx.method_25294(x + w - 1, y, x + w, y + 1, 0); ctx.method_25294(x, y + h - 1, x + 1, y + h, 0); ctx.method_25294(x + w - 1, y + h - 1, x + w, y + h, 0);
/*  946 */     ctx.method_25300(this.field_22793, text, x + w / 2, y + (h - 8) / 2, ACCENT_GREEN);
/*  947 */     return hov;
/*      */   }
/*      */   
/*      */   private boolean drawSmallButton(class_332 ctx, int x, int y, int w, int h, String text, int mx, int my, boolean en) {
/*  951 */     boolean hov = (en && mx >= x && mx < x + w && my >= y && my < y + h);
/*  952 */     int bg = !en ? BattleTowerStyledButton.getButtonDisabled() : (hov ? BattleTowerStyledButton.getButtonHover() : BattleTowerStyledButton.getButtonBg());
/*  953 */     ctx.method_25294(x + 1, y + 1, x + w - 1, y + h - 1, bg);
/*  954 */     int bc = hov ? BattleTowerStyledButton.getButtonBorderHover() : BattleTowerStyledButton.getButtonBorder();
/*  955 */     ctx.method_25294(x, y, x + w, y + 1, bc); ctx.method_25294(x, y + h - 1, x + w, y + h, BattleTowerStyledButton.darken(bc, 25));
/*  956 */     ctx.method_25294(x, y, x + 1, y + h, bc); ctx.method_25294(x + w - 1, y, x + w, y + h, BattleTowerStyledButton.darken(bc, 25));
/*  957 */     ctx.method_25300(this.field_22793, text, x + w / 2, y + (h - 8) / 2, en ? BattleTowerStyledButton.getTextPrimary() : BattleTowerStyledButton.getTextDim());
/*  958 */     return hov;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void renderPokemonInSlot(class_332 context, BattleTowerNetwork.PartyEntry pokemon, int sx, int sy, int ss, int si, float delta) {
/*      */     
/*  965 */     try { class_2960 speciesId = class_2960.method_60654(pokemon.speciesId());
/*  966 */       FloatingState state = this.pokemonStates.get(Integer.valueOf(si));
/*      */       
/*  968 */       Set<String> aspects = parseAspects(pokemon.aspects());
/*  969 */       Pokemon parsed = getPokemonFromEntry(pokemon);
/*  970 */       if (parsed != null) {
/*  971 */         aspects = aspects.isEmpty() ? parsed.getAspects() : aspects;
/*  972 */       } else if (pokemon.shiny()) {
/*  973 */         aspects = Set.of("shiny");
/*      */       } 
/*  975 */       state.setCurrentAspects(aspects);
/*  976 */       class_4587 mat = context.method_51448(); mat.method_22903();
/*      */       
/*  978 */       mat.method_22904(sx + ss / 2.0D, sy + 4.0D, 0.0D); mat.method_22905(2.5F, 2.5F, 1.0F);
/*  979 */       Quaternionf rot = new Quaternionf(); rot.rotationXYZ((float)Math.toRadians(13.0D), (float)Math.toRadians(35.0D), 0.0F);
/*  980 */       if (PokemonRenderHelper.isAvailable())
/*  981 */       { PokemonRenderHelper.draw(speciesId, mat, rot, state, delta); }
/*  982 */       else { drawFallback(context, pokemon, sx, sy, ss); }
/*  983 */        mat.method_22909(); }
/*  984 */     catch (Exception e) { drawFallback(context, pokemon, sx, sy, ss); }
/*      */   
/*      */   }
/*      */   private void drawFallback(class_332 ctx, BattleTowerNetwork.PartyEntry p, int x, int y, int s) {
/*  988 */     ctx.method_25300(this.field_22793, p.speciesName().isEmpty() ? "?" : p.speciesName().substring(0, 1).toUpperCase(), x + s / 2, y + s / 2 - 4, TEXT_PRIMARY);
/*      */   }
/*      */   
/*      */   private Set<String> parseAspects(String aspects) {
/*  992 */     if (aspects == null || aspects.isBlank()) return Set.of(); 
/*  993 */     Set<String> parsed = new HashSet<>();
/*  994 */     for (String aspect : aspects.split(",")) {
/*  995 */       String trimmed = aspect.trim();
/*  996 */       if (!trimmed.isEmpty()) parsed.add(trimmed); 
/*      */     } 
/*  998 */     return parsed;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private Pokemon getPokemonFromEntry(BattleTowerNetwork.PartyEntry entry) {
/* 1004 */     if (this.pokemonCache.containsKey(Integer.valueOf(entry.slot()))) return this.pokemonCache.get(Integer.valueOf(entry.slot())); 
/* 1005 */     if (entry.pokemonData() == null || entry.pokemonData().isEmpty()) return null; 
/*      */     try {
/* 1007 */       if ((class_310.method_1551()).field_1687 == null) return null; 
/* 1008 */       class_2487 nbt = class_2522.method_10718(entry.pokemonData());
/* 1009 */       Pokemon pokemon = new Pokemon();
/* 1010 */       pokemon.loadFromNBT((class_310.method_1551()).field_1687.method_30349(), nbt);
/* 1011 */       this.pokemonCache.put(Integer.valueOf(entry.slot()), pokemon);
/* 1012 */       return pokemon;
/* 1013 */     } catch (Exception e) {
/* 1014 */       AtlasMod.LOGGER.debug("Failed to deserialize Pokemon from SNBT: {}", e.getMessage());
/* 1015 */       return null;
/*      */     } 
/*      */   }
/*      */   
/*      */   private static boolean hasHiddenAbility(Pokemon pokemon) {
/* 1020 */     AbilityPool abilities = pokemon.getForm().getAbilities();
/* 1021 */     for (PotentialAbility ability : abilities) {
/* 1022 */       if (ability instanceof HiddenAbility) { HiddenAbility ha = (HiddenAbility)ability; if (ha.getTemplate() == pokemon.getAbility().getTemplate())
/* 1023 */           return true;  }
/*      */     
/* 1025 */     }  return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void drawPartyTooltip(class_332 context, BattleTowerNetwork.PartyEntry entry, int mx, int my) {
/*      */     List<class_2561> tooltip;
/* 1034 */     long now = System.currentTimeMillis();
/* 1035 */     String key = entry.speciesId() + "|" + entry.speciesId() + "|" + entry.level() + "|" + entry.shiny() + "|" + entry.heldItem() + "|" + entry.caughtBall();
/* 1036 */     TooltipCacheEntry cache = this.partyTooltipCache.get(Integer.valueOf(entry.slot()));
/*      */     
/* 1038 */     if (cache != null && cache.expiresAt >= now && cache.key.equals(key)) {
/* 1039 */       tooltip = cache.tooltip;
/*      */     } else {
/* 1041 */       tooltip = new ArrayList<>();
/* 1042 */       Pokemon pokemon = getPokemonFromEntry(entry);
/*      */       
/* 1044 */       if (pokemon != null) {
/*      */         
/* 1046 */         Gender gender = pokemon.getGender();
/*      */         
/* 1048 */         String genderSymbol = (gender == Gender.MALE) ? " ♂" : ((gender == Gender.FEMALE) ? " ♀" : " ⚲");
/* 1049 */         class_124 genderFormat = (gender == Gender.MALE) ? class_124.field_1075 : ((gender == Gender.FEMALE) ? class_124.field_1076 : class_124.field_1068);
/*      */         
/* 1051 */         class_5250 headerLine = pokemon.getSpecies().getTranslatedName().method_27661().method_27695(new class_124[] { class_124.field_1067, class_124.field_1068 });
/* 1052 */         headerLine = headerLine.method_10852((class_2561)class_2561.method_43470(genderSymbol).method_27692(genderFormat));
/* 1053 */         headerLine = headerLine.method_10852((class_2561)class_2561.method_43470(" Lv. " + pokemon.getLevel()).method_27692(class_124.field_1068));
/* 1054 */         tooltip.add(headerLine);
/* 1055 */         tooltip.add(class_2561.method_43473());
/*      */ 
/*      */         
/* 1058 */         class_5250 tagsLine = null;
/* 1059 */         if (pokemon.isLegendary()) {
/* 1060 */           tagsLine = class_2561.method_43473().method_27661();
/* 1061 */           tagsLine = tagsLine.method_10852((class_2561)CobblemonFontIcon.LEGENDARY.draw());
/*      */         } 
/* 1063 */         if (pokemon.isUltraBeast()) {
/* 1064 */           tagsLine = (tagsLine == null) ? class_2561.method_43473().method_27661() : tagsLine.method_10852((class_2561)class_2561.method_43470(" "));
/* 1065 */           tagsLine = tagsLine.method_10852((class_2561)CobblemonFontIcon.ULTRA_BEAST.draw());
/*      */         } 
/* 1067 */         if (pokemon.isMythical()) {
/* 1068 */           tagsLine = (tagsLine == null) ? class_2561.method_43473().method_27661() : tagsLine.method_10852((class_2561)class_2561.method_43470(" "));
/* 1069 */           tagsLine = tagsLine.method_10852((class_2561)CobblemonFontIcon.MYTHICAL.draw());
/*      */         } 
/* 1071 */         if (pokemon.hasLabels(new String[] { "paradox" }) || pokemon.getSpecies().getName().equals("Miraidon") || pokemon.getSpecies().getName().equals("Koraidon")) {
/* 1072 */           tagsLine = (tagsLine == null) ? class_2561.method_43473().method_27661() : tagsLine.method_10852((class_2561)class_2561.method_43470(" "));
/* 1073 */           tagsLine = tagsLine.method_10852((class_2561)CobblemonFontIcon.getParadoxIcon(pokemon.getSpecies().getName()).draw());
/*      */         } 
/* 1075 */         if (pokemon.getShiny()) {
/* 1076 */           tagsLine = (tagsLine == null) ? class_2561.method_43473().method_27661() : tagsLine.method_10852((class_2561)class_2561.method_43470(" "));
/* 1077 */           tagsLine = tagsLine.method_10852((class_2561)CobblemonFontIcon.SHINY.draw());
/*      */         } 
/* 1079 */         if (tagsLine != null) tooltip.add(tagsLine);
/*      */ 
/*      */         
/* 1082 */         class_5250 typesLine = class_2561.method_43473().method_27661();
/* 1083 */         for (ElementalType type : pokemon.getTypes()) {
/* 1084 */           typesLine = typesLine.method_10852((class_2561)CobblemonFontIcon.getTypeIcon(type.getName()).draw()).method_10852((class_2561)class_2561.method_43470(" "));
/*      */         }
/* 1086 */         tooltip.add(typesLine);
/*      */         
/* 1088 */         tooltip.add(class_2561.method_43473());
/*      */ 
/*      */         
/* 1091 */         String ballName = pokemon.getCaughtBall().getName().toString();
/*      */         try {
/* 1093 */           PokeBall ball = getPokeBallById(class_2960.method_60654(ballName));
/* 1094 */           if (ball != null) {
/* 1095 */             tooltip.add(class_2561.method_43470("Caught Ball: ").method_27692(class_124.field_1080)
/* 1096 */                 .method_10852((class_2561)(new class_1799((class_1935)ball.item())).method_7964().method_27661().method_27692(class_124.field_1068)));
/*      */           }
/* 1098 */         } catch (Exception e) {
/* 1099 */           String fb = ballName.contains(":") ? ballName.substring(ballName.indexOf(":") + 1) : ballName;
/* 1100 */           tooltip.add(class_2561.method_43470("Caught Ball: ").method_27692(class_124.field_1080)
/* 1101 */               .method_10852((class_2561)class_2561.method_43470(capitalize(fb.replace("_", " "))).method_27692(class_124.field_1068)));
/*      */         } 
/*      */ 
/*      */         
/* 1105 */         class_1799 heldItem = pokemon.heldItem();
/* 1106 */         tooltip.add(class_2561.method_43470("Held Item: ").method_27692(class_124.field_1080)
/* 1107 */             .method_10852((class_2561)class_2561.method_43470(heldItem.method_7960() ? "None" : heldItem.method_7964().getString()).method_27692(heldItem.method_7960() ? class_124.field_1063 : class_124.field_1068)));
/*      */ 
/*      */         
/* 1110 */         String abilityName = pokemon.getAbility().getDisplayName();
/* 1111 */         boolean hidden = hasHiddenAbility(pokemon);
/* 1112 */         tooltip.add(class_2561.method_43470("Ability: ").method_27692(class_124.field_1080)
/* 1113 */             .method_10852((class_2561)class_2561.method_43470(abilityName + abilityName).method_27692(class_124.field_1068)));
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1118 */         String natureName = (pokemon.getMintedNature() != null) ? (pokemon.getMintedNature().getDisplayName() + " (Minted)") : pokemon.getNature().getDisplayName();
/* 1119 */         tooltip.add(class_2561.method_43470("Nature: ").method_27692(class_124.field_1080)
/* 1120 */             .method_10852((class_2561)class_2561.method_43470(natureName).method_27692(class_124.field_1068)));
/*      */ 
/*      */         
/* 1123 */         int ivHp = ((Integer)Objects.<Integer>requireNonNullElse(pokemon.getIvs().get((Stat)Stats.HP), Integer.valueOf(0))).intValue();
/* 1124 */         int ivAtk = ((Integer)Objects.<Integer>requireNonNullElse(pokemon.getIvs().get((Stat)Stats.ATTACK), Integer.valueOf(0))).intValue();
/* 1125 */         int ivDef = ((Integer)Objects.<Integer>requireNonNullElse(pokemon.getIvs().get((Stat)Stats.DEFENCE), Integer.valueOf(0))).intValue();
/* 1126 */         int ivSpAtk = ((Integer)Objects.<Integer>requireNonNullElse(pokemon.getIvs().get((Stat)Stats.SPECIAL_ATTACK), Integer.valueOf(0))).intValue();
/* 1127 */         int ivSpDef = ((Integer)Objects.<Integer>requireNonNullElse(pokemon.getIvs().get((Stat)Stats.SPECIAL_DEFENCE), Integer.valueOf(0))).intValue();
/* 1128 */         int ivSpd = ((Integer)Objects.<Integer>requireNonNullElse(pokemon.getIvs().get((Stat)Stats.SPEED), Integer.valueOf(0))).intValue();
/* 1129 */         int totalIVs = ivHp + ivAtk + ivDef + ivSpAtk + ivSpDef + ivSpd;
/* 1130 */         double ivPct = totalIVs / 186.0D * 100.0D;
/*      */         
/* 1132 */         tooltip.add(class_2561.method_43473());
/* 1133 */         tooltip.add(class_2561.method_43470("IVs: ").method_27692(class_124.field_1080)
/* 1134 */             .method_10852((class_2561)class_2561.method_43470("" + totalIVs).method_27692(class_124.field_1068))
/* 1135 */             .method_10852((class_2561)class_2561.method_43470("/").method_27692(class_124.field_1080))
/* 1136 */             .method_10852((class_2561)class_2561.method_43470("186").method_27692(class_124.field_1068))
/* 1137 */             .method_10852((class_2561)class_2561.method_43470(String.format(" (%.0f%%)", new Object[] { Double.valueOf(ivPct) })).method_27692(class_124.field_1080)));
/*      */ 
/*      */ 
/*      */         
/* 1141 */         int[][] ivStats = { { ivHp, STAT_HP }, { ivAtk, STAT_ATK }, { ivDef, STAT_DEF }, { ivSpAtk, STAT_SPATK }, { ivSpDef, STAT_SPDEF }, { ivSpd, STAT_SPD } };
/* 1142 */         Stats[] statEnums = { Stats.HP, Stats.ATTACK, Stats.DEFENCE, Stats.SPECIAL_ATTACK, Stats.SPECIAL_DEFENCE, Stats.SPEED };
/* 1143 */         class_5250 ivLine = class_2561.method_43473().method_27661();
/* 1144 */         for (int si = 0; si < ivStats.length; si++) {
/* 1145 */           int val = ivStats[si][0];
/* 1146 */           int col = ivStats[si][1];
/*      */ 
/*      */ 
/*      */           
/* 1150 */           ivLine = ivLine.method_10852((class_2561)CobblemonFontIcon.getStatIcon(statEnums[si].name()).draw()).method_10852((class_2561)class_2561.method_43470(" ")).method_10852((class_2561)class_2561.method_43470(String.valueOf(val)).method_54663((val == 31) ? ACCENT_GOLD : col)).method_10852((class_2561)class_2561.method_43470(" "));
/*      */         } 
/* 1152 */         tooltip.add(ivLine);
/*      */ 
/*      */ 
/*      */         
/* 1156 */         int evHp = ((Integer)Objects.<Integer>requireNonNullElse(pokemon.getEvs().get((Stat)Stats.HP), Integer.valueOf(0))).intValue();
/* 1157 */         int evAtk = ((Integer)Objects.<Integer>requireNonNullElse(pokemon.getEvs().get((Stat)Stats.ATTACK), Integer.valueOf(0))).intValue();
/* 1158 */         int evDef = ((Integer)Objects.<Integer>requireNonNullElse(pokemon.getEvs().get((Stat)Stats.DEFENCE), Integer.valueOf(0))).intValue();
/* 1159 */         int evSpAtk = ((Integer)Objects.<Integer>requireNonNullElse(pokemon.getEvs().get((Stat)Stats.SPECIAL_ATTACK), Integer.valueOf(0))).intValue();
/* 1160 */         int evSpDef = ((Integer)Objects.<Integer>requireNonNullElse(pokemon.getEvs().get((Stat)Stats.SPECIAL_DEFENCE), Integer.valueOf(0))).intValue();
/* 1161 */         int evSpd = ((Integer)Objects.<Integer>requireNonNullElse(pokemon.getEvs().get((Stat)Stats.SPEED), Integer.valueOf(0))).intValue();
/* 1162 */         int totalEVs = evHp + evAtk + evDef + evSpAtk + evSpDef + evSpd;
/* 1163 */         double evPct = totalEVs / 510.0D * 100.0D;
/*      */         
/* 1165 */         tooltip.add(class_2561.method_43473());
/* 1166 */         tooltip.add(class_2561.method_43470("EVs: ").method_27692(class_124.field_1080)
/* 1167 */             .method_10852((class_2561)class_2561.method_43470("" + totalEVs).method_27692(class_124.field_1068))
/* 1168 */             .method_10852((class_2561)class_2561.method_43470("/").method_27692(class_124.field_1080))
/* 1169 */             .method_10852((class_2561)class_2561.method_43470("510").method_27692(class_124.field_1068))
/* 1170 */             .method_10852((class_2561)class_2561.method_43470(String.format(" (%.0f%%)", new Object[] { Double.valueOf(evPct) })).method_27692(class_124.field_1080)));
/*      */ 
/*      */ 
/*      */         
/* 1174 */         int[] evVals = { evHp, evAtk, evDef, evSpAtk, evSpDef, evSpd };
/* 1175 */         int[] evCols = { STAT_HP, STAT_ATK, STAT_DEF, STAT_SPATK, STAT_SPDEF, STAT_SPD };
/* 1176 */         Stats[] arrayOfStats1 = { Stats.HP, Stats.ATTACK, Stats.DEFENCE, Stats.SPECIAL_ATTACK, Stats.SPECIAL_DEFENCE, Stats.SPEED };
/* 1177 */         class_5250 evLine = class_2561.method_43473().method_27661();
/* 1178 */         for (int i = 0; i < evVals.length; i++)
/*      */         {
/*      */ 
/*      */           
/* 1182 */           evLine = evLine.method_10852((class_2561)CobblemonFontIcon.getStatIcon(arrayOfStats1[i].name()).draw()).method_10852((class_2561)class_2561.method_43470(" ")).method_10852((class_2561)class_2561.method_43470(String.valueOf(evVals[i])).method_54663(evCols[i])).method_10852((class_2561)class_2561.method_43470(" "));
/*      */         }
/* 1184 */         tooltip.add(evLine);
/*      */ 
/*      */ 
/*      */         
/* 1188 */         List<Move> moves = pokemon.getMoveSet().getMoves();
/* 1189 */         if (!moves.isEmpty()) {
/* 1190 */           tooltip.add(class_2561.method_43473());
/* 1191 */           tooltip.add(class_2561.method_43470("Moves:").method_27692(class_124.field_1054));
/* 1192 */           class_5250 moveComponent = class_2561.method_43473();
/* 1193 */           for (int index = 0; index < moves.size(); index++) {
/* 1194 */             Move move = moves.get(index);
/* 1195 */             boolean isLast = (index == moves.size() - 1);
/*      */             
/* 1197 */             moveComponent = moveComponent.method_10852((class_2561)class_2561.method_43470(move.getDisplayName().getString()).method_27692(class_124.field_1068)).method_10852(isLast ? (class_2561)class_2561.method_43473() : (class_2561)class_2561.method_43470(", ").method_27692(class_124.field_1080));
/*      */           } 
/* 1199 */           tooltip.add(moveComponent);
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/* 1204 */       this.partyTooltipCache.put(Integer.valueOf(entry.slot()), new TooltipCacheEntry(key, now + 500L, tooltip));
/*      */     } 
/* 1206 */     context.method_51437(this.field_22793, tooltip, Optional.empty(), mx, my);
/*      */   }
/*      */   private void drawFloorTooltip(class_332 context, int mx, int my) {
/*      */     List<class_2561> tooltip;
/* 1210 */     List<BattleTowerNetwork.ActivePlayerData> players = getPlayersOnFloor(this.hoveredFloor);
/* 1211 */     if (players.isEmpty())
/* 1212 */       return;  long now = System.currentTimeMillis();
/* 1213 */     StringBuilder kb = (new StringBuilder()).append(this.hoveredFloor).append("|").append(players.size());
/* 1214 */     for (BattleTowerNetwork.ActivePlayerData p : players) kb.append("|").append(p.playerName()); 
/* 1215 */     String key = kb.toString();
/* 1216 */     TooltipCacheEntry cache = this.towerTooltipCache.get(Integer.valueOf(this.hoveredFloor));
/*      */     
/* 1218 */     if (cache != null && cache.expiresAt >= now && cache.key.equals(key)) { tooltip = cache.tooltip; }
/*      */     else
/* 1220 */     { tooltip = new ArrayList<>();
/* 1221 */       tooltip.add(TranslationHelper.translate("gui.tooltip.tower.floor_active", new Object[] { Integer.valueOf(this.hoveredFloor) }).method_27695(new class_124[] { class_124.field_1067, class_124.field_1065 }));
/* 1222 */       for (int i = 0; i < Math.min(players.size(), 10); ) { BattleTowerNetwork.ActivePlayerData pd = players.get(i); tooltip.add(class_2561.method_43470("  ⚔ " + pd.playerName()).method_27692(class_124.field_1080)); i++; }
/* 1223 */        if (players.size() > 10) tooltip.add(TranslationHelper.translate("gui.tooltip.tower.more_players", new Object[] { Integer.valueOf(players.size() - 10) }).method_27692(class_124.field_1063)); 
/* 1224 */       this.towerTooltipCache.put(Integer.valueOf(this.hoveredFloor), new TooltipCacheEntry(key, now + 250L, tooltip)); }
/*      */     
/* 1226 */     context.method_51437(this.field_22793, tooltip, Optional.empty(), mx, my);
/*      */   }
/*      */   
/*      */   private BattleTowerNetwork.PartyEntry getPartySlot(int slot)
/*      */   {
/* 1231 */     for (BattleTowerNetwork.PartyEntry e : this.partyData) { if (e.slot() == slot) return e;  }  return null;
/* 1232 */   } private List<BattleTowerNetwork.ActivePlayerData> getPlayersOnFloor(int floor) { List<BattleTowerNetwork.ActivePlayerData> r = new ArrayList<>(); for (BattleTowerNetwork.ActivePlayerData p : this.activePlayers) { if (p.floor() == floor) r.add(p);  }  return r; }
/* 1233 */   private boolean hasValidParty() { return !this.partyData.isEmpty(); } private ShopItem getShopItemById(String id) {
/* 1234 */     for (ShopItem i : this.shopItems) { if (i.id().equals(id)) return i;  }  return null;
/*      */   } private class_2561 getTranslatedSpeciesName(BattleTowerNetwork.PartyEntry p) {
/*      */     
/* 1237 */     try { Species s = getSpeciesById(class_2960.method_60654(p.speciesId())); if (s != null) return (class_2561)s.getTranslatedName();  } catch (Exception exception) {}
/* 1238 */     return (class_2561)class_2561.method_43470(p.speciesName());
/*      */   }
/*      */   private Species getSpeciesById(class_2960 id) { 
/* 1241 */     try { Method m = PokemonSpecies.class.getMethod("getByIdentifier", new Class[] { class_2960.class }); return Modifier.isStatic(m.getModifiers()) ? (Species)m.invoke(null, new Object[] { id }) : (Species)m.invoke(PokemonSpecies.INSTANCE, new Object[] { id }); } catch (Exception e) { return null; }
/* 1242 */      } private PokeBall getPokeBallById(class_2960 id) { try { Method m = PokeBalls.class.getMethod("getPokeBall", new Class[] { class_2960.class }); return Modifier.isStatic(m.getModifiers()) ? (PokeBall)m.invoke(null, new Object[] { id }) : (PokeBall)m.invoke(PokeBalls.INSTANCE, new Object[] { id }); } catch (Exception e) { return null; }
/*      */      }
/* 1244 */   private String capitalize(String str) { if (str == null || str.isEmpty()) return str;  StringBuilder sb = new StringBuilder(); for (String w : str.split(" ")) { if (!w.isEmpty()) sb.append(Character.toUpperCase(w.charAt(0))).append(w.substring(1).toLowerCase()).append(" ");  }  return sb.toString().trim(); } private void playClickSound() {
/* 1245 */     class_310.method_1551().method_1483().method_4873((class_1113)class_1109.method_47978((class_6880)class_3417.field_15015, 1.0F));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean method_25402(double mouseX, double mouseY, int button) {
/* 1252 */     if (this.showPopup && button == 0) {
/* 1253 */       if (this.popupOkHovered) {
/* 1254 */         playClickSound();
/* 1255 */         this.showPopup = false;
/* 1256 */         this.popupTitle = "";
/* 1257 */         this.popupLine1 = "";
/* 1258 */         this.popupLine2 = "";
/*      */       } 
/* 1260 */       return true;
/*      */     } 
/*      */     
/* 1263 */     if (button == 0) {
/* 1264 */       if (this.shopItems.size() > 7 && mouseX >= this.scrollbarX && mouseX < (this.scrollbarX + this.scrollbarWidth) && mouseY >= this.scrollbarThumbY && mouseY < (this.scrollbarThumbY + this.scrollbarThumbHeight)) { this.scrollbarDragging = true; this.scrollbarDragStartY = (int)mouseY; this.scrollbarDragStartOffset = this.shopScrollOffset; return true; }
/* 1265 */        if (this.leaderboardTabHovered) { playClickSound(); class_310.method_1551().method_1507(new LeaderboardScreen(this.bpBalance)); return true; }
/* 1266 */        if (this.lockInHovered && !this.partyLockedIn && hasValidParty()) { playClickSound(); BattleTowerNetwork.sendLockInTeam(); return true; }
/* 1267 */        if (this.unlockHovered && this.partyLockedIn && !this.runActive) { playClickSound(); BattleTowerNetwork.sendUnlockTeam(); return true; }
/* 1268 */        if (this.startBattleHovered && this.partyLockedIn) { playClickSound(); BattleTowerNetwork.sendStartBattleRequest(this.currentFloor); method_25419(); return true; }
/* 1269 */        if (this.forfeitHovered && this.runActive) { playClickSound(); BattleTowerNetwork.sendForfeitRun(); this.runActive = false; this.partyLockedIn = false; return true; }
/* 1270 */        if (this.closeHovered) { playClickSound(); method_25419(); return true; }
/* 1271 */        if (this.shopUpHovered && this.shopScrollOffset > 0) { playClickSound(); this.shopScrollOffset--; return true; }
/* 1272 */        if (this.shopDownHovered && this.shopScrollOffset + 7 < this.shopItems.size()) { playClickSound(); this.shopScrollOffset++; return true; }
/* 1273 */        if (this.purchaseCartHovered && !this.shoppingCart.isEmpty()) { playClickSound(); for (Iterator<Map.Entry<String, Integer>> iterator = this.shoppingCart.entrySet().iterator(); iterator.hasNext();) { for (e = iterator.next(), i = 0; i < ((Integer)e.getValue()).intValue(); ) { BattleTowerNetwork.sendPurchaseRequest(e.getKey()); i++; }  }  this.shoppingCart.clear(); return true; }
/* 1274 */        if (this.clearCartHovered && !this.shoppingCart.isEmpty()) { playClickSound(); this.shoppingCart.clear(); return true; }
/* 1275 */        if (this.hoveredShopIndex >= 0 && this.hoveredShopIndex < this.shopItems.size()) { ShopItem item = this.shopItems.get(this.hoveredShopIndex); playClickSound(); this.shoppingCart.put(item.id(), Integer.valueOf(((Integer)this.shoppingCart.getOrDefault(item.id(), Integer.valueOf(0))).intValue() + 1)); return true; }
/*      */     
/* 1277 */     }  if (button == 1 && this.hoveredShopIndex >= 0 && this.hoveredShopIndex < this.shopItems.size()) {
/* 1278 */       ShopItem item = this.shopItems.get(this.hoveredShopIndex); int q = ((Integer)this.shoppingCart.getOrDefault(item.id(), Integer.valueOf(0))).intValue();
/* 1279 */       if (q > 0) { playClickSound(); if (q > 1) { this.shoppingCart.put(item.id(), Integer.valueOf(q - 1)); } else { this.shoppingCart.remove(item.id()); }  return true; }
/*      */     
/* 1281 */     }  return super.method_25402(mouseX, mouseY, button);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean method_25401(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
/* 1286 */     int sx = this.guiLeft + 520 - 4 - 160, sy = this.guiTop + 52;
/* 1287 */     if (mouseX >= sx && mouseX < (sx + 160) && mouseY >= sy && mouseY < (sy + 360 - 52 - 36)) {
/* 1288 */       if (verticalAmount > 0.0D && this.shopScrollOffset > 0) { this.shopScrollOffset--; } else if (verticalAmount < 0.0D && this.shopScrollOffset + 7 < this.shopItems.size()) { this.shopScrollOffset++; }  return true;
/*      */     } 
/* 1290 */     return super.method_25401(mouseX, mouseY, horizontalAmount, verticalAmount);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean method_25403(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
/* 1295 */     if (this.scrollbarDragging && button == 0) { int d = (int)mouseY - this.scrollbarDragStartY, md = this.scrollbarTrackHeight - this.scrollbarThumbHeight, mo = this.shopItems.size() - 7; if (md > 0) this.shopScrollOffset = Math.max(0, Math.min(mo, this.scrollbarDragStartOffset + (int)(d / md * mo)));  return true; }
/* 1296 */      return super.method_25403(mouseX, mouseY, button, deltaX, deltaY);
/*      */   }
/*      */   
/*      */   public boolean method_25406(double mouseX, double mouseY, int button) {
/* 1300 */     if (button == 0 && this.scrollbarDragging) { this.scrollbarDragging = false; return true; }  return super.method_25406(mouseX, mouseY, button);
/*      */   }
/*      */   
/*      */   public void method_25420(class_332 context, int mouseX, int mouseY, float delta) {}
/*      */   
/*      */   public boolean method_25421() {
/* 1306 */     return false;
/*      */   }
/*      */   private static class TooltipCacheEntry { final String key; final long expiresAt;
/*      */     final List<class_2561> tooltip;
/*      */     
/*      */     TooltipCacheEntry(String key, long expiresAt, List<class_2561> tooltip) {
/* 1312 */       this.key = key; this.expiresAt = expiresAt; this.tooltip = tooltip;
/*      */     } }
/*      */ 
/*      */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\battletower\screen\BattleTowerScreen.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */