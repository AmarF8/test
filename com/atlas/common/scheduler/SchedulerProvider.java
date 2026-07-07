/*     */ package com.atlas.common.scheduler;
/*     */ 
/*     */ import com.atlas.common.AtlasCommon;
/*     */ import com.atlas.common.event.AtlasEventBus;
/*     */ import com.atlas.common.event.tick.AtlasTickEvent;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import java.util.Optional;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.Executors;
/*     */ import java.util.concurrent.ScheduledExecutorService;
/*     */ import java.util.concurrent.ThreadFactory;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import java.util.concurrent.atomic.AtomicLong;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SchedulerProvider
/*     */ {
/*  34 */   private final ConcurrentHashMap<Long, Scheduler> content = new ConcurrentHashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  42 */   private final ArrayList<Scheduler> tickBuffer = new ArrayList<>(128); private final Consumer<Scheduler> stopHandler;
/*     */   private long lastTickTime;
/*  44 */   private final AtomicLong counter = new AtomicLong(0L); public SchedulerProvider() {
/*  45 */     this.stopHandler = (scheduler -> {
/*     */         Scheduler removed = this.content.remove(Long.valueOf(scheduler.getIdentifier()));
/*     */         
/*     */         if (removed != null) {
/*     */           AtlasCommon.getInstance().getLogger().debug("Removed scheduler {} from provider", Long.valueOf(scheduler.getIdentifier()));
/*     */         }
/*     */       });
/*     */     
/*  53 */     this.lastTickTime = System.currentTimeMillis();
/*  54 */     this.tickCount = 0L;
/*  55 */     this.totalTickTime = 0L;
/*  56 */     this.debugEnabled = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  68 */     ThreadFactory threadFactory = runnable -> {
/*     */         Thread thread = new Thread(runnable);
/*     */         
/*     */         thread.setDaemon(true);
/*     */         
/*     */         thread.setName("Atlas-Scheduler-" + this.counter.incrementAndGet());
/*     */         
/*     */         thread.setUncaughtExceptionHandler(());
/*     */         return thread;
/*     */       };
/*  78 */     this.executorService = Executors.newScheduledThreadPool(16, threadFactory);
/*     */ 
/*     */     
/*  81 */     AtlasEventBus.register(AtlasTickEvent.class, this::handleTick);
/*     */ 
/*     */     
/*  84 */     ofAsyncPeriodic(scheduler -> {
/*     */           if (this.debugEnabled) {
/*     */             logDebugStats();
/*     */           }
/*  88 */         }SchedulerDuration.ofSeconds(30L));
/*     */     
/*  90 */     AtlasCommon.getInstance().getLogger().info("SchedulerProvider initialized with {} thread pool", Integer.valueOf(16));
/*     */   }
/*     */   private long tickCount; private long totalTickTime;
/*     */   private volatile boolean debugEnabled;
/*     */   public final ScheduledExecutorService executorService;
/*     */   
/*     */   private void handleTick(AtlasTickEvent event) {
/*  97 */     long tickStart = System.currentTimeMillis();
/*  98 */     this.tickCount++;
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 103 */       ArrayList<Scheduler> syncSchedulers = this.tickBuffer;
/* 104 */       syncSchedulers.clear();
/* 105 */       int completedCount = 0;
/* 106 */       for (Scheduler scheduler : this.content.values()) {
/* 107 */         if (scheduler.getEnvironmentType() != SchedulerEnvironmentType.SYNC)
/* 108 */           continue;  if (scheduler.getStatus() == SchedulerStatus.STOPPED) {
/*     */           
/* 110 */           this.content.remove(Long.valueOf(scheduler.getIdentifier()));
/* 111 */           completedCount++; continue;
/*     */         } 
/* 113 */         syncSchedulers.add(scheduler);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 118 */       int successfulTicks = 0;
/* 119 */       for (Scheduler scheduler : syncSchedulers) {
/*     */         try {
/* 121 */           scheduler.tick();
/* 122 */           successfulTicks++;
/* 123 */         } catch (Exception e) {
/* 124 */           AtlasCommon.getInstance().getLogger().error("Error ticking scheduler {}: {}", new Object[] {
/* 125 */                 Long.valueOf(scheduler.getIdentifier()), e.getMessage(), e
/*     */               });
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 131 */       long tickDuration = System.currentTimeMillis() - tickStart;
/* 132 */       this.totalTickTime += tickDuration;
/* 133 */       this.lastTickTime = System.currentTimeMillis();
/*     */ 
/*     */       
/* 136 */       if (tickDuration > 50L) {
/* 137 */         AtlasCommon.getInstance().getLogger().warn("Slow scheduler tick: {}ms for {} schedulers ({} successful, {} completed)", new Object[] {
/*     */               
/* 139 */               Long.valueOf(tickDuration), Integer.valueOf(syncSchedulers.size()), Integer.valueOf(successfulTicks), Integer.valueOf(completedCount)
/*     */             });
/*     */       }
/*     */ 
/*     */       
/* 144 */       if (this.debugEnabled && this.tickCount % 100L == 0L) {
/* 145 */         AtlasCommon.getInstance().getLogger().debug("Scheduler tick #{}: {}ms, {} sync schedulers, {} total schedulers, avg tick time: {}ms", new Object[] {
/*     */               
/* 147 */               Long.valueOf(this.tickCount), Long.valueOf(tickDuration), Integer.valueOf(syncSchedulers.size()), Integer.valueOf(this.content.size()), 
/* 148 */               Long.valueOf(this.totalTickTime / this.tickCount)
/*     */             });
/*     */       }
/*     */     }
/* 152 */     catch (Exception e) {
/* 153 */       AtlasCommon.getInstance().getLogger().error("Critical error in scheduler tick handler", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Optional<Scheduler> getByIdentifier(long identifier) {
/* 165 */     return Optional.ofNullable(this.content.get(Long.valueOf(identifier)));
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Scheduler ofSync(@NotNull SchedulerRunnable runnable) {
/* 171 */     if (!AtlasCommon.getInstance().getProjectType().isMinecraft()) {
/* 172 */       if (this.debugEnabled) {
/* 173 */         AtlasCommon.getInstance().getLogger().debug("Creating async scheduler instead of sync for non-minecraft project");
/*     */       }
/* 175 */       return ofAsync(runnable);
/*     */     } 
/* 177 */     return createSyncScheduler(runnable, null, null);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public Scheduler ofSync(@NotNull SchedulerRunnable runnable, @NotNull SchedulerDuration delay, @NotNull SchedulerDuration period) {
/* 182 */     if (!AtlasCommon.getInstance().getProjectType().isMinecraft()) {
/* 183 */       return ofAsync(runnable, delay, period);
/*     */     }
/* 185 */     return createSyncScheduler(runnable, delay, period);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public Scheduler ofSyncDelayed(@NotNull SchedulerRunnable runnable, @NotNull SchedulerDuration delay) {
/* 190 */     if (!AtlasCommon.getInstance().getProjectType().isMinecraft()) {
/* 191 */       return ofAsyncDelayed(runnable, delay);
/*     */     }
/* 193 */     return createSyncScheduler(runnable, delay, null);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public Scheduler ofSyncPeriodic(@NotNull SchedulerRunnable runnable, @NotNull SchedulerDuration period) {
/* 198 */     if (!AtlasCommon.getInstance().getProjectType().isMinecraft()) {
/* 199 */       return ofAsyncPeriodic(runnable, period);
/*     */     }
/* 201 */     return createSyncScheduler(runnable, null, period);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public Scheduler ofSyncPeriodic(@NotNull SchedulerRunnable runnable, @NotNull SchedulerDuration delay, @NotNull SchedulerDuration period) {
/* 206 */     if (!AtlasCommon.getInstance().getProjectType().isMinecraft()) {
/* 207 */       return ofAsyncPeriodic(runnable, delay, period);
/*     */     }
/* 209 */     return createSyncScheduler(runnable, delay, period);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Scheduler ofAsync(@NotNull SchedulerRunnable runnable) {
/* 215 */     return createAsyncScheduler(runnable, null, null, false);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public Scheduler ofAsync(@NotNull SchedulerRunnable runnable, @NotNull SchedulerDuration delay, @NotNull SchedulerDuration period) {
/* 220 */     return createAsyncScheduler(runnable, delay, period, true);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public Scheduler ofAsyncDelayed(@NotNull SchedulerRunnable runnable, @NotNull SchedulerDuration delay) {
/* 225 */     return createAsyncScheduler(runnable, delay, null, false);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public Scheduler ofAsyncPeriodic(@NotNull SchedulerRunnable runnable, @NotNull SchedulerDuration period) {
/* 230 */     return createAsyncScheduler(runnable, null, period, true);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public Scheduler ofAsyncPeriodic(@NotNull SchedulerRunnable runnable, @NotNull SchedulerDuration delay, @NotNull SchedulerDuration period) {
/* 235 */     return createAsyncScheduler(runnable, delay, period, true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private Scheduler createSyncScheduler(SchedulerRunnable runnable, SchedulerDuration delay, SchedulerDuration period) {
/* 241 */     Scheduler scheduler = new Scheduler(this.counter.getAndIncrement(), runnable, SchedulerEnvironmentType.SYNC, delay, period, this.stopHandler);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 248 */     this.content.put(Long.valueOf(scheduler.getIdentifier()), scheduler);
/*     */     
/* 250 */     if (this.debugEnabled) {
/* 251 */       AtlasCommon.getInstance().getLogger().debug("Created sync scheduler {}: delay={}ms, period={}ms", new Object[] {
/*     */             
/* 253 */             Long.valueOf(scheduler.getIdentifier()), 
/* 254 */             Long.valueOf((delay != null) ? delay.toMillis() : 0L), 
/* 255 */             Long.valueOf((period != null) ? period.toMillis() : 0L)
/*     */           });
/*     */     }
/*     */     
/* 259 */     return scheduler;
/*     */   }
/*     */ 
/*     */   
/*     */   private Scheduler createAsyncScheduler(SchedulerRunnable runnable, SchedulerDuration delay, SchedulerDuration period, boolean isPeriodic) {
/* 264 */     Scheduler scheduler = new Scheduler(this.counter.getAndIncrement(), runnable, SchedulerEnvironmentType.ASYNC, delay, period, this.stopHandler);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 271 */     this.content.put(Long.valueOf(scheduler.getIdentifier()), scheduler);
/*     */     
/*     */     try {
/* 274 */       if (isPeriodic && period != null) {
/* 275 */         long delayMs = (delay != null) ? delay.toMillis() : 0L;
/* 276 */         long periodMs = period.toMillis();
/*     */         
/* 278 */         Objects.requireNonNull(scheduler); scheduler.registerSchedulerFutureFromExecutor(this.executorService.scheduleAtFixedRate(scheduler::run, delayMs, periodMs, TimeUnit.MILLISECONDS));
/*     */       } else {
/*     */         
/* 281 */         long delayMs = (delay != null) ? delay.toMillis() : 0L;
/*     */         
/* 283 */         Objects.requireNonNull(scheduler); scheduler.registerSchedulerFutureFromExecutor(this.executorService.schedule(scheduler::run, delayMs, TimeUnit.MILLISECONDS));
/*     */       } 
/*     */ 
/*     */       
/* 287 */       if (this.debugEnabled) {
/* 288 */         AtlasCommon.getInstance().getLogger().debug("Created async scheduler {}: delay={}ms, period={}ms, periodic={}", new Object[] {
/*     */               
/* 290 */               Long.valueOf(scheduler.getIdentifier()), 
/* 291 */               Long.valueOf((delay != null) ? delay.toMillis() : 0L), 
/* 292 */               Long.valueOf((period != null) ? period.toMillis() : 0L), 
/* 293 */               Boolean.valueOf(isPeriodic)
/*     */             });
/*     */       }
/*     */     }
/* 297 */     catch (Exception e) {
/* 298 */       AtlasCommon.getInstance().getLogger().error("Failed to schedule async task for scheduler {}", Long.valueOf(scheduler.getIdentifier()), e);
/* 299 */       this.content.remove(Long.valueOf(scheduler.getIdentifier()));
/* 300 */       scheduler.setError(e);
/* 301 */       scheduler.stop();
/*     */     } 
/*     */     
/* 304 */     return scheduler;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDebugStats() {
/* 311 */     int syncCount = 0, asyncCount = 0, stoppedCount = 0;
/* 312 */     long totalExecutions = 0L;
/* 313 */     double totalAvgTime = 0.0D;
/*     */     
/* 315 */     for (Scheduler scheduler : this.content.values()) {
/* 316 */       if (scheduler.getEnvironmentType() == SchedulerEnvironmentType.SYNC) {
/* 317 */         syncCount++;
/*     */       } else {
/* 319 */         asyncCount++;
/*     */       } 
/*     */       
/* 322 */       if (scheduler.getStatus() == SchedulerStatus.STOPPED) {
/* 323 */         stoppedCount++;
/*     */       }
/*     */       
/* 326 */       totalExecutions += scheduler.getTotalExecutions();
/* 327 */       totalAvgTime += scheduler.getAverageExecutionTimeMs();
/*     */     } 
/*     */     
/* 330 */     return String.format("Schedulers[total=%d, sync=%d, async=%d, stopped=%d, executions=%d, avgExecTime=%.2fms, avgTickTime=%.2fms, ticks=%d]", new Object[] {
/*     */           
/* 332 */           Integer.valueOf(this.content.size()), Integer.valueOf(syncCount), Integer.valueOf(asyncCount), Integer.valueOf(stoppedCount), Long.valueOf(totalExecutions), 
/* 333 */           Double.valueOf((this.content.size() > 0) ? (totalAvgTime / this.content.size()) : 0.0D), 
/* 334 */           Double.valueOf((this.tickCount > 0L) ? (this.totalTickTime / this.tickCount) : 0.0D), 
/* 335 */           Long.valueOf(this.tickCount)
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void logDebugStats() {
/* 343 */     AtlasCommon.getInstance().getLogger().info("Scheduler Stats: {}", getDebugStats());
/*     */ 
/*     */     
/* 346 */     for (Scheduler scheduler : this.content.values()) {
/* 347 */       if (scheduler.getAverageExecutionTimeMs() > 10.0D) {
/* 348 */         AtlasCommon.getInstance().getLogger().info("Slow scheduler: {}", scheduler.getDebugInfo());
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDebugEnabled(boolean enabled) {
/* 357 */     this.debugEnabled = enabled;
/* 358 */     AtlasCommon.getInstance().getLogger().info("Scheduler debug logging {}", enabled ? "enabled" : "disabled");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Scheduler> getAllSchedulers() {
/* 365 */     return new ArrayList<>(this.content.values());
/*     */   }
/*     */   
/*     */   public void onUnload() {
/* 369 */     AtlasCommon.getInstance().getLogger().info("Unloading scheduler provider with {} schedulers", Integer.valueOf(this.content.size()));
/*     */ 
/*     */     
/* 372 */     List<Scheduler> schedulersToStop = new ArrayList<>(this.content.values());
/* 373 */     for (Scheduler scheduler : schedulersToStop) {
/*     */       try {
/* 375 */         scheduler.stop();
/* 376 */       } catch (Exception e) {
/* 377 */         AtlasCommon.getInstance().getLogger().error("Error stopping scheduler {} during unload", Long.valueOf(scheduler.getIdentifier()), e);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 382 */     this.executorService.shutdown();
/*     */     try {
/* 384 */       if (!this.executorService.awaitTermination(5L, TimeUnit.SECONDS)) {
/* 385 */         AtlasCommon.getInstance().getLogger().warn("Scheduler executor service did not terminate gracefully, forcing shutdown");
/* 386 */         this.executorService.shutdownNow();
/*     */       } 
/* 388 */     } catch (InterruptedException e) {
/* 389 */       AtlasCommon.getInstance().getLogger().warn("Interrupted while waiting for executor service to terminate");
/* 390 */       this.executorService.shutdownNow();
/* 391 */       Thread.currentThread().interrupt();
/*     */     } 
/*     */     
/* 394 */     AtlasCommon.getInstance().getLogger().info("Scheduler provider unloaded");
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\scheduler\SchedulerProvider.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */