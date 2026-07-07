/*     */ package com.atlas.common.fabric.cosmetic.preview;
/*     */ import com.atlas.common.fabric.cosmetic.Cosmetic;
/*     */ import com.atlas.common.fabric.cosmetic.CosmeticCategory;
/*     */ import com.atlas.common.fabric.cosmetic.CosmeticRepository;
/*     */ import com.atlas.common.fabric.cosmetic.CosmeticType;
/*     */ import com.atlas.common.fabric.cosmetic.CosmeticVariant;
/*     */ import com.atlas.common.fabric.cosmetic.CustomModelDataCosmeticModel;
/*     */ import com.atlas.common.fabric.cosmetic.EquippedCosmetic;
/*     */ import com.atlas.common.fabric.guide.screen.GuideColors;
/*     */ import com.atlas.common.fabric.guide.screen.GuideSounds;
/*     */ import com.atlas.common.fabric.guide.screen.widgets.GuideSearchBar;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import net.minecraft.class_1268;
/*     */ import net.minecraft.class_1792;
/*     */ import net.minecraft.class_1799;
/*     */ import net.minecraft.class_1935;
/*     */ import net.minecraft.class_2561;
/*     */ import net.minecraft.class_310;
/*     */ import net.minecraft.class_332;
/*     */ import net.minecraft.class_4587;
/*     */ import net.minecraft.class_4597;
/*     */ import net.minecraft.class_742;
/*     */ import net.minecraft.class_746;
/*     */ import net.minecraft.class_898;
/*     */ import net.minecraft.class_9280;
/*     */ import net.minecraft.class_9334;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ import org.joml.Quaternionf;
/*     */ 
/*     */ public class CosmeticPreviewScreen extends class_437 implements CosmeticPreviewProvider {
/*     */   private static final int GUI_WIDTH = 400;
/*     */   private static final int GUI_HEIGHT = 280;
/*     */   private static final int HEADER_HEIGHT = 18;
/*     */   private static final int TAB_HEIGHT = 16;
/*     */   private static final int COSMETIC_ROW_HEIGHT = 18;
/*     */   private static final int SCROLLBAR_WIDTH = 4;
/*     */   private static final float MODEL_BASE_SCALE = 55.0F;
/*     */   private int guiLeft;
/*     */   private int guiTop;
/*     */   private int modelPanelX;
/*     */   private int modelPanelY;
/*     */   private int modelPanelW;
/*     */   private int modelPanelH;
/*     */   private int listPanelX;
/*     */   private int listPanelY;
/*     */   private int listPanelW;
/*     */   private int listPanelH;
/*     */   private int tabsY;
/*     */   private int cosmeticListY;
/*     */   private int cosmeticListH;
/*     */   private int resetBtnX;
/*     */   private int resetBtnY;
/*     */   private int resetBtnW;
/*     */   private int resetBtnH;
/*  58 */   private final Map<CosmeticCategory, EquippedCosmetic> previewedCosmetics = new LinkedHashMap<>();
/*  59 */   private CosmeticCategory selectedCategory = CosmeticCategory.HAT;
/*     */   
/*     */   @Nullable
/*  62 */   private Cosmetic previewedToolCosmetic = null;
/*     */ 
/*     */   
/*  65 */   private float rotationYaw = 0.0F;
/*  66 */   private float rotationPitch = 0.0F;
/*  67 */   private float zoomLevel = 1.0F;
/*     */   
/*     */   private boolean dragging = false;
/*     */   private double lastMouseX;
/*     */   private double lastMouseY;
/*  72 */   private List<CosmeticListEntry> currentEntries = new ArrayList<>();
/*  73 */   private float scrollOffset = 0.0F;
/*  74 */   private int hoveredEntryIndex = -1;
/*     */   
/*     */   private boolean hoveredResetBtn = false;
/*     */   
/*     */   private boolean scrollbarDragging = false;
/*  79 */   private double scrollbarDragStartY = 0.0D;
/*  80 */   private float scrollbarDragStartOffset = 0.0F;
/*     */   
/*     */   private GuideSearchBar searchBar;
/*     */   
/*  84 */   private String searchText = "";
/*     */   private final Set<String> expandedCosmeticIds;
/*     */   private static final class CosmeticListEntry extends Record {
/*     */     private final Cosmetic cosmetic; @Nullable private final CosmeticVariant variant; private final boolean isVariant; private final boolean expanded; private CosmeticListEntry(Cosmetic cosmetic, @Nullable CosmeticVariant variant, boolean isVariant, boolean expanded) { this.cosmetic = cosmetic; this.variant = variant; this.isVariant = isVariant; this.expanded = expanded; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/cosmetic/preview/CosmeticPreviewScreen$CosmeticListEntry;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #96	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/cosmetic/preview/CosmeticPreviewScreen$CosmeticListEntry; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/cosmetic/preview/CosmeticPreviewScreen$CosmeticListEntry;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #96	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/cosmetic/preview/CosmeticPreviewScreen$CosmeticListEntry; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/cosmetic/preview/CosmeticPreviewScreen$CosmeticListEntry;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #96	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lcom/atlas/common/fabric/cosmetic/preview/CosmeticPreviewScreen$CosmeticListEntry;
/*  87 */       //   0	8	1	o	Ljava/lang/Object; } public Cosmetic cosmetic() { return this.cosmetic; } @Nullable public CosmeticVariant variant() { return this.variant; } public boolean isVariant() { return this.isVariant; } public boolean expanded() { return this.expanded; } } protected void method_25426() { super.method_25426(); this.guiLeft = (this.field_22789 - 400) / 2; this.guiTop = (this.field_22790 - 280) / 2; int contentX = this.guiLeft + 2; int contentY = this.guiTop + 18 + 1; int contentW = 396; int contentH = 259; this.modelPanelX = contentX; this.modelPanelY = contentY; this.modelPanelW = (int)(contentW * 0.52D); this.modelPanelH = contentH; this.listPanelX = this.modelPanelX + this.modelPanelW + 2; this.listPanelY = contentY; this.listPanelW = contentW - this.modelPanelW - 2; this.listPanelH = contentH; this.tabsY = this.listPanelY + 2; int searchBarY = this.tabsY + 16 + 4; int searchBarH = 14; if (this.searchBar == null) { this.searchBar = new GuideSearchBar(this.listPanelX + 4, searchBarY, this.listPanelW - 8, searchBarH); this.searchBar.setPlaceholder("Search cosmetics..."); this.searchBar.setOnTextChanged(text -> { this.searchText = text; this.scrollOffset = 0.0F; refreshCategoryCosmetics(); }); } else { this.searchBar.updateBounds(this.listPanelX + 4, searchBarY, this.listPanelW - 8, searchBarH); }  this.cosmeticListY = searchBarY + searchBarH + 3; this.cosmeticListH = this.listPanelH - 16 - searchBarH - 11; this.resetBtnW = 42; this.resetBtnH = 12; this.resetBtnX = this.modelPanelX + 4; this.resetBtnY = this.modelPanelY + this.modelPanelH - this.resetBtnH - 4; this.previewedCosmetics.clear(); this.previewedToolCosmetic = null; refreshCategoryCosmetics(); } public boolean method_25421() { return false; } @Nullable public Map<CosmeticCategory, EquippedCosmetic> getPreviewedCosmetics() { return this.previewedCosmetics.isEmpty() ? null : Collections.<CosmeticCategory, EquippedCosmetic>unmodifiableMap(this.previewedCosmetics); } public void method_25394(class_332 ctx, int mouseX, int mouseY, float delta) { ctx.method_25294(0, 0, this.field_22789, this.field_22790, GuideColors.color(0, 0, 0, 120)); drawPanel(ctx); drawHeader(ctx); drawModelPanel(ctx, mouseX, mouseY, delta); drawListPanel(ctx, mouseX, mouseY); super.method_25394(ctx, mouseX, mouseY, delta); } public void method_25420(class_332 context, int mouseX, int mouseY, float delta) {} public CosmeticPreviewScreen() { super((class_2561)class_2561.method_43471("screen.atlas_cosmetic.preview_title"));
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
/* 727 */     this.expandedCosmeticIds = new HashSet<>(); }
/*     */   private void drawPanel(class_332 ctx) { ctx.method_25294(this.guiLeft - 1, this.guiTop - 1, this.guiLeft + 400 + 1, this.guiTop + 280 + 1, GuideColors.BORDER); ctx.method_25294(this.guiLeft, this.guiTop, this.guiLeft + 400, this.guiTop + 280, GuideColors.PANEL_BG); }
/*     */   private void drawHeader(class_332 ctx) { int hx = this.guiLeft; int hy = this.guiTop; int hw = 400; int hh = 18; ctx.method_25294(hx, hy, hx + hw, hy + hh, GuideColors.HEADER_BG); ctx.method_51433(this.field_22793, "Cosmetic Preview", hx + 6, hy + 5, GuideColors.TEXT_ACCENT, true); String subtitle = "Preview cosmetics on your character"; int subtitleWidth = this.field_22793.method_1727(subtitle); ctx.method_51433(this.field_22793, subtitle, hx + hw - subtitleWidth - 6, hy + 5, GuideColors.TEXT_DIM, true); ctx.method_25294(hx, hy + hh - 1, hx + hw, hy + hh, GuideColors.ACCENT_EMERALD); }
/*     */   private void drawModelPanel(class_332 ctx, int mouseX, int mouseY, float delta) { ctx.method_25294(this.modelPanelX, this.modelPanelY, this.modelPanelX + this.modelPanelW, this.modelPanelY + this.modelPanelH, GuideColors.SECTION_BG); drawBorder(ctx, this.modelPanelX, this.modelPanelY, this.modelPanelW, this.modelPanelH, GuideColors.BORDER_DIM); ctx.method_44379(this.modelPanelX + 1, this.modelPanelY + 1, this.modelPanelX + this.modelPanelW - 1, this.modelPanelY + this.modelPanelH - 1); drawPlayerEntity(ctx, this.modelPanelX + this.modelPanelW / 2, this.modelPanelY + this.modelPanelH / 2 + 30, 55.0F); ctx.method_44380(); this.hoveredResetBtn = (mouseX >= this.resetBtnX && mouseX <= this.resetBtnX + this.resetBtnW && mouseY >= this.resetBtnY && mouseY <= this.resetBtnY + this.resetBtnH); int resetBg = this.hoveredResetBtn ? GuideColors.BUTTON_HOVER : GuideColors.BUTTON_BG; int resetBorder = this.hoveredResetBtn ? GuideColors.BUTTON_BORDER_HOVER : GuideColors.BUTTON_BORDER; ctx.method_25294(this.resetBtnX, this.resetBtnY, this.resetBtnX + this.resetBtnW, this.resetBtnY + this.resetBtnH, resetBg); drawBorder(ctx, this.resetBtnX, this.resetBtnY, this.resetBtnW, this.resetBtnH, resetBorder); String resetText = "Reset"; int resetTextW = this.field_22793.method_1727(resetText); ctx.method_51433(this.field_22793, resetText, this.resetBtnX + (this.resetBtnW - resetTextW) / 2, this.resetBtnY + 2, this.hoveredResetBtn ? GuideColors.TEXT_WHITE : GuideColors.TEXT_DIM, true); String hint = "Drag to rotate · Scroll to zoom"; int hintW = this.field_22793.method_1727(hint); ctx.method_51433(this.field_22793, hint, this.modelPanelX + (this.modelPanelW - hintW) / 2, this.modelPanelY + 4, GuideColors.TEXT_MUTED, false); }
/*     */   private void drawPlayerEntity(class_332 ctx, int centerX, int centerY, float size) { class_746 class_746 = (class_310.method_1551()).field_1724; if (class_746 == null) return;  float oldBodyYaw = ((class_742)class_746).field_6283; float oldYaw = class_746.method_36454(); float oldPitch = class_746.method_36455(); float oldPrevHeadYaw = ((class_742)class_746).field_6259; float oldHeadYaw = ((class_742)class_746).field_6241; class_1799 originalMainHand = class_746.method_6047().method_7972(); ((class_742)class_746).field_6283 = 180.0F + this.rotationYaw; class_746.method_36456(180.0F + this.rotationYaw); class_746.method_36457(this.rotationPitch); ((class_742)class_746).field_6241 = class_746.method_36454(); ((class_742)class_746).field_6259 = class_746.method_36454(); if (this.previewedToolCosmetic != null) { class_1799 toolStack = createToolPreviewStack(this.previewedToolCosmetic); if (toolStack != null) class_746.method_6122(class_1268.field_5808, toolStack);  }  class_4587 matrices = ctx.method_51448(); matrices.method_22903(); matrices.method_22904(centerX, centerY, 50.0D); float scaledSize = size * this.zoomLevel; matrices.method_22905(scaledSize, scaledSize, -scaledSize); Quaternionf quaternionf = (new Quaternionf()).rotateZ(3.1415927F); Quaternionf pitchQuat = (new Quaternionf()).rotateX(this.rotationPitch * 0.017453292F); quaternionf.mul((Quaternionfc)pitchQuat); matrices.method_22907(quaternionf); class_308.method_34742(); class_898 dispatcher = class_310.method_1551().method_1561(); pitchQuat.conjugate(); dispatcher.method_24196(pitchQuat); dispatcher.method_3948(false); class_4597.class_4598 immediate = class_310.method_1551().method_22940().method_23000(); try { dispatcher.method_3954((class_1297)class_746, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, matrices, (class_4597)immediate, 15728880); immediate.method_22993(); } catch (Exception exception) {} dispatcher.method_3948(true); class_308.method_24211(); matrices.method_22909(); ((class_742)class_746).field_6283 = oldBodyYaw; class_746.method_36456(oldYaw); class_746.method_36457(oldPitch); ((class_742)class_746).field_6259 = oldPrevHeadYaw; ((class_742)class_746).field_6241 = oldHeadYaw; class_746.method_6122(class_1268.field_5808, originalMainHand); }
/*     */   @Nullable private class_1799 createToolPreviewStack(Cosmetic cosmetic) { return (class_1799)cosmetic.getDisplayModel().fold(bedrock -> null, customModel -> { CosmeticType type = customModel.getType(); class_1792 previewItem = type.getPreviewItem(); if (previewItem != null) { class_1799 stack = new class_1799((class_1935)previewItem); stack.method_57379(class_9334.field_49637, new class_9280(customModel.getSteveModelId())); return stack; }  return null; }); }
/* 733 */   private void drawListPanel(class_332 ctx, int mouseX, int mouseY) { ctx.method_25294(this.listPanelX, this.listPanelY, this.listPanelX + this.listPanelW, this.listPanelY + this.listPanelH, GuideColors.SECTION_BG); drawBorder(ctx, this.listPanelX, this.listPanelY, this.listPanelW, this.listPanelH, GuideColors.BORDER_DIM); drawCategoryTabs(ctx, mouseX, mouseY); this.searchBar.render(ctx, this.field_22793, mouseX, mouseY); drawCosmeticList(ctx, mouseX, mouseY); } private boolean isBeingPreviewed(Cosmetic cosmetic, @Nullable CosmeticVariant variant) { if (cosmetic.getCategory() == CosmeticCategory.TOOL) {
/* 734 */       return (this.previewedToolCosmetic != null && this.previewedToolCosmetic.equals(cosmetic));
/*     */     }
/* 736 */     EquippedCosmetic preview = this.previewedCosmetics.get(cosmetic.getCategory());
/* 737 */     if (preview == null) return false; 
/* 738 */     return (preview.getCosmetic().equals(cosmetic) && 
/* 739 */       Objects.equals(preview.getVariant(), variant)); } private void drawCategoryTabs(class_332 ctx, int mouseX, int mouseY) { CosmeticCategory[] categories = CosmeticCategory.values(); int tabW = (this.listPanelW - 8) / categories.length; int tx = this.listPanelX + 4; for (int i = 0; i < categories.length; i++) { CosmeticCategory cat = categories[i]; int tabX = tx + i * tabW; boolean isActive = (cat == this.selectedCategory); boolean isHovered = (mouseX >= tabX && mouseX <= tabX + tabW - 2 && mouseY >= this.tabsY && mouseY <= this.tabsY + 16); int bg = isActive ? GuideColors.TAB_ACTIVE_BG : (isHovered ? GuideColors.TAB_HOVER_BG : GuideColors.TAB_INACTIVE_BG); ctx.method_25294(tabX, this.tabsY, tabX + tabW - 2, this.tabsY + 16, bg); if (isActive) ctx.method_25294(tabX, this.tabsY + 16 - 1, tabX + tabW - 2, this.tabsY + 16, GuideColors.ACCENT_EMERALD);  String label = formatCategoryName(cat); int labelW = this.field_22793.method_1727(label); int labelColor = isActive ? GuideColors.TEXT_ACCENT : (isHovered ? GuideColors.TEXT_PRIMARY : GuideColors.TEXT_DIM); ctx.method_51433(this.field_22793, label, tabX + (tabW - 2 - labelW) / 2, this.tabsY + 4, labelColor, true); }  }
/*     */   private void drawCosmeticList(class_332 ctx, int mouseX, int mouseY) { int listX = this.listPanelX + 4; int listW = this.listPanelW - 8 - 4 - 2; ctx.method_44379(listX, this.cosmeticListY, listX + listW + 4 + 2, this.cosmeticListY + this.cosmeticListH); this.hoveredEntryIndex = -1; int totalHeight = this.currentEntries.size() * 18; float maxScroll = Math.max(0, totalHeight - this.cosmeticListH); this.scrollOffset = Math.max(0.0F, Math.min(this.scrollOffset, maxScroll)); for (int i = 0; i < this.currentEntries.size(); i++) { int rowY = this.cosmeticListY + i * 18 - (int)this.scrollOffset; if (rowY + 18 >= this.cosmeticListY && rowY <= this.cosmeticListY + this.cosmeticListH) { CosmeticListEntry entry = this.currentEntries.get(i); boolean isHovered = (mouseX >= listX && mouseX <= listX + listW && mouseY >= Math.max(rowY, this.cosmeticListY) && mouseY <= Math.min(rowY + 18, this.cosmeticListY + this.cosmeticListH) && mouseY >= rowY && mouseY <= rowY + 18); if (isHovered) this.hoveredEntryIndex = i;  drawCosmeticRow(ctx, entry, listX, rowY, listW, isHovered); }  }  if (totalHeight > this.cosmeticListH) drawScrollbar(ctx, listX + listW + 2, this.cosmeticListY, this.cosmeticListH, totalHeight, mouseX, mouseY);  ctx.method_44380(); }
/*     */   private void drawCosmeticRow(class_332 ctx, CosmeticListEntry entry, int x, int y, int w, boolean hovered) { int nameColor; String statusIcon; int statusColor; if (hovered) ctx.method_25294(x, y, x + w, y + 18, GuideColors.CARD_HOVER_BG);  Cosmetic cosmetic = entry.cosmetic(); boolean isUnpurchased = CosmeticRepository.INSTANCE.isUnpurchased(cosmetic); boolean isPreviewing = isBeingPreviewed(cosmetic, entry.variant()); boolean isEquipped = isCurrentlyEquipped(cosmetic, entry.variant()); int textX = x + (entry.isVariant() ? 14 : 4); int textY = y + 5; String rawName = (entry.isVariant() && entry.variant() != null) ? entry.variant().getName() : cosmetic.getName(); String name = stripColorCodes(rawName); if (isPreviewing) { nameColor = GuideColors.ACCENT_EMERALD; } else if (isUnpurchased) { nameColor = GuideColors.TEXT_MUTED; } else { nameColor = GuideColors.TEXT_PRIMARY; }  ctx.method_51433(this.field_22793, name, textX, textY, nameColor, true); if (isPreviewing) { statusIcon = "◆"; statusColor = GuideColors.ACCENT_EMERALD; } else if (isEquipped) { statusIcon = "✔"; statusColor = GuideColors.TEXT_ACCENT; } else if (isUnpurchased) { statusIcon = "🔒"; statusColor = GuideColors.TEXT_MUTED; } else { statusIcon = ""; statusColor = 0; }  if (!statusIcon.isEmpty()) { int iconW = this.field_22793.method_1727(statusIcon); ctx.method_51433(this.field_22793, statusIcon, x + w - iconW - 4, textY, statusColor, true); }  if (!entry.isVariant() && cosmetic.getVariants() != null && !cosmetic.getVariants().isEmpty()) { String arrow = entry.expanded() ? "▼" : "▶"; ctx.method_51433(this.field_22793, arrow, x + w - 22, textY, GuideColors.TEXT_DIM, true); }  ctx.method_25294(x + 2, y + 18 - 1, x + w - 2, y + 18, GuideColors.color(30, 40, 38, 100)); }
/*     */   private void drawScrollbar(class_332 ctx, int x, int y, int h, int totalHeight, int mouseX, int mouseY) { ctx.method_25294(x, y, x + 4, y + h, GuideColors.SCROLLBAR_TRACK); float thumbRatio = h / totalHeight; int thumbH = Math.max(15, (int)(h * thumbRatio)); float scrollRatio = this.scrollOffset / Math.max(1, totalHeight - h); int thumbY = y + (int)((h - thumbH) * scrollRatio); boolean thumbHovered = (mouseX >= x && mouseX <= x + 4 && mouseY >= thumbY && mouseY <= thumbY + thumbH); int thumbColor = (thumbHovered || this.scrollbarDragging) ? GuideColors.SCROLLBAR_THUMB_HOVER : GuideColors.SCROLLBAR_THUMB; ctx.method_25294(x, thumbY, x + 4, thumbY + thumbH, thumbColor); }
/*     */   public boolean method_25402(double mouseX, double mouseY, int button) { if (this.searchBar.mouseClicked(mouseX, mouseY, button))
/*     */       return true;  if (this.hoveredResetBtn && button == 0) { this.previewedCosmetics.clear(); this.previewedToolCosmetic = null; refreshCategoryCosmetics(); GuideSounds.playClick(); return true; }  if (mouseY >= this.tabsY && mouseY <= (this.tabsY + 16) && mouseX >= this.listPanelX && mouseX <= (this.listPanelX + this.listPanelW)) { CosmeticCategory[] categories = CosmeticCategory.values(); int tabW = (this.listPanelW - 8) / categories.length; int tx = this.listPanelX + 4; for (int i = 0; i < categories.length; i++) { int tabX = tx + i * tabW; if (mouseX >= tabX && mouseX <= (tabX + tabW - 2)) { if (categories[i] != this.selectedCategory) { this.selectedCategory = categories[i]; this.searchText = ""; this.searchBar.setText(""); this.scrollOffset = 0.0F; refreshCategoryCosmetics(); GuideSounds.playClick(); }  return true; }  }  }  if (this.hoveredEntryIndex >= 0 && this.hoveredEntryIndex < this.currentEntries.size() && button == 0) { CosmeticListEntry entry = this.currentEntries.get(this.hoveredEntryIndex); if (!entry.isVariant() && entry.cosmetic().getVariants() != null && !entry.cosmetic().getVariants().isEmpty()) { toggleExpand(entry.cosmetic()); GuideSounds.playClick(); return true; }  togglePreview(entry.cosmetic(), entry.variant()); GuideSounds.playClick(); return true; }  int scrollbarX = this.listPanelX + this.listPanelW - 4 - 4; if (mouseX >= scrollbarX && mouseX <= (scrollbarX + 4) && mouseY >= this.cosmeticListY && mouseY <= (this.cosmeticListY + this.cosmeticListH)) { this.scrollbarDragging = true; this.scrollbarDragStartY = mouseY; this.scrollbarDragStartOffset = this.scrollOffset; return true; }  if (mouseX >= this.modelPanelX && mouseX <= (this.modelPanelX + this.modelPanelW) && mouseY >= this.modelPanelY && mouseY <= (this.modelPanelY + this.modelPanelH) && button == 0) { this.dragging = true; this.lastMouseX = mouseX; this.lastMouseY = mouseY; return true; }  return super.method_25402(mouseX, mouseY, button); }
/*     */   public boolean method_25403(double mouseX, double mouseY, int button, double deltaX, double deltaY) { if (this.scrollbarDragging) { int totalHeight = this.currentEntries.size() * 18; float maxScroll = Math.max(0, totalHeight - this.cosmeticListH); float scrollableTrackH = (this.cosmeticListH - Math.max(15, (int)(this.cosmeticListH * this.cosmeticListH / totalHeight))); if (scrollableTrackH > 0.0F) { float dragDelta = (float)(mouseY - this.scrollbarDragStartY); this.scrollOffset = Math.max(0.0F, Math.min(maxScroll, this.scrollbarDragStartOffset + dragDelta / scrollableTrackH * maxScroll)); }  return true; }  if (this.dragging && button == 0) { double dx = mouseX - this.lastMouseX; double dy = mouseY - this.lastMouseY; this.rotationYaw += (float)dx * 0.8F; this.rotationPitch = Math.max(-30.0F, Math.min(30.0F, this.rotationPitch + (float)dy * 0.5F)); this.lastMouseX = mouseX; this.lastMouseY = mouseY; return true; }  return super.method_25403(mouseX, mouseY, button, deltaX, deltaY); }
/* 746 */   private boolean isCurrentlyEquipped(Cosmetic cosmetic, @Nullable CosmeticVariant variant) { class_310 client = class_310.method_1551();
/* 747 */     if (client.field_1724 == null) return false;
/*     */     
/* 749 */     EquippedCosmetic equipped = EquippedCosmeticsRepository.getCosmetic(client.field_1724
/* 750 */         .method_5667(), cosmetic.getCategory());
/* 751 */     if (equipped == null) return false; 
/* 752 */     return (equipped.getCosmetic().equals(cosmetic) && 
/* 753 */       Objects.equals(equipped.getVariant(), variant)); } public boolean method_25406(double mouseX, double mouseY, int button) { this.dragging = false; this.scrollbarDragging = false; return super.method_25406(mouseX, mouseY, button); }
/*     */   public boolean method_25401(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) { if (mouseX >= this.modelPanelX && mouseX <= (this.modelPanelX + this.modelPanelW) && mouseY >= this.modelPanelY && mouseY <= (this.modelPanelY + this.modelPanelH)) { this.zoomLevel = Math.max(0.5F, Math.min(3.0F, this.zoomLevel + (float)verticalAmount * 0.15F)); return true; }  if (mouseX >= this.listPanelX && mouseX <= (this.listPanelX + this.listPanelW) && mouseY >= this.cosmeticListY && mouseY <= (this.cosmeticListY + this.cosmeticListH)) { int totalHeight = this.currentEntries.size() * 18; float maxScroll = Math.max(0, totalHeight - this.cosmeticListH); this.scrollOffset = Math.max(0.0F, Math.min(maxScroll, this.scrollOffset - (float)verticalAmount * 18.0F)); return true; }  return super.method_25401(mouseX, mouseY, horizontalAmount, verticalAmount); }
/*     */   public boolean method_25404(int keyCode, int scanCode, int modifiers) { if (this.searchBar.keyPressed(keyCode, scanCode, modifiers))
/*     */       return true;  return super.method_25404(keyCode, scanCode, modifiers); }
/*     */   public boolean method_25400(char chr, int modifiers) { if (this.searchBar.charTyped(chr, modifiers))
/*     */       return true;  return super.method_25400(chr, modifiers); }
/*     */   private void togglePreview(Cosmetic cosmetic, @Nullable CosmeticVariant variant) { CosmeticCategory category = cosmetic.getCategory(); if (category == CosmeticCategory.TOOL) { if (this.previewedToolCosmetic != null && this.previewedToolCosmetic.equals(cosmetic)) { this.previewedToolCosmetic = null; } else { this.previewedToolCosmetic = cosmetic; }  } else { EquippedCosmetic current = this.previewedCosmetics.get(category); if (current != null && current.getCosmetic().equals(cosmetic) && Objects.equals(current.getVariant(), variant)) { this.previewedCosmetics.remove(category); } else { this.previewedCosmetics.put(category, new EquippedCosmetic(cosmetic, variant)); }  }  refreshCategoryCosmetics(); }
/*     */   private void toggleExpand(Cosmetic cosmetic) { boolean isCurrentlyExpanded = false; for (int i = 0; i < this.currentEntries.size(); i++) { if (((CosmeticListEntry)this.currentEntries.get(i)).cosmetic().equals(cosmetic) && !((CosmeticListEntry)this.currentEntries.get(i)).isVariant()) { isCurrentlyExpanded = ((CosmeticListEntry)this.currentEntries.get(i)).expanded(); break; }  }  if (isCurrentlyExpanded) { this.expandedCosmeticIds.remove(cosmetic.getId()); } else { this.expandedCosmeticIds.add(cosmetic.getId()); }
/*     */      refreshCategoryCosmetics(); }
/* 762 */   private void refreshCategoryCosmetics() { String lowerSearch = this.searchText.toLowerCase();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 767 */     List<Cosmetic> cosmetics = (List<Cosmetic>)CosmeticRepository.INSTANCE.getCosmetics().values().stream().filter(c -> (c.getCategory() == this.selectedCategory)).filter(c -> !CosmeticRepository.INSTANCE.isInvisible(c)).filter(c -> (lowerSearch.isEmpty() || stripColorCodes(c.getName()).toLowerCase().contains(lowerSearch))).collect(Collectors.toList());
/*     */     
/* 769 */     this.currentEntries = new ArrayList<>();
/* 770 */     for (Cosmetic cosmetic : cosmetics) {
/* 771 */       boolean hasVariants = (cosmetic.getVariants() != null && !cosmetic.getVariants().isEmpty());
/* 772 */       boolean expanded = this.expandedCosmeticIds.contains(cosmetic.getId());
/*     */       
/* 774 */       this.currentEntries.add(new CosmeticListEntry(cosmetic, null, false, expanded));
/*     */       
/* 776 */       if (hasVariants && expanded) {
/* 777 */         for (CosmeticVariant variant : cosmetic.getVariants()) {
/* 778 */           if (!CosmeticRepository.INSTANCE.isInvisible(variant)) {
/* 779 */             this.currentEntries.add(new CosmeticListEntry(cosmetic, variant, true, false));
/*     */           }
/*     */         } 
/*     */       }
/*     */     }  }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String formatCategoryName(CosmeticCategory category) {
/* 789 */     String name = category.name();
/* 790 */     return "" + name.charAt(0) + name.charAt(0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String stripColorCodes(String text) {
/* 798 */     if (text == null || text.isEmpty()) return "";
/*     */     
/* 800 */     String stripped = text.replaceAll("(?i)[&§]#[0-9a-f]{6}", "");
/*     */     
/* 802 */     stripped = stripped.replaceAll("(?i)[&§][0-9a-fk-orx]", "");
/* 803 */     return stripped.trim();
/*     */   }
/*     */   
/*     */   private void drawBorder(class_332 ctx, int x, int y, int w, int h, int color) {
/* 807 */     ctx.method_25294(x, y, x + w, y + 1, color);
/* 808 */     ctx.method_25294(x, y + h - 1, x + w, y + h, color);
/* 809 */     ctx.method_25294(x, y, x + 1, y + h, color);
/* 810 */     ctx.method_25294(x + w - 1, y, x + w, y + h, color);
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\cosmetic\preview\CosmeticPreviewScreen.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */