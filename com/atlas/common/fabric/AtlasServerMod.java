/*    */ package com.atlas.common.fabric;
/*    */ 
/*    */ import com.atlas.common.fabric.clientverify.ClientVerifyNetwork;
/*    */ import com.atlas.common.fabric.packet.listener.AtlasModServerPacketListener;
/*    */ import net.fabricmc.api.DedicatedServerModInitializer;
/*    */ import net.fabricmc.api.EnvType;
/*    */ import net.fabricmc.api.Environment;
/*    */ import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Environment(EnvType.SERVER)
/*    */ public final class AtlasServerMod
/*    */   implements DedicatedServerModInitializer
/*    */ {
/*    */   public void onInitializeServer() {
/* 23 */     AtlasModServerPacketListener.register();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 30 */     ServerPlayNetworking.registerGlobalReceiver(ClientVerifyNetwork.RequestPayload.ID, (payload, context) -> {
/*    */         
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\AtlasServerMod.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */