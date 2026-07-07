package com.atlas.common.fabric.mixin.content.accessor;

import java.util.Map;
import java.util.UUID;
import net.minecraft.class_337;
import net.minecraft.class_345;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({class_337.class})
public interface BossBarHudAccessor {
  @Accessor("bossBars")
  Map<UUID, class_345> getBossBars();
}


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\mixin\content\accessor\BossBarHudAccessor.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */