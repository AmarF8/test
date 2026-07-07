/*     */ package com.atlas.common.fabric.safari.board;
/*     */ 
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class ContractCardData
/*     */   extends Record
/*     */ {
/*     */   private final int slot;
/*     */   private final SafariBoardModels.Difficulty difficulty;
/*     */   private final boolean completed;
/*     */   private final boolean rewarded;
/*     */   private final List<SafariBoardModels.MissionEntryData> missions;
/*     */   private final List<SafariBoardModels.RewardEntryData> rewards;
/*     */   
/*     */   public final String toString() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/safari/board/SafariBoardModels$ContractCardData;)Ljava/lang/String;
/*     */     //   6: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #103	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	7	0	this	Lcom/atlas/common/fabric/safari/board/SafariBoardModels$ContractCardData;
/*     */   }
/*     */   
/*     */   public final int hashCode() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/safari/board/SafariBoardModels$ContractCardData;)I
/*     */     //   6: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #103	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	7	0	this	Lcom/atlas/common/fabric/safari/board/SafariBoardModels$ContractCardData;
/*     */   }
/*     */   
/*     */   public final boolean equals(Object o) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/safari/board/SafariBoardModels$ContractCardData;Ljava/lang/Object;)Z
/*     */     //   7: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #103	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	8	0	this	Lcom/atlas/common/fabric/safari/board/SafariBoardModels$ContractCardData;
/*     */     //   0	8	1	o	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public ContractCardData(int slot, SafariBoardModels.Difficulty difficulty, boolean completed, boolean rewarded, List<SafariBoardModels.MissionEntryData> missions, List<SafariBoardModels.RewardEntryData> rewards) {
/* 103 */     this.slot = slot; this.difficulty = difficulty; this.completed = completed; this.rewarded = rewarded; this.missions = missions; this.rewards = rewards; } public int slot() { return this.slot; } public SafariBoardModels.Difficulty difficulty() { return this.difficulty; } public boolean completed() { return this.completed; } public boolean rewarded() { return this.rewarded; } public List<SafariBoardModels.MissionEntryData> missions() { return this.missions; } public List<SafariBoardModels.RewardEntryData> rewards() { return this.rewards; }
/*     */ 
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\safari\board\SafariBoardModels$ContractCardData.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */