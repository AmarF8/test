/*    */ package com.atlas.common.fabric.starterguide.network;
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
/*    */ public final class StarterGuideNetworkHelper
/*    */ {
/*    */   public static void registerPackets() {
/* 22 */     PayloadTypeRegistry.playC2S().register(StarterGuideNetwork.TOGGLE_GUIDE_TYPE, StarterGuideNetwork.TOGGLE_GUIDE_CODEC);
/*    */ 
/*    */     
/* 25 */     PayloadTypeRegistry.playS2C().register(StarterGuideNetwork.SYNC_GUIDE_TYPE, StarterGuideNetwork.SYNC_GUIDE_CODEC);
/* 26 */     PayloadTypeRegistry.playS2C().register(StarterGuideNetwork.STEP_COMPLETE_TYPE, StarterGuideNetwork.STEP_COMPLETE_CODEC);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void registerClientPackets() {
/* 33 */     StarterGuideNetworkHelperClient.registerClientReceivers();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void sendToServer(class_8710 payload) {
/* 40 */     StarterGuideNetworkHelperClient.sendToServer(payload);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void sendToPlayer(class_3222 player, class_8710 payload) {
/* 47 */     ServerPlayNetworking.send(player, payload);
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\starterguide\network\StarterGuideNetworkHelper.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */