/*    */ package com.atlas.common.utility;
/*    */ 
/*    */ import java.math.BigInteger;
/*    */ import org.jetbrains.annotations.NotNull;
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
/*    */ public final class MD5Utility
/*    */ {
/*    */   @NotNull
/*    */   public static String getHash(byte[] messageDigest) {
/* 23 */     BigInteger no = new BigInteger(1, messageDigest);
/*    */     
/* 25 */     StringBuilder hashText = new StringBuilder(no.toString(16));
/* 26 */     while (hashText.length() < 32) {
/* 27 */       hashText.insert(0, "0");
/*    */     }
/* 29 */     return hashText.toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\commo\\utility\MD5Utility.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */