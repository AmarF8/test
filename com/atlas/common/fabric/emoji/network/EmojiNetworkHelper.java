/*    */ package com.atlas.common.fabric.emoji.network;
/*    */ 
/*    */ import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
/*    */ import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
/*    */ import net.minecraft.class_3222;
/*    */ import net.minecraft.class_8710;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class EmojiNetworkHelper
/*    */ {
/*    */   public static void registerPackets() {
/* 20 */     PayloadTypeRegistry.playS2C().register(EmojiNetwork.SYNC_EMOJI_LIST_TYPE, EmojiNetwork.SYNC_EMOJI_LIST_CODEC);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void registerClientPackets() {
/* 27 */     EmojiNetworkHelperClient.registerClientReceivers();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void sendToPlayer(class_3222 player, class_8710 payload) {
/* 34 */     ServerPlayNetworking.send(player, payload);
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\emoji\network\EmojiNetworkHelper.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */