/*    */ package com.atlas.common.fabric.cosmetic.packet;
/*    */ 
/*    */ import com.atlas.common.fabric.cosmetic.EquippedCosmetics;
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
/*    */ public final class ClientboundEquippedCosmeticsUpdatePacket
/*    */   implements AtlasModPacket
/*    */ {
/*    */   @NotNull
/* 24 */   public static final class_8710.class_9154<ClientboundEquippedCosmeticsUpdatePacket> PACKET_ID = new class_8710.class_9154(
/* 25 */       class_2960.method_60655("atlas", "equipped_cosmetics_update"));
/*    */ 
/*    */   
/* 28 */   public static final class_9139<class_2540, ClientboundEquippedCosmeticsUpdatePacket> CODEC = class_9139.method_56438(ClientboundEquippedCosmeticsUpdatePacket::write, ClientboundEquippedCosmeticsUpdatePacket::new);
/*    */   
/*    */   private final List<EquippedCosmetics> updates;
/*    */   
/*    */   public ClientboundEquippedCosmeticsUpdatePacket(@NotNull class_2540 buf) {
/* 33 */     this.updates = buf.method_34066(CosmeticDecoders::decodeEquippedCosmetics);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ClientboundEquippedCosmeticsUpdatePacket(@NotNull List<EquippedCosmetics> updates) {
/* 43 */     this.updates = Objects.<List<EquippedCosmetics>>requireNonNull(updates);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public List<EquippedCosmetics> getUpdates() {
/* 51 */     return this.updates;
/*    */   }
/*    */ 
/*    */   
/*    */   public class_8710.class_9154<? extends class_8710> method_56479() {
/* 56 */     return (class_8710.class_9154)PACKET_ID;
/*    */   }
/*    */ 
/*    */   
/*    */   public void write(class_2540 buf) {
/* 61 */     buf.method_34062(this.updates, CosmeticEncoders::encodeEquippedCosmetics);
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\cosmetic\packet\ClientboundEquippedCosmeticsUpdatePacket.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */