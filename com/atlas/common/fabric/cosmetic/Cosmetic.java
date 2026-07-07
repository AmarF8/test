/*     */ package com.atlas.common.fabric.cosmetic;
/*     */ 
/*     */ import com.atlas.common.fabric.utility.Either;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
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
/*     */ public final class Cosmetic
/*     */ {
/*     */   @NotNull
/*     */   private final String id;
/*     */   @NotNull
/*     */   private final String name;
/*     */   @NotNull
/*     */   private final CosmeticCategory category;
/*     */   private final boolean purchaseable;
/*     */   @NotNull
/*     */   private final Either<BedrockCosmeticModel, CustomModelDataCosmeticModel> displayModel;
/*     */   @NotNull
/*     */   private final List<Either<BedrockCosmeticModel, CustomModelDataCosmeticModel>> models;
/*     */   @Nullable
/*     */   private final List<CosmeticVariant> variants;
/*     */   @Nullable
/*     */   private final Integer price;
/*     */   
/*     */   public Cosmetic(@NotNull String id, @NotNull String name, @NotNull CosmeticCategory category, boolean purchaseable, @NotNull Either<BedrockCosmeticModel, CustomModelDataCosmeticModel> displayModel, @NotNull List<Either<BedrockCosmeticModel, CustomModelDataCosmeticModel>> models, @Nullable List<CosmeticVariant> variants, @Nullable Integer price) {
/*  36 */     this.id = Objects.<String>requireNonNull(id, "id cannot be null");
/*  37 */     this.name = Objects.<String>requireNonNull(name, "name cannot be null");
/*  38 */     this.category = Objects.<CosmeticCategory>requireNonNull(category, "category cannot be null");
/*  39 */     this.purchaseable = purchaseable;
/*  40 */     this.displayModel = Objects.<Either<BedrockCosmeticModel, CustomModelDataCosmeticModel>>requireNonNull(displayModel, "displayModel cannot be null");
/*  41 */     this.models = Objects.<List<Either<BedrockCosmeticModel, CustomModelDataCosmeticModel>>>requireNonNull(models, "models cannot be null");
/*  42 */     this.variants = variants;
/*  43 */     this.price = price;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public String getId() {
/*  48 */     return this.id;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public String getName() {
/*  53 */     return this.name;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public CosmeticCategory getCategory() {
/*  58 */     return this.category;
/*     */   }
/*     */   
/*     */   public boolean isPurchaseable() {
/*  62 */     return this.purchaseable;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public Either<BedrockCosmeticModel, CustomModelDataCosmeticModel> getDisplayModel() {
/*  67 */     return this.displayModel;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public List<Either<BedrockCosmeticModel, CustomModelDataCosmeticModel>> getModels() {
/*  72 */     return this.models;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public List<CosmeticVariant> getVariants() {
/*  77 */     return this.variants;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public Integer getPrice() {
/*  82 */     return this.price;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/*  87 */     return this.id.hashCode();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(@Nullable Object other) {
/*  92 */     if (this == other) return true; 
/*  93 */     if (!(other instanceof Cosmetic)) return false; 
/*  94 */     Cosmetic that = (Cosmetic)other;
/*  95 */     return Objects.equals(this.id, that.id);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 100 */     return "Cosmetic(id=" + this.id + ", name=" + this.name + ", category=" + String.valueOf(this.category) + ", purchaseable=" + this.purchaseable + ", displayModel=" + String.valueOf(this.displayModel) + ", models=" + String.valueOf(this.models) + ", variants=" + String.valueOf(this.variants) + ", price=" + this.price + ")";
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\cosmetic\Cosmetic.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */