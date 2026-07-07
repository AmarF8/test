/*     */ package com.atlas.common.fabric.block.statue.system;
/*     */ 
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.Map;
/*     */ import net.minecraft.class_2338;
/*     */ import net.minecraft.class_259;
/*     */ import net.minecraft.class_265;
/*     */ import org.jetbrains.annotations.NotNull;
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
/*     */ public final class Builder
/*     */ {
/*  95 */   private final Map<class_2338, class_265> pieces = new LinkedHashMap<>();
/*     */   @NotNull
/*     */   public Builder add(@NotNull class_2338 offset, @NotNull class_265 shape) {
/*  98 */     this.pieces.put(offset, shape);
/*  99 */     return this;
/*     */   }
/*     */   @NotNull
/*     */   public Builder addCube(@NotNull class_2338 offset) {
/* 103 */     return add(offset, class_259.method_1077());
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Builder addCentered(@NotNull class_2338 offset, double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
/* 109 */     return add(offset, class_259.method_1081(minX, minY, minZ, maxX, maxY, maxZ));
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Builder fill(int minX, int maxX, int minY, int maxY, int minZ, int maxZ) {
/* 115 */     for (int x = minX; x <= maxX; x++) {
/* 116 */       for (int y = minY; y <= maxY; y++) {
/* 117 */         for (int z = minZ; z <= maxZ; z++) {
/* 118 */           addCube(new class_2338(x, y, z));
/*     */         }
/*     */       } 
/*     */     } 
/* 122 */     return this;
/*     */   }
/*     */   @NotNull
/*     */   public StatueStructure build() {
/* 126 */     return new StatueStructure(this.pieces);
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\block\statue\system\StatueStructure$Builder.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */