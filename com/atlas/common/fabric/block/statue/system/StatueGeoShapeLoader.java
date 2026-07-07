/*     */ package com.atlas.common.fabric.block.statue.system;
/*     */ 
/*     */ import com.atlas.common.fabric.AtlasMod;
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParser;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import net.minecraft.class_2338;
/*     */ import net.minecraft.class_259;
/*     */ import net.minecraft.class_265;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.joml.Matrix4f;
/*     */ import org.joml.Matrix4fc;
/*     */ import org.joml.Vector3f;
/*     */ import org.joml.Vector3fc;
/*     */ import org.joml.Vector4f;
/*     */ 
/*     */ public final class StatueGeoShapeLoader {
/*     */   private static final float BLOCK_PIXELS = 16.0F;
/*     */   private static final float HALF_BLOCK_PIXELS = 8.0F;
/*     */   
/*     */   @NotNull
/*     */   public static StatueStructure load(@NotNull String modelName, @NotNull StatueStructure fallback) {
/*  30 */     String resourcePath = "assets/atlas-common-fabric/geo/block/statues/" + modelName + ".geo.json"; 
/*  31 */     try { InputStream stream = StatueGeoShapeLoader.class.getClassLoader().getResourceAsStream(resourcePath); 
/*  32 */       try { if (stream == null)
/*  33 */         { AtlasMod.LOGGER.warn("Could not find statue geo resource {}", resourcePath);
/*  34 */           StatueStructure statueStructure1 = fallback;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*  67 */           if (stream != null) stream.close();  return statueStructure1; }  JsonObject root = JsonParser.parseReader(new InputStreamReader(stream, StandardCharsets.UTF_8)).getAsJsonObject(); JsonObject geometry = root.getAsJsonArray("minecraft:geometry").get(0).getAsJsonObject(); JsonArray bones = geometry.getAsJsonArray("bones"); Map<String, JsonObject> boneMap = new HashMap<>(); for (JsonElement element : bones) { JsonObject bone = element.getAsJsonObject(); boneMap.put(bone.get("name").getAsString(), bone); }  Map<String, Matrix4f> worldTransforms = new HashMap<>(); for (String boneName : boneMap.keySet()) computeWorldTransform(boneName, boneMap, worldTransforms);  Map<class_2338, class_265> cellShapes = new HashMap<>(); for (JsonElement element : bones) { JsonObject bone = element.getAsJsonObject(); Matrix4f boneTransform = worldTransforms.get(bone.get("name").getAsString()); if (boneTransform == null || !bone.has("cubes")) continue;  for (JsonElement cubeElement : bone.getAsJsonArray("cubes")) { JsonObject cube = cubeElement.getAsJsonObject(); addCubeToCells(cellShapes, boneTransform, cube); }  }  StatueStructure statueStructure = cellShapes.isEmpty() ? fallback : StatueStructure.of(cellShapes); if (stream != null) stream.close();  return statueStructure; } catch (Throwable throwable) { if (stream != null) try { stream.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }  } catch (Exception exception)
/*  68 */     { AtlasMod.LOGGER.warn("Failed to load geo collision for statue {}", modelName, exception);
/*  69 */       return fallback; }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void addCubeToCells(@NotNull Map<class_2338, class_265> cellShapes, @NotNull Matrix4f boneTransform, @NotNull JsonObject cube) {
/*  76 */     Vector3f origin = readVector(cube.getAsJsonArray("origin"));
/*  77 */     Vector3f size = readVector(cube.getAsJsonArray("size"));
/*  78 */     float inflate = cube.has("inflate") ? cube.get("inflate").getAsFloat() : 0.0F;
/*     */     
/*  80 */     Matrix4f transform = (new Matrix4f((Matrix4fc)boneTransform)).mul((Matrix4fc)createTransform(
/*  81 */           cube.has("pivot") ? readVector(cube.getAsJsonArray("pivot")) : new Vector3f(), 
/*  82 */           cube.has("rotation") ? readVector(cube.getAsJsonArray("rotation")) : new Vector3f()));
/*     */ 
/*     */     
/*  85 */     Vector3f min = new Vector3f(Float.POSITIVE_INFINITY);
/*  86 */     Vector3f max = new Vector3f(Float.NEGATIVE_INFINITY);
/*     */     
/*  88 */     for (float f : new float[] { origin.x - inflate, origin.x + size.x + inflate }) {
/*  89 */       for (float y : new float[] { origin.y - inflate, origin.y + size.y + inflate }) {
/*  90 */         for (float z : new float[] { origin.z - inflate, origin.z + size.z + inflate }) {
/*  91 */           Vector4f transformed = transform.transform(new Vector4f(f, y, z, 1.0F));
/*  92 */           min.min((Vector3fc)new Vector3f(transformed.x, transformed.y, transformed.z));
/*  93 */           max.max((Vector3fc)new Vector3f(transformed.x, transformed.y, transformed.z));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  98 */     int minX = (int)Math.floor(((min.x + 8.0F) / 16.0F));
/*  99 */     int maxX = (int)Math.floor(((max.x + 8.0F - 1.0E-4F) / 16.0F));
/* 100 */     int minY = (int)Math.floor((min.y / 16.0F));
/* 101 */     int maxY = (int)Math.floor(((max.y - 1.0E-4F) / 16.0F));
/* 102 */     int minZ = (int)Math.floor(((min.z + 8.0F) / 16.0F));
/* 103 */     int maxZ = (int)Math.floor(((max.z + 8.0F - 1.0E-4F) / 16.0F));
/*     */     
/* 105 */     for (int x = minX; x <= maxX; x++) {
/* 106 */       for (int y = minY; y <= maxY; y++) {
/* 107 */         for (int z = minZ; z <= maxZ; z++) {
/* 108 */           float cellMinX = x * 16.0F - 8.0F;
/* 109 */           float cellMinY = y * 16.0F;
/* 110 */           float cellMinZ = z * 16.0F - 8.0F;
/* 111 */           float cellMaxX = cellMinX + 16.0F;
/* 112 */           float cellMaxY = cellMinY + 16.0F;
/* 113 */           float cellMaxZ = cellMinZ + 16.0F;
/*     */           
/* 115 */           float clipMinX = Math.max(min.x, cellMinX);
/* 116 */           float clipMinY = Math.max(min.y, cellMinY);
/* 117 */           float clipMinZ = Math.max(min.z, cellMinZ);
/* 118 */           float clipMaxX = Math.min(max.x, cellMaxX);
/* 119 */           float clipMaxY = Math.min(max.y, cellMaxY);
/* 120 */           float clipMaxZ = Math.min(max.z, cellMaxZ);
/* 121 */           if (clipMinX < clipMaxX && clipMinY < clipMaxY && clipMinZ < clipMaxZ) {
/*     */ 
/*     */ 
/*     */             
/* 125 */             class_265 clippedShape = class_259.method_1081(
/* 126 */                 normalize(clipMinX - cellMinX), 
/* 127 */                 normalize(clipMinY - cellMinY), 
/* 128 */                 normalize(clipMinZ - cellMinZ), 
/* 129 */                 normalize(clipMaxX - cellMinX), 
/* 130 */                 normalize(clipMaxY - cellMinY), 
/* 131 */                 normalize(clipMaxZ - cellMinZ));
/*     */ 
/*     */             
/* 134 */             class_2338 offset = new class_2338(x, y, z);
/* 135 */             cellShapes.merge(offset, clippedShape, class_259::method_1084);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   private static Matrix4f computeWorldTransform(@NotNull String boneName, @NotNull Map<String, JsonObject> boneMap, @NotNull Map<String, Matrix4f> cache) {
/* 144 */     Matrix4f cached = cache.get(boneName);
/* 145 */     if (cached != null) {
/* 146 */       return new Matrix4f((Matrix4fc)cached);
/*     */     }
/*     */     
/* 149 */     JsonObject bone = Objects.<JsonObject>requireNonNull(boneMap.get(boneName));
/* 150 */     Vector3f pivot = bone.has("pivot") ? readVector(bone.getAsJsonArray("pivot")) : new Vector3f();
/* 151 */     Vector3f rotation = bone.has("rotation") ? readVector(bone.getAsJsonArray("rotation")) : new Vector3f();
/*     */     
/* 153 */     Matrix4f transform = createTransform(pivot, rotation);
/* 154 */     if (bone.has("parent")) {
/* 155 */       Matrix4f parent = computeWorldTransform(bone.get("parent").getAsString(), boneMap, cache);
/* 156 */       parent.mul((Matrix4fc)transform);
/* 157 */       cache.put(boneName, new Matrix4f((Matrix4fc)parent));
/* 158 */       return parent;
/*     */     } 
/*     */     
/* 161 */     cache.put(boneName, new Matrix4f((Matrix4fc)transform));
/* 162 */     return transform;
/*     */   }
/*     */   @NotNull
/*     */   private static Matrix4f createTransform(@NotNull Vector3f pivot, @NotNull Vector3f rotationDegrees) {
/* 166 */     return (new Matrix4f())
/* 167 */       .translate((Vector3fc)pivot)
/* 168 */       .rotateZ((float)Math.toRadians(rotationDegrees.z))
/* 169 */       .rotateY((float)Math.toRadians(rotationDegrees.y))
/* 170 */       .rotateX((float)Math.toRadians(rotationDegrees.x))
/* 171 */       .translate(-pivot.x, -pivot.y, -pivot.z);
/*     */   }
/*     */   @NotNull
/*     */   private static Vector3f readVector(@NotNull JsonArray array) {
/* 175 */     return new Vector3f(array
/* 176 */         .get(0).getAsFloat(), array
/* 177 */         .get(1).getAsFloat(), array
/* 178 */         .get(2).getAsFloat());
/*     */   }
/*     */ 
/*     */   
/*     */   private static double normalize(float value) {
/* 183 */     return Math.max(0.0D, Math.min(1.0D, (value / 16.0F)));
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\block\statue\system\StatueGeoShapeLoader.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */