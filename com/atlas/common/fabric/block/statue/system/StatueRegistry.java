/*     */ package com.atlas.common.fabric.block.statue.system;
/*     */ 
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.minecraft.class_2680;
/*     */ import net.minecraft.class_7923;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public final class StatueRegistry
/*     */ {
/*  13 */   private static final Map<String, StatueDefinition> DEFINITIONS = new LinkedHashMap<>();
/*     */   
/*     */   static {
/*  16 */     registerTypePair("pidgeot", "flying", mediumStatue(), 1.5D, 2.0D);
/*  17 */     registerTypePair("pikachu", "electric", smallStatue(), 1.5D, 2.0D);
/*  18 */     registerTypePair("clefable", "fairy", smallStatue(), 1.5D, 2.0D);
/*  19 */     registerTypePair("alakazam", "psychic", mediumStatue(), 1.5D, 2.0D);
/*  20 */     registerTypePair("machamp", "fighting", mediumStatue(), 1.5D, 2.0D);
/*  21 */     registerTypePair("gengar", "ghost", mediumStatue(), 1.5D, 2.0D);
/*  22 */     registerTypePair("onix", "rock", largeWideStatue(), 1.5D, 2.0D);
/*  23 */     registerTypePair("gyarados", "water", largeWideStatue(), 1.5D, 2.0D);
/*  24 */     registerTypePair("heracross", "bug", mediumStatue(), 1.5D, 2.0D);
/*  25 */     registerTypePair("breloom", "poison", mediumStatue(), 1.5D, 2.0D);
/*  26 */     registerTypePair("aggron", "steel", mediumStatue(), 1.5D, 2.0D);
/*  27 */     registerSpecialPair("lunatone", StatueEffectType.RARE_NIGHT_SPAWN_BOOST, 1.25D, 1.5D, smallStatue());
/*  28 */     registerSpecialPair("solrock", StatueEffectType.RARE_DAY_SPAWN_BOOST, 1.25D, 1.5D, smallStatue());
/*  29 */     registerTypePair("torterra", "grass", mediumStatue(), 1.5D, 2.0D);
/*  30 */     registerTypePair("magmortar", "fire", mediumStatue(), 1.5D, 2.0D);
/*  31 */     registerTypePair("froslass", "ice", mediumStatue(), 1.5D, 2.0D);
/*  32 */     registerTypePair("excadrill", "ground", mediumStatue(), 1.5D, 2.0D);
/*  33 */     registerTypePair("hydreigon", "dragon", largeStatue(), 1.5D, 2.0D);
/*  34 */     registerSpecialPair("celebi", StatueEffectType.WISH_SPAWN, 0.05D, 0.1D, smallStatue());
/*  35 */     registerSpecialPair("dialga", StatueEffectType.STATION_EFFICIENCY, 1.5D, 2.0D, largeStatue());
/*  36 */     registerSpecialPair("giratina", "giratina_statue", StatueEffectType.IV_BONUS, 0.05D, 0.1D, largeStatue());
/*  37 */     registerSpecialPair("jirachi", StatueEffectType.CRATE_KEY, 0.02D, 0.05D, smallStatue());
/*  38 */     registerSpecialPair("palkia", StatueEffectType.RAID_DAMAGE, 2.0D, 3.0D, largeStatue());
/*     */     
/*  40 */     registerTypePair("mega_rayquaza", "megarayquaza", "dragon", massiveStatue(), 1.75D, 2.25D);
/*  41 */     registerSpecialPair("origin_giratina", "giratina_origin", StatueEffectType.IV_BONUS, 0.08D, 0.15D, massiveStatue());
/*  42 */     registerTypePair("primal_groudon", "groudonprimal", "ground", massiveStatue(), 1.75D, 2.25D);
/*  43 */     registerTypePair("primal_kyogre", "kyogreprimal", "water", largeWideStatue(), 1.75D, 2.25D);
/*  44 */     registerSpecialPair("origin_palkia", "palkia_origin", StatueEffectType.RAID_DAMAGE, 2.5D, 3.5D, massiveStatue());
/*  45 */     registerSpecialPair("origin_dialga", "dialga_origin", StatueEffectType.STATION_EFFICIENCY, 1.75D, 2.25D, massiveStatue());
/*  46 */     registerTypePair("black_kyurem", "kyuremblack", "dragon", massiveStatue(), 1.75D, 2.25D);
/*  47 */     registerTypePair("white_kyurem", "kyuremwhite", "ice", massiveStatue(), 1.75D, 2.25D);
/*  48 */     registerTypePair("dusk_mane", "necrozma_duskmane", "steel", massiveStatue(), 1.75D, 2.25D);
/*  49 */     registerTypePair("dawn_wings", "necrozma_dawnwings", "ghost", massiveStatue(), 1.75D, 2.25D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void register(@NotNull StatueDefinition definition) {
/*  56 */     DEFINITIONS.put(definition.id(), definition);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void registerTypePair(@NotNull String statueName, @NotNull String typeName, @NotNull StatueStructure structure, double baseMultiplier, double gildedMultiplier) {
/*  64 */     registerTypePair(statueName, statueName, typeName, structure, baseMultiplier, gildedMultiplier);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void registerTypePair(@NotNull String statueName, @NotNull String modelName, @NotNull String typeName, @NotNull StatueStructure structure, double baseMultiplier, double gildedMultiplier) {
/*  73 */     registerPair(statueName, modelName, structure, 
/*  74 */         StatueEffect.typeSpawnBoost(typeName, baseMultiplier), 
/*  75 */         StatueEffect.typeSpawnBoost(typeName, gildedMultiplier));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void registerSpecialPair(@NotNull String statueName, @NotNull StatueEffectType effectType, double baseValue, double gildedValue, @NotNull StatueStructure structure) {
/*  83 */     registerSpecialPair(statueName, statueName, effectType, baseValue, gildedValue, structure);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void registerSpecialPair(@NotNull String statueName, @NotNull String modelName, @NotNull StatueEffectType effectType, double baseValue, double gildedValue, @NotNull StatueStructure structure) {
/*  92 */     registerPair(statueName, modelName, structure, new StatueEffect(effectType, null, baseValue), new StatueEffect(effectType, null, gildedValue));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void registerPair(@NotNull String statueName, @NotNull String modelName, @NotNull StatueStructure structure, @NotNull StatueEffect baseEffect, @NotNull StatueEffect gildedEffect) {
/* 102 */     register(new StatueDefinition(statueName + "_statue", modelName, modelName + "_gray", structure, baseEffect, false));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 110 */     register(new StatueDefinition("gilded_" + statueName + "_statue", modelName, modelName + "_gold", structure, gildedEffect, true));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static StatueDefinition get(@NotNull String id) {
/* 121 */     return DEFINITIONS.get(id);
/*     */   }
/*     */   @Nullable
/*     */   public static StatueDefinition get(@NotNull class_2680 state) {
/* 125 */     return DEFINITIONS.get(class_7923.field_41175.method_10221(state.method_26204()).method_12832());
/*     */   }
/*     */   @NotNull
/*     */   public static List<StatueDefinition> definitions() {
/* 129 */     return List.copyOf(DEFINITIONS.values());
/*     */   }
/*     */   @NotNull
/*     */   private static StatueStructure smallStatue() {
/* 133 */     return StatueStructure.builder()
/* 134 */       .fill(-1, 1, 0, 0, -1, 1)
/* 135 */       .fill(-1, 1, 1, 2, -1, 1)
/* 136 */       .build();
/*     */   }
/*     */   @NotNull
/*     */   private static StatueStructure mediumStatue() {
/* 140 */     return StatueStructure.builder()
/* 141 */       .fill(-2, 2, 0, 0, -2, 2)
/* 142 */       .fill(-1, 1, 1, 3, -1, 1)
/* 143 */       .build();
/*     */   }
/*     */   @NotNull
/*     */   private static StatueStructure largeStatue() {
/* 147 */     return StatueStructure.builder()
/* 148 */       .fill(-2, 2, 0, 1, -2, 2)
/* 149 */       .fill(-1, 1, 2, 4, -1, 1)
/* 150 */       .build();
/*     */   }
/*     */   @NotNull
/*     */   private static StatueStructure largeWideStatue() {
/* 154 */     return StatueStructure.builder()
/* 155 */       .fill(-2, 2, 0, 0, -3, 3)
/* 156 */       .fill(-1, 1, 1, 3, -2, 2)
/* 157 */       .build();
/*     */   }
/*     */   @NotNull
/*     */   private static StatueStructure massiveStatue() {
/* 161 */     return StatueStructure.builder()
/* 162 */       .fill(-3, 3, 0, 0, -3, 3)
/* 163 */       .fill(-2, 2, 1, 2, -2, 2)
/* 164 */       .fill(-1, 1, 3, 5, -1, 1)
/* 165 */       .build();
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\block\statue\system\StatueRegistry.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */