package com.atlas.client.mixin.compat.morecobblemon;

import com.cobblemon.mod.common.api.pokemon.stats.Stat;
import com.cobblemon.mod.common.api.pokemon.stats.Stats;
import com.cobblemon.mod.common.client.gui.pc.PCGUI;
import com.cobblemon.mod.common.client.render.RenderHelperKt;
import com.cobblemon.mod.common.pokemon.IVs;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.mojang.blaze3d.systems.RenderSystem;
import java.text.DecimalFormat;
import net.minecraft.class_124;
import net.minecraft.class_2561;
import net.minecraft.class_2583;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_437;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@Mixin(targets = {"me.justahuman.more_cobblemon_tweaks.features.pc.IvWidget"}, remap = false)
public abstract class MoreCobblemonTweaksIvWidgetMixin {
  private static final int WIDTH = 64;
  
  private static final int HEIGHT = 128;
  
  private static final class_2960 IV_DISPLAY_TEXTURE = class_2960.method_60655("atlas", "textures/gui/pc/iv_display.png");
  
  private static final DecimalFormat IV_PERCENT_FORMAT = new DecimalFormat("#.##");
  
  private static final String[] SIZE_LABELS = new String[] { "XXS", "XS", "S", "M", "L", "XL", "XXL", "XXXL" };
  
  private static final float[] SIZE_SCALES = new float[] { 0.4F, 0.6F, 0.8F, 1.0F, 1.2F, 1.4F, 1.75F, 2.0F };
  
  private static final int SIZE_COLOR = 16753920;
  
  @Shadow(remap = false)
  protected PCGUI gui;
  
  @Inject(method = {"method_25394"}, at = {@At("HEAD")}, cancellable = true, remap = false)
  private void atlas$renderIvWidget(class_332 paramclass_332, int paramInt1, int paramInt2, float paramFloat, CallbackInfo paramCallbackInfo) {
    Pokemon pokemon = this.gui.getPreviewPokemon$common();
    if (pokemon == null) {
      paramCallbackInfo.cancel();
      return;
    } 
    double d1 = (this.gui.field_22789 - 349) / 2.0D - 64.0D;
    double d2 = (this.gui.field_22790 - 205) / 2.0D + 31.0D;
    RenderSystem.enableBlend();
    RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
    paramclass_332.method_25293(IV_DISPLAY_TEXTURE, (int)d1, (int)d2, 64, 128, 0.0F, 0.0F, 64, 128, 64, 128);
    d1 += 9.5D;
    d2 += 9.5D;
    IVs iVs = pokemon.getIvs();
    d2 = drawStat(paramclass_332, "HP:", iVs.getOrDefault((Stat)Stats.HP), class_124.field_1060, d1, d2, paramInt1, paramInt2);
    d2 = drawStat(paramclass_332, "Atk:", iVs.getOrDefault((Stat)Stats.ATTACK), class_124.field_1061, d1, d2, paramInt1, paramInt2);
    d2 = drawStat(paramclass_332, "Def:", iVs.getOrDefault((Stat)Stats.DEFENCE), class_124.field_1065, d1, d2, paramInt1, paramInt2);
    d2 = drawStat(paramclass_332, "Sp.Atk:", iVs.getOrDefault((Stat)Stats.SPECIAL_ATTACK), class_124.field_1076, d1, d2, paramInt1, paramInt2);
    d2 = drawStat(paramclass_332, "Sp.Def:", iVs.getOrDefault((Stat)Stats.SPECIAL_DEFENCE), class_124.field_1054, d1, d2, paramInt1, paramInt2);
    d2 = drawStat(paramclass_332, "Speed:", iVs.getOrDefault((Stat)Stats.SPEED), class_124.field_1075, d1, d2, paramInt1, paramInt2);
    double d3 = (iVs.getOrDefault((Stat)Stats.HP) + iVs.getOrDefault((Stat)Stats.ATTACK) + iVs.getOrDefault((Stat)Stats.DEFENCE) + iVs.getOrDefault((Stat)Stats.SPECIAL_ATTACK) + iVs.getOrDefault((Stat)Stats.SPECIAL_DEFENCE) + iVs.getOrDefault((Stat)Stats.SPEED)) / 6.0D;
    d2 = drawIvValue(paramclass_332, "Avg:", d3, class_124.field_1068, d1, d2, paramInt1, paramInt2);
    drawValue(paramclass_332, "Size:", sizeLabel(pokemon.getScaleModifier()), 16753920, d1, d2, paramInt1, paramInt2);
    paramCallbackInfo.cancel();
  }
  
  private double drawStat(class_332 paramclass_332, String paramString, int paramInt1, class_124 paramclass_124, double paramDouble1, double paramDouble2, int paramInt2, int paramInt3) {
    return drawIvValue(paramclass_332, paramString, paramInt1, paramclass_124, paramDouble1, paramDouble2, paramInt2, paramInt3);
  }
  
  private double drawIvValue(class_332 paramclass_332, String paramString, double paramDouble1, class_124 paramclass_124, double paramDouble2, double paramDouble3, int paramInt1, int paramInt2) {
    String str = class_437.method_25442() ? (IV_PERCENT_FORMAT.format(paramDouble1 / 31.0D * 100.0D) + "%") : Integer.toString((int)paramDouble1);
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
  
  private static String sizeLabel(float paramFloat) {
    String str = "M";
    float f = Math.abs(1.0F - paramFloat);
    for (byte b = 0; b < SIZE_SCALES.length; b++) {
      float f1 = Math.abs(SIZE_SCALES[b] - paramFloat);
      if (f1 < f) {
        f = f1;
        str = SIZE_LABELS[b];
      } 
    } 
    return str;
  }
}


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-client-1.2.0.jar!\com\atlas\client\mixin\compat\morecobblemon\MoreCobblemonTweaksIvWidgetMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */