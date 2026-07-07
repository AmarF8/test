/*     */ package com.atlas.common.fabric.block.station.packet.client;
/*     */ import com.atlas.common.fabric.block.station.StationPermissions;
/*     */ import com.atlas.common.fabric.packet.AtlasModPacket;
/*     */ import com.cobblemon.mod.common.entity.pokemon.PokemonBehaviourFlag;
/*     */ import com.cobblemon.mod.common.net.IntSize;
/*     */ import com.cobblemon.mod.common.util.BufferUtilsKt;
/*     */ import com.cobblemon.mod.common.util.NetExtensionsKt;
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.class_1799;
/*     */ import net.minecraft.class_2338;
/*     */ import net.minecraft.class_2540;
/*     */ import net.minecraft.class_2561;
/*     */ import net.minecraft.class_2960;
/*     */ import net.minecraft.class_8710;
/*     */ import net.minecraft.class_9129;
/*     */ import net.minecraft.class_9139;
/*     */ import net.minecraft.class_9141;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public final class StationOpenPacket implements AtlasModPacket {
/*     */   @NotNull
/*  27 */   public static final class_8710.class_9154<StationOpenPacket> PACKET_ID = new class_8710.class_9154(
/*  28 */       class_2960.method_60655("atlas", "station_open"));
/*     */ 
/*     */ 
/*     */   
/*  32 */   public static final class_9139<class_2540, StationOpenPacket> CODEC = class_9139.method_56438(StationOpenPacket::write, StationOpenPacket::new);
/*     */   
/*     */   private final class_2338 stationPos;
/*     */   private final UUID pcId;
/*     */   private final UUID stationId;
/*     */   private final int limit;
/*     */   private final List<StationPokemonDataDTO> tetheredPokemon;
/*     */   private final StationPermissions permissions;
/*     */   
/*     */   public StationOpenPacket(class_2540 buf) {
/*  42 */     this.stationPos = buf.method_10811();
/*  43 */     this.pcId = buf.method_10790();
/*  44 */     this.stationId = buf.method_10790();
/*  45 */     this.limit = NetExtensionsKt.readSizedInt((ByteBuf)buf, IntSize.U_BYTE);
/*     */     
/*  47 */     int count = NetExtensionsKt.readSizedInt((ByteBuf)buf, IntSize.U_BYTE);
/*  48 */     this.tetheredPokemon = new ArrayList<>();
/*  49 */     for (int i = 0; i < count; i++) {
/*  50 */       this.tetheredPokemon.add(StationPokemonDataDTO.decode(buf));
/*     */     }
/*     */     
/*  53 */     this.permissions = StationPermissions.decode(buf);
/*     */   }
/*     */ 
/*     */   
/*     */   public StationOpenPacket(@NotNull class_2338 stationPos, UUID pcId, UUID stationId, int limit, List<StationPokemonDataDTO> tetheredPokemon, StationPermissions permissions) {
/*  58 */     this.stationPos = stationPos;
/*  59 */     this.pcId = pcId;
/*  60 */     this.stationId = stationId;
/*  61 */     this.limit = limit;
/*  62 */     this.tetheredPokemon = tetheredPokemon;
/*  63 */     this.permissions = permissions;
/*     */   }
/*     */ 
/*     */   
/*     */   public void write(class_2540 buf) {
/*  68 */     buf.method_10807(this.stationPos);
/*  69 */     buf.method_10797(this.pcId);
/*  70 */     buf.method_10797(this.stationId);
/*  71 */     NetExtensionsKt.writeSizedInt((ByteBuf)buf, IntSize.U_BYTE, this.limit);
/*  72 */     NetExtensionsKt.writeSizedInt((ByteBuf)buf, IntSize.U_BYTE, this.tetheredPokemon.size());
/*  73 */     for (StationPokemonDataDTO tethered : this.tetheredPokemon) {
/*  74 */       tethered.encode(buf);
/*     */     }
/*  76 */     this.permissions.encode(buf);
/*     */   }
/*     */ 
/*     */   
/*     */   public class_8710.class_9154<? extends class_8710> method_56479() {
/*  81 */     return (class_8710.class_9154)PACKET_ID;
/*     */   }
/*     */   
/*     */   public class_2338 getStationPos() {
/*  85 */     return this.stationPos;
/*  86 */   } public UUID getPcId() { return this.pcId; }
/*  87 */   public UUID getStationId() { return this.stationId; }
/*  88 */   public int getLimit() { return this.limit; }
/*  89 */   public List<StationPokemonDataDTO> getTetheredPokemon() { return this.tetheredPokemon; } public StationPermissions getPermissions() {
/*  90 */     return this.permissions;
/*     */   }
/*     */   public static final class StationPokemonDataDTO extends Record { private final UUID pokemonId; private final UUID playerId; private final class_2561 displayName; @Nullable
/*     */     private final String ownerName; private final class_2960 species; private final Set<String> aspects; private final class_1799 heldItem; private final int level; private final boolean entityKnown; private final Set<PokemonBehaviourFlag> behaviourFlags;
/*     */     
/*  95 */     public StationPokemonDataDTO(UUID pokemonId, UUID playerId, class_2561 displayName, @Nullable String ownerName, class_2960 species, Set<String> aspects, class_1799 heldItem, int level, boolean entityKnown, Set<PokemonBehaviourFlag> behaviourFlags) { this.pokemonId = pokemonId; this.playerId = playerId; this.displayName = displayName; this.ownerName = ownerName; this.species = species; this.aspects = aspects; this.heldItem = heldItem; this.level = level; this.entityKnown = entityKnown; this.behaviourFlags = behaviourFlags; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/block/station/packet/client/StationOpenPacket$StationPokemonDataDTO;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #95	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*  95 */       //   0	7	0	this	Lcom/atlas/common/fabric/block/station/packet/client/StationOpenPacket$StationPokemonDataDTO; } public UUID pokemonId() { return this.pokemonId; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/block/station/packet/client/StationOpenPacket$StationPokemonDataDTO;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #95	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/block/station/packet/client/StationOpenPacket$StationPokemonDataDTO; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/block/station/packet/client/StationOpenPacket$StationPokemonDataDTO;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #95	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lcom/atlas/common/fabric/block/station/packet/client/StationOpenPacket$StationPokemonDataDTO;
/*  95 */       //   0	8	1	o	Ljava/lang/Object; } public UUID playerId() { return this.playerId; } public class_2561 displayName() { return this.displayName; } @Nullable public String ownerName() { return this.ownerName; } public class_2960 species() { return this.species; } public Set<String> aspects() { return this.aspects; } public class_1799 heldItem() { return this.heldItem; } public int level() { return this.level; } public boolean entityKnown() { return this.entityKnown; } public Set<PokemonBehaviourFlag> behaviourFlags() { return this.behaviourFlags; }
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
/*     */     public static StationPokemonDataDTO decode(class_2540 buf) {
/* 108 */       return new StationPokemonDataDTO(buf
/* 109 */           .method_10790(), buf
/* 110 */           .method_10790(), 
/* 111 */           BufferUtilsKt.readText((class_9129)buf), (String)buf
/* 112 */           .method_43827(class_2540::method_19772), buf
/* 113 */           .method_10810(), new HashSet<>(buf
/* 114 */             .method_34066(class_2540::method_19772)), (class_1799)class_1799.field_49268
/* 115 */           .decode(buf), 
/* 116 */           NetExtensionsKt.readSizedInt((ByteBuf)buf, IntSize.U_SHORT), buf
/* 117 */           .readBoolean(), new HashSet<>(buf
/* 118 */             .method_34066(b -> (PokemonBehaviourFlag)b.method_10818(PokemonBehaviourFlag.class))));
/*     */     }
/*     */ 
/*     */     
/*     */     public void encode(class_2540 buf) {
/* 123 */       buf.method_10797(this.pokemonId);
/* 124 */       buf.method_10797(this.playerId);
/* 125 */       BufferUtilsKt.writeText((class_9129)buf, this.displayName);
/* 126 */       buf.method_43826(this.ownerName, (b, val) -> buf.method_10814(val));
/* 127 */       buf.method_10812(this.species);
/* 128 */       buf.method_34062(this.aspects, class_2540::method_10814);
/* 129 */       class_1799.field_49268.encode(buf, this.heldItem);
/* 130 */       NetExtensionsKt.writeSizedInt((ByteBuf)buf, IntSize.U_SHORT, this.level);
/* 131 */       buf.method_52964(this.entityKnown);
/* 132 */       buf.method_34062(this.behaviourFlags, (b, flag) -> b.method_10817((Enum)flag));
/*     */     } }
/*     */ 
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\block\station\packet\client\StationOpenPacket.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */