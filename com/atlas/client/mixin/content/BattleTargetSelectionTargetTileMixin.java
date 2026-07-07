package com.atlas.client.mixin.content;

import com.atlas.common.fabric.battle.ui.BattleUiClientBattleSync;
import com.cobblemon.mod.common.client.battle.ActiveClientBattlePokemon;
import com.cobblemon.mod.common.client.battle.ClientBattlePokemon;
import com.cobblemon.mod.common.client.gui.battle.subscreen.BattleTargetSelection;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = {BattleTargetSelection.TargetTile.class}, remap = false)
public abstract class BattleTargetSelectionTargetTileMixin {
  @Shadow
  @Final
  private ActiveClientBattlePokemon target;
  
  @Inject(method = {"getSelectable"}, at = {@At("HEAD")}, cancellable = true, remap = false)
  private void atlas$deadTargetsAreNotSelectable(CallbackInfoReturnable<Boolean> paramCallbackInfoReturnable) {
    BattleUiClientBattleSync.syncActiveSlotsFromOverlayState();
    if (!atlas$hasLiveTarget())
      paramCallbackInfoReturnable.setReturnValue(Boolean.valueOf(false)); 
  }
  
  @Inject(method = {"isHovered"}, at = {@At("HEAD")}, cancellable = true, remap = false)
  private void atlas$deadTargetsAreNotHovered(double paramDouble1, double paramDouble2, CallbackInfoReturnable<Boolean> paramCallbackInfoReturnable) {
    if (!atlas$hasLiveTarget())
      paramCallbackInfoReturnable.setReturnValue(Boolean.valueOf(false)); 
  }
  
  @Unique
  private boolean atlas$hasLiveTarget() {
    ClientBattlePokemon clientBattlePokemon = (this.target == null) ? null : this.target.getBattlePokemon();
    return (clientBattlePokemon != null && clientBattlePokemon.getHpValue() > 0.0F);
  }
}


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-client-1.2.0.jar!\com\atlas\client\mixin\content\BattleTargetSelectionTargetTileMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */