/*     */ package com.atlas.common.fabric.auction.screen;
/*     */ 
/*     */ import com.atlas.common.fabric.auction.data.AuctionClientData;
/*     */ import com.atlas.common.fabric.auction.network.AuctionNetwork;
/*     */ import com.atlas.common.fabric.auction.network.AuctionNetworkHelper;
/*     */ import com.atlas.common.fabric.ranked.screen.RankedStyledButton;
/*     */ import com.atlas.common.fabric.screen.PokemonRenderHelper;
/*     */ import com.cobblemon.mod.common.api.gui.GuiUtilsKt;
/*     */ import com.cobblemon.mod.common.client.CobblemonResources;
/*     */ import java.text.NumberFormat;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import net.minecraft.class_1109;
/*     */ import net.minecraft.class_1113;
/*     */ import net.minecraft.class_1799;
/*     */ import net.minecraft.class_2561;
/*     */ import net.minecraft.class_2583;
/*     */ import net.minecraft.class_2960;
/*     */ import net.minecraft.class_332;
/*     */ import net.minecraft.class_3414;
/*     */ import net.minecraft.class_3417;
/*     */ import net.minecraft.class_437;
/*     */ import net.minecraft.class_5250;
/*     */ import net.minecraft.class_5348;
/*     */ import net.minecraft.class_8710;
/*     */ 
/*     */ public class PriceLookupScreen
/*     */   extends class_437
/*     */ {
/*     */   private static final String NS = "atlas-common-fabric";
/*     */   
/*     */   private static class_2960 tex(String path) {
/*  33 */     return class_2960.method_60655("atlas-common-fabric", "textures/auction/" + path);
/*     */   } private static final class WidgetTexture extends Record { private final class_2960 normal; private final class_2960 hover; private final int width; private final int height;
/*  35 */     private WidgetTexture(class_2960 normal, class_2960 hover, int width, int height) { this.normal = normal; this.hover = hover; this.width = width; this.height = height; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/auction/screen/PriceLookupScreen$WidgetTexture;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #35	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*  35 */       //   0	7	0	this	Lcom/atlas/common/fabric/auction/screen/PriceLookupScreen$WidgetTexture; } public class_2960 normal() { return this.normal; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/auction/screen/PriceLookupScreen$WidgetTexture;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #35	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*  35 */       //   0	7	0	this	Lcom/atlas/common/fabric/auction/screen/PriceLookupScreen$WidgetTexture; } public class_2960 hover() { return this.hover; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/auction/screen/PriceLookupScreen$WidgetTexture;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #35	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lcom/atlas/common/fabric/auction/screen/PriceLookupScreen$WidgetTexture;
/*  35 */       //   0	8	1	o	Ljava/lang/Object; } public int width() { return this.width; } public int height() { return this.height; }
/*     */      }
/*     */   private static WidgetTexture widget(String fileName, int width, int height) {
/*  38 */     class_2960 texture = tex(fileName);
/*  39 */     return new WidgetTexture(texture, texture, width, height);
/*     */   }
/*     */   
/*     */   private static WidgetTexture widget(String normalFile, String hoverFile, int width, int height) {
/*  43 */     return new WidgetTexture(tex(normalFile), tex(hoverFile), width, height);
/*     */   }
/*     */   
/*     */   private static int c(int r, int g, int b, int a) {
/*  47 */     return RankedStyledButton.color(r, g, b, a);
/*     */   }
/*  49 */   private static final int ACCENT_GOLD = c(255, 200, 60, 255);
/*  50 */   private static final int ACCENT_WARM = c(180, 130, 50, 255);
/*  51 */   private static final int ACCENT_BLUE = c(176, 184, 255, 255);
/*  52 */   private static final int ACCENT_RED = c(220, 60, 60, 255);
/*  53 */   private static final int TEXT_PRIMARY = c(240, 235, 220, 255);
/*  54 */   private static final int TEXT_SECONDARY = c(176, 184, 220, 255);
/*  55 */   private static final int TEXT_DIM = c(116, 124, 174, 255);
/*  56 */   private static final int TEXT_GOLD = c(255, 210, 80, 255);
/*     */   
/*  58 */   private static final NumberFormat COIN_FORMAT = NumberFormat.getNumberInstance(Locale.US);
/*     */   
/*  60 */   private static final class_2960 TEX_BG = tex("pricelookup-frame.png");
/*  61 */   private static final class_2960 AUCTION_FONT = CobblemonResources.INSTANCE.getDEFAULT_LARGE();
/*  62 */   private static final WidgetTexture TEX_ITEM_PANEL = widget("item_panel.png", "item_panel_hover.png", 138, 53);
/*  63 */   private static final WidgetTexture TEX_SEARCH_BAR = widget("search_bar.png", "search_bar_active.png", 110, 14);
/*  64 */   private static final WidgetTexture TEX_SEARCH_BTN = widget("search_btn.png", "search_btn_hover.png", 65, 14);
/*  65 */   private static final WidgetTexture TEX_REFRESH_BTN = widget("refresh_btn.png", "refresh_btn_hover.png", 50, 13);
/*  66 */   private static final class_2960 TEX_COINS_ICON = tex("coins_icon.png"); private static final int BACKGROUND_WIDTH = 604; private static final int GUI_WIDTH = 450; private static final int GUI_HEIGHT = 350; private static final int GRID_COLS = 3;
/*     */   private static final int GRID_ROWS = 5;
/*     */   private static final int CARD_PAD = 2;
/*     */   private static final int CONTENT_X = 15;
/*     */   private static final int CONTROLS_Y = 22;
/*     */   private static final int GRID_Y = 39;
/*     */   private static final int FOOTER_Y = 320;
/*     */   private static final int BACKGROUND_X_OFFSET = 77;
/*     */   private int guiLeft;
/*     */   private int guiTop;
/*     */   private int gridX;
/*     */   private int gridY;
/*     */   private int gridW;
/*     */   private int gridH;
/*     */   private int cardW;
/*     */   private int cardH;
/*     */   private int searchBarX;
/*     */   private int searchBarY;
/*     */   private int searchBarW;
/*     */   private int searchBarH;
/*  86 */   private String searchText = "";
/*     */   private boolean searchFocused = true;
/*  88 */   private int cursorPos = 0;
/*  89 */   private long lastBlinkTime = System.currentTimeMillis();
/*     */   
/*     */   private boolean showingDetail = false;
/*  92 */   private String selectedFilterKey = null;
/*  93 */   private String selectedDisplayName = null;
/*  94 */   private AuctionDetailPanel detailPanel = null;
/*     */   
/*  96 */   private int hoveredCardIndex = -1; private boolean closeHovered; private boolean searchBtnHovered; private boolean refreshHovered; private boolean prevPageHovered;
/*     */   private boolean nextPageHovered;
/*  98 */   private int currentPage = 1;
/*  99 */   private int totalPages = 1;
/*     */ 
/*     */   
/*     */   private AuctionHouseScreen cardRenderer;
/*     */ 
/*     */   
/*     */   public PriceLookupScreen() {
/* 106 */     super((class_2561)class_2561.method_43470("Price Lookup"));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void method_25426() {
/* 111 */     super.method_25426();
/* 112 */     PokemonRenderHelper.init();
/* 113 */     this.guiLeft = (this.field_22789 - 450) / 2;
/* 114 */     this.guiTop = (this.field_22790 - 350) / 2;
/*     */     
/* 116 */     this.gridX = this.guiLeft + 15;
/* 117 */     this.gridY = this.guiTop + 39;
/* 118 */     this.cardW = TEX_ITEM_PANEL.width();
/* 119 */     this.cardH = TEX_ITEM_PANEL.height();
/* 120 */     this.gridW = 3 * this.cardW + 4;
/* 121 */     this.gridH = 5 * this.cardH + 8;
/*     */     
/* 123 */     this.searchBarW = TEX_SEARCH_BAR.width();
/* 124 */     this.searchBarH = TEX_SEARCH_BAR.height();
/* 125 */     this.searchBarX = this.guiLeft + (450 - this.searchBarW - 2 - TEX_SEARCH_BTN.width()) / 2;
/* 126 */     this.searchBarY = this.guiTop + 22;
/*     */ 
/*     */ 
/*     */     
/* 130 */     if (this.cardRenderer == null) {
/* 131 */       this.cardRenderer = new AuctionHouseScreen();
/*     */     }
/* 133 */     this.cardRenderer.method_25423(this.field_22787, this.field_22789, this.field_22790);
/*     */   }
/*     */   
/*     */   public boolean method_25421() {
/* 137 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void method_25420(class_332 context, int mouseX, int mouseY, float delta) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void method_25394(class_332 ctx, int mouseX, int mouseY, float delta) {
/* 151 */     ctx.method_25294(0, 0, this.field_22789, this.field_22790, c(0, 0, 0, 180));
/*     */     
/* 153 */     if (this.showingDetail && this.detailPanel != null) {
/* 154 */       this.detailPanel.tick();
/* 155 */       drawPanel(ctx);
/* 156 */       this.detailPanel.render(ctx, this.field_22793, mouseX, mouseY, delta);
/* 157 */       super.method_25394(ctx, mouseX, mouseY, delta);
/*     */       
/*     */       return;
/*     */     } 
/* 161 */     this.hoveredCardIndex = -1;
/* 162 */     this.closeHovered = this.searchBtnHovered = this.refreshHovered = this.prevPageHovered = this.nextPageHovered = false;
/*     */     
/* 164 */     drawPanel(ctx);
/* 165 */     drawHeader(ctx, mouseX, mouseY);
/* 166 */     drawGrid(ctx, mouseX, mouseY, delta);
/* 167 */     drawFooter(ctx, mouseX, mouseY);
/*     */     
/* 169 */     super.method_25394(ctx, mouseX, mouseY, delta);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawPanel(class_332 ctx) {
/* 175 */     drawTex(ctx, TEX_BG, this.guiLeft - 77, this.guiTop, 604, 350);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawHeader(class_332 ctx, int mouseX, int mouseY) {
/* 181 */     drawSearchBar(ctx, mouseX, mouseY);
/*     */     
/* 183 */     int searchBtnX = this.searchBarX + this.searchBarW + 2;
/* 184 */     this.searchBtnHovered = drawTexturedWidget(ctx, TEX_SEARCH_BTN, searchBtnX, this.searchBarY, mouseX, mouseY);
/* 185 */     drawButtonLabel(ctx, "Search", searchBtnX, this.searchBarY, TEX_SEARCH_BTN.width(), TEX_SEARCH_BTN.height(), this.searchBtnHovered, true);
/*     */   }
/*     */   
/*     */   private void drawSearchBar(class_332 ctx, int mouseX, int mouseY) {
/* 189 */     boolean hov = (mouseX >= this.searchBarX && mouseX < this.searchBarX + this.searchBarW && mouseY >= this.searchBarY && mouseY < this.searchBarY + this.searchBarH);
/* 190 */     class_2960 searchTex = (this.searchFocused || !this.searchText.isEmpty() || hov) ? TEX_SEARCH_BAR.hover() : TEX_SEARCH_BAR.normal();
/* 191 */     drawTex(ctx, searchTex, this.searchBarX, this.searchBarY, this.searchBarW, this.searchBarH);
/*     */     
/* 193 */     int textX = this.searchBarX + 4;
/* 194 */     int textY = this.searchBarY + 3;
/*     */     
/* 196 */     if (!this.searchText.isEmpty() || this.searchFocused) {
/* 197 */       ctx.method_25294(this.searchBarX + 2, this.searchBarY + 2, this.searchBarX + this.searchBarW - 2, this.searchBarY + this.searchBarH - 2, c(26, 25, 50, 255));
/* 198 */       String visibleText = this.searchText;
/* 199 */       int availW = this.searchBarW - 10;
/* 200 */       while (this.field_22793.method_27525((class_5348)auctionText(visibleText)) > availW && visibleText.length() > 0) {
/* 201 */         visibleText = visibleText.substring(1);
/*     */       }
/* 203 */       drawCobblemonText(ctx, auctionText(visibleText), textX, textY, 1.0F, TEXT_PRIMARY, false, availW);
/*     */       
/* 205 */       if (this.searchFocused) {
/* 206 */         long elapsed = (System.currentTimeMillis() - this.lastBlinkTime) % 1000L;
/* 207 */         if (elapsed < 500L) {
/* 208 */           int trimmed = this.searchText.length() - visibleText.length();
/* 209 */           if (this.cursorPos >= trimmed) {
/* 210 */             String beforeCursor = this.searchText.substring(trimmed, this.cursorPos);
/* 211 */             int cursorX = textX + this.field_22793.method_27525((class_5348)auctionText(beforeCursor));
/* 212 */             ctx.method_25294(cursorX, textY - 1, cursorX + 1, textY + 9, TEXT_PRIMARY);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 217 */       if (!this.searchText.isEmpty()) {
/* 218 */         int clearX = this.searchBarX + this.searchBarW - 10;
/* 219 */         boolean clearHov = (mouseX >= clearX - 2 && mouseX < clearX + 8 && mouseY >= textY - 2 && mouseY < textY + 10);
/* 220 */         ctx.method_51433(this.field_22793, "×", clearX, textY, clearHov ? TEXT_PRIMARY : TEXT_DIM, true);
/*     */       } 
/* 222 */     } else if (hov) {
/* 223 */       ctx.method_25294(this.searchBarX + 1, this.searchBarY + this.searchBarH - 1, this.searchBarX + this.searchBarW - 1, this.searchBarY + this.searchBarH, c(42, 47, 78, 90));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawGrid(class_332 ctx, int mouseX, int mouseY, float delta) {
/* 230 */     AuctionClientData data = AuctionClientData.getInstance();
/* 231 */     List<AuctionNetwork.PriceLookupKeyEntry> results = data.getPriceLookupResults();
/*     */     
/* 233 */     if (data.isPriceLookupKeysLoading()) {
/* 234 */       drawCobblemonText(ctx, auctionText("Loading..."), this.gridX + this.gridW / 2.0F, this.gridY + this.gridH / 2.0F - 4.0F, 1.0F, TEXT_DIM, true, this.gridW);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 239 */     if (results.isEmpty()) {
/* 240 */       String msg = this.searchText.isEmpty() ? "Type to search for items or Pokemon" : "No listings found";
/* 241 */       drawCobblemonText(ctx, auctionText(msg), this.gridX + this.gridW / 2.0F, this.gridY + this.gridH / 2.0F - 4.0F, 1.0F, 
/* 242 */           this.searchText.isEmpty() ? ACCENT_BLUE : TEXT_DIM, true, this.gridW);
/*     */       
/*     */       return;
/*     */     } 
/* 246 */     int maxCards = 15;
/* 247 */     this.totalPages = Math.max(1, (results.size() + maxCards - 1) / maxCards);
/* 248 */     if (this.currentPage > this.totalPages) this.currentPage = this.totalPages; 
/* 249 */     int startIdx = (this.currentPage - 1) * maxCards;
/*     */     
/* 251 */     ctx.method_44379(this.gridX, this.gridY, this.gridX + this.gridW, this.gridY + this.gridH);
/*     */     
/* 253 */     for (int i = 0; i < maxCards && startIdx + i < results.size(); i++) {
/* 254 */       int col = i % 3;
/* 255 */       int row = i / 3;
/* 256 */       int cx = this.gridX + col * (this.cardW + 2);
/* 257 */       int cy = this.gridY + row * (this.cardH + 2);
/*     */       
/* 259 */       AuctionNetwork.PriceLookupKeyEntry entry = results.get(startIdx + i);
/* 260 */       boolean hov = (mouseX >= cx && mouseX < cx + this.cardW && mouseY >= cy && mouseY < cy + this.cardH);
/* 261 */       if (hov) this.hoveredCardIndex = startIdx + i;
/*     */       
/* 263 */       drawResultCard(ctx, cx, cy, this.cardW, this.cardH, entry, hov, i, delta);
/*     */     } 
/*     */     
/* 266 */     ctx.method_44380();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawResultCard(class_332 ctx, int x, int y, int w, int h, AuctionNetwork.PriceLookupKeyEntry entry, boolean hovered, int cardIndex, float delta) {
/* 272 */     String key = entry.filterKey();
/* 273 */     String speciesId = "";
/* 274 */     String itemType = "ITEMS";
/* 275 */     if (key.startsWith("cobblemon:") && !key.contains("#")) {
/* 276 */       speciesId = key;
/* 277 */       itemType = "POKEMON";
/* 278 */     } else if (key.startsWith("plushie:")) {
/* 279 */       speciesId = key.replace("plushie:", "");
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 284 */     AuctionNetwork.AuctionListingEntry syntheticListing = new AuctionNetwork.AuctionListingEntry(key, entry.displayName(), "", 0.0D, "BUY_NOW", itemType, 0, 1, (entry.displayStack() != null) ? entry.displayStack() : class_1799.field_8037, speciesId, Long.MAX_VALUE, 0);
/*     */ 
/*     */ 
/*     */     
/* 288 */     this.cardRenderer.drawAuctionCard(ctx, x, y, w, h, syntheticListing, hovered, false, cardIndex, delta, Long.MAX_VALUE);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawFooter(class_332 ctx, int mouseX, int mouseY) {
/* 294 */     AuctionClientData data = AuctionClientData.getInstance();
/* 295 */     int footerY = this.guiTop + 320;
/*     */     
/* 297 */     int refreshBtnX = this.guiLeft + 15;
/* 298 */     int refreshBtnY = footerY + 1;
/* 299 */     this.refreshHovered = drawTexturedWidget(ctx, TEX_REFRESH_BTN, refreshBtnX, refreshBtnY, mouseX, mouseY);
/* 300 */     drawButtonLabel(ctx, "Refresh", refreshBtnX, refreshBtnY, TEX_REFRESH_BTN.width(), TEX_REFRESH_BTN.height(), this.refreshHovered);
/*     */     
/* 302 */     String pageText = "Page " + this.currentPage + " / " + this.totalPages;
/* 303 */     int pageCenterX = this.guiLeft + 225 + 8;
/* 304 */     ctx.method_51433(this.field_22793, pageText, pageCenterX - this.field_22793.method_1727(pageText) / 2, footerY + 5, TEXT_PRIMARY, false);
/*     */     
/* 306 */     int pageTextW = this.field_22793.method_27525((class_5348)auctionText(pageText));
/* 307 */     int prevX = pageCenterX - pageTextW / 2 - 12 - 8;
/* 308 */     int nextX = pageCenterX + pageTextW / 2 + 5 + 8;
/* 309 */     int arrowY = footerY + 3;
/*     */     
/* 311 */     if (this.currentPage > 1) {
/* 312 */       this.prevPageHovered = (mouseX >= prevX && mouseX < prevX + 8 && mouseY >= arrowY && mouseY < arrowY + 10);
/* 313 */       ctx.method_51433(this.field_22793, "◀", prevX + 1, arrowY + 3, this.prevPageHovered ? ACCENT_BLUE : TEXT_PRIMARY, false);
/*     */     } 
/* 315 */     if (this.currentPage < this.totalPages) {
/* 316 */       this.nextPageHovered = (mouseX >= nextX && mouseX < nextX + 8 && mouseY >= arrowY && mouseY < arrowY + 10);
/* 317 */       ctx.method_51433(this.field_22793, "▶", nextX + 1, arrowY + 3, this.nextPageHovered ? ACCENT_BLUE : TEXT_PRIMARY, false);
/*     */     } 
/*     */     
/* 320 */     String balanceText = COIN_FORMAT.format((long)data.getBalance()) + " Coins";
/* 321 */     int balanceW = this.field_22793.method_1727(balanceText);
/* 322 */     int balanceX = this.guiLeft + 450 - 15 - balanceW - 6;
/* 323 */     int balanceY = footerY + 3;
/* 324 */     drawTex(ctx, TEX_COINS_ICON, balanceX - 20, footerY + 4, 16, 7);
/* 325 */     ctx.method_51433(this.field_22793, balanceText, balanceX, balanceY, TEXT_GOLD, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean method_25400(char chr, int modifiers) {
/* 336 */     if (this.searchFocused && !this.showingDetail && chr >= ' ') {
/* 337 */       this.searchText = this.searchText.substring(0, this.cursorPos) + this.searchText.substring(0, this.cursorPos) + chr;
/* 338 */       this.cursorPos++;
/* 339 */       this.lastBlinkTime = System.currentTimeMillis();
/* 340 */       triggerSearch();
/* 341 */       return true;
/*     */     } 
/* 343 */     return super.method_25400(chr, modifiers);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean method_25404(int keyCode, int scanCode, int modifiers) {
/* 348 */     if (keyCode == 256) {
/* 349 */       if (this.showingDetail) { closeDetail(); return true; }
/* 350 */        method_25419();
/* 351 */       return true;
/*     */     } 
/* 353 */     if (this.showingDetail && this.detailPanel != null) {
/* 354 */       return this.detailPanel.keyPressed(keyCode, scanCode, modifiers);
/*     */     }
/* 356 */     if (this.searchFocused && !this.showingDetail) {
/* 357 */       if (keyCode == 259 && this.cursorPos > 0) {
/* 358 */         this.searchText = this.searchText.substring(0, this.cursorPos - 1) + this.searchText.substring(0, this.cursorPos - 1);
/* 359 */         this.cursorPos--;
/* 360 */         this.lastBlinkTime = System.currentTimeMillis();
/* 361 */         triggerSearch();
/* 362 */         return true;
/*     */       } 
/* 364 */       if (keyCode == 261 && this.cursorPos < this.searchText.length()) {
/* 365 */         this.searchText = this.searchText.substring(0, this.cursorPos) + this.searchText.substring(0, this.cursorPos);
/* 366 */         this.lastBlinkTime = System.currentTimeMillis();
/* 367 */         triggerSearch();
/* 368 */         return true;
/*     */       } 
/* 370 */       if (keyCode == 263 && this.cursorPos > 0) { this.cursorPos--; this.lastBlinkTime = System.currentTimeMillis(); return true; }
/* 371 */        if (keyCode == 262 && this.cursorPos < this.searchText.length()) { this.cursorPos++; this.lastBlinkTime = System.currentTimeMillis(); return true; }
/* 372 */        return true;
/*     */     } 
/* 374 */     return super.method_25404(keyCode, scanCode, modifiers);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean method_25402(double mouseX, double mouseY, int button) {
/* 379 */     int mx = (int)mouseX, my = (int)mouseY;
/* 380 */     if (button != 0) return super.method_25402(mouseX, mouseY, button);
/*     */     
/* 382 */     if (this.showingDetail && this.detailPanel != null) {
/* 383 */       return this.detailPanel.mouseClicked(mouseX, mouseY, button);
/*     */     }
/*     */ 
/*     */     
/* 387 */     if (this.closeHovered) { method_25419(); return true; }
/*     */ 
/*     */     
/* 390 */     if (this.searchBtnHovered) { triggerSearch(); playClick(); return true; }
/*     */ 
/*     */     
/* 393 */     if (this.refreshHovered) { triggerSearch(); playClick(); return true; }
/*     */ 
/*     */     
/* 396 */     if (!this.searchText.isEmpty()) {
/* 397 */       int clearX = this.searchBarX + this.searchBarW - 10;
/* 398 */       int textY = this.searchBarY + 3;
/* 399 */       if (mx >= clearX - 2 && mx < clearX + 8 && my >= textY - 2 && my < textY + 10) {
/* 400 */         this.searchText = "";
/* 401 */         this.cursorPos = 0;
/* 402 */         AuctionClientData.getInstance().updatePriceLookupKeysFromSync(new AuctionNetwork.SyncPriceLookupKeysPayload(List.of()));
/* 403 */         playClick();
/* 404 */         return true;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 409 */     if (mx >= this.searchBarX && mx < this.searchBarX + this.searchBarW && my >= this.searchBarY && my < this.searchBarY + this.searchBarH) {
/* 410 */       this.searchFocused = true;
/* 411 */       this.lastBlinkTime = System.currentTimeMillis();
/* 412 */       return true;
/*     */     } 
/* 414 */     this.searchFocused = false;
/*     */ 
/*     */ 
/*     */     
/* 418 */     if (this.prevPageHovered && this.currentPage > 1) { this.currentPage--; playClick(); return true; }
/* 419 */      if (this.nextPageHovered && this.currentPage < this.totalPages) { this.currentPage++; playClick(); return true; }
/*     */ 
/*     */     
/* 422 */     if (this.hoveredCardIndex >= 0) {
/* 423 */       List<AuctionNetwork.PriceLookupKeyEntry> results = AuctionClientData.getInstance().getPriceLookupResults();
/* 424 */       if (this.hoveredCardIndex < results.size()) {
/* 425 */         AuctionNetwork.PriceLookupKeyEntry entry = results.get(this.hoveredCardIndex);
/* 426 */         this.selectedFilterKey = entry.filterKey();
/* 427 */         this.selectedDisplayName = entry.displayName();
/* 428 */         openDetail(entry);
/* 429 */         playClick();
/* 430 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/* 434 */     return super.method_25402(mouseX, mouseY, button);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean method_25406(double mouseX, double mouseY, int button) {
/* 439 */     if (this.showingDetail && this.detailPanel != null) {
/* 440 */       return this.detailPanel.mouseReleased(mouseX, mouseY, button);
/*     */     }
/* 442 */     return super.method_25406(mouseX, mouseY, button);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean method_25403(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
/* 447 */     if (this.showingDetail && this.detailPanel != null) {
/* 448 */       return this.detailPanel.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
/*     */     }
/* 450 */     return super.method_25403(mouseX, mouseY, button, deltaX, deltaY);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean method_25401(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
/* 455 */     if (this.showingDetail && this.detailPanel != null) {
/* 456 */       return this.detailPanel.mouseScrolled(mouseX, mouseY, verticalAmount);
/*     */     }
/* 458 */     return super.method_25401(mouseX, mouseY, horizontalAmount, verticalAmount);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void triggerSearch() {
/* 466 */     this.currentPage = 1;
/* 467 */     if (this.searchText.length() >= 2) {
/*     */ 
/*     */       
/* 470 */       AuctionClientData.getInstance().setPriceLookupKeysLoading(true);
/* 471 */       AuctionNetworkHelper.sendToServer((class_8710)new AuctionNetwork.RequestPriceLookupKeysPayload(this.searchText));
/* 472 */     } else if (this.searchText.length() < 2) {
/*     */       
/* 474 */       AuctionClientData.getInstance().updatePriceLookupKeysFromSync(new AuctionNetwork.SyncPriceLookupKeysPayload(List.of()));
/*     */     } 
/*     */   }
/*     */   
/*     */   private void openDetail(AuctionNetwork.PriceLookupKeyEntry entry) {
/* 479 */     AuctionClientData data = AuctionClientData.getInstance();
/* 480 */     data.setPriceLookupLoading(true);
/*     */ 
/*     */ 
/*     */     
/* 484 */     String speciesId = "";
/* 485 */     String itemType = "ITEMS";
/* 486 */     String key = entry.filterKey();
/* 487 */     if (key.startsWith("cobblemon:") && !key.contains("#")) {
/* 488 */       speciesId = key;
/* 489 */       itemType = "POKEMON";
/* 490 */     } else if (key.startsWith("plushie:")) {
/* 491 */       speciesId = key.replace("plushie:", "");
/* 492 */       itemType = "ITEMS";
/*     */     } 
/*     */ 
/*     */     
/* 496 */     class_1799 displayStack = (entry.displayStack() != null) ? entry.displayStack() : class_1799.field_8037;
/*     */ 
/*     */ 
/*     */     
/* 500 */     AuctionNetwork.AuctionListingEntry syntheticListing = new AuctionNetwork.AuctionListingEntry("", entry.displayName(), "", 0.0D, "BUY_NOW", itemType, 0, 1, displayStack, speciesId, 0L, 0);
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
/* 513 */     data.setSelectedListing(syntheticListing);
/* 514 */     this.detailPanel = new AuctionDetailPanel(this.guiLeft, this.guiTop, 450, 350, this::closeDetail, this::playClick);
/* 515 */     this.detailPanel.setPriceLookupMode(true);
/* 516 */     this.detailPanel.init(syntheticListing);
/* 517 */     this.showingDetail = true;
/*     */     
/* 519 */     AuctionNetworkHelper.sendToServer((class_8710)new AuctionNetwork.RequestPriceLookupPayload(entry.filterKey()));
/*     */   }
/*     */   
/*     */   private void closeDetail() {
/* 523 */     this.showingDetail = false;
/* 524 */     this.detailPanel = null;
/*     */   }
/*     */   
/*     */   private void playClick() {
/* 528 */     if (this.field_22787 != null)
/* 529 */       this.field_22787.method_1483().method_4873((class_1113)class_1109.method_4758((class_3414)class_3417.field_15015.comp_349(), 1.0F)); 
/*     */   }
/*     */   
/*     */   private void drawTex(class_332 ctx, class_2960 tex, int x, int y, int w, int h) {
/* 533 */     GuiUtilsKt.blitk(ctx.method_51448(), tex, Integer.valueOf(x), Integer.valueOf(y), Integer.valueOf(h), Integer.valueOf(w), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(w), Integer.valueOf(h), Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(1), Float.valueOf(1.0F), true, 1.0F);
/*     */   }
/*     */   
/*     */   private boolean drawTexturedWidget(class_332 ctx, WidgetTexture widget, int x, int y, int mouseX, int mouseY) {
/* 537 */     boolean hovered = (mouseX >= x && mouseX < x + widget.width() && mouseY >= y && mouseY < y + widget.height());
/* 538 */     drawTex(ctx, hovered ? widget.hover() : widget.normal(), x, y, widget.width(), widget.height());
/* 539 */     return hovered;
/*     */   }
/*     */   
/*     */   private class_5250 auctionText(String text) {
/* 543 */     return class_2561.method_43470(text).method_27696(class_2583.field_24360.method_27704(AUCTION_FONT).method_10978(Boolean.valueOf(false)));
/*     */   }
/*     */   
/*     */   private void drawButtonLabel(class_332 ctx, String text, int x, int y, int w, int h, boolean hovered) {
/* 547 */     drawButtonLabel(ctx, text, x, y, w, h, hovered, false);
/*     */   }
/*     */   
/*     */   private void drawButtonLabel(class_332 ctx, String text, int x, int y, int w, int h, boolean hovered, boolean centered) {
/* 551 */     ctx.method_51448().method_22903();
/* 552 */     ctx.method_51448().method_46416(0.0F, 0.0F, 50.0F);
/* 553 */     class_5250 label = auctionText(text);
/* 554 */     int textW = this.field_22793.method_27525((class_5348)label);
/* 555 */     float textX = centered ? (x + (w - textW) / 2.0F) : (x + 5);
/* 556 */     drawCobblemonText(ctx, label, textX, (y + 2), 1.0F, TEXT_PRIMARY, false, w);
/* 557 */     ctx.method_51448().method_22909();
/*     */   }
/*     */   
/*     */   private void drawCobblemonText(class_332 ctx, class_5250 text, float x, float y, float scale, int color, boolean centered, int maxWidth) {
/* 561 */     class_5250 styled = text.method_27661().method_27696(class_2583.field_24360.method_27704(AUCTION_FONT).method_10978(Boolean.valueOf(false)));
/* 562 */     int width = this.field_22793.method_27525((class_5348)styled);
/* 563 */     float drawX = centered ? (x - width * scale / 2.0F) : x;
/*     */     
/* 565 */     ctx.method_51448().method_22903();
/* 566 */     ctx.method_51448().method_46416(drawX, y, 0.0F);
/* 567 */     ctx.method_51448().method_22905(scale, scale, 1.0F);
/* 568 */     ctx.method_51439(this.field_22793, (class_2561)styled, 0, 0, color, false);
/* 569 */     ctx.method_51448().method_22909();
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\auction\screen\PriceLookupScreen.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */