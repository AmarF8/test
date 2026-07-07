/*     */ package com.atlas.common.fabric.block.station.system;
/*     */ import com.atlas.common.fabric.block.station.system.requirement.AverageIVRequirement;
/*     */ import com.atlas.common.fabric.block.station.system.requirement.AverageLevelRequirement;
/*     */ import com.atlas.common.fabric.block.station.system.requirement.CountRequirement;
/*     */ import com.atlas.common.fabric.block.station.system.requirement.PokemonCriteria;
/*     */ import com.cobblemon.mod.common.api.types.ElementalType;
/*     */ import com.cobblemon.mod.common.api.types.ElementalTypes;
/*     */ import java.util.List;
/*     */ 
/*     */ public class StationRegistry {
/*  11 */   private static final Map<String, StationDefinition> STATIONS = new HashMap<>();
/*     */   
/*     */   public static void register(StationDefinition definition) {
/*  14 */     STATIONS.put(definition.getId(), definition);
/*     */   }
/*     */   
/*     */   public static StationDefinition get(String id) {
/*  18 */     return STATIONS.get(id);
/*     */   }
/*     */   
/*     */   public static Collection<StationDefinition> getAll() {
/*  22 */     return STATIONS.values();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/*  30 */     register(createTier1("hut", "Hut", "normal"));
/*  31 */     register(createTier1("crop_yard", "Crop Yard", "grass"));
/*  32 */     register(createTier1("fishing_pond", "Fishing Pond", "water"));
/*  33 */     register(createTier1("forge", "Forge", "fire"));
/*  34 */     register(createTier1("ore_mine", "Ore Mine", "rock"));
/*  35 */     register(createTier1("mob_arena", "Mob Arena", "fighting"));
/*  36 */     register(createTier1("flying_course", "Flying Course", "flying"));
/*  37 */     register(createTier1("tesla_coil", "Tesla Coil", "electric"));
/*  38 */     register(createTier1("igloo", "Igloo", "ice"));
/*  39 */     register(createTier1("mushroom_garden", "Mushroom Garden", "poison"));
/*  40 */     register(createTier1("digging_site", "Digging Site", "ground"));
/*  41 */     register(createTier1("zen_garden", "Zen Garden", "psychic"));
/*  42 */     register(createTier1("bug_nest", "Bug Nest", "bug"));
/*  43 */     register(createTier1("abandoned_fields", "Abandoned Fields", "ghost"));
/*  44 */     register(createTier1("roaring_mountains", "Roaring Mountains", "dragon"));
/*  45 */     register(createTier1("dark_ruins", "Dark Ruins", "dark"));
/*  46 */     register(createTier1("steel_machine", "Steel Machine", "steel"));
/*  47 */     register(createTier1("butterfly_fields", "Butterfly Fields", "fairy"));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  53 */     register(createTier2("village", "Village", "normal"));
/*  54 */     register(createTier2("crop_farm", "Crop Farm", "grass"));
/*  55 */     register(createTier2("fishing_lake", "Fishing Lake", "water"));
/*  56 */     register(createTier2("molten_forge", "Molten Forge", "fire"));
/*  57 */     register(createTier2("quarry", "Quarry", "rock"));
/*  58 */     register(createTier2("mob_stadium", "Mob Stadium", "fighting"));
/*  59 */     register(createTier2("gusty_hills", "Gusty Hills", "flying"));
/*  60 */     register(createTier2("electric_field", "Electric Field", "electric"));
/*  61 */     register(createTier2("glaciers", "Glaciers", "ice"));
/*  62 */     register(createTier2("mushroom_forest", "Mushroom Forest", "poison"));
/*  63 */     register(createTier2("crater", "Crater", "ground"));
/*  64 */     register(createTier2("tranquil_fields", "Tranquil Fields", "psychic"));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  71 */     register(createTier3("insect_hive", "Insect Hive", "bug"));
/*  72 */     register(createTier3("graveyard", "Graveyard", "ghost"));
/*  73 */     register(createTier3("dragon_temple", "Dragon Temple", "dragon"));
/*  74 */     register(createTier3("shadow_realm", "Shadow Realm", "dark"));
/*  75 */     register(createTier3("steampunk_factory", "Steampunk Factory", "steel"));
/*  76 */     register(createTier3("mystic_garden", "Mystic Garden", "fairy"));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  82 */     register(createTier4("battle_grounds", "Battle Grounds", "fighting"));
/*  83 */     register(createTier4("forest_temple", "Forest Temple", "grass"));
/*  84 */     register(createTier4("haunted_manor", "Haunted Manor", "ghost"));
/*  85 */     register(createTier4("ice_palace", "Ice Palace", "ice"));
/*  86 */     register(createTier4("poison_valley", "Poison Valley", "poison"));
/*  87 */     register(createTier4("thunderstorm_valley", "Thunderstorm Valley", "electric"));
/*  88 */     register(createTier4("volcano", "Volcano", "fire"));
/*  89 */     register(createTier4("wyvern_grounds", "Wyvern Grounds", "dragon"));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  95 */     register(createTier5("enchanted_forest", "Enchanted Forest", "fairy"));
/*     */   }
/*     */   
/*     */   private static StationDefinition createTier1(String id, String displayName, String typeName) {
/*  99 */     ElementalType type = ElementalTypes.get(typeName);
/* 100 */     PokemonCriteria criteria = (new PokemonCriteria.Builder()).types(new ElementalType[] { type }).build();
/* 101 */     List<StationRequirement> baseReqs = (List)List.of(new CountRequirement(criteria, 3));
/*     */     
/* 103 */     StationDefinition def = new StationDefinition(id, displayName, 1, baseReqs);
/*     */     
/* 105 */     def.addTier(new StationTier(1, 0.1D, List.of()));
/* 106 */     def.addTier(new StationTier(2, 0.2D, (List)List.of(new AverageLevelRequirement(20))));
/* 107 */     def.addTier(new StationTier(3, 0.3D, (List)List.of(new AverageLevelRequirement(35))));
/* 108 */     def.addTier(new StationTier(4, 0.4D, (List)List.of(new AverageLevelRequirement(50))));
/* 109 */     def.addTier(new StationTier(5, 0.5D, (List)List.of(new AverageIVRequirement(0.5D))));
/*     */     
/* 111 */     return def;
/*     */   }
/*     */   
/*     */   private static StationDefinition createTier2(String id, String displayName, String typeName) {
/* 115 */     ElementalType type = ElementalTypes.get(typeName);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 121 */     PokemonCriteria stage2Criteria = (new PokemonCriteria.Builder()).types(new ElementalType[] { type }).minEvolutionStage(2).build();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 127 */     PokemonCriteria bstCriteria = (new PokemonCriteria.Builder()).types(new ElementalType[] { type }).minBST(400).build();
/*     */     
/* 129 */     List<StationRequirement> baseReqs = (List)List.of(new CountRequirement(stage2Criteria, 5), new CountRequirement(bstCriteria, 3));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 134 */     StationDefinition def = new StationDefinition(id, displayName, 2, baseReqs);
/*     */     
/* 136 */     def.addTier(new StationTier(1, 0.5D, List.of()));
/* 137 */     def.addTier(new StationTier(2, 0.75D, (List)List.of(new AverageLevelRequirement(40))));
/* 138 */     def.addTier(new StationTier(3, 1.0D, (List)List.of(new AverageLevelRequirement(60))));
/* 139 */     def.addTier(new StationTier(4, 1.5D, (List)List.of(new AverageEVRequirement(300))));
/* 140 */     def.addTier(new StationTier(5, 2.0D, (List)List.of(new AverageIVRequirement(0.6D))));
/*     */     
/* 142 */     return def;
/*     */   }
/*     */   
/*     */   private static StationDefinition createTier3(String id, String displayName, String typeName) {
/* 146 */     ElementalType type = ElementalTypes.get(typeName);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 152 */     PokemonCriteria stage2Criteria = (new PokemonCriteria.Builder()).types(new ElementalType[] { type }).minEvolutionStage(2).build();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 157 */     PokemonCriteria bstCriteria = (new PokemonCriteria.Builder()).types(new ElementalType[] { type }).minBST(400).build();
/*     */     
/* 159 */     List<StationRequirement> baseReqs = (List)List.of(new CountRequirement(stage2Criteria, 7), new CountRequirement(bstCriteria, 5));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 164 */     StationDefinition def = new StationDefinition(id, displayName, 3, baseReqs);
/*     */     
/* 166 */     def.addTier(new StationTier(1, 1.5D, List.of()));
/* 167 */     def.addTier(new StationTier(2, 2.0D, (List)List.of(new AverageLevelRequirement(50))));
/* 168 */     def.addTier(new StationTier(3, 3.5D, (List)List.of(new AverageLevelRequirement(75))));
/* 169 */     def.addTier(new StationTier(4, 5.0D, (List)List.of(new AverageEVRequirement(510))));
/* 170 */     def.addTier(new StationTier(5, 6.5D, (List)List.of(new AverageIVRequirement(0.7D))));
/*     */     
/* 172 */     return def;
/*     */   }
/*     */   
/*     */   private static StationDefinition createTier4(String id, String displayName, String typeName) {
/* 176 */     ElementalType type = ElementalTypes.get(typeName);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 182 */     PokemonCriteria shinyCriteria = (new PokemonCriteria.Builder()).types(new ElementalType[] { type }).shiny().build();
/*     */     
/* 184 */     List<StationRequirement> baseReqs = (List)List.of(new CountRequirement(shinyCriteria, 4));
/*     */ 
/*     */ 
/*     */     
/* 188 */     StationDefinition def = new StationDefinition(id, displayName, 4, baseReqs);
/*     */     
/* 190 */     def.addTier(new StationTier(1, 5.0D, List.of()));
/* 191 */     def.addTier(new StationTier(2, 9.0D, (List)List.of(new AverageLevelRequirement(85))));
/* 192 */     def.addTier(new StationTier(3, 14.0D, (List)List.of(new AverageEVRequirement(510))));
/* 193 */     def.addTier(new StationTier(4, 19.0D, (List)List.of(new AverageSizeRequirement("XL", 1.45F))));
/* 194 */     def.addTier(new StationTier(5, 23.0D, (List)List.of(new AverageIVRequirement(0.75D))));
/*     */     
/* 196 */     return def;
/*     */   }
/*     */   
/*     */   private static StationDefinition createTier5(String id, String displayName, String typeName) {
/* 200 */     ElementalType fairyType = ElementalTypes.get("fairy");
/* 201 */     ElementalType psychicType = ElementalTypes.get("psychic");
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 206 */     PokemonCriteria legendaryCriteria = (new PokemonCriteria.Builder()).legendary().build();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 211 */     PokemonCriteria mythicalCriteria = (new PokemonCriteria.Builder()).mythical().build();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 217 */     PokemonCriteria shinyFairyCriteria = (new PokemonCriteria.Builder()).types(new ElementalType[] { fairyType }).shiny().build();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 223 */     PokemonCriteria shinyPsychicCriteria = (new PokemonCriteria.Builder()).types(new ElementalType[] { psychicType }).shiny().build();
/*     */     
/* 225 */     List<StationRequirement> baseReqs = (List)List.of(new CountRequirement(legendaryCriteria, 4), new CountRequirement(mythicalCriteria, 2), new CountRequirement(shinyFairyCriteria, 2), new CountRequirement(shinyPsychicCriteria, 2));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 232 */     StationDefinition def = new StationDefinition(id, displayName, 5, baseReqs);
/*     */     
/* 234 */     def.addTier(new StationTier(1, 17.0D, List.of()));
/* 235 */     def.addTier(new StationTier(2, 35.0D, (List)List.of(new AverageLevelRequirement(100))));
/* 236 */     def.addTier(new StationTier(3, 45.0D, (List)List.of(new AverageEVRequirement(510))));
/* 237 */     def.addTier(new StationTier(4, 58.0D, (List)List.of(new AverageSizeRequirement("XXL", 1.75F))));
/* 238 */     def.addTier(new StationTier(5, 70.0D, (List)List.of(new AverageIVRequirement(0.85D))));
/*     */     
/* 240 */     return def;
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\block\station\system\StationRegistry.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */