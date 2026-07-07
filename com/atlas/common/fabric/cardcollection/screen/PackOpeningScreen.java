/*     */ package com.atlas.common.fabric.cardcollection.screen;
/*     */ 
/*     */ import com.atlas.common.fabric.cardcollection.client.CardCollectionClientData;
/*     */ import com.atlas.common.fabric.cardcollection.client.CardCollectionDto;
/*     */ import com.atlas.common.fabric.cardcollection.network.CardCollectionNetwork;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.class_1109;
/*     */ import net.minecraft.class_1113;
/*     */ import net.minecraft.class_2561;
/*     */ import net.minecraft.class_2583;
/*     */ import net.minecraft.class_2960;
/*     */ import net.minecraft.class_310;
/*     */ import net.minecraft.class_332;
/*     */ import net.minecraft.class_3414;
/*     */ import net.minecraft.class_3417;
/*     */ import net.minecraft.class_437;
/*     */ import net.minecraft.class_4587;
/*     */ import net.minecraft.class_6880;
/*     */ import net.minecraft.class_7833;
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
/*     */ public class PackOpeningScreen
/*     */   extends class_437
/*     */ {
/*     */   private static final int NORMAL_COUNT = 5;
/*     */   private static final int MAX_COUNT = 7;
/*     */   private static final int MIN_CARD_W = 78;
/*     */   private static final int MAX_CARD_W = 124;
/*     */   private static final int SPACING = 20;
/*     */   private static final int PACK_W = 88;
/*     */   private static final int PACK_H = 126;
/*     */   private static final int MAX_PARTICLES = 180;
/*  47 */   private final boolean[] clicked = new boolean[7];
/*  48 */   private final float[] shakeTicks = new float[7];
/*  49 */   private final float[] flipProgress = new float[7];
/*  50 */   private final boolean[] introSound = new boolean[7];
/*  51 */   private final boolean[] revealEffect = new boolean[7];
/*     */   
/*  53 */   private float ticks = 0.0F;
/*  54 */   private long lastTime = 0L;
/*  55 */   private float cameraShake = 0.0F;
/*     */   
/*  57 */   private final List<Particle> particles = new ArrayList<>();
/*  58 */   private final Random rng = new Random(); private int binderBtnX; private int anotherBtnX;
/*     */   private int closeBtnX;
/*     */   private int btnY;
/*  61 */   private int btnW = 96; private int btnH = 18;
/*     */   
/*     */   private boolean ackSent = false;
/*     */   
/*     */   private static final int VERTICAL_LIFT = 30;
/*     */   
/*     */   private int belowCardsY;
/*     */   
/*     */   private int cardsTopY;
/*  70 */   private final List<PendingSound> pendingSounds = new ArrayList<>();
/*  71 */   private final List<Ring> rings = new ArrayList<>();
/*  72 */   private final float[] revealTick = new float[7];
/*  73 */   private float screenFlash = 0.0F;
/*  74 */   private int flashColor = -1;
/*     */   
/*     */   public PackOpeningScreen() {
/*  77 */     super((class_2561)class_2561.method_43470("Opening Pack"));
/*  78 */     Arrays.fill(this.revealTick, -1.0F);
/*     */   }
/*     */   
/*     */   public boolean method_25421() {
/*  82 */     return false;
/*     */   }
/*     */   public void method_25420(class_332 ctx, int mouseX, int mouseY, float delta) {}
/*     */   
/*     */   private CardCollectionDto.PackData data() {
/*  87 */     return CardCollectionClientData.getInstance().getPack();
/*     */   }
/*     */   
/*     */   public void method_25394(class_332 ctx, int mouseX, int mouseY, float delta) {
/*  91 */     long now = System.currentTimeMillis();
/*  92 */     if (this.lastTime == 0L) this.lastTime = now; 
/*  93 */     float dt = Math.min(4.0F, (float)(now - this.lastTime) / 50.0F);
/*  94 */     this.lastTime = now;
/*  95 */     this.ticks += dt;
/*     */     
/*  97 */     ctx.method_25294(0, 0, this.field_22789, this.field_22790, CardCollectionColors.withAlpha(-16777216, 200));
/*     */     
/*  99 */     flushScheduledSounds(now);
/* 100 */     if (this.screenFlash > 0.0F) this.screenFlash = Math.max(0.0F, this.screenFlash - dt * 0.1F);
/*     */     
/* 102 */     CardCollectionDto.PackData pack = data();
/* 103 */     if (pack == null || pack.cards().isEmpty()) {
/* 104 */       ctx.method_25300(this.field_22793, "Opening pack…", this.field_22789 / 2, this.field_22790 / 2, CardCollectionColors.TEXT_DIM);
/* 105 */       super.method_25394(ctx, mouseX, mouseY, delta);
/* 106 */       CardCollectionToast.renderOverlay(ctx);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 111 */     class_4587 matrices = ctx.method_51448();
/* 112 */     matrices.method_22903();
/* 113 */     if (this.cameraShake > 0.0F) {
/* 114 */       this.cameraShake -= dt;
/* 115 */       float intensity = Math.max(0.0F, 3.5F * this.cameraShake / 14.0F);
/* 116 */       matrices.method_46416((this.rng.nextFloat() - 0.5F) * intensity, (this.rng.nextFloat() - 0.5F) * intensity, 0.0F);
/*     */     } 
/*     */     
/* 119 */     int count = slotCount(pack);
/* 120 */     int cardW = cardWidth(count);
/* 121 */     int cardH = cardHeight(cardW);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 126 */     class_2960 packTex = CardTextures.parse(pack.packTexture());
/* 127 */     float packAspect = CardTextures.imageAspect(packTex, 0.6984127F);
/* 128 */     int packDrawW = 88;
/* 129 */     int packDrawH = Math.round(packDrawW / packAspect);
/* 130 */     if (packDrawH > 126) { packDrawH = 126; packDrawW = Math.round(packDrawH * packAspect); }
/*     */     
/* 132 */     int gapPackTitle = 5;
/* 133 */     int titleH = 9;
/* 134 */     int gapTitleCards = 16;
/*     */ 
/*     */     
/* 137 */     int gapCardsHint = 30;
/* 138 */     int hintH = 10;
/*     */     
/* 140 */     int contentH = packDrawH + 5 + 9 + 16 + cardH + 30 + 10;
/* 141 */     int top = Math.max(8, (this.field_22790 - contentH) / 2 - 30);
/*     */     
/* 143 */     int packX = this.field_22789 / 2 - packDrawW / 2;
/* 144 */     if (CardTextures.exists(packTex)) {
/* 145 */       CardTextures.drawAspect(ctx, packTex, packX, top, packDrawW, packDrawH, packAspect);
/*     */     }
/* 147 */     int titleY = top + packDrawH + 5;
/* 148 */     ctx.method_25300(this.field_22793, pack.setName() + " Booster Pack", this.field_22789 / 2, titleY, CardCollectionColors.ACCENT_GOLD);
/*     */     
/* 150 */     boolean allRevealed = true;
/*     */     
/* 152 */     int totalW = count * cardW + (count - 1) * 20;
/* 153 */     int startX = (this.field_22789 - totalW) / 2;
/* 154 */     int cardsTop = titleY + 9 + 16;
/* 155 */     int centerY = cardsTop + cardH / 2;
/* 156 */     this.belowCardsY = cardsTop + cardH + 30;
/* 157 */     this.cardsTopY = cardsTop;
/*     */     
/* 159 */     List<CardCollectionDto.PackCard> cards = pack.cards();
/* 160 */     for (int i = 0; i < count; i++) {
/* 161 */       CardCollectionDto.PackCard card = (i < cards.size()) ? cards.get(i) : null;
/*     */ 
/*     */       
/* 164 */       float intro = clamp((this.ticks - i * 3.0F) / 12.0F);
/* 165 */       float easedIntro = smooth(intro);
/* 166 */       if (intro > 0.0F && !this.introSound[i]) {
/* 167 */         this.introSound[i] = true;
/* 168 */         play(class_3417.field_17481, 1.3F + i * 0.12F, 0.7F);
/*     */       } 
/*     */ 
/*     */       
/* 172 */       if (this.clicked[i])
/* 173 */         if (this.shakeTicks[i] > 0.0F) {
/* 174 */           this.shakeTicks[i] = this.shakeTicks[i] - dt;
/* 175 */           if (this.shakeTicks[i] <= 0.0F) triggerReveal(i, card, startX + i * (cardW + 20) + cardW / 2, centerY); 
/* 176 */         } else if (this.flipProgress[i] < 1.0F) {
/* 177 */           this.flipProgress[i] = this.flipProgress[i] + flipSpeed(card) * dt;
/* 178 */           if (this.flipProgress[i] > 1.0F) this.flipProgress[i] = 1.0F;
/*     */         
/*     */         }  
/* 181 */       if (!this.clicked[i] || this.flipProgress[i] < 1.0F) allRevealed = false;
/*     */       
/* 183 */       int cardX = startX + i * (cardW + 20);
/* 184 */       float introOffset = (1.0F - easedIntro) * 220.0F;
/* 185 */       drawCard(ctx, card, i, cardX, (int)(centerY - cardH / 2.0F + introOffset), cardW, cardH, easedIntro, mouseX, mouseY);
/*     */     } 
/*     */ 
/*     */     
/* 189 */     updateRings(ctx, dt);
/* 190 */     updateParticles(ctx, dt);
/*     */     
/* 192 */     matrices.method_22909();
/*     */ 
/*     */     
/* 195 */     if (this.screenFlash > 0.0F) {
/* 196 */       int a = (int)(Math.min(1.0F, this.screenFlash) * 150.0F);
/* 197 */       ctx.method_25294(0, 0, this.field_22789, this.field_22790, CardCollectionColors.withAlpha(this.flashColor, a));
/*     */     } 
/*     */ 
/*     */     
/* 201 */     if (allRevealed && allClicked()) {
/* 202 */       drawSummary(ctx, pack, mouseX, mouseY);
/*     */     } else {
/* 204 */       ctx.method_25300(this.field_22793, "Click each card to reveal it", this.field_22789 / 2, this.belowCardsY, CardCollectionColors.TEXT_DIM);
/*     */     } 
/*     */ 
/*     */     
/* 208 */     super.method_25394(ctx, mouseX, mouseY, delta);
/* 209 */     CardCollectionToast.renderOverlay(ctx);
/*     */   }
/*     */ 
/*     */   
/*     */   private void drawCard(class_332 ctx, CardCollectionDto.PackCard card, int index, int x, int y, int cardW, int cardH, float easedIntro, int mouseX, int mouseY) {
/* 214 */     if (easedIntro <= 0.0F)
/*     */       return; 
/* 216 */     float flip = smooth(this.flipProgress[index]);
/* 217 */     boolean faceUp = (flip >= 0.5F);
/* 218 */     float widthFactor = Math.abs((float)Math.cos((1.0F - flip) * Math.PI));
/* 219 */     if (widthFactor < 0.02F) widthFactor = 0.02F;
/*     */     
/* 221 */     int rc = (card != null) ? CardCollectionColors.rarityColor(card.rarity()) : CardCollectionColors.BORDER;
/* 222 */     int tier = (card != null) ? CardCollectionColors.rarityTier(card.rarity()) : 0;
/*     */     
/* 224 */     class_4587 m = ctx.method_51448();
/* 225 */     m.method_22903();
/* 226 */     int cx = x + cardW / 2;
/* 227 */     int cy = y + cardH / 2;
/* 228 */     m.method_46416(cx, cy, (100 + index));
/*     */ 
/*     */     
/* 231 */     if (this.clicked[index] && this.shakeTicks[index] > 0.0F) {
/* 232 */       float prog = 1.0F - this.shakeTicks[index] / shakeDuration(card);
/* 233 */       float intensity = (1.5F + prog * 2.5F) * ((tier >= 2) ? 1.8F : ((tier >= 1) ? 1.3F : 1.0F));
/* 234 */       m.method_46416((this.rng.nextFloat() - 0.5F) * intensity, (this.rng.nextFloat() - 0.5F) * intensity, 0.0F);
/* 235 */       float sparkChance = 0.1F + prog * ((tier >= 2) ? 0.36F : ((tier >= 1) ? 0.22F : 0.06F));
/* 236 */       if (this.rng.nextFloat() < sparkChance) spawnShakeSpark(cx, cy, rc, tier);
/*     */     
/*     */     } 
/*     */     
/* 240 */     float pop = revealPop(index);
/* 241 */     if (pop != 1.0F) m.method_22905(pop, pop, 1.0F);
/*     */ 
/*     */     
/* 244 */     if (faceUp && this.flipProgress[index] >= 1.0F && tier >= 1) {
/* 245 */       drawGlow(ctx, rc, tier);
/*     */     }
/*     */ 
/*     */     
/* 249 */     m.method_22903();
/* 250 */     m.method_22905(widthFactor, 1.0F, 1.0F);
/* 251 */     int dw = cardW;
/* 252 */     int dh = cardH;
/* 253 */     int dx = -dw / 2;
/* 254 */     int dy = -dh / 2;
/*     */     
/* 256 */     if (!faceUp) {
/* 257 */       CardRender.cardBack(ctx, this.field_22793, dx, dy, dw, dh);
/* 258 */     } else if (card != null) {
/* 259 */       class_2960 tex = CardTextures.parse(card.texture());
/* 260 */       boolean hasArt = CardTextures.exists(tex);
/* 261 */       if (hasArt) {
/* 262 */         CardTextures.drawFull(ctx, tex, dx, dy, dw, dh);
/*     */       } else {
/* 264 */         drawFacePlaceholder(ctx, dx, dy, dw, dh, card);
/*     */       } 
/*     */     } 
/* 267 */     m.method_22909();
/* 268 */     m.method_22909();
/*     */ 
/*     */     
/* 271 */     if (faceUp && this.flipProgress[index] >= 1.0F && tier >= 1) {
/*     */       
/* 273 */       if (tier >= 2) {
/* 274 */         int hue = (int)(this.ticks * 4.0F) % 360;
/* 275 */         int pulse = 2 + (int)(1.5D * (1.0D + Math.sin((this.ticks * 0.2F))));
/* 276 */         CardRender.frame(ctx, x - pulse, y - pulse, cardW + 2 * pulse, cardH + 2 * pulse, 
/* 277 */             CardCollectionColors.withAlpha(hsv(hue, 0.7F, 1.0F), 170), 2);
/*     */       } 
/*     */       
/* 280 */       float sweep = (this.ticks * 0.9F + index * 24.0F) % 90.0F / 90.0F;
/* 281 */       int bandX = x - 10 + (int)(sweep * (cardW + 20));
/* 282 */       ctx.method_44379(x, y, x + cardW, y + cardH);
/* 283 */       int maxA = (tier >= 2) ? 80 : 45;
/* 284 */       for (int dxo = -3; dxo <= 3; dxo++) {
/* 285 */         int a = (int)((1.0F - Math.abs(dxo) / 3.0F) * maxA);
/* 286 */         ctx.method_25294(bandX + dxo, y, bandX + dxo + 1, y + cardH, CardCollectionColors.withAlpha(-1, a));
/*     */       } 
/* 288 */       ctx.method_44380();
/*     */     } 
/*     */ 
/*     */     
/* 292 */     if (faceUp && this.flipProgress[index] >= 1.0F && card != null) {
/* 293 */       boolean hover = (mouseX >= x && mouseX < x + cardW && mouseY >= y && mouseY < y + cardH);
/* 294 */       if (card.isNew()) {
/* 295 */         ctx.method_25294(x - 2, y - 8, x + 26, y + 2, CardCollectionColors.withAlpha(CardCollectionColors.ACCENT_GREEN, 230));
/* 296 */         ctx.method_51433(this.field_22793, "NEW", x + 2, y - 7, -15458288, false);
/*     */       } 
/* 298 */       ctx.method_25300(this.field_22793, card.rarityName(), x + cardW / 2, y + cardH + 2, rc);
/*     */       
/* 300 */       if (card.hasSerial()) {
/* 301 */         String mint = "✦ MINT #" + card.serial() + " / " + card.minted();
/* 302 */         ctx.method_25300(this.field_22793, mint, x + cardW / 2, y + cardH + 12, CardCollectionColors.ACCENT_GOLD);
/*     */       } 
/* 304 */       if (hover) {
/* 305 */         List<class_2561> lines = new ArrayList<>();
/* 306 */         lines.add(class_2561.method_43470(card.name()).method_27694(s -> s.method_36139(15655630).method_10982(Boolean.valueOf(true))));
/* 307 */         lines.add(class_2561.method_43470("#" + card.number() + " • " + card.rarityName())
/* 308 */             .method_27694(s -> s.method_36139(rc & 0xFFFFFF)));
/* 309 */         if (card.hasSerial()) {
/* 310 */           lines.add(class_2561.method_43470("✦ Mint #" + card.serial() + " / " + card.minted())
/* 311 */               .method_27694(s -> s.method_36139(16765518).method_10982(Boolean.valueOf(true))));
/*     */         }
/* 313 */         lines.add(class_2561.method_43470("Owned: " + card.owned()).method_27694(s -> s.method_36139(11047542)));
/* 314 */         ctx.method_51434(this.field_22793, lines, mouseX, mouseY);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void drawFacePlaceholder(class_332 ctx, int x, int y, int w, int h, CardCollectionDto.PackCard card) {
/* 320 */     ctx.method_25294(x, y, x + w, y + h, CardCollectionColors.darken(CardCollectionColors.rarityColor(card.rarity()), 110));
/* 321 */     ctx.method_25300(this.field_22793, "#" + card.number(), x + w / 2, y + h / 2 - 8, CardCollectionColors.TEXT_PRIMARY);
/* 322 */     ctx.method_25300(this.field_22793, card.name(), x + w / 2, y + h / 2 + 4, CardCollectionColors.TEXT_DIM);
/*     */   }
/*     */   
/*     */   private void drawGlow(class_332 ctx, int color, int tier) {
/* 326 */     class_4587 m = ctx.method_51448();
/*     */     
/* 328 */     if (tier >= 2) {
/* 329 */       int rays = 8;
/* 330 */       float len = 78.0F + (float)Math.sin((this.ticks * 0.1F)) * 14.0F;
/* 331 */       int rayColor = CardCollectionColors.withAlpha(color, 55);
/* 332 */       m.method_22903();
/* 333 */       m.method_22907(class_7833.field_40718.rotationDegrees(this.ticks * 1.1F));
/* 334 */       for (int i = 0; i < rays; i++) {
/* 335 */         m.method_22903();
/* 336 */         m.method_22907(class_7833.field_40718.rotationDegrees(i * 360.0F / rays));
/* 337 */         ctx.method_25294(-6, (int)-len, 6, (int)len, rayColor);
/* 338 */         m.method_22909();
/*     */       } 
/*     */       
/* 341 */       m.method_22907(class_7833.field_40718.rotationDegrees(-this.ticks * 1.9F));
/* 342 */       int rayColor2 = CardCollectionColors.withAlpha(-1, 30);
/* 343 */       for (int j = 0; j < rays; j++) {
/* 344 */         m.method_22903();
/* 345 */         m.method_22907(class_7833.field_40718.rotationDegrees(j * 360.0F / rays + 15.0F));
/* 346 */         ctx.method_25294(-2, (int)-(len * 0.8F), 2, (int)(len * 0.8F), rayColor2);
/* 347 */         m.method_22909();
/*     */       } 
/* 349 */       m.method_22909();
/*     */     } 
/*     */     
/* 352 */     int haloAlpha = ((tier >= 2) ? 40 : 26) + (int)(16.0D * Math.sin((this.ticks * 0.15F)));
/* 353 */     int halo = CardCollectionColors.withAlpha(color, Math.max(0, haloAlpha));
/* 354 */     float size = ((tier >= 2) ? 52.0F : 38.0F) + (float)Math.sin((this.ticks * 0.05F)) * 5.0F;
/* 355 */     ctx.method_25294((int)-size, (int)-size, (int)size, (int)size, halo);
/*     */   }
/*     */ 
/*     */   
/*     */   private void beginReveal(int index, CardCollectionDto.PackCard card) {
/*     */     long span;
/*     */     int s;
/* 362 */     this.clicked[index] = true;
/* 363 */     this.shakeTicks[index] = shakeDuration(card);
/* 364 */     int tier = (card != null) ? CardCollectionColors.rarityTier(card.rarity()) : 0;
/*     */     
/* 366 */     switch (tier) {
/*     */       
/*     */       case 2:
/* 369 */         play(class_3417.field_14703, 0.7F, 0.6F);
/* 370 */         play(class_3417.field_15203, 0.6F, 0.25F);
/* 371 */         span = (long)(this.shakeTicks[index] * 50.0F);
/* 372 */         for (s = 0; s < 5; s++) {
/* 373 */           float pitch = 0.7F + s * 0.18F;
/* 374 */           schedule(span * s / 5L, () -> play((class_6880<class_3414>)class_3417.field_14793, pitch, 0.5F));
/*     */         } 
/*     */         return;
/*     */       case 1:
/* 378 */         play((class_6880<class_3414>)class_3417.field_15015, 0.9F, 0.5F);
/* 379 */         span = (long)(this.shakeTicks[index] * 50.0F);
/* 380 */         for (s = 0; s < 3; s++) {
/* 381 */           float pitch = 1.0F + s * 0.2F;
/* 382 */           schedule(span * s / 3L, () -> play((class_6880<class_3414>)class_3417.field_14725, pitch, 0.4F));
/*     */         }  return;
/*     */     } 
/* 385 */     play((class_6880<class_3414>)class_3417.field_15015, 1.2F, 0.5F);
/*     */   }
/*     */   
/*     */   private void triggerReveal(int index, CardCollectionDto.PackCard card, int cx, int cy) {
/*     */     int count;
/* 390 */     if (this.revealEffect[index])
/* 391 */       return;  this.revealEffect[index] = true;
/* 392 */     this.revealTick[index] = this.ticks;
/* 393 */     int tier = (card != null) ? CardCollectionColors.rarityTier(card.rarity()) : 0;
/* 394 */     int color = (card != null) ? CardCollectionColors.rarityColor(card.rarity()) : CardCollectionColors.TEXT_WHITE;
/*     */ 
/*     */     
/* 397 */     switch (tier) {
/*     */       case 2:
/* 399 */         count = 72;
/* 400 */         this.cameraShake = 16.0F;
/* 401 */         this.screenFlash = 1.0F;
/* 402 */         this.flashColor = color;
/* 403 */         spawnRing(cx, cy, color, 150.0F, 26.0F);
/* 404 */         spawnRing(cx, cy, -1, 96.0F, 18.0F);
/*     */         
/* 406 */         play(class_3417.field_15195, 1.0F, 1.0F);
/* 407 */         play(class_3417.field_15188, 1.0F, 0.9F);
/* 408 */         play(class_3417.field_14865, 1.5F, 0.4F);
/* 409 */         schedule(90L, () -> play(class_3417.field_14800, 1.2F, 0.7F));
/* 410 */         schedule(160L, () -> play(class_3417.field_14709, 0.9F, 0.7F));
/*     */         break;
/*     */       case 1:
/* 413 */         count = 36;
/* 414 */         this.cameraShake = 7.0F;
/* 415 */         this.screenFlash = 0.4F;
/* 416 */         this.flashColor = color;
/* 417 */         spawnRing(cx, cy, color, 92.0F, 18.0F);
/* 418 */         play(class_3417.field_26980, 1.2F, 0.9F);
/* 419 */         play(class_3417.field_14800, 1.0F, 0.6F);
/* 420 */         schedule(110L, () -> play(class_3417.field_14627, 1.3F, 0.6F));
/*     */         break;
/*     */       default:
/* 423 */         count = 14;
/* 424 */         spawnRing(cx, cy, color, 54.0F, 12.0F);
/* 425 */         play(class_3417.field_15197, 1.0F, 0.6F);
/*     */         break;
/*     */     } 
/* 428 */     for (int p = 0; p < count; p++) {
/* 429 */       float angle = this.rng.nextFloat() * 2.0F * 3.1415927F;
/* 430 */       float speed = 2.0F + this.rng.nextFloat() * ((tier >= 2) ? 9.0F : 6.0F);
/* 431 */       float vx = (float)Math.cos(angle) * speed;
/* 432 */       float vy = (float)Math.sin(angle) * speed - 2.5F;
/* 433 */       int pc = (tier >= 2 && this.rng.nextFloat() < 0.4F) ? -1 : color;
/* 434 */       addParticle(new Particle(cx, cy, vx, vy, pc, 2.5F + this.rng.nextFloat() * 3.0F, (16 + this.rng.nextInt(22))));
/*     */     } 
/*     */   }
/*     */   
/*     */   private void spawnRing(int cx, int cy, int color, float maxRadius, float life) {
/* 439 */     this.rings.add(new Ring(cx, cy, color, maxRadius, life));
/*     */   }
/*     */   
/*     */   private void updateRings(class_332 ctx, float dt) {
/* 443 */     if (this.rings.isEmpty())
/* 444 */       return;  class_4587 m = ctx.method_51448();
/* 445 */     m.method_22903();
/* 446 */     m.method_46416(0.0F, 0.0F, 250.0F);
/* 447 */     Iterator<Ring> it = this.rings.iterator();
/* 448 */     while (it.hasNext()) {
/* 449 */       Ring r = it.next();
/* 450 */       r.age += dt;
/* 451 */       if (r.age >= r.life) { it.remove(); continue; }
/* 452 */        float t = r.age / r.life;
/* 453 */       float radius = r.maxRadius * smooth(t);
/* 454 */       int alpha = (int)((1.0F - t) * 200.0F);
/* 455 */       int col = CardCollectionColors.withAlpha(r.color, alpha);
/* 456 */       int thickness = Math.max(1, Math.round(3.0F * (1.0F - t)));
/*     */       
/* 458 */       int segs = (r.maxRadius >= 100.0F) ? 30 : 24;
/* 459 */       for (int s = 0; s < segs; s++) {
/* 460 */         double a = s / segs * Math.PI * 2.0D;
/* 461 */         int px = r.cx + (int)(Math.cos(a) * radius);
/* 462 */         int py = r.cy + (int)(Math.sin(a) * radius);
/* 463 */         ctx.method_25294(px - thickness, py - thickness, px + thickness, py + thickness, col);
/*     */       } 
/*     */     } 
/* 466 */     m.method_22909();
/*     */   }
/*     */   
/*     */   private void spawnShakeSpark(int cx, int cy, int color, int tier) {
/* 470 */     if (tier == 0)
/* 471 */       return;  float angle = this.rng.nextFloat() * 2.0F * 3.1415927F;
/* 472 */     float dist = 18.0F + this.rng.nextFloat() * 14.0F;
/* 473 */     float px = cx + (float)Math.cos(angle) * dist;
/* 474 */     float py = cy + (float)Math.sin(angle) * dist;
/* 475 */     addParticle(new Particle(px, py, (cx - px) * 0.05F, (cy - py) * 0.05F, color, 1.5F + this.rng.nextFloat() * 1.5F, (9 + this.rng.nextInt(7))));
/*     */   }
/*     */   
/*     */   private void addParticle(Particle particle) {
/* 479 */     if (this.particles.size() >= 180) this.particles.removeFirst(); 
/* 480 */     this.particles.add(particle);
/*     */   }
/*     */   
/*     */   private void updateParticles(class_332 ctx, float dt) {
/* 484 */     if (this.particles.isEmpty())
/* 485 */       return;  class_4587 m = ctx.method_51448();
/* 486 */     m.method_22903();
/* 487 */     m.method_46416(0.0F, 0.0F, 300.0F);
/* 488 */     Iterator<Particle> it = this.particles.iterator();
/* 489 */     while (it.hasNext()) {
/* 490 */       Particle p = it.next();
/* 491 */       p.age += dt;
/* 492 */       if (p.age >= p.life) { it.remove(); continue; }
/* 493 */        p.x += p.vx * dt;
/* 494 */       p.y += p.vy * dt;
/* 495 */       p.vy += 0.25F * dt;
/* 496 */       p.vx *= 0.98F;
/* 497 */       float fade = Math.max(0.0F, 1.0F - p.age / p.life);
/* 498 */       int col = CardCollectionColors.withAlpha(p.color, (int)(fade * 255.0F));
/* 499 */       int s = Math.round(p.size);
/* 500 */       ctx.method_25294((int)p.x, (int)p.y, (int)p.x + s, (int)p.y + s, col);
/*     */     } 
/* 502 */     m.method_22909();
/*     */   }
/*     */ 
/*     */   
/*     */   private void drawSummary(class_332 ctx, CardCollectionDto.PackData pack, int mouseX, int mouseY) {
/* 507 */     int cy = this.belowCardsY;
/*     */     
/* 509 */     int newCount = 0;
/* 510 */     for (CardCollectionDto.PackCard c : pack.cards()) { if (c.isNew()) newCount++;
/*     */        }
/*     */     
/* 513 */     String summary = !"regular".equals(pack.packKind()) ? (pack.packKindName() + "!") : ((newCount > 0) ? ("" + newCount + " new card" + newCount + " added to your binder!") : "All duplicates - burn them for Karma!");
/* 514 */     ctx.method_25300(this.field_22793, summary, this.field_22789 / 2, cy, CardCollectionColors.ACCENT_GOLD);
/* 515 */     ctx.method_25300(this.field_22793, "" + pack.packsRemaining() + " " + pack.packsRemaining() + " pack" + pack
/* 516 */         .setName() + " remaining", this.field_22789 / 2, cy + 10, CardCollectionColors.TEXT_DIM);
/*     */ 
/*     */     
/* 519 */     this.btnY = cy + 24;
/* 520 */     boolean another = pack.canOpenAnother();
/* 521 */     int gap = 8;
/* 522 */     int count = another ? 3 : 2;
/* 523 */     int totalW = count * this.btnW + (count - 1) * gap;
/* 524 */     int sx = (this.field_22789 - totalW) / 2;
/*     */     
/* 526 */     this.binderBtnX = sx;
/* 527 */     if (another) { this.anotherBtnX = sx + this.btnW + gap; this.closeBtnX = sx + 2 * (this.btnW + gap); }
/* 528 */     else { this.anotherBtnX = -1; this.closeBtnX = sx + this.btnW + gap; }
/*     */     
/* 530 */     drawBtn(ctx, this.binderBtnX, this.btnY, "Open Binder", mouseX, mouseY, CardCollectionColors.ACCENT_GOLD);
/* 531 */     if (another) drawBtn(ctx, this.anotherBtnX, this.btnY, "Open Another", mouseX, mouseY, CardCollectionColors.ACCENT_GREEN); 
/* 532 */     drawBtn(ctx, this.closeBtnX, this.btnY, "Close", mouseX, mouseY, CardCollectionColors.TEXT_DIM);
/*     */   }
/*     */   
/*     */   private void drawBtn(class_332 ctx, int x, int y, String label, int mouseX, int mouseY, int accent) {
/* 536 */     boolean hover = (mouseX >= x && mouseX < x + this.btnW && mouseY >= y && mouseY < y + this.btnH);
/* 537 */     ctx.method_25294(x, y, x + this.btnW, y + this.btnH, hover ? CardCollectionColors.BUTTON_HOVER : CardCollectionColors.BUTTON_BG);
/* 538 */     ctx.method_49601(x, y, this.btnW, this.btnH, hover ? CardCollectionColors.BORDER_HIGHLIGHT : CardCollectionColors.BUTTON_BORDER);
/* 539 */     ctx.method_25300(this.field_22793, label, x + this.btnW / 2, y + (this.btnH - 8) / 2, hover ? CardCollectionColors.TEXT_WHITE : accent);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean method_25402(double mouseX, double mouseY, int button) {
/* 545 */     if (button != 0) return super.method_25402(mouseX, mouseY, button); 
/* 546 */     CardCollectionDto.PackData pack = data();
/* 547 */     if (pack == null) return super.method_25402(mouseX, mouseY, button);
/*     */     
/* 549 */     boolean allRevealed = allRevealed();
/*     */     
/* 551 */     if (allRevealed) {
/* 552 */       if (hit(mouseX, mouseY, this.binderBtnX, this.btnY)) {
/* 553 */         sendAck();
/* 554 */         CardCollectionClientData.getInstance().clearBinder();
/* 555 */         CardCollectionNetwork.requestBinder(pack.setId(), 1);
/* 556 */         return true;
/*     */       } 
/* 558 */       if (pack.canOpenAnother() && this.anotherBtnX >= 0 && hit(mouseX, mouseY, this.anotherBtnX, this.btnY)) {
/* 559 */         sendAck();
/* 560 */         CardCollectionNetwork.openPack(pack.setId());
/* 561 */         return true;
/*     */       } 
/* 563 */       if (hit(mouseX, mouseY, this.closeBtnX, this.btnY)) {
/* 564 */         method_25419();
/* 565 */         return true;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 570 */     int count = slotCount(pack);
/* 571 */     int cardW = cardWidth(count);
/* 572 */     int cardH = cardHeight(cardW);
/* 573 */     int totalW = count * cardW + (count - 1) * 20;
/* 574 */     int startX = (this.field_22789 - totalW) / 2;
/* 575 */     int top = this.cardsTopY;
/* 576 */     for (int i = 0; i < count; i++) {
/* 577 */       int cx = startX + i * (cardW + 20);
/* 578 */       if (!this.clicked[i] && mouseX >= cx && mouseX < (cx + cardW) && mouseY >= top && mouseY < (top + cardH)) {
/* 579 */         beginReveal(i, (i < pack.cards().size()) ? pack.cards().get(i) : null);
/* 580 */         return true;
/*     */       } 
/*     */     } 
/* 583 */     return super.method_25402(mouseX, mouseY, button);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean method_25404(int keyCode, int scanCode, int modifiers) {
/* 588 */     if (keyCode == 256) { method_25419(); return true; }
/*     */     
/* 590 */     if (keyCode == 32 || keyCode == 257) {
/* 591 */       CardCollectionDto.PackData pack = data();
/* 592 */       if (pack != null) {
/* 593 */         int count = slotCount(pack);
/* 594 */         for (int i = 0; i < count; i++) {
/* 595 */           if (!this.clicked[i]) {
/* 596 */             beginReveal(i, (i < pack.cards().size()) ? pack.cards().get(i) : null);
/* 597 */             return true;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 602 */     return super.method_25404(keyCode, scanCode, modifiers);
/*     */   }
/*     */ 
/*     */   
/*     */   public void method_25419() {
/* 607 */     sendAck();
/* 608 */     CardCollectionClientData.getInstance().clearPack();
/* 609 */     super.method_25419();
/*     */   }
/*     */   
/*     */   private void sendAck() {
/* 613 */     if (this.ackSent)
/* 614 */       return;  this.ackSent = true;
/* 615 */     CardCollectionNetwork.closePack();
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean hit(double mx, double my, int x, int y) {
/* 620 */     return (x >= 0 && mx >= x && mx < (x + this.btnW) && my >= y && my < (y + this.btnH));
/*     */   }
/*     */   
/*     */   private boolean allClicked() {
/* 624 */     int count = slotCount(data());
/* 625 */     for (int i = 0; i < count; ) { if (!this.clicked[i]) return false;  i++; }
/* 626 */      return true;
/*     */   }
/*     */   
/*     */   private boolean allRevealed() {
/* 630 */     int count = slotCount(data());
/* 631 */     for (int i = 0; i < count; ) { if (!this.clicked[i] || this.flipProgress[i] < 1.0F) return false;  i++; }
/* 632 */      return true;
/*     */   }
/*     */   
/*     */   private float shakeDuration(CardCollectionDto.PackCard card) {
/* 636 */     switch ((card == null) ? 0 : CardCollectionColors.rarityTier(card.rarity())) { case true: case true:  }  return 
/*     */ 
/*     */       
/* 639 */       8.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   private float flipSpeed(CardCollectionDto.PackCard card) {
/* 644 */     switch ((card == null) ? 0 : CardCollectionColors.rarityTier(card.rarity())) { case true: case true:  }  return 
/*     */ 
/*     */       
/* 647 */       0.12F;
/*     */   }
/*     */ 
/*     */   
/*     */   private void play(class_3414 sound, float pitch) {
/* 652 */     class_310.method_1551().method_1483().method_4873((class_1113)class_1109.method_4758(sound, pitch));
/*     */   }
/*     */   
/*     */   private void play(class_3414 sound, float pitch, float volume) {
/* 656 */     class_310.method_1551().method_1483().method_4873((class_1113)class_1109.method_4757(sound, pitch, volume));
/*     */   }
/*     */   
/*     */   private void play(class_6880<class_3414> sound, float pitch, float volume) {
/* 660 */     class_310.method_1551().method_1483().method_4873((class_1113)class_1109.method_4757((class_3414)sound.comp_349(), pitch, volume));
/*     */   }
/*     */ 
/*     */   
/*     */   private void schedule(long delayMs, Runnable r) {
/* 665 */     this.pendingSounds.add(new PendingSound(System.currentTimeMillis() + delayMs, r));
/*     */   }
/*     */   
/*     */   private void flushScheduledSounds(long now) {
/* 669 */     if (this.pendingSounds.isEmpty())
/* 670 */       return;  Iterator<PendingSound> it = this.pendingSounds.iterator();
/* 671 */     while (it.hasNext()) {
/* 672 */       PendingSound ps = it.next();
/* 673 */       if (now >= ps.at) { ps.play.run(); it.remove(); }
/*     */     
/*     */     } 
/*     */   } private static final class PendingSound extends Record { private final long at; private final Runnable play;
/* 677 */     private PendingSound(long at, Runnable play) { this.at = at; this.play = play; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/cardcollection/screen/PackOpeningScreen$PendingSound;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #677	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/* 677 */       //   0	7	0	this	Lcom/atlas/common/fabric/cardcollection/screen/PackOpeningScreen$PendingSound; } public long at() { return this.at; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/cardcollection/screen/PackOpeningScreen$PendingSound;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #677	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/* 677 */       //   0	7	0	this	Lcom/atlas/common/fabric/cardcollection/screen/PackOpeningScreen$PendingSound; } public Runnable play() { return this.play; }
/*     */     
/*     */     public final boolean equals(Object o) {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/cardcollection/screen/PackOpeningScreen$PendingSound;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #677	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lcom/atlas/common/fabric/cardcollection/screen/PackOpeningScreen$PendingSound;
/*     */       //   0	8	1	o	Ljava/lang/Object;
/*     */     } }
/*     */   private static final class Ring { final int cx; final int cy; final int color; final float maxRadius; final float life; float age;
/*     */     Ring(int cx, int cy, int color, float maxRadius, float life) {
/* 684 */       this.cx = cx; this.cy = cy; this.color = color; this.maxRadius = maxRadius; this.life = life;
/*     */     } }
/*     */ 
/*     */   
/*     */   private static int slotCount(CardCollectionDto.PackData pack) {
/* 689 */     if (pack == null || pack.cards() == null || pack.cards().isEmpty()) return 5; 
/* 690 */     return Math.max(5, Math.min(7, pack.cards().size()));
/*     */   }
/*     */   
/*     */   private int cardWidth(int count) {
/* 694 */     int min = (count > 5) ? 62 : 78;
/* 695 */     return Math.max(min, Math.min(124, (this.field_22789 - 80 - (count - 1) * 20) / count));
/*     */   }
/*     */   
/*     */   private static int cardHeight(int cardW) {
/* 699 */     return Math.round(cardW / 0.718F);
/*     */   }
/*     */   private static float clamp(float v) {
/* 702 */     return Math.max(0.0F, Math.min(1.0F, v));
/*     */   } private static float smooth(float x) {
/* 704 */     return x * x * (3.0F - 2.0F * x);
/*     */   }
/*     */   
/*     */   private float revealPop(int index) {
/* 708 */     if (this.revealTick[index] < 0.0F) return 1.0F; 
/* 709 */     float t = (this.ticks - this.revealTick[index]) / 4.0F;
/* 710 */     if (t >= 1.0F) return 1.0F; 
/* 711 */     return 1.0F + 0.22F * (float)Math.sin(t * Math.PI);
/*     */   }
/*     */ 
/*     */   
/*     */   private static int hsv(float hueDeg, float s, float v) {
/* 716 */     float h = hueDeg % 360.0F / 60.0F;
/* 717 */     int i = (int)Math.floor(h);
/* 718 */     float f = h - i;
/* 719 */     float p = v * (1.0F - s);
/* 720 */     float q = v * (1.0F - s * f);
/* 721 */     float t = v * (1.0F - s * (1.0F - f));
/*     */     
/* 723 */     switch (i % 6) { case 0:
/* 724 */         r = v; g = t; b = p;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 731 */         return (int)(r * 255.0F) << 16 | (int)(g * 255.0F) << 8 | (int)(b * 255.0F);case 1: r = q; g = v; b = p; return (int)(r * 255.0F) << 16 | (int)(g * 255.0F) << 8 | (int)(b * 255.0F);case 2: r = p; g = v; b = t; return (int)(r * 255.0F) << 16 | (int)(g * 255.0F) << 8 | (int)(b * 255.0F);case 3: r = p; g = q; b = v; return (int)(r * 255.0F) << 16 | (int)(g * 255.0F) << 8 | (int)(b * 255.0F);case 4: r = t; g = p; b = v; return (int)(r * 255.0F) << 16 | (int)(g * 255.0F) << 8 | (int)(b * 255.0F); }  float r = v, g = p, b = q; return (int)(r * 255.0F) << 16 | (int)(g * 255.0F) << 8 | (int)(b * 255.0F);
/*     */   }
/*     */   private static final class Particle { float x; float y; float vx; float vy; float age;
/*     */     final int color;
/*     */     final float size;
/*     */     final float life;
/*     */     
/*     */     Particle(float x, float y, float vx, float vy, int color, float size, float life) {
/* 739 */       this.x = x; this.y = y; this.vx = vx; this.vy = vy;
/* 740 */       this.color = color; this.size = size; this.life = life;
/*     */     } }
/*     */ 
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\cardcollection\screen\PackOpeningScreen.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */