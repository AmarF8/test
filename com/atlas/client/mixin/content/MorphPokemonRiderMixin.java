package com.atlas.client.mixin.content;

import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({PokemonEntity.class})
public abstract class MorphPokemonRiderMixin {
  @Inject(method = {"updatePassengerPosition(Lnet/minecraft/entity/Entity;Lnet/minecraft/entity/Entity$PositionUpdater;)V"}, at = {@At("HEAD")}, cancellable = true)
  private void atlas$centerMorphRider(class_1297 paramclass_1297, class_1297.class_4738 paramclass_4738, CallbackInfo paramCallbackInfo) {
    if (paramclass_1297 instanceof class_1657) {
      class_1657 class_1657 = (class_1657)paramclass_1297;
      if (class_1657.method_5767()) {
        PokemonEntity pokemonEntity = (PokemonEntity)this;
        if (class_1657.method_5854() != pokemonEntity)
          return; 
        double d = pokemonEntity.method_23318() + Math.max(0.0D, (pokemonEntity.method_17682() - paramclass_1297.method_17682()) * 0.5D);
        paramclass_4738.accept(paramclass_1297, pokemonEntity.method_23317(), d, pokemonEntity.method_23321());
        paramCallbackInfo.cancel();
        return;
      } 
    } 
  }
}


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-client-1.2.0.jar!\com\atlas\client\mixin\content\MorphPokemonRiderMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */