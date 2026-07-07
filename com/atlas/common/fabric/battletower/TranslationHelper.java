/*    */ package com.atlas.common.fabric.battletower;
/*    */ 
/*    */ import net.minecraft.class_2561;
/*    */ import net.minecraft.class_5250;
/*    */ 
/*    */ public class TranslationHelper {
/*    */   private static final String PREFIX = "battletower.";
/*    */   
/*    */   public static class_5250 translate(String key, Object... args) {
/* 10 */     return class_2561.method_43469("battletower." + key, args);
/*    */   }
/*    */   
/*    */   public static class_5250 literal(String text) {
/* 14 */     return class_2561.method_43470(text);
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\battletower\TranslationHelper.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */