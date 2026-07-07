/*     */ package net.kyori.adventure.translation;
/*     */ 
/*     */ import java.text.MessageFormat;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.stream.Stream;
/*     */ import net.kyori.adventure.internal.Internals;
/*     */ import net.kyori.adventure.key.Key;
/*     */ import net.kyori.adventure.util.TriState;
/*     */ import net.kyori.examination.Examinable;
/*     */ import net.kyori.examination.ExaminableProperty;
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
/*     */ final class TranslationRegistryImpl
/*     */   implements Examinable, TranslationRegistry
/*     */ {
/*     */   private final Key name;
/*  44 */   private final Map<String, Translation> translations = new ConcurrentHashMap<>();
/*  45 */   private Locale defaultLocale = Locale.US;
/*     */   
/*     */   TranslationRegistryImpl(Key name) {
/*  48 */     this.name = name;
/*     */   }
/*     */ 
/*     */   
/*     */   public void register(@NotNull String key, @NotNull Locale locale, @NotNull MessageFormat format) {
/*  53 */     ((Translation)this.translations.computeIfAbsent(key, x$0 -> new Translation(x$0))).register(locale, format);
/*     */   }
/*     */ 
/*     */   
/*     */   public void unregister(@NotNull String key) {
/*  58 */     this.translations.remove(key);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public Key name() {
/*  63 */     return this.name;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean contains(@NotNull String key) {
/*  68 */     return this.translations.containsKey(key);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public TriState hasAnyTranslations() {
/*  73 */     if (!this.translations.isEmpty()) {
/*  74 */       return TriState.TRUE;
/*     */     }
/*  76 */     return TriState.FALSE;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public MessageFormat translate(@NotNull String key, @NotNull Locale locale) {
/*  81 */     Translation translation = this.translations.get(key);
/*  82 */     if (translation == null) return null; 
/*  83 */     return translation.translate(locale);
/*     */   }
/*     */ 
/*     */   
/*     */   public void defaultLocale(@NotNull Locale defaultLocale) {
/*  88 */     this.defaultLocale = Objects.<Locale>requireNonNull(defaultLocale, "defaultLocale");
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public Stream<? extends ExaminableProperty> examinableProperties() {
/*  93 */     return Stream.of(ExaminableProperty.of("translations", this.translations));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object other) {
/*  98 */     if (this == other) return true; 
/*  99 */     if (!(other instanceof TranslationRegistryImpl)) return false;
/*     */     
/* 101 */     TranslationRegistryImpl that = (TranslationRegistryImpl)other;
/*     */     
/* 103 */     return (this.name.equals(that.name) && this.translations
/* 104 */       .equals(that.translations) && this.defaultLocale
/* 105 */       .equals(that.defaultLocale));
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 110 */     return Objects.hash(new Object[] { this.name, this.translations, this.defaultLocale });
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 115 */     return Internals.toString(this);
/*     */   }
/*     */   
/*     */   final class Translation implements Examinable {
/*     */     private final String key;
/*     */     private final Map<Locale, MessageFormat> formats;
/*     */     
/*     */     Translation(String key) {
/* 123 */       this.key = Objects.<String>requireNonNull(key, "translation key");
/* 124 */       this.formats = new ConcurrentHashMap<>();
/*     */     }
/*     */     
/*     */     void register(@NotNull Locale locale, @NotNull MessageFormat format) {
/* 128 */       if (this.formats.putIfAbsent(Objects.<Locale>requireNonNull(locale, "locale"), Objects.<MessageFormat>requireNonNull(format, "message format")) != null)
/* 129 */         throw new IllegalArgumentException(String.format("Translation already exists: %s for %s", new Object[] { this.key, locale })); 
/*     */     }
/*     */     
/*     */     @Nullable
/*     */     MessageFormat translate(@NotNull Locale locale) {
/* 134 */       MessageFormat format = this.formats.get(Objects.requireNonNull(locale, "locale"));
/* 135 */       if (format == null) {
/* 136 */         format = this.formats.get(new Locale(locale.getLanguage()));
/* 137 */         if (format == null) {
/* 138 */           format = this.formats.get(TranslationRegistryImpl.this.defaultLocale);
/* 139 */           if (format == null) {
/* 140 */             format = this.formats.get(TranslationLocales.global());
/*     */           }
/*     */         } 
/*     */       } 
/* 144 */       return format;
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public Stream<? extends ExaminableProperty> examinableProperties() {
/* 149 */       return Stream.of(new ExaminableProperty[] {
/* 150 */             ExaminableProperty.of("key", this.key), 
/* 151 */             ExaminableProperty.of("formats", this.formats)
/*     */           });
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean equals(Object other) {
/* 157 */       if (this == other) return true; 
/* 158 */       if (!(other instanceof Translation)) return false; 
/* 159 */       Translation that = (Translation)other;
/* 160 */       return (this.key.equals(that.key) && this.formats
/* 161 */         .equals(that.formats));
/*     */     }
/*     */ 
/*     */     
/*     */     public int hashCode() {
/* 166 */       return Objects.hash(new Object[] { this.key, this.formats });
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 171 */       return Internals.toString(this);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\net\kyori\adventure\translation\TranslationRegistryImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */