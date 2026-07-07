package com.atlas.client.mixin.compat.xaero;

import com.atlas.client.d.a.a;
import net.minecraft.class_1297;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Pseudo
@Mixin(targets = {"xaero.common.minimap.render.radar.element.RadarElementReader"}, remap = false)
public abstract class XaeroRadarElementReaderMixin {
  @Inject(method = {"isHidden(Ljava/lang/Object;Ljava/lang/Object;)Z"}, at = {@At("HEAD")}, cancellable = true)
  private void atlas$hideAtlasRadarEntity(Object paramObject1, Object paramObject2, CallbackInfoReturnable<Boolean> paramCallbackInfoReturnable) {
    if (paramObject1 instanceof class_1297) {
      class_1297 class_1297 = (class_1297)paramObject1;
      if (a.a(class_1297))
        paramCallbackInfoReturnable.setReturnValue(Boolean.valueOf(true)); 
    } 
  }
}


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-client-1.2.0.jar!\com\atlas\client\mixin\compat\xaero\XaeroRadarElementReaderMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */