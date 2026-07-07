/*    */ package com.atlas.common.fabric.ranked.network;
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
/*    */ public class RankedNetworkHelper
/*    */ {
/*    */   public static void registerPackets() {
/* 24 */     PayloadTypeRegistry.playC2S().register(RankedNetwork.REQUEST_SYNC_TYPE, RankedNetwork.REQUEST_SYNC_CODEC);
/* 25 */     PayloadTypeRegistry.playC2S().register(RankedNetwork.LOCK_IN_DEFENSE_TYPE, RankedNetwork.LOCK_IN_DEFENSE_CODEC);
/* 26 */     PayloadTypeRegistry.playC2S().register(RankedNetwork.LOCK_IN_BATTLE_TYPE, RankedNetwork.LOCK_IN_BATTLE_CODEC);
/* 27 */     PayloadTypeRegistry.playC2S().register(RankedNetwork.UNLOCK_DEFENSE_TYPE, RankedNetwork.UNLOCK_DEFENSE_CODEC);
/* 28 */     PayloadTypeRegistry.playC2S().register(RankedNetwork.REFRESH_OPPONENTS_TYPE, RankedNetwork.REFRESH_OPPONENTS_CODEC);
/* 29 */     PayloadTypeRegistry.playC2S().register(RankedNetwork.CHALLENGE_TYPE, RankedNetwork.CHALLENGE_CODEC);
/* 30 */     PayloadTypeRegistry.playC2S().register(RankedNetwork.REQUEST_LEADERBOARD_TYPE, RankedNetwork.REQUEST_LEADERBOARD_CODEC);
/* 31 */     PayloadTypeRegistry.playC2S().register(RankedNetwork.PURCHASE_SHOP_ITEM_TYPE, RankedNetwork.PURCHASE_SHOP_ITEM_CODEC);
/* 32 */     PayloadTypeRegistry.playC2S().register(RankedNetwork.REQUEST_HISTORY_TYPE, RankedNetwork.REQUEST_HISTORY_CODEC);
/* 33 */     PayloadTypeRegistry.playC2S().register(RankedNetwork.REQUEST_PAST_SEASONS_TYPE, RankedNetwork.REQUEST_PAST_SEASONS_CODEC);
/* 34 */     PayloadTypeRegistry.playC2S().register(RankedNetwork.REQUEST_PAST_SEASON_LB_TYPE, RankedNetwork.REQUEST_PAST_SEASON_LB_CODEC);
/*    */ 
/*    */     
/* 37 */     PayloadTypeRegistry.playS2C().register(RankedNetwork.SYNC_DATA_TYPE, RankedNetwork.SYNC_DATA_CODEC);
/* 38 */     PayloadTypeRegistry.playS2C().register(RankedNetwork.SYNC_DEFENSE_PARTY_TYPE, RankedNetwork.SYNC_DEFENSE_PARTY_CODEC);
/* 39 */     PayloadTypeRegistry.playS2C().register(RankedNetwork.SYNC_BATTLE_PARTY_TYPE, RankedNetwork.SYNC_BATTLE_PARTY_CODEC);
/* 40 */     PayloadTypeRegistry.playS2C().register(RankedNetwork.SYNC_OPPONENTS_TYPE, RankedNetwork.SYNC_OPPONENTS_CODEC);
/* 41 */     PayloadTypeRegistry.playS2C().register(RankedNetwork.SYNC_LEADERBOARD_TYPE, RankedNetwork.SYNC_LEADERBOARD_CODEC);
/* 42 */     PayloadTypeRegistry.playS2C().register(RankedNetwork.SYNC_SHOP_ITEMS_TYPE, RankedNetwork.SYNC_SHOP_ITEMS_CODEC);
/* 43 */     PayloadTypeRegistry.playS2C().register(RankedNetwork.LOCK_IN_RESULT_TYPE, RankedNetwork.LOCK_IN_RESULT_CODEC);
/* 44 */     PayloadTypeRegistry.playS2C().register(RankedNetwork.OPEN_SCREEN_TYPE, RankedNetwork.OPEN_SCREEN_CODEC);
/* 45 */     PayloadTypeRegistry.playS2C().register(RankedNetwork.SYNC_HISTORY_TYPE, RankedNetwork.SYNC_HISTORY_CODEC);
/* 46 */     PayloadTypeRegistry.playS2C().register(RankedNetwork.SYNC_PAST_SEASONS_TYPE, RankedNetwork.SYNC_PAST_SEASONS_CODEC);
/* 47 */     PayloadTypeRegistry.playS2C().register(RankedNetwork.SYNC_PAST_SEASON_LB_TYPE, RankedNetwork.SYNC_PAST_SEASON_LB_CODEC);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static void registerClientPackets() {
/* 53 */     RankedNetworkHelperClient.registerClientReceivers();
/*    */   }
/*    */   
/*    */   public static void sendToServer(class_8710 payload) {
/* 57 */     RankedNetworkHelperClient.sendToServer(payload);
/*    */   }
/*    */   
/*    */   public static void sendToPlayer(class_3222 player, class_8710 payload) {
/* 61 */     ServerPlayNetworking.send(player, payload);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static String serializePokemon(Pokemon pokemon, class_7225.class_7874 registryLookup) {
/*    */     try {
/* 68 */       class_2487 nbt = pokemon.saveToNBT((class_5455)registryLookup, new class_2487());
/* 69 */       return nbt.toString();
/* 70 */     } catch (Exception e) {
/* 71 */       AtlasMod.LOGGER.warn("Failed to serialize Pokemon NBT for ranked: {}", e.getMessage());
/* 72 */       return "";
/*    */     } 
/*    */   }
/*    */   
/*    */   public static RankedNetwork.PartyEntry buildPartyEntry(int slot, Pokemon pokemon, class_7225.class_7874 registryLookup) {
/* 77 */     class_1799 heldItemStack = pokemon.heldItem();
/* 78 */     String heldItemId = heldItemStack.method_7960() ? "none" : class_7923.field_41178.method_10221(heldItemStack.method_7909()).toString();
/* 79 */     String caughtBall = pokemon.getCaughtBall().getName().toString();
/* 80 */     String pokemonData = serializePokemon(pokemon, registryLookup);
/* 81 */     String aspects = String.join(",", pokemon.getAspects());
/* 82 */     return new RankedNetwork.PartyEntry(slot, pokemon
/*    */         
/* 84 */         .getSpecies().getName(), pokemon
/* 85 */         .getSpecies().getResourceIdentifier().toString(), pokemon
/* 86 */         .getLevel(), pokemon
/* 87 */         .getShiny(), heldItemId, caughtBall, aspects, pokemonData);
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\ranked\network\RankedNetworkHelper.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */