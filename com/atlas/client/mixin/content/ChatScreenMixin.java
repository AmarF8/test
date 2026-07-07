package com.atlas.client.mixin.content;

import com.atlas.client.b.a;
import com.atlas.common.fabric.emoji.data.EmojiData;
import net.minecraft.class_332;
import net.minecraft.class_342;
import net.minecraft.class_408;
import net.minecraft.class_437;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_408.class})
public abstract class ChatScreenMixin extends class_437 {
  @Shadow
  protected class_342 field_2382;
  
  @Unique
  private a atlas$emojiWidget;
  
  protected ChatScreenMixin() {
    super(null);
  }
  
  @Inject(method = {"init"}, at = {@At("TAIL")})
  private void atlas$init(CallbackInfo paramCallbackInfo) {
    this.atlas$emojiWidget = new a();
    this.atlas$emojiWidget.a(this::atlas$tabCompleteEmoji);
  }
  
  @Inject(method = {"render"}, at = {@At("TAIL")})
  private void atlas$renderEmojiOverlay(class_332 paramclass_332, int paramInt1, int paramInt2, float paramFloat, CallbackInfo paramCallbackInfo) {
    if (this.atlas$emojiWidget == null)
      return; 
    int i = this.field_22790 - 14;
    this.atlas$emojiWidget.a(paramclass_332, this.field_22793, this.field_2382.method_1882(), i);
    this.atlas$emojiWidget.a(paramclass_332, this.field_22793, i);
  }
  
  @Inject(method = {"keyPressed"}, at = {@At("HEAD")}, cancellable = true)
  private void atlas$onKeyPressed(int paramInt1, int paramInt2, int paramInt3, CallbackInfoReturnable<Boolean> paramCallbackInfoReturnable) {
    if (this.atlas$emojiWidget == null)
      return; 
    if (paramInt1 == 257 && this.atlas$emojiWidget.a()) {
      EmojiData.EmojiEntry emojiEntry = this.atlas$emojiWidget.e();
      if (emojiEntry != null) {
        atlas$finalCompleteEmoji(emojiEntry);
        paramCallbackInfoReturnable.setReturnValue(Boolean.valueOf(true));
        return;
      } 
    } 
    if (this.atlas$emojiWidget.b()) {
      int i = paramInt1;
      if (i != 258 && i != 257 && i != 256 && i != 265 && i != 264)
        this.atlas$emojiWidget.c(); 
    } 
    if (this.atlas$emojiWidget.a(paramInt1))
      paramCallbackInfoReturnable.setReturnValue(Boolean.valueOf(true)); 
  }
  
  @Inject(method = {"keyPressed"}, at = {@At("TAIL")})
  private void atlas$afterKeyPressed(int paramInt1, int paramInt2, int paramInt3, CallbackInfoReturnable<Boolean> paramCallbackInfoReturnable) {
    if (this.atlas$emojiWidget == null)
      return; 
    this.atlas$emojiWidget.a(this.field_2382.method_1882(), this.field_2382.method_1881());
  }
  
  @Inject(method = {"onChatFieldUpdate"}, at = {@At("TAIL")})
  private void atlas$onChatFieldUpdate(String paramString, CallbackInfo paramCallbackInfo) {
    if (this.atlas$emojiWidget == null)
      return; 
    this.atlas$emojiWidget.a(this.field_2382.method_1882(), this.field_2382.method_1881());
  }
  
  @Unique
  private void atlas$tabCompleteEmoji(int paramInt, String paramString) {
    String str1 = this.field_2382.method_1882();
    int i = Math.min(this.field_2382.method_1881(), str1.length());
    if (paramInt < 0) {
      paramInt = str1.lastIndexOf(':', i - 1);
      if (paramInt < 0)
        return; 
      this.atlas$emojiWidget.b(paramInt);
    } 
    paramInt = Math.min(paramInt, str1.length());
    int j = i;
    if (j < str1.length() && str1.charAt(j) == ':')
      j++; 
    String str2 = ":" + paramString + ":";
    String str3 = str1.substring(0, paramInt) + str1.substring(0, paramInt) + str2;
    this.field_2382.method_1852(str3);
    this.field_2382.method_1883(paramInt + str2.length(), false);
  }
  
  @Unique
  private void atlas$finalCompleteEmoji(EmojiData.EmojiEntry paramEmojiEntry) {
    int j;
    String str1 = this.field_2382.method_1882();
    int i = Math.min(this.field_2382.method_1881(), str1.length());
    if (this.atlas$emojiWidget.b()) {
      j = Math.min(this.atlas$emojiWidget.d(), str1.length());
    } else {
      j = str1.lastIndexOf(':', i - 1);
    } 
    if (j < 0)
      return; 
    int k = i;
    if (k < str1.length() && str1.charAt(k) == ':')
      k++; 
    String str2 = ":" + paramEmojiEntry.name() + ": ";
    String str3 = str1.substring(0, j) + str1.substring(0, j) + str2;
    this.field_2382.method_1852(str3);
    this.field_2382.method_1883(j + str2.length(), false);
    this.atlas$emojiWidget.c();
  }
}


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-client-1.2.0.jar!\com\atlas\client\mixin\content\ChatScreenMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */