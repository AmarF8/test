package com.atlas.client.mixin.content;

import com.atlas.client.f.b;
import com.atlas.client.g.a;
import com.atlas.client.stations.StationWidgetAccessor;
import com.atlas.client.stations.c;
import com.cobblemon.mod.common.client.gui.pc.IconButton;
import com.cobblemon.mod.common.client.gui.pc.PCGUI;
import com.cobblemon.mod.common.client.gui.pc.StorageWidget;
import com.cobblemon.mod.common.client.gui.pc.WallpapersScrollingWidget;
import com.cobblemon.mod.common.client.gui.summary.Summary;
import com.cobblemon.mod.common.client.gui.summary.SummaryButton;
import com.cobblemon.mod.common.client.gui.summary.widgets.MarkingsWidget;
import com.cobblemon.mod.common.pokemon.Pokemon;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_364;
import net.minecraft.class_4185;
import net.minecraft.class_437;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({PCGUI.class})
public abstract class PCGUIMixin extends class_437 {
  @Shadow(remap = false)
  public StorageWidget storageWidget;
  
  @Shadow
  private MarkingsWidget markingsWidget;
  
  @Shadow
  private boolean showCosmeticItem;
  
  @Shadow
  private int currentStatIndex;
  
  @Shadow(remap = false)
  private static class_2960 buttonInfoArrow;
  
  @Shadow(remap = false)
  private WallpapersScrollingWidget wallpaperWidget;
  
  protected PCGUIMixin(class_2561 paramclass_2561) {
    super(paramclass_2561);
  }
  
  @Shadow
  public abstract void setPreviewPokemon(@Nullable Pokemon paramPokemon, boolean paramBoolean);
  
  @Inject(method = {"init"}, at = {@At(value = "INVOKE", target = "Ljava/util/List;clear()V", ordinal = 0)}, cancellable = true)
  private void skipOptionButtonsIfStation(CallbackInfo paramCallbackInfo) {
    if (this.storageWidget != null) {
      c c = ((StationWidgetAccessor)this.storageWidget).atlas$getStationWidget();
      b b = ((StationWidgetAccessor)this.storageWidget).atlas$getExpeditionWidget();
      if (c != null || b != null) {
        paramCallbackInfo.cancel();
        PCGUI pCGUI = (PCGUI)this;
        int i = (this.field_22789 - 349) / 2;
        int j = (this.field_22790 - 205) / 2;
        this.markingsWidget = new MarkingsWidget(Integer.valueOf(i + 29), Double.valueOf(j + 96.5D), null, false);
        method_37063((class_364)this.markingsWidget);
        SummaryButton[] arrayOfSummaryButton = new SummaryButton[1];
        arrayOfSummaryButton[0] = new SummaryButton(i + 67.0F, j + 107.0F, Integer.valueOf(12), Integer.valueOf(12), paramclass_4185 -> {
              this.showCosmeticItem = !this.showCosmeticItem;
              paramArrayOfSummaryButton[0].setButtonActive(this.showCosmeticItem);
            }class_2561.method_43473(), Summary.Companion.getIconCosmeticItemResource(), Summary.Companion.getIconHeldItemResource(), paramSummaryButton -> Boolean.valueOf(true), paramSummaryButton -> Boolean.valueOf(true), true, false, true, true, 1.0F, 0.5F);
        method_37063((class_364)arrayOfSummaryButton[0]);
        method_37063((class_364)new IconButton(i + 1, j + 157, 10, 16, buttonInfoArrow, null, null, "switch", paramclass_4185 -> this.currentStatIndex = (this.currentStatIndex + 1) % 3));
        if (this.wallpaperWidget == null) {
          this.wallpaperWidget = new WallpapersScrollingWidget(0, 0, pCGUI, this.storageWidget);
          this.wallpaperWidget.field_22764 = false;
        } 
        setPreviewPokemon((Pokemon)null, false);
        method_25426();
      } 
    } 
  }
  
  @Inject(method = {"mouseScrolled"}, at = {@At("HEAD")}, cancellable = true)
  private void stationMouseScrolled(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, CallbackInfoReturnable<Boolean> paramCallbackInfoReturnable) {
    if (this.storageWidget != null) {
      c c = ((StationWidgetAccessor)this.storageWidget).atlas$getStationWidget();
      b b = ((StationWidgetAccessor)this.storageWidget).atlas$getExpeditionWidget();
      if (c != null) {
        if (c.c().a(paramDouble1, paramDouble2)) {
          c.c().method_25401(paramDouble1, paramDouble2, paramDouble3, paramDouble4);
          paramCallbackInfoReturnable.setReturnValue(Boolean.valueOf(true));
        } 
      } else if (b != null && b.b().a(paramDouble1, paramDouble2)) {
        b.b().method_25401(paramDouble1, paramDouble2, paramDouble3, paramDouble4);
        paramCallbackInfoReturnable.setReturnValue(Boolean.valueOf(true));
      } 
    } 
  }
  
  @Inject(method = {"init"}, at = {@At("TAIL")})
  private void atlas$addSealButton(CallbackInfo paramCallbackInfo) {
    if (this.storageWidget == null)
      return; 
    c c = ((StationWidgetAccessor)this.storageWidget).atlas$getStationWidget();
    b b = ((StationWidgetAccessor)this.storageWidget).atlas$getExpeditionWidget();
    if (c != null || b != null)
      return; 
    int i = (this.field_22789 - 349) / 2 - 61;
    int j = (this.field_22790 - 205) / 2 + 161;
    method_37063((class_364)new a(i, j, this.storageWidget));
  }
  
  @Inject(method = {"mouseDragged"}, at = {@At("HEAD")}, cancellable = true)
  private void stationMouseDragged(double paramDouble1, double paramDouble2, int paramInt, double paramDouble3, double paramDouble4, CallbackInfoReturnable<Boolean> paramCallbackInfoReturnable) {
    if (this.storageWidget != null) {
      c c = ((StationWidgetAccessor)this.storageWidget).atlas$getStationWidget();
      b b = ((StationWidgetAccessor)this.storageWidget).atlas$getExpeditionWidget();
      if (c != null && c.c().a(paramDouble1, paramDouble2)) {
        if (c.c().method_25403(paramDouble1, paramDouble2, paramInt, paramDouble3, paramDouble4))
          paramCallbackInfoReturnable.setReturnValue(Boolean.valueOf(true)); 
      } else if (b != null && b.b().a(paramDouble1, paramDouble2) && b.b().method_25403(paramDouble1, paramDouble2, paramInt, paramDouble3, paramDouble4)) {
        paramCallbackInfoReturnable.setReturnValue(Boolean.valueOf(true));
      } 
    } 
  }
}


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-client-1.2.0.jar!\com\atlas\client\mixin\content\PCGUIMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */