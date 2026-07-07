/*    */ package com.atlas.common.fabric.battletower.data;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.UUID;
/*    */ import java.util.concurrent.ConcurrentHashMap;
/*    */ import java.util.stream.Collectors;
/*    */ 
/*    */ public class ActivePlayerTracker {
/*  9 */   private static final Map<UUID, ActiveSession> activeSessions = new ConcurrentHashMap<>();
/*    */ 
/*    */   
/*    */   private static final long STALE_TIMEOUT_MS = 300000L;
/*    */ 
/*    */ 
/*    */   
/*    */   public static void updateSession(UUID playerUUID, String playerName, int currentFloor, String battleMode) {
/* 17 */     activeSessions.put(playerUUID, new ActiveSession(playerUUID, playerName, currentFloor, battleMode));
/*    */   }
/*    */   
/*    */   public static void removeSession(UUID playerUUID) {
/* 21 */     activeSessions.remove(playerUUID);
/*    */   }
/*    */   
/*    */   public static void clearAll() {
/* 25 */     activeSessions.clear();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static ActiveSession getSession(UUID playerUUID) {
/* 31 */     ActiveSession session = activeSessions.get(playerUUID);
/* 32 */     if (session != null && session.isStale()) {
/* 33 */       activeSessions.remove(playerUUID);
/* 34 */       return null;
/*    */     } 
/* 36 */     return session;
/*    */   }
/*    */   
/*    */   public static boolean hasActiveSession(UUID playerUUID) {
/* 40 */     return (getSession(playerUUID) != null);
/*    */   }
/*    */ 
/*    */   
/*    */   public static List<ActiveSession> getAllActiveSessions() {
/* 45 */     activeSessions.entrySet().removeIf(entry -> ((ActiveSession)entry.getValue()).isStale());
/* 46 */     return new ArrayList<>(activeSessions.values());
/*    */   }
/*    */   
/*    */   public static List<ActiveSession> getPlayersOnFloor(int floor) {
/* 50 */     return (List<ActiveSession>)getAllActiveSessions().stream()
/* 51 */       .filter(session -> (session.getCurrentFloor() == floor))
/* 52 */       .collect(Collectors.toList());
/*    */   }
/*    */   
/*    */   public static int getPlayerCountOnFloor(int floor) {
/* 56 */     return 
/*    */       
/* 58 */       (int)getAllActiveSessions().stream().filter(session -> (session.getCurrentFloor() == floor)).count();
/*    */   }
/*    */   
/*    */   public static Map<Integer, List<ActiveSession>> getSessionsByFloor() {
/* 62 */     return (Map<Integer, List<ActiveSession>>)getAllActiveSessions().stream()
/* 63 */       .collect(Collectors.groupingBy(ActiveSession::getCurrentFloor));
/*    */   }
/*    */   
/*    */   public static int getTotalActivePlayers() {
/* 67 */     return getAllActiveSessions().size();
/*    */   }
/*    */ 
/*    */   
/*    */   public static class ActiveSession
/*    */   {
/*    */     private final UUID playerUUID;
/*    */     
/*    */     private final String playerName;
/*    */     private final int currentFloor;
/*    */     private final String battleMode;
/*    */     private final long lastUpdateTime;
/*    */     
/*    */     public ActiveSession(UUID playerUUID, String playerName, int currentFloor, String battleMode) {
/* 81 */       this.playerUUID = playerUUID;
/* 82 */       this.playerName = playerName;
/* 83 */       this.currentFloor = currentFloor;
/* 84 */       this.battleMode = battleMode;
/* 85 */       this.lastUpdateTime = System.currentTimeMillis();
/*    */     }
/*    */     
/* 88 */     public UUID getPlayerUUID() { return this.playerUUID; }
/* 89 */     public String getPlayerName() { return this.playerName; }
/* 90 */     public int getCurrentFloor() { return this.currentFloor; }
/* 91 */     public String getBattleMode() { return this.battleMode; } public long getLastUpdateTime() {
/* 92 */       return this.lastUpdateTime;
/*    */     }
/*    */     public boolean isStale() {
/* 95 */       return (System.currentTimeMillis() - this.lastUpdateTime > 300000L);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\battletower\data\ActivePlayerTracker.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */