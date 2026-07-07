/*     */ package com.atlas.common.fabric.ranked.screen;
/*     */ 
/*     */ import com.atlas.common.fabric.ranked.data.PlayerRankedData;
/*     */ import com.atlas.common.fabric.ranked.network.RankedNetwork;
/*     */ import java.util.List;
/*     */ import net.minecraft.class_1109;
/*     */ import net.minecraft.class_1113;
/*     */ import net.minecraft.class_2561;
/*     */ import net.minecraft.class_310;
/*     */ import net.minecraft.class_332;
/*     */ import net.minecraft.class_3417;
/*     */ import net.minecraft.class_437;
/*     */ import net.minecraft.class_4587;
/*     */ import net.minecraft.class_6880;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RankedShopScreen
/*     */   extends class_437
/*     */ {
/*  25 */   private static final int PANEL_BG = RankedStyledButton.color(10, 12, 18, 255);
/*  26 */   private static final int SECTION_BG = RankedStyledButton.color(14, 16, 24, 255);
/*  27 */   private static final int BORDER_COLOR = RankedStyledButton.color(50, 60, 100, 255);
/*  28 */   private static final int BORDER_HIGHLIGHT = RankedStyledButton.color(80, 140, 220, 255);
/*  29 */   private static final int ACCENT_RED = RankedStyledButton.color(220, 50, 50, 255);
/*  30 */   private static final int ACCENT_ORANGE = RankedStyledButton.color(80, 200, 220, 255);
/*  31 */   private static final int ACCENT_GOLD = RankedStyledButton.color(255, 200, 80, 255);
/*  32 */   private static final int ACCENT_GREEN = RankedStyledButton.color(80, 255, 150, 255);
/*  33 */   private static final int ACCENT_PURPLE = RankedStyledButton.color(200, 140, 255, 255);
/*  34 */   private static final int TEXT_PRIMARY = RankedStyledButton.color(220, 230, 245, 255);
/*  35 */   private static final int TEXT_SECONDARY = RankedStyledButton.color(160, 180, 210, 255);
/*  36 */   private static final int TEXT_DIM = RankedStyledButton.color(100, 120, 160, 255);
/*  37 */   private static final int TEXT_SOLDOUT = RankedStyledButton.color(80, 80, 100, 255);
/*     */ 
/*     */   
/*  40 */   private static final int HOVER_GLOW_1 = RankedStyledButton.color(200, 140, 255, 20);
/*  41 */   private static final int HOVER_GLOW_2 = RankedStyledButton.color(200, 140, 255, 12);
/*     */   
/*     */   private static final int GUI_WIDTH = 310;
/*     */   
/*     */   private static final int GUI_HEIGHT = 256;
/*     */   
/*     */   private static final int ITEM_H = 28;
/*     */   
/*     */   private int guiLeft;
/*     */   
/*     */   private int guiTop;
/*     */   private boolean closeHovered;
/*     */   private boolean backHovered;
/*  54 */   private int hoveredItemIndex = -1;
/*  55 */   private int scrollOffset = 0;
/*     */   
/*     */   private boolean scrollbarDragging = false;
/*     */   
/*  59 */   private int scrollbarDragStartY = 0;
/*  60 */   private int scrollbarDragStartOffset = 0;
/*  61 */   private int scrollbarThumbY = 0;
/*  62 */   private int scrollbarThumbHeight = 0;
/*  63 */   private int scrollbarTrackY = 0;
/*  64 */   private int scrollbarTrackHeight = 0;
/*  65 */   private int scrollbarX = 0;
/*     */   
/*     */   private static final int SCROLLBAR_WIDTH = 8;
/*     */   
/*     */   private boolean showConfirm;
/*  70 */   private int confirmItemIndex = -1;
/*     */   private boolean confirmYesHovered;
/*     */   private boolean confirmNoHovered;
/*     */   
/*     */   public RankedShopScreen() {
/*  75 */     super((class_2561)class_2561.method_43471("screen.cobblemon_ranked.shop"));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void method_25426() {
/*  80 */     super.method_25426();
/*  81 */     this.guiLeft = (this.field_22789 - 310) / 2;
/*  82 */     this.guiTop = (this.field_22790 - 256) / 2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void method_25394(class_332 ctx, int mouseX, int mouseY, float delta) {
/*  91 */     ctx.method_25294(0, 0, this.field_22789, this.field_22790, RankedStyledButton.color(0, 0, 0, 180));
/*  92 */     drawPanel(ctx);
/*     */     
/*  94 */     this.closeHovered = this.backHovered = false;
/*  95 */     this.hoveredItemIndex = -1;
/*     */     
/*  97 */     PlayerRankedData data = PlayerRankedData.getInstance();
/*     */     
/*  99 */     drawHeader(ctx, data);
/* 100 */     if (data.isBetaSeason()) {
/* 101 */       drawBetaSeasonMessage(ctx);
/*     */     } else {
/* 103 */       drawShopItems(ctx, data, mouseX, mouseY);
/*     */     } 
/* 105 */     drawBottomButtons(ctx, mouseX, mouseY);
/*     */     
/* 107 */     super.method_25394(ctx, mouseX, mouseY, delta);
/*     */     
/* 109 */     if (this.showConfirm) drawConfirmOverlay(ctx, data, mouseX, mouseY);
/*     */   
/*     */   }
/*     */ 
/*     */   
/*     */   private void drawPanel(class_332 ctx) {
/* 115 */     int x = this.guiLeft, y = this.guiTop, w = 310, h = 256;
/* 116 */     ctx.method_25294(x, y, x + w, y + h, PANEL_BG);
/*     */     
/* 118 */     for (int i = 0; i < w - 4; i++) {
/* 119 */       float p = i / (w - 4);
/* 120 */       int r = (int)(160.0F + p * 40.0F);
/* 121 */       int g = (int)(100.0F + p * 60.0F);
/* 122 */       int b = (int)(255.0F - p * 30.0F);
/* 123 */       ctx.method_25294(x + 2 + i, y, x + 3 + i, y + 2, RankedStyledButton.color(r, g, b, 200));
/*     */     } 
/* 125 */     ctx.method_25294(x, y + h - 2, x + w, y + h, RankedStyledButton.color(40, 50, 80, 255));
/* 126 */     ctx.method_25294(x, y, x + 2, y + h, BORDER_HIGHLIGHT);
/* 127 */     ctx.method_25294(x + w - 2, y, x + w, y + h, RankedStyledButton.color(40, 50, 80, 255));
/*     */     
/* 129 */     ctx.method_25294(x + 2, y + 2, x + w - 2, y + 3, RankedStyledButton.color(0, 0, 0, 100));
/* 130 */     ctx.method_25294(x + 2, y + 2, x + 3, y + h - 2, RankedStyledButton.color(0, 0, 0, 100));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawHeader(class_332 ctx, PlayerRankedData data) {
/* 136 */     int headerY = this.guiTop + 2;
/* 137 */     int headerH = 20;
/*     */ 
/*     */     
/* 140 */     for (int row = 0; row < headerH; row++) {
/* 141 */       float p = row / headerH;
/* 142 */       ctx.method_25294(this.guiLeft + 2, headerY + row, this.guiLeft + 310 - 2, headerY + row + 1, 
/* 143 */           RankedStyledButton.color(15, 18, 30, 200 + (int)(p * 55.0F)));
/*     */     } 
/*     */ 
/*     */     
/* 147 */     ctx.method_25300(this.field_22793, "§lTROPHY SHOP", this.guiLeft + 155, headerY + 6, ACCENT_PURPLE);
/*     */ 
/*     */     
/* 150 */     String trophyText = " " + data.getTrophies() + " Trophies";
/* 151 */     int trophyTextW = this.field_22793.method_1727(trophyText);
/* 152 */     int trophyIconSize = 9;
/* 153 */     int totalTrophyW = trophyIconSize + trophyTextW;
/* 154 */     int trophyX = this.guiLeft + 310 - totalTrophyW - 10;
/* 155 */     int trophyY = headerY + 6;
/* 156 */     RankedIcon.TROPHY.drawTexture(ctx, trophyX, trophyY - 1, trophyIconSize, trophyIconSize);
/* 157 */     ctx.method_51433(this.field_22793, trophyText, trophyX + trophyIconSize, trophyY, ACCENT_GOLD, true);
/*     */ 
/*     */     
/* 160 */     int sepY = headerY + headerH - 1;
/* 161 */     for (int i = 0; i < 298; i++) {
/* 162 */       float p = i / 298.0F;
/* 163 */       int r = (int)(160.0F + p * 40.0F), g = (int)(100.0F - p * 30.0F), b = (int)(255.0F - p * 30.0F);
/* 164 */       ctx.method_25294(this.guiLeft + 6 + i, sepY, this.guiLeft + 7 + i, sepY + 1, 
/* 165 */           RankedStyledButton.color(r, g, b, 150 + (int)(50.0D * Math.sin(i * 0.1D))));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawShopItems(class_332 ctx, PlayerRankedData data, int mouseX, int mouseY) {
/* 172 */     List<RankedNetwork.ShopItemEntry> items = data.getShopItems();
/* 173 */     int listX = this.guiLeft + 6;
/* 174 */     int listY = this.guiTop + 25;
/* 175 */     int listW = 298;
/* 176 */     int listH = 202;
/*     */ 
/*     */     
/* 179 */     ctx.method_25294(listX, listY, listX + listW, listY + listH, SECTION_BG);
/*     */     
/* 181 */     for (int row = 0; row < listH; row++) {
/* 182 */       float p = row / listH;
/* 183 */       ctx.method_25294(listX + 1, listY + row, listX + listW - 1, listY + row + 1, 
/* 184 */           RankedStyledButton.color(20, 15, 35, (int)(10.0F + p * 12.0F)));
/*     */     } 
/*     */     
/* 187 */     ctx.method_25294(listX, listY, listX + listW, listY + 2, ACCENT_PURPLE);
/* 188 */     ctx.method_25294(listX, listY + listH - 1, listX + listW, listY + listH, RankedStyledButton.darken(ACCENT_PURPLE, 40));
/* 189 */     ctx.method_25294(listX, listY, listX + 1, listY + listH, ACCENT_PURPLE);
/* 190 */     ctx.method_25294(listX + listW - 1, listY, listX + listW, listY + listH, RankedStyledButton.darken(ACCENT_PURPLE, 40));
/*     */     
/* 192 */     int innerY = listY + 4;
/* 193 */     int maxVisible = (listH - 8) / 28;
/* 194 */     int maxScroll = Math.max(0, items.size() - maxVisible);
/* 195 */     if (this.scrollOffset > maxScroll) this.scrollOffset = maxScroll; 
/* 196 */     if (this.scrollOffset < 0) this.scrollOffset = 0;
/*     */     
/* 198 */     ctx.method_44379(listX + 2, listY + 2, listX + listW - 2, listY + listH - 2);
/*     */     
/* 200 */     for (int i = 0; i < maxVisible && i + this.scrollOffset < items.size(); i++) {
/* 201 */       int itemBg, costColor; RankedNetwork.ShopItemEntry item = items.get(i + this.scrollOffset);
/* 202 */       int itemY = innerY + i * 28;
/* 203 */       int itemX = listX + 4;
/* 204 */       int itemW = listW - 8;
/*     */       
/* 206 */       boolean limitReached = (item.purchaseLimit() > 0 && item.currentPurchases() >= item.purchaseLimit());
/* 207 */       boolean canAfford = (data.getTrophies() >= item.trophyCost());
/* 208 */       boolean hovered = (mouseX >= itemX && mouseX < itemX + itemW && mouseY >= itemY && mouseY < itemY + 28);
/* 209 */       if (hovered) this.hoveredItemIndex = i + this.scrollOffset;
/*     */ 
/*     */ 
/*     */       
/* 213 */       if (limitReached) {
/* 214 */         itemBg = RankedStyledButton.color(12, 12, 18, 255);
/* 215 */       } else if (hovered) {
/* 216 */         itemBg = RankedStyledButton.color(24, 22, 40, 255);
/*     */       } else {
/* 218 */         itemBg = RankedStyledButton.color(16, 18, 30, 255);
/*     */       } 
/* 220 */       ctx.method_25294(itemX, itemY, itemX + itemW, itemY + 28 - 2, itemBg);
/*     */ 
/*     */       
/* 223 */       if (!limitReached) {
/* 224 */         int dotColor = canAfford ? ACCENT_PURPLE : RankedStyledButton.darken(ACCENT_PURPLE, 60);
/* 225 */         ctx.method_25294(itemX + 2, itemY + 11, itemX + 6, itemY + 11 + 4, dotColor);
/*     */       } else {
/*     */         
/* 228 */         ctx.method_25294(itemX + 2, itemY + 11, itemX + 6, itemY + 11 + 4, ACCENT_RED);
/*     */       } 
/*     */ 
/*     */       
/* 232 */       if (hovered && !limitReached) {
/*     */         
/* 234 */         ctx.method_25294(itemX, itemY, itemX + itemW, itemY + 1, ACCENT_PURPLE);
/* 235 */         ctx.method_25294(itemX, itemY + 28 - 3, itemX + itemW, itemY + 28 - 2, RankedStyledButton.darken(ACCENT_PURPLE, 30));
/* 236 */         ctx.method_25294(itemX, itemY, itemX + 1, itemY + 28 - 2, ACCENT_PURPLE);
/* 237 */         ctx.method_25294(itemX + itemW - 1, itemY, itemX + itemW, itemY + 28 - 2, RankedStyledButton.darken(ACCENT_PURPLE, 30));
/*     */ 
/*     */         
/* 240 */         ctx.method_25294(itemX - 1, itemY - 1, itemX + itemW + 1, itemY, HOVER_GLOW_1);
/* 241 */         ctx.method_25294(itemX - 1, itemY + 28 - 2, itemX + itemW + 1, itemY + 28 - 1, HOVER_GLOW_1);
/* 242 */         ctx.method_25294(itemX - 1, itemY, itemX, itemY + 28 - 2, HOVER_GLOW_1);
/* 243 */         ctx.method_25294(itemX + itemW, itemY, itemX + itemW + 1, itemY + 28 - 2, HOVER_GLOW_1);
/*     */         
/* 245 */         ctx.method_25294(itemX - 2, itemY - 2, itemX + itemW + 2, itemY - 1, HOVER_GLOW_2);
/* 246 */         ctx.method_25294(itemX - 2, itemY + 28 - 1, itemX + itemW + 2, itemY + 28, HOVER_GLOW_2);
/* 247 */         ctx.method_25294(itemX - 2, itemY - 1, itemX - 1, itemY + 28 - 1, HOVER_GLOW_2);
/* 248 */         ctx.method_25294(itemX + itemW + 1, itemY - 1, itemX + itemW + 2, itemY + 28 - 1, HOVER_GLOW_2);
/*     */       } 
/*     */ 
/*     */       
/* 252 */       int nameColor = limitReached ? TEXT_SOLDOUT : TEXT_PRIMARY;
/* 253 */       String namePrefix = limitReached ? "§m" : "";
/* 254 */       ctx.method_51433(this.field_22793, namePrefix + namePrefix, itemX + 10, itemY + 4, nameColor, !limitReached);
/*     */ 
/*     */       
/* 257 */       String costText = " " + item.trophyCost();
/*     */       
/* 259 */       if (limitReached) {
/* 260 */         costColor = TEXT_SOLDOUT;
/* 261 */       } else if (canAfford) {
/* 262 */         costColor = ACCENT_GOLD;
/*     */       } else {
/* 264 */         costColor = ACCENT_RED;
/*     */       } 
/* 266 */       int trophyCostIconSize = 9;
/* 267 */       int costTotalW = trophyCostIconSize + this.field_22793.method_1727(costText);
/* 268 */       int costStartX = itemX + itemW - costTotalW - 6;
/* 269 */       RankedIcon.TROPHY.drawTexture(ctx, costStartX, itemY + 3, trophyCostIconSize, trophyCostIconSize);
/* 270 */       ctx.method_51433(this.field_22793, costText, costStartX + trophyCostIconSize, itemY + 4, costColor, !limitReached);
/*     */ 
/*     */       
/* 273 */       if (item.purchaseLimit() > 0) {
/* 274 */         String limitText = "" + item.currentPurchases() + "/" + item.currentPurchases();
/* 275 */         int limitColor = limitReached ? ACCENT_RED : TEXT_DIM;
/* 276 */         ctx.method_51433(this.field_22793, limitText, itemX + 10, itemY + 15, limitColor, false);
/*     */       } 
/*     */ 
/*     */       
/* 280 */       if (limitReached) {
/* 281 */         String soldOutText = "✖ Sold Out";
/* 282 */         int soldOutX = itemX + itemW - this.field_22793.method_1727(soldOutText) - 6;
/* 283 */         ctx.method_51433(this.field_22793, soldOutText, soldOutX, itemY + 15, ACCENT_RED, true);
/* 284 */       } else if (!canAfford) {
/*     */         
/* 286 */         String insuffText = "Insufficient";
/* 287 */         int insuffX = itemX + itemW - this.field_22793.method_1727(insuffText) - 6;
/* 288 */         ctx.method_51433(this.field_22793, insuffText, insuffX, itemY + 15, RankedStyledButton.color(180, 80, 80, 255), false);
/*     */       } 
/*     */ 
/*     */       
/* 292 */       ctx.method_25294(itemX + 8, itemY + 28 - 2, itemX + itemW - 8, itemY + 28 - 1, 
/* 293 */           RankedStyledButton.color(40, 35, 60, 80));
/*     */     } 
/*     */     
/* 296 */     ctx.method_44380();
/*     */ 
/*     */     
/* 299 */     this.scrollbarX = listX + listW - 8 - 2;
/* 300 */     this.scrollbarTrackY = listY + 4;
/* 301 */     this.scrollbarTrackHeight = listH - 8;
/* 302 */     ctx.method_25294(this.scrollbarX, this.scrollbarTrackY, this.scrollbarX + 8, this.scrollbarTrackY + this.scrollbarTrackHeight, 
/* 303 */         RankedStyledButton.color(15, 15, 30, 255));
/* 304 */     if (items.size() > maxVisible) {
/* 305 */       float scrollRatio = (maxScroll > 0) ? (this.scrollOffset / maxScroll) : 0.0F;
/* 306 */       this.scrollbarThumbHeight = Math.max(15, (int)(maxVisible / items.size() * this.scrollbarTrackHeight));
/* 307 */       this.scrollbarThumbY = this.scrollbarTrackY + (int)(scrollRatio * (this.scrollbarTrackHeight - this.scrollbarThumbHeight));
/* 308 */       if (this.scrollbarThumbY + this.scrollbarThumbHeight > this.scrollbarTrackY + this.scrollbarTrackHeight) {
/* 309 */         this.scrollbarThumbY = this.scrollbarTrackY + this.scrollbarTrackHeight - this.scrollbarThumbHeight;
/*     */       }
/* 311 */       boolean thumbHov = ((mouseX >= this.scrollbarX && mouseX < this.scrollbarX + 8 && mouseY >= this.scrollbarThumbY && mouseY < this.scrollbarThumbY + this.scrollbarThumbHeight) || this.scrollbarDragging);
/*     */       
/* 313 */       int thumbCol = thumbHov ? RankedStyledButton.color(200, 150, 255, 255) : RankedStyledButton.color(160, 110, 220, 255);
/* 314 */       ctx.method_25294(this.scrollbarX, this.scrollbarThumbY, this.scrollbarX + 8, this.scrollbarThumbY + this.scrollbarThumbHeight, thumbCol);
/* 315 */       int thumbBorder = thumbHov ? RankedStyledButton.color(230, 180, 255, 255) : RankedStyledButton.color(200, 140, 255, 255);
/* 316 */       ctx.method_49601(this.scrollbarX, this.scrollbarThumbY, 8, this.scrollbarThumbHeight, thumbBorder);
/*     */     } 
/*     */ 
/*     */     
/* 320 */     if (items.isEmpty()) {
/* 321 */       ctx.method_25300(this.field_22793, "No items available", listX + listW / 2, listY + listH / 2 - 4, TEXT_DIM);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawBetaSeasonMessage(class_332 ctx) {
/* 329 */     int listX = this.guiLeft + 6;
/* 330 */     int listY = this.guiTop + 25;
/* 331 */     int listW = 298;
/* 332 */     int listH = 202;
/*     */     
/* 334 */     ctx.method_25294(listX, listY, listX + listW, listY + listH, SECTION_BG);
/*     */     
/* 336 */     int centerX = listX + listW / 2;
/* 337 */     int centerY = listY + listH / 2;
/*     */     
/* 339 */     ctx.method_25300(this.field_22793, "§lBETA SEASON", centerX, centerY - 18, ACCENT_ORANGE);
/*     */     
/* 341 */     ctx.method_25300(this.field_22793, "The shop is unavailable", centerX, centerY - 4, TEXT_SECONDARY);
/*     */     
/* 343 */     ctx.method_25300(this.field_22793, "during the beta season.", centerX, centerY + 8, TEXT_SECONDARY);
/*     */     
/* 345 */     ctx.method_25300(this.field_22793, "It will open with Season 1.", centerX, centerY + 22, TEXT_DIM);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawBottomButtons(class_332 ctx, int mouseX, int mouseY) {
/* 352 */     int btnY = this.guiTop + 256 - 24;
/* 353 */     int btnH = 18;
/*     */     
/* 355 */     int backW = 50;
/* 356 */     this.backHovered = drawButton(ctx, this.guiLeft + 8, btnY, backW, btnH, "◀ Back", mouseX, mouseY, true);
/*     */     
/* 358 */     int closeW = 50;
/* 359 */     this.closeHovered = drawButton(ctx, this.guiLeft + 310 - closeW - 8, btnY, closeW, btnH, "Close", mouseX, mouseY, true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawConfirmOverlay(class_332 ctx, PlayerRankedData data, int mouseX, int mouseY) {
/* 365 */     this.confirmYesHovered = this.confirmNoHovered = false;
/*     */     
/* 367 */     class_4587 matrices = ctx.method_51448();
/* 368 */     matrices.method_22903();
/* 369 */     matrices.method_46416(0.0F, 0.0F, 400.0F);
/*     */     
/* 371 */     ctx.method_25294(0, 0, this.field_22789, this.field_22790, RankedStyledButton.color(0, 0, 0, 150));
/*     */     
/* 373 */     List<RankedNetwork.ShopItemEntry> items = data.getShopItems();
/* 374 */     if (this.confirmItemIndex < 0 || this.confirmItemIndex >= items.size()) {
/* 375 */       this.showConfirm = false;
/* 376 */       matrices.method_22909();
/*     */       
/*     */       return;
/*     */     } 
/* 380 */     RankedNetwork.ShopItemEntry item = items.get(this.confirmItemIndex);
/*     */     
/* 382 */     int popupW = 230;
/* 383 */     int popupH = 84;
/* 384 */     int popupX = (this.field_22789 - popupW) / 2;
/* 385 */     int popupY = (this.field_22790 - popupH) / 2;
/*     */ 
/*     */     
/* 388 */     ctx.method_25294(popupX, popupY, popupX + popupW, popupY + popupH, RankedStyledButton.color(14, 14, 24, 255));
/*     */     
/* 390 */     ctx.method_25294(popupX, popupY, popupX + popupW, popupY + 2, ACCENT_PURPLE);
/* 391 */     ctx.method_25294(popupX, popupY + popupH - 2, popupX + popupW, popupY + popupH, ACCENT_PURPLE);
/* 392 */     ctx.method_25294(popupX, popupY, popupX + 2, popupY + popupH, ACCENT_PURPLE);
/* 393 */     ctx.method_25294(popupX + popupW - 2, popupY, popupX + popupW, popupY + popupH, ACCENT_PURPLE);
/*     */     
/* 395 */     ctx.method_25294(popupX + 2, popupY + 2, popupX + popupW - 2, popupY + 3, RankedStyledButton.lighten(ACCENT_PURPLE, 20));
/*     */     
/* 397 */     ctx.method_25300(this.field_22793, "§lPurchase " + item.displayName() + "?", popupX + popupW / 2, popupY + 10, TEXT_PRIMARY);
/*     */ 
/*     */ 
/*     */     
/* 401 */     String costPrefix = "Cost: ";
/* 402 */     String costAmount = " " + item.trophyCost();
/* 403 */     int costPrefixW = this.field_22793.method_1727(costPrefix);
/* 404 */     int costIconSize = 9;
/* 405 */     int costAmountW = this.field_22793.method_1727(costAmount);
/* 406 */     int costLineW = costPrefixW + costIconSize + costAmountW;
/* 407 */     int costLineX = popupX + (popupW - costLineW) / 2;
/* 408 */     int costLineY = popupY + 26;
/* 409 */     ctx.method_51433(this.field_22793, costPrefix, costLineX, costLineY, ACCENT_GOLD, true);
/* 410 */     RankedIcon.TROPHY.drawTexture(ctx, costLineX + costPrefixW, costLineY - 1, costIconSize, costIconSize);
/* 411 */     ctx.method_51433(this.field_22793, costAmount, costLineX + costPrefixW + costIconSize, costLineY, ACCENT_GOLD, true);
/*     */ 
/*     */     
/* 414 */     int balanceAfter = data.getTrophies() - item.trophyCost();
/* 415 */     String balanceLine = "Balance: " + data.getTrophies() + " → " + balanceAfter;
/* 416 */     int balanceColor = (balanceAfter >= 0) ? ACCENT_GREEN : ACCENT_RED;
/* 417 */     ctx.method_25300(this.field_22793, balanceLine, popupX + popupW / 2, popupY + 40, balanceColor);
/*     */     
/* 419 */     int btnW = 55, btnH = 18, btnSpacing = 12;
/* 420 */     int totalBtnW = btnW * 2 + btnSpacing;
/* 421 */     int btnStartX = popupX + (popupW - totalBtnW) / 2;
/* 422 */     int btnY = popupY + popupH - 26;
/*     */     
/* 424 */     this.confirmYesHovered = drawButton(ctx, btnStartX, btnY, btnW, btnH, "§aConfirm", mouseX, mouseY, true);
/* 425 */     this.confirmNoHovered = drawButton(ctx, btnStartX + btnW + btnSpacing, btnY, btnW, btnH, "§cCancel", mouseX, mouseY, true);
/*     */     
/* 427 */     matrices.method_22909();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean drawButton(class_332 ctx, int x, int y, int w, int h, String text, int mx, int my, boolean en) {
/* 433 */     boolean hov = (en && mx >= x && mx < x + w && my >= y && my < y + h);
/* 434 */     int bg = !en ? RankedStyledButton.getButtonDisabled() : (hov ? RankedStyledButton.getButtonHover() : RankedStyledButton.getButtonBg());
/* 435 */     ctx.method_25294(x + 1, y + 1, x + w - 1, y + h - 1, bg);
/* 436 */     if (en) ctx.method_25294(x + 1, y + h - 3, x + w - 1, y + h - 1, RankedStyledButton.darken(bg, 15)); 
/* 437 */     int bc = hov ? RankedStyledButton.getButtonBorderHover() : RankedStyledButton.getButtonBorder();
/* 438 */     if (!en) bc = RankedStyledButton.color(30, 35, 50, 255); 
/* 439 */     ctx.method_25294(x + 1, y, x + w - 1, y + 1, bc);
/* 440 */     ctx.method_25294(x + 1, y + h - 1, x + w - 1, y + h, RankedStyledButton.darken(bc, 30));
/* 441 */     ctx.method_25294(x, y + 1, x + 1, y + h - 1, bc);
/* 442 */     ctx.method_25294(x + w - 1, y + 1, x + w, y + h - 1, RankedStyledButton.darken(bc, 30));
/* 443 */     if (en) {
/* 444 */       ctx.method_25294(x + 2, y + 1, x + w - 2, y + 2, RankedStyledButton.lighten(bg, 20));
/* 445 */       ctx.method_25294(x + 1, y + 2, x + 2, y + h - 3, RankedStyledButton.lighten(bg, 10));
/*     */     } 
/* 447 */     ctx.method_25294(x, y, x + 1, y + 1, 0);
/* 448 */     ctx.method_25294(x + w - 1, y, x + w, y + 1, 0);
/* 449 */     ctx.method_25294(x, y + h - 1, x + 1, y + h, 0);
/* 450 */     ctx.method_25294(x + w - 1, y + h - 1, x + w, y + h, 0);
/* 451 */     ctx.method_25300(this.field_22793, text, x + w / 2, y + (h - 8) / 2, 
/* 452 */         en ? RankedStyledButton.getTextPrimary() : RankedStyledButton.getTextDim());
/* 453 */     return hov;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean method_25402(double mouseX, double mouseY, int button) {
/* 460 */     if (button == 0) {
/*     */       
/* 462 */       if (this.showConfirm) {
/* 463 */         if (this.confirmYesHovered) {
/* 464 */           playClickSound();
/* 465 */           List<RankedNetwork.ShopItemEntry> items = PlayerRankedData.getInstance().getShopItems();
/* 466 */           if (this.confirmItemIndex >= 0 && this.confirmItemIndex < items.size()) {
/* 467 */             RankedNetwork.sendPurchaseShopItem(((RankedNetwork.ShopItemEntry)items.get(this.confirmItemIndex)).id());
/*     */           }
/* 469 */           this.showConfirm = false;
/* 470 */           return true;
/*     */         } 
/* 472 */         if (this.confirmNoHovered) {
/* 473 */           playClickSound();
/* 474 */           this.showConfirm = false;
/* 475 */           return true;
/*     */         } 
/* 477 */         return true;
/*     */       } 
/*     */ 
/*     */       
/* 481 */       if (!this.showConfirm && mouseX >= this.scrollbarX && mouseX < (this.scrollbarX + 8) && mouseY >= this.scrollbarThumbY && mouseY < (this.scrollbarThumbY + this.scrollbarThumbHeight)) {
/*     */         
/* 483 */         this.scrollbarDragging = true;
/* 484 */         this.scrollbarDragStartY = (int)mouseY;
/* 485 */         this.scrollbarDragStartOffset = this.scrollOffset;
/* 486 */         return true;
/*     */       } 
/*     */ 
/*     */       
/* 490 */       if (this.hoveredItemIndex >= 0) {
/* 491 */         PlayerRankedData data = PlayerRankedData.getInstance();
/* 492 */         List<RankedNetwork.ShopItemEntry> items = data.getShopItems();
/* 493 */         if (this.hoveredItemIndex < items.size()) {
/* 494 */           RankedNetwork.ShopItemEntry item = items.get(this.hoveredItemIndex);
/* 495 */           boolean limitReached = (item.purchaseLimit() > 0 && item.currentPurchases() >= item.purchaseLimit());
/* 496 */           boolean canAfford = (data.getTrophies() >= item.trophyCost());
/* 497 */           if (!limitReached && canAfford) {
/* 498 */             playClickSound();
/* 499 */             this.confirmItemIndex = this.hoveredItemIndex;
/* 500 */             this.showConfirm = true;
/* 501 */             return true;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 506 */       if (this.backHovered) {
/* 507 */         playClickSound();
/* 508 */         class_310.method_1551().method_1507(new RankedScreen());
/* 509 */         return true;
/*     */       } 
/*     */       
/* 512 */       if (this.closeHovered) {
/* 513 */         playClickSound();
/* 514 */         method_25419();
/* 515 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/* 519 */     return super.method_25402(mouseX, mouseY, button);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean method_25403(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
/* 524 */     if (this.scrollbarDragging && button == 0) {
/* 525 */       int d = (int)mouseY - this.scrollbarDragStartY;
/* 526 */       int maxDrag = this.scrollbarTrackHeight - this.scrollbarThumbHeight;
/* 527 */       List<RankedNetwork.ShopItemEntry> items = PlayerRankedData.getInstance().getShopItems();
/* 528 */       int listH = 202;
/* 529 */       int maxVisible = (listH - 8) / 28;
/* 530 */       int maxScroll = Math.max(0, items.size() - maxVisible);
/* 531 */       if (maxDrag > 0) {
/* 532 */         this.scrollOffset = Math.max(0, Math.min(maxScroll, this.scrollbarDragStartOffset + (int)(d / maxDrag * maxScroll)));
/*     */       }
/* 534 */       return true;
/*     */     } 
/* 536 */     return super.method_25403(mouseX, mouseY, button, deltaX, deltaY);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean method_25406(double mouseX, double mouseY, int button) {
/* 541 */     if (button == 0 && this.scrollbarDragging) {
/* 542 */       this.scrollbarDragging = false;
/* 543 */       return true;
/*     */     } 
/* 545 */     return super.method_25406(mouseX, mouseY, button);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean method_25401(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
/* 550 */     if (!this.showConfirm) {
/* 551 */       this.scrollOffset -= (int)verticalAmount * 2;
/* 552 */       List<RankedNetwork.ShopItemEntry> items = PlayerRankedData.getInstance().getShopItems();
/* 553 */       int listH = 202;
/* 554 */       int maxVisible = (listH - 8) / 28;
/* 555 */       int maxScroll = Math.max(0, items.size() - maxVisible);
/* 556 */       this.scrollOffset = Math.max(0, Math.min(this.scrollOffset, maxScroll));
/* 557 */       return true;
/*     */     } 
/* 559 */     return super.method_25401(mouseX, mouseY, horizontalAmount, verticalAmount);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean method_25404(int keyCode, int scanCode, int modifiers) {
/* 564 */     if (keyCode == 256) {
/* 565 */       if (this.showConfirm) {
/* 566 */         this.showConfirm = false;
/* 567 */         return true;
/*     */       } 
/* 569 */       method_25419();
/* 570 */       return true;
/*     */     } 
/* 572 */     return super.method_25404(keyCode, scanCode, modifiers);
/*     */   }
/*     */   
/*     */   private void playClickSound() {
/* 576 */     class_310.method_1551().method_1483().method_4873((class_1113)class_1109.method_47978((class_6880)class_3417.field_15015, 1.0F));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void method_25420(class_332 context, int mouseX, int mouseY, float delta) {}
/*     */ 
/*     */   
/*     */   public boolean method_25421() {
/* 585 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\ranked\screen\RankedShopScreen.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */