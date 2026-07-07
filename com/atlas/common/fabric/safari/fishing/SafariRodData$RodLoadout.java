/*     */ package com.atlas.common.fabric.safari.fishing;
/*     */ 
/*     */ import org.jetbrains.annotations.Nullable;
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
/*     */ public final class RodLoadout
/*     */   extends Record
/*     */ {
/*     */   @Nullable
/*     */   private final String bait;
/*     */   @Nullable
/*     */   private final String line;
/*     */   @Nullable
/*     */   private final String reel;
/*     */   private final double biteSpeed;
/*     */   private final double rareBonus;
/*     */   private final double deepBonus;
/*     */   private final double safeZoneBonus;
/*     */   private final double tensionRelief;
/*     */   private final double reelPower;
/*     */   private final double pumpPower;
/*     */   
/*     */   public final String toString() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/safari/fishing/SafariRodData$RodLoadout;)Ljava/lang/String;
/*     */     //   6: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #114	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	7	0	this	Lcom/atlas/common/fabric/safari/fishing/SafariRodData$RodLoadout;
/*     */   }
/*     */   
/*     */   public final int hashCode() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/safari/fishing/SafariRodData$RodLoadout;)I
/*     */     //   6: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #114	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	7	0	this	Lcom/atlas/common/fabric/safari/fishing/SafariRodData$RodLoadout;
/*     */   }
/*     */   
/*     */   public final boolean equals(Object o) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/safari/fishing/SafariRodData$RodLoadout;Ljava/lang/Object;)Z
/*     */     //   7: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #114	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	8	0	this	Lcom/atlas/common/fabric/safari/fishing/SafariRodData$RodLoadout;
/*     */     //   0	8	1	o	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public RodLoadout(@Nullable String bait, @Nullable String line, @Nullable String reel, double biteSpeed, double rareBonus, double deepBonus, double safeZoneBonus, double tensionRelief, double reelPower, double pumpPower) {
/* 114 */     this.bait = bait; this.line = line; this.reel = reel; this.biteSpeed = biteSpeed; this.rareBonus = rareBonus; this.deepBonus = deepBonus; this.safeZoneBonus = safeZoneBonus; this.tensionRelief = tensionRelief; this.reelPower = reelPower; this.pumpPower = pumpPower; } @Nullable public String bait() { return this.bait; } @Nullable public String line() { return this.line; } @Nullable public String reel() { return this.reel; } public double biteSpeed() { return this.biteSpeed; } public double rareBonus() { return this.rareBonus; } public double deepBonus() { return this.deepBonus; } public double safeZoneBonus() { return this.safeZoneBonus; } public double tensionRelief() { return this.tensionRelief; } public double reelPower() { return this.reelPower; } public double pumpPower() { return this.pumpPower; }
/*     */ 
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\safari\fishing\SafariRodData$RodLoadout.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */