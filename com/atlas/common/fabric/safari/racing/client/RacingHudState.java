/*     */ package com.atlas.common.fabric.safari.racing.client;
/*     */ 
/*     */ import com.atlas.common.fabric.safari.racing.packet.RacingTimingPacket;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.fabricmc.api.EnvType;
/*     */ import net.fabricmc.api.Environment;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Environment(EnvType.CLIENT)
/*     */ public final class RacingHudState
/*     */ {
/*     */   private static final long STALE_TIMEOUT_MILLIS = 5000L;
/*  18 */   private static final RacingHudState INSTANCE = new RacingHudState(); private boolean active; private boolean waitingForStart;
/*     */   
/*     */   public static RacingHudState getInstance() {
/*  21 */     return INSTANCE;
/*     */   }
/*     */ 
/*     */   
/*     */   private long currentLapTimeMillis;
/*     */   
/*     */   private long personalBestMillis;
/*     */   
/*     */   private long eventRemainingMillis;
/*     */   
/*     */   private int currentCheckpoint;
/*     */   private int totalCheckpoints;
/*     */   private int playerPosition;
/*     */   private int lapCount;
/*  35 */   private final List<RacingTimingPacket.CheckpointView> checkpoints = new ArrayList<>();
/*     */ 
/*     */ 
/*     */   
/*  39 */   private String splitText = "";
/*  40 */   private int splitColor = -1;
/*     */ 
/*     */   
/*     */   private long splitShowTimeMillis;
/*     */   
/*  45 */   private final List<RacingLeaderboardEntry> leaderboard = new ArrayList<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private long lastUpdateMillis;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateFromTimingPacket(boolean active, boolean waitingForStart, long currentLapTimeMillis, long personalBestMillis, long eventRemainingMillis, int currentCheckpoint, int totalCheckpoints, int playerPosition, int lapCount, List<RacingLeaderboardEntry> leaderboardEntries, List<RacingTimingPacket.CheckpointView> checkpointViews) {
/*  61 */     this.active = active;
/*  62 */     this.waitingForStart = waitingForStart;
/*  63 */     this.currentLapTimeMillis = currentLapTimeMillis;
/*  64 */     this.personalBestMillis = personalBestMillis;
/*  65 */     this.eventRemainingMillis = eventRemainingMillis;
/*  66 */     this.currentCheckpoint = currentCheckpoint;
/*  67 */     this.totalCheckpoints = totalCheckpoints;
/*  68 */     this.playerPosition = playerPosition;
/*  69 */     this.lapCount = lapCount;
/*  70 */     this.lastUpdateMillis = System.currentTimeMillis();
/*     */     
/*  72 */     this.leaderboard.clear();
/*  73 */     this.leaderboard.addAll(leaderboardEntries);
/*  74 */     this.checkpoints.clear();
/*  75 */     this.checkpoints.addAll(checkpointViews);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateFromCheckpointPacket(int checkpointIndex, long splitTimeMillis, long deltaMillis, boolean purpleSector, boolean personalBest) {
/*  82 */     String deltaStr = (deltaMillis == 0L) ? "" : ((deltaMillis <= 0L) ? ("-" + formatTime(Math.abs(deltaMillis))) : ("+" + formatTime(deltaMillis)));
/*  83 */     String label = purpleSector ? " PURPLE" : (personalBest ? " PB" : "");
/*  84 */     this.splitText = "Ring " + checkpointIndex + 1 + "  " + formatTime(splitTimeMillis) + "  " + deltaStr + label;
/*  85 */     this.splitColor = purpleSector ? -6283024 : ((personalBest || deltaMillis <= 0L) ? -16711936 : -48060);
/*  86 */     this.splitShowTimeMillis = System.currentTimeMillis();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/*  92 */     this.active = false;
/*  93 */     this.waitingForStart = false;
/*  94 */     this.currentLapTimeMillis = 0L;
/*  95 */     this.personalBestMillis = -1L;
/*  96 */     this.eventRemainingMillis = 0L;
/*  97 */     this.currentCheckpoint = 0;
/*  98 */     this.totalCheckpoints = 0;
/*  99 */     this.playerPosition = -1;
/* 100 */     this.lapCount = 0;
/* 101 */     this.splitText = "";
/* 102 */     this.splitShowTimeMillis = 0L;
/* 103 */     this.lastUpdateMillis = 0L;
/* 104 */     this.leaderboard.clear();
/* 105 */     this.checkpoints.clear();
/*     */   }
/*     */   
/*     */   public void clearIfStale() {
/* 109 */     if (!this.active)
/* 110 */       return;  if (this.lastUpdateMillis <= 0L)
/* 111 */       return;  if (System.currentTimeMillis() - this.lastUpdateMillis > 5000L) {
/* 112 */       clear();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isActive() {
/* 118 */     return this.active;
/* 119 */   } public boolean isWaitingForStart() { return this.waitingForStart; } public int getLapCount() {
/* 120 */     return this.lapCount;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public long getInterpolatedLapTimeMillis() {
/* 126 */     if (this.waitingForStart || !this.active) return this.currentLapTimeMillis; 
/* 127 */     long elapsed = System.currentTimeMillis() - this.lastUpdateMillis;
/* 128 */     return this.currentLapTimeMillis + elapsed;
/*     */   }
/*     */   public long getPersonalBestMillis() {
/* 131 */     return this.personalBestMillis;
/*     */   }
/*     */   public long getInterpolatedEventRemainingMillis() {
/* 134 */     long elapsed = System.currentTimeMillis() - this.lastUpdateMillis;
/* 135 */     return Math.max(0L, this.eventRemainingMillis - elapsed);
/*     */   }
/*     */   
/* 138 */   public int getCurrentCheckpoint() { return this.currentCheckpoint; }
/* 139 */   public int getTotalCheckpoints() { return this.totalCheckpoints; }
/* 140 */   public int getPlayerPosition() { return this.playerPosition; }
/* 141 */   public List<RacingLeaderboardEntry> getLeaderboard() { return this.leaderboard; } public List<RacingTimingPacket.CheckpointView> getCheckpoints() {
/* 142 */     return this.checkpoints;
/*     */   }
/* 144 */   public String getSplitText() { return this.splitText; } public int getSplitColor() {
/* 145 */     return this.splitColor;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getSplitOpacity() {
/* 151 */     long age = System.currentTimeMillis() - this.splitShowTimeMillis;
/* 152 */     if (age > 3000L) return 0.0F; 
/* 153 */     if (age > 2000L) return 1.0F - (float)(age - 2000L) / 1000.0F; 
/* 154 */     return 1.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static String formatTime(long millis) {
/* 160 */     if (millis <= 0L) return "0:00.000"; 
/* 161 */     long minutes = millis / 60000L;
/* 162 */     long seconds = millis % 60000L / 1000L;
/* 163 */     long ms = millis % 1000L;
/* 164 */     return String.format("%d:%02d.%03d", new Object[] { Long.valueOf(minutes), Long.valueOf(seconds), Long.valueOf(ms) });
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\safari\racing\client\RacingHudState.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */