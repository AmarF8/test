/*    */ package com.atlas.common.fabric.block.station.system.requirement;
/*    */ 
/*    */ import com.atlas.common.fabric.block.station.system.StationRequirement;
/*    */ import com.cobblemon.mod.common.api.pokemon.stats.Stat;
/*    */ import com.cobblemon.mod.common.api.pokemon.stats.Stats;
/*    */ import com.cobblemon.mod.common.pokemon.Pokemon;
/*    */ import java.util.List;
/*    */ 
/*    */ public class AverageIVRequirement
/*    */   implements StationRequirement {
/*    */   private final double minPercentage;
/*    */   
/*    */   public AverageIVRequirement(double minPercentage) {
/* 14 */     this.minPercentage = minPercentage;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(List<Pokemon> allPokemon, List<Pokemon> remainingPokemon) {
/* 19 */     if (allPokemon.isEmpty()) return false; 
/* 20 */     double totalPercentage = 0.0D;
/*    */     
/* 22 */     for (Pokemon p : allPokemon) {
/* 23 */       int currentIvs = 0;
/* 24 */       for (Stats stats : Stats.values()) {
/* 25 */         Integer val = p.getIvs().get((Stat)stats);
/* 26 */         currentIvs += (val != null) ? val.intValue() : 0;
/*    */       } 
/* 28 */       totalPercentage += currentIvs / 186.0D;
/*    */     } 
/*    */     
/* 31 */     double avg = totalPercentage / allPokemon.size();
/* 32 */     return (avg >= this.minPercentage);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getDescription() {
/* 37 */     return "Average Pokemon IV: " + (int)(this.minPercentage * 100.0D) + "%";
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\block\station\system\requirement\AverageIVRequirement.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */