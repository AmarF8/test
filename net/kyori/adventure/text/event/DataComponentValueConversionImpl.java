/*    */ package net.kyori.adventure.text.event;
/*    */ 
/*    */ import java.util.Objects;
/*    */ import java.util.function.BiFunction;
/*    */ import java.util.stream.Stream;
/*    */ import net.kyori.adventure.internal.Internals;
/*    */ import net.kyori.adventure.key.Key;
/*    */ import net.kyori.examination.ExaminableProperty;
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
/*    */ final class DataComponentValueConversionImpl<I, O>
/*    */   implements DataComponentValueConverterRegistry.Conversion<I, O>
/*    */ {
/*    */   private final Class<I> source;
/*    */   private final Class<O> destination;
/*    */   private final BiFunction<Key, I, O> conversion;
/*    */   
/*    */   DataComponentValueConversionImpl(@NotNull Class<I> source, @NotNull Class<O> destination, @NotNull BiFunction<Key, I, O> conversion) {
/* 42 */     this.source = source;
/* 43 */     this.destination = destination;
/* 44 */     this.conversion = conversion;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public Class<I> source() {
/* 49 */     return this.source;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public Class<O> destination() {
/* 54 */     return this.destination;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public O convert(@NotNull Key key, @NotNull I input) {
/* 59 */     return this.conversion.apply(Objects.<Key>requireNonNull(key, "key"), Objects.requireNonNull(input, "input"));
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public Stream<? extends ExaminableProperty> examinableProperties() {
/* 64 */     return Stream.of(new ExaminableProperty[] {
/* 65 */           ExaminableProperty.of("source", this.source), 
/* 66 */           ExaminableProperty.of("destination", this.destination), 
/* 67 */           ExaminableProperty.of("conversion", this.conversion)
/*    */         });
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 73 */     return Internals.toString(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object other) {
/* 78 */     if (this == other) return true; 
/* 79 */     if (other == null || getClass() != other.getClass()) return false; 
/* 80 */     DataComponentValueConversionImpl<?, ?> that = (DataComponentValueConversionImpl<?, ?>)other;
/* 81 */     return (Objects.equals(this.source, that.source) && 
/* 82 */       Objects.equals(this.destination, that.destination) && 
/* 83 */       Objects.equals(this.conversion, that.conversion));
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 88 */     return Objects.hash(new Object[] { this.source, this.destination, this.conversion });
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\net\kyori\adventure\text\event\DataComponentValueConversionImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */