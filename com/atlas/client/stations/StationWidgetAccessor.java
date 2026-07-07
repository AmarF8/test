package com.atlas.client.stations;

import com.atlas.client.f.b;

public interface StationWidgetAccessor {
  c atlas$getStationWidget();
  
  default b atlas$getExpeditionWidget() {
    return null;
  }
}


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-client-1.2.0.jar!\com\atlas\client\stations\StationWidgetAccessor.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */