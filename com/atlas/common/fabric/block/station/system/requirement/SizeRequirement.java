/*    */ package com.atlas.common.fabric.block.station.system.requirement;
/*    */ 
/*    */ import com.atlas.common.fabric.block.station.system.StationRequirement;
/*    */ import com.cobblemon.mod.common.pokemon.Pokemon;
/*    */ import java.util.List;
/*    */ 
/*    */ public class SizeRequirement implements StationRequirement {
/*    */   private final String minSize;
/*    */   
/*    */   public SizeRequirement(String minSize) {
/* 11 */     this.minSize = minSize;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(List<Pokemon> allPokemon, List<Pokemon> remainingPokemon) {
/* 16 */     if (allPokemon.isEmpty()) return false;
/*    */     
/* 18 */     for (Pokemon pokemon : allPokemon);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 26 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getDescription() {
/* 31 */     return "Pokemon size: " + this.minSize + " or bigger";
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\block\station\system\requirement\SizeRequirement.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */