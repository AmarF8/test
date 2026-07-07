/*    */ package com.atlas.common.fabric.block.station;
/*    */ 
/*    */ import com.atlas.common.fabric.block.station.packet.client.StationOpenPacket;
/*    */ import com.atlas.common.fabric.block.station.packet.server.StationAddPokemonPacket;
/*    */ import com.atlas.common.fabric.packet.AtlasModPacket;
/*    */ import com.cobblemon.mod.common.CobblemonSounds;
/*    */ import com.cobblemon.mod.common.api.reactive.SettableObservable;
/*    */ import com.cobblemon.mod.common.api.storage.StorePosition;
/*    */ import com.cobblemon.mod.common.client.gui.pc.PCGUI;
/*    */ import com.cobblemon.mod.common.client.gui.pc.PCGUIConfiguration;
/*    */ import com.cobblemon.mod.common.pokemon.Pokemon;
/*    */ import java.util.List;
/*    */ import java.util.UUID;
/*    */ import kotlin.Unit;
/*    */ import net.minecraft.class_2338;
/*    */ import net.minecraft.class_8710;
/*    */ 
/*    */ 
/*    */ public class StationPCGUIConfiguration
/*    */   extends PCGUIConfiguration
/*    */ {
/*    */   private final UUID stationId;
/*    */   private final int limit;
/*    */   private final SettableObservable<List<StationOpenPacket.StationPokemonDataDTO>> stationedPokemon;
/*    */   private StationPermissions permissions;
/*    */   private class_2338 blockPos;
/*    */   
/*    */   public StationPCGUIConfiguration(UUID stationId, int limit, SettableObservable<List<StationOpenPacket.StationPokemonDataDTO>> stationedPokemon, StationPermissions permissions, class_2338 blockPos) {
/* 29 */     super(pcGui -> { pcGui.closeNormally(true); return null; }(pcGui, position, pokemon) -> { if (pokemon != null && !pokemon.isFainted() && pokemon.getTetheringId() == null && permissions.getCanStation()) { AtlasModPacket.sendToServer((class_8710)new StationAddPokemonPacket(stationId, pokemon.getUuid())); pcGui.playSound(CobblemonSounds.PC_CLICK); }  return null; }false, pokemon -> Boolean.valueOf(
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */           
/* 46 */           (!pokemon.isFainted() && pokemon.getTetheringId() == null)));
/*    */ 
/*    */     
/* 49 */     this.stationId = stationId;
/* 50 */     this.limit = limit;
/* 51 */     this.stationedPokemon = stationedPokemon;
/* 52 */     this.permissions = permissions;
/* 53 */     this.blockPos = blockPos;
/*    */   }
/*    */   
/*    */   public UUID getStationId() {
/* 57 */     return this.stationId;
/*    */   }
/*    */   
/*    */   public int getLimit() {
/* 61 */     return this.limit;
/*    */   }
/*    */   
/*    */   public SettableObservable<List<StationOpenPacket.StationPokemonDataDTO>> getStationedPokemon() {
/* 65 */     return this.stationedPokemon;
/*    */   }
/*    */   
/*    */   public StationPermissions getPermissions() {
/* 69 */     return this.permissions;
/*    */   }
/*    */   
/*    */   public void setPermissions(StationPermissions permissions) {
/* 73 */     this.permissions = permissions;
/*    */   }
/*    */   
/*    */   public class_2338 getBlockPos() {
/* 77 */     return this.blockPos;
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\block\station\StationPCGUIConfiguration.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */