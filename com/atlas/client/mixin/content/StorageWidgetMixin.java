package com.atlas.client.mixin.content;

import com.atlas.client.f.b;
import com.atlas.client.stations.StationWidgetAccessor;
import com.atlas.client.stations.c;
import com.atlas.common.fabric.block.station.StationPCGUIConfiguration;
import com.atlas.common.fabric.safari.expedition.ExpeditionPCGUIConfiguration;
import com.cobblemon.mod.common.client.gui.pasture.PastureWidget;
import com.cobblemon.mod.common.client.gui.pc.PCGUI;
import com.cobblemon.mod.common.client.gui.pc.PCGUIConfiguration;
import com.cobblemon.mod.common.client.gui.pc.StorageWidget;
import com.cobblemon.mod.common.client.storage.ClientPC;
import com.cobblemon.mod.common.client.storage.ClientParty;
import java.lang.reflect.Field;
import net.minecraft.class_332;
import net.minecraft.class_339;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = {StorageWidget.class}, priority = 1500)
public abstract class StorageWidgetMixin implements StationWidgetAccessor {
  @Shadow(remap = false)
  @Mutable
  public PastureWidget pastureWidget;
  
  private c stationWidget;
  
  private b expeditionWidget;
  
  @Unique
  private Field atlas$boxListButtonField;
  
  @Unique
  private boolean atlas$boxListButtonSearched = false;
  
  @Inject(method = {"<init>"}, at = {@At("RETURN")}, remap = false)
  private void initStationWidget(int paramInt1, int paramInt2, PCGUI paramPCGUI, ClientPC paramClientPC, ClientParty paramClientParty, CallbackInfo paramCallbackInfo) {
    try {
      StorageWidget storageWidget = (StorageWidget)this;
      PCGUIConfiguration pCGUIConfiguration = storageWidget.getPcGui().getConfiguration();
      if (pCGUIConfiguration instanceof StationPCGUIConfiguration) {
        StationPCGUIConfiguration stationPCGUIConfiguration = (StationPCGUIConfiguration)pCGUIConfiguration;
        this.stationWidget = new c(storageWidget, stationPCGUIConfiguration, paramInt1 + 182, paramInt2 - 19);
        this.pastureWidget = null;
        atlas$hideBoxListButton();
      } else if (pCGUIConfiguration instanceof ExpeditionPCGUIConfiguration) {
        ExpeditionPCGUIConfiguration expeditionPCGUIConfiguration = (ExpeditionPCGUIConfiguration)pCGUIConfiguration;
        this.expeditionWidget = new b(storageWidget, expeditionPCGUIConfiguration, paramInt1 + 182, paramInt2 - 19);
        this.pastureWidget = null;
        atlas$hideBoxListButton();
      } 
    } catch (Exception exception) {}
  }
  
  @Unique
  private void atlas$hideBoxListButton() {
    try {
      if (!this.atlas$boxListButtonSearched) {
        this.atlas$boxListButtonSearched = true;
        for (Field field : getClass().getDeclaredFields()) {
          if (field.getName().contains("boxListButton")) {
            field.setAccessible(true);
            this.atlas$boxListButtonField = field;
            break;
          } 
        } 
      } 
      if (this.atlas$boxListButtonField != null) {
        Object object = this.atlas$boxListButtonField.get(this);
        if (object instanceof class_339) {
          class_339 class_339 = (class_339)object;
          class_339.field_22764 = false;
        } 
      } 
    } catch (Exception exception) {}
  }
  
  @Inject(method = {"renderWidget"}, at = {@At("TAIL")})
  private void renderStationWidget(class_332 paramclass_332, int paramInt1, int paramInt2, float paramFloat, CallbackInfo paramCallbackInfo) {
    if (this.stationWidget != null)
      this.stationWidget.method_48579(paramclass_332, paramInt1, paramInt2, paramFloat); 
    if (this.expeditionWidget != null)
      this.expeditionWidget.method_48579(paramclass_332, paramInt1, paramInt2, paramFloat); 
  }
  
  @Inject(method = {"mouseClicked"}, at = {@At("HEAD")}, cancellable = true)
  private void stationMouseClicked(double paramDouble1, double paramDouble2, int paramInt, CallbackInfoReturnable<Boolean> paramCallbackInfoReturnable) {
    if (this.stationWidget != null && this.stationWidget.method_25402(paramDouble1, paramDouble2, paramInt))
      paramCallbackInfoReturnable.cancel(); 
    if (this.expeditionWidget != null && this.expeditionWidget.method_25402(paramDouble1, paramDouble2, paramInt))
      paramCallbackInfoReturnable.cancel(); 
  }
  
  public c atlas$getStationWidget() {
    return this.stationWidget;
  }
  
  public b atlas$getExpeditionWidget() {
    return this.expeditionWidget;
  }
}


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-client-1.2.0.jar!\com\atlas\client\mixin\content\StorageWidgetMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */