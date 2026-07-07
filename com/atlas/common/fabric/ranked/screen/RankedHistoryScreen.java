/*     */ package com.atlas.common.fabric.ranked.screen;
/*     */ 
/*     */ import com.atlas.common.fabric.ranked.data.PlayerRankedData;
/*     */ import com.atlas.common.fabric.ranked.data.RankedRank;
/*     */ import com.atlas.common.fabric.ranked.network.RankedNetwork;
/*     */ import com.atlas.common.fabric.screen.PokemonRenderHelper;
/*     */ import com.cobblemon.mod.common.client.render.models.blockbench.FloatingState;
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import com.mojang.authlib.yggdrasil.ProfileResult;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
/*     */ import java.util.concurrent.CompletableFuture;
/*     */ import java.util.concurrent.Executor;
/*     */ import net.minecraft.class_1109;
/*     */ import net.minecraft.class_1113;
/*     */ import net.minecraft.class_2561;
/*     */ import net.minecraft.class_2960;
/*     */ import net.minecraft.class_310;
/*     */ import net.minecraft.class_332;
/*     */ import net.minecraft.class_3417;
/*     */ import net.minecraft.class_437;
/*     */ import net.minecraft.class_4587;
/*     */ import net.minecraft.class_640;
/*     */ import net.minecraft.class_6880;
/*     */ import net.minecraft.class_8685;
/*     */ import org.joml.Quaternionf;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RankedHistoryScreen
/*     */   extends class_437
/*     */ {
/*  39 */   private static final int PANEL_BG = c(10, 12, 18, 255);
/*  40 */   private static final int SECTION_BG = c(14, 16, 24, 255);
/*  41 */   private static final int BORDER_COLOR = c(50, 60, 100, 255);
/*  42 */   private static final int BORDER_HIGHLIGHT = c(80, 140, 220, 255);
/*  43 */   private static final int ACCENT_ORANGE = c(80, 200, 220, 255);
/*  44 */   private static final int ACCENT_GREEN = c(80, 255, 150, 255);
/*  45 */   private static final int ACCENT_RED = c(220, 50, 50, 255);
/*  46 */   private static final int ACCENT_GOLD = c(255, 200, 80, 255);
/*  47 */   private static final int TEXT_PRIMARY = c(220, 230, 245, 255);
/*  48 */   private static final int TEXT_SECONDARY = c(160, 180, 210, 255);
/*  49 */   private static final int TEXT_DIM = c(100, 120, 160, 255);
/*     */   
/*  51 */   private static final int WIN_ACCENT = c(50, 200, 100, 200);
/*  52 */   private static final int LOSS_ACCENT = c(200, 50, 50, 200);
/*  53 */   private static final int WIN_BG = c(30, 80, 50, 40);
/*  54 */   private static final int LOSS_BG = c(80, 30, 30, 40);
/*  55 */   private static final int ROW_HOVER_BG = c(80, 200, 220, 25);
/*     */ 
/*     */   
/*  58 */   private static final class_2960 DEFAULT_SKIN = class_2960.method_60655("minecraft", "textures/entity/player/wide/steve.png");
/*     */   
/*     */   private static final int GUI_WIDTH = 390;
/*     */   
/*     */   private static final int GUI_HEIGHT = 300;
/*     */   
/*     */   private static final int ENTRY_HEIGHT = 34;
/*     */   
/*     */   private int guiLeft;
/*     */   private int guiTop;
/*     */   private boolean closeHovered;
/*     */   private boolean backHovered;
/*  70 */   private int hoveredEntryIndex = -1;
/*     */ 
/*     */   
/*  73 */   private String tooltipText = null;
/*     */   
/*     */   private int tooltipX;
/*     */   private int tooltipY;
/*  77 */   private int scrollOffset = 0;
/*     */ 
/*     */   
/*  80 */   private final Map<UUID, class_2960> skinCache = new HashMap<>();
/*  81 */   private final Map<UUID, Boolean> skinFetchStarted = new HashMap<>();
/*     */ 
/*     */   
/*  84 */   private final Map<Integer, FloatingState> pokemonStates = new HashMap<>(); private static int c(int r, int g, int b, int a) {
/*  85 */     return a << 24 | r << 16 | g << 8 | b;
/*     */   }
/*     */   public RankedHistoryScreen() {
/*  88 */     super((class_2561)class_2561.method_43471("screen.cobblemon_ranked.history"));
/*     */     
/*  90 */     for (int i = 0; i < 120; ) { this.pokemonStates.put(Integer.valueOf(i), new FloatingState()); i++; }
/*  91 */      PokemonRenderHelper.init();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void method_25426() {
/*  96 */     super.method_25426();
/*  97 */     this.guiLeft = (this.field_22789 - 390) / 2;
/*  98 */     this.guiTop = (this.field_22790 - 300) / 2;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawPlayerFace(class_332 ctx, class_2960 skinTexture, int x, int y, int size) {
/* 104 */     ctx.method_25293(skinTexture, x, y, size, size, 8.0F, 8.0F, 8, 8, 64, 64);
/* 105 */     ctx.method_25293(skinTexture, x, y, size, size, 40.0F, 8.0F, 8, 8, 64, 64);
/*     */   }
/*     */ 
/*     */   
/*     */   private class_2960 getSkinTexture(UUID uuid, String playerName) {
/* 110 */     class_2960 cached = this.skinCache.get(uuid);
/* 111 */     if (cached != null) return cached;
/*     */ 
/*     */     
/* 114 */     class_310 client = class_310.method_1551();
/* 115 */     if (client.method_1562() != null) {
/* 116 */       class_640 entry = client.method_1562().method_2871(uuid);
/* 117 */       if (entry != null) {
/* 118 */         class_2960 tex = entry.method_52810().comp_1626();
/* 119 */         if (tex != null) {
/* 120 */           this.skinCache.put(uuid, tex);
/* 121 */           return tex;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 127 */     if (!this.skinFetchStarted.containsKey(uuid)) {
/* 128 */       this.skinFetchStarted.put(uuid, Boolean.valueOf(true));
/* 129 */       CompletableFuture.supplyAsync(() -> {
/*     */             try {
/*     */               ProfileResult result = client.method_1495().fetchProfile(uuid, false);
/*     */               if (result != null) {
/*     */                 GameProfile fullProfile = result.profile();
/*     */                 class_8685 textures = client.method_1582().method_52862(fullProfile);
/*     */                 if (textures != null && textures.comp_1626() != null) {
/*     */                   return textures.comp_1626();
/*     */                 }
/*     */               } 
/* 139 */             } catch (Throwable throwable) {}
/*     */             return null;
/* 141 */           }).thenAcceptAsync(tex -> {
/*     */             if (tex != null) {
/*     */               this.skinCache.put(uuid, tex);
/*     */             }
/*     */           }(Executor)client);
/*     */     } 
/*     */     
/* 148 */     return DEFAULT_SKIN;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void method_25394(class_332 ctx, int mouseX, int mouseY, float delta) {
/* 157 */     ctx.method_25294(0, 0, this.field_22789, this.field_22790, c(0, 0, 0, 180));
/* 158 */     drawPanel(ctx);
/*     */     
/* 160 */     this.closeHovered = this.backHovered = false;
/* 161 */     this.hoveredEntryIndex = -1;
/* 162 */     this.tooltipText = null;
/*     */     
/* 164 */     drawHeader(ctx, mouseX, mouseY);
/* 165 */     drawHistoryList(ctx, mouseX, mouseY, delta);
/* 166 */     drawBottomButtons(ctx, mouseX, mouseY);
/*     */     
/* 168 */     super.method_25394(ctx, mouseX, mouseY, delta);
/*     */ 
/*     */     
/* 171 */     if (this.tooltipText != null) {
/* 172 */       int tw = this.field_22793.method_1727(this.tooltipText) + 6;
/* 173 */       int th = 12;
/* 174 */       int tx = this.tooltipX + 8;
/* 175 */       int ty = this.tooltipY - 14;
/* 176 */       ctx.method_51448().method_22903();
/* 177 */       ctx.method_51448().method_46416(0.0F, 0.0F, 400.0F);
/* 178 */       ctx.method_25294(tx - 2, ty - 2, tx + tw + 2, ty + th + 2, c(10, 12, 20, 240));
/* 179 */       ctx.method_49601(tx - 2, ty - 2, tw + 4, th + 4, c(80, 140, 220, 200));
/* 180 */       ctx.method_51433(this.field_22793, this.tooltipText, tx + 2, ty + 2, TEXT_PRIMARY, true);
/* 181 */       ctx.method_51448().method_22909();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawPanel(class_332 ctx) {
/* 188 */     int x = this.guiLeft, y = this.guiTop, w = 390, h = 300;
/* 189 */     ctx.method_25294(x, y, x + w, y + h, PANEL_BG);
/*     */     
/* 191 */     for (int i = 0; i < w - 4; i++) {
/* 192 */       float p = i / (w - 4);
/* 193 */       int r = (int)(60.0F + p * 40.0F);
/* 194 */       int g = (int)(180.0F + p * 20.0F);
/* 195 */       int b = (int)(220.0F - p * 20.0F);
/* 196 */       ctx.method_25294(x + 2 + i, y, x + 3 + i, y + 2, c(r, g, b, 200));
/*     */     } 
/* 198 */     ctx.method_25294(x, y + h - 2, x + w, y + h, c(40, 50, 80, 255));
/* 199 */     ctx.method_25294(x, y, x + 2, y + h, BORDER_HIGHLIGHT);
/* 200 */     ctx.method_25294(x + w - 2, y, x + w, y + h, c(40, 50, 80, 255));
/*     */     
/* 202 */     ctx.method_25294(x + 2, y + 2, x + w - 2, y + 3, c(0, 0, 0, 100));
/* 203 */     ctx.method_25294(x + 2, y + 2, x + 3, y + h - 2, c(0, 0, 0, 100));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawHeader(class_332 ctx, int mouseX, int mouseY) {
/* 209 */     int headerY = this.guiTop + 2;
/* 210 */     int headerH = 20;
/*     */ 
/*     */     
/* 213 */     for (int row = 0; row < headerH; row++) {
/* 214 */       float p = row / headerH;
/* 215 */       ctx.method_25294(this.guiLeft + 2, headerY + row, this.guiLeft + 390 - 2, headerY + row + 1, 
/* 216 */           c(15, 18, 30, 200 + (int)(p * 55.0F)));
/*     */     } 
/*     */ 
/*     */     
/* 220 */     ctx.method_25300(this.field_22793, "§lHISTORY", this.guiLeft + 195, headerY + 6, ACCENT_ORANGE);
/*     */ 
/*     */     
/* 223 */     PlayerRankedData data = PlayerRankedData.getInstance();
/* 224 */     String trophyText = " " + data.getTrophies() + " Trophies";
/* 225 */     int trophyTextW = this.field_22793.method_1727(trophyText);
/* 226 */     int trophyIconSize = 9;
/* 227 */     int totalTrophyW = trophyIconSize + trophyTextW;
/* 228 */     int trophyX = this.guiLeft + 390 - totalTrophyW - 10;
/* 229 */     int trophyY = headerY + 6;
/* 230 */     RankedIcon.TROPHY.drawTexture(ctx, trophyX, trophyY - 1, trophyIconSize, trophyIconSize);
/* 231 */     ctx.method_51433(this.field_22793, trophyText, trophyX + trophyIconSize, trophyY, ACCENT_GOLD, true);
/*     */ 
/*     */     
/* 234 */     int sepY = headerY + headerH - 1;
/* 235 */     for (int i = 0; i < 378; i++) {
/* 236 */       float p = i / 378.0F;
/* 237 */       int r = (int)(60.0F + p * 20.0F), g = (int)(180.0F - p * 60.0F), b = (int)(220.0F - p * 20.0F);
/* 238 */       ctx.method_25294(this.guiLeft + 6 + i, sepY, this.guiLeft + 7 + i, sepY + 1, 
/* 239 */           c(r, g, b, 150 + (int)(50.0D * Math.sin(i * 0.1D))));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawHistoryList(class_332 ctx, int mouseX, int mouseY, float delta) {
/* 246 */     int listX = this.guiLeft + 6;
/* 247 */     int listY = this.guiTop + 24;
/* 248 */     int listW = 378;
/* 249 */     int listH = 248;
/*     */ 
/*     */     
/* 252 */     ctx.method_25294(listX, listY, listX + listW, listY + listH, SECTION_BG);
/* 253 */     for (int row = 0; row < listH; row++) {
/* 254 */       float p = row / listH;
/* 255 */       ctx.method_25294(listX + 1, listY + row, listX + listW - 1, listY + row + 1, 
/* 256 */           c(20, 30, 50, (int)(10.0F + p * 15.0F)));
/*     */     } 
/*     */ 
/*     */     
/* 260 */     ctx.method_25294(listX, listY, listX + listW, listY + 2, ACCENT_ORANGE);
/* 261 */     ctx.method_25294(listX, listY + listH - 2, listX + listW, listY + listH, RankedStyledButton.darken(ACCENT_ORANGE, 40));
/* 262 */     ctx.method_25294(listX, listY, listX + 2, listY + listH, ACCENT_ORANGE);
/* 263 */     ctx.method_25294(listX + listW - 2, listY, listX + listW, listY + listH, RankedStyledButton.darken(ACCENT_ORANGE, 40));
/* 264 */     ctx.method_49601(listX, listY, listW, listH, ACCENT_ORANGE);
/*     */ 
/*     */     
/* 267 */     ctx.method_25300(this.field_22793, "§lRecent Battles", listX + listW / 2, listY + 5, TEXT_PRIMARY);
/* 268 */     ctx.method_25294(listX + 4, listY + 17, listX + listW - 4, listY + 18, BORDER_COLOR);
/*     */     
/* 270 */     List<RankedNetwork.MatchHistoryPayload> history = PlayerRankedData.getInstance().getBattleHistory();
/*     */     
/* 272 */     int contentTop = listY + 20;
/* 273 */     int contentBottom = listY + listH - 4;
/* 274 */     int visibleH = contentBottom - contentTop;
/*     */     
/* 276 */     if (history.isEmpty()) {
/* 277 */       ctx.method_25300(this.field_22793, "No battles yet", listX + listW / 2, listY + listH / 2 - 4, TEXT_DIM);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 282 */     int maxVisible = visibleH / 34;
/* 283 */     int maxScroll = Math.max(0, history.size() - maxVisible);
/* 284 */     if (this.scrollOffset > maxScroll) this.scrollOffset = maxScroll; 
/* 285 */     if (this.scrollOffset < 0) this.scrollOffset = 0;
/*     */     
/* 287 */     ctx.method_44379(listX + 2, contentTop, listX + listW - 2, contentBottom);
/*     */     
/* 289 */     int cy = contentTop;
/* 290 */     for (int i = this.scrollOffset; i < history.size() && cy + 34 <= contentBottom; i++) {
/* 291 */       RankedNetwork.MatchHistoryPayload entry = history.get(i);
/* 292 */       int entryX = listX + 4;
/* 293 */       int entryW = listW - 8;
/* 294 */       int entryBottom = cy + 34;
/* 295 */       int entryCenterY = cy + 17;
/*     */       
/* 297 */       boolean isWin = entry.won();
/* 298 */       boolean rowHovered = (mouseX >= entryX && mouseX < entryX + entryW && mouseY >= cy && mouseY < entryBottom);
/*     */       
/* 300 */       if (rowHovered) this.hoveredEntryIndex = i;
/*     */ 
/*     */       
/* 303 */       ctx.method_25294(entryX, cy, entryX + entryW, entryBottom, isWin ? WIN_BG : LOSS_BG);
/* 304 */       if (rowHovered) {
/* 305 */         ctx.method_25294(entryX, cy, entryX + entryW, entryBottom, ROW_HOVER_BG);
/*     */       }
/*     */ 
/*     */       
/* 309 */       ctx.method_25294(entryX, cy, entryX + 3, entryBottom, isWin ? WIN_ACCENT : LOSS_ACCENT);
/*     */ 
/*     */       
/* 312 */       String badge = isWin ? "WIN" : "LOSS";
/* 313 */       int badgeColor = isWin ? ACCENT_GREEN : ACCENT_RED;
/* 314 */       int badgeBg = isWin ? c(40, 160, 80, 100) : c(160, 40, 40, 100);
/* 315 */       int badgeW = this.field_22793.method_1727(badge) + 8;
/* 316 */       int badgeH = 14;
/* 317 */       int badgeX = entryX + entryW - badgeW - 4;
/* 318 */       int badgeY = entryCenterY - badgeH / 2;
/* 319 */       ctx.method_25294(badgeX, badgeY, badgeX + badgeW, badgeY + badgeH, badgeBg);
/* 320 */       ctx.method_49601(badgeX, badgeY, badgeW, badgeH, badgeColor);
/* 321 */       ctx.method_25300(this.field_22793, "§l" + badge, badgeX + badgeW / 2, badgeY + 3, badgeColor);
/*     */ 
/*     */ 
/*     */       
/* 325 */       int infoRightEdge = badgeX - 6;
/* 326 */       String eloChange = ((entry.eloChange() >= 0) ? "+" : "") + ((entry.eloChange() >= 0) ? "+" : "");
/* 327 */       int eloChangeColor = (entry.eloChange() >= 0) ? ACCENT_GREEN : ACCENT_RED;
/* 328 */       int trophyChange = entry.trophyChange();
/* 329 */       int trophyIconSize = 9;
/* 330 */       String timeAgo = formatTimeAgo(entry.timestamp());
/*     */       
/* 332 */       if (trophyChange > 0) {
/*     */         
/* 334 */         String trophyText = " +" + trophyChange;
/* 335 */         int trophyTextW = this.field_22793.method_1727(trophyText);
/* 336 */         int eloW = this.field_22793.method_1727(eloChange);
/* 337 */         int lineW = eloW + 3 + trophyIconSize + trophyTextW;
/* 338 */         int lineX = infoRightEdge - lineW;
/* 339 */         int lineY = entryCenterY - 8;
/* 340 */         ctx.method_51433(this.field_22793, eloChange, lineX, lineY, eloChangeColor, true);
/* 341 */         RankedIcon.TROPHY.drawTexture(ctx, lineX + eloW + 3, lineY - 1, trophyIconSize, trophyIconSize);
/* 342 */         ctx.method_51433(this.field_22793, trophyText, lineX + eloW + 3 + trophyIconSize, lineY, ACCENT_GOLD, true);
/*     */       } else {
/*     */         
/* 345 */         int eloW = this.field_22793.method_1727(eloChange);
/* 346 */         ctx.method_51433(this.field_22793, eloChange, infoRightEdge - eloW, entryCenterY - 8, eloChangeColor, true);
/*     */       } 
/*     */       
/* 349 */       int timeAgoW = this.field_22793.method_1727(timeAgo);
/* 350 */       ctx.method_51433(this.field_22793, timeAgo, infoRightEdge - timeAgoW, entryCenterY + 3, TEXT_DIM, true);
/*     */ 
/*     */       
/* 353 */       int faceX = entryX + 6;
/* 354 */       int faceSize = 14;
/* 355 */       int faceY = entryCenterY - faceSize / 2;
/* 356 */       class_2960 skinTex = getSkinTexture(entry.opponentUUID(), entry.opponentName());
/* 357 */       drawPlayerFace(ctx, skinTex, faceX, faceY, faceSize);
/*     */ 
/*     */       
/* 360 */       int infoX = faceX + faceSize + 4;
/* 361 */       String name = entry.opponentName();
/* 362 */       int maxNameW = 80;
/* 363 */       if (this.field_22793.method_1727(name) > maxNameW) {
/* 364 */         while (this.field_22793.method_1727(name + "..") > maxNameW && name.length() > 1)
/* 365 */           name = name.substring(0, name.length() - 1); 
/* 366 */         name = name + "..";
/*     */       } 
/* 368 */       int textBlockH = 19;
/* 369 */       int textBlockTopY = entryCenterY - textBlockH / 2;
/* 370 */       ctx.method_51433(this.field_22793, "§l" + name, infoX, textBlockTopY, TEXT_PRIMARY, true);
/*     */       
/* 372 */       RankedRank oppRank = RankedRank.getRankForElo(entry.opponentElo());
/* 373 */       int rankColor = rankColorFromHex(oppRank.getIconColor());
/*     */       
/* 375 */       RankedIcon oppTag = RankedIcon.tagForRank(oppRank);
/* 376 */       if (oppTag != null) {
/* 377 */         oppTag.drawTexture(ctx, infoX, textBlockTopY + 11);
/*     */       } else {
/* 379 */         ctx.method_51433(this.field_22793, oppRank.getDisplayName(), infoX, textBlockTopY + 11, rankColor, true);
/*     */       } 
/*     */ 
/*     */       
/* 383 */       List<String> party = entry.opponentPartySpecies();
/* 384 */       if (party != null && !party.isEmpty()) {
/* 385 */         int cellSize = 22;
/* 386 */         int cellSpacing = 1;
/* 387 */         int totalGridW = party.size() * cellSize + (party.size() - 1) * cellSpacing;
/* 388 */         int leftBound = entryX + 110;
/* 389 */         int rightBound = infoRightEdge - 70;
/* 390 */         int gridX = leftBound + (rightBound - leftBound - totalGridW) / 2;
/* 391 */         int gridY = entryCenterY - cellSize / 2;
/*     */         
/* 393 */         for (int j = 0; j < 6 && j < party.size(); j++) {
/* 394 */           int cx = gridX + j * (cellSize + cellSpacing);
/* 395 */           String species = party.get(j);
/* 396 */           boolean cellHovered = (mouseX >= cx && mouseX < cx + cellSize && mouseY >= gridY && mouseY < gridY + cellSize);
/*     */ 
/*     */           
/* 399 */           ctx.method_25294(cx, gridY, cx + cellSize, gridY + cellSize, c(12, 15, 25, 200));
/* 400 */           ctx.method_25294(cx, gridY, cx + cellSize, gridY + 1, c(35, 45, 70, 200));
/* 401 */           if (cellHovered) {
/* 402 */             ctx.method_25294(cx, gridY, cx + cellSize, gridY + cellSize, c(60, 80, 120, 40));
/*     */           }
/*     */ 
/*     */           
/* 406 */           int stateIndex = i * 6 + j;
/* 407 */           renderPokemonInCell(ctx, species, cx, gridY, cellSize, stateIndex, delta);
/*     */ 
/*     */           
/* 410 */           if (cellHovered) {
/* 411 */             String label = species.contains(":") ? species.substring(species.indexOf(":") + 1) : species;
/* 412 */             if (!label.isEmpty()) label = label.substring(0, 1).toUpperCase() + label.substring(0, 1).toUpperCase(); 
/* 413 */             this.tooltipText = label;
/* 414 */             this.tooltipX = mouseX;
/* 415 */             this.tooltipY = mouseY;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 421 */       ctx.method_25294(entryX + 4, entryBottom - 1, entryX + entryW - 4, entryBottom, 
/* 422 */           c(40, 50, 80, 60));
/*     */       
/* 424 */       cy += 34;
/*     */     } 
/*     */     
/* 427 */     ctx.method_44380();
/*     */ 
/*     */     
/* 430 */     if (history.size() > maxVisible) {
/* 431 */       int scrollTrackX = listX + listW - 5;
/* 432 */       int scrollTrackH = visibleH;
/* 433 */       ctx.method_25294(scrollTrackX, contentTop, scrollTrackX + 3, contentTop + scrollTrackH, c(25, 30, 45, 180));
/* 434 */       int maxScrCalc = Math.max(1, history.size() - maxVisible);
/* 435 */       float scrollRatio = this.scrollOffset / maxScrCalc;
/* 436 */       int thumbH = Math.max(10, scrollTrackH * maxVisible / history.size());
/* 437 */       int thumbY = contentTop + (int)((scrollTrackH - thumbH) * scrollRatio);
/* 438 */       for (int j = 0; j < thumbH; j++) {
/* 439 */         float p = j / thumbH;
/* 440 */         int r = (int)(80.0F + p * 40.0F), g = (int)(200.0F - p * 40.0F), b = (int)(220.0F - p * 20.0F);
/* 441 */         ctx.method_25294(scrollTrackX, thumbY + j, scrollTrackX + 3, thumbY + j + 1, 
/* 442 */             c(r, g, b, 220));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderPokemonInCell(class_332 ctx, String speciesStr, int sx, int sy, int ss, int stateIndex, float delta) {
/* 450 */     if (speciesStr == null || speciesStr.isEmpty() || speciesStr.equals("Unknown")) {
/* 451 */       ctx.method_25300(this.field_22793, "?", sx + ss / 2, sy + ss / 2 - 4, TEXT_DIM);
/*     */       return;
/*     */     } 
/*     */     try {
/* 455 */       class_2960 speciesId = class_2960.method_60654(speciesStr);
/* 456 */       FloatingState state = this.pokemonStates.get(Integer.valueOf(stateIndex));
/* 457 */       if (state == null) {
/* 458 */         state = new FloatingState();
/* 459 */         this.pokemonStates.put(Integer.valueOf(stateIndex), state);
/*     */       } 
/* 461 */       state.setCurrentAspects(Set.of());
/* 462 */       class_4587 mat = ctx.method_51448();
/* 463 */       mat.method_22903();
/* 464 */       mat.method_22904(sx + ss / 2.0D, sy, 0.0D);
/* 465 */       mat.method_22905(2.5F, 2.5F, 1.0F);
/* 466 */       Quaternionf rot = new Quaternionf();
/* 467 */       rot.rotationXYZ((float)Math.toRadians(13.0D), (float)Math.toRadians(35.0D), 0.0F);
/* 468 */       if (PokemonRenderHelper.isAvailable()) {
/* 469 */         PokemonRenderHelper.draw(speciesId, mat, rot, state, delta);
/*     */       } else {
/* 471 */         drawFallback(ctx, speciesStr, sx, sy, ss);
/*     */       } 
/* 473 */       mat.method_22909();
/* 474 */     } catch (Exception e) {
/* 475 */       drawFallback(ctx, speciesStr, sx, sy, ss);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void drawFallback(class_332 ctx, String species, int x, int y, int s) {
/* 480 */     String label = species.contains(":") ? species.substring(species.indexOf(":") + 1) : species;
/* 481 */     String initial = label.isEmpty() ? "?" : label.substring(0, 1).toUpperCase();
/* 482 */     ctx.method_25300(this.field_22793, initial, x + s / 2, y + s / 2 - 4, TEXT_PRIMARY);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawBottomButtons(class_332 ctx, int mouseX, int mouseY) {
/* 488 */     int btnY = this.guiTop + 300 - 24;
/* 489 */     int btnH = 18;
/*     */     
/* 491 */     int backW = 50;
/* 492 */     this.backHovered = drawButton(ctx, this.guiLeft + 8, btnY, backW, btnH, "◀ Back", mouseX, mouseY, true);
/*     */     
/* 494 */     int closeW = 50;
/* 495 */     this.closeHovered = drawButton(ctx, this.guiLeft + 390 - closeW - 8, btnY, closeW, btnH, "Close", mouseX, mouseY, true);
/*     */ 
/*     */     
/* 498 */     PlayerRankedData data = PlayerRankedData.getInstance();
/* 499 */     String seasonText = (data.getSeason() <= 0) ? "Season BETA" : ("Season " + data.getSeason());
/* 500 */     ctx.method_25300(this.field_22793, seasonText, this.guiLeft + 195, btnY + 5, TEXT_DIM);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean drawButton(class_332 ctx, int x, int y, int w, int h, String text, int mx, int my, boolean en) {
/* 506 */     boolean hov = (en && mx >= x && mx < x + w && my >= y && my < y + h);
/* 507 */     int bg = !en ? RankedStyledButton.getButtonDisabled() : (hov ? RankedStyledButton.getButtonHover() : RankedStyledButton.getButtonBg());
/* 508 */     ctx.method_25294(x + 1, y + 1, x + w - 1, y + h - 1, bg);
/* 509 */     if (en) ctx.method_25294(x + 1, y + h - 3, x + w - 1, y + h - 1, RankedStyledButton.darken(bg, 15)); 
/* 510 */     int bc = hov ? RankedStyledButton.getButtonBorderHover() : RankedStyledButton.getButtonBorder();
/* 511 */     if (!en) bc = c(30, 35, 50, 255); 
/* 512 */     ctx.method_25294(x + 1, y, x + w - 1, y + 1, bc);
/* 513 */     ctx.method_25294(x + 1, y + h - 1, x + w - 1, y + h, RankedStyledButton.darken(bc, 30));
/* 514 */     ctx.method_25294(x, y + 1, x + 1, y + h - 1, bc);
/* 515 */     ctx.method_25294(x + w - 1, y + 1, x + w, y + h - 1, RankedStyledButton.darken(bc, 30));
/* 516 */     if (en) {
/* 517 */       ctx.method_25294(x + 2, y + 1, x + w - 2, y + 2, RankedStyledButton.lighten(bg, 20));
/* 518 */       ctx.method_25294(x + 1, y + 2, x + 2, y + h - 3, RankedStyledButton.lighten(bg, 10));
/*     */     } 
/* 520 */     ctx.method_25294(x, y, x + 1, y + 1, 0);
/* 521 */     ctx.method_25294(x + w - 1, y, x + w, y + 1, 0);
/* 522 */     ctx.method_25294(x, y + h - 1, x + 1, y + h, 0);
/* 523 */     ctx.method_25294(x + w - 1, y + h - 1, x + w, y + h, 0);
/* 524 */     ctx.method_25300(this.field_22793, text, x + w / 2, y + (h - 8) / 2, 
/* 525 */         en ? RankedStyledButton.getTextPrimary() : RankedStyledButton.getTextDim());
/* 526 */     return hov;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private String formatTimeAgo(long timestamp) {
/* 532 */     long diff = System.currentTimeMillis() - timestamp;
/* 533 */     if (diff < 0L) return "just now"; 
/* 534 */     long seconds = diff / 1000L;
/* 535 */     long minutes = seconds / 60L;
/* 536 */     long hours = minutes / 60L;
/* 537 */     long days = hours / 24L;
/* 538 */     if (days > 0L) return "" + days + "d ago"; 
/* 539 */     if (hours > 0L) return "" + hours + "h ago"; 
/* 540 */     if (minutes > 0L) return "" + minutes + "m ago"; 
/* 541 */     return "just now";
/*     */   }
/*     */   
/*     */   private static int rankColorFromHex(int hex) {
/* 545 */     int r = hex >> 16 & 0xFF;
/* 546 */     int g = hex >> 8 & 0xFF;
/* 547 */     int b = hex & 0xFF;
/* 548 */     return c(r, g, b, 255);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean method_25402(double mouseX, double mouseY, int button) {
/* 555 */     if (button == 0) {
/* 556 */       if (this.backHovered) {
/* 557 */         playClickSound();
/* 558 */         class_310.method_1551().method_1507(new RankedScreen());
/* 559 */         return true;
/*     */       } 
/* 561 */       if (this.closeHovered) {
/* 562 */         playClickSound();
/* 563 */         method_25419();
/* 564 */         return true;
/*     */       } 
/*     */     } 
/* 567 */     return super.method_25402(mouseX, mouseY, button);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean method_25401(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
/* 572 */     int listX = this.guiLeft + 6;
/* 573 */     int listY = this.guiTop + 24;
/* 574 */     int listW = 378;
/* 575 */     int listH = 248;
/*     */     
/* 577 */     if (mouseX >= listX && mouseX < (listX + listW) && mouseY >= listY && mouseY < (listY + listH)) {
/* 578 */       this.scrollOffset -= (int)verticalAmount * 2;
/* 579 */       List<RankedNetwork.MatchHistoryPayload> history = PlayerRankedData.getInstance().getBattleHistory();
/* 580 */       int visibleH = listH - 24;
/* 581 */       int maxVisible = visibleH / 34;
/* 582 */       int maxScroll = Math.max(0, history.size() - maxVisible);
/* 583 */       this.scrollOffset = Math.max(0, Math.min(this.scrollOffset, maxScroll));
/* 584 */       return true;
/*     */     } 
/* 586 */     return super.method_25401(mouseX, mouseY, horizontalAmount, verticalAmount);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean method_25404(int keyCode, int scanCode, int modifiers) {
/* 591 */     if (keyCode == 256) {
/* 592 */       method_25419();
/* 593 */       return true;
/*     */     } 
/* 595 */     return super.method_25404(keyCode, scanCode, modifiers);
/*     */   }
/*     */   
/*     */   private void playClickSound() {
/* 599 */     class_310.method_1551().method_1483().method_4873((class_1113)class_1109.method_47978((class_6880)class_3417.field_15015, 1.0F));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void method_25420(class_332 context, int mouseX, int mouseY, float delta) {}
/*     */ 
/*     */   
/*     */   public boolean method_25421() {
/* 608 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\ranked\screen\RankedHistoryScreen.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */