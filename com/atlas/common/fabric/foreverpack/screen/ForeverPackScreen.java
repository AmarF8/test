/*     */ package com.atlas.common.fabric.foreverpack.screen;
/*     */ 
/*     */ import com.atlas.common.fabric.foreverpack.data.ForeverPackClientData;
/*     */ import com.atlas.common.fabric.foreverpack.network.ForeverPackNetwork;
/*     */ import com.cobblemon.mod.common.api.gui.GuiUtilsKt;
/*     */ import java.text.NumberFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Random;
/*     */ import net.minecraft.class_1109;
/*     */ import net.minecraft.class_1113;
/*     */ import net.minecraft.class_1144;
/*     */ import net.minecraft.class_1799;
/*     */ import net.minecraft.class_2561;
/*     */ import net.minecraft.class_2583;
/*     */ import net.minecraft.class_2960;
/*     */ import net.minecraft.class_310;
/*     */ import net.minecraft.class_332;
/*     */ import net.minecraft.class_3414;
/*     */ import net.minecraft.class_3417;
/*     */ import net.minecraft.class_437;
/*     */ import net.minecraft.class_4587;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ForeverPackScreen
/*     */   extends class_437
/*     */ {
/*     */   private static final String NS = "atlas-common-fabric";
/*     */   
/*     */   private static class_2960 tex(String p) {
/*  38 */     return class_2960.method_60655("atlas-common-fabric", "textures/foreverpack/" + p);
/*     */   }
/*  40 */   private static final class_2960 TEX_BG = tex("bg.png");
/*  41 */   private static final class_2960 TEX_SLOT_TOP = tex("slot_top.png");
/*  42 */   private static final class_2960 TEX_ARROW = tex("arrow.png");
/*  43 */   private static final class_2960 TEX_SLOT_MID = tex("slot_middle.png");
/*  44 */   private static final class_2960 TEX_BTN_GOLD = tex("btn_gold.png");
/*  45 */   private static final class_2960 TEX_BTN_GOLD_H = tex("btn_gold_hover.png");
/*  46 */   private static final class_2960 TEX_BTN_BLUE = tex("btn_blue.png");
/*  47 */   private static final class_2960 TEX_BTN_PURPLE = tex("btn_purple.png");
/*  48 */   private static final class_2960 TEX_BTN_PURPLE_H = tex("btn_purple_hover.png");
/*  49 */   private static final class_2960 TEX_PREVIEW_BTN = tex("btn_preview.png");
/*  50 */   private static final class_2960 TEX_GEM = tex("gem.png");
/*     */   
/*     */   public static final int GUI_W = 420;
/*     */   
/*     */   public static final int GUI_H = 235;
/*     */   
/*     */   private static final int SLOT_TOP_W = 46;
/*     */   
/*     */   private static final int SLOT_TOP_H = 53;
/*     */   
/*     */   private static final int ARROW_W = 16;
/*     */   
/*     */   private static final int ARROW_H = 15;
/*     */   
/*     */   private static final int SLOT_MID_W = 24;
/*     */   
/*     */   private static final int SLOT_MID_H = 25;
/*     */   private static final int BTN_W = 160;
/*     */   private static final int BTN_H = 22;
/*     */   private static final int PREVIEW_BTN_W = 24;
/*     */   private static final int PREVIEW_BTN_H = 25;
/*     */   private static final int GEM_W = 16;
/*     */   private static final int GEM_H = 16;
/*     */   private static final int TOP_ROW_Y = 45;
/*     */   private static final int TOP_SLOT_COUNT = 5;
/*     */   private static final int TOP_SLOT_STRIDE = 78;
/*     */   private static final int TOP_FIRST_SLOT_X = 31;
/*     */   private static final int ARROW_Y_OFFSET = 19;
/*     */   private static final int MID_ROW_Y = 138;
/*     */   private static final int MID_SLOT_COUNT = 3;
/*     */   private static final int MID_SLOT_GAP = 2;
/*     */   private static final int MID_TOTAL_W = 76;
/*     */   private static final int MID_FIRST_SLOT_X = 172;
/*     */   private static final int BTN_X = 130;
/*     */   private static final int BTN_Y = 178;
/*     */   private static final int PREVIEW_BTN_X = 296;
/*     */   private static final int PREVIEW_BTN_Y = 177;
/*     */   private int guiLeft;
/*     */   private int guiTop;
/*  89 */   private float railScroll = 0.0F;
/*  90 */   private float targetRailScroll = 0.0F;
/*     */   private boolean unlockHover = false;
/*     */   private boolean previewHover = false;
/*  93 */   private int hoveredRailCard = -1;
/*  94 */   private int hoveredMidSlot = -1;
/*  95 */   private long lastFrameMs = 0L;
/*  96 */   private long purchaseAnimAt = 0L;
/*  97 */   private int lastKnownTier = -1;
/*     */ 
/*     */   
/* 100 */   private final List<ConfettiParticle> confetti = new ArrayList<>();
/* 101 */   private static final Random CONFETTI_RNG = new Random();
/* 102 */   private static final int[] CONFETTI_COLORS = new int[] {
/* 103 */       color(255, 200, 60, 255), color(80, 220, 100, 255), color(200, 120, 255, 255), 
/* 104 */       color(80, 200, 240, 255), color(255, 120, 80, 255), color(255, 255, 100, 255), 
/* 105 */       color(120, 255, 200, 255), color(255, 160, 220, 255)
/*     */     };
/*     */   
/* 108 */   private static final NumberFormat GEM_FMT = NumberFormat.getNumberInstance(Locale.US);
/*     */   
/*     */   private static int color(int r, int g, int b, int a) {
/* 111 */     return a << 24 | r << 16 | g << 8 | b;
/* 112 */   } private static final int ACCENT_GOLD = color(255, 200, 60, 255);
/* 113 */   private static final int ACCENT_GREEN = color(80, 220, 100, 255);
/* 114 */   private static final int ACCENT_RED = color(220, 60, 60, 255);
/* 115 */   private static final int ACCENT_PURPLE = color(200, 120, 255, 255);
/* 116 */   private static final int ACCENT_BLUE = color(80, 200, 240, 255);
/*     */   public ForeverPackScreen() {
/* 118 */     super((class_2561)class_2561.method_43470("Forever Pack"));
/*     */   }
/*     */   
/*     */   protected void method_25426() {
/* 122 */     super.method_25426();
/* 123 */     this.guiLeft = (this.field_22789 - 420) / 2;
/* 124 */     this.guiTop = (this.field_22790 - 235) / 2;
/* 125 */     ForeverPackNetwork.requestSync();
/*     */   }
/*     */   public boolean method_25421() {
/* 128 */     return false;
/*     */   }
/*     */   
/*     */   private void drawTex(class_332 ctx, class_2960 tex, int x, int y, int w, int h) {
/* 132 */     GuiUtilsKt.blitk(ctx.method_51448(), tex, Integer.valueOf(x), Integer.valueOf(y), Integer.valueOf(h), Integer.valueOf(w), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(w), Integer.valueOf(h), Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(1), Float.valueOf(1.0F), true, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void method_25394(class_332 ctx, int mouseX, int mouseY, float delta) {
/* 137 */     super.method_25394(ctx, mouseX, mouseY, delta);
/* 138 */     long now = System.currentTimeMillis();
/* 139 */     long dt = (this.lastFrameMs == 0L) ? 16L : Math.min(100L, now - this.lastFrameMs);
/* 140 */     this.lastFrameMs = now;
/* 141 */     this.hoveredRailCard = -1;
/* 142 */     this.hoveredMidSlot = -1;
/*     */ 
/*     */     
/* 145 */     ctx.method_25294(0, 0, this.field_22789, this.field_22790, color(0, 0, 0, 160));
/*     */ 
/*     */     
/* 148 */     drawTex(ctx, TEX_BG, this.guiLeft, this.guiTop, 420, 235);
/*     */ 
/*     */     
/* 151 */     drawTopRail(ctx, mouseX, mouseY, now, dt);
/*     */ 
/*     */     
/* 154 */     drawMiddleSlots(ctx, mouseX, mouseY);
/*     */ 
/*     */     
/* 157 */     drawActionButton(ctx, mouseX, mouseY, now);
/*     */ 
/*     */     
/* 160 */     updateAndDrawConfetti(ctx, dt);
/*     */ 
/*     */     
/* 163 */     drawToast(ctx, now);
/*     */ 
/*     */     
/* 166 */     drawRailTooltips(ctx, mouseX, mouseY);
/* 167 */     drawMidSlotTooltips(ctx, mouseX, mouseY);
/*     */   }
/*     */ 
/*     */   
/*     */   private void drawTopRail(class_332 ctx, int mouseX, int mouseY, long now, long dt) {
/* 172 */     ForeverPackClientData data = ForeverPackClientData.getInstance();
/* 173 */     List<ForeverPackNetwork.TierEntry> tiers = data.getTiers();
/* 174 */     if (tiers.isEmpty()) {
/*     */       return;
/*     */     }
/* 177 */     if (this.lastKnownTier != data.getCurrentLadderTier()) {
/* 178 */       this.lastKnownTier = data.getCurrentLadderTier();
/*     */     }
/*     */ 
/*     */     
/* 182 */     int nextIdx = firstUnclaimedIndex(tiers);
/* 183 */     if (nextIdx >= 0)
/*     */     {
/* 185 */       this.targetRailScroll = Math.max(0, (nextIdx - 2) * 78);
/*     */     }
/* 187 */     this.railScroll += (this.targetRailScroll - this.railScroll) * Math.min(1.0F, (float)dt / 180.0F);
/*     */ 
/*     */     
/* 190 */     int railLeftPx = this.guiLeft + 31;
/* 191 */     int railRightPx = railLeftPx + 312 + 46;
/*     */     
/* 193 */     ctx.method_44379(railLeftPx - 4, this.guiTop + 45 - 4, railRightPx + 4, this.guiTop + 45 + 53 + 4);
/*     */     
/*     */     int i;
/* 196 */     for (i = 0; i < tiers.size(); i++) {
/* 197 */       int slotX = (int)((railLeftPx + i * 78) - this.railScroll);
/* 198 */       int slotY = this.guiTop + 45;
/*     */       
/* 200 */       if (slotX + 46 >= railLeftPx - 8 && 
/* 201 */         slotX <= railRightPx + 8) {
/*     */         
/* 203 */         ForeverPackNetwork.TierEntry tier = tiers.get(i);
/*     */ 
/*     */         
/* 206 */         drawTex(ctx, TEX_SLOT_TOP, slotX, slotY, 46, 53);
/*     */ 
/*     */         
/* 209 */         boolean hov = (mouseX >= slotX && mouseX < slotX + 46 && mouseY >= slotY && mouseY < slotY + 53);
/*     */         
/* 211 */         if (hov) this.hoveredRailCard = i;
/*     */ 
/*     */         
/* 214 */         if (!tier.rewards().isEmpty()) {
/* 215 */           class_1799 stack = ((ForeverPackNetwork.RewardEntry)tier.rewards().get(0)).iconStack();
/* 216 */           if (stack != null && !stack.method_7960()) {
/* 217 */             class_4587 m = ctx.method_51448(); m.method_22903(); m.method_46416(0.0F, 0.0F, 100.0F);
/* 218 */             ctx.method_51427(stack, slotX + 15, slotY + 14);
/* 219 */             m.method_22909();
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 224 */         if (tier.paid() && tier.milestone() && !tier.claimed()) {
/* 225 */           ctx.method_51433(this.field_22793, "★", slotX + 46 - 7, slotY + 4, ACCENT_PURPLE, true);
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 230 */         int bottomY = slotY + 53 - 14;
/* 231 */         if (tier.claimed()) {
/* 232 */           ctx.method_25300(this.field_22793, "✔", slotX + 23, bottomY, ACCENT_GREEN);
/*     */         }
/* 234 */         else if (tier.paid() && tier.cost() > 0) {
/* 235 */           String costStr = GEM_FMT.format(tier.cost());
/* 236 */           int textW = this.field_22793.method_1727(costStr);
/* 237 */           int gemSize = 7;
/* 238 */           int gap = 2;
/* 239 */           int totalW = textW + gap + gemSize;
/* 240 */           int startX = slotX + (46 - totalW) / 2;
/* 241 */           ctx.method_51433(this.field_22793, costStr, startX, bottomY, ACCENT_GOLD, true);
/*     */ 
/*     */           
/* 244 */           drawTexScaled(ctx, TEX_GEM, startX + textW + gap, bottomY, gemSize, gemSize);
/* 245 */         } else if (!tier.paid()) {
/* 246 */           ctx.method_25300(this.field_22793, "FREE", slotX + 23, bottomY, ACCENT_BLUE);
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 251 */         if (i == nextIdx && !tier.claimed()) {
/* 252 */           float pulse = (float)(0.5D + 0.5D * Math.sin(now * 0.005D));
/* 253 */           int alpha = 80 + (int)(pulse * 60.0F);
/* 254 */           int glow = tier.milestone() ? color(220, 140, 255, alpha) : color(255, 220, 100, alpha);
/* 255 */           ctx.method_25294(slotX + 1, slotY + 1, slotX + 46 - 1, slotY + 3, glow);
/* 256 */           ctx.method_25294(slotX + 1, slotY + 53 - 3, slotX + 46 - 1, slotY + 53 - 1, glow);
/*     */         } 
/*     */       } 
/* 259 */     }  ctx.method_44380();
/*     */ 
/*     */     
/* 262 */     ctx.method_44379(railLeftPx - 4, this.guiTop + 45 - 4, railRightPx + 4, this.guiTop + 45 + 53 + 4);
/*     */     
/* 264 */     for (i = 0; i < tiers.size() - 1; i++) {
/* 265 */       int arrowX = (int)((railLeftPx + i * 78 + 46 + 8) - this.railScroll);
/*     */       
/* 267 */       int arrowY = this.guiTop + 45 + 19;
/* 268 */       if (arrowX + 16 >= railLeftPx - 8 && arrowX <= railRightPx + 8)
/* 269 */         drawTex(ctx, TEX_ARROW, arrowX, arrowY, 16, 15); 
/*     */     } 
/* 271 */     ctx.method_44380();
/*     */   }
/*     */ 
/*     */   
/*     */   private void drawTexScaled(class_332 ctx, class_2960 tex, int x, int y, int w, int h) {
/* 276 */     ctx.method_25293(tex, x, y, w, h, 0.0F, 0.0F, 16, 16, 16, 16);
/*     */   }
/*     */ 
/*     */   
/*     */   private void drawMiddleSlots(class_332 ctx, int mouseX, int mouseY) {
/* 281 */     ForeverPackClientData data = ForeverPackClientData.getInstance();
/* 282 */     List<ForeverPackNetwork.TierEntry> tiers = data.getTiers();
/* 283 */     int nextIdx = firstUnclaimedIndex(tiers);
/* 284 */     if (nextIdx < 0 || tiers.isEmpty())
/*     */       return; 
/* 286 */     ForeverPackNetwork.TierEntry focus = tiers.get(nextIdx);
/* 287 */     int rewardCount = Math.min(3, focus.rewards().size());
/*     */     
/* 289 */     int actualW = rewardCount * 24 + (rewardCount - 1) * 2;
/* 290 */     int startX = this.guiLeft + (420 - actualW) / 2;
/*     */     
/* 292 */     for (int i = 0; i < rewardCount; i++) {
/* 293 */       int sx = startX + i * 26;
/* 294 */       int sy = this.guiTop + 138;
/*     */       
/* 296 */       drawTex(ctx, TEX_SLOT_MID, sx, sy, 24, 25);
/*     */       
/* 298 */       ForeverPackNetwork.RewardEntry r = focus.rewards().get(i);
/* 299 */       class_1799 stack = r.iconStack();
/* 300 */       if (stack != null && !stack.method_7960()) {
/* 301 */         class_4587 m = ctx.method_51448(); m.method_22903(); m.method_46416(0.0F, 0.0F, 100.0F);
/* 302 */         ctx.method_51427(stack, sx + 4, sy + 4);
/* 303 */         m.method_22909();
/*     */       } 
/* 305 */       if (r.amount() > 1) {
/* 306 */         String amt = "x" + r.amount();
/* 307 */         int tw = this.field_22793.method_1727(amt);
/* 308 */         ctx.method_51433(this.field_22793, amt, sx + 24 - tw - 1, sy + 25 - 9, ACCENT_GOLD, true);
/*     */       } 
/*     */ 
/*     */       
/* 312 */       boolean hov = (mouseX >= sx && mouseX < sx + 24 && mouseY >= sy && mouseY < sy + 25);
/*     */       
/* 314 */       if (hov) this.hoveredMidSlot = i;
/*     */     
/*     */     } 
/*     */   }
/*     */   
/*     */   private void drawActionButton(class_332 ctx, int mouseX, int mouseY, long now) {
/* 320 */     ForeverPackClientData data = ForeverPackClientData.getInstance();
/* 321 */     List<ForeverPackNetwork.TierEntry> tiers = data.getTiers();
/* 322 */     int nextIdx = firstUnclaimedIndex(tiers);
/*     */     
/* 324 */     int btnX = this.guiLeft + 130;
/* 325 */     int btnY = this.guiTop + 178;
/* 326 */     this.unlockHover = (mouseX >= btnX && mouseX < btnX + 160 && mouseY >= btnY && mouseY < btnY + 22);
/*     */ 
/*     */     
/* 329 */     if (nextIdx < 0 || tiers.isEmpty()) {
/*     */       
/* 331 */       drawTex(ctx, TEX_BTN_GOLD, btnX, btnY, 160, 22);
/* 332 */       ctx.method_25300(this.field_22793, "ALL CLAIMED", btnX + 80, btnY + 7, ACCENT_GREEN);
/*     */     } else {
/*     */       class_2960 btnTex; String label; int textColor;
/* 335 */       ForeverPackNetwork.TierEntry focus = tiers.get(nextIdx);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 340 */       if (focus.paid()) {
/* 341 */         if (focus.milestone()) {
/* 342 */           btnTex = this.unlockHover ? TEX_BTN_PURPLE_H : TEX_BTN_PURPLE;
/*     */         } else {
/* 344 */           btnTex = this.unlockHover ? TEX_BTN_GOLD_H : TEX_BTN_GOLD;
/*     */         } 
/* 346 */         label = "UNLOCK  •  " + GEM_FMT.format(focus.cost());
/*     */         
/* 348 */         textColor = focus.milestone() ? color(255, 230, 255, 255) : color(255, 240, 200, 255);
/*     */       } else {
/* 350 */         btnTex = TEX_BTN_BLUE;
/* 351 */         label = "CLAIM";
/* 352 */         textColor = color(220, 240, 255, 255);
/*     */       } 
/*     */       
/* 355 */       drawTex(ctx, btnTex, btnX, btnY, 160, 22);
/*     */ 
/*     */       
/* 358 */       int textW = this.field_22793.method_1727(label);
/* 359 */       int totalW = focus.paid() ? (textW + 2 + 12) : textW;
/* 360 */       int startX = btnX + (160 - totalW) / 2;
/* 361 */       ctx.method_51433(this.field_22793, label, startX, btnY + 7, textColor, true);
/* 362 */       if (focus.paid()) {
/* 363 */         drawTexScaled(ctx, TEX_GEM, startX + textW + 2, btnY + 5, 12, 12);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 368 */     int pX = this.guiLeft + 296;
/* 369 */     int pY = this.guiTop + 177;
/* 370 */     this.previewHover = (mouseX >= pX && mouseX < pX + 24 && mouseY >= pY && mouseY < pY + 25);
/*     */     
/* 372 */     drawTex(ctx, TEX_PREVIEW_BTN, pX, pY, 24, 25);
/* 373 */     if (this.previewHover) {
/* 374 */       ctx.method_25294(pX, pY, pX + 24, pY + 25, color(255, 255, 255, 30));
/*     */       
/* 376 */       ArrayList<class_2561> lines = new ArrayList<>();
/* 377 */       lines.add(class_2561.method_43470("Preview Rewards")
/* 378 */           .method_27694(s -> s.method_36139(16762940).method_10982(Boolean.valueOf(true))));
/* 379 */       lines.add(class_2561.method_43470("See all possible rewards")
/* 380 */           .method_27694(s -> s.method_36139(12632256)));
/* 381 */       class_4587 m = ctx.method_51448(); m.method_22903(); m.method_46416(0.0F, 0.0F, 600.0F);
/* 382 */       ctx.method_51434(this.field_22793, lines, mouseX, mouseY);
/* 383 */       m.method_22909();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void drawRailTooltips(class_332 ctx, int mouseX, int mouseY) {
/* 389 */     if (this.hoveredRailCard < 0)
/* 390 */       return;  ForeverPackClientData data = ForeverPackClientData.getInstance();
/* 391 */     List<ForeverPackNetwork.TierEntry> tiers = data.getTiers();
/* 392 */     if (this.hoveredRailCard >= tiers.size())
/* 393 */       return;  ForeverPackNetwork.TierEntry tier = tiers.get(this.hoveredRailCard);
/*     */     
/* 395 */     List<class_2561> lines = new ArrayList<>();
/* 396 */     if (tier.paid()) {
/* 397 */       String prefix = tier.milestone() ? "★ Milestone Tier " : "Tier ";
/* 398 */       lines.add(class_2561.method_43470(prefix + prefix)
/* 399 */           .method_27694(s -> s.method_36139(tier.milestone() ? 13138175 : 16762940)));
/* 400 */       lines.add(class_2561.method_43470("Cost: " + GEM_FMT.format(tier.cost()) + " Gems")
/* 401 */           .method_27694(s -> s.method_36139(16765520)));
/*     */     } else {
/* 403 */       lines.add(class_2561.method_43470("Free Reward").method_27694(s -> s.method_36139(8565980)));
/*     */     } 
/* 405 */     if (tier.claimed()) {
/* 406 */       lines.add(class_2561.method_43470("✔ Claimed").method_27694(s -> s.method_36139(5299300)));
/*     */     }
/* 408 */     lines.add(class_2561.method_43473());
/* 409 */     for (ForeverPackNetwork.RewardEntry r : tier.rewards()) {
/* 410 */       String txt = ((r.amount() > 1) ? ("" + r.amount() + "x ") : "") + ((r.amount() > 1) ? ("" + r.amount() + "x ") : "");
/* 411 */       lines.add(class_2561.method_43470("  " + txt).method_27694(s -> s.method_36139(15789020)));
/*     */     } 
/* 413 */     class_4587 m = ctx.method_51448(); m.method_22903(); m.method_46416(0.0F, 0.0F, 600.0F);
/* 414 */     ctx.method_51434(this.field_22793, lines, mouseX, mouseY);
/* 415 */     m.method_22909();
/*     */   }
/*     */   
/*     */   private void drawMidSlotTooltips(class_332 ctx, int mouseX, int mouseY) {
/* 419 */     if (this.hoveredMidSlot < 0)
/* 420 */       return;  ForeverPackClientData data = ForeverPackClientData.getInstance();
/* 421 */     List<ForeverPackNetwork.TierEntry> tiers = data.getTiers();
/* 422 */     int nextIdx = firstUnclaimedIndex(tiers);
/* 423 */     if (nextIdx < 0)
/* 424 */       return;  ForeverPackNetwork.TierEntry focus = tiers.get(nextIdx);
/* 425 */     if (this.hoveredMidSlot >= focus.rewards().size())
/* 426 */       return;  ForeverPackNetwork.RewardEntry r = focus.rewards().get(this.hoveredMidSlot);
/* 427 */     String txt = ((r.amount() > 1) ? ("" + r.amount() + "x ") : "") + ((r.amount() > 1) ? ("" + r.amount() + "x ") : "");
/* 428 */     class_4587 m = ctx.method_51448(); m.method_22903(); m.method_46416(0.0F, 0.0F, 600.0F);
/* 429 */     ctx.method_51438(this.field_22793, (class_2561)class_2561.method_43470(txt), mouseX, mouseY);
/* 430 */     m.method_22909();
/*     */   }
/*     */   
/*     */   private static int firstUnclaimedIndex(List<ForeverPackNetwork.TierEntry> tiers) {
/* 434 */     for (int i = 0; i < tiers.size(); ) { if (!((ForeverPackNetwork.TierEntry)tiers.get(i)).claimed()) return i;  i++; }
/* 435 */      return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   private void drawToast(class_332 ctx, long now) {
/* 440 */     ForeverPackClientData data = ForeverPackClientData.getInstance();
/* 441 */     String rawMsg = data.getLastResultMessage();
/* 442 */     if (rawMsg == null || rawMsg.isBlank())
/* 443 */       return;  long age = now - data.getLastResultAt();
/* 444 */     if (age > 3000L) { data.clearLastResult(); return; }
/*     */     
/* 446 */     if (age < 50L && this.purchaseAnimAt != data.getLastResultAt()) {
/* 447 */       this.purchaseAnimAt = data.getLastResultAt();
/* 448 */       if (data.isLastResultSuccess()) {
/* 449 */         if (rawMsg.contains("Free")) { playClaimSound(); } else { playPurchaseSound(); }
/*     */       
/*     */       }
/*     */     } 
/* 453 */     float alpha = (age > 2500L) ? ((float)(3000L - age) / 500.0F) : 1.0F;
/* 454 */     int a = (int)(alpha * 255.0F);
/* 455 */     boolean success = data.isLastResultSuccess();
/* 456 */     String msg = success ? ("✔ " + rawMsg) : ("✖ " + rawMsg);
/*     */     
/* 458 */     int accentColor = success ? (a << 24 | ACCENT_GREEN & 0xFFFFFF) : (a << 24 | ACCENT_RED & 0xFFFFFF);
/*     */     
/* 460 */     int bgColor = success ? color(30, 70, 40, (int)(a * 0.9D)) : color(80, 25, 25, (int)(a * 0.9D));
/*     */     
/* 462 */     int textW = this.field_22793.method_1727(msg);
/* 463 */     int toastW = textW + 20;
/* 464 */     int toastH = 18;
/* 465 */     int toastX = this.guiLeft + (420 - toastW) / 2;
/*     */     
/* 467 */     int railBottom = 98;
/* 468 */     int toastY = this.guiTop + railBottom + (138 - railBottom - toastH) / 2;
/*     */     
/* 470 */     ctx.method_51448().method_22903();
/* 471 */     ctx.method_51448().method_46416(0.0F, 0.0F, 700.0F);
/*     */     
/* 473 */     ctx.method_25294(toastX - 1, toastY - 1, toastX + toastW + 1, toastY + toastH + 1, color(0, 0, 0, a));
/* 474 */     ctx.method_25294(toastX, toastY, toastX + toastW, toastY + toastH, bgColor);
/* 475 */     ctx.method_49601(toastX, toastY, toastW, toastH, accentColor);
/* 476 */     ctx.method_25294(toastX + 1, toastY, toastX + toastW - 1, toastY + 1, accentColor);
/*     */     
/* 478 */     int textColor = a << 24 | 0xFFFFFF;
/* 479 */     ctx.method_25300(this.field_22793, msg, toastX + toastW / 2, toastY + 5, textColor);
/*     */     
/* 481 */     ctx.method_51448().method_22909();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean method_25402(double mouseX, double mouseY, int button) {
/* 487 */     if (button != 0) return super.method_25402(mouseX, mouseY, button);
/*     */ 
/*     */     
/* 490 */     if (this.previewHover) {
/* 491 */       playClick();
/* 492 */       class_310.method_1551().method_1507(new ForeverPackPreviewScreen(this));
/* 493 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 497 */     if (this.unlockHover) {
/* 498 */       ForeverPackClientData data = ForeverPackClientData.getInstance();
/* 499 */       int nextIdx = firstUnclaimedIndex(data.getTiers());
/* 500 */       if (nextIdx < 0) { playClick(); return true; }
/* 501 */        playClick();
/* 502 */       int btnCenterX = this.guiLeft + 130 + 80;
/* 503 */       int btnCenterY = this.guiTop + 178 + 11;
/* 504 */       spawnConfetti(btnCenterX, btnCenterY, 30);
/* 505 */       ForeverPackNetwork.requestPurchase();
/* 506 */       return true;
/*     */     } 
/* 508 */     return super.method_25402(mouseX, mouseY, button);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean method_25401(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
/* 514 */     if (mouseY >= (this.guiTop + 45) && mouseY < (this.guiTop + 45 + 53)) {
/* 515 */       this.targetRailScroll = Math.max(0.0F, this.targetRailScroll - (float)verticalAmount * 24.0F);
/* 516 */       return true;
/*     */     } 
/* 518 */     return super.method_25401(mouseX, mouseY, horizontalAmount, verticalAmount);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean method_25404(int keyCode, int scanCode, int modifiers) {
/* 523 */     if (keyCode == 256) { method_25419(); return true; }
/* 524 */      return super.method_25404(keyCode, scanCode, modifiers);
/*     */   }
/*     */ 
/*     */   
/*     */   private void playClick() {
/* 529 */     class_310.method_1551().method_1483().method_4873(
/* 530 */         (class_1113)class_1109.method_4757((class_3414)class_3417.field_15015.comp_349(), 1.0F, 0.8F));
/*     */   }
/*     */   
/*     */   private void playPurchaseSound() {
/* 534 */     class_1144 sm = class_310.method_1551().method_1483();
/* 535 */     sm.method_4873((class_1113)class_1109.method_4757((class_3414)class_3417.field_15015.comp_349(), 1.4F, 0.7F));
/* 536 */     sm.method_4873((class_1113)class_1109.method_4757((class_3414)class_3417.field_14793.comp_349(), 1.6F, 0.9F));
/*     */   }
/*     */   
/*     */   private void playClaimSound() {
/* 540 */     class_310.method_1551().method_1483().method_4873(
/* 541 */         (class_1113)class_1109.method_4757((class_3414)class_3417.field_14725.comp_349(), 1.8F, 0.8F));
/*     */   }
/*     */ 
/*     */   
/*     */   private void spawnConfetti(int cx, int cy, int count) {
/* 546 */     for (int i = 0; i < count; i++) {
/* 547 */       float angle = (float)(CONFETTI_RNG.nextDouble() * Math.PI * 2.0D);
/* 548 */       float speed = 1.5F + CONFETTI_RNG.nextFloat() * 3.5F;
/* 549 */       float vx = (float)(Math.cos(angle) * speed);
/* 550 */       float vy = (float)(Math.sin(angle) * speed) - 2.0F;
/* 551 */       int color = CONFETTI_COLORS[CONFETTI_RNG.nextInt(CONFETTI_COLORS.length)];
/* 552 */       int size = 2 + CONFETTI_RNG.nextInt(3);
/* 553 */       float life = 0.6F + CONFETTI_RNG.nextFloat() * 0.8F;
/* 554 */       this.confetti.add(new ConfettiParticle(cx, cy, vx, vy, color, size, life));
/*     */     } 
/*     */   }
/*     */   
/*     */   private void updateAndDrawConfetti(class_332 ctx, long dt) {
/* 559 */     if (this.confetti.isEmpty())
/* 560 */       return;  float dtSec = (float)dt / 1000.0F;
/* 561 */     ctx.method_51448().method_22903();
/* 562 */     ctx.method_51448().method_46416(0.0F, 0.0F, 500.0F);
/* 563 */     Iterator<ConfettiParticle> iter = this.confetti.iterator();
/* 564 */     while (iter.hasNext()) {
/* 565 */       ConfettiParticle p = iter.next();
/* 566 */       p.age += dtSec;
/* 567 */       if (p.age >= p.maxLife) { iter.remove(); continue; }
/* 568 */        p.x += p.vx * dtSec * 60.0F;
/* 569 */       p.y += p.vy * dtSec * 60.0F;
/* 570 */       p.vy += 4.5F * dtSec * 60.0F * 0.016F;
/* 571 */       p.vx *= 0.98F;
/* 572 */       float fade = Math.max(0.0F, 1.0F - p.age / p.maxLife);
/* 573 */       int alpha = (int)(fade * 255.0F);
/* 574 */       int col = alpha << 24 | p.color & 0xFFFFFF;
/* 575 */       ctx.method_25294((int)p.x, (int)p.y, (int)p.x + p.size, (int)p.y + p.size, col);
/*     */     } 
/* 577 */     ctx.method_51448().method_22909();
/*     */   }
/*     */   private static final class ConfettiParticle { float x;
/*     */     float y;
/*     */     float vx;
/*     */     float vy;
/*     */     
/*     */     ConfettiParticle(float x, float y, float vx, float vy, int color, int size, float maxLife) {
/* 585 */       this.x = x; this.y = y; this.vx = vx; this.vy = vy;
/* 586 */       this.color = color; this.size = size; this.maxLife = maxLife; this.age = 0.0F;
/*     */     }
/*     */     
/*     */     float age;
/*     */     final int color;
/*     */     final int size;
/*     */     final float maxLife; }
/*     */ 
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\foreverpack\screen\ForeverPackScreen.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */