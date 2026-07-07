/*    */ package net.kyori.adventure.audience;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collections;
/*    */ import java.util.UUID;
/*    */ import java.util.function.Consumer;
/*    */ import java.util.function.Supplier;
/*    */ import java.util.stream.Collector;
/*    */ import java.util.stream.Collectors;
/*    */ import net.kyori.adventure.resource.ResourcePackCallback;
/*    */ import net.kyori.adventure.resource.ResourcePackStatus;
/*    */ import net.kyori.adventure.text.ComponentLike;
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
/*    */ public final class Audiences
/*    */ {
/*    */   static final Collector<? super Audience, ?, ForwardingAudience> COLLECTOR;
/*    */   
/*    */   static {
/* 41 */     COLLECTOR = Collectors.collectingAndThen(
/* 42 */         Collectors.toCollection(ArrayList::new), audiences -> Audience.audience(Collections.unmodifiableCollection(audiences)));
/*    */   }
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
/*    */   @NotNull
/*    */   public static Consumer<? super Audience> sendingMessage(@NotNull ComponentLike message) {
/* 57 */     return audience -> audience.sendMessage(message);
/*    */   }
/*    */   @NotNull
/*    */   static ResourcePackCallback unwrapCallback(Audience forwarding, Audience dest, @NotNull ResourcePackCallback cb) {
/* 61 */     if (cb == ResourcePackCallback.noOp()) return cb;
/*    */     
/* 63 */     return (uuid, status, audience) -> cb.packEventReceived(uuid, status, (audience == dest) ? forwarding : audience);
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\net\kyori\adventure\audience\Audiences.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */