package com.atlas.common.fabric.mixin.content.accessor;

import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import java.util.SortedSet;
import net.minecraft.class_3191;
import net.minecraft.class_761;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({class_761.class})
public interface WorldRendererAccessor {
  @Accessor("blockBreakingProgressions")
  Long2ObjectMap<SortedSet<class_3191>> getBlockBreakingProgressions();
}


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\mixin\content\accessor\WorldRendererAccessor.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */