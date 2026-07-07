/*    */ package com.atlas.common.fabric.hud.text.packet;
/*    */ 
/*    */ import com.atlas.common.fabric.packet.AtlasModPacket;
/*    */ import java.util.Objects;
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
/*    */ public final class HudTextRemovePacket
/*    */   implements AtlasModPacket
/*    */ {
/*    */   @NotNull
/* 21 */   public static final class_8710.class_9154<HudTextRemovePacket> PACKET_ID = new class_8710.class_9154(
/* 22 */       class_2960.method_60655("atlas", "hud_text_remove"));
/*    */ 
/*    */   
/* 25 */   public static final class_9139<class_2540, HudTextRemovePacket> CODEC = class_9139.method_56438(HudTextRemovePacket::write, HudTextRemovePacket::new);
/*    */   
/*    */   private final String identifier;
/*    */   
/*    */   public HudTextRemovePacket(class_2540 buf) {
/* 30 */     this.identifier = buf.method_19772();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public HudTextRemovePacket(String identifier) {
/* 40 */     this.identifier = Objects.<String>requireNonNull(identifier);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public String getIdentifier() {
/* 48 */     return this.identifier;
/*    */   }
/*    */ 
/*    */   
/*    */   public class_8710.class_9154<? extends class_8710> method_56479() {
/* 53 */     return (class_8710.class_9154)PACKET_ID;
/*    */   }
/*    */ 
/*    */   
/*    */   public void write(class_2540 buf) {
/* 58 */     buf.method_10814(this.identifier);
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\hud\text\packet\HudTextRemovePacket.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */