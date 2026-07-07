package com.atlas.client.mixin.content;

import net.minecraft.class_1304;
import net.minecraft.class_1309;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_490;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_490.class})
public abstract class InventoryScreenMixin {
  @Inject(method = {"drawEntity(Lnet/minecraft/client/gui/DrawContext;FFFLorg/joml/Vector3f;Lorg/joml/Quaternionf;Lorg/joml/Quaternionf;Lnet/minecraft/entity/LivingEntity;)V"}, at = {@At("TAIL")})
  private static void atlas$flushPokeArmorEntityBuffer(class_332 paramclass_332, float paramFloat1, float paramFloat2, float paramFloat3, Vector3f paramVector3f, Quaternionf paramQuaternionf1, Quaternionf paramQuaternionf2, class_1309 paramclass_1309, CallbackInfo paramCallbackInfo) {
    if (hasPokeArmor(paramclass_1309))
      class_310.method_1551().method_22940().method_23000().method_22993(); 
  }
  
  private static boolean hasPokeArmor(class_1309 paramclass_1309) {
    return (paramclass_1309.method_6118(class_1304.field_6169).method_7909() instanceof com.atlas.common.fabric.item.armor.PokeArmorItem || paramclass_1309.method_6118(class_1304.field_6174).method_7909() instanceof com.atlas.common.fabric.item.armor.PokeArmorItem || paramclass_1309.method_6118(class_1304.field_6172).method_7909() instanceof com.atlas.common.fabric.item.armor.PokeArmorItem || paramclass_1309.method_6118(class_1304.field_6166).method_7909() instanceof com.atlas.common.fabric.item.armor.PokeArmorItem);
  }
}


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-client-1.2.0.jar!\com\atlas\client\mixin\content\InventoryScreenMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */