package com.atlas.client.e.a;

import com.atlas.client.a.c;
import com.atlas.common.fabric.cosmetic.BedrockCosmeticModel;
import com.atlas.common.fabric.cosmetic.CosmeticCategory;
import com.atlas.common.fabric.cosmetic.CosmeticPreviewProvider;
import com.atlas.common.fabric.cosmetic.CosmeticType;
import com.atlas.common.fabric.cosmetic.CustomModelDataCosmeticModel;
import com.atlas.common.fabric.cosmetic.EquippedCosmetic;
import com.atlas.common.fabric.cosmetic.EquippedCosmeticsRepository;
import com.atlas.common.fabric.cosmetic.PlayerModelType;
import com.atlas.common.fabric.utility.Either;
import com.cobblemon.mod.common.client.render.models.blockbench.PosableState;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1297;
import net.minecraft.class_1306;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_310;
import net.minecraft.class_3883;
import net.minecraft.class_3887;
import net.minecraft.class_437;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_4608;
import net.minecraft.class_591;
import net.minecraft.class_742;
import net.minecraft.class_7833;
import net.minecraft.class_811;
import net.minecraft.class_976;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public class a extends class_3887<class_742, class_591<class_742>> {
  private static final int a = 889742637;
  
  @NotNull
  private final PlayerModelType b;
  
  public static int a() {
    return a;
  }
  
  public a(@NotNull class_3883<class_742, class_591<class_742>> paramclass_3883, @NotNull PlayerModelType paramPlayerModelType) {
    super(paramclass_3883);
    this.b = paramPlayerModelType;
  }
  
  @NotNull
  public final PlayerModelType b() {
    return this.b;
  }
  
  public void a(@NotNull class_4587 paramclass_4587, @NotNull class_4597 paramclass_4597, int paramInt, @NotNull class_742 paramclass_742, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6) {
    class_591<class_742> class_591 = (class_591)method_17165();
    a a1 = new a(paramclass_4587, paramclass_4597, paramInt, (class_1657)paramclass_742, paramFloat4, paramFloat3, class_591);
    Map<CosmeticCategory, EquippedCosmetic> map = a((class_1657)paramclass_742);
    for (CosmeticCategory cosmeticCategory : CosmeticCategory.values()) {
      EquippedCosmetic equippedCosmetic;
      if (map != null && map.containsKey(cosmeticCategory)) {
        equippedCosmetic = map.get(cosmeticCategory);
      } else {
        equippedCosmetic = EquippedCosmeticsRepository.getCosmetic(paramclass_742.method_5667(), cosmeticCategory);
      } 
      if (equippedCosmetic != null)
        a(equippedCosmetic, a1); 
    } 
  }
  
  private void a(EquippedCosmetic paramEquippedCosmetic, a parama) {
    List list;
    if (paramEquippedCosmetic.getVariant() != null && paramEquippedCosmetic.getVariant().getModels() != null) {
      list = paramEquippedCosmetic.getVariant().getModels();
    } else {
      list = paramEquippedCosmetic.getCosmetic().getModels();
    } 
    for (Either<BedrockCosmeticModel, CustomModelDataCosmeticModel> either : (Iterable<Either<BedrockCosmeticModel, CustomModelDataCosmeticModel>>)list) {
      CosmeticType cosmeticType = (CosmeticType)either.fold(BedrockCosmeticModel::getType, CustomModelDataCosmeticModel::getType);
      switch (null.a[cosmeticType.ordinal()]) {
        case 1:
          a(parama, either);
        case 2:
          a(parama, either, class_1306.field_6182);
        case 3:
          a(parama, either, class_1306.field_6183);
        case 4:
          b(parama, either);
      } 
    } 
  }
  
  @Nullable
  private Map<CosmeticCategory, EquippedCosmetic> a(class_1657 paramclass_1657) {
    class_310 class_310 = class_310.method_1551();
    if (!paramclass_1657.equals(class_310.field_1724))
      return null; 
    class_437 class_437 = class_310.field_1755;
    if (class_437 instanceof CosmeticPreviewProvider) {
      CosmeticPreviewProvider cosmeticPreviewProvider = (CosmeticPreviewProvider)class_437;
      return cosmeticPreviewProvider.getPreviewedCosmetics();
    } 
    return null;
  }
  
  private void a(a parama, Either<BedrockCosmeticModel, CustomModelDataCosmeticModel> paramEither) {
    Consumer consumer1 = paramclass_4587 -> ((class_591)method_17165()).field_3398.method_22703(paramclass_4587);
    Consumer consumer2 = paramclass_4587 -> {
        ((class_591)method_17165()).field_3398.method_22703(paramclass_4587);
        class_976.method_32798(paramclass_4587, false);
      };
    paramEither.fold(paramBedrockCosmeticModel -> {
          a(parama, paramBedrockCosmeticModel, paramConsumer);
          return null;
        }paramCustomModelDataCosmeticModel -> {
          a(parama, paramCustomModelDataCosmeticModel, paramConsumer);
          return null;
        });
  }
  
  private void a(a parama, Either<BedrockCosmeticModel, CustomModelDataCosmeticModel> paramEither, class_1306 paramclass_1306) {
    Consumer consumer1 = paramclass_4587 -> {
        ((class_591)method_17165()).method_2803(paramclass_1306, paramclass_4587);
        float f1 = (paramclass_1306 == class_1306.field_6182) ? 90.0F : -90.0F;
        float f2 = (paramclass_1306 == class_1306.field_6182) ? 0.45F : -0.45F;
        paramclass_4587.method_22907(class_7833.field_40718.rotationDegrees(f1));
        paramclass_4587.method_46416(f2, -0.27F, 0.0F);
      };
    Consumer consumer2 = paramclass_4587 -> {
        ((class_591)method_17165()).method_2803(paramclass_1306, paramclass_4587);
        float f1 = (paramclass_1306 == class_1306.field_6182) ? -90.0F : 90.0F;
        float f2 = (paramclass_1306 == class_1306.field_6182) ? 180.0F : -180.0F;
        paramclass_4587.method_22907(class_7833.field_40714.rotationDegrees(f1));
        paramclass_4587.method_22907(class_7833.field_40716.rotationDegrees(f2));
        paramclass_4587.method_46416(0.0F, 0.125F, -0.625F);
      };
    paramEither.fold(paramBedrockCosmeticModel -> {
          a(parama, paramBedrockCosmeticModel, paramConsumer);
          return null;
        }paramCustomModelDataCosmeticModel -> {
          a(parama, paramCustomModelDataCosmeticModel, paramConsumer);
          return null;
        });
  }
  
  private void b(a parama, Either<BedrockCosmeticModel, CustomModelDataCosmeticModel> paramEither) {
    Consumer consumer1 = paramclass_4587 -> ((class_591)method_17165()).field_3391.method_22703(paramclass_4587);
    Consumer consumer2 = paramclass_4587 -> {
        ((class_591)method_17165()).field_3391.method_22703(paramclass_4587);
        class_976.method_32798(paramclass_4587, false);
      };
    paramEither.fold(paramBedrockCosmeticModel -> {
          a(parama, paramBedrockCosmeticModel, paramConsumer);
          return null;
        }paramCustomModelDataCosmeticModel -> {
          a(parama, paramCustomModelDataCosmeticModel, paramConsumer);
          return null;
        });
  }
  
  private void a(a parama, BedrockCosmeticModel paramBedrockCosmeticModel, Consumer<class_4587> paramConsumer) {
    if (paramBedrockCosmeticModel.getPlayerModel() == PlayerModelType.BOTH || paramBedrockCosmeticModel.getPlayerModel() == this.b) {
      parama.a.method_22903();
      paramConsumer.accept(parama.a);
      c.a.a(paramBedrockCosmeticModel.getModel(), (PosableState)paramBedrockCosmeticModel, paramBedrockCosmeticModel.getTexture(), parama.a, parama.b, parama.c, parama.e, paramBedrockCosmeticModel.getAnimation(), Integer.valueOf(parama.d.field_6012));
      parama.a.method_22909();
    } 
  }
  
  private void a(a parama, CustomModelDataCosmeticModel paramCustomModelDataCosmeticModel, Consumer<class_4587> paramConsumer) {
    class_811 class_811 = (paramCustomModelDataCosmeticModel.getType() == CosmeticType.LEFT_ARM) ? class_811.field_4323 : class_811.field_4316;
    parama.a.method_22903();
    paramConsumer.accept(parama.a);
    class_310.method_1551().method_1480().method_23177((class_1309)parama.d, (this.b == PlayerModelType.STEVE) ? paramCustomModelDataCosmeticModel.getSteveStack() : paramCustomModelDataCosmeticModel.getAlexStack(), class_811, false, parama.a, parama.b, parama.d.method_37908(), parama.c, class_4608.field_21444, parama.d.method_5628() + class_811.ordinal());
    parama.a.method_22909();
  }
  
  public static class a {
    public final class_4587 a;
    
    public final class_4597 b;
    
    public final int c;
    
    public final class_1657 d;
    
    public final float e;
    
    public final float f;
    
    public final class_591<class_742> g;
    
    public a(class_4587 param1class_4587, class_4597 param1class_4597, int param1Int, class_1657 param1class_1657, float param1Float1, float param1Float2, class_591<class_742> param1class_591) {
      this.a = param1class_4587;
      this.b = param1class_4597;
      this.c = param1Int;
      this.d = param1class_1657;
      this.e = param1Float1;
      this.f = param1Float2;
      this.g = param1class_591;
    }
  }
}


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-client-1.2.0.jar!\com\atlas\client\e\a\a.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */