/*     */ package net.kyori.adventure.text.serializer;
/*     */ 
/*     */ import org.jetbrains.annotations.ApiStatus.ScheduledForRemoval;
/*     */ import org.jetbrains.annotations.Contract;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public interface ComponentSerializer<I extends net.kyori.adventure.text.Component, O extends net.kyori.adventure.text.Component, R>
/*     */   extends ComponentEncoder<I, R>, ComponentDecoder<R, O>
/*     */ {
/*     */   @NotNull
/*     */   O deserialize(@NotNull R paramR);
/*     */   
/*     */   @Deprecated
/*     */   @ScheduledForRemoval(inVersion = "5.0.0")
/*     */   @Contract(value = "!null -> !null; null -> null", pure = true)
/*     */   @Nullable
/*     */   default O deseializeOrNull(@Nullable R input) {
/*  65 */     return super.deserializeOrNull(input);
/*     */   }
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
/*     */   @Contract(value = "!null -> !null; null -> null", pure = true)
/*     */   @Nullable
/*     */   default O deserializeOrNull(@Nullable R input) {
/*  80 */     return super.deserializeOr(input, null);
/*     */   }
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
/*     */   @Contract(value = "!null, _ -> !null; null, _ -> param2", pure = true)
/*     */   @Nullable
/*     */   default O deserializeOr(@Nullable R input, @Nullable O fallback) {
/*  96 */     return super.deserializeOr(input, fallback);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   R serialize(@NotNull I paramI);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Contract(value = "!null -> !null; null -> null", pure = true)
/*     */   @Nullable
/*     */   default R serializeOrNull(@Nullable I component) {
/* 121 */     return serializeOr(component, null);
/*     */   }
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
/*     */   @Contract(value = "!null, _ -> !null; null, _ -> param2", pure = true)
/*     */   @Nullable
/*     */   default R serializeOr(@Nullable I component, @Nullable R fallback) {
/* 137 */     if (component == null) return fallback;
/*     */     
/* 139 */     return serialize(component);
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\net\kyori\adventure\text\serializer\ComponentSerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */