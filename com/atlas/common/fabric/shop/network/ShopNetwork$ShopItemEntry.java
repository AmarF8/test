/*    */ package com.atlas.common.fabric.shop.network;
/*    */ 
/*    */ import net.minecraft.class_1799;
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
/*    */ public final class ShopItemEntry
/*    */   extends Record
/*    */ {
/*    */   private final String identifier;
/*    */   private final String name;
/*    */   private final double buyPrice;
/*    */   private final double sellPrice;
/*    */   private final String currency;
/*    */   private final int stockRemaining;
/*    */   private final class_1799 displayStack;
/*    */   private final int playerBuyLimit;
/*    */   private final int playerSellLimit;
/*    */   private final int playerBuyRemaining;
/*    */   private final int playerSellRemaining;
/*    */   private final long limitDurationSeconds;
/*    */   
/*    */   public final String toString() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/shop/network/ShopNetwork$ShopItemEntry;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #80	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/fabric/shop/network/ShopNetwork$ShopItemEntry;
/*    */   }
/*    */   
/*    */   public final int hashCode() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/shop/network/ShopNetwork$ShopItemEntry;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #80	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/fabric/shop/network/ShopNetwork$ShopItemEntry;
/*    */   }
/*    */   
/*    */   public final boolean equals(Object o) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/shop/network/ShopNetwork$ShopItemEntry;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #80	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lcom/atlas/common/fabric/shop/network/ShopNetwork$ShopItemEntry;
/*    */     //   0	8	1	o	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public ShopItemEntry(String identifier, String name, double buyPrice, double sellPrice, String currency, int stockRemaining, class_1799 displayStack, int playerBuyLimit, int playerSellLimit, int playerBuyRemaining, int playerSellRemaining, long limitDurationSeconds) {
/* 80 */     this.identifier = identifier; this.name = name; this.buyPrice = buyPrice; this.sellPrice = sellPrice; this.currency = currency; this.stockRemaining = stockRemaining; this.displayStack = displayStack; this.playerBuyLimit = playerBuyLimit; this.playerSellLimit = playerSellLimit; this.playerBuyRemaining = playerBuyRemaining; this.playerSellRemaining = playerSellRemaining; this.limitDurationSeconds = limitDurationSeconds; } public String identifier() { return this.identifier; } public String name() { return this.name; } public double buyPrice() { return this.buyPrice; } public double sellPrice() { return this.sellPrice; } public String currency() { return this.currency; } public int stockRemaining() { return this.stockRemaining; } public class_1799 displayStack() { return this.displayStack; } public int playerBuyLimit() { return this.playerBuyLimit; } public int playerSellLimit() { return this.playerSellLimit; } public int playerBuyRemaining() { return this.playerBuyRemaining; } public int playerSellRemaining() { return this.playerSellRemaining; } public long limitDurationSeconds() { return this.limitDurationSeconds; }
/*    */ 
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\shop\network\ShopNetwork$ShopItemEntry.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */