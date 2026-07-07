/*    */ package com.atlas.common.fabric.lookup.network;
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
/*    */ public final class LookupNetworkHelper
/*    */ {
/*    */   public static void registerPackets() {
/* 21 */     PayloadTypeRegistry.playC2S().register(LookupNetwork.REQUEST_LOOKUP_DATA_TYPE, LookupNetwork.REQUEST_LOOKUP_DATA_CODEC);
/*    */ 
/*    */     
/* 24 */     PayloadTypeRegistry.playS2C().register(LookupNetwork.OPEN_LOOKUP_TYPE, LookupNetwork.OPEN_LOOKUP_CODEC);
/* 25 */     PayloadTypeRegistry.playS2C().register(LookupNetwork.SYNC_LOOKUP_DATA_TYPE, LookupNetwork.SYNC_LOOKUP_DATA_CODEC);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void registerClientPackets() {
/* 32 */     LookupNetworkHelperClient.registerClientReceivers();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void sendToServer(class_8710 payload) {
/* 39 */     LookupNetworkHelperClient.sendToServer(payload);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void sendToPlayer(class_3222 player, class_8710 payload) {
/* 46 */     ServerPlayNetworking.send(player, payload);
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\lookup\network\LookupNetworkHelper.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */