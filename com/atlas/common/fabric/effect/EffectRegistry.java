/*     */ package com.atlas.common.fabric.effect;
/*     */ 
/*     */ import com.atlas.common.fabric.effect.codec.BattleFormChange;
/*     */ import com.atlas.common.fabric.effect.codec.Effect;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonParser;
/*     */ import com.mojang.serialization.DynamicOps;
/*     */ import com.mojang.serialization.JsonOps;
/*     */ import java.io.InputStreamReader;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
/*     */ import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
/*     */ import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
/*     */ import net.minecraft.class_2960;
/*     */ import net.minecraft.class_3264;
/*     */ import net.minecraft.class_3298;
/*     */ import net.minecraft.class_3300;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
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
/*     */ public final class EffectRegistry
/*     */ {
/*  43 */   private static final Logger LOGGER = LoggerFactory.getLogger(EffectRegistry.class);
/*     */   
/*     */   private static final String NAMESPACE = "mega_showdown";
/*     */   
/*     */   private static final String EFFECT_PATH_PREFIX = "mega_showdown/effect";
/*     */   
/*     */   private static final String BATTLE_FORM_PATH_PREFIX = "mega_showdown/battle_form";
/*  50 */   public static final Map<class_2960, Effect> EFFECTS = new HashMap<>();
/*  51 */   public static final Map<class_2960, BattleFormChange> BATTLE_FORM_CHANGES = new HashMap<>();
/*     */ 
/*     */   
/*     */   public static void registerReloadListener() {
/*  55 */     ResourceManagerHelper.get(class_3264.field_14190).registerReloadListener((IdentifiableResourceReloadListener)new SimpleSynchronousResourceReloadListener()
/*     */         {
/*     */           public class_2960 getFabricId()
/*     */           {
/*  59 */             return class_2960.method_60655("atlas", "effect_engine");
/*     */           }
/*     */ 
/*     */           
/*     */           public void method_14491(class_3300 manager) {
/*  64 */             EffectRegistry.reload(manager);
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void load(MinecraftServer server) {
/*  72 */     reload(server.method_34864());
/*     */   }
/*     */   
/*     */   private static void reload(class_3300 manager) {
/*  76 */     EFFECTS.clear();
/*  77 */     BATTLE_FORM_CHANGES.clear();
/*     */     
/*  79 */     manager.method_14488("mega_showdown/effect", path -> (path.method_12836().equals("mega_showdown") && path.method_12832().endsWith(".json")))
/*  80 */       .forEach((id, resource) -> { try { InputStreamReader reader = new InputStreamReader(resource.method_14482(), StandardCharsets.UTF_8); try { JsonElement json = JsonParser.parseReader(reader); Effect effect = (Effect)Effect.CODEC.parse((DynamicOps)JsonOps.INSTANCE, json).getOrThrow(()); EFFECTS.put(stripPrefix(id, "mega_showdown/effect/"), effect); reader.close(); }
/*  81 */             catch (Throwable throwable) { try { reader.close(); } catch (Throwable throwable1)
/*     */               { throwable.addSuppressed(throwable1); }
/*     */               
/*     */               throw throwable; }
/*     */              }
/*  86 */           catch (Exception e)
/*     */           { LOGGER.error("Failed to load effect {}: {}", id, e.getMessage()); }
/*     */         
/*     */         });
/*     */     
/*  91 */     manager.method_14488("mega_showdown/battle_form", path -> (path.method_12836().equals("mega_showdown") && path.method_12832().endsWith(".json")))
/*  92 */       .forEach((id, resource) -> { try { InputStreamReader reader = new InputStreamReader(resource.method_14482(), StandardCharsets.UTF_8); try { JsonElement json = JsonParser.parseReader(reader); BattleFormChange entry = (BattleFormChange)BattleFormChange.CODEC.parse((DynamicOps)JsonOps.INSTANCE, json).getOrThrow(()); BATTLE_FORM_CHANGES.put(stripPrefix(id, "mega_showdown/battle_form/"), entry); reader.close(); }
/*  93 */             catch (Throwable throwable) { try { reader.close(); } catch (Throwable throwable1)
/*     */               { throwable.addSuppressed(throwable1); }
/*     */               
/*     */               throw throwable; }
/*     */              }
/*  98 */           catch (Exception e)
/*     */           { LOGGER.error("Failed to load battle_form {}: {}", id, e.getMessage()); }
/*     */         
/*     */         });
/*     */     
/* 103 */     LOGGER.info("Atlas effect engine: loaded {} effects, {} battle_form bindings", 
/* 104 */         Integer.valueOf(EFFECTS.size()), Integer.valueOf(BATTLE_FORM_CHANGES.size()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static class_2960 stripPrefix(class_2960 id, String prefix) {
/* 114 */     String path = id.method_12832();
/* 115 */     if (path.startsWith(prefix)) path = path.substring(prefix.length()); 
/* 116 */     if (path.endsWith(".json")) path = path.substring(0, path.length() - 5); 
/* 117 */     return class_2960.method_60655(id.method_12836(), path);
/*     */   }
/*     */   
/*     */   public static Effect getEffect(class_2960 id) {
/* 121 */     return EFFECTS.get(id);
/*     */   }
/*     */   
/*     */   public static Effect getEffect(String id) {
/* 125 */     if (id == null) return null; 
/* 126 */     class_2960 loc = class_2960.method_12829(id);
/* 127 */     return (loc == null) ? null : EFFECTS.get(loc);
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\effect\EffectRegistry.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */