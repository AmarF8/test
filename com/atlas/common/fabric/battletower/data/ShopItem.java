/*     */ package com.atlas.common.fabric.battletower.data;
/*     */ public final class ShopItem extends Record {
/*     */   private final String id;
/*     */   private final String displayName;
/*     */   private final int bpCost;
/*     */   private final class_2960 itemId;
/*     */   
/*   8 */   public String id() { return this.id; } private final int quantity; private final String command; public final String toString() { // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/battletower/data/ShopItem;)Ljava/lang/String;
/*     */     //   6: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #8	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	7	0	this	Lcom/atlas/common/fabric/battletower/data/ShopItem; } public final int hashCode() { // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/battletower/data/ShopItem;)I
/*     */     //   6: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #8	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	7	0	this	Lcom/atlas/common/fabric/battletower/data/ShopItem; } public final boolean equals(Object o) { // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/battletower/data/ShopItem;Ljava/lang/Object;)Z
/*     */     //   7: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #8	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	8	0	this	Lcom/atlas/common/fabric/battletower/data/ShopItem;
/*   8 */     //   0	8	1	o	Ljava/lang/Object; } public String displayName() { return this.displayName; } public int bpCost() { return this.bpCost; } public class_2960 itemId() { return this.itemId; } public int quantity() { return this.quantity; } public String command() { return this.command; }
/*     */   
/*  10 */   private static List<ShopItem> SHOP_ITEMS = createDefaults();
/*     */ 
/*     */ 
/*     */   
/*     */   public static void configure(List<ShopItem> items) {
/*  15 */     if (items != null && !items.isEmpty()) {
/*  16 */       SHOP_ITEMS = Collections.unmodifiableList(new ArrayList<>(items));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static List<ShopItem> getAllItems() {
/*  23 */     return SHOP_ITEMS;
/*     */   }
/*     */   
/*     */   public static ShopItem getById(String id) {
/*  27 */     for (ShopItem item : SHOP_ITEMS) {
/*  28 */       if (item.id().equals(id)) {
/*  29 */         return item;
/*     */       }
/*     */     } 
/*  32 */     return null;
/*     */   }
/*     */   
/*     */   public ShopItem(String id, String displayName, int bpCost, class_2960 itemId, int quantity) {
/*  36 */     this(id, displayName, bpCost, itemId, quantity, (String)null);
/*     */   }
/*     */   
/*     */   public ShopItem(String id, String displayName, int bpCost, class_2960 itemId, int quantity, String command) {
/*  40 */     this.id = id;
/*  41 */     this.displayName = displayName;
/*  42 */     this.bpCost = bpCost;
/*  43 */     this.itemId = itemId;
/*  44 */     this.quantity = quantity;
/*  45 */     this.command = command;
/*     */   }
/*     */   
/*     */   public boolean isCommandItem() {
/*  49 */     return (this.command != null && !this.command.isEmpty());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static List<ShopItem> createDefaults() {
/*  55 */     List<ShopItem> items = new ArrayList<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 138 */     return Collections.unmodifiableList(items);
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\battletower\data\ShopItem.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */