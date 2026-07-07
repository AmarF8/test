/*     */ package com.atlas.common.fabric.skindex.screen;
/*     */ 
/*     */ import com.atlas.common.fabric.guide.screen.GuideColors;
/*     */ import com.atlas.common.fabric.guide.screen.GuideSounds;
/*     */ import com.atlas.common.fabric.guide.screen.widgets.ModelViewerOverlay;
/*     */ import com.atlas.common.fabric.skindex.data.SkinDexData;
/*     */ import com.atlas.common.fabric.skindex.network.SkinDexNetwork;
/*     */ import net.minecraft.class_2561;
/*     */ import net.minecraft.class_332;
/*     */ import net.minecraft.class_437;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SkinDexScreen
/*     */   extends class_437
/*     */ {
/*     */   private static final int GUI_WIDTH = 384;
/*     */   private static final int GUI_HEIGHT = 272;
/*     */   private static final int HEADER_HEIGHT = 18;
/*     */   private int guiLeft;
/*     */   private int guiTop;
/*     */   private final SkinDexListView listView;
/*     */   private final SkinDexDetailView detailView;
/*     */   private boolean showingDetail = false;
/*  33 */   private final ModelViewerOverlay modelViewer = new ModelViewerOverlay();
/*     */   
/*     */   public SkinDexScreen() {
/*  36 */     super((class_2561)class_2561.method_43471("screen.atlas_skindex.title"));
/*  37 */     this.listView = new SkinDexListView();
/*  38 */     this.detailView = new SkinDexDetailView();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void method_25426() {
/*  45 */     super.method_25426();
/*  46 */     this.guiLeft = (this.field_22789 - 384) / 2;
/*  47 */     this.guiTop = (this.field_22790 - 272) / 2;
/*     */     
/*  49 */     int contentX = this.guiLeft + 2;
/*  50 */     int contentY = this.guiTop + 18 + 1;
/*  51 */     int contentW = 380;
/*  52 */     int contentH = 251;
/*     */     
/*  54 */     this.listView.init(contentX, contentY, contentW, contentH, this::onLineSelected, this.modelViewer);
/*  55 */     this.detailView.init(contentX, contentY, contentW, contentH, this.modelViewer, this::onBackToList);
/*  56 */     this.detailView.setScreenDimensions(this.field_22789, this.field_22790);
/*     */ 
/*     */     
/*  59 */     SkinDexData.getInstance().setLinesLoading(true);
/*  60 */     SkinDexNetwork.requestSkinDexData("lines", "");
/*     */   }
/*     */ 
/*     */   
/*     */   public void method_25419() {
/*  65 */     SkinDexData.getInstance().clearAll();
/*  66 */     super.method_25419();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean method_25421() {
/*  71 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void method_25394(class_332 ctx, int mouseX, int mouseY, float delta) {
/*  79 */     ctx.method_25294(0, 0, this.field_22789, this.field_22790, GuideColors.color(0, 0, 0, 120));
/*     */ 
/*     */     
/*  82 */     drawPanel(ctx);
/*  83 */     drawHeader(ctx, mouseX, mouseY);
/*     */ 
/*     */     
/*  86 */     if (this.showingDetail) {
/*  87 */       this.detailView.render(ctx, this.field_22793, mouseX, mouseY, delta);
/*     */     } else {
/*  89 */       this.listView.render(ctx, this.field_22793, mouseX, mouseY, delta);
/*     */     } 
/*     */     
/*  92 */     super.method_25394(ctx, mouseX, mouseY, delta);
/*     */ 
/*     */     
/*  95 */     if (this.modelViewer.isVisible()) {
/*  96 */       this.modelViewer.render(ctx, this.field_22793, this.field_22789, this.field_22790, mouseX, mouseY, delta);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void method_25420(class_332 context, int mouseX, int mouseY, float delta) {}
/*     */ 
/*     */   
/*     */   private void drawPanel(class_332 ctx) {
/* 105 */     ctx.method_25294(this.guiLeft - 1, this.guiTop - 1, this.guiLeft + 384 + 1, this.guiTop + 272 + 1, GuideColors.BORDER);
/*     */     
/* 107 */     ctx.method_25294(this.guiLeft, this.guiTop, this.guiLeft + 384, this.guiTop + 272, GuideColors.PANEL_BG);
/*     */   }
/*     */   
/*     */   private void drawHeader(class_332 ctx, int mouseX, int mouseY) {
/* 111 */     int hx = this.guiLeft;
/* 112 */     int hy = this.guiTop;
/* 113 */     int hw = 384;
/* 114 */     int hh = 18;
/*     */ 
/*     */     
/* 117 */     ctx.method_25294(hx, hy, hx + hw, hy + hh, GuideColors.HEADER_BG);
/*     */ 
/*     */     
/* 120 */     ctx.method_51433(this.field_22793, "SkinDex", hx + 6, hy + 5, GuideColors.TEXT_ACCENT, true);
/*     */ 
/*     */     
/* 123 */     String subtitle = "Skin Collections";
/* 124 */     int subtitleWidth = this.field_22793.method_1727(subtitle);
/* 125 */     ctx.method_51433(this.field_22793, subtitle, hx + hw - subtitleWidth - 6, hy + 5, GuideColors.TEXT_DIM, true);
/*     */ 
/*     */     
/* 128 */     ctx.method_25294(hx, hy + hh - 1, hx + hw, hy + hh, GuideColors.ACCENT_EMERALD);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean method_25402(double mouseX, double mouseY, int button) {
/* 136 */     if (this.modelViewer.isVisible()) {
/* 137 */       return this.modelViewer.mouseClicked(mouseX, mouseY, button, this.field_22789, this.field_22790);
/*     */     }
/*     */     
/* 140 */     if (this.showingDetail) {
/* 141 */       return this.detailView.mouseClicked(mouseX, mouseY, button);
/*     */     }
/* 143 */     return this.listView.mouseClicked(mouseX, mouseY, button);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean method_25401(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
/* 149 */     if (this.modelViewer.isVisible()) {
/* 150 */       return this.modelViewer.mouseScrolled(mouseX, mouseY, verticalAmount);
/*     */     }
/* 152 */     if (this.showingDetail) {
/* 153 */       return this.detailView.mouseScrolled(mouseX, mouseY, horizontalAmount, verticalAmount);
/*     */     }
/* 155 */     return this.listView.mouseScrolled(mouseX, mouseY, horizontalAmount, verticalAmount);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean method_25403(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
/* 161 */     if (this.modelViewer.isVisible()) {
/* 162 */       return this.modelViewer.mouseDragged(mouseX, mouseY, button);
/*     */     }
/* 164 */     if (this.showingDetail) {
/* 165 */       return this.detailView.mouseDragged(mouseX, mouseY, button);
/*     */     }
/* 167 */     return this.listView.mouseDragged(mouseX, mouseY, button);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean method_25406(double mouseX, double mouseY, int button) {
/* 173 */     if (this.modelViewer.isVisible()) {
/* 174 */       return this.modelViewer.mouseReleased(mouseX, mouseY, button);
/*     */     }
/* 176 */     if (this.showingDetail) {
/* 177 */       this.detailView.mouseReleased(mouseX, mouseY, button);
/*     */     } else {
/* 179 */       this.listView.mouseReleased(mouseX, mouseY, button);
/*     */     } 
/* 181 */     return super.method_25406(mouseX, mouseY, button);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean method_25404(int keyCode, int scanCode, int modifiers) {
/* 186 */     if (this.modelViewer.isVisible()) {
/* 187 */       return this.modelViewer.keyPressed(keyCode, scanCode, modifiers);
/*     */     }
/* 189 */     if (!this.showingDetail && this.listView.keyPressed(keyCode, scanCode, modifiers)) {
/* 190 */       return true;
/*     */     }
/* 192 */     return super.method_25404(keyCode, scanCode, modifiers);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean method_25400(char chr, int modifiers) {
/* 197 */     if (!this.showingDetail && this.listView.charTyped(chr, modifiers)) {
/* 198 */       return true;
/*     */     }
/* 200 */     return super.method_25400(chr, modifiers);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void onLineSelected(String lineId) {
/* 206 */     GuideSounds.playNavigate();
/* 207 */     this.showingDetail = true;
/* 208 */     SkinDexData.getInstance().setDetailLoading(true);
/* 209 */     SkinDexData.getInstance().clearClaimResult();
/* 210 */     SkinDexNetwork.requestSkinDexData("detail", lineId);
/*     */   }
/*     */   
/*     */   private void onBackToList() {
/* 214 */     GuideSounds.playClick();
/* 215 */     this.showingDetail = false;
/* 216 */     SkinDexData.getInstance().clearDetail();
/*     */     
/* 218 */     SkinDexData.getInstance().setLinesLoading(true);
/* 219 */     SkinDexNetwork.requestSkinDexData("lines", "");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ModelViewerOverlay getModelViewer() {
/* 226 */     return this.modelViewer;
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\skindex\screen\SkinDexScreen.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */