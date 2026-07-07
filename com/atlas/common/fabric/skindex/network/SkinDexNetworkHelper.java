/*    */ package com.atlas.common.fabric.skindex.network;
/*    */ 
/*    */ import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
/*    */ import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
/*    */ import net.minecraft.class_3222;
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
/*    */ public final class SkinDexNetworkHelper
/*    */ {
/*    */   public static void registerPackets() {
/* 21 */     PayloadTypeRegistry.playC2S().register(SkinDexNetwork.REQUEST_SKINDEX_DATA_TYPE, SkinDexNetwork.REQUEST_SKINDEX_DATA_CODEC);
/* 22 */     PayloadTypeRegistry.playC2S().register(SkinDexNetwork.CLAIM_SKINDEX_REWARD_TYPE, SkinDexNetwork.CLAIM_SKINDEX_REWARD_CODEC);
/*    */ 
/*    */     
/* 25 */     PayloadTypeRegistry.playS2C().register(SkinDexNetwork.OPEN_SKINDEX_TYPE, SkinDexNetwork.OPEN_SKINDEX_CODEC);
/* 26 */     PayloadTypeRegistry.playS2C().register(SkinDexNetwork.SYNC_SKINDEX_DATA_TYPE, SkinDexNetwork.SYNC_SKINDEX_DATA_CODEC);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void registerClientPackets() {
/* 33 */     SkinDexNetworkHelperClient.registerClientReceivers();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void sendToServer(class_8710 payload) {
/* 40 */     SkinDexNetworkHelperClient.sendToServer(payload);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void sendToPlayer(class_3222 player, class_8710 payload) {
/* 47 */     ServerPlayNetworking.send(player, payload);
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\skindex\network\SkinDexNetworkHelper.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */