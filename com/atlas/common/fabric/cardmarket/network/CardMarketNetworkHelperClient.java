/*    */ package com.atlas.common.fabric.cardmarket.network;
/*    */ 
/*    */ import com.atlas.common.fabric.cardmarket.client.CardMarketClientData;
/*    */ import com.atlas.common.fabric.cardmarket.screen.MarketplaceScreen;
/*    */ import net.fabricmc.api.EnvType;
/*    */ import net.fabricmc.api.Environment;
/*    */ import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
/*    */ import net.minecraft.class_310;
/*    */ import net.minecraft.class_437;
/*    */ import net.minecraft.class_8710;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Environment(EnvType.CLIENT)
/*    */ public final class CardMarketNetworkHelperClient
/*    */ {
/*    */   public static void sendToServer(class_8710 payload) {
/* 18 */     ClientPlayNetworking.send(payload);
/*    */   }
/*    */   
/*    */   public static void registerClientReceivers() {
/* 22 */     ClientPlayNetworking.registerGlobalReceiver(CardMarketNetwork.OPEN_MARKET_TYPE, (payload, context) -> context.client().execute(()));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 28 */     ClientPlayNetworking.registerGlobalReceiver(CardMarketNetwork.SYNC_MARKET_TYPE, (payload, context) -> context.client().execute(()));
/*    */ 
/*    */ 
/*    */     
/* 32 */     ClientPlayNetworking.registerGlobalReceiver(CardMarketNetwork.RESULT_TYPE, (payload, context) -> context.client().execute(()));
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\cardmarket\network\CardMarketNetworkHelperClient.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */