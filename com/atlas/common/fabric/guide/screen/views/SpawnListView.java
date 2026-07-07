/*     */ package com.atlas.common.fabric.guide.screen.views;
/*     */ 
/*     */ import com.atlas.common.fabric.guide.data.GuideData;
/*     */ import com.atlas.common.fabric.guide.data.SpawnEntry;
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
/*     */ 
/*     */ public class SpawnListView
/*     */ {
/*     */   private static final int SEARCH_BAR_HEIGHT = 16;
/*     */   private static final int FILTER_HEIGHT = 18;
/*     */   private static final int TYPE_FILTER_HEIGHT = 30;
/*     */   private static final int HEADER_HEIGHT = 76;
/*  28 */   private static final String[] RARITY_FILTERS = new String[] { "All", "Common", "Uncommon", "Rare", "Ultra-Rare" };
/*  29 */   private static final String[] TYPE_NAMES = new String[] { "normal", "fire", "water", "electric", "grass", "ice", "fighting", "poison", "ground", "flying", "psychic", "bug", "rock", "ghost", "dragon", "dark", "steel", "fairy" };
/*     */   
/*     */   private int x;
/*     */   
/*     */   private int y;
/*     */   private int width;
/*     */   private int height;
/*     */   private GuideSearchBar searchBar;
/*     */   private GuideScrollableArea scrollArea;
/*  38 */   private String currentSearch = "";
/*  39 */   private String currentRarityFilter = "All";
/*  40 */   private String currentTypeFilter = "";
/*  41 */   private String currentBiomeFilter = "";
/*     */   private boolean initialized = false;
/*  43 */   private long lastRequestTime = 0L;
/*     */   
/*     */   private Consumer<String> onPokemonSelected;
/*     */   
/*  47 */   private String pendingSearch = null;
/*  48 */   private long pendingSearchTime = 0L;
/*     */   
/*     */   private static final long SEARCH_DEBOUNCE_MS = 300L;
/*     */   
/*  52 */   private String hoveredBiome = null;
/*  53 */   private String hoveredRarity = null;
/*  54 */   private String hoveredType = null;
/*  55 */   private String hoveredPokemonSlug = null;
/*     */ 
/*     */   
/*  58 */   private final int[] rarityFilterX = new int[RARITY_FILTERS.length];
/*  59 */   private final int[] rarityFilterW = new int[RARITY_FILTERS.length];
/*     */ 
/*     */   
/*  62 */   private int hoveredTypeFilterIndex = -1;
/*  63 */   private final int[] typeFilterX = new int[TYPE_NAMES.length];
/*  64 */   private final int[] typeFilterY = new int[TYPE_NAMES.length];
/*  65 */   private final int[] typeFilterW = new int[TYPE_NAMES.length];
/*     */ 
/*     */ 
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
/*  78 */       this.searchBar.setPlaceholder("Search spawns...");
/*  79 */       this.searchBar.setOnTextChanged(text -> {
/*     */             this.pendingSearch = text; this.pendingSearchTime = System.currentTimeMillis();
/*  81 */           }); int scrollTop = y + 76;
/*  82 */       int scrollHeight = height - 76;
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
/*  94 */     if (this.scrollArea != null) this.scrollArea.updateBounds(x, y + 76, width, height - 76);
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void filterByBiome(String biome) {
/* 101 */     this.currentBiomeFilter = biome;
/* 102 */     this.currentSearch = biome;
/* 103 */     this.currentRarityFilter = "All";
/* 104 */     if (this.searchBar != null) this.searchBar.setText(biome); 
/* 105 */     this.pendingSearch = null;
/* 106 */     GuideData.getInstance().resetSpawns();
/* 107 */     if (this.scrollArea != null) this.scrollArea.resetScroll(); 
/* 108 */     requestPage(0);
/*     */   }
/*     */   
/*     */   public void filterByRarity(String rarity) {
/* 112 */     this.currentRarityFilter = rarity;
/* 113 */     GuideData.getInstance().resetSpawns();
/* 114 */     if (this.scrollArea != null) this.scrollArea.resetScroll(); 
/* 115 */     requestPage(0);
/*     */   }
/*     */   
/*     */   public void filterByType(String type) {
/* 119 */     this.currentTypeFilter = type.toLowerCase();
/* 120 */     GuideData.getInstance().resetSpawns();
/* 121 */     if (this.scrollArea != null) this.scrollArea.resetScroll(); 
/* 122 */     requestPage(0);
/*     */   }
/*     */   
/*     */   public void render(class_332 ctx, class_327 textRenderer, int mouseX, int mouseY, float delta) {
/* 126 */     if (this.pendingSearch != null && System.currentTimeMillis() - this.pendingSearchTime > 300L) {
/* 127 */       this.currentSearch = this.pendingSearch;
/* 128 */       this.pendingSearch = null;
/* 129 */       GuideData.getInstance().resetSpawns();
/* 130 */       this.scrollArea.resetScroll();
/* 131 */       requestPage(0);
/*     */     } 
/*     */     
/* 134 */     ctx.method_25294(this.x, this.y, this.x + this.width, this.y + this.height, GuideColors.SECTION_BG);
/* 135 */     this.searchBar.render(ctx, textRenderer, mouseX, mouseY);
/*     */ 
/*     */     
/* 138 */     renderRarityFilters(ctx, textRenderer, mouseX, mouseY);
/*     */ 
/*     */     
/* 141 */     renderTypeFilters(ctx, textRenderer, mouseX, mouseY);
/*     */     
/* 143 */     GuideData data = GuideData.getInstance();
/* 144 */     List<SpawnEntry> allEntries = data.getAllSpawnEntries();
/*     */ 
/*     */     
/* 147 */     List<SpawnEntry> entries = allEntries;
/* 148 */     if (!this.currentRarityFilter.equals("All")) {
/* 149 */       String rf = this.currentRarityFilter.toLowerCase().replace("-", "");
/*     */ 
/*     */ 
/*     */       
/* 153 */       entries = entries.stream().filter(e -> e.spawns.stream().anyMatch(())).toList();
/*     */     } 
/* 155 */     if (!this.currentTypeFilter.isEmpty())
/*     */     {
/*     */       
/* 158 */       entries = entries.stream().filter(e -> e.types.stream().anyMatch(())).toList();
/*     */     }
/*     */ 
/*     */     
/* 162 */     int totalHeight = 4;
/* 163 */     for (SpawnEntry entry : entries) {
/* 164 */       totalHeight += calculateEntryHeight(textRenderer, entry) + 6;
/*     */     }
/* 166 */     this.scrollArea.setContentHeight(totalHeight);
/* 167 */     this.scrollArea.beginRender(ctx);
/*     */     
/* 169 */     this.hoveredBiome = null;
/* 170 */     this.hoveredRarity = null;
/* 171 */     this.hoveredType = null;
/* 172 */     this.hoveredPokemonSlug = null;
/*     */     
/* 174 */     int cy = this.scrollArea.getY() + 2 - this.scrollArea.getScrollOffset();
/* 175 */     int cw = this.width - 12;
/* 176 */     int cx = this.x + 6;
/*     */     
/* 178 */     for (SpawnEntry entry : entries) {
/* 179 */       int entryH = calculateEntryHeight(textRenderer, entry);
/* 180 */       if (cy > this.scrollArea.getY() + this.scrollArea.getHeight() + 50)
/* 181 */         break;  if (cy + entryH < this.scrollArea.getY() - 20) {
/* 182 */         cy += entryH + 6;
/*     */         
/*     */         continue;
/*     */       } 
/* 186 */       cy = renderSpawnEntry(ctx, textRenderer, entry, cx, cy, cw, mouseX, mouseY);
/*     */ 
/*     */       
/* 189 */       ctx.method_25294(cx, cy + 1, cx + cw, cy + 2, GuideColors.BORDER_DIM);
/* 190 */       cy += 6;
/*     */     } 
/*     */     
/* 193 */     this.scrollArea.endRender(ctx);
/*     */ 
/*     */     
/* 196 */     if (data.isSpawnLoading()) {
/* 197 */       ctx.method_25300(textRenderer, "Loading...", this.x + this.width / 2, this.scrollArea.getY() + this.scrollArea.getHeight() - 14, GuideColors.TEXT_DIM);
/*     */     }
/*     */     
/* 200 */     if (entries.isEmpty() && !data.isSpawnLoading()) {
/* 201 */       ctx.method_25300(textRenderer, "No spawn data found", this.x + this.width / 2, this.scrollArea.getY() + this.scrollArea.getHeight() / 2, GuideColors.TEXT_DIM);
/*     */     }
/*     */ 
/*     */     
/* 205 */     if (!allEntries.isEmpty() && data.hasMoreSpawnPages() && !data.isSpawnLoading()) {
/* 206 */       int maxScroll = this.scrollArea.getMaxScroll();
/* 207 */       int threshold = Math.max(300, this.scrollArea.getHeight());
/* 208 */       if (maxScroll <= 0 || this.scrollArea.getScrollOffset() >= maxScroll - threshold) {
/* 209 */         long now = System.currentTimeMillis();
/* 210 */         if (now - this.lastRequestTime > 500L) {
/* 211 */           requestPage(data.getSpawnCurrentPage() + 1);
/* 212 */           this.lastRequestTime = now;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private int renderSpawnEntry(class_332 ctx, class_327 textRenderer, SpawnEntry entry, int cx, int cy, int cw, int mouseX, int mouseY) {
/* 221 */     int badgeH = 11;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 226 */     boolean nameHovered = (mouseX >= cx && mouseX < cx + textRenderer.method_1727(entry.name) && mouseY >= cy - 1 && mouseY < cy + 10 && this.scrollArea.isInBounds(mouseX, mouseY));
/* 227 */     int nameColor = nameHovered ? GuideColors.TEXT_ACCENT : GuideColors.TEXT_WHITE;
/* 228 */     if (nameHovered) this.hoveredPokemonSlug = entry.slug; 
/* 229 */     ctx.method_51433(textRenderer, entry.name, cx, cy, nameColor, true);
/*     */     
/* 231 */     int typeX = cx + textRenderer.method_1727(entry.name) + 6;
/* 232 */     for (String type : entry.types) {
/* 233 */       int tw = GuideTypeBadge.draw(ctx, textRenderer, type, typeX, cy - 1);
/* 234 */       if (mouseX >= typeX && mouseX < typeX + tw && mouseY >= cy - 1 && mouseY < cy + 9 && this.scrollArea
/* 235 */         .isInBounds(mouseX, mouseY)) {
/* 236 */         this.hoveredType = type;
/*     */       }
/* 238 */       typeX += tw + 2;
/*     */     } 
/* 240 */     cy += 14;
/*     */ 
/*     */     
/* 243 */     if (entry.spawns.isEmpty()) {
/* 244 */       ctx.method_51433(textRenderer, "  No spawn data", cx, cy, GuideColors.TEXT_DIM, true);
/* 245 */       cy += 12;
/*     */     } else {
/* 247 */       for (int si = 0; si < entry.spawns.size(); si++) {
/* 248 */         SpawnEntry.SpawnData spawn = entry.spawns.get(si);
/*     */ 
/*     */         
/* 251 */         if (!this.currentRarityFilter.equals("All")) {
/* 252 */           String rf = this.currentRarityFilter.toLowerCase().replace("-", "");
/* 253 */           if (!spawn.rarity.toLowerCase().replace("-", "").contains(rf) && 
/* 254 */             !spawn.rarity.toLowerCase().replace("_", "").contains(rf)) {
/*     */             continue;
/*     */           }
/*     */         } 
/*     */ 
/*     */         
/* 260 */         int rarityColor = GuideColors.getRarityColor(spawn.rarity);
/*     */         
/* 262 */         String rarityLabel = spawn.rarity.isEmpty() ? "?" : (spawn.rarity.substring(0, 1).toUpperCase() + spawn.rarity.substring(0, 1).toUpperCase());
/* 263 */         int rarityW = textRenderer.method_1727(rarityLabel) + 8;
/*     */ 
/*     */         
/* 266 */         int rarityBg = GuideColors.darken(rarityColor, 60);
/* 267 */         ctx.method_25294(cx, cy, cx + rarityW, cy + badgeH, rarityBg);
/* 268 */         ctx.method_25294(cx, cy, cx + rarityW, cy + 1, rarityColor);
/* 269 */         ctx.method_25294(cx, cy + badgeH - 1, cx + rarityW, cy + badgeH, rarityColor);
/*     */ 
/*     */ 
/*     */         
/* 273 */         boolean rarityHovered = (mouseX >= cx && mouseX < cx + rarityW && mouseY >= cy && mouseY < cy + badgeH && this.scrollArea.isInBounds(mouseX, mouseY));
/* 274 */         if (rarityHovered) this.hoveredRarity = spawn.rarity; 
/* 275 */         ctx.method_51433(textRenderer, rarityLabel, cx + 4, cy + 2, GuideColors.TEXT_WHITE, true);
/*     */ 
/*     */         
/* 278 */         int afterRarity = cx + rarityW + 5;
/* 279 */         if (!spawn.levelRange.isEmpty()) {
/* 280 */           ctx.method_51433(textRenderer, "Lv. " + spawn.levelRange, afterRarity, cy + 2, GuideColors.TEXT_DIM, true);
/*     */         }
/*     */ 
/*     */         
/* 284 */         if (spawn.context != null && !spawn.context.isEmpty() && !spawn.context.equalsIgnoreCase("grounded")) {
/* 285 */           String ctxLabel = spawn.context.substring(0, 1).toUpperCase() + spawn.context.substring(0, 1).toUpperCase();
/* 286 */           int ctxW = textRenderer.method_1727(ctxLabel) + 6;
/* 287 */           int ctxX = cx + cw - ctxW;
/* 288 */           ctx.method_25294(ctxX, cy, ctxX + ctxW, cy + badgeH, GuideColors.color(40, 50, 60, 200));
/* 289 */           ctx.method_49601(ctxX, cy, ctxW, badgeH, GuideColors.BORDER_DIM);
/* 290 */           ctx.method_51433(textRenderer, ctxLabel, ctxX + 3, cy + 2, GuideColors.TEXT_PRIMARY, true);
/*     */         } 
/* 292 */         cy += badgeH + 4;
/*     */ 
/*     */         
/* 295 */         if (!spawn.biomes.isEmpty()) {
/* 296 */           int tagX = cx;
/* 297 */           for (String biome : spawn.biomes) {
/* 298 */             String biomeDisplay = formatBiomeName(biome);
/* 299 */             int tagW = textRenderer.method_1727(biomeDisplay) + 8;
/*     */ 
/*     */             
/* 302 */             if (tagX + tagW > cx + cw && tagX > cx) {
/* 303 */               tagX = cx;
/* 304 */               cy += badgeH + 2;
/*     */             } 
/*     */ 
/*     */             
/* 308 */             int biomeColor = getBiomeColor(biome);
/* 309 */             int biomeBg = GuideColors.darken(biomeColor, 80);
/* 310 */             ctx.method_25294(tagX, cy, tagX + tagW, cy + badgeH, biomeBg);
/* 311 */             ctx.method_25294(tagX, cy, tagX + tagW, cy + 1, GuideColors.withAlpha(biomeColor, 180));
/* 312 */             ctx.method_25294(tagX, cy + badgeH - 1, tagX + tagW, cy + badgeH, GuideColors.withAlpha(biomeColor, 180));
/*     */ 
/*     */ 
/*     */             
/* 316 */             boolean biomeHovered = (mouseX >= tagX && mouseX < tagX + tagW && mouseY >= cy && mouseY < cy + badgeH && this.scrollArea.isInBounds(mouseX, mouseY));
/* 317 */             if (biomeHovered) this.hoveredBiome = biome; 
/* 318 */             ctx.method_51433(textRenderer, biomeDisplay, tagX + 4, cy + 2, GuideColors.TEXT_WHITE, true);
/*     */             
/* 320 */             tagX += tagW + 3;
/*     */           } 
/* 322 */           cy += badgeH + 3;
/*     */         } 
/*     */ 
/*     */         
/* 326 */         if (spawn.excludedBiomes != null && !spawn.excludedBiomes.isEmpty()) {
/* 327 */           int tagX = cx;
/* 328 */           ctx.method_51433(textRenderer, "✗", tagX, cy + 2, GuideColors.color(180, 70, 70, 255), true);
/* 329 */           tagX += 8;
/* 330 */           for (String exBiome : spawn.excludedBiomes) {
/* 331 */             String exDisplay = formatBiomeName(exBiome);
/* 332 */             int tagW = textRenderer.method_1727(exDisplay) + 8;
/*     */             
/* 334 */             if (tagX + tagW > cx + cw && tagX > cx + 8) {
/* 335 */               tagX = cx + 8;
/* 336 */               cy += badgeH + 2;
/*     */             } 
/*     */ 
/*     */             
/* 340 */             int exColor = GuideColors.color(180, 80, 80, 255);
/* 341 */             ctx.method_25294(tagX, cy, tagX + tagW, cy + badgeH, GuideColors.color(50, 25, 25, 180));
/* 342 */             ctx.method_25294(tagX, cy, tagX + tagW, cy + 1, GuideColors.withAlpha(exColor, 120));
/* 343 */             ctx.method_25294(tagX, cy + badgeH - 1, tagX + tagW, cy + badgeH, GuideColors.withAlpha(exColor, 120));
/* 344 */             ctx.method_51433(textRenderer, exDisplay, tagX + 4, cy + 2, GuideColors.color(220, 150, 150, 255), true);
/*     */             
/* 346 */             tagX += tagW + 3;
/*     */           } 
/* 348 */           cy += badgeH + 3;
/*     */         } 
/*     */ 
/*     */         
/* 352 */         if (spawn.conditions != null && !spawn.conditions.isEmpty()) {
/* 353 */           for (String condition : spawn.conditions) {
/*     */             
/* 355 */             String condDisplay = formatCondition(condition);
/* 356 */             int condW = textRenderer.method_1727(condDisplay) + 8;
/* 357 */             int condColor = GuideColors.color(160, 150, 100, 255);
/* 358 */             int condBg = GuideColors.darken(condColor, 80);
/* 359 */             ctx.method_25294(cx, cy, cx + condW, cy + badgeH, condBg);
/* 360 */             ctx.method_25294(cx, cy, cx + condW, cy + 1, GuideColors.withAlpha(condColor, 150));
/* 361 */             ctx.method_25294(cx, cy + badgeH - 1, cx + condW, cy + badgeH, GuideColors.withAlpha(condColor, 150));
/* 362 */             ctx.method_51433(textRenderer, condDisplay, cx + 4, cy + 2, GuideColors.TEXT_WHITE, true);
/* 363 */             cy += badgeH + 2;
/*     */           } 
/*     */         }
/*     */ 
/*     */         
/* 368 */         if (si < entry.spawns.size() - 1) {
/* 369 */           cy++;
/* 370 */           ctx.method_25294(cx + 4, cy, cx + cw - 4, cy + 1, GuideColors.color(50, 60, 65, 150));
/* 371 */           cy += 4;
/*     */         } 
/*     */         continue;
/*     */       } 
/*     */     } 
/* 376 */     cy += 3;
/* 377 */     return cy;
/*     */   }
/*     */ 
/*     */   
/*     */   private int calculateEntryHeight(class_327 textRenderer, SpawnEntry entry) {
/* 382 */     int h = 14;
/* 383 */     if (entry.spawns.isEmpty()) {
/* 384 */       h += 12;
/*     */     } else {
/* 386 */       for (SpawnEntry.SpawnData spawn : entry.spawns) {
/* 387 */         if (!this.currentRarityFilter.equals("All")) {
/* 388 */           String rf = this.currentRarityFilter.toLowerCase().replace("-", "");
/* 389 */           if (!spawn.rarity.toLowerCase().replace("-", "").contains(rf) && 
/* 390 */             !spawn.rarity.toLowerCase().replace("_", "").contains(rf)) {
/*     */             continue;
/*     */           }
/*     */         } 
/* 394 */         h += 15;
/* 395 */         if (!spawn.biomes.isEmpty()) h += 14; 
/* 396 */         if (spawn.excludedBiomes != null && !spawn.excludedBiomes.isEmpty()) h += 14; 
/* 397 */         if (spawn.conditions != null && !spawn.conditions.isEmpty()) h += spawn.conditions.size() * 13; 
/* 398 */         h += 5;
/*     */       } 
/*     */     } 
/* 401 */     return h + 6;
/*     */   }
/*     */   
/*     */   private void renderRarityFilters(class_332 ctx, class_327 textRenderer, int mouseX, int mouseY) {
/* 405 */     int filterY = this.y + 16 + 6;
/* 406 */     int fx = this.x + 4;
/*     */     
/* 408 */     for (int i = 0; i < RARITY_FILTERS.length; i++) {
/* 409 */       String rarity = RARITY_FILTERS[i];
/* 410 */       int tw = textRenderer.method_1727(rarity) + 8;
/* 411 */       boolean active = rarity.equals(this.currentRarityFilter);
/* 412 */       boolean hovered = (mouseX >= fx && mouseX < fx + tw && mouseY >= filterY && mouseY < filterY + 12);
/*     */ 
/*     */       
/* 415 */       this.rarityFilterX[i] = fx;
/* 416 */       this.rarityFilterW[i] = tw;
/*     */       
/* 418 */       int rarityColor = rarity.equalsIgnoreCase("All") ? GuideColors.ACCENT_TEAL : GuideColors.getRarityColor(rarity.toLowerCase());
/* 419 */       int bg = active ? GuideColors.darken(rarityColor, 60) : (hovered ? GuideColors.CARD_HOVER_BG : GuideColors.CARD_BG);
/* 420 */       ctx.method_25294(fx, filterY, fx + tw, filterY + 12, bg);
/* 421 */       if (active) {
/*     */         
/* 423 */         ctx.method_25294(fx, filterY, fx + tw, filterY + 1, rarityColor);
/* 424 */         ctx.method_25294(fx, filterY + 11, fx + tw, filterY + 12, rarityColor);
/*     */       } else {
/* 426 */         ctx.method_49601(fx, filterY, tw, 12, GuideColors.BORDER_DIM);
/*     */       } 
/*     */       
/* 429 */       int textColor = active ? GuideColors.TEXT_WHITE : (hovered ? GuideColors.TEXT_PRIMARY : GuideColors.TEXT_DIM);
/* 430 */       ctx.method_25300(textRenderer, rarity, fx + tw / 2, filterY + 2, textColor);
/*     */       
/* 432 */       fx += tw + 3;
/*     */     } 
/*     */ 
/*     */     
/* 436 */     if (!this.currentTypeFilter.isEmpty()) {
/* 437 */       fx += 6;
/* 438 */       ctx.method_51433(textRenderer, "Type:", fx, filterY + 2, GuideColors.TEXT_DIM, true);
/* 439 */       fx += textRenderer.method_1727("Type:") + 2;
/* 440 */       GuideTypeBadge.draw(ctx, textRenderer, this.currentTypeFilter, fx, filterY + 1);
/* 441 */       fx += textRenderer.method_1727(this.currentTypeFilter) + 10;
/* 442 */       boolean clearHovered = (mouseX >= fx && mouseX < fx + 8 && mouseY >= filterY && mouseY < filterY + 12);
/* 443 */       ctx.method_51433(textRenderer, "×", fx, filterY + 2, clearHovered ? GuideColors.TEXT_WHITE : GuideColors.TEXT_DIM, true);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void renderTypeFilters(class_332 ctx, class_327 textRenderer, int mouseX, int mouseY) {
/* 448 */     int filterStartY = this.y + 16 + 18 + 6;
/* 449 */     int fx = this.x + 4;
/* 450 */     int fy = filterStartY;
/* 451 */     int maxX = this.x + this.width - 4;
/* 452 */     this.hoveredTypeFilterIndex = -1;
/*     */     
/* 454 */     for (int i = 0; i < TYPE_NAMES.length; i++) {
/* 455 */       String type = TYPE_NAMES[i];
/* 456 */       boolean selected = type.equalsIgnoreCase(this.currentTypeFilter);
/*     */       
/* 458 */       int badgeW = GuideTypeBadge.getWidth(textRenderer, type) + 4;
/*     */ 
/*     */       
/* 461 */       if (fx + badgeW > maxX && fx > this.x + 4) {
/* 462 */         fx = this.x + 4;
/* 463 */         fy += 14;
/*     */       } 
/*     */       
/* 466 */       boolean hovered = (mouseX >= fx && mouseX < fx + badgeW && mouseY >= fy && mouseY < fy + 12);
/*     */       
/* 468 */       if (hovered) this.hoveredTypeFilterIndex = i;
/*     */ 
/*     */       
/* 471 */       this.typeFilterX[i] = fx;
/* 472 */       this.typeFilterY[i] = fy;
/* 473 */       this.typeFilterW[i] = badgeW;
/*     */       
/* 475 */       GuideTypeBadge.drawFilter(ctx, textRenderer, type, fx, fy, selected, hovered);
/* 476 */       fx += badgeW + 2;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static String formatBiomeName(String biome) {
/* 482 */     if (biome == null || biome.isEmpty()) return biome; 
/* 483 */     if (biome.startsWith("#")) biome = biome.substring(1); 
/* 484 */     if (biome.startsWith("is_")) biome = biome.substring(3); 
/* 485 */     if (biome.contains(":")) biome = biome.substring(biome.indexOf(':') + 1); 
/* 486 */     String[] words = biome.replace('_', ' ').replace('-', ' ').split(" ");
/* 487 */     StringBuilder sb = new StringBuilder();
/* 488 */     for (String word : words) {
/* 489 */       if (!word.isEmpty()) {
/* 490 */         if (sb.length() > 0) sb.append(' '); 
/* 491 */         sb.append(word.substring(0, 1).toUpperCase()).append(word.substring(1));
/*     */       } 
/*     */     } 
/* 494 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   private static String formatCondition(String condition) {
/* 499 */     if (condition == null || condition.isEmpty()) return condition;
/*     */     
/* 501 */     String display = condition.replace("_", " ");
/*     */     
/* 503 */     if (display.contains("=")) {
/* 504 */       String[] parts = display.split("=", 2);
/* 505 */       String key = parts[0].trim();
/* 506 */       String val = parts[1].trim();
/*     */       
/* 508 */       key = key.substring(0, 1).toUpperCase() + key.substring(0, 1).toUpperCase();
/* 509 */       val = val.substring(0, 1).toUpperCase() + val.substring(0, 1).toUpperCase();
/* 510 */       return key + ": " + key;
/*     */     } 
/* 512 */     return display.substring(0, 1).toUpperCase() + display.substring(0, 1).toUpperCase();
/*     */   }
/*     */ 
/*     */   
/*     */   private static int getBiomeColor(String biome) {
/* 517 */     String lower = biome.toLowerCase();
/* 518 */     if (lower.contains("ocean") || lower.contains("river") || lower.contains("beach") || lower.contains("lake") || lower.contains("deep"))
/* 519 */       return GuideColors.color(80, 140, 210, 255); 
/* 520 */     if (lower.contains("forest") || lower.contains("jungle") || lower.contains("grove") || lower.contains("woodland"))
/* 521 */       return GuideColors.color(70, 170, 90, 255); 
/* 522 */     if (lower.contains("desert") || lower.contains("badlands") || lower.contains("mesa") || lower.contains("savanna"))
/* 523 */       return GuideColors.color(210, 170, 70, 255); 
/* 524 */     if (lower.contains("snow") || lower.contains("frozen") || lower.contains("ice") || lower.contains("glacier") || lower.contains("cold"))
/* 525 */       return GuideColors.color(150, 200, 220, 255); 
/* 526 */     if (lower.contains("mountain") || lower.contains("peak") || lower.contains("hill") || lower.contains("stony") || lower.contains("windswept"))
/* 527 */       return GuideColors.color(150, 150, 165, 255); 
/* 528 */     if (lower.contains("swamp") || lower.contains("marsh") || lower.contains("bog") || lower.contains("mangrove"))
/* 529 */       return GuideColors.color(100, 150, 80, 255); 
/* 530 */     if (lower.contains("cave") || lower.contains("deep_dark") || lower.contains("lush"))
/* 531 */       return GuideColors.color(120, 100, 150, 255); 
/* 532 */     if (lower.contains("nether") || lower.contains("crimson") || lower.contains("warped") || lower.contains("basalt") || lower.contains("soul"))
/* 533 */       return GuideColors.color(190, 70, 70, 255); 
/* 534 */     if (lower.contains("mushroom"))
/* 535 */       return GuideColors.color(180, 90, 170, 255); 
/* 536 */     if (lower.contains("plain") || lower.contains("meadow") || lower.contains("field") || lower.contains("flower"))
/* 537 */       return GuideColors.color(140, 200, 80, 255); 
/* 538 */     if (lower.contains("taiga") || lower.contains("spruce"))
/* 539 */       return GuideColors.color(90, 130, 90, 255); 
/* 540 */     if (lower.contains("cherry"))
/* 541 */       return GuideColors.color(220, 140, 170, 255); 
/* 542 */     if (lower.contains("birch"))
/* 543 */       return GuideColors.color(160, 190, 100, 255); 
/* 544 */     return GuideColors.ACCENT_TEAL;
/*     */   }
/*     */   
/*     */   private void requestPage(int page) {
/* 548 */     GuideData.getInstance().setSpawnLoading(true);
/* 549 */     String filter = this.currentTypeFilter.isEmpty() ? "" : this.currentTypeFilter;
/* 550 */     GuideNetwork.requestGuideData("spawns", page, this.currentSearch, filter);
/* 551 */     this.lastRequestTime = System.currentTimeMillis();
/*     */   }
/*     */   
/*     */   public boolean mouseClicked(double mouseX, double mouseY, int button) {
/* 555 */     if (this.searchBar.mouseClicked(mouseX, mouseY, button)) return true;
/*     */ 
/*     */     
/* 558 */     int filterY = this.y + 16 + 6;
/* 559 */     if (mouseY >= filterY && mouseY < (filterY + 12) && button == 0) {
/* 560 */       for (int i = 0; i < RARITY_FILTERS.length; i++) {
/* 561 */         if (mouseX >= this.rarityFilterX[i] && mouseX < (this.rarityFilterX[i] + this.rarityFilterW[i])) {
/* 562 */           GuideSounds.playClick();
/* 563 */           this.currentRarityFilter = RARITY_FILTERS[i];
/* 564 */           GuideData.getInstance().resetSpawns();
/* 565 */           this.scrollArea.resetScroll();
/* 566 */           requestPage(0);
/* 567 */           return true;
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 572 */       if (!this.currentTypeFilter.isEmpty()) {
/* 573 */         this.currentTypeFilter = "";
/* 574 */         GuideData.getInstance().resetSpawns();
/* 575 */         this.scrollArea.resetScroll();
/* 576 */         requestPage(0);
/* 577 */         return true;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 582 */     if (button == 0 && this.hoveredTypeFilterIndex >= 0 && this.hoveredTypeFilterIndex < TYPE_NAMES.length) {
/* 583 */       GuideSounds.playClick();
/* 584 */       String type = TYPE_NAMES[this.hoveredTypeFilterIndex];
/* 585 */       if (type.equalsIgnoreCase(this.currentTypeFilter)) {
/* 586 */         this.currentTypeFilter = "";
/*     */       } else {
/* 588 */         this.currentTypeFilter = type;
/*     */       } 
/* 590 */       GuideData.getInstance().resetSpawns();
/* 591 */       this.scrollArea.resetScroll();
/* 592 */       requestPage(0);
/* 593 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 597 */     if (button == 0) {
/*     */       
/* 599 */       if (this.hoveredBiome != null) {
/* 600 */         GuideSounds.playClick();
/* 601 */         filterByBiome(formatBiomeName(this.hoveredBiome));
/* 602 */         return true;
/*     */       } 
/*     */       
/* 605 */       if (this.hoveredRarity != null) {
/* 606 */         GuideSounds.playClick();
/* 607 */         String r = this.hoveredRarity.substring(0, 1).toUpperCase() + this.hoveredRarity.substring(0, 1).toUpperCase();
/* 608 */         this.currentRarityFilter = r;
/*     */         
/* 610 */         return true;
/*     */       } 
/*     */       
/* 613 */       if (this.hoveredType != null) {
/* 614 */         GuideSounds.playClick();
/* 615 */         this.currentTypeFilter = this.hoveredType.toLowerCase();
/* 616 */         GuideData.getInstance().resetSpawns();
/* 617 */         this.scrollArea.resetScroll();
/* 618 */         requestPage(0);
/* 619 */         return true;
/*     */       } 
/*     */       
/* 622 */       if (this.hoveredPokemonSlug != null && this.onPokemonSelected != null) {
/* 623 */         GuideSounds.playNavigate();
/* 624 */         this.onPokemonSelected.accept(this.hoveredPokemonSlug);
/* 625 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/* 629 */     return this.scrollArea.handleMouseClicked(mouseX, mouseY, button);
/*     */   }
/*     */   
/*     */   public boolean mouseScrolled(double mouseX, double mouseY, double h, double v) {
/* 633 */     return this.scrollArea.handleScroll(mouseX, mouseY, v);
/*     */   }
/*     */   
/*     */   public boolean mouseDragged(double mouseX, double mouseY, int button) {
/* 637 */     return this.scrollArea.handleMouseDragged(mouseX, mouseY, button);
/*     */   }
/*     */   
/*     */   public boolean mouseReleased(double mouseX, double mouseY, int button) {
/* 641 */     return this.scrollArea.handleMouseReleased(mouseX, mouseY, button);
/*     */   }
/*     */   
/*     */   public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
/* 645 */     return this.searchBar.keyPressed(keyCode, scanCode, modifiers);
/*     */   }
/*     */   
/*     */   public boolean charTyped(char chr, int modifiers) {
/* 649 */     return this.searchBar.charTyped(chr, modifiers);
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\guide\screen\views\SpawnListView.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */