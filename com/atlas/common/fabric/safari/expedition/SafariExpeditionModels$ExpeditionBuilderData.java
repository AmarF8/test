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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class ExpeditionBuilderData
/*     */   extends Record
/*     */ {
/*     */   private final SafariExpeditionModels.BalloonData balloon;
/*     */   private final SafariExpeditionModels.ExpeditionBoosterType boosterType;
/*     */   private final int availablePowerBoosters;
/*     */   private final int availableSuccessBoosters;
/*     */   private final int availableBountyBoosters;
/*     */   private final int selectedCount;
/*     */   private final int minimumTeamSize;
/*     */   private final int maximumTeamSize;
/*     */   private final int teamPower;
/*     */   private final int successChance;
/*     */   private final int eligiblePokemonCount;
/*     */   private final boolean canLaunch;
/*     */   private final String statusText;
/*     */   private final List<String> synergyNotes;
/*     */   private final List<SafariExpeditionModels.BuilderSelectedPokemonData> selectedTeam;
/*     */   
/*     */   public final String toString() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/safari/expedition/SafariExpeditionModels$ExpeditionBuilderData;)Ljava/lang/String;
/*     */     //   6: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #194	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	7	0	this	Lcom/atlas/common/fabric/safari/expedition/SafariExpeditionModels$ExpeditionBuilderData;
/*     */   }
/*     */   
/*     */   public final int hashCode() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/safari/expedition/SafariExpeditionModels$ExpeditionBuilderData;)I
/*     */     //   6: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #194	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	7	0	this	Lcom/atlas/common/fabric/safari/expedition/SafariExpeditionModels$ExpeditionBuilderData;
/*     */   }
/*     */   
/*     */   public final boolean equals(Object o) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/safari/expedition/SafariExpeditionModels$ExpeditionBuilderData;Ljava/lang/Object;)Z
/*     */     //   7: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #194	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	8	0	this	Lcom/atlas/common/fabric/safari/expedition/SafariExpeditionModels$ExpeditionBuilderData;
/*     */     //   0	8	1	o	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public ExpeditionBuilderData(SafariExpeditionModels.BalloonData balloon, SafariExpeditionModels.ExpeditionBoosterType boosterType, int availablePowerBoosters, int availableSuccessBoosters, int availableBountyBoosters, int selectedCount, int minimumTeamSize, int maximumTeamSize, int teamPower, int successChance, int eligiblePokemonCount, boolean canLaunch, String statusText, List<String> synergyNotes, List<SafariExpeditionModels.BuilderSelectedPokemonData> selectedTeam) {
/* 194 */     this.balloon = balloon; this.boosterType = boosterType; this.availablePowerBoosters = availablePowerBoosters; this.availableSuccessBoosters = availableSuccessBoosters; this.availableBountyBoosters = availableBountyBoosters; this.selectedCount = selectedCount; this.minimumTeamSize = minimumTeamSize; this.maximumTeamSize = maximumTeamSize; this.teamPower = teamPower; this.successChance = successChance; this.eligiblePokemonCount = eligiblePokemonCount; this.canLaunch = canLaunch; this.statusText = statusText; this.synergyNotes = synergyNotes; this.selectedTeam = selectedTeam; } public SafariExpeditionModels.BalloonData balloon() { return this.balloon; } public SafariExpeditionModels.ExpeditionBoosterType boosterType() { return this.boosterType; } public int availablePowerBoosters() { return this.availablePowerBoosters; } public int availableSuccessBoosters() { return this.availableSuccessBoosters; } public int availableBountyBoosters() { return this.availableBountyBoosters; } public int selectedCount() { return this.selectedCount; } public int minimumTeamSize() { return this.minimumTeamSize; } public int maximumTeamSize() { return this.maximumTeamSize; } public int teamPower() { return this.teamPower; } public int successChance() { return this.successChance; } public int eligiblePokemonCount() { return this.eligiblePokemonCount; } public boolean canLaunch() { return this.canLaunch; } public String statusText() { return this.statusText; } public List<String> synergyNotes() { return this.synergyNotes; } public List<SafariExpeditionModels.BuilderSelectedPokemonData> selectedTeam() { return this.selectedTeam; }
/*     */ 
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\safari\expedition\SafariExpeditionModels$ExpeditionBuilderData.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */