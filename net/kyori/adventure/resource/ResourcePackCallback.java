/*    */ package net.kyori.adventure.resource;
/*    */ 
/*    */ import java.util.UUID;
/*    */ import java.util.function.BiConsumer;
/*    */ import net.kyori.adventure.audience.Audience;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @FunctionalInterface
/*    */ public interface ResourcePackCallback
/*    */ {
/*    */   @NotNull
/*    */   static ResourcePackCallback noOp() {
/* 47 */     return ResourcePackCallbacks.NO_OP;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   static ResourcePackCallback onTerminal(@NotNull BiConsumer<UUID, Audience> success, @NotNull BiConsumer<UUID, Audience> failure) {
/* 59 */     return (uuid, status, audience) -> {
/*    */         if (status == ResourcePackStatus.SUCCESSFULLY_LOADED) {
/*    */           success.accept(uuid, audience);
/*    */         } else if (!status.intermediate()) {
/*    */           failure.accept(uuid, audience);
/*    */         } 
/*    */       };
/*    */   }
/*    */   
/*    */   void packEventReceived(@NotNull UUID paramUUID, @NotNull ResourcePackStatus paramResourcePackStatus, @NotNull Audience paramAudience);
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\net\kyori\adventure\resource\ResourcePackCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */