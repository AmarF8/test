/*    */ package com.atlas.common.fabric.safari.expedition.network;
/*    */ 
/*    */ import com.atlas.common.fabric.safari.expedition.ExpeditionPCGUIConfiguration;
/*    */ import com.atlas.common.fabric.safari.expedition.SafariExpeditionClientData;
/*    */ import com.atlas.common.fabric.safari.expedition.SafariExpeditionModels;
/*    */ import com.atlas.common.fabric.safari.expedition.screen.SafariExpeditionScreen;
/*    */ import com.cobblemon.mod.common.api.reactive.SettableObservable;
/*    */ import com.cobblemon.mod.common.client.CobblemonClient;
/*    */ import com.cobblemon.mod.common.client.gui.pc.PCGUI;
/*    */ import com.cobblemon.mod.common.client.gui.pc.PCGUIConfiguration;
/*    */ import com.cobblemon.mod.common.client.storage.ClientPC;
/*    */ import java.util.LinkedHashSet;
/*    */ import net.fabricmc.api.EnvType;
/*    */ import net.fabricmc.api.Environment;
/*    */ import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
/*    */ import net.minecraft.class_310;
/*    */ import net.minecraft.class_437;
/*    */ import net.minecraft.class_8710;
/*    */ 
/*    */ @Environment(EnvType.CLIENT)
/*    */ public final class SafariExpeditionNetworkHelperClient {
/*    */   public static void sendToServer(class_8710 payload) {
/* 23 */     ClientPlayNetworking.send(payload);
/*    */   }
/*    */   
/*    */   public static void registerClientReceivers() {
/* 27 */     ClientPlayNetworking.registerGlobalReceiver(SafariExpeditionNetwork.OPEN_SCREEN_TYPE, (payload, context) -> context.client().execute(()));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 33 */     ClientPlayNetworking.registerGlobalReceiver(SafariExpeditionNetwork.SYNC_TYPE, (payload, context) -> context.client().execute(()));
/*    */ 
/*    */     
/* 36 */     ClientPlayNetworking.registerGlobalReceiver(SafariExpeditionNetwork.BUILDER_OPEN_TYPE, (payload, context) -> context.client().execute(()));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 52 */     ClientPlayNetworking.registerGlobalReceiver(SafariExpeditionNetwork.BUILDER_SYNC_TYPE, (payload, context) -> context.client().execute(()));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 64 */     ClientPlayNetworking.registerGlobalReceiver(SafariExpeditionNetwork.BUILDER_CLOSE_TYPE, (payload, context) -> context.client().execute(()));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 73 */     ClientPlayNetworking.registerGlobalReceiver(SafariExpeditionNetwork.ACTION_RESULT_TYPE, (payload, context) -> context.client().execute(()));
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\safari\expedition\network\SafariExpeditionNetworkHelperClient.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */