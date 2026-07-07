/*    */ package com.atlas.common.fabric.cosmetic;
/*    */ 
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class BedrockCosmeticItemRenderingOptions
/*    */ {
/*    */   @Nullable
/*    */   private final float[] translation;
/*    */   @Nullable
/*    */   private final float[] rotation;
/*    */   @Nullable
/*    */   private final float[] scale;
/*    */   private final boolean runAnimation;
/*    */   
/*    */   public BedrockCosmeticItemRenderingOptions(@Nullable float[] translation, @Nullable float[] rotation, @Nullable float[] scale, boolean runAnimation) {
/* 21 */     this.translation = translation;
/* 22 */     this.rotation = rotation;
/* 23 */     this.scale = scale;
/* 24 */     this.runAnimation = runAnimation;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public BedrockCosmeticItemRenderingOptions() {
/* 35 */     this(new float[] { 0.0F, 0.0F, 0.0F }, new float[] { 0.0F, 0.0F, 0.0F }, new float[] { 1.0F, 1.0F, 1.0F }, false);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public BedrockCosmeticItemRenderingOptions(@Nullable float[] translation, @Nullable float[] rotation, @Nullable float[] scale) {
/* 50 */     this(translation, rotation, scale, false);
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public float[] getTranslation() {
/* 55 */     return this.translation;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public float[] getRotation() {
/* 60 */     return this.rotation;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public float[] getScale() {
/* 65 */     return this.scale;
/*    */   }
/*    */   
/*    */   public boolean isRunAnimation() {
/* 69 */     return this.runAnimation;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 75 */     return "BedrockCosmeticItemRenderingOptions{translation=" + arrayToString(this.translation) + ", rotation=" + 
/* 76 */       arrayToString(this.rotation) + ", scale=" + 
/* 77 */       arrayToString(this.scale) + ", runAnimation=" + this.runAnimation + "}";
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private static String arrayToString(@Nullable float[] array) {
/* 83 */     if (array == null) {
/* 84 */       return "null";
/*    */     }
/* 86 */     StringBuilder sb = new StringBuilder("[");
/* 87 */     for (int i = 0; i < array.length; i++) {
/* 88 */       if (i > 0) sb.append(", "); 
/* 89 */       sb.append(array[i]);
/*    */     } 
/* 91 */     sb.append("]");
/* 92 */     return sb.toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\cosmetic\BedrockCosmeticItemRenderingOptions.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */