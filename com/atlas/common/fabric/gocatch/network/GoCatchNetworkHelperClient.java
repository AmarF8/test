/*    */ package com.atlas.common.fabric.gocatch.network;
/*    */ 
/*    */ import com.atlas.common.fabric.gocatch.GoCatchThrowQuality;
/*    */ import com.atlas.common.fabric.gocatch.client.GoCatchFlagCache;
/*    */ import com.atlas.common.fabric.gocatch.client.GoCatchThrowEffectsHandler;
/*    */ import com.atlas.common.fabric.gocatch.client.GoCatchThrowFeedbackTracker;
/*    */ import net.fabricmc.api.EnvType;
/*    */ import net.fabricmc.api.Environment;
/*    */ import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
/*    */ import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
/*    */ import net.minecraft.class_310;
/*    */ import net.minecraft.class_634;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Environment(EnvType.CLIENT)
/*    */ public final class GoCatchNetworkHelperClient
/*    */ {
/*    */   public static void registerClientReceivers() {
/* 22 */     ClientPlayConnectionEvents.DISCONNECT.register((handler, client) -> GoCatchFlagCache.getInstance().clear());
/*    */ 
/*    */ 
/*    */     
/* 26 */     ClientPlayNetworking.registerGlobalReceiver(GoCatchNetwork.SYNC_CONFIG_TYPE, (payload, context) -> {
/*    */         
/*    */         });
/*    */ 
/*    */     
/* 31 */     ClientPlayNetworking.registerGlobalReceiver(GoCatchNetwork.FLAG_TYPE, (payload, context) -> context.client().execute(()));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 38 */     ClientPlayNetworking.registerGlobalReceiver(GoCatchNetwork.THROW_QUALITY_TYPE, (payload, context) -> context.client().execute(()));
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\gocatch\network\GoCatchNetworkHelperClient.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */