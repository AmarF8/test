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
/*    */ public class BPRewardsConfig {
/* 15 */   private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();
/* 16 */   private static BPRewardsConfig INSTANCE = null;
/* 17 */   public List<BPTier> bpTiers = createDefaultTiers();
/* 18 */   public int legendaryBPPerFloor = 1;
/*    */   
/*    */   private static List<BPTier> createDefaultTiers() {
/* 21 */     List<BPTier> tiers = new ArrayList<>();
/* 22 */     tiers.add(new BPTier(1, 5, 1));
/* 23 */     tiers.add(new BPTier(6, 10, 2));
/* 24 */     tiers.add(new BPTier(11, 15, 3));
/* 25 */     tiers.add(new BPTier(16, 30, 4));
/* 26 */     tiers.add(new BPTier(31, 999, 5));
/* 27 */     return tiers;
/*    */   }
/*    */   
/*    */   public int getBPForFloor(int floor, boolean usedLegendaries) {
/* 31 */     if (usedLegendaries) return this.legendaryBPPerFloor; 
/* 32 */     Iterator<BPTier> var3 = this.bpTiers.iterator();
/*    */     
/*    */     while (true) {
/* 35 */       if (!var3.hasNext()) return this.bpTiers.isEmpty() ? 1 : ((BPTier)this.bpTiers.get(this.bpTiers.size() - 1)).bpPerWin; 
/* 36 */       BPTier tier = var3.next();
/* 37 */       if (floor >= tier.minFloor && floor <= tier.maxFloor)
/* 38 */         return tier.bpPerWin; 
/*    */     } 
/*    */   }
/*    */   public int calculateRunBP(int floorsCompleted, boolean usedLegendaries) {
/* 42 */     int totalBP = 0;
/* 43 */     for (int floor = 1; floor < floorsCompleted; ) { totalBP += getBPForFloor(floor, usedLegendaries); floor++; }
/* 44 */      return totalBP;
/*    */   }
/*    */   public static BPRewardsConfig load(MinecraftServer server) {
/* 47 */     return load(server, false);
/*    */   }
/*    */   public static BPRewardsConfig load(MinecraftServer server, boolean force) {
/* 50 */     if (INSTANCE != null && !force) return INSTANCE; 
/* 51 */     File configDir = server.method_27050(class_5218.field_24188).resolve("config").resolve("cobblemon_battle_tower").toFile();
/* 52 */     if (!configDir.exists()) configDir.mkdirs(); 
/* 53 */     File configFile = new File(configDir, "bp_per_floor.json");
/* 54 */     if (configFile.exists()) {
/*    */       try {
/* 56 */         FileReader reader = new FileReader(configFile);
/*    */         
/* 58 */         try { INSTANCE = (BPRewardsConfig)GSON.fromJson(reader, BPRewardsConfig.class);
/* 59 */           return INSTANCE; }
/* 60 */         catch (Throwable t) { 
/* 61 */           try { reader.close(); } catch (Throwable t2) { t.addSuppressed(t2); }
/* 62 */            throw t; }
/* 63 */         finally { reader.close(); } 
/* 64 */       } catch (Exception e) {
/* 65 */         System.err.println("Failed to load BP rewards config, using defaults: " + e.getMessage());
/*    */       } 
/*    */     }
/* 68 */     INSTANCE = new BPRewardsConfig();
/* 69 */     save(server);
/* 70 */     return INSTANCE;
/*    */   }
/*    */   
/*    */   public static void save(MinecraftServer server) {
/* 74 */     if (INSTANCE == null)
/* 75 */       return;  File configDir = server.method_27050(class_5218.field_24188).resolve("config").resolve("cobblemon_battle_tower").toFile();
/* 76 */     if (!configDir.exists()) configDir.mkdirs(); 
/* 77 */     File configFile = new File(configDir, "bp_per_floor.json");
/*    */     try {
/* 79 */       FileWriter writer = new FileWriter(configFile); 
/* 80 */       try { GSON.toJson(INSTANCE, writer); }
/* 81 */       catch (Throwable t) { try { writer.close(); } catch (Throwable t2) { t.addSuppressed(t2); }  throw t; }
/* 82 */        writer.close();
/* 83 */     } catch (Exception e) {
/* 84 */       System.err.println("Failed to save BP rewards config: " + e.getMessage());
/*    */     } 
/*    */   }
/*    */   public static BPRewardsConfig get() {
/* 88 */     return (INSTANCE != null) ? INSTANCE : new BPRewardsConfig();
/*    */   }
/*    */   public static class BPTier { public int minFloor;
/*    */     public int maxFloor;
/*    */     public int bpPerWin;
/*    */     
/*    */     public BPTier(int minFloor, int maxFloor, int bpPerWin) {
/* 95 */       this.minFloor = minFloor; this.maxFloor = maxFloor; this.bpPerWin = bpPerWin;
/*    */     } }
/*    */ 
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\battletower\config\BPRewardsConfig.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */