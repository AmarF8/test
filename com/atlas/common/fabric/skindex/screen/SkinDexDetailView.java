/*     */ package com.atlas.common.fabric.skindex.screen;
/*     */ 
/*     */ import com.atlas.common.fabric.guide.screen.GuideColors;
/*     */ import com.atlas.common.fabric.guide.screen.GuideSounds;
/*     */ import com.atlas.common.fabric.guide.screen.widgets.GuideScrollableArea;
/*     */ import com.atlas.common.fabric.guide.screen.widgets.ModelViewerOverlay;
/*     */ import com.atlas.common.fabric.screen.PokemonRenderHelper;
/*     */ import com.atlas.common.fabric.skindex.data.SkinDexData;
/*     */ import com.atlas.common.fabric.skindex.data.SkinDexDetailInfo;
/*     */ import com.atlas.common.fabric.skindex.network.SkinDexNetwork;
/*     */ import com.cobblemon.mod.common.api.pokemon.PokemonSpecies;
/*     */ import com.cobblemon.mod.common.client.render.models.blockbench.FloatingState;
/*     */ import com.cobblemon.mod.common.pokemon.Species;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import net.minecraft.class_2960;
/*     */ import net.minecraft.class_310;
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
/*     */ 
/*     */ 
/*     */ public class SkinDexDetailView
/*     */ {
/*  34 */   private static final Logger LOGGER = LoggerFactory.getLogger(SkinDexDetailView.class);
/*     */   
/*     */   private static final int HEADER_HEIGHT = 16;
/*     */   
/*     */   private static final int COLUMNS = 4;
/*     */   
/*     */   private static final int CARD_WIDTH = 88;
/*     */   
/*     */   private static final int CARD_HEIGHT = 66;
/*     */   
/*     */   private static final int CARD_GAP = 5;
/*     */   
/*     */   private static final int MODEL_SIZE = 36;
/*     */   
/*     */   private static final int CLAIM_BUTTON_WIDTH = 130;
/*     */   private static final int CLAIM_BUTTON_HEIGHT = 14;
/*     */   private static final int SHINY_BTN_SIZE = 9;
/*     */   private static final int AURA_BTN_SIZE = 9;
/*     */   private static final int AURA_DROPDOWN_ITEM_HEIGHT = 12;
/*     */   private int x;
/*     */   private int y;
/*     */   private int width;
/*     */   private int height;
/*     */   private int screenWidth;
/*     */   private int screenHeight;
/*     */   private GuideScrollableArea scrollArea;
/*     */   private ModelViewerOverlay modelViewer;
/*     */   private Runnable onBack;
/*     */   private boolean initialized = false;
/*  63 */   private int hoveredSkinIndex = -1;
/*  64 */   private int hoveredShinyIndex = -1;
/*  65 */   private int hoveredAuraButtonIndex = -1;
/*     */   
/*     */   private boolean backButtonHovered = false;
/*     */   
/*     */   private boolean claimButtonHovered = false;
/*  70 */   private final Set<Integer> shinyToggles = new HashSet<>();
/*     */ 
/*     */   
/*  73 */   private int auraDropdownCardIndex = -1;
/*  74 */   private int hoveredAuraOptionIndex = -1;
/*  75 */   private final Map<Integer, Integer> selectedAuraIndex = new HashMap<>();
/*     */   
/*     */   private int dropdownX;
/*     */   
/*     */   private int dropdownY;
/*     */   private int dropdownHeight;
/*     */   private int dropdownWidth;
/*     */   private boolean deferredDropdownPending = false;
/*     */   private int deferredDropdownMouseX;
/*     */   private int deferredDropdownMouseY;
/*  85 */   private final Map<Integer, FloatingState> modelStates = new HashMap<>();
/*  86 */   private final Map<String, class_2960> resolvedSpeciesIds = new HashMap<>();
/*     */   
/*     */   public SkinDexDetailView() {
/*  89 */     PokemonRenderHelper.init();
/*     */   }
/*     */   
/*     */   public void init(int x, int y, int width, int height, ModelViewerOverlay modelViewer, Runnable onBack) {
/*  93 */     this.x = x;
/*  94 */     this.y = y;
/*  95 */     this.width = width;
/*  96 */     this.height = height;
/*  97 */     this.modelViewer = modelViewer;
/*  98 */     this.onBack = onBack;
/*     */     
/* 100 */     int scrollY = y + 16;
/* 101 */     int scrollH = height - 16;
/*     */     
/* 103 */     if (!this.initialized) {
/* 104 */       this.scrollArea = new GuideScrollableArea(x, scrollY, width, scrollH);
/* 105 */       this.initialized = true;
/*     */     } else {
/* 107 */       this.scrollArea.updateBounds(x, scrollY, width, scrollH);
/*     */     } 
/*     */ 
/*     */     
/* 111 */     this.shinyToggles.clear();
/* 112 */     this.selectedAuraIndex.clear();
/* 113 */     this.auraDropdownCardIndex = -1;
/*     */   }
/*     */   
/*     */   public void setScreenDimensions(int screenWidth, int screenHeight) {
/* 117 */     this.screenWidth = screenWidth;
/* 118 */     this.screenHeight = screenHeight;
/*     */   }
/*     */   
/*     */   public void render(class_332 ctx, class_327 textRenderer, int mouseX, int mouseY, float delta) {
/* 122 */     ctx.method_25294(this.x, this.y, this.x + this.width, this.y + this.height, GuideColors.SECTION_BG);
/*     */     
/* 124 */     SkinDexData data = SkinDexData.getInstance();
/* 125 */     SkinDexDetailInfo detail = data.getCurrentDetail();
/*     */ 
/*     */     
/* 128 */     int effectiveMouseX = (this.modelViewer != null && this.modelViewer.isVisible()) ? -1 : mouseX;
/* 129 */     int effectiveMouseY = (this.modelViewer != null && this.modelViewer.isVisible()) ? -1 : mouseY;
/*     */ 
/*     */     
/* 132 */     renderHeader(ctx, textRenderer, detail, effectiveMouseX, effectiveMouseY);
/*     */     
/* 134 */     if (data.isDetailLoading() || detail == null) {
/* 135 */       ctx.method_25300(textRenderer, "Loading...", this.x + this.width / 2, this.y + this.height / 2, GuideColors.TEXT_DIM);
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 141 */     boolean hasRewards = !detail.rewardDescriptions().isEmpty();
/* 142 */     int rewardSectionHeight = hasRewards ? calculateRewardSectionHeight(detail) : 0;
/* 143 */     int gridWidth = 367;
/* 144 */     int rows = (detail.skins().size() + 4 - 1) / 4;
/* 145 */     int skinsHeight = rows * 71 + 5;
/* 146 */     int hintHeight = 22;
/* 147 */     int totalContentHeight = rewardSectionHeight + (hasRewards ? hintHeight : 0) + hintHeight + skinsHeight + 8;
/*     */     
/* 149 */     this.scrollArea.setContentHeight(totalContentHeight);
/* 150 */     this.scrollArea.beginRender(ctx);
/*     */     
/* 152 */     int cy = this.scrollArea.getY() - this.scrollArea.getScrollOffset();
/*     */     
/* 154 */     int contentEndY = cy + 4;
/*     */ 
/*     */     
/* 157 */     if (hasRewards) {
/* 158 */       contentEndY = renderRewards(ctx, textRenderer, detail, contentEndY, effectiveMouseX, effectiveMouseY);
/*     */     }
/*     */ 
/*     */     
/* 162 */     String hintText = "Click a skin to preview its 3D model";
/* 163 */     ctx.method_25300(textRenderer, hintText, this.x + this.width / 2, contentEndY + 8, GuideColors.TEXT_MUTED);
/*     */ 
/*     */     
/* 166 */     int gridLeft = this.x + (this.width - gridWidth) / 2;
/* 167 */     int gridStartY = contentEndY + 22;
/*     */     
/* 169 */     this.hoveredSkinIndex = -1;
/* 170 */     this.hoveredShinyIndex = -1;
/* 171 */     this.hoveredAuraButtonIndex = -1;
/* 172 */     this.hoveredAuraOptionIndex = -1;
/*     */     
/* 174 */     boolean hasAuras = !detail.auras().isEmpty();
/*     */     
/* 176 */     for (int i = 0; i < detail.skins().size(); i++) {
/* 177 */       int col = i % 4;
/* 178 */       int row = i / 4;
/*     */       
/* 180 */       int cardX = gridLeft + col * 93;
/* 181 */       int cardY = gridStartY + row * 71;
/*     */       
/* 183 */       if (cardY + 66 >= this.scrollArea.getY()) {
/* 184 */         if (cardY > this.scrollArea.getY() + this.scrollArea.getHeight() + 20)
/*     */           break; 
/* 186 */         SkinDexDetailInfo.SkinEntry skin = detail.skins().get(i);
/*     */ 
/*     */         
/* 189 */         boolean isHovered = (effectiveMouseX >= cardX && effectiveMouseX < cardX + 88 && effectiveMouseY >= cardY && effectiveMouseY < cardY + 66 && this.scrollArea.isInBounds(effectiveMouseX, effectiveMouseY));
/*     */         
/* 191 */         if (isHovered) this.hoveredSkinIndex = i;
/*     */ 
/*     */         
/* 194 */         int shinyX = cardX + 2;
/* 195 */         int shinyY = cardY + 2;
/* 196 */         if (effectiveMouseX >= shinyX && effectiveMouseX < shinyX + 9 && effectiveMouseY >= shinyY && effectiveMouseY < shinyY + 9 && this.scrollArea
/*     */           
/* 198 */           .isInBounds(effectiveMouseX, effectiveMouseY)) {
/* 199 */           this.hoveredShinyIndex = i;
/*     */         }
/*     */ 
/*     */         
/* 203 */         if (hasAuras) {
/* 204 */           int auraX = cardX + 2 + 9 + 2;
/* 205 */           int auraY = cardY + 2;
/* 206 */           if (effectiveMouseX >= auraX && effectiveMouseX < auraX + 9 && effectiveMouseY >= auraY && effectiveMouseY < auraY + 9 && this.scrollArea
/*     */             
/* 208 */             .isInBounds(effectiveMouseX, effectiveMouseY)) {
/* 209 */             this.hoveredAuraButtonIndex = i;
/*     */           }
/*     */         } 
/*     */         
/* 213 */         renderSkinCard(ctx, textRenderer, detail, skin, cardX, cardY, isHovered, i, delta);
/*     */       } 
/*     */     } 
/*     */     
/* 217 */     this.deferredDropdownPending = false;
/* 218 */     if (this.auraDropdownCardIndex >= 0 && hasAuras) {
/*     */ 
/*     */       
/* 221 */       cacheDropdownPosition(detail, gridLeft, gridStartY);
/* 222 */       this.deferredDropdownPending = true;
/* 223 */       this.deferredDropdownMouseX = effectiveMouseX;
/* 224 */       this.deferredDropdownMouseY = effectiveMouseY;
/*     */     } 
/*     */     
/* 227 */     this.scrollArea.endRender(ctx);
/*     */ 
/*     */     
/* 230 */     if (this.deferredDropdownPending) {
/* 231 */       renderAuraDropdown(ctx, textRenderer, detail, gridLeft, gridStartY, this.deferredDropdownMouseX, this.deferredDropdownMouseY);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void renderHeader(class_332 ctx, class_327 textRenderer, SkinDexDetailInfo detail, int mouseX, int mouseY) {
/* 237 */     int headerY = this.y;
/* 238 */     int insetX = this.x + 8;
/*     */ 
/*     */     
/* 241 */     ctx.method_25294(this.x, headerY + 16 - 1, this.x + this.width, headerY + 16, GuideColors.BORDER_DIM);
/*     */ 
/*     */     
/* 244 */     String backLabel = "← Back";
/* 245 */     int backWidth = textRenderer.method_1727(backLabel) + 4;
/* 246 */     int backX = insetX;
/* 247 */     int backY = headerY + 3;
/*     */     
/* 249 */     this.backButtonHovered = (mouseX >= backX && mouseX < backX + backWidth && mouseY >= backY && mouseY < backY + 10);
/*     */     
/* 251 */     if (this.backButtonHovered) GuideSounds.playHover("skindex_back");
/*     */     
/* 253 */     int backColor = this.backButtonHovered ? GuideColors.TEXT_WHITE : GuideColors.TEXT_DIM;
/* 254 */     ctx.method_51433(textRenderer, backLabel, backX, backY, backColor, true);
/*     */ 
/*     */     
/* 257 */     if (detail != null) {
/* 258 */       int titleX = backX + backWidth + 8;
/* 259 */       ctx.method_51433(textRenderer, detail.name(), titleX, backY, GuideColors.TEXT_WHITE, true);
/*     */ 
/*     */       
/* 262 */       if (!detail.rewardDescriptions().isEmpty()) {
/* 263 */         String completion = "" + detail.completedCount() + "/" + detail.completedCount();
/* 264 */         boolean complete = (detail.completedCount() >= detail.totalCount());
/* 265 */         int compColor = complete ? GuideColors.ACCENT_EMERALD : GuideColors.TEXT_DIM;
/* 266 */         int compX = this.x + this.width - textRenderer.method_1727(completion) - 8;
/* 267 */         ctx.method_51433(textRenderer, completion, compX, backY, compColor, true);
/*     */       } else {
/*     */         
/* 270 */         String skinCount = "" + detail.totalCount() + " Skins";
/* 271 */         int countX = this.x + this.width - textRenderer.method_1727(skinCount) - 8;
/* 272 */         ctx.method_51433(textRenderer, skinCount, countX, backY, GuideColors.TEXT_DIM, true);
/*     */       } 
/*     */ 
/*     */       
/* 276 */       int accentColor = parseColor(detail.color(), 255);
/* 277 */       ctx.method_25294(this.x, headerY, this.x + 3, headerY + 16, accentColor);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int calculateRewardSectionHeight(SkinDexDetailInfo detail) {
/* 285 */     int rewardLines = detail.rewardDescriptions().isEmpty() ? 0 : detail.rewardDescriptions().size();
/* 286 */     return 11 + rewardLines * 11 + 5 + 14 + 8;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int renderRewards(class_332 ctx, class_327 textRenderer, SkinDexDetailInfo detail, int startY, int mouseX, int mouseY) {
/*     */     int btnBg, btnBorder, btnTextColor;
/*     */     String btnText;
/* 295 */     int sectionX = this.x + 8;
/* 296 */     int sectionWidth = this.width - 16;
/* 297 */     int curY = startY;
/*     */     
/* 299 */     boolean completed = (detail.completedCount() >= detail.totalCount());
/* 300 */     boolean claimed = detail.claimed();
/*     */ 
/*     */     
/* 303 */     int panelHeight = calculateRewardSectionHeight(detail);
/* 304 */     ctx.method_25294(sectionX, curY - 2, sectionX + sectionWidth, curY + panelHeight - 2, 
/* 305 */         GuideColors.color(18, 22, 32, 255));
/* 306 */     ctx.method_49601(sectionX, curY - 2, sectionWidth, panelHeight, 
/* 307 */         completed ? GuideColors.color(46, 204, 113, 80) : GuideColors.BORDER_DIM);
/*     */ 
/*     */     
/* 310 */     ctx.method_51433(textRenderer, "Completion Rewards", sectionX + 4, curY, GuideColors.TEXT_WHITE, true);
/* 311 */     curY += 11;
/*     */ 
/*     */     
/* 314 */     for (String reward : detail.rewardDescriptions()) {
/* 315 */       ctx.method_51433(textRenderer, " ◆ " + reward, sectionX + 6, curY, GuideColors.TEXT_PRIMARY, true);
/* 316 */       curY += 11;
/*     */     } 
/*     */     
/* 319 */     curY += 5;
/*     */ 
/*     */     
/* 322 */     int btnX = this.x + (this.width - 130) / 2;
/* 323 */     int btnY = curY;
/*     */     
/* 325 */     this.claimButtonHovered = (mouseX >= btnX && mouseX < btnX + 130 && mouseY >= btnY && mouseY < btnY + 14 && completed && !claimed);
/*     */ 
/*     */ 
/*     */     
/* 329 */     if (this.claimButtonHovered) GuideSounds.playHover("skindex_claim");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 336 */     if (claimed) {
/* 337 */       btnBg = GuideColors.color(40, 50, 40, 255);
/* 338 */       btnBorder = GuideColors.color(60, 80, 60, 255);
/* 339 */       btnTextColor = GuideColors.TEXT_DIM;
/* 340 */       btnText = "Reward Claimed ✔";
/* 341 */     } else if (completed) {
/* 342 */       btnBg = this.claimButtonHovered ? GuideColors.color(30, 120, 70, 255) : GuideColors.ACCENT_DARK_GREEN;
/* 343 */       btnBorder = this.claimButtonHovered ? GuideColors.ACCENT_EMERALD : GuideColors.color(60, 140, 90, 255);
/* 344 */       btnTextColor = GuideColors.TEXT_WHITE;
/* 345 */       btnText = "Claim Reward";
/*     */     } else {
/* 347 */       btnBg = GuideColors.color(35, 40, 50, 255);
/* 348 */       btnBorder = GuideColors.BORDER_DIM;
/* 349 */       btnTextColor = GuideColors.TEXT_DIM;
/* 350 */       btnText = "Collect All Skins to Claim";
/*     */     } 
/*     */     
/* 353 */     ctx.method_25294(btnX, btnY, btnX + 130, btnY + 14, btnBg);
/* 354 */     ctx.method_49601(btnX, btnY, 130, 14, btnBorder);
/* 355 */     ctx.method_25300(textRenderer, btnText, btnX + 65, btnY + 3, btnTextColor);
/*     */ 
/*     */     
/* 358 */     return curY + 14 + 2;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderSkinCard(class_332 ctx, class_327 textRenderer, SkinDexDetailInfo detail, SkinDexDetailInfo.SkinEntry skin, int cardX, int cardY, boolean hovered, int index, float delta) {
/* 364 */     boolean isShiny = this.shinyToggles.contains(Integer.valueOf(index));
/* 365 */     boolean hasAuras = !detail.auras().isEmpty();
/*     */ 
/*     */     
/* 368 */     int auraIdx = ((Integer)this.selectedAuraIndex.getOrDefault(Integer.valueOf(index), Integer.valueOf(-1))).intValue();
/* 369 */     String activeAuraAspect = null;
/* 370 */     if (auraIdx >= 0 && auraIdx < detail.auras().size()) {
/* 371 */       activeAuraAspect = ((SkinDexDetailInfo.AuraEntry)detail.auras().get(auraIdx)).aspect();
/*     */     }
/*     */ 
/*     */     
/* 375 */     int bgColor = hovered ? GuideColors.CARD_HOVER_BG : GuideColors.CARD_BG;
/* 376 */     ctx.method_25294(cardX, cardY, cardX + 88, cardY + 66, bgColor);
/*     */ 
/*     */     
/* 379 */     int borderColor = skin.unlocked() ? GuideColors.ACCENT_EMERALD : GuideColors.BORDER_DIM;
/* 380 */     ctx.method_49601(cardX, cardY, 88, 66, borderColor);
/*     */ 
/*     */     
/* 383 */     int modelX = cardX + 26;
/* 384 */     int modelY = cardY + 2;
/* 385 */     renderPokemonModel(ctx, skin.pokemonId(), skin.aspect(), activeAuraAspect, modelX, modelY, 36, index, delta, isShiny);
/*     */ 
/*     */ 
/*     */     
/* 389 */     int nameY = cardY + 66 - 12;
/* 390 */     int nameColor = skin.unlocked() ? GuideColors.TEXT_PRIMARY : GuideColors.TEXT_DIM;
/* 391 */     String skinName = skin.name();
/* 392 */     if (textRenderer.method_1727(skinName) > 82) {
/* 393 */       while (textRenderer.method_1727(skinName + "..") > 82 && skinName.length() > 3) {
/* 394 */         skinName = skinName.substring(0, skinName.length() - 1);
/*     */       }
/* 396 */       skinName = skinName + "..";
/*     */     } 
/* 398 */     ctx.method_25300(textRenderer, skinName, cardX + 44, nameY, nameColor);
/*     */ 
/*     */     
/* 401 */     if (!skin.unlocked()) {
/* 402 */       ctx.method_25294(cardX + 1, cardY + 1, cardX + 88 - 1, cardY + 66 - 1, 
/* 403 */           GuideColors.color(140, 20, 20, 70));
/*     */     }
/*     */ 
/*     */     
/* 407 */     if (skin.unlocked()) {
/* 408 */       ctx.method_51433(textRenderer, "✔", cardX + 88 - 10, cardY + 2, GuideColors.ACCENT_EMERALD, true);
/*     */     }
/*     */ 
/*     */     
/* 412 */     int shinyX = cardX + 2;
/* 413 */     int shinyY = cardY + 2;
/* 414 */     boolean shinyHovered = (this.hoveredShinyIndex == index);
/*     */     
/* 416 */     int shinyBg = isShiny ? GuideColors.color(200, 180, 50, 200) : (shinyHovered ? GuideColors.color(60, 60, 50, 200) : GuideColors.color(35, 35, 35, 160));
/* 417 */     ctx.method_25294(shinyX, shinyY, shinyX + 9, shinyY + 9, shinyBg);
/* 418 */     int starColor = isShiny ? GuideColors.color(255, 255, 100, 255) : (shinyHovered ? GuideColors.color(180, 170, 100, 255) : GuideColors.color(100, 100, 80, 200));
/* 419 */     ctx.method_51433(textRenderer, "★", shinyX + 1, shinyY + 1, starColor, false);
/*     */ 
/*     */     
/* 422 */     if (hasAuras) {
/* 423 */       int auraX = cardX + 2 + 9 + 2;
/* 424 */       int auraY = cardY + 2;
/* 425 */       boolean auraHovered = (this.hoveredAuraButtonIndex == index);
/* 426 */       boolean auraActive = (auraIdx >= 0);
/* 427 */       boolean isDropdownOpen = (this.auraDropdownCardIndex == index);
/*     */ 
/*     */       
/* 430 */       int auraBg = auraActive ? GuideColors.color(128, 0, 128, 200) : ((auraHovered || isDropdownOpen) ? GuideColors.color(60, 30, 60, 200) : GuideColors.color(35, 35, 35, 160));
/* 431 */       ctx.method_25294(auraX, auraY, auraX + 9, auraY + 9, auraBg);
/*     */       
/* 433 */       int diamondColor = auraActive ? GuideColors.color(220, 130, 255, 255) : ((auraHovered || isDropdownOpen) ? GuideColors.color(160, 100, 180, 255) : GuideColors.color(100, 80, 100, 200));
/* 434 */       String diamondChar = "◆";
/* 435 */       int diamondWidth = textRenderer.method_1727(diamondChar);
/* 436 */       int diamondDrawX = auraX + (9 - diamondWidth) / 2;
/* 437 */       int diamondDrawY = auraY + 1;
/* 438 */       ctx.method_51433(textRenderer, diamondChar, diamondDrawX, diamondDrawY, diamondColor, false);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void cacheDropdownPosition(SkinDexDetailInfo detail, int gridLeft, int gridStartY) {
/* 447 */     int cardIndex = this.auraDropdownCardIndex;
/* 448 */     int col = cardIndex % 4;
/* 449 */     int row = cardIndex / 4;
/* 450 */     int cardX = gridLeft + col * 93;
/* 451 */     int cardY = gridStartY + row * 71;
/*     */     
/* 453 */     int auraButtonX = cardX + 2 + 9 + 2;
/* 454 */     int auraButtonY = cardY + 2 + 9;
/*     */     
/* 456 */     int itemCount = 1 + detail.auras().size();
/* 457 */     int ddH = 4 + itemCount * 12 + 2;
/* 458 */     int ddX = auraButtonX;
/* 459 */     int ddY = auraButtonY + 2;
/*     */ 
/*     */     
/* 462 */     class_327 textRenderer = (class_310.method_1551()).field_1772;
/* 463 */     int maxTextWidth = textRenderer.method_1727("None");
/* 464 */     for (SkinDexDetailInfo.AuraEntry aura : detail.auras()) {
/* 465 */       String label = aura.name();
/* 466 */       if (!aura.unlocked()) {
/* 467 */         label = "🔒 " + label;
/*     */       }
/* 469 */       maxTextWidth = Math.max(maxTextWidth, textRenderer.method_1727(label));
/*     */     } 
/* 471 */     int ddW = Math.max(80, maxTextWidth + 12);
/*     */     
/* 473 */     this.dropdownX = ddX;
/* 474 */     this.dropdownY = ddY;
/* 475 */     this.dropdownHeight = ddH;
/* 476 */     this.dropdownWidth = ddW;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderAuraDropdown(class_332 ctx, class_327 textRenderer, SkinDexDetailInfo detail, int gridLeft, int gridStartY, int mouseX, int mouseY) {
/* 486 */     int ddX = this.dropdownX;
/* 487 */     int ddY = this.dropdownY;
/* 488 */     int ddH = this.dropdownHeight;
/* 489 */     int ddW = this.dropdownWidth;
/* 490 */     int cardIndex = this.auraDropdownCardIndex;
/* 491 */     int itemCount = 1 + detail.auras().size();
/*     */ 
/*     */     
/* 494 */     ctx.method_51448().method_22903();
/* 495 */     ctx.method_51448().method_46416(0.0F, 0.0F, 500.0F);
/*     */ 
/*     */     
/* 498 */     ctx.method_25294(ddX, ddY, ddX + ddW, ddY + ddH, GuideColors.color(10, 12, 20, 245));
/* 499 */     ctx.method_49601(ddX, ddY, ddW, ddH, GuideColors.color(128, 80, 180, 200));
/*     */     
/* 501 */     int currentSelection = ((Integer)this.selectedAuraIndex.getOrDefault(Integer.valueOf(cardIndex), Integer.valueOf(-1))).intValue();
/*     */     
/* 503 */     for (int i = 0; i < itemCount; i++) {
/* 504 */       String label; int textColor, itemY = ddY + 2 + i * 12;
/* 505 */       boolean itemHovered = (mouseX >= ddX && mouseX < ddX + ddW && mouseY >= itemY && mouseY < itemY + 12);
/*     */ 
/*     */       
/* 508 */       if (itemHovered) {
/* 509 */         this.hoveredAuraOptionIndex = i;
/*     */       }
/*     */ 
/*     */       
/* 513 */       int auraIndex = i - 1;
/* 514 */       boolean isSelected = (auraIndex == currentSelection);
/*     */ 
/*     */       
/* 517 */       if (isSelected) {
/* 518 */         ctx.method_25294(ddX + 1, itemY, ddX + ddW - 1, itemY + 12, 
/* 519 */             GuideColors.color(80, 40, 100, 150));
/* 520 */       } else if (itemHovered) {
/* 521 */         ctx.method_25294(ddX + 1, itemY, ddX + ddW - 1, itemY + 12, 
/* 522 */             GuideColors.color(50, 30, 60, 150));
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 527 */       if (i == 0) {
/*     */         
/* 529 */         label = "None";
/* 530 */         textColor = isSelected ? GuideColors.TEXT_WHITE : (itemHovered ? GuideColors.TEXT_PRIMARY : GuideColors.TEXT_DIM);
/*     */       } else {
/* 532 */         SkinDexDetailInfo.AuraEntry aura = detail.auras().get(auraIndex);
/* 533 */         label = aura.name();
/* 534 */         if (!aura.unlocked()) {
/*     */           
/* 536 */           label = "🔒 " + label;
/* 537 */           textColor = GuideColors.color(100, 60, 60, 200);
/*     */         } else {
/*     */           
/* 540 */           textColor = isSelected ? GuideColors.color(220, 130, 255, 255) : (itemHovered ? GuideColors.TEXT_PRIMARY : GuideColors.TEXT_DIM);
/*     */         } 
/*     */       } 
/*     */       
/* 544 */       ctx.method_51433(textRenderer, label, ddX + 4, itemY + 2, textColor, true);
/*     */     } 
/*     */     
/* 547 */     ctx.method_51448().method_22909();
/*     */   }
/*     */ 
/*     */   
/*     */   private void renderPokemonModel(class_332 ctx, String pokemonId, String aspect, String auraAspect, int sx, int sy, int ss, int stateIndex, float delta, boolean shiny) {
/* 552 */     if (pokemonId == null || pokemonId.isEmpty())
/*     */       return; 
/* 554 */     if (!PokemonRenderHelper.isAvailable()) {
/* 555 */       ctx.method_25294(sx, sy, sx + ss, sy + ss, GuideColors.color(20, 25, 30, 200));
/*     */       
/*     */       return;
/*     */     } 
/*     */     try {
/* 560 */       class_2960 speciesId = resolveSpeciesId(pokemonId);
/*     */       
/* 562 */       FloatingState state = this.modelStates.computeIfAbsent(Integer.valueOf(stateIndex), k -> new FloatingState());
/* 563 */       Set<String> aspects = new HashSet<>();
/*     */ 
/*     */       
/* 566 */       if (aspect != null && !aspect.isEmpty())
/* 567 */         for (String a : aspect.split(" ")) {
/* 568 */           if (!a.isEmpty()) aspects.add(a);
/*     */         
/*     */         }  
/* 571 */       if (shiny) aspects.add("shiny");
/*     */ 
/*     */       
/* 574 */       if (auraAspect != null && aspect != null && !aspect.isEmpty()) {
/* 575 */         String[] parts = aspect.split(" ");
/* 576 */         aspects.remove(parts[parts.length - 1]);
/* 577 */         aspects.add(auraAspect);
/*     */       } 
/*     */       
/* 580 */       state.setCurrentAspects(aspects);
/*     */       
/* 582 */       class_4587 mat = ctx.method_51448();
/* 583 */       mat.method_22903();
/* 584 */       mat.method_22904(sx + ss / 2.0D, sy + 2.0D, 0.0D);
/* 585 */       float scale = ss / 10.0F;
/* 586 */       mat.method_22905(scale, scale, 1.0F);
/*     */       
/* 588 */       Quaternionf rot = new Quaternionf();
/* 589 */       rot.rotationXYZ((float)Math.toRadians(13.0D), (float)Math.toRadians(35.0D), 0.0F);
/*     */       
/* 591 */       PokemonRenderHelper.draw(speciesId, mat, rot, state, delta);
/* 592 */       mat.method_22909();
/* 593 */     } catch (Exception e) {
/* 594 */       LOGGER.error("[SkinDex] Detail model render failed for pokemonId='{}', aspect='{}', auraAspect='{}', index={}: {}", new Object[] { pokemonId, aspect, auraAspect, 
/* 595 */             Integer.valueOf(stateIndex), e.getMessage(), e });
/* 596 */       ctx.method_25294(sx, sy, sx + ss, sy + ss, GuideColors.color(20, 25, 30, 200));
/*     */     } 
/*     */   }
/*     */   
/*     */   private class_2960 resolveSpeciesId(String slug) {
/* 601 */     class_2960 id, cached = this.resolvedSpeciesIds.get(slug);
/* 602 */     if (cached != null) return cached;
/*     */     
/* 604 */     Species species = PokemonSpecies.getByName(slug);
/*     */     
/* 606 */     if (species != null) {
/* 607 */       id = species.getResourceIdentifier();
/*     */     } else {
/* 609 */       id = class_2960.method_60655("cobblemon", slug);
/*     */     } 
/* 611 */     this.resolvedSpeciesIds.put(slug, id);
/* 612 */     return id;
/*     */   }
/*     */   
/*     */   private int parseColor(String hex, int alpha) {
/*     */     try {
/* 617 */       if (hex.startsWith("#")) hex = hex.substring(1); 
/* 618 */       int rgb = Integer.parseInt(hex, 16);
/* 619 */       return alpha << 24 | rgb;
/* 620 */     } catch (Exception e) {
/* 621 */       return GuideColors.TEXT_WHITE;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean mouseClicked(double mouseX, double mouseY, int button) {
/* 629 */     if (button == 0 && this.auraDropdownCardIndex >= 0) {
/* 630 */       SkinDexDetailInfo detail = SkinDexData.getInstance().getCurrentDetail();
/* 631 */       if (detail != null && !detail.auras().isEmpty()) {
/*     */         
/* 633 */         if (mouseX >= this.dropdownX && mouseX < (this.dropdownX + this.dropdownWidth) && mouseY >= this.dropdownY && mouseY < (this.dropdownY + this.dropdownHeight)) {
/*     */           
/* 635 */           if (this.hoveredAuraOptionIndex >= 0) {
/* 636 */             int auraIndex = this.hoveredAuraOptionIndex - 1;
/*     */             
/* 638 */             if (auraIndex < 0) {
/*     */               
/* 640 */               this.selectedAuraIndex.remove(Integer.valueOf(this.auraDropdownCardIndex));
/* 641 */               this.modelStates.remove(Integer.valueOf(this.auraDropdownCardIndex));
/* 642 */               GuideSounds.playClick();
/* 643 */             } else if (auraIndex < detail.auras().size() && ((SkinDexDetailInfo.AuraEntry)detail.auras().get(auraIndex)).unlocked()) {
/*     */               
/* 645 */               this.selectedAuraIndex.put(Integer.valueOf(this.auraDropdownCardIndex), Integer.valueOf(auraIndex));
/* 646 */               this.modelStates.remove(Integer.valueOf(this.auraDropdownCardIndex));
/* 647 */               GuideSounds.playClick();
/*     */             } 
/*     */           } 
/* 650 */           this.auraDropdownCardIndex = -1;
/* 651 */           return true;
/*     */         } 
/*     */         
/* 654 */         this.auraDropdownCardIndex = -1;
/* 655 */         return true;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 660 */     if (button == 0 && this.hoveredAuraButtonIndex >= 0) {
/* 661 */       SkinDexDetailInfo detail = SkinDexData.getInstance().getCurrentDetail();
/* 662 */       if (detail != null && !detail.auras().isEmpty()) {
/* 663 */         GuideSounds.playClick();
/* 664 */         if (this.auraDropdownCardIndex == this.hoveredAuraButtonIndex) {
/* 665 */           this.auraDropdownCardIndex = -1;
/*     */         } else {
/* 667 */           this.auraDropdownCardIndex = this.hoveredAuraButtonIndex;
/*     */         } 
/* 669 */         return true;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 674 */     if (button == 0 && this.backButtonHovered && this.onBack != null) {
/* 675 */       this.onBack.run();
/* 676 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 680 */     if (button == 0 && this.claimButtonHovered) {
/* 681 */       SkinDexDetailInfo detail = SkinDexData.getInstance().getCurrentDetail();
/* 682 */       if (detail != null) {
/* 683 */         GuideSounds.playClick();
/* 684 */         SkinDexNetwork.claimReward(detail.id());
/* 685 */         SkinDexData.getInstance().setDetailLoading(true);
/* 686 */         SkinDexNetwork.requestSkinDexData("detail", detail.id());
/* 687 */         return true;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 692 */     if (button == 0 && this.hoveredShinyIndex >= 0) {
/* 693 */       GuideSounds.playClick();
/* 694 */       if (this.shinyToggles.contains(Integer.valueOf(this.hoveredShinyIndex))) {
/* 695 */         this.shinyToggles.remove(Integer.valueOf(this.hoveredShinyIndex));
/*     */       } else {
/* 697 */         this.shinyToggles.add(Integer.valueOf(this.hoveredShinyIndex));
/*     */       } 
/*     */       
/* 700 */       this.modelStates.remove(Integer.valueOf(this.hoveredShinyIndex));
/* 701 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 705 */     if (this.scrollArea.handleMouseClicked(mouseX, mouseY, button)) return true;
/*     */ 
/*     */     
/* 708 */     if (button == 0 && this.hoveredSkinIndex >= 0 && this.modelViewer != null) {
/* 709 */       SkinDexDetailInfo detail = SkinDexData.getInstance().getCurrentDetail();
/* 710 */       if (detail != null && this.hoveredSkinIndex < detail.skins().size()) {
/* 711 */         SkinDexDetailInfo.SkinEntry skin = detail.skins().get(this.hoveredSkinIndex);
/* 712 */         if (skin.pokemonId() != null && !skin.pokemonId().isEmpty()) {
/* 713 */           GuideSounds.playNavigate();
/* 714 */           class_2960 speciesId = resolveSpeciesId(skin.pokemonId());
/* 715 */           FloatingState previewState = new FloatingState();
/* 716 */           Set<String> aspects = new HashSet<>();
/*     */           
/* 718 */           if (skin.aspect() != null && !skin.aspect().isEmpty()) {
/* 719 */             for (String a : skin.aspect().split(" ")) {
/* 720 */               if (!a.isEmpty()) aspects.add(a);
/*     */             
/*     */             } 
/*     */           }
/* 724 */           if (this.shinyToggles.contains(Integer.valueOf(this.hoveredSkinIndex))) aspects.add("shiny");
/*     */ 
/*     */           
/* 727 */           int auraIdx = ((Integer)this.selectedAuraIndex.getOrDefault(Integer.valueOf(this.hoveredSkinIndex), Integer.valueOf(-1))).intValue();
/* 728 */           if (auraIdx >= 0 && auraIdx < detail.auras().size()) {
/* 729 */             String auraAspect = ((SkinDexDetailInfo.AuraEntry)detail.auras().get(auraIdx)).aspect();
/* 730 */             String[] parts = skin.aspect().split(" ");
/* 731 */             aspects.remove(parts[parts.length - 1]);
/* 732 */             aspects.add(auraAspect);
/*     */           } 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 738 */           String[] aspectParts = skin.aspect().split(" ");
/* 739 */           String themeAspect = aspectParts[aspectParts.length - 1];
/* 740 */           this.modelViewer.open(speciesId, previewState, aspects, detail
/* 741 */               .auras(), themeAspect, auraIdx, skin.pokemonId(), () -> { 
/* 742 */               }); return true;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 747 */     return false;
/*     */   }
/*     */   
/*     */   public boolean mouseScrolled(double mouseX, double mouseY, double h, double v) {
/* 751 */     return this.scrollArea.handleScroll(mouseX, mouseY, v);
/*     */   }
/*     */   
/*     */   public boolean mouseDragged(double mouseX, double mouseY, int button) {
/* 755 */     return this.scrollArea.handleMouseDragged(mouseX, mouseY, button);
/*     */   }
/*     */   
/*     */   public boolean mouseReleased(double mouseX, double mouseY, int button) {
/* 759 */     return this.scrollArea.handleMouseReleased(mouseX, mouseY, button);
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\skindex\screen\SkinDexDetailView.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */