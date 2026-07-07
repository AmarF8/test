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
/*    */ public class WikiSection
/*    */ {
/* 15 */   private static final Gson GSON = new Gson();
/*    */   
/* 17 */   public String title = "";
/* 18 */   public List<WikiArticle> articles = Collections.emptyList();
/*    */   
/*    */   public static class WikiArticle {
/* 21 */     public String title = "";
/* 22 */     public String path = "";
/*    */   }
/*    */   
/*    */   public static List<WikiSection> fromJsonArray(String json) {
/*    */     try {
/* 27 */       Type listType = (new TypeToken<List<WikiSection>>() {  }).getType();
/* 28 */       return (List<WikiSection>)GSON.fromJson(json, listType);
/* 29 */     } catch (Exception e) {
/* 30 */       return Collections.emptyList();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\guide\data\WikiSection.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */