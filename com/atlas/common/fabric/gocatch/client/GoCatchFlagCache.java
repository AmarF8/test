/*    */ package com.atlas.common.fabric.gocatch.client;
/*    */ 
/*    */ import java.util.concurrent.ConcurrentHashMap;
/*    */ import net.fabricmc.api.EnvType;
/*    */ import net.fabricmc.api.Environment;
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
/*    */ @Environment(EnvType.CLIENT)
/*    */ public final class GoCatchFlagCache
/*    */ {
/* 19 */   private static final GoCatchFlagCache INSTANCE = new GoCatchFlagCache();
/*    */   
/*    */   public static GoCatchFlagCache getInstance() {
/* 22 */     return INSTANCE;
/*    */   }
/*    */ 
/*    */   
/* 26 */   private final ConcurrentHashMap<Integer, String> flagged = new ConcurrentHashMap<>();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void set(int entityId, boolean enabled, String tier) {
/* 35 */     if (enabled) {
/* 36 */       this.flagged.put(Integer.valueOf(entityId), (tier == null) ? "" : tier);
/*    */     } else {
/* 38 */       this.flagged.remove(Integer.valueOf(entityId));
/*    */     } 
/*    */   }
/*    */   
/*    */   public boolean isFlagged(int entityId) {
/* 43 */     return this.flagged.containsKey(Integer.valueOf(entityId));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getTier(int entityId) {
/* 50 */     return this.flagged.getOrDefault(Integer.valueOf(entityId), "");
/*    */   }
/*    */   
/*    */   public void clear() {
/* 54 */     this.flagged.clear();
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\gocatch\client\GoCatchFlagCache.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */