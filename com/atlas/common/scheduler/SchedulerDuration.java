/*     */ package com.atlas.common.scheduler;
/*     */ 
/*     */ import com.atlas.common.utility.TimeUtility;
/*     */ import java.time.Duration;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class SchedulerDuration
/*     */ {
/*     */   @NotNull
/*  15 */   public static final SchedulerDuration ZERO = new SchedulerDuration(0L); @NotNull
/*  16 */   public static final SchedulerDuration ONE_TICK = new SchedulerDuration(1L); @NotNull
/*  17 */   public static final SchedulerDuration ONE_SECOND = new SchedulerDuration(Duration.ofSeconds(1L)); @NotNull
/*  18 */   public static final SchedulerDuration ONE_MINUTE = new SchedulerDuration(Duration.ofMinutes(1L));
/*     */ 
/*     */   
/*     */   private final long ticks;
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   private final Duration duration;
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static SchedulerDuration ofTicks(long ticks) {
/*  30 */     return new SchedulerDuration(ticks);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static SchedulerDuration ofSeconds(long seconds) {
/*  41 */     return new SchedulerDuration(Duration.ofSeconds(seconds));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static SchedulerDuration ofMinutes(long minutes) {
/*  52 */     return new SchedulerDuration(Duration.ofMinutes(minutes));
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
/*     */   public static SchedulerDuration from(@NotNull Duration duration) {
/*  65 */     return new SchedulerDuration(duration);
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
/*     */   public static SchedulerDuration fromMillis(long millis) {
/*  77 */     return new SchedulerDuration(Duration.ofMillis(millis));
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
/*     */   public static SchedulerDuration fromString(@NotNull String durationAsString) {
/*  90 */     return new SchedulerDuration(TimeUtility.fromReadableString(durationAsString));
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SchedulerDuration(@NotNull Duration duration) {
/* 109 */     this(duration.toMillis() / 50L);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SchedulerDuration(long ticks) {
/* 119 */     if (ticks < 0L) throw new IllegalArgumentException("Ticks cannot be less than 0!"); 
/* 120 */     this.ticks = ticks;
/* 121 */     this.duration = Duration.ofMillis(ticks * 50L);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getTicks() {
/* 128 */     return this.ticks;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Duration getDuration() {
/* 136 */     return this.duration;
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
/*     */   public SchedulerDuration add(@NotNull SchedulerDuration schedulerDuration) {
/* 148 */     return new SchedulerDuration(this.ticks + schedulerDuration.ticks);
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
/*     */   public SchedulerDuration add(@NotNull Number number) {
/* 161 */     if (number.longValue() <= 0L) throw new IllegalArgumentException("Number cannot be less or equal to 0!"); 
/* 162 */     return new SchedulerDuration(this.ticks + getDuration().plusMillis(number.longValue()).toMillis() / 50L);
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
/*     */   public SchedulerDuration subtract(@NotNull SchedulerDuration schedulerDuration) {
/* 174 */     return new SchedulerDuration(this.ticks - schedulerDuration.ticks);
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
/*     */   public SchedulerDuration subtract(@NotNull Number number) {
/* 187 */     if (number.longValue() <= 0L) throw new IllegalArgumentException("Number cannot be less or equal to 0!"); 
/* 188 */     return new SchedulerDuration(this.ticks - getDuration().minusMillis(number.longValue()).toMillis() / 50L);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public SchedulerDuration multiply(long multiplier) {
/* 199 */     return new SchedulerDuration(this.ticks * multiplier);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public SchedulerDuration divide(long divisor) {
/* 210 */     return new SchedulerDuration(this.ticks / divisor);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long toMillis() {
/* 217 */     return this.ticks * 50L;
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\scheduler\SchedulerDuration.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */