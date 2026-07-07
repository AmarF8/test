/*     */ package com.atlas.common.fabric.guide.screen.views;
/*     */ 
/*     */ import com.atlas.common.fabric.guide.data.GuideData;
/*     */ import com.atlas.common.fabric.guide.data.MoveInfo;
/*     */ import com.atlas.common.fabric.guide.network.GuideNetwork;
/*     */ import com.atlas.common.fabric.guide.screen.GuideColors;
/*     */ import com.atlas.common.fabric.guide.screen.GuideSounds;
/*     */ import com.atlas.common.fabric.guide.screen.widgets.GuideScrollableArea;
/*     */ import com.atlas.common.fabric.guide.screen.widgets.GuideSearchBar;
/*     */ import com.atlas.common.fabric.guide.screen.widgets.GuideTypeBadge;
/*     */ import java.util.List;
/*     */ import java.util.function.Consumer;
/*     */ import net.minecraft.class_327;
/*     */ import net.minecraft.class_332;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MoveListView
/*     */ {
/*     */   private static final int SEARCH_BAR_HEIGHT = 16;
/*     */   private static final int FILTER_HEIGHT = 18;
/*     */   private static final int COLUMN_HEADER_HEIGHT = 12;
/*     */   private static final int HEADER_HEIGHT = 60;
/*     */   private static final int ROW_HEIGHT = 18;
/*  28 */   private static final String[] CATEGORY_FILTERS = new String[] { "All", "Physical", "Special", "Status" };
/*     */ 
/*     */   
/*  31 */   private static final String[] TYPE_NAMES = new String[] { "normal", "fire", "water", "electric", "grass", "ice", "fighting", "poison", "ground", "flying", "psychic", "bug", "rock", "ghost", "dragon", "dark", "steel", "fairy" };
/*     */   
/*     */   private int x;
/*     */   
/*     */   private int y;
/*     */   private int width;
/*     */   private int height;
/*     */   private GuideSearchBar searchBar;
/*     */   private GuideScrollableArea scrollArea;
/*  40 */   private String currentSearch = "";
/*  41 */   private String currentTypeFilter = "";
/*  42 */   private String currentCategoryFilter = "All";
/*     */   private boolean initialized = false;
/*  44 */   private long lastRequestTime = 0L;
/*     */   
/*     */   private Consumer<String> onMoveSelected;
/*     */   
/*  48 */   private String pendingSearch = null;
/*  49 */   private long pendingSearchTime = 0L;
/*     */   
/*     */   private static final long SEARCH_DEBOUNCE_MS = 300L;
/*     */   
/*  53 */   private int hoveredRowIndex = -1;
/*  54 */   private int hoveredCategoryIndex = -1;
/*     */   
/*     */   private boolean hoveredClearType = false;
/*     */ 
/*     */   
/*     */   public void init(int x, int y, int width, int height, Consumer<String> onMoveSelected) {
/*  60 */     this.x = x;
/*  61 */     this.y = y;
/*  62 */     this.width = width;
/*  63 */     this.height = height;
/*  64 */     this.onMoveSelected = onMoveSelected;
/*     */     
/*  66 */     if (!this.initialized) {
/*  67 */       this.searchBar = new GuideSearchBar(x + 4, y + 4, width - 8, 16);
/*  68 */       this.searchBar.setPlaceholder("Search moves...");
/*  69 */       this.searchBar.setOnTextChanged(text -> {
/*     */             this.pendingSearch = text; this.pendingSearchTime = System.currentTimeMillis();
/*  71 */           }); int scrollTop = y + 60;
/*  72 */       int scrollHeight = height - 60;
/*  73 */       this.scrollArea = new GuideScrollableArea(x, scrollTop, width, scrollHeight);
/*  74 */       this.initialized = true;
/*  75 */       requestPage(0);
/*     */     } else {
/*  77 */       updateBounds(x, y, width, height);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void searchForMove(String moveName) {
/*  86 */     if (this.searchBar != null) {
/*  87 */       this.searchBar.setText(moveName);
/*     */     }
/*  89 */     this.currentSearch = moveName;
/*  90 */     this.currentCategoryFilter = "All";
/*  91 */     this.currentTypeFilter = "";
/*  92 */     this.pendingSearch = null;
/*  93 */     GuideData.getInstance().resetMoves();
/*  94 */     if (this.scrollArea != null) this.scrollArea.resetScroll(); 
/*  95 */     requestPage(0);
/*     */   }
/*     */   
/*     */   public void updateBounds(int x, int y, int width, int height) {
/*  99 */     this.x = x; this.y = y; this.width = width; this.height = height;
/* 100 */     if (this.searchBar != null) this.searchBar.updateBounds(x + 4, y + 4, width - 8, 16); 
/* 101 */     if (this.scrollArea != null) this.scrollArea.updateBounds(x, y + 60, width, height - 60);
/*     */   
/*     */   }
/*     */   
/*     */   public void render(class_332 ctx, class_327 textRenderer, int mouseX, int mouseY, float delta) {
/* 106 */     if (this.pendingSearch != null && System.currentTimeMillis() - this.pendingSearchTime > 300L) {
/* 107 */       this.currentSearch = this.pendingSearch;
/* 108 */       this.pendingSearch = null;
/* 109 */       GuideData.getInstance().resetMoves();
/* 110 */       this.scrollArea.resetScroll();
/* 111 */       requestPage(0);
/*     */     } 
/*     */     
/* 114 */     ctx.method_25294(this.x, this.y, this.x + this.width, this.y + this.height, GuideColors.SECTION_BG);
/* 115 */     this.searchBar.render(ctx, textRenderer, mouseX, mouseY);
/*     */ 
/*     */     
/* 118 */     renderCategoryFilters(ctx, textRenderer, mouseX, mouseY);
/*     */ 
/*     */     
/* 121 */     renderColumnHeaders(ctx, textRenderer);
/*     */     
/* 123 */     GuideData data = GuideData.getInstance();
/* 124 */     List<MoveInfo> moves = data.getAllMoveEntries();
/*     */ 
/*     */     
/* 127 */     int totalHeight = moves.size() * 18 + 4;
/* 128 */     this.scrollArea.setContentHeight(totalHeight);
/* 129 */     this.scrollArea.beginRender(ctx);
/*     */     
/* 131 */     this.hoveredRowIndex = -1;
/* 132 */     int cy = this.scrollArea.getY() + 2 - this.scrollArea.getScrollOffset();
/*     */     
/* 134 */     for (int i = 0; i < moves.size(); i++) {
/* 135 */       MoveInfo move = moves.get(i);
/*     */       
/* 137 */       if (cy > this.scrollArea.getY() + this.scrollArea.getHeight() + 20)
/* 138 */         break;  if (cy + 18 < this.scrollArea.getY()) {
/* 139 */         cy += 18;
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */         
/* 145 */         boolean isHovered = (mouseX >= this.x && mouseX < this.x + this.width && mouseY >= cy && mouseY < cy + 18 && this.scrollArea.isInBounds(mouseX, mouseY));
/*     */         
/* 147 */         if (isHovered) this.hoveredRowIndex = i;
/*     */ 
/*     */         
/* 150 */         int rowBg = isHovered ? GuideColors.CARD_HOVER_BG : ((i % 2 == 0) ? GuideColors.SECTION_BG : GuideColors.CARD_BG);
/* 151 */         ctx.method_25294(this.x + 2, cy, this.x + this.width - 2, cy + 18, rowBg);
/*     */         
/* 153 */         int textY = cy + 5;
/*     */ 
/*     */         
/* 156 */         int nameColor = isHovered ? GuideColors.TEXT_WHITE : GuideColors.TEXT_PRIMARY;
/* 157 */         ctx.method_51433(textRenderer, move.name, this.x + 6, textY, nameColor, true);
/*     */ 
/*     */         
/* 160 */         int typeX = this.x + 130;
/* 161 */         if (!move.type.isEmpty()) {
/* 162 */           GuideTypeBadge.draw(ctx, textRenderer, move.type, typeX, textY - 1);
/*     */         }
/*     */ 
/*     */         
/* 166 */         int catX = this.x + 210;
/* 167 */         int catColor = getCategoryColor(move.category);
/*     */         
/* 169 */         String catLabel = move.category.isEmpty() ? "-" : (move.category.substring(0, 1).toUpperCase() + move.category.substring(0, 1).toUpperCase());
/* 170 */         ctx.method_51433(textRenderer, catLabel, catX, textY, catColor, true);
/*     */ 
/*     */         
/* 173 */         int pwrX = this.x + 290;
/* 174 */         String pwr = (move.power > 0) ? String.valueOf(move.power) : "-";
/* 175 */         ctx.method_51433(textRenderer, pwr, pwrX, textY, GuideColors.TEXT_PRIMARY, true);
/*     */ 
/*     */         
/* 178 */         int accX = this.x + 330;
/* 179 */         String acc = (move.accuracy > 0) ? ("" + move.accuracy + "%") : "-";
/* 180 */         ctx.method_51433(textRenderer, acc, accX, textY, GuideColors.TEXT_PRIMARY, true);
/*     */ 
/*     */         
/* 183 */         int ppX = this.x + 380;
/* 184 */         String pp = (move.pp > 0) ? String.valueOf(move.pp) : "-";
/* 185 */         ctx.method_51433(textRenderer, pp, ppX, textY, GuideColors.TEXT_DIM, true);
/*     */         
/* 187 */         cy += 18;
/*     */       } 
/*     */     } 
/* 190 */     this.scrollArea.endRender(ctx);
/*     */ 
/*     */     
/* 193 */     if (data.isMoveLoading()) {
/* 194 */       ctx.method_25300(textRenderer, "Loading...", this.x + this.width / 2, this.scrollArea
/* 195 */           .getY() + this.scrollArea.getHeight() - 14, GuideColors.TEXT_DIM);
/*     */     }
/*     */     
/* 198 */     if (moves.isEmpty() && !data.isMoveLoading()) {
/* 199 */       ctx.method_25300(textRenderer, "No moves found", this.x + this.width / 2, this.scrollArea
/* 200 */           .getY() + this.scrollArea.getHeight() / 2, GuideColors.TEXT_DIM);
/*     */     }
/*     */ 
/*     */     
/* 204 */     if (!moves.isEmpty() && data.hasMoreMovePages() && !data.isMoveLoading()) {
/* 205 */       int maxScroll = this.scrollArea.getMaxScroll();
/* 206 */       int threshold = Math.max(300, this.scrollArea.getHeight());
/* 207 */       if (maxScroll <= 0 || this.scrollArea.getScrollOffset() >= maxScroll - threshold) {
/* 208 */         long now = System.currentTimeMillis();
/* 209 */         if (now - this.lastRequestTime > 500L) {
/* 210 */           requestPage(data.getMoveCurrentPage() + 1);
/* 211 */           this.lastRequestTime = now;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void renderCategoryFilters(class_332 ctx, class_327 textRenderer, int mouseX, int mouseY) {
/* 218 */     int filterY = this.y + 16 + 6;
/* 219 */     int fx = this.x + 4;
/*     */     
/* 221 */     this.hoveredCategoryIndex = -1;
/* 222 */     this.hoveredClearType = false;
/*     */     
/* 224 */     for (int fi = 0; fi < CATEGORY_FILTERS.length; fi++) {
/* 225 */       String cat = CATEGORY_FILTERS[fi];
/* 226 */       int tw = textRenderer.method_1727(cat) + 8;
/* 227 */       boolean active = cat.equals(this.currentCategoryFilter);
/* 228 */       boolean hovered = (mouseX >= fx && mouseX < fx + tw && mouseY >= filterY && mouseY < filterY + 12);
/*     */       
/* 230 */       if (hovered) this.hoveredCategoryIndex = fi;
/*     */       
/* 232 */       int bg = active ? GuideColors.ACCENT_DARK_GREEN : (hovered ? GuideColors.CARD_HOVER_BG : GuideColors.CARD_BG);
/* 233 */       ctx.method_25294(fx, filterY, fx + tw, filterY + 12, bg);
/* 234 */       ctx.method_49601(fx, filterY, tw, 12, active ? GuideColors.ACCENT_EMERALD : GuideColors.BORDER_DIM);
/*     */       
/* 236 */       int textColor = active ? GuideColors.TEXT_WHITE : (hovered ? GuideColors.TEXT_PRIMARY : GuideColors.TEXT_DIM);
/* 237 */       ctx.method_25300(textRenderer, cat, fx + tw / 2, filterY + 2, textColor);
/*     */       
/* 239 */       fx += tw + 3;
/*     */     } 
/*     */ 
/*     */     
/* 243 */     if (!this.currentTypeFilter.isEmpty()) {
/* 244 */       fx += 10;
/* 245 */       ctx.method_51433(textRenderer, "Type: ", fx, filterY + 2, GuideColors.TEXT_DIM, true);
/* 246 */       fx += textRenderer.method_1727("Type: ");
/* 247 */       GuideTypeBadge.draw(ctx, textRenderer, this.currentTypeFilter, fx, filterY + 1);
/* 248 */       fx += textRenderer.method_1727(this.currentTypeFilter) + 12;
/*     */ 
/*     */       
/* 251 */       boolean clearHovered = (mouseX >= fx && mouseX < fx + 8 && mouseY >= filterY && mouseY < filterY + 12);
/* 252 */       this.hoveredClearType = clearHovered;
/* 253 */       ctx.method_51433(textRenderer, "×", fx, filterY + 2, 
/* 254 */           clearHovered ? GuideColors.TEXT_WHITE : GuideColors.TEXT_DIM, true);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void renderColumnHeaders(class_332 ctx, class_327 textRenderer) {
/* 259 */     int headerY = this.y + 16 + 18 + 10;
/*     */     
/* 261 */     ctx.method_25294(this.x + 2, headerY, this.x + this.width - 2, headerY + 12, GuideColors.CARD_BG);
/*     */     
/* 263 */     int textY = headerY + 2;
/* 264 */     ctx.method_51433(textRenderer, "Name", this.x + 6, textY, GuideColors.TEXT_DIM, true);
/* 265 */     ctx.method_51433(textRenderer, "Type", this.x + 130, textY, GuideColors.TEXT_DIM, true);
/* 266 */     ctx.method_51433(textRenderer, "Category", this.x + 210, textY, GuideColors.TEXT_DIM, true);
/* 267 */     ctx.method_51433(textRenderer, "Pwr", this.x + 290, textY, GuideColors.TEXT_DIM, true);
/* 268 */     ctx.method_51433(textRenderer, "Acc", this.x + 330, textY, GuideColors.TEXT_DIM, true);
/* 269 */     ctx.method_51433(textRenderer, "PP", this.x + 380, textY, GuideColors.TEXT_DIM, true);
/*     */ 
/*     */     
/* 272 */     ctx.method_25294(this.x + 2, headerY + 12 - 1, this.x + this.width - 2, headerY + 12, GuideColors.BORDER_DIM);
/*     */   }
/*     */   
/*     */   private int getCategoryColor(String category) {
/* 276 */     if (category == null) return GuideColors.TEXT_DIM; 
/* 277 */     switch (category.toLowerCase()) { case "physical": case "special": case "status":  }  return 
/*     */ 
/*     */ 
/*     */       
/* 281 */       GuideColors.TEXT_DIM;
/*     */   }
/*     */ 
/*     */   
/*     */   private void requestPage(int page) {
/* 286 */     GuideData.getInstance().setMoveLoading(true);
/* 287 */     String filter = this.currentCategoryFilter.equals("All") ? "" : this.currentCategoryFilter.toLowerCase();
/* 288 */     if (!this.currentTypeFilter.isEmpty()) {
/* 289 */       filter = (filter.isEmpty() ? "" : (filter + ":")) + "type=" + (filter.isEmpty() ? "" : (filter + ":"));
/*     */     }
/* 291 */     GuideNetwork.requestGuideData("moves", page, this.currentSearch, filter);
/* 292 */     this.lastRequestTime = System.currentTimeMillis();
/*     */   }
/*     */   
/*     */   public boolean mouseClicked(double mouseX, double mouseY, int button) {
/* 296 */     if (this.searchBar.mouseClicked(mouseX, mouseY, button)) return true;
/*     */ 
/*     */     
/* 299 */     if (button == 0 && this.hoveredCategoryIndex >= 0 && this.hoveredCategoryIndex < CATEGORY_FILTERS.length) {
/* 300 */       String cat = CATEGORY_FILTERS[this.hoveredCategoryIndex];
/* 301 */       if (!cat.equals(this.currentCategoryFilter)) {
/* 302 */         GuideSounds.playClick();
/* 303 */         this.currentCategoryFilter = cat;
/* 304 */         GuideData.getInstance().resetMoves();
/* 305 */         this.scrollArea.resetScroll();
/* 306 */         requestPage(0);
/*     */       } 
/* 308 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 312 */     int filterY = this.y + 16 + 6;
/* 313 */     if (mouseY >= filterY && mouseY < (filterY + 12) && button == 0 && 
/* 314 */       !this.currentTypeFilter.isEmpty() && this.hoveredClearType) {
/* 315 */       this.currentTypeFilter = "";
/* 316 */       GuideData.getInstance().resetMoves();
/* 317 */       this.scrollArea.resetScroll();
/* 318 */       requestPage(0);
/* 319 */       return true;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 324 */     if (this.scrollArea.handleMouseClicked(mouseX, mouseY, button)) return true;
/*     */ 
/*     */     
/* 327 */     if (button == 0 && this.hoveredRowIndex >= 0) {
/* 328 */       List<MoveInfo> moves = GuideData.getInstance().getAllMoveEntries();
/* 329 */       if (this.hoveredRowIndex < moves.size() && this.onMoveSelected != null) {
/* 330 */         GuideSounds.playNavigate();
/* 331 */         this.onMoveSelected.accept(((MoveInfo)moves.get(this.hoveredRowIndex)).name);
/* 332 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/* 336 */     return false;
/*     */   }
/*     */   
/*     */   public boolean mouseScrolled(double mouseX, double mouseY, double h, double v) {
/* 340 */     return this.scrollArea.handleScroll(mouseX, mouseY, v);
/*     */   }
/*     */   
/*     */   public boolean mouseDragged(double mouseX, double mouseY, int button) {
/* 344 */     return this.scrollArea.handleMouseDragged(mouseX, mouseY, button);
/*     */   }
/*     */   
/*     */   public boolean mouseReleased(double mouseX, double mouseY, int button) {
/* 348 */     return this.scrollArea.handleMouseReleased(mouseX, mouseY, button);
/*     */   }
/*     */   
/*     */   public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
/* 352 */     return this.searchBar.keyPressed(keyCode, scanCode, modifiers);
/*     */   }
/*     */   
/*     */   public boolean charTyped(char chr, int modifiers) {
/* 356 */     return this.searchBar.charTyped(chr, modifiers);
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\guide\screen\views\MoveListView.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */