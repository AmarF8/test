/*     */ package com.atlas.common.fabric.safari.racing.client;
/*     */ 
/*     */ import com.atlas.common.fabric.safari.racing.packet.RacingTimingPacket;
/*     */ import java.util.List;
/*     */ import net.fabricmc.api.EnvType;
/*     */ import net.fabricmc.api.Environment;
/*     */ import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext;
/*     */ import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
/*     */ import net.minecraft.class_243;
/*     */ import net.minecraft.class_310;
/*     */ import net.minecraft.class_4587;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.joml.Quaternionf;
/*     */ import org.joml.Vector3f;
/*     */ import org.joml.Vector3fc;
/*     */ 
/*     */ @Environment(EnvType.CLIENT)
/*     */ public final class RacingWorldRenderer
/*     */   implements WorldRenderEvents.AfterEntities
/*     */ {
/*  21 */   private static final RacingWorldRenderer INSTANCE = new RacingWorldRenderer();
/*     */   private static final double MAX_RENDER_DISTANCE = 110.0D;
/*  23 */   private static final RacingRingGeoRenderer RING_RENDERER = new RacingRingGeoRenderer();
/*  24 */   private static final Vector3f MODEL_FORWARD = new Vector3f(0.0F, 0.0F, 1.0F);
/*  25 */   private static final class_243 DEFAULT_FACING = new class_243(0.0D, 0.0D, 1.0D);
/*     */ 
/*     */ 
/*     */   
/*     */   public static void register() {
/*  30 */     WorldRenderEvents.AFTER_ENTITIES.register(INSTANCE);
/*     */   }
/*     */ 
/*     */   
/*     */   public void afterEntities(WorldRenderContext context) {
/*  35 */     if (context == null || context.matrixStack() == null || context.camera() == null)
/*  36 */       return;  safeRender(() -> render(context));
/*     */   }
/*     */   
/*     */   private void render(@NotNull WorldRenderContext context) {
/*  40 */     RacingHudState state = RacingHudState.getInstance();
/*  41 */     if (!state.isActive() || state.getCheckpoints().isEmpty())
/*     */       return; 
/*  43 */     class_310 client = class_310.method_1551();
/*  44 */     if (client.field_1724 == null || client.field_1687 == null)
/*  45 */       return;  if (context.consumers() == null)
/*     */       return; 
/*  47 */     class_243 cameraPos = context.camera().method_19326();
/*  48 */     class_4587 matrices = context.matrixStack();
/*  49 */     List<RacingTimingPacket.CheckpointView> checkpoints = state.getCheckpoints();
/*     */     
/*  51 */     for (int i = 0; i < checkpoints.size(); i++) {
/*  52 */       RacingTimingPacket.CheckpointView checkpoint = checkpoints.get(i);
/*  53 */       class_243 center = new class_243(checkpoint.x(), checkpoint.y(), checkpoint.z());
/*  54 */       if (center.method_1022(client.field_1724.method_19538()) <= 110.0D) {
/*     */         
/*  56 */         boolean completed = (!state.isWaitingForStart() && i < state.getCurrentCheckpoint());
/*  57 */         RacingRingAnimatable animatable = completed ? RacingRingAnimatable.green() : RacingRingAnimatable.yellow();
/*  58 */         int packedLight = 15728880;
/*  59 */         class_243 facing = getFacingDirection(checkpoints, i);
/*     */         
/*  61 */         renderRing(matrices, cameraPos, checkpoint, animatable, context, 15728880, facing);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderRing(@NotNull class_4587 matrices, @NotNull class_243 cameraPos, @NotNull RacingTimingPacket.CheckpointView checkpoint, @NotNull RacingRingAnimatable animatable, @NotNull WorldRenderContext context, int packedLight, @NotNull class_243 facing) {
/*  72 */     double scale = checkpoint.radius() / 2.042185625D;
/*  73 */     matrices.method_22903();
/*  74 */     matrices.method_22904(checkpoint
/*  75 */         .x() - cameraPos.field_1352, checkpoint
/*  76 */         .y() - cameraPos.field_1351, checkpoint
/*  77 */         .z() - cameraPos.field_1350);
/*     */     
/*  79 */     orientRing(matrices, facing);
/*  80 */     matrices.method_22905((float)scale, (float)scale, (float)scale);
/*  81 */     RING_RENDERER.render(matrices, animatable, context.consumers(), null, null, packedLight, 0.0F);
/*  82 */     matrices.method_22909();
/*     */   }
/*     */   
/*     */   private static void orientRing(@NotNull class_4587 matrices, @NotNull class_243 facing) {
/*  86 */     Vector3f target = facing.method_46409();
/*  87 */     if (target.lengthSquared() < 1.0E-6F)
/*     */       return; 
/*  89 */     target.normalize();
/*  90 */     matrices.method_22907((new Quaternionf()).rotationTo((Vector3fc)new Vector3f((Vector3fc)MODEL_FORWARD), (Vector3fc)target));
/*     */   }
/*     */   @NotNull
/*     */   private static class_243 getFacingDirection(@NotNull List<RacingTimingPacket.CheckpointView> checkpoints, int index) {
/*  94 */     int checkpointCount = checkpoints.size();
/*  95 */     if (checkpointCount <= 1) {
/*  96 */       return DEFAULT_FACING;
/*     */     }
/*     */     
/*  99 */     class_243 current = toVec3d(checkpoints.get(index));
/* 100 */     if (checkpointCount == 2) {
/* 101 */       class_243 other = toVec3d(checkpoints.get((index + 1) % checkpointCount));
/* 102 */       class_243 class_2431 = other.method_1020(current);
/* 103 */       return (class_2431.method_1027() > 1.0E-6D) ? class_2431.method_1029() : DEFAULT_FACING;
/*     */     } 
/*     */     
/* 106 */     class_243 previous = toVec3d(checkpoints.get((index - 1 + checkpointCount) % checkpointCount));
/* 107 */     class_243 next = toVec3d(checkpoints.get((index + 1) % checkpointCount));
/*     */     
/* 109 */     class_243 direction = next.method_1020(previous);
/* 110 */     if (direction.method_1027() <= 1.0E-6D) {
/* 111 */       direction = next.method_1020(current);
/*     */     }
/* 113 */     if (direction.method_1027() <= 1.0E-6D) {
/* 114 */       direction = current.method_1020(previous);
/*     */     }
/*     */     
/* 117 */     return (direction.method_1027() > 1.0E-6D) ? direction.method_1029() : DEFAULT_FACING;
/*     */   }
/*     */   @NotNull
/*     */   private static class_243 toVec3d(@NotNull RacingTimingPacket.CheckpointView checkpoint) {
/* 121 */     return new class_243(checkpoint.x(), checkpoint.y(), checkpoint.z());
/*     */   }
/*     */   
/*     */   private static void safeRender(@NotNull Runnable runnable) {
/*     */     try {
/* 126 */       runnable.run();
/* 127 */     } catch (Exception exception) {}
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\safari\racing\client\RacingWorldRenderer.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */