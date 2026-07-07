/*    */ package com.atlas.common.fabric.vote.network;
/*    */ 
/*    */ import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
/*    */ import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
/*    */ import net.minecraft.class_3222;
/*    */ import net.minecraft.class_8710;
/*    */ 
/*    */ 
/*    */ public class VoteNetworkHelper
/*    */ {
/*    */   public static void registerPackets() {
/* 12 */     PayloadTypeRegistry.playC2S().register(VoteNetwork.REQUEST_CLAIM_TYPE, VoteNetwork.REQUEST_CLAIM_CODEC);
/*    */ 
/*    */     
/* 15 */     PayloadTypeRegistry.playS2C().register(VoteNetwork.OPEN_VOTE_SCREEN_TYPE, VoteNetwork.OPEN_VOTE_SCREEN_CODEC);
/*    */   }
/*    */   
/*    */   public static void registerClientPackets() {
/* 19 */     VoteNetworkHelperClient.registerClientReceivers();
/*    */   }
/*    */   
/*    */   public static void sendToServer(class_8710 payload) {
/* 23 */     VoteNetworkHelperClient.sendToServer(payload);
/*    */   }
/*    */   
/*    */   public static void sendToPlayer(class_3222 player, class_8710 payload) {
/* 27 */     ServerPlayNetworking.send(player, payload);
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\vote\network\VoteNetworkHelper.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */