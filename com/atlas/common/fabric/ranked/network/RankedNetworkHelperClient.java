/*     */ package com.atlas.common.fabric.ranked.network;
/*     */ 
/*     */ import com.atlas.common.fabric.ranked.data.PlayerRankedData;
/*     */ import com.atlas.common.fabric.ranked.screen.RankedDefenseScreen;
/*     */ import com.atlas.common.fabric.ranked.screen.RankedScreen;
/*     */ import net.fabricmc.api.EnvType;
/*     */ import net.fabricmc.api.Environment;
/*     */ import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
/*     */ import net.minecraft.class_310;
/*     */ import net.minecraft.class_437;
/*     */ import net.minecraft.class_8710;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Environment(EnvType.CLIENT)
/*     */ public class RankedNetworkHelperClient
/*     */ {
/*     */   public static void sendToServer(class_8710 payload) {
/*  25 */     ClientPlayNetworking.send(payload);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void registerClientReceivers() {
/*  31 */     ClientPlayNetworking.registerGlobalReceiver(RankedNetwork.SYNC_DATA_TYPE, (payload, context) -> {
/*     */           RankedNetwork.SyncDataPayload data = payload;
/*     */ 
/*     */           
/*     */           context.client().execute(());
/*     */         });
/*     */ 
/*     */     
/*  39 */     ClientPlayNetworking.registerGlobalReceiver(RankedNetwork.SYNC_DEFENSE_PARTY_TYPE, (payload, context) -> {
/*     */           RankedNetwork.SyncDefensePartyPayload data = payload;
/*     */ 
/*     */           
/*     */           context.client().execute(());
/*     */         });
/*     */ 
/*     */     
/*  47 */     ClientPlayNetworking.registerGlobalReceiver(RankedNetwork.SYNC_BATTLE_PARTY_TYPE, (payload, context) -> {
/*     */           RankedNetwork.SyncBattlePartyPayload data = payload;
/*     */ 
/*     */           
/*     */           context.client().execute(());
/*     */         });
/*     */ 
/*     */     
/*  55 */     ClientPlayNetworking.registerGlobalReceiver(RankedNetwork.SYNC_OPPONENTS_TYPE, (payload, context) -> {
/*     */           RankedNetwork.SyncOpponentsPayload data = payload;
/*     */ 
/*     */           
/*     */           context.client().execute(());
/*     */         });
/*     */ 
/*     */     
/*  63 */     ClientPlayNetworking.registerGlobalReceiver(RankedNetwork.SYNC_LEADERBOARD_TYPE, (payload, context) -> {
/*     */           RankedNetwork.SyncLeaderboardPayload data = payload;
/*     */ 
/*     */           
/*     */           context.client().execute(());
/*     */         });
/*     */ 
/*     */     
/*  71 */     ClientPlayNetworking.registerGlobalReceiver(RankedNetwork.SYNC_SHOP_ITEMS_TYPE, (payload, context) -> {
/*     */           RankedNetwork.SyncShopItemsPayload data = payload;
/*     */ 
/*     */           
/*     */           context.client().execute(());
/*     */         });
/*     */ 
/*     */     
/*  79 */     ClientPlayNetworking.registerGlobalReceiver(RankedNetwork.LOCK_IN_RESULT_TYPE, (payload, context) -> {
/*     */           RankedNetwork.LockInResultPayload data = payload;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           context.client().execute(());
/*     */         });
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  92 */     ClientPlayNetworking.registerGlobalReceiver(RankedNetwork.OPEN_SCREEN_TYPE, (payload, context) -> context.client().execute(()));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  99 */     ClientPlayNetworking.registerGlobalReceiver(RankedNetwork.SYNC_HISTORY_TYPE, (payload, context) -> {
/*     */           RankedNetwork.SyncHistoryPayload data = payload;
/*     */ 
/*     */           
/*     */           context.client().execute(());
/*     */         });
/*     */ 
/*     */     
/* 107 */     ClientPlayNetworking.registerGlobalReceiver(RankedNetwork.SYNC_PAST_SEASONS_TYPE, (payload, context) -> {
/*     */           RankedNetwork.SyncPastSeasonsPayload data = payload;
/*     */ 
/*     */           
/*     */           context.client().execute(());
/*     */         });
/*     */ 
/*     */     
/* 115 */     ClientPlayNetworking.registerGlobalReceiver(RankedNetwork.SYNC_PAST_SEASON_LB_TYPE, (payload, context) -> {
/*     */           RankedNetwork.SyncPastSeasonLeaderboardPayload data = payload;
/*     */           context.client().execute(());
/*     */         });
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\ranked\network\RankedNetworkHelperClient.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */