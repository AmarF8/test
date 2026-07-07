package com.atlas.client.mixin.content;

import com.cobblemon.mod.common.api.pokemon.stats.Stat;
import com.cobblemon.mod.common.api.pokemon.stats.Stats;
import com.cobblemon.mod.common.client.gui.pc.PCGUI;
import com.cobblemon.mod.common.client.render.RenderHelperKt;
import com.cobblemon.mod.common.pokemon.IVs;
import com.cobblemon.mod.common.pokemon.Pokemon;
import java.text.DecimalFormat;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.class_124;
import net.minecraft.class_2561;
import net.minecraft.class_2583;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_4068;
import net.minecraft.class_437;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = {PCGUI.class}, priority = 3000)
public abstract class PCIVOverlayMixin extends class_437 {
  private static final int WIDTH = 64;
  
  private static final int HEIGHT = 128;
  
  private static final class_2960 IV_DISPLAY_TEXTURE = class_2960.method_60655("atlas", "textures/gui/pc/iv_display.png");
  
  private static final DecimalFormat IV_PERCENT_FORMAT = new DecimalFormat("#.##");
  
  private static final String[] SIZE_LABELS = new String[] { "XXS", "XS", "S", "M", "L", "XL", "XXL", "XXXL" };
  
  private static final float[] SIZE_SCALES = new float[] { 0.4F, 0.6F, 0.8F, 1.0F, 1.2F, 1.4F, 1.75F, 2.0F };
  
  private static final int SIZE_COLOR = 16753920;
  
  private static final boolean MORE_COBBLEMON_TWEAKS_LOADED = FabricLoader.getInstance().isModLoaded("more_cobblemon_tweaks");
  
  protected PCIVOverlayMixin(class_2561 paramclass_2561) {
    super(paramclass_2561);
  }
  
  @Inject(method = {"init"}, at = {@At("TAIL")})
  private void atlas$addIvOverlay(CallbackInfo paramCallbackInfo) {
    if (MORE_COBBLEMON_TWEAKS_LOADED)
      return; 
    method_37060(new AtlasIvOverlayDrawable((PCGUI)this));
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
  
  private static final class AtlasIvOverlayDrawable implements class_4068 {
    private final PCGUI gui;
    
    private AtlasIvOverlayDrawable(PCGUI param1PCGUI) {
      this.gui = param1PCGUI;
    }
    
    public void method_25394(class_332 param1class_332, int param1Int1, int param1Int2, float param1Float) {
      Pokemon pokemon = this.gui.getPreviewPokemon$common();
      if (pokemon == null)
        return; 
      double d1 = (this.gui.field_22789 - 349) / 2.0D - 64.0D;
      double d2 = (this.gui.field_22790 - 205) / 2.0D + 31.0D;
      if (!PCIVOverlayMixin.MORE_COBBLEMON_TWEAKS_LOADED) {
        param1class_332.method_25293(PCIVOverlayMixin.IV_DISPLAY_TEXTURE, (int)d1, (int)d2, 64, 128, 0.0F, 0.0F, 64, 128, 64, 128);
      } else {
        param1class_332.method_25293(PCIVOverlayMixin.IV_DISPLAY_TEXTURE, (int)d1, (int)d2 + 112, 64, 16, 0.0F, 112.0F, 64, 16, 64, 128);
      } 
      d1 += 9.5D;
      d2 += 9.5D;
      if (PCIVOverlayMixin.MORE_COBBLEMON_TWEAKS_LOADED) {
        drawSpeedLabel(param1class_332, d1, d2 + 75.0D, param1Int1, param1Int2);
        drawValue(param1class_332, "Size:", PCIVOverlayMixin.sizeLabel(pokemon.getScaleModifier()), 16753920, d1, d2 + 105.0D, param1Int1, param1Int2);
        return;
      } 
      IVs iVs = pokemon.getIvs();
      d2 = drawStat(param1class_332, "HP:", iVs.getOrDefault((Stat)Stats.HP), class_124.field_1060, d1, d2, param1Int1, param1Int2);
      d2 = drawStat(param1class_332, "Atk:", iVs.getOrDefault((Stat)Stats.ATTACK), class_124.field_1061, d1, d2, param1Int1, param1Int2);
      d2 = drawStat(param1class_332, "Def:", iVs.getOrDefault((Stat)Stats.DEFENCE), class_124.field_1065, d1, d2, param1Int1, param1Int2);
      d2 = drawStat(param1class_332, "Sp.Atk:", iVs.getOrDefault((Stat)Stats.SPECIAL_ATTACK), class_124.field_1076, d1, d2, param1Int1, param1Int2);
      d2 = drawStat(param1class_332, "Sp.Def:", iVs.getOrDefault((Stat)Stats.SPECIAL_DEFENCE), class_124.field_1054, d1, d2, param1Int1, param1Int2);
      d2 = drawStat(param1class_332, "Speed:", iVs.getOrDefault((Stat)Stats.SPEED), class_124.field_1075, d1, d2, param1Int1, param1Int2);
      double d3 = (iVs.getOrDefault((Stat)Stats.HP) + iVs.getOrDefault((Stat)Stats.ATTACK) + iVs.getOrDefault((Stat)Stats.DEFENCE) + iVs.getOrDefault((Stat)Stats.SPECIAL_ATTACK) + iVs.getOrDefault((Stat)Stats.SPECIAL_DEFENCE) + iVs.getOrDefault((Stat)Stats.SPEED)) / 6.0D;
      d2 = drawIvValue(param1class_332, "Avg:", d3, class_124.field_1068, d1, d2, param1Int1, param1Int2);
      drawValue(param1class_332, "Size:", PCIVOverlayMixin.sizeLabel(pokemon.getScaleModifier()), 16753920, d1, d2, param1Int1, param1Int2);
    }
    
    private double drawStat(class_332 param1class_332, String param1String, int param1Int1, class_124 param1class_124, double param1Double1, double param1Double2, int param1Int2, int param1Int3) {
      return drawIvValue(param1class_332, param1String, param1Int1, param1class_124, param1Double1, param1Double2, param1Int2, param1Int3);
    }
    
    private double drawIvValue(class_332 param1class_332, String param1String, double param1Double1, class_124 param1class_124, double param1Double2, double param1Double3, int param1Int1, int param1Int2) {
      String str = class_437.method_25442() ? (PCIVOverlayMixin.IV_PERCENT_FORMAT.format(param1Double1 / 31.0D * 100.0D) + "%") : Integer.toString((int)param1Double1);
      return drawValue(param1class_332, param1String, str, param1class_124, param1Double2, param1Double3, param1Int1, param1Int2);
    }
    
    private double drawValue(class_332 param1class_332, String param1String1, String param1String2, class_124 param1class_124, double param1Double1, double param1Double2, int param1Int1, int param1Int2) {
      return drawValue(param1class_332, param1String1, param1String2, param1class_124.method_532().intValue(), param1Double1, param1Double2, param1Int1, param1Int2);
    }
    
    private double drawValue(class_332 param1class_332, String param1String1, String param1String2, int param1Int1, double param1Double1, double param1Double2, int param1Int2, int param1Int3) {
      RenderHelperKt.drawScaledText(param1class_332, null, class_2561.method_43470(param1String1).method_27694(param1class_2583 -> param1class_2583.method_36139(param1Int)), Double.valueOf(param1Double1), Double.valueOf(param1Double2), 0.5F, Integer.valueOf(1), 2147483647, 16777215, false, true, Integer.valueOf(param1Int2), Integer.valueOf(param1Int3));
      int i = (class_310.method_1551()).field_1772.method_1727(param1String2);
      double d = param1Double1 + 45.0D - (i * 0.5F);
      RenderHelperKt.drawScaledText(param1class_332, null, class_2561.method_43470(param1String2).method_27692(class_124.field_1068), Double.valueOf(d), Double.valueOf(param1Double2), 0.5F, Integer.valueOf(1), 2147483647, 16777215, false, true, Integer.valueOf(param1Int2), Integer.valueOf(param1Int3));
      return param1Double2 + 15.0D;
    }
    
    private void drawSpeedLabel(class_332 param1class_332, double param1Double1, double param1Double2, int param1Int1, int param1Int2) {
      param1class_332.method_25294((int)param1Double1 - 1, (int)param1Double2 - 1, (int)param1Double1 + 25, (int)param1Double2 + 8, -13684945);
      RenderHelperKt.drawScaledText(param1class_332, null, class_2561.method_43470("Speed:").method_27692(class_124.field_1075), Double.valueOf(param1Double1), Double.valueOf(param1Double2), 0.5F, Integer.valueOf(1), 2147483647, 16777215, false, true, Integer.valueOf(param1Int1), Integer.valueOf(param1Int2));
    }
  }
}


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-client-1.2.0.jar!\com\atlas\client\mixin\content\PCIVOverlayMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */