package com.atlas.client.stations;

import com.atlas.common.fabric.block.station.StationPCGUIConfiguration;
import com.atlas.common.fabric.block.station.packet.client.StationOpenPacket;
import com.atlas.common.fabric.block.station.packet.server.StationRemovePokemonPacket;
import com.atlas.common.fabric.packet.AtlasModPacket;
import com.cobblemon.mod.common.CobblemonSounds;
import com.cobblemon.mod.common.api.Priority;
import com.cobblemon.mod.common.api.gui.GuiUtilsKt;
import com.cobblemon.mod.common.api.pokemon.PokemonSpecies;
import com.cobblemon.mod.common.client.CobblemonResources;
import com.cobblemon.mod.common.client.gui.CobblemonRenderable;
import com.cobblemon.mod.common.client.gui.PokemonGuiUtilsKt;
import com.cobblemon.mod.common.client.gui.pasture.PastureSlotIconButton;
import com.cobblemon.mod.common.client.gui.summary.widgets.PartySlotWidget;
import com.cobblemon.mod.common.client.render.RenderHelperKt;
import com.cobblemon.mod.common.client.render.models.blockbench.FloatingState;
import com.cobblemon.mod.common.client.render.models.blockbench.PosableState;
import com.cobblemon.mod.common.entity.PoseType;
import com.cobblemon.mod.common.pokemon.Species;
import com.cobblemon.mod.common.util.LocalizationUtilsKt;
import com.cobblemon.mod.common.util.MiscUtilsKt;
import com.cobblemon.mod.common.util.math.QuaternionUtilsKt;
import java.util.List;
import kotlin.Unit;
import net.minecraft.class_124;
import net.minecraft.class_1799;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_350;
import net.minecraft.class_364;
import net.minecraft.class_4185;
import net.minecraft.class_4280;
import net.minecraft.class_4587;
import net.minecraft.class_5250;
import net.minecraft.class_8710;
import org.jetbrains.annotations.Nullable;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class b extends class_4280<b.a> implements CobblemonRenderable {
  public static final int a = 70;
  
  public static final int b = 120;
  
  public static final int c = 62;
  
  public static final int d = 29;
  
  public static final int e = 3;
  
  public static final float f = 0.5F;
  
  private static final class_2960 g = MiscUtilsKt.cobblemonResource("textures/gui/pasture/pasture_scroll_overlay.png");
  
  private static final class_2960 h = MiscUtilsKt.cobblemonResource("textures/gui/pasture/pasture_slot.png");
  
  private static final class_2960 i = MiscUtilsKt.cobblemonResource("textures/gui/pasture/pasture_slot_owner.png");
  
  private final int j;
  
  private final int k;
  
  private final c l;
  
  private boolean m = false;
  
  public b(int paramInt1, int paramInt2, c paramc) {
    super(class_310.method_1551(), 70, 120, 0, 32);
    this.j = paramInt1;
    this.k = paramInt2;
    this.l = paramc;
    method_46421(paramInt1);
    method_46419(paramInt2);
    a();
    paramc.a().getStationedPokemon().subscribeIncludingCurrent(Priority.NORMAL, paramList -> {
          List list1 = method_25396();
          List list2 = list1.stream().filter(()).toList();
          list2.forEach(this::b);
          paramList.stream().filter(()).forEach(());
          return null;
        });
  }
  
  public int method_25322() {
    return 62;
  }
  
  protected int method_25329() {
    return method_46426() + this.field_22758 - 3;
  }
  
  public int a(a parama) {
    return super.method_25321((class_350.class_351)parama);
  }
  
  public boolean b(a parama) {
    return super.method_25330((class_350.class_351)parama);
  }
  
  protected void method_44398(class_332 paramclass_332, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {}
  
  public void method_48579(class_332 paramclass_332, int paramInt1, int paramInt2, float paramFloat) {
    a();
    paramclass_332.method_51448().method_22903();
    paramclass_332.method_44379(method_46426(), method_46427() + 4, method_46426() + this.field_22758, method_46427() - 1 + this.field_22759);
    paramclass_332.method_51448().method_22909();
    super.method_48579(paramclass_332, paramInt1, paramInt2, paramFloat);
    paramclass_332.method_44380();
    GuiUtilsKt.blitk(paramclass_332.method_51448(), g, Integer.valueOf(this.j), Integer.valueOf(this.k - 13), Integer.valueOf(131), Integer.valueOf(70), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(70), Integer.valueOf(131), Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(1), Float.valueOf(1.0F), true, 1.0F);
    StationPCGUIConfiguration stationPCGUIConfiguration = this.l.a();
    long l = method_25396().stream().filter(a::a).count();
    int i = (stationPCGUIConfiguration.getPermissions().getMaxPokemon() >= 0) ? stationPCGUIConfiguration.getPermissions().getMaxPokemon() : stationPCGUIConfiguration.getLimit();
    class_5250 class_5250 = class_2561.method_43470("" + l + "/" + l).method_27692(class_124.field_1067);
    RenderHelperKt.drawScaledText(paramclass_332, CobblemonResources.INSTANCE.getDEFAULT_LARGE(), class_5250.method_27661(), Integer.valueOf(method_46426() + 35), Integer.valueOf(method_46427() - 7), 1.0F, Float.valueOf(1.0F), 2147483647, 16777215, true, false, null, null);
  }
  
  public boolean method_25402(double paramDouble1, double paramDouble2, int paramInt) {
    b(paramDouble1, paramDouble2);
    if (this.m) {
      method_25395((class_364)method_25308(paramDouble1, paramDouble2));
      method_25398(true);
    } 
    return super.method_25402(paramDouble1, paramDouble2, paramInt);
  }
  
  public boolean method_25403(double paramDouble1, double paramDouble2, int paramInt, double paramDouble3, double paramDouble4) {
    if (this.m && method_57717() && paramDouble1 >= method_25329())
      if (paramDouble2 < this.k) {
        method_25307(0.0D);
      } else if (paramDouble2 > method_55443()) {
        method_25307(method_25331());
      } else {
        method_25307(method_25341() + paramDouble4);
      }  
    return super.method_25403(paramDouble1, paramDouble2, paramInt, paramDouble3, paramDouble4);
  }
  
  private void b(double paramDouble1, double paramDouble2) {
    this.m = (paramDouble1 >= method_25329() && paramDouble1 < (method_25329() + 3) && paramDouble2 >= this.k && paramDouble2 < method_55443());
  }
  
  protected void method_57715(class_332 paramclass_332) {}
  
  private void a() {
    method_55444(70, 120, this.j, this.k - 4);
  }
  
  public boolean a(double paramDouble1, double paramDouble2) {
    return (paramDouble1 >= method_46426() && paramDouble1 <= (method_46426() + 70) && paramDouble2 >= method_46427() && paramDouble2 <= (method_46427() + 120));
  }
  
  public static class a extends class_4280.class_4281<a> {
    private final StationOpenPacket.StationPokemonDataDTO a;
    
    private final c b;
    
    private final class_310 c = class_310.method_1551();
    
    private final FloatingState d = new FloatingState();
    
    private final PastureSlotIconButton e;
    
    public a(StationOpenPacket.StationPokemonDataDTO param1StationPokemonDataDTO, c param1c) {
      this.a = param1StationPokemonDataDTO;
      this.b = param1c;
      this.e = new PastureSlotIconButton(0, 0, param1class_4185 -> {
            param1c.b().getPcGui().playSound(CobblemonSounds.PC_CLICK);
            AtlasModPacket.sendToServer((class_8710)new StationRemovePokemonPacket(param1c.a().getStationId(), param1StationPokemonDataDTO.pokemonId()));
          });
    }
    
    public boolean a() {
      return (this.c.field_1724 != null && this.c.field_1724.method_5667().equals(this.a.playerId()));
    }
    
    public boolean b() {
      return (a() || this.b.a().getPermissions().getCanUnstationOthers());
    }
    
    public Boolean c() {
      Species species = PokemonSpecies.getByIdentifier(this.a.species());
      return Boolean.valueOf((species != null && species.getBehaviour().getCombat().getWillDefendOwner()));
    }
    
    public void method_25343(class_332 param1class_332, int param1Int1, int param1Int2, int param1Int3, int param1Int4, int param1Int5, int param1Int6, int param1Int7, boolean param1Boolean, float param1Float) {
      int i = param1Int3 - 4;
      int j = param1Int2 + 2;
      class_4587 class_4587 = param1class_332.method_51448();
      GuiUtilsKt.blitk(class_4587, a() ? b.i : b.h, Integer.valueOf(i), Integer.valueOf(j), Integer.valueOf(29), Integer.valueOf(param1Int4), Integer.valueOf(0), Integer.valueOf(param1Boolean ? 29 : 0), Integer.valueOf(param1Int4), Integer.valueOf(58), Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(1), Float.valueOf(1.0F), true, 1.0F);
      class_4587.method_22903();
      class_4587.method_22904((i + 11) + 12.5D, j - 1.0D, 0.0D);
      class_4587.method_22905(2.5F, 2.5F, 1.0F);
      this.d.setCurrentAspects(this.a.aspects());
      PokemonGuiUtilsKt.drawProfilePokemon(this.a.species(), class_4587, QuaternionUtilsKt.fromEulerXYZDegrees(new Quaternionf(), new Vector3f(13.0F, 35.0F, 0.0F)), PoseType.PROFILE, (PosableState)this.d, param1Float, 4.5F, true, false, false, 1.0F, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F);
      class_4587.method_22909();
      class_1799 class_1799 = this.a.heldItem();
      if (!class_1799.method_7960())
        RenderHelperKt.renderScaledGuiItemIcon(class_1799, i + 23.5D, j + 13.0D, 0.5D, 100.0F, class_4587); 
      RenderHelperKt.drawScaledTextJustifiedRight(param1class_332, null, LocalizationUtilsKt.lang("ui.lv.number", new Object[] { Integer.valueOf(this.a.level()) }), Integer.valueOf(i + 59), Integer.valueOf(j + 17), 0.5F, Float.valueOf(1.0F), 2147483647, 16777215, true);
      class_2561 class_2561 = (class_2561)((param1Boolean && this.a.ownerName() != null) ? class_2561.method_43470(this.a.ownerName()).method_27692(class_124.field_1056) : this.a.displayName());
      RenderHelperKt.drawScaledText(param1class_332, null, class_2561.method_27661(), Integer.valueOf(i + 11), Integer.valueOf(j + 24), 0.5F, Float.valueOf(1.0F), 2147483647, 16777215, false, false, null, null);
      if (this.a.aspects().contains("male") || this.a.aspects().contains("female"))
        GuiUtilsKt.blitk(class_4587, this.a.aspects().contains("male") ? PartySlotWidget.Companion.getGenderIconMale() : PartySlotWidget.Companion.getGenderIconFemale(), Float.valueOf((float)((i + 56.5D) / 0.5D)), Float.valueOf((j + 24) / 0.5F), Integer.valueOf(7), Integer.valueOf(5), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(5), Integer.valueOf(7), Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(1), Float.valueOf(1.0F), true, 0.5F); 
      if (b()) {
        this.e.setPos(i + 2, j + 11);
        this.e.method_25394(param1class_332, param1Int6, param1Int7, param1Float);
      } 
    }
    
    public boolean method_25402(double param1Double1, double param1Double2, int param1Int) {
      if (b() && this.e.isHovered(param1Double1, param1Double2)) {
        this.e.method_25306();
        return true;
      } 
      return false;
    }
    
    public class_2561 method_37006() {
      return this.a.displayName();
    }
  }
}


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-client-1.2.0.jar!\com\atlas\client\stations\b.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */