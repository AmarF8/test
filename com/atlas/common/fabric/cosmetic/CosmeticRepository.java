/*     */ package com.atlas.common.fabric.cosmetic;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.Set;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class CosmeticRepository
/*     */ {
/*     */   @NotNull
/*  17 */   public static final CosmeticRepository INSTANCE = new CosmeticRepository();
/*     */   
/*     */   @NotNull
/*  20 */   private static final Map<String, Cosmetic> cosmetics = new LinkedHashMap<>();
/*     */   
/*     */   @NotNull
/*  23 */   private static final Set<Cosmetic> unpurchasedCosmetics = new LinkedHashSet<>();
/*     */   @NotNull
/*  25 */   private static final Set<CosmeticVariant> unpurchasedCosmeticVariants = new LinkedHashSet<>();
/*     */   
/*     */   @NotNull
/*  28 */   private static final Set<Cosmetic> invisibleCosmetics = new LinkedHashSet<>();
/*     */   @NotNull
/*  30 */   private static final Set<CosmeticVariant> invisibleCosmeticVariants = new LinkedHashSet<>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Map<String, Cosmetic> getCosmetics() {
/*  37 */     return Collections.unmodifiableMap(cosmetics);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public Set<Cosmetic> getUnpurchasedCosmetics() {
/*  42 */     return Collections.unmodifiableSet(unpurchasedCosmetics);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public Set<Cosmetic> getInvisibleCosmetics() {
/*  47 */     return Collections.unmodifiableSet(invisibleCosmetics);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public Set<CosmeticVariant> getUnpurchasedCosmeticVariants() {
/*  52 */     return Collections.unmodifiableSet(unpurchasedCosmeticVariants);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public Set<CosmeticVariant> getInvisibleCosmeticVariants() {
/*  57 */     return Collections.unmodifiableSet(invisibleCosmeticVariants);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void register(@NotNull Cosmetic cosmetic) {
/*  65 */     Objects.requireNonNull(cosmetic, "cosmetic cannot be null");
/*  66 */     cosmetics.put(cosmetic.getId(), cosmetic);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Cosmetic get(@NotNull String id) {
/*  76 */     Objects.requireNonNull(id, "id cannot be null");
/*  77 */     return cosmetics.get(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/*  84 */     cosmetics.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUnpurchasedCosmetic(@NotNull Cosmetic cosmetic, boolean invisible) {
/*  95 */     Objects.requireNonNull(cosmetic, "cosmetic cannot be null");
/*  96 */     if (invisible) {
/*  97 */       invisibleCosmetics.add(cosmetic);
/*     */     } else {
/*  99 */       unpurchasedCosmetics.add(cosmetic);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUnpurchasedCosmetic(@NotNull Cosmetic cosmetic) {
/* 108 */     setUnpurchasedCosmetic(cosmetic, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUnpurchasedCosmeticVariant(@NotNull CosmeticVariant variant, boolean invisible) {
/* 118 */     Objects.requireNonNull(variant, "variant cannot be null");
/* 119 */     if (invisible) {
/* 120 */       invisibleCosmeticVariants.add(variant);
/*     */     } else {
/* 122 */       unpurchasedCosmeticVariants.add(variant);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUnpurchasedCosmeticVariant(@NotNull CosmeticVariant variant) {
/* 131 */     setUnpurchasedCosmeticVariant(variant, false);
/*     */   }
/*     */   
/*     */   public boolean isUnpurchased(@NotNull Cosmetic cosmetic) {
/* 135 */     Objects.requireNonNull(cosmetic, "cosmetic cannot be null");
/* 136 */     return unpurchasedCosmetics.contains(cosmetic);
/*     */   }
/*     */   
/*     */   public boolean isUnpurchased(@NotNull CosmeticVariant variant) {
/* 140 */     Objects.requireNonNull(variant, "variant cannot be null");
/* 141 */     return unpurchasedCosmeticVariants.contains(variant);
/*     */   }
/*     */   
/*     */   public boolean isInvisible(@NotNull Cosmetic cosmetic) {
/* 145 */     Objects.requireNonNull(cosmetic, "cosmetic cannot be null");
/* 146 */     return invisibleCosmetics.contains(cosmetic);
/*     */   }
/*     */   
/*     */   public boolean isInvisible(@NotNull CosmeticVariant variant) {
/* 150 */     Objects.requireNonNull(variant, "variant cannot be null");
/* 151 */     return invisibleCosmeticVariants.contains(variant);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetUnpurchasedCosmeticItems() {
/* 158 */     unpurchasedCosmetics.clear();
/* 159 */     unpurchasedCosmeticVariants.clear();
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\cosmetic\CosmeticRepository.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */