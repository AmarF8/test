/*     */ package com.atlas.common.fabric.block.station.packet.client;
/*     */ 
/*     */ import com.cobblemon.mod.common.entity.pokemon.PokemonBehaviourFlag;
/*     */ import com.cobblemon.mod.common.net.IntSize;
/*     */ import com.cobblemon.mod.common.util.BufferUtilsKt;
/*     */ import com.cobblemon.mod.common.util.NetExtensionsKt;
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.class_1799;
/*     */ import net.minecraft.class_2540;
/*     */ import net.minecraft.class_2561;
/*     */ import net.minecraft.class_2960;
/*     */ import net.minecraft.class_9129;
/*     */ import org.jetbrains.annotations.Nullable;
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
/*     */ public final class StationPokemonDataDTO
/*     */   extends Record
/*     */ {
/*     */   private final UUID pokemonId;
/*     */   private final UUID playerId;
/*     */   private final class_2561 displayName;
/*     */   @Nullable
/*     */   private final String ownerName;
/*     */   private final class_2960 species;
/*     */   private final Set<String> aspects;
/*     */   private final class_1799 heldItem;
/*     */   private final int level;
/*     */   private final boolean entityKnown;
/*     */   private final Set<PokemonBehaviourFlag> behaviourFlags;
/*     */   
/*     */   public final String toString() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/block/station/packet/client/StationOpenPacket$StationPokemonDataDTO;)Ljava/lang/String;
/*     */     //   6: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #95	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	7	0	this	Lcom/atlas/common/fabric/block/station/packet/client/StationOpenPacket$StationPokemonDataDTO;
/*     */   }
/*     */   
/*     */   public final int hashCode() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/block/station/packet/client/StationOpenPacket$StationPokemonDataDTO;)I
/*     */     //   6: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #95	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	7	0	this	Lcom/atlas/common/fabric/block/station/packet/client/StationOpenPacket$StationPokemonDataDTO;
/*     */   }
/*     */   
/*     */   public final boolean equals(Object o) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/block/station/packet/client/StationOpenPacket$StationPokemonDataDTO;Ljava/lang/Object;)Z
/*     */     //   7: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #95	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	8	0	this	Lcom/atlas/common/fabric/block/station/packet/client/StationOpenPacket$StationPokemonDataDTO;
/*     */     //   0	8	1	o	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public StationPokemonDataDTO(UUID pokemonId, UUID playerId, class_2561 displayName, @Nullable String ownerName, class_2960 species, Set<String> aspects, class_1799 heldItem, int level, boolean entityKnown, Set<PokemonBehaviourFlag> behaviourFlags) {
/*  95 */     this.pokemonId = pokemonId; this.playerId = playerId; this.displayName = displayName; this.ownerName = ownerName; this.species = species; this.aspects = aspects; this.heldItem = heldItem; this.level = level; this.entityKnown = entityKnown; this.behaviourFlags = behaviourFlags; } public UUID pokemonId() { return this.pokemonId; } public UUID playerId() { return this.playerId; } public class_2561 displayName() { return this.displayName; } @Nullable public String ownerName() { return this.ownerName; } public class_2960 species() { return this.species; } public Set<String> aspects() { return this.aspects; } public class_1799 heldItem() { return this.heldItem; } public int level() { return this.level; } public boolean entityKnown() { return this.entityKnown; } public Set<PokemonBehaviourFlag> behaviourFlags() { return this.behaviourFlags; }
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
/*     */   public static StationPokemonDataDTO decode(class_2540 buf) {
/* 108 */     return new StationPokemonDataDTO(buf
/* 109 */         .method_10790(), buf
/* 110 */         .method_10790(), 
/* 111 */         BufferUtilsKt.readText((class_9129)buf), (String)buf
/* 112 */         .method_43827(class_2540::method_19772), buf
/* 113 */         .method_10810(), new HashSet<>(buf
/* 114 */           .method_34066(class_2540::method_19772)), (class_1799)class_1799.field_49268
/* 115 */         .decode(buf), 
/* 116 */         NetExtensionsKt.readSizedInt((ByteBuf)buf, IntSize.U_SHORT), buf
/* 117 */         .readBoolean(), new HashSet<>(buf
/* 118 */           .method_34066(b -> (PokemonBehaviourFlag)b.method_10818(PokemonBehaviourFlag.class))));
/*     */   }
/*     */ 
/*     */   
/*     */   public void encode(class_2540 buf) {
/* 123 */     buf.method_10797(this.pokemonId);
/* 124 */     buf.method_10797(this.playerId);
/* 125 */     BufferUtilsKt.writeText((class_9129)buf, this.displayName);
/* 126 */     buf.method_43826(this.ownerName, (b, val) -> buf.method_10814(val));
/* 127 */     buf.method_10812(this.species);
/* 128 */     buf.method_34062(this.aspects, class_2540::method_10814);
/* 129 */     class_1799.field_49268.encode(buf, this.heldItem);
/* 130 */     NetExtensionsKt.writeSizedInt((ByteBuf)buf, IntSize.U_SHORT, this.level);
/* 131 */     buf.method_52964(this.entityKnown);
/* 132 */     buf.method_34062(this.behaviourFlags, (b, flag) -> b.method_10817((Enum)flag));
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\block\station\packet\client\StationOpenPacket$StationPokemonDataDTO.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */