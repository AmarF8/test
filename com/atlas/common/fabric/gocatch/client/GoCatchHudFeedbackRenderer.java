/*    */ package com.atlas.common.fabric.gocatch.client;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import net.fabricmc.api.EnvType;
/*    */ import net.fabricmc.api.Environment;
/*    */ import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
/*    */ import net.minecraft.class_310;
/*    */ import net.minecraft.class_327;
/*    */ import net.minecraft.class_332;
/*    */ import net.minecraft.class_9779;
/*    */ import org.joml.Quaternionf;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Environment(EnvType.CLIENT)
/*    */ public final class GoCatchHudFeedbackRenderer
/*    */ {
/*    */   public static void register() {
/* 24 */     HudRenderCallback.EVENT.register((context, tickCounter) -> render(context));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private static void render(class_332 context) {
/* 30 */     class_310 client = class_310.method_1551();
/* 31 */     if (client.field_1724 == null || client.field_1690.field_1842)
/*    */       return; 
/* 33 */     Collection<GoCatchThrowFeedbackTracker.ActiveFeedback> feedbackList = GoCatchThrowFeedbackTracker.getInstance().getActiveFeedback();
/* 34 */     if (feedbackList.isEmpty())
/*    */       return; 
/* 36 */     class_327 textRenderer = client.field_1772;
/* 37 */     int screenWidth = context.method_51421();
/* 38 */     int screenHeight = context.method_51443();
/*    */ 
/*    */     
/* 41 */     int centerX = screenWidth / 2;
/* 42 */     int centerY = (int)(screenHeight * 0.28D);
/*    */     
/* 44 */     int offsetIndex = 0;
/* 45 */     for (GoCatchThrowFeedbackTracker.ActiveFeedback feedback : feedbackList) {
/* 46 */       String text = feedback.getText();
/* 47 */       int textWidth = textRenderer.method_1727(text);
/*    */       
/* 49 */       float fadeAlpha = feedback.getFadeAlpha();
/* 50 */       int alpha = Math.max(1, (int)(fadeAlpha * 255.0F));
/* 51 */       int fillColor = alpha << 24 | feedback.getColor() & 0xFFFFFF;
/*    */       
/* 53 */       float popScale = feedback.getPopScale();
/*    */       
/* 55 */       float scale = 3.0F * popScale;
/*    */ 
/*    */       
/* 58 */       float yOffset = offsetIndex * 40.0F - (float)(feedback.getFloatOffset() * 20.0D);
/*    */       
/* 60 */       context.method_51448().method_22903();
/* 61 */       context.method_51448().method_46416(centerX, centerY + yOffset, 0.0F);
/* 62 */       context.method_51448().method_22905(scale, scale, 1.0F);
/*    */       
/* 64 */       float rotationDeg = feedback.getRotationDegrees();
/* 65 */       if (rotationDeg != 0.0F) {
/* 66 */         context.method_51448().method_22907((new Quaternionf()).rotateZ((float)Math.toRadians(rotationDeg)));
/*    */       }
/*    */ 
/*    */       
/* 70 */       context.method_51433(textRenderer, text, -textWidth / 2, 0, fillColor, true);
/* 71 */       context.method_51448().method_22909();
/* 72 */       offsetIndex++;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\gocatch\client\GoCatchHudFeedbackRenderer.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */