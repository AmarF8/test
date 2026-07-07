/*     */ package com.atlas.common.fabric.rustlingspots.particle;
/*     */ 
/*     */ import com.atlas.common.fabric.rustlingspots.RustlingSpotFamily;
/*     */ import net.fabricmc.api.EnvType;
/*     */ import net.fabricmc.api.Environment;
/*     */ import net.minecraft.class_1922;
/*     */ import net.minecraft.class_2338;
/*     */ import net.minecraft.class_2350;
/*     */ import net.minecraft.class_2394;
/*     */ import net.minecraft.class_2398;
/*     */ import net.minecraft.class_2400;
/*     */ import net.minecraft.class_243;
/*     */ import net.minecraft.class_310;
/*     */ import net.minecraft.class_3532;
/*     */ import net.minecraft.class_5819;
/*     */ import net.minecraft.class_638;
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
/*     */ @Environment(EnvType.CLIENT)
/*     */ public final class RustlingParticles
/*     */ {
/*     */   public static void spawn(RustlingSpotFamily family, class_2338 pos, boolean shiny) {
/*  33 */     class_638 level = (class_310.method_1551()).field_1687;
/*  34 */     if (level == null)
/*     */       return; 
/*  36 */     class_5819 random = level.method_8409();
/*     */     
/*  38 */     double collisionTop = level.method_8320(pos).method_26220((class_1922)level, pos).method_1105(class_2350.class_2351.field_11052);
/*  39 */     double visualTop = level.method_8320(pos).method_26218((class_1922)level, pos).method_1105(class_2350.class_2351.field_11052);
/*  40 */     double topHeight = Math.max(collisionTop, visualTop);
/*  41 */     if (topHeight < 0.01D) topHeight = 1.0D;
/*     */     
/*  43 */     class_243 topCenter = new class_243(pos.method_10263() + 0.5D, pos.method_10264() + topHeight + 0.02D, pos.method_10260() + 0.5D);
/*  44 */     class_243 spotCenter = new class_243(pos.method_10263() + 0.5D, pos.method_10264() + 0.02D, pos.method_10260() + 0.5D);
/*     */     
/*  46 */     switch (family) { default: throw new MatchException(null, null);
/*     */       case GRASS: 
/*     */       case LEAVES: 
/*     */       case WATER: 
/*     */       case CAVE: 
/*     */       case SAND: 
/*     */       case SNOW: 
/*     */       case NETHERFLAMME: 
/*     */       case SOULFLAME: 
/*  55 */       case FLYING: break; }  class_2400 class_2400 = RustlingParticleTypes.FLYING_BURST;
/*     */ 
/*     */     
/*  58 */     LeafyBurstProfile profile = new LeafyBurstProfile((class_2394)class_2400, 22, 28, 10, 15, 0.4F, 0.6F, 0.12F, 0.06F, 0.035F, 0.018F, 0.12F, 0.05F);
/*     */ 
/*     */     
/*  61 */     emitLeafyBurst(level, random, spotCenter, profile);
/*     */     
/*  63 */     if (family == RustlingSpotFamily.FLYING) {
/*  64 */       emitFlyingAccent(level, random, topCenter);
/*     */     }
/*  66 */     if (shiny)
/*  67 */       emitShinySparkles(level, random, spotCenter); 
/*     */   }
/*     */   private static final class LeafyBurstProfile extends Record { private final class_2394 primary; private final int phaseOneMin; private final int phaseOneMax; private final int phaseTwoMin; private final int phaseTwoMax; private final float radiusMin; private final float radiusMax; private final float upwardBase; private final float upwardJitter; private final float horizontalBase; private final float horizontalJitter; private final float yOffsetBase; private final float yOffsetJitter;
/*     */     
/*  71 */     private LeafyBurstProfile(class_2394 primary, int phaseOneMin, int phaseOneMax, int phaseTwoMin, int phaseTwoMax, float radiusMin, float radiusMax, float upwardBase, float upwardJitter, float horizontalBase, float horizontalJitter, float yOffsetBase, float yOffsetJitter) { this.primary = primary; this.phaseOneMin = phaseOneMin; this.phaseOneMax = phaseOneMax; this.phaseTwoMin = phaseTwoMin; this.phaseTwoMax = phaseTwoMax; this.radiusMin = radiusMin; this.radiusMax = radiusMax; this.upwardBase = upwardBase; this.upwardJitter = upwardJitter; this.horizontalBase = horizontalBase; this.horizontalJitter = horizontalJitter; this.yOffsetBase = yOffsetBase; this.yOffsetJitter = yOffsetJitter; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/rustlingspots/particle/RustlingParticles$LeafyBurstProfile;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #71	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*  71 */       //   0	7	0	this	Lcom/atlas/common/fabric/rustlingspots/particle/RustlingParticles$LeafyBurstProfile; } public class_2394 primary() { return this.primary; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/rustlingspots/particle/RustlingParticles$LeafyBurstProfile;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #71	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/rustlingspots/particle/RustlingParticles$LeafyBurstProfile; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/rustlingspots/particle/RustlingParticles$LeafyBurstProfile;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #71	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lcom/atlas/common/fabric/rustlingspots/particle/RustlingParticles$LeafyBurstProfile;
/*  71 */       //   0	8	1	o	Ljava/lang/Object; } public int phaseOneMin() { return this.phaseOneMin; } public int phaseOneMax() { return this.phaseOneMax; } public int phaseTwoMin() { return this.phaseTwoMin; } public int phaseTwoMax() { return this.phaseTwoMax; } public float radiusMin() { return this.radiusMin; } public float radiusMax() { return this.radiusMax; } public float upwardBase() { return this.upwardBase; } public float upwardJitter() { return this.upwardJitter; } public float horizontalBase() { return this.horizontalBase; } public float horizontalJitter() { return this.horizontalJitter; } public float yOffsetBase() { return this.yOffsetBase; } public float yOffsetJitter() { return this.yOffsetJitter; }
/*     */      }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void emitLeafyBurst(class_638 level, class_5819 random, class_243 center, LeafyBurstProfile profile) {
/*  78 */     int firstCount = profile.phaseOneMin + random.method_43048(profile.phaseOneMax - profile.phaseOneMin + 1);
/*  79 */     int secondCount = profile.phaseTwoMin + random.method_43048(profile.phaseTwoMax - profile.phaseTwoMin + 1);
/*  80 */     emitLeafyBurstPhase(level, random, center, profile, firstCount);
/*  81 */     emitLeafyBurstPhase(level, random, center, profile, secondCount);
/*     */   }
/*     */   
/*     */   private static void emitLeafyBurstPhase(class_638 level, class_5819 random, class_243 center, LeafyBurstProfile profile, int count) {
/*  85 */     for (int i = 0; i < count; i++) {
/*  86 */       double angle = random.method_43058() * Math.PI * 2.0D;
/*  87 */       double radius = class_3532.method_16436(random.method_43058(), profile.radiusMin, profile.radiusMax);
/*  88 */       double x = center.field_1352 + Math.cos(angle) * radius;
/*  89 */       double z = center.field_1350 + Math.sin(angle) * radius;
/*  90 */       double y = center.field_1351 + profile.yOffsetBase + random.method_43058() * profile.yOffsetJitter;
/*     */       
/*  92 */       double outward = profile.horizontalBase + random.method_43058() * profile.horizontalJitter;
/*  93 */       double velX = Math.cos(angle) * outward + (random.method_43058() - 0.5D) * 0.01D;
/*  94 */       double velZ = Math.sin(angle) * outward + (random.method_43058() - 0.5D) * 0.01D;
/*  95 */       double velY = profile.upwardBase + random.method_43058() * profile.upwardJitter;
/*     */       
/*  97 */       level.method_8406(profile.primary, x, y, z, velX, velY, velZ);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void emitFlyingAccent(class_638 level, class_5819 random, class_243 center) {
/* 102 */     for (int i = 0; i < 14; i++) {
/* 103 */       double angle = random.method_43058() * Math.PI * 2.0D;
/* 104 */       double radius = class_3532.method_16436(random.method_43058(), 0.14D, 0.32D);
/* 105 */       double x = center.field_1352 + Math.cos(angle) * radius;
/* 106 */       double z = center.field_1350 + Math.sin(angle) * radius;
/* 107 */       level.method_8406((class_2394)class_2398.field_11204, x, center.field_1351 + 0.02D, z, 0.0D, 0.018D, 0.0D);
/*     */     } 
/*     */   }
/*     */   private static void emitShinySparkles(class_638 level, class_5819 random, class_243 center) {
/*     */     int i;
/* 112 */     for (i = 0; i < 4; i++) {
/* 113 */       double angle = random.method_43058() * Math.PI * 2.0D;
/* 114 */       double radius = class_3532.method_16436(random.method_43058(), 0.08D, 0.44D);
/* 115 */       double x = center.field_1352 + Math.cos(angle) * radius;
/* 116 */       double z = center.field_1350 + Math.sin(angle) * radius;
/* 117 */       double y = center.field_1351 + 0.1D + random.method_43058() * 0.55D;
/* 118 */       double velX = (random.method_43058() - 0.5D) * 0.004D;
/* 119 */       double velZ = (random.method_43058() - 0.5D) * 0.004D;
/* 120 */       double velY = 0.005D + random.method_43058() * 0.01D;
/* 121 */       level.method_8406((class_2394)RustlingParticleTypes.SHINY_SPARKLE_ONE, x, y, z, velX, velY, velZ);
/*     */     } 
/*     */     
/* 124 */     for (i = 0; i < 2; i++) {
/* 125 */       double angle = random.method_43058() * Math.PI * 2.0D;
/* 126 */       double radius = class_3532.method_16436(random.method_43058(), 0.06D, 0.32D);
/* 127 */       double x = center.field_1352 + Math.cos(angle) * radius;
/* 128 */       double z = center.field_1350 + Math.sin(angle) * radius;
/* 129 */       double y = center.field_1351 + 0.16D + random.method_43058() * 0.42D;
/* 130 */       double velX = (random.method_43058() - 0.5D) * 0.003D;
/* 131 */       double velZ = (random.method_43058() - 0.5D) * 0.003D;
/* 132 */       double velY = 0.004D + random.method_43058() * 0.008D;
/* 133 */       level.method_8406((class_2394)RustlingParticleTypes.SHINY_SPARKLE_TWO, x, y, z, velX, velY, velZ);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\rustlingspots\particle\RustlingParticles.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */