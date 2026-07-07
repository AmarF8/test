/*    */ package com.atlas.common.fabric.cardmarket.client;
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
/*    */ public final class Snapshot
/*    */   extends Record
/*    */ {
/*    */   private final String filter;
/*    */   private final long viewerCoins;
/*    */   private final long viewerCreditCents;
/*    */   private final List<CardMarketDto.Listing> listings;
/*    */   
/*    */   public final String toString() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/cardmarket/client/CardMarketDto$Snapshot;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #40	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/fabric/cardmarket/client/CardMarketDto$Snapshot;
/*    */   }
/*    */   
/*    */   public final int hashCode() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/cardmarket/client/CardMarketDto$Snapshot;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #40	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/fabric/cardmarket/client/CardMarketDto$Snapshot;
/*    */   }
/*    */   
/*    */   public final boolean equals(Object o) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/cardmarket/client/CardMarketDto$Snapshot;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #40	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lcom/atlas/common/fabric/cardmarket/client/CardMarketDto$Snapshot;
/*    */     //   0	8	1	o	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public Snapshot(String filter, long viewerCoins, long viewerCreditCents, List<CardMarketDto.Listing> listings) {
/* 40 */     this.filter = filter; this.viewerCoins = viewerCoins; this.viewerCreditCents = viewerCreditCents; this.listings = listings; } public String filter() { return this.filter; } public long viewerCoins() { return this.viewerCoins; } public long viewerCreditCents() { return this.viewerCreditCents; } public List<CardMarketDto.Listing> listings() { return this.listings; }
/*    */ 
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\cardmarket\client\CardMarketDto$Snapshot.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */