/*     */ package com.atlas.common.fabric.cardcollection.screen;
/*     */ 
/*     */ import java.io.InputStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import net.minecraft.class_2960;
/*     */ import net.minecraft.class_310;
/*     */ import net.minecraft.class_3298;
/*     */ import net.minecraft.class_332;
/*     */ import net.minecraft.class_4587;
/*     */ import net.minecraft.class_7833;
/*     */ import org.jetbrains.annotations.Nullable;
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
/*     */ public final class CardTextures
/*     */ {
/*     */   public static final float CARD_ASPECT = 0.718F;
/*     */   
/*     */   @Nullable
/*     */   public static class_2960 parse(String idString) {
/*  35 */     if (idString == null || idString.isBlank()) return null; 
/*     */     try {
/*  37 */       return class_2960.method_60654(idString);
/*  38 */     } catch (Exception e) {
/*  39 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*  43 */   private static final Map<class_2960, Float> ASPECT_CACHE = new HashMap<>();
/*  44 */   private static final Map<class_2960, Boolean> EXISTS_CACHE = new HashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float imageAspect(@Nullable class_2960 id, float def) {
/*  54 */     if (id == null) return def; 
/*  55 */     Float cached = ASPECT_CACHE.get(id);
/*  56 */     if (cached != null) return cached.floatValue(); 
/*  57 */     float aspect = def;
/*     */     try {
/*  59 */       Optional<class_3298> resource = class_310.method_1551().method_1478().method_14486(id);
/*  60 */       if (resource.isPresent())
/*  61 */       { InputStream in = ((class_3298)resource.get()).method_14482(); 
/*  62 */         try { byte[] header = in.readNBytes(24);
/*  63 */           if (header.length >= 24) {
/*  64 */             int w = (header[16] & 0xFF) << 24 | (header[17] & 0xFF) << 16 | (header[18] & 0xFF) << 8 | header[19] & 0xFF;
/*     */             
/*  66 */             int h = (header[20] & 0xFF) << 24 | (header[21] & 0xFF) << 16 | (header[22] & 0xFF) << 8 | header[23] & 0xFF;
/*     */             
/*  68 */             if (w > 0 && h > 0) aspect = w / h; 
/*     */           } 
/*  70 */           if (in != null) in.close();  } catch (Throwable throwable) { if (in != null)
/*     */             try { in.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }  } 
/*  72 */     } catch (Exception exception) {}
/*     */     
/*  74 */     ASPECT_CACHE.put(id, Float.valueOf(aspect));
/*  75 */     return aspect;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean exists(@Nullable class_2960 id) {
/*  80 */     if (id == null) return false; 
/*  81 */     Boolean cached = EXISTS_CACHE.get(id);
/*  82 */     if (cached != null) return cached.booleanValue(); 
/*  83 */     boolean exists = false;
/*     */     try {
/*  85 */       exists = class_310.method_1551().method_1478().method_14486(id).isPresent();
/*  86 */     } catch (Exception exception) {}
/*     */     
/*  88 */     EXISTS_CACHE.put(id, Boolean.valueOf(exists));
/*  89 */     return exists;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void drawFull(class_332 ctx, class_2960 tex, int x, int y, int w, int h) {
/*  95 */     ctx.method_25293(tex, x, y, w, h, 0.0F, 0.0F, 16, 16, 16, 16);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void drawAspect(class_332 ctx, class_2960 tex, int boxX, int boxY, int boxW, int boxH, float imgAspect) {
/* 105 */     int w = boxW;
/* 106 */     int h = Math.round(boxW / imgAspect);
/* 107 */     if (h > boxH) {
/* 108 */       h = boxH;
/* 109 */       w = Math.round(boxH * imgAspect);
/*     */     } 
/* 111 */     int x = boxX + (boxW - w) / 2;
/* 112 */     int y = boxY + (boxH - h) / 2;
/* 113 */     drawFull(ctx, tex, x, y, w, h);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void drawAspectRotated(class_332 ctx, class_2960 tex, int boxX, int boxY, int boxW, int boxH, float imgAspect, int quarterTurns) {
/* 125 */     int turns = (quarterTurns % 4 + 4) % 4;
/* 126 */     if (turns == 0) { drawAspect(ctx, tex, boxX, boxY, boxW, boxH, imgAspect); return; }
/*     */     
/* 128 */     boolean swap = (turns % 2 != 0);
/* 129 */     float displayAspect = swap ? (1.0F / imgAspect) : imgAspect;
/*     */     
/* 131 */     int dw = boxW;
/* 132 */     int dh = Math.round(boxW / displayAspect);
/* 133 */     if (dh > boxH) { dh = boxH; dw = Math.round(boxH * displayAspect); }
/*     */     
/* 135 */     int qw = swap ? dh : dw;
/* 136 */     int qh = swap ? dw : dh;
/*     */     
/* 138 */     float cx = boxX + boxW / 2.0F;
/* 139 */     float cy = boxY + boxH / 2.0F;
/*     */     
/* 141 */     class_4587 m = ctx.method_51448();
/* 142 */     m.method_22903();
/* 143 */     m.method_46416(cx, cy, 0.0F);
/* 144 */     m.method_22907(class_7833.field_40718.rotationDegrees(90.0F * turns));
/* 145 */     m.method_46416(-qw / 2.0F, -qh / 2.0F, 0.0F);
/* 146 */     drawFull(ctx, tex, 0, 0, qw, qh);
/* 147 */     m.method_22909();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void drawFullTinted(class_332 ctx, class_2960 tex, int x, int y, int w, int h, int argb) {
/* 152 */     ctx.method_51422((argb >> 16 & 0xFF) / 255.0F, (argb >> 8 & 0xFF) / 255.0F, (argb & 0xFF) / 255.0F, (argb >>> 24 & 0xFF) / 255.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 157 */     ctx.method_25293(tex, x, y, w, h, 0.0F, 0.0F, 16, 16, 16, 16);
/* 158 */     ctx.method_51422(1.0F, 1.0F, 1.0F, 1.0F);
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\cardcollection\screen\CardTextures.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */