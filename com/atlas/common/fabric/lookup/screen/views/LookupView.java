/*    */ package com.atlas.common.fabric.lookup.screen.views;
/*    */ 
/*    */ import net.minecraft.class_327;
/*    */ import net.minecraft.class_332;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface LookupView
/*    */ {
/*    */   void init(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
/*    */   
/*    */   void render(class_332 paramclass_332, int paramInt1, int paramInt2, float paramFloat, class_327 paramclass_327);
/*    */   
/*    */   default boolean mouseClicked(double mouseX, double mouseY, int button) {
/* 26 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   default boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
/* 32 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   default boolean keyPressed(int keyCode, int scanCode, int modifiers) {
/* 38 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   default boolean charTyped(char chr, int modifiers) {
/* 44 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   default boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
/* 50 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   default boolean mouseReleased(double mouseX, double mouseY, int button) {
/* 56 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\lookup\screen\views\LookupView.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */