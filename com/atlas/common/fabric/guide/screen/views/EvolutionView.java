/*     */ package com.atlas.common.fabric.guide.screen.views;
/*     */ 
/*     */ import com.atlas.common.fabric.guide.data.EvolutionFamily;
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
/*     */ public class EvolutionView
/*     */ {
/*     */   private static final int SEARCH_BAR_HEIGHT = 16;
/*     */   private static final int FILTER_HEIGHT = 18;
/*     */   private static final int HEADER_HEIGHT = 44;
/*     */   private static final int MODEL_SIZE = 26;
/*  36 */   private static final String[] METHOD_FILTERS = new String[] { "All", "Level", "Item", "Trade", "Friendship", "Other" };
/*     */   private int x;
/*     */   private int y;
/*     */   private int width;
/*     */   private int height;
/*     */   private GuideSearchBar searchBar;
/*     */   private GuideScrollableArea scrollArea;
/*  43 */   private String currentSearch = "";
/*  44 */   private String currentMethodFilter = "All";
/*     */   private boolean initialized = false;
/*  46 */   private long lastRequestTime = 0L;
/*     */   
/*     */   private Consumer<String> onPokemonSelected;
/*     */   
/*  50 */   private String pendingSearch = null;
/*  51 */   private long pendingSearchTime = 0L;
/*     */   
/*     */   private static final long SEARCH_DEBOUNCE_MS = 300L;
/*     */   
/*  55 */   private String hoveredSlug = null;
/*     */ 
/*     */   
/*  58 */   private final Map<String, FloatingState> pokemonStates = new HashMap<>();
/*  59 */   private final Map<String, class_2960> resolvedSpeciesIds = new HashMap<>();
/*     */ 
/*     */   
/*  62 */   private final int[] methodFilterX = new int[METHOD_FILTERS.length];
/*  63 */   private final int[] methodFilterW = new int[METHOD_FILTERS.length];
/*     */   
/*     */   private static Map<String, class_2960> NORMALIZED_SPECIES_MAP;
/*     */ 
/*     */   
/*     */   private static synchronized Map<String, class_2960> getNormalizedSpeciesMap() {
/*  69 */     if (NORMALIZED_SPECIES_MAP != null) return NORMALIZED_SPECIES_MAP; 
/*  70 */     NORMALIZED_SPECIES_MAP = new HashMap<>();
/*     */     try {
/*  72 */       for (Species species : PokemonSpecies.getSpecies()) {
/*  73 */         class_2960 resId = species.getResourceIdentifier();
/*  74 */         String path = resId.method_12832();
/*  75 */         String namespace = resId.method_12836();
/*  76 */         class_2960 fullId = class_2960.method_60655(namespace, path);
/*  77 */         NORMALIZED_SPECIES_MAP.put(path, fullId);
/*  78 */         String normalized = path.replaceAll("[^a-z0-9]", "");
/*  79 */         NORMALIZED_SPECIES_MAP.putIfAbsent(normalized, fullId);
/*     */       } 
/*  81 */     } catch (Exception exception) {}
/*     */ 
/*     */     
/*  84 */     return NORMALIZED_SPECIES_MAP;
/*     */   }
/*     */   
/*     */   private class_2960 resolveSpeciesId(String slug) {
/*  88 */     if (this.resolvedSpeciesIds.containsKey(slug)) return this.resolvedSpeciesIds.get(slug); 
/*  89 */     Map<String, class_2960> speciesMap = getNormalizedSpeciesMap();
/*  90 */     class_2960 id = speciesMap.get(slug);
/*  91 */     if (id == null) {
/*  92 */       String normalized = slug.replaceAll("[^a-z0-9]", "");
/*  93 */       id = speciesMap.get(normalized);
/*     */     } 
/*  95 */     if (id == null) {
/*  96 */       id = class_2960.method_60655("cobblemon", slug);
/*     */     }
/*  98 */     this.resolvedSpeciesIds.put(slug, id);
/*  99 */     return id;
/*     */   }
/*     */   
/*     */   public EvolutionView() {
/* 103 */     PokemonRenderHelper.init();
/*     */   }
/*     */   
/*     */   public void init(int x, int y, int width, int height, Consumer<String> onPokemonSelected) {
/* 107 */     this.x = x;
/* 108 */     this.y = y;
/* 109 */     this.width = width;
/* 110 */     this.height = height;
/* 111 */     this.onPokemonSelected = onPokemonSelected;
/*     */     
/* 113 */     if (!this.initialized) {
/* 114 */       this.searchBar = new GuideSearchBar(x + 4, y + 4, width - 8, 16);
/* 115 */       this.searchBar.setPlaceholder("Search evolutions...");
/* 116 */       this.searchBar.setOnTextChanged(text -> {
/*     */             this.pendingSearch = text; this.pendingSearchTime = System.currentTimeMillis();
/* 118 */           }); int scrollTop = y + 44;
/* 119 */       int scrollHeight = height - 44;
/* 120 */       this.scrollArea = new GuideScrollableArea(x, scrollTop, width, scrollHeight);
/* 121 */       this.initialized = true;
/* 122 */       requestPage(0);
/*     */     } else {
/* 124 */       updateBounds(x, y, width, height);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void updateBounds(int x, int y, int width, int height) {
/* 129 */     this.x = x; this.y = y; this.width = width; this.height = height;
/* 130 */     if (this.searchBar != null) this.searchBar.updateBounds(x + 4, y + 4, width - 8, 16); 
/* 131 */     if (this.scrollArea != null) this.scrollArea.updateBounds(x, y + 44, width, height - 44);
/*     */   
/*     */   }
/*     */   
/*     */   public void render(class_332 ctx, class_327 textRenderer, int mouseX, int mouseY, float delta) {
/* 136 */     if (this.pendingSearch != null && System.currentTimeMillis() - this.pendingSearchTime > 300L) {
/* 137 */       this.currentSearch = this.pendingSearch;
/* 138 */       this.pendingSearch = null;
/* 139 */       GuideData.getInstance().resetEvolutions();
/* 140 */       this.scrollArea.resetScroll();
/* 141 */       requestPage(0);
/*     */     } 
/*     */     
/* 144 */     ctx.method_25294(this.x, this.y, this.x + this.width, this.y + this.height, GuideColors.SECTION_BG);
/* 145 */     this.searchBar.render(ctx, textRenderer, mouseX, mouseY);
/*     */ 
/*     */     
/* 148 */     renderMethodFilters(ctx, textRenderer, mouseX, mouseY);
/*     */     
/* 150 */     GuideData data = GuideData.getInstance();
/* 151 */     List<EvolutionFamily> families = data.getAllEvolutionFamilies();
/*     */ 
/*     */     
/* 154 */     int totalHeight = 4;
/* 155 */     for (EvolutionFamily family : families) {
/* 156 */       totalHeight += calculateFamilyHeight(textRenderer, family) + 6;
/*     */     }
/* 158 */     this.scrollArea.setContentHeight(totalHeight);
/* 159 */     this.scrollArea.beginRender(ctx);
/*     */     
/* 161 */     this.hoveredSlug = null;
/* 162 */     int cy = this.scrollArea.getY() + 2 - this.scrollArea.getScrollOffset();
/*     */     
/* 164 */     for (EvolutionFamily family : families) {
/* 165 */       int familyH = calculateFamilyHeight(textRenderer, family);
/* 166 */       if (cy > this.scrollArea.getY() + this.scrollArea.getHeight() + 50)
/* 167 */         break;  if (cy + familyH < this.scrollArea.getY() - 20) {
/* 168 */         cy += familyH + 6;
/*     */         
/*     */         continue;
/*     */       } 
/* 172 */       cy = renderFamily(ctx, textRenderer, family, cy, mouseX, mouseY, delta);
/*     */ 
/*     */       
/* 175 */       ctx.method_25294(this.x + 6, cy + 1, this.x + this.width - 6, cy + 2, GuideColors.BORDER_DIM);
/* 176 */       cy += 6;
/*     */     } 
/*     */     
/* 179 */     this.scrollArea.endRender(ctx);
/*     */ 
/*     */     
/* 182 */     if (data.isEvolutionLoading()) {
/* 183 */       ctx.method_25300(textRenderer, "Loading...", this.x + this.width / 2, this.scrollArea
/* 184 */           .getY() + this.scrollArea.getHeight() - 14, GuideColors.TEXT_DIM);
/*     */     }
/*     */     
/* 187 */     if (families.isEmpty() && !data.isEvolutionLoading()) {
/* 188 */       ctx.method_25300(textRenderer, "No evolution data found", this.x + this.width / 2, this.scrollArea
/* 189 */           .getY() + this.scrollArea.getHeight() / 2, GuideColors.TEXT_DIM);
/*     */     }
/*     */ 
/*     */     
/* 193 */     if (!families.isEmpty() && data.hasMoreEvolutionPages() && !data.isEvolutionLoading()) {
/* 194 */       int maxScroll = this.scrollArea.getMaxScroll();
/* 195 */       int threshold = Math.max(300, this.scrollArea.getHeight());
/* 196 */       if (maxScroll <= 0 || this.scrollArea.getScrollOffset() >= maxScroll - threshold) {
/* 197 */         long now = System.currentTimeMillis();
/* 198 */         if (now - this.lastRequestTime > 500L) {
/* 199 */           requestPage(data.getEvolutionCurrentPage() + 1);
/* 200 */           this.lastRequestTime = now;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void renderMethodFilters(class_332 ctx, class_327 textRenderer, int mouseX, int mouseY) {
/* 207 */     int filterY = this.y + 16 + 6;
/* 208 */     int fx = this.x + 4;
/*     */     
/* 210 */     for (int i = 0; i < METHOD_FILTERS.length; i++) {
/* 211 */       String method = METHOD_FILTERS[i];
/* 212 */       int tw = textRenderer.method_1727(method) + 8;
/* 213 */       boolean active = method.equals(this.currentMethodFilter);
/* 214 */       boolean hovered = (mouseX >= fx && mouseX < fx + tw && mouseY >= filterY && mouseY < filterY + 12);
/*     */       
/* 216 */       this.methodFilterX[i] = fx;
/* 217 */       this.methodFilterW[i] = tw;
/*     */       
/* 219 */       int bg = active ? GuideColors.ACCENT_DARK_GREEN : (hovered ? GuideColors.CARD_HOVER_BG : GuideColors.CARD_BG);
/* 220 */       ctx.method_25294(fx, filterY, fx + tw, filterY + 12, bg);
/* 221 */       ctx.method_49601(fx, filterY, tw, 12, active ? GuideColors.ACCENT_EMERALD : GuideColors.BORDER_DIM);
/*     */       
/* 223 */       int textColor = active ? GuideColors.TEXT_WHITE : (hovered ? GuideColors.TEXT_PRIMARY : GuideColors.TEXT_DIM);
/* 224 */       ctx.method_25300(textRenderer, method, fx + tw / 2, filterY + 2, textColor);
/*     */       
/* 226 */       fx += tw + 3;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int calculateFamilyHeight(class_327 textRenderer, EvolutionFamily family) {
/* 235 */     if (family.chain == null || family.chain.stages == null || family.chain.stages.isEmpty()) {
/* 236 */       return 22;
/*     */     }
/*     */ 
/*     */     
/* 240 */     int maxBranch = 1;
/* 241 */     for (EvolutionFamily.StageData stage : family.chain.stages) {
/* 242 */       if (stage.pokemon != null) {
/* 243 */         maxBranch = Math.max(maxBranch, stage.pokemon.size());
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 248 */     int cardH = 66;
/* 249 */     return 12 + maxBranch * (cardH + 2) + 4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int renderFamily(class_332 ctx, class_327 textRenderer, EvolutionFamily family, int startY, int mouseX, int mouseY, float delta) {
/* 259 */     int cx = this.x + 6;
/* 260 */     int cw = this.width - 12;
/*     */ 
/*     */     
/* 263 */     String header = (family.root != null) ? family.root.name : family.rootSlug;
/* 264 */     if (family.stageCount > 0) {
/* 265 */       header = header + " (" + header + " stages)";
/*     */     }
/* 267 */     ctx.method_51433(textRenderer, header, cx, startY, GuideColors.TEXT_ACCENT, true);
/* 268 */     int cy = startY + 12;
/*     */     
/* 270 */     if (family.chain == null || family.chain.stages == null || family.chain.stages.isEmpty()) {
/* 271 */       return cy + 6;
/*     */     }
/*     */     
/* 274 */     int stageCount = family.chain.stages.size();
/*     */ 
/*     */     
/* 277 */     int arrowZoneW = 14;
/* 278 */     int totalArrowSpace = (stageCount - 1) * arrowZoneW;
/* 279 */     int stageW = (cw - totalArrowSpace) / Math.max(1, stageCount);
/* 280 */     stageW = Math.min(stageW, 200);
/*     */ 
/*     */     
/* 283 */     int maxBranch = 1;
/* 284 */     for (EvolutionFamily.StageData stage : family.chain.stages) {
/* 285 */       if (stage.pokemon != null) {
/* 286 */         maxBranch = Math.max(maxBranch, stage.pokemon.size());
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 291 */     int cardH = 66;
/*     */ 
/*     */     
/* 294 */     for (int s = 0; s < stageCount; s++) {
/* 295 */       EvolutionFamily.StageData stage = family.chain.stages.get(s);
/* 296 */       int stageX = cx + s * (stageW + arrowZoneW);
/*     */       
/* 298 */       if (stage.pokemon != null) {
/*     */         
/* 300 */         for (int p = 0; p < stage.pokemon.size(); p++) {
/* 301 */           EvolutionFamily.StagePokemon pokemon = stage.pokemon.get(p);
/* 302 */           int pokemonY = cy + p * (cardH + 2);
/*     */ 
/*     */ 
/*     */           
/* 306 */           boolean isHovered = (mouseX >= stageX && mouseX < stageX + stageW && mouseY >= pokemonY && mouseY < pokemonY + cardH && this.scrollArea.isInBounds(mouseX, mouseY));
/*     */           
/* 308 */           if (isHovered) {
/* 309 */             this.hoveredSlug = pokemon.slug;
/* 310 */             GuideSounds.playHover("evo:" + pokemon.slug);
/*     */           } 
/*     */ 
/*     */           
/* 314 */           int cardBg = isHovered ? GuideColors.CARD_HOVER_BG : GuideColors.CARD_BG;
/* 315 */           ctx.method_25294(stageX, pokemonY, stageX + stageW, pokemonY + cardH, cardBg);
/* 316 */           ctx.method_49601(stageX, pokemonY, stageW, cardH, 
/* 317 */               isHovered ? GuideColors.BORDER_HIGHLIGHT : GuideColors.BORDER_DIM);
/*     */ 
/*     */           
/* 320 */           int modelX = stageX + (stageW - 26) / 2;
/* 321 */           renderPokemonInCell(ctx, pokemon.slug, modelX, pokemonY + 2, 26, delta);
/*     */ 
/*     */           
/* 324 */           int nameY = pokemonY + 26 + 3;
/* 325 */           String name = pokemon.name;
/* 326 */           int nameW = textRenderer.method_1727(name);
/* 327 */           while (nameW > stageW - 4 && name.length() > 3) {
/* 328 */             name = name.substring(0, name.length() - 1);
/* 329 */             nameW = textRenderer.method_1727(name + "..");
/*     */           } 
/* 331 */           if (!name.equals(pokemon.name)) name = name + ".."; 
/* 332 */           int nameColor = isHovered ? GuideColors.TEXT_WHITE : GuideColors.TEXT_PRIMARY;
/* 333 */           ctx.method_25300(textRenderer, name, stageX + stageW / 2, nameY, nameColor);
/*     */ 
/*     */           
/* 336 */           int afterTypesY = nameY + 10;
/* 337 */           if (pokemon.types != null && !pokemon.types.isEmpty()) {
/* 338 */             int typeY = nameY + 10;
/* 339 */             int totalTypeW = 0;
/* 340 */             for (String type : pokemon.types) {
/* 341 */               totalTypeW += GuideTypeBadge.getWidth(textRenderer, type) + 2;
/*     */             }
/* 343 */             totalTypeW -= 2;
/* 344 */             int typeX = Math.max(stageX + 2, stageX + (stageW - totalTypeW) / 2);
/* 345 */             for (String type : pokemon.types) {
/* 346 */               int tw = GuideTypeBadge.getWidth(textRenderer, type);
/* 347 */               if (typeX + tw > stageX + stageW - 2)
/* 348 */                 break;  GuideTypeBadge.draw(ctx, textRenderer, type, typeX, typeY);
/* 349 */               typeX += tw + 2;
/*     */             } 
/* 351 */             afterTypesY = typeY + 12;
/*     */           } 
/*     */ 
/*     */ 
/*     */           
/* 356 */           String method = !pokemon.method.isEmpty() ? pokemon.method : (!pokemon.details.isEmpty() ? pokemon.details : "");
/* 357 */           if (!method.isEmpty()) {
/* 358 */             String methodText = method;
/* 359 */             int maxMethodW = stageW - 8;
/* 360 */             while (textRenderer.method_1727(methodText) > maxMethodW && methodText.length() > 5) {
/* 361 */               methodText = methodText.substring(0, methodText.length() - 1);
/*     */             }
/* 363 */             if (!methodText.equals(method)) methodText = methodText + "..";
/*     */             
/* 365 */             int mw = textRenderer.method_1727(methodText) + 8;
/* 366 */             int mx = stageX + (stageW - mw) / 2;
/* 367 */             int my = afterTypesY + 1;
/* 368 */             ctx.method_25294(mx, my, mx + mw, my + 11, GuideColors.color(30, 45, 50, 220));
/* 369 */             ctx.method_49601(mx, my, mw, 11, GuideColors.color(46, 150, 130, 150));
/* 370 */             ctx.method_25300(textRenderer, methodText, stageX + stageW / 2, my + 2, GuideColors.ACCENT_TEAL);
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 375 */         if (s < stageCount - 1) {
/* 376 */           int arrowX = stageX + stageW;
/* 377 */           int arrowCenterY = cy + cardH / 2 - 3;
/* 378 */           ctx.method_25300(textRenderer, "→", arrowX + arrowZoneW / 2, arrowCenterY, GuideColors.ACCENT_EMERALD);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 384 */     cy += maxBranch * (cardH + 2) + 2;
/*     */     
/* 386 */     return cy;
/*     */   }
/*     */   
/*     */   private void renderPokemonInCell(class_332 ctx, String slug, int sx, int sy, int ss, float delta) {
/* 390 */     if (!PokemonRenderHelper.isAvailable() || slug == null || slug.isEmpty()) {
/* 391 */       ctx.method_25294(sx, sy, sx + ss, sy + ss, GuideColors.color(20, 25, 30, 200));
/*     */       
/*     */       return;
/*     */     } 
/*     */     try {
/* 396 */       class_2960 speciesId = resolveSpeciesId(slug);
/* 397 */       FloatingState state = this.pokemonStates.computeIfAbsent(slug, k -> new FloatingState());
/* 398 */       state.setCurrentAspects(Set.of());
/*     */       
/* 400 */       class_4587 mat = ctx.method_51448();
/* 401 */       mat.method_22903();
/* 402 */       mat.method_22904(sx + ss / 2.0D, sy + 2.0D, 0.0D);
/* 403 */       float scale = ss / 9.0F;
/* 404 */       mat.method_22905(scale, scale, 1.0F);
/*     */       
/* 406 */       Quaternionf rot = new Quaternionf();
/* 407 */       rot.rotationXYZ((float)Math.toRadians(13.0D), (float)Math.toRadians(35.0D), 0.0F);
/*     */       
/* 409 */       PokemonRenderHelper.draw(speciesId, mat, rot, state, delta);
/* 410 */       mat.method_22909();
/* 411 */     } catch (Exception e) {
/* 412 */       ctx.method_25294(sx, sy, sx + ss, sy + ss, GuideColors.color(20, 25, 30, 200));
/*     */     } 
/*     */   }
/*     */   
/*     */   private void requestPage(int page) {
/* 417 */     GuideData.getInstance().setEvolutionLoading(true);
/* 418 */     String filter = this.currentMethodFilter.equals("All") ? "" : this.currentMethodFilter.toLowerCase();
/* 419 */     GuideNetwork.requestGuideData("evolutions", page, this.currentSearch, filter);
/* 420 */     this.lastRequestTime = System.currentTimeMillis();
/*     */   }
/*     */   
/*     */   public boolean mouseClicked(double mouseX, double mouseY, int button) {
/* 424 */     if (this.searchBar.mouseClicked(mouseX, mouseY, button)) return true;
/*     */ 
/*     */     
/* 427 */     int filterY = this.y + 16 + 6;
/* 428 */     if (mouseY >= filterY && mouseY < (filterY + 12) && button == 0) {
/* 429 */       for (int i = 0; i < METHOD_FILTERS.length; i++) {
/* 430 */         if (mouseX >= this.methodFilterX[i] && mouseX < (this.methodFilterX[i] + this.methodFilterW[i])) {
/* 431 */           if (!METHOD_FILTERS[i].equals(this.currentMethodFilter)) {
/* 432 */             GuideSounds.playClick();
/* 433 */             this.currentMethodFilter = METHOD_FILTERS[i];
/* 434 */             GuideData.getInstance().resetEvolutions();
/* 435 */             this.scrollArea.resetScroll();
/* 436 */             requestPage(0);
/*     */           } 
/* 438 */           return true;
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 444 */     if (this.hoveredSlug != null && this.onPokemonSelected != null && button == 0) {
/* 445 */       GuideSounds.playNavigate();
/* 446 */       this.onPokemonSelected.accept(this.hoveredSlug);
/* 447 */       return true;
/*     */     } 
/*     */     
/* 450 */     return this.scrollArea.handleMouseClicked(mouseX, mouseY, button);
/*     */   }
/*     */   
/*     */   public boolean mouseScrolled(double mouseX, double mouseY, double h, double v) {
/* 454 */     return this.scrollArea.handleScroll(mouseX, mouseY, v);
/*     */   }
/*     */   
/*     */   public boolean mouseDragged(double mouseX, double mouseY, int button) {
/* 458 */     return this.scrollArea.handleMouseDragged(mouseX, mouseY, button);
/*     */   }
/*     */   
/*     */   public boolean mouseReleased(double mouseX, double mouseY, int button) {
/* 462 */     return this.scrollArea.handleMouseReleased(mouseX, mouseY, button);
/*     */   }
/*     */   
/*     */   public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
/* 466 */     return this.searchBar.keyPressed(keyCode, scanCode, modifiers);
/*     */   }
/*     */   
/*     */   public boolean charTyped(char chr, int modifiers) {
/* 470 */     return this.searchBar.charTyped(chr, modifiers);
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\guide\screen\views\EvolutionView.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */