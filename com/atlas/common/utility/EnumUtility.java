/*    */ package com.atlas.common.utility;
/*    */ 
/*    */ import java.util.List;
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
/*    */ public final class EnumUtility
/*    */ {
/*    */   public static int nextIndex(int current, int size) {
/* 22 */     return (current + 1 < size) ? (current + 1) : 0;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static int nextIndex(@NotNull Enum<?> current) {
/* 33 */     return nextIndex(current.ordinal(), ((Enum[])current.getClass().getEnumConstants()).length);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static int previousIndex(int current, int size) {
/* 44 */     return (current - 1 >= 0) ? (current - 1) : (size - 1);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static int previousIndex(@NotNull Enum<?> current) {
/* 55 */     return previousIndex(current.ordinal(), ((Enum[])current.getClass().getEnumConstants()).length);
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
/*    */   @NotNull
/*    */   public static Enum<?> next(@NotNull Enum<?> current, Enum<?>... skips) {
/* 68 */     List<Enum<?>> skipList = List.of(skips);
/* 69 */     Enum<?> next = ((Enum[])current.getClass().getEnumConstants())[nextIndex(current)];
/* 70 */     while (skipList.contains(next))
/* 71 */       next = ((Enum[])current.getClass().getEnumConstants())[nextIndex(next)]; 
/* 72 */     return next;
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
/*    */   @NotNull
/*    */   public static Enum<?> previous(@NotNull Enum<?> current, Enum<?>... skips) {
/* 85 */     List<Enum<?>> skipList = List.of(skips);
/* 86 */     if (skipList.size() == ((Enum[])current.getClass().getEnumConstants()).length)
/* 87 */       throw new IllegalArgumentException("Cannot skip all enum values."); 
/* 88 */     Enum<?> previous = ((Enum[])current.getClass().getEnumConstants())[previousIndex(current)];
/* 89 */     while (skipList.contains(previous))
/* 90 */       previous = ((Enum[])current.getClass().getEnumConstants())[previousIndex(previous)]; 
/* 91 */     return previous;
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\commo\\utility\EnumUtility.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */