/*    */ package com.atlas.common.fabric.gocatch;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class GoCatchConfig
/*    */ {
/* 13 */   private static final GoCatchConfig INSTANCE = new GoCatchConfig();
/*    */   
/*    */   public static GoCatchConfig getInstance() {
/* 16 */     return INSTANCE;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean enabled = true;
/* 21 */   public double ringMaxSize = 1.5D;
/* 22 */   public double ringMinSize = 0.15D;
/* 23 */   public double baseShrinkSpeed = 1.0D;
/* 24 */   public double levelSpeedScaling = 0.008D;
/* 25 */   public double speedMultiplier = 1.0D;
/* 26 */   public double targetingRange = 12.0D;
/*    */   
/*    */   public boolean unpredictableRing = false;
/*    */   
/* 30 */   public double niceThreshold = 1.0D;
/* 31 */   public double greatThreshold = 0.65D;
/* 32 */   public double excellentThreshold = 0.35D;
/*    */ 
/*    */   
/* 35 */   public double niceBonus = 1.1D;
/* 36 */   public double greatBonus = 1.2D;
/* 37 */   public double excellentBonus = 1.3D;
/* 38 */   public double curveBonus = 1.2D;
/*    */ 
/*    */   
/* 41 */   public int niceColor = 5635925;
/* 42 */   public int greatColor = 5592575;
/* 43 */   public int excellentColor = 16755200;
/* 44 */   public int curveColor = 16733695;
/*    */   
/*    */   public boolean curveEnabled = false;
/*    */   
/* 48 */   public double curveDifficultyMultiplier = 1.0D;
/* 49 */   public double curveSparkleThresholdMultiplier = 1.0D;
/*    */ 
/*    */   
/* 52 */   public final Map<String, TierOverride> overrides = new HashMap<>();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ResolvedConfig resolve(String tier) {
/* 60 */     if (tier == null || tier.isEmpty() || !this.overrides.containsKey(tier)) {
/* 61 */       return new ResolvedConfig(this.baseShrinkSpeed, this.speedMultiplier, this.niceBonus, this.greatBonus, this.excellentBonus);
/*    */     }
/*    */     
/* 64 */     TierOverride override = this.overrides.get(tier);
/* 65 */     return new ResolvedConfig(
/* 66 */         (override.baseShrinkSpeed > 0.0D) ? override.baseShrinkSpeed : this.baseShrinkSpeed, 
/* 67 */         (override.speedMultiplier > 0.0D) ? override.speedMultiplier : this.speedMultiplier, 
/* 68 */         (override.niceBonus > 0.0D) ? override.niceBonus : this.niceBonus, 
/* 69 */         (override.greatBonus > 0.0D) ? override.greatBonus : this.greatBonus, 
/* 70 */         (override.excellentBonus > 0.0D) ? override.excellentBonus : this.excellentBonus);
/*    */   }
/*    */ 
/*    */   
/*    */   public static final class TierOverride
/*    */   {
/*    */     public double baseShrinkSpeed;
/*    */     
/*    */     public double speedMultiplier;
/*    */     
/*    */     public double niceBonus;
/*    */     
/*    */     public double greatBonus;
/*    */     public double excellentBonus;
/*    */     
/*    */     public TierOverride() {}
/*    */     
/*    */     public TierOverride(double baseShrinkSpeed, double speedMultiplier, double niceBonus, double greatBonus, double excellentBonus) {
/* 88 */       this.baseShrinkSpeed = baseShrinkSpeed;
/* 89 */       this.speedMultiplier = speedMultiplier;
/* 90 */       this.niceBonus = niceBonus;
/* 91 */       this.greatBonus = greatBonus;
/* 92 */       this.excellentBonus = excellentBonus;
/*    */     } }
/*    */   public static final class ResolvedConfig extends Record { private final double baseShrinkSpeed; private final double speedMultiplier;
/*    */     private final double niceBonus;
/*    */     private final double greatBonus;
/*    */     private final double excellentBonus;
/*    */     
/* 99 */     public ResolvedConfig(double baseShrinkSpeed, double speedMultiplier, double niceBonus, double greatBonus, double excellentBonus) { this.baseShrinkSpeed = baseShrinkSpeed; this.speedMultiplier = speedMultiplier; this.niceBonus = niceBonus; this.greatBonus = greatBonus; this.excellentBonus = excellentBonus; } public final String toString() { // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/gocatch/GoCatchConfig$ResolvedConfig;)Ljava/lang/String;
/*    */       //   6: areturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #99	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/* 99 */       //   0	7	0	this	Lcom/atlas/common/fabric/gocatch/GoCatchConfig$ResolvedConfig; } public double baseShrinkSpeed() { return this.baseShrinkSpeed; } public final int hashCode() { // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/gocatch/GoCatchConfig$ResolvedConfig;)I
/*    */       //   6: ireturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #99	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	7	0	this	Lcom/atlas/common/fabric/gocatch/GoCatchConfig$ResolvedConfig; } public final boolean equals(Object o) { // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: aload_1
/*    */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/gocatch/GoCatchConfig$ResolvedConfig;Ljava/lang/Object;)Z
/*    */       //   7: ireturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #99	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	8	0	this	Lcom/atlas/common/fabric/gocatch/GoCatchConfig$ResolvedConfig;
/* 99 */       //   0	8	1	o	Ljava/lang/Object; } public double speedMultiplier() { return this.speedMultiplier; } public double niceBonus() { return this.niceBonus; } public double greatBonus() { return this.greatBonus; } public double excellentBonus() { return this.excellentBonus; }
/*    */      }
/*    */ 
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\gocatch\GoCatchConfig.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */