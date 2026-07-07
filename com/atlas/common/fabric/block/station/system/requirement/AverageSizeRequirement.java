/*    */ package com.atlas.common.fabric.block.station.system.requirement;
/*    */ 
/*    */ import com.atlas.common.fabric.block.station.system.StationRequirement;
/*    */ import com.cobblemon.mod.common.pokemon.Pokemon;
/*    */ import java.util.List;
/*    */ import org.apache.commons.lang3.StringUtils;
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
/*    */ public class AverageSizeRequirement
/*    */   implements StationRequirement
/*    */ {
/*    */   private final String sizeName;
/*    */   private final float minScale;
/*    */   
/*    */   public AverageSizeRequirement(String sizeName, float minScale) {
/* 27 */     this.sizeName = sizeName;
/* 28 */     this.minScale = minScale;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(List<Pokemon> allPokemon, List<Pokemon> remainingPokemon) {
/* 33 */     if (allPokemon.isEmpty()) return false;
/*    */     
/* 35 */     double totalScale = 0.0D;
/* 36 */     for (Pokemon p : allPokemon) {
/* 37 */       totalScale += p.getScaleModifier();
/*    */     }
/*    */     
/* 40 */     double avg = totalScale / allPokemon.size();
/* 41 */     return (avg >= this.minScale);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getDescription() {
/* 46 */     return "Average Pokemon " + StringUtils.capitalize(this.sizeName) + " or bigger";
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\block\station\system\requirement\AverageSizeRequirement.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */