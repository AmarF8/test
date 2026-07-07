/*    */ package com.atlas.common.fabric.block.station.packet.client;
/*    */ import com.atlas.common.fabric.packet.AtlasModPacket;
/*    */ import net.minecraft.class_2540;
/*    */ import net.minecraft.class_2960;
/*    */ import net.minecraft.class_8710;
/*    */ import net.minecraft.class_9139;
/*    */ import net.minecraft.class_9141;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public final class StationRenderStatePacket implements AtlasModPacket {
/*    */   @NotNull
/* 12 */   public static final class_8710.class_9154<StationRenderStatePacket> PACKET_ID = new class_8710.class_9154(
/* 13 */       class_2960.method_60655("atlas", "station_render_state"));
/*    */ 
/*    */   
/* 16 */   public static final class_9139<class_2540, StationRenderStatePacket> CODEC = class_9139.method_56438(StationRenderStatePacket::write, StationRenderStatePacket::new);
/*    */   
/*    */   private final boolean enabled;
/*    */   
/*    */   public StationRenderStatePacket(boolean enabled) {
/* 21 */     this.enabled = enabled;
/*    */   }
/*    */   
/*    */   public StationRenderStatePacket(class_2540 buf) {
/* 25 */     this.enabled = buf.readBoolean();
/*    */   }
/*    */ 
/*    */   
/*    */   public void write(class_2540 buf) {
/* 30 */     buf.method_52964(this.enabled);
/*    */   }
/*    */ 
/*    */   
/*    */   public class_8710.class_9154<? extends class_8710> method_56479() {
/* 35 */     return (class_8710.class_9154)PACKET_ID;
/*    */   }
/*    */   
/*    */   public boolean isEnabled() {
/* 39 */     return this.enabled;
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\block\station\packet\client\StationRenderStatePacket.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */