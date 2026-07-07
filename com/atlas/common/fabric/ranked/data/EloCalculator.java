/*     */ package com.atlas.common.fabric.ranked.data;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class EloCalculator
/*     */ {
/*  13 */   private static int placementMatches = 5;
/*     */ 
/*     */   
/*  16 */   private static int placementK = 64;
/*     */ 
/*     */   
/*  19 */   private static int streakThreshold = 3;
/*     */ 
/*     */   
/*  22 */   private static int streakKModifier = 5;
/*     */ 
/*     */   
/*  25 */   private static List<StreakBonus> streakBonuses = createDefaultStreakBonuses();
/*     */ 
/*     */   
/*  28 */   private static int startingElo = 1000;
/*     */ 
/*     */   
/*  31 */   private static int minK = 8;
/*     */ 
/*     */   
/*  34 */   private static List<KBracket> kBrackets = createDefaultBrackets();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void configure(int startingElo, int placementMatches, int placementK, int streakThreshold, int streakKModifier, int minK, List<KBracket> kBrackets, List<StreakBonus> streakBonuses) {
/*  43 */     EloCalculator.startingElo = startingElo;
/*  44 */     EloCalculator.placementMatches = placementMatches;
/*  45 */     EloCalculator.placementK = placementK;
/*  46 */     EloCalculator.streakThreshold = streakThreshold;
/*  47 */     EloCalculator.streakKModifier = streakKModifier;
/*  48 */     EloCalculator.minK = minK;
/*  49 */     if (kBrackets != null && !kBrackets.isEmpty()) {
/*  50 */       EloCalculator.kBrackets = kBrackets;
/*     */     }
/*  52 */     if (streakBonuses != null && !streakBonuses.isEmpty()) {
/*  53 */       EloCalculator.streakBonuses = streakBonuses;
/*     */     }
/*     */   }
/*     */   
/*     */   private static List<KBracket> createDefaultBrackets() {
/*  58 */     List<KBracket> brackets = new ArrayList<>();
/*  59 */     brackets.add(new KBracket(1200, 40));
/*  60 */     brackets.add(new KBracket(2000, 32));
/*  61 */     brackets.add(new KBracket(2800, 24));
/*  62 */     brackets.add(new KBracket(2147483647, 16));
/*  63 */     return brackets;
/*     */   }
/*     */   
/*     */   private static List<StreakBonus> createDefaultStreakBonuses() {
/*  67 */     List<StreakBonus> bonuses = new ArrayList<>();
/*  68 */     bonuses.add(new StreakBonus(3, 5));
/*  69 */     bonuses.add(new StreakBonus(5, 10));
/*  70 */     bonuses.add(new StreakBonus(10, 20));
/*  71 */     return bonuses;
/*     */   }
/*     */   
/*     */   public static int getStartingElo()
/*     */   {
/*  76 */     return startingElo; } public static int getPlacementMatches() {
/*  77 */     return placementMatches;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static double getExpectedScore(int playerElo, int opponentElo) {
/*  87 */     return 1.0D / (1.0D + Math.pow(10.0D, (opponentElo - playerElo) / 400.0D));
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
/*     */   public static int getKFactor(int elo, int matchesPlayed, int winStreak, int lossStreak) {
/* 101 */     if (matchesPlayed < placementMatches) {
/* 102 */       return placementK;
/*     */     }
/*     */     
/* 105 */     int k = 16;
/* 106 */     for (KBracket bracket : kBrackets) {
/* 107 */       if (elo < bracket.maxElo) {
/* 108 */         k = bracket.k;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/*     */     
/* 114 */     if (winStreak >= streakThreshold) {
/* 115 */       k += streakKModifier;
/*     */     }
/* 117 */     if (lossStreak >= streakThreshold) {
/* 118 */       k -= streakKModifier;
/*     */     }
/*     */     
/* 121 */     return Math.max(k, minK);
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
/*     */   public static int calculateNewElo(int playerElo, int opponentElo, boolean won, int matchesPlayed, int winStreak, int lossStreak) {
/* 137 */     double expectedScore = getExpectedScore(playerElo, opponentElo);
/* 138 */     double actualScore = won ? 1.0D : 0.0D;
/* 139 */     int k = getKFactor(playerElo, matchesPlayed, winStreak, lossStreak);
/*     */     
/* 141 */     int eloChange = (int)Math.round(k * (actualScore - expectedScore));
/*     */ 
/*     */     
/* 144 */     if (won && eloChange < 3) {
/* 145 */       eloChange = 3;
/*     */     }
/*     */     
/* 148 */     return Math.max(0, playerElo + eloChange);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getStreakBonus(int winStreak) {
/* 159 */     int bonus = 0;
/* 160 */     for (StreakBonus sb : streakBonuses) {
/* 161 */       if (winStreak >= sb.minStreak) {
/* 162 */         bonus = sb.bonus;
/*     */       }
/*     */     } 
/* 165 */     return bonus;
/*     */   } public static final class KBracket extends Record { private final int maxElo; private final int k; public final String toString() {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/ranked/data/EloCalculator$KBracket;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #171	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/ranked/data/EloCalculator$KBracket;
/*     */     } public final int hashCode() {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/ranked/data/EloCalculator$KBracket;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #171	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/ranked/data/EloCalculator$KBracket;
/*     */     }
/* 171 */     public KBracket(int maxElo, int k) { this.maxElo = maxElo; this.k = k; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/ranked/data/EloCalculator$KBracket;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #171	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lcom/atlas/common/fabric/ranked/data/EloCalculator$KBracket;
/* 171 */       //   0	8	1	o	Ljava/lang/Object; } public int maxElo() { return this.maxElo; } public int k() { return this.k; }
/*     */      }
/*     */    public static final class StreakBonus extends Record { private final int minStreak; private final int bonus; public final String toString() {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/ranked/data/EloCalculator$StreakBonus;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #177	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/ranked/data/EloCalculator$StreakBonus;
/*     */     } public final int hashCode() {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/ranked/data/EloCalculator$StreakBonus;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #177	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/ranked/data/EloCalculator$StreakBonus;
/* 177 */     } public StreakBonus(int minStreak, int bonus) { this.minStreak = minStreak; this.bonus = bonus; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/ranked/data/EloCalculator$StreakBonus;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #177	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lcom/atlas/common/fabric/ranked/data/EloCalculator$StreakBonus;
/* 177 */       //   0	8	1	o	Ljava/lang/Object; } public int minStreak() { return this.minStreak; } public int bonus() { return this.bonus; }
/*     */      }
/*     */ 
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\ranked\data\EloCalculator.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */