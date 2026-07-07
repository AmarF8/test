/*     */ package com.atlas.common.fabric.block.station.system.requirement;
/*     */ import com.cobblemon.mod.common.api.pokemon.evolution.Evolution;
/*     */ import com.cobblemon.mod.common.api.pokemon.evolution.PreEvolution;
/*     */ import com.cobblemon.mod.common.api.pokemon.stats.Stat;
/*     */ import com.cobblemon.mod.common.api.types.ElementalType;
/*     */ import com.cobblemon.mod.common.pokemon.Pokemon;
/*     */ import com.cobblemon.mod.common.pokemon.Species;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ 
/*     */ public class PokemonCriteria {
/*     */   private final List<ElementalType> types;
/*     */   private final int minBaseStatTotal;
/*     */   private final int minEvolutionStage;
/*     */   private final boolean requireFullyEvolved;
/*     */   private final String sizeConstraint;
/*     */   private final boolean requireShiny;
/*     */   private final boolean requireLegendary;
/*     */   private final boolean requireMythical;
/*     */   
/*     */   public PokemonCriteria(List<ElementalType> types, int minBaseStatTotal, int minEvolutionStage, boolean requireFullyEvolved, String sizeConstraint, boolean requireShiny, boolean requireLegendary, boolean requireMythical) {
/*  25 */     this.types = types;
/*  26 */     this.minBaseStatTotal = minBaseStatTotal;
/*  27 */     this.minEvolutionStage = minEvolutionStage;
/*  28 */     this.requireFullyEvolved = requireFullyEvolved;
/*  29 */     this.sizeConstraint = sizeConstraint;
/*  30 */     this.requireShiny = requireShiny;
/*  31 */     this.requireLegendary = requireLegendary;
/*  32 */     this.requireMythical = requireMythical;
/*     */   }
/*     */   
/*     */   public static class Builder {
/*  36 */     private List<ElementalType> types = List.of();
/*  37 */     private int minBaseStatTotal = 0;
/*  38 */     private int minEvolutionStage = 0;
/*     */     private boolean requireFullyEvolved = false;
/*  40 */     private String sizeConstraint = null;
/*     */     private boolean requireShiny = false;
/*     */     private boolean requireLegendary = false;
/*     */     private boolean requireMythical = false;
/*     */     
/*     */     public Builder types(ElementalType... types) {
/*  46 */       this.types = List.of(types);
/*  47 */       return this;
/*     */     }
/*     */     
/*     */     public Builder minBST(int bst) {
/*  51 */       this.minBaseStatTotal = bst;
/*  52 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder minEvolutionStage(int stage) {
/*  62 */       this.minEvolutionStage = stage;
/*  63 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder fullyEvolved() {
/*  70 */       this.requireFullyEvolved = true;
/*  71 */       return this;
/*     */     }
/*     */     
/*     */     public Builder size(String size) {
/*  75 */       this.sizeConstraint = size;
/*  76 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder shiny() {
/*  83 */       this.requireShiny = true;
/*  84 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder legendary() {
/*  91 */       this.requireLegendary = true;
/*  92 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder mythical() {
/*  99 */       this.requireMythical = true;
/* 100 */       return this;
/*     */     }
/*     */     
/*     */     public PokemonCriteria build() {
/* 104 */       return new PokemonCriteria(this.types, this.minBaseStatTotal, this.minEvolutionStage, this.requireFullyEvolved, this.sizeConstraint, this.requireShiny, this.requireLegendary, this.requireMythical);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean matches(Pokemon pokemon) {
/* 110 */     if (pokemon == null) return false;
/*     */ 
/*     */     
/* 113 */     if (!this.types.isEmpty()) {
/* 114 */       for (ElementalType requiredType : this.types) {
/*     */         
/* 116 */         boolean hasType = (pokemon.getPrimaryType().equals(requiredType) || (pokemon.getSecondaryType() != null && pokemon.getSecondaryType().equals(requiredType)));
/* 117 */         if (!hasType) return false;
/*     */       
/*     */       } 
/*     */     }
/*     */     
/* 122 */     if (this.minBaseStatTotal > 0) {
/* 123 */       Map<Stat, Integer> stats = pokemon.getSpecies().getBaseStats();
/* 124 */       int bst = stats.values().stream().mapToInt(Integer::intValue).sum();
/* 125 */       if (bst < this.minBaseStatTotal) return false;
/*     */     
/*     */     } 
/*     */     
/* 129 */     if (this.minEvolutionStage > 0) {
/* 130 */       int stage = getEvolutionStage(pokemon);
/* 131 */       if (stage < this.minEvolutionStage) return false;
/*     */     
/*     */     } 
/*     */     
/* 135 */     if (this.requireFullyEvolved && 
/* 136 */       !isFullyEvolved(pokemon)) return false;
/*     */ 
/*     */ 
/*     */     
/* 140 */     if (this.sizeConstraint != null && 
/* 141 */       !matchesSize(pokemon, this.sizeConstraint)) return false;
/*     */ 
/*     */ 
/*     */     
/* 145 */     if (this.requireShiny && 
/* 146 */       !pokemon.getShiny()) return false;
/*     */ 
/*     */ 
/*     */     
/* 150 */     if (this.requireLegendary && 
/* 151 */       !pokemon.isLegendary()) return false;
/*     */ 
/*     */ 
/*     */     
/* 155 */     if (this.requireMythical && 
/* 156 */       !pokemon.isMythical()) return false;
/*     */ 
/*     */     
/* 159 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int getEvolutionStage(Pokemon pokemon) {
/* 169 */     Species species = pokemon.getSpecies();
/* 170 */     int stage = 1;
/*     */ 
/*     */     
/* 173 */     PreEvolution preEvolutions = species.getPreEvolution();
/* 174 */     while (preEvolutions != null) {
/* 175 */       stage++;
/* 176 */       Species preEvoSpecies = preEvolutions.getSpecies();
/* 177 */       if (preEvoSpecies == null)
/* 178 */         break;  preEvolutions = preEvoSpecies.getPreEvolution();
/*     */     } 
/*     */     
/* 181 */     return stage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isFullyEvolved(Pokemon pokemon) {
/* 188 */     Species species = pokemon.getSpecies();
/* 189 */     Set<Evolution> evolutions = species.getEvolutions();
/* 190 */     return (evolutions == null || evolutions.isEmpty());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean matchesSize(Pokemon pokemon, String minSize) {
/* 198 */     float scale = pokemon.getScaleModifier();
/*     */     
/* 200 */     switch (minSize.toLowerCase()) { case "large": return 
/* 201 */           (scale >= 1.25F);
/* 202 */       case "extra large": case "extra_large": return (scale >= 1.5F);
/* 203 */       case "huge": return (scale >= 1.75F); }
/*     */     
/*     */     return true;
/*     */   }
/*     */   
/*     */   public String toReadableString() {
/* 209 */     List<String> parts = new ArrayList<>();
/*     */     
/* 211 */     if (this.requireShiny) {
/* 212 */       parts.add("Shiny");
/*     */     }
/*     */     
/* 215 */     if (this.requireLegendary) {
/* 216 */       parts.add("Legendary");
/*     */     }
/*     */     
/* 219 */     if (this.requireMythical) {
/* 220 */       parts.add("Mythical");
/*     */     }
/*     */     
/* 223 */     if (this.requireFullyEvolved) {
/* 224 */       parts.add("Fully Evolved");
/* 225 */     } else if (this.minEvolutionStage == 2) {
/* 226 */       parts.add("Stage 2+");
/* 227 */     } else if (this.minEvolutionStage == 3) {
/* 228 */       parts.add("Stage 3");
/*     */     } 
/*     */     
/* 231 */     if (!this.types.isEmpty()) {
/*     */ 
/*     */ 
/*     */       
/* 235 */       String typeNames = this.types.stream().map(t -> StringUtils.capitalize(t.getName())).reduce((a, b) -> a + "/" + a).orElse("");
/* 236 */       parts.add(typeNames + " Type");
/*     */     } 
/*     */     
/* 239 */     if (this.minBaseStatTotal > 0) {
/* 240 */       parts.add("" + this.minBaseStatTotal + "+ BST");
/*     */     }
/*     */     
/* 243 */     if (this.sizeConstraint != null) {
/* 244 */       parts.add(StringUtils.capitalize(this.sizeConstraint) + "+ Size");
/*     */     }
/*     */     
/* 247 */     if (parts.isEmpty()) return "Any Pokémon"; 
/* 248 */     return String.join(" ", (Iterable)parts);
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\block\station\system\requirement\PokemonCriteria.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */