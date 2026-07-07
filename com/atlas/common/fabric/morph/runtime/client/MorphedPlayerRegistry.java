/*    */ package com.atlas.common.fabric.morph.runtime.client;
/*    */ 
/*    */ import com.atlas.common.fabric.morph.runtime.network.MorphRuntimeNetwork;
/*    */ import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
/*    */ import java.util.Map;
/*    */ import java.util.UUID;
/*    */ import java.util.concurrent.ConcurrentHashMap;
/*    */ import net.fabricmc.api.EnvType;
/*    */ import net.fabricmc.api.Environment;
/*    */ import net.minecraft.class_1297;
/*    */ import net.minecraft.class_310;
/*    */ import net.minecraft.class_638;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Environment(EnvType.CLIENT)
/*    */ public final class MorphedPlayerRegistry
/*    */ {
/* 23 */   private static final MorphedPlayerRegistry INSTANCE = new MorphedPlayerRegistry(); public static MorphedPlayerRegistry get() {
/* 24 */     return INSTANCE;
/*    */   }
/* 26 */   private final Map<UUID, Entry> entries = new ConcurrentHashMap<>();
/*    */ 
/*    */ 
/*    */   
/*    */   public void apply(MorphRuntimeNetwork.MorphStatePayload payload) {
/* 31 */     if (!payload.active()) {
/* 32 */       this.entries.remove(payload.playerId());
/* 33 */       MorphPokemonRenderState.clear(payload.playerId());
/*    */       return;
/*    */     } 
/* 36 */     this.entries.put(payload.playerId(), new Entry(payload
/* 37 */           .playerId(), payload
/* 38 */           .species(), payload
/* 39 */           .aspects(), payload
/* 40 */           .moves(), payload
/* 41 */           .mountEntityId()));
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public Entry get(UUID playerId) {
/* 46 */     return this.entries.get(playerId);
/*    */   }
/*    */   
/*    */   public boolean isMorphed(UUID playerId) {
/* 50 */     return this.entries.containsKey(playerId);
/*    */   }
/*    */   
/*    */   public void clearAll() {
/* 54 */     this.entries.clear();
/* 55 */     MorphPokemonRenderState.clearAll();
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public PokemonEntity getMount(UUID playerId) {
/* 61 */     Entry e = this.entries.get(playerId);
/* 62 */     if (e == null || e.mountEntityId < 0) return null; 
/* 63 */     class_638 world = (class_310.method_1551()).field_1687;
/* 64 */     if (world == null) return null; 
/* 65 */     class_1297 ent = world.method_8469(e.mountEntityId);
/* 66 */     PokemonEntity pe = (PokemonEntity)ent; return (ent instanceof PokemonEntity) ? pe : null;
/*    */   }
/*    */   
/*    */   public static final class Entry {
/*    */     public final UUID playerId;
/*    */     public final String species;
/*    */     public final String aspects;
/*    */     public final String moves;
/*    */     public final int mountEntityId;
/*    */     
/*    */     Entry(UUID playerId, String species, String aspects, String moves, int mountEntityId) {
/* 77 */       this.playerId = playerId;
/* 78 */       this.species = species;
/* 79 */       this.aspects = aspects;
/* 80 */       this.moves = moves;
/* 81 */       this.mountEntityId = mountEntityId;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\morph\runtime\client\MorphedPlayerRegistry.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */