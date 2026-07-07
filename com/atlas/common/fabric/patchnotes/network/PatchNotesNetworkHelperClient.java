/*    */ package com.atlas.common.fabric.patchnotes.network;
/*    */ 
/*    */ import com.atlas.common.fabric.patchnotes.screen.PatchNotesScreen;
/*    */ import java.util.ArrayList;
/*    */ import net.fabricmc.api.EnvType;
/*    */ import net.fabricmc.api.Environment;
/*    */ import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
/*    */ import net.minecraft.class_310;
/*    */ import net.minecraft.class_437;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Environment(EnvType.CLIENT)
/*    */ public final class PatchNotesNetworkHelperClient
/*    */ {
/*    */   public static void registerClientReceivers() {
/* 20 */     ClientPlayNetworking.registerGlobalReceiver(PatchNotesNetwork.OPEN_SCREEN_TYPE, (payload, context) -> context.client().execute(()));
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\patchnotes\network\PatchNotesNetworkHelperClient.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */