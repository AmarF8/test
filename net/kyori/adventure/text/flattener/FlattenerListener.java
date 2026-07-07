/*    */ package net.kyori.adventure.text.flattener;
/*    */ 
/*    */ import net.kyori.adventure.text.format.Style;
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
/*    */ public interface FlattenerListener
/*    */ {
/*    */   default void pushStyle(@NotNull Style style) {}
/*    */   
/*    */   void component(@NotNull String paramString);
/*    */   
/*    */   default boolean shouldContinue() {
/* 60 */     return true;
/*    */   }
/*    */   
/*    */   default void popStyle(@NotNull Style style) {}
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\net\kyori\adventure\text\flattener\FlattenerListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */