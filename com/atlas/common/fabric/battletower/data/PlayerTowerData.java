/*     */ package com.atlas.common.fabric.battletower.data;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.class_2487;
/*     */ import net.minecraft.class_2520;
/*     */ import net.minecraft.class_7225;
/*     */ 
/*     */ 
/*     */ public class PlayerTowerData
/*     */ {
/*     */   private final UUID playerUUID;
/*  15 */   private String playerName = "Unknown";
/*  16 */   private int currentFloor = 1;
/*  17 */   private int highestFloor = 1;
/*  18 */   private int currentStreak = 0;
/*  19 */   private int bestStreak = 0;
/*  20 */   private int bpBalance = 0;
/*  21 */   private int totalWins = 0;
/*  22 */   private int totalLosses = 0;
/*  23 */   private Map<String, Integer> highestFloorByMode = new HashMap<>();
/*  24 */   private Map<String, Integer> bestStreakByMode = new HashMap<>();
/*     */   private boolean runActive = false;
/*     */   private boolean partyLockedIn = false;
/*  27 */   private TeamSnapshot teamSnapshot = new TeamSnapshot();
/*  28 */   private BattleSettings battleSettings = BattleSettings.createDefault();
/*  29 */   private long lastRunEndTime = 0L;
/*     */   
/*     */   public PlayerTowerData(UUID playerUUID) {
/*  32 */     this.playerUUID = playerUUID;
/*     */   }
/*     */   
/*     */   public UUID getPlayerUUID() {
/*  36 */     return this.playerUUID;
/*     */   }
/*     */   
/*     */   public String getPlayerName() {
/*  40 */     return this.playerName;
/*     */   }
/*     */   
/*     */   public int getCurrentFloor() {
/*  44 */     return this.currentFloor;
/*     */   }
/*     */   
/*     */   public int getHighestFloor() {
/*  48 */     return this.highestFloor;
/*     */   }
/*     */   
/*     */   public int getCurrentStreak() {
/*  52 */     return this.currentStreak;
/*     */   }
/*     */   
/*     */   public int getBestStreak() {
/*  56 */     return this.bestStreak;
/*     */   }
/*     */   
/*     */   public int getBpBalance() {
/*  60 */     return this.bpBalance;
/*     */   }
/*     */   
/*     */   public int getTotalWins() {
/*  64 */     return this.totalWins;
/*     */   }
/*     */   
/*     */   public int getTotalLosses() {
/*  68 */     return this.totalLosses;
/*     */   }
/*     */   
/*     */   public boolean isRunActive() {
/*  72 */     return this.runActive;
/*     */   }
/*     */   
/*     */   public boolean isPartyLockedIn() {
/*  76 */     return this.partyLockedIn;
/*     */   }
/*     */   
/*     */   public TeamSnapshot getTeamSnapshot() {
/*  80 */     return this.teamSnapshot;
/*     */   }
/*     */   
/*     */   public BattleSettings getBattleSettings() {
/*  84 */     return this.battleSettings;
/*     */   }
/*     */   
/*     */   public int getHighestFloorForMode(BattleSettings.BattleMode mode) {
/*  88 */     return ((Integer)this.highestFloorByMode.getOrDefault(mode.name(), Integer.valueOf(0))).intValue();
/*     */   }
/*     */   
/*     */   public int getBestStreakForMode(BattleSettings.BattleMode mode) {
/*  92 */     return ((Integer)this.bestStreakByMode.getOrDefault(mode.name(), Integer.valueOf(0))).intValue();
/*     */   }
/*     */   
/*     */   public Map<String, Integer> getAllHighestFloorsByMode() {
/*  96 */     return new HashMap<>(this.highestFloorByMode);
/*     */   }
/*     */   
/*     */   public Map<String, Integer> getAllBestStreaksByMode() {
/* 100 */     return new HashMap<>(this.bestStreakByMode);
/*     */   }
/*     */   
/*     */   public void setPlayerName(String name) {
/* 104 */     this.playerName = name;
/*     */   }
/*     */   
/* 107 */   public void setCurrentFloor(int floor) { this.currentFloor = Math.max(1, floor); }
/* 108 */   public void setHighestFloor(int floor) { this.highestFloor = Math.max(1, floor); }
/* 109 */   public void setCurrentStreak(int streak) { this.currentStreak = streak; }
/* 110 */   public void setBestStreak(int streak) { this.bestStreak = streak; }
/* 111 */   public void setBpBalance(int bp) { this.bpBalance = bp; }
/* 112 */   public void setTotalWins(int wins) { this.totalWins = wins; }
/* 113 */   public void setTotalLosses(int losses) { this.totalLosses = losses; }
/* 114 */   public void setRunActive(boolean active) { this.runActive = active; }
/* 115 */   public void setPartyLockedIn(boolean locked) { this.partyLockedIn = locked; } public void setLastRunEndTime(long time) {
/* 116 */     this.lastRunEndTime = time;
/*     */   }
/*     */   public void setHighestFloorByMode(Map<String, Integer> map) {
/* 119 */     this.highestFloorByMode = new HashMap<>(map);
/*     */   }
/*     */   
/*     */   public void setBestStreakByMode(Map<String, Integer> map) {
/* 123 */     this.bestStreakByMode = new HashMap<>(map);
/*     */   }
/*     */   
/*     */   public long getLastRunEndTime() {
/* 127 */     return this.lastRunEndTime;
/*     */   }
/*     */   
/*     */   public void lockInTeam(TeamSnapshot snapshot, BattleSettings settings) {
/* 131 */     this.partyLockedIn = true;
/* 132 */     this.teamSnapshot = snapshot;
/* 133 */     this.battleSettings = settings;
/*     */   }
/*     */   
/*     */   public int getRemainingCooldown() {
/* 137 */     int cooldownSeconds = BattleTowerDataProvider.getRunCooldownSeconds();
/* 138 */     if (cooldownSeconds > 0 && this.lastRunEndTime != 0L) {
/* 139 */       long currentTime = System.currentTimeMillis();
/* 140 */       long elapsedSeconds = (currentTime - this.lastRunEndTime) / 1000L;
/* 141 */       int remaining = cooldownSeconds - (int)elapsedSeconds;
/* 142 */       return Math.max(0, remaining);
/*     */     } 
/* 144 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canStartRun() {
/* 149 */     return (getRemainingCooldown() == 0);
/*     */   }
/*     */   
/*     */   public void startRun() {
/* 153 */     if (this.partyLockedIn) {
/* 154 */       this.runActive = true;
/* 155 */       this.currentFloor = 1;
/* 156 */       this.currentStreak = 0;
/*     */     } 
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public void startRun(TeamSnapshot snapshot, BattleSettings settings) {
/* 162 */     lockInTeam(snapshot, settings);
/* 163 */     startRun();
/*     */   }
/*     */   
/*     */   public int forfeitRun() {
/* 167 */     return endRun(false);
/*     */   }
/*     */   
/*     */   public int endRun(boolean defeated) {
/* 171 */     if (!this.runActive && !this.partyLockedIn) {
/* 172 */       return 0;
/*     */     }
/* 174 */     int earnedBP = this.runActive ? calculateRunBP() : 0;
/* 175 */     this.bpBalance += earnedBP;
/* 176 */     if (defeated) {
/* 177 */       this.totalLosses++;
/*     */     }
/*     */     
/* 180 */     this.lastRunEndTime = System.currentTimeMillis();
/* 181 */     this.runActive = false;
/* 182 */     this.partyLockedIn = false;
/* 183 */     this.teamSnapshot.clear();
/* 184 */     this.currentFloor = 1;
/* 185 */     this.currentStreak = 0;
/* 186 */     return earnedBP;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onWin() {
/* 191 */     if (this.runActive) {
/* 192 */       this.totalWins++;
/* 193 */       this.currentStreak++;
/* 194 */       this.currentFloor++;
/* 195 */       if (this.currentFloor > this.highestFloor) {
/* 196 */         this.highestFloor = this.currentFloor;
/*     */       }
/*     */       
/* 199 */       if (this.currentStreak > this.bestStreak) {
/* 200 */         this.bestStreak = this.currentStreak;
/*     */       }
/*     */       
/* 203 */       String modeName = this.battleSettings.getBattleMode().name();
/* 204 */       int modeHighest = ((Integer)this.highestFloorByMode.getOrDefault(modeName, Integer.valueOf(0))).intValue();
/* 205 */       int modeBestStreak = ((Integer)this.bestStreakByMode.getOrDefault(modeName, Integer.valueOf(0))).intValue();
/* 206 */       if (this.currentFloor > modeHighest) {
/* 207 */         this.highestFloorByMode.put(modeName, Integer.valueOf(this.currentFloor));
/*     */       }
/*     */       
/* 210 */       if (this.currentStreak > modeBestStreak) {
/* 211 */         this.bestStreakByMode.put(modeName, Integer.valueOf(this.currentStreak));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private int calculateRunBP() {
/* 218 */     boolean usedLegendaries = this.battleSettings.isAllowLegendaries();
/* 219 */     return BattleTowerDataProvider.calculateRunBP(this.currentFloor, usedLegendaries);
/*     */   }
/*     */   
/*     */   public int onLoss() {
/* 223 */     return endRun(true);
/*     */   }
/*     */   
/*     */   public boolean spendBP(int amount) {
/* 227 */     if (this.bpBalance >= amount) {
/* 228 */       this.bpBalance -= amount;
/* 229 */       return true;
/*     */     } 
/* 231 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addBP(int amount) {
/* 236 */     this.bpBalance += amount;
/*     */   }
/*     */   
/*     */   public void resetLeaderboardData() {
/* 240 */     this.highestFloor = 1;
/* 241 */     this.bestStreak = 0;
/* 242 */     this.highestFloorByMode.clear();
/* 243 */     this.bestStreakByMode.clear();
/*     */   }
/*     */   
/*     */   public class_2487 toNbt(class_7225.class_7874 registries) {
/* 247 */     class_2487 tag = new class_2487();
/* 248 */     tag.method_10582("playerName", this.playerName);
/* 249 */     tag.method_10569("currentFloor", this.currentFloor);
/* 250 */     tag.method_10569("highestFloor", this.highestFloor);
/* 251 */     tag.method_10569("currentStreak", this.currentStreak);
/* 252 */     tag.method_10569("bestStreak", this.bestStreak);
/* 253 */     tag.method_10569("bpBalance", this.bpBalance);
/* 254 */     tag.method_10569("totalWins", this.totalWins);
/* 255 */     tag.method_10569("totalLosses", this.totalLosses);
/* 256 */     tag.method_10556("runActive", this.runActive);
/* 257 */     tag.method_10556("partyLockedIn", this.partyLockedIn);
/* 258 */     tag.method_10544("lastRunEndTime", this.lastRunEndTime);
/* 259 */     class_2487 floorsByMode = new class_2487();
/* 260 */     Iterator<Map.Entry<String, Integer>> var4 = this.highestFloorByMode.entrySet().iterator();
/*     */     
/* 262 */     while (var4.hasNext()) {
/* 263 */       Map.Entry<String, Integer> entry = var4.next();
/* 264 */       floorsByMode.method_10569(entry.getKey(), ((Integer)entry.getValue()).intValue());
/*     */     } 
/*     */     
/* 267 */     tag.method_10566("highestFloorByMode", (class_2520)floorsByMode);
/* 268 */     class_2487 streaksByMode = new class_2487();
/* 269 */     Iterator<Map.Entry<String, Integer>> var10 = this.bestStreakByMode.entrySet().iterator();
/*     */     
/* 271 */     while (var10.hasNext()) {
/* 272 */       Map.Entry<String, Integer> entry = var10.next();
/* 273 */       streaksByMode.method_10569(entry.getKey(), ((Integer)entry.getValue()).intValue());
/*     */     } 
/*     */     
/* 276 */     tag.method_10566("bestStreakByMode", (class_2520)streaksByMode);
/* 277 */     if (this.teamSnapshot.hasSnapshot()) {
/* 278 */       tag.method_10566("teamSnapshot", (class_2520)this.teamSnapshot.toNbt(registries));
/*     */     }
/*     */     
/* 281 */     tag.method_10566("battleSettings", (class_2520)this.battleSettings.toNbt());
/* 282 */     return tag;
/*     */   }
/*     */   
/*     */   public static PlayerTowerData fromNbt(UUID playerUUID, class_2487 tag, class_7225.class_7874 registries) {
/* 286 */     PlayerTowerData data = new PlayerTowerData(playerUUID);
/* 287 */     data.playerName = tag.method_10545("playerName") ? tag.method_10558("playerName") : "Unknown";
/* 288 */     data.currentFloor = tag.method_10550("currentFloor");
/* 289 */     data.highestFloor = tag.method_10550("highestFloor");
/* 290 */     data.currentStreak = tag.method_10550("currentStreak");
/* 291 */     data.bestStreak = tag.method_10550("bestStreak");
/* 292 */     data.bpBalance = tag.method_10550("bpBalance");
/* 293 */     data.totalWins = tag.method_10550("totalWins");
/* 294 */     data.totalLosses = tag.method_10550("totalLosses");
/* 295 */     data.runActive = tag.method_10577("runActive");
/* 296 */     data.partyLockedIn = tag.method_10577("partyLockedIn");
/* 297 */     data.lastRunEndTime = tag.method_10537("lastRunEndTime");
/* 298 */     if (tag.method_10545("highestFloorByMode")) {
/* 299 */       class_2487 floorsTag = tag.method_10562("highestFloorByMode");
/* 300 */       for (String key : floorsTag.method_10541()) {
/* 301 */         data.highestFloorByMode.put(key, Integer.valueOf(floorsTag.method_10550(key)));
/*     */       }
/*     */     } 
/*     */     
/* 305 */     if (tag.method_10545("bestStreakByMode")) {
/* 306 */       class_2487 streaksTag = tag.method_10562("bestStreakByMode");
/* 307 */       for (String key : streaksTag.method_10541()) {
/* 308 */         data.bestStreakByMode.put(key, Integer.valueOf(streaksTag.method_10550(key)));
/*     */       }
/*     */     } 
/*     */     
/* 312 */     if (tag.method_10545("teamSnapshot")) {
/* 313 */       data.teamSnapshot = TeamSnapshot.fromNbt(tag.method_10562("teamSnapshot"), registries);
/*     */     }
/*     */     
/* 316 */     if (tag.method_10545("battleSettings")) {
/* 317 */       data.battleSettings = BattleSettings.fromNbt(tag.method_10562("battleSettings"));
/*     */     }
/*     */     
/* 320 */     if (data.currentFloor < 1) {
/* 321 */       data.currentFloor = 1;
/*     */     }
/*     */     
/* 324 */     if (data.highestFloor < 1) {
/* 325 */       data.highestFloor = 1;
/*     */     }
/*     */     
/* 328 */     return data;
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\battletower\data\PlayerTowerData.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */