/*    */ package net.kyori.adventure.builder;
/*    */ 
/*    */ import java.util.function.Consumer;
/*    */ import org.jetbrains.annotations.Contract;
/*    */ import org.jetbrains.annotations.NotNull;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ @FunctionalInterface
/*    */ public interface AbstractBuilder<R>
/*    */ {
/*    */   @Contract(mutates = "param1")
/*    */   @NotNull
/*    */   static <R, B extends AbstractBuilder<R>> R configureAndBuild(@NotNull B builder, @Nullable Consumer<? super B> consumer) {
/* 51 */     if (consumer != null) {
/* 52 */       consumer.accept(builder);
/*    */     }
/* 54 */     return builder.build();
/*    */   }
/*    */   
/*    */   @Contract(value = "-> new", pure = true)
/*    */   @NotNull
/*    */   R build();
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\net\kyori\adventure\builder\AbstractBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */