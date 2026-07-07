/*      */ package com.atlas.common.fabric.lookup.screen.views;
/*      */ 
/*      */ import com.atlas.common.fabric.guide.screen.widgets.GuideScrollableArea;
/*      */ import com.atlas.common.fabric.lookup.data.LookupClientData;
/*      */ import com.atlas.common.fabric.lookup.network.LookupNetwork;
/*      */ import com.atlas.common.fabric.lookup.screen.LookupColors;
/*      */ import com.atlas.common.fabric.lookup.screen.LookupSounds;
/*      */ import com.atlas.common.fabric.lookup.screen.widgets.CalendarPickerWidget;
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
/*      */ import java.util.Comparator;
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
/*      */ 
/*      */ public class LookupLogsView
/*      */   implements LookupView
/*      */ {
/*      */   private int x;
/*      */   private int y;
/*      */   private int width;
/*      */   private int height;
/*      */   private boolean dataRequested = false;
/*      */   private boolean dataParsed = false;
/*   48 */   private int currentPage = 0;
/*   49 */   private int highestLoadedPage = -1;
/*      */   
/*      */   private boolean requestInFlight = false;
/*      */   private boolean hasMorePages = true;
/*      */   private GuideScrollableArea scrollArea;
/*   54 */   private final Runnable dataListener = this::onDataUpdated;
/*      */   
/*   56 */   private final List<LogEntry> entries = new ArrayList<>();
/*   57 */   private int totalLogs = 0;
/*      */ 
/*      */   
/*   60 */   private static final String[] FILTERS = new String[] { "All", "Chat", "Commands", "PMs", "Pay", "Auction", "Reroll", "Crates", "Drops" };
/*   61 */   private static final String[] FILTER_KEYS = new String[] { "all", "chat", "commands", "pms", "pay", "auction", "reroll", "crate", "drops" };
/*   62 */   private int activeFilter = 0;
/*      */ 
/*      */   
/*      */   private CalendarPickerWidget calendarPicker;
/*      */   
/*   67 */   private long dateFromMillis = -1L;
/*   68 */   private long dateToMillis = -1L;
/*      */ 
/*      */   
/*      */   private static final int DATE_BAR_HEIGHT = 0;
/*      */   
/*   73 */   private int hoveredRowIndex = -1;
/*      */   
/*   75 */   private LogEntry hoveredIvRerollEntry = null;
/*   76 */   private int hoveredIvRerollMouseX = 0;
/*   77 */   private int hoveredIvRerollMouseY = 0;
/*      */ 
/*      */   
/*      */   private class_327 cachedTextRenderer;
/*      */ 
/*      */   
/*      */   private int[] tabXPositions;
/*      */   
/*      */   private int[] tabWidths;
/*      */   
/*   87 */   private String searchText = "";
/*      */   
/*      */   private boolean searchFocused = false;
/*      */   
/*      */   private static final int SEARCH_HEIGHT = 16;
/*      */   
/*      */   private static final int ROW_HEIGHT = 26;
/*      */   
/*      */   private static final int PADDING = 6;
/*      */   
/*      */   private static final int HEADER_HEIGHT = 22;
/*      */   private static final int FILTER_HEIGHT = 20;
/*      */   private static final int FOOTER_HEIGHT = 22;
/*      */   private static final int ENTRIES_PER_PAGE = 50;
/*      */   private static final int LOAD_MORE_THRESHOLD = 52;
/*      */   private static final int POKEMON_MODEL_SIZE = 22;
/*      */   private static final int POKEMON_CELL_W = 24;
/*  104 */   private final Map<String, FloatingState> modelStates = new HashMap<>();
/*      */ 
/*      */ 
/*      */   
/*  108 */   private JsonObject hoveredPokemonJson = null;
/*  109 */   private int hoveredPokemonMouseX = 0;
/*  110 */   private int hoveredPokemonMouseY = 0;
/*      */   private static final class LogEntry extends Record {
/*      */     private final String timestamp; private final String type; private final String content; private final class_1799 stack; private final List<class_1799> extraStacks; private final List<class_1799> rewardStacks; private final String otherPlayerUuid; private final String otherPlayerName; private final String rerollStat;
/*      */     private final int rerollOldValue;
/*      */     private final int rerollNewValue;
/*      */     private final JsonObject primaryPokemon;
/*      */     private final List<JsonObject> extraPokemon;
/*      */     private final List<JsonObject> rewardPokemon;
/*      */     private final LookupLogsView.AuctionData auction;
/*      */     private final String rerollSubtype;
/*      */     private final String crateSourceType;
/*      */     private final String crateSourceDisplayName;
/*      */     
/*  123 */     private LogEntry(String timestamp, String type, String content, class_1799 stack, List<class_1799> extraStacks, List<class_1799> rewardStacks, String otherPlayerUuid, String otherPlayerName, String rerollStat, int rerollOldValue, int rerollNewValue, JsonObject primaryPokemon, List<JsonObject> extraPokemon, List<JsonObject> rewardPokemon, LookupLogsView.AuctionData auction, String rerollSubtype, String crateSourceType, String crateSourceDisplayName) { this.timestamp = timestamp; this.type = type; this.content = content; this.stack = stack; this.extraStacks = extraStacks; this.rewardStacks = rewardStacks; this.otherPlayerUuid = otherPlayerUuid; this.otherPlayerName = otherPlayerName; this.rerollStat = rerollStat; this.rerollOldValue = rerollOldValue; this.rerollNewValue = rerollNewValue; this.primaryPokemon = primaryPokemon; this.extraPokemon = extraPokemon; this.rewardPokemon = rewardPokemon; this.auction = auction; this.rerollSubtype = rerollSubtype; this.crateSourceType = crateSourceType; this.crateSourceDisplayName = crateSourceDisplayName; } public final String toString() { // Byte code:
/*      */       //   0: aload_0
/*      */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/lookup/screen/views/LookupLogsView$LogEntry;)Ljava/lang/String;
/*      */       //   6: areturn
/*      */       // Line number table:
/*      */       //   Java source line number -> byte code offset
/*      */       //   #123	-> 0
/*      */       // Local variable table:
/*      */       //   start	length	slot	name	descriptor
/*      */       //   0	7	0	this	Lcom/atlas/common/fabric/lookup/screen/views/LookupLogsView$LogEntry; } public final int hashCode() { // Byte code:
/*      */       //   0: aload_0
/*      */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/lookup/screen/views/LookupLogsView$LogEntry;)I
/*      */       //   6: ireturn
/*      */       // Line number table:
/*      */       //   Java source line number -> byte code offset
/*      */       //   #123	-> 0
/*      */       // Local variable table:
/*      */       //   start	length	slot	name	descriptor
/*      */       //   0	7	0	this	Lcom/atlas/common/fabric/lookup/screen/views/LookupLogsView$LogEntry; } public final boolean equals(Object o) { // Byte code:
/*      */       //   0: aload_0
/*      */       //   1: aload_1
/*      */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/lookup/screen/views/LookupLogsView$LogEntry;Ljava/lang/Object;)Z
/*      */       //   7: ireturn
/*      */       // Line number table:
/*      */       //   Java source line number -> byte code offset
/*      */       //   #123	-> 0
/*      */       // Local variable table:
/*      */       //   start	length	slot	name	descriptor
/*      */       //   0	8	0	this	Lcom/atlas/common/fabric/lookup/screen/views/LookupLogsView$LogEntry;
/*  123 */       //   0	8	1	o	Ljava/lang/Object; } public String timestamp() { return this.timestamp; } public String type() { return this.type; } public String content() { return this.content; } public class_1799 stack() { return this.stack; } public List<class_1799> extraStacks() { return this.extraStacks; } public List<class_1799> rewardStacks() { return this.rewardStacks; } public String otherPlayerUuid() { return this.otherPlayerUuid; } public String otherPlayerName() { return this.otherPlayerName; } public String rerollStat() { return this.rerollStat; } public int rerollOldValue() { return this.rerollOldValue; } public int rerollNewValue() { return this.rerollNewValue; } public JsonObject primaryPokemon() { return this.primaryPokemon; } public List<JsonObject> extraPokemon() { return this.extraPokemon; } public List<JsonObject> rewardPokemon() { return this.rewardPokemon; } public LookupLogsView.AuctionData auction() { return this.auction; } public String rerollSubtype() { return this.rerollSubtype; } public String crateSourceType() { return this.crateSourceType; } public String crateSourceDisplayName() { return this.crateSourceDisplayName; }
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static final class AuctionData
/*      */     extends Record
/*      */   {
/*      */     private final String action;
/*      */ 
/*      */     
/*      */     private final String listingType;
/*      */ 
/*      */     
/*      */     private final String currency;
/*      */ 
/*      */     
/*      */     private final long price;
/*      */ 
/*      */     
/*      */     private final String sellerName;
/*      */ 
/*      */     
/*      */     private final String buyerName;
/*      */ 
/*      */     
/*      */     private final String itemName;
/*      */ 
/*      */ 
/*      */     
/*      */     private AuctionData(String action, String listingType, String currency, long price, String sellerName, String buyerName, String itemName) {
/*  155 */       this.action = action; this.listingType = listingType; this.currency = currency; this.price = price; this.sellerName = sellerName; this.buyerName = buyerName; this.itemName = itemName; } public final String toString() { // Byte code:
/*      */       //   0: aload_0
/*      */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/lookup/screen/views/LookupLogsView$AuctionData;)Ljava/lang/String;
/*      */       //   6: areturn
/*      */       // Line number table:
/*      */       //   Java source line number -> byte code offset
/*      */       //   #155	-> 0
/*      */       // Local variable table:
/*      */       //   start	length	slot	name	descriptor
/*      */       //   0	7	0	this	Lcom/atlas/common/fabric/lookup/screen/views/LookupLogsView$AuctionData; } public final int hashCode() { // Byte code:
/*      */       //   0: aload_0
/*      */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/lookup/screen/views/LookupLogsView$AuctionData;)I
/*      */       //   6: ireturn
/*      */       // Line number table:
/*      */       //   Java source line number -> byte code offset
/*      */       //   #155	-> 0
/*      */       // Local variable table:
/*      */       //   start	length	slot	name	descriptor
/*      */       //   0	7	0	this	Lcom/atlas/common/fabric/lookup/screen/views/LookupLogsView$AuctionData; } public final boolean equals(Object o) { // Byte code:
/*      */       //   0: aload_0
/*      */       //   1: aload_1
/*      */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/lookup/screen/views/LookupLogsView$AuctionData;Ljava/lang/Object;)Z
/*      */       //   7: ireturn
/*      */       // Line number table:
/*      */       //   Java source line number -> byte code offset
/*      */       //   #155	-> 0
/*      */       // Local variable table:
/*      */       //   start	length	slot	name	descriptor
/*      */       //   0	8	0	this	Lcom/atlas/common/fabric/lookup/screen/views/LookupLogsView$AuctionData;
/*  155 */       //   0	8	1	o	Ljava/lang/Object; } public String action() { return this.action; } public String listingType() { return this.listingType; } public String currency() { return this.currency; } public long price() { return this.price; } public String sellerName() { return this.sellerName; } public String buyerName() { return this.buyerName; } public String itemName() { return this.itemName; }
/*      */   
/*      */   }
/*      */   
/*      */   private static final class NameRegion extends Record {
/*      */     private final int rx;
/*      */     private final int ry;
/*      */     private final int rw;
/*      */     private final int rh;
/*      */     private final String playerName;
/*      */     
/*  166 */     private NameRegion(int rx, int ry, int rw, int rh, String playerName) { this.rx = rx; this.ry = ry; this.rw = rw; this.rh = rh; this.playerName = playerName; } public final String toString() { // Byte code:
/*      */       //   0: aload_0
/*      */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/lookup/screen/views/LookupLogsView$NameRegion;)Ljava/lang/String;
/*      */       //   6: areturn
/*      */       // Line number table:
/*      */       //   Java source line number -> byte code offset
/*      */       //   #166	-> 0
/*      */       // Local variable table:
/*      */       //   start	length	slot	name	descriptor
/*      */       //   0	7	0	this	Lcom/atlas/common/fabric/lookup/screen/views/LookupLogsView$NameRegion; } public final int hashCode() { // Byte code:
/*      */       //   0: aload_0
/*      */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/lookup/screen/views/LookupLogsView$NameRegion;)I
/*      */       //   6: ireturn
/*      */       // Line number table:
/*      */       //   Java source line number -> byte code offset
/*      */       //   #166	-> 0
/*      */       // Local variable table:
/*      */       //   start	length	slot	name	descriptor
/*      */       //   0	7	0	this	Lcom/atlas/common/fabric/lookup/screen/views/LookupLogsView$NameRegion; } public final boolean equals(Object o) { // Byte code:
/*      */       //   0: aload_0
/*      */       //   1: aload_1
/*      */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/lookup/screen/views/LookupLogsView$NameRegion;Ljava/lang/Object;)Z
/*      */       //   7: ireturn
/*      */       // Line number table:
/*      */       //   Java source line number -> byte code offset
/*      */       //   #166	-> 0
/*      */       // Local variable table:
/*      */       //   start	length	slot	name	descriptor
/*      */       //   0	8	0	this	Lcom/atlas/common/fabric/lookup/screen/views/LookupLogsView$NameRegion;
/*  166 */       //   0	8	1	o	Ljava/lang/Object; } public int rx() { return this.rx; } public int ry() { return this.ry; } public int rw() { return this.rw; } public int rh() { return this.rh; } public String playerName() { return this.playerName; }
/*  167 */      } private final List<NameRegion> nameRegions = new ArrayList<>();
/*      */   public LookupLogsView() {
/*      */     
/*  170 */     try { PokemonRenderHelper.init(); } catch (Throwable throwable) {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void init(int x, int y, int width, int height) {
/*  177 */     this.x = x;
/*  178 */     this.y = y;
/*  179 */     this.width = width;
/*  180 */     this.height = height;
/*      */ 
/*      */     
/*  183 */     int calW = (int)(width * 0.4D) - 6;
/*  184 */     int calX = x + width - calW - 6;
/*  185 */     int calY = y + 22 + 20 + 2;
/*  186 */     if (this.calendarPicker == null) {
/*  187 */       this.calendarPicker = new CalendarPickerWidget(calX, calY, calW, 16);
/*  188 */       this.calendarPicker.setListener((from, to) -> {
/*      */             this.dateFromMillis = from;
/*      */             
/*      */             this.dateToMillis = to;
/*      */             resetAndRequestFirstPage();
/*      */           });
/*      */     } else {
/*  195 */       this.calendarPicker = new CalendarPickerWidget(calX, calY, calW, 16);
/*  196 */       this.calendarPicker.setListener((from, to) -> {
/*      */             this.dateFromMillis = from;
/*      */             this.dateToMillis = to;
/*      */             resetAndRequestFirstPage();
/*      */           });
/*      */     } 
/*  202 */     this.calendarPicker.setCommittedRange(this.dateFromMillis, this.dateToMillis);
/*      */     
/*  204 */     int contentTop = y + 22 + 20 + 16 + 4;
/*  205 */     int contentHeight = height - 22 - 20 - 16 - 4 - 22;
/*  206 */     if (this.scrollArea == null) {
/*  207 */       this.scrollArea = new GuideScrollableArea(x, contentTop, width, contentHeight);
/*  208 */       this.scrollArea.setThumbColors(-12958368, -10777105);
/*      */     } else {
/*  210 */       this.scrollArea.updateBounds(x, contentTop, width, contentHeight);
/*      */     } 
/*      */     
/*  213 */     LookupClientData data = LookupClientData.getInstance();
/*  214 */     data.removeListener(this.dataListener);
/*  215 */     data.addListener(this.dataListener);
/*      */     
/*  217 */     if (!this.dataRequested) {
/*  218 */       this.dataRequested = true;
/*  219 */       resetAndRequestFirstPage();
/*      */     } 
/*      */   }
/*      */   
/*      */   private String buildRequestFilter() {
/*  224 */     String uuid = LookupClientData.getInstance().getTargetUuid();
/*  225 */     String filter = FILTER_KEYS[this.activeFilter];
/*  226 */     if (uuid != null) filter = filter + ":" + filter; 
/*  227 */     if (!this.searchText.isEmpty()) filter = filter + ":search=" + filter;
/*      */     
/*  229 */     if (this.dateFromMillis >= 0L) {
/*  230 */       filter = filter + ":date_from=" + filter;
/*      */     }
/*  232 */     if (this.dateToMillis >= 0L) {
/*  233 */       filter = filter + ":date_to=" + filter;
/*      */     }
/*  235 */     return filter;
/*      */   }
/*      */   
/*      */   private void requestData(int page) {
/*  239 */     this.currentPage = Math.max(0, page);
/*  240 */     this.requestInFlight = true;
/*  241 */     LookupNetwork.requestLookupData("logs", this.currentPage, buildRequestFilter());
/*      */   }
/*      */   
/*      */   private void resetAndRequestFirstPage() {
/*  245 */     this.currentPage = 0;
/*  246 */     this.highestLoadedPage = -1;
/*  247 */     this.hasMorePages = true;
/*  248 */     this.requestInFlight = false;
/*  249 */     this.dataParsed = false;
/*  250 */     this.entries.clear();
/*  251 */     if (this.scrollArea != null) {
/*  252 */       this.scrollArea.setContentHeight(6);
/*  253 */       this.scrollArea.resetScroll();
/*      */     } 
/*  255 */     requestData(0);
/*      */   }
/*      */   
/*      */   private void maybeRequestMore() {
/*  259 */     if (this.scrollArea == null || this.requestInFlight || !this.hasMorePages)
/*      */       return; 
/*  261 */     int remainingScroll = this.scrollArea.getMaxScroll() - this.scrollArea.getScrollOffset();
/*  262 */     if (this.scrollArea.getMaxScroll() == 0 || remainingScroll <= 52) {
/*  263 */       requestData(this.highestLoadedPage + 1);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void onDataUpdated() {
/*  270 */     this.dataParsed = false;
/*      */   }
/*      */   
/*      */   private void parseData() {
/*  274 */     LookupClientData data = LookupClientData.getInstance();
/*  275 */     String json = data.getLogsJson();
/*  276 */     this.totalLogs = data.getLogsTotal();
/*  277 */     int incomingPage = data.getLogsPage();
/*      */     
/*  279 */     if (json == null || json.isEmpty()) {
/*  280 */       this.requestInFlight = false;
/*  281 */       this.dataParsed = true;
/*      */       
/*      */       return;
/*      */     } 
/*  285 */     if (incomingPage > 0 && incomingPage <= this.highestLoadedPage) {
/*  286 */       this.requestInFlight = false;
/*  287 */       this.dataParsed = true;
/*      */       
/*      */       return;
/*      */     } 
/*  291 */     List<LogEntry> pageEntries = new ArrayList<>();
/*      */     try {
/*  293 */       JsonArray arr = JsonParser.parseString(json).getAsJsonArray();
/*  294 */       for (int i = 0; i < arr.size(); i++) {
/*  295 */         JsonObject obj = arr.get(i).getAsJsonObject();
/*  296 */         String timestamp = obj.has("timestamp") ? obj.get("timestamp").getAsString() : "";
/*  297 */         String type = obj.has("type") ? obj.get("type").getAsString() : "chat";
/*  298 */         String content = obj.has("content") ? obj.get("content").getAsString() : "";
/*      */ 
/*      */         
/*  301 */         class_1799 stack = class_1799.field_8037;
/*  302 */         if (obj.has("stackJson")) {
/*  303 */           stack = deserializeStack(obj.get("stackJson").getAsString());
/*  304 */         } else if (obj.has("pokemonStackJson")) {
/*  305 */           stack = deserializeStack(obj.get("pokemonStackJson").getAsString());
/*  306 */         } else if (obj.has("resultStackJson")) {
/*  307 */           stack = deserializeStack(obj.get("resultStackJson").getAsString());
/*      */         } 
/*      */ 
/*      */         
/*  311 */         JsonObject primaryPokemon = null;
/*  312 */         if (obj.has("pokemonJson") && obj.get("pokemonJson").isJsonObject()) {
/*  313 */           primaryPokemon = obj.getAsJsonObject("pokemonJson");
/*  314 */         } else if (obj.has("resultPokemonJson") && obj.get("resultPokemonJson").isJsonObject()) {
/*  315 */           primaryPokemon = obj.getAsJsonObject("resultPokemonJson");
/*      */         } 
/*      */ 
/*      */         
/*  319 */         List<class_1799> extraStacks = new ArrayList<>();
/*  320 */         List<JsonObject> extraPokemon = new ArrayList<>();
/*      */ 
/*      */         
/*  323 */         JsonArray sacrificedPokemonArr = (obj.has("sacrificedPokemonJsons") && obj.get("sacrificedPokemonJsons").isJsonArray()) ? obj.getAsJsonArray("sacrificedPokemonJsons") : null;
/*  324 */         if (obj.has("sacrificedStackJsons") && obj.get("sacrificedStackJsons").isJsonArray()) {
/*  325 */           JsonArray arrStacks = obj.getAsJsonArray("sacrificedStackJsons");
/*  326 */           for (int si = 0; si < arrStacks.size(); si++) {
/*      */             try {
/*  328 */               class_1799 s = deserializeStack(arrStacks.get(si).getAsString());
/*  329 */               if (!s.method_7960())
/*  330 */               { extraStacks.add(s);
/*  331 */                 JsonObject pj = null;
/*  332 */                 if (sacrificedPokemonArr != null && si < sacrificedPokemonArr.size()) {
/*  333 */                   JsonElement el = sacrificedPokemonArr.get(si);
/*  334 */                   if (el != null && el.isJsonObject()) pj = el.getAsJsonObject(); 
/*      */                 } 
/*  336 */                 extraPokemon.add(pj); } 
/*  337 */             } catch (Exception exception) {}
/*      */           } 
/*      */         } 
/*      */         
/*  341 */         List<class_1799> rewardStacks = new ArrayList<>();
/*  342 */         List<JsonObject> rewardPokemon = new ArrayList<>();
/*      */ 
/*      */         
/*  345 */         JsonArray rewardPokemonArr = (obj.has("rewardPokemonJsons") && obj.get("rewardPokemonJsons").isJsonArray()) ? obj.getAsJsonArray("rewardPokemonJsons") : null;
/*  346 */         if (obj.has("rewardStackJsons") && obj.get("rewardStackJsons").isJsonArray()) {
/*  347 */           JsonArray arrStacks = obj.getAsJsonArray("rewardStackJsons");
/*  348 */           for (int ri = 0; ri < arrStacks.size(); ri++) {
/*      */             try {
/*  350 */               class_1799 s = deserializeStack(arrStacks.get(ri).getAsString());
/*  351 */               if (!s.method_7960())
/*  352 */               { rewardStacks.add(s);
/*  353 */                 JsonObject pj = null;
/*  354 */                 if (rewardPokemonArr != null && ri < rewardPokemonArr.size()) {
/*  355 */                   JsonElement el = rewardPokemonArr.get(ri);
/*  356 */                   if (el != null && el.isJsonObject()) pj = el.getAsJsonObject(); 
/*      */                 } 
/*  358 */                 rewardPokemon.add(pj); } 
/*  359 */             } catch (Exception exception) {}
/*      */           } 
/*      */         } 
/*      */         
/*  363 */         String otherUuid = obj.has("otherPlayerUuid") ? obj.get("otherPlayerUuid").getAsString() : null;
/*  364 */         String otherName = obj.has("otherPlayerName") ? obj.get("otherPlayerName").getAsString() : null;
/*      */ 
/*      */         
/*  367 */         String rerollStat = obj.has("rerollStat") ? obj.get("rerollStat").getAsString() : null;
/*  368 */         int rerollOld = obj.has("rerollOldValue") ? obj.get("rerollOldValue").getAsInt() : -1;
/*  369 */         int rerollNew = obj.has("rerollNewValue") ? obj.get("rerollNewValue").getAsInt() : -1;
/*      */ 
/*      */         
/*  372 */         AuctionData auctionData = null;
/*  373 */         if ("auction".equalsIgnoreCase(type) && obj.has("auctionAction")) {
/*  374 */           String action = obj.get("auctionAction").getAsString();
/*  375 */           String listingType = obj.has("listingType") ? obj.get("listingType").getAsString() : null;
/*  376 */           String currency = obj.has("currency") ? obj.get("currency").getAsString() : null;
/*  377 */           long price = obj.has("price") ? obj.get("price").getAsLong() : -1L;
/*  378 */           String sellerName = obj.has("sellerName") ? obj.get("sellerName").getAsString() : null;
/*  379 */           String buyerName = obj.has("buyerName") ? obj.get("buyerName").getAsString() : null;
/*  380 */           String itemName = obj.has("itemName") ? obj.get("itemName").getAsString() : "";
/*  381 */           auctionData = new AuctionData(action, listingType, currency, price, sellerName, buyerName, itemName);
/*      */         } 
/*      */         
/*  384 */         String rerollSubtype = obj.has("rerollSubtype") ? obj.get("rerollSubtype").getAsString() : null;
/*  385 */         String crateSourceType = obj.has("crateSourceType") ? obj.get("crateSourceType").getAsString() : null;
/*  386 */         String crateSourceDisplayName = obj.has("crateSourceDisplayName") ? obj.get("crateSourceDisplayName").getAsString() : null;
/*      */         
/*  388 */         pageEntries.add(new LogEntry(timestamp, type, content, stack, extraStacks, rewardStacks, otherUuid, otherName, rerollStat, rerollOld, rerollNew, primaryPokemon, extraPokemon, rewardPokemon, auctionData, rerollSubtype, crateSourceType, crateSourceDisplayName));
/*      */       }
/*      */     
/*      */     }
/*  392 */     catch (Exception exception) {}
/*      */     
/*  394 */     if (incomingPage == 0) {
/*  395 */       this.entries.clear();
/*  396 */       if (this.scrollArea != null) this.scrollArea.resetScroll(); 
/*      */     } 
/*  398 */     this.entries.addAll(pageEntries);
/*      */ 
/*      */     
/*  401 */     if (this.activeFilter == 0 && !this.entries.isEmpty()) {
/*  402 */       this.entries.sort(Comparator.comparing(LogEntry::timestamp, Comparator.nullsLast(Comparator.reverseOrder())));
/*      */     }
/*      */     
/*  405 */     this.highestLoadedPage = Math.max(this.highestLoadedPage, incomingPage);
/*  406 */     this.currentPage = this.highestLoadedPage;
/*  407 */     this.hasMorePages = !pageEntries.isEmpty();
/*  408 */     this.requestInFlight = false;
/*  409 */     this.dataParsed = true;
/*  410 */     this.scrollArea.setContentHeight(this.entries.size() * 26 + 6);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static class_1799 deserializeStack(String json) {
/*      */     try {
/*  417 */       JsonElement element = JsonParser.parseString(json);
/*  418 */       class_6903<JsonElement> ops = class_6903.method_46632((DynamicOps)JsonOps.INSTANCE, 
/*  419 */           (class_7225.class_7874)(class_310.method_1551()).field_1687.method_30349());
/*  420 */       return (class_1799)((Pair)class_1799.field_24671.decode((DynamicOps)ops, element).getOrThrow()).getFirst();
/*  421 */     } catch (Exception e) {
/*  422 */       return class_1799.field_8037;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void render(class_332 ctx, int mouseX, int mouseY, float delta, class_327 textRenderer) {
/*  430 */     this.cachedTextRenderer = textRenderer;
/*      */     
/*  432 */     if (!this.dataParsed) {
/*  433 */       parseData();
/*      */     }
/*  435 */     maybeRequestMore();
/*      */ 
/*      */     
/*  438 */     ctx.method_51433(textRenderer, "§lLogs", this.x + 6, this.y + 6, -10777105, false);
/*      */ 
/*      */     
/*  441 */     if (this.totalLogs > 0) {
/*  442 */       String countLabel = "" + this.totalLogs + " entries";
/*  443 */       int clw = textRenderer.method_1727(countLabel);
/*  444 */       ctx.method_51433(textRenderer, countLabel, this.x + this.width - clw - 6, this.y + 6, -9930592, false);
/*      */     } 
/*      */ 
/*      */     
/*  448 */     renderFilterTabs(ctx, mouseX, mouseY, textRenderer);
/*      */ 
/*      */     
/*  451 */     renderSearchBar(ctx, mouseX, mouseY, textRenderer);
/*  452 */     if (this.calendarPicker != null) {
/*  453 */       this.calendarPicker.render(ctx, mouseX, mouseY, textRenderer);
/*      */     }
/*      */     
/*  456 */     if (this.entries.isEmpty()) {
/*      */ 
/*      */       
/*  459 */       String msg = (this.requestInFlight || LookupClientData.getInstance().getLogsJson() == null) ? "Loading..." : "No log entries found";
/*  460 */       int tw = textRenderer.method_1727(msg);
/*  461 */       ctx.method_51433(textRenderer, msg, this.x + (this.width - tw) / 2, this.y + this.height / 2, -9930592, false);
/*  462 */       renderFooter(ctx, mouseX, mouseY, textRenderer);
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*  467 */     this.scrollArea.beginRender(ctx);
/*      */     
/*  469 */     int contentTop = this.scrollArea.getY();
/*  470 */     int scrollOff = this.scrollArea.getScrollOffset();
/*  471 */     this.hoveredRowIndex = -1;
/*  472 */     this.nameRegions.clear();
/*      */ 
/*      */     
/*  475 */     class_1799 hoveredItemStack = class_1799.field_8037;
/*  476 */     int hoveredItemTooltipX = mouseX;
/*  477 */     int hoveredItemTooltipY = mouseY;
/*      */     
/*  479 */     this.hoveredIvRerollEntry = null;
/*  480 */     this.hoveredPokemonJson = null;
/*      */     
/*  482 */     for (int i = 0; i < this.entries.size(); i++) {
/*  483 */       LogEntry entry = this.entries.get(i);
/*  484 */       int rowY = contentTop + i * 26 - scrollOff;
/*      */       
/*  486 */       if (rowY + 26 >= this.scrollArea.getY() && rowY <= this.scrollArea.getY() + this.scrollArea.getHeight()) {
/*      */         int badgeColor;
/*      */         String badge;
/*  489 */         if (i % 2 == 0) {
/*  490 */           ctx.method_25294(this.x, rowY, this.x + this.width, rowY + 26, -14801866);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  496 */         boolean rowHovered = (mouseX >= this.x && mouseX < this.x + this.width && mouseY >= rowY && mouseY < rowY + 26 && mouseY >= this.scrollArea.getY() && mouseY < this.scrollArea.getY() + this.scrollArea.getHeight());
/*  497 */         if (rowHovered) {
/*  498 */           this.hoveredRowIndex = i;
/*  499 */           ctx.method_25294(this.x, rowY, this.x + this.width, rowY + 26, -14274480);
/*  500 */           ctx.method_25294(this.x, rowY, this.x + 2, rowY + 26, -10777105);
/*      */         } 
/*      */         
/*  503 */         int textY = rowY + 8;
/*  504 */         int cx = this.x + 6 + (rowHovered ? 2 : 0);
/*      */ 
/*      */         
/*  507 */         String ts = formatTimestampShort(entry.timestamp());
/*  508 */         ctx.method_51433(textRenderer, ts, cx, textY, -9930592, false);
/*  509 */         cx += textRenderer.method_1727(ts) + 6;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  516 */         boolean isAuctionRow = (entry.auction() != null);
/*  517 */         boolean isRerollRow = "reroll".equalsIgnoreCase(entry.type());
/*  518 */         boolean isCrateRow = "crate".equalsIgnoreCase(entry.type());
/*      */ 
/*      */         
/*  521 */         if (isAuctionRow) {
/*  522 */           badgeColor = auctionActionColor(entry.auction().action());
/*  523 */           badge = entry.auction().action();
/*  524 */         } else if (isRerollRow) {
/*  525 */           String sub = entry.rerollSubtype();
/*  526 */           if ("LEGENDARY".equalsIgnoreCase(sub)) {
/*  527 */             badgeColor = -6596170;
/*  528 */             badge = "LEGENDARY_REROLL";
/*      */           } else {
/*  530 */             badgeColor = -1499549;
/*  531 */             badge = "IV_REROLL";
/*      */           } 
/*  533 */         } else if (isCrateRow) {
/*  534 */           badgeColor = crateSourceBadgeColor(entry.crateSourceType());
/*  535 */           badge = crateSourceBadge(entry.crateSourceType());
/*      */         } else {
/*  537 */           badgeColor = typeBadgeColor(entry.type());
/*  538 */           badge = formatTypeBadge(entry.type());
/*      */         } 
/*  540 */         int badgeW = textRenderer.method_1727(badge) + 6;
/*  541 */         ctx.method_25294(cx, rowY + 2, cx + badgeW, rowY + 26 - 2, LookupColors.withAlpha(badgeColor, 50));
/*  542 */         ctx.method_51433(textRenderer, badge, cx + 3, textY, badgeColor, false);
/*  543 */         cx += badgeW + 6;
/*      */ 
/*      */         
/*  546 */         boolean hasStack = !entry.stack().method_7960();
/*  547 */         boolean hasExtraStacks = !entry.extraStacks().isEmpty();
/*  548 */         boolean hasRewardStacks = !entry.rewardStacks().isEmpty();
/*  549 */         boolean isReroll = "reroll".equalsIgnoreCase(entry.type());
/*  550 */         boolean isLegendaryReroll = (isReroll && hasExtraStacks);
/*  551 */         boolean isIvReroll = (isReroll && hasStack && !hasExtraStacks);
/*      */         
/*  553 */         if (isLegendaryReroll) {
/*      */           
/*  555 */           for (int si = 0; si < Math.min(entry.extraStacks().size(), 3); si++) {
/*  556 */             class_1799 es = entry.extraStacks().get(si);
/*  557 */             JsonObject ep = (si < entry.extraPokemon().size()) ? entry.extraPokemon().get(si) : null;
/*  558 */             int consumed = renderPokemonOrItemCell(ctx, textRenderer, es, ep, cx, rowY, mouseX, mouseY, delta, "log_" + i + "_sac_" + si);
/*      */ 
/*      */             
/*  561 */             if (mouseInCell(mouseX, mouseY, cx, rowY, consumed)) {
/*  562 */               if (ep != null) {
/*  563 */                 this.hoveredPokemonJson = ep;
/*  564 */                 this.hoveredPokemonMouseX = mouseX;
/*  565 */                 this.hoveredPokemonMouseY = mouseY;
/*      */               } else {
/*  567 */                 hoveredItemStack = es;
/*  568 */                 hoveredItemTooltipX = mouseX;
/*  569 */                 hoveredItemTooltipY = mouseY;
/*      */               } 
/*      */             }
/*  572 */             cx += consumed;
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/*  577 */           int arrowY = rowY + 8 + 2;
/*  578 */           ctx.method_51433(textRenderer, "→", cx, arrowY, -9930592, false);
/*  579 */           cx += textRenderer.method_1727("→") + 4;
/*      */           
/*  581 */           if (hasStack) {
/*  582 */             int consumed = renderPokemonOrItemCell(ctx, textRenderer, entry.stack(), entry.primaryPokemon(), cx, rowY, mouseX, mouseY, delta, "log_" + i + "_res");
/*      */             
/*  584 */             if (mouseInCell(mouseX, mouseY, cx, rowY, consumed)) {
/*  585 */               if (entry.primaryPokemon() != null) {
/*  586 */                 this.hoveredPokemonJson = entry.primaryPokemon();
/*  587 */                 this.hoveredPokemonMouseX = mouseX;
/*  588 */                 this.hoveredPokemonMouseY = mouseY;
/*      */               } else {
/*  590 */                 hoveredItemStack = entry.stack();
/*  591 */                 hoveredItemTooltipX = mouseX;
/*  592 */                 hoveredItemTooltipY = mouseY;
/*      */               } 
/*      */             }
/*  595 */             cx += consumed;
/*      */           } 
/*  597 */         } else if (isIvReroll) {
/*      */           
/*  599 */           int consumed = renderPokemonOrItemCell(ctx, textRenderer, entry.stack(), entry.primaryPokemon(), cx, rowY, mouseX, mouseY, delta, "log_" + i + "_iv");
/*      */           
/*  601 */           if (mouseInCell(mouseX, mouseY, cx, rowY, consumed)) {
/*      */             
/*  603 */             if (entry.rerollStat() != null && entry.rerollOldValue() >= 0) {
/*  604 */               this.hoveredIvRerollEntry = entry;
/*  605 */             } else if (entry.primaryPokemon() != null) {
/*  606 */               this.hoveredPokemonJson = entry.primaryPokemon();
/*  607 */               this.hoveredPokemonMouseX = mouseX;
/*  608 */               this.hoveredPokemonMouseY = mouseY;
/*      */             } else {
/*  610 */               hoveredItemStack = entry.stack();
/*  611 */               hoveredItemTooltipX = mouseX;
/*  612 */               hoveredItemTooltipY = mouseY;
/*      */             } 
/*  614 */             this.hoveredIvRerollMouseX = mouseX;
/*  615 */             this.hoveredIvRerollMouseY = mouseY;
/*      */           } 
/*  617 */           cx += consumed;
/*  618 */         } else if (isCrateRow && hasRewardStacks) {
/*  619 */           for (int ri = 0; ri < entry.rewardStacks().size(); ri++) {
/*  620 */             class_1799 rewardStack = entry.rewardStacks().get(ri);
/*  621 */             JsonObject rewardPokemon = (ri < entry.rewardPokemon().size()) ? entry.rewardPokemon().get(ri) : null;
/*  622 */             int consumed = renderPokemonOrItemCell(ctx, textRenderer, rewardStack, rewardPokemon, cx, rowY, mouseX, mouseY, delta, "log_" + i + "_crate_" + ri);
/*      */             
/*  624 */             if (mouseInCell(mouseX, mouseY, cx, rowY, consumed)) {
/*  625 */               if (rewardPokemon != null) {
/*  626 */                 this.hoveredPokemonJson = rewardPokemon;
/*  627 */                 this.hoveredPokemonMouseX = mouseX;
/*  628 */                 this.hoveredPokemonMouseY = mouseY;
/*      */               } else {
/*  630 */                 hoveredItemStack = rewardStack;
/*  631 */                 hoveredItemTooltipX = mouseX;
/*  632 */                 hoveredItemTooltipY = mouseY;
/*      */               } 
/*      */             }
/*  635 */             cx += consumed;
/*      */           } 
/*  637 */         } else if (hasStack) {
/*      */           
/*  639 */           int consumed = renderPokemonOrItemCell(ctx, textRenderer, entry.stack(), entry.primaryPokemon(), cx, rowY, mouseX, mouseY, delta, "log_" + i + "_main");
/*      */           
/*  641 */           if (mouseInCell(mouseX, mouseY, cx, rowY, consumed)) {
/*  642 */             if (entry.primaryPokemon() != null) {
/*  643 */               this.hoveredPokemonJson = entry.primaryPokemon();
/*  644 */               this.hoveredPokemonMouseX = mouseX;
/*  645 */               this.hoveredPokemonMouseY = mouseY;
/*      */             } else {
/*  647 */               hoveredItemStack = entry.stack();
/*  648 */               hoveredItemTooltipX = mouseX;
/*  649 */               hoveredItemTooltipY = mouseY;
/*      */             } 
/*      */           }
/*  652 */           cx += consumed;
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/*  657 */         int remaining = this.x + this.width - cx - 6;
/*  658 */         if (isAuctionRow) {
/*  659 */           renderAuctionRowContent(ctx, textRenderer, entry, cx, textY, rowY, remaining);
/*      */         } else {
/*  661 */           renderContentWithClickableName(ctx, textRenderer, entry, cx, textY, remaining, rowY);
/*      */         } 
/*      */       } 
/*      */     } 
/*  665 */     this.scrollArea.endRender(ctx);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  675 */     if (this.calendarPicker == null || !this.calendarPicker.isMouseOverPopup(mouseX, mouseY))
/*      */     {
/*  677 */       if (this.hoveredIvRerollEntry != null) {
/*  678 */         drawRerollIvTooltip(ctx, textRenderer, this.hoveredIvRerollEntry, this.hoveredIvRerollMouseX, this.hoveredIvRerollMouseY);
/*  679 */       } else if (this.hoveredPokemonJson != null) {
/*  680 */         drawPokemonTooltip(ctx, textRenderer, this.hoveredPokemonJson, this.hoveredPokemonMouseX, this.hoveredPokemonMouseY);
/*  681 */       } else if (!hoveredItemStack.method_7960()) {
/*  682 */         class_4587 matrices = ctx.method_51448();
/*  683 */         matrices.method_22903();
/*  684 */         matrices.method_46416(0.0F, 0.0F, 500.0F);
/*  685 */         ctx.method_51446(textRenderer, hoveredItemStack, hoveredItemTooltipX, hoveredItemTooltipY);
/*  686 */         matrices.method_22909();
/*  687 */       } else if (this.hoveredRowIndex >= 0 && this.hoveredRowIndex < this.entries.size()) {
/*      */         
/*  689 */         renderLogTooltip(ctx, textRenderer, this.entries.get(this.hoveredRowIndex), mouseX, mouseY);
/*      */       } 
/*      */     }
/*      */     
/*  693 */     renderFooter(ctx, mouseX, mouseY, textRenderer);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void renderContentWithClickableName(class_332 ctx, class_327 textRenderer, LogEntry entry, int cx, int textY, int maxWidth, int rowY) {
/*  703 */     String content = entry.content();
/*  704 */     String otherName = entry.otherPlayerName();
/*      */     
/*  706 */     if (otherName == null || otherName.isEmpty() || content == null || content.isEmpty()) {
/*      */       
/*  708 */       String truncated = truncateText(textRenderer, (content != null) ? content : "", maxWidth);
/*  709 */       ctx.method_51433(textRenderer, truncated, cx, textY, -1511169, false);
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*  714 */     int nameIdx = content.indexOf(otherName);
/*  715 */     if (nameIdx < 0) {
/*      */       
/*  717 */       String truncated = truncateText(textRenderer, content, maxWidth);
/*  718 */       ctx.method_51433(textRenderer, truncated, cx, textY, -1511169, false);
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*  723 */     String before = content.substring(0, nameIdx);
/*  724 */     String after = content.substring(nameIdx + otherName.length());
/*      */     
/*  726 */     int beforeW = textRenderer.method_1727(before);
/*  727 */     int nameW = textRenderer.method_1727(otherName);
/*      */ 
/*      */     
/*  730 */     int totalNeeded = textRenderer.method_1727(content);
/*  731 */     if (totalNeeded <= maxWidth) {
/*      */       
/*  733 */       ctx.method_51433(textRenderer, before, cx, textY, -1511169, false);
/*  734 */       int nameCx = cx + beforeW;
/*  735 */       ctx.method_51433(textRenderer, otherName, nameCx, textY, -10777105, false);
/*  736 */       ctx.method_51433(textRenderer, after, nameCx + nameW, textY, -1511169, false);
/*      */ 
/*      */       
/*  739 */       if (this.scrollArea.isInBounds(nameCx, textY)) {
/*  740 */         this.nameRegions.add(new NameRegion(nameCx, rowY, nameW, 26, otherName));
/*      */       }
/*      */     } else {
/*      */       
/*  744 */       int ellipsisW = textRenderer.method_1727("...");
/*  745 */       int availForContent = maxWidth - ellipsisW;
/*  746 */       if (availForContent <= 0) {
/*  747 */         ctx.method_51433(textRenderer, "...", cx, textY, -1511169, false);
/*      */         
/*      */         return;
/*      */       } 
/*  751 */       String truncatedAfter = truncateText(textRenderer, after, maxWidth - beforeW - nameW);
/*  752 */       int usedW = beforeW + nameW + textRenderer.method_1727(truncatedAfter);
/*  753 */       if (usedW <= maxWidth) {
/*  754 */         ctx.method_51433(textRenderer, before, cx, textY, -1511169, false);
/*  755 */         int nameCx = cx + beforeW;
/*  756 */         ctx.method_51433(textRenderer, otherName, nameCx, textY, -10777105, false);
/*  757 */         ctx.method_51433(textRenderer, truncatedAfter, nameCx + nameW, textY, -1511169, false);
/*  758 */         if (this.scrollArea.isInBounds(nameCx, textY)) {
/*  759 */           this.nameRegions.add(new NameRegion(nameCx, rowY, nameW, 26, otherName));
/*      */         }
/*      */       } else {
/*      */         
/*  763 */         String truncated = truncateText(textRenderer, content, maxWidth);
/*  764 */         ctx.method_51433(textRenderer, truncated, cx, textY, -1511169, false);
/*      */       } 
/*      */     } 
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
/*      */   private void renderAuctionRowContent(class_332 ctx, class_327 tr, LogEntry entry, int cx, int textY, int rowY, int maxWidth) {
/*  779 */     AuctionData a = entry.auction();
/*  780 */     if (a == null)
/*      */       return; 
/*  782 */     int startX = cx;
/*  783 */     int available = maxWidth;
/*      */ 
/*      */     
/*  786 */     String itemName = (a.itemName() != null) ? a.itemName() : "";
/*  787 */     if (!itemName.isEmpty()) {
/*  788 */       int w = tr.method_1727(itemName);
/*  789 */       ctx.method_51433(tr, itemName, cx, textY, -1511169, false);
/*  790 */       cx += w + 6;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  797 */     String action = (a.action() != null) ? a.action() : "";
/*  798 */     boolean hasSeller = (a.sellerName() != null && !a.sellerName().isEmpty());
/*  799 */     boolean hasBuyer = (a.buyerName() != null && !a.buyerName().isEmpty());
/*  800 */     switch (action) { case "LIST": case "UNLIST": 
/*      */       case "BUY": case "BID": 
/*      */       case "SOLD":
/*  803 */         if (hasSeller && hasBuyer);
/*  804 */       default: break; }  boolean canRenderStructured = false;
/*      */     
/*  806 */     if (!canRenderStructured) {
/*  807 */       String fallback = (entry.content() != null) ? entry.content() : "";
/*  808 */       if (fallback.isEmpty())
/*  809 */         return;  String truncated = truncateText(tr, fallback, startX + available - cx);
/*  810 */       ctx.method_51433(tr, truncated, cx, textY, -1511169, false);
/*      */       
/*      */       return;
/*      */     } 
/*  814 */     switch (action) { case "LIST":
/*      */       case "UNLIST":
/*  816 */         cx = drawConnector(ctx, tr, cx, textY, "by");
/*  817 */         cx = drawPlayerName(ctx, tr, cx, textY, rowY, a.sellerName()); break;
/*      */       case "BUY":
/*      */       case "BID":
/*  820 */         cx = drawConnector(ctx, tr, cx, textY, "by");
/*  821 */         cx = drawPlayerName(ctx, tr, cx, textY, rowY, a.buyerName());
/*  822 */         if (hasSeller) {
/*  823 */           cx = drawConnector(ctx, tr, cx, textY, "from");
/*  824 */           cx = drawPlayerName(ctx, tr, cx, textY, rowY, a.sellerName());
/*      */         } 
/*      */         break;
/*      */       case "SOLD":
/*  828 */         cx = drawConnector(ctx, tr, cx, textY, "seller");
/*  829 */         cx = drawPlayerName(ctx, tr, cx, textY, rowY, a.sellerName());
/*  830 */         cx = drawConnector(ctx, tr, cx, textY, "→");
/*  831 */         cx = drawConnector(ctx, tr, cx, textY, "buyer");
/*  832 */         cx = drawPlayerName(ctx, tr, cx, textY, rowY, a.buyerName());
/*      */         break; }
/*      */ 
/*      */ 
/*      */     
/*  837 */     if (a.price() >= 0L) {
/*  838 */       String priceStr = formatPrice(a.price());
/*  839 */       int pw = tr.method_1727(priceStr);
/*  840 */       ctx.method_51433(tr, priceStr, cx, textY, -1, true);
/*  841 */       cx += pw + 6;
/*      */     } 
/*      */ 
/*      */     
/*  845 */     if (a.currency() != null && !a.currency().isEmpty()) {
/*  846 */       cx = drawPillBadge(ctx, tr, cx, rowY, a.currency(), currencyColor(a.currency()));
/*      */     }
/*      */     
/*  849 */     if (a.listingType() != null && !a.listingType().isEmpty()) {
/*  850 */       cx = drawPillBadge(ctx, tr, cx, rowY, a.listingType(), -10777105);
/*      */     }
/*      */   }
/*      */   
/*      */   private int drawConnector(class_332 ctx, class_327 tr, int cx, int textY, String word) {
/*  855 */     int w = tr.method_1727(word);
/*  856 */     ctx.method_51433(tr, word, cx, textY, -9930592, false);
/*  857 */     return cx + w + 4;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int drawPlayerName(class_332 ctx, class_327 tr, int cx, int textY, int rowY, String name) {
/*  865 */     if (name == null || name.isEmpty()) return cx; 
/*  866 */     int w = tr.method_1727(name);
/*  867 */     ctx.method_51433(tr, name, cx, textY, -10777105, false);
/*  868 */     if (this.scrollArea.isInBounds(cx, textY)) {
/*  869 */       this.nameRegions.add(new NameRegion(cx, rowY, w, 26, name));
/*      */     }
/*  871 */     return cx + w + 4;
/*      */   }
/*      */ 
/*      */   
/*      */   private int drawPillBadge(class_332 ctx, class_327 tr, int cx, int rowY, String label, int color) {
/*  876 */     int w = tr.method_1727(label) + 6;
/*  877 */     int top = rowY + 3;
/*  878 */     int bottom = rowY + 26 - 3;
/*  879 */     ctx.method_25294(cx, top, cx + w, bottom, LookupColors.withAlpha(color, 50));
/*  880 */     int textY = top + (bottom - top - 9) / 2;
/*  881 */     ctx.method_51433(tr, label, cx + 3, textY, color, false);
/*  882 */     return cx + w + 4;
/*      */   }
/*      */   
/*      */   private static int auctionActionColor(String action) {
/*  886 */     if (action == null) return -9930592; 
/*  887 */     switch (action) { case "BUY": case "LIST": case "UNLIST": case "SOLD": case "BID":  }  return 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  893 */       -9930592;
/*      */   }
/*      */ 
/*      */   
/*      */   private static int currencyColor(String currency) {
/*  898 */     if (currency == null) return -9930592; 
/*  899 */     switch (currency) { case "POKE_COIN": case "POKE_GEM": case "HONOR_POINT":  }  return 
/*      */ 
/*      */ 
/*      */       
/*  903 */       -9930592;
/*      */   }
/*      */ 
/*      */   
/*      */   private static String formatPrice(long price) {
/*  908 */     return String.format("%,d", new Object[] { Long.valueOf(price) });
/*      */   }
/*      */   
/*      */   private void renderFilterTabs(class_332 ctx, int mouseX, int mouseY, class_327 textRenderer) {
/*  912 */     int tabY = this.y + 22;
/*  913 */     int tabX = this.x + 6;
/*      */ 
/*      */     
/*  916 */     this.tabXPositions = new int[FILTERS.length];
/*  917 */     this.tabWidths = new int[FILTERS.length];
/*      */     
/*  919 */     for (int i = 0; i < FILTERS.length; i++) {
/*  920 */       String label = FILTERS[i];
/*  921 */       int tw = textRenderer.method_1727(label);
/*  922 */       int tabW = tw + 12;
/*      */       
/*  924 */       this.tabXPositions[i] = tabX;
/*  925 */       this.tabWidths[i] = tabW;
/*      */       
/*  927 */       boolean isActive = (i == this.activeFilter);
/*  928 */       boolean hover = (mouseX >= tabX && mouseX < tabX + tabW && mouseY >= tabY && mouseY < tabY + 20);
/*      */ 
/*      */       
/*  931 */       int bg = isActive ? -15063744 : (hover ? -15459800 : -15328732);
/*  932 */       ctx.method_25294(tabX, tabY, tabX + tabW, tabY + 20, bg);
/*      */ 
/*      */       
/*  935 */       if (isActive) {
/*  936 */         ctx.method_25294(tabX, tabY + 20 - 2, tabX + tabW, tabY + 20, -10777105);
/*      */       }
/*      */ 
/*      */       
/*  940 */       int textColor = isActive ? -1 : (hover ? -1511169 : -9930592);
/*  941 */       ctx.method_51433(textRenderer, label, tabX + 6, tabY + 5, textColor, isActive);
/*      */       
/*  943 */       tabX += tabW + 2;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void renderSearchBar(class_332 ctx, int mouseX, int mouseY, class_327 textRenderer) {
/*  948 */     int searchY = this.y + 22 + 20 + 2;
/*  949 */     int searchX = this.x + 6;
/*      */     
/*  951 */     int calW = (int)(this.width * 0.4D) - 6;
/*  952 */     int searchW = this.width - 12 - calW - 6;
/*      */ 
/*      */     
/*  955 */     int bg = this.searchFocused ? -14274480 : -14801866;
/*  956 */     ctx.method_25294(searchX, searchY, searchX + searchW, searchY + 16, bg);
/*  957 */     ctx.method_49601(searchX, searchY, searchW, 16, this.searchFocused ? -10777105 : -14012352);
/*      */ 
/*      */     
/*  960 */     String prefix = "Search: ";
/*  961 */     ctx.method_51433(textRenderer, prefix, searchX + 4, searchY + 4, -9930592, false);
/*  962 */     int prefixW = textRenderer.method_1727(prefix);
/*      */ 
/*      */     
/*  965 */     if (this.searchText.isEmpty() && !this.searchFocused) {
/*  966 */       ctx.method_51433(textRenderer, "Type and press Enter...", searchX + 4 + prefixW, searchY + 4, LookupColors.withAlpha(-9930592, 100), false);
/*      */     } else {
/*  968 */       String displayText = this.searchText + this.searchText;
/*  969 */       ctx.method_51433(textRenderer, displayText, searchX + 4 + prefixW, searchY + 4, -1511169, false);
/*      */     } 
/*      */   }
/*      */   private void renderFooter(class_332 ctx, int mouseX, int mouseY, class_327 textRenderer) {
/*      */     String footerText;
/*  974 */     int footerY = this.y + this.height - 22;
/*      */     
/*  976 */     if (this.requestInFlight && !this.entries.isEmpty()) {
/*  977 */       footerText = "Loading more...";
/*  978 */     } else if (!this.hasMorePages && !this.entries.isEmpty()) {
/*  979 */       footerText = "End of logs";
/*  980 */     } else if (this.totalLogs > 0) {
/*  981 */       footerText = "Loaded " + this.entries.size() + " / " + this.totalLogs;
/*  982 */     } else if (!this.entries.isEmpty()) {
/*  983 */       footerText = "Loaded " + this.entries.size();
/*      */     } else {
/*      */       return;
/*      */     } 
/*      */     
/*  988 */     int footerTextW = textRenderer.method_1727(footerText);
/*  989 */     ctx.method_51433(textRenderer, footerText, this.x + (this.width - footerTextW) / 2, footerY + 6, -9930592, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
/*  996 */     if (this.scrollArea != null) {
/*  997 */       boolean handled = this.scrollArea.handleScroll(mouseX, mouseY, verticalAmount);
/*  998 */       if (handled) maybeRequestMore(); 
/*  999 */       return handled;
/*      */     } 
/* 1001 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
/* 1006 */     if (this.scrollArea != null) return this.scrollArea.handleMouseDragged(mouseX, mouseY, button); 
/* 1007 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean mouseReleased(double mouseX, double mouseY, int button) {
/* 1012 */     if (this.scrollArea != null) return this.scrollArea.handleMouseReleased(mouseX, mouseY, button); 
/* 1013 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean mouseClicked(double mouseX, double mouseY, int button) {
/* 1018 */     if (button != 0) return false;
/*      */ 
/*      */     
/* 1021 */     if (this.scrollArea != null && this.scrollArea.handleMouseClicked(mouseX, mouseY, button)) {
/* 1022 */       return true;
/*      */     }
/*      */ 
/*      */     
/* 1026 */     for (NameRegion nr : this.nameRegions) {
/* 1027 */       if (mouseX >= nr.rx() && mouseX < (nr.rx() + nr.rw()) && mouseY >= nr
/* 1028 */         .ry() && mouseY < (nr.ry() + nr.rh())) {
/*      */         
/* 1030 */         class_310 mc = class_310.method_1551();
/* 1031 */         if (mc.field_1724 != null) {
/* 1032 */           mc.field_1724.field_3944.method_45730("lookup " + nr.playerName());
/*      */         }
/* 1034 */         return true;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1039 */     int tabY = this.y + 22;
/* 1040 */     if (mouseY >= tabY && mouseY < (tabY + 20) && this.tabXPositions != null) {
/* 1041 */       for (int i = 0; i < FILTERS.length; i++) {
/* 1042 */         int tabX = this.tabXPositions[i];
/* 1043 */         int tabW = this.tabWidths[i];
/* 1044 */         if (mouseX >= tabX && mouseX < (tabX + tabW)) {
/* 1045 */           if (this.activeFilter != i) {
/* 1046 */             LookupSounds.playTabClick();
/* 1047 */             this.activeFilter = i;
/* 1048 */             resetAndRequestFirstPage();
/*      */           } 
/* 1050 */           return true;
/*      */         } 
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/* 1056 */     if (this.calendarPicker != null && 
/* 1057 */       this.calendarPicker.mouseClicked(mouseX, mouseY, button)) {
/* 1058 */       return true;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1063 */     int searchY = this.y + 22 + 20 + 2;
/* 1064 */     int searchX = this.x + 6;
/* 1065 */     int calW = (int)(this.width * 0.4D) - 6;
/* 1066 */     int searchW = this.width - 12 - calW - 6;
/* 1067 */     if (mouseX >= searchX && mouseX < (searchX + searchW) && mouseY >= searchY && mouseY < (searchY + 16)) {
/* 1068 */       this.searchFocused = true;
/* 1069 */       return true;
/*      */     } 
/*      */     
/* 1072 */     this.searchFocused = false;
/*      */ 
/*      */     
/* 1075 */     return false;
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
/*      */ 
/*      */ 
/*      */   
/*      */   private int renderPokemonOrItemCell(class_332 ctx, class_327 textRenderer, class_1799 stack, JsonObject pokemonJson, int cx, int rowY, int mouseX, int mouseY, float delta, String stateKey) {
/* 1091 */     if (pokemonJson != null) {
/* 1092 */       int my = rowY + 2;
/* 1093 */       renderPokemonModel(ctx, pokemonJson, cx, my, 22, stateKey, delta);
/* 1094 */       return 24;
/*      */     } 
/*      */     
/* 1097 */     int itemX = cx;
/* 1098 */     int itemY = rowY + 5;
/* 1099 */     ctx.method_51448().method_22903();
/* 1100 */     ctx.method_51448().method_46416(0.0F, 0.0F, 100.0F);
/* 1101 */     ctx.method_51427(stack, itemX, itemY);
/* 1102 */     ctx.method_51448().method_22909();
/* 1103 */     return 18;
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean mouseInCell(int mouseX, int mouseY, int cx, int rowY, int w) {
/* 1108 */     return (mouseX >= cx && mouseX < cx + w && mouseY >= rowY && mouseY < rowY + 26 && mouseY >= this.scrollArea
/*      */       
/* 1110 */       .getY() && mouseY < this.scrollArea.getY() + this.scrollArea.getHeight());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void renderPokemonModel(class_332 ctx, JsonObject poke, int mx, int my, int ms, String stateKey, float delta) {
/* 1119 */     String species = pokeString(poke, "species");
/* 1120 */     if (species == null || species.isEmpty() || !PokemonRenderHelper.isAvailable()) {
/* 1121 */       ctx.method_25294(mx, my, mx + ms, my + ms, LookupColors.withAlpha(-15328732, 200));
/*      */       return;
/*      */     } 
/*      */     try {
/* 1125 */       class_2960 speciesId = resolveSpeciesId(species);
/* 1126 */       FloatingState state = this.modelStates.computeIfAbsent(stateKey, k -> new FloatingState());
/* 1127 */       state.setCurrentAspects(getAspects(poke));
/*      */       
/* 1129 */       class_4587 mat = ctx.method_51448();
/* 1130 */       mat.method_22903();
/* 1131 */       mat.method_22904(mx + ms / 2.0D, my + 2.0D, 0.0D);
/* 1132 */       float scale = ms / 9.0F;
/* 1133 */       mat.method_22905(scale, scale, 1.0F);
/* 1134 */       Quaternionf rot = new Quaternionf();
/* 1135 */       rot.rotationXYZ((float)Math.toRadians(13.0D), (float)Math.toRadians(35.0D), 0.0F);
/* 1136 */       PokemonRenderHelper.draw(speciesId, mat, rot, state, delta);
/* 1137 */       mat.method_22909();
/* 1138 */     } catch (Exception e) {
/* 1139 */       ctx.method_25294(mx, my, mx + ms, my + ms, LookupColors.withAlpha(-15328732, 200));
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/* 1144 */   private static final String[] POKEMON_TIP_STAT_KEYS = StatIcon.KEYS;
/* 1145 */   private static final int[] POKEMON_TIP_STAT_COLORS = StatIcon.COLORS;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void drawPokemonTooltip(class_332 ctx, class_327 tr, JsonObject poke, int mouseX, int mouseY) {
/* 1153 */     if (poke == null)
/*      */       return; 
/* 1155 */     int TOOLTIP_PADDING = 5;
/* 1156 */     int LINE_H = 11;
/*      */ 
/*      */     
/* 1159 */     List<TipLine> lines = new ArrayList<>();
/* 1160 */     String species = pokeString(poke, "species");
/* 1161 */     lines.add(new TipLine("§l" + formatSpeciesName(species), -1));
/*      */     
/* 1163 */     int level = pokeInt(poke, "level");
/* 1164 */     lines.add(new TipLine("Level: " + level, -1511169));
/*      */     
/* 1166 */     String nature = pokeString(poke, "nature");
/* 1167 */     if (nature != null && !nature.isEmpty()) {
/* 1168 */       lines.add(new TipLine("Nature: " + nature, -1511169));
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1173 */     String gender = pokeString(poke, "gender");
/* 1174 */     String gSym = genderSymbol(gender);
/* 1175 */     String genderDisplay = gSym.isEmpty() ? ((gender != null && !gender.isEmpty()) ? gender : "N/A") : gSym;
/* 1176 */     lines.add(new TipLine("Gender: " + genderDisplay, genderColor(gender)));
/*      */     
/* 1178 */     String heldItem = pokeString(poke, "heldItem");
/* 1179 */     if (heldItem != null && !heldItem.isEmpty()) {
/* 1180 */       lines.add(new TipLine("Held: " + prettifyName(heldItem), -9930592));
/*      */     }
/*      */     
/* 1183 */     JsonObject ivs = (poke.has("ivs") && poke.get("ivs").isJsonObject()) ? poke.getAsJsonObject("ivs") : null;
/*      */ 
/*      */     
/* 1186 */     List<class_2561> propIcons = StatIcon.propertyIcons(poke);
/*      */ 
/*      */     
/* 1189 */     int maxTextW = 0;
/* 1190 */     for (TipLine line : lines) {
/* 1191 */       int w = tr.method_1727(line.text());
/* 1192 */       if (w > maxTextW) maxTextW = w;
/*      */     
/*      */     } 
/* 1195 */     int BAR_LABEL_W = 42;
/* 1196 */     int BAR_VALUE_W = 18;
/* 1197 */     int BAR_FIXED_W = 110;
/* 1198 */     int BAR_MIN_W = 178;
/* 1199 */     int BAR_SECTION_H = (ivs != null) ? (POKEMON_TIP_STAT_KEYS.length * 10 + 12) : 0;
/* 1200 */     boolean hasPropIcons = !propIcons.isEmpty();
/* 1201 */     int PROP_ICON_ROW_H = hasPropIcons ? 14 : 0;
/*      */     
/* 1203 */     int minWidth = (ivs != null) ? 188 : 0;
/* 1204 */     int tooltipW = Math.max(maxTextW + 10, minWidth);
/* 1205 */     int textBlockH = lines.size() * 11 + 10 - 2;
/* 1206 */     int separatorH = (ivs != null) ? 4 : 0;
/* 1207 */     int totalH = textBlockH + PROP_ICON_ROW_H + separatorH + BAR_SECTION_H;
/*      */     
/* 1209 */     int tx = mouseX + 10;
/* 1210 */     int ty = mouseY - totalH - 4;
/* 1211 */     if (tx + tooltipW > this.x + this.width) tx = mouseX - tooltipW - 4; 
/* 1212 */     if (ty < this.y) ty = mouseY + 14;
/*      */     
/* 1214 */     class_4587 matrices = ctx.method_51448();
/* 1215 */     matrices.method_22903();
/* 1216 */     matrices.method_46416(0.0F, 0.0F, 500.0F);
/*      */     
/* 1218 */     ctx.method_25294(tx, ty, tx + tooltipW, ty + totalH, -267383266);
/* 1219 */     ctx.method_49601(tx, ty, tooltipW, totalH, -10777105);
/*      */     
/* 1221 */     int lineY = ty + 5;
/* 1222 */     for (TipLine line : lines) {
/* 1223 */       ctx.method_51433(tr, line.text(), tx + 5, lineY, line.color(), true);
/* 1224 */       lineY += 11;
/*      */     } 
/*      */ 
/*      */     
/* 1228 */     if (hasPropIcons) {
/* 1229 */       int ix = tx + 5;
/* 1230 */       for (class_2561 icon : propIcons) {
/* 1231 */         ctx.method_51439(tr, icon, ix, lineY + 1, -1, false);
/* 1232 */         ix += tr.method_27525((class_5348)icon) + 3;
/*      */       } 
/* 1234 */       lineY += PROP_ICON_ROW_H;
/*      */     } 
/*      */     
/* 1237 */     if (ivs != null) {
/* 1238 */       lineY += 2;
/* 1239 */       ctx.method_25294(tx + 5, lineY, tx + tooltipW - 5, lineY + 1, 
/* 1240 */           LookupColors.withAlpha(-14012352, 160));
/* 1241 */       lineY += 3;
/*      */       
/* 1243 */       int barX = tx + 5 + 42;
/* 1244 */       int barW = 110;
/* 1245 */       int maxVal = 31;
/* 1246 */       int ivTotal = 0;
/* 1247 */       for (int s = 0; s < POKEMON_TIP_STAT_KEYS.length; s++) {
/* 1248 */         int val = ivs.has(POKEMON_TIP_STAT_KEYS[s]) ? ivs.get(POKEMON_TIP_STAT_KEYS[s]).getAsInt() : 0;
/* 1249 */         ivTotal += val;
/*      */         
/* 1251 */         ctx.method_51439(tr, StatIcon.icon(s), tx + 5, lineY + 2, -1, false);
/* 1252 */         ctx.method_25294(barX, lineY + 2, barX + barW, lineY + 8, -14540254);
/* 1253 */         int fillW = (int)(val / 31.0F * barW);
/* 1254 */         if (fillW > 0) ctx.method_25294(barX, lineY + 2, barX + fillW, lineY + 8, POKEMON_TIP_STAT_COLORS[s]); 
/* 1255 */         ctx.method_51433(tr, String.valueOf(val), barX + barW + 3, lineY, -1, true);
/* 1256 */         lineY += 10;
/*      */       } 
/* 1258 */       int pct = (int)(ivTotal * 100.0D / (31 * POKEMON_TIP_STAT_KEYS.length));
/* 1259 */       String totalStr = "" + ivTotal + "/" + ivTotal + " (" + 31 * POKEMON_TIP_STAT_KEYS.length + "%)";
/* 1260 */       ctx.method_51433(tr, totalStr, tx + 5, lineY + 1, -10777105, true);
/*      */     } 
/*      */     
/* 1263 */     matrices.method_22909();
/*      */   } private static final class TipLine extends Record {
/*      */     private final String text; private final int color;
/* 1266 */     private TipLine(String text, int color) { this.text = text; this.color = color; } public final String toString() { // Byte code:
/*      */       //   0: aload_0
/*      */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/lookup/screen/views/LookupLogsView$TipLine;)Ljava/lang/String;
/*      */       //   6: areturn
/*      */       // Line number table:
/*      */       //   Java source line number -> byte code offset
/*      */       //   #1266	-> 0
/*      */       // Local variable table:
/*      */       //   start	length	slot	name	descriptor
/*      */       //   0	7	0	this	Lcom/atlas/common/fabric/lookup/screen/views/LookupLogsView$TipLine; } public final int hashCode() { // Byte code:
/*      */       //   0: aload_0
/*      */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/lookup/screen/views/LookupLogsView$TipLine;)I
/*      */       //   6: ireturn
/*      */       // Line number table:
/*      */       //   Java source line number -> byte code offset
/*      */       //   #1266	-> 0
/*      */       // Local variable table:
/*      */       //   start	length	slot	name	descriptor
/*      */       //   0	7	0	this	Lcom/atlas/common/fabric/lookup/screen/views/LookupLogsView$TipLine; } public final boolean equals(Object o) { // Byte code:
/*      */       //   0: aload_0
/*      */       //   1: aload_1
/*      */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/lookup/screen/views/LookupLogsView$TipLine;Ljava/lang/Object;)Z
/*      */       //   7: ireturn
/*      */       // Line number table:
/*      */       //   Java source line number -> byte code offset
/*      */       //   #1266	-> 0
/*      */       // Local variable table:
/*      */       //   start	length	slot	name	descriptor
/*      */       //   0	8	0	this	Lcom/atlas/common/fabric/lookup/screen/views/LookupLogsView$TipLine;
/* 1266 */       //   0	8	1	o	Ljava/lang/Object; } public String text() { return this.text; } public int color() { return this.color; }
/*      */   
/*      */   }
/*      */   
/*      */   private static String pokeString(JsonObject obj, String key) {
/* 1271 */     if (obj == null || !obj.has(key) || obj.get(key).isJsonNull()) return null;  
/* 1272 */     try { return obj.get(key).getAsString(); } catch (Exception e) { return null; }
/*      */   
/*      */   }
/*      */   private static int pokeInt(JsonObject obj, String key) {
/* 1276 */     if (obj == null || !obj.has(key) || obj.get(key).isJsonNull()) return 0;  
/* 1277 */     try { return obj.get(key).getAsInt(); } catch (Exception e) { return 0; }
/*      */   
/*      */   }
/*      */   private static Set<String> getAspects(JsonObject poke) {
/* 1281 */     Set<String> aspects = new HashSet<>();
/* 1282 */     if (poke == null) return aspects; 
/* 1283 */     if (poke.has("aspects") && poke.get("aspects").isJsonArray())
/* 1284 */       for (JsonElement el : poke.getAsJsonArray("aspects")) {
/* 1285 */         if (!el.isJsonNull()) aspects.add(el.getAsString());
/*      */       
/*      */       }  
/* 1288 */     if (poke.has("shiny") && !poke.get("shiny").isJsonNull()) {
/* 1289 */       try { if (poke.get("shiny").getAsBoolean()) aspects.add("shiny");  } catch (Exception exception) {}
/*      */     }
/* 1291 */     return aspects;
/*      */   }
/*      */   
/*      */   private static boolean hasAspect(JsonObject poke, String aspect) {
/* 1295 */     return getAspects(poke).contains(aspect);
/*      */   }
/*      */   
/*      */   private static class_2960 resolveSpeciesId(String species) {
/* 1299 */     if (species == null) return class_2960.method_60655("cobblemon", "missingno"); 
/* 1300 */     String slug = species.contains(":") ? species.substring(species.indexOf(':') + 1) : species;
/* 1301 */     return class_2960.method_60655("cobblemon", slug);
/*      */   }
/*      */   
/*      */   private static String formatSpeciesName(String species) {
/* 1305 */     if (species == null || species.isEmpty()) return "???"; 
/* 1306 */     String name = species.contains(":") ? species.substring(species.indexOf(':') + 1) : species;
/* 1307 */     StringBuilder sb = new StringBuilder();
/* 1308 */     for (String word : name.replace('_', ' ').split(" ")) {
/* 1309 */       if (!word.isEmpty()) {
/* 1310 */         if (sb.length() > 0) sb.append(' '); 
/* 1311 */         sb.append(Character.toUpperCase(word.charAt(0)));
/* 1312 */         if (word.length() > 1) sb.append(word.substring(1)); 
/*      */       } 
/*      */     } 
/* 1315 */     return sb.toString();
/*      */   }
/*      */   
/*      */   private static String genderSymbol(String gender) {
/* 1319 */     if (gender == null) return ""; 
/* 1320 */     switch (gender.toUpperCase()) { case "MALE": case "FEMALE":  }  return 
/*      */ 
/*      */       
/* 1323 */       "";
/*      */   }
/*      */ 
/*      */   
/*      */   private static int genderColor(String gender) {
/* 1328 */     if (gender == null) return -9930592; 
/* 1329 */     switch (gender.toUpperCase()) { case "MALE": case "FEMALE":  }  return 
/*      */ 
/*      */       
/* 1332 */       -9930592;
/*      */   }
/*      */ 
/*      */   
/*      */   private static String prettifyName(String name) {
/* 1337 */     if (name == null || name.isEmpty()) return "?"; 
/* 1338 */     int colon = name.indexOf(':');
/* 1339 */     if (colon >= 0) name = name.substring(colon + 1); 
/* 1340 */     StringBuilder sb = new StringBuilder();
/* 1341 */     for (String word : name.replace('_', ' ').split(" ")) {
/* 1342 */       if (!word.isEmpty()) {
/* 1343 */         if (sb.length() > 0) sb.append(' '); 
/* 1344 */         sb.append(Character.toUpperCase(word.charAt(0)));
/* 1345 */         if (word.length() > 1) sb.append(word.substring(1)); 
/*      */       } 
/*      */     } 
/* 1348 */     return sb.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void drawRerollIvTooltip(class_332 ctx, class_327 textRenderer, LogEntry entry, int mouseX, int mouseY) {
/*      */     String pokemonName;
/* 1357 */     int TOOLTIP_PADDING = 5;
/* 1358 */     int LINE_H = 11;
/*      */     
/* 1360 */     int BAR_LABEL_W = 28;
/* 1361 */     int BAR_VALUE_W = 18;
/* 1362 */     int BAR_H = 6;
/* 1363 */     int BAR_FIXED_W = 110;
/*      */ 
/*      */     
/* 1366 */     int statIdx = StatIcon.indexOf(entry.rerollStat());
/* 1367 */     int statColor = (statIdx >= 0) ? StatIcon.COLORS[statIdx] : -1;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1372 */     if (entry.primaryPokemon() != null) {
/* 1373 */       pokemonName = formatSpeciesName(pokeString(entry.primaryPokemon(), "species"));
/*      */     } else {
/* 1375 */       pokemonName = entry.stack().method_7960() ? "Pokemon" : entry.stack().method_7964().getString();
/*      */     } 
/*      */ 
/*      */     
/* 1379 */     String line0 = "§f" + pokemonName;
/* 1380 */     class_2561 statIconText = (statIdx >= 0) ? StatIcon.icon(statIdx) : (class_2561)class_2561.method_43470("");
/*      */     
/* 1382 */     int statIconW = (statIdx >= 0) ? textRenderer.method_27525((class_5348)statIconText) : 0;
/* 1383 */     int subHeadingW = statIconW + 2 + textRenderer.method_1727("Reroll");
/* 1384 */     int maxTextW = Math.max(textRenderer.method_1727(line0), subHeadingW);
/* 1385 */     int barRowW = 159;
/* 1386 */     int tooltipW = Math.max(maxTextW, barRowW) + 10 + 8;
/*      */ 
/*      */     
/* 1389 */     int BAR_ROW_H = 12;
/* 1390 */     int tooltipH = 62;
/*      */     
/* 1392 */     int tx = mouseX + 10;
/* 1393 */     int ty = mouseY - tooltipH - 4;
/* 1394 */     if (tx + tooltipW > this.x + this.width) tx = mouseX - tooltipW - 4; 
/* 1395 */     if (ty < this.y) ty = mouseY + 14;
/*      */     
/* 1397 */     ctx.method_51448().method_22903();
/* 1398 */     ctx.method_51448().method_46416(0.0F, 0.0F, 500.0F);
/*      */ 
/*      */     
/* 1401 */     ctx.method_25294(tx, ty, tx + tooltipW, ty + tooltipH, -267383266);
/* 1402 */     ctx.method_49601(tx, ty, tooltipW, tooltipH, -10777105);
/*      */     
/* 1404 */     int lineY = ty + 5;
/*      */ 
/*      */     
/* 1407 */     ctx.method_51433(textRenderer, line0, tx + 5, lineY, -1, true);
/* 1408 */     lineY += 11;
/*      */ 
/*      */     
/* 1411 */     int subX = tx + 5;
/* 1412 */     if (statIdx >= 0) {
/* 1413 */       ctx.method_51439(textRenderer, statIconText, subX, lineY + 2, -1, false);
/* 1414 */       subX += statIconW + 4;
/*      */     } 
/* 1416 */     ctx.method_51433(textRenderer, "Reroll", subX, lineY, statColor, true);
/* 1417 */     lineY += 15;
/*      */ 
/*      */     
/* 1420 */     ctx.method_25294(tx + 5, lineY, tx + tooltipW - 5, lineY + 1, 
/* 1421 */         LookupColors.withAlpha(-14012352, 160));
/* 1422 */     lineY += 3;
/*      */     
/* 1424 */     int barAreaW = 110;
/* 1425 */     int barX = tx + 5 + 28;
/* 1426 */     int maxVal = 31;
/*      */ 
/*      */     
/* 1429 */     int oldVal = entry.rerollOldValue();
/* 1430 */     int newVal = entry.rerollNewValue();
/*      */ 
/*      */     
/* 1433 */     ctx.method_51433(textRenderer, "Old", tx + 5, lineY + 1, -5592406, true);
/*      */     
/* 1435 */     ctx.method_25294(barX, lineY + 2, barX + barAreaW, lineY + 2 + 6, -14540254);
/*      */     
/* 1437 */     int oldFillW = (int)(oldVal / 31.0F * barAreaW);
/* 1438 */     if (oldFillW > 0) {
/* 1439 */       ctx.method_25294(barX, lineY + 2, barX + oldFillW, lineY + 2 + 6, 
/* 1440 */           LookupColors.withAlpha(statColor, 140));
/*      */     }
/* 1442 */     ctx.method_51433(textRenderer, String.valueOf(oldVal), barX + barAreaW + 3, lineY + 1, -5592406, true);
/* 1443 */     lineY += 12;
/*      */ 
/*      */     
/* 1446 */     ctx.method_51433(textRenderer, "New", tx + 5, lineY + 1, -1, true);
/* 1447 */     ctx.method_25294(barX, lineY + 2, barX + barAreaW, lineY + 2 + 6, -14540254);
/* 1448 */     int newFillW = (int)(newVal / 31.0F * barAreaW);
/* 1449 */     if (newFillW > 0) {
/* 1450 */       ctx.method_25294(barX, lineY + 2, barX + newFillW, lineY + 2 + 6, statColor);
/*      */     }
/* 1452 */     ctx.method_51433(textRenderer, String.valueOf(newVal), barX + barAreaW + 3, lineY + 1, -1, true);
/*      */     
/* 1454 */     ctx.method_51448().method_22909();
/*      */   }
/*      */   
/*      */   private void renderLogTooltip(class_332 ctx, class_327 textRenderer, LogEntry entry, int mouseX, int mouseY) {
/* 1458 */     int MAX_TOOLTIP_W = 220;
/* 1459 */     int TOOLTIP_PADDING = 5;
/* 1460 */     int LINE_H = 11;
/*      */ 
/*      */     
/* 1463 */     List<String> lines = new ArrayList<>();
/*      */ 
/*      */     
/* 1466 */     lines.add("§7" + ((entry.timestamp() != null) ? entry.timestamp().replace('T', ' ') : ""));
/*      */ 
/*      */     
/* 1469 */     String typeLabel = formatTypeBadge(entry.type());
/* 1470 */     lines.add("§f[" + typeLabel + "]");
/*      */ 
/*      */     
/* 1473 */     if (entry.content() != null && !entry.content().isEmpty()) {
/* 1474 */       List<String> wrapped = wordWrap(textRenderer, entry.content(), 210);
/* 1475 */       lines.addAll(wrapped);
/*      */     } 
/*      */ 
/*      */     
/* 1479 */     if (entry.otherPlayerName() != null && !entry.otherPlayerName().isEmpty()) {
/* 1480 */       lines.add("§a[Click name to lookup " + entry.otherPlayerName() + "]");
/*      */     }
/*      */ 
/*      */     
/* 1484 */     int tooltipW = 0;
/* 1485 */     for (String line : lines) {
/* 1486 */       int lw = textRenderer.method_1727(line);
/* 1487 */       if (lw > tooltipW) tooltipW = lw; 
/*      */     } 
/* 1489 */     tooltipW = Math.min(tooltipW + 10, 230);
/* 1490 */     int tooltipH = lines.size() * 11 + 10;
/*      */ 
/*      */     
/* 1493 */     int tx = mouseX + 12;
/* 1494 */     int ty = mouseY - 4;
/* 1495 */     if (tx + tooltipW > this.x + this.width) tx = mouseX - tooltipW - 4; 
/* 1496 */     if (ty + tooltipH > this.y + this.height) ty = this.y + this.height - tooltipH; 
/* 1497 */     if (ty < this.y) ty = this.y;
/*      */     
/* 1499 */     class_4587 matrices = ctx.method_51448();
/* 1500 */     matrices.method_22903();
/* 1501 */     matrices.method_46416(0.0F, 0.0F, 400.0F);
/*      */ 
/*      */     
/* 1504 */     ctx.method_25294(tx, ty, tx + tooltipW, ty + tooltipH, -267382768);
/* 1505 */     ctx.method_49601(tx, ty, tooltipW, tooltipH, -12958368);
/*      */ 
/*      */     
/* 1508 */     int lineY = ty + 5;
/* 1509 */     for (String line : lines) {
/* 1510 */       ctx.method_51433(textRenderer, line, tx + 5, lineY, -1, false);
/* 1511 */       lineY += 11;
/*      */     } 
/*      */     
/* 1514 */     matrices.method_22909();
/*      */   }
/*      */   
/*      */   private static List<String> wordWrap(class_327 textRenderer, String text, int maxWidth) {
/* 1518 */     List<String> result = new ArrayList<>();
/* 1519 */     if (text == null || text.isEmpty()) return result;
/*      */     
/* 1521 */     String[] words = text.split(" ");
/* 1522 */     StringBuilder current = new StringBuilder();
/*      */     
/* 1524 */     for (String word : words) {
/* 1525 */       String test = (current.length() == 0) ? word : (String.valueOf(current) + " " + String.valueOf(current));
/* 1526 */       if (textRenderer.method_1727(test) <= maxWidth) {
/* 1527 */         if (current.length() > 0) current.append(' '); 
/* 1528 */         current.append(word);
/*      */       } else {
/* 1530 */         if (current.length() > 0) {
/* 1531 */           result.add(current.toString());
/* 1532 */           current = new StringBuilder();
/*      */         } 
/*      */         
/* 1535 */         if (textRenderer.method_1727(word) > maxWidth) {
/* 1536 */           StringBuilder chunk = new StringBuilder();
/* 1537 */           for (char c : word.toCharArray()) {
/* 1538 */             String chunkTest = chunk.toString() + chunk.toString();
/* 1539 */             if (textRenderer.method_1727(chunkTest) <= maxWidth) {
/* 1540 */               chunk.append(c);
/*      */             } else {
/* 1542 */               result.add(chunk.toString());
/* 1543 */               chunk = new StringBuilder(String.valueOf(c));
/*      */             } 
/*      */           } 
/* 1546 */           current = chunk;
/*      */         } else {
/* 1548 */           current.append(word);
/*      */         } 
/*      */       } 
/*      */     } 
/* 1552 */     if (current.length() > 0) result.add(current.toString()); 
/* 1553 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static int typeBadgeColor(String type) {
/* 1559 */     if (type == null) return -9930592; 
/* 1560 */     switch (type.toLowerCase()) { case "chat": case "command": case "commands": case "pm": case "pms": case "pay": case "auction": case "reroll": case "crate": case "drops": case "drop":  }  return 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1569 */       -9930592;
/*      */   }
/*      */ 
/*      */   
/*      */   private static int crateSourceBadgeColor(String sourceType) {
/* 1574 */     if (sourceType == null) return -812014; 
/* 1575 */     switch (sourceType.toUpperCase()) { case "POKESPINNER": case "MONTHLY":  }  return 
/*      */ 
/*      */       
/* 1578 */       -812014;
/*      */   }
/*      */ 
/*      */   
/*      */   private static String crateSourceBadge(String sourceType) {
/* 1583 */     if (sourceType == null || sourceType.isEmpty()) return "CRATE"; 
/* 1584 */     return sourceType.toUpperCase();
/*      */   }
/*      */   
/*      */   private static String formatTypeBadge(String type) {
/* 1588 */     if (type == null || type.isEmpty()) return "LOG";
/*      */     
/* 1590 */     return type.substring(0, 1).toUpperCase() + type.substring(0, 1).toUpperCase();
/*      */   }
/*      */   
/*      */   private static String formatTimestampShort(String timestamp) {
/* 1594 */     if (timestamp == null || timestamp.isEmpty()) return "";
/*      */     
/* 1596 */     if (timestamp.length() >= 16) {
/*      */       
/* 1598 */       String normalized = timestamp.replace('T', ' ');
/*      */       
/* 1600 */       if (normalized.length() >= 16) {
/* 1601 */         return normalized.substring(5, 16);
/*      */       }
/*      */     } 
/* 1604 */     return timestamp;
/*      */   }
/*      */   
/*      */   private static String formatTimestamp(String timestamp) {
/* 1608 */     if (timestamp == null || timestamp.isEmpty()) return "";
/*      */     
/* 1610 */     if (timestamp.length() > 16) return timestamp.substring(0, 16).replace('T', ' '); 
/* 1611 */     return timestamp;
/*      */   }
/*      */   
/*      */   private static String truncateText(class_327 textRenderer, String text, int maxWidth) {
/* 1615 */     if (maxWidth <= 0) return ""; 
/* 1616 */     if (textRenderer.method_1727(text) <= maxWidth) return text; 
/* 1617 */     String ellipsis = "...";
/* 1618 */     int ellipsisW = textRenderer.method_1727(ellipsis);
/* 1619 */     if (maxWidth <= ellipsisW) return ellipsis; 
/* 1620 */     int target = maxWidth - ellipsisW;
/* 1621 */     int len = text.length();
/* 1622 */     while (len > 0 && textRenderer.method_1727(text.substring(0, len)) > target) {
/* 1623 */       len--;
/*      */     }
/* 1625 */     return text.substring(0, len) + text.substring(0, len);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
/* 1630 */     if (!this.searchFocused) return false;
/*      */ 
/*      */     
/* 1633 */     if (keyCode == 257) {
/* 1634 */       resetAndRequestFirstPage();
/* 1635 */       return true;
/*      */     } 
/*      */ 
/*      */     
/* 1639 */     if (keyCode == 259 && !this.searchText.isEmpty()) {
/* 1640 */       this.searchText = this.searchText.substring(0, this.searchText.length() - 1);
/* 1641 */       return true;
/*      */     } 
/*      */ 
/*      */     
/* 1645 */     if (keyCode == 256) {
/* 1646 */       this.searchFocused = false;
/* 1647 */       return true;
/*      */     } 
/*      */     
/* 1650 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean charTyped(char chr, int modifiers) {
/* 1655 */     if (!this.searchFocused) return false; 
/* 1656 */     if (chr < ' ') return false; 
/* 1657 */     if (this.searchText.length() < 64) {
/* 1658 */       this.searchText += this.searchText;
/*      */     }
/* 1660 */     return true;
/*      */   }
/*      */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\lookup\screen\views\LookupLogsView.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */