package com.atlas.client;

import com.atlas.client.a.a;
import com.atlas.client.a.b;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.class_2960;
import net.minecraft.class_3300;

class null implements SimpleSynchronousResourceReloadListener {
  null(AtlasClientModInitializer paramAtlasClientModInitializer) {}
  
  public void method_14491(class_3300 paramclass_3300) {
    b.a.reload(paramclass_3300);
    a.a.a(paramclass_3300);
  }
  
  public class_2960 getFabricId() {
    return class_2960.method_60655("atlas", "resources");
  }
}


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-client-1.2.0.jar!\com\atlas\client\AtlasClientModInitializer$1.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */