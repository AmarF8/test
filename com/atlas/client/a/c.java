package com.atlas.client.a;

import com.cobblemon.mod.common.client.render.models.blockbench.PosableModel;
import com.cobblemon.mod.common.client.render.models.blockbench.PosableState;
import com.cobblemon.mod.common.client.render.models.blockbench.bedrock.animation.BedrockAnimation;
import com.cobblemon.mod.common.client.render.models.blockbench.repository.RenderContext;
import net.minecraft.class_1921;
import net.minecraft.class_2960;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_4597;
import net.minecraft.class_4608;
import net.minecraft.class_630;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class c {
  @NotNull
  public static final c a = new c();
  
  private static final int b = 772302905;
  
  public static int a() {
    return b;
  }
  
  public void a(@NotNull class_2960 paramclass_29601, @NotNull PosableState paramPosableState, @NotNull class_2960 paramclass_29602, @NotNull class_4587 paramclass_4587, @NotNull class_4597 paramclass_4597, int paramInt, float paramFloat, @Nullable class_2960 paramclass_29603, @Nullable Integer paramInteger) {
    class_630 class_630 = b.a.a(paramclass_29601);
    if (class_630 == null)
      return; 
    PosableModel posableModel = b.a.b(paramclass_29601);
    if (posableModel == null)
      return; 
    RenderContext renderContext = new RenderContext();
    posableModel.setContext(renderContext);
    paramclass_4587.method_22903();
    posableModel.setDefault();
    if (paramInteger != null)
      paramPosableState.updateAge(paramInteger.intValue()); 
    BedrockAnimation bedrockAnimation = null;
    if (paramclass_29603 != null)
      bedrockAnimation = a.a.a(paramclass_29603); 
    if (bedrockAnimation != null)
      bedrockAnimation.run(posableModel, paramPosableState, paramPosableState.getAnimationSeconds(), 0.0F, 0.0F, paramFloat, 1.0F); 
    class_1921 class_1921 = class_1921.method_23576(paramclass_29602);
    class_4588 class_4588 = paramclass_4597.getBuffer(class_1921);
    class_630.method_22699(paramclass_4587, class_4588, paramInt, class_4608.field_21444, -1);
    paramclass_4587.method_22909();
  }
  
  public void a(@NotNull class_2960 paramclass_29601, @NotNull PosableState paramPosableState, @NotNull class_2960 paramclass_29602, @NotNull class_4587 paramclass_4587, @NotNull class_4597 paramclass_4597, int paramInt, float paramFloat) {
    a(paramclass_29601, paramPosableState, paramclass_29602, paramclass_4587, paramclass_4597, paramInt, paramFloat, null, null);
  }
}


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-client-1.2.0.jar!\com\atlas\client\a\c.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */