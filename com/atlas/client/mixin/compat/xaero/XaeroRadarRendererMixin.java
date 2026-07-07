package com.atlas.client.mixin.compat.xaero;

import com.atlas.client.d.a.a;
import net.minecraft.class_1297;
import net.minecraft.class_4587;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@Mixin(targets = {"xaero.hud.minimap.radar.render.element.RadarRenderer"}, remap = false)
public abstract class XaeroRadarRendererMixin {
  @Inject(method = {"renderLabel(Lnet/minecraft/class_1297;Lnet/minecraft/class_1297;ZDLnet/minecraft/class_4587;)V"}, at = {@At("HEAD")}, cancellable = true)
  private void atlas$hideAtlasNpcGeneratedName(class_1297 paramclass_12971, class_1297 paramclass_12972, boolean paramBoolean, double paramDouble, class_4587 paramclass_4587, CallbackInfo paramCallbackInfo) {
    if (paramBoolean && a.b(paramclass_12971))
      paramCallbackInfo.cancel(); 
  }
}


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-client-1.2.0.jar!\com\atlas\client\mixin\compat\xaero\XaeroRadarRendererMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */