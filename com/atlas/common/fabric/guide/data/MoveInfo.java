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
/*    */ public class MoveInfo
/*    */ {
/* 15 */   private static final Gson GSON = new Gson();
/*    */   
/* 17 */   public String name = "";
/* 18 */   public String slug = "";
/* 19 */   public String type = "";
/* 20 */   public String category = "";
/*    */   public int power;
/*    */   public int accuracy;
/*    */   public int pp;
/* 24 */   public String description = "";
/*    */   
/*    */   public static List<MoveInfo> fromJsonArray(String json) {
/*    */     try {
/* 28 */       Type listType = (new TypeToken<List<MoveInfo>>() {  }).getType();
/* 29 */       return (List<MoveInfo>)GSON.fromJson(json, listType);
/* 30 */     } catch (Exception e) {
/* 31 */       return Collections.emptyList();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\guide\data\MoveInfo.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */