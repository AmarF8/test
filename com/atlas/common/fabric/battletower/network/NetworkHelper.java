/*    */ package com.atlas.common.fabric.battletower.network;
/*    */ 
/*    */ import com.atlas.common.fabric.AtlasMod;
/*    */ import com.cobblemon.mod.common.pokemon.Pokemon;
/*    */ import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
/*    */ import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
/*    */ import net.minecraft.class_1799;
/*    */ import net.minecraft.class_2487;
/*    */ import net.minecraft.class_3222;
/*    */ import net.minecraft.class_5455;
/*    */ import net.minecraft.class_7225;
/*    */ import net.minecraft.class_7923;
/*    */ import net.minecraft.class_8710;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NetworkHelper
/*    */ {
/*    */   public static void registerPackets() {
/* 26 */     PayloadTypeRegistry.playC2S().register(BattleTowerNetwork.START_BATTLE_TYPE, BattleTowerNetwork.START_BATTLE_CODEC);
/* 27 */     PayloadTypeRegistry.playC2S().register(BattleTowerNetwork.REQUEST_SYNC_TYPE, BattleTowerNetwork.REQUEST_SYNC_CODEC);
/* 28 */     PayloadTypeRegistry.playC2S().register(BattleTowerNetwork.PURCHASE_TYPE, BattleTowerNetwork.PURCHASE_CODEC);
/* 29 */     PayloadTypeRegistry.playC2S().register(BattleTowerNetwork.LOCK_IN_TEAM_TYPE, BattleTowerNetwork.LOCK_IN_TEAM_CODEC);
/* 30 */     PayloadTypeRegistry.playC2S().register(BattleTowerNetwork.FORFEIT_RUN_TYPE, BattleTowerNetwork.FORFEIT_RUN_CODEC);
/* 31 */     PayloadTypeRegistry.playC2S().register(BattleTowerNetwork.UNLOCK_TEAM_TYPE, BattleTowerNetwork.UNLOCK_TEAM_CODEC);
/* 32 */     PayloadTypeRegistry.playC2S().register(BattleTowerNetwork.REQUEST_LEADERBOARD_TYPE, BattleTowerNetwork.REQUEST_LEADERBOARD_CODEC);
/*    */ 
/*    */     
/* 35 */     PayloadTypeRegistry.playS2C().register(BattleTowerNetwork.SYNC_DATA_TYPE, BattleTowerNetwork.SYNC_DATA_CODEC);
/* 36 */     PayloadTypeRegistry.playS2C().register(BattleTowerNetwork.SYNC_PARTY_TYPE, BattleTowerNetwork.SYNC_PARTY_CODEC);
/* 37 */     PayloadTypeRegistry.playS2C().register(BattleTowerNetwork.SYNC_SNAPSHOT_TYPE, BattleTowerNetwork.SYNC_SNAPSHOT_CODEC);
/* 38 */     PayloadTypeRegistry.playS2C().register(BattleTowerNetwork.SYNC_SHOP_ITEMS_TYPE, BattleTowerNetwork.SYNC_SHOP_ITEMS_CODEC);
/* 39 */     PayloadTypeRegistry.playS2C().register(BattleTowerNetwork.SYNC_LEADERBOARD_TYPE, BattleTowerNetwork.SYNC_LEADERBOARD_CODEC);
/* 40 */     PayloadTypeRegistry.playS2C().register(BattleTowerNetwork.SYNC_ACTIVE_PLAYERS_TYPE, BattleTowerNetwork.SYNC_ACTIVE_PLAYERS_CODEC);
/* 41 */     PayloadTypeRegistry.playS2C().register(BattleTowerNetwork.LOCK_IN_RESULT_TYPE, BattleTowerNetwork.LOCK_IN_RESULT_CODEC);
/* 42 */     PayloadTypeRegistry.playS2C().register(BattleTowerNetwork.OPEN_SCREEN_TYPE, BattleTowerNetwork.OPEN_SCREEN_CODEC);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void registerClientPackets() {
/* 49 */     NetworkHelperClient.registerClientReceivers();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void sendToServer(class_8710 payload) {
/* 57 */     NetworkHelperClient.sendToServer(payload);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static void sendToPlayer(class_3222 player, class_8710 payload) {
/* 63 */     ServerPlayNetworking.send(player, payload);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static String serializePokemon(Pokemon pokemon, class_7225.class_7874 registryLookup) {
/*    */     try {
/* 73 */       class_2487 nbt = pokemon.saveToNBT((class_5455)registryLookup, new class_2487());
/* 74 */       return nbt.toString();
/* 75 */     } catch (Exception e) {
/* 76 */       AtlasMod.LOGGER.warn("Failed to serialize Pokemon NBT: {}", e.getMessage());
/* 77 */       return "";
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static BattleTowerNetwork.PartyEntry buildPartyEntry(int slot, Pokemon pokemon, class_7225.class_7874 registryLookup) {
/* 85 */     class_1799 heldItemStack = pokemon.heldItem();
/* 86 */     String heldItemId = heldItemStack.method_7960() ? "none" : class_7923.field_41178.method_10221(heldItemStack.method_7909()).toString();
/* 87 */     String caughtBall = pokemon.getCaughtBall().getName().toString();
/* 88 */     String pokemonData = serializePokemon(pokemon, registryLookup);
/* 89 */     String aspects = String.join(",", pokemon.getAspects());
/* 90 */     return new BattleTowerNetwork.PartyEntry(slot, pokemon
/*    */         
/* 92 */         .getSpecies().getName(), pokemon
/* 93 */         .getSpecies().getResourceIdentifier().toString(), pokemon
/* 94 */         .getLevel(), pokemon
/* 95 */         .getShiny(), heldItemId, caughtBall, aspects, pokemonData);
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\battletower\network\NetworkHelper.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */