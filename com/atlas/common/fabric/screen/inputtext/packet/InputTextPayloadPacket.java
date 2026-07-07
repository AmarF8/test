/*    */ package com.atlas.common.fabric.screen.inputtext.packet;
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
/*    */ public final class InputTextPayloadPacket
/*    */   implements AtlasModPacket
/*    */ {
/*    */   @NotNull
/* 21 */   public static final class_8710.class_9154<InputTextPayloadPacket> PACKET_ID = new class_8710.class_9154(
/* 22 */       class_2960.method_60655("atlas", "input_text_payload"));
/*    */ 
/*    */   
/* 25 */   public static final class_9139<class_2540, InputTextPayloadPacket> CODEC = class_9139.method_56438(InputTextPayloadPacket::write, InputTextPayloadPacket::new);
/*    */   
/*    */   private final String identifier;
/*    */   private final String text;
/*    */   
/*    */   public InputTextPayloadPacket(@NotNull class_2540 buf) {
/* 31 */     this.identifier = buf.method_19772();
/* 32 */     this.text = buf.method_19772();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public InputTextPayloadPacket(@NotNull String identifier, @NotNull String text) {
/* 43 */     this.identifier = Objects.<String>requireNonNull(identifier);
/* 44 */     this.text = Objects.<String>requireNonNull(text);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public String getIdentifier() {
/* 52 */     return this.identifier;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public String getText() {
/* 60 */     return this.text;
/*    */   }
/*    */ 
/*    */   
/*    */   public class_8710.class_9154<? extends class_8710> method_56479() {
/* 65 */     return (class_8710.class_9154)PACKET_ID;
/*    */   }
/*    */ 
/*    */   
/*    */   public void write(class_2540 buf) {
/* 70 */     buf.method_10814(this.identifier);
/* 71 */     buf.method_10814(this.text);
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\screen\inputtext\packet\InputTextPayloadPacket.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */