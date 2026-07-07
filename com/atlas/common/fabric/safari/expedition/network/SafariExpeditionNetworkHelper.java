/*    */ package com.atlas.common.fabric.safari.expedition.network;
/*    */ 
/*    */ import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
/*    */ import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
/*    */ import net.minecraft.class_3222;
/*    */ import net.minecraft.class_8710;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ public final class SafariExpeditionNetworkHelper
/*    */ {
/*    */   public static void registerPackets() {
/* 13 */     PayloadTypeRegistry.playC2S().register(SafariExpeditionNetwork.REQUEST_SYNC_TYPE, SafariExpeditionNetwork.REQUEST_SYNC_CODEC);
/* 14 */     PayloadTypeRegistry.playC2S().register(SafariExpeditionNetwork.REQUEST_START_TYPE, SafariExpeditionNetwork.REQUEST_START_CODEC);
/* 15 */     PayloadTypeRegistry.playC2S().register(SafariExpeditionNetwork.REQUEST_OPEN_BUILDER_TYPE, SafariExpeditionNetwork.REQUEST_OPEN_BUILDER_CODEC);
/* 16 */     PayloadTypeRegistry.playC2S().register(SafariExpeditionNetwork.REQUEST_BUILDER_ADD_TYPE, SafariExpeditionNetwork.REQUEST_BUILDER_ADD_CODEC);
/* 17 */     PayloadTypeRegistry.playC2S().register(SafariExpeditionNetwork.REQUEST_BUILDER_REMOVE_TYPE, SafariExpeditionNetwork.REQUEST_BUILDER_REMOVE_CODEC);
/* 18 */     PayloadTypeRegistry.playC2S().register(SafariExpeditionNetwork.REQUEST_BUILDER_CLEAR_TYPE, SafariExpeditionNetwork.REQUEST_BUILDER_CLEAR_CODEC);
/* 19 */     PayloadTypeRegistry.playC2S().register(SafariExpeditionNetwork.REQUEST_BUILDER_CYCLE_BOOSTER_TYPE, SafariExpeditionNetwork.REQUEST_BUILDER_CYCLE_BOOSTER_CODEC);
/* 20 */     PayloadTypeRegistry.playC2S().register(SafariExpeditionNetwork.REQUEST_BUILDER_LAUNCH_TYPE, SafariExpeditionNetwork.REQUEST_BUILDER_LAUNCH_CODEC);
/*    */     
/* 22 */     PayloadTypeRegistry.playS2C().register(SafariExpeditionNetwork.OPEN_SCREEN_TYPE, SafariExpeditionNetwork.OPEN_SCREEN_CODEC);
/* 23 */     PayloadTypeRegistry.playS2C().register(SafariExpeditionNetwork.SYNC_TYPE, SafariExpeditionNetwork.SYNC_CODEC);
/* 24 */     PayloadTypeRegistry.playS2C().register(SafariExpeditionNetwork.BUILDER_OPEN_TYPE, SafariExpeditionNetwork.BUILDER_OPEN_CODEC);
/* 25 */     PayloadTypeRegistry.playS2C().register(SafariExpeditionNetwork.BUILDER_SYNC_TYPE, SafariExpeditionNetwork.BUILDER_SYNC_CODEC);
/* 26 */     PayloadTypeRegistry.playS2C().register(SafariExpeditionNetwork.BUILDER_CLOSE_TYPE, SafariExpeditionNetwork.BUILDER_CLOSE_CODEC);
/* 27 */     PayloadTypeRegistry.playS2C().register(SafariExpeditionNetwork.ACTION_RESULT_TYPE, SafariExpeditionNetwork.ACTION_RESULT_CODEC);
/*    */   }
/*    */   
/*    */   public static void registerClientPackets() {
/* 31 */     SafariExpeditionNetworkHelperClient.registerClientReceivers();
/*    */   }
/*    */   
/*    */   public static void sendToServer(@NotNull class_8710 payload) {
/* 35 */     SafariExpeditionNetworkHelperClient.sendToServer(payload);
/*    */   }
/*    */   
/*    */   public static void sendToPlayer(@NotNull class_3222 player, @NotNull class_8710 payload) {
/* 39 */     ServerPlayNetworking.send(player, payload);
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\safari\expedition\network\SafariExpeditionNetworkHelper.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */