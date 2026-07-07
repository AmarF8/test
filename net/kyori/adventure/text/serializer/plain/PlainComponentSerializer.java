/*     */ package net.kyori.adventure.text.serializer.plain;
/*     */ 
/*     */ import java.util.function.Function;
/*     */ import net.kyori.adventure.builder.AbstractBuilder;
/*     */ import net.kyori.adventure.text.Component;
/*     */ import net.kyori.adventure.text.KeybindComponent;
/*     */ import net.kyori.adventure.text.TextComponent;
/*     */ import net.kyori.adventure.text.TranslatableComponent;
/*     */ import net.kyori.adventure.text.flattener.ComponentFlattener;
/*     */ import net.kyori.adventure.text.serializer.ComponentSerializer;
/*     */ import net.kyori.adventure.util.Buildable;
/*     */ import org.jetbrains.annotations.ApiStatus.ScheduledForRemoval;
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
/*     */ @Deprecated
/*     */ @ScheduledForRemoval(inVersion = "5.0.0")
/*     */ public class PlainComponentSerializer
/*     */   implements ComponentSerializer<Component, TextComponent, String>, Buildable<PlainComponentSerializer, PlainComponentSerializer.Builder>
/*     */ {
/*     */   @Deprecated
/*     */   final PlainTextComponentSerializer serializer;
/*     */   
/*     */   @Deprecated
/*     */   @ScheduledForRemoval(inVersion = "5.0.0")
/*     */   @NotNull
/*     */   public static PlainComponentSerializer plain() {
/*  63 */     return PlainComponentSerializerImpl.INSTANCE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   @ScheduledForRemoval(inVersion = "5.0.0")
/*     */   public static Builder builder() {
/*  76 */     return new PlainComponentSerializerImpl.BuilderImpl();
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
/*     */   @Deprecated
/*     */   @ScheduledForRemoval(inVersion = "5.0.0")
/*     */   public PlainComponentSerializer() {
/*  90 */     this(PlainTextComponentSerializer.plainText());
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
/*     */   @Deprecated
/*     */   @ScheduledForRemoval(inVersion = "5.0.0")
/*     */   public PlainComponentSerializer(@Nullable Function<KeybindComponent, String> keybind, @Nullable Function<TranslatableComponent, String> translatable) {
/* 104 */     this(PlainComponentSerializerImpl.createRealSerializerFromLegacyFunctions(keybind, translatable));
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   PlainComponentSerializer(@NotNull PlainTextComponentSerializer serializer) {
/* 109 */     this.serializer = serializer;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public TextComponent deserialize(@NotNull String input) {
/* 114 */     return this.serializer.deserialize(input);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public String serialize(@NotNull Component component) {
/* 119 */     return this.serializer.serialize(component);
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
/*     */   @Deprecated
/*     */   @ScheduledForRemoval(inVersion = "5.0.0")
/*     */   public void serialize(@NotNull StringBuilder sb, @NotNull Component component) {
/* 133 */     this.serializer.serialize(sb, component);
/*     */   }
/*     */ 
/*     */   
/*     */   public Builder toBuilder() {
/* 138 */     return new PlainComponentSerializerImpl.BuilderImpl(this);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   @ScheduledForRemoval(inVersion = "5.0.0")
/*     */   public static interface Builder extends AbstractBuilder<PlainComponentSerializer>, Buildable.Builder<PlainComponentSerializer> {
/*     */     @Deprecated
/*     */     @ScheduledForRemoval(inVersion = "5.0.0")
/*     */     @NotNull
/*     */     Builder flattener(@NotNull ComponentFlattener param1ComponentFlattener);
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\net\kyori\adventure\text\serializer\plain\PlainComponentSerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */