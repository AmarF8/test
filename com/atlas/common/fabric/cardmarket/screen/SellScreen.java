/*     */ package com.atlas.common.fabric.cardmarket.screen;
/*     */ 
/*     */ import com.atlas.common.fabric.cardcollection.screen.CardCollectionColors;
/*     */ import com.atlas.common.fabric.cardcollection.screen.CardMenuTextures;
/*     */ import com.atlas.common.fabric.cardcollection.screen.CardRender;
/*     */ import com.atlas.common.fabric.cardcollection.screen.CardTextures;
/*     */ import com.atlas.common.fabric.cardmarket.network.CardMarketNetwork;
/*     */ import com.google.gson.JsonObject;
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
/*     */ public class SellScreen
/*     */   extends class_437 {
/*     */   private final class_437 parent;
/*     */   private final String cardId;
/*     */   private final String cardName;
/*     */   private final String rarity;
/*     */   private final String rarityName;
/*     */   private final String texture;
/*     */   private final int number;
/*     */   private final List<Integer> serials;
/*  34 */   private int serialIndex = 0;
/*     */   
/*     */   private boolean coins = true;
/*     */   
/*     */   private class_342 priceField;
/*     */   private int guiLeft;
/*     */   private int guiTop;
/*     */   private static final int GUI_W = 167;
/*     */   private static final int GUI_H = 250;
/*     */   private int coinsTabX;
/*     */   private int usdTabX;
/*     */   private int tabY;
/*     */   private int tabW;
/*     */   
/*     */   public SellScreen(class_437 parent, String cardId, int number, String cardName, String rarity, String rarityName, String texture, List<Integer> serials) {
/*  49 */     super((class_2561)class_2561.method_43470("List Card"));
/*  50 */     this.parent = parent;
/*  51 */     this.cardId = cardId;
/*  52 */     this.number = number;
/*  53 */     this.cardName = cardName;
/*  54 */     this.rarity = rarity;
/*  55 */     this.rarityName = rarityName;
/*  56 */     this.texture = texture;
/*  57 */     this.serials = (serials == null) ? List.<Integer>of() : serials;
/*     */   }
/*     */   private int listX; private int listY; private int listW; private int listH; private int cancelX; private int cancelY; private int prevSerX; private int nextSerX; private int serArrowY; private static final int SER_W = 14; private static final int SER_H = 12;
/*     */   
/*     */   private int selectedSerial() {
/*  62 */     if (this.serials.isEmpty()) return 0; 
/*  63 */     return ((Integer)this.serials.get(Math.max(0, Math.min(this.serialIndex, this.serials.size() - 1)))).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void method_25426() {
/*  68 */     super.method_25426();
/*  69 */     this.guiLeft = (this.field_22789 - 167) / 2;
/*  70 */     this.guiTop = (this.field_22790 - 250) / 2;
/*  71 */     this.priceField = new class_342(this.field_22793, this.guiLeft + 12, this.guiTop + 186, 143, 14, (class_2561)class_2561.method_43470("Price"));
/*  72 */     this.priceField.method_1880(12);
/*  73 */     this.priceField.method_47404((class_2561)class_2561.method_43470(this.coins ? "e.g. 5000" : "e.g. 4.99").method_27694(s -> s.method_36139(8415828)));
/*  74 */     this.priceField.method_1858(false);
/*  75 */     method_25429((class_364)this.priceField);
/*  76 */     method_48265((class_364)this.priceField);
/*     */   }
/*     */   
/*     */   public boolean method_25421() {
/*  80 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void method_25420(class_332 ctx, int mouseX, int mouseY, float delta) {}
/*     */   
/*     */   public void method_25394(class_332 ctx, int mouseX, int mouseY, float delta) {
/*  87 */     ctx.method_25294(0, 0, this.field_22789, this.field_22790, CardCollectionColors.withAlpha(-16777216, 200));
/*  88 */     CardMenuTextures.draw(ctx, "sell/pop-up/next/popup_background", this.guiLeft, this.guiTop, 167, 250);
/*     */ 
/*     */     
/*  91 */     int rc = CardCollectionColors.rarityColor(this.rarity);
/*  92 */     int prW = 88, prH = 124;
/*  93 */     int prX = this.guiLeft + 40, prY = this.guiTop + 10;
/*  94 */     class_2960 tex = CardTextures.parse(this.texture);
/*  95 */     ctx.method_25294(prX - 2, prY - 2, prX + prW + 2, prY + prH + 2, CardCollectionColors.darken(rc, 120));
/*  96 */     CardRender.card(ctx, this.field_22793, prX, prY, prW, prH, tex, true, CardTextures.exists(tex), rc, this.number, null);
/*     */ 
/*     */     
/*  99 */     int selSerial = selectedSerial();
/* 100 */     if (selSerial > 0) {
/* 101 */       if (this.serials.size() > 1) {
/* 102 */         this.serArrowY = this.guiTop + 143;
/* 103 */         this.prevSerX = this.guiLeft + 52;
/* 104 */         this.nextSerX = this.prevSerX + 14 + 4;
/* 105 */         serArrow(ctx, this.prevSerX, this.serArrowY, "<", mouseX, mouseY);
/* 106 */         serArrow(ctx, this.nextSerX, this.serArrowY, ">", mouseX, mouseY);
/* 107 */         ctx.method_51433(this.field_22793, "" + this.serialIndex + 1 + "/" + this.serialIndex + 1, this.nextSerX + 14 + 6, this.guiTop + 146, CardCollectionColors.TEXT_DIM, false);
/*     */       } else {
/*     */         
/* 110 */         this.prevSerX = this.nextSerX = -1;
/*     */       } 
/*     */     } else {
/* 113 */       this.prevSerX = this.nextSerX = -1;
/*     */     } 
/*     */ 
/*     */     
/* 117 */     this.tabY = this.guiTop + 164;
/* 118 */     this.tabW = 65;
/* 119 */     this.coinsTabX = this.guiLeft + 8; this.usdTabX = this.guiLeft + 93;
/* 120 */     currencyTab(ctx, this.coinsTabX, this.tabY, "PokéCoins", this.coins, mouseX, mouseY);
/* 121 */     currencyTab(ctx, this.usdTabX, this.tabY, "Gift card", !this.coins, mouseX, mouseY);
/*     */     
/* 123 */     CardMenuTextures.draw(ctx, "sell/pop-up/next/text", this.guiLeft + 8, this.guiTop + 183, 151, 19);
/* 124 */     int fieldW = 143;
/* 125 */     this.priceField.method_46421(this.guiLeft + 12);
/* 126 */     this.priceField.method_46419(this.guiTop + 186);
/* 127 */     this.priceField.method_25358(fieldW);
/* 128 */     this.priceField.method_25394(ctx, mouseX, mouseY, delta);
/*     */     
/* 130 */     long preview = parsePrice();
/*     */ 
/*     */     
/* 133 */     this.listW = 151; this.listH = 18;
/* 134 */     this.listX = this.guiLeft + 8; this.listY = this.guiTop + 217;
/* 135 */     this.cancelX = this.guiLeft + 4; this.cancelY = this.guiTop + 4;
/* 136 */     boolean valid = (preview > 0L);
/* 137 */     CardMenuTextures.drawButton(ctx, "sell/pop-up/next/listforsale_button", (valid && 
/* 138 */         inRect(mouseX, mouseY, this.listX, this.listY, this.listW, this.listH)), this.listX, this.listY, this.listW, this.listH);
/* 139 */     if (!valid) ctx.method_25294(this.listX, this.listY, this.listX + this.listW, this.listY + this.listH, CardCollectionColors.withAlpha(-16777216, 120)); 
/* 140 */     CardMenuTextures.drawButton(ctx, "sell/pop-up/next/back_button", 
/* 141 */         inRect(mouseX, mouseY, this.cancelX, this.cancelY, 26, 20), this.cancelX, this.cancelY, 26, 20);
/*     */     
/* 143 */     super.method_25394(ctx, mouseX, mouseY, delta);
/*     */   }
/*     */   
/*     */   private void serArrow(class_332 ctx, int x, int y, String glyph, int mouseX, int mouseY) {
/* 147 */     boolean h = inRect(mouseX, mouseY, x, y, 14, 12);
/* 148 */     ctx.method_25294(x, y, x + 14, y + 12, h ? CardCollectionColors.BUTTON_HOVER : CardCollectionColors.BUTTON_BG);
/* 149 */     ctx.method_49601(x, y, 14, 12, h ? CardCollectionColors.ACCENT_GOLD : CardCollectionColors.BUTTON_BORDER);
/* 150 */     ctx.method_25300(this.field_22793, glyph, x + 7, y + 2, h ? CardCollectionColors.TEXT_WHITE : CardCollectionColors.ACCENT_GOLD);
/*     */   }
/*     */   
/*     */   private void currencyTab(class_332 ctx, int x, int y, String label, boolean active, int mouseX, int mouseY) {
/* 154 */     boolean h = inRect(mouseX, mouseY, x, y, this.tabW, 18);
/* 155 */     String path = label.startsWith("Pok") ? "sell/pop-up/next/pokecoins_button" : "sell/pop-up/next/giftcard_button";
/* 156 */     CardMenuTextures.drawButton(ctx, path, (active || h), x, y, this.tabW, 18);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean method_25402(double mx, double my, int b) {
/* 161 */     if (b == 0) {
/* 162 */       if (this.serials.size() > 1 && this.prevSerX >= 0) {
/* 163 */         if (inRect(mx, my, this.prevSerX, this.serArrowY, 14, 12)) {
/* 164 */           this.serialIndex = (this.serialIndex - 1 + this.serials.size()) % this.serials.size(); play(1.1F); return true;
/*     */         } 
/* 166 */         if (inRect(mx, my, this.nextSerX, this.serArrowY, 14, 12)) {
/* 167 */           this.serialIndex = (this.serialIndex + 1) % this.serials.size(); play(1.1F); return true;
/*     */         } 
/*     */       } 
/* 170 */       if (inRect(mx, my, this.coinsTabX, this.tabY, this.tabW, 18)) { this.coins = true; play(0.9F); return true; }
/* 171 */        if (inRect(mx, my, this.usdTabX, this.tabY, this.tabW, 18)) { this.coins = false; play(0.9F); return true; }
/* 172 */        if (this.priceField.method_25402(mx, my, b)) return true; 
/* 173 */       if (inRect(mx, my, this.cancelX, this.cancelY, 26, 20)) { method_25419(); return true; }
/* 174 */        if (inRect(mx, my, this.listX, this.listY, this.listW, this.listH) && parsePrice() > 0L) {
/* 175 */         submit();
/* 176 */         return true;
/*     */       } 
/*     */     } 
/* 179 */     return super.method_25402(mx, my, b);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean method_25404(int k, int sc, int m) {
/* 184 */     if (k == 256) { method_25419(); return true; }
/* 185 */      if (this.priceField.method_25370() && this.priceField.method_25404(k, sc, m)) return true; 
/* 186 */     return super.method_25404(k, sc, m);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean method_25400(char c, int m) {
/* 191 */     if (this.priceField.method_25370() && this.priceField.method_25400(c, m)) return true; 
/* 192 */     return super.method_25400(c, m);
/*     */   }
/*     */ 
/*     */   
/*     */   public void method_25419() {
/* 197 */     class_310.method_1551().method_1507(this.parent);
/*     */   }
/*     */   
/*     */   private void submit() {
/* 201 */     long price = parsePrice();
/* 202 */     if (price <= 0L)
/* 203 */       return;  play(1.0F);
/* 204 */     JsonObject o = new JsonObject();
/* 205 */     o.addProperty("cardId", this.cardId);
/* 206 */     o.addProperty("serial", Integer.valueOf(selectedSerial()));
/* 207 */     o.addProperty("kind", this.coins ? "COINS" : "GIFTCARD");
/* 208 */     o.addProperty("priceCoins", Long.valueOf(this.coins ? price : 0L));
/* 209 */     o.addProperty("priceUsdCents", Long.valueOf(this.coins ? 0L : price));
/* 210 */     CardMarketNetwork.listCard(o.toString());
/*     */     
/* 212 */     class_310.method_1551().method_1507(new MarketplaceScreen());
/*     */   }
/*     */ 
/*     */   
/*     */   private long parsePrice() {
/* 217 */     String raw = this.priceField.method_1882().trim();
/* 218 */     if (raw.isEmpty()) return 0L; 
/*     */     try {
/* 220 */       if (this.coins) {
/* 221 */         long v = Long.parseLong(raw.replaceAll("[^0-9]", ""));
/* 222 */         return (v > 0L) ? v : 0L;
/*     */       } 
/* 224 */       double usd = Double.parseDouble(raw.replaceAll("[^0-9.]", ""));
/* 225 */       long cents = Math.round(usd * 100.0D);
/* 226 */       return (cents > 0L) ? cents : 0L;
/* 227 */     } catch (Exception e) {
/* 228 */       return 0L;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void button(class_332 ctx, int x, int y, int w, int h, String label, boolean enabled, boolean hover, int accent) {
/* 233 */     int bg = !enabled ? CardCollectionColors.BUTTON_DISABLED : (hover ? CardCollectionColors.BUTTON_HOVER : CardCollectionColors.BUTTON_BG);
/* 234 */     ctx.method_25294(x, y, x + w, y + h, bg);
/* 235 */     ctx.method_49601(x, y, w, h, !enabled ? CardCollectionColors.BORDER_DIM : (hover ? CardCollectionColors.BORDER_HIGHLIGHT : CardCollectionColors.BUTTON_BORDER));
/* 236 */     ctx.method_25300(this.field_22793, label, x + w / 2, y + (h - 8) / 2, !enabled ? CardCollectionColors.TEXT_MUTED : (hover ? CardCollectionColors.TEXT_WHITE : accent));
/*     */   }
/*     */   
/*     */   private static boolean inRect(double mx, double my, int x, int y, int w, int h) {
/* 240 */     return (mx >= x && mx < (x + w) && my >= y && my < (y + h));
/*     */   }
/*     */   private static String usd(long cents) {
/* 243 */     return String.format(Locale.US, "$%.2f", new Object[] { Double.valueOf(cents / 100.0D) });
/*     */   }
/*     */   private String trim(String s, int maxW) {
/* 246 */     if (this.field_22793.method_1727(s) <= maxW) return s; 
/* 247 */     for (; s.length() > 1 && this.field_22793.method_1727(s + "...") > maxW; s = s.substring(0, s.length() - 1));
/* 248 */     return s + "...";
/*     */   }
/*     */   
/*     */   private void play(float pitch) {
/* 252 */     class_310.method_1551().method_1483().method_4873((class_1113)class_1109.method_47978((class_6880)class_3417.field_15015, pitch));
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\cardmarket\screen\SellScreen.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */