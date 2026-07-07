/*    */ package com.atlas.common.fabric.cosmetic;
/*    */ 
/*    */ import java.util.Objects;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ public final class EquippedCosmetic
/*    */ {
/*    */   @NotNull
/*    */   private final Cosmetic cosmetic;
/*    */   @Nullable
/*    */   private final CosmeticVariant variant;
/*    */   
/*    */   public EquippedCosmetic(@NotNull Cosmetic cosmetic, @Nullable CosmeticVariant variant) {
/* 16 */     this.cosmetic = Objects.<Cosmetic>requireNonNull(cosmetic, "cosmetic cannot be null");
/* 17 */     this.variant = variant;
/*    */   }
/*    */   
/*    */   public EquippedCosmetic(@NotNull Cosmetic cosmetic) {
/* 21 */     this(cosmetic, null);
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public Cosmetic getCosmetic() {
/* 26 */     return this.cosmetic;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public CosmeticVariant getVariant() {
/* 31 */     return this.variant;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 36 */     return "EquippedCosmetic(cosmetic=" + String.valueOf(this.cosmetic) + ", variant=" + String.valueOf(this.variant) + ")";
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 41 */     return Objects.hash(new Object[] { this.cosmetic, this.variant });
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(@Nullable Object other) {
/* 46 */     if (this == other) {
/* 47 */       return true;
/*    */     }
/* 49 */     if (!(other instanceof EquippedCosmetic)) {
/* 50 */       return false;
/*    */     }
/* 52 */     EquippedCosmetic that = (EquippedCosmetic)other;
/* 53 */     return (Objects.equals(this.cosmetic, that.cosmetic) && 
/* 54 */       Objects.equals(this.variant, that.variant));
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\cosmetic\EquippedCosmetic.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */