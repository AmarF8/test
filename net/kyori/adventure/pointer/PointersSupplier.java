/*    */ package net.kyori.adventure.pointer;
/*    */ 
/*    */ import java.util.function.Function;
/*    */ import net.kyori.adventure.builder.AbstractBuilder;
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
/*    */ public interface PointersSupplier<T>
/*    */ {
/*    */   @NotNull
/*    */   static <T> Builder<T> builder() {
/* 65 */     return new PointersSupplierImpl.BuilderImpl<>();
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   Pointers view(@NotNull T paramT);
/*    */   
/*    */   <P> boolean supports(@NotNull Pointer<P> paramPointer);
/*    */   
/*    */   @Nullable
/*    */   <P> Function<? super T, P> resolver(@NotNull Pointer<P> paramPointer);
/*    */   
/*    */   public static interface Builder<T> extends AbstractBuilder<PointersSupplier<T>> {
/*    */     @Contract("_ -> this")
/*    */     @NotNull
/*    */     Builder<T> parent(@Nullable PointersSupplier<? super T> param1PointersSupplier);
/*    */     
/*    */     @Contract("_, _ -> this")
/*    */     @NotNull
/*    */     <P> Builder<T> resolving(@NotNull Pointer<P> param1Pointer, @NotNull Function<T, P> param1Function);
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\net\kyori\adventure\pointer\PointersSupplier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */