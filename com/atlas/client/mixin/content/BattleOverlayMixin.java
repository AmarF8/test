package com.atlas.client.mixin.content;

import com.atlas.common.fabric.battle.ui.BattleUiClientState;
import com.atlas.common.fabric.battle.ui.BattleUiOverlayRenderer;
import com.cobblemon.mod.common.api.pokedex.PokedexEntryProgress;
import com.cobblemon.mod.common.client.battle.ActiveClientBattlePokemon;
import com.cobblemon.mod.common.client.gui.battle.BattleOverlay;
import net.minecraft.class_1041;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_9779;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = {BattleOverlay.class}, remap = false)
public final class BattleOverlayMixin {
  @Inject(method = {"drawTile"}, at = {@At("HEAD")}, cancellable = true)
  private void atlas$hideVanillaBattleTile(class_332 paramclass_332, float paramFloat, ActiveClientBattlePokemon paramActiveClientBattlePokemon, boolean paramBoolean1, int paramInt, PokedexEntryProgress paramPokedexEntryProgress, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, CallbackInfo paramCallbackInfo) {
    if (BattleUiClientState.currentBattle().isPresent())
      paramCallbackInfo.cancel(); 
  }
  
  @Inject(method = {"method_1753"}, at = {@At("TAIL")})
  private void atlas$renderBattleUi(class_332 paramclass_332, class_9779 paramclass_9779, CallbackInfo paramCallbackInfo) {
    class_310 class_310 = class_310.method_1551();
    class_1041 class_1041 = class_310.method_22683();
    int i = (int)(class_310.field_1729.method_1603() * class_1041.method_4486() / class_1041.method_4480());
    int j = (int)(class_310.field_1729.method_1604() * class_1041.method_4502() / class_1041.method_4507());
    BattleUiOverlayRenderer.render(paramclass_332, i, j);
  }
}


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-client-1.2.0.jar!\com\atlas\client\mixin\content\BattleOverlayMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */