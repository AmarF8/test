/*    */ package net.kyori.adventure.util;
/*    */ 
/*    */ import net.kyori.adventure.text.Component;
/*    */ import org.jetbrains.annotations.Nullable;
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
/*    */ public interface ComponentMessageThrowable
/*    */ {
/*    */   @Nullable
/*    */   static Component getMessage(@Nullable Throwable throwable) {
/* 44 */     if (throwable instanceof ComponentMessageThrowable) {
/* 45 */       return ((ComponentMessageThrowable)throwable).componentMessage();
/*    */     }
/* 47 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   static Component getOrConvertMessage(@Nullable Throwable throwable) {
/* 59 */     if (throwable instanceof ComponentMessageThrowable)
/* 60 */       return ((ComponentMessageThrowable)throwable).componentMessage(); 
/* 61 */     if (throwable != null) {
/* 62 */       String message = throwable.getMessage();
/* 63 */       if (message != null) {
/* 64 */         return (Component)Component.text(message);
/*    */       }
/*    */     } 
/* 67 */     return null;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   Component componentMessage();
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\net\kyori\adventur\\util\ComponentMessageThrowable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */