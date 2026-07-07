/*    */ package com.atlas.common.fabric.cardcollection.client;
/*    */ 
/*    */ import java.util.List;
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
/*    */ public final class BinderData
/*    */   extends Record
/*    */ {
/*    */   private final String setId;
/*    */   private final String setName;
/*    */   private final int page;
/*    */   private final int totalPages;
/*    */   private final int pageSize;
/*    */   private final int uniqueOwned;
/*    */   private final int totalUnique;
/*    */   private final int totalOwned;
/*    */   private final int completion;
/*    */   private final CardCollectionDto.BurnResult burnResult;
/*    */   private final List<CardCollectionDto.BinderCard> cards;
/*    */   
/*    */   public final String toString() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$BinderData;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #73	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$BinderData;
/*    */   }
/*    */   
/*    */   public final int hashCode() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$BinderData;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #73	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$BinderData;
/*    */   }
/*    */   
/*    */   public final boolean equals(Object o) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$BinderData;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #73	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$BinderData;
/*    */     //   0	8	1	o	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public BinderData(String setId, String setName, int page, int totalPages, int pageSize, int uniqueOwned, int totalUnique, int totalOwned, int completion, CardCollectionDto.BurnResult burnResult, List<CardCollectionDto.BinderCard> cards) {
/* 73 */     this.setId = setId; this.setName = setName; this.page = page; this.totalPages = totalPages; this.pageSize = pageSize; this.uniqueOwned = uniqueOwned; this.totalUnique = totalUnique; this.totalOwned = totalOwned; this.completion = completion; this.burnResult = burnResult; this.cards = cards; } public String setId() { return this.setId; } public String setName() { return this.setName; } public int page() { return this.page; } public int totalPages() { return this.totalPages; } public int pageSize() { return this.pageSize; } public int uniqueOwned() { return this.uniqueOwned; } public int totalUnique() { return this.totalUnique; } public int totalOwned() { return this.totalOwned; } public int completion() { return this.completion; } public CardCollectionDto.BurnResult burnResult() { return this.burnResult; } public List<CardCollectionDto.BinderCard> cards() { return this.cards; }
/*    */ 
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\cardcollection\client\CardCollectionDto$BinderData.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */