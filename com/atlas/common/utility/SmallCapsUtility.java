/*    */ package com.atlas.common.utility;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class SmallCapsUtility
/*    */ {
/*  8 */   private static final Map<Character, Character> smallCapsMap = new HashMap<>();
/*    */   
/*    */   static {
/* 11 */     smallCapsMap.put(Character.valueOf('a'), Character.valueOf('ᴀ'));
/* 12 */     smallCapsMap.put(Character.valueOf('b'), Character.valueOf('ʙ'));
/* 13 */     smallCapsMap.put(Character.valueOf('c'), Character.valueOf('ᴄ'));
/* 14 */     smallCapsMap.put(Character.valueOf('d'), Character.valueOf('ᴅ'));
/* 15 */     smallCapsMap.put(Character.valueOf('e'), Character.valueOf('ᴇ'));
/* 16 */     smallCapsMap.put(Character.valueOf('f'), Character.valueOf('ғ'));
/* 17 */     smallCapsMap.put(Character.valueOf('g'), Character.valueOf('ɢ'));
/* 18 */     smallCapsMap.put(Character.valueOf('h'), Character.valueOf('ʜ'));
/* 19 */     smallCapsMap.put(Character.valueOf('i'), Character.valueOf('ɪ'));
/* 20 */     smallCapsMap.put(Character.valueOf('j'), Character.valueOf('ᴊ'));
/* 21 */     smallCapsMap.put(Character.valueOf('k'), Character.valueOf('ᴋ'));
/* 22 */     smallCapsMap.put(Character.valueOf('l'), Character.valueOf('ʟ'));
/* 23 */     smallCapsMap.put(Character.valueOf('m'), Character.valueOf('ᴍ'));
/* 24 */     smallCapsMap.put(Character.valueOf('n'), Character.valueOf('ɴ'));
/* 25 */     smallCapsMap.put(Character.valueOf('o'), Character.valueOf('ᴏ'));
/* 26 */     smallCapsMap.put(Character.valueOf('p'), Character.valueOf('ᴘ'));
/* 27 */     smallCapsMap.put(Character.valueOf('q'), Character.valueOf('ǫ'));
/* 28 */     smallCapsMap.put(Character.valueOf('r'), Character.valueOf('ʀ'));
/* 29 */     smallCapsMap.put(Character.valueOf('s'), Character.valueOf('s'));
/* 30 */     smallCapsMap.put(Character.valueOf('t'), Character.valueOf('ᴛ'));
/* 31 */     smallCapsMap.put(Character.valueOf('u'), Character.valueOf('ᴜ'));
/* 32 */     smallCapsMap.put(Character.valueOf('v'), Character.valueOf('ᴠ'));
/* 33 */     smallCapsMap.put(Character.valueOf('w'), Character.valueOf('ᴡ'));
/* 34 */     smallCapsMap.put(Character.valueOf('x'), Character.valueOf('x'));
/* 35 */     smallCapsMap.put(Character.valueOf('y'), Character.valueOf('ʏ'));
/* 36 */     smallCapsMap.put(Character.valueOf('z'), Character.valueOf('ᴢ'));
/*    */   }
/*    */   
/*    */   public static String toSmallCaps(String text) {
/* 40 */     StringBuilder result = new StringBuilder();
/*    */     
/* 42 */     for (char ch : text.toLowerCase().toCharArray()) {
/* 43 */       result.append(smallCapsMap.getOrDefault(Character.valueOf(ch), Character.valueOf(ch)));
/*    */     }
/*    */     
/* 46 */     return result.toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\commo\\utility\SmallCapsUtility.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */