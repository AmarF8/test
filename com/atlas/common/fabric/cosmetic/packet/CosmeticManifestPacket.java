/*    */ package com.atlas.common.fabric.cosmetic.packet;
/*    */ 
/*    */ import com.atlas.common.fabric.cosmetic.Cosmetic;
/*    */ import com.atlas.common.fabric.packet.AtlasModPacket;
/*    */ import java.util.List;
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
/*    */ 
/*    */ public final class CosmeticManifestPacket
/*    */   implements AtlasModPacket
/*    */ {
/*    */   @NotNull
/* 24 */   public static final class_8710.class_9154<CosmeticManifestPacket> PACKET_ID = new class_8710.class_9154(
/* 25 */       class_2960.method_60655("atlas", "cosmetic_manifest"));
/*    */ 
/*    */   
/* 28 */   public static final class_9139<class_2540, CosmeticManifestPacket> CODEC = class_9139.method_56438(CosmeticManifestPacket::write, CosmeticManifestPacket::new);
/*    */   
/*    */   private final List<Cosmetic> cosmetics;
/*    */   
/*    */   public CosmeticManifestPacket(@NotNull class_2540 buf) {
/* 33 */     this.cosmetics = buf.method_34066(CosmeticDecoders::decodeCosmetic);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public CosmeticManifestPacket(@NotNull List<Cosmetic> cosmetics) {
/* 43 */     this.cosmetics = Objects.<List<Cosmetic>>requireNonNull(cosmetics);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public List<Cosmetic> getCosmetics() {
/* 51 */     return this.cosmetics;
/*    */   }
/*    */ 
/*    */   
/*    */   public class_8710.class_9154<? extends class_8710> method_56479() {
/* 56 */     return (class_8710.class_9154)PACKET_ID;
/*    */   }
/*    */ 
/*    */   
/*    */   public void write(class_2540 buf) {
/* 61 */     buf.method_34062(this.cosmetics, CosmeticEncoders::encodeCosmetic);
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\cosmetic\packet\CosmeticManifestPacket.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */