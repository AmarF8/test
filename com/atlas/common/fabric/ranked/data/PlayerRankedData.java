/*     */ package com.atlas.common.fabric.ranked.data;
/*     */ 
/*     */ import com.atlas.common.fabric.ranked.network.RankedNetwork;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PlayerRankedData
/*     */ {
/*  14 */   private static final PlayerRankedData INSTANCE = new PlayerRankedData();
/*     */   
/*  16 */   private int elo = EloCalculator.getStartingElo();
/*  17 */   private int trophies = 0;
/*  18 */   private int wins = 0;
/*  19 */   private int losses = 0;
/*  20 */   private int winStreak = 0;
/*  21 */   private int lossStreak = 0;
/*  22 */   private int matchesPlayed = 0;
/*  23 */   private int season = 1;
/*  24 */   private long seasonEndEpoch = 0L;
/*  25 */   private int refreshTickets = 5;
/*  26 */   private long lastFreeRefreshTime = 0L;
/*  27 */   private String rankName = "Bronze III";
/*     */   private boolean defenseLockedIn = false;
/*  29 */   private long defenseEditCooldownRemaining = 0L;
/*  30 */   private long freeRefreshIntervalMs = 14400000L;
/*  31 */   private String seasonDisplayName = "Season BETA";
/*     */   
/*     */   private boolean isBetaSeason = true;
/*  34 */   private List<RankedNetwork.PartyEntry> defenseParty = new ArrayList<>();
/*  35 */   private List<RankedNetwork.PartyEntry> battleParty = new ArrayList<>();
/*  36 */   private List<RankedNetwork.OpponentPayload> opponents = new ArrayList<>();
/*  37 */   private List<RankedNetwork.LeaderboardEntryData> leaderboardEntries = new ArrayList<>();
/*  38 */   private RankedNetwork.LeaderboardEntryData playerLeaderboardRank = null;
/*  39 */   private List<RankedNetwork.SeasonRewardData> seasonRewards = new ArrayList<>();
/*  40 */   private List<RankedNetwork.ShopItemEntry> shopItems = new ArrayList<>();
/*  41 */   private List<RankedNetwork.MatchHistoryPayload> battleHistory = new ArrayList<>();
/*  42 */   private List<RankedNetwork.PastSeasonMeta> pastSeasons = new ArrayList<>();
/*  43 */   private List<RankedNetwork.PastSeasonLeaderboardEntry> pastSeasonLeaderboard = new ArrayList<>();
/*  44 */   private int viewingPastSeasonNumber = -1;
/*  45 */   private String viewingPastSeasonName = "";
/*     */ 
/*     */ 
/*     */   
/*     */   public static PlayerRankedData getInstance() {
/*  50 */     return INSTANCE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateFromSync(RankedNetwork.SyncDataPayload data) {
/*  56 */     this.elo = data.elo();
/*  57 */     this.trophies = data.trophies();
/*  58 */     this.wins = data.wins();
/*  59 */     this.losses = data.losses();
/*  60 */     this.winStreak = data.winStreak();
/*  61 */     this.lossStreak = data.lossStreak();
/*  62 */     this.matchesPlayed = data.matchesPlayed();
/*  63 */     this.season = data.season();
/*  64 */     this.seasonEndEpoch = data.seasonEndEpoch();
/*  65 */     this.refreshTickets = data.refreshTickets();
/*  66 */     this.lastFreeRefreshTime = data.lastFreeRefreshTime();
/*  67 */     this.rankName = data.rankName();
/*  68 */     this.defenseLockedIn = data.defenseLockedIn();
/*  69 */     this.defenseEditCooldownRemaining = data.defenseEditCooldownRemaining();
/*  70 */     this.freeRefreshIntervalMs = data.freeRefreshIntervalMs();
/*  71 */     this.seasonDisplayName = data.seasonDisplayName();
/*  72 */     this.isBetaSeason = data.isBetaSeason();
/*     */   }
/*     */   
/*     */   public void setDefenseParty(List<RankedNetwork.PartyEntry> entries) {
/*  76 */     this.defenseParty = new ArrayList<>(entries);
/*     */   }
/*     */   
/*     */   public void setBattleParty(List<RankedNetwork.PartyEntry> entries) {
/*  80 */     this.battleParty = new ArrayList<>(entries);
/*     */   }
/*     */   
/*     */   public void setOpponents(List<RankedNetwork.OpponentPayload> opponents) {
/*  84 */     this.opponents = new ArrayList<>(opponents);
/*     */   }
/*     */   
/*     */   public void setLeaderboard(List<RankedNetwork.LeaderboardEntryData> entries, RankedNetwork.LeaderboardEntryData playerRank, List<RankedNetwork.SeasonRewardData> rewards) {
/*  88 */     this.leaderboardEntries = new ArrayList<>(entries);
/*  89 */     this.playerLeaderboardRank = playerRank;
/*  90 */     this.seasonRewards = new ArrayList<>(rewards);
/*     */   }
/*     */   
/*     */   public void setShopItems(List<RankedNetwork.ShopItemEntry> items) {
/*  94 */     this.shopItems = new ArrayList<>(items);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getElo() {
/*  99 */     return this.elo; }
/* 100 */   public int getTrophies() { return this.trophies; }
/* 101 */   public int getWins() { return this.wins; }
/* 102 */   public int getLosses() { return this.losses; }
/* 103 */   public int getWinStreak() { return this.winStreak; }
/* 104 */   public int getLossStreak() { return this.lossStreak; }
/* 105 */   public int getMatchesPlayed() { return this.matchesPlayed; }
/* 106 */   public int getSeason() { return this.season; }
/* 107 */   public long getSeasonEndEpoch() { return this.seasonEndEpoch; }
/* 108 */   public int getRefreshTickets() { return this.refreshTickets; }
/* 109 */   public long getLastFreeRefreshTime() { return this.lastFreeRefreshTime; }
/* 110 */   public String getRankName() { return this.rankName; }
/* 111 */   public boolean isDefenseLockedIn() { return this.defenseLockedIn; }
/* 112 */   public long getDefenseEditCooldownRemaining() { return this.defenseEditCooldownRemaining; }
/* 113 */   public long getFreeRefreshIntervalMs() { return this.freeRefreshIntervalMs; }
/* 114 */   public String getSeasonDisplayName() { return this.seasonDisplayName; } public boolean isBetaSeason() {
/* 115 */     return this.isBetaSeason;
/*     */   }
/* 117 */   public List<RankedNetwork.PartyEntry> getDefenseParty() { return this.defenseParty; } public List<RankedNetwork.PartyEntry> getBattleParty() {
/* 118 */     return this.battleParty;
/*     */   } public List<RankedNetwork.OpponentPayload> getOpponents() {
/* 120 */     if (this.opponents.isEmpty()) {
/* 121 */       return getPlaceholderOpponents();
/*     */     }
/* 123 */     return this.opponents;
/*     */   }
/*     */   
/*     */   private static List<RankedNetwork.OpponentPayload> getPlaceholderOpponents() {
/* 127 */     List<RankedNetwork.OpponentPayload> placeholders = new ArrayList<>();
/* 128 */     placeholders.add(new RankedNetwork.OpponentPayload(
/* 129 */           UUID.fromString("00000000-0000-0001-0000-000000000001"), "TrainerAsh", 950, "Bronze I", "cobblemon:charizard", 100, "", "", false, false, 
/*     */ 
/*     */           
/* 132 */           List.of("cobblemon:charizard", "cobblemon:pikachu", "cobblemon:snorlax", "cobblemon:greninja", "cobblemon:lucario", "cobblemon:gengar")));
/* 133 */     placeholders.add(new RankedNetwork.OpponentPayload(
/* 134 */           UUID.fromString("00000000-0000-0002-0000-000000000002"), "GymLeaderMisty", 1250, "Silver II", "cobblemon:gyarados", 100, "", "", false, false, 
/*     */ 
/*     */           
/* 137 */           List.of("cobblemon:gyarados", "cobblemon:starmie", "cobblemon:lapras", "cobblemon:vaporeon", "cobblemon:tentacruel")));
/* 138 */     placeholders.add(new RankedNetwork.OpponentPayload(
/* 139 */           UUID.fromString("00000000-0000-0003-0000-000000000003"), "EliteFourBruno", 1850, "Gold I", "cobblemon:machamp", 100, "", "", false, false, 
/*     */ 
/*     */           
/* 142 */           List.of("cobblemon:machamp", "cobblemon:hitmonchan", "cobblemon:hitmonlee", "cobblemon:onix")));
/* 143 */     return placeholders;
/*     */   }
/* 145 */   public List<RankedNetwork.LeaderboardEntryData> getLeaderboardEntries() { return this.leaderboardEntries; }
/* 146 */   public RankedNetwork.LeaderboardEntryData getPlayerLeaderboardRank() { return this.playerLeaderboardRank; }
/* 147 */   public List<RankedNetwork.SeasonRewardData> getSeasonRewards() { return this.seasonRewards; }
/* 148 */   public List<RankedNetwork.ShopItemEntry> getShopItems() { return this.shopItems; } public List<RankedNetwork.MatchHistoryPayload> getBattleHistory() {
/* 149 */     return this.battleHistory;
/*     */   }
/*     */   public void setHistory(List<RankedNetwork.MatchHistoryPayload> entries) {
/* 152 */     this.battleHistory = new ArrayList<>(entries);
/*     */   }
/*     */   
/*     */   public void setPastSeasons(List<RankedNetwork.PastSeasonMeta> seasons) {
/* 156 */     this.pastSeasons = new ArrayList<>(seasons);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPastSeasonLeaderboard(int seasonNumber, String seasonName, List<RankedNetwork.PastSeasonLeaderboardEntry> entries) {
/* 161 */     this.viewingPastSeasonNumber = seasonNumber;
/* 162 */     this.viewingPastSeasonName = seasonName;
/* 163 */     this.pastSeasonLeaderboard = new ArrayList<>(entries);
/*     */   }
/*     */   
/* 166 */   public List<RankedNetwork.PastSeasonMeta> getPastSeasons() { return this.pastSeasons; }
/* 167 */   public List<RankedNetwork.PastSeasonLeaderboardEntry> getPastSeasonLeaderboard() { return this.pastSeasonLeaderboard; }
/* 168 */   public int getViewingPastSeasonNumber() { return this.viewingPastSeasonNumber; } public String getViewingPastSeasonName() {
/* 169 */     return this.viewingPastSeasonName;
/*     */   }
/*     */   public boolean isInPlacement() {
/* 172 */     return (this.matchesPlayed < EloCalculator.getPlacementMatches());
/*     */   }
/*     */   
/*     */   public int getPlacementMatchesRemaining() {
/* 176 */     return Math.max(0, EloCalculator.getPlacementMatches() - this.matchesPlayed);
/*     */   }
/*     */   
/*     */   public RankedRank getCurrentRank() {
/* 180 */     return RankedRank.getRankForElo(this.elo);
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\ranked\data\PlayerRankedData.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */