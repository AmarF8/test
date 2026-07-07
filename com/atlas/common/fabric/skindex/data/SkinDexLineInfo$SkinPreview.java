/*    */ package com.atlas.common.fabric.skindex.data;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class SkinPreview
/*    */   extends Record
/*    */ {
/*    */   private final String id;
/*    */   private final String name;
/*    */   private final String pokemonId;
/*    */   private final String aspect;
/*    */   
/*    */   public final String toString() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/skindex/data/SkinDexLineInfo$SkinPreview;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #27	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/fabric/skindex/data/SkinDexLineInfo$SkinPreview;
/*    */   }
/*    */   
/*    */   public final int hashCode() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/skindex/data/SkinDexLineInfo$SkinPreview;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #27	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/fabric/skindex/data/SkinDexLineInfo$SkinPreview;
/*    */   }
/*    */   
/*    */   public final boolean equals(Object o) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/skindex/data/SkinDexLineInfo$SkinPreview;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #27	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lcom/atlas/common/fabric/skindex/data/SkinDexLineInfo$SkinPreview;
/*    */     //   0	8	1	o	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public SkinPreview(String id, String name, String pokemonId, String aspect) {
/* 27 */     this.id = id; this.name = name; this.pokemonId = pokemonId; this.aspect = aspect; } public String id() { return this.id; } public String name() { return this.name; } public String pokemonId() { return this.pokemonId; } public String aspect() { return this.aspect; }
/*    */ 
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\skindex\data\SkinDexLineInfo$SkinPreview.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */