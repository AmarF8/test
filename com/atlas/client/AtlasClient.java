package com.atlas.client;

import com.atlas.client.c.a;
import com.atlas.common.AtlasNetworkType;
import com.atlas.common.fabric.block.station.StationPCGUIConfiguration;
import com.cobblemon.mod.common.client.gui.pc.PCGUI;
import com.cobblemon.mod.common.client.gui.pc.PCGUIConfiguration;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.minecraft.class_1268;
import net.minecraft.class_2350;
import net.minecraft.class_2382;
import net.minecraft.class_243;
import net.minecraft.class_310;
import net.minecraft.class_3965;
import net.minecraft.class_412;
import net.minecraft.class_437;
import net.minecraft.class_639;
import net.minecraft.class_642;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class AtlasClient implements ModInitializer {
  private static final a a = new a();
  
  private boolean b = false;
  
  private static Logger c = LoggerFactory.getLogger(AtlasClient.class);
  
  private static final Gson d = (new GsonBuilder()).setPrettyPrinting().create();
  
  private static final File e = new File("config/modpack.json");
  
  private static final Map<String, String> f = Map.of("curseforge", AtlasNetworkType.current().getCurseforgeServer(), "modrinth", AtlasNetworkType.current().getModrinthServer());
  
  public static Logger a() {
    return c;
  }
  
  static a b() {
    return a;
  }
  
  public void onInitialize() {
    ScreenEvents.AFTER_INIT.register((paramclass_310, paramclass_437, paramInt1, paramInt2) -> {
          if (paramclass_437 instanceof PCGUI) {
            PCGUI pCGUI = (PCGUI)paramclass_437;
            PCGUIConfiguration pCGUIConfiguration = pCGUI.getConfiguration();
            if (pCGUIConfiguration instanceof StationPCGUIConfiguration) {
              StationPCGUIConfiguration stationPCGUIConfiguration = (StationPCGUIConfiguration)pCGUIConfiguration;
              ScreenEvents.remove(paramclass_437).register(());
            } 
          } 
        });
    ScreenEvents.AFTER_INIT.register((paramclass_310, paramclass_437, paramInt1, paramInt2) -> {
          if (paramclass_437 instanceof net.minecraft.class_442) {
            try {
              a a1 = c();
              if (a1.firstBoot && !a1.platform.equalsIgnoreCase("curseforge")) {
                String str = f.getOrDefault(a1.platform, f.get("curseforge"));
                class_639 class_639 = class_639.method_2950(str);
                class_642 class_642 = new class_642("Cobblemon", str, class_642.class_8678.field_45611);
                class_642.method_2995(class_642.class_643.field_3768);
                a1.firstBoot = false;
                a(a1);
                class_412.method_36877(paramclass_437, paramclass_310, class_639, class_642, true, null);
              } 
            } catch (IOException iOException) {
              c.error("Failed to load modpack config", iOException);
            } 
            File file = new File("config/modpack_first_boot.txt");
            if (file.exists())
              file.delete(); 
          } 
        });
  }
  
  private static a c() {
    if (!e.exists()) {
      a a1 = new a();
      a(a1);
      return a1;
    } 
    InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(e), StandardCharsets.UTF_8);
    try {
      a a1 = (a)d.fromJson(inputStreamReader, a.class);
      a a2 = (a1 != null) ? a1 : new a();
      inputStreamReader.close();
      return a2;
    } catch (Throwable throwable) {
      try {
        inputStreamReader.close();
      } catch (Throwable throwable1) {
        throwable.addSuppressed(throwable1);
      } 
      throw throwable;
    } 
  }
  
  private static void a(a parama) {
    e.getParentFile().mkdirs();
    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(e), StandardCharsets.UTF_8);
    try {
      d.toJson(parama, outputStreamWriter);
      outputStreamWriter.close();
    } catch (Throwable throwable) {
      try {
        outputStreamWriter.close();
      } catch (Throwable throwable1) {
        throwable.addSuppressed(throwable1);
      } 
      throw throwable;
    } 
  }
  
  private static class a {
    String platform = "curseforge";
    
    boolean firstBoot = true;
  }
}


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-client-1.2.0.jar!\com\atlas\client\AtlasClient.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */