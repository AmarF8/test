/*    */ package com.atlas.common.fabric.morph.network;
/*    */ 
/*    */ import com.atlas.common.fabric.morph.MorphClientData;
/*    */ import com.atlas.common.fabric.morph.screen.MorphNexusScreen;
/*    */ import net.fabricmc.api.EnvType;
/*    */ import net.fabricmc.api.Environment;
/*    */ import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
/*    */ import net.minecraft.class_310;
/*    */ import net.minecraft.class_437;
/*    */ import net.minecraft.class_8710;
/*    */ 
/*    */ @Environment(EnvType.CLIENT)
/*    */ public final class MorphNetworkHelperClient
/*    */ {
/*    */   public static void sendToServer(class_8710 payload) {
/* 16 */     ClientPlayNetworking.send(payload);
/*    */   }
/*    */   
/*    */   public static void registerClientReceivers() {
/* 20 */     ClientPlayNetworking.registerGlobalReceiver(MorphNetwork.OPEN_SCREEN_TYPE, (payload, context) -> context.client().execute(()));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 26 */     ClientPlayNetworking.registerGlobalReceiver(MorphNetwork.SYNC_TYPE, (payload, context) -> context.client().execute(()));
/*    */ 
/*    */     
/* 29 */     ClientPlayNetworking.registerGlobalReceiver(MorphNetwork.ACTION_RESULT_TYPE, (payload, context) -> context.client().execute(()));
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\morph\network\MorphNetworkHelperClient.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */