package com.atlas.common.fabric.block.station.system;

import com.cobblemon.mod.common.pokemon.Pokemon;
import java.util.List;

public interface StationRequirement {
  boolean check(List<Pokemon> paramList1, List<Pokemon> paramList2);
  
  String getDescription();
}


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\block\station\system\StationRequirement.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */