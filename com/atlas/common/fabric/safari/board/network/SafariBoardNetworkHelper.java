/*    */ package com.atlas.common.fabric.safari.board.network;
/*    */ 
/*    */ import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
/*    */ import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
/*    */ import net.minecraft.class_3222;
/*    */ import net.minecraft.class_8710;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ public final class SafariBoardNetworkHelper
/*    */ {
/*    */   public static void registerPackets() {
/* 13 */     PayloadTypeRegistry.playC2S().register(SafariBoardNetwork.REQUEST_SYNC_TYPE, SafariBoardNetwork.REQUEST_SYNC_CODEC);
/* 14 */     PayloadTypeRegistry.playC2S().register(SafariBoardNetwork.REQUEST_REROLL_TYPE, SafariBoardNetwork.REQUEST_REROLL_CODEC);
/*    */     
/* 16 */     PayloadTypeRegistry.playS2C().register(SafariBoardNetwork.OPEN_SCREEN_TYPE, SafariBoardNetwork.OPEN_SCREEN_CODEC);
/* 17 */     PayloadTypeRegistry.playS2C().register(SafariBoardNetwork.SYNC_TYPE, SafariBoardNetwork.SYNC_CODEC);
/* 18 */     PayloadTypeRegistry.playS2C().register(SafariBoardNetwork.ACTION_RESULT_TYPE, SafariBoardNetwork.ACTION_RESULT_CODEC);
/*    */   }
/*    */   
/*    */   public static void registerClientPackets() {
/* 22 */     SafariBoardNetworkHelperClient.registerClientReceivers();
/*    */   }
/*    */   
/*    */   public static void sendToServer(@NotNull class_8710 payload) {
/* 26 */     SafariBoardNetworkHelperClient.sendToServer(payload);
/*    */   }
/*    */   
/*    */   public static void sendToPlayer(@NotNull class_3222 player, @NotNull class_8710 payload) {
/* 30 */     ServerPlayNetworking.send(player, payload);
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\safari\board\network\SafariBoardNetworkHelper.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */