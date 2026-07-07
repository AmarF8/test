/*    */ package com.atlas.common.fabric.pc.packet;
/*    */ import com.atlas.common.fabric.packet.AtlasModPacket;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.UUID;
/*    */ import net.minecraft.class_2540;
/*    */ import net.minecraft.class_2960;
/*    */ import net.minecraft.class_8710;
/*    */ import net.minecraft.class_9139;
/*    */ import net.minecraft.class_9141;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public final class BulkSealPokemonPacket implements AtlasModPacket {
/*    */   @NotNull
/* 15 */   public static final class_8710.class_9154<BulkSealPokemonPacket> PACKET_ID = new class_8710.class_9154(
/* 16 */       class_2960.method_60655("atlas", "bulk_seal_pokemon"));
/*    */ 
/*    */   
/* 19 */   public static final class_9139<class_2540, BulkSealPokemonPacket> CODEC = class_9139.method_56438(BulkSealPokemonPacket::write, BulkSealPokemonPacket::new);
/*    */   
/*    */   private final List<Entry> entries;
/*    */   
/*    */   public BulkSealPokemonPacket(class_2540 buf) {
/* 24 */     int size = buf.readInt();
/* 25 */     List<Entry> entries = new ArrayList<>(size);
/* 26 */     for (int i = 0; i < size; i++) {
/* 27 */       entries.add(new Entry(buf.method_10790(), buf.readInt(), buf.readInt()));
/*    */     }
/* 29 */     this.entries = entries;
/*    */   }
/*    */   
/*    */   public BulkSealPokemonPacket(@NotNull List<Entry> entries) {
/* 33 */     this.entries = List.copyOf(entries);
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public List<Entry> getEntries() {
/* 38 */     return this.entries;
/*    */   }
/*    */ 
/*    */   
/*    */   public class_8710.class_9154<? extends class_8710> method_56479() {
/* 43 */     return (class_8710.class_9154)PACKET_ID;
/*    */   }
/*    */ 
/*    */   
/*    */   public void write(class_2540 buf) {
/* 48 */     buf.method_53002(this.entries.size());
/* 49 */     for (Entry entry : this.entries) {
/* 50 */       buf.method_10797(entry.pokemonId());
/* 51 */       buf.method_53002(entry.box());
/* 52 */       buf.method_53002(entry.slot());
/*    */     } 
/*    */   } public static final class Entry extends Record { @NotNull
/*    */     private final UUID pokemonId; private final int box; private final int slot;
/* 56 */     public Entry(@NotNull UUID pokemonId, int box, int slot) { this.pokemonId = pokemonId; this.box = box; this.slot = slot; } public final String toString() { // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/pc/packet/BulkSealPokemonPacket$Entry;)Ljava/lang/String;
/*    */       //   6: areturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #56	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/* 56 */       //   0	7	0	this	Lcom/atlas/common/fabric/pc/packet/BulkSealPokemonPacket$Entry; } @NotNull public UUID pokemonId() { return this.pokemonId; } public final int hashCode() { // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/pc/packet/BulkSealPokemonPacket$Entry;)I
/*    */       //   6: ireturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #56	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	7	0	this	Lcom/atlas/common/fabric/pc/packet/BulkSealPokemonPacket$Entry; } public final boolean equals(Object o) { // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: aload_1
/*    */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/pc/packet/BulkSealPokemonPacket$Entry;Ljava/lang/Object;)Z
/*    */       //   7: ireturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #56	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	8	0	this	Lcom/atlas/common/fabric/pc/packet/BulkSealPokemonPacket$Entry;
/* 56 */       //   0	8	1	o	Ljava/lang/Object; } public int box() { return this.box; } public int slot() { return this.slot; }
/*    */      }
/*    */ 
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\pc\packet\BulkSealPokemonPacket.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */