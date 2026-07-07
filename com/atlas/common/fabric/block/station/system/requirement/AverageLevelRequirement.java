/*    */ package com.atlas.common.fabric.block.station.system.requirement;
/*    */ 
/*    */ import com.atlas.common.fabric.block.station.system.StationRequirement;
/*    */ import com.cobblemon.mod.common.pokemon.Pokemon;
/*    */ import java.util.List;
/*    */ 
/*    */ public class AverageLevelRequirement implements StationRequirement {
/*    */   private final int minAverageLevel;
/*    */   
/*    */   public AverageLevelRequirement(int minAverageLevel) {
/* 11 */     this.minAverageLevel = minAverageLevel;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(List<Pokemon> allPokemon, List<Pokemon> remainingPokemon) {
/* 16 */     if (allPokemon.isEmpty()) return false; 
/* 17 */     double total = 0.0D;
/* 18 */     for (Pokemon p : allPokemon) {
/* 19 */       total += p.getLevel();
/*    */     }
/* 21 */     double avg = total / allPokemon.size();
/* 22 */     return (avg >= this.minAverageLevel);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getDescription() {
/* 27 */     return "Average Pokemon Level: " + this.minAverageLevel;
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\block\station\system\requirement\AverageLevelRequirement.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */