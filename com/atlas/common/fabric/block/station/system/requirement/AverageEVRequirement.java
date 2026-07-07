/*    */ package com.atlas.common.fabric.block.station.system.requirement;
/*    */ 
/*    */ import com.atlas.common.fabric.block.station.system.StationRequirement;
/*    */ import com.cobblemon.mod.common.api.pokemon.stats.Stat;
/*    */ import com.cobblemon.mod.common.api.pokemon.stats.Stats;
/*    */ import com.cobblemon.mod.common.pokemon.Pokemon;
/*    */ import java.util.List;
/*    */ 
/*    */ public class AverageEVRequirement implements StationRequirement {
/*    */   private final int minTotalEVs;
/*    */   
/*    */   public AverageEVRequirement(int minTotalEVs) {
/* 13 */     this.minTotalEVs = minTotalEVs;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(List<Pokemon> allPokemon, List<Pokemon> remainingPokemon) {
/* 18 */     if (allPokemon.isEmpty()) return false; 
/* 19 */     double totalEVs = 0.0D;
/*    */     
/* 21 */     for (Pokemon p : allPokemon) {
/* 22 */       int currentEvs = 0;
/* 23 */       for (Stats stats : Stats.values()) {
/* 24 */         Integer val = p.getEvs().get((Stat)stats);
/* 25 */         currentEvs += (val != null) ? val.intValue() : 0;
/*    */       } 
/* 27 */       totalEVs += currentEvs;
/*    */     } 
/*    */     
/* 30 */     double avg = totalEVs / allPokemon.size();
/* 31 */     return (avg >= this.minTotalEVs);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getDescription() {
/* 36 */     return "Average EV Level: " + this.minTotalEVs;
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\block\station\system\requirement\AverageEVRequirement.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */