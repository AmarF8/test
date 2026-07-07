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
/*     */ public final class Left<L>
/*     */   extends Either<L, Void>
/*     */ {
/*     */   private final L value;
/*     */   
/*     */   public Left(@NotNull L value) {
/*  62 */     this.value = value;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public L getValue() {
/*  67 */     return this.value;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public L component1() {
/*  72 */     return this.value;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public Left<L> copy(@NotNull L value) {
/*  77 */     return new Left(value);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public String toString() {
/*  83 */     return "Left(value=" + String.valueOf(this.value) + ")";
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/*  88 */     return (this.value == null) ? 0 : this.value.hashCode();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(@Nullable Object other) {
/*  93 */     if (this == other) {
/*  94 */       return true;
/*     */     }
/*  96 */     if (!(other instanceof Left)) {
/*  97 */       return false;
/*     */     }
/*  99 */     Left<?> that = (Left)other;
/* 100 */     return Objects.equals(this.value, that.value);
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabri\\utility\Either$Left.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */