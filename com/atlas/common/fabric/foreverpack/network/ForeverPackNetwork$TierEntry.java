/*    */ package com.atlas.common.fabric.foreverpack.network;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class TierEntry
/*    */   extends Record
/*    */ {
/*    */   private final int ladderIndex;
/*    */   private final boolean paid;
/*    */   private final boolean milestone;
/*    */   private final int cost;
/*    */   private final int paidIndex;
/*    */   private final boolean claimed;
/*    */   private final List<ForeverPackNetwork.RewardEntry> rewards;
/*    */   
/*    */   public final String toString() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/foreverpack/network/ForeverPackNetwork$TierEntry;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #98	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/fabric/foreverpack/network/ForeverPackNetwork$TierEntry;
/*    */   }
/*    */   
/*    */   public final int hashCode() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/foreverpack/network/ForeverPackNetwork$TierEntry;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #98	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/fabric/foreverpack/network/ForeverPackNetwork$TierEntry;
/*    */   }
/*    */   
/*    */   public final boolean equals(Object o) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/foreverpack/network/ForeverPackNetwork$TierEntry;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #98	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lcom/atlas/common/fabric/foreverpack/network/ForeverPackNetwork$TierEntry;
/*    */     //   0	8	1	o	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public TierEntry(int ladderIndex, boolean paid, boolean milestone, int cost, int paidIndex, boolean claimed, List<ForeverPackNetwork.RewardEntry> rewards) {
/* 98 */     this.ladderIndex = ladderIndex; this.paid = paid; this.milestone = milestone; this.cost = cost; this.paidIndex = paidIndex; this.claimed = claimed; this.rewards = rewards; } public int ladderIndex() { return this.ladderIndex; } public boolean paid() { return this.paid; } public boolean milestone() { return this.milestone; } public int cost() { return this.cost; } public int paidIndex() { return this.paidIndex; } public boolean claimed() { return this.claimed; } public List<ForeverPackNetwork.RewardEntry> rewards() { return this.rewards; }
/*    */ 
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\foreverpack\network\ForeverPackNetwork$TierEntry.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */