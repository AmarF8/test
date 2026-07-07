/*    */ package com.atlas.common.fabric.block.station.packet.client;
/*    */ 
/*    */ import com.atlas.common.fabric.packet.AtlasModPacket;
/*    */ import net.minecraft.class_2540;
/*    */ import net.minecraft.class_2960;
/*    */ import net.minecraft.class_8710;
/*    */ import net.minecraft.class_9139;
/*    */ import net.minecraft.class_9141;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ public final class StationPokemonAddedPacket
/*    */   implements AtlasModPacket
/*    */ {
/*    */   @NotNull
/* 16 */   public static final class_8710.class_9154<StationPokemonAddedPacket> PACKET_ID = new class_8710.class_9154(
/* 17 */       class_2960.method_60655("atlas", "station_pokemon_added"));
/*    */ 
/*    */   
/* 20 */   public static final class_9139<class_2540, StationPokemonAddedPacket> CODEC = class_9139.method_56438(StationPokemonAddedPacket::write, StationPokemonAddedPacket::new);
/*    */   
/*    */   private final StationOpenPacket.StationPokemonDataDTO pokemonData;
/*    */   
/*    */   public StationPokemonAddedPacket(StationOpenPacket.StationPokemonDataDTO pokemonData) {
/* 25 */     this.pokemonData = pokemonData;
/*    */   }
/*    */   
/*    */   public StationPokemonAddedPacket(class_2540 buf) {
/* 29 */     this.pokemonData = StationOpenPacket.StationPokemonDataDTO.decode(buf);
/*    */   }
/*    */ 
/*    */   
/*    */   public void write(class_2540 buf) {
/* 34 */     this.pokemonData.encode(buf);
/*    */   }
/*    */ 
/*    */   
/*    */   public class_8710.class_9154<? extends class_8710> method_56479() {
/* 39 */     return (class_8710.class_9154)PACKET_ID;
/*    */   }
/*    */   
/*    */   public StationOpenPacket.StationPokemonDataDTO getPokemonData() {
/* 43 */     return this.pokemonData;
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\block\station\packet\client\StationPokemonAddedPacket.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */