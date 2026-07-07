/*    */ package com.atlas.common.fabric.guide.data;
/*    */ 
/*    */ import com.google.gson.Gson;
/*    */ import com.google.gson.reflect.TypeToken;
/*    */ import java.lang.reflect.Type;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SpawnEntry
/*    */ {
/* 15 */   private static final Gson GSON = new Gson();
/*    */   
/*    */   public int id;
/* 18 */   public String name = "";
/* 19 */   public String slug = "";
/* 20 */   public List<String> types = Collections.emptyList();
/* 21 */   public List<SpawnData> spawns = Collections.emptyList();
/*    */   
/*    */   public static class SpawnData {
/* 24 */     public List<String> biomes = Collections.emptyList();
/* 25 */     public String rarity = "";
/* 26 */     public String levelRange = "";
/* 27 */     public String context = "";
/* 28 */     public List<String> conditions = Collections.emptyList();
/* 29 */     public List<String> excludedBiomes = Collections.emptyList();
/*    */   }
/*    */   
/*    */   public static List<SpawnEntry> fromJsonArray(String json) {
/*    */     try {
/* 34 */       Type listType = (new TypeToken<List<SpawnEntry>>() {  }).getType();
/* 35 */       return (List<SpawnEntry>)GSON.fromJson(json, listType);
/* 36 */     } catch (Exception e) {
/* 37 */       return Collections.emptyList();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\guide\data\SpawnEntry.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */