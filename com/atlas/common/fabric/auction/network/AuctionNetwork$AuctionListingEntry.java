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
/*     */ public final class AuctionListingEntry
/*     */   extends Record
/*     */ {
/*     */   private final String auctionId;
/*     */   private final String itemName;
/*     */   private final String sellerName;
/*     */   private final double price;
/*     */   private final String auctionType;
/*     */   private final String itemType;
/*     */   private final int remainingSeconds;
/*     */   private final int itemCount;
/*     */   private final class_1799 displayStack;
/*     */   private final String speciesId;
/*     */   private final long expiresAt;
/*     */   private final int ivPercentage;
/*     */   
/*     */   public final String toString() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/auction/network/AuctionNetwork$AuctionListingEntry;)Ljava/lang/String;
/*     */     //   6: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #184	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	7	0	this	Lcom/atlas/common/fabric/auction/network/AuctionNetwork$AuctionListingEntry;
/*     */   }
/*     */   
/*     */   public final int hashCode() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/auction/network/AuctionNetwork$AuctionListingEntry;)I
/*     */     //   6: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #184	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	7	0	this	Lcom/atlas/common/fabric/auction/network/AuctionNetwork$AuctionListingEntry;
/*     */   }
/*     */   
/*     */   public final boolean equals(Object o) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/auction/network/AuctionNetwork$AuctionListingEntry;Ljava/lang/Object;)Z
/*     */     //   7: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #184	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	8	0	this	Lcom/atlas/common/fabric/auction/network/AuctionNetwork$AuctionListingEntry;
/*     */     //   0	8	1	o	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public AuctionListingEntry(String auctionId, String itemName, String sellerName, double price, String auctionType, String itemType, int remainingSeconds, int itemCount, class_1799 displayStack, String speciesId, long expiresAt, int ivPercentage) {
/* 184 */     this.auctionId = auctionId; this.itemName = itemName; this.sellerName = sellerName; this.price = price; this.auctionType = auctionType; this.itemType = itemType; this.remainingSeconds = remainingSeconds; this.itemCount = itemCount; this.displayStack = displayStack; this.speciesId = speciesId; this.expiresAt = expiresAt; this.ivPercentage = ivPercentage; } public String auctionId() { return this.auctionId; } public String itemName() { return this.itemName; } public String sellerName() { return this.sellerName; } public double price() { return this.price; } public String auctionType() { return this.auctionType; } public String itemType() { return this.itemType; } public int remainingSeconds() { return this.remainingSeconds; } public int itemCount() { return this.itemCount; } public class_1799 displayStack() { return this.displayStack; } public String speciesId() { return this.speciesId; } public long expiresAt() { return this.expiresAt; } public int ivPercentage() { return this.ivPercentage; }
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
/* 199 */     return (this.speciesId != null && !this.speciesId.isEmpty());
/*     */   }
/*     */ 
/*     */   
/*     */   public int computeRemainingSeconds() {
/* 204 */     int secs = (int)((this.expiresAt - System.currentTimeMillis()) / 1000L);
/* 205 */     return Math.max(0, secs);
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\auction\network\AuctionNetwork$AuctionListingEntry.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */