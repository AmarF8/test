/*     */ package net.kyori.adventure.text.format;
/*     */ 
/*     */ import java.util.EnumMap;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.Set;
/*     */ import java.util.stream.Stream;
/*     */ import net.kyori.adventure.internal.Internals;
/*     */ import net.kyori.adventure.key.Key;
/*     */ import net.kyori.adventure.text.event.ClickEvent;
/*     */ import net.kyori.adventure.text.event.HoverEvent;
/*     */ import net.kyori.adventure.text.event.HoverEventSource;
/*     */ import net.kyori.adventure.util.ARGBLike;
/*     */ import net.kyori.adventure.util.Buildable;
/*     */ import net.kyori.examination.ExaminableProperty;
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
/*     */ final class StyleImpl
/*     */   implements Style
/*     */ {
/*  44 */   static final StyleImpl EMPTY = new StyleImpl(null, null, null, DecorationMap.EMPTY, null, null, null);
/*     */   
/*     */   @Nullable
/*     */   final Key font;
/*     */   
/*     */   @Nullable
/*     */   final TextColor color;
/*     */   @Nullable
/*     */   final ShadowColor shadowColor;
/*     */   @NotNull
/*     */   final DecorationMap decorations;
/*     */   @Nullable
/*     */   final ClickEvent clickEvent;
/*     */   @Nullable
/*     */   final HoverEvent<?> hoverEvent;
/*     */   @Nullable
/*     */   final String insertion;
/*     */   
/*     */   StyleImpl(@Nullable Key font, @Nullable TextColor color, @Nullable ShadowColor shadowColor, @NotNull Map<TextDecoration, TextDecoration.State> decorations, @Nullable ClickEvent clickEvent, @Nullable HoverEvent<?> hoverEvent, @Nullable String insertion) {
/*  63 */     this.font = font;
/*  64 */     this.color = color;
/*  65 */     this.shadowColor = shadowColor;
/*  66 */     this.decorations = DecorationMap.fromMap(decorations);
/*  67 */     this.clickEvent = clickEvent;
/*  68 */     this.hoverEvent = hoverEvent;
/*  69 */     this.insertion = insertion;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public Key font() {
/*  74 */     return this.font;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public Style font(@Nullable Key font) {
/*  79 */     if (Objects.equals(this.font, font)) return this; 
/*  80 */     return new StyleImpl(font, this.color, this.shadowColor, this.decorations, this.clickEvent, this.hoverEvent, this.insertion);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public TextColor color() {
/*  85 */     return this.color;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public Style color(@Nullable TextColor color) {
/*  90 */     if (Objects.equals(this.color, color)) return this; 
/*  91 */     return new StyleImpl(this.font, color, this.shadowColor, this.decorations, this.clickEvent, this.hoverEvent, this.insertion);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public Style colorIfAbsent(@Nullable TextColor color) {
/*  96 */     if (this.color == null) {
/*  97 */       return color(color);
/*     */     }
/*  99 */     return this;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public ShadowColor shadowColor() {
/* 104 */     return this.shadowColor;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public Style shadowColor(@Nullable ARGBLike argb) {
/* 109 */     if (Objects.equals(this.shadowColor, argb)) return this; 
/* 110 */     return new StyleImpl(this.font, this.color, (argb == null) ? null : ShadowColor.shadowColor(argb), this.decorations, this.clickEvent, this.hoverEvent, this.insertion);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public Style shadowColorIfAbsent(@Nullable ARGBLike argb) {
/* 115 */     if (this.shadowColor == null) {
/* 116 */       return shadowColor(argb);
/*     */     }
/* 118 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public TextDecoration.State decoration(@NotNull TextDecoration decoration) {
/* 124 */     TextDecoration.State state = this.decorations.get(decoration);
/* 125 */     if (state != null) {
/* 126 */       return state;
/*     */     }
/* 128 */     throw new IllegalArgumentException(String.format("unknown decoration '%s'", new Object[] { decoration }));
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public Style decoration(@NotNull TextDecoration decoration, TextDecoration.State state) {
/* 133 */     Objects.requireNonNull(state, "state");
/* 134 */     if (decoration(decoration) == state) return this; 
/* 135 */     return new StyleImpl(this.font, this.color, this.shadowColor, this.decorations.with(decoration, state), this.clickEvent, this.hoverEvent, this.insertion);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public Style decorationIfAbsent(@NotNull TextDecoration decoration, TextDecoration.State state) {
/* 140 */     Objects.requireNonNull(state, "state");
/* 141 */     TextDecoration.State oldState = this.decorations.get(decoration);
/* 142 */     if (oldState == TextDecoration.State.NOT_SET) {
/* 143 */       return new StyleImpl(this.font, this.color, this.shadowColor, this.decorations.with(decoration, state), this.clickEvent, this.hoverEvent, this.insertion);
/*     */     }
/* 145 */     if (oldState != null) {
/* 146 */       return this;
/*     */     }
/* 148 */     throw new IllegalArgumentException(String.format("unknown decoration '%s'", new Object[] { decoration }));
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public Map<TextDecoration, TextDecoration.State> decorations() {
/* 153 */     return this.decorations;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public Style decorations(@NotNull Map<TextDecoration, TextDecoration.State> decorations) {
/* 158 */     return new StyleImpl(this.font, this.color, this.shadowColor, DecorationMap.merge(decorations, this.decorations), this.clickEvent, this.hoverEvent, this.insertion);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public ClickEvent clickEvent() {
/* 163 */     return this.clickEvent;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public Style clickEvent(@Nullable ClickEvent event) {
/* 168 */     return new StyleImpl(this.font, this.color, this.shadowColor, this.decorations, event, this.hoverEvent, this.insertion);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public HoverEvent<?> hoverEvent() {
/* 173 */     return this.hoverEvent;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public Style hoverEvent(@Nullable HoverEventSource<?> source) {
/* 178 */     return new StyleImpl(this.font, this.color, this.shadowColor, this.decorations, this.clickEvent, HoverEventSource.unbox(source), this.insertion);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public String insertion() {
/* 183 */     return this.insertion;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public Style insertion(@Nullable String insertion) {
/* 188 */     if (Objects.equals(this.insertion, insertion)) return this; 
/* 189 */     return new StyleImpl(this.font, this.color, this.shadowColor, this.decorations, this.clickEvent, this.hoverEvent, insertion);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public Style merge(@NotNull Style that, Style.Merge.Strategy strategy, @NotNull Set<Style.Merge> merges) {
/* 194 */     if (nothingToMerge(that, strategy, merges)) {
/* 195 */       return this;
/*     */     }
/*     */     
/* 198 */     if (isEmpty() && Style.Merge.hasAll(merges))
/*     */     {
/*     */       
/* 201 */       return that;
/*     */     }
/*     */     
/* 204 */     Style.Builder builder = toBuilder();
/* 205 */     builder.merge(that, strategy, merges);
/* 206 */     return builder.build();
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public Style unmerge(@NotNull Style that) {
/* 211 */     if (isEmpty())
/*     */     {
/* 213 */       return this;
/*     */     }
/*     */     
/* 216 */     Style.Builder builder = new BuilderImpl(this);
/*     */     
/* 218 */     if (Objects.equals(font(), that.font())) {
/* 219 */       builder.font((Key)null);
/*     */     }
/*     */     
/* 222 */     if (Objects.equals(color(), that.color())) {
/* 223 */       builder.color((TextColor)null);
/*     */     }
/*     */     
/* 226 */     if (Objects.equals(shadowColor(), that.shadowColor())) {
/* 227 */       builder.shadowColor(null);
/*     */     }
/*     */     
/* 230 */     for (int i = 0, length = DecorationMap.DECORATIONS.length; i < length; i++) {
/* 231 */       TextDecoration decoration = DecorationMap.DECORATIONS[i];
/* 232 */       if (decoration(decoration) == that.decoration(decoration)) {
/* 233 */         builder.decoration(decoration, TextDecoration.State.NOT_SET);
/*     */       }
/*     */     } 
/*     */     
/* 237 */     if (Objects.equals(clickEvent(), that.clickEvent())) {
/* 238 */       builder.clickEvent((ClickEvent)null);
/*     */     }
/*     */     
/* 241 */     if (Objects.equals(hoverEvent(), that.hoverEvent())) {
/* 242 */       builder.hoverEvent((HoverEventSource<?>)null);
/*     */     }
/*     */     
/* 245 */     if (Objects.equals(insertion(), that.insertion())) {
/* 246 */       builder.insertion((String)null);
/*     */     }
/*     */     
/* 249 */     return builder.build();
/*     */   }
/*     */ 
/*     */   
/*     */   static boolean nothingToMerge(@NotNull Style mergeFrom, Style.Merge.Strategy strategy, @NotNull Set<Style.Merge> merges) {
/* 254 */     if (strategy == Style.Merge.Strategy.NEVER) return true; 
/* 255 */     if (mergeFrom.isEmpty()) return true; 
/* 256 */     if (merges.isEmpty()) return true; 
/* 257 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/* 262 */     return (this == EMPTY);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public Style.Builder toBuilder() {
/* 267 */     return new BuilderImpl(this);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public Stream<? extends ExaminableProperty> examinableProperties() {
/* 272 */     return Stream.concat(this.decorations
/* 273 */         .examinableProperties(), 
/* 274 */         Stream.of(new ExaminableProperty[] {
/* 275 */             ExaminableProperty.of("color", this.color), 
/* 276 */             ExaminableProperty.of("shadowColor", this.shadowColor), 
/* 277 */             ExaminableProperty.of("clickEvent", this.clickEvent), 
/* 278 */             ExaminableProperty.of("hoverEvent", this.hoverEvent), 
/* 279 */             ExaminableProperty.of("insertion", this.insertion), 
/* 280 */             ExaminableProperty.of("font", this.font)
/*     */           }));
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public String toString() {
/* 287 */     return Internals.toString(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(@Nullable Object other) {
/* 292 */     if (this == other) return true; 
/* 293 */     if (!(other instanceof StyleImpl)) return false; 
/* 294 */     StyleImpl that = (StyleImpl)other;
/* 295 */     return (Objects.equals(this.color, that.color) && this.decorations
/* 296 */       .equals(that.decorations) && 
/* 297 */       Objects.equals(this.shadowColor, that.shadowColor) && 
/* 298 */       Objects.equals(this.clickEvent, that.clickEvent) && 
/* 299 */       Objects.equals(this.hoverEvent, that.hoverEvent) && 
/* 300 */       Objects.equals(this.insertion, that.insertion) && 
/* 301 */       Objects.equals(this.font, that.font));
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 306 */     int result = Objects.hashCode(this.color);
/* 307 */     result = 31 * result + Objects.hashCode(this.shadowColor);
/* 308 */     result = 31 * result + this.decorations.hashCode();
/* 309 */     result = 31 * result + Objects.hashCode(this.clickEvent);
/* 310 */     result = 31 * result + Objects.hashCode(this.hoverEvent);
/* 311 */     result = 31 * result + Objects.hashCode(this.insertion);
/* 312 */     result = 31 * result + Objects.hashCode(this.font);
/* 313 */     return result;
/*     */   }
/*     */   
/*     */   static final class BuilderImpl
/*     */     implements Style.Builder {
/*     */     @Nullable
/*     */     Key font;
/*     */     @Nullable
/*     */     TextColor color;
/*     */     @Nullable
/*     */     ShadowColor shadowColor;
/*     */     
/*     */     BuilderImpl() {
/* 326 */       this.decorations = new EnumMap<>(DecorationMap.EMPTY); } final Map<TextDecoration, TextDecoration.State> decorations; @Nullable
/*     */     ClickEvent clickEvent; @Nullable
/*     */     HoverEvent<?> hoverEvent; @Nullable
/*     */     String insertion; BuilderImpl(@NotNull StyleImpl style) {
/* 330 */       this.color = style.color;
/* 331 */       this.shadowColor = style.shadowColor;
/* 332 */       this.decorations = new EnumMap<>(style.decorations);
/* 333 */       this.clickEvent = style.clickEvent;
/* 334 */       this.hoverEvent = style.hoverEvent;
/* 335 */       this.insertion = style.insertion;
/* 336 */       this.font = style.font;
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public Style.Builder font(@Nullable Key font) {
/* 341 */       this.font = font;
/* 342 */       return this;
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public Style.Builder color(@Nullable TextColor color) {
/* 347 */       this.color = color;
/* 348 */       return this;
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public Style.Builder colorIfAbsent(@Nullable TextColor color) {
/* 353 */       if (this.color == null) {
/* 354 */         this.color = color;
/*     */       }
/* 356 */       return this;
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public Style.Builder shadowColor(@Nullable ARGBLike argb) {
/* 361 */       this.shadowColor = (argb == null) ? null : ShadowColor.shadowColor(argb);
/* 362 */       return this;
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public Style.Builder shadowColorIfAbsent(@Nullable ARGBLike argb) {
/* 367 */       if (this.shadowColor == null) {
/* 368 */         this.shadowColor = (argb == null) ? null : ShadowColor.shadowColor(argb);
/*     */       }
/* 370 */       return this;
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public Style.Builder decoration(@NotNull TextDecoration decoration, TextDecoration.State state) {
/* 375 */       Objects.requireNonNull(state, "state");
/* 376 */       Objects.requireNonNull(decoration, "decoration");
/* 377 */       this.decorations.put(decoration, state);
/* 378 */       return this;
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public Style.Builder decorationIfAbsent(@NotNull TextDecoration decoration, TextDecoration.State state) {
/* 383 */       Objects.requireNonNull(state, "state");
/* 384 */       TextDecoration.State oldState = this.decorations.get(decoration);
/* 385 */       if (oldState == TextDecoration.State.NOT_SET) {
/* 386 */         this.decorations.put(decoration, state);
/*     */       }
/* 388 */       if (oldState != null) {
/* 389 */         return this;
/*     */       }
/* 391 */       throw new IllegalArgumentException(String.format("unknown decoration '%s'", new Object[] { decoration }));
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public Style.Builder clickEvent(@Nullable ClickEvent event) {
/* 396 */       this.clickEvent = event;
/* 397 */       return this;
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public Style.Builder hoverEvent(@Nullable HoverEventSource<?> source) {
/* 402 */       this.hoverEvent = HoverEventSource.unbox(source);
/* 403 */       return this;
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public Style.Builder insertion(@Nullable String insertion) {
/* 408 */       this.insertion = insertion;
/* 409 */       return this;
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public Style.Builder merge(@NotNull Style that, Style.Merge.Strategy strategy, @NotNull Set<Style.Merge> merges) {
/* 414 */       Objects.requireNonNull(that, "style");
/* 415 */       Objects.requireNonNull(strategy, "strategy");
/* 416 */       Objects.requireNonNull(merges, "merges");
/*     */       
/* 418 */       if (StyleImpl.nothingToMerge(that, strategy, merges)) {
/* 419 */         return this;
/*     */       }
/*     */       
/* 422 */       if (merges.contains(Style.Merge.COLOR)) {
/* 423 */         TextColor color = that.color();
/* 424 */         if (color != null && (
/* 425 */           strategy == Style.Merge.Strategy.ALWAYS || (strategy == Style.Merge.Strategy.IF_ABSENT_ON_TARGET && this.color == null))) {
/* 426 */           color(color);
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 431 */       if (merges.contains(Style.Merge.SHADOW_COLOR)) {
/* 432 */         ShadowColor shadowColor = that.shadowColor();
/* 433 */         if (shadowColor != null && (
/* 434 */           strategy == Style.Merge.Strategy.ALWAYS || (strategy == Style.Merge.Strategy.IF_ABSENT_ON_TARGET && this.shadowColor == null))) {
/* 435 */           shadowColor(shadowColor);
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 440 */       if (merges.contains(Style.Merge.DECORATIONS)) {
/* 441 */         for (int i = 0, length = DecorationMap.DECORATIONS.length; i < length; i++) {
/* 442 */           TextDecoration decoration = DecorationMap.DECORATIONS[i];
/* 443 */           TextDecoration.State state = that.decoration(decoration);
/* 444 */           if (state != TextDecoration.State.NOT_SET) {
/* 445 */             if (strategy == Style.Merge.Strategy.ALWAYS) {
/* 446 */               decoration(decoration, state);
/* 447 */             } else if (strategy == Style.Merge.Strategy.IF_ABSENT_ON_TARGET) {
/* 448 */               decorationIfAbsent(decoration, state);
/*     */             } 
/*     */           }
/*     */         } 
/*     */       }
/*     */       
/* 454 */       if (merges.contains(Style.Merge.EVENTS)) {
/* 455 */         ClickEvent clickEvent = that.clickEvent();
/* 456 */         if (clickEvent != null && (
/* 457 */           strategy == Style.Merge.Strategy.ALWAYS || (strategy == Style.Merge.Strategy.IF_ABSENT_ON_TARGET && this.clickEvent == null))) {
/* 458 */           clickEvent(clickEvent);
/*     */         }
/*     */ 
/*     */         
/* 462 */         HoverEvent<?> hoverEvent = that.hoverEvent();
/* 463 */         if (hoverEvent != null && (
/* 464 */           strategy == Style.Merge.Strategy.ALWAYS || (strategy == Style.Merge.Strategy.IF_ABSENT_ON_TARGET && this.hoverEvent == null))) {
/* 465 */           hoverEvent((HoverEventSource<?>)hoverEvent);
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 470 */       if (merges.contains(Style.Merge.INSERTION)) {
/* 471 */         String insertion = that.insertion();
/* 472 */         if (insertion != null && (
/* 473 */           strategy == Style.Merge.Strategy.ALWAYS || (strategy == Style.Merge.Strategy.IF_ABSENT_ON_TARGET && this.insertion == null))) {
/* 474 */           insertion(insertion);
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 479 */       if (merges.contains(Style.Merge.FONT)) {
/* 480 */         Key font = that.font();
/* 481 */         if (font != null && (
/* 482 */           strategy == Style.Merge.Strategy.ALWAYS || (strategy == Style.Merge.Strategy.IF_ABSENT_ON_TARGET && this.font == null))) {
/* 483 */           font(font);
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 488 */       return this;
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public StyleImpl build() {
/* 493 */       if (isEmpty()) {
/* 494 */         return StyleImpl.EMPTY;
/*     */       }
/* 496 */       return new StyleImpl(this.font, this.color, this.shadowColor, this.decorations, this.clickEvent, this.hoverEvent, this.insertion);
/*     */     }
/*     */     
/*     */     private boolean isEmpty() {
/* 500 */       return (this.color == null && this.shadowColor == null && this.decorations
/*     */         
/* 502 */         .values().stream().allMatch(state -> (state == TextDecoration.State.NOT_SET)) && this.clickEvent == null && this.hoverEvent == null && this.insertion == null && this.font == null);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\net\kyori\adventure\text\format\StyleImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */