package com.atlas.common.metadata;

import org.jetbrains.annotations.NotNull;

public interface MetadataListener {
  default void onKeyAdd(@NotNull Metadata.MetadataKeyInformation information) {}
  
  default void onKeyRemove(@NotNull Metadata.MetadataKeyInformation information) {}
}


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\metadata\Metadata$MetadataListener.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */