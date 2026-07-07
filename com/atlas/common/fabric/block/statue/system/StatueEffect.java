/*   */ package com.atlas.common.fabric.block.statue.system;public final class StatueEffect extends Record { @NotNull
/*   */   private final StatueEffectType type; @Nullable
/*   */   private final String key;
/*   */   private final double value;
/*   */   
/* 6 */   public StatueEffect(@NotNull StatueEffectType type, @Nullable String key, double value) { this.type = type; this.key = key; this.value = value; } public final String toString() { // Byte code:
/*   */     //   0: aload_0
/*   */     //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/block/statue/system/StatueEffect;)Ljava/lang/String;
/*   */     //   6: areturn
/*   */     // Line number table:
/*   */     //   Java source line number -> byte code offset
/*   */     //   #6	-> 0
/*   */     // Local variable table:
/*   */     //   start	length	slot	name	descriptor
/* 6 */     //   0	7	0	this	Lcom/atlas/common/fabric/block/statue/system/StatueEffect; } @NotNull public StatueEffectType type() { return this.type; } public final int hashCode() { // Byte code:
/*   */     //   0: aload_0
/*   */     //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/block/statue/system/StatueEffect;)I
/*   */     //   6: ireturn
/*   */     // Line number table:
/*   */     //   Java source line number -> byte code offset
/*   */     //   #6	-> 0
/*   */     // Local variable table:
/*   */     //   start	length	slot	name	descriptor
/*   */     //   0	7	0	this	Lcom/atlas/common/fabric/block/statue/system/StatueEffect; } public final boolean equals(Object o) { // Byte code:
/*   */     //   0: aload_0
/*   */     //   1: aload_1
/*   */     //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/block/statue/system/StatueEffect;Ljava/lang/Object;)Z
/*   */     //   7: ireturn
/*   */     // Line number table:
/*   */     //   Java source line number -> byte code offset
/*   */     //   #6	-> 0
/*   */     // Local variable table:
/*   */     //   start	length	slot	name	descriptor
/*   */     //   0	8	0	this	Lcom/atlas/common/fabric/block/statue/system/StatueEffect;
/* 6 */     //   0	8	1	o	Ljava/lang/Object; } @Nullable public String key() { return this.key; } public double value() { return this.value; }
/*   */   
/*   */   public static StatueEffect typeSpawnBoost(@NotNull String typeName, double multiplier) {
/* 9 */     return new StatueEffect(StatueEffectType.TYPE_SPAWN_BOOST, typeName.toLowerCase(), multiplier);
/*   */   } }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\block\statue\system\StatueEffect.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */