/*    */ package com.atlas.common.utility;
/*    */ 
/*    */ import com.atlas.common.AtlasCommon;
/*    */ import com.atlas.common.scheduler.Scheduler;
/*    */ import com.google.gson.JsonParser;
/*    */ import java.io.BufferedReader;
/*    */ import java.io.InputStreamReader;
/*    */ import java.net.URL;
/*    */ import java.util.UUID;
/*    */ import java.util.concurrent.CompletableFuture;
/*    */ import org.jetbrains.annotations.NotNull;
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
/*    */ 
/*    */ 
/*    */ public final class MojangUtility
/*    */ {
/*    */   @NotNull
/*    */   public static CompletableFuture<UUID> fetchIdentifierByName(@NotNull String name) {
/* 30 */     CompletableFuture<UUID> completableFuture = new CompletableFuture<>();
/* 31 */     AtlasCommon.getInstance().getSchedulerProvider().ofAsync(scheduler -> {
/*    */           try {
/*    */             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((new URL("https://api.mojang.com/users/profiles/minecraft/" + name)).openStream()));
/*    */             String uuid = (new JsonParser()).parse(bufferedReader).getAsJsonObject().get("id").getAsString();
/*    */             uuid = uuid.replaceAll("(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})", "$1-$2-$3-$4-$5");
/*    */             bufferedReader.close();
/*    */             completableFuture.complete(UUID.fromString(uuid));
/* 38 */           } catch (Exception exception) {
/*    */             throw new RuntimeException("Failed to get UUID for " + name, exception);
/*    */           } 
/*    */         });
/* 42 */     return completableFuture;
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\commo\\utility\MojangUtility.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */