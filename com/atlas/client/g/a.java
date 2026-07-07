package com.atlas.client.g;

import com.atlas.common.fabric.packet.AtlasModPacket;
import com.atlas.common.fabric.pc.packet.BulkSealPokemonPacket;
import com.atlas.common.fabric.pc.packet.SealPokemonPacket;
import com.cobblemon.mod.common.api.storage.StorePosition;
import com.cobblemon.mod.common.api.storage.pc.PCPosition;
import com.cobblemon.mod.common.api.text.TextKt;
import com.cobblemon.mod.common.client.CobblemonResources;
import com.cobblemon.mod.common.client.gui.pc.StorageWidget;
import com.cobblemon.mod.common.client.render.RenderHelperKt;
import com.cobblemon.mod.common.pokemon.Pokemon;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_332;
import net.minecraft.class_4185;
import net.minecraft.class_8710;

public final class a extends class_4185 {
  private static final int a = 58;
  
  private static final int b = 16;
  
  private static final class_2960 c = class_2960.method_60655("cobblemon", "textures/gui/pc/pc_release_button.png");
  
  private final StorageWidget d;
  
  public a(int paramInt1, int paramInt2, StorageWidget paramStorageWidget) {
    super(paramInt1, paramInt2, 58, 16, (class_2561)class_2561.method_43470("Seal"), paramclass_4185 -> {
        
        }field_40754);
    this.d = paramStorageWidget;
  }
  
  public boolean method_25402(double paramDouble1, double paramDouble2, int paramInt) {
    if (!a() || !a(paramDouble1, paramDouble2))
      return false; 
    if (c()) {
      b();
      return true;
    } 
    PCPosition pCPosition = (PCPosition)this.d.getSelectedPosition();
    Pokemon pokemon = this.d.getPcGui().getPc().get(pCPosition);
    if (pokemon == null) {
      this.d.resetSelected();
      return false;
    } 
    AtlasModPacket.sendToServer((class_8710)new SealPokemonPacket(pokemon.getUuid(), pCPosition.getBox(), pCPosition.getSlot()));
    this.d.getPcGui().getPc().set(pCPosition, null);
    this.d.resetSelected();
    return true;
  }
  
  protected void method_48579(class_332 paramclass_332, int paramInt1, int paramInt2, float paramFloat) {
    if (!a())
      return; 
    boolean bool = a(paramInt1, paramInt2) ? true : false;
    paramclass_332.method_25293(c, method_46426(), method_46427(), 58, 16, 0.0F, bool, 58, 16, 58, 32);
    RenderHelperKt.drawScaledText(paramclass_332, CobblemonResources.INSTANCE.getDEFAULT_LARGE(), TextKt.bold(class_2561.method_43470("Seal")), Integer.valueOf(method_46426() + 29), Double.valueOf(method_46427() + 3.5D), 1.0F, Float.valueOf(1.0F), 2147483647, 16777215, true, true, null, null);
  }
  
  private boolean a() {
    if (c())
      return !d().isEmpty(); 
    StorePosition storePosition = this.d.getSelectedPosition();
    if (storePosition instanceof PCPosition) {
      PCPosition pCPosition = (PCPosition)storePosition;
      if (this.d.getGrabbedSlot() != null && this.d.getGrabbedSlot().getPokemon() != null) {
        Pokemon pokemon = this.d.getPcGui().getPc().get(pCPosition);
        return (pokemon != null && pokemon.getUuid().equals(this.d.getGrabbedSlot().getPokemon().getUuid()));
      } 
    } 
    return false;
  }
  
  private void b() {
    List<PCPosition> list = e();
    ArrayList<BulkSealPokemonPacket.Entry> arrayList = new ArrayList(list.size());
    for (PCPosition pCPosition : list) {
      Pokemon pokemon = this.d.getPcGui().getPc().get(pCPosition);
      if (pokemon == null)
        continue; 
      arrayList.add(new BulkSealPokemonPacket.Entry(pokemon.getUuid(), pCPosition.getBox(), pCPosition.getSlot()));
    } 
    if (!arrayList.isEmpty())
      AtlasModPacket.sendToServer((class_8710)new BulkSealPokemonPacket(arrayList)); 
    f();
    this.d.setDisplayConfirmRelease(false);
    for (PCPosition pCPosition : list)
      this.d.getPcGui().getPc().set(pCPosition, null); 
    this.d.resetSelected();
  }
  
  private boolean c() {
    Object object = a("moreCobblemonTweaks$isMultiSelecting");
    if (object instanceof Boolean) {
      Boolean bool = (Boolean)object;
      if (bool.booleanValue());
    } 
    return false;
  }
  
  private List<Pokemon> d() {
    List list;
    Object object = a("moreCobblemonTweaks$getSelectedPokemon");
    if (object instanceof List) {
      list = (List)object;
    } else {
      return List.of();
    } 
    ArrayList<Pokemon> arrayList = new ArrayList(list.size());
    for (Pokemon pokemon : list) {
      if (pokemon instanceof Pokemon) {
        Pokemon pokemon1 = pokemon;
        if (this.d.getPcGui().getPc().getPosition(pokemon1) != null)
          arrayList.add(pokemon1); 
      } 
    } 
    return arrayList;
  }
  
  private List<PCPosition> e() {
    Object object = a("moreCobblemonTweaks$getSelectedPositions");
    if (object instanceof List) {
      List list1 = (List)object;
      ArrayList<PCPosition> arrayList1 = new ArrayList(list1.size());
      for (PCPosition pCPosition : list1) {
        if (pCPosition instanceof PCPosition) {
          PCPosition pCPosition1 = pCPosition;
          if (this.d.getPcGui().getPc().get(pCPosition1) != null)
            arrayList1.add(pCPosition1); 
        } 
      } 
      return arrayList1;
    } 
    List<Pokemon> list = d();
    ArrayList<PCPosition> arrayList = new ArrayList(list.size());
    for (Pokemon pokemon : list) {
      PCPosition pCPosition = this.d.getPcGui().getPc().getPosition(pokemon);
      if (pCPosition != null)
        arrayList.add(pCPosition); 
    } 
    return arrayList;
  }
  
  private void f() {
    a("moreCobblemonTweaks$clearMultiSelection");
  }
  
  private Object a(String paramString) {
    try {
      Method method = this.d.getClass().getMethod(paramString, new Class[0]);
      return method.invoke(this.d, new Object[0]);
    } catch (ReflectiveOperationException reflectiveOperationException) {
      return null;
    } 
  }
  
  private boolean a(double paramDouble1, double paramDouble2) {
    return (paramDouble1 >= method_46426() && paramDouble1 <= (method_46426() + 58) && paramDouble2 >= method_46427() && paramDouble2 <= (method_46427() + 16));
  }
}


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-client-1.2.0.jar!\com\atlas\client\g\a.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */