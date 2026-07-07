/*    */ package com.atlas.common.fabric.mixin.content;
/*    */ 
/*    */ import com.atlas.common.fabric.mega.LoadShowdown;
/*    */ import com.cobblemon.mod.common.battles.runner.graal.GraalShowdownUnbundler;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ 
/*    */ @Mixin(value = {GraalShowdownUnbundler.class}, remap = false)
/*    */ public class GraalShowdownUnbundlerMixin
/*    */ {
/*    */   @Inject(method = {"attemptUnbundle"}, at = {@At("TAIL")})
/*    */   private void beforeShowdownStarts(CallbackInfo ci) {
/* 15 */     (new LoadShowdown()).load();
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\mixin\content\GraalShowdownUnbundlerMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */