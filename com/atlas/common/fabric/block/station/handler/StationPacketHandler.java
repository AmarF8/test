/*     */ package com.atlas.common.fabric.block.station.handler;
/*     */ 
/*     */ import com.atlas.common.event.AtlasEventBus;
/*     */ import com.atlas.common.fabric.block.station.StationLink;
/*     */ import com.atlas.common.fabric.block.station.StationLinkManager;
/*     */ import com.atlas.common.fabric.block.station.entity.StationBlockEntity;
/*     */ import com.atlas.common.fabric.block.station.packet.client.StationClosePacket;
/*     */ import com.atlas.common.fabric.block.station.packet.client.StationPokemonRemovedPacket;
/*     */ import com.atlas.common.fabric.block.station.packet.server.StationAddPokemonPacket;
/*     */ import com.atlas.common.fabric.block.station.packet.server.StationRemoveAllPokemonPacket;
/*     */ import com.atlas.common.fabric.block.station.packet.server.StationRemovePokemonPacket;
/*     */ import com.atlas.common.fabric.packet.AtlasModPacket;
/*     */ import com.atlas.common.fabric.packet.event.AtlasModPacketServerEvent;
/*     */ import com.cobblemon.mod.common.Cobblemon;
/*     */ import com.cobblemon.mod.common.api.storage.pc.PCStore;
/*     */ import com.cobblemon.mod.common.pokemon.Pokemon;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.class_1937;
/*     */ import net.minecraft.class_2586;
/*     */ import net.minecraft.class_3222;
/*     */ import net.minecraft.class_8710;
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
/*     */ public final class StationPacketHandler
/*     */ {
/*     */   public static void register() {
/*  35 */     AtlasEventBus.register(AtlasModPacketServerEvent.class, event -> {
/*     */           AtlasModPacket patt0$temp = event.getPacket(); if (patt0$temp instanceof StationAddPokemonPacket) {
/*     */             StationAddPokemonPacket packet = (StationAddPokemonPacket)patt0$temp; handleAddPokemon(packet, event.getPlayer());
/*     */           } else {
/*     */             AtlasModPacket patt1$temp = event.getPacket(); if (patt1$temp instanceof StationRemovePokemonPacket) {
/*     */               StationRemovePokemonPacket packet = (StationRemovePokemonPacket)patt1$temp; handleRemovePokemon(packet, event.getPlayer());
/*     */             } else {
/*     */               AtlasModPacket patt2$temp = event.getPacket(); if (patt2$temp instanceof StationRemoveAllPokemonPacket) {
/*     */                 StationRemoveAllPokemonPacket packet = (StationRemoveAllPokemonPacket)patt2$temp; handleRemoveAllPokemon(packet, event.getPlayer());
/*     */               } 
/*     */             } 
/*     */           } 
/*  47 */         }); } private static void handleAddPokemon(StationAddPokemonPacket packet, class_3222 player) { StationBlockEntity stationBE; StationLink link = StationLinkManager.getLinkByPlayer(player);
/*     */ 
/*     */     
/*  50 */     if (link == null || !link.getLinkId().equals(packet.getStationId())) {
/*  51 */       AtlasModPacket.sendToClient(player, (class_8710)new StationClosePacket());
/*     */       
/*     */       return;
/*     */     } 
/*  55 */     class_1937 world = player.method_37908();
/*  56 */     class_2586 class_2586 = world.method_8321(link.getPos()); if (class_2586 instanceof StationBlockEntity) { stationBE = (StationBlockEntity)class_2586; }
/*     */     else
/*     */     { return; }
/*     */     
/*  60 */     PCStore pc = Cobblemon.INSTANCE.getStorage().getPC(link.getPcId(), player.method_56673());
/*  61 */     Pokemon pokemon = pc.get(packet.getPokemonId());
/*     */     
/*  63 */     if (pokemon == null || pokemon.getTetheringId() != null) {
/*     */       return;
/*     */     }
/*     */     
/*  67 */     int maxAllowed = link.getPermissions().getMaxPokemon();
/*     */ 
/*     */     
/*  70 */     if (stationBE.canAddPokemon(player, pokemon, maxAllowed))
/*  71 */       stationBE.tether(player, pokemon);  }
/*     */ 
/*     */   
/*     */   private static void handleRemovePokemon(StationRemovePokemonPacket packet, class_3222 player) {
/*     */     StationBlockEntity stationBE;
/*  76 */     StationLink link = StationLinkManager.getLinkByPlayer(player);
/*     */     
/*  78 */     if (link == null || !link.getLinkId().equals(packet.getStationId())) {
/*  79 */       AtlasModPacket.sendToClient(player, (class_8710)new StationClosePacket());
/*     */       
/*     */       return;
/*     */     } 
/*  83 */     class_1937 world = player.method_37908();
/*  84 */     class_2586 class_2586 = world.method_8321(link.getPos()); if (class_2586 instanceof StationBlockEntity) { stationBE = (StationBlockEntity)class_2586; }
/*     */     else
/*     */     { return; }
/*     */ 
/*     */     
/*  89 */     stationBE.getTetheredPokemon().stream()
/*  90 */       .filter(t -> t.pokemonId().equals(packet.getPokemonId()))
/*  91 */       .findFirst()
/*  92 */       .ifPresent(tethered -> {
/*     */           if (tethered.playerId().equals(player.method_5667())) {
/*     */             stationBE.releasePokemon(tethered.pokemonId());
/*     */             AtlasModPacket.sendToClient(player, (class_8710)new StationPokemonRemovedPacket(packet.getPokemonId()));
/*     */           } else {
/*     */             AtlasModPacket.sendToClient(player, (class_8710)new StationClosePacket());
/*     */           } 
/*     */         });
/*     */   }
/*     */   
/*     */   private static void handleRemoveAllPokemon(StationRemoveAllPokemonPacket packet, class_3222 player) {
/*     */     StationBlockEntity stationBE;
/* 104 */     StationLink link = StationLinkManager.getLinkByPlayer(player);
/*     */     
/* 106 */     if (link == null || !link.getLinkId().equals(packet.getStationId())) {
/* 107 */       AtlasModPacket.sendToClient(player, (class_8710)new StationClosePacket());
/*     */       
/*     */       return;
/*     */     } 
/* 111 */     class_1937 world = player.method_37908();
/* 112 */     class_2586 class_2586 = world.method_8321(link.getPos()); if (class_2586 instanceof StationBlockEntity) { stationBE = (StationBlockEntity)class_2586; }
/*     */     else
/*     */     { return; }
/*     */ 
/*     */     
/* 117 */     stationBE.releaseAllPokemon(player.method_5667()).forEach(uuid -> AtlasModPacket.sendToClient(player, (class_8710)new StationPokemonRemovedPacket(uuid)));
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\block\station\handler\StationPacketHandler.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */