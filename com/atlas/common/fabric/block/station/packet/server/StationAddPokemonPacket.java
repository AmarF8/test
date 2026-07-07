/*    */ package com.atlas.common.fabric.block.station.packet.server;
/*    */ 
/*    */ import com.atlas.common.fabric.packet.AtlasModPacket;
/*    */ import java.util.UUID;
/*    */ import net.minecraft.class_2540;
/*    */ import net.minecraft.class_2960;
/*    */ import net.minecraft.class_8710;
/*    */ import net.minecraft.class_9139;
/*    */ import net.minecraft.class_9141;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class StationAddPokemonPacket
/*    */   implements AtlasModPacket
/*    */ {
/*    */   @NotNull
/* 20 */   public static final class_8710.class_9154<StationAddPokemonPacket> PACKET_ID = new class_8710.class_9154(
/* 21 */       class_2960.method_60655("atlas", "station_add_pokemon"));
/*    */ 
/*    */   
/* 24 */   public static final class_9139<class_2540, StationAddPokemonPacket> CODEC = class_9139.method_56438(StationAddPokemonPacket::write, StationAddPokemonPacket::new);
/*    */   
/*    */   private final UUID stationId;
/*    */   private final UUID pokemonId;
/*    */   
/*    */   public StationAddPokemonPacket(class_2540 buf) {
/* 30 */     this.stationId = buf.method_10790();
/* 31 */     this.pokemonId = buf.method_10790();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public StationAddPokemonPacket(@NotNull UUID stationId, @NotNull UUID pokemonId) {
/* 41 */     this.stationId = stationId;
/* 42 */     this.pokemonId = pokemonId;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public UUID getStationId() {
/* 47 */     return this.stationId;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public UUID getPokemonId() {
/* 52 */     return this.pokemonId;
/*    */   }
/*    */ 
/*    */   
/*    */   public class_8710.class_9154<? extends class_8710> method_56479() {
/* 57 */     return (class_8710.class_9154)PACKET_ID;
/*    */   }
/*    */ 
/*    */   
/*    */   public void write(class_2540 buf) {
/* 62 */     buf.method_10797(this.stationId);
/* 63 */     buf.method_10797(this.pokemonId);
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\block\station\packet\server\StationAddPokemonPacket.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */