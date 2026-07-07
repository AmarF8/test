/*    */ package com.atlas.common.fabric.safari.board;
/*    */ 
/*    */ import com.atlas.common.fabric.safari.board.network.SafariBoardNetwork;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class SafariBoardClientData
/*    */ {
/* 15 */   private static final SafariBoardClientData INSTANCE = new SafariBoardClientData();
/*    */   
/* 17 */   private SafariBoardModels.BoardType lastSyncedType = SafariBoardModels.BoardType.CONTRACTS;
/* 18 */   private long contractsNextRefreshAt = 0L;
/* 19 */   private int contractsFreeRerollsRemaining = 1;
/* 20 */   private int contractsNextRerollCost = 100;
/* 21 */   private long huntsNextRefreshAt = 0L;
/* 22 */   private int huntsFreeRerollsRemaining = 1;
/* 23 */   private int huntsNextRerollCost = 100;
/* 24 */   private List<SafariBoardModels.ContractCardData> contracts = new ArrayList<>();
/* 25 */   private List<SafariBoardModels.HuntCardData> hunts = new ArrayList<>();
/* 26 */   private String lastMessage = "";
/*    */   private boolean lastMessageSuccess = true;
/* 28 */   private long lastMessageAt = 0L;
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public static SafariBoardClientData getInstance() {
/* 34 */     return INSTANCE;
/*    */   }
/*    */   
/*    */   public void applySync(@NotNull SafariBoardNetwork.SyncPayload payload) {
/* 38 */     this.lastSyncedType = payload.boardType();
/* 39 */     if (payload.boardType() == SafariBoardModels.BoardType.CONTRACTS) {
/* 40 */       this.contractsNextRefreshAt = payload.nextRefreshAt();
/* 41 */       this.contractsFreeRerollsRemaining = payload.freeRerollsRemaining();
/* 42 */       this.contractsNextRerollCost = payload.nextRerollCost();
/* 43 */       this.contracts = new ArrayList<>(payload.contracts());
/*    */     } else {
/* 45 */       this.huntsNextRefreshAt = payload.nextRefreshAt();
/* 46 */       this.huntsFreeRerollsRemaining = payload.freeRerollsRemaining();
/* 47 */       this.huntsNextRerollCost = payload.nextRerollCost();
/* 48 */       this.hunts = new ArrayList<>(payload.hunts());
/*    */     } 
/*    */   }
/*    */   
/*    */   public void applyActionResult(@NotNull SafariBoardNetwork.ActionResultPayload payload) {
/* 53 */     this.lastMessageSuccess = payload.success();
/* 54 */     this.lastMessage = payload.message();
/* 55 */     this.lastMessageAt = System.currentTimeMillis();
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public SafariBoardModels.BoardType getLastSyncedType() {
/* 60 */     return this.lastSyncedType;
/*    */   }
/*    */   
/*    */   public long getNextRefreshAt(@NotNull SafariBoardModels.BoardType boardType) {
/* 64 */     return (boardType == SafariBoardModels.BoardType.CONTRACTS) ? this.contractsNextRefreshAt : this.huntsNextRefreshAt;
/*    */   }
/*    */   
/*    */   public int getFreeRerollsRemaining(@NotNull SafariBoardModels.BoardType boardType) {
/* 68 */     return (boardType == SafariBoardModels.BoardType.CONTRACTS) ? this.contractsFreeRerollsRemaining : this.huntsFreeRerollsRemaining;
/*    */   }
/*    */   
/*    */   public int getNextRerollCost(@NotNull SafariBoardModels.BoardType boardType) {
/* 72 */     return (boardType == SafariBoardModels.BoardType.CONTRACTS) ? this.contractsNextRerollCost : this.huntsNextRerollCost;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public List<SafariBoardModels.ContractCardData> getContracts() {
/* 77 */     return this.contracts;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public List<SafariBoardModels.HuntCardData> getHunts() {
/* 82 */     return this.hunts;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public String getLastMessage() {
/* 87 */     return this.lastMessage;
/*    */   }
/*    */   
/*    */   public boolean isLastMessageSuccess() {
/* 91 */     return this.lastMessageSuccess;
/*    */   }
/*    */   
/*    */   public long getLastMessageAt() {
/* 95 */     return this.lastMessageAt;
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\safari\board\SafariBoardClientData.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */