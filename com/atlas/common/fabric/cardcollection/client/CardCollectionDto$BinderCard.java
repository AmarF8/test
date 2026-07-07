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
/*    */ public final class BinderCard
/*    */   extends Record
/*    */ {
/*    */   private final String id;
/*    */   private final int number;
/*    */   private final String name;
/*    */   private final String rarity;
/*    */   private final String rarityName;
/*    */   private final String texture;
/*    */   private final int owned;
/*    */   private final int karmaValue;
/*    */   private final boolean serialized;
/*    */   private final int serial;
/*    */   private final int minted;
/*    */   private final List<Integer> serials;
/*    */   
/*    */   public final String toString() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$BinderCard;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #48	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$BinderCard;
/*    */   }
/*    */   
/*    */   public final int hashCode() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$BinderCard;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #48	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$BinderCard;
/*    */   }
/*    */   
/*    */   public final boolean equals(Object o) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$BinderCard;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #48	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$BinderCard;
/*    */     //   0	8	1	o	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public BinderCard(String id, int number, String name, String rarity, String rarityName, String texture, int owned, int karmaValue, boolean serialized, int serial, int minted, List<Integer> serials) {
/* 48 */     this.id = id; this.number = number; this.name = name; this.rarity = rarity; this.rarityName = rarityName; this.texture = texture; this.owned = owned; this.karmaValue = karmaValue; this.serialized = serialized; this.serial = serial; this.minted = minted; this.serials = serials; } public String id() { return this.id; } public int number() { return this.number; } public String name() { return this.name; } public String rarity() { return this.rarity; } public String rarityName() { return this.rarityName; } public String texture() { return this.texture; } public int owned() { return this.owned; } public int karmaValue() { return this.karmaValue; } public boolean serialized() { return this.serialized; } public int serial() { return this.serial; } public int minted() { return this.minted; } public List<Integer> serials() { return this.serials; }
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
/*    */   public boolean isOwned() {
/* 62 */     return (this.owned > 0);
/*    */   } public boolean hasSerial() {
/* 64 */     return (this.serialized && this.serial > 0);
/*    */   } public int serialCount() {
/* 66 */     return (this.serials == null) ? 0 : this.serials.size();
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\cardcollection\client\CardCollectionDto$BinderCard.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */