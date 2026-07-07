/*     */ package com.atlas.common.fabric.safari.expedition;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class ExpeditionProfileData
/*     */   extends Record
/*     */ {
/*     */   private final int rank;
/*     */   private final int rankXp;
/*     */   private final int nextRankXp;
/*     */   private final int availableSlots;
/*     */   private final int activeSlotsUsed;
/*     */   private final int returnedSlotsUsed;
/*     */   private final int powerBoosters;
/*     */   private final int successBoosters;
/*     */   private final int bountyBoosters;
/*     */   
/*     */   public final String toString() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/safari/expedition/SafariExpeditionModels$ExpeditionProfileData;)Ljava/lang/String;
/*     */     //   6: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #139	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	7	0	this	Lcom/atlas/common/fabric/safari/expedition/SafariExpeditionModels$ExpeditionProfileData;
/*     */   }
/*     */   
/*     */   public final int hashCode() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/safari/expedition/SafariExpeditionModels$ExpeditionProfileData;)I
/*     */     //   6: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #139	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	7	0	this	Lcom/atlas/common/fabric/safari/expedition/SafariExpeditionModels$ExpeditionProfileData;
/*     */   }
/*     */   
/*     */   public final boolean equals(Object o) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/safari/expedition/SafariExpeditionModels$ExpeditionProfileData;Ljava/lang/Object;)Z
/*     */     //   7: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #139	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	8	0	this	Lcom/atlas/common/fabric/safari/expedition/SafariExpeditionModels$ExpeditionProfileData;
/*     */     //   0	8	1	o	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public ExpeditionProfileData(int rank, int rankXp, int nextRankXp, int availableSlots, int activeSlotsUsed, int returnedSlotsUsed, int powerBoosters, int successBoosters, int bountyBoosters) {
/* 139 */     this.rank = rank; this.rankXp = rankXp; this.nextRankXp = nextRankXp; this.availableSlots = availableSlots; this.activeSlotsUsed = activeSlotsUsed; this.returnedSlotsUsed = returnedSlotsUsed; this.powerBoosters = powerBoosters; this.successBoosters = successBoosters; this.bountyBoosters = bountyBoosters; } public int rank() { return this.rank; } public int rankXp() { return this.rankXp; } public int nextRankXp() { return this.nextRankXp; } public int availableSlots() { return this.availableSlots; } public int activeSlotsUsed() { return this.activeSlotsUsed; } public int returnedSlotsUsed() { return this.returnedSlotsUsed; } public int powerBoosters() { return this.powerBoosters; } public int successBoosters() { return this.successBoosters; } public int bountyBoosters() { return this.bountyBoosters; }
/*     */ 
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\safari\expedition\SafariExpeditionModels$ExpeditionProfileData.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */