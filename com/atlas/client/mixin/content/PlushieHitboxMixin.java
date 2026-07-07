package com.atlas.client.mixin.content;

import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import net.minecraft.class_4048;
import net.minecraft.class_4050;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({PokemonEntity.class})
public abstract class PlushieHitboxMixin {
  @Inject(method = {"getDimensions"}, at = {@At("RETURN")}, cancellable = true)
  private void atlas$shrinkPlushieDimensions(class_4050 paramclass_4050, CallbackInfoReturnable<class_4048> paramCallbackInfoReturnable) {
    PokemonEntity pokemonEntity = (PokemonEntity)this;
    try {
      if (!pokemonEntity.method_37908().method_8608())
        return; 
      if (atlas$isPlushie(pokemonEntity))
        paramCallbackInfoReturnable.setReturnValue(class_4048.method_18385(0.3F, 0.3F)); 
    } catch (Exception exception) {}
  }
  
  @Inject(method = {"isPushable"}, at = {@At("HEAD")}, cancellable = true)
  private void atlas$disablePlushiePush(CallbackInfoReturnable<Boolean> paramCallbackInfoReturnable) {
    PokemonEntity pokemonEntity = (PokemonEntity)this;
    try {
      if (atlas$isPlushie(pokemonEntity))
        paramCallbackInfoReturnable.setReturnValue(Boolean.valueOf(false)); 
    } catch (Exception exception) {}
  }
  
  @Unique
  private static boolean atlas$isPlushie(PokemonEntity paramPokemonEntity) {
    for (String str : paramPokemonEntity.getAspects()) {
      if (str.toLowerCase().contains("plushie"))
        return true; 
    } 
    return false;
  }
}


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-client-1.2.0.jar!\com\atlas\client\mixin\content\PlushieHitboxMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */