/*    */ package com.atlas.common.fabric.rustlingspots.network;
/*    */ 
/*    */ import com.atlas.common.fabric.rustlingspots.client.RustlingSpotClientHandler;
/*    */ import net.fabricmc.api.EnvType;
/*    */ import net.fabricmc.api.Environment;
/*    */ import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Environment(EnvType.CLIENT)
/*    */ public final class RustlingSpotsNetworkHelperClient
/*    */ {
/*    */   public static void registerClientReceivers() {
/* 17 */     ClientPlayNetworking.registerGlobalReceiver(RustlingSpotsNetwork.SPAWN_SPOT_TYPE, (payload, context) -> context.client().execute(()));
/*    */ 
/*    */ 
/*    */     
/* 21 */     ClientPlayNetworking.registerGlobalReceiver(RustlingSpotsNetwork.REMOVE_SPOT_TYPE, (payload, context) -> context.client().execute(()));
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\rustlingspots\network\RustlingSpotsNetworkHelperClient.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */