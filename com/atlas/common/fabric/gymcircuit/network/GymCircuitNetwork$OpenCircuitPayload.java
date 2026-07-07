/*     */ package com.atlas.common.fabric.gymcircuit.network;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.class_8710;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class OpenCircuitPayload
/*     */   extends Record
/*     */   implements class_8710
/*     */ {
/*     */   private final String playerName;
/*     */   private final int currentCircuit;
/*     */   private final int completedCircuits;
/*     */   private final int badgeCount;
/*     */   private final int totalBadges;
/*     */   private final boolean repeatUnlocked;
/*     */   private final boolean badgeSetConsumed;
/*     */   private final String nextObjective;
/*     */   private final List<GymCircuitNetwork.BadgeEntry> badges;
/*     */   private final List<GymCircuitNetwork.LeagueEntry> league;
/*     */   private final List<GymCircuitNetwork.RuleEntry> rules;
/*     */   private final List<GymCircuitNetwork.RewardEntry> rewards;
/*     */   private final List<GymCircuitNetwork.RewardPreviewEntry> rewardPreview;
/*     */   private final List<GymCircuitNetwork.LeaderboardEntry> leaderboard;
/*     */   
/*     */   public final String toString() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/gymcircuit/network/GymCircuitNetwork$OpenCircuitPayload;)Ljava/lang/String;
/*     */     //   6: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #156	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	7	0	this	Lcom/atlas/common/fabric/gymcircuit/network/GymCircuitNetwork$OpenCircuitPayload;
/*     */   }
/*     */   
/*     */   public final int hashCode() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/gymcircuit/network/GymCircuitNetwork$OpenCircuitPayload;)I
/*     */     //   6: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #156	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	7	0	this	Lcom/atlas/common/fabric/gymcircuit/network/GymCircuitNetwork$OpenCircuitPayload;
/*     */   }
/*     */   
/*     */   public final boolean equals(Object o) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/gymcircuit/network/GymCircuitNetwork$OpenCircuitPayload;Ljava/lang/Object;)Z
/*     */     //   7: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #156	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	8	0	this	Lcom/atlas/common/fabric/gymcircuit/network/GymCircuitNetwork$OpenCircuitPayload;
/*     */     //   0	8	1	o	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public OpenCircuitPayload(String playerName, int currentCircuit, int completedCircuits, int badgeCount, int totalBadges, boolean repeatUnlocked, boolean badgeSetConsumed, String nextObjective, List<GymCircuitNetwork.BadgeEntry> badges, List<GymCircuitNetwork.LeagueEntry> league, List<GymCircuitNetwork.RuleEntry> rules, List<GymCircuitNetwork.RewardEntry> rewards, List<GymCircuitNetwork.RewardPreviewEntry> rewardPreview, List<GymCircuitNetwork.LeaderboardEntry> leaderboard) {
/* 156 */     this.playerName = playerName; this.currentCircuit = currentCircuit; this.completedCircuits = completedCircuits; this.badgeCount = badgeCount; this.totalBadges = totalBadges; this.repeatUnlocked = repeatUnlocked; this.badgeSetConsumed = badgeSetConsumed; this.nextObjective = nextObjective; this.badges = badges; this.league = league; this.rules = rules; this.rewards = rewards; this.rewardPreview = rewardPreview; this.leaderboard = leaderboard; } public String playerName() { return this.playerName; } public int currentCircuit() { return this.currentCircuit; } public int completedCircuits() { return this.completedCircuits; } public int badgeCount() { return this.badgeCount; } public int totalBadges() { return this.totalBadges; } public boolean repeatUnlocked() { return this.repeatUnlocked; } public boolean badgeSetConsumed() { return this.badgeSetConsumed; } public String nextObjective() { return this.nextObjective; } public List<GymCircuitNetwork.BadgeEntry> badges() { return this.badges; } public List<GymCircuitNetwork.LeagueEntry> league() { return this.league; } public List<GymCircuitNetwork.RuleEntry> rules() { return this.rules; } public List<GymCircuitNetwork.RewardEntry> rewards() { return this.rewards; } public List<GymCircuitNetwork.RewardPreviewEntry> rewardPreview() { return this.rewardPreview; } public List<GymCircuitNetwork.LeaderboardEntry> leaderboard() { return this.leaderboard; }
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
/*     */   public class_8710.class_9154<? extends class_8710> method_56479() {
/* 174 */     return (class_8710.class_9154)GymCircuitNetwork.OPEN_SCREEN_TYPE;
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\gymcircuit\network\GymCircuitNetwork$OpenCircuitPayload.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */