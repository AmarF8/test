/*    */ package com.atlas.common.serializer;
/*    */ 
/*    */ import java.io.ByteArrayInputStream;
/*    */ import java.io.ByteArrayOutputStream;
/*    */ import java.io.ObjectInputStream;
/*    */ import java.io.ObjectOutputStream;
/*    */ import java.util.Objects;
/*    */ import java.util.zip.GZIPInputStream;
/*    */ import java.util.zip.GZIPOutputStream;
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
/*    */ 
/*    */ 
/*    */ public final class GzipSerializer
/*    */ {
/*    */   @NotNull
/*    */   public static <T> T deserializeByte(byte[] bytes, @NotNull Class<T> clazz) {
/* 33 */     Objects.requireNonNull(clazz);
/*    */     
/*    */     try {
/* 36 */       ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
/* 37 */       GZIPInputStream gzipInputStream = new GZIPInputStream(byteArrayInputStream);
/* 38 */       ObjectInputStream objectInputStream = new ObjectInputStream(gzipInputStream);
/*    */       
/* 40 */       return (T)objectInputStream.readObject();
/* 41 */     } catch (Exception exception) {
/* 42 */       throw new IllegalArgumentException("Unable to deserialize byte.", exception);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static byte[] serializeByte(@NotNull Object object) {
/*    */     try {
/* 55 */       ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
/* 56 */       GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);
/* 57 */       ObjectOutputStream objectOutputStream = new ObjectOutputStream(gzipOutputStream);
/* 58 */       objectOutputStream.writeObject(object);
/* 59 */       objectOutputStream.close();
/* 60 */       return byteArrayOutputStream.toByteArray();
/* 61 */     } catch (Exception exception) {
/* 62 */       throw new IllegalArgumentException("Unable to serialize byte.", exception);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\serializer\GzipSerializer.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */