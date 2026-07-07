/*    */ package com.atlas.common.fabric.mixin.content;
/*    */ 
/*    */ import com.atlas.common.fabric.mega.Abilities;
/*    */ import com.atlas.common.fabric.mega.Conditions;
/*    */ import com.atlas.common.fabric.mega.HeldItems;
/*    */ import com.atlas.common.fabric.mega.Moves;
/*    */ import com.cobblemon.mod.common.api.data.DataRegistry;
/*    */ import com.cobblemon.mod.common.data.CobblemonDataProvider;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin(value = {CobblemonDataProvider.class}, remap = false)
/*    */ public class CobblemonDataProviderMixin
/*    */ {
/*    */   @Inject(method = {"registerDefaults"}, at = {@At(value = "INVOKE", target = "Lcom/cobblemon/mod/common/data/CobblemonDataProvider;register(Lcom/cobblemon/mod/common/api/data/DataRegistry;Z)Lcom/cobblemon/mod/common/api/data/DataRegistry;", ordinal = 0)})
/*    */   private void injectBeforeScriptsRegister(CallbackInfo ci) {
/* 25 */     CobblemonDataProvider.INSTANCE.register((DataRegistry)Moves.INSTANCE, false);
/* 26 */     CobblemonDataProvider.INSTANCE.register((DataRegistry)Abilities.INSTANCE, false);
/* 27 */     CobblemonDataProvider.INSTANCE.register((DataRegistry)Conditions.INSTANCE, false);
/* 28 */     CobblemonDataProvider.INSTANCE.register((DataRegistry)HeldItems.INSTANCE, false);
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\mixin\content\CobblemonDataProviderMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */