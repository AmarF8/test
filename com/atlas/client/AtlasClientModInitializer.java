package com.atlas.client;

import com.atlas.client.a.a;
import com.atlas.client.a.b;
import com.atlas.client.c.a;
import com.atlas.client.e.a.a;
import com.atlas.common.fabric.clientverify.ClientVerifyNetwork;
import com.atlas.common.fabric.cosmetic.PlayerModelType;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.class_1007;
import net.minecraft.class_1299;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_3264;
import net.minecraft.class_3300;
import net.minecraft.class_3883;
import net.minecraft.class_3887;
import net.minecraft.class_5617;
import net.minecraft.class_591;
import net.minecraft.class_634;
import net.minecraft.class_8710;
import net.minecraft.class_922;

@Environment(EnvType.CLIENT)
public final class AtlasClientModInitializer implements ClientModInitializer {
  public void onInitializeClient() {
    a();
    LivingEntityFeatureRendererRegistrationCallback.EVENT.register((paramclass_1299, paramclass_922, paramRegistrationHelper, paramclass_5618) -> {
          if (paramclass_922 instanceof class_1007) {
            class_1007 class_1007 = (class_1007)paramclass_922;
            boolean bool = ((class_591)class_1007.method_4038()).getClass().getSimpleName().contains("Slim");
            PlayerModelType playerModelType = bool ? PlayerModelType.ALEX : PlayerModelType.STEVE;
            class_1007.method_4046((class_3887)new a((class_3883)class_1007, playerModelType));
          } 
        });
    ResourceManagerHelper.get(class_3264.field_14188).registerReloadListener((IdentifiableResourceReloadListener)new SimpleSynchronousResourceReloadListener(this) {
          public void method_14491(class_3300 param1class_3300) {
            b.a.reload(param1class_3300);
            a.a.a(param1class_3300);
          }
          
          public class_2960 getFabricId() {
            return class_2960.method_60655("atlas", "resources");
          }
        });
  }
  
  private static void a() {
    a a = AtlasClient.b();
    ClientPlayNetworking.registerGlobalReceiver(ClientVerifyNetwork.ResponsePayload.ID, (paramResponsePayload, paramContext) -> {
          if (paramResponsePayload.isVerified())
            parama.b(); 
        });
    ClientPlayNetworking.registerGlobalReceiver(ClientVerifyNetwork.ChallengePayload.ID, (paramChallengePayload, paramContext) -> {
          String str1 = paramChallengePayload.getNonce();
          String str2 = parama.d();
          String str3 = parama.a(str1, paramChallengePayload.getHmacKey(), paramChallengePayload.isIntegrityEnabled());
          String str4 = parama.e();
          ClientPlayNetworking.send((class_8710)new ClientVerifyNetwork.RequestPayload(str3, str4, str2));
          for (byte b = 1; b <= 2; b++) {
            int i = b * 1500;
            (new Thread(())).start();
          } 
        });
    ClientPlayConnectionEvents.DISCONNECT.register((paramclass_634, paramclass_310) -> parama.c());
  }
}


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-client-1.2.0.jar!\com\atlas\client\AtlasClientModInitializer.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */