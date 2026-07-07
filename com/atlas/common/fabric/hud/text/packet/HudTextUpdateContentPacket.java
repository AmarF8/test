/*    */ package com.atlas.common.fabric.hud.text.packet;
/*    */ 
/*    */ import com.atlas.common.fabric.packet.AtlasModPacket;
/*    */ import java.util.Objects;
/*    */ import net.minecraft.class_2540;
/*    */ import net.minecraft.class_2561;
/*    */ import net.minecraft.class_2960;
/*    */ import net.minecraft.class_8710;
/*    */ import net.minecraft.class_8824;
/*    */ import net.minecraft.class_9139;
/*    */ import net.minecraft.class_9141;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class HudTextUpdateContentPacket
/*    */   implements AtlasModPacket
/*    */ {
/*    */   @NotNull
/* 23 */   public static final class_8710.class_9154<HudTextUpdateContentPacket> PACKET_ID = new class_8710.class_9154(
/* 24 */       class_2960.method_60655("atlas", "hud_text_update_content"));
/*    */ 
/*    */   
/* 27 */   public static final class_9139<class_2540, HudTextUpdateContentPacket> CODEC = class_9139.method_56438(HudTextUpdateContentPacket::write, HudTextUpdateContentPacket::new);
/*    */   
/*    */   private final String identifier;
/*    */   private final class_2561 text;
/*    */   
/*    */   public HudTextUpdateContentPacket(class_2540 buf) {
/* 33 */     this.identifier = buf.method_19772();
/* 34 */     this.text = (class_2561)class_8824.field_49668.decode(buf);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public HudTextUpdateContentPacket(String identifier, class_2561 text) {
/* 45 */     this.identifier = Objects.<String>requireNonNull(identifier);
/* 46 */     this.text = Objects.<class_2561>requireNonNull(text);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public String getIdentifier() {
/* 54 */     return this.identifier;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public class_2561 getText() {
/* 62 */     return this.text;
/*    */   }
/*    */ 
/*    */   
/*    */   public class_8710.class_9154<? extends class_8710> method_56479() {
/* 67 */     return (class_8710.class_9154)PACKET_ID;
/*    */   }
/*    */ 
/*    */   
/*    */   public void write(class_2540 buf) {
/* 72 */     buf.method_10814(this.identifier);
/* 73 */     class_8824.field_49668.encode(buf, this.text);
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\hud\text\packet\HudTextUpdateContentPacket.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */