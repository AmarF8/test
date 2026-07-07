/*    */ package com.atlas.common.fabric.mixin.content.client;
/*    */ 
/*    */ import com.atlas.common.fabric.block.statue.system.StatueStructureHelper;
/*    */ import net.minecraft.class_1937;
/*    */ import net.minecraft.class_2338;
/*    */ import net.minecraft.class_310;
/*    */ import net.minecraft.class_636;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.ModifyVariable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({class_636.class})
/*    */ public abstract class ClientPlayerInteractionManagerStatueMixin
/*    */ {
/*    */   @ModifyVariable(method = {"attackBlock"}, at = @At("HEAD"), argsOnly = true)
/*    */   private class_2338 atlas$normalizeAttackBlockPos(class_2338 pos) {
/* 20 */     return atlas$resolveMiningPos(pos);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @ModifyVariable(method = {"updateBlockBreakingProgress"}, at = @At("HEAD"), argsOnly = true)
/*    */   private class_2338 atlas$normalizeUpdateBreakingPos(class_2338 pos) {
/* 29 */     return atlas$resolveMiningPos(pos);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @ModifyVariable(method = {"breakBlock"}, at = @At("HEAD"), argsOnly = true)
/*    */   private class_2338 atlas$normalizeBreakBlockPos(class_2338 pos) {
/* 38 */     return atlas$resolveMiningPos(pos);
/*    */   }
/*    */   
/*    */   private class_2338 atlas$resolveMiningPos(class_2338 pos) {
/* 42 */     class_310 client = class_310.method_1551();
/* 43 */     if (client.field_1687 == null) {
/* 44 */       return pos;
/*    */     }
/*    */     
/* 47 */     return StatueStructureHelper.resolveMiningPos((class_1937)client.field_1687, pos);
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\mixin\content\client\ClientPlayerInteractionManagerStatueMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */