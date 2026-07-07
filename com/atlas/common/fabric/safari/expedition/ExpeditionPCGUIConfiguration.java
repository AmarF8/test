/*    */ package com.atlas.common.fabric.safari.expedition;
/*    */ import com.atlas.common.fabric.safari.expedition.network.SafariExpeditionNetwork;
/*    */ import com.cobblemon.mod.common.CobblemonSounds;
/*    */ import com.cobblemon.mod.common.api.reactive.SettableObservable;
/*    */ import com.cobblemon.mod.common.api.storage.StorePosition;
/*    */ import com.cobblemon.mod.common.client.gui.pc.PCGUI;
/*    */ import com.cobblemon.mod.common.pokemon.Pokemon;
/*    */ import kotlin.Unit;
/*    */ 
/*    */ public final class ExpeditionPCGUIConfiguration extends PCGUIConfiguration {
/*    */   private final SettableObservable<SafariExpeditionModels.ExpeditionBuilderData> builderData;
/*    */   
/*    */   public ExpeditionPCGUIConfiguration(SettableObservable<SafariExpeditionModels.ExpeditionBuilderData> builderData) {
/* 14 */     super(pcGui -> { pcGui.closeNormally(false); return null; }(pcGui, position, pokemon) -> { if (pokemon != null && !pokemon.isFainted() && pokemon.getTetheringId() == null) { SafariExpeditionNetwork.requestBuilderAddPokemon(pokemon.getUuid().toString()); pcGui.playSound(CobblemonSounds.PC_CLICK); }  return null; }false, pokemon -> Boolean.valueOf(
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */           
/* 27 */           (pokemon != null && !pokemon.isFainted() && pokemon.getTetheringId() == null)));
/*    */     
/* 29 */     this.builderData = builderData;
/*    */   }
/*    */   
/*    */   public SettableObservable<SafariExpeditionModels.ExpeditionBuilderData> getBuilderData() {
/* 33 */     return this.builderData;
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\safari\expedition\ExpeditionPCGUIConfiguration.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */