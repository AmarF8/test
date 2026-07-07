/*    */ package com.atlas.common.fabric.morph;
/*    */ 
/*    */ import java.util.EnumMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class MorphDefinition
/*    */   extends Record
/*    */ {
/*    */   @NotNull
/*    */   private final String speciesId;
/*    */   @NotNull
/*    */   private final String displayName;
/*    */   @NotNull
/*    */   private final String roleLabel;
/*    */   @NotNull
/*    */   private final String flavorText;
/*    */   private final boolean starterUnlocked;
/*    */   private final int gemCost;
/*    */   private final int baseHp;
/*    */   private final int baseAttack;
/*    */   private final int baseDefense;
/*    */   private final int baseAttackSpeed;
/*    */   private final int baseSpeed;
/*    */   @NotNull
/*    */   private final List<MorphModels.MorphMove> moves;
/*    */   
/*    */   public final String toString() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/morph/MorphModels$MorphDefinition;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #60	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/fabric/morph/MorphModels$MorphDefinition;
/*    */   }
/*    */   
/*    */   public final int hashCode() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/morph/MorphModels$MorphDefinition;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #60	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/fabric/morph/MorphModels$MorphDefinition;
/*    */   }
/*    */   
/*    */   public MorphDefinition(@NotNull String speciesId, @NotNull String displayName, @NotNull String roleLabel, @NotNull String flavorText, boolean starterUnlocked, int gemCost, int baseHp, int baseAttack, int baseDefense, int baseAttackSpeed, int baseSpeed, @NotNull List<MorphModels.MorphMove> moves) {
/* 60 */     this.speciesId = speciesId; this.displayName = displayName; this.roleLabel = roleLabel; this.flavorText = flavorText; this.starterUnlocked = starterUnlocked; this.gemCost = gemCost; this.baseHp = baseHp; this.baseAttack = baseAttack; this.baseDefense = baseDefense; this.baseAttackSpeed = baseAttackSpeed; this.baseSpeed = baseSpeed; this.moves = moves; } public final boolean equals(Object o) { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/morph/MorphModels$MorphDefinition;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #60	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lcom/atlas/common/fabric/morph/MorphModels$MorphDefinition;
/* 60 */     //   0	8	1	o	Ljava/lang/Object; } @NotNull public String speciesId() { return this.speciesId; } @NotNull public String displayName() { return this.displayName; } @NotNull public String roleLabel() { return this.roleLabel; } @NotNull public String flavorText() { return this.flavorText; } public boolean starterUnlocked() { return this.starterUnlocked; } public int gemCost() { return this.gemCost; } public int baseHp() { return this.baseHp; } public int baseAttack() { return this.baseAttack; } public int baseDefense() { return this.baseDefense; } public int baseAttackSpeed() { return this.baseAttackSpeed; } public int baseSpeed() { return this.baseSpeed; } @NotNull public List<MorphModels.MorphMove> moves() { return this.moves; }
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
/*    */   public int baseStat(@NotNull MorphModels.MorphStat stat) {
/* 75 */     switch (stat.ordinal()) { default: throw new MatchException(null, null);case 0: case 1: case 2: case 3: case 4: break; }  return 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 80 */       this.baseSpeed;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public Map<MorphModels.MorphStat, Integer> statMap() {
/* 85 */     Map<MorphModels.MorphStat, Integer> stats = new EnumMap<>(MorphModels.MorphStat.class);
/* 86 */     for (MorphModels.MorphStat stat : MorphModels.MorphStat.values()) {
/* 87 */       stats.put(stat, Integer.valueOf(baseStat(stat)));
/*    */     }
/* 89 */     return stats;
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\morph\MorphModels$MorphDefinition.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */