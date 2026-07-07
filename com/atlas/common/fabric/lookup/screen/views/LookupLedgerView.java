/*     */ package com.atlas.common.fabric.lookup.screen.views;
/*     */ 
/*     */ import com.atlas.common.fabric.guide.screen.widgets.GuideScrollableArea;
/*     */ import com.atlas.common.fabric.lookup.data.LookupClientData;
/*     */ import com.atlas.common.fabric.lookup.network.LookupNetwork;
/*     */ import com.atlas.common.fabric.lookup.screen.LookupColors;
/*     */ import com.atlas.common.fabric.lookup.screen.LookupSounds;
/*     */ import com.atlas.common.fabric.lookup.screen.widgets.CalendarPickerWidget;
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParser;
/*     */ import com.mojang.datafixers.util.Pair;
/*     */ import com.mojang.serialization.DynamicOps;
/*     */ import com.mojang.serialization.JsonOps;
/*     */ import java.time.Instant;
/*     */ import java.time.ZoneId;
/*     */ import java.time.format.DateTimeFormatter;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import net.minecraft.class_1799;
/*     */ import net.minecraft.class_310;
/*     */ import net.minecraft.class_327;
/*     */ import net.minecraft.class_332;
/*     */ import net.minecraft.class_6903;
/*     */ import net.minecraft.class_7225;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class LookupLedgerView implements LookupView {
/*     */   private int x;
/*     */   private int y;
/*     */   private int width;
/*     */   private int height;
/*     */   private boolean dataRequested = false;
/*     */   private boolean dataParsed = false;
/*  38 */   private int currentPage = 0;
/*     */   
/*     */   private GuideScrollableArea scrollArea;
/*  41 */   private final Runnable dataListener = this::onDataUpdated;
/*     */   
/*  43 */   private final List<LedgerEntry> entries = new ArrayList<>();
/*  44 */   private final List<LedgerEntry> filteredEntries = new ArrayList<>();
/*  45 */   private int totalEntries = 0;
/*     */ 
/*     */   
/*  48 */   private int hoveredRowIndex = -1;
/*     */ 
/*     */   
/*  51 */   private String searchQuery = "";
/*     */   
/*     */   private boolean searchFocused = false;
/*     */   private boolean searchHovered = false;
/*     */   private CalendarPickerWidget calendarPicker;
/*  56 */   private long dateFromMillis = -1L;
/*  57 */   private long dateToMillis = -1L;
/*     */ 
/*     */   
/*  60 */   private static final String[] ACTION_TYPES = new String[] { "block-break", "block-place", "item-insert", "item-remove", "item-drop", "item-pickup", "item-sell", "entity-kill" };
/*     */ 
/*     */ 
/*     */   
/*  64 */   private static final String[] ACTION_LABELS = new String[] { "Break", "Place", "Insert", "Remove", "Drop", "Pickup", "Sell", "Kill" };
/*     */ 
/*     */ 
/*     */   
/*  68 */   private final Set<String> enabledActions = new HashSet<>(); private static final int ROW_HEIGHT = 24; private static final int PADDING = 6; private static final int HEADER_HEIGHT = 22; private static final int SEARCH_ROW_HEIGHT = 16; private static final int FILTER_HEIGHT = 20; private static final int FOOTER_HEIGHT = 22; private static final int ENTRIES_PER_PAGE = 50;
/*     */   private static final int TOOLTIP_BG = -267383266;
/*     */   private static final int TOOLTIP_BORDER = -10777105;
/*     */   private static final int TOOLTIP_PADDING = 5;
/*     */   private static final int TOOLTIP_LINE_HEIGHT = 11;
/*     */   private boolean prevHovered = false;
/*     */   private boolean nextHovered = false;
/*     */   
/*     */   private static final class LedgerEntry extends Record { private final String timestamp;
/*     */     private final String action;
/*     */     private final String objectName;
/*     */     private final String coords;
/*     */     private final String server;
/*     */     private final String world;
/*     */     private final int blockX;
/*     */     private final int blockY;
/*     */     private final int blockZ;
/*     */     private final String extraData;
/*     */     private final boolean rolledBack;
/*     */     @Nullable
/*     */     private final class_1799 itemStack;
/*     */     
/*  90 */     private LedgerEntry(String timestamp, String action, String objectName, String coords, String server, String world, int blockX, int blockY, int blockZ, String extraData, boolean rolledBack, @Nullable class_1799 itemStack) { this.timestamp = timestamp; this.action = action; this.objectName = objectName; this.coords = coords; this.server = server; this.world = world; this.blockX = blockX; this.blockY = blockY; this.blockZ = blockZ; this.extraData = extraData; this.rolledBack = rolledBack; this.itemStack = itemStack; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/lookup/screen/views/LookupLedgerView$LedgerEntry;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #90	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*  90 */       //   0	7	0	this	Lcom/atlas/common/fabric/lookup/screen/views/LookupLedgerView$LedgerEntry; } public String timestamp() { return this.timestamp; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/lookup/screen/views/LookupLedgerView$LedgerEntry;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #90	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/lookup/screen/views/LookupLedgerView$LedgerEntry; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/lookup/screen/views/LookupLedgerView$LedgerEntry;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #90	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lcom/atlas/common/fabric/lookup/screen/views/LookupLedgerView$LedgerEntry;
/*  90 */       //   0	8	1	o	Ljava/lang/Object; } public String action() { return this.action; } public String objectName() { return this.objectName; } public String coords() { return this.coords; } public String server() { return this.server; } public String world() { return this.world; } public int blockX() { return this.blockX; } public int blockY() { return this.blockY; } public int blockZ() { return this.blockZ; } public String extraData() { return this.extraData; } public boolean rolledBack() { return this.rolledBack; } @Nullable public class_1799 itemStack() { return this.itemStack; }
/*     */      }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LookupLedgerView() {
/* 100 */     for (String action : ACTION_TYPES) {
/* 101 */       this.enabledActions.add(action);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void init(int x, int y, int width, int height) {
/* 107 */     this.x = x;
/* 108 */     this.y = y;
/* 109 */     this.width = width;
/* 110 */     this.height = height;
/*     */     
/* 112 */     int contentTop = y + 22 + 16 + 4 + 20 + 6;
/* 113 */     int contentHeight = height - 22 - 16 - 4 - 20 - 6 - 22;
/* 114 */     if (this.scrollArea == null) {
/* 115 */       this.scrollArea = new GuideScrollableArea(x, contentTop, width, contentHeight);
/* 116 */       this.scrollArea.setThumbColors(-12958368, -10777105);
/*     */     } else {
/* 118 */       this.scrollArea.updateBounds(x, contentTop, width, contentHeight);
/*     */     } 
/*     */     
/* 121 */     LookupClientData data = LookupClientData.getInstance();
/* 122 */     data.removeListener(this.dataListener);
/* 123 */     data.addListener(this.dataListener);
/*     */ 
/*     */     
/* 126 */     int searchRowY = y + 22 + 2;
/* 127 */     int calW = (int)(width * 0.4D);
/* 128 */     int calX = x + width - calW - 6;
/* 129 */     if (this.calendarPicker == null) {
/* 130 */       this.calendarPicker = new CalendarPickerWidget(calX, searchRowY, calW, 16);
/* 131 */       this.calendarPicker.setListener((from, to) -> {
/*     */             this.dateFromMillis = from;
/*     */             this.dateToMillis = to;
/*     */             requestPage(0);
/*     */           });
/*     */     } else {
/* 137 */       this.calendarPicker.updateBounds(calX, searchRowY, calW, 16);
/*     */     } 
/* 139 */     this.calendarPicker.setCommittedRange(this.dateFromMillis, this.dateToMillis);
/*     */     
/* 141 */     if (!this.dataRequested) {
/* 142 */       this.dataRequested = true;
/* 143 */       String uuid = data.getTargetUuid();
/* 144 */       String filterStr = buildFilterString(uuid);
/* 145 */       LookupNetwork.requestLookupData("ledger", this.currentPage, filterStr);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void onDataUpdated() {
/* 152 */     this.dataParsed = false;
/*     */   }
/*     */   
/*     */   private void parseData() {
/* 156 */     this.entries.clear();
/* 157 */     LookupClientData data = LookupClientData.getInstance();
/* 158 */     String json = data.getLedgerJson();
/* 159 */     this.totalEntries = data.getLedgerTotal();
/* 160 */     this.currentPage = data.getLedgerPage();
/*     */     
/* 162 */     if (json == null || json.isEmpty()) {
/* 163 */       this.dataParsed = true;
/* 164 */       applyFilter();
/*     */       
/*     */       return;
/*     */     } 
/*     */     try {
/* 169 */       JsonArray arr = JsonParser.parseString(json).getAsJsonArray();
/* 170 */       for (int i = 0; i < arr.size(); i++) {
/* 171 */         JsonObject obj = arr.get(i).getAsJsonObject();
/* 172 */         String timestamp = obj.has("timestamp") ? obj.get("timestamp").getAsString() : "";
/* 173 */         String action = obj.has("action") ? obj.get("action").getAsString() : "";
/* 174 */         String objectName = "";
/* 175 */         if (obj.has("object")) { objectName = obj.get("object").getAsString(); }
/* 176 */         else if (obj.has("objectName")) { objectName = obj.get("objectName").getAsString(); }
/* 177 */         else if (obj.has("block")) { objectName = obj.get("block").getAsString(); }
/* 178 */         else if (obj.has("item")) { objectName = obj.get("item").getAsString(); }
/*     */         
/* 180 */         int bx = 0, by = 0, bz = 0;
/* 181 */         String coords = "";
/* 182 */         if (obj.has("x") && obj.has("y") && obj.has("z")) {
/* 183 */           bx = obj.get("x").getAsInt();
/* 184 */           by = obj.get("y").getAsInt();
/* 185 */           bz = obj.get("z").getAsInt();
/* 186 */           coords = "" + bx + ", " + bx + ", " + by;
/* 187 */         } else if (obj.has("coords")) {
/* 188 */           coords = obj.get("coords").getAsString();
/*     */           
/* 190 */           String[] parts = coords.replace(" ", "").split(",");
/* 191 */           if (parts.length >= 3) {
/*     */             try {
/* 193 */               bx = Integer.parseInt(parts[0]);
/* 194 */               by = Integer.parseInt(parts[1]);
/* 195 */               bz = Integer.parseInt(parts[2]);
/* 196 */             } catch (NumberFormatException numberFormatException) {}
/*     */           }
/*     */         } 
/* 199 */         String server = obj.has("server") ? obj.get("server").getAsString() : "";
/* 200 */         String world = obj.has("world") ? obj.get("world").getAsString() : "";
/* 201 */         String extraData = obj.has("extra") ? obj.get("extra").getAsString() : "";
/* 202 */         boolean rolledBack = (obj.has("rolledBack") && obj.get("rolledBack").getAsBoolean());
/*     */         
/* 204 */         class_1799 itemStack = null;
/* 205 */         if (obj.has("stackJson")) {
/* 206 */           itemStack = deserializeStack(obj.get("stackJson").toString());
/*     */         }
/*     */         
/* 209 */         this.entries.add(new LedgerEntry(timestamp, action, objectName, coords, server, world, bx, by, bz, extraData, rolledBack, itemStack));
/*     */       }
/*     */     
/* 212 */     } catch (Exception exception) {}
/*     */     
/* 214 */     this.dataParsed = true;
/* 215 */     applyFilter();
/*     */   }
/*     */   
/*     */   private void applyFilter() {
/* 219 */     this.filteredEntries.clear();
/* 220 */     String lowerQuery = this.searchQuery.trim().toLowerCase();
/* 221 */     for (LedgerEntry entry : this.entries) {
/* 222 */       if (!this.enabledActions.contains(entry.action))
/* 223 */         continue;  if (!lowerQuery.isEmpty()) {
/* 224 */         boolean matchObj = (entry.objectName != null && entry.objectName.toLowerCase().contains(lowerQuery));
/* 225 */         boolean matchPretty = prettifyName(entry.objectName).toLowerCase().contains(lowerQuery);
/* 226 */         boolean matchAction = entry.action.toLowerCase().contains(lowerQuery);
/* 227 */         if (!matchObj && !matchPretty && !matchAction)
/*     */           continue; 
/* 229 */       }  this.filteredEntries.add(entry);
/*     */     } 
/* 231 */     this.scrollArea.setContentHeight(this.filteredEntries.size() * 24 + 6);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(class_332 ctx, int mouseX, int mouseY, float delta, class_327 textRenderer) {
/* 238 */     if (!this.dataParsed) {
/* 239 */       parseData();
/*     */     }
/*     */ 
/*     */     
/* 243 */     ctx.method_51433(textRenderer, "§lLedger", this.x + 6, this.y + 6, -10777105, false);
/*     */     
/* 245 */     if (this.totalEntries > 0) {
/* 246 */       String countLabel = "" + this.totalEntries + " entries";
/* 247 */       int clw = textRenderer.method_1727(countLabel);
/* 248 */       ctx.method_51433(textRenderer, countLabel, this.x + this.width - clw - 6, this.y + 6, -9930592, false);
/*     */     } 
/*     */ 
/*     */     
/* 252 */     renderSearchAndDateRow(ctx, mouseX, mouseY, textRenderer);
/*     */ 
/*     */     
/* 255 */     renderFilterBadges(ctx, mouseX, mouseY, textRenderer);
/*     */     
/* 257 */     if (this.filteredEntries.isEmpty()) {
/* 258 */       String msg = (LookupClientData.getInstance().getLedgerJson() == null) ? "Loading..." : "No ledger entries found";
/* 259 */       int tw = textRenderer.method_1727(msg);
/* 260 */       ctx.method_51433(textRenderer, msg, this.x + (this.width - tw) / 2, this.y + this.height / 2, -9930592, false);
/* 261 */       renderFooter(ctx, mouseX, mouseY, textRenderer);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 266 */     this.scrollArea.beginRender(ctx);
/*     */     
/* 268 */     int contentTop = this.scrollArea.getY();
/* 269 */     int scrollOff = this.scrollArea.getScrollOffset();
/* 270 */     this.hoveredRowIndex = -1;
/*     */     
/* 272 */     for (int i = 0; i < this.filteredEntries.size(); i++) {
/* 273 */       LedgerEntry entry = this.filteredEntries.get(i);
/* 274 */       int rowY = contentTop + i * 24 - scrollOff;
/*     */       
/* 276 */       if (rowY + 24 >= this.scrollArea.getY() && rowY <= this.scrollArea.getY() + this.scrollArea.getHeight()) {
/*     */ 
/*     */         
/* 279 */         if (i % 2 == 0) {
/* 280 */           ctx.method_25294(this.x, rowY, this.x + this.width, rowY + 24, -14801866);
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 286 */         boolean rowHovered = (mouseX >= this.x && mouseX < this.x + this.width && mouseY >= rowY && mouseY < rowY + 24 && mouseY >= this.scrollArea.getY() && mouseY < this.scrollArea.getY() + this.scrollArea.getHeight());
/* 287 */         if (rowHovered) {
/* 288 */           this.hoveredRowIndex = i;
/* 289 */           ctx.method_25294(this.x, rowY, this.x + this.width, rowY + 24, -14274480);
/* 290 */           ctx.method_25294(this.x, rowY, this.x + 2, rowY + 24, -10777105);
/*     */         } 
/*     */         
/* 293 */         int textY = rowY + 7;
/* 294 */         int cx = this.x + 6 + (rowHovered ? 2 : 0);
/*     */ 
/*     */         
/* 297 */         String ts = formatTimestamp(entry.timestamp);
/* 298 */         ctx.method_51433(textRenderer, ts, cx, textY, -9930592, false);
/* 299 */         cx += textRenderer.method_1727(ts) + 6;
/*     */ 
/*     */         
/* 302 */         int actionColor = LookupColors.actionColor(entry.action);
/* 303 */         String actionLabel = entry.action;
/* 304 */         int badgeW = textRenderer.method_1727(actionLabel) + 6;
/* 305 */         ctx.method_25294(cx, rowY + 3, cx + badgeW, rowY + 24 - 3, LookupColors.withAlpha(actionColor, 40));
/* 306 */         ctx.method_51433(textRenderer, actionLabel, cx + 3, textY, actionColor, false);
/* 307 */         cx += badgeW + 6;
/*     */ 
/*     */         
/* 310 */         String prettyObj = prettifyName(entry.objectName);
/* 311 */         if (entry.rolledBack) {
/*     */           
/* 313 */           ctx.method_51433(textRenderer, prettyObj, cx, textY, -9930592, false);
/* 314 */           int objW = textRenderer.method_1727(prettyObj);
/* 315 */           ctx.method_25294(cx, textY + 4, cx + objW, textY + 5, -1030329);
/* 316 */           cx += objW + 4;
/*     */ 
/*     */           
/* 319 */           String revertLabel = "Reverted";
/* 320 */           ctx.method_51433(textRenderer, revertLabel, cx, textY, -1030329, false);
/* 321 */           cx += textRenderer.method_1727(revertLabel) + 6;
/*     */         } else {
/* 323 */           ctx.method_51433(textRenderer, prettyObj, cx, textY, -1511169, false);
/* 324 */           cx += textRenderer.method_1727(prettyObj) + 6;
/*     */         } 
/*     */ 
/*     */         
/* 328 */         if (!entry.coords.isEmpty()) {
/* 329 */           ctx.method_51433(textRenderer, entry.coords, cx, textY, -9930592, false);
/* 330 */           cx += textRenderer.method_1727(entry.coords) + 6;
/*     */         } 
/*     */ 
/*     */         
/* 334 */         if (!entry.server.isEmpty()) {
/* 335 */           int sw = textRenderer.method_1727(entry.server);
/* 336 */           ctx.method_51433(textRenderer, entry.server, this.x + this.width - sw - 6 - 8, textY, -9930592, false);
/*     */         } 
/*     */       } 
/*     */     } 
/* 340 */     this.scrollArea.endRender(ctx);
/*     */     
/* 342 */     renderFooter(ctx, mouseX, mouseY, textRenderer);
/*     */ 
/*     */     
/* 345 */     if (this.calendarPicker == null || !this.calendarPicker.isMouseOverPopup(mouseX, mouseY)) {
/* 346 */       renderRowTooltip(ctx, mouseX, mouseY, textRenderer);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderRowTooltip(class_332 ctx, int mouseX, int mouseY, class_327 textRenderer) {
/* 353 */     if (this.hoveredRowIndex < 0 || this.hoveredRowIndex >= this.filteredEntries.size())
/*     */       return; 
/* 355 */     LedgerEntry entry = this.filteredEntries.get(this.hoveredRowIndex);
/*     */ 
/*     */     
/* 358 */     if (entry.itemStack != null && !entry.itemStack.method_7960()) {
/* 359 */       ctx.method_51446(textRenderer, entry.itemStack, mouseX, mouseY);
/*     */       
/*     */       return;
/*     */     } 
/* 363 */     List<TooltipLine> lines = new ArrayList<>();
/* 364 */     lines.add(new TooltipLine("Performed: " + formatAbsoluteLocal(entry.timestamp) + " (" + 
/* 365 */           formatTimestamp(entry.timestamp) + ")", -1511169));
/* 366 */     if (!entry.server.isEmpty()) {
/* 367 */       lines.add(new TooltipLine("Server: " + entry.server, -1511169));
/*     */     }
/* 369 */     if (!entry.world.isEmpty()) {
/* 370 */       lines.add(new TooltipLine("World: " + entry.world, -1511169));
/*     */     }
/* 372 */     lines.add(new TooltipLine("Object: " + entry.objectName, -1511169));
/* 373 */     if (!entry.coords.isEmpty()) {
/* 374 */       lines.add(new TooltipLine("Coords: " + entry.coords, -9930592));
/*     */     }
/* 376 */     if (!entry.extraData.isEmpty()) {
/* 377 */       lines.add(new TooltipLine("Extra: " + entry.extraData, -9930592));
/*     */     }
/* 379 */     lines.add(new TooltipLine("Click to teleport", -10777105));
/*     */     
/* 381 */     drawTooltip(ctx, textRenderer, lines, mouseX, mouseY);
/*     */   }
/*     */   private static final class TooltipLine extends Record { private final String text; private final int color;
/* 384 */     private TooltipLine(String text, int color) { this.text = text; this.color = color; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/lookup/screen/views/LookupLedgerView$TooltipLine;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #384	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/* 384 */       //   0	7	0	this	Lcom/atlas/common/fabric/lookup/screen/views/LookupLedgerView$TooltipLine; } public String text() { return this.text; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/lookup/screen/views/LookupLedgerView$TooltipLine;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #384	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/lookup/screen/views/LookupLedgerView$TooltipLine; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/lookup/screen/views/LookupLedgerView$TooltipLine;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #384	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lcom/atlas/common/fabric/lookup/screen/views/LookupLedgerView$TooltipLine;
/* 384 */       //   0	8	1	o	Ljava/lang/Object; } public int color() { return this.color; }
/*     */      }
/*     */   private void drawTooltip(class_332 ctx, class_327 textRenderer, List<TooltipLine> lines, int mouseX, int mouseY) {
/* 387 */     if (lines.isEmpty())
/*     */       return; 
/* 389 */     int maxW = 0;
/* 390 */     for (TooltipLine line : lines) {
/* 391 */       int w = textRenderer.method_1727(line.text);
/* 392 */       if (w > maxW) maxW = w;
/*     */     
/*     */     } 
/* 395 */     int tooltipW = maxW + 10;
/* 396 */     int tooltipH = lines.size() * 11 + 10 - 2;
/* 397 */     int tx = mouseX + 10;
/* 398 */     int ty = mouseY - tooltipH - 4;
/*     */ 
/*     */     
/* 401 */     if (tx + tooltipW > this.x + this.width) tx = mouseX - tooltipW - 4; 
/* 402 */     if (ty < this.y) ty = mouseY + 14;
/*     */ 
/*     */     
/* 405 */     ctx.method_25294(tx, ty, tx + tooltipW, ty + tooltipH, -267383266);
/* 406 */     ctx.method_49601(tx, ty, tooltipW, tooltipH, -10777105);
/*     */ 
/*     */     
/* 409 */     int lineY = ty + 5;
/* 410 */     for (TooltipLine line : lines) {
/* 411 */       ctx.method_51433(textRenderer, line.text, tx + 5, lineY, line.color, true);
/* 412 */       lineY += 11;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void renderSearchAndDateRow(class_332 ctx, int mouseX, int mouseY, class_327 textRenderer) {
/* 417 */     int rowY = this.y + 22 + 2;
/* 418 */     int rowH = 16;
/*     */ 
/*     */     
/* 421 */     int searchW = this.width / 2 - 12;
/* 422 */     int searchX = this.x + 6;
/*     */     
/* 424 */     this.searchHovered = (mouseX >= searchX && mouseX < searchX + searchW && mouseY >= rowY && mouseY < rowY + rowH);
/*     */ 
/*     */     
/* 427 */     int searchBg = this.searchFocused ? -15328732 : -14801866;
/* 428 */     int searchBorder = this.searchFocused ? -10777105 : (this.searchHovered ? -10777105 : -14012352);
/* 429 */     ctx.method_25294(searchX, rowY, searchX + searchW, rowY + rowH, searchBg);
/* 430 */     ctx.method_49601(searchX, rowY, searchW, rowH, searchBorder);
/*     */     
/* 432 */     String displayQuery = (this.searchQuery.isEmpty() && !this.searchFocused) ? "Search..." : (this.searchQuery + this.searchQuery);
/* 433 */     int queryColor = (this.searchQuery.isEmpty() && !this.searchFocused) ? -9930592 : -1511169;
/* 434 */     ctx.method_51433(textRenderer, displayQuery, searchX + 4, rowY + (rowH - 9) / 2, queryColor, false);
/*     */ 
/*     */     
/* 437 */     if (this.calendarPicker != null) {
/* 438 */       this.calendarPicker.render(ctx, mouseX, mouseY, textRenderer);
/*     */     }
/*     */   }
/*     */   
/*     */   private void renderFilterBadges(class_332 ctx, int mouseX, int mouseY, class_327 textRenderer) {
/* 443 */     int badgeY = this.y + 22 + 16 + 4;
/* 444 */     int badgeX = this.x + 6;
/*     */     
/* 446 */     for (int i = 0; i < ACTION_TYPES.length; i++) {
/* 447 */       String label = ACTION_LABELS[i];
/* 448 */       String actionType = ACTION_TYPES[i];
/* 449 */       int tw = textRenderer.method_1727(label);
/* 450 */       int bw = tw + 8;
/*     */       
/* 452 */       boolean enabled = this.enabledActions.contains(actionType);
/* 453 */       boolean hover = (mouseX >= badgeX && mouseX < badgeX + bw && mouseY >= badgeY && mouseY < badgeY + 20);
/*     */       
/* 455 */       int actionColor = LookupColors.actionColor(actionType);
/*     */ 
/*     */       
/* 458 */       if (enabled) {
/* 459 */         ctx.method_25294(badgeX, badgeY, badgeX + bw, badgeY + 20, LookupColors.withAlpha(actionColor, 50));
/*     */       } else {
/* 461 */         ctx.method_25294(badgeX, badgeY, badgeX + bw, badgeY + 20, -15328732);
/*     */       } 
/*     */ 
/*     */       
/* 465 */       if (hover) {
/* 466 */         ctx.method_25294(badgeX, badgeY, badgeX + bw, badgeY + 20, LookupColors.withAlpha(actionColor, 30));
/*     */       }
/*     */ 
/*     */       
/* 470 */       int textColor = enabled ? actionColor : -9930592;
/* 471 */       ctx.method_51433(textRenderer, label, badgeX + 4, badgeY + 5, textColor, false);
/*     */       
/* 473 */       badgeX += bw + 2;
/*     */ 
/*     */       
/* 476 */       if (badgeX + 40 > this.x + this.width) {
/* 477 */         badgeX = this.x + 6;
/* 478 */         badgeY += 22;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void renderFooter(class_332 ctx, int mouseX, int mouseY, class_327 textRenderer) {
/* 484 */     int footerY = this.y + this.height - 22;
/*     */     
/* 486 */     if (this.totalEntries <= 0)
/*     */       return; 
/* 488 */     int totalPages = Math.max(1, (this.totalEntries + 50 - 1) / 50);
/*     */ 
/*     */     
/* 491 */     String pageLabel = "Page " + this.currentPage + 1 + " / " + totalPages;
/* 492 */     int pageLabelW = textRenderer.method_1727(pageLabel);
/* 493 */     ctx.method_51433(textRenderer, pageLabel, this.x + (this.width - pageLabelW) / 2, footerY + 6, -9930592, false);
/*     */ 
/*     */     
/* 496 */     this.prevHovered = false;
/* 497 */     if (this.currentPage > 0) {
/* 498 */       String prevText = "< Prev";
/* 499 */       int prevW = textRenderer.method_1727(prevText);
/* 500 */       int prevX = this.x + 6;
/* 501 */       this.prevHovered = (mouseX >= prevX && mouseX < prevX + prevW && mouseY >= footerY && mouseY < footerY + 22);
/* 502 */       ctx.method_51433(textRenderer, prevText, prevX, footerY + 6, this.prevHovered ? -10777105 : -9930592, false);
/*     */     } 
/*     */ 
/*     */     
/* 506 */     this.nextHovered = false;
/* 507 */     if (this.currentPage < totalPages - 1) {
/* 508 */       String nextText = "Next >";
/* 509 */       int nextW = textRenderer.method_1727(nextText);
/* 510 */       int nextX = this.x + this.width - nextW - 6;
/* 511 */       this.nextHovered = (mouseX >= nextX && mouseX < nextX + nextW && mouseY >= footerY && mouseY < footerY + 22);
/* 512 */       ctx.method_51433(textRenderer, nextText, nextX, footerY + 6, this.nextHovered ? -10777105 : -9930592, false);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
/* 520 */     if (this.scrollArea != null) {
/* 521 */       return this.scrollArea.handleScroll(mouseX, mouseY, verticalAmount);
/*     */     }
/* 523 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
/* 528 */     if (this.scrollArea != null) return this.scrollArea.handleMouseDragged(mouseX, mouseY, button); 
/* 529 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean mouseReleased(double mouseX, double mouseY, int button) {
/* 534 */     if (this.scrollArea != null) return this.scrollArea.handleMouseReleased(mouseX, mouseY, button); 
/* 535 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean mouseClicked(double mouseX, double mouseY, int button) {
/* 540 */     if (button != 0) return false;
/*     */ 
/*     */     
/* 543 */     if (this.scrollArea != null && this.scrollArea.handleMouseClicked(mouseX, mouseY, button)) {
/* 544 */       return true;
/*     */     }
/*     */ 
/*     */     
/* 548 */     int searchRowY = this.y + 22 + 2;
/* 549 */     int searchW = this.width / 2 - 12;
/* 550 */     int searchX = this.x + 6;
/* 551 */     if (mouseX >= searchX && mouseX < (searchX + searchW) && mouseY >= searchRowY && mouseY < (searchRowY + 16)) {
/*     */       
/* 553 */       this.searchFocused = true;
/* 554 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 558 */     if (this.calendarPicker != null && this.calendarPicker.mouseClicked(mouseX, mouseY, button)) {
/* 559 */       return true;
/*     */     }
/*     */ 
/*     */     
/* 563 */     if (this.searchFocused) {
/* 564 */       this.searchFocused = false;
/*     */     }
/*     */ 
/*     */     
/* 568 */     int badgeY = this.y + 22 + 16 + 4;
/* 569 */     int badgeX = this.x + 6;
/* 570 */     for (int i = 0; i < ACTION_TYPES.length; i++) {
/* 571 */       int bw = ACTION_LABELS[i].length() * 6 + 8;
/* 572 */       if (mouseX >= badgeX && mouseX < (badgeX + bw) && mouseY >= badgeY && mouseY < (badgeY + 20)) {
/* 573 */         String actionType = ACTION_TYPES[i];
/* 574 */         if (this.enabledActions.contains(actionType)) {
/* 575 */           this.enabledActions.remove(actionType);
/*     */         } else {
/* 577 */           this.enabledActions.add(actionType);
/*     */         } 
/* 579 */         LookupSounds.playClick();
/* 580 */         applyFilter();
/* 581 */         this.scrollArea.resetScroll();
/* 582 */         return true;
/*     */       } 
/* 584 */       badgeX += bw + 2;
/* 585 */       if (badgeX + 40 > this.x + this.width) {
/* 586 */         badgeX = this.x + 6;
/* 587 */         badgeY += 22;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 592 */     int footerY = this.y + this.height - 22;
/* 593 */     if (mouseY >= footerY && mouseY < (footerY + 22)) {
/* 594 */       if (this.prevHovered && this.currentPage > 0) {
/* 595 */         LookupSounds.playClick();
/* 596 */         this.currentPage--;
/* 597 */         requestPage(this.currentPage);
/* 598 */         return true;
/*     */       } 
/* 600 */       if (this.nextHovered) {
/* 601 */         int totalPages = Math.max(1, (this.totalEntries + 50 - 1) / 50);
/* 602 */         if (this.currentPage < totalPages - 1) {
/* 603 */           LookupSounds.playClick();
/* 604 */           this.currentPage++;
/* 605 */           requestPage(this.currentPage);
/* 606 */           return true;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 612 */     if (this.hoveredRowIndex >= 0 && this.hoveredRowIndex < this.filteredEntries.size()) {
/* 613 */       LedgerEntry entry = this.filteredEntries.get(this.hoveredRowIndex);
/* 614 */       if (!entry.coords.isEmpty()) {
/* 615 */         LookupSounds.playNavigate();
/* 616 */         String teleportData = "" + entry.blockX + ":" + entry.blockX + ":" + entry.blockY + 1 + ":" + entry.blockZ;
/* 617 */         LookupNetwork.requestLookupData("teleport", 0, teleportData);
/* 618 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/* 622 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
/* 627 */     if (!this.searchFocused) return false; 
/* 628 */     if (keyCode == 259 && !this.searchQuery.isEmpty()) {
/* 629 */       this.searchQuery = this.searchQuery.substring(0, this.searchQuery.length() - 1);
/* 630 */       applyFilter();
/* 631 */       this.scrollArea.resetScroll();
/* 632 */       return true;
/*     */     } 
/* 634 */     if (keyCode == 256) {
/* 635 */       this.searchFocused = false;
/* 636 */       return true;
/*     */     } 
/* 638 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean charTyped(char chr, int modifiers) {
/* 643 */     if (!this.searchFocused) return false; 
/* 644 */     if (chr >= ' ') {
/* 645 */       this.searchQuery += this.searchQuery;
/* 646 */       applyFilter();
/* 647 */       this.scrollArea.resetScroll();
/* 648 */       return true;
/*     */     } 
/* 650 */     return false;
/*     */   }
/*     */   
/*     */   private void requestPage(int page) {
/* 654 */     this.dataParsed = false;
/* 655 */     this.entries.clear();
/* 656 */     this.filteredEntries.clear();
/* 657 */     this.scrollArea.resetScroll();
/* 658 */     this.currentPage = page;
/* 659 */     String uuid = LookupClientData.getInstance().getTargetUuid();
/* 660 */     String filterStr = buildFilterString(uuid);
/* 661 */     LookupNetwork.requestLookupData("ledger", page, filterStr);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String buildFilterString(String uuid) {
/* 670 */     StringBuilder sb = new StringBuilder();
/* 671 */     if (!this.searchQuery.isEmpty()) sb.append("search=").append(this.searchQuery); 
/* 672 */     sb.append("|");
/* 673 */     if (this.dateFromMillis > 0L) sb.append("date_from=").append(this.dateFromMillis); 
/* 674 */     sb.append("|");
/* 675 */     if (this.dateToMillis > 0L) sb.append("date_to=").append(this.dateToMillis); 
/* 676 */     sb.append("|");
/* 677 */     if (uuid != null) sb.append(uuid); 
/* 678 */     return sb.toString();
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
/*     */   private static String formatTimestamp(String timestamp) {
/* 690 */     if (timestamp == null || timestamp.isEmpty()) return ""; 
/*     */     try {
/* 692 */       Instant then = Instant.parse(timestamp);
/* 693 */       long secs = (System.currentTimeMillis() - then.toEpochMilli()) / 1000L;
/* 694 */       if (secs < 0L) secs = 0L; 
/* 695 */       if (secs < 10L) return "just now"; 
/* 696 */       if (secs < 60L) return "" + secs + "s ago"; 
/* 697 */       long mins = secs / 60L;
/* 698 */       if (mins < 60L) return "" + mins + "m ago"; 
/* 699 */       long hours = mins / 60L;
/* 700 */       if (hours < 24L) return "" + hours + "h ago"; 
/* 701 */       long days = hours / 24L;
/* 702 */       if (days < 30L) return "" + days + "d ago"; 
/* 703 */       long months = days / 30L;
/* 704 */       if (months < 12L) return "" + months + "mo ago"; 
/* 705 */       return "" + days / 365L + "y ago";
/* 706 */     } catch (Exception e) {
/* 707 */       if (timestamp.length() > 16) return timestamp.substring(0, 16).replace('T', ' '); 
/* 708 */       return timestamp;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static String formatAbsoluteLocal(String timestamp) {
/* 714 */     if (timestamp == null || timestamp.isEmpty()) return ""; 
/*     */     try {
/* 716 */       Instant then = Instant.parse(timestamp);
/* 717 */       return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
/* 718 */         .withZone(ZoneId.systemDefault()).format(then);
/* 719 */     } catch (Exception e) {
/* 720 */       return timestamp;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static class_1799 deserializeStack(String json) {
/*     */     try {
/* 726 */       JsonElement element = JsonParser.parseString(json);
/* 727 */       class_6903<JsonElement> ops = class_6903.method_46632((DynamicOps)JsonOps.INSTANCE, 
/* 728 */           (class_7225.class_7874)(class_310.method_1551()).field_1687.method_30349());
/* 729 */       return (class_1799)((Pair)class_1799.field_24671.decode((DynamicOps)ops, element).getOrThrow()).getFirst();
/* 730 */     } catch (Exception e) {
/* 731 */       return class_1799.field_8037;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static String prettifyName(String id) {
/* 736 */     if (id == null || id.isEmpty()) return ""; 
/* 737 */     int colon = id.indexOf(':');
/* 738 */     String name = (colon >= 0) ? id.substring(colon + 1) : id;
/* 739 */     StringBuilder sb = new StringBuilder();
/* 740 */     for (String word : name.split("_")) {
/* 741 */       if (!word.isEmpty()) {
/* 742 */         if (sb.length() > 0) sb.append(' '); 
/* 743 */         sb.append(Character.toUpperCase(word.charAt(0)));
/* 744 */         if (word.length() > 1) sb.append(word.substring(1)); 
/*     */       } 
/*     */     } 
/* 747 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\lookup\screen\views\LookupLedgerView.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */