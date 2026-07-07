package com.atlas.client.stations;

import com.atlas.common.fabric.block.station.StationPCGUIConfiguration;
import com.atlas.common.fabric.block.station.packet.server.StationRemoveAllPokemonPacket;
import com.cobblemon.mod.common.CobblemonSounds;
import com.cobblemon.mod.common.api.gui.GuiUtilsKt;
import com.cobblemon.mod.common.client.CobblemonResources;
import com.cobblemon.mod.common.client.gui.pasture.RecallButton;
import com.cobblemon.mod.common.client.gui.pc.StorageWidget;
import com.cobblemon.mod.common.client.gui.summary.widgets.SoundlessWidget;
import com.cobblemon.mod.common.client.render.RenderHelperKt;
import com.cobblemon.mod.common.util.MiscUtilsKt;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.class_124;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_332;
import net.minecraft.class_4185;
import net.minecraft.class_5250;
import net.minecraft.class_8710;

public class c extends SoundlessWidget {
  private static final class_2960 a = MiscUtilsKt.cobblemonResource("textures/gui/pasture/pasture_panel.png");
  
  private final StorageWidget b;
  
  private final StationPCGUIConfiguration c;
  
  private final RecallButton d;
  
  private final b e;
  
  public c(StorageWidget paramStorageWidget, StationPCGUIConfiguration paramStationPCGUIConfiguration, int paramInt1, int paramInt2) {
    super(paramInt1, paramInt2, 82, 169, (class_2561)class_2561.method_43470("StationWidget"));
    this.b = paramStorageWidget;
    this.c = paramStationPCGUIConfiguration;
    this.d = new RecallButton(method_46426() + 6, method_46427() + 153, paramclass_4185 -> {
          this.b.getPcGui().playSound(CobblemonSounds.PC_CLICK);
          ClientPlayNetworking.send((class_8710)new StationRemoveAllPokemonPacket(this.c.getStationId()));
        });
    this.e = new b(method_46426() + 6, method_46427() + 31, this);
  }
  
  public void method_48579(class_332 paramclass_332, int paramInt1, int paramInt2, float paramFloat) {
    GuiUtilsKt.blitk(paramclass_332.method_51448(), a, Integer.valueOf(method_46426()), Integer.valueOf(method_46427()), Integer.valueOf(169), Integer.valueOf(82), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(82), Integer.valueOf(169), Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(1), Float.valueOf(1.0F), true, 1.0F);
    class_5250 class_5250 = class_2561.method_43470("Station").method_27692(class_124.field_1067);
    RenderHelperKt.drawScaledText(paramclass_332, CobblemonResources.INSTANCE.getDEFAULT_LARGE(), class_5250.method_27661(), Float.valueOf(method_46426() + 31.5F), Float.valueOf(method_46427() + 3.5F), 1.0F, Float.valueOf(1.0F), 2147483647, 16777215, true, false, null, null);
    this.e.method_25394(paramclass_332, paramInt1, paramInt2, paramFloat);
    this.d.method_25394(paramclass_332, paramInt1, paramInt2, paramFloat);
  }
  
  public boolean method_25402(double paramDouble1, double paramDouble2, int paramInt) {
    return (this.d.isHovered(paramDouble1, paramDouble2) && this.d.method_25402(paramDouble1, paramDouble2, paramInt)) ? true : ((this.e.a(paramDouble1, paramDouble2) && this.e.method_25402(paramDouble1, paramDouble2, paramInt)) ? true : super.method_25402(paramDouble1, paramDouble2, paramInt));
  }
  
  public StationPCGUIConfiguration a() {
    return this.c;
  }
  
  public StorageWidget b() {
    return this.b;
  }
  
  public b c() {
    return this.e;
  }
}


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-client-1.2.0.jar!\com\atlas\client\stations\c.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */