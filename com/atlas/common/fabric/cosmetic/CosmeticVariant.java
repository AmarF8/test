/*    */ package com.atlas.common.fabric.cosmetic;
/*    */ 
/*    */ import com.atlas.common.fabric.utility.Either;
/*    */ import com.google.common.base.Preconditions;
/*    */ import java.util.List;
/*    */ import java.util.Objects;
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
/*    */ public final class CosmeticVariant
/*    */ {
/*    */   @NotNull
/*    */   private final String id;
/*    */   @NotNull
/*    */   private final String name;
/*    */   private final boolean purchaseable;
/*    */   @NotNull
/*    */   private final Either<BedrockCosmeticModel, CustomModelDataCosmeticModel> displayModel;
/*    */   @NotNull
/*    */   private final List<Either<BedrockCosmeticModel, CustomModelDataCosmeticModel>> models;
/*    */   @Nullable
/*    */   private final Integer price;
/*    */   
/*    */   public CosmeticVariant(@NotNull String id, @NotNull String name, boolean purchaseable, @NotNull Either<BedrockCosmeticModel, CustomModelDataCosmeticModel> displayModel, @NotNull List<Either<BedrockCosmeticModel, CustomModelDataCosmeticModel>> models, @Nullable Integer price) {
/* 36 */     Preconditions.checkNotNull(id, "id cannot be null");
/* 37 */     Preconditions.checkNotNull(name, "name cannot be null");
/* 38 */     Preconditions.checkNotNull(displayModel, "displayModel cannot be null");
/* 39 */     Preconditions.checkNotNull(models, "models cannot be null");
/*    */     
/* 41 */     this.id = id;
/* 42 */     this.name = name;
/* 43 */     this.purchaseable = purchaseable;
/* 44 */     this.displayModel = displayModel;
/* 45 */     this.models = models;
/* 46 */     this.price = price;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public String getId() {
/* 51 */     return this.id;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public String getName() {
/* 56 */     return this.name;
/*    */   }
/*    */   
/*    */   public boolean isPurchaseable() {
/* 60 */     return this.purchaseable;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public Either<BedrockCosmeticModel, CustomModelDataCosmeticModel> getDisplayModel() {
/* 65 */     return this.displayModel;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public List<Either<BedrockCosmeticModel, CustomModelDataCosmeticModel>> getModels() {
/* 70 */     return this.models;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public Integer getPrice() {
/* 75 */     return this.price;
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 80 */     return this.id.hashCode();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(@Nullable Object other) {
/* 85 */     if (this == other) {
/* 86 */       return true;
/*    */     }
/* 88 */     if (!(other instanceof CosmeticVariant)) {
/* 89 */       return false;
/*    */     }
/* 91 */     CosmeticVariant that = (CosmeticVariant)other;
/* 92 */     return Objects.equals(this.id, that.id);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 97 */     return "CosmeticVariant{id='" + this.id + "', name='" + this.name + "', purchaseable=" + this.purchaseable + ", price=" + this.price + "}";
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\cosmetic\CosmeticVariant.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */