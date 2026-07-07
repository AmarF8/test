/*     */ package com.atlas.common.fabric.claim.screen;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.nio.file.Files;
/*     */ import java.nio.file.Path;
/*     */ import java.nio.file.attribute.FileAttribute;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import net.fabricmc.api.EnvType;
/*     */ import net.fabricmc.api.Environment;
/*     */ import net.minecraft.class_310;
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
/*     */ @Environment(EnvType.CLIENT)
/*     */ public final class ClaimMapCache
/*     */ {
/*     */   private static final int CHUNK_PIXELS = 256;
/*     */   private static final int ENTRY_BYTES = 1032;
/*     */   private static final int MAX_MEMORY_ENTRIES = 4096;
/*  32 */   private final Map<Long, int[]> memoryCache = (Map)new ConcurrentHashMap<>();
/*     */ 
/*     */ 
/*     */   
/*     */   private Path cacheFilePath;
/*     */ 
/*     */   
/*     */   private boolean diskLoaded = false;
/*     */ 
/*     */ 
/*     */   
/*     */   public void initialize() {
/*     */     try {
/*     */       String serverName;
/*  46 */       class_310 client = class_310.method_1551();
/*  47 */       Path gameDir = client.field_1697.toPath();
/*  48 */       Path cacheDir = gameDir.resolve("atlas_cache");
/*  49 */       Files.createDirectories(cacheDir, (FileAttribute<?>[])new FileAttribute[0]);
/*     */ 
/*     */ 
/*     */       
/*  53 */       if (client.method_1558() != null) {
/*     */         
/*  55 */         serverName = (client.method_1558()).field_3761.replaceAll("[^a-zA-Z0-9._-]", "_");
/*     */       } else {
/*  57 */         serverName = "singleplayer";
/*     */       } 
/*     */       
/*  60 */       this.cacheFilePath = cacheDir.resolve(serverName + "_chunks.dat");
/*     */       
/*  62 */       if (!this.diskLoaded) {
/*  63 */         loadFromDisk();
/*  64 */         this.diskLoaded = true;
/*     */       } 
/*  66 */     } catch (Exception e) {
/*     */       
/*  68 */       this.cacheFilePath = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void put(int chunkX, int chunkZ, int[] pixels) {
/*  79 */     if (pixels == null || pixels.length != 256) {
/*     */       return;
/*     */     }
/*  82 */     if (this.memoryCache.size() >= 4096) {
/*     */       
/*  84 */       int toRemove = 2048;
/*  85 */       Iterator<Long> iterator = this.memoryCache.keySet().iterator();
/*  86 */       while (toRemove > 0 && iterator.hasNext()) {
/*  87 */         iterator.next();
/*  88 */         iterator.remove();
/*  89 */         toRemove--;
/*     */       } 
/*     */     } 
/*     */     
/*  93 */     this.memoryCache.put(Long.valueOf(chunkKey(chunkX, chunkZ)), (int[])pixels.clone());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int[] get(int chunkX, int chunkZ) {
/* 101 */     return this.memoryCache.get(Long.valueOf(chunkKey(chunkX, chunkZ)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean has(int chunkX, int chunkZ) {
/* 108 */     return this.memoryCache.containsKey(Long.valueOf(chunkKey(chunkX, chunkZ)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void saveToDisk() {
/* 115 */     if (this.cacheFilePath == null || this.memoryCache.isEmpty())
/*     */       return; 
/*     */     
/* 118 */     try { DataOutputStream out = new DataOutputStream(new BufferedOutputStream(Files.newOutputStream(this.cacheFilePath, new java.nio.file.OpenOption[0])));
/*     */       
/* 120 */       try { out.writeInt(1);
/* 121 */         out.writeInt(this.memoryCache.size());
/*     */         
/* 123 */         for (Map.Entry<Long, int[]> entry : this.memoryCache.entrySet()) {
/* 124 */           long key = ((Long)entry.getKey()).longValue();
/* 125 */           int[] pixels = entry.getValue();
/* 126 */           out.writeInt((int)(key >> 32L));
/* 127 */           out.writeInt((int)key);
/* 128 */           for (int pixel : pixels) {
/* 129 */             out.writeInt(pixel);
/*     */           }
/*     */         } 
/* 132 */         out.close(); } catch (Throwable throwable) { try { out.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  throw throwable; }  } catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void loadFromDisk() {
/* 141 */     if (this.cacheFilePath == null || !Files.exists(this.cacheFilePath, new java.nio.file.LinkOption[0]))
/*     */       return; 
/*     */     
/* 144 */     try { DataInputStream in = new DataInputStream(new BufferedInputStream(Files.newInputStream(this.cacheFilePath, new java.nio.file.OpenOption[0]))); 
/* 145 */       try { int version = in.readInt();
/* 146 */         if (version != 1)
/*     */         
/*     */         { 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 158 */           in.close(); return; }  int count = in.readInt(); for (int i = 0; i < count && i < 4096; i++) { int chunkX = in.readInt(); int chunkZ = in.readInt(); int[] pixels = new int[256]; for (int p = 0; p < 256; p++) pixels[p] = in.readInt();  this.memoryCache.put(Long.valueOf(chunkKey(chunkX, chunkZ)), pixels); }  in.close(); } catch (Throwable throwable) { try { in.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  throw throwable; }  } catch (Exception e)
/*     */     
/* 160 */     { this.memoryCache.clear(); }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/* 168 */     this.memoryCache.clear();
/* 169 */     if (this.cacheFilePath != null) {
/* 170 */       try { Files.deleteIfExists(this.cacheFilePath); } catch (Exception exception) {}
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/* 178 */     return this.memoryCache.size();
/*     */   }
/*     */   
/*     */   private static long chunkKey(int x, int z) {
/* 182 */     return x << 32L | z & 0xFFFFFFFFL;
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\claim\screen\ClaimMapCache.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */