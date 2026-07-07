/*    */ package com.atlas.common.utility;
/*    */ 
/*    */ import java.lang.reflect.Field;
/*    */ import java.lang.reflect.Method;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Arrays;
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
/*    */ 
/*    */ 
/*    */ public final class ClassUtility
/*    */ {
/*    */   @NotNull
/*    */   public static <T> List<Field> getFields(@NotNull T t) {
/* 29 */     ArrayList<Field> fields = new ArrayList<>(0);
/* 30 */     Class<? extends Object> clazz = (t instanceof Class) ? (Class<? extends Object>)t : (Class)t.getClass();
/* 31 */     while (clazz != Object.class) {
/* 32 */       fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
/* 33 */       clazz = (Class)clazz.getSuperclass();
/*    */     } 
/* 35 */     return fields;
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
/*    */   public static <T> List<Method> getMethods(@NotNull T t) {
/* 47 */     ArrayList<Method> methods = new ArrayList<>(0);
/* 48 */     Class<? extends Object> clazz = (t instanceof Class) ? (Class<? extends Object>)t : (Class)t.getClass();
/* 49 */     while (clazz != Object.class) {
/* 50 */       methods.addAll(Arrays.asList(clazz.getDeclaredMethods()));
/* 51 */       clazz = (Class)clazz.getSuperclass();
/*    */     } 
/* 53 */     return methods;
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\commo\\utility\ClassUtility.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */