/*    */ package com.atlas.common.fabric.guide.screen.views;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ final class CategoryCard
/*    */   extends Record
/*    */ {
/*    */   private final String title;
/*    */   private final String subtitle;
/*    */   private final int accentColor;
/*    */   private final int tabIndex;
/*    */   private final String articlePath;
/*    */   
/*    */   public final String toString() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/guide/screen/views/LandingView$CategoryCard;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #32	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/fabric/guide/screen/views/LandingView$CategoryCard;
/*    */   }
/*    */   
/*    */   public final int hashCode() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/guide/screen/views/LandingView$CategoryCard;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #32	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/fabric/guide/screen/views/LandingView$CategoryCard;
/*    */   }
/*    */   
/*    */   public final boolean equals(Object o) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/guide/screen/views/LandingView$CategoryCard;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #32	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lcom/atlas/common/fabric/guide/screen/views/LandingView$CategoryCard;
/*    */     //   0	8	1	o	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   private CategoryCard(String title, String subtitle, int accentColor, int tabIndex, String articlePath) {
/* 32 */     this.title = title; this.subtitle = subtitle; this.accentColor = accentColor; this.tabIndex = tabIndex; this.articlePath = articlePath; } public String title() { return this.title; } public String subtitle() { return this.subtitle; } public int accentColor() { return this.accentColor; } public int tabIndex() { return this.tabIndex; } public String articlePath() { return this.articlePath; }
/*    */ 
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\guide\screen\views\LandingView$CategoryCard.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */