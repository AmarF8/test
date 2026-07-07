/*     */ package com.atlas.common.fabric.gocatch.client;
/*     */ 
/*     */ import com.atlas.common.fabric.gocatch.GoCatchConfig;
/*     */ import com.atlas.common.fabric.gocatch.GoCatchThrowQuality;
/*     */ import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
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
/*     */ import net.minecraft.class_310;
/*     */ import net.minecraft.class_4587;
/*     */ import net.minecraft.class_757;
/*     */ import org.joml.Matrix4f;
/*     */ import org.joml.Quaternionf;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Environment(EnvType.CLIENT)
/*     */ public final class GoCatchCaptureRingRenderer
/*     */   implements WorldRenderEvents.Last
/*     */ {
/*     */   private static final int RING_SEGMENTS = 64;
/*     */   private static final float RING_WIDTH = 0.06F;
/*     */   private static final float RING_ALPHA = 0.85F;
/*  36 */   private static final GoCatchCaptureRingRenderer INSTANCE = new GoCatchCaptureRingRenderer();
/*     */ 
/*     */ 
/*     */   
/*     */   public static void register() {
/*  41 */     WorldRenderEvents.LAST.register(INSTANCE);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onLast(WorldRenderContext context) {
/*  46 */     if (context == null || context.matrixStack() == null || context.camera() == null)
/*     */       return; 
/*  48 */     GoCatchConfig config = GoCatchConfig.getInstance();
/*  49 */     if (!config.enabled)
/*     */       return; 
/*  51 */     safeRender(() -> renderTargetRing(context, config));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderTargetRing(WorldRenderContext context, GoCatchConfig config) {
/*  59 */     class_310 client = class_310.method_1551();
/*  60 */     if (client.field_1687 == null)
/*     */       return; 
/*  62 */     GoCatchTargetingTracker tracker = GoCatchTargetingTracker.getInstance();
/*  63 */     PokemonEntity target = tracker.getTargetedPokemon();
/*  64 */     if (target == null || !target.method_5805())
/*     */       return; 
/*  66 */     double ringSize = tracker.getCurrentRingSize();
/*  67 */     GoCatchThrowQuality quality = tracker.getCurrentThrowQuality();
/*  68 */     int color = (quality == GoCatchThrowQuality.NONE) ? 16777215 : quality.getColor(config);
/*     */     
/*  70 */     float r = (color >> 16 & 0xFF) / 255.0F;
/*  71 */     float g = (color >> 8 & 0xFF) / 255.0F;
/*  72 */     float b = (color & 0xFF) / 255.0F;
/*     */     
/*  74 */     class_243 cameraPos = context.camera().method_19326();
/*  75 */     class_243 pokemonCenter = GoCatchCaptureRingGeometry.getPokemonCenter(target.method_19538(), target.method_17682());
/*  76 */     class_243 ringCenter = GoCatchCaptureRingGeometry.computeRingCenter(pokemonCenter, target.method_5829(), cameraPos);
/*     */     
/*  78 */     class_4587 matrices = context.matrixStack();
/*  79 */     matrices.method_22903();
/*  80 */     matrices.method_22904(ringCenter.field_1352 - cameraPos.field_1352, ringCenter.field_1351 - cameraPos.field_1351, ringCenter.field_1350 - cameraPos.field_1350);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  86 */     double innerRadius = Math.max(0.05D, ringSize - 0.05999999865889549D);
/*  87 */     renderRingAtOrigin(matrices, ringSize, innerRadius, r, g, b, 0.85F, context.camera().method_23767());
/*     */     
/*  89 */     matrices.method_22909();
/*     */   }
/*     */ 
/*     */   
/*     */   private void renderRingAtOrigin(class_4587 matrices, double outerRadius, double innerRadius, float r, float g, float b, float a, Quaternionf cameraRotation) {
/*  94 */     if (outerRadius <= 0.0D || innerRadius < 0.0D || cameraRotation == null)
/*     */       return; 
/*  96 */     RenderSystem.enableBlend();
/*  97 */     RenderSystem.defaultBlendFunc();
/*  98 */     RenderSystem.disableCull();
/*  99 */     RenderSystem.setShader(class_757::method_34540);
/* 100 */     RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/* 102 */     matrices.method_22903();
/* 103 */     matrices.method_22907(cameraRotation);
/*     */     
/* 105 */     Matrix4f matrix = matrices.method_23760().method_23761();
/*     */ 
/*     */ 
/*     */     
/* 109 */     RenderSystem.disableDepthTest();
/* 110 */     RenderSystem.depthMask(false);
/* 111 */     renderRingGeometry(matrix, outerRadius, innerRadius, r, g, b, a);
/*     */     
/* 113 */     RenderSystem.enableDepthTest();
/* 114 */     renderRingGeometry(matrix, outerRadius, innerRadius, r, g, b, a);
/*     */     
/* 116 */     matrices.method_22909();
/*     */     
/* 118 */     RenderSystem.enableCull();
/* 119 */     RenderSystem.depthMask(true);
/* 120 */     RenderSystem.disableBlend();
/*     */   }
/*     */ 
/*     */   
/*     */   private void renderRingGeometry(Matrix4f matrix, double outerRadius, double innerRadius, float r, float g, float b, float a) {
/* 125 */     class_289 tessellator = class_289.method_1348();
/* 126 */     class_287 buffer = tessellator.method_60827(class_293.class_5596.field_27380, class_290.field_1576);
/*     */     
/* 128 */     float outerR = (float)outerRadius;
/* 129 */     float innerR = (float)innerRadius;
/*     */     
/* 131 */     for (int i = 0; i <= 64; i++) {
/* 132 */       double angle = 6.283185307179586D * i / 64.0D;
/* 133 */       double cos = Math.cos(angle);
/* 134 */       double sin = Math.sin(angle);
/*     */       
/* 136 */       float outerX = (float)(outerR * cos);
/* 137 */       float outerY = (float)(outerR * sin);
/* 138 */       float innerX = (float)(innerR * cos);
/* 139 */       float innerY = (float)(innerR * sin);
/*     */       
/* 141 */       buffer.method_22918(matrix, outerX, outerY, 0.0F).method_22915(r, g, b, a);
/* 142 */       buffer.method_22918(matrix, innerX, innerY, 0.0F).method_22915(r, g, b, a);
/*     */     } 
/*     */     
/* 145 */     class_286.method_43433(buffer.method_60800());
/*     */   }
/*     */   
/*     */   private static void safeRender(Runnable action) {
/*     */     try {
/* 150 */       action.run();
/* 151 */     } catch (Exception exception) {}
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\gocatch\client\GoCatchCaptureRingRenderer.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */