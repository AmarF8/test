/*    */ package com.atlas.common.fabric.clan.network;
/*    */ 
/*    */ import com.atlas.common.fabric.clan.data.PlayerClanData;
/*    */ import com.atlas.common.fabric.clan.screen.ClanScreen;
/*    */ import net.fabricmc.api.EnvType;
/*    */ import net.fabricmc.api.Environment;
/*    */ import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
/*    */ import net.minecraft.class_310;
/*    */ import net.minecraft.class_437;
/*    */ import net.minecraft.class_8710;
/*    */ 
/*    */ 
/*    */ @Environment(EnvType.CLIENT)
/*    */ public class ClanNetworkHelperClient
/*    */ {
/*    */   public static void sendToServer(class_8710 payload) {
/* 17 */     ClientPlayNetworking.send(payload);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static void registerClientReceivers() {
/* 23 */     ClientPlayNetworking.registerGlobalReceiver(ClanNetwork.OPEN_SCREEN_TYPE, (payload, context) -> context.client().execute(()));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 30 */     ClientPlayNetworking.registerGlobalReceiver(ClanNetwork.SYNC_NO_CLAN_TYPE, (payload, context) -> context.client().execute(()));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 37 */     ClientPlayNetworking.registerGlobalReceiver(ClanNetwork.SYNC_CLAN_DATA_TYPE, (payload, context) -> {
/*    */           ClanNetwork.SyncClanDataPayload data = payload;
/*    */ 
/*    */           
/*    */           context.client().execute(());
/*    */         });
/*    */ 
/*    */     
/* 45 */     ClientPlayNetworking.registerGlobalReceiver(ClanNetwork.SYNC_CHALLENGES_TYPE, (payload, context) -> {
/*    */           ClanNetwork.SyncChallengesPayload data = payload;
/*    */ 
/*    */           
/*    */           context.client().execute(());
/*    */         });
/*    */ 
/*    */     
/* 53 */     ClientPlayNetworking.registerGlobalReceiver(ClanNetwork.SYNC_LEADERBOARD_TYPE, (payload, context) -> {
/*    */           ClanNetwork.SyncLeaderboardPayload data = payload;
/*    */ 
/*    */           
/*    */           context.client().execute(());
/*    */         });
/*    */ 
/*    */     
/* 61 */     ClientPlayNetworking.registerGlobalReceiver(ClanNetwork.SYNC_VALOR_ACTIVITIES_TYPE, (payload, context) -> {
/*    */           ClanNetwork.SyncValorActivitiesPayload data = payload;
/*    */ 
/*    */           
/*    */           context.client().execute(());
/*    */         });
/*    */ 
/*    */     
/* 69 */     ClientPlayNetworking.registerGlobalReceiver(ClanNetwork.SYNC_VALOR_ACTIVITY_PROGRESS_TYPE, (payload, context) -> {
/*    */           ClanNetwork.SyncValorActivityProgressPayload data = payload;
/*    */ 
/*    */           
/*    */           context.client().execute(());
/*    */         });
/*    */ 
/*    */     
/* 77 */     ClientPlayNetworking.registerGlobalReceiver(ClanNetwork.SYNC_PLAYER_NAMES_TYPE, (payload, context) -> {
/*    */           ClanNetwork.SyncPlayerNamesPayload data = payload;
/*    */ 
/*    */           
/*    */           context.client().execute(());
/*    */         });
/*    */ 
/*    */     
/* 85 */     ClientPlayNetworking.registerGlobalReceiver(ClanNetwork.ACTION_RESULT_TYPE, (payload, context) -> {
/*    */           ClanNetwork.ActionResultPayload data = payload;
/*    */           context.client().execute(());
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\clan\network\ClanNetworkHelperClient.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */