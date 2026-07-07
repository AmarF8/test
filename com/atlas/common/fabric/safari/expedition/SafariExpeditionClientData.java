/*    */ package com.atlas.common.fabric.safari.expedition;
/*    */ 
/*    */ import com.atlas.common.fabric.safari.expedition.network.SafariExpeditionNetwork;
/*    */ import com.google.gson.Gson;
/*    */ import java.util.List;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class SafariExpeditionClientData
/*    */ {
/* 13 */   private static final SafariExpeditionClientData INSTANCE = new SafariExpeditionClientData();
/* 14 */   private static final Gson GSON = new Gson();
/*    */   
/* 16 */   private SafariExpeditionModels.ExpeditionSyncData syncData = new SafariExpeditionModels.ExpeditionSyncData(null, List.of(), List.of(), List.of());
/*    */   private SafariExpeditionModels.ExpeditionBuilderData builderData;
/* 18 */   private String lastMessage = "";
/*    */   private boolean lastMessageSuccess = true;
/* 20 */   private long lastMessageAt = 0L;
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public static SafariExpeditionClientData getInstance() {
/* 26 */     return INSTANCE;
/*    */   }
/*    */   
/*    */   public void applySync(@NotNull SafariExpeditionNetwork.SyncPayload payload) {
/* 30 */     SafariExpeditionModels.ExpeditionSyncData parsed = (SafariExpeditionModels.ExpeditionSyncData)GSON.fromJson(payload.json(), SafariExpeditionModels.ExpeditionSyncData.class);
/* 31 */     this.syncData = (parsed == null) ? new SafariExpeditionModels.ExpeditionSyncData(null, List.of(), List.of(), List.of()) : parsed;
/*    */   }
/*    */   
/*    */   public void applyActionResult(@NotNull SafariExpeditionNetwork.ActionResultPayload payload) {
/* 35 */     this.lastMessageSuccess = payload.success();
/* 36 */     this.lastMessage = payload.message();
/* 37 */     this.lastMessageAt = System.currentTimeMillis();
/*    */   }
/*    */   
/*    */   public void applyBuilderOpen(@NotNull SafariExpeditionNetwork.BuilderOpenPayload payload) {
/* 41 */     this.builderData = (SafariExpeditionModels.ExpeditionBuilderData)GSON.fromJson(payload.json(), SafariExpeditionModels.ExpeditionBuilderData.class);
/*    */   }
/*    */   
/*    */   public void applyBuilderSync(@NotNull SafariExpeditionNetwork.BuilderSyncPayload payload) {
/* 45 */     this.builderData = (SafariExpeditionModels.ExpeditionBuilderData)GSON.fromJson(payload.json(), SafariExpeditionModels.ExpeditionBuilderData.class);
/*    */   }
/*    */   
/*    */   public void clearBuilder() {
/* 49 */     this.builderData = null;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public SafariExpeditionModels.ExpeditionSyncData getSyncData() {
/* 54 */     return this.syncData;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public String getLastMessage() {
/* 59 */     return this.lastMessage;
/*    */   }
/*    */   
/*    */   public boolean isLastMessageSuccess() {
/* 63 */     return this.lastMessageSuccess;
/*    */   }
/*    */   
/*    */   public long getLastMessageAt() {
/* 67 */     return this.lastMessageAt;
/*    */   }
/*    */   
/*    */   public SafariExpeditionModels.ExpeditionBuilderData getBuilderData() {
/* 71 */     return this.builderData;
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\safari\expedition\SafariExpeditionClientData.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */