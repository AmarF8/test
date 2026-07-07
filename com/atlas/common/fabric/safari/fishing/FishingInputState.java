/*   */ package com.atlas.common.fabric.safari.fishing;public final class FishingInputState extends Record { private final boolean reeling; private final boolean bracing; private final boolean pump;
/*   */   
/* 3 */   public FishingInputState(boolean reeling, boolean bracing, boolean pump) { this.reeling = reeling; this.bracing = bracing; this.pump = pump; } public final String toString() { // Byte code:
/*   */     //   0: aload_0
/*   */     //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/safari/fishing/FishingInputState;)Ljava/lang/String;
/*   */     //   6: areturn
/*   */     // Line number table:
/*   */     //   Java source line number -> byte code offset
/*   */     //   #3	-> 0
/*   */     // Local variable table:
/*   */     //   start	length	slot	name	descriptor
/* 3 */     //   0	7	0	this	Lcom/atlas/common/fabric/safari/fishing/FishingInputState; } public boolean reeling() { return this.reeling; } public final int hashCode() { // Byte code:
/*   */     //   0: aload_0
/*   */     //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/safari/fishing/FishingInputState;)I
/*   */     //   6: ireturn
/*   */     // Line number table:
/*   */     //   Java source line number -> byte code offset
/*   */     //   #3	-> 0
/*   */     // Local variable table:
/*   */     //   start	length	slot	name	descriptor
/*   */     //   0	7	0	this	Lcom/atlas/common/fabric/safari/fishing/FishingInputState; } public final boolean equals(Object o) { // Byte code:
/*   */     //   0: aload_0
/*   */     //   1: aload_1
/*   */     //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/safari/fishing/FishingInputState;Ljava/lang/Object;)Z
/*   */     //   7: ireturn
/*   */     // Line number table:
/*   */     //   Java source line number -> byte code offset
/*   */     //   #3	-> 0
/*   */     // Local variable table:
/*   */     //   start	length	slot	name	descriptor
/*   */     //   0	8	0	this	Lcom/atlas/common/fabric/safari/fishing/FishingInputState;
/* 3 */     //   0	8	1	o	Ljava/lang/Object; } public boolean bracing() { return this.bracing; } public boolean pump() { return this.pump; }
/* 4 */    public static final FishingInputState IDLE = new FishingInputState(false, false, false); }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\safari\fishing\FishingInputState.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */