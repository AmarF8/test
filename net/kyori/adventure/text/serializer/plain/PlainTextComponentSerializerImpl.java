/*    */ package net.kyori.adventure.text.serializer.plain;
/*    */ 
/*    */ import java.util.Objects;
/*    */ import java.util.Optional;
/*    */ import java.util.function.Consumer;
/*    */ import net.kyori.adventure.text.Component;
/*    */ import net.kyori.adventure.text.flattener.ComponentFlattener;
/*    */ import net.kyori.adventure.util.Buildable;
/*    */ import net.kyori.adventure.util.Services;
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
/*    */ final class PlainTextComponentSerializerImpl
/*    */   implements PlainTextComponentSerializer
/*    */ {
/*    */   private static final ComponentFlattener DEFAULT_FLATTENER;
/*    */   
/*    */   static {
/* 40 */     DEFAULT_FLATTENER = (ComponentFlattener)((ComponentFlattener.Builder)ComponentFlattener.basic().toBuilder()).unknownMapper(component -> { throw new UnsupportedOperationException("Don't know how to turn " + component.getClass().getSimpleName() + " into a string"); }).build();
/* 41 */   } private static final Optional<PlainTextComponentSerializer.Provider> SERVICE = Services.service(PlainTextComponentSerializer.Provider.class);
/* 42 */   static final Consumer<PlainTextComponentSerializer.Builder> BUILDER = SERVICE
/* 43 */     .<Consumer<PlainTextComponentSerializer.Builder>>map(PlainTextComponentSerializer.Provider::plainText)
/* 44 */     .orElseGet(() -> ());
/*    */   
/*    */   final ComponentFlattener flattener;
/*    */ 
/*    */   
/*    */   static final class Instances
/*    */   {
/* 51 */     static final PlainTextComponentSerializer INSTANCE = PlainTextComponentSerializerImpl.SERVICE
/* 52 */       .map(PlainTextComponentSerializer.Provider::plainTextSimple)
/* 53 */       .orElseGet(() -> new PlainTextComponentSerializerImpl(PlainTextComponentSerializerImpl.DEFAULT_FLATTENER));
/*    */   }
/*    */   
/*    */   PlainTextComponentSerializerImpl(ComponentFlattener flattener) {
/* 57 */     this.flattener = flattener;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serialize(@NotNull StringBuilder sb, @NotNull Component component) {
/* 62 */     Objects.requireNonNull(sb); this.flattener.flatten(Objects.<Component>requireNonNull(component, "component"), sb::append);
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public PlainTextComponentSerializer.Builder toBuilder() {
/* 67 */     return new BuilderImpl(this);
/*    */   }
/*    */   
/*    */   static final class BuilderImpl implements PlainTextComponentSerializer.Builder {
/* 71 */     private ComponentFlattener flattener = PlainTextComponentSerializerImpl.DEFAULT_FLATTENER;
/*    */     
/*    */     BuilderImpl() {
/* 74 */       PlainTextComponentSerializerImpl.BUILDER.accept(this);
/*    */     }
/*    */     
/*    */     BuilderImpl(PlainTextComponentSerializerImpl serializer) {
/* 78 */       this();
/* 79 */       this.flattener = serializer.flattener;
/*    */     }
/*    */ 
/*    */     
/*    */     public PlainTextComponentSerializer.Builder flattener(@NotNull ComponentFlattener flattener) {
/* 84 */       this.flattener = Objects.<ComponentFlattener>requireNonNull(flattener, "flattener");
/* 85 */       return this;
/*    */     }
/*    */     
/*    */     @NotNull
/*    */     public PlainTextComponentSerializer build() {
/* 90 */       return new PlainTextComponentSerializerImpl(this.flattener);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\net\kyori\adventure\text\serializer\plain\PlainTextComponentSerializerImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */