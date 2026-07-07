/*    */ package com.atlas.common.fabric.rustlingspots.particle;
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
/*    */ public final class Style
/*    */   extends Record
/*    */ {
/*    */   private final float scaleMultiplier;
/*    */   private final float scaleJitter;
/*    */   private final int minLifetime;
/*    */   private final int maxLifetime;
/*    */   private final float friction;
/*    */   private final float gravity;
/*    */   private final float riseAcceleration;
/*    */   
/*    */   public final String toString() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/rustlingspots/particle/RustlingBurstParticle$Style;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #36	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/fabric/rustlingspots/particle/RustlingBurstParticle$Style;
/*    */   }
/*    */   
/*    */   public final int hashCode() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/rustlingspots/particle/RustlingBurstParticle$Style;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #36	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/fabric/rustlingspots/particle/RustlingBurstParticle$Style;
/*    */   }
/*    */   
/*    */   public final boolean equals(Object o) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/rustlingspots/particle/RustlingBurstParticle$Style;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #36	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lcom/atlas/common/fabric/rustlingspots/particle/RustlingBurstParticle$Style;
/*    */     //   0	8	1	o	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public Style(float scaleMultiplier, float scaleJitter, int minLifetime, int maxLifetime, float friction, float gravity, float riseAcceleration) {
/* 36 */     this.scaleMultiplier = scaleMultiplier; this.scaleJitter = scaleJitter; this.minLifetime = minLifetime; this.maxLifetime = maxLifetime; this.friction = friction; this.gravity = gravity; this.riseAcceleration = riseAcceleration; } public float scaleMultiplier() { return this.scaleMultiplier; } public float scaleJitter() { return this.scaleJitter; } public int minLifetime() { return this.minLifetime; } public int maxLifetime() { return this.maxLifetime; } public float friction() { return this.friction; } public float gravity() { return this.gravity; } public float riseAcceleration() { return this.riseAcceleration; }
/*    */ 
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\rustlingspots\particle\RustlingBurstParticle$Style.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */