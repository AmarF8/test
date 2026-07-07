/*    */ package com.atlas.common.utility;
/*    */ 
/*    */ import java.util.UUID;
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
/*    */ 
/*    */ public final class UUIDUtility
/*    */ {
/*    */   @NotNull
/*    */   public static UUID fromStringWithoutDashes(@NotNull String string) {
/* 24 */     if (string.length() != 32) throw new IllegalArgumentException("Invalid UUID string"); 
/* 25 */     return UUID.fromString((new StringBuilder(string))
/* 26 */         .insert(8, '-')
/* 27 */         .insert(13, '-')
/* 28 */         .insert(18, '-')
/* 29 */         .insert(23, '-').toString());
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\commo\\utility\UUIDUtility.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */