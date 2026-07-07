package com.atlas.client.mixin.content;

import com.atlas.common.fabric.cosmetic.ArmorSlot;
import com.atlas.common.fabric.cosmetic.EquippedCosmetics;
import com.atlas.common.fabric.cosmetic.EquippedCosmeticsRepository;
import net.minecraft.class_1304;
import net.minecraft.class_1309;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_572;
import net.minecraft.class_970;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_970.class})
public abstract class HideArmorMixin {
  @Inject(method = {"renderArmor"}, at = {@At("HEAD")}, cancellable = true)
  private void atlas$hideArmorPiece(class_4587 paramclass_4587, class_4597 paramclass_4597, class_1309 paramclass_1309, class_1304 paramclass_1304, int paramInt, class_572<?> paramclass_572, CallbackInfo paramCallbackInfo) {
    if (paramclass_1309.method_6118(paramclass_1304).method_7909() instanceof com.atlas.common.fabric.item.armor.PokeArmorItem)
      return; 
    EquippedCosmetics equippedCosmetics = EquippedCosmeticsRepository.INSTANCE.getOrCreate(paramclass_1309.method_5667());
    switch (paramclass_1304) {
      case field_6169:
      
      case field_6174:
      
      case field_6172:
      
      case field_6166:
      
      default:
        break;
    } 
    ArmorSlot armorSlot = null;
    if (armorSlot != null && equippedCosmetics.isDisabled(armorSlot))
      paramCallbackInfo.cancel(); 
  }
}


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-client-1.2.0.jar!\com\atlas\client\mixin\content\HideArmorMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */