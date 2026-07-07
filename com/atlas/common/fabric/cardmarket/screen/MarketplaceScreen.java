/*     */ package com.atlas.common.fabric.cardmarket.screen;
/*     */ 
/*     */ import com.atlas.common.fabric.cardcollection.screen.CardCollectionColors;
/*     */ import com.atlas.common.fabric.cardcollection.screen.CardMenuTextures;
/*     */ import com.atlas.common.fabric.cardcollection.screen.CardRender;
/*     */ import com.atlas.common.fabric.cardcollection.screen.CardTextures;
/*     */ import com.atlas.common.fabric.cardmarket.client.CardMarketClientData;
/*     */ import com.atlas.common.fabric.cardmarket.client.CardMarketDto;
/*     */ import com.atlas.common.fabric.cardmarket.network.CardMarketNetwork;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import net.minecraft.class_1109;
/*     */ import net.minecraft.class_1113;
/*     */ import net.minecraft.class_2561;
/*     */ import net.minecraft.class_2583;
/*     */ import net.minecraft.class_2960;
/*     */ import net.minecraft.class_310;
/*     */ import net.minecraft.class_332;
/*     */ import net.minecraft.class_3417;
/*     */ import net.minecraft.class_342;
/*     */ import net.minecraft.class_364;
/*     */ import net.minecraft.class_437;
/*     */ import net.minecraft.class_6880;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MarketplaceScreen
/*     */   extends class_437
/*     */ {
/*     */   private static final int COLS = 5;
/*     */   private static final int ROWS = 3;
/*     */   private static final int CELL_W = 56;
/*     */   private static final int CELL_H = 78;
/*     */   private static final int GAP_X = 8;
/*     */   private static final int GAP_Y = 22;
/*     */   private static final int GRID_W = 312;
/*     */   private static final int GRID_H = 278;
/*     */   private static final int PAD = 24;
/*     */   private static final int HEADER_H = 62;
/*     */   private static final int FOOTER_H = 30;
/*     */   private static final int GUI_W = 454;
/*     */   private static final int GUI_H = 368;
/*     */   private int guiLeft;
/*     */   private int guiTop;
/*  51 */   private String filter = "all";
/*  52 */   private float scroll = 0.0F;
/*     */   
/*  54 */   private int hovered = -1;
/*  55 */   private String selectedId = null; private class_342 codeField; private int allTabX; private int allTabW; private int mineTabX;
/*     */   private int mineTabW;
/*     */   private int closeX;
/*     */   private int closeY;
/*  59 */   private int closeW = 31, closeH = 20; private int buyX; private int buyY; private int buyW; private int buyH;
/*     */   private int delistX;
/*     */   
/*     */   public MarketplaceScreen() {
/*  63 */     super((class_2561)class_2561.method_43470("Card Market"));
/*     */   }
/*     */   private int delistY; private int delistW; private int delistH; private int detailCloseX; private int detailCloseY; private int detailCloseSz;
/*     */   
/*     */   protected void method_25426() {
/*  68 */     super.method_25426();
/*  69 */     this.guiLeft = (this.field_22789 - 454) / 2;
/*  70 */     this.guiTop = (this.field_22790 - 368) / 2;
/*  71 */     this.codeField = new class_342(this.field_22793, 0, 0, 150, 14, (class_2561)class_2561.method_43470("Gift card code"));
/*  72 */     this.codeField.method_1880(32);
/*  73 */     this.codeField.method_47404((class_2561)class_2561.method_43470("XXXX-XXXX-XXXX-XXXX").method_27694(s -> s.method_36139(8415828)));
/*  74 */     this.codeField.method_1862(false);
/*  75 */     method_25429((class_364)this.codeField);
/*  76 */     CardMarketNetwork.requestMarket(this.filter);
/*     */   }
/*     */   
/*     */   public boolean method_25421() {
/*  80 */     return false;
/*     */   }
/*     */   public void method_25420(class_332 ctx, int mouseX, int mouseY, float delta) {}
/*     */   
/*     */   private CardMarketDto.Snapshot data() {
/*  85 */     return CardMarketClientData.getInstance().getSnapshot();
/*     */   }
/*     */   
/*     */   public void method_25394(class_332 ctx, int mouseX, int mouseY, float delta) {
/*  89 */     CardMarketDto.Snapshot snap = data();
/*     */ 
/*     */     
/*  92 */     if (this.selectedId != null) {
/*  93 */       CardMarketDto.Listing sel = find(snap, this.selectedId);
/*  94 */       if (sel != null) {
/*  95 */         ctx.method_25294(0, 0, this.field_22789, this.field_22790, CardCollectionColors.withAlpha(-16777216, 238));
/*  96 */         drawDetail(ctx, snap, sel, mouseX, mouseY);
/*  97 */         super.method_25394(ctx, mouseX, mouseY, delta);
/*     */         return;
/*     */       } 
/* 100 */       this.selectedId = null;
/*     */     } 
/*     */     
/* 103 */     ctx.method_25294(0, 0, this.field_22789, this.field_22790, CardCollectionColors.SCRIM);
/* 104 */     drawPanel(ctx);
/* 105 */     drawHeader(ctx, snap, mouseX, mouseY);
/*     */ 
/*     */     
/* 108 */     this.hovered = -1;
/* 109 */     List<CardMarketDto.Listing> listings = snap.listings();
/* 110 */     int gridLeft = this.guiLeft + 24;
/* 111 */     int gridTop = this.guiTop + 62;
/* 112 */     int clipBottom = this.guiTop + 368 - 30;
/* 113 */     ctx.method_44379(gridLeft, gridTop, gridLeft + 312, clipBottom);
/* 114 */     int perPage = 15;
/* 115 */     int y0 = gridTop - (int)this.scroll;
/* 116 */     for (int i = 0; i < listings.size(); i++) {
/* 117 */       int col = i % 5;
/* 118 */       int row = i / 5;
/* 119 */       int cx = gridLeft + col * 64;
/* 120 */       int cy = y0 + row * 100;
/* 121 */       if (cy + 78 >= gridTop && cy <= clipBottom)
/* 122 */         drawTile(ctx, listings.get(i), i, cx, cy, mouseX, mouseY, gridTop, clipBottom); 
/*     */     } 
/* 124 */     ctx.method_44380();
/*     */     
/* 126 */     if (listings.isEmpty()) {
/* 127 */       ctx.method_25300(this.field_22793, "mine".equals(this.filter) ? "You have no active listings." : "No cards for sale right now.", this.guiLeft + 227, gridTop + 139, CardCollectionColors.TEXT_DIM);
/*     */     }
/*     */ 
/*     */     
/* 131 */     drawResultBanner(ctx);
/* 132 */     super.method_25394(ctx, mouseX, mouseY, delta);
/*     */   }
/*     */   
/*     */   private void drawPanel(class_332 ctx) {
/* 136 */     CardMenuTextures.draw(ctx, "market/background_wtext_topbelow", this.guiLeft, this.guiTop, 454, 368);
/* 137 */     CardMenuTextures.drawButton(ctx, "market/left_arrow", false, this.guiLeft + 16, this.guiTop + 178, 19, 25);
/* 138 */     CardMenuTextures.drawButton(ctx, "market/right_arrow", false, this.guiLeft + 419, this.guiTop + 178, 19, 25);
/*     */   }
/*     */ 
/*     */   
/*     */   private void drawHeader(class_332 ctx, CardMarketDto.Snapshot snap, int mouseX, int mouseY) {
/* 143 */     String bal = "" + snap.viewerCoins() + " PC   •   " + snap.viewerCoins() + " credit";
/* 144 */     int bw = this.field_22793.method_1727(bal);
/* 145 */     ctx.method_51433(this.field_22793, bal, this.guiLeft + 392 - bw, this.guiTop + 25, CardCollectionColors.TEXT_DIM, false);
/*     */ 
/*     */     
/* 148 */     int ty = this.guiTop + 40;
/* 149 */     this.allTabX = this.guiLeft + 22; this.allTabW = 35;
/* 150 */     this.mineTabX = this.guiLeft + 60; this.mineTabW = 83;
/* 151 */     CardMenuTextures.drawButton(ctx, "market/all_button", "all".equals(this.filter), this.allTabX, ty, 35, 18);
/* 152 */     CardMenuTextures.drawButton(ctx, "market/mylistings_button", "mine".equals(this.filter), this.mineTabX, ty, 83, 18);
/*     */ 
/*     */     
/* 155 */     this.closeX = this.guiLeft + 398; this.closeY = this.guiTop + 17;
/* 156 */     boolean ch = inRect(mouseX, mouseY, this.closeX, this.closeY, this.closeW, this.closeH);
/* 157 */     CardMenuTextures.drawButton(ctx, "market/close_button", ch, this.closeX, this.closeY, 31, 20);
/*     */   }
/*     */   
/*     */   private void tab(class_332 ctx, int x, int y, int w, String label, boolean active, int mouseX, int mouseY) {
/* 161 */     boolean h = inRect(mouseX, mouseY, x, y, w, 13);
/* 162 */     ctx.method_25294(x, y, x + w, y + 13, active ? CardCollectionColors.darken(CardCollectionColors.ACCENT_GOLD, 150) : (h ? CardCollectionColors.BUTTON_HOVER : CardCollectionColors.BUTTON_BG));
/* 163 */     ctx.method_49601(x, y, w, 13, active ? CardCollectionColors.ACCENT_GOLD : CardCollectionColors.BUTTON_BORDER);
/* 164 */     ctx.method_51433(this.field_22793, label, x + 7, y + 3, active ? CardCollectionColors.ACCENT_GOLD : CardCollectionColors.TEXT_DIM, false);
/*     */   }
/*     */   
/*     */   private void drawTile(class_332 ctx, CardMarketDto.Listing l, int index, int x, int y, int mouseX, int mouseY, int clipTop, int clipBottom) {
/* 168 */     boolean hover = (inRect(mouseX, mouseY, x, y, 56, 78) && mouseY >= clipTop && mouseY < clipBottom);
/* 169 */     if (hover) this.hovered = index;
/*     */     
/* 171 */     class_2960 tex = CardTextures.parse(l.texture());
/* 172 */     boolean hasArt = CardTextures.exists(tex);
/* 173 */     int rc = CardCollectionColors.rarityColor(l.rarity());
/* 174 */     CardRender.card(ctx, this.field_22793, x, y, 56, 78, tex, true, hasArt, rc, l.number(), shortName(l.cardName()));
/* 175 */     CardRender.frame(ctx, x, y, 56, 78, hover ? CardCollectionColors.BORDER_HIGHLIGHT : rc, hover ? 2 : 1);
/* 176 */     if (l.serial() > 0) {
/* 177 */       String s = "✦" + l.serial();
/* 178 */       int sw = this.field_22793.method_1727(s) + 4;
/* 179 */       ctx.method_25294(x + 56 - sw - 1, y + 1, x + 56 - 1, y + 10, CardCollectionColors.withAlpha(-16777216, 200));
/* 180 */       ctx.method_51433(this.field_22793, s, x + 56 - sw + 1, y + 2, CardCollectionColors.ACCENT_GOLD, false);
/*     */     } 
/* 182 */     if (l.mine()) {
/* 183 */       ctx.method_25294(x + 1, y + 1, x + 18, y + 9, CardCollectionColors.withAlpha(CardCollectionColors.ACCENT_GREEN, 220));
/* 184 */       ctx.method_51433(this.field_22793, "MINE", x + 2, y + 2, -15458288, false);
/*     */     } 
/*     */ 
/*     */     
/* 188 */     String price = l.isCoins() ? ("" + l.priceCoins() + " PC") : usd(l.priceUsdCents());
/* 189 */     ctx.method_25300(this.field_22793, price, x + 28, y + 78 + 2, l.isCoins() ? CardCollectionColors.ACCENT_GOLD : CardCollectionColors.ACCENT_GREEN);
/* 190 */     ctx.method_25300(this.field_22793, l.sellerName(), x + 28, y + 78 + 11, CardCollectionColors.TEXT_MUTED);
/*     */   }
/*     */ 
/*     */   
/*     */   private void drawDetail(class_332 ctx, CardMarketDto.Snapshot snap, CardMarketDto.Listing l, int mouseX, int mouseY) {
/* 195 */     int previewH = Math.max(170, Math.min(this.field_22790 - 90, 300));
/* 196 */     int previewW = Math.round(previewH * 0.718F);
/* 197 */     int infoW = 196;
/* 198 */     int headerH = 22, pad = 16, gap = 18;
/* 199 */     int pw = pad + previewW + gap + infoW + pad;
/* 200 */     int ph = headerH + previewH + pad;
/* 201 */     int px = (this.field_22789 - pw) / 2, py = (this.field_22790 - ph) / 2;
/*     */     
/* 203 */     CardMenuTextures.draw(ctx, "market/pop-up/popup_background", px, py, pw, ph);
/* 204 */     ctx.method_51433(this.field_22793, l.cardName(), px + 8, py + 7, CardCollectionColors.ACCENT_GOLD, true);
/*     */     
/* 206 */     int rc = CardCollectionColors.rarityColor(l.rarity());
/* 207 */     int prX = px + pad, prY = py + headerH + (ph - headerH - previewH) / 2;
/* 208 */     class_2960 tex = CardTextures.parse(l.texture());
/* 209 */     ctx.method_25294(prX - 2, prY - 2, prX + previewW + 2, prY + previewH + 2, CardCollectionColors.darken(rc, 120));
/* 210 */     CardRender.card(ctx, this.field_22793, prX, prY, previewW, previewH, tex, true, CardTextures.exists(tex), rc, l.number(), shortName(l.cardName()));
/*     */     
/* 212 */     int ix = prX + previewW + gap, iy = py + headerH + 10;
/* 213 */     ctx.method_51433(this.field_22793, "#" + l.number(), ix, iy, CardCollectionColors.TEXT_DIM, false);
/* 214 */     ctx.method_51433(this.field_22793, l.rarityName(), ix, iy + 14, rc, true);
/* 215 */     if (l.serial() > 0) ctx.method_51433(this.field_22793, "✦ Mint #" + l.serial(), ix, iy + 28, CardCollectionColors.ACCENT_GOLD, true); 
/* 216 */     ctx.method_51433(this.field_22793, "Seller: " + l.sellerName(), ix, iy + 44, CardCollectionColors.TEXT_DIM, false);
/* 217 */     String price = l.isCoins() ? ("" + l.priceCoins() + " PokéCoins") : (usd(l.priceUsdCents()) + " (gift card)");
/* 218 */     ctx.method_51433(this.field_22793, "Price: " + price, ix, iy + 58, l.isCoins() ? CardCollectionColors.ACCENT_GOLD : CardCollectionColors.ACCENT_GREEN, true);
/*     */ 
/*     */     
/* 221 */     int actionY = py + ph - pad - 18;
/* 222 */     if (l.mine()) {
/* 223 */       this.delistX = ix; this.delistY = actionY; this.delistW = infoW; this.delistH = 18;
/* 224 */       this.buyW = 0;
/* 225 */       button(ctx, this.delistX, this.delistY, this.delistW, this.delistH, "Delist (return card)", true, 
/* 226 */           inRect(mouseX, mouseY, this.delistX, this.delistY, this.delistW, this.delistH), CardCollectionColors.ACCENT_RED);
/* 227 */     } else if (l.isCoins()) {
/* 228 */       this.delistW = 0;
/* 229 */       boolean afford = (snap.viewerCoins() >= l.priceCoins());
/* 230 */       this.buyX = ix; this.buyY = actionY; this.buyW = infoW; this.buyH = 18;
/* 231 */       button(ctx, this.buyX, this.buyY, this.buyW, this.buyH, afford ? ("Buy — " + l.priceCoins() + " PC") : "Not enough PokéCoins", afford, 
/* 232 */           inRect(mouseX, mouseY, this.buyX, this.buyY, this.buyW, this.buyH), CardCollectionColors.ACCENT_GOLD);
/*     */     } else {
/*     */       
/* 235 */       this.delistW = 0;
/* 236 */       this.codeField.method_46421(ix);
/* 237 */       this.codeField.method_46419(actionY - 22);
/* 238 */       this.codeField.method_25358(infoW);
/* 239 */       this.codeField.method_53533(16);
/* 240 */       this.codeField.method_1862(true);
/* 241 */       this.codeField.method_25394(ctx, mouseX, mouseY, 0.0F);
/* 242 */       ctx.method_51433(this.field_22793, "Enter a PayNow gift-card code:", ix, actionY - 34, CardCollectionColors.TEXT_DIM, false);
/* 243 */       this.buyX = ix; this.buyY = actionY; this.buyW = infoW; this.buyH = 18;
/* 244 */       boolean hasCode = !this.codeField.method_1882().isBlank();
/* 245 */       button(ctx, this.buyX, this.buyY, this.buyW, this.buyH, hasCode ? ("Pay " + usd(l.priceUsdCents()) + " with gift card") : "Enter a code", hasCode, 
/* 246 */           inRect(mouseX, mouseY, this.buyX, this.buyY, this.buyW, this.buyH), CardCollectionColors.ACCENT_GREEN);
/*     */     } 
/*     */ 
/*     */     
/* 250 */     this.detailCloseSz = 14;
/* 251 */     this.detailCloseX = px + pw - this.detailCloseSz - 5; this.detailCloseY = py + 4;
/* 252 */     boolean hc = inRect(mouseX, mouseY, this.detailCloseX, this.detailCloseY, this.detailCloseSz, this.detailCloseSz);
/* 253 */     ctx.method_25294(this.detailCloseX, this.detailCloseY, this.detailCloseX + this.detailCloseSz, this.detailCloseY + this.detailCloseSz, hc ? CardCollectionColors.BUTTON_HOVER : CardCollectionColors.BUTTON_BG);
/* 254 */     ctx.method_25300(this.field_22793, "✖", this.detailCloseX + this.detailCloseSz / 2, this.detailCloseY + 3, CardCollectionColors.ACCENT_RED);
/*     */     
/* 256 */     drawResultBanner(ctx);
/*     */   }
/*     */   
/*     */   private void drawResultBanner(class_332 ctx) {
/* 260 */     CardMarketDto.Result r = CardMarketClientData.getInstance().getLastResult();
/* 261 */     if (r == null || r.message() == null || r.message().isBlank())
/* 262 */       return;  long age = System.currentTimeMillis() - CardMarketClientData.getInstance().getLastResultAt();
/* 263 */     if (age > 5000L) { CardMarketClientData.getInstance().clearResult(); return; }
/* 264 */      int color = r.ok() ? CardCollectionColors.ACCENT_GREEN : CardCollectionColors.ACCENT_RED;
/* 265 */     int w = Math.min(446, this.field_22793.method_1727(r.message()) + 16);
/* 266 */     int x = this.guiLeft + (454 - w) / 2;
/* 267 */     int y = this.guiTop + 368 - 30 + 4;
/* 268 */     ctx.method_25300(this.field_22793, trim(r.message(), w), this.guiLeft + 227, y, color);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean method_25402(double mx, double my, int button) {
/* 274 */     if (button != 0) return super.method_25402(mx, my, button); 
/* 275 */     CardMarketDto.Snapshot snap = data();
/*     */     
/* 277 */     if (this.selectedId != null) {
/* 278 */       CardMarketDto.Listing sel = find(snap, this.selectedId);
/* 279 */       if (sel != null) {
/* 280 */         if (inRect(mx, my, this.detailCloseX, this.detailCloseY, this.detailCloseSz, this.detailCloseSz)) { play(0.8F); this.selectedId = null; this.codeField.method_1862(false); return true; }
/* 281 */          if (!sel.isCoins() && !sel.mine() && this.codeField.method_1885() && this.codeField.method_25402(mx, my, button)) return true; 
/* 282 */         if (sel.mine() && this.delistW > 0 && inRect(mx, my, this.delistX, this.delistY, this.delistW, this.delistH)) {
/* 283 */           play(0.8F); CardMarketNetwork.delist(sel.id()); this.selectedId = null; this.codeField.method_1862(false); return true;
/*     */         } 
/* 285 */         if (!sel.mine() && this.buyW > 0 && inRect(mx, my, this.buyX, this.buyY, this.buyW, this.buyH)) {
/* 286 */           if (sel.isCoins())
/* 287 */           { if (snap.viewerCoins() >= sel.priceCoins()) { play(1.0F); CardMarketNetwork.buy(sel.id(), ""); this.selectedId = null; }
/*     */              }
/* 289 */           else { String code = this.codeField.method_1882().trim();
/* 290 */             if (!code.isBlank()) { play(1.0F); CardMarketNetwork.buy(sel.id(), code); this.selectedId = null; this.codeField.method_1862(false); }
/*     */              }
/* 292 */            return true;
/*     */         } 
/* 294 */         return true;
/*     */       } 
/* 296 */       this.selectedId = null;
/*     */     } 
/*     */ 
/*     */     
/* 300 */     if (inRect(mx, my, this.allTabX, this.guiTop + 40, this.allTabW, 18)) { switchFilter("all"); return true; }
/* 301 */      if (inRect(mx, my, this.mineTabX, this.guiTop + 40, this.mineTabW, 18)) { switchFilter("mine"); return true; }
/*     */     
/* 303 */     if (inRect(mx, my, this.closeX, this.closeY, this.closeW, this.closeH)) { method_25419(); return true; }
/*     */ 
/*     */     
/* 306 */     if (this.hovered >= 0 && this.hovered < snap.listings().size()) {
/* 307 */       play(0.7F);
/* 308 */       this.selectedId = ((CardMarketDto.Listing)snap.listings().get(this.hovered)).id();
/* 309 */       this.codeField.method_1852("");
/* 310 */       return true;
/*     */     } 
/* 312 */     return super.method_25402(mx, my, button);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean method_25401(double mx, double my, double h, double v) {
/* 317 */     if (this.selectedId != null) return true; 
/* 318 */     int rows = (data().listings().size() + 5 - 1) / 5;
/* 319 */     int contentH = rows * 100;
/* 320 */     int viewH = 278;
/* 321 */     int max = Math.max(0, contentH - viewH);
/* 322 */     this.scroll = (float)Math.max(0.0D, Math.min(max, this.scroll - v * 22.0D));
/* 323 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean method_25404(int k, int sc, int m) {
/* 328 */     if (k == 256) {
/* 329 */       if (this.selectedId != null) { this.selectedId = null; this.codeField.method_1862(false); return true; }
/* 330 */        method_25419();
/* 331 */       return true;
/*     */     } 
/* 333 */     if (this.codeField.method_1885() && this.codeField.method_25370() && this.codeField.method_25404(k, sc, m)) return true; 
/* 334 */     return super.method_25404(k, sc, m);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean method_25400(char c, int m) {
/* 339 */     if (this.codeField.method_1885() && this.codeField.method_25370() && this.codeField.method_25400(c, m)) return true; 
/* 340 */     return super.method_25400(c, m);
/*     */   }
/*     */   
/*     */   private void switchFilter(String f) {
/* 344 */     if (f.equals(this.filter))
/* 345 */       return;  play(0.9F);
/* 346 */     this.filter = f;
/* 347 */     this.scroll = 0.0F;
/* 348 */     CardMarketNetwork.requestMarket(f);
/*     */   }
/*     */ 
/*     */   
/*     */   private void button(class_332 ctx, int x, int y, int w, int h, String label, boolean enabled, boolean hover, int accent) {
/* 353 */     int bg = !enabled ? CardCollectionColors.BUTTON_DISABLED : (hover ? CardCollectionColors.BUTTON_HOVER : CardCollectionColors.BUTTON_BG);
/* 354 */     ctx.method_25294(x, y, x + w, y + h, bg);
/* 355 */     ctx.method_49601(x, y, w, h, !enabled ? CardCollectionColors.BORDER_DIM : (hover ? CardCollectionColors.BORDER_HIGHLIGHT : CardCollectionColors.BUTTON_BORDER));
/* 356 */     ctx.method_25300(this.field_22793, label, x + w / 2, y + (h - 8) / 2, !enabled ? CardCollectionColors.TEXT_MUTED : (hover ? CardCollectionColors.TEXT_WHITE : accent));
/*     */   }
/*     */   
/*     */   private static CardMarketDto.Listing find(CardMarketDto.Snapshot snap, String id) {
/* 360 */     for (CardMarketDto.Listing l : snap.listings()) { if (l.id().equals(id)) return l;  }
/* 361 */      return null;
/*     */   }
/*     */   
/*     */   private static boolean inRect(double mx, double my, int x, int y, int w, int h) {
/* 365 */     return (w > 0 && mx >= x && mx < (x + w) && my >= y && my < (y + h));
/*     */   }
/*     */   private static String usd(long cents) {
/* 368 */     return String.format(Locale.US, "$%.2f", new Object[] { Double.valueOf(cents / 100.0D) });
/*     */   } private static String shortName(String n) {
/* 370 */     return (n == null) ? "" : ((n.length() <= 9) ? n : (n.substring(0, 8) + "…"));
/*     */   }
/*     */   private String trim(String s, int maxW) {
/* 373 */     if (this.field_22793.method_1727(s) <= maxW) return s; 
/* 374 */     for (; s.length() > 1 && this.field_22793.method_1727(s + "…") > maxW; s = s.substring(0, s.length() - 1));
/* 375 */     return s + "…";
/*     */   }
/*     */   
/*     */   private void play(float pitch) {
/* 379 */     class_310.method_1551().method_1483().method_4873((class_1113)class_1109.method_47978((class_6880)class_3417.field_15015, pitch));
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\cardmarket\screen\MarketplaceScreen.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */