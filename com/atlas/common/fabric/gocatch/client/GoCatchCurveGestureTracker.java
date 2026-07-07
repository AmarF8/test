/*     */ package com.atlas.common.fabric.gocatch.client;
/*     */ 
/*     */ import com.atlas.common.fabric.gocatch.GoCatchConfig;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.fabricmc.api.EnvType;
/*     */ import net.fabricmc.api.Environment;
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
/*     */ @Environment(EnvType.CLIENT)
/*     */ final class GoCatchCurveGestureTracker
/*     */ {
/*     */   private static final long GESTURE_MIN_TIME_MS = 220L;
/*     */   private static final long GESTURE_MAX_IDLE_TIME_MS = 220L;
/*     */   private static final int GESTURE_MIN_SAMPLES = 10;
/*     */   private static final double GESTURE_MIN_LOOK_DELTA = 5.0D;
/*     */   private static final double GESTURE_MIN_TOTAL_TRAVEL = 270.0D;
/*     */   private static final double GESTURE_MIN_HORIZONTAL_TRAVEL = 134.0D;
/*     */   private static final double GESTURE_MIN_VERTICAL_TRAVEL = 62.0D;
/*     */   private static final double GESTURE_MIN_CUMULATIVE_TURN = 4.65D;
/*     */   private static final double GESTURE_MIN_NET_TURN = 2.01D;
/*     */   private static final double GESTURE_MIN_CONSISTENCY = 0.64D;
/*     */   private static final int SPARKLE_MIN_SAMPLES = 8;
/*     */   private static final double SPARKLE_LOOK_DELTA = 19.5D;
/*     */   private static final double SPARKLE_MIN_TOTAL_TRAVEL = 245.0D;
/*     */   private static final double SPARKLE_MIN_HORIZONTAL_TRAVEL = 122.0D;
/*     */   private static final double SPARKLE_MIN_VERTICAL_TRAVEL = 54.0D;
/*     */   private static final double SPARKLE_MIN_CUMULATIVE_TURN = 3.77D;
/*     */   private static final double SPARKLE_MIN_NET_TURN = 1.63D;
/*  39 */   private final List<LookSample> samples = new ArrayList<>();
/*     */   private boolean gestureActive = false;
/*  41 */   private long gestureStartTime = 0L;
/*     */   
/*     */   private boolean curveDetected = false;
/*     */   
/*     */   private boolean sparkleDetected = false;
/*  46 */   private double totalHorizontalTravel = 0.0D;
/*  47 */   private double totalVerticalTravel = 0.0D;
/*  48 */   private double cumulativeTurn = 0.0D;
/*  49 */   private double netTurnX = 0.0D;
/*  50 */   private double netTurnY = 0.0D;
/*  51 */   private float lastYaw = 0.0F;
/*  52 */   private float lastPitch = 0.0F;
/*     */ 
/*     */ 
/*     */   
/*     */   void recordLookChange(float yaw, float pitch) {
/*  57 */     long now = System.currentTimeMillis();
/*     */     
/*  59 */     if (!this.gestureActive) {
/*  60 */       startGesture(yaw, pitch, now);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/*  65 */     if (!this.samples.isEmpty()) {
/*  66 */       LookSample last = this.samples.get(this.samples.size() - 1);
/*  67 */       if (now - last.timestamp > 220L) {
/*  68 */         reset();
/*  69 */         startGesture(yaw, pitch, now);
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/*  74 */     float deltaYaw = yaw - this.lastYaw;
/*  75 */     float deltaPitch = pitch - this.lastPitch;
/*  76 */     double lookDelta = Math.sqrt((deltaYaw * deltaYaw + deltaPitch * deltaPitch));
/*     */     
/*  78 */     this.totalHorizontalTravel += Math.abs(deltaYaw);
/*  79 */     this.totalVerticalTravel += Math.abs(deltaPitch);
/*     */ 
/*     */     
/*  82 */     this.netTurnX += deltaYaw;
/*  83 */     this.netTurnY += deltaPitch;
/*  84 */     this.cumulativeTurn += Math.abs(Math.atan2(deltaPitch, deltaYaw));
/*     */     
/*  86 */     this.samples.add(new LookSample(yaw, pitch, now, lookDelta));
/*  87 */     this.lastYaw = yaw;
/*  88 */     this.lastPitch = pitch;
/*     */     
/*  90 */     evaluateGesture(now);
/*     */   }
/*     */   
/*     */   private void startGesture(float yaw, float pitch, long time) {
/*  94 */     this.gestureActive = true;
/*  95 */     this.gestureStartTime = time;
/*  96 */     this.lastYaw = yaw;
/*  97 */     this.lastPitch = pitch;
/*  98 */     this.samples.clear();
/*  99 */     this.samples.add(new LookSample(yaw, pitch, time, 0.0D));
/* 100 */     this.totalHorizontalTravel = 0.0D;
/* 101 */     this.totalVerticalTravel = 0.0D;
/* 102 */     this.cumulativeTurn = 0.0D;
/* 103 */     this.netTurnX = 0.0D;
/* 104 */     this.netTurnY = 0.0D;
/* 105 */     this.curveDetected = false;
/* 106 */     this.sparkleDetected = false;
/*     */   }
/*     */   
/*     */   private void evaluateGesture(long now) {
/* 110 */     GoCatchConfig config = GoCatchConfig.getInstance();
/* 111 */     double diffMul = config.curveDifficultyMultiplier;
/* 112 */     double sparkleMul = config.curveSparkleThresholdMultiplier;
/*     */     
/* 114 */     long elapsed = now - this.gestureStartTime;
/* 115 */     double totalTravel = this.totalHorizontalTravel + this.totalVerticalTravel;
/* 116 */     double netTurn = Math.sqrt(this.netTurnX * this.netTurnX + this.netTurnY * this.netTurnY);
/* 117 */     double consistency = (this.cumulativeTurn > 0.0D) ? (netTurn / this.cumulativeTurn) : 0.0D;
/*     */ 
/*     */     
/* 120 */     if (!this.sparkleDetected && this.samples
/* 121 */       .size() >= 8 && 
/* 122 */       hasRecentLookDelta(19.5D * sparkleMul) && totalTravel >= 245.0D * sparkleMul && this.totalHorizontalTravel >= 122.0D * sparkleMul && this.totalVerticalTravel >= 54.0D * sparkleMul && this.cumulativeTurn >= 3.77D * sparkleMul && netTurn >= 1.63D * sparkleMul)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 128 */       this.sparkleDetected = true;
/*     */     }
/*     */ 
/*     */     
/* 132 */     if (!this.curveDetected && elapsed >= 220L && this.samples
/*     */       
/* 134 */       .size() >= 10 && 
/* 135 */       hasRecentLookDelta(5.0D * diffMul) && totalTravel >= 270.0D * diffMul && this.totalHorizontalTravel >= 134.0D * diffMul && this.totalVerticalTravel >= 62.0D * diffMul && this.cumulativeTurn >= 4.65D * diffMul && netTurn >= 2.01D * diffMul && consistency >= 0.64D * diffMul)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 142 */       this.curveDetected = true;
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean hasRecentLookDelta(double threshold) {
/* 147 */     if (this.samples.isEmpty()) return false;
/*     */     
/* 149 */     int checkCount = Math.min(5, this.samples.size());
/* 150 */     for (int i = this.samples.size() - checkCount; i < this.samples.size(); i++) {
/* 151 */       if (((LookSample)this.samples.get(i)).lookDelta >= threshold) return true; 
/*     */     } 
/* 153 */     return false;
/*     */   }
/*     */   
/*     */   boolean isCurveDetected() {
/* 157 */     return this.curveDetected;
/*     */   }
/*     */   
/*     */   boolean isSparkleDetected() {
/* 161 */     return this.sparkleDetected;
/*     */   }
/*     */   
/*     */   boolean isGestureActive() {
/* 165 */     return this.gestureActive;
/*     */   }
/*     */   
/*     */   void reset() {
/* 169 */     this.gestureActive = false;
/* 170 */     this.gestureStartTime = 0L;
/* 171 */     this.samples.clear();
/* 172 */     this.totalHorizontalTravel = 0.0D;
/* 173 */     this.totalVerticalTravel = 0.0D;
/* 174 */     this.cumulativeTurn = 0.0D;
/* 175 */     this.netTurnX = 0.0D;
/* 176 */     this.netTurnY = 0.0D;
/* 177 */     this.curveDetected = false;
/* 178 */     this.sparkleDetected = false;
/*     */   }
/*     */   private static final class LookSample extends Record { private final float yaw; private final float pitch; private final long timestamp; private final double lookDelta;
/* 181 */     private LookSample(float yaw, float pitch, long timestamp, double lookDelta) { this.yaw = yaw; this.pitch = pitch; this.timestamp = timestamp; this.lookDelta = lookDelta; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/gocatch/client/GoCatchCurveGestureTracker$LookSample;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #181	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/* 181 */       //   0	7	0	this	Lcom/atlas/common/fabric/gocatch/client/GoCatchCurveGestureTracker$LookSample; } public float yaw() { return this.yaw; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/gocatch/client/GoCatchCurveGestureTracker$LookSample;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #181	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/gocatch/client/GoCatchCurveGestureTracker$LookSample; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/gocatch/client/GoCatchCurveGestureTracker$LookSample;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #181	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lcom/atlas/common/fabric/gocatch/client/GoCatchCurveGestureTracker$LookSample;
/* 181 */       //   0	8	1	o	Ljava/lang/Object; } public float pitch() { return this.pitch; } public long timestamp() { return this.timestamp; } public double lookDelta() { return this.lookDelta; }
/*     */      }
/*     */ 
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\gocatch\client\GoCatchCurveGestureTracker.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */