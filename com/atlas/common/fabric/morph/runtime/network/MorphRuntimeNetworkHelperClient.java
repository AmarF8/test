/*    */ package com.atlas.common.fabric.morph.runtime.network;
/*    */ 
/*    */ import com.atlas.common.fabric.morph.runtime.client.MorphClientHandler;
/*    */ import com.atlas.common.fabric.morph.runtime.client.MorphedPlayerRegistry;
/*    */ import net.fabricmc.api.EnvType;
/*    */ import net.fabricmc.api.Environment;
/*    */ import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
/*    */ import net.minecraft.class_8710;
/*    */ 
/*    */ @Environment(EnvType.CLIENT)
/*    */ public class MorphRuntimeNetworkHelperClient
/*    */ {
/*    */   public static void sendToServer(class_8710 payload) {
/* 14 */     ClientPlayNetworking.send(payload);
/*    */   }
/*    */   
/*    */   public static void registerClientReceivers() {
/* 18 */     ClientPlayNetworking.registerGlobalReceiver(MorphRuntimeNetwork.MORPH_STATE_TYPE, (payload, context) -> context.client().execute(()));
/*    */ 
/*    */ 
/*    */     
/* 22 */     ClientPlayNetworking.registerGlobalReceiver(MorphRuntimeNetwork.PLAY_MOVE_TYPE, (payload, context) -> context.client().execute(()));
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\morph\runtime\network\MorphRuntimeNetworkHelperClient.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */