/*    */ package com.atlas.common.fabric.foreverpack.network;
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
/*    */ public class ForeverPackNetworkHelper
/*    */ {
/*    */   public static void registerPackets() {
/* 17 */     PayloadTypeRegistry.playC2S().register(ForeverPackNetwork.REQUEST_SYNC_TYPE, ForeverPackNetwork.REQUEST_SYNC_CODEC);
/* 18 */     PayloadTypeRegistry.playC2S().register(ForeverPackNetwork.REQUEST_PURCHASE_TYPE, ForeverPackNetwork.REQUEST_PURCHASE_CODEC);
/*    */ 
/*    */     
/* 21 */     PayloadTypeRegistry.playS2C().register(ForeverPackNetwork.OPEN_SCREEN_TYPE, ForeverPackNetwork.OPEN_SCREEN_CODEC);
/* 22 */     PayloadTypeRegistry.playS2C().register(ForeverPackNetwork.SYNC_TYPE, ForeverPackNetwork.SYNC_CODEC);
/* 23 */     PayloadTypeRegistry.playS2C().register(ForeverPackNetwork.PURCHASE_RESULT_TYPE, ForeverPackNetwork.PURCHASE_RESULT_CODEC);
/*    */   }
/*    */   
/*    */   public static void registerClientPackets() {
/* 27 */     ForeverPackNetworkHelperClient.registerClientReceivers();
/*    */   }
/*    */   
/*    */   public static void sendToServer(class_8710 payload) {
/* 31 */     ForeverPackNetworkHelperClient.sendToServer(payload);
/*    */   }
/*    */   
/*    */   public static void sendToPlayer(class_3222 player, class_8710 payload) {
/* 35 */     ServerPlayNetworking.send(player, payload);
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\foreverpack\network\ForeverPackNetworkHelper.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */