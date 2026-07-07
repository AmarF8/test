/*     */ package com.atlas.common.scheduler;
/*     */ 
/*     */ import com.atlas.common.AtlasCommon;
/*     */ import java.util.Objects;
/*     */ import java.util.concurrent.ScheduledFuture;
/*     */ import java.util.function.Consumer;
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
/*     */ public final class Scheduler
/*     */ {
/*     */   private final long identifier;
/*     */   @NotNull
/*     */   private final SchedulerRunnable job;
/*     */   @NotNull
/*     */   private final SchedulerEnvironmentType environmentType;
/*     */   @Nullable
/*     */   private final SchedulerDuration delay;
/*     */   @Nullable
/*     */   private final SchedulerDuration period;
/*     */   @NotNull
/*     */   private final Consumer<Scheduler> stopHandler;
/*     */   private Exception error;
/*     */   private final int delayTicks;
/*     */   private final int periodTicks;
/*     */   private final long creationTime;
/*     */   private long lastExecutionTime;
/*     */   private int totalExecutions;
/*     */   private long totalExecutionTimeMs;
/*     */   private boolean debugEnabled = false;
/*     */   @NotNull
/*  44 */   private SchedulerStatus status = SchedulerStatus.RUNNING;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int delayTicksPassed;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int periodTicksPassed;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int elapsedPeriods;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private volatile ScheduledFuture<?> future;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Scheduler(long identifier, @NotNull SchedulerRunnable job, @NotNull SchedulerEnvironmentType environmentType, @Nullable SchedulerDuration delay, @Nullable SchedulerDuration period, @NotNull Consumer<Scheduler> stopHandler) {
/*  81 */     this.identifier = identifier;
/*  82 */     this.job = Objects.<SchedulerRunnable>requireNonNull(job);
/*  83 */     this.environmentType = Objects.<SchedulerEnvironmentType>requireNonNull(environmentType);
/*  84 */     this.delay = delay;
/*  85 */     this.period = period;
/*  86 */     this.delayTicks = (delay == null) ? 0 : ((int)delay.toMillis() / 50);
/*  87 */     this.periodTicks = (period == null) ? 0 : ((int)period.toMillis() / 50);
/*  88 */     this.stopHandler = Objects.<Consumer<Scheduler>>requireNonNull(stopHandler);
/*  89 */     this.creationTime = System.currentTimeMillis();
/*     */     
/*  91 */     if (this.debugEnabled) {
/*  92 */       AtlasCommon.getInstance().getLogger().debug("Scheduler {} created: type={}, delay={}ms ({}ticks), period={}ms ({}ticks)", new Object[] {
/*     */             
/*  94 */             Long.valueOf(identifier), environmentType, 
/*  95 */             Long.valueOf((delay != null) ? delay.toMillis() : 0L), Integer.valueOf(this.delayTicks), 
/*  96 */             Long.valueOf((period != null) ? period.toMillis() : 0L), Integer.valueOf(this.periodTicks)
/*     */           });
/*     */     }
/*     */   }
/*     */   
/*     */   public long getIdentifier() {
/* 102 */     return this.identifier; } @NotNull
/* 103 */   public SchedulerRunnable getJob() { return this.job; } @NotNull
/* 104 */   public SchedulerEnvironmentType getEnvironmentType() { return this.environmentType; } @Nullable
/* 105 */   public SchedulerDuration getDelay() { return this.delay; } @Nullable
/* 106 */   public SchedulerDuration getPeriod() { return this.period; }
/* 107 */   public int getDelayTicks() { return this.delayTicks; }
/* 108 */   public int getPeriodTicks() { return this.periodTicks; }
/* 109 */   public boolean isCompleted() { return (this.status == SchedulerStatus.STOPPED); } @NotNull
/* 110 */   public SchedulerStatus getStatus() { return this.status; } @Nullable
/* 111 */   public Exception getError() { return this.error; }
/* 112 */   public boolean hasAnyError() { return (this.error != null); } public int getElapsedPeriods() {
/* 113 */     return this.elapsedPeriods;
/*     */   }
/*     */   
/* 116 */   public int getTotalExecutions() { return this.totalExecutions; } public long getTotalExecutionTimeMs() {
/* 117 */     return this.totalExecutionTimeMs;
/*     */   } public double getAverageExecutionTimeMs() {
/* 119 */     return (this.totalExecutions > 0) ? (this.totalExecutionTimeMs / this.totalExecutions) : 0.0D;
/*     */   } public long getTimeSinceCreationMs() {
/* 121 */     return System.currentTimeMillis() - this.creationTime;
/*     */   } public long getTimeSinceLastExecutionMs() {
/* 123 */     return (this.lastExecutionTime > 0L) ? (System.currentTimeMillis() - this.lastExecutionTime) : -1L;
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
/*     */   public void consumeSafe(@NotNull Runnable runnable, boolean announceError) {
/*     */     try {
/* 137 */       runnable.run();
/* 138 */     } catch (Exception throwable) {
/* 139 */       if (announceError) {
/* 140 */         AtlasCommon.getInstance().getLogger().error("Error while running scheduler {}", Long.valueOf(this.identifier), throwable);
/* 141 */         throw throwable;
/*     */       } 
/* 143 */       setError(throwable);
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
/*     */   public void setError(@NotNull Exception error) {
/* 156 */     this.error = Objects.<Exception>requireNonNull(error);
/* 157 */     if (this.debugEnabled) {
/* 158 */       AtlasCommon.getInstance().getLogger().error("Scheduler {} encountered error after {} executions", new Object[] {
/* 159 */             Long.valueOf(this.identifier), Integer.valueOf(this.totalExecutions), error
/*     */           });
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerSchedulerFutureFromExecutor(@NotNull ScheduledFuture<?> future) {
/* 169 */     this.future = Objects.<ScheduledFuture>requireNonNull(future);
/* 170 */     if (this.debugEnabled) {
/* 171 */       AtlasCommon.getInstance().getLogger().debug("Scheduler {} registered future: {}", Long.valueOf(this.identifier), future);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void stop() {
/* 179 */     if (this.status == SchedulerStatus.STOPPED) {
/* 180 */       if (this.debugEnabled) {
/* 181 */         AtlasCommon.getInstance().getLogger().warn("Attempted to stop already stopped scheduler {}", Long.valueOf(this.identifier));
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/* 186 */     if (this.debugEnabled) {
/* 187 */       AtlasCommon.getInstance().getLogger().debug("Stopping scheduler {}: executions={}, avgTime={}ms, totalTime={}ms", new Object[] {
/*     */             
/* 189 */             Long.valueOf(this.identifier), Integer.valueOf(this.totalExecutions), Double.valueOf(getAverageExecutionTimeMs()), Long.valueOf(getTimeSinceCreationMs())
/*     */           });
/*     */     }
/*     */     
/* 193 */     this.status = SchedulerStatus.STOPPED;
/*     */ 
/*     */     
/* 196 */     ScheduledFuture<?> currentFuture = this.future;
/* 197 */     if (currentFuture != null) {
/* 198 */       boolean cancelled = currentFuture.cancel(true);
/* 199 */       if (this.debugEnabled) {
/* 200 */         AtlasCommon.getInstance().getLogger().debug("Scheduler {} future cancelled: {}", Long.valueOf(this.identifier), Boolean.valueOf(cancelled));
/*     */       }
/*     */     } 
/* 203 */     this.future = null;
/*     */     
/*     */     try {
/* 206 */       this.stopHandler.accept(this);
/* 207 */     } catch (Exception e) {
/* 208 */       AtlasCommon.getInstance().getLogger().error("Error in stop handler for scheduler {}", Long.valueOf(this.identifier), e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/* 217 */     if (this.environmentType == SchedulerEnvironmentType.ASYNC) {
/* 218 */       AtlasCommon.getInstance().getLogger().error("Cannot tick async scheduler {}", Long.valueOf(this.identifier));
/* 219 */       throw new IllegalStateException("Cannot tick async scheduler");
/*     */     } 
/*     */     
/* 222 */     if (this.status != SchedulerStatus.RUNNING) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 227 */     if (this.elapsedPeriods == 0) {
/* 228 */       if (this.delayTicks > 0 && this.delayTicksPassed < this.delayTicks) {
/* 229 */         this.delayTicksPassed++;
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 234 */       executeTask();
/* 235 */       this.periodTicksPassed = 0;
/*     */ 
/*     */       
/* 238 */       if (this.period == null) {
/* 239 */         stop();
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 245 */     if (this.period != null) {
/* 246 */       if (this.periodTicksPassed < this.periodTicks) {
/* 247 */         this.periodTicksPassed++;
/*     */         
/*     */         return;
/*     */       } 
/* 251 */       executeTask();
/* 252 */       this.periodTicksPassed = 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void run() {
/* 262 */     if (this.environmentType == SchedulerEnvironmentType.SYNC) {
/* 263 */       AtlasCommon.getInstance().getLogger().error("Cannot run sync scheduler {} async", Long.valueOf(this.identifier));
/* 264 */       throw new IllegalStateException("Cannot run sync scheduler");
/*     */     } 
/* 266 */     if (this.status != SchedulerStatus.RUNNING) {
/* 267 */       if (this.debugEnabled) {
/* 268 */         AtlasCommon.getInstance().getLogger().debug("Skipping run for non-running scheduler {} (status: {})", 
/* 269 */             Long.valueOf(this.identifier), this.status);
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 275 */     executeTask();
/*     */ 
/*     */     
/* 278 */     if (this.period == null) {
/* 279 */       if (this.debugEnabled) {
/* 280 */         AtlasCommon.getInstance().getLogger().debug("Scheduler {} completed (no period)", Long.valueOf(this.identifier));
/*     */       }
/* 282 */       stop();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void executeTask() {
/* 290 */     long startTime = System.currentTimeMillis();
/* 291 */     boolean successful = false;
/*     */     
/*     */     try {
/* 294 */       if (this.debugEnabled && this.totalExecutions % 100 == 0 && this.totalExecutions > 0) {
/* 295 */         AtlasCommon.getInstance().getLogger().debug("Scheduler {} executing task #{} (avg: {}ms)", new Object[] {
/*     */               
/* 297 */               Long.valueOf(this.identifier), Integer.valueOf(this.totalExecutions + 1), Double.valueOf(getAverageExecutionTimeMs())
/*     */             });
/*     */       }
/*     */       
/* 301 */       this.job.run(this);
/* 302 */       successful = true;
/*     */     }
/* 304 */     catch (Exception throwable) {
/* 305 */       AtlasCommon.getInstance().getLogger().error("Error while running scheduler {}", Long.valueOf(this.identifier), throwable);
/* 306 */       setError(throwable);
/* 307 */       stop();
/*     */       return;
/*     */     } finally {
/* 310 */       long executionTime = System.currentTimeMillis() - startTime;
/* 311 */       this.lastExecutionTime = System.currentTimeMillis();
/* 312 */       this.totalExecutions++;
/* 313 */       this.totalExecutionTimeMs += executionTime;
/* 314 */       this.elapsedPeriods++;
/*     */       
/* 316 */       if (this.debugEnabled && (executionTime > 50L || !successful)) {
/* 317 */         AtlasCommon.getInstance().getLogger().warn("Scheduler {} execution #{} took {}ms (success: {})", new Object[] {
/*     */               
/* 319 */               Long.valueOf(this.identifier), Integer.valueOf(this.totalExecutions), Long.valueOf(executionTime), Boolean.valueOf(successful)
/*     */             });
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDebugInfo() {
/* 329 */     return String.format("Scheduler[id=%d, type=%s, status=%s, executions=%d, avgTime=%.2fms, age=%dms, lastExec=%dms ago]", new Object[] {
/*     */           
/* 331 */           Long.valueOf(this.identifier), this.environmentType, this.status, Integer.valueOf(this.totalExecutions), 
/* 332 */           Double.valueOf(getAverageExecutionTimeMs()), Long.valueOf(getTimeSinceCreationMs()), Long.valueOf(getTimeSinceLastExecutionMs())
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDebugEnabled(boolean enabled) {
/* 340 */     this.debugEnabled = enabled;
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\scheduler\Scheduler.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */