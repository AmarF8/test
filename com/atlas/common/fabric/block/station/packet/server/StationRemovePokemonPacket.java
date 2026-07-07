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
/*    */ 
/*    */ public final class StationRemovePokemonPacket
/*    */   implements AtlasModPacket
/*    */ {
/*    */   @NotNull
/* 21 */   public static final class_8710.class_9154<StationRemovePokemonPacket> PACKET_ID = new class_8710.class_9154(
/* 22 */       class_2960.method_60655("atlas", "station_remove_pokemon"));
/*    */ 
/*    */   
/* 25 */   public static final class_9139<class_2540, StationRemovePokemonPacket> CODEC = class_9139.method_56438(StationRemovePokemonPacket::write, StationRemovePokemonPacket::new);
/*    */   
/*    */   private final UUID stationId;
/*    */   private final UUID pokemonId;
/*    */   
/*    */   public StationRemovePokemonPacket(class_2540 buf) {
/* 31 */     this.stationId = buf.method_10790();
/* 32 */     this.pokemonId = buf.method_10790();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public StationRemovePokemonPacket(@NotNull UUID stationId, @NotNull UUID pokemonId) {
/* 42 */     this.stationId = stationId;
/* 43 */     this.pokemonId = pokemonId;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public UUID getStationId() {
/* 48 */     return this.stationId;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public UUID getPokemonId() {
/* 53 */     return this.pokemonId;
/*    */   }
/*    */ 
/*    */   
/*    */   public class_8710.class_9154<? extends class_8710> method_56479() {
/* 58 */     return (class_8710.class_9154)PACKET_ID;
/*    */   }
/*    */ 
/*    */   
/*    */   public void write(class_2540 buf) {
/* 63 */     buf.method_10797(this.stationId);
/* 64 */     buf.method_10797(this.pokemonId);
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\block\station\packet\server\StationRemovePokemonPacket.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */