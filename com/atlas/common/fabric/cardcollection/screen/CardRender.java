/*     */ package com.atlas.common.fabric.cardcollection.screen;
/*     */ 
/*     */ import net.minecraft.class_2960;
/*     */ import net.minecraft.class_327;
/*     */ import net.minecraft.class_332;
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
/*     */ public final class CardRender
/*     */ {
/*     */   public static void panel(class_332 ctx, int x, int y, int w, int h, int bg, int border) {
/*  23 */     ctx.method_25294(x, y, x + w, y + h, bg);
/*  24 */     ctx.method_49601(x, y, w, h, border);
/*  25 */     ctx.method_25294(x + 1, y + 1, x + w - 1, y + 2, CardCollectionColors.lighten(bg, 14));
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
/*     */ 
/*     */   
/*     */   public static void card(class_332 ctx, class_327 tr, int x, int y, int w, int h, class_2960 tex, boolean owned, boolean hasArt, int rarityColor, int number, String shortName) {
/*  39 */     if (owned && hasArt) {
/*  40 */       CardTextures.drawAspect(ctx, tex, x, y, w, h, CardTextures.imageAspect(tex, 0.718F));
/*  41 */     } else if (owned) {
/*     */       
/*  43 */       ctx.method_25294(x + 1, y + 1, x + w - 1, y + h - 1, CardCollectionColors.darken(rarityColor, 80));
/*  44 */       centered(ctx, tr, "#" + number, x + w / 2, y + h / 2 - 10, CardCollectionColors.TEXT_WHITE);
/*  45 */       if (shortName != null) centered(ctx, tr, shortName, x + w / 2, y + h / 2 + 2, CardCollectionColors.TEXT_PRIMARY); 
/*  46 */       centered(ctx, tr, "art missing", x + w / 2, y + h - 12, CardCollectionColors.TEXT_MUTED);
/*  47 */       frame(ctx, x, y, w, h, rarityColor, 2);
/*     */     } else {
/*     */       
/*  50 */       cardBack(ctx, tr, x, y, w, h);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void cardBack(class_332 ctx, class_327 tr, int x, int y, int w, int h) {
/*  56 */     ctx.method_25294(x, y, x + w, y + h, CardCollectionColors.CARD_BACK_BG);
/*     */     
/*  58 */     ctx.method_25294(x + w / 2 - 1, y + 3, x + w / 2 + 1, y + h - 3, CardCollectionColors.lighten(CardCollectionColors.CARD_BACK_BG, 16));
/*     */     
/*  60 */     frame(ctx, x + 3, y + 3, w - 6, h - 6, CardCollectionColors.ACCENT_GOLD_DIM, 1);
/*     */     
/*  62 */     int cx = x + w / 2;
/*  63 */     int cy = y + h / 2;
/*  64 */     int r = Math.min(w, h) / 6;
/*  65 */     diamond(ctx, cx, cy, r, CardCollectionColors.ACCENT_GOLD_DIM);
/*  66 */     diamond(ctx, cx, cy, r - 3, CardCollectionColors.darken(CardCollectionColors.CARD_BACK_BG, 18));
/*  67 */     ctx.method_25294(x, y, x + w, y + h, CardCollectionColors.withAlpha(-16777216, 36));
/*  68 */     frame(ctx, x, y, w, h, CardCollectionColors.BORDER_DIM, 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void frame(class_332 ctx, int x, int y, int w, int h, int color, int t) {
/*  73 */     ctx.method_25294(x, y, x + w, y + t, color);
/*  74 */     ctx.method_25294(x, y + h - t, x + w, y + h, color);
/*  75 */     ctx.method_25294(x, y, x + t, y + h, color);
/*  76 */     ctx.method_25294(x + w - t, y, x + w, y + h, color);
/*     */   }
/*     */ 
/*     */   
/*     */   private static void diamond(class_332 ctx, int cx, int cy, int r, int color) {
/*  81 */     for (int dy = -r; dy <= r; dy++) {
/*  82 */       int dx = r - Math.abs(dy);
/*  83 */       ctx.method_25294(cx - dx, cy + dy, cx + dx + 1, cy + dy + 1, color);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void numberPlate(class_332 ctx, class_327 tr, int x, int y, int number, boolean owned) {
/*  89 */     String num = "#" + number;
/*  90 */     int w = tr.method_1727(num) + 4;
/*  91 */     ctx.method_25294(x, y, x + w, y + 10, CardCollectionColors.withAlpha(-16777216, 190));
/*  92 */     ctx.method_25294(x, y, x + w, y + 1, CardCollectionColors.withAlpha(CardCollectionColors.ACCENT_GOLD, owned ? 200 : 60));
/*  93 */     ctx.method_51433(tr, num, x + 2, y + 1, owned ? CardCollectionColors.TEXT_PRIMARY : CardCollectionColors.TEXT_MUTED, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void countBadge(class_332 ctx, class_327 tr, int x, int y, int w, int h, int count) {
/*  98 */     if (count <= 1)
/*  99 */       return;  String badge = "x" + count;
/* 100 */     int bw = tr.method_1727(badge) + 5;
/* 101 */     int bx = x + w - bw - 2;
/* 102 */     int by = y + h - 11;
/* 103 */     ctx.method_25294(bx, by, bx + bw, by + 9, CardCollectionColors.withAlpha(-16777216, 210));
/* 104 */     ctx.method_49601(bx, by, bw, 9, CardCollectionColors.ACCENT_GOLD_DIM);
/* 105 */     ctx.method_51433(tr, badge, bx + 3, by + 1, CardCollectionColors.ACCENT_GOLD, false);
/*     */   }
/*     */   
/*     */   private static void centered(class_332 ctx, class_327 tr, String s, int cx, int y, int color) {
/* 109 */     ctx.method_25300(tr, s, cx, y, color);
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\cardcollection\screen\CardRender.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */