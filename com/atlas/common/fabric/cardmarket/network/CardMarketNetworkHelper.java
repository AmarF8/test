/*    */ package com.atlas.common.fabric.cardmarket.network;
/*    */ 
/*    */ import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
/*    */ import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
/*    */ import net.minecraft.class_3222;
/*    */ import net.minecraft.class_8710;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class CardMarketNetworkHelper
/*    */ {
/*    */   public static void registerPackets() {
/* 14 */     PayloadTypeRegistry.playC2S().register(CardMarketNetwork.REQUEST_MARKET_TYPE, CardMarketNetwork.REQUEST_MARKET_CODEC);
/* 15 */     PayloadTypeRegistry.playC2S().register(CardMarketNetwork.LIST_CARD_TYPE, CardMarketNetwork.LIST_CARD_CODEC);
/* 16 */     PayloadTypeRegistry.playC2S().register(CardMarketNetwork.BUY_TYPE, CardMarketNetwork.BUY_CODEC);
/* 17 */     PayloadTypeRegistry.playC2S().register(CardMarketNetwork.DELIST_TYPE, CardMarketNetwork.DELIST_CODEC);
/*    */     
/* 19 */     PayloadTypeRegistry.playS2C().register(CardMarketNetwork.OPEN_MARKET_TYPE, CardMarketNetwork.OPEN_MARKET_CODEC);
/* 20 */     PayloadTypeRegistry.playS2C().register(CardMarketNetwork.SYNC_MARKET_TYPE, CardMarketNetwork.SYNC_MARKET_CODEC);
/* 21 */     PayloadTypeRegistry.playS2C().register(CardMarketNetwork.RESULT_TYPE, CardMarketNetwork.RESULT_CODEC);
/*    */   }
/*    */   
/*    */   public static void registerClientPackets() {
/* 25 */     CardMarketNetworkHelperClient.registerClientReceivers();
/*    */   }
/*    */   
/*    */   public static void sendToServer(class_8710 payload) {
/* 29 */     CardMarketNetworkHelperClient.sendToServer(payload);
/*    */   }
/*    */   
/*    */   public static void sendToPlayer(class_3222 player, class_8710 payload) {
/* 33 */     ServerPlayNetworking.send(player, payload);
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\cardmarket\network\CardMarketNetworkHelper.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */