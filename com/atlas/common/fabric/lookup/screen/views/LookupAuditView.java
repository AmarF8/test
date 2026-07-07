/*     */ package com.atlas.common.fabric.lookup.screen.views;
/*     */ import com.atlas.common.fabric.guide.screen.widgets.GuideScrollableArea;
/*     */ import com.atlas.common.fabric.lookup.data.LookupClientData;
/*     */ import com.atlas.common.fabric.lookup.network.LookupNetwork;
/*     */ import com.atlas.common.fabric.lookup.screen.LookupColors;
/*     */ import com.atlas.common.fabric.lookup.screen.widgets.StatIcon;
/*     */ import com.atlas.common.fabric.screen.PokemonRenderHelper;
/*     */ import com.cobblemon.mod.common.client.render.models.blockbench.FloatingState;
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParser;
/*     */ import com.mojang.datafixers.util.Pair;
/*     */ import com.mojang.serialization.DynamicOps;
/*     */ import com.mojang.serialization.JsonOps;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.Set;
/*     */ import net.minecraft.class_1799;
/*     */ import net.minecraft.class_2960;
/*     */ import net.minecraft.class_310;
/*     */ import net.minecraft.class_327;
/*     */ import net.minecraft.class_332;
/*     */ import net.minecraft.class_4587;
/*     */ import net.minecraft.class_6903;
/*     */ import net.minecraft.class_7225;
/*     */ import org.joml.Quaternionf;
/*     */ 
/*     */ public class LookupAuditView implements LookupView {
/*     */   private int x;
/*     */   private int y;
/*     */   private int width;
/*     */   private int height;
/*     */   private boolean dataRequested = false;
/*  39 */   private int currentPage = 0;
/*     */   
/*     */   private GuideScrollableArea scrollArea;
/*  42 */   private final Runnable dataListener = this::onDataUpdated;
/*     */   
/*  44 */   private final List<AuditEntry> entries = new ArrayList<>();
/*  45 */   private int totalEntries = 0;
/*     */   
/*  47 */   private int hoveredIndex = -1;
/*     */   
/*     */   private String lastAuditJson;
/*     */   
/*  51 */   private final HashMap<String, String> detailsTruncationCache = new HashMap<>();
/*     */   private static final int ROW_HEIGHT = 24;
/*     */   private static final int PADDING = 6;
/*     */   private static final int HEADER_HEIGHT = 22;
/*     */   private static final int FOOTER_HEIGHT = 22;
/*     */   private static final int BADGE_BLUE = -15056768;
/*     */   private static final int BADGE_ORANGE = -8372208;
/*     */   private static final int BADGE_RED = -8384496;
/*     */   private static final int BADGE_GREEN = -15697856;
/*     */   private static final int BADGE_GRAY = -13619136;
/*     */   private static final int BADGE_TEXT_BLUE = -9785857;
/*     */   private static final int BADGE_TEXT_ORANGE = -21952;
/*     */   private static final int BADGE_TEXT_RED = -40864;
/*     */   private static final int BADGE_TEXT_GREEN = -11477616;
/*     */   private static final int BADGE_TEXT_GRAY = -7824965;
/*     */   
/*     */   private static final class AuditEntry extends Record { private final String timestamp;
/*     */     private final String action;
/*     */     private final String actor;
/*     */     private final String details;
/*     */     private final List<class_1799> itemStacks;
/*     */     private final JsonObject pokemonObj;
/*     */     
/*     */     public final String toString() {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/lookup/screen/views/LookupAuditView$AuditEntry;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #82	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/lookup/screen/views/LookupAuditView$AuditEntry;
/*     */     }
/*     */     
/*     */     public final int hashCode() {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/lookup/screen/views/LookupAuditView$AuditEntry;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #82	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/lookup/screen/views/LookupAuditView$AuditEntry;
/*     */     }
/*     */     
/*  82 */     private AuditEntry(String timestamp, String action, String actor, String details, List<class_1799> itemStacks, JsonObject pokemonObj) { this.timestamp = timestamp; this.action = action; this.actor = actor; this.details = details; this.itemStacks = itemStacks; this.pokemonObj = pokemonObj; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/lookup/screen/views/LookupAuditView$AuditEntry;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #82	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lcom/atlas/common/fabric/lookup/screen/views/LookupAuditView$AuditEntry;
/*  82 */       //   0	8	1	o	Ljava/lang/Object; } public String timestamp() { return this.timestamp; } public String action() { return this.action; } public String actor() { return this.actor; } public String details() { return this.details; } public List<class_1799> itemStacks() { return this.itemStacks; } public JsonObject pokemonObj() { return this.pokemonObj; }
/*     */      }
/*     */   private static final class IconHit extends Record { private final int x; private final int y; private final int w; private final int h; private final class_1799 stack;
/*     */     private final JsonObject pokemon;
/*     */     
/*     */     public final String toString() {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/lookup/screen/views/LookupAuditView$IconHit;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #95	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/lookup/screen/views/LookupAuditView$IconHit;
/*     */     }
/*     */     
/*     */     public final int hashCode() {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/lookup/screen/views/LookupAuditView$IconHit;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #95	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/lookup/screen/views/LookupAuditView$IconHit;
/*     */     }
/*     */     
/*  95 */     private IconHit(int x, int y, int w, int h, class_1799 stack, JsonObject pokemon) { this.x = x; this.y = y; this.w = w; this.h = h; this.stack = stack; this.pokemon = pokemon; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/lookup/screen/views/LookupAuditView$IconHit;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #95	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lcom/atlas/common/fabric/lookup/screen/views/LookupAuditView$IconHit;
/*  95 */       //   0	8	1	o	Ljava/lang/Object; } public int x() { return this.x; } public int y() { return this.y; } public int w() { return this.w; } public int h() { return this.h; } public class_1799 stack() { return this.stack; } public JsonObject pokemon() { return this.pokemon; }
/*  96 */      } private final List<IconHit> iconHits = new ArrayList<>();
/*     */ 
/*     */   
/*  99 */   private final Map<String, FloatingState> pokemonStates = new HashMap<>();
/*     */ 
/*     */ 
/*     */   
/* 103 */   private static final Set<String> MEDIA_KEYS = Set.of("pokemon", "item", "stack", "from", "to", "fromitem", "toitem", "fromstack", "tostack");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void init(int x, int y, int width, int height) {
/* 110 */     this.x = x;
/* 111 */     this.y = y;
/* 112 */     this.width = width;
/* 113 */     this.height = height;
/*     */     
/* 115 */     int contentTop = y + 22;
/* 116 */     int contentHeight = height - 22 - 22;
/* 117 */     if (this.scrollArea == null) {
/* 118 */       this.scrollArea = new GuideScrollableArea(x, contentTop, width, contentHeight);
/* 119 */       this.scrollArea.setThumbColors(-12958368, -10777105);
/*     */     } else {
/* 121 */       this.scrollArea.updateBounds(x, contentTop, width, contentHeight);
/*     */     } 
/*     */     
/* 124 */     LookupClientData data = LookupClientData.getInstance();
/* 125 */     data.removeListener(this.dataListener);
/* 126 */     data.addListener(this.dataListener);
/*     */     
/* 128 */     if (!this.dataRequested) {
/* 129 */       this.dataRequested = true;
/* 130 */       String uuid = data.getTargetUuid();
/* 131 */       LookupNetwork.requestLookupData("audit", this.currentPage, (uuid != null) ? uuid : "");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void onDataUpdated() {
/* 143 */     String currentJson = LookupClientData.getInstance().getAuditJson();
/* 144 */     if (!Objects.equals(currentJson, this.lastAuditJson)) {
/* 145 */       parseData();
/*     */     }
/*     */   }
/*     */   
/*     */   private void parseData() {
/* 150 */     this.entries.clear();
/* 151 */     this.detailsTruncationCache.clear();
/* 152 */     LookupClientData data = LookupClientData.getInstance();
/* 153 */     String json = data.getAuditJson();
/* 154 */     this.totalEntries = data.getAuditTotal();
/* 155 */     this.currentPage = data.getAuditPage();
/* 156 */     this.lastAuditJson = json;
/*     */     
/* 158 */     if (json == null || json.isEmpty()) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 164 */     try { PokemonRenderHelper.init(); } catch (Exception exception) {}
/*     */     
/*     */     try {
/* 167 */       JsonArray arr = JsonParser.parseString(json).getAsJsonArray();
/* 168 */       for (int i = 0; i < arr.size(); i++) {
/* 169 */         JsonObject obj = arr.get(i).getAsJsonObject();
/* 170 */         String timestamp = obj.has("timestamp") ? obj.get("timestamp").getAsString() : "";
/* 171 */         String action = obj.has("action") ? obj.get("action").getAsString() : "";
/*     */         
/* 173 */         String actor = obj.has("actorName") ? obj.get("actorName").getAsString() : (obj.has("actor") ? obj.get("actor").getAsString() : "");
/*     */         
/* 175 */         String summary = "";
/* 176 */         List<class_1799> items = new ArrayList<>();
/* 177 */         JsonObject pokemonObj = null;
/*     */         
/* 179 */         if (obj.has("details")) {
/* 180 */           String raw = obj.get("details").getAsString();
/* 181 */           ParsedDetails parsed = parseDetailsPayload(raw);
/* 182 */           summary = parsed.summary();
/* 183 */           items = parsed.items();
/* 184 */           pokemonObj = parsed.pokemon();
/*     */         } 
/* 186 */         this.entries.add(new AuditEntry(timestamp, action, actor, summary, items, pokemonObj));
/*     */       } 
/* 188 */     } catch (Exception exception) {}
/*     */     
/* 190 */     if (this.scrollArea != null)
/* 191 */       this.scrollArea.setContentHeight(this.entries.size() * 24 + 6); 
/*     */   }
/*     */   private static final class ParsedDetails extends Record { private final String summary; private final List<class_1799> items;
/*     */     private final JsonObject pokemon;
/*     */     
/* 196 */     private ParsedDetails(String summary, List<class_1799> items, JsonObject pokemon) { this.summary = summary; this.items = items; this.pokemon = pokemon; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/lookup/screen/views/LookupAuditView$ParsedDetails;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #196	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/* 196 */       //   0	7	0	this	Lcom/atlas/common/fabric/lookup/screen/views/LookupAuditView$ParsedDetails; } public String summary() { return this.summary; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/lookup/screen/views/LookupAuditView$ParsedDetails;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #196	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/lookup/screen/views/LookupAuditView$ParsedDetails; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/lookup/screen/views/LookupAuditView$ParsedDetails;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #196	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lcom/atlas/common/fabric/lookup/screen/views/LookupAuditView$ParsedDetails;
/* 196 */       //   0	8	1	o	Ljava/lang/Object; } public List<class_1799> items() { return this.items; } public JsonObject pokemon() { return this.pokemon; }
/*     */      }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static ParsedDetails parseDetailsPayload(String raw) {
/* 205 */     if (raw == null || raw.isEmpty()) {
/* 206 */       return new ParsedDetails("", List.of(), null);
/*     */     }
/* 208 */     raw = raw.trim();
/* 209 */     if (!raw.startsWith("{")) {
/* 210 */       String s = (raw.length() > 80) ? (raw.substring(0, 80) + "…") : raw;
/* 211 */       return new ParsedDetails(s, List.of(), null);
/*     */     } 
/*     */     
/* 214 */     StringBuilder sb = new StringBuilder();
/* 215 */     List<class_1799> items = new ArrayList<>();
/* 216 */     JsonObject pokemonObj = null;
/*     */     
/*     */     try {
/* 219 */       JsonObject obj = JsonParser.parseString(raw).getAsJsonObject();
/* 220 */       for (Map.Entry<String, JsonElement> entry : (Iterable<Map.Entry<String, JsonElement>>)obj.entrySet()) {
/* 221 */         String key = entry.getKey();
/* 222 */         String keyLower = key.toLowerCase();
/* 223 */         JsonElement value = entry.getValue();
/*     */ 
/*     */         
/* 226 */         if (value.isJsonObject()) {
/* 227 */           JsonObject child = value.getAsJsonObject();
/*     */           
/* 229 */           if (child.has("id") && child.has("count")) {
/* 230 */             class_1799 stack = deserializeAuditStack(child);
/* 231 */             if (!stack.method_7960()) {
/* 232 */               items.add(stack);
/*     */               
/*     */               continue;
/*     */             } 
/*     */           } 
/* 237 */           if ((child.has("species") || MEDIA_KEYS.contains(keyLower)) && 
/* 238 */             pokemonObj == null && child.has("species")) {
/* 239 */             pokemonObj = child;
/*     */           }
/*     */ 
/*     */ 
/*     */           
/*     */           continue;
/*     */         } 
/*     */ 
/*     */         
/* 248 */         if (value.isJsonArray()) {
/*     */           continue;
/*     */         }
/* 251 */         if (sb.length() > 0) sb.append(", "); 
/* 252 */         String displayKey = camelToTitle(key);
/*     */ 
/*     */         
/* 255 */         String displayVal = value.isJsonNull() ? "null" : (value.isJsonPrimitive() ? value.getAsString() : value.toString());
/* 256 */         if (displayVal.contains("_") && !displayVal.contains(" ")) {
/* 257 */           displayVal = titleCaseUnderscore(displayVal);
/*     */         }
/* 259 */         sb.append(displayKey).append(": ").append(displayVal);
/* 260 */         if (sb.length() > 80) {
/* 261 */           sb.append("…");
/*     */           break;
/*     */         } 
/*     */       } 
/* 265 */     } catch (Exception ignored) {
/* 266 */       String fallback = (raw.length() > 80) ? (raw.substring(0, 80) + "…") : raw;
/* 267 */       return new ParsedDetails(fallback, items, pokemonObj);
/*     */     } 
/*     */     
/* 270 */     return new ParsedDetails(sb.toString(), items, pokemonObj);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static class_1799 deserializeAuditStack(JsonObject doc) {
/*     */     try {
/* 281 */       JsonObject codecJson = new JsonObject();
/* 282 */       codecJson.addProperty("id", doc.get("id").getAsString());
/* 283 */       codecJson.addProperty("count", Integer.valueOf(doc.get("count").getAsInt()));
/* 284 */       if (doc.has("components") && doc.get("components").isJsonObject()) {
/* 285 */         codecJson.add("components", doc.get("components"));
/*     */       }
/* 287 */       class_310 client = class_310.method_1551();
/* 288 */       if (client == null || client.field_1687 == null) return class_1799.field_8037; 
/* 289 */       class_6903<JsonElement> ops = class_6903.method_46632((DynamicOps)JsonOps.INSTANCE, (class_7225.class_7874)client.field_1687.method_30349());
/* 290 */       return (class_1799)((Pair)class_1799.field_24671.decode((DynamicOps)ops, codecJson).getOrThrow()).getFirst();
/* 291 */     } catch (Exception ignored) {
/* 292 */       return class_1799.field_8037;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(class_332 ctx, int mouseX, int mouseY, float delta, class_327 textRenderer) {
/* 301 */     String currentJson = LookupClientData.getInstance().getAuditJson();
/* 302 */     if (this.lastAuditJson == null && currentJson != null) {
/* 303 */       parseData();
/*     */     }
/*     */ 
/*     */     
/* 307 */     ctx.method_25294(this.x, this.y, this.x + this.width, this.y + 22, -15855080);
/* 308 */     ctx.method_51433(textRenderer, "§lAudit Log", this.x + 6, this.y + 7, -10777105, false);
/*     */     
/* 310 */     if (this.totalEntries > 0) {
/* 311 */       String countLabel = "" + this.totalEntries + " entries";
/* 312 */       int clw = textRenderer.method_1727(countLabel);
/* 313 */       ctx.method_51433(textRenderer, countLabel, this.x + this.width - clw - 6, this.y + 7, -9930592, false);
/*     */     } 
/*     */ 
/*     */     
/* 317 */     ctx.method_25294(this.x, this.y + 22 - 1, this.x + this.width, this.y + 22, -14012352);
/*     */     
/* 319 */     if (this.entries.isEmpty()) {
/* 320 */       String msg = (LookupClientData.getInstance().getAuditJson() == null) ? "Loading..." : "No audit entries found";
/* 321 */       int tw = textRenderer.method_1727(msg);
/* 322 */       ctx.method_51433(textRenderer, msg, this.x + (this.width - tw) / 2, this.y + this.height / 2, -9930592, false);
/* 323 */       renderFooter(ctx, mouseX, mouseY, textRenderer);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 328 */     this.scrollArea.beginRender(ctx);
/*     */     
/* 330 */     int contentTop = this.scrollArea.getY();
/* 331 */     int scrollOff = this.scrollArea.getScrollOffset();
/* 332 */     int scrollAreaBottom = this.scrollArea.getY() + this.scrollArea.getHeight();
/*     */ 
/*     */ 
/*     */     
/* 336 */     int firstIdx = Math.max(0, (scrollOff - 24) / 24);
/* 337 */     int lastIdx = Math.min(this.entries.size() - 1, (scrollOff + this.scrollArea
/* 338 */         .getHeight() + 24) / 24);
/*     */     
/* 340 */     this.hoveredIndex = -1;
/* 341 */     this.iconHits.clear();
/* 342 */     for (int i = firstIdx; i <= lastIdx; i++) {
/* 343 */       AuditEntry entry = this.entries.get(i);
/* 344 */       int rowY = contentTop + i * 24 - scrollOff;
/*     */       
/* 346 */       if (rowY + 24 >= this.scrollArea.getY() && rowY <= scrollAreaBottom) {
/*     */ 
/*     */         
/* 349 */         int rowBg = (i % 2 == 0) ? -14801866 : LookupColors.withAlpha(-15328732, 255);
/* 350 */         ctx.method_25294(this.x, rowY, this.x + this.width, rowY + 24, rowBg);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 356 */         boolean hovered = (mouseX >= this.x && mouseX < this.x + this.width && mouseY >= rowY && mouseY < rowY + 24 && mouseY >= this.scrollArea.getY() && mouseY < scrollAreaBottom);
/* 357 */         if (hovered) {
/* 358 */           this.hoveredIndex = i;
/* 359 */           ctx.method_25294(this.x, rowY, this.x + this.width, rowY + 24, -14274480);
/*     */           
/* 361 */           ctx.method_25294(this.x, rowY, this.x + 2, rowY + 24, -10777105);
/*     */         } 
/*     */         
/* 364 */         int textY = rowY + 7;
/* 365 */         int cx = this.x + 6 + (hovered ? 2 : 0);
/*     */ 
/*     */         
/* 368 */         String ts = formatTimestamp(entry.timestamp());
/* 369 */         ctx.method_51433(textRenderer, ts, cx, textY, -5592406, false);
/* 370 */         cx += textRenderer.method_1727(ts) + 6;
/*     */ 
/*     */         
/* 373 */         int[] badgeColors = badgeColors(entry.action());
/* 374 */         int badgeBg = badgeColors[0];
/* 375 */         int badgeFg = badgeColors[1];
/* 376 */         String actionLabel = formatActionLabel(entry.action());
/* 377 */         int badgeW = textRenderer.method_1727(actionLabel) + 8;
/* 378 */         int badgeH = 18;
/* 379 */         int badgeY = rowY + 3;
/* 380 */         ctx.method_25294(cx, badgeY, cx + badgeW, badgeY + badgeH, badgeBg);
/* 381 */         ctx.method_49601(cx, badgeY, badgeW, badgeH, LookupColors.withAlpha(badgeFg, 120));
/* 382 */         ctx.method_51433(textRenderer, actionLabel, cx + 4, textY, badgeFg, false);
/* 383 */         cx += badgeW + 6;
/*     */ 
/*     */         
/* 386 */         ctx.method_51433(textRenderer, entry.actor(), cx, textY, -1, true);
/* 387 */         cx += textRenderer.method_1727(entry.actor()) + 8;
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 392 */         int ICON_W = 18;
/* 393 */         int ICON_H = 18;
/* 394 */         int iconY = rowY + 3;
/*     */ 
/*     */         
/* 397 */         for (class_1799 stack : entry.itemStacks()) {
/* 398 */           if (stack == null || stack.method_7960())
/* 399 */             continue;  int iconCenterX = cx + 1;
/* 400 */           int iconCenterY = iconY + 1;
/* 401 */           ctx.method_51427(stack, iconCenterX, iconCenterY);
/*     */           
/* 403 */           ctx.method_51431(textRenderer, stack, iconCenterX, iconCenterY);
/* 404 */           this.iconHits.add(new IconHit(cx, iconY, 18, 18, stack, null));
/* 405 */           cx += 20;
/*     */         } 
/*     */ 
/*     */         
/* 409 */         JsonObject poke = entry.pokemonObj();
/* 410 */         if (poke != null) {
/* 411 */           renderPokemonInline(ctx, textRenderer, poke, cx, iconY, 18, 18, "audit_" + i, delta);
/* 412 */           this.iconHits.add(new IconHit(cx, iconY, 18, 18, null, poke));
/* 413 */           cx += 20;
/*     */ 
/*     */           
/* 416 */           String species = poke.has("species") ? poke.get("species").getAsString() : "?";
/* 417 */           int level = poke.has("level") ? poke.get("level").getAsInt() : 0;
/* 418 */           boolean shiny = (poke.has("shiny") && poke.get("shiny").getAsBoolean());
/* 419 */           String label = species + " Lv." + species + level;
/* 420 */           ctx.method_51433(textRenderer, label, cx, textY, 
/* 421 */               shiny ? -1662404 : -1511169, true);
/* 422 */           cx += textRenderer.method_1727(label) + 8;
/*     */         } 
/*     */ 
/*     */         
/* 426 */         if (!entry.details().isEmpty()) {
/* 427 */           int remaining = this.x + this.width - cx - 6 - 4;
/* 428 */           String detail = truncateCached(textRenderer, entry.details(), remaining);
/* 429 */           ctx.method_51433(textRenderer, detail, cx, textY, -9930592, false);
/*     */         } 
/*     */ 
/*     */         
/* 433 */         ctx.method_25294(this.x, rowY + 24 - 1, this.x + this.width, rowY + 24, 
/* 434 */             LookupColors.withAlpha(-14012352, 60));
/*     */       } 
/*     */     } 
/* 437 */     this.scrollArea.endRender(ctx);
/*     */     
/* 439 */     renderFooter(ctx, mouseX, mouseY, textRenderer);
/*     */ 
/*     */ 
/*     */     
/* 443 */     IconHit hit = null;
/* 444 */     for (IconHit h : this.iconHits) {
/* 445 */       if (mouseX >= h.x && mouseX < h.x + h.w && mouseY >= h.y && mouseY < h.y + h.h && mouseY >= this.scrollArea
/* 446 */         .getY() && mouseY < this.scrollArea.getY() + this.scrollArea.getHeight()) {
/* 447 */         hit = h;
/*     */         break;
/*     */       } 
/*     */     } 
/* 451 */     if (hit != null && hit.stack() != null && !hit.stack().method_7960()) {
/*     */ 
/*     */       
/* 454 */       class_4587 matrices = ctx.method_51448();
/* 455 */       matrices.method_22903();
/* 456 */       matrices.method_46416(0.0F, 0.0F, 500.0F);
/* 457 */       ctx.method_51446(textRenderer, hit.stack(), mouseX, mouseY);
/* 458 */       matrices.method_22909();
/* 459 */     } else if (hit != null && hit.pokemon() != null) {
/* 460 */       renderPokemonTooltip(ctx, textRenderer, hit.pokemon(), mouseX, mouseY);
/* 461 */     } else if (this.hoveredIndex >= 0 && this.hoveredIndex < this.entries.size()) {
/* 462 */       renderRowTooltip(ctx, textRenderer, this.entries.get(this.hoveredIndex), mouseX, mouseY);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderPokemonInline(class_332 ctx, class_327 tr, JsonObject poke, int sx, int sy, int sw, int sh, String stateKey, float delta) {
/* 473 */     String species = poke.has("species") ? poke.get("species").getAsString() : null;
/* 474 */     if (species == null || species.isEmpty() || !PokemonRenderHelper.isAvailable()) {
/* 475 */       ctx.method_25294(sx, sy, sx + sw, sy + sh, LookupColors.withAlpha(-10777105, 60));
/* 476 */       ctx.method_49601(sx, sy, sw, sh, -10777105);
/* 477 */       ctx.method_51433(tr, "P", sx + (sw - tr.method_1727("P")) / 2, sy + (sh - 8) / 2, -1, false);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/*     */     try {
/* 483 */       String slug = species.contains(":") ? species.substring(species.indexOf(':') + 1) : species;
/* 484 */       slug = slug.toLowerCase().replace(' ', '_');
/* 485 */       class_2960 speciesId = class_2960.method_60655("cobblemon", slug);
/*     */       
/* 487 */       String stateId = poke.has("uuid") ? poke.get("uuid").getAsString() : stateKey;
/* 488 */       FloatingState state = this.pokemonStates.computeIfAbsent(stateId, k -> new FloatingState());
/*     */ 
/*     */       
/* 491 */       Set<String> aspects = new HashSet<>();
/* 492 */       if (poke.has("shiny") && poke.get("shiny").getAsBoolean()) aspects.add("shiny"); 
/* 493 */       state.setCurrentAspects(aspects);
/*     */       
/* 495 */       class_4587 mat = ctx.method_51448();
/* 496 */       mat.method_22903();
/* 497 */       mat.method_22904(sx + sw / 2.0D, sy + 1.0D, 0.0D);
/* 498 */       float scale = sh / 9.0F;
/* 499 */       mat.method_22905(scale, scale, 1.0F);
/* 500 */       Quaternionf rot = new Quaternionf();
/* 501 */       rot.rotationXYZ((float)Math.toRadians(13.0D), (float)Math.toRadians(35.0D), 0.0F);
/* 502 */       PokemonRenderHelper.draw(speciesId, mat, rot, state, delta);
/* 503 */       mat.method_22909();
/* 504 */     } catch (Exception ignored) {
/* 505 */       ctx.method_25294(sx, sy, sx + sw, sy + sh, LookupColors.withAlpha(-10777105, 60));
/* 506 */       ctx.method_49601(sx, sy, sw, sh, -10777105);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderPokemonTooltip(class_332 ctx, class_327 tr, JsonObject poke, int mouseX, int mouseY) {
/* 517 */     int PAD = 5;
/* 518 */     int LINE_H = 11;
/* 519 */     int BAR_LABEL_W = 32;
/* 520 */     int BAR_W = 80;
/* 521 */     int BAR_VALUE_W = 22;
/*     */     
/* 523 */     List<String> headerLines = new ArrayList<>();
/* 524 */     String species = poke.has("species") ? poke.get("species").getAsString() : "?";
/* 525 */     int level = poke.has("level") ? poke.get("level").getAsInt() : 0;
/* 526 */     boolean shiny = (poke.has("shiny") && poke.get("shiny").getAsBoolean());
/* 527 */     String nickname = poke.has("nickname") ? poke.get("nickname").getAsString() : null;
/* 528 */     headerLines.add("§l" + species + (shiny ? " ★" : ""));
/* 529 */     if (nickname != null && !nickname.isBlank()) headerLines.add("§7\"" + nickname + "\""); 
/* 530 */     headerLines.add("Level: " + level);
/* 531 */     if (poke.has("nature")) headerLines.add("Nature: " + poke.get("nature").getAsString()); 
/* 532 */     if (poke.has("ability")) headerLines.add("Ability: " + poke.get("ability").getAsString()); 
/* 533 */     if (poke.has("ball")) headerLines.add("Ball: " + poke.get("ball").getAsString()); 
/* 534 */     if (poke.has("gender")) headerLines.add("Gender: " + poke.get("gender").getAsString());
/*     */     
/* 536 */     JsonObject ivs = (poke.has("ivs") && poke.get("ivs").isJsonObject()) ? poke.getAsJsonObject("ivs") : null;
/* 537 */     JsonObject evs = (poke.has("evs") && poke.get("evs").isJsonObject()) ? poke.getAsJsonObject("evs") : null;
/* 538 */     JsonArray moves = (poke.has("moves") && poke.get("moves").isJsonArray()) ? poke.getAsJsonArray("moves") : null;
/*     */ 
/*     */     
/* 541 */     int barSectionH = (ivs != null) ? (StatIcon.KEYS.length * 10 + 4) : 0;
/* 542 */     int textH = headerLines.size() * 11;
/* 543 */     int movesH = (moves != null && !moves.isEmpty()) ? 13 : 0;
/*     */     
/* 545 */     int maxTextW = 0;
/* 546 */     for (String line : headerLines) {
/* 547 */       int w = tr.method_1727(line);
/* 548 */       if (w > maxTextW) maxTextW = w; 
/*     */     } 
/* 550 */     int barColW = 138;
/* 551 */     int barRowW = (ivs != null && evs != null) ? (barColW * 2 + 12) : ((ivs != null) ? barColW : 0);
/* 552 */     int tooltipW = Math.max(maxTextW + 10, barRowW + 10);
/* 553 */     int tooltipH = textH + barSectionH + movesH + 10;
/* 554 */     if (barSectionH > 0) tooltipH += 4; 
/* 555 */     if (movesH > 0) tooltipH += 4;
/*     */     
/* 557 */     int tx = mouseX + 12;
/* 558 */     int ty = mouseY - 4;
/* 559 */     if (tx + tooltipW > this.x + this.width) tx = mouseX - tooltipW - 4; 
/* 560 */     if (ty + tooltipH > this.y + this.height) ty = this.y + this.height - tooltipH; 
/* 561 */     if (ty < this.y) ty = this.y;
/*     */     
/* 563 */     class_4587 matrices = ctx.method_51448();
/* 564 */     matrices.method_22903();
/* 565 */     matrices.method_46416(0.0F, 0.0F, 500.0F);
/*     */     
/* 567 */     ctx.method_25294(tx, ty, tx + tooltipW, ty + tooltipH, -267382768);
/* 568 */     ctx.method_49601(tx, ty, tooltipW, tooltipH, -12958368);
/*     */     
/* 570 */     int lineY = ty + 5;
/* 571 */     for (String line : headerLines) {
/* 572 */       ctx.method_51433(tr, line, tx + 5, lineY, -1, false);
/* 573 */       lineY += 11;
/*     */     } 
/*     */     
/* 576 */     if (ivs != null) {
/* 577 */       lineY += 4;
/* 578 */       int leftX = tx + 5;
/* 579 */       int rightX = leftX + barColW + 12;
/* 580 */       renderStatBars(ctx, tr, ivs, leftX, lineY, 32, 80, 22, 31, "IV");
/* 581 */       if (evs != null) {
/* 582 */         renderStatBars(ctx, tr, evs, rightX, lineY, 32, 80, 22, 252, "EV");
/*     */       }
/* 584 */       lineY += barSectionH;
/*     */     } 
/*     */     
/* 587 */     if (moves != null && !moves.isEmpty()) {
/* 588 */       lineY += 4;
/* 589 */       StringBuilder sb = new StringBuilder("Moves: ");
/* 590 */       for (int i = 0; i < moves.size() && i < 4; i++) {
/* 591 */         if (i > 0) sb.append(", "); 
/* 592 */         JsonElement e = moves.get(i);
/* 593 */         if (e.isJsonPrimitive()) { sb.append(e.getAsString()); }
/* 594 */         else if (e.isJsonObject() && e.getAsJsonObject().has("name")) { sb.append(e.getAsJsonObject().get("name").getAsString()); }
/*     */       
/* 596 */       }  ctx.method_51433(tr, sb.toString(), tx + 5, lineY, -9930592, false);
/*     */     } 
/*     */     
/* 599 */     matrices.method_22909();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void renderStatBars(class_332 ctx, class_327 tr, JsonObject stats, int sx, int sy, int labelW, int barW, int valueW, int maxVal, String header) {
/* 606 */     ctx.method_51433(tr, header, sx, sy - 10, -9930592, false);
/* 607 */     int rowY = sy;
/* 608 */     for (int i = 0; i < StatIcon.KEYS.length; i++) {
/* 609 */       String key = StatIcon.KEYS[i];
/* 610 */       int val = stats.has(key) ? stats.get(key).getAsInt() : 0;
/* 611 */       ctx.method_51433(tr, header.substring(0, 1) + header.substring(0, 1), sx, rowY, StatIcon.COLORS[i], false);
/*     */       
/* 613 */       int barX = sx + labelW;
/* 614 */       ctx.method_25294(barX, rowY + 2, barX + barW, rowY + 6, -14540254);
/* 615 */       int fillW = (int)(val / maxVal * barW);
/* 616 */       if (fillW > 0) ctx.method_25294(barX, rowY + 2, barX + fillW, rowY + 6, StatIcon.COLORS[i]); 
/* 617 */       ctx.method_51433(tr, String.valueOf(val), barX + barW + 3, rowY, -1, false);
/* 618 */       rowY += 10;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderRowTooltip(class_332 ctx, class_327 tr, AuditEntry entry, int mouseX, int mouseY) {
/* 628 */     int MAX_TOOLTIP_W = 260;
/* 629 */     int TOOLTIP_PADDING = 5;
/* 630 */     int LINE_H = 11;
/*     */     
/* 632 */     List<String> lines = new ArrayList<>();
/* 633 */     if (entry.timestamp() != null && !entry.timestamp().isEmpty()) {
/* 634 */       lines.add("§7" + entry.timestamp().replace('T', ' '));
/*     */     }
/* 636 */     if (entry.action() != null && !entry.action().isEmpty()) {
/* 637 */       lines.add("§f[" + formatActionLabel(entry.action()) + "]");
/*     */     }
/* 639 */     if (entry.actor() != null && !entry.actor().isEmpty()) {
/* 640 */       lines.add("§fActor: " + entry.actor());
/*     */     }
/* 642 */     if (entry.details() != null && !entry.details().isEmpty()) {
/*     */       
/* 644 */       List<String> wrapped = wordWrap(tr, entry.details(), 250);
/* 645 */       lines.addAll(wrapped);
/*     */     } 
/* 647 */     if (lines.isEmpty())
/*     */       return; 
/* 649 */     int tooltipW = 0;
/* 650 */     for (String line : lines) {
/* 651 */       int lw = tr.method_1727(line);
/* 652 */       if (lw > tooltipW) tooltipW = lw; 
/*     */     } 
/* 654 */     tooltipW = Math.min(tooltipW + 10, 270);
/* 655 */     int tooltipH = lines.size() * 11 + 10;
/*     */     
/* 657 */     int tx = mouseX + 12;
/* 658 */     int ty = mouseY - 4;
/* 659 */     if (tx + tooltipW > this.x + this.width) tx = mouseX - tooltipW - 4; 
/* 660 */     if (ty + tooltipH > this.y + this.height) ty = this.y + this.height - tooltipH; 
/* 661 */     if (ty < this.y) ty = this.y;
/*     */     
/* 663 */     class_4587 matrices = ctx.method_51448();
/* 664 */     matrices.method_22903();
/* 665 */     matrices.method_46416(0.0F, 0.0F, 400.0F);
/*     */     
/* 667 */     ctx.method_25294(tx, ty, tx + tooltipW, ty + tooltipH, -267382768);
/* 668 */     ctx.method_49601(tx, ty, tooltipW, tooltipH, -12958368);
/*     */     
/* 670 */     int lineY = ty + 5;
/* 671 */     for (String line : lines) {
/* 672 */       ctx.method_51433(tr, line, tx + 5, lineY, -1, false);
/* 673 */       lineY += 11;
/*     */     } 
/*     */     
/* 676 */     matrices.method_22909();
/*     */   }
/*     */ 
/*     */   
/*     */   private static List<String> wordWrap(class_327 tr, String text, int maxWidth) {
/* 681 */     List<String> result = new ArrayList<>();
/* 682 */     if (text == null || text.isEmpty()) return result; 
/* 683 */     String[] words = text.split(" ");
/* 684 */     StringBuilder current = new StringBuilder();
/* 685 */     for (String word : words) {
/* 686 */       String test = (current.length() == 0) ? word : (String.valueOf(current) + " " + String.valueOf(current));
/* 687 */       if (tr.method_1727(test) <= maxWidth) {
/* 688 */         if (current.length() > 0) current.append(' '); 
/* 689 */         current.append(word);
/*     */       } else {
/* 691 */         if (current.length() > 0) {
/* 692 */           result.add(current.toString());
/* 693 */           current = new StringBuilder();
/*     */         } 
/* 695 */         if (tr.method_1727(word) > maxWidth) {
/* 696 */           StringBuilder chunk = new StringBuilder();
/* 697 */           for (char c : word.toCharArray()) {
/* 698 */             if (tr.method_1727(chunk.toString() + chunk.toString()) <= maxWidth) {
/* 699 */               chunk.append(c);
/*     */             } else {
/* 701 */               result.add(chunk.toString());
/* 702 */               chunk = new StringBuilder(String.valueOf(c));
/*     */             } 
/*     */           } 
/* 705 */           current = chunk;
/*     */         } else {
/* 707 */           current.append(word);
/*     */         } 
/*     */       } 
/*     */     } 
/* 711 */     if (current.length() > 0) result.add(current.toString()); 
/* 712 */     return result;
/*     */   }
/*     */   
/*     */   private void renderFooter(class_332 ctx, int mouseX, int mouseY, class_327 textRenderer) {
/* 716 */     int footerY = this.y + this.height - 22;
/* 717 */     ctx.method_25294(this.x, footerY, this.x + this.width, footerY + 22, -15855080);
/* 718 */     ctx.method_25294(this.x, footerY, this.x + this.width, footerY + 1, -14012352);
/*     */     
/* 720 */     if (this.totalEntries > 0 && !this.entries.isEmpty()) {
/* 721 */       String loadMore = "Load More…";
/* 722 */       int lmw = textRenderer.method_1727(loadMore);
/* 723 */       int lmx = this.x + (this.width - lmw) / 2;
/* 724 */       boolean hover = (mouseX >= lmx && mouseX < lmx + lmw && mouseY >= footerY && mouseY < footerY + 22);
/* 725 */       ctx.method_51433(textRenderer, loadMore, lmx, footerY + 7, 
/* 726 */           hover ? -1 : -10777105, false);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
/* 734 */     if (this.scrollArea != null) {
/* 735 */       return this.scrollArea.handleScroll(mouseX, mouseY, verticalAmount);
/*     */     }
/* 737 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
/* 742 */     if (this.scrollArea != null) return this.scrollArea.handleMouseDragged(mouseX, mouseY, button); 
/* 743 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean mouseReleased(double mouseX, double mouseY, int button) {
/* 748 */     if (this.scrollArea != null) return this.scrollArea.handleMouseReleased(mouseX, mouseY, button); 
/* 749 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean mouseClicked(double mouseX, double mouseY, int button) {
/* 754 */     if (button != 0) return false;
/*     */ 
/*     */     
/* 757 */     if (this.scrollArea != null && this.scrollArea.handleMouseClicked(mouseX, mouseY, button)) {
/* 758 */       return true;
/*     */     }
/*     */ 
/*     */     
/* 762 */     int footerY = this.y + this.height - 22;
/* 763 */     if (mouseY >= footerY && mouseY < (footerY + 22) && this.totalEntries > 0) {
/* 764 */       this.currentPage++;
/* 765 */       String uuid = LookupClientData.getInstance().getTargetUuid();
/* 766 */       LookupNetwork.requestLookupData("audit", this.currentPage, (uuid != null) ? uuid : "");
/* 767 */       return true;
/*     */     } 
/*     */     
/* 770 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int[] badgeColors(String action) {
/* 779 */     if (action == null) return new int[] { -13619136, -7824965 }; 
/* 780 */     String lower = action.toLowerCase();
/* 781 */     if (lower.contains("open_lookup") || lower.contains("open")) {
/* 782 */       return new int[] { -15056768, -9785857 };
/*     */     }
/* 784 */     if (lower.contains("edit_inventory") || lower.contains("edit_vault") || lower.contains("edit_bank") || lower
/* 785 */       .startsWith("edit")) {
/* 786 */       return new int[] { -8372208, -21952 };
/*     */     }
/* 788 */     if (lower.contains("seal_pokemon") || lower.contains("issue_punishment") || lower
/* 789 */       .contains("delete_punishment") || lower.startsWith("seal") || lower.startsWith("delete")) {
/* 790 */       return new int[] { -8384496, -40864 };
/*     */     }
/* 792 */     if (lower.contains("pardon") || lower.startsWith("restore") || lower.startsWith("unlock")) {
/* 793 */       return new int[] { -15697856, -11477616 };
/*     */     }
/* 795 */     return new int[] { -13619136, -7824965 };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String formatActionLabel(String action) {
/* 802 */     if (action == null || action.isEmpty()) return "?";
/*     */     
/* 804 */     String[] parts = action.replace("_", " ").split(" ");
/* 805 */     StringBuilder sb = new StringBuilder();
/* 806 */     for (String part : parts) {
/* 807 */       if (!part.isEmpty()) {
/* 808 */         if (sb.length() > 0) sb.append(' '); 
/* 809 */         sb.append(Character.toUpperCase(part.charAt(0)));
/* 810 */         if (part.length() > 1) sb.append(part.substring(1).toLowerCase()); 
/*     */       } 
/* 812 */     }  return sb.toString();
/*     */   }
/*     */   
/*     */   private static String camelToTitle(String camel) {
/* 816 */     if (camel == null || camel.isEmpty()) return camel; 
/* 817 */     StringBuilder sb = new StringBuilder();
/* 818 */     sb.append(Character.toUpperCase(camel.charAt(0)));
/* 819 */     for (int i = 1; i < camel.length(); i++) {
/* 820 */       char c = camel.charAt(i);
/* 821 */       if (Character.isUpperCase(c)) {
/* 822 */         sb.append(' ');
/* 823 */         sb.append(c);
/*     */       } else {
/* 825 */         sb.append(c);
/*     */       } 
/*     */     } 
/* 828 */     return sb.toString();
/*     */   }
/*     */   
/*     */   private static String titleCaseUnderscore(String s) {
/* 832 */     String[] parts = s.split("_");
/* 833 */     StringBuilder sb = new StringBuilder();
/* 834 */     for (String p : parts) {
/* 835 */       if (!p.isEmpty()) {
/* 836 */         if (sb.length() > 0) sb.append(' '); 
/* 837 */         sb.append(Character.toUpperCase(p.charAt(0)));
/* 838 */         if (p.length() > 1) sb.append(p.substring(1).toLowerCase()); 
/*     */       } 
/* 840 */     }  return sb.toString();
/*     */   }
/*     */   
/*     */   private static String formatTimestamp(String timestamp) {
/* 844 */     if (timestamp == null || timestamp.isEmpty()) return "";
/*     */ 
/*     */     
/*     */     try {
/* 848 */       String s = timestamp.replace('T', ' ');
/*     */       
/* 850 */       int dotIdx = s.indexOf('.');
/* 851 */       if (dotIdx > 0) s = s.substring(0, dotIdx); 
/* 852 */       int zIdx = s.indexOf('Z');
/* 853 */       if (zIdx > 0) s = s.substring(0, zIdx);
/*     */       
/* 855 */       if (s.length() >= 16) {
/* 856 */         s = s.substring(5, 16);
/*     */       }
/* 858 */       return s;
/* 859 */     } catch (Exception ignored) {
/* 860 */       return (timestamp.length() > 16) ? timestamp.substring(0, 16) : timestamp;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static String truncateText(class_327 textRenderer, String text, int maxWidth) {
/* 865 */     if (maxWidth <= 0) return ""; 
/* 866 */     if (textRenderer.method_1727(text) <= maxWidth) return text; 
/* 867 */     String ellipsis = "…";
/* 868 */     int ellipsisW = textRenderer.method_1727(ellipsis);
/* 869 */     if (maxWidth <= ellipsisW) return ellipsis; 
/* 870 */     int target = maxWidth - ellipsisW;
/* 871 */     int len = text.length();
/* 872 */     while (len > 0 && textRenderer.method_1727(text.substring(0, len)) > target) {
/* 873 */       len--;
/*     */     }
/* 875 */     return text.substring(0, len) + text.substring(0, len);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String truncateCached(class_327 textRenderer, String text, int maxWidth) {
/* 885 */     if (text == null || text.isEmpty()) return "";
/*     */     
/* 887 */     int bucketed = Math.max(0, maxWidth / 4 * 4);
/* 888 */     String key = "" + bucketed + "|" + bucketed;
/* 889 */     String cached = this.detailsTruncationCache.get(key);
/* 890 */     if (cached != null) return cached; 
/* 891 */     String truncated = truncateText(textRenderer, text, bucketed);
/* 892 */     this.detailsTruncationCache.put(key, truncated);
/* 893 */     return truncated;
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\lookup\screen\views\LookupAuditView.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */