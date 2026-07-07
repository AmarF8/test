/*    */ package com.atlas.common.fabric.cardcollection.client;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ public final class SetOverview extends Record {
/*    */   private final String id;
/*    */   private final String name;
/*    */   private final String coverTexture;
/*    */   private final int uniqueOwned;
/*    */   private final int totalUnique;
/*    */   private final int totalOwned;
/*    */   private final int packsOwned;
/*    */   private final int completion;
/*    */   private final int nextMilestone;
/*    */   private final List<CardCollectionDto.MilestoneChip> milestones;
/*    */   
/*    */   public final String toString() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$SetOverview;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #29	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$SetOverview;
/*    */   }
/*    */   
/*    */   public final int hashCode() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$SetOverview;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #29	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$SetOverview;
/*    */   }
/*    */   
/*    */   public final boolean equals(Object o) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$SetOverview;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #29	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$SetOverview;
/*    */     //   0	8	1	o	Ljava/lang/Object;
/*    */   }
/*    */   
/* 29 */   public SetOverview(String id, String name, String coverTexture, int uniqueOwned, int totalUnique, int totalOwned, int packsOwned, int completion, int nextMilestone, List<CardCollectionDto.MilestoneChip> milestones) { this.id = id; this.name = name; this.coverTexture = coverTexture; this.uniqueOwned = uniqueOwned; this.totalUnique = totalUnique; this.totalOwned = totalOwned; this.packsOwned = packsOwned; this.completion = completion; this.nextMilestone = nextMilestone; this.milestones = milestones; } public String id() { return this.id; } public String name() { return this.name; } public String coverTexture() { return this.coverTexture; } public int uniqueOwned() { return this.uniqueOwned; } public int totalUnique() { return this.totalUnique; } public int totalOwned() { return this.totalOwned; } public int packsOwned() { return this.packsOwned; } public int completion() { return this.completion; } public int nextMilestone() { return this.nextMilestone; } public List<CardCollectionDto.MilestoneChip> milestones() { return this.milestones; }
/*    */ 
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\cardcollection\client\CardCollectionDto$SetOverview.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */