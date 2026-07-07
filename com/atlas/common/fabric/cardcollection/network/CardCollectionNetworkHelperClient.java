/*    */ package com.atlas.common.fabric.cardcollection.network;
/*    */ 
/*    */ import com.atlas.common.fabric.cardcollection.client.CardCollectionClientData;
/*    */ import com.atlas.common.fabric.cardcollection.screen.BinderScreen;
/*    */ import com.atlas.common.fabric.cardcollection.screen.CardCollectionScreen;
/*    */ import com.atlas.common.fabric.cardcollection.screen.CardCollectionToast;
/*    */ import com.atlas.common.fabric.cardcollection.screen.CardSkillTreeScreen;
/*    */ import com.atlas.common.fabric.cardcollection.screen.PackOpeningScreen;
/*    */ import net.fabricmc.api.EnvType;
/*    */ import net.fabricmc.api.Environment;
/*    */ import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
/*    */ import net.minecraft.class_310;
/*    */ import net.minecraft.class_437;
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
/*    */ @Environment(EnvType.CLIENT)
/*    */ public final class CardCollectionNetworkHelperClient
/*    */ {
/*    */   public static void sendToServer(class_8710 payload) {
/* 29 */     ClientPlayNetworking.send(payload);
/*    */   }
/*    */ 
/*    */   
/*    */   public static void registerClientReceivers() {
/* 34 */     ClientPlayNetworking.registerGlobalReceiver(CardCollectionNetwork.OPEN_COLLECTION_TYPE, (payload, context) -> context.client().execute(()));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 41 */     ClientPlayNetworking.registerGlobalReceiver(CardCollectionNetwork.SYNC_COLLECTION_TYPE, (payload, context) -> context.client().execute(()));
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 46 */     ClientPlayNetworking.registerGlobalReceiver(CardCollectionNetwork.OPEN_BINDER_TYPE, (payload, context) -> context.client().execute(()));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 55 */     ClientPlayNetworking.registerGlobalReceiver(CardCollectionNetwork.OPEN_PACK_RESULT_TYPE, (payload, context) -> context.client().execute(()));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 62 */     ClientPlayNetworking.registerGlobalReceiver(CardCollectionNetwork.MILESTONE_TYPE, (payload, context) -> context.client().execute(()));
/*    */ 
/*    */ 
/*    */     
/* 66 */     ClientPlayNetworking.registerGlobalReceiver(CardCollectionNetwork.OPEN_SKILL_TREE_TYPE, (payload, context) -> context.client().execute(()));
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\cardcollection\network\CardCollectionNetworkHelperClient.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */