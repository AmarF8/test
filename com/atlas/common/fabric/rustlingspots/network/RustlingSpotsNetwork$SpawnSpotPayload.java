/*    */ package com.atlas.common.fabric.rustlingspots.network;
/*    */ 
/*    */ import java.util.UUID;
/*    */ import net.minecraft.class_2338;
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
/*    */ public final class SpawnSpotPayload
/*    */   extends Record
/*    */   implements class_8710
/*    */ {
/*    */   private final UUID id;
/*    */   private final class_2338 pos;
/*    */   private final int familyOrdinal;
/*    */   private final boolean shiny;
/*    */   
/*    */   public final String toString() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/rustlingspots/network/RustlingSpotsNetwork$SpawnSpotPayload;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #81	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/fabric/rustlingspots/network/RustlingSpotsNetwork$SpawnSpotPayload;
/*    */   }
/*    */   
/*    */   public final int hashCode() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/rustlingspots/network/RustlingSpotsNetwork$SpawnSpotPayload;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #81	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/fabric/rustlingspots/network/RustlingSpotsNetwork$SpawnSpotPayload;
/*    */   }
/*    */   
/*    */   public final boolean equals(Object o) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/rustlingspots/network/RustlingSpotsNetwork$SpawnSpotPayload;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #81	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lcom/atlas/common/fabric/rustlingspots/network/RustlingSpotsNetwork$SpawnSpotPayload;
/*    */     //   0	8	1	o	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public SpawnSpotPayload(UUID id, class_2338 pos, int familyOrdinal, boolean shiny) {
/* 81 */     this.id = id; this.pos = pos; this.familyOrdinal = familyOrdinal; this.shiny = shiny; } public UUID id() { return this.id; } public class_2338 pos() { return this.pos; } public int familyOrdinal() { return this.familyOrdinal; } public boolean shiny() { return this.shiny; }
/*    */   
/*    */   public class_8710.class_9154<? extends class_8710> method_56479() {
/* 84 */     return (class_8710.class_9154)RustlingSpotsNetwork.SPAWN_SPOT_TYPE;
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\rustlingspots\network\RustlingSpotsNetwork$SpawnSpotPayload.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */