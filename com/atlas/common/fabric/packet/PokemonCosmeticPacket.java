/*    */ package com.atlas.common.fabric.packet;
/*    */ 
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
/*    */ public final class PokemonCosmeticPacket
/*    */   implements AtlasModPacket
/*    */ {
/*    */   @NotNull
/* 21 */   public static final class_8710.class_9154<PokemonCosmeticPacket> PACKET_ID = new class_8710.class_9154(
/* 22 */       class_2960.method_60655("atlas", "pokemon_cosmetic"));
/*    */ 
/*    */   
/* 25 */   public static final class_9139<class_2540, PokemonCosmeticPacket> CODEC = class_9139.method_56438(PokemonCosmeticPacket::write, PokemonCosmeticPacket::new);
/*    */   
/*    */   private final UUID pokemonID;
/*    */   
/*    */   public PokemonCosmeticPacket(@NotNull class_2540 buf) {
/* 30 */     this.pokemonID = buf.method_10790();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public PokemonCosmeticPacket(@NotNull UUID pokemonID) {
/* 40 */     this.pokemonID = Objects.<UUID>requireNonNull(pokemonID);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public UUID getPokemonID() {
/* 48 */     return this.pokemonID;
/*    */   }
/*    */ 
/*    */   
/*    */   public class_8710.class_9154<? extends class_8710> method_56479() {
/* 53 */     return (class_8710.class_9154)PACKET_ID;
/*    */   }
/*    */ 
/*    */   
/*    */   public void write(class_2540 buf) {
/* 58 */     buf.method_10797(this.pokemonID);
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\packet\PokemonCosmeticPacket.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */