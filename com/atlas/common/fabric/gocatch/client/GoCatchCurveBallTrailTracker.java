/*     */ package com.atlas.common.fabric.gocatch.client;
/*     */ 
/*     */ import com.atlas.common.fabric.gocatch.particle.GoCatchParticleTypes;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.fabricmc.api.EnvType;
/*     */ import net.fabricmc.api.Environment;
/*     */ import net.minecraft.class_1297;
/*     */ import net.minecraft.class_2394;
/*     */ import net.minecraft.class_2400;
/*     */ import net.minecraft.class_243;
/*     */ import net.minecraft.class_310;
/*     */ import net.minecraft.class_638;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Environment(EnvType.CLIENT)
/*     */ public final class GoCatchCurveBallTrailTracker
/*     */ {
/*  24 */   private static final GoCatchCurveBallTrailTracker INSTANCE = new GoCatchCurveBallTrailTracker();
/*     */   private static final long PENDING_EXPIRE_MS = 1000L;
/*     */   private static final int MAX_TRAIL_TICKS = 60;
/*     */   private static final int PARTICLES_PER_TICK = 2;
/*     */   
/*     */   public static GoCatchCurveBallTrailTracker getInstance() {
/*  30 */     return INSTANCE;
/*     */   }
/*     */   
/*  33 */   private final List<PendingTrail> pendingTrails = new ArrayList<>();
/*  34 */   private final List<ActiveTrail> activeTrails = new ArrayList<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void queuePendingTrail() {
/*  43 */     this.pendingTrails.add(new PendingTrail(System.currentTimeMillis()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick(class_310 client) {
/*  51 */     if (client.field_1687 == null || client.field_1724 == null)
/*     */       return; 
/*  53 */     long now = System.currentTimeMillis();
/*     */ 
/*     */     
/*  56 */     this.pendingTrails.removeIf(p -> (now - p.createdAt > 1000L));
/*     */ 
/*     */     
/*  59 */     if (!this.pendingTrails.isEmpty()) {
/*  60 */       matchProjectiles(client);
/*     */     }
/*     */ 
/*     */     
/*  64 */     Iterator<ActiveTrail> it = this.activeTrails.iterator();
/*  65 */     while (it.hasNext()) {
/*  66 */       ActiveTrail trail = it.next();
/*  67 */       trail.ticksAlive++;
/*  68 */       if (trail.ticksAlive > 60) {
/*  69 */         it.remove();
/*     */         
/*     */         continue;
/*     */       } 
/*  73 */       class_1297 entity = client.field_1687.method_8469(trail.entityId);
/*  74 */       if (entity == null || !entity.method_5805()) {
/*  75 */         it.remove();
/*     */         
/*     */         continue;
/*     */       } 
/*  79 */       spawnTrailParticles(client.field_1687, entity.method_19538(), trail.ticksAlive);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void matchProjectiles(class_310 client) {
/*  84 */     if (client.field_1687 == null || client.field_1724 == null)
/*  85 */       return;  class_243 playerPos = client.field_1724.method_19538();
/*  86 */     double searchRange = 8.0D;
/*     */     
/*  88 */     for (class_1297 entity : client.field_1687.method_18112()) {
/*  89 */       if (!(entity instanceof net.minecraft.class_1676) || 
/*  90 */         entity.field_6012 > 5 || 
/*  91 */         entity.method_19538().method_1022(playerPos) > searchRange) {
/*     */         continue;
/*     */       }
/*  94 */       boolean alreadyTracked = false;
/*  95 */       for (ActiveTrail trail : this.activeTrails) {
/*  96 */         if (trail.entityId == entity.method_5628()) {
/*  97 */           alreadyTracked = true;
/*     */           break;
/*     */         } 
/*     */       } 
/* 101 */       if (alreadyTracked)
/*     */         continue; 
/* 103 */       if (!this.pendingTrails.isEmpty()) {
/* 104 */         this.pendingTrails.remove(0);
/* 105 */         this.activeTrails.add(new ActiveTrail(entity.method_5628()));
/*     */         break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void spawnTrailParticles(class_638 world, class_243 pos, int ticksAlive) {
/*     */     class_2400 particleType;
/* 113 */     if (ticksAlive < 10) {
/* 114 */       particleType = GoCatchParticleTypes.FULL_SPARKLE;
/* 115 */     } else if (ticksAlive < 30) {
/* 116 */       particleType = GoCatchParticleTypes.MEDIUM_SPARKLE;
/*     */     } else {
/* 118 */       particleType = GoCatchParticleTypes.LOW_SPARKLE;
/*     */     } 
/*     */     
/* 121 */     for (int i = 0; i < 2; i++) {
/* 122 */       double ox = (world.field_9229.method_43058() - 0.5D) * 0.3D;
/* 123 */       double oy = (world.field_9229.method_43058() - 0.5D) * 0.3D;
/* 124 */       double oz = (world.field_9229.method_43058() - 0.5D) * 0.3D;
/* 125 */       world.method_8406((class_2394)particleType, pos.field_1352 + ox, pos.field_1351 + oy, pos.field_1350 + oz, 0.0D, 0.01D, 0.0D);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static final class PendingTrail {
/*     */     final long createdAt;
/*     */     
/*     */     PendingTrail(long createdAt) {
/* 133 */       this.createdAt = createdAt;
/*     */     }
/*     */   }
/*     */   
/*     */   private static final class ActiveTrail {
/*     */     final int entityId;
/*     */     int ticksAlive;
/*     */     
/*     */     ActiveTrail(int entityId) {
/* 142 */       this.entityId = entityId;
/* 143 */       this.ticksAlive = 0;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\gocatch\client\GoCatchCurveBallTrailTracker.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */