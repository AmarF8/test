/*     */ package net.kyori.adventure.text.flattener;
/*     */ 
/*     */ import java.util.Objects;
/*     */ import java.util.function.BiConsumer;
/*     */ import java.util.function.Consumer;
/*     */ import java.util.function.Function;
/*     */ import net.kyori.adventure.text.Component;
/*     */ import net.kyori.adventure.text.KeybindComponent;
/*     */ import net.kyori.adventure.text.ScoreComponent;
/*     */ import net.kyori.adventure.text.SelectorComponent;
/*     */ import net.kyori.adventure.text.TextComponent;
/*     */ import net.kyori.adventure.text.TranslatableComponent;
/*     */ import net.kyori.adventure.text.format.Style;
/*     */ import net.kyori.adventure.util.Buildable;
/*     */ import net.kyori.adventure.util.InheritanceAwareMap;
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
/*     */ final class ComponentFlattenerImpl
/*     */   implements ComponentFlattener
/*     */ {
/*     */   static final ComponentFlattener BASIC;
/*     */   
/*     */   static {
/*  58 */     BASIC = (ComponentFlattener)(new BuilderImpl()).<KeybindComponent>mapper(KeybindComponent.class, component -> component.keybind()).<ScoreComponent>mapper(ScoreComponent.class, component -> { String value = component.value(); return (Function)((value != null) ? value : ""); }).<SelectorComponent>mapper(SelectorComponent.class, SelectorComponent::pattern).<TextComponent>mapper(TextComponent.class, TextComponent::content).<TranslatableComponent>mapper(TranslatableComponent.class, component -> { String fallback = component.fallback(); return (Function)((fallback != null) ? fallback : component.key()); }).build();
/*  59 */   } static final ComponentFlattener TEXT_ONLY = (ComponentFlattener)(new BuilderImpl())
/*  60 */     .<TextComponent>mapper(TextComponent.class, TextComponent::content)
/*  61 */     .build();
/*     */   
/*     */   private static final int MAX_DEPTH = 512;
/*     */   
/*     */   private final InheritanceAwareMap<Component, Handler> flatteners;
/*     */   private final Function<Component, String> unknownHandler;
/*     */   
/*     */   ComponentFlattenerImpl(InheritanceAwareMap<Component, Handler> flatteners, @Nullable Function<Component, String> unknownHandler) {
/*  69 */     this.flatteners = flatteners;
/*  70 */     this.unknownHandler = unknownHandler;
/*     */   }
/*     */ 
/*     */   
/*     */   public void flatten(@NotNull Component input, @NotNull FlattenerListener listener) {
/*  75 */     flatten0(input, listener, 0);
/*     */   }
/*     */   
/*     */   private void flatten0(@NotNull Component input, @NotNull FlattenerListener listener, int depth) {
/*  79 */     Objects.requireNonNull(input, "input");
/*  80 */     Objects.requireNonNull(listener, "listener");
/*  81 */     if (input == Component.empty())
/*  82 */       return;  if (depth > 512) {
/*  83 */       throw new IllegalStateException("Exceeded maximum depth of 512 while attempting to flatten components!");
/*     */     }
/*     */     
/*  86 */     Handler flattener = flattener(input);
/*  87 */     Style inputStyle = input.style();
/*     */     
/*  89 */     listener.pushStyle(inputStyle);
/*     */     try {
/*  91 */       if (flattener != null) {
/*  92 */         flattener.handle(this, input, listener, depth + 1);
/*     */       }
/*     */       
/*  95 */       if (!input.children().isEmpty() && listener.shouldContinue()) {
/*  96 */         for (Component child : input.children()) {
/*  97 */           flatten0(child, listener, depth + 1);
/*     */         }
/*     */       }
/*     */     } finally {
/* 101 */       listener.popStyle(inputStyle);
/*     */     } 
/*     */   }
/*     */   @Nullable
/*     */   private <T extends Component> Handler flattener(T test) {
/* 106 */     Handler flattener = (Handler)this.flatteners.get(test.getClass());
/*     */     
/* 108 */     if (flattener == null && this.unknownHandler != null) {
/* 109 */       return (self, component, listener, depth) -> listener.component(this.unknownHandler.apply(component));
/*     */     }
/* 111 */     return flattener;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ComponentFlattener.Builder toBuilder() {
/* 117 */     return new BuilderImpl(this.flatteners, this.unknownHandler);
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   static interface Handler {
/*     */     void handle(ComponentFlattenerImpl param1ComponentFlattenerImpl, Component param1Component, FlattenerListener param1FlattenerListener, int param1Int);
/*     */   }
/*     */   
/*     */   static final class BuilderImpl implements ComponentFlattener.Builder {
/*     */     private final InheritanceAwareMap.Builder<Component, ComponentFlattenerImpl.Handler> flatteners;
/*     */     @Nullable
/*     */     private Function<Component, String> unknownHandler;
/*     */     
/*     */     BuilderImpl() {
/* 131 */       this.flatteners = InheritanceAwareMap.builder().strict(true);
/*     */     }
/*     */     
/*     */     BuilderImpl(InheritanceAwareMap<Component, ComponentFlattenerImpl.Handler> flatteners, @Nullable Function<Component, String> unknownHandler) {
/* 135 */       this.flatteners = InheritanceAwareMap.builder(flatteners).strict(true);
/* 136 */       this.unknownHandler = unknownHandler;
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public ComponentFlattener build() {
/* 141 */       return new ComponentFlattenerImpl((InheritanceAwareMap<Component, ComponentFlattenerImpl.Handler>)this.flatteners.build(), this.unknownHandler);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public <T extends Component> ComponentFlattener.Builder mapper(@NotNull Class<T> type, @NotNull Function<T, String> converter) {
/* 147 */       this.flatteners.put(type, (self, component, listener, depth) -> listener.component(converter.apply(component)));
/* 148 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public <T extends Component> ComponentFlattener.Builder complexMapper(@NotNull Class<T> type, @NotNull BiConsumer<T, Consumer<Component>> converter) {
/* 154 */       this.flatteners.put(type, (self, component, listener, depth) -> converter.accept(component, ()));
/* 155 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public ComponentFlattener.Builder unknownMapper(@Nullable Function<Component, String> converter) {
/* 160 */       this.unknownHandler = converter;
/* 161 */       return this;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\net\kyori\adventure\text\flattener\ComponentFlattenerImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */