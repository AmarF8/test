/*    */ package com.atlas.common.fabric.clan.network;
/*    */ 
/*    */ import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
/*    */ import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
/*    */ import net.minecraft.class_3222;
/*    */ import net.minecraft.class_8710;
/*    */ 
/*    */ 
/*    */ public class ClanNetworkHelper
/*    */ {
/*    */   public static void registerPackets() {
/* 12 */     PayloadTypeRegistry.playC2S().register(ClanNetwork.REQUEST_SYNC_TYPE, ClanNetwork.REQUEST_SYNC_CODEC);
/* 13 */     PayloadTypeRegistry.playC2S().register(ClanNetwork.REQUEST_CREATE_CLAN_TYPE, ClanNetwork.REQUEST_CREATE_CLAN_CODEC);
/* 14 */     PayloadTypeRegistry.playC2S().register(ClanNetwork.REQUEST_INVITE_MEMBER_TYPE, ClanNetwork.REQUEST_INVITE_MEMBER_CODEC);
/* 15 */     PayloadTypeRegistry.playC2S().register(ClanNetwork.REQUEST_KICK_MEMBER_TYPE, ClanNetwork.REQUEST_KICK_MEMBER_CODEC);
/* 16 */     PayloadTypeRegistry.playC2S().register(ClanNetwork.REQUEST_PROMOTE_MEMBER_TYPE, ClanNetwork.REQUEST_PROMOTE_MEMBER_CODEC);
/* 17 */     PayloadTypeRegistry.playC2S().register(ClanNetwork.REQUEST_RENAME_CLAN_TYPE, ClanNetwork.REQUEST_RENAME_CLAN_CODEC);
/* 18 */     PayloadTypeRegistry.playC2S().register(ClanNetwork.REQUEST_SET_BANNER_TYPE, ClanNetwork.REQUEST_SET_BANNER_CODEC);
/* 19 */     PayloadTypeRegistry.playC2S().register(ClanNetwork.REQUEST_TOGGLE_PERMISSION_TYPE, ClanNetwork.REQUEST_TOGGLE_PERMISSION_CODEC);
/* 20 */     PayloadTypeRegistry.playC2S().register(ClanNetwork.REQUEST_LEAVE_CLAN_TYPE, ClanNetwork.REQUEST_LEAVE_CLAN_CODEC);
/* 21 */     PayloadTypeRegistry.playC2S().register(ClanNetwork.REQUEST_CHALLENGES_TYPE, ClanNetwork.REQUEST_CHALLENGES_CODEC);
/* 22 */     PayloadTypeRegistry.playC2S().register(ClanNetwork.REQUEST_LEADERBOARD_TYPE, ClanNetwork.REQUEST_LEADERBOARD_CODEC);
/* 23 */     PayloadTypeRegistry.playC2S().register(ClanNetwork.REQUEST_VALOR_ACTIVITIES_TYPE, ClanNetwork.REQUEST_VALOR_ACTIVITIES_CODEC);
/* 24 */     PayloadTypeRegistry.playC2S().register(ClanNetwork.REQUEST_VALOR_ACTIVITY_PROGRESS_TYPE, ClanNetwork.REQUEST_VALOR_ACTIVITY_PROGRESS_CODEC);
/* 25 */     PayloadTypeRegistry.playC2S().register(ClanNetwork.REQUEST_PLAYER_NAMES_TYPE, ClanNetwork.REQUEST_PLAYER_NAMES_CODEC);
/*    */ 
/*    */     
/* 28 */     PayloadTypeRegistry.playS2C().register(ClanNetwork.OPEN_SCREEN_TYPE, ClanNetwork.OPEN_SCREEN_CODEC);
/* 29 */     PayloadTypeRegistry.playS2C().register(ClanNetwork.SYNC_CLAN_DATA_TYPE, ClanNetwork.SYNC_CLAN_DATA_CODEC);
/* 30 */     PayloadTypeRegistry.playS2C().register(ClanNetwork.SYNC_NO_CLAN_TYPE, ClanNetwork.SYNC_NO_CLAN_CODEC);
/* 31 */     PayloadTypeRegistry.playS2C().register(ClanNetwork.SYNC_CHALLENGES_TYPE, ClanNetwork.SYNC_CHALLENGES_CODEC);
/* 32 */     PayloadTypeRegistry.playS2C().register(ClanNetwork.SYNC_LEADERBOARD_TYPE, ClanNetwork.SYNC_LEADERBOARD_CODEC);
/* 33 */     PayloadTypeRegistry.playS2C().register(ClanNetwork.SYNC_VALOR_ACTIVITIES_TYPE, ClanNetwork.SYNC_VALOR_ACTIVITIES_CODEC);
/* 34 */     PayloadTypeRegistry.playS2C().register(ClanNetwork.SYNC_VALOR_ACTIVITY_PROGRESS_TYPE, ClanNetwork.SYNC_VALOR_ACTIVITY_PROGRESS_CODEC);
/* 35 */     PayloadTypeRegistry.playS2C().register(ClanNetwork.ACTION_RESULT_TYPE, ClanNetwork.ACTION_RESULT_CODEC);
/* 36 */     PayloadTypeRegistry.playS2C().register(ClanNetwork.SYNC_PLAYER_NAMES_TYPE, ClanNetwork.SYNC_PLAYER_NAMES_CODEC);
/*    */   }
/*    */   
/*    */   public static void registerClientPackets() {
/* 40 */     ClanNetworkHelperClient.registerClientReceivers();
/*    */   }
/*    */   
/*    */   public static void sendToServer(class_8710 payload) {
/* 44 */     ClanNetworkHelperClient.sendToServer(payload);
/*    */   }
/*    */   
/*    */   public static void sendToPlayer(class_3222 player, class_8710 payload) {
/* 48 */     ServerPlayNetworking.send(player, payload);
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\clan\network\ClanNetworkHelper.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */