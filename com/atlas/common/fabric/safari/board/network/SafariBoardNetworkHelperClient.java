/*    */ package com.atlas.common.fabric.safari.board.network;
/*    */ 
/*    */ import com.atlas.common.fabric.safari.board.SafariBoardClientData;
/*    */ import com.atlas.common.fabric.safari.board.screen.SafariBoardScreen;
/*    */ import net.fabricmc.api.EnvType;
/*    */ import net.fabricmc.api.Environment;
/*    */ import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
/*    */ import net.minecraft.class_310;
/*    */ import net.minecraft.class_437;
/*    */ import net.minecraft.class_8710;
/*    */ 
/*    */ 
/*    */ @Environment(EnvType.CLIENT)
/*    */ public final class SafariBoardNetworkHelperClient
/*    */ {
/*    */   public static void sendToServer(class_8710 payload) {
/* 17 */     ClientPlayNetworking.send(payload);
/*    */   }
/*    */   
/*    */   public static void registerClientReceivers() {
/* 21 */     ClientPlayNetworking.registerGlobalReceiver(SafariBoardNetwork.OPEN_SCREEN_TYPE, (payload, context) -> context.client().execute(()));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 27 */     ClientPlayNetworking.registerGlobalReceiver(SafariBoardNetwork.SYNC_TYPE, (payload, context) -> context.client().execute(()));
/*    */ 
/*    */     
/* 30 */     ClientPlayNetworking.registerGlobalReceiver(SafariBoardNetwork.ACTION_RESULT_TYPE, (payload, context) -> context.client().execute(()));
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\safari\board\network\SafariBoardNetworkHelperClient.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */