/*    */ package net.kyori.adventure.text.format;
/*    */ 
/*    */ import net.kyori.adventure.text.ComponentBuilder;
/*    */ import net.kyori.adventure.text.ComponentBuilderApplicable;
/*    */ import org.jetbrains.annotations.Contract;
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
/*    */ public interface StyleBuilderApplicable
/*    */   extends ComponentBuilderApplicable
/*    */ {
/*    */   @Contract(mutates = "param")
/*    */   void styleApply(Style.Builder paramBuilder);
/*    */   
/*    */   default void componentBuilderApply(@NotNull ComponentBuilder<?, ?> component) {
/* 50 */     component.style(this::styleApply);
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\net\kyori\adventure\text\format\StyleBuilderApplicable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */