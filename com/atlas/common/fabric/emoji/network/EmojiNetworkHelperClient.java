/*    */ package com.atlas.common.fabric.emoji.network;
/*    */ 
/*    */ import com.atlas.common.fabric.emoji.data.EmojiData;
/*    */ import com.google.gson.Gson;
/*    */ import com.google.gson.JsonArray;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.fabricmc.api.EnvType;
/*    */ import net.fabricmc.api.Environment;
/*    */ import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Environment(EnvType.CLIENT)
/*    */ public final class EmojiNetworkHelperClient
/*    */ {
/* 21 */   private static final Gson GSON = new Gson();
/*    */ 
/*    */ 
/*    */   
/*    */   public static void registerClientReceivers() {
/* 26 */     ClientPlayNetworking.registerGlobalReceiver(EmojiNetwork.SYNC_EMOJI_LIST_TYPE, (payload, context) -> context.client().execute(()));
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\emoji\network\EmojiNetworkHelperClient.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */