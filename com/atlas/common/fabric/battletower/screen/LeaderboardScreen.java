/*     */ package com.atlas.common.fabric.battletower.screen;
/*     */ import com.atlas.common.fabric.battletower.TranslationHelper;
/*     */ import com.atlas.common.fabric.battletower.network.BattleTowerNetwork;
/*     */ import com.cobblemon.mod.common.api.gui.GuiUtilsKt;
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import com.mojang.authlib.yggdrasil.ProfileResult;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.UUID;
/*     */ import java.util.concurrent.CompletableFuture;
/*     */ import java.util.concurrent.Executor;
/*     */ import net.minecraft.class_1113;
/*     */ import net.minecraft.class_2561;
/*     */ import net.minecraft.class_2960;
/*     */ import net.minecraft.class_310;
/*     */ import net.minecraft.class_332;
/*     */ import net.minecraft.class_437;
/*     */ import net.minecraft.class_640;
/*     */ import net.minecraft.class_6880;
/*     */ import net.minecraft.class_8685;
/*     */ 
/*     */ public class LeaderboardScreen extends class_437 {
/*     */   private static final String NS = "atlas-common-fabric";
/*     */   
/*     */   private static class_2960 tex(String path) {
/*  28 */     return class_2960.method_60655("atlas-common-fabric", "textures/battletower/" + path);
/*  29 */   } private static final class_2960 TEX_BG = tex("leaderboard.png");
/*     */ 
/*     */   
/*  32 */   private static final int PANEL_BG = color(14, 11, 8, 255);
/*  33 */   private static final int HEADER_BG = color(25, 16, 10, 255);
/*  34 */   private static final int SECTION_BG = color(18, 14, 10, 255);
/*  35 */   private static final int BORDER_COLOR = color(100, 50, 40, 255);
/*  36 */   private static final int BORDER_HIGHLIGHT = color(220, 100, 40, 255);
/*  37 */   private static final int ACCENT_RED = color(220, 50, 50, 255);
/*  38 */   private static final int ACCENT_CRIMSON = color(180, 35, 50, 255);
/*  39 */   private static final int ACCENT_ORANGE = color(255, 140, 40, 255);
/*  40 */   private static final int ACCENT_WARM = color(240, 100, 50, 255);
/*  41 */   private static final int ACCENT_CYAN = color(100, 160, 240, 255);
/*  42 */   private static final int ACCENT_GOLD = color(255, 200, 80, 255);
/*  43 */   private static final int ACCENT_GREEN = color(80, 255, 150, 255);
/*  44 */   private static final int TEXT_PRIMARY = color(240, 230, 230, 255);
/*  45 */   private static final int TEXT_SECONDARY = color(200, 180, 160, 255);
/*  46 */   private static final int TEXT_DIM = color(150, 120, 90, 255);
/*     */   
/*  48 */   private static final class_2960 DEFAULT_SKIN = class_2960.method_60655("minecraft", "textures/entity/player/wide/steve.png");
/*     */   
/*     */   private static final int GUI_WIDTH = 564;
/*     */   
/*     */   private static final int GUI_HEIGHT = 378;
/*     */   
/*     */   private static final int FACE_SIZE = 10;
/*     */   
/*     */   private static final int ENTRY_H = 13;
/*     */   private static final int LEADERBOARD_X = 68;
/*     */   private static final int LEADERBOARD_Y = 65;
/*     */   private static final int LEADERBOARD_W = 251;
/*     */   private static final int LEADERBOARD_H = 262;
/*     */   private static final int REWARDS_X = 326;
/*     */   private static final int REWARDS_Y = 65;
/*     */   private static final int REWARDS_W = 168;
/*     */   private static final int REWARDS_H = 262;
/*     */   private int guiLeft;
/*     */   private int guiTop;
/*     */   private int bpBalance;
/*  68 */   private List<BattleTowerNetwork.LeaderboardEntryData> floorEntries = new ArrayList<>();
/*  69 */   private BattleTowerNetwork.LeaderboardEntryData playerFloorRank = null;
/*  70 */   private List<BattleTowerNetwork.SeasonRewardData> seasonRewards = new ArrayList<>();
/*     */   
/*     */   private boolean towerTabHovered = false;
/*     */   
/*     */   private boolean leaderboardTabHovered = false;
/*     */   
/*     */   private boolean closeHovered = false;
/*  77 */   private int scrollOffset = 0;
/*     */ 
/*     */   
/*  80 */   private final Map<UUID, class_2960> skinCache = new HashMap<>();
/*  81 */   private final Map<UUID, Boolean> skinFetchStarted = new HashMap<>();
/*     */   
/*     */   private static int color(int r, int g, int b, int a) {
/*  84 */     return a << 24 | r << 16 | g << 8 | b;
/*     */   }
/*     */   
/*     */   public LeaderboardScreen(int bpBalance) {
/*  88 */     super((class_2561)class_2561.method_43471("battletower.gui.tab.leaderboard"));
/*  89 */     this.bpBalance = bpBalance;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void method_25426() {
/*  94 */     super.method_25426();
/*  95 */     this.guiLeft = (this.field_22789 - 564) / 2;
/*  96 */     this.guiTop = (this.field_22790 - 378) / 2;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 103 */     BattleTowerNetwork.requestLeaderboard();
/*     */   }
/*     */   
/*     */   private void fillPlaceholderData() {
/* 107 */     UUID clientUUID = class_310.method_1551().method_1548().method_44717();
/* 108 */     if (clientUUID == null) clientUUID = UUID.randomUUID();
/*     */     
/* 110 */     String[] names = { "xStarfyre", "AshKetchum99", "RedChampion", "CynthiaFan", "StevenStone", "LeonUndefeated", "BlueOakRival", "N_Harmonia", "DianthaPkmn", "LanceEliteFour", "WallaceMaster", "IrisChamp", "AldorTheGreat", "GeranimoRush", "MistyWaterflwr", "BrockSolid", "SabrinaGhost", "ErikaGrass", "KogaPoison", "BlaineFireLord", "GiovanniKanto", "DragoniteFan", "PikachuLover", "MewtwoSeeker", "LugiaWings", "HoOhFlame", "RayquazaSky", "GroudonEarth", "KyogreDeep", "GarchompBite", "LucarioAura", "ZoroarkNight" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 120 */     this.floorEntries = new ArrayList<>();
/* 121 */     for (int i = 0; i < names.length; i++) {
/* 122 */       int floor = 50 - i;
/* 123 */       boolean isPlayer = (i == 15);
/* 124 */       UUID uuid = isPlayer ? clientUUID : UUID.randomUUID();
/* 125 */       String name = isPlayer ? class_310.method_1551().method_1548().method_1676() : names[i];
/* 126 */       this.floorEntries.add(new BattleTowerNetwork.LeaderboardEntryData(uuid, name, floor, i + 1, isPlayer));
/*     */     } 
/*     */     
/* 129 */     this
/* 130 */       .playerFloorRank = new BattleTowerNetwork.LeaderboardEntryData(clientUUID, class_310.method_1551().method_1548().method_1676(), 35, 16, true);
/*     */     
/* 132 */     this.seasonRewards = new ArrayList<>();
/* 133 */     this.seasonRewards.add(new BattleTowerNetwork.SeasonRewardData(1, "Exclusive Shiny + 5000 BP"));
/* 134 */     this.seasonRewards.add(new BattleTowerNetwork.SeasonRewardData(2, "Exclusive Shiny + 3000 BP"));
/* 135 */     this.seasonRewards.add(new BattleTowerNetwork.SeasonRewardData(3, "Exclusive Shiny + 2000 BP"));
/* 136 */     this.seasonRewards.add(new BattleTowerNetwork.SeasonRewardData(5, "1500 BP + Rare Candy x10"));
/* 137 */     this.seasonRewards.add(new BattleTowerNetwork.SeasonRewardData(10, "1000 BP + Master Ball"));
/* 138 */     this.seasonRewards.add(new BattleTowerNetwork.SeasonRewardData(20, "500 BP"));
/*     */   }
/*     */   
/*     */   public void updateLeaderboardData(BattleTowerNetwork.SyncLeaderboardPayload payload) {
/* 142 */     this.floorEntries = new ArrayList<>(payload.floorEntries());
/* 143 */     this.playerFloorRank = payload.playerFloorRank();
/* 144 */     this.seasonRewards = new ArrayList<>(payload.seasonRewards());
/* 145 */     this.skinCache.clear(); this.skinFetchStarted.clear();
/* 146 */     this.scrollOffset = 0;
/*     */   }
/*     */   
/*     */   public void setBpBalance(int bp) {
/* 150 */     this.bpBalance = bp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private class_2960 getSkinTexture(UUID uuid, String playerName) {
/* 159 */     class_2960 cached = this.skinCache.get(uuid);
/* 160 */     if (cached != null) return cached;
/*     */ 
/*     */     
/* 163 */     class_310 client = class_310.method_1551();
/* 164 */     if (client.method_1562() != null) {
/* 165 */       class_640 entry = client.method_1562().method_2871(uuid);
/* 166 */       if (entry != null) {
/* 167 */         class_2960 tex = entry.method_52810().comp_1626();
/* 168 */         if (tex != null) {
/* 169 */           this.skinCache.put(uuid, tex);
/* 170 */           return tex;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 176 */     if (!this.skinFetchStarted.containsKey(uuid)) {
/* 177 */       this.skinFetchStarted.put(uuid, Boolean.valueOf(true));
/* 178 */       CompletableFuture.supplyAsync(() -> {
/*     */             try {
/*     */               ProfileResult result = client.method_1495().fetchProfile(uuid, false);
/*     */               if (result != null) {
/*     */                 GameProfile fullProfile = result.profile();
/*     */                 class_8685 textures = client.method_1582().method_52862(fullProfile);
/*     */                 if (textures != null && textures.comp_1626() != null) {
/*     */                   return textures.comp_1626();
/*     */                 }
/*     */               } 
/* 188 */             } catch (Throwable throwable) {}
/*     */             return null;
/* 190 */           }).thenAcceptAsync(tex -> {
/*     */             if (tex != null) {
/*     */               this.skinCache.put(uuid, tex);
/*     */             }
/*     */           }(Executor)client);
/*     */     } 
/*     */     
/* 197 */     return DEFAULT_SKIN;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void method_25394(class_332 ctx, int mouseX, int mouseY, float delta) {
/* 206 */     ctx.method_25294(0, 0, this.field_22789, this.field_22790, color(0, 0, 0, 180));
/* 207 */     drawPanel(ctx, this.guiLeft, this.guiTop, 564, 378);
/*     */     
/* 209 */     this.towerTabHovered = this.leaderboardTabHovered = this.closeHovered = false;
/*     */     
/* 211 */     drawHeader(ctx, mouseX, mouseY);
/* 212 */     drawLeaderboardColumn(ctx, mouseX, mouseY);
/* 213 */     drawSeasonRewards(ctx, mouseX, mouseY);
/*     */     
/* 215 */     super.method_25394(ctx, mouseX, mouseY, delta);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawPanel(class_332 ctx, int x, int y, int w, int h) {
/* 221 */     drawTex(ctx, TEX_BG, x, y, w, h);
/*     */   }
/*     */   
/*     */   private void drawTex(class_332 ctx, class_2960 texture, int x, int y, int w, int h) {
/* 225 */     GuiUtilsKt.blitk(ctx.method_51448(), texture, Integer.valueOf(x), Integer.valueOf(y), Integer.valueOf(h), Integer.valueOf(w), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(w), Integer.valueOf(h), Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(1), Float.valueOf(1.0F), true, 1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawHeader(class_332 ctx, int mouseX, int mouseY) {
/* 231 */     int towerTabX = this.guiLeft + 67, towerTabY = this.guiTop + 30, towerTabW = 78, towerTabH = 20;
/* 232 */     int lbTabX = this.guiLeft + 149, lbTabY = towerTabY, lbTabW = 80, lbTabH = towerTabH;
/* 233 */     this.towerTabHovered = (mouseX >= towerTabX && mouseX < towerTabX + towerTabW && mouseY >= towerTabY && mouseY < towerTabY + towerTabH);
/* 234 */     this.leaderboardTabHovered = (mouseX >= lbTabX && mouseX < lbTabX + lbTabW && mouseY >= lbTabY && mouseY < lbTabY + lbTabH);
/* 235 */     this.closeHovered = (mouseX >= this.guiLeft + 477 && mouseX < this.guiLeft + 500 && mouseY >= this.guiTop + 29 && mouseY < this.guiTop + 52);
/*     */     
/* 237 */     if (this.towerTabHovered) ctx.method_49601(towerTabX, towerTabY, towerTabW, towerTabH, BORDER_HIGHLIGHT); 
/* 238 */     if (this.leaderboardTabHovered) ctx.method_49601(lbTabX, lbTabY, lbTabW, lbTabH, BORDER_HIGHLIGHT); 
/* 239 */     if (this.closeHovered) ctx.method_49601(this.guiLeft + 477, this.guiTop + 29, 23, 23, ACCENT_RED);
/*     */     
/* 241 */     String bpText = "" + this.bpBalance + " BP";
/* 242 */     int bpX = this.guiLeft + 238;
/* 243 */     int bpY = this.guiTop + 338;
/* 244 */     ctx.method_25294(bpX + 1, bpY + 1, bpX + 84, bpY + 13, color(15, 15, 15, 180));
/* 245 */     ctx.method_25300(this.field_22793, bpText, bpX + 42, bpY + 3, ACCENT_GOLD);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawLeaderboardColumn(class_332 ctx, int mouseX, int mouseY) {
/* 251 */     int columnX = this.guiLeft + 68;
/* 252 */     int columnY = this.guiTop + 65;
/* 253 */     int columnW = 251;
/* 254 */     int columnH = 262;
/*     */ 
/*     */     
/* 257 */     int listTop = columnY + 23;
/* 258 */     int listBottom = columnY + columnH - 5;
/*     */     
/* 260 */     boolean showPlayerRankBar = (this.playerFloorRank != null && !isPlayerInVisibleRange());
/* 261 */     if (showPlayerRankBar) {
/* 262 */       listBottom -= 16;
/*     */     }
/* 264 */     int visibleH = listBottom - listTop;
/* 265 */     int maxVisible = visibleH / 13;
/*     */     
/* 267 */     if (this.floorEntries.isEmpty()) {
/* 268 */       String noEntries = TranslationHelper.translate("gui.leaderboard.no_entries", new Object[0]).getString();
/* 269 */       ctx.method_25300(this.field_22793, noEntries, columnX + columnW / 2, columnY + columnH / 2 - 4, TEXT_DIM);
/*     */     } else {
/*     */       
/* 272 */       int maxScroll = Math.max(0, this.floorEntries.size() - maxVisible);
/* 273 */       if (this.scrollOffset > maxScroll) this.scrollOffset = maxScroll; 
/* 274 */       if (this.scrollOffset < 0) this.scrollOffset = 0;
/*     */ 
/*     */       
/* 277 */       ctx.method_44379(columnX + 2, listTop, columnX + columnW - 2, listBottom);
/*     */       
/* 279 */       for (int i = 0; i < maxVisible && i + this.scrollOffset < this.floorEntries.size(); i++) {
/* 280 */         BattleTowerNetwork.LeaderboardEntryData entry = this.floorEntries.get(i + this.scrollOffset);
/* 281 */         int cy = listTop + i * 13;
/*     */ 
/*     */         
/* 284 */         if (entry.isCurrentPlayer()) {
/* 285 */           ctx.method_25294(columnX + 2, cy - 1, columnX + columnW - 2, cy + 13 - 1, color(220, 100, 40, 40));
/*     */         }
/*     */ 
/*     */         
/* 289 */         String rankText = "#" + entry.rank();
/* 290 */         int rankReservedW = this.field_22793.method_1727("#99");
/* 291 */         int rankColor = getRankColor(entry.rank());
/* 292 */         ctx.method_51433(this.field_22793, rankText, columnX + 4 + rankReservedW - this.field_22793.method_1727(rankText), cy + 1, rankColor, false);
/*     */ 
/*     */         
/* 295 */         int faceX = columnX + rankReservedW + 8;
/* 296 */         int faceY = cy;
/* 297 */         class_2960 skinTex = getSkinTexture(entry.playerUUID(), entry.playerName());
/* 298 */         drawPlayerFace(ctx, skinTex, faceX, faceY, 10);
/*     */ 
/*     */         
/* 301 */         String name = entry.playerName();
/* 302 */         int nameStartX = faceX + 10 + 3;
/* 303 */         int maxNameWidth = columnX + columnW - nameStartX - 50;
/* 304 */         if (this.field_22793.method_1727(name) > maxNameWidth) {
/* 305 */           while (this.field_22793.method_1727(name + "...") > maxNameWidth && name.length() > 1)
/* 306 */             name = name.substring(0, name.length() - 1); 
/* 307 */           name = name + "...";
/*     */         } 
/* 309 */         int nameColor = entry.isCurrentPlayer() ? ACCENT_CYAN : TEXT_PRIMARY;
/* 310 */         ctx.method_51433(this.field_22793, name, nameStartX, cy + 1, nameColor, false);
/*     */ 
/*     */         
/* 313 */         String valueText = "Floor " + entry.value();
/* 314 */         int valueX = columnX + columnW - this.field_22793.method_1727(valueText) - 6;
/* 315 */         ctx.method_51433(this.field_22793, valueText, valueX, cy + 1, TEXT_SECONDARY, false);
/*     */       } 
/*     */       
/* 318 */       ctx.method_44380();
/*     */ 
/*     */       
/* 321 */       if (this.floorEntries.size() > maxVisible) {
/* 322 */         int scrollTrackX = columnX + columnW - 5;
/* 323 */         int scrollTrackY = listTop;
/* 324 */         int scrollTrackH = visibleH;
/* 325 */         ctx.method_25294(scrollTrackX, scrollTrackY, scrollTrackX + 3, scrollTrackY + scrollTrackH, color(40, 30, 20, 180));
/*     */         
/* 327 */         float scrollRatio = this.scrollOffset / maxScroll;
/* 328 */         int thumbH = Math.max(10, scrollTrackH * maxVisible / this.floorEntries.size());
/* 329 */         int thumbY = scrollTrackY + (int)((scrollTrackH - thumbH) * scrollRatio);
/* 330 */         ctx.method_25294(scrollTrackX, thumbY, scrollTrackX + 3, thumbY + thumbH, ACCENT_ORANGE);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 335 */     if (showPlayerRankBar) {
/* 336 */       int playerY = listBottom + 2;
/* 337 */       ctx.method_25294(columnX + 2, playerY - 2, columnX + columnW - 2, playerY - 1, BORDER_COLOR);
/*     */ 
/*     */       
/* 340 */       class_2960 playerSkin = getSkinTexture(this.playerFloorRank.playerUUID(), this.playerFloorRank.playerName());
/* 341 */       drawPlayerFace(ctx, playerSkin, columnX + 6, playerY, 8);
/*     */       
/* 343 */       String yourRank = TranslationHelper.translate("gui.leaderboard.your_rank", new Object[] { Integer.valueOf(this.playerFloorRank.rank()), Integer.valueOf(this.playerFloorRank.value()) }).getString();
/* 344 */       ctx.method_51433(this.field_22793, yourRank, columnX + 18, playerY + 1, ACCENT_CYAN, true);
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean isPlayerInVisibleRange() {
/* 349 */     if (this.playerFloorRank == null) return true; 
/* 350 */     int safeOffset = Math.max(0, this.scrollOffset);
/* 351 */     for (int i = safeOffset; i < this.floorEntries.size(); i++) {
/* 352 */       if (((BattleTowerNetwork.LeaderboardEntryData)this.floorEntries.get(i)).isCurrentPlayer()) return true; 
/*     */     } 
/* 354 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawSeasonRewards(class_332 ctx, int mouseX, int mouseY) {
/* 360 */     int rewardX = this.guiLeft + 326;
/* 361 */     int rewardY = this.guiTop + 65;
/* 362 */     int rewardW = 168;
/* 363 */     int rewardH = 262;
/*     */ 
/*     */     
/* 366 */     int drawY = rewardY + 23;
/* 367 */     int maxDrawY = rewardY + rewardH - 5;
/* 368 */     int contentW = rewardW - 12;
/*     */     
/* 370 */     if (this.seasonRewards.isEmpty()) {
/* 371 */       ctx.method_25300(this.field_22793, "TBD", rewardX + rewardW / 2, rewardY + rewardH / 2, TEXT_DIM);
/*     */     } else {
/* 373 */       for (int i = 0; i < this.seasonRewards.size(); i++) {
/* 374 */         String rankLabel; BattleTowerNetwork.SeasonRewardData reward = this.seasonRewards.get(i);
/* 375 */         if (drawY + 10 > maxDrawY) {
/*     */           break;
/*     */         }
/*     */         
/* 379 */         if (reward.rank() <= 3) {
/* 380 */           rankLabel = getRankEmoji(reward.rank()) + " #" + getRankEmoji(reward.rank());
/*     */         } else {
/* 382 */           rankLabel = "Top " + reward.rank();
/*     */         } 
/* 384 */         ctx.method_51433(this.field_22793, rankLabel, rewardX + 6, drawY, getRankColor(reward.rank()), true);
/* 385 */         drawY += 11;
/*     */ 
/*     */         
/* 388 */         String remaining = reward.description();
/* 389 */         while (!remaining.isEmpty() && drawY + 8 <= maxDrawY) {
/* 390 */           String line = remaining;
/* 391 */           if (this.field_22793.method_1727(line) > contentW) {
/*     */             
/* 393 */             int cutoff = line.length();
/* 394 */             while (cutoff > 0 && this.field_22793.method_1727(line.substring(0, cutoff)) > contentW) {
/* 395 */               cutoff--;
/*     */             }
/*     */             
/* 398 */             int space = line.lastIndexOf(' ', cutoff);
/* 399 */             if (space > 0) {
/* 400 */               cutoff = space;
/*     */             }
/* 402 */             line = remaining.substring(0, cutoff);
/* 403 */             remaining = remaining.substring(cutoff).stripLeading();
/*     */           } else {
/* 405 */             remaining = "";
/*     */           } 
/* 407 */           ctx.method_51433(this.field_22793, line, rewardX + 6, drawY, TEXT_SECONDARY, false);
/* 408 */           drawY += 10;
/*     */         } 
/*     */         
/* 411 */         drawY += 4;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawTabButton(class_332 ctx, int x, int y, int w, int h, String text, boolean active, boolean hov, boolean isTower) {
/* 419 */     ctx.method_25294(x, y, x + w, y + h, color(25, 15, 12, 255));
/* 420 */     ctx.method_49601(x, y, w, h, active ? ACCENT_ORANGE : (hov ? BORDER_HIGHLIGHT : BORDER_COLOR));
/* 421 */     if (active) ctx.method_25294(x + 1, y + h - 2, x + w - 1, y + h, ACCENT_ORANGE); 
/* 422 */     int tw = this.field_22793.method_1727(text), tx = x + (w - tw) / 2, ty = y + (h - 8) / 2;
/* 423 */     int cx = tx;
/* 424 */     for (int i = 0; i < text.length(); i++) {
/* 425 */       int r, g, b; float p = i / Math.max(1, text.length() - 1);
/*     */       
/* 427 */       if (isTower) { r = (int)(255.0F - p * 35.0F); g = (int)(160.0F - p * 120.0F); b = (int)(40.0F - p * 10.0F); }
/* 428 */       else { r = (int)(220.0F + p * 35.0F); g = (int)(150.0F + p * 50.0F); b = (int)(50.0F + p * 30.0F); }
/* 429 */        if (!active && !hov) { r /= 2; g /= 2; b /= 2; }
/* 430 */        ctx.method_51433(this.field_22793, String.valueOf(text.charAt(i)), cx, ty, color(r, g, b, 255), true);
/* 431 */       cx += this.field_22793.method_1727(String.valueOf(text.charAt(i)));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean drawButton(class_332 ctx, int x, int y, int w, int h, String text, int mx, int my, boolean en) {
/* 438 */     boolean hov = (en && mx >= x && mx < x + w && my >= y && my < y + h);
/* 439 */     int bg = !en ? BattleTowerStyledButton.getButtonDisabled() : (hov ? BattleTowerStyledButton.getButtonHover() : BattleTowerStyledButton.getButtonBg());
/* 440 */     ctx.method_25294(x + 1, y + 1, x + w - 1, y + h - 1, bg);
/* 441 */     if (en) ctx.method_25294(x + 1, y + h - 3, x + w - 1, y + h - 1, BattleTowerStyledButton.darken(bg, 15)); 
/* 442 */     int bc = hov ? BattleTowerStyledButton.getButtonBorderHover() : BattleTowerStyledButton.getButtonBorder();
/* 443 */     ctx.method_25294(x + 1, y, x + w - 1, y + 1, bc);
/* 444 */     ctx.method_25294(x + 1, y + h - 1, x + w - 1, y + h, BattleTowerStyledButton.darken(bc, 30));
/* 445 */     ctx.method_25294(x, y + 1, x + 1, y + h - 1, bc);
/* 446 */     ctx.method_25294(x + w - 1, y + 1, x + w, y + h - 1, BattleTowerStyledButton.darken(bc, 30));
/* 447 */     if (en) {
/* 448 */       ctx.method_25294(x + 2, y + 1, x + w - 2, y + 2, BattleTowerStyledButton.lighten(bg, 20));
/* 449 */       ctx.method_25294(x + 1, y + 2, x + 2, y + h - 3, BattleTowerStyledButton.lighten(bg, 10));
/*     */     } 
/* 451 */     ctx.method_25294(x, y, x + 1, y + 1, 0);
/* 452 */     ctx.method_25294(x + w - 1, y, x + w, y + 1, 0);
/* 453 */     ctx.method_25294(x, y + h - 1, x + 1, y + h, 0);
/* 454 */     ctx.method_25294(x + w - 1, y + h - 1, x + w, y + h, 0);
/* 455 */     ctx.method_25300(this.field_22793, text, x + w / 2, y + (h - 8) / 2, 
/* 456 */         en ? BattleTowerStyledButton.getTextPrimary() : BattleTowerStyledButton.getTextDim());
/* 457 */     return hov;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawPlayerFace(class_332 ctx, class_2960 skinTexture, int x, int y, int size) {
/* 465 */     ctx.method_25293(skinTexture, x, y, size, size, 8.0F, 8.0F, 8, 8, 64, 64);
/* 466 */     ctx.method_25293(skinTexture, x, y, size, size, 40.0F, 8.0F, 8, 8, 64, 64);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private int getRankColor(int rank) {
/* 472 */     switch (rank) { case 1: case 2: case 3:  }  return 
/*     */ 
/*     */ 
/*     */       
/* 476 */       TEXT_DIM;
/*     */   }
/*     */ 
/*     */   
/*     */   private String getRankEmoji(int rank) {
/* 481 */     switch (rank) { case 1: case 2: case 3:  }  return 
/*     */ 
/*     */ 
/*     */       
/* 485 */       "";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean method_25402(double mouseX, double mouseY, int button) {
/* 493 */     if (button == 0) {
/* 494 */       if (this.towerTabHovered) {
/* 495 */         playClickSound();
/* 496 */         class_310.method_1551().method_1507(new BattleTowerScreen());
/* 497 */         return true;
/*     */       } 
/*     */       
/* 500 */       if (this.closeHovered) {
/* 501 */         playClickSound();
/* 502 */         method_25419();
/* 503 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/* 507 */     return super.method_25402(mouseX, mouseY, button);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean method_25401(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
/* 513 */     int columnX = this.guiLeft + 68;
/* 514 */     int columnY = this.guiTop + 65;
/* 515 */     int columnW = 251;
/* 516 */     int columnH = 262;
/*     */     
/* 518 */     if (mouseX >= columnX && mouseX < (columnX + columnW) && mouseY >= columnY && mouseY < (columnY + columnH)) {
/*     */       
/* 520 */       this.scrollOffset -= (int)verticalAmount * 3;
/*     */ 
/*     */       
/* 523 */       int visibleH = columnH - 21 - 4;
/* 524 */       if (this.playerFloorRank != null && !isPlayerInVisibleRange()) {
/* 525 */         visibleH -= 16;
/*     */       }
/* 527 */       int maxVisible = visibleH / 13;
/* 528 */       int maxScroll = Math.max(0, this.floorEntries.size() - maxVisible);
/* 529 */       this.scrollOffset = Math.max(0, Math.min(this.scrollOffset, maxScroll));
/* 530 */       return true;
/*     */     } 
/*     */     
/* 533 */     return super.method_25401(mouseX, mouseY, horizontalAmount, verticalAmount);
/*     */   }
/*     */   
/*     */   private void playClickSound() {
/* 537 */     class_310.method_1551().method_1483().method_4873((class_1113)class_1109.method_47978((class_6880)class_3417.field_15015, 1.0F));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void method_25420(class_332 context, int mouseX, int mouseY, float delta) {}
/*     */ 
/*     */   
/*     */   public boolean method_25421() {
/* 546 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\battletower\screen\LeaderboardScreen.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */