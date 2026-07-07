/*     */ package com.atlas.common.fabric.block.crate.packet;
/*     */ 
/*     */ import com.atlas.common.fabric.block.crate.entity.CrateBlockEntity;
/*     */ import com.atlas.common.fabric.packet.AtlasModPacket;
/*     */ import net.fabricmc.api.EnvType;
/*     */ import net.fabricmc.api.Environment;
/*     */ import net.minecraft.class_2338;
/*     */ import net.minecraft.class_2540;
/*     */ import net.minecraft.class_2960;
/*     */ import net.minecraft.class_310;
/*     */ import net.minecraft.class_638;
/*     */ import net.minecraft.class_8710;
/*     */ import net.minecraft.class_9139;
/*     */ import net.minecraft.class_9141;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class CrateOpenPacket
/*     */   implements AtlasModPacket
/*     */ {
/*     */   @NotNull
/*  25 */   public static final class_8710.class_9154<CrateOpenPacket> PACKET_ID = new class_8710.class_9154(
/*  26 */       class_2960.method_60655("atlas", "crate_open"));
/*     */ 
/*     */   
/*  29 */   public static final class_9139<class_2540, CrateOpenPacket> CODEC = class_9139.method_56438(CrateOpenPacket::write, CrateOpenPacket::new);
/*     */   
/*     */   private final int x;
/*     */   private final int y;
/*     */   private final int z;
/*     */   
/*     */   public CrateOpenPacket(class_2540 buf) {
/*  36 */     this.x = buf.readInt();
/*  37 */     this.y = buf.readInt();
/*  38 */     this.z = buf.readInt();
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
/*     */   public CrateOpenPacket(int x, int y, int z) {
/*  50 */     this.x = x;
/*  51 */     this.y = y;
/*  52 */     this.z = z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getX() {
/*  59 */     return this.x;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getY() {
/*  66 */     return this.y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getZ() {
/*  73 */     return this.z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public class_2338 createBlockPos() {
/*  81 */     return new class_2338(this.x, this.y, this.z);
/*     */   }
/*     */ 
/*     */   
/*     */   public class_8710.class_9154<? extends class_8710> method_56479() {
/*  86 */     return (class_8710.class_9154)PACKET_ID;
/*     */   }
/*     */ 
/*     */   
/*     */   public void write(class_2540 buf) {
/*  91 */     buf.method_53002(this.x);
/*  92 */     buf.method_53002(this.y);
/*  93 */     buf.method_53002(this.z);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   @Environment(EnvType.CLIENT)
/*     */   public CrateBlockEntity getBlockEntity() {
/* 102 */     class_638 class_638 = (class_310.method_1551()).field_1687;
/*     */     
/* 104 */     if (class_638 == null) {
/* 105 */       throw new IllegalStateException("World is null");
/*     */     }
/*     */     
/* 108 */     return (CrateBlockEntity)class_638.method_8321(createBlockPos());
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\block\crate\packet\CrateOpenPacket.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */