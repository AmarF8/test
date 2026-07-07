/*     */ package com.atlas.common.fabric.safari.fishing;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import net.minecraft.class_2338;
/*     */ import net.minecraft.class_3218;
/*     */ import net.minecraft.class_5819;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public final class SafariFishCatalog
/*     */ {
/*  15 */   private static final Map<String, SafariFishDefinition> DEFINITIONS = new LinkedHashMap<>();
/*     */   
/*     */   static {
/*  18 */     register("anglerfish", "Anglerfish", SafariFishRarity.RARE, 0.82D, 1.38D, 12.0D, 39.0D, 1.18D, 1.24D, 0.18D, true, 
/*  19 */         Set.of("deep", "ocean"), new String[] { "black_angler_fish", "deep_angler_fish", "red_angler_fish" });
/*     */     
/*  21 */     register("jellyfish", "Jellyfish", SafariFishRarity.UNCOMMON, 0.88D, 1.32D, 4.0D, 15.5D, 1.04D, 1.02D, 0.23D, false, 
/*  22 */         Set.of("warm", "lukewarm", "ocean", "beach"), new String[] { "glow_fat_jellyfish", "glow_jellyfish", "glow_long_jellyfish", "lime_fat_jellyfish", "lime_jellyfish", "lime_long_jellyfish", "orange_fat_jellyfish", "orange_jellyfish", "orange_long_jellyfish", "pink_fat_jellyfish", "pink_jellyfish", "pink_long_jellyfish", "purple_fat_jellyfish", "purple_jellyfish", "purple_long_jellyfish", "red_fat_jellyfish", "red_jellyfish", "red_long_jellyfish", "turquoise_fat_jellyfish", "turquoise_jellyfish", "turquoise_long_jellyfish", "yellow_fat_jellyfish", "yellow_jellyfish", "yellow_long_jellyfish" });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  29 */     register("swordfish", "Swordfish", SafariFishRarity.RARE, 0.94D, 1.35D, 18.0D, 61.0D, 1.2D, 1.16D, 0.17D, false, 
/*  30 */         Set.of("ocean", "deep"), new String[] { "haunted_swordfish", "marlin", "sailfish", "sunken_swordfish", "swordfish" });
/*     */     
/*  32 */     register("hammerhead_shark", "Hammerhead Shark", SafariFishRarity.EPIC, 1.04D, 1.44D, 60.0D, 185.0D, 1.32D, 1.26D, 0.15D, true, 
/*  33 */         Set.of("deep", "ocean"), new String[] { "hammerhead_shark" });
/*     */     
/*  35 */     register("manta_ray", "Manta Ray", SafariFishRarity.UNCOMMON, 0.92D, 1.3D, 16.0D, 48.0D, 1.08D, 1.05D, 0.21D, false, 
/*  36 */         Set.of("warm", "lukewarm", "ocean"), new String[] { "black_manta_ray", "blue_manta_ray", "gray_manta_ray" });
/*     */     
/*  38 */     register("electric_eel", "Electric Eel", SafariFishRarity.RARE, 0.9D, 1.28D, 8.0D, 24.0D, 1.16D, 1.22D, 0.19D, false, 
/*  39 */         Set.of("river", "swamp", "lukewarm"), new String[] { "black_electric_eel", "electro_eel", "green_electric_eel" });
/*     */     
/*  41 */     register("seahorse", "Seahorse", SafariFishRarity.UNCOMMON, 0.75D, 1.1D, 1.0D, 4.5D, 1.0D, 1.02D, 0.24D, false, 
/*  42 */         Set.of("warm", "lukewarm", "reef", "beach"), new String[] { "blue_seahorse", "green_seahorse", "orange_seahorse", "pink_seahorse", "purple_seahorse", "red_seahorse", "yellow_seahorse" });
/*     */ 
/*     */     
/*  45 */     register("sea_turtle", "Sea Turtle", SafariFishRarity.UNCOMMON, 0.95D, 1.36D, 22.0D, 78.0D, 1.14D, 1.08D, 0.2D, false, 
/*  46 */         Set.of("beach", "ocean", "warm"), new String[] { "black_sea_turtle", "brown_sea_turtle", "green_sea_turtle" });
/*     */     
/*  48 */     register("lobster", "Lobster", SafariFishRarity.UNCOMMON, 0.82D, 1.18D, 2.5D, 9.0D, 1.08D, 1.04D, 0.22D, false, 
/*  49 */         Set.of("reef", "ocean", "beach"), new String[] { "blue_lobster", "red_lobster" });
/*     */     
/*  51 */     register("crab", "Crab", SafariFishRarity.COMMON, 0.78D, 1.12D, 1.5D, 6.5D, 0.98D, 0.94D, 0.26D, false, 
/*  52 */         Set.of("beach", "shore", "ocean"), new String[] { "blue_crab", "brown_crab", "green_crab", "red_crab", "yellow_crab" });
/*     */     
/*  54 */     register("sea_squid", "Sea Squid", SafariFishRarity.UNCOMMON, 0.9D, 1.26D, 5.0D, 16.0D, 1.1D, 1.06D, 0.22D, false, 
/*  55 */         Set.of("ocean", "deep"), new String[] { "black_sea_squid", "glow_sea_squid", "white_sea_squid" });
/*     */     
/*  57 */     register("stingray", "Stingray", SafariFishRarity.UNCOMMON, 0.92D, 1.24D, 11.0D, 31.0D, 1.09D, 1.08D, 0.21D, false, 
/*  58 */         Set.of("ocean", "warm", "lukewarm"), new String[] { "black_stingray", "brown_stingray", "gray_stingray" });
/*     */     
/*  60 */     register("shrimp", "Shrimp", SafariFishRarity.COMMON, 0.7D, 1.02D, 0.4D, 1.5D, 0.94D, 0.9D, 0.28D, false, 
/*  61 */         Set.of("reef", "warm", "beach"), new String[] { "blue_shrimp", "red_shrimp" });
/*     */     
/*  63 */     register("sea_isopod", "Sea Isopod", SafariFishRarity.UNCOMMON, 0.82D, 1.1D, 1.8D, 5.5D, 1.03D, 1.0D, 0.23D, true, 
/*  64 */         Set.of("deep", "ocean"), new String[] { "black_sea_isopod", "brown_sea_isopod", "purple_sea_isopod", "red_sea_isopod", "yellow_sea_isopod" });
/*     */   }
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
/*     */   private static void register(@NotNull String id, @NotNull String displayName, @NotNull SafariFishRarity rarity, double minSizeScale, double maxSizeScale, double minWeightKg, double maxWeightKg, double reelDifficulty, double tensionDifficulty, double baseSafeZoneWidth, boolean deepWaterPreferred, @NotNull Set<String> biomeHints, @NotNull String... modelNames) {
/*  86 */     List<SafariFishDefinition.Variant> variants = new ArrayList<>();
/*  87 */     for (String modelName : modelNames) {
/*  88 */       variants.add(new SafariFishDefinition.Variant(id + "/" + id, SafariRodData.prettyName(modelName)));
/*     */     }
/*  90 */     DEFINITIONS.put(id, new SafariFishDefinition(id, displayName, rarity, variants, minSizeScale, maxSizeScale, minWeightKg, maxWeightKg, reelDifficulty, tensionDifficulty, baseSafeZoneWidth, deepWaterPreferred, biomeHints));
/*     */   }
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
/*     */   public static SafariCatchProfile rollCatch(@NotNull class_3218 world, @NotNull class_2338 pos, @NotNull SafariRodData.RodLoadout loadout) {
/* 108 */     class_5819 random = world.method_8409();
/* 109 */     String biomePath = world.method_23753(pos).method_55840().toLowerCase();
/*     */     
/* 111 */     List<WeightedDefinition> weighted = new ArrayList<>();
/* 112 */     double total = 0.0D;
/* 113 */     for (SafariFishDefinition definition : DEFINITIONS.values()) {
/* 114 */       double weight = definition.rarity().getSelectionWeight() * definition.biomeScore(biomePath);
/* 115 */       if (definition.deepWaterPreferred()) weight *= loadout.deepBonus(); 
/* 116 */       if (definition.rarity().ordinal() >= SafariFishRarity.RARE.ordinal()) weight *= loadout.rareBonus(); 
/* 117 */       weighted.add(new WeightedDefinition(definition, weight));
/* 118 */       total += weight;
/*     */     } 
/*     */     
/* 121 */     if (total <= 0.0D) {
/* 122 */       SafariFishDefinition fallback = DEFINITIONS.values().iterator().next();
/* 123 */       return makeProfile(fallback, random, loadout);
/*     */     } 
/*     */     
/* 126 */     double roll = random.method_43058() * total;
/* 127 */     for (WeightedDefinition entry : weighted) {
/* 128 */       roll -= entry.weight();
/* 129 */       if (roll <= 0.0D) {
/* 130 */         return makeProfile(entry.definition(), random, loadout);
/*     */       }
/*     */     } 
/*     */     
/* 134 */     return makeProfile(((WeightedDefinition)weighted.get(weighted.size() - 1)).definition(), random, loadout);
/*     */   }
/*     */   
/*     */   private static SafariCatchProfile makeProfile(@NotNull SafariFishDefinition definition, @NotNull class_5819 random, @NotNull SafariRodData.RodLoadout loadout) {
/* 138 */     SafariFishDefinition.Variant variant = definition.pickVariant(random);
/* 139 */     double size = definition.rollSize(random);
/* 140 */     double weight = definition.rollWeightKg(random) * (0.8D + size * 0.25D);
/* 141 */     return new SafariCatchProfile(definition
/* 142 */         .id(), definition
/* 143 */         .displayName(), variant, definition
/*     */         
/* 145 */         .rarity(), size, weight, definition
/*     */ 
/*     */         
/* 148 */         .reelDifficulty() * definition.rarity().getCatchDifficulty(), definition
/* 149 */         .tensionDifficulty() * definition.rarity().getTensionDifficulty(), 
/* 150 */         Math.max(0.12D, definition.baseSafeZoneWidth() + loadout.safeZoneBonus()));
/*     */   }
/*     */ 
/*     */   
/*     */   public static Map<String, SafariFishDefinition> getDefinitions() {
/* 155 */     return DEFINITIONS;
/*     */   } private static final class WeightedDefinition extends Record { @NotNull
/*     */     private final SafariFishDefinition definition; private final double weight;
/* 158 */     private WeightedDefinition(@NotNull SafariFishDefinition definition, double weight) { this.definition = definition; this.weight = weight; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/safari/fishing/SafariFishCatalog$WeightedDefinition;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #158	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/* 158 */       //   0	7	0	this	Lcom/atlas/common/fabric/safari/fishing/SafariFishCatalog$WeightedDefinition; } @NotNull public SafariFishDefinition definition() { return this.definition; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/safari/fishing/SafariFishCatalog$WeightedDefinition;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #158	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/safari/fishing/SafariFishCatalog$WeightedDefinition; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/safari/fishing/SafariFishCatalog$WeightedDefinition;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #158	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lcom/atlas/common/fabric/safari/fishing/SafariFishCatalog$WeightedDefinition;
/* 158 */       //   0	8	1	o	Ljava/lang/Object; } public double weight() { return this.weight; }
/*     */      }
/*     */ 
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\safari\fishing\SafariFishCatalog.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */