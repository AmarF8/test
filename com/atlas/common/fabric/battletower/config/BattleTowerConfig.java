/*    */ package com.atlas.common.fabric.battletower.config;
/*    */ 
/*    */ import com.google.gson.Gson;
/*    */ import com.google.gson.GsonBuilder;
/*    */ import java.io.File;
/*    */ import java.io.FileReader;
/*    */ import java.io.FileWriter;
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ 
/*    */ public class BattleTowerConfig {
/* 11 */   private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();
/* 12 */   private static BattleTowerConfig INSTANCE = null;
/* 13 */   public int runCooldownSeconds = 0;
/*    */   
/*    */   public static BattleTowerConfig load(MinecraftServer server) {
/* 16 */     return load(server, false);
/*    */   }
/*    */   
/*    */   public static BattleTowerConfig load(MinecraftServer server, boolean force) {
/* 20 */     if (INSTANCE != null && !force) {
/* 21 */       return INSTANCE;
/*    */     }
/* 23 */     File configDir = server.method_3831().resolve("config").resolve("cobblemon_battle_tower").toFile();
/* 24 */     if (!configDir.exists()) {
/* 25 */       configDir.mkdirs();
/*    */     }
/*    */     
/* 28 */     File configFile = new File(configDir, "config.json");
/* 29 */     if (configFile.exists()) {
/*    */       try {
/* 31 */         BattleTowerConfig var5; FileReader reader = new FileReader(configFile);
/*    */ 
/*    */         
/*    */         try {
/* 35 */           INSTANCE = (BattleTowerConfig)GSON.fromJson(reader, BattleTowerConfig.class);
/* 36 */           var5 = INSTANCE;
/* 37 */         } catch (Throwable var8) {
/*    */           try {
/* 39 */             reader.close();
/* 40 */           } catch (Throwable var7) {
/* 41 */             var8.addSuppressed(var7);
/*    */           } 
/*    */           
/* 44 */           throw var8;
/*    */         } 
/*    */         
/* 47 */         reader.close();
/* 48 */         return var5;
/* 49 */       } catch (Exception var9) {
/* 50 */         System.err.println("Failed to load Battle Tower config, using defaults: " + var9.getMessage());
/*    */       } 
/*    */     }
/*    */     
/* 54 */     INSTANCE = new BattleTowerConfig();
/* 55 */     save(server);
/* 56 */     return INSTANCE;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void save(MinecraftServer server) {
/* 61 */     if (INSTANCE != null) {
/* 62 */       File configDir = server.method_3831().resolve("config").resolve("cobblemon_battle_tower").toFile();
/* 63 */       if (!configDir.exists()) {
/* 64 */         configDir.mkdirs();
/*    */       }
/*    */       
/* 67 */       File configFile = new File(configDir, "config.json");
/*    */       
/*    */       try {
/* 70 */         FileWriter writer = new FileWriter(configFile);
/*    */         
/*    */         try {
/* 73 */           GSON.toJson(INSTANCE, writer);
/* 74 */         } catch (Throwable var7) {
/*    */           try {
/* 76 */             writer.close();
/* 77 */           } catch (Throwable var6) {
/* 78 */             var7.addSuppressed(var6);
/*    */           } 
/*    */           
/* 81 */           throw var7;
/*    */         } 
/*    */         
/* 84 */         writer.close();
/* 85 */       } catch (Exception var8) {
/* 86 */         System.err.println("Failed to save Battle Tower config: " + var8.getMessage());
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static BattleTowerConfig get() {
/* 93 */     return (INSTANCE != null) ? INSTANCE : new BattleTowerConfig();
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\battletower\config\BattleTowerConfig.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */