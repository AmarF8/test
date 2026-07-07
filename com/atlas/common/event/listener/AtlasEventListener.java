/*     */ package com.atlas.common.event.listener;
/*     */ 
/*     */ import com.atlas.common.AtlasCommon;
/*     */ import com.atlas.common.event.AtlasEvent;
/*     */ import com.atlas.common.event.AtlasEventBus;
/*     */ import com.atlas.common.event.cancellable.CancellableEvent;
/*     */ import com.atlas.common.scheduler.Scheduler;
/*     */ import com.atlas.common.scheduler.SchedulerEnvironmentType;
/*     */ import java.util.Collection;
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
/*     */ public final class AtlasEventListener<T extends AtlasEvent>
/*     */ {
/*     */   private final Class<T> eventClass;
/*     */   private final Consumer<T> handler;
/*     */   private final SchedulerEnvironmentType environmentType;
/*     */   private final Set<Predicate<T>> filters;
/*     */   private final AtlasEventListenerPriority priority;
/*     */   private final boolean ignoreCancelled;
/*     */   private final int executionLimit;
/*     */   private int executionCount;
/*     */   private boolean registered = true;
/*     */   
/*     */   AtlasEventListener(@NotNull Class<T> eventClass, @NotNull Consumer<T> handler, @NotNull SchedulerEnvironmentType environmentType, @NotNull Set<Predicate<T>> filters, @NotNull AtlasEventListenerPriority priority, boolean ignoreCancelled, int executionLimit) {
/*  58 */     this.eventClass = Objects.<Class<T>>requireNonNull(eventClass);
/*  59 */     this.handler = Objects.<Consumer<T>>requireNonNull(handler);
/*  60 */     this.environmentType = Objects.<SchedulerEnvironmentType>requireNonNull(environmentType);
/*  61 */     this.filters = new HashSet<>(Objects.<Collection<? extends Predicate<T>>>requireNonNull(filters));
/*  62 */     this.priority = priority;
/*  63 */     this.ignoreCancelled = ignoreCancelled;
/*  64 */     this.executionLimit = executionLimit;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Class<T> getEventClass() {
/*  72 */     return this.eventClass;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Consumer<T> getHandler() {
/*  80 */     return this.handler;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public SchedulerEnvironmentType getEnvironmentType() {
/*  89 */     return this.environmentType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Set<Predicate<T>> getFilters() {
/*  97 */     return this.filters;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public AtlasEventListenerPriority getPriority() {
/* 106 */     return this.priority;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isIgnoreCancelled() {
/* 113 */     return this.ignoreCancelled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getExecutionLimit() {
/* 120 */     return this.executionLimit;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isRegistered() {
/* 127 */     return this.registered;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void unregister() {
/* 134 */     if (!this.registered) throw new IllegalStateException("Listener is already unregistered"); 
/* 135 */     AtlasEventBus.unregister(this);
/* 136 */     this.registered = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void run(@NotNull T event) {
/* 147 */     if (!this.registered) throw new IllegalStateException("Listener is not registered");
/*     */ 
/*     */     
/* 150 */     if (!this.ignoreCancelled && CancellableEvent.class.isAssignableFrom(event.getClass()) && ((CancellableEvent)event).isCancelled()) {
/*     */       return;
/*     */     }
/*     */     
/* 154 */     for (Predicate<T> filter : this.filters) {
/* 155 */       if (!filter.test(event)) {
/*     */         return;
/*     */       }
/*     */     } 
/* 159 */     if (this.environmentType == SchedulerEnvironmentType.ASYNC)
/* 160 */     { AtlasCommon.getInstance().getSchedulerProvider().ofAsync(scheduler -> this.handler.accept((T)event)); }
/* 161 */     else { this.handler.accept(event); }
/*     */ 
/*     */     
/* 164 */     if (this.executionLimit > 0 && ++this.executionCount >= this.executionLimit) unregister();
/*     */   
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
/*     */   public static <T extends AtlasEvent> Builder<T> builder(@NotNull Class<T> eventClass) {
/* 179 */     return new Builder<>(eventClass);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static final class Builder<T extends AtlasEvent>
/*     */   {
/*     */     private final Class<T> eventClass;
/*     */ 
/*     */     
/*     */     private Consumer<T> handler;
/*     */ 
/*     */     
/* 192 */     private SchedulerEnvironmentType environmentType = SchedulerEnvironmentType.SYNC;
/* 193 */     private final Set<Predicate<T>> filters = new HashSet<>();
/* 194 */     private AtlasEventListenerPriority priority = AtlasEventListenerPriority.NORMAL;
/*     */     private boolean ignoreCancelled = false;
/* 196 */     private int executionLimit = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder(@NotNull Class<T> eventClass) {
/* 205 */       this.eventClass = Objects.<Class<T>>requireNonNull(eventClass);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public Builder<T> handler(@NotNull Consumer<T> handler) {
/* 217 */       this.handler = Objects.<Consumer<T>>requireNonNull(handler);
/* 218 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public Builder<T> environmentType(@NotNull SchedulerEnvironmentType environmentType) {
/* 231 */       this.environmentType = Objects.<SchedulerEnvironmentType>requireNonNull(environmentType);
/* 232 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public Builder<T> filter(@NotNull Predicate<T> filter) {
/* 243 */       this.filters.add(Objects.<Predicate<T>>requireNonNull(filter));
/* 244 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public Builder<T> priority(@NotNull AtlasEventListenerPriority priority) {
/* 257 */       this.priority = Objects.<AtlasEventListenerPriority>requireNonNull(priority);
/* 258 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public Builder<T> ignoreCancelled(boolean ignoreCancelled) {
/* 269 */       this.ignoreCancelled = ignoreCancelled;
/* 270 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public Builder<T> executionLimit(int executionLimit) {
/* 282 */       if (executionLimit < 0) throw new IllegalArgumentException("Execution limit cannot be less than 0"); 
/* 283 */       this.executionLimit = executionLimit;
/* 284 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public AtlasEventListener<T> build() {
/* 294 */       return new AtlasEventListener<>(this.eventClass, this.handler, this.environmentType, this.filters, this.priority, this.ignoreCancelled, this.executionLimit);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public AtlasEventListener<T> register() {
/* 305 */       AtlasEventListener<T> listener = build();
/* 306 */       AtlasEventBus.register(listener);
/* 307 */       return listener;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\event\listener\AtlasEventListener.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */