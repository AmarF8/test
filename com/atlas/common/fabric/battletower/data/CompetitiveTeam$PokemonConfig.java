/*    */ package com.atlas.common.fabric.battletower.data;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PokemonConfig
/*    */ {
/*    */   public final String species;
/*    */   public final String nature;
/*    */   public final String ability;
/*    */   public final int[] ivs;
/*    */   public final int[] evs;
/*    */   public final String[] moves;
/*    */   public final String heldItem;
/*    */   public final String teraType;
/*    */   public final boolean gmaxCapable;
/*    */   
/*    */   public PokemonConfig(String species, String nature, String ability, int[] ivs, int[] evs, String[] moves, String heldItem, String teraType, boolean gmaxCapable) {
/* 33 */     this.species = species;
/* 34 */     this.nature = nature;
/* 35 */     this.ability = ability;
/* 36 */     this.ivs = ivs;
/* 37 */     this.evs = evs;
/* 38 */     this.moves = moves;
/* 39 */     this.heldItem = heldItem;
/* 40 */     this.teraType = teraType;
/* 41 */     this.gmaxCapable = gmaxCapable;
/*    */   }
/*    */   
/*    */   public PokemonConfig(String species, String nature, String ability, int[] ivs, int[] evs, String[] moves, String heldItem, String teraType) {
/* 45 */     this(species, nature, ability, ivs, evs, moves, heldItem, teraType, false);
/*    */   }
/*    */   
/*    */   public PokemonConfig(String species, String nature, String ability, int[] ivs, int[] evs, String[] moves, String heldItem) {
/* 49 */     this(species, nature, ability, ivs, evs, moves, heldItem, (String)null, false);
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\battletower\data\CompetitiveTeam$PokemonConfig.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */