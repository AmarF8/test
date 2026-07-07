/*     */ package com.atlas.common.fabric.battle.ui;
/*     */ 
/*     */ import com.cobblemon.mod.common.api.pokemon.PokemonProperties;
/*     */ import com.cobblemon.mod.common.api.pokemon.stats.Stat;
/*     */ import com.cobblemon.mod.common.client.CobblemonClient;
/*     */ import com.cobblemon.mod.common.client.battle.ActiveClientBattlePokemon;
/*     */ import com.cobblemon.mod.common.client.battle.ClientBattle;
/*     */ import com.cobblemon.mod.common.client.battle.ClientBattleActor;
/*     */ import com.cobblemon.mod.common.client.battle.ClientBattlePokemon;
/*     */ import com.cobblemon.mod.common.client.battle.ClientBattleSide;
/*     */ import com.cobblemon.mod.common.client.battle.SingleActionRequest;
/*     */ import com.cobblemon.mod.common.pokemon.Gender;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
/*     */ import net.fabricmc.api.EnvType;
/*     */ import net.fabricmc.api.Environment;
/*     */ import net.minecraft.class_2561;
/*     */ import net.minecraft.class_2960;
/*     */ 
/*     */ 
/*     */ 
/*     */ @Environment(EnvType.CLIENT)
/*     */ public final class BattleUiClientBattleSync
/*     */ {
/*     */   public static void syncActiveSlotsFromOverlayState() {
/*  32 */     ClientBattle battle = CobblemonClient.INSTANCE.getBattle();
/*  33 */     if (battle == null)
/*     */       return; 
/*  35 */     Optional<BattleUiClientState.BattleState> currentState = BattleUiClientState.currentBattle();
/*  36 */     if (currentState.isEmpty())
/*     */       return; 
/*  38 */     BattleUiClientState.BattleState state = currentState.get();
/*  39 */     if (!battle.getBattleId().equals(state.battleId()))
/*     */       return; 
/*  41 */     for (ClientBattleSide side : battle.getSides()) {
/*  42 */       for (ClientBattleActor actor : side.getActors()) {
/*  43 */         syncActor(actor, state.team(actor.getUuid()));
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void syncActor(ClientBattleActor actor, BattleUiNetwork.TeamPayload team) {
/*  49 */     if (actor == null || team == null) {
/*     */       return;
/*     */     }
/*     */     
/*  53 */     List<BattleUiNetwork.PokemonEntry> activeEntries = team.pokemon().stream().filter(entry -> (entry.active() && !entry.fainted())).toList();
/*  54 */     List<ActiveClientBattlePokemon> activeSlots = actor.getActivePokemon();
/*  55 */     Map<UUID, BattleUiNetwork.PokemonEntry> activeById = new HashMap<>();
/*  56 */     for (BattleUiNetwork.PokemonEntry entry : activeEntries) {
/*  57 */       activeById.put(entry.pokemonId(), entry);
/*     */     }
/*     */     
/*  60 */     Set<UUID> matched = new HashSet<>();
/*  61 */     for (ActiveClientBattlePokemon slot : activeSlots) {
/*  62 */       if (slot == null || slot.getBattlePokemon() == null)
/*  63 */         continue;  BattleUiNetwork.PokemonEntry entry = activeById.get(slot.getBattlePokemon().getUuid());
/*  64 */       if (entry == null)
/*  65 */         continue;  updateExisting(slot.getBattlePokemon(), entry);
/*  66 */       matched.add(entry.pokemonId());
/*     */     } 
/*     */     
/*  69 */     int nextEntry = 0;
/*  70 */     for (ActiveClientBattlePokemon slot : activeSlots) {
/*  71 */       if (slot == null || (slot.getBattlePokemon() != null && matched.contains(slot.getBattlePokemon().getUuid()))) {
/*     */         continue;
/*     */       }
/*     */       
/*  75 */       BattleUiNetwork.PokemonEntry entry = null;
/*  76 */       while (nextEntry < activeEntries.size()) {
/*  77 */         BattleUiNetwork.PokemonEntry candidate = activeEntries.get(nextEntry++);
/*  78 */         if (!matched.contains(candidate.pokemonId())) {
/*  79 */           entry = candidate;
/*     */           break;
/*     */         } 
/*     */       } 
/*  83 */       if (entry == null) {
/*  84 */         markMissingSlotFainted(slot);
/*     */         continue;
/*     */       } 
/*  87 */       slot.setBattlePokemon(createClientPokemon(actor, entry));
/*  88 */       matched.add(entry.pokemonId());
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void markMissingSlotFainted(ActiveClientBattlePokemon slot) {
/*  93 */     ClientBattlePokemon pokemon = slot.getBattlePokemon();
/*  94 */     if (pokemon == null)
/*  95 */       return;  pokemon.setHpValue(0.0F);
/*  96 */     pokemon.setMaxHp(Math.max(1.0F, pokemon.getMaxHp()));
/*  97 */     pokemon.setHpFlat(false);
/*     */   }
/*     */   
/*     */   public static boolean hasFaintedActivePokemon(SingleActionRequest request) {
/* 101 */     if (request == null || request.getActivePokemon() == null) return false; 
/* 102 */     ClientBattlePokemon activePokemon = request.getActivePokemon().getBattlePokemon();
/* 103 */     return (activePokemon == null || activePokemon.getHpValue() <= 0.0F);
/*     */   }
/*     */   
/*     */   private static void updateExisting(ClientBattlePokemon pokemon, BattleUiNetwork.PokemonEntry entry) {
/* 107 */     pokemon.setDisplayName(class_2561.method_43470(entry.displayName()));
/* 108 */     pokemon.setHpValue(entry.hp());
/* 109 */     pokemon.setMaxHp(entry.maxHp());
/* 110 */     pokemon.setHpFlat(entry.flatHp());
/* 111 */     pokemon.setStatChanges(statChanges(entry));
/* 112 */     pokemon.updateAspects(new HashSet<>(entry.aspects()));
/*     */   }
/*     */   
/*     */   private static ClientBattlePokemon createClientPokemon(ClientBattleActor actor, BattleUiNetwork.PokemonEntry entry) {
/* 116 */     PokemonProperties properties = properties(entry);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 126 */     ClientBattlePokemon pokemon = new ClientBattlePokemon(entry.pokemonId(), class_2561.method_43470(entry.displayName()), properties, new HashSet<>(entry.aspects()), entry.hp(), entry.maxHp(), entry.flatHp(), null, statChanges(entry));
/*     */     
/* 128 */     pokemon.setActor(actor);
/* 129 */     return pokemon;
/*     */   }
/*     */   
/*     */   private static PokemonProperties properties(BattleUiNetwork.PokemonEntry entry) {
/* 133 */     PokemonProperties properties = new PokemonProperties();
/* 134 */     properties.setSpecies(speciesProperty(entry.speciesId()));
/* 135 */     properties.setLevel(Integer.valueOf(entry.level()));
/* 136 */     properties.setAspects(new HashSet<>(entry.aspects()));
/* 137 */     properties.setGender(gender(entry.gender()));
/* 138 */     properties.setShiny(Boolean.valueOf(entry.aspects().contains("shiny")));
/* 139 */     if (entry.status() != null && !entry.status().isBlank()) {
/* 140 */       properties.setStatus(entry.status());
/*     */     }
/* 142 */     if (entry.form() != null && !entry.form().isBlank() && !"normal".equalsIgnoreCase(entry.form())) {
/* 143 */       properties.setForm(entry.form().toLowerCase(Locale.ROOT).replace(' ', '-'));
/*     */     }
/* 145 */     return properties;
/*     */   }
/*     */   
/*     */   private static String speciesProperty(String speciesId) {
/* 149 */     if (speciesId == null || speciesId.isBlank()) return ""; 
/*     */     try {
/* 151 */       class_2960 id = class_2960.method_60654(speciesId);
/* 152 */       return "cobblemon".equals(id.method_12836()) ? id.method_12832() : id.toString();
/* 153 */     } catch (RuntimeException ignored) {
/* 154 */       return speciesId;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static Gender gender(String value) {
/* 159 */     if (value == null || value.isBlank()) return Gender.GENDERLESS; 
/*     */     try {
/* 161 */       return Gender.valueOf(value);
/* 162 */     } catch (IllegalArgumentException ignored) {
/* 163 */       return Gender.GENDERLESS;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static Map<Stat, Integer> statChanges(BattleUiNetwork.PokemonEntry entry) {
/* 168 */     Map<Stat, Integer> stats = new HashMap<>();
/* 169 */     return stats;
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\battl\\ui\BattleUiClientBattleSync.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */