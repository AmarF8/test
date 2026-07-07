/*    */ package com.atlas.common.fabric.skindex.network;
/*    */ 
/*    */ import com.atlas.common.fabric.skindex.data.SkinDexData;
/*    */ import com.atlas.common.fabric.skindex.screen.SkinDexScreen;
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
/*    */ public final class SkinDexNetworkHelperClient
/*    */ {
/*    */   public static void sendToServer(class_8710 payload) {
/* 24 */     ClientPlayNetworking.send(payload);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void registerClientReceivers() {
/* 32 */     ClientPlayNetworking.registerGlobalReceiver(SkinDexNetwork.OPEN_SKINDEX_TYPE, (payload, context) -> context.client().execute(()));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 39 */     ClientPlayNetworking.registerGlobalReceiver(SkinDexNetwork.SYNC_SKINDEX_DATA_TYPE, (payload, context) -> {
/*    */           SkinDexNetwork.SyncSkinDexDataPayload data = payload;
/*    */           context.client().execute(());
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\skindex\network\SkinDexNetworkHelperClient.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */