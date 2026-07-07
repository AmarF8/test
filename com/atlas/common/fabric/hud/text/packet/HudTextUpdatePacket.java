/*     */ package com.atlas.common.fabric.hud.text.packet;
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
/*     */ public final class HudTextUpdatePacket
/*     */   implements AtlasModPacket
/*     */ {
/*     */   @NotNull
/*  23 */   public static final class_8710.class_9154<HudTextUpdatePacket> PACKET_ID = new class_8710.class_9154(
/*  24 */       class_2960.method_60655("atlas", "hud_text_update"));
/*     */ 
/*     */   
/*  27 */   public static final class_9139<class_2540, HudTextUpdatePacket> CODEC = class_9139.method_56438(HudTextUpdatePacket::write, HudTextUpdatePacket::new);
/*     */   
/*     */   private final String identifier;
/*     */   private final class_2561 text;
/*     */   private final int xPercentage;
/*     */   private final int yPercentage;
/*     */   private final int x;
/*     */   private final int y;
/*     */   private final boolean shadow;
/*     */   private final boolean centered;
/*     */   private final int maximumWidth;
/*     */   private final int defaultTextColor;
/*     */   private final int defaultBackgroundColor;
/*     */   private final boolean createIfNotExists;
/*     */   
/*     */   public HudTextUpdatePacket(class_2540 buf) {
/*  43 */     this.identifier = buf.method_19772();
/*  44 */     this.text = (class_2561)class_8824.field_49668.decode(buf);
/*  45 */     this.xPercentage = buf.readInt();
/*  46 */     this.yPercentage = buf.readInt();
/*  47 */     this.x = buf.readInt();
/*  48 */     this.y = buf.readInt();
/*  49 */     this.shadow = buf.readBoolean();
/*  50 */     this.centered = buf.readBoolean();
/*  51 */     this.maximumWidth = buf.readInt();
/*  52 */     this.defaultTextColor = buf.readInt();
/*  53 */     this.defaultBackgroundColor = buf.readInt();
/*  54 */     this.createIfNotExists = buf.readBoolean();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public HudTextUpdatePacket(@NotNull String identifier, @NotNull class_2561 text, int xPercentage, int yPercentage, int x, int y, boolean shadow, boolean centered, int maximumWidth, int defaultTextColor, int defaultBackgroundColor, boolean createIfNotExists) {
/*  86 */     this.identifier = Objects.<String>requireNonNull(identifier);
/*  87 */     this.text = Objects.<class_2561>requireNonNull(text);
/*  88 */     this.xPercentage = xPercentage;
/*  89 */     this.yPercentage = yPercentage;
/*  90 */     this.x = x;
/*  91 */     this.y = y;
/*  92 */     this.shadow = shadow;
/*  93 */     this.centered = centered;
/*  94 */     this.maximumWidth = maximumWidth;
/*  95 */     this.defaultTextColor = defaultTextColor;
/*  96 */     this.defaultBackgroundColor = defaultBackgroundColor;
/*  97 */     this.createIfNotExists = createIfNotExists;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public String getIdentifier() {
/* 105 */     return this.identifier;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public class_2561 getText() {
/* 113 */     return this.text;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getXPercentage() {
/* 120 */     return this.xPercentage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getYPercentage() {
/* 127 */     return this.yPercentage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getX() {
/* 134 */     return this.x;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getY() {
/* 141 */     return this.y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasShadow() {
/* 148 */     return this.shadow;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isCentered() {
/* 155 */     return this.centered;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaximumWidth() {
/* 162 */     return this.maximumWidth;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDefaultTextColor() {
/* 169 */     return this.defaultTextColor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDefaultBackgroundColor() {
/* 176 */     return this.defaultBackgroundColor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isCreateIfNotExists() {
/* 183 */     return this.createIfNotExists;
/*     */   }
/*     */ 
/*     */   
/*     */   public class_8710.class_9154<? extends class_8710> method_56479() {
/* 188 */     return (class_8710.class_9154)PACKET_ID;
/*     */   }
/*     */ 
/*     */   
/*     */   public void write(class_2540 buf) {
/* 193 */     buf.method_10814(this.identifier);
/* 194 */     class_8824.field_49668.encode(buf, this.text);
/* 195 */     buf.method_53002(this.xPercentage);
/* 196 */     buf.method_53002(this.yPercentage);
/* 197 */     buf.method_53002(this.x);
/* 198 */     buf.method_53002(this.y);
/* 199 */     buf.method_52964(this.shadow);
/* 200 */     buf.method_52964(this.centered);
/* 201 */     buf.method_53002(this.maximumWidth);
/* 202 */     buf.method_53002(this.defaultTextColor);
/* 203 */     buf.method_53002(this.defaultBackgroundColor);
/* 204 */     buf.method_52964(this.createIfNotExists);
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\hud\text\packet\HudTextUpdatePacket.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */