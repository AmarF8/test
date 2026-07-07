package com.atlas.client.f;

import com.atlas.common.fabric.safari.expedition.SafariExpeditionModels;
import com.atlas.common.fabric.safari.expedition.network.SafariExpeditionNetwork;
import com.atlas.common.fabric.safari.expedition.screen.SafariExpeditionScreen;
import com.cobblemon.mod.common.api.Priority;
import com.cobblemon.mod.common.client.gui.TypeIcon;
import kotlin.Unit;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_350;
import net.minecraft.class_364;
import net.minecraft.class_4280;
import org.jetbrains.annotations.Nullable;

public final class a extends class_4280<a.a> {
  private static final int a = 160;
  
  private static final int b = 78;
  
  private static final int c = 22;
  
  private final b d;
  
  private final int e;
  
  private final int f;
  
  private boolean g;
  
  public a(int paramInt1, int paramInt2, b paramb) {
    super(class_310.method_1551(), 160, 78, 0, 22);
    this.d = paramb;
    this.e = paramInt1;
    this.f = paramInt2;
    method_46421(paramInt1);
    method_46419(paramInt2);
    method_55444(160, 78, paramInt1, paramInt2);
    paramb.a().getBuilderData().subscribeIncludingCurrent(Priority.NORMAL, paramExpeditionBuilderData -> {
          method_25339();
          if (paramExpeditionBuilderData != null)
            for (SafariExpeditionModels.BuilderSelectedPokemonData builderSelectedPokemonData : paramExpeditionBuilderData.selectedTeam())
              method_25321((class_350.class_351)new a(builderSelectedPokemonData));  
          return null;
        });
  }
  
  public int method_25322() {
    return 154;
  }
  
  protected int method_25329() {
    return method_46426() + this.field_22758 - 4;
  }
  
  protected void method_44398(class_332 paramclass_332, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {}
  
  protected void method_57715(class_332 paramclass_332) {
    paramclass_332.method_25294(method_46426(), method_46427(), method_46426() + 160, method_46427() + 78, -15855336);
    paramclass_332.method_49601(method_46426(), method_46427(), 160, 78, -14210251);
  }
  
  public void method_48579(class_332 paramclass_332, int paramInt1, int paramInt2, float paramFloat) {
    method_55444(160, 78, this.e, this.f);
    super.method_48579(paramclass_332, paramInt1, paramInt2, paramFloat);
    if (method_25396().isEmpty()) {
      paramclass_332.method_25300((class_310.method_1551()).field_1772, "Click a Pokémon to add", method_46426() + 80, method_46427() + 39 - 8, -5656386);
      paramclass_332.method_25300((class_310.method_1551()).field_1772, "from the PC →", method_46426() + 80, method_46427() + 39 + 2, -9537914);
    } 
  }
  
  public boolean method_25402(double paramDouble1, double paramDouble2, int paramInt) {
    this.g = (paramDouble1 >= method_25329() && paramDouble1 <= (method_25329() + 4) && paramDouble2 >= method_46427() && paramDouble2 <= method_55443());
    if (this.g)
      method_25398(true); 
    return super.method_25402(paramDouble1, paramDouble2, paramInt);
  }
  
  public boolean method_25403(double paramDouble1, double paramDouble2, int paramInt, double paramDouble3, double paramDouble4) {
    if (this.g && method_57717()) {
      method_25307(method_25341() + paramDouble4);
      return true;
    } 
    return super.method_25403(paramDouble1, paramDouble2, paramInt, paramDouble3, paramDouble4);
  }
  
  public boolean a(double paramDouble1, double paramDouble2) {
    return (paramDouble1 >= method_46426() && paramDouble1 <= (method_46426() + 160) && paramDouble2 >= method_46427() && paramDouble2 <= (method_46427() + 78));
  }
  
  public static final class a extends class_4280.class_4281<a> {
    private static final int a = -1;
    
    private static final int b = -5656386;
    
    private static final int c = -800918;
    
    private static final int d = -30070;
    
    private static final int e = -8396545;
    
    private final SafariExpeditionModels.BuilderSelectedPokemonData f;
    
    private int g;
    
    private int h;
    
    private int i;
    
    private a(SafariExpeditionModels.BuilderSelectedPokemonData param1BuilderSelectedPokemonData) {
      this.f = param1BuilderSelectedPokemonData;
    }
    
    public void method_25343(class_332 param1class_332, int param1Int1, int param1Int2, int param1Int3, int param1Int4, int param1Int5, int param1Int6, int param1Int7, boolean param1Boolean, float param1Float) {
      this.g = param1Int3;
      this.h = param1Int2;
      this.i = param1Int4;
      int i = param1Int3;
      int j = param1Int2 + 1;
      byte b1 = 20;
      int k = param1Boolean ? -8407297 : -13486269;
      param1class_332.method_25296(i, j, i + param1Int4, j + 20, -14539217, -15197405);
      param1class_332.method_49601(i, j, param1Int4, 20, k);
      param1class_332.method_25294(i, j, i + 3, j + 20, -8407297);
      param1class_332.method_51433((class_310.method_1551()).field_1772, String.valueOf(param1Int1 + 1), i + 5, j + 2, -5656386, false);
      String str1 = (this.f.shiny() ? "✦ " : "") + (this.f.shiny() ? "✦ " : "");
      boolean bool1 = this.f.legendary() ? true : (this.f.shiny() ? true : true);
      param1class_332.method_51433((class_310.method_1551()).field_1772, "§l" + a(str1, 13), i + 12, j + 2, bool1, false);
      param1class_332.method_51433((class_310.method_1551()).field_1772, "Lv " + this.f.level(), i + 12, j + 11, -5656386, false);
      int m = i + 56;
      byte b2 = 0;
      for (String str : this.f.types()) {
        if (b2 >= 2)
          break; 
        TypeIcon typeIcon = SafariExpeditionScreen.typeIconFor(m, j + 2, str, true);
        if (typeIcon != null) {
          typeIcon.render(param1class_332);
          m += 12;
          b2++;
        } 
      } 
      String str2 = "+" + this.f.contribution();
      int n = (class_310.method_1551()).field_1772.method_1727(str2) + 6;
      int i1 = i + param1Int4 - n - 12;
      param1class_332.method_25294(i1, j + 3, i1 + n, j + 13, -14015976);
      param1class_332.method_49601(i1, j + 3, n, 10, -10860746);
      param1class_332.method_51433((class_310.method_1551()).field_1772, str2, i1 + 3, j + 4, -800918, false);
      int i2 = i + param1Int4 - 10;
      boolean bool2 = (param1Boolean && param1Int6 >= i2 - 2 && param1Int6 <= i2 + 8 && param1Int7 >= j + 2 && param1Int7 <= j + 14) ? true : false;
      param1class_332.method_25294(i2 - 2, j + 3, i2 + 7, j + 13, bool2 ? -11918300 : -14018022);
      param1class_332.method_49601(i2 - 2, j + 3, 9, 10, bool2 ? -30070 : -10868182);
      param1class_332.method_51433((class_310.method_1551()).field_1772, "§l✕", i2, j + 4, -30070, false);
    }
    
    public boolean method_25402(double param1Double1, double param1Double2, int param1Int) {
      SafariExpeditionNetwork.requestBuilderRemovePokemon(this.f.pokemonId());
      return true;
    }
    
    private String a(String param1String, int param1Int) {
      return (param1String.length() <= param1Int) ? param1String : (param1String.substring(0, Math.max(0, param1Int - 1)) + "…");
    }
    
    public class_2561 method_37006() {
      return (class_2561)class_2561.method_43470(this.f.displayName());
    }
  }
}


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-client-1.2.0.jar!\com\atlas\client\f\a.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */