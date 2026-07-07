/*    */ package com.atlas.common.fabric.battletower.battle;
/*    */ 
/*    */ import com.cobblemon.mod.common.pokemon.Pokemon;
/*    */ import java.util.HashSet;
/*    */ 
/*    */ public class LegendaryChecker
/*    */ {
/*    */   public static boolean isRestricted(Pokemon pokemon) {
/*  9 */     if (pokemon == null)
/* 10 */       return false; 
/* 11 */     if (pokemon.isLegendary()) {
/* 12 */       return true;
/*    */     }
/* 14 */     HashSet<String> labels = pokemon.getSpecies().getLabels();
/* 15 */     if (labels.contains("mythical"))
/* 16 */       return true; 
/* 17 */     if (labels.contains("paradox")) {
/* 18 */       return true;
/*    */     }
/* 20 */     return (labels.contains("ultra_beast") || labels.contains("ultrabeast"));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static String getRestrictionReason(Pokemon pokemon) {
/* 26 */     if (pokemon.isLegendary()) {
/* 27 */       return "Legendary";
/*    */     }
/* 29 */     HashSet<String> labels = pokemon.getSpecies().getLabels();
/* 30 */     if (labels.contains("mythical"))
/* 31 */       return "Mythical"; 
/* 32 */     if (labels.contains("paradox")) {
/* 33 */       return "Paradox";
/*    */     }
/* 35 */     return (!labels.contains("ultra_beast") && !labels.contains("ultrabeast")) ? "Restricted" : "Ultra Beast";
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\battletower\battle\LegendaryChecker.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */