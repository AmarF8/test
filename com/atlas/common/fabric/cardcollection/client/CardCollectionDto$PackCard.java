/*     */ package com.atlas.common.fabric.cardcollection.client;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class PackCard
/*     */   extends Record
/*     */ {
/*     */   private final String id;
/*     */   private final int number;
/*     */   private final String name;
/*     */   private final String rarity;
/*     */   private final String rarityName;
/*     */   private final String texture;
/*     */   private final int owned;
/*     */   private final boolean isNew;
/*     */   private final int weight;
/*     */   private final boolean serialized;
/*     */   private final int serial;
/*     */   private final int minted;
/*     */   
/*     */   public final String toString() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$PackCard;)Ljava/lang/String;
/*     */     //   6: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #88	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	7	0	this	Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$PackCard;
/*     */   }
/*     */   
/*     */   public final int hashCode() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$PackCard;)I
/*     */     //   6: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #88	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	7	0	this	Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$PackCard;
/*     */   }
/*     */   
/*     */   public final boolean equals(Object o) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$PackCard;Ljava/lang/Object;)Z
/*     */     //   7: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #88	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	8	0	this	Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$PackCard;
/*     */     //   0	8	1	o	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public PackCard(String id, int number, String name, String rarity, String rarityName, String texture, int owned, boolean isNew, int weight, boolean serialized, int serial, int minted) {
/*  88 */     this.id = id; this.number = number; this.name = name; this.rarity = rarity; this.rarityName = rarityName; this.texture = texture; this.owned = owned; this.isNew = isNew; this.weight = weight; this.serialized = serialized; this.serial = serial; this.minted = minted; } public String id() { return this.id; } public int number() { return this.number; } public String name() { return this.name; } public String rarity() { return this.rarity; } public String rarityName() { return this.rarityName; } public String texture() { return this.texture; } public int owned() { return this.owned; } public boolean isNew() { return this.isNew; } public int weight() { return this.weight; } public boolean serialized() { return this.serialized; } public int serial() { return this.serial; } public int minted() { return this.minted; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasSerial() {
/* 102 */     return (this.serialized && this.serial > 0);
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\cardcollection\client\CardCollectionDto$PackCard.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */