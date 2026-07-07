/*    */ package com.atlas.common.fabric.mixin.content.client;
/*    */ 
/*    */ import com.atlas.common.fabric.safari.fishing.client.SafariFishingHudRenderer;
/*    */ import net.minecraft.class_332;
/*    */ import net.minecraft.class_338;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ 
/*    */ @Mixin({class_338.class})
/*    */ public final class ChatHudSafariFishingMixin
/*    */ {
/*    */   @Inject(method = {"render"}, at = {@At("TAIL")})
/*    */   private void atlas$renderSafariFishingAfterChatHud(class_332 context, int currentTick, int mouseX, int mouseY, boolean focused, CallbackInfo ci) {
/* 16 */     SafariFishingHudRenderer.renderOverlay(context);
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\mixin\content\client\ChatHudSafariFishingMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */