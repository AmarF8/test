/*    */ package com.atlas.common.fabric.battle.ui;
/*    */ 
/*    */ import net.fabricmc.api.EnvType;
/*    */ import net.fabricmc.api.Environment;
/*    */ import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Environment(EnvType.CLIENT)
/*    */ public final class BattleUiNetworkClient
/*    */ {
/*    */   public static void registerClientReceivers() {
/* 13 */     ClientPlayNetworking.registerGlobalReceiver(BattleUiNetwork.TEAM_TYPE, (payload, context) -> context.client().execute(()));
/*    */ 
/*    */     
/* 16 */     ClientPlayNetworking.registerGlobalReceiver(BattleUiNetwork.INFO_TYPE, (payload, context) -> context.client().execute(()));
/*    */ 
/*    */     
/* 19 */     ClientPlayNetworking.registerGlobalReceiver(BattleUiNetwork.TIMER_TYPE, (payload, context) -> context.client().execute(()));
/*    */ 
/*    */     
/* 22 */     ClientPlayNetworking.registerGlobalReceiver(BattleUiNetwork.HEALTH_TYPE, (payload, context) -> context.client().execute(()));
/*    */ 
/*    */     
/* 25 */     ClientPlayNetworking.registerGlobalReceiver(BattleUiNetwork.CLEAR_TYPE, (payload, context) -> context.client().execute(()));
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\battl\\ui\BattleUiNetworkClient.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */