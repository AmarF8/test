/*    */ package com.atlas.common.fabric.gymcircuit.network;
/*    */ 
/*    */ import com.atlas.common.fabric.gymcircuit.screen.GymCircuitScreen;
/*    */ import net.fabricmc.api.EnvType;
/*    */ import net.fabricmc.api.Environment;
/*    */ import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
/*    */ import net.minecraft.class_310;
/*    */ import net.minecraft.class_437;
/*    */ 
/*    */ 
/*    */ @Environment(EnvType.CLIENT)
/*    */ public final class GymCircuitNetworkHelperClient
/*    */ {
/*    */   public static void registerClientReceivers() {
/* 15 */     ClientPlayNetworking.registerGlobalReceiver(GymCircuitNetwork.OPEN_SCREEN_TYPE, (payload, context) -> context.client().execute(()));
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\gymcircuit\network\GymCircuitNetworkHelperClient.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */