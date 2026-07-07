/*     */ package com.atlas.common.serializer;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.ObjectInputFilter;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Serializer
/*     */ {
/*  30 */   private static final Logger LOGGER = LoggerFactory.getLogger(Serializer.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  35 */   private static final String[] ALLOWED_PREFIXES = new String[] { "com.atlas.", "java.", "javax.", "kotlin.", "org.bson.", "net.kyori.", "com.google.common.", "com.google.gson.", "it.unimi.dsi.fastutil.", "[" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  49 */   private static final boolean ENFORCE = Boolean.parseBoolean(System.getenv().getOrDefault("ATLAS_SERIALIZER_FILTER_ENFORCE", "false"));
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  54 */   private static final Set<String> REPORTED = ConcurrentHashMap.newKeySet();
/*     */   static {
/*  56 */     FILTER = (filterInfo -> {
/*     */         Class<?> serialClass = filterInfo.serialClass();
/*     */         if (serialClass == null) {
/*     */           return ObjectInputFilter.Status.UNDECIDED;
/*     */         }
/*     */         String name = serialClass.getName();
/*     */         for (String prefix : ALLOWED_PREFIXES) {
/*     */           if (name.startsWith(prefix)) {
/*     */             return ObjectInputFilter.Status.ALLOWED;
/*     */           }
/*     */         } 
/*     */         if (ENFORCE) {
/*     */           LOGGER.error("Rejected deserialization of non-allowlisted class: {}", name);
/*     */           return ObjectInputFilter.Status.REJECTED;
/*     */         } 
/*     */         if (REPORTED.add(name)) {
/*     */           LOGGER.warn("Deserialization filter (observe mode): class {} is not on the allowlist. Add its prefix to Serializer.ALLOWED_PREFIXES before enabling ATLAS_SERIALIZER_FILTER_ENFORCE.", name);
/*     */         }
/*     */         return ObjectInputFilter.Status.UNDECIDED;
/*     */       });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final ObjectInputFilter FILTER;
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static <T> T deserializeByte(byte[] bytes) {
/*     */     try {
/*  88 */       ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(bytes));
/*  89 */       objectInputStream.setObjectInputFilter(FILTER);
/*  90 */       return (T)objectInputStream.readObject();
/*  91 */     } catch (Exception exception) {
/*  92 */       throw new IllegalArgumentException("Unable to deserialize byte.", exception);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static byte[] serializeByte(@NotNull Object object) {
/*     */     try {
/* 105 */       ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
/* 106 */       ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
/* 107 */       objectOutputStream.writeObject(object);
/* 108 */       objectOutputStream.flush();
/* 109 */       return byteArrayOutputStream.toByteArray();
/* 110 */     } catch (Exception exception) {
/* 111 */       throw new IllegalArgumentException("Unable to serialize byte.", exception);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\serializer\Serializer.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */