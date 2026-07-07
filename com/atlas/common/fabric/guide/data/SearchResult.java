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
/*    */ 
/*    */ public class SearchResult
/*    */ {
/* 16 */   private static final Gson GSON = new Gson();
/*    */ 
/*    */   
/* 19 */   public String category = "";
/*    */ 
/*    */   
/* 22 */   public String title = "";
/*    */ 
/*    */   
/* 25 */   public String snippet = "";
/*    */ 
/*    */   
/* 28 */   public String slug = "";
/*    */ 
/*    */   
/* 31 */   public String extra = "";
/*    */   
/*    */   public double relevance;
/*    */ 
/*    */   
/*    */   public static List<SearchResult> fromJsonArray(String json) {
/*    */     try {
/* 38 */       Type listType = (new TypeToken<List<SearchResult>>() {  }).getType();
/* 39 */       return (List<SearchResult>)GSON.fromJson(json, listType);
/* 40 */     } catch (Exception e) {
/* 41 */       return Collections.emptyList();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getCategoryLabel() {
/* 49 */     switch (this.category) { case "pokemon": case "spawn": case "evolution": case "move": case "rideable": case "wiki": case "skin": case "form": case "drop": case "biome_spawn": case "server_info":  }  return 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 61 */       this.category;
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\guide\data\SearchResult.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */