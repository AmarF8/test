package com.atlas.client.stations;

import com.atlas.common.fabric.block.station.packet.client.StationOpenPacket;
import com.atlas.common.fabric.block.station.packet.server.StationRemovePokemonPacket;
import com.atlas.common.fabric.packet.AtlasModPacket;
import com.cobblemon.mod.common.CobblemonSounds;
import com.cobblemon.mod.common.api.gui.GuiUtilsKt;
import com.cobblemon.mod.common.api.pokemon.PokemonSpecies;
import com.cobblemon.mod.common.client.gui.PokemonGuiUtilsKt;
import com.cobblemon.mod.common.client.gui.pasture.PastureSlotIconButton;
import com.cobblemon.mod.common.client.gui.summary.widgets.PartySlotWidget;
import com.cobblemon.mod.common.client.render.RenderHelperKt;
import com.cobblemon.mod.common.client.render.models.blockbench.FloatingState;
import com.cobblemon.mod.common.client.render.models.blockbench.PosableState;
import com.cobblemon.mod.common.entity.PoseType;
import com.cobblemon.mod.common.pokemon.Species;
import com.cobblemon.mod.common.util.LocalizationUtilsKt;
import com.cobblemon.mod.common.util.math.QuaternionUtilsKt;
import net.minecraft.class_124;
import net.minecraft.class_1799;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_4185;
import net.minecraft.class_4280;
import net.minecraft.class_4587;
import net.minecraft.class_8710;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class a extends class_4280.class_4281<b.a> {
  private final StationOpenPacket.StationPokemonDataDTO a;
  
  private final c b;
  
  private final class_310 c = class_310.method_1551();
  
  private final FloatingState d = new FloatingState();
  
  private final PastureSlotIconButton e;
  
  public a(StationOpenPacket.StationPokemonDataDTO paramStationPokemonDataDTO, c paramc) {
    this.a = paramStationPokemonDataDTO;
    this.b = paramc;
    this.e = new PastureSlotIconButton(0, 0, paramclass_4185 -> {
          paramc.b().getPcGui().playSound(CobblemonSounds.PC_CLICK);
          AtlasModPacket.sendToServer((class_8710)new StationRemovePokemonPacket(paramc.a().getStationId(), paramStationPokemonDataDTO.pokemonId()));
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
  
  public void method_25343(class_332 paramclass_332, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, boolean paramBoolean, float paramFloat) {
    int i = paramInt3 - 4;
    int j = paramInt2 + 2;
    class_4587 class_4587 = paramclass_332.method_51448();
    GuiUtilsKt.blitk(class_4587, a() ? b.i : b.h, Integer.valueOf(i), Integer.valueOf(j), Integer.valueOf(29), Integer.valueOf(paramInt4), Integer.valueOf(0), Integer.valueOf(paramBoolean ? 29 : 0), Integer.valueOf(paramInt4), Integer.valueOf(58), Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(1), Float.valueOf(1.0F), true, 1.0F);
    class_4587.method_22903();
    class_4587.method_22904((i + 11) + 12.5D, j - 1.0D, 0.0D);
    class_4587.method_22905(2.5F, 2.5F, 1.0F);
    this.d.setCurrentAspects(this.a.aspects());
    PokemonGuiUtilsKt.drawProfilePokemon(this.a.species(), class_4587, QuaternionUtilsKt.fromEulerXYZDegrees(new Quaternionf(), new Vector3f(13.0F, 35.0F, 0.0F)), PoseType.PROFILE, (PosableState)this.d, paramFloat, 4.5F, true, false, false, 1.0F, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F);
    class_4587.method_22909();
    class_1799 class_1799 = this.a.heldItem();
    if (!class_1799.method_7960())
      RenderHelperKt.renderScaledGuiItemIcon(class_1799, i + 23.5D, j + 13.0D, 0.5D, 100.0F, class_4587); 
    RenderHelperKt.drawScaledTextJustifiedRight(paramclass_332, null, LocalizationUtilsKt.lang("ui.lv.number", new Object[] { Integer.valueOf(this.a.level()) }), Integer.valueOf(i + 59), Integer.valueOf(j + 17), 0.5F, Float.valueOf(1.0F), 2147483647, 16777215, true);
    class_2561 class_2561 = (class_2561)((paramBoolean && this.a.ownerName() != null) ? class_2561.method_43470(this.a.ownerName()).method_27692(class_124.field_1056) : this.a.displayName());
    RenderHelperKt.drawScaledText(paramclass_332, null, class_2561.method_27661(), Integer.valueOf(i + 11), Integer.valueOf(j + 24), 0.5F, Float.valueOf(1.0F), 2147483647, 16777215, false, false, null, null);
    if (this.a.aspects().contains("male") || this.a.aspects().contains("female"))
      GuiUtilsKt.blitk(class_4587, this.a.aspects().contains("male") ? PartySlotWidget.Companion.getGenderIconMale() : PartySlotWidget.Companion.getGenderIconFemale(), Float.valueOf((float)((i + 56.5D) / 0.5D)), Float.valueOf((j + 24) / 0.5F), Integer.valueOf(7), Integer.valueOf(5), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(5), Integer.valueOf(7), Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(1), Float.valueOf(1.0F), true, 0.5F); 
    if (b()) {
      this.e.setPos(i + 2, j + 11);
      this.e.method_25394(paramclass_332, paramInt6, paramInt7, paramFloat);
    } 
  }
  
  public boolean method_25402(double paramDouble1, double paramDouble2, int paramInt) {
    if (b() && this.e.isHovered(paramDouble1, paramDouble2)) {
      this.e.method_25306();
      return true;
    } 
    return false;
  }
  
  public class_2561 method_37006() {
    return this.a.displayName();
  }
}


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-client-1.2.0.jar!\com\atlas\client\stations\b$a.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */