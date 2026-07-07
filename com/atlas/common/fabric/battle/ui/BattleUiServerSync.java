/*     */ package com.atlas.common.fabric.battle.ui;
/*     */ 
/*     */ import com.cobblemon.mod.common.api.abilities.Ability;
/*     */ import com.cobblemon.mod.common.api.battles.model.PokemonBattle;
/*     */ import com.cobblemon.mod.common.api.battles.model.actor.BattleActor;
/*     */ import com.cobblemon.mod.common.api.battles.model.actor.EntityBackedBattleActor;
/*     */ import com.cobblemon.mod.common.api.moves.Move;
/*     */ import com.cobblemon.mod.common.api.net.NetworkPacket;
/*     */ import com.cobblemon.mod.common.api.pokemon.stats.Stat;
/*     */ import com.cobblemon.mod.common.battles.ActiveBattlePokemon;
/*     */ import com.cobblemon.mod.common.battles.pokemon.BattlePokemon;
/*     */ import com.cobblemon.mod.common.net.messages.client.battle.BattleHealthChangePacket;
/*     */ import com.cobblemon.mod.common.pokemon.Pokemon;
/*     */ import com.cobblemon.mod.common.pokemon.status.PersistentStatusContainer;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
/*     */ import net.minecraft.class_1309;
/*     */ import net.minecraft.class_1799;
/*     */ import net.minecraft.class_2561;
/*     */ import net.minecraft.class_3222;
/*     */ import net.minecraft.class_8710;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public final class BattleUiServerSync {
/*  37 */   private static final Logger LOGGER = LoggerFactory.getLogger("atlas-battle-ui");
/*  38 */   private static final Pattern UUID_PATTERN = Pattern.compile("[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}");
/*     */ 
/*     */   
/*  41 */   private static final Map<UUID, RevealedMoveState> REVEALED_MOVE_STATES = new HashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void syncBattle(PokemonBattle battle) {
/*  47 */     if (battle == null || battle.getEnded())
/*     */       return; 
/*  49 */     BattleUiNetwork.InfoPayload info = buildInfo(battle);
/*  50 */     Map<UUID, Map<String, Integer>> revealedMoves = parseRevealedMoves(battle);
/*  51 */     debug("syncBattle battle={} turn={} showdownMessages={} battleLog={} revealed={}", new Object[] { battle
/*  52 */           .getBattleId(), 
/*  53 */           Integer.valueOf(battle.getTurn()), 
/*  54 */           Integer.valueOf(battle.getShowdownMessages().size()), 
/*  55 */           Integer.valueOf(battle.getBattleLog().size()), 
/*  56 */           revealedSummary(revealedMoves) });
/*  57 */     for (class_3222 viewer : viewers(battle)) {
/*  58 */       send(viewer, info);
/*  59 */       for (BattleActor actor : battle.getActors()) {
/*  60 */         send(viewer, buildTeam(battle, actor, viewer, revealedMoves));
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void clear(PokemonBattle battle) {
/*  66 */     if (battle == null)
/*  67 */       return;  REVEALED_MOVE_STATES.remove(battle.getBattleId());
/*  68 */     BattleUiNetwork.ClearPayload payload = new BattleUiNetwork.ClearPayload(battle.getBattleId());
/*  69 */     for (class_3222 viewer : viewers(battle))
/*  70 */       send(viewer, payload); 
/*     */   }
/*     */   public static void syncTurnTimer(BattleActor actor, int remainingSeconds) {
/*     */     EntityBackedBattleActor<?> entityBacked;
/*     */     class_3222 player;
/*  75 */     if (actor == null || actor.getBattle() == null)
/*  76 */       return;  if (actor instanceof EntityBackedBattleActor) { entityBacked = (EntityBackedBattleActor)actor; } else { return; }
/*  77 */      class_1309 class_1309 = entityBacked.getEntity(); if (class_1309 instanceof class_3222) { player = (class_3222)class_1309; }
/*     */     else { return; }
/*  79 */      send(player, new BattleUiNetwork.TimerPayload(actor
/*  80 */           .getBattle().getBattleId(), 
/*  81 */           Math.max(0, remainingSeconds)));
/*     */     
/*  83 */     send(player, buildInfo(actor.getBattle(), Math.max(0, remainingSeconds)));
/*     */   }
/*     */   public static void syncHealthChange(PokemonBattle battle, NetworkPacket<?> packet) {
/*     */     BattleHealthChangePacket healthPacket;
/*  87 */     if (battle != null && !battle.getEnded() && packet instanceof BattleHealthChangePacket) { healthPacket = (BattleHealthChangePacket)packet; }
/*     */     else { return; }
/*  89 */      BattlePokemon target = findActivePokemonByPnx(battle, healthPacket.getPnx());
/*  90 */     if (target == null) {
/*  91 */       debug("healthChange unmatched battle={} pnx={} newHealth={} newMaxHealth={}", new Object[] { battle
/*  92 */             .getBattleId(), healthPacket.getPnx(), Float.valueOf(healthPacket.getNewHealth()), healthPacket.getNewMaxHealth() });
/*     */       
/*     */       return;
/*     */     } 
/*  96 */     float maxHp = Math.max(1.0F, (healthPacket.getNewMaxHealth() == null) ? target.getMaxHealth() : healthPacket.getNewMaxHealth().floatValue());
/*  97 */     float hp = healthPacket.getNewHealth();
/*  98 */     if (healthPacket.getNewMaxHealth() == null && hp <= 1.0F) {
/*  99 */       hp *= maxHp;
/*     */     }
/* 101 */     hp = Math.max(0.0F, Math.min(hp, maxHp));
/*     */ 
/*     */ 
/*     */     
/* 105 */     BattleUiNetwork.HealthPayload payload = new BattleUiNetwork.HealthPayload(battle.getBattleId(), target.getUuid(), hp, maxHp, true);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 111 */     debug("healthChange battle={} pnx={} pokemon={} hp={}/{} sourceMax={}", new Object[] { battle
/* 112 */           .getBattleId(), healthPacket.getPnx(), target.getUuid(), Float.valueOf(hp), Float.valueOf(maxHp), healthPacket.getNewMaxHealth() });
/* 113 */     for (class_3222 viewer : viewers(battle)) {
/* 114 */       send(viewer, payload);
/*     */     }
/*     */   }
/*     */   
/*     */   private static BattlePokemon findActivePokemonByPnx(PokemonBattle battle, String pnx) {
/* 119 */     if (pnx == null || pnx.isBlank()) return null; 
/* 120 */     for (BattleActor actor : battle.getActors()) {
/* 121 */       for (ActiveBattlePokemon activePokemon : actor.getActivePokemon()) {
/* 122 */         if (activePokemon == null || !pnx.equals(activePokemon.getPNX()))
/* 123 */           continue;  return activePokemon.getBattlePokemon();
/*     */       } 
/*     */     } 
/* 126 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private static BattleUiNetwork.TeamPayload buildTeam(PokemonBattle battle, BattleActor actor, class_3222 viewer, Map<UUID, Map<String, Integer>> revealedMoves) {
/* 131 */     boolean sideOne = Arrays.<BattleActor>asList(battle.getSide1().getActors()).contains(actor);
/* 132 */     boolean revealFullMoves = actor.isForPlayer(viewer);
/* 133 */     List<BattleUiNetwork.PokemonEntry> entries = new ArrayList<>();
/* 134 */     for (BattlePokemon battlePokemon : actor.getPokemonList()) {
/* 135 */       if (battlePokemon != null) entries.add(buildPokemon(actor, battlePokemon, revealFullMoves, revealedMoves));
/*     */     
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 141 */     BattleUiNetwork.TeamPayload payload = new BattleUiNetwork.TeamPayload(battle.getBattleId(), actor.getUuid(), sideOne, safe((class_2561)actor.getName()), entries);
/*     */ 
/*     */     
/* 144 */     debug("teamPayload battle={} viewer={} actor={} actorName='{}' sideOne={} revealFullMoves={} pokemonMoves={}", new Object[] { battle
/* 145 */           .getBattleId(), viewer
/* 146 */           .method_5477().getString(), actor
/* 147 */           .getUuid(), payload
/* 148 */           .actorName(), 
/* 149 */           Boolean.valueOf(sideOne), 
/* 150 */           Boolean.valueOf(revealFullMoves), 
/* 151 */           pokemonMoveSummary(entries) });
/* 152 */     return payload;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static BattleUiNetwork.PokemonEntry buildPokemon(BattleActor actor, BattlePokemon battlePokemon, boolean revealFullMoves, Map<UUID, Map<String, Integer>> revealedMoves) {
/* 158 */     Pokemon pokemon = battlePokemon.getEffectedPokemon();
/* 159 */     String displayName = cleanPokemonName(safe((class_2561)pokemon.getDisplayName(false)));
/* 160 */     String species = pokemon.getSpecies().getTranslatedName().getString();
/* 161 */     String form = (pokemon.getForm() == null) ? "" : pokemon.getForm().getName();
/* 162 */     float hp = battlePokemon.getHealth();
/* 163 */     float maxHp = Math.max(1, battlePokemon.getMaxHealth());
/* 164 */     boolean active = isActive(actor, battlePokemon);
/* 165 */     boolean fainted = (pokemon.isFainted() || hp <= 0.0F);
/*     */ 
/*     */     
/* 168 */     List<BattleUiNetwork.MoveEntry> moves = revealFullMoves ? fullMoves(battlePokemon, revealedMoves) : revealedMoves(battlePokemon, revealedMoves);
/*     */     
/* 170 */     List<BattleUiNetwork.StatEntry> boosts = new ArrayList<>();
/* 171 */     for (Map.Entry<Stat, Integer> entry : (Iterable<Map.Entry<Stat, Integer>>)battlePokemon.getStatChanges().entrySet()) {
/* 172 */       int stage = (entry.getValue() == null) ? 0 : ((Integer)entry.getValue()).intValue();
/* 173 */       if (stage == 0)
/* 174 */         continue;  boosts.add(new BattleUiNetwork.StatEntry(((Stat)entry
/* 175 */             .getKey()).getShowdownId(), ((Stat)entry
/* 176 */             .getKey()).getDisplayName().getString(), stage));
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 181 */     return new BattleUiNetwork.PokemonEntry(battlePokemon
/* 182 */         .getUuid(), displayName, species, pokemon
/*     */ 
/*     */         
/* 185 */         .getSpecies().getResourceIdentifier().toString(), form, pokemon
/*     */         
/* 187 */         .getLevel(), pokemon
/* 188 */         .getGender().name(), 
/* 189 */         (pokemon.getPrimaryType() == null) ? "" : pokemon.getPrimaryType().getName(), 
/* 190 */         (pokemon.getSecondaryType() == null) ? "" : pokemon.getSecondaryType().getName(), hp, maxHp, true, 
/*     */ 
/*     */ 
/*     */         
/* 194 */         statusName(pokemon.getStatus()), 
/* 195 */         abilityName(pokemon.getAbility()), 
/* 196 */         heldItemName(pokemon.heldItem()), pokemon
/* 197 */         .getSpeed(), active, fainted, true, new ArrayList<>(pokemon
/*     */ 
/*     */ 
/*     */           
/* 201 */           .getAspects()), moves, boosts);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static BattleUiNetwork.InfoPayload buildInfo(PokemonBattle battle) {
/* 208 */     return buildInfo(battle, -1);
/*     */   }
/*     */   
/*     */   private static BattleUiNetwork.InfoPayload buildInfo(PokemonBattle battle, int timerRemainingSeconds) {
/* 212 */     EffectSnapshot snapshot = parseEffects(battle);
/* 213 */     int turn = Math.max(battle.getTurn(), snapshot.turn);
/* 214 */     return new BattleUiNetwork.InfoPayload(battle
/* 215 */         .getBattleId(), turn, snapshot.weather, snapshot.terrain, snapshot.room, new ArrayList<>(snapshot.sideOneEffects
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 220 */           .values()), new ArrayList<>(snapshot.sideTwoEffects
/* 221 */           .values()), timerRemainingSeconds);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static List<BattleUiNetwork.MoveEntry> fullMoves(BattlePokemon battlePokemon, Map<UUID, Map<String, Integer>> revealedMoves) {
/* 228 */     Map<String, Integer> seen = revealedMoves.getOrDefault(battlePokemon.getUuid(), Map.of());
/* 229 */     List<BattleUiNetwork.MoveEntry> moves = new ArrayList<>();
/* 230 */     for (Move move : battlePokemon.getMoveSet().getMoves()) {
/* 231 */       if (move == null)
/* 232 */         continue;  int maxPp = Math.max(0, move.getMaxPp());
/* 233 */       int currentPp = Math.max(0, move.getCurrentPp());
/* 234 */       String name = move.getDisplayName().getString();
/* 235 */       moves.add(new BattleUiNetwork.MoveEntry(name, move
/*     */             
/* 237 */             .getType().getName(), currentPp, maxPp, ((Integer)seen
/*     */ 
/*     */             
/* 240 */             .getOrDefault(normalize(name), Integer.valueOf(Math.max(0, maxPp - currentPp)))).intValue()));
/*     */     } 
/*     */     
/* 243 */     return moves;
/*     */   }
/*     */ 
/*     */   
/*     */   private static List<BattleUiNetwork.MoveEntry> revealedMoves(BattlePokemon battlePokemon, Map<UUID, Map<String, Integer>> revealedMoves) {
/* 248 */     Map<String, Integer> seen = revealedMoves.getOrDefault(battlePokemon.getUuid(), Map.of());
/* 249 */     List<BattleUiNetwork.MoveEntry> moves = new ArrayList<>();
/* 250 */     for (Map.Entry<String, Integer> entry : seen.entrySet()) {
/* 251 */       Move move = findMove(battlePokemon, entry.getKey());
/* 252 */       String name = (move == null) ? readableName(entry.getKey()) : move.getDisplayName().getString();
/* 253 */       String type = (move == null) ? "" : move.getType().getName();
/* 254 */       moves.add(new BattleUiNetwork.MoveEntry(name, type, 0, 0, ((Integer)entry.getValue()).intValue()));
/* 255 */       if (moves.size() >= 4)
/*     */         break; 
/* 257 */     }  return moves;
/*     */   }
/*     */   
/*     */   private static Move findMove(BattlePokemon battlePokemon, String normalizedMove) {
/* 261 */     for (Move move : battlePokemon.getMoveSet().getMoves()) {
/* 262 */       if (move != null && normalize(move.getDisplayName().getString()).equals(normalizedMove)) {
/* 263 */         return move;
/*     */       }
/*     */     } 
/* 266 */     return null;
/*     */   }
/*     */   
/*     */   private static Map<UUID, Map<String, Integer>> parseRevealedMoves(PokemonBattle battle) {
/* 270 */     Map<ActorPokemonName, UUID> pokemonByPublicName = pokemonNameIndex(battle);
/* 271 */     RevealedMoveState state = REVEALED_MOVE_STATES.computeIfAbsent(battle.getBattleId(), ignored -> new RevealedMoveState());
/* 272 */     int currentTurn = 0;
/* 273 */     for (String line : battleLines(battle)) {
/* 274 */       if (line == null || line.isBlank())
/* 275 */         continue;  for (ProtocolLine protocolLine : protocolLines(line)) {
/* 276 */         String[] parts = protocolLine.parts();
/* 277 */         if (parts.length >= 2 && "turn".equals(parts[0])) {
/* 278 */           currentTurn = parseInt(parts[1], currentTurn);
/*     */           continue;
/*     */         } 
/* 281 */         if (parts.length < 3 || !"move".equals(parts[0])) {
/*     */           continue;
/*     */         }
/*     */         
/* 285 */         String eventKey = "" + currentTurn + "|" + currentTurn;
/* 286 */         if (!state.seenMoveEvents.add(eventKey)) {
/*     */           continue;
/*     */         }
/*     */         
/* 290 */         debug("moveLine candidate battle={} turn={} raw='{}' parts={}", new Object[] { battle
/* 291 */               .getBattleId(), Integer.valueOf(currentTurn), protocolLine.raw(), Arrays.toString((Object[])parts) });
/* 292 */         String showdownSlot = showdownSlot(parts[1]);
/* 293 */         String actorShowdownId = showdownActorId(parts[1]);
/* 294 */         String pokemonName = normalize(label(parts[1]));
/* 295 */         String moveName = normalize(label(parts[2]));
/* 296 */         if (actorShowdownId.isBlank() || pokemonName.isBlank() || moveName.isBlank()) {
/* 297 */           debug("moveLine skipped battle={} raw='{}' reason=blank actorId='{}' pokemon='{}' move='{}'", new Object[] { battle
/* 298 */                 .getBattleId(), protocolLine.raw(), actorShowdownId, pokemonName, moveName });
/*     */           
/*     */           continue;
/*     */         } 
/* 302 */         UUID pokemonId = findBattlePokemonId(battle, showdownSlot, parts[1], pokemonByPublicName);
/* 303 */         if (pokemonId == null) {
/* 304 */           debug("moveLine unmatched battle={} raw='{}' slot='{}' actorId='{}' pokemonLabel='{}' move='{}'", new Object[] { battle
/* 305 */                 .getBattleId(), protocolLine.raw(), showdownSlot, actorShowdownId, label(parts[1]), label(parts[2]) });
/*     */           continue;
/*     */         } 
/* 308 */         debug("moveLine matched battle={} turn={} pokemonId={} slot='{}' actorId='{}' pokemonLabel='{}' move='{}'", new Object[] { battle
/* 309 */               .getBattleId(), Integer.valueOf(currentTurn), pokemonId, showdownSlot, actorShowdownId, label(parts[1]), label(parts[2]) });
/* 310 */         ((Map<String, Integer>)state.revealed.computeIfAbsent(pokemonId, ignored -> new LinkedHashMap<>()))
/* 311 */           .merge(moveName, Integer.valueOf(1), Integer::sum);
/*     */       } 
/*     */     } 
/* 314 */     debug("revealedMoves parsed battle={} events={} revealed={}", new Object[] { battle
/* 315 */           .getBattleId(), Integer.valueOf(state.seenMoveEvents.size()), revealedSummary(state.revealed) });
/* 316 */     return state.snapshot();
/*     */   }
/*     */   
/*     */   private static List<String> battleLines(PokemonBattle battle) {
/* 320 */     List<String> lines = new ArrayList<>();
/* 321 */     lines.addAll(battle.getShowdownMessages());
/* 322 */     lines.addAll(battle.getBattleLog());
/* 323 */     return lines;
/*     */   }
/*     */ 
/*     */   
/*     */   private static UUID findBattlePokemonId(PokemonBattle battle, String showdownSlot, String rawPokemon, Map<ActorPokemonName, UUID> pokemonByPublicName) {
/* 328 */     String actorShowdownId = showdownActorId(rawPokemon);
/* 329 */     String pokemonName = normalize(label(rawPokemon));
/*     */     
/* 331 */     UUID uuid = uuidFromPokemonIdent(rawPokemon);
/* 332 */     if (uuid != null) {
/* 333 */       for (BattleActor actor : battle.getActors()) {
/* 334 */         for (BattlePokemon battlePokemon : actor.getPokemonList()) {
/* 335 */           if (battlePokemon != null && uuid.equals(battlePokemon.getUuid())) {
/* 336 */             debug("movePokemon uuidMatch battle={} raw='{}' uuid={}", new Object[] { battle
/* 337 */                   .getBattleId(), rawPokemon, uuid });
/* 338 */             return uuid;
/*     */           } 
/*     */         } 
/*     */       } 
/* 342 */       debug("movePokemon uuidMissing battle={} raw='{}' uuid={}", new Object[] { battle.getBattleId(), rawPokemon, uuid });
/*     */     } 
/*     */     
/* 345 */     if (!showdownSlot.isBlank()) {
/*     */       try {
/* 347 */         BattlePokemon direct = battle.getBattlePokemon(showdownSlot, label(rawPokemon));
/* 348 */         if (direct != null) {
/* 349 */           debug("movePokemon directMatch battle={} slot='{}' raw='{}' uuid={}", new Object[] { battle
/* 350 */                 .getBattleId(), showdownSlot, rawPokemon, direct.getUuid() });
/* 351 */           return direct.getUuid();
/*     */         } 
/* 353 */       } catch (Exception exception) {
/* 354 */         debug("movePokemon directLookupFailed battle={} slot='{}' raw='{}' reason={}", new Object[] { battle
/* 355 */               .getBattleId(), showdownSlot, rawPokemon, exception.toString() });
/*     */       } 
/*     */     }
/*     */     
/* 359 */     UUID fallback = pokemonByPublicName.get(new ActorPokemonName(actorShowdownId, pokemonName));
/* 360 */     debug("movePokemon aliasMatch battle={} actorId='{}' pokemon='{}' raw='{}' uuid={}", new Object[] { battle
/* 361 */           .getBattleId(), actorShowdownId, pokemonName, rawPokemon, fallback });
/* 362 */     return fallback;
/*     */   }
/*     */   
/*     */   private static UUID uuidFromPokemonIdent(String rawPokemon) {
/* 366 */     if (rawPokemon == null || rawPokemon.isBlank()) return null; 
/* 367 */     Matcher matcher = UUID_PATTERN.matcher(rawPokemon);
/* 368 */     if (!matcher.find()) return null; 
/*     */     try {
/* 370 */       return UUID.fromString(matcher.group());
/* 371 */     } catch (Exception ignored) {
/* 372 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static String[] protocolParts(String line) {
/* 377 */     String[] raw = line.split("\\|");
/* 378 */     if (raw.length > 0 && raw[0].isBlank()) {
/* 379 */       return Arrays.<String>copyOfRange(raw, 1, raw.length);
/*     */     }
/* 381 */     return raw;
/*     */   }
/*     */   
/*     */   private static List<ProtocolLine> protocolLines(String line) {
/* 385 */     List<ProtocolLine> rows = new ArrayList<>();
/* 386 */     for (String row : line.split("\\R")) {
/* 387 */       String trimmed = row.trim();
/* 388 */       if (!trimmed.isEmpty()) {
/* 389 */         String[] parts = protocolParts(trimmed);
/* 390 */         if (parts.length > 0) {
/* 391 */           rows.add(new ProtocolLine(trimmed, parts));
/*     */         }
/*     */       } 
/*     */     } 
/* 395 */     if (rows.isEmpty()) {
/* 396 */       String[] parts = protocolParts(line.trim());
/* 397 */       if (parts.length > 0) {
/* 398 */         rows.add(new ProtocolLine(line.trim(), parts));
/*     */       }
/*     */     } 
/*     */     
/* 402 */     return rows;
/*     */   }
/*     */   
/*     */   private static Map<ActorPokemonName, UUID> pokemonNameIndex(PokemonBattle battle) {
/* 406 */     Map<ActorPokemonName, UUID> index = new LinkedHashMap<>();
/* 407 */     for (BattleActor actor : battle.getActors()) {
/* 408 */       String actorId = actor.getShowdownId();
/* 409 */       if (actorId == null || actorId.isBlank())
/* 410 */         continue;  for (BattlePokemon battlePokemon : actor.getPokemonList()) {
/* 411 */         if (battlePokemon == null)
/* 412 */           continue;  Pokemon pokemon = battlePokemon.getEffectedPokemon();
/* 413 */         addPokemonAlias(index, actorId, battlePokemon.getUuid(), safe((class_2561)pokemon.getDisplayName(false)));
/* 414 */         addPokemonAlias(index, actorId, battlePokemon.getUuid(), safe((class_2561)pokemon.getDisplayName(true)));
/* 415 */         addPokemonAlias(index, actorId, battlePokemon.getUuid(), safe((class_2561)battlePokemon.getName()));
/* 416 */         addPokemonAlias(index, actorId, battlePokemon.getUuid(), pokemon.getSpecies().getName());
/* 417 */         addPokemonAlias(index, actorId, battlePokemon.getUuid(), pokemon.getSpecies().getTranslatedName().getString());
/*     */       } 
/*     */     } 
/* 420 */     return index;
/*     */   }
/*     */   
/*     */   private static void addPokemonAlias(Map<ActorPokemonName, UUID> index, String actorId, UUID pokemonId, String name) {
/* 424 */     String cleaned = normalize(cleanPokemonName(name));
/* 425 */     if (!cleaned.isBlank()) {
/* 426 */       index.putIfAbsent(new ActorPokemonName(actorId, cleaned), pokemonId);
/*     */     }
/*     */   }
/*     */   
/*     */   private static String showdownActorId(String showdownPokemon) {
/* 431 */     if (showdownPokemon == null) return ""; 
/* 432 */     String slot = showdownSlot(showdownPokemon);
/* 433 */     if (slot.length() < 2 || slot.charAt(0) != 'p') return ""; 
/* 434 */     int end = 2;
/* 435 */     while (end < slot.length() && Character.isDigit(slot.charAt(end))) {
/* 436 */       end++;
/*     */     }
/* 438 */     return slot.substring(0, end);
/*     */   }
/*     */   
/*     */   private static String showdownSlot(String showdownPokemon) {
/* 442 */     if (showdownPokemon == null) return ""; 
/* 443 */     String cleaned = showdownPokemon.trim();
/* 444 */     int colon = cleaned.indexOf(':');
/* 445 */     return (colon >= 0) ? cleaned.substring(0, colon).trim() : cleaned;
/*     */   }
/*     */   
/*     */   private static EffectSnapshot parseEffects(PokemonBattle battle) {
/* 449 */     EffectSnapshot snapshot = new EffectSnapshot(Math.max(0, battle.getTurn()));
/* 450 */     int currentTurn = 0;
/* 451 */     for (String line : battle.getBattleLog()) {
/* 452 */       if (line == null || line.isBlank())
/* 453 */         continue;  String[] parts = line.split("\\|");
/* 454 */       if (parts.length < 2)
/*     */         continue; 
/* 456 */       String opcode = parts[1];
/* 457 */       if ("turn".equals(opcode) && parts.length > 2) {
/* 458 */         currentTurn = parseInt(parts[2], currentTurn);
/* 459 */         snapshot.turn = currentTurn;
/*     */         
/*     */         continue;
/*     */       } 
/* 463 */       int turnStarted = Math.max(1, currentTurn);
/* 464 */       if ("-weather".equals(opcode) && parts.length > 2) {
/* 465 */         String raw = parts[2];
/* 466 */         if ("none".equalsIgnoreCase(raw)) {
/* 467 */           snapshot.weather = none(); continue;
/*     */         } 
/* 469 */         snapshot.weather = effect(raw, turnStarted);
/*     */         
/*     */         continue;
/*     */       } 
/*     */       
/* 474 */       if ("-fieldstart".equals(opcode) && parts.length > 2) {
/* 475 */         BattleUiNetwork.EffectEntry effect = effect(parts[2], turnStarted);
/* 476 */         if (isTerrain(effect.id())) {
/* 477 */           snapshot.terrain = effect; continue;
/*     */         } 
/* 479 */         snapshot.room = effect;
/*     */         
/*     */         continue;
/*     */       } 
/*     */       
/* 484 */       if ("-fieldend".equals(opcode) && parts.length > 2) {
/* 485 */         BattleUiNetwork.EffectEntry effect = effect(parts[2], turnStarted);
/* 486 */         if (isTerrain(effect.id())) {
/* 487 */           snapshot.terrain = none(); continue;
/*     */         } 
/* 489 */         snapshot.room = none();
/*     */         
/*     */         continue;
/*     */       } 
/*     */       
/* 494 */       if ("-sidestart".equals(opcode) && parts.length > 3) {
/* 495 */         sideEffects(snapshot, parts[2]).put(effect(parts[3], turnStarted).id(), effect(parts[3], turnStarted));
/*     */         
/*     */         continue;
/*     */       } 
/* 499 */       if ("-sideend".equals(opcode) && parts.length > 3) {
/* 500 */         sideEffects(snapshot, parts[2]).remove(effect(parts[3], turnStarted).id());
/*     */       }
/*     */     } 
/* 503 */     return snapshot;
/*     */   }
/*     */   
/*     */   private static Map<String, BattleUiNetwork.EffectEntry> sideEffects(EffectSnapshot snapshot, String target) {
/* 507 */     return (target != null && target.toLowerCase(Locale.ROOT).startsWith("p2")) ? 
/* 508 */       snapshot.sideTwoEffects : 
/* 509 */       snapshot.sideOneEffects;
/*     */   }
/*     */   
/*     */   private static BattleUiNetwork.EffectEntry effect(String raw, int turnStarted) {
/* 513 */     String label = label(raw);
/* 514 */     return new BattleUiNetwork.EffectEntry(normalize(label), label, turnStarted);
/*     */   }
/*     */   
/*     */   private static BattleUiNetwork.EffectEntry none() {
/* 518 */     return new BattleUiNetwork.EffectEntry("", "", 0);
/*     */   }
/*     */   
/*     */   private static String label(String raw) {
/* 522 */     if (raw == null) return ""; 
/* 523 */     String cleaned = raw.trim();
/* 524 */     int colon = cleaned.indexOf(':');
/* 525 */     if (colon >= 0 && colon + 1 < cleaned.length()) cleaned = cleaned.substring(colon + 1).trim(); 
/* 526 */     cleaned = cleaned.replace('_', ' ').replace('-', ' ');
/* 527 */     return cleaned.isBlank() ? raw : cleaned;
/*     */   }
/*     */   
/*     */   private static String normalize(String label) {
/* 531 */     return label.toLowerCase(Locale.ROOT).replaceAll("[^a-z0-9]", "");
/*     */   }
/*     */   
/*     */   private static boolean isTerrain(String id) {
/* 535 */     return id.endsWith("terrain");
/*     */   }
/*     */   
/*     */   private static int parseInt(String value, int fallback) {
/*     */     try {
/* 540 */       return Integer.parseInt(value.trim());
/* 541 */     } catch (Exception ignored) {
/* 542 */       return fallback;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static boolean isActive(BattleActor actor, BattlePokemon battlePokemon) {
/* 547 */     for (ActiveBattlePokemon activePokemon : actor.getActivePokemon()) {
/* 548 */       if (activePokemon != null && activePokemon
/* 549 */         .getBattlePokemon() != null && activePokemon
/* 550 */         .getBattlePokemon().getUuid().equals(battlePokemon.getUuid())) {
/* 551 */         return true;
/*     */       }
/*     */     } 
/* 554 */     return false;
/*     */   }
/*     */   
/*     */   private static String statusName(PersistentStatusContainer status) {
/* 558 */     if (status == null || status.getStatus() == null) return ""; 
/* 559 */     return status.getStatus().getShowdownName();
/*     */   }
/*     */   
/*     */   private static String heldItemName(class_1799 heldItem) {
/* 563 */     if (heldItem == null || heldItem.method_7960()) return ""; 
/* 564 */     return heldItem.method_7964().getString();
/*     */   }
/*     */   
/*     */   private static String abilityName(Ability ability) {
/* 568 */     if (ability == null) return ""; 
/* 569 */     String key = ability.getDisplayName();
/* 570 */     if (key == null || key.isBlank()) return fallbackAbilityName(ability.getName()); 
/* 571 */     String translated = class_2561.method_43471(key).getString();
/* 572 */     return translated.equals(key) ? fallbackAbilityName(ability.getName()) : translated;
/*     */   }
/*     */   
/*     */   private static String fallbackAbilityName(String raw) {
/* 576 */     if (raw == null || raw.isBlank()) return "?"; 
/* 577 */     return readableName(raw);
/*     */   }
/*     */   
/*     */   private static String readableName(String raw) {
/* 581 */     if (raw == null || raw.isBlank()) return "?"; 
/* 582 */     String cleaned = raw;
/* 583 */     int dot = cleaned.lastIndexOf('.');
/* 584 */     if (dot >= 0 && dot + 1 < cleaned.length()) cleaned = cleaned.substring(dot + 1); 
/* 585 */     cleaned = cleaned.replace('_', ' ').replace('-', ' ');
/* 586 */     StringBuilder name = new StringBuilder(cleaned.length());
/* 587 */     boolean upper = true;
/* 588 */     for (int i = 0; i < cleaned.length(); i++) {
/* 589 */       char c = cleaned.charAt(i);
/* 590 */       if (Character.isWhitespace(c)) {
/* 591 */         name.append(c);
/* 592 */         upper = true;
/*     */       } else {
/* 594 */         name.append(upper ? Character.toUpperCase(c) : c);
/* 595 */         upper = false;
/*     */       } 
/*     */     } 
/* 598 */     return name.toString();
/*     */   }
/*     */   
/*     */   private static String safe(class_2561 text) {
/* 602 */     return (text == null) ? "" : text.getString();
/*     */   }
/*     */   
/*     */   private static String cleanPokemonName(String name) {
/* 606 */     if (name == null) return ""; 
/* 607 */     int possessive = name.indexOf("'s ");
/* 608 */     if (possessive >= 0 && possessive + 3 < name.length()) {
/* 609 */       return name.substring(possessive + 3).trim();
/*     */     }
/* 611 */     return name;
/*     */   }
/*     */   
/*     */   private static Set<class_3222> viewers(PokemonBattle battle) {
/* 615 */     Set<class_3222> players = new LinkedHashSet<>(battle.getPlayers());
/* 616 */     class_3222 first = players.stream().findFirst().orElse(null);
/* 617 */     if (first == null) return players; 
/* 618 */     for (UUID spectatorId : battle.getSpectators()) {
/* 619 */       class_3222 spectator = first.method_5682().method_3760().method_14602(spectatorId);
/* 620 */       if (spectator != null) players.add(spectator); 
/*     */     } 
/* 622 */     return players;
/*     */   }
/*     */   
/*     */   private static void send(class_3222 player, class_8710 payload) {
/* 626 */     if (player == null)
/*     */       return;  try {
/* 628 */       if (!ServerPlayNetworking.canSend(player, payload.method_56479())) {
/* 629 */         debug("payloadDropped player={} payload={} reason=client_cannot_receive", new Object[] { player
/* 630 */               .method_5477().getString(), payload.method_56479().comp_2242() });
/*     */         return;
/*     */       } 
/* 633 */       ServerPlayNetworking.send(player, payload);
/* 634 */     } catch (Exception ignored) {
/* 635 */       debug("payloadDropped player={} payload={} reason=exception {}", new Object[] { player
/* 636 */             .method_5477().getString(), payload.method_56479().comp_2242(), ignored.toString() });
/*     */     } 
/*     */   }
/*     */   
/*     */   private static final class EffectSnapshot {
/*     */     private int turn;
/* 642 */     private BattleUiNetwork.EffectEntry weather = BattleUiServerSync.none();
/* 643 */     private BattleUiNetwork.EffectEntry terrain = BattleUiServerSync.none();
/* 644 */     private BattleUiNetwork.EffectEntry room = BattleUiServerSync.none();
/* 645 */     private final Map<String, BattleUiNetwork.EffectEntry> sideOneEffects = new LinkedHashMap<>();
/* 646 */     private final Map<String, BattleUiNetwork.EffectEntry> sideTwoEffects = new LinkedHashMap<>();
/*     */     
/*     */     private EffectSnapshot(int turn) {
/* 649 */       this.turn = turn;
/*     */     } }
/*     */   private static final class ActorPokemonName extends Record { private final String actorShowdownId; private final String pokemonName;
/*     */     
/* 653 */     private ActorPokemonName(String actorShowdownId, String pokemonName) { this.actorShowdownId = actorShowdownId; this.pokemonName = pokemonName; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/battle/ui/BattleUiServerSync$ActorPokemonName;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #653	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/* 653 */       //   0	7	0	this	Lcom/atlas/common/fabric/battle/ui/BattleUiServerSync$ActorPokemonName; } public String actorShowdownId() { return this.actorShowdownId; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/battle/ui/BattleUiServerSync$ActorPokemonName;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #653	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/battle/ui/BattleUiServerSync$ActorPokemonName; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/battle/ui/BattleUiServerSync$ActorPokemonName;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #653	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lcom/atlas/common/fabric/battle/ui/BattleUiServerSync$ActorPokemonName;
/* 653 */       //   0	8	1	o	Ljava/lang/Object; } public String pokemonName() { return this.pokemonName; }
/*     */      }
/*     */   private static final class ProtocolLine extends Record { private final String raw; private final String[] parts;
/* 656 */     private ProtocolLine(String raw, String[] parts) { this.raw = raw; this.parts = parts; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/battle/ui/BattleUiServerSync$ProtocolLine;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #656	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/battle/ui/BattleUiServerSync$ProtocolLine; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/battle/ui/BattleUiServerSync$ProtocolLine;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #656	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/battle/ui/BattleUiServerSync$ProtocolLine; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/battle/ui/BattleUiServerSync$ProtocolLine;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #656	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lcom/atlas/common/fabric/battle/ui/BattleUiServerSync$ProtocolLine;
/* 656 */       //   0	8	1	o	Ljava/lang/Object; } public String raw() { return this.raw; } public String[] parts() { return this.parts; }
/*     */      }
/*     */   
/*     */   private static final class RevealedMoveState {
/* 660 */     private final Set<String> seenMoveEvents = new LinkedHashSet<>();
/* 661 */     private final Map<UUID, Map<String, Integer>> revealed = new LinkedHashMap<>();
/*     */     
/*     */     private Map<UUID, Map<String, Integer>> snapshot() {
/* 664 */       Map<UUID, Map<String, Integer>> copy = new LinkedHashMap<>();
/* 665 */       for (Map.Entry<UUID, Map<String, Integer>> entry : this.revealed.entrySet()) {
/* 666 */         copy.put(entry.getKey(), new LinkedHashMap<>(entry.getValue()));
/*     */       }
/* 668 */       return copy;
/*     */     }
/*     */   }
/*     */   
/*     */   private static boolean debugEnabled() {
/* 673 */     String property = System.getProperty("atlas.battle-ui.debug");
/* 674 */     String env = System.getenv("ATLAS_BATTLE_UI_DEBUG");
/* 675 */     if (property != null) return Boolean.parseBoolean(property); 
/* 676 */     if (env != null) return Boolean.parseBoolean(env); 
/* 677 */     return false;
/*     */   }
/*     */   
/*     */   private static void debug(String message, Object... args) {
/* 681 */     if (debugEnabled()) LOGGER.info("[BattleUI] " + message, args); 
/*     */   }
/*     */   
/*     */   private static String revealedSummary(Map<UUID, Map<String, Integer>> revealedMoves) {
/* 685 */     if (revealedMoves.isEmpty()) return "{}"; 
/* 686 */     StringBuilder builder = new StringBuilder("{");
/* 687 */     boolean firstPokemon = true;
/* 688 */     for (Map.Entry<UUID, Map<String, Integer>> entry : revealedMoves.entrySet()) {
/* 689 */       if (!firstPokemon) builder.append(", "); 
/* 690 */       firstPokemon = false;
/* 691 */       builder.append(entry.getKey()).append("=").append(entry.getValue());
/*     */     } 
/* 693 */     return builder.append("}").toString();
/*     */   }
/*     */   
/*     */   private static String pokemonMoveSummary(List<BattleUiNetwork.PokemonEntry> pokemon) {
/* 697 */     if (pokemon.isEmpty()) return "[]"; 
/* 698 */     StringBuilder builder = new StringBuilder("[");
/* 699 */     for (int i = 0; i < pokemon.size(); i++) {
/* 700 */       BattleUiNetwork.PokemonEntry entry = pokemon.get(i);
/* 701 */       if (i > 0) builder.append(", "); 
/* 702 */       builder.append(entry.displayName()).append("(").append(entry.pokemonId()).append(")=");
/* 703 */       if (entry.moves().isEmpty()) {
/* 704 */         builder.append("?");
/*     */       } else {
/*     */         
/* 707 */         builder.append("[");
/* 708 */         for (int moveIndex = 0; moveIndex < entry.moves().size(); moveIndex++) {
/* 709 */           BattleUiNetwork.MoveEntry move = entry.moves().get(moveIndex);
/* 710 */           if (moveIndex > 0) builder.append(", "); 
/* 711 */           builder.append(move.name()).append("x").append(move.timesUsed());
/*     */         } 
/* 713 */         builder.append("]");
/*     */       } 
/* 715 */     }  return builder.append("]").toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\battl\\ui\BattleUiServerSync.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */