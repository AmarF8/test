/*    */ package net.kyori.adventure.text;
/*    */ 
/*    */ import java.util.Objects;
/*    */ import net.kyori.examination.Examinable;
/*    */ import org.jetbrains.annotations.ApiStatus.NonExtendable;
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
/*    */ @NonExtendable
/*    */ public interface TranslationArgument
/*    */   extends TranslationArgumentLike, Examinable
/*    */ {
/*    */   @NotNull
/*    */   static TranslationArgument bool(boolean value) {
/* 48 */     return new TranslationArgumentImpl(Boolean.valueOf(value));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   static TranslationArgument numeric(@NotNull Number value) {
/* 60 */     return new TranslationArgumentImpl(Objects.requireNonNull(value, "value"));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   static TranslationArgument component(@NotNull ComponentLike value) {
/* 72 */     if (value instanceof TranslationArgumentLike) return ((TranslationArgumentLike)value).asTranslationArgument(); 
/* 73 */     return new TranslationArgumentImpl(Objects.requireNonNull(((ComponentLike)Objects.<ComponentLike>requireNonNull(value, "value")).asComponent(), "value.asComponent()"));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   Object value();
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   default TranslationArgument asTranslationArgument() {
/* 86 */     return this;
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\net\kyori\adventure\text\TranslationArgument.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */