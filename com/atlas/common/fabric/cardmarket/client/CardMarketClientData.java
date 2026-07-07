/*    */ package com.atlas.common.fabric.cardmarket.client;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class CardMarketClientData
/*    */ {
/* 14 */   private static final CardMarketClientData INSTANCE = new CardMarketClientData();
/*    */ 
/*    */   
/*    */   public static CardMarketClientData getInstance() {
/* 18 */     return INSTANCE;
/*    */   }
/* 20 */   private CardMarketDto.Snapshot snapshot = new CardMarketDto.Snapshot("all", 0L, 0L, List.of());
/* 21 */   private CardMarketDto.Result lastResult = null;
/* 22 */   private long lastResultAt = 0L;
/*    */   
/*    */   public void setSnapshotJson(String json) {
/* 25 */     this.snapshot = CardMarketDto.parseSnapshot(json);
/*    */   }
/*    */   public CardMarketDto.Snapshot getSnapshot() {
/* 28 */     return this.snapshot;
/*    */   }
/*    */   public void setResultJson(String json) {
/* 31 */     this.lastResult = CardMarketDto.parseResult(json);
/* 32 */     this.lastResultAt = System.currentTimeMillis();
/*    */   }
/*    */   public CardMarketDto.Result getLastResult() {
/* 35 */     return this.lastResult;
/*    */   } public long getLastResultAt() {
/* 37 */     return this.lastResultAt;
/*    */   } public void clearResult() {
/* 39 */     this.lastResult = null;
/*    */   }
/*    */   public void clear() {
/* 42 */     this.snapshot = new CardMarketDto.Snapshot("all", 0L, 0L, List.of());
/* 43 */     this.lastResult = null;
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\cardmarket\client\CardMarketClientData.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */