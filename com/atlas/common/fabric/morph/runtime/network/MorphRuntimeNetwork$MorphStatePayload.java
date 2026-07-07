/*    */ package com.atlas.common.fabric.morph.runtime.network;
/*    */ 
/*    */ import java.util.UUID;
/*    */ import net.minecraft.class_8710;
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
/*    */ public final class MorphStatePayload
/*    */   extends Record
/*    */   implements class_8710
/*    */ {
/*    */   private final UUID playerId;
/*    */   private final boolean active;
/*    */   private final String species;
/*    */   private final String aspects;
/*    */   private final String moves;
/*    */   private final int mountEntityId;
/*    */   
/*    */   public final String toString() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/morph/runtime/network/MorphRuntimeNetwork$MorphStatePayload;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #68	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/fabric/morph/runtime/network/MorphRuntimeNetwork$MorphStatePayload;
/*    */   }
/*    */   
/*    */   public final int hashCode() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/morph/runtime/network/MorphRuntimeNetwork$MorphStatePayload;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #68	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/fabric/morph/runtime/network/MorphRuntimeNetwork$MorphStatePayload;
/*    */   }
/*    */   
/*    */   public final boolean equals(Object o) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/morph/runtime/network/MorphRuntimeNetwork$MorphStatePayload;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #68	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lcom/atlas/common/fabric/morph/runtime/network/MorphRuntimeNetwork$MorphStatePayload;
/*    */     //   0	8	1	o	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public MorphStatePayload(UUID playerId, boolean active, String species, String aspects, String moves, int mountEntityId) {
/* 68 */     this.playerId = playerId; this.active = active; this.species = species; this.aspects = aspects; this.moves = moves; this.mountEntityId = mountEntityId; } public UUID playerId() { return this.playerId; } public boolean active() { return this.active; } public String species() { return this.species; } public String aspects() { return this.aspects; } public String moves() { return this.moves; } public int mountEntityId() { return this.mountEntityId; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public class_8710.class_9154<? extends class_8710> method_56479() {
/* 76 */     return (class_8710.class_9154)MorphRuntimeNetwork.MORPH_STATE_TYPE;
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\morph\runtime\network\MorphRuntimeNetwork$MorphStatePayload.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */