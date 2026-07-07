/*    */ package com.atlas.common.fabric.safari.racing;
/*    */ 
/*    */ import com.atlas.common.fabric.safari.racing.client.RacingHudState;
/*    */ import com.atlas.common.fabric.safari.racing.client.RacingLeaderboardEntry;
/*    */ import com.atlas.common.fabric.safari.racing.packet.RacingCheckpointPacket;
/*    */ import com.atlas.common.fabric.safari.racing.packet.RacingTimingPacket;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.fabricmc.api.EnvType;
/*    */ import net.fabricmc.api.Environment;
/*    */ import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
/*    */ import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
/*    */ import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
/*    */ import net.minecraft.class_310;
/*    */ import net.minecraft.class_634;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class RacingNetworkHelper
/*    */ {
/*    */   public static void registerPacketTypes() {
/* 23 */     PayloadTypeRegistry.playS2C().register(RacingCheckpointPacket.PACKET_ID, RacingCheckpointPacket.CODEC);
/* 24 */     PayloadTypeRegistry.playS2C().register(RacingTimingPacket.PACKET_ID, RacingTimingPacket.CODEC);
/*    */   }
/*    */   
/*    */   @Environment(EnvType.CLIENT)
/*    */   public static void registerClientReceivers() {
/* 29 */     ClientPlayConnectionEvents.DISCONNECT.register((handler, client) -> RacingHudState.getInstance().clear());
/*    */     
/* 31 */     ClientPlayNetworking.registerGlobalReceiver(RacingTimingPacket.PACKET_ID, (packet, context) -> {
/*    */           RacingHudState state = RacingHudState.getInstance();
/*    */ 
/*    */           
/*    */           if (!packet.isActive()) {
/*    */             state.clear();
/*    */ 
/*    */             
/*    */             return;
/*    */           } 
/*    */           
/*    */           List<String> lbNames = packet.getLeaderboardNames();
/*    */           
/*    */           List<Long> lbTimes = packet.getLeaderboardTimes();
/*    */           
/*    */           ArrayList<RacingLeaderboardEntry> entries = new ArrayList<>();
/*    */           
/*    */           for (int i = 0; i < lbNames.size(); i++) {
/*    */             entries.add(new RacingLeaderboardEntry(lbNames.get(i), ((Long)lbTimes.get(i)).longValue(), ((String)lbNames.get(i)).equals(packet.getPlayerName())));
/*    */           }
/*    */           
/*    */           state.updateFromTimingPacket(packet.isActive(), packet.isWaitingForStart(), packet.getCurrentLapTimeMillis(), packet.getPersonalBestMillis(), packet.getEventRemainingMillis(), packet.getCurrentCheckpoint(), packet.getTotalCheckpoints(), packet.getPlayerPosition(), packet.getLapCount(), entries, packet.getCheckpoints());
/*    */         });
/*    */     
/* 55 */     ClientPlayNetworking.registerGlobalReceiver(RacingCheckpointPacket.PACKET_ID, (packet, context) -> RacingHudState.getInstance().updateFromCheckpointPacket(packet.getCheckpointIndex(), packet.getSplitTimeMillis(), packet.getDeltaMillis(), packet.isPurpleSector(), packet.isPersonalBest()));
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\safari\racing\RacingNetworkHelper.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */