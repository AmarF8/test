/*    */ package com.atlas.common.fabric.block.station.system;
/*    */ 
/*    */ import com.cobblemon.mod.common.pokemon.Pokemon;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ public class StationTier
/*    */ {
/*    */   private final int level;
/*    */   private final double baseGeneration;
/*    */   private final List<StationRequirement> requirements;
/*    */   
/*    */   public StationTier(int level, double baseGeneration, List<StationRequirement> requirements) {
/* 15 */     this.level = level;
/* 16 */     this.baseGeneration = baseGeneration;
/* 17 */     this.requirements = requirements;
/*    */   }
/*    */   
/*    */   public int getLevel() {
/* 21 */     return this.level;
/*    */   }
/*    */   
/*    */   public double getBaseGeneration() {
/* 25 */     return this.baseGeneration;
/*    */   }
/*    */   
/*    */   public List<StationRequirement> getRequirements() {
/* 29 */     return this.requirements;
/*    */   }
/*    */   
/*    */   public boolean areRequirementsMet(List<Pokemon> pokemonList) {
/* 33 */     List<Pokemon> remaining = new ArrayList<>(pokemonList);
/* 34 */     for (StationRequirement req : this.requirements) {
/* 35 */       if (!req.check(pokemonList, remaining)) {
/* 36 */         return false;
/*    */       }
/*    */     } 
/* 39 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\block\station\system\StationTier.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */