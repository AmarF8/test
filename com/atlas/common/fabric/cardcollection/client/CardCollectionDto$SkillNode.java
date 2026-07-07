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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class SkillNode
/*     */   extends Record
/*     */ {
/*     */   private final String id;
/*     */   private final String name;
/*     */   private final String description;
/*     */   private final int level;
/*     */   private final int maxLevel;
/*     */   private final int cost;
/*     */   private final double percentPerLevel;
/*     */   private final double currentPercent;
/*     */   private final boolean unlocked;
/*     */   private final boolean available;
/*     */   
/*     */   public final String toString() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$SkillNode;)Ljava/lang/String;
/*     */     //   6: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #121	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	7	0	this	Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$SkillNode;
/*     */   }
/*     */   
/*     */   public final int hashCode() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$SkillNode;)I
/*     */     //   6: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #121	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	7	0	this	Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$SkillNode;
/*     */   }
/*     */   
/*     */   public final boolean equals(Object o) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$SkillNode;Ljava/lang/Object;)Z
/*     */     //   7: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #121	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	8	0	this	Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$SkillNode;
/*     */     //   0	8	1	o	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public SkillNode(String id, String name, String description, int level, int maxLevel, int cost, double percentPerLevel, double currentPercent, boolean unlocked, boolean available) {
/* 121 */     this.id = id; this.name = name; this.description = description; this.level = level; this.maxLevel = maxLevel; this.cost = cost; this.percentPerLevel = percentPerLevel; this.currentPercent = currentPercent; this.unlocked = unlocked; this.available = available; } public String id() { return this.id; } public String name() { return this.name; } public String description() { return this.description; } public int level() { return this.level; } public int maxLevel() { return this.maxLevel; } public int cost() { return this.cost; } public double percentPerLevel() { return this.percentPerLevel; } public double currentPercent() { return this.currentPercent; } public boolean unlocked() { return this.unlocked; } public boolean available() { return this.available; }
/*     */ 
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\cardcollection\client\CardCollectionDto$SkillNode.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */