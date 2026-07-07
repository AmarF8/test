/*      */ package net.kyori.adventure.text;
/*      */ 
/*      */ import java.util.Arrays;
/*      */ import java.util.Collections;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Objects;
/*      */ import java.util.Set;
/*      */ import java.util.Spliterator;
/*      */ import java.util.Spliterators;
/*      */ import java.util.function.BiPredicate;
/*      */ import java.util.function.Consumer;
/*      */ import java.util.function.Function;
/*      */ import java.util.function.Predicate;
/*      */ import java.util.function.UnaryOperator;
/*      */ import java.util.regex.Pattern;
/*      */ import java.util.stream.Collector;
/*      */ import java.util.stream.Stream;
/*      */ import net.kyori.adventure.builder.AbstractBuilder;
/*      */ import net.kyori.adventure.key.Key;
/*      */ import net.kyori.adventure.text.event.ClickEvent;
/*      */ import net.kyori.adventure.text.event.HoverEvent;
/*      */ import net.kyori.adventure.text.event.HoverEventSource;
/*      */ import net.kyori.adventure.text.format.ShadowColor;
/*      */ import net.kyori.adventure.text.format.Style;
/*      */ import net.kyori.adventure.text.format.StyleBuilderApplicable;
/*      */ import net.kyori.adventure.text.format.StyleGetter;
/*      */ import net.kyori.adventure.text.format.StyleSetter;
/*      */ import net.kyori.adventure.text.format.TextColor;
/*      */ import net.kyori.adventure.text.format.TextDecoration;
/*      */ import net.kyori.adventure.translation.Translatable;
/*      */ import net.kyori.adventure.util.ARGBLike;
/*      */ import net.kyori.adventure.util.ForwardingIterator;
/*      */ import net.kyori.adventure.util.IntFunction2;
/*      */ import net.kyori.adventure.util.MonkeyBars;
/*      */ import net.kyori.examination.Examinable;
/*      */ import net.kyori.examination.ExaminableProperty;
/*      */ import org.jetbrains.annotations.ApiStatus.NonExtendable;
/*      */ import org.jetbrains.annotations.ApiStatus.ScheduledForRemoval;
/*      */ import org.jetbrains.annotations.Contract;
/*      */ import org.jetbrains.annotations.NotNull;
/*      */ import org.jetbrains.annotations.Nullable;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ @NonExtendable
/*      */ public interface Component
/*      */   extends ComponentBuilderApplicable, ComponentLike, Examinable, HoverEventSource<Component>, StyleGetter, StyleSetter<Component>
/*      */ {
/*  118 */   public static final BiPredicate<? super Component, ? super Component> EQUALS = Objects::equals;
/*      */   
/*      */   public static final BiPredicate<? super Component, ? super Component> EQUALS_IDENTITY;
/*      */   public static final Predicate<? super Component> IS_NOT_EMPTY;
/*      */   
/*      */   static {
/*  124 */     EQUALS_IDENTITY = ((a, b) -> (a == b));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  130 */     IS_NOT_EMPTY = (component -> (component != empty()));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   static TextComponent empty() {
/*  139 */     return TextComponentImpl.EMPTY;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   static TextComponent newline() {
/*  149 */     return TextComponentImpl.NEWLINE;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   static TextComponent space() {
/*  159 */     return TextComponentImpl.SPACE;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   @ScheduledForRemoval(inVersion = "5.0.0")
/*      */   @Contract(value = "_, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static TextComponent join(@NotNull ComponentLike separator, @NotNull ComponentLike... components) {
/*  175 */     return join(separator, Arrays.asList(components));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   @ScheduledForRemoval(inVersion = "5.0.0")
/*      */   @Contract(value = "_, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static TextComponent join(@NotNull ComponentLike separator, Iterable<? extends ComponentLike> components) {
/*  191 */     Component component = join(JoinConfiguration.separator(separator), components);
/*      */     
/*  193 */     if (component instanceof TextComponent) return (TextComponent)component; 
/*  194 */     return text().append(component).build();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(pure = true)
/*      */   @NotNull
/*      */   static Component join(JoinConfiguration.Builder configBuilder, @NotNull ComponentLike... components) {
/*  210 */     return join(configBuilder, Arrays.asList(components));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(pure = true)
/*      */   @NotNull
/*      */   static Component join(JoinConfiguration.Builder configBuilder, @NotNull Iterable<? extends ComponentLike> components) {
/*  226 */     return JoinConfigurationImpl.join((JoinConfiguration)configBuilder.build(), components);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(pure = true)
/*      */   @NotNull
/*      */   static Component join(@NotNull JoinConfiguration config, @NotNull ComponentLike... components) {
/*  242 */     return join(config, Arrays.asList(components));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(pure = true)
/*      */   @NotNull
/*      */   static Component join(@NotNull JoinConfiguration config, @NotNull Iterable<? extends ComponentLike> components) {
/*  258 */     return JoinConfigurationImpl.join(config, components);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   static Collector<Component, ? extends ComponentBuilder<?, ?>, Component> toComponent() {
/*  268 */     return toComponent(empty());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   static Collector<Component, ? extends ComponentBuilder<?, ?>, Component> toComponent(@NotNull Component separator) {
/*  279 */     return Collector.of(Component::text, (builder, add) -> { if (separator != empty() && !builder.children().isEmpty()) builder.append(separator);  builder.append(add); }(a, b) -> { List<Component> aChildren = a.children(); TextComponent.Builder ret = text().append((Iterable)aChildren); if (!aChildren.isEmpty()) ret.append(separator);  ret.append((Iterable)b.children()); return ret; }ComponentBuilder::build, new Collector.Characteristics[0]);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(pure = true)
/*      */   static BlockNBTComponent.Builder blockNBT() {
/*  313 */     return new BlockNBTComponentImpl.BuilderImpl();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract("_ -> new")
/*      */   @NotNull
/*      */   static BlockNBTComponent blockNBT(@NotNull Consumer<? super BlockNBTComponent.Builder> consumer) {
/*  325 */     return (BlockNBTComponent)AbstractBuilder.configureAndBuild(blockNBT(), consumer);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static BlockNBTComponent blockNBT(@NotNull String nbtPath, BlockNBTComponent.Pos pos) {
/*  338 */     return blockNBT(nbtPath, false, pos);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static BlockNBTComponent blockNBT(@NotNull String nbtPath, boolean interpret, BlockNBTComponent.Pos pos) {
/*  352 */     return blockNBT(nbtPath, interpret, null, pos);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _, _, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static BlockNBTComponent blockNBT(@NotNull String nbtPath, boolean interpret, @Nullable ComponentLike separator, BlockNBTComponent.Pos pos) {
/*  367 */     return BlockNBTComponentImpl.create(Collections.emptyList(), Style.empty(), nbtPath, interpret, separator, pos);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(pure = true)
/*      */   static EntityNBTComponent.Builder entityNBT() {
/*  384 */     return new EntityNBTComponentImpl.BuilderImpl();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract("_ -> new")
/*      */   @NotNull
/*      */   static EntityNBTComponent entityNBT(@NotNull Consumer<? super EntityNBTComponent.Builder> consumer) {
/*  396 */     return (EntityNBTComponent)AbstractBuilder.configureAndBuild(entityNBT(), consumer);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract("_, _ -> new")
/*      */   @NotNull
/*      */   static EntityNBTComponent entityNBT(@NotNull String nbtPath, @NotNull String selector) {
/*  409 */     return entityNBT().nbtPath(nbtPath).selector(selector).build();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(pure = true)
/*      */   static KeybindComponent.Builder keybind() {
/*  426 */     return new KeybindComponentImpl.BuilderImpl();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract("_ -> new")
/*      */   @NotNull
/*      */   static KeybindComponent keybind(@NotNull Consumer<? super KeybindComponent.Builder> consumer) {
/*  438 */     return (KeybindComponent)AbstractBuilder.configureAndBuild(keybind(), consumer);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_ -> new", pure = true)
/*      */   @NotNull
/*      */   static KeybindComponent keybind(@NotNull String keybind) {
/*  450 */     return keybind(keybind, Style.empty());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_ -> new", pure = true)
/*      */   @NotNull
/*      */   static KeybindComponent keybind(KeybindComponent.KeybindLike keybind) {
/*  462 */     return keybind(((KeybindComponent.KeybindLike)Objects.<KeybindComponent.KeybindLike>requireNonNull(keybind, "keybind")).asKeybind(), Style.empty());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static KeybindComponent keybind(@NotNull String keybind, @NotNull Style style) {
/*  475 */     return KeybindComponentImpl.create(Collections.emptyList(), Objects.<Style>requireNonNull(style, "style"), keybind);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static KeybindComponent keybind(KeybindComponent.KeybindLike keybind, @NotNull Style style) {
/*  488 */     return KeybindComponentImpl.create(Collections.emptyList(), Objects.<Style>requireNonNull(style, "style"), ((KeybindComponent.KeybindLike)Objects.<KeybindComponent.KeybindLike>requireNonNull(keybind, "keybind")).asKeybind());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static KeybindComponent keybind(@NotNull String keybind, @Nullable TextColor color) {
/*  501 */     return keybind(keybind, Style.style(color));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static KeybindComponent keybind(KeybindComponent.KeybindLike keybind, @Nullable TextColor color) {
/*  514 */     return keybind(((KeybindComponent.KeybindLike)Objects.<KeybindComponent.KeybindLike>requireNonNull(keybind, "keybind")).asKeybind(), Style.style(color));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static KeybindComponent keybind(@NotNull String keybind, @Nullable TextColor color, TextDecoration... decorations) {
/*  528 */     return keybind(keybind, Style.style(color, decorations));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static KeybindComponent keybind(KeybindComponent.KeybindLike keybind, @Nullable TextColor color, TextDecoration... decorations) {
/*  542 */     return keybind(((KeybindComponent.KeybindLike)Objects.<KeybindComponent.KeybindLike>requireNonNull(keybind, "keybind")).asKeybind(), Style.style(color, decorations));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static KeybindComponent keybind(@NotNull String keybind, @Nullable TextColor color, @NotNull Set<TextDecoration> decorations) {
/*  556 */     return keybind(keybind, Style.style(color, decorations));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static KeybindComponent keybind(KeybindComponent.KeybindLike keybind, @Nullable TextColor color, @NotNull Set<TextDecoration> decorations) {
/*  570 */     return keybind(((KeybindComponent.KeybindLike)Objects.<KeybindComponent.KeybindLike>requireNonNull(keybind, "keybind")).asKeybind(), Style.style(color, decorations));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(pure = true)
/*      */   static ScoreComponent.Builder score() {
/*  587 */     return new ScoreComponentImpl.BuilderImpl();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract("_ -> new")
/*      */   @NotNull
/*      */   static ScoreComponent score(@NotNull Consumer<? super ScoreComponent.Builder> consumer) {
/*  599 */     return (ScoreComponent)AbstractBuilder.configureAndBuild(score(), consumer);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static ScoreComponent score(@NotNull String name, @NotNull String objective) {
/*  612 */     return score(name, objective, null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   @Contract(value = "_, _, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static ScoreComponent score(@NotNull String name, @NotNull String objective, @Nullable String value) {
/*  628 */     return ScoreComponentImpl.create(Collections.emptyList(), Style.empty(), name, objective, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(pure = true)
/*      */   static SelectorComponent.Builder selector() {
/*  645 */     return new SelectorComponentImpl.BuilderImpl();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract("_ -> new")
/*      */   @NotNull
/*      */   static SelectorComponent selector(@NotNull Consumer<? super SelectorComponent.Builder> consumer) {
/*  657 */     return (SelectorComponent)AbstractBuilder.configureAndBuild(selector(), consumer);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_ -> new", pure = true)
/*      */   @NotNull
/*      */   static SelectorComponent selector(@NotNull String pattern) {
/*  669 */     return selector(pattern, null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static SelectorComponent selector(@NotNull String pattern, @Nullable ComponentLike separator) {
/*  682 */     return SelectorComponentImpl.create(Collections.emptyList(), Style.empty(), pattern, separator);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(pure = true)
/*      */   static StorageNBTComponent.Builder storageNBT() {
/*  699 */     return new StorageNBTComponentImpl.BuilderImpl();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract("_ -> new")
/*      */   @NotNull
/*      */   static StorageNBTComponent storageNBT(@NotNull Consumer<? super StorageNBTComponent.Builder> consumer) {
/*  711 */     return (StorageNBTComponent)AbstractBuilder.configureAndBuild(storageNBT(), consumer);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static StorageNBTComponent storageNBT(@NotNull String nbtPath, @NotNull Key storage) {
/*  724 */     return storageNBT(nbtPath, false, storage);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static StorageNBTComponent storageNBT(@NotNull String nbtPath, boolean interpret, @NotNull Key storage) {
/*  738 */     return storageNBT(nbtPath, interpret, null, storage);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _, _, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static StorageNBTComponent storageNBT(@NotNull String nbtPath, boolean interpret, @Nullable ComponentLike separator, @NotNull Key storage) {
/*  753 */     return StorageNBTComponentImpl.create(Collections.emptyList(), Style.empty(), nbtPath, interpret, separator, storage);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(pure = true)
/*      */   static TextComponent.Builder text() {
/*  770 */     return new TextComponentImpl.BuilderImpl();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   static TextComponent textOfChildren(@NotNull ComponentLike... components) {
/*  781 */     if (components.length == 0) return empty(); 
/*  782 */     return TextComponentImpl.create(Arrays.asList(components), Style.empty(), "");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract("_ -> new")
/*      */   @NotNull
/*      */   static TextComponent text(@NotNull Consumer<? super TextComponent.Builder> consumer) {
/*  794 */     return (TextComponent)AbstractBuilder.configureAndBuild(text(), consumer);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_ -> new", pure = true)
/*      */   @NotNull
/*      */   static TextComponent text(@NotNull String content) {
/*  806 */     if (content.isEmpty()) return empty(); 
/*  807 */     return text(content, Style.empty());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static TextComponent text(@NotNull String content, @NotNull Style style) {
/*  820 */     return TextComponentImpl.create(Collections.emptyList(), Objects.<Style>requireNonNull(style, "style"), content);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static TextComponent text(@NotNull String content, @Nullable TextColor color) {
/*  833 */     return text(content, Style.style(color));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static TextComponent text(@NotNull String content, @Nullable TextColor color, TextDecoration... decorations) {
/*  847 */     return text(content, Style.style(color, decorations));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static TextComponent text(@NotNull String content, @Nullable TextColor color, @NotNull Set<TextDecoration> decorations) {
/*  861 */     return text(content, Style.style(color, decorations));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_ -> new", pure = true)
/*      */   @NotNull
/*      */   static TextComponent text(boolean value) {
/*  873 */     return text(String.valueOf(value));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static TextComponent text(boolean value, @NotNull Style style) {
/*  886 */     return text(String.valueOf(value), style);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static TextComponent text(boolean value, @Nullable TextColor color) {
/*  899 */     return text(String.valueOf(value), color);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static TextComponent text(boolean value, @Nullable TextColor color, TextDecoration... decorations) {
/*  913 */     return text(String.valueOf(value), color, decorations);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static TextComponent text(boolean value, @Nullable TextColor color, @NotNull Set<TextDecoration> decorations) {
/*  927 */     return text(String.valueOf(value), color, decorations);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(pure = true)
/*      */   @NotNull
/*      */   static TextComponent text(char value) {
/*  939 */     if (value == '\n') return newline(); 
/*  940 */     if (value == ' ') return space(); 
/*  941 */     return text(String.valueOf(value));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static TextComponent text(char value, @NotNull Style style) {
/*  954 */     return text(String.valueOf(value), style);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static TextComponent text(char value, @Nullable TextColor color) {
/*  967 */     return text(String.valueOf(value), color);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static TextComponent text(char value, @Nullable TextColor color, TextDecoration... decorations) {
/*  981 */     return text(String.valueOf(value), color, decorations);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static TextComponent text(char value, @Nullable TextColor color, @NotNull Set<TextDecoration> decorations) {
/*  995 */     return text(String.valueOf(value), color, decorations);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_ -> new", pure = true)
/*      */   @NotNull
/*      */   static TextComponent text(double value) {
/* 1007 */     return text(String.valueOf(value));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static TextComponent text(double value, @NotNull Style style) {
/* 1020 */     return text(String.valueOf(value), style);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static TextComponent text(double value, @Nullable TextColor color) {
/* 1033 */     return text(String.valueOf(value), color);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static TextComponent text(double value, @Nullable TextColor color, TextDecoration... decorations) {
/* 1047 */     return text(String.valueOf(value), color, decorations);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static TextComponent text(double value, @Nullable TextColor color, @NotNull Set<TextDecoration> decorations) {
/* 1061 */     return text(String.valueOf(value), color, decorations);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_ -> new", pure = true)
/*      */   @NotNull
/*      */   static TextComponent text(float value) {
/* 1073 */     return text(String.valueOf(value));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static TextComponent text(float value, @NotNull Style style) {
/* 1086 */     return text(String.valueOf(value), style);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static TextComponent text(float value, @Nullable TextColor color) {
/* 1099 */     return text(String.valueOf(value), color);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static TextComponent text(float value, @Nullable TextColor color, TextDecoration... decorations) {
/* 1113 */     return text(String.valueOf(value), color, decorations);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static TextComponent text(float value, @Nullable TextColor color, @NotNull Set<TextDecoration> decorations) {
/* 1127 */     return text(String.valueOf(value), color, decorations);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_ -> new", pure = true)
/*      */   @NotNull
/*      */   static TextComponent text(int value) {
/* 1139 */     return text(String.valueOf(value));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static TextComponent text(int value, @NotNull Style style) {
/* 1152 */     return text(String.valueOf(value), style);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static TextComponent text(int value, @Nullable TextColor color) {
/* 1165 */     return text(String.valueOf(value), color);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static TextComponent text(int value, @Nullable TextColor color, TextDecoration... decorations) {
/* 1179 */     return text(String.valueOf(value), color, decorations);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static TextComponent text(int value, @Nullable TextColor color, @NotNull Set<TextDecoration> decorations) {
/* 1193 */     return text(String.valueOf(value), color, decorations);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_ -> new", pure = true)
/*      */   @NotNull
/*      */   static TextComponent text(long value) {
/* 1205 */     return text(String.valueOf(value));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static TextComponent text(long value, @NotNull Style style) {
/* 1218 */     return text(String.valueOf(value), style);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static TextComponent text(long value, @Nullable TextColor color) {
/* 1231 */     return text(String.valueOf(value), color);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static TextComponent text(long value, @Nullable TextColor color, TextDecoration... decorations) {
/* 1245 */     return text(String.valueOf(value), color, decorations);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static TextComponent text(long value, @Nullable TextColor color, @NotNull Set<TextDecoration> decorations) {
/* 1259 */     return text(String.valueOf(value), color, decorations);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static <C> VirtualComponent virtual(@NotNull Class<C> contextType, @NotNull VirtualComponentRenderer<C> renderer) {
/* 1279 */     Objects.requireNonNull(contextType, "context type");
/* 1280 */     Objects.requireNonNull(renderer, "renderer");
/* 1281 */     return VirtualComponentImpl.createVirtual(contextType, renderer);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static <C> VirtualComponent virtual(@NotNull Class<C> contextType, @NotNull VirtualComponentRenderer<C> renderer, @NotNull Style style) {
/* 1296 */     Objects.requireNonNull(contextType, "context type");
/* 1297 */     Objects.requireNonNull(renderer, "renderer");
/* 1298 */     return VirtualComponentImpl.createVirtual(contextType, renderer, Collections.emptyList(), style);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static <C> VirtualComponent virtual(@NotNull Class<C> contextType, @NotNull VirtualComponentRenderer<C> renderer, @NotNull StyleBuilderApplicable... style) {
/* 1313 */     Objects.requireNonNull(contextType, "context type");
/* 1314 */     Objects.requireNonNull(renderer, "renderer");
/* 1315 */     return VirtualComponentImpl.createVirtual(contextType, renderer, Collections.emptyList(), Style.style(style));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static <C> VirtualComponent virtual(@NotNull Class<C> contextType, @NotNull VirtualComponentRenderer<C> renderer, @NotNull Iterable<StyleBuilderApplicable> style) {
/* 1330 */     Objects.requireNonNull(contextType, "context type");
/* 1331 */     Objects.requireNonNull(renderer, "renderer");
/* 1332 */     return VirtualComponentImpl.createVirtual(contextType, renderer, Collections.emptyList(), Style.style(style));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(pure = true)
/*      */   static TranslatableComponent.Builder translatable() {
/* 1349 */     return new TranslatableComponentImpl.BuilderImpl();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract("_ -> new")
/*      */   @NotNull
/*      */   static TranslatableComponent translatable(@NotNull Consumer<? super TranslatableComponent.Builder> consumer) {
/* 1361 */     return (TranslatableComponent)AbstractBuilder.configureAndBuild(translatable(), consumer);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_ -> new", pure = true)
/*      */   @NotNull
/*      */   static TranslatableComponent translatable(@NotNull String key) {
/* 1373 */     return translatable(key, Style.empty());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_ -> new", pure = true)
/*      */   @NotNull
/*      */   static TranslatableComponent translatable(@NotNull Translatable translatable) {
/* 1385 */     return translatable(((Translatable)Objects.<Translatable>requireNonNull(translatable, "translatable")).translationKey(), Style.empty());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static TranslatableComponent translatable(@NotNull String key, @Nullable String fallback) {
/* 1399 */     return translatable(key, fallback, Style.empty());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static TranslatableComponent translatable(@NotNull Translatable translatable, @Nullable String fallback) {
/* 1413 */     return translatable(((Translatable)Objects.<Translatable>requireNonNull(translatable, "translatable")).translationKey(), fallback, Style.empty());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static TranslatableComponent translatable(@NotNull String key, @NotNull Style style) {
/* 1426 */     return TranslatableComponentImpl.create(Collections.emptyList(), Objects.<Style>requireNonNull(style, "style"), key, (String)null, Collections.emptyList());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static TranslatableComponent translatable(@NotNull Translatable translatable, @NotNull Style style) {
/* 1439 */     return translatable(((Translatable)Objects.<Translatable>requireNonNull(translatable, "translatable")).translationKey(), style);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static TranslatableComponent translatable(@NotNull String key, @Nullable String fallback, @NotNull Style style) {
/* 1454 */     return TranslatableComponentImpl.create(Collections.emptyList(), Objects.<Style>requireNonNull(style, "style"), key, fallback, Collections.emptyList());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static TranslatableComponent translatable(@NotNull Translatable translatable, @Nullable String fallback, @NotNull Style style) {
/* 1469 */     return translatable(((Translatable)Objects.<Translatable>requireNonNull(translatable, "translatable")).translationKey(), fallback, style);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static TranslatableComponent translatable(@NotNull String key, @Nullable String fallback, @NotNull StyleBuilderApplicable... style) {
/* 1484 */     return translatable(Objects.<String>requireNonNull(key, "key"), fallback, Style.style(style));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static TranslatableComponent translatable(@NotNull Translatable translatable, @Nullable String fallback, @NotNull Iterable<StyleBuilderApplicable> style) {
/* 1499 */     return translatable(((Translatable)Objects.<Translatable>requireNonNull(translatable, "translatable")).translationKey(), fallback, Style.style(style));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static TranslatableComponent translatable(@NotNull String key, @Nullable String fallback, @NotNull ComponentLike... args) {
/* 1514 */     return translatable(key, fallback, Style.empty(), args);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static TranslatableComponent translatable(@NotNull Translatable translatable, @Nullable String fallback, @NotNull ComponentLike... args) {
/* 1529 */     return translatable(((Translatable)Objects.<Translatable>requireNonNull(translatable, "translatable")).translationKey(), fallback, args);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _, _, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static TranslatableComponent translatable(@NotNull String key, @Nullable String fallback, @NotNull Style style, @NotNull ComponentLike... args) {
/* 1545 */     return TranslatableComponentImpl.create(Collections.emptyList(), Objects.<Style>requireNonNull(style, "style"), key, fallback, Objects.<ComponentLike[]>requireNonNull(args, "args"));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _, _, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static TranslatableComponent translatable(@NotNull Translatable translatable, @Nullable String fallback, @NotNull Style style, @NotNull ComponentLike... args) {
/* 1561 */     return translatable(((Translatable)Objects.<Translatable>requireNonNull(translatable, "translatable")).translationKey(), fallback, style, args);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _, _, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static TranslatableComponent translatable(@NotNull String key, @Nullable String fallback, @NotNull Style style, @NotNull List<? extends ComponentLike> args) {
/* 1577 */     return TranslatableComponentImpl.create(Collections.emptyList(), style, key, fallback, Objects.<List<? extends ComponentLike>>requireNonNull(args, "args"));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _, _, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static TranslatableComponent translatable(@NotNull Translatable translatable, @Nullable String fallback, @NotNull Style style, @NotNull List<? extends ComponentLike> args) {
/* 1593 */     return translatable(((Translatable)Objects.<Translatable>requireNonNull(translatable, "translatable")).translationKey(), fallback, style, args);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _, _, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static TranslatableComponent translatable(@NotNull String key, @Nullable String fallback, @NotNull List<? extends ComponentLike> args, @NotNull Iterable<StyleBuilderApplicable> style) {
/* 1609 */     return TranslatableComponentImpl.create(Collections.emptyList(), Style.style(style), key, fallback, Objects.<List<? extends ComponentLike>>requireNonNull(args, "args"));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _, _, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static TranslatableComponent translatable(@NotNull Translatable translatable, @Nullable String fallback, @NotNull List<? extends ComponentLike> args, @NotNull Iterable<StyleBuilderApplicable> style) {
/* 1625 */     return translatable(((Translatable)Objects.<Translatable>requireNonNull(translatable, "translatable")).translationKey(), fallback, args, style);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _, _, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static TranslatableComponent translatable(@NotNull String key, @Nullable String fallback, @NotNull List<? extends ComponentLike> args, @NotNull StyleBuilderApplicable... style) {
/* 1641 */     return TranslatableComponentImpl.create(Collections.emptyList(), Style.style(style), key, fallback, Objects.<List<? extends ComponentLike>>requireNonNull(args, "args"));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _, _, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static TranslatableComponent translatable(@NotNull Translatable translatable, @Nullable String fallback, @NotNull List<? extends ComponentLike> args, @NotNull StyleBuilderApplicable... style) {
/* 1657 */     return translatable(((Translatable)Objects.<Translatable>requireNonNull(translatable, "translatable")).translationKey(), fallback, args, style);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static TranslatableComponent translatable(@NotNull String key, @Nullable TextColor color) {
/* 1670 */     return translatable(key, Style.style(color));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static TranslatableComponent translatable(@NotNull Translatable translatable, @Nullable TextColor color) {
/* 1683 */     return translatable(((Translatable)Objects.<Translatable>requireNonNull(translatable, "translatable")).translationKey(), color);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static TranslatableComponent translatable(@NotNull String key, @Nullable TextColor color, TextDecoration... decorations) {
/* 1697 */     return translatable(key, Style.style(color, decorations));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static TranslatableComponent translatable(@NotNull Translatable translatable, @Nullable TextColor color, TextDecoration... decorations) {
/* 1711 */     return translatable(((Translatable)Objects.<Translatable>requireNonNull(translatable, "translatable")).translationKey(), color, decorations);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static TranslatableComponent translatable(@NotNull String key, @Nullable TextColor color, @NotNull Set<TextDecoration> decorations) {
/* 1725 */     return translatable(key, Style.style(color, decorations));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static TranslatableComponent translatable(@NotNull Translatable translatable, @Nullable TextColor color, @NotNull Set<TextDecoration> decorations) {
/* 1739 */     return translatable(((Translatable)Objects.<Translatable>requireNonNull(translatable, "translatable")).translationKey(), color, decorations);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static TranslatableComponent translatable(@NotNull String key, @NotNull ComponentLike... args) {
/* 1752 */     return translatable(key, Style.empty(), args);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static TranslatableComponent translatable(@NotNull Translatable translatable, @NotNull ComponentLike... args) {
/* 1765 */     return translatable(((Translatable)Objects.<Translatable>requireNonNull(translatable, "translatable")).translationKey(), args);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static TranslatableComponent translatable(@NotNull String key, @NotNull Style style, @NotNull ComponentLike... args) {
/* 1779 */     return TranslatableComponentImpl.create(Collections.emptyList(), Objects.<Style>requireNonNull(style, "style"), key, (String)null, Objects.<ComponentLike[]>requireNonNull(args, "args"));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static TranslatableComponent translatable(@NotNull Translatable translatable, @NotNull Style style, @NotNull ComponentLike... args) {
/* 1793 */     return translatable(((Translatable)Objects.<Translatable>requireNonNull(translatable, "translatable")).translationKey(), style, args);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static TranslatableComponent translatable(@NotNull String key, @Nullable TextColor color, @NotNull ComponentLike... args) {
/* 1807 */     return translatable(key, Style.style(color), args);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static TranslatableComponent translatable(@NotNull Translatable translatable, @Nullable TextColor color, @NotNull ComponentLike... args) {
/* 1821 */     return translatable(((Translatable)Objects.<Translatable>requireNonNull(translatable, "translatable")).translationKey(), color, args);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _, _, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static TranslatableComponent translatable(@NotNull String key, @Nullable TextColor color, @NotNull Set<TextDecoration> decorations, @NotNull ComponentLike... args) {
/* 1836 */     return translatable(key, Style.style(color, decorations), args);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _, _, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static TranslatableComponent translatable(@NotNull Translatable translatable, @Nullable TextColor color, @NotNull Set<TextDecoration> decorations, @NotNull ComponentLike... args) {
/* 1851 */     return translatable(((Translatable)Objects.<Translatable>requireNonNull(translatable, "translatable")).translationKey(), color, decorations, args);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static TranslatableComponent translatable(@NotNull String key, @NotNull List<? extends ComponentLike> args) {
/* 1864 */     return TranslatableComponentImpl.create(Collections.emptyList(), Style.empty(), key, (String)null, Objects.<List<? extends ComponentLike>>requireNonNull(args, "args"));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static TranslatableComponent translatable(@NotNull Translatable translatable, @NotNull List<? extends ComponentLike> args) {
/* 1877 */     return translatable(((Translatable)Objects.<Translatable>requireNonNull(translatable, "translatable")).translationKey(), args);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static TranslatableComponent translatable(@NotNull String key, @NotNull Style style, @NotNull List<? extends ComponentLike> args) {
/* 1891 */     return TranslatableComponentImpl.create(Collections.emptyList(), Objects.<Style>requireNonNull(style, "style"), key, (String)null, Objects.<List<? extends ComponentLike>>requireNonNull(args, "args"));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static TranslatableComponent translatable(@NotNull Translatable translatable, @NotNull Style style, @NotNull List<? extends ComponentLike> args) {
/* 1905 */     return translatable(((Translatable)Objects.<Translatable>requireNonNull(translatable, "translatable")).translationKey(), style, args);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _, _ -> new", pure = true)
/*      */   static TranslatableComponent translatable(@NotNull String key, @Nullable TextColor color, @NotNull List<? extends ComponentLike> args) {
/* 1919 */     return translatable(key, Style.style(color), args);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _, _ -> new", pure = true)
/*      */   static TranslatableComponent translatable(@NotNull Translatable translatable, @Nullable TextColor color, @NotNull List<? extends ComponentLike> args) {
/* 1933 */     return translatable(((Translatable)Objects.<Translatable>requireNonNull(translatable, "translatable")).translationKey(), color, args);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _, _, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static TranslatableComponent translatable(@NotNull String key, @Nullable TextColor color, @NotNull Set<TextDecoration> decorations, @NotNull List<? extends ComponentLike> args) {
/* 1948 */     return translatable(key, Style.style(color, decorations), args);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(value = "_, _, _, _ -> new", pure = true)
/*      */   @NotNull
/*      */   static TranslatableComponent translatable(@NotNull Translatable translatable, @Nullable TextColor color, @NotNull Set<TextDecoration> decorations, @NotNull List<? extends ComponentLike> args) {
/* 1963 */     return translatable(((Translatable)Objects.<Translatable>requireNonNull(translatable, "translatable")).translationKey(), color, decorations, args);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   default boolean contains(@NotNull Component that) {
/* 1998 */     return contains(that, EQUALS_IDENTITY);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   default boolean contains(@NotNull Component that, @NotNull BiPredicate<? super Component, ? super Component> equals) {
/* 2011 */     if (equals.test(this, that)) return true; 
/* 2012 */     for (Component child : children()) {
/* 2013 */       if (child.contains(that, equals)) return true; 
/*      */     } 
/* 2015 */     HoverEvent<?> hoverEvent = hoverEvent();
/* 2016 */     if (hoverEvent != null) {
/* 2017 */       Object value = hoverEvent.value();
/* 2018 */       Component component = null;
/* 2019 */       if (value instanceof Component) {
/* 2020 */         component = (Component)hoverEvent.value();
/* 2021 */       } else if (value instanceof HoverEvent.ShowEntity) {
/* 2022 */         component = ((HoverEvent.ShowEntity)value).name();
/*      */       } 
/* 2024 */       if (component != null) {
/* 2025 */         if (equals.test(that, component)) return true; 
/* 2026 */         for (Component child : component.children()) {
/* 2027 */           if (child.contains(that, equals)) return true; 
/*      */         } 
/*      */       } 
/*      */     } 
/* 2031 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   @ScheduledForRemoval(inVersion = "5.0.0")
/*      */   default void detectCycle(@NotNull Component that) {
/* 2044 */     if (that.contains(this)) {
/* 2045 */       throw new IllegalStateException("Component cycle detected between " + this + " and " + that);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(pure = true)
/*      */   @NotNull
/*      */   default Component append(@NotNull Component component) {
/* 2058 */     return append(component);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   default Component append(@NotNull ComponentLike like) {
/* 2069 */     Objects.requireNonNull(like, "like");
/* 2070 */     Component component = like.asComponent();
/* 2071 */     Objects.requireNonNull(component, "component");
/* 2072 */     if (component == empty()) return this; 
/* 2073 */     List<Component> oldChildren = children();
/* 2074 */     return children(MonkeyBars.addOne(oldChildren, component));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(pure = true)
/*      */   @NotNull
/*      */   default Component append(@NotNull ComponentBuilder<?, ?> builder) {
/* 2086 */     return append((Component)builder.build());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(pure = true)
/*      */   @NotNull
/*      */   default Component appendNewline() {
/* 2097 */     return append(newline());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(pure = true)
/*      */   @NotNull
/*      */   default Component appendSpace() {
/* 2108 */     return append(space());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(pure = true)
/*      */   @NotNull
/*      */   default Component applyFallbackStyle(@NotNull Style style) {
/* 2122 */     Objects.requireNonNull(style, "style");
/* 2123 */     return style(style().merge(style, Style.Merge.Strategy.IF_ABSENT_ON_TARGET));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(pure = true)
/*      */   @NotNull
/*      */   Component applyFallbackStyle(@NotNull StyleBuilderApplicable... style) {
/* 2137 */     return applyFallbackStyle(Style.style(style));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(pure = true)
/*      */   @NotNull
/*      */   default Component style(@NotNull Consumer<Style.Builder> consumer) {
/* 2167 */     return style(style().edit(consumer));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(pure = true)
/*      */   @NotNull
/*      */   default Component style(@NotNull Consumer<Style.Builder> consumer, Style.Merge.Strategy strategy) {
/* 2180 */     return style(style().edit(consumer, strategy));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(pure = true)
/*      */   @NotNull
/*      */   default Component style(Style.Builder style) {
/* 2192 */     return style(style.build());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(pure = true)
/*      */   @NotNull
/*      */   default Component mergeStyle(@NotNull Component that) {
/* 2204 */     return mergeStyle(that, Style.Merge.all());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(pure = true)
/*      */   @NotNull
/*      */   Component mergeStyle(@NotNull Component that, Style.Merge... merges) {
/* 2217 */     return mergeStyle(that, Style.Merge.merges(merges));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(pure = true)
/*      */   @NotNull
/*      */   default Component mergeStyle(@NotNull Component that, @NotNull Set<Style.Merge> merges) {
/* 2230 */     return style(style().merge(that.style(), merges));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   default Key font() {
/* 2241 */     return style().font();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   default Component font(@Nullable Key key) {
/* 2253 */     return style(style().font(key));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   default TextColor color() {
/* 2264 */     return style().color();
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   default ShadowColor shadowColor() {
/* 2269 */     return style().shadowColor();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(pure = true)
/*      */   @NotNull
/*      */   default Component color(@Nullable TextColor color) {
/* 2282 */     return style(style().color(color));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(pure = true)
/*      */   @NotNull
/*      */   default Component colorIfAbsent(@Nullable TextColor color) {
/* 2295 */     if (color() == null) return color(color); 
/* 2296 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(pure = true)
/*      */   @NotNull
/*      */   default Component shadowColor(@Nullable ARGBLike argb) {
/* 2309 */     return style((Style)style().shadowColor(argb));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(pure = true)
/*      */   @NotNull
/*      */   default Component shadowColorIfAbsent(@Nullable ARGBLike argb) {
/* 2322 */     if (shadowColor() == null) return shadowColor(argb); 
/* 2323 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   default boolean hasDecoration(@NotNull TextDecoration decoration) {
/* 2336 */     return super.hasDecoration(decoration);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(pure = true)
/*      */   @NotNull
/*      */   default Component decorate(@NotNull TextDecoration decoration) {
/* 2349 */     return (Component)super.decorate(decoration);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   default TextDecoration.State decoration(@NotNull TextDecoration decoration) {
/* 2363 */     return style().decoration(decoration);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(pure = true)
/*      */   @NotNull
/*      */   default Component decoration(@NotNull TextDecoration decoration, boolean flag) {
/* 2378 */     return (Component)super.decoration(decoration, flag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(pure = true)
/*      */   @NotNull
/*      */   default Component decoration(@NotNull TextDecoration decoration, TextDecoration.State state) {
/* 2395 */     return style(style().decoration(decoration, state));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   default Component decorationIfAbsent(@NotNull TextDecoration decoration, TextDecoration.State state) {
/* 2409 */     Objects.requireNonNull(state, "state");
/*      */     
/* 2411 */     TextDecoration.State oldState = decoration(decoration);
/* 2412 */     if (oldState == TextDecoration.State.NOT_SET) {
/* 2413 */       return style(style().decoration(decoration, state));
/*      */     }
/* 2415 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   default Map<TextDecoration, TextDecoration.State> decorations() {
/* 2426 */     return style().decorations();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(pure = true)
/*      */   @NotNull
/*      */   default Component decorations(@NotNull Map<TextDecoration, TextDecoration.State> decorations) {
/* 2441 */     return style(style().decorations(decorations));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   default ClickEvent clickEvent() {
/* 2452 */     return style().clickEvent();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(pure = true)
/*      */   @NotNull
/*      */   default Component clickEvent(@Nullable ClickEvent event) {
/* 2465 */     return style(style().clickEvent(event));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   default HoverEvent<?> hoverEvent() {
/* 2476 */     return style().hoverEvent();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(pure = true)
/*      */   @NotNull
/*      */   default Component hoverEvent(@Nullable HoverEventSource<?> source) {
/* 2489 */     return style(style().hoverEvent(source));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   default String insertion() {
/* 2500 */     return style().insertion();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(pure = true)
/*      */   @NotNull
/*      */   default Component insertion(@Nullable String insertion) {
/* 2513 */     return style(style().insertion(insertion));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   default boolean hasStyling() {
/* 2524 */     return !style().isEmpty();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(pure = true)
/*      */   @NotNull
/*      */   default Component replaceText(@NotNull Consumer<TextReplacementConfig.Builder> configurer) {
/* 2536 */     Objects.requireNonNull(configurer, "configurer");
/* 2537 */     return replaceText((TextReplacementConfig)AbstractBuilder.configureAndBuild(TextReplacementConfig.builder(), configurer));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract(pure = true)
/*      */   @NotNull
/*      */   default Component replaceText(@NotNull TextReplacementConfig config) {
/* 2549 */     Objects.requireNonNull(config, "replacement");
/* 2550 */     if (!(config instanceof TextReplacementConfigImpl)) {
/* 2551 */       throw new IllegalArgumentException("Provided replacement was a custom TextReplacementConfig implementation, which is not supported.");
/*      */     }
/* 2553 */     return TextReplacementRenderer.INSTANCE.render(this, ((TextReplacementConfigImpl)config).createState());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   default Component compact() {
/* 2563 */     return ComponentCompaction.compact(this, null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   Iterable<Component> iterable(@NotNull ComponentIteratorType type, @NotNull ComponentIteratorFlag... flags) {
/* 2575 */     return iterable(type, (flags == null) ? Collections.<ComponentIteratorFlag>emptySet() : MonkeyBars.enumSet(ComponentIteratorFlag.class, (Enum[])flags));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   default Iterable<Component> iterable(@NotNull ComponentIteratorType type, @NotNull Set<ComponentIteratorFlag> flags) {
/* 2587 */     Objects.requireNonNull(type, "type");
/* 2588 */     Objects.requireNonNull(flags, "flags");
/* 2589 */     return (Iterable<Component>)new ForwardingIterator(() -> iterator(type, flags), () -> spliterator(type, flags));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   Iterator<Component> iterator(@NotNull ComponentIteratorType type, @NotNull ComponentIteratorFlag... flags) {
/* 2603 */     return iterator(type, (flags == null) ? Collections.<ComponentIteratorFlag>emptySet() : MonkeyBars.enumSet(ComponentIteratorFlag.class, (Enum[])flags));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   default Iterator<Component> iterator(@NotNull ComponentIteratorType type, @NotNull Set<ComponentIteratorFlag> flags) {
/* 2617 */     return new ComponentIterator(this, Objects.<ComponentIteratorType>requireNonNull(type, "type"), Objects.<Set<ComponentIteratorFlag>>requireNonNull(flags, "flags"));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   Spliterator<Component> spliterator(@NotNull ComponentIteratorType type, @NotNull ComponentIteratorFlag... flags) {
/* 2631 */     return spliterator(type, (flags == null) ? Collections.<ComponentIteratorFlag>emptySet() : MonkeyBars.enumSet(ComponentIteratorFlag.class, (Enum[])flags));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   default Spliterator<Component> spliterator(@NotNull ComponentIteratorType type, @NotNull Set<ComponentIteratorFlag> flags) {
/* 2645 */     return Spliterators.spliteratorUnknownSize(iterator(type, flags), 1296);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   @ScheduledForRemoval(inVersion = "5.0.0")
/*      */   @Contract(pure = true)
/*      */   @NotNull
/*      */   default Component replaceText(@NotNull String search, @Nullable ComponentLike replacement) {
/* 2661 */     return replaceText(b -> b.matchLiteral(search).replacement(replacement));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   @ScheduledForRemoval(inVersion = "5.0.0")
/*      */   @Contract(pure = true)
/*      */   @NotNull
/*      */   default Component replaceText(@NotNull Pattern pattern, @NotNull Function<TextComponent.Builder, ComponentLike> replacement) {
/* 2677 */     return replaceText(b -> b.match(pattern).replacement(replacement));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   @ScheduledForRemoval(inVersion = "5.0.0")
/*      */   @Contract(pure = true)
/*      */   @NotNull
/*      */   default Component replaceFirstText(@NotNull String search, @Nullable ComponentLike replacement) {
/* 2693 */     return replaceText(b -> b.matchLiteral(search).once().replacement(replacement));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   @ScheduledForRemoval(inVersion = "5.0.0")
/*      */   @Contract(pure = true)
/*      */   @NotNull
/*      */   default Component replaceFirstText(@NotNull Pattern pattern, @NotNull Function<TextComponent.Builder, ComponentLike> replacement) {
/* 2709 */     return replaceText(b -> b.match(pattern).once().replacement(replacement));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   @ScheduledForRemoval(inVersion = "5.0.0")
/*      */   @Contract(pure = true)
/*      */   @NotNull
/*      */   default Component replaceText(@NotNull String search, @Nullable ComponentLike replacement, int numberOfReplacements) {
/* 2726 */     return replaceText(b -> b.matchLiteral(search).times(numberOfReplacements).replacement(replacement));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   @ScheduledForRemoval(inVersion = "5.0.0")
/*      */   @Contract(pure = true)
/*      */   @NotNull
/*      */   default Component replaceText(@NotNull Pattern pattern, @NotNull Function<TextComponent.Builder, ComponentLike> replacement, int numberOfReplacements) {
/* 2743 */     return replaceText(b -> b.match(pattern).times(numberOfReplacements).replacement(replacement));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   @ScheduledForRemoval(inVersion = "5.0.0")
/*      */   @Contract(pure = true)
/*      */   @NotNull
/*      */   default Component replaceText(@NotNull String search, @Nullable ComponentLike replacement, @NotNull IntFunction2<PatternReplacementResult> fn) {
/* 2762 */     return replaceText(b -> b.matchLiteral(search).replacement(replacement).condition(fn));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   @ScheduledForRemoval(inVersion = "5.0.0")
/*      */   @Contract(pure = true)
/*      */   @NotNull
/*      */   default Component replaceText(@NotNull Pattern pattern, @NotNull Function<TextComponent.Builder, ComponentLike> replacement, @NotNull IntFunction2<PatternReplacementResult> fn) {
/* 2781 */     return replaceText(b -> b.match(pattern).replacement(replacement).condition(fn));
/*      */   }
/*      */ 
/*      */   
/*      */   default void componentBuilderApply(@NotNull ComponentBuilder<?, ?> component) {
/* 2786 */     component.append(this);
/*      */   }
/*      */   
/*      */   @NotNull
/*      */   default Component asComponent() {
/* 2791 */     return this;
/*      */   }
/*      */   
/*      */   @NotNull
/*      */   default HoverEvent<Component> asHoverEvent(@NotNull UnaryOperator<Component> op) {
/* 2796 */     return HoverEvent.showText(op.apply(this));
/*      */   }
/*      */   
/*      */   @NotNull
/*      */   default Stream<? extends ExaminableProperty> examinableProperties() {
/* 2801 */     return Stream.of(new ExaminableProperty[] {
/* 2802 */           ExaminableProperty.of("style", style()), 
/* 2803 */           ExaminableProperty.of("children", children())
/*      */         });
/*      */   }
/*      */   
/*      */   @NotNull
/*      */   List<Component> children();
/*      */   
/*      */   @Contract(pure = true)
/*      */   @NotNull
/*      */   Component children(@NotNull List<? extends ComponentLike> paramList);
/*      */   
/*      */   @NotNull
/*      */   Style style();
/*      */   
/*      */   @Contract(pure = true)
/*      */   @NotNull
/*      */   Component style(@NotNull Style paramStyle);
/*      */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\net\kyori\adventure\text\Component.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */