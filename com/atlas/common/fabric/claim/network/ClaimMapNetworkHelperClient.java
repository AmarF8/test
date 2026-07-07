/*     */ package com.atlas.common.fabric.claim.network;
/*     */ 
/*     */ import com.atlas.common.fabric.claim.screen.ClaimMapScreen;
/*     */ import net.fabricmc.api.EnvType;
/*     */ import net.fabricmc.api.Environment;
/*     */ import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
/*     */ import net.minecraft.class_310;
/*     */ import net.minecraft.class_437;
/*     */ import net.minecraft.class_8710;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Environment(EnvType.CLIENT)
/*     */ public class ClaimMapNetworkHelperClient
/*     */ {
/*     */   public static void sendToServer(class_8710 payload) {
/*  19 */     ClientPlayNetworking.send(payload);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void registerClientReceivers() {
/*  25 */     ClientPlayNetworking.registerGlobalReceiver(ClaimMapNetwork.OPEN_SCREEN_TYPE, (payload, context) -> context.client().execute(()));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  40 */     ClientPlayNetworking.registerGlobalReceiver(ClaimMapNetwork.SYNC_ADMIN_LOOKUP_TYPE, (payload, context) -> {
/*     */           ClaimMapNetwork.SyncAdminLookupPayload data = payload;
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           context.client().execute(());
/*     */         });
/*     */ 
/*     */ 
/*     */     
/*  51 */     ClientPlayNetworking.registerGlobalReceiver(ClaimMapNetwork.SYNC_MAP_DATA_TYPE, (payload, context) -> {
/*     */           ClaimMapNetwork.SyncMapDataPayload data = payload;
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           context.client().execute(());
/*     */         });
/*     */ 
/*     */ 
/*     */     
/*  62 */     ClientPlayNetworking.registerGlobalReceiver(ClaimMapNetwork.SYNC_PLAYER_DATA_TYPE, (payload, context) -> {
/*     */           ClaimMapNetwork.SyncPlayerDataPayload data = payload;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           context.client().execute(());
/*     */         });
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  75 */     ClientPlayNetworking.registerGlobalReceiver(ClaimMapNetwork.SYNC_CLAIM_DETAIL_TYPE, (payload, context) -> {
/*     */           ClaimMapNetwork.SyncClaimDetailPayload data = payload;
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           context.client().execute(());
/*     */         });
/*     */ 
/*     */ 
/*     */     
/*  86 */     ClientPlayNetworking.registerGlobalReceiver(ClaimMapNetwork.SYNC_PROFILES_TYPE, (payload, context) -> {
/*     */           ClaimMapNetwork.SyncProfilesPayload data = payload;
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           context.client().execute(());
/*     */         });
/*     */ 
/*     */ 
/*     */     
/*  97 */     ClientPlayNetworking.registerGlobalReceiver(ClaimMapNetwork.CLAIM_RESULT_TYPE, (payload, context) -> {
/*     */           ClaimMapNetwork.ClaimResultPayload data = payload;
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           context.client().execute(());
/*     */         });
/*     */ 
/*     */ 
/*     */     
/* 108 */     ClientPlayNetworking.registerGlobalReceiver(ClaimMapNetwork.SYNC_PROFILE_DETAIL_TYPE, (payload, context) -> {
/*     */           ClaimMapNetwork.SyncProfileDetailPayload data = payload;
/*     */           context.client().execute(());
/*     */         });
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\claim\network\ClaimMapNetworkHelperClient.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */