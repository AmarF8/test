/*    */ package com.atlas.common.fabric.cardcollection.screen;
/*    */ 
/*    */ import net.minecraft.class_2960;
/*    */ import net.minecraft.class_332;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class CardMenuTextures
/*    */ {
/*    */   public static final int FRAME_W = 454;
/*    */   public static final int FRAME_H = 368;
/*    */   private static final String BASE = "textures/cardcollection/ui/card_menu/";
/*    */   
/*    */   @NotNull
/*    */   public static class_2960 tex(@NotNull String path) {
/* 21 */     return class_2960.method_60655("atlas", "textures/cardcollection/ui/card_menu/" + path + ".png");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void draw(@NotNull class_332 ctx, @NotNull String path, int x, int y, int w, int h) {
/* 30 */     CardTextures.drawFull(ctx, tex(path), x, y, w, h);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void drawButton(@NotNull class_332 ctx, @NotNull String path, boolean pressed, int x, int y, int w, int h) {
/* 40 */     draw(ctx, path + path, x, y, w, h);
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\cardcollection\screen\CardMenuTextures.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */