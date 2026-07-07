package com.atlas.client.a;

import com.atlas.client.AtlasClient;
import com.cobblemon.mod.common.api.data.JsonDataRegistry;
import com.cobblemon.mod.common.api.reactive.SimpleObservable;
import com.cobblemon.mod.common.client.render.models.blockbench.PosableModel;
import com.cobblemon.mod.common.client.render.models.blockbench.TexturedModel;
import com.cobblemon.mod.common.client.render.models.blockbench.pose.Bone;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import net.minecraft.class_2960;
import net.minecraft.class_3222;
import net.minecraft.class_3264;
import net.minecraft.class_630;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class b implements JsonDataRegistry<TexturedModel> {
  @NotNull
  public static final b a = new b();
  
  @NotNull
  private static final class_2960 b = class_2960.method_60655("atlas", "bedrock_models");
  
  private static final class_3264 c = class_3264.field_14188;
  
  @NotNull
  private static final SimpleObservable<b> d = new SimpleObservable();
  
  @NotNull
  private static final Gson e;
  
  @NotNull
  private static final TypeToken<TexturedModel> f;
  
  @NotNull
  private static final String g = "models/bedrock/models";
  
  @NotNull
  private static final Map<class_2960, class_630> h = new HashMap<>();
  
  @NotNull
  private static final Map<class_2960, PosableModel> i = new HashMap<>();
  
  private static final int j = 1613634414;
  
  public static int a() {
    return j;
  }
  
  public void sync(@NotNull class_3222 paramclass_3222) {}
  
  public void reload(@NotNull Map<class_2960, ? extends TexturedModel> paramMap) {
    for (Map.Entry<class_2960, ? extends TexturedModel> entry : paramMap.entrySet()) {
      class_2960 class_29601 = (class_2960)entry.getKey();
      TexturedModel texturedModel = (TexturedModel)entry.getValue();
      try {
        class_630 class_630 = texturedModel.create().method_32109();
        h.put(class_29601, class_630);
        PosableModel posableModel = new PosableModel((Bone)class_630);
        posableModel.registerPartAndAllNamedChildren("root", (Bone)class_630);
        i.put(class_29601, posableModel);
      } catch (Exception exception) {
        AtlasClient.a().error("Failed to load model " + String.valueOf(class_29601), exception);
      } 
    } 
    d.emit((Object[])new b[] { this });
    AtlasClient.a().info("Loaded {} models", Integer.valueOf(h.size()));
    Set<class_2960> set = h.keySet();
    String str = String.join(", ", (CharSequence[])set.stream().map(class_2960::toString).toArray(paramInt -> new String[paramInt]));
    AtlasClient.a().info("Models: {}", str);
  }
  
  @NotNull
  public class_2960 getId() {
    return b;
  }
  
  public class_3264 getType() {
    return c;
  }
  
  @NotNull
  public SimpleObservable<b> getObservable() {
    return d;
  }
  
  @NotNull
  public Gson getGson() {
    return e;
  }
  
  @NotNull
  public TypeToken<TexturedModel> getTypeToken() {
    return f;
  }
  
  @NotNull
  public String getResourcePath() {
    return "models/bedrock/models";
  }
  
  @Nullable
  public class_630 a(@NotNull class_2960 paramclass_2960) {
    return h.get(paramclass_2960);
  }
  
  @Nullable
  public PosableModel b(@NotNull class_2960 paramclass_2960) {
    return i.get(paramclass_2960);
  }
  
  static {
    e = TexturedModel.Companion.getGSON();
    f = TypeToken.get(TexturedModel.class);
  }
}


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-client-1.2.0.jar!\com\atlas\client\a\b.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */