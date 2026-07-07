/*    */ package com.atlas.common.fabric.packet.listener;
/*    */ 
/*    */ import com.atlas.common.event.AtlasEvent;
/*    */ import com.atlas.common.event.AtlasEventBus;
/*    */ import com.atlas.common.fabric.block.station.handler.StationPacketHandler;
/*    */ import com.atlas.common.fabric.block.station.packet.server.StationAddPokemonPacket;
/*    */ import com.atlas.common.fabric.block.station.packet.server.StationRemoveAllPokemonPacket;
/*    */ import com.atlas.common.fabric.block.station.packet.server.StationRemovePokemonPacket;
/*    */ import com.atlas.common.fabric.packet.AtlasModPacket;
/*    */ import com.atlas.common.fabric.packet.PokemonCosmeticPacket;
/*    */ import com.atlas.common.fabric.packet.event.AtlasModPacketServerEvent;
/*    */ import com.atlas.common.fabric.pc.packet.BulkSealPokemonPacket;
/*    */ import com.atlas.common.fabric.pc.packet.SealPokemonPacket;
/*    */ import com.atlas.common.fabric.screen.inputtext.packet.InputTextPayloadPacket;
/*    */ import net.fabricmc.api.EnvType;
/*    */ import net.fabricmc.api.Environment;
/*    */ import net.fabricmc.fabric.api.networking.v1.PacketSender;
/*    */ import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
/*    */ import net.minecraft.class_3222;
/*    */ import net.minecraft.class_8710;
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Environment(EnvType.SERVER)
/*    */ public final class AtlasModServerPacketListener
/*    */ {
/*    */   public static void register() {
/* 32 */     registerHandler(InputTextPayloadPacket.PACKET_ID);
/* 33 */     registerHandler(PokemonCosmeticPacket.PACKET_ID);
/* 34 */     registerHandler(SealPokemonPacket.PACKET_ID);
/* 35 */     registerHandler(BulkSealPokemonPacket.PACKET_ID);
/*    */ 
/*    */     
/* 38 */     registerHandler(StationAddPokemonPacket.PACKET_ID);
/* 39 */     registerHandler(StationRemovePokemonPacket.PACKET_ID);
/* 40 */     registerHandler(StationRemoveAllPokemonPacket.PACKET_ID);
/*    */ 
/*    */     
/* 43 */     StationPacketHandler.register();
/*    */   }
/*    */   
/*    */   private static <T extends AtlasModPacket> void registerHandler(class_8710.class_9154<T> id) {
/* 47 */     ServerPlayNetworking.registerGlobalReceiver(id, (packet, context) -> {
/*    */           try {
/*    */             MinecraftServer server = context.server();
/*    */             
/*    */             class_3222 player = context.player();
/*    */             
/*    */             PacketSender responseSender = context.responseSender();
/*    */             AtlasEventBus.post((AtlasEvent)new AtlasModPacketServerEvent(packet, server, player, responseSender));
/* 55 */           } catch (Exception exception) {
/*    */             throw new RuntimeException(exception);
/*    */           } 
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\packet\listener\AtlasModServerPacketListener.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */