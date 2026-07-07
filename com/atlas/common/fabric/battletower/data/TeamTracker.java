/*    */ package com.atlas.common.fabric.battletower.data;
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ import java.util.UUID;
/*    */ 
/*    */ public class TeamTracker {
/*  7 */   private static final Map<UUID, Set<Integer>> usedTeams = new HashMap<>();
/*    */   
/*    */   public static void markTeamUsed(UUID playerUUID, int teamId) {
/* 10 */     ((Set<Integer>)usedTeams.computeIfAbsent(playerUUID, k -> new HashSet())).add(Integer.valueOf(teamId));
/*    */   }
/*    */   
/*    */   public static boolean isTeamUsed(UUID playerUUID, int teamId) {
/* 14 */     Set<Integer> used = usedTeams.get(playerUUID);
/* 15 */     return (used != null && used.contains(Integer.valueOf(teamId)));
/*    */   }
/*    */   
/*    */   public static Set<Integer> getUsedTeams(UUID playerUUID) {
/* 19 */     return usedTeams.getOrDefault(playerUUID, new HashSet<>());
/*    */   }
/*    */   
/*    */   public static void clearUsedTeams(UUID playerUUID) {
/* 23 */     usedTeams.remove(playerUUID);
/*    */   }
/*    */   
/*    */   public static int getUsedTeamCount(UUID playerUUID) {
/* 27 */     Set<Integer> used = usedTeams.get(playerUUID);
/* 28 */     return (used != null) ? used.size() : 0;
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\battletower\data\TeamTracker.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */