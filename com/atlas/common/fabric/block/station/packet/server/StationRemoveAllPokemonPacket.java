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
/*    */ public final class StationRemoveAllPokemonPacket
/*    */   implements AtlasModPacket
/*    */ {
/*    */   @NotNull
/* 18 */   public static final class_8710.class_9154<StationRemoveAllPokemonPacket> PACKET_ID = new class_8710.class_9154(
/* 19 */       class_2960.method_60655("atlas", "station_remove_all_pokemon"));
/*    */ 
/*    */   
/* 22 */   public static final class_9139<class_2540, StationRemoveAllPokemonPacket> CODEC = class_9139.method_56438(StationRemoveAllPokemonPacket::write, StationRemoveAllPokemonPacket::new);
/*    */   
/*    */   private final UUID stationId;
/*    */   
/*    */   public StationRemoveAllPokemonPacket(UUID stationId) {
/* 27 */     this.stationId = stationId;
/*    */   }
/*    */   
/*    */   public StationRemoveAllPokemonPacket(class_2540 buf) {
/* 31 */     this.stationId = buf.method_10790();
/*    */   }
/*    */ 
/*    */   
/*    */   public void write(class_2540 buf) {
/* 36 */     buf.method_10797(this.stationId);
/*    */   }
/*    */ 
/*    */   
/*    */   public class_8710.class_9154<? extends class_8710> method_56479() {
/* 41 */     return (class_8710.class_9154)PACKET_ID;
/*    */   }
/*    */   
/*    */   public UUID getStationId() {
/* 45 */     return this.stationId;
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\block\station\packet\server\StationRemoveAllPokemonPacket.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */