/*    */ package com.atlas.common.fabric.block.station.packet.client;
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
/*    */ public final class StationPokemonRemovedPacket
/*    */   implements AtlasModPacket
/*    */ {
/*    */   @NotNull
/* 18 */   public static final class_8710.class_9154<StationPokemonRemovedPacket> PACKET_ID = new class_8710.class_9154(
/* 19 */       class_2960.method_60655("atlas", "station_pokemon_removed"));
/*    */ 
/*    */   
/* 22 */   public static final class_9139<class_2540, StationPokemonRemovedPacket> CODEC = class_9139.method_56438(StationPokemonRemovedPacket::write, StationPokemonRemovedPacket::new);
/*    */   
/*    */   private final UUID pokemonId;
/*    */   
/*    */   public StationPokemonRemovedPacket(UUID pokemonId) {
/* 27 */     this.pokemonId = pokemonId;
/*    */   }
/*    */   
/*    */   public StationPokemonRemovedPacket(class_2540 buf) {
/* 31 */     this.pokemonId = buf.method_10790();
/*    */   }
/*    */ 
/*    */   
/*    */   public void write(class_2540 buf) {
/* 36 */     buf.method_10797(this.pokemonId);
/*    */   }
/*    */ 
/*    */   
/*    */   public class_8710.class_9154<? extends class_8710> method_56479() {
/* 41 */     return (class_8710.class_9154)PACKET_ID;
/*    */   }
/*    */   
/*    */   public UUID getPokemonId() {
/* 45 */     return this.pokemonId;
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\block\station\packet\client\StationPokemonRemovedPacket.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */