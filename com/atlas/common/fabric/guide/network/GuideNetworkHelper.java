/*    */ package com.atlas.common.fabric.guide.network;
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
/*    */ public final class GuideNetworkHelper
/*    */ {
/*    */   public static void registerPackets() {
/* 21 */     PayloadTypeRegistry.playC2S().register(GuideNetwork.REQUEST_POKEDEX_PAGE_TYPE, GuideNetwork.REQUEST_POKEDEX_PAGE_CODEC);
/* 22 */     PayloadTypeRegistry.playC2S().register(GuideNetwork.REQUEST_POKEMON_DETAIL_TYPE, GuideNetwork.REQUEST_POKEMON_DETAIL_CODEC);
/* 23 */     PayloadTypeRegistry.playC2S().register(GuideNetwork.REQUEST_GUIDE_DATA_TYPE, GuideNetwork.REQUEST_GUIDE_DATA_CODEC);
/* 24 */     PayloadTypeRegistry.playC2S().register(GuideNetwork.TAB_CLICKED_TYPE, GuideNetwork.TAB_CLICKED_CODEC);
/*    */ 
/*    */     
/* 27 */     PayloadTypeRegistry.playS2C().register(GuideNetwork.OPEN_GUIDE_TYPE, GuideNetwork.OPEN_GUIDE_CODEC);
/* 28 */     PayloadTypeRegistry.playS2C().register(GuideNetwork.OPEN_GUIDE_BRANDED_TYPE, GuideNetwork.OPEN_GUIDE_BRANDED_CODEC);
/* 29 */     PayloadTypeRegistry.playS2C().register(GuideNetwork.SYNC_POKEDEX_PAGE_TYPE, GuideNetwork.SYNC_POKEDEX_PAGE_CODEC);
/* 30 */     PayloadTypeRegistry.playS2C().register(GuideNetwork.SYNC_POKEMON_DETAIL_TYPE, GuideNetwork.SYNC_POKEMON_DETAIL_CODEC);
/* 31 */     PayloadTypeRegistry.playS2C().register(GuideNetwork.SYNC_GUIDE_DATA_TYPE, GuideNetwork.SYNC_GUIDE_DATA_CODEC);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void registerClientPackets() {
/* 38 */     GuideNetworkHelperClient.registerClientReceivers();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void sendToServer(class_8710 payload) {
/* 45 */     GuideNetworkHelperClient.sendToServer(payload);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void sendToPlayer(class_3222 player, class_8710 payload) {
/* 52 */     ServerPlayNetworking.send(player, payload);
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\guide\network\GuideNetworkHelper.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */