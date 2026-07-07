/*      */ package com.atlas.common.fabric.auction.screen;
/*      */ import com.atlas.common.fabric.auction.data.AuctionClientData;
/*      */ import com.atlas.common.fabric.auction.network.AuctionNetwork;
/*      */ import com.atlas.common.fabric.cosmetic.Cosmetic;
/*      */ import com.atlas.common.fabric.cosmetic.CosmeticCategory;
/*      */ import com.atlas.common.fabric.cosmetic.CosmeticRepository;
/*      */ import com.atlas.common.fabric.cosmetic.CustomModelDataCosmeticModel;
/*      */ import com.atlas.common.fabric.cosmetic.EquippedCosmetic;
/*      */ import com.atlas.common.fabric.ranked.screen.RankedStyledButton;
/*      */ import com.atlas.common.fabric.screen.PokemonRenderHelper;
/*      */ import com.cobblemon.mod.common.client.render.models.blockbench.FloatingState;
/*      */ import com.google.gson.JsonObject;
/*      */ import java.text.NumberFormat;
/*      */ import java.util.HashMap;
/*      */ import java.util.List;
/*      */ import java.util.Locale;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import java.util.regex.Matcher;
/*      */ import net.minecraft.class_1792;
/*      */ import net.minecraft.class_1799;
/*      */ import net.minecraft.class_1935;
/*      */ import net.minecraft.class_2487;
/*      */ import net.minecraft.class_2520;
/*      */ import net.minecraft.class_2561;
/*      */ import net.minecraft.class_2583;
/*      */ import net.minecraft.class_2960;
/*      */ import net.minecraft.class_310;
/*      */ import net.minecraft.class_332;
/*      */ import net.minecraft.class_4587;
/*      */ import net.minecraft.class_5250;
/*      */ import net.minecraft.class_5348;
/*      */ import net.minecraft.class_9279;
/*      */ import net.minecraft.class_9280;
/*      */ import net.minecraft.class_9334;
/*      */ import org.jetbrains.annotations.Nullable;
/*      */ import org.joml.Quaternionf;
/*      */ 
/*      */ public class AuctionHouseScreen extends class_437 implements CosmeticPreviewProvider {
/*      */   private static class_2960 tex(String path) {
/*   41 */     return class_2960.method_60655("atlas-common-fabric", "textures/auction/" + path);
/*      */   } private static final String NS = "atlas-common-fabric"; private static final class WidgetTexture extends Record { private final class_2960 normal; private final class_2960 hover; private final int width; private final int height; private WidgetTexture(class_2960 normal, class_2960 hover, int width, int height) {
/*   43 */       this.normal = normal; this.hover = hover; this.width = width; this.height = height; } public final String toString() { // Byte code:
/*      */       //   0: aload_0
/*      */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/auction/screen/AuctionHouseScreen$WidgetTexture;)Ljava/lang/String;
/*      */       //   6: areturn
/*      */       // Line number table:
/*      */       //   Java source line number -> byte code offset
/*      */       //   #43	-> 0
/*      */       // Local variable table:
/*      */       //   start	length	slot	name	descriptor
/*      */       //   0	7	0	this	Lcom/atlas/common/fabric/auction/screen/AuctionHouseScreen$WidgetTexture; } public final int hashCode() { // Byte code:
/*      */       //   0: aload_0
/*      */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/auction/screen/AuctionHouseScreen$WidgetTexture;)I
/*      */       //   6: ireturn
/*      */       // Line number table:
/*      */       //   Java source line number -> byte code offset
/*      */       //   #43	-> 0
/*      */       // Local variable table:
/*      */       //   start	length	slot	name	descriptor
/*      */       //   0	7	0	this	Lcom/atlas/common/fabric/auction/screen/AuctionHouseScreen$WidgetTexture; } public final boolean equals(Object o) { // Byte code:
/*      */       //   0: aload_0
/*      */       //   1: aload_1
/*      */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/auction/screen/AuctionHouseScreen$WidgetTexture;Ljava/lang/Object;)Z
/*      */       //   7: ireturn
/*      */       // Line number table:
/*      */       //   Java source line number -> byte code offset
/*      */       //   #43	-> 0
/*      */       // Local variable table:
/*      */       //   start	length	slot	name	descriptor
/*      */       //   0	8	0	this	Lcom/atlas/common/fabric/auction/screen/AuctionHouseScreen$WidgetTexture;
/*   43 */       //   0	8	1	o	Ljava/lang/Object; } public class_2960 normal() { return this.normal; } public class_2960 hover() { return this.hover; } public int width() { return this.width; } public int height() { return this.height; }
/*      */      }
/*      */   private static WidgetTexture widget(String fileName, int width, int height) {
/*   46 */     class_2960 texture = tex(fileName);
/*   47 */     return new WidgetTexture(texture, texture, width, height);
/*      */   }
/*      */   
/*      */   private static WidgetTexture widget(String normalFile, String hoverFile, int width, int height) {
/*   51 */     return new WidgetTexture(tex(normalFile), tex(hoverFile), width, height);
/*      */   }
/*      */   
/*   54 */   private static final class_2960 TEX_BG = tex("background-frame.png");
/*   55 */   private static final class_2960 AUCTION_FONT = CobblemonResources.INSTANCE.getDEFAULT_LARGE();
/*   56 */   private static final WidgetTexture TEX_ITEM_PANEL = widget("item_panel.png", "item_panel_hover.png", 138, 53);
/*   57 */   private static final class_2960 TEX_ITEM_PANEL_HOVER_NO_CORNER = tex("item_panel_hover_no_corner.png");
/*   58 */   private static final WidgetTexture TEX_SORT_PANEL = widget("time_remaining_panel.png", "time_remaining_panel_hover.png", 81, 14);
/*   59 */   private static final WidgetTexture TEX_TYPE_PANEL = widget("types_panel.png", "types_panel_hover.png", 81, 14);
/*   60 */   private static final WidgetTexture TEX_SEARCH_BAR = widget("search_bar.png", "search_bar_active.png", 110, 14);
/*   61 */   private static final WidgetTexture TEX_SEARCH_BTN = widget("search_btn.png", "search_btn_hover.png", 65, 14);
/*   62 */   private static final WidgetTexture TEX_RECENT_BTN = widget("recent_sales_btn.png", "recent_sales_btn_hover.png", 72, 14);
/*   63 */   private static final WidgetTexture TEX_BACK = widget("back_btn_normal.png", "back_btn_hover.png", 28, 7);
/*   64 */   private static final WidgetTexture TEX_REFRESH_BTN = widget("refresh_btn.png", "refresh_btn_hover.png", 50, 13);
/*   65 */   private static final WidgetTexture TEX_MY_AUCTIONS_BTN = widget("my_auctions_btn.png", "my_auctions_btn_hover.png", 65, 13);
/*   66 */   private static final WidgetTexture TEX_BUY_TAG = widget("buy_btn.png", "buy_btn_hover.png", 28, 14);
/*   67 */   private static final WidgetTexture TEX_BID_TAG = widget("bid_btn.png", "bid_btn_hover.png", 28, 14);
/*   68 */   private static final WidgetTexture TEX_SOLD_TAG = widget("sold_tag.png", 30, 15);
/*   69 */   private static final WidgetTexture TEX_WON_TAG = widget("won_tag.png", 28, 15);
/*   70 */   private static final class_2960 TEX_CONFIRM_POPUP_BG = tex("confirm_popup_bg.png");
/*   71 */   private static final WidgetTexture TEX_CONFIRM_POPUP_CONFIRM = widget("confirm_popup_confirm_normal.png", "confirm_popup_confirm_hover.png", 48, 13);
/*   72 */   private static final WidgetTexture TEX_CONFIRM_POPUP_CANCEL = widget("confirm_popup_cancel_normal.png", "confirm_popup_cancel_hover.png", 48, 13);
/*   73 */   private static final class_2960 TEX_FAV_IDLE = tex("fav_idle.png");
/*   74 */   private static final WidgetTexture TEX_FAV_ICON = widget("fav_normal_icon.png", "fav_hover_icon.png", 7, 7);
/*   75 */   private static final class_2960 TEX_COINS_ICON = tex("coins_icon.png");
/*      */ 
/*      */   
/*   78 */   private final Map<Integer, FloatingState> pokemonStates = new HashMap<>();
/*   79 */   private final Map<String, Set<String>> pokemonAspectsCache = new HashMap<>();
/*      */   
/*      */   private static int c(int r, int g, int b, int a) {
/*   82 */     return RankedStyledButton.color(r, g, b, a);
/*      */   }
/*      */ 
/*      */   
/*   86 */   private static final int ACCENT_GOLD = c(255, 200, 60, 255);
/*   87 */   private static final int ACCENT_AMBER = c(220, 160, 50, 255);
/*   88 */   private static final int ACCENT_WARM = c(180, 130, 50, 255);
/*   89 */   private static final int ACCENT_GREEN = c(80, 220, 100, 255);
/*   90 */   private static final int ACCENT_RED = c(220, 60, 60, 255);
/*   91 */   private static final int ACCENT_CYAN = c(80, 200, 220, 255);
/*      */ 
/*      */   
/*   94 */   private static final int TEXT_PRIMARY = c(240, 235, 220, 255);
/*   95 */   private static final int TEXT_SECONDARY = c(180, 170, 150, 255);
/*   96 */   private static final int TEXT_DIM = c(120, 110, 95, 255);
/*   97 */   private static final int TEXT_GOLD = c(255, 210, 80, 255);
/*   98 */   private static final int BORDER_DARK = c(50, 44, 32, 255);
/*      */ 
/*      */   
/*  101 */   private static final int DROPDOWN_BG = c(18, 18, 38, 250);
/*  102 */   private static final int DROPDOWN_HOVER = c(42, 47, 78, 255);
/*  103 */   private static final int DROPDOWN_BORDER = c(72, 78, 130, 255);
/*  104 */   private static final int DROPDOWN_SELECTED = c(176, 184, 255, 255);
/*      */   
/*      */   private static final int BACKGROUND_WIDTH = 604;
/*      */   
/*      */   private static final int GUI_WIDTH = 450;
/*      */   
/*      */   private static final int GUI_HEIGHT = 350;
/*      */   
/*      */   private static final int GRID_COLS = 3;
/*      */   private static final int GRID_ROWS = 5;
/*      */   private static final int CARD_PAD = 2;
/*      */   private static final int CONTENT_X = 15;
/*      */   private static final int CONTROLS_Y = 22;
/*      */   private static final int GRID_Y = 39;
/*      */   private static final int FOOTER_Y = 320;
/*      */   private static final int BACKGROUND_X_OFFSET = 77;
/*      */   private int guiLeft;
/*      */   private int guiTop;
/*      */   private int gridX;
/*      */   private int gridY;
/*      */   private int gridW;
/*      */   private int gridH;
/*      */   private int cardW;
/*      */   private int cardH;
/*      */   private AuctionDetailPanel detailPanel;
/*      */   private boolean showingDetail;
/*  130 */   private final Map<CosmeticCategory, EquippedCosmetic> previewedCosmetics = new LinkedHashMap<>();
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public Map<CosmeticCategory, EquippedCosmetic> getPreviewedCosmetics() {
/*  135 */     return this.previewedCosmetics.isEmpty() ? null : this.previewedCosmetics;
/*      */   }
/*      */ 
/*      */   
/*  139 */   private int hoveredCardIndex = -1;
/*  140 */   private int pinHoveredIndex = -1;
/*      */   
/*      */   private class_1799 hoveredItemStack;
/*      */   
/*      */   private boolean closeHovered;
/*      */   private boolean refreshHovered;
/*      */   private boolean searchBtnHovered;
/*      */   private boolean prevPageHovered;
/*      */   private boolean nextPageHovered;
/*      */   private boolean myAuctionsHovered;
/*  150 */   private static final String[] SORT_OPTIONS = new String[] { "Time Remaining", "Price: Low", "Price: High", "Newest", "Rarity", "Size", "Buy Now First", "Bid First" }; private boolean sortDropdownOpen; private int sortDropdownX;
/*      */   private int sortDropdownY;
/*      */   private int sortDropdownW;
/*  153 */   private int selectedSortIndex = 0;
/*  154 */   private int hoveredSortIndex = -1;
/*      */   
/*      */   private boolean sortBtnHovered;
/*      */   
/*  158 */   private static final String[] TYPE_OPTIONS = new String[] { "All Types", "Pokémon", "Cosmetics", "Pokémon Skins", "Items", "Pokéballs", "Held Items", "Enchants", "Blocks", "Tools/Armor" }; private boolean typeDropdownOpen; private int typeDropdownX;
/*      */   private int typeDropdownY;
/*      */   private int typeDropdownW;
/*  161 */   private int selectedTypeIndex = 0;
/*  162 */   private int hoveredTypeIndex = -1;
/*      */   
/*      */   private boolean typeBtnHovered;
/*      */   
/*  166 */   private String searchText = ""; private boolean searchFocused;
/*      */   private int searchBarX;
/*      */   private int searchBarY;
/*      */   private int searchBarW;
/*      */   private int searchBarH;
/*      */   private int cursorPos;
/*      */   private long lastBlinkTime;
/*      */   private boolean recentlySoldMode = false;
/*      */   private boolean recentlySoldHovered;
/*      */   private boolean recentlySoldBackHovered;
/*  176 */   private int recentlySoldPage = 1;
/*  177 */   private int recentlySoldTotalPages = 1;
/*      */ 
/*      */   
/*  180 */   private int currentPage = 1;
/*  181 */   private int totalPages = 1;
/*      */ 
/*      */   
/*  184 */   private long lastRequestTime = 0L; private static final long REQUEST_COOLDOWN_MS = 500L; private boolean showConfirm; private String confirmListingId;
/*      */   private boolean confirmYesHovered;
/*      */   private boolean confirmNoHovered;
/*      */   
/*      */   private boolean canSendRequest() {
/*  189 */     long now = System.currentTimeMillis();
/*  190 */     if (now - this.lastRequestTime < 500L) return false; 
/*  191 */     this.lastRequestTime = now;
/*  192 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  200 */   private static final NumberFormat COIN_FORMAT = NumberFormat.getNumberInstance(Locale.US);
/*      */   
/*      */   public AuctionHouseScreen() {
/*  203 */     super((class_2561)class_2561.method_43471("screen.cobblemon_auction.house"));
/*  204 */     this.lastBlinkTime = System.currentTimeMillis();
/*      */     
/*  206 */     for (int i = 0; i < 15; i++) {
/*  207 */       this.pokemonStates.put(Integer.valueOf(i), new FloatingState());
/*      */     }
/*  209 */     PokemonRenderHelper.init();
/*      */   }
/*      */ 
/*      */   
/*      */   protected void method_25426() {
/*  214 */     super.method_25426();
/*  215 */     this.guiLeft = (this.field_22789 - 450) / 2;
/*  216 */     this.guiTop = (this.field_22790 - 350) / 2;
/*      */     
/*  218 */     this.gridX = this.guiLeft + 15;
/*  219 */     this.gridY = this.guiTop + 39;
/*  220 */     this.cardW = TEX_ITEM_PANEL.width();
/*  221 */     this.cardH = TEX_ITEM_PANEL.height();
/*  222 */     this.gridW = 3 * this.cardW + 4;
/*  223 */     this.gridH = 5 * this.cardH + 8;
/*      */     
/*  225 */     this.sortDropdownW = TEX_SORT_PANEL.width();
/*  226 */     this.sortDropdownX = this.guiLeft + 15;
/*  227 */     this.sortDropdownY = this.guiTop + 22;
/*      */     
/*  229 */     this.typeDropdownW = TEX_TYPE_PANEL.width();
/*  230 */     this.typeDropdownX = this.sortDropdownX + this.sortDropdownW + 2;
/*  231 */     this.typeDropdownY = this.sortDropdownY;
/*      */     
/*  233 */     this.searchBarW = TEX_SEARCH_BAR.width();
/*  234 */     this.searchBarH = TEX_SEARCH_BAR.height();
/*  235 */     this.searchBarX = this.typeDropdownX + this.typeDropdownW + 2;
/*  236 */     this.searchBarY = this.sortDropdownY;
/*      */ 
/*      */     
/*  239 */     if (this.showingDetail && this.detailPanel != null) {
/*  240 */       AuctionNetwork.AuctionListingEntry listing = AuctionClientData.getInstance().getSelectedListing();
/*  241 */       if (listing != null) {
/*  242 */         boolean wasRecentlySold = (this.detailPanel != null && this.recentlySoldMode);
/*  243 */         this.detailPanel = new AuctionDetailPanel(this.guiLeft, this.guiTop, 450, 350, this::closeDetail, this::playClickSound);
/*  244 */         if (wasRecentlySold) {
/*  245 */           this.detailPanel.setRecentlySoldMode(true);
/*      */         }
/*  247 */         this.detailPanel.init(listing);
/*      */       } 
/*      */     } else {
/*      */       
/*  251 */       AuctionNetwork.requestAuctionPage(1, getSortKey(), getTypeKey(), this.searchText);
/*  252 */       AuctionClientData.getInstance().setLoading(true);
/*      */     } 
/*      */   }
/*      */   
/*      */   private String getSortKey() {
/*  257 */     switch (this.selectedSortIndex) { case 1: case 2: case 3: case 4: case 5: case 6: case 7:  }  return 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  265 */       "time_remaining";
/*      */   }
/*      */ 
/*      */   
/*      */   private String getTypeKey() {
/*  270 */     switch (this.selectedTypeIndex) { case 1: case 2: case 3: case 4: case 5: case 6: case 7: case 8: case 9:  }  return 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  280 */       "ALL";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void method_25394(class_332 ctx, int mouseX, int mouseY, float delta) {
/*  291 */     ctx.method_25294(0, 0, this.field_22789, this.field_22790, c(0, 0, 0, 180));
/*      */     
/*  293 */     if (this.showingDetail && this.detailPanel != null) {
/*      */       
/*  295 */       if (this.detailPanel.tick())
/*      */       {
/*  297 */         if (canSendRequest()) {
/*  298 */           AuctionNetwork.AuctionListingEntry selected = AuctionClientData.getInstance().getSelectedListing();
/*  299 */           if (selected != null)
/*      */           {
/*  301 */             AuctionNetwork.requestAuctionPage(this.currentPage, getSortKey(), getTypeKey(), this.searchText);
/*      */           }
/*      */         } 
/*      */       }
/*  305 */       drawPanel(ctx);
/*  306 */       this.detailPanel.render(ctx, this.field_22793, mouseX, mouseY, delta);
/*  307 */       super.method_25394(ctx, mouseX, mouseY, delta);
/*      */       
/*      */       return;
/*      */     } 
/*  311 */     int contentMouseX = this.showConfirm ? Integer.MIN_VALUE : mouseX;
/*  312 */     int contentMouseY = this.showConfirm ? Integer.MIN_VALUE : mouseY;
/*      */ 
/*      */     
/*  315 */     this.hoveredCardIndex = -1;
/*  316 */     this.pinHoveredIndex = -1;
/*  317 */     this.hoveredItemStack = null;
/*  318 */     this.closeHovered = this.refreshHovered = this.searchBtnHovered = this.recentlySoldHovered = this.recentlySoldBackHovered = false;
/*  319 */     this.prevPageHovered = this.nextPageHovered = this.myAuctionsHovered = false;
/*  320 */     this.sortBtnHovered = this.typeBtnHovered = false;
/*  321 */     this.hoveredSortIndex = this.hoveredTypeIndex = -1;
/*      */     
/*  323 */     drawPanel(ctx);
/*  324 */     drawHeader(ctx, contentMouseX, contentMouseY);
/*  325 */     drawGrid(ctx, contentMouseX, contentMouseY, delta);
/*  326 */     drawFooter(ctx, contentMouseX, contentMouseY);
/*      */ 
/*      */     
/*  329 */     if (!this.recentlySoldMode) {
/*  330 */       if (this.sortDropdownOpen) drawDropdown(ctx, contentMouseX, contentMouseY, SORT_OPTIONS, this.sortDropdownX, this.sortDropdownY + 16, this.sortDropdownW, true); 
/*  331 */       if (this.typeDropdownOpen) drawDropdown(ctx, contentMouseX, contentMouseY, TYPE_OPTIONS, this.typeDropdownX, this.typeDropdownY + 16, this.typeDropdownW, false);
/*      */     
/*      */     } 
/*  334 */     super.method_25394(ctx, mouseX, mouseY, delta);
/*      */     
/*  336 */     if (this.showConfirm) drawConfirmOverlay(ctx, mouseX, mouseY);
/*      */ 
/*      */     
/*  339 */     if (this.hoveredItemStack != null && !this.hoveredItemStack.method_7960() && !this.showConfirm && !this.sortDropdownOpen && !this.typeDropdownOpen) {
/*  340 */       ctx.method_51448().method_22903();
/*  341 */       ctx.method_51448().method_46416(0.0F, 0.0F, 500.0F);
/*  342 */       ctx.method_51446(this.field_22793, this.hoveredItemStack, mouseX, mouseY);
/*  343 */       ctx.method_51448().method_22909();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void drawTex(class_332 ctx, class_2960 tex, int x, int y, int w, int h) {
/*  350 */     GuiUtilsKt.blitk(ctx.method_51448(), tex, Integer.valueOf(x), Integer.valueOf(y), Integer.valueOf(h), Integer.valueOf(w), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(w), Integer.valueOf(h), Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(1), Float.valueOf(1.0F), true, 1.0F);
/*      */   }
/*      */   
/*      */   private void drawPanel(class_332 ctx) {
/*  354 */     int x = this.guiLeft - 77, y = this.guiTop, w = 604, h = 350;
/*      */     
/*  356 */     drawTex(ctx, TEX_BG, x, y, w, h);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void drawHeader(class_332 ctx, int mouseX, int mouseY) {
/*  362 */     this.closeHovered = false;
/*      */     
/*  364 */     if (this.recentlySoldMode) {
/*  365 */       int backX = this.guiLeft + 15;
/*  366 */       int backY = this.sortDropdownY + 3;
/*  367 */       this.recentlySoldBackHovered = drawTexturedWidget(ctx, TEX_BACK, backX, backY, mouseX, mouseY);
/*      */       
/*  369 */       int searchBtnX = this.searchBarX + this.searchBarW + 2;
/*  370 */       int recentBtnX = searchBtnX + TEX_SEARCH_BTN.width() + 3;
/*  371 */       this.recentlySoldHovered = drawTexturedWidget(ctx, TEX_RECENT_BTN, recentBtnX, this.searchBarY, mouseX, mouseY);
/*  372 */       drawButtonLabel(ctx, "Recent Sales", recentBtnX, this.searchBarY, TEX_RECENT_BTN.width(), TEX_RECENT_BTN.height(), this.recentlySoldHovered);
/*      */     } else {
/*  374 */       this.sortBtnHovered = drawTexturedWidget(ctx, TEX_SORT_PANEL, this.sortDropdownX, this.sortDropdownY, mouseX, mouseY);
/*  375 */       this.typeBtnHovered = drawTexturedWidget(ctx, TEX_TYPE_PANEL, this.typeDropdownX, this.typeDropdownY, mouseX, mouseY);
/*  376 */       drawDropdownLabel(ctx, this.sortDropdownX, this.sortDropdownY, TEX_SORT_PANEL.width(), TEX_SORT_PANEL.height(), SORT_OPTIONS[this.selectedSortIndex]);
/*  377 */       drawDropdownLabel(ctx, this.typeDropdownX, this.typeDropdownY, TEX_TYPE_PANEL.width(), TEX_TYPE_PANEL.height(), TYPE_OPTIONS[this.selectedTypeIndex]);
/*  378 */       drawSearchBar(ctx, mouseX, mouseY);
/*      */       
/*  380 */       int searchBtnX = this.searchBarX + this.searchBarW + 2;
/*  381 */       this.searchBtnHovered = drawTexturedWidget(ctx, TEX_SEARCH_BTN, searchBtnX, this.searchBarY, mouseX, mouseY);
/*  382 */       drawButtonLabel(ctx, "Search", searchBtnX, this.searchBarY, TEX_SEARCH_BTN.width(), TEX_SEARCH_BTN.height(), this.searchBtnHovered, true);
/*      */       
/*  384 */       int recentBtnX = searchBtnX + TEX_SEARCH_BTN.width() + 3;
/*  385 */       this.recentlySoldHovered = drawTexturedWidget(ctx, TEX_RECENT_BTN, recentBtnX, this.searchBarY, mouseX, mouseY);
/*  386 */       drawButtonLabel(ctx, "Recent Sales", recentBtnX, this.searchBarY, TEX_RECENT_BTN.width(), TEX_RECENT_BTN.height(), this.recentlySoldHovered);
/*      */     } 
/*      */   }
/*      */   
/*      */   private boolean drawTexturedWidget(class_332 ctx, WidgetTexture widget, int x, int y, int mx, int my) {
/*  391 */     boolean hovered = (mx >= x && mx < x + widget.width() && my >= y && my < y + widget.height());
/*  392 */     drawTex(ctx, hovered ? widget.hover() : widget.normal(), x, y, widget.width(), widget.height());
/*  393 */     return hovered;
/*      */   }
/*      */   
/*      */   private class_5250 auctionText(String text) {
/*  397 */     return class_2561.method_43470(text).method_27696(class_2583.field_24360.method_27704(AUCTION_FONT).method_10978(Boolean.valueOf(false)));
/*      */   }
/*      */   
/*      */   private void drawDropdownLabel(class_332 ctx, int x, int y, int w, int h, String text) {
/*  401 */     ctx.method_51448().method_22903();
/*  402 */     ctx.method_51448().method_46416(0.0F, 0.0F, 50.0F);
/*  403 */     String displayText = text;
/*  404 */     int maxTextW = w - 14;
/*  405 */     while (this.field_22793.method_27525((class_5348)auctionText(displayText)) > maxTextW && displayText.length() > 2) {
/*  406 */       displayText = displayText.substring(0, displayText.length() - 1);
/*      */     }
/*  408 */     if (!displayText.equals(text)) displayText = displayText + ".."; 
/*  409 */     drawCobblemonText(ctx, auctionText(displayText), (x + 5), (y + 3), 1.0F, TEXT_PRIMARY, false, maxTextW);
/*  410 */     ctx.method_51448().method_22909();
/*      */   }
/*      */   
/*      */   private void drawButtonLabel(class_332 ctx, String text, int x, int y, int w, int h, boolean hovered) {
/*  414 */     drawButtonLabel(ctx, text, x, y, w, h, hovered, false);
/*      */   }
/*      */   
/*      */   private void drawButtonLabel(class_332 ctx, String text, int x, int y, int w, int h, boolean hovered, boolean centered) {
/*  418 */     ctx.method_51448().method_22903();
/*  419 */     ctx.method_51448().method_46416(0.0F, 0.0F, 50.0F);
/*  420 */     class_5250 label = auctionText(text);
/*  421 */     int textW = this.field_22793.method_27525((class_5348)label);
/*      */ 
/*      */ 
/*      */     
/*  425 */     float textX = centered ? (x + (w - textW) / 2.0F) : (x + 5);
/*  426 */     drawCobblemonText(ctx, label, textX, (y + 2), 1.0F, TEXT_PRIMARY, false, w);
/*  427 */     ctx.method_51448().method_22909();
/*      */   }
/*      */   
/*      */   private void drawSearchBar(class_332 ctx, int mouseX, int mouseY) {
/*  431 */     boolean hov = (mouseX >= this.searchBarX && mouseX < this.searchBarX + this.searchBarW && mouseY >= this.searchBarY && mouseY < this.searchBarY + this.searchBarH);
/*  432 */     class_2960 searchTex = (this.searchFocused || !this.searchText.isEmpty() || hov) ? TEX_SEARCH_BAR.hover() : TEX_SEARCH_BAR.normal();
/*  433 */     drawTex(ctx, searchTex, this.searchBarX, this.searchBarY, this.searchBarW, this.searchBarH);
/*      */     
/*  435 */     int textX = this.searchBarX + 4;
/*  436 */     int textY = this.searchBarY + 3;
/*      */     
/*  438 */     if (!this.searchText.isEmpty() || this.searchFocused) {
/*  439 */       ctx.method_25294(this.searchBarX + 2, this.searchBarY + 2, this.searchBarX + this.searchBarW - 2, this.searchBarY + this.searchBarH - 2, c(26, 25, 50, 255));
/*  440 */       String visibleText = this.searchText;
/*  441 */       int availW = this.searchBarW - 10;
/*  442 */       while (this.field_22793.method_27525((class_5348)auctionText(visibleText)) > availW && visibleText.length() > 0) {
/*  443 */         visibleText = visibleText.substring(1);
/*      */       }
/*  445 */       drawCobblemonText(ctx, auctionText(visibleText), textX, textY, 1.0F, TEXT_PRIMARY, false, availW);
/*      */ 
/*      */       
/*  448 */       if (this.searchFocused) {
/*  449 */         long elapsed = (System.currentTimeMillis() - this.lastBlinkTime) % 1000L;
/*  450 */         if (elapsed < 500L) {
/*  451 */           int trimmed = this.searchText.length() - visibleText.length();
/*  452 */           if (this.cursorPos >= trimmed) {
/*  453 */             String beforeCursor = this.searchText.substring(trimmed, this.cursorPos);
/*  454 */             int cursorX = textX + this.field_22793.method_27525((class_5348)auctionText(beforeCursor));
/*  455 */             ctx.method_25294(cursorX, textY - 1, cursorX + 1, textY + 9, TEXT_PRIMARY);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/*  461 */       if (!this.searchText.isEmpty()) {
/*  462 */         int clearX = this.searchBarX + this.searchBarW - 10;
/*  463 */         boolean clearHov = (mouseX >= clearX - 2 && mouseX < clearX + 8 && mouseY >= textY - 2 && mouseY < textY + 10);
/*  464 */         ctx.method_51433(this.field_22793, "×", clearX, textY, clearHov ? TEXT_PRIMARY : TEXT_DIM, true);
/*      */       } 
/*  466 */     } else if (hov) {
/*  467 */       ctx.method_25294(this.searchBarX + 1, this.searchBarY + this.searchBarH - 1, this.searchBarX + this.searchBarW - 1, this.searchBarY + this.searchBarH, c(42, 47, 78, 90));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void drawGrid(class_332 ctx, int mouseX, int mouseY, float delta) {
/*  474 */     AuctionClientData data = AuctionClientData.getInstance();
/*      */     
/*  476 */     if (this.recentlySoldMode) {
/*  477 */       drawRecentlySoldGrid(ctx, mouseX, mouseY, delta);
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*  482 */     List<AuctionNetwork.AuctionListingEntry> listings = data.getPinnedFirst(data.getListings());
/*      */     
/*  484 */     if (data.isLoading()) {
/*  485 */       ctx.method_25300(this.field_22793, "Loading...", this.gridX + this.gridW / 2, this.gridY + this.gridH / 2 - 4, TEXT_DIM);
/*      */       
/*      */       return;
/*      */     } 
/*  489 */     if (listings.isEmpty()) {
/*  490 */       ctx.method_25300(this.field_22793, "No listings found", this.gridX + this.gridW / 2, this.gridY + this.gridH / 2 - 4, TEXT_DIM);
/*      */       
/*      */       return;
/*      */     } 
/*  494 */     ctx.method_44379(this.gridX, this.gridY, this.gridX + this.gridW, this.gridY + this.gridH);
/*      */ 
/*      */     
/*  497 */     long frameNow = System.currentTimeMillis() / 1000L * 1000L;
/*      */     
/*  499 */     for (int i = 0; i < 15 && i < listings.size(); i++) {
/*  500 */       int col = i % 3;
/*  501 */       int row = i / 3;
/*      */       
/*  503 */       int cx = this.gridX + col * (this.cardW + 2);
/*  504 */       int cy = this.gridY + row * (this.cardH + 2);
/*      */       
/*  506 */       AuctionNetwork.AuctionListingEntry listing = listings.get(i);
/*  507 */       boolean hov = (mouseX >= cx && mouseX < cx + this.cardW && mouseY >= cy && mouseY < cy + this.cardH);
/*  508 */       if (hov) {
/*  509 */         this.hoveredCardIndex = i;
/*  510 */         this.hoveredItemStack = listing.displayStack();
/*      */       } 
/*      */       
/*  513 */       int pinBtnX = cx + 4;
/*  514 */       int pinBtnY = cy + 4;
/*  515 */       boolean pinHov = (mouseX >= pinBtnX && mouseX < pinBtnX + TEX_FAV_ICON.width() && mouseY >= pinBtnY && mouseY < pinBtnY + TEX_FAV_ICON.height());
/*  516 */       if (pinHov) this.pinHoveredIndex = i;
/*      */       
/*  518 */       drawAuctionCard(ctx, cx, cy, this.cardW, this.cardH, listing, hov, pinHov, i, delta, frameNow);
/*      */     } 
/*      */     
/*  521 */     ctx.method_44380();
/*      */   }
/*      */   
/*      */   private void drawRecentlySoldGrid(class_332 ctx, int mouseX, int mouseY, float delta) {
/*  525 */     AuctionClientData data = AuctionClientData.getInstance();
/*  526 */     List<AuctionNetwork.RecentlySoldEntry> entries = data.getRecentlySoldEntries();
/*      */     
/*  528 */     if (data.isRecentlySoldLoading()) {
/*  529 */       ctx.method_25300(this.field_22793, "Loading...", this.gridX + this.gridW / 2, this.gridY + this.gridH / 2 - 4, TEXT_DIM);
/*      */       
/*      */       return;
/*      */     } 
/*  533 */     if (entries.isEmpty()) {
/*  534 */       ctx.method_25300(this.field_22793, "No recent sales", this.gridX + this.gridW / 2, this.gridY + this.gridH / 2 - 4, TEXT_DIM);
/*      */       
/*      */       return;
/*      */     } 
/*  538 */     ctx.method_44379(this.gridX, this.gridY, this.gridX + this.gridW, this.gridY + this.gridH);
/*      */     
/*  540 */     for (int i = 0; i < 15 && i < entries.size(); i++) {
/*  541 */       int col = i % 3;
/*  542 */       int row = i / 3;
/*      */       
/*  544 */       int cx = this.gridX + col * (this.cardW + 2);
/*  545 */       int cy = this.gridY + row * (this.cardH + 2);
/*      */       
/*  547 */       AuctionNetwork.RecentlySoldEntry entry = entries.get(i);
/*  548 */       boolean hov = (mouseX >= cx && mouseX < cx + this.cardW && mouseY >= cy && mouseY < cy + this.cardH);
/*  549 */       if (hov) {
/*  550 */         this.hoveredCardIndex = i;
/*  551 */         this.hoveredItemStack = entry.displayStack();
/*      */       } 
/*      */       
/*  554 */       drawRecentlySoldCard(ctx, cx, cy, this.cardW, this.cardH, entry, hov, i, delta);
/*      */     } 
/*      */     
/*  557 */     ctx.method_44380();
/*      */   }
/*      */   
/*      */   private void drawRecentlySoldCard(class_332 ctx, int x, int y, int w, int h, AuctionNetwork.RecentlySoldEntry entry, boolean hovered, int cardIndex, float delta) {
/*  561 */     drawCardFrame(ctx, x, y, hovered);
/*  562 */     int iconAreaX = x + 4;
/*  563 */     int iconAreaY = y + 4;
/*  564 */     int iconAreaW = 43;
/*  565 */     int iconAreaH = 42;
/*  566 */     int textAreaX = x + 53;
/*  567 */     int textAreaW = 79;
/*      */     
/*  569 */     if (entry.isPokemon()) {
/*  570 */       Set<String> aspects = this.pokemonAspectsCache.computeIfAbsent(entry.auctionId() + "_sold", k -> AuctionDetailPanel.extractPokemonAspects(entry.displayStack()));
/*      */       
/*  572 */       renderPokemonInCard(ctx, entry.speciesId(), aspects, iconAreaX, iconAreaY, iconAreaW, iconAreaH, cardIndex, delta);
/*  573 */     } else if (entry.displayStack() != null && !entry.displayStack().method_7960()) {
/*  574 */       class_2561 tagText = buildTagText(entry.displayStack());
/*  575 */       if (tagText != null) {
/*  576 */         renderTagInArea(ctx, tagText, iconAreaX, iconAreaY, iconAreaW, iconAreaH);
/*      */       } else {
/*  578 */         class_1799 iconStack = getCosmeticDisplayStack(entry.displayStack());
/*  579 */         if (iconStack == null) {
/*  580 */           iconStack = entry.displayStack();
/*  581 */           class_9279 iconCd = (class_9279)iconStack.method_57824(class_9334.field_49628);
/*  582 */           if (iconCd != null && iconCd.method_57461().method_10545("cosmetic-note")) {
/*  583 */             iconStack = new class_1799((class_1935)class_1802.field_8407);
/*      */           }
/*      */         } 
/*  586 */         drawStackInCard(ctx, iconStack, iconAreaX, iconAreaY, iconAreaW, iconAreaH, entry.itemCount());
/*      */       } 
/*      */     } 
/*      */     
/*  590 */     int textPad = 2;
/*  591 */     int lineHeight = 11;
/*  592 */     int nameY = y + 7;
/*  593 */     int nameColor = entry.itemType().equals("POKEMON") ? ACCENT_CYAN : TEXT_PRIMARY;
/*  594 */     int nameMaxW = textAreaW - textPad * 2;
/*  595 */     class_5250 nameText = auctionText(entry.itemName());
/*  596 */     ctx.method_44379(textAreaX + textPad, nameY, textAreaX + textPad + nameMaxW, nameY + 10);
/*  597 */     drawCobblemonText(ctx, nameText, (textAreaX + textPad), nameY, 1.0F, nameColor, false, 9999);
/*  598 */     ctx.method_44380();
/*      */     
/*  600 */     int priceY = nameY + lineHeight;
/*  601 */     drawCobblemonText(ctx, auctionText(COIN_FORMAT.format((long)entry.price())), (textAreaX + textPad), priceY, 1.0F, ACCENT_GOLD, false, textAreaW - textPad * 2);
/*      */ 
/*      */     
/*  604 */     if (entry.isPokemon() && entry.ivPercentage() > 0) {
/*  605 */       int ivPct = entry.ivPercentage();
/*  606 */       float t = ivPct / 100.0F;
/*  607 */       int ivR = (int)(220.0F + -140.0F * t);
/*  608 */       int ivG = (int)(60.0F + 160.0F * t);
/*  609 */       int ivColor = c(ivR, ivG, 60, 255);
/*  610 */       drawCobblemonText(ctx, auctionText("" + ivPct + "% IVs"), (textAreaX + textPad), (priceY + lineHeight), 1.0F, ivColor, false, textAreaW - textPad * 2);
/*      */     } 
/*      */ 
/*      */     
/*  614 */     long agoMs = System.currentTimeMillis() - entry.soldAt();
/*  615 */     drawCobblemonText(ctx, auctionText(formatTimeAgo(agoMs)), (textAreaX + textPad), (y + 40), 1.0F, TEXT_DIM, false, textAreaW - 26);
/*  616 */     WidgetTexture statusTexture = entry.wasBidWon() ? TEX_WON_TAG : TEX_SOLD_TAG;
/*  617 */     drawTex(ctx, statusTexture.normal(), x + 138 - statusTexture.width() - 4, y + 34, statusTexture.width(), statusTexture.height());
/*      */   }
/*      */   
/*      */   private String formatTimeAgo(long ms) {
/*  621 */     if (ms < 0L) return "just now"; 
/*  622 */     long seconds = ms / 1000L;
/*  623 */     if (seconds < 60L) return "" + seconds + "s ago"; 
/*  624 */     long minutes = seconds / 60L;
/*  625 */     if (minutes < 60L) return "" + minutes + "m ago"; 
/*  626 */     long hours = minutes / 60L;
/*  627 */     if (hours < 24L) return "" + hours + "h ago"; 
/*  628 */     long days = hours / 24L;
/*  629 */     return "" + days + "d ago";
/*      */   }
/*      */   
/*      */   void drawAuctionCard(class_332 ctx, int x, int y, int w, int h, AuctionNetwork.AuctionListingEntry listing, boolean hovered, boolean pinHov, int cardIndex, float delta, long frameNow) {
/*  633 */     AuctionClientData data = AuctionClientData.getInstance();
/*  634 */     boolean pinned = data.isPinned(listing.auctionId());
/*  635 */     drawCardFrame(ctx, x, y, hovered, pinned, (hovered && !pinned));
/*      */     
/*  637 */     int iconAreaX = x + 4;
/*  638 */     int iconAreaY = y + 4;
/*  639 */     int iconAreaW = 43;
/*  640 */     int iconAreaH = 42;
/*  641 */     int textAreaX = x + 53;
/*  642 */     int textAreaW = 79;
/*      */     
/*  644 */     if (listing.isPokemon()) {
/*  645 */       Set<String> aspects = this.pokemonAspectsCache.computeIfAbsent(listing.auctionId(), k -> AuctionDetailPanel.extractPokemonAspects(listing.displayStack()));
/*      */       
/*  647 */       renderPokemonInCard(ctx, listing.speciesId(), aspects, iconAreaX, iconAreaY, iconAreaW, iconAreaH, cardIndex, delta);
/*  648 */     } else if (listing.displayStack() != null && !listing.displayStack().method_7960()) {
/*  649 */       drawStackInCard(ctx, listing.displayStack(), iconAreaX, iconAreaY, iconAreaW, iconAreaH, listing.displayStack().method_7947());
/*      */     } 
/*      */     
/*  652 */     int textPad = 2;
/*  653 */     int lineHeight = 11;
/*  654 */     int nameY = y + 7;
/*  655 */     int nameColor = listing.itemType().equals("POKEMON") ? ACCENT_CYAN : TEXT_PRIMARY;
/*  656 */     int nameMaxW = textAreaW - textPad * 2;
/*  657 */     class_5250 nameText = auctionText(listing.itemName());
/*  658 */     class_5250 class_52501 = nameText.method_27661().method_27696(class_2583.field_24360.method_27704(AUCTION_FONT).method_10978(Boolean.valueOf(false)));
/*  659 */     int nameTextW = this.field_22793.method_27525((class_5348)class_52501);
/*  660 */     int scrollOffset = 0;
/*  661 */     if (nameTextW > nameMaxW && hovered) {
/*  662 */       long now = System.currentTimeMillis();
/*  663 */       int overflow = nameTextW - nameMaxW;
/*  664 */       int scrollDurationMs = Math.max(overflow * 40, 800);
/*  665 */       int pauseMs = 1200;
/*  666 */       int totalCycleMs = pauseMs + scrollDurationMs + pauseMs;
/*  667 */       long phase = now % totalCycleMs;
/*  668 */       if (phase < pauseMs) {
/*  669 */         scrollOffset = 0;
/*  670 */       } else if (phase < (pauseMs + scrollDurationMs)) {
/*  671 */         scrollOffset = (int)((phase - pauseMs) * overflow / scrollDurationMs);
/*      */       } else {
/*  673 */         scrollOffset = overflow;
/*      */       } 
/*      */     } 
/*  676 */     ctx.method_44379(textAreaX + textPad, nameY, textAreaX + textPad + nameMaxW, nameY + 10);
/*  677 */     drawCobblemonText(ctx, nameText, (textAreaX + textPad - scrollOffset), nameY, 1.0F, nameColor, false, 9999);
/*  678 */     ctx.method_44380();
/*      */     
/*  680 */     boolean isPriceLookupCard = (listing.auctionId().isEmpty() || listing.expiresAt() == Long.MAX_VALUE);
/*  681 */     if (!isPriceLookupCard) {
/*  682 */       int priceY = nameY + lineHeight;
/*  683 */       drawCobblemonText(ctx, auctionText(COIN_FORMAT.format((long)listing.price())), (textAreaX + textPad), priceY, 1.0F, ACCENT_GOLD, false, textAreaW - textPad * 2);
/*      */ 
/*      */       
/*  686 */       if (listing.isPokemon() && listing.ivPercentage() > 0) {
/*  687 */         int ivPct = listing.ivPercentage();
/*  688 */         float t = ivPct / 100.0F;
/*  689 */         int ivR = (int)(220.0F + -140.0F * t);
/*  690 */         int ivG = (int)(60.0F + 160.0F * t);
/*  691 */         int ivColor = c(ivR, ivG, 60, 255);
/*  692 */         drawCobblemonText(ctx, auctionText("" + ivPct + "% IVs"), (textAreaX + textPad), (priceY + lineHeight), 1.0F, ivColor, false, textAreaW - textPad * 2);
/*      */       } 
/*      */ 
/*      */       
/*  696 */       int liveRemaining = Math.max(0, (int)((listing.expiresAt() - frameNow) / 1000L));
/*  697 */       int timeColor = (liveRemaining < 300) ? ACCENT_RED : TEXT_DIM;
/*  698 */       drawCobblemonText(ctx, auctionText(formatTime(liveRemaining)), (textAreaX + textPad), (y + 40), 1.0F, timeColor, false, textAreaW - 26);
/*      */       
/*  700 */       boolean bid = "BID".equalsIgnoreCase(listing.auctionType());
/*  701 */       WidgetTexture tagTexture = bid ? TEX_BID_TAG : TEX_BUY_TAG;
/*  702 */       drawTex(ctx, tagTexture.normal(), x + 107, y + 36, tagTexture.width(), tagTexture.height());
/*      */       
/*  704 */       if (!pinned && hovered) {
/*  705 */         int pinBtnX = x + 4;
/*  706 */         int pinBtnY = y + 4;
/*  707 */         drawTex(ctx, pinHov ? TEX_FAV_ICON.hover() : TEX_FAV_ICON.normal(), pinBtnX, pinBtnY, TEX_FAV_ICON.width(), TEX_FAV_ICON.height());
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void drawCardFrame(class_332 ctx, int x, int y, boolean hovered) {
/*  713 */     drawTex(ctx, hovered ? TEX_ITEM_PANEL.hover() : TEX_ITEM_PANEL.normal(), x, y, TEX_ITEM_PANEL.width(), TEX_ITEM_PANEL.height());
/*      */   }
/*      */   
/*      */   private void drawCardFrame(class_332 ctx, int x, int y, boolean hovered, boolean pinned, boolean showFavoriteIcon) {
/*  717 */     if (pinned) {
/*  718 */       drawTex(ctx, TEX_FAV_IDLE, x, y, TEX_ITEM_PANEL.width(), TEX_ITEM_PANEL.height());
/*      */       return;
/*      */     } 
/*  721 */     if (hovered && showFavoriteIcon) {
/*  722 */       drawCardFrame(ctx, x, y, true);
/*      */       return;
/*      */     } 
/*  725 */     drawCardFrame(ctx, x, y, hovered);
/*      */   }
/*      */   
/*      */   private void drawStackInCard(class_332 ctx, class_1799 stack, int iconAreaX, int iconAreaY, int iconAreaW, int iconAreaH, int count) {
/*  729 */     class_4587 mat = ctx.method_51448();
/*  730 */     mat.method_22903();
/*  731 */     float itemScale = 1.25F;
/*  732 */     int centeredIconX = iconAreaX + (iconAreaW - (int)(16.0F * itemScale)) / 2 + 2;
/*  733 */     int centeredIconY = iconAreaY + (iconAreaH - (int)(16.0F * itemScale)) / 2;
/*  734 */     mat.method_46416(centeredIconX, centeredIconY, 0.0F);
/*  735 */     mat.method_22905(itemScale, itemScale, 1.0F);
/*  736 */     mat.method_46416(-centeredIconX, -centeredIconY, 0.0F);
/*  737 */     ctx.method_51427(stack, centeredIconX, centeredIconY);
/*  738 */     mat.method_22909();
/*      */     
/*  740 */     if (count > 1) {
/*  741 */       class_5250 countText = auctionText("x" + count);
/*  742 */       drawCobblemonText(ctx, countText, (centeredIconX + (int)(16.0F * itemScale) - 2), (centeredIconY + (int)(16.0F * itemScale) - 8), 1.0F, TEXT_SECONDARY, false, iconAreaW);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void drawCardStatusText(class_332 ctx, String text, int x, int y, int color) {
/*  747 */     drawCobblemonText(ctx, auctionText(text), x + 12.5F, (y + 4), 0.9F, color, true, 25);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   static class_2561 buildTagText(class_1799 stack) {
/*  756 */     if (stack == null || stack.method_7960()) return null; 
/*  757 */     class_9279 cd = (class_9279)stack.method_57824(class_9334.field_49628);
/*  758 */     if (cd == null) return null; 
/*  759 */     class_2487 nbt = cd.method_57461();
/*  760 */     if (!nbt.method_10545("tag-note")) return null;
/*      */     
/*  762 */     String tagId = nbt.method_10558("tag-note").replace("-", "_").toLowerCase(Locale.ROOT);
/*  763 */     return (class_2561)class_2561.method_43471("tags." + tagId)
/*  764 */       .method_27696(class_2583.field_24360.method_27704(class_2960.method_60654("tags")).method_10978(Boolean.valueOf(false)));
/*      */   }
/*      */ 
/*      */   
/*      */   void renderTagInArea(class_332 ctx, class_2561 tagText, int x, int y, int areaW, int areaH) {
/*  769 */     int textW = this.field_22793.method_27525((class_5348)tagText);
/*  770 */     if (textW <= 0) textW = 8;
/*      */     
/*  772 */     Objects.requireNonNull(this.field_22793); float scale = Math.min(areaW * 0.65F / textW, areaH * 0.65F / 9.0F);
/*  773 */     scale = Math.max(scale, 0.5F);
/*  774 */     float scaledW = textW * scale;
/*  775 */     Objects.requireNonNull(this.field_22793); float scaledH = 9.0F * scale;
/*  776 */     float tx = x + areaW / 2.0F - scaledW / 2.0F;
/*  777 */     float ty = y + areaH / 2.0F - scaledH / 2.0F;
/*  778 */     class_4587 mat = ctx.method_51448();
/*  779 */     mat.method_22903();
/*  780 */     mat.method_46416(tx, ty, 0.0F);
/*  781 */     mat.method_22905(scale, scale, 1.0F);
/*  782 */     ctx.method_51439(this.field_22793, tagText, 0, 0, 16777215, false);
/*  783 */     mat.method_22909();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void drawCobblemonText(class_332 ctx, class_5250 text, float x, float y, float scale, int color, boolean centered, int maxWidth) {
/*  790 */     class_5250 styled = text.method_27661().method_27696(class_2583.field_24360.method_27704(AUCTION_FONT).method_10978(Boolean.valueOf(false)));
/*  791 */     int width = this.field_22793.method_27525((class_5348)styled);
/*  792 */     float drawX = centered ? (x - width * scale / 2.0F) : x;
/*      */     
/*  794 */     ctx.method_51448().method_22903();
/*  795 */     ctx.method_51448().method_46416(drawX, y, 0.0F);
/*  796 */     ctx.method_51448().method_22905(scale, scale, 1.0F);
/*  797 */     ctx.method_51439(this.field_22793, (class_2561)styled, 0, 0, color, false);
/*  798 */     ctx.method_51448().method_22909();
/*      */   }
/*      */   
/*      */   void renderPokemonInCard(class_332 ctx, String speciesStr, Set<String> aspects, int areaX, int areaY, int areaW, int areaH, int stateIndex, float delta) {
/*  802 */     if (speciesStr == null || speciesStr.isEmpty())
/*      */       return;  try {
/*  804 */       class_2960 speciesId = class_2960.method_60654(speciesStr);
/*  805 */       FloatingState state = this.pokemonStates.get(Integer.valueOf(stateIndex));
/*  806 */       if (state == null)
/*  807 */         return;  state.setCurrentAspects((aspects != null) ? aspects : Set.of());
/*      */       
/*  809 */       class_4587 mat = ctx.method_51448();
/*  810 */       mat.method_22903();
/*      */       
/*  812 */       mat.method_22904(areaX + areaW / 2.0D, areaY + areaH / 2.0D - 16.0D, 0.0D);
/*  813 */       float cardScale = getPokemonCardScale(speciesId, state);
/*  814 */       mat.method_22905(cardScale, cardScale, 1.0F);
/*  815 */       Quaternionf rot = new Quaternionf();
/*  816 */       rot.rotationXYZ((float)Math.toRadians(13.0D), (float)Math.toRadians(35.0D), 0.0F);
/*      */       
/*  818 */       if (PokemonRenderHelper.isAvailable()) {
/*  819 */         PokemonRenderHelper.draw(speciesId, mat, rot, state, delta);
/*      */       } else {
/*  821 */         String label = speciesStr.contains(":") ? speciesStr.substring(speciesStr.indexOf(":") + 1) : speciesStr;
/*  822 */         String initial = label.isEmpty() ? "?" : label.substring(0, 1).toUpperCase();
/*  823 */         ctx.method_25300(this.field_22793, initial, areaX + areaW / 2, areaY + areaH / 2, TEXT_PRIMARY);
/*      */       } 
/*  825 */       mat.method_22909();
/*  826 */     } catch (Exception e) {
/*      */       
/*  828 */       String label = speciesStr.contains(":") ? speciesStr.substring(speciesStr.indexOf(":") + 1) : speciesStr;
/*  829 */       ctx.method_25300(this.field_22793, label.substring(0, Math.min(3, label.length())), areaX + areaW / 2, areaY + areaH / 2, TEXT_DIM);
/*      */     } 
/*      */   }
/*      */   
/*      */   private float getPokemonCardScale(class_2960 speciesId, FloatingState state) {
/*      */     try {
/*  835 */       if ("celesteela".equals(speciesId.method_12832())) {
/*  836 */         return 1.6F;
/*      */       }
/*  838 */       float profileScale = VaryingModelRepository.INSTANCE.getPoser(speciesId, (PosableState)state).getProfileScale();
/*  839 */       return Math.max(2.2F, Math.min(4.0F, 2.4F + profileScale * 1.4F));
/*  840 */     } catch (Exception ignored) {
/*  841 */       return 3.2F;
/*      */     } 
/*      */   }
/*      */   
/*      */   private String formatTime(int totalSeconds) {
/*  846 */     if (totalSeconds <= 0) return "Expired"; 
/*  847 */     int hours = totalSeconds / 3600;
/*  848 */     int minutes = totalSeconds % 3600 / 60;
/*  849 */     if (hours > 0) return "" + hours + "h " + hours + "m"; 
/*  850 */     int seconds = totalSeconds % 60;
/*  851 */     if (minutes > 0) return "" + minutes + "m " + minutes + "s"; 
/*  852 */     return "" + seconds + "s";
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void drawFooter(class_332 ctx, int mouseX, int mouseY) {
/*  858 */     AuctionClientData data = AuctionClientData.getInstance();
/*  859 */     int footerY = this.guiTop + 320;
/*      */ 
/*      */     
/*  862 */     int refreshBtnX = this.guiLeft + 15;
/*  863 */     int refreshBtnY = footerY + 1;
/*  864 */     this.refreshHovered = drawTexturedWidget(ctx, TEX_REFRESH_BTN, refreshBtnX, refreshBtnY, mouseX, mouseY);
/*  865 */     drawButtonLabel(ctx, "Refresh", refreshBtnX, refreshBtnY, TEX_REFRESH_BTN.width(), TEX_REFRESH_BTN.height(), this.refreshHovered);
/*      */ 
/*      */     
/*  868 */     int myAucX = refreshBtnX + TEX_REFRESH_BTN.width() + 3;
/*  869 */     this.myAuctionsHovered = drawTexturedWidget(ctx, TEX_MY_AUCTIONS_BTN, myAucX, refreshBtnY, mouseX, mouseY);
/*  870 */     drawButtonLabel(ctx, "My Auctions", myAucX, refreshBtnY, TEX_MY_AUCTIONS_BTN.width(), TEX_MY_AUCTIONS_BTN.height(), this.myAuctionsHovered);
/*      */ 
/*      */     
/*  873 */     if (this.recentlySoldMode) {
/*  874 */       this.recentlySoldTotalPages = Math.max(1, data.getRecentlySoldTotalPages());
/*  875 */       this.recentlySoldPage = data.getRecentlySoldCurrentPage();
/*  876 */       this.totalPages = this.recentlySoldTotalPages;
/*  877 */       this.currentPage = this.recentlySoldPage;
/*      */     } else {
/*  879 */       this.totalPages = Math.max(1, data.getTotalPages());
/*  880 */       this.currentPage = data.getCurrentPage();
/*      */     } 
/*      */     
/*  883 */     String pageText = this.recentlySoldMode ? ("Page " + this.currentPage) : ("Page " + this.currentPage + " / " + this.totalPages);
/*  884 */     int pageCenterX = this.guiLeft + 225 + 8;
/*  885 */     ctx.method_51433(this.field_22793, pageText, pageCenterX - this.field_22793.method_1727(pageText) / 2, footerY + 5, TEXT_PRIMARY, false);
/*      */ 
/*      */     
/*  888 */     int pageTextW = this.field_22793.method_27525((class_5348)auctionText(pageText));
/*  889 */     int prevX = pageCenterX - pageTextW / 2 - 12 - 8;
/*  890 */     int nextX = pageCenterX + pageTextW / 2 + 5 + 8;
/*  891 */     int arrowY = footerY + 3;
/*      */     
/*  893 */     if (this.currentPage > 1) {
/*  894 */       this.prevPageHovered = (mouseX >= prevX && mouseX < prevX + 8 && mouseY >= arrowY && mouseY < arrowY + 10);
/*  895 */       ctx.method_51433(this.field_22793, "◀", prevX + 1, arrowY + 3, this.prevPageHovered ? ACCENT_GOLD : TEXT_PRIMARY, false);
/*      */     } 
/*  897 */     if (this.currentPage < this.totalPages) {
/*  898 */       this.nextPageHovered = (mouseX >= nextX && mouseX < nextX + 8 && mouseY >= arrowY && mouseY < arrowY + 10);
/*  899 */       ctx.method_51433(this.field_22793, "▶", nextX + 1, arrowY + 3, this.nextPageHovered ? ACCENT_GOLD : TEXT_PRIMARY, false);
/*      */     } 
/*      */ 
/*      */     
/*  903 */     String balanceText = COIN_FORMAT.format((long)data.getBalance()) + " Coins";
/*  904 */     int balanceW = this.field_22793.method_1727(balanceText);
/*  905 */     int balanceX = this.guiLeft + 450 - 15 - balanceW - 6;
/*  906 */     int balanceY = footerY + 3;
/*  907 */     drawTex(ctx, TEX_COINS_ICON, balanceX - 20, footerY + 4, 16, 7);
/*  908 */     ctx.method_51433(this.field_22793, balanceText, balanceX, balanceY, TEXT_GOLD, false);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void drawDropdown(class_332 ctx, int mouseX, int mouseY, String[] options, int x, int y, int w, boolean isSort) {
/*  914 */     class_4587 matrices = ctx.method_51448();
/*  915 */     matrices.method_22903();
/*  916 */     matrices.method_46416(0.0F, 0.0F, 300.0F);
/*      */     
/*  918 */     int itemH = 14;
/*  919 */     int totalH = options.length * itemH;
/*      */ 
/*      */     
/*  922 */     ctx.method_25294(x - 1, y - 1, x + w + 1, y + totalH + 1, c(0, 0, 0, 200));
/*  923 */     ctx.method_25294(x, y, x + w, y + totalH, DROPDOWN_BG);
/*  924 */     ctx.method_49601(x, y, w, totalH, DROPDOWN_BORDER);
/*      */     
/*  926 */     for (int i = 0; i < options.length; i++) {
/*  927 */       int itemY = y + i * itemH;
/*  928 */       boolean hov = (mouseX >= x && mouseX < x + w && mouseY >= itemY && mouseY < itemY + itemH);
/*  929 */       boolean selected = isSort ? ((i == this.selectedSortIndex)) : ((i == this.selectedTypeIndex));
/*      */       
/*  931 */       if (hov) {
/*  932 */         if (isSort) { this.hoveredSortIndex = i; } else { this.hoveredTypeIndex = i; }
/*  933 */          ctx.method_25294(x + 1, itemY, x + w - 1, itemY + itemH, DROPDOWN_HOVER);
/*      */       } 
/*      */       
/*  936 */       int textCol = selected ? DROPDOWN_SELECTED : (hov ? TEXT_PRIMARY : TEXT_SECONDARY);
/*  937 */       String text = options[i];
/*  938 */       int maxTW = w - 8;
/*  939 */       while (this.field_22793.method_27525((class_5348)auctionText(text)) > maxTW && text.length() > 2) {
/*  940 */         text = text.substring(0, text.length() - 1);
/*      */       }
/*  942 */       drawCobblemonText(ctx, auctionText(text), (x + 6), (itemY + 3), 1.0F, textCol, false, maxTW);
/*      */ 
/*      */       
/*  945 */       if (selected) {
/*  946 */         ctx.method_25294(x + 1, itemY, x + 3, itemY + itemH, DROPDOWN_SELECTED);
/*      */       }
/*      */ 
/*      */       
/*  950 */       if (i < options.length - 1) {
/*  951 */         ctx.method_25294(x + 4, itemY + itemH - 1, x + w - 4, itemY + itemH, c(72, 78, 130, 90));
/*      */       }
/*      */     } 
/*      */     
/*  955 */     matrices.method_22909();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void drawConfirmOverlay(class_332 ctx, int mouseX, int mouseY) {
/*  961 */     this.confirmYesHovered = this.confirmNoHovered = false;
/*      */     
/*  963 */     class_4587 matrices = ctx.method_51448();
/*  964 */     matrices.method_22903();
/*  965 */     matrices.method_46416(0.0F, 0.0F, 400.0F);
/*      */     
/*  967 */     ctx.method_25294(0, 0, this.field_22789, this.field_22790, c(0, 0, 0, 150));
/*      */     
/*  969 */     AuctionClientData data = AuctionClientData.getInstance();
/*  970 */     AuctionNetwork.AuctionListingEntry listing = findListingById(this.confirmListingId);
/*  971 */     if (listing == null) {
/*  972 */       this.showConfirm = false;
/*  973 */       matrices.method_22909();
/*      */       
/*      */       return;
/*      */     } 
/*  977 */     int popupScale = 1;
/*  978 */     int popupW = 128 * popupScale;
/*  979 */     int popupH = 80 * popupScale;
/*  980 */     int popupX = (this.field_22789 - popupW) / 2;
/*  981 */     int popupY = (this.field_22790 - popupH) / 2;
/*  982 */     drawTex(ctx, TEX_CONFIRM_POPUP_BG, popupX, popupY, popupW, popupH);
/*      */     
/*  984 */     float contentScale = popupScale;
/*  985 */     float px = popupX;
/*  986 */     float py = popupY;
/*      */     
/*  988 */     drawCobblemonText(ctx, auctionText("CONFIRM TRADE"), px + 80.0F * contentScale, py + 5.0F * contentScale, 1.0F, TEXT_SECONDARY, true, 120);
/*      */     
/*  990 */     String verb = "Buy for";
/*  991 */     String amountText = COIN_FORMAT.format((long)listing.price()) + " Coins";
/*  992 */     int gap1 = 3;
/*  993 */     int gap2 = 3;
/*  994 */     int verbW = this.field_22793.method_27525((class_5348)auctionText(verb));
/*  995 */     int amountW = this.field_22793.method_27525((class_5348)auctionText(amountText));
/*  996 */     int questionW = this.field_22793.method_27525((class_5348)auctionText("?"));
/*  997 */     int totalLineW = verbW + gap1 + amountW + gap2 + questionW;
/*  998 */     int lineStartX = popupX + (popupW - totalLineW) / 2;
/*  999 */     int lineY = popupY + 25;
/* 1000 */     drawCobblemonText(ctx, auctionText(verb), lineStartX, lineY, 1.0F, TEXT_PRIMARY, false, verbW + 2);
/* 1001 */     drawCobblemonText(ctx, auctionText(amountText), (lineStartX + verbW + gap1), lineY, 1.0F, TEXT_GOLD, false, amountW + 2);
/* 1002 */     drawCobblemonText(ctx, auctionText("?"), (lineStartX + verbW + gap1 + amountW + gap2), lineY, 1.0F, TEXT_PRIMARY, false, questionW + 2);
/*      */     
/* 1004 */     drawTex(ctx, TEX_COINS_ICON, (int)(px + 7.0F * contentScale), (int)(py + 38.0F * contentScale), 16, 7);
/* 1005 */     drawCobblemonText(ctx, auctionText("BALANCE :"), px + 26.0F * contentScale, py + 38.0F * contentScale, 1.0F, TEXT_GOLD, false, 72);
/* 1006 */     drawCobblemonText(ctx, auctionText(COIN_FORMAT.format((long)data.getBalance())), px + 70.0F * contentScale, py + 38.0F * contentScale, 1.0F, TEXT_PRIMARY, false, 60);
/*      */     
/* 1008 */     int btnW = TEX_CONFIRM_POPUP_CONFIRM.width() * popupScale;
/* 1009 */     int btnH = TEX_CONFIRM_POPUP_CONFIRM.height() * popupScale;
/* 1010 */     int cancelX = popupX + 13 * popupScale;
/* 1011 */     int btnY = popupY + 58 * popupScale;
/* 1012 */     int confirmX = popupX + 67 * popupScale;
/*      */     
/* 1014 */     this.confirmNoHovered = (mouseX >= cancelX && mouseX < cancelX + btnW && mouseY >= btnY && mouseY < btnY + btnH);
/* 1015 */     this.confirmYesHovered = (mouseX >= confirmX && mouseX < confirmX + btnW && mouseY >= btnY && mouseY < btnY + btnH);
/* 1016 */     drawTex(ctx, this.confirmNoHovered ? TEX_CONFIRM_POPUP_CANCEL.hover() : TEX_CONFIRM_POPUP_CANCEL.normal(), cancelX, btnY, btnW, btnH);
/* 1017 */     drawTex(ctx, this.confirmYesHovered ? TEX_CONFIRM_POPUP_CONFIRM.hover() : TEX_CONFIRM_POPUP_CONFIRM.normal(), confirmX, btnY, btnW, btnH);
/*      */     
/* 1019 */     matrices.method_22909();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean drawSmallButton(class_332 ctx, int x, int y, int w, int h, String text, int mx, int my) {
/* 1025 */     boolean hov = (mx >= x && mx < x + w && my >= y && my < y + h);
/* 1026 */     int bg = hov ? c(50, 44, 32, 255) : c(38, 34, 26, 255);
/* 1027 */     ctx.method_25294(x + 1, y + 1, x + w - 1, y + h - 1, bg);
/*      */ 
/*      */     
/* 1030 */     if (hov) {
/* 1031 */       ctx.method_25294(x + 1, y + h - 3, x + w - 1, y + h - 1, RankedStyledButton.darken(bg, 12));
/*      */     }
/*      */     
/* 1034 */     int bc = hov ? ACCENT_WARM : BORDER_DARK;
/* 1035 */     ctx.method_25294(x + 1, y, x + w - 1, y + 1, bc);
/* 1036 */     ctx.method_25294(x + 1, y + h - 1, x + w - 1, y + h, RankedStyledButton.darken(bc, 20));
/* 1037 */     ctx.method_25294(x, y + 1, x + 1, y + h - 1, bc);
/* 1038 */     ctx.method_25294(x + w - 1, y + 1, x + w, y + h - 1, RankedStyledButton.darken(bc, 20));
/*      */ 
/*      */     
/* 1041 */     ctx.method_25294(x + 2, y + 1, x + w - 2, y + 2, RankedStyledButton.lighten(bg, 15));
/*      */ 
/*      */     
/* 1044 */     ctx.method_25294(x, y, x + 1, y + 1, 0);
/* 1045 */     ctx.method_25294(x + w - 1, y, x + w, y + 1, 0);
/* 1046 */     ctx.method_25294(x, y + h - 1, x + 1, y + h, 0);
/* 1047 */     ctx.method_25294(x + w - 1, y + h - 1, x + w, y + h, 0);
/*      */     
/* 1049 */     ctx.method_25300(this.field_22793, text, x + w / 2, y + (h - 8) / 2, hov ? TEXT_PRIMARY : TEXT_SECONDARY);
/* 1050 */     return hov;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean method_25402(double mouseX, double mouseY, int button) {
/* 1060 */     if (this.showingDetail && this.detailPanel != null) {
/* 1061 */       return this.detailPanel.mouseClicked(mouseX, mouseY, button);
/*      */     }
/*      */     
/* 1064 */     if (button != 0) return super.method_25402(mouseX, mouseY, button);
/*      */ 
/*      */     
/* 1067 */     if (this.showConfirm) {
/* 1068 */       if (this.confirmYesHovered) {
/* 1069 */         playClickSound();
/* 1070 */         AuctionNetwork.AuctionListingEntry listing = findListingById(this.confirmListingId);
/* 1071 */         if (listing != null) {
/* 1072 */           AuctionNetwork.requestBuy(listing.auctionId());
/*      */         }
/* 1074 */         this.confirmListingId = null;
/* 1075 */         this.showConfirm = false;
/* 1076 */         return true;
/*      */       } 
/* 1078 */       if (this.confirmNoHovered) {
/* 1079 */         playClickSound();
/* 1080 */         this.confirmListingId = null;
/* 1081 */         this.showConfirm = false;
/* 1082 */         return true;
/*      */       } 
/* 1084 */       return true;
/*      */     } 
/*      */ 
/*      */     
/* 1088 */     if (this.sortDropdownOpen) {
/* 1089 */       if (this.hoveredSortIndex >= 0) {
/* 1090 */         playClickSound();
/* 1091 */         this.selectedSortIndex = this.hoveredSortIndex;
/* 1092 */         this.sortDropdownOpen = false;
/* 1093 */         requestRefresh();
/* 1094 */         return true;
/*      */       } 
/* 1096 */       this.sortDropdownOpen = false;
/* 1097 */       return true;
/*      */     } 
/*      */ 
/*      */     
/* 1101 */     if (this.typeDropdownOpen) {
/* 1102 */       if (this.hoveredTypeIndex >= 0) {
/* 1103 */         playClickSound();
/* 1104 */         this.selectedTypeIndex = this.hoveredTypeIndex;
/* 1105 */         this.typeDropdownOpen = false;
/* 1106 */         requestRefresh();
/* 1107 */         return true;
/*      */       } 
/* 1109 */       this.typeDropdownOpen = false;
/* 1110 */       return true;
/*      */     } 
/*      */ 
/*      */     
/* 1114 */     if (this.closeHovered) {
/* 1115 */       playClickSound();
/* 1116 */       method_25419();
/* 1117 */       return true;
/*      */     } 
/*      */ 
/*      */     
/* 1121 */     if (this.sortBtnHovered) {
/* 1122 */       playClickSound();
/* 1123 */       this.sortDropdownOpen = !this.sortDropdownOpen;
/* 1124 */       this.typeDropdownOpen = false;
/* 1125 */       return true;
/*      */     } 
/*      */ 
/*      */     
/* 1129 */     if (this.typeBtnHovered) {
/* 1130 */       playClickSound();
/* 1131 */       this.typeDropdownOpen = !this.typeDropdownOpen;
/* 1132 */       this.sortDropdownOpen = false;
/* 1133 */       return true;
/*      */     } 
/*      */ 
/*      */     
/* 1137 */     boolean clickedSearch = (mouseX >= this.searchBarX && mouseX < (this.searchBarX + this.searchBarW) && mouseY >= this.searchBarY && mouseY < (this.searchBarY + this.searchBarH));
/*      */     
/* 1139 */     if (clickedSearch) {
/*      */       
/* 1141 */       if (!this.searchText.isEmpty() && mouseX >= (this.searchBarX + this.searchBarW - 12)) {
/* 1142 */         this.searchText = "";
/* 1143 */         this.cursorPos = 0;
/* 1144 */         requestRefresh();
/* 1145 */         return true;
/*      */       } 
/* 1147 */       this.searchFocused = true;
/* 1148 */       this.lastBlinkTime = System.currentTimeMillis();
/* 1149 */       return true;
/*      */     } 
/* 1151 */     this.searchFocused = false;
/*      */ 
/*      */ 
/*      */     
/* 1155 */     if (this.searchBtnHovered) {
/* 1156 */       playClickSound();
/* 1157 */       if (this.recentlySoldMode) {
/* 1158 */         this.recentlySoldMode = false;
/*      */       }
/* 1160 */       requestRefresh();
/* 1161 */       return true;
/*      */     } 
/*      */ 
/*      */     
/* 1165 */     if (this.recentlySoldBackHovered) {
/* 1166 */       playClickSound();
/* 1167 */       this.recentlySoldMode = false;
/* 1168 */       this.sortDropdownOpen = false;
/* 1169 */       this.typeDropdownOpen = false;
/* 1170 */       this.searchFocused = false;
/* 1171 */       requestRefresh();
/* 1172 */       return true;
/*      */     } 
/*      */ 
/*      */     
/* 1176 */     if (this.recentlySoldHovered) {
/* 1177 */       playClickSound();
/* 1178 */       this.recentlySoldMode = !this.recentlySoldMode;
/* 1179 */       this.sortDropdownOpen = false;
/* 1180 */       this.typeDropdownOpen = false;
/* 1181 */       this.searchFocused = false;
/* 1182 */       if (this.recentlySoldMode) {
/* 1183 */         requestRecentlySoldPage(1);
/*      */       } else {
/* 1185 */         requestRefresh();
/*      */       } 
/* 1187 */       return true;
/*      */     } 
/*      */ 
/*      */     
/* 1191 */     if (this.refreshHovered) {
/* 1192 */       playClickSound();
/* 1193 */       if (this.recentlySoldMode) {
/* 1194 */         requestRecentlySoldPage(1);
/*      */       } else {
/* 1196 */         requestRefresh();
/*      */       } 
/* 1198 */       return true;
/*      */     } 
/*      */ 
/*      */     
/* 1202 */     if (this.myAuctionsHovered) {
/* 1203 */       playClickSound();
/* 1204 */       class_310.method_1551().method_1507(new MyAuctionsScreen());
/* 1205 */       return true;
/*      */     } 
/*      */ 
/*      */     
/* 1209 */     if (this.prevPageHovered && this.currentPage > 1) {
/* 1210 */       playClickSound();
/* 1211 */       if (this.recentlySoldMode) {
/* 1212 */         requestRecentlySoldPage(this.currentPage - 1);
/*      */       } else {
/* 1214 */         requestPage(this.currentPage - 1);
/*      */       } 
/* 1216 */       return true;
/*      */     } 
/* 1218 */     if (this.nextPageHovered && this.currentPage < this.totalPages) {
/* 1219 */       playClickSound();
/* 1220 */       if (this.recentlySoldMode) {
/* 1221 */         requestRecentlySoldPage(this.currentPage + 1);
/*      */       } else {
/* 1223 */         requestPage(this.currentPage + 1);
/*      */       } 
/* 1225 */       return true;
/*      */     } 
/*      */ 
/*      */     
/* 1229 */     if (this.pinHoveredIndex >= 0) {
/* 1230 */       AuctionClientData data = AuctionClientData.getInstance();
/* 1231 */       List<AuctionNetwork.AuctionListingEntry> listings = data.getPinnedFirst(data.getListings());
/* 1232 */       if (this.pinHoveredIndex < listings.size()) {
/* 1233 */         playClickSound();
/* 1234 */         data.togglePin(((AuctionNetwork.AuctionListingEntry)listings.get(this.pinHoveredIndex)).auctionId());
/* 1235 */         return true;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1240 */     if (this.hoveredCardIndex >= 0) {
/* 1241 */       if (this.recentlySoldMode) {
/* 1242 */         AuctionClientData data = AuctionClientData.getInstance();
/* 1243 */         List<AuctionNetwork.RecentlySoldEntry> entries = data.getRecentlySoldEntries();
/* 1244 */         if (this.hoveredCardIndex < entries.size()) {
/* 1245 */           playClickSound();
/* 1246 */           AuctionNetwork.RecentlySoldEntry entry = entries.get(this.hoveredCardIndex);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1252 */           AuctionNetwork.AuctionListingEntry syntheticListing = new AuctionNetwork.AuctionListingEntry(entry.auctionId(), entry.itemName(), entry.sellerName(), entry.price(), entry.auctionType(), entry.itemType(), 0, entry.itemCount(), entry.displayStack(), entry.speciesId(), entry.soldAt(), entry.ivPercentage());
/*      */           
/* 1254 */           openDetail(syntheticListing);
/* 1255 */           return true;
/*      */         } 
/*      */       } else {
/* 1258 */         AuctionClientData data = AuctionClientData.getInstance();
/* 1259 */         List<AuctionNetwork.AuctionListingEntry> listings = data.getPinnedFirst(data.getListings());
/* 1260 */         if (this.hoveredCardIndex < listings.size()) {
/* 1261 */           playClickSound();
/* 1262 */           AuctionNetwork.AuctionListingEntry listing = listings.get(this.hoveredCardIndex);
/* 1263 */           openDetail(listing);
/* 1264 */           return true;
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/* 1269 */     return super.method_25402(mouseX, mouseY, button);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean method_25404(int keyCode, int scanCode, int modifiers) {
/* 1274 */     if (this.showingDetail && this.detailPanel != null) {
/* 1275 */       if (this.detailPanel.keyPressed(keyCode, scanCode, modifiers)) return true; 
/* 1276 */       if (keyCode == 256) { closeDetail(); return true; }
/* 1277 */        return true;
/*      */     } 
/*      */     
/* 1280 */     if (keyCode == 256) {
/* 1281 */       if (this.showConfirm) {
/* 1282 */         this.showConfirm = false;
/* 1283 */         return true;
/*      */       } 
/* 1285 */       if (this.sortDropdownOpen || this.typeDropdownOpen) {
/* 1286 */         this.sortDropdownOpen = this.typeDropdownOpen = false;
/* 1287 */         return true;
/*      */       } 
/* 1289 */       if (this.searchFocused) {
/* 1290 */         this.searchFocused = false;
/* 1291 */         return true;
/*      */       } 
/* 1293 */       method_25419();
/* 1294 */       return true;
/*      */     } 
/*      */     
/* 1297 */     if (this.searchFocused) {
/* 1298 */       switch (keyCode) {
/*      */         case 259:
/* 1300 */           if (this.cursorPos > 0) {
/* 1301 */             this.searchText = this.searchText.substring(0, this.cursorPos - 1) + this.searchText.substring(0, this.cursorPos - 1);
/* 1302 */             this.cursorPos--;
/* 1303 */             this.lastBlinkTime = System.currentTimeMillis();
/*      */           } 
/* 1305 */           return true;
/*      */         
/*      */         case 261:
/* 1308 */           if (this.cursorPos < this.searchText.length()) {
/* 1309 */             this.searchText = this.searchText.substring(0, this.cursorPos) + this.searchText.substring(0, this.cursorPos);
/* 1310 */             this.lastBlinkTime = System.currentTimeMillis();
/*      */           } 
/* 1312 */           return true;
/*      */         
/*      */         case 263:
/* 1315 */           if (this.cursorPos > 0) { this.cursorPos--; this.lastBlinkTime = System.currentTimeMillis(); }
/* 1316 */            return true;
/*      */         
/*      */         case 264:
/* 1319 */           return true;
/*      */         
/*      */         case 262:
/* 1322 */           if (this.cursorPos < this.searchText.length()) { this.cursorPos++; this.lastBlinkTime = System.currentTimeMillis(); }
/* 1323 */            return true;
/*      */         case 268:
/* 1325 */           this.cursorPos = 0; this.lastBlinkTime = System.currentTimeMillis(); return true;
/* 1326 */         case 269: this.cursorPos = this.searchText.length(); this.lastBlinkTime = System.currentTimeMillis(); return true;
/*      */         case 257: case 335:
/* 1328 */           requestRefresh();
/* 1329 */           this.searchFocused = false;
/* 1330 */           return true;
/*      */       } 
/*      */       
/* 1333 */       return true;
/*      */     } 
/*      */     
/* 1336 */     return super.method_25404(keyCode, scanCode, modifiers);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean method_25400(char chr, int modifiers) {
/* 1341 */     if (this.showingDetail && this.detailPanel != null) {
/* 1342 */       return this.detailPanel.charTyped(chr, modifiers);
/*      */     }
/* 1344 */     if (this.searchFocused) {
/* 1345 */       if (chr < ' ') return false; 
/* 1346 */       if (this.searchText.length() >= 32) return false; 
/* 1347 */       this.searchText = this.searchText.substring(0, this.cursorPos) + this.searchText.substring(0, this.cursorPos) + chr;
/* 1348 */       this.cursorPos++;
/* 1349 */       this.lastBlinkTime = System.currentTimeMillis();
/* 1350 */       return true;
/*      */     } 
/* 1352 */     return super.method_25400(chr, modifiers);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean method_25403(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
/* 1357 */     if (this.showingDetail && this.detailPanel != null) {
/* 1358 */       return this.detailPanel.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
/*      */     }
/* 1360 */     return super.method_25403(mouseX, mouseY, button, deltaX, deltaY);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean method_25406(double mouseX, double mouseY, int button) {
/* 1365 */     if (this.showingDetail && this.detailPanel != null) {
/* 1366 */       return this.detailPanel.mouseReleased(mouseX, mouseY, button);
/*      */     }
/* 1368 */     return super.method_25406(mouseX, mouseY, button);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean method_25401(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
/* 1373 */     if (this.showingDetail && this.detailPanel != null) {
/* 1374 */       return this.detailPanel.mouseScrolled(mouseX, mouseY, verticalAmount);
/*      */     }
/* 1376 */     return super.method_25401(mouseX, mouseY, horizontalAmount, verticalAmount);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private AuctionNetwork.AuctionListingEntry findListingById(String auctionId) {
/* 1382 */     if (auctionId == null || auctionId.isEmpty()) return null; 
/* 1383 */     AuctionClientData data = AuctionClientData.getInstance();
/* 1384 */     for (AuctionNetwork.AuctionListingEntry listing : data.getListings()) {
/* 1385 */       if (auctionId.equals(listing.auctionId())) return listing; 
/*      */     } 
/* 1387 */     return null;
/*      */   }
/*      */   
/*      */   private void openDetail(AuctionNetwork.AuctionListingEntry listing) {
/* 1391 */     if (!canSendRequest())
/* 1392 */       return;  AuctionClientData data = AuctionClientData.getInstance();
/* 1393 */     data.setSelectedListing(listing);
/* 1394 */     data.setDetailLoading(true);
/* 1395 */     this.detailPanel = new AuctionDetailPanel(this.guiLeft, this.guiTop, 450, 350, this::closeDetail, this::playClickSound);
/* 1396 */     if (this.recentlySoldMode) {
/* 1397 */       this.detailPanel.setRecentlySoldMode(true);
/*      */     }
/* 1399 */     this.detailPanel.init(listing);
/* 1400 */     this.showingDetail = true;
/*      */ 
/*      */ 
/*      */     
/* 1404 */     String filterKey = "";
/* 1405 */     if (listing.displayStack() != null && !listing.displayStack().method_7960()) {
/*      */       
/* 1407 */       class_9279 cd = (class_9279)listing.displayStack().method_57824(class_9334.field_49628);
/* 1408 */       boolean isPlushie = (cd != null && cd.method_57461().method_10545("plushie"));
/* 1409 */       if (isPlushie) {
/*      */         
/* 1411 */         filterKey = buildItemFilterKey(listing.displayStack());
/* 1412 */       } else if (listing.isPokemon() && listing.speciesId() != null && !listing.speciesId().isEmpty()) {
/* 1413 */         filterKey = listing.speciesId();
/*      */       } else {
/* 1415 */         filterKey = buildItemFilterKey(listing.displayStack());
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1420 */     this.previewedCosmetics.clear();
/* 1421 */     if (listing.displayStack() != null) {
/* 1422 */       class_9279 cd = (class_9279)listing.displayStack().method_57824(class_9334.field_49628);
/* 1423 */       if (cd != null && cd.method_57461().method_10545("cosmetic-note")) {
/* 1424 */         String cosmeticId = cd.method_57461().method_10558("cosmetic-note");
/* 1425 */         Cosmetic cosmetic = CosmeticRepository.INSTANCE.get(cosmeticId);
/* 1426 */         if (cosmetic != null) {
/*      */           
/* 1428 */           for (CosmeticCategory cat : CosmeticCategory.values()) {
/* 1429 */             this.previewedCosmetics.put(cat, null);
/*      */           }
/*      */           
/* 1432 */           this.previewedCosmetics.put(cosmetic.getCategory(), new EquippedCosmetic(cosmetic, null));
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1439 */     AuctionNetwork.requestAuctionDetail(listing.auctionId(), listing.itemType(), filterKey);
/*      */   }
/*      */   
/*      */   void closeDetail() {
/* 1443 */     this.showingDetail = false;
/* 1444 */     this.detailPanel = null;
/* 1445 */     this.previewedCosmetics.clear();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static String buildItemFilterKey(class_1799 stack) {
/* 1454 */     String baseId = class_7923.field_41178.method_10221(stack.method_7909()).toString();
/* 1455 */     class_9279 customData = (class_9279)stack.method_57824(class_9334.field_49628);
/* 1456 */     if (customData != null) {
/* 1457 */       class_2487 nbt = customData.method_57461();
/*      */ 
/*      */       
/* 1460 */       if (nbt.method_10545("plushie")) {
/* 1461 */         String species = extractSpeciesFromStack(stack);
/* 1462 */         if (!species.isEmpty()) {
/* 1463 */           return "plushie:" + species;
/*      */         }
/* 1465 */         return baseId + "#plushie:" + baseId;
/*      */       } 
/*      */       
/* 1468 */       if (nbt.method_10545("cosmetic-note")) {
/* 1469 */         return baseId + "#cosmetic:" + baseId;
/*      */       }
/*      */       
/* 1472 */       if (nbt.method_10545("tag-note")) {
/* 1473 */         return baseId + "#tag:" + baseId;
/*      */       }
/*      */       
/* 1476 */       if (nbt.method_10545("dungeons:custom_tool")) {
/* 1477 */         return baseId + "#dungeons:" + baseId;
/*      */       }
/*      */       
/* 1480 */       if (nbt.method_10545("megastone_item")) {
/* 1481 */         return baseId + "#megastone:" + baseId;
/*      */       }
/*      */       
/* 1484 */       class_9280 cmd = (class_9280)stack.method_57824(class_9334.field_49637);
/* 1485 */       if (cmd != null) {
/* 1486 */         return baseId + "#cmd:" + baseId;
/*      */       }
/*      */     } 
/* 1489 */     return baseId;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   static class_1799 getCosmeticDisplayStack(class_1799 stack) {
/* 1499 */     if (stack == null || stack.method_7960()) return null; 
/* 1500 */     class_9279 cd = (class_9279)stack.method_57824(class_9334.field_49628);
/* 1501 */     if (cd == null) return null; 
/* 1502 */     class_2487 nbt = cd.method_57461();
/*      */     
/* 1504 */     String cosmeticId = null;
/* 1505 */     if (nbt.method_10545("cosmetic-note")) {
/* 1506 */       cosmeticId = nbt.method_10558("cosmetic-note");
/*      */     }
/* 1508 */     if (cosmeticId == null || cosmeticId.isEmpty()) return null;
/*      */     
/* 1510 */     Cosmetic cosmetic = CosmeticRepository.INSTANCE.get(cosmeticId);
/* 1511 */     if (cosmetic == null) return null;
/*      */ 
/*      */     
/* 1514 */     return (class_1799)cosmetic.getDisplayModel().fold(bedrock -> (class_1799)null, customModel -> {
/*      */           class_1792 previewItem = customModel.getType().getPreviewItem();
/*      */           if (previewItem != null) {
/*      */             class_1799 displayStack = new class_1799((class_1935)previewItem);
/*      */             displayStack.method_57379(class_9334.field_49637, new class_9280(customModel.getSteveModelId()));
/*      */             return displayStack;
/*      */           } 
/*      */           return customModel.getSteveStack();
/*      */         });
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static String extractSpeciesFromStack(class_1799 stack) {
/* 1536 */     class_9279 customData = (class_9279)stack.method_57824(class_9334.field_49628);
/* 1537 */     if (customData != null) {
/* 1538 */       class_2487 nbt = customData.method_57461();
/* 1539 */       if (nbt.method_10545("pokemon_payload")) {
/*      */         try {
/* 1541 */           JsonObject json = JsonParser.parseString(nbt.method_10558("pokemon_payload")).getAsJsonObject();
/* 1542 */           if (json.has("Species")) return json.get("Species").getAsString(); 
/* 1543 */         } catch (Exception exception) {}
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*      */     try {
/* 1549 */       class_2520 encoded = stack.method_57358((class_7225.class_7874)(class_310.method_1551()).field_1687.method_30349());
/* 1550 */       String nbtStr = encoded.toString();
/*      */       
/* 1552 */       Matcher m = Pattern.compile("species\\s*:\\s*\"?(cobblemon:[^\"\\},]+)").matcher(nbtStr);
/* 1553 */       if (m.find()) return m.group(1); 
/* 1554 */     } catch (Exception exception) {}
/* 1555 */     return "";
/*      */   }
/*      */   
/*      */   private void requestRefresh() {
/* 1559 */     if (!canSendRequest())
/* 1560 */       return;  AuctionNetwork.requestAuctionPage(1, getSortKey(), getTypeKey(), this.searchText);
/* 1561 */     AuctionClientData.getInstance().setLoading(true);
/*      */   }
/*      */   
/*      */   private void requestPage(int page) {
/* 1565 */     if (!canSendRequest())
/* 1566 */       return;  AuctionNetwork.requestAuctionPage(page, getSortKey(), getTypeKey(), this.searchText);
/* 1567 */     AuctionClientData.getInstance().setLoading(true);
/*      */   }
/*      */   
/*      */   private void requestRecentlySoldPage(int page) {
/* 1571 */     if (!canSendRequest())
/* 1572 */       return;  AuctionNetwork.requestRecentlySold(page);
/* 1573 */     AuctionClientData.getInstance().setRecentlySoldLoading(true);
/*      */   }
/*      */   
/*      */   private void playClickSound() {
/* 1577 */     class_310.method_1551().method_1483().method_4873(
/* 1578 */         (class_1113)class_1109.method_47978((class_6880)class_3417.field_15015, 1.0F));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void method_25420(class_332 context, int mouseX, int mouseY, float delta) {}
/*      */ 
/*      */   
/*      */   public boolean method_25421() {
/* 1587 */     return false;
/*      */   }
/*      */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\auction\screen\AuctionHouseScreen.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */