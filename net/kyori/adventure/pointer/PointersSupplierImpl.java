/*     */ package net.kyori.adventure.pointer;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.Optional;
/*     */ import java.util.function.Function;
/*     */ import net.kyori.adventure.util.Buildable;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class PointersSupplierImpl<T>
/*     */   implements PointersSupplier<T>
/*     */ {
/*     */   private final PointersSupplier<? super T> parent;
/*     */   private final Map<Pointer<?>, Function<T, ?>> resolvers;
/*     */   
/*     */   PointersSupplierImpl(@NotNull BuilderImpl<T> builder) {
/*  40 */     this.parent = builder.parent;
/*  41 */     this.resolvers = new HashMap<>(builder.resolvers);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public Pointers view(@NotNull T instance) {
/*  46 */     return new ForwardingPointers<>(instance, this);
/*     */   }
/*     */ 
/*     */   
/*     */   public <P> boolean supports(@NotNull Pointer<P> pointer) {
/*  51 */     if (this.resolvers.containsKey(Objects.requireNonNull(pointer, "pointer")))
/*  52 */       return true; 
/*  53 */     if (this.parent == null) {
/*  54 */       return false;
/*     */     }
/*  56 */     return this.parent.supports(pointer);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public <P> Function<? super T, P> resolver(@NotNull Pointer<P> pointer) {
/*  63 */     Function<? super T, ?> resolver = this.resolvers.get(Objects.requireNonNull(pointer, "pointer"));
/*     */     
/*  65 */     if (resolver != null)
/*  66 */       return (Function)resolver; 
/*  67 */     if (this.parent == null) {
/*  68 */       return null;
/*     */     }
/*  70 */     return this.parent.resolver(pointer);
/*     */   }
/*     */   
/*     */   static final class ForwardingPointers<U>
/*     */     implements Pointers {
/*     */     private final U instance;
/*     */     private final PointersSupplierImpl<U> supplier;
/*     */     
/*     */     ForwardingPointers(@NotNull U instance, @NotNull PointersSupplierImpl<U> supplier) {
/*  79 */       this.instance = instance;
/*  80 */       this.supplier = supplier;
/*     */     }
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public <T> Optional<T> get(@NotNull Pointer<T> pointer) {
/*  86 */       Function<? super U, ?> resolver = (Function<? super U, ?>)this.supplier.resolvers.get(Objects.requireNonNull(pointer, "pointer"));
/*     */ 
/*     */       
/*  89 */       if (resolver == null) {
/*  90 */         resolver = this.supplier.parent.resolver(pointer);
/*     */       }
/*     */ 
/*     */       
/*  94 */       if (resolver == null) {
/*  95 */         return Optional.empty();
/*     */       }
/*  97 */       return Optional.ofNullable((T)resolver.apply(this.instance));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public <T> boolean supports(@NotNull Pointer<T> pointer) {
/* 103 */       return this.supplier.supports(pointer);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public Pointers.Builder toBuilder() {
/* 109 */       Pointers.Builder builder = (this.supplier.parent == null) ? Pointers.builder() : (Pointers.Builder)this.supplier.parent.view(this.instance).toBuilder();
/*     */       
/* 111 */       for (Map.Entry<Pointer<?>, Function<U, ?>> entry : (Iterable<Map.Entry<Pointer<?>, Function<U, ?>>>)this.supplier.resolvers.entrySet()) {
/* 112 */         builder.withDynamic(entry.getKey(), () -> ((Function)entry.getValue()).apply(this.instance));
/*     */       }
/*     */       
/* 115 */       return builder;
/*     */     }
/*     */   }
/*     */   
/*     */   static final class BuilderImpl<T> implements PointersSupplier.Builder<T> {
/* 120 */     private PointersSupplier<? super T> parent = null;
/*     */     private final Map<Pointer<?>, Function<T, ?>> resolvers;
/*     */     
/*     */     BuilderImpl() {
/* 124 */       this.resolvers = new HashMap<>();
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public PointersSupplier.Builder<T> parent(@Nullable PointersSupplier<? super T> parent) {
/* 129 */       this.parent = parent;
/* 130 */       return this;
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public <P> PointersSupplier.Builder<T> resolving(@NotNull Pointer<P> pointer, @NotNull Function<T, P> resolver) {
/* 135 */       this.resolvers.put(pointer, resolver);
/* 136 */       return this;
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public PointersSupplier<T> build() {
/* 141 */       return new PointersSupplierImpl<>(this);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\net\kyori\adventure\pointer\PointersSupplierImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */