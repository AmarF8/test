/*     */ package com.atlas.common.fabric.battletower.renderer;
/*     */ 
/*     */ import net.minecraft.class_4588;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class TintedVertexConsumer
/*     */   implements class_4588
/*     */ {
/*     */   private final class_4588 delegate;
/*     */   private final float tintR;
/*     */   private final float tintG;
/*     */   private final float tintB;
/*     */   private final float tintA;
/*     */   
/*     */   TintedVertexConsumer(class_4588 delegate, float r, float g, float b, float a) {
/* 187 */     this.delegate = delegate;
/* 188 */     this.tintR = r;
/* 189 */     this.tintG = g;
/* 190 */     this.tintB = b;
/* 191 */     this.tintA = a;
/*     */   }
/*     */ 
/*     */   
/*     */   public class_4588 method_22912(float x, float y, float z) {
/* 196 */     this.delegate.method_22912(x, y, z);
/* 197 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public class_4588 method_1336(int r, int g, int b, int a) {
/* 202 */     float fr = r / 255.0F;
/* 203 */     float fg = g / 255.0F;
/* 204 */     float fb = b / 255.0F;
/*     */     
/* 206 */     float strength = 0.85F;
/* 207 */     float nr = Math.clamp(fr * (1.0F - strength) + this.tintR * strength, 0.0F, 1.0F);
/* 208 */     float ng = Math.clamp(fg * (1.0F - strength) + this.tintG * strength, 0.0F, 1.0F);
/* 209 */     float nb = Math.clamp(fb * (1.0F - strength) + this.tintB * strength, 0.0F, 1.0F);
/*     */     
/* 211 */     this.delegate.method_1336((int)(nr * 255.0F), (int)(ng * 255.0F), (int)(nb * 255.0F), (int)(this.tintA * 255.0F));
/* 212 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public class_4588 method_22913(float u, float v) {
/* 217 */     this.delegate.method_22913(u, v);
/* 218 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public class_4588 method_60796(int u, int v) {
/* 223 */     this.delegate.method_60796(u, v);
/* 224 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public class_4588 method_22921(int u, int v) {
/* 229 */     this.delegate.method_22921(u, v);
/* 230 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public class_4588 method_22914(float x, float y, float z) {
/* 235 */     this.delegate.method_22914(x, y, z);
/* 236 */     return this;
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\battletower\renderer\BattleTowerTrainerRenderer$TintedVertexConsumer.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */