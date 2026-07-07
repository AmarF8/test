package com.atlas.client.stations;

import com.cobblemon.mod.common.api.gui.GuiUtilsKt;
import com.cobblemon.mod.common.client.CobblemonResources;
import com.cobblemon.mod.common.client.gui.CobblemonRenderable;
import com.cobblemon.mod.common.client.render.RenderHelperKt;
import com.cobblemon.mod.common.util.LocalizationUtilsKt;
import com.cobblemon.mod.common.util.MiscUtilsKt;
import net.minecraft.class_1144;
import net.minecraft.class_124;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_332;
import net.minecraft.class_4185;
import net.minecraft.class_5250;

public class a extends class_4185 implements CobblemonRenderable {
  private static final int a = 79;
  
  private static final int b = 70;
  
  private static final int c = 17;
  
  private static final class_2960 d = MiscUtilsKt.cobblemonResource("textures/gui/pasture/pasture_button.png");
  
  public static int a() {
    return a;
  }
  
  public a(int paramInt1, int paramInt2, class_4185.class_4241 paramclass_4241) {
    super(paramInt1, paramInt2, 70, 17, (class_2561)class_2561.method_43470("Retrieve"), paramclass_4241, field_40754);
  }
  
  protected void method_48579(class_332 paramclass_332, int paramInt1, int paramInt2, float paramFloat) {
    boolean bool = a(paramInt1, paramInt2) ? true : false;
    GuiUtilsKt.blitk(paramclass_332.method_51448(), d, Integer.valueOf(method_46426()), Integer.valueOf(method_46427()), Integer.valueOf(17), Integer.valueOf(70), Integer.valueOf(0), Integer.valueOf(bool), Float.valueOf(1.0F), Float.valueOf(1.0F), Integer.valueOf(0), Integer.valueOf(34));
    class_5250 class_5250 = LocalizationUtilsKt.lang("ui.pasture.recall_all", new Object[0]).method_27692(class_124.field_1067);
    RenderHelperKt.drawScaledText(paramclass_332, CobblemonResources.INSTANCE.getDEFAULT_LARGE(), class_5250.method_27661(), Float.valueOf(method_46426() + 35.0F), Float.valueOf(method_46427() + 4.0F), 1.0F, Float.valueOf(1.0F), 2147483647, 16777215, true, true, null, null);
  }
  
  public void method_25354(class_1144 paramclass_1144) {}
  
  public boolean a(double paramDouble1, double paramDouble2) {
    return (paramDouble1 >= method_46426() && paramDouble1 <= (method_46426() + 70) && paramDouble2 >= method_46427() && paramDouble2 <= (method_46427() + 17));
  }
}


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-client-1.2.0.jar!\com\atlas\client\stations\a.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */