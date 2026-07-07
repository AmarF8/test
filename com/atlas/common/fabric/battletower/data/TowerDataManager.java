/*     */ package com.atlas.common.fabric.battletower.data;
/*     */ 
/*     */ import com.atlas.common.fabric.AtlasMod;
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import java.io.File;
/*     */ import java.lang.reflect.Field;
/*     */ import java.nio.file.Path;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.class_2487;
/*     */ import net.minecraft.class_2505;
/*     */ import net.minecraft.class_2507;
/*     */ import net.minecraft.class_5218;
/*     */ import net.minecraft.class_7225;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ 
/*     */ public class TowerDataManager {
/*  21 */   private static final Map<UUID, PlayerTowerData> playerData = new HashMap<>();
/*  22 */   private static Path dataDirectory = null;
/*  23 */   private static MinecraftServer server = null;
/*     */   
/*     */   public static void init(MinecraftServer minecraftServer) {
/*  26 */     server = minecraftServer;
/*  27 */     dataDirectory = server.method_27050(class_5218.field_24188).resolve("data").resolve("battle_tower");
/*  28 */     dataDirectory.toFile().mkdirs();
/*  29 */     AtlasMod.LOGGER.info("Battle Tower data directory: {}", dataDirectory);
/*     */   }
/*     */   
/*     */   public static PlayerTowerData getPlayerData(UUID playerUUID) {
/*  33 */     if (!playerData.containsKey(playerUUID)) {
/*  34 */       PlayerTowerData data = loadPlayerData(playerUUID);
/*  35 */       playerData.put(playerUUID, data);
/*     */     } 
/*     */     
/*  38 */     return playerData.get(playerUUID);
/*     */   }
/*     */   
/*     */   private static PlayerTowerData loadPlayerData(UUID playerUUID) {
/*  42 */     if (dataDirectory != null && server != null) {
/*  43 */       File file = dataDirectory.resolve(playerUUID.toString() + ".dat").toFile();
/*  44 */       if (file.exists()) {
/*     */         try {
/*  46 */           class_2487 tag = class_2507.method_30613(file.toPath(), class_2505.method_53898());
/*  47 */           return PlayerTowerData.fromNbt(playerUUID, tag, (class_7225.class_7874)server.method_30611());
/*  48 */         } catch (Exception var3) {
/*  49 */           AtlasMod.LOGGER.error("Failed to load player data for {}: {}", playerUUID, var3.getMessage());
/*     */         } 
/*     */       }
/*     */       
/*  53 */       return new PlayerTowerData(playerUUID);
/*     */     } 
/*  55 */     return new PlayerTowerData(playerUUID);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void savePlayerData(UUID playerUUID) {
/*  60 */     if (dataDirectory != null && server != null) {
/*  61 */       PlayerTowerData data = playerData.get(playerUUID);
/*  62 */       if (data != null) {
/*  63 */         File file = dataDirectory.resolve(playerUUID.toString() + ".dat").toFile();
/*     */         
/*     */         try {
/*  66 */           class_2507.method_30614(data.toNbt((class_7225.class_7874)server.method_30611()), file.toPath());
/*  67 */         } catch (Exception var4) {
/*  68 */           AtlasMod.LOGGER.error("Failed to save player data for {}: {}", playerUUID, var4.getMessage());
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void saveAll() {
/*  76 */     Iterator<UUID> var0 = playerData.keySet().iterator();
/*     */     
/*  78 */     while (var0.hasNext()) {
/*  79 */       UUID uuid = var0.next();
/*  80 */       savePlayerData(uuid);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void clear() {
/*  86 */     saveAll();
/*  87 */     playerData.clear();
/*  88 */     dataDirectory = null;
/*  89 */     server = null;
/*     */   }
/*     */   
/*     */   public static Map<UUID, PlayerTowerData> getAllPlayerData() {
/*  93 */     loadAllPlayerDataFromDisk();
/*  94 */     return new HashMap<>(playerData);
/*     */   }
/*     */   
/*     */   private static void loadAllPlayerDataFromDisk() {
/*  98 */     if (dataDirectory != null && server != null) {
/*  99 */       File[] files = dataDirectory.toFile().listFiles((dir, name) -> name.endsWith(".dat"));
/*     */ 
/*     */       
/* 102 */       if (files != null) {
/* 103 */         File[] var1 = files;
/* 104 */         int var2 = files.length;
/*     */         
/* 106 */         for (int var3 = 0; var3 < var2; var3++) {
/* 107 */           File file = var1[var3];
/* 108 */           String fileName = file.getName();
/* 109 */           String uuidString = fileName.substring(0, fileName.length() - 4);
/*     */           
/*     */           try {
/* 112 */             UUID uuid = UUID.fromString(uuidString);
/*     */             
/* 114 */             if (!playerData.containsKey(uuid)) {
/* 115 */               PlayerTowerData playerTowerData = loadPlayerData(uuid);
/* 116 */               playerData.put(uuid, playerTowerData);
/*     */             } 
/*     */             
/* 119 */             PlayerTowerData data = playerData.get(uuid);
/* 120 */             if (data != null && (data.getPlayerName().equals("Unknown") || data.getPlayerName().isEmpty())) {
/* 121 */               Optional<GameProfile> gameProfile = server.method_3793().method_14512(uuid);
/* 122 */               if (gameProfile.isPresent()) {
/* 123 */                 String playerName = ((GameProfile)gameProfile.get()).getName();
/* 124 */                 data.setPlayerName(playerName);
/* 125 */                 savePlayerData(uuid);
/* 126 */                 AtlasMod.LOGGER.debug("Updated player name for {}: {}", uuid, playerName);
/*     */               } 
/*     */             } 
/* 129 */           } catch (IllegalArgumentException var11) {
/* 130 */             AtlasMod.LOGGER.warn("Invalid UUID in filename: {}", fileName);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void updatePlayerName(UUID playerUUID, String playerName) {
/* 139 */     PlayerTowerData data = getPlayerData(playerUUID);
/* 140 */     data.setPlayerName(playerName);
/* 141 */     savePlayerData(playerUUID);
/*     */   }
/*     */   
/*     */   public static int resetAllLeaderboardData() {
/* 145 */     loadAllPlayerDataFromDisk();
/* 146 */     int count = 0;
/* 147 */     Iterator<PlayerTowerData> var1 = playerData.values().iterator();
/*     */     
/* 149 */     while (var1.hasNext()) {
/* 150 */       PlayerTowerData data = var1.next();
/*     */       
/*     */       try {
/* 153 */         Field highestFloorField = PlayerTowerData.class.getDeclaredField("highestFloor");
/* 154 */         highestFloorField.setAccessible(true);
/* 155 */         highestFloorField.setInt(data, 1);
/* 156 */         Field bestStreakField = PlayerTowerData.class.getDeclaredField("bestStreak");
/* 157 */         bestStreakField.setAccessible(true);
/* 158 */         bestStreakField.setInt(data, 0);
/* 159 */         Field totalWinsField = PlayerTowerData.class.getDeclaredField("totalWins");
/* 160 */         totalWinsField.setAccessible(true);
/* 161 */         totalWinsField.setInt(data, 0);
/* 162 */         Field totalLossesField = PlayerTowerData.class.getDeclaredField("totalLosses");
/* 163 */         totalLossesField.setAccessible(true);
/* 164 */         totalLossesField.setInt(data, 0);
/* 165 */         Field highestFloorByModeField = PlayerTowerData.class.getDeclaredField("highestFloorByMode");
/* 166 */         highestFloorByModeField.setAccessible(true);
/* 167 */         ((Map)highestFloorByModeField.get(data)).clear();
/* 168 */         Field bestStreakByModeField = PlayerTowerData.class.getDeclaredField("bestStreakByMode");
/* 169 */         bestStreakByModeField.setAccessible(true);
/* 170 */         ((Map)bestStreakByModeField.get(data)).clear();
/* 171 */         savePlayerData(data.getPlayerUUID());
/* 172 */         count++;
/* 173 */       } catch (Exception var11) {
/* 174 */         AtlasMod.LOGGER.error("Failed to reset data for player {}: {}", data.getPlayerUUID(), var11.getMessage());
/*     */       } 
/*     */     } 
/*     */     
/* 178 */     return count;
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\battletower\data\TowerDataManager.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */