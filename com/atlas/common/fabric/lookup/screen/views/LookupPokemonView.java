/*      */ package com.atlas.common.fabric.lookup.screen.views;
/*      */ 
/*      */ import com.atlas.common.fabric.guide.screen.widgets.GuideScrollableArea;
/*      */ import com.atlas.common.fabric.guide.screen.widgets.GuideTypeBadge;
/*      */ import com.atlas.common.fabric.lookup.data.LookupClientData;
/*      */ import com.atlas.common.fabric.lookup.network.LookupNetwork;
/*      */ import com.atlas.common.fabric.lookup.screen.LookupColors;
/*      */ import com.atlas.common.fabric.lookup.screen.LookupSounds;
/*      */ import com.atlas.common.fabric.lookup.screen.widgets.StatIcon;
/*      */ import com.atlas.common.fabric.screen.PokemonRenderHelper;
/*      */ import com.cobblemon.mod.common.client.render.models.blockbench.FloatingState;
/*      */ import com.google.gson.JsonArray;
/*      */ import com.google.gson.JsonElement;
/*      */ import com.google.gson.JsonObject;
/*      */ import com.google.gson.JsonParser;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.LinkedHashSet;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import java.util.regex.Matcher;
/*      */ import java.util.regex.Pattern;
/*      */ import java.util.stream.Collectors;
/*      */ import net.minecraft.class_2561;
/*      */ import net.minecraft.class_2960;
/*      */ import net.minecraft.class_310;
/*      */ import net.minecraft.class_327;
/*      */ import net.minecraft.class_332;
/*      */ import net.minecraft.class_437;
/*      */ import net.minecraft.class_4587;
/*      */ import net.minecraft.class_5348;
/*      */ import org.joml.Quaternionf;
/*      */ 
/*      */ 
/*      */ public class LookupPokemonView
/*      */   implements LookupView
/*      */ {
/*      */   private int x;
/*      */   private int y;
/*      */   private int width;
/*      */   private int height;
/*      */   private boolean requestSent;
/*   46 */   private final Runnable dataListener = this::onDataChanged;
/*      */   
/*      */   private boolean listenerRegistered;
/*      */   
/*   50 */   private List<JsonObject> partyPokemon = new ArrayList<>();
/*   51 */   private List<JsonObject> pcPokemon = new ArrayList<>();
/*   52 */   private final List<PcBoxData> pcBoxes = new ArrayList<>();
/*      */ 
/*      */   
/*      */   private String lastPokemonJson;
/*      */   
/*      */   private GuideScrollableArea pcScroll;
/*      */   
/*   59 */   private final Map<String, FloatingState> modelStates = new HashMap<>();
/*      */ 
/*      */   
/*   62 */   private int hoveredPartySlot = -1;
/*      */   
/*      */   private JsonObject hoveredPcPokemon;
/*      */   
/*      */   private JsonObject selectedPokemon;
/*      */   
/*      */   private boolean showingDetail;
/*      */   
/*      */   private boolean backButtonHovered;
/*      */   
/*      */   private GuideScrollableArea detailScroll;
/*      */   
/*      */   private boolean takeConfirmPending;
/*      */   
/*      */   private boolean takeButtonHovered;
/*      */   private long takeConfirmTime;
/*      */   private boolean giveConfirmPending;
/*      */   private boolean giveButtonHovered;
/*      */   private int giveBtnX;
/*      */   private int giveBtnY;
/*      */   private int giveBtnW;
/*      */   private int giveBtnH;
/*      */   private boolean pickButtonHovered;
/*      */   private int pickBtnX;
/*      */   private int pickBtnY;
/*      */   private int pickBtnW;
/*      */   private int pickBtnH;
/*      */   private boolean giveMode;
/*   90 */   private final List<JsonObject> myPokemon = new ArrayList<>();
/*      */   private String lastMyPokemonJson;
/*   92 */   private final Set<String> selectedUuids = new LinkedHashSet<>();
/*      */   private GuideScrollableArea givePickerScroll;
/*   94 */   private int givePickerCardW = 80;
/*   95 */   private int givePickerCardH = 50;
/*   96 */   private int givePickerGap = 4;
/*   97 */   private int givePickerCols = 6; private boolean giveSendHovered; private boolean giveCancelHovered; private int giveSendX; private int giveSendY;
/*      */   private int giveSendW;
/*      */   private int giveSendH;
/*      */   private int giveCancelX;
/*      */   private int giveCancelY;
/*      */   private int giveCancelW;
/*      */   private int giveCancelH;
/*      */   private JsonObject givePickerHovered;
/*  105 */   private String pcSearchQuery = "";
/*      */   private boolean pcSearchFocused;
/*      */   private boolean pcSearchHovered;
/*  108 */   private final List<JsonObject> filteredPc = new ArrayList<>();
/*  109 */   private final List<PcBoxData> filteredPcBoxes = new ArrayList<>();
/*      */   
/*      */   private int pcSearchX;
/*      */   
/*      */   private int pcSearchY;
/*      */   
/*      */   private int pcSearchW;
/*      */   
/*      */   private int pcSearchH;
/*      */   
/*      */   private static final int PC_COLUMNS = 5;
/*      */   private static final int PC_CARD_W = 88;
/*      */   private static final int PC_CARD_H = 50;
/*      */   private static final int PC_CARD_GAP = 3;
/*      */   private static final int PC_BOX_COLUMNS = 5;
/*      */   private static final int PC_BOX_ROWS = 6;
/*      */   private static final int PC_BOX_SLOT_COUNT = 30;
/*      */   private static final int PC_BOX_SLOT_W = 88;
/*      */   private static final int PC_BOX_SLOT_H = 50;
/*      */   private static final int PC_BOX_SLOT_GAP = 3;
/*      */   private static final int PC_BOX_HEADER_H = 12;
/*      */   private static final int PC_BOX_PADDING = 4;
/*      */   private static final int PC_BOX_SECTION_GAP = 8;
/*      */   private static final int PC_BOX_COMPACT_H = 16;
/*      */   private static final int MODEL_SIZE = 24;
/*      */   private static final int PARTY_ROWS = 2;
/*      */   private static final int PARTY_SLOT_H = 103;
/*  136 */   private static final String[] STAT_KEYS = StatIcon.KEYS;
/*  137 */   private static final int[] STAT_COLORS = StatIcon.COLORS;
/*  138 */   private static final String[] STAT_NAMES = new String[] { "HP", "Atk", "Def", "SpA", "SpD", "Spe" };
/*      */   
/*      */   private static final int TOOLTIP_BG = -267383266;
/*      */   
/*      */   private static final int TOOLTIP_BORDER = -10777105;
/*      */   private static final int TOOLTIP_PADDING = 5;
/*      */   private static final int TOOLTIP_LINE_HEIGHT = 11;
/*  145 */   private static final Pattern UUID_PATTERN = Pattern.compile("[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[1-5][0-9a-fA-F]{3}-[89aAbB][0-9a-fA-F]{3}-[0-9a-fA-F]{12}");
/*      */   private static Map<String, class_2960> SPECIES_CACHE;
/*      */   
/*      */   private static final class PcBoxData { private final int boxNumber;
/*  149 */     private final JsonObject[] slots = new JsonObject[30];
/*      */     
/*      */     private PcBoxData(int boxNumber) {
/*  152 */       this.boxNumber = boxNumber;
/*      */     }
/*      */     
/*      */     private int matchCount() {
/*  156 */       int count = 0;
/*  157 */       for (JsonObject slot : this.slots) {
/*  158 */         if (slot != null) count++; 
/*      */       } 
/*  160 */       return count;
/*      */     }
/*      */     
/*      */     private boolean isEmpty() {
/*  164 */       return (matchCount() == 0);
/*      */     } }
/*      */ 
/*      */   
/*      */   public LookupPokemonView() {
/*  169 */     PokemonRenderHelper.init();
/*      */   }
/*      */ 
/*      */   
/*      */   public void init(int x, int y, int width, int height) {
/*  174 */     this.x = x;
/*  175 */     this.y = y;
/*  176 */     this.width = width;
/*  177 */     this.height = height;
/*      */ 
/*      */     
/*  180 */     int pcY = y + 103 + 32;
/*  181 */     int pcH = height - 103 - 36;
/*  182 */     if (this.pcScroll == null) {
/*  183 */       this.pcScroll = new GuideScrollableArea(x + 2, pcY, width - 4, pcH);
/*  184 */       this.pcScroll.setThumbColors(-12958368, -10777105);
/*      */     } else {
/*  186 */       this.pcScroll.updateBounds(x + 2, pcY, width - 4, pcH);
/*      */     } 
/*      */ 
/*      */     
/*  190 */     if (this.detailScroll == null) {
/*  191 */       this.detailScroll = new GuideScrollableArea(x + 2, y + 18, width - 4, height - 22);
/*  192 */       this.detailScroll.setThumbColors(-12958368, -10777105);
/*      */     } else {
/*  194 */       this.detailScroll.updateBounds(x + 2, y + 18, width - 4, height - 22);
/*      */     } 
/*      */ 
/*      */     
/*  198 */     if (!this.listenerRegistered) {
/*  199 */       LookupClientData.getInstance().addListener(this.dataListener);
/*  200 */       this.listenerRegistered = true;
/*      */     } 
/*      */     
/*  203 */     if (!this.requestSent) {
/*  204 */       String targetUuid = LookupClientData.getInstance().getTargetUuid();
/*  205 */       if (targetUuid != null) {
/*  206 */         LookupNetwork.requestLookupData("pokemon", 0, targetUuid);
/*  207 */         this.requestSent = true;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void onDataChanged() {
/*  213 */     String json = LookupClientData.getInstance().getPokemonJson();
/*  214 */     if (json != null && !json.equals(this.lastPokemonJson)) {
/*  215 */       this.lastPokemonJson = json;
/*  216 */       parsePokemonData(json);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  221 */     String myJson = LookupClientData.getInstance().getMyPokemonJson();
/*  222 */     if (myJson != null && !myJson.equals(this.lastMyPokemonJson)) {
/*  223 */       this.lastMyPokemonJson = myJson;
/*  224 */       parseMyPokemonData(myJson);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void parseMyPokemonData(String json) {
/*  234 */     this.myPokemon.clear();
/*      */     try {
/*  236 */       JsonObject root = JsonParser.parseString(json).getAsJsonObject();
/*  237 */       if (root.has("party") && root.get("party").isJsonArray())
/*  238 */         for (JsonElement elem : root.getAsJsonArray("party")) {
/*  239 */           if (elem.isJsonObject()) this.myPokemon.add(elem.getAsJsonObject());
/*      */         
/*      */         }  
/*  242 */       if (root.has("pcBoxes") && root.get("pcBoxes").isJsonArray()) {
/*  243 */         for (JsonElement boxElem : root.getAsJsonArray("pcBoxes")) {
/*  244 */           if (!boxElem.isJsonObject())
/*  245 */             continue;  JsonObject boxObj = boxElem.getAsJsonObject();
/*  246 */           if (!boxObj.has("pokemon") || !boxObj.get("pokemon").isJsonArray())
/*  247 */             continue;  for (JsonElement p : boxObj.getAsJsonArray("pokemon")) {
/*  248 */             if (p.isJsonObject()) this.myPokemon.add(p.getAsJsonObject()); 
/*      */           } 
/*      */         } 
/*  251 */       } else if (root.has("pc") && root.get("pc").isJsonArray()) {
/*  252 */         for (JsonElement elem : root.getAsJsonArray("pc")) {
/*  253 */           if (elem.isJsonObject()) this.myPokemon.add(elem.getAsJsonObject()); 
/*      */         } 
/*      */       } 
/*  256 */     } catch (Exception exception) {}
/*      */ 
/*      */     
/*  259 */     Set<String> presentUuids = new HashSet<>();
/*  260 */     for (JsonObject p : this.myPokemon) {
/*  261 */       String u = getString(p, "uuid");
/*  262 */       if (u != null) presentUuids.add(u); 
/*      */     } 
/*  264 */     this.selectedUuids.removeIf(u -> !presentUuids.contains(u));
/*      */   }
/*      */   
/*      */   private void parsePokemonData(String json) {
/*  268 */     this.partyPokemon.clear();
/*  269 */     this.pcPokemon.clear();
/*  270 */     this.pcBoxes.clear();
/*      */     try {
/*  272 */       JsonObject root = JsonParser.parseString(json).getAsJsonObject();
/*      */       
/*  274 */       if (root.has("party") && root.get("party").isJsonArray()) {
/*  275 */         JsonArray party = root.getAsJsonArray("party");
/*  276 */         for (JsonElement elem : party) {
/*  277 */           if (elem.isJsonObject()) {
/*  278 */             this.partyPokemon.add(elem.getAsJsonObject());
/*      */           }
/*      */         } 
/*      */       } 
/*      */       
/*  283 */       if (root.has("pcBoxes") && root.get("pcBoxes").isJsonArray()) {
/*  284 */         JsonArray boxes = root.getAsJsonArray("pcBoxes");
/*  285 */         for (JsonElement boxElem : boxes) {
/*  286 */           if (!boxElem.isJsonObject())
/*  287 */             continue;  JsonObject boxObj = boxElem.getAsJsonObject();
/*  288 */           PcBoxData boxData = new PcBoxData(getInt(boxObj, "box"));
/*  289 */           if (boxObj.has("pokemon") && boxObj.get("pokemon").isJsonArray()) {
/*  290 */             for (JsonElement pokemonElem : boxObj.getAsJsonArray("pokemon")) {
/*  291 */               if (!pokemonElem.isJsonObject())
/*  292 */                 continue;  JsonObject poke = pokemonElem.getAsJsonObject();
/*  293 */               int slot = getInt(poke, "slot");
/*  294 */               if (slot < 0 || slot >= 30)
/*  295 */                 continue;  boxData.slots[slot] = poke;
/*  296 */               this.pcPokemon.add(poke);
/*      */             } 
/*      */           }
/*  299 */           this.pcBoxes.add(boxData);
/*      */         } 
/*  301 */       } else if (root.has("pc") && root.get("pc").isJsonArray()) {
/*  302 */         JsonArray pc = root.getAsJsonArray("pc");
/*  303 */         int sequentialIndex = 0;
/*  304 */         for (JsonElement elem : pc) {
/*  305 */           if (elem.isJsonObject()) {
/*  306 */             JsonObject poke = elem.getAsJsonObject();
/*  307 */             this.pcPokemon.add(poke);
/*      */             
/*  309 */             int boxIndex = sequentialIndex / 30;
/*  310 */             int slotIndex = sequentialIndex % 30;
/*  311 */             while (this.pcBoxes.size() <= boxIndex) {
/*  312 */               this.pcBoxes.add(new PcBoxData(this.pcBoxes.size()));
/*      */             }
/*  314 */             ((PcBoxData)this.pcBoxes.get(boxIndex)).slots[slotIndex] = poke;
/*  315 */             sequentialIndex++;
/*      */           } 
/*      */         } 
/*      */       } 
/*  319 */     } catch (Exception exception) {}
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void render(class_332 ctx, int mouseX, int mouseY, float delta, class_327 textRenderer) {
/*  325 */     onDataChanged();
/*      */     
/*  327 */     if (this.giveMode) {
/*  328 */       renderGivePicker(ctx, mouseX, mouseY, delta, textRenderer);
/*      */       
/*      */       return;
/*      */     } 
/*  332 */     if (this.showingDetail && this.selectedPokemon != null) {
/*  333 */       renderDetailOverlay(ctx, mouseX, mouseY, delta, textRenderer);
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*  338 */     if (this.lastPokemonJson == null) {
/*  339 */       ctx.method_25300(textRenderer, "Loading Pokemon...", this.x + this.width / 2, this.y + this.height / 2, -9930592);
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*  344 */     renderPartySection(ctx, mouseX, mouseY, delta, textRenderer);
/*      */ 
/*      */     
/*  347 */     renderPcSection(ctx, mouseX, mouseY, delta, textRenderer);
/*      */ 
/*      */     
/*  350 */     renderTooltips(ctx, mouseX, mouseY, textRenderer);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void renderPartySection(class_332 ctx, int mouseX, int mouseY, float delta, class_327 textRenderer) {
/*  356 */     int gBg, gText, gBorder, py = this.y + 4;
/*  357 */     ctx.method_51433(textRenderer, "Party", this.x + 6, py, -1, true);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  362 */     String giveLabel = this.giveConfirmPending ? "Click to confirm" : "Give Held Capsule";
/*  363 */     String pickLabel = "Pick from My PC";
/*  364 */     this.giveBtnW = textRenderer.method_1727(giveLabel) + 12;
/*  365 */     this.pickBtnW = textRenderer.method_1727(pickLabel) + 12;
/*  366 */     this.giveBtnH = this.pickBtnH = 14;
/*      */     
/*  368 */     this.pickBtnX = this.x + this.width - this.pickBtnW - 4;
/*  369 */     this.pickBtnY = py - 3;
/*  370 */     this.giveBtnX = this.pickBtnX - this.giveBtnW - 4;
/*  371 */     this.giveBtnY = py - 3;
/*      */     
/*  373 */     this.giveButtonHovered = (mouseX >= this.giveBtnX && mouseX < this.giveBtnX + this.giveBtnW && mouseY >= this.giveBtnY && mouseY < this.giveBtnY + this.giveBtnH);
/*      */     
/*  375 */     this.pickButtonHovered = (mouseX >= this.pickBtnX && mouseX < this.pickBtnX + this.pickBtnW && mouseY >= this.pickBtnY && mouseY < this.pickBtnY + this.pickBtnH);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  380 */     if (this.giveConfirmPending) {
/*  381 */       gBg = this.giveButtonHovered ? -14714337 : -13660369;
/*  382 */       gText = -1;
/*  383 */       gBorder = -11149995;
/*      */     } else {
/*  385 */       gBg = this.giveButtonHovered ? -14274480 : -14801866;
/*  386 */       gText = this.giveButtonHovered ? -1662404 : -1511169;
/*  387 */       gBorder = -14012352;
/*      */     } 
/*  389 */     ctx.method_25294(this.giveBtnX, this.giveBtnY, this.giveBtnX + this.giveBtnW, this.giveBtnY + this.giveBtnH, gBg);
/*  390 */     ctx.method_49601(this.giveBtnX, this.giveBtnY, this.giveBtnW, this.giveBtnH, gBorder);
/*  391 */     int gLblW = textRenderer.method_1727(giveLabel);
/*  392 */     ctx.method_51433(textRenderer, giveLabel, this.giveBtnX + (this.giveBtnW - gLblW) / 2, this.giveBtnY + 3, gText, true);
/*      */ 
/*      */     
/*  395 */     int pBg = this.pickButtonHovered ? -14274480 : -14801866;
/*  396 */     int pText = this.pickButtonHovered ? -10777105 : -1511169;
/*  397 */     int pBorder = this.pickButtonHovered ? -10777105 : -14012352;
/*  398 */     ctx.method_25294(this.pickBtnX, this.pickBtnY, this.pickBtnX + this.pickBtnW, this.pickBtnY + this.pickBtnH, pBg);
/*  399 */     ctx.method_49601(this.pickBtnX, this.pickBtnY, this.pickBtnW, this.pickBtnH, pBorder);
/*  400 */     int pLblW = textRenderer.method_1727(pickLabel);
/*  401 */     ctx.method_51433(textRenderer, pickLabel, this.pickBtnX + (this.pickBtnW - pLblW) / 2, this.pickBtnY + 3, pText, true);
/*      */     
/*  403 */     py += 12;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  410 */     int partySlotW = 88;
/*  411 */     int partySlotH = 50;
/*  412 */     int partyStartX = this.x + 6;
/*      */     
/*  414 */     this.hoveredPartySlot = -1;
/*      */     
/*  416 */     for (int i = 0; i < 6; i++) {
/*  417 */       int col = i % 5;
/*  418 */       int row = i / 5;
/*  419 */       int slotX = partyStartX + col * 91;
/*  420 */       int slotY = py + row * 53;
/*      */       
/*  422 */       boolean hasData = (i < this.partyPokemon.size());
/*  423 */       boolean hovered = (mouseX >= slotX && mouseX < slotX + 88 && mouseY >= slotY && mouseY < slotY + 50);
/*      */       
/*  425 */       if (hovered && hasData) {
/*  426 */         this.hoveredPartySlot = i;
/*  427 */         LookupSounds.playHover("party:" + i);
/*      */       } 
/*      */ 
/*      */       
/*  431 */       int bg = (hovered && hasData) ? -14274480 : -14801866;
/*  432 */       ctx.method_25294(slotX, slotY, slotX + 88, slotY + 50, bg);
/*  433 */       ctx.method_49601(slotX, slotY, 88, 50, (
/*  434 */           hovered && hasData) ? -10777105 : -14012352);
/*      */       
/*  436 */       if (hasData) {
/*  437 */         JsonObject poke = this.partyPokemon.get(i);
/*      */         
/*  439 */         renderPokemonCard(ctx, textRenderer, poke, slotX, slotY, "party_" + i, delta);
/*      */       } else {
/*  441 */         ctx.method_25300(textRenderer, "-", slotX + 44, slotY + 25 - 4, -9930592);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void renderPcSection(class_332 ctx, int mouseX, int mouseY, float delta, class_327 textRenderer) {
/*  449 */     int labelY = this.y + 103 + 20;
/*  450 */     applyPcFilter();
/*      */ 
/*      */     
/*  453 */     String header = this.pcSearchQuery.isEmpty() ? ("PC (" + this.pcPokemon.size() + ")") : ("PC (" + this.filteredPc.size() + "/" + this.pcPokemon.size() + ")");
/*  454 */     ctx.method_51433(textRenderer, header, this.x + 6, labelY, -1, true);
/*      */ 
/*      */     
/*  457 */     this.pcSearchW = Math.min(140, this.width / 2);
/*  458 */     this.pcSearchH = 14;
/*  459 */     this.pcSearchX = this.x + this.width - this.pcSearchW - 4;
/*  460 */     this.pcSearchY = labelY - 3;
/*  461 */     this.pcSearchHovered = (mouseX >= this.pcSearchX && mouseX < this.pcSearchX + this.pcSearchW && mouseY >= this.pcSearchY && mouseY < this.pcSearchY + this.pcSearchH);
/*      */     
/*  463 */     int sBg = this.pcSearchFocused ? -15328732 : -14801866;
/*      */     
/*  465 */     int sBorder = this.pcSearchFocused ? -10777105 : (this.pcSearchHovered ? -10777105 : -14012352);
/*  466 */     ctx.method_25294(this.pcSearchX, this.pcSearchY, this.pcSearchX + this.pcSearchW, this.pcSearchY + this.pcSearchH, sBg);
/*  467 */     ctx.method_49601(this.pcSearchX, this.pcSearchY, this.pcSearchW, this.pcSearchH, sBorder);
/*      */ 
/*      */     
/*  470 */     String display = (this.pcSearchQuery.isEmpty() && !this.pcSearchFocused) ? "Search species or UUID..." : (this.pcSearchQuery + this.pcSearchQuery);
/*      */     
/*  472 */     int displayColor = (this.pcSearchQuery.isEmpty() && !this.pcSearchFocused) ? -9930592 : -1511169;
/*  473 */     ctx.method_51433(textRenderer, display, this.pcSearchX + 4, this.pcSearchY + 3, displayColor, false);
/*      */     
/*  475 */     if (this.pcPokemon.isEmpty()) {
/*  476 */       ctx.method_51433(textRenderer, "No PC Pokemon", this.x + 6, labelY + 14, -9930592, true);
/*      */       return;
/*      */     } 
/*  479 */     if (this.filteredPcBoxes.isEmpty() || this.filteredPc.isEmpty()) {
/*  480 */       ctx.method_51433(textRenderer, "No matches", this.x + 6, labelY + 14, -9930592, true);
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*  485 */     int contentHeight = 4;
/*  486 */     for (int i = 0; i < this.filteredPcBoxes.size(); i++) {
/*  487 */       contentHeight += getPcBoxHeight(this.filteredPcBoxes.get(i));
/*  488 */       if (i < this.filteredPcBoxes.size() - 1) contentHeight += 8; 
/*      */     } 
/*  490 */     this.pcScroll.setContentHeight(contentHeight);
/*      */     
/*  492 */     this.pcScroll.beginRender(ctx);
/*      */     
/*  494 */     int scrollOffset = this.pcScroll.getScrollOffset();
/*  495 */     int startY = this.pcScroll.getY() + 2 - scrollOffset;
/*  496 */     this.hoveredPcPokemon = null;
/*      */     
/*  498 */     for (int boxIndex = 0; boxIndex < this.filteredPcBoxes.size(); boxIndex++) {
/*  499 */       PcBoxData box = this.filteredPcBoxes.get(boxIndex);
/*  500 */       int boxX = this.pcScroll.getX() + 4;
/*  501 */       int boxY = startY;
/*  502 */       int outerW = 460;
/*  503 */       int currentBoxHeight = getPcBoxHeight(box);
/*      */       
/*  505 */       if (boxY + currentBoxHeight < this.pcScroll.getY() || boxY > this.pcScroll.getY() + this.pcScroll.getHeight()) {
/*  506 */         startY += currentBoxHeight + 8;
/*      */       }
/*      */       else {
/*      */         
/*  510 */         ctx.method_25294(boxX, boxY, boxX + outerW, boxY + currentBoxHeight, LookupColors.withAlpha(-15328732, 185));
/*  511 */         ctx.method_49601(boxX, boxY, outerW, currentBoxHeight, -14012352);
/*  512 */         ctx.method_51433(textRenderer, "Box " + box.boxNumber + 1 + " (" + box
/*  513 */             .matchCount() + "/30)", boxX + 4, boxY + 2, -9930592, true);
/*      */ 
/*      */         
/*  516 */         if (box.isEmpty()) {
/*  517 */           startY += currentBoxHeight + 8;
/*      */         }
/*      */         else {
/*      */           
/*  521 */           int gridStartX = boxX + 4;
/*  522 */           int gridStartY = boxY + 12 + 4;
/*  523 */           for (int slotIndex = 0; slotIndex < 30; slotIndex++) {
/*  524 */             int col = slotIndex % 5;
/*  525 */             int row = slotIndex / 5;
/*  526 */             int slotX = gridStartX + col * 91;
/*  527 */             int slotY = gridStartY + row * 53;
/*  528 */             JsonObject poke = box.slots[slotIndex];
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  533 */             boolean hovered = (poke != null && mouseX >= slotX && mouseX < slotX + 88 && mouseY >= slotY && mouseY < slotY + 50 && this.pcScroll.isInBounds(mouseX, mouseY));
/*  534 */             if (hovered) {
/*  535 */               this.hoveredPcPokemon = poke;
/*  536 */               LookupSounds.playHover("pc:" + box.boxNumber + ":" + slotIndex);
/*      */             } 
/*      */             
/*  539 */             int bg = hovered ? -14274480 : -14801866;
/*  540 */             if (poke == null) bg = LookupColors.withAlpha(-15789801, 120); 
/*  541 */             ctx.method_25294(slotX, slotY, slotX + 88, slotY + 50, bg);
/*  542 */             ctx.method_49601(slotX, slotY, 88, 50, 
/*  543 */                 hovered ? -10777105 : -14012352);
/*      */             
/*  545 */             if (poke == null) {
/*  546 */               ctx.method_51433(textRenderer, String.valueOf(slotIndex + 1), slotX + 3, slotY + 3, -9930592, false);
/*      */             } else {
/*  548 */               renderPcBoxSlot(ctx, textRenderer, poke, slotX, slotY, 88, 50, "pc_" + box.boxNumber + "_" + slotIndex, delta, slotIndex);
/*      */             } 
/*      */           } 
/*      */ 
/*      */           
/*  553 */           startY += currentBoxHeight + 8;
/*      */         } 
/*      */       } 
/*  556 */     }  this.pcScroll.endRender(ctx);
/*      */   }
/*      */   
/*      */   private void applyPcFilter() {
/*  560 */     this.filteredPc.clear();
/*  561 */     this.filteredPcBoxes.clear();
/*  562 */     String q = normalizeSearchQuery(this.pcSearchQuery).toLowerCase();
/*  563 */     if (q.isEmpty()) {
/*  564 */       this.filteredPc.addAll(this.pcPokemon);
/*  565 */       this.filteredPcBoxes.addAll(this.pcBoxes);
/*      */       
/*      */       return;
/*      */     } 
/*  569 */     String normalizedQuery = q.replace("-", "");
/*  570 */     for (PcBoxData box : this.pcBoxes) {
/*  571 */       PcBoxData filteredBox = new PcBoxData(box.boxNumber);
/*  572 */       boolean hasMatch = false;
/*  573 */       for (int slot = 0; slot < box.slots.length; slot++) {
/*  574 */         JsonObject poke = box.slots[slot];
/*  575 */         if (poke != null) {
/*      */           
/*  577 */           String species = getString(poke, "species");
/*  578 */           String pretty = formatSpeciesName(species).toLowerCase();
/*  579 */           String raw = (species == null) ? "" : species.toLowerCase();
/*  580 */           String nick = getString(poke, "nickname");
/*  581 */           String nickLower = (nick == null) ? "" : nick.toLowerCase();
/*  582 */           String uuid = getString(poke, "uuid");
/*  583 */           String uuidLower = (uuid == null) ? "" : uuid.toLowerCase();
/*  584 */           String compactUuid = uuidLower.replace("-", "");
/*      */           
/*  586 */           if (pretty.contains(q) || raw.contains(q) || nickLower.contains(q) || uuidLower
/*  587 */             .contains(q) || compactUuid.contains(normalizedQuery)) {
/*  588 */             this.filteredPc.add(poke);
/*  589 */             filteredBox.slots[slot] = poke;
/*  590 */             hasMatch = true;
/*      */           } 
/*      */         } 
/*  593 */       }  if (hasMatch) {
/*  594 */         this.filteredPcBoxes.add(filteredBox);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void renderTooltips(class_332 ctx, int mouseX, int mouseY, class_327 textRenderer) {
/*  602 */     JsonObject hoveredPoke = null;
/*      */     
/*  604 */     if (this.hoveredPartySlot >= 0 && this.hoveredPartySlot < this.partyPokemon.size()) {
/*  605 */       hoveredPoke = this.partyPokemon.get(this.hoveredPartySlot);
/*  606 */     } else if (this.hoveredPcPokemon != null) {
/*  607 */       hoveredPoke = this.hoveredPcPokemon;
/*      */     } 
/*      */     
/*  610 */     if (hoveredPoke == null) {
/*      */       return;
/*      */     }
/*  613 */     List<TooltipLine> lines = new ArrayList<>();
/*      */     
/*  615 */     String species = getString(hoveredPoke, "species");
/*  616 */     String displayName = formatSpeciesName(species);
/*  617 */     lines.add(new TooltipLine("§l" + displayName, -1));
/*      */     
/*  619 */     int level = getInt(hoveredPoke, "level");
/*  620 */     lines.add(new TooltipLine("Level: " + level, -1511169));
/*      */     
/*  622 */     String nature = getString(hoveredPoke, "nature");
/*  623 */     if (nature != null && !nature.isEmpty()) {
/*  624 */       lines.add(new TooltipLine("Nature: " + nature, -1511169));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  630 */     String gender = getString(hoveredPoke, "gender");
/*  631 */     String genderSym = genderSymbol(gender);
/*  632 */     String genderDisplay = genderSym.isEmpty() ? ((gender != null && !gender.isEmpty()) ? gender : "N/A") : genderSym;
/*  633 */     lines.add(new TooltipLine("Gender: " + genderDisplay, genderColor(gender)));
/*      */     
/*  635 */     String heldItem = getString(hoveredPoke, "heldItem");
/*  636 */     if (heldItem != null && !heldItem.isEmpty()) {
/*  637 */       lines.add(new TooltipLine("Held: " + prettifyName(heldItem), -9930592));
/*      */     }
/*      */ 
/*      */     
/*  641 */     List<class_2561> propIcons = StatIcon.propertyIcons(hoveredPoke);
/*      */ 
/*      */     
/*  644 */     JsonObject ivObj = null;
/*  645 */     if (hoveredPoke.has("ivs") && hoveredPoke.get("ivs").isJsonObject()) {
/*  646 */       ivObj = hoveredPoke.getAsJsonObject("ivs");
/*      */     }
/*      */     
/*  649 */     drawTooltipWithBars(ctx, textRenderer, lines, propIcons, ivObj, mouseX, mouseY);
/*      */   }
/*      */   private static final class TooltipLine extends Record { private final String text; private final int color;
/*  652 */     private TooltipLine(String text, int color) { this.text = text; this.color = color; } public final String toString() { // Byte code:
/*      */       //   0: aload_0
/*      */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/lookup/screen/views/LookupPokemonView$TooltipLine;)Ljava/lang/String;
/*      */       //   6: areturn
/*      */       // Line number table:
/*      */       //   Java source line number -> byte code offset
/*      */       //   #652	-> 0
/*      */       // Local variable table:
/*      */       //   start	length	slot	name	descriptor
/*  652 */       //   0	7	0	this	Lcom/atlas/common/fabric/lookup/screen/views/LookupPokemonView$TooltipLine; } public String text() { return this.text; } public final int hashCode() { // Byte code:
/*      */       //   0: aload_0
/*      */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/lookup/screen/views/LookupPokemonView$TooltipLine;)I
/*      */       //   6: ireturn
/*      */       // Line number table:
/*      */       //   Java source line number -> byte code offset
/*      */       //   #652	-> 0
/*      */       // Local variable table:
/*      */       //   start	length	slot	name	descriptor
/*  652 */       //   0	7	0	this	Lcom/atlas/common/fabric/lookup/screen/views/LookupPokemonView$TooltipLine; } public int color() { return this.color; } public final boolean equals(Object o) {
/*      */       // Byte code:
/*      */       //   0: aload_0
/*      */       //   1: aload_1
/*      */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/lookup/screen/views/LookupPokemonView$TooltipLine;Ljava/lang/Object;)Z
/*      */       //   7: ireturn
/*      */       // Line number table:
/*      */       //   Java source line number -> byte code offset
/*      */       //   #652	-> 0
/*      */       // Local variable table:
/*      */       //   start	length	slot	name	descriptor
/*      */       //   0	8	0	this	Lcom/atlas/common/fabric/lookup/screen/views/LookupPokemonView$TooltipLine;
/*      */       //   0	8	1	o	Ljava/lang/Object;
/*      */     } } private void drawTooltip(class_332 ctx, class_327 textRenderer, List<TooltipLine> lines, int mouseX, int mouseY) {
/*  655 */     drawTooltipWithBars(ctx, textRenderer, lines, List.of(), null, mouseX, mouseY);
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
/*      */   private void drawTooltipWithBars(class_332 ctx, class_327 textRenderer, List<TooltipLine> lines, List<class_2561> propIcons, JsonObject ivObj, int mouseX, int mouseY) {
/*  667 */     if (lines.isEmpty() && ivObj == null) {
/*      */       return;
/*      */     }
/*  670 */     int maxTextW = 0;
/*  671 */     for (TooltipLine line : lines) {
/*  672 */       int w = textRenderer.method_1727(line.text);
/*  673 */       if (w > maxTextW) maxTextW = w;
/*      */     
/*      */     } 
/*      */     
/*  677 */     int BAR_SECTION_H = (ivObj != null) ? (STAT_KEYS.length * 10 + 12) : 0;
/*  678 */     int BAR_LABEL_W = 42;
/*  679 */     int BAR_VALUE_W = 18;
/*  680 */     int BAR_FIXED_W = 110;
/*  681 */     int BAR_MIN_W = 178;
/*      */     
/*  683 */     boolean hasPropIcons = (propIcons != null && !propIcons.isEmpty());
/*  684 */     int PROP_ICON_ROW_H = hasPropIcons ? 14 : 0;
/*      */ 
/*      */     
/*  687 */     int minWidth = (ivObj != null) ? 188 : 0;
/*  688 */     int tooltipW = Math.max(maxTextW + 10, minWidth);
/*      */     
/*  690 */     int textBlockH = lines.size() * 11 + 10 - 2;
/*  691 */     int separatorH = (ivObj != null) ? 4 : 0;
/*  692 */     int totalH = textBlockH + PROP_ICON_ROW_H + separatorH + BAR_SECTION_H;
/*      */     
/*  694 */     int tx = mouseX + 10;
/*  695 */     int ty = mouseY - totalH - 4;
/*      */ 
/*      */     
/*  698 */     if (tx + tooltipW > this.x + this.width) tx = mouseX - tooltipW - 4; 
/*  699 */     if (ty < this.y) ty = mouseY + 14;
/*      */     
/*  701 */     ctx.method_51448().method_22903();
/*  702 */     ctx.method_51448().method_46416(0.0F, 0.0F, 400.0F);
/*      */ 
/*      */     
/*  705 */     ctx.method_25294(tx, ty, tx + tooltipW, ty + totalH, -267383266);
/*  706 */     ctx.method_49601(tx, ty, tooltipW, totalH, -10777105);
/*      */ 
/*      */     
/*  709 */     int lineY = ty + 5;
/*  710 */     for (TooltipLine line : lines) {
/*  711 */       ctx.method_51433(textRenderer, line.text, tx + 5, lineY, line.color, true);
/*  712 */       lineY += 11;
/*      */     } 
/*      */ 
/*      */     
/*  716 */     if (hasPropIcons) {
/*  717 */       int ix = tx + 5;
/*  718 */       for (class_2561 icon : propIcons) {
/*  719 */         ctx.method_51439(textRenderer, icon, ix, lineY + 1, -1, false);
/*  720 */         ix += textRenderer.method_27525((class_5348)icon) + 3;
/*      */       } 
/*  722 */       lineY += PROP_ICON_ROW_H;
/*      */     } 
/*      */ 
/*      */     
/*  726 */     if (ivObj != null) {
/*      */       
/*  728 */       lineY += 2;
/*  729 */       ctx.method_25294(tx + 5, lineY, tx + tooltipW - 5, lineY + 1, 
/*  730 */           LookupColors.withAlpha(-14012352, 160));
/*  731 */       lineY += 3;
/*      */       
/*  733 */       int barX = tx + 5 + 42;
/*  734 */       int barW = 110;
/*  735 */       int maxVal = 31;
/*  736 */       int ivTotal = 0;
/*      */       
/*  738 */       for (int s = 0; s < STAT_KEYS.length; s++) {
/*  739 */         int val = ivObj.has(STAT_KEYS[s]) ? ivObj.get(STAT_KEYS[s]).getAsInt() : 0;
/*  740 */         ivTotal += val;
/*      */ 
/*      */ 
/*      */         
/*  744 */         ctx.method_51439(textRenderer, StatIcon.icon(s), tx + 5, lineY + 2, -1, false);
/*      */ 
/*      */ 
/*      */         
/*  748 */         ctx.method_25294(barX, lineY + 2, barX + barW, lineY + 8, -14540254);
/*      */ 
/*      */         
/*  751 */         int fillW = (int)(val / 31.0F * barW);
/*  752 */         if (fillW > 0) {
/*  753 */           ctx.method_25294(barX, lineY + 2, barX + fillW, lineY + 8, STAT_COLORS[s]);
/*      */         }
/*      */ 
/*      */         
/*  757 */         String valStr = String.valueOf(val);
/*  758 */         ctx.method_51433(textRenderer, valStr, barX + barW + 3, lineY, -1, true);
/*      */         
/*  760 */         lineY += 10;
/*      */       } 
/*      */ 
/*      */       
/*  764 */       int pct = (int)(ivTotal * 100.0D / (31 * STAT_KEYS.length));
/*  765 */       String totalStr = "" + ivTotal + "/" + ivTotal + " (" + 31 * STAT_KEYS.length + "%)";
/*  766 */       ctx.method_51433(textRenderer, totalStr, tx + 5, lineY + 1, -10777105, true);
/*      */     } 
/*      */     
/*  769 */     ctx.method_51448().method_22909();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void renderPokemonSlot(class_332 ctx, class_327 textRenderer, JsonObject poke, int sx, int sy, int sw, int sh, String stateKey, float delta) {
/*  776 */     String species = getString(poke, "species");
/*  777 */     int level = getInt(poke, "level");
/*  778 */     String displayName = formatSpeciesName(species);
/*      */ 
/*      */     
/*  781 */     int modelX = sx + (sw - 24) / 2;
/*  782 */     int modelY = sy + 2;
/*  783 */     renderPokemonModel(ctx, species, poke, modelX, modelY, 24, stateKey, delta);
/*      */ 
/*      */     
/*  786 */     String nameText = displayName;
/*  787 */     int maxNameW = sw - 4;
/*  788 */     if (textRenderer.method_1727(nameText) > maxNameW) {
/*  789 */       while (nameText.length() > 1 && textRenderer.method_1727(nameText + "..") > maxNameW) {
/*  790 */         nameText = nameText.substring(0, nameText.length() - 1);
/*      */       }
/*  792 */       nameText = nameText + "..";
/*      */     } 
/*  794 */     ctx.method_25300(textRenderer, nameText, sx + sw / 2, sy + 24 + 4, -1511169);
/*      */ 
/*      */     
/*  797 */     String lvlStr = "Lv" + level;
/*  798 */     ctx.method_25300(textRenderer, lvlStr, sx + sw / 2, sy + sh - 10, -9930592);
/*      */ 
/*      */     
/*  801 */     String gender = getString(poke, "gender");
/*  802 */     String gSym = genderSymbol(gender);
/*  803 */     if (!gSym.isEmpty()) {
/*  804 */       int gw = textRenderer.method_1727(gSym);
/*  805 */       ctx.method_51433(textRenderer, gSym, sx + sw - gw - 3, sy + 2, genderColor(gender), false);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void renderPokemonCard(class_332 ctx, class_327 textRenderer, JsonObject poke, int cx, int cy, String stateKey, float delta) {
/*  811 */     String species = getString(poke, "species");
/*  812 */     int level = getInt(poke, "level");
/*  813 */     String displayName = formatSpeciesName(species);
/*      */ 
/*      */     
/*  816 */     int modelX = cx + 32;
/*  817 */     int modelY = cy + 1;
/*  818 */     renderPokemonModel(ctx, species, poke, modelX, modelY, 24, stateKey, delta);
/*      */ 
/*      */     
/*  821 */     String nameText = displayName;
/*  822 */     int maxW = 82;
/*  823 */     if (textRenderer.method_1727(nameText) > maxW) {
/*  824 */       while (nameText.length() > 1 && textRenderer.method_1727(nameText + "..") > maxW) {
/*  825 */         nameText = nameText.substring(0, nameText.length() - 1);
/*      */       }
/*  827 */       nameText = nameText + "..";
/*      */     } 
/*  829 */     int nameW = textRenderer.method_1727(nameText);
/*  830 */     ctx.method_51433(textRenderer, nameText, cx + (88 - nameW) / 2, cy + 24 + 3, -1511169, true);
/*      */ 
/*      */     
/*  833 */     String lvlStr = "Lv" + level;
/*  834 */     int lvlW = textRenderer.method_1727(lvlStr);
/*  835 */     ctx.method_51433(textRenderer, lvlStr, cx + (88 - lvlW) / 2, cy + 24 + 13, -9930592, true);
/*      */ 
/*      */     
/*  838 */     String gender = getString(poke, "gender");
/*  839 */     String gSym = genderSymbol(gender);
/*  840 */     boolean shiny = hasAspect(poke, "shiny");
/*  841 */     int topRightX = cx + 88 - 3;
/*  842 */     if (shiny) {
/*  843 */       topRightX -= textRenderer.method_1727("★");
/*  844 */       ctx.method_51433(textRenderer, "★", topRightX, cy + 2, -1662404, true);
/*      */     } 
/*  846 */     if (!gSym.isEmpty()) {
/*  847 */       topRightX -= textRenderer.method_1727(gSym) + (shiny ? 1 : 0);
/*  848 */       ctx.method_51433(textRenderer, gSym, topRightX, cy + 2, genderColor(gender), true);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void renderPcBoxSlot(class_332 ctx, class_327 textRenderer, JsonObject poke, int sx, int sy, int sw, int sh, String stateKey, float delta, int slotIndex) {
/*  854 */     renderPokemonCard(ctx, textRenderer, poke, sx, sy, stateKey, delta);
/*  855 */     ctx.method_51433(textRenderer, String.valueOf(slotIndex + 1), sx + 3, sy + 3, -9930592, false);
/*      */   }
/*      */ 
/*      */   
/*      */   private void renderPokemonModel(class_332 ctx, String species, JsonObject poke, int mx, int my, int ms, String stateKey, float delta) {
/*  860 */     if (species == null || species.isEmpty() || !PokemonRenderHelper.isAvailable()) {
/*  861 */       ctx.method_25294(mx, my, mx + ms, my + ms, LookupColors.withAlpha(-15328732, 200));
/*      */       
/*      */       return;
/*      */     } 
/*      */     try {
/*  866 */       class_2960 speciesId = resolveSpeciesId(species);
/*  867 */       FloatingState state = this.modelStates.computeIfAbsent(stateKey, k -> new FloatingState());
/*      */ 
/*      */       
/*  870 */       Set<String> aspects = getAspects(poke);
/*  871 */       state.setCurrentAspects(aspects);
/*      */       
/*  873 */       class_4587 mat = ctx.method_51448();
/*  874 */       mat.method_22903();
/*  875 */       mat.method_22904(mx + ms / 2.0D, my + 2.0D, 0.0D);
/*  876 */       float scale = ms / 9.0F;
/*  877 */       mat.method_22905(scale, scale, 1.0F);
/*      */       
/*  879 */       Quaternionf rot = new Quaternionf();
/*  880 */       rot.rotationXYZ((float)Math.toRadians(13.0D), (float)Math.toRadians(35.0D), 0.0F);
/*      */       
/*  882 */       PokemonRenderHelper.draw(speciesId, mat, rot, state, delta);
/*  883 */       mat.method_22909();
/*  884 */     } catch (Exception e) {
/*  885 */       ctx.method_25294(mx, my, mx + ms, my + ms, LookupColors.withAlpha(-15328732, 200));
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
/*      */   private void renderGivePicker(class_332 ctx, int mouseX, int mouseY, float delta, class_327 textRenderer) {
/*      */     int sBg, sText, sBorder;
/*  898 */     ctx.method_25294(this.x, this.y, this.x + this.width, this.y + this.height, -15789801);
/*      */ 
/*      */     
/*  901 */     String header = "Select Pokemon to send";
/*  902 */     ctx.method_51433(textRenderer, header, this.x + 6, this.y + 4, -1, true);
/*  903 */     String subhead = "Click cards to (de)select; Send transfers them to the target's PC";
/*  904 */     ctx.method_51433(textRenderer, subhead, this.x + 6, this.y + 16, -9930592, true);
/*      */ 
/*      */     
/*  907 */     int footerH = 22;
/*  908 */     int footerY = this.y + this.height - 22;
/*      */     
/*  910 */     this.giveCancelW = 80;
/*  911 */     this.giveCancelH = 18;
/*  912 */     this.giveCancelX = this.x + 6;
/*  913 */     this.giveCancelY = footerY + 2;
/*  914 */     this.giveCancelHovered = (mouseX >= this.giveCancelX && mouseX < this.giveCancelX + this.giveCancelW && mouseY >= this.giveCancelY && mouseY < this.giveCancelY + this.giveCancelH);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  919 */     String sendLabel = this.selectedUuids.isEmpty() ? "Select at least 1" : ("Send " + this.selectedUuids.size() + " to PC");
/*  920 */     this.giveSendW = Math.max(140, textRenderer.method_1727(sendLabel) + 16);
/*  921 */     this.giveSendH = 18;
/*  922 */     this.giveSendX = this.x + this.width - this.giveSendW - 6;
/*  923 */     this.giveSendY = footerY + 2;
/*  924 */     this.giveSendHovered = (mouseX >= this.giveSendX && mouseX < this.giveSendX + this.giveSendW && mouseY >= this.giveSendY && mouseY < this.giveSendY + this.giveSendH);
/*      */ 
/*      */ 
/*      */     
/*  928 */     int gridY = this.y + 30;
/*  929 */     int gridH = footerY - gridY - 4;
/*  930 */     if (this.givePickerScroll == null) {
/*  931 */       this.givePickerScroll = new GuideScrollableArea(this.x + 4, gridY, this.width - 8, gridH);
/*  932 */       this.givePickerScroll.setThumbColors(-12958368, -10777105);
/*      */     } else {
/*  934 */       this.givePickerScroll.updateBounds(this.x + 4, gridY, this.width - 8, gridH);
/*      */     } 
/*      */ 
/*      */     
/*  938 */     if (this.lastMyPokemonJson == null) {
/*  939 */       ctx.method_25300(textRenderer, "Loading your Pokemon...", this.x + this.width / 2, gridY + gridH / 2, -9930592);
/*      */     }
/*  941 */     else if (this.myPokemon.isEmpty()) {
/*  942 */       ctx.method_25300(textRenderer, "You have no Pokemon to send.", this.x + this.width / 2, gridY + gridH / 2, -9930592);
/*      */     } else {
/*      */       
/*  945 */       int innerW = this.givePickerScroll.getContentWidth() - 8;
/*      */       
/*  947 */       int cardW = this.givePickerCardW;
/*  948 */       int gap = this.givePickerGap;
/*  949 */       int cols = Math.max(1, (innerW + gap) / (cardW + gap));
/*  950 */       int rows = (int)Math.ceil(this.myPokemon.size() / cols);
/*  951 */       int contentH = rows * (this.givePickerCardH + gap);
/*  952 */       this.givePickerScroll.setContentHeight(contentH + 4);
/*      */       
/*  954 */       this.givePickerScroll.beginRender(ctx);
/*  955 */       int scrollOffset = this.givePickerScroll.getScrollOffset();
/*  956 */       int gridStartX = this.givePickerScroll.getX() + 4;
/*  957 */       int gridStartY = this.givePickerScroll.getY() + 2 - scrollOffset;
/*      */       
/*  959 */       this.givePickerHovered = null;
/*  960 */       for (int i = 0; i < this.myPokemon.size(); i++) {
/*  961 */         int col = i % cols;
/*  962 */         int row = i / cols;
/*  963 */         int sx = gridStartX + col * (cardW + gap);
/*  964 */         int sy = gridStartY + row * (this.givePickerCardH + gap);
/*      */ 
/*      */         
/*  967 */         if (sy + this.givePickerCardH >= this.givePickerScroll.getY() && sy <= this.givePickerScroll.getY() + this.givePickerScroll.getHeight()) {
/*      */ 
/*      */ 
/*      */           
/*  971 */           JsonObject poke = this.myPokemon.get(i);
/*  972 */           String uuid = getString(poke, "uuid");
/*  973 */           boolean selected = (uuid != null && this.selectedUuids.contains(uuid));
/*      */ 
/*      */           
/*  976 */           boolean hovered = (mouseX >= sx && mouseX < sx + cardW && mouseY >= sy && mouseY < sy + this.givePickerCardH && this.givePickerScroll.isInBounds(mouseX, mouseY));
/*  977 */           if (hovered) {
/*  978 */             this.givePickerHovered = poke;
/*  979 */             LookupSounds.playHover("give:" + i);
/*      */           } 
/*      */           
/*  982 */           int bg = hovered ? -14274480 : -14801866;
/*  983 */           int border = selected ? -11149995 : (hovered ? -10777105 : -14012352);
/*  984 */           ctx.method_25294(sx, sy, sx + cardW, sy + this.givePickerCardH, bg);
/*  985 */           ctx.method_49601(sx, sy, cardW, this.givePickerCardH, border);
/*      */ 
/*      */ 
/*      */           
/*  989 */           renderPokemonCard(ctx, textRenderer, poke, sx + (cardW - 88) / 2, sy, "give_" + i, delta);
/*      */ 
/*      */ 
/*      */           
/*  993 */           if (selected) {
/*  994 */             ctx.method_25294(sx + 2, sy + 2, sx + 12, sy + 12, -14714337);
/*  995 */             ctx.method_49601(sx + 2, sy + 2, 10, 10, -11149995);
/*  996 */             ctx.method_51433(textRenderer, "✓", sx + 4, sy + 3, -1, true);
/*      */           } 
/*      */         } 
/*  999 */       }  this.givePickerScroll.endRender(ctx);
/*      */     } 
/*      */ 
/*      */     
/* 1003 */     ctx.method_25294(this.x + 4, footerY, this.x + this.width - 4, footerY + 1, -14012352);
/*      */ 
/*      */     
/* 1006 */     int cBg = this.giveCancelHovered ? -14274480 : -14801866;
/* 1007 */     ctx.method_25294(this.giveCancelX, this.giveCancelY, this.giveCancelX + this.giveCancelW, this.giveCancelY + this.giveCancelH, cBg);
/* 1008 */     ctx.method_49601(this.giveCancelX, this.giveCancelY, this.giveCancelW, this.giveCancelH, 
/* 1009 */         this.giveCancelHovered ? -10777105 : -14012352);
/* 1010 */     String cancelLbl = "Cancel";
/* 1011 */     int cLblW = textRenderer.method_1727(cancelLbl);
/* 1012 */     ctx.method_51433(textRenderer, cancelLbl, this.giveCancelX + (this.giveCancelW - cLblW) / 2, this.giveCancelY + 5, 
/* 1013 */         this.giveCancelHovered ? -1662404 : -1511169, true);
/*      */ 
/*      */     
/* 1016 */     boolean canSend = !this.selectedUuids.isEmpty();
/*      */     
/* 1018 */     if (!canSend) {
/* 1019 */       sBg = -14801866;
/* 1020 */       sText = -9930592;
/* 1021 */       sBorder = -14012352;
/* 1022 */     } else if (this.giveSendHovered) {
/* 1023 */       sBg = -14714337;
/* 1024 */       sText = -1;
/* 1025 */       sBorder = -11149995;
/*      */     } else {
/* 1027 */       sBg = -13660369;
/* 1028 */       sText = -1;
/* 1029 */       sBorder = -11149995;
/*      */     } 
/* 1031 */     ctx.method_25294(this.giveSendX, this.giveSendY, this.giveSendX + this.giveSendW, this.giveSendY + this.giveSendH, sBg);
/* 1032 */     ctx.method_49601(this.giveSendX, this.giveSendY, this.giveSendW, this.giveSendH, sBorder);
/* 1033 */     int sLblW = textRenderer.method_1727(sendLabel);
/* 1034 */     ctx.method_51433(textRenderer, sendLabel, this.giveSendX + (this.giveSendW - sLblW) / 2, this.giveSendY + 5, sText, true);
/*      */ 
/*      */     
/* 1037 */     if (this.givePickerHovered != null) {
/* 1038 */       renderGivePickerTooltip(ctx, textRenderer, mouseX, mouseY);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void renderGivePickerTooltip(class_332 ctx, class_327 textRenderer, int mouseX, int mouseY) {
/* 1047 */     JsonObject poke = this.givePickerHovered;
/* 1048 */     if (poke == null)
/* 1049 */       return;  List<TooltipLine> lines = new ArrayList<>();
/* 1050 */     String species = getString(poke, "species");
/* 1051 */     lines.add(new TooltipLine("§l" + formatSpeciesName(species), -1));
/* 1052 */     int level = getInt(poke, "level");
/* 1053 */     lines.add(new TooltipLine("Level: " + level, -1511169));
/* 1054 */     String nature = getString(poke, "nature");
/* 1055 */     if (nature != null && !nature.isEmpty()) {
/* 1056 */       lines.add(new TooltipLine("Nature: " + nature, -1511169));
/*      */     }
/* 1058 */     JsonObject ivObj = null;
/* 1059 */     if (poke.has("ivs") && poke.get("ivs").isJsonObject()) {
/* 1060 */       ivObj = poke.getAsJsonObject("ivs");
/*      */     }
/* 1062 */     List<class_2561> propIcons = StatIcon.propertyIcons(poke);
/* 1063 */     drawTooltipWithBars(ctx, textRenderer, lines, propIcons, ivObj, mouseX, mouseY);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void enterGiveMode() {
/* 1071 */     this.giveMode = true;
/* 1072 */     this.selectedUuids.clear();
/*      */     
/* 1074 */     LookupClientData.getInstance().clearMyPokemonJson();
/* 1075 */     this.lastMyPokemonJson = null;
/* 1076 */     this.myPokemon.clear();
/* 1077 */     LookupNetwork.requestLookupData("give_my_pokemon", 0, "");
/*      */   }
/*      */ 
/*      */   
/*      */   private void exitGiveMode() {
/* 1082 */     this.giveMode = false;
/* 1083 */     this.selectedUuids.clear();
/* 1084 */     this.givePickerHovered = null;
/*      */   }
/*      */ 
/*      */   
/*      */   private void sendGiveBatch() {
/* 1089 */     if (this.selectedUuids.isEmpty())
/* 1090 */       return;  String targetUuid = LookupClientData.getInstance().getTargetUuid();
/* 1091 */     if (targetUuid == null)
/* 1092 */       return;  String filter = targetUuid + ":" + targetUuid;
/* 1093 */     LookupNetwork.requestLookupData("give_pokemon_batch", 0, filter);
/*      */ 
/*      */ 
/*      */     
/* 1097 */     this.selectedUuids.clear();
/*      */   }
/*      */ 
/*      */   
/*      */   private void renderDetailOverlay(class_332 ctx, int mouseX, int mouseY, float delta, class_327 textRenderer) {
/*      */     String btnLabel;
/*      */     int btnBg, btnTextColor;
/* 1104 */     ctx.method_25294(this.x, this.y, this.x + this.width, this.y + this.height, -15789801);
/*      */ 
/*      */     
/* 1107 */     int bx = this.x + 6, by = this.y + 4;
/* 1108 */     String backText = "<- Back";
/* 1109 */     int backTextW = textRenderer.method_1727(backText);
/* 1110 */     this.backButtonHovered = (mouseX >= bx && mouseX < bx + backTextW + 4 && mouseY >= by && mouseY < by + 12);
/* 1111 */     int backColor = this.backButtonHovered ? -10777105 : -9930592;
/* 1112 */     ctx.method_51433(textRenderer, backText, bx, by + 2, backColor, true);
/*      */     
/* 1114 */     String species = getString(this.selectedPokemon, "species");
/* 1115 */     int level = getInt(this.selectedPokemon, "level");
/* 1116 */     String displayName = formatSpeciesName(species);
/*      */     
/* 1118 */     this.detailScroll.beginRender(ctx);
/*      */     
/* 1120 */     int scrollOffset = this.detailScroll.getScrollOffset();
/* 1121 */     int cx = this.detailScroll.getX() + 4;
/* 1122 */     int cy = this.detailScroll.getY() + 4 - scrollOffset;
/* 1123 */     int cw = this.detailScroll.getContentWidth() - 8;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1128 */     int leftW = (int)(cw * 0.45D);
/* 1129 */     int rightW = cw - leftW;
/* 1130 */     int rightX = cx + leftW;
/* 1131 */     int topSectionY = cy;
/*      */ 
/*      */     
/* 1134 */     int largeModelSize = 120;
/* 1135 */     int modelX = cx + (leftW - largeModelSize) / 2;
/* 1136 */     int modelY = cy;
/*      */     
/* 1138 */     ctx.method_25294(modelX, modelY, modelX + largeModelSize, modelY + largeModelSize, -14801866);
/* 1139 */     ctx.method_49601(modelX, modelY, largeModelSize, largeModelSize, -14012352);
/* 1140 */     renderPokemonModel(ctx, species, this.selectedPokemon, modelX, modelY, largeModelSize, "detail", delta);
/*      */ 
/*      */     
/* 1143 */     int badgeY = modelY + largeModelSize + 4;
/* 1144 */     renderTypeBadges(ctx, textRenderer, this.selectedPokemon, cx, badgeY, leftW);
/*      */ 
/*      */     
/* 1147 */     int ry = topSectionY + 2;
/* 1148 */     int infoColW = rightW - 6;
/*      */ 
/*      */     
/* 1151 */     ctx.method_51433(textRenderer, "§l" + displayName, rightX, ry, -1, true);
/* 1152 */     String lvlStr = "Lv." + level;
/* 1153 */     int lvlW = textRenderer.method_1727(lvlStr);
/* 1154 */     ctx.method_51433(textRenderer, lvlStr, rightX + infoColW - lvlW, ry, -9930592, true);
/* 1155 */     ry += 14;
/*      */ 
/*      */     
/* 1158 */     String nature = getString(this.selectedPokemon, "nature");
/* 1159 */     String gender = getString(this.selectedPokemon, "gender");
/* 1160 */     boolean shiny = hasAspect(this.selectedPokemon, "shiny");
/* 1161 */     if (nature != null && !nature.isEmpty()) {
/* 1162 */       ctx.method_51433(textRenderer, nature, rightX, ry, -1511169, true);
/*      */     }
/*      */     
/* 1165 */     String gSym = genderSymbol(gender);
/* 1166 */     if (!gSym.isEmpty()) {
/* 1167 */       int natureW = (nature != null && !nature.isEmpty()) ? (textRenderer.method_1727(nature) + 5) : 0;
/* 1168 */       ctx.method_51433(textRenderer, gSym, rightX + natureW, ry, genderColor(gender), true);
/* 1169 */       int gSymW = textRenderer.method_1727(gSym);
/* 1170 */       if (shiny) {
/* 1171 */         ctx.method_51433(textRenderer, " ★", rightX + natureW + gSymW, ry, -1662404, true);
/*      */       }
/* 1173 */     } else if (shiny) {
/* 1174 */       int natureW = (nature != null && !nature.isEmpty()) ? (textRenderer.method_1727(nature) + 5) : 0;
/* 1175 */       ctx.method_51433(textRenderer, "★", rightX + natureW, ry, -1662404, true);
/*      */     } 
/* 1177 */     ry += 13;
/*      */ 
/*      */     
/* 1180 */     String ability = getString(this.selectedPokemon, "ability");
/* 1181 */     drawDetailRow(ctx, textRenderer, "Ability", ability, rightX, ry, infoColW);
/* 1182 */     ry += 13;
/*      */ 
/*      */     
/* 1185 */     String pokeball = getString(this.selectedPokemon, "pokeball");
/* 1186 */     drawDetailRow(ctx, textRenderer, "Ball", prettifyBall(pokeball), rightX, ry, infoColW);
/* 1187 */     ry += 13;
/*      */ 
/*      */     
/* 1190 */     String heldItem = getString(this.selectedPokemon, "heldItem");
/* 1191 */     drawDetailRow(ctx, textRenderer, "Held Item", (heldItem != null && !heldItem.isEmpty()) ? heldItem : "None", rightX, ry, infoColW);
/* 1192 */     ry += 13;
/*      */ 
/*      */     
/* 1195 */     String ot = getString(this.selectedPokemon, "ot");
/* 1196 */     String otUuid = getString(this.selectedPokemon, "otUuid");
/* 1197 */     String otDisplay = (ot != null && !ot.isEmpty()) ? ot : "Unknown";
/* 1198 */     if (otUuid != null && !otUuid.isEmpty()) {
/* 1199 */       otDisplay = otDisplay + " (" + otDisplay + "...)";
/*      */     }
/* 1201 */     drawDetailRow(ctx, textRenderer, "OT", otDisplay, rightX, ry, infoColW);
/* 1202 */     ry += 16;
/*      */ 
/*      */     
/* 1205 */     ctx.method_51433(textRenderer, "Moves", rightX, ry, -1, true);
/* 1206 */     ry += 12;
/* 1207 */     ry = renderMoves(ctx, textRenderer, this.selectedPokemon, rightX, ry, infoColW);
/*      */ 
/*      */     
/* 1210 */     int topSectionBottom = Math.max(badgeY + 18, ry);
/* 1211 */     cy = topSectionBottom + 8;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1216 */     int halfW = cw / 2 - 4;
/* 1217 */     int ivX = cx;
/* 1218 */     int evX = cx + cw / 2 + 2;
/*      */ 
/*      */     
/* 1221 */     ctx.method_51433(textRenderer, "IVs", ivX, cy, -1, true);
/*      */     
/* 1223 */     ctx.method_51433(textRenderer, "EVs", evX, cy, -1, true);
/* 1224 */     int statStartY = cy + 12;
/*      */ 
/*      */     
/* 1227 */     int ivEndY = renderStatBars(ctx, textRenderer, this.selectedPokemon, "ivs", 31, ivX, statStartY, halfW);
/*      */     
/* 1229 */     int evEndY = renderStatBars(ctx, textRenderer, this.selectedPokemon, "evs", 252, evX, statStartY, halfW);
/*      */     
/* 1231 */     cy = Math.max(ivEndY, evEndY) + 10;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1236 */     int btnW = 120;
/* 1237 */     int btnH = 18;
/* 1238 */     int btnX = cx + (cw - btnW) / 2;
/* 1239 */     int btnY = cy;
/* 1240 */     this.takeButtonHovered = (mouseX >= btnX && mouseX < btnX + btnW && mouseY >= btnY && mouseY < btnY + btnH);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1245 */     if (this.takeConfirmPending) {
/* 1246 */       btnLabel = "Click to confirm";
/* 1247 */       btnBg = this.takeButtonHovered ? -3394765 : -1030329;
/* 1248 */       btnTextColor = -1;
/*      */     } else {
/* 1250 */       btnLabel = "Take Pokemon";
/* 1251 */       btnBg = this.takeButtonHovered ? -14274480 : -14801866;
/* 1252 */       btnTextColor = this.takeButtonHovered ? -1662404 : -1511169;
/*      */     } 
/* 1254 */     ctx.method_25294(btnX, btnY, btnX + btnW, btnY + btnH, btnBg);
/* 1255 */     ctx.method_49601(btnX, btnY, btnW, btnH, this.takeConfirmPending ? -1030329 : -14012352);
/* 1256 */     int lblW = textRenderer.method_1727(btnLabel);
/* 1257 */     ctx.method_51433(textRenderer, btnLabel, btnX + (btnW - lblW) / 2, btnY + 5, btnTextColor, true);
/*      */     
/* 1259 */     cy = btnY + btnH + 6;
/*      */     
/* 1261 */     int totalHeight = cy - this.detailScroll.getY() + 4 - scrollOffset + 8;
/* 1262 */     this.detailScroll.setContentHeight(totalHeight);
/* 1263 */     this.detailScroll.endRender(ctx);
/*      */   }
/*      */ 
/*      */   
/*      */   private static String prettifyName(String name) {
/* 1268 */     if (name == null || name.isEmpty()) return "?"; 
/* 1269 */     int colon = name.indexOf(':');
/* 1270 */     if (colon >= 0) name = name.substring(colon + 1); 
/* 1271 */     return Arrays.<String>stream(name.split("_"))
/* 1272 */       .map(w -> w.isEmpty() ? "" : ("" + Character.toUpperCase(w.charAt(0)) + Character.toUpperCase(w.charAt(0))))
/* 1273 */       .collect(Collectors.joining(" "));
/*      */   }
/*      */   
/*      */   private static String prettifyBall(String ball) {
/* 1277 */     if (ball == null || ball.isEmpty()) return "Unknown";
/*      */     
/* 1279 */     int colon = ball.indexOf(':');
/* 1280 */     if (colon >= 0) ball = ball.substring(colon + 1);
/*      */     
/* 1282 */     return Arrays.<String>stream(ball.split("_"))
/* 1283 */       .map(w -> w.isEmpty() ? "" : ("" + Character.toUpperCase(w.charAt(0)) + Character.toUpperCase(w.charAt(0))))
/* 1284 */       .collect(Collectors.joining(" "));
/*      */   }
/*      */ 
/*      */   
/*      */   private int renderTypeBadges(class_332 ctx, class_327 textRenderer, JsonObject poke, int cx, int cy, int cw) {
/* 1289 */     List<String> types = new ArrayList<>();
/* 1290 */     if (poke.has("types") && poke.get("types").isJsonArray()) {
/* 1291 */       for (JsonElement elem : poke.getAsJsonArray("types")) {
/* 1292 */         if (!elem.isJsonNull()) {
/* 1293 */           types.add(elem.getAsString());
/*      */         }
/*      */       } 
/* 1296 */     } else if (poke.has("type1")) {
/* 1297 */       String t1 = getString(poke, "type1");
/* 1298 */       if (t1 != null && !t1.isEmpty()) types.add(t1); 
/* 1299 */       String t2 = getString(poke, "type2");
/* 1300 */       if (t2 != null && !t2.isEmpty()) types.add(t2);
/*      */     
/*      */     } 
/* 1303 */     if (types.isEmpty()) return cy;
/*      */ 
/*      */     
/* 1306 */     int totalW = 0;
/* 1307 */     for (String type : types) {
/* 1308 */       totalW += GuideTypeBadge.getWidth(textRenderer, type) + 4;
/*      */     }
/* 1310 */     totalW -= 4;
/*      */     
/* 1312 */     int badgeX = cx + (cw - totalW) / 2;
/* 1313 */     for (String type : types) {
/* 1314 */       int bw = GuideTypeBadge.draw(ctx, textRenderer, type, badgeX, cy);
/* 1315 */       badgeX += bw + 4;
/*      */     } 
/*      */     
/* 1318 */     return cy + 14;
/*      */   }
/*      */   
/*      */   private int drawDetailRow(class_332 ctx, class_327 textRenderer, String label, String value, int rx, int ry, int rw) {
/* 1322 */     if (value == null) value = "N/A"; 
/* 1323 */     ctx.method_51433(textRenderer, label + ":", rx, ry, -9930592, true);
/* 1324 */     int labelW = textRenderer.method_1727(label + ": ");
/* 1325 */     ctx.method_51433(textRenderer, value, rx + labelW, ry, -1511169, true);
/* 1326 */     return ry + 15;
/*      */   }
/*      */ 
/*      */   
/*      */   private int renderStatBars(class_332 ctx, class_327 textRenderer, JsonObject poke, String statGroupKey, int maxValue, int sx, int sy, int sw) {
/* 1331 */     JsonObject statGroup = null;
/* 1332 */     if (poke.has(statGroupKey) && poke.get(statGroupKey).isJsonObject()) {
/* 1333 */       statGroup = poke.getAsJsonObject(statGroupKey);
/*      */     }
/*      */     
/* 1336 */     int labelW = 30;
/* 1337 */     int valueW = 22;
/* 1338 */     int barX = sx + labelW + valueW + 4;
/* 1339 */     int barW = sw - labelW - valueW - 8;
/* 1340 */     int barH = 4;
/*      */     
/* 1342 */     for (int i = 0; i < STAT_KEYS.length; i++) {
/* 1343 */       int value = 0;
/* 1344 */       if (statGroup != null && statGroup.has(STAT_KEYS[i])) {
/*      */         try {
/* 1346 */           value = statGroup.get(STAT_KEYS[i]).getAsInt();
/* 1347 */         } catch (Exception exception) {}
/*      */       }
/*      */ 
/*      */       
/* 1351 */       ctx.method_51433(textRenderer, STAT_NAMES[i], sx, sy + 1, STAT_COLORS[i], true);
/* 1352 */       ctx.method_51433(textRenderer, String.valueOf(value), sx + labelW, sy + 1, -1511169, true);
/*      */ 
/*      */       
/* 1355 */       ctx.method_25294(barX, sy + 3, barX + barW, sy + 3 + barH, LookupColors.withAlpha(-14012352, 100));
/*      */ 
/*      */       
/* 1358 */       int filled = (int)(barW * Math.min(1.0D, value / maxValue));
/* 1359 */       if (filled > 0) {
/* 1360 */         ctx.method_25294(barX, sy + 3, barX + filled, sy + 3 + barH, STAT_COLORS[i]);
/*      */       }
/*      */       
/* 1363 */       sy += 11;
/*      */     } 
/*      */     
/* 1366 */     return sy;
/*      */   }
/*      */   
/*      */   private int renderMoves(class_332 ctx, class_327 textRenderer, JsonObject poke, int mx, int my, int mw) {
/* 1370 */     if (!poke.has("moves") || !poke.get("moves").isJsonArray()) {
/* 1371 */       ctx.method_51433(textRenderer, "No moves", mx, my, -9930592, true);
/* 1372 */       return my + 12;
/*      */     } 
/*      */     
/* 1375 */     JsonArray moves = poke.getAsJsonArray("moves");
/* 1376 */     for (int i = 0; i < moves.size() && i < 4; i++) {
/*      */       String moveName;
/* 1378 */       JsonElement moveElem = moves.get(i);
/* 1379 */       if (moveElem.isJsonObject()) {
/* 1380 */         moveName = getString(moveElem.getAsJsonObject(), "name");
/* 1381 */         if (moveName == null) moveName = getString(moveElem.getAsJsonObject(), "move"); 
/*      */       } else {
/* 1383 */         moveName = moveElem.getAsString();
/*      */       } 
/* 1385 */       if (moveName == null) moveName = "---";
/*      */ 
/*      */       
/* 1388 */       int slotW = mw / 2 - 2;
/* 1389 */       int col = i % 2;
/* 1390 */       int row = i / 2;
/* 1391 */       int slotX = mx + col * (slotW + 4);
/* 1392 */       int slotY = my + row * 16;
/*      */       
/* 1394 */       ctx.method_25294(slotX, slotY, slotX + slotW, slotY + 13, -14801866);
/* 1395 */       ctx.method_49601(slotX, slotY, slotW, 13, -14012352);
/* 1396 */       ctx.method_51433(textRenderer, moveName, slotX + 4, slotY + 2, -1511169, true);
/*      */     } 
/*      */     
/* 1399 */     int moveRows = Math.min(2, (int)Math.ceil(Math.min(moves.size(), 4) / 2.0D));
/* 1400 */     return my + moveRows * 16 + 4;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean mouseClicked(double mouseX, double mouseY, int button) {
/* 1407 */     if (button != 0) return false;
/*      */ 
/*      */     
/* 1410 */     if (this.giveMode) {
/* 1411 */       if (this.giveCancelHovered) {
/* 1412 */         LookupSounds.playClick();
/* 1413 */         exitGiveMode();
/* 1414 */         return true;
/*      */       } 
/* 1416 */       if (this.giveSendHovered && !this.selectedUuids.isEmpty()) {
/* 1417 */         LookupSounds.playClick();
/* 1418 */         sendGiveBatch();
/* 1419 */         return true;
/*      */       } 
/*      */       
/* 1422 */       if (this.givePickerHovered != null) {
/* 1423 */         String uuid = getString(this.givePickerHovered, "uuid");
/* 1424 */         if (uuid != null) {
/* 1425 */           if (this.selectedUuids.contains(uuid)) { this.selectedUuids.remove(uuid); }
/* 1426 */           else { this.selectedUuids.add(uuid); }
/* 1427 */            LookupSounds.playTabClick();
/*      */         } 
/* 1429 */         return true;
/*      */       } 
/*      */       
/* 1432 */       if (this.givePickerScroll != null) {
/* 1433 */         return this.givePickerScroll.handleMouseClicked(mouseX, mouseY, button);
/*      */       }
/* 1435 */       return false;
/*      */     } 
/*      */ 
/*      */     
/* 1439 */     if (this.showingDetail) {
/* 1440 */       if (this.backButtonHovered) {
/* 1441 */         LookupSounds.playClick();
/* 1442 */         this.showingDetail = false;
/* 1443 */         this.selectedPokemon = null;
/* 1444 */         this.takeConfirmPending = false;
/* 1445 */         this.detailScroll.resetScroll();
/* 1446 */         return true;
/*      */       } 
/*      */       
/* 1449 */       if (this.takeButtonHovered && this.selectedPokemon != null) {
/* 1450 */         if (this.takeConfirmPending) {
/*      */           
/* 1452 */           LookupSounds.playClick();
/* 1453 */           String pokemonUuid = getString(this.selectedPokemon, "uuid");
/* 1454 */           String source = this.partyPokemon.contains(this.selectedPokemon) ? "party" : "pc";
/* 1455 */           String targetUuid = LookupClientData.getInstance().getTargetUuid();
/* 1456 */           if (pokemonUuid != null && targetUuid != null) {
/* 1457 */             LookupNetwork.requestLookupData("take_pokemon", 0, pokemonUuid + ":" + pokemonUuid + ":" + source);
/*      */           }
/* 1459 */           this.showingDetail = false;
/* 1460 */           this.selectedPokemon = null;
/* 1461 */           this.takeConfirmPending = false;
/* 1462 */           return true;
/*      */         } 
/*      */         
/* 1465 */         LookupSounds.playTabClick();
/* 1466 */         this.takeConfirmPending = true;
/* 1467 */         this.takeConfirmTime = System.currentTimeMillis();
/* 1468 */         return true;
/*      */       } 
/*      */ 
/*      */       
/* 1472 */       if (this.takeConfirmPending) this.takeConfirmPending = false; 
/* 1473 */       return this.detailScroll.handleMouseClicked(mouseX, mouseY, button);
/*      */     } 
/*      */ 
/*      */     
/* 1477 */     if (this.pickButtonHovered) {
/* 1478 */       LookupSounds.playClick();
/* 1479 */       enterGiveMode();
/* 1480 */       return true;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1485 */     if (this.giveButtonHovered) {
/* 1486 */       if (this.giveConfirmPending) {
/* 1487 */         LookupSounds.playClick();
/* 1488 */         String targetUuid = LookupClientData.getInstance().getTargetUuid();
/* 1489 */         if (targetUuid != null) {
/* 1490 */           LookupNetwork.requestLookupData("give_pokemon", 0, targetUuid + ":held");
/*      */         }
/* 1492 */         this.giveConfirmPending = false;
/* 1493 */         return true;
/*      */       } 
/* 1495 */       LookupSounds.playTabClick();
/* 1496 */       this.giveConfirmPending = true;
/* 1497 */       return true;
/*      */     } 
/*      */ 
/*      */     
/* 1501 */     if (this.giveConfirmPending) this.giveConfirmPending = false;
/*      */ 
/*      */     
/* 1504 */     if (this.hoveredPartySlot >= 0 && this.hoveredPartySlot < this.partyPokemon.size()) {
/* 1505 */       LookupSounds.playNavigate();
/* 1506 */       this.selectedPokemon = this.partyPokemon.get(this.hoveredPartySlot);
/* 1507 */       this.showingDetail = true;
/* 1508 */       this.takeConfirmPending = false;
/* 1509 */       this.detailScroll.resetScroll();
/* 1510 */       return true;
/*      */     } 
/*      */ 
/*      */     
/* 1514 */     if (mouseX >= this.pcSearchX && mouseX < (this.pcSearchX + this.pcSearchW) && mouseY >= this.pcSearchY && mouseY < (this.pcSearchY + this.pcSearchH)) {
/*      */       
/* 1516 */       this.pcSearchFocused = true;
/* 1517 */       return true;
/*      */     } 
/* 1519 */     if (this.pcSearchFocused) this.pcSearchFocused = false;
/*      */ 
/*      */     
/* 1522 */     if (this.hoveredPcPokemon != null) {
/* 1523 */       LookupSounds.playNavigate();
/* 1524 */       this.selectedPokemon = this.hoveredPcPokemon;
/* 1525 */       this.showingDetail = true;
/* 1526 */       this.takeConfirmPending = false;
/* 1527 */       this.detailScroll.resetScroll();
/* 1528 */       return true;
/*      */     } 
/*      */ 
/*      */     
/* 1532 */     return this.pcScroll.handleMouseClicked(mouseX, mouseY, button);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
/* 1537 */     if (this.giveMode) {
/* 1538 */       return (this.givePickerScroll != null && this.givePickerScroll.handleMouseDragged(mouseX, mouseY, button));
/*      */     }
/* 1540 */     if (this.showingDetail) {
/* 1541 */       return this.detailScroll.handleMouseDragged(mouseX, mouseY, button);
/*      */     }
/* 1543 */     return this.pcScroll.handleMouseDragged(mouseX, mouseY, button);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean mouseReleased(double mouseX, double mouseY, int button) {
/* 1548 */     if (this.giveMode) {
/* 1549 */       return (this.givePickerScroll != null && this.givePickerScroll.handleMouseReleased(mouseX, mouseY, button));
/*      */     }
/* 1551 */     if (this.showingDetail) {
/* 1552 */       return this.detailScroll.handleMouseReleased(mouseX, mouseY, button);
/*      */     }
/* 1554 */     return this.pcScroll.handleMouseReleased(mouseX, mouseY, button);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
/* 1560 */     if (this.giveMode && keyCode == 256) {
/* 1561 */       exitGiveMode();
/* 1562 */       return true;
/*      */     } 
/* 1564 */     if (this.showingDetail || !this.pcSearchFocused) return false; 
/* 1565 */     if (class_437.method_25441() && keyCode == 86) {
/* 1566 */       String clipboard = (class_310.method_1551()).field_1774.method_1460();
/* 1567 */       if (clipboard != null && !clipboard.isEmpty()) {
/* 1568 */         this.pcSearchQuery = sanitizeSearchInput(clipboard);
/* 1569 */         this.pcScroll.resetScroll();
/*      */       } 
/* 1571 */       return true;
/*      */     } 
/* 1573 */     if (keyCode == 259 && !this.pcSearchQuery.isEmpty()) {
/* 1574 */       this.pcSearchQuery = this.pcSearchQuery.substring(0, this.pcSearchQuery.length() - 1);
/* 1575 */       this.pcScroll.resetScroll();
/* 1576 */       return true;
/*      */     } 
/* 1578 */     if (keyCode == 256) {
/* 1579 */       this.pcSearchFocused = false;
/* 1580 */       return true;
/*      */     } 
/* 1582 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean charTyped(char chr, int modifiers) {
/* 1587 */     if (this.showingDetail || !this.pcSearchFocused) return false; 
/* 1588 */     if (chr >= ' ' && this.pcSearchQuery.length() < 64) {
/* 1589 */       this.pcSearchQuery += this.pcSearchQuery;
/* 1590 */       this.pcScroll.resetScroll();
/* 1591 */       return true;
/*      */     } 
/* 1593 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
/* 1598 */     if (this.giveMode) {
/* 1599 */       return (this.givePickerScroll != null && this.givePickerScroll.handleScroll(mouseX, mouseY, verticalAmount));
/*      */     }
/* 1601 */     if (this.showingDetail) {
/* 1602 */       return this.detailScroll.handleScroll(mouseX, mouseY, verticalAmount);
/*      */     }
/* 1604 */     return this.pcScroll.handleScroll(mouseX, mouseY, verticalAmount);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private class_2960 resolveSpeciesId(String species) {
/* 1612 */     if (species == null) return class_2960.method_60655("cobblemon", "missingno");
/*      */ 
/*      */     
/* 1615 */     String slug = species.contains(":") ? species.substring(species.indexOf(':') + 1) : species;
/* 1616 */     return class_2960.method_60655("cobblemon", slug);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static String getString(JsonObject obj, String key) {
/* 1622 */     if (obj == null || !obj.has(key) || obj.get(key).isJsonNull()) return null; 
/* 1623 */     return obj.get(key).getAsString();
/*      */   }
/*      */   
/*      */   private static String normalizeSearchQuery(String query) {
/* 1627 */     if (query == null) return ""; 
/* 1628 */     return sanitizeSearchInput(query).trim();
/*      */   }
/*      */   
/*      */   private static String sanitizeSearchInput(String raw) {
/* 1632 */     if (raw == null) return ""; 
/* 1633 */     String trimmed = raw.trim();
/* 1634 */     Matcher matcher = UUID_PATTERN.matcher(trimmed);
/* 1635 */     if (matcher.find()) {
/* 1636 */       return matcher.group();
/*      */     }
/* 1638 */     return trimmed;
/*      */   }
/*      */   
/*      */   private static int getPcBoxHeight(PcBoxData box) {
/* 1642 */     if (box.isEmpty()) {
/* 1643 */       return 16;
/*      */     }
/* 1645 */     return 335;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static int getInt(JsonObject obj, String key) {
/* 1652 */     if (obj == null || !obj.has(key) || obj.get(key).isJsonNull()) return 0; 
/*      */     try {
/* 1654 */       return obj.get(key).getAsInt();
/* 1655 */     } catch (Exception e) {
/* 1656 */       return 0;
/*      */     } 
/*      */   }
/*      */   
/*      */   private static Set<String> getAspects(JsonObject poke) {
/* 1661 */     Set<String> aspects = new HashSet<>();
/* 1662 */     if (poke != null && poke.has("aspects") && poke.get("aspects").isJsonArray()) {
/* 1663 */       for (JsonElement elem : poke.getAsJsonArray("aspects")) {
/* 1664 */         if (!elem.isJsonNull()) {
/* 1665 */           aspects.add(elem.getAsString());
/*      */         }
/*      */       } 
/*      */     }
/* 1669 */     return aspects;
/*      */   }
/*      */   
/*      */   private static boolean hasAspect(JsonObject poke, String aspect) {
/* 1673 */     return getAspects(poke).contains(aspect);
/*      */   }
/*      */   
/*      */   private static String formatSpeciesName(String species) {
/* 1677 */     if (species == null || species.isEmpty()) return "???";
/*      */     
/* 1679 */     String name = species.contains(":") ? species.substring(species.indexOf(':') + 1) : species;
/*      */     
/* 1681 */     StringBuilder sb = new StringBuilder();
/* 1682 */     for (String word : name.replace('_', ' ').split(" ")) {
/* 1683 */       if (!word.isEmpty()) {
/* 1684 */         if (sb.length() > 0) sb.append(' '); 
/* 1685 */         sb.append(word.substring(0, 1).toUpperCase()).append(word.substring(1));
/*      */       } 
/*      */     } 
/* 1688 */     return sb.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static String genderSymbol(String gender) {
/* 1694 */     if (gender == null) return ""; 
/* 1695 */     switch (gender.toUpperCase()) { case "MALE": case "FEMALE":  }  return 
/*      */ 
/*      */       
/* 1698 */       "";
/*      */   }
/*      */ 
/*      */   
/*      */   private static int genderColor(String gender) {
/* 1703 */     if (gender == null) return -9930592; 
/* 1704 */     switch (gender.toUpperCase()) { case "MALE": case "FEMALE":  }  return 
/*      */ 
/*      */       
/* 1707 */       -9930592;
/*      */   }
/*      */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\lookup\screen\views\LookupPokemonView.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */