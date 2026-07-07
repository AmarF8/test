/*     */ package com.atlas.common.requirement;
/*     */ 
/*     */ import com.atlas.common.component.BooleanWithText;
/*     */ import com.atlas.common.component.TextComponent;
/*     */ import java.util.Objects;
/*     */ import java.util.function.Function;
/*     */ import net.kyori.adventure.text.Component;
/*     */ import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
/*     */ import org.jetbrains.annotations.NotNull;
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
/*     */ public final class Requirement<T>
/*     */ {
/*     */   private final Component name;
/*     */   private final Component description;
/*     */   private final String namePlain;
/*     */   private final String descriptionPlain;
/*     */   private final Function<T, BooleanWithText> check;
/*     */   
/*     */   public static <R> Requirement<R> of(@NotNull String name, @NotNull String description, @NotNull Function<R, BooleanWithText> check) {
/*  29 */     return new Requirement<>(TextComponent.of(name), TextComponent.of(description), check);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static <R> Requirement<R> of(@NotNull String name, @NotNull Function<R, BooleanWithText> check) {
/*  40 */     return new Requirement<>(TextComponent.of(name), TextComponent.empty(), check);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static <R> Requirement<R> of(@NotNull Function<R, BooleanWithText> check) {
/*  50 */     return new Requirement<>(TextComponent.empty(), TextComponent.empty(), check);
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
/*     */   public static <R> Requirement<R> of(@NotNull Component name, @NotNull Component description, @NotNull Function<R, BooleanWithText> check) {
/*  63 */     return new Requirement<>(name, description, check);
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
/*     */   public static <R> Requirement<R> of(@NotNull Component name, @NotNull Function<R, BooleanWithText> check) {
/*  75 */     return new Requirement<>(name, TextComponent.empty(), check);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Requirement(@NotNull Component name, @NotNull Component description, @NotNull Function<T, BooleanWithText> check) {
/*  93 */     this.name = Objects.<Component>requireNonNull(name);
/*  94 */     this.namePlain = PlainTextComponentSerializer.plainText().serialize(name);
/*  95 */     this.description = Objects.<Component>requireNonNull(description);
/*  96 */     this.descriptionPlain = PlainTextComponentSerializer.plainText().serialize(description);
/*  97 */     this.check = Objects.<Function<T, BooleanWithText>>requireNonNull(check);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Component getName() {
/* 105 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public String getNamePlain() {
/* 113 */     return this.namePlain;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Component getDescription() {
/* 121 */     return this.description;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public String getDescriptionPlain() {
/* 129 */     return this.descriptionPlain;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public BooleanWithText apply(@NotNull T value) {
/* 140 */     return this.check.apply(value);
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\requirement\Requirement.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */