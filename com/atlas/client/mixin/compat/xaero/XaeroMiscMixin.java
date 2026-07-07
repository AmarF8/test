package com.atlas.client.mixin.compat.xaero;

import com.atlas.client.d.a.a;
import net.minecraft.class_1297;
import net.minecraft.class_2561;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Pseudo
@Mixin(targets = {"xaero.common.misc.Misc"}, remap = false)
public abstract class XaeroMiscMixin {
  @Inject(method = {"getFixedDisplayName(Lnet/minecraft/class_1297;)Lnet/minecraft/class_2561;"}, at = {@At("HEAD")}, cancellable = true)
  private static void atlas$hideAtlasNpcGeneratedName(class_1297 paramclass_1297, CallbackInfoReturnable<class_2561> paramCallbackInfoReturnable) {
    if (a.b(paramclass_1297))
      paramCallbackInfoReturnable.setReturnValue(null); 
  }
}


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-client-1.2.0.jar!\com\atlas\client\mixin\compat\xaero\XaeroMiscMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */