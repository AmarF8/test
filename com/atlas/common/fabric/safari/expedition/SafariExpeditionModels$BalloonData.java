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
/*     */ public final class BalloonData
/*     */   extends Record
/*     */ {
/*     */   private final String balloonId;
/*     */   private final String areaId;
/*     */   private final String areaName;
/*     */   private final String locationHint;
/*     */   private final double x;
/*     */   private final double y;
/*     */   private final double z;
/*     */   private final long spawnedAt;
/*     */   private final long expiresAt;
/*     */   private final SafariExpeditionModels.ExpeditionDefinitionData expedition;
/*     */   
/*     */   public final String toString() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/safari/expedition/SafariExpeditionModels$BalloonData;)Ljava/lang/String;
/*     */     //   6: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #117	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	7	0	this	Lcom/atlas/common/fabric/safari/expedition/SafariExpeditionModels$BalloonData;
/*     */   }
/*     */   
/*     */   public final int hashCode() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/safari/expedition/SafariExpeditionModels$BalloonData;)I
/*     */     //   6: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #117	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	7	0	this	Lcom/atlas/common/fabric/safari/expedition/SafariExpeditionModels$BalloonData;
/*     */   }
/*     */   
/*     */   public final boolean equals(Object o) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/safari/expedition/SafariExpeditionModels$BalloonData;Ljava/lang/Object;)Z
/*     */     //   7: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #117	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	8	0	this	Lcom/atlas/common/fabric/safari/expedition/SafariExpeditionModels$BalloonData;
/*     */     //   0	8	1	o	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public BalloonData(String balloonId, String areaId, String areaName, String locationHint, double x, double y, double z, long spawnedAt, long expiresAt, SafariExpeditionModels.ExpeditionDefinitionData expedition) {
/* 117 */     this.balloonId = balloonId; this.areaId = areaId; this.areaName = areaName; this.locationHint = locationHint; this.x = x; this.y = y; this.z = z; this.spawnedAt = spawnedAt; this.expiresAt = expiresAt; this.expedition = expedition; } public String balloonId() { return this.balloonId; } public String areaId() { return this.areaId; } public String areaName() { return this.areaName; } public String locationHint() { return this.locationHint; } public double x() { return this.x; } public double y() { return this.y; } public double z() { return this.z; } public long spawnedAt() { return this.spawnedAt; } public long expiresAt() { return this.expiresAt; } public SafariExpeditionModels.ExpeditionDefinitionData expedition() { return this.expedition; }
/*     */ 
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\safari\expedition\SafariExpeditionModels$BalloonData.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */