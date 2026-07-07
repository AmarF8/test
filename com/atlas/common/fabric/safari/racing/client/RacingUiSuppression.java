/*     */ package com.atlas.common.fabric.safari.racing.client;
/*     */ 
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Method;
/*     */ import net.fabricmc.api.EnvType;
/*     */ import net.fabricmc.api.Environment;
/*     */ import net.minecraft.class_310;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Environment(EnvType.CLIENT)
/*     */ public final class RacingUiSuppression
/*     */ {
/*     */   private static boolean xaeroSuppressionTracked;
/*     */   private static boolean xaeroRestoreMinimapOnExit;
/*     */   
/*     */   public static void tick(class_310 client) {
/*  22 */     RacingHudState hudState = RacingHudState.getInstance();
/*  23 */     hudState.clearIfStale();
/*     */     
/*  25 */     if (client.field_1724 == null || client.field_1687 == null) {
/*  26 */       hudState.clear();
/*     */     }
/*     */     
/*  29 */     if (hudState.isActive()) {
/*  30 */       hideXaeroMinimap();
/*     */       
/*  32 */       if (client.field_1755 != null && isXaeroWorldMapScreen(client.field_1755.getClass().getName())) {
/*  33 */         client.method_1507(null);
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/*  38 */     restoreXaeroMinimap();
/*     */   }
/*     */   
/*     */   private static boolean isXaeroWorldMapScreen(String className) {
/*  42 */     String normalized = className.toLowerCase();
/*  43 */     if (!normalized.contains("xaero")) return false; 
/*  44 */     return (normalized.contains("worldmap") || normalized.contains("map.gui") || normalized.contains("guimap"));
/*     */   }
/*     */   
/*     */   private static void hideXaeroMinimap() {
/*  48 */     if (!xaeroSuppressionTracked) {
/*  49 */       Boolean currentlyActive = getXaeroMinimapActive();
/*  50 */       if (currentlyActive == null)
/*     */         return; 
/*  52 */       xaeroSuppressionTracked = true;
/*  53 */       xaeroRestoreMinimapOnExit = currentlyActive.booleanValue();
/*     */     } 
/*     */     
/*  56 */     setXaeroMinimapActive(false);
/*     */   }
/*     */   
/*     */   private static void restoreXaeroMinimap() {
/*  60 */     if (!xaeroSuppressionTracked)
/*     */       return; 
/*  62 */     if (xaeroRestoreMinimapOnExit) {
/*  63 */       setXaeroMinimapActive(true);
/*     */     }
/*     */     
/*  66 */     xaeroSuppressionTracked = false;
/*  67 */     xaeroRestoreMinimapOnExit = false;
/*     */   }
/*     */   
/*     */   private static Boolean getXaeroMinimapActive() {
/*     */     try {
/*  72 */       Object minimapModule = getXaeroMinimapModule();
/*  73 */       Object configManager = getXaeroConfigManager();
/*  74 */       if (minimapModule == null || configManager == null) return null;
/*     */       
/*  76 */       Method isActive = minimapModule.getClass().getMethod("isActive", new Class[] { configManager.getClass() });
/*  77 */       return (Boolean)isActive.invoke(minimapModule, new Object[] { configManager });
/*  78 */     } catch (Throwable ignored) {
/*  79 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void setXaeroMinimapActive(boolean active) {
/*     */     try {
/*  85 */       Object minimapModule = getXaeroMinimapModule();
/*  86 */       Object configManager = getXaeroConfigManager();
/*  87 */       if (minimapModule == null || configManager == null)
/*     */         return; 
/*  89 */       Method setActive = minimapModule.getClass().getMethod("setActive", new Class[] { configManager.getClass(), boolean.class });
/*  90 */       setActive.invoke(minimapModule, new Object[] { configManager, Boolean.valueOf(active) });
/*  91 */     } catch (Throwable throwable) {}
/*     */   }
/*     */ 
/*     */   
/*     */   private static Object getXaeroMinimapModule() throws ReflectiveOperationException {
/*  96 */     Class<?> builtInHudModules = Class.forName("xaero.hud.minimap.BuiltInHudModules");
/*  97 */     Field minimapField = builtInHudModules.getField("MINIMAP");
/*  98 */     return minimapField.get(null);
/*     */   }
/*     */   
/*     */   private static Object getXaeroConfigManager() throws ReflectiveOperationException {
/* 102 */     Object modInstance = getXaeroModInstance();
/* 103 */     if (modInstance == null) return null;
/*     */     
/* 105 */     Method getHudConfigs = modInstance.getClass().getMethod("getHudConfigs", new Class[0]);
/* 106 */     Object hudConfigs = getHudConfigs.invoke(modInstance, new Object[0]);
/* 107 */     if (hudConfigs == null) return null;
/*     */     
/*     */     try {
/* 110 */       return hudConfigs.getClass().getMethod("getClientConfigManager", new Class[0]).invoke(hudConfigs, new Object[0]);
/* 111 */     } catch (NoSuchMethodException ignored) {
/* 112 */       return hudConfigs.getClass().getMethod("getPrimaryClientConfigManager", new Class[0]).invoke(hudConfigs, new Object[0]);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static Object getXaeroModInstance() throws ReflectiveOperationException {
/*     */     try {
/* 118 */       Class<?> hudModClass = Class.forName("xaero.common.HudMod");
/* 119 */       Field instanceField = hudModClass.getField("INSTANCE");
/* 120 */       return instanceField.get(null);
/* 121 */     } catch (ClassNotFoundException ignored) {
/* 122 */       Class<?> minimapClass = Class.forName("xaero.minimap.XaeroMinimap");
/* 123 */       Field instanceField = minimapClass.getField("instance");
/* 124 */       return instanceField.get(null);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\safari\racing\client\RacingUiSuppression.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */