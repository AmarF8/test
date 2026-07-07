/*    */ package com.atlas.common.fabric.battletower.renderer;
/*    */ 
/*    */ import com.atlas.common.fabric.AtlasMod;
/*    */ import com.atlas.common.fabric.battletower.BattleTower;
/*    */ import com.atlas.common.fabric.battletower.data.SkinManager;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.nio.file.Files;
/*    */ import java.nio.file.Path;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import net.minecraft.class_1011;
/*    */ import net.minecraft.class_1043;
/*    */ import net.minecraft.class_2960;
/*    */ import net.minecraft.class_310;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CustomSkinLoader
/*    */ {
/* 21 */   private static final Map<String, class_2960> LOADED_SKINS = new HashMap<>();
/*    */   
/*    */   public static class_2960 loadCustomSkin(String skinName) {
/* 24 */     if (skinName == null || skinName.isEmpty()) return null; 
/* 25 */     if (LOADED_SKINS.containsKey(skinName)) return LOADED_SKINS.get(skinName);
/*    */     
/*    */     try {
/* 28 */       class_1011 image = null;
/*    */       
/* 30 */       if (skinName.startsWith("default/")) {
/* 31 */         image = loadDefaultSkin(skinName);
/* 32 */       } else if (skinName.startsWith("custom/")) {
/* 33 */         image = loadCustomSkinFile(skinName);
/*    */       } 
/*    */       
/* 36 */       if (image == null) return null;
/*    */       
/* 38 */       class_1043 texture = new class_1043(image);
/* 39 */       String safeKey = "battletower_skin_" + skinName.replace("/", "_");
/*    */ 
/*    */       
/* 42 */       class_2960 location = class_310.method_1551().method_1531().method_4617(safeKey, texture);
/*    */       
/* 44 */       LOADED_SKINS.put(skinName, location);
/* 45 */       return location;
/*    */     }
/* 47 */     catch (IOException e) {
/* 48 */       AtlasMod.LOGGER.error("Failed to load skin {}: {}", skinName, e.getMessage());
/* 49 */       return null;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   private static class_1011 loadDefaultSkin(String skinName) throws IOException {
/* 55 */     String resourcePath = "assets/cobblemon_battle_tower/textures/entity/trainer/" + skinName.substring("default/".length()) + ".png";
/*    */     
/* 57 */     InputStream stream = BattleTower.class.getClassLoader().getResourceAsStream(resourcePath); 
/* 58 */     try { if (stream == null)
/* 59 */       { AtlasMod.LOGGER.warn("Default skin resource not found: {}", resourcePath);
/* 60 */         class_1011 class_10111 = null;
/*    */ 
/*    */ 
/*    */         
/* 64 */         if (stream != null) stream.close();  return class_10111; }  AtlasMod.LOGGER.debug("Loaded default skin from resources: {}", skinName); class_1011 class_1011 = class_1011.method_4309(stream); if (stream != null) stream.close();  return class_1011; } catch (Throwable throwable) { if (stream != null)
/*    */         try { stream.close(); }
/*    */         catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }
/*    */           throw throwable; }
/* 68 */      } private static class_1011 loadCustomSkinFile(String skinName) throws IOException { Path skinPath = SkinManager.getSkinPath(skinName);
/* 69 */     if (skinPath == null || !Files.exists(skinPath, new java.nio.file.LinkOption[0])) {
/* 70 */       AtlasMod.LOGGER.warn("Custom skin file not found: {}", skinName);
/* 71 */       return null;
/*    */     } 
/*    */     
/* 74 */     InputStream stream = Files.newInputStream(skinPath, new java.nio.file.OpenOption[0]); 
/* 75 */     try { AtlasMod.LOGGER.debug("Loaded custom skin from file: {}", skinName);
/* 76 */       class_1011 class_1011 = class_1011.method_4309(stream);
/* 77 */       if (stream != null) stream.close();  return class_1011; } catch (Throwable throwable) { if (stream != null)
/*    */         try { stream.close(); }
/*    */         catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }
/*    */           throw throwable; }
/* 81 */      } public static void clearCache() { LOADED_SKINS.clear(); }
/*    */ 
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\battletower\renderer\CustomSkinLoader.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */