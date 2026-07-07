/*     */ package com.atlas.common.fabric.safari.fishing;
/*     */ 
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
/*     */ 
/*     */ public final class UpdatePayload
/*     */   extends Record
/*     */   implements class_8710
/*     */ {
/*     */   private final boolean active;
/*     */   private final String phase;
/*     */   private final String fishName;
/*     */   private final String rarityName;
/*     */   private final int rarityColor;
/*     */   private final float weightKg;
/*     */   private final float sizeScale;
/*     */   private final float pointer;
/*     */   private final float zoneCenter;
/*     */   private final float zoneWidth;
/*     */   private final float progress;
/*     */   private final float tension;
/*     */   private final boolean thrashing;
/*     */   private final int waitTicks;
/*     */   private final String baitName;
/*     */   private final String lineName;
/*     */   private final String reelName;
/*     */   
/*     */   public final String toString() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/safari/fishing/SafariFishingNetwork$UpdatePayload;)Ljava/lang/String;
/*     */     //   6: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #134	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	7	0	this	Lcom/atlas/common/fabric/safari/fishing/SafariFishingNetwork$UpdatePayload;
/*     */   }
/*     */   
/*     */   public final int hashCode() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/safari/fishing/SafariFishingNetwork$UpdatePayload;)I
/*     */     //   6: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #134	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	7	0	this	Lcom/atlas/common/fabric/safari/fishing/SafariFishingNetwork$UpdatePayload;
/*     */   }
/*     */   
/*     */   public final boolean equals(Object o) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/safari/fishing/SafariFishingNetwork$UpdatePayload;Ljava/lang/Object;)Z
/*     */     //   7: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #134	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	8	0	this	Lcom/atlas/common/fabric/safari/fishing/SafariFishingNetwork$UpdatePayload;
/*     */     //   0	8	1	o	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public UpdatePayload(boolean active, String phase, String fishName, String rarityName, int rarityColor, float weightKg, float sizeScale, float pointer, float zoneCenter, float zoneWidth, float progress, float tension, boolean thrashing, int waitTicks, String baitName, String lineName, String reelName) {
/* 134 */     this.active = active; this.phase = phase; this.fishName = fishName; this.rarityName = rarityName; this.rarityColor = rarityColor; this.weightKg = weightKg; this.sizeScale = sizeScale; this.pointer = pointer; this.zoneCenter = zoneCenter; this.zoneWidth = zoneWidth; this.progress = progress; this.tension = tension; this.thrashing = thrashing; this.waitTicks = waitTicks; this.baitName = baitName; this.lineName = lineName; this.reelName = reelName; } public boolean active() { return this.active; } public String phase() { return this.phase; } public String fishName() { return this.fishName; } public String rarityName() { return this.rarityName; } public int rarityColor() { return this.rarityColor; } public float weightKg() { return this.weightKg; } public float sizeScale() { return this.sizeScale; } public float pointer() { return this.pointer; } public float zoneCenter() { return this.zoneCenter; } public float zoneWidth() { return this.zoneWidth; } public float progress() { return this.progress; } public float tension() { return this.tension; } public boolean thrashing() { return this.thrashing; } public int waitTicks() { return this.waitTicks; } public String baitName() { return this.baitName; } public String lineName() { return this.lineName; } public String reelName() { return this.reelName; }
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
/*     */   public class_8710.class_9154<? extends class_8710> method_56479() {
/* 155 */     return (class_8710.class_9154)SafariFishingNetwork.UPDATE_TYPE;
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\safari\fishing\SafariFishingNetwork$UpdatePayload.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */