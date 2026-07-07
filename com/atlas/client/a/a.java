package com.atlas.client.a;

import com.atlas.client.AtlasClient;
import com.cobblemon.mod.common.client.render.models.blockbench.AnimationReferenceFactory;
import com.cobblemon.mod.common.client.render.models.blockbench.BedrockAnimationReferenceFactory;
import com.cobblemon.mod.common.client.render.models.blockbench.JsonPose;
import com.cobblemon.mod.common.client.render.models.blockbench.bedrock.animation.BedrockAnimation;
import com.cobblemon.mod.common.client.render.models.blockbench.bedrock.animation.BedrockAnimationAdapter;
import com.cobblemon.mod.common.client.render.models.blockbench.bedrock.animation.BedrockAnimationGroup;
import com.google.common.base.Preconditions;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.class_2960;
import net.minecraft.class_3298;
import net.minecraft.class_3300;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class a {
  @NotNull
  public static final a a = new a();
  
  @NotNull
  private static final List<String> b = new ArrayList<>();
  
  private static final Gson c;
  
  @NotNull
  private static final Map<String, BedrockAnimationGroup> d;
  
  private static final int e = 40253444;
  
  public static int a() {
    return e;
  }
  
  public void a(@NotNull class_3300 paramclass_3300) {
    Preconditions.checkNotNull(paramclass_3300, "resourceManager");
    JsonPose.Companion.registerAnimationFactory("bedrock", (AnimationReferenceFactory)BedrockAnimationReferenceFactory.INSTANCE);
    System.out.println("Loading animations...");
    int i = 0;
    d.clear();
    boolean bool = false;
    for (String str : b) {
      Map map = paramclass_3300.method_14488(str, this::c);
      Preconditions.checkNotNull(map, "findResources result");
      for (Map.Entry entry : map.entrySet()) {
        class_2960 class_2960 = (class_2960)entry.getKey();
        class_3298 class_3298 = (class_3298)entry.getValue();
        try {
          BedrockAnimationGroup bedrockAnimationGroup = a(class_3298);
          for (Map.Entry entry1 : bedrockAnimationGroup.getAnimations().entrySet()) {
            String str2 = (String)entry1.getKey();
            BedrockAnimation bedrockAnimation = (BedrockAnimation)entry1.getValue();
            bedrockAnimation.setName(str2);
            try {
              bedrockAnimation.checkForErrors();
            } catch (Throwable throwable) {
              AtlasClient.a().error("Failed to load animation " + str2 + " in group " + String.valueOf(class_2960) + ": " + throwable.getMessage());
              bool = true;
            } 
          } 
          String str1 = b(class_2960);
          d.put(str1, bedrockAnimationGroup);
          i += bedrockAnimationGroup.getAnimations().size();
        } catch (Exception exception) {
          AtlasClient.a().error("Failed to load animation group " + String.valueOf(class_2960), exception);
        } 
      } 
    } 
    if (bool)
      AtlasClient.a().error("There were errors in the animations. See above for details. You should fix these or there might be crashes later."); 
    AtlasClient.a().info("Loaded " + i + " animations from " + d.size() + " animation groups");
  }
  
  @NotNull
  private BedrockAnimationGroup a(@NotNull class_3298 paramclass_3298) {
    Preconditions.checkNotNull(paramclass_3298, "resource");
    InputStream inputStream = paramclass_3298.method_14482();
    try {
      InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
      try {
        BedrockAnimationGroup bedrockAnimationGroup1 = (BedrockAnimationGroup)c.fromJson(inputStreamReader, BedrockAnimationGroup.class);
        Preconditions.checkNotNull(bedrockAnimationGroup1, "deserialized animation group");
        BedrockAnimationGroup bedrockAnimationGroup2 = bedrockAnimationGroup1;
        inputStreamReader.close();
        if (inputStream != null)
          inputStream.close(); 
        return bedrockAnimationGroup2;
      } catch (Throwable throwable) {
        try {
          inputStreamReader.close();
        } catch (Throwable throwable1) {
          throwable.addSuppressed(throwable1);
        } 
        throw throwable;
      } 
    } catch (Throwable throwable) {
      if (inputStream != null)
        try {
          inputStream.close();
        } catch (Throwable throwable1) {
          throwable.addSuppressed(throwable1);
        }  
      throw throwable;
    } 
  }
  
  @NotNull
  private String b(@NotNull class_2960 paramclass_2960) {
    Preconditions.checkNotNull(paramclass_2960, "identifier");
    String str1 = paramclass_2960.method_12832();
    Preconditions.checkNotNull(str1, "identifier path");
    int i = str1.lastIndexOf('/');
    String str2 = (i >= 0) ? str1.substring(i + 1) : str1;
    return str2.endsWith(".animation.json") ? str2.substring(0, str2.length() - ".animation.json".length()) : str2;
  }
  
  private boolean c(@NotNull class_2960 paramclass_2960) {
    Preconditions.checkNotNull(paramclass_2960, "identifier");
    String str = paramclass_2960.method_12832();
    Preconditions.checkNotNull(str, "identifier path");
    return str.endsWith(".animation.json");
  }
  
  @Nullable
  public BedrockAnimation a(@NotNull class_2960 paramclass_2960) {
    Preconditions.checkNotNull(paramclass_2960, "resource");
    String str = paramclass_2960.method_12836();
    return a(str, "animation." + str + "." + paramclass_2960.method_12832());
  }
  
  @Nullable
  public BedrockAnimation a(@NotNull String paramString1, @NotNull String paramString2) {
    Preconditions.checkNotNull(paramString1, "fileName");
    Preconditions.checkNotNull(paramString2, "animationName");
    BedrockAnimationGroup bedrockAnimationGroup = d.get(paramString1);
    if (bedrockAnimationGroup != null) {
      Map map = bedrockAnimationGroup.getAnimations();
      return (BedrockAnimation)map.get(paramString2);
    } 
    return null;
  }
  
  static {
    b.add("models/bedrock/animations");
    c = (new GsonBuilder()).disableHtmlEscaping().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).registerTypeAdapter(BedrockAnimation.class, BedrockAnimationAdapter.INSTANCE).create();
    d = new LinkedHashMap<>();
  }
}


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-client-1.2.0.jar!\com\atlas\client\a\a.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */