/*     */ package com.atlas.common.fabric.safari.expedition;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class ReturnedExpeditionData
/*     */   extends Record
/*     */ {
/*     */   private final String runId;
/*     */   private final SafariExpeditionModels.ExpeditionDefinitionData expedition;
/*     */   private final SafariExpeditionModels.ExpeditionBoosterType boosterType;
/*     */   private final long startedAt;
/*     */   private final long completedAt;
/*     */   private final boolean success;
/*     */   private final int teamPower;
/*     */   private final int successChance;
/*     */   private final int rankXpReward;
/*     */   private final int rewardCoins;
/*     */   private final int rewardGems;
/*     */   private final int rewardPowerBoosters;
/*     */   private final int rewardSuccessBoosters;
/*     */   private final int rewardBountyBoosters;
/*     */   private final List<String> lockedSpecies;
/*     */   private final List<String> synergyNotes;
/*     */   
/*     */   public final String toString() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/safari/expedition/SafariExpeditionModels$ReturnedExpeditionData;)Ljava/lang/String;
/*     */     //   6: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #164	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	7	0	this	Lcom/atlas/common/fabric/safari/expedition/SafariExpeditionModels$ReturnedExpeditionData;
/*     */   }
/*     */   
/*     */   public final int hashCode() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/safari/expedition/SafariExpeditionModels$ReturnedExpeditionData;)I
/*     */     //   6: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #164	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	7	0	this	Lcom/atlas/common/fabric/safari/expedition/SafariExpeditionModels$ReturnedExpeditionData;
/*     */   }
/*     */   
/*     */   public final boolean equals(Object o) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/safari/expedition/SafariExpeditionModels$ReturnedExpeditionData;Ljava/lang/Object;)Z
/*     */     //   7: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #164	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	8	0	this	Lcom/atlas/common/fabric/safari/expedition/SafariExpeditionModels$ReturnedExpeditionData;
/*     */     //   0	8	1	o	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public ReturnedExpeditionData(String runId, SafariExpeditionModels.ExpeditionDefinitionData expedition, SafariExpeditionModels.ExpeditionBoosterType boosterType, long startedAt, long completedAt, boolean success, int teamPower, int successChance, int rankXpReward, int rewardCoins, int rewardGems, int rewardPowerBoosters, int rewardSuccessBoosters, int rewardBountyBoosters, List<String> lockedSpecies, List<String> synergyNotes) {
/* 164 */     this.runId = runId; this.expedition = expedition; this.boosterType = boosterType; this.startedAt = startedAt; this.completedAt = completedAt; this.success = success; this.teamPower = teamPower; this.successChance = successChance; this.rankXpReward = rankXpReward; this.rewardCoins = rewardCoins; this.rewardGems = rewardGems; this.rewardPowerBoosters = rewardPowerBoosters; this.rewardSuccessBoosters = rewardSuccessBoosters; this.rewardBountyBoosters = rewardBountyBoosters; this.lockedSpecies = lockedSpecies; this.synergyNotes = synergyNotes; } public String runId() { return this.runId; } public SafariExpeditionModels.ExpeditionDefinitionData expedition() { return this.expedition; } public SafariExpeditionModels.ExpeditionBoosterType boosterType() { return this.boosterType; } public long startedAt() { return this.startedAt; } public long completedAt() { return this.completedAt; } public boolean success() { return this.success; } public int teamPower() { return this.teamPower; } public int successChance() { return this.successChance; } public int rankXpReward() { return this.rankXpReward; } public int rewardCoins() { return this.rewardCoins; } public int rewardGems() { return this.rewardGems; } public int rewardPowerBoosters() { return this.rewardPowerBoosters; } public int rewardSuccessBoosters() { return this.rewardSuccessBoosters; } public int rewardBountyBoosters() { return this.rewardBountyBoosters; } public List<String> lockedSpecies() { return this.lockedSpecies; } public List<String> synergyNotes() { return this.synergyNotes; }
/*     */ 
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\safari\expedition\SafariExpeditionModels$ReturnedExpeditionData.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */