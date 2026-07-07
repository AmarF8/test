/*     */ package com.atlas.common.fabric.guide.screen.views;
/*     */ 
/*     */ import com.atlas.common.fabric.guide.data.GuideData;
/*     */ import com.atlas.common.fabric.guide.data.RideableInfo;
/*     */ import com.atlas.common.fabric.guide.network.GuideNetwork;
/*     */ import com.atlas.common.fabric.guide.screen.GuideColors;
/*     */ import com.atlas.common.fabric.guide.screen.GuideSounds;
/*     */ import com.atlas.common.fabric.guide.screen.widgets.GuideScrollableArea;
/*     */ import com.atlas.common.fabric.guide.screen.widgets.GuideSearchBar;
/*     */ import com.atlas.common.fabric.guide.screen.widgets.GuideTypeBadge;
/*     */ import com.atlas.common.fabric.screen.PokemonRenderHelper;
/*     */ import com.cobblemon.mod.common.client.render.models.blockbench.FloatingState;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.function.Consumer;
/*     */ import net.minecraft.class_2960;
/*     */ import net.minecraft.class_327;
/*     */ import net.minecraft.class_332;
/*     */ import net.minecraft.class_4587;
/*     */ import org.joml.Quaternionf;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RideableListView
/*     */ {
/*     */   private static final int SEARCH_BAR_HEIGHT = 16;
/*     */   private static final int FILTER_HEIGHT = 18;
/*     */   private static final int HEADER_HEIGHT = 44;
/*     */   private static final int CARD_NAME_ROW = 16;
/*     */   private static final int STAT_ROW_HEIGHT = 10;
/*     */   private static final int CARD_SPACING = 3;
/*     */   private static final int MODEL_SIZE = 30;
/*  41 */   private static final String[] MOVEMENT_FILTERS = new String[] { "All", "Land", "Water", "Fly" }; private int x; private int y;
/*     */   private int width;
/*     */   private int height;
/*     */   private GuideSearchBar searchBar;
/*     */   private GuideScrollableArea scrollArea;
/*  46 */   private String currentSearch = "";
/*  47 */   private String currentMovementFilter = "All";
/*     */   private boolean initialized = false;
/*  49 */   private long lastRequestTime = 0L;
/*     */   
/*     */   private Consumer<String> onPokemonSelected;
/*     */   
/*  53 */   private String pendingSearch = null;
/*  54 */   private long pendingSearchTime = 0L;
/*     */   
/*     */   private static final long SEARCH_DEBOUNCE_MS = 300L;
/*     */   
/*  58 */   private int hoveredCardIndex = -1;
/*  59 */   private int hoveredFilterIndex = -1;
/*     */ 
/*     */   
/*  62 */   private final Map<String, FloatingState> modelStates = new HashMap<>();
/*  63 */   private final Set<String> failedModelSpecies = new HashSet<>();
/*     */   
/*     */   public RideableListView() {
/*  66 */     PokemonRenderHelper.init();
/*     */   }
/*     */   
/*     */   public void init(int x, int y, int width, int height, Consumer<String> onPokemonSelected) {
/*  70 */     this.x = x;
/*  71 */     this.y = y;
/*  72 */     this.width = width;
/*  73 */     this.height = height;
/*  74 */     this.onPokemonSelected = onPokemonSelected;
/*     */     
/*  76 */     if (!this.initialized) {
/*  77 */       this.searchBar = new GuideSearchBar(x + 4, y + 4, width - 8, 16);
/*  78 */       this.searchBar.setPlaceholder("Search rideable...");
/*  79 */       this.searchBar.setOnTextChanged(text -> {
/*     */             this.pendingSearch = text; this.pendingSearchTime = System.currentTimeMillis();
/*  81 */           }); int scrollTop = y + 44;
/*  82 */       int scrollHeight = height - 44;
/*  83 */       this.scrollArea = new GuideScrollableArea(x, scrollTop, width, scrollHeight);
/*  84 */       this.initialized = true;
/*  85 */       requestPage(0);
/*     */     } else {
/*  87 */       updateBounds(x, y, width, height);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void updateBounds(int x, int y, int width, int height) {
/*  92 */     this.x = x; this.y = y; this.width = width; this.height = height;
/*  93 */     if (this.searchBar != null) this.searchBar.updateBounds(x + 4, y + 4, width - 8, 16); 
/*  94 */     if (this.scrollArea != null) this.scrollArea.updateBounds(x, y + 44, width, height - 44);
/*     */   
/*     */   }
/*     */   
/*     */   public void render(class_332 ctx, class_327 textRenderer, int mouseX, int mouseY, float delta) {
/*  99 */     if (this.pendingSearch != null && System.currentTimeMillis() - this.pendingSearchTime > 300L) {
/* 100 */       this.currentSearch = this.pendingSearch;
/* 101 */       this.pendingSearch = null;
/* 102 */       GuideData.getInstance().resetRideable();
/* 103 */       this.scrollArea.resetScroll();
/* 104 */       requestPage(0);
/*     */     } 
/*     */     
/* 107 */     ctx.method_25294(this.x, this.y, this.x + this.width, this.y + this.height, GuideColors.SECTION_BG);
/* 108 */     this.searchBar.render(ctx, textRenderer, mouseX, mouseY);
/*     */ 
/*     */     
/* 111 */     renderMovementFilters(ctx, textRenderer, mouseX, mouseY);
/*     */     
/* 113 */     GuideData data = GuideData.getInstance();
/* 114 */     List<RideableInfo> entries = data.getAllRideableEntries();
/*     */ 
/*     */     
/* 117 */     int totalHeight = 4;
/* 118 */     for (RideableInfo entry : entries) {
/* 119 */       totalHeight += getCardHeight(entry) + 3;
/*     */     }
/* 121 */     this.scrollArea.setContentHeight(totalHeight);
/* 122 */     this.scrollArea.beginRender(ctx);
/*     */     
/* 124 */     this.hoveredCardIndex = -1;
/* 125 */     int cy = this.scrollArea.getY() + 2 - this.scrollArea.getScrollOffset();
/*     */     
/* 127 */     for (int i = 0; i < entries.size(); i++) {
/* 128 */       RideableInfo entry = entries.get(i);
/* 129 */       int cardH = getCardHeight(entry);
/*     */       
/* 131 */       if (cy > this.scrollArea.getY() + this.scrollArea.getHeight() + 50)
/* 132 */         break;  if (cy + cardH < this.scrollArea.getY()) {
/* 133 */         cy += cardH + 3;
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */         
/* 139 */         boolean isHovered = (mouseX >= this.x + 4 && mouseX < this.x + this.width - 4 && mouseY >= cy && mouseY < cy + cardH && this.scrollArea.isInBounds(mouseX, mouseY));
/*     */         
/* 141 */         if (isHovered) {
/* 142 */           this.hoveredCardIndex = i;
/* 143 */           GuideSounds.playHover("rideable:" + i);
/*     */         } 
/*     */         
/* 146 */         renderCard(ctx, textRenderer, entry, this.x + 4, cy, this.width - 8, isHovered, mouseX, mouseY);
/* 147 */         cy += cardH + 3;
/*     */       } 
/*     */     } 
/* 150 */     this.scrollArea.endRender(ctx);
/*     */ 
/*     */     
/* 153 */     if (data.isRideableLoading()) {
/* 154 */       ctx.method_25300(textRenderer, "Loading...", this.x + this.width / 2, this.scrollArea
/* 155 */           .getY() + this.scrollArea.getHeight() - 14, GuideColors.TEXT_DIM);
/*     */     }
/*     */     
/* 158 */     if (entries.isEmpty() && !data.isRideableLoading()) {
/* 159 */       ctx.method_25300(textRenderer, "No rideable Pokemon found", this.x + this.width / 2, this.scrollArea
/* 160 */           .getY() + this.scrollArea.getHeight() / 2, GuideColors.TEXT_DIM);
/*     */     }
/*     */ 
/*     */     
/* 164 */     if (!entries.isEmpty() && data.hasMoreRideablePages() && !data.isRideableLoading()) {
/* 165 */       int maxScroll = this.scrollArea.getMaxScroll();
/* 166 */       int threshold = Math.max(300, this.scrollArea.getHeight());
/* 167 */       if (maxScroll <= 0 || this.scrollArea.getScrollOffset() >= maxScroll - threshold) {
/* 168 */         long now = System.currentTimeMillis();
/* 169 */         if (now - this.lastRequestTime > 500L) {
/* 170 */           requestPage(data.getRideableCurrentPage() + 1);
/* 171 */           this.lastRequestTime = now;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void renderMovementFilters(class_332 ctx, class_327 textRenderer, int mouseX, int mouseY) {
/* 178 */     int filterY = this.y + 16 + 6;
/* 179 */     int fx = this.x + 4;
/*     */     
/* 181 */     this.hoveredFilterIndex = -1;
/*     */     
/* 183 */     for (int fi = 0; fi < MOVEMENT_FILTERS.length; fi++) {
/* 184 */       String filter = MOVEMENT_FILTERS[fi];
/* 185 */       int tw = textRenderer.method_1727(filter) + 8;
/* 186 */       boolean active = filter.equals(this.currentMovementFilter);
/* 187 */       boolean hovered = (mouseX >= fx && mouseX < fx + tw && mouseY >= filterY && mouseY < filterY + 12);
/*     */       
/* 189 */       if (hovered) this.hoveredFilterIndex = fi;
/*     */       
/* 191 */       int bg = active ? getMovementBgColor(filter) : (hovered ? GuideColors.CARD_HOVER_BG : GuideColors.CARD_BG);
/* 192 */       ctx.method_25294(fx, filterY, fx + tw, filterY + 12, bg);
/* 193 */       ctx.method_49601(fx, filterY, tw, 12, 
/* 194 */           active ? getMovementAccentColor(filter) : GuideColors.BORDER_DIM);
/*     */       
/* 196 */       int textColor = active ? GuideColors.TEXT_WHITE : (hovered ? GuideColors.TEXT_PRIMARY : GuideColors.TEXT_DIM);
/* 197 */       ctx.method_25300(textRenderer, filter, fx + tw / 2, filterY + 2, textColor);
/*     */       
/* 199 */       fx += tw + 3;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static int getCardHeight(RideableInfo entry) {
/* 204 */     int statCount = (entry.stats != null) ? entry.stats.size() : 0;
/* 205 */     int statsH = statCount * 10;
/*     */     
/* 207 */     int contentH = Math.max(statsH, 30);
/* 208 */     return 16 + contentH + 4;
/*     */   }
/*     */ 
/*     */   
/*     */   private void renderCard(class_332 ctx, class_327 textRenderer, RideableInfo entry, int cx, int cy, int cw, boolean hovered, int mouseX, int mouseY) {
/* 213 */     int cardH = getCardHeight(entry);
/*     */ 
/*     */     
/* 216 */     int bg = hovered ? GuideColors.CARD_HOVER_BG : GuideColors.CARD_BG;
/* 217 */     ctx.method_25294(cx, cy, cx + cw, cy + cardH, bg);
/*     */     
/* 219 */     if (hovered) {
/* 220 */       ctx.method_49601(cx, cy, cw, cardH, GuideColors.BORDER_HIGHLIGHT);
/*     */     }
/*     */ 
/*     */     
/* 224 */     String dexStr = String.format("#%03d ", new Object[] { Integer.valueOf(entry.id) });
/* 225 */     ctx.method_51433(textRenderer, dexStr, cx + 4, cy + 4, GuideColors.TEXT_DIM, true);
/* 226 */     int nameX = cx + 4 + textRenderer.method_1727(dexStr);
/* 227 */     int nameColor = hovered ? GuideColors.TEXT_WHITE : GuideColors.TEXT_PRIMARY;
/* 228 */     ctx.method_51433(textRenderer, entry.name, nameX, cy + 4, nameColor, true);
/*     */ 
/*     */     
/* 231 */     int typeX = nameX + textRenderer.method_1727(entry.name) + 6;
/* 232 */     for (String type : entry.types) {
/* 233 */       int tw = GuideTypeBadge.draw(ctx, textRenderer, type, typeX, cy + 3);
/* 234 */       typeX += tw + 2;
/*     */     } 
/*     */ 
/*     */     
/* 238 */     int movementColor = getMovementAccentColor(capitalize(entry.movementType));
/* 239 */     String movementLabel = capitalize(entry.movementType);
/* 240 */     int mvX = cx + cw - textRenderer.method_1727(movementLabel) - 8;
/* 241 */     ctx.method_25294(mvX - 2, cy + 2, mvX + textRenderer.method_1727(movementLabel) + 2, cy + 12, GuideColors.darken(movementColor, 80));
/* 242 */     ctx.method_51433(textRenderer, movementLabel, mvX, cy + 3, movementColor, true);
/*     */ 
/*     */     
/* 245 */     String seats = "" + entry.seats + " seat" + entry.seats;
/* 246 */     int seatsX = mvX - textRenderer.method_1727(seats) - 8;
/* 247 */     ctx.method_51433(textRenderer, seats, seatsX, cy + 4, GuideColors.TEXT_DIM, true);
/*     */ 
/*     */     
/* 250 */     int contentY = cy + 16;
/*     */ 
/*     */     
/* 253 */     renderSmallModel(ctx, entry.slug, cx + 4, contentY, 30);
/*     */ 
/*     */     
/* 256 */     if (entry.stats != null && !entry.stats.isEmpty()) {
/* 257 */       int statX = cx + 4 + 30 + 6;
/* 258 */       int statAreaW = cw - 30 - 14;
/* 259 */       int labelW = 72;
/* 260 */       int valueW = 18;
/* 261 */       int barX = statX + labelW + valueW;
/* 262 */       int barW = statAreaW - labelW - valueW - 4;
/* 263 */       int barH = 3;
/* 264 */       if (barW < 20) barW = 20;
/*     */       
/* 266 */       int barStartY = contentY + 2;
/* 267 */       int statIdx = 0;
/* 268 */       for (RideableInfo.RideStat stat : entry.stats) {
/* 269 */         String label = stat.displayName.isEmpty() ? stat.key : stat.displayName;
/*     */         
/* 271 */         if (textRenderer.method_1727(label) > labelW - 2) {
/* 272 */           while (label.length() > 2 && textRenderer.method_1727(label + "..") > labelW - 2) {
/* 273 */             label = label.substring(0, label.length() - 1);
/*     */           }
/* 275 */           label = label + "..";
/*     */         } 
/* 277 */         String value = String.valueOf(stat.maxValue);
/* 278 */         int statColor = getRideStatColor(statIdx);
/*     */         
/* 280 */         ctx.method_51433(textRenderer, label, statX, barStartY + 1, GuideColors.TEXT_DIM, true);
/* 281 */         ctx.method_51433(textRenderer, value, statX + labelW, barStartY + 1, GuideColors.TEXT_PRIMARY, true);
/* 282 */         ctx.method_25294(barX, barStartY + 3, barX + barW, barStartY + 3 + barH, GuideColors.BORDER_DIM);
/*     */         
/* 284 */         double ratio = Math.min(1.0D, stat.maxValue / 100.0D);
/* 285 */         int filled = (int)(barW * ratio);
/* 286 */         if (filled > 0) {
/* 287 */           ctx.method_25294(barX, barStartY + 3, barX + filled, barStartY + 3 + barH, statColor);
/*     */         }
/*     */         
/* 290 */         barStartY += 10;
/* 291 */         statIdx++;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void renderSmallModel(class_332 ctx, String slug, int sx, int sy, int ss) {
/* 297 */     if (!PokemonRenderHelper.isAvailable() || slug == null || slug.isEmpty())
/* 298 */       return;  if (this.failedModelSpecies.contains(slug))
/*     */       return;  try {
/* 300 */       class_2960 speciesId = class_2960.method_60654("cobblemon:" + slug);
/* 301 */       FloatingState state = this.modelStates.computeIfAbsent(slug, k -> new FloatingState());
/* 302 */       state.setCurrentAspects(Set.of());
/*     */       
/* 304 */       class_4587 mat = ctx.method_51448();
/* 305 */       mat.method_22903();
/* 306 */       mat.method_22904(sx + ss / 2.0D, sy + 2.0D, 0.0D);
/* 307 */       float scale = ss / 9.0F;
/* 308 */       mat.method_22905(scale, scale, 1.0F);
/*     */       
/* 310 */       Quaternionf rot = new Quaternionf();
/* 311 */       rot.rotationXYZ((float)Math.toRadians(13.0D), (float)Math.toRadians(35.0D), 0.0F);
/*     */       
/* 313 */       PokemonRenderHelper.draw(speciesId, mat, rot, state, 0.0F);
/* 314 */       mat.method_22909();
/* 315 */     } catch (Exception e) {
/* 316 */       this.failedModelSpecies.add(slug);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static String capitalize(String s) {
/* 321 */     if (s == null || s.isEmpty()) return "?"; 
/* 322 */     return s.substring(0, 1).toUpperCase() + s.substring(0, 1).toUpperCase();
/*     */   }
/*     */   
/*     */   private static int getRideStatColor(int index) {
/* 326 */     switch (index) { case 0: case 1: case 2: case 3: case 4:  }  return 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 332 */       GuideColors.ACCENT_EMERALD;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int getMovementBgColor(String movement) {
/* 337 */     switch (movement.toLowerCase()) { case "land": case "water": case "fly":  }  return 
/*     */ 
/*     */ 
/*     */       
/* 341 */       GuideColors.ACCENT_DARK_GREEN;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int getMovementAccentColor(String movement) {
/* 346 */     switch (movement.toLowerCase()) { case "land": case "water": case "fly":  }  return 
/*     */ 
/*     */ 
/*     */       
/* 350 */       GuideColors.ACCENT_EMERALD;
/*     */   }
/*     */ 
/*     */   
/*     */   private void requestPage(int page) {
/* 355 */     GuideData.getInstance().setRideableLoading(true);
/* 356 */     String filter = this.currentMovementFilter.equals("All") ? "" : this.currentMovementFilter.toLowerCase();
/* 357 */     GuideNetwork.requestGuideData("rideable", page, this.currentSearch, filter);
/* 358 */     this.lastRequestTime = System.currentTimeMillis();
/*     */   }
/*     */   
/*     */   public boolean mouseClicked(double mouseX, double mouseY, int button) {
/* 362 */     if (this.searchBar.mouseClicked(mouseX, mouseY, button)) return true;
/*     */ 
/*     */     
/* 365 */     if (button == 0 && this.hoveredFilterIndex >= 0 && this.hoveredFilterIndex < MOVEMENT_FILTERS.length) {
/* 366 */       String filter = MOVEMENT_FILTERS[this.hoveredFilterIndex];
/* 367 */       if (!filter.equals(this.currentMovementFilter)) {
/* 368 */         GuideSounds.playClick();
/* 369 */         this.currentMovementFilter = filter;
/* 370 */         GuideData.getInstance().resetRideable();
/* 371 */         this.scrollArea.resetScroll();
/* 372 */         requestPage(0);
/*     */       } 
/* 374 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 378 */     if (this.scrollArea.handleMouseClicked(mouseX, mouseY, button)) return true;
/*     */ 
/*     */     
/* 381 */     if (this.hoveredCardIndex >= 0 && button == 0) {
/* 382 */       List<RideableInfo> entries = GuideData.getInstance().getAllRideableEntries();
/* 383 */       if (this.hoveredCardIndex < entries.size() && this.onPokemonSelected != null) {
/* 384 */         GuideSounds.playNavigate();
/* 385 */         this.onPokemonSelected.accept(((RideableInfo)entries.get(this.hoveredCardIndex)).slug);
/* 386 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/* 390 */     return false;
/*     */   }
/*     */   
/*     */   public boolean mouseScrolled(double mouseX, double mouseY, double h, double v) {
/* 394 */     return this.scrollArea.handleScroll(mouseX, mouseY, v);
/*     */   }
/*     */   
/*     */   public boolean mouseDragged(double mouseX, double mouseY, int button) {
/* 398 */     return this.scrollArea.handleMouseDragged(mouseX, mouseY, button);
/*     */   }
/*     */   
/*     */   public boolean mouseReleased(double mouseX, double mouseY, int button) {
/* 402 */     return this.scrollArea.handleMouseReleased(mouseX, mouseY, button);
/*     */   }
/*     */   
/*     */   public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
/* 406 */     return this.searchBar.keyPressed(keyCode, scanCode, modifiers);
/*     */   }
/*     */   
/*     */   public boolean charTyped(char chr, int modifiers) {
/* 410 */     return this.searchBar.charTyped(chr, modifiers);
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\guide\screen\views\RideableListView.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */