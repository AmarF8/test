/*    */ package com.atlas.common.fabric.gocatch.client;
/*    */ 
/*    */ import net.fabricmc.api.EnvType;
/*    */ import net.fabricmc.api.Environment;
/*    */ import net.minecraft.class_238;
/*    */ import net.minecraft.class_243;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Environment(EnvType.CLIENT)
/*    */ public final class GoCatchCaptureRingGeometry
/*    */ {
/*    */   private static final double MIN_FRONT_OFFSET = 0.35D;
/*    */   private static final double FRONT_SURFACE_PADDING = 0.2D;
/*    */   private static final double MIN_RING_DISTANCE_FROM_CAMERA = 1.1D;
/*    */   private static final double EPSILON = 1.0E-6D;
/*    */   
/*    */   public static class_243 getPokemonCenter(class_243 basePos, double height) {
/* 23 */     if (basePos == null) return class_243.field_1353; 
/* 24 */     return basePos.method_1031(0.0D, height * 0.5D, 0.0D);
/*    */   }
/*    */   public static class_243 computeRingCenter(class_243 pokemonCenter, class_238 boundingBox, class_243 cameraPos) {
/*    */     double desiredOffset;
/* 28 */     if (pokemonCenter == null || cameraPos == null) {
/* 29 */       return (pokemonCenter == null) ? class_243.field_1353 : pokemonCenter;
/*    */     }
/*    */     
/* 32 */     class_243 toCamera = cameraPos.method_1020(pokemonCenter);
/* 33 */     double toCameraLength = toCamera.method_1033();
/* 34 */     if (toCameraLength < 1.0E-6D) return pokemonCenter;
/*    */     
/* 36 */     class_243 directionToCamera = toCamera.method_1029();
/*    */     
/* 38 */     if (boundingBox == null) {
/* 39 */       desiredOffset = 0.35D;
/*    */     } else {
/* 41 */       double halfX = Math.max(0.0D, (boundingBox.field_1320 - boundingBox.field_1323) * 0.5D);
/* 42 */       double halfY = Math.max(0.0D, (boundingBox.field_1325 - boundingBox.field_1322) * 0.5D);
/* 43 */       double halfZ = Math.max(0.0D, (boundingBox.field_1324 - boundingBox.field_1321) * 0.5D);
/*    */ 
/*    */       
/* 46 */       double projectedHalfDepth = Math.abs(directionToCamera.field_1352) * halfX + Math.abs(directionToCamera.field_1351) * halfY + Math.abs(directionToCamera.field_1350) * halfZ;
/* 47 */       desiredOffset = Math.max(0.35D, projectedHalfDepth + 0.2D);
/*    */     } 
/*    */     
/* 50 */     double offset = clampOffset(desiredOffset, toCameraLength);
/* 51 */     return pokemonCenter.method_1019(directionToCamera.method_1021(offset));
/*    */   }
/*    */   
/*    */   public static class_243 computeRingNormal(class_243 ringCenter, class_243 cameraPos) {
/* 55 */     if (ringCenter == null || cameraPos == null) return new class_243(0.0D, 0.0D, 1.0D); 
/* 56 */     class_243 toCamera = cameraPos.method_1020(ringCenter);
/* 57 */     if (toCamera.method_1033() < 1.0E-6D) return new class_243(0.0D, 0.0D, 1.0D); 
/* 58 */     return toCamera.method_1029();
/*    */   }
/*    */   
/*    */   public static boolean isCursorInsideRing(class_243 eyePos, class_243 lookVec, class_243 ringCenter, class_243 ringNormal, double ringRadius) {
/* 62 */     if (eyePos == null || lookVec == null || ringCenter == null || ringNormal == null || ringRadius <= 0.0D) {
/* 63 */       return false;
/*    */     }
/*    */     
/* 66 */     class_243 direction = (lookVec.method_1033() < 1.0E-6D) ? class_243.field_1353 : lookVec.method_1029();
/* 67 */     if (direction.method_1033() < 1.0E-6D) return false;
/*    */     
/* 69 */     double denominator = direction.method_1026(ringNormal);
/* 70 */     if (Math.abs(denominator) < 1.0E-6D) return false;
/*    */     
/* 72 */     double distanceAlongRay = ringCenter.method_1020(eyePos).method_1026(ringNormal) / denominator;
/* 73 */     if (distanceAlongRay <= 0.0D) return false;
/*    */     
/* 75 */     class_243 hitPoint = eyePos.method_1019(direction.method_1021(distanceAlongRay));
/* 76 */     return (hitPoint.method_1022(ringCenter) <= ringRadius);
/*    */   }
/*    */   
/*    */   private static double clampOffset(double desiredOffset, double distanceToCamera) {
/* 80 */     if (distanceToCamera < 1.0E-6D) return 0.0D; 
/* 81 */     double maxOffset = distanceToCamera - 1.1D;
/* 82 */     if (maxOffset <= 0.0D) return 0.0D; 
/* 83 */     return Math.min(desiredOffset, maxOffset);
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\gocatch\client\GoCatchCaptureRingGeometry.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */