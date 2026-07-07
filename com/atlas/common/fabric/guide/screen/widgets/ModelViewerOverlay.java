/*     */ package com.atlas.common.fabric.guide.screen.widgets;
/*     */ 
/*     */ import com.atlas.common.fabric.guide.screen.GuideColors;
/*     */ import com.atlas.common.fabric.screen.PokemonRenderHelper;
/*     */ import com.atlas.common.fabric.skindex.data.SkinDexDetailInfo;
/*     */ import com.cobblemon.mod.common.client.render.ModelAssetVariation;
/*     */ import com.cobblemon.mod.common.client.render.VaryingRenderableResolver;
/*     */ import com.cobblemon.mod.common.client.render.models.blockbench.FloatingState;
/*     */ import com.cobblemon.mod.common.client.render.models.blockbench.repository.VaryingModelRepository;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashSet;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import net.minecraft.class_2960;
/*     */ import net.minecraft.class_327;
/*     */ import net.minecraft.class_332;
/*     */ import net.minecraft.class_4587;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ import org.joml.Quaternionf;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ModelViewerOverlay
/*     */ {
/*  31 */   private static final Logger LOGGER = LoggerFactory.getLogger(ModelViewerOverlay.class);
/*     */   private static final int MODEL_BOX_SIZE = 200;
/*     */   private static final int SHINY_BTN_SIZE = 14;
/*     */   private static final int AURA_BTN_SIZE = 14;
/*     */   private static final int AURA_DROPDOWN_ITEM_HEIGHT = 14;
/*  36 */   private int computedDropdownWidth = 90;
/*     */   
/*     */   private boolean visible = false;
/*     */   
/*     */   private class_2960 speciesId;
/*     */   private FloatingState floatingState;
/*  42 */   private Set<String> aspects = Set.of();
/*  43 */   private Set<String> baseAspects = Set.of();
/*     */   
/*     */   private Runnable onClose;
/*     */   
/*     */   private boolean shinyEnabled = false;
/*     */   
/*     */   private boolean shinyButtonHovered = false;
/*     */   
/*  51 */   private List<SkinDexDetailInfo.AuraEntry> availableAuras = List.of();
/*  52 */   private int selectedAuraIdx = -1;
/*  53 */   private String baseDisplayAspect = null;
/*     */   private boolean auraDropdownOpen = false;
/*     */   private boolean auraButtonHovered = false;
/*  56 */   private int hoveredAuraOptionIdx = -1;
/*     */   
/*     */   private static final int FORM_BTN_SIZE = 14;
/*     */   
/*     */   private static final int FORM_DROPDOWN_ITEM_HEIGHT = 14;
/*  61 */   private List<FormEntry> availableForms = List.of();
/*  62 */   private int selectedFormIdx = -1;
/*     */   private boolean formDropdownOpen = false;
/*     */   private boolean formButtonHovered = false;
/*  65 */   private int hoveredFormOptionIdx = -1;
/*  66 */   private int computedFormDropdownWidth = 90;
/*  67 */   private String pokemonSlug = null;
/*     */ 
/*     */   
/*  70 */   private float rotationYaw = 35.0F;
/*  71 */   private float rotationPitch = 13.0F;
/*     */ 
/*     */   
/*  74 */   private float zoomLevel = 1.0F;
/*     */   
/*     */   private static final float MIN_ZOOM = 0.4F;
/*     */   
/*     */   private static final float MAX_ZOOM = 3.0F;
/*  79 */   private float panX = 0.0F;
/*  80 */   private float panY = 0.0F;
/*     */   
/*     */   private static final float MAX_PAN = 100.0F;
/*     */   
/*     */   private boolean dragging = false;
/*  85 */   private int dragButton = -1; private double lastDragX;
/*     */   private double lastDragY;
/*     */   
/*     */   static {
/*  89 */     PokemonRenderHelper.init();
/*     */   } public static final class FormEntry extends Record {
/*     */     private final String name; private final List<String> aspects; public final String toString() {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/guide/screen/widgets/ModelViewerOverlay$FormEntry;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #95	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/guide/screen/widgets/ModelViewerOverlay$FormEntry;
/*     */     } public final int hashCode() {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/guide/screen/widgets/ModelViewerOverlay$FormEntry;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #95	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/guide/screen/widgets/ModelViewerOverlay$FormEntry;
/*  95 */     } public FormEntry(String name, List<String> aspects) { this.name = name; this.aspects = aspects; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/guide/screen/widgets/ModelViewerOverlay$FormEntry;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #95	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lcom/atlas/common/fabric/guide/screen/widgets/ModelViewerOverlay$FormEntry;
/*  95 */       //   0	8	1	o	Ljava/lang/Object; } public String name() { return this.name; } public List<String> aspects() { return this.aspects; }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void open(class_2960 speciesId, FloatingState state, Set<String> aspects, Runnable onClose) {
/* 103 */     open(speciesId, state, aspects, List.of(), null, -1, null, onClose);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void open(class_2960 speciesId, FloatingState state, Set<String> aspects, @Nullable List<SkinDexDetailInfo.AuraEntry> auras, @Nullable String baseDisplayAspect, int initialAuraIdx, Runnable onClose) {
/* 112 */     open(speciesId, state, aspects, auras, baseDisplayAspect, initialAuraIdx, null, onClose);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void open(class_2960 speciesId, FloatingState state, Set<String> aspects, @Nullable List<SkinDexDetailInfo.AuraEntry> auras, @Nullable String baseDisplayAspect, int initialAuraIdx, @Nullable String pokemonSlug, Runnable onClose) {
/* 131 */     this.speciesId = speciesId;
/* 132 */     this.floatingState = state;
/* 133 */     this.baseAspects = (aspects != null) ? new HashSet<>(aspects) : new HashSet<>();
/* 134 */     this.shinyEnabled = this.baseAspects.contains("shiny");
/* 135 */     this.aspects = new HashSet<>(this.baseAspects);
/* 136 */     this.onClose = onClose;
/* 137 */     this.visible = true;
/* 138 */     this.rotationYaw = 35.0F;
/* 139 */     this.rotationPitch = 13.0F;
/* 140 */     this.zoomLevel = 1.0F;
/* 141 */     this.panX = 0.0F;
/* 142 */     this.panY = 0.0F;
/* 143 */     this.dragging = false;
/* 144 */     this.dragButton = -1;
/* 145 */     this.shinyButtonHovered = false;
/*     */ 
/*     */     
/* 148 */     this.availableAuras = (auras != null) ? auras : List.<SkinDexDetailInfo.AuraEntry>of();
/* 149 */     this.baseDisplayAspect = baseDisplayAspect;
/* 150 */     this.selectedAuraIdx = initialAuraIdx;
/* 151 */     this.auraDropdownOpen = false;
/* 152 */     this.auraButtonHovered = false;
/* 153 */     this.hoveredAuraOptionIdx = -1;
/*     */ 
/*     */     
/* 156 */     this.pokemonSlug = pokemonSlug;
/* 157 */     this.selectedFormIdx = -1;
/* 158 */     this.formDropdownOpen = false;
/* 159 */     this.formButtonHovered = false;
/* 160 */     this.hoveredFormOptionIdx = -1;
/* 161 */     this.availableForms = lookupForms(pokemonSlug);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 166 */     if (initialAuraIdx >= 0 && baseDisplayAspect != null && initialAuraIdx < this.availableAuras
/* 167 */       .size()) {
/* 168 */       this.baseAspects.remove(((SkinDexDetailInfo.AuraEntry)this.availableAuras.get(initialAuraIdx)).aspect());
/* 169 */       this.baseAspects.add(baseDisplayAspect);
/* 170 */       if (!this.shinyEnabled) this.baseAspects.remove("shiny"); 
/* 171 */       rebuildAspects();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private List<FormEntry> lookupForms(@Nullable String slug) {
/* 182 */     if (this.speciesId == null) return List.of(); 
/*     */     try {
/* 184 */       VaryingRenderableResolver resolver = (VaryingRenderableResolver)VaryingModelRepository.INSTANCE.getVariations().get(this.speciesId);
/* 185 */       if (resolver == null) return List.of();
/*     */ 
/*     */       
/* 188 */       Set<String> baseAspectSet = new HashSet<>(this.baseAspects);
/* 189 */       baseAspectSet.remove("shiny");
/*     */ 
/*     */ 
/*     */       
/* 193 */       Map<String, List<String>> formMap = new LinkedHashMap<>();
/*     */       
/* 195 */       for (ModelAssetVariation variation : resolver.getVariations()) {
/* 196 */         Set<String> varAspects = variation.getAspects();
/* 197 */         if (varAspects == null || varAspects.isEmpty()) {
/*     */           continue;
/*     */         }
/* 200 */         if (!varAspects.containsAll(baseAspectSet)) {
/*     */           continue;
/*     */         }
/* 203 */         Set<String> extras = new HashSet<>(varAspects);
/* 204 */         extras.removeAll(baseAspectSet);
/* 205 */         extras.remove("shiny");
/* 206 */         extras.remove("male");
/* 207 */         extras.remove("female");
/*     */         
/* 209 */         if (extras.isEmpty()) {
/*     */           continue;
/*     */         }
/* 212 */         List<String> extrasList = new ArrayList<>(extras);
/* 213 */         Collections.sort(extrasList);
/* 214 */         String key = String.join("+", (Iterable)extrasList);
/*     */         
/* 216 */         if (!formMap.containsKey(key)) {
/* 217 */           formMap.put(key, extrasList);
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 222 */       List<FormEntry> forms = new ArrayList<>();
/* 223 */       for (Map.Entry<String, List<String>> entry : formMap.entrySet()) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 231 */         String displayName = ((List)entry.getValue()).stream().map(a -> { String n = a.replace('-', ' ').replace('_', ' '); return "" + Character.toUpperCase(n.charAt(0)) + Character.toUpperCase(n.charAt(0)); }).reduce((a, b) -> a + " " + a).orElse(entry.getKey());
/* 232 */         forms.add(new FormEntry(displayName, entry.getValue()));
/*     */       } 
/* 234 */       return forms;
/* 235 */     } catch (Exception e) {
/* 236 */       LOGGER.warn("[ModelViewer] Failed to lookup forms from resolver for species '{}'", this.speciesId, e);
/* 237 */       return List.of();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void close() {
/* 242 */     this.visible = false;
/* 243 */     this.dragging = false;
/* 244 */     this.auraDropdownOpen = false;
/* 245 */     this.formDropdownOpen = false;
/* 246 */     if (this.onClose != null) {
/* 247 */       this.onClose.run();
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isVisible() {
/* 252 */     return this.visible;
/*     */   }
/*     */   
/*     */   private void rebuildAspects() {
/* 256 */     Set<String> newAspects = new HashSet<>(this.baseAspects);
/* 257 */     if (this.shinyEnabled) {
/* 258 */       newAspects.add("shiny");
/*     */     } else {
/* 260 */       newAspects.remove("shiny");
/*     */     } 
/*     */     
/* 263 */     if (this.selectedAuraIdx >= 0 && this.selectedAuraIdx < this.availableAuras.size() && this.baseDisplayAspect != null) {
/*     */       
/* 265 */       newAspects.remove(this.baseDisplayAspect);
/* 266 */       newAspects.add(((SkinDexDetailInfo.AuraEntry)this.availableAuras.get(this.selectedAuraIdx)).aspect());
/*     */     } 
/*     */ 
/*     */     
/* 270 */     for (FormEntry form : this.availableForms) {
/* 271 */       newAspects.removeAll(form.aspects());
/*     */     }
/* 273 */     if (this.selectedFormIdx >= 0 && this.selectedFormIdx < this.availableForms.size()) {
/* 274 */       newAspects.addAll(((FormEntry)this.availableForms.get(this.selectedFormIdx)).aspects());
/*     */     }
/* 276 */     this.aspects = newAspects;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(class_332 ctx, class_327 textRenderer, int screenWidth, int screenHeight, int mouseX, int mouseY, float delta) {
/* 283 */     if (!this.visible) {
/*     */       return;
/*     */     }
/* 286 */     ctx.method_51448().method_22903();
/* 287 */     ctx.method_51448().method_46416(0.0F, 0.0F, 200.0F);
/*     */ 
/*     */     
/* 290 */     ctx.method_25294(0, 0, screenWidth, screenHeight, GuideColors.color(0, 0, 0, 180));
/*     */ 
/*     */     
/* 293 */     int boxX = (screenWidth - 200) / 2;
/* 294 */     int boxY = (screenHeight - 200) / 2;
/*     */ 
/*     */     
/* 297 */     ctx.method_25294(boxX - 2, boxY - 2, boxX + 200 + 2, boxY + 200 + 2, GuideColors.BORDER);
/* 298 */     ctx.method_25294(boxX, boxY, boxX + 200, boxY + 200, GuideColors.CARD_BG);
/*     */ 
/*     */     
/* 301 */     if (PokemonRenderHelper.isAvailable() && this.speciesId != null && this.floatingState != null) {
/* 302 */       ctx.method_44379(boxX, boxY, boxX + 200, boxY + 200);
/*     */       
/*     */       try {
/* 305 */         this.floatingState.setCurrentAspects(this.aspects);
/*     */         
/* 307 */         class_4587 mat = ctx.method_51448();
/* 308 */         mat.method_22903();
/* 309 */         mat.method_22904(boxX + 100.0D + this.panX, ((boxY + 10) + this.panY), 0.0D);
/* 310 */         float scale = 20.0F * this.zoomLevel;
/* 311 */         mat.method_22905(scale, scale, 1.0F);
/*     */         
/* 313 */         Quaternionf rot = new Quaternionf();
/* 314 */         rot.rotationXYZ((float)Math.toRadians(this.rotationPitch), (float)Math.toRadians(this.rotationYaw), 0.0F);
/*     */         
/* 316 */         PokemonRenderHelper.draw(this.speciesId, mat, rot, this.floatingState, delta);
/* 317 */         mat.method_22909();
/* 318 */       } catch (Exception e) {
/* 319 */         LOGGER.error("[ModelViewer] Model render failed for species='{}', aspects={}: {}", new Object[] { this.speciesId, this.aspects, e
/* 320 */               .getMessage(), e });
/*     */       } 
/*     */       
/* 323 */       ctx.method_44380();
/*     */     } 
/*     */ 
/*     */     
/* 327 */     int shinyX = boxX + 4;
/* 328 */     int shinyY = boxY + 4;
/* 329 */     this.shinyButtonHovered = (mouseX >= shinyX && mouseX < shinyX + 14 && mouseY >= shinyY && mouseY < shinyY + 14);
/*     */ 
/*     */ 
/*     */     
/* 333 */     int shinyBg = this.shinyEnabled ? GuideColors.color(200, 180, 50, 220) : (this.shinyButtonHovered ? GuideColors.color(60, 60, 50, 220) : GuideColors.color(35, 35, 35, 200));
/* 334 */     ctx.method_25294(shinyX, shinyY, shinyX + 14, shinyY + 14, shinyBg);
/* 335 */     ctx.method_49601(shinyX, shinyY, 14, 14, 
/* 336 */         this.shinyEnabled ? GuideColors.color(255, 220, 50, 255) : GuideColors.color(80, 80, 60, 200));
/*     */     
/* 338 */     int starColor = this.shinyEnabled ? GuideColors.color(255, 255, 100, 255) : (this.shinyButtonHovered ? GuideColors.color(180, 170, 100, 255) : GuideColors.color(120, 120, 90, 220));
/* 339 */     String starChar = "★";
/* 340 */     int starW = textRenderer.method_1727(starChar);
/* 341 */     ctx.method_51433(textRenderer, starChar, shinyX + (14 - starW) / 2, shinyY + 3, starColor, false);
/*     */ 
/*     */     
/* 344 */     if (!this.availableAuras.isEmpty()) {
/* 345 */       int auraX = boxX + 4;
/* 346 */       int auraY = shinyY + 14 + 4;
/* 347 */       boolean auraActive = (this.selectedAuraIdx >= 0);
/*     */       
/* 349 */       this.auraButtonHovered = (mouseX >= auraX && mouseX < auraX + 14 && mouseY >= auraY && mouseY < auraY + 14);
/*     */ 
/*     */ 
/*     */       
/* 353 */       int auraBg = auraActive ? GuideColors.color(128, 0, 128, 220) : ((this.auraButtonHovered || this.auraDropdownOpen) ? GuideColors.color(60, 30, 60, 220) : GuideColors.color(35, 35, 35, 200));
/* 354 */       ctx.method_25294(auraX, auraY, auraX + 14, auraY + 14, auraBg);
/* 355 */       ctx.method_49601(auraX, auraY, 14, 14, 
/* 356 */           auraActive ? GuideColors.color(200, 120, 255, 255) : GuideColors.color(80, 60, 80, 200));
/*     */       
/* 358 */       int diamondColor = auraActive ? GuideColors.color(220, 130, 255, 255) : ((this.auraButtonHovered || this.auraDropdownOpen) ? GuideColors.color(160, 100, 180, 255) : GuideColors.color(120, 90, 120, 220));
/* 359 */       String diamondChar = "◆";
/* 360 */       int diamondW = textRenderer.method_1727(diamondChar);
/* 361 */       int diamondDrawX = auraX + (14 - diamondW) / 2;
/* 362 */       int diamondDrawY = auraY + 3;
/* 363 */       ctx.method_51433(textRenderer, diamondChar, diamondDrawX, diamondDrawY, diamondColor, false);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 369 */     if (!this.availableForms.isEmpty()) {
/* 370 */       int formX = boxX + 4;
/* 371 */       int formY = shinyY + 14 + 4;
/* 372 */       if (!this.availableAuras.isEmpty()) {
/* 373 */         formY += 18;
/*     */       }
/* 375 */       boolean formActive = (this.selectedFormIdx >= 0);
/*     */       
/* 377 */       this.formButtonHovered = (mouseX >= formX && mouseX < formX + 14 && mouseY >= formY && mouseY < formY + 14);
/*     */ 
/*     */ 
/*     */       
/* 381 */       int formBg = formActive ? GuideColors.color(0, 100, 140, 220) : ((this.formButtonHovered || this.formDropdownOpen) ? GuideColors.color(30, 50, 70, 220) : GuideColors.color(35, 35, 35, 200));
/* 382 */       ctx.method_25294(formX, formY, formX + 14, formY + 14, formBg);
/* 383 */       ctx.method_49601(formX, formY, 14, 14, 
/* 384 */           formActive ? GuideColors.color(80, 180, 255, 255) : GuideColors.color(60, 80, 100, 200));
/*     */       
/* 386 */       int formIconColor = formActive ? GuideColors.color(100, 200, 255, 255) : ((this.formButtonHovered || this.formDropdownOpen) ? GuideColors.color(80, 150, 200, 255) : GuideColors.color(90, 110, 130, 220));
/*     */       
/* 388 */       String formChar = "▲";
/* 389 */       int formCharW = textRenderer.method_1727(formChar);
/* 390 */       ctx.method_51433(textRenderer, formChar, formX + (14 - formCharW) / 2, formY + 3, formIconColor, false);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 395 */     ctx.method_25300(textRenderer, "Left drag: rotate • Right drag: pan • Scroll: zoom • ESC to close", screenWidth / 2, boxY + 200 + 8, GuideColors.TEXT_DIM);
/*     */ 
/*     */ 
/*     */     
/* 399 */     int closeX = boxX + 200 - 10;
/* 400 */     int closeY = boxY + 2;
/* 401 */     boolean closeHovered = (mouseX >= closeX - 2 && mouseX < closeX + 10 && mouseY >= closeY - 2 && mouseY < closeY + 12);
/* 402 */     ctx.method_51433(textRenderer, "×", closeX, closeY, closeHovered ? GuideColors.TEXT_WHITE : GuideColors.TEXT_DIM, true);
/*     */ 
/*     */     
/* 405 */     ctx.method_51448().method_22909();
/*     */ 
/*     */     
/* 408 */     if (this.auraDropdownOpen && !this.availableAuras.isEmpty()) {
/* 409 */       int auraX = boxX + 4;
/* 410 */       int auraY = boxY + 4 + 14 + 4;
/*     */       
/* 412 */       ctx.method_51448().method_22903();
/* 413 */       ctx.method_51448().method_46416(0.0F, 0.0F, 600.0F);
/* 414 */       renderAuraDropdown(ctx, textRenderer, auraX + 14 + 2, auraY, mouseX, mouseY);
/* 415 */       ctx.method_51448().method_22909();
/*     */     } 
/*     */ 
/*     */     
/* 419 */     if (this.formDropdownOpen && !this.availableForms.isEmpty()) {
/* 420 */       int formBtnY = boxY + 4 + 14 + 4;
/* 421 */       if (!this.availableAuras.isEmpty()) formBtnY += 18;
/*     */       
/* 423 */       ctx.method_51448().method_22903();
/* 424 */       ctx.method_51448().method_46416(0.0F, 0.0F, 600.0F);
/* 425 */       renderFormDropdown(ctx, textRenderer, boxX + 4 + 14 + 2, formBtnY, mouseX, mouseY);
/* 426 */       ctx.method_51448().method_22909();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderAuraDropdown(class_332 ctx, class_327 textRenderer, int ddX, int ddY, int mouseX, int mouseY) {
/* 436 */     int itemCount = 1 + this.availableAuras.size();
/*     */ 
/*     */     
/* 439 */     int maxTextWidth = textRenderer.method_1727("None");
/* 440 */     for (SkinDexDetailInfo.AuraEntry aura : this.availableAuras) {
/* 441 */       String label = aura.name();
/* 442 */       if (!aura.unlocked()) {
/* 443 */         label = "🔒 " + label;
/*     */       }
/* 445 */       maxTextWidth = Math.max(maxTextWidth, textRenderer.method_1727(label));
/*     */     } 
/* 447 */     int ddW = Math.max(90, maxTextWidth + 12);
/* 448 */     this.computedDropdownWidth = ddW;
/*     */     
/* 450 */     int ddH = 4 + itemCount * 14 + 2;
/*     */ 
/*     */     
/* 453 */     ctx.method_25294(ddX, ddY, ddX + ddW, ddY + ddH, GuideColors.color(10, 12, 20, 245));
/* 454 */     ctx.method_49601(ddX, ddY, ddW, ddH, GuideColors.color(128, 80, 180, 200));
/*     */     
/* 456 */     this.hoveredAuraOptionIdx = -1;
/*     */     
/* 458 */     for (int i = 0; i < itemCount; i++) {
/* 459 */       String label; int textColor, itemY = ddY + 2 + i * 14;
/* 460 */       boolean itemHovered = (mouseX >= ddX && mouseX < ddX + ddW && mouseY >= itemY && mouseY < itemY + 14);
/*     */ 
/*     */       
/* 463 */       if (itemHovered) {
/* 464 */         this.hoveredAuraOptionIdx = i;
/*     */       }
/*     */       
/* 467 */       int auraIndex = i - 1;
/* 468 */       boolean isSelected = (auraIndex == this.selectedAuraIdx);
/*     */ 
/*     */       
/* 471 */       if (isSelected) {
/* 472 */         ctx.method_25294(ddX + 1, itemY, ddX + ddW - 1, itemY + 14, 
/* 473 */             GuideColors.color(80, 40, 100, 150));
/* 474 */       } else if (itemHovered) {
/* 475 */         ctx.method_25294(ddX + 1, itemY, ddX + ddW - 1, itemY + 14, 
/* 476 */             GuideColors.color(50, 30, 60, 150));
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 481 */       if (i == 0) {
/* 482 */         label = "None";
/* 483 */         textColor = isSelected ? GuideColors.TEXT_WHITE : (itemHovered ? GuideColors.TEXT_PRIMARY : GuideColors.TEXT_DIM);
/*     */       } else {
/* 485 */         SkinDexDetailInfo.AuraEntry aura = this.availableAuras.get(auraIndex);
/* 486 */         label = aura.name();
/* 487 */         if (!aura.unlocked()) {
/* 488 */           label = "🔒 " + label;
/* 489 */           textColor = GuideColors.color(100, 60, 60, 200);
/*     */         } else {
/*     */           
/* 492 */           textColor = isSelected ? GuideColors.color(220, 130, 255, 255) : (itemHovered ? GuideColors.TEXT_PRIMARY : GuideColors.TEXT_DIM);
/*     */         } 
/*     */       } 
/*     */       
/* 496 */       ctx.method_51433(textRenderer, label, ddX + 4, itemY + 3, textColor, true);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderFormDropdown(class_332 ctx, class_327 textRenderer, int ddX, int ddY, int mouseX, int mouseY) {
/* 505 */     int itemCount = 1 + this.availableForms.size();
/*     */ 
/*     */     
/* 508 */     int maxTextWidth = textRenderer.method_1727("Base");
/* 509 */     for (FormEntry form : this.availableForms) {
/* 510 */       maxTextWidth = Math.max(maxTextWidth, textRenderer.method_1727(form.name()));
/*     */     }
/* 512 */     int ddW = Math.max(90, maxTextWidth + 12);
/* 513 */     this.computedFormDropdownWidth = ddW;
/*     */     
/* 515 */     int ddH = 4 + itemCount * 14 + 2;
/*     */ 
/*     */     
/* 518 */     ctx.method_25294(ddX, ddY, ddX + ddW, ddY + ddH, GuideColors.color(10, 12, 20, 245));
/* 519 */     ctx.method_49601(ddX, ddY, ddW, ddH, GuideColors.color(60, 130, 200, 200));
/*     */     
/* 521 */     this.hoveredFormOptionIdx = -1;
/*     */     
/* 523 */     for (int i = 0; i < itemCount; i++) {
/* 524 */       String label; int textColor, itemY = ddY + 2 + i * 14;
/* 525 */       boolean itemHovered = (mouseX >= ddX && mouseX < ddX + ddW && mouseY >= itemY && mouseY < itemY + 14);
/*     */ 
/*     */       
/* 528 */       if (itemHovered) {
/* 529 */         this.hoveredFormOptionIdx = i;
/*     */       }
/*     */       
/* 532 */       int formIndex = i - 1;
/* 533 */       boolean isSelected = (formIndex == this.selectedFormIdx);
/*     */ 
/*     */       
/* 536 */       if (isSelected) {
/* 537 */         ctx.method_25294(ddX + 1, itemY, ddX + ddW - 1, itemY + 14, 
/* 538 */             GuideColors.color(30, 70, 100, 150));
/* 539 */       } else if (itemHovered) {
/* 540 */         ctx.method_25294(ddX + 1, itemY, ddX + ddW - 1, itemY + 14, 
/* 541 */             GuideColors.color(25, 50, 70, 150));
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 546 */       if (i == 0) {
/* 547 */         label = "Base";
/* 548 */         textColor = isSelected ? GuideColors.TEXT_WHITE : (itemHovered ? GuideColors.TEXT_PRIMARY : GuideColors.TEXT_DIM);
/*     */       } else {
/* 550 */         label = ((FormEntry)this.availableForms.get(formIndex)).name();
/*     */         
/* 552 */         textColor = isSelected ? GuideColors.color(100, 200, 255, 255) : (itemHovered ? GuideColors.TEXT_PRIMARY : GuideColors.TEXT_DIM);
/*     */       } 
/*     */       
/* 555 */       ctx.method_51433(textRenderer, label, ddX + 4, itemY + 3, textColor, true);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean mouseClicked(double mouseX, double mouseY, int button, int screenWidth, int screenHeight) {
/* 562 */     if (!this.visible) return false;
/*     */     
/* 564 */     int boxX = (screenWidth - 200) / 2;
/* 565 */     int boxY = (screenHeight - 200) / 2;
/*     */ 
/*     */     
/* 568 */     if (button == 0 && this.auraDropdownOpen && !this.availableAuras.isEmpty()) {
/* 569 */       int auraX = boxX + 4;
/* 570 */       int auraY = boxY + 4 + 14 + 4;
/* 571 */       int ddX = auraX + 14 + 2;
/* 572 */       int ddY = auraY;
/* 573 */       int itemCount = 1 + this.availableAuras.size();
/* 574 */       int ddW = this.computedDropdownWidth;
/* 575 */       int ddH = 4 + itemCount * 14 + 2;
/*     */       
/* 577 */       if (mouseX >= ddX && mouseX < (ddX + ddW) && mouseY >= ddY && mouseY < (ddY + ddH)) {
/*     */         
/* 579 */         if (this.hoveredAuraOptionIdx >= 0) {
/* 580 */           int auraIndex = this.hoveredAuraOptionIdx - 1;
/* 581 */           if (auraIndex < 0) {
/*     */             
/* 583 */             this.selectedAuraIdx = -1;
/* 584 */             rebuildAspects();
/* 585 */           } else if (auraIndex < this.availableAuras.size() && ((SkinDexDetailInfo.AuraEntry)this.availableAuras.get(auraIndex)).unlocked()) {
/* 586 */             this.selectedAuraIdx = auraIndex;
/* 587 */             rebuildAspects();
/*     */           } 
/*     */         } 
/* 590 */         this.auraDropdownOpen = false;
/* 591 */         return true;
/*     */       } 
/*     */       
/* 594 */       this.auraDropdownOpen = false;
/* 595 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 599 */     if (button == 0 && this.formDropdownOpen && !this.availableForms.isEmpty()) {
/* 600 */       int formBtnY = boxY + 4 + 14 + 4;
/* 601 */       if (!this.availableAuras.isEmpty()) formBtnY += 18; 
/* 602 */       int ddX = boxX + 4 + 14 + 2;
/* 603 */       int ddY = formBtnY;
/* 604 */       int itemCount = 1 + this.availableForms.size();
/* 605 */       int ddW = this.computedFormDropdownWidth;
/* 606 */       int ddH = 4 + itemCount * 14 + 2;
/*     */       
/* 608 */       if (mouseX >= ddX && mouseX < (ddX + ddW) && mouseY >= ddY && mouseY < (ddY + ddH)) {
/*     */         
/* 610 */         if (this.hoveredFormOptionIdx >= 0) {
/* 611 */           int formIndex = this.hoveredFormOptionIdx - 1;
/* 612 */           if (formIndex < 0) {
/* 613 */             this.selectedFormIdx = -1;
/* 614 */           } else if (formIndex < this.availableForms.size()) {
/* 615 */             this.selectedFormIdx = formIndex;
/*     */           } 
/* 617 */           rebuildAspects();
/*     */         } 
/* 619 */         this.formDropdownOpen = false;
/* 620 */         return true;
/*     */       } 
/* 622 */       this.formDropdownOpen = false;
/* 623 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 627 */     if (button == 0) {
/* 628 */       int shinyX = boxX + 4;
/* 629 */       int shinyY = boxY + 4;
/* 630 */       if (mouseX >= shinyX && mouseX < (shinyX + 14) && mouseY >= shinyY && mouseY < (shinyY + 14)) {
/*     */         
/* 632 */         this.shinyEnabled = !this.shinyEnabled;
/* 633 */         rebuildAspects();
/* 634 */         return true;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 639 */     if (button == 0 && !this.availableAuras.isEmpty()) {
/* 640 */       int auraX = boxX + 4;
/* 641 */       int auraY = boxY + 4 + 14 + 4;
/* 642 */       if (mouseX >= auraX && mouseX < (auraX + 14) && mouseY >= auraY && mouseY < (auraY + 14)) {
/*     */         
/* 644 */         this.auraDropdownOpen = !this.auraDropdownOpen;
/* 645 */         this.formDropdownOpen = false;
/* 646 */         return true;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 651 */     if (button == 0 && !this.availableForms.isEmpty()) {
/* 652 */       int formX = boxX + 4;
/* 653 */       int formY = boxY + 4 + 14 + 4;
/* 654 */       if (!this.availableAuras.isEmpty()) formY += 18; 
/* 655 */       if (mouseX >= formX && mouseX < (formX + 14) && mouseY >= formY && mouseY < (formY + 14)) {
/*     */         
/* 657 */         this.formDropdownOpen = !this.formDropdownOpen;
/* 658 */         this.auraDropdownOpen = false;
/* 659 */         return true;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 664 */     if (button == 0) {
/* 665 */       int closeX = boxX + 200 - 10;
/* 666 */       int closeY = boxY + 2;
/* 667 */       if (mouseX >= (closeX - 2) && mouseX < (closeX + 10) && mouseY >= (closeY - 2) && mouseY < (closeY + 12)) {
/* 668 */         close();
/* 669 */         return true;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 674 */     if (mouseX >= boxX && mouseX < (boxX + 200) && mouseY >= boxY && mouseY < (boxY + 200)) {
/*     */       
/* 676 */       this.dragging = true;
/* 677 */       this.dragButton = button;
/* 678 */       this.lastDragX = mouseX;
/* 679 */       this.lastDragY = mouseY;
/* 680 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 684 */     if (button == 0) {
/* 685 */       close();
/*     */     }
/* 687 */     return true;
/*     */   }
/*     */   
/*     */   public boolean mouseDragged(double mouseX, double mouseY, int button) {
/* 691 */     if (!this.visible || !this.dragging) return false;
/*     */     
/* 693 */     double dx = mouseX - this.lastDragX;
/* 694 */     double dy = mouseY - this.lastDragY;
/*     */     
/* 696 */     if (this.dragButton == 1) {
/*     */       
/* 698 */       this.panX += (float)dx;
/* 699 */       this.panY += (float)dy;
/* 700 */       this.panX = Math.max(-100.0F, Math.min(100.0F, this.panX));
/* 701 */       this.panY = Math.max(-100.0F, Math.min(100.0F, this.panY));
/*     */     } else {
/*     */       
/* 704 */       this.rotationYaw -= (float)dx * 0.8F;
/* 705 */       this.rotationPitch -= (float)dy * 0.5F;
/* 706 */       this.rotationPitch = Math.max(-60.0F, Math.min(60.0F, this.rotationPitch));
/*     */     } 
/*     */     
/* 709 */     this.lastDragX = mouseX;
/* 710 */     this.lastDragY = mouseY;
/* 711 */     return true;
/*     */   }
/*     */   
/*     */   public boolean mouseReleased(double mouseX, double mouseY, int button) {
/* 715 */     if (!this.visible) return false; 
/* 716 */     if (button == this.dragButton) {
/* 717 */       this.dragging = false;
/* 718 */       this.dragButton = -1;
/*     */     } 
/* 720 */     return true;
/*     */   }
/*     */   
/*     */   public boolean mouseScrolled(double mouseX, double mouseY, double vertical) {
/* 724 */     if (!this.visible) return false;
/*     */     
/* 726 */     this.zoomLevel += (float)vertical * 0.15F;
/* 727 */     this.zoomLevel = Math.max(0.4F, Math.min(3.0F, this.zoomLevel));
/* 728 */     return true;
/*     */   }
/*     */   
/*     */   public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
/* 732 */     if (!this.visible) return false;
/*     */ 
/*     */     
/* 735 */     if (keyCode == 256) {
/* 736 */       if (this.auraDropdownOpen) {
/* 737 */         this.auraDropdownOpen = false;
/* 738 */         return true;
/*     */       } 
/* 740 */       if (this.formDropdownOpen) {
/* 741 */         this.formDropdownOpen = false;
/* 742 */         return true;
/*     */       } 
/* 744 */       close();
/* 745 */       return true;
/*     */     } 
/* 747 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\guide\screen\widgets\ModelViewerOverlay.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */