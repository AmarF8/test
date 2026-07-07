/*      */ package com.atlas.common.fabric.lookup.screen.views;
/*      */ 
/*      */ import com.atlas.common.fabric.guide.screen.widgets.GuideScrollableArea;
/*      */ import com.atlas.common.fabric.lookup.data.LookupClientData;
/*      */ import com.atlas.common.fabric.lookup.network.LookupNetwork;
/*      */ import com.atlas.common.fabric.lookup.screen.LookupColors;
/*      */ import com.atlas.common.fabric.lookup.screen.widgets.StatIcon;
/*      */ import com.atlas.common.fabric.screen.PokemonRenderHelper;
/*      */ import com.cobblemon.mod.common.client.render.models.blockbench.FloatingState;
/*      */ import com.google.gson.JsonArray;
/*      */ import com.google.gson.JsonElement;
/*      */ import com.google.gson.JsonObject;
/*      */ import com.google.gson.JsonParser;
/*      */ import com.mojang.datafixers.util.Pair;
/*      */ import com.mojang.serialization.DynamicOps;
/*      */ import com.mojang.serialization.JsonOps;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import net.minecraft.class_1799;
/*      */ import net.minecraft.class_2561;
/*      */ import net.minecraft.class_2960;
/*      */ import net.minecraft.class_310;
/*      */ import net.minecraft.class_327;
/*      */ import net.minecraft.class_332;
/*      */ import net.minecraft.class_4587;
/*      */ import net.minecraft.class_5348;
/*      */ import net.minecraft.class_6903;
/*      */ import net.minecraft.class_7225;
/*      */ import org.joml.Quaternionf;
/*      */ 
/*      */ public class LookupSnapshotsView implements LookupView {
/*      */   private int x;
/*      */   private int y;
/*      */   private int width;
/*      */   private int height;
/*      */   private boolean dataRequested = false;
/*      */   private boolean dataParsed = false;
/*   42 */   private int currentPage = 0;
/*      */   
/*      */   private GuideScrollableArea scrollArea;
/*      */   private GuideScrollableArea detailScrollArea;
/*   46 */   private final Runnable dataListener = this::onDataUpdated;
/*      */   
/*   48 */   private final List<SnapshotEntry> entries = new ArrayList<>();
/*   49 */   private int totalEntries = 0;
/*      */ 
/*      */   
/*   52 */   private int expandedIndex = -1;
/*      */ 
/*      */   
/*      */   private boolean showingDetail = false;
/*      */ 
/*      */   
/*   58 */   private SnapshotEntry detailEntry = null;
/*      */ 
/*      */   
/*   61 */   private List<JsonObject> detailParty = new ArrayList<>();
/*   62 */   private List<JsonObject> detailPc = new ArrayList<>();
/*   63 */   private List<PcBoxData> detailPcBoxes = new ArrayList<>();
/*   64 */   private List<ItemRow> detailInventory = new ArrayList<>();
/*   65 */   private long detailPokeCoin = 0L;
/*   66 */   private long detailPokeGem = 0L;
/*   67 */   private long detailHonorPoint = 0L;
/*      */   
/*      */   private boolean detailLoaded = false;
/*      */   
/*   71 */   private String confirmingSection = null;
/*      */ 
/*      */   
/*   74 */   private final Map<String, FloatingState> modelStates = new HashMap<>();
/*      */ 
/*      */   
/*   77 */   private int hoveredPartyIndex = -1;
/*   78 */   private JsonObject hoveredPcPokemon = null;
/*      */   
/*      */   private int hoveredMouseX;
/*      */   private int hoveredMouseY;
/*      */   private static final int COLLAPSED_HEIGHT = 34;
/*      */   private static final int PADDING = 6;
/*      */   private static final int HEADER_HEIGHT = 22;
/*      */   private static final int FOOTER_HEIGHT = 22;
/*   86 */   private static final String[] RESTORE_SECTIONS = new String[] { "party", "pc", "inventory", "currency" }; private static final int BUTTON_HEIGHT = 16; private static final int BUTTON_GAP = 4; private static final int SECTION_HEADER_H = 18; private static final int POKEMON_ROW_H = 14; private static final int ITEM_ROW_H = 13;
/*      */   private static final int INV_SLOT_SIZE = 22;
/*      */   private static final int INV_COLS = 9;
/*      */   private static final int HOTBAR_GAP = 4;
/*      */   private static final int POKEMON_CARD_H = 50;
/*      */   private static final int POKEMON_CARD_W = 88;
/*      */   private static final int POKEMON_CARDS_PER_ROW = 5;
/*      */   private static final int POKEMON_CARD_GAP = 3;
/*      */   private static final int POKEMON_MODEL_SIZE = 24;
/*      */   private static final int PC_BOX_COLUMNS = 5;
/*      */   private static final int PC_BOX_ROWS = 6;
/*      */   private static final int PC_BOX_SLOT_COUNT = 30;
/*      */   private static final int PC_BOX_HEADER_H = 12;
/*      */   private static final int PC_BOX_PADDING = 4;
/*      */   private static final int PC_BOX_SECTION_GAP = 8;
/*      */   private static final int PC_BOX_COMPACT_H = 16;
/*      */   private static final int TOOLTIP_BG = -267383266;
/*      */   private static final int TOOLTIP_BORDER = -10777105;
/*      */   private static final int TOOLTIP_PADDING = 5;
/*      */   private static final int TOOLTIP_LINE_HEIGHT = 11;
/*      */   private static final int SECTION_HEADER_TOTAL = 20;
/*      */   
/*      */   private static final class SnapshotEntry extends Record { private final String id;
/*      */     private final String timestamp;
/*      */     private final String changeType;
/*      */     private final List<String> fields;
/*      */     
/*      */     public final String toString() {
/*      */       // Byte code:
/*      */       //   0: aload_0
/*      */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/lookup/screen/views/LookupSnapshotsView$SnapshotEntry;)Ljava/lang/String;
/*      */       //   6: areturn
/*      */       // Line number table:
/*      */       //   Java source line number -> byte code offset
/*      */       //   #121	-> 0
/*      */       // Local variable table:
/*      */       //   start	length	slot	name	descriptor
/*      */       //   0	7	0	this	Lcom/atlas/common/fabric/lookup/screen/views/LookupSnapshotsView$SnapshotEntry;
/*      */     }
/*      */     
/*      */     public final int hashCode() {
/*      */       // Byte code:
/*      */       //   0: aload_0
/*      */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/lookup/screen/views/LookupSnapshotsView$SnapshotEntry;)I
/*      */       //   6: ireturn
/*      */       // Line number table:
/*      */       //   Java source line number -> byte code offset
/*      */       //   #121	-> 0
/*      */       // Local variable table:
/*      */       //   start	length	slot	name	descriptor
/*      */       //   0	7	0	this	Lcom/atlas/common/fabric/lookup/screen/views/LookupSnapshotsView$SnapshotEntry;
/*      */     }
/*      */     
/*  121 */     private SnapshotEntry(String id, String timestamp, String changeType, List<String> fields) { this.id = id; this.timestamp = timestamp; this.changeType = changeType; this.fields = fields; } public final boolean equals(Object o) { // Byte code:
/*      */       //   0: aload_0
/*      */       //   1: aload_1
/*      */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/lookup/screen/views/LookupSnapshotsView$SnapshotEntry;Ljava/lang/Object;)Z
/*      */       //   7: ireturn
/*      */       // Line number table:
/*      */       //   Java source line number -> byte code offset
/*      */       //   #121	-> 0
/*      */       // Local variable table:
/*      */       //   start	length	slot	name	descriptor
/*      */       //   0	8	0	this	Lcom/atlas/common/fabric/lookup/screen/views/LookupSnapshotsView$SnapshotEntry;
/*  121 */       //   0	8	1	o	Ljava/lang/Object; } public String id() { return this.id; } public String timestamp() { return this.timestamp; } public String changeType() { return this.changeType; } public List<String> fields() { return this.fields; } } private static final class ItemRow extends Record { private final int slot; private final String displayName; private final int count; private final class_1799 stack;
/*  122 */     private ItemRow(int slot, String displayName, int count, class_1799 stack) { this.slot = slot; this.displayName = displayName; this.count = count; this.stack = stack; } public final String toString() { // Byte code:
/*      */       //   0: aload_0
/*      */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/lookup/screen/views/LookupSnapshotsView$ItemRow;)Ljava/lang/String;
/*      */       //   6: areturn
/*      */       // Line number table:
/*      */       //   Java source line number -> byte code offset
/*      */       //   #122	-> 0
/*      */       // Local variable table:
/*      */       //   start	length	slot	name	descriptor
/*  122 */       //   0	7	0	this	Lcom/atlas/common/fabric/lookup/screen/views/LookupSnapshotsView$ItemRow; } public int slot() { return this.slot; } public final int hashCode() { // Byte code:
/*      */       //   0: aload_0
/*      */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/lookup/screen/views/LookupSnapshotsView$ItemRow;)I
/*      */       //   6: ireturn
/*      */       // Line number table:
/*      */       //   Java source line number -> byte code offset
/*      */       //   #122	-> 0
/*      */       // Local variable table:
/*      */       //   start	length	slot	name	descriptor
/*      */       //   0	7	0	this	Lcom/atlas/common/fabric/lookup/screen/views/LookupSnapshotsView$ItemRow; } public final boolean equals(Object o) { // Byte code:
/*      */       //   0: aload_0
/*      */       //   1: aload_1
/*      */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/lookup/screen/views/LookupSnapshotsView$ItemRow;Ljava/lang/Object;)Z
/*      */       //   7: ireturn
/*      */       // Line number table:
/*      */       //   Java source line number -> byte code offset
/*      */       //   #122	-> 0
/*      */       // Local variable table:
/*      */       //   start	length	slot	name	descriptor
/*      */       //   0	8	0	this	Lcom/atlas/common/fabric/lookup/screen/views/LookupSnapshotsView$ItemRow;
/*  122 */       //   0	8	1	o	Ljava/lang/Object; } public String displayName() { return this.displayName; } public int count() { return this.count; } public class_1799 stack() { return this.stack; }
/*      */      }
/*      */   private static final class PcBoxData { private final int boxNumber;
/*  125 */     private final JsonObject[] slots = new JsonObject[30];
/*      */     
/*      */     private PcBoxData(int boxNumber) {
/*  128 */       this.boxNumber = boxNumber;
/*      */     }
/*      */     
/*      */     private int count() {
/*  132 */       int count = 0;
/*  133 */       for (JsonObject slot : this.slots) {
/*  134 */         if (slot != null) count++; 
/*      */       } 
/*  136 */       return count;
/*      */     }
/*      */     
/*      */     private boolean isEmpty() {
/*  140 */       return (count() == 0);
/*      */     } }
/*      */   
/*      */   public LookupSnapshotsView() {
/*      */     
/*  145 */     try { PokemonRenderHelper.init(); } catch (Throwable throwable) {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void init(int x, int y, int width, int height) {
/*  152 */     this.x = x;
/*  153 */     this.y = y;
/*  154 */     this.width = width;
/*  155 */     this.height = height;
/*      */     
/*  157 */     int contentTop = y + 22;
/*  158 */     int contentHeight = height - 22 - 22;
/*  159 */     if (this.scrollArea == null) {
/*  160 */       this.scrollArea = new GuideScrollableArea(x, contentTop, width, contentHeight);
/*  161 */       this.scrollArea.setThumbColors(-12958368, -10777105);
/*      */     } else {
/*  163 */       this.scrollArea.updateBounds(x, contentTop, width, contentHeight);
/*      */     } 
/*      */     
/*  166 */     if (this.detailScrollArea == null) {
/*  167 */       this.detailScrollArea = new GuideScrollableArea(x, contentTop, width, contentHeight - 22);
/*  168 */       this.detailScrollArea.setThumbColors(-12958368, -10777105);
/*      */     } else {
/*  170 */       this.detailScrollArea.updateBounds(x, contentTop, width, contentHeight - 22);
/*      */     } 
/*      */     
/*  173 */     LookupClientData data = LookupClientData.getInstance();
/*  174 */     data.removeListener(this.dataListener);
/*  175 */     data.addListener(this.dataListener);
/*      */     
/*  177 */     if (!this.dataRequested) {
/*  178 */       this.dataRequested = true;
/*  179 */       String uuid = data.getTargetUuid();
/*  180 */       LookupNetwork.requestLookupData("snapshots", this.currentPage, (uuid != null) ? uuid : "");
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void onDataUpdated() {
/*  187 */     this.dataParsed = false;
/*  188 */     LookupClientData data = LookupClientData.getInstance();
/*  189 */     String detailJson = data.getSnapshotDetailJson();
/*  190 */     if (detailJson != null && !detailJson.isEmpty() && this.showingDetail && !this.detailLoaded) {
/*  191 */       parseDetailData(detailJson);
/*  192 */       this.detailLoaded = true;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void parseData() {
/*  197 */     this.entries.clear();
/*  198 */     LookupClientData data = LookupClientData.getInstance();
/*  199 */     String json = data.getSnapshotsJson();
/*  200 */     this.totalEntries = data.getSnapshotsTotal();
/*  201 */     this.currentPage = data.getSnapshotsPage();
/*      */     
/*  203 */     if (json == null || json.isEmpty()) {
/*  204 */       this.dataParsed = true;
/*      */       
/*      */       return;
/*      */     } 
/*      */     try {
/*  209 */       JsonArray arr = JsonParser.parseString(json).getAsJsonArray();
/*  210 */       for (int i = 0; i < arr.size(); i++) {
/*  211 */         JsonObject obj = arr.get(i).getAsJsonObject();
/*  212 */         String id = obj.has("id") ? obj.get("id").getAsString() : "";
/*  213 */         String timestamp = obj.has("timestamp") ? obj.get("timestamp").getAsString() : "";
/*  214 */         String changeType = obj.has("changeType") ? obj.get("changeType").getAsString() : "";
/*  215 */         List<String> fields = new ArrayList<>();
/*  216 */         if (obj.has("fields") && obj.get("fields").isJsonArray()) {
/*  217 */           for (JsonElement f : obj.getAsJsonArray("fields")) {
/*  218 */             fields.add(f.getAsString());
/*      */           }
/*      */         }
/*  221 */         this.entries.add(new SnapshotEntry(id, timestamp, changeType, fields));
/*      */       } 
/*  223 */     } catch (Exception exception) {}
/*      */     
/*  225 */     this.dataParsed = true;
/*  226 */     recalcScrollHeight();
/*      */   }
/*      */   
/*      */   private void parseDetailData(String json) {
/*  230 */     this.detailParty.clear();
/*  231 */     this.detailPc.clear();
/*  232 */     this.detailPcBoxes.clear();
/*  233 */     this.detailInventory.clear();
/*  234 */     this.detailPokeCoin = 0L;
/*  235 */     this.detailPokeGem = 0L;
/*  236 */     this.detailHonorPoint = 0L;
/*      */     
/*      */     try {
/*  239 */       JsonObject root = JsonParser.parseString(json).getAsJsonObject();
/*      */       
/*  241 */       if (root.has("party") && root.get("party").isJsonArray())
/*  242 */         for (JsonElement el : root.getAsJsonArray("party")) {
/*  243 */           if (el.isJsonObject()) this.detailParty.add(el.getAsJsonObject());
/*      */         
/*      */         }  
/*  246 */       if (root.has("pc") && root.get("pc").isJsonArray())
/*  247 */         for (JsonElement el : root.getAsJsonArray("pc")) {
/*  248 */           if (el.isJsonObject()) this.detailPc.add(el.getAsJsonObject());
/*      */         
/*      */         }  
/*  251 */       if (root.has("pcBoxes") && root.get("pcBoxes").isJsonArray()) {
/*  252 */         for (JsonElement boxEl : root.getAsJsonArray("pcBoxes")) {
/*  253 */           if (!boxEl.isJsonObject())
/*  254 */             continue;  JsonObject boxObj = boxEl.getAsJsonObject();
/*  255 */           PcBoxData box = new PcBoxData(boxObj.has("box") ? boxObj.get("box").getAsInt() : this.detailPcBoxes.size());
/*  256 */           if (boxObj.has("pokemon") && boxObj.get("pokemon").isJsonArray()) {
/*  257 */             int fallbackSlot = 0;
/*  258 */             for (JsonElement pokeEl : boxObj.getAsJsonArray("pokemon")) {
/*  259 */               if (!pokeEl.isJsonObject())
/*  260 */                 continue;  JsonObject poke = pokeEl.getAsJsonObject();
/*  261 */               int slot = poke.has("slot") ? poke.get("slot").getAsInt() : fallbackSlot;
/*  262 */               if (slot >= 0 && slot < 30) {
/*  263 */                 box.slots[slot] = poke;
/*      */               }
/*  265 */               fallbackSlot++;
/*      */             } 
/*      */           } 
/*  268 */           this.detailPcBoxes.add(box);
/*      */         } 
/*  270 */       } else if (!this.detailPc.isEmpty()) {
/*  271 */         int sequentialIndex = 0;
/*  272 */         for (JsonObject poke : this.detailPc) {
/*  273 */           int boxIndex = sequentialIndex / 30;
/*  274 */           int slotIndex = sequentialIndex % 30;
/*  275 */           while (this.detailPcBoxes.size() <= boxIndex) {
/*  276 */             this.detailPcBoxes.add(new PcBoxData(this.detailPcBoxes.size()));
/*      */           }
/*  278 */           ((PcBoxData)this.detailPcBoxes.get(boxIndex)).slots[slotIndex] = poke;
/*  279 */           sequentialIndex++;
/*      */         } 
/*      */       } 
/*      */       
/*  283 */       if (root.has("inventory") && root.get("inventory").isJsonArray()) {
/*  284 */         for (JsonElement el : root.getAsJsonArray("inventory")) {
/*  285 */           if (!el.isJsonObject())
/*  286 */             continue;  JsonObject it = el.getAsJsonObject();
/*  287 */           int slot = it.has("slot") ? it.get("slot").getAsInt() : -1;
/*  288 */           String name = it.has("displayName") ? it.get("displayName").getAsString() : "?";
/*  289 */           int count = it.has("count") ? it.get("count").getAsInt() : 1;
/*  290 */           class_1799 stack = class_1799.field_8037;
/*  291 */           if (it.has("stackJson")) {
/*  292 */             stack = deserializeStack(it.get("stackJson").getAsString());
/*      */           }
/*  294 */           this.detailInventory.add(new ItemRow(slot, name, count, stack));
/*      */         } 
/*      */       }
/*      */       
/*  298 */       if (root.has("currency") && root.get("currency").isJsonObject()) {
/*  299 */         JsonObject cur = root.getAsJsonObject("currency");
/*  300 */         if (cur.has("pokeCoin")) this.detailPokeCoin = cur.get("pokeCoin").getAsLong(); 
/*  301 */         if (cur.has("pokeGem")) this.detailPokeGem = cur.get("pokeGem").getAsLong(); 
/*  302 */         if (cur.has("honorPoint")) this.detailHonorPoint = cur.get("honorPoint").getAsLong(); 
/*      */       } 
/*  304 */     } catch (Exception exception) {}
/*      */     
/*  306 */     recalcDetailScrollHeight();
/*      */   }
/*      */   
/*      */   private void recalcScrollHeight() {
/*  310 */     int totalH = 6;
/*  311 */     for (int i = 0; i < this.entries.size(); i++) {
/*  312 */       totalH += 34;
/*      */     }
/*  314 */     this.scrollArea.setContentHeight(totalH);
/*      */   }
/*      */ 
/*      */   
/*      */   private int inventoryGridHeight() {
/*  319 */     int maxSlot = -1;
/*  320 */     for (ItemRow row : this.detailInventory) {
/*  321 */       if (row.slot() > maxSlot) maxSlot = row.slot();
/*      */     
/*      */     } 
/*      */     
/*  325 */     int baseRows = 4;
/*  326 */     int extraRows = 0;
/*  327 */     if (maxSlot >= 36) {
/*  328 */       int extraSlots = maxSlot - 35;
/*  329 */       extraRows = (extraSlots + 9 - 1) / 9;
/*      */     } 
/*  331 */     return (baseRows + extraRows) * 24 + 4;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void recalcDetailScrollHeight() {
/*  338 */     int h = 6;
/*  339 */     h += 20;
/*  340 */     if (this.detailInventory.isEmpty()) {
/*  341 */       h += 26;
/*      */     } else {
/*  343 */       h += inventoryGridHeight() + 4;
/*      */     } 
/*  345 */     h += 20;
/*  346 */     int partyCards = Math.max(1, this.detailParty.size());
/*  347 */     int partyCardRows = (partyCards + 5 - 1) / 5;
/*  348 */     h += partyCardRows * 53 + 4;
/*  349 */     h += 20;
/*  350 */     h += getPcBoxesHeight() + 4;
/*  351 */     h += 20;
/*  352 */     h += 43;
/*  353 */     h += 28;
/*      */     
/*  355 */     this.detailScrollArea.setContentHeight(h);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void render(class_332 ctx, int mouseX, int mouseY, float delta, class_327 textRenderer) {
/*  362 */     if (!this.dataParsed) {
/*  363 */       parseData();
/*      */     }
/*      */     
/*  366 */     if (this.showingDetail) {
/*  367 */       renderDetail(ctx, mouseX, mouseY, delta, textRenderer);
/*      */       
/*      */       return;
/*      */     } 
/*  371 */     ctx.method_51433(textRenderer, "§lSnapshots", this.x + 6, this.y + 6, -10777105, false);
/*      */     
/*  373 */     if (this.totalEntries > 0) {
/*  374 */       String countLabel = "" + this.totalEntries + " snapshots";
/*  375 */       int clw = textRenderer.method_1727(countLabel);
/*  376 */       ctx.method_51433(textRenderer, countLabel, this.x + this.width - clw - 6, this.y + 6, -9930592, false);
/*      */     } 
/*      */     
/*  379 */     if (this.entries.isEmpty()) {
/*  380 */       String msg = (LookupClientData.getInstance().getSnapshotsJson() == null) ? "Loading..." : "No snapshots found";
/*  381 */       int tw = textRenderer.method_1727(msg);
/*  382 */       ctx.method_51433(textRenderer, msg, this.x + (this.width - tw) / 2, this.y + this.height / 2, -9930592, false);
/*  383 */       renderFooter(ctx, mouseX, mouseY, textRenderer);
/*      */       
/*      */       return;
/*      */     } 
/*  387 */     this.scrollArea.beginRender(ctx);
/*      */     
/*  389 */     int contentTop = this.scrollArea.getY();
/*  390 */     int scrollOff = this.scrollArea.getScrollOffset();
/*  391 */     int areaBottom = this.scrollArea.getY() + this.scrollArea.getHeight();
/*      */     
/*  393 */     int rowY = contentTop - scrollOff;
/*  394 */     for (int i = 0; i < this.entries.size(); i++) {
/*  395 */       if (rowY + 34 >= this.scrollArea.getY() && rowY <= areaBottom) {
/*  396 */         renderEntry(ctx, textRenderer, this.entries.get(i), i, this.x + 6, rowY, this.width - 12, mouseX, mouseY);
/*      */       }
/*  398 */       rowY += 34;
/*      */     } 
/*      */     
/*  401 */     this.scrollArea.endRender(ctx);
/*      */     
/*  403 */     renderFooter(ctx, mouseX, mouseY, textRenderer);
/*      */   }
/*      */ 
/*      */   
/*      */   private void renderEntry(class_332 ctx, class_327 textRenderer, SnapshotEntry entry, int index, int ex, int ey, int ew, int mouseX, int mouseY) {
/*  408 */     int bg = (index % 2 == 0) ? -14801866 : -15328732;
/*  409 */     ctx.method_25294(ex, ey, ex + ew, ey + 34, bg);
/*      */     
/*  411 */     boolean hovered = (mouseX >= ex && mouseX < ex + ew && mouseY >= ey && mouseY < ey + 34);
/*  412 */     if (hovered) {
/*  413 */       ctx.method_25294(ex, ey, ex + ew, ey + 34, -14274480);
/*      */     }
/*      */     
/*  416 */     ctx.method_25294(ex, ey, ex + 2, ey + 34, changeTypeColor(entry.changeType));
/*      */     
/*  418 */     int textY = ey + 12 - 4;
/*  419 */     int cx = ex + 6;
/*      */     
/*  421 */     String ts = formatTimestamp(entry.timestamp);
/*  422 */     ctx.method_51433(textRenderer, ts, cx, textY, -9930592, false);
/*  423 */     cx += textRenderer.method_1727(ts) + 6;
/*      */     
/*  425 */     int badgeColor = changeTypeColor(entry.changeType);
/*  426 */     String typeLabel = entry.changeType;
/*  427 */     int badgeW = textRenderer.method_1727(typeLabel) + 6;
/*  428 */     ctx.method_25294(cx, ey + 4, cx + badgeW, ey + 4 + 12, LookupColors.withAlpha(badgeColor, 40));
/*  429 */     ctx.method_51433(textRenderer, typeLabel, cx + 3, textY, badgeColor, false);
/*  430 */     cx += badgeW + 6;
/*      */     
/*  432 */     int fieldY = textY + 12;
/*  433 */     int fx = ex + 6;
/*  434 */     for (String field : entry.fields) {
/*  435 */       int fieldBadgeColor = fieldBadgeColor(field);
/*  436 */       int fw = textRenderer.method_1727(field) + 6;
/*  437 */       ctx.method_25294(fx, fieldY, fx + fw, fieldY + 10, LookupColors.withAlpha(fieldBadgeColor, 45));
/*  438 */       ctx.method_49601(fx, fieldY, fw, 10, LookupColors.withAlpha(fieldBadgeColor, 90));
/*  439 */       ctx.method_51433(textRenderer, field, fx + 3, fieldY + 1, fieldBadgeColor, false);
/*  440 */       fx += fw + 3;
/*      */     } 
/*      */     
/*  443 */     String hint = ">";
/*  444 */     ctx.method_51433(textRenderer, hint, ex + ew - textRenderer.method_1727(hint) - 4, textY + 4, -9930592, false);
/*      */   }
/*      */ 
/*      */   
/*      */   private void renderDetail(class_332 ctx, int mouseX, int mouseY, float delta, class_327 textRenderer) {
/*  449 */     this.hoveredPartyIndex = -1;
/*  450 */     this.hoveredPcPokemon = null;
/*  451 */     this.hoveredMouseX = mouseX;
/*  452 */     this.hoveredMouseY = mouseY;
/*      */     
/*  454 */     ctx.method_25294(this.x, this.y, this.x + this.width, this.y + 22, -15328732);
/*      */     
/*  456 */     String backLabel = "< Back";
/*  457 */     int backW = textRenderer.method_1727(backLabel) + 8;
/*  458 */     boolean backHovered = (mouseX >= this.x + 6 && mouseX < this.x + 6 + backW && mouseY >= this.y + 4 && mouseY < this.y + 22 - 4);
/*      */     
/*  460 */     ctx.method_25294(this.x + 6, this.y + 4, this.x + 6 + backW, this.y + 22 - 4, 
/*  461 */         backHovered ? -14142392 : -14801354);
/*  462 */     ctx.method_49601(this.x + 6, this.y + 4, backW, 14, -12958368);
/*  463 */     ctx.method_51433(textRenderer, backLabel, this.x + 6 + 4, this.y + 7, -1511169, false);
/*      */     
/*  465 */     if (this.detailEntry != null) {
/*  466 */       String ts = formatTimestamp(this.detailEntry.timestamp);
/*  467 */       int tsW = textRenderer.method_1727(ts);
/*  468 */       String typeLabel = this.detailEntry.changeType;
/*  469 */       int typeLabelW = textRenderer.method_1727(typeLabel) + 6;
/*  470 */       int totalW = tsW + 8 + typeLabelW;
/*  471 */       int titleStartX = this.x + (this.width - totalW) / 2;
/*  472 */       ctx.method_51433(textRenderer, ts, titleStartX, this.y + 7, -1511169, false);
/*  473 */       int badgeX = titleStartX + tsW + 8;
/*  474 */       int typeColor = changeTypeColor(this.detailEntry.changeType);
/*  475 */       ctx.method_25294(badgeX, this.y + 4, badgeX + typeLabelW, this.y + 4 + 13, LookupColors.withAlpha(typeColor, 50));
/*  476 */       ctx.method_49601(badgeX, this.y + 4, typeLabelW, 13, LookupColors.withAlpha(typeColor, 120));
/*  477 */       ctx.method_51433(textRenderer, typeLabel, badgeX + 3, this.y + 7, typeColor, false);
/*      */     } 
/*      */     
/*  480 */     if (!this.detailLoaded) {
/*  481 */       String msg = "Loading snapshot data...";
/*  482 */       int tw = textRenderer.method_1727(msg);
/*  483 */       ctx.method_51433(textRenderer, msg, this.x + (this.width - tw) / 2, this.y + this.height / 2, -9930592, false);
/*      */       
/*      */       return;
/*      */     } 
/*  487 */     this.detailScrollArea.beginRender(ctx);
/*      */     
/*  489 */     int top = this.detailScrollArea.getY();
/*  490 */     int scrollOff = this.detailScrollArea.getScrollOffset();
/*  491 */     int cy = top - scrollOff + 6;
/*  492 */     int contentX = this.x + 6;
/*  493 */     int contentW = this.width - 12;
/*      */ 
/*      */     
/*  496 */     cy = renderSectionHeader(ctx, textRenderer, "Inventory  (" + this.detailInventory.size() + " items)", contentX, cy, contentW);
/*  497 */     if (this.detailInventory.isEmpty()) {
/*  498 */       ctx.method_51433(textRenderer, "Empty", contentX + 4, cy + 2, -9930592, false);
/*  499 */       cy += 26;
/*      */     } else {
/*  501 */       cy = renderInventoryGrid(ctx, textRenderer, contentX, cy, contentW, mouseX, mouseY);
/*  502 */       cy += 4;
/*      */     } 
/*      */ 
/*      */     
/*  506 */     cy = renderSectionHeader(ctx, textRenderer, "Party  (" + this.detailParty.size() + ")", contentX, cy, contentW);
/*  507 */     if (this.detailParty.isEmpty()) {
/*  508 */       ctx.method_51433(textRenderer, "Empty", contentX + 4, cy + 2, -9930592, false);
/*  509 */       cy += 54;
/*      */     } else {
/*  511 */       cy = renderPokemonCardGrid(ctx, textRenderer, this.detailParty, "party", contentX, cy, contentW, mouseX, mouseY, delta);
/*  512 */       cy += 4;
/*      */     } 
/*      */ 
/*      */     
/*  516 */     cy = renderSectionHeader(ctx, textRenderer, "PC  (" + this.detailPc.size() + " Pokemon)", contentX, cy, contentW);
/*  517 */     if (this.detailPc.isEmpty()) {
/*  518 */       ctx.method_51433(textRenderer, "Empty", contentX + 4, cy + 2, -9930592, false);
/*  519 */       cy += 54;
/*      */     } else {
/*  521 */       cy = renderPcBoxes(ctx, textRenderer, contentX, cy, contentW, mouseX, mouseY, delta);
/*  522 */       cy += 4;
/*      */     } 
/*      */ 
/*      */     
/*  526 */     cy = renderSectionHeader(ctx, textRenderer, "Currency", contentX, cy, contentW);
/*  527 */     ctx.method_51433(textRenderer, "PokeCoin: " + this.detailPokeCoin, contentX + 4, cy + 2, -1511169, false);
/*  528 */     cy += 13;
/*  529 */     ctx.method_51433(textRenderer, "PokeGem: " + this.detailPokeGem, contentX + 4, cy + 2, -1511169, false);
/*  530 */     cy += 13;
/*  531 */     ctx.method_51433(textRenderer, "HonorPoint: " + this.detailHonorPoint, contentX + 4, cy + 2, -1511169, false);
/*  532 */     cy += 17;
/*      */ 
/*      */     
/*  535 */     if (this.detailEntry != null) {
/*  536 */       int btnX = contentX;
/*  537 */       int btnY = cy + 2;
/*  538 */       for (String section : RESTORE_SECTIONS) {
/*  539 */         if (this.detailEntry.fields().contains(section)) {
/*  540 */           boolean isConfirming = section.equals(this.confirmingSection);
/*  541 */           String label = isConfirming ? "Confirm" : ("Restore " + capitalize(section));
/*  542 */           int btnW = textRenderer.method_1727(label) + 10;
/*  543 */           boolean btnHovered = (mouseX >= btnX && mouseX < btnX + btnW && mouseY >= btnY && mouseY < btnY + 16);
/*  544 */           int btnBg = isConfirming ? -1662404 : (btnHovered ? -14142392 : -14801354);
/*  545 */           int btnBorder = isConfirming ? -1662404 : (btnHovered ? -10777105 : -12958368);
/*  546 */           ctx.method_25294(btnX, btnY, btnX + btnW, btnY + 16, btnBg);
/*  547 */           ctx.method_49601(btnX, btnY, btnW, 16, btnBorder);
/*  548 */           int textColor = isConfirming ? -15789801 : (btnHovered ? -1511169 : -9930592);
/*  549 */           ctx.method_51433(textRenderer, label, btnX + 5, btnY + 4, textColor, false);
/*  550 */           btnX += btnW + 4;
/*      */         } 
/*      */       } 
/*      */     } 
/*  554 */     this.detailScrollArea.endRender(ctx);
/*      */ 
/*      */     
/*  557 */     renderTooltips(ctx, mouseX, mouseY, textRenderer);
/*      */   }
/*      */ 
/*      */   
/*      */   private int renderSectionHeader(class_332 ctx, class_327 tr, String label, int sx, int sy, int sw) {
/*  562 */     ctx.method_25294(sx, sy, sx + sw, sy + 18 - 2, -15328732);
/*  563 */     ctx.method_25294(sx, sy, sx + 3, sy + 18 - 2, -10777105);
/*  564 */     ctx.method_25294(sx, sy + 18 - 2, sx + sw, sy + 18 - 1, LookupColors.withAlpha(-10777105, 80));
/*  565 */     ctx.method_51433(tr, "§l" + label, sx + 7, sy + 4, -10777105, false);
/*  566 */     return sy + 18 + 2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int renderInventoryGrid(class_332 ctx, class_327 tr, int gridX, int startY, int contentW, int mouseX, int mouseY) {
/*  577 */     Map<Integer, ItemRow> bySlot = new HashMap<>();
/*  578 */     int maxSlot = -1;
/*  579 */     for (ItemRow row : this.detailInventory) {
/*  580 */       bySlot.put(Integer.valueOf(row.slot()), row);
/*  581 */       if (row.slot() > maxSlot) maxSlot = row.slot();
/*      */     
/*      */     } 
/*      */ 
/*      */     
/*  586 */     int extraSlots = Math.max(0, maxSlot - 35);
/*  587 */     int extraRows = (extraSlots + 9 - 1) / 9;
/*  588 */     int totalRows = 4 + extraRows;
/*      */     
/*  590 */     class_1799 hovStack = null;
/*  591 */     String hovLabel = null;
/*  592 */     int hovX = -1, hovY = -1;
/*      */     
/*  594 */     for (int visualIndex = 0; visualIndex < totalRows * 9; visualIndex++) {
/*  595 */       int s, row = visualIndex / 9;
/*  596 */       int col = visualIndex % 9;
/*  597 */       int sx = gridX + col * 24;
/*  598 */       int sy = startY + row * 24 + ((row >= 3) ? 4 : 0);
/*      */ 
/*      */ 
/*      */       
/*  602 */       if (row < 3) {
/*  603 */         s = 9 + row * 9 + col;
/*  604 */       } else if (row == 3) {
/*  605 */         s = col;
/*      */       } else {
/*  607 */         s = 36 + (row - 4) * 9 + col;
/*      */       } 
/*      */       
/*  610 */       ctx.method_25294(sx, sy, sx + 22, sy + 22, -14801866);
/*  611 */       ctx.method_49601(sx, sy, 22, 22, -14012352);
/*      */       
/*  613 */       ItemRow item = bySlot.get(Integer.valueOf(s));
/*  614 */       boolean hovered = (mouseX >= sx && mouseX < sx + 22 && mouseY >= sy && mouseY < sy + 22);
/*      */ 
/*      */       
/*  617 */       if (item != null) {
/*  618 */         if (item.stack() != null && !item.stack().method_7960()) {
/*  619 */           int ix = sx + 3;
/*  620 */           int iy = sy + 3;
/*  621 */           ctx.method_51427(item.stack(), ix, iy);
/*  622 */           if (item.count() > 1 || item.stack().method_7947() > 1) {
/*  623 */             int n = (item.stack().method_7947() > 1) ? item.stack().method_7947() : item.count();
/*  624 */             ctx.method_51448().method_22903();
/*  625 */             ctx.method_51448().method_46416(0.0F, 0.0F, 200.0F);
/*  626 */             String countStr = String.valueOf(n);
/*  627 */             int countW = tr.method_1727(countStr);
/*  628 */             ctx.method_51433(tr, countStr, sx + 22 - countW - 1, sy + 22 - 9, -1, true);
/*  629 */             ctx.method_51448().method_22909();
/*      */           } 
/*      */         } else {
/*      */           
/*  633 */           String abbrev = (item.displayName().length() > 2) ? item.displayName().substring(0, 2) : item.displayName();
/*  634 */           ctx.method_51433(tr, abbrev, sx + 4, sy + 7, -1511169, false);
/*  635 */           if (item.count() > 1) {
/*  636 */             ctx.method_51448().method_22903();
/*  637 */             ctx.method_51448().method_46416(0.0F, 0.0F, 200.0F);
/*  638 */             String countStr = String.valueOf(item.count());
/*  639 */             int countW = tr.method_1727(countStr);
/*  640 */             ctx.method_51433(tr, countStr, sx + 22 - countW - 1, sy + 22 - 9, -1, true);
/*  641 */             ctx.method_51448().method_22909();
/*      */           } 
/*      */         } 
/*      */         
/*  645 */         if (hovered) {
/*  646 */           ctx.method_25294(sx, sy, sx + 22, sy + 22, LookupColors.withAlpha(-10777105, 40));
/*  647 */           if (item.stack() != null && !item.stack().method_7960()) {
/*  648 */             hovStack = item.stack();
/*      */           } else {
/*  650 */             hovLabel = item.displayName() + item.displayName();
/*      */           } 
/*  652 */           hovX = mouseX;
/*  653 */           hovY = mouseY;
/*      */         } 
/*  655 */       } else if (hovered) {
/*  656 */         ctx.method_25294(sx, sy, sx + 22, sy + 22, LookupColors.withAlpha(-12958368, 30));
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  661 */     if (hovStack != null) {
/*  662 */       class_4587 matrices = ctx.method_51448();
/*  663 */       matrices.method_22903();
/*  664 */       matrices.method_46416(0.0F, 0.0F, 400.0F);
/*  665 */       ctx.method_51446(tr, hovStack, hovX, hovY);
/*  666 */       matrices.method_22909();
/*  667 */     } else if (hovLabel != null) {
/*  668 */       class_4587 matrices = ctx.method_51448();
/*  669 */       matrices.method_22903();
/*  670 */       matrices.method_46416(0.0F, 0.0F, 400.0F);
/*  671 */       int w = tr.method_1727(hovLabel) + 8;
/*  672 */       int tx = hovX + 10;
/*  673 */       int ty = hovY + 10;
/*  674 */       ctx.method_25294(tx, ty, tx + w, ty + 12, -267383266);
/*  675 */       ctx.method_49601(tx, ty, w, 12, -10777105);
/*  676 */       ctx.method_51433(tr, hovLabel, tx + 4, ty + 2, -1511169, true);
/*  677 */       matrices.method_22909();
/*      */     } 
/*      */     
/*  680 */     return startY + totalRows * 24 + 4;
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
/*      */   private int renderPokemonCardGrid(class_332 ctx, class_327 tr, List<JsonObject> rows, String section, int gridX, int startY, int contentW, int mouseX, int mouseY, float delta) {
/*  692 */     int cardsPerRow = Math.max(1, (contentW + 3) / 91);
/*  693 */     int actualCardW = 88;
/*      */     
/*  695 */     int cy = startY;
/*  696 */     for (int i = 0; i < rows.size(); i++) {
/*  697 */       int col = i % cardsPerRow;
/*  698 */       int cardX = gridX + col * (actualCardW + 3);
/*  699 */       int cardY = cy;
/*      */       
/*  701 */       JsonObject poke = rows.get(i);
/*  702 */       boolean shiny = hasAspect(poke, "shiny");
/*  703 */       String species = getString(poke, "species");
/*  704 */       int level = getInt(poke, "level");
/*      */       
/*  706 */       boolean hovered = (mouseX >= cardX && mouseX < cardX + actualCardW && mouseY >= cardY && mouseY < cardY + 50);
/*      */ 
/*      */       
/*  709 */       int bg = hovered ? -14274480 : -14801866;
/*  710 */       int border = hovered ? -10777105 : -14012352;
/*  711 */       ctx.method_25294(cardX, cardY, cardX + actualCardW, cardY + 50, bg);
/*  712 */       ctx.method_49601(cardX, cardY, actualCardW, 50, border);
/*      */ 
/*      */       
/*  715 */       int modelX = cardX + (actualCardW - 24) / 2;
/*  716 */       int modelY = cardY + 1;
/*  717 */       renderPokemonModel(ctx, species, poke, modelX, modelY, 24, section + "_" + section, delta);
/*      */ 
/*      */       
/*  720 */       String displayName = formatSpeciesName(species);
/*  721 */       int maxNameW = actualCardW - 6;
/*  722 */       String nameText = displayName;
/*  723 */       if (tr.method_1727(nameText) > maxNameW) {
/*  724 */         while (nameText.length() > 1 && tr.method_1727(nameText + "..") > maxNameW) {
/*  725 */           nameText = nameText.substring(0, nameText.length() - 1);
/*      */         }
/*  727 */         nameText = nameText + "..";
/*      */       } 
/*  729 */       int nameColor = shiny ? -1662404 : -1511169;
/*  730 */       int nameW = tr.method_1727(nameText);
/*  731 */       ctx.method_51433(tr, nameText, cardX + (actualCardW - nameW) / 2, cardY + 24 + 3, nameColor, true);
/*      */ 
/*      */ 
/*      */       
/*  735 */       String lvlStr = "Lv" + level;
/*  736 */       int lvlW = tr.method_1727(lvlStr);
/*  737 */       ctx.method_51433(tr, lvlStr, cardX + (actualCardW - lvlW) / 2, cardY + 24 + 13, -9930592, true);
/*      */ 
/*      */ 
/*      */       
/*  741 */       String gender = getString(poke, "gender");
/*  742 */       String gSym = genderSymbol(gender);
/*  743 */       int topRightX = cardX + actualCardW - 3;
/*  744 */       if (shiny) {
/*  745 */         topRightX -= tr.method_1727("★");
/*  746 */         ctx.method_51433(tr, "★", topRightX, cardY + 2, -1662404, true);
/*      */       } 
/*  748 */       if (!gSym.isEmpty()) {
/*  749 */         topRightX -= tr.method_1727(gSym) + (shiny ? 1 : 0);
/*  750 */         ctx.method_51433(tr, gSym, topRightX, cardY + 2, genderColor(gender), true);
/*      */       } 
/*      */       
/*  753 */       if (hovered) {
/*  754 */         if ("party".equals(section)) { this.hoveredPartyIndex = i; }
/*  755 */         else if ("pc".equals(section)) { this.hoveredPcPokemon = poke; }
/*      */       
/*      */       }
/*  758 */       if (col == cardsPerRow - 1 || i == rows.size() - 1) {
/*  759 */         cy += 53;
/*      */       }
/*      */     } 
/*  762 */     return cy;
/*      */   }
/*      */ 
/*      */   
/*      */   private int renderPcBoxes(class_332 ctx, class_327 tr, int gridX, int startY, int contentW, int mouseX, int mouseY, float delta) {
/*  767 */     int cy = startY;
/*  768 */     int outerW = 460;
/*  769 */     for (PcBoxData box : this.detailPcBoxes) {
/*  770 */       int boxHeight = getPcBoxHeight(box);
/*  771 */       ctx.method_25294(gridX, cy, gridX + outerW, cy + boxHeight, LookupColors.withAlpha(-15328732, 185));
/*  772 */       ctx.method_49601(gridX, cy, outerW, boxHeight, -14012352);
/*  773 */       ctx.method_51433(tr, "Box " + box.boxNumber + 1 + " (" + box.count() + "/30)", gridX + 4, cy + 2, -9930592, true);
/*      */ 
/*      */       
/*  776 */       if (!box.isEmpty()) {
/*  777 */         int slotStartX = gridX + 4;
/*  778 */         int slotStartY = cy + 12 + 4;
/*  779 */         for (int slotIndex = 0; slotIndex < 30; slotIndex++) {
/*  780 */           int col = slotIndex % 5;
/*  781 */           int row = slotIndex / 5;
/*  782 */           int slotX = slotStartX + col * 91;
/*  783 */           int slotY = slotStartY + row * 53;
/*  784 */           JsonObject poke = box.slots[slotIndex];
/*      */           
/*  786 */           boolean hovered = (poke != null && mouseX >= slotX && mouseX < slotX + 88 && mouseY >= slotY && mouseY < slotY + 50);
/*      */ 
/*      */           
/*  789 */           int bg = hovered ? -14274480 : -14801866;
/*  790 */           if (poke == null) bg = LookupColors.withAlpha(-15789801, 120); 
/*  791 */           ctx.method_25294(slotX, slotY, slotX + 88, slotY + 50, bg);
/*  792 */           ctx.method_49601(slotX, slotY, 88, 50, 
/*  793 */               hovered ? -10777105 : -14012352);
/*      */           
/*  795 */           if (poke == null) {
/*  796 */             ctx.method_51433(tr, String.valueOf(slotIndex + 1), slotX + 3, slotY + 3, -9930592, false);
/*      */           } else {
/*  798 */             renderSnapshotPcSlot(ctx, tr, poke, slotX, slotY, slotIndex, "pc_box_" + box.boxNumber + "_" + slotIndex, delta);
/*  799 */             if (hovered) this.hoveredPcPokemon = poke;
/*      */           
/*      */           } 
/*      */         } 
/*      */       } 
/*  804 */       cy += boxHeight + 8;
/*      */     } 
/*  806 */     return cy;
/*      */   }
/*      */ 
/*      */   
/*      */   private void renderSnapshotPcSlot(class_332 ctx, class_327 tr, JsonObject poke, int cardX, int cardY, int slotIndex, String stateKey, float delta) {
/*  811 */     boolean shiny = hasAspect(poke, "shiny");
/*  812 */     String species = getString(poke, "species");
/*  813 */     int level = getInt(poke, "level");
/*      */     
/*  815 */     int modelX = cardX + 32;
/*  816 */     int modelY = cardY + 1;
/*  817 */     renderPokemonModel(ctx, species, poke, modelX, modelY, 24, stateKey, delta);
/*      */     
/*  819 */     String displayName = formatSpeciesName(species);
/*  820 */     int maxNameW = 82;
/*  821 */     String nameText = displayName;
/*  822 */     if (tr.method_1727(nameText) > maxNameW) {
/*  823 */       while (nameText.length() > 1 && tr.method_1727(nameText + "..") > maxNameW) {
/*  824 */         nameText = nameText.substring(0, nameText.length() - 1);
/*      */       }
/*  826 */       nameText = nameText + "..";
/*      */     } 
/*  828 */     int nameColor = shiny ? -1662404 : -1511169;
/*  829 */     int nameW = tr.method_1727(nameText);
/*  830 */     ctx.method_51433(tr, nameText, cardX + (88 - nameW) / 2, cardY + 24 + 3, nameColor, true);
/*      */ 
/*      */     
/*  833 */     String lvlStr = "Lv" + level;
/*  834 */     int lvlW = tr.method_1727(lvlStr);
/*  835 */     ctx.method_51433(tr, lvlStr, cardX + (88 - lvlW) / 2, cardY + 24 + 13, -9930592, true);
/*      */ 
/*      */     
/*  838 */     ctx.method_51433(tr, String.valueOf(slotIndex + 1), cardX + 3, cardY + 3, -9930592, false);
/*      */     
/*  840 */     String gender = getString(poke, "gender");
/*  841 */     String gSym = genderSymbol(gender);
/*  842 */     int topRightX = cardX + 88 - 3;
/*  843 */     if (shiny) {
/*  844 */       topRightX -= tr.method_1727("★");
/*  845 */       ctx.method_51433(tr, "★", topRightX, cardY + 2, -1662404, true);
/*      */     } 
/*  847 */     if (!gSym.isEmpty()) {
/*  848 */       topRightX -= tr.method_1727(gSym) + (shiny ? 1 : 0);
/*  849 */       ctx.method_51433(tr, gSym, topRightX, cardY + 2, genderColor(gender), true);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void renderPokemonModel(class_332 ctx, String species, JsonObject poke, int mx, int my, int ms, String stateKey, float delta) {
/*  855 */     if (species == null || species.isEmpty() || !PokemonRenderHelper.isAvailable()) {
/*  856 */       ctx.method_25294(mx, my, mx + ms, my + ms, LookupColors.withAlpha(-15328732, 200));
/*      */       return;
/*      */     } 
/*      */     try {
/*  860 */       class_2960 speciesId = resolveSpeciesId(species);
/*  861 */       FloatingState state = this.modelStates.computeIfAbsent(stateKey, k -> new FloatingState());
/*  862 */       Set<String> aspects = getAspects(poke);
/*  863 */       state.setCurrentAspects(aspects);
/*      */       
/*  865 */       class_4587 mat = ctx.method_51448();
/*  866 */       mat.method_22903();
/*  867 */       mat.method_22904(mx + ms / 2.0D, my + 2.0D, 0.0D);
/*  868 */       float scale = ms / 9.0F;
/*  869 */       mat.method_22905(scale, scale, 1.0F);
/*  870 */       Quaternionf rot = new Quaternionf();
/*  871 */       rot.rotationXYZ((float)Math.toRadians(13.0D), (float)Math.toRadians(35.0D), 0.0F);
/*  872 */       PokemonRenderHelper.draw(speciesId, mat, rot, state, delta);
/*  873 */       mat.method_22909();
/*  874 */     } catch (Exception e) {
/*  875 */       ctx.method_25294(mx, my, mx + ms, my + ms, LookupColors.withAlpha(-15328732, 200));
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*  880 */   private static final String[] STAT_KEYS = StatIcon.KEYS;
/*  881 */   private static final int[] STAT_COLORS = StatIcon.COLORS;
/*      */   private static final class TooltipLine extends Record { private final String text; private final int color;
/*  883 */     private TooltipLine(String text, int color) { this.text = text; this.color = color; } public final String toString() { // Byte code:
/*      */       //   0: aload_0
/*      */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/lookup/screen/views/LookupSnapshotsView$TooltipLine;)Ljava/lang/String;
/*      */       //   6: areturn
/*      */       // Line number table:
/*      */       //   Java source line number -> byte code offset
/*      */       //   #883	-> 0
/*      */       // Local variable table:
/*      */       //   start	length	slot	name	descriptor
/*  883 */       //   0	7	0	this	Lcom/atlas/common/fabric/lookup/screen/views/LookupSnapshotsView$TooltipLine; } public String text() { return this.text; } public final int hashCode() { // Byte code:
/*      */       //   0: aload_0
/*      */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/lookup/screen/views/LookupSnapshotsView$TooltipLine;)I
/*      */       //   6: ireturn
/*      */       // Line number table:
/*      */       //   Java source line number -> byte code offset
/*      */       //   #883	-> 0
/*      */       // Local variable table:
/*      */       //   start	length	slot	name	descriptor
/*      */       //   0	7	0	this	Lcom/atlas/common/fabric/lookup/screen/views/LookupSnapshotsView$TooltipLine; } public final boolean equals(Object o) { // Byte code:
/*      */       //   0: aload_0
/*      */       //   1: aload_1
/*      */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/lookup/screen/views/LookupSnapshotsView$TooltipLine;Ljava/lang/Object;)Z
/*      */       //   7: ireturn
/*      */       // Line number table:
/*      */       //   Java source line number -> byte code offset
/*      */       //   #883	-> 0
/*      */       // Local variable table:
/*      */       //   start	length	slot	name	descriptor
/*      */       //   0	8	0	this	Lcom/atlas/common/fabric/lookup/screen/views/LookupSnapshotsView$TooltipLine;
/*  883 */       //   0	8	1	o	Ljava/lang/Object; } public int color() { return this.color; }
/*      */      }
/*      */   private void renderTooltips(class_332 ctx, int mouseX, int mouseY, class_327 tr) {
/*  886 */     JsonObject hovered = null;
/*  887 */     if (this.hoveredPartyIndex >= 0 && this.hoveredPartyIndex < this.detailParty.size()) {
/*  888 */       hovered = this.detailParty.get(this.hoveredPartyIndex);
/*  889 */     } else if (this.hoveredPcPokemon != null) {
/*  890 */       hovered = this.hoveredPcPokemon;
/*      */     } 
/*  892 */     if (hovered == null)
/*      */       return; 
/*  894 */     List<TooltipLine> lines = new ArrayList<>();
/*      */     
/*  896 */     String species = getString(hovered, "species");
/*  897 */     lines.add(new TooltipLine("§l" + formatSpeciesName(species), -1));
/*      */     
/*  899 */     lines.add(new TooltipLine("Level: " + getInt(hovered, "level"), -1511169));
/*      */     
/*  901 */     String nature = getString(hovered, "nature");
/*  902 */     if (nature != null && !nature.isEmpty()) {
/*  903 */       lines.add(new TooltipLine("Nature: " + nature, -1511169));
/*      */     }
/*      */ 
/*      */     
/*  907 */     String gender = getString(hovered, "gender");
/*  908 */     String gSym = genderSymbol(gender);
/*  909 */     String genderDisplay = gSym.isEmpty() ? ((gender != null && !gender.isEmpty()) ? gender : "N/A") : gSym;
/*  910 */     lines.add(new TooltipLine("Gender: " + genderDisplay, genderColor(gender)));
/*      */ 
/*      */     
/*  913 */     String heldItem = getString(hovered, "heldItem");
/*  914 */     if (heldItem != null && !heldItem.isEmpty()) {
/*  915 */       String prettyHeld = prettifyName(heldItem);
/*  916 */       lines.add(new TooltipLine("Held: " + prettyHeld, -9930592));
/*      */     } 
/*      */ 
/*      */     
/*  920 */     List<class_2561> propIcons = StatIcon.propertyIcons(hovered);
/*      */ 
/*      */     
/*  923 */     JsonObject ivObj = null;
/*  924 */     if (hovered.has("ivs") && hovered.get("ivs").isJsonObject()) {
/*  925 */       ivObj = hovered.getAsJsonObject("ivs");
/*      */     }
/*      */     
/*  928 */     drawTooltipWithBars(ctx, tr, lines, propIcons, ivObj, mouseX, mouseY);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void drawTooltipWithBars(class_332 ctx, class_327 tr, List<TooltipLine> lines, List<class_2561> propIcons, JsonObject ivObj, int mouseX, int mouseY) {
/*  939 */     if (lines.isEmpty() && ivObj == null)
/*      */       return; 
/*  941 */     int maxTextW = 0;
/*  942 */     for (TooltipLine line : lines) {
/*  943 */       int w = tr.method_1727(line.text());
/*  944 */       if (w > maxTextW) maxTextW = w;
/*      */     
/*      */     } 
/*      */     
/*  948 */     int BAR_SECTION_H = (ivObj != null) ? (STAT_KEYS.length * 10 + 12) : 0;
/*  949 */     int BAR_LABEL_W = 42;
/*  950 */     int BAR_VALUE_W = 18;
/*  951 */     int BAR_FIXED_W = 110;
/*  952 */     int BAR_MIN_W = 178;
/*  953 */     boolean hasPropIcons = (propIcons != null && !propIcons.isEmpty());
/*  954 */     int PROP_ICON_ROW_H = hasPropIcons ? 14 : 0;
/*      */     
/*  956 */     int minWidth = (ivObj != null) ? 188 : 0;
/*  957 */     int tooltipW = Math.max(maxTextW + 10, minWidth);
/*      */     
/*  959 */     int textBlockH = lines.size() * 11 + 10 - 2;
/*  960 */     int separatorH = (ivObj != null) ? 4 : 0;
/*  961 */     int totalH = textBlockH + PROP_ICON_ROW_H + separatorH + BAR_SECTION_H;
/*      */     
/*  963 */     int tx = mouseX + 10;
/*  964 */     int ty = mouseY - totalH - 4;
/*  965 */     if (tx + tooltipW > this.x + this.width) tx = mouseX - tooltipW - 4; 
/*  966 */     if (ty < this.y) ty = mouseY + 14;
/*      */     
/*  968 */     class_4587 matrices = ctx.method_51448();
/*  969 */     matrices.method_22903();
/*  970 */     matrices.method_46416(0.0F, 0.0F, 400.0F);
/*      */     
/*  972 */     ctx.method_25294(tx, ty, tx + tooltipW, ty + totalH, -267383266);
/*  973 */     ctx.method_49601(tx, ty, tooltipW, totalH, -10777105);
/*      */     
/*  975 */     int lineY = ty + 5;
/*  976 */     for (TooltipLine line : lines) {
/*  977 */       ctx.method_51433(tr, line.text(), tx + 5, lineY, line.color(), true);
/*  978 */       lineY += 11;
/*      */     } 
/*      */ 
/*      */     
/*  982 */     if (hasPropIcons) {
/*  983 */       int ix = tx + 5;
/*  984 */       for (class_2561 icon : propIcons) {
/*  985 */         ctx.method_51439(tr, icon, ix, lineY + 1, -1, false);
/*  986 */         ix += tr.method_27525((class_5348)icon) + 3;
/*      */       } 
/*  988 */       lineY += PROP_ICON_ROW_H;
/*      */     } 
/*      */     
/*  991 */     if (ivObj != null) {
/*  992 */       lineY += 2;
/*  993 */       ctx.method_25294(tx + 5, lineY, tx + tooltipW - 5, lineY + 1, 
/*  994 */           LookupColors.withAlpha(-14012352, 160));
/*  995 */       lineY += 3;
/*      */       
/*  997 */       int barX = tx + 5 + 42;
/*  998 */       int barW = 110;
/*  999 */       int MAX_IV = 31;
/* 1000 */       int ivTotal = 0;
/*      */       
/* 1002 */       for (int s = 0; s < STAT_KEYS.length; s++) {
/* 1003 */         int val = ivObj.has(STAT_KEYS[s]) ? ivObj.get(STAT_KEYS[s]).getAsInt() : 0;
/* 1004 */         ivTotal += val;
/*      */ 
/*      */         
/* 1007 */         ctx.method_51439(tr, StatIcon.icon(s), tx + 5, lineY + 2, -1, false);
/* 1008 */         ctx.method_25294(barX, lineY + 2, barX + barW, lineY + 8, -14540254);
/* 1009 */         int fillW = (int)(val / 31.0F * barW);
/* 1010 */         if (fillW > 0) ctx.method_25294(barX, lineY + 2, barX + fillW, lineY + 8, STAT_COLORS[s]); 
/* 1011 */         String valStr = String.valueOf(val);
/* 1012 */         ctx.method_51433(tr, valStr, barX + barW + 3, lineY, -1, true);
/* 1013 */         lineY += 10;
/*      */       } 
/*      */       
/* 1016 */       int pct = (int)(ivTotal * 100.0D / (31 * STAT_KEYS.length));
/* 1017 */       String totalStr = "" + ivTotal + "/" + ivTotal + " (" + 31 * STAT_KEYS.length + "%)";
/* 1018 */       ctx.method_51433(tr, totalStr, tx + 5, lineY + 1, -10777105, true);
/*      */     } 
/*      */     
/* 1021 */     matrices.method_22909();
/*      */   }
/*      */   
/*      */   private void renderFooter(class_332 ctx, int mouseX, int mouseY, class_327 textRenderer) {
/* 1025 */     int footerY = this.y + this.height - 22;
/*      */     
/* 1027 */     if (this.totalEntries > 0 && !this.entries.isEmpty()) {
/* 1028 */       String loadMore = "Load More...";
/* 1029 */       int lmw = textRenderer.method_1727(loadMore);
/* 1030 */       int lmx = this.x + (this.width - lmw) / 2;
/* 1031 */       boolean hover = (mouseX >= lmx && mouseX < lmx + lmw && mouseY >= footerY && mouseY < footerY + 22);
/* 1032 */       ctx.method_51433(textRenderer, loadMore, lmx, footerY + 6, hover ? -10777105 : -9930592, false);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
/* 1040 */     if (this.showingDetail && this.detailScrollArea != null) {
/* 1041 */       return this.detailScrollArea.handleScroll(mouseX, mouseY, verticalAmount);
/*      */     }
/* 1043 */     if (this.scrollArea != null) {
/* 1044 */       return this.scrollArea.handleScroll(mouseX, mouseY, verticalAmount);
/*      */     }
/* 1046 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
/* 1051 */     if (this.showingDetail && this.detailScrollArea != null) return this.detailScrollArea.handleMouseDragged(mouseX, mouseY, button); 
/* 1052 */     if (this.scrollArea != null) return this.scrollArea.handleMouseDragged(mouseX, mouseY, button); 
/* 1053 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean mouseReleased(double mouseX, double mouseY, int button) {
/* 1058 */     if (this.showingDetail && this.detailScrollArea != null) return this.detailScrollArea.handleMouseReleased(mouseX, mouseY, button); 
/* 1059 */     if (this.scrollArea != null) return this.scrollArea.handleMouseReleased(mouseX, mouseY, button); 
/* 1060 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean mouseClicked(double mouseX, double mouseY, int button) {
/* 1065 */     if (button != 0) return false;
/*      */     
/* 1067 */     if (this.showingDetail) {
/* 1068 */       if (this.detailScrollArea != null && this.detailScrollArea.handleMouseClicked(mouseX, mouseY, button)) {
/* 1069 */         return true;
/*      */       }
/*      */       
/* 1072 */       class_327 tr = (class_310.method_1551()).field_1772;
/* 1073 */       String backLabel = "< Back";
/* 1074 */       int backW = tr.method_1727(backLabel) + 8;
/* 1075 */       if (mouseX >= (this.x + 6) && mouseX < (this.x + 6 + backW) && mouseY >= (this.y + 4) && mouseY < (this.y + 22 - 4)) {
/* 1076 */         this.showingDetail = false;
/* 1077 */         this.detailEntry = null;
/* 1078 */         this.confirmingSection = null;
/* 1079 */         this.detailLoaded = false;
/* 1080 */         return true;
/*      */       } 
/*      */       
/* 1083 */       if (this.detailLoaded && this.detailEntry != null) {
/* 1084 */         int scrollOff = this.detailScrollArea.getScrollOffset();
/* 1085 */         int top = this.detailScrollArea.getY();
/* 1086 */         int cy = top - scrollOff + 6;
/* 1087 */         cy += 20;
/* 1088 */         if (this.detailInventory.isEmpty()) {
/* 1089 */           cy += 26;
/*      */         } else {
/* 1091 */           cy += inventoryGridHeight() + 4;
/*      */         } 
/* 1093 */         cy += 20;
/* 1094 */         int partyCards = Math.max(1, this.detailParty.size());
/* 1095 */         int partyCardRows = (partyCards + 5 - 1) / 5;
/* 1096 */         cy += partyCardRows * 53 + 4;
/* 1097 */         cy += 20;
/* 1098 */         cy += getPcBoxesHeight() + 4;
/* 1099 */         cy += 20;
/* 1100 */         cy += 43;
/*      */         
/* 1102 */         int btnX = this.x + 6;
/* 1103 */         int btnY = cy + 2;
/*      */         
/* 1105 */         for (String section : RESTORE_SECTIONS) {
/* 1106 */           if (this.detailEntry.fields().contains(section)) {
/* 1107 */             boolean isConfirming = section.equals(this.confirmingSection);
/* 1108 */             String label = isConfirming ? "Confirm" : ("Restore " + capitalize(section));
/* 1109 */             int btnW = tr.method_1727(label) + 10;
/*      */             
/* 1111 */             if (mouseX >= btnX && mouseX < (btnX + btnW) && mouseY >= btnY && mouseY < (btnY + 16)) {
/* 1112 */               if (isConfirming) {
/* 1113 */                 String targetUuid = LookupClientData.getInstance().getTargetUuid();
/* 1114 */                 String filter = this.detailEntry.id() + ":" + this.detailEntry.id() + ":" + section;
/* 1115 */                 LookupNetwork.requestLookupData("restore", 0, filter);
/* 1116 */                 this.confirmingSection = null;
/* 1117 */                 this.showingDetail = false;
/* 1118 */                 this.detailEntry = null;
/* 1119 */                 this.detailLoaded = false;
/*      */               } else {
/* 1121 */                 this.confirmingSection = section;
/*      */               } 
/* 1123 */               return true;
/*      */             } 
/* 1125 */             btnX += btnW + 4;
/*      */           } 
/*      */         } 
/*      */       } 
/* 1129 */       return false;
/*      */     } 
/*      */     
/* 1132 */     if (this.scrollArea != null && this.scrollArea.handleMouseClicked(mouseX, mouseY, button)) {
/* 1133 */       return true;
/*      */     }
/*      */     
/* 1136 */     if (!this.entries.isEmpty()) {
/* 1137 */       int scrollOff = this.scrollArea.getScrollOffset();
/* 1138 */       int contentTop = this.scrollArea.getY();
/* 1139 */       int rowYCalc = contentTop - scrollOff;
/*      */       
/* 1141 */       for (int i = 0; i < this.entries.size(); i++) {
/* 1142 */         int ex = this.x + 6;
/* 1143 */         int ew = this.width - 12;
/*      */         
/* 1145 */         if (mouseX >= ex && mouseX < (ex + ew) && mouseY >= rowYCalc && mouseY < (rowYCalc + 34)) {
/* 1146 */           openDetailView(this.entries.get(i));
/* 1147 */           return true;
/*      */         } 
/* 1149 */         rowYCalc += 34;
/*      */       } 
/*      */     } 
/*      */     
/* 1153 */     int footerY = this.y + this.height - 22;
/* 1154 */     if (mouseY >= footerY && mouseY < (footerY + 22) && this.totalEntries > 0) {
/* 1155 */       this.currentPage++;
/* 1156 */       String uuid = LookupClientData.getInstance().getTargetUuid();
/* 1157 */       LookupNetwork.requestLookupData("snapshots", this.currentPage, (uuid != null) ? uuid : "");
/* 1158 */       return true;
/*      */     } 
/*      */     
/* 1161 */     return false;
/*      */   }
/*      */   
/*      */   private void openDetailView(SnapshotEntry entry) {
/* 1165 */     this.detailEntry = entry;
/* 1166 */     this.showingDetail = true;
/* 1167 */     this.detailLoaded = false;
/* 1168 */     this.confirmingSection = null;
/* 1169 */     this.detailParty = new ArrayList<>();
/* 1170 */     this.detailPc = new ArrayList<>();
/* 1171 */     this.detailPcBoxes = new ArrayList<>();
/* 1172 */     this.detailInventory = new ArrayList<>();
/* 1173 */     this.detailPokeCoin = 0L;
/* 1174 */     this.detailPokeGem = 0L;
/* 1175 */     this.detailHonorPoint = 0L;
/* 1176 */     this.modelStates.clear();
/*      */     
/* 1178 */     LookupClientData.getInstance().clearSnapshotDetail();
/* 1179 */     this.detailScrollArea.resetScroll();
/* 1180 */     recalcDetailScrollHeight();
/*      */     
/* 1182 */     String targetUuid = LookupClientData.getInstance().getTargetUuid();
/* 1183 */     String filter = entry.id() + ":" + entry.id();
/* 1184 */     LookupNetwork.requestLookupData("snapshot_detail", 0, filter);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static int fieldBadgeColor(String field) {
/* 1190 */     if (field == null) return -9930592; 
/* 1191 */     switch (field.toLowerCase()) { case "party": case "pc": case "inventory": case "currency":  }  return 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1196 */       -9930592;
/*      */   }
/*      */ 
/*      */   
/*      */   private static int changeTypeColor(String changeType) {
/* 1201 */     if (changeType == null) return -9930592; 
/* 1202 */     switch (changeType.toUpperCase()) { case "PERIODIC": case "DAILY_SAVE": case "AUTO": case "MANUAL": case "TRADE": case "ADMIN": case "BATTLE": case "PURCHASE":  }  return 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1209 */       -9930592;
/*      */   }
/*      */ 
/*      */   
/*      */   private static String formatTimestamp(String timestamp) {
/* 1214 */     if (timestamp == null || timestamp.isEmpty()) return ""; 
/* 1215 */     if (timestamp.length() > 16) return timestamp.substring(0, 16).replace('T', ' '); 
/* 1216 */     return timestamp;
/*      */   }
/*      */   
/*      */   private static String capitalize(String s) {
/* 1220 */     if (s == null || s.isEmpty()) return s; 
/* 1221 */     return "" + Character.toUpperCase(s.charAt(0)) + Character.toUpperCase(s.charAt(0));
/*      */   }
/*      */   
/*      */   private static String formatSpeciesName(String species) {
/* 1225 */     if (species == null || species.isEmpty()) return "???"; 
/* 1226 */     String name = species.contains(":") ? species.substring(species.indexOf(':') + 1) : species;
/* 1227 */     StringBuilder sb = new StringBuilder();
/* 1228 */     for (String word : name.replace('_', ' ').split(" ")) {
/* 1229 */       if (!word.isEmpty()) {
/* 1230 */         if (sb.length() > 0) sb.append(' '); 
/* 1231 */         sb.append(Character.toUpperCase(word.charAt(0)));
/* 1232 */         if (word.length() > 1) sb.append(word.substring(1)); 
/*      */       } 
/*      */     } 
/* 1235 */     return sb.toString();
/*      */   }
/*      */   
/*      */   private static class_2960 resolveSpeciesId(String species) {
/* 1239 */     if (species == null) return class_2960.method_60655("cobblemon", "missingno"); 
/* 1240 */     String slug = species.contains(":") ? species.substring(species.indexOf(':') + 1) : species;
/* 1241 */     return class_2960.method_60655("cobblemon", slug);
/*      */   }
/*      */   
/*      */   private static String getString(JsonObject obj, String key) {
/* 1245 */     if (obj == null || !obj.has(key) || obj.get(key).isJsonNull()) return null;  
/* 1246 */     try { return obj.get(key).getAsString(); } catch (Exception e) { return null; }
/*      */   
/*      */   }
/*      */   private static int getInt(JsonObject obj, String key) {
/* 1250 */     if (obj == null || !obj.has(key) || obj.get(key).isJsonNull()) return 0;  
/* 1251 */     try { return obj.get(key).getAsInt(); } catch (Exception e) { return 0; }
/*      */   
/*      */   }
/*      */   private static Set<String> getAspects(JsonObject poke) {
/* 1255 */     Set<String> aspects = new HashSet<>();
/* 1256 */     if (poke != null && poke.has("aspects") && poke.get("aspects").isJsonArray()) {
/* 1257 */       for (JsonElement elem : poke.getAsJsonArray("aspects")) {
/* 1258 */         if (!elem.isJsonNull()) aspects.add(elem.getAsString());
/*      */       
/*      */       } 
/*      */     }
/* 1262 */     if (poke != null && poke.has("shiny") && !poke.get("shiny").isJsonNull()) {
/*      */       try {
/* 1264 */         if (poke.get("shiny").getAsBoolean()) aspects.add("shiny"); 
/* 1265 */       } catch (Exception exception) {}
/*      */     }
/* 1267 */     return aspects;
/*      */   }
/*      */   
/*      */   private static boolean hasAspect(JsonObject poke, String aspect) {
/* 1271 */     return getAspects(poke).contains(aspect);
/*      */   }
/*      */   
/*      */   private int getPcBoxesHeight() {
/* 1275 */     if (this.detailPcBoxes.isEmpty()) {
/* 1276 */       return 50;
/*      */     }
/* 1278 */     int total = 0;
/* 1279 */     for (int i = 0; i < this.detailPcBoxes.size(); i++) {
/* 1280 */       total += getPcBoxHeight(this.detailPcBoxes.get(i));
/* 1281 */       if (i < this.detailPcBoxes.size() - 1) total += 8; 
/*      */     } 
/* 1283 */     return total;
/*      */   }
/*      */   
/*      */   private static int getPcBoxHeight(PcBoxData box) {
/* 1287 */     if (box.isEmpty()) return 16; 
/* 1288 */     return 335;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static class_1799 deserializeStack(String json) {
/*      */     try {
/* 1296 */       JsonElement element = JsonParser.parseString(json);
/* 1297 */       class_6903<JsonElement> ops = class_6903.method_46632((DynamicOps)JsonOps.INSTANCE, 
/* 1298 */           (class_7225.class_7874)(class_310.method_1551()).field_1687.method_30349());
/* 1299 */       return (class_1799)((Pair)class_1799.field_24671.decode((DynamicOps)ops, element).getOrThrow()).getFirst();
/* 1300 */     } catch (Exception e) {
/* 1301 */       return class_1799.field_8037;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static String genderSymbol(String gender) {
/* 1308 */     if (gender == null) return ""; 
/* 1309 */     switch (gender.toUpperCase()) { case "MALE": case "FEMALE":  }  return 
/*      */ 
/*      */       
/* 1312 */       "";
/*      */   }
/*      */ 
/*      */   
/*      */   private static int genderColor(String gender) {
/* 1317 */     if (gender == null) return -9930592; 
/* 1318 */     switch (gender.toUpperCase()) { case "MALE": case "FEMALE":  }  return 
/*      */ 
/*      */       
/* 1321 */       -9930592;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static String prettifyName(String name) {
/* 1327 */     if (name == null || name.isEmpty()) return "?"; 
/* 1328 */     int colon = name.indexOf(':');
/* 1329 */     if (colon >= 0) name = name.substring(colon + 1); 
/* 1330 */     StringBuilder sb = new StringBuilder();
/* 1331 */     for (String word : name.replace('_', ' ').split(" ")) {
/* 1332 */       if (!word.isEmpty()) {
/* 1333 */         if (sb.length() > 0) sb.append(' '); 
/* 1334 */         sb.append(Character.toUpperCase(word.charAt(0)));
/* 1335 */         if (word.length() > 1) sb.append(word.substring(1)); 
/*      */       } 
/*      */     } 
/* 1338 */     return sb.toString();
/*      */   }
/*      */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\lookup\screen\views\LookupSnapshotsView.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */