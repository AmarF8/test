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
/*     */ public final class ActiveExpeditionData
/*     */   extends Record
/*     */ {
/*     */   private final String runId;
/*     */   private final SafariExpeditionModels.ExpeditionDefinitionData expedition;
/*     */   private final SafariExpeditionModels.ExpeditionBoosterType boosterType;
/*     */   private final long startedAt;
/*     */   private final long completesAt;
/*     */   private final int teamPower;
/*     */   private final int successChance;
/*     */   private final int teamSize;
/*     */   private final List<String> lockedSpecies;
/*     */   private final List<String> synergyNotes;
/*     */   
/*     */   public final String toString() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/safari/expedition/SafariExpeditionModels$ActiveExpeditionData;)Ljava/lang/String;
/*     */     //   6: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #151	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	7	0	this	Lcom/atlas/common/fabric/safari/expedition/SafariExpeditionModels$ActiveExpeditionData;
/*     */   }
/*     */   
/*     */   public final int hashCode() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/safari/expedition/SafariExpeditionModels$ActiveExpeditionData;)I
/*     */     //   6: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #151	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	7	0	this	Lcom/atlas/common/fabric/safari/expedition/SafariExpeditionModels$ActiveExpeditionData;
/*     */   }
/*     */   
/*     */   public final boolean equals(Object o) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/safari/expedition/SafariExpeditionModels$ActiveExpeditionData;Ljava/lang/Object;)Z
/*     */     //   7: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #151	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	8	0	this	Lcom/atlas/common/fabric/safari/expedition/SafariExpeditionModels$ActiveExpeditionData;
/*     */     //   0	8	1	o	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public ActiveExpeditionData(String runId, SafariExpeditionModels.ExpeditionDefinitionData expedition, SafariExpeditionModels.ExpeditionBoosterType boosterType, long startedAt, long completesAt, int teamPower, int successChance, int teamSize, List<String> lockedSpecies, List<String> synergyNotes) {
/* 151 */     this.runId = runId; this.expedition = expedition; this.boosterType = boosterType; this.startedAt = startedAt; this.completesAt = completesAt; this.teamPower = teamPower; this.successChance = successChance; this.teamSize = teamSize; this.lockedSpecies = lockedSpecies; this.synergyNotes = synergyNotes; } public String runId() { return this.runId; } public SafariExpeditionModels.ExpeditionDefinitionData expedition() { return this.expedition; } public SafariExpeditionModels.ExpeditionBoosterType boosterType() { return this.boosterType; } public long startedAt() { return this.startedAt; } public long completesAt() { return this.completesAt; } public int teamPower() { return this.teamPower; } public int successChance() { return this.successChance; } public int teamSize() { return this.teamSize; } public List<String> lockedSpecies() { return this.lockedSpecies; } public List<String> synergyNotes() { return this.synergyNotes; }
/*     */ 
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\safari\expedition\SafariExpeditionModels$ActiveExpeditionData.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */