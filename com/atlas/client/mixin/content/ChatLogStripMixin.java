package com.atlas.client.mixin.content;

import com.mojang.logging.LogUtils;
import net.minecraft.class_2561;
import net.minecraft.class_303;
import net.minecraft.class_338;
import net.minecraft.class_7469;
import net.minecraft.class_7591;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_338.class})
public abstract class ChatLogStripMixin {
  private static final Logger ATLAS_LOGGER = LogUtils.getLogger();
  
  private static final String BAIT_MARKER = "​‌​‌";
  
  @Inject(method = {"addMessage(Lnet/minecraft/text/Text;Lnet/minecraft/network/message/MessageSignatureData;Lnet/minecraft/client/gui/hud/MessageIndicator;)V"}, at = {@At("HEAD")}, cancellable = true)
  private void atlas$baitLogOnly(class_2561 paramclass_2561, class_7469 paramclass_7469, class_7591 paramclass_7591, CallbackInfo paramCallbackInfo) {
    String str1 = paramclass_2561.getString();
    if (!str1.contains("​‌​‌"))
      return; 
    String str2 = str1.replaceAll("\r", "\\\\r").replaceAll("\n", "\\\\n");
    ATLAS_LOGGER.info("[CHAT] {}", str2);
    paramCallbackInfo.cancel();
  }
  
  @Inject(method = {"logChatMessage"}, at = {@At("HEAD")}, cancellable = true)
  private void atlas$stripBotTriggers(class_303 paramclass_303, CallbackInfo paramCallbackInfo) {
    String str = paramclass_303.comp_893().getString().toLowerCase();
    if (str.contains("welcome") || str.contains("gg") || str.contains("congrat") || (str.contains("joined") && str.contains("first")) || str.contains("karma point") || str.contains("clan chat"))
      paramCallbackInfo.cancel(); 
  }
}


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-client-1.2.0.jar!\com\atlas\client\mixin\content\ChatLogStripMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */