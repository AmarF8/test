/*     */ package com.atlas.common.fabric.block.statue.system;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.Map;
/*     */ import net.minecraft.class_2338;
/*     */ import net.minecraft.class_2350;
/*     */ import net.minecraft.class_259;
/*     */ import net.minecraft.class_265;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public final class StatueStructure
/*     */ {
/*     */   private final Map<class_2338, class_265> localShapes;
/*     */   
/*     */   private StatueStructure(@NotNull Map<class_2338, class_265> localShapes) {
/*  17 */     this.localShapes = Map.copyOf(localShapes);
/*     */   }
/*     */   @NotNull
/*     */   public Collection<class_2338> getLocalOffsets() {
/*  21 */     return this.localShapes.keySet();
/*     */   }
/*     */   @NotNull
/*     */   public class_2338 rotateOffset(@NotNull class_2350 facing, @NotNull class_2338 localOffset) {
/*  25 */     switch (facing) { case field_11043: case field_11035: case field_11034: case field_11039:  }  return 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  30 */       localOffset;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public class_265 getRotatedShape(@NotNull class_2350 facing, @NotNull class_2338 localOffset) {
/*  35 */     class_265 localShape = this.localShapes.get(localOffset);
/*  36 */     if (localShape == null) {
/*  37 */       return class_259.method_1073();
/*     */     }
/*  39 */     return rotateShape(facing, localShape);
/*     */   }
/*     */   @NotNull
/*     */   private static class_265 rotateShape(@NotNull class_2350 facing, @NotNull class_265 shape) {
/*  43 */     if (facing == class_2350.field_11043) {
/*  44 */       return shape;
/*     */     }
/*     */     
/*  47 */     class_265[] rotated = { class_259.method_1073() };
/*  48 */     shape.method_1089((minX, minY, minZ, maxX, maxY, maxZ) -> {
/*     */           double rMinX;
/*     */           double rMaxX;
/*     */           double rMinZ;
/*     */           double rMaxZ;
/*     */           switch (facing) {
/*     */             case field_11035:
/*     */               rMinX = 1.0D - maxX;
/*     */               rMaxX = 1.0D - minX;
/*     */               rMinZ = 1.0D - maxZ;
/*     */               rMaxZ = 1.0D - minZ;
/*     */               break;
/*     */             
/*     */             case field_11034:
/*     */               rMinX = 1.0D - maxZ;
/*     */               rMaxX = 1.0D - minZ;
/*     */               rMinZ = minX;
/*     */               rMaxZ = maxX;
/*     */               break;
/*     */             
/*     */             case field_11039:
/*     */               rMinX = minZ;
/*     */               rMaxX = maxZ;
/*     */               rMinZ = 1.0D - maxX;
/*     */               rMaxZ = 1.0D - minX;
/*     */               break;
/*     */             default:
/*     */               rMinX = minX;
/*     */               rMaxX = maxX;
/*     */               rMinZ = minZ;
/*     */               rMaxZ = maxZ;
/*     */               break;
/*     */           } 
/*     */           rotated[0] = class_259.method_1084(rotated[0], class_259.method_1081(rMinX, minY, rMinZ, rMaxX, maxY, rMaxZ));
/*     */         });
/*  83 */     return rotated[0];
/*     */   }
/*     */   @NotNull
/*     */   public static Builder builder() {
/*  87 */     return new Builder();
/*     */   }
/*     */   @NotNull
/*     */   public static StatueStructure of(@NotNull Map<class_2338, class_265> localShapes) {
/*  91 */     return new StatueStructure(localShapes);
/*     */   }
/*     */   
/*     */   public static final class Builder {
/*  95 */     private final Map<class_2338, class_265> pieces = new LinkedHashMap<>();
/*     */     @NotNull
/*     */     public Builder add(@NotNull class_2338 offset, @NotNull class_265 shape) {
/*  98 */       this.pieces.put(offset, shape);
/*  99 */       return this;
/*     */     }
/*     */     @NotNull
/*     */     public Builder addCube(@NotNull class_2338 offset) {
/* 103 */       return add(offset, class_259.method_1077());
/*     */     }
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public Builder addCentered(@NotNull class_2338 offset, double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
/* 109 */       return add(offset, class_259.method_1081(minX, minY, minZ, maxX, maxY, maxZ));
/*     */     }
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public Builder fill(int minX, int maxX, int minY, int maxY, int minZ, int maxZ) {
/* 115 */       for (int x = minX; x <= maxX; x++) {
/* 116 */         for (int y = minY; y <= maxY; y++) {
/* 117 */           for (int z = minZ; z <= maxZ; z++) {
/* 118 */             addCube(new class_2338(x, y, z));
/*     */           }
/*     */         } 
/*     */       } 
/* 122 */       return this;
/*     */     }
/*     */     @NotNull
/*     */     public StatueStructure build() {
/* 126 */       return new StatueStructure(this.pieces);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\block\statue\system\StatueStructure.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */