/*    */ package com.atlas.common.fabric.battletower.config;
/*    */ 
/*    */ import com.google.gson.Gson;
/*    */ import com.google.gson.GsonBuilder;
/*    */ import java.io.File;
/*    */ import java.io.FileReader;
/*    */ import java.io.FileWriter;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import net.minecraft.class_5218;
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ 
/*    */ public class ItemUsageConfig {
/* 15 */   private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();
/* 16 */   private static ItemUsageConfig INSTANCE = null;
/* 17 */   public List<ItemUsageTier> itemUsageTiers = createDefaultItemUsageTiers();
/*    */   
/*    */   private static List<ItemUsageTier> createDefaultItemUsageTiers() {
/* 20 */     List<ItemUsageTier> tiers = new ArrayList<>();
/* 21 */     tiers.add(new ItemUsageTier(1, 5, -1));
/* 22 */     tiers.add(new ItemUsageTier(6, 10, 3));
/* 23 */     tiers.add(new ItemUsageTier(11, 15, 2));
/* 24 */     tiers.add(new ItemUsageTier(16, 999, 1));
/* 25 */     return tiers;
/*    */   }
/*    */   
/*    */   public int getMaxItemUsesForFloor(int floor) {
/* 29 */     Iterator<ItemUsageTier> var2 = this.itemUsageTiers.iterator();
/*    */     
/*    */     while (true) {
/* 32 */       if (!var2.hasNext()) {
/* 33 */         return this.itemUsageTiers.isEmpty() ? 1 : ((ItemUsageTier)this.itemUsageTiers.get(this.itemUsageTiers.size() - 1)).maxItemUses;
/*    */       }
/* 35 */       ItemUsageTier tier = var2.next();
/* 36 */       if (floor >= tier.minFloor && floor <= tier.maxFloor)
/* 37 */         return tier.maxItemUses; 
/*    */     } 
/*    */   } public static ItemUsageConfig load(MinecraftServer server) {
/* 40 */     return load(server, false);
/*    */   }
/*    */   public static ItemUsageConfig load(MinecraftServer server, boolean force) {
/* 43 */     if (INSTANCE != null && !force) return INSTANCE; 
/* 44 */     File configDir = server.method_27050(class_5218.field_24188).resolve("config").resolve("cobblemon_battle_tower").toFile();
/* 45 */     if (!configDir.exists()) configDir.mkdirs(); 
/* 46 */     File configFile = new File(configDir, "itemusage.json");
/* 47 */     if (configFile.exists()) {
/*    */       try {
/* 49 */         FileReader reader = new FileReader(configFile);
/*    */         
/* 51 */         try { INSTANCE = (ItemUsageConfig)GSON.fromJson(reader, ItemUsageConfig.class);
/* 52 */           return INSTANCE; }
/* 53 */         catch (Throwable t) { 
/* 54 */           try { reader.close(); } catch (Throwable t2) { t.addSuppressed(t2); }
/* 55 */            throw t; }
/* 56 */         finally { reader.close(); } 
/* 57 */       } catch (Exception e) {
/* 58 */         System.err.println("Failed to load item usage config, using defaults: " + e.getMessage());
/*    */       } 
/*    */     }
/* 61 */     INSTANCE = new ItemUsageConfig();
/* 62 */     save(server);
/* 63 */     return INSTANCE;
/*    */   }
/*    */   
/*    */   public static void save(MinecraftServer server) {
/* 67 */     if (INSTANCE == null)
/* 68 */       return;  File configDir = server.method_27050(class_5218.field_24188).resolve("config").resolve("cobblemon_battle_tower").toFile();
/* 69 */     if (!configDir.exists()) configDir.mkdirs(); 
/* 70 */     File configFile = new File(configDir, "itemusage.json");
/*    */     try {
/* 72 */       FileWriter writer = new FileWriter(configFile); 
/* 73 */       try { GSON.toJson(INSTANCE, writer); }
/* 74 */       catch (Throwable t) { try { writer.close(); } catch (Throwable t2) { t.addSuppressed(t2); }  throw t; }
/* 75 */        writer.close();
/* 76 */     } catch (Exception e) {
/* 77 */       System.err.println("Failed to save item usage config: " + e.getMessage());
/*    */     } 
/*    */   }
/*    */   public static ItemUsageConfig get() {
/* 81 */     return (INSTANCE != null) ? INSTANCE : new ItemUsageConfig();
/*    */   }
/*    */   public static class ItemUsageTier { public int minFloor;
/*    */     public int maxFloor;
/*    */     public int maxItemUses;
/*    */     
/*    */     public ItemUsageTier(int minFloor, int maxFloor, int maxItemUses) {
/* 88 */       this.minFloor = minFloor; this.maxFloor = maxFloor; this.maxItemUses = maxItemUses;
/*    */     } }
/*    */ 
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\battletower\config\ItemUsageConfig.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */