/*    */ package com.atlas.common.fabric.ranked.data;
/*    */ public final class RankedShopItem extends Record {
/*    */   private final String id;
/*    */   private final String displayName;
/*    */   private final int trophyCost;
/*    */   private final String itemId;
/*    */   private final int quantity;
/*    */   private final String command;
/*    */   private final int purchaseLimit;
/*    */   
/* 11 */   public RankedShopItem(String id, String displayName, int trophyCost, String itemId, int quantity, String command, int purchaseLimit) { this.id = id; this.displayName = displayName; this.trophyCost = trophyCost; this.itemId = itemId; this.quantity = quantity; this.command = command; this.purchaseLimit = purchaseLimit; } public final String toString() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/ranked/data/RankedShopItem;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #11	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/* 11 */     //   0	7	0	this	Lcom/atlas/common/fabric/ranked/data/RankedShopItem; } public String id() { return this.id; } public final int hashCode() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/ranked/data/RankedShopItem;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #11	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/fabric/ranked/data/RankedShopItem; } public final boolean equals(Object o) { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/ranked/data/RankedShopItem;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #11	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lcom/atlas/common/fabric/ranked/data/RankedShopItem;
/* 11 */     //   0	8	1	o	Ljava/lang/Object; } public String displayName() { return this.displayName; } public int trophyCost() { return this.trophyCost; } public String itemId() { return this.itemId; } public int quantity() { return this.quantity; } public String command() { return this.command; } public int purchaseLimit() { return this.purchaseLimit; }
/*    */ 
/*    */   
/* 14 */   private static List<RankedShopItem> SHOP_ITEMS = createDefaults();
/*    */ 
/*    */ 
/*    */   
/*    */   public static void configure(List<RankedShopItem> items) {
/* 19 */     if (items != null && !items.isEmpty()) {
/* 20 */       SHOP_ITEMS = Collections.unmodifiableList(new ArrayList<>(items));
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static List<RankedShopItem> getAllItems() {
/* 27 */     return SHOP_ITEMS;
/*    */   }
/*    */   
/*    */   public static RankedShopItem getById(String id) {
/* 31 */     for (RankedShopItem item : SHOP_ITEMS) {
/* 32 */       if (item.id().equals(id)) {
/* 33 */         return item;
/*    */       }
/*    */     } 
/* 36 */     return null;
/*    */   }
/*    */   
/*    */   public boolean isCommandItem() {
/* 40 */     return (this.command != null && !this.command.isEmpty());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private static List<RankedShopItem> createDefaults() {
/* 46 */     List<RankedShopItem> items = new ArrayList<>();
/*    */ 
/*    */     
/* 49 */     items.add(new RankedShopItem("crate_1_key", "Crate 1 Key", 1000, "", 1, "crate give {player} CRATE_1 1", 5));
/*    */     
/* 51 */     items.add(new RankedShopItem("crate_2_key", "Crate 2 Key", 2000, "", 1, "crate give {player} CRATE_2 1", 5));
/*    */     
/* 53 */     items.add(new RankedShopItem("crate_3_key", "Crate 3 Key", 3000, "", 1, "crate give {player} CRATE_3 1", 5));
/*    */     
/* 55 */     items.add(new RankedShopItem("monthly_key", "Monthly Key", 5000, "", 1, "crate give {player} MONTHLY 1", 1));
/*    */ 
/*    */ 
/*    */     
/* 59 */     items.add(new RankedShopItem("random_mega_stone", "Random Mega Stone", 200, "", 1, "pokegive {player} random_mega_stone", 10));
/*    */     
/* 61 */     items.add(new RankedShopItem("rare_candy", "Rare Candy", 500, "cobblemon:rare_candy", 1, "", 25));
/*    */     
/* 63 */     items.add(new RankedShopItem("master_ball", "Master Ball", 5000, "cobblemon:master_ball", 1, "", 1));
/*    */     
/* 65 */     items.add(new RankedShopItem("iv_max", "IV Max", 10000, "", 1, "pokegive {player} iv_max", 1));
/*    */ 
/*    */ 
/*    */     
/* 69 */     items.add(new RankedShopItem("random_tier1_station", "Random Tier 1 Station", 2500, "", 1, "station give {player} random_tier1", 3));
/*    */     
/* 71 */     items.add(new RankedShopItem("random_tier2_station", "Random Tier 2 Station", 5000, "", 1, "station give {player} random_tier2", 3));
/*    */     
/* 73 */     items.add(new RankedShopItem("random_tier3_station", "Random Tier 3 Station", 7500, "", 1, "station give {player} random_tier3", 3));
/*    */     
/* 75 */     items.add(new RankedShopItem("random_tier4_station", "Random Tier 4 Station", 10000, "", 1, "station give {player} random_tier4", 1));
/*    */     
/* 77 */     items.add(new RankedShopItem("random_tier5_station", "Random Tier 5 Station", 15000, "", 1, "station give {player} random_tier5", 1));
/*    */ 
/*    */ 
/*    */     
/* 81 */     items.add(new RankedShopItem("shop_cosmetic_1", "Shop Cosmetic 1", 1000, "", 1, "cosmetic give {player} shop_cosmetic_1", 1));
/*    */     
/* 83 */     items.add(new RankedShopItem("shop_cosmetic_2", "Shop Cosmetic 2", 1000, "", 1, "cosmetic give {player} shop_cosmetic_2", 1));
/*    */     
/* 85 */     items.add(new RankedShopItem("shop_cosmetic_3", "Shop Cosmetic 3", 1000, "", 1, "cosmetic give {player} shop_cosmetic_3", 1));
/*    */     
/* 87 */     items.add(new RankedShopItem("shop_cosmetic_4", "Shop Cosmetic 4", 1000, "", 1, "cosmetic give {player} shop_cosmetic_4", 1));
/*    */     
/* 89 */     items.add(new RankedShopItem("shop_cosmetic_5", "Shop Cosmetic 5", 1000, "", 1, "cosmetic give {player} shop_cosmetic_5", 1));
/*    */ 
/*    */     
/* 92 */     return Collections.unmodifiableList(items);
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\ranked\data\RankedShopItem.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */