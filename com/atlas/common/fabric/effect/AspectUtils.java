/*    */ package com.atlas.common.fabric.effect;
/*    */ 
/*    */ import com.cobblemon.mod.common.api.battles.model.PokemonBattle;
/*    */ import com.cobblemon.mod.common.api.battles.model.actor.ActorType;
/*    */ import com.cobblemon.mod.common.api.net.NetworkPacket;
/*    */ import com.cobblemon.mod.common.api.pokemon.PokemonProperties;
/*    */ import com.cobblemon.mod.common.api.pokemon.feature.FlagSpeciesFeature;
/*    */ import com.cobblemon.mod.common.battles.ActiveBattlePokemon;
/*    */ import com.cobblemon.mod.common.battles.pokemon.BattlePokemon;
/*    */ import com.cobblemon.mod.common.net.messages.client.battle.BattleTransformPokemonPacket;
/*    */ import com.cobblemon.mod.common.net.messages.client.battle.BattleUpdateTeamPokemonPacket;
/*    */ import com.cobblemon.mod.common.net.messages.client.pokemon.update.AbilityUpdatePacket;
/*    */ import com.cobblemon.mod.common.pokemon.Pokemon;
/*    */ import java.util.List;
/*    */ import java.util.Objects;
/*    */ import java.util.Optional;
/*    */ import java.util.Set;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class AspectUtils
/*    */ {
/* 25 */   private static final Logger LOGGER = LoggerFactory.getLogger("atlas-effect");
/*    */ 
/*    */ 
/*    */   
/*    */   public static void applyAspects(Pokemon pokemon, List<String> aspects) {
/* 30 */     if (aspects == null || aspects.isEmpty())
/*    */       return; 
/* 32 */     String species = pokemon.getSpecies().getName();
/* 33 */     Set<String> aspectsBefore = pokemon.getAspects();
/* 34 */     String formBefore = pokemon.getForm().getName();
/* 35 */     LOGGER.info("[effect][apply] {} (uuid={}) BEFORE: form={} aspects={} | applying={}", new Object[] { species, pokemon
/* 36 */           .getUuid(), formBefore, aspectsBefore, aspects });
/*    */     
/* 38 */     for (String aspect : aspects) {
/*    */       try {
/* 40 */         if (!aspect.contains("=")) {
/*    */ 
/*    */           
/* 43 */           PokemonProperties.Companion.parse(aspect).apply(pokemon);
/*    */           continue;
/*    */         } 
/* 46 */         String[] parts = aspect.split("=", 2);
/* 47 */         if (parts[1].equals("true") || parts[1].equals("false")) {
/* 48 */           (new FlagSpeciesFeature(parts[0], Boolean.parseBoolean(parts[1]))).apply(pokemon);
/*    */ 
/*    */ 
/*    */           
/*    */           continue;
/*    */         } 
/*    */ 
/*    */         
/* 56 */         PokemonProperties.Companion.parse(aspect).apply(pokemon);
/*    */       }
/* 58 */       catch (Throwable t) {
/* 59 */         LOGGER.error("[effect][apply] {} (uuid={}) FAILED to apply aspect '{}'", new Object[] { species, pokemon
/* 60 */               .getUuid(), aspect, t });
/*    */       } 
/*    */     } 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 68 */     pokemon.updateAspects();
/* 69 */     pokemon.updateForm();
/*    */     
/* 71 */     LOGGER.info("[effect][apply] {} (uuid={}) AFTER: form={} aspects={}", new Object[] { species, pokemon
/* 72 */           .getUuid(), pokemon.getForm().getName(), pokemon.getAspects() });
/*    */   }
/*    */   
/*    */   public static void applyProperties(Pokemon pokemon, Optional<String> propertyString) {
/* 76 */     propertyString.ifPresent(prop -> PokemonProperties.Companion.parse(prop).apply(pokemon));
/*    */   }
/*    */   
/*    */   public static void updatePackets(BattlePokemon battlePokemon) {
/* 80 */     if (battlePokemon == null || battlePokemon.getEntity() == null)
/* 81 */       return;  Pokemon pokemon = battlePokemon.getEntity().getPokemon();
/* 82 */     PokemonBattle battle = battlePokemon.getActor().getBattle();
/*    */     
/* 84 */     if (battlePokemon.getActor().getType() == ActorType.PLAYER) {
/*    */       
/* 86 */       Objects.requireNonNull(battlePokemon); battle.sendUpdate((NetworkPacket)new AbilityUpdatePacket(battlePokemon::getEffectedPokemon, pokemon
/* 87 */             .getAbility().getTemplate()));
/*    */       
/* 89 */       battle.sendUpdate((NetworkPacket)new BattleUpdateTeamPokemonPacket(pokemon));
/*    */     } 
/*    */     
/* 92 */     for (ActiveBattlePokemon active : battle.getActivePokemon()) {
/* 93 */       if (active.getBattlePokemon() == null || 
/* 94 */         active.getBattlePokemon() != battlePokemon)
/* 95 */         continue;  battle.sendSidedUpdate(active
/* 96 */           .getActor(), (NetworkPacket)new BattleTransformPokemonPacket(active
/* 97 */             .getPNX(), battlePokemon, true), (NetworkPacket)new BattleTransformPokemonPacket(active
/* 98 */             .getPNX(), battlePokemon, false), false);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\effect\AspectUtils.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */