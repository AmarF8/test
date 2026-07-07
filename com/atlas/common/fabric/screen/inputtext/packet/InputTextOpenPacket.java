/*     */ package com.atlas.common.fabric.screen.inputtext.packet;
/*     */ 
/*     */ import com.atlas.common.fabric.packet.AtlasModPacket;
/*     */ import java.util.Objects;
/*     */ import net.minecraft.class_2540;
/*     */ import net.minecraft.class_2561;
/*     */ import net.minecraft.class_2960;
/*     */ import net.minecraft.class_8710;
/*     */ import net.minecraft.class_8824;
/*     */ import net.minecraft.class_9139;
/*     */ import net.minecraft.class_9141;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class InputTextOpenPacket
/*     */   implements AtlasModPacket
/*     */ {
/*     */   @NotNull
/*  23 */   public static final class_8710.class_9154<InputTextOpenPacket> PACKET_ID = new class_8710.class_9154(
/*  24 */       class_2960.method_60655("atlas", "input_text_open"));
/*     */ 
/*     */   
/*  27 */   public static final class_9139<class_2540, InputTextOpenPacket> CODEC = class_9139.method_56438(InputTextOpenPacket::write, InputTextOpenPacket::new);
/*     */   
/*     */   private final String identifier;
/*     */   private final class_2561 title;
/*     */   private final class_2561 subtitle;
/*     */   private final class_2561 buttonText;
/*     */   private final int maximumTextLength;
/*     */   private final int textBoxWidth;
/*     */   private final int textBoxHeight;
/*     */   private final boolean allowEmpty;
/*     */   
/*     */   public InputTextOpenPacket(class_2540 buf) {
/*  39 */     this.identifier = buf.method_19772();
/*  40 */     this.title = (class_2561)class_8824.field_49668.decode(buf);
/*  41 */     this.subtitle = (class_2561)class_8824.field_49668.decode(buf);
/*  42 */     this.buttonText = (class_2561)class_8824.field_49668.decode(buf);
/*  43 */     this.maximumTextLength = buf.readInt();
/*  44 */     this.textBoxWidth = buf.readInt();
/*  45 */     this.textBoxHeight = buf.readInt();
/*  46 */     this.allowEmpty = buf.readBoolean();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InputTextOpenPacket(@NotNull String identifier, @NotNull class_2561 title, @NotNull class_2561 subtitle, @NotNull class_2561 buttonText, int maximumTextLength, int textBoxWidth, int textBoxHeight, boolean allowEmpty) {
/*  70 */     this.identifier = Objects.<String>requireNonNull(identifier);
/*  71 */     this.title = Objects.<class_2561>requireNonNull(title);
/*  72 */     this.subtitle = Objects.<class_2561>requireNonNull(subtitle);
/*  73 */     this.buttonText = Objects.<class_2561>requireNonNull(buttonText);
/*  74 */     this.maximumTextLength = maximumTextLength;
/*  75 */     this.textBoxWidth = textBoxWidth;
/*  76 */     this.textBoxHeight = textBoxHeight;
/*  77 */     this.allowEmpty = allowEmpty;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public String getIdentifier() {
/*  84 */     return this.identifier;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public class_2561 getTitle() {
/*  91 */     return this.title;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public class_2561 getSubtitle() {
/*  98 */     return this.subtitle;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public class_2561 getButtonText() {
/* 105 */     return this.buttonText;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaximumTextLength() {
/* 112 */     return this.maximumTextLength;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTextBoxWidth() {
/* 119 */     return this.textBoxWidth;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTextBoxHeight() {
/* 126 */     return this.textBoxHeight;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAllowEmpty() {
/* 133 */     return this.allowEmpty;
/*     */   }
/*     */ 
/*     */   
/*     */   public class_8710.class_9154<? extends class_8710> method_56479() {
/* 138 */     return (class_8710.class_9154)PACKET_ID;
/*     */   }
/*     */ 
/*     */   
/*     */   public void write(class_2540 buf) {
/* 143 */     buf.method_10814(this.identifier);
/* 144 */     class_8824.field_49668.encode(buf, this.title);
/* 145 */     class_8824.field_49668.encode(buf, this.subtitle);
/* 146 */     class_8824.field_49668.encode(buf, this.buttonText);
/* 147 */     buf.method_53002(this.maximumTextLength);
/* 148 */     buf.method_53002(this.textBoxWidth);
/* 149 */     buf.method_53002(this.textBoxHeight);
/* 150 */     buf.method_52964(this.allowEmpty);
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\screen\inputtext\packet\InputTextOpenPacket.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */