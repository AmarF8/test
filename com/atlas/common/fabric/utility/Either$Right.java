/*     */ package com.atlas.common.fabric.utility;
/*     */ 
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
/*     */ public final class Right<R>
/*     */   extends Either<Void, R>
/*     */ {
/*     */   private final R value;
/*     */   
/*     */   public Right(@NotNull R value) {
/* 113 */     this.value = value;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public R getValue() {
/* 118 */     return this.value;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public R component1() {
/* 123 */     return this.value;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public Right<R> copy(@NotNull R value) {
/* 128 */     return new Right(value);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public String toString() {
/* 134 */     return "Right(value=" + String.valueOf(this.value) + ")";
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 139 */     return (this.value == null) ? 0 : this.value.hashCode();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(@Nullable Object other) {
/* 144 */     if (this == other) {
/* 145 */       return true;
/*     */     }
/* 147 */     if (!(other instanceof Right)) {
/* 148 */       return false;
/*     */     }
/* 150 */     Right<?> that = (Right)other;
/* 151 */     return Objects.equals(this.value, that.value);
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabri\\utility\Either$Right.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */