/*     */ package com.atlas.common.fabric.battletower.network;
/*     */ 
/*     */ import com.atlas.common.fabric.AtlasMod;
/*     */ import com.atlas.common.fabric.battletower.data.ShopItem;
/*     */ import com.atlas.common.fabric.battletower.screen.BattleTowerScreen;
/*     */ import com.atlas.common.fabric.battletower.screen.LeaderboardScreen;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.fabricmc.api.EnvType;
/*     */ import net.fabricmc.api.Environment;
/*     */ import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
/*     */ import net.minecraft.class_2960;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Environment(EnvType.CLIENT)
/*     */ public class NetworkHelperClient
/*     */ {
/*     */   public static void sendToServer(class_8710 payload) {
/*  33 */     ClientPlayNetworking.send(payload);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void registerClientReceivers() {
/*  42 */     ClientPlayNetworking.registerGlobalReceiver(BattleTowerNetwork.SYNC_DATA_TYPE, (payload, context) -> {
/*     */           BattleTowerNetwork.SyncDataPayload data = payload;
/*     */ 
/*     */ 
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
/*     */ 
/*     */ 
/*     */     
/*  60 */     ClientPlayNetworking.registerGlobalReceiver(BattleTowerNetwork.SYNC_PARTY_TYPE, (payload, context) -> {
/*     */           BattleTowerNetwork.SyncPartyPayload data = payload;
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           context.client().execute(());
/*     */         });
/*     */ 
/*     */ 
/*     */     
/*  71 */     ClientPlayNetworking.registerGlobalReceiver(BattleTowerNetwork.SYNC_SNAPSHOT_TYPE, (payload, context) -> {
/*     */           BattleTowerNetwork.SyncSnapshotPayload data = payload;
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
/*     */     
/*  85 */     ClientPlayNetworking.registerGlobalReceiver(BattleTowerNetwork.SYNC_SHOP_ITEMS_TYPE, (payload, context) -> {
/*     */           BattleTowerNetwork.SyncShopItemsPayload data = payload;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 115 */     ClientPlayNetworking.registerGlobalReceiver(BattleTowerNetwork.SYNC_LEADERBOARD_TYPE, (payload, context) -> {
/*     */           BattleTowerNetwork.SyncLeaderboardPayload data = payload;
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           context.client().execute(());
/*     */         });
/*     */ 
/*     */ 
/*     */     
/* 126 */     ClientPlayNetworking.registerGlobalReceiver(BattleTowerNetwork.SYNC_ACTIVE_PLAYERS_TYPE, (payload, context) -> {
/*     */           BattleTowerNetwork.SyncActivePlayersPayload data = payload;
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           context.client().execute(());
/*     */         });
/*     */ 
/*     */ 
/*     */     
/* 137 */     ClientPlayNetworking.registerGlobalReceiver(BattleTowerNetwork.LOCK_IN_RESULT_TYPE, (payload, context) -> {
/*     */           BattleTowerNetwork.LockInResultPayload data = payload;
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           context.client().execute(());
/*     */         });
/*     */ 
/*     */ 
/*     */     
/* 148 */     ClientPlayNetworking.registerGlobalReceiver(BattleTowerNetwork.OPEN_SCREEN_TYPE, (payload, context) -> context.client().execute(()));
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\battletower\network\NetworkHelperClient.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */