/*     */ package com.atlas.common.component;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import net.kyori.adventure.text.Component;
/*     */ import net.kyori.adventure.text.TranslatableComponent;
/*     */ import net.kyori.adventure.text.format.Style;
/*     */ import net.kyori.adventure.text.format.TextColor;
/*     */ import net.kyori.adventure.text.format.TextDecoration;
/*     */ import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class TextComponent
/*     */ {
/*     */   @NotNull
/*  22 */   public static final Style STYLE_DEFAULT = Style.style()
/*  23 */     .decoration(TextDecoration.ITALIC, false)
/*  24 */     .build(); @NotNull
/*  25 */   public static final Style STYLE_BOLD = Style.style()
/*  26 */     .decoration(TextDecoration.ITALIC, false)
/*  27 */     .decorate(TextDecoration.BOLD)
/*  28 */     .build(); @NotNull
/*  29 */   public static final LegacyComponentSerializer SERIALIZER = LegacyComponentSerializer.legacyAmpersand();
/*     */   @NotNull
/*  31 */   public static final LegacyComponentSerializer HEX_SERIALIZER = LegacyComponentSerializer.builder()
/*  32 */     .character('&')
/*  33 */     .hexColors()
/*  34 */     .build();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static Component empty() {
/*  44 */     return Component.empty().style(STYLE_DEFAULT);
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
/*     */   public static Component title(@NotNull String text) {
/*  56 */     return Component.text(Objects.<String>requireNonNull(text)).style(STYLE_BOLD);
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
/*     */   public static Component title(@NotNull String text, @NotNull TextColor color) {
/*  69 */     return ((net.kyori.adventure.text.TextComponent)Component.text(Objects.<String>requireNonNull(text)).style(STYLE_BOLD)).color(color);
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
/*     */   public static Component titleTranslatable(@NotNull String key) {
/*  81 */     return Component.translatable(Objects.<String>requireNonNull(key)).style(STYLE_BOLD);
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
/*     */   public static Component titleTranslatable(@NotNull String key, @NotNull TextColor color) {
/*  94 */     return ((TranslatableComponent)Component.translatable(Objects.<String>requireNonNull(key)).style(STYLE_BOLD)).color(color);
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
/*     */   public static Component of(@NotNull String text) {
/* 106 */     return Component.text(Objects.<String>requireNonNull(text)).style(STYLE_DEFAULT);
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
/*     */   public static Component of(@NotNull String text, @NotNull Style style) {
/* 119 */     return Component.text(Objects.<String>requireNonNull(text)).style(Objects.<Style>requireNonNull(style));
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
/*     */   public static Component of(@NotNull String text, @NotNull TextColor color) {
/* 132 */     return ((net.kyori.adventure.text.TextComponent)Component.text(Objects.<String>requireNonNull(text)).style(STYLE_DEFAULT)).color(color);
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
/*     */   public static Component ofTranslatable(@NotNull String key) {
/* 144 */     return Component.translatable(Objects.<String>requireNonNull(key)).style(STYLE_DEFAULT);
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
/*     */   public static Component ofTranslatable(@NotNull String key, @NotNull TextColor color) {
/* 157 */     return ((TranslatableComponent)Component.translatable(Objects.<String>requireNonNull(key)).style(STYLE_DEFAULT)).color(color);
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
/*     */   public static Collection<Component> toCollection(@NotNull Collection<String> strings) {
/* 169 */     Collection<Component> components = new ArrayList<>();
/* 170 */     strings.forEach(string -> components.add(of(string)));
/* 171 */     return components;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static Component toLinesComponent(@NotNull List<Component> components) {
/*     */     Component component;
/* 183 */     net.kyori.adventure.text.TextComponent textComponent = Component.empty();
/* 184 */     int index = 0;
/* 185 */     for (Component component1 : components) {
/* 186 */       if (index == 0) { component = textComponent.append(component1); }
/* 187 */       else { component = component.appendNewline().append(component1); }
/* 188 */        index++;
/*     */     } 
/* 190 */     return component;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static Component join(@NotNull Component... components) {
/*     */     Component component;
/* 201 */     net.kyori.adventure.text.TextComponent textComponent = Component.empty();
/* 202 */     for (Component component1 : components) {
/* 203 */       component = textComponent.append(component1);
/*     */     }
/* 205 */     return component;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static String serializeLegacy(@NotNull Component component) {
/* 216 */     return SERIALIZER.serialize(component);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static Component deserializeLegacy(@NotNull String string) {
/* 227 */     return HEX_SERIALIZER.deserialize(string).decoration(TextDecoration.ITALIC, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static List<Component> deserializeLegacy(@NotNull List<String> strings) {
/* 238 */     List<Component> components = new ArrayList<>();
/* 239 */     for (String string : strings) {
/* 240 */       components.add(HEX_SERIALIZER.deserialize(string).decoration(TextDecoration.ITALIC, false));
/*     */     }
/* 242 */     return components;
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
/*     */   public static List<Component> splitStringIntoLines(@NotNull String string, int maxLineLength, @NotNull TextColor color) {
/* 255 */     List<Component> components = new ArrayList<>();
/* 256 */     String[] words = string.split(" ");
/* 257 */     StringBuilder line = new StringBuilder();
/*     */     
/* 259 */     for (String word : words) {
/* 260 */       if (line.length() + word.length() + 1 > maxLineLength) {
/*     */         
/* 262 */         components.add(of(line.toString().trim(), color));
/* 263 */         line.setLength(0);
/*     */       } 
/* 265 */       if (!line.isEmpty()) line.append(" "); 
/* 266 */       line.append(word);
/*     */     } 
/*     */ 
/*     */     
/* 270 */     if (!line.isEmpty()) components.add(of(line.toString().trim(), color)); 
/* 271 */     return components;
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\component\TextComponent.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */