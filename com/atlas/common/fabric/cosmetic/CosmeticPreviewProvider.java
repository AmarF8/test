package com.atlas.common.fabric.cosmetic;

import java.util.Map;
import org.jetbrains.annotations.Nullable;

public interface CosmeticPreviewProvider {
  @Nullable
  Map<CosmeticCategory, EquippedCosmetic> getPreviewedCosmetics();
}


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\cosmetic\CosmeticPreviewProvider.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */