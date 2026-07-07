/*    */ package net.kyori.adventure.text.serializer;
/*    */ 
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
/*    */ public interface ComponentDecoder<S, O extends net.kyori.adventure.text.Component>
/*    */ {
/*    */   @NotNull
/*    */   O deserialize(@NotNull S paramS);
/*    */   
/*    */   @Contract(value = "!null -> !null; null -> null", pure = true)
/*    */   @Nullable
/*    */   default O deserializeOrNull(@Nullable S input) {
/* 61 */     return deserializeOr(input, null);
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
/*    */   @Contract(value = "!null, _ -> !null; null, _ -> param2", pure = true)
/*    */   @Nullable
/*    */   default O deserializeOr(@Nullable S input, @Nullable O fallback) {
/* 76 */     if (input == null) return fallback;
/*    */     
/* 78 */     return deserialize(input);
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\net\kyori\adventure\text\serializer\ComponentDecoder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */