package com.atlas.common.fabric.block.station;

import com.atlas.common.fabric.block.station.entity.StationBlockEntity;
import net.minecraft.class_3222;
import org.jetbrains.annotations.Nullable;

@FunctionalInterface
public interface StationPermissionController {
  @Nullable
  StationPermissions permit(class_3222 paramclass_3222, StationBlockEntity paramStationBlockEntity);
}


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\block\station\StationPermissionController.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */