/*    */ package com.atlas.common.fabric.hud.text.client;
/*    */ 
/*    */ import com.atlas.common.fabric.hud.HudRenderer;
/*    */ import com.atlas.common.fabric.hud.text.HudText;
/*    */ import java.util.Iterator;
/*    */ import net.fabricmc.api.EnvType;
/*    */ import net.fabricmc.api.Environment;
/*    */ import net.minecraft.class_2561;
/*    */ import net.minecraft.class_310;
/*    */ import net.minecraft.class_327;
/*    */ import net.minecraft.class_332;
/*    */ import net.minecraft.class_5348;
/*    */ import net.minecraft.class_5481;
/*    */ import net.minecraft.class_9779;
/*    */ import org.jetbrains.annotations.NotNull;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Environment(EnvType.CLIENT)
/*    */ public final class ClientHudText
/*    */   extends HudText
/*    */   implements HudRenderer
/*    */ {
/*    */   public ClientHudText(@NotNull String identifier, @NotNull class_2561 text, int xPercentage, int yPercentage, int x, int y, boolean shadow, boolean centered, int maximumWidth, int defaultTextColor, int backgroundColor) {
/* 39 */     super(identifier, text, xPercentage, yPercentage, x, y, shadow, centered, maximumWidth, defaultTextColor, backgroundColor);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(@NotNull class_332 drawContext, class_9779 tickCounter) {
/* 48 */     class_327 textRenderer = (class_310.method_1551()).field_1772;
/*    */     
/* 50 */     int x = Math.round(drawContext.method_51421() * this.xPercentage / 100.0F) + this.x;
/* 51 */     int y = Math.round(drawContext.method_51443() * this.yPercentage / 100.0F) + this.y;
/*    */ 
/*    */     
/* 54 */     int latestY = y;
/* 55 */     int latestMinimumX = Integer.MAX_VALUE;
/* 56 */     int latestMaximumWidth = 0;
/* 57 */     for (Iterator<class_5481> line = textRenderer.method_1728((class_5348)this.text, this.maximumWidth).iterator(); line.hasNext(); latestY += 9) {
/*    */       
/* 59 */       class_5481 lineText = line.next();
/* 60 */       int centeredX = this.centered ? (x - textRenderer.method_30880(lineText) / 2) : x;
/*    */ 
/*    */       
/* 63 */       drawContext.method_51430(textRenderer, lineText, centeredX, latestY, this.defaultTextColor, this.shadow);
/*    */ 
/*    */       
/* 66 */       int textWidth = textRenderer.method_30880(lineText);
/* 67 */       latestMaximumWidth = Math.max(latestMaximumWidth, textWidth);
/* 68 */       latestMinimumX = Math.min(latestMinimumX, centeredX);
/*    */     } 
/*    */     
/* 71 */     drawContext.method_25294(latestMinimumX - 2, y - 2, latestMinimumX + latestMaximumWidth + 2, latestY + 2, this.backgroundColor);
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\hud\text\client\ClientHudText.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */