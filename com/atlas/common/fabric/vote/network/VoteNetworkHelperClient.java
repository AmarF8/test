/*    */ package com.atlas.common.fabric.vote.network;
/*    */ 
/*    */ import com.atlas.common.fabric.vote.data.VoteClientData;
/*    */ import com.atlas.common.fabric.vote.screen.VoteScreen;
/*    */ import net.fabricmc.api.EnvType;
/*    */ import net.fabricmc.api.Environment;
/*    */ import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
/*    */ import net.minecraft.class_310;
/*    */ import net.minecraft.class_437;
/*    */ import net.minecraft.class_8710;
/*    */ 
/*    */ @Environment(EnvType.CLIENT)
/*    */ public class VoteNetworkHelperClient {
/*    */   public static void sendToServer(class_8710 payload) {
/* 15 */     ClientPlayNetworking.send(payload);
/*    */   }
/*    */ 
/*    */   
/*    */   public static void registerClientReceivers() {
/* 20 */     ClientPlayNetworking.registerGlobalReceiver(VoteNetwork.OPEN_VOTE_SCREEN_TYPE, (payload, context) -> context.client().execute(()));
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\vote\network\VoteNetworkHelperClient.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */