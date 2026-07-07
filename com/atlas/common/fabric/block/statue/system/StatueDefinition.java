/*    */ package com.atlas.common.fabric.block.statue.system;
/*    */ public final class StatueDefinition extends Record { @NotNull
/*    */   private final String id; @NotNull
/*    */   private final String modelName; @NotNull
/*    */   private final String textureName; @NotNull
/*  6 */   private final StatueStructure structure; public StatueDefinition(@NotNull String id, @NotNull String modelName, @NotNull String textureName, @NotNull StatueStructure structure, @NotNull class_2338 cleanupMin, @NotNull class_2338 cleanupMax, @NotNull StatueEffect effect, boolean gilded) { this.id = id; this.modelName = modelName; this.textureName = textureName; this.structure = structure; this.cleanupMin = cleanupMin; this.cleanupMax = cleanupMax; this.effect = effect; this.gilded = gilded; } @NotNull private final class_2338 cleanupMin; @NotNull private final class_2338 cleanupMax; @NotNull private final StatueEffect effect; private final boolean gilded; public final String toString() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/block/statue/system/StatueDefinition;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #6	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/fabric/block/statue/system/StatueDefinition; } public final int hashCode() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/block/statue/system/StatueDefinition;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #6	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/fabric/block/statue/system/StatueDefinition; } public final boolean equals(Object o) { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/block/statue/system/StatueDefinition;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #6	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lcom/atlas/common/fabric/block/statue/system/StatueDefinition;
/*  6 */     //   0	8	1	o	Ljava/lang/Object; } @NotNull public String id() { return this.id; } @NotNull public String modelName() { return this.modelName; } @NotNull public String textureName() { return this.textureName; } @NotNull public StatueStructure structure() { return this.structure; } @NotNull public class_2338 cleanupMin() { return this.cleanupMin; } @NotNull public class_2338 cleanupMax() { return this.cleanupMax; } @NotNull public StatueEffect effect() { return this.effect; } public boolean gilded() { return this.gilded; }
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
/*    */   public StatueDefinition(@NotNull String id, @NotNull String modelName, @NotNull String textureName, @NotNull StatueStructure structure, @NotNull StatueEffect effect, boolean gilded) {
/* 22 */     this(id, modelName, textureName, structure, minOffset(structure), maxOffset(structure), effect, gilded);
/*    */   }
/*    */   @NotNull
/*    */   private static class_2338 minOffset(@NotNull StatueStructure structure) {
/* 26 */     int minX = Integer.MAX_VALUE;
/* 27 */     int minY = Integer.MAX_VALUE;
/* 28 */     int minZ = Integer.MAX_VALUE;
/*    */     
/* 30 */     for (class_2338 offset : structure.getLocalOffsets()) {
/* 31 */       minX = Math.min(minX, offset.method_10263());
/* 32 */       minY = Math.min(minY, offset.method_10264());
/* 33 */       minZ = Math.min(minZ, offset.method_10260());
/*    */     } 
/*    */     
/* 36 */     return new class_2338(minX, minY, minZ);
/*    */   }
/*    */   @NotNull
/*    */   private static class_2338 maxOffset(@NotNull StatueStructure structure) {
/* 40 */     int maxX = Integer.MIN_VALUE;
/* 41 */     int maxY = Integer.MIN_VALUE;
/* 42 */     int maxZ = Integer.MIN_VALUE;
/*    */     
/* 44 */     for (class_2338 offset : structure.getLocalOffsets()) {
/* 45 */       maxX = Math.max(maxX, offset.method_10263());
/* 46 */       maxY = Math.max(maxY, offset.method_10264());
/* 47 */       maxZ = Math.max(maxZ, offset.method_10260());
/*    */     } 
/*    */     
/* 50 */     return new class_2338(maxX, maxY, maxZ);
/*    */   } }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\block\statue\system\StatueDefinition.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */