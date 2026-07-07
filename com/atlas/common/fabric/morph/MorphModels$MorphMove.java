/*    */ package com.atlas.common.fabric.morph;
/*    */ 
/*    */ import org.jetbrains.annotations.NotNull;
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
/*    */ public final class MorphMove
/*    */   extends Record
/*    */ {
/*    */   @NotNull
/*    */   private final String moveId;
/*    */   @NotNull
/*    */   private final String displayName;
/*    */   private final int unlockLevel;
/*    */   @NotNull
/*    */   private final String summary;
/*    */   private final int damage;
/*    */   private final double cooldownSeconds;
/*    */   
/*    */   public final String toString() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/morph/MorphModels$MorphMove;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #47	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/fabric/morph/MorphModels$MorphMove;
/*    */   }
/*    */   
/*    */   public final int hashCode() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/morph/MorphModels$MorphMove;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #47	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/fabric/morph/MorphModels$MorphMove;
/*    */   }
/*    */   
/*    */   public final boolean equals(Object o) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/morph/MorphModels$MorphMove;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #47	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lcom/atlas/common/fabric/morph/MorphModels$MorphMove;
/*    */     //   0	8	1	o	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public MorphMove(@NotNull String moveId, @NotNull String displayName, int unlockLevel, @NotNull String summary, int damage, double cooldownSeconds) {
/* 47 */     this.moveId = moveId; this.displayName = displayName; this.unlockLevel = unlockLevel; this.summary = summary; this.damage = damage; this.cooldownSeconds = cooldownSeconds; } @NotNull public String moveId() { return this.moveId; } @NotNull public String displayName() { return this.displayName; } public int unlockLevel() { return this.unlockLevel; } @NotNull public String summary() { return this.summary; } public int damage() { return this.damage; } public double cooldownSeconds() { return this.cooldownSeconds; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isStatusMove() {
/* 56 */     return (this.damage <= 0);
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\morph\MorphModels$MorphMove.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */