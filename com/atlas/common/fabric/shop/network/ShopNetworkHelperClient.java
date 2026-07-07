/*    */ package com.atlas.common.fabric.shop.network;
/*    */ 
/*    */ import com.atlas.common.fabric.shop.data.ShopClientData;
/*    */ import com.atlas.common.fabric.shop.screen.PokeMartScreen;
/*    */ import net.fabricmc.api.EnvType;
/*    */ import net.fabricmc.api.Environment;
/*    */ import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
/*    */ import net.minecraft.class_310;
/*    */ import net.minecraft.class_437;
/*    */ import net.minecraft.class_8710;
/*    */ 
/*    */ @Environment(EnvType.CLIENT)
/*    */ public class ShopNetworkHelperClient {
/*    */   public static void sendToServer(class_8710 payload) {
/* 15 */     ClientPlayNetworking.send(payload);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static void registerClientReceivers() {
/* 21 */     ClientPlayNetworking.registerGlobalReceiver(ShopNetwork.OPEN_SCREEN_TYPE, (payload, context) -> context.client().execute(()));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 28 */     ClientPlayNetworking.registerGlobalReceiver(ShopNetwork.SYNC_CATEGORIES_TYPE, (payload, context) -> context.client().execute(()));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 35 */     ClientPlayNetworking.registerGlobalReceiver(ShopNetwork.SYNC_ITEMS_TYPE, (payload, context) -> context.client().execute(()));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 42 */     ClientPlayNetworking.registerGlobalReceiver(ShopNetwork.SYNC_BALANCE_TYPE, (payload, context) -> context.client().execute(()));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 49 */     ClientPlayNetworking.registerGlobalReceiver(ShopNetwork.PURCHASE_RESULT_TYPE, (payload, context) -> context.client().execute(()));
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\shop\network\ShopNetworkHelperClient.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */