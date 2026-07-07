/*     */ package com.atlas.common.fabric.skindex.screen;
/*     */ 
/*     */ import com.atlas.common.fabric.guide.screen.GuideColors;
/*     */ import com.atlas.common.fabric.guide.screen.GuideSounds;
/*     */ import com.atlas.common.fabric.guide.screen.widgets.GuideScrollableArea;
/*     */ import com.atlas.common.fabric.guide.screen.widgets.GuideSearchBar;
/*     */ import com.atlas.common.fabric.guide.screen.widgets.ModelViewerOverlay;
/*     */ import com.atlas.common.fabric.screen.PokemonRenderHelper;
/*     */ import com.atlas.common.fabric.skindex.data.SkinDexData;
/*     */ import com.atlas.common.fabric.skindex.data.SkinDexLineInfo;
/*     */ import com.cobblemon.mod.common.api.pokemon.PokemonSpecies;
/*     */ import com.cobblemon.mod.common.client.render.models.blockbench.FloatingState;
/*     */ import com.cobblemon.mod.common.pokemon.Species;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.function.Consumer;
/*     */ import java.util.stream.Collectors;
/*     */ import net.minecraft.class_2960;
/*     */ import net.minecraft.class_327;
/*     */ import net.minecraft.class_332;
/*     */ import net.minecraft.class_4587;
/*     */ import org.joml.Quaternionf;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SkinDexListView
/*     */ {
/*  35 */   private static final Logger LOGGER = LoggerFactory.getLogger(SkinDexListView.class);
/*     */   
/*     */   private static final int COLUMNS = 2;
/*     */   
/*     */   private static final int CARD_WIDTH = 182;
/*     */   private static final int CARD_HEIGHT = 74;
/*     */   private static final int CARD_GAP = 6;
/*     */   private static final int CARD_PADDING = 6;
/*     */   private static final int MODEL_SIZE = 44;
/*     */   private static final int SEARCH_BAR_HEIGHT = 16;
/*     */   private static final int SEARCH_BAR_GAP = 4;
/*     */   private static final int SEARCH_COLUMNS = 4;
/*     */   private static final int SEARCH_CARD_WIDTH = 88;
/*     */   private static final int SEARCH_CARD_HEIGHT = 66;
/*     */   private static final int SEARCH_CARD_GAP = 5;
/*     */   private static final int SEARCH_MODEL_SIZE = 36;
/*     */   private int x;
/*     */   private int y;
/*     */   private int width;
/*     */   private int height;
/*     */   private GuideScrollableArea scrollArea;
/*     */   private GuideSearchBar searchBar;
/*     */   private Consumer<String> onLineSelected;
/*     */   private ModelViewerOverlay modelViewer;
/*     */   private boolean initialized = false;
/*  60 */   private String searchText = "";
/*  61 */   private List<SkinDexLineInfo> filteredLines = null;
/*     */ 
/*     */   
/*  64 */   private int hoveredCardIndex = -1;
/*     */ 
/*     */   
/*  67 */   private final Map<Integer, FloatingState> modelStates = new HashMap<>();
/*  68 */   private final Map<String, class_2960> resolvedSpeciesIds = new HashMap<>();
/*     */   
/*     */   public SkinDexListView() {
/*  71 */     PokemonRenderHelper.init();
/*     */   }
/*     */   
/*     */   public void init(int x, int y, int width, int height, Consumer<String> onLineSelected, ModelViewerOverlay modelViewer) {
/*  75 */     this.x = x;
/*  76 */     this.y = y;
/*  77 */     this.width = width;
/*  78 */     this.height = height;
/*  79 */     this.onLineSelected = onLineSelected;
/*  80 */     this.modelViewer = modelViewer;
/*     */     
/*  82 */     int searchBarY = y + 2;
/*  83 */     int scrollY = searchBarY + 16 + 4;
/*  84 */     int scrollH = height - 16 - 4 - 2;
/*     */     
/*  86 */     if (!this.initialized) {
/*  87 */       this.searchBar = new GuideSearchBar(x + 4, searchBarY, width - 8, 16);
/*  88 */       this.searchBar.setPlaceholder("Search Pokemon...");
/*  89 */       this.searchBar.setOnTextChanged(text -> {
/*     */             this.searchText = text;
/*     */             this.filteredLines = null;
/*     */             this.scrollArea.resetScroll();
/*     */           });
/*  94 */       this.scrollArea = new GuideScrollableArea(x, scrollY, width, scrollH);
/*  95 */       this.initialized = true;
/*     */     } else {
/*  97 */       this.searchBar.updateBounds(x + 4, searchBarY, width - 8, 16);
/*  98 */       this.scrollArea.updateBounds(x, scrollY, width, scrollH);
/*     */     } 
/*     */   }
/*     */   
/*     */   private List<SkinDexLineInfo> getFilteredLines(List<SkinDexLineInfo> allLines) {
/* 103 */     if (this.searchText.isEmpty()) return allLines; 
/* 104 */     if (this.filteredLines != null) return this.filteredLines; 
/* 105 */     String lower = this.searchText.toLowerCase(Locale.ENGLISH);
/* 106 */     String normalized = normalizeSearch(this.searchText);
/* 107 */     this
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 115 */       .filteredLines = (List<SkinDexLineInfo>)allLines.stream().filter(line -> (matchesSearch(line.name(), lower, normalized) || matchesSearch(line.rawName(), lower, normalized) || matchesSearch(line.displayPokemon(), lower, normalized) || line.skins().stream().anyMatch(()))).collect(Collectors.toList());
/* 116 */     return this.filteredLines;
/*     */   }
/*     */   
/*     */   public void render(class_332 ctx, class_327 textRenderer, int mouseX, int mouseY, float delta) {
/* 120 */     ctx.method_25294(this.x, this.y, this.x + this.width, this.y + this.height, GuideColors.SECTION_BG);
/*     */ 
/*     */     
/* 123 */     this.searchBar.render(ctx, textRenderer, mouseX, mouseY);
/*     */     
/* 125 */     SkinDexData data = SkinDexData.getInstance();
/* 126 */     List<SkinDexLineInfo> allLines = data.getLines();
/*     */     
/* 128 */     if (data.isLinesLoading()) {
/* 129 */       ctx.method_25300(textRenderer, "Loading...", this.x + this.width / 2, this.y + this.height / 2, GuideColors.TEXT_DIM);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 134 */     List<SkinDexLineInfo> lines = getFilteredLines(allLines);
/*     */     
/* 136 */     if (lines.isEmpty()) {
/* 137 */       String msg = this.searchText.isEmpty() ? "No skin collections available" : ("No results for \"" + this.searchText + "\"");
/* 138 */       ctx.method_25300(textRenderer, msg, this.x + this.width / 2, this.y + this.height / 2, GuideColors.TEXT_DIM);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 143 */     if (!this.searchText.isEmpty()) {
/*     */       
/* 145 */       int gridWidth = 367;
/* 146 */       int gridLeft = this.x + (this.width - gridWidth) / 2;
/* 147 */       int rows = (lines.size() + 4 - 1) / 4;
/* 148 */       int totalHeight = rows * 71 + 10;
/*     */       
/* 150 */       this.scrollArea.setContentHeight(totalHeight);
/* 151 */       this.scrollArea.beginRender(ctx);
/*     */       
/* 153 */       this.hoveredCardIndex = -1;
/* 154 */       int cy = this.scrollArea.getY() + 5 - this.scrollArea.getScrollOffset();
/*     */       
/* 156 */       for (int i = 0; i < lines.size(); i++) {
/* 157 */         int col = i % 4;
/* 158 */         int row = i / 4;
/*     */         
/* 160 */         int cardX = gridLeft + col * 93;
/* 161 */         int cardY = cy + row * 71;
/*     */         
/* 163 */         if (cardY + 66 >= this.scrollArea.getY()) {
/* 164 */           if (cardY > this.scrollArea.getY() + this.scrollArea.getHeight() + 20)
/*     */             break; 
/* 166 */           SkinDexLineInfo line = lines.get(i);
/*     */ 
/*     */           
/* 169 */           boolean isHovered = (mouseX >= cardX && mouseX < cardX + 88 && mouseY >= cardY && mouseY < cardY + 66 && this.scrollArea.isInBounds(mouseX, mouseY));
/*     */           
/* 171 */           if (isHovered) this.hoveredCardIndex = i;
/*     */           
/* 173 */           renderSearchCard(ctx, textRenderer, line, cardX, cardY, isHovered, i, delta);
/*     */         } 
/*     */       } 
/* 176 */       this.scrollArea.endRender(ctx);
/*     */     } else {
/*     */       
/* 179 */       int gridWidth = 370;
/* 180 */       int gridLeft = this.x + (this.width - gridWidth) / 2;
/* 181 */       int rows = (lines.size() + 2 - 1) / 2;
/* 182 */       int totalHeight = rows * 80 + 12;
/*     */       
/* 184 */       this.scrollArea.setContentHeight(totalHeight);
/* 185 */       this.scrollArea.beginRender(ctx);
/*     */       
/* 187 */       this.hoveredCardIndex = -1;
/* 188 */       int cy = this.scrollArea.getY() + 6 - this.scrollArea.getScrollOffset();
/*     */       
/* 190 */       for (int i = 0; i < lines.size(); i++) {
/* 191 */         int col = i % 2;
/* 192 */         int row = i / 2;
/*     */         
/* 194 */         int cardX = gridLeft + col * 188;
/* 195 */         int cardY = cy + row * 80;
/*     */         
/* 197 */         if (cardY + 74 >= this.scrollArea.getY()) {
/* 198 */           if (cardY > this.scrollArea.getY() + this.scrollArea.getHeight() + 20)
/*     */             break; 
/* 200 */           SkinDexLineInfo line = lines.get(i);
/*     */ 
/*     */           
/* 203 */           boolean isHovered = (mouseX >= cardX && mouseX < cardX + 182 && mouseY >= cardY && mouseY < cardY + 74 && this.scrollArea.isInBounds(mouseX, mouseY));
/*     */           
/* 205 */           if (isHovered) this.hoveredCardIndex = i;
/*     */           
/* 207 */           renderCard(ctx, textRenderer, line, cardX, cardY, isHovered, i, delta);
/*     */         } 
/*     */       } 
/* 210 */       this.scrollArea.endRender(ctx);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void renderCard(class_332 ctx, class_327 textRenderer, SkinDexLineInfo line, int cardX, int cardY, boolean hovered, int index, float delta) {
/* 216 */     boolean hasRewards = !line.rewardDescriptions().isEmpty();
/* 217 */     boolean completed = (hasRewards && line.completedCount() >= line.totalCount());
/* 218 */     boolean claimed = line.claimed();
/* 219 */     int accentColor = parseColor(line.color(), 255);
/*     */ 
/*     */     
/* 222 */     int bgColor = hovered ? GuideColors.CARD_HOVER_BG : GuideColors.CARD_BG;
/* 223 */     ctx.method_25294(cardX, cardY, cardX + 182, cardY + 74, bgColor);
/*     */ 
/*     */     
/* 226 */     ctx.method_25294(cardX, cardY, cardX + 3, cardY + 74, accentColor);
/*     */ 
/*     */ 
/*     */     
/* 230 */     int borderColor = completed ? GuideColors.ACCENT_EMERALD : (hovered ? GuideColors.BORDER : GuideColors.BORDER_DIM);
/* 231 */     ctx.method_49601(cardX, cardY, 182, 74, borderColor);
/*     */ 
/*     */     
/* 234 */     int modelX = cardX + 6 + 2;
/* 235 */     int modelY = cardY + 15;
/* 236 */     renderPokemonModel(ctx, line.displayPokemon(), line.displayAspect(), modelX, modelY, 44, index, delta);
/*     */ 
/*     */     
/* 239 */     int textX = modelX + 44 + 6;
/* 240 */     int availableTextWidth = 116;
/*     */ 
/*     */     
/* 243 */     int nameY = cardY + 10;
/* 244 */     int nameColor = hovered ? GuideColors.TEXT_WHITE : GuideColors.TEXT_PRIMARY;
/* 245 */     ctx.method_51433(textRenderer, line.name(), textX, nameY, nameColor, true);
/*     */     
/* 247 */     if (hasRewards) {
/*     */       
/* 249 */       int compY = nameY + 12;
/* 250 */       String completion = "" + line.completedCount() + "/" + line.completedCount() + " Collected";
/* 251 */       int completionColor = completed ? GuideColors.ACCENT_EMERALD : GuideColors.TEXT_DIM;
/* 252 */       ctx.method_51433(textRenderer, completion, textX, compY, completionColor, true);
/*     */ 
/*     */       
/* 255 */       int barY = compY + 12;
/* 256 */       int barWidth = availableTextWidth;
/* 257 */       int barHeight = 3;
/*     */ 
/*     */       
/* 260 */       ctx.method_25294(textX, barY, textX + barWidth, barY + barHeight, GuideColors.color(10, 10, 15, 255));
/*     */       
/* 262 */       if (line.totalCount() > 0) {
/* 263 */         int fillWidth = (int)(line.completedCount() / line.totalCount() * barWidth);
/* 264 */         if (fillWidth > 0) {
/* 265 */           int fillColor = completed ? GuideColors.ACCENT_EMERALD : accentColor;
/* 266 */           ctx.method_25294(textX, barY, textX + fillWidth, barY + barHeight, fillColor);
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 271 */       int rewardY = barY + 7;
/* 272 */       for (String rewardDesc : line.rewardDescriptions()) {
/* 273 */         String rewardText = "◆ " + rewardDesc;
/* 274 */         if (textRenderer.method_1727(rewardText) > availableTextWidth) {
/* 275 */           while (textRenderer.method_1727(rewardText + "..") > availableTextWidth && rewardText.length() > 4) {
/* 276 */             rewardText = rewardText.substring(0, rewardText.length() - 1);
/*     */           }
/* 278 */           rewardText = rewardText + "..";
/*     */         } 
/* 280 */         ctx.method_51433(textRenderer, rewardText, textX, rewardY, GuideColors.TEXT_MUTED, true);
/* 281 */         rewardY += 10;
/*     */       } 
/*     */     } else {
/*     */       
/* 285 */       int compY = nameY + 12;
/* 286 */       String skinCount = "" + line.totalCount() + " Skins";
/* 287 */       ctx.method_51433(textRenderer, skinCount, textX, compY, GuideColors.TEXT_DIM, true);
/*     */     } 
/*     */ 
/*     */     
/* 291 */     if (hasRewards && claimed) {
/* 292 */       String badge = "CLAIMED";
/* 293 */       int badgeWidth = textRenderer.method_1727(badge) + 6;
/* 294 */       int badgeX = cardX + 182 - badgeWidth - 4;
/* 295 */       int badgeY = cardY + 4;
/* 296 */       ctx.method_25294(badgeX, badgeY, badgeX + badgeWidth, badgeY + 10, GuideColors.ACCENT_DARK_GREEN);
/* 297 */       ctx.method_51433(textRenderer, badge, badgeX + 3, badgeY + 1, GuideColors.TEXT_WHITE, true);
/* 298 */     } else if (hasRewards && completed) {
/* 299 */       String check = "✔";
/* 300 */       int checkX = cardX + 182 - textRenderer.method_1727(check) - 6;
/* 301 */       int checkY = cardY + 4;
/* 302 */       ctx.method_51433(textRenderer, check, checkX, checkY, GuideColors.ACCENT_EMERALD, true);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderSearchCard(class_332 ctx, class_327 textRenderer, SkinDexLineInfo line, int cardX, int cardY, boolean hovered, int index, float delta) {
/* 312 */     int accentColor = parseColor(line.color(), 255);
/*     */ 
/*     */     
/* 315 */     int bgColor = hovered ? GuideColors.CARD_HOVER_BG : GuideColors.CARD_BG;
/* 316 */     ctx.method_25294(cardX, cardY, cardX + 88, cardY + 66, bgColor);
/*     */ 
/*     */     
/* 319 */     int borderColor = hovered ? GuideColors.BORDER : GuideColors.BORDER_DIM;
/* 320 */     ctx.method_49601(cardX, cardY, 88, 66, borderColor);
/*     */ 
/*     */     
/* 323 */     ctx.method_25294(cardX, cardY, cardX + 88, cardY + 2, accentColor);
/*     */ 
/*     */     
/* 326 */     SkinDexLineInfo.SkinPreview preview = getSearchPreview(line);
/* 327 */     int modelX = cardX + 26;
/* 328 */     int modelY = cardY + 3;
/* 329 */     renderPokemonModel(ctx, preview.pokemonId(), preview.aspect(), modelX, modelY, 36, index, delta);
/*     */ 
/*     */ 
/*     */     
/* 333 */     String pokemonName = formatPokemonName(preview.pokemonId());
/* 334 */     int nameWidth = textRenderer.method_1727(pokemonName);
/* 335 */     int nameX = cardX + (88 - nameWidth) / 2;
/* 336 */     int nameY = cardY + 66 - 20;
/* 337 */     int nameColor = hovered ? GuideColors.TEXT_WHITE : GuideColors.TEXT_PRIMARY;
/* 338 */     ctx.method_51433(textRenderer, pokemonName, nameX, nameY, nameColor, true);
/*     */ 
/*     */     
/* 341 */     String lineName = line.name();
/* 342 */     int lineWidth = textRenderer.method_1727(lineName);
/*     */     
/* 344 */     if (lineWidth > 82) {
/* 345 */       while (textRenderer.method_1727(lineName + "..") > 82 && lineName.length() > 2) {
/* 346 */         lineName = lineName.substring(0, lineName.length() - 1);
/*     */       }
/* 348 */       lineName = lineName + "..";
/* 349 */       lineWidth = textRenderer.method_1727(lineName);
/*     */     } 
/* 351 */     int lineX = cardX + (88 - lineWidth) / 2;
/* 352 */     int lineY = nameY + 10;
/* 353 */     ctx.method_51433(textRenderer, lineName, lineX, lineY, GuideColors.TEXT_MUTED, true);
/*     */   }
/*     */   
/*     */   private SkinDexLineInfo.SkinPreview getSearchPreview(SkinDexLineInfo line) {
/* 357 */     if (!this.searchText.isEmpty()) {
/* 358 */       String lower = this.searchText.toLowerCase(Locale.ENGLISH);
/* 359 */       String normalized = normalizeSearch(this.searchText);
/* 360 */       for (SkinDexLineInfo.SkinPreview skin : line.skins()) {
/* 361 */         if (matchesSearch(skin.id(), lower, normalized) || 
/* 362 */           matchesSearch(skin.name(), lower, normalized) || 
/* 363 */           matchesSearch(skin.pokemonId(), lower, normalized)) {
/* 364 */           return skin;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 369 */     return new SkinDexLineInfo.SkinPreview(line
/* 370 */         .id(), line
/* 371 */         .name(), line
/* 372 */         .displayPokemon(), line
/* 373 */         .displayAspect());
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean matchesSearch(String value, String lowerQuery, String normalizedQuery) {
/* 378 */     if (value == null || value.isEmpty()) return false;
/*     */     
/* 380 */     String lowerValue = value.toLowerCase(Locale.ENGLISH);
/* 381 */     if (lowerValue.contains(lowerQuery)) return true;
/*     */     
/* 383 */     String normalizedValue = normalizeSearch(value);
/* 384 */     return (!normalizedQuery.isEmpty() && normalizedValue.contains(normalizedQuery));
/*     */   }
/*     */   
/*     */   private String normalizeSearch(String value) {
/* 388 */     return (value == null) ? "" : value.toLowerCase(Locale.ENGLISH).replaceAll("[^a-z0-9]", "");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String formatPokemonName(String slug) {
/* 395 */     if (slug == null || slug.isEmpty()) return ""; 
/* 396 */     return "" + Character.toUpperCase(slug.charAt(0)) + Character.toUpperCase(slug.charAt(0));
/*     */   }
/*     */ 
/*     */   
/*     */   private void renderPokemonModel(class_332 ctx, String pokemonId, String aspect, int sx, int sy, int ss, int stateIndex, float delta) {
/* 401 */     if (pokemonId == null || pokemonId.isEmpty())
/*     */       return; 
/* 403 */     if (!PokemonRenderHelper.isAvailable()) {
/* 404 */       ctx.method_25294(sx, sy, sx + ss, sy + ss, GuideColors.color(20, 25, 30, 200));
/*     */       
/*     */       return;
/*     */     } 
/*     */     try {
/* 409 */       class_2960 speciesId = resolveSpeciesId(pokemonId);
/*     */       
/* 411 */       FloatingState state = this.modelStates.computeIfAbsent(Integer.valueOf(stateIndex), k -> new FloatingState());
/* 412 */       Set<String> aspects = new HashSet<>();
/* 413 */       if (aspect != null && !aspect.isEmpty())
/* 414 */         for (String a : aspect.split(" ")) {
/* 415 */           if (!a.isEmpty()) aspects.add(a);
/*     */         
/*     */         }  
/* 418 */       state.setCurrentAspects(aspects);
/*     */       
/* 420 */       class_4587 mat = ctx.method_51448();
/* 421 */       mat.method_22903();
/* 422 */       mat.method_22904(sx + ss / 2.0D, sy + 2.0D, 0.0D);
/* 423 */       float scale = ss / 10.0F;
/* 424 */       mat.method_22905(scale, scale, 1.0F);
/*     */       
/* 426 */       Quaternionf rot = new Quaternionf();
/* 427 */       rot.rotationXYZ((float)Math.toRadians(13.0D), (float)Math.toRadians(35.0D), 0.0F);
/*     */       
/* 429 */       PokemonRenderHelper.draw(speciesId, mat, rot, state, delta);
/* 430 */       mat.method_22909();
/* 431 */     } catch (Exception e) {
/* 432 */       LOGGER.error("[SkinDex] Model render failed for pokemonId='{}', aspect='{}', stateIndex={}: {}", new Object[] { pokemonId, aspect, 
/* 433 */             Integer.valueOf(stateIndex), e.getMessage(), e });
/* 434 */       ctx.method_25294(sx, sy, sx + ss, sy + ss, GuideColors.color(20, 25, 30, 200));
/*     */     } 
/*     */   }
/*     */   
/*     */   private class_2960 resolveSpeciesId(String slug) {
/* 439 */     class_2960 id, cached = this.resolvedSpeciesIds.get(slug);
/* 440 */     if (cached != null) return cached;
/*     */     
/* 442 */     Species species = PokemonSpecies.getByName(slug);
/*     */     
/* 444 */     if (species != null) {
/* 445 */       id = species.getResourceIdentifier();
/*     */     } else {
/* 447 */       id = class_2960.method_60655("cobblemon", slug);
/*     */     } 
/* 449 */     this.resolvedSpeciesIds.put(slug, id);
/* 450 */     return id;
/*     */   }
/*     */   
/*     */   private int parseColor(String hex, int alpha) {
/*     */     try {
/* 455 */       if (hex.startsWith("#")) hex = hex.substring(1); 
/* 456 */       int rgb = Integer.parseInt(hex, 16);
/* 457 */       return alpha << 24 | rgb;
/* 458 */     } catch (Exception e) {
/* 459 */       return GuideColors.TEXT_WHITE;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean mouseClicked(double mouseX, double mouseY, int button) {
/* 466 */     if (this.searchBar.mouseClicked(mouseX, mouseY, button)) return true; 
/* 467 */     if (this.scrollArea.handleMouseClicked(mouseX, mouseY, button)) return true;
/*     */     
/* 469 */     if (button == 0 && this.hoveredCardIndex >= 0) {
/* 470 */       List<SkinDexLineInfo> lines = getFilteredLines(SkinDexData.getInstance().getLines());
/* 471 */       if (this.hoveredCardIndex < lines.size()) {
/* 472 */         SkinDexLineInfo line = lines.get(this.hoveredCardIndex);
/*     */         
/* 474 */         if (!this.searchText.isEmpty() && this.modelViewer != null) {
/*     */           
/* 476 */           GuideSounds.playNavigate();
/* 477 */           SkinDexLineInfo.SkinPreview preview = getSearchPreview(line);
/* 478 */           class_2960 speciesId = resolveSpeciesId(preview.pokemonId());
/* 479 */           FloatingState previewState = new FloatingState();
/* 480 */           Set<String> aspects = new HashSet<>();
/* 481 */           if (preview.aspect() != null && !preview.aspect().isEmpty())
/* 482 */             for (String a : preview.aspect().split(" ")) {
/* 483 */               if (!a.isEmpty()) aspects.add(a);
/*     */             
/*     */             }  
/* 486 */           this.modelViewer.open(speciesId, previewState, aspects, () -> { 
/* 487 */               }); return true;
/* 488 */         }  if (this.onLineSelected != null) {
/*     */           
/* 490 */           GuideSounds.playNavigate();
/* 491 */           this.onLineSelected.accept(line.id());
/* 492 */           return true;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 497 */     return false;
/*     */   }
/*     */   
/*     */   public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
/* 501 */     return this.searchBar.keyPressed(keyCode, scanCode, modifiers);
/*     */   }
/*     */   
/*     */   public boolean charTyped(char chr, int modifiers) {
/* 505 */     return this.searchBar.charTyped(chr, modifiers);
/*     */   }
/*     */   
/*     */   public boolean mouseScrolled(double mouseX, double mouseY, double h, double v) {
/* 509 */     return this.scrollArea.handleScroll(mouseX, mouseY, v);
/*     */   }
/*     */   
/*     */   public boolean mouseDragged(double mouseX, double mouseY, int button) {
/* 513 */     return this.scrollArea.handleMouseDragged(mouseX, mouseY, button);
/*     */   }
/*     */   
/*     */   public boolean mouseReleased(double mouseX, double mouseY, int button) {
/* 517 */     return this.scrollArea.handleMouseReleased(mouseX, mouseY, button);
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\skindex\screen\SkinDexListView.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */