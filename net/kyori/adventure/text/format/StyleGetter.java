/*     */ package net.kyori.adventure.text.format;
/*     */ 
/*     */ import java.util.EnumMap;
/*     */ import java.util.Map;
/*     */ import net.kyori.adventure.key.Key;
/*     */ import net.kyori.adventure.text.event.ClickEvent;
/*     */ import net.kyori.adventure.text.event.HoverEvent;
/*     */ import org.jetbrains.annotations.ApiStatus.NonExtendable;
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
/*     */ @NonExtendable
/*     */ public interface StyleGetter
/*     */ {
/*     */   @Nullable
/*     */   Key font();
/*     */   
/*     */   @Nullable
/*     */   TextColor color();
/*     */   
/*     */   @Nullable
/*     */   ShadowColor shadowColor();
/*     */   
/*     */   default boolean hasDecoration(@NotNull TextDecoration decoration) {
/*  78 */     return (decoration(decoration) == TextDecoration.State.TRUE);
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
/*     */   TextDecoration.State decoration(@NotNull TextDecoration paramTextDecoration);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   default Map<TextDecoration, TextDecoration.State> decorations() {
/* 100 */     Map<TextDecoration, TextDecoration.State> decorations = new EnumMap<>(TextDecoration.class);
/* 101 */     for (int i = 0, length = DecorationMap.DECORATIONS.length; i < length; i++) {
/* 102 */       TextDecoration decoration = DecorationMap.DECORATIONS[i];
/* 103 */       TextDecoration.State value = decoration(decoration);
/* 104 */       decorations.put(decoration, value);
/*     */     } 
/* 106 */     return decorations;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   ClickEvent clickEvent();
/*     */   
/*     */   @Nullable
/*     */   HoverEvent<?> hoverEvent();
/*     */   
/*     */   @Nullable
/*     */   String insertion();
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\net\kyori\adventure\text\format\StyleGetter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */