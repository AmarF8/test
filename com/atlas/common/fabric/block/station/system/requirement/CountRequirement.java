/*    */ package com.atlas.common.fabric.block.station.system.requirement;
/*    */ 
/*    */ import com.atlas.common.fabric.block.station.system.StationRequirement;
/*    */ import com.cobblemon.mod.common.pokemon.Pokemon;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ 
/*    */ public class CountRequirement
/*    */   implements StationRequirement {
/*    */   private final PokemonCriteria criteria;
/*    */   private final int count;
/*    */   
/*    */   public CountRequirement(PokemonCriteria criteria, int count) {
/* 14 */     this.criteria = criteria;
/* 15 */     this.count = count;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(List<Pokemon> allPokemon, List<Pokemon> remainingPokemon) {
/* 20 */     int found = 0;
/* 21 */     Iterator<Pokemon> it = remainingPokemon.iterator();
/* 22 */     while (it.hasNext()) {
/* 23 */       Pokemon p = it.next();
/* 24 */       if (this.criteria.matches(p)) {
/* 25 */         found++;
/* 26 */         it.remove();
/* 27 */         if (found >= this.count) {
/* 28 */           return true;
/*    */         }
/*    */       } 
/*    */     } 
/* 32 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getDescription() {
/* 37 */     return "" + this.count + "x " + this.count;
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\block\station\system\requirement\CountRequirement.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */