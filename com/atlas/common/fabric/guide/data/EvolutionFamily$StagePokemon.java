/*    */ package com.atlas.common.fabric.guide.data;
/*    */ 
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
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
/*    */ public class StagePokemon
/*    */ {
/* 41 */   public String name = "";
/* 42 */   public String slug = "";
/*    */   @SerializedName("dexNumber")
/*    */   public int id;
/* 45 */   public List<String> types = Collections.emptyList();
/* 46 */   public String method = "";
/* 47 */   public String trigger = "";
/* 48 */   public String details = "";
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\guide\data\EvolutionFamily$StagePokemon.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */