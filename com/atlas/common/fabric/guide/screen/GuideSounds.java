/*    */ package com.atlas.common.fabric.guide.screen;
/*    */ 
/*    */ import net.minecraft.class_1109;
/*    */ import net.minecraft.class_1113;
/*    */ import net.minecraft.class_310;
/*    */ import net.minecraft.class_3417;
/*    */ import net.minecraft.class_6880;
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
/*    */ public final class GuideSounds
/*    */ {
/* 20 */   private static String lastHoveredKey = "";
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void playClick() {
/* 27 */     class_310.method_1551().method_1483().method_4873(
/* 28 */         (class_1113)class_1109.method_47978((class_6880)class_3417.field_15015, 1.0F));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void playTabClick() {
/* 37 */     class_310.method_1551().method_1483().method_4873(
/* 38 */         (class_1113)class_1109.method_47978((class_6880)class_3417.field_15015, 1.3F));
/*    */   }
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
/*    */   public static boolean playHover(String hoverKey) {
/* 52 */     if (hoverKey == null || hoverKey.isEmpty()) {
/* 53 */       lastHoveredKey = "";
/* 54 */       return false;
/*    */     } 
/* 56 */     if (hoverKey.equals(lastHoveredKey)) return false; 
/* 57 */     lastHoveredKey = hoverKey;
/* 58 */     class_310.method_1551().method_1483().method_4873(
/* 59 */         (class_1113)class_1109.method_47978((class_6880)class_3417.field_15015, 1.8F));
/*    */     
/* 61 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void playNavigate() {
/* 69 */     class_310.method_1551().method_1483().method_4873(
/* 70 */         (class_1113)class_1109.method_47978((class_6880)class_3417.field_15015, 0.8F));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void resetHover() {
/* 79 */     lastHoveredKey = "";
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\guide\screen\GuideSounds.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */