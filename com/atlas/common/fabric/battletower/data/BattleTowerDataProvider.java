/*     */ package com.atlas.common.fabric.battletower.data;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import java.util.function.BiConsumer;
/*     */ import java.util.function.Consumer;
/*     */ import java.util.function.Function;
/*     */ import java.util.function.Supplier;
/*     */ import net.minecraft.class_3222;
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
/*     */ public final class BattleTowerDataProvider
/*     */ {
/*     */   private static Function<UUID, PlayerTowerData> playerDataProvider;
/*     */   private static BiConsumer<UUID, PlayerTowerData> savePlayerDataProvider;
/*     */   private static Consumer<class_3222> sendDataToClientProvider;
/*     */   private static Supplier<List<ShopItem>> shopItemsProvider;
/*     */   private static Supplier<Integer> runCooldownProvider;
/*     */   private static Function<Integer, Integer> bpForFloorProvider;
/*     */   private static Function<int[], Integer> calculateRunBPProvider;
/*     */   private static Function<Integer, Integer> itemUsageForFloorProvider;
/*     */   private static Consumer<class_3222> sendLeaderboardProvider;
/*     */   
/*     */   public static void setPlayerDataProvider(Function<UUID, PlayerTowerData> provider) {
/*  42 */     playerDataProvider = provider;
/*     */   }
/*     */   
/*     */   public static void setSavePlayerDataProvider(BiConsumer<UUID, PlayerTowerData> provider) {
/*  46 */     savePlayerDataProvider = provider;
/*     */   }
/*     */   
/*     */   public static void setSendDataToClientProvider(Consumer<class_3222> provider) {
/*  50 */     sendDataToClientProvider = provider;
/*     */   }
/*     */   
/*     */   public static void setShopItemsProvider(Supplier<List<ShopItem>> provider) {
/*  54 */     shopItemsProvider = provider;
/*     */   }
/*     */   
/*     */   public static void setRunCooldownProvider(Supplier<Integer> provider) {
/*  58 */     runCooldownProvider = provider;
/*     */   }
/*     */   
/*     */   public static void setBpForFloorProvider(Function<Integer, Integer> provider) {
/*  62 */     bpForFloorProvider = provider;
/*     */   }
/*     */   
/*     */   public static void setCalculateRunBPProvider(Function<int[], Integer> provider) {
/*  66 */     calculateRunBPProvider = provider;
/*     */   }
/*     */   
/*     */   public static void setItemUsageForFloorProvider(Function<Integer, Integer> provider) {
/*  70 */     itemUsageForFloorProvider = provider;
/*     */   }
/*     */   
/*     */   public static void setSendLeaderboardProvider(Consumer<class_3222> provider) {
/*  74 */     sendLeaderboardProvider = provider;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static PlayerTowerData getPlayerData(UUID playerUUID) {
/*  80 */     if (playerDataProvider != null) {
/*  81 */       return playerDataProvider.apply(playerUUID);
/*     */     }
/*     */     
/*  84 */     return TowerDataManager.getPlayerData(playerUUID);
/*     */   }
/*     */   
/*     */   public static void savePlayerData(UUID playerUUID, PlayerTowerData data) {
/*  88 */     if (savePlayerDataProvider != null) {
/*  89 */       savePlayerDataProvider.accept(playerUUID, data);
/*     */     } else {
/*  91 */       TowerDataManager.savePlayerData(playerUUID);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void sendDataToClient(class_3222 player) {
/*  96 */     if (sendDataToClientProvider != null) {
/*  97 */       sendDataToClientProvider.accept(player);
/*     */     }
/*     */   }
/*     */   
/*     */   public static List<ShopItem> getShopItems() {
/* 102 */     if (shopItemsProvider != null) {
/* 103 */       return shopItemsProvider.get();
/*     */     }
/* 105 */     return ShopItem.getAllItems();
/*     */   }
/*     */   
/*     */   public static int getRunCooldownSeconds() {
/* 109 */     if (runCooldownProvider != null) {
/* 110 */       return ((Integer)runCooldownProvider.get()).intValue();
/*     */     }
/* 112 */     return 0;
/*     */   }
/*     */   
/*     */   public static int getBPForFloor(int floor, boolean usedLegendaries) {
/* 116 */     if (bpForFloorProvider != null)
/*     */     {
/* 118 */       return ((Integer)bpForFloorProvider.apply(Integer.valueOf(usedLegendaries ? -floor : floor))).intValue();
/*     */     }
/* 120 */     return 1;
/*     */   }
/*     */   
/*     */   public static int calculateRunBP(int floorsCompleted, boolean usedLegendaries) {
/* 124 */     if (calculateRunBPProvider != null) {
/* 125 */       return ((Integer)calculateRunBPProvider.apply(new int[] { floorsCompleted, usedLegendaries ? 1 : 0 })).intValue();
/*     */     }
/* 127 */     return 0;
/*     */   }
/*     */   
/*     */   public static int getMaxItemUsesForFloor(int floor) {
/* 131 */     if (itemUsageForFloorProvider != null) {
/* 132 */       return ((Integer)itemUsageForFloorProvider.apply(Integer.valueOf(floor))).intValue();
/*     */     }
/* 134 */     return -1;
/*     */   }
/*     */   
/*     */   public static void sendLeaderboardToClient(class_3222 player) {
/* 138 */     if (sendLeaderboardProvider != null) {
/* 139 */       sendLeaderboardProvider.accept(player);
/*     */     }
/*     */   }
/*     */   
/*     */   public static boolean isInitialized() {
/* 144 */     return (playerDataProvider != null);
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\battletower\data\BattleTowerDataProvider.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */