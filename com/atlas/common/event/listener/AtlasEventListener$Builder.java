/*     */ package com.atlas.common.event.listener;
/*     */ 
/*     */ import com.atlas.common.event.AtlasEvent;
/*     */ import com.atlas.common.event.AtlasEventBus;
/*     */ import com.atlas.common.scheduler.SchedulerEnvironmentType;
/*     */ import java.util.HashSet;
/*     */ import java.util.Objects;
/*     */ import java.util.Set;
/*     */ import java.util.function.Consumer;
/*     */ import java.util.function.Predicate;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Builder<T extends AtlasEvent>
/*     */ {
/*     */   private final Class<T> eventClass;
/*     */   private Consumer<T> handler;
/* 192 */   private SchedulerEnvironmentType environmentType = SchedulerEnvironmentType.SYNC;
/* 193 */   private final Set<Predicate<T>> filters = new HashSet<>();
/* 194 */   private AtlasEventListenerPriority priority = AtlasEventListenerPriority.NORMAL;
/*     */   private boolean ignoreCancelled = false;
/* 196 */   private int executionLimit = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Builder(@NotNull Class<T> eventClass) {
/* 205 */     this.eventClass = Objects.<Class<T>>requireNonNull(eventClass);
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
/*     */   public Builder<T> handler(@NotNull Consumer<T> handler) {
/* 217 */     this.handler = Objects.<Consumer<T>>requireNonNull(handler);
/* 218 */     return this;
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
/*     */   public Builder<T> environmentType(@NotNull SchedulerEnvironmentType environmentType) {
/* 231 */     this.environmentType = Objects.<SchedulerEnvironmentType>requireNonNull(environmentType);
/* 232 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Builder<T> filter(@NotNull Predicate<T> filter) {
/* 243 */     this.filters.add(Objects.<Predicate<T>>requireNonNull(filter));
/* 244 */     return this;
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
/*     */   public Builder<T> priority(@NotNull AtlasEventListenerPriority priority) {
/* 257 */     this.priority = Objects.<AtlasEventListenerPriority>requireNonNull(priority);
/* 258 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Builder<T> ignoreCancelled(boolean ignoreCancelled) {
/* 269 */     this.ignoreCancelled = ignoreCancelled;
/* 270 */     return this;
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
/*     */   public Builder<T> executionLimit(int executionLimit) {
/* 282 */     if (executionLimit < 0) throw new IllegalArgumentException("Execution limit cannot be less than 0"); 
/* 283 */     this.executionLimit = executionLimit;
/* 284 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public AtlasEventListener<T> build() {
/* 294 */     return new AtlasEventListener<>(this.eventClass, this.handler, this.environmentType, this.filters, this.priority, this.ignoreCancelled, this.executionLimit);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public AtlasEventListener<T> register() {
/* 305 */     AtlasEventListener<T> listener = build();
/* 306 */     AtlasEventBus.register(listener);
/* 307 */     return listener;
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\event\listener\AtlasEventListener$Builder.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */