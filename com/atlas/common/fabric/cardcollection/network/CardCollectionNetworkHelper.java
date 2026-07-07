/*    */ package com.atlas.common.fabric.cardcollection.network;
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
/*    */ 
/*    */ 
/*    */ public final class CardCollectionNetworkHelper
/*    */ {
/*    */   public static void registerPackets() {
/* 22 */     PayloadTypeRegistry.playC2S().register(CardCollectionNetwork.REQUEST_OVERVIEW_TYPE, CardCollectionNetwork.REQUEST_OVERVIEW_CODEC);
/* 23 */     PayloadTypeRegistry.playC2S().register(CardCollectionNetwork.REQUEST_BINDER_TYPE, CardCollectionNetwork.REQUEST_BINDER_CODEC);
/* 24 */     PayloadTypeRegistry.playC2S().register(CardCollectionNetwork.BURN_CARD_TYPE, CardCollectionNetwork.BURN_CARD_CODEC);
/* 25 */     PayloadTypeRegistry.playC2S().register(CardCollectionNetwork.OPEN_PACK_TYPE, CardCollectionNetwork.OPEN_PACK_CODEC);
/* 26 */     PayloadTypeRegistry.playC2S().register(CardCollectionNetwork.CLOSE_PACK_TYPE, CardCollectionNetwork.CLOSE_PACK_CODEC);
/* 27 */     PayloadTypeRegistry.playC2S().register(CardCollectionNetwork.REQUEST_SKILL_TREE_TYPE, CardCollectionNetwork.REQUEST_SKILL_TREE_CODEC);
/* 28 */     PayloadTypeRegistry.playC2S().register(CardCollectionNetwork.UNLOCK_SKILL_TYPE, CardCollectionNetwork.UNLOCK_SKILL_CODEC);
/*    */ 
/*    */     
/* 31 */     PayloadTypeRegistry.playS2C().register(CardCollectionNetwork.OPEN_COLLECTION_TYPE, CardCollectionNetwork.OPEN_COLLECTION_CODEC);
/* 32 */     PayloadTypeRegistry.playS2C().register(CardCollectionNetwork.SYNC_COLLECTION_TYPE, CardCollectionNetwork.SYNC_COLLECTION_CODEC);
/* 33 */     PayloadTypeRegistry.playS2C().register(CardCollectionNetwork.OPEN_BINDER_TYPE, CardCollectionNetwork.OPEN_BINDER_CODEC);
/* 34 */     PayloadTypeRegistry.playS2C().register(CardCollectionNetwork.OPEN_PACK_RESULT_TYPE, CardCollectionNetwork.OPEN_PACK_RESULT_CODEC);
/* 35 */     PayloadTypeRegistry.playS2C().register(CardCollectionNetwork.MILESTONE_TYPE, CardCollectionNetwork.MILESTONE_CODEC);
/* 36 */     PayloadTypeRegistry.playS2C().register(CardCollectionNetwork.OPEN_SKILL_TREE_TYPE, CardCollectionNetwork.OPEN_SKILL_TREE_CODEC);
/*    */   }
/*    */ 
/*    */   
/*    */   public static void registerClientPackets() {
/* 41 */     CardCollectionNetworkHelperClient.registerClientReceivers();
/*    */   }
/*    */ 
/*    */   
/*    */   public static void sendToServer(class_8710 payload) {
/* 46 */     CardCollectionNetworkHelperClient.sendToServer(payload);
/*    */   }
/*    */ 
/*    */   
/*    */   public static void sendToPlayer(class_3222 player, class_8710 payload) {
/* 51 */     ServerPlayNetworking.send(player, payload);
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\cardcollection\network\CardCollectionNetworkHelper.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */