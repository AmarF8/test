/*    */ package net.kyori.adventure.util;
/*    */ 
/*    */ import net.kyori.adventure.builder.AbstractBuilder;
/*    */ import org.jetbrains.annotations.CheckReturnValue;
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
/*    */ public interface InheritanceAwareMap<C, V>
/*    */ {
/*    */   @NotNull
/*    */   static <K, E> InheritanceAwareMap<K, E> empty() {
/* 56 */     return InheritanceAwareMapImpl.EMPTY;
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
/*    */   static <K, E> Builder<K, E> builder() {
/* 68 */     return new InheritanceAwareMapImpl.BuilderImpl<>();
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
/*    */   static <K, E> Builder<K, E> builder(InheritanceAwareMap<? extends K, ? extends E> existing) {
/* 81 */     return (new InheritanceAwareMapImpl.BuilderImpl<>())
/* 82 */       .putAll(existing);
/*    */   }
/*    */   
/*    */   boolean containsKey(@NotNull Class<? extends C> paramClass);
/*    */   
/*    */   @Nullable
/*    */   V get(@NotNull Class<? extends C> paramClass);
/*    */   
/*    */   @CheckReturnValue
/*    */   @NotNull
/*    */   InheritanceAwareMap<C, V> with(@NotNull Class<? extends C> paramClass, @NotNull V paramV);
/*    */   
/*    */   @CheckReturnValue
/*    */   @NotNull
/*    */   InheritanceAwareMap<C, V> without(@NotNull Class<? extends C> paramClass);
/*    */   
/*    */   public static interface Builder<C, V> extends AbstractBuilder<InheritanceAwareMap<C, V>> {
/*    */     @NotNull
/*    */     Builder<C, V> strict(boolean param1Boolean);
/*    */     
/*    */     @NotNull
/*    */     Builder<C, V> put(@NotNull Class<? extends C> param1Class, @NotNull V param1V);
/*    */     
/*    */     @NotNull
/*    */     Builder<C, V> remove(@NotNull Class<? extends C> param1Class);
/*    */     
/*    */     @NotNull
/*    */     Builder<C, V> putAll(@NotNull InheritanceAwareMap<? extends C, ? extends V> param1InheritanceAwareMap);
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\net\kyori\adventur\\util\InheritanceAwareMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */