/*    */ package com.atlas.common.fabric.guide.network;
/*    */ 
/*    */ import com.atlas.common.fabric.guide.data.GuideData;
/*    */ import com.atlas.common.fabric.guide.screen.GuideScreen;
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
/*    */ public final class GuideNetworkHelperClient
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
/* 32 */     ClientPlayNetworking.registerGlobalReceiver(GuideNetwork.OPEN_GUIDE_TYPE, (payload, context) -> context.client().execute(()));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 39 */     ClientPlayNetworking.registerGlobalReceiver(GuideNetwork.OPEN_GUIDE_BRANDED_TYPE, (payload, context) -> {
/*    */           String networkName = payload.networkName();
/*    */ 
/*    */ 
/*    */           
/*    */           String wikiUrl = payload.wikiUrl();
/*    */ 
/*    */           
/*    */           context.client().execute(());
/*    */         });
/*    */ 
/*    */     
/* 51 */     ClientPlayNetworking.registerGlobalReceiver(GuideNetwork.SYNC_POKEDEX_PAGE_TYPE, (payload, context) -> {
/*    */           GuideNetwork.SyncPokedexPagePayload data = payload;
/*    */ 
/*    */           
/*    */           context.client().execute(());
/*    */         });
/*    */ 
/*    */     
/* 59 */     ClientPlayNetworking.registerGlobalReceiver(GuideNetwork.SYNC_POKEMON_DETAIL_TYPE, (payload, context) -> {
/*    */           GuideNetwork.SyncPokemonDetailPayload data = payload;
/*    */ 
/*    */           
/*    */           context.client().execute(());
/*    */         });
/*    */ 
/*    */     
/* 67 */     ClientPlayNetworking.registerGlobalReceiver(GuideNetwork.SYNC_GUIDE_DATA_TYPE, (payload, context) -> {
/*    */           GuideNetwork.SyncGuideDataPayload data = payload;
/*    */           context.client().execute(());
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\guide\network\GuideNetworkHelperClient.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */