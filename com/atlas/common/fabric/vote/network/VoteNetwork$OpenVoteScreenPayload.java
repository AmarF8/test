/*     */ package com.atlas.common.fabric.vote.network;
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
/*     */ public final class OpenVoteScreenPayload
/*     */   extends Record
/*     */   implements class_8710
/*     */ {
/*     */   private final int totalVotes;
/*     */   private final int monthlyVotes;
/*     */   private final int monthlyClaimed;
/*     */   private final int unclaimedVotes;
/*     */   private final int votesUntilReward;
/*     */   private final int tier;
/*     */   private final int votesForNextTier;
/*     */   private final List<Integer> tierThresholds;
/*     */   private final List<Integer> monthlyThresholds;
/*     */   private final List<String> linkUrls;
/*     */   private final List<String> linkLabels;
/*     */   private final List<VoteNetwork.TierRewardText> tierRewards;
/*     */   private final List<VoteNetwork.MonthlyRewardText> monthlyRewards;
/*     */   
/*     */   public final String toString() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/vote/network/VoteNetwork$OpenVoteScreenPayload;)Ljava/lang/String;
/*     */     //   6: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #140	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	7	0	this	Lcom/atlas/common/fabric/vote/network/VoteNetwork$OpenVoteScreenPayload;
/*     */   }
/*     */   
/*     */   public final int hashCode() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/vote/network/VoteNetwork$OpenVoteScreenPayload;)I
/*     */     //   6: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #140	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	7	0	this	Lcom/atlas/common/fabric/vote/network/VoteNetwork$OpenVoteScreenPayload;
/*     */   }
/*     */   
/*     */   public final boolean equals(Object o) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/vote/network/VoteNetwork$OpenVoteScreenPayload;Ljava/lang/Object;)Z
/*     */     //   7: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #140	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	8	0	this	Lcom/atlas/common/fabric/vote/network/VoteNetwork$OpenVoteScreenPayload;
/*     */     //   0	8	1	o	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public OpenVoteScreenPayload(int totalVotes, int monthlyVotes, int monthlyClaimed, int unclaimedVotes, int votesUntilReward, int tier, int votesForNextTier, List<Integer> tierThresholds, List<Integer> monthlyThresholds, List<String> linkUrls, List<String> linkLabels, List<VoteNetwork.TierRewardText> tierRewards, List<VoteNetwork.MonthlyRewardText> monthlyRewards) {
/* 140 */     this.totalVotes = totalVotes; this.monthlyVotes = monthlyVotes; this.monthlyClaimed = monthlyClaimed; this.unclaimedVotes = unclaimedVotes; this.votesUntilReward = votesUntilReward; this.tier = tier; this.votesForNextTier = votesForNextTier; this.tierThresholds = tierThresholds; this.monthlyThresholds = monthlyThresholds; this.linkUrls = linkUrls; this.linkLabels = linkLabels; this.tierRewards = tierRewards; this.monthlyRewards = monthlyRewards; } public int totalVotes() { return this.totalVotes; } public int monthlyVotes() { return this.monthlyVotes; } public int monthlyClaimed() { return this.monthlyClaimed; } public int unclaimedVotes() { return this.unclaimedVotes; } public int votesUntilReward() { return this.votesUntilReward; } public int tier() { return this.tier; } public int votesForNextTier() { return this.votesForNextTier; } public List<Integer> tierThresholds() { return this.tierThresholds; } public List<Integer> monthlyThresholds() { return this.monthlyThresholds; } public List<String> linkUrls() { return this.linkUrls; } public List<String> linkLabels() { return this.linkLabels; } public List<VoteNetwork.TierRewardText> tierRewards() { return this.tierRewards; } public List<VoteNetwork.MonthlyRewardText> monthlyRewards() { return this.monthlyRewards; }
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
/* 156 */     return (class_8710.class_9154)VoteNetwork.OPEN_VOTE_SCREEN_TYPE;
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\vote\network\VoteNetwork$OpenVoteScreenPayload.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */