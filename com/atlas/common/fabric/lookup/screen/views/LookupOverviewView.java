/*     */ package com.atlas.common.fabric.lookup.screen.views;
/*     */ 
/*     */ import com.atlas.common.fabric.guide.screen.widgets.GuideScrollableArea;
/*     */ import com.atlas.common.fabric.lookup.data.LookupClientData;
/*     */ import com.atlas.common.fabric.lookup.network.LookupNetwork;
/*     */ import com.atlas.common.fabric.lookup.screen.LookupColors;
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParser;
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.InputStream;
/*     */ import java.net.URI;
/*     */ import java.net.http.HttpClient;
/*     */ import java.net.http.HttpRequest;
/*     */ import java.net.http.HttpResponse;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.CompletableFuture;
/*     */ import net.minecraft.class_1011;
/*     */ import net.minecraft.class_1043;
/*     */ import net.minecraft.class_1044;
/*     */ import net.minecraft.class_2561;
/*     */ import net.minecraft.class_2583;
/*     */ import net.minecraft.class_2960;
/*     */ import net.minecraft.class_310;
/*     */ import net.minecraft.class_327;
/*     */ import net.minecraft.class_332;
/*     */ import net.minecraft.class_5250;
/*     */ import net.minecraft.class_5348;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class LookupOverviewView
/*     */   implements LookupView
/*     */ {
/*  38 */   private static final Logger LOGGER = LoggerFactory.getLogger("LookupOverviewView");
/*     */   
/*     */   private static final int PUNISHMENT_BAR_H = 18;
/*     */   
/*     */   private static final int PUNISHMENT_BAR_MARGIN = 3;
/*     */   
/*     */   private static final int CARD_PADDING = 6;
/*     */   
/*     */   private static final int ROW_H = 14;
/*     */   
/*     */   private static final int ROW_GAP = 3;
/*     */   
/*     */   private static final int SECTION_HEADER_H = 16;
/*     */   
/*     */   private static final int SECTION_GAP = 6;
/*     */   
/*     */   private static final int COLOR_POKECOIN = -281343;
/*     */   private static final int COLOR_POKEGEM = -14746869;
/*     */   private static final int COLOR_KARMA = -65281;
/*     */   private int x;
/*     */   private int y;
/*     */   private int width;
/*     */   private int height;
/*     */   private JsonObject overview;
/*     */   private String lastOverviewJson;
/*     */   private class_2960 headTextureId;
/*     */   private boolean headLoading;
/*     */   private boolean headFailed;
/*     */   private String lastHeadUuid;
/*     */   private boolean locationHovered = false;
/*  68 */   private int locationRenderY = -1;
/*     */   
/*     */   private GuideScrollableArea homesScroll;
/*     */   
/*  72 */   private int hoveredHomeIndex = -1;
/*  73 */   private int claimsButtonX = -1;
/*  74 */   private int claimsButtonY = -1;
/*  75 */   private int claimsButtonW = 0;
/*  76 */   private int claimsButtonH = 18;
/*     */ 
/*     */   
/*  79 */   private final List<HomeEntry> homes = new ArrayList<>();
/*     */   private static final class HomeEntry extends Record { private final String name; private final String server; private final int hx; private final int hy; private final int hz;
/*  81 */     private HomeEntry(String name, String server, int hx, int hy, int hz) { this.name = name; this.server = server; this.hx = hx; this.hy = hy; this.hz = hz; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/lookup/screen/views/LookupOverviewView$HomeEntry;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #81	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*  81 */       //   0	7	0	this	Lcom/atlas/common/fabric/lookup/screen/views/LookupOverviewView$HomeEntry; } public String name() { return this.name; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/lookup/screen/views/LookupOverviewView$HomeEntry;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #81	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/lookup/screen/views/LookupOverviewView$HomeEntry; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/lookup/screen/views/LookupOverviewView$HomeEntry;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #81	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lcom/atlas/common/fabric/lookup/screen/views/LookupOverviewView$HomeEntry;
/*  81 */       //   0	8	1	o	Ljava/lang/Object; } public String server() { return this.server; } public int hx() { return this.hx; } public int hy() { return this.hy; } public int hz() { return this.hz; }
/*     */      }
/*     */ 
/*     */ 
/*     */   
/*     */   public void init(int x, int y, int width, int height) {
/*  87 */     this.x = x;
/*  88 */     this.y = y;
/*  89 */     this.width = width;
/*  90 */     this.height = height;
/*     */ 
/*     */     
/*  93 */     if (this.homesScroll == null) {
/*  94 */       this.homesScroll = new GuideScrollableArea(x, y, 10, 10);
/*  95 */       this.homesScroll.setThumbColors(-12958368, -10777105);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(class_332 ctx, int mouseX, int mouseY, float delta, class_327 textRenderer) {
/* 103 */     String json = LookupClientData.getInstance().getOverviewJson();
/* 104 */     if (json == null || json.isEmpty()) {
/* 105 */       ctx.method_25300(textRenderer, "No overview data available", this.x + this.width / 2, this.y + this.height / 2 - 4, -9930592);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 110 */     if (this.overview == null || !json.equals(this.lastOverviewJson)) {
/*     */       try {
/* 112 */         this.overview = JsonParser.parseString(json).getAsJsonObject();
/* 113 */         this.lastOverviewJson = json;
/* 114 */         parseHomes();
/* 115 */         rebuildHomesScroll();
/* 116 */       } catch (Exception e) {
/* 117 */         ctx.method_25300(textRenderer, "Failed to parse overview", this.x + this.width / 2, this.y + this.height / 2, -1030329);
/*     */ 
/*     */         
/*     */         return;
/*     */       } 
/*     */     }
/*     */     
/* 124 */     String uuid = getString(this.overview, "uuid");
/* 125 */     if (uuid != null && !uuid.equals(this.lastHeadUuid)) {
/* 126 */       this.lastHeadUuid = uuid;
/* 127 */       this.headTextureId = null;
/* 128 */       this.headFailed = false;
/* 129 */       this.headLoading = true;
/* 130 */       loadPlayerHead(uuid);
/*     */     } 
/*     */ 
/*     */     
/* 134 */     int leftW = (int)(this.width * 0.38D);
/* 135 */     int rightW = this.width - leftW - 4;
/* 136 */     int leftX = this.x + 6;
/* 137 */     int rightX = this.x + leftW + 4;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 142 */     int contentTop = this.y + 8;
/* 143 */     int punishBarY = this.y + this.height - 18 - 3;
/* 144 */     int cardBottomY = punishBarY - 4 - 6;
/* 145 */     int contentH = cardBottomY - contentTop;
/*     */ 
/*     */     
/* 148 */     this.locationHovered = false;
/* 149 */     this.hoveredHomeIndex = -1;
/*     */     
/* 151 */     renderLeftSection(ctx, textRenderer, mouseX, mouseY, leftX, contentTop, leftW - 10, contentH);
/* 152 */     renderRightSection(ctx, textRenderer, mouseX, mouseY, rightX, contentTop, rightW - 8, contentH);
/* 153 */     renderPunishmentBar(ctx, textRenderer, mouseX, mouseY);
/*     */   }
/*     */   
/*     */   private void parseHomes() {
/* 157 */     this.homes.clear();
/* 158 */     if (this.overview == null || !this.overview.has("homes") || !this.overview.get("homes").isJsonArray())
/* 159 */       return;  JsonArray arr = this.overview.getAsJsonArray("homes");
/* 160 */     for (JsonElement elem : arr) {
/* 161 */       if (!elem.isJsonObject())
/* 162 */         continue;  JsonObject h = elem.getAsJsonObject();
/* 163 */       String name = getString(h, "name");
/* 164 */       String server = getString(h, "server");
/* 165 */       int hx = getInt(h, "x");
/* 166 */       int hy = getInt(h, "y");
/* 167 */       int hz = getInt(h, "z");
/* 168 */       if (name != null) this.homes.add(new HomeEntry(name, (server != null) ? server : "?", hx, hy, hz));
/*     */     
/*     */     } 
/*     */   }
/*     */   
/*     */   private void rebuildHomesScroll() {
/* 174 */     int perRow = 19;
/* 175 */     int total = this.homes.size() * perRow + 6;
/* 176 */     this.homesScroll.setContentHeight(total);
/* 177 */     this.homesScroll.resetScroll();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderLeftSection(class_332 ctx, class_327 textRenderer, int mouseX, int mouseY, int lx, int ly, int lw, int cardH) {
/* 185 */     ctx.method_25294(lx - 6, ly - 6, lx + lw + 6, ly + cardH + 6, -14801866);
/*     */ 
/*     */     
/* 188 */     ctx.method_49601(lx - 6, ly - 6, lw + 12, cardH + 12, -14012352);
/*     */ 
/*     */ 
/*     */     
/* 192 */     int cy = ly;
/*     */ 
/*     */     
/* 195 */     int headSize = 48;
/* 196 */     int headX = lx + (lw - headSize) / 2;
/* 197 */     if (this.headTextureId != null && !this.headFailed) {
/* 198 */       RenderSystem.setShaderTexture(0, this.headTextureId);
/* 199 */       ctx.method_25290(this.headTextureId, headX, cy, 0.0F, 0.0F, headSize, headSize, headSize, headSize);
/*     */     } else {
/* 201 */       ctx.method_25294(headX, cy, headX + headSize, cy + headSize, -15328732);
/* 202 */       String mark = this.headLoading ? "..." : "?";
/* 203 */       ctx.method_25300(textRenderer, mark, headX + headSize / 2, cy + headSize / 2 - 4, -9930592);
/*     */     } 
/*     */     
/* 206 */     cy += headSize + 6;
/*     */ 
/*     */     
/* 209 */     String playerName = getString(this.overview, "name");
/* 210 */     if (playerName == null) playerName = "Unknown"; 
/* 211 */     ctx.method_25300(textRenderer, "§l" + playerName, lx + lw / 2, cy, -1);
/*     */     
/* 213 */     cy += 13;
/*     */ 
/*     */     
/* 216 */     String rankId = getString(this.overview, "rank");
/* 217 */     if (rankId != null && !rankId.isEmpty()) {
/*     */       
/* 219 */       class_5250 class_5250 = class_2561.method_43471("rank.player." + rankId).method_27694(s -> s.method_27704(class_2960.method_60654("rank")));
/* 220 */       int rankW = textRenderer.method_27525((class_5348)class_5250);
/* 221 */       ctx.method_51439(textRenderer, (class_2561)class_5250, lx + (lw - rankW) / 2, cy, -1, false);
/* 222 */       cy += 13;
/*     */     } 
/*     */ 
/*     */     
/* 226 */     cy += 2;
/* 227 */     ctx.method_25294(lx, cy, lx + lw, cy + 1, -14012352);
/* 228 */     cy += 5;
/*     */ 
/*     */     
/* 231 */     this.locationRenderY = -1;
/* 232 */     if (hasLocation()) {
/* 233 */       drawSectionHeader(ctx, textRenderer, "Location", lx, cy, lw);
/* 234 */       cy += 16;
/*     */       
/* 236 */       int locX = getInt(this.overview, "x");
/* 237 */       int locY = getInt(this.overview, "y");
/* 238 */       int locZ = getInt(this.overview, "z");
/* 239 */       String srv = getString(this.overview, "server");
/* 240 */       if (srv == null) srv = "?";
/*     */       
/* 242 */       this.locationRenderY = cy;
/* 243 */       boolean hov = (mouseX >= lx && mouseX < lx + lw && mouseY >= cy && mouseY < cy + 14);
/* 244 */       this.locationHovered = hov;
/*     */       
/* 246 */       int rowBg = hov ? -14274480 : LookupColors.withAlpha(-10777105, 18);
/* 247 */       ctx.method_25294(lx - 2, cy - 1, lx + lw + 2, cy + 14 + 1, rowBg);
/*     */       
/* 249 */       String arrow = "> ";
/* 250 */       int arrowW = textRenderer.method_1727(arrow);
/* 251 */       ctx.method_51433(textRenderer, arrow, lx + 2, cy + 2, -9930592, false);
/* 252 */       ctx.method_51433(textRenderer, srv, lx + 2 + arrowW, cy + 2, 
/* 253 */           hov ? -1 : -10496, false);
/* 254 */       int srvW = textRenderer.method_1727(srv);
/* 255 */       String coords = " (" + locX + ", " + locY + ", " + locZ + ")";
/* 256 */       ctx.method_51433(textRenderer, coords, lx + 2 + arrowW + srvW, cy + 2, -9930592, false);
/* 257 */       cy += 21;
/*     */     } 
/*     */ 
/*     */     
/* 261 */     if (!this.homes.isEmpty()) {
/* 262 */       drawSectionHeader(ctx, textRenderer, "Homes (" + this.homes.size() + ")", lx, cy, lw);
/* 263 */       cy += 16;
/*     */ 
/*     */       
/* 266 */       int scrollTop = cy;
/* 267 */       int scrollBottom = ly + cardH;
/* 268 */       int scrollAreaH = Math.max(10, scrollBottom - scrollTop);
/*     */       
/* 270 */       this.homesScroll.updateBounds(lx, scrollTop, lw, scrollAreaH);
/*     */       
/* 272 */       int scrollOff = this.homesScroll.getScrollOffset();
/* 273 */       int perRow = 19;
/* 274 */       int totalContent = this.homes.size() * perRow + 6;
/* 275 */       this.homesScroll.setContentHeight(totalContent);
/*     */       
/* 277 */       this.homesScroll.beginRender(ctx);
/*     */       
/* 279 */       int rowY = scrollTop - scrollOff;
/* 280 */       for (int i = 0; i < this.homes.size(); i++) {
/* 281 */         HomeEntry home = this.homes.get(i);
/*     */         
/* 283 */         boolean visible = (rowY + 14 >= scrollTop && rowY <= scrollTop + scrollAreaH);
/* 284 */         if (visible) {
/* 285 */           boolean hov = (mouseX >= lx && mouseX < lx + lw && mouseY >= rowY && mouseY < rowY + 14 && mouseY >= scrollTop && mouseY < scrollTop + scrollAreaH);
/*     */ 
/*     */           
/* 288 */           if (hov) this.hoveredHomeIndex = i;
/*     */           
/* 290 */           int rowBg = hov ? -14274480 : LookupColors.withAlpha(-10777105, 10);
/* 291 */           ctx.method_25294(lx - 2, rowY - 1, lx + lw + 2, rowY + 14 + 1, rowBg);
/*     */           
/* 293 */           String dash = "- ";
/* 294 */           int dashW = textRenderer.method_1727(dash);
/* 295 */           ctx.method_51433(textRenderer, dash, lx + 2, rowY + 2, -9930592, false);
/* 296 */           ctx.method_51433(textRenderer, home.name(), lx + 2 + dashW, rowY + 2, 
/* 297 */               hov ? -1 : -1511169, false);
/* 298 */           int nameW = textRenderer.method_1727(home.name());
/* 299 */           String suffix = " [" + home.server() + "]";
/* 300 */           ctx.method_51433(textRenderer, suffix, lx + 2 + dashW + nameW, rowY + 2, -9930592, false);
/*     */ 
/*     */ 
/*     */           
/* 304 */           ctx.method_25294(lx, rowY + 14 + 1, lx + lw, rowY + 14 + 2, 
/* 305 */               LookupColors.withAlpha(-14012352, 60));
/*     */         } 
/* 307 */         rowY += perRow;
/*     */       } 
/*     */       
/* 310 */       this.homesScroll.endRender(ctx);
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean hasLocation() {
/* 315 */     if (this.overview == null) return false; 
/* 316 */     return (this.overview.has("x") && this.overview.has("y") && this.overview.has("z") && 
/* 317 */       !this.overview.get("x").isJsonNull());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderRightSection(class_332 ctx, class_327 textRenderer, int mouseX, int mouseY, int rx, int ry, int rw, int cardH) {
/* 325 */     ctx.method_25294(rx - 6, ry - 6, rx + rw + 6, ry + cardH + 6, -14801866);
/*     */ 
/*     */     
/* 328 */     ctx.method_49601(rx - 6, ry - 6, rw + 12, cardH + 12, -14012352);
/*     */ 
/*     */ 
/*     */     
/* 332 */     int cy = ry;
/*     */ 
/*     */     
/* 335 */     drawSectionHeader(ctx, textRenderer, "Player Info", rx, cy, rw);
/* 336 */     cy += 16;
/*     */     
/* 338 */     cy = drawStatRow(ctx, textRenderer, "Join Date", formatDate(getString(this.overview, "joinDate")), rx, cy, rw);
/* 339 */     cy = drawStatRow(ctx, textRenderer, "Last Seen", formatDate(getString(this.overview, "lastSeen")), rx, cy, rw);
/* 340 */     cy = drawStatRow(ctx, textRenderer, "Playtime", formatPlaytime(getLong(this.overview, "playtime")), rx, cy, rw);
/* 341 */     cy = drawStatRow(ctx, textRenderer, "Clan", getString(this.overview, "clan"), rx, cy, rw);
/* 342 */     cy = drawStatRow(ctx, textRenderer, "Elite 4", formatNumber(getLong(this.overview, "elite4")), rx, cy, rw, -11870592);
/*     */     
/* 344 */     cy += 6;
/*     */ 
/*     */     
/* 347 */     drawSectionHeader(ctx, textRenderer, "Currencies", rx, cy, rw);
/* 348 */     cy += 16;
/*     */     
/* 350 */     cy = drawCurrencyRow(ctx, textRenderer, "PokeCoin(s)", 
/* 351 */         (class_2561)class_2561.method_43471("miscellaneous.coin").method_27694(s -> s.method_27704(class_2960.method_60654("miscellaneous"))), 
/* 352 */         formatNumber(getLong(this.overview, "pokeCoin")), rx, cy, rw, -281343);
/* 353 */     cy = drawCurrencyRow(ctx, textRenderer, "PokeGem(s)", 
/* 354 */         (class_2561)class_2561.method_43471("miscellaneous.gem").method_27694(s -> s.method_27704(class_2960.method_60654("miscellaneous"))), 
/* 355 */         formatNumber(getLong(this.overview, "pokeGem")), rx, cy, rw, -14746869);
/* 356 */     cy = drawCurrencyRow(ctx, textRenderer, "Karma Point(s)", 
/* 357 */         (class_2561)class_2561.method_43471("miscellaneous.karma").method_27694(s -> s.method_27704(class_2960.method_60654("miscellaneous"))), 
/* 358 */         formatNumber(getLong(this.overview, "karmaPoint")), rx, cy, rw, -65281);
/*     */     
/* 360 */     cy += 6;
/*     */ 
/*     */     
/* 363 */     drawSectionHeader(ctx, textRenderer, "Pokedex", rx, cy, rw);
/* 364 */     cy += 16;
/*     */     
/* 366 */     long dexCaught = getLong(this.overview, "pokedexCaught");
/* 367 */     long dexSeen = getLong(this.overview, "pokedexSeen");
/* 368 */     cy = drawStatRow(ctx, textRenderer, "Caught", formatNumber(dexCaught), rx, cy, rw, -11870592);
/* 369 */     cy = drawStatRow(ctx, textRenderer, "Seen", formatNumber(dexSeen), rx, cy, rw);
/*     */ 
/*     */     
/* 372 */     int totalSpecies = 1025;
/* 373 */     int barW = rw - 4;
/* 374 */     int barH = 6;
/* 375 */     int barX = rx + 2;
/* 376 */     int barY = cy + 2;
/* 377 */     int filledW = (int)(barW * Math.min(1.0D, dexCaught / 1025.0D));
/* 378 */     ctx.method_25294(barX, barY, barX + barW, barY + barH, -15328732);
/* 379 */     ctx.method_25294(barX, barY, barX + filledW, barY + barH, -12339839);
/* 380 */     ctx.method_49601(barX, barY, barW, barH, LookupColors.withAlpha(-14012352, 80));
/*     */     
/* 382 */     String pct = String.format("%.1f%%", new Object[] { Double.valueOf(dexCaught * 100.0D / 1025.0D) });
/* 383 */     int pctW = textRenderer.method_1727(pct);
/* 384 */     cy += barH + 4;
/* 385 */     ctx.method_51433(textRenderer, pct, rx + rw - pctW, cy, -9930592, false);
/* 386 */     cy += 14;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawSectionHeader(class_332 ctx, class_327 textRenderer, String title, int sx, int sy, int sw) {
/* 397 */     ctx.method_25294(sx - 2, sy, sx + sw + 2, sy + 16 - 2, 
/* 398 */         LookupColors.withAlpha(-10777105, 14));
/*     */ 
/*     */     
/* 401 */     ctx.method_51433(textRenderer, "§l" + title, sx + 3, sy + 3, -10777105, false);
/*     */ 
/*     */     
/* 404 */     ctx.method_25294(sx, sy + 16 - 2, sx + sw, sy + 16 - 1, 
/* 405 */         LookupColors.withAlpha(-10777105, 80));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int drawStatRow(class_332 ctx, class_327 textRenderer, String label, String value, int rx, int ry, int rw) {
/* 412 */     return drawStatRow(ctx, textRenderer, label, value, rx, ry, rw, -1511169);
/*     */   }
/*     */ 
/*     */   
/*     */   private int drawStatRow(class_332 ctx, class_327 textRenderer, String label, String value, int rx, int ry, int rw, int valueColor) {
/* 417 */     if (value == null) value = "N/A"; 
/* 418 */     ctx.method_51433(textRenderer, label, rx + 3, ry + 2, -9930592, false);
/* 419 */     int vw = textRenderer.method_1727(value);
/* 420 */     ctx.method_51433(textRenderer, value, rx + rw - vw, ry + 2, valueColor, false);
/* 421 */     ry += 14;
/* 422 */     ctx.method_25294(rx, ry, rx + rw, ry + 1, LookupColors.withAlpha(-14012352, 60));
/* 423 */     ry += 3;
/* 424 */     return ry;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int drawCurrencyRow(class_332 ctx, class_327 textRenderer, String label, class_2561 icon, String amount, int rx, int ry, int rw, int valueColor) {
/* 434 */     int iconW = textRenderer.method_27525((class_5348)icon);
/* 435 */     ctx.method_51439(textRenderer, icon, rx + 3, ry + 2, -1, false);
/*     */ 
/*     */     
/* 438 */     int labelX = rx + 3 + iconW + 4;
/* 439 */     ctx.method_51433(textRenderer, label, labelX, ry + 2, -9930592, false);
/*     */ 
/*     */     
/* 442 */     int amountW = textRenderer.method_1727(amount);
/* 443 */     ctx.method_51433(textRenderer, amount, rx + rw - amountW, ry + 2, valueColor, false);
/*     */     
/* 445 */     ry += 14;
/* 446 */     ctx.method_25294(rx, ry, rx + rw, ry + 1, LookupColors.withAlpha(-14012352, 60));
/* 447 */     ry += 3;
/* 448 */     return ry;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderPunishmentBar(class_332 ctx, class_327 textRenderer, int mouseX, int mouseY) {
/* 454 */     int barY = this.y + this.height - 18 - 3;
/* 455 */     int barX = this.x + 4;
/* 456 */     int barW = this.width - 8;
/* 457 */     int gap = 4;
/* 458 */     int punishW = Math.min(220, Math.max(170, barW / 2 - 20));
/* 459 */     int claimsCardW = barW - punishW - gap;
/* 460 */     int claimsCardX = barX + punishW + gap;
/*     */     
/* 462 */     ctx.method_25294(barX, barY, barX + punishW, barY + 18, -14801866);
/* 463 */     ctx.method_49601(barX, barY, punishW, 18, -14012352);
/*     */     
/* 465 */     int total = getInt(this.overview, "punishmentTotal");
/* 466 */     int active = getInt(this.overview, "punishmentActive");
/* 467 */     long claimCount = getLong(this.overview, "claimCount");
/*     */     
/* 469 */     int statusColor = (active > 0) ? -1030329 : -12339839;
/* 470 */     String summary = "" + total + " total, " + total + " active";
/*     */     
/* 472 */     this.claimsButtonX = claimsCardX;
/* 473 */     this.claimsButtonY = barY;
/* 474 */     this.claimsButtonW = claimsCardW;
/* 475 */     this.claimsButtonH = 18;
/* 476 */     boolean claimsHovered = (mouseX >= this.claimsButtonX && mouseX < this.claimsButtonX + this.claimsButtonW && mouseY >= this.claimsButtonY && mouseY < this.claimsButtonY + this.claimsButtonH);
/*     */     
/* 478 */     int claimsBg = claimsHovered ? -14274480 : -14801866;
/* 479 */     ctx.method_25294(claimsCardX, barY, claimsCardX + claimsCardW, barY + 18, claimsBg);
/* 480 */     ctx.method_49601(claimsCardX, barY, claimsCardW, 18, 
/* 481 */         claimsHovered ? -10777105 : -14012352);
/*     */     
/* 483 */     String punLabel = "Punishments:";
/* 484 */     ctx.method_51433(textRenderer, punLabel, barX + 6, barY + 6, -9930592, false);
/* 485 */     int labelW = textRenderer.method_1727(punLabel);
/* 486 */     ctx.method_51433(textRenderer, summary, barX + 6 + labelW + 4, barY + 6, statusColor, false);
/*     */     
/* 488 */     String claimsLabel = "Claims: " + formatNumber(claimCount);
/* 489 */     String actionLabel = "Open";
/* 490 */     int actionW = textRenderer.method_1727(actionLabel);
/* 491 */     int claimsTextMaxW = Math.max(20, claimsCardW - actionW - 22);
/* 492 */     ctx.method_51433(textRenderer, truncateText(textRenderer, claimsLabel, claimsTextMaxW), claimsCardX + 6, barY + 6, -9930592, false);
/*     */     
/* 494 */     ctx.method_51433(textRenderer, actionLabel, claimsCardX + claimsCardW - actionW - 8, barY + 6, 
/*     */         
/* 496 */         claimsHovered ? -1 : -10777105, false);
/*     */ 
/*     */     
/* 499 */     int dotRight = barX + punishW - 6;
/* 500 */     ctx.method_25294(dotRight - 8, barY + 5, dotRight, barY + 13, statusColor);
/*     */   }
/*     */   
/*     */   private static String truncateText(class_327 textRenderer, String text, int maxWidth) {
/* 504 */     if (maxWidth <= 0 || text == null) return ""; 
/* 505 */     if (textRenderer.method_1727(text) <= maxWidth) return text; 
/* 506 */     String ellipsis = "...";
/* 507 */     int len = text.length();
/* 508 */     while (len > 0 && textRenderer.method_1727(text.substring(0, len) + text.substring(0, len)) > maxWidth) {
/* 509 */       len--;
/*     */     }
/* 511 */     return text.substring(0, Math.max(0, len)) + text.substring(0, Math.max(0, len));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean mouseClicked(double mouseX, double mouseY, int button) {
/* 518 */     if (button != 0 || this.overview == null) return false;
/*     */ 
/*     */     
/* 521 */     if (this.homesScroll != null && this.homesScroll.handleMouseClicked(mouseX, mouseY, button)) return true;
/*     */     
/* 523 */     int leftW = (int)(this.width * 0.38D);
/* 524 */     int lx = this.x + 4;
/* 525 */     int lw = leftW - 8;
/*     */ 
/*     */     
/* 528 */     if (this.locationRenderY >= 0 && hasLocation() && 
/* 529 */       mouseX >= lx && mouseX < (lx + lw) && mouseY >= this.locationRenderY && mouseY < (this.locationRenderY + 14)) {
/*     */       
/* 531 */       int locX = getInt(this.overview, "x");
/* 532 */       int locY = getInt(this.overview, "y");
/* 533 */       int locZ = getInt(this.overview, "z");
/* 534 */       String srv = getString(this.overview, "server");
/* 535 */       if (srv == null) srv = "?"; 
/* 536 */       LookupNetwork.requestLookupData("teleport", 0, "" + locX + ":" + locX + ":" + locY + ":" + locZ);
/* 537 */       return true;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 542 */     if (this.hoveredHomeIndex >= 0 && this.hoveredHomeIndex < this.homes.size()) {
/* 543 */       HomeEntry home = this.homes.get(this.hoveredHomeIndex);
/* 544 */       LookupNetwork.requestLookupData("teleport", 0, "" + home
/* 545 */           .hx() + ":" + home.hx() + ":" + home.hy() + ":" + home.hz());
/* 546 */       return true;
/*     */     } 
/*     */     
/* 549 */     if (this.claimsButtonX >= 0 && mouseX >= this.claimsButtonX && mouseX < (this.claimsButtonX + this.claimsButtonW) && mouseY >= this.claimsButtonY && mouseY < (this.claimsButtonY + this.claimsButtonH)) {
/*     */ 
/*     */       
/* 552 */       String targetUuid = LookupClientData.getInstance().getTargetUuid();
/* 553 */       if (targetUuid != null && !targetUuid.isEmpty()) {
/* 554 */         LookupNetwork.requestLookupData("open_claims", 0, targetUuid);
/* 555 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/* 559 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
/* 564 */     if (this.homesScroll != null) return this.homesScroll.handleScroll(mouseX, mouseY, verticalAmount); 
/* 565 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
/* 570 */     if (this.homesScroll != null) return this.homesScroll.handleMouseDragged(mouseX, mouseY, button); 
/* 571 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean mouseReleased(double mouseX, double mouseY, int button) {
/* 576 */     if (this.homesScroll != null) return this.homesScroll.handleMouseReleased(mouseX, mouseY, button); 
/* 577 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void loadPlayerHead(String uuid) {
/* 583 */     String url = "https://mc-heads.net/avatar/" + uuid + "/64";
/* 584 */     LOGGER.info("Loading player head for UUID: {} from {}", uuid, url);
/* 585 */     CompletableFuture.runAsync(() -> {
/*     */           try {
/*     */             HttpClient client = HttpClient.newHttpClient();
/*     */ 
/*     */ 
/*     */             
/*     */             HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).header("User-Agent", "AtlasLookup/1.0").GET().build();
/*     */ 
/*     */ 
/*     */             
/*     */             HttpResponse<byte[]> response = (HttpResponse)client.send(request, (HttpResponse.BodyHandler)HttpResponse.BodyHandlers.ofByteArray());
/*     */ 
/*     */ 
/*     */             
/*     */             if (response.statusCode() == 200) {
/*     */               byte[] bytes = response.body();
/*     */ 
/*     */ 
/*     */               
/*     */               LOGGER.info("Player head downloaded successfully, {} bytes", Integer.valueOf(bytes.length));
/*     */ 
/*     */ 
/*     */               
/*     */               class_310.method_1551().execute(());
/*     */             } else {
/*     */               LOGGER.warn("Player head download failed with status {} for UUID: {}", Integer.valueOf(response.statusCode()), uuid);
/*     */ 
/*     */               
/*     */               this.headFailed = true;
/*     */ 
/*     */               
/*     */               this.headLoading = false;
/*     */             } 
/* 618 */           } catch (Exception e) {
/*     */             LOGGER.error("Player head download error for UUID: {}", uuid, e);
/*     */             this.headFailed = true;
/*     */             this.headLoading = false;
/*     */           } 
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static String getString(JsonObject obj, String key) {
/* 629 */     if (obj == null || !obj.has(key) || obj.get(key).isJsonNull()) return null; 
/* 630 */     return obj.get(key).getAsString();
/*     */   }
/*     */   
/*     */   private static int getInt(JsonObject obj, String key) {
/* 634 */     if (obj == null || !obj.has(key) || obj.get(key).isJsonNull()) return 0;  
/* 635 */     try { return obj.get(key).getAsInt(); } catch (Exception e) { return 0; }
/*     */   
/*     */   }
/*     */   private static long getLong(JsonObject obj, String key) {
/* 639 */     if (obj == null || !obj.has(key) || obj.get(key).isJsonNull()) return 0L;  
/* 640 */     try { return obj.get(key).getAsLong(); } catch (Exception e) { return 0L; }
/*     */   
/*     */   }
/*     */   private static boolean hasField(JsonObject obj, String key) {
/* 644 */     return (obj != null && obj.has(key) && !obj.get(key).isJsonNull());
/*     */   }
/*     */   
/*     */   private static String formatDate(String raw) {
/* 648 */     if (raw == null || raw.isEmpty()) return "N/A"; 
/* 649 */     return (raw.length() > 16) ? raw.substring(0, 16) : raw;
/*     */   }
/*     */   
/*     */   private static String formatPlaytime(long seconds) {
/* 653 */     if (seconds <= 0L) return "0m"; 
/* 654 */     long days = seconds / 86400L;
/* 655 */     long hours = seconds % 86400L / 3600L;
/* 656 */     long minutes = seconds % 3600L / 60L;
/* 657 */     StringBuilder sb = new StringBuilder();
/* 658 */     if (days > 0L) sb.append(days).append("d "); 
/* 659 */     if (hours > 0L) sb.append(hours).append("h "); 
/* 660 */     if (minutes > 0L || sb.isEmpty()) sb.append(minutes).append("m"); 
/* 661 */     return sb.toString().trim();
/*     */   }
/*     */   
/*     */   private static String formatNumber(long value) {
/* 665 */     return String.format("%,d", new Object[] { Long.valueOf(value) });
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\lookup\screen\views\LookupOverviewView.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */