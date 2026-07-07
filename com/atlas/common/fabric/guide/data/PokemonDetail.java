/*     */ package com.atlas.common.fabric.guide.data;
/*     */ 
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.annotations.SerializedName;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PokemonDetail
/*     */ {
/*  15 */   private static final Gson GSON = new Gson();
/*     */ 
/*     */   
/*     */   public int id;
/*     */   
/*  20 */   public String name = "";
/*  21 */   public String slug = "";
/*  22 */   public List<String> types = Collections.emptyList();
/*     */   
/*     */   public int generation;
/*     */   
/*     */   public double height;
/*     */   
/*     */   public double weight;
/*  29 */   public double maleRatio = -1.0D;
/*     */   public int catchRate;
/*     */   public int baseFriendship;
/*     */   public int eggCycles;
/*  33 */   public List<String> eggGroups = Collections.emptyList();
/*  34 */   public String experienceGroup = "";
/*     */ 
/*     */ 
/*     */   
/*  38 */   public BaseStats baseStats = new BaseStats();
/*  39 */   public BaseStats evYield = new BaseStats();
/*     */ 
/*     */ 
/*     */   
/*  43 */   public List<Ability> abilities = Collections.emptyList();
/*     */ 
/*     */ 
/*     */   
/*  47 */   public PokemonMoves moves = new PokemonMoves();
/*     */ 
/*     */ 
/*     */   
/*  51 */   public List<SpawnInfo> spawns = Collections.emptyList();
/*     */ 
/*     */ 
/*     */   
/*  55 */   public List<DropInfo> drops = Collections.emptyList();
/*     */ 
/*     */ 
/*     */   
/*  59 */   public List<EvolutionInfo> evolutions = Collections.emptyList();
/*     */   public String preEvolution;
/*  61 */   public List<EvolutionChainEntry> evolutionChain = Collections.emptyList();
/*     */ 
/*     */ 
/*     */   
/*  65 */   public List<FormInfo> forms = Collections.emptyList();
/*     */ 
/*     */ 
/*     */   
/*  69 */   public List<SkinInfo> skins = Collections.emptyList();
/*     */   
/*     */   public RideableData rideable;
/*     */ 
/*     */   
/*     */   public static class BaseStats
/*     */   {
/*     */     public int hp;
/*     */     
/*     */     public int attack;
/*     */     
/*     */     public int defense;
/*     */     @SerializedName("specialAttack")
/*     */     public int spAtk;
/*     */     @SerializedName("specialDefense")
/*     */     public int spDef;
/*     */     public int speed;
/*     */     
/*     */     public int get(int index) {
/*  88 */       switch (index) { case 0: case 1: case 2: case 3: case 4: case 5:  }  return 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  95 */         0;
/*     */     }
/*     */ 
/*     */     
/*     */     public int total() {
/* 100 */       return this.hp + this.attack + this.defense + this.spAtk + this.spDef + this.speed;
/*     */     } }
/*     */   public static class Ability { public String name; public boolean hidden;
/*     */     
/*     */     public Ability() {
/* 105 */       this.name = "";
/*     */     } }
/*     */   public static class PokemonMoves { public List<PokemonDetail.LevelMove> level; public List<String> tm; public List<String> egg; public List<String> tutor;
/*     */     
/*     */     public PokemonMoves() {
/* 110 */       this.level = Collections.emptyList();
/* 111 */       this.tm = Collections.emptyList();
/* 112 */       this.egg = Collections.emptyList();
/* 113 */       this.tutor = Collections.emptyList();
/*     */     } }
/*     */   public static class LevelMove { public int level; public String name;
/*     */     
/*     */     public LevelMove() {
/* 118 */       this.name = "";
/*     */     } }
/*     */   public static class SpawnInfo { public List<String> biomes; public String rarity; public String levelRange; public String context; public List<String> conditions; public List<String> excludedBiomes;
/*     */     public SpawnInfo() {
/* 122 */       this.biomes = Collections.emptyList();
/* 123 */       this.rarity = "common";
/* 124 */       this.levelRange = "";
/* 125 */       this.context = "grounded";
/* 126 */       this.conditions = Collections.emptyList();
/* 127 */       this.excludedBiomes = Collections.emptyList();
/*     */     } }
/*     */   public static class DropInfo { public String item; public String quantityRange; public double chance;
/*     */     public DropInfo() {
/* 131 */       this.item = "";
/* 132 */       this.quantityRange = "";
/*     */     } }
/*     */   public static class EvolutionInfo { public String to; public String toSlug; public String method; public String trigger;
/*     */     
/*     */     public EvolutionInfo() {
/* 137 */       this.to = "";
/* 138 */       this.toSlug = "";
/* 139 */       this.method = "";
/* 140 */       this.trigger = "";
/*     */     } }
/*     */   public static class EvolutionChainEntry { public int id; public String name; public String slug; public List<String> types; public String methodToNext;
/*     */     public boolean isCurrent;
/*     */     
/*     */     public EvolutionChainEntry() {
/* 146 */       this.name = "";
/* 147 */       this.slug = "";
/* 148 */       this.types = Collections.emptyList();
/* 149 */       this.methodToNext = "";
/* 150 */       this.isCurrent = false;
/*     */     } }
/*     */   public static class FormInfo { public String name; public List<String> types; public List<String> aspects; public PokemonDetail.BaseStats baseStats; public List<PokemonDetail.DropInfo> drops;
/*     */     public FormInfo() {
/* 154 */       this.name = "";
/* 155 */       this.types = Collections.emptyList();
/* 156 */       this.aspects = Collections.emptyList();
/*     */       
/* 158 */       this.drops = Collections.emptyList();
/*     */     } }
/*     */   public static class SkinInfo { public String identifier; public String name; public List<String> aspects; public String color;
/*     */     public SkinInfo() {
/* 162 */       this.identifier = "";
/* 163 */       this.name = "";
/* 164 */       this.aspects = Collections.emptyList();
/* 165 */       this.color = "";
/*     */     } }
/*     */   public static class RideableData { public String movementType; public int seats; public List<RideStat> stats;
/*     */     public RideableData() {
/* 169 */       this.movementType = "";
/*     */       
/* 171 */       this.stats = Collections.emptyList();
/*     */     } public static class RideStat {
/*     */       public String displayName; public String ranges; public int maxValue;
/* 174 */       public RideStat() { this.displayName = "";
/* 175 */         this.ranges = ""; } } } public static class RideStat { public String displayName; public String ranges; public int maxValue; public RideStat() { this.displayName = ""; this.ranges = ""; }
/*     */      }
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
/*     */   public static PokemonDetail fromJson(String json) {
/*     */     try {
/* 190 */       return (PokemonDetail)GSON.fromJson(json, PokemonDetail.class);
/* 191 */     } catch (Exception e) {
/* 192 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toJson() {
/* 200 */     return GSON.toJson(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSpeciesId() {
/* 208 */     return "cobblemon:" + this.slug;
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\guide\data\PokemonDetail.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */