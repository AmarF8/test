/*     */ package com.atlas.common.fabric.block.station.entity;
/*     */ import com.atlas.common.fabric.block.station.StationLinkManager;
/*     */ import com.atlas.common.fabric.block.station.packet.client.StationOpenPacket;
/*     */ import com.atlas.common.fabric.block.station.packet.client.StationPokemonAddedPacket;
/*     */ import com.atlas.common.fabric.block.station.packet.client.StationPokemonRemovedPacket;
/*     */ import com.atlas.common.fabric.block.station.system.StationDefinition;
/*     */ import com.atlas.common.fabric.block.station.system.StationRegistry;
/*     */ import com.atlas.common.fabric.packet.AtlasModPacket;
/*     */ import com.cobblemon.mod.common.Cobblemon;
/*     */ import com.cobblemon.mod.common.api.storage.pc.PCBox;
/*     */ import com.cobblemon.mod.common.api.storage.pc.PCStore;
/*     */ import com.cobblemon.mod.common.pokemon.Pokemon;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import java.util.stream.Collectors;
/*     */ import net.minecraft.class_124;
/*     */ import net.minecraft.class_1937;
/*     */ import net.minecraft.class_2338;
/*     */ import net.minecraft.class_2481;
/*     */ import net.minecraft.class_2487;
/*     */ import net.minecraft.class_2499;
/*     */ import net.minecraft.class_2520;
/*     */ import net.minecraft.class_2591;
/*     */ import net.minecraft.class_2596;
/*     */ import net.minecraft.class_2602;
/*     */ import net.minecraft.class_2680;
/*     */ import net.minecraft.class_3218;
/*     */ import net.minecraft.class_3222;
/*     */ import net.minecraft.class_5250;
/*     */ import net.minecraft.class_5455;
/*     */ import net.minecraft.class_7225;
/*     */ import net.minecraft.class_7923;
/*     */ import net.minecraft.class_8710;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ import software.bernie.geckolib.animatable.GeoBlockEntity;
/*     */ import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
/*     */ import software.bernie.geckolib.animation.AnimatableManager;
/*     */ 
/*     */ public class StationBlockEntity extends class_2586 implements GeoBlockEntity {
/*  43 */   private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache((GeoAnimatable)this);
/*     */   
/*     */   private static final String STATION_ID_KEY = "StationId";
/*     */   
/*     */   private static final String TOTAL_GENERATED_KEY = "TotalGenerated";
/*     */   
/*     */   private static final String CURRENT_LEVEL_KEY = "CurrentLevel";
/*     */   private static final String TETHER_POKEMON_KEY = "TetheredPokemon";
/*     */   private static final String OWNER_ID_KEY = "OwnerId";
/*     */   private static final String REWARDS_KEY = "Rewards";
/*     */   private static final int CHECK_INTERVAL = 100;
/*  54 */   private String stationId = class_7923.field_41175.method_10221(method_11010().method_26204()).method_12832();
/*  55 */   private double totalGenerated = 0.0D;
/*  56 */   private int currentLevel = 1;
/*  57 */   private int tickCounter = 0;
/*  58 */   private int checkCounter = 0;
/*  59 */   private double totalRewards = 0.0D;
/*     */   
/*     */   private boolean needsLegacySync = false;
/*  62 */   private final List<StationTethering> tetheredPokemon = new ArrayList<>();
/*     */   private UUID ownerId;
/*     */   
/*     */   public StationBlockEntity(@NotNull class_2591<? extends StationBlockEntity> type, @NotNull class_2338 pos, @NotNull class_2680 state) {
/*  66 */     super(type, pos, state);
/*     */   }
/*     */   public static final class StationTethering extends Record { private final UUID pokemonId; private final UUID playerId; private final UUID pcId; private final UUID tetheringId; private final String playerName; private final String species;
/*     */     private final String aspects;
/*     */     private final boolean shiny;
/*     */     private final String pokemonData;
/*     */     
/*  73 */     public StationTethering(UUID pokemonId, UUID playerId, UUID pcId, UUID tetheringId, String playerName, String species, String aspects, boolean shiny, String pokemonData) { this.pokemonId = pokemonId; this.playerId = playerId; this.pcId = pcId; this.tetheringId = tetheringId; this.playerName = playerName; this.species = species; this.aspects = aspects; this.shiny = shiny; this.pokemonData = pokemonData; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/block/station/entity/StationBlockEntity$StationTethering;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #73	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*  73 */       //   0	7	0	this	Lcom/atlas/common/fabric/block/station/entity/StationBlockEntity$StationTethering; } public UUID pokemonId() { return this.pokemonId; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/block/station/entity/StationBlockEntity$StationTethering;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #73	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/block/station/entity/StationBlockEntity$StationTethering; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/block/station/entity/StationBlockEntity$StationTethering;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #73	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lcom/atlas/common/fabric/block/station/entity/StationBlockEntity$StationTethering;
/*  73 */       //   0	8	1	o	Ljava/lang/Object; } public UUID playerId() { return this.playerId; } public UUID pcId() { return this.pcId; } public UUID tetheringId() { return this.tetheringId; } public String playerName() { return this.playerName; } public String species() { return this.species; } public String aspects() { return this.aspects; } public boolean shiny() { return this.shiny; } public String pokemonData() { return this.pokemonData; }
/*     */     
/*     */     @Nullable
/*     */     public Pokemon getPokemon(class_7225.class_7874 registries) {
/*     */       try {
/*  78 */         PCStore pc = Cobblemon.INSTANCE.getStorage().getPC(this.pcId, (class_5455)registries);
/*  79 */         return pc.get(this.pokemonId);
/*  80 */       } catch (Exception e) {
/*  81 */         return null;
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean isPCEmpty(class_7225.class_7874 registries) {
/*     */       try {
/*  92 */         PCStore pc = Cobblemon.INSTANCE.getStorage().getPC(this.pcId, (class_5455)registries);
/*  93 */         if (pc == null) return true; 
/*  94 */         for (PCBox box : pc.getBoxes()) {
/*  95 */           for (int i = 0; i < 30; i++) {
/*  96 */             if (box.get(i) != null) return false; 
/*     */           } 
/*     */         } 
/*  99 */         return true;
/* 100 */       } catch (Exception e) {
/* 101 */         return true;
/*     */       } 
/*     */     }
/*     */     @Nullable
/*     */     public StationOpenPacket.StationPokemonDataDTO toDTO(class_3222 viewer, class_7225.class_7874 registries) {
/* 106 */       Pokemon pokemon = getPokemon(registries);
/* 107 */       if (pokemon == null) return null;
/*     */       
/* 109 */       class_5250 class_5250 = pokemon.getDisplayName(false);
/* 110 */       if (!this.playerId.equals(viewer.method_5667())) {
/* 111 */         class_5250 = class_5250.method_27661().method_27692(class_124.field_1056);
/*     */       }
/*     */       
/* 114 */       return new StationOpenPacket.StationPokemonDataDTO(this.pokemonId, this.playerId, (class_2561)class_5250, 
/*     */ 
/*     */ 
/*     */           
/* 118 */           this.playerId.equals(viewer.method_5667()) ? null : this.playerName, pokemon
/* 119 */           .getSpecies().getResourceIdentifier(), pokemon
/* 120 */           .getAspects(), pokemon
/* 121 */           .heldItem(), pokemon
/* 122 */           .getLevel(), false, 
/*     */           
/* 124 */           Collections.emptySet());
/*     */     } }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<StationOpenPacket.StationPokemonDataDTO> getPokemonDTOs(class_3222 viewer) {
/* 130 */     List<StationOpenPacket.StationPokemonDataDTO> dtos = new ArrayList<>();
/* 131 */     class_5455 class_5455 = viewer.method_56673();
/* 132 */     for (StationTethering tether : this.tetheredPokemon) {
/* 133 */       StationOpenPacket.StationPokemonDataDTO dto = tether.toDTO(viewer, (class_7225.class_7874)class_5455);
/* 134 */       if (dto != null) dtos.add(dto); 
/*     */     } 
/* 136 */     return dtos;
/*     */   }
/*     */   
/*     */   public boolean tether(class_3222 player, Pokemon pokemon) {
/* 140 */     UUID tetheringId = UUID.randomUUID();
/* 141 */     pokemon.setTetheringId(tetheringId);
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
/* 153 */     StationTethering tethering = new StationTethering(pokemon.getUuid(), player.method_5667(), Cobblemon.INSTANCE.getStorage().getPC(player).getUuid(), tetheringId, player.method_7334().getName(), pokemon.getSpecies().getResourceIdentifier().toString(), pokemon.getAspects().stream().map(Object::toString).collect(Collectors.joining(",")), pokemon.getShiny(), serializePokemon(pokemon, (class_7225.class_7874)method_10997().method_30349()));
/*     */ 
/*     */     
/* 156 */     this.tetheredPokemon.add(tethering);
/*     */     
/* 158 */     StationOpenPacket.StationPokemonDataDTO dto = tethering.toDTO(player, (class_7225.class_7874)method_10997().method_30349());
/* 159 */     if (dto != null) {
/* 160 */       AtlasModPacket.sendToClient(player, (class_8710)new StationPokemonAddedPacket(dto));
/*     */     }
/*     */     
/* 163 */     method_5431();
/* 164 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxTethered() {
/* 171 */     int tier = StationRegistry.get(this.stationId).getStationTier();
/* 172 */     if (tier == 2) return 5; 
/* 173 */     if (tier == 3) return 7; 
/* 174 */     if (tier == 4) return 10; 
/* 175 */     if (tier == 5) return 14; 
/* 176 */     return 3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canAddPokemon(class_3222 player, Pokemon pokemon, int maxPerPlayer) {
/* 187 */     if (this.tetheredPokemon.size() >= getMaxTethered()) {
/* 188 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 193 */     boolean alreadyTethered = this.tetheredPokemon.stream().anyMatch(t -> t.pokemonId().equals(pokemon.getUuid()));
/* 194 */     if (alreadyTethered) return false;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 199 */     long countForPlayer = this.tetheredPokemon.stream().filter(t -> t.playerId().equals(player.method_5667())).count();
/* 200 */     if (countForPlayer >= maxPerPlayer) {
/* 201 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 205 */     if (pokemon.isFainted()) {
/* 206 */       return false;
/*     */     }
/*     */     
/* 209 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void checkPokemon() {
/* 220 */     if (this.field_11863 == null || this.field_11863.field_9236)
/* 221 */       return;  MinecraftServer server = this.field_11863.method_8503();
/* 222 */     if (server == null)
/*     */       return; 
/* 224 */     List<UUID> deadLinks = new ArrayList<>();
/* 225 */     class_5455 class_5455 = this.field_11863.method_30349();
/*     */     
/* 227 */     for (StationTethering tethering : this.tetheredPokemon) {
/* 228 */       boolean ownerOnline = (server.method_3760().method_14602(tethering.playerId()) != null);
/* 229 */       if (!ownerOnline)
/*     */         continue; 
/* 231 */       Pokemon pokemon = tethering.getPokemon((class_7225.class_7874)class_5455);
/*     */       
/* 233 */       if (pokemon == null) {
/* 234 */         boolean pcEmpty = tethering.isPCEmpty((class_7225.class_7874)class_5455);
/* 235 */         if (pcEmpty)
/* 236 */           continue;  deadLinks.add(tethering.pokemonId()); continue;
/* 237 */       }  if (pokemon.getTetheringId() == null) {
/* 238 */         pokemon.setTetheringId(tethering.tetheringId()); continue;
/* 239 */       }  if (!tethering.tetheringId().equals(pokemon.getTetheringId())) {
/* 240 */         deadLinks.add(tethering.pokemonId());
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 245 */     for (UUID pokemonId : deadLinks) {
/* 246 */       this.tetheredPokemon.removeIf(t -> t.pokemonId().equals(pokemonId));
/*     */     }
/*     */     
/* 249 */     if (!deadLinks.isEmpty()) {
/* 250 */       method_5431();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void syncLegacyTetherings() {
/* 259 */     if (!this.needsLegacySync || this.field_11863 == null || this.field_11863.field_9236)
/*     */       return; 
/* 261 */     this.needsLegacySync = false;
/* 262 */     class_5455 class_5455 = this.field_11863.method_30349();
/*     */     
/* 264 */     for (StationTethering tethering : this.tetheredPokemon) {
/* 265 */       Pokemon pokemon = tethering.getPokemon((class_7225.class_7874)class_5455);
/* 266 */       if (pokemon != null)
/*     */       {
/*     */         
/* 269 */         if (pokemon.getTetheringId() == null) {
/* 270 */           pokemon.setTetheringId(tethering.tetheringId());
/*     */         }
/*     */       }
/*     */     } 
/*     */     
/* 275 */     method_5431();
/*     */   }
/*     */   
/*     */   public void setStationId(@Nullable String stationId) {
/* 279 */     this.stationId = stationId;
/* 280 */     method_5431();
/*     */   }
/*     */   
/*     */   public void setOwnerUUID(@Nullable UUID ownerId) {
/* 284 */     this.ownerId = ownerId;
/* 285 */     method_5431();
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public UUID getOwnerUUID() {
/* 290 */     return this.ownerId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addReward(double amount) {
/* 298 */     this.totalRewards += amount;
/* 299 */     method_5431();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getTotalRewards() {
/* 306 */     return this.totalRewards;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double claimAllRewards() {
/* 314 */     double claimed = this.totalRewards;
/* 315 */     this.totalRewards = 0.0D;
/* 316 */     method_5431();
/* 317 */     return claimed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<UUID> releaseAllPokemon(UUID playerId) {
/* 325 */     List<UUID> removed = new ArrayList<>();
/*     */     
/* 327 */     this.tetheredPokemon.removeIf(t -> {
/*     */           if (t.playerId().equals(playerId)) {
/*     */             if (this.field_11863 != null && !this.field_11863.field_9236) {
/*     */               Pokemon p = t.getPokemon((class_7225.class_7874)method_10997().method_30349());
/*     */               if (p != null)
/*     */                 p.setTetheringId(null); 
/*     */             } 
/*     */             removed.add(t.pokemonId());
/*     */             return true;
/*     */           } 
/*     */           return false;
/*     */         });
/* 339 */     if (!removed.isEmpty()) {
/* 340 */       method_5431();
/*     */     }
/* 342 */     return removed;
/*     */   }
/*     */   
/*     */   public void releasePokemon(UUID pokemonId) {
/* 346 */     this.tetheredPokemon.removeIf(t -> {
/*     */           if (t.pokemonId().equals(pokemonId)) {
/*     */             if (this.field_11863 != null && !this.field_11863.field_9236) {
/*     */               Pokemon p = t.getPokemon((class_7225.class_7874)this.field_11863.method_30349());
/*     */               if (p != null) {
/*     */                 p.setTetheringId(null);
/*     */               }
/*     */               class_3222 player = this.field_11863.method_8503().method_3760().method_14602(t.playerId());
/*     */               if (player != null)
/*     */                 AtlasModPacket.sendToClient(player, (class_8710)new StationPokemonRemovedPacket(pokemonId)); 
/*     */             } 
/*     */             return true;
/*     */           } 
/*     */           return false;
/*     */         });
/* 361 */     method_5431();
/*     */   }
/*     */   
/*     */   public void onBroken() {
/* 365 */     class_1937 class_1937 = this.field_11863; if (class_1937 instanceof class_3218) { class_3218 serverWorld = (class_3218)class_1937;
/* 366 */       for (StationTethering tethering : new ArrayList(this.tetheredPokemon)) {
/* 367 */         releasePokemon(tethering.pokemonId());
/*     */       }
/* 369 */       StationLinkManager.removeAt(serverWorld, this.field_11867); }
/*     */   
/*     */   }
/*     */   
/*     */   public static void tick(class_1937 world, class_2338 pos, class_2680 state, StationBlockEntity blockEntity) {
/* 374 */     if (world.field_9236) {
/*     */       return;
/*     */     }
/* 377 */     if (blockEntity.needsLegacySync) {
/* 378 */       blockEntity.syncLegacyTetherings();
/*     */     }
/*     */ 
/*     */     
/* 382 */     blockEntity.checkCounter++;
/* 383 */     if (blockEntity.checkCounter >= 100) {
/* 384 */       blockEntity.checkCounter = 0;
/* 385 */       blockEntity.checkPokemon();
/*     */     } 
/*     */ 
/*     */     
/* 389 */     blockEntity.tickCounter++;
/* 390 */     if (blockEntity.tickCounter >= 1200) {
/* 391 */       blockEntity.tickCounter = 0;
/*     */       
/* 393 */       StationDefinition stationDef = StationRegistry.get(blockEntity.stationId);
/*     */       
/* 395 */       if (stationDef != null) {
/*     */         
/* 397 */         if (blockEntity.tetheredPokemon.size() < blockEntity.getMaxTethered())
/*     */           return; 
/* 399 */         List<Pokemon> pokemonList = new ArrayList<>();
/* 400 */         class_5455 class_5455 = world.method_30349();
/*     */         
/* 402 */         for (StationTethering tethering : blockEntity.tetheredPokemon) {
/* 403 */           Pokemon pokemon = tethering.getPokemon((class_7225.class_7874)class_5455);
/* 404 */           if (pokemon != null) {
/* 405 */             pokemonList.add(pokemon);
/*     */           }
/*     */         } 
/*     */ 
/*     */         
/* 410 */         double generation = stationDef.calculateGeneration(pokemonList);
/*     */         
/* 412 */         if (generation > 0.0D && blockEntity.getTotalRewards() < ((StationTier)stationDef.getTiers().get(4)).getBaseGeneration() * 240.0D) {
/* 413 */           blockEntity.addReward(generation);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void method_11014(@NotNull class_2487 nbt, class_7225.class_7874 registries) {
/* 421 */     super.method_11014(nbt, registries);
/*     */     
/* 423 */     this.stationId = nbt.method_10558("StationId");
/* 424 */     this.totalGenerated = nbt.method_10574("TotalGenerated");
/* 425 */     this.currentLevel = nbt.method_10550("CurrentLevel");
/* 426 */     this.totalRewards = nbt.method_10574("Rewards");
/*     */     
/* 428 */     this.tetheredPokemon.clear();
/* 429 */     if (nbt.method_10545("TetheredPokemon")) {
/* 430 */       class_2499 list = nbt.method_10554("TetheredPokemon", 10);
/* 431 */       for (int i = 0; i < list.size(); i++) {
/* 432 */         UUID tetheringId; class_2487 tag = list.method_10602(i);
/*     */ 
/*     */ 
/*     */         
/* 436 */         if (tag.method_10545("TetheringId")) {
/* 437 */           tetheringId = tag.method_25926("TetheringId");
/*     */         } else {
/*     */           
/* 440 */           tetheringId = UUID.randomUUID();
/*     */         } 
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
/* 452 */         StationTethering tethering = new StationTethering(tag.method_25926("PokemonId"), tag.method_25926("PlayerId"), tag.method_25926("PcId"), tetheringId, tag.method_10558("PlayerName"), tag.method_10558("Species"), tag.method_10558("Aspects"), (tag.method_10545("Shiny") && tag.method_10577("Shiny")), tag.method_10545("PokemonData") ? tag.method_10558("PokemonData") : "");
/*     */         
/* 454 */         this.tetheredPokemon.add(tethering);
/*     */       } 
/*     */     } 
/*     */     
/* 458 */     if (nbt.method_10545("OwnerId")) this.ownerId = nbt.method_25926("OwnerId");
/*     */ 
/*     */     
/* 461 */     if (!this.tetheredPokemon.isEmpty()) {
/* 462 */       this.needsLegacySync = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void method_11007(@NotNull class_2487 nbt, class_7225.class_7874 registries) {
/* 468 */     super.method_11007(nbt, registries);
/*     */     
/* 470 */     nbt.method_10582("StationId", this.stationId);
/* 471 */     nbt.method_10549("TotalGenerated", this.totalGenerated);
/* 472 */     nbt.method_10569("CurrentLevel", this.currentLevel);
/* 473 */     nbt.method_10549("Rewards", this.totalRewards);
/*     */     
/* 475 */     class_2499 list = new class_2499();
/* 476 */     for (StationTethering t : this.tetheredPokemon) {
/* 477 */       class_2487 tag = new class_2487();
/* 478 */       tag.method_25927("PokemonId", t.pokemonId());
/* 479 */       tag.method_25927("PlayerId", t.playerId());
/* 480 */       tag.method_25927("PcId", t.pcId());
/* 481 */       tag.method_25927("TetheringId", t.tetheringId());
/* 482 */       tag.method_10582("PlayerName", t.playerName());
/* 483 */       tag.method_10582("Species", t.species());
/* 484 */       tag.method_10582("Aspects", t.aspects());
/* 485 */       tag.method_10566("Shiny", (class_2520)class_2481.method_23234(t.shiny()));
/* 486 */       tag.method_10582("PokemonData", t.pokemonData());
/* 487 */       list.add(tag);
/*     */     } 
/* 489 */     nbt.method_10566("TetheredPokemon", (class_2520)list);
/*     */     
/* 491 */     if (this.ownerId != null) nbt.method_25927("OwnerId", this.ownerId);
/*     */   
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public class_2596<class_2602> method_38235() {
/* 497 */     return (class_2596<class_2602>)class_2622.method_38585(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public class_2487 method_16887(class_7225.class_7874 registries) {
/* 502 */     return method_38244(registries);
/*     */   }
/*     */ 
/*     */   
/*     */   public void method_5431() {
/* 507 */     super.method_5431();
/* 508 */     class_1937 class_1937 = this.field_11863; if (class_1937 instanceof class_3218) { class_3218 sw = (class_3218)class_1937;
/* 509 */       sw.method_14178().method_14128(this.field_11867); }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerControllers(AnimatableManager.ControllerRegistrar registrar) {}
/*     */ 
/*     */   
/*     */   public AnimatableInstanceCache getAnimatableInstanceCache() {
/* 519 */     return this.cache;
/*     */   }
/*     */   
/*     */   public String getStationId() {
/* 523 */     return this.stationId;
/*     */   }
/*     */   
/*     */   public List<StationTethering> getTetheredPokemon() {
/* 527 */     return this.tetheredPokemon;
/*     */   }
/*     */   
/*     */   private static String serializePokemon(@NotNull Pokemon pokemon, @NotNull class_7225.class_7874 registryLookup) {
/*     */     try {
/* 532 */       class_2487 nbt = pokemon.saveToNBT((class_5455)registryLookup, new class_2487());
/* 533 */       return nbt.toString();
/* 534 */     } catch (Exception ignored) {
/* 535 */       return "";
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\block\station\entity\StationBlockEntity.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */