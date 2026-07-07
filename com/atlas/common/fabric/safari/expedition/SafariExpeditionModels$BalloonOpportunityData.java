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
/*     */ public final class BalloonOpportunityData
/*     */   extends Record
/*     */ {
/*     */   private final SafariExpeditionModels.BalloonData balloon;
/*     */   private final int estimatedTeamPower;
/*     */   private final int estimatedSuccessChance;
/*     */   private final int eligiblePokemonCount;
/*     */   private final boolean canStart;
/*     */   private final String statusText;
/*     */   
/*     */   public final String toString() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/safari/expedition/SafariExpeditionModels$BalloonOpportunityData;)Ljava/lang/String;
/*     */     //   6: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #130	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	7	0	this	Lcom/atlas/common/fabric/safari/expedition/SafariExpeditionModels$BalloonOpportunityData;
/*     */   }
/*     */   
/*     */   public final int hashCode() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/safari/expedition/SafariExpeditionModels$BalloonOpportunityData;)I
/*     */     //   6: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #130	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	7	0	this	Lcom/atlas/common/fabric/safari/expedition/SafariExpeditionModels$BalloonOpportunityData;
/*     */   }
/*     */   
/*     */   public final boolean equals(Object o) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/safari/expedition/SafariExpeditionModels$BalloonOpportunityData;Ljava/lang/Object;)Z
/*     */     //   7: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #130	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	8	0	this	Lcom/atlas/common/fabric/safari/expedition/SafariExpeditionModels$BalloonOpportunityData;
/*     */     //   0	8	1	o	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public BalloonOpportunityData(SafariExpeditionModels.BalloonData balloon, int estimatedTeamPower, int estimatedSuccessChance, int eligiblePokemonCount, boolean canStart, String statusText) {
/* 130 */     this.balloon = balloon; this.estimatedTeamPower = estimatedTeamPower; this.estimatedSuccessChance = estimatedSuccessChance; this.eligiblePokemonCount = eligiblePokemonCount; this.canStart = canStart; this.statusText = statusText; } public SafariExpeditionModels.BalloonData balloon() { return this.balloon; } public int estimatedTeamPower() { return this.estimatedTeamPower; } public int estimatedSuccessChance() { return this.estimatedSuccessChance; } public int eligiblePokemonCount() { return this.eligiblePokemonCount; } public boolean canStart() { return this.canStart; } public String statusText() { return this.statusText; }
/*     */ 
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\safari\expedition\SafariExpeditionModels$BalloonOpportunityData.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */