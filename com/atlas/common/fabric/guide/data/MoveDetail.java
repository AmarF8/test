/*    */ package com.atlas.common.fabric.guide.data;
/*    */ 
/*    */ import com.google.gson.Gson;
/*    */ import com.google.gson.JsonArray;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonParser;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MoveDetail
/*    */ {
/* 18 */   private static final Gson GSON = new Gson();
/*    */   
/* 20 */   public String name = "";
/* 21 */   public String type = "";
/* 22 */   public String category = "";
/*    */   public int power;
/*    */   public int accuracy;
/*    */   public int pp;
/* 26 */   public String description = "";
/* 27 */   public List<MovePokemon> pokemon = Collections.emptyList();
/*    */   
/*    */   public static class MovePokemon {
/*    */     public int id;
/* 31 */     public String name = "";
/* 32 */     public String slug = "";
/* 33 */     public String types = "";
/* 34 */     public String method = "";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static MoveDetail fromJson(String json) {
/*    */     try {
/* 42 */       JsonObject obj = JsonParser.parseString(json).getAsJsonObject();
/* 43 */       MoveDetail detail = new MoveDetail();
/* 44 */       detail.name = getStr(obj, "name");
/* 45 */       detail.type = getStr(obj, "type");
/* 46 */       detail.category = getStr(obj, "category");
/* 47 */       detail.power = getInt(obj, "power");
/* 48 */       detail.accuracy = getInt(obj, "accuracy");
/* 49 */       detail.pp = getInt(obj, "pp");
/* 50 */       detail.description = getStr(obj, "description");
/*    */       
/* 52 */       JsonArray arr = obj.getAsJsonArray("pokemon");
/* 53 */       if (arr != null) {
/* 54 */         List<MovePokemon> list = new ArrayList<>();
/* 55 */         for (JsonElement el : arr) {
/* 56 */           if (!el.isJsonObject())
/* 57 */             continue;  JsonObject po = el.getAsJsonObject();
/* 58 */           MovePokemon mp = new MovePokemon();
/* 59 */           mp.id = getInt(po, "id");
/* 60 */           mp.name = getStr(po, "name");
/* 61 */           mp.slug = getStr(po, "slug");
/* 62 */           mp.types = getStr(po, "types");
/* 63 */           mp.method = getStr(po, "method");
/* 64 */           list.add(mp);
/*    */         } 
/* 66 */         detail.pokemon = list;
/*    */       } 
/* 68 */       return detail;
/* 69 */     } catch (Exception e) {
/* 70 */       return null;
/*    */     } 
/*    */   }
/*    */   
/*    */   private static String getStr(JsonObject obj, String key) {
/* 75 */     return (obj.has(key) && !obj.get(key).isJsonNull()) ? obj.get(key).getAsString() : "";
/*    */   }
/*    */   
/*    */   private static int getInt(JsonObject obj, String key) {
/* 79 */     return (obj.has(key) && !obj.get(key).isJsonNull()) ? obj.get(key).getAsInt() : 0;
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\guide\data\MoveDetail.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */