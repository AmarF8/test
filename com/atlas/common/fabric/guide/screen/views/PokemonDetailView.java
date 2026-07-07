/*      */ package com.atlas.common.fabric.guide.screen.views;
/*      */ 
/*      */ import com.atlas.common.fabric.guide.data.GuideData;
/*      */ import com.atlas.common.fabric.guide.data.PokemonDetail;
/*      */ import com.atlas.common.fabric.guide.network.GuideNetwork;
/*      */ import com.atlas.common.fabric.guide.screen.GuideColors;
/*      */ import com.atlas.common.fabric.guide.screen.GuideSounds;
/*      */ import com.atlas.common.fabric.guide.screen.widgets.GuideScrollableArea;
/*      */ import com.atlas.common.fabric.guide.screen.widgets.GuideStatBar;
/*      */ import com.atlas.common.fabric.guide.screen.widgets.GuideTypeBadge;
/*      */ import com.atlas.common.fabric.guide.screen.widgets.ModelViewerOverlay;
/*      */ import com.atlas.common.fabric.screen.PokemonRenderHelper;
/*      */ import com.cobblemon.mod.common.api.pokemon.PokemonSpecies;
/*      */ import com.cobblemon.mod.common.client.render.models.blockbench.FloatingState;
/*      */ import com.cobblemon.mod.common.pokemon.Species;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import java.util.function.Consumer;
/*      */ import net.minecraft.class_2960;
/*      */ import net.minecraft.class_327;
/*      */ import net.minecraft.class_332;
/*      */ import net.minecraft.class_4587;
/*      */ import org.joml.Quaternionf;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class PokemonDetailView
/*      */ {
/*      */   private int x;
/*      */   private int y;
/*      */   private int width;
/*      */   private int height;
/*      */   private Runnable onBack;
/*   39 */   private static final String[] SUB_TABS = new String[] { "Stats", "Moves", "Spawns", "Drops", "Evo", "Forms" };
/*   40 */   private int activeSubTab = 0;
/*   41 */   private int hoveredSubTab = -1;
/*      */ 
/*      */   
/*      */   private GuideScrollableArea rightScroll;
/*      */   
/*      */   private FloatingState pokemonState;
/*      */   
/*   48 */   private final Map<String, class_2960> resolvedSpeciesIds = new HashMap<>();
/*      */ 
/*      */   
/*   51 */   private int activeMoveTab = 0;
/*   52 */   private int hoveredMoveTab = -1;
/*   53 */   private static final String[] MOVE_TABS = new String[] { "Level", "TM", "Egg", "Tutor" };
/*      */ 
/*      */   
/*      */   private boolean backHovered = false;
/*      */   
/*      */   private Consumer<String> onMoveSelected;
/*      */   
/*   60 */   private int hoveredMoveRowIndex = -1;
/*      */   
/*      */   private Consumer<String> onTypeSelected;
/*      */   
/*   64 */   private String hoveredType = null;
/*      */   
/*      */   private boolean modelBoxHovered = false;
/*      */   
/*      */   private int lastModelX;
/*      */   private int lastModelY;
/*      */   private int lastModelSize;
/*   71 */   private final ModelViewerOverlay modelViewer = new ModelViewerOverlay();
/*      */   
/*      */   private int screenWidth;
/*      */   
/*      */   private int screenHeight;
/*      */   
/*      */   private boolean isShinyToggled = false;
/*      */   
/*      */   private boolean shinyButtonHovered = false;
/*      */   
/*   81 */   private int selectedFormIndex = -1;
/*   82 */   private int hoveredFormIndex = -1;
/*   83 */   private int hoveredSkinIndex = -1;
/*   84 */   private int selectedSkinIndex = -1;
/*      */ 
/*      */   
/*      */   private static Map<String, class_2960> NORMALIZED_SPECIES_MAP;
/*      */ 
/*      */   
/*      */   private String hoveredEvoSlug;
/*      */ 
/*      */   
/*      */   private final Map<String, FloatingState> evoModelStates;
/*      */ 
/*      */   
/*      */   private static synchronized Map<String, class_2960> getNormalizedSpeciesMap() {
/*   97 */     if (NORMALIZED_SPECIES_MAP != null) return NORMALIZED_SPECIES_MAP; 
/*   98 */     NORMALIZED_SPECIES_MAP = new HashMap<>();
/*      */     try {
/*  100 */       for (Species species : PokemonSpecies.getSpecies()) {
/*  101 */         class_2960 resId = species.getResourceIdentifier();
/*  102 */         String path = resId.method_12832();
/*  103 */         String namespace = resId.method_12836();
/*  104 */         class_2960 fullId = class_2960.method_60655(namespace, path);
/*      */         
/*  106 */         NORMALIZED_SPECIES_MAP.put(path, fullId);
/*      */         
/*  108 */         String normalized = path.replaceAll("[^a-z0-9]", "");
/*  109 */         NORMALIZED_SPECIES_MAP.putIfAbsent(normalized, fullId);
/*      */       } 
/*  111 */     } catch (Exception exception) {}
/*      */ 
/*      */     
/*  114 */     return NORMALIZED_SPECIES_MAP;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private class_2960 resolveSpeciesId(String slug) {
/*  123 */     if (this.resolvedSpeciesIds.containsKey(slug)) return this.resolvedSpeciesIds.get(slug);
/*      */     
/*  125 */     Map<String, class_2960> speciesMap = getNormalizedSpeciesMap();
/*      */ 
/*      */     
/*  128 */     class_2960 id = speciesMap.get(slug);
/*      */ 
/*      */     
/*  131 */     if (id == null) {
/*  132 */       String normalized = slug.replaceAll("[^a-z0-9]", "");
/*  133 */       id = speciesMap.get(normalized);
/*      */     } 
/*      */ 
/*      */     
/*  137 */     if (id == null) {
/*  138 */       id = class_2960.method_60655("cobblemon", slug);
/*      */     }
/*      */     
/*  141 */     this.resolvedSpeciesIds.put(slug, id);
/*  142 */     return id;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void init(int x, int y, int width, int height, Runnable onBack) {
/*  148 */     this.x = x;
/*  149 */     this.y = y;
/*  150 */     this.width = width;
/*  151 */     this.height = height;
/*  152 */     this.onBack = onBack;
/*      */     
/*  154 */     if (this.rightScroll == null) {
/*  155 */       this.activeSubTab = 0;
/*  156 */       this.activeMoveTab = 0;
/*      */       
/*  158 */       int rightX = x + width / 2 + 2;
/*  159 */       int rightW = width / 2 - 6;
/*  160 */       int rightY = y + 24;
/*  161 */       int rightH = height - 28;
/*  162 */       this.rightScroll = new GuideScrollableArea(rightX, rightY, rightW, rightH);
/*      */     } else {
/*  164 */       updateBounds(x, y, width, height);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateBounds(int x, int y, int width, int height) {
/*  172 */     this.x = x;
/*  173 */     this.y = y;
/*  174 */     this.width = width;
/*  175 */     this.height = height;
/*      */     
/*  177 */     if (this.rightScroll != null) {
/*  178 */       int rightX = x + width / 2 + 2;
/*  179 */       int rightW = width / 2 - 6;
/*  180 */       int rightY = y + 24;
/*  181 */       int rightH = height - 28;
/*  182 */       this.rightScroll.updateBounds(rightX, rightY, rightW, rightH);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void requestDetail(String slug) {
/*  190 */     requestDetailWithSubTab(slug, 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void requestDetailWithSubTab(String slug, int subTab) {
/*  198 */     GuideData.getInstance().clearPokemonDetail();
/*  199 */     GuideData.getInstance().setDetailLoading(true);
/*  200 */     GuideNetwork.requestPokemonDetail(slug);
/*  201 */     this.pokemonState = new FloatingState();
/*  202 */     this.selectedFormIndex = -1;
/*  203 */     this.selectedSkinIndex = -1;
/*  204 */     this.isShinyToggled = false;
/*  205 */     this.activeSubTab = Math.max(0, Math.min(subTab, SUB_TABS.length - 1));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setOnMoveSelected(Consumer<String> onMoveSelected) {
/*  212 */     this.onMoveSelected = onMoveSelected;
/*      */   }
/*      */   
/*      */   public void setOnTypeSelected(Consumer<String> onTypeSelected) {
/*  216 */     this.onTypeSelected = onTypeSelected;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setScreenDimensions(int screenWidth, int screenHeight) {
/*  223 */     this.screenWidth = screenWidth;
/*  224 */     this.screenHeight = screenHeight;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isModelViewerOpen() {
/*  231 */     return this.modelViewer.isVisible();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void render(class_332 ctx, class_327 textRenderer, int mouseX, int mouseY, float delta) {
/*  237 */     PokemonDetail detail = GuideData.getInstance().getCurrentPokemonDetail();
/*      */ 
/*      */     
/*  240 */     ctx.method_25294(this.x, this.y, this.x + this.width, this.y + this.height, GuideColors.SECTION_BG);
/*      */ 
/*      */     
/*  243 */     if (GuideData.getInstance().isDetailLoading()) {
/*  244 */       ctx.method_25300(textRenderer, "Loading...", this.x + this.width / 2, this.y + this.height / 2, GuideColors.TEXT_DIM);
/*      */       
/*  246 */       renderBackButton(ctx, textRenderer, mouseX, mouseY);
/*      */       
/*      */       return;
/*      */     } 
/*  250 */     if (detail == null) {
/*  251 */       ctx.method_25300(textRenderer, "Pokemon data not available", this.x + this.width / 2, this.y + this.height / 2, GuideColors.TEXT_DIM);
/*      */       
/*  253 */       renderBackButton(ctx, textRenderer, mouseX, mouseY);
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*  258 */     renderBackButton(ctx, textRenderer, mouseX, mouseY);
/*      */ 
/*      */     
/*  261 */     int effectiveMouseX = this.modelViewer.isVisible() ? -1 : mouseX;
/*  262 */     int effectiveMouseY = this.modelViewer.isVisible() ? -1 : mouseY;
/*      */ 
/*      */     
/*  265 */     this.modelBoxHovered = (effectiveMouseX >= this.lastModelX && effectiveMouseX < this.lastModelX + this.lastModelSize && effectiveMouseY >= this.lastModelY && effectiveMouseY < this.lastModelY + this.lastModelSize);
/*      */ 
/*      */ 
/*      */     
/*  269 */     renderLeftPanel(ctx, textRenderer, detail, effectiveMouseX, effectiveMouseY, delta);
/*      */ 
/*      */     
/*  272 */     renderRightPanel(ctx, textRenderer, detail, effectiveMouseX, effectiveMouseY, delta);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void renderModelViewerOverlay(class_332 ctx, class_327 textRenderer, int mouseX, int mouseY, float delta) {
/*  283 */     if (this.modelViewer.isVisible()) {
/*  284 */       this.modelViewer.render(ctx, textRenderer, this.screenWidth, this.screenHeight, mouseX, mouseY, delta);
/*      */     }
/*      */   }
/*      */   
/*      */   private void renderBackButton(class_332 ctx, class_327 textRenderer, int mouseX, int mouseY) {
/*  289 */     int bx = this.x + 4, by = this.y + 4, bw = 40, bh = 12;
/*  290 */     this.backHovered = (mouseX >= bx && mouseX < bx + bw && mouseY >= by && mouseY < by + bh);
/*      */     
/*  292 */     int bgColor = this.backHovered ? GuideColors.BUTTON_HOVER : GuideColors.BUTTON_BG;
/*  293 */     ctx.method_25294(bx, by, bx + bw, by + bh, bgColor);
/*  294 */     ctx.method_49601(bx, by, bw, bh, this.backHovered ? GuideColors.BUTTON_BORDER_HOVER : GuideColors.BUTTON_BORDER);
/*  295 */     ctx.method_25300(textRenderer, "← Back", bx + bw / 2, by + 2, GuideColors.TEXT_PRIMARY);
/*      */   }
/*      */   
/*      */   private void renderLeftPanel(class_332 ctx, class_327 textRenderer, PokemonDetail detail, int mouseX, int mouseY, float delta) {
/*      */     String title;
/*  300 */     int leftX = this.x + 4;
/*  301 */     int leftY = this.y + 20;
/*  302 */     int leftW = this.width / 2 - 6;
/*      */ 
/*      */     
/*  305 */     PokemonDetail.FormInfo activeForm = null;
/*  306 */     if (this.selectedFormIndex >= 0 && this.selectedFormIndex < detail.forms.size()) {
/*  307 */       activeForm = detail.forms.get(this.selectedFormIndex);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  312 */     if (activeForm != null) {
/*  313 */       title = String.format("#%03d %s (%s)", new Object[] { Integer.valueOf(detail.id), detail.name, activeForm.name });
/*  314 */     } else if (this.selectedSkinIndex >= 0 && this.selectedSkinIndex < detail.skins.size()) {
/*  315 */       PokemonDetail.SkinInfo activeSkin = detail.skins.get(this.selectedSkinIndex);
/*  316 */       String skinLabel = activeSkin.name.isEmpty() ? activeSkin.identifier : activeSkin.name;
/*  317 */       title = String.format("#%03d %s (%s)", new Object[] { Integer.valueOf(detail.id), detail.name, skinLabel });
/*      */     } else {
/*  319 */       title = String.format("#%03d %s", new Object[] { Integer.valueOf(detail.id), detail.name });
/*      */     } 
/*  321 */     ctx.method_51433(textRenderer, title, leftX + 4, leftY, GuideColors.TEXT_WHITE, true);
/*  322 */     leftY += 12;
/*      */ 
/*      */ 
/*      */     
/*  326 */     List<String> displayTypes = (activeForm != null && activeForm.types != null && !activeForm.types.isEmpty()) ? activeForm.types : detail.types;
/*  327 */     this.hoveredType = null;
/*  328 */     int typeX = leftX + 4;
/*  329 */     for (String type : displayTypes) {
/*  330 */       int w = GuideTypeBadge.draw(ctx, textRenderer, type, typeX, leftY);
/*  331 */       boolean typeHovered = (mouseX >= typeX && mouseX < typeX + w && mouseY >= leftY && mouseY < leftY + 12);
/*      */       
/*  333 */       if (typeHovered) {
/*  334 */         this.hoveredType = type;
/*  335 */         ctx.method_49601(typeX - 1, leftY - 1, w + 2, 12, GuideColors.TEXT_WHITE);
/*      */       } 
/*  337 */       typeX += w + 3;
/*      */     } 
/*      */ 
/*      */     
/*  341 */     if (activeForm != null) {
/*  342 */       String resetLabel = "✕ Base";
/*  343 */       int resetW = textRenderer.method_1727(resetLabel) + 6;
/*  344 */       int resetX = leftX + leftW - resetW - 4;
/*  345 */       boolean resetHovered = (mouseX >= resetX && mouseX < resetX + resetW && mouseY >= leftY - 1 && mouseY < leftY + 11);
/*      */       
/*  347 */       int resetBg = resetHovered ? GuideColors.BUTTON_HOVER : GuideColors.BUTTON_BG;
/*  348 */       ctx.method_25294(resetX, leftY - 1, resetX + resetW, leftY + 10, resetBg);
/*  349 */       ctx.method_51433(textRenderer, resetLabel, resetX + 3, leftY, GuideColors.TEXT_PRIMARY, true);
/*      */     } 
/*  351 */     leftY += 16;
/*      */ 
/*      */     
/*  354 */     int modelSize = Math.min(leftW - 8, 80);
/*  355 */     int modelX = leftX + 4;
/*  356 */     int modelY = leftY;
/*      */ 
/*      */     
/*  359 */     this.lastModelX = modelX;
/*  360 */     this.lastModelY = modelY;
/*  361 */     this.lastModelSize = modelSize;
/*      */ 
/*      */     
/*  364 */     ctx.method_25294(modelX, modelY, modelX + modelSize, modelY + modelSize, GuideColors.CARD_BG);
/*  365 */     ctx.method_49601(modelX, modelY, modelSize, modelSize, 
/*  366 */         this.modelBoxHovered ? GuideColors.BORDER_HIGHLIGHT : GuideColors.BORDER_DIM);
/*      */ 
/*      */     
/*  369 */     String shinyLabel = "Shiny";
/*  370 */     int shinyBtnW = textRenderer.method_1727(shinyLabel) + 6;
/*  371 */     int shinyBtnH = 11;
/*  372 */     int shinyBtnX = modelX + modelSize - shinyBtnW - 2;
/*  373 */     int shinyBtnY = modelY + 2;
/*  374 */     this.shinyButtonHovered = (mouseX >= shinyBtnX && mouseX < shinyBtnX + shinyBtnW && mouseY >= shinyBtnY && mouseY < shinyBtnY + shinyBtnH);
/*      */ 
/*      */ 
/*      */     
/*  378 */     int shinyBg = this.isShinyToggled ? GuideColors.color(120, 100, 20, 220) : (this.shinyButtonHovered ? GuideColors.color(50, 60, 70, 220) : GuideColors.color(35, 40, 48, 200));
/*      */ 
/*      */     
/*  381 */     int shinyBorder = this.isShinyToggled ? GuideColors.color(248, 208, 48, 255) : (this.shinyButtonHovered ? GuideColors.BORDER_HIGHLIGHT : GuideColors.BORDER_DIM);
/*  382 */     ctx.method_25294(shinyBtnX, shinyBtnY, shinyBtnX + shinyBtnW, shinyBtnY + shinyBtnH, shinyBg);
/*  383 */     ctx.method_49601(shinyBtnX, shinyBtnY, shinyBtnW, shinyBtnH, shinyBorder);
/*  384 */     int shinyTextColor = this.isShinyToggled ? GuideColors.color(248, 208, 48, 255) : (this.shinyButtonHovered ? GuideColors.TEXT_PRIMARY : GuideColors.TEXT_DIM);
/*  385 */     ctx.method_51433(textRenderer, shinyLabel, shinyBtnX + 3, shinyBtnY + 1, shinyTextColor, true);
/*      */ 
/*      */     
/*      */     try {
/*  389 */       renderPokemonModel(ctx, detail, modelX, modelY, modelSize, delta);
/*  390 */     } catch (Exception e) {
/*      */       
/*  392 */       ctx.method_25300(textRenderer, detail.name, modelX + modelSize / 2, modelY + modelSize / 2 - 4, GuideColors.TEXT_DIM);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  397 */     if (this.modelBoxHovered) {
/*  398 */       ctx.method_25300(textRenderer, "Click to view", modelX + modelSize / 2, modelY + modelSize - 10, GuideColors.TEXT_DIM);
/*      */     }
/*      */     
/*  401 */     leftY += modelSize + 6;
/*      */ 
/*      */     
/*  404 */     if (detail.rideable != null) {
/*  405 */       leftY = renderRideableBadge(ctx, textRenderer, detail.rideable, leftX + 4, leftY, leftW - 8);
/*  406 */       leftY += 4;
/*      */     } 
/*      */ 
/*      */     
/*  410 */     renderInfoGrid(ctx, textRenderer, detail, leftX + 4, leftY, leftW - 8);
/*      */   }
/*      */ 
/*      */   
/*      */   private void renderPokemonModel(class_332 ctx, PokemonDetail detail, int modelX, int modelY, int modelSize, float delta) {
/*  415 */     if (!PokemonRenderHelper.isAvailable())
/*      */       return; 
/*      */     try {
/*  418 */       class_2960 speciesId = resolveSpeciesId(detail.slug);
/*      */       
/*  420 */       Set<String> modelAspects = Set.of();
/*  421 */       if (this.selectedSkinIndex >= 0 && this.selectedSkinIndex < detail.skins.size()) {
/*  422 */         PokemonDetail.SkinInfo activeSkin = detail.skins.get(this.selectedSkinIndex);
/*  423 */         if (activeSkin.aspects != null && !activeSkin.aspects.isEmpty()) {
/*  424 */           modelAspects = new HashSet<>(activeSkin.aspects);
/*      */         }
/*  426 */       } else if (this.selectedFormIndex >= 0 && this.selectedFormIndex < detail.forms.size()) {
/*  427 */         PokemonDetail.FormInfo activeFormForModel = detail.forms.get(this.selectedFormIndex);
/*  428 */         if (activeFormForModel.aspects != null && !activeFormForModel.aspects.isEmpty()) {
/*  429 */           modelAspects = new HashSet<>(activeFormForModel.aspects);
/*      */         }
/*      */       } 
/*      */       
/*  433 */       if (this.isShinyToggled) {
/*  434 */         if (!(modelAspects instanceof HashSet))
/*  435 */           modelAspects = new HashSet<>(modelAspects); 
/*  436 */         modelAspects.add("shiny");
/*      */       } 
/*  438 */       this.pokemonState.setCurrentAspects(modelAspects);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  443 */       class_4587 mat = ctx.method_51448();
/*  444 */       mat.method_22903();
/*      */       
/*  446 */       mat.method_22904(modelX + modelSize / 2.0D, (modelY + 2), 0.0D);
/*  447 */       float scale = modelSize / 10.0F;
/*  448 */       mat.method_22905(scale, scale, 1.0F);
/*      */       
/*  450 */       Quaternionf rot = new Quaternionf();
/*  451 */       rot.rotationXYZ((float)Math.toRadians(13.0D), (float)Math.toRadians(35.0D), 0.0F);
/*      */       
/*  453 */       PokemonRenderHelper.draw(speciesId, mat, rot, this.pokemonState, delta);
/*  454 */       mat.method_22909();
/*  455 */     } catch (Exception exception) {}
/*      */   }
/*      */ 
/*      */   
/*      */   private void renderInfoGrid(class_332 ctx, class_327 textRenderer, PokemonDetail detail, int infoX, int infoY, int infoW) {
/*  460 */     int col1X = infoX;
/*  461 */     int col2X = infoX + infoW / 2;
/*  462 */     int rowH = 11;
/*      */ 
/*      */     
/*  465 */     drawInfoRow(ctx, textRenderer, "Height", String.format("%.1fm", new Object[] { Double.valueOf(detail.height) }), col1X, infoY);
/*  466 */     drawInfoRow(ctx, textRenderer, "Weight", String.format("%.1fkg", new Object[] { Double.valueOf(detail.weight) }), col2X, infoY);
/*  467 */     infoY += rowH;
/*      */ 
/*      */     
/*  470 */     drawInfoRow(ctx, textRenderer, "Catch Rate", String.valueOf(detail.catchRate), col1X, infoY);
/*  471 */     drawInfoRow(ctx, textRenderer, "Friendship", String.valueOf(detail.baseFriendship), col2X, infoY);
/*  472 */     infoY += rowH;
/*      */ 
/*      */     
/*  475 */     drawInfoRow(ctx, textRenderer, "Gen", String.valueOf(detail.generation), col1X, infoY);
/*  476 */     drawInfoRow(ctx, textRenderer, "Egg Cycles", String.valueOf(detail.eggCycles), col2X, infoY);
/*  477 */     infoY += rowH;
/*      */ 
/*      */     
/*  480 */     if (!detail.abilities.isEmpty()) {
/*  481 */       ctx.method_51433(textRenderer, "Abilities:", col1X, infoY, GuideColors.TEXT_DIM, true);
/*  482 */       infoY += 10;
/*  483 */       for (PokemonDetail.Ability ability : detail.abilities) {
/*  484 */         String label = ability.name + ability.name;
/*  485 */         int color = ability.hidden ? GuideColors.TEXT_ACCENT : GuideColors.TEXT_PRIMARY;
/*  486 */         ctx.method_51433(textRenderer, "• " + label, col1X + 4, infoY, color, true);
/*  487 */         infoY += 10;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  492 */     if (!detail.eggGroups.isEmpty()) {
/*  493 */       ctx.method_51433(textRenderer, "Egg Groups:", col1X, infoY, GuideColors.TEXT_DIM, true);
/*  494 */       ctx.method_51433(textRenderer, String.join(", ", detail.eggGroups), col1X + 64, infoY, GuideColors.TEXT_PRIMARY, true);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private int renderRideableBadge(class_332 ctx, class_327 textRenderer, PokemonDetail.RideableData rideable, int rx, int ry, int rw) {
/*  501 */     int movementColor = getRideableMovementColor(rideable.movementType);
/*  502 */     int bgColor = GuideColors.darken(movementColor, 80);
/*      */     
/*  504 */     ctx.method_25294(rx, ry, rx + rw, ry + 14, bgColor);
/*  505 */     ctx.method_49601(rx, ry, rw, 14, movementColor);
/*      */ 
/*      */     
/*  508 */     String movementLabel = rideable.movementType.isEmpty() ? "Rideable" : (rideable.movementType.substring(0, 1).toUpperCase() + rideable.movementType.substring(0, 1).toUpperCase());
/*  509 */     ctx.method_51433(textRenderer, movementLabel + " mount", rx + 4, ry + 3, movementColor, true);
/*      */     
/*  511 */     String seats = "" + rideable.seats + " seat" + rideable.seats;
/*  512 */     ctx.method_51433(textRenderer, seats, rx + rw - textRenderer.method_1727(seats) - 4, ry + 3, GuideColors.TEXT_PRIMARY, true);
/*  513 */     ry += 16;
/*      */ 
/*      */     
/*  516 */     if (rideable.stats != null && !rideable.stats.isEmpty()) {
/*  517 */       int labelW = 72;
/*  518 */       int valueW = 18;
/*  519 */       int barX = rx + labelW + valueW + 2;
/*  520 */       int barW = rw - labelW - valueW - 4;
/*  521 */       int barH = 3;
/*  522 */       if (barW < 20) barW = 20;
/*      */       
/*  524 */       int statIdx = 0;
/*  525 */       for (PokemonDetail.RideableData.RideStat stat : rideable.stats) {
/*  526 */         int statColor = getRideStatColor(statIdx);
/*  527 */         String label = stat.displayName;
/*  528 */         if (textRenderer.method_1727(label) > labelW - 2) {
/*  529 */           while (label.length() > 2 && textRenderer.method_1727(label + "..") > labelW - 2) {
/*  530 */             label = label.substring(0, label.length() - 1);
/*      */           }
/*  532 */           label = label + "..";
/*      */         } 
/*  534 */         ctx.method_51433(textRenderer, label, rx, ry + 1, GuideColors.TEXT_DIM, true);
/*  535 */         ctx.method_51433(textRenderer, String.valueOf(stat.maxValue), rx + labelW, ry + 1, GuideColors.TEXT_PRIMARY, true);
/*  536 */         ctx.method_25294(barX, ry + 3, barX + barW, ry + 3 + barH, GuideColors.BORDER_DIM);
/*  537 */         int filled = (int)(barW * Math.min(1.0D, stat.maxValue / 100.0D));
/*  538 */         if (filled > 0) {
/*  539 */           ctx.method_25294(barX, ry + 3, barX + filled, ry + 3 + barH, statColor);
/*      */         }
/*  541 */         ry += 9;
/*  542 */         statIdx++;
/*      */       } 
/*      */     } 
/*      */     
/*  546 */     return ry;
/*      */   }
/*      */   
/*      */   private static int getRideStatColor(int index) {
/*  550 */     switch (index) { case 0: case 1: case 2: case 3: case 4:  }  return 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  556 */       GuideColors.ACCENT_EMERALD;
/*      */   }
/*      */ 
/*      */   
/*      */   private static int getRideableMovementColor(String movementType) {
/*  561 */     if (movementType == null) return GuideColors.ACCENT_EMERALD; 
/*  562 */     switch (movementType.toLowerCase()) { case "land": case "water": case "fly": case "air": case "lava": case "composite":  }  return 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  568 */       GuideColors.ACCENT_EMERALD;
/*      */   }
/*      */ 
/*      */   
/*      */   private void drawInfoRow(class_332 ctx, class_327 textRenderer, String label, String value, int ix, int iy) {
/*  573 */     ctx.method_51433(textRenderer, label + ":", ix, iy, GuideColors.TEXT_DIM, true);
/*  574 */     int labelW = textRenderer.method_1727(label + ": ");
/*  575 */     ctx.method_51433(textRenderer, value, ix + labelW, iy, GuideColors.TEXT_PRIMARY, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void renderRightPanel(class_332 ctx, class_327 textRenderer, PokemonDetail detail, int mouseX, int mouseY, float delta) {
/*  582 */     int rightX = this.x + this.width / 2 + 2;
/*  583 */     int rightY = this.y + 4;
/*  584 */     int rightW = this.width / 2 - 6;
/*      */ 
/*      */     
/*  587 */     this.hoveredSubTab = -1;
/*  588 */     int tabX = rightX;
/*  589 */     for (int i = 0; i < SUB_TABS.length; i++) {
/*  590 */       int tabW = textRenderer.method_1727(SUB_TABS[i]) + 8;
/*  591 */       boolean active = (i == this.activeSubTab);
/*  592 */       boolean hovered = (mouseX >= tabX && mouseX < tabX + tabW && mouseY >= rightY && mouseY < rightY + 14);
/*  593 */       if (hovered) {
/*  594 */         this.hoveredSubTab = i;
/*  595 */         if (!active) GuideSounds.playHover("detail_tab:" + i);
/*      */       
/*      */       } 
/*  598 */       int bg = active ? GuideColors.TAB_ACTIVE_BG : (hovered ? GuideColors.TAB_HOVER_BG : GuideColors.TAB_INACTIVE_BG);
/*  599 */       ctx.method_25294(tabX, rightY, tabX + tabW, rightY + 14, bg);
/*  600 */       if (active) {
/*  601 */         ctx.method_25294(tabX, rightY + 13, tabX + tabW, rightY + 14, GuideColors.ACCENT_EMERALD);
/*      */       }
/*  603 */       int textColor = active ? GuideColors.TEXT_ACCENT : (hovered ? GuideColors.TEXT_PRIMARY : GuideColors.TEXT_DIM);
/*  604 */       ctx.method_51433(textRenderer, SUB_TABS[i], tabX + 5, rightY + 3, textColor, true);
/*  605 */       tabX += tabW + 2;
/*      */     } 
/*      */ 
/*      */     
/*  609 */     this.rightScroll.beginRender(ctx);
/*      */     
/*  611 */     int contentY = this.rightScroll.getY() + 4 - this.rightScroll.getScrollOffset();
/*  612 */     int contentX = this.rightScroll.getX() + 4;
/*  613 */     int contentW = this.rightScroll.getContentWidth() - 8;
/*      */     
/*  615 */     switch (this.activeSubTab) { case 0: 
/*      */       case 1: 
/*      */       case 2: 
/*      */       case 3: 
/*      */       case 4: 
/*      */       case 5: 
/*      */       default:
/*  622 */         break; }  int totalContentHeight = 0;
/*      */ 
/*      */     
/*  625 */     this.rightScroll.setContentHeight(totalContentHeight + 8);
/*  626 */     this.rightScroll.endRender(ctx);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private int renderStatsContent(class_332 ctx, class_327 textRenderer, PokemonDetail detail, int cx, int cy, int cw) {
/*  632 */     PokemonDetail.BaseStats displayStats = detail.baseStats;
/*  633 */     if (this.selectedFormIndex >= 0 && this.selectedFormIndex < detail.forms.size()) {
/*  634 */       PokemonDetail.FormInfo form = detail.forms.get(this.selectedFormIndex);
/*  635 */       if (form.baseStats != null) {
/*  636 */         displayStats = form.baseStats;
/*      */       }
/*      */     } 
/*  639 */     int[] stats = { displayStats.hp, displayStats.attack, displayStats.defense, displayStats.spAtk, displayStats.spDef, displayStats.speed };
/*      */ 
/*      */ 
/*      */     
/*  643 */     return GuideStatBar.drawStatPanel(ctx, textRenderer, stats, cx, cy, cw);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private int renderMovesContent(class_332 ctx, class_327 textRenderer, PokemonDetail detail, int cx, int cy, int cw, int mouseX, int mouseY) {
/*  649 */     this.hoveredMoveTab = -1;
/*  650 */     int tabX = cx;
/*  651 */     for (int i = 0; i < MOVE_TABS.length; i++) {
/*  652 */       int tabW = textRenderer.method_1727(MOVE_TABS[i]) + 8;
/*  653 */       boolean active = (i == this.activeMoveTab);
/*      */       
/*  655 */       boolean hovered = (mouseX >= tabX && mouseX < tabX + tabW && mouseY >= cy && mouseY < cy + 12 && this.rightScroll.isInBounds(mouseX, mouseY));
/*  656 */       if (hovered) this.hoveredMoveTab = i;
/*      */       
/*  658 */       int bg = active ? GuideColors.BUTTON_BG : GuideColors.CARD_BG;
/*  659 */       ctx.method_25294(tabX, cy, tabX + tabW, cy + 12, bg);
/*  660 */       if (active) ctx.method_25294(tabX, cy + 11, tabX + tabW, cy + 12, GuideColors.ACCENT_TEAL); 
/*  661 */       int tc = active ? GuideColors.TEXT_WHITE : (hovered ? GuideColors.TEXT_PRIMARY : GuideColors.TEXT_DIM);
/*  662 */       ctx.method_51433(textRenderer, MOVE_TABS[i], tabX + 4, cy + 2, tc, true);
/*  663 */       tabX += tabW + 2;
/*      */     } 
/*  665 */     cy += 16;
/*      */ 
/*      */     
/*  668 */     switch (this.activeMoveTab) { case 0: 
/*      */       case 1: 
/*      */       case 2: 
/*      */       case 3: 
/*      */       default:
/*  673 */         break; }  List<?> moves = List.of();
/*      */ 
/*      */     
/*  676 */     if (moves.isEmpty()) {
/*  677 */       ctx.method_51433(textRenderer, "No moves", cx, cy, GuideColors.TEXT_DIM, true);
/*  678 */       return cy - this.rightScroll.getY() + 4 - this.rightScroll.getScrollOffset() + 12;
/*      */     } 
/*      */     
/*  681 */     this.hoveredMoveRowIndex = -1;
/*  682 */     for (int mi = 0; mi < moves.size(); mi++) {
/*  683 */       Object move = moves.get(mi);
/*      */ 
/*      */ 
/*      */       
/*  687 */       boolean rowHovered = (mouseX >= cx && mouseX < cx + cw && mouseY >= cy && mouseY < cy + 11 && this.rightScroll.isInBounds(mouseX, mouseY));
/*  688 */       if (rowHovered) this.hoveredMoveRowIndex = mi;
/*      */ 
/*      */       
/*  691 */       if (rowHovered) {
/*  692 */         ctx.method_25294(cx - 2, cy, cx + cw, cy + 11, GuideColors.CARD_HOVER_BG);
/*      */       }
/*      */       
/*  695 */       if (move instanceof PokemonDetail.LevelMove) { PokemonDetail.LevelMove lm = (PokemonDetail.LevelMove)move;
/*  696 */         String lvlStr = (lm.level == 0) ? "—" : ("Lv" + lm.level);
/*  697 */         ctx.method_51433(textRenderer, lvlStr, cx, cy + 1, GuideColors.TEXT_DIM, true);
/*  698 */         int nameColor = rowHovered ? GuideColors.TEXT_ACCENT : GuideColors.TEXT_PRIMARY;
/*  699 */         ctx.method_51433(textRenderer, lm.name, cx + 30, cy + 1, nameColor, true); }
/*  700 */       else if (move instanceof String) { String name = (String)move;
/*  701 */         ctx.method_51433(textRenderer, "•", cx, cy + 1, GuideColors.TEXT_DIM, true);
/*  702 */         int nameColor = rowHovered ? GuideColors.TEXT_ACCENT : GuideColors.TEXT_PRIMARY;
/*  703 */         ctx.method_51433(textRenderer, name, cx + 10, cy + 1, nameColor, true); }
/*      */       
/*  705 */       cy += 11;
/*      */     } 
/*      */     
/*  708 */     return cy - this.rightScroll.getY() + 4 - this.rightScroll.getScrollOffset();
/*      */   }
/*      */ 
/*      */   
/*      */   private int renderSpawnsContent(class_332 ctx, class_327 textRenderer, PokemonDetail detail, int cx, int cy, int cw) {
/*  713 */     if (detail.spawns.isEmpty()) {
/*  714 */       ctx.method_51433(textRenderer, "No spawn data available", cx, cy, GuideColors.TEXT_DIM, true);
/*  715 */       return 20;
/*      */     } 
/*      */     
/*  718 */     int badgeH = 11;
/*      */     
/*  720 */     for (int si = 0; si < detail.spawns.size(); si++) {
/*  721 */       PokemonDetail.SpawnInfo spawn = detail.spawns.get(si);
/*      */ 
/*      */       
/*  724 */       int rarityColor = GuideColors.getRarityColor(spawn.rarity);
/*  725 */       String rarityLabel = spawn.rarity.substring(0, 1).toUpperCase() + spawn.rarity.substring(0, 1).toUpperCase();
/*  726 */       int rarityW = textRenderer.method_1727(rarityLabel) + 8;
/*      */ 
/*      */       
/*  729 */       int rarityBg = GuideColors.darken(rarityColor, 60);
/*  730 */       ctx.method_25294(cx, cy, cx + rarityW, cy + badgeH, rarityBg);
/*  731 */       ctx.method_25294(cx, cy, cx + rarityW, cy + 1, rarityColor);
/*  732 */       ctx.method_25294(cx, cy + badgeH - 1, cx + rarityW, cy + badgeH, rarityColor);
/*  733 */       ctx.method_51433(textRenderer, rarityLabel, cx + 4, cy + 2, GuideColors.TEXT_WHITE, true);
/*      */ 
/*      */       
/*  736 */       int afterRarity = cx + rarityW + 5;
/*  737 */       if (!spawn.levelRange.isEmpty()) {
/*  738 */         ctx.method_51433(textRenderer, "Lv. " + spawn.levelRange, afterRarity, cy + 2, GuideColors.TEXT_DIM, true);
/*      */       }
/*      */ 
/*      */       
/*  742 */       if (spawn.context != null && !spawn.context.isEmpty() && !spawn.context.equalsIgnoreCase("grounded")) {
/*  743 */         String ctxLabel = spawn.context.substring(0, 1).toUpperCase() + spawn.context.substring(0, 1).toUpperCase();
/*  744 */         int ctxW = textRenderer.method_1727(ctxLabel) + 6;
/*  745 */         int ctxX = cx + cw - ctxW;
/*  746 */         ctx.method_25294(ctxX, cy, ctxX + ctxW, cy + badgeH, GuideColors.color(40, 50, 60, 200));
/*  747 */         ctx.method_49601(ctxX, cy, ctxW, badgeH, GuideColors.BORDER_DIM);
/*  748 */         ctx.method_51433(textRenderer, ctxLabel, ctxX + 3, cy + 2, GuideColors.TEXT_PRIMARY, true);
/*      */       } 
/*  750 */       cy += badgeH + 4;
/*      */ 
/*      */       
/*  753 */       if (!spawn.biomes.isEmpty()) {
/*  754 */         int tagX = cx;
/*  755 */         for (String biome : spawn.biomes) {
/*  756 */           String biomeDisplay = formatBiomeName(biome);
/*  757 */           int tagW = textRenderer.method_1727(biomeDisplay) + 8;
/*      */ 
/*      */           
/*  760 */           if (tagX + tagW > cx + cw && tagX > cx) {
/*  761 */             tagX = cx;
/*  762 */             cy += badgeH + 2;
/*      */           } 
/*      */ 
/*      */           
/*  766 */           int biomeColor = getBiomeColor(biome);
/*  767 */           int biomeBg = GuideColors.darken(biomeColor, 80);
/*  768 */           ctx.method_25294(tagX, cy, tagX + tagW, cy + badgeH, biomeBg);
/*  769 */           ctx.method_25294(tagX, cy, tagX + tagW, cy + 1, GuideColors.withAlpha(biomeColor, 180));
/*  770 */           ctx.method_25294(tagX, cy + badgeH - 1, tagX + tagW, cy + badgeH, GuideColors.withAlpha(biomeColor, 180));
/*  771 */           ctx.method_51433(textRenderer, biomeDisplay, tagX + 4, cy + 2, GuideColors.TEXT_WHITE, true);
/*      */           
/*  773 */           tagX += tagW + 3;
/*      */         } 
/*  775 */         cy += badgeH + 3;
/*      */       } 
/*      */ 
/*      */       
/*  779 */       if (spawn.conditions != null && !spawn.conditions.isEmpty()) {
/*  780 */         for (String condition : spawn.conditions) {
/*  781 */           ctx.method_51433(textRenderer, "• " + condition, cx + 2, cy, GuideColors.TEXT_DIM, true);
/*  782 */           cy += 10;
/*      */         } 
/*      */       }
/*      */ 
/*      */       
/*  787 */       if (si < detail.spawns.size() - 1) {
/*  788 */         cy += 2;
/*  789 */         ctx.method_25294(cx, cy, cx + cw, cy + 1, GuideColors.BORDER_DIM);
/*  790 */         cy += 5;
/*      */       } 
/*      */     } 
/*      */     
/*  794 */     return cy - this.rightScroll.getY() + 4 - this.rightScroll.getScrollOffset();
/*      */   }
/*      */ 
/*      */   
/*      */   private static String formatBiomeName(String biome) {
/*  799 */     if (biome.startsWith("#")) biome = biome.substring(1); 
/*  800 */     if (biome.startsWith("is_")) biome = biome.substring(3); 
/*  801 */     if (biome.contains(":")) biome = biome.substring(biome.indexOf(':') + 1); 
/*  802 */     String[] words = biome.replace('_', ' ').replace('-', ' ').split(" ");
/*  803 */     StringBuilder sb = new StringBuilder();
/*  804 */     for (String word : words) {
/*  805 */       if (!word.isEmpty()) {
/*  806 */         if (sb.length() > 0) sb.append(' '); 
/*  807 */         sb.append(word.substring(0, 1).toUpperCase()).append(word.substring(1));
/*      */       } 
/*      */     } 
/*  810 */     return sb.toString();
/*      */   }
/*      */ 
/*      */   
/*      */   private static int getBiomeColor(String biome) {
/*  815 */     String lower = biome.toLowerCase();
/*  816 */     if (lower.contains("ocean") || lower.contains("river") || lower.contains("beach") || lower.contains("lake") || lower.contains("deep"))
/*  817 */       return GuideColors.color(80, 140, 210, 255); 
/*  818 */     if (lower.contains("forest") || lower.contains("jungle") || lower.contains("grove") || lower.contains("woodland"))
/*  819 */       return GuideColors.color(70, 170, 90, 255); 
/*  820 */     if (lower.contains("desert") || lower.contains("badlands") || lower.contains("mesa") || lower.contains("savanna"))
/*  821 */       return GuideColors.color(210, 170, 70, 255); 
/*  822 */     if (lower.contains("snow") || lower.contains("frozen") || lower.contains("ice") || lower.contains("glacier") || lower.contains("cold"))
/*  823 */       return GuideColors.color(150, 200, 220, 255); 
/*  824 */     if (lower.contains("mountain") || lower.contains("peak") || lower.contains("hill") || lower.contains("stony") || lower.contains("windswept"))
/*  825 */       return GuideColors.color(150, 150, 165, 255); 
/*  826 */     if (lower.contains("swamp") || lower.contains("marsh") || lower.contains("bog") || lower.contains("mangrove"))
/*  827 */       return GuideColors.color(100, 150, 80, 255); 
/*  828 */     if (lower.contains("cave") || lower.contains("deep_dark") || lower.contains("lush"))
/*  829 */       return GuideColors.color(120, 100, 150, 255); 
/*  830 */     if (lower.contains("nether") || lower.contains("crimson") || lower.contains("warped") || lower.contains("basalt") || lower.contains("soul"))
/*  831 */       return GuideColors.color(190, 70, 70, 255); 
/*  832 */     if (lower.contains("mushroom"))
/*  833 */       return GuideColors.color(180, 90, 170, 255); 
/*  834 */     if (lower.contains("plain") || lower.contains("meadow") || lower.contains("field") || lower.contains("flower"))
/*  835 */       return GuideColors.color(140, 200, 80, 255); 
/*  836 */     if (lower.contains("taiga") || lower.contains("spruce"))
/*  837 */       return GuideColors.color(90, 130, 90, 255); 
/*  838 */     if (lower.contains("cherry"))
/*  839 */       return GuideColors.color(220, 140, 170, 255); 
/*  840 */     if (lower.contains("birch"))
/*  841 */       return GuideColors.color(160, 190, 100, 255); 
/*  842 */     return GuideColors.ACCENT_TEAL;
/*      */   }
/*      */ 
/*      */   
/*      */   private int renderDropsContent(class_332 ctx, class_327 textRenderer, PokemonDetail detail, int cx, int cy, int cw) {
/*  847 */     if (detail.drops.isEmpty()) {
/*  848 */       ctx.method_51433(textRenderer, "No drop data available", cx, cy, GuideColors.TEXT_DIM, true);
/*  849 */       return 20;
/*      */     } 
/*      */     
/*  852 */     for (PokemonDetail.DropInfo drop : detail.drops) {
/*  853 */       ctx.method_51433(textRenderer, drop.item, cx, cy, GuideColors.TEXT_PRIMARY, true);
/*  854 */       String info = String.format("×%s (%.1f%%)", new Object[] { drop.quantityRange, Double.valueOf(drop.chance) });
/*  855 */       ctx.method_51433(textRenderer, info, cx + cw - textRenderer.method_1727(info), cy, GuideColors.TEXT_DIM, true);
/*  856 */       cy += 11;
/*      */     } 
/*      */     
/*  859 */     return cy - this.rightScroll.getY() + 4 - this.rightScroll.getScrollOffset();
/*      */   }
/*      */   public PokemonDetailView() {
/*  862 */     this.hoveredEvoSlug = null;
/*      */ 
/*      */     
/*  865 */     this.evoModelStates = new HashMap<>();
/*      */     PokemonRenderHelper.init();
/*      */     this.pokemonState = new FloatingState();
/*      */   } private int renderEvolutionContent(class_332 ctx, class_327 textRenderer, PokemonDetail detail, int cx, int cy, int cw, int mouseX, int mouseY, float delta) {
/*  869 */     this.hoveredEvoSlug = null;
/*      */ 
/*      */     
/*  872 */     List<PokemonDetail.EvolutionChainEntry> chain = detail.evolutionChain;
/*  873 */     if (chain != null && chain.size() > 1) {
/*  874 */       ctx.method_51433(textRenderer, "Evolution Line", cx, cy, GuideColors.TEXT_WHITE, true);
/*  875 */       cy += 14;
/*      */       
/*  877 */       int modelSize = 26;
/*  878 */       int cardH = modelSize + 4;
/*      */       
/*  880 */       for (int i = 0; i < chain.size(); i++) {
/*  881 */         PokemonDetail.EvolutionChainEntry stage = chain.get(i);
/*      */ 
/*      */ 
/*      */         
/*  885 */         boolean isHovered = (!stage.isCurrent && mouseX >= cx && mouseX < cx + cw && mouseY >= cy && mouseY < cy + cardH && this.rightScroll.isInBounds(mouseX, mouseY));
/*  886 */         if (isHovered) this.hoveredEvoSlug = stage.slug;
/*      */ 
/*      */ 
/*      */         
/*  890 */         int bg = stage.isCurrent ? GuideColors.TAB_ACTIVE_BG : (isHovered ? GuideColors.CARD_HOVER_BG : GuideColors.CARD_BG);
/*      */         
/*  892 */         int border = stage.isCurrent ? GuideColors.ACCENT_EMERALD : (isHovered ? GuideColors.BORDER_HIGHLIGHT : GuideColors.BORDER_DIM);
/*  893 */         ctx.method_25294(cx, cy, cx + cw, cy + cardH, bg);
/*  894 */         ctx.method_49601(cx, cy, cw, cardH, border);
/*      */ 
/*      */         
/*  897 */         renderEvoModel(ctx, stage.slug, cx + 2, cy + 2, modelSize, delta);
/*      */ 
/*      */         
/*  900 */         int textX = cx + modelSize + 6;
/*  901 */         String dexLabel = String.format("#%03d", new Object[] { Integer.valueOf(stage.id) });
/*  902 */         ctx.method_51433(textRenderer, dexLabel, textX, cy + 4, GuideColors.TEXT_DIM, true);
/*      */ 
/*      */         
/*  905 */         int nameColor = stage.isCurrent ? GuideColors.TEXT_ACCENT : (isHovered ? GuideColors.TEXT_WHITE : GuideColors.TEXT_PRIMARY);
/*  906 */         ctx.method_51433(textRenderer, stage.name, textX, cy + 14, nameColor, true);
/*      */ 
/*      */         
/*  909 */         int typeX = textX + textRenderer.method_1727(stage.name) + 4;
/*  910 */         if (stage.types != null) {
/*  911 */           for (String type : stage.types) {
/*  912 */             if (typeX + GuideTypeBadge.getWidth(textRenderer, type) > cx + cw - 14)
/*  913 */               break;  int tw = GuideTypeBadge.draw(ctx, textRenderer, type, typeX, cy + 13);
/*  914 */             typeX += tw + 2;
/*      */           } 
/*      */         }
/*      */ 
/*      */         
/*  919 */         if (stage.isCurrent) {
/*  920 */           ctx.method_51433(textRenderer, "★", cx + cw - 10, cy + cardH / 2 - 3, GuideColors.ACCENT_EMERALD, true);
/*      */         }
/*      */ 
/*      */         
/*  924 */         if (isHovered) {
/*  925 */           ctx.method_51433(textRenderer, "→", cx + cw - 10, cy + cardH / 2 - 3, GuideColors.ACCENT_EMERALD, true);
/*      */         }
/*      */         
/*  928 */         cy += cardH + 2;
/*      */ 
/*      */         
/*  931 */         if (i < chain.size() - 1) {
/*  932 */           String method = ((PokemonDetail.EvolutionChainEntry)chain.get(i)).methodToNext;
/*  933 */           if (!method.isEmpty()) {
/*  934 */             String methodText = "↓ " + method;
/*  935 */             if (textRenderer.method_1727(methodText) > cw - 10) {
/*  936 */               methodText = "↓ " + method.substring(0, Math.min(method.length(), 28)) + "..";
/*      */             }
/*  938 */             int methodW = textRenderer.method_1727(methodText) + 10;
/*  939 */             int methodX = cx + (cw - methodW) / 2;
/*  940 */             ctx.method_25294(methodX, cy, methodX + methodW, cy + 12, GuideColors.color(30, 45, 50, 220));
/*  941 */             ctx.method_49601(methodX, cy, methodW, 12, GuideColors.color(46, 150, 130, 150));
/*  942 */             ctx.method_25300(textRenderer, methodText, cx + cw / 2, cy + 2, GuideColors.ACCENT_TEAL);
/*  943 */             cy += 15;
/*      */           } else {
/*  945 */             ctx.method_25300(textRenderer, "↓", cx + cw / 2, cy, GuideColors.TEXT_DIM);
/*  946 */             cy += 12;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/*  951 */       return cy - this.rightScroll.getY() + 4 - this.rightScroll.getScrollOffset();
/*      */     } 
/*      */ 
/*      */     
/*  955 */     ctx.method_51433(textRenderer, "No evolution data available", cx, cy, GuideColors.TEXT_DIM, true);
/*  956 */     cy += 12;
/*      */     
/*  958 */     return cy - this.rightScroll.getY() + 4 - this.rightScroll.getScrollOffset();
/*      */   }
/*      */ 
/*      */   
/*      */   private void renderEvoModel(class_332 ctx, String slug, int sx, int sy, int ss, float delta) {
/*  963 */     if (!PokemonRenderHelper.isAvailable() || slug == null || slug.isEmpty()) {
/*  964 */       ctx.method_25294(sx, sy, sx + ss, sy + ss, GuideColors.color(20, 25, 30, 200));
/*      */       return;
/*      */     } 
/*      */     try {
/*  968 */       class_2960 speciesId = resolveSpeciesId(slug);
/*  969 */       FloatingState state = this.evoModelStates.computeIfAbsent(slug, k -> new FloatingState());
/*  970 */       state.setCurrentAspects(Set.of());
/*      */       
/*  972 */       class_4587 mat = ctx.method_51448();
/*  973 */       mat.method_22903();
/*  974 */       mat.method_22904(sx + ss / 2.0D, sy + 2.0D, 0.0D);
/*  975 */       float scale = ss / 9.0F;
/*  976 */       mat.method_22905(scale, scale, 1.0F);
/*      */       
/*  978 */       Quaternionf rot = new Quaternionf();
/*  979 */       rot.rotationXYZ((float)Math.toRadians(13.0D), (float)Math.toRadians(35.0D), 0.0F);
/*      */       
/*  981 */       PokemonRenderHelper.draw(speciesId, mat, rot, state, delta);
/*  982 */       mat.method_22909();
/*  983 */     } catch (Exception e) {
/*  984 */       ctx.method_25294(sx, sy, sx + ss, sy + ss, GuideColors.color(20, 25, 30, 200));
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private int renderFormsContent(class_332 ctx, class_327 textRenderer, PokemonDetail detail, int cx, int cy, int cw, int mouseX, int mouseY) {
/*  990 */     boolean hasContent = false;
/*  991 */     this.hoveredFormIndex = -1;
/*  992 */     this.hoveredSkinIndex = -1;
/*      */ 
/*      */     
/*  995 */     if (!detail.forms.isEmpty()) {
/*  996 */       hasContent = true;
/*  997 */       ctx.method_51433(textRenderer, "Alternate Forms", cx, cy, GuideColors.TEXT_WHITE, true);
/*  998 */       ctx.method_51433(textRenderer, "(click to view)", cx + textRenderer.method_1727("Alternate Forms") + 6, cy, GuideColors.TEXT_DIM, true);
/*      */       
/* 1000 */       cy += 14;
/*      */       
/* 1002 */       for (int fi = 0; fi < detail.forms.size(); fi++) {
/* 1003 */         PokemonDetail.FormInfo form = detail.forms.get(fi);
/*      */         
/* 1005 */         int cardH = 18;
/* 1006 */         boolean isSelected = (fi == this.selectedFormIndex);
/*      */ 
/*      */         
/* 1009 */         boolean isHovered = (mouseX >= cx && mouseX < cx + cw && mouseY >= cy && mouseY < cy + cardH && this.rightScroll.isInBounds(mouseX, mouseY));
/* 1010 */         if (isHovered) this.hoveredFormIndex = fi;
/*      */ 
/*      */ 
/*      */         
/* 1014 */         int cardBg = isSelected ? GuideColors.TAB_ACTIVE_BG : (isHovered ? GuideColors.CARD_HOVER_BG : GuideColors.CARD_BG);
/*      */         
/* 1016 */         int borderColor = isSelected ? GuideColors.ACCENT_EMERALD : (isHovered ? GuideColors.BORDER_HIGHLIGHT : GuideColors.BORDER_DIM);
/* 1017 */         ctx.method_25294(cx, cy, cx + cw, cy + cardH, cardBg);
/* 1018 */         ctx.method_49601(cx, cy, cw, cardH, borderColor);
/*      */ 
/*      */         
/* 1021 */         int nameColor = isSelected ? GuideColors.TEXT_ACCENT : GuideColors.TEXT_PRIMARY;
/* 1022 */         ctx.method_51433(textRenderer, form.name, cx + 4, cy + 5, nameColor, true);
/*      */ 
/*      */         
/* 1025 */         if (form.types != null && !form.types.isEmpty()) {
/* 1026 */           int typeX = cx + 4 + textRenderer.method_1727(form.name) + 6;
/* 1027 */           for (String type : form.types) {
/* 1028 */             int w = GuideTypeBadge.draw(ctx, textRenderer, type, typeX, cy + 4);
/* 1029 */             typeX += w + 2;
/*      */           } 
/*      */         } 
/*      */ 
/*      */         
/* 1034 */         if (isHovered) {
/* 1035 */           ctx.method_51433(textRenderer, "→", cx + cw - 10, cy + 5, GuideColors.ACCENT_EMERALD, true);
/*      */         }
/*      */         
/* 1038 */         cy += cardH + 3;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1043 */     if (!detail.skins.isEmpty()) {
/* 1044 */       if (hasContent) cy += 8; 
/* 1045 */       hasContent = true;
/*      */       
/* 1047 */       ctx.method_51433(textRenderer, "Skins", cx, cy, GuideColors.TEXT_WHITE, true);
/* 1048 */       ctx.method_51433(textRenderer, "(click to preview)", cx + textRenderer.method_1727("Skins") + 6, cy, GuideColors.TEXT_DIM, true);
/*      */       
/* 1050 */       cy += 14;
/*      */       
/* 1052 */       for (int si = 0; si < detail.skins.size(); si++) {
/* 1053 */         PokemonDetail.SkinInfo skin = detail.skins.get(si);
/*      */         
/* 1055 */         boolean isSelected = (si == this.selectedSkinIndex);
/*      */ 
/*      */         
/* 1058 */         boolean isHovered = (mouseX >= cx && mouseX < cx + cw && mouseY >= cy && mouseY < cy + 20 && this.rightScroll.isInBounds(mouseX, mouseY));
/* 1059 */         if (isHovered) this.hoveredSkinIndex = si;
/*      */ 
/*      */ 
/*      */         
/* 1063 */         int cardBg = isSelected ? GuideColors.TAB_ACTIVE_BG : (isHovered ? GuideColors.CARD_HOVER_BG : GuideColors.CARD_BG);
/*      */         
/* 1065 */         int borderColor = isSelected ? GuideColors.ACCENT_EMERALD : (isHovered ? GuideColors.BORDER_HIGHLIGHT : GuideColors.BORDER_DIM);
/* 1066 */         ctx.method_25294(cx, cy, cx + cw, cy + 20, cardBg);
/* 1067 */         ctx.method_49601(cx, cy, cw, 20, borderColor);
/*      */ 
/*      */         
/* 1070 */         if (!skin.color.isEmpty()) {
/*      */           try {
/* 1072 */             int colorVal = (int)Long.parseLong(skin.color.replace("#", ""), 16);
/* 1073 */             int r = colorVal >> 16 & 0xFF;
/* 1074 */             int g = colorVal >> 8 & 0xFF;
/* 1075 */             int b = colorVal & 0xFF;
/* 1076 */             int argb = GuideColors.color(r, g, b, 255);
/* 1077 */             ctx.method_25294(cx + 4, cy + 6, cx + 12, cy + 14, argb);
/* 1078 */             ctx.method_49601(cx + 4, cy + 6, 8, 8, GuideColors.BORDER_DIM);
/* 1079 */           } catch (Exception exception) {}
/*      */         }
/*      */ 
/*      */         
/* 1083 */         String skinName = skin.name.isEmpty() ? skin.identifier : skin.name;
/*      */         
/* 1085 */         int nameColor = isSelected ? GuideColors.TEXT_ACCENT : (isHovered ? GuideColors.TEXT_WHITE : GuideColors.TEXT_PRIMARY);
/* 1086 */         ctx.method_51433(textRenderer, skinName, cx + 16, cy + 6, nameColor, true);
/*      */ 
/*      */         
/* 1089 */         if (isSelected) {
/* 1090 */           ctx.method_51433(textRenderer, "✔", cx + cw - 10, cy + 6, GuideColors.ACCENT_EMERALD, true);
/*      */         }
/*      */         
/* 1093 */         cy += 24;
/*      */       } 
/*      */     } 
/*      */     
/* 1097 */     if (!hasContent) {
/* 1098 */       ctx.method_51433(textRenderer, "No forms or skins available", cx, cy, GuideColors.TEXT_DIM, true);
/* 1099 */       return 20;
/*      */     } 
/*      */     
/* 1102 */     return cy - this.rightScroll.getY() + 4 - this.rightScroll.getScrollOffset();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean mouseClicked(double mouseX, double mouseY, int button) {
/* 1109 */     if (this.modelViewer.isVisible()) {
/* 1110 */       return this.modelViewer.mouseClicked(mouseX, mouseY, button, this.screenWidth, this.screenHeight);
/*      */     }
/*      */ 
/*      */     
/* 1114 */     if (button == 0 && this.backHovered && this.onBack != null) {
/* 1115 */       GuideSounds.playClick();
/* 1116 */       this.onBack.run();
/* 1117 */       return true;
/*      */     } 
/*      */ 
/*      */     
/* 1121 */     if (button == 0 && this.shinyButtonHovered) {
/* 1122 */       GuideSounds.playClick();
/* 1123 */       this.isShinyToggled = !this.isShinyToggled;
/* 1124 */       this.pokemonState = new FloatingState();
/* 1125 */       return true;
/*      */     } 
/*      */ 
/*      */     
/* 1129 */     if (button == 0 && this.modelBoxHovered) {
/* 1130 */       GuideSounds.playNavigate();
/* 1131 */       PokemonDetail detail = GuideData.getInstance().getCurrentPokemonDetail();
/* 1132 */       if (detail != null) {
/* 1133 */         class_2960 speciesId = resolveSpeciesId(detail.slug);
/*      */         
/* 1135 */         Set<String> viewerAspects = Set.of();
/* 1136 */         if (this.selectedSkinIndex >= 0 && this.selectedSkinIndex < detail.skins.size()) {
/* 1137 */           PokemonDetail.SkinInfo skin = detail.skins.get(this.selectedSkinIndex);
/* 1138 */           if (skin.aspects != null && !skin.aspects.isEmpty()) {
/* 1139 */             viewerAspects = new HashSet<>(skin.aspects);
/*      */           }
/* 1141 */         } else if (this.selectedFormIndex >= 0 && this.selectedFormIndex < detail.forms.size()) {
/* 1142 */           PokemonDetail.FormInfo form = detail.forms.get(this.selectedFormIndex);
/* 1143 */           if (form.aspects != null && !form.aspects.isEmpty()) {
/* 1144 */             viewerAspects = new HashSet<>(form.aspects);
/*      */           }
/*      */         } 
/*      */         
/* 1148 */         if (this.isShinyToggled) {
/* 1149 */           if (!(viewerAspects instanceof HashSet))
/* 1150 */             viewerAspects = new HashSet<>(viewerAspects); 
/* 1151 */           viewerAspects.add("shiny");
/*      */         } 
/* 1153 */         this.modelViewer.open(speciesId, this.pokemonState, viewerAspects, () -> { 
/* 1154 */             }); return true;
/*      */       } 
/*      */     } 
/*      */     
/* 1158 */     if (button != 0) return false;
/*      */ 
/*      */     
/* 1161 */     if (this.hoveredType != null && this.onTypeSelected != null) {
/* 1162 */       GuideSounds.playClick();
/* 1163 */       this.onTypeSelected.accept(this.hoveredType);
/* 1164 */       return true;
/*      */     } 
/*      */ 
/*      */     
/* 1168 */     if (this.selectedFormIndex >= 0) {
/* 1169 */       PokemonDetail detail2 = GuideData.getInstance().getCurrentPokemonDetail();
/* 1170 */       if (detail2 != null) {
/* 1171 */         int leftX = this.x + 4;
/* 1172 */         int leftW = this.width / 2 - 6;
/* 1173 */         int leftY = this.y + 20 + 12;
/* 1174 */         String resetLabel = "✕ Base";
/* 1175 */         int resetW = 6 * resetLabel.length() + 6;
/* 1176 */         int resetX = leftX + leftW - resetW - 4;
/* 1177 */         if (mouseX >= resetX && mouseX < (resetX + resetW) && mouseY >= (leftY - 1) && mouseY < (leftY + 11)) {
/*      */           
/* 1179 */           this.selectedFormIndex = -1;
/* 1180 */           return true;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1186 */     if (this.hoveredSubTab >= 0 && this.hoveredSubTab < SUB_TABS.length) {
/* 1187 */       GuideSounds.playTabClick();
/* 1188 */       this.activeSubTab = this.hoveredSubTab;
/* 1189 */       this.rightScroll.resetScroll();
/* 1190 */       return true;
/*      */     } 
/*      */ 
/*      */     
/* 1194 */     if (this.activeSubTab == 4 && 
/* 1195 */       this.hoveredEvoSlug != null && !this.hoveredEvoSlug.isEmpty()) {
/* 1196 */       GuideSounds.playNavigate();
/* 1197 */       requestDetailWithSubTab(this.hoveredEvoSlug, 4);
/* 1198 */       return true;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1203 */     if (this.activeSubTab == 1 && this.hoveredMoveTab >= 0 && this.hoveredMoveTab < MOVE_TABS.length) {
/* 1204 */       GuideSounds.playTabClick();
/* 1205 */       this.activeMoveTab = this.hoveredMoveTab;
/* 1206 */       this.rightScroll.resetScroll();
/* 1207 */       return true;
/*      */     } 
/*      */ 
/*      */     
/* 1211 */     if (this.activeSubTab == 1 && this.hoveredMoveRowIndex >= 0 && this.onMoveSelected != null) {
/* 1212 */       PokemonDetail detail2 = GuideData.getInstance().getCurrentPokemonDetail();
/* 1213 */       if (detail2 != null) {
/* 1214 */         switch (this.activeMoveTab) { case 0: 
/*      */           case 1: 
/*      */           case 2: 
/*      */           case 3: 
/*      */           default:
/* 1219 */             break; }  List<?> moves = List.of();
/*      */         
/* 1221 */         if (this.hoveredMoveRowIndex < moves.size()) {
/* 1222 */           Object move = moves.get(this.hoveredMoveRowIndex);
/* 1223 */           String moveName = "";
/* 1224 */           if (move instanceof PokemonDetail.LevelMove) { PokemonDetail.LevelMove lm = (PokemonDetail.LevelMove)move;
/* 1225 */             moveName = lm.name; }
/* 1226 */           else if (move instanceof String) { String name = (String)move;
/* 1227 */             moveName = name; }
/*      */           
/* 1229 */           if (!moveName.isEmpty()) {
/* 1230 */             GuideSounds.playClick();
/* 1231 */             this.onMoveSelected.accept(moveName);
/* 1232 */             return true;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1239 */     if (this.activeSubTab == 5 && this.hoveredFormIndex >= 0) {
/* 1240 */       PokemonDetail detail2 = GuideData.getInstance().getCurrentPokemonDetail();
/* 1241 */       if (detail2 != null && this.hoveredFormIndex < detail2.forms.size()) {
/* 1242 */         GuideSounds.playClick();
/*      */         
/* 1244 */         if (this.selectedFormIndex == this.hoveredFormIndex) {
/* 1245 */           this.selectedFormIndex = -1;
/*      */         } else {
/* 1247 */           this.selectedFormIndex = this.hoveredFormIndex;
/* 1248 */           this.selectedSkinIndex = -1;
/*      */         } 
/* 1250 */         this.pokemonState = new FloatingState();
/*      */         
/* 1252 */         this.activeSubTab = 0;
/* 1253 */         this.rightScroll.resetScroll();
/* 1254 */         return true;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1259 */     if (this.activeSubTab == 5 && this.hoveredSkinIndex >= 0) {
/* 1260 */       PokemonDetail detail2 = GuideData.getInstance().getCurrentPokemonDetail();
/* 1261 */       if (detail2 != null && this.hoveredSkinIndex < detail2.skins.size()) {
/* 1262 */         GuideSounds.playClick();
/*      */         
/* 1264 */         if (this.selectedSkinIndex == this.hoveredSkinIndex) {
/* 1265 */           this.selectedSkinIndex = -1;
/*      */         } else {
/* 1267 */           this.selectedSkinIndex = this.hoveredSkinIndex;
/* 1268 */           this.selectedFormIndex = -1;
/*      */         } 
/* 1270 */         this.pokemonState = new FloatingState();
/* 1271 */         return true;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1276 */     return this.rightScroll.handleMouseClicked(mouseX, mouseY, button);
/*      */   }
/*      */   
/*      */   public boolean mouseScrolled(double mouseX, double mouseY, double horizontal, double vertical) {
/* 1280 */     if (this.modelViewer.isVisible()) {
/* 1281 */       return this.modelViewer.mouseScrolled(mouseX, mouseY, vertical);
/*      */     }
/* 1283 */     return this.rightScroll.handleScroll(mouseX, mouseY, vertical);
/*      */   }
/*      */   
/*      */   public boolean mouseDragged(double mouseX, double mouseY, int button) {
/* 1287 */     if (this.modelViewer.isVisible()) {
/* 1288 */       return this.modelViewer.mouseDragged(mouseX, mouseY, button);
/*      */     }
/* 1290 */     return this.rightScroll.handleMouseDragged(mouseX, mouseY, button);
/*      */   }
/*      */   
/*      */   public boolean mouseReleased(double mouseX, double mouseY, int button) {
/* 1294 */     if (this.modelViewer.isVisible()) {
/* 1295 */       return this.modelViewer.mouseReleased(mouseX, mouseY, button);
/*      */     }
/* 1297 */     return this.rightScroll.handleMouseReleased(mouseX, mouseY, button);
/*      */   }
/*      */   
/*      */   public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
/* 1301 */     if (this.modelViewer.isVisible()) {
/* 1302 */       return this.modelViewer.keyPressed(keyCode, scanCode, modifiers);
/*      */     }
/* 1304 */     return false;
/*      */   }
/*      */   public boolean charTyped(char chr, int modifiers) {
/* 1307 */     return false;
/*      */   }
/*      */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\guide\screen\views\PokemonDetailView.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */