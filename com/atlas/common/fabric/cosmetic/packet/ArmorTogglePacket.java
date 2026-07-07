/*    */ package com.atlas.common.fabric.cosmetic.packet;
/*    */ 
/*    */ import com.atlas.common.fabric.cosmetic.ArmorSlot;
/*    */ import com.atlas.common.fabric.packet.AtlasModPacket;
/*    */ import java.util.Objects;
/*    */ import java.util.UUID;
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
/*    */ public final class ArmorTogglePacket
/*    */   implements AtlasModPacket
/*    */ {
/*    */   @NotNull
/* 23 */   public static final class_8710.class_9154<ArmorTogglePacket> PACKET_ID = new class_8710.class_9154(
/* 24 */       class_2960.method_60655("atlas", "armor_toggle"));
/*    */ 
/*    */   
/* 27 */   public static final class_9139<class_2540, ArmorTogglePacket> CODEC = class_9139.method_56438(ArmorTogglePacket::write, ArmorTogglePacket::new);
/*    */   
/*    */   private final UUID playerUuid;
/*    */   private final ArmorSlot slot;
/*    */   private final boolean disabled;
/*    */   
/*    */   public ArmorTogglePacket(@NotNull class_2540 buf) {
/* 34 */     this.playerUuid = buf.method_10790();
/* 35 */     this.slot = (ArmorSlot)buf.method_10818(ArmorSlot.class);
/* 36 */     this.disabled = buf.readBoolean();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ArmorTogglePacket(@NotNull UUID playerUuid, @NotNull ArmorSlot slot, boolean disabled) {
/* 48 */     this.playerUuid = Objects.<UUID>requireNonNull(playerUuid);
/* 49 */     this.slot = Objects.<ArmorSlot>requireNonNull(slot);
/* 50 */     this.disabled = disabled;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public UUID getPlayerUuid() {
/* 58 */     return this.playerUuid;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public ArmorSlot getSlot() {
/* 66 */     return this.slot;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isDisabled() {
/* 73 */     return this.disabled;
/*    */   }
/*    */ 
/*    */   
/*    */   public class_8710.class_9154<? extends class_8710> method_56479() {
/* 78 */     return (class_8710.class_9154)PACKET_ID;
/*    */   }
/*    */ 
/*    */   
/*    */   public void write(class_2540 buf) {
/* 83 */     buf.method_10797(this.playerUuid);
/* 84 */     buf.method_10817((Enum)this.slot);
/* 85 */     buf.method_52964(this.disabled);
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\cosmetic\packet\ArmorTogglePacket.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */