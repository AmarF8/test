/*     */ package com.atlas.common.fabric.guide.screen;
/*     */ 
/*     */ import com.atlas.common.fabric.guide.data.GuideData;
/*     */ import com.atlas.common.fabric.guide.network.GuideNetwork;
/*     */ import com.atlas.common.fabric.guide.screen.views.AskAiView;
/*     */ import com.atlas.common.fabric.guide.screen.views.EvolutionView;
/*     */ import com.atlas.common.fabric.guide.screen.views.LandingView;
/*     */ import com.atlas.common.fabric.guide.screen.views.MoveDetailView;
/*     */ import com.atlas.common.fabric.guide.screen.views.MoveListView;
/*     */ import com.atlas.common.fabric.guide.screen.views.PokedexListView;
/*     */ import com.atlas.common.fabric.guide.screen.views.PokemonDetailView;
/*     */ import com.atlas.common.fabric.guide.screen.views.RideableListView;
/*     */ import com.atlas.common.fabric.guide.screen.views.ServerGuideView;
/*     */ import com.atlas.common.fabric.guide.screen.views.SpawnListView;
/*     */ import com.atlas.common.fabric.starterguide.PlayerStarterGuideData;
/*     */ import net.minecraft.class_2561;
/*     */ import net.minecraft.class_332;
/*     */ import net.minecraft.class_437;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GuideScreen
/*     */   extends class_437
/*     */ {
/*     */   private static final int GUI_WIDTH = 480;
/*     */   private static final int GUI_HEIGHT = 340;
/*     */   private static final int HEADER_HEIGHT = 22;
/*     */   private static final int TAB_HEIGHT = 16;
/*     */   private int guiLeft;
/*     */   private int guiTop;
/*  33 */   private static final String[] TAB_NAMES = new String[] { "Pokédex", "Spawns", "Evolutions", "Moves", "Server Guide", "Rideable", "Ask AI" };
/*     */ 
/*     */   
/*  36 */   private int activeTab = 0;
/*  37 */   private int hoveredTab = -1;
/*  38 */   private final int[] tabXPositions = new int[TAB_NAMES.length];
/*  39 */   private final int[] tabWidths = new int[TAB_NAMES.length];
/*     */   
/*     */   private final PokedexListView pokedexListView;
/*     */   
/*     */   private final PokemonDetailView pokemonDetailView;
/*     */   
/*     */   private final SpawnListView spawnListView;
/*     */   
/*     */   private final EvolutionView evolutionView;
/*     */   
/*     */   private final MoveListView moveListView;
/*     */   private final ServerGuideView serverGuideView;
/*     */   private final RideableListView rideableListView;
/*     */   private final AskAiView askAiView;
/*     */   private final MoveDetailView moveDetailView;
/*     */   private final LandingView landingView;
/*     */   private boolean showingDetail = false;
/*     */   private boolean showingMoveDetail = false;
/*     */   private boolean showLanding = true;
/*     */   private boolean homeButtonHovered = false;
/*     */   
/*     */   public GuideScreen() {
/*  61 */     super((class_2561)class_2561.method_43471("screen.atlas_guide.title"));
/*     */ 
/*     */     
/*  64 */     this.pokedexListView = new PokedexListView();
/*  65 */     this.pokemonDetailView = new PokemonDetailView();
/*  66 */     this.spawnListView = new SpawnListView();
/*  67 */     this.evolutionView = new EvolutionView();
/*  68 */     this.moveListView = new MoveListView();
/*  69 */     this.serverGuideView = new ServerGuideView();
/*  70 */     this.rideableListView = new RideableListView();
/*  71 */     this.askAiView = new AskAiView();
/*  72 */     this.moveDetailView = new MoveDetailView();
/*  73 */     this.landingView = new LandingView();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void method_25426() {
/*  80 */     super.method_25426();
/*  81 */     this.guiLeft = (this.field_22789 - 480) / 2;
/*  82 */     this.guiTop = (this.field_22790 - 340) / 2;
/*     */ 
/*     */     
/*  85 */     int contentX = this.guiLeft + 2;
/*  86 */     int contentY = this.guiTop + 22 + 16 + 2;
/*  87 */     int contentW = 476;
/*  88 */     int contentH = 298;
/*     */ 
/*     */     
/*  91 */     this.pokedexListView.init(contentX, contentY, contentW, contentH, this::onPokemonSelected);
/*  92 */     this.pokemonDetailView.init(contentX, contentY, contentW, contentH, this::onBackToList);
/*  93 */     this.pokemonDetailView.setScreenDimensions(this.field_22789, this.field_22790);
/*  94 */     this.pokemonDetailView.setOnMoveSelected(this::onMoveSelectedFromDetail);
/*  95 */     this.pokemonDetailView.setOnTypeSelected(this::onTypeSelectedFromDetail);
/*     */ 
/*     */     
/*  98 */     this.spawnListView.init(contentX, contentY, contentW, contentH, this::onPokemonSelectedFromTab);
/*  99 */     this.evolutionView.init(contentX, contentY, contentW, contentH, this::onPokemonSelectedFromTab);
/* 100 */     this.moveListView.init(contentX, contentY, contentW, contentH, this::onMoveClickedFromList);
/* 101 */     this.serverGuideView.init(contentX, contentY, contentW, contentH);
/* 102 */     this.rideableListView.init(contentX, contentY, contentW, contentH, this::onPokemonSelectedFromTab);
/* 103 */     this.askAiView.init(contentX, contentY, contentW, contentH, this::onSearchResultSelected);
/* 104 */     this.moveDetailView.init(contentX, contentY, contentW, contentH, this::onPokemonSelectedFromMoveDetail, this::onBackFromMoveDetail);
/*     */ 
/*     */     
/* 107 */     int landingY = this.guiTop + 22 + 1;
/* 108 */     int landingH = 316;
/* 109 */     this.landingView.init(this.guiLeft + 2, landingY, 476, landingH, this::onLandingCategorySelected);
/*     */ 
/*     */     
/* 112 */     computeTabLayout();
/*     */   }
/*     */ 
/*     */   
/*     */   public void method_25419() {
/* 117 */     GuideData.getInstance().clearAll();
/* 118 */     super.method_25419();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean method_25421() {
/* 123 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void method_25394(class_332 ctx, int mouseX, int mouseY, float delta) {
/* 131 */     ctx.method_25294(0, 0, this.field_22789, this.field_22790, GuideColors.color(0, 0, 0, 120));
/*     */ 
/*     */     
/* 134 */     drawPanel(ctx);
/* 135 */     drawHeader(ctx, mouseX, mouseY);
/*     */     
/* 137 */     if (this.showLanding) {
/*     */       
/* 139 */       this.landingView.render(ctx, this.field_22793, mouseX, mouseY, delta);
/*     */     } else {
/*     */       
/* 142 */       drawTabs(ctx, mouseX, mouseY);
/* 143 */       drawContent(ctx, mouseX, mouseY, delta);
/*     */     } 
/*     */     
/* 146 */     super.method_25394(ctx, mouseX, mouseY, delta);
/*     */ 
/*     */     
/* 149 */     if (!this.showLanding && this.activeTab == 0 && this.showingDetail && this.pokemonDetailView.isModelViewerOpen()) {
/* 150 */       this.pokemonDetailView.renderModelViewerOverlay(ctx, this.field_22793, mouseX, mouseY, delta);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void method_25420(class_332 context, int mouseX, int mouseY, float delta) {}
/*     */ 
/*     */   
/*     */   private void drawPanel(class_332 ctx) {
/* 159 */     ctx.method_25294(this.guiLeft - 1, this.guiTop - 1, this.guiLeft + 480 + 1, this.guiTop + 340 + 1, GuideColors.BORDER);
/*     */ 
/*     */     
/* 162 */     ctx.method_25294(this.guiLeft, this.guiTop, this.guiLeft + 480, this.guiTop + 340, GuideColors.PANEL_BG);
/*     */   }
/*     */   
/*     */   private void drawHeader(class_332 ctx, int mouseX, int mouseY) {
/* 166 */     int hx = this.guiLeft;
/* 167 */     int hy = this.guiTop;
/* 168 */     int hw = 480;
/* 169 */     int hh = 22;
/*     */ 
/*     */     
/* 172 */     ctx.method_25294(hx, hy, hx + hw, hy + hh, GuideColors.HEADER_BG);
/*     */ 
/*     */     
/* 175 */     ctx.method_51433(this.field_22793, GuideData.getInstance().getNetworkName() + " Guide", hx + 8, hy + 7, GuideColors.TEXT_ACCENT, true);
/*     */ 
/*     */     
/* 178 */     this.homeButtonHovered = false;
/* 179 */     if (!this.showLanding) {
/* 180 */       String homeLabel = "Home";
/* 181 */       int homeBtnW = this.field_22793.method_1727(homeLabel) + 8;
/* 182 */       int homeBtnH = 14;
/* 183 */       int homeBtnX = hx + hw - homeBtnW - 6;
/* 184 */       int homeBtnY = hy + (hh - homeBtnH) / 2;
/* 185 */       this.homeButtonHovered = (mouseX >= homeBtnX && mouseX < homeBtnX + homeBtnW && mouseY >= homeBtnY && mouseY < homeBtnY + homeBtnH);
/*     */       
/* 187 */       if (this.homeButtonHovered) GuideSounds.playHover("guide_home"); 
/* 188 */       int homeBg = this.homeButtonHovered ? GuideColors.BUTTON_HOVER : GuideColors.BUTTON_BG;
/* 189 */       ctx.method_25294(homeBtnX, homeBtnY, homeBtnX + homeBtnW, homeBtnY + homeBtnH, homeBg);
/* 190 */       ctx.method_49601(homeBtnX, homeBtnY, homeBtnW, homeBtnH, 
/* 191 */           this.homeButtonHovered ? GuideColors.BUTTON_BORDER_HOVER : GuideColors.BUTTON_BORDER);
/* 192 */       int homeTextColor = this.homeButtonHovered ? GuideColors.TEXT_WHITE : GuideColors.TEXT_DIM;
/* 193 */       ctx.method_51433(this.field_22793, homeLabel, homeBtnX + 4, homeBtnY + 3, homeTextColor, true);
/*     */     } 
/*     */ 
/*     */     
/* 197 */     ctx.method_25294(hx, hy + hh - 1, hx + hw, hy + hh, GuideColors.ACCENT_EMERALD);
/*     */   }
/*     */   
/*     */   private void drawTabs(class_332 ctx, int mouseX, int mouseY) {
/* 201 */     this.hoveredTab = -1;
/* 202 */     int tabY = this.guiTop + 22;
/*     */ 
/*     */     
/* 205 */     int guideHighlightTab = getGuideHighlightTabIndex();
/*     */ 
/*     */     
/* 208 */     ctx.method_25294(this.guiLeft, tabY, this.guiLeft + 480, tabY + 16, GuideColors.TAB_INACTIVE_BG);
/*     */     
/* 210 */     for (int i = 0; i < TAB_NAMES.length; i++) {
/* 211 */       int bg, textColor, tx = this.tabXPositions[i];
/* 212 */       int tw = this.tabWidths[i];
/* 213 */       boolean active = (i == this.activeTab);
/* 214 */       boolean hovered = (mouseX >= tx && mouseX < tx + tw && mouseY >= tabY && mouseY < tabY + 16);
/* 215 */       if (hovered) {
/* 216 */         this.hoveredTab = i;
/* 217 */         if (!active) GuideSounds.playHover("guide_tab:" + i);
/*     */       
/*     */       } 
/*     */       
/* 221 */       boolean highlight = (i == guideHighlightTab && !active);
/* 222 */       if (highlight) {
/* 223 */         float pulse = (float)(Math.sin(System.currentTimeMillis() / 400.0D) * 0.5D + 0.5D);
/* 224 */         int outerAlpha = (int)(40.0F + 60.0F * pulse);
/* 225 */         int innerAlpha = (int)(80.0F + 120.0F * pulse);
/* 226 */         int outerGlow = GuideColors.color(255, 170, 20, outerAlpha);
/* 227 */         int innerGlow = GuideColors.color(255, 195, 50, innerAlpha);
/* 228 */         ctx.method_25294(tx - 2, tabY - 2, tx + tw + 2, tabY + 16 + 2, outerGlow);
/* 229 */         ctx.method_25294(tx - 1, tabY - 1, tx + tw + 1, tabY + 16 + 1, innerGlow);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 234 */       if (active) {
/* 235 */         bg = GuideColors.TAB_ACTIVE_BG;
/* 236 */       } else if (hovered) {
/* 237 */         bg = GuideColors.TAB_HOVER_BG;
/*     */       } else {
/* 239 */         bg = GuideColors.TAB_INACTIVE_BG;
/*     */       } 
/* 241 */       ctx.method_25294(tx, tabY, tx + tw, tabY + 16, bg);
/*     */ 
/*     */       
/* 244 */       if (active) {
/* 245 */         ctx.method_25294(tx, tabY + 16 - 2, tx + tw, tabY + 16, GuideColors.ACCENT_EMERALD);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 250 */       if (highlight && !hovered) {
/* 251 */         textColor = GuideColors.color(255, 210, 80, 255);
/* 252 */       } else if (active) {
/* 253 */         textColor = GuideColors.TEXT_ACCENT;
/* 254 */       } else if (hovered) {
/* 255 */         textColor = GuideColors.TEXT_PRIMARY;
/*     */       } else {
/* 257 */         textColor = GuideColors.TEXT_DIM;
/*     */       } 
/* 259 */       ctx.method_25300(this.field_22793, TAB_NAMES[i], tx + tw / 2, tabY + 4, textColor);
/*     */     } 
/*     */ 
/*     */     
/* 263 */     ctx.method_25294(this.guiLeft, tabY + 16, this.guiLeft + 480, tabY + 16 + 1, GuideColors.BORDER_DIM);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int getGuideHighlightTabIndex() {
/* 270 */     PlayerStarterGuideData guide = PlayerStarterGuideData.getInstance();
/* 271 */     if (!guide.hasData() || guide.isCompleted()) return -1; 
/* 272 */     if (!"GUIDE_TAB".equals(guide.getStepType())) return -1; 
/* 273 */     switch (guide.getStepDetail()) { case "pokedex": case "getting_started":  }  return 
/*     */ 
/*     */       
/* 276 */       -1;
/*     */   }
/*     */ 
/*     */   
/*     */   private void drawContent(class_332 ctx, int mouseX, int mouseY, float delta) {
/* 281 */     switch (this.activeTab) {
/*     */       case 0:
/* 283 */         if (this.showingDetail) {
/* 284 */           this.pokemonDetailView.render(ctx, this.field_22793, mouseX, mouseY, delta); break;
/*     */         } 
/* 286 */         this.pokedexListView.render(ctx, this.field_22793, mouseX, mouseY, delta);
/*     */         break;
/*     */       case 1:
/* 289 */         this.spawnListView.render(ctx, this.field_22793, mouseX, mouseY, delta); break;
/* 290 */       case 2: this.evolutionView.render(ctx, this.field_22793, mouseX, mouseY, delta); break;
/*     */       case 3:
/* 292 */         if (this.showingMoveDetail) {
/* 293 */           this.moveDetailView.render(ctx, this.field_22793, mouseX, mouseY, delta); break;
/*     */         } 
/* 295 */         this.moveListView.render(ctx, this.field_22793, mouseX, mouseY, delta);
/*     */         break;
/*     */       case 4:
/* 298 */         this.serverGuideView.render(ctx, this.field_22793, mouseX, mouseY, delta); break;
/* 299 */       case 5: this.rideableListView.render(ctx, this.field_22793, mouseX, mouseY, delta); break;
/* 300 */       case 6: this.askAiView.render(ctx, this.field_22793, mouseX, mouseY, delta);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void computeTabLayout() {
/* 307 */     int totalPadding = 10 * TAB_NAMES.length;
/* 308 */     int availableWidth = 480;
/*     */ 
/*     */     
/* 311 */     int totalTextWidth = 0;
/* 312 */     for (String name : TAB_NAMES) {
/* 313 */       totalTextWidth += this.field_22793.method_1727(name);
/*     */     }
/*     */     
/* 316 */     int tabX = this.guiLeft;
/* 317 */     for (int i = 0; i < TAB_NAMES.length; i++) {
/* 318 */       int textW = this.field_22793.method_1727(TAB_NAMES[i]);
/*     */       
/* 320 */       int tw = (int)((textW + 10) / (totalTextWidth + totalPadding) * availableWidth);
/* 321 */       tw = Math.max(tw, textW + 8);
/* 322 */       this.tabXPositions[i] = tabX;
/* 323 */       this.tabWidths[i] = tw;
/* 324 */       tabX += tw;
/*     */     } 
/*     */ 
/*     */     
/* 328 */     int remaining = this.guiLeft + 480 - tabX;
/* 329 */     if (remaining > 0 && TAB_NAMES.length > 0) {
/* 330 */       this.tabWidths[TAB_NAMES.length - 1] = this.tabWidths[TAB_NAMES.length - 1] + remaining;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean method_25402(double mouseX, double mouseY, int button) {
/* 339 */     if (button == 0 && this.homeButtonHovered && !this.showLanding) {
/* 340 */       GuideSounds.playClick();
/* 341 */       this.showLanding = true;
/* 342 */       GuideSounds.resetHover();
/* 343 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 347 */     if (this.showLanding) {
/* 348 */       return this.landingView.mouseClicked(mouseX, mouseY, button);
/*     */     }
/*     */ 
/*     */     
/* 352 */     if (this.activeTab == 0 && this.showingDetail && this.pokemonDetailView.isModelViewerOpen()) {
/* 353 */       return this.pokemonDetailView.mouseClicked(mouseX, mouseY, button);
/*     */     }
/*     */ 
/*     */     
/* 357 */     if (button == 0 && this.hoveredTab >= 0 && this.hoveredTab < TAB_NAMES.length) {
/* 358 */       if (this.hoveredTab != this.activeTab) {
/* 359 */         GuideSounds.playTabClick();
/* 360 */         setActiveTab(this.hoveredTab);
/* 361 */         this.showingDetail = false;
/* 362 */         this.showingMoveDetail = false;
/* 363 */         GuideSounds.resetHover();
/*     */       } 
/* 365 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 369 */     switch (this.activeTab) { case 0: return 
/* 370 */           this.showingDetail ? this.pokemonDetailView.mouseClicked(mouseX, mouseY, button) : 
/* 371 */           this.pokedexListView.mouseClicked(mouseX, mouseY, button);
/*     */       case 1: 
/*     */       case 2: 
/* 374 */       case 3: return this.showingMoveDetail ? this.moveDetailView.mouseClicked(mouseX, mouseY, button) : 
/* 375 */           this.moveListView.mouseClicked(mouseX, mouseY, button);
/*     */       case 4: 
/*     */       case 5: 
/*     */       case 6:
/* 379 */        }  return super.method_25402(mouseX, mouseY, button);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean method_25401(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
/* 385 */     if (this.showLanding) return false;
/*     */     
/* 387 */     if (this.activeTab == 0 && this.showingDetail && this.pokemonDetailView.isModelViewerOpen()) {
/* 388 */       return this.pokemonDetailView.mouseScrolled(mouseX, mouseY, horizontalAmount, verticalAmount);
/*     */     }
/* 390 */     switch (this.activeTab) { case 0: return 
/* 391 */           this.showingDetail ? this.pokemonDetailView.mouseScrolled(mouseX, mouseY, horizontalAmount, verticalAmount) : 
/* 392 */           this.pokedexListView.mouseScrolled(mouseX, mouseY, horizontalAmount, verticalAmount);
/*     */       case 1: 
/*     */       case 2: 
/* 395 */       case 3: return this.showingMoveDetail ? this.moveDetailView.mouseScrolled(mouseX, mouseY, horizontalAmount, verticalAmount) : 
/* 396 */           this.moveListView.mouseScrolled(mouseX, mouseY, horizontalAmount, verticalAmount);
/*     */       case 4: 
/*     */       case 5: 
/*     */       case 6:
/* 400 */        }  return super.method_25401(mouseX, mouseY, horizontalAmount, verticalAmount);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean method_25403(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
/* 406 */     if (this.showLanding) return false;
/*     */     
/* 408 */     if (this.activeTab == 0 && this.showingDetail && this.pokemonDetailView.isModelViewerOpen()) {
/* 409 */       return this.pokemonDetailView.mouseDragged(mouseX, mouseY, button);
/*     */     }
/* 411 */     switch (this.activeTab) { case 0: return 
/* 412 */           this.showingDetail ? this.pokemonDetailView.mouseDragged(mouseX, mouseY, button) : 
/* 413 */           this.pokedexListView.mouseDragged(mouseX, mouseY, button);
/*     */       case 1: 
/*     */       case 2: 
/* 416 */       case 3: return this.showingMoveDetail ? this.moveDetailView.mouseDragged(mouseX, mouseY, button) : 
/* 417 */           this.moveListView.mouseDragged(mouseX, mouseY, button);
/*     */       case 4: 
/*     */       case 5: 
/*     */       case 6:
/* 421 */        }  return super.method_25403(mouseX, mouseY, button, deltaX, deltaY);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean method_25406(double mouseX, double mouseY, int button) {
/* 427 */     if (this.showLanding) return false;
/*     */     
/* 429 */     if (this.activeTab == 0 && this.showingDetail && this.pokemonDetailView.isModelViewerOpen()) {
/* 430 */       this.pokemonDetailView.mouseReleased(mouseX, mouseY, button);
/* 431 */       return true;
/*     */     } 
/* 433 */     switch (this.activeTab) { case 0:
/* 434 */         if (this.showingDetail) { this.pokemonDetailView.mouseReleased(mouseX, mouseY, button); break; }
/* 435 */          this.pokedexListView.mouseReleased(mouseX, mouseY, button); break;
/* 436 */       case 1: this.spawnListView.mouseReleased(mouseX, mouseY, button); break;
/* 437 */       case 2: this.evolutionView.mouseReleased(mouseX, mouseY, button); break;
/* 438 */       case 3: if (this.showingMoveDetail) { this.moveDetailView.mouseReleased(mouseX, mouseY, button); break; }
/* 439 */          this.moveListView.mouseReleased(mouseX, mouseY, button); break;
/* 440 */       case 4: this.serverGuideView.mouseReleased(mouseX, mouseY, button); break;
/* 441 */       case 5: this.rideableListView.mouseReleased(mouseX, mouseY, button); break;
/* 442 */       case 6: this.askAiView.mouseReleased(mouseX, mouseY, button); break; }
/*     */     
/* 444 */     return super.method_25406(mouseX, mouseY, button);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean method_25404(int keyCode, int scanCode, int modifiers) {
/* 449 */     if (this.showLanding) return super.method_25404(keyCode, scanCode, modifiers);
/*     */     
/* 451 */     switch (this.activeTab) { case 0:
/* 452 */         if (this.showingDetail);
/*     */       case 1: 
/*     */       case 2: 
/*     */       case 3:
/* 456 */         if (this.showingMoveDetail);
/*     */       case 4: 
/*     */       case 5: 
/*     */       case 6: 
/*     */       default:
/* 461 */         break; }  boolean handled = false;
/*     */     
/* 463 */     if (handled) return true; 
/* 464 */     return super.method_25404(keyCode, scanCode, modifiers);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean method_25400(char chr, int modifiers) {
/* 469 */     if (this.showLanding) return false; 
/* 470 */     switch (this.activeTab) { case 0:
/* 471 */         if (this.showingDetail);
/*     */       case 1: 
/*     */       case 2: 
/*     */       case 3:
/* 475 */         if (this.showingMoveDetail);
/*     */       case 4: 
/*     */       case 5: 
/*     */       case 6: 
/*     */       default:
/* 480 */         break; }  boolean handled = false;
/*     */     
/* 482 */     if (handled) return true; 
/* 483 */     return super.method_25400(chr, modifiers);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setActiveTab(int tab) {
/* 493 */     this.activeTab = tab;
/* 494 */     GuideNetwork.notifyTabClicked(tab);
/*     */   }
/*     */   
/*     */   private void onPokemonSelected(String slug) {
/* 498 */     GuideSounds.playNavigate();
/* 499 */     this.showingDetail = true;
/* 500 */     this.pokemonDetailView.requestDetail(slug);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void onPokemonSelectedFromTab(String slug) {
/* 508 */     GuideSounds.playNavigate();
/* 509 */     setActiveTab(0);
/* 510 */     this.showingDetail = true;
/* 511 */     this.pokemonDetailView.requestDetail(slug);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void onSearchResultSelected(String category, String slug) {
/* 519 */     switch (category) {
/*     */       
/*     */       case "pokemon":
/* 522 */         setActiveTab(0);
/* 523 */         this.showingDetail = true;
/* 524 */         this.pokemonDetailView.requestDetail(slug);
/*     */         break;
/*     */       
/*     */       case "spawn":
/* 528 */         setActiveTab(0);
/* 529 */         this.showingDetail = true;
/* 530 */         this.pokemonDetailView.requestDetailWithSubTab(slug, 2);
/*     */         break;
/*     */       
/*     */       case "rideable":
/* 534 */         setActiveTab(0);
/* 535 */         this.showingDetail = true;
/* 536 */         this.pokemonDetailView.requestDetail(slug);
/*     */         break;
/*     */       case "skin":
/*     */       case "form":
/* 540 */         setActiveTab(0);
/* 541 */         this.showingDetail = true;
/* 542 */         this.pokemonDetailView.requestDetailWithSubTab(slug, 5);
/*     */         break;
/*     */       
/*     */       case "drop":
/* 546 */         setActiveTab(0);
/* 547 */         this.showingDetail = true;
/* 548 */         this.pokemonDetailView.requestDetailWithSubTab(slug, 3);
/*     */         break;
/*     */       
/*     */       case "evolution":
/* 552 */         setActiveTab(0);
/* 553 */         this.showingDetail = true;
/* 554 */         this.pokemonDetailView.requestDetailWithSubTab(slug, 4);
/*     */         break;
/*     */       
/*     */       case "pokemon_moves":
/* 558 */         setActiveTab(0);
/* 559 */         this.showingDetail = true;
/* 560 */         this.pokemonDetailView.requestDetailWithSubTab(slug, 1);
/*     */         break;
/*     */       
/*     */       case "wiki":
/* 564 */         setActiveTab(4);
/* 565 */         GuideData.getInstance().setWikiLoading(true);
/* 566 */         GuideNetwork.requestGuideData("wiki_article", 0, slug, "");
/* 567 */         this.serverGuideView.navigateToArticle(slug);
/*     */         break;
/*     */       case "move":
/* 570 */         setActiveTab(3);
/* 571 */         this.showingMoveDetail = true;
/* 572 */         this.moveDetailView.requestMoveDetail(slug);
/*     */         break;
/*     */       
/*     */       case "biome_spawn":
/* 576 */         setActiveTab(1);
/* 577 */         this.spawnListView.filterByBiome(slug);
/*     */         break;
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
/*     */   private void onMoveSelectedFromDetail(String moveName) {
/* 590 */     GuideSounds.playNavigate();
/* 591 */     setActiveTab(3);
/* 592 */     this.showingDetail = false;
/* 593 */     this.showingMoveDetail = true;
/* 594 */     this.moveDetailView.requestMoveDetail(moveName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void onTypeSelectedFromDetail(String type) {
/* 602 */     setActiveTab(0);
/* 603 */     this.showingDetail = false;
/* 604 */     this.pokedexListView.filterByType(type);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void onMoveClickedFromList(String moveName) {
/* 612 */     GuideSounds.playNavigate();
/* 613 */     this.showingMoveDetail = true;
/* 614 */     this.moveDetailView.requestMoveDetail(moveName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void onPokemonSelectedFromMoveDetail(String slug) {
/* 621 */     setActiveTab(0);
/* 622 */     this.showingDetail = true;
/* 623 */     this.showingMoveDetail = false;
/* 624 */     this.pokemonDetailView.requestDetail(slug);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void onBackFromMoveDetail() {
/* 631 */     GuideSounds.playClick();
/* 632 */     this.showingMoveDetail = false;
/* 633 */     GuideData.getInstance().clearMoveDetail();
/*     */   }
/*     */   
/*     */   private void onBackToList() {
/* 637 */     GuideSounds.playClick();
/* 638 */     this.showingDetail = false;
/* 639 */     GuideData.getInstance().clearPokemonDetail();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void onLandingCategorySelected(int tabIndex, String articlePath) {
/* 647 */     GuideSounds.playNavigate();
/* 648 */     this.showLanding = false;
/* 649 */     setActiveTab(tabIndex);
/* 650 */     this.showingDetail = false;
/* 651 */     this.showingMoveDetail = false;
/*     */     
/* 653 */     if (tabIndex == 4 && articlePath != null && !articlePath.isEmpty())
/* 654 */       if (articlePath.startsWith("section:")) {
/*     */         
/* 656 */         String sectionTitle = articlePath.substring(8);
/* 657 */         this.serverGuideView.expandSectionByTitle(sectionTitle);
/*     */       } else {
/*     */         
/* 660 */         GuideData.getInstance().setWikiLoading(true);
/* 661 */         GuideNetwork.requestGuideData("wiki_article", 0, articlePath, "");
/* 662 */         this.serverGuideView.navigateToArticle(articlePath);
/*     */       }  
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\guide\screen\GuideScreen.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */