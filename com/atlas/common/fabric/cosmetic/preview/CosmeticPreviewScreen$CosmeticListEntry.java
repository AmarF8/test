/*    */ package com.atlas.common.fabric.cosmetic.preview;
/*    */ 
/*    */ import com.atlas.common.fabric.cosmetic.Cosmetic;
/*    */ import com.atlas.common.fabric.cosmetic.CosmeticVariant;
/*    */ import org.jetbrains.annotations.Nullable;
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
/*    */ final class CosmeticListEntry
/*    */   extends Record
/*    */ {
/*    */   private final Cosmetic cosmetic;
/*    */   @Nullable
/*    */   private final CosmeticVariant variant;
/*    */   private final boolean isVariant;
/*    */   private final boolean expanded;
/*    */   
/*    */   public final String toString() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/cosmetic/preview/CosmeticPreviewScreen$CosmeticListEntry;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #96	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/fabric/cosmetic/preview/CosmeticPreviewScreen$CosmeticListEntry;
/*    */   }
/*    */   
/*    */   public final int hashCode() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/cosmetic/preview/CosmeticPreviewScreen$CosmeticListEntry;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #96	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/fabric/cosmetic/preview/CosmeticPreviewScreen$CosmeticListEntry;
/*    */   }
/*    */   
/*    */   public final boolean equals(Object o) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/cosmetic/preview/CosmeticPreviewScreen$CosmeticListEntry;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #96	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lcom/atlas/common/fabric/cosmetic/preview/CosmeticPreviewScreen$CosmeticListEntry;
/*    */     //   0	8	1	o	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   private CosmeticListEntry(Cosmetic cosmetic, @Nullable CosmeticVariant variant, boolean isVariant, boolean expanded) {
/* 96 */     this.cosmetic = cosmetic; this.variant = variant; this.isVariant = isVariant; this.expanded = expanded; } public Cosmetic cosmetic() { return this.cosmetic; } @Nullable public CosmeticVariant variant() { return this.variant; } public boolean isVariant() { return this.isVariant; } public boolean expanded() { return this.expanded; }
/*    */ 
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\cosmetic\preview\CosmeticPreviewScreen$CosmeticListEntry.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */