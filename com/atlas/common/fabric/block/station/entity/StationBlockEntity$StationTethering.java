/*     */ package com.atlas.common.fabric.block.station.entity;
/*     */ 
/*     */ import com.atlas.common.fabric.block.station.packet.client.StationOpenPacket;
/*     */ import com.cobblemon.mod.common.Cobblemon;
/*     */ import com.cobblemon.mod.common.api.storage.pc.PCBox;
/*     */ import com.cobblemon.mod.common.api.storage.pc.PCStore;
/*     */ import com.cobblemon.mod.common.pokemon.Pokemon;
/*     */ import java.util.Collections;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.class_124;
/*     */ import net.minecraft.class_2561;
/*     */ import net.minecraft.class_3222;
/*     */ import net.minecraft.class_5250;
/*     */ import net.minecraft.class_5455;
/*     */ import net.minecraft.class_7225;
/*     */ import org.jetbrains.annotations.Nullable;
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
/*     */ 
/*     */ public final class StationTethering
/*     */   extends Record
/*     */ {
/*     */   private final UUID pokemonId;
/*     */   private final UUID playerId;
/*     */   private final UUID pcId;
/*     */   private final UUID tetheringId;
/*     */   private final String playerName;
/*     */   private final String species;
/*     */   private final String aspects;
/*     */   private final boolean shiny;
/*     */   private final String pokemonData;
/*     */   
/*     */   public final String toString() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/block/station/entity/StationBlockEntity$StationTethering;)Ljava/lang/String;
/*     */     //   6: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #73	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	7	0	this	Lcom/atlas/common/fabric/block/station/entity/StationBlockEntity$StationTethering;
/*     */   }
/*     */   
/*     */   public final int hashCode() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/block/station/entity/StationBlockEntity$StationTethering;)I
/*     */     //   6: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #73	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	7	0	this	Lcom/atlas/common/fabric/block/station/entity/StationBlockEntity$StationTethering;
/*     */   }
/*     */   
/*     */   public final boolean equals(Object o) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/block/station/entity/StationBlockEntity$StationTethering;Ljava/lang/Object;)Z
/*     */     //   7: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #73	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	8	0	this	Lcom/atlas/common/fabric/block/station/entity/StationBlockEntity$StationTethering;
/*     */     //   0	8	1	o	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public StationTethering(UUID pokemonId, UUID playerId, UUID pcId, UUID tetheringId, String playerName, String species, String aspects, boolean shiny, String pokemonData) {
/*  73 */     this.pokemonId = pokemonId; this.playerId = playerId; this.pcId = pcId; this.tetheringId = tetheringId; this.playerName = playerName; this.species = species; this.aspects = aspects; this.shiny = shiny; this.pokemonData = pokemonData; } public UUID pokemonId() { return this.pokemonId; } public UUID playerId() { return this.playerId; } public UUID pcId() { return this.pcId; } public UUID tetheringId() { return this.tetheringId; } public String playerName() { return this.playerName; } public String species() { return this.species; } public String aspects() { return this.aspects; } public boolean shiny() { return this.shiny; } public String pokemonData() { return this.pokemonData; }
/*     */   
/*     */   @Nullable
/*     */   public Pokemon getPokemon(class_7225.class_7874 registries) {
/*     */     try {
/*  78 */       PCStore pc = Cobblemon.INSTANCE.getStorage().getPC(this.pcId, (class_5455)registries);
/*  79 */       return pc.get(this.pokemonId);
/*  80 */     } catch (Exception e) {
/*  81 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isPCEmpty(class_7225.class_7874 registries) {
/*     */     try {
/*  92 */       PCStore pc = Cobblemon.INSTANCE.getStorage().getPC(this.pcId, (class_5455)registries);
/*  93 */       if (pc == null) return true; 
/*  94 */       for (PCBox box : pc.getBoxes()) {
/*  95 */         for (int i = 0; i < 30; i++) {
/*  96 */           if (box.get(i) != null) return false; 
/*     */         } 
/*     */       } 
/*  99 */       return true;
/* 100 */     } catch (Exception e) {
/* 101 */       return true;
/*     */     } 
/*     */   }
/*     */   @Nullable
/*     */   public StationOpenPacket.StationPokemonDataDTO toDTO(class_3222 viewer, class_7225.class_7874 registries) {
/* 106 */     Pokemon pokemon = getPokemon(registries);
/* 107 */     if (pokemon == null) return null;
/*     */     
/* 109 */     class_5250 class_5250 = pokemon.getDisplayName(false);
/* 110 */     if (!this.playerId.equals(viewer.method_5667())) {
/* 111 */       class_5250 = class_5250.method_27661().method_27692(class_124.field_1056);
/*     */     }
/*     */     
/* 114 */     return new StationOpenPacket.StationPokemonDataDTO(this.pokemonId, this.playerId, (class_2561)class_5250, 
/*     */ 
/*     */ 
/*     */         
/* 118 */         this.playerId.equals(viewer.method_5667()) ? null : this.playerName, pokemon
/* 119 */         .getSpecies().getResourceIdentifier(), pokemon
/* 120 */         .getAspects(), pokemon
/* 121 */         .heldItem(), pokemon
/* 122 */         .getLevel(), false, 
/*     */         
/* 124 */         Collections.emptySet());
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\block\station\entity\StationBlockEntity$StationTethering.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */