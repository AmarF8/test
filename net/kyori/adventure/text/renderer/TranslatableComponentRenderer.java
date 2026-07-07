/*     */ package net.kyori.adventure.text.renderer;
/*     */ 
/*     */ import java.text.AttributedCharacterIterator;
/*     */ import java.text.FieldPosition;
/*     */ import java.text.MessageFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.EnumSet;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Objects;
/*     */ import java.util.Set;
/*     */ import net.kyori.adventure.text.BlockNBTComponent;
/*     */ import net.kyori.adventure.text.Component;
/*     */ import net.kyori.adventure.text.ComponentBuilder;
/*     */ import net.kyori.adventure.text.ComponentLike;
/*     */ import net.kyori.adventure.text.EntityNBTComponent;
/*     */ import net.kyori.adventure.text.KeybindComponent;
/*     */ import net.kyori.adventure.text.ScoreComponent;
/*     */ import net.kyori.adventure.text.SelectorComponent;
/*     */ import net.kyori.adventure.text.StorageNBTComponent;
/*     */ import net.kyori.adventure.text.TextComponent;
/*     */ import net.kyori.adventure.text.TranslatableComponent;
/*     */ import net.kyori.adventure.text.TranslationArgument;
/*     */ import net.kyori.adventure.text.event.HoverEvent;
/*     */ import net.kyori.adventure.text.event.HoverEventSource;
/*     */ import net.kyori.adventure.text.format.Style;
/*     */ import net.kyori.adventure.translation.Translator;
/*     */ import net.kyori.adventure.util.TriState;
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
/*     */ public abstract class TranslatableComponentRenderer<C>
/*     */   extends AbstractComponentRenderer<C>
/*     */ {
/*     */   private static final Set<Style.Merge> MERGES;
/*     */   
/*     */   static {
/*  67 */     Set<Style.Merge> merges = EnumSet.allOf(Style.Merge.class);
/*  68 */     merges.remove(Style.Merge.EVENTS);
/*  69 */     MERGES = Collections.unmodifiableSet(merges);
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
/*     */   @NotNull
/*     */   public static TranslatableComponentRenderer<Locale> usingTranslationSource(@NotNull final Translator source) {
/*  83 */     Objects.requireNonNull(source, "source");
/*  84 */     return new TranslatableComponentRenderer<Locale>() {
/*     */         @Nullable
/*     */         protected MessageFormat translate(@NotNull String key, @NotNull Locale context) {
/*  87 */           return source.translate(key, context);
/*     */         }
/*     */         
/*     */         @NotNull
/*     */         protected Component renderTranslatable(@NotNull TranslatableComponent component, @NotNull Locale context) {
/*  92 */           TriState anyTranslations = source.hasAnyTranslations();
/*  93 */           if (anyTranslations == TriState.TRUE || anyTranslations == TriState.NOT_SET) {
/*  94 */             Component translated = source.translate(component, context);
/*  95 */             if (translated != null) return translated; 
/*  96 */             return super.renderTranslatable(component, context);
/*     */           } 
/*  98 */           return (Component)component;
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   protected MessageFormat translate(@NotNull String key, @NotNull C context) {
/* 111 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   protected MessageFormat translate(@NotNull String key, @Nullable String fallback, @NotNull C context) {
/* 123 */     return translate(key, context);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   protected Component renderBlockNbt(@NotNull BlockNBTComponent component, @NotNull C context) {
/* 129 */     BlockNBTComponent.Builder builder = ((BlockNBTComponent.Builder)nbt(context, Component.blockNBT(), component)).pos(component.pos());
/* 130 */     return mergeStyleAndOptionallyDeepRender((Component)component, builder, context);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   protected Component renderEntityNbt(@NotNull EntityNBTComponent component, @NotNull C context) {
/* 136 */     EntityNBTComponent.Builder builder = ((EntityNBTComponent.Builder)nbt(context, Component.entityNBT(), component)).selector(component.selector());
/* 137 */     return mergeStyleAndOptionallyDeepRender((Component)component, builder, context);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   protected Component renderStorageNbt(@NotNull StorageNBTComponent component, @NotNull C context) {
/* 143 */     StorageNBTComponent.Builder builder = ((StorageNBTComponent.Builder)nbt(context, Component.storageNBT(), component)).storage(component.storage());
/* 144 */     return mergeStyleAndOptionallyDeepRender((Component)component, builder, context);
/*     */   }
/*     */   
/*     */   protected <O extends net.kyori.adventure.text.NBTComponent<O, B>, B extends net.kyori.adventure.text.NBTComponentBuilder<O, B>> B nbt(@NotNull C context, B builder, O oldComponent) {
/* 148 */     builder
/* 149 */       .nbtPath(oldComponent.nbtPath())
/* 150 */       .interpret(oldComponent.interpret());
/* 151 */     Component separator = oldComponent.separator();
/* 152 */     if (separator != null) {
/* 153 */       builder.separator((ComponentLike)render(separator, context));
/*     */     }
/* 155 */     return builder;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   protected Component renderKeybind(@NotNull KeybindComponent component, @NotNull C context) {
/* 160 */     KeybindComponent.Builder builder = Component.keybind().keybind(component.keybind());
/* 161 */     return mergeStyleAndOptionallyDeepRender((Component)component, builder, context);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   protected Component renderScore(@NotNull ScoreComponent component, @NotNull C context) {
/* 170 */     ScoreComponent.Builder builder = Component.score().name(component.name()).objective(component.objective()).value(component.value());
/* 171 */     return mergeStyleAndOptionallyDeepRender((Component)component, builder, context);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   protected Component renderSelector(@NotNull SelectorComponent component, @NotNull C context) {
/* 176 */     SelectorComponent.Builder builder = Component.selector().pattern(component.pattern());
/* 177 */     return mergeStyleAndOptionallyDeepRender((Component)component, builder, context);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   protected Component renderText(@NotNull TextComponent component, @NotNull C context) {
/* 182 */     TextComponent.Builder builder = Component.text().content(component.content());
/* 183 */     return mergeStyleAndOptionallyDeepRender((Component)component, builder, context);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   protected Component renderTranslatable(@NotNull TranslatableComponent component, @NotNull C context) {
/* 189 */     MessageFormat format = translate(component.key(), component.fallback(), context);
/* 190 */     if (format == null) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 195 */       TranslatableComponent.Builder builder1 = Component.translatable().key(component.key()).fallback(component.fallback());
/* 196 */       if (!component.arguments().isEmpty()) {
/* 197 */         List<TranslationArgument> list = new ArrayList<>(component.arguments());
/* 198 */         for (int i = 0, size = list.size(); i < size; i++) {
/* 199 */           TranslationArgument arg = list.get(i);
/* 200 */           if (arg.value() instanceof Component) {
/* 201 */             list.set(i, TranslationArgument.component((ComponentLike)render((Component)arg.value(), context)));
/*     */           }
/*     */         } 
/* 204 */         builder1.arguments(list);
/*     */       } 
/* 206 */       return mergeStyleAndOptionallyDeepRender((Component)component, builder1, context);
/*     */     } 
/*     */     
/* 209 */     List<TranslationArgument> args = component.arguments();
/*     */     
/* 211 */     TextComponent.Builder builder = Component.text();
/* 212 */     mergeStyle((Component)component, builder, context);
/*     */ 
/*     */     
/* 215 */     if (args.isEmpty()) {
/* 216 */       builder.content(format.format((Object[])null, new StringBuffer(), (FieldPosition)null).toString());
/* 217 */       return optionallyRenderChildrenAppendAndBuild(component.children(), builder, context);
/*     */     } 
/*     */     
/* 220 */     Object[] nulls = new Object[args.size()];
/* 221 */     StringBuffer sb = format.format(nulls, new StringBuffer(), (FieldPosition)null);
/* 222 */     AttributedCharacterIterator it = format.formatToCharacterIterator(nulls);
/*     */     
/* 224 */     while (it.getIndex() < it.getEndIndex()) {
/* 225 */       int end = it.getRunLimit();
/* 226 */       Integer index = (Integer)it.getAttribute(MessageFormat.Field.ARGUMENT);
/* 227 */       if (index != null) {
/* 228 */         TranslationArgument arg = args.get(index.intValue());
/* 229 */         if (arg.value() instanceof Component) {
/* 230 */           builder.append(render(arg.asComponent(), context));
/*     */         } else {
/* 232 */           builder.append(arg.asComponent());
/*     */         } 
/*     */       } else {
/* 235 */         builder.append((Component)Component.text(sb.substring(it.getIndex(), end)));
/*     */       } 
/* 237 */       it.setIndex(end);
/*     */     } 
/*     */     
/* 240 */     return optionallyRenderChildrenAppendAndBuild(component.children(), builder, context);
/*     */   }
/*     */   
/*     */   protected <O extends net.kyori.adventure.text.BuildableComponent<O, B>, B extends ComponentBuilder<O, B>> O mergeStyleAndOptionallyDeepRender(Component component, B builder, C context) {
/* 244 */     mergeStyle(component, (ComponentBuilder<?, ?>)builder, context);
/* 245 */     return optionallyRenderChildrenAppendAndBuild(component.children(), builder, context);
/*     */   }
/*     */   
/*     */   protected <O extends net.kyori.adventure.text.BuildableComponent<O, B>, B extends ComponentBuilder<O, B>> O optionallyRenderChildrenAppendAndBuild(List<Component> children, B builder, C context) {
/* 249 */     if (!children.isEmpty()) {
/* 250 */       children.forEach(child -> builder.append(render(child, (C)context)));
/*     */     }
/* 252 */     return (O)builder.build();
/*     */   }
/*     */   
/*     */   protected <B extends ComponentBuilder<?, ?>> void mergeStyle(Component component, B builder, C context) {
/* 256 */     builder.mergeStyle(component, MERGES);
/* 257 */     builder.clickEvent(component.clickEvent());
/* 258 */     HoverEvent<?> hoverEvent = component.hoverEvent();
/* 259 */     if (hoverEvent != null)
/* 260 */       builder.hoverEvent((HoverEventSource)hoverEvent.withRenderedValue(this, context)); 
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\net\kyori\adventure\text\renderer\TranslatableComponentRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */