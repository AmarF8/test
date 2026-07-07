/*     */ package com.atlas.common.fabric.clan.data;
/*     */ 
/*     */ import com.atlas.common.fabric.clan.network.ClanNetwork;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.class_1799;
/*     */ 
/*     */ public class PlayerClanData
/*     */ {
/*  13 */   private static final PlayerClanData INSTANCE = new PlayerClanData();
/*     */   
/*     */   private boolean hasClan = false;
/*     */   
/*  17 */   private String clanName = "";
/*  18 */   private String clanIdentifier = "";
/*  19 */   private int totalValor = 0;
/*  20 */   private int clanValor = 0;
/*  21 */   private UUID ownerUuid = null;
/*  22 */   private String ownerName = "";
/*  23 */   private class_1799 banner = class_1799.field_8037;
/*     */   private boolean isOwner = false;
/*  25 */   private String playerRankType = "MEMBER";
/*  26 */   private long disqualifiedUntil = 0L;
/*     */ 
/*     */   
/*  29 */   private List<ClanNetwork.ClanMemberEntry> members = new ArrayList<>();
/*     */ 
/*     */   
/*  32 */   private List<ClanNetwork.ClanRankPermissionEntry> rankPermissions = new ArrayList<>();
/*     */ 
/*     */   
/*  35 */   private String dailyTaskName = "";
/*  36 */   private String dailyTaskDescription = "";
/*  37 */   private String dailyTaskIcon = "";
/*  38 */   private long dailyEndEpochMs = 0L;
/*  39 */   private List<ClanNetwork.ChallengeLeaderboardEntry> dailyTop5 = new ArrayList<>();
/*  40 */   private String weeklyTaskName = "";
/*  41 */   private String weeklyTaskDescription = "";
/*  42 */   private String weeklyTaskIcon = "";
/*  43 */   private long weeklyEndEpochMs = 0L;
/*  44 */   private List<ClanNetwork.ChallengeLeaderboardEntry> weeklyTop5 = new ArrayList<>();
/*  45 */   private long challengesLoadedAt = 0L;
/*  46 */   private ClanNetwork.ChallengeLeaderboardEntry ownDailyRank = null;
/*  47 */   private ClanNetwork.ChallengeLeaderboardEntry ownWeeklyRank = null;
/*     */ 
/*     */   
/*  50 */   private List<ClanNetwork.ClanLeaderboardEntry> biWeeklyTop20 = new ArrayList<>();
/*  51 */   private long biWeeklyEndEpochMs = 0L;
/*  52 */   private List<ClanNetwork.ClanLeaderboardEntry> dailyChallengeTop20 = new ArrayList<>();
/*  53 */   private List<ClanNetwork.ClanLeaderboardEntry> weeklyChallengeTop20 = new ArrayList<>();
/*  54 */   private long leaderboardLoadedAt = 0L;
/*     */   private boolean newRealm = false;
/*  56 */   private ClanNetwork.ClanLeaderboardEntry ownBiWeeklyRank = null;
/*  57 */   private ClanNetwork.ClanLeaderboardEntry ownDailyChallengeRank = null;
/*  58 */   private ClanNetwork.ClanLeaderboardEntry ownWeeklyChallengeRank = null;
/*     */ 
/*     */   
/*  61 */   private List<ClanNetwork.ValorActivityEntry> valorActivities = new ArrayList<>();
/*  62 */   private Map<String, Integer> valorActivityProgress = new HashMap<>();
/*  63 */   private long valorActivitiesLoadedAt = 0L;
/*     */ 
/*     */   
/*  66 */   private List<String> allPlayerNames = new ArrayList<>();
/*     */ 
/*     */   
/*  69 */   private String lastActionMessage = "";
/*     */   private boolean lastActionSuccess = false;
/*  71 */   private long lastActionTimestamp = 0L;
/*     */ 
/*     */   
/*     */   public static PlayerClanData getInstance() {
/*  75 */     return INSTANCE;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNoClan() {
/*  80 */     this.hasClan = false;
/*  81 */     this.clanName = "";
/*  82 */     this.clanIdentifier = "";
/*  83 */     this.totalValor = 0;
/*  84 */     this.clanValor = 0;
/*  85 */     this.ownerUuid = null;
/*  86 */     this.ownerName = "";
/*  87 */     this.banner = class_1799.field_8037;
/*  88 */     this.isOwner = false;
/*  89 */     this.playerRankType = "MEMBER";
/*  90 */     this.disqualifiedUntil = 0L;
/*  91 */     this.members = new ArrayList<>();
/*  92 */     this.rankPermissions = new ArrayList<>();
/*     */   }
/*     */   
/*     */   public void updateFromSync(ClanNetwork.SyncClanDataPayload data) {
/*  96 */     this.hasClan = true;
/*  97 */     this.clanName = data.clanName();
/*  98 */     this.clanIdentifier = data.clanIdentifier();
/*  99 */     this.totalValor = data.totalValor();
/* 100 */     this.clanValor = data.clanValor();
/* 101 */     this.ownerUuid = data.ownerUuid();
/* 102 */     this.ownerName = data.ownerName();
/* 103 */     this.banner = (data.banner() != null) ? data.banner() : class_1799.field_8037;
/* 104 */     this.members = new ArrayList<>(data.members());
/* 105 */     this.rankPermissions = new ArrayList<>(data.rankPermissions());
/* 106 */     this.isOwner = data.isOwner();
/* 107 */     this.playerRankType = data.playerRankType();
/* 108 */     this.disqualifiedUntil = data.disqualifiedUntil();
/*     */   }
/*     */   
/*     */   public void updateChallenges(ClanNetwork.SyncChallengesPayload data) {
/* 112 */     this.dailyTaskName = data.dailyTaskName();
/* 113 */     this.dailyTaskDescription = data.dailyTaskDescription();
/* 114 */     this.dailyTaskIcon = data.dailyTaskIcon();
/* 115 */     this.dailyEndEpochMs = data.dailyEndEpochMs();
/* 116 */     this.dailyTop5 = new ArrayList<>(data.dailyTop5());
/* 117 */     this.weeklyTaskName = data.weeklyTaskName();
/* 118 */     this.weeklyTaskDescription = data.weeklyTaskDescription();
/* 119 */     this.weeklyTaskIcon = data.weeklyTaskIcon();
/* 120 */     this.weeklyEndEpochMs = data.weeklyEndEpochMs();
/* 121 */     this.weeklyTop5 = new ArrayList<>(data.weeklyTop5());
/* 122 */     this.ownDailyRank = data.ownDailyRank();
/* 123 */     this.ownWeeklyRank = data.ownWeeklyRank();
/* 124 */     this.challengesLoadedAt = System.currentTimeMillis();
/*     */   }
/*     */   
/*     */   public void updateLeaderboard(ClanNetwork.SyncLeaderboardPayload data) {
/* 128 */     this.biWeeklyTop20 = new ArrayList<>(data.biWeeklyTop20());
/* 129 */     this.biWeeklyEndEpochMs = data.biWeeklyEndEpochMs();
/* 130 */     this.dailyChallengeTop20 = new ArrayList<>(data.dailyChallengeTop20());
/* 131 */     this.weeklyChallengeTop20 = new ArrayList<>(data.weeklyChallengeTop20());
/* 132 */     this.newRealm = data.newRealm();
/* 133 */     this.ownBiWeeklyRank = data.ownBiWeeklyRank();
/* 134 */     this.ownDailyChallengeRank = data.ownDailyChallengeRank();
/* 135 */     this.ownWeeklyChallengeRank = data.ownWeeklyChallengeRank();
/* 136 */     this.leaderboardLoadedAt = System.currentTimeMillis();
/*     */   }
/*     */   
/*     */   public void updateValorActivities(ClanNetwork.SyncValorActivitiesPayload data) {
/* 140 */     this.valorActivities = new ArrayList<>(data.activities());
/* 141 */     this.valorActivitiesLoadedAt = System.currentTimeMillis();
/*     */   }
/*     */   
/*     */   public void updateValorActivityProgress(ClanNetwork.SyncValorActivityProgressPayload data) {
/* 145 */     this.valorActivityProgress = new HashMap<>();
/* 146 */     for (ClanNetwork.ValorActivityProgressEntry entry : data.progress()) {
/* 147 */       this.valorActivityProgress.put(entry.name(), Integer.valueOf(entry.currentProgress()));
/*     */     }
/*     */   }
/*     */   
/*     */   public void updatePlayerNames(List<String> names) {
/* 152 */     this.allPlayerNames = new ArrayList<>(names);
/*     */   }
/*     */   
/*     */   public void setActionResult(boolean success, String message) {
/* 156 */     this.lastActionSuccess = success;
/* 157 */     this.lastActionMessage = message;
/* 158 */     this.lastActionTimestamp = System.currentTimeMillis();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasClan() {
/* 163 */     return this.hasClan; }
/* 164 */   public String getClanName() { return this.clanName; }
/* 165 */   public String getClanIdentifier() { return this.clanIdentifier; }
/* 166 */   public int getTotalValor() { return this.totalValor; }
/* 167 */   public int getClanValor() { return this.clanValor; }
/* 168 */   public UUID getOwnerUuid() { return this.ownerUuid; }
/* 169 */   public String getOwnerName() { return this.ownerName; }
/* 170 */   public class_1799 getBanner() { return this.banner; }
/* 171 */   public boolean isOwner() { return this.isOwner; }
/* 172 */   public String getPlayerRankType() { return this.playerRankType; }
/* 173 */   public boolean isDisqualified() { return (this.disqualifiedUntil > System.currentTimeMillis()); }
/* 174 */   public long getDisqualifiedUntil() { return this.disqualifiedUntil; }
/* 175 */   public List<ClanNetwork.ClanMemberEntry> getMembers() { return this.members; } public List<ClanNetwork.ClanRankPermissionEntry> getRankPermissions() {
/* 176 */     return this.rankPermissions;
/*     */   }
/* 178 */   public String getDailyTaskName() { return this.dailyTaskName; }
/* 179 */   public String getDailyTaskDescription() { return this.dailyTaskDescription; }
/* 180 */   public String getDailyTaskIcon() { return this.dailyTaskIcon; }
/* 181 */   public long getDailyEndEpochMs() { return this.dailyEndEpochMs; }
/* 182 */   public List<ClanNetwork.ChallengeLeaderboardEntry> getDailyTop5() { return this.dailyTop5; }
/* 183 */   public String getWeeklyTaskName() { return this.weeklyTaskName; }
/* 184 */   public String getWeeklyTaskDescription() { return this.weeklyTaskDescription; }
/* 185 */   public String getWeeklyTaskIcon() { return this.weeklyTaskIcon; }
/* 186 */   public long getWeeklyEndEpochMs() { return this.weeklyEndEpochMs; }
/* 187 */   public List<ClanNetwork.ChallengeLeaderboardEntry> getWeeklyTop5() { return this.weeklyTop5; }
/* 188 */   public boolean isChallengesLoaded() { return (this.challengesLoadedAt > 0L); }
/* 189 */   public ClanNetwork.ChallengeLeaderboardEntry getOwnDailyRank() { return this.ownDailyRank; }
/* 190 */   public ClanNetwork.ChallengeLeaderboardEntry getOwnWeeklyRank() { return this.ownWeeklyRank; } public boolean isChallengesStale(long ttlMs) {
/* 191 */     return (this.challengesLoadedAt == 0L || System.currentTimeMillis() - this.challengesLoadedAt > ttlMs);
/*     */   }
/* 193 */   public List<ClanNetwork.ClanLeaderboardEntry> getBiWeeklyTop20() { return this.biWeeklyTop20; }
/* 194 */   public long getBiWeeklyEndEpochMs() { return this.biWeeklyEndEpochMs; }
/* 195 */   public List<ClanNetwork.ClanLeaderboardEntry> getDailyChallengeTop20() { return this.dailyChallengeTop20; }
/* 196 */   public List<ClanNetwork.ClanLeaderboardEntry> getWeeklyChallengeTop20() { return this.weeklyChallengeTop20; }
/* 197 */   public boolean isLeaderboardLoaded() { return (this.leaderboardLoadedAt > 0L); }
/* 198 */   public boolean isLeaderboardStale(long ttlMs) { return (this.leaderboardLoadedAt == 0L || System.currentTimeMillis() - this.leaderboardLoadedAt > ttlMs); }
/* 199 */   public boolean isNewRealm() { return this.newRealm; }
/* 200 */   public ClanNetwork.ClanLeaderboardEntry getOwnBiWeeklyRank() { return this.ownBiWeeklyRank; }
/* 201 */   public ClanNetwork.ClanLeaderboardEntry getOwnDailyChallengeRank() { return this.ownDailyChallengeRank; } public ClanNetwork.ClanLeaderboardEntry getOwnWeeklyChallengeRank() {
/* 202 */     return this.ownWeeklyChallengeRank;
/*     */   }
/* 204 */   public List<ClanNetwork.ValorActivityEntry> getValorActivities() { return this.valorActivities; }
/* 205 */   public int getValorActivityProgress(String name) { return ((Integer)this.valorActivityProgress.getOrDefault(name, Integer.valueOf(0))).intValue(); }
/* 206 */   public boolean isValorActivitiesLoaded() { return (this.valorActivitiesLoadedAt > 0L); } public boolean isValorActivitiesStale(long ttlMs) {
/* 207 */     return (this.valorActivitiesLoadedAt == 0L || System.currentTimeMillis() - this.valorActivitiesLoadedAt > ttlMs);
/*     */   } public List<String> getAllPlayerNames() {
/* 209 */     return this.allPlayerNames;
/*     */   }
/* 211 */   public String getLastActionMessage() { return this.lastActionMessage; }
/* 212 */   public boolean isLastActionSuccess() { return this.lastActionSuccess; } public long getLastActionTimestamp() {
/* 213 */     return this.lastActionTimestamp;
/*     */   }
/*     */   public boolean hasPermission(String permission) {
/* 216 */     if (this.isOwner) return true; 
/* 217 */     for (ClanNetwork.ClanRankPermissionEntry rp : this.rankPermissions) {
/* 218 */       if (rp.rankType().equals(this.playerRankType)) {
/* 219 */         return rp.permissions().contains(permission);
/*     */       }
/*     */     } 
/* 222 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\clan\data\PlayerClanData.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */