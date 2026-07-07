package com.atlas.client.mixin.content;

import com.atlas.common.fabric.battle.ui.BattleUiClientBattleSync;
import com.cobblemon.mod.common.battles.PassActionResponse;
import com.cobblemon.mod.common.battles.ShowdownActionResponse;
import com.cobblemon.mod.common.client.battle.SingleActionRequest;
import com.cobblemon.mod.common.client.gui.battle.subscreen.BattleActionSelection;
import com.cobblemon.mod.common.client.gui.battle.subscreen.BattleMoveSelection;
import java.util.List;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = {BattleMoveSelection.class}, remap = false)
public abstract class BattleMoveSelectionMixin {
  @Shadow
  public abstract List<BattleMoveSelection.MoveTile> getMoveTiles();
  
  @Inject(method = {"mousePrimaryClicked"}, at = {@At("HEAD")}, cancellable = true, remap = false)
  private void atlas$ignoreDisabledMoveTiles(double paramDouble1, double paramDouble2, CallbackInfoReturnable<Boolean> paramCallbackInfoReturnable) {
    BattleUiClientBattleSync.syncActiveSlotsFromOverlayState();
    BattleActionSelection battleActionSelection = (BattleActionSelection)this;
    SingleActionRequest singleActionRequest = battleActionSelection.getRequest();
    if (!singleActionRequest.getForceSwitch() && BattleUiClientBattleSync.hasFaintedActivePokemon(singleActionRequest)) {
      battleActionSelection.getBattleGUI().selectAction(singleActionRequest, (ShowdownActionResponse)PassActionResponse.INSTANCE);
      paramCallbackInfoReturnable.setReturnValue(Boolean.valueOf(true));
      return;
    } 
    for (BattleMoveSelection.MoveTile moveTile : getMoveTiles()) {
      if (moveTile.isHovered(paramDouble1, paramDouble2) && !moveTile.getSelectable()) {
        paramCallbackInfoReturnable.setReturnValue(Boolean.valueOf(true));
        return;
      } 
    } 
  }
}


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-client-1.2.0.jar!\com\atlas\client\mixin\content\BattleMoveSelectionMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */