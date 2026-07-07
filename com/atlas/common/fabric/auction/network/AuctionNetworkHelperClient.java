/*     */ package com.atlas.common.fabric.auction.network;
/*     */ import com.atlas.common.fabric.auction.data.AuctionClientData;
/*     */ import com.atlas.common.fabric.auction.screen.AuctionHouseScreen;
/*     */ import com.atlas.common.fabric.auction.screen.PriceLookupScreen;
/*     */ import net.fabricmc.api.EnvType;
/*     */ import net.fabricmc.api.Environment;
/*     */ import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
/*     */ import net.minecraft.class_310;
/*     */ import net.minecraft.class_437;
/*     */ import net.minecraft.class_8710;
/*     */ 
/*     */ @Environment(EnvType.CLIENT)
/*     */ public class AuctionNetworkHelperClient {
/*     */   public static void sendToServer(class_8710 payload) {
/*  15 */     ClientPlayNetworking.send(payload);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void registerClientReceivers() {
/*  21 */     ClientPlayNetworking.registerGlobalReceiver(AuctionNetwork.SYNC_PAGE_TYPE, (payload, context) -> {
/*     */           AuctionNetwork.SyncAuctionPagePayload data = payload;
/*     */ 
/*     */           
/*     */           context.client().execute(());
/*     */         });
/*     */ 
/*     */     
/*  29 */     ClientPlayNetworking.registerGlobalReceiver(AuctionNetwork.OPEN_SCREEN_TYPE, (payload, context) -> context.client().execute(()));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  36 */     ClientPlayNetworking.registerGlobalReceiver(AuctionNetwork.SYNC_BALANCE_TYPE, (payload, context) -> {
/*     */           AuctionNetwork.SyncBalancePayload data = payload;
/*     */ 
/*     */           
/*     */           context.client().execute(());
/*     */         });
/*     */ 
/*     */     
/*  44 */     ClientPlayNetworking.registerGlobalReceiver(AuctionNetwork.SYNC_DETAIL_TYPE, (payload, context) -> {
/*     */           AuctionNetwork.SyncAuctionDetailPayload data = payload;
/*     */ 
/*     */           
/*     */           context.client().execute(());
/*     */         });
/*     */ 
/*     */     
/*  52 */     ClientPlayNetworking.registerGlobalReceiver(AuctionNetwork.SYNC_BID_UPDATE_TYPE, (payload, context) -> {
/*     */           AuctionNetwork.SyncBidUpdatePayload data = payload;
/*     */ 
/*     */           
/*     */           context.client().execute(());
/*     */         });
/*     */ 
/*     */     
/*  60 */     ClientPlayNetworking.registerGlobalReceiver(AuctionNetwork.SYNC_BID_RESULT_TYPE, (payload, context) -> {
/*     */           AuctionNetwork.SyncBidResultPayload data = payload;
/*     */ 
/*     */           
/*     */           context.client().execute(());
/*     */         });
/*     */ 
/*     */     
/*  68 */     ClientPlayNetworking.registerGlobalReceiver(AuctionNetwork.SYNC_RECENTLY_SOLD_TYPE, (payload, context) -> context.client().execute(()));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  73 */     ClientPlayNetworking.registerGlobalReceiver(AuctionNetwork.SYNC_MY_AUCTIONS_TYPE, (payload, context) -> context.client().execute(()));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  78 */     ClientPlayNetworking.registerGlobalReceiver(AuctionNetwork.SYNC_HISTORY_TYPE, (payload, context) -> context.client().execute(()));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  83 */     ClientPlayNetworking.registerGlobalReceiver(AuctionNetwork.SYNC_CREATE_RESULT_TYPE, (payload, context) -> context.client().execute(()));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  88 */     ClientPlayNetworking.registerGlobalReceiver(AuctionNetwork.SYNC_UNLIST_RESULT_TYPE, (payload, context) -> context.client().execute(()));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  93 */     ClientPlayNetworking.registerGlobalReceiver(AuctionNetwork.SYNC_INVENTORY_TYPE, (payload, context) -> context.client().execute(()));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  99 */     ClientPlayNetworking.registerGlobalReceiver(AuctionNetwork.SYNC_PRICE_LOOKUP_KEYS_TYPE, (payload, context) -> context.client().execute(()));
/*     */ 
/*     */ 
/*     */     
/* 103 */     ClientPlayNetworking.registerGlobalReceiver(AuctionNetwork.SYNC_PRICE_LOOKUP_TYPE, (payload, context) -> context.client().execute(()));
/*     */ 
/*     */ 
/*     */     
/* 107 */     ClientPlayNetworking.registerGlobalReceiver(AuctionNetwork.OPEN_PRICE_LOOKUP_TYPE, (payload, context) -> context.client().execute(()));
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\auction\network\AuctionNetworkHelperClient.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */