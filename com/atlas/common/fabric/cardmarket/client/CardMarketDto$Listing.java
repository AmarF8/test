/*    */ package com.atlas.common.fabric.cardmarket.client;public final class Listing extends Record { private final String id; private final String sellerName; private final String cardId;
/*    */   private final int number;
/*    */   private final String cardName;
/*    */   private final String rarity;
/*    */   private final String rarityName;
/*    */   private final String texture;
/*    */   private final int serial;
/*    */   private final String kind;
/*    */   private final long priceCoins;
/*    */   private final long priceUsdCents;
/*    */   private final boolean mine;
/*    */   
/*    */   public final String toString() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/cardmarket/client/CardMarketDto$Listing;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #21	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/fabric/cardmarket/client/CardMarketDto$Listing;
/*    */   }
/*    */   
/*    */   public final int hashCode() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/cardmarket/client/CardMarketDto$Listing;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #21	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/fabric/cardmarket/client/CardMarketDto$Listing;
/*    */   }
/*    */   
/* 21 */   public Listing(String id, String sellerName, String cardId, int number, String cardName, String rarity, String rarityName, String texture, int serial, String kind, long priceCoins, long priceUsdCents, boolean mine) { this.id = id; this.sellerName = sellerName; this.cardId = cardId; this.number = number; this.cardName = cardName; this.rarity = rarity; this.rarityName = rarityName; this.texture = texture; this.serial = serial; this.kind = kind; this.priceCoins = priceCoins; this.priceUsdCents = priceUsdCents; this.mine = mine; } public String id() { return this.id; } public final boolean equals(Object o) { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/cardmarket/client/CardMarketDto$Listing;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #21	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lcom/atlas/common/fabric/cardmarket/client/CardMarketDto$Listing;
/* 21 */     //   0	8	1	o	Ljava/lang/Object; } public String sellerName() { return this.sellerName; } public String cardId() { return this.cardId; } public int number() { return this.number; } public String cardName() { return this.cardName; } public String rarity() { return this.rarity; } public String rarityName() { return this.rarityName; } public String texture() { return this.texture; } public int serial() { return this.serial; } public String kind() { return this.kind; } public long priceCoins() { return this.priceCoins; } public long priceUsdCents() { return this.priceUsdCents; } public boolean mine() { return this.mine; }
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
/*    */   public boolean isCoins() {
/* 36 */     return "COINS".equals(this.kind);
/*    */   } }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\cardmarket\client\CardMarketDto$Listing.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */