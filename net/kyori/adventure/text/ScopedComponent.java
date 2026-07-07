/*     */ package net.kyori.adventure.text;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import java.util.function.Consumer;
/*     */ import net.kyori.adventure.text.event.ClickEvent;
/*     */ import net.kyori.adventure.text.event.HoverEventSource;
/*     */ import net.kyori.adventure.text.format.Style;
/*     */ import net.kyori.adventure.text.format.StyleSetter;
/*     */ import net.kyori.adventure.text.format.TextColor;
/*     */ import net.kyori.adventure.text.format.TextDecoration;
/*     */ import net.kyori.adventure.util.ARGBLike;
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
/*     */ public interface ScopedComponent<C extends Component>
/*     */   extends Component
/*     */ {
/*     */   @NotNull
/*     */   default C style(@NotNull Consumer<Style.Builder> style) {
/*  54 */     return (C)super.style(style);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   default C style(Style.Builder style) {
/*  60 */     return (C)super.style(style);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   default C mergeStyle(@NotNull Component that) {
/*  66 */     return (C)super.mergeStyle(that);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   C mergeStyle(@NotNull Component that, Style.Merge... merges) {
/*  72 */     return (C)super.mergeStyle(that, merges);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   default C append(@NotNull Component component) {
/*  78 */     return (C)super.append(component);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   default C append(@NotNull ComponentLike like) {
/*  84 */     return (C)super.append(like);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   default C append(@NotNull ComponentBuilder<?, ?> builder) {
/*  90 */     return (C)super.append(builder);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   default C mergeStyle(@NotNull Component that, @NotNull Set<Style.Merge> merges) {
/*  96 */     return (C)super.mergeStyle(that, merges);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   default C color(@Nullable TextColor color) {
/* 102 */     return (C)super.color(color);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   default C colorIfAbsent(@Nullable TextColor color) {
/* 108 */     return (C)super.colorIfAbsent(color);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   default C shadowColor(@Nullable ARGBLike argb) {
/* 114 */     return (C)super.shadowColor(argb);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   default C shadowColorIfAbsent(@Nullable ARGBLike argb) {
/* 120 */     return (C)super.shadowColorIfAbsent(argb);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   default C decorate(@NotNull TextDecoration decoration) {
/* 126 */     return (C)super.decorate(decoration);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   default C decoration(@NotNull TextDecoration decoration, boolean flag) {
/* 132 */     return (C)super.decoration(decoration, flag);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   default C decoration(@NotNull TextDecoration decoration, TextDecoration.State state) {
/* 138 */     return (C)super.decoration(decoration, state);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   default C clickEvent(@Nullable ClickEvent event) {
/* 144 */     return (C)super.clickEvent(event);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   default C hoverEvent(@Nullable HoverEventSource<?> event) {
/* 150 */     return (C)super.hoverEvent(event);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   default C insertion(@Nullable String insertion) {
/* 156 */     return (C)super.insertion(insertion);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   C children(@NotNull List<? extends ComponentLike> paramList);
/*     */   
/*     */   @NotNull
/*     */   C style(@NotNull Style paramStyle);
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\net\kyori\adventure\text\ScopedComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */