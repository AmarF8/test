/*    */ package com.atlas.common.fabric.block.station.client;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class StationRenderClientState
/*    */ {
/*    */   private static boolean stationPokemonRenderingEnabled = true;
/*    */   
/*    */   public static boolean isStationPokemonRenderingEnabled() {
/* 11 */     return stationPokemonRenderingEnabled;
/*    */   }
/*    */   
/*    */   public static void setStationPokemonRenderingEnabled(boolean enabled) {
/* 15 */     stationPokemonRenderingEnabled = enabled;
/*    */   }
/*    */   
/*    */   public static void reset() {
/* 19 */     stationPokemonRenderingEnabled = true;
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\block\station\client\StationRenderClientState.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */