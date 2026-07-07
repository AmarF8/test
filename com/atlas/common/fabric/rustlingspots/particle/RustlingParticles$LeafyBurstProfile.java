/*    */ package com.atlas.common.fabric.rustlingspots.particle;
/*    */ 
/*    */ import net.minecraft.class_2394;
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
/*    */ final class LeafyBurstProfile
/*    */   extends Record
/*    */ {
/*    */   private final class_2394 primary;
/*    */   private final int phaseOneMin;
/*    */   private final int phaseOneMax;
/*    */   private final int phaseTwoMin;
/*    */   private final int phaseTwoMax;
/*    */   private final float radiusMin;
/*    */   private final float radiusMax;
/*    */   private final float upwardBase;
/*    */   private final float upwardJitter;
/*    */   private final float horizontalBase;
/*    */   private final float horizontalJitter;
/*    */   private final float yOffsetBase;
/*    */   private final float yOffsetJitter;
/*    */   
/*    */   public final String toString() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/rustlingspots/particle/RustlingParticles$LeafyBurstProfile;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #71	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/fabric/rustlingspots/particle/RustlingParticles$LeafyBurstProfile;
/*    */   }
/*    */   
/*    */   public final int hashCode() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/rustlingspots/particle/RustlingParticles$LeafyBurstProfile;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #71	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/fabric/rustlingspots/particle/RustlingParticles$LeafyBurstProfile;
/*    */   }
/*    */   
/*    */   public final boolean equals(Object o) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/rustlingspots/particle/RustlingParticles$LeafyBurstProfile;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #71	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lcom/atlas/common/fabric/rustlingspots/particle/RustlingParticles$LeafyBurstProfile;
/*    */     //   0	8	1	o	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   private LeafyBurstProfile(class_2394 primary, int phaseOneMin, int phaseOneMax, int phaseTwoMin, int phaseTwoMax, float radiusMin, float radiusMax, float upwardBase, float upwardJitter, float horizontalBase, float horizontalJitter, float yOffsetBase, float yOffsetJitter) {
/* 71 */     this.primary = primary; this.phaseOneMin = phaseOneMin; this.phaseOneMax = phaseOneMax; this.phaseTwoMin = phaseTwoMin; this.phaseTwoMax = phaseTwoMax; this.radiusMin = radiusMin; this.radiusMax = radiusMax; this.upwardBase = upwardBase; this.upwardJitter = upwardJitter; this.horizontalBase = horizontalBase; this.horizontalJitter = horizontalJitter; this.yOffsetBase = yOffsetBase; this.yOffsetJitter = yOffsetJitter; } public class_2394 primary() { return this.primary; } public int phaseOneMin() { return this.phaseOneMin; } public int phaseOneMax() { return this.phaseOneMax; } public int phaseTwoMin() { return this.phaseTwoMin; } public int phaseTwoMax() { return this.phaseTwoMax; } public float radiusMin() { return this.radiusMin; } public float radiusMax() { return this.radiusMax; } public float upwardBase() { return this.upwardBase; } public float upwardJitter() { return this.upwardJitter; } public float horizontalBase() { return this.horizontalBase; } public float horizontalJitter() { return this.horizontalJitter; } public float yOffsetBase() { return this.yOffsetBase; } public float yOffsetJitter() { return this.yOffsetJitter; }
/*    */ 
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\rustlingspots\particle\RustlingParticles$LeafyBurstProfile.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */