/*     */ package com.atlas.common.fabric.ranked.data;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class RankedRank
/*     */ {
/*  16 */   private static List<RankedRank> ALL_RANKS = createDefaults();
/*     */   
/*     */   private final String displayName;
/*     */   
/*     */   private final String tier;
/*     */   private final String tierNumber;
/*     */   private final int minElo;
/*     */   private final int maxElo;
/*     */   private final int refreshCostGems;
/*     */   private final int winTrophies;
/*     */   private final int lossTrophies;
/*     */   private final int iconColor;
/*     */   
/*     */   public RankedRank(@NotNull String displayName, @NotNull String tier, @NotNull String tierNumber, int minElo, int maxElo, int refreshCostGems, int winTrophies, int lossTrophies, int iconColor) {
/*  30 */     this.displayName = displayName;
/*  31 */     this.tier = tier;
/*  32 */     this.tierNumber = tierNumber;
/*  33 */     this.minElo = minElo;
/*  34 */     this.maxElo = maxElo;
/*  35 */     this.refreshCostGems = refreshCostGems;
/*  36 */     this.winTrophies = winTrophies;
/*  37 */     this.lossTrophies = lossTrophies;
/*  38 */     this.iconColor = iconColor;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void configure(@NotNull List<RankedRank> ranks) {
/*  44 */     if (!ranks.isEmpty()) {
/*  45 */       ALL_RANKS = Collections.unmodifiableList(new ArrayList<>(ranks));
/*     */     }
/*     */   }
/*     */   
/*     */   public static List<RankedRank> getAllRanks() {
/*  50 */     return ALL_RANKS;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public String getDisplayName() {
/*  55 */     return this.displayName; } @NotNull
/*  56 */   public String getTier() { return this.tier; } @NotNull
/*  57 */   public String getTierNumber() { return this.tierNumber; }
/*  58 */   public int getMinElo() { return this.minElo; }
/*  59 */   public int getMaxElo() { return this.maxElo; }
/*  60 */   public int getRefreshCostGems() { return this.refreshCostGems; }
/*  61 */   public int getWinTrophies() { return this.winTrophies; }
/*  62 */   public int getLossTrophies() { return this.lossTrophies; } public int getIconColor() {
/*  63 */     return this.iconColor;
/*     */   } @NotNull
/*     */   public String getBaseRank() {
/*  66 */     return this.tier;
/*     */   }
/*     */   public boolean isHigherThan(@NotNull RankedRank other) {
/*  69 */     return (this.minElo > other.minElo);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static RankedRank getRankForElo(int elo) {
/*  76 */     for (RankedRank rank : ALL_RANKS) {
/*  77 */       if (elo >= rank.minElo && elo <= rank.maxElo) {
/*  78 */         return rank;
/*     */       }
/*     */     } 
/*     */     
/*  82 */     if (elo < 0 || ALL_RANKS.isEmpty()) return ALL_RANKS.get(0); 
/*  83 */     return ALL_RANKS.get(ALL_RANKS.size() - 1);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static RankedRank getByDisplayName(@NotNull String displayName) {
/*  88 */     for (RankedRank rank : ALL_RANKS) {
/*  89 */       if (rank.displayName.equals(displayName)) {
/*  90 */         return rank;
/*     */       }
/*     */     } 
/*  93 */     return null;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static RankedRank getByTier(@NotNull String tier) {
/*  98 */     for (RankedRank rank : ALL_RANKS) {
/*  99 */       if (rank.tier.equals(tier)) {
/* 100 */         return rank;
/*     */       }
/*     */     } 
/* 103 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static List<String> getUniqueTiers() {
/* 111 */     List<String> tiers = new ArrayList<>();
/* 112 */     for (RankedRank rank : ALL_RANKS) {
/* 113 */       if (!tiers.contains(rank.tier)) {
/* 114 */         tiers.add(rank.tier);
/*     */       }
/*     */     } 
/* 117 */     return tiers;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static List<RankedRank> createDefaults() {
/* 123 */     List<RankedRank> ranks = new ArrayList<>();
/* 124 */     ranks.add(new RankedRank("Bronze III", "Bronze", "III", 0, 899, 39, 5, 3, 10907987));
/* 125 */     ranks.add(new RankedRank("Bronze II", "Bronze", "II", 900, 999, 39, 5, 3, 10907987));
/* 126 */     ranks.add(new RankedRank("Bronze I", "Bronze", "I", 1000, 1099, 39, 5, 3, 10907987));
/* 127 */     ranks.add(new RankedRank("Silver III", "Silver", "III", 1100, 1199, 59, 10, 5, 14737632));
/* 128 */     ranks.add(new RankedRank("Silver II", "Silver", "II", 1200, 1349, 59, 10, 5, 14737632));
/* 129 */     ranks.add(new RankedRank("Silver I", "Silver", "I", 1350, 1499, 59, 10, 5, 14737632));
/* 130 */     ranks.add(new RankedRank("Gold III", "Gold", "III", 1500, 1649, 79, 15, 8, 14397720));
/* 131 */     ranks.add(new RankedRank("Gold II", "Gold", "II", 1650, 1799, 79, 15, 8, 14397720));
/* 132 */     ranks.add(new RankedRank("Gold I", "Gold", "I", 1800, 1949, 79, 15, 8, 14397720));
/* 133 */     ranks.add(new RankedRank("Diamond III", "Diamond", "III", 1950, 2149, 99, 20, 10, 6155509));
/* 134 */     ranks.add(new RankedRank("Diamond II", "Diamond", "II", 2150, 2349, 99, 20, 10, 6155509));
/* 135 */     ranks.add(new RankedRank("Diamond I", "Diamond", "I", 2350, 2549, 99, 20, 10, 6155509));
/* 136 */     ranks.add(new RankedRank("Master III", "Master", "III", 2550, 2799, 119, 25, 13, 11103455));
/* 137 */     ranks.add(new RankedRank("Master II", "Master", "II", 2800, 3049, 119, 25, 13, 11103455));
/* 138 */     ranks.add(new RankedRank("Master I", "Master", "I", 3050, 3299, 119, 25, 13, 11103455));
/* 139 */     ranks.add(new RankedRank("Champion III", "Champion", "III", 3300, 3599, 139, 30, 15, 15085102));
/* 140 */     ranks.add(new RankedRank("Champion II", "Champion", "II", 3600, 3899, 139, 30, 15, 15085102));
/* 141 */     ranks.add(new RankedRank("Champion I", "Champion", "I", 3900, 4199, 139, 30, 15, 15085102));
/* 142 */     ranks.add(new RankedRank("Legend", "Legend", "", 4200, 2147483647, 159, 40, 20, 6750161));
/* 143 */     return Collections.unmodifiableList(ranks);
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\ranked\data\RankedRank.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */