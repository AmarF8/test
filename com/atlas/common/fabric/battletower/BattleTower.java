/*     */ package com.atlas.common.fabric.battletower;
/*     */ import com.atlas.common.fabric.battletower.data.ActivePlayerTracker;
/*     */ import com.atlas.common.fabric.battletower.entity.BattleTowerTrainerEntity;
/*     */ import com.cobblemon.mod.common.api.Priority;
/*     */ import com.cobblemon.mod.common.api.events.CobblemonEvents;
/*     */ import com.cobblemon.mod.common.api.events.battles.BattleFledEvent;
/*     */ import com.cobblemon.mod.common.api.events.drops.LootDroppedEvent;
/*     */ import com.cobblemon.mod.common.api.events.pokemon.HeldItemEvent;
/*     */ import com.cobblemon.mod.common.api.events.pokemon.PokemonFaintedEvent;
/*     */ import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
/*     */ import com.cobblemon.mod.common.pokemon.Pokemon;
/*     */ import com.gitlab.srcmc.rctapi.api.RCTApi;
/*     */ import com.gitlab.srcmc.rctapi.api.battle.BattleState;
/*     */ import com.gitlab.srcmc.rctapi.api.events.Event;
/*     */ import com.gitlab.srcmc.rctapi.api.events.Events;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import kotlin.Unit;
/*     */ import net.minecraft.class_1297;
/*     */ import net.minecraft.class_1309;
/*     */ import net.minecraft.class_1799;
/*     */ import net.minecraft.class_3218;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class BattleTower {
/*  35 */   public static final Logger LOGGER = LoggerFactory.getLogger("cobblemon_battle_tower");
/*     */   
/*     */   public static final String MOD_ID = "cobblemon_battle_tower";
/*     */   
/*     */   private static RCTApi rctApi;
/*     */   
/*  41 */   private static final Set<UUID> battleTowerBattleIds = new HashSet<>();
/*  42 */   private static final Set<UUID> pokemonInBattleTowerBattles = new HashSet<>();
/*  43 */   private static final Map<UUID, Set<UUID>> pokemonByBattleId = new ConcurrentHashMap<>();
/*  44 */   private static final ThreadLocal<UUID> currentTrackingBattleId = new ThreadLocal<>();
/*     */ 
/*     */   
/*  47 */   private static final Map<UUID, Boolean> battleNoTeraMap = new ConcurrentHashMap<>();
/*  48 */   private static final Map<UUID, Boolean> battleNoDynamaxMap = new ConcurrentHashMap<>();
/*  49 */   private static final Map<UUID, Boolean> battleNoMegaMap = new ConcurrentHashMap<>();
/*  50 */   private static final Map<UUID, String> battleDifficultyMap = new ConcurrentHashMap<>();
/*     */ 
/*     */   
/*  53 */   private static final Map<UUID, List<BattleTowerTrainerEntity>> playerTrainerEntities = new ConcurrentHashMap<>();
/*     */ 
/*     */   
/*  56 */   private static final Set<UUID> cleanupInProgress = Collections.newSetFromMap(new ConcurrentHashMap<>());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void init() {
/*  63 */     LOGGER.info("Cobblemon Battle Tower initializing...");
/*     */     
/*  65 */     rctApi = RCTApi.initInstance("cobblemon_battle_tower");
/*  66 */     LOGGER.info("RCT API initialized for Battle Tower");
/*     */     
/*  68 */     rctApi.getEventContext().register(Events.BATTLE_STARTED, event -> {
/*     */           if (event.getValue() != null && ((BattleState)event.getValue()).getBattle() != null) {
/*     */             LOGGER.debug("Battle started: {}", ((BattleState)event.getValue()).getBattle().getBattleId());
/*     */           }
/*     */         });
/*  73 */     rctApi.getEventContext().register(Events.BATTLE_ENDED, event -> {
/*     */           if (event.getValue() != null && ((BattleState)event.getValue()).getBattle() != null) {
/*     */             LOGGER.debug("Battle ended: {}", ((BattleState)event.getValue()).getBattle().getBattleId());
/*     */           }
/*     */         });
/*     */     
/*  79 */     registerItemDropPrevention();
/*     */     
/*  81 */     BattleTowerEntities.init();
/*     */     
/*  83 */     BattleTowerNetwork.init();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void onServerStarting(MinecraftServer server) {
/*  92 */     LOGGER.info("Battle Tower server starting");
/*     */   }
/*     */   
/*     */   public static void onServerStopping(MinecraftServer server) {
/*  96 */     cleanupAllBattlePokemon(server);
/*  97 */     LOGGER.info("Battle Tower server stopping");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void registerItemDropPrevention() {
/* 107 */     CobblemonEvents.HELD_ITEM_PRE.subscribe(Priority.HIGHEST, event -> {
/*     */           try {
/*     */             UUID pokemonUuid = event.getPokemon().getUuid();
/*     */ 
/*     */             
/*     */             if (pokemonInBattleTowerBattles.contains(pokemonUuid) && event.getReceiving().method_7960() && !event.getReturning().method_7960()) {
/*     */               event.cancel();
/*     */             }
/* 115 */           } catch (Exception e) {
/*     */             LOGGER.error("Error handling held item pre event", e);
/*     */           } 
/*     */           
/*     */           return Unit.INSTANCE;
/*     */         });
/*     */     
/* 122 */     CobblemonEvents.LOOT_DROPPED.subscribe(Priority.HIGHEST, event -> {
/*     */           try {
/*     */             class_1309 entity = event.getEntity();
/*     */             if (entity instanceof PokemonEntity) {
/*     */               PokemonEntity pokemonEntity = (PokemonEntity)entity;
/*     */               UUID battleId = pokemonEntity.getBattleId();
/*     */               if (battleId != null && battleTowerBattleIds.contains(battleId))
/*     */                 event.cancel(); 
/*     */             } 
/* 131 */           } catch (Exception e) {
/*     */             LOGGER.error("Error handling loot dropped event", e);
/*     */           } 
/*     */           
/*     */           return Unit.INSTANCE;
/*     */         });
/*     */     
/* 138 */     CobblemonEvents.POKEMON_FAINTED.subscribe(Priority.HIGHEST, event -> {
/*     */           try {
/*     */             Pokemon pokemon = event.getPokemon();
/*     */             if (!pokemonInBattleTowerBattles.contains(pokemon.getUuid())) {
/*     */               return Unit.INSTANCE;
/*     */             }
/*     */             PokemonEntity pokemonEntity = pokemon.getEntity();
/*     */             if (pokemonEntity == null)
/*     */               return Unit.INSTANCE; 
/*     */             UUID ownerUUID = pokemonEntity.method_6139();
/*     */             if (ownerUUID != null)
/*     */               return Unit.INSTANCE; 
/*     */             class_1799 currentItem = pokemon.heldItem();
/*     */             if (!currentItem.method_7960()) {
/*     */               try {
/*     */                 Field heldItemField = pokemon.getClass().getDeclaredField("heldItem");
/*     */                 heldItemField.setAccessible(true);
/*     */                 heldItemField.set(pokemon, class_1799.field_8037);
/* 156 */               } catch (Exception ex) {
/*     */                 pokemon.swapHeldItem(class_1799.field_8037, false, false);
/*     */               } 
/*     */             }
/* 160 */           } catch (Exception e) {
/*     */             LOGGER.error("Error handling Pokemon fainted event", e);
/*     */           } 
/*     */           
/*     */           return Unit.INSTANCE;
/*     */         });
/*     */     
/* 167 */     CobblemonEvents.BATTLE_FLED.subscribe(Priority.NORMAL, event -> {
/*     */           try {
/*     */             cleanupBattle(event.getBattle().getBattleId());
/* 170 */           } catch (Exception e) {
/*     */             LOGGER.error("Error cleaning up fled battle", e);
/*     */           } 
/*     */           return Unit.INSTANCE;
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void trackBattle(UUID battleId) {
/* 182 */     battleTowerBattleIds.add(battleId);
/* 183 */     currentTrackingBattleId.set(battleId);
/*     */   }
/*     */   
/*     */   public static void trackPokemon(UUID pokemonUuid) {
/* 187 */     pokemonInBattleTowerBattles.add(pokemonUuid);
/* 188 */     UUID battleId = currentTrackingBattleId.get();
/* 189 */     if (battleId != null) {
/* 190 */       ((Set<UUID>)pokemonByBattleId
/* 191 */         .computeIfAbsent(battleId, id -> Collections.newSetFromMap(new ConcurrentHashMap<>())))
/* 192 */         .add(pokemonUuid);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void untrackPokemon(Set<UUID> pokemonUuids) {
/* 201 */     if (pokemonUuids != null && !pokemonUuids.isEmpty()) {
/* 202 */       pokemonInBattleTowerBattles.removeAll(pokemonUuids);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void trackTrainerEntity(UUID playerUuid, BattleTowerTrainerEntity trainerEntity) {
/* 207 */     ((List<BattleTowerTrainerEntity>)playerTrainerEntities
/* 208 */       .computeIfAbsent(playerUuid, k -> new ArrayList()))
/* 209 */       .add(trainerEntity);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void cleanupBattle(UUID battleId) {
/* 217 */     if (battleTowerBattleIds.remove(battleId)) {
/* 218 */       LOGGER.debug("Cleaned up Battle Tower battle: {}", battleId);
/*     */     }
/*     */     
/* 221 */     Set<UUID> tracked = pokemonByBattleId.remove(battleId);
/* 222 */     if (tracked != null && !tracked.isEmpty()) {
/* 223 */       pokemonInBattleTowerBattles.removeAll(tracked);
/*     */     }
/*     */     
/* 226 */     UUID current = currentTrackingBattleId.get();
/* 227 */     if (battleId.equals(current)) {
/* 228 */       currentTrackingBattleId.remove();
/*     */     }
/*     */     
/* 231 */     battleNoTeraMap.remove(battleId);
/* 232 */     battleNoDynamaxMap.remove(battleId);
/* 233 */     battleNoMegaMap.remove(battleId);
/* 234 */     battleDifficultyMap.remove(battleId);
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
/*     */   public static void cleanupPlayerTrainers(UUID playerUuid, MinecraftServer server) {
/* 246 */     server.execute(() -> cleanupPlayerTrainersOnServerThread(playerUuid, server));
/*     */   }
/*     */   
/*     */   private static void cleanupPlayerTrainersOnServerThread(UUID playerUuid, MinecraftServer server) {
/* 250 */     if (!cleanupInProgress.add(playerUuid))
/*     */       return; 
/*     */     try {
/* 253 */       ActivePlayerTracker.removeSession(playerUuid);
/* 254 */       removeTrackedTrainerEntities(playerUuid);
/*     */     } finally {
/* 256 */       cleanupInProgress.remove(playerUuid);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static void removeTrackedTrainerEntities(UUID playerUuid) {
/* 262 */     List<BattleTowerTrainerEntity> trainers = playerTrainerEntities.remove(playerUuid);
/* 263 */     if (trainers == null)
/*     */       return; 
/* 265 */     for (BattleTowerTrainerEntity trainer : trainers) {
/*     */       try {
/* 267 */         if (trainer != null && !trainer.method_31481()) {
/* 268 */           trainer.method_31472();
/* 269 */           LOGGER.debug("Removed trainer entity for disconnected player {}", playerUuid);
/*     */         } 
/* 271 */       } catch (Exception e) {
/* 272 */         LOGGER.debug("Error removing trainer entity: {}", e.getMessage());
/*     */       } 
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
/*     */   public static void registerBattleGimmicks(UUID battleId, int floor) {
/* 285 */     registerBattleGimmicks(battleId, floor, 5, 10, 15);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void registerBattleGimmicks(UUID battleId, int floor, int teraFloor, int dynamaxFloor, int megaFloor) {
/*     */     String difficulty;
/* 297 */     if (floor < teraFloor) { difficulty = "easy"; }
/* 298 */     else if (floor < dynamaxFloor) { difficulty = "medium"; }
/* 299 */     else if (floor < megaFloor) { difficulty = "hard"; }
/* 300 */     else { difficulty = "extreme"; }
/* 301 */      battleDifficultyMap.put(battleId, difficulty);
/*     */     
/* 303 */     boolean noTera = (floor < teraFloor);
/*     */     
/* 305 */     boolean noDynamax = true;
/* 306 */     boolean noMega = (floor < megaFloor);
/*     */     
/* 308 */     battleNoTeraMap.put(battleId, Boolean.valueOf(noTera));
/* 309 */     battleNoDynamaxMap.put(battleId, Boolean.valueOf(noDynamax));
/* 310 */     battleNoMegaMap.put(battleId, Boolean.valueOf(noMega));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getBattleDifficulty(UUID battleId) {
/* 316 */     return battleDifficultyMap.getOrDefault(battleId, "medium");
/*     */   }
/*     */   
/*     */   public static boolean isNoTeraBattle(UUID battleId) {
/* 320 */     return ((Boolean)battleNoTeraMap.getOrDefault(battleId, Boolean.valueOf(false))).booleanValue();
/*     */   }
/*     */   
/*     */   public static boolean isNoDynamaxBattle(UUID battleId) {
/* 324 */     return ((Boolean)battleNoDynamaxMap.getOrDefault(battleId, Boolean.valueOf(false))).booleanValue();
/*     */   }
/*     */   
/*     */   public static boolean isBattleTowerBattle(UUID battleId) {
/* 328 */     return battleTowerBattleIds.contains(battleId);
/*     */   }
/*     */   
/*     */   public static boolean isNoMegaBattle(UUID battleId) {
/* 332 */     return ((Boolean)battleNoMegaMap.getOrDefault(battleId, Boolean.valueOf(false))).booleanValue();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static RCTApi getRCTApi() {
/* 338 */     return rctApi;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void cleanupAllBattlePokemon(MinecraftServer server) {
/* 346 */     int removedCount = 0;
/*     */     
/* 348 */     for (class_3218 world : server.method_3738()) {
/* 349 */       List<class_1297> toRemove = new ArrayList<>();
/* 350 */       for (class_1297 entity : world.method_27909()) {
/* 351 */         if (entity instanceof PokemonEntity) { PokemonEntity pokemonEntity = (PokemonEntity)entity; if (pokemonEntity
/* 352 */             .getBattleId() != null)
/* 353 */             toRemove.add(pokemonEntity);  }
/*     */       
/*     */       } 
/* 356 */       for (class_1297 entity : toRemove) {
/* 357 */         entity.method_31472();
/* 358 */         removedCount++;
/*     */       } 
/*     */     } 
/*     */     
/* 362 */     battleTowerBattleIds.clear();
/* 363 */     pokemonInBattleTowerBattles.clear();
/* 364 */     pokemonByBattleId.clear();
/* 365 */     battleNoTeraMap.clear();
/* 366 */     battleNoDynamaxMap.clear();
/* 367 */     battleNoMegaMap.clear();
/* 368 */     battleDifficultyMap.clear();
/* 369 */     playerTrainerEntities.clear();
/*     */     
/* 371 */     LOGGER.info("Cleaned up {} battle Pokemon entities on server stop", Integer.valueOf(removedCount));
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\battletower\BattleTower.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */