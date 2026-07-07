/*     */ package com.atlas.common.fabric.cardcollection.screen;
/*     */ 
/*     */ import com.atlas.common.fabric.cardcollection.client.CardCollectionClientData;
/*     */ import com.atlas.common.fabric.cardcollection.client.CardCollectionDto;
/*     */ import com.atlas.common.fabric.cardcollection.network.CardCollectionNetwork;
/*     */ import com.atlas.common.fabric.cardmarket.screen.SellScreen;
/*     */ import java.util.ArrayList;
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
/*     */ public class BinderScreen
/*     */   extends class_437
/*     */ {
/*     */   private static final int COLS = 6;
/*     */   private static final int ROWS = 3;
/*     */   static final int PER_PAGE = 18;
/*     */   private static final int CELL_W = 60;
/*     */   private static final int CELL_H = 80;
/*     */   private static final int GAP_X = 4;
/*     */   private static final int GAP_Y = 4;
/*     */   private static final int GRID_W = 380;
/*     */   private static final int GRID_H = 248;
/*     */   private static final int PAD = 37;
/*     */   private static final int HEADER_H = 67;
/*     */   private static final int FOOTER_H = 30;
/*     */   private static final int GRID_TOP_INSET = 0;
/*     */   private static final int GUI_W = 454;
/*     */   private static final int GUI_H = 368;
/*     */   private static final int ARROW_W = 19;
/*     */   private static final int ARROW_H = 25;
/*     */   private int guiLeft;
/*     */   private int guiTop;
/*     */   private int gridLeft;
/*     */   private int gridTop;
/*  65 */   private String search = "";
/*     */   
/*     */   private class_342 searchField;
/*  68 */   private int hoveredCell = -1;
/*  69 */   private String selectedCardId = null;
/*     */   private int backX;
/*     */   private int backY;
/*  72 */   private int backW = 60, backH = 20; private int packX; private int packY;
/*  73 */   private int packW = 96; private int packH = 16; private boolean prevHover; private boolean nextHover; private boolean backHover; private boolean packHover;
/*     */   private int burnOneX;
/*     */   private int burnOneY;
/*     */   private int burnOneW;
/*     */   private int burnOneH;
/*     */   private int burnDupX;
/*     */   private int burnDupY;
/*     */   private int burnDupW;
/*     */   private int burnDupH;
/*     */   
/*     */   public BinderScreen() {
/*  84 */     super((class_2561)class_2561.method_43470("Binder"));
/*     */   }
/*     */   private int sellX; private int sellY; private int sellW; private int sellH; private int detailPanelX; private int detailPanelY; private int detailPanelW; private int detailPanelH; private int detailCloseX; private int detailCloseY; private int detailCloseW; private int detailCloseH;
/*     */   
/*     */   protected void method_25426() {
/*  89 */     super.method_25426();
/*  90 */     this.guiLeft = (this.field_22789 - 454) / 2;
/*  91 */     this.guiTop = (this.field_22790 - 368) / 2;
/*  92 */     this.gridLeft = this.guiLeft + 37;
/*  93 */     this.gridTop = this.guiTop + 67 + 0;
/*     */     
/*  95 */     int sfW = 136, sfH = 14;
/*  96 */     this.searchField = new class_342(this.field_22793, this.guiLeft + 24, this.guiTop + 50, sfW, sfH, (class_2561)class_2561.method_43470("Search"));
/*  97 */     this.searchField.method_1880(32);
/*  98 */     this.searchField.method_47404((class_2561)class_2561.method_43470("Search name / #").method_27694(s -> s.method_36139(8415828)));
/*  99 */     this.searchField.method_1858(false);
/* 100 */     this.searchField.method_1852(this.search);
/* 101 */     this.searchField.method_1863(s -> this.search = (s == null) ? "" : s.toLowerCase(Locale.ENGLISH).trim());
/* 102 */     method_25429((class_364)this.searchField);
/*     */   }
/*     */   
/*     */   public boolean method_25421() {
/* 106 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void method_25420(class_332 ctx, int mouseX, int mouseY, float delta) {}
/*     */   
/*     */   public void method_25394(class_332 ctx, int mouseX, int mouseY, float delta) {
/* 113 */     CardCollectionDto.BinderData binder = CardCollectionClientData.getInstance().getBinder();
/*     */     
/* 115 */     ctx.method_25294(0, 0, this.field_22789, this.field_22790, CardCollectionColors.SCRIM);
/*     */     
/* 117 */     drawPanel(ctx);
/*     */     
/* 119 */     if (binder == null) {
/* 120 */       ctx.method_25300(this.field_22793, "Loading binder…", this.guiLeft + 227, this.guiTop + 184, CardCollectionColors.TEXT_DIM);
/* 121 */       super.method_25394(ctx, mouseX, mouseY, delta);
/* 122 */       CardCollectionToast.renderOverlay(ctx);
/*     */       
/*     */       return;
/*     */     } 
/* 126 */     drawHeader(ctx, binder, mouseX, mouseY);
/* 127 */     this.searchField.method_25394(ctx, mouseX, mouseY, delta);
/*     */     
/* 129 */     CardCollectionDto.BinderCard selected = (this.selectedCardId == null) ? null : findCard(binder, this.selectedCardId);
/* 130 */     if (this.selectedCardId != null && selected == null) this.selectedCardId = null; 
/* 131 */     boolean showingDetail = (selected != null);
/*     */ 
/*     */ 
/*     */     
/* 135 */     List<CardCollectionDto.BinderCard> cards = binder.cards();
/* 136 */     this.hoveredCell = -1;
/* 137 */     if (!showingDetail) {
/* 138 */       for (int i = 0; i < 18; i++) {
/* 139 */         int col = i % 6;
/* 140 */         int row = i / 6;
/* 141 */         int cx = this.gridLeft + col * 64;
/* 142 */         int cy = this.gridTop + row * 84;
/* 143 */         CardCollectionDto.BinderCard card = (i < cards.size()) ? cards.get(i) : null;
/* 144 */         drawCell(ctx, card, i, cx, cy, mouseX, mouseY);
/*     */       } 
/*     */     }
/*     */     
/* 148 */     drawFooter(ctx, binder, mouseX, mouseY);
/* 149 */     if (showingDetail) {
/* 150 */       drawDetailPanel(ctx, binder, selected, mouseX, mouseY);
/*     */     }
/*     */     
/* 153 */     if (!showingDetail && this.hoveredCell >= 0 && this.hoveredCell < cards.size()) {
/* 154 */       drawCardTooltip(ctx, binder, cards.get(this.hoveredCell), mouseX, mouseY);
/*     */     }
/*     */     
/* 157 */     super.method_25394(ctx, mouseX, mouseY, delta);
/* 158 */     CardCollectionToast.renderOverlay(ctx);
/*     */   }
/*     */   
/*     */   private void drawPanel(class_332 ctx) {
/* 162 */     CardMenuTextures.draw(ctx, "binder/background", this.guiLeft, this.guiTop, 454, 368);
/* 163 */     CardMenuTextures.draw(ctx, "binder/top_w_search_experiencebar", this.guiLeft + 20, this.guiTop + 36, 415, 37);
/* 164 */     CardMenuTextures.draw(ctx, "binder/lower_part", this.guiLeft + 20, this.guiTop + 318, 414, 30);
/*     */   }
/*     */ 
/*     */   
/*     */   private void drawHeader(class_332 ctx, CardCollectionDto.BinderData binder, int mouseX, int mouseY) {
/* 169 */     this.backX = this.guiLeft + 25;
/* 170 */     this.backY = this.guiTop + 323;
/* 171 */     this.backHover = inRect(mouseX, mouseY, this.backX, this.backY, this.backW, this.backH);
/* 172 */     CardMenuTextures.drawButton(ctx, "binder/sets_button", this.backHover, this.backX, this.backY, 60, 20);
/*     */     
/* 174 */     ctx.method_51433(this.field_22793, trim(binder.setName() + " Binder", 94), this.guiLeft + 28, this.guiTop + 26, CardCollectionColors.ACCENT_GOLD, true);
/*     */ 
/*     */     
/* 177 */     CardCollectionDto.SetOverview ov = findSet(binder.setId());
/* 178 */     int packs = (ov != null) ? ov.packsOwned() : 0;
/* 179 */     this.packW = 60;
/* 180 */     this.packX = this.guiLeft + 374;
/* 181 */     this.packY = this.guiTop + 20;
/* 182 */     this.packHover = inRect(mouseX, mouseY, this.packX, this.packY, this.packW, this.packH);
/* 183 */     CardMenuTextures.drawButton(ctx, "binder/open_pack_button", this.packHover, this.packX, this.packY, 60, 16);
/*     */     
/* 185 */     String stats = "" + binder.uniqueOwned() + "/" + binder.uniqueOwned() + " cards  •  " + binder.totalUnique() + " owned";
/* 186 */     ctx.method_51433(this.field_22793, stats, this.guiLeft + 171, this.guiTop + 37, CardCollectionColors.TEXT_DIM, false);
/*     */     
/* 188 */     int barX = this.guiLeft + 162;
/* 189 */     int barY = this.guiTop + 52;
/* 190 */     int barW = 268;
/* 191 */     if (barW > 40) {
/* 192 */       drawProgressBarTexture(ctx, barX, barY, barW, 7, binder.completion());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void drawCell(class_332 ctx, CardCollectionDto.BinderCard card, int index, int x, int y, int mouseX, int mouseY) {
/* 198 */     if (card == null) {
/* 199 */       drawEmptySlot(ctx, x, y);
/*     */       
/*     */       return;
/*     */     } 
/* 203 */     boolean dimmed = !matchesSearch(card);
/* 204 */     boolean hover = (!dimmed && inRect(mouseX, mouseY, x, y, 60, 80));
/* 205 */     if (hover) this.hoveredCell = index;
/*     */     
/* 207 */     if (!card.isOwned()) {
/* 208 */       drawEmptySlot(ctx, x, y);
/* 209 */       if (hover) CardRender.frame(ctx, x - 1, y - 1, 62, 82, CardCollectionColors.BORDER_HIGHLIGHT, 1); 
/* 210 */       if (dimmed) ctx.method_25294(x, y, x + 60, y + 80, CardCollectionColors.withAlpha(-16777216, 160));
/*     */       
/*     */       return;
/*     */     } 
/* 214 */     class_2960 tex = CardTextures.parse(card.texture());
/* 215 */     boolean hasArt = CardTextures.exists(tex);
/* 216 */     int rc = CardCollectionColors.rarityColor(card.rarity());
/*     */     
/* 218 */     CardRender.card(ctx, this.field_22793, x, y, 60, 80, tex, true, hasArt, rc, card
/* 219 */         .number(), shortName(card.name()));
/* 220 */     CardRender.countBadge(ctx, this.field_22793, x, y, 60, 80, card.owned());
/*     */ 
/*     */     
/* 223 */     if (card.hasSerial()) {
/* 224 */       String tag = (card.serialCount() > 1) ? ("✦" + card.serial() + "×" + card.serialCount()) : ("✦" + card.serial());
/* 225 */       int tw = this.field_22793.method_1727(tag) + 4;
/* 226 */       int bx = x + 60 - tw - 1;
/* 227 */       ctx.method_25294(bx, y + 1, bx + tw, y + 10, CardCollectionColors.withAlpha(-16777216, 200));
/* 228 */       ctx.method_25294(bx, y + 1, bx + tw, y + 2, CardCollectionColors.ACCENT_GOLD);
/* 229 */       ctx.method_51433(this.field_22793, tag, bx + 2, y + 2, CardCollectionColors.ACCENT_GOLD, false);
/*     */     } 
/*     */     
/* 232 */     if (hover) CardRender.frame(ctx, x - 1, y - 1, 62, 82, CardCollectionColors.BORDER_HIGHLIGHT, 1); 
/* 233 */     if (dimmed) ctx.method_25294(x, y, x + 60, y + 80, CardCollectionColors.withAlpha(-16777216, 160)); 
/*     */   }
/*     */   
/*     */   private void drawEmptySlot(class_332 ctx, int x, int y) {
/* 237 */     ctx.method_25294(x, y, x + 60, y + 80, CardCollectionColors.color(32, 32, 32, 255));
/* 238 */     CardRender.frame(ctx, x, y, 60, 80, CardCollectionColors.color(218, 218, 218, 255), 1);
/*     */   }
/*     */ 
/*     */   
/*     */   private void drawFooter(class_332 ctx, CardCollectionDto.BinderData binder, int mouseX, int mouseY) {
/* 243 */     int fy = this.guiTop + 368 - 30;
/* 244 */     int midY = this.guiTop + 178;
/* 245 */     int cx = this.guiLeft + 227;
/*     */     
/* 247 */     String page = "Page " + binder.page() + " / " + binder.totalPages();
/* 248 */     int pageW = this.field_22793.method_1727(page);
/*     */     
/* 250 */     int la = this.guiLeft + 16;
/* 251 */     int ra = (this.selectedCardId != null) ? (this.guiLeft + 247) : (this.guiLeft + 419);
/*     */     
/* 253 */     boolean canPrev = (binder.page() > 1);
/* 254 */     boolean canNext = (binder.page() < binder.totalPages());
/* 255 */     this.prevHover = (canPrev && inRect(mouseX, mouseY, la, midY, 19, 25));
/* 256 */     this.nextHover = (canNext && inRect(mouseX, mouseY, ra, midY, 19, 25));
/*     */     
/* 258 */     drawArrow(ctx, la, midY, true, canPrev, this.prevHover);
/* 259 */     drawArrow(ctx, ra, midY, false, canNext, this.nextHover);
/* 260 */     ctx.method_25300(this.field_22793, page, cx, fy + 11, CardCollectionColors.TEXT_PRIMARY);
/*     */     
/* 262 */     CardCollectionDto.BurnResult br = binder.burnResult();
/* 263 */     if (br != null && br.message() != null && !br.message().isBlank()) {
/* 264 */       int color = br.ok() ? CardCollectionColors.ACCENT_GREEN : CardCollectionColors.ACCENT_RED;
/* 265 */       ctx.method_25300(this.field_22793, br.message(), cx, fy - 10, color);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void drawArrow(class_332 ctx, int x, int y, boolean left, boolean enabled, boolean hover) {
/* 270 */     CardMenuTextures.drawButton(ctx, "binder/" + (left ? "left_arrow" : "right_arrow"), (enabled && hover), x, y, 19, 25);
/* 271 */     if (!enabled) ctx.method_25294(x, y, x + 19, y + 25, CardCollectionColors.withAlpha(-16777216, 120));
/*     */   
/*     */   }
/*     */   
/*     */   private void drawCardTooltip(class_332 ctx, CardCollectionDto.BinderData binder, CardCollectionDto.BinderCard card, int mouseX, int mouseY) {
/* 276 */     List<class_2561> lines = new ArrayList<>();
/* 277 */     lines.add(class_2561.method_43470(card.name()).method_27694(s -> s.method_36139(card.isOwned() ? 15655630 : 9073240).method_10982(Boolean.valueOf(true))));
/* 278 */     lines.add(class_2561.method_43470("#" + card.number() + " • " + binder.setName()).method_27694(s -> s.method_36139(11047542)));
/* 279 */     lines.add(class_2561.method_43470(card.rarityName()).method_27694(s -> s.method_36139(CardCollectionColors.rarityColor(card.rarity()) & 0xFFFFFF)));
/* 280 */     if (card.hasSerial()) {
/* 281 */       if (card.serialCount() > 1) {
/* 282 */         lines.add(class_2561.method_43470("✦ " + card.serialCount() + " copies / " + card.minted() + " minted")
/* 283 */             .method_27694(s -> s.method_36139(16765518).method_10982(Boolean.valueOf(true))));
/* 284 */         lines.add(class_2561.method_43470("   Mints: " + formatSerials(card.serials()))
/* 285 */             .method_27694(s -> s.method_36139(13150304)));
/*     */       } else {
/* 287 */         lines.add(class_2561.method_43470("✦ Mint #" + card.serial() + " / " + card.minted())
/* 288 */             .method_27694(s -> s.method_36139(16765518).method_10982(Boolean.valueOf(true))));
/*     */       } 
/* 290 */     } else if (card.serialized() && card.minted() > 0) {
/* 291 */       lines.add(class_2561.method_43470("✦ " + card.minted() + " pulled worldwide")
/* 292 */           .method_27694(s -> s.method_36139(11047542)));
/*     */     } 
/* 294 */     if (card.isOwned()) { lines.add(class_2561.method_43470("Owned: " + card.owned()).method_27694(s -> s.method_36139(7915630))); }
/* 295 */     else { lines.add(class_2561.method_43470("Not yet collected").method_27694(s -> s.method_36139(9073240))); }
/* 296 */      lines.add(class_2561.method_43470(card.id()).method_27694(s -> s.method_36139(5589048)));
/* 297 */     ctx.method_51434(this.field_22793, lines, mouseX, mouseY);
/*     */   }
/*     */ 
/*     */   
/*     */   private void drawDetailPanel(class_332 ctx, CardCollectionDto.BinderData binder, CardCollectionDto.BinderCard card, int mouseX, int mouseY) {
/* 302 */     this.detailPanelW = 167;
/* 303 */     this.detailPanelH = 250;
/* 304 */     this.detailPanelX = this.guiLeft + 268;
/* 305 */     this.detailPanelY = this.guiTop + 64;
/*     */     
/* 307 */     CardMenuTextures.draw(ctx, "sell/pop-up/popup_background", this.detailPanelX, this.detailPanelY, this.detailPanelW, this.detailPanelH);
/*     */     
/* 309 */     this.detailCloseX = this.detailPanelX + 4;
/* 310 */     this.detailCloseY = this.detailPanelY + 4;
/* 311 */     this.detailCloseW = 26;
/* 312 */     this.detailCloseH = 20;
/* 313 */     CardMenuTextures.drawButton(ctx, "sell/pop-up/back_button", 
/* 314 */         inRect(mouseX, mouseY, this.detailCloseX, this.detailCloseY, this.detailCloseW, this.detailCloseH), this.detailCloseX, this.detailCloseY, this.detailCloseW, this.detailCloseH);
/*     */ 
/*     */     
/* 317 */     int rc = CardCollectionColors.rarityColor(card.rarity());
/* 318 */     class_2960 tex = CardTextures.parse(card.texture());
/* 319 */     boolean hasArt = (card.isOwned() && CardTextures.exists(tex));
/* 320 */     int prH = 136;
/* 321 */     int prW = Math.round(prH * 0.718F);
/* 322 */     int prX = this.detailPanelX + (this.detailPanelW - prW) / 2;
/* 323 */     int prY = this.guiTop + 76;
/* 324 */     CardRender.card(ctx, this.field_22793, prX, prY, prW, prH, tex, card.isOwned(), hasArt, rc, card
/* 325 */         .number(), shortName(card.name()));
/*     */     
/* 327 */     this.sellW = 151;
/* 328 */     this.sellH = 18;
/* 329 */     this.sellX = this.guiLeft + 275;
/* 330 */     this.sellY = this.guiTop + 244;
/* 331 */     CardMenuTextures.drawButton(ctx, "sell/pop-up/sellonmarket_button", (card
/* 332 */         .isOwned() && inRect(mouseX, mouseY, this.sellX, this.sellY, this.sellW, this.sellH)), this.sellX, this.sellY, this.sellW, this.sellH);
/*     */     
/* 334 */     if (!card.isOwned()) ctx.method_25294(this.sellX, this.sellY, this.sellX + this.sellW, this.sellY + this.sellH, CardCollectionColors.withAlpha(-16777216, 120));
/*     */     
/* 336 */     this.burnOneW = 151;
/* 337 */     this.burnOneH = 18;
/* 338 */     this.burnOneX = this.guiLeft + 275;
/* 339 */     this.burnOneY = this.guiTop + 263;
/* 340 */     CardMenuTextures.drawButton(ctx, "sell/pop-up/burn_button", (card
/* 341 */         .owned() >= 1 && inRect(mouseX, mouseY, this.burnOneX, this.burnOneY, this.burnOneW, this.burnOneH)), this.burnOneX, this.burnOneY, this.burnOneW, this.burnOneH);
/*     */     
/* 343 */     if (card.owned() < 1) ctx.method_25294(this.burnOneX, this.burnOneY, this.burnOneX + this.burnOneW, this.burnOneY + this.burnOneH, CardCollectionColors.withAlpha(-16777216, 120));
/*     */     
/* 345 */     this.burnDupW = 151;
/* 346 */     this.burnDupH = 18;
/* 347 */     this.burnDupX = this.guiLeft + 275;
/* 348 */     this.burnDupY = this.guiTop + 282;
/* 349 */     CardMenuTextures.drawButton(ctx, "sell/pop-up/burn5_button", (card
/* 350 */         .owned() > 1 && inRect(mouseX, mouseY, this.burnDupX, this.burnDupY, this.burnDupW, this.burnDupH)), this.burnDupX, this.burnDupY, this.burnDupW, this.burnDupH);
/*     */     
/* 352 */     if (card.owned() <= 1) ctx.method_25294(this.burnDupX, this.burnDupY, this.burnDupX + this.burnDupW, this.burnDupY + this.burnDupH, CardCollectionColors.withAlpha(-16777216, 120));
/*     */     
/* 354 */     CardCollectionDto.BurnResult br = binder.burnResult();
/* 355 */     if (br != null && br.message() != null && !br.message().isBlank() && card.id().equals(br.cardId())) {
/* 356 */       int color = br.ok() ? CardCollectionColors.ACCENT_GREEN : CardCollectionColors.ACCENT_RED;
/* 357 */       ctx.method_25300(this.field_22793, trim(br.message(), 150), this.detailPanelX + this.detailPanelW / 2, this.detailPanelY + 145, color);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void button(class_332 ctx, int x, int y, int w, int h, String label, boolean enabled, boolean hover, int accent) {
/* 364 */     int bg = !enabled ? CardCollectionColors.BUTTON_DISABLED : (hover ? CardCollectionColors.BUTTON_HOVER : CardCollectionColors.BUTTON_BG);
/* 365 */     ctx.method_25294(x, y, x + w, y + h, bg);
/* 366 */     ctx.method_49601(x, y, w, h, !enabled ? CardCollectionColors.BORDER_DIM : (hover ? CardCollectionColors.BORDER_HIGHLIGHT : CardCollectionColors.BUTTON_BORDER));
/* 367 */     int tc = !enabled ? CardCollectionColors.TEXT_MUTED : (hover ? CardCollectionColors.TEXT_WHITE : accent);
/* 368 */     ctx.method_25300(this.field_22793, label, x + w / 2, y + (h - 8) / 2, tc);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean method_25402(double mouseX, double mouseY, int button) {
/* 374 */     if (button != 0) return super.method_25402(mouseX, mouseY, button); 
/* 375 */     CardCollectionDto.BinderData binder = CardCollectionClientData.getInstance().getBinder();
/* 376 */     if (binder == null) return super.method_25402(mouseX, mouseY, button);
/*     */     
/* 378 */     if (this.selectedCardId != null) {
/* 379 */       CardCollectionDto.BinderCard sel = findCard(binder, this.selectedCardId);
/* 380 */       if (sel != null) {
/* 381 */         if (inRect(mouseX, mouseY, this.detailCloseX, this.detailCloseY, this.detailCloseW, this.detailCloseH)) {
/* 382 */           playClick(1.0F); this.selectedCardId = null; return true;
/*     */         } 
/* 384 */         if (sel.owned() >= 1 && inRect(mouseX, mouseY, this.burnOneX, this.burnOneY, this.burnOneW, this.burnOneH)) {
/* 385 */           playBurn(); CardCollectionNetwork.burnCard(sel.id(), 1); return true;
/*     */         } 
/* 387 */         if (sel.owned() > 1 && inRect(mouseX, mouseY, this.burnDupX, this.burnDupY, this.burnDupW, this.burnDupH)) {
/* 388 */           playBurn(); CardCollectionNetwork.burnCard(sel.id(), Math.min(5, sel.owned() - 1)); return true;
/*     */         } 
/* 390 */         if (sel.isOwned() && this.sellW > 0 && inRect(mouseX, mouseY, this.sellX, this.sellY, this.sellW, this.sellH)) {
/* 391 */           playClick(0.9F);
/* 392 */           class_310.method_1551().method_1507((class_437)new SellScreen(this, sel
/* 393 */                 .id(), sel.number(), sel.name(), sel.rarity(), sel.rarityName(), sel.texture(), sel
/* 394 */                 .serials()));
/* 395 */           return true;
/*     */         } 
/* 397 */         if (this.prevHover) { this.selectedCardId = null; changePage(binder, -1); return true; }
/* 398 */          if (this.nextHover) { this.selectedCardId = null; changePage(binder, 1); return true; }
/* 399 */          if (inRect(mouseX, mouseY, this.detailPanelX, this.detailPanelY, this.detailPanelW, this.detailPanelH)) return true; 
/* 400 */         this.selectedCardId = null;
/* 401 */         return true;
/*     */       } 
/* 403 */       this.selectedCardId = null;
/*     */     } 
/*     */     
/* 406 */     if (this.searchField.method_25402(mouseX, mouseY, button)) return true;
/*     */     
/* 408 */     if (this.backHover) {
/* 409 */       playClick(0.8F);
/* 410 */       CardCollectionClientData.getInstance().clearBinder();
/* 411 */       CardCollectionNetwork.requestOverview();
/* 412 */       class_310.method_1551().method_1507(new CardCollectionScreen());
/* 413 */       return true;
/*     */     } 
/*     */     
/* 416 */     if (this.packHover) {
/* 417 */       CardCollectionDto.SetOverview ov = findSet(binder.setId());
/* 418 */       if (ov != null && ov.packsOwned() > 0) {
/* 419 */         playClick(0.9F);
/* 420 */         CardCollectionNetwork.openPack(binder.setId());
/*     */       } else {
/* 422 */         playClick(1.4F);
/*     */       } 
/* 424 */       return true;
/*     */     } 
/*     */     
/* 427 */     if (this.prevHover) { changePage(binder, -1); return true; }
/* 428 */      if (this.nextHover) { changePage(binder, 1); return true; }
/*     */     
/* 430 */     if (this.hoveredCell >= 0 && this.hoveredCell < binder.cards().size()) {
/* 431 */       playClick(0.7F);
/* 432 */       this.selectedCardId = ((CardCollectionDto.BinderCard)binder.cards().get(this.hoveredCell)).id();
/* 433 */       return true;
/*     */     } 
/*     */     
/* 436 */     return super.method_25402(mouseX, mouseY, button);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean method_25401(double mouseX, double mouseY, double h, double v) {
/* 441 */     CardCollectionDto.BinderData binder = CardCollectionClientData.getInstance().getBinder();
/* 442 */     if (binder != null && this.selectedCardId == null) {
/* 443 */       if (v < 0.0D) { changePage(binder, 1); }
/* 444 */       else if (v > 0.0D) { changePage(binder, -1); }
/* 445 */        return true;
/*     */     } 
/* 447 */     return super.method_25401(mouseX, mouseY, h, v);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean method_25404(int keyCode, int scanCode, int modifiers) {
/* 452 */     if (keyCode == 256) {
/* 453 */       if (this.selectedCardId != null) { this.selectedCardId = null; return true; }
/* 454 */        method_25419();
/* 455 */       return true;
/*     */     } 
/* 457 */     if (this.searchField.method_25370() && this.searchField.method_25404(keyCode, scanCode, modifiers)) return true; 
/* 458 */     return super.method_25404(keyCode, scanCode, modifiers);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean method_25400(char chr, int modifiers) {
/* 463 */     if (this.searchField.method_25370() && this.searchField.method_25400(chr, modifiers)) return true; 
/* 464 */     return super.method_25400(chr, modifiers);
/*     */   }
/*     */ 
/*     */   
/*     */   public void method_25419() {
/* 469 */     CardCollectionClientData.getInstance().clearBinder();
/* 470 */     super.method_25419();
/*     */   }
/*     */   
/*     */   private void changePage(CardCollectionDto.BinderData binder, int delta) {
/* 474 */     int target = binder.page() + delta;
/* 475 */     if (target < 1 || target > binder.totalPages())
/* 476 */       return;  playClick(1.0F);
/* 477 */     CardCollectionNetwork.requestBinder(binder.setId(), target);
/*     */   }
/*     */ 
/*     */   
/*     */   private void drawProgressBar(class_332 ctx, int x, int y, int w, int h, int percent) {
/* 482 */     ctx.method_25294(x, y, x + w, y + h, CardCollectionColors.SLOT_INNER);
/* 483 */     ctx.method_49601(x, y, w, h, CardCollectionColors.BORDER_DIM);
/* 484 */     int fill = (int)(Math.max(0, Math.min(100, percent)) / 100.0F * (w - 2));
/* 485 */     if (fill > 0) {
/* 486 */       int c = (percent >= 100) ? CardCollectionColors.ACCENT_GREEN : CardCollectionColors.ACCENT_GOLD;
/* 487 */       ctx.method_25294(x + 1, y + 1, x + 1 + fill, y + h - 1, c);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void drawProgressBarTexture(class_332 ctx, int x, int y, int w, int h, int percent) {
/* 492 */     CardMenuTextures.draw(ctx, "binder/experience_bar_empty", x - 1, y - 1, w + 2, h + 2);
/* 493 */     int fill = (int)(Math.max(0, Math.min(100, percent)) / 100.0D * w);
/* 494 */     if (fill <= 0)
/* 495 */       return;  ctx.method_44379(x, y, x + fill, y + h);
/* 496 */     CardMenuTextures.draw(ctx, "binder/experience", x, y, w, h);
/* 497 */     ctx.method_44380();
/*     */   }
/*     */   
/*     */   private boolean matchesSearch(CardCollectionDto.BinderCard card) {
/* 501 */     if (this.search.isEmpty()) return true; 
/* 502 */     return (card.name().toLowerCase(Locale.ENGLISH).contains(this.search) || 
/* 503 */       String.valueOf(card.number()).contains(this.search));
/*     */   }
/*     */ 
/*     */   
/*     */   private static String formatSerials(List<Integer> serials) {
/* 508 */     if (serials == null || serials.isEmpty()) return "—"; 
/* 509 */     int cap = Math.min(serials.size(), 8);
/* 510 */     StringBuilder sb = new StringBuilder();
/* 511 */     for (int i = 0; i < cap; i++) {
/* 512 */       if (i > 0) sb.append(", "); 
/* 513 */       sb.append('#').append(serials.get(i));
/*     */     } 
/* 515 */     if (serials.size() > cap) sb.append(", +").append(serials.size() - cap).append(" more"); 
/* 516 */     return sb.toString();
/*     */   }
/*     */   
/*     */   private static String shortName(String name) {
/* 520 */     if (name == null) return ""; 
/* 521 */     return (name.length() <= 9) ? name : (name.substring(0, 8) + "…");
/*     */   }
/*     */   
/*     */   private String trim(String text, int maxWidth) {
/* 525 */     if (this.field_22793.method_1727(text) <= maxWidth) return text; 
/* 526 */     String suffix = "…";
/* 527 */     int suffixWidth = this.field_22793.method_1727(suffix);
/* 528 */     StringBuilder out = new StringBuilder(text);
/* 529 */     while (!out.isEmpty() && this.field_22793.method_1727(out.toString()) + suffixWidth > maxWidth) {
/* 530 */       out.setLength(out.length() - 1);
/*     */     }
/* 532 */     return String.valueOf(out) + String.valueOf(out);
/*     */   }
/*     */   
/*     */   private static CardCollectionDto.SetOverview findSet(String id) {
/* 536 */     for (CardCollectionDto.SetOverview s : CardCollectionClientData.getInstance().getOverview().sets()) {
/* 537 */       if (s.id().equals(id)) return s; 
/*     */     } 
/* 539 */     return null;
/*     */   }
/*     */   
/*     */   private static boolean inRect(double mx, double my, int x, int y, int w, int h) {
/* 543 */     return (mx >= x && mx < (x + w) && my >= y && my < (y + h));
/*     */   }
/*     */   
/*     */   private static CardCollectionDto.BinderCard findCard(CardCollectionDto.BinderData binder, String id) {
/* 547 */     for (CardCollectionDto.BinderCard c : binder.cards()) { if (c.id().equals(id)) return c;  }
/* 548 */      return null;
/*     */   }
/*     */   
/*     */   private void playClick(float pitch) {
/* 552 */     class_310.method_1551().method_1483().method_4873(
/* 553 */         (class_1113)class_1109.method_47978((class_6880)class_3417.field_15015, pitch));
/*     */   }
/*     */   
/*     */   private void playBurn() {
/* 557 */     class_310.method_1551().method_1483().method_4873(
/* 558 */         (class_1113)class_1109.method_4758(class_3417.field_15102, 1.2F));
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\cardcollection\screen\BinderScreen.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */