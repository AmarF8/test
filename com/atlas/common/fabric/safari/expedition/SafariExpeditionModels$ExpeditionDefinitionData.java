/*    */ package com.atlas.common.fabric.safari.expedition;
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
/*    */ public final class ExpeditionDefinitionData
/*    */   extends Record
/*    */ {
/*    */   private final String id;
/*    */   private final String displayName;
/*    */   private final String description;
/*    */   private final String areaId;
/*    */   private final String areaName;
/*    */   private final String locationHint;
/*    */   private final String mascotSpeciesId;
/*    */   private final SafariExpeditionModels.ExpeditionTier tier;
/*    */   private final int durationMinutes;
/*    */   private final int requiredPower;
/*    */   private final int minimumTeamSize;
/*    */   private final int maximumTeamSize;
/*    */   private final List<String> allowedTypes;
/*    */   private final List<SafariExpeditionModels.ExpeditionHazard> hazards;
/*    */   private final int rankXpReward;
/*    */   private final int coinReward;
/*    */   private final int gemReward;
/*    */   private final int boosterDropChance;
/*    */   
/*    */   public final String toString() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/safari/expedition/SafariExpeditionModels$ExpeditionDefinitionData;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #96	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/fabric/safari/expedition/SafariExpeditionModels$ExpeditionDefinitionData;
/*    */   }
/*    */   
/*    */   public final int hashCode() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/safari/expedition/SafariExpeditionModels$ExpeditionDefinitionData;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #96	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/fabric/safari/expedition/SafariExpeditionModels$ExpeditionDefinitionData;
/*    */   }
/*    */   
/*    */   public final boolean equals(Object o) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/safari/expedition/SafariExpeditionModels$ExpeditionDefinitionData;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #96	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lcom/atlas/common/fabric/safari/expedition/SafariExpeditionModels$ExpeditionDefinitionData;
/*    */     //   0	8	1	o	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public ExpeditionDefinitionData(String id, String displayName, String description, String areaId, String areaName, String locationHint, String mascotSpeciesId, SafariExpeditionModels.ExpeditionTier tier, int durationMinutes, int requiredPower, int minimumTeamSize, int maximumTeamSize, List<String> allowedTypes, List<SafariExpeditionModels.ExpeditionHazard> hazards, int rankXpReward, int coinReward, int gemReward, int boosterDropChance) {
/* 96 */     this.id = id; this.displayName = displayName; this.description = description; this.areaId = areaId; this.areaName = areaName; this.locationHint = locationHint; this.mascotSpeciesId = mascotSpeciesId; this.tier = tier; this.durationMinutes = durationMinutes; this.requiredPower = requiredPower; this.minimumTeamSize = minimumTeamSize; this.maximumTeamSize = maximumTeamSize; this.allowedTypes = allowedTypes; this.hazards = hazards; this.rankXpReward = rankXpReward; this.coinReward = coinReward; this.gemReward = gemReward; this.boosterDropChance = boosterDropChance; } public String id() { return this.id; } public String displayName() { return this.displayName; } public String description() { return this.description; } public String areaId() { return this.areaId; } public String areaName() { return this.areaName; } public String locationHint() { return this.locationHint; } public String mascotSpeciesId() { return this.mascotSpeciesId; } public SafariExpeditionModels.ExpeditionTier tier() { return this.tier; } public int durationMinutes() { return this.durationMinutes; } public int requiredPower() { return this.requiredPower; } public int minimumTeamSize() { return this.minimumTeamSize; } public int maximumTeamSize() { return this.maximumTeamSize; } public List<String> allowedTypes() { return this.allowedTypes; } public List<SafariExpeditionModels.ExpeditionHazard> hazards() { return this.hazards; } public int rankXpReward() { return this.rankXpReward; } public int coinReward() { return this.coinReward; } public int gemReward() { return this.gemReward; } public int boosterDropChance() { return this.boosterDropChance; }
/*    */ 
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\safari\expedition\SafariExpeditionModels$ExpeditionDefinitionData.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */