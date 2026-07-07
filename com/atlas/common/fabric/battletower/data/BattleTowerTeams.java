/*     */ package com.atlas.common.fabric.battletower.data;
/*     */ import com.atlas.common.fabric.AtlasMod;
/*     */ import com.atlas.common.fabric.battletower.battle.LegendaryChecker;
/*     */ import com.cobblemon.mod.common.api.types.tera.TeraType;
/*     */ import com.cobblemon.mod.common.pokemon.Pokemon;
/*     */ import com.gitlab.srcmc.rctapi.api.errors.RCTErrors;
/*     */ import com.gitlab.srcmc.rctapi.api.errors.RCTException;
/*     */ import com.gitlab.srcmc.rctapi.api.models.PokemonModel;
/*     */ import com.gitlab.srcmc.rctapi.api.models.converter.PokemonModelConverter;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.class_1799;
/*     */ 
/*     */ public class BattleTowerTeams {
/*  21 */   private static final Random RANDOM = new Random();
/*  22 */   private static final List<CompetitiveTeam> ALL_TEAMS = new ArrayList<>();
/*  23 */   private static final Map<Integer, List<CompetitiveTeam>> TEAMS_BY_FLOOR = new HashMap<>();
/*     */   
/*  25 */   public static final int[] PERFECT_IVS = new int[] { 31, 31, 31, 31, 31, 31 };
/*  26 */   public static final int[] PHYSICAL_IVS = new int[] { 31, 31, 31, 0, 31, 31 };
/*  27 */   public static final int[] SPECIAL_IVS = new int[] { 31, 0, 31, 31, 31, 31 };
/*  28 */   public static final int[] TRICK_ROOM_IVS = new int[] { 31, 31, 31, 31, 31, 0 };
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/*  33 */     initializeTeams();
/*  34 */     organizeTeamsByFloor();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static TeamResult getTeamForFloor(int floor, UUID playerUUID) {
/*  44 */     List<CompetitiveTeam> availableTeams = TEAMS_BY_FLOOR.get(Integer.valueOf(floor));
/*     */     
/*  46 */     if (availableTeams == null || availableTeams.isEmpty()) {
/*  47 */       AtlasMod.LOGGER.error("No teams available for floor {}", Integer.valueOf(floor));
/*  48 */       return new TeamResult(new Pokemon[0], "Unknown Trainer");
/*     */     } 
/*     */ 
/*     */     
/*  52 */     Set<Integer> usedTeamIds = TeamTracker.getUsedTeams(playerUUID);
/*  53 */     List<CompetitiveTeam> unusedTeams = new ArrayList<>();
/*     */     
/*  55 */     for (CompetitiveTeam team : availableTeams) {
/*  56 */       if (!usedTeamIds.contains(Integer.valueOf(team.id))) {
/*  57 */         unusedTeams.add(team);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/*  62 */     if (unusedTeams.isEmpty()) {
/*  63 */       AtlasMod.LOGGER.info("All teams used for player {}, allowing repeats", playerUUID);
/*  64 */       unusedTeams = availableTeams;
/*     */     } 
/*     */     
/*  67 */     CompetitiveTeam selectedTeam = unusedTeams.get(RANDOM.nextInt(unusedTeams.size()));
/*  68 */     TeamTracker.markTeamUsed(playerUUID, selectedTeam.id);
/*     */     
/*  70 */     return new TeamResult(createTeamFromConfig(selectedTeam), selectedTeam.name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static TeamResult getRandomTeamForFloor(int floor, UUID playerUUID, boolean allowLegendaries) {
/*  79 */     List<CompetitiveTeam> availableTeams = TEAMS_BY_FLOOR.get(Integer.valueOf(floor));
/*     */     
/*  81 */     if (availableTeams == null || availableTeams.isEmpty()) {
/*  82 */       AtlasMod.LOGGER.error("No teams available for floor {}", Integer.valueOf(floor));
/*  83 */       return new TeamResult(new Pokemon[0], "Unknown Trainer");
/*     */     } 
/*     */ 
/*     */     
/*  87 */     List<CompetitiveTeam.PokemonConfig> pool = new ArrayList<>();
/*  88 */     for (CompetitiveTeam team : availableTeams) {
/*  89 */       pool.addAll(Arrays.asList(team.pokemon));
/*     */     }
/*     */     
/*  92 */     if (pool.isEmpty()) {
/*  93 */       AtlasMod.LOGGER.error("No Pokemon configs available for floor {}", Integer.valueOf(floor));
/*  94 */       return new TeamResult(new Pokemon[0], "Unknown Trainer");
/*     */     } 
/*     */ 
/*     */     
/*  98 */     List<CompetitiveTeam.PokemonConfig> shuffledPool = new ArrayList<>(pool);
/*  99 */     Collections.shuffle(shuffledPool, RANDOM);
/*     */     
/* 101 */     List<Pokemon> pokemonList = new ArrayList<>();
/* 102 */     List<String> selectedSpecies = new ArrayList<>();
/* 103 */     Set<String> usedSpecies = new HashSet<>();
/* 104 */     boolean hasRestricted = false;
/*     */     
/* 106 */     for (CompetitiveTeam.PokemonConfig config : shuffledPool) {
/* 107 */       if (pokemonList.size() >= 6)
/* 108 */         break;  if (usedSpecies.contains(config.species))
/*     */         continue; 
/* 110 */       Pokemon pokemon = createPokemon(config);
/* 111 */       if (pokemon == null)
/*     */         continue; 
/* 113 */       boolean restricted = LegendaryChecker.isRestricted(pokemon);
/*     */ 
/*     */       
/* 116 */       if (floor < 30 && restricted)
/*     */         continue; 
/* 118 */       usedSpecies.add(config.species);
/* 119 */       selectedSpecies.add(config.species);
/* 120 */       pokemonList.add(pokemon);
/* 121 */       if (restricted) hasRestricted = true;
/*     */     
/*     */     } 
/* 124 */     if (pokemonList.isEmpty()) {
/* 125 */       AtlasMod.LOGGER.error("Failed to build random team for player {} on floor {}", playerUUID, Integer.valueOf(floor));
/* 126 */       return new TeamResult(new Pokemon[0], "Unknown Trainer");
/*     */     } 
/*     */     
/* 129 */     if (pokemonList.size() < 6) {
/* 130 */       AtlasMod.LOGGER.warn("Random team for player {} on floor {} has only {} unique species", new Object[] { playerUUID, 
/*     */             
/* 132 */             Integer.valueOf(floor), Integer.valueOf(pokemonList.size()) });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 137 */     if (floor >= 30 && !hasRestricted) {
/* 138 */       hasRestricted = tryInjectRestricted(shuffledPool, usedSpecies, selectedSpecies, pokemonList);
/* 139 */       if (!hasRestricted) {
/* 140 */         AtlasMod.LOGGER.warn("No restricted Pokemon available to include on floor {} (player {})", 
/* 141 */             Integer.valueOf(floor), playerUUID);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 146 */     String trainerName = ((CompetitiveTeam)availableTeams.get(RANDOM.nextInt(availableTeams.size()))).name;
/* 147 */     return new TeamResult(pokemonList.<Pokemon>toArray(new Pokemon[0]), trainerName);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static Pokemon[] createTeamFromConfig(CompetitiveTeam team) {
/* 153 */     List<Pokemon> pokemonList = new ArrayList<>();
/*     */     
/* 155 */     for (CompetitiveTeam.PokemonConfig config : team.pokemon) {
/* 156 */       Pokemon pokemon = createPokemon(config);
/* 157 */       if (pokemon != null) {
/* 158 */         pokemonList.add(pokemon);
/*     */       }
/*     */     } 
/*     */     
/* 162 */     return pokemonList.<Pokemon>toArray(new Pokemon[0]);
/*     */   }
/*     */ 
/*     */   
/*     */   private static Pokemon createPokemon(CompetitiveTeam.PokemonConfig config) {
/* 167 */     return createPokemon(config.species, 50, config.nature, config.ability, config.ivs, config.evs, config.moves, config.heldItem, config.teraType, config.gmaxCapable);
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
/*     */   private static Pokemon createPokemon(String species, int level, String nature, String ability, int[] ivs, int[] evs, String[] moves, String heldItem, String teraType, boolean gmaxCapable) {
/*     */     try {
/* 180 */       String baseSpecies = species;
/* 181 */       Set<String> aspects = new HashSet<>();
/*     */       
/* 183 */       resolveSpeciesAndAspects(species, heldItem, baseSpecies, aspects);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 188 */       baseSpecies = resolveBaseSpecies(species, heldItem);
/* 189 */       resolveAspects(species, heldItem, aspects);
/*     */ 
/*     */       
/* 192 */       PokemonModel.StatsModel ivsModel = new PokemonModel.StatsModel(ivs[0], ivs[1], ivs[2], ivs[3], ivs[4], ivs[5]);
/* 193 */       PokemonModel.StatsModel evsModel = new PokemonModel.StatsModel(evs[0], evs[1], evs[2], evs[3], evs[4], evs[5]);
/*     */       
/* 195 */       Set<String> moveSet = new HashSet<>(Arrays.asList(moves));
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 200 */       PokemonModel model = new PokemonModel(baseSpecies, "GENDERLESS", level, nature, ability, moveSet, ivsModel, evsModel, false, (heldItem != null) ? heldItem : "", aspects);
/*     */ 
/*     */ 
/*     */       
/* 204 */       PokemonModelConverter converter = new PokemonModelConverter();
/* 205 */       RCTErrors<RCTException> errors = RCTErrors.create();
/* 206 */       Pokemon pokemon = converter.toTarget(model, errors);
/*     */       
/* 208 */       if (pokemon == null) {
/* 209 */         AtlasMod.LOGGER.error("Converter returned null for Pokemon: {} (base: {}, aspects: {})", new Object[] { species, baseSpecies, aspects });
/*     */ 
/*     */ 
/*     */         
/* 213 */         return null;
/*     */       } 
/*     */ 
/*     */       
/* 217 */       class_1799 heldItemStack = pokemon.heldItem();
/* 218 */       if (heldItemStack.method_7960()) {
/* 219 */         AtlasMod.LOGGER.warn("No held item set for {} (expected: {})", species, heldItem);
/*     */       }
/*     */       
/* 222 */       if (!aspects.isEmpty()) pokemon.updateAspects(); 
/* 223 */       if (gmaxCapable) pokemon.setGmaxFactor(true);
/*     */       
/* 225 */       applyTeraType(pokemon, species, teraType);
/*     */ 
/*     */       
/* 228 */       if (baseSpecies.equals("arceus") && heldItem != null && heldItem.contains("_plate")) {
/* 229 */         pokemon.updateAspects();
/*     */       }
/*     */       
/* 232 */       pokemon.heal();
/* 233 */       pokemon.updateAspects();
/* 234 */       return pokemon;
/*     */     }
/* 236 */     catch (Exception e) {
/* 237 */       AtlasMod.LOGGER.error("Failed to create Pokemon {}: {}", species, e.getMessage());
/* 238 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String resolveBaseSpecies(String species, String heldItem) {
/* 249 */     if (species.startsWith("tapu_")) return species.replace("_", ""); 
/* 250 */     if (species.equals("ho_oh")) return "hooh"; 
/* 251 */     if (species.equals("shaymin_sky")) return "shaymin"; 
/* 252 */     if (species.equals("mega_rayquaza")) return "rayquaza"; 
/* 253 */     if (species.equals("zacian_crowned")) return "zacian"; 
/* 254 */     if (species.equals("zamazenta_crowned")) return "zamazenta"; 
/* 255 */     if (species.equals("ursaluna_bloodmoon")) return "ursaluna"; 
/* 256 */     if (species.startsWith("rotom_")) return "rotom"; 
/* 257 */     if (species.startsWith("necrozma_")) return "necrozma"; 
/* 258 */     if (species.startsWith("kyurem_")) return "kyurem"; 
/* 259 */     if (species.startsWith("calyrex_")) return "calyrex"; 
/* 260 */     if (species.startsWith("urshifu_")) return "urshifu"; 
/* 261 */     if (species.startsWith("ogerpon_")) return "ogerpon";
/*     */ 
/*     */     
/* 264 */     for (String suffix : List.<String>of("_galar", "_alola", "_hisui", "_paldea", "_therian", "_incarnate")) {
/* 265 */       if (species.contains(suffix)) return species.replace(suffix, "");
/*     */     
/*     */     } 
/* 268 */     return species;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void resolveAspects(String species, String heldItem, Set<String> aspects) {
/* 277 */     if (species.equals("shaymin_sky")) { aspects.add("sky"); }
/* 278 */     else if (species.equals("mega_rayquaza")) { aspects.add("mega"); }
/* 279 */     else if (species.equals("zacian_crowned")) { aspects.add("crowned"); }
/* 280 */     else if (species.equals("zamazenta_crowned")) { aspects.add("crowned"); }
/* 281 */     else if (species.equals("ursaluna_bloodmoon")) { aspects.add("bloodmoon");
/*     */        }
/*     */     
/* 284 */     else if (species.equals("groudon") && itemContains(heldItem, "red_orb")) { aspects.add("primal"); }
/* 285 */     else if (species.equals("kyogre") && itemContains(heldItem, "blue_orb")) { aspects.add("primal"); }
/* 286 */     else if (species.equals("dialga") && itemContains(heldItem, "adamant_crystal")) { aspects.add("origin-forme"); }
/* 287 */     else if (species.equals("palkia") && itemContains(heldItem, "lustrous_globe")) { aspects.add("origin-forme"); }
/* 288 */     else if (species.equals("giratina") && itemContains(heldItem, "griseous_core")) { aspects.add("origin-forme"); }
/* 289 */     else if (species.equals("eternatus") && itemContains(heldItem, "star_core")) { aspects.add("eternamax");
/*     */        }
/*     */     
/* 292 */     else if (species.startsWith("rotom_"))
/* 293 */     { String formName = species.substring("rotom_".length());
/* 294 */       aspects.add(formName + "-appliance");
/*     */       
/*     */        }
/*     */     
/* 298 */     else if (species.startsWith("necrozma_"))
/* 299 */     { if (species.contains("dusk_mane")) { aspects.add("dusk-fusion"); }
/* 300 */       else if (species.contains("dawn_wings")) { aspects.add("dawn-fusion"); }
/* 301 */       else if (species.contains("ultra")) { aspects.add("ultra-fusion"); }
/*     */       
/*     */        }
/*     */     
/* 305 */     else if (species.startsWith("kyurem_"))
/* 306 */     { if (species.contains("black")) { aspects.add("black-fusion"); }
/* 307 */       else if (species.contains("white")) { aspects.add("white-fusion"); }
/*     */       
/*     */        }
/*     */     
/* 311 */     else if (species.startsWith("calyrex_"))
/* 312 */     { if (species.contains("ice")) { aspects.add("ice-rider"); }
/* 313 */       else if (species.contains("shadow")) { aspects.add("shadow-rider"); }
/*     */       
/*     */        }
/*     */     
/* 317 */     else if (species.startsWith("urshifu_"))
/* 318 */     { if (species.contains("rapid")) { aspects.add("rapid_strike-style"); }
/* 319 */       else if (species.contains("single")) { aspects.add("single_strike-style"); }
/*     */       
/*     */        }
/*     */     
/* 323 */     else if (species.startsWith("ogerpon_"))
/* 324 */     { if (species.contains("wellspring")) { aspects.add("wellspring-mask"); }
/* 325 */       else if (species.contains("hearthflame")) { aspects.add("hearthflame-mask"); }
/* 326 */       else if (species.contains("cornerstone")) { aspects.add("cornerstone-mask"); }
/*     */       
/*     */        }
/*     */     
/* 330 */     else if (species.contains("_galar")) { aspects.add("galarian"); }
/* 331 */     else if (species.contains("_alola")) { aspects.add("alolan"); }
/* 332 */     else if (species.contains("_hisui")) { aspects.add("hisuian"); }
/* 333 */     else if (species.contains("_paldea")) { aspects.add("paldean"); }
/* 334 */     else if (species.contains("_therian")) { aspects.add("therian-forme"); }
/* 335 */     else if (species.contains("_incarnate")) { aspects.add("incarnate-forme"); }
/*     */   
/*     */   }
/*     */   
/*     */   private static boolean itemContains(String heldItem, String substring) {
/* 340 */     return (heldItem != null && heldItem.contains(substring));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void resolveSpeciesAndAspects(String species, String heldItem, String baseSpecies, Set<String> aspects) {}
/*     */ 
/*     */ 
/*     */   
/*     */   private static void applyTeraType(Pokemon pokemon, String species, String teraType) {
/* 351 */     if (teraType == null || teraType.isEmpty())
/*     */       return; 
/*     */     try {
/* 354 */       TeraType teraTypeObj = TeraTypes.get(teraType);
/* 355 */       if (teraTypeObj != null) {
/* 356 */         pokemon.setTeraType(teraTypeObj);
/*     */       } else {
/* 358 */         AtlasMod.LOGGER.warn("Unknown tera type '{}' for Pokemon {}", teraType, species);
/*     */       } 
/* 360 */     } catch (Exception e) {
/* 361 */       AtlasMod.LOGGER.error("Failed to set tera type '{}' for Pokemon {}: {}", new Object[] { teraType, species, e.getMessage() });
/*     */     } 
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
/*     */   private static boolean tryInjectRestricted(List<CompetitiveTeam.PokemonConfig> shuffledPool, Set<String> usedSpecies, List<String> selectedSpecies, List<Pokemon> pokemonList) {
/* 378 */     for (CompetitiveTeam.PokemonConfig config : shuffledPool) {
/* 379 */       if (usedSpecies.contains(config.species))
/*     */         continue; 
/* 381 */       Pokemon pokemon = createPokemon(config);
/* 382 */       if (pokemon == null || !LegendaryChecker.isRestricted(pokemon)) {
/*     */         continue;
/*     */       }
/* 385 */       int replaceIndex = -1;
/* 386 */       for (int i = 0; i < pokemonList.size(); i++) {
/* 387 */         if (!LegendaryChecker.isRestricted(pokemonList.get(i))) {
/* 388 */           replaceIndex = i;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/* 393 */       if (replaceIndex >= 0) {
/* 394 */         usedSpecies.remove(selectedSpecies.get(replaceIndex));
/* 395 */         pokemonList.set(replaceIndex, pokemon);
/* 396 */         selectedSpecies.set(replaceIndex, config.species);
/* 397 */         usedSpecies.add(config.species);
/* 398 */       } else if (pokemonList.size() < 6) {
/* 399 */         usedSpecies.add(config.species);
/* 400 */         selectedSpecies.add(config.species);
/* 401 */         pokemonList.add(pokemon);
/*     */       } 
/*     */       
/* 404 */       return true;
/*     */     } 
/*     */     
/* 407 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void initializeTeams() {
/* 413 */     addFloor1to5Teams();
/* 414 */     addFloor5to10Teams();
/* 415 */     addFloor10to15Teams();
/* 416 */     BattleTowerTeamsPart2.addFloor15to30Teams(ALL_TEAMS);
/* 417 */     BattleTowerTeamsPart3.addFloor30PlusTeams(ALL_TEAMS);
/* 418 */     AtlasMod.LOGGER.info("Initialized {} competitive teams", Integer.valueOf(ALL_TEAMS.size()));
/*     */   }
/*     */   
/*     */   private static void organizeTeamsByFloor() {
/* 422 */     for (CompetitiveTeam team : ALL_TEAMS) {
/* 423 */       for (int floor = team.minFloor; floor <= team.maxFloor; floor++) {
/* 424 */         ((List<CompetitiveTeam>)TEAMS_BY_FLOOR.computeIfAbsent(Integer.valueOf(floor), k -> new ArrayList())).add(team);
/*     */       }
/*     */     } 
/* 427 */     AtlasMod.LOGGER.info("Organized {} teams across {} floors", Integer.valueOf(ALL_TEAMS.size()), Integer.valueOf(TEAMS_BY_FLOOR.size()));
/*     */   }
/*     */   
/*     */   private static void addFloor1to5Teams() {
/* 431 */     ALL_TEAMS.add(new CompetitiveTeam(1, "Youngster Joey", 1, 5, new CompetitiveTeam.PokemonConfig[] { new CompetitiveTeam.PokemonConfig("raticate", "jolly", "guts", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "facade", "suckerpunch", "uturn", "stompingtantrum" }, "cobblemon:flame_orb"), new CompetitiveTeam.PokemonConfig("pidgeot", "timid", "keeneye", SPECIAL_IVS, new int[] { 0, 0, 0, 252, 4, 252 }, new String[] { "hyperbeam", "heatwave", "uturn", "roost" }, "cobblemon:wide_lens"), new CompetitiveTeam.PokemonConfig("diggersby", "adamant", "hugepower", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "bodyslam", "earthquake", "quickattack", "icepunch" }, "cobblemon:choice_band"), new CompetitiveTeam.PokemonConfig("zangoose", "jolly", "toxicboost", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "facade", "closecombat", "protect", "quickattack" }, "cobblemon:toxic_orb"), new CompetitiveTeam.PokemonConfig("tauros", "jolly", "intimidate", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "bodyslam", "earthquake", "zenheadbutt", "closecombat" }, "cobblemon:choice_scarf"), new CompetitiveTeam.PokemonConfig("porygon2", "modest", "download", SPECIAL_IVS, new int[] { 252, 0, 4, 252, 0, 0 }, new String[] { "triattack", "reflect", "recover", "confuseray" }, "cobblemon:eviolite") }false));
/* 432 */     ALL_TEAMS.add(new CompetitiveTeam(2, "Lass Sally", 1, 5, new CompetitiveTeam.PokemonConfig[] { new CompetitiveTeam.PokemonConfig("wigglytuff", "bold", "competitive", SPECIAL_IVS, new int[] { 252, 0, 252, 0, 4, 0 }, new String[] { "dazzlinggleam", "thunderbolt", "fireblast", "stealthrock" }, "cobblemon:babiri_berry"), new CompetitiveTeam.PokemonConfig("persian", "jolly", "technician", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "fakeout", "uturn", "knockoff", "playrough" }, "cobblemon:silk_scarf"), new CompetitiveTeam.PokemonConfig("azumarill", "adamant", "hugepower", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "liquidation", "playrough", "aquajet", "knockoff" }, "cobblemon:choice_band"), new CompetitiveTeam.PokemonConfig("granbull", "adamant", "intimidate", PHYSICAL_IVS, new int[] { 252, 252, 0, 0, 4, 0 }, new String[] { "playrough", "earthquake", "thunderwave", "healbell" }, "cobblemon:eject_button"), new CompetitiveTeam.PokemonConfig("cinccino", "jolly", "skilllink", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "tailslap", "bulletseed", "rockblast", "uturn" }, "cobblemon:kings_rock"), new CompetitiveTeam.PokemonConfig("alomomola", "bold", "regenerator", SPECIAL_IVS, new int[] { 252, 0, 252, 0, 4, 0 }, new String[] { "scald", "wish", "protect", "toxic" }, "cobblemon:damp_rock") }false));
/* 433 */     ALL_TEAMS.add(new CompetitiveTeam(3, "Bug Catcher", 1, 5, new CompetitiveTeam.PokemonConfig[] { new CompetitiveTeam.PokemonConfig("butterfree", "timid", "compoundeyes", SPECIAL_IVS, new int[] { 0, 0, 0, 252, 4, 252 }, new String[] { "sleeppowder", "quiverdance", "bugbuzz", "hurricane" }, "cobblemon:focus_sash", (String)null, true), new CompetitiveTeam.PokemonConfig("galvantula", "timid", "compoundeyes", SPECIAL_IVS, new int[] { 0, 0, 0, 252, 4, 252 }, new String[] { "thunder", "bugbuzz", "energyball", "voltswitch" }, "cobblemon:wise_glasses"), new CompetitiveTeam.PokemonConfig("scizor", "adamant", "technician", PHYSICAL_IVS, new int[] { 252, 252, 0, 0, 4, 0 }, new String[] { "bulletpunch", "protect", "roost", "knockoff" }, "cobblemon:occa_berry"), new CompetitiveTeam.PokemonConfig("heracross", "jolly", "moxie", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "closecombat", "megahorn", "stoneedge", "earthquake" }, "cobblemon:choice_scarf"), new CompetitiveTeam.PokemonConfig("araquanid", "adamant", "waterbubble", PHYSICAL_IVS, new int[] { 252, 252, 0, 0, 4, 0 }, new String[] { "liquidation", "leechlife", "aquajet", "poisonjab" }, "cobblemon:assault_vest"), new CompetitiveTeam.PokemonConfig("frosmoth", "timid", "icescales", SPECIAL_IVS, new int[] { 0, 0, 0, 252, 4, 252 }, new String[] { "icebeam", "bugbuzz", "gigadrain", "quiverdance" }, "cobblemon:bright_powder") }false));
/* 434 */     ALL_TEAMS.add(new CompetitiveTeam(4, "Swimmer", 1, 5, new CompetitiveTeam.PokemonConfig[] { new CompetitiveTeam.PokemonConfig("blastoise", "modest", "torrent", SPECIAL_IVS, new int[] { 0, 0, 4, 252, 0, 252 }, new String[] { "shellsmash", "hydropump", "icebeam", "raindance" }, "cobblemon:white_herb", (String)null, true), new CompetitiveTeam.PokemonConfig("gastrodon", "bold", "stormdrain", SPECIAL_IVS, new int[] { 252, 0, 252, 0, 4, 0 }, new String[] { "earthpower", "scald", "recover", "toxic" }, "cobblemon:rindo_berry"), new CompetitiveTeam.PokemonConfig("gyarados", "jolly", "intimidate", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "waterfall", "earthquake", "icefang", "dragondance" }, "cobblemon:lum_berry"), new CompetitiveTeam.PokemonConfig("vaporeon", "bold", "hydration", SPECIAL_IVS, new int[] { 4, 0, 252, 0, 252, 0 }, new String[] { "scald", "wish", "protect", "toxic" }, "cobblemon:kee_berry"), new CompetitiveTeam.PokemonConfig("starmie", "timid", "naturalcure", SPECIAL_IVS, new int[] { 0, 0, 0, 252, 4, 252 }, new String[] { "hydropump", "psyshock", "icebeam", "rapidspin" }, "cobblemon:wise_glasses"), new CompetitiveTeam.PokemonConfig("empoleon", "calm", "competitive", SPECIAL_IVS, new int[] { 252, 0, 4, 0, 252, 0 }, new String[] { "stealthrock", "surf", "flashcannon", "roost" }, "cobblemon:shuca_berry") }false));
/* 435 */     ALL_TEAMS.add(new CompetitiveTeam(5, "Camper", 1, 5, new CompetitiveTeam.PokemonConfig[] { new CompetitiveTeam.PokemonConfig("charizard", "timid", "solarpower", SPECIAL_IVS, new int[] { 0, 0, 0, 252, 4, 252 }, new String[] { "fireblast", "airslash", "solarbeam", "roost" }, "cobblemon:power_herb", (String)null, true), new CompetitiveTeam.PokemonConfig("arcanine", "jolly", "intimidate", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "flareblitz", "extremespeed", "closecombat", "morningsun" }, "cobblemon:white_herb"), new CompetitiveTeam.PokemonConfig("lilligant_hisui", "jolly", "chlorophyll", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "victorydance", "closecombat", "solarblade", "icespinner" }, "cobblemon:muscle_band"), new CompetitiveTeam.PokemonConfig("incineroar", "adamant", "intimidate", PHYSICAL_IVS, new int[] { 252, 252, 0, 0, 4, 0 }, new String[] { "flareblitz", "knockoff", "fakeout", "partingshot" }, "cobblemon:sitrus_berry"), new CompetitiveTeam.PokemonConfig("typhlosion_hisui", "timid", "frisk", SPECIAL_IVS, new int[] { 0, 0, 0, 252, 4, 252 }, new String[] { "eruption", "shadowball", "infernalparade", "focusblast" }, "cobblemon:choice_scarf"), new CompetitiveTeam.PokemonConfig("torkoal", "bold", "drought", SPECIAL_IVS, new int[] { 248, 0, 252, 0, 8, 0 }, new String[] { "stealthrock", "lavaplume", "yawn", "bodypress" }, "cobblemon:heat_rock") }false));
/* 436 */     ALL_TEAMS.add(new CompetitiveTeam(6, "Picnicker", 1, 5, new CompetitiveTeam.PokemonConfig[] { new CompetitiveTeam.PokemonConfig("serperior", "timid", "contrary", SPECIAL_IVS, new int[] { 0, 0, 0, 252, 4, 252 }, new String[] { "leafstorm", "substitute", "glare", "gigadrain" }, "cobblemon:wide_lens", (String)null, true), new CompetitiveTeam.PokemonConfig("amoonguss", "bold", "regenerator", SPECIAL_IVS, new int[] { 252, 0, 252, 0, 4, 0 }, new String[] { "gigadrain", "sludgebomb", "spore", "synthesis" }, "cobblemon:black_sludge"), new CompetitiveTeam.PokemonConfig("scovillain", "timid", "moody", SPECIAL_IVS, new int[] { 0, 0, 0, 252, 4, 252 }, new String[] { "leafstorm", "flamethrower", "sludgebomb", "overheat" }, "cobblemon:choice_scarf"), new CompetitiveTeam.PokemonConfig("dipplin", "calm", "stickyhold", SPECIAL_IVS, new int[] { 252, 0, 4, 0, 252, 0 }, new String[] { "gigadrain", "dragonpulse", "recover", "dragontail" }, "cobblemon:eviolite"), new CompetitiveTeam.PokemonConfig("tangrowth", "relaxed", "regenerator", PHYSICAL_IVS, new int[] { 252, 0, 252, 0, 4, 0 }, new String[] { "gigadrain", "knockoff", "earthquake", "sleeppowder" }, "cobblemon:jaboca_berry"), new CompetitiveTeam.PokemonConfig("ferrothorn", "relaxed", "ironbarbs", TRICK_ROOM_IVS, new int[] { 252, 0, 252, 0, 4, 0 }, new String[] { "ingrain", "gyroball", "stealthrock", "leechseed" }, "cobblemon:big_root") }false));
/* 437 */     ALL_TEAMS.add(new CompetitiveTeam(7, "Engineer", 1, 5, new CompetitiveTeam.PokemonConfig[] { new CompetitiveTeam.PokemonConfig("raichu", "timid", "lightningrod", SPECIAL_IVS, new int[] { 0, 0, 0, 252, 4, 252 }, new String[] { "thunderbolt", "grassknot", "nastyplot", "surf" }, "cobblemon:magnet"), new CompetitiveTeam.PokemonConfig("magneton", "quiet", "analytic", TRICK_ROOM_IVS, new int[] { 252, 0, 0, 252, 4, 0 }, new String[] { "thunderbolt", "terablast", "steelbeam", "lightscreen" }, "cobblemon:eviolite"), new CompetitiveTeam.PokemonConfig("jolteon", "timid", "voltabsorb", SPECIAL_IVS, new int[] { 0, 0, 0, 252, 4, 252 }, new String[] { "thunderbolt", "voltswitch", "shadowball", "hypervoice" }, "cobblemon:choice_specs"), new CompetitiveTeam.PokemonConfig("kilowattrel", "timid", "voltabsorb", SPECIAL_IVS, new int[] { 0, 0, 0, 252, 4, 252 }, new String[] { "thunderbolt", "hurricane", "voltswitch", "roost" }, "cobblemon:safety_goggles"), new CompetitiveTeam.PokemonConfig("rotom_wash", "bold", "levitate", SPECIAL_IVS, new int[] { 252, 0, 252, 0, 4, 0 }, new String[] { "hydropump", "voltswitch", "willowisp", "painsplit" }, "cobblemon:sitrus_berry"), new CompetitiveTeam.PokemonConfig("ampharos", "modest", "static", SPECIAL_IVS, new int[] { 252, 0, 0, 252, 4, 0 }, new String[] { "thunderbolt", "voltswitch", "focusblast", "dragonpulse" }, "cobblemon:assault_vest") }false));
/* 438 */     ALL_TEAMS.add(new CompetitiveTeam(8, "Black Belt", 1, 5, new CompetitiveTeam.PokemonConfig[] { new CompetitiveTeam.PokemonConfig("machamp", "adamant", "noguard", PHYSICAL_IVS, new int[] { 252, 252, 0, 0, 4, 0 }, new String[] { "dynamicpunch", "stoneedge", "knockoff", "drainpunch" }, "cobblemon:assault_vest", (String)null, true), new CompetitiveTeam.PokemonConfig("annihilape", "adamant", "defiant", PHYSICAL_IVS, new int[] { 252, 252, 4, 0, 0, 0 }, new String[] { "ragefist", "bulkup", "drainpunch", "taunt" }, "cobblemon:punching_glove"), new CompetitiveTeam.PokemonConfig("lucario", "modest", "innerfocus", SPECIAL_IVS, new int[] { 0, 0, 0, 252, 4, 252 }, new String[] { "thunderbolt", "darkpulse", "flashcannon", "aurasphere" }, "cobblemon:choice_scarf"), new CompetitiveTeam.PokemonConfig("conkeldurr", "adamant", "guts", PHYSICAL_IVS, new int[] { 252, 252, 4, 0, 0, 0 }, new String[] { "drainpunch", "machpunch", "protect", "icepunch" }, "cobblemon:flame_orb"), new CompetitiveTeam.PokemonConfig("sneasler", "adamant", "unburden", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "fakeout", "closecombat", "direclaw", "acrobatics" }, "cobblemon:normal_gem"), new CompetitiveTeam.PokemonConfig("quaquaval", "jolly", "moxie", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "aquastep", "closecombat", "aquajet", "icespinner" }, "cobblemon:mystic_water") }false));
/* 439 */     ALL_TEAMS.add(new CompetitiveTeam(9, "Psychic", 1, 5, new CompetitiveTeam.PokemonConfig[] { new CompetitiveTeam.PokemonConfig("malamar", "adamant", "contrary", PHYSICAL_IVS, new int[] { 128, 252, 128, 0, 0, 0 }, new String[] { "topsyturvy", "knockoff", "psychocut", "superpower" }, "cobblemon:focus_sash"), new CompetitiveTeam.PokemonConfig("mrrime", "modest", "screencleaner", SPECIAL_IVS, new int[] { 252, 0, 0, 252, 4, 0 }, new String[] { "freezedry", "psychic", "dazzlinggleam", "stealthrock" }, "cobblemon:colbur_berry"), new CompetitiveTeam.PokemonConfig("hatterene", "quiet", "magicbounce", TRICK_ROOM_IVS, new int[] { 252, 0, 0, 252, 4, 0 }, new String[] { "psychic", "drainingkiss", "trickroom", "calmmind" }, "cobblemon:sitrus_berry"), new CompetitiveTeam.PokemonConfig("slowbro_galar", "bold", "regenerator", SPECIAL_IVS, new int[] { 252, 0, 252, 0, 4, 0 }, new String[] { "shellsidearm", "psychic", "slackoff", "toxic" }, "cobblemon:quick_claw"), new CompetitiveTeam.PokemonConfig("reuniclus", "quiet", "magicguard", TRICK_ROOM_IVS, new int[] { 252, 0, 0, 252, 4, 0 }, new String[] { "psychicnoise", "recover", "shadowball", "trickroom" }, "cobblemon:wiki_berry"), new CompetitiveTeam.PokemonConfig("metagross", "adamant", "clearbody", PHYSICAL_IVS, new int[] { 252, 252, 4, 0, 0, 0 }, new String[] { "icepunch", "bulletpunch", "earthquake", "knockoff" }, "cobblemon:muscle_band") }false));
/* 440 */     ALL_TEAMS.add(new CompetitiveTeam(10, "Hiker", 1, 5, new CompetitiveTeam.PokemonConfig[] { new CompetitiveTeam.PokemonConfig("golem", "adamant", "sturdy", PHYSICAL_IVS, new int[] { 252, 252, 0, 0, 4, 0 }, new String[] { "stoneedge", "earthquake", "explosion", "stealthrock" }, "cobblemon:custap_berry"), new CompetitiveTeam.PokemonConfig("omastar", "modest", "swiftswim", SPECIAL_IVS, new int[] { 0, 0, 0, 252, 4, 252 }, new String[] { "hydropump", "icebeam", "earthpower", "shellsmash" }, "cobblemon:white_herb"), new CompetitiveTeam.PokemonConfig("tyranitar", "adamant", "sandstream", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "stoneedge", "crunch", "earthquake", "dragondance" }, "cobblemon:lum_berry"), new CompetitiveTeam.PokemonConfig("aerodactyl", "jolly", "unnerve", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "stoneedge", "earthquake", "aerialace", "dualwingbeat" }, "cobblemon:normal_gem"), new CompetitiveTeam.PokemonConfig("rhyperior", "adamant", "solidrock", PHYSICAL_IVS, new int[] { 252, 252, 0, 0, 4, 0 }, new String[] { "stoneedge", "earthquake", "megahorn", "rockpolish" }, "cobblemon:weakness_policy"), new CompetitiveTeam.PokemonConfig("cradily", "careful", "stormdrain", SPECIAL_IVS, new int[] { 252, 0, 4, 0, 252, 0 }, new String[] { "recover", "stealthrock", "toxic", "rockslide" }, "cobblemon:big_root") }false));
/* 441 */     ALL_TEAMS.add(new CompetitiveTeam(11, "Hex Maniac", 1, 5, new CompetitiveTeam.PokemonConfig[] { new CompetitiveTeam.PokemonConfig("gengar", "timid", "cursedbody", SPECIAL_IVS, new int[] { 0, 0, 0, 252, 4, 252 }, new String[] { "shadowball", "sludgebomb", "focusblast", "nastyplot" }, "cobblemon:spell_tag"), new CompetitiveTeam.PokemonConfig("mismagius", "timid", "levitate", SPECIAL_IVS, new int[] { 0, 0, 0, 252, 4, 252 }, new String[] { "shadowball", "mysticalfire", "dazzlinggleam", "nastyplot" }, "cobblemon:colbur_berry"), new CompetitiveTeam.PokemonConfig("drifblim", "modest", "unburden", SPECIAL_IVS, new int[] { 252, 0, 4, 252, 0, 0 }, new String[] { "shadowball", "airslash", "calmmind", "substitute" }, "cobblemon:sitrus_berry"), new CompetitiveTeam.PokemonConfig("spiritomb", "careful", "infiltrator", PHYSICAL_IVS, new int[] { 252, 0, 4, 0, 252, 0 }, new String[] { "suckerpunch", "willowisp", "rest", "sleeptalk" }, "cobblemon:chesto_berry"), new CompetitiveTeam.PokemonConfig("banette", "jolly", "cursedbody", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "poltergeist", "shadowclaw", "willowisp", "destinybond" }, "cobblemon:kasib_berry"), new CompetitiveTeam.PokemonConfig("dusclops", "relaxed", "pressure", TRICK_ROOM_IVS, new int[] { 252, 0, 252, 0, 4, 0 }, new String[] { "nightshade", "willowisp", "painsplit", "trickroom" }, "cobblemon:eviolite") }false));
/* 442 */     ALL_TEAMS.add(new CompetitiveTeam(12, "Punk Girl", 1, 5, new CompetitiveTeam.PokemonConfig[] { new CompetitiveTeam.PokemonConfig("arbok", "jolly", "intimidate", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "gunkshot", "earthquake", "suckerpunch", "coil" }, "cobblemon:black_sludge"), new CompetitiveTeam.PokemonConfig("muk", "adamant", "poisontouch", PHYSICAL_IVS, new int[] { 252, 252, 4, 0, 0, 0 }, new String[] { "gunkshot", "shadowsneak", "curse", "rest" }, "cobblemon:chesto_berry"), new CompetitiveTeam.PokemonConfig("overqwil", "jolly", "intimidate", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "gunkshot", "crunch", "aquajet", "toxicspikes" }, "cobblemon:payapa_berry"), new CompetitiveTeam.PokemonConfig("crobat", "jolly", "infiltrator", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "bravebird", "uturn", "roost", "defog" }, "cobblemon:sharp_beak"), new CompetitiveTeam.PokemonConfig("nidoking", "timid", "sheerforce", SPECIAL_IVS, new int[] { 0, 0, 0, 252, 4, 252 }, new String[] { "sludgewave", "earthpower", "icebeam", "flamethrower" }, "cobblemon:wise_glasses"), new CompetitiveTeam.PokemonConfig("drapion", "jolly", "sniper", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "crosspoison", "earthquake", "aquatail", "swordsdance" }, "cobblemon:scope_lens") }false));
/* 443 */     ALL_TEAMS.add(new CompetitiveTeam(13, "Bird Keeper", 1, 5, new CompetitiveTeam.PokemonConfig[] { new CompetitiveTeam.PokemonConfig("fearow", "jolly", "sniper", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "drillpeck", "drillrun", "uturn", "roost" }, "cobblemon:scope_lens"), new CompetitiveTeam.PokemonConfig("noctowl", "calm", "tintedlens", SPECIAL_IVS, new int[] { 252, 0, 4, 0, 252, 0 }, new String[] { "airslash", "roost", "defog", "toxic" }, "cobblemon:sharp_beak"), new CompetitiveTeam.PokemonConfig("talonflame", "jolly", "galewings", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "bravebird", "flareblitz", "roost", "uturn" }, "cobblemon:wacan_berry"), new CompetitiveTeam.PokemonConfig("staraptor", "jolly", "reckless", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "bravebird", "closecombat", "uturn", "quickattack" }, "cobblemon:choice_scarf"), new CompetitiveTeam.PokemonConfig("kleavor", "jolly", "sharpness", PHYSICAL_IVS, new int[] { 252, 0, 4, 0, 0, 252 }, new String[] { "stoneedge", "xscissor", "closecombat", "defog" }, "cobblemon:rocky_helmet"), new CompetitiveTeam.PokemonConfig("bombirdier", "jolly", "keeneye", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "bravebird", "knockoff", "uturn", "stealthrock" }, "cobblemon:focus_sash") }false));
/* 444 */     ALL_TEAMS.add(new CompetitiveTeam(14, "Ruin Maniac", 1, 5, new CompetitiveTeam.PokemonConfig[] { new CompetitiveTeam.PokemonConfig("sandslash", "adamant", "sandveil", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "earthquake", "stoneedge", "rapidspin", "swordsdance" }, "cobblemon:soft_sand"), new CompetitiveTeam.PokemonConfig("dugtrio", "jolly", "arenatrap", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "earthquake", "stoneedge", "suckerpunch", "stealthrock" }, "cobblemon:power_herb"), new CompetitiveTeam.PokemonConfig("excadrill", "adamant", "moldbreaker", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "earthquake", "ironhead", "rapidspin", "swordsdance" }, "cobblemon:life_orb"), new CompetitiveTeam.PokemonConfig("krookodile", "jolly", "intimidate", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "earthquake", "knockoff", "stoneedge", "crunch" }, "cobblemon:choice_scarf"), new CompetitiveTeam.PokemonConfig("hippowdon", "impish", "sandstream", PHYSICAL_IVS, new int[] { 252, 0, 252, 0, 4, 0 }, new String[] { "earthquake", "slackoff", "stealthrock", "whirlwind" }, "cobblemon:smooth_rock"), new CompetitiveTeam.PokemonConfig("swampert", "adamant", "torrent", PHYSICAL_IVS, new int[] { 252, 252, 0, 0, 4, 0 }, new String[] { "earthquake", "liquidation", "icepunch", "stealthrock" }, "cobblemon:rindo_berry") }false));
/* 445 */     ALL_TEAMS.add(new CompetitiveTeam(15, "Skier", 1, 5, new CompetitiveTeam.PokemonConfig[] { new CompetitiveTeam.PokemonConfig("avalugg", "impish", "sturdy", PHYSICAL_IVS, new int[] { 252, 0, 252, 0, 4, 0 }, new String[] { "avalanche", "bodypress", "recover", "rapidspin" }, "cobblemon:heavy_duty_boots"), new CompetitiveTeam.PokemonConfig("glalie", "jolly", "innerfocus", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "iciclecrash", "earthquake", "explosion", "spikes" }, "cobblemon:custap_berry"), new CompetitiveTeam.PokemonConfig("froslass", "timid", "cursedbody", SPECIAL_IVS, new int[] { 0, 0, 0, 252, 4, 252 }, new String[] { "icebeam", "shadowball", "spikes", "destinybond" }, "cobblemon:life_orb"), new CompetitiveTeam.PokemonConfig("weavile", "jolly", "pressure", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "knockoff", "iceshard", "tripleaxel", "swordsdance" }, "cobblemon:razor_claw"), new CompetitiveTeam.PokemonConfig("mamoswine", "jolly", "thickfat", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "earthquake", "iciclecrash", "iceshard", "stealthrock" }, "cobblemon:never_melt_ice"), new CompetitiveTeam.PokemonConfig("glaceon", "modest", "snowcloak", SPECIAL_IVS, new int[] { 0, 0, 0, 252, 4, 252 }, new String[] { "icebeam", "freezedry", "shadowball", "waterpulse" }, "cobblemon:choice_specs") }false));
/* 446 */     ALL_TEAMS.add(new CompetitiveTeam(16, "Dragon Tamer", 1, 5, new CompetitiveTeam.PokemonConfig[] { new CompetitiveTeam.PokemonConfig("dragonite", "adamant", "multiscale", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "extremespeed", "earthquake", "firepunch", "dragondance" }, "cobblemon:lum_berry"), new CompetitiveTeam.PokemonConfig("kingdra", "modest", "swiftswim", SPECIAL_IVS, new int[] { 0, 0, 0, 252, 4, 252 }, new String[] { "hydropump", "dracometeor", "icebeam", "focusenergy" }, "cobblemon:scope_lens"), new CompetitiveTeam.PokemonConfig("haxorus", "jolly", "moldbreaker", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "outrage", "earthquake", "poisonjab", "closecombat" }, "cobblemon:choice_scarf"), new CompetitiveTeam.PokemonConfig("salamence", "jolly", "intimidate", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "outrage", "earthquake", "roost", "dragondance" }, "cobblemon:weakness_policy"), new CompetitiveTeam.PokemonConfig("flygon", "jolly", "levitate", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "earthquake", "outrage", "uturn", "dragondance" }, "cobblemon:life_orb"), new CompetitiveTeam.PokemonConfig("dragapult", "jolly", "infiltrator", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "dragondarts", "phantomforce", "uturn", "suckerpunch" }, "cobblemon:choice_band") }false));
/* 447 */     ALL_TEAMS.add(new CompetitiveTeam(17, "Worker", 1, 5, new CompetitiveTeam.PokemonConfig[] { new CompetitiveTeam.PokemonConfig("aggron", "adamant", "rockhead", PHYSICAL_IVS, new int[] { 252, 252, 0, 0, 4, 0 }, new String[] { "headsmash", "earthquake", "heavyslam", "aquatail" }, "cobblemon:choice_band"), new CompetitiveTeam.PokemonConfig("metagross", "jolly", "clearbody", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "meteormash", "earthquake", "zenheadbutt", "agility" }, "cobblemon:weakness_policy"), new CompetitiveTeam.PokemonConfig("lucario", "jolly", "justified", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "closecombat", "extremespeed", "swordsdance", "meteormash" }, "cobblemon:black_belt"), new CompetitiveTeam.PokemonConfig("skarmory", "impish", "sturdy", PHYSICAL_IVS, new int[] { 252, 0, 252, 0, 4, 0 }, new String[] { "bravebird", "roost", "spikes", "whirlwind" }, "cobblemon:rocky_helmet"), new CompetitiveTeam.PokemonConfig("bronzong", "relaxed", "levitate", TRICK_ROOM_IVS, new int[] { 252, 0, 252, 0, 4, 0 }, new String[] { "gyroball", "bodypress", "stealthrock", "trickroom" }, "cobblemon:leftovers"), new CompetitiveTeam.PokemonConfig("magnezone", "modest", "magnetpull", SPECIAL_IVS, new int[] { 252, 0, 4, 252, 0, 0 }, new String[] { "thunderbolt", "flashcannon", "voltswitch", "bodypress" }, "cobblemon:air_balloon") }false));
/* 448 */     ALL_TEAMS.add(new CompetitiveTeam(18, "Fairy Tale Girl", 1, 5, new CompetitiveTeam.PokemonConfig[] { new CompetitiveTeam.PokemonConfig("clefable", "bold", "magicguard", SPECIAL_IVS, new int[] { 252, 0, 252, 0, 4, 0 }, new String[] { "moonblast", "softboiled", "stealthrock", "thunderwave" }, "cobblemon:leftovers"), new CompetitiveTeam.PokemonConfig("wigglytuff", "bold", "competitive", SPECIAL_IVS, new int[] { 252, 0, 252, 0, 4, 0 }, new String[] { "dazzlinggleam", "thunderbolt", "fireblast", "stealthrock" }, "cobblemon:rocky_helmet"), new CompetitiveTeam.PokemonConfig("togekiss", "timid", "serenegrace", SPECIAL_IVS, new int[] { 252, 0, 0, 252, 0, 4 }, new String[] { "airslash", "dazzlinggleam", "roost", "thunderwave" }, "cobblemon:heavy_duty_boots"), new CompetitiveTeam.PokemonConfig("sylveon", "modest", "pixilate", SPECIAL_IVS, new int[] { 252, 0, 0, 252, 4, 0 }, new String[] { "hypervoice", "mysticalfire", "shadowball", "psyshock" }, "cobblemon:choice_specs"), new CompetitiveTeam.PokemonConfig("grimmsnarl", "careful", "prankster", PHYSICAL_IVS, new int[] { 252, 0, 252, 0, 4, 0 }, new String[] { "spiritbreak", "lightscreen", "reflect", "taunt" }, "cobblemon:light_clay"), new CompetitiveTeam.PokemonConfig("mawile", "adamant", "intimidate", PHYSICAL_IVS, new int[] { 252, 252, 0, 0, 4, 0 }, new String[] { "ironhead", "playrough", "suckerpunch", "swordsdance" }, "cobblemon:metal_coat") }false));
/* 449 */     ALL_TEAMS.add(new CompetitiveTeam(19, "Team Grunt", 1, 5, new CompetitiveTeam.PokemonConfig[] { new CompetitiveTeam.PokemonConfig("mightyena", "adamant", "moxie", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "crunch", "playrough", "suckerpunch", "firefang" }, "cobblemon:muscle_band"), new CompetitiveTeam.PokemonConfig("houndoom", "timid", "flashfire", SPECIAL_IVS, new int[] { 0, 0, 0, 252, 4, 252 }, new String[] { "darkpulse", "fireblast", "sludgebomb", "solarbeam" }, "cobblemon:choice_specs"), new CompetitiveTeam.PokemonConfig("absol", "jolly", "superluck", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "knockoff", "suckerpunch", "playrough", "swordsdance" }, "cobblemon:scope_lens"), new CompetitiveTeam.PokemonConfig("hydreigon", "timid", "levitate", SPECIAL_IVS, new int[] { 0, 0, 0, 252, 4, 252 }, new String[] { "darkpulse", "dracometeor", "flashcannon", "uturn" }, "cobblemon:choice_scarf"), new CompetitiveTeam.PokemonConfig("bisharp", "adamant", "defiant", PHYSICAL_IVS, new int[] { 252, 252, 0, 0, 4, 0 }, new String[] { "suckerpunch", "ironhead", "knockoff", "swordsdance" }, "cobblemon:focus_sash"), new CompetitiveTeam.PokemonConfig("umbreon", "careful", "synchronize", PHYSICAL_IVS, new int[] { 252, 0, 4, 0, 252, 0 }, new String[] { "foulplay", "wish", "protect", "toxic" }, "cobblemon:mago_berry") }false));
/* 450 */     ALL_TEAMS.add(new CompetitiveTeam(20, "Youngster", 1, 5, new CompetitiveTeam.PokemonConfig[] { new CompetitiveTeam.PokemonConfig("vaporeon", "bold", "waterabsorb", SPECIAL_IVS, new int[] { 252, 0, 252, 0, 4, 0 }, new String[] { "scald", "wish", "protect", "toxic" }, "cobblemon:leftovers"), new CompetitiveTeam.PokemonConfig("jolteon", "timid", "voltabsorb", SPECIAL_IVS, new int[] { 0, 0, 0, 252, 4, 252 }, new String[] { "thunderbolt", "voltswitch", "shadowball", "weatherball" }, "cobblemon:choice_specs"), new CompetitiveTeam.PokemonConfig("flareon", "adamant", "guts", PHYSICAL_IVS, new int[] { 252, 252, 0, 0, 4, 0 }, new String[] { "flareblitz", "facade", "superpower", "quickattack" }, "cobblemon:toxic_orb"), new CompetitiveTeam.PokemonConfig("espeon", "timid", "magicbounce", SPECIAL_IVS, new int[] { 0, 0, 0, 252, 4, 252 }, new String[] { "psychic", "dazzlinggleam", "calmmind", "morningsun" }, "cobblemon:colbur_berry"), new CompetitiveTeam.PokemonConfig("sylveon", "modest", "pixilate", SPECIAL_IVS, new int[] { 252, 0, 4, 252, 0, 0 }, new String[] { "hypervoice", "mysticalfire", "wish", "protect" }, "cobblemon:rocky_helmet"), new CompetitiveTeam.PokemonConfig("leafeon", "jolly", "chlorophyll", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "leafblade", "knockoff", "xscissor", "swordsdance" }, "cobblemon:miracle_seed") }false));
/*     */   } private static void addFloor5to10Teams() {
/* 452 */     ALL_TEAMS.add(new CompetitiveTeam(21, "Swimmer Pro", 5, 10, new CompetitiveTeam.PokemonConfig[] { new CompetitiveTeam.PokemonConfig("barraskewda", "jolly", "swiftswim", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "liquidation", "closecombat", "psychicfangs", "crunch" }, "cobblemon:life_orb", "water"), new CompetitiveTeam.PokemonConfig("clawitzer", "modest", "megalauncher", SPECIAL_IVS, new int[] { 252, 0, 0, 252, 4, 0 }, new String[] { "waterpulse", "aurasphere", "darkpulse", "icebeam" }, "cobblemon:choice_specs", "water"), new CompetitiveTeam.PokemonConfig("palafin", "jolly", "zerotohero", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "jetpunch", "drainpunch", "icepunch", "bulkup" }, "cobblemon:leftovers", "water"), new CompetitiveTeam.PokemonConfig("quaquaval", "jolly", "moxie", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "aquastep", "closecombat", "aquajet", "swordsdance" }, "cobblemon:lum_berry", "water"), new CompetitiveTeam.PokemonConfig("primarina", "modest", "torrent", SPECIAL_IVS, new int[] { 252, 0, 0, 252, 4, 0 }, new String[] { "sparklingaria", "moonblast", "energyball", "psychic" }, "cobblemon:assault_vest", "water"), new CompetitiveTeam.PokemonConfig("toxapex", "bold", "regenerator", SPECIAL_IVS, new int[] { 252, 0, 252, 0, 4, 0 }, new String[] { "scald", "recover", "haze", "toxicspikes" }, "cobblemon:black_sludge", "water") }false));
/* 453 */     ALL_TEAMS.add(new CompetitiveTeam(22, "Ace Trainer", 5, 10, new CompetitiveTeam.PokemonConfig[] { new CompetitiveTeam.PokemonConfig("skeledirge", "modest", "unaware", SPECIAL_IVS, new int[] { 252, 0, 4, 252, 0, 0 }, new String[] { "torchsong", "shadowball", "slackoff", "willowisp" }, "cobblemon:heavy_duty_boots", "fire"), new CompetitiveTeam.PokemonConfig("ceruledge", "jolly", "flashfire", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "bitterblade", "poltergeist", "closecombat", "swordsdance" }, "cobblemon:life_orb", "fire"), new CompetitiveTeam.PokemonConfig("armarouge", "timid", "flashfire", SPECIAL_IVS, new int[] { 0, 0, 0, 252, 4, 252 }, new String[] { "armorcanon", "psyshock", "energyball", "expandingforce" }, "cobblemon:choice_scarf", "fire"), new CompetitiveTeam.PokemonConfig("cinderace", "jolly", "libero", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "pyroball", "highjumpkick", "uturn", "suckerpunch" }, "cobblemon:choice_band", "fire"), new CompetitiveTeam.PokemonConfig("chandelure", "timid", "flashfire", SPECIAL_IVS, new int[] { 0, 0, 0, 252, 4, 252 }, new String[] { "fireblast", "shadowball", "energyball", "trick" }, "cobblemon:blunder_policy", "fire"), new CompetitiveTeam.PokemonConfig("coalossal", "brave", "steamengine", PHYSICAL_IVS, new int[] { 252, 252, 0, 0, 4, 0 }, new String[] { "stoneedge", "flareblitz", "rapidspin", "stealthrock" }, "cobblemon:weakness_policy", "fire") }false));
/* 454 */     ALL_TEAMS.add(new CompetitiveTeam(23, "Gardener", 5, 10, new CompetitiveTeam.PokemonConfig[] { new CompetitiveTeam.PokemonConfig("rillaboom", "adamant", "grassysurge", PHYSICAL_IVS, new int[] { 252, 252, 0, 0, 4, 0 }, new String[] { "grassyglide", "knockoff", "uturn", "woodhammer" }, "cobblemon:choice_band", "grass"), new CompetitiveTeam.PokemonConfig("meowscarada", "jolly", "overgrow", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "flowertrick", "knockoff", "uturn", "suckerpunch" }, "cobblemon:focus_sash", "grass"), new CompetitiveTeam.PokemonConfig("tsareena", "adamant", "queenlymajesty", PHYSICAL_IVS, new int[] { 252, 252, 0, 0, 4, 0 }, new String[] { "powerwhip", "highjumpkick", "uturn", "knockoff" }, "cobblemon:assault_vest", "grass"), new CompetitiveTeam.PokemonConfig("lilligant_hisui", "jolly", "chlorophyll", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "victorydance", "closecombat", "solarblade", "icespinner" }, "cobblemon:life_orb", "grass"), new CompetitiveTeam.PokemonConfig("abomasnow", "quiet", "snowwarning", TRICK_ROOM_IVS, new int[] { 252, 0, 0, 252, 4, 0 }, new String[] { "blizzard", "gigadrain", "earthpower", "iceshard" }, "cobblemon:icy_rock", "grass"), new CompetitiveTeam.PokemonConfig("decidueye", "adamant", "longreach", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "leafblade", "spiritshackle", "swordsdance", "roost" }, "cobblemon:leftovers", "grass") }false));
/* 455 */     ALL_TEAMS.add(new CompetitiveTeam(24, "Engineer", 5, 10, new CompetitiveTeam.PokemonConfig[] { new CompetitiveTeam.PokemonConfig("toxtricity", "modest", "punkrock", SPECIAL_IVS, new int[] { 0, 0, 0, 252, 4, 252 }, new String[] { "overdrive", "boomburst", "sludgebomb", "voltswitch" }, "cobblemon:throat_spray", "electric"), new CompetitiveTeam.PokemonConfig("raichu_alola", "timid", "surgesurfer", SPECIAL_IVS, new int[] { 0, 0, 0, 252, 4, 252 }, new String[] { "thunderbolt", "psychic", "grassknot", "nastyplot" }, "cobblemon:life_orb", "electric"), new CompetitiveTeam.PokemonConfig("electivire", "adamant", "motordrive", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "wildcharge", "earthquake", "icepunch", "crosschop" }, "cobblemon:expert_belt", "electric"), new CompetitiveTeam.PokemonConfig("vikavolt", "modest", "levitate", SPECIAL_IVS, new int[] { 252, 0, 0, 252, 4, 0 }, new String[] { "thunderbolt", "bugbuzz", "energyball", "voltswitch" }, "cobblemon:choice_specs", "electric"), new CompetitiveTeam.PokemonConfig("bellibolt", "modest", "electromorphosis", SPECIAL_IVS, new int[] { 252, 0, 252, 252, 4, 0 }, new String[] { "thunderbolt", "voltswitch", "slackoff", "toxic" }, "cobblemon:leftovers", "electric"), new CompetitiveTeam.PokemonConfig("boltund", "jolly", "strongjaw", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "thunderfang", "firefang", "psychicfangs", "playrough" }, "cobblemon:choice_band", "electric") }false));
/* 456 */     ALL_TEAMS.add(new CompetitiveTeam(25, "Black Belt", 5, 10, new CompetitiveTeam.PokemonConfig[] { new CompetitiveTeam.PokemonConfig("breloom", "jolly", "technician", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "bulletseed", "machpunch", "rocktomb", "spore" }, "cobblemon:focus_sash", "fighting"), new CompetitiveTeam.PokemonConfig("hawlucha", "jolly", "unburden", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "closecombat", "acrobatics", "swordsdance", "encore" }, "cobblemon:grassy_seed", "fighting"), new CompetitiveTeam.PokemonConfig("medicham", "jolly", "purepower", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "closecombat", "zenheadbutt", "icepunch", "bulletpunch" }, "cobblemon:choice_scarf", "fighting"), new CompetitiveTeam.PokemonConfig("mienshao", "jolly", "regenerator", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "closecombat", "knockoff", "uturn", "fakeout" }, "cobblemon:life_orb", "fighting"), new CompetitiveTeam.PokemonConfig("falinks", "jolly", "defiant", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "closecombat", "noretreat", "throatchop", "poisonjab" }, "cobblemon:leftovers", "fighting"), new CompetitiveTeam.PokemonConfig("sirfetchd", "adamant", "scrappy", PHYSICAL_IVS, new int[] { 252, 252, 0, 0, 4, 0 }, new String[] { "closecombat", "bravebird", "leafblade", "firstimpression" }, "cobblemon:leek", "fighting") }false));
/* 457 */     ALL_TEAMS.add(new CompetitiveTeam(26, "Psychic", 5, 10, new CompetitiveTeam.PokemonConfig[] { new CompetitiveTeam.PokemonConfig("indeedee", "timid", "psychicsurge", SPECIAL_IVS, new int[] { 0, 0, 0, 252, 4, 252 }, new String[] { "expandingforce", "hypervoice", "shadowball", "mysticalfire" }, "cobblemon:choice_scarf", "psychic"), new CompetitiveTeam.PokemonConfig("armarouge", "modest", "flashfire", SPECIAL_IVS, new int[] { 0, 0, 0, 252, 4, 252 }, new String[] { "armorcanon", "psyshock", "energyball", "calmmind" }, "cobblemon:life_orb", "psychic"), new CompetitiveTeam.PokemonConfig("espathra", "timid", "speedboost", SPECIAL_IVS, new int[] { 0, 0, 0, 252, 4, 252 }, new String[] { "luminarcrash", "dazzlinggleam", "calmmind", "protect" }, "cobblemon:leftovers", "psychic"), new CompetitiveTeam.PokemonConfig("slowking_galar", "calm", "regenerator", SPECIAL_IVS, new int[] { 252, 0, 4, 0, 252, 0 }, new String[] { "sludgebomb", "psychic", "icebeam", "flamethrower" }, "cobblemon:assault_vest", "psychic"), new CompetitiveTeam.PokemonConfig("bronzong", "relaxed", "levitate", TRICK_ROOM_IVS, new int[] { 252, 0, 252, 0, 4, 0 }, new String[] { "gyroball", "bodypress", "stealthrock", "trickroom" }, "cobblemon:rocky_helmet", "psychic"), new CompetitiveTeam.PokemonConfig("sigilyph", "timid", "magicguard", SPECIAL_IVS, new int[] { 0, 0, 0, 252, 4, 252 }, new String[] { "psychic", "airslash", "heatwave", "roost" }, "cobblemon:heavy_duty_boots", "psychic") }false));
/* 458 */     ALL_TEAMS.add(new CompetitiveTeam(27, "Hiker", 5, 10, new CompetitiveTeam.PokemonConfig[] { new CompetitiveTeam.PokemonConfig("lycanrocdusk", "jolly", "toughclaws", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "stoneedge", "closecombat", "accelerock", "swordsdance" }, "cobblemon:life_orb", "rock"), new CompetitiveTeam.PokemonConfig("coalossal", "brave", "steamengine", PHYSICAL_IVS, new int[] { 252, 252, 0, 0, 4, 0 }, new String[] { "stoneedge", "flareblitz", "rapidspin", "stealthrock" }, "cobblemon:weakness_policy", "rock"), new CompetitiveTeam.PokemonConfig("stonjourner", "jolly", "powerspot", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "stoneedge", "bodypress", "heatcrash", "heavyslam" }, "cobblemon:choice_band", "rock"), new CompetitiveTeam.PokemonConfig("kleavor", "jolly", "sharpness", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "stoneedge", "xscissor", "closecombat", "swordsdance" }, "cobblemon:protective_pads", "rock"), new CompetitiveTeam.PokemonConfig("glimmora", "timid", "toxicdebris", SPECIAL_IVS, new int[] { 0, 0, 0, 252, 4, 252 }, new String[] { "powergem", "sludgewave", "earthpower", "stealthrock" }, "cobblemon:focus_sash", "rock"), new CompetitiveTeam.PokemonConfig("garganacl", "careful", "purifyingsalt", PHYSICAL_IVS, new int[] { 252, 0, 4, 0, 252, 0 }, new String[] { "saltcure", "recover", "stealthrock", "bodypress" }, "cobblemon:leftovers", "rock") }false));
/* 459 */     ALL_TEAMS.add(new CompetitiveTeam(28, "Hex Maniac", 5, 10, new CompetitiveTeam.PokemonConfig[] { new CompetitiveTeam.PokemonConfig("ceruledge", "jolly", "flashfire", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "bitterblade", "poltergeist", "closecombat", "swordsdance" }, "cobblemon:life_orb", "ghost"), new CompetitiveTeam.PokemonConfig("annihilape", "adamant", "defiant", PHYSICAL_IVS, new int[] { 252, 252, 4, 0, 0, 0 }, new String[] { "ragefist", "bulkup", "drainpunch", "taunt" }, "cobblemon:leftovers", "ghost"), new CompetitiveTeam.PokemonConfig("gholdengo", "timid", "goodasgold", SPECIAL_IVS, new int[] { 0, 0, 0, 252, 4, 252 }, new String[] { "makeitrain", "shadowball", "focusblast", "thunderbolt" }, "cobblemon:choice_scarf", "ghost"), new CompetitiveTeam.PokemonConfig("polteageist", "modest", "weakarmor", SPECIAL_IVS, new int[] { 0, 0, 0, 252, 4, 252 }, new String[] { "shadowball", "storedpower", "shellsmash", "strengthsap" }, "cobblemon:white_herb", "ghost"), new CompetitiveTeam.PokemonConfig("cursola", "modest", "perishbody", SPECIAL_IVS, new int[] { 252, 0, 0, 252, 4, 0 }, new String[] { "shadowball", "powergem", "earthpower", "strengthsap" }, "cobblemon:heavy_duty_boots", "ghost"), new CompetitiveTeam.PokemonConfig("zoroark_hisui", "timid", "illusion", SPECIAL_IVS, new int[] { 0, 0, 0, 252, 4, 252 }, new String[] { "bittermalice", "hypervoice", "flamethrower", "uturn" }, "cobblemon:choice_specs", "ghost") }false));
/* 460 */     ALL_TEAMS.add(new CompetitiveTeam(29, "Dragon Tamer", 5, 10, new CompetitiveTeam.PokemonConfig[] { new CompetitiveTeam.PokemonConfig("baxcalibur", "jolly", "thermalexchange", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "glaiverush", "iciclecrash", "earthquake", "dragondance" }, "cobblemon:loaded_dice", "dragon"), new CompetitiveTeam.PokemonConfig("goodra_hisui", "modest", "sapsipper", SPECIAL_IVS, new int[] { 252, 0, 0, 252, 4, 0 }, new String[] { "dracometeor", "flashcannon", "thunderbolt", "flamethrower" }, "cobblemon:assault_vest", "dragon"), new CompetitiveTeam.PokemonConfig("kommoo", "jolly", "overcoat", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "clangingscales", "closecombat", "poisonjab", "dragondance" }, "cobblemon:throat_spray", "dragon"), new CompetitiveTeam.PokemonConfig("noivern", "timid", "infiltrator", SPECIAL_IVS, new int[] { 0, 0, 0, 252, 4, 252 }, new String[] { "dracometeor", "hurricane", "flamethrower", "uturn" }, "cobblemon:choice_specs", "dragon"), new CompetitiveTeam.PokemonConfig("duraludon", "modest", "lightmetal", SPECIAL_IVS, new int[] { 0, 0, 0, 252, 4, 252 }, new String[] { "dracometeor", "flashcannon", "thunderbolt", "stealthrock" }, "cobblemon:eviolite", "dragon"), new CompetitiveTeam.PokemonConfig("appletun", "modest", "thickfat", SPECIAL_IVS, new int[] { 252, 0, 0, 252, 4, 0 }, new String[] { "appleacid", "dragonpulse", "recover", "leechseed" }, "cobblemon:leftovers", "dragon") }false));
/* 461 */     ALL_TEAMS.add(new CompetitiveTeam(30, "Worker", 5, 10, new CompetitiveTeam.PokemonConfig[] { new CompetitiveTeam.PokemonConfig("kingambit", "adamant", "supremeoverlord", PHYSICAL_IVS, new int[] { 252, 252, 0, 0, 4, 0 }, new String[] { "ironhead", "suckerpunch", "kowtowcleave", "swordsdance" }, "cobblemon:black_glasses", "steel"), new CompetitiveTeam.PokemonConfig("gholdengo", "timid", "goodasgold", SPECIAL_IVS, new int[] { 0, 0, 0, 252, 4, 252 }, new String[] { "makeitrain", "shadowball", "focusblast", "nastyplot" }, "cobblemon:air_balloon", "steel"), new CompetitiveTeam.PokemonConfig("corviknight", "impish", "pressure", PHYSICAL_IVS, new int[] { 252, 0, 252, 0, 4, 0 }, new String[] { "bravebird", "roost", "defog", "uturn" }, "cobblemon:leftovers", "steel"), new CompetitiveTeam.PokemonConfig("tinkaton", "jolly", "moldbreaker", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "gigatonhammer", "playrough", "knockoff", "stealthrock" }, "cobblemon:eject_pack", "steel"), new CompetitiveTeam.PokemonConfig("orthworm", "careful", "eartheater", PHYSICAL_IVS, new int[] { 252, 0, 4, 0, 252, 0 }, new String[] { "ironhead", "bodypress", "stealthrock", "shedtail" }, "cobblemon:sitrus_berry", "steel"), new CompetitiveTeam.PokemonConfig("copperajah", "adamant", "sheerforce", PHYSICAL_IVS, new int[] { 252, 252, 0, 0, 4, 0 }, new String[] { "ironhead", "playrough", "heatcrash", "stealthrock" }, "cobblemon:life_orb", "steel") }false));
/* 462 */     ALL_TEAMS.add(new CompetitiveTeam(31, "Fairy Tale Girl", 5, 10, new CompetitiveTeam.PokemonConfig[] { new CompetitiveTeam.PokemonConfig("primarina", "modest", "torrent", SPECIAL_IVS, new int[] { 252, 0, 0, 252, 4, 0 }, new String[] { "sparklingaria", "moonblast", "energyball", "psychic" }, "cobblemon:assault_vest", "fairy"), new CompetitiveTeam.PokemonConfig("florges", "calm", "symbiosis", SPECIAL_IVS, new int[] { 252, 0, 4, 0, 252, 0 }, new String[] { "moonblast", "synthesis", "calmmind", "wish" }, "cobblemon:leftovers", "fairy"), new CompetitiveTeam.PokemonConfig("ninetales_alola", "timid", "snowwarning", SPECIAL_IVS, new int[] { 0, 0, 0, 252, 4, 252 }, new String[] { "blizzard", "moonblast", "freezedry", "auroraveil" }, "cobblemon:light_clay", "fairy"), new CompetitiveTeam.PokemonConfig("ribombee", "timid", "shielddust", SPECIAL_IVS, new int[] { 0, 0, 0, 252, 4, 252 }, new String[] { "moonblast", "bugbuzz", "energyball", "quiverdance" }, "cobblemon:focus_sash", "fairy"), new CompetitiveTeam.PokemonConfig("hatterene", "quiet", "magicbounce", TRICK_ROOM_IVS, new int[] { 252, 0, 0, 252, 4, 0 }, new String[] { "psychic", "drainingkiss", "trickroom", "calmmind" }, "cobblemon:life_orb", "fairy"), new CompetitiveTeam.PokemonConfig("rapidash_galar", "jolly", "pastelveil", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "playrough", "zenheadbutt", "morningsun", "swordsdance" }, "cobblemon:heavy_duty_boots", "fairy") }false));
/* 463 */     ALL_TEAMS.add(new CompetitiveTeam(32, "Team Admin", 5, 10, new CompetitiveTeam.PokemonConfig[] { new CompetitiveTeam.PokemonConfig("kingambit", "adamant", "supremeoverlord", PHYSICAL_IVS, new int[] { 252, 252, 0, 0, 4, 0 }, new String[] { "ironhead", "suckerpunch", "kowtowcleave", "swordsdance" }, "cobblemon:black_glasses", "dark"), new CompetitiveTeam.PokemonConfig("meowscarada", "jolly", "overgrow", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "flowertrick", "knockoff", "uturn", "suckerpunch" }, "cobblemon:focus_sash", "dark"), new CompetitiveTeam.PokemonConfig("grimmsnarl", "careful", "prankster", PHYSICAL_IVS, new int[] { 252, 0, 252, 0, 4, 0 }, new String[] { "spiritbreak", "lightscreen", "reflect", "taunt" }, "cobblemon:light_clay", "dark"), new CompetitiveTeam.PokemonConfig("zoroark_hisui", "timid", "illusion", SPECIAL_IVS, new int[] { 0, 0, 0, 252, 4, 252 }, new String[] { "bittermalice", "hypervoice", "flamethrower", "uturn" }, "cobblemon:choice_specs", "dark"), new CompetitiveTeam.PokemonConfig("malamar", "adamant", "contrary", PHYSICAL_IVS, new int[] { 128, 252, 128, 0, 0, 0 }, new String[] { "topsyturvy", "knockoff", "psychocut", "superpower" }, "cobblemon:life_orb", "dark"), new CompetitiveTeam.PokemonConfig("incineroar", "adamant", "intimidate", PHYSICAL_IVS, new int[] { 252, 252, 0, 0, 4, 0 }, new String[] { "flareblitz", "knockoff", "fakeout", "partingshot" }, "cobblemon:sitrus_berry", "dark") }false));
/* 464 */     ALL_TEAMS.add(new CompetitiveTeam(33, "Punk Girl", 5, 10, new CompetitiveTeam.PokemonConfig[] { new CompetitiveTeam.PokemonConfig("glimmora", "timid", "toxicdebris", SPECIAL_IVS, new int[] { 0, 0, 0, 252, 4, 252 }, new String[] { "powergem", "sludgewave", "earthpower", "stealthrock" }, "cobblemon:focus_sash", "poison"), new CompetitiveTeam.PokemonConfig("overqwil", "jolly", "intimidate", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "gunkshot", "crunch", "aquajet", "swordsdance" }, "cobblemon:life_orb", "poison"), new CompetitiveTeam.PokemonConfig("salazzle", "timid", "corrosion", SPECIAL_IVS, new int[] { 0, 0, 0, 252, 4, 252 }, new String[] { "sludgewave", "fireblast", "toxic", "nastyplot" }, "cobblemon:black_sludge", "poison"), new CompetitiveTeam.PokemonConfig("weezing_galar", "bold", "neutralizinggas", SPECIAL_IVS, new int[] { 252, 0, 252, 0, 4, 0 }, new String[] { "strangesteam", "sludgebomb", "willowisp", "painsplit" }, "cobblemon:rocky_helmet", "poison"), new CompetitiveTeam.PokemonConfig("scolipede", "jolly", "speedboost", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "poisonjab", "earthquake", "megahorn", "swordsdance" }, "cobblemon:protective_pads", "poison"), new CompetitiveTeam.PokemonConfig("dragalge", "modest", "adaptability", SPECIAL_IVS, new int[] { 252, 0, 0, 252, 4, 0 }, new String[] { "sludgewave", "dracometeor", "hydropump", "focusblast" }, "cobblemon:choice_specs", "poison") }false));
/* 465 */     ALL_TEAMS.add(new CompetitiveTeam(34, "Ruin Maniac", 5, 10, new CompetitiveTeam.PokemonConfig[] { new CompetitiveTeam.PokemonConfig("clodsire", "careful", "waterabsorb", PHYSICAL_IVS, new int[] { 252, 0, 4, 0, 252, 0 }, new String[] { "earthquake", "recover", "toxic", "stealthrock" }, "cobblemon:leftovers", "ground"), new CompetitiveTeam.PokemonConfig("mudsdale", "adamant", "stamina", PHYSICAL_IVS, new int[] { 252, 252, 0, 0, 4, 0 }, new String[] { "earthquake", "closecombat", "heavyslam", "rockslide" }, "cobblemon:assault_vest", "ground"), new CompetitiveTeam.PokemonConfig("palossand", "bold", "watercompaction", SPECIAL_IVS, new int[] { 252, 0, 252, 0, 4, 0 }, new String[] { "earthpower", "shadowball", "shoreup", "stealthrock" }, "cobblemon:colbur_berry", "ground"), new CompetitiveTeam.PokemonConfig("golurk", "adamant", "ironfist", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "earthquake", "poltergeist", "thunderpunch", "icepunch" }, "cobblemon:choice_band", "ground"), new CompetitiveTeam.PokemonConfig("gastrodon", "bold", "stormdrain", SPECIAL_IVS, new int[] { 252, 0, 252, 0, 4, 0 }, new String[] { "earthpower", "scald", "recover", "toxic" }, "cobblemon:rindo_berry", "ground"), new CompetitiveTeam.PokemonConfig("whiscash", "adamant", "oblivious", PHYSICAL_IVS, new int[] { 252, 252, 0, 0, 4, 0 }, new String[] { "earthquake", "liquidation", "zenheadbutt", "dragondance" }, "cobblemon:life_orb", "ground") }false));
/*     */   }
/*     */   
/*     */   private static void addFloor10to15Teams() {
/* 469 */     ALL_TEAMS.add(new CompetitiveTeam(35, "Bird Keeper", 10, 15, new CompetitiveTeam.PokemonConfig[] { new CompetitiveTeam.PokemonConfig("talonflame", "jolly", "galewings", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "bravebird", "flareblitz", "roost", "uturn" }, "cobblemon:heavy_duty_boots", "flying"), new CompetitiveTeam.PokemonConfig("staraptor", "jolly", "reckless", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "bravebird", "closecombat", "uturn", "quickattack" }, "cobblemon:choice_scarf", "flying"), new CompetitiveTeam.PokemonConfig("gliscor", "impish", "poisonheal", PHYSICAL_IVS, new int[] { 252, 0, 184, 0, 72, 0 }, new String[] { "earthquake", "protect", "toxic", "roost" }, "cobblemon:toxic_orb", "flying"), new CompetitiveTeam.PokemonConfig("crobat", "jolly", "infiltrator", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "bravebird", "uturn", "roost", "defog" }, "cobblemon:life_orb", "flying"), new CompetitiveTeam.PokemonConfig("mandibuzz", "impish", "overcoat", PHYSICAL_IVS, new int[] { 252, 0, 252, 0, 4, 0 }, new String[] { "foulplay", "roost", "toxic", "defog" }, "cobblemon:rocky_helmet", "flying"), new CompetitiveTeam.PokemonConfig("noivern", "timid", "infiltrator", SPECIAL_IVS, new int[] { 0, 0, 0, 252, 4, 252 }, new String[] { "dracometeor", "hurricane", "flamethrower", "uturn" }, "cobblemon:choice_specs", "flying") }false));
/* 470 */     ALL_TEAMS.add(new CompetitiveTeam(36, "Rain Dancer", 10, 15, new CompetitiveTeam.PokemonConfig[] { new CompetitiveTeam.PokemonConfig("barraskewda", "adamant", "swiftswim", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "liquidation", "closecombat", "psychicfangs", "crunch" }, "cobblemon:choice_band", "water"), new CompetitiveTeam.PokemonConfig("seismitoad", "adamant", "swiftswim", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "earthquake", "liquidation", "powerwhip", "stealthrock" }, "cobblemon:life_orb", "ground"), new CompetitiveTeam.PokemonConfig("pelipper", "bold", "drizzle", SPECIAL_IVS, new int[] { 248, 0, 252, 0, 8, 0 }, new String[] { "hurricane", "surf", "uturn", "roost" }, "cobblemon:damp_rock", "water"), new CompetitiveTeam.PokemonConfig("ferrothorn", "relaxed", "ironbarbs", TRICK_ROOM_IVS, new int[] { 252, 0, 252, 0, 4, 0 }, new String[] { "powerwhip", "gyroball", "stealthrock", "leechseed" }, "cobblemon:leftovers", "steel"), new CompetitiveTeam.PokemonConfig("kingdra", "modest", "swiftswim", SPECIAL_IVS, new int[] { 0, 0, 0, 252, 4, 252 }, new String[] { "hydropump", "dracometeor", "icebeam", "focusenergy" }, "cobblemon:scope_lens", "dragon"), new CompetitiveTeam.PokemonConfig("tentacruel", "timid", "raindish", SPECIAL_IVS, new int[] { 252, 0, 0, 4, 0, 252 }, new String[] { "scald", "rapidspin", "toxicspikes", "sludgebomb" }, "cobblemon:black_sludge", "poison") }false));
/* 471 */     ALL_TEAMS.add(new CompetitiveTeam(37, "Sun Worshipper", 10, 15, new CompetitiveTeam.PokemonConfig[] { new CompetitiveTeam.PokemonConfig("ninetales", "timid", "drought", SPECIAL_IVS, new int[] { 0, 0, 0, 252, 4, 252 }, new String[] { "fireblast", "solarbeam", "willowisp", "nastyplot" }, "cobblemon:heat_rock", "fire"), new CompetitiveTeam.PokemonConfig("venusaur", "modest", "chlorophyll", SPECIAL_IVS, new int[] { 0, 0, 0, 252, 4, 252 }, new String[] { "gigadrain", "sludgebomb", "earthpower", "weatherball" }, "cobblemon:life_orb", "grass", true), new CompetitiveTeam.PokemonConfig("charizard", "timid", "solarpower", SPECIAL_IVS, new int[] { 0, 0, 0, 252, 4, 252 }, new String[] { "weatherball", "solarbeam", "focusblast", "airslash" }, "cobblemon:choice_specs", "fire", true), new CompetitiveTeam.PokemonConfig("torkoal", "quiet", "drought", TRICK_ROOM_IVS, new int[] { 248, 0, 252, 8, 0, 0 }, new String[] { "eruption", "earthpower", "solarbeam", "stealthrock" }, "cobblemon:leftovers", "fire"), new CompetitiveTeam.PokemonConfig("camerupt", "quiet", "solidrock", TRICK_ROOM_IVS, new int[] { 252, 0, 4, 252, 0, 0 }, new String[] { "eruption", "earthpower", "flashcannon", "stealthrock" }, "cobblemon:passho_berry", "fire"), new CompetitiveTeam.PokemonConfig("leafeon", "jolly", "chlorophyll", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "leafblade", "knockoff", "xscissor", "doubleedge" }, "cobblemon:choice_band", "grass") }false));
/* 472 */     ALL_TEAMS.add(new CompetitiveTeam(38, "Desert Nomad", 10, 15, new CompetitiveTeam.PokemonConfig[] { new CompetitiveTeam.PokemonConfig("excadrill", "jolly", "sandrush", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "earthquake", "ironhead", "rockslide", "rapidspin" }, "cobblemon:life_orb", "ground"), new CompetitiveTeam.PokemonConfig("tyranitar", "adamant", "sandstream", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "stoneedge", "crunch", "earthquake", "dragondance" }, "cobblemon:smooth_rock", "rock"), new CompetitiveTeam.PokemonConfig("dracozolt", "jolly", "sandrush", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "boltbeak", "earthquake", "outrage", "firefang" }, "cobblemon:choice_scarf", "electric"), new CompetitiveTeam.PokemonConfig("sandaconda", "adamant", "sandspit", PHYSICAL_IVS, new int[] { 252, 252, 0, 0, 4, 0 }, new String[] { "earthquake", "stoneedge", "coil", "rest" }, "cobblemon:chesto_berry", "ground"), new CompetitiveTeam.PokemonConfig("stoutland", "adamant", "sandrush", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "return", "crunch", "superpower", "playrough" }, "cobblemon:choice_band", "normal"), new CompetitiveTeam.PokemonConfig("garchomp", "jolly", "roughskin", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "earthquake", "outrage", "stoneedge", "swordsdance" }, "cobblemon:focus_sash", "dragon") }false));
/* 473 */     ALL_TEAMS.add(new CompetitiveTeam(39, "Veteran", 10, 15, new CompetitiveTeam.PokemonConfig[] { new CompetitiveTeam.PokemonConfig("arboliva", "bold", "seedsower", TRICK_ROOM_IVS, new int[] { 252, 0, 252, 0, 4, 0 }, new String[] { "trickroom", "gigadrain", "earthpower", "terrainpulse" }, "cobblemon:mental_herb", "grass"), new CompetitiveTeam.PokemonConfig("conkeldurr", "brave", "guts", TRICK_ROOM_IVS, new int[] { 252, 252, 0, 0, 4, 0 }, new String[] { "drainpunch", "machpunch", "knockoff", "icepunch" }, "cobblemon:flame_orb", "fighting"), new CompetitiveTeam.PokemonConfig("rhyperior", "brave", "solidrock", TRICK_ROOM_IVS, new int[] { 252, 252, 0, 0, 4, 0 }, new String[] { "earthquake", "stoneedge", "megahorn", "rockblast" }, "cobblemon:weakness_policy", "rock"), new CompetitiveTeam.PokemonConfig("marowak_alola", "brave", "rockhead", TRICK_ROOM_IVS, new int[] { 252, 252, 0, 0, 4, 0 }, new String[] { "flareblitz", "poltergeist", "bonemerang", "shadowbone" }, "cobblemon:thick_club", "fire"), new CompetitiveTeam.PokemonConfig("slowbro", "quiet", "regenerator", TRICK_ROOM_IVS, new int[] { 252, 0, 252, 0, 4, 0 }, new String[] { "psychic", "scald", "slackoff", "trickroom" }, "cobblemon:leftovers", "psychic"), new CompetitiveTeam.PokemonConfig("reuniclus", "quiet", "magicguard", TRICK_ROOM_IVS, new int[] { 252, 0, 0, 252, 4, 0 }, new String[] { "psychic", "focusblast", "shadowball", "trickroom" }, "cobblemon:life_orb", "psychic") }false));
/* 474 */     ALL_TEAMS.add(new CompetitiveTeam(40, "Ace Trainer Elite", 10, 15, new CompetitiveTeam.PokemonConfig[] { new CompetitiveTeam.PokemonConfig("krookodile", "jolly", "intimidate", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "earthquake", "stoneedge", "knockoff", "stealthrock" }, "cobblemon:focus_sash", "ground"), new CompetitiveTeam.PokemonConfig("toxapex", "bold", "regenerator", SPECIAL_IVS, new int[] { 252, 0, 252, 0, 4, 0 }, new String[] { "scald", "recover", "haze", "toxicspikes" }, "cobblemon:black_sludge", "poison"), new CompetitiveTeam.PokemonConfig("corviknight", "impish", "pressure", PHYSICAL_IVS, new int[] { 252, 0, 252, 0, 4, 0 }, new String[] { "bravebird", "roost", "defog", "uturn" }, "cobblemon:rocky_helmet", "flying", true), new CompetitiveTeam.PokemonConfig("dragapult", "jolly", "infiltrator", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "dragondarts", "phantomforce", "uturn", "suckerpunch" }, "cobblemon:choice_band", "dragon"), new CompetitiveTeam.PokemonConfig("clefable", "bold", "magicguard", SPECIAL_IVS, new int[] { 252, 0, 252, 0, 4, 0 }, new String[] { "moonblast", "softboiled", "stealthrock", "thunderwave" }, "cobblemon:leftovers", "fairy"), new CompetitiveTeam.PokemonConfig("empoleon", "timid", "competitive", SPECIAL_IVS, new int[] { 0, 0, 0, 252, 4, 252 }, new String[] { "surf", "flashcannon", "icebeam", "stealthrock" }, "cobblemon:air_balloon", "steel") }false));
/* 475 */     ALL_TEAMS.add(new CompetitiveTeam(41, "Stall Master", 10, 15, new CompetitiveTeam.PokemonConfig[] { new CompetitiveTeam.PokemonConfig("chansey", "bold", "naturalcure", SPECIAL_IVS, new int[] { 252, 0, 252, 0, 4, 0 }, new String[] { "seismictoss", "softboiled", "toxic", "stealthrock" }, "cobblemon:eviolite", "normal"), new CompetitiveTeam.PokemonConfig("dondozo", "careful", "unaware", PHYSICAL_IVS, new int[] { 252, 0, 4, 0, 252, 0 }, new String[] { "waterfall", "rest", "sleeptalk", "curse" }, "cobblemon:chesto_berry", "water"), new CompetitiveTeam.PokemonConfig("skarmory", "impish", "sturdy", PHYSICAL_IVS, new int[] { 252, 0, 252, 0, 4, 0 }, new String[] { "bravebird", "roost", "spikes", "whirlwind" }, "cobblemon:rocky_helmet", "steel"), new CompetitiveTeam.PokemonConfig("quagsire", "relaxed", "unaware", TRICK_ROOM_IVS, new int[] { 252, 0, 252, 0, 4, 0 }, new String[] { "recover", "earthquake", "toxic", "scald" }, "cobblemon:leftovers", "water"), new CompetitiveTeam.PokemonConfig("umbreon", "careful", "synchronize", PHYSICAL_IVS, new int[] { 252, 0, 4, 0, 252, 0 }, new String[] { "foulplay", "wish", "protect", "toxic" }, "cobblemon:lum_berry", "dark"), new CompetitiveTeam.PokemonConfig("hippowdon", "impish", "sandstream", PHYSICAL_IVS, new int[] { 252, 0, 252, 0, 4, 0 }, new String[] { "earthquake", "slackoff", "stealthrock", "whirlwind" }, "cobblemon:smooth_rock", "ground") }false));
/* 476 */     ALL_TEAMS.add(new CompetitiveTeam(42, "Striker", 10, 15, new CompetitiveTeam.PokemonConfig[] { new CompetitiveTeam.PokemonConfig("sneasler", "jolly", "unburden", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "closecombat", "direclaw", "fakeout", "swordsdance" }, "cobblemon:normal_gem", "dark"), new CompetitiveTeam.PokemonConfig("volcarona", "timid", "flamebody", SPECIAL_IVS, new int[] { 0, 0, 0, 252, 4, 252 }, new String[] { "fierydance", "bugbuzz", "gigadrain", "quiverdance" }, "cobblemon:heavy_duty_boots", "fire"), new CompetitiveTeam.PokemonConfig("garchomp", "jolly", "roughskin", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "earthquake", "outrage", "stoneedge", "firefang" }, "cobblemon:choice_scarf", "dragon"), new CompetitiveTeam.PokemonConfig("azumarill", "adamant", "hugepower", PHYSICAL_IVS, new int[] { 252, 252, 0, 0, 4, 0 }, new String[] { "aquajet", "playrough", "bellydrum", "liquidation" }, "cobblemon:sitrus_berry", "fairy"), new CompetitiveTeam.PokemonConfig("gengar", "timid", "cursedbody", SPECIAL_IVS, new int[] { 0, 0, 0, 252, 4, 252 }, new String[] { "shadowball", "sludgebomb", "focusblast", "nastyplot" }, "cobblemon:life_orb", "ghost"), new CompetitiveTeam.PokemonConfig("dragonite", "adamant", "multiscale", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "extremespeed", "earthquake", "firepunch", "dragondance" }, "cobblemon:lum_berry", "dragon") }false));
/* 477 */     ALL_TEAMS.add(new CompetitiveTeam(43, "Tank Commander", 10, 15, new CompetitiveTeam.PokemonConfig[] { new CompetitiveTeam.PokemonConfig("slowbro", "bold", "regenerator", SPECIAL_IVS, new int[] { 252, 0, 252, 0, 4, 0 }, new String[] { "scald", "psychic", "slackoff", "teleport" }, "cobblemon:colbur_berry", "water"), new CompetitiveTeam.PokemonConfig("tangrowth", "relaxed", "regenerator", TRICK_ROOM_IVS, new int[] { 252, 0, 252, 0, 4, 0 }, new String[] { "gigadrain", "knockoff", "sleeppowder", "synthesis" }, "cobblemon:rocky_helmet", "grass"), new CompetitiveTeam.PokemonConfig("copperajah", "brave", "sheerforce", PHYSICAL_IVS, new int[] { 252, 252, 0, 0, 4, 0 }, new String[] { "ironhead", "heatcrash", "playrough", "stealthrock" }, "cobblemon:leftovers", "steel"), new CompetitiveTeam.PokemonConfig("togekiss", "timid", "serenegrace", SPECIAL_IVS, new int[] { 252, 0, 0, 252, 0, 4 }, new String[] { "airslash", "dazzlinggleam", "roost", "thunderwave" }, "cobblemon:heavy_duty_boots", "fairy"), new CompetitiveTeam.PokemonConfig("swampert", "relaxed", "torrent", TRICK_ROOM_IVS, new int[] { 252, 0, 252, 0, 4, 0 }, new String[] { "earthquake", "liquidation", "stealthrock", "yawn" }, "cobblemon:rindo_berry", "water"), new CompetitiveTeam.PokemonConfig("mandibuzz", "impish", "overcoat", PHYSICAL_IVS, new int[] { 252, 0, 252, 0, 4, 0 }, new String[] { "foulplay", "roost", "defog", "toxic" }, "cobblemon:protective_pads", "dark") }false));
/* 478 */     ALL_TEAMS.add(new CompetitiveTeam(44, "Weather Master", 10, 15, new CompetitiveTeam.PokemonConfig[] { new CompetitiveTeam.PokemonConfig("ninetales_alola", "timid", "snowwarning", SPECIAL_IVS, new int[] { 0, 0, 0, 252, 4, 252 }, new String[] { "blizzard", "freezedry", "moonblast", "auroraveil" }, "cobblemon:light_clay", "ice"), new CompetitiveTeam.PokemonConfig("sandslash_alola", "jolly", "slushrush", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "iciclecrash", "ironhead", "earthquake", "rapidspin" }, "cobblemon:life_orb", "ice"), new CompetitiveTeam.PokemonConfig("arctozolt", "naive", "slushrush", PERFECT_IVS, new int[] { 0, 252, 0, 4, 0, 252 }, new String[] { "boltbeak", "blizzard", "freezedry", "lowkick" }, "cobblemon:choice_scarf", "electric"), new CompetitiveTeam.PokemonConfig("mamoswine", "jolly", "thickfat", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "earthquake", "iciclecrash", "iceshard", "stealthrock" }, "cobblemon:focus_sash", "ground"), new CompetitiveTeam.PokemonConfig("weavile", "jolly", "pressure", PHYSICAL_IVS, new int[] { 0, 252, 0, 0, 4, 252 }, new String[] { "knockoff", "iceshard", "tripleaxel", "swordsdance" }, "cobblemon:heavy_duty_boots", "dark"), new CompetitiveTeam.PokemonConfig("glaceon", "modest", "snowcloak", SPECIAL_IVS, new int[] { 0, 0, 0, 252, 4, 252 }, new String[] { "icebeam", "freezedry", "shadowball", "waterpulse" }, "cobblemon:choice_specs", "ice") }false));
/* 479 */     ALL_TEAMS.add(new CompetitiveTeam(45, "Momentum Master", 10, 15, new CompetitiveTeam.PokemonConfig[] { new CompetitiveTeam.PokemonConfig("rotom_wash", "bold", "levitate", SPECIAL_IVS, new int[] { 252, 0, 252, 0, 4, 0 }, new String[] { "hydropump", "voltswitch", "willowisp", "painsplit" }, "cobblemon:sitrus_berry", "electric"), new CompetitiveTeam.PokemonConfig("scizor", "adamant", "technician", PHYSICAL_IVS, new int[] { 252, 252, 0, 0, 4, 0 }, new String[] { "bulletpunch", "uturn", "knockoff", "superpower" }, "cobblemon:choice_band", "steel"), new CompetitiveTeam.PokemonConfig("gliscor", "careful", "poisonheal", PHYSICAL_IVS, new int[] { 252, 0, 184, 0, 72, 0 }, new String[] { "earthquake", "protect", "toxic", "uturn" }, "cobblemon:toxic_orb", "ground"), new CompetitiveTeam.PokemonConfig("kilowattrel", "timid", "voltabsorb", SPECIAL_IVS, new int[] { 0, 0, 0, 252, 4, 252 }, new String[] { "hurricane", "thunderbolt", "voltswitch", "roost" }, "cobblemon:heavy_duty_boots", "flying"), new CompetitiveTeam.PokemonConfig("gardevoir", "timid", "trace", SPECIAL_IVS, new int[] { 0, 0, 0, 252, 4, 252 }, new String[] { "moonblast", "psychic", "focusblast", "calmmind" }, "cobblemon:life_orb", "fairy"), new CompetitiveTeam.PokemonConfig("slowking", "bold", "regenerator", SPECIAL_IVS, new int[] { 252, 0, 252, 0, 4, 0 }, new String[] { "scald", "futuresight", "slackoff", "teleport" }, "cobblemon:colbur_berry", "psychic") }false));
/*     */   }
/*     */ 
/*     */   
/*     */   public static class TeamResult
/*     */   {
/*     */     public final Pokemon[] team;
/*     */     public final String trainerName;
/*     */     
/*     */     public TeamResult(Pokemon[] team, String trainerName) {
/* 489 */       this.team = team;
/* 490 */       this.trainerName = trainerName;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\battletower\data\BattleTowerTeams.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */