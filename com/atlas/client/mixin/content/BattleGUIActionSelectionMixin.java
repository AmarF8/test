package com.atlas.client.mixin.content;

import com.atlas.common.fabric.battle.ui.BattleUiClientBattleSync;
import com.cobblemon.mod.common.battles.PassActionResponse;
import com.cobblemon.mod.common.battles.ShowdownActionResponse;
import com.cobblemon.mod.common.battles.ShowdownPokemon;
import com.cobblemon.mod.common.client.CobblemonClient;
import com.cobblemon.mod.common.client.battle.ClientBattle;
import com.cobblemon.mod.common.client.battle.ClientBattleActor;
import com.cobblemon.mod.common.client.battle.ClientBattlePokemon;
import com.cobblemon.mod.common.client.battle.SingleActionRequest;
import com.cobblemon.mod.common.client.gui.battle.BattleGUI;
import com.cobblemon.mod.common.client.gui.battle.subscreen.BattleActionSelection;
import com.cobblemon.mod.common.client.gui.battle.subscreen.BattleGeneralActionSelection;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = {BattleGUI.class}, remap = false)
public final class BattleGUIActionSelectionMixin {
  @Unique
  private static final Logger ATLAS_BATTLE_REQUEST_LOGGER = LoggerFactory.getLogger("atlas-battle-ui");
  
  @Unique
  private static final Set<String> ATLAS_LOGGED_STALE_REQUESTS = ConcurrentHashMap.newKeySet();
  
  @Inject(method = {"deriveRootActionSelection"}, at = {@At("HEAD")}, cancellable = true, remap = false)
  private void atlas$showMoveMenuForStaleSideSnapshot(ClientBattleActor paramClientBattleActor, SingleActionRequest paramSingleActionRequest, CallbackInfoReturnable<BattleActionSelection> paramCallbackInfoReturnable) {
    BattleUiClientBattleSync.syncActiveSlotsFromOverlayState();
    if (paramSingleActionRequest != null && !paramSingleActionRequest.getForceSwitch() && BattleUiClientBattleSync.hasFaintedActivePokemon(paramSingleActionRequest)) {
      atlas$logFaintedActiveRequest(paramClientBattleActor, paramSingleActionRequest);
      ((BattleGUI)this).selectAction(paramSingleActionRequest, (ShowdownActionResponse)PassActionResponse.INSTANCE);
      paramCallbackInfoReturnable.setReturnValue(null);
      return;
    } 
    if (paramSingleActionRequest == null || paramSingleActionRequest.getForceSwitch() || paramSingleActionRequest.getMoveSet() == null)
      return; 
    if (paramSingleActionRequest.getActivePokemon() == null)
      return; 
    ClientBattlePokemon clientBattlePokemon = paramSingleActionRequest.getActivePokemon().getBattlePokemon();
    if (clientBattlePokemon == null || clientBattlePokemon.getHpValue() <= 0.0F)
      return; 
    ShowdownPokemon showdownPokemon = atlas$findSidePokemon(paramSingleActionRequest, clientBattlePokemon.getUuid());
    String str = atlas$staleSideReason(showdownPokemon);
    if (str == null)
      return; 
    atlas$logStaleSideRequest(paramClientBattleActor, clientBattlePokemon, showdownPokemon, str);
    paramCallbackInfoReturnable.setReturnValue(new BattleGeneralActionSelection((BattleGUI)this, paramSingleActionRequest));
  }
  
  @Unique
  private static ShowdownPokemon atlas$findSidePokemon(SingleActionRequest paramSingleActionRequest, UUID paramUUID) {
    if (paramSingleActionRequest.getSide() == null || paramSingleActionRequest.getSide().getPokemon() == null || paramUUID == null)
      return null; 
    for (ShowdownPokemon showdownPokemon : paramSingleActionRequest.getSide().getPokemon()) {
      if (paramUUID.equals(showdownPokemon.getUuid()))
        return showdownPokemon; 
    } 
    return null;
  }
  
  @Unique
  private static String atlas$staleSideReason(ShowdownPokemon paramShowdownPokemon) {
    if (paramShowdownPokemon == null)
      return "side_pokemon_missing"; 
    String str = paramShowdownPokemon.getCondition();
    return (str != null && str.toLowerCase(Locale.ROOT).contains("fnt")) ? "side_pokemon_fainted" : null;
  }
  
  @Unique
  private static void atlas$logStaleSideRequest(ClientBattleActor paramClientBattleActor, ClientBattlePokemon paramClientBattlePokemon, ShowdownPokemon paramShowdownPokemon, String paramString) {
    if (!atlas$debugEnabled())
      return; 
    ClientBattle clientBattle = CobblemonClient.INSTANCE.getBattle();
    String str = String.valueOf((clientBattle == null) ? "unknown" : clientBattle.getBattleId()) + ":" + String.valueOf((clientBattle == null) ? "unknown" : clientBattle.getBattleId()) + ":" + String.valueOf((paramClientBattlePokemon == null) ? "unknown" : paramClientBattlePokemon.getUuid());
    if (!ATLAS_LOGGED_STALE_REQUESTS.add(str))
      return; 
    ATLAS_BATTLE_REQUEST_LOGGER.info("[BattleRequestClient] showing move menu over stale side snapshot battle={} actor={} pokemon={} hp={}/{} reason={} sideCondition={}", new Object[] { (clientBattle == null) ? "null" : clientBattle.getBattleId(), (paramClientBattleActor == null) ? "null" : paramClientBattleActor.getUuid(), (paramClientBattlePokemon == null) ? "null" : paramClientBattlePokemon.getUuid(), (paramClientBattlePokemon == null) ? "null" : Float.valueOf(paramClientBattlePokemon.getHpValue()), (paramClientBattlePokemon == null) ? "null" : Float.valueOf(paramClientBattlePokemon.getMaxHp()), paramString, (paramShowdownPokemon == null) ? "null" : paramShowdownPokemon.getCondition() });
  }
  
  @Unique
  private static void atlas$logFaintedActiveRequest(ClientBattleActor paramClientBattleActor, SingleActionRequest paramSingleActionRequest) {
    if (!atlas$debugEnabled())
      return; 
    ClientBattle clientBattle = CobblemonClient.INSTANCE.getBattle();
    ClientBattlePokemon clientBattlePokemon = (paramSingleActionRequest.getActivePokemon() == null) ? null : paramSingleActionRequest.getActivePokemon().getBattlePokemon();
    String str = String.valueOf((clientBattle == null) ? "unknown" : clientBattle.getBattleId()) + ":" + String.valueOf((clientBattle == null) ? "unknown" : clientBattle.getBattleId()) + ":fainted-active-pass";
    if (!ATLAS_LOGGED_STALE_REQUESTS.add(str))
      return; 
    ATLAS_BATTLE_REQUEST_LOGGER.info("[BattleRequestClient] auto-passing fainted active request battle={} actor={} pokemon={} hp={}/{}", new Object[] { (clientBattle == null) ? "null" : clientBattle.getBattleId(), (paramClientBattleActor == null) ? "null" : paramClientBattleActor.getUuid(), (clientBattlePokemon == null) ? "null" : clientBattlePokemon.getUuid(), (clientBattlePokemon == null) ? "null" : Float.valueOf(clientBattlePokemon.getHpValue()), (clientBattlePokemon == null) ? "null" : Float.valueOf(clientBattlePokemon.getMaxHp()) });
  }
  
  @Unique
  private static boolean atlas$debugEnabled() {
    String str1 = System.getProperty("atlas.battle-ui.debug");
    String str2 = System.getenv("ATLAS_BATTLE_UI_DEBUG");
    return (str1 != null) ? Boolean.parseBoolean(str1) : ((str2 != null) ? Boolean.parseBoolean(str2) : false);
  }
}


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-client-1.2.0.jar!\com\atlas\client\mixin\content\BattleGUIActionSelectionMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */