/*    */ package com.atlas.common.fabric.guide.data;
/*    */ 
/*    */ import com.google.gson.Gson;
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import com.google.gson.reflect.TypeToken;
/*    */ import java.lang.reflect.Type;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EvolutionFamily
/*    */ {
/* 16 */   private static final Gson GSON = new Gson();
/*    */   
/* 18 */   public String rootSlug = "";
/*    */   public PokemonRef root;
/*    */   public int stageCount;
/* 21 */   public List<String> methods = Collections.emptyList();
/* 22 */   public List<String> pokemonSlugs = Collections.emptyList();
/*    */   public ChainData chain;
/*    */   
/*    */   public static class PokemonRef {
/* 26 */     public String name = "";
/* 27 */     public String slug = "";
/*    */     public int id;
/* 29 */     public List<String> types = Collections.emptyList();
/*    */   }
/*    */   
/*    */   public static class ChainData {
/* 33 */     public List<EvolutionFamily.StageData> stages = Collections.emptyList();
/*    */   }
/*    */   
/*    */   public static class StageData {
/* 37 */     public List<EvolutionFamily.StagePokemon> pokemon = Collections.emptyList();
/*    */   }
/*    */   
/*    */   public static class StagePokemon {
/* 41 */     public String name = "";
/* 42 */     public String slug = "";
/*    */     @SerializedName("dexNumber")
/*    */     public int id;
/* 45 */     public List<String> types = Collections.emptyList();
/* 46 */     public String method = "";
/* 47 */     public String trigger = "";
/* 48 */     public String details = "";
/*    */   }
/*    */   
/*    */   public static List<EvolutionFamily> fromJsonArray(String json) {
/*    */     try {
/* 53 */       Type listType = (new TypeToken<List<EvolutionFamily>>() {  }).getType();
/* 54 */       return (List<EvolutionFamily>)GSON.fromJson(json, listType);
/* 55 */     } catch (Exception e) {
/* 56 */       return Collections.emptyList();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\guide\data\EvolutionFamily.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */