/*     */ package com.atlas.common.fabric.cardcollection.screen;
/*     */ 
/*     */ import com.atlas.common.fabric.cardcollection.client.CardCollectionDto;
/*     */ import java.util.ArrayDeque;
/*     */ import java.util.Deque;
/*     */ import net.fabricmc.api.EnvType;
/*     */ import net.fabricmc.api.Environment;
/*     */ import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
/*     */ import net.minecraft.class_1109;
/*     */ import net.minecraft.class_1113;
/*     */ import net.minecraft.class_310;
/*     */ import net.minecraft.class_327;
/*     */ import net.minecraft.class_332;
/*     */ import net.minecraft.class_3417;
/*     */ import net.minecraft.class_4587;
/*     */ import net.minecraft.class_9779;
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
/*     */ @Environment(EnvType.CLIENT)
/*     */ public final class CardCollectionToast
/*     */ {
/*     */   private static final long DURATION_MS = 4500L;
/*     */   private static final long ANIM_MS = 350L;
/*     */   private static final int WIDTH = 220;
/*     */   private static final int HEIGHT = 36;
/*     */   private static final int TOP_MARGIN = 8;
/*  37 */   private static final Deque<CardCollectionDto.MilestoneInfo> QUEUE = new ArrayDeque<>();
/*  38 */   private static CardCollectionDto.MilestoneInfo current = null;
/*  39 */   private static long shownAt = 0L;
/*     */ 
/*     */   
/*     */   public static void register() {
/*  43 */     HudRenderCallback.EVENT.register((ctx, tick) -> {
/*     */           class_310 client = class_310.method_1551();
/*     */           if (client.field_1755 instanceof CardCollectionScreen || client.field_1755 instanceof BinderScreen || client.field_1755 instanceof PackOpeningScreen) {
/*     */             return;
/*     */           }
/*     */           renderOverlay(ctx);
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void show(String json) {
/*  56 */     CardCollectionDto.MilestoneInfo info = CardCollectionDto.parseMilestone(json);
/*  57 */     if (info == null)
/*  58 */       return;  QUEUE.addLast(info);
/*  59 */     playChime();
/*     */   }
/*     */   
/*     */   public static void renderOverlay(class_332 ctx) {
/*  63 */     class_310 client = class_310.method_1551();
/*  64 */     if (client.field_1690.field_1842)
/*     */       return; 
/*  66 */     long now = System.currentTimeMillis();
/*     */     
/*  68 */     if (current == null) {
/*  69 */       current = QUEUE.pollFirst();
/*  70 */       if (current == null)
/*  71 */         return;  shownAt = now;
/*     */     } 
/*     */     
/*  74 */     long age = now - shownAt;
/*  75 */     if (age > 4500L) {
/*  76 */       current = null;
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/*  81 */     float in = Math.min(1.0F, (float)age / 350.0F);
/*     */     
/*  83 */     float out = (age > 4150L) ? Math.max(0.0F, (float)(4500L - age) / 350.0F) : 1.0F;
/*  84 */     float appear = Math.min(in, out);
/*  85 */     float eased = appear * appear * (3.0F - 2.0F * appear);
/*     */     
/*  87 */     int screenW = client.method_22683().method_4486();
/*  88 */     int x = (screenW - 220) / 2;
/*  89 */     int y = (int)(8.0F - (1.0F - eased) * 48.0F);
/*  90 */     int alpha = (int)(eased * 255.0F);
/*     */     
/*  92 */     class_4587 matrices = ctx.method_51448();
/*  93 */     matrices.method_22903();
/*  94 */     matrices.method_46416(0.0F, 0.0F, 400.0F);
/*     */ 
/*     */     
/*  97 */     ctx.method_25294(x - 1, y - 1, x + 220 + 1, y + 36 + 1, CardCollectionColors.withAlpha(-16777216, (int)(alpha * 0.85F)));
/*  98 */     ctx.method_25294(x, y, x + 220, y + 36, CardCollectionColors.withAlpha(CardCollectionColors.PANEL_BG, alpha));
/*  99 */     ctx.method_49601(x, y, 220, 36, CardCollectionColors.withAlpha(CardCollectionColors.ACCENT_GOLD, alpha));
/*     */     
/* 101 */     ctx.method_25294(x, y, x + 220, y + 2, CardCollectionColors.withAlpha(CardCollectionColors.ACCENT_GOLD, alpha));
/*     */     
/* 103 */     class_327 tr = client.field_1772;
/* 104 */     int textColor = CardCollectionColors.withAlpha(CardCollectionColors.TEXT_GOLD, alpha);
/* 105 */     int dimColor = CardCollectionColors.withAlpha(CardCollectionColors.TEXT_PRIMARY, alpha);
/*     */     
/* 107 */     String title = "★ " + current.setName() + " — " + current.percent() + "% Complete!";
/* 108 */     ctx.method_51433(tr, title, x + 8, y + 6, textColor, true);
/* 109 */     String reward = current.reward();
/* 110 */     if (reward != null && !reward.isBlank()) {
/* 111 */       ctx.method_51433(tr, reward, x + 8, y + 20, dimColor, false);
/*     */     }
/*     */     
/* 114 */     matrices.method_22909();
/*     */   }
/*     */   
/*     */   private static void playChime() {
/*     */     try {
/* 119 */       class_310.method_1551().method_1483().method_4873(
/* 120 */           (class_1113)class_1109.method_4758(class_3417.field_15195, 1.0F));
/* 121 */     } catch (Exception exception) {}
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\cardcollection\screen\CardCollectionToast.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */