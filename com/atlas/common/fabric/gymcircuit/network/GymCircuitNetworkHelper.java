/*    */ package com.atlas.common.fabric.gymcircuit.network;
/*    */ 
/*    */ import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
/*    */ import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
/*    */ import net.minecraft.class_3222;
/*    */ import net.minecraft.class_8710;
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class GymCircuitNetworkHelper
/*    */ {
/*    */   public static void registerPackets() {
/* 13 */     PayloadTypeRegistry.playS2C().register(GymCircuitNetwork.OPEN_SCREEN_TYPE, GymCircuitNetwork.OPEN_SCREEN_CODEC);
/*    */   }
/*    */   
/*    */   public static void registerClientPackets() {
/* 17 */     GymCircuitNetworkHelperClient.registerClientReceivers();
/*    */   }
/*    */   
/*    */   public static void sendToPlayer(class_3222 player, class_8710 payload) {
/* 21 */     ServerPlayNetworking.send(player, payload);
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\gymcircuit\network\GymCircuitNetworkHelper.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */