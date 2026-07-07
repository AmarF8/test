/*    */ package net.kyori.adventure.text.serializer.plain;
/*    */ 
/*    */ import java.util.function.Function;
/*    */ import net.kyori.adventure.text.KeybindComponent;
/*    */ import net.kyori.adventure.text.TranslatableComponent;
/*    */ import net.kyori.adventure.text.flattener.ComponentFlattener;
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
/*    */ @Deprecated
/*    */ final class PlainComponentSerializerImpl
/*    */ {
/*    */   @Deprecated
/* 35 */   static final PlainComponentSerializer INSTANCE = new PlainComponentSerializer();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   static PlainTextComponentSerializer createRealSerializerFromLegacyFunctions(@Nullable Function<KeybindComponent, String> keybind, @Nullable Function<TranslatableComponent, String> translatable) {
/* 46 */     if (keybind == null && translatable == null) {
/* 47 */       return PlainTextComponentSerializer.plainText();
/*    */     }
/* 49 */     ComponentFlattener.Builder builder = (ComponentFlattener.Builder)ComponentFlattener.basic().toBuilder();
/* 50 */     if (keybind != null) builder.mapper(KeybindComponent.class, keybind); 
/* 51 */     if (translatable != null) builder.mapper(TranslatableComponent.class, translatable); 
/* 52 */     return (PlainTextComponentSerializer)PlainTextComponentSerializer.builder().flattener((ComponentFlattener)builder.build()).build();
/*    */   }
/*    */   
/*    */   @Deprecated
/*    */   static final class BuilderImpl implements PlainComponentSerializer.Builder {
/* 57 */     private final PlainTextComponentSerializer.Builder builder = PlainTextComponentSerializer.builder();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     @Deprecated
/*    */     BuilderImpl(PlainComponentSerializer serializer) {
/* 65 */       this.builder.flattener(((PlainTextComponentSerializerImpl)serializer.serializer).flattener);
/*    */     }
/*    */ 
/*    */     
/*    */     public PlainComponentSerializer.Builder flattener(@NotNull ComponentFlattener flattener) {
/* 70 */       this.builder.flattener(flattener);
/* 71 */       return this;
/*    */     }
/*    */     
/*    */     @NotNull
/*    */     public PlainComponentSerializer build() {
/* 76 */       return new PlainComponentSerializer((PlainTextComponentSerializer)this.builder.build());
/*    */     }
/*    */     
/*    */     @Deprecated
/*    */     BuilderImpl() {}
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\net\kyori\adventure\text\serializer\plain\PlainComponentSerializerImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */