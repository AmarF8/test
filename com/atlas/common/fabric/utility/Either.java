/*     */ package com.atlas.common.fabric.utility;
/*     */ 
/*     */ import java.util.Objects;
/*     */ import java.util.function.Function;
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
/*     */ public abstract class Either<L, R>
/*     */ {
/*     */   public final boolean isLeft() {
/*  24 */     return this instanceof Left;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final boolean isRight() {
/*  31 */     return this instanceof Right;
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
/*     */   public final <T> T fold(@NotNull Function<? super L, ? extends T> onLeft, @NotNull Function<? super R, ? extends T> onRight) {
/*  44 */     if (this instanceof Left)
/*  45 */       return onLeft.apply(((Left)this).getValue()); 
/*  46 */     if (this instanceof Right) {
/*  47 */       return onRight.apply(((Right)this).getValue());
/*     */     }
/*  49 */     throw new IllegalStateException("Unknown Either type");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static final class Left<L>
/*     */     extends Either<L, Void>
/*     */   {
/*     */     private final L value;
/*     */ 
/*     */ 
/*     */     
/*     */     public Left(@NotNull L value) {
/*  62 */       this.value = value;
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public L getValue() {
/*  67 */       return this.value;
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public L component1() {
/*  72 */       return this.value;
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public Left<L> copy(@NotNull L value) {
/*  77 */       return new Left(value);
/*     */     }
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public String toString() {
/*  83 */       return "Left(value=" + String.valueOf(this.value) + ")";
/*     */     }
/*     */ 
/*     */     
/*     */     public int hashCode() {
/*  88 */       return (this.value == null) ? 0 : this.value.hashCode();
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean equals(@Nullable Object other) {
/*  93 */       if (this == other) {
/*  94 */         return true;
/*     */       }
/*  96 */       if (!(other instanceof Left)) {
/*  97 */         return false;
/*     */       }
/*  99 */       Left<?> that = (Left)other;
/* 100 */       return Objects.equals(this.value, that.value);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static final class Right<R>
/*     */     extends Either<Void, R>
/*     */   {
/*     */     private final R value;
/*     */ 
/*     */     
/*     */     public Right(@NotNull R value) {
/* 113 */       this.value = value;
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public R getValue() {
/* 118 */       return this.value;
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public R component1() {
/* 123 */       return this.value;
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public Right<R> copy(@NotNull R value) {
/* 128 */       return new Right(value);
/*     */     }
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public String toString() {
/* 134 */       return "Right(value=" + String.valueOf(this.value) + ")";
/*     */     }
/*     */ 
/*     */     
/*     */     public int hashCode() {
/* 139 */       return (this.value == null) ? 0 : this.value.hashCode();
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean equals(@Nullable Object other) {
/* 144 */       if (this == other) {
/* 145 */         return true;
/*     */       }
/* 147 */       if (!(other instanceof Right)) {
/* 148 */         return false;
/*     */       }
/* 150 */       Right<?> that = (Right)other;
/* 151 */       return Objects.equals(this.value, that.value);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabri\\utility\Either.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */