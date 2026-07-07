/*    */ package com.atlas.common.fabric.vote.data;
/*    */ 
/*    */ import com.atlas.common.fabric.vote.network.VoteNetwork;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class VoteClientData
/*    */ {
/* 15 */   private static final VoteClientData INSTANCE = new VoteClientData(); private int totalVotes;
/*    */   
/*    */   public static VoteClientData getInstance() {
/* 18 */     return INSTANCE;
/*    */   }
/*    */ 
/*    */   
/*    */   private int monthlyVotes;
/*    */   private int monthlyClaimed;
/*    */   private int unclaimedVotes;
/*    */   private int votesUntilReward;
/*    */   private int tier;
/* 27 */   private int votesForNextTier = -1;
/* 28 */   private List<Integer> tierThresholds = List.of();
/* 29 */   private List<Integer> monthlyThresholds = List.of();
/* 30 */   private List<String> linkUrls = List.of();
/* 31 */   private List<String> linkLabels = List.of();
/* 32 */   private List<VoteNetwork.TierRewardText> tierRewards = List.of();
/* 33 */   private List<VoteNetwork.MonthlyRewardText> monthlyRewards = List.of();
/*    */   
/*    */   public void updateFromSync(VoteNetwork.OpenVoteScreenPayload p) {
/* 36 */     this.totalVotes = p.totalVotes();
/* 37 */     this.monthlyVotes = p.monthlyVotes();
/* 38 */     this.monthlyClaimed = p.monthlyClaimed();
/* 39 */     this.unclaimedVotes = p.unclaimedVotes();
/* 40 */     this.votesUntilReward = p.votesUntilReward();
/* 41 */     this.tier = p.tier();
/* 42 */     this.votesForNextTier = p.votesForNextTier();
/* 43 */     this.tierThresholds = p.tierThresholds();
/* 44 */     this.monthlyThresholds = p.monthlyThresholds();
/* 45 */     this.linkUrls = p.linkUrls();
/* 46 */     this.linkLabels = p.linkLabels();
/* 47 */     this.tierRewards = p.tierRewards();
/* 48 */     this.monthlyRewards = p.monthlyRewards();
/*    */   }
/*    */   
/* 51 */   public int getTotalVotes() { return this.totalVotes; }
/* 52 */   public int getMonthlyVotes() { return this.monthlyVotes; }
/* 53 */   public int getMonthlyClaimed() { return this.monthlyClaimed; }
/* 54 */   public int getUnclaimedVotes() { return this.unclaimedVotes; }
/* 55 */   public int getVotesUntilReward() { return this.votesUntilReward; }
/* 56 */   public int getTier() { return this.tier; }
/* 57 */   public int getVotesForNextTier() { return this.votesForNextTier; }
/* 58 */   public boolean isMaxTier() { return (this.votesForNextTier < 0); }
/* 59 */   public List<Integer> getTierThresholds() { return this.tierThresholds; }
/* 60 */   public List<Integer> getMonthlyThresholds() { return this.monthlyThresholds; }
/* 61 */   public List<String> getLinkUrls() { return this.linkUrls; }
/* 62 */   public List<String> getLinkLabels() { return this.linkLabels; }
/* 63 */   public List<VoteNetwork.TierRewardText> getTierRewards() { return this.tierRewards; } public List<VoteNetwork.MonthlyRewardText> getMonthlyRewards() {
/* 64 */     return this.monthlyRewards;
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\vote\data\VoteClientData.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */