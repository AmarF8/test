/*    */ package com.atlas.common.fabric.mixin.compat.jade;
/*    */ 
/*    */ import com.atlas.common.fabric.starterguide.PlayerStarterGuideData;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import snownee.jade.overlay.OverlayRenderer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin(value = {OverlayRenderer.class}, remap = false)
/*    */ public abstract class JadeOverlayMixin
/*    */ {
/*    */   @Inject(method = {"renderOverlay"}, at = {@At("HEAD")}, cancellable = true, require = 0)
/*    */   private static void atlas$suppressForStarterGuide(CallbackInfo ci) {
/* 21 */     if (PlayerStarterGuideData.getInstance().shouldRender())
/* 22 */       ci.cancel(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\mixin\compat\jade\JadeOverlayMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */