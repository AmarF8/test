package com.atlas.client.mixin.content;

import com.cobblemon.mod.common.api.pokemon.stats.Stat;
import com.cobblemon.mod.common.api.pokemon.stats.Stats;
import com.cobblemon.mod.common.client.gui.pc.PCGUI;
import com.cobblemon.mod.common.client.render.RenderHelperKt;
import com.cobblemon.mod.common.pokemon.IVs;
import com.cobblemon.mod.common.pokemon.Pokemon;
import net.minecraft.class_124;
import net.minecraft.class_2561;
import net.minecraft.class_2583;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_4068;
import net.minecraft.class_437;

final class AtlasIvOverlayDrawable implements class_4068 {
  private final PCGUI gui;
  
  private AtlasIvOverlayDrawable(PCGUI paramPCGUI) {
    this.gui = paramPCGUI;
  }
  
  public void method_25394(class_332 paramclass_332, int paramInt1, int paramInt2, float paramFloat) {
    Pokemon pokemon = this.gui.getPreviewPokemon$common();
    if (pokemon == null)
      return; 
    double d1 = (this.gui.field_22789 - 349) / 2.0D - 64.0D;
    double d2 = (this.gui.field_22790 - 205) / 2.0D + 31.0D;
    if (!PCIVOverlayMixin.MORE_COBBLEMON_TWEAKS_LOADED) {
      paramclass_332.method_25293(PCIVOverlayMixin.IV_DISPLAY_TEXTURE, (int)d1, (int)d2, 64, 128, 0.0F, 0.0F, 64, 128, 64, 128);
    } else {
      paramclass_332.method_25293(PCIVOverlayMixin.IV_DISPLAY_TEXTURE, (int)d1, (int)d2 + 112, 64, 16, 0.0F, 112.0F, 64, 16, 64, 128);
    } 
    d1 += 9.5D;
    d2 += 9.5D;
    if (PCIVOverlayMixin.MORE_COBBLEMON_TWEAKS_LOADED) {
      drawSpeedLabel(paramclass_332, d1, d2 + 75.0D, paramInt1, paramInt2);
      drawValue(paramclass_332, "Size:", PCIVOverlayMixin.sizeLabel(pokemon.getScaleModifier()), 16753920, d1, d2 + 105.0D, paramInt1, paramInt2);
      return;
    } 
    IVs iVs = pokemon.getIvs();
    d2 = drawStat(paramclass_332, "HP:", iVs.getOrDefault((Stat)Stats.HP), class_124.field_1060, d1, d2, paramInt1, paramInt2);
    d2 = drawStat(paramclass_332, "Atk:", iVs.getOrDefault((Stat)Stats.ATTACK), class_124.field_1061, d1, d2, paramInt1, paramInt2);
    d2 = drawStat(paramclass_332, "Def:", iVs.getOrDefault((Stat)Stats.DEFENCE), class_124.field_1065, d1, d2, paramInt1, paramInt2);
    d2 = drawStat(paramclass_332, "Sp.Atk:", iVs.getOrDefault((Stat)Stats.SPECIAL_ATTACK), class_124.field_1076, d1, d2, paramInt1, paramInt2);
    d2 = drawStat(paramclass_332, "Sp.Def:", iVs.getOrDefault((Stat)Stats.SPECIAL_DEFENCE), class_124.field_1054, d1, d2, paramInt1, paramInt2);
    d2 = drawStat(paramclass_332, "Speed:", iVs.getOrDefault((Stat)Stats.SPEED), class_124.field_1075, d1, d2, paramInt1, paramInt2);
    double d3 = (iVs.getOrDefault((Stat)Stats.HP) + iVs.getOrDefault((Stat)Stats.ATTACK) + iVs.getOrDefault((Stat)Stats.DEFENCE) + iVs.getOrDefault((Stat)Stats.SPECIAL_ATTACK) + iVs.getOrDefault((Stat)Stats.SPECIAL_DEFENCE) + iVs.getOrDefault((Stat)Stats.SPEED)) / 6.0D;
    d2 = drawIvValue(paramclass_332, "Avg:", d3, class_124.field_1068, d1, d2, paramInt1, paramInt2);
    drawValue(paramclass_332, "Size:", PCIVOverlayMixin.sizeLabel(pokemon.getScaleModifier()), 16753920, d1, d2, paramInt1, paramInt2);
  }
  
  private double drawStat(class_332 paramclass_332, String paramString, int paramInt1, class_124 paramclass_124, double paramDouble1, double paramDouble2, int paramInt2, int paramInt3) {
    return drawIvValue(paramclass_332, paramString, paramInt1, paramclass_124, paramDouble1, paramDouble2, paramInt2, paramInt3);
  }
  
  private double drawIvValue(class_332 paramclass_332, String paramString, double paramDouble1, class_124 paramclass_124, double paramDouble2, double paramDouble3, int paramInt1, int paramInt2) {
    String str = class_437.method_25442() ? (PCIVOverlayMixin.IV_PERCENT_FORMAT.format(paramDouble1 / 31.0D * 100.0D) + "%") : Integer.toString((int)paramDouble1);
    return drawValue(paramclass_332, paramString, str, paramclass_124, paramDouble2, paramDouble3, paramInt1, paramInt2);
  }
  
  private double drawValue(class_332 paramclass_332, String paramString1, String paramString2, class_124 paramclass_124, double paramDouble1, double paramDouble2, int paramInt1, int paramInt2) {
    return drawValue(paramclass_332, paramString1, paramString2, paramclass_124.method_532().intValue(), paramDouble1, paramDouble2, paramInt1, paramInt2);
  }
  
  private double drawValue(class_332 paramclass_332, String paramString1, String paramString2, int paramInt1, double paramDouble1, double paramDouble2, int paramInt2, int paramInt3) {
    RenderHelperKt.drawScaledText(paramclass_332, null, class_2561.method_43470(paramString1).method_27694(paramclass_2583 -> paramclass_2583.method_36139(paramInt)), Double.valueOf(paramDouble1), Double.valueOf(paramDouble2), 0.5F, Integer.valueOf(1), 2147483647, 16777215, false, true, Integer.valueOf(paramInt2), Integer.valueOf(paramInt3));
    int i = (class_310.method_1551()).field_1772.method_1727(paramString2);
    double d = paramDouble1 + 45.0D - (i * 0.5F);
    RenderHelperKt.drawScaledText(paramclass_332, null, class_2561.method_43470(paramString2).method_27692(class_124.field_1068), Double.valueOf(d), Double.valueOf(paramDouble2), 0.5F, Integer.valueOf(1), 2147483647, 16777215, false, true, Integer.valueOf(paramInt2), Integer.valueOf(paramInt3));
    return paramDouble2 + 15.0D;
  }
  
  private void drawSpeedLabel(class_332 paramclass_332, double paramDouble1, double paramDouble2, int paramInt1, int paramInt2) {
    paramclass_332.method_25294((int)paramDouble1 - 1, (int)paramDouble2 - 1, (int)paramDouble1 + 25, (int)paramDouble2 + 8, -13684945);
    RenderHelperKt.drawScaledText(paramclass_332, null, class_2561.method_43470("Speed:").method_27692(class_124.field_1075), Double.valueOf(paramDouble1), Double.valueOf(paramDouble2), 0.5F, Integer.valueOf(1), 2147483647, 16777215, false, true, Integer.valueOf(paramInt1), Integer.valueOf(paramInt2));
  }
}


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-client-1.2.0.jar!\com\atlas\client\mixin\content\PCIVOverlayMixin$AtlasIvOverlayDrawable.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */