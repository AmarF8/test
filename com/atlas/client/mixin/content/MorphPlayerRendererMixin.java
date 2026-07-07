package com.atlas.client.mixin.content;

import com.atlas.common.fabric.morph.runtime.client.MorphPokemonRenderState;
import com.atlas.common.fabric.morph.runtime.client.MorphedPlayerRegistry;
import net.minecraft.class_1007;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_742;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_1007.class})
public abstract class MorphPlayerRendererMixin {
  @Inject(method = {"render(Lnet/minecraft/client/network/AbstractClientPlayerEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V"}, at = {@At("HEAD")}, cancellable = true)
  private void atlas$morphRender(class_742 paramclass_742, float paramFloat1, float paramFloat2, class_4587 paramclass_4587, class_4597 paramclass_4597, int paramInt, CallbackInfo paramCallbackInfo) {
    if (MorphedPlayerRegistry.get().isMorphed(paramclass_742.method_5667())) {
      MorphPokemonRenderState.render(paramclass_742, paramFloat1, paramFloat2, paramclass_4587, paramclass_4597, paramInt);
      paramCallbackInfo.cancel();
      return;
    } 
    if (paramclass_742.method_5767() && paramclass_742.method_5854() instanceof com.cobblemon.mod.common.entity.pokemon.PokemonEntity)
      paramCallbackInfo.cancel(); 
  }
}


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-client-1.2.0.jar!\com\atlas\client\mixin\content\MorphPlayerRendererMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */