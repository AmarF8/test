/*    */ package com.atlas.common.fabric.block.station.system;
/*    */ 
/*    */ import com.cobblemon.mod.common.pokemon.Pokemon;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ public class StationDefinition
/*    */ {
/*    */   private final String id;
/*    */   private final String displayName;
/*    */   private final int stationTier;
/*    */   private final List<StationRequirement> baseRequirements;
/* 15 */   private final List<StationTier> tiers = new ArrayList<>();
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public StationDefinition(@NotNull String id, @NotNull String displayName, int stationTier, @NotNull List<StationRequirement> baseRequirements) {
/* 21 */     this.id = id;
/* 22 */     this.displayName = displayName;
/* 23 */     this.stationTier = stationTier;
/* 24 */     this.baseRequirements = baseRequirements;
/*    */   }
/*    */   
/*    */   public String getId() {
/* 28 */     return this.id;
/*    */   }
/*    */   
/*    */   public String getDisplayName() {
/* 32 */     return this.displayName;
/*    */   }
/*    */   
/*    */   public int getStationTier() {
/* 36 */     return this.stationTier;
/*    */   }
/*    */   
/*    */   public List<StationRequirement> getBaseRequirements() {
/* 40 */     return this.baseRequirements;
/*    */   }
/*    */   
/*    */   public List<StationTier> getTiers() {
/* 44 */     return this.tiers;
/*    */   }
/*    */   
/*    */   public void addTier(StationTier tier) {
/* 48 */     this.tiers.add(tier);
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public StationTier getActiveTier(List<Pokemon> pokemon) {
/* 53 */     StationTier activeTier = null;
/*    */     
/* 55 */     for (StationTier tier : this.tiers) {
/*    */       
/* 57 */       boolean basesMet = true;
/* 58 */       for (StationRequirement req : this.baseRequirements) {
/* 59 */         if (!req.check(pokemon, new ArrayList<>(pokemon))) {
/* 60 */           basesMet = false;
/*    */           break;
/*    */         } 
/*    */       } 
/* 64 */       if (!basesMet) {
/*    */         break;
/*    */       }
/* 67 */       boolean tierMet = true;
/* 68 */       for (StationRequirement req : tier.getRequirements()) {
/* 69 */         if (!req.check(pokemon, new ArrayList<>(pokemon))) {
/* 70 */           tierMet = false;
/*    */           
/*    */           break;
/*    */         } 
/*    */       } 
/* 75 */       if (tierMet) {
/* 76 */         activeTier = tier;
/*    */       }
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 82 */     return activeTier;
/*    */   }
/*    */   
/*    */   public double calculateGeneration(List<Pokemon> pokemon) {
/* 86 */     StationTier tier = getActiveTier(pokemon);
/* 87 */     if (tier == null) return 0.0D;
/*    */     
/* 89 */     double base = tier.getBaseGeneration();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 97 */     return base;
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\block\station\system\StationDefinition.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */