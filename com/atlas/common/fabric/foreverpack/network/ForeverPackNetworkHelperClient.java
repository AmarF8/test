/*    */ package com.atlas.common.fabric.foreverpack.network;
/*    */ 
/*    */ import com.atlas.common.fabric.foreverpack.data.ForeverPackClientData;
/*    */ import com.atlas.common.fabric.foreverpack.screen.ForeverPackScreen;
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
/*    */ @Environment(EnvType.CLIENT)
/*    */ public class ForeverPackNetworkHelperClient
/*    */ {
/*    */   public static void sendToServer(class_8710 payload) {
/* 21 */     ClientPlayNetworking.send(payload);
/*    */   }
/*    */ 
/*    */   
/*    */   public static void registerClientReceivers() {
/* 26 */     ClientPlayNetworking.registerGlobalReceiver(ForeverPackNetwork.OPEN_SCREEN_TYPE, (payload, context) -> context.client().execute(()));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 36 */     ClientPlayNetworking.registerGlobalReceiver(ForeverPackNetwork.SYNC_TYPE, (payload, context) -> context.client().execute(()));
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 41 */     ClientPlayNetworking.registerGlobalReceiver(ForeverPackNetwork.PURCHASE_RESULT_TYPE, (payload, context) -> context.client().execute(()));
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\foreverpack\network\ForeverPackNetworkHelperClient.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */