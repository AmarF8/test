/*     */ package com.atlas.common.dictionary;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.Map;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.Objects;
/*     */ import java.util.Optional;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.function.Predicate;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Dictionary<I, O extends DictionaryObject<I>>
/*     */ {
/*     */   private Map<I, O> dictionary;
/*     */   private final boolean isSynchronized;
/*     */   
/*     */   public Dictionary(boolean isSynchronized) {
/*  27 */     this.dictionary = isSynchronized ? new ConcurrentHashMap<>(0) : new LinkedHashMap<>(0);
/*  28 */     this.isSynchronized = isSynchronized;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Collection<O> getContent() {
/*  38 */     return this.dictionary.values();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Collection<O> getContentFiltered(@NotNull Predicate<O> predicate) {
/*  50 */     ArrayList<O> content = new ArrayList<>();
/*  51 */     for (DictionaryObject dictionaryObject : this.dictionary.values()) {
/*  52 */       if (predicate.test((O)dictionaryObject)) content.add((O)dictionaryObject); 
/*     */     } 
/*  54 */     return content;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Optional<O> findByIdentifier(@NotNull I identifier) {
/*  66 */     return Optional.ofNullable(this.dictionary.get(Objects.requireNonNull(identifier)));
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
/*     */   public O findByIdentifierOrThrow(@NotNull I identifier) {
/*  79 */     return findByIdentifier(identifier).<Throwable>orElseThrow(() -> new NoSuchElementException("Object(" + String.valueOf(identifier) + ") not found."));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void add(@NotNull O object) {
/*  90 */     if (this.dictionary.putIfAbsent(object.getIdentifier(), object) != null) {
/*  91 */       throw new IllegalArgumentException("Object already exists.");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void remove(@NotNull O object) {
/* 102 */     if (this.dictionary.remove(object.getIdentifier()) == null) {
/* 103 */       throw new IllegalArgumentException("Object does not exist.");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean removeIf(@NotNull Predicate<O> predicate) {
/* 114 */     return this.dictionary.values().removeIf(predicate);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDictionary(@NotNull Map<I, O> dictionary) {
/* 124 */     this.dictionary = Objects.<Map<I, O>>requireNonNull(dictionary);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void cleanUp() {
/* 131 */     this.dictionary = this.isSynchronized ? new ConcurrentHashMap<>(0) : new LinkedHashMap<>(0);
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\dictionary\Dictionary.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */