/*     */ package com.atlas.common.fabric.starterguide;
/*     */ 
/*     */ import com.atlas.common.fabric.mixin.content.accessor.ToastManagerAccessor;
/*     */ import com.atlas.common.fabric.safari.racing.client.RacingHudState;
/*     */ import com.cobblemon.mod.common.client.CobblemonClient;
/*     */ import com.cobblemon.mod.common.client.render.RenderHelperKt;
/*     */ import net.fabricmc.api.EnvType;
/*     */ import net.fabricmc.api.Environment;
/*     */ import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
/*     */ import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
/*     */ import net.fabricmc.fabric.api.client.screen.v1.ScreenMouseEvents;
/*     */ import net.minecraft.class_2561;
/*     */ import net.minecraft.class_310;
/*     */ import net.minecraft.class_327;
/*     */ import net.minecraft.class_332;
/*     */ import net.minecraft.class_437;
/*     */ import net.minecraft.class_746;
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
/*     */ 
/*     */ @Environment(EnvType.CLIENT)
/*     */ public final class StarterGuideHudRenderer
/*     */ {
/*     */   private static final int BG = -1073741824;
/*     */   private static final int ACCENT = -10496;
/*     */   private static final int TITLE_COLOR = -1;
/*     */   private static final int DESC_COLOR = -5592406;
/*     */   private static final int REWARD_COLOR = -11141291;
/*     */   private static final int HINT_DIM = -7829368;
/*     */   private static final int HINT_BRIGHT = -10496;
/*     */   private static final int PROGRESS_BG = -13421773;
/*     */   private static final int PROGRESS_FILL = -10496;
/*     */   private static final int PCT_COLOR = -3355444;
/*     */   private static final int COMPLETE_TEXT = -11141291;
/*     */   private static final int CLOSE_COLOR = -43691;
/*     */   private static final int TOP_MARGIN = 6;
/*     */   private static final int WIDGET_WIDTH = 160;
/*     */   private static final int PAD = 5;
/*     */   private static final int LINE_H = 9;
/*     */   private static final int BAR_H = 4;
/*     */   private static final float SMALL_SCALE = 0.5F;
/*     */   private static int closeBtnX1;
/*     */   private static int closeBtnY1;
/*     */   private static int closeBtnX2;
/*     */   private static int closeBtnY2;
/*     */   
/*     */   public static void register() {
/*  59 */     HudRenderCallback.EVENT.register((drawContext, tickCounter) -> {
/*     */           if ((class_310.method_1551()).field_1724 == null) {
/*     */             return;
/*     */           }
/*     */           render(drawContext);
/*     */         });
/*  65 */     ScreenEvents.AFTER_INIT.register((client, screen, scaledWidth, scaledHeight) -> ScreenMouseEvents.beforeMouseClick(screen).register(()));
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void render(class_332 ctx) {
/*  86 */     class_310 client = class_310.method_1551();
/*     */     
/*  88 */     if (client.field_1690.field_1842)
/*  89 */       return;  if (client.method_53526().method_53536())
/*  90 */       return;  if (CobblemonClient.INSTANCE.getBattle() != null)
/*  91 */       return;  if (RacingHudState.getInstance().isActive())
/*  92 */       return;  if (client.field_1755 instanceof com.atlas.common.fabric.cardcollection.screen.CardCollectionScreen || client.field_1755 instanceof com.atlas.common.fabric.cardcollection.screen.BinderScreen || client.field_1755 instanceof com.atlas.common.fabric.cardcollection.screen.PackOpeningScreen) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 102 */     if (!((ToastManagerAccessor)client.method_1566()).atlas$getVisibleEntries().isEmpty())
/*     */       return; 
/* 104 */     PlayerStarterGuideData data = PlayerStarterGuideData.getInstance();
/* 105 */     if (!data.shouldRender())
/*     */       return; 
/* 107 */     class_327 tr = client.field_1772;
/* 108 */     int screenW = client.method_22683().method_4486();
/* 109 */     int x = (screenW - 160) / 2;
/* 110 */     int y = 6;
/*     */ 
/*     */     
/* 113 */     ctx.method_51448().method_22903();
/* 114 */     ctx.method_51448().method_46416(0.0F, 0.0F, 200.0F);
/*     */     
/* 116 */     if (data.isShowingCompletion()) {
/* 117 */       renderCompletion(ctx, tr, x, 6, data);
/*     */     } else {
/* 119 */       renderNormal(ctx, tr, x, 6, data);
/*     */     } 
/*     */     
/* 122 */     ctx.method_51448().method_22909();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void renderNormal(class_332 ctx, class_327 tr, int x, int y, PlayerStarterGuideData data) {
/* 128 */     int smallLineH = Math.round(4.5F);
/* 129 */     int maxCharWidth = 300;
/*     */ 
/*     */     
/* 132 */     int h = 5;
/* 133 */     h += 9;
/* 134 */     h += 3;
/* 135 */     h += smallLineH;
/* 136 */     h += 3;
/* 137 */     h += 4;
/* 138 */     h += 2;
/* 139 */     h += 7;
/*     */     
/* 141 */     if (!data.getRewardDescription().isEmpty()) {
/* 142 */       h += 2;
/* 143 */       h += smallLineH;
/*     */     } 
/* 145 */     if (!data.getHint().isEmpty()) {
/* 146 */       h += 2;
/* 147 */       h += smallLineH;
/*     */     } 
/* 149 */     h += 5;
/*     */ 
/*     */     
/* 152 */     ctx.method_25294(x, y, x + 160, y + h, -1073741824);
/*     */     
/* 154 */     ctx.method_25294(x, y, x + 160, y + 1, -10496);
/*     */ 
/*     */     
/* 157 */     renderCloseButton(ctx, tr, x, y);
/*     */     
/* 159 */     int cy = y + 5;
/*     */ 
/*     */     
/* 162 */     String title = "Starter Guide";
/* 163 */     int tw = tr.method_1727(title);
/* 164 */     ctx.method_51433(tr, title, x + (160 - tw) / 2, cy, -1, true);
/* 165 */     cy += 12;
/*     */ 
/*     */     
/* 168 */     String desc = data.getDescription();
/* 169 */     int descPixelW = (int)(tr.method_1727(desc) * 0.5F);
/* 170 */     float descX = x + (160 - descPixelW) / 2.0F;
/* 171 */     RenderHelperKt.drawScaledText(ctx, null, 
/* 172 */         class_2561.method_43470(desc), 
/* 173 */         Float.valueOf(descX), Float.valueOf(cy), 0.5F, 
/* 174 */         Float.valueOf(1.0F), maxCharWidth, -5592406, false, true, null, null);
/*     */ 
/*     */     
/* 177 */     cy += smallLineH + 3;
/*     */ 
/*     */     
/* 180 */     int barX = x + 5;
/* 181 */     int barW = 150;
/* 182 */     float ratio = (data.getTarget() > 0) ? Math.min(1.0F, (float)data.getProgress() / data.getTarget()) : 0.0F;
/* 183 */     int filled = Math.round(barW * ratio);
/*     */     
/* 185 */     ctx.method_25294(barX, cy, barX + barW, cy + 4, -13421773);
/* 186 */     if (filled > 0) {
/* 187 */       ctx.method_25294(barX, cy, barX + filled, cy + 4, -10496);
/*     */     }
/* 189 */     cy += 6;
/*     */ 
/*     */     
/* 192 */     int pct = Math.round(ratio * 100.0F);
/* 193 */     String pctText = "" + pct + "%";
/* 194 */     int pctW = tr.method_1727(pctText);
/* 195 */     ctx.method_51433(tr, pctText, x + (160 - pctW) / 2, cy, -3355444, true);
/* 196 */     cy += 7;
/*     */ 
/*     */     
/* 199 */     if (!data.getRewardDescription().isEmpty()) {
/* 200 */       cy += 2;
/* 201 */       String reward = "Reward: " + data.getRewardDescription();
/* 202 */       int rewardPixelW = (int)(tr.method_1727(reward) * 0.5F);
/* 203 */       float rewardX = x + (160 - rewardPixelW) / 2.0F;
/* 204 */       RenderHelperKt.drawScaledText(ctx, null, 
/* 205 */           class_2561.method_43470(reward), 
/* 206 */           Float.valueOf(rewardX), Float.valueOf(cy), 0.5F, 
/* 207 */           Float.valueOf(1.0F), maxCharWidth, -11141291, false, true, null, null);
/*     */ 
/*     */       
/* 210 */       cy += smallLineH;
/*     */     } 
/*     */ 
/*     */     
/* 214 */     if (!data.getHint().isEmpty()) {
/* 215 */       cy += 2;
/* 216 */       String hint = data.getHint();
/* 217 */       float pulse = (float)(Math.sin(System.currentTimeMillis() / 500.0D) * 0.5D + 0.5D);
/* 218 */       int hintColor = lerpColor(-7829368, -10496, pulse);
/* 219 */       int hintPixelW = (int)(tr.method_1727(hint) * 0.5F);
/* 220 */       float hintX = x + (160 - hintPixelW) / 2.0F;
/* 221 */       RenderHelperKt.drawScaledText(ctx, null, 
/* 222 */           class_2561.method_43470(hint), 
/* 223 */           Float.valueOf(hintX), Float.valueOf(cy), 0.5F, 
/* 224 */           Float.valueOf(1.0F), maxCharWidth, hintColor, false, true, null, null);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void renderCompletion(class_332 ctx, class_327 tr, int x, int y, PlayerStarterGuideData data) {
/* 233 */     int smallLineH = Math.round(4.5F);
/* 234 */     int h = 18 + smallLineH + 5;
/*     */     
/* 236 */     float anim = data.getCompletionAnimProgress();
/* 237 */     int alpha = (int)(192.0F * (1.0F - anim * 0.3F));
/* 238 */     int bg = alpha << 24;
/*     */     
/* 240 */     ctx.method_25294(x, y, x + 160, y + h, bg);
/* 241 */     ctx.method_25294(x, y, x + 160, y + 1, -11141291);
/*     */ 
/*     */     
/* 244 */     renderCloseButton(ctx, tr, x, y);
/*     */     
/* 246 */     int cy = y + 5;
/* 247 */     String completeText = "Step Complete!";
/* 248 */     int cw = tr.method_1727(completeText);
/* 249 */     ctx.method_51433(tr, completeText, x + (160 - cw) / 2, cy, -11141291, true);
/* 250 */     cy += 13;
/*     */ 
/*     */     
/* 253 */     String desc = data.getCompletedStepDescription();
/* 254 */     int maxCharWidth = 300;
/* 255 */     int descPixelW = (int)(tr.method_1727(desc) * 0.5F);
/* 256 */     float descX = x + (160 - descPixelW) / 2.0F;
/* 257 */     RenderHelperKt.drawScaledText(ctx, null, 
/* 258 */         class_2561.method_43470(desc), 
/* 259 */         Float.valueOf(descX), Float.valueOf(cy), 0.5F, 
/* 260 */         Float.valueOf(1.0F), maxCharWidth, -5592406, false, true, null, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void renderCloseButton(class_332 ctx, class_327 tr, int x, int y) {
/* 268 */     String close = "x";
/* 269 */     int closeW = tr.method_1727(close);
/* 270 */     int closeX = x + 160 - closeW - 3;
/* 271 */     int closeY = y + 3;
/* 272 */     ctx.method_51433(tr, close, closeX, closeY, -43691, true);
/*     */ 
/*     */     
/* 275 */     closeBtnX1 = closeX - 2;
/* 276 */     closeBtnY1 = closeY - 2;
/* 277 */     closeBtnX2 = closeX + closeW + 2;
/* 278 */     closeBtnY2 = closeY + 9 + 2;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static int lerpColor(int colorA, int colorB, float t) {
/* 284 */     int aA = colorA >> 24 & 0xFF, rA = colorA >> 16 & 0xFF, gA = colorA >> 8 & 0xFF, bA = colorA & 0xFF;
/* 285 */     int aB = colorB >> 24 & 0xFF, rB = colorB >> 16 & 0xFF, gB = colorB >> 8 & 0xFF, bB = colorB & 0xFF;
/* 286 */     int a = (int)(aA + (aB - aA) * t);
/* 287 */     int r = (int)(rA + (rB - rA) * t);
/* 288 */     int g = (int)(gA + (gB - gA) * t);
/* 289 */     int b = (int)(bA + (bB - bA) * t);
/* 290 */     return a << 24 | r << 16 | g << 8 | b;
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\starterguide\StarterGuideHudRenderer.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */