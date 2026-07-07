/*     */ package com.atlas.common.event;
/*     */ 
/*     */ import com.atlas.common.AtlasCommon;
/*     */ import com.atlas.common.event.listener.AtlasEventListener;
/*     */ import com.atlas.common.event.listener.AtlasEventListenerPriority;
/*     */ import com.atlas.common.scheduler.Scheduler;
/*     */ import com.atlas.common.scheduler.SchedulerEnvironmentType;
/*     */ import java.util.Comparator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.CopyOnWriteArrayList;
/*     */ import java.util.function.Consumer;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class AtlasEventBus
/*     */ {
/*  25 */   private static final Map<Class<?>, List<AtlasEventListener<?>>> LISTENERS = new ConcurrentHashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static <T extends AtlasEvent> void register(AtlasEventListener<T> listener) {
/*  36 */     List<AtlasEventListener<?>> listeners = LISTENERS.computeIfAbsent(listener.getEventClass(), k -> new CopyOnWriteArrayList());
/*  37 */     listeners.add(listener);
/*  38 */     listeners.sort(Comparator.<AtlasEventListener<?>>comparingInt(event -> event.getPriority().ordinal()).reversed());
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
/*     */   public static <T extends AtlasEvent> AtlasEventListener<T> register(Class<T> eventClass, Consumer<T> handler) {
/*  52 */     AtlasEventListener<T> listener = AtlasEventListener.builder(eventClass).handler(handler).build();
/*  53 */     register(listener);
/*  54 */     return listener;
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
/*     */   public static <T extends AtlasEvent> AtlasEventListener<T> registerAsync(@NotNull Class<T> eventClass, @NotNull Consumer<T> handler) {
/*  68 */     AtlasEventListener<T> listener = AtlasEventListener.builder(eventClass).handler(handler).environmentType(SchedulerEnvironmentType.ASYNC).build();
/*  69 */     register(listener);
/*  70 */     return listener;
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
/*     */   
/*     */   @NotNull
/*     */   public static <T extends AtlasEvent> AtlasEventListener<T> register(@NotNull Class<T> eventClass, @NotNull AtlasEventListenerPriority priority, @NotNull Consumer<T> handler) {
/*  86 */     AtlasEventListener<T> listener = AtlasEventListener.builder(eventClass).handler(handler).priority(priority).build();
/*  87 */     register(listener);
/*  88 */     return listener;
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
/*     */   
/*     */   @NotNull
/*     */   public static <T extends AtlasEvent> AtlasEventListener<T> registerAsync(@NotNull Class<T> eventClass, @NotNull AtlasEventListenerPriority priority, @NotNull Consumer<T> handler) {
/* 104 */     AtlasEventListener<T> listener = AtlasEventListener.builder(eventClass).handler(handler).priority(priority).environmentType(SchedulerEnvironmentType.ASYNC).build();
/* 105 */     register(listener);
/* 106 */     return listener;
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
/*     */   public static <T extends AtlasEvent> void unregister(AtlasEventListener<T> listener) {
/* 118 */     List<AtlasEventListener<?>> listeners = LISTENERS.get(listener.getEventClass());
/*     */     
/* 120 */     if (listeners != null) {
/* 121 */       listeners.remove(listener);
/*     */     }
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
/*     */   @NotNull
/*     */   public static <T extends AtlasEvent> T post(@NotNull T event) {
/* 137 */     return post(event, false);
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
/*     */   @NotNull
/*     */   public static <T extends AtlasEvent> T post(@NotNull T event, boolean async) {
/* 152 */     List<AtlasEventListener<?>> listeners = LISTENERS.get(event.getClass());
/*     */     
/* 154 */     if (listeners == null || listeners.isEmpty()) {
/* 155 */       return event;
/*     */     }
/*     */     
/* 158 */     for (AtlasEventListener<?> listener : listeners) {
/* 159 */       AtlasEventListener<T> casted = (AtlasEventListener)listener;
/*     */       
/* 161 */       if (!async) {
/* 162 */         casted.run((AtlasEvent)event); continue;
/*     */       } 
/* 164 */       AtlasCommon.getInstance().getSchedulerProvider().ofAsync(scheduler -> casted.run(event));
/*     */     } 
/*     */ 
/*     */     
/* 168 */     return event;
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\event\AtlasEventBus.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */