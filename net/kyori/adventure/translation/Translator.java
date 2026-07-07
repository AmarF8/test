/*     */ package net.kyori.adventure.translation;
/*     */ 
/*     */ import java.text.MessageFormat;
/*     */ import java.util.Locale;
/*     */ import net.kyori.adventure.key.Key;
/*     */ import net.kyori.adventure.text.Component;
/*     */ import net.kyori.adventure.text.TranslatableComponent;
/*     */ import net.kyori.adventure.util.TriState;
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
/*     */ public interface Translator
/*     */ {
/*     */   @Nullable
/*     */   static Locale parseLocale(@NotNull String string) {
/*  60 */     String[] segments = string.split("_", 3);
/*  61 */     int length = segments.length;
/*  62 */     if (length == 1)
/*  63 */       return new Locale(string); 
/*  64 */     if (length == 2)
/*  65 */       return new Locale(segments[0], segments[1]); 
/*  66 */     if (length == 3) {
/*  67 */       return new Locale(segments[0], segments[1], segments[2]);
/*     */     }
/*  69 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   Key name();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   default TriState hasAnyTranslations() {
/*  89 */     return TriState.NOT_SET;
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
/*     */   @Nullable
/*     */   MessageFormat translate(@NotNull String paramString, @NotNull Locale paramLocale);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   default Component translate(@NotNull TranslatableComponent component, @NotNull Locale locale) {
/* 114 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\net\kyori\adventure\translation\Translator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */