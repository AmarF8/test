/*     */ package com.atlas.common.fabric.lookup.screen.views;
/*     */ 
/*     */ import com.atlas.common.fabric.lookup.data.LookupClientData;
/*     */ import com.atlas.common.fabric.lookup.network.LookupNetwork;
/*     */ import com.atlas.common.fabric.lookup.screen.LookupColors;
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParser;
/*     */ import com.mojang.datafixers.util.Pair;
/*     */ import com.mojang.serialization.DynamicOps;
/*     */ import com.mojang.serialization.JsonOps;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.minecraft.class_1661;
/*     */ import net.minecraft.class_1799;
/*     */ import net.minecraft.class_310;
/*     */ import net.minecraft.class_327;
/*     */ import net.minecraft.class_332;
/*     */ import net.minecraft.class_4587;
/*     */ import net.minecraft.class_6903;
/*     */ import net.minecraft.class_7225;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LookupInventoryView
/*     */   implements LookupView
/*     */ {
/*     */   private int x;
/*     */   private int y;
/*     */   private int width;
/*     */   private int height;
/*     */   private boolean dataRequested = false;
/*     */   private boolean dataParsed = false;
/*  38 */   private long lastRefreshRequestAt = 0L;
/*     */   
/*  40 */   private final Runnable dataListener = this::onDataUpdated;
/*     */ 
/*     */   
/*  43 */   private final Map<Integer, JsonObject> slotMap = new HashMap<>();
/*  44 */   private final Map<Integer, class_1799> itemStacks = new HashMap<>();
/*  45 */   private final Map<String, JsonObject> namedSlots = new HashMap<>();
/*  46 */   private final Map<String, class_1799> namedItemStacks = new HashMap<>();
/*     */   
/*     */   private static final int SLOT_SIZE = 22;
/*     */   
/*     */   private static final int PADDING = 6;
/*     */   
/*     */   private static final int HEADER_HEIGHT = 18;
/*     */   
/*     */   private static final long REFRESH_INTERVAL_MS = 750L;
/*     */   private class_1799 tooltipStack;
/*     */   private int tooltipX;
/*     */   private int tooltipY;
/*     */   private boolean showTooltip;
/*  59 */   private class_1799 cursorStack = class_1799.field_8037;
/*  60 */   private String cursorFromSide = null;
/*  61 */   private int cursorFromSlot = -1;
/*     */   
/*  63 */   private int cursorCount = 0;
/*     */   
/*     */   private static final class SlotRect {
/*     */     final String side;
/*     */     final int slot;
/*     */     final int sx;
/*     */     final int sy;
/*     */     
/*     */     SlotRect(String side, int slot, int sx, int sy) {
/*  72 */       this.side = side; this.slot = slot; this.sx = sx; this.sy = sy;
/*     */     }
/*     */   }
/*  75 */   private final List<SlotRect> slotRects = new ArrayList<>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void init(int x, int y, int width, int height) {
/*  81 */     this.x = x;
/*  82 */     this.y = y;
/*  83 */     this.width = width;
/*  84 */     this.height = height;
/*     */     
/*  86 */     LookupClientData data = LookupClientData.getInstance();
/*  87 */     data.removeListener(this.dataListener);
/*  88 */     data.addListener(this.dataListener);
/*     */     
/*  90 */     if (!this.dataRequested) {
/*  91 */       this.dataRequested = true;
/*  92 */       String uuid = data.getTargetUuid();
/*  93 */       LookupNetwork.requestLookupData("inventory", 0, (uuid != null) ? uuid : "");
/*  94 */       this.lastRefreshRequestAt = System.currentTimeMillis();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void onDataUpdated() {
/*  99 */     this.dataParsed = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void parseData() {
/* 109 */     this.slotMap.clear();
/* 110 */     this.namedSlots.clear();
/* 111 */     this.itemStacks.clear();
/* 112 */     this.namedItemStacks.clear();
/* 113 */     String json = LookupClientData.getInstance().getInventoryJson();
/* 114 */     if (json == null || json.isEmpty()) {
/* 115 */       this.dataParsed = true;
/*     */       
/*     */       return;
/*     */     } 
/*     */     try {
/* 120 */       JsonElement root = JsonParser.parseString(json);
/* 121 */       if (root.isJsonObject()) {
/* 122 */         JsonObject obj = root.getAsJsonObject();
/* 123 */         parseSectionSlots(obj, "hotbar", "Hotbar", 0);
/* 124 */         parseSectionSlots(obj, "main", "Main Inventory", 9);
/* 125 */         parseSectionArmor(obj);
/* 126 */         parseSectionOffhand(obj);
/* 127 */       } else if (root.isJsonArray()) {
/* 128 */         JsonArray arr = root.getAsJsonArray();
/* 129 */         for (int i = 0; i < arr.size(); i++) {
/* 130 */           JsonObject item = arr.get(i).getAsJsonObject();
/* 131 */           int slot = item.has("slot") ? item.get("slot").getAsInt() : i;
/* 132 */           String section = item.has("section") ? item.get("section").getAsString() : "";
/* 133 */           if ("armor".equals(section)) {
/* 134 */             int armorIndex = slot - 36;
/* 135 */             String key = "armor_" + armorIndex;
/* 136 */             item.addProperty("_section", "Armor");
/* 137 */             this.namedSlots.put(key, item);
/* 138 */             if (item.has("stackJson")) {
/* 139 */               this.namedItemStacks.put(key, deserializeStack(item.get("stackJson").getAsString()));
/*     */             }
/* 141 */           } else if ("offhand".equals(section)) {
/* 142 */             String key = "offhand_0";
/* 143 */             item.addProperty("_section", "Offhand");
/* 144 */             this.namedSlots.put(key, item);
/* 145 */             if (item.has("stackJson")) {
/* 146 */               this.namedItemStacks.put(key, deserializeStack(item.get("stackJson").getAsString()));
/*     */             }
/*     */           } else {
/* 149 */             item.addProperty("_section", "Inventory");
/* 150 */             this.slotMap.put(Integer.valueOf(slot), item);
/* 151 */             if (item.has("stackJson")) {
/* 152 */               this.itemStacks.put(Integer.valueOf(slot), deserializeStack(item.get("stackJson").getAsString()));
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/* 157 */     } catch (Exception exception) {}
/*     */     
/* 159 */     this.dataParsed = true;
/*     */   }
/*     */   
/*     */   private void parseSectionSlots(JsonObject root, String key, String sectionLabel, int slotOffset) {
/* 163 */     if (!root.has(key))
/* 164 */       return;  JsonArray arr = root.getAsJsonArray(key);
/* 165 */     for (int i = 0; i < arr.size(); i++) {
/* 166 */       JsonObject item = arr.get(i).getAsJsonObject();
/* 167 */       int slot = item.has("slot") ? item.get("slot").getAsInt() : (slotOffset + i);
/* 168 */       item.addProperty("_section", sectionLabel);
/* 169 */       this.slotMap.put(Integer.valueOf(slot), item);
/* 170 */       if (item.has("stackJson")) {
/* 171 */         this.itemStacks.put(Integer.valueOf(slot), deserializeStack(item.get("stackJson").getAsString()));
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void parseSectionArmor(JsonObject root) {
/* 177 */     if (!root.has("armor"))
/* 178 */       return;  JsonArray arr = root.getAsJsonArray("armor");
/* 179 */     for (int i = 0; i < arr.size(); i++) {
/* 180 */       JsonObject item = arr.get(i).getAsJsonObject();
/* 181 */       int slot = item.has("slot") ? item.get("slot").getAsInt() : i;
/* 182 */       item.addProperty("_section", "Armor");
/* 183 */       String key = "armor_" + slot;
/* 184 */       this.namedSlots.put(key, item);
/* 185 */       if (item.has("stackJson")) {
/* 186 */         this.namedItemStacks.put(key, deserializeStack(item.get("stackJson").getAsString()));
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void parseSectionOffhand(JsonObject root) {
/* 192 */     if (!root.has("offhand"))
/* 193 */       return;  JsonArray arr = root.getAsJsonArray("offhand");
/* 194 */     for (int i = 0; i < arr.size(); i++) {
/* 195 */       JsonObject item = arr.get(i).getAsJsonObject();
/* 196 */       item.addProperty("_section", "Offhand");
/* 197 */       String key = "offhand_" + i;
/* 198 */       this.namedSlots.put(key, item);
/* 199 */       if (item.has("stackJson")) {
/* 200 */         this.namedItemStacks.put(key, deserializeStack(item.get("stackJson").getAsString()));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private class_1799 getTargetStack(int slot) {
/* 207 */     if (slot >= 0 && slot <= 35) {
/* 208 */       class_1799 s = this.itemStacks.get(Integer.valueOf(slot));
/* 209 */       return (s == null) ? class_1799.field_8037 : s;
/*     */     } 
/* 211 */     if (slot >= 36 && slot <= 39) {
/* 212 */       class_1799 s = this.namedItemStacks.get("armor_" + slot - 36);
/* 213 */       return (s == null) ? class_1799.field_8037 : s;
/*     */     } 
/* 215 */     if (slot == 40) {
/* 216 */       class_1799 s = this.namedItemStacks.get("offhand_0");
/* 217 */       return (s == null) ? class_1799.field_8037 : s;
/*     */     } 
/* 219 */     return class_1799.field_8037;
/*     */   }
/*     */ 
/*     */   
/*     */   private class_1799 getStaffStack(int slot) {
/* 224 */     class_310 mc = class_310.method_1551();
/* 225 */     if (mc == null || mc.field_1724 == null) return class_1799.field_8037; 
/* 226 */     class_1661 inv = mc.field_1724.method_31548();
/*     */     
/* 228 */     if (slot >= 0 && slot <= 40) {
/* 229 */       return inv.method_5438(slot);
/*     */     }
/* 231 */     return class_1799.field_8037;
/*     */   }
/*     */ 
/*     */   
/*     */   private class_1799 getStackFor(String side, int slot) {
/* 236 */     if ("target".equals(side)) return getTargetStack(slot); 
/* 237 */     if ("staff".equals(side)) return getStaffStack(slot); 
/* 238 */     return class_1799.field_8037;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(class_332 ctx, int mouseX, int mouseY, float delta, class_327 textRenderer) {
/* 245 */     maybeRefreshData();
/*     */     
/* 247 */     if (!this.dataParsed) {
/* 248 */       parseData();
/*     */     }
/*     */     
/* 251 */     boolean canEdit = resolveCanEdit();
/* 252 */     this.showTooltip = false;
/* 253 */     this.slotRects.clear();
/*     */ 
/*     */ 
/*     */     
/* 257 */     int slotStep = 24;
/* 258 */     int gridTotalW = 9 * slotStep - 2;
/* 259 */     int gridLeft = this.x + (this.width - gridTotalW) / 2;
/*     */     
/* 261 */     String targetTitle = "§lTarget's Inventory";
/* 262 */     int targetTitleW = textRenderer.method_1727(targetTitle);
/* 263 */     ctx.method_51433(textRenderer, targetTitle, this.x + (this.width - targetTitleW) / 2, this.y + 5, -10777105, false);
/*     */     
/* 265 */     if (this.slotMap.isEmpty() && this.namedSlots.isEmpty()) {
/* 266 */       String msg = (LookupClientData.getInstance().getInventoryJson() == null) ? "Loading..." : "No items found";
/* 267 */       int tw = textRenderer.method_1727(msg);
/* 268 */       ctx.method_51433(textRenderer, msg, this.x + (this.width - tw) / 2, this.y + 18 + 8, -9930592, false);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 273 */     int armorOffhandGap = 10;
/*     */ 
/*     */     
/* 276 */     int targetTop = this.y + 18;
/*     */ 
/*     */     
/* 279 */     int[] armorOrder = { 36, 37, 38, 39 };
/* 280 */     for (int i = 0; i < armorOrder.length; i++) {
/* 281 */       int sx = gridLeft + i * slotStep;
/* 282 */       renderSlotAt(ctx, textRenderer, "target", armorOrder[i], sx, targetTop, mouseX, mouseY);
/*     */     } 
/* 284 */     int offhandX = gridLeft + armorOrder.length * slotStep + armorOffhandGap;
/* 285 */     renderSlotAt(ctx, textRenderer, "target", 40, offhandX, targetTop, mouseX, mouseY);
/*     */ 
/*     */     
/* 288 */     int mainTop = targetTop + slotStep + 2;
/* 289 */     for (int row = 0; row < 3; row++) {
/* 290 */       for (int n = 0; n < 9; n++) {
/* 291 */         int slot = 9 + row * 9 + n;
/* 292 */         int sx = gridLeft + n * slotStep;
/* 293 */         int sy = mainTop + row * slotStep;
/* 294 */         renderSlotAt(ctx, textRenderer, "target", slot, sx, sy, mouseX, mouseY);
/*     */       } 
/*     */     } 
/*     */     
/* 298 */     int hotbarY = mainTop + 3 * slotStep + 4;
/* 299 */     for (int col = 0; col < 9; col++) {
/* 300 */       int sx = gridLeft + col * slotStep;
/* 301 */       renderSlotAt(ctx, textRenderer, "target", col, sx, hotbarY, mouseX, mouseY);
/*     */     } 
/*     */     
/* 304 */     int targetBottom = hotbarY + 22;
/* 305 */     int sepY = targetBottom + 4;
/* 306 */     ctx.method_25294(this.x + 6, sepY, this.x + this.width - 6, sepY + 1, -14012352);
/*     */     
/* 308 */     int staffLabelY = sepY + 6;
/* 309 */     String staffTitle = "§lYour Inventory";
/* 310 */     int staffTitleW = textRenderer.method_1727(staffTitle);
/* 311 */     ctx.method_51433(textRenderer, staffTitle, this.x + (this.width - staffTitleW) / 2, staffLabelY, -10777105, false);
/*     */     
/* 313 */     int staffTop = staffLabelY + 14;
/*     */ 
/*     */     
/* 316 */     for (int j = 0; j < armorOrder.length; j++) {
/* 317 */       int sx = gridLeft + j * slotStep;
/* 318 */       renderSlotAt(ctx, textRenderer, "staff", armorOrder[j], sx, staffTop, mouseX, mouseY);
/*     */     } 
/* 320 */     renderSlotAt(ctx, textRenderer, "staff", 40, offhandX, staffTop, mouseX, mouseY);
/*     */ 
/*     */     
/* 323 */     int staffMainTop = staffTop + slotStep + 2;
/* 324 */     for (int k = 0; k < 3; k++) {
/* 325 */       for (int n = 0; n < 9; n++) {
/* 326 */         int slot = 9 + k * 9 + n;
/* 327 */         int sx = gridLeft + n * slotStep;
/* 328 */         int sy = staffMainTop + k * slotStep;
/* 329 */         renderSlotAt(ctx, textRenderer, "staff", slot, sx, sy, mouseX, mouseY);
/*     */       } 
/*     */     } 
/*     */     
/* 333 */     int staffHotbarY = staffMainTop + 3 * slotStep + 4;
/* 334 */     for (int m = 0; m < 9; m++) {
/* 335 */       int sx = gridLeft + m * slotStep;
/* 336 */       renderSlotAt(ctx, textRenderer, "staff", m, sx, staffHotbarY, mouseX, mouseY);
/*     */     } 
/*     */ 
/*     */     
/* 340 */     if (this.showTooltip && this.tooltipStack != null && !this.tooltipStack.method_7960() && this.cursorStack.method_7960()) {
/* 341 */       class_4587 matrices = ctx.method_51448();
/* 342 */       matrices.method_22903();
/* 343 */       matrices.method_46416(0.0F, 0.0F, 500.0F);
/* 344 */       ctx.method_51446(textRenderer, this.tooltipStack, this.tooltipX, this.tooltipY);
/* 345 */       matrices.method_22909();
/*     */     } 
/*     */ 
/*     */     
/* 349 */     if (!this.cursorStack.method_7960()) {
/* 350 */       class_4587 matrices = ctx.method_51448();
/* 351 */       matrices.method_22903();
/* 352 */       matrices.method_46416(0.0F, 0.0F, 600.0F);
/* 353 */       int cx = mouseX - 8;
/* 354 */       int cy = mouseY - 8;
/* 355 */       ctx.method_51427(this.cursorStack, cx, cy);
/* 356 */       int count = this.cursorStack.method_7947();
/* 357 */       if (count > 1) {
/* 358 */         String countStr = String.valueOf(count);
/* 359 */         int countW = textRenderer.method_1727(countStr);
/* 360 */         ctx.method_51433(textRenderer, countStr, cx + 16 - countW, cy + 8, -1, true);
/*     */       } 
/* 362 */       matrices.method_22909();
/*     */     } 
/*     */     
/* 365 */     if (!canEdit) {
/*     */       
/* 367 */       String hint = "Read-only";
/* 368 */       int hw = textRenderer.method_1727(hint);
/* 369 */       ctx.method_51433(textRenderer, hint, this.x + this.width - hw - 6, this.y + 5, -9930592, false);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderSlotAt(class_332 ctx, class_327 textRenderer, String side, int slot, int sx, int sy, int mouseX, int mouseY) {
/* 379 */     this.slotRects.add(new SlotRect(side, slot, sx, sy));
/*     */ 
/*     */     
/* 382 */     boolean isCursorSource = (!this.cursorStack.method_7960() && side.equals(this.cursorFromSide) && slot == this.cursorFromSlot);
/*     */     
/* 384 */     ctx.method_25294(sx, sy, sx + 22, sy + 22, -14801866);
/* 385 */     ctx.method_49601(sx, sy, 22, 22, -14012352);
/*     */     
/* 387 */     boolean hovered = (mouseX >= sx && mouseX < sx + 22 && mouseY >= sy && mouseY < sy + 22);
/*     */ 
/*     */     
/* 390 */     class_1799 stack = getStackFor(side, slot);
/*     */ 
/*     */     
/* 393 */     int visualCount = (stack != null) ? stack.method_7947() : 0;
/* 394 */     if (isCursorSource) visualCount -= this.cursorCount; 
/* 395 */     boolean showItem = (stack != null && !stack.method_7960() && visualCount > 0);
/*     */     
/* 397 */     if (showItem) {
/* 398 */       int itemX = sx + 3;
/* 399 */       int itemY = sy + 3;
/* 400 */       ctx.method_51427(stack, itemX, itemY);
/*     */       
/* 402 */       if (visualCount > 1) {
/* 403 */         ctx.method_51448().method_22903();
/* 404 */         ctx.method_51448().method_46416(0.0F, 0.0F, 200.0F);
/* 405 */         String countStr = String.valueOf(visualCount);
/* 406 */         int countW = textRenderer.method_1727(countStr);
/* 407 */         ctx.method_51433(textRenderer, countStr, sx + 22 - countW - 1, sy + 22 - 9, -1, true);
/* 408 */         ctx.method_51448().method_22909();
/*     */       } 
/*     */       
/* 411 */       if (hovered && this.cursorStack.method_7960()) {
/* 412 */         ctx.method_25294(sx, sy, sx + 22, sy + 22, LookupColors.withAlpha(-10777105, 40));
/* 413 */         this.showTooltip = true;
/* 414 */         this.tooltipStack = stack;
/* 415 */         this.tooltipX = mouseX;
/* 416 */         this.tooltipY = mouseY;
/*     */       } 
/* 418 */     } else if (isCursorSource) {
/*     */       
/* 420 */       ctx.method_25294(sx + 1, sy + 1, sx + 22 - 1, sy + 22 - 1, 
/* 421 */           LookupColors.withAlpha(-12958368, 40));
/* 422 */     } else if (hovered) {
/* 423 */       ctx.method_25294(sx, sy, sx + 22, sy + 22, 
/* 424 */           LookupColors.withAlpha(-12958368, 30));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
/* 432 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean mouseClicked(double mouseX, double mouseY, int button) {
/* 438 */     if (button == 1) return handleRightClick(mouseX, mouseY); 
/* 439 */     if (button != 0) return false;
/*     */     
/* 441 */     boolean canEdit = resolveCanEdit();
/* 442 */     if (!canEdit) return false;
/*     */ 
/*     */     
/* 445 */     SlotRect hit = null;
/* 446 */     for (SlotRect r : this.slotRects) {
/* 447 */       if (mouseX >= r.sx && mouseX < (r.sx + 22) && mouseY >= r.sy && mouseY < (r.sy + 22)) {
/*     */         
/* 449 */         hit = r;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 454 */     if (this.cursorStack.method_7960()) {
/*     */       
/* 456 */       if (hit == null) return false; 
/* 457 */       class_1799 stack = getStackFor(hit.side, hit.slot);
/* 458 */       if (stack == null || stack.method_7960()) return false; 
/* 459 */       this.cursorStack = stack.method_7972();
/* 460 */       this.cursorFromSide = hit.side;
/* 461 */       this.cursorFromSlot = hit.slot;
/* 462 */       this.cursorCount = stack.method_7947();
/* 463 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 467 */     if (hit == null) {
/*     */       
/* 469 */       clearCursor();
/* 470 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 474 */     if (hit.side.equals(this.cursorFromSide) && hit.slot == this.cursorFromSlot) {
/* 475 */       clearCursor();
/* 476 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 480 */     String uuid = LookupClientData.getInstance().getTargetUuid();
/* 481 */     if (uuid != null) {
/* 482 */       class_1799 destStack = getStackFor(hit.side, hit.slot);
/*     */       
/* 484 */       boolean sameOrEmpty = (destStack == null || destStack.method_7960() || class_1799.method_31577(destStack, this.cursorStack));
/* 485 */       if (sameOrEmpty) {
/* 486 */         String filter = this.cursorFromSide + ":" + this.cursorFromSide + ":" + this.cursorFromSlot + ":" + hit.side + ":" + hit.slot + ":" + this.cursorCount;
/*     */         
/* 488 */         LookupNetwork.requestLookupData("inv_split", 0, filter);
/*     */       } else {
/* 490 */         String filter = this.cursorFromSide + ":" + this.cursorFromSide + ":" + this.cursorFromSlot + ":" + hit.side + ":" + hit.slot;
/* 491 */         LookupNetwork.requestLookupData("inv_move", 0, filter);
/*     */       } 
/* 493 */       this.lastRefreshRequestAt = 0L;
/*     */     } 
/* 495 */     clearCursor();
/* 496 */     return true;
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
/*     */   private boolean handleRightClick(double mouseX, double mouseY) {
/* 509 */     if (!resolveCanEdit()) return false;
/*     */     
/* 511 */     SlotRect hit = null;
/* 512 */     for (SlotRect r : this.slotRects) {
/* 513 */       if (mouseX >= r.sx && mouseX < (r.sx + 22) && mouseY >= r.sy && mouseY < (r.sy + 22)) {
/*     */         
/* 515 */         hit = r;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 520 */     if (this.cursorStack.method_7960()) {
/* 521 */       if (hit == null) return false; 
/* 522 */       class_1799 stack = getStackFor(hit.side, hit.slot);
/* 523 */       if (stack == null || stack.method_7960()) return false; 
/* 524 */       int half = (stack.method_7947() + 1) / 2;
/* 525 */       this.cursorStack = stack.method_7972();
/* 526 */       this.cursorStack.method_7939(half);
/* 527 */       this.cursorFromSide = hit.side;
/* 528 */       this.cursorFromSlot = hit.slot;
/* 529 */       this.cursorCount = half;
/* 530 */       return true;
/*     */     } 
/*     */     
/* 533 */     if (hit == null) return false; 
/* 534 */     if (hit.side.equals(this.cursorFromSide) && hit.slot == this.cursorFromSlot) {
/* 535 */       clearCursor();
/* 536 */       return true;
/*     */     } 
/*     */     
/* 539 */     class_1799 destStack = getStackFor(hit.side, hit.slot);
/*     */     
/* 541 */     boolean sameOrEmpty = (destStack == null || destStack.method_7960() || class_1799.method_31577(destStack, this.cursorStack));
/* 542 */     if (!sameOrEmpty) return true;
/*     */     
/* 544 */     String uuid = LookupClientData.getInstance().getTargetUuid();
/* 545 */     if (uuid == null) return true;
/*     */     
/* 547 */     String filter = this.cursorFromSide + ":" + this.cursorFromSide + ":" + this.cursorFromSlot + ":" + hit.side + ":1:" + hit.slot;
/*     */     
/* 549 */     LookupNetwork.requestLookupData("inv_split", 0, filter);
/* 550 */     this.lastRefreshRequestAt = 0L;
/*     */     
/* 552 */     this.cursorCount--;
/* 553 */     if (this.cursorCount <= 0) {
/* 554 */       clearCursor();
/*     */     } else {
/* 556 */       this.cursorStack.method_7939(this.cursorCount);
/*     */     } 
/* 558 */     return true;
/*     */   }
/*     */   
/*     */   private void clearCursor() {
/* 562 */     this.cursorStack = class_1799.field_8037;
/* 563 */     this.cursorFromSide = null;
/* 564 */     this.cursorFromSlot = -1;
/* 565 */     this.cursorCount = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean resolveCanEdit() {
/*     */     try {
/* 572 */       String overviewJson = LookupClientData.getInstance().getOverviewJson();
/* 573 */       if (overviewJson != null && !overviewJson.isEmpty()) {
/* 574 */         JsonObject obj = JsonParser.parseString(overviewJson).getAsJsonObject();
/* 575 */         return (obj.has("canEdit") && obj.get("canEdit").getAsBoolean());
/*     */       } 
/* 577 */     } catch (Exception exception) {}
/* 578 */     return false;
/*     */   }
/*     */   
/*     */   private void maybeRefreshData() {
/* 582 */     if (!this.dataRequested || !this.cursorStack.method_7960())
/*     */       return; 
/* 584 */     long now = System.currentTimeMillis();
/* 585 */     if (now - this.lastRefreshRequestAt < 750L)
/*     */       return; 
/* 587 */     String uuid = LookupClientData.getInstance().getTargetUuid();
/* 588 */     if (uuid == null || uuid.isEmpty())
/*     */       return; 
/* 590 */     LookupNetwork.requestLookupData("inventory", 0, uuid);
/* 591 */     this.lastRefreshRequestAt = now;
/*     */   }
/*     */   
/*     */   private static class_1799 deserializeStack(String json) {
/*     */     try {
/* 596 */       JsonElement element = JsonParser.parseString(json);
/* 597 */       class_6903<JsonElement> ops = class_6903.method_46632((DynamicOps)JsonOps.INSTANCE, 
/* 598 */           (class_7225.class_7874)(class_310.method_1551()).field_1687.method_30349());
/* 599 */       return (class_1799)((Pair)class_1799.field_24671.decode((DynamicOps)ops, element).getOrThrow()).getFirst();
/* 600 */     } catch (Exception e) {
/* 601 */       return class_1799.field_8037;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\lookup\screen\views\LookupInventoryView.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */