/*    */ package com.atlas.common.fabric.guide.data;
/*    */ 
/*    */ import com.google.gson.JsonArray;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonParser;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class RideableInfo {
/* 13 */   public String name = ""; public int id;
/* 14 */   public String slug = "";
/* 15 */   public List<String> types = Collections.emptyList();
/* 16 */   public String movementType = "";
/*    */   public int seats;
/* 18 */   public List<RideStat> stats = Collections.emptyList();
/*    */   
/*    */   public static class RideStat {
/* 21 */     public String key = "";
/* 22 */     public String displayName = "";
/*    */     public int maxValue;
/*    */   }
/*    */   
/*    */   public static List<RideableInfo> fromJsonArray(String json) {
/*    */     try {
/* 28 */       JsonArray arr = JsonParser.parseString(json).getAsJsonArray();
/* 29 */       List<RideableInfo> result = new ArrayList<>();
/* 30 */       for (JsonElement el : arr) {
/* 31 */         JsonObject obj = el.getAsJsonObject();
/* 32 */         RideableInfo info = new RideableInfo();
/* 33 */         info.id = obj.has("id") ? obj.get("id").getAsInt() : 0;
/* 34 */         info.name = obj.has("name") ? obj.get("name").getAsString() : "";
/* 35 */         info.slug = obj.has("slug") ? obj.get("slug").getAsString() : "";
/* 36 */         info.movementType = obj.has("movementType") ? obj.get("movementType").getAsString() : "";
/* 37 */         info.seats = obj.has("seats") ? obj.get("seats").getAsInt() : 0;
/*    */         
/* 39 */         if (obj.has("types") && obj.get("types").isJsonArray()) {
/* 40 */           info.types = new ArrayList<>();
/* 41 */           for (JsonElement t : obj.getAsJsonArray("types")) {
/* 42 */             info.types.add(t.getAsString());
/*    */           }
/*    */         } 
/*    */         
/* 46 */         if (obj.has("stats") && obj.get("stats").isJsonArray()) {
/* 47 */           info.stats = new ArrayList<>();
/* 48 */           for (JsonElement s : obj.getAsJsonArray("stats")) {
/* 49 */             JsonObject so = s.getAsJsonObject();
/* 50 */             RideStat stat = new RideStat();
/* 51 */             stat.key = so.has("key") ? so.get("key").getAsString() : "";
/* 52 */             stat.displayName = so.has("displayName") ? so.get("displayName").getAsString() : stat.key;
/*    */ 
/*    */             
/* 55 */             if (so.has("ranges")) {
/* 56 */               JsonElement rangesEl = so.get("ranges");
/* 57 */               if (rangesEl.isJsonPrimitive()) {
/* 58 */                 stat.maxValue = parseMaxFromRange(rangesEl.getAsString());
/* 59 */               } else if (rangesEl.isJsonObject()) {
/* 60 */                 int max = 0;
/* 61 */                 for (Map.Entry<String, JsonElement> entry : (Iterable<Map.Entry<String, JsonElement>>)rangesEl.getAsJsonObject().entrySet()) {
/* 62 */                   max = Math.max(max, parseMaxFromRange(((JsonElement)entry.getValue()).getAsString()));
/*    */                 }
/* 64 */                 stat.maxValue = max;
/*    */               } 
/*    */             } 
/*    */             
/* 68 */             info.stats.add(stat);
/*    */           } 
/*    */         } 
/*    */         
/* 72 */         result.add(info);
/*    */       } 
/* 74 */       return result;
/* 75 */     } catch (Exception e) {
/* 76 */       return Collections.emptyList();
/*    */     } 
/*    */   }
/*    */   
/*    */   private static int parseMaxFromRange(String range) {
/*    */     try {
/* 82 */       String[] parts = range.split("-");
/* 83 */       if (parts.length >= 2) return Integer.parseInt(parts[1].trim()); 
/* 84 */       return Integer.parseInt(parts[0].trim());
/* 85 */     } catch (Exception e) {
/* 86 */       return 0;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\guide\data\RideableInfo.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */