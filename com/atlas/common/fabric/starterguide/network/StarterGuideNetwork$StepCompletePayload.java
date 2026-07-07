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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class StepCompletePayload
/*     */   extends Record
/*     */   implements class_8710
/*     */ {
/*     */   private final int nextStepIndex;
/*     */   private final int nextTarget;
/*     */   private final String nextDescription;
/*     */   private final String nextHint;
/*     */   private final String nextRewardDescription;
/*     */   private final int totalSteps;
/*     */   private final String nextStepType;
/*     */   private final String nextStepDetail;
/*     */   
/*     */   public final String toString() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/starterguide/network/StarterGuideNetwork$StepCompletePayload;)Ljava/lang/String;
/*     */     //   6: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #124	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	7	0	this	Lcom/atlas/common/fabric/starterguide/network/StarterGuideNetwork$StepCompletePayload;
/*     */   }
/*     */   
/*     */   public final int hashCode() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/starterguide/network/StarterGuideNetwork$StepCompletePayload;)I
/*     */     //   6: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #124	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	7	0	this	Lcom/atlas/common/fabric/starterguide/network/StarterGuideNetwork$StepCompletePayload;
/*     */   }
/*     */   
/*     */   public final boolean equals(Object o) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/starterguide/network/StarterGuideNetwork$StepCompletePayload;Ljava/lang/Object;)Z
/*     */     //   7: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #124	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	8	0	this	Lcom/atlas/common/fabric/starterguide/network/StarterGuideNetwork$StepCompletePayload;
/*     */     //   0	8	1	o	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public StepCompletePayload(int nextStepIndex, int nextTarget, String nextDescription, String nextHint, String nextRewardDescription, int totalSteps, String nextStepType, String nextStepDetail) {
/* 124 */     this.nextStepIndex = nextStepIndex; this.nextTarget = nextTarget; this.nextDescription = nextDescription; this.nextHint = nextHint; this.nextRewardDescription = nextRewardDescription; this.totalSteps = totalSteps; this.nextStepType = nextStepType; this.nextStepDetail = nextStepDetail; } public int nextStepIndex() { return this.nextStepIndex; } public int nextTarget() { return this.nextTarget; } public String nextDescription() { return this.nextDescription; } public String nextHint() { return this.nextHint; } public String nextRewardDescription() { return this.nextRewardDescription; } public int totalSteps() { return this.totalSteps; } public String nextStepType() { return this.nextStepType; } public String nextStepDetail() { return this.nextStepDetail; }
/*     */ 
/*     */ 
/*     */   
/*     */   public class_8710.class_9154<? extends class_8710> method_56479() {
/* 129 */     return (class_8710.class_9154)StarterGuideNetwork.STEP_COMPLETE_TYPE;
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\starterguide\network\StarterGuideNetwork$StepCompletePayload.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */