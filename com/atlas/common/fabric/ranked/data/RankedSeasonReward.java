/*    */ package com.atlas.common.fabric.ranked.data;public final class RankedSeasonReward extends Record { private final String rankDisplayName;
/*    */   private final String description;
/*    */   
/*    */   public final String toString() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/ranked/data/RankedSeasonReward;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #12	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/fabric/ranked/data/RankedSeasonReward;
/*    */   }
/*    */   
/*    */   public final int hashCode() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/ranked/data/RankedSeasonReward;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #12	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/fabric/ranked/data/RankedSeasonReward;
/*    */   }
/*    */   
/* 12 */   public RankedSeasonReward(String rankDisplayName, String description) { this.rankDisplayName = rankDisplayName; this.description = description; } public final boolean equals(Object o) { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/ranked/data/RankedSeasonReward;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #12	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lcom/atlas/common/fabric/ranked/data/RankedSeasonReward;
/* 12 */     //   0	8	1	o	Ljava/lang/Object; } public String rankDisplayName() { return this.rankDisplayName; } public String description() { return this.description; }
/*    */   
/* 14 */   private static List<RankedSeasonReward> RANK_REWARDS = createDefaultRankRewards();
/* 15 */   private static List<LegendPositionReward> LEGEND_REWARDS = createDefaultLegendRewards();
/*    */ 
/*    */ 
/*    */   
/*    */   public static void configureRankRewards(List<RankedSeasonReward> rewards) {
/* 20 */     if (rewards != null && !rewards.isEmpty()) {
/* 21 */       RANK_REWARDS = Collections.unmodifiableList(new ArrayList<>(rewards));
/*    */     }
/*    */   }
/*    */   
/*    */   public static void configureLegendRewards(List<LegendPositionReward> rewards) {
/* 26 */     if (rewards != null && !rewards.isEmpty()) {
/* 27 */       LEGEND_REWARDS = Collections.unmodifiableList(new ArrayList<>(rewards));
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static RankedSeasonReward getRewardForRank(RankedRank rank) {
/* 34 */     for (RankedSeasonReward reward : RANK_REWARDS) {
/* 35 */       if (reward.rankDisplayName().equals(rank.getDisplayName())) {
/* 36 */         return reward;
/*    */       }
/*    */     } 
/* 39 */     return null;
/*    */   }
/*    */   
/*    */   public static List<RankedSeasonReward> getAllRankRewards() {
/* 43 */     return RANK_REWARDS;
/*    */   }
/*    */   
/*    */   public static LegendPositionReward getLegendRewardForPosition(int position) {
/* 47 */     for (LegendPositionReward reward : LEGEND_REWARDS) {
/* 48 */       if (position <= reward.maxPosition()) {
/* 49 */         return reward;
/*    */       }
/*    */     } 
/* 52 */     return LEGEND_REWARDS.get(LEGEND_REWARDS.size() - 1);
/*    */   }
/*    */   
/*    */   public static List<LegendPositionReward> getAllLegendRewards() {
/* 56 */     return LEGEND_REWARDS;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private static List<RankedSeasonReward> createDefaultRankRewards() {
/* 62 */     List<RankedSeasonReward> rewards = new ArrayList<>();
/* 63 */     rewards.add(new RankedSeasonReward("Bronze III", "1x Pokespinner, 10 PG, 25 Trophies"));
/* 64 */     rewards.add(new RankedSeasonReward("Bronze II", "1x Pokespinner, 15 PG, 30 Trophies"));
/* 65 */     rewards.add(new RankedSeasonReward("Bronze I", "1x Pokespinner, 20 PG, 35 Trophies"));
/* 66 */     rewards.add(new RankedSeasonReward("Silver III", "3x Pokespinner, 30 PG, 50 Trophies"));
/* 67 */     rewards.add(new RankedSeasonReward("Silver II", "3x Pokespinner, 40 PG, 60 Trophies"));
/* 68 */     rewards.add(new RankedSeasonReward("Silver I", "3x Pokespinner, 50 PG, 70 Trophies"));
/* 69 */     rewards.add(new RankedSeasonReward("Gold III", "5x Pokespinner, 100 PG, 100 Trophies"));
/* 70 */     rewards.add(new RankedSeasonReward("Gold II", "5x Pokespinner, 125 PG, 120 Trophies"));
/* 71 */     rewards.add(new RankedSeasonReward("Gold I", "5x Pokespinner, 150 PG, 140 Trophies"));
/* 72 */     rewards.add(new RankedSeasonReward("Diamond III", "15x Pokespinner, 400 PG, 300 Trophies"));
/* 73 */     rewards.add(new RankedSeasonReward("Diamond II", "15x Pokespinner, 500 PG, 350 Trophies"));
/* 74 */     rewards.add(new RankedSeasonReward("Diamond I", "15x Pokespinner, 600 PG, 400 Trophies"));
/* 75 */     rewards.add(new RankedSeasonReward("Master III", "20x Pokespinner, 750 PG, 500 Trophies"));
/* 76 */     rewards.add(new RankedSeasonReward("Master II", "20x Pokespinner, 1000 PG, 575 Trophies"));
/* 77 */     rewards.add(new RankedSeasonReward("Master I", "20x Pokespinner, 1250 PG, 650 Trophies"));
/* 78 */     rewards.add(new RankedSeasonReward("Champion III", "30x Pokespinner, 2500 PG, 1200 Trophies"));
/* 79 */     rewards.add(new RankedSeasonReward("Champion II", "30x Pokespinner, 3000 PG, 1400 Trophies"));
/* 80 */     rewards.add(new RankedSeasonReward("Champion I", "30x Pokespinner, 3500 PG, 1600 Trophies"));
/* 81 */     rewards.add(new RankedSeasonReward("Legend", "50x Pokespinner, 5000 PG, 1000 Trophies"));
/* 82 */     return Collections.unmodifiableList(rewards);
/*    */   }
/*    */   
/*    */   private static List<LegendPositionReward> createDefaultLegendRewards() {
/* 86 */     List<LegendPositionReward> rewards = new ArrayList<>();
/* 87 */     rewards.add(new LegendPositionReward(1, "100x PS, 7500 PG, 3000 Trophies + Exclusive Cosmetic 1, 2, 3, 4, 5"));
/* 88 */     rewards.add(new LegendPositionReward(2, "90x PS, 6500 PG, 2700 Trophies + Exclusive Cosmetic 2, 3, 4, 5"));
/* 89 */     rewards.add(new LegendPositionReward(3, "80x PS, 6000 PG, 2400 Trophies + Exclusive Cosmetic 2, 3, 4, 5"));
/* 90 */     rewards.add(new LegendPositionReward(4, "70x PS, 5500 PG, 2200 Trophies + Exclusive Cosmetic 3, 4, 5"));
/* 91 */     rewards.add(new LegendPositionReward(5, "60x PS, 5000 PG, 2000 Trophies + Exclusive Cosmetic 3, 4, 5"));
/* 92 */     rewards.add(new LegendPositionReward(10, "50x PS, 4500 PG, 1800 Trophies + Exclusive Cosmetic 4, 5"));
/* 93 */     rewards.add(new LegendPositionReward(2147483647, "40x PS, 4000 PG, 1700 Trophies + Exclusive Cosmetic 5"));
/* 94 */     return Collections.unmodifiableList(rewards);
/*    */   }
/*    */   public static final class LegendPositionReward extends Record { private final int maxPosition; private final String description;
/* 97 */     public LegendPositionReward(int maxPosition, String description) { this.maxPosition = maxPosition; this.description = description; } public final String toString() { // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/ranked/data/RankedSeasonReward$LegendPositionReward;)Ljava/lang/String;
/*    */       //   6: areturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #97	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/* 97 */       //   0	7	0	this	Lcom/atlas/common/fabric/ranked/data/RankedSeasonReward$LegendPositionReward; } public int maxPosition() { return this.maxPosition; } public final int hashCode() { // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/ranked/data/RankedSeasonReward$LegendPositionReward;)I
/*    */       //   6: ireturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #97	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	7	0	this	Lcom/atlas/common/fabric/ranked/data/RankedSeasonReward$LegendPositionReward; } public final boolean equals(Object o) { // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: aload_1
/*    */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/ranked/data/RankedSeasonReward$LegendPositionReward;Ljava/lang/Object;)Z
/*    */       //   7: ireturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #97	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	8	0	this	Lcom/atlas/common/fabric/ranked/data/RankedSeasonReward$LegendPositionReward;
/* 97 */       //   0	8	1	o	Ljava/lang/Object; } public String description() { return this.description; }
/*    */      }
/*    */    }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\ranked\data\RankedSeasonReward.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */