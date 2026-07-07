/*     */ package com.atlas.common.fabric.foreverpack.screen;
/*     */ 
/*     */ import com.atlas.common.fabric.foreverpack.data.ForeverPackClientData;
/*     */ import com.atlas.common.fabric.foreverpack.network.ForeverPackNetwork;
/*     */ import com.cobblemon.mod.common.api.gui.GuiUtilsKt;
/*     */ import java.text.NumberFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import net.minecraft.class_1109;
/*     */ import net.minecraft.class_1113;
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
/*     */ public class ForeverPackPreviewScreen
/*     */   extends class_437
/*     */ {
/*     */   private static final String NS = "atlas-common-fabric";
/*     */   
/*     */   private static class_2960 tex(String p) {
/*  32 */     return class_2960.method_60655("atlas-common-fabric", "textures/foreverpack/" + p);
/*     */   }
/*  34 */   private static final class_2960 TEX_BG = tex("bg.png");
/*  35 */   private static final class_2960 TEX_PREVIEW_BG = tex("preview_bg.png");
/*  36 */   private static final class_2960 TEX_GEM = tex("gem.png");
/*     */   
/*     */   private static final int FRAME_W = 420;
/*     */   
/*     */   private static final int FRAME_H = 235;
/*     */   
/*     */   private static final int GRID_W = 199;
/*     */   
/*     */   private static final int GRID_H = 114;
/*     */   
/*     */   private static final int GRID_COLS = 10;
/*     */   private static final int GRID_ROWS = 6;
/*     */   private static final int FIRST_SLOT_X = 0;
/*     */   private static final int FIRST_SLOT_Y = 0;
/*     */   private static final int SLOT_STRIDE_X = 20;
/*     */   private static final int SLOT_STRIDE_Y = 19;
/*     */   private static final int SLOT_INNER_W = 20;
/*     */   private static final int SLOT_INNER_H = 19;
/*     */   private static final int GRID_OFFSET_X = 110;
/*     */   private static final int GRID_OFFSET_Y = 60;
/*     */   
/*     */   private static int color(int r, int g, int b, int a) {
/*  58 */     return a << 24 | r << 16 | g << 8 | b;
/*  59 */   } private static final NumberFormat GEM_FMT = NumberFormat.getNumberInstance(Locale.US);
/*     */   private final class_437 parent;
/*     */   private int guiLeft;
/*     */   private int guiTop;
/*  63 */   private int hoveredSlot = -1;
/*     */   
/*     */   public ForeverPackPreviewScreen(class_437 parent) {
/*  66 */     super((class_2561)class_2561.method_43470("Forever Pack — Preview"));
/*  67 */     this.parent = parent;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void method_25426() {
/*  72 */     super.method_25426();
/*  73 */     this.guiLeft = (this.field_22789 - 420) / 2;
/*  74 */     this.guiTop = (this.field_22790 - 235) / 2;
/*     */   }
/*     */   public boolean method_25421() {
/*  77 */     return false;
/*     */   }
/*     */   private void drawTex(class_332 ctx, class_2960 tex, int x, int y, int w, int h) {
/*  80 */     GuiUtilsKt.blitk(ctx.method_51448(), tex, Integer.valueOf(x), Integer.valueOf(y), Integer.valueOf(h), Integer.valueOf(w), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(w), Integer.valueOf(h), Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(1), Float.valueOf(1.0F), true, 1.0F);
/*     */   }
/*     */   
/*     */   private void drawTexScaled(class_332 ctx, class_2960 tex, int x, int y, int w, int h, int srcW, int srcH) {
/*  84 */     ctx.method_25293(tex, x, y, w, h, 0.0F, 0.0F, srcW, srcH, srcW, srcH);
/*     */   }
/*     */ 
/*     */   
/*     */   public void method_25394(class_332 ctx, int mouseX, int mouseY, float delta) {
/*  89 */     super.method_25394(ctx, mouseX, mouseY, delta);
/*  90 */     ctx.method_25294(0, 0, this.field_22789, this.field_22790, color(0, 0, 0, 180));
/*  91 */     this.hoveredSlot = -1;
/*     */ 
/*     */     
/*  94 */     drawTex(ctx, TEX_BG, this.guiLeft, this.guiTop, 420, 235);
/*     */ 
/*     */     
/*  97 */     ctx.method_25300(this.field_22793, "Upcoming Rewards", this.guiLeft + 210, this.guiTop + 40, 
/*  98 */         color(255, 220, 100, 255));
/*     */ 
/*     */     
/* 101 */     int gx = this.guiLeft + 110;
/* 102 */     int gy = this.guiTop + 60;
/* 103 */     drawTex(ctx, TEX_PREVIEW_BG, gx, gy, 199, 114);
/*     */ 
/*     */     
/* 106 */     ForeverPackClientData data = ForeverPackClientData.getInstance();
/* 107 */     List<ForeverPackNetwork.TierEntry> tiers = data.getTiers();
/* 108 */     if (tiers.isEmpty())
/*     */       return; 
/* 110 */     int startIdx = 0;
/* 111 */     for (int i = 0; i < tiers.size(); i++) {
/* 112 */       if (!((ForeverPackNetwork.TierEntry)tiers.get(i)).claimed()) { startIdx = i; break; }
/*     */     
/*     */     } 
/* 115 */     int max = Math.min(tiers.size() - startIdx, 60);
/* 116 */     for (int n = 0; n < max; n++) {
/* 117 */       int tierIdx = startIdx + n;
/* 118 */       int col = n % 10;
/* 119 */       int row = n / 10;
/*     */ 
/*     */       
/* 122 */       int sx = gx + 0 + col * 20;
/* 123 */       int sy = gy + 0 + row * 19;
/*     */ 
/*     */       
/* 126 */       int iconX = sx + 2 - 1;
/* 127 */       int iconY = sy + 1;
/*     */       
/* 129 */       ForeverPackNetwork.TierEntry t = tiers.get(tierIdx);
/* 130 */       if (!t.rewards().isEmpty()) {
/* 131 */         class_1799 stack = ((ForeverPackNetwork.RewardEntry)t.rewards().get(0)).iconStack();
/* 132 */         if (stack != null && !stack.method_7960()) {
/* 133 */           class_4587 m = ctx.method_51448(); m.method_22903(); m.method_46416(0.0F, 0.0F, 100.0F);
/* 134 */           ctx.method_51427(stack, iconX, iconY);
/* 135 */           m.method_22909();
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 140 */       if (t.paid() && 
/* 141 */         t.milestone()) {
/* 142 */         ctx.method_51433(this.field_22793, "★", sx + 1, sy, -3639041, true);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 147 */       if (mouseX >= sx && mouseX < sx + 20 && mouseY >= sy && mouseY < sy + 19)
/*     */       {
/* 149 */         this.hoveredSlot = tierIdx;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 154 */     if (this.hoveredSlot >= 0 && this.hoveredSlot < tiers.size()) {
/* 155 */       drawSlotTooltip(ctx, mouseX, mouseY, tiers.get(this.hoveredSlot));
/*     */     }
/*     */ 
/*     */     
/* 159 */     ctx.method_25300(this.field_22793, "Click outside or press Esc to close", this.guiLeft + 210, this.guiTop + 235 + 6, 
/* 160 */         color(220, 210, 190, 255));
/*     */   }
/*     */   
/*     */   private void drawSlotTooltip(class_332 ctx, int mouseX, int mouseY, ForeverPackNetwork.TierEntry tier) {
/* 164 */     ArrayList<class_2561> lines = new ArrayList<>();
/* 165 */     if (tier.paid()) {
/* 166 */       String prefix = tier.milestone() ? "★ Milestone Tier " : "Tier ";
/* 167 */       lines.add(class_2561.method_43470(prefix + prefix)
/* 168 */           .method_27694(s -> s.method_36139(tier.milestone() ? 13138175 : 16762940)));
/* 169 */       lines.add(class_2561.method_43470("Cost: " + GEM_FMT.format(tier.cost()) + " Gems")
/* 170 */           .method_27694(s -> s.method_36139(16765520)));
/*     */     } else {
/* 172 */       lines.add(class_2561.method_43470("Free Reward").method_27694(s -> s.method_36139(8565980)));
/*     */     } 
/* 174 */     lines.add(class_2561.method_43473());
/* 175 */     for (ForeverPackNetwork.RewardEntry r : tier.rewards()) {
/* 176 */       String txt = ((r.amount() > 1) ? ("" + r.amount() + "x ") : "") + ((r.amount() > 1) ? ("" + r.amount() + "x ") : "");
/* 177 */       lines.add(class_2561.method_43470("  " + txt).method_27694(s -> s.method_36139(15789020)));
/*     */     } 
/* 179 */     class_4587 m = ctx.method_51448(); m.method_22903(); m.method_46416(0.0F, 0.0F, 600.0F);
/* 180 */     ctx.method_51434(this.field_22793, lines, mouseX, mouseY);
/* 181 */     m.method_22909();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean method_25402(double mouseX, double mouseY, int button) {
/* 187 */     if (mouseX < this.guiLeft || mouseX > (this.guiLeft + 420) || mouseY < this.guiTop || mouseY > (this.guiTop + 235)) {
/*     */       
/* 189 */       playClick();
/* 190 */       method_25419();
/* 191 */       return true;
/*     */     } 
/* 193 */     return super.method_25402(mouseX, mouseY, button);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean method_25404(int keyCode, int scanCode, int modifiers) {
/* 198 */     if (keyCode == 256) { method_25419(); return true; }
/* 199 */      return super.method_25404(keyCode, scanCode, modifiers);
/*     */   }
/*     */ 
/*     */   
/*     */   public void method_25419() {
/* 204 */     class_310.method_1551().method_1507(this.parent);
/*     */   }
/*     */   
/*     */   private void playClick() {
/* 208 */     class_310.method_1551().method_1483().method_4873(
/* 209 */         (class_1113)class_1109.method_4757((class_3414)class_3417.field_15015.comp_349(), 1.0F, 0.8F));
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\foreverpack\screen\ForeverPackPreviewScreen.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */