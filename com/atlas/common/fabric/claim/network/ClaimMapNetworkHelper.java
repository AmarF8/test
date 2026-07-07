/*    */ package com.atlas.common.fabric.claim.network;
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
/*    */ public class ClaimMapNetworkHelper
/*    */ {
/*    */   public static void registerPackets() {
/* 17 */     PayloadTypeRegistry.playC2S().register(ClaimMapNetwork.REQUEST_MAP_DATA_TYPE, ClaimMapNetwork.REQUEST_MAP_DATA_CODEC);
/* 18 */     PayloadTypeRegistry.playC2S().register(ClaimMapNetwork.REQUEST_CLAIM_CHUNKS_TYPE, ClaimMapNetwork.REQUEST_CLAIM_CHUNKS_CODEC);
/* 19 */     PayloadTypeRegistry.playC2S().register(ClaimMapNetwork.REQUEST_UNCLAIM_TYPE, ClaimMapNetwork.REQUEST_UNCLAIM_CODEC);
/* 20 */     PayloadTypeRegistry.playC2S().register(ClaimMapNetwork.REQUEST_CLAIM_DETAIL_TYPE, ClaimMapNetwork.REQUEST_CLAIM_DETAIL_CODEC);
/* 21 */     PayloadTypeRegistry.playC2S().register(ClaimMapNetwork.REQUEST_SET_PROFILE_TYPE, ClaimMapNetwork.REQUEST_SET_PROFILE_CODEC);
/* 22 */     PayloadTypeRegistry.playC2S().register(ClaimMapNetwork.REQUEST_TOGGLE_PERMISSION_TYPE, ClaimMapNetwork.REQUEST_TOGGLE_PERMISSION_CODEC);
/* 23 */     PayloadTypeRegistry.playC2S().register(ClaimMapNetwork.REQUEST_TOGGLE_SETTING_TYPE, ClaimMapNetwork.REQUEST_TOGGLE_SETTING_CODEC);
/* 24 */     PayloadTypeRegistry.playC2S().register(ClaimMapNetwork.REQUEST_RENAME_CLAIM_TYPE, ClaimMapNetwork.REQUEST_RENAME_CLAIM_CODEC);
/* 25 */     PayloadTypeRegistry.playC2S().register(ClaimMapNetwork.REQUEST_RENAME_PROFILE_TYPE, ClaimMapNetwork.REQUEST_RENAME_PROFILE_CODEC);
/* 26 */     PayloadTypeRegistry.playC2S().register(ClaimMapNetwork.REQUEST_REMOVE_MEMBER_TYPE, ClaimMapNetwork.REQUEST_REMOVE_MEMBER_CODEC);
/* 27 */     PayloadTypeRegistry.playC2S().register(ClaimMapNetwork.REQUEST_SET_MEMBER_RANK_TYPE, ClaimMapNetwork.REQUEST_SET_MEMBER_RANK_CODEC);
/* 28 */     PayloadTypeRegistry.playC2S().register(ClaimMapNetwork.REQUEST_ADD_MEMBER_TYPE, ClaimMapNetwork.REQUEST_ADD_MEMBER_CODEC);
/* 29 */     PayloadTypeRegistry.playC2S().register(ClaimMapNetwork.REQUEST_TELEPORT_CLAIM_TYPE, ClaimMapNetwork.REQUEST_TELEPORT_CLAIM_CODEC);
/* 30 */     PayloadTypeRegistry.playC2S().register(ClaimMapNetwork.REQUEST_PROFILE_DETAIL_TYPE, ClaimMapNetwork.REQUEST_PROFILE_DETAIL_CODEC);
/*    */ 
/*    */     
/* 33 */     PayloadTypeRegistry.playS2C().register(ClaimMapNetwork.OPEN_SCREEN_TYPE, ClaimMapNetwork.OPEN_SCREEN_CODEC);
/* 34 */     PayloadTypeRegistry.playS2C().register(ClaimMapNetwork.SYNC_MAP_DATA_TYPE, ClaimMapNetwork.SYNC_MAP_DATA_CODEC);
/* 35 */     PayloadTypeRegistry.playS2C().register(ClaimMapNetwork.SYNC_PLAYER_DATA_TYPE, ClaimMapNetwork.SYNC_PLAYER_DATA_CODEC);
/* 36 */     PayloadTypeRegistry.playS2C().register(ClaimMapNetwork.SYNC_CLAIM_DETAIL_TYPE, ClaimMapNetwork.SYNC_CLAIM_DETAIL_CODEC);
/* 37 */     PayloadTypeRegistry.playS2C().register(ClaimMapNetwork.SYNC_PROFILES_TYPE, ClaimMapNetwork.SYNC_PROFILES_CODEC);
/* 38 */     PayloadTypeRegistry.playS2C().register(ClaimMapNetwork.CLAIM_RESULT_TYPE, ClaimMapNetwork.CLAIM_RESULT_CODEC);
/* 39 */     PayloadTypeRegistry.playS2C().register(ClaimMapNetwork.SYNC_PROFILE_DETAIL_TYPE, ClaimMapNetwork.SYNC_PROFILE_DETAIL_CODEC);
/* 40 */     PayloadTypeRegistry.playS2C().register(ClaimMapNetwork.SYNC_ADMIN_LOOKUP_TYPE, ClaimMapNetwork.SYNC_ADMIN_LOOKUP_CODEC);
/*    */   }
/*    */   
/*    */   public static void registerClientPackets() {
/* 44 */     ClaimMapNetworkHelperClient.registerClientReceivers();
/*    */   }
/*    */   
/*    */   public static void sendToServer(class_8710 payload) {
/* 48 */     ClaimMapNetworkHelperClient.sendToServer(payload);
/*    */   }
/*    */   
/*    */   public static void sendToPlayer(class_3222 player, class_8710 payload) {
/* 52 */     ServerPlayNetworking.send(player, payload);
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\claim\network\ClaimMapNetworkHelper.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */