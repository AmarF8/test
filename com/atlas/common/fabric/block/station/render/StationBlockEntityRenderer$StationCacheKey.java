/*    */ package com.atlas.common.fabric.block.station.render;
/*    */ 
/*    */ import java.util.UUID;
/*    */ import net.minecraft.class_2338;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ final class StationCacheKey
/*    */   extends Record
/*    */ {
/*    */   private final class_2338 pos;
/*    */   private final UUID pokemonUuid;
/*    */   
/*    */   public final String toString() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/block/station/render/StationBlockEntityRenderer$StationCacheKey;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #31	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/fabric/block/station/render/StationBlockEntityRenderer$StationCacheKey;
/*    */   }
/*    */   
/*    */   public final int hashCode() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/block/station/render/StationBlockEntityRenderer$StationCacheKey;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #31	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/fabric/block/station/render/StationBlockEntityRenderer$StationCacheKey;
/*    */   }
/*    */   
/*    */   public final boolean equals(Object o) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/block/station/render/StationBlockEntityRenderer$StationCacheKey;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #31	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lcom/atlas/common/fabric/block/station/render/StationBlockEntityRenderer$StationCacheKey;
/*    */     //   0	8	1	o	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   private StationCacheKey(class_2338 pos, UUID pokemonUuid) {
/* 31 */     this.pos = pos; this.pokemonUuid = pokemonUuid; } public class_2338 pos() { return this.pos; } public UUID pokemonUuid() { return this.pokemonUuid; }
/*    */ 
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\block\station\render\StationBlockEntityRenderer$StationCacheKey.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */