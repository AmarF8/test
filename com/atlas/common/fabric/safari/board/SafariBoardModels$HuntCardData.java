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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class HuntCardData
/*     */   extends Record
/*     */ {
/*     */   private final int slot;
/*     */   private final String speciesId;
/*     */   private final String speciesName;
/*     */   private final SafariBoardModels.Difficulty difficulty;
/*     */   private final int required;
/*     */   private final int progress;
/*     */   private final boolean completed;
/*     */   private final boolean rewarded;
/*     */   private final List<SafariBoardModels.RewardEntryData> rewards;
/*     */   
/*     */   public final String toString() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/safari/board/SafariBoardModels$HuntCardData;)Ljava/lang/String;
/*     */     //   6: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #112	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	7	0	this	Lcom/atlas/common/fabric/safari/board/SafariBoardModels$HuntCardData;
/*     */   }
/*     */   
/*     */   public final int hashCode() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/safari/board/SafariBoardModels$HuntCardData;)I
/*     */     //   6: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #112	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	7	0	this	Lcom/atlas/common/fabric/safari/board/SafariBoardModels$HuntCardData;
/*     */   }
/*     */   
/*     */   public final boolean equals(Object o) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/safari/board/SafariBoardModels$HuntCardData;Ljava/lang/Object;)Z
/*     */     //   7: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #112	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	8	0	this	Lcom/atlas/common/fabric/safari/board/SafariBoardModels$HuntCardData;
/*     */     //   0	8	1	o	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public HuntCardData(int slot, String speciesId, String speciesName, SafariBoardModels.Difficulty difficulty, int required, int progress, boolean completed, boolean rewarded, List<SafariBoardModels.RewardEntryData> rewards) {
/* 112 */     this.slot = slot; this.speciesId = speciesId; this.speciesName = speciesName; this.difficulty = difficulty; this.required = required; this.progress = progress; this.completed = completed; this.rewarded = rewarded; this.rewards = rewards; } public int slot() { return this.slot; } public String speciesId() { return this.speciesId; } public String speciesName() { return this.speciesName; } public SafariBoardModels.Difficulty difficulty() { return this.difficulty; } public int required() { return this.required; } public int progress() { return this.progress; } public boolean completed() { return this.completed; } public boolean rewarded() { return this.rewarded; } public List<SafariBoardModels.RewardEntryData> rewards() { return this.rewards; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int clampedProgress() {
/* 124 */     return Math.min(this.progress, this.required);
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\safari\board\SafariBoardModels$HuntCardData.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */