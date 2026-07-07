/*    */ package com.atlas.common.fabric.guide.screen.views;
/*    */ 
/*    */ import com.atlas.common.fabric.guide.screen.GuideColors;
/*    */ import net.minecraft.class_327;
/*    */ import net.minecraft.class_332;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PlaceholderView
/*    */ {
/*    */   private final String tabName;
/*    */   
/*    */   public PlaceholderView(String tabName) {
/* 16 */     this.tabName = tabName;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(class_332 ctx, class_327 textRenderer, int x, int y, int width, int height, int mouseX, int mouseY, float delta) {
/* 22 */     ctx.method_25294(x, y, x + width, y + height, GuideColors.SECTION_BG);
/*    */ 
/*    */     
/* 25 */     int centerX = x + width / 2;
/* 26 */     int centerY = y + height / 2;
/*    */ 
/*    */     
/* 29 */     ctx.method_25300(textRenderer, this.tabName, centerX, centerY - 16, GuideColors.TEXT_ACCENT);
/*    */ 
/*    */     
/* 32 */     ctx.method_25300(textRenderer, "Coming Soon", centerX, centerY, GuideColors.TEXT_DIM);
/*    */ 
/*    */     
/* 35 */     int lineW = 60;
/* 36 */     ctx.method_25294(centerX - lineW / 2, centerY + 14, centerX + lineW / 2, centerY + 15, GuideColors.BORDER_DIM);
/*    */   }
/*    */   
/* 39 */   public boolean mouseClicked(double mouseX, double mouseY, int button) { return false; }
/* 40 */   public boolean mouseScrolled(double mouseX, double mouseY, double horizontal, double vertical) { return false; }
/* 41 */   public boolean keyPressed(int keyCode, int scanCode, int modifiers) { return false; } public boolean charTyped(char chr, int modifiers) {
/* 42 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\guide\screen\views\PlaceholderView.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */