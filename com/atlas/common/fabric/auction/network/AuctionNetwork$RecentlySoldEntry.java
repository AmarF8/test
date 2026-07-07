/*     */ package com.atlas.common.fabric.auction.network;
/*     */ 
/*     */ import net.minecraft.class_1799;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class RecentlySoldEntry
/*     */   extends Record
/*     */ {
/*     */   private final String auctionId;
/*     */   private final String itemName;
/*     */   private final String sellerName;
/*     */   private final String buyerName;
/*     */   private final double price;
/*     */   private final String auctionType;
/*     */   private final String saleType;
/*     */   private final String itemType;
/*     */   private final int itemCount;
/*     */   private final class_1799 displayStack;
/*     */   private final String speciesId;
/*     */   private final long soldAt;
/*     */   private final int ivPercentage;
/*     */   
/*     */   public final String toString() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/auction/network/AuctionNetwork$RecentlySoldEntry;)Ljava/lang/String;
/*     */     //   6: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #227	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	7	0	this	Lcom/atlas/common/fabric/auction/network/AuctionNetwork$RecentlySoldEntry;
/*     */   }
/*     */   
/*     */   public final int hashCode() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/auction/network/AuctionNetwork$RecentlySoldEntry;)I
/*     */     //   6: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #227	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	7	0	this	Lcom/atlas/common/fabric/auction/network/AuctionNetwork$RecentlySoldEntry;
/*     */   }
/*     */   
/*     */   public final boolean equals(Object o) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/auction/network/AuctionNetwork$RecentlySoldEntry;Ljava/lang/Object;)Z
/*     */     //   7: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #227	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	8	0	this	Lcom/atlas/common/fabric/auction/network/AuctionNetwork$RecentlySoldEntry;
/*     */     //   0	8	1	o	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public RecentlySoldEntry(String auctionId, String itemName, String sellerName, String buyerName, double price, String auctionType, String saleType, String itemType, int itemCount, class_1799 displayStack, String speciesId, long soldAt, int ivPercentage) {
/* 227 */     this.auctionId = auctionId; this.itemName = itemName; this.sellerName = sellerName; this.buyerName = buyerName; this.price = price; this.auctionType = auctionType; this.saleType = saleType; this.itemType = itemType; this.itemCount = itemCount; this.displayStack = displayStack; this.speciesId = speciesId; this.soldAt = soldAt; this.ivPercentage = ivPercentage; } public String auctionId() { return this.auctionId; } public String itemName() { return this.itemName; } public String sellerName() { return this.sellerName; } public String buyerName() { return this.buyerName; } public double price() { return this.price; } public String auctionType() { return this.auctionType; } public String saleType() { return this.saleType; } public String itemType() { return this.itemType; } public int itemCount() { return this.itemCount; } public class_1799 displayStack() { return this.displayStack; } public String speciesId() { return this.speciesId; } public long soldAt() { return this.soldAt; } public int ivPercentage() { return this.ivPercentage; }
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
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isPokemon() {
/* 243 */     return (this.speciesId != null && !this.speciesId.isEmpty());
/*     */   }
/*     */   
/*     */   public boolean wasBidWon() {
/* 247 */     return "SOLD".equalsIgnoreCase(this.saleType);
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\auction\network\AuctionNetwork$RecentlySoldEntry.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */