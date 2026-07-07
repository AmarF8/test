/*    */ package net.kyori.adventure.text;
/*    */ 
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
/*    */ @FunctionalInterface
/*    */ public interface TranslationArgumentLike
/*    */   extends ComponentLike
/*    */ {
/*    */   @NotNull
/*    */   TranslationArgument asTranslationArgument();
/*    */   
/*    */   @NotNull
/*    */   default Component asComponent() {
/* 46 */     return asTranslationArgument().asComponent();
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\net\kyori\adventure\text\TranslationArgumentLike.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */