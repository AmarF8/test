/*     */ package com.atlas.common.fabric.morph.runtime.client;
/*     */ 
/*     */ import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
/*     */ import net.fabricmc.api.EnvType;
/*     */ import net.fabricmc.api.Environment;
/*     */ import net.minecraft.class_1297;
/*     */ import net.minecraft.class_1309;
/*     */ import net.minecraft.class_1657;
/*     */ import net.minecraft.class_238;
/*     */ import net.minecraft.class_239;
/*     */ import net.minecraft.class_243;
/*     */ import net.minecraft.class_310;
/*     */ import net.minecraft.class_3959;
/*     */ import net.minecraft.class_3965;
/*     */ import net.minecraft.class_3966;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ @Environment(EnvType.CLIENT)
/*     */ public final class MorphAimTracker
/*     */ {
/*     */   public static final double MOVE_RANGE = 18.0D;
/*     */   private static final double GROUND_TRACE_UP = 10.0D;
/*     */   private static final double GROUND_TRACE_DOWN = 24.0D;
/*     */   private static final double SURFACE_OFFSET = 0.05D;
/*     */   private static final double RETICLE_RADIUS = 1.7D;
/*  28 */   private static final MorphAimTracker INSTANCE = new MorphAimTracker();
/*     */   
/*     */   private boolean active;
/*  31 */   private class_243 aimDirection = new class_243(0.0D, 0.0D, 1.0D);
/*  32 */   private class_243 targetPos = class_243.field_1353;
/*  33 */   private int targetEntityId = -1;
/*     */ 
/*     */ 
/*     */   
/*     */   public static MorphAimTracker get() {
/*  38 */     return INSTANCE;
/*     */   }
/*     */   
/*     */   public void update(@NotNull class_310 client, @NotNull class_1657 player) {
/*  42 */     if (client.field_1687 == null) {
/*  43 */       clear();
/*     */       
/*     */       return;
/*     */     } 
/*  47 */     PokemonEntity mount = MorphedPlayerRegistry.get().getMount(player.method_5667());
/*  48 */     class_1297 cameraEntity = (client.method_1560() != null) ? client.method_1560() : (class_1297)player;
/*  49 */     class_243 basePos = (mount != null) ? mount.method_19538() : player.method_19538();
/*     */ 
/*     */     
/*  52 */     class_243 origin = (mount != null) ? new class_243(mount.method_23317(), mount.method_23318() + mount.method_5751(), mount.method_23321()) : player.method_33571();
/*  53 */     class_243 look = cameraEntity.method_5828(1.0F);
/*  54 */     if (look.method_1027() < 1.0E-6D) {
/*  55 */       look = player.method_5828(1.0F);
/*     */     }
/*  57 */     if (look.method_1027() < 1.0E-6D) {
/*  58 */       look = new class_243(0.0D, 0.0D, 1.0D);
/*     */     }
/*  60 */     look = look.method_1029();
/*     */     
/*  62 */     class_243 candidate = resolveCrosshairTarget(client, player, mount, cameraEntity, basePos.field_1351, look);
/*  63 */     class_243 snappedTarget = snapToGround(client, player, mount, candidate, basePos.field_1351);
/*  64 */     class_1309 targetEntity = resolveTargetEntity(client, player, mount, cameraEntity, snappedTarget);
/*  65 */     class_243 direction = snappedTarget.method_1020(origin);
/*  66 */     if (direction.method_1027() < 1.0E-6D) {
/*  67 */       direction = look;
/*     */     } else {
/*  69 */       direction = direction.method_1029();
/*     */     } 
/*     */     
/*  72 */     this.aimDirection = direction;
/*  73 */     this.targetPos = snappedTarget;
/*  74 */     this.targetEntityId = (targetEntity != null) ? targetEntity.method_5628() : -1;
/*  75 */     this.active = true;
/*     */   }
/*     */   
/*     */   public void clear() {
/*  79 */     this.active = false;
/*  80 */     this.aimDirection = new class_243(0.0D, 0.0D, 1.0D);
/*  81 */     this.targetPos = class_243.field_1353;
/*  82 */     this.targetEntityId = -1;
/*     */   }
/*     */   
/*     */   public boolean isActive() {
/*  86 */     return this.active;
/*     */   }
/*     */   @NotNull
/*     */   public class_243 getTargetPos() {
/*  90 */     return this.targetPos;
/*     */   }
/*     */   @Nullable
/*     */   public class_243 getTargetPosIfActive() {
/*  94 */     return this.active ? this.targetPos : null;
/*     */   }
/*     */   
/*     */   public int getTargetEntityIdIfActive() {
/*  98 */     return this.active ? this.targetEntityId : -1;
/*     */   }
/*     */   @NotNull
/*     */   public class_243 getAimDirection(@NotNull class_1657 player) {
/* 102 */     if (this.active && this.aimDirection.method_1027() > 1.0E-6D) {
/* 103 */       return this.aimDirection;
/*     */     }
/*     */     
/* 106 */     class_243 look = player.method_5828(1.0F);
/* 107 */     if (look.method_1027() < 1.0E-6D) {
/* 108 */       look = new class_243(0.0D, 0.0D, 1.0D);
/*     */     }
/* 110 */     return look.method_1029();
/*     */   }
/*     */   
/*     */   public double getReticleRadius() {
/* 114 */     return 1.7D;
/*     */   }
/*     */ 
/*     */   
/*     */   private class_243 resolveCrosshairTarget(class_310 client, class_1657 player, @Nullable PokemonEntity mount, class_1297 cameraEntity, double fallbackGroundY, class_243 look) {
/* 119 */     class_239 crosshairTarget = client.field_1765;
/* 120 */     if (crosshairTarget instanceof class_3965) { class_3965 blockHit = (class_3965)crosshairTarget; if (blockHit.method_17783() != class_239.class_240.field_1333)
/* 121 */         return clampToRange(cameraEntity.method_5836(1.0F), blockHit.method_17784(), fallbackGroundY);  }
/*     */     
/* 123 */     if (crosshairTarget instanceof class_3966) { class_3966 entityHit = (class_3966)crosshairTarget; if (entityHit
/* 124 */         .method_17782() != null && entityHit
/* 125 */         .method_17782() != player && entityHit
/* 126 */         .method_17782() != mount) {
/* 127 */         return clampToRange(cameraEntity.method_5836(1.0F), entityHit.method_17782().method_19538(), fallbackGroundY);
/*     */       } }
/*     */     
/* 130 */     class_243 cameraPos = cameraEntity.method_5836(1.0F);
/* 131 */     class_243 end = cameraPos.method_1019(look.method_1021(18.0D));
/* 132 */     class_3965 class_3965 = client.field_1687.method_17742(new class_3959(cameraPos, end, class_3959.class_3960.field_17558, class_3959.class_242.field_1348, 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 137 */           (mount != null) ? (class_1297)mount : (class_1297)player));
/* 138 */     if (class_3965.method_17783() != class_239.class_240.field_1333) {
/* 139 */       return clampToRange(cameraPos, class_3965.method_17784(), fallbackGroundY);
/*     */     }
/*     */     
/* 142 */     class_243 planeHit = intersectHorizontalPlane(cameraPos, look, fallbackGroundY);
/* 143 */     if (planeHit != null) {
/* 144 */       return clampToRange(cameraPos, planeHit, fallbackGroundY);
/*     */     }
/* 146 */     return clampToRange(cameraPos, end, fallbackGroundY);
/*     */   }
/*     */   
/*     */   private class_243 snapToGround(class_310 client, class_1657 player, @Nullable PokemonEntity mount, class_243 candidate, double fallbackGroundY) {
/* 150 */     class_243 start = new class_243(candidate.field_1352, candidate.field_1351 + 10.0D, candidate.field_1350);
/* 151 */     class_243 end = new class_243(candidate.field_1352, candidate.field_1351 - 24.0D, candidate.field_1350);
/* 152 */     class_3965 class_3965 = client.field_1687.method_17742(new class_3959(start, end, class_3959.class_3960.field_17558, class_3959.class_242.field_1348, 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 157 */           (mount != null) ? (class_1297)mount : (class_1297)player));
/* 158 */     if (class_3965.method_17783() != class_239.class_240.field_1333) {
/* 159 */       return class_3965.method_17784().method_1031(0.0D, 0.05D, 0.0D);
/*     */     }
/* 161 */     return new class_243(candidate.field_1352, fallbackGroundY + 0.05D, candidate.field_1350);
/*     */   }
/*     */   
/*     */   private class_243 clampToRange(class_243 start, class_243 target, double fallbackGroundY) {
/* 165 */     class_243 delta = target.method_1020(start);
/* 166 */     if (delta.method_1027() <= 324.0D) {
/* 167 */       return target;
/*     */     }
/* 169 */     class_243 direction = delta.method_1029();
/* 170 */     class_243 clamped = start.method_1019(direction.method_1021(18.0D));
/* 171 */     return new class_243(clamped.field_1352, (target.field_1351 == fallbackGroundY) ? fallbackGroundY : clamped.field_1351, clamped.field_1350);
/*     */   }
/*     */   @Nullable
/*     */   private class_243 intersectHorizontalPlane(class_243 origin, class_243 direction, double planeY) {
/* 175 */     double dy = direction.field_1351;
/* 176 */     if (Math.abs(dy) < 1.0E-6D) {
/* 177 */       return null;
/*     */     }
/* 179 */     double t = (planeY - origin.field_1351) / dy;
/* 180 */     if (t <= 0.0D) {
/* 181 */       return null;
/*     */     }
/* 183 */     return origin.method_1019(direction.method_1021(t));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   private class_1309 resolveTargetEntity(class_310 client, class_1657 player, @Nullable PokemonEntity mount, class_1297 cameraEntity, class_243 snappedTarget) {
/* 193 */     if (client.field_1687 == null) {
/* 194 */       return null;
/*     */     }
/*     */     
/* 197 */     class_239 crosshairTarget = client.field_1765;
/* 198 */     if (crosshairTarget instanceof class_3966) { class_3966 entityHit = (class_3966)crosshairTarget;
/* 199 */       class_1297 class_12971 = entityHit.method_17782(); if (class_12971 instanceof class_1309) { class_1309 living = (class_1309)class_12971;
/* 200 */         if (isValidTarget(living, player, mount))
/* 201 */           return living;  }
/*     */        }
/*     */     
/* 204 */     class_238 searchBox = new class_238(snappedTarget.field_1352 - 1.7D, snappedTarget.field_1351 - 2.0D, snappedTarget.field_1350 - 1.7D, snappedTarget.field_1352 + 1.7D, snappedTarget.field_1351 + 3.0D, snappedTarget.field_1350 + 1.7D);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 213 */     class_1309 best = null;
/* 214 */     double bestScore = Double.MAX_VALUE;
/* 215 */     class_243 cameraPos = cameraEntity.method_5836(1.0F);
/* 216 */     for (class_1309 living : client.field_1687.method_8390(class_1309.class, searchBox, entity -> isValidTarget(entity, player, mount))) {
/*     */       
/* 218 */       double horizontalScore = horizontalDistanceSquared(living.method_19538(), snappedTarget);
/* 219 */       double cameraScore = living.method_5707(cameraPos);
/* 220 */       double score = horizontalScore * 1000.0D + cameraScore;
/* 221 */       if (score < bestScore) {
/* 222 */         bestScore = score;
/* 223 */         best = living;
/*     */       } 
/*     */     } 
/* 226 */     return best;
/*     */   }
/*     */   
/*     */   private boolean isValidTarget(class_1309 entity, class_1657 player, @Nullable PokemonEntity mount) {
/* 230 */     return (entity.method_5805() && entity != player && entity != mount);
/*     */   }
/*     */   
/*     */   private double horizontalDistanceSquared(class_243 a, class_243 b) {
/* 234 */     double dx = a.field_1352 - b.field_1352;
/* 235 */     double dz = a.field_1350 - b.field_1350;
/* 236 */     return dx * dx + dz * dz;
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\morph\runtime\client\MorphAimTracker.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */