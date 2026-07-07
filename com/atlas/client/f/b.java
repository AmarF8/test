package com.atlas.client.f;

import com.atlas.common.fabric.safari.expedition.ExpeditionPCGUIConfiguration;
import com.atlas.common.fabric.safari.expedition.SafariExpeditionModels;
import com.atlas.common.fabric.safari.expedition.network.SafariExpeditionNetwork;
import com.atlas.common.fabric.safari.expedition.screen.SafariExpeditionScreen;
import com.cobblemon.mod.common.client.gui.TypeIcon;
import com.cobblemon.mod.common.client.gui.pc.StorageWidget;
import com.cobblemon.mod.common.client.gui.summary.widgets.SoundlessWidget;
import java.util.Locale;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_327;
import net.minecraft.class_332;
import org.jetbrains.annotations.NotNull;

public final class b extends SoundlessWidget {
  private static final int a = 172;
  
  private static final int b = 244;
  
  private static final int c = -14999768;
  
  private static final int d = -15723750;
  
  private static final int e = -12959408;
  
  private static final int f = -14210251;
  
  private static final int g = -15723751;
  
  private static final int h = -14539217;
  
  private static final int i = -15197405;
  
  private static final int j = -1;
  
  private static final int k = -1644047;
  
  private static final int l = -5656386;
  
  private static final int m = -9537914;
  
  private static final int n = -8396670;
  
  private static final int o = -800918;
  
  private static final int p = -30070;
  
  private static final int q = -8407297;
  
  private final StorageWidget r;
  
  private final ExpeditionPCGUIConfiguration s;
  
  private final a t;
  
  public b(StorageWidget paramStorageWidget, ExpeditionPCGUIConfiguration paramExpeditionPCGUIConfiguration, int paramInt1, int paramInt2) {
    super(paramInt1, paramInt2, 82, 169, (class_2561)class_2561.method_43470("ExpeditionWidget"));
    this.r = paramStorageWidget;
    this.s = paramExpeditionPCGUIConfiguration;
    this.t = new a(method_46426() + 6, method_46427() + 122, this);
  }
  
  public void method_48579(class_332 paramclass_332, int paramInt1, int paramInt2, float paramFloat) {
    SafariExpeditionModels.ExpeditionBuilderData expeditionBuilderData = (SafariExpeditionModels.ExpeditionBuilderData)this.s.getBuilderData().get();
    class_327 class_327 = (class_310.method_1551()).field_1772;
    int i = method_46426();
    int j = method_46427();
    paramclass_332.method_25296(i, j, i + 172, j + 244, -14999768, -15723750);
    paramclass_332.method_49601(i, j, 172, 244, -12959408);
    paramclass_332.method_25292(i + 1, i + 172 - 2, j + 1, 587202559);
    if (expeditionBuilderData == null) {
      paramclass_332.method_25300(class_327, "Loading...", i + 86, j + 122 - 4, -5656386);
      this.t.method_25394(paramclass_332, paramInt1, paramInt2, paramFloat);
      return;
    } 
    SafariExpeditionModels.ExpeditionTier expeditionTier = expeditionBuilderData.balloon().expedition().tier();
    int k = expeditionTier.color() | 0xFF000000;
    paramclass_332.method_25294(i, j, i + 172, j + 3, k);
    paramclass_332.method_25294(i, j + 3, i + 172, j + 4, a(k, 102));
    a(paramclass_332, i, j, expeditionBuilderData, k);
    a(paramclass_332, i + 6, j + 42, 160, expeditionBuilderData);
    b(paramclass_332, i + 6, j + 76, 160, expeditionBuilderData);
    int m = j + 102;
    paramclass_332.method_25294(i + 6, m + 5, i + 9, m + 12, -8407297);
    paramclass_332.method_51433(class_327, "§lSQUAD", i + 13, m + 4, -1644047, false);
    String str = "" + expeditionBuilderData.selectedCount() + " / " + expeditionBuilderData.selectedCount();
    paramclass_332.method_51433(class_327, str, i + 172 - 6 - class_327.method_1727(str), m + 4, -5656386, false);
    this.t.method_25394(paramclass_332, paramInt1, paramInt2, paramFloat);
    int n = j + 185;
    int i1;
    for (i1 = 0; i1 < Math.min(2, expeditionBuilderData.synergyNotes().size()); i1++)
      paramclass_332.method_51433(class_327, "★ " + a(expeditionBuilderData.synergyNotes().get(i1), 158), i + 6, n + i1 * 9, -8396670, false); 
    if (!expeditionBuilderData.statusText().isBlank() && !expeditionBuilderData.canLaunch())
      paramclass_332.method_51433(class_327, "⚠ " + a(expeditionBuilderData.statusText(), 158), i + 6, j + 203, -30070, false); 
    i1 = j + 244 - 32;
    a(paramclass_332, i + 6, i1, 160, 13, expeditionBuilderData, paramInt1, paramInt2);
    int i2 = j + 244 - 16;
    b(paramclass_332, i + 6, i2, 50, 13, "CLEAR", paramInt1, paramInt2, (expeditionBuilderData.selectedCount() > 0));
    a(paramclass_332, i + 58, i2, 108, 13, "LAUNCH ▸", paramInt1, paramInt2, expeditionBuilderData.canLaunch());
  }
  
  private void a(@NotNull class_332 paramclass_332, int paramInt1, int paramInt2, @NotNull SafariExpeditionModels.ExpeditionBuilderData paramExpeditionBuilderData, int paramInt3) {
    class_327 class_327 = (class_310.method_1551()).field_1772;
    String str = paramExpeditionBuilderData.balloon().expedition().tier().displayName().toUpperCase(Locale.ROOT);
    int i = class_327.method_1727(str) + 10;
    int j = paramInt1 + 172 - i - 6;
    paramclass_332.method_25294(j, paramInt2 + 7, j + i, paramInt2 + 17, a(paramInt3, 85));
    paramclass_332.method_49601(j, paramInt2 + 7, i, 10, paramInt3);
    paramclass_332.method_51433(class_327, "§l" + str, j + 5, paramInt2 + 8, paramInt3, false);
    int k = j - paramInt1 + 6 - 4;
    paramclass_332.method_51433(class_327, "§l" + a(paramExpeditionBuilderData.balloon().expedition().displayName(), k), paramInt1 + 6, paramInt2 + 8, -1, true);
    paramclass_332.method_51433(class_327, a(paramExpeditionBuilderData.balloon().areaName(), 156), paramInt1 + 6, paramInt2 + 21, -5656386, false);
    paramclass_332.method_51433(class_327, "· " + a(paramExpeditionBuilderData.balloon().locationHint(), 150), paramInt1 + 6, paramInt2 + 30, -9537914, false);
  }
  
  private String a(@NotNull String paramString, int paramInt) {
    class_327 class_327 = (class_310.method_1551()).field_1772;
    if (class_327.method_1727(paramString) <= paramInt)
      return paramString; 
    int i = class_327.method_1727("…");
    int j;
    for (j = paramString.length(); j > 0 && class_327.method_1727(paramString.substring(0, j)) + i > paramInt; j--);
    return paramString.substring(0, j) + "…";
  }
  
  private void a(@NotNull class_332 paramclass_332, int paramInt1, int paramInt2, int paramInt3, @NotNull SafariExpeditionModels.ExpeditionBuilderData paramExpeditionBuilderData) {
    byte b1 = 28;
    paramclass_332.method_25296(paramInt1, paramInt2, paramInt1 + paramInt3, paramInt2 + 28, -14539217, -15197405);
    paramclass_332.method_49601(paramInt1, paramInt2, paramInt3, 28, -14210251);
    int i = paramInt3 / 3;
    a(paramclass_332, paramInt1, paramInt2 + 3, i, "PWR", String.valueOf(paramExpeditionBuilderData.teamPower()), -8407297);
    paramclass_332.method_25294(paramInt1 + i, paramInt2 + 4, paramInt1 + i + 1, paramInt2 + 28 - 7, -14012872);
    a(paramclass_332, paramInt1 + i, paramInt2 + 3, i, "SUC", "" + paramExpeditionBuilderData.successChance() + "%", a(paramExpeditionBuilderData.successChance()));
    paramclass_332.method_25294(paramInt1 + i * 2, paramInt2 + 4, paramInt1 + i * 2 + 1, paramInt2 + 28 - 7, -14012872);
    a(paramclass_332, paramInt1 + i * 2, paramInt2 + 3, paramInt3 - i * 2, "TEAM", "" + paramExpeditionBuilderData.selectedCount() + "/" + paramExpeditionBuilderData.selectedCount(), (paramExpeditionBuilderData.selectedCount() >= paramExpeditionBuilderData.minimumTeamSize()) ? -8396670 : -800918);
    int j = paramInt1 + 2;
    int k = paramInt3 - 4;
    int m = paramInt2 + 28 - 4;
    paramclass_332.method_25294(j, m, j + k, m + 2, -15987180);
    int n = Math.max(0, Math.min(k, k * paramExpeditionBuilderData.successChance() / 100));
    if (n > 0)
      paramclass_332.method_25296(j, m, j + n, m + 2, a(paramExpeditionBuilderData.successChance()), c(a(paramExpeditionBuilderData.successChance()))); 
  }
  
  private void a(@NotNull class_332 paramclass_332, int paramInt1, int paramInt2, int paramInt3, String paramString1, String paramString2, int paramInt4) {
    class_327 class_327 = (class_310.method_1551()).field_1772;
    int i = paramInt1 + (paramInt3 - class_327.method_1727(paramString1)) / 2;
    int j = paramInt1 + (paramInt3 - class_327.method_1727("§l" + paramString2)) / 2;
    paramclass_332.method_51433(class_327, paramString1, i, paramInt2, -9537914, false);
    paramclass_332.method_51433(class_327, "§l" + paramString2, j, paramInt2 + 10, paramInt4, false);
  }
  
  private void b(@NotNull class_332 paramclass_332, int paramInt1, int paramInt2, int paramInt3, @NotNull SafariExpeditionModels.ExpeditionBuilderData paramExpeditionBuilderData) {
    class_327 class_327 = (class_310.method_1551()).field_1772;
    paramclass_332.method_51433(class_327, "TYPE FIT", paramInt1, paramInt2 + 4, -9537914, false);
    int i = paramInt1 + 48;
    int j = i;
    byte b1 = 0;
    int k = Math.max(1, (paramInt1 + paramInt3 - i) / 18);
    for (String str : paramExpeditionBuilderData.balloon().expedition().allowedTypes()) {
      if (b1 >= k)
        break; 
      TypeIcon typeIcon = SafariExpeditionScreen.typeIconFor(j, paramInt2, str, false);
      if (typeIcon != null) {
        typeIcon.render(paramclass_332);
        j += 18;
        b1++;
      } 
    } 
    if (b1 == 0)
      paramclass_332.method_51433(class_327, "any species welcome", i, paramInt2 + 4, -5656386, false); 
  }
  
  private void a(@NotNull class_332 paramclass_332, int paramInt1, int paramInt2, int paramInt3, int paramInt4, @NotNull SafariExpeditionModels.ExpeditionBuilderData paramExpeditionBuilderData, int paramInt5, int paramInt6) {
    class_327 class_327 = (class_310.method_1551()).field_1772;
    boolean bool = a(paramInt5, paramInt6, paramInt1, paramInt2, paramInt3, paramInt4);
    int i = b(paramExpeditionBuilderData);
    paramclass_332.method_25296(paramInt1, paramInt2, paramInt1 + paramInt3, paramInt2 + paramInt4, bool ? b(-14736595) : -14736595, -15328990);
    paramclass_332.method_49601(paramInt1, paramInt2, paramInt3, paramInt4, bool ? i : -14210251);
    paramclass_332.method_25294(paramInt1, paramInt2, paramInt1 + 3, paramInt2 + paramInt4, i);
    paramclass_332.method_51433(class_327, "BOOSTER", paramInt1 + 8, paramInt2 + 2, -9537914, false);
    String str = a(paramExpeditionBuilderData);
    paramclass_332.method_51433(class_327, "§l" + str, paramInt1 + paramInt3 - class_327.method_1727("§l" + str) - 6, paramInt2 + 2, i, false);
  }
  
  private void a(@NotNull class_332 paramclass_332, int paramInt1, int paramInt2, int paramInt3, int paramInt4, @NotNull String paramString, int paramInt5, int paramInt6, boolean paramBoolean) {
    class_327 class_327 = (class_310.method_1551()).field_1772;
    boolean bool = (paramBoolean && a(paramInt5, paramInt6, paramInt1, paramInt2, paramInt3, paramInt4)) ? true : false;
    int i = paramBoolean ? (bool ? -13731252 : -14458816) : -13421773;
    int j = paramBoolean ? (bool ? -15118288 : -15451613) : -14737633;
    int k = paramBoolean ? (bool ? -8396670 : -11555213) : -12237499;
    paramclass_332.method_25296(paramInt1, paramInt2, paramInt1 + paramInt3, paramInt2 + paramInt4, i, j);
    paramclass_332.method_49601(paramInt1, paramInt2, paramInt3, paramInt4, k);
    if (paramBoolean) {
      paramclass_332.method_25294(paramInt1, paramInt2, paramInt1 + paramInt3, paramInt2 + 1, a(-8396670, 153));
      paramclass_332.method_25294(paramInt1, paramInt2 + paramInt4 - 1, paramInt1 + paramInt3, paramInt2 + paramInt4, a(-16777216, 85));
    } 
    String str = "§l" + paramString;
    int m = class_327.method_1727(str);
    paramclass_332.method_51433(class_327, str, paramInt1 + (paramInt3 - m) / 2, paramInt2 + (paramInt4 - 7) / 2, paramBoolean ? -1 : -5656386, true);
  }
  
  private void b(@NotNull class_332 paramclass_332, int paramInt1, int paramInt2, int paramInt3, int paramInt4, @NotNull String paramString, int paramInt5, int paramInt6, boolean paramBoolean) {
    class_327 class_327 = (class_310.method_1551()).field_1772;
    boolean bool = (paramBoolean && a(paramInt5, paramInt6, paramInt1, paramInt2, paramInt3, paramInt4)) ? true : false;
    int i = bool ? -12967130 : -14407889;
    int j = bool ? -14412262 : -15197405;
    paramclass_332.method_25296(paramInt1, paramInt2, paramInt1 + paramInt3, paramInt2 + paramInt4, i, j);
    paramclass_332.method_49601(paramInt1, paramInt2, paramInt3, paramInt4, paramBoolean ? (bool ? -30070 : -12762548) : -14210251);
    String str = "§l" + paramString;
    int k = class_327.method_1727(str);
    paramclass_332.method_51433(class_327, str, paramInt1 + (paramInt3 - k) / 2, paramInt2 + (paramInt4 - 7) / 2, paramBoolean ? -1644047 : -9537914, false);
  }
  
  public boolean method_25402(double paramDouble1, double paramDouble2, int paramInt) {
    if (this.t.a(paramDouble1, paramDouble2) && this.t.method_25402(paramDouble1, paramDouble2, paramInt))
      return true; 
    int i = method_46426();
    int j = method_46427();
    if (a(paramDouble1, paramDouble2, i + 6, j + 244 - 32, 160, 13)) {
      SafariExpeditionNetwork.requestBuilderCycleBooster();
      return true;
    } 
    if (a(paramDouble1, paramDouble2, i + 6, j + 244 - 16, 50, 13)) {
      SafariExpeditionNetwork.requestBuilderClear();
      return true;
    } 
    if (a(paramDouble1, paramDouble2, i + 58, j + 244 - 16, 108, 13)) {
      SafariExpeditionModels.ExpeditionBuilderData expeditionBuilderData = (SafariExpeditionModels.ExpeditionBuilderData)this.s.getBuilderData().get();
      if (expeditionBuilderData != null && expeditionBuilderData.canLaunch())
        SafariExpeditionNetwork.requestBuilderLaunch(); 
      return true;
    } 
    return super.method_25402(paramDouble1, paramDouble2, paramInt);
  }
  
  public ExpeditionPCGUIConfiguration a() {
    return this.s;
  }
  
  public a b() {
    return this.t;
  }
  
  private boolean a(double paramDouble1, double paramDouble2, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    return (paramDouble1 >= paramInt1 && paramDouble1 <= (paramInt1 + paramInt3) && paramDouble2 >= paramInt2 && paramDouble2 <= (paramInt2 + paramInt4));
  }
  
  @NotNull
  private String a(@NotNull SafariExpeditionModels.ExpeditionBuilderData paramExpeditionBuilderData) {
    switch (null.a[paramExpeditionBuilderData.boosterType().ordinal()]) {
      default:
        throw new MatchException(null, null);
      case 1:
      
      case 2:
      
      case 3:
      
      case 4:
        break;
    } 
    return "Bounty · " + paramExpeditionBuilderData.availableBountyBoosters();
  }
  
  private int b(@NotNull SafariExpeditionModels.ExpeditionBuilderData paramExpeditionBuilderData) {
    switch (null.a[paramExpeditionBuilderData.boosterType().ordinal()]) {
      default:
        throw new MatchException(null, null);
      case 1:
      
      case 2:
      
      case 3:
      
      case 4:
        break;
    } 
    return -2049195;
  }
  
  private int a(int paramInt) {
    return (paramInt >= 75) ? -8396670 : ((paramInt >= 50) ? -800918 : -30070);
  }
  
  private String b(@NotNull String paramString, int paramInt) {
    return (paramString == null) ? "" : ((paramString.length() <= paramInt) ? paramString : (paramString.substring(0, Math.max(0, paramInt - 1)) + "…"));
  }
  
  private int a(int paramInt1, int paramInt2) {
    return (paramInt2 & 0xFF) << 24 | paramInt1 & 0xFFFFFF;
  }
  
  private int b(int paramInt) {
    int i = paramInt >>> 24 & 0xFF;
    int j = Math.min(255, (paramInt >> 16 & 0xFF) + 20);
    int k = Math.min(255, (paramInt >> 8 & 0xFF) + 20);
    int m = Math.min(255, (paramInt & 0xFF) + 20);
    return i << 24 | j << 16 | k << 8 | m;
  }
  
  private int c(int paramInt) {
    int i = paramInt >>> 24 & 0xFF;
    int j = Math.max(0, (paramInt >> 16 & 0xFF) - 40);
    int k = Math.max(0, (paramInt >> 8 & 0xFF) - 40);
    int m = Math.max(0, (paramInt & 0xFF) - 40);
    return i << 24 | j << 16 | k << 8 | m;
  }
}


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-client-1.2.0.jar!\com\atlas\client\f\b.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */