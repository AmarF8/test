/*    */ package com.atlas.common.fabric.shop.network;
/*    */ 
/*    */ import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
/*    */ import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
/*    */ import net.minecraft.class_3222;
/*    */ import net.minecraft.class_8710;
/*    */ 
/*    */ public class ShopNetworkHelper
/*    */ {
/*    */   public static void registerPackets() {
/* 11 */     PayloadTypeRegistry.playC2S().register(ShopNetwork.REQUEST_ITEMS_TYPE, ShopNetwork.REQUEST_ITEMS_CODEC);
/* 12 */     PayloadTypeRegistry.playC2S().register(ShopNetwork.REQUEST_PURCHASE_TYPE, ShopNetwork.REQUEST_PURCHASE_CODEC);
/* 13 */     PayloadTypeRegistry.playC2S().register(ShopNetwork.REQUEST_SELL_TYPE, ShopNetwork.REQUEST_SELL_CODEC);
/*    */ 
/*    */     
/* 16 */     PayloadTypeRegistry.playS2C().register(ShopNetwork.OPEN_SCREEN_TYPE, ShopNetwork.OPEN_SCREEN_CODEC);
/* 17 */     PayloadTypeRegistry.playS2C().register(ShopNetwork.SYNC_CATEGORIES_TYPE, ShopNetwork.SYNC_CATEGORIES_CODEC);
/* 18 */     PayloadTypeRegistry.playS2C().register(ShopNetwork.SYNC_ITEMS_TYPE, ShopNetwork.SYNC_ITEMS_CODEC);
/* 19 */     PayloadTypeRegistry.playS2C().register(ShopNetwork.SYNC_BALANCE_TYPE, ShopNetwork.SYNC_BALANCE_CODEC);
/* 20 */     PayloadTypeRegistry.playS2C().register(ShopNetwork.PURCHASE_RESULT_TYPE, ShopNetwork.PURCHASE_RESULT_CODEC);
/*    */   }
/*    */   
/*    */   public static void registerClientPackets() {
/* 24 */     ShopNetworkHelperClient.registerClientReceivers();
/*    */   }
/*    */   
/*    */   public static void sendToServer(class_8710 payload) {
/* 28 */     ShopNetworkHelperClient.sendToServer(payload);
/*    */   }
/*    */   
/*    */   public static void sendToPlayer(class_3222 player, class_8710 payload) {
/* 32 */     ServerPlayNetworking.send(player, payload);
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\shop\network\ShopNetworkHelper.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */