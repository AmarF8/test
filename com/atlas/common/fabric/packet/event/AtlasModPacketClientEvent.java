/*    */ package com.atlas.common.fabric.packet.event;
/*    */ 
/*    */ import com.atlas.common.event.AtlasEvent;
/*    */ import com.atlas.common.fabric.packet.AtlasModPacket;
/*    */ import java.util.Objects;
/*    */ import net.fabricmc.api.EnvType;
/*    */ import net.fabricmc.api.Environment;
/*    */ import net.fabricmc.fabric.api.networking.v1.PacketSender;
/*    */ import net.minecraft.class_310;
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
/*    */ @Environment(EnvType.CLIENT)
/*    */ public final class AtlasModPacketClientEvent
/*    */   implements AtlasEvent
/*    */ {
/*    */   private final AtlasModPacket packet;
/*    */   private final class_310 client;
/*    */   private final PacketSender responseSender;
/*    */   
/*    */   public AtlasModPacketClientEvent(@NotNull AtlasModPacket packet, @NotNull class_310 client, @NotNull PacketSender responseSender) {
/* 40 */     this.packet = Objects.<AtlasModPacket>requireNonNull(packet);
/* 41 */     this.client = Objects.<class_310>requireNonNull(client);
/* 42 */     this.responseSender = Objects.<PacketSender>requireNonNull(responseSender);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public AtlasModPacket getPacket() {
/* 49 */     return this.packet;
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
/* 62 */     return clazz.cast(this.packet);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public class_310 getClient() {
/* 69 */     return this.client;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public PacketSender getResponseSender() {
/* 76 */     return this.responseSender;
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\packet\event\AtlasModPacketClientEvent.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */