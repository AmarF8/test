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
/*    */ public final class StationClosePacket
/*    */   implements AtlasModPacket
/*    */ {
/*    */   @NotNull
/* 16 */   public static final class_8710.class_9154<StationClosePacket> PACKET_ID = new class_8710.class_9154(
/* 17 */       class_2960.method_60655("atlas", "station_close"));
/*    */ 
/*    */   
/* 20 */   public static final class_9139<class_2540, StationClosePacket> CODEC = class_9139.method_56438(StationClosePacket::write, StationClosePacket::new);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public StationClosePacket(class_2540 buf) {}
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public StationClosePacket() {}
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void write(class_2540 buf) {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public class_8710.class_9154<? extends class_8710> method_56479() {
/* 44 */     return (class_8710.class_9154)PACKET_ID;
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\block\station\packet\client\StationClosePacket.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */