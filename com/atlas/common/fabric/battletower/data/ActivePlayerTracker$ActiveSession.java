/*    */ package com.atlas.common.fabric.battletower.data;
/*    */ 
/*    */ import java.util.UUID;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ActiveSession
/*    */ {
/*    */   private final UUID playerUUID;
/*    */   private final String playerName;
/*    */   private final int currentFloor;
/*    */   private final String battleMode;
/*    */   private final long lastUpdateTime;
/*    */   
/*    */   public ActiveSession(UUID playerUUID, String playerName, int currentFloor, String battleMode) {
/* 81 */     this.playerUUID = playerUUID;
/* 82 */     this.playerName = playerName;
/* 83 */     this.currentFloor = currentFloor;
/* 84 */     this.battleMode = battleMode;
/* 85 */     this.lastUpdateTime = System.currentTimeMillis();
/*    */   }
/*    */   
/* 88 */   public UUID getPlayerUUID() { return this.playerUUID; }
/* 89 */   public String getPlayerName() { return this.playerName; }
/* 90 */   public int getCurrentFloor() { return this.currentFloor; }
/* 91 */   public String getBattleMode() { return this.battleMode; } public long getLastUpdateTime() {
/* 92 */     return this.lastUpdateTime;
/*    */   }
/*    */   public boolean isStale() {
/* 95 */     return (System.currentTimeMillis() - this.lastUpdateTime > 300000L);
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\battletower\data\ActivePlayerTracker$ActiveSession.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */