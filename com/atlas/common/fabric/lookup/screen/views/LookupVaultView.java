/*      */ package com.atlas.common.fabric.lookup.screen.views;
/*      */ 
/*      */ import com.atlas.common.fabric.lookup.data.LookupClientData;
/*      */ import com.atlas.common.fabric.lookup.network.LookupNetwork;
/*      */ import com.atlas.common.fabric.lookup.screen.LookupColors;
/*      */ import com.google.gson.JsonArray;
/*      */ import com.google.gson.JsonElement;
/*      */ import com.google.gson.JsonObject;
/*      */ import com.google.gson.JsonParser;
/*      */ import com.mojang.datafixers.util.Pair;
/*      */ import com.mojang.serialization.DynamicOps;
/*      */ import com.mojang.serialization.JsonOps;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import net.minecraft.class_1661;
/*      */ import net.minecraft.class_1799;
/*      */ import net.minecraft.class_310;
/*      */ import net.minecraft.class_327;
/*      */ import net.minecraft.class_332;
/*      */ import net.minecraft.class_4587;
/*      */ import net.minecraft.class_6903;
/*      */ import net.minecraft.class_7225;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class LookupVaultView
/*      */   implements LookupView
/*      */ {
/*      */   private int x;
/*      */   private int y;
/*      */   private int width;
/*      */   private int height;
/*      */   private boolean dataRequested = false;
/*      */   private boolean dataParsed = false;
/*   38 */   private int currentPage = 0;
/*   39 */   private long lastRefreshRequestAt = 0L;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   46 */   private String selectedVaultType = null;
/*      */   
/*   48 */   private String selectedStorageVaultId = null;
/*   49 */   private String selectedStorageVaultName = null;
/*      */   
/*      */   private int[] tabXPositions;
/*      */   
/*      */   private int[] tabWidths;
/*      */   
/*      */   private String[] tabLabels;
/*   56 */   private final Runnable dataListener = this::onDataUpdated;
/*      */ 
/*      */   
/*   59 */   private final List<VaultItem> allItems = new ArrayList<>();
/*   60 */   private final List<VaultItem> filteredItems = new ArrayList<>();
/*   61 */   private final List<String> vaultTypes = new ArrayList<>();
/*   62 */   private String activeType = null;
/*   63 */   private int totalItems = 0;
/*      */ 
/*      */   
/*   66 */   private final List<StorageVaultEntry> storageVaults = new ArrayList<>();
/*      */   
/*      */   private boolean storageListParsed = false;
/*      */   
/*      */   private static final int SLOT_SIZE = 22;
/*      */   
/*      */   private static final int PADDING = 6;
/*      */   
/*      */   private static final int HEADER_HEIGHT = 22;
/*      */   
/*      */   private static final int TAB_HEIGHT = 28;
/*      */   
/*      */   private static final int FOOTER_HEIGHT = 22;
/*      */   
/*      */   private static final int GRID_COLS = 9;
/*      */   private static final long REFRESH_INTERVAL_MS = 750L;
/*      */   private static final int CARD_W = 110;
/*      */   private static final int CARD_H = 60;
/*      */   private static final int CARD_GAP = 12;
/*      */   private static final int STORAGE_SLOT_W = 100;
/*      */   private static final int STORAGE_SLOT_H = 32;
/*      */   private static final int STORAGE_SLOT_GAP = 6;
/*      */   private static final int STORAGE_COLS = 4;
/*   89 */   private static final String[] VAULT_TYPES = new String[] { "lost", "shop", "auction", "storage_list" };
/*   90 */   private static final String[] VAULT_LABELS = new String[] { "Lost Vault", "Shop Vault", "Auction Vault", "Player Vaults" };
/*      */ 
/*      */   
/*   93 */   private final int[] selectorCardX = new int[VAULT_TYPES.length];
/*   94 */   private final int[] selectorCardY = new int[VAULT_TYPES.length];
/*      */   
/*      */   private int[] storageSlotX;
/*      */   
/*      */   private int[] storageSlotY;
/*      */   
/*      */   private class_1799 tooltipStack;
/*      */   
/*      */   private int tooltipX;
/*      */   private int tooltipY;
/*      */   private boolean showTooltip;
/*      */   private int backBtnX;
/*      */   private int backBtnY;
/*      */   private int backBtnW;
/*      */   private int backBtnH;
/*  109 */   private class_1799 cursorStack = class_1799.field_8037;
/*  110 */   private String cursorFromSide = null;
/*  111 */   private int cursorFromSlot = -1;
/*      */   
/*  113 */   private int cursorCount = 0;
/*      */   
/*      */   private static final class SlotRect { final String side;
/*      */     final int slot;
/*      */     final int sx;
/*      */     final int sy;
/*      */     
/*      */     SlotRect(String side, int slot, int sx, int sy) {
/*  121 */       this.side = side; this.slot = slot; this.sx = sx; this.sy = sy;
/*      */     } }
/*      */   
/*  124 */   private final List<SlotRect> slotRects = new ArrayList<>();
/*      */   private static final class VaultItem extends Record { private final int slot; private final String name; private final String id; private final int count; private final String section; private final String extra;
/*      */     private final class_1799 stack;
/*      */     
/*  128 */     private VaultItem(int slot, String name, String id, int count, String section, String extra, class_1799 stack) { this.slot = slot; this.name = name; this.id = id; this.count = count; this.section = section; this.extra = extra; this.stack = stack; } public final String toString() { // Byte code:
/*      */       //   0: aload_0
/*      */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/lookup/screen/views/LookupVaultView$VaultItem;)Ljava/lang/String;
/*      */       //   6: areturn
/*      */       // Line number table:
/*      */       //   Java source line number -> byte code offset
/*      */       //   #128	-> 0
/*      */       // Local variable table:
/*      */       //   start	length	slot	name	descriptor
/*  128 */       //   0	7	0	this	Lcom/atlas/common/fabric/lookup/screen/views/LookupVaultView$VaultItem; } public int slot() { return this.slot; } public final int hashCode() { // Byte code:
/*      */       //   0: aload_0
/*      */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/lookup/screen/views/LookupVaultView$VaultItem;)I
/*      */       //   6: ireturn
/*      */       // Line number table:
/*      */       //   Java source line number -> byte code offset
/*      */       //   #128	-> 0
/*      */       // Local variable table:
/*      */       //   start	length	slot	name	descriptor
/*      */       //   0	7	0	this	Lcom/atlas/common/fabric/lookup/screen/views/LookupVaultView$VaultItem; } public final boolean equals(Object o) { // Byte code:
/*      */       //   0: aload_0
/*      */       //   1: aload_1
/*      */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/lookup/screen/views/LookupVaultView$VaultItem;Ljava/lang/Object;)Z
/*      */       //   7: ireturn
/*      */       // Line number table:
/*      */       //   Java source line number -> byte code offset
/*      */       //   #128	-> 0
/*      */       // Local variable table:
/*      */       //   start	length	slot	name	descriptor
/*      */       //   0	8	0	this	Lcom/atlas/common/fabric/lookup/screen/views/LookupVaultView$VaultItem;
/*  128 */       //   0	8	1	o	Ljava/lang/Object; } public String name() { return this.name; } public String id() { return this.id; } public int count() { return this.count; } public String section() { return this.section; } public String extra() { return this.extra; } public class_1799 stack() { return this.stack; } } private static final class StorageVaultEntry extends Record { private final int index; private final String id; private final String name; private final int itemCount;
/*  129 */     private StorageVaultEntry(int index, String id, String name, int itemCount) { this.index = index; this.id = id; this.name = name; this.itemCount = itemCount; } public final String toString() { // Byte code:
/*      */       //   0: aload_0
/*      */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/lookup/screen/views/LookupVaultView$StorageVaultEntry;)Ljava/lang/String;
/*      */       //   6: areturn
/*      */       // Line number table:
/*      */       //   Java source line number -> byte code offset
/*      */       //   #129	-> 0
/*      */       // Local variable table:
/*      */       //   start	length	slot	name	descriptor
/*      */       //   0	7	0	this	Lcom/atlas/common/fabric/lookup/screen/views/LookupVaultView$StorageVaultEntry; } public final int hashCode() { // Byte code:
/*      */       //   0: aload_0
/*      */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/lookup/screen/views/LookupVaultView$StorageVaultEntry;)I
/*      */       //   6: ireturn
/*      */       // Line number table:
/*      */       //   Java source line number -> byte code offset
/*      */       //   #129	-> 0
/*      */       // Local variable table:
/*      */       //   start	length	slot	name	descriptor
/*      */       //   0	7	0	this	Lcom/atlas/common/fabric/lookup/screen/views/LookupVaultView$StorageVaultEntry; } public final boolean equals(Object o) { // Byte code:
/*      */       //   0: aload_0
/*      */       //   1: aload_1
/*      */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/lookup/screen/views/LookupVaultView$StorageVaultEntry;Ljava/lang/Object;)Z
/*      */       //   7: ireturn
/*      */       // Line number table:
/*      */       //   Java source line number -> byte code offset
/*      */       //   #129	-> 0
/*      */       // Local variable table:
/*      */       //   start	length	slot	name	descriptor
/*      */       //   0	8	0	this	Lcom/atlas/common/fabric/lookup/screen/views/LookupVaultView$StorageVaultEntry;
/*  129 */       //   0	8	1	o	Ljava/lang/Object; } public int index() { return this.index; } public String id() { return this.id; } public String name() { return this.name; } public int itemCount() { return this.itemCount; }
/*      */      }
/*      */ 
/*      */ 
/*      */   
/*      */   public void init(int x, int y, int width, int height) {
/*  135 */     this.x = x;
/*  136 */     this.y = y;
/*  137 */     this.width = width;
/*  138 */     this.height = height;
/*      */     
/*  140 */     LookupClientData data = LookupClientData.getInstance();
/*  141 */     data.removeListener(this.dataListener);
/*  142 */     data.addListener(this.dataListener);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void onDataUpdated() {
/*  148 */     this.dataParsed = false;
/*  149 */     this.storageListParsed = false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void requestVaultData(String vaultType) {
/*  157 */     this.dataRequested = true;
/*  158 */     this.dataParsed = true;
/*  159 */     this.allItems.clear();
/*  160 */     this.filteredItems.clear();
/*      */ 
/*      */     
/*  163 */     LookupClientData.getInstance().clearVaultCaches();
/*  164 */     String uuid = LookupClientData.getInstance().getTargetUuid();
/*  165 */     LookupNetwork.requestLookupData("vault", this.currentPage, (uuid != null) ? uuid : "");
/*  166 */     this.lastRefreshRequestAt = System.currentTimeMillis();
/*      */   }
/*      */   
/*      */   private void requestStorageList() {
/*  170 */     this.storageListParsed = false;
/*  171 */     this.storageVaults.clear();
/*  172 */     String uuid = LookupClientData.getInstance().getTargetUuid();
/*  173 */     LookupNetwork.requestLookupData("vault_storage", 0, (uuid != null) ? uuid : "");
/*  174 */     this.lastRefreshRequestAt = System.currentTimeMillis();
/*      */   }
/*      */   
/*      */   private void requestStorageItems(String vaultId) {
/*  178 */     this.dataParsed = true;
/*  179 */     this.allItems.clear();
/*  180 */     this.filteredItems.clear();
/*  181 */     LookupClientData.getInstance().clearVaultStorageItems();
/*  182 */     String uuid = LookupClientData.getInstance().getTargetUuid();
/*      */     
/*  184 */     LookupNetwork.requestLookupData("vault_storage_items", this.currentPage, vaultId + ":" + vaultId);
/*  185 */     this.lastRefreshRequestAt = System.currentTimeMillis();
/*      */   }
/*      */   
/*      */   private void parseVaultData() {
/*  189 */     this.allItems.clear();
/*  190 */     this.vaultTypes.clear();
/*  191 */     LookupClientData data = LookupClientData.getInstance();
/*  192 */     String json = data.getVaultJson();
/*  193 */     this.totalItems = data.getVaultTotal();
/*  194 */     this.currentPage = data.getVaultPage();
/*      */     
/*  196 */     if (json == null || json.isEmpty()) {
/*  197 */       this.dataParsed = true;
/*  198 */       applyFilter();
/*      */       
/*      */       return;
/*      */     } 
/*      */     try {
/*  203 */       JsonElement root = JsonParser.parseString(json);
/*  204 */       JsonArray arr = root.isJsonArray() ? root.getAsJsonArray() : new JsonArray();
/*  205 */       Map<String, Boolean> typesSeen = new HashMap<>();
/*  206 */       for (int i = 0; i < arr.size(); i++) {
/*  207 */         JsonObject item = arr.get(i).getAsJsonObject();
/*  208 */         int slot = item.has("slot") ? item.get("slot").getAsInt() : i;
/*  209 */         String name = "Unknown";
/*  210 */         String id = null;
/*  211 */         if (item.has("name")) { name = item.get("name").getAsString(); }
/*  212 */         else if (item.has("id"))
/*  213 */         { id = item.get("id").getAsString();
/*  214 */           name = prettifyName(id); }
/*      */         
/*  216 */         if (item.has("id")) id = item.get("id").getAsString(); 
/*  217 */         if (item.has("displayName")) name = item.get("displayName").getAsString(); 
/*  218 */         int count = item.has("count") ? item.get("count").getAsInt() : 1;
/*  219 */         String section = item.has("section") ? item.get("section").getAsString() : "";
/*  220 */         String extra = "";
/*  221 */         if (item.has("enchantments")) extra = item.get("enchantments").getAsString(); 
/*  222 */         if (item.has("nbt")) extra = item.get("nbt").getAsString();
/*      */         
/*  224 */         class_1799 stack = class_1799.field_8037;
/*  225 */         if (item.has("stackJson")) {
/*  226 */           stack = deserializeStack(item.get("stackJson").getAsString());
/*      */         }
/*      */         
/*  229 */         this.allItems.add(new VaultItem(slot, name, id, count, section, extra, stack));
/*      */         
/*  231 */         if (!section.isEmpty() && !typesSeen.containsKey(section)) {
/*  232 */           typesSeen.put(section, Boolean.valueOf(true));
/*  233 */           this.vaultTypes.add(section);
/*      */         } 
/*      */       } 
/*  236 */     } catch (Exception exception) {}
/*      */     
/*  238 */     this.dataParsed = true;
/*  239 */     applyFilter();
/*      */   }
/*      */   
/*      */   private void parseStorageListData() {
/*  243 */     this.storageVaults.clear();
/*  244 */     LookupClientData data = LookupClientData.getInstance();
/*  245 */     String json = data.getVaultStorageListJson();
/*      */     
/*  247 */     if (json == null || json.isEmpty()) {
/*  248 */       this.storageListParsed = true;
/*      */       
/*      */       return;
/*      */     } 
/*      */     try {
/*  253 */       JsonArray arr = JsonParser.parseString(json).getAsJsonArray();
/*  254 */       for (int i = 0; i < arr.size(); i++) {
/*  255 */         JsonObject obj = arr.get(i).getAsJsonObject();
/*  256 */         int index = obj.has("index") ? obj.get("index").getAsInt() : i;
/*  257 */         String id = obj.has("id") ? obj.get("id").getAsString() : ("vault:" + i);
/*  258 */         String name = obj.has("name") ? obj.get("name").getAsString() : ("Vault " + i);
/*  259 */         int itemCount = obj.has("itemCount") ? obj.get("itemCount").getAsInt() : 0;
/*  260 */         this.storageVaults.add(new StorageVaultEntry(index, id, name, itemCount));
/*      */       } 
/*  262 */     } catch (Exception exception) {}
/*      */     
/*  264 */     this.storageListParsed = true;
/*      */     
/*  266 */     this.storageSlotX = new int[this.storageVaults.size()];
/*  267 */     this.storageSlotY = new int[this.storageVaults.size()];
/*      */   }
/*      */   
/*      */   private void parseStorageItemsData() {
/*  271 */     this.allItems.clear();
/*  272 */     this.filteredItems.clear();
/*  273 */     LookupClientData data = LookupClientData.getInstance();
/*  274 */     String json = data.getVaultStorageItemsJson();
/*      */     
/*  276 */     if (json == null || json.isEmpty()) {
/*  277 */       this.dataParsed = true;
/*      */       
/*      */       return;
/*      */     } 
/*      */     try {
/*  282 */       JsonElement root = JsonParser.parseString(json);
/*  283 */       JsonArray arr = root.isJsonArray() ? root.getAsJsonArray() : new JsonArray();
/*  284 */       for (int i = 0; i < arr.size(); i++) {
/*  285 */         JsonObject item = arr.get(i).getAsJsonObject();
/*  286 */         int slot = item.has("slot") ? item.get("slot").getAsInt() : i;
/*  287 */         String name = "Unknown";
/*  288 */         String id = null;
/*  289 */         if (item.has("displayName")) { name = item.get("displayName").getAsString(); }
/*  290 */         else if (item.has("name")) { name = item.get("name").getAsString(); }
/*  291 */         else if (item.has("id")) { id = item.get("id").getAsString(); name = prettifyName(id); }
/*  292 */          if (item.has("id")) id = item.get("id").getAsString(); 
/*  293 */         int count = item.has("count") ? item.get("count").getAsInt() : 1;
/*  294 */         String section = item.has("section") ? item.get("section").getAsString() : "";
/*      */         
/*  296 */         class_1799 stack = class_1799.field_8037;
/*  297 */         if (item.has("stackJson")) {
/*  298 */           stack = deserializeStack(item.get("stackJson").getAsString());
/*      */         }
/*  300 */         this.allItems.add(new VaultItem(slot, name, id, count, section, "", stack));
/*      */       } 
/*  302 */     } catch (Exception exception) {}
/*      */     
/*  304 */     this.filteredItems.addAll(this.allItems);
/*  305 */     this.dataParsed = true;
/*      */   }
/*      */   
/*      */   private void applyFilter() {
/*  309 */     this.filteredItems.clear();
/*  310 */     String filterType = (this.selectedVaultType != null) ? this.selectedVaultType : this.activeType;
/*  311 */     if (filterType == null || filterType.equals("storage_list") || filterType.equals("storage_items")) {
/*  312 */       this.filteredItems.addAll(this.allItems);
/*      */     } else {
/*  314 */       for (VaultItem item : this.allItems) {
/*  315 */         if (filterType.equalsIgnoreCase(item.section())) {
/*  316 */           this.filteredItems.add(item);
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private String activeVaultId() {
/*  324 */     if ("storage_items".equals(this.selectedVaultType)) return this.selectedStorageVaultId; 
/*  325 */     return this.selectedVaultType;
/*      */   }
/*      */ 
/*      */   
/*      */   private class_1799 getVaultStack(int slot) {
/*  330 */     for (VaultItem item : this.filteredItems) {
/*  331 */       if (item.slot() == slot) {
/*  332 */         return (item.stack() == null) ? class_1799.field_8037 : item.stack();
/*      */       }
/*      */     } 
/*  335 */     return class_1799.field_8037;
/*      */   }
/*      */ 
/*      */   
/*      */   private class_1799 getStaffStack(int slot) {
/*  340 */     class_310 mc = class_310.method_1551();
/*  341 */     if (mc == null || mc.field_1724 == null) return class_1799.field_8037; 
/*  342 */     class_1661 inv = mc.field_1724.method_31548();
/*  343 */     if (slot >= 0 && slot <= 40) {
/*  344 */       return inv.method_5438(slot);
/*      */     }
/*  346 */     return class_1799.field_8037;
/*      */   }
/*      */   
/*      */   private class_1799 getStackFor(String side, int slot) {
/*  350 */     if ("vault".equals(side)) return getVaultStack(slot); 
/*  351 */     if ("staff".equals(side)) return getStaffStack(slot); 
/*  352 */     return class_1799.field_8037;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void render(class_332 ctx, int mouseX, int mouseY, float delta, class_327 textRenderer) {
/*  359 */     maybeRefreshData();
/*      */     
/*  361 */     if (this.selectedVaultType == null) {
/*  362 */       renderVaultSelector(ctx, mouseX, mouseY, textRenderer);
/*  363 */     } else if ("storage_list".equals(this.selectedVaultType)) {
/*  364 */       if (!this.storageListParsed) parseStorageListData(); 
/*  365 */       renderStorageList(ctx, mouseX, mouseY, textRenderer);
/*  366 */     } else if ("storage_items".equals(this.selectedVaultType)) {
/*  367 */       if (!this.dataParsed) parseStorageItemsData(); 
/*  368 */       renderVaultItems(ctx, mouseX, mouseY, delta, textRenderer);
/*      */     } else {
/*  370 */       if (!this.dataParsed) parseVaultData(); 
/*  371 */       renderVaultItems(ctx, mouseX, mouseY, delta, textRenderer);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void renderVaultSelector(class_332 ctx, int mouseX, int mouseY, class_327 textRenderer) {
/*  378 */     String title = "Select Vault";
/*  379 */     int titleW = textRenderer.method_1727(title);
/*  380 */     ctx.method_51433(textRenderer, title, this.x + (this.width - titleW) / 2, this.y + 10, -1511169, true);
/*      */     
/*  382 */     int totalCardsW = VAULT_TYPES.length * 110 + (VAULT_TYPES.length - 1) * 12;
/*  383 */     int startX = this.x + (this.width - totalCardsW) / 2;
/*  384 */     int cardY = this.y + (this.height - 60) / 2 - 8;
/*      */     
/*  386 */     for (int i = 0; i < VAULT_TYPES.length; i++) {
/*  387 */       int cardX = startX + i * 122;
/*  388 */       this.selectorCardX[i] = cardX;
/*  389 */       this.selectorCardY[i] = cardY;
/*      */       
/*  391 */       boolean hov = (mouseX >= cardX && mouseX < cardX + 110 && mouseY >= cardY && mouseY < cardY + 60);
/*      */ 
/*      */       
/*  394 */       int bgColor = hov ? -14274480 : -14801866;
/*  395 */       int borderColor = hov ? -10777105 : -14012352;
/*      */       
/*  397 */       ctx.method_25294(cardX, cardY, cardX + 110, cardY + 60, bgColor);
/*  398 */       ctx.method_49601(cardX, cardY, 110, 60, borderColor);
/*      */       
/*  400 */       if (hov) {
/*  401 */         ctx.method_25294(cardX + 1, cardY + 60 - 3, cardX + 110 - 1, cardY + 60 - 1, -10777105);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  406 */       int labelColor = (i == VAULT_TYPES.length - 1) ? (hov ? -1 : -10496) : (hov ? -1 : -1511169);
/*      */       
/*  408 */       String label = VAULT_LABELS[i];
/*  409 */       int lw = textRenderer.method_1727(label);
/*  410 */       int ly = cardY + 25;
/*  411 */       ctx.method_51433(textRenderer, label, cardX + (110 - lw) / 2, ly, labelColor, hov);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void renderStorageList(class_332 ctx, int mouseX, int mouseY, class_327 textRenderer) {
/*  418 */     renderBackButton(ctx, mouseX, mouseY, textRenderer);
/*      */     
/*  420 */     String heading = "Player Vaults";
/*  421 */     int headingW = textRenderer.method_1727(heading);
/*  422 */     ctx.method_51433(textRenderer, heading, this.x + (this.width - headingW) / 2, this.y + 6, -10496, false);
/*      */     
/*  424 */     int contentY = this.y + 22 + 4;
/*      */     
/*  426 */     if (!this.storageListParsed || this.storageVaults.isEmpty()) {
/*  427 */       String msg = !this.storageListParsed ? "Loading..." : "No player vaults found";
/*  428 */       int tw = textRenderer.method_1727(msg);
/*  429 */       ctx.method_51433(textRenderer, msg, this.x + (this.width - tw) / 2, this.y + this.height / 2, -9930592, false);
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*  434 */     int storageTotalW = 418;
/*  435 */     int gridX = this.x + (this.width - storageTotalW) / 2;
/*  436 */     int col = 0;
/*  437 */     int rowY = contentY;
/*      */     
/*  439 */     for (int i = 0; i < this.storageVaults.size(); i++) {
/*  440 */       StorageVaultEntry vault = this.storageVaults.get(i);
/*  441 */       int slotX = gridX + col * 106;
/*      */       
/*  443 */       if (rowY + 32 > this.y + this.height - 22)
/*      */         break; 
/*  445 */       this.storageSlotX[i] = slotX;
/*  446 */       this.storageSlotY[i] = rowY;
/*      */       
/*  448 */       boolean hov = (mouseX >= slotX && mouseX < slotX + 100 && mouseY >= rowY && mouseY < rowY + 32);
/*      */ 
/*      */       
/*  451 */       int bg = hov ? -14274480 : -14801866;
/*  452 */       int border = hov ? -10777105 : -14012352;
/*  453 */       ctx.method_25294(slotX, rowY, slotX + 100, rowY + 32, bg);
/*  454 */       ctx.method_49601(slotX, rowY, 100, 32, border);
/*      */       
/*  456 */       int dotColor = (vault.itemCount() > 0) ? -12339839 : LookupColors.withAlpha(-9930592, 80);
/*  457 */       ctx.method_25294(slotX + 100 - 8, rowY + 4, slotX + 100 - 4, rowY + 8, dotColor);
/*      */       
/*  459 */       String nameStr = vault.name();
/*  460 */       int nameW = textRenderer.method_1727(nameStr);
/*  461 */       if (nameW > 90) {
/*  462 */         nameStr = truncateText(textRenderer, nameStr, 90);
/*  463 */         nameW = textRenderer.method_1727(nameStr);
/*      */       } 
/*  465 */       ctx.method_51433(textRenderer, nameStr, slotX + (100 - nameW) / 2, rowY + 6, 
/*  466 */           hov ? -1 : -1511169, false);
/*      */       
/*  468 */       String countStr = "" + vault.itemCount() + " items";
/*  469 */       int countW = textRenderer.method_1727(countStr);
/*  470 */       ctx.method_51433(textRenderer, countStr, slotX + (100 - countW) / 2, rowY + 17, 
/*  471 */           hov ? -10777105 : -9930592, false);
/*      */       
/*  473 */       col++;
/*  474 */       if (col >= 4) {
/*  475 */         col = 0;
/*  476 */         rowY += 38;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void renderVaultItems(class_332 ctx, int mouseX, int mouseY, float delta, class_327 textRenderer) {
/*      */     String heading;
/*  484 */     renderBackButton(ctx, mouseX, mouseY, textRenderer);
/*      */ 
/*      */     
/*  487 */     if ("storage_items".equals(this.selectedVaultType)) {
/*  488 */       heading = (this.selectedStorageVaultName != null) ? this.selectedStorageVaultName : "Player Vault";
/*      */     } else {
/*  490 */       heading = capitalize(this.selectedVaultType) + " Vault";
/*      */     } 
/*      */     
/*  493 */     int headingW = textRenderer.method_1727(heading);
/*  494 */     ctx.method_51433(textRenderer, heading, this.x + (this.width - headingW) / 2, this.y + 6, -10777105, false);
/*      */     
/*  496 */     this.showTooltip = false;
/*  497 */     this.slotRects.clear();
/*      */     
/*  499 */     boolean canEdit = resolveCanEdit();
/*      */ 
/*      */     
/*  502 */     int contentY = this.y + 22 + 4;
/*  503 */     int vaultGridTotalW = 214;
/*  504 */     int gridX = this.x + (this.width - vaultGridTotalW) / 2;
/*      */ 
/*      */     
/*  507 */     Map<Integer, VaultItem> vaultItems = new HashMap<>();
/*  508 */     for (VaultItem item : this.filteredItems) {
/*  509 */       vaultItems.put(Integer.valueOf(item.slot()), item);
/*      */     }
/*      */     
/*  512 */     if (!this.dataParsed) {
/*  513 */       String msg = "Loading...";
/*  514 */       int tw = textRenderer.method_1727(msg);
/*  515 */       ctx.method_51433(textRenderer, msg, this.x + (this.width - tw) / 2, contentY + 12, -9930592, false);
/*      */     } 
/*      */ 
/*      */     
/*  519 */     int vaultGridRows = 6;
/*  520 */     int vaultTotalSlots = 9 * vaultGridRows;
/*  521 */     for (int slotIndex = 0; slotIndex < vaultTotalSlots; slotIndex++) {
/*  522 */       int col = slotIndex % 9;
/*  523 */       int row = slotIndex / 9;
/*  524 */       int sx = gridX + col * 24;
/*  525 */       int sy = contentY + row * 24;
/*      */       
/*  527 */       VaultItem item = vaultItems.get(Integer.valueOf(slotIndex));
/*  528 */       if (item != null) {
/*  529 */         renderVaultSlot(ctx, textRenderer, sx, sy, item, mouseX, mouseY);
/*      */       } else {
/*      */         
/*  532 */         renderEmptyVaultSlot(ctx, textRenderer, sx, sy, slotIndex, mouseX, mouseY);
/*      */       } 
/*      */     } 
/*      */     
/*  536 */     int vaultGridBottomY = contentY + vaultGridRows * 24;
/*      */ 
/*      */     
/*  539 */     if (canEdit) {
/*  540 */       int sepY = vaultGridBottomY + 4;
/*  541 */       ctx.method_25294(this.x + 6, sepY, this.x + this.width - 6, sepY + 1, -14012352);
/*      */       
/*  543 */       int staffLabelY = sepY + 6;
/*  544 */       String staffLabel = "§lYour Inventory";
/*  545 */       int staffLabelW = textRenderer.method_1727(staffLabel);
/*  546 */       ctx.method_51433(textRenderer, staffLabel, gridX + (vaultGridTotalW - staffLabelW) / 2, staffLabelY, -10777105, false);
/*      */       
/*  548 */       int staffTop = staffLabelY + 12;
/*      */ 
/*      */       
/*  551 */       for (int row = 0; row < 3; row++) {
/*  552 */         for (int i = 0; i < 9; i++) {
/*  553 */           int slot = 9 + row * 9 + i;
/*  554 */           int sx = gridX + i * 24;
/*  555 */           int sy = staffTop + row * 24;
/*  556 */           renderStaffSlot(ctx, textRenderer, slot, sx, sy, mouseX, mouseY);
/*      */         } 
/*      */       } 
/*      */       
/*  560 */       int hotbarY = staffTop + 72 + 4;
/*  561 */       for (int col = 0; col < 9; col++) {
/*  562 */         int sx = gridX + col * 24;
/*  563 */         renderStaffSlot(ctx, textRenderer, col, sx, hotbarY, mouseX, mouseY);
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  568 */     if (this.showTooltip && this.tooltipStack != null && !this.tooltipStack.method_7960() && this.cursorStack.method_7960()) {
/*  569 */       class_4587 matrices = ctx.method_51448();
/*  570 */       matrices.method_22903();
/*  571 */       matrices.method_46416(0.0F, 0.0F, 500.0F);
/*  572 */       ctx.method_51446(textRenderer, this.tooltipStack, this.tooltipX, this.tooltipY);
/*  573 */       matrices.method_22909();
/*      */     } 
/*      */ 
/*      */     
/*  577 */     if (!this.cursorStack.method_7960()) {
/*  578 */       class_4587 matrices = ctx.method_51448();
/*  579 */       matrices.method_22903();
/*  580 */       matrices.method_46416(0.0F, 0.0F, 600.0F);
/*  581 */       int cx = mouseX - 8;
/*  582 */       int cy = mouseY - 8;
/*  583 */       ctx.method_51427(this.cursorStack, cx, cy);
/*  584 */       int count = this.cursorStack.method_7947();
/*  585 */       if (count > 1) {
/*  586 */         String countStr = String.valueOf(count);
/*  587 */         int countW = textRenderer.method_1727(countStr);
/*  588 */         ctx.method_51433(textRenderer, countStr, cx + 16 - countW, cy + 8, -1, true);
/*      */       } 
/*  590 */       matrices.method_22909();
/*      */     } 
/*      */     
/*  593 */     renderFooter(ctx, mouseX, mouseY, textRenderer);
/*      */   }
/*      */   
/*      */   private void renderBackButton(class_332 ctx, int mouseX, int mouseY, class_327 textRenderer) {
/*  597 */     String backLabel = "← Back";
/*  598 */     this.backBtnW = textRenderer.method_1727(backLabel) + 10;
/*  599 */     this.backBtnH = 14;
/*  600 */     this.backBtnX = this.x + 6;
/*  601 */     this.backBtnY = this.y + 5;
/*  602 */     boolean backHov = (mouseX >= this.backBtnX && mouseX < this.backBtnX + this.backBtnW && mouseY >= this.backBtnY && mouseY < this.backBtnY + this.backBtnH);
/*      */     
/*  604 */     ctx.method_25294(this.backBtnX, this.backBtnY, this.backBtnX + this.backBtnW, this.backBtnY + this.backBtnH, 
/*  605 */         backHov ? -14274480 : -14801866);
/*  606 */     ctx.method_49601(this.backBtnX, this.backBtnY, this.backBtnW, this.backBtnH, backHov ? -10777105 : -14012352);
/*  607 */     ctx.method_51433(textRenderer, backLabel, this.backBtnX + 5, this.backBtnY + 3, 
/*  608 */         backHov ? -1 : -9930592, false);
/*      */   }
/*      */ 
/*      */   
/*      */   private void renderVaultSlot(class_332 ctx, class_327 textRenderer, int sx, int sy, VaultItem item, int mouseX, int mouseY) {
/*  613 */     this.slotRects.add(new SlotRect("vault", item.slot(), sx, sy));
/*      */ 
/*      */     
/*  616 */     boolean isCursorSource = (!this.cursorStack.method_7960() && "vault".equals(this.cursorFromSide) && item.slot() == this.cursorFromSlot);
/*      */     
/*  618 */     ctx.method_25294(sx, sy, sx + 22, sy + 22, -14801866);
/*  619 */     ctx.method_49601(sx, sy, 22, 22, -14012352);
/*      */     
/*  621 */     boolean hovered = (mouseX >= sx && mouseX < sx + 22 && mouseY >= sy && mouseY < sy + 22);
/*      */ 
/*      */     
/*  624 */     class_1799 stack = item.stack();
/*      */ 
/*      */     
/*  627 */     int visualCount = (stack != null) ? stack.method_7947() : 0;
/*  628 */     if (isCursorSource) visualCount -= this.cursorCount; 
/*  629 */     boolean showItem = (stack != null && !stack.method_7960() && visualCount > 0);
/*      */     
/*  631 */     if (showItem) {
/*  632 */       int itemX = sx + 3;
/*  633 */       int itemY = sy + 3;
/*  634 */       ctx.method_51427(stack, itemX, itemY);
/*      */       
/*  636 */       if (visualCount > 1) {
/*  637 */         ctx.method_51448().method_22903();
/*  638 */         ctx.method_51448().method_46416(0.0F, 0.0F, 200.0F);
/*  639 */         String countStr = String.valueOf(visualCount);
/*  640 */         int countW = textRenderer.method_1727(countStr);
/*  641 */         ctx.method_51433(textRenderer, countStr, sx + 22 - countW - 1, sy + 22 - 9, -1, true);
/*  642 */         ctx.method_51448().method_22909();
/*      */       } 
/*      */       
/*  645 */       if (hovered && this.cursorStack.method_7960()) {
/*  646 */         ctx.method_25294(sx, sy, sx + 22, sy + 22, LookupColors.withAlpha(-10777105, 40));
/*  647 */         this.showTooltip = true;
/*  648 */         this.tooltipStack = stack;
/*  649 */         this.tooltipX = mouseX;
/*  650 */         this.tooltipY = mouseY;
/*      */       } 
/*  652 */     } else if (isCursorSource) {
/*  653 */       ctx.method_25294(sx + 1, sy + 1, sx + 22 - 1, sy + 22 - 1, 
/*  654 */           LookupColors.withAlpha(-12958368, 40));
/*  655 */     } else if (hovered) {
/*  656 */       ctx.method_25294(sx, sy, sx + 22, sy + 22, 
/*  657 */           LookupColors.withAlpha(-12958368, 30));
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void renderEmptyVaultSlot(class_332 ctx, class_327 textRenderer, int sx, int sy, int slotIndex, int mouseX, int mouseY) {
/*  663 */     this.slotRects.add(new SlotRect("vault", slotIndex, sx, sy));
/*      */ 
/*      */     
/*  666 */     boolean isCursorSource = (!this.cursorStack.method_7960() && "vault".equals(this.cursorFromSide) && slotIndex == this.cursorFromSlot);
/*      */     
/*  668 */     ctx.method_25294(sx, sy, sx + 22, sy + 22, -14801866);
/*  669 */     ctx.method_49601(sx, sy, 22, 22, -14012352);
/*      */     
/*  671 */     boolean hovered = (mouseX >= sx && mouseX < sx + 22 && mouseY >= sy && mouseY < sy + 22);
/*      */ 
/*      */     
/*  674 */     if (isCursorSource) {
/*  675 */       ctx.method_25294(sx + 1, sy + 1, sx + 22 - 1, sy + 22 - 1, 
/*  676 */           LookupColors.withAlpha(-12958368, 40));
/*  677 */     } else if (hovered) {
/*  678 */       ctx.method_25294(sx, sy, sx + 22, sy + 22, 
/*  679 */           LookupColors.withAlpha(-12958368, 30));
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void renderStaffSlot(class_332 ctx, class_327 textRenderer, int slot, int sx, int sy, int mouseX, int mouseY) {
/*  685 */     this.slotRects.add(new SlotRect("staff", slot, sx, sy));
/*      */ 
/*      */     
/*  688 */     boolean isCursorSource = (!this.cursorStack.method_7960() && "staff".equals(this.cursorFromSide) && slot == this.cursorFromSlot);
/*      */     
/*  690 */     ctx.method_25294(sx, sy, sx + 22, sy + 22, -14801866);
/*  691 */     ctx.method_49601(sx, sy, 22, 22, -14012352);
/*      */     
/*  693 */     boolean hovered = (mouseX >= sx && mouseX < sx + 22 && mouseY >= sy && mouseY < sy + 22);
/*      */ 
/*      */     
/*  696 */     class_1799 stack = getStaffStack(slot);
/*  697 */     int visualCount = (stack != null) ? stack.method_7947() : 0;
/*  698 */     if (isCursorSource) visualCount -= this.cursorCount; 
/*  699 */     boolean showItem = (stack != null && !stack.method_7960() && visualCount > 0);
/*      */     
/*  701 */     if (showItem) {
/*  702 */       int itemX = sx + 3;
/*  703 */       int itemY = sy + 3;
/*  704 */       ctx.method_51427(stack, itemX, itemY);
/*      */       
/*  706 */       if (visualCount > 1) {
/*  707 */         ctx.method_51448().method_22903();
/*  708 */         ctx.method_51448().method_46416(0.0F, 0.0F, 200.0F);
/*  709 */         String countStr = String.valueOf(visualCount);
/*  710 */         int countW = textRenderer.method_1727(countStr);
/*  711 */         ctx.method_51433(textRenderer, countStr, sx + 22 - countW - 1, sy + 22 - 9, -1, true);
/*  712 */         ctx.method_51448().method_22909();
/*      */       } 
/*      */       
/*  715 */       if (hovered && this.cursorStack.method_7960()) {
/*  716 */         ctx.method_25294(sx, sy, sx + 22, sy + 22, LookupColors.withAlpha(-10777105, 40));
/*  717 */         this.showTooltip = true;
/*  718 */         this.tooltipStack = stack;
/*  719 */         this.tooltipX = mouseX;
/*  720 */         this.tooltipY = mouseY;
/*      */       } 
/*  722 */     } else if (isCursorSource) {
/*  723 */       ctx.method_25294(sx + 1, sy + 1, sx + 22 - 1, sy + 22 - 1, 
/*  724 */           LookupColors.withAlpha(-12958368, 40));
/*  725 */     } else if (hovered) {
/*  726 */       ctx.method_25294(sx, sy, sx + 22, sy + 22, 
/*  727 */           LookupColors.withAlpha(-12958368, 30));
/*      */     } 
/*      */   }
/*      */   
/*      */   private void renderFooter(class_332 ctx, int mouseX, int mouseY, class_327 textRenderer) {
/*  732 */     int footerY = this.y + this.height - 22;
/*      */     
/*  734 */     if (this.currentPage > 0) {
/*  735 */       String prev = "« Prev";
/*  736 */       int prevW = textRenderer.method_1727(prev);
/*  737 */       int prevX = this.x + 6;
/*  738 */       boolean hoverPrev = (mouseX >= prevX && mouseX < prevX + prevW && mouseY >= footerY && mouseY < footerY + 22);
/*  739 */       ctx.method_51433(textRenderer, prev, prevX, footerY + 6, hoverPrev ? -10777105 : -9930592, false);
/*      */     } 
/*      */     
/*  742 */     if (this.totalItems > 0 && !this.allItems.isEmpty()) {
/*  743 */       String next = "Next »";
/*  744 */       int nextW = textRenderer.method_1727(next);
/*  745 */       int nextX = this.x + this.width - nextW - 6;
/*  746 */       boolean hoverNext = (mouseX >= nextX && mouseX < nextX + nextW && mouseY >= footerY && mouseY < footerY + 22);
/*  747 */       ctx.method_51433(textRenderer, next, nextX, footerY + 6, hoverNext ? -10777105 : -9930592, false);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
/*  755 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
/*  760 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean mouseReleased(double mouseX, double mouseY, int button) {
/*  765 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean mouseClicked(double mouseX, double mouseY, int button) {
/*  773 */     if (button != 0 && button != 1) return false; 
/*  774 */     if (button == 1) {
/*  775 */       return handleRightClick(mouseX, mouseY);
/*      */     }
/*      */ 
/*      */     
/*  779 */     if (this.selectedVaultType == null) {
/*  780 */       for (int i = 0; i < VAULT_TYPES.length; i++) {
/*  781 */         int cardX = this.selectorCardX[i];
/*  782 */         int cardY = this.selectorCardY[i];
/*  783 */         if (mouseX >= cardX && mouseX < (cardX + 110) && mouseY >= cardY && mouseY < (cardY + 60)) {
/*  784 */           this.selectedVaultType = VAULT_TYPES[i];
/*  785 */           this.activeType = VAULT_TYPES[i];
/*  786 */           this.currentPage = 0;
/*  787 */           if ("storage_list".equals(this.selectedVaultType)) {
/*  788 */             requestStorageList();
/*      */           } else {
/*  790 */             requestVaultData(this.selectedVaultType);
/*      */           } 
/*  792 */           return true;
/*      */         } 
/*      */       } 
/*  795 */       return false;
/*      */     } 
/*      */ 
/*      */     
/*  799 */     if ("storage_list".equals(this.selectedVaultType)) {
/*      */       
/*  801 */       if (mouseX >= this.backBtnX && mouseX < (this.backBtnX + this.backBtnW) && mouseY >= this.backBtnY && mouseY < (this.backBtnY + this.backBtnH)) {
/*      */         
/*  803 */         this.selectedVaultType = null;
/*  804 */         this.activeType = null;
/*  805 */         return true;
/*      */       } 
/*      */       
/*  808 */       if (this.storageSlotX != null && this.storageSlotY != null) {
/*  809 */         for (int i = 0; i < this.storageVaults.size() && i < this.storageSlotX.length; i++) {
/*  810 */           int sx = this.storageSlotX[i];
/*  811 */           int sy = this.storageSlotY[i];
/*  812 */           if ((sx != 0 || sy != 0) && 
/*  813 */             mouseX >= sx && mouseX < (sx + 100) && mouseY >= sy && mouseY < (sy + 32)) {
/*      */             
/*  815 */             StorageVaultEntry vault = this.storageVaults.get(i);
/*  816 */             this.selectedStorageVaultId = vault.id();
/*  817 */             this.selectedStorageVaultName = vault.name();
/*  818 */             this.selectedVaultType = "storage_items";
/*  819 */             this.currentPage = 0;
/*  820 */             requestStorageItems(this.selectedStorageVaultId);
/*  821 */             return true;
/*      */           } 
/*      */         } 
/*      */       }
/*  825 */       return false;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  831 */     if (mouseX >= this.backBtnX && mouseX < (this.backBtnX + this.backBtnW) && mouseY >= this.backBtnY && mouseY < (this.backBtnY + this.backBtnH)) {
/*      */       
/*  833 */       if ("storage_items".equals(this.selectedVaultType)) {
/*  834 */         this.selectedVaultType = "storage_list";
/*  835 */         this.selectedStorageVaultId = null;
/*  836 */         this.selectedStorageVaultName = null;
/*  837 */         this.dataParsed = false;
/*      */       } else {
/*  839 */         this.selectedVaultType = null;
/*  840 */         this.activeType = null;
/*  841 */         this.dataParsed = false;
/*      */       } 
/*  843 */       clearCursor();
/*  844 */       return true;
/*      */     } 
/*      */ 
/*      */     
/*  848 */     int footerY = this.y + this.height - 22;
/*  849 */     if (mouseY >= footerY && mouseY < (footerY + 22)) {
/*  850 */       if (this.currentPage > 0 && mouseX >= (this.x + 6) && mouseX < (this.x + 60)) {
/*  851 */         this.currentPage--;
/*  852 */         if ("storage_items".equals(this.selectedVaultType)) {
/*  853 */           requestStorageItems(this.selectedStorageVaultId);
/*      */         } else {
/*  855 */           requestVaultData(this.selectedVaultType);
/*      */         } 
/*  857 */         return true;
/*      */       } 
/*  859 */       if (mouseX >= (this.x + this.width - 60) && mouseX < (this.x + this.width)) {
/*  860 */         this.currentPage++;
/*  861 */         if ("storage_items".equals(this.selectedVaultType)) {
/*  862 */           requestStorageItems(this.selectedStorageVaultId);
/*      */         } else {
/*  864 */           requestVaultData(this.selectedVaultType);
/*      */         } 
/*  866 */         return true;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  871 */     boolean canEdit = resolveCanEdit();
/*  872 */     if (!canEdit) return false;
/*      */     
/*  874 */     SlotRect hit = null;
/*  875 */     for (SlotRect r : this.slotRects) {
/*  876 */       if (mouseX >= r.sx && mouseX < (r.sx + 22) && mouseY >= r.sy && mouseY < (r.sy + 22)) {
/*      */         
/*  878 */         hit = r;
/*      */         
/*      */         break;
/*      */       } 
/*      */     } 
/*  883 */     if (this.cursorStack.method_7960()) {
/*      */       
/*  885 */       if (hit == null) return false; 
/*  886 */       class_1799 stack = getStackFor(hit.side, hit.slot);
/*  887 */       if (stack == null || stack.method_7960()) return false; 
/*  888 */       this.cursorStack = stack.method_7972();
/*  889 */       this.cursorFromSide = hit.side;
/*  890 */       this.cursorFromSlot = hit.slot;
/*  891 */       this.cursorCount = stack.method_7947();
/*  892 */       return true;
/*      */     } 
/*      */ 
/*      */     
/*  896 */     if (hit == null) {
/*      */       
/*  898 */       clearCursor();
/*  899 */       return true;
/*      */     } 
/*      */ 
/*      */     
/*  903 */     if (hit.side.equals(this.cursorFromSide) && hit.slot == this.cursorFromSlot) {
/*  904 */       clearCursor();
/*  905 */       return true;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  911 */     String uuid = LookupClientData.getInstance().getTargetUuid();
/*  912 */     String vaultId = activeVaultId();
/*  913 */     if (uuid != null && vaultId != null) {
/*  914 */       class_1799 destStack = getStackFor(hit.side, hit.slot);
/*      */       
/*  916 */       boolean sameOrEmpty = (destStack == null || destStack.method_7960() || class_1799.method_31577(destStack, this.cursorStack));
/*  917 */       if (sameOrEmpty) {
/*  918 */         String filter = this.cursorFromSide + ":" + this.cursorFromSide + ":" + this.cursorFromSlot + ":" + hit.side + ":" + hit.slot + ":" + this.cursorCount + ":" + vaultId;
/*      */         
/*  920 */         LookupNetwork.requestLookupData("vault_split", this.currentPage, filter);
/*      */       } else {
/*  922 */         String filter = this.cursorFromSide + ":" + this.cursorFromSide + ":" + this.cursorFromSlot + ":" + hit.side + ":" + hit.slot + ":" + vaultId;
/*      */         
/*  924 */         LookupNetwork.requestLookupData("vault_move", this.currentPage, filter);
/*      */       } 
/*  926 */       this.lastRefreshRequestAt = 0L;
/*      */     } 
/*  928 */     clearCursor();
/*  929 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean handleRightClick(double mouseX, double mouseY) {
/*  942 */     if (this.selectedVaultType == null || "storage_list".equals(this.selectedVaultType)) return false; 
/*  943 */     if (!resolveCanEdit()) return false;
/*      */     
/*  945 */     SlotRect hit = null;
/*  946 */     for (SlotRect r : this.slotRects) {
/*  947 */       if (mouseX >= r.sx && mouseX < (r.sx + 22) && mouseY >= r.sy && mouseY < (r.sy + 22)) {
/*      */         
/*  949 */         hit = r;
/*      */         
/*      */         break;
/*      */       } 
/*      */     } 
/*  954 */     if (this.cursorStack.method_7960()) {
/*  955 */       if (hit == null) return false; 
/*  956 */       class_1799 stack = getStackFor(hit.side, hit.slot);
/*  957 */       if (stack == null || stack.method_7960()) return false;
/*      */       
/*  959 */       int half = (stack.method_7947() + 1) / 2;
/*  960 */       this.cursorStack = stack.method_7972();
/*  961 */       this.cursorStack.method_7939(half);
/*  962 */       this.cursorFromSide = hit.side;
/*  963 */       this.cursorFromSlot = hit.slot;
/*  964 */       this.cursorCount = half;
/*  965 */       return true;
/*      */     } 
/*      */     
/*  968 */     if (hit == null) return false; 
/*  969 */     if (hit.side.equals(this.cursorFromSide) && hit.slot == this.cursorFromSlot) {
/*  970 */       clearCursor();
/*  971 */       return true;
/*      */     } 
/*      */     
/*  974 */     class_1799 destStack = getStackFor(hit.side, hit.slot);
/*      */     
/*  976 */     boolean sameOrEmpty = (destStack == null || destStack.method_7960() || class_1799.method_31577(destStack, this.cursorStack));
/*  977 */     if (!sameOrEmpty) return true;
/*      */     
/*  979 */     String uuid = LookupClientData.getInstance().getTargetUuid();
/*  980 */     String vaultId = activeVaultId();
/*  981 */     if (uuid == null || vaultId == null) return true;
/*      */     
/*  983 */     String filter = this.cursorFromSide + ":" + this.cursorFromSide + ":" + this.cursorFromSlot + ":" + hit.side + ":1:" + hit.slot + ":" + vaultId;
/*      */     
/*  985 */     LookupNetwork.requestLookupData("vault_split", this.currentPage, filter);
/*  986 */     this.lastRefreshRequestAt = 0L;
/*      */ 
/*      */ 
/*      */     
/*  990 */     this.cursorCount--;
/*  991 */     if (this.cursorCount <= 0) {
/*  992 */       clearCursor();
/*      */     } else {
/*  994 */       this.cursorStack.method_7939(this.cursorCount);
/*      */     } 
/*  996 */     return true;
/*      */   }
/*      */   
/*      */   private void clearCursor() {
/* 1000 */     this.cursorStack = class_1799.field_8037;
/* 1001 */     this.cursorFromSide = null;
/* 1002 */     this.cursorFromSlot = -1;
/* 1003 */     this.cursorCount = 0;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean resolveCanEdit() {
/*      */     try {
/* 1010 */       String overviewJson = LookupClientData.getInstance().getOverviewJson();
/* 1011 */       if (overviewJson != null && !overviewJson.isEmpty()) {
/* 1012 */         JsonObject obj = JsonParser.parseString(overviewJson).getAsJsonObject();
/* 1013 */         return (obj.has("canEdit") && obj.get("canEdit").getAsBoolean());
/*      */       } 
/* 1015 */     } catch (Exception exception) {}
/* 1016 */     return false;
/*      */   }
/*      */   
/*      */   private void maybeRefreshData() {
/* 1020 */     if (!this.dataRequested || (this.cursorStack != null && !this.cursorStack.method_7960()))
/* 1021 */       return;  if (this.selectedVaultType == null || "storage_list".equals(this.selectedVaultType))
/*      */       return; 
/* 1023 */     long now = System.currentTimeMillis();
/* 1024 */     if (now - this.lastRefreshRequestAt < 750L)
/*      */       return; 
/* 1026 */     if ("storage_items".equals(this.selectedVaultType)) {
/* 1027 */       if (this.selectedStorageVaultId == null || this.selectedStorageVaultId.isEmpty())
/* 1028 */         return;  requestStorageItems(this.selectedStorageVaultId);
/*      */     } else {
/* 1030 */       requestVaultData(this.selectedVaultType);
/*      */     } 
/* 1032 */     this.lastRefreshRequestAt = now;
/*      */   }
/*      */   
/*      */   private static String truncateText(class_327 textRenderer, String text, int maxWidth) {
/* 1036 */     if (maxWidth <= 0) return ""; 
/* 1037 */     if (textRenderer.method_1727(text) <= maxWidth) return text; 
/* 1038 */     String ellipsis = "…";
/* 1039 */     int ellipsisW = textRenderer.method_1727(ellipsis);
/* 1040 */     if (maxWidth <= ellipsisW) return ellipsis; 
/* 1041 */     int target = maxWidth - ellipsisW;
/* 1042 */     int len = text.length();
/* 1043 */     for (; len > 0 && textRenderer.method_1727(text.substring(0, len)) > target; len--);
/* 1044 */     return text.substring(0, len) + text.substring(0, len);
/*      */   }
/*      */   
/*      */   private static String prettifyName(String id) {
/* 1048 */     if (id == null) return "Unknown"; 
/* 1049 */     int colon = id.indexOf(':');
/* 1050 */     String name = (colon >= 0) ? id.substring(colon + 1) : id;
/* 1051 */     StringBuilder sb = new StringBuilder();
/* 1052 */     for (String word : name.split("_")) {
/* 1053 */       if (!word.isEmpty()) {
/* 1054 */         if (sb.length() > 0) sb.append(' '); 
/* 1055 */         sb.append(Character.toUpperCase(word.charAt(0)));
/* 1056 */         if (word.length() > 1) sb.append(word.substring(1)); 
/*      */       } 
/*      */     } 
/* 1059 */     return sb.toString();
/*      */   }
/*      */   
/*      */   private static String capitalize(String s) {
/* 1063 */     if (s == null || s.isEmpty()) return s; 
/* 1064 */     return "" + Character.toUpperCase(s.charAt(0)) + Character.toUpperCase(s.charAt(0));
/*      */   }
/*      */   
/*      */   private static class_1799 deserializeStack(String json) {
/*      */     try {
/* 1069 */       JsonElement element = JsonParser.parseString(json);
/* 1070 */       class_6903<JsonElement> ops = class_6903.method_46632((DynamicOps)JsonOps.INSTANCE, 
/* 1071 */           (class_7225.class_7874)(class_310.method_1551()).field_1687.method_30349());
/* 1072 */       return (class_1799)((Pair)class_1799.field_24671.decode((DynamicOps)ops, element).getOrThrow()).getFirst();
/* 1073 */     } catch (Exception e) {
/* 1074 */       return class_1799.field_8037;
/*      */     } 
/*      */   }
/*      */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\lookup\screen\views\LookupVaultView.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */