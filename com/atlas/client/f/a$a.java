package com.atlas.client.f;

import com.atlas.common.fabric.safari.expedition.SafariExpeditionModels;
import com.atlas.common.fabric.safari.expedition.network.SafariExpeditionNetwork;
import com.atlas.common.fabric.safari.expedition.screen.SafariExpeditionScreen;
import com.cobblemon.mod.common.client.gui.TypeIcon;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_4280;

public final class a extends class_4280.class_4281<a.a> {
  private static final int a = -1;
  
  private static final int b = -5656386;
  
  private static final int c = -800918;
  
  private static final int d = -30070;
  
  private static final int e = -8396545;
  
  private final SafariExpeditionModels.BuilderSelectedPokemonData f;
  
  private int g;
  
  private int h;
  
  private int i;
  
  private a(SafariExpeditionModels.BuilderSelectedPokemonData paramBuilderSelectedPokemonData) {
    this.f = paramBuilderSelectedPokemonData;
  }
  
  public void method_25343(class_332 paramclass_332, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, boolean paramBoolean, float paramFloat) {
    this.g = paramInt3;
    this.h = paramInt2;
    this.i = paramInt4;
    int i = paramInt3;
    int j = paramInt2 + 1;
    byte b1 = 20;
    int k = paramBoolean ? -8407297 : -13486269;
    paramclass_332.method_25296(i, j, i + paramInt4, j + 20, -14539217, -15197405);
    paramclass_332.method_49601(i, j, paramInt4, 20, k);
    paramclass_332.method_25294(i, j, i + 3, j + 20, -8407297);
    paramclass_332.method_51433((class_310.method_1551()).field_1772, String.valueOf(paramInt1 + 1), i + 5, j + 2, -5656386, false);
    String str1 = (this.f.shiny() ? "✦ " : "") + (this.f.shiny() ? "✦ " : "");
    boolean bool1 = this.f.legendary() ? true : (this.f.shiny() ? true : true);
    paramclass_332.method_51433((class_310.method_1551()).field_1772, "§l" + a(str1, 13), i + 12, j + 2, bool1, false);
    paramclass_332.method_51433((class_310.method_1551()).field_1772, "Lv " + this.f.level(), i + 12, j + 11, -5656386, false);
    int m = i + 56;
    byte b2 = 0;
    for (String str : this.f.types()) {
      if (b2 >= 2)
        break; 
      TypeIcon typeIcon = SafariExpeditionScreen.typeIconFor(m, j + 2, str, true);
      if (typeIcon != null) {
        typeIcon.render(paramclass_332);
        m += 12;
        b2++;
      } 
    } 
    String str2 = "+" + this.f.contribution();
    int n = (class_310.method_1551()).field_1772.method_1727(str2) + 6;
    int i1 = i + paramInt4 - n - 12;
    paramclass_332.method_25294(i1, j + 3, i1 + n, j + 13, -14015976);
    paramclass_332.method_49601(i1, j + 3, n, 10, -10860746);
    paramclass_332.method_51433((class_310.method_1551()).field_1772, str2, i1 + 3, j + 4, -800918, false);
    int i2 = i + paramInt4 - 10;
    boolean bool2 = (paramBoolean && paramInt6 >= i2 - 2 && paramInt6 <= i2 + 8 && paramInt7 >= j + 2 && paramInt7 <= j + 14) ? true : false;
    paramclass_332.method_25294(i2 - 2, j + 3, i2 + 7, j + 13, bool2 ? -11918300 : -14018022);
    paramclass_332.method_49601(i2 - 2, j + 3, 9, 10, bool2 ? -30070 : -10868182);
    paramclass_332.method_51433((class_310.method_1551()).field_1772, "§l✕", i2, j + 4, -30070, false);
  }
  
  public boolean method_25402(double paramDouble1, double paramDouble2, int paramInt) {
    SafariExpeditionNetwork.requestBuilderRemovePokemon(this.f.pokemonId());
    return true;
  }
  
  private String a(String paramString, int paramInt) {
    return (paramString.length() <= paramInt) ? paramString : (paramString.substring(0, Math.max(0, paramInt - 1)) + "…");
  }
  
  public class_2561 method_37006() {
    return (class_2561)class_2561.method_43470(this.f.displayName());
  }
}


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-client-1.2.0.jar!\com\atlas\client\f\a$a.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */