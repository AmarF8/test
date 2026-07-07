package com.atlas.client.mixin.content;

import com.atlas.common.fabric.battle.ui.BattleMessageFormatter;
import com.cobblemon.mod.common.client.net.battle.BattleMessageHandler;
import com.cobblemon.mod.common.net.messages.client.battle.BattleMessagePacket;
import net.minecraft.class_310;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = {BattleMessageHandler.class}, remap = false)
public final class BattleMessageHandlerMixin {
  @Inject(method = {"handle(Lcom/cobblemon/mod/common/net/messages/client/battle/BattleMessagePacket;Lnet/minecraft/client/MinecraftClient;)V"}, at = {@At("HEAD")}, cancellable = true, remap = true)
  private void atlas$formatBattleMessages(BattleMessagePacket paramBattleMessagePacket, class_310 paramclass_310, CallbackInfo paramCallbackInfo) {
    if (BattleMessageFormatter.handle(paramBattleMessagePacket, paramclass_310))
      paramCallbackInfo.cancel(); 
  }
}


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-client-1.2.0.jar!\com\atlas\client\mixin\content\BattleMessageHandlerMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */