/*     */ package com.atlas.common.fabric.shop.screen;
/*     */ import com.atlas.common.fabric.shop.data.ShopClientData;
/*     */ import com.atlas.common.fabric.shop.network.ShopNetwork;
/*     */ import com.cobblemon.mod.common.api.gui.GuiUtilsKt;
/*     */ import com.cobblemon.mod.common.client.render.RenderHelperKt;
/*     */ import java.text.NumberFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import net.minecraft.class_1661;
/*     */ import net.minecraft.class_1799;
/*     */ import net.minecraft.class_1935;
/*     */ import net.minecraft.class_2561;
/*     */ import net.minecraft.class_2583;
/*     */ import net.minecraft.class_2960;
/*     */ import net.minecraft.class_310;
/*     */ import net.minecraft.class_332;
/*     */ import net.minecraft.class_3417;
/*     */ import net.minecraft.class_4587;
/*     */ import net.minecraft.class_5250;
/*     */ import net.minecraft.class_5348;
/*     */ import net.minecraft.class_6880;
/*     */ import net.minecraft.class_746;
/*     */ import net.minecraft.class_7923;
/*     */ 
/*     */ public class PokeMartScreen extends class_437 {
/*     */   private static final String NS = "atlas-common-fabric";
/*     */   
/*     */   private static class_2960 tex(String p) {
/*  30 */     return class_2960.method_60655("atlas-common-fabric", "textures/shop/" + p);
/*     */   }
/*  32 */   private static final class_2960 TEX_BG = tex("bg_idle.png");
/*  33 */   private static final class_2960 TEX_BG_DIM = tex("bg_dim.png");
/*  34 */   private static final class_2960 TEX_BALANCE = tex("balance_input.png");
/*  35 */   private static final class_2960 TEX_CLOSE_IDLE = tex("btn_close_idle.png");
/*  36 */   private static final class_2960 TEX_CLOSE_HOVER = tex("btn_close_hover.png");
/*  37 */   private static final class_2960 TEX_ITEM_IDLE = tex("content_bg_idle.png");
/*  38 */   private static final class_2960 TEX_ITEM_HOVER = tex("content_bg_hover.png");
/*  39 */   private static final class_2960 TEX_SCROLL_ACTIVE = tex("scrollbar_active.png");
/*  40 */   private static final class_2960 TEX_SCROLL_INACTIVE = tex("scrollbar_inactive.png");
/*     */   
/*  42 */   private static final class_2960 TEX_CAT_IDLE = tex("sidebar/category_btn_idle.png");
/*  43 */   private static final class_2960 TEX_CAT_HOVER = tex("sidebar/category_btn_hover.png");
/*  44 */   private static final class_2960 TEX_CAT_ACTIVE = tex("sidebar/category_btn_active.png");
/*     */   
/*  46 */   private static final class_2960 TEX_SEARCH_IDLE = tex("search/bg_searchbar_idle.png");
/*  47 */   private static final class_2960 TEX_SEARCH_HOVER = tex("search/bg_searchbar_hover.png");
/*     */   
/*  49 */   private static final class_2960 TEX_CONFIRM_BUY_BG = tex("category/confirmation_buy_bg.png");
/*  50 */   private static final class_2960 TEX_CONFIRM_SELL_BG = tex("category/confirmation_sell_bg.png");
/*  51 */   private static final class_2960 TEX_CONFIRM_BTN_OK = tex("category/confirmation_btn_confirm.png");
/*  52 */   private static final class_2960 TEX_CONFIRM_BTN_OK_HOVER = tex("category/confirmation_btn_confirm_hover.png");
/*  53 */   private static final class_2960 TEX_CONFIRM_BTN_NO = tex("category/confirmation_btn_cancel.png");
/*  54 */   private static final class_2960 TEX_CONFIRM_BTN_NO_HOVER = tex("category/confirmation_btn_cancel_hover.png");
/*  55 */   private static final class_2960 TEX_CONFIRM_INPUT = tex("category/confirmation_input.png");
/*  56 */   private static final class_2960 TEX_BTN_1_PLUS = tex("category/confirmation_btn_1_plus.png");
/*  57 */   private static final class_2960 TEX_BTN_1_PLUS_HOVER = tex("category/confirmation_btn_1_plus_hover.png");
/*  58 */   private static final class_2960 TEX_BTN_1_MINUS = tex("category/confirmation_btn_1_minus.png");
/*  59 */   private static final class_2960 TEX_BTN_1_MINUS_HOVER = tex("category/confirmation_btn_1_minus_hover.png");
/*  60 */   private static final class_2960 TEX_BTN_16_PLUS = tex("category/confirmation_btn_16_plus.png");
/*  61 */   private static final class_2960 TEX_BTN_16_PLUS_HOVER = tex("category/confirmation_btn_16_plus_hover.png");
/*  62 */   private static final class_2960 TEX_BTN_16_MINUS = tex("category/confirmation_btn_16_minus.png");
/*  63 */   private static final class_2960 TEX_BTN_16_MINUS_HOVER = tex("category/confirmation_btn_16_minus_hover.png");
/*  64 */   private static final class_2960 TEX_BTN_64_PLUS = tex("category/confirmation_btn_64_plus.png");
/*  65 */   private static final class_2960 TEX_BTN_64_PLUS_HOVER = tex("category/confirmation_btn_64_plus_hover.png");
/*  66 */   private static final class_2960 TEX_BTN_64_MINUS = tex("category/confirmation_btn_64_minus.png");
/*  67 */   private static final class_2960 TEX_BTN_64_MINUS_HOVER = tex("category/confirmation_btn_64_minus_hover.png");
/*  68 */   private static final class_2960 TEX_BTN_FILL = tex("category/confirmation_btn_fill.png"); private static final int GUI_W = 599; private static final int GUI_H = 371; private static final int FRAME_X = 52; private static final int FRAME_Y = 8; private static final int CAT_W = 69; private static final int CAT_H = 39; private static final int CAT_HOVER_W = 71; private static final int CAT_HOVER_H = 41; private static final int ITEM_W = 55; private static final int ITEM_H = 69; private static final int ITEM_IDLE_W = 51; private static final int ITEM_IDLE_H = 65; private static final int CLOSE_W = 60; private static final int CLOSE_H = 20; private static final int SCROLL_W = 10; private static final int SCROLL_H = 281; private static final int BALANCE_W = 103; private static final int BALANCE_H = 20; private static final int SEARCH_W = 145; private static final int SEARCH_H = 21; private static final int CONFIRM_W = 214; private static final int CONFIRM_H = 174; private static final int INC_W = 20; private static final int INC_H = 20; private static final int CBTN_W = 65; private static final int CBTN_H = 20; private static final int INPUT_W = 52;
/*  69 */   private static final class_2960 TEX_BTN_FILL_HOVER = tex("category/confirmation_btn_fill_hover.png");
/*     */   
/*     */   private static final int INPUT_H = 20;
/*     */   
/*     */   private static final int FILL_W = 32;
/*     */   
/*     */   private static final int FILL_H = 20;
/*     */   
/*     */   private static final int MAX_QUANTITY = 5000;
/*     */   
/*     */   private static final int CAT_X = 60;
/*     */   
/*     */   private static final int CAT_Y = 45;
/*     */   
/*     */   private static final int CAT_GAP = 2;
/*     */   
/*     */   private static final int MAX_VISIBLE_CATS = 7;
/*     */   
/*     */   private static final int GRID_COLS = 7;
/*     */   
/*     */   private static final int GRID_ROWS_VISIBLE = 4;
/*     */   
/*     */   private static final int GRID_X = 136;
/*     */   
/*     */   private static final int GRID_Y = 46;
/*     */   
/*     */   private static final int GRID_COL_GAP = 2;
/*     */   private static final int GRID_ROW_GAP = 2;
/*     */   private static final int GRID_BOTTOM = 328;
/*     */   private static final int NAME_INSET = 7;
/*     */   private static final int SCROLL_X = 538;
/*     */   private static final int SCROLL_Y = 40;
/*     */   private static final int CLOSE_X = 502;
/*     */   private static final int CLOSE_Y = 8;
/*     */   private static final int BALANCE_X = 441;
/*     */   private static final int BALANCE_Y = 339;
/*     */   private static final int SEARCH_X = 160;
/*     */   private static final int SEARCH_Y = 337;
/*     */   private int guiLeft;
/*     */   private int guiTop;
/* 109 */   private int hoveredCategoryIndex = -1; private int hoveredItemIndex = -1;
/*     */   private boolean hoveringClose = false;
/*     */   private boolean hoveringSearch = false;
/* 112 */   private int categoryScrollOffset = 0;
/* 113 */   private float itemScrollOffset = 0.0F;
/*     */   private boolean draggingScrollbar = false;
/* 115 */   private float dragOffset = 0.0F;
/*     */   
/* 117 */   private int lastHoveredItemIndex = -1;
/* 118 */   private long hoverStartTime = 0L;
/*     */   
/*     */   private boolean searchActive = false;
/*     */   
/* 122 */   private String searchText = "";
/*     */   
/*     */   private boolean showConfirmation = false;
/*     */   
/* 126 */   private ShopNetwork.ShopItemEntry confirmItem = null;
/* 127 */   private int confirmQuantity = 1;
/*     */   private boolean isSellMode = false;
/* 129 */   private int hoveredConfirmButton = -1;
/*     */   
/*     */   private boolean quantityInputActive = false;
/*     */   
/* 133 */   private String quantityInputText = "1";
/*     */ 
/*     */   
/* 136 */   private static final NumberFormat NUM_FMT = NumberFormat.getInstance(Locale.US);
/*     */ 
/*     */   
/* 139 */   private static final NumberFormat DECIMAL_FMT = NumberFormat.getInstance(Locale.US); static {
/* 140 */     DECIMAL_FMT.setMaximumFractionDigits(2);
/* 141 */     DECIMAL_FMT.setMinimumFractionDigits(0);
/*     */   }
/*     */   
/*     */   private static String formatDuration(long seconds) {
/* 145 */     if (seconds <= 0L) return "permanent"; 
/* 146 */     long d = seconds / 86400L; seconds %= 86400L;
/* 147 */     long h = seconds / 3600L; seconds %= 3600L;
/* 148 */     long m = seconds / 60L, s = seconds % 60L;
/* 149 */     StringBuilder sb = new StringBuilder();
/* 150 */     if (d > 0L) sb.append(d).append("d "); 
/* 151 */     if (h > 0L) sb.append(h).append("h "); 
/* 152 */     if (m > 0L) sb.append(m).append("m "); 
/* 153 */     if (s > 0L && d == 0L && h == 0L) sb.append(s).append("s "); 
/* 154 */     String out = sb.toString().trim();
/* 155 */     return out.isEmpty() ? "permanent" : ("per " + out);
/*     */   }
/*     */   
/*     */   private static String fmtPrice(double v) {
/* 159 */     if (v == Math.floor(v) && !Double.isInfinite(v)) return NUM_FMT.format((long)v); 
/* 160 */     return DECIMAL_FMT.format(v);
/*     */   }
/*     */   
/* 163 */   private static final class_2960 MISC_FONT = class_2960.method_60655("minecraft", "miscellaneous"); private static final String COIN_CHAR = ""; private static final String MOUSE_LEFT_CHAR = ""; private static final String MOUSE_RIGHT_CHAR = "";
/*     */   private static final int CLICK_INFO_COLOR = -6056193;
/*     */   private static final int Q_ROW_Y = 105;
/*     */   private static final int CONFIRM_BTN_Y = 136;
/*     */   
/*     */   public PokeMartScreen() {
/* 169 */     super((class_2561)class_2561.method_43470("PokeMart"));
/*     */   }
/*     */   
/*     */   protected void method_25426() {
/* 173 */     super.method_25426();
/* 174 */     this.guiLeft = (this.field_22789 - 599) / 2;
/* 175 */     this.guiTop = (this.field_22790 - 371) / 2;
/*     */   }
/*     */   public boolean method_25421() {
/* 178 */     return false;
/*     */   }
/*     */   
/*     */   private void drawTex(class_332 ctx, class_2960 tex, int x, int y, int w, int h) {
/* 182 */     GuiUtilsKt.blitk(ctx.method_51448(), tex, Integer.valueOf(x), Integer.valueOf(y), Integer.valueOf(h), Integer.valueOf(w), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(w), Integer.valueOf(h), Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(1), Float.valueOf(1.0F), true, 1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private List<ShopNetwork.ShopItemEntry> getFilteredItems() {
/* 189 */     List<ShopNetwork.ShopItemEntry> all = ShopClientData.getInstance().getCurrentItems();
/* 190 */     if (this.searchText.isEmpty()) return all; 
/* 191 */     String lower = this.searchText.toLowerCase(Locale.ROOT);
/* 192 */     List<ShopNetwork.ShopItemEntry> filtered = new ArrayList<>();
/* 193 */     for (ShopNetwork.ShopItemEntry item : all) {
/* 194 */       if (item.name().toLowerCase(Locale.ROOT).contains(lower)) {
/* 195 */         filtered.add(item);
/*     */       }
/*     */     } 
/* 198 */     return filtered;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int getAvailableSlots(class_1661 inventory, class_1799 itemStack) {
/* 203 */     int stackableAmount = 0;
/* 204 */     for (int i = 0; i < 36; i++) {
/* 205 */       class_1799 slotStack = inventory.method_5438(i);
/* 206 */       if (slotStack.method_7960()) {
/* 207 */         stackableAmount += itemStack.method_7914();
/* 208 */       } else if (class_1799.method_31577(slotStack, itemStack)) {
/* 209 */         stackableAmount += itemStack.method_7914() - slotStack.method_7947();
/*     */       } 
/*     */     } 
/* 212 */     return stackableAmount;
/*     */   }
/*     */   
/*     */   private static int getAmount(class_1661 inventory, class_1799 itemStack) {
/* 216 */     int amount = 0;
/* 217 */     for (int i = 0; i < 36; i++) {
/* 218 */       class_1799 slotStack = inventory.method_5438(i);
/* 219 */       if (class_1799.method_31577(slotStack, itemStack)) {
/* 220 */         amount += slotStack.method_7947();
/*     */       }
/*     */     } 
/* 223 */     return amount;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void method_25394(class_332 ctx, int mouseX, int mouseY, float delta) {
/* 229 */     ctx.method_25294(0, 0, this.field_22789, this.field_22790, -2147483648);
/*     */     
/* 231 */     int relX = mouseX - this.guiLeft, relY = mouseY - this.guiTop;
/* 232 */     updateHoverStates(relX, relY);
/*     */     
/* 234 */     drawTex(ctx, TEX_BG, this.guiLeft, this.guiTop, 599, 371);
/*     */     
/* 236 */     drawCategories(ctx);
/*     */     
/* 238 */     ctx.method_44379(this.guiLeft + 136, this.guiTop + 46, this.guiLeft + 136 + 385 + 12, this.guiTop + 328);
/*     */ 
/*     */     
/* 241 */     drawItemsGrid(ctx);
/* 242 */     ctx.method_44380();
/*     */     
/* 244 */     drawScrollBar(ctx);
/*     */     
/* 246 */     drawTex(ctx, this.hoveringClose ? TEX_CLOSE_HOVER : TEX_CLOSE_IDLE, this.guiLeft + 502, this.guiTop + 8, 60, 20);
/*     */ 
/*     */ 
/*     */     
/* 250 */     drawSearchBar(ctx);
/*     */ 
/*     */     
/* 253 */     drawBalanceWithCoin(ctx, (this.guiLeft + 441) + 51.5F, (this.guiTop + 339 + 3), -281343);
/*     */ 
/*     */     
/* 256 */     if (this.showConfirmation) {
/* 257 */       class_4587 pm = ctx.method_51448(); pm.method_22903(); pm.method_46416(0.0F, 0.0F, 400.0F);
/* 258 */       drawConfirmationPopup(ctx, mouseX, mouseY);
/* 259 */       pm.method_22909();
/*     */     } 
/*     */     
/* 262 */     if (!this.showConfirmation && this.hoveredItemIndex >= 0) {
/* 263 */       class_4587 tm = ctx.method_51448(); tm.method_22903(); tm.method_46416(0.0F, 0.0F, 600.0F);
/* 264 */       drawItemTooltip(ctx, mouseX, mouseY);
/* 265 */       tm.method_22909();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void drawSearchBar(class_332 ctx) {
/* 271 */     int sx = this.guiLeft + 160, sy = this.guiTop + 337;
/* 272 */     drawTex(ctx, (this.searchActive || this.hoveringSearch) ? TEX_SEARCH_HOVER : TEX_SEARCH_IDLE, sx, sy, 145, 21);
/*     */ 
/*     */     
/* 275 */     String displayText = this.searchText;
/* 276 */     if (this.searchActive && System.currentTimeMillis() / 500L % 2L == 0L) {
/* 277 */       displayText = this.searchText + "_";
/*     */     }
/* 279 */     if (!displayText.isEmpty()) {
/* 280 */       ctx.method_25303(this.field_22793, displayText, sx + 31, sy + 6, -1);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void drawItemTooltip(class_332 ctx, int mouseX, int mouseY) {
/* 286 */     List<ShopNetwork.ShopItemEntry> items = getFilteredItems();
/* 287 */     if (this.hoveredItemIndex >= items.size())
/* 288 */       return;  ShopNetwork.ShopItemEntry item = items.get(this.hoveredItemIndex);
/*     */     
/* 290 */     class_2583 silver = class_2583.field_24360.method_36139(-4144960);
/* 291 */     class_2583 gray = class_2583.field_24360.method_36139(-5592406);
/* 292 */     class_2583 gold = class_2583.field_24360.method_36139(-281343);
/* 293 */     class_2583 red = class_2583.field_24360.method_36139(-43691);
/* 294 */     class_2583 lime = class_2583.field_24360.method_36139(-11141291);
/* 295 */     class_2583 clickInfo = class_2583.field_24360.method_36139(-6056193);
/* 296 */     class_2583 mouseGlyph = class_2583.field_24360.method_27704(MISC_FONT).method_36139(-6056193);
/*     */     
/* 298 */     List<class_2561> lines = new ArrayList<>();
/* 299 */     lines.add(class_2561.method_43470(item.name()).method_10862(class_2583.field_24360.method_36139(-1).method_10982(Boolean.valueOf(true))));
/* 300 */     lines.add(class_2561.method_43470(""));
/* 301 */     lines.add(class_2561.method_43470("Prices").method_10862(class_2583.field_24360.method_36139(-1).method_10982(Boolean.valueOf(true))));
/* 302 */     lines.add(class_2561.method_43470(" ◆").method_10862(gold)
/* 303 */         .method_10852((class_2561)class_2561.method_43470(" Buy: ").method_10862(gray))
/* 304 */         .method_10852((class_2561)class_2561.method_43470(fmtPrice(item.buyPrice()) + " PokeCoins").method_10862(gold)));
/* 305 */     if (item.sellPrice() > 0.0D) {
/* 306 */       lines.add(class_2561.method_43470(" ◆").method_10862(gold)
/* 307 */           .method_10852((class_2561)class_2561.method_43470(" Sell: ").method_10862(gray))
/* 308 */           .method_10852((class_2561)class_2561.method_43470(fmtPrice(item.sellPrice()) + " PokeCoins").method_10862(gold)));
/*     */     } else {
/* 310 */       lines.add(class_2561.method_43470(" ◆").method_10862(gold)
/* 311 */           .method_10852((class_2561)class_2561.method_43470(" Sell: ").method_10862(gray))
/* 312 */           .method_10852((class_2561)class_2561.method_43470("Not sellable").method_10862(red)));
/*     */     } 
/* 314 */     if (item.stockRemaining() >= 0) {
/* 315 */       lines.add(class_2561.method_43470(""));
/* 316 */       lines.add(class_2561.method_43470("Available Stock").method_10862(silver.method_10982(Boolean.valueOf(true))));
/* 317 */       int stock = item.stockRemaining();
/* 318 */       lines.add(class_2561.method_43470(" ◆ ").method_10862(gold)
/* 319 */           .method_10852((class_2561)class_2561.method_43470(NUM_FMT.format(stock)).method_10862((stock > 0) ? lime : red)));
/*     */     } 
/*     */     
/* 322 */     if (item.playerBuyLimit() > 0 || item.playerSellLimit() > 0) {
/* 323 */       lines.add(class_2561.method_43470(""));
/* 324 */       String durStr = formatDuration(item.limitDurationSeconds());
/* 325 */       lines.add(class_2561.method_43470("Your Limits").method_10862(silver.method_10982(Boolean.valueOf(true)))
/* 326 */           .method_10852((class_2561)class_2561.method_43470(" (" + durStr + ")").method_10862(gray)));
/* 327 */       if (item.playerBuyLimit() > 0) {
/* 328 */         int rem = Math.max(0, item.playerBuyRemaining());
/* 329 */         lines.add(class_2561.method_43470(" ◆").method_10862(gold)
/* 330 */             .method_10852((class_2561)class_2561.method_43470(" Buy: ").method_10862(gray))
/* 331 */             .method_10852((class_2561)class_2561.method_43470(NUM_FMT.format(rem) + " / " + NUM_FMT.format(rem))
/* 332 */               .method_10862((rem > 0) ? lime : red)));
/*     */       } 
/* 334 */       if (item.playerSellLimit() > 0) {
/* 335 */         int rem = Math.max(0, item.playerSellRemaining());
/* 336 */         lines.add(class_2561.method_43470(" ◆").method_10862(gold)
/* 337 */             .method_10852((class_2561)class_2561.method_43470(" Sell: ").method_10862(gray))
/* 338 */             .method_10852((class_2561)class_2561.method_43470(NUM_FMT.format(rem) + " / " + NUM_FMT.format(rem))
/* 339 */               .method_10862((rem > 0) ? lime : red)));
/*     */       } 
/*     */     } 
/* 342 */     lines.add(class_2561.method_43470(""));
/* 343 */     class_2960 defaultFont = class_2960.method_60655("minecraft", "default");
/* 344 */     class_2583 clickInfoDefaultFont = clickInfo.method_27704(defaultFont);
/* 345 */     lines.add(class_2561.method_43470("").method_10862(mouseGlyph)
/* 346 */         .method_10852((class_2561)class_2561.method_43470(" Left-click to buy!").method_10862(clickInfoDefaultFont)));
/* 347 */     if (item.sellPrice() > 0.0D) {
/* 348 */       lines.add(class_2561.method_43470("").method_10862(mouseGlyph)
/* 349 */           .method_10852((class_2561)class_2561.method_43470(" Right-click to sell!").method_10862(clickInfoDefaultFont)));
/*     */     }
/* 351 */     ctx.method_51434(this.field_22793, lines, mouseX, mouseY);
/*     */   }
/*     */ 
/*     */   
/*     */   private void drawCategories(class_332 ctx) {
/* 356 */     ShopClientData data = ShopClientData.getInstance();
/* 357 */     List<ShopNetwork.ShopCategoryEntry> cats = data.getCategories();
/* 358 */     String selId = data.getSelectedCategoryId();
/* 359 */     int start = this.categoryScrollOffset;
/* 360 */     int end = Math.min(cats.size(), start + 7);
/*     */     
/* 362 */     for (int i = start; i < end; i++) {
/* 363 */       ShopNetwork.ShopCategoryEntry cat = cats.get(i);
/* 364 */       int idx = i - start;
/* 365 */       int x = this.guiLeft + 60;
/* 366 */       int y = this.guiTop + 45 + idx * 41;
/*     */ 
/*     */       
/* 369 */       class_2960 btnTex = cat.id().equals(selId) ? TEX_CAT_ACTIVE : ((this.hoveredCategoryIndex == i) ? TEX_CAT_HOVER : TEX_CAT_IDLE);
/* 370 */       if (this.hoveredCategoryIndex == i && !cat.id().equals(selId)) {
/* 371 */         drawTex(ctx, btnTex, x - 1, y - 1, 71, 41);
/*     */       } else {
/* 373 */         drawTex(ctx, btnTex, x, y, 69, 39);
/*     */       } 
/*     */       
/*     */       try {
/* 377 */         class_2960 iconId = class_2960.method_12829(cat.iconItemId());
/* 378 */         if (iconId != null) {
/* 379 */           class_4587 m = ctx.method_51448(); m.method_22903(); m.method_46416(0.0F, 0.0F, 100.0F);
/* 380 */           ctx.method_51427(new class_1799((class_1935)class_7923.field_41178.method_10223(iconId)), x + 34 - 8, y + 6);
/* 381 */           m.method_22909();
/*     */         } 
/* 383 */       } catch (Exception exception) {}
/*     */       
/* 385 */       ctx.method_25300(this.field_22793, cat.name(), x + 34, y + 39 - 14, -1);
/*     */     } 
/*     */     
/* 388 */     if (cats.size() > 7) {
/* 389 */       int arrowX = this.guiLeft + 60 + 34;
/* 390 */       if (this.categoryScrollOffset > 0)
/* 391 */         ctx.method_25303(this.field_22793, "▲", arrowX - 3, this.guiTop + 45 - 8, -5592406); 
/* 392 */       if (this.categoryScrollOffset + 7 < cats.size()) {
/* 393 */         int bottomY = this.guiTop + 45 + 287;
/* 394 */         ctx.method_25303(this.field_22793, "▼", arrowX - 3, bottomY, -5592406);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private int getTotalContentHeight() {
/* 401 */     int rows = (int)Math.ceil(getFilteredItems().size() / 7.0D);
/* 402 */     return rows * 71;
/*     */   }
/*     */   private int getVisibleHeight() {
/* 405 */     return 282;
/*     */   } private float getMaxScroll() {
/* 407 */     return Math.max(0, getTotalContentHeight() - getVisibleHeight());
/*     */   }
/*     */   private void drawItemsGrid(class_332 ctx) {
/* 410 */     List<ShopNetwork.ShopItemEntry> items = getFilteredItems();
/* 411 */     int nameMaxW = 41;
/*     */     
/* 413 */     for (int i = 0; i < items.size(); i++) {
/* 414 */       ShopNetwork.ShopItemEntry item = items.get(i);
/* 415 */       int col = i % 7, row = i / 7;
/* 416 */       int x = this.guiLeft + 136 + col * 57;
/* 417 */       int y = this.guiTop + 46 + row * 71 - (int)this.itemScrollOffset;
/* 418 */       if (y + 69 >= this.guiTop + 46 && y <= this.guiTop + 328) {
/*     */         
/* 420 */         if (this.hoveredItemIndex == i) {
/* 421 */           drawTex(ctx, TEX_ITEM_HOVER, x, y, 55, 69);
/*     */         } else {
/* 423 */           drawTex(ctx, TEX_ITEM_IDLE, x + 2, y + 2, 51, 65);
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 431 */         class_4587 m = ctx.method_51448(); m.method_22903(); m.method_46416(0.0F, 0.0F, 100.0F);
/* 432 */         float ico = 1.25F;
/* 433 */         float iconPx = 16.0F * ico;
/* 434 */         int iconTopH = 48;
/* 435 */         m.method_46416(x + (55.0F - iconPx) / 2.0F, y + (iconTopH - iconPx) / 2.0F, 0.0F);
/* 436 */         m.method_22905(ico, ico, 1.0F);
/* 437 */         ctx.method_51427(item.displayStack(), 0, 0);
/* 438 */         m.method_22909();
/*     */ 
/*     */         
/* 441 */         int nameY = y + 69 - 22;
/* 442 */         int nameTextW = (int)(this.field_22793.method_1727(item.name()) * 0.5F);
/* 443 */         int scrollOff = 0;
/*     */         
/* 445 */         if (nameTextW > nameMaxW && this.hoveredItemIndex == i) {
/* 446 */           if (this.lastHoveredItemIndex != i) {
/* 447 */             this.lastHoveredItemIndex = i;
/* 448 */             this.hoverStartTime = System.currentTimeMillis();
/*     */           } 
/* 450 */           long elapsed = System.currentTimeMillis() - this.hoverStartTime;
/* 451 */           int overflow = nameTextW - nameMaxW;
/* 452 */           int scrollDurationMs = Math.max(overflow * 80, 800);
/* 453 */           int pauseMs = 400;
/* 454 */           long totalCycleMs = (scrollDurationMs + pauseMs * 2);
/* 455 */           long phase = elapsed % totalCycleMs;
/* 456 */           if (phase < pauseMs) { scrollOff = 0; }
/* 457 */           else if (phase < (pauseMs + scrollDurationMs))
/* 458 */           { scrollOff = (int)((phase - pauseMs) * overflow / scrollDurationMs); }
/* 459 */           else { scrollOff = overflow; } 
/* 460 */         } else if (this.hoveredItemIndex != i && this.lastHoveredItemIndex == i) {
/* 461 */           this.lastHoveredItemIndex = -1;
/*     */         } 
/*     */         
/* 464 */         if (nameTextW > nameMaxW) {
/* 465 */           ctx.method_44379(x + 7, nameY, x + 55 - 7, nameY + 8);
/* 466 */           drawSmallText(ctx, item.name(), (x + 7 - scrollOff), nameY, -1, false);
/* 467 */           ctx.method_44380();
/*     */         } else {
/* 469 */           drawSmallText(ctx, item.name(), x + 27.5F, nameY, -1, true);
/*     */         } 
/*     */         
/* 472 */         drawNumberWithCoin(ctx, fmtPrice(item.buyPrice()), x + 27.5F, (y + 69 - 12), 0.5F, -281343, true);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void drawScrollBar(class_332 ctx) {
/* 479 */     float maxScroll = getMaxScroll();
/* 480 */     int tX = this.guiLeft + 538, tY = this.guiTop + 40;
/*     */     
/* 482 */     drawTex(ctx, TEX_SCROLL_INACTIVE, tX, tY, 10, 281);
/*     */     
/* 484 */     if (maxScroll <= 0.0F)
/*     */       return; 
/* 486 */     int thumbH = 40;
/* 487 */     int thumbY = tY + (int)(this.itemScrollOffset / maxScroll * 241.0F);
/* 488 */     ctx.method_25293(TEX_SCROLL_ACTIVE, tX, thumbY, 10, 40, 0.0F, 0.0F, 10, 40, 10, 281);
/*     */   }
/*     */   
/*     */   private int popupLeft()
/*     */   {
/* 493 */     return (this.field_22789 - 214) / 2 + 10; } private int popupTop() {
/* 494 */     return (this.field_22790 - 174) / 2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int[] qButtonXs() {
/* 502 */     int BTN_GAP = 2;
/* 503 */     int INPUT_GAP = 7;
/* 504 */     int totalW = 194;
/* 505 */     int left = (214 - totalW) / 2;
/* 506 */     int x0 = left;
/* 507 */     int x1 = x0 + 20 + 2;
/* 508 */     int x2 = x1 + 20 + 2;
/* 509 */     int x3 = x2 + 20 + 7;
/* 510 */     int x4 = x3 + 52 + 7;
/* 511 */     int x5 = x4 + 20 + 2;
/* 512 */     int x6 = x5 + 20 + 2;
/* 513 */     return new int[] { x0, x1, x2, x3, x4, x5, x6 };
/*     */   }
/*     */   
/*     */   private void drawConfirmationPopup(class_332 ctx, int mouseX, int mouseY) {
/* 517 */     int px = popupLeft(), py = popupTop();
/* 518 */     drawTex(ctx, this.isSellMode ? TEX_CONFIRM_SELL_BG : TEX_CONFIRM_BUY_BG, px, py, 214, 174);
/* 519 */     updateConfirmHoverStates(mouseX - px, mouseY - py);
/*     */ 
/*     */     
/* 522 */     float popIco = 2.5F;
/* 523 */     float iconPx = 16.0F * popIco;
/* 524 */     int iconX = px + 20;
/* 525 */     int iconY = py + 42;
/* 526 */     class_4587 m = ctx.method_51448(); m.method_22903(); m.method_46416(0.0F, 0.0F, 200.0F);
/* 527 */     m.method_46416(iconX, iconY, 0.0F);
/* 528 */     m.method_22905(popIco, popIco, 1.0F);
/* 529 */     ctx.method_51427(this.confirmItem.displayStack(), 0, 0);
/* 530 */     m.method_22909();
/*     */ 
/*     */     
/* 533 */     int textX = iconX + (int)iconPx + 14;
/* 534 */     drawScaledVanillaText(ctx, this.confirmItem.name(), textX, (py + 48), 1.2F, -1, false);
/*     */ 
/*     */     
/* 537 */     double price = this.isSellMode ? this.confirmItem.sellPrice() : this.confirmItem.buyPrice();
/* 538 */     double total = price * this.confirmQuantity;
/* 539 */     drawScaledVanillaText(ctx, "Total cost:", textX, (py + 63), 1.0F, -1, false);
/* 540 */     int labelW = (int)(this.field_22793.method_1727("Total cost: ") * 1.0F);
/* 541 */     drawNumberWithCoin(ctx, fmtPrice(total), (textX + labelW), (py + 63), 1.0F, -281343, false);
/*     */ 
/*     */     
/* 544 */     int[] xs = qButtonXs();
/* 545 */     int qy = py + 105;
/* 546 */     drawIncBtn(ctx, TEX_BTN_64_MINUS, TEX_BTN_64_MINUS_HOVER, px + xs[0], qy, 2);
/* 547 */     drawIncBtn(ctx, TEX_BTN_16_MINUS, TEX_BTN_16_MINUS_HOVER, px + xs[1], qy, 6);
/* 548 */     drawIncBtn(ctx, TEX_BTN_1_MINUS, TEX_BTN_1_MINUS_HOVER, px + xs[2], qy, 3);
/*     */ 
/*     */     
/* 551 */     drawTex(ctx, TEX_CONFIRM_INPUT, px + xs[3], qy, 52, 20);
/* 552 */     String qtyDisplay = this.quantityInputActive ? this.quantityInputText : String.valueOf(this.confirmQuantity);
/* 553 */     if (this.quantityInputActive && System.currentTimeMillis() / 500L % 2L == 0L) {
/* 554 */       qtyDisplay = qtyDisplay + "_";
/*     */     }
/* 556 */     ctx.method_25303(this.field_22793, qtyDisplay, px + xs[3] + 26 - this.field_22793
/* 557 */         .method_1727(qtyDisplay) / 2, qy + 6, 
/* 558 */         this.quantityInputActive ? -256 : -1);
/*     */     
/* 560 */     drawIncBtn(ctx, TEX_BTN_1_PLUS, TEX_BTN_1_PLUS_HOVER, px + xs[4], qy, 4);
/* 561 */     drawIncBtn(ctx, TEX_BTN_16_PLUS, TEX_BTN_16_PLUS_HOVER, px + xs[5], qy, 7);
/* 562 */     drawIncBtn(ctx, TEX_BTN_64_PLUS, TEX_BTN_64_PLUS_HOVER, px + xs[6], qy, 5);
/*     */ 
/*     */     
/* 565 */     int btnGap = 5;
/* 566 */     int totalBtnW = 162 + btnGap * 2;
/* 567 */     int cancelX = px + (214 - totalBtnW) / 2;
/* 568 */     int fillX = cancelX + 65 + btnGap;
/* 569 */     int confirmX = fillX + 32 + btnGap;
/* 570 */     int btnY = py + 136;
/* 571 */     drawBtn(ctx, TEX_CONFIRM_BTN_NO, TEX_CONFIRM_BTN_NO_HOVER, cancelX, btnY, 65, 20, (this.hoveredConfirmButton == 1));
/* 572 */     drawBtn(ctx, TEX_BTN_FILL, TEX_BTN_FILL_HOVER, fillX, btnY, 32, 20, (this.hoveredConfirmButton == 8));
/* 573 */     drawBtn(ctx, TEX_CONFIRM_BTN_OK, TEX_CONFIRM_BTN_OK_HOVER, confirmX, btnY, 65, 20, (this.hoveredConfirmButton == 0));
/*     */ 
/*     */     
/* 576 */     ShopClientData sd = ShopClientData.getInstance();
/* 577 */     if (sd.getLastPurchaseSuccess() != null) {
/* 578 */       long elapsed = System.currentTimeMillis() - sd.getLastPurchaseResultTime();
/* 579 */       if (elapsed < 3000L) {
/* 580 */         int col = sd.getLastPurchaseSuccess().booleanValue() ? -10027162 : -39322;
/* 581 */         String msg = sd.getLastPurchaseMessage();
/* 582 */         int cx = px + 107;
/* 583 */         ctx.method_25303(this.field_22793, msg, cx - this.field_22793.method_1727(msg) / 2, py + 174 - 14, col);
/*     */       } else {
/* 585 */         sd.clearPurchaseResult();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   private void drawIncBtn(class_332 ctx, class_2960 tex, class_2960 hoverTex, int x, int y, int id) {
/* 590 */     drawBtn(ctx, tex, hoverTex, x, y, 20, 20, (this.hoveredConfirmButton == id));
/*     */   }
/*     */   
/*     */   private void drawBtn(class_332 ctx, class_2960 tex, class_2960 hoverTex, int x, int y, int w, int h, boolean hovered) {
/* 594 */     drawTex(ctx, hovered ? hoverTex : tex, x, y, w, h);
/*     */   }
/*     */ 
/*     */   
/*     */   private void drawSmallText(class_332 ctx, String text, float x, float y, int color, boolean centered) {
/* 599 */     RenderHelperKt.drawScaledText(ctx, null, 
/* 600 */         class_2561.method_43470(text).method_27661(), Float.valueOf(x), Float.valueOf(y), 0.5F, Float.valueOf(1.0F), 2147483647, color, centered, true, null, null);
/*     */   }
/*     */   
/*     */   private void drawScaledVanillaText(class_332 ctx, String text, float x, float y, float scale, int color, boolean centered) {
/* 604 */     RenderHelperKt.drawScaledText(ctx, null, 
/* 605 */         class_2561.method_43470(text).method_27661(), Float.valueOf(x), Float.valueOf(y), scale, Float.valueOf(1.0F), 2147483647, color, centered, true, null, null);
/*     */   }
/*     */   
/*     */   private void drawBalanceWithCoin(class_332 ctx, float cx, float y, int color) {
/* 609 */     String number = fmtPrice(ShopClientData.getInstance().getBalance());
/* 610 */     String numSpaced = number + " ";
/* 611 */     int numW = this.field_22793.method_1727(numSpaced);
/* 612 */     int coinW = this.field_22793.method_27525((class_5348)class_2561.method_43470("").method_10862(class_2583.field_24360.method_27704(MISC_FONT)));
/* 613 */     int totalW = numW + coinW;
/* 614 */     float startX = cx - totalW / 2.0F;
/* 615 */     RenderHelperKt.drawScaledText(ctx, null, class_2561.method_43470(numSpaced).method_27661(), 
/* 616 */         Float.valueOf(startX), Float.valueOf(y + 1.0F), 1.0F, Float.valueOf(1.0F), 2147483647, color, false, true, null, null);
/* 617 */     RenderHelperKt.drawScaledText(ctx, null, 
/* 618 */         class_2561.method_43470("").method_10862(class_2583.field_24360.method_27704(MISC_FONT).method_36139(-1)).method_27661(), 
/* 619 */         Float.valueOf(startX + numW), Float.valueOf(y), 1.0F, Float.valueOf(1.0F), 2147483647, -1, false, true, null, null);
/*     */   }
/*     */ 
/*     */   
/*     */   private void drawNumberWithCoin(class_332 ctx, String number, float x, float y, float scale, int color, boolean centered) {
/* 624 */     class_5250 combined = class_2561.method_43470(number + " ").method_10852((class_2561)class_2561.method_43470("").method_10862(class_2583.field_24360.method_27704(MISC_FONT).method_36139(-1)));
/* 625 */     int totalW = this.field_22793.method_27525((class_5348)combined);
/* 626 */     float drawX = centered ? (x - totalW * scale / 2.0F) : x;
/* 627 */     RenderHelperKt.drawScaledText(ctx, null, combined, 
/* 628 */         Float.valueOf(drawX), Float.valueOf(y), scale, Float.valueOf(1.0F), 2147483647, color, false, true, null, null);
/*     */   }
/*     */ 
/*     */   
/*     */   private void updateHoverStates(int relX, int relY) {
/* 633 */     if (this.showConfirmation) {
/* 634 */       this.hoveredCategoryIndex = -1;
/* 635 */       this.hoveredItemIndex = -1;
/* 636 */       this.hoveringClose = false;
/* 637 */       this.hoveringSearch = false;
/*     */       
/*     */       return;
/*     */     } 
/* 641 */     this.hoveredCategoryIndex = -1;
/* 642 */     List<ShopNetwork.ShopCategoryEntry> cats = ShopClientData.getInstance().getCategories();
/* 643 */     for (int i = this.categoryScrollOffset; i < Math.min(cats.size(), this.categoryScrollOffset + 7); i++) {
/* 644 */       int y = 45 + (i - this.categoryScrollOffset) * 41;
/* 645 */       if (relX >= 60 && relX < 129 && relY >= y && relY < y + 39) {
/* 646 */         this.hoveredCategoryIndex = i;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 651 */     this.hoveredItemIndex = -1;
/* 652 */     int gridRight = 533;
/* 653 */     if (relX >= 136 && relX < gridRight && relY >= 46 && relY < 328) {
/* 654 */       List<ShopNetwork.ShopItemEntry> items = getFilteredItems();
/* 655 */       for (int j = 0; j < items.size(); j++) {
/* 656 */         int col = j % 7, row = j / 7;
/* 657 */         int x = 136 + col * 57;
/* 658 */         int y = 46 + row * 71 - (int)this.itemScrollOffset;
/* 659 */         if (relX >= x && relX < x + 55 && relY >= y && relY < y + 69 && y >= 46 && y + 69 <= 328) {
/*     */           
/* 661 */           this.hoveredItemIndex = j;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/* 667 */     this.hoveringClose = (relX >= 502 && relX < 562 && relY >= 8 && relY < 28);
/* 668 */     this.hoveringSearch = (relX >= 160 && relX < 305 && relY >= 337 && relY < 358);
/*     */   }
/*     */   
/*     */   private void updateConfirmHoverStates(int rx, int ry) {
/* 672 */     this.hoveredConfirmButton = -1;
/* 673 */     int[] xs = qButtonXs();
/* 674 */     int qy = 105;
/* 675 */     if (inR(rx, ry, xs[0], qy, 20, 20)) { this.hoveredConfirmButton = 2; return; }
/* 676 */      if (inR(rx, ry, xs[1], qy, 20, 20)) { this.hoveredConfirmButton = 6; return; }
/* 677 */      if (inR(rx, ry, xs[2], qy, 20, 20)) { this.hoveredConfirmButton = 3; return; }
/* 678 */      if (inR(rx, ry, xs[3], qy, 52, 20)) { this.hoveredConfirmButton = 9; return; }
/* 679 */      if (inR(rx, ry, xs[4], qy, 20, 20)) { this.hoveredConfirmButton = 4; return; }
/* 680 */      if (inR(rx, ry, xs[5], qy, 20, 20)) { this.hoveredConfirmButton = 7; return; }
/* 681 */      if (inR(rx, ry, xs[6], qy, 20, 20)) { this.hoveredConfirmButton = 5;
/*     */       return; }
/*     */     
/* 684 */     int btnGap = 5;
/* 685 */     int totalBtnW = 162 + btnGap * 2;
/* 686 */     int cancelX = (214 - totalBtnW) / 2;
/* 687 */     int fillX = cancelX + 65 + btnGap;
/* 688 */     int confirmX = fillX + 32 + btnGap;
/* 689 */     if (inR(rx, ry, cancelX, 136, 65, 20)) { this.hoveredConfirmButton = 1; return; }
/* 690 */      if (inR(rx, ry, fillX, 136, 32, 20)) { this.hoveredConfirmButton = 8; return; }
/* 691 */      if (inR(rx, ry, confirmX, 136, 65, 20)) this.hoveredConfirmButton = 0; 
/*     */   }
/*     */   
/*     */   private boolean inR(int mx, int my, int x, int y, int w, int h) {
/* 695 */     return (mx >= x && mx < x + w && my >= y && my < y + h);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean method_25402(double mouseX, double mouseY, int button) {
/* 702 */     if (button == 1 && !this.showConfirmation && this.hoveredItemIndex >= 0) {
/* 703 */       List<ShopNetwork.ShopItemEntry> items = getFilteredItems();
/* 704 */       if (this.hoveredItemIndex < items.size()) {
/* 705 */         ShopNetwork.ShopItemEntry it = items.get(this.hoveredItemIndex);
/* 706 */         if (it.sellPrice() > 0.0D) { openConfirmation(it, true); playClick(); }
/*     */       
/* 708 */       }  return true;
/*     */     } 
/*     */     
/* 711 */     if (button != 0) return super.method_25402(mouseX, mouseY, button);
/*     */ 
/*     */     
/* 714 */     if (this.showConfirmation && this.confirmItem != null) return handleConfirmClick(mouseX, mouseY);
/*     */ 
/*     */     
/* 717 */     if (this.hoveringClose) { playClick(); method_25419(); return true; }
/*     */     
/* 719 */     int relX = (int)mouseX - this.guiLeft, relY = (int)mouseY - this.guiTop;
/*     */ 
/*     */     
/* 722 */     if (relX >= 160 && relX < 305 && relY >= 337 && relY < 358) {
/* 723 */       if (!this.searchActive) {
/* 724 */         this.searchActive = true;
/* 725 */         ShopNetwork.requestItems("*", 1);
/* 726 */         this.itemScrollOffset = 0.0F;
/*     */       } 
/* 728 */       playClick();
/* 729 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 733 */     if (this.hoveredItemIndex >= 0) {
/* 734 */       List<ShopNetwork.ShopItemEntry> items = getFilteredItems();
/* 735 */       if (this.hoveredItemIndex < items.size()) {
/* 736 */         openConfirmation(items.get(this.hoveredItemIndex), false);
/* 737 */         playClick();
/*     */       } 
/* 739 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 743 */     if (this.hoveredCategoryIndex >= 0) {
/* 744 */       ShopClientData data = ShopClientData.getInstance();
/* 745 */       List<ShopNetwork.ShopCategoryEntry> cats = data.getCategories();
/* 746 */       if (this.hoveredCategoryIndex < cats.size()) {
/* 747 */         ShopNetwork.ShopCategoryEntry cat = cats.get(this.hoveredCategoryIndex);
/* 748 */         if (!cat.id().equals(data.getSelectedCategoryId()) || this.searchActive) {
/* 749 */           data.setSelectedCategoryId(cat.id());
/* 750 */           data.setLoading(true);
/* 751 */           this.itemScrollOffset = 0.0F;
/* 752 */           this.searchActive = false;
/* 753 */           this.searchText = "";
/* 754 */           ShopNetwork.requestItems(cat.id(), 1);
/* 755 */           playClick();
/*     */         } 
/*     */       } 
/* 758 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 762 */     if (getMaxScroll() > 0.0F && relX >= 538 && relX < 548 && relY >= 40 && relY < 321) {
/*     */       
/* 764 */       this.draggingScrollbar = true;
/* 765 */       int thumbH = 40;
/* 766 */       int thumbY = 40 + (int)(this.itemScrollOffset / getMaxScroll() * (281 - thumbH));
/* 767 */       if (relY >= thumbY && relY < thumbY + thumbH) { this.dragOffset = (relY - thumbY); }
/* 768 */       else { this.dragOffset = thumbH / 2.0F; updateScrollFromThumbTop(relY - this.dragOffset); }
/* 769 */        return true;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 774 */     if (this.searchActive) {
/* 775 */       this.searchActive = false;
/* 776 */       return true;
/*     */     } 
/*     */     
/* 779 */     return super.method_25402(mouseX, mouseY, button);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean method_25406(double mx, double my, int b) {
/* 784 */     this.draggingScrollbar = false;
/* 785 */     return super.method_25406(mx, my, b);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean method_25403(double mouseX, double mouseY, int button, double dX, double dY) {
/* 790 */     if (this.draggingScrollbar) {
/* 791 */       updateScrollFromThumbTop((float)mouseY - this.guiTop - this.dragOffset);
/* 792 */       return true;
/*     */     } 
/* 794 */     return super.method_25403(mouseX, mouseY, button, dX, dY);
/*     */   }
/*     */   
/*     */   private void updateScrollFromThumbTop(float thumbTop) {
/* 798 */     int thumbH = 40;
/* 799 */     float ratio = (thumbTop - 40.0F) / (281 - thumbH);
/* 800 */     ratio = Math.max(0.0F, Math.min(1.0F, ratio));
/* 801 */     this.itemScrollOffset = ratio * getMaxScroll();
/*     */   }
/*     */   private boolean handleConfirmClick(double mouseX, double mouseY) {
/*     */     class_746 player;
/* 805 */     int px = popupLeft(), py = popupTop();
/* 806 */     updateConfirmHoverStates((int)mouseX - px, (int)mouseY - py);
/*     */     
/* 808 */     switch (this.hoveredConfirmButton) {
/*     */       case 0:
/* 810 */         playClick();
/* 811 */         commitQuantityInput();
/* 812 */         if (this.isSellMode) { ShopNetwork.requestSell(this.confirmItem.identifier(), this.confirmQuantity); }
/* 813 */         else { ShopNetwork.requestPurchase(this.confirmItem.identifier(), this.confirmQuantity); }
/* 814 */          return true;
/*     */       case 1:
/* 816 */         playClick(); closeConfirmation(); return true;
/* 817 */       case 2: playClick(); deactivateQuantityInput(); this.confirmQuantity = Math.max(1, this.confirmQuantity - 64); syncQuantityInputText(); return true;
/* 818 */       case 3: playClick(); deactivateQuantityInput(); this.confirmQuantity = Math.max(1, this.confirmQuantity - 1); syncQuantityInputText(); return true;
/* 819 */       case 4: playClick(); deactivateQuantityInput(); this.confirmQuantity = Math.min(5000, this.confirmQuantity + 1); syncQuantityInputText(); return true;
/* 820 */       case 5: playClick(); deactivateQuantityInput(); this.confirmQuantity = incrementConfirmQuantity(64); syncQuantityInputText(); return true;
/* 821 */       case 6: playClick(); deactivateQuantityInput(); this.confirmQuantity = Math.max(1, this.confirmQuantity - 16); syncQuantityInputText(); return true;
/* 822 */       case 7: playClick(); deactivateQuantityInput(); this.confirmQuantity = incrementConfirmQuantity(16); syncQuantityInputText(); return true;
/*     */       
/*     */       case 8:
/* 825 */         playClick();
/* 826 */         deactivateQuantityInput();
/* 827 */         player = (class_310.method_1551()).field_1724;
/* 828 */         if (player != null && this.confirmItem != null) {
/* 829 */           if (this.isSellMode) {
/* 830 */             this.confirmQuantity = Math.max(1, Math.min(5000, getAmount(player.method_31548(), this.confirmItem.displayStack())));
/*     */           } else {
/* 832 */             this.confirmQuantity = Math.max(1, Math.min(5000, getAvailableSlots(player.method_31548(), this.confirmItem.displayStack())));
/*     */           } 
/* 834 */           syncQuantityInputText();
/*     */         } 
/* 836 */         return true;
/*     */ 
/*     */       
/*     */       case 9:
/* 840 */         playClick();
/* 841 */         this.quantityInputActive = true;
/* 842 */         this.quantityInputText = String.valueOf(this.confirmQuantity);
/* 843 */         return true;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 848 */     int rx = (int)mouseX - px, ry = (int)mouseY - py;
/* 849 */     if (rx >= 0 && rx < 214 && ry >= 0 && ry < 174) {
/* 850 */       if (this.quantityInputActive) commitQuantityInput(); 
/* 851 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 855 */     closeConfirmation();
/* 856 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean method_25401(double mouseX, double mouseY, double hAmt, double vAmt) {
/* 861 */     if (this.showConfirmation) return true; 
/* 862 */     int relX = (int)mouseX - this.guiLeft;
/* 863 */     if (relX >= 60 && relX < 129) {
/* 864 */       int max = Math.max(0, ShopClientData.getInstance().getCategories().size() - 7);
/* 865 */       this.categoryScrollOffset = Math.max(0, Math.min(max, this.categoryScrollOffset + ((vAmt > 0.0D) ? -1 : 1)));
/* 866 */       return true;
/*     */     } 
/* 868 */     float maxS = getMaxScroll();
/* 869 */     if (maxS > 0.0F) {
/* 870 */       this.itemScrollOffset -= (float)vAmt * 34.5F;
/* 871 */       this.itemScrollOffset = Math.max(0.0F, Math.min(maxS, this.itemScrollOffset));
/*     */     } 
/* 873 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean method_25404(int keyCode, int scanCode, int modifiers) {
/* 880 */     if (keyCode == 256) {
/* 881 */       if (this.quantityInputActive) { commitQuantityInput(); return true; }
/* 882 */        if (this.searchActive) {
/*     */ 
/*     */         
/* 885 */         this.searchActive = false;
/* 886 */         return true;
/*     */       } 
/* 888 */       if (this.showConfirmation) { closeConfirmation(); return true; }
/* 889 */        method_25419();
/* 890 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 894 */     if (keyCode == 257 && 
/* 895 */       this.quantityInputActive) { commitQuantityInput(); return true; }
/*     */ 
/*     */ 
/*     */     
/* 899 */     if (keyCode == 259) {
/* 900 */       if (this.quantityInputActive && !this.quantityInputText.isEmpty()) {
/* 901 */         this.quantityInputText = this.quantityInputText.substring(0, this.quantityInputText.length() - 1);
/* 902 */         return true;
/*     */       } 
/* 904 */       if (this.searchActive && !this.searchText.isEmpty()) {
/* 905 */         this.searchText = this.searchText.substring(0, this.searchText.length() - 1);
/* 906 */         this.itemScrollOffset = 0.0F;
/* 907 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/* 911 */     return super.method_25404(keyCode, scanCode, modifiers);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean method_25400(char chr, int modifiers) {
/* 917 */     if (this.quantityInputActive) {
/* 918 */       if (chr >= '0' && chr <= '9') {
/* 919 */         if (this.quantityInputText.length() < 6) {
/* 920 */           this.quantityInputText += this.quantityInputText;
/*     */         }
/* 922 */         return true;
/*     */       } 
/* 924 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 928 */     if (this.searchActive) {
/* 929 */       if (chr >= ' ' && chr < '') {
/* 930 */         this.searchText += this.searchText;
/* 931 */         this.itemScrollOffset = 0.0F;
/* 932 */         return true;
/*     */       } 
/* 934 */       return true;
/*     */     } 
/*     */     
/* 937 */     return super.method_25400(chr, modifiers);
/*     */   }
/*     */ 
/*     */   
/*     */   private void commitQuantityInput() {
/* 942 */     if (this.quantityInputActive) {
/* 943 */       this.quantityInputActive = false;
/*     */       try {
/* 945 */         int val = Integer.parseInt(this.quantityInputText);
/* 946 */         this.confirmQuantity = Math.max(1, Math.min(5000, val));
/* 947 */       } catch (NumberFormatException e) {
/* 948 */         this.confirmQuantity = Math.max(1, Math.min(5000, this.confirmQuantity));
/*     */       } 
/* 950 */       this.quantityInputText = String.valueOf(this.confirmQuantity);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void deactivateQuantityInput() {
/* 955 */     if (this.quantityInputActive) commitQuantityInput(); 
/*     */   }
/*     */   
/*     */   private void syncQuantityInputText() {
/* 959 */     this.quantityInputText = String.valueOf(this.confirmQuantity);
/*     */   }
/*     */   
/*     */   private int incrementConfirmQuantity(int amount) {
/* 963 */     if (this.confirmQuantity == 1) {
/* 964 */       return Math.min(5000, amount);
/*     */     }
/* 966 */     return Math.min(5000, this.confirmQuantity + amount);
/*     */   }
/*     */ 
/*     */   
/*     */   private void openConfirmation(ShopNetwork.ShopItemEntry item, boolean sell) {
/* 971 */     this.showConfirmation = true;
/* 972 */     this.confirmItem = item;
/* 973 */     this.confirmQuantity = 1;
/* 974 */     this.isSellMode = sell;
/* 975 */     this.quantityInputActive = false;
/* 976 */     this.quantityInputText = "1";
/*     */     
/* 978 */     ShopClientData.getInstance().clearPurchaseResult();
/*     */   }
/*     */   
/*     */   private void closeConfirmation() {
/* 982 */     this.showConfirmation = false;
/* 983 */     this.confirmItem = null;
/* 984 */     this.confirmQuantity = 1;
/* 985 */     this.hoveredConfirmButton = -1;
/* 986 */     this.quantityInputActive = false;
/* 987 */     this.quantityInputText = "1";
/*     */   }
/*     */   
/*     */   private void playClick() {
/* 991 */     class_310.method_1551().method_1483().method_4873(
/* 992 */         (class_1113)class_1109.method_47978((class_6880)class_3417.field_15015, 1.0F));
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\shop\screen\PokeMartScreen.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */