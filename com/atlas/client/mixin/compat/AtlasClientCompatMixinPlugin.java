package com.atlas.client.mixin.compat;

import java.util.List;
import java.util.Set;
import net.fabricmc.loader.api.FabricLoader;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

public final class AtlasClientCompatMixinPlugin implements IMixinConfigPlugin {
  public boolean shouldApplyMixin(String paramString1, String paramString2) {
    return paramString2.contains(".xaero.") ? FabricLoader.getInstance().isModLoaded("xaerominimap") : (paramString2.contains(".morecobblemon.") ? FabricLoader.getInstance().isModLoaded("more_cobblemon_tweaks") : true);
  }
  
  public void onLoad(String paramString) {}
  
  public String getRefMapperConfig() {
    return null;
  }
  
  public void acceptTargets(Set<String> paramSet1, Set<String> paramSet2) {}
  
  public List<String> getMixins() {
    return null;
  }
  
  public void preApply(String paramString1, ClassNode paramClassNode, String paramString2, IMixinInfo paramIMixinInfo) {}
  
  public void postApply(String paramString1, ClassNode paramClassNode, String paramString2, IMixinInfo paramIMixinInfo) {}
}


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-client-1.2.0.jar!\com\atlas\client\mixin\compat\AtlasClientCompatMixinPlugin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */