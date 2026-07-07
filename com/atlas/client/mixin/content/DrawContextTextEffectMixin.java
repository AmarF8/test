package com.atlas.client.mixin.content;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_2583;
import net.minecraft.class_327;
import net.minecraft.class_4597;
import net.minecraft.class_5224;
import net.minecraft.class_5251;
import net.minecraft.class_5481;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_327.class})
public abstract class DrawContextTextEffectMixin {
  @Unique
  private static boolean atlas$renderingOutline = false;
  
  @Inject(method = {"draw(Lnet/minecraft/text/OrderedText;FFIZLorg/joml/Matrix4f;Lnet/minecraft/client/render/VertexConsumerProvider;Lnet/minecraft/client/font/TextRenderer$TextLayerType;II)I"}, at = {@At("HEAD")}, cancellable = true)
  private void atlas$renderOutlineEffect(class_5481 paramclass_5481, float paramFloat1, float paramFloat2, int paramInt1, boolean paramBoolean, Matrix4f paramMatrix4f, class_4597 paramclass_4597, class_327.class_6415 paramclass_6415, int paramInt2, int paramInt3, CallbackInfoReturnable<Integer> paramCallbackInfoReturnable) {
    if (paramclass_5481 == null || atlas$renderingOutline)
      return; 
    int[] arrayOfInt = { -1 };
    paramclass_5481.accept((paramInt1, paramclass_2583, paramInt2) -> {
          if (paramclass_2583.method_10973() != null) {
            int i = paramclass_2583.method_10973().method_27716();
            if (atlas$getOutlineBorderColor(i) != -1) {
              paramArrayOfint[0] = i;
              return false;
            } 
          } 
          return true;
        });
    if (arrayOfInt[0] == -1)
      return; 
    boolean[] arrayOfBoolean = { false };
    paramclass_5481.accept((paramInt1, paramclass_2583, paramInt2) -> {
          if (paramclass_2583.method_10973() != null && atlas$getOutlineBorderColor(paramclass_2583.method_10973().method_27716()) == -1) {
            paramArrayOfboolean[0] = true;
            return false;
          } 
          return true;
        });
    int i = atlas$getOutlineBorderColor(arrayOfInt[0]);
    int j = atlas$getOutlineTextColor(arrayOfInt[0]);
    if (arrayOfBoolean[0]) {
      atlas$renderingOutline = true;
      try {
        class_327 class_327 = (class_327)this;
        class_327.class_6415 class_64151 = class_327.class_6415.field_33994;
        ArrayList arrayList = new ArrayList();
        ArrayList<class_5481> arrayList1 = new ArrayList();
        float[] arrayOfFloat = { 0.0F };
        paramclass_5481.accept((paramInt1, paramclass_2583, paramInt2) -> {
              class_2583 class_25831 = paramclass_2583;
              int i = paramInt2;
              class_5481 class_5481 = ();
              float f = paramclass_327.method_30880(class_5481);
              if (paramclass_2583.method_10973() != null && atlas$getOutlineBorderColor(paramclass_2583.method_10973().method_27716()) != -1) {
                int j = paramclass_2583.method_10973().method_27716();
                paramList1.add(new float[] { paramArrayOffloat[0], atlas$getOutlineBorderColor(j), atlas$getOutlineTextColor(j) });
                paramList2.add(class_5481);
              } 
              paramArrayOffloat[0] = paramArrayOffloat[0] + f;
              return true;
            });
        class_5481 class_54811 = atlas$replaceOutlineColors(paramclass_5481, j);
        int k = class_327.method_22942(class_54811, paramFloat1, paramFloat2, paramInt1, paramBoolean, paramMatrix4f, paramclass_4597, paramclass_6415, paramInt2, paramInt3);
        for (byte b = 0; b < arrayList1.size(); b++) {
          float f = paramFloat1 + ((float[])arrayList.get(b))[0];
          int m = (int)((float[])arrayList.get(b))[1];
          int n = (int)((float[])arrayList.get(b))[2];
          class_5481 class_54812 = atlas$recolorChar(arrayList1.get(b), m);
          for (byte b1 = -1; b1 <= 1; b1++) {
            for (byte b2 = -1; b2 <= 1; b2++) {
              if (b1 != 0 || b2 != 0)
                class_327.method_22942(class_54812, f + b1, paramFloat2 + b2, paramInt1, false, paramMatrix4f, paramclass_4597, class_64151, 0, paramInt3); 
            } 
          } 
          class_5481 class_54813 = atlas$recolorChar(arrayList1.get(b), n);
          class_327.method_22942(class_54813, f, paramFloat2, paramInt1, false, paramMatrix4f, paramclass_4597, class_64151, 0, paramInt3);
        } 
        paramCallbackInfoReturnable.setReturnValue(Integer.valueOf(k));
      } finally {
        atlas$renderingOutline = false;
      } 
      return;
    } 
    atlas$renderingOutline = true;
    try {
      class_327 class_327 = (class_327)this;
      class_327.class_6415 class_64151 = class_327.class_6415.field_33994;
      class_5481 class_54811 = atlas$replaceOutlineColors(paramclass_5481, i);
      for (byte b = -1; b <= 1; b++) {
        for (byte b1 = -1; b1 <= 1; b1++) {
          if (b != 0 || b1 != 0)
            class_327.method_22942(class_54811, paramFloat1 + b, paramFloat2 + b1, paramInt1, false, paramMatrix4f, paramclass_4597, class_64151, 0, paramInt3); 
        } 
      } 
      class_5481 class_54812 = atlas$replaceOutlineColors(paramclass_5481, j);
      int k = class_327.method_22942(class_54812, paramFloat1, paramFloat2, paramInt1, false, paramMatrix4f, paramclass_4597, class_64151, 0, paramInt3);
      paramCallbackInfoReturnable.setReturnValue(Integer.valueOf(k));
    } finally {
      atlas$renderingOutline = false;
    } 
  }
  
  @Unique
  private static int atlas$getOutlineBorderColor(int paramInt) {
    switch (paramInt) {
      case 15790100:
      
      case 15790180:
      
      case 15790184:
      
      case 15790188:
      
      case 15790192:
      
      case 15790196:
      
      case 15790200:
      
    } 
    return -1;
  }
  
  @Unique
  private static int atlas$getOutlineTextColor(int paramInt) {
    switch (paramInt) {
      case 15790100:
      
    } 
    return 16777215;
  }
  
  @Unique
  private static class_5481 atlas$recolorChar(class_5481 paramclass_5481, int paramInt) {
    class_5251 class_5251 = class_5251.method_27717(paramInt);
    return paramclass_5224 -> paramclass_5481.accept(());
  }
  
  @Unique
  private static class_5481 atlas$replaceOutlineColors(class_5481 paramclass_5481, int paramInt) {
    class_5251 class_5251 = class_5251.method_27717(paramInt);
    return paramclass_5224 -> paramclass_5481.accept(());
  }
}


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-client-1.2.0.jar!\com\atlas\client\mixin\content\DrawContextTextEffectMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */