/*    */ package com.atlas.common.fabric.starterguide.network;
/*    */ 
/*    */ import com.atlas.common.fabric.starterguide.PlayerStarterGuideData;
/*    */ import net.fabricmc.api.EnvType;
/*    */ import net.fabricmc.api.Environment;
/*    */ import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
/*    */ import net.minecraft.class_8710;
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
/*    */ public final class StarterGuideNetworkHelperClient
/*    */ {
/*    */   public static void sendToServer(class_8710 payload) {
/* 23 */     ClientPlayNetworking.send(payload);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void registerClientReceivers() {
/* 31 */     ClientPlayNetworking.registerGlobalReceiver(StarterGuideNetwork.SYNC_GUIDE_TYPE, (payload, context) -> {
/*    */           StarterGuideNetwork.SyncGuidePayload data = payload;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */           
/*    */           context.client().execute(());
/*    */         });
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 44 */     ClientPlayNetworking.registerGlobalReceiver(StarterGuideNetwork.STEP_COMPLETE_TYPE, (payload, context) -> {
/*    */           StarterGuideNetwork.StepCompletePayload data = payload;
/*    */           context.client().execute(());
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\starterguide\network\StarterGuideNetworkHelperClient.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */