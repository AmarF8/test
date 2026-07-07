/*    */ package net.kyori.adventure.text.event;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.kyori.adventure.audience.Audience;
/*    */ import net.kyori.adventure.permission.PermissionChecker;
/*    */ import net.kyori.adventure.util.Services;
/*    */ import net.kyori.adventure.util.TriState;
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
/*    */ final class ClickCallbackInternals
/*    */ {
/* 36 */   static final PermissionChecker ALWAYS_FALSE = PermissionChecker.always(TriState.FALSE);
/*    */   
/* 38 */   static final ClickCallback.Provider PROVIDER = Services.service(ClickCallback.Provider.class)
/* 39 */     .orElseGet(Fallback::new);
/*    */   
/*    */   static final class Fallback implements ClickCallback.Provider {
/*    */     @NotNull
/*    */     public ClickEvent create(@NotNull ClickCallback<Audience> callback, ClickCallback.Options options) {
/* 44 */       return ClickEvent.suggestCommand("Callbacks are not supported on this platform!");
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\net\kyori\adventure\text\event\ClickCallbackInternals.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */