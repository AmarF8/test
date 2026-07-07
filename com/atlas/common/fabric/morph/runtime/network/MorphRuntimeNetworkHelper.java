/*    */ package com.atlas.common.fabric.morph.runtime.network;
/*    */ 
/*    */ import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
/*    */ import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
/*    */ import net.minecraft.class_3222;
/*    */ import net.minecraft.class_8710;
/*    */ 
/*    */ public class MorphRuntimeNetworkHelper
/*    */ {
/*    */   public static void registerPackets() {
/* 11 */     PayloadTypeRegistry.playC2S().register(MorphRuntimeNetwork.REQUEST_EXIT_TYPE, MorphRuntimeNetwork.REQUEST_EXIT_CODEC);
/* 12 */     PayloadTypeRegistry.playC2S().register(MorphRuntimeNetwork.USE_MOVE_TYPE, MorphRuntimeNetwork.USE_MOVE_CODEC);
/*    */ 
/*    */     
/* 15 */     PayloadTypeRegistry.playS2C().register(MorphRuntimeNetwork.MORPH_STATE_TYPE, MorphRuntimeNetwork.MORPH_STATE_CODEC);
/* 16 */     PayloadTypeRegistry.playS2C().register(MorphRuntimeNetwork.PLAY_MOVE_TYPE, MorphRuntimeNetwork.PLAY_MOVE_CODEC);
/*    */   }
/*    */   
/*    */   public static void registerClientPackets() {
/* 20 */     MorphRuntimeNetworkHelperClient.registerClientReceivers();
/*    */   }
/*    */   
/*    */   public static void sendToPlayer(class_3222 player, class_8710 payload) {
/* 24 */     ServerPlayNetworking.send(player, payload);
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\morph\runtime\network\MorphRuntimeNetworkHelper.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */