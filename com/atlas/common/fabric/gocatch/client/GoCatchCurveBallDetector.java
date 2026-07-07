/*     */ package com.atlas.common.fabric.gocatch.client;
/*     */ 
/*     */ import com.atlas.common.fabric.gocatch.GoCatchConfig;
/*     */ import com.atlas.common.fabric.gocatch.particle.GoCatchParticleTypes;
/*     */ import net.fabricmc.api.EnvType;
/*     */ import net.fabricmc.api.Environment;
/*     */ import net.minecraft.class_2394;
/*     */ import net.minecraft.class_310;
/*     */ import net.minecraft.class_746;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Environment(EnvType.CLIENT)
/*     */ public final class GoCatchCurveBallDetector
/*     */ {
/*  18 */   private static final GoCatchCurveBallDetector INSTANCE = new GoCatchCurveBallDetector();
/*     */   
/*     */   public static GoCatchCurveBallDetector getInstance() {
/*  21 */     return INSTANCE;
/*     */   }
/*     */   
/*  24 */   private final GoCatchCurveGestureTracker gestureTracker = new GoCatchCurveGestureTracker();
/*     */   private boolean tracking = false;
/*     */   private boolean curveGestureDetected = false;
/*  27 */   private float prevYaw = 0.0F;
/*  28 */   private float prevPitch = 0.0F;
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean sparkleSpawned = false;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startTick(class_310 client) {
/*  38 */     GoCatchConfig config = GoCatchConfig.getInstance();
/*  39 */     if (!config.enabled || !config.curveEnabled) {
/*  40 */       if (this.tracking) reset();
/*     */       
/*     */       return;
/*     */     } 
/*  44 */     class_746 player = client.field_1724;
/*  45 */     if (player == null)
/*     */       return; 
/*  47 */     if (!this.tracking) {
/*  48 */       this.tracking = true;
/*  49 */       this.prevYaw = player.method_36454();
/*  50 */       this.prevPitch = player.method_36455();
/*  51 */       this.gestureTracker.reset();
/*  52 */       this.curveGestureDetected = false;
/*  53 */       this.sparkleSpawned = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick(class_310 client) {
/*  62 */     if (!this.tracking)
/*  63 */       return;  if (!(GoCatchConfig.getInstance()).curveEnabled) { reset(); return; }
/*     */     
/*  65 */     class_746 player = client.field_1724;
/*  66 */     if (player == null) {
/*  67 */       reset();
/*     */       
/*     */       return;
/*     */     } 
/*  71 */     float yaw = player.method_36454();
/*  72 */     float pitch = player.method_36455();
/*     */     
/*  74 */     float deltaYaw = yaw - this.prevYaw;
/*  75 */     float deltaPitch = pitch - this.prevPitch;
/*  76 */     double lookDelta = Math.sqrt((deltaYaw * deltaYaw + deltaPitch * deltaPitch));
/*     */     
/*  78 */     if (lookDelta > 0.1D) {
/*  79 */       this.gestureTracker.recordLookChange(yaw, pitch);
/*     */     }
/*     */     
/*  82 */     this.prevYaw = yaw;
/*  83 */     this.prevPitch = pitch;
/*     */ 
/*     */     
/*  86 */     if (this.gestureTracker.isCurveDetected()) {
/*  87 */       this.curveGestureDetected = true;
/*     */     }
/*     */ 
/*     */     
/*  91 */     if (this.gestureTracker.isSparkleDetected() && !this.sparkleSpawned && client.field_1687 != null) {
/*  92 */       this.sparkleSpawned = true;
/*  93 */       spawnGestureParticles(client);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void spawnGestureParticles(class_310 client) {
/*  98 */     if (client.field_1724 == null || client.field_1687 == null)
/*  99 */       return;  double x = client.field_1724.method_23317();
/* 100 */     double y = client.field_1724.method_23320();
/* 101 */     double z = client.field_1724.method_23321();
/* 102 */     for (int i = 0; i < 5; i++) {
/* 103 */       double ox = (client.field_1687.field_9229.method_43058() - 0.5D) * 0.6D;
/* 104 */       double oy = (client.field_1687.field_9229.method_43058() - 0.5D) * 0.3D;
/* 105 */       double oz = (client.field_1687.field_9229.method_43058() - 0.5D) * 0.6D;
/* 106 */       client.field_1687.method_8406((class_2394)GoCatchParticleTypes.LOW_SPARKLE, x + ox, y + oy, z + oz, 0.0D, 0.02D, 0.0D);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isCurveBallGesture() {
/* 114 */     if (!(GoCatchConfig.getInstance()).curveEnabled) return false; 
/* 115 */     return this.curveGestureDetected;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onThrow() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void reset() {
/* 129 */     this.tracking = false;
/* 130 */     this.curveGestureDetected = false;
/* 131 */     this.sparkleSpawned = false;
/* 132 */     this.gestureTracker.reset();
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\gocatch\client\GoCatchCurveBallDetector.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */