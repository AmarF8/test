/*     */ package com.atlas.common.fabric.utility;
/*     */ import net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents;
/*     */ import net.fabricmc.fabric.api.entity.event.v1.ServerEntityWorldChangeEvents;
/*     */ import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
/*     */ import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
/*     */ import net.fabricmc.fabric.api.event.Event;
/*     */ import net.fabricmc.fabric.api.event.lifecycle.v1.ServerBlockEntityEvents;
/*     */ import net.fabricmc.fabric.api.event.lifecycle.v1.ServerChunkEvents;
/*     */ import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
/*     */ import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
/*     */ import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
/*     */ import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
/*     */ import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
/*     */ import net.fabricmc.fabric.api.event.player.UseEntityCallback;
/*     */ import net.fabricmc.fabric.api.event.player.UseItemCallback;
/*     */ import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
/*     */ import net.fabricmc.fabric.api.message.v1.ServerMessageEvents;
/*     */ import net.fabricmc.fabric.api.networking.v1.ServerLoginConnectionEvents;
/*     */ import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
/*     */ import net.minecraft.class_2960;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public final class EventUtility {
/*  24 */   public static final class_2960 LOWEST_PHASE = class_2960.method_60655("fabric", "lowest");
/*  25 */   public static final class_2960 LOW_PHASE = class_2960.method_60655("fabric", "low");
/*  26 */   public static final class_2960 NORMAL_PHASE = class_2960.method_60655("fabric", "default");
/*  27 */   public static final class_2960 HIGH_PHASE = class_2960.method_60655("fabric", "high");
/*  28 */   public static final class_2960 HIGHEST_PHASE = class_2960.method_60655("fabric", "highest");
/*     */   
/*     */   static {
/*  31 */     registerEvents((Event<?>[])new Event[] { PlayerBlockBreakEvents.AFTER, PlayerBlockBreakEvents.BEFORE, UseItemCallback.EVENT, UseEntityCallback.EVENT, UseBlockCallback.EVENT, AttackEntityCallback.EVENT, ServerLifecycleEvents.SERVER_STARTING, ServerLifecycleEvents.SERVER_STARTED, ServerLifecycleEvents.SERVER_STOPPING, ServerLifecycleEvents.SERVER_STOPPED, ServerLifecycleEvents.END_DATA_PACK_RELOAD, ServerLifecycleEvents.START_DATA_PACK_RELOAD, ServerLifecycleEvents.SYNC_DATA_PACK_CONTENTS, ServerMessageEvents.ALLOW_CHAT_MESSAGE, ServerMessageEvents.ALLOW_COMMAND_MESSAGE, ServerMessageEvents.ALLOW_GAME_MESSAGE, ServerMessageEvents.CHAT_MESSAGE, ServerMessageEvents.COMMAND_MESSAGE, ServerMessageEvents.GAME_MESSAGE, LootTableEvents.ALL_LOADED, LootTableEvents.MODIFY, LootTableEvents.REPLACE, ServerLivingEntityEvents.AFTER_DEATH, ServerLivingEntityEvents.ALLOW_DAMAGE, ServerLivingEntityEvents.ALLOW_DEATH, ServerLoginConnectionEvents.QUERY_START, ServerLoginConnectionEvents.INIT, ServerLoginConnectionEvents.DISCONNECT, ServerEntityEvents.ENTITY_LOAD, ServerEntityEvents.ENTITY_UNLOAD, ServerPlayerEvents.AFTER_RESPAWN, ServerPlayerEvents.COPY_FROM, ServerChunkEvents.CHUNK_LOAD, ServerChunkEvents.CHUNK_UNLOAD, ServerWorldEvents.LOAD, ServerWorldEvents.UNLOAD, ServerBlockEntityEvents.BLOCK_ENTITY_LOAD, ServerBlockEntityEvents.BLOCK_ENTITY_UNLOAD, ServerEntityCombatEvents.AFTER_KILLED_OTHER_ENTITY, ServerEntityWorldChangeEvents.AFTER_ENTITY_CHANGE_WORLD, ServerEntityWorldChangeEvents.AFTER_PLAYER_CHANGE_WORLD, ServerPlayConnectionEvents.INIT, ServerPlayConnectionEvents.JOIN, ServerPlayConnectionEvents.DISCONNECT });
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
/*     */   
/*     */   public static void registerEvents(@NotNull Event<?>... events) {
/*  94 */     for (Event<?> event : events) registerEvent(event);
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void registerEvent(@NotNull Event<?> event) {
/* 104 */     event.addPhaseOrdering(LOWEST_PHASE, LOW_PHASE);
/* 105 */     event.addPhaseOrdering(LOW_PHASE, NORMAL_PHASE);
/* 106 */     event.addPhaseOrdering(NORMAL_PHASE, HIGH_PHASE);
/* 107 */     event.addPhaseOrdering(HIGH_PHASE, HIGHEST_PHASE);
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabri\\utility\EventUtility.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */