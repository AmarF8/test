package com.atlas.common.fabric.mixin.content.accessor;

import java.util.List;
import net.minecraft.class_374;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({class_374.class})
public interface ToastManagerAccessor {
  @Accessor("visibleEntries")
  List<?> atlas$getVisibleEntries();
}


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\mixin\content\accessor\ToastManagerAccessor.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */