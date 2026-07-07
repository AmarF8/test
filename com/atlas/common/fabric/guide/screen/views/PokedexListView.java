/*     */ package com.atlas.common.fabric.guide.screen.views;
/*     */ 
/*     */ import com.atlas.common.fabric.guide.data.GuideData;
/*     */ import com.atlas.common.fabric.guide.network.GuideNetwork;
/*     */ import com.atlas.common.fabric.guide.screen.GuideColors;
/*     */ import com.atlas.common.fabric.guide.screen.GuideSounds;
/*     */ import com.atlas.common.fabric.guide.screen.widgets.GuideScrollableArea;
/*     */ import com.atlas.common.fabric.guide.screen.widgets.GuideSearchBar;
/*     */ import com.atlas.common.fabric.guide.screen.widgets.GuideTypeBadge;
/*     */ import com.atlas.common.fabric.screen.PokemonRenderHelper;
/*     */ import com.cobblemon.mod.common.api.pokemon.PokemonSpecies;
/*     */ import com.cobblemon.mod.common.client.render.models.blockbench.FloatingState;
/*     */ import com.cobblemon.mod.common.pokemon.Species;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedHashSet;
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
/*     */ public class PokedexListView
/*     */ {
/*     */   private static final int SEARCH_BAR_HEIGHT = 16;
/*     */   private static final int TYPE_FILTER_HEIGHT = 30;
/*     */   private static final int CARD_WIDTH = 152;
/*     */   private static final int CARD_HEIGHT = 28;
/*     */   private static final int CARD_GAP = 3;
/*     */   private static final int COLUMNS = 3;
/*     */   private static final int HEADER_HEIGHT = 58;
/*     */   private static final int MODEL_SIZE = 24;
/*     */   private static final int MODEL_PAD = 2;
/*     */   private static final int TEXT_OFFSET = 30;
/*     */   private static final int MAX_VISIBLE_MODELS = 36;
/*  45 */   private static final String[] ALL_TYPES = new String[] { "normal", "fire", "water", "electric", "grass", "ice", "fighting", "poison", "ground", "flying", "psychic", "bug", "rock", "ghost", "dragon", "dark", "steel", "fairy" };
/*     */   
/*     */   private int x;
/*     */   
/*     */   private int y;
/*     */   
/*     */   private int width;
/*     */   private int height;
/*     */   private GuideSearchBar searchBar;
/*     */   private GuideScrollableArea scrollArea;
/*  55 */   private final Set<String> selectedTypes = new LinkedHashSet<>();
/*  56 */   private String currentSearch = "";
/*     */   private Consumer<String> onPokemonSelected;
/*  58 */   private long lastRequestTime = 0L;
/*     */   
/*     */   private boolean initialized = false;
/*     */   
/*  62 */   private String pendingSearch = null;
/*  63 */   private long pendingSearchTime = 0L;
/*     */   
/*     */   private static final long SEARCH_DEBOUNCE_MS = 300L;
/*     */   
/*  67 */   private int hoveredTypeIndex = -1;
/*  68 */   private final int[] typeFilterX = new int[ALL_TYPES.length];
/*  69 */   private final int[] typeFilterY = new int[ALL_TYPES.length];
/*  70 */   private final int[] typeFilterW = new int[ALL_TYPES.length];
/*  71 */   private int typeFilterCount = 0;
/*     */ 
/*     */   
/*  74 */   private int hoveredCardIndex = -1;
/*     */ 
/*     */   
/*  77 */   private String hoveredCardType = null;
/*     */ 
/*     */   
/*  80 */   private final Map<Integer, FloatingState> pokemonStates = new HashMap<>();
/*  81 */   private final Map<String, class_2960> resolvedSpeciesIds = new HashMap<>(); private static Map<String, class_2960> NORMALIZED_SPECIES_MAP;
/*     */   
/*     */   public PokedexListView() {
/*  84 */     PokemonRenderHelper.init();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void filterByType(String type) {
/*  92 */     this.selectedTypes.clear();
/*  93 */     this.selectedTypes.add(type.toLowerCase());
/*  94 */     this.currentSearch = "";
/*  95 */     if (this.searchBar != null) this.searchBar.setText(""); 
/*  96 */     applyFilters();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void init(int x, int y, int width, int height, Consumer<String> onPokemonSelected) {
/* 102 */     this.x = x;
/* 103 */     this.y = y;
/* 104 */     this.width = width;
/* 105 */     this.height = height;
/* 106 */     this.onPokemonSelected = onPokemonSelected;
/*     */     
/* 108 */     if (!this.initialized) {
/*     */       
/* 110 */       this.searchBar = new GuideSearchBar(x + 4, y + 4, width - 8, 16);
/* 111 */       this.searchBar.setPlaceholder("Search Pokémon...");
/* 112 */       this.searchBar.setOnTextChanged(this::onSearchChanged);
/*     */ 
/*     */       
/* 115 */       int scrollTop = y + 58;
/* 116 */       int scrollHeight = height - 58;
/* 117 */       this.scrollArea = new GuideScrollableArea(x, scrollTop, width, scrollHeight);
/*     */       
/* 119 */       this.initialized = true;
/*     */ 
/*     */       
/* 122 */       requestPage(0);
/*     */     } else {
/* 124 */       updateBounds(x, y, width, height);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateBounds(int x, int y, int width, int height) {
/* 132 */     this.x = x;
/* 133 */     this.y = y;
/* 134 */     this.width = width;
/* 135 */     this.height = height;
/*     */     
/* 137 */     if (this.searchBar != null) {
/* 138 */       this.searchBar.updateBounds(x + 4, y + 4, width - 8, 16);
/*     */     }
/* 140 */     if (this.scrollArea != null) {
/* 141 */       int scrollTop = y + 58;
/* 142 */       int scrollHeight = height - 58;
/* 143 */       this.scrollArea.updateBounds(x, scrollTop, width, scrollHeight);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(class_332 ctx, class_327 textRenderer, int mouseX, int mouseY, float delta) {
/* 151 */     if (this.pendingSearch != null && System.currentTimeMillis() - this.pendingSearchTime > 300L) {
/* 152 */       applySearch(this.pendingSearch);
/* 153 */       this.pendingSearch = null;
/*     */     } 
/*     */ 
/*     */     
/* 157 */     ctx.method_25294(this.x, this.y, this.x + this.width, this.y + this.height, GuideColors.SECTION_BG);
/*     */ 
/*     */     
/* 160 */     this.searchBar.render(ctx, textRenderer, mouseX, mouseY);
/*     */ 
/*     */     
/* 163 */     renderTypeFilters(ctx, textRenderer, mouseX, mouseY);
/*     */ 
/*     */     
/* 166 */     renderCardGrid(ctx, textRenderer, mouseX, mouseY, delta);
/*     */   }
/*     */   
/*     */   private void renderTypeFilters(class_332 ctx, class_327 textRenderer, int mouseX, int mouseY) {
/* 170 */     int filterStartY = this.y + 16 + 8;
/* 171 */     int filterX = this.x + 4;
/* 172 */     int filterY = filterStartY;
/* 173 */     int maxX = this.x + this.width - 4;
/* 174 */     this.hoveredTypeIndex = -1;
/* 175 */     this.typeFilterCount = 0;
/*     */     
/* 177 */     for (int i = 0; i < ALL_TYPES.length; i++) {
/* 178 */       String type = ALL_TYPES[i];
/* 179 */       boolean selected = this.selectedTypes.contains(type);
/*     */ 
/*     */       
/* 182 */       int badgeW = GuideTypeBadge.getWidth(textRenderer, type) + 4;
/*     */ 
/*     */       
/* 185 */       if (filterX + badgeW > maxX && filterX > this.x + 4) {
/* 186 */         filterX = this.x + 4;
/* 187 */         filterY += 14;
/*     */       } 
/*     */       
/* 190 */       boolean hovered = (mouseX >= filterX && mouseX < filterX + badgeW && mouseY >= filterY && mouseY < filterY + 12);
/*     */       
/* 192 */       if (hovered) this.hoveredTypeIndex = i;
/*     */ 
/*     */       
/* 195 */       this.typeFilterX[i] = filterX;
/* 196 */       this.typeFilterY[i] = filterY;
/* 197 */       this.typeFilterW[i] = badgeW;
/* 198 */       this.typeFilterCount = i + 1;
/*     */       
/* 200 */       GuideTypeBadge.drawFilter(ctx, textRenderer, type, filterX, filterY, selected, hovered);
/* 201 */       filterX += badgeW + 2;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void renderCardGrid(class_332 ctx, class_327 textRenderer, int mouseX, int mouseY, float delta) {
/* 206 */     GuideData guideData = GuideData.getInstance();
/* 207 */     List<GuideNetwork.PokedexEntry> entries = guideData.getAllPokedexEntries();
/*     */ 
/*     */     
/* 210 */     int rows = (int)Math.ceil(entries.size() / 3.0D);
/* 211 */     int contentHeight = rows * 31 + 4;
/* 212 */     this.scrollArea.setContentHeight(contentHeight);
/*     */     
/* 214 */     this.scrollArea.beginRender(ctx);
/*     */     
/* 216 */     int scrollOffset = this.scrollArea.getScrollOffset();
/* 217 */     int startY = this.scrollArea.getY() + 2 - scrollOffset;
/* 218 */     this.hoveredCardIndex = -1;
/* 219 */     this.hoveredCardType = null;
/*     */     
/* 221 */     for (int i = 0; i < entries.size(); i++) {
/* 222 */       int col = i % 3;
/* 223 */       int row = i / 3;
/*     */       
/* 225 */       int cardX = this.x + 4 + col * 155;
/* 226 */       int cardY = startY + row * 31;
/*     */ 
/*     */       
/* 229 */       if (cardY + 28 >= this.scrollArea.getY() && cardY <= this.scrollArea.getY() + this.scrollArea.getHeight()) {
/*     */ 
/*     */ 
/*     */         
/* 233 */         GuideNetwork.PokedexEntry entry = entries.get(i);
/*     */ 
/*     */         
/* 236 */         boolean hovered = (mouseX >= cardX && mouseX < cardX + 152 && mouseY >= cardY && mouseY < cardY + 28 && this.scrollArea.isInBounds(mouseX, mouseY));
/* 237 */         if (hovered) {
/* 238 */           this.hoveredCardIndex = i;
/* 239 */           GuideSounds.playHover("pokedex:" + i);
/*     */         } 
/*     */         
/* 242 */         renderPokemonCard(ctx, textRenderer, entry, cardX, cardY, hovered, mouseX, mouseY, i, delta);
/*     */       } 
/*     */     } 
/* 245 */     this.scrollArea.endRender(ctx);
/*     */ 
/*     */     
/* 248 */     if (guideData.isPokedexLoading()) {
/* 249 */       ctx.method_25300(textRenderer, "Loading...", this.x + this.width / 2, this.scrollArea
/* 250 */           .getY() + this.scrollArea.getHeight() - 14, GuideColors.TEXT_DIM);
/*     */     }
/*     */ 
/*     */     
/* 254 */     if (entries.isEmpty() && !guideData.isPokedexLoading()) {
/* 255 */       ctx.method_25300(textRenderer, "No Pokémon found", this.x + this.width / 2, this.scrollArea
/* 256 */           .getY() + this.scrollArea.getHeight() / 2, GuideColors.TEXT_DIM);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 262 */     if (!entries.isEmpty() && guideData.hasMorePokedexPages() && !guideData.isPokedexLoading()) {
/* 263 */       int maxScroll = this.scrollArea.getMaxScroll();
/* 264 */       int threshold = Math.max(300, this.scrollArea.getHeight());
/* 265 */       if (maxScroll <= 0 || scrollOffset >= maxScroll - threshold) {
/* 266 */         long now = System.currentTimeMillis();
/* 267 */         if (now - this.lastRequestTime > 500L) {
/* 268 */           requestNextPage();
/* 269 */           this.lastRequestTime = now;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderPokemonCard(class_332 ctx, class_327 textRenderer, GuideNetwork.PokedexEntry entry, int cardX, int cardY, boolean hovered, int mouseX, int mouseY, int index, float delta) {
/* 279 */     int bgColor = hovered ? GuideColors.CARD_HOVER_BG : GuideColors.CARD_BG;
/* 280 */     ctx.method_25294(cardX, cardY, cardX + 152, cardY + 28, bgColor);
/*     */ 
/*     */     
/* 283 */     if (hovered) {
/* 284 */       ctx.method_49601(cardX, cardY, 152, 28, GuideColors.BORDER_HIGHLIGHT);
/*     */     }
/*     */ 
/*     */     
/* 288 */     int modelX = cardX + 2;
/* 289 */     int modelY = cardY + 2;
/* 290 */     renderPokemonInCell(ctx, entry.slug(), modelX, modelY, 24, index, delta);
/*     */ 
/*     */     
/* 293 */     int textX = cardX + 30;
/* 294 */     int textAvailW = 118;
/*     */ 
/*     */     
/* 297 */     String dexNum = String.format("#%03d ", new Object[] { Integer.valueOf(entry.id()) });
/* 298 */     int dexWidth = textRenderer.method_1727(dexNum);
/* 299 */     ctx.method_51433(textRenderer, dexNum, textX, cardY + 3, GuideColors.TEXT_DIM, true);
/*     */     
/* 301 */     String name = entry.name();
/* 302 */     int maxNameWidth = textAvailW - dexWidth - 10;
/* 303 */     if (textRenderer.method_1727(name) > maxNameWidth) {
/* 304 */       while (textRenderer.method_1727(name + "..") > maxNameWidth && name.length() > 1) {
/* 305 */         name = name.substring(0, name.length() - 1);
/*     */       }
/* 307 */       name = name + "..";
/*     */     } 
/* 309 */     ctx.method_51433(textRenderer, name, textX + dexWidth, cardY + 3, GuideColors.TEXT_PRIMARY, true);
/*     */ 
/*     */     
/* 312 */     String[] types = entry.types().split(",");
/* 313 */     int typeX = textX;
/* 314 */     int typeY = cardY + 15;
/* 315 */     for (String type : types) {
/* 316 */       String trimType = type.trim();
/* 317 */       if (!trimType.isEmpty()) {
/* 318 */         int badgeWidth = GuideTypeBadge.draw(ctx, textRenderer, trimType, typeX, typeY);
/*     */ 
/*     */         
/* 321 */         if (hovered && mouseX >= typeX && mouseX < typeX + badgeWidth && mouseY >= typeY && mouseY < typeY + 11)
/*     */         {
/* 323 */           this.hoveredCardType = trimType.toLowerCase();
/*     */         }
/*     */         
/* 326 */         typeX += badgeWidth + 2;
/*     */       } 
/*     */     } 
/*     */     
/* 330 */     if (!entry.implemented()) {
/* 331 */       ctx.method_51433(textRenderer, "✗", cardX + 152 - 10, cardY + 3, GuideColors.TEXT_MUTED, false);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static synchronized Map<String, class_2960> getNormalizedSpeciesMap() {
/* 343 */     if (NORMALIZED_SPECIES_MAP != null) return NORMALIZED_SPECIES_MAP; 
/* 344 */     NORMALIZED_SPECIES_MAP = new HashMap<>();
/*     */     try {
/* 346 */       for (Species species : PokemonSpecies.getSpecies()) {
/* 347 */         class_2960 resId = species.getResourceIdentifier();
/* 348 */         String path = resId.method_12832();
/* 349 */         String namespace = resId.method_12836();
/* 350 */         class_2960 fullId = class_2960.method_60655(namespace, path);
/*     */         
/* 352 */         NORMALIZED_SPECIES_MAP.put(path, fullId);
/*     */         
/* 354 */         String normalized = path.replaceAll("[^a-z0-9]", "");
/* 355 */         NORMALIZED_SPECIES_MAP.putIfAbsent(normalized, fullId);
/*     */       } 
/* 357 */     } catch (Exception exception) {}
/*     */ 
/*     */     
/* 360 */     return NORMALIZED_SPECIES_MAP;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private class_2960 resolveSpeciesId(String slug) {
/* 369 */     if (this.resolvedSpeciesIds.containsKey(slug)) return this.resolvedSpeciesIds.get(slug);
/*     */     
/* 371 */     Map<String, class_2960> speciesMap = getNormalizedSpeciesMap();
/*     */ 
/*     */     
/* 374 */     class_2960 id = speciesMap.get(slug);
/*     */ 
/*     */     
/* 377 */     if (id == null) {
/* 378 */       String normalized = slug.replaceAll("[^a-z0-9]", "");
/* 379 */       id = speciesMap.get(normalized);
/*     */     } 
/*     */ 
/*     */     
/* 383 */     if (id == null) {
/* 384 */       id = class_2960.method_60655("cobblemon", slug);
/*     */     }
/*     */     
/* 387 */     this.resolvedSpeciesIds.put(slug, id);
/* 388 */     return id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderPokemonInCell(class_332 ctx, String speciesSlug, int sx, int sy, int ss, int stateIndex, float delta) {
/* 396 */     if (speciesSlug == null || speciesSlug.isEmpty())
/*     */       return; 
/* 398 */     if (!PokemonRenderHelper.isAvailable()) {
/* 399 */       renderModelPlaceholder(ctx, speciesSlug, sx, sy, ss);
/*     */       
/*     */       return;
/*     */     } 
/*     */     try {
/* 404 */       class_2960 speciesId = resolveSpeciesId(speciesSlug);
/*     */ 
/*     */       
/* 407 */       FloatingState state = this.pokemonStates.computeIfAbsent(Integer.valueOf(stateIndex), k -> new FloatingState());
/* 408 */       state.setCurrentAspects(Set.of());
/*     */       
/* 410 */       class_4587 mat = ctx.method_51448();
/* 411 */       mat.method_22903();
/* 412 */       mat.method_22904(sx + ss / 2.0D, sy + 2.0D, 0.0D);
/* 413 */       float scale = ss / 9.0F;
/* 414 */       mat.method_22905(scale, scale, 1.0F);
/*     */       
/* 416 */       Quaternionf rot = new Quaternionf();
/* 417 */       rot.rotationXYZ((float)Math.toRadians(13.0D), (float)Math.toRadians(35.0D), 0.0F);
/*     */       
/* 419 */       PokemonRenderHelper.draw(speciesId, mat, rot, state, delta);
/* 420 */       mat.method_22909();
/* 421 */     } catch (Exception e) {
/*     */       
/* 423 */       renderModelPlaceholder(ctx, speciesSlug, sx, sy, ss);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderModelPlaceholder(class_332 ctx, String slug, int sx, int sy, int ss) {
/* 430 */     ctx.method_25294(sx, sy, sx + ss, sy + ss, GuideColors.color(20, 25, 30, 200));
/*     */     
/* 432 */     int cx = sx + ss / 2;
/* 433 */     int cy = sy + ss / 2;
/* 434 */     int r = ss / 2 - 2;
/*     */     
/* 436 */     ctx.method_25294(sx + 2, sy + 2, sx + ss - 2, cy, GuideColors.color(200, 60, 60, 150));
/*     */     
/* 438 */     ctx.method_25294(sx + 2, cy, sx + ss - 2, sy + ss - 2, GuideColors.color(180, 180, 180, 100));
/*     */     
/* 440 */     ctx.method_25294(sx + 2, cy - 1, sx + ss - 2, cy + 1, GuideColors.color(40, 40, 40, 200));
/*     */     
/* 442 */     ctx.method_25294(cx - 2, cy - 2, cx + 2, cy + 2, GuideColors.color(60, 60, 60, 220));
/* 443 */     ctx.method_25294(cx - 1, cy - 1, cx + 1, cy + 1, GuideColors.color(200, 200, 200, 220));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean mouseClicked(double mouseX, double mouseY, int button) {
/* 450 */     if (this.searchBar.mouseClicked(mouseX, mouseY, button)) return true;
/*     */ 
/*     */     
/* 453 */     if (this.scrollArea.handleMouseClicked(mouseX, mouseY, button)) return true;
/*     */ 
/*     */     
/* 456 */     if (button == 0 && this.hoveredTypeIndex >= 0 && this.hoveredTypeIndex < ALL_TYPES.length) {
/* 457 */       GuideSounds.playClick();
/* 458 */       String type = ALL_TYPES[this.hoveredTypeIndex];
/* 459 */       if (this.selectedTypes.contains(type)) {
/* 460 */         this.selectedTypes.remove(type);
/*     */       } else {
/* 462 */         this.selectedTypes.add(type);
/*     */       } 
/* 464 */       applyFilters();
/* 465 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 469 */     if (button == 0 && this.hoveredCardIndex >= 0) {
/* 470 */       List<GuideNetwork.PokedexEntry> entries = GuideData.getInstance().getAllPokedexEntries();
/* 471 */       if (this.hoveredCardIndex < entries.size() && this.onPokemonSelected != null) {
/* 472 */         this.onPokemonSelected.accept(((GuideNetwork.PokedexEntry)entries.get(this.hoveredCardIndex)).slug());
/* 473 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/* 477 */     return false;
/*     */   }
/*     */   
/*     */   public boolean mouseScrolled(double mouseX, double mouseY, double horizontal, double vertical) {
/* 481 */     return this.scrollArea.handleScroll(mouseX, mouseY, vertical);
/*     */   }
/*     */   
/*     */   public boolean mouseDragged(double mouseX, double mouseY, int button) {
/* 485 */     return this.scrollArea.handleMouseDragged(mouseX, mouseY, button);
/*     */   }
/*     */   
/*     */   public boolean mouseReleased(double mouseX, double mouseY, int button) {
/* 489 */     return this.scrollArea.handleMouseReleased(mouseX, mouseY, button);
/*     */   }
/*     */   
/*     */   public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
/* 493 */     return this.searchBar.keyPressed(keyCode, scanCode, modifiers);
/*     */   }
/*     */   
/*     */   public boolean charTyped(char chr, int modifiers) {
/* 497 */     return this.searchBar.charTyped(chr, modifiers);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void onSearchChanged(String text) {
/* 503 */     this.pendingSearch = text;
/* 504 */     this.pendingSearchTime = System.currentTimeMillis();
/*     */   }
/*     */   
/*     */   private void applySearch(String search) {
/* 508 */     this.currentSearch = search;
/* 509 */     applyFilters();
/*     */   }
/*     */   
/*     */   private void applyFilters() {
/* 513 */     String typeFilter = String.join(",", (Iterable)this.selectedTypes);
/* 514 */     GuideData.getInstance().resetPokedex(this.currentSearch, typeFilter);
/* 515 */     this.scrollArea.resetScroll();
/* 516 */     requestPage(0);
/*     */   }
/*     */   
/*     */   private void requestPage(int page) {
/* 520 */     String typeFilter = String.join(",", (Iterable)this.selectedTypes);
/* 521 */     GuideData.getInstance().setPokedexLoading(true);
/* 522 */     GuideNetwork.requestPokedexPage(page, this.currentSearch, typeFilter);
/* 523 */     this.lastRequestTime = System.currentTimeMillis();
/*     */   }
/*     */   
/*     */   private void requestNextPage() {
/* 527 */     int nextPage = GuideData.getInstance().getPokedexCurrentPage() + 1;
/* 528 */     requestPage(nextPage);
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\guide\screen\views\PokedexListView.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */