/*     */ package com.atlas.common.fabric.morph.runtime.network;
/*     */ 
/*     */ import java.util.UUID;
/*     */ import net.minecraft.class_243;
/*     */ import net.minecraft.class_8710;
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
/*     */ public final class PlayMovePayload
/*     */   extends Record
/*     */   implements class_8710
/*     */ {
/*     */   private final UUID shooterId;
/*     */   private final int slot;
/*     */   private final String move;
/*     */   private final class_243 origin;
/*     */   private final class_243 direction;
/*     */   private final class_243 hit;
/*     */   private final int targetEntityId;
/*     */   private final class_243 rootPos;
/*     */   private final float rootYaw;
/*     */   private final float rootPitch;
/*     */   private final float rootBodyYaw;
/*     */   private final float rootHeadYaw;
/*     */   private final class_243 mountPos;
/*     */   private final float mountYaw;
/*     */   private final float mountPitch;
/*     */   private final float mountBodyYaw;
/*     */   private final float mountHeadYaw;
/*     */   
/*     */   public final String toString() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/morph/runtime/network/MorphRuntimeNetwork$PlayMovePayload;)Ljava/lang/String;
/*     */     //   6: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #88	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	7	0	this	Lcom/atlas/common/fabric/morph/runtime/network/MorphRuntimeNetwork$PlayMovePayload;
/*     */   }
/*     */   
/*     */   public final int hashCode() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/morph/runtime/network/MorphRuntimeNetwork$PlayMovePayload;)I
/*     */     //   6: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #88	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	7	0	this	Lcom/atlas/common/fabric/morph/runtime/network/MorphRuntimeNetwork$PlayMovePayload;
/*     */   }
/*     */   
/*     */   public final boolean equals(Object o) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/morph/runtime/network/MorphRuntimeNetwork$PlayMovePayload;Ljava/lang/Object;)Z
/*     */     //   7: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #88	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	8	0	this	Lcom/atlas/common/fabric/morph/runtime/network/MorphRuntimeNetwork$PlayMovePayload;
/*     */     //   0	8	1	o	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public PlayMovePayload(UUID shooterId, int slot, String move, class_243 origin, class_243 direction, class_243 hit, int targetEntityId, class_243 rootPos, float rootYaw, float rootPitch, float rootBodyYaw, float rootHeadYaw, class_243 mountPos, float mountYaw, float mountPitch, float mountBodyYaw, float mountHeadYaw) {
/*  88 */     this.shooterId = shooterId; this.slot = slot; this.move = move; this.origin = origin; this.direction = direction; this.hit = hit; this.targetEntityId = targetEntityId; this.rootPos = rootPos; this.rootYaw = rootYaw; this.rootPitch = rootPitch; this.rootBodyYaw = rootBodyYaw; this.rootHeadYaw = rootHeadYaw; this.mountPos = mountPos; this.mountYaw = mountYaw; this.mountPitch = mountPitch; this.mountBodyYaw = mountBodyYaw; this.mountHeadYaw = mountHeadYaw; } public UUID shooterId() { return this.shooterId; } public int slot() { return this.slot; } public String move() { return this.move; } public class_243 origin() { return this.origin; } public class_243 direction() { return this.direction; } public class_243 hit() { return this.hit; } public int targetEntityId() { return this.targetEntityId; } public class_243 rootPos() { return this.rootPos; } public float rootYaw() { return this.rootYaw; } public float rootPitch() { return this.rootPitch; } public float rootBodyYaw() { return this.rootBodyYaw; } public float rootHeadYaw() { return this.rootHeadYaw; } public class_243 mountPos() { return this.mountPos; } public float mountYaw() { return this.mountYaw; } public float mountPitch() { return this.mountPitch; } public float mountBodyYaw() { return this.mountBodyYaw; } public float mountHeadYaw() { return this.mountHeadYaw; }
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
/*     */   public class_8710.class_9154<? extends class_8710> method_56479() {
/* 107 */     return (class_8710.class_9154)MorphRuntimeNetwork.PLAY_MOVE_TYPE;
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\morph\runtime\network\MorphRuntimeNetwork$PlayMovePayload.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */