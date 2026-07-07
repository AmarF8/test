/*    */ package com.atlas.common.fabric.morph.network;
/*    */ 
/*    */ import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
/*    */ import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
/*    */ import net.minecraft.class_3222;
/*    */ import net.minecraft.class_8710;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public final class MorphNetworkHelper
/*    */ {
/*    */   public static void registerPackets() {
/* 12 */     PayloadTypeRegistry.playS2C().register(MorphNetwork.OPEN_SCREEN_TYPE, MorphNetwork.OPEN_SCREEN_CODEC);
/* 13 */     PayloadTypeRegistry.playS2C().register(MorphNetwork.SYNC_TYPE, MorphNetwork.SYNC_CODEC);
/* 14 */     PayloadTypeRegistry.playS2C().register(MorphNetwork.ACTION_RESULT_TYPE, MorphNetwork.ACTION_RESULT_CODEC);
/*    */     
/* 16 */     PayloadTypeRegistry.playC2S().register(MorphNetwork.REQUEST_SYNC_TYPE, MorphNetwork.REQUEST_SYNC_CODEC);
/* 17 */     PayloadTypeRegistry.playC2S().register(MorphNetwork.SELECT_MORPH_TYPE, MorphNetwork.SELECT_MORPH_CODEC);
/* 18 */     PayloadTypeRegistry.playC2S().register(MorphNetwork.UNLOCK_MORPH_TYPE, MorphNetwork.UNLOCK_MORPH_CODEC);
/* 19 */     PayloadTypeRegistry.playC2S().register(MorphNetwork.SPEND_POINT_TYPE, MorphNetwork.SPEND_POINT_CODEC);
/* 20 */     PayloadTypeRegistry.playC2S().register(MorphNetwork.EQUIP_MOVE_TYPE, MorphNetwork.EQUIP_MOVE_CODEC);
/* 21 */     PayloadTypeRegistry.playC2S().register(MorphNetwork.ACTIVATE_MORPH_TYPE, MorphNetwork.ACTIVATE_MORPH_CODEC);
/* 22 */     PayloadTypeRegistry.playC2S().register(MorphNetwork.DEFENSE_ACTION_TYPE, MorphNetwork.DEFENSE_ACTION_CODEC);
/*    */   }
/*    */   
/*    */   public static void registerClientPackets() {
/* 26 */     MorphNetworkHelperClient.registerClientReceivers();
/*    */   }
/*    */   
/*    */   public static void sendToPlayer(@NotNull class_3222 player, @NotNull class_8710 payload) {
/* 30 */     ServerPlayNetworking.send(player, payload);
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\morph\network\MorphNetworkHelper.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */