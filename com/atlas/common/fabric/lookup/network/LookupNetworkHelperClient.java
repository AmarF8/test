/*    */ package com.atlas.common.fabric.lookup.network;
/*    */ 
/*    */ import com.atlas.common.fabric.lookup.data.LookupClientData;
/*    */ import com.atlas.common.fabric.lookup.screen.LookupScreen;
/*    */ import net.fabricmc.api.EnvType;
/*    */ import net.fabricmc.api.Environment;
/*    */ import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
/*    */ import net.minecraft.class_310;
/*    */ import net.minecraft.class_437;
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
/*    */ @Environment(EnvType.CLIENT)
/*    */ public final class LookupNetworkHelperClient
/*    */ {
/*    */   public static void sendToServer(class_8710 payload) {
/* 24 */     ClientPlayNetworking.send(payload);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void registerClientReceivers() {
/* 35 */     ClientPlayNetworking.registerGlobalReceiver(LookupNetwork.OPEN_LOOKUP_TYPE, (payload, context) -> {
/*    */           LookupNetwork.OpenLookupPayload data = payload;
/*    */ 
/*    */ 
/*    */           
/*    */           context.client().execute(());
/*    */         });
/*    */ 
/*    */     
/* 44 */     ClientPlayNetworking.registerGlobalReceiver(LookupNetwork.SYNC_LOOKUP_DATA_TYPE, (payload, context) -> {
/*    */           LookupNetwork.SyncLookupDataPayload data = payload;
/*    */           context.client().execute(());
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\lookup\network\LookupNetworkHelperClient.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */