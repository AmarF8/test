/*    */ package com.atlas.common.fabric.pc.packet;
/*    */ import com.atlas.common.fabric.packet.AtlasModPacket;
/*    */ import java.util.UUID;
/*    */ import net.minecraft.class_2540;
/*    */ import net.minecraft.class_2960;
/*    */ import net.minecraft.class_8710;
/*    */ import net.minecraft.class_9139;
/*    */ import net.minecraft.class_9141;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public final class SealPokemonPacket implements AtlasModPacket {
/*    */   @NotNull
/* 13 */   public static final class_8710.class_9154<SealPokemonPacket> PACKET_ID = new class_8710.class_9154(
/* 14 */       class_2960.method_60655("atlas", "seal_pokemon"));
/*    */ 
/*    */   
/* 17 */   public static final class_9139<class_2540, SealPokemonPacket> CODEC = class_9139.method_56438(SealPokemonPacket::write, SealPokemonPacket::new);
/*    */   
/*    */   private final UUID pokemonId;
/*    */   private final int box;
/*    */   private final int slot;
/*    */   
/*    */   public SealPokemonPacket(class_2540 buf) {
/* 24 */     this.pokemonId = buf.method_10790();
/* 25 */     this.box = buf.readInt();
/* 26 */     this.slot = buf.readInt();
/*    */   }
/*    */   
/*    */   public SealPokemonPacket(@NotNull UUID pokemonId, int box, int slot) {
/* 30 */     this.pokemonId = pokemonId;
/* 31 */     this.box = box;
/* 32 */     this.slot = slot;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public UUID getPokemonId() {
/* 37 */     return this.pokemonId;
/*    */   }
/*    */   
/*    */   public int getBox() {
/* 41 */     return this.box;
/*    */   }
/*    */   
/*    */   public int getSlot() {
/* 45 */     return this.slot;
/*    */   }
/*    */ 
/*    */   
/*    */   public class_8710.class_9154<? extends class_8710> method_56479() {
/* 50 */     return (class_8710.class_9154)PACKET_ID;
/*    */   }
/*    */ 
/*    */   
/*    */   public void write(class_2540 buf) {
/* 55 */     buf.method_10797(this.pokemonId);
/* 56 */     buf.method_53002(this.box);
/* 57 */     buf.method_53002(this.slot);
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\pc\packet\SealPokemonPacket.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */