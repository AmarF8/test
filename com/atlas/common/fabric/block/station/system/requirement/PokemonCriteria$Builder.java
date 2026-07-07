/*     */ package com.atlas.common.fabric.block.station.system.requirement;
/*     */ 
/*     */ import com.cobblemon.mod.common.api.types.ElementalType;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Builder
/*     */ {
/*  36 */   private List<ElementalType> types = List.of();
/*  37 */   private int minBaseStatTotal = 0;
/*  38 */   private int minEvolutionStage = 0;
/*     */   private boolean requireFullyEvolved = false;
/*  40 */   private String sizeConstraint = null;
/*     */   private boolean requireShiny = false;
/*     */   private boolean requireLegendary = false;
/*     */   private boolean requireMythical = false;
/*     */   
/*     */   public Builder types(ElementalType... types) {
/*  46 */     this.types = List.of(types);
/*  47 */     return this;
/*     */   }
/*     */   
/*     */   public Builder minBST(int bst) {
/*  51 */     this.minBaseStatTotal = bst;
/*  52 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Builder minEvolutionStage(int stage) {
/*  62 */     this.minEvolutionStage = stage;
/*  63 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Builder fullyEvolved() {
/*  70 */     this.requireFullyEvolved = true;
/*  71 */     return this;
/*     */   }
/*     */   
/*     */   public Builder size(String size) {
/*  75 */     this.sizeConstraint = size;
/*  76 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Builder shiny() {
/*  83 */     this.requireShiny = true;
/*  84 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Builder legendary() {
/*  91 */     this.requireLegendary = true;
/*  92 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Builder mythical() {
/*  99 */     this.requireMythical = true;
/* 100 */     return this;
/*     */   }
/*     */   
/*     */   public PokemonCriteria build() {
/* 104 */     return new PokemonCriteria(this.types, this.minBaseStatTotal, this.minEvolutionStage, this.requireFullyEvolved, this.sizeConstraint, this.requireShiny, this.requireLegendary, this.requireMythical);
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\block\station\system\requirement\PokemonCriteria$Builder.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */