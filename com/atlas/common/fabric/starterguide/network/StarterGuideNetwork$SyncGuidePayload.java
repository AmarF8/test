/*     */ package com.atlas.common.fabric.starterguide.network;
/*     */ 
/*     */ import net.minecraft.class_8710;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class SyncGuidePayload
/*     */   extends Record
/*     */   implements class_8710
/*     */ {
/*     */   private final int stepIndex;
/*     */   private final double progress;
/*     */   private final int target;
/*     */   private final String description;
/*     */   private final String hint;
/*     */   private final String rewardDescription;
/*     */   private final int totalSteps;
/*     */   private final boolean completed;
/*     */   private final String stepType;
/*     */   private final String stepDetail;
/*     */   
/*     */   public final String toString() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/starterguide/network/StarterGuideNetwork$SyncGuidePayload;)Ljava/lang/String;
/*     */     //   6: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #116	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	7	0	this	Lcom/atlas/common/fabric/starterguide/network/StarterGuideNetwork$SyncGuidePayload;
/*     */   }
/*     */   
/*     */   public final int hashCode() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/starterguide/network/StarterGuideNetwork$SyncGuidePayload;)I
/*     */     //   6: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #116	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	7	0	this	Lcom/atlas/common/fabric/starterguide/network/StarterGuideNetwork$SyncGuidePayload;
/*     */   }
/*     */   
/*     */   public final boolean equals(Object o) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/starterguide/network/StarterGuideNetwork$SyncGuidePayload;Ljava/lang/Object;)Z
/*     */     //   7: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #116	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	8	0	this	Lcom/atlas/common/fabric/starterguide/network/StarterGuideNetwork$SyncGuidePayload;
/*     */     //   0	8	1	o	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public SyncGuidePayload(int stepIndex, double progress, int target, String description, String hint, String rewardDescription, int totalSteps, boolean completed, String stepType, String stepDetail) {
/* 116 */     this.stepIndex = stepIndex; this.progress = progress; this.target = target; this.description = description; this.hint = hint; this.rewardDescription = rewardDescription; this.totalSteps = totalSteps; this.completed = completed; this.stepType = stepType; this.stepDetail = stepDetail; } public int stepIndex() { return this.stepIndex; } public double progress() { return this.progress; } public int target() { return this.target; } public String description() { return this.description; } public String hint() { return this.hint; } public String rewardDescription() { return this.rewardDescription; } public int totalSteps() { return this.totalSteps; } public boolean completed() { return this.completed; } public String stepType() { return this.stepType; } public String stepDetail() { return this.stepDetail; }
/*     */ 
/*     */   
/*     */   public class_8710.class_9154<? extends class_8710> method_56479() {
/* 120 */     return (class_8710.class_9154)StarterGuideNetwork.SYNC_GUIDE_TYPE;
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\starterguide\network\StarterGuideNetwork$SyncGuidePayload.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */