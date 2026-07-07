/*     */ package com.atlas.common.fabric.morph.runtime.client;
/*     */ 
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import net.fabricmc.api.EnvType;
/*     */ import net.fabricmc.api.Environment;
/*     */ import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext;
/*     */ import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
/*     */ import net.minecraft.class_243;
/*     */ import net.minecraft.class_286;
/*     */ import net.minecraft.class_287;
/*     */ import net.minecraft.class_289;
/*     */ import net.minecraft.class_290;
/*     */ import net.minecraft.class_293;
/*     */ import net.minecraft.class_4587;
/*     */ import net.minecraft.class_757;
/*     */ import org.joml.Matrix4f;
/*     */ 
/*     */ @Environment(EnvType.CLIENT)
/*     */ public final class MorphAimRenderer
/*     */   implements WorldRenderEvents.Last {
/*  21 */   private static final MorphAimRenderer INSTANCE = new MorphAimRenderer();
/*     */   
/*     */   private static final int SEGMENTS = 96;
/*     */   
/*     */   private static final float RETICLE_R = 0.3F;
/*     */   
/*     */   private static final float RETICLE_G = 0.95F;
/*     */   private static final float RETICLE_B = 1.0F;
/*     */   private static final float OUTER_ALPHA = 0.8F;
/*     */   private static final float INNER_ALPHA = 0.48F;
/*     */   private static final float CENTER_ALPHA = 0.95F;
/*     */   private static final double OUTER_RING_THICKNESS = 0.08D;
/*     */   private static final double INNER_RING_RADIUS_SCALE = 0.38D;
/*     */   private static final double INNER_RING_THICKNESS = 0.05D;
/*     */   private static final double SPOKE_WIDTH = 0.045D;
/*     */   private static final double RENDER_Y_OFFSET = 0.03D;
/*     */   
/*     */   public static void register() {
/*  39 */     WorldRenderEvents.LAST.register(INSTANCE);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onLast(WorldRenderContext context) {
/*  44 */     if (context == null || context.matrixStack() == null || context.camera() == null)
/*     */       return; 
/*  46 */     MorphAimTracker tracker = MorphAimTracker.get();
/*  47 */     if (!tracker.isActive())
/*     */       return; 
/*  49 */     class_243 targetPos = tracker.getTargetPos();
/*  50 */     if (targetPos == null)
/*     */       return; 
/*  52 */     class_4587 matrices = context.matrixStack();
/*  53 */     class_243 cameraPos = context.camera().method_19326();
/*     */     
/*  55 */     matrices.method_22903();
/*  56 */     matrices.method_22904(targetPos.field_1352 - cameraPos.field_1352, targetPos.field_1351 - cameraPos.field_1351 + 0.03D, targetPos.field_1350 - cameraPos.field_1350);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  62 */     safeRender(() -> renderReticle(matrices, tracker.getReticleRadius()));
/*  63 */     matrices.method_22909();
/*     */   }
/*     */   
/*     */   private void renderReticle(class_4587 matrices, double radius) {
/*  67 */     RenderSystem.enableBlend();
/*  68 */     RenderSystem.defaultBlendFunc();
/*  69 */     RenderSystem.disableCull();
/*  70 */     RenderSystem.setShader(class_757::method_34540);
/*  71 */     RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/*  73 */     Matrix4f matrix = matrices.method_23760().method_23761();
/*     */     
/*  75 */     RenderSystem.disableDepthTest();
/*  76 */     RenderSystem.depthMask(false);
/*  77 */     drawReticleGeometry(matrix, radius);
/*     */     
/*  79 */     RenderSystem.enableDepthTest();
/*  80 */     drawReticleGeometry(matrix, radius);
/*     */     
/*  82 */     RenderSystem.enableCull();
/*  83 */     RenderSystem.depthMask(true);
/*  84 */     RenderSystem.disableBlend();
/*     */   }
/*     */   
/*     */   private void drawReticleGeometry(Matrix4f matrix, double radius) {
/*  88 */     renderRingBand(matrix, radius, Math.max(0.05D, radius - 0.08D), 0.8F);
/*  89 */     renderRingBand(matrix, radius * 0.38D, 
/*     */ 
/*     */         
/*  92 */         Math.max(0.04D, radius * 0.38D - 0.05D), 0.48F);
/*     */ 
/*     */ 
/*     */     
/*  96 */     renderSpoke(matrix, Math.toRadians(-90.0D), radius * 0.18D, radius * 0.92D, 0.045D, 0.48F);
/*  97 */     renderSpoke(matrix, Math.toRadians(30.0D), radius * 0.18D, radius * 0.92D, 0.045D, 0.48F);
/*  98 */     renderSpoke(matrix, Math.toRadians(150.0D), radius * 0.18D, radius * 0.92D, 0.045D, 0.48F);
/*     */     
/* 100 */     renderRingBand(matrix, radius * 0.08D, 0.0D, 0.95F);
/*     */   }
/*     */   
/*     */   private void renderRingBand(Matrix4f matrix, double outerRadius, double innerRadius, float alpha) {
/* 104 */     class_289 tessellator = class_289.method_1348();
/* 105 */     class_287 buffer = tessellator.method_60827(class_293.class_5596.field_27380, class_290.field_1576);
/*     */     
/* 107 */     float outer = (float)outerRadius;
/* 108 */     float inner = (float)innerRadius;
/*     */     
/* 110 */     for (int i = 0; i <= 96; i++) {
/* 111 */       double angle = 6.283185307179586D * i / 96.0D;
/* 112 */       float cos = (float)Math.cos(angle);
/* 113 */       float sin = (float)Math.sin(angle);
/*     */       
/* 115 */       buffer.method_22918(matrix, cos * outer, 0.0F, sin * outer).method_22915(0.3F, 0.95F, 1.0F, alpha);
/* 116 */       buffer.method_22918(matrix, cos * inner, 0.0F, sin * inner).method_22915(0.3F, 0.95F, 1.0F, alpha);
/*     */     } 
/*     */     
/* 119 */     class_286.method_43433(buffer.method_60800());
/*     */   }
/*     */   
/*     */   private void renderSpoke(Matrix4f matrix, double angle, double startRadius, double endRadius, double width, float alpha) {
/* 123 */     float startX = (float)(Math.cos(angle) * startRadius);
/* 124 */     float startZ = (float)(Math.sin(angle) * startRadius);
/* 125 */     float endX = (float)(Math.cos(angle) * endRadius);
/* 126 */     float endZ = (float)(Math.sin(angle) * endRadius);
/*     */     
/* 128 */     double dx = (endX - startX);
/* 129 */     double dz = (endZ - startZ);
/* 130 */     double length = Math.sqrt(dx * dx + dz * dz);
/* 131 */     if (length < 1.0E-6D)
/*     */       return; 
/* 133 */     float nx = (float)(-(dz / length) * width);
/* 134 */     float nz = (float)(dx / length * width);
/*     */     
/* 136 */     class_289 tessellator = class_289.method_1348();
/* 137 */     class_287 buffer = tessellator.method_60827(class_293.class_5596.field_27380, class_290.field_1576);
/* 138 */     buffer.method_22918(matrix, startX + nx, 0.0F, startZ + nz).method_22915(0.3F, 0.95F, 1.0F, alpha);
/* 139 */     buffer.method_22918(matrix, startX - nx, 0.0F, startZ - nz).method_22915(0.3F, 0.95F, 1.0F, alpha);
/* 140 */     buffer.method_22918(matrix, endX + nx, 0.0F, endZ + nz).method_22915(0.3F, 0.95F, 1.0F, alpha);
/* 141 */     buffer.method_22918(matrix, endX - nx, 0.0F, endZ - nz).method_22915(0.3F, 0.95F, 1.0F, alpha);
/* 142 */     class_286.method_43433(buffer.method_60800());
/*     */   }
/*     */   
/*     */   private static void safeRender(Runnable action) {
/*     */     try {
/* 147 */       action.run();
/* 148 */     } catch (Exception exception) {}
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\morph\runtime\client\MorphAimRenderer.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */