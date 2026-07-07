/*     */ package com.atlas.common.fabric.morph;
/*     */ 
/*     */ import java.util.List;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class MorphProgressSnapshot
/*     */   extends Record
/*     */ {
/*     */   @NotNull
/*     */   private final String speciesId;
/*     */   private final boolean unlocked;
/*     */   private final int level;
/*     */   private final int experience;
/*     */   private final int attackDamagePoints;
/*     */   private final int defensePoints;
/*     */   private final int healthPoints;
/*     */   private final int attackSpeedPoints;
/*     */   private final int speedPoints;
/*     */   @NotNull
/*     */   private final List<String> equippedMoves;
/*     */   
/*     */   public final String toString() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/morph/MorphModels$MorphProgressSnapshot;)Ljava/lang/String;
/*     */     //   6: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #93	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	7	0	this	Lcom/atlas/common/fabric/morph/MorphModels$MorphProgressSnapshot;
/*     */   }
/*     */   
/*     */   public final int hashCode() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/morph/MorphModels$MorphProgressSnapshot;)I
/*     */     //   6: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #93	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	7	0	this	Lcom/atlas/common/fabric/morph/MorphModels$MorphProgressSnapshot;
/*     */   }
/*     */   
/*     */   public MorphProgressSnapshot(@NotNull String speciesId, boolean unlocked, int level, int experience, int attackDamagePoints, int defensePoints, int healthPoints, int attackSpeedPoints, int speedPoints, @NotNull List<String> equippedMoves) {
/*  93 */     this.speciesId = speciesId; this.unlocked = unlocked; this.level = level; this.experience = experience; this.attackDamagePoints = attackDamagePoints; this.defensePoints = defensePoints; this.healthPoints = healthPoints; this.attackSpeedPoints = attackSpeedPoints; this.speedPoints = speedPoints; this.equippedMoves = equippedMoves; } public final boolean equals(Object o) { // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/morph/MorphModels$MorphProgressSnapshot;Ljava/lang/Object;)Z
/*     */     //   7: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #93	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	8	0	this	Lcom/atlas/common/fabric/morph/MorphModels$MorphProgressSnapshot;
/*  93 */     //   0	8	1	o	Ljava/lang/Object; } @NotNull public String speciesId() { return this.speciesId; } public boolean unlocked() { return this.unlocked; } public int level() { return this.level; } public int experience() { return this.experience; } public int attackDamagePoints() { return this.attackDamagePoints; } public int defensePoints() { return this.defensePoints; } public int healthPoints() { return this.healthPoints; } public int attackSpeedPoints() { return this.attackSpeedPoints; } public int speedPoints() { return this.speedPoints; } @NotNull public List<String> equippedMoves() { return this.equippedMoves; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int investedPoints() {
/* 106 */     return this.attackDamagePoints + this.defensePoints + this.healthPoints + this.attackSpeedPoints + this.speedPoints;
/*     */   }
/*     */   
/*     */   public int pointsFor(@NotNull MorphModels.MorphStat stat) {
/* 110 */     switch (stat.ordinal()) { default: throw new MatchException(null, null);case 0: case 1: case 2: case 3: case 4: break; }  return 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 115 */       this.speedPoints;
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\morph\MorphModels$MorphProgressSnapshot.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */