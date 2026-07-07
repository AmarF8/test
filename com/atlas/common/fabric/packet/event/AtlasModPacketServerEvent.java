/*    */ package com.atlas.common.fabric.packet.event;
/*    */ 
/*    */ import com.atlas.common.event.AtlasEvent;
/*    */ import com.atlas.common.fabric.packet.AtlasModPacket;
/*    */ import java.util.Objects;
/*    */ import net.fabricmc.api.EnvType;
/*    */ import net.fabricmc.api.Environment;
/*    */ import net.fabricmc.fabric.api.networking.v1.PacketSender;
/*    */ import net.minecraft.class_3222;
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ import org.jetbrains.annotations.NotNull;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Environment(EnvType.SERVER)
/*    */ public final class AtlasModPacketServerEvent
/*    */   implements AtlasEvent
/*    */ {
/*    */   private final AtlasModPacket packet;
/*    */   private final MinecraftServer server;
/*    */   private final class_3222 player;
/*    */   private final PacketSender responseSender;
/*    */   
/*    */   public AtlasModPacketServerEvent(@NotNull AtlasModPacket packet, @NotNull MinecraftServer server, @NotNull class_3222 player, @NotNull PacketSender responseSender) {
/* 43 */     this.packet = Objects.<AtlasModPacket>requireNonNull(packet);
/* 44 */     this.server = Objects.<MinecraftServer>requireNonNull(server);
/* 45 */     this.player = Objects.<class_3222>requireNonNull(player);
/* 46 */     this.responseSender = Objects.<PacketSender>requireNonNull(responseSender);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public AtlasModPacket getPacket() {
/* 53 */     return this.packet;
/*    */   }
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
/*    */   public <T extends AtlasModPacket> T getPacket(@NotNull Class<T> clazz) {
/* 66 */     return clazz.cast(this.packet);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public MinecraftServer getServer() {
/* 73 */     return this.server;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public class_3222 getPlayer() {
/* 80 */     return this.player;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public PacketSender getResponseSender() {
/* 87 */     return this.responseSender;
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\packet\event\AtlasModPacketServerEvent.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */