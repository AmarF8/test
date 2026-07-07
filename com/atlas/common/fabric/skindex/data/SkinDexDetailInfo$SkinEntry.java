/*    */ package com.atlas.common.fabric.skindex.data;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class SkinEntry
/*    */   extends Record
/*    */ {
/*    */   private final String id;
/*    */   private final String name;
/*    */   private final String pokemonId;
/*    */   private final String aspect;
/*    */   private final boolean unlocked;
/*    */   
/*    */   public final String toString() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/skindex/data/SkinDexDetailInfo$SkinEntry;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #28	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/fabric/skindex/data/SkinDexDetailInfo$SkinEntry;
/*    */   }
/*    */   
/*    */   public final int hashCode() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/skindex/data/SkinDexDetailInfo$SkinEntry;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #28	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/fabric/skindex/data/SkinDexDetailInfo$SkinEntry;
/*    */   }
/*    */   
/*    */   public final boolean equals(Object o) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/skindex/data/SkinDexDetailInfo$SkinEntry;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #28	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lcom/atlas/common/fabric/skindex/data/SkinDexDetailInfo$SkinEntry;
/*    */     //   0	8	1	o	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public SkinEntry(String id, String name, String pokemonId, String aspect, boolean unlocked) {
/* 28 */     this.id = id; this.name = name; this.pokemonId = pokemonId; this.aspect = aspect; this.unlocked = unlocked; } public String id() { return this.id; } public String name() { return this.name; } public String pokemonId() { return this.pokemonId; } public String aspect() { return this.aspect; } public boolean unlocked() { return this.unlocked; }
/*    */ 
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\skindex\data\SkinDexDetailInfo$SkinEntry.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */