/*    */ package com.atlas.common.fabric.auction.network;
/*    */ 
/*    */ import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
/*    */ import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
/*    */ import net.minecraft.class_3222;
/*    */ import net.minecraft.class_8710;
/*    */ 
/*    */ public class AuctionNetworkHelper
/*    */ {
/*    */   public static void registerPackets() {
/* 11 */     PayloadTypeRegistry.playC2S().register(AuctionNetwork.REQUEST_PAGE_TYPE, AuctionNetwork.REQUEST_PAGE_CODEC);
/* 12 */     PayloadTypeRegistry.playC2S().register(AuctionNetwork.REQUEST_BUY_TYPE, AuctionNetwork.REQUEST_BUY_CODEC);
/* 13 */     PayloadTypeRegistry.playC2S().register(AuctionNetwork.REQUEST_DETAIL_TYPE, AuctionNetwork.REQUEST_DETAIL_CODEC);
/* 14 */     PayloadTypeRegistry.playC2S().register(AuctionNetwork.REQUEST_BID_TYPE, AuctionNetwork.REQUEST_BID_CODEC);
/* 15 */     PayloadTypeRegistry.playC2S().register(AuctionNetwork.REQUEST_MY_AUCTIONS_TYPE, AuctionNetwork.REQUEST_MY_AUCTIONS_CODEC);
/* 16 */     PayloadTypeRegistry.playC2S().register(AuctionNetwork.REQUEST_HISTORY_TYPE, AuctionNetwork.REQUEST_HISTORY_CODEC);
/* 17 */     PayloadTypeRegistry.playC2S().register(AuctionNetwork.REQUEST_CREATE_TYPE, AuctionNetwork.REQUEST_CREATE_CODEC);
/* 18 */     PayloadTypeRegistry.playC2S().register(AuctionNetwork.REQUEST_CREATE_POKEMON_TYPE, AuctionNetwork.REQUEST_CREATE_POKEMON_CODEC);
/* 19 */     PayloadTypeRegistry.playC2S().register(AuctionNetwork.REQUEST_UNLIST_TYPE, AuctionNetwork.REQUEST_UNLIST_CODEC);
/* 20 */     PayloadTypeRegistry.playC2S().register(AuctionNetwork.REQUEST_INVENTORY_TYPE, AuctionNetwork.REQUEST_INVENTORY_CODEC);
/*    */ 
/*    */     
/* 23 */     PayloadTypeRegistry.playC2S().register(AuctionNetwork.REQUEST_RECENTLY_SOLD_TYPE, AuctionNetwork.REQUEST_RECENTLY_SOLD_CODEC);
/*    */ 
/*    */     
/* 26 */     PayloadTypeRegistry.playC2S().register(AuctionNetwork.REQUEST_PRICE_LOOKUP_KEYS_TYPE, AuctionNetwork.REQUEST_PRICE_LOOKUP_KEYS_CODEC);
/* 27 */     PayloadTypeRegistry.playC2S().register(AuctionNetwork.REQUEST_PRICE_LOOKUP_TYPE, AuctionNetwork.REQUEST_PRICE_LOOKUP_CODEC);
/*    */ 
/*    */     
/* 30 */     PayloadTypeRegistry.playS2C().register(AuctionNetwork.SYNC_PAGE_TYPE, AuctionNetwork.SYNC_PAGE_CODEC);
/* 31 */     PayloadTypeRegistry.playS2C().register(AuctionNetwork.OPEN_SCREEN_TYPE, AuctionNetwork.OPEN_SCREEN_CODEC);
/* 32 */     PayloadTypeRegistry.playS2C().register(AuctionNetwork.SYNC_BALANCE_TYPE, AuctionNetwork.SYNC_BALANCE_CODEC);
/* 33 */     PayloadTypeRegistry.playS2C().register(AuctionNetwork.SYNC_DETAIL_TYPE, AuctionNetwork.SYNC_DETAIL_CODEC);
/* 34 */     PayloadTypeRegistry.playS2C().register(AuctionNetwork.SYNC_BID_UPDATE_TYPE, AuctionNetwork.SYNC_BID_UPDATE_CODEC);
/* 35 */     PayloadTypeRegistry.playS2C().register(AuctionNetwork.SYNC_BID_RESULT_TYPE, AuctionNetwork.SYNC_BID_RESULT_CODEC);
/* 36 */     PayloadTypeRegistry.playS2C().register(AuctionNetwork.SYNC_MY_AUCTIONS_TYPE, AuctionNetwork.SYNC_MY_AUCTIONS_CODEC);
/* 37 */     PayloadTypeRegistry.playS2C().register(AuctionNetwork.SYNC_HISTORY_TYPE, AuctionNetwork.SYNC_HISTORY_CODEC);
/* 38 */     PayloadTypeRegistry.playS2C().register(AuctionNetwork.SYNC_CREATE_RESULT_TYPE, AuctionNetwork.SYNC_CREATE_RESULT_CODEC);
/* 39 */     PayloadTypeRegistry.playS2C().register(AuctionNetwork.SYNC_UNLIST_RESULT_TYPE, AuctionNetwork.SYNC_UNLIST_RESULT_CODEC);
/* 40 */     PayloadTypeRegistry.playS2C().register(AuctionNetwork.SYNC_INVENTORY_TYPE, AuctionNetwork.SYNC_INVENTORY_CODEC);
/*    */ 
/*    */     
/* 43 */     PayloadTypeRegistry.playS2C().register(AuctionNetwork.SYNC_RECENTLY_SOLD_TYPE, AuctionNetwork.SYNC_RECENTLY_SOLD_CODEC);
/*    */ 
/*    */     
/* 46 */     PayloadTypeRegistry.playS2C().register(AuctionNetwork.SYNC_PRICE_LOOKUP_KEYS_TYPE, AuctionNetwork.SYNC_PRICE_LOOKUP_KEYS_CODEC);
/* 47 */     PayloadTypeRegistry.playS2C().register(AuctionNetwork.SYNC_PRICE_LOOKUP_TYPE, AuctionNetwork.SYNC_PRICE_LOOKUP_CODEC);
/* 48 */     PayloadTypeRegistry.playS2C().register(AuctionNetwork.OPEN_PRICE_LOOKUP_TYPE, AuctionNetwork.OPEN_PRICE_LOOKUP_CODEC);
/*    */   }
/*    */   
/*    */   public static void registerClientPackets() {
/* 52 */     AuctionNetworkHelperClient.registerClientReceivers();
/*    */   }
/*    */   
/*    */   public static void sendToServer(class_8710 payload) {
/* 56 */     AuctionNetworkHelperClient.sendToServer(payload);
/*    */   }
/*    */   
/*    */   public static void sendToPlayer(class_3222 player, class_8710 payload) {
/* 60 */     ServerPlayNetworking.send(player, payload);
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\auction\network\AuctionNetworkHelper.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */