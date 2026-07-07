/*    */ package com.atlas.common.utility;
/*    */ 
/*    */ import java.security.SecureRandom;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class StringUtility
/*    */ {
/*    */   private static final String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
/* 15 */   private static final SecureRandom random = new SecureRandom();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public static String random(int length) {
/* 26 */     StringBuilder builder = new StringBuilder(length);
/* 27 */     for (int i = 0; i < length; i++)
/* 28 */       builder.append("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".charAt(random.nextInt("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".length()))); 
/* 29 */     return builder.toString();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static String getIndefiniteArticle(String word) {
/* 40 */     if (word == null || word.isEmpty()) {
/* 41 */       return "";
/*    */     }
/*    */ 
/*    */     
/* 45 */     char firstChar = Character.toLowerCase(word.charAt(0));
/*    */ 
/*    */     
/* 48 */     if (firstChar == 'a' || firstChar == 'e' || firstChar == 'i' || firstChar == 'o' || firstChar == 'u')
/*    */     {
/* 50 */       return "an";
/*    */     }
/* 52 */     return "a";
/*    */   }
/*    */ 
/*    */   
/*    */   public static String title(String str) {
/* 57 */     if (str == null) {
/* 58 */       return null;
/*    */     }
/* 60 */     StringBuilder sb = new StringBuilder();
/* 61 */     boolean next = true;
/*    */     
/* 63 */     for (char c : str.toCharArray()) {
/* 64 */       if (Character.isWhitespace(c)) {
/* 65 */         next = true;
/* 66 */       } else if (next) {
/* 67 */         c = Character.toTitleCase(c);
/* 68 */         next = false;
/*    */       } else {
/* 70 */         c = Character.toLowerCase(c);
/*    */       } 
/*    */       
/* 73 */       sb.append(c);
/*    */     } 
/*    */     
/* 76 */     return sb.toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\commo\\utility\StringUtility.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */