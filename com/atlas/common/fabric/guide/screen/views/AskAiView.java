/*      */ package com.atlas.common.fabric.guide.screen.views;
/*      */ 
/*      */ import com.atlas.common.fabric.guide.data.GuideData;
/*      */ import com.atlas.common.fabric.guide.data.SearchResult;
/*      */ import com.atlas.common.fabric.guide.network.GuideNetwork;
/*      */ import com.atlas.common.fabric.guide.screen.GuideColors;
/*      */ import com.atlas.common.fabric.guide.screen.GuideSounds;
/*      */ import com.atlas.common.fabric.guide.screen.widgets.GuideScrollableArea;
/*      */ import com.atlas.common.fabric.guide.screen.widgets.GuideSearchBar;
/*      */ import com.atlas.common.fabric.guide.screen.widgets.GuideTypeBadge;
/*      */ import com.google.gson.JsonObject;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collections;
/*      */ import java.util.HashSet;
/*      */ import java.util.List;
/*      */ import java.util.Set;
/*      */ import java.util.function.BiConsumer;
/*      */ import net.minecraft.class_327;
/*      */ import net.minecraft.class_332;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class AskAiView
/*      */ {
/*      */   private static final int INPUT_BAR_HEIGHT = 20;
/*      */   private static final int INPUT_PADDING = 4;
/*      */   private static final int BUBBLE_PADDING = 6;
/*      */   private static final int BUBBLE_MAX_WIDTH_RATIO = 80;
/*      */   private static final int RESULT_CARD_HEIGHT = 24;
/*      */   private int x;
/*      */   private int y;
/*      */   private int width;
/*      */   private int height;
/*      */   private GuideSearchBar inputBar;
/*      */   private GuideScrollableArea scrollArea;
/*      */   private boolean initialized = false;
/*   39 */   private final List<ChatMessage> chatHistory = new ArrayList<>();
/*      */   private boolean waitingForResponse = false;
/*   41 */   private String lastSentQuery = "";
/*      */ 
/*      */   
/*   44 */   private int hoveredMessageIndex = -1;
/*   45 */   private int hoveredResultIndex = -1;
/*      */ 
/*      */   
/*      */   private BiConsumer<String, String> onResultSelected;
/*      */ 
/*      */   
/*   51 */   private final Set<String> expandedInfoCards = new HashSet<>();
/*      */ 
/*      */   
/*   54 */   private final Set<Integer> expandedResultMessages = new HashSet<>();
/*   55 */   private int hoveredShowMoreIndex = -1;
/*      */ 
/*      */   
/*   58 */   private int feedbackMessageIndex = -1;
/*   59 */   private String feedbackText = "";
/*   60 */   private int feedbackCursorPos = 0;
/*   61 */   private long feedbackBlinkTime = 0L;
/*   62 */   private int hoveredThumbsDownIndex = -1;
/*      */   
/*      */   private boolean hoveredFeedbackSubmit = false;
/*      */   private boolean hoveredFeedbackCancel = false;
/*      */   
/*      */   private static class ChatMessage
/*      */   {
/*      */     final boolean isUser;
/*      */     final String text;
/*      */     final List<SearchResult> results;
/*      */     final long timestamp;
/*      */     final String originalQuery;
/*      */     boolean feedbackSubmitted = false;
/*   75 */     String intent = null;
/*      */     
/*      */     ChatMessage(boolean isUser, String text, List<SearchResult> results) {
/*   78 */       this(isUser, text, results, null);
/*      */     }
/*      */     
/*      */     ChatMessage(boolean isUser, String text, List<SearchResult> results, String originalQuery) {
/*   82 */       this.isUser = isUser;
/*   83 */       this.text = text;
/*   84 */       this.results = (results != null) ? results : Collections.<SearchResult>emptyList();
/*   85 */       this.timestamp = System.currentTimeMillis();
/*   86 */       this.originalQuery = originalQuery;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void init(int x, int y, int width, int height, BiConsumer<String, String> onResultSelected) {
/*   93 */     this.x = x;
/*   94 */     this.y = y;
/*   95 */     this.width = width;
/*   96 */     this.height = height;
/*   97 */     this.onResultSelected = onResultSelected;
/*      */     
/*   99 */     if (!this.initialized) {
/*      */       
/*  101 */       int inputY = y + height - 20 - 4;
/*  102 */       this.inputBar = new GuideSearchBar(x + 4, inputY, width - 8, 20);
/*  103 */       this.inputBar.setPlaceholder("Ask a question about Cobblemon...");
/*  104 */       this.inputBar.setMaxLength(200);
/*  105 */       this.inputBar.setOnTextChanged(text -> {
/*      */           
/*      */           });
/*  108 */       int scrollHeight = height - 20 - 8 - 18;
/*  109 */       this.scrollArea = new GuideScrollableArea(x, y + 18, width, scrollHeight);
/*  110 */       this.initialized = true;
/*      */     } else {
/*  112 */       updateBounds(x, y, width, height);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void updateBounds(int x, int y, int width, int height) {
/*  117 */     this.x = x; this.y = y; this.width = width; this.height = height;
/*  118 */     if (this.inputBar != null) {
/*  119 */       int inputY = y + height - 20 - 4;
/*  120 */       this.inputBar.updateBounds(x + 4, inputY, width - 8, 20);
/*      */     } 
/*  122 */     if (this.scrollArea != null) {
/*  123 */       int scrollHeight = height - 20 - 8 - 18;
/*  124 */       this.scrollArea.updateBounds(x, y + 18, width, scrollHeight);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void render(class_332 ctx, class_327 textRenderer, int mouseX, int mouseY, float delta) {
/*  132 */     checkForResponse();
/*      */     
/*  134 */     ctx.method_25294(this.x, this.y, this.x + this.width, this.y + this.height, GuideColors.SECTION_BG);
/*      */ 
/*      */     
/*  137 */     ctx.method_25300(textRenderer, "Ask AI", this.x + this.width / 2, this.y + 5, GuideColors.TEXT_ACCENT);
/*      */ 
/*      */     
/*  140 */     ctx.method_25294(this.x + 4, this.y + 15, this.x + this.width - 4, this.y + 16, GuideColors.BORDER_DIM);
/*      */ 
/*      */     
/*  143 */     if (this.chatHistory.isEmpty()) {
/*  144 */       renderEmptyState(ctx, textRenderer);
/*      */     } else {
/*  146 */       renderChatMessages(ctx, textRenderer, mouseX, mouseY);
/*      */     } 
/*      */ 
/*      */     
/*  150 */     int inputTop = this.y + this.height - 20 - 4 - 2;
/*  151 */     ctx.method_25294(this.x + 4, inputTop, this.x + this.width - 4, inputTop + 1, GuideColors.BORDER_DIM);
/*      */ 
/*      */     
/*  154 */     this.inputBar.render(ctx, textRenderer, mouseX, mouseY);
/*      */ 
/*      */     
/*  157 */     if (this.waitingForResponse) {
/*  158 */       String dots = ".".repeat((int)(System.currentTimeMillis() / 400L % 4L));
/*  159 */       ctx.method_51433(textRenderer, "Thinking" + dots, this.x + 8, inputTop - 10, GuideColors.TEXT_DIM, true);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void checkForResponse() {
/*  164 */     if (!this.waitingForResponse)
/*      */       return; 
/*  166 */     GuideData data = GuideData.getInstance();
/*  167 */     if (!data.isSearchLoading()) {
/*      */       String responseText;
/*  169 */       this.waitingForResponse = false;
/*  170 */       List<SearchResult> results = new ArrayList<>(data.getSearchResults());
/*      */ 
/*      */       
/*  173 */       String intent = detectQueryIntent(this.lastSentQuery);
/*  174 */       if (intent != null && !results.isEmpty()) {
/*  175 */         reorderByIntent(results, intent);
/*      */       }
/*      */ 
/*      */       
/*  179 */       if (results.isEmpty()) {
/*  180 */         responseText = "I couldn't find anything matching \"" + this.lastSentQuery + "\". Try rephrasing your question or using specific Pokemon/move names!";
/*      */       } else {
/*  182 */         responseText = generateSmartAnswer(this.lastSentQuery, results);
/*      */       } 
/*      */       
/*  185 */       ChatMessage aiMsg = new ChatMessage(false, responseText, results, this.lastSentQuery);
/*  186 */       aiMsg.intent = intent;
/*  187 */       this.chatHistory.add(aiMsg);
/*      */ 
/*      */       
/*  190 */       scrollToBottom();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String generateSmartAnswer(String query, List<SearchResult> results) {
/*  199 */     if (results.isEmpty()) return "I couldn't find an answer.";
/*      */     
/*  201 */     String lower = query.toLowerCase();
/*  202 */     SearchResult top = results.get(0);
/*  203 */     String intent = detectQueryIntent(query);
/*      */ 
/*      */     
/*  206 */     SearchResult bestPokemon = findBestByCategory(results, "pokemon");
/*  207 */     SearchResult bestSpawn = findBestByCategory(results, "spawn");
/*  208 */     SearchResult bestEvolution = findBestByCategory(results, "evolution");
/*  209 */     SearchResult bestMove = findBestByCategory(results, "move");
/*  210 */     SearchResult bestRideable = findBestByCategory(results, "rideable");
/*  211 */     SearchResult bestDrop = findBestByCategory(results, "drop");
/*  212 */     SearchResult bestSkin = findBestByCategory(results, "skin");
/*  213 */     SearchResult bestForm = findBestByCategory(results, "form");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  221 */     if ("spawn".equals(intent)) {
/*  222 */       if (top.category.equals("biome_spawn")) {
/*  223 */         return top.snippet + ". Click below to view them all!";
/*      */       }
/*  225 */       if (bestSpawn != null) {
/*  226 */         return bestSpawn.title + " — " + bestSpawn.title + ". Click below for full spawn details!";
/*      */       }
/*  228 */       if (bestPokemon != null) {
/*  229 */         return "Click below to see where " + bestPokemon.title + " spawns!";
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  234 */     if ("evolution".equals(intent)) {
/*  235 */       if (bestEvolution != null) {
/*  236 */         return bestEvolution.title + " — " + bestEvolution.title + ". Click below for the full evolution chain!";
/*      */       }
/*  238 */       if (bestPokemon != null) {
/*  239 */         return "Click below to see " + bestPokemon.title + "'s evolution chain!";
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  244 */     if ("drop".equals(intent)) {
/*  245 */       if (bestDrop != null) {
/*  246 */         return bestDrop.title + " — " + bestDrop.title + ". Click below for all drop details!";
/*      */       }
/*  248 */       long dropCount = results.stream().filter(r -> r.category.equals("drop")).count();
/*  249 */       if (dropCount > 0L) {
/*  250 */         return "Found " + dropCount + " Pokemon that drop matching items:";
/*      */       }
/*  252 */       if (bestPokemon != null) {
/*  253 */         return "Click below to see what " + bestPokemon.title + " drops!";
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  258 */     if ("skin".equals(intent)) {
/*  259 */       if (bestSkin != null) {
/*  260 */         return bestSkin.title + " — " + bestSkin.title + ". Click below to view!";
/*      */       }
/*  262 */       long skinCount = results.stream().filter(r -> r.category.equals("skin")).count();
/*  263 */       if (skinCount > 0L) {
/*  264 */         return "Found " + skinCount + " Pokemon with matching skins:";
/*      */       }
/*  266 */       if (bestPokemon != null) {
/*  267 */         return "Click below to see " + bestPokemon.title + "'s available skins!";
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  272 */     if ("form".equals(intent)) {
/*  273 */       if (bestForm != null) {
/*  274 */         return bestForm.title + " — " + bestForm.title + ". Click below to view!";
/*      */       }
/*  276 */       long formCount = results.stream().filter(r -> r.category.equals("form")).count();
/*  277 */       if (formCount > 0L) {
/*  278 */         return "Found " + formCount + " Pokemon with matching forms:";
/*      */       }
/*  280 */       if (bestPokemon != null) {
/*  281 */         return "Click below to see " + bestPokemon.title + "'s forms and variants!";
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  286 */     if ("rideable".equals(intent)) {
/*  287 */       if (bestRideable != null)
/*  288 */         return "Yes! " + bestRideable.title + " is rideable — " + bestRideable.snippet + "."; 
/*  289 */       if (bestPokemon != null) {
/*  290 */         return bestPokemon.title + " doesn't appear to be rideable.";
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  295 */     if ("pokemon_moves".equals(intent) && bestPokemon != null) {
/*  296 */       return "Click below to see " + bestPokemon.title + "'s full moveset!";
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  302 */     if (top.category.equals("biome_spawn")) {
/*  303 */       return top.snippet + ". Click below to view them all!";
/*      */     }
/*      */ 
/*      */     
/*  307 */     if (top.category.equals("skin")) {
/*  308 */       long skinCount = results.stream().filter(r -> r.category.equals("skin")).count();
/*  309 */       if (skinCount > 0L) return "Found " + skinCount + " Pokemon with matching skins:";
/*      */     
/*      */     } 
/*      */     
/*  313 */     if (top.category.equals("form")) {
/*  314 */       long formCount = results.stream().filter(r -> r.category.equals("form")).count();
/*  315 */       if (formCount > 0L) return "Found " + formCount + " Pokemon with matching forms:";
/*      */     
/*      */     } 
/*      */     
/*  319 */     if (top.category.equals("drop") && 
/*  320 */       bestDrop != null) return bestDrop.title + " — " + bestDrop.title;
/*      */ 
/*      */ 
/*      */     
/*  324 */     if (lower.contains("type") && !lower.contains("drop") && bestPokemon != null) {
/*  325 */       String types = bestPokemon.extra.replace(",", " / ");
/*  326 */       return bestPokemon.title + " is a " + bestPokemon.title + " type Pokemon.";
/*      */     } 
/*      */ 
/*      */     
/*  330 */     if (top.category.equals("evolution") && bestEvolution != null) {
/*  331 */       return bestEvolution.title + " — " + bestEvolution.title;
/*      */     }
/*      */ 
/*      */     
/*  335 */     if (top.category.equals("spawn") && bestSpawn != null) {
/*  336 */       return bestSpawn.title + " — " + bestSpawn.title;
/*      */     }
/*      */ 
/*      */     
/*  340 */     if (top.category.equals("move")) {
/*  341 */       return top.title + " — " + top.title;
/*      */     }
/*      */ 
/*      */     
/*  345 */     if (top.category.equals("server_info")) {
/*  346 */       return top.snippet;
/*      */     }
/*      */ 
/*      */     
/*  350 */     if (top.category.equals("wiki")) {
/*  351 */       return "I found a guide article that might help: " + top.title;
/*      */     }
/*      */ 
/*      */     
/*  355 */     if (top.category.equals("pokemon") || bestPokemon != null) {
/*  356 */       SearchResult p = top.category.equals("pokemon") ? top : bestPokemon;
/*  357 */       return p.title + " — " + p.title;
/*      */     } 
/*      */ 
/*      */     
/*  361 */     return "Here's what I found about \"" + query + "\":";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private SearchResult findBestByCategory(List<SearchResult> results, String category) {
/*  368 */     for (SearchResult r : results) {
/*  369 */       if (r.category.equals(category)) return r; 
/*      */     } 
/*  371 */     return null;
/*      */   }
/*      */   
/*      */   private void renderEmptyState(class_332 ctx, class_327 textRenderer) {
/*  375 */     int centerX = this.x + this.width / 2;
/*  376 */     int cy = this.scrollArea.getY() + this.scrollArea.getHeight() / 2 - 20;
/*      */     
/*  378 */     ctx.method_25300(textRenderer, "Ask me anything about Cobblemon!", centerX, cy, GuideColors.TEXT_DIM);
/*      */     
/*  380 */     cy += 14;
/*  381 */     ctx.method_25300(textRenderer, "Pokemon, moves, spawns, evolutions & more", centerX, cy, GuideColors.TEXT_MUTED);
/*      */     
/*  383 */     cy += 24;
/*      */ 
/*      */     
/*  386 */     ctx.method_25300(textRenderer, "Try asking:", centerX, cy, GuideColors.TEXT_DIM);
/*  387 */     cy += 12;
/*  388 */     String[] examples = { "\"what pokemon have easter skins\"", "\"what pokemon drop ender pearls\"", "\"what spawns in jungle\"", "\"show me all the megas\"" };
/*  389 */     for (String example : examples) {
/*  390 */       ctx.method_25300(textRenderer, example, centerX, cy, GuideColors.TEXT_MUTED);
/*  391 */       cy += 11;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void renderChatMessages(class_332 ctx, class_327 textRenderer, int mouseX, int mouseY) {
/*  397 */     int totalHeight = calculateTotalContentHeight(textRenderer);
/*  398 */     this.scrollArea.setContentHeight(totalHeight + 8);
/*  399 */     this.scrollArea.beginRender(ctx);
/*      */     
/*  401 */     this.hoveredMessageIndex = -1;
/*  402 */     this.hoveredResultIndex = -1;
/*  403 */     this.hoveredThumbsDownIndex = -1;
/*  404 */     this.hoveredShowMoreIndex = -1;
/*  405 */     this.hoveredFeedbackSubmit = false;
/*  406 */     this.hoveredFeedbackCancel = false;
/*      */     
/*  408 */     int cy = this.scrollArea.getY() + 4 - this.scrollArea.getScrollOffset();
/*      */     
/*  410 */     for (int mi = 0; mi < this.chatHistory.size(); mi++) {
/*  411 */       ChatMessage msg = this.chatHistory.get(mi);
/*      */       
/*  413 */       if (cy > this.scrollArea.getY() + this.scrollArea.getHeight() + 100)
/*      */         break; 
/*  415 */       if (msg.isUser) {
/*  416 */         cy = renderUserBubble(ctx, textRenderer, msg, cy, mouseX, mouseY);
/*      */       } else {
/*  418 */         cy = renderAiBubble(ctx, textRenderer, msg, cy, mi, mouseX, mouseY);
/*      */       } 
/*      */       
/*  421 */       cy += 6;
/*      */     } 
/*      */     
/*  424 */     this.scrollArea.endRender(ctx);
/*      */   }
/*      */ 
/*      */   
/*      */   private int renderUserBubble(class_332 ctx, class_327 textRenderer, ChatMessage msg, int cy, int mouseX, int mouseY) {
/*  429 */     int maxBubbleW = this.width * 80 / 100;
/*  430 */     int textW = Math.min(textRenderer.method_1727(msg.text), maxBubbleW - 12);
/*      */ 
/*      */     
/*  433 */     List<String> lines = wrapText(textRenderer, msg.text, maxBubbleW - 12);
/*  434 */     int lineHeight = 10;
/*  435 */     int bubbleH = lines.size() * lineHeight + 12 - 2;
/*  436 */     int bubbleW = 0;
/*  437 */     for (String line : lines) {
/*  438 */       bubbleW = Math.max(bubbleW, textRenderer.method_1727(line));
/*      */     }
/*  440 */     bubbleW += 12;
/*      */     
/*  442 */     int bubbleX = this.x + this.width - bubbleW - 8;
/*  443 */     int bubbleY = cy;
/*      */ 
/*      */     
/*  446 */     ctx.method_25294(bubbleX, bubbleY, bubbleX + bubbleW, bubbleY + bubbleH, 
/*  447 */         GuideColors.color(30, 80, 60, 220));
/*  448 */     ctx.method_49601(bubbleX, bubbleY, bubbleW, bubbleH, GuideColors.ACCENT_EMERALD);
/*      */ 
/*      */     
/*  451 */     int textY = bubbleY + 6;
/*  452 */     for (String line : lines) {
/*  453 */       ctx.method_51433(textRenderer, line, bubbleX + 6, textY, GuideColors.TEXT_WHITE, true);
/*  454 */       textY += lineHeight;
/*      */     } 
/*      */     
/*  457 */     return bubbleY + bubbleH;
/*      */   }
/*      */ 
/*      */   
/*      */   private int renderAiBubble(class_332 ctx, class_327 textRenderer, ChatMessage msg, int cy, int msgIndex, int mouseX, int mouseY) {
/*  462 */     int maxBubbleW = this.width * 80 / 100;
/*  463 */     int aiLabelOffset = 16;
/*      */ 
/*      */     
/*  466 */     List<String> lines = wrapText(textRenderer, msg.text, maxBubbleW - 12 - aiLabelOffset);
/*  467 */     int lineHeight = 10;
/*  468 */     int textHeight = lines.size() * lineHeight;
/*      */ 
/*      */ 
/*      */     
/*  472 */     int visibleResultCount = msg.results.isEmpty() ? 0 : (this.expandedResultMessages.contains(Integer.valueOf(msgIndex)) ? msg.results.size() : Math.min(3, msg.results.size()));
/*  473 */     int resultsHeight = 0;
/*  474 */     if (!msg.results.isEmpty()) {
/*  475 */       for (int ri = 0; ri < visibleResultCount; ri++) {
/*  476 */         resultsHeight += 26;
/*      */         
/*  478 */         String expandKey = "" + msgIndex + ":" + msgIndex;
/*  479 */         if (this.expandedInfoCards.contains(expandKey) && ((SearchResult)msg.results.get(ri)).category.equals("server_info")) {
/*  480 */           int expandedTextW = maxBubbleW - 12 - 8;
/*  481 */           List<String> expandedLines = wrapText(textRenderer, ((SearchResult)msg.results.get(ri)).snippet, expandedTextW);
/*  482 */           resultsHeight += expandedLines.size() * 10 + 6;
/*      */         } 
/*      */       } 
/*      */       
/*  486 */       if (msg.results.size() > 3) {
/*  487 */         resultsHeight += 14;
/*      */       }
/*      */     } 
/*      */     
/*  491 */     int bubbleH = 12 + textHeight + (msg.results.isEmpty() ? 0 : (4 + resultsHeight)) - 2;
/*      */     
/*  493 */     int bubbleW = maxBubbleW;
/*  494 */     int bubbleX = this.x + 8;
/*  495 */     int bubbleY = cy;
/*      */ 
/*      */     
/*  498 */     ctx.method_25294(bubbleX, bubbleY, bubbleX + bubbleW, bubbleY + bubbleH, 
/*  499 */         GuideColors.color(40, 45, 50, 220));
/*  500 */     ctx.method_49601(bubbleX, bubbleY, bubbleW, bubbleH, GuideColors.BORDER_DIM);
/*      */ 
/*      */     
/*  503 */     ctx.method_51433(textRenderer, "AI", bubbleX + 6, bubbleY + 2, 
/*  504 */         GuideColors.color(100, 200, 160, 255), true);
/*      */ 
/*      */     
/*  507 */     if (!msg.feedbackSubmitted) {
/*  508 */       String thumbLabel = "👎";
/*  509 */       int lblW = textRenderer.method_1727(thumbLabel);
/*  510 */       if (lblW <= 0) lblW = 8; 
/*  511 */       int iconX = bubbleX + bubbleW - 6 - lblW;
/*  512 */       int iconY = bubbleY + 2;
/*      */ 
/*      */ 
/*      */       
/*  516 */       boolean iconHovered = (mouseX >= iconX - 2 && mouseX < iconX + lblW + 2 && mouseY >= iconY - 2 && mouseY < iconY + 10 && this.scrollArea.isInBounds(mouseX, mouseY));
/*  517 */       if (iconHovered) this.hoveredThumbsDownIndex = msgIndex;
/*      */       
/*  519 */       int iconColor = iconHovered ? GuideColors.color(255, 80, 80, 255) : GuideColors.TEXT_MUTED;
/*  520 */       ctx.method_51433(textRenderer, thumbLabel, iconX, iconY, iconColor, true);
/*      */       
/*  522 */       if (iconHovered) {
/*  523 */         String tooltip = "Report bad answer";
/*  524 */         int tipW = textRenderer.method_1727(tooltip) + 6;
/*  525 */         int tipX = iconX - tipW + lblW;
/*  526 */         int tipY = iconY - 12;
/*  527 */         ctx.method_25294(tipX - 2, tipY - 1, tipX + tipW, tipY + 10, GuideColors.color(20, 22, 25, 240));
/*  528 */         ctx.method_49601(tipX - 2, tipY - 1, tipW + 2, 11, GuideColors.BORDER_DIM);
/*  529 */         ctx.method_51433(textRenderer, tooltip, tipX + 1, tipY, GuideColors.TEXT_PRIMARY, true);
/*      */       } 
/*      */     } else {
/*  532 */       String reportedLabel = "Reported ✔";
/*  533 */       int lblW = textRenderer.method_1727(reportedLabel);
/*  534 */       ctx.method_51433(textRenderer, reportedLabel, bubbleX + bubbleW - 6 - lblW, bubbleY + 2, GuideColors.TEXT_MUTED, true);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  539 */     int textY = bubbleY + 6;
/*  540 */     for (String line : lines) {
/*  541 */       ctx.method_51433(textRenderer, line, bubbleX + 6 + aiLabelOffset, textY, GuideColors.TEXT_PRIMARY, true);
/*  542 */       textY += lineHeight;
/*      */     } 
/*      */ 
/*      */     
/*  546 */     if (!msg.results.isEmpty()) {
/*  547 */       textY += 4;
/*  548 */       for (int ri = 0; ri < visibleResultCount; ri++) {
/*  549 */         SearchResult result = msg.results.get(ri);
/*  550 */         int cardX = bubbleX + 6;
/*  551 */         int cardW = bubbleW - 12;
/*      */ 
/*      */ 
/*      */         
/*  555 */         boolean isHovered = (mouseX >= cardX && mouseX < cardX + cardW && mouseY >= textY && mouseY < textY + 24 && this.scrollArea.isInBounds(mouseX, mouseY));
/*      */         
/*  557 */         if (isHovered) {
/*  558 */           this.hoveredMessageIndex = msgIndex;
/*  559 */           this.hoveredResultIndex = ri;
/*      */         } 
/*      */         
/*  562 */         renderResultCard(ctx, textRenderer, result, cardX, textY, cardW, isHovered);
/*  563 */         textY += 26;
/*      */ 
/*      */         
/*  566 */         String expandKey = "" + msgIndex + ":" + msgIndex;
/*  567 */         if (this.expandedInfoCards.contains(expandKey) && result.category.equals("server_info")) {
/*  568 */           int expandedTextW = cardW - 8;
/*  569 */           List<String> expandedLines = wrapText(textRenderer, result.snippet, expandedTextW);
/*  570 */           int expandBg = GuideColors.color(30, 35, 40, 240);
/*  571 */           int expandH = expandedLines.size() * 10 + 4;
/*  572 */           ctx.method_25294(cardX, textY, cardX + cardW, textY + expandH, expandBg);
/*  573 */           ctx.method_49601(cardX, textY, cardW, expandH, GuideColors.color(26, 188, 156, 100));
/*  574 */           int ely = textY + 2;
/*  575 */           for (String eline : expandedLines) {
/*  576 */             ctx.method_51433(textRenderer, eline, cardX + 4, ely, GuideColors.TEXT_PRIMARY, true);
/*  577 */             ely += 10;
/*      */           } 
/*  579 */           textY += expandH + 2;
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/*  584 */       if (msg.results.size() > 3) {
/*  585 */         int cardX = bubbleX + 6;
/*  586 */         int cardW = bubbleW - 12;
/*  587 */         boolean isExpanded = this.expandedResultMessages.contains(Integer.valueOf(msgIndex));
/*  588 */         int remaining = msg.results.size() - 3;
/*  589 */         String toggleLabel = isExpanded ? "▲ Show less" : ("▼ Show " + remaining + " more result" + ((remaining > 1) ? "s" : ""));
/*  590 */         int toggleW = textRenderer.method_1727(toggleLabel) + 8;
/*  591 */         int toggleX = cardX + (cardW - toggleW) / 2;
/*      */ 
/*      */ 
/*      */         
/*  595 */         boolean toggleHovered = (mouseX >= toggleX && mouseX < toggleX + toggleW && mouseY >= textY && mouseY < textY + 12 && this.scrollArea.isInBounds(mouseX, mouseY));
/*  596 */         if (toggleHovered) this.hoveredShowMoreIndex = msgIndex;
/*      */         
/*  598 */         int toggleColor = toggleHovered ? GuideColors.TEXT_WHITE : GuideColors.TEXT_DIM;
/*  599 */         int toggleBg = toggleHovered ? GuideColors.CARD_HOVER_BG : GuideColors.color(0, 0, 0, 0);
/*  600 */         if (toggleHovered) {
/*  601 */           ctx.method_25294(toggleX, textY, toggleX + toggleW, textY + 12, toggleBg);
/*  602 */           ctx.method_49601(toggleX, textY, toggleW, 12, GuideColors.BORDER_DIM);
/*      */         } 
/*  604 */         ctx.method_51433(textRenderer, toggleLabel, toggleX + 4, textY + 2, toggleColor, true);
/*  605 */         textY += 14;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  610 */     int feedbackAreaHeight = 0;
/*  611 */     if (this.feedbackMessageIndex == msgIndex) {
/*  612 */       int inputY = bubbleY + bubbleH + 2;
/*  613 */       int inputW = bubbleW;
/*  614 */       int inputH = 14;
/*      */ 
/*      */       
/*  617 */       ctx.method_25294(bubbleX, inputY, bubbleX + inputW, inputY + inputH, GuideColors.SEARCH_BG);
/*  618 */       ctx.method_49601(bubbleX, inputY, inputW, inputH, GuideColors.SEARCH_BORDER_FOCUS);
/*      */ 
/*      */       
/*  621 */       if (this.feedbackText.isEmpty()) {
/*  622 */         ctx.method_51433(textRenderer, "Optional: describe the issue...", bubbleX + 4, inputY + 3, GuideColors.SEARCH_PLACEHOLDER, false);
/*      */       } else {
/*      */         
/*  625 */         String displayText = this.feedbackText;
/*  626 */         int maxTextW = inputW - 8;
/*  627 */         if (textRenderer.method_1727(displayText) > maxTextW)
/*      */         {
/*  629 */           while (textRenderer.method_1727(displayText) > maxTextW && displayText.length() > 1) {
/*  630 */             displayText = displayText.substring(1);
/*      */           }
/*      */         }
/*  633 */         ctx.method_51433(textRenderer, displayText, bubbleX + 4, inputY + 3, GuideColors.TEXT_PRIMARY, true);
/*      */       } 
/*      */ 
/*      */       
/*  637 */       long elapsed = (System.currentTimeMillis() - this.feedbackBlinkTime) % 1000L;
/*  638 */       if (elapsed < 500L) {
/*  639 */         String beforeCursor = this.feedbackText.substring(0, Math.min(this.feedbackCursorPos, this.feedbackText.length()));
/*  640 */         int cursorX = bubbleX + 4 + textRenderer.method_1727(beforeCursor);
/*  641 */         cursorX = Math.min(cursorX, bubbleX + inputW - 4);
/*  642 */         ctx.method_25294(cursorX, inputY + 2, cursorX + 1, inputY + 12, GuideColors.TEXT_PRIMARY);
/*      */       } 
/*      */ 
/*      */       
/*  646 */       int btnY = inputY + inputH + 2;
/*  647 */       int submitW = textRenderer.method_1727("Submit") + 8;
/*  648 */       int cancelW = textRenderer.method_1727("Cancel") + 8;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  653 */       boolean submitHov = (mouseX >= bubbleX && mouseX < bubbleX + submitW && mouseY >= btnY && mouseY < btnY + 12 && this.scrollArea.isInBounds(mouseX, mouseY));
/*  654 */       if (submitHov) this.hoveredFeedbackSubmit = true; 
/*  655 */       ctx.method_25294(bubbleX, btnY, bubbleX + submitW, btnY + 12, 
/*  656 */           submitHov ? GuideColors.ACCENT_DARK_GREEN : GuideColors.BUTTON_BG);
/*  657 */       ctx.method_49601(bubbleX, btnY, submitW, 12, 
/*  658 */           submitHov ? GuideColors.ACCENT_EMERALD : GuideColors.BUTTON_BORDER);
/*  659 */       ctx.method_51433(textRenderer, "Submit", bubbleX + 4, btnY + 2, 
/*  660 */           submitHov ? GuideColors.TEXT_WHITE : GuideColors.TEXT_PRIMARY, true);
/*      */ 
/*      */       
/*  663 */       int cancelX = bubbleX + submitW + 4;
/*      */ 
/*      */       
/*  666 */       boolean cancelHov = (mouseX >= cancelX && mouseX < cancelX + cancelW && mouseY >= btnY && mouseY < btnY + 12 && this.scrollArea.isInBounds(mouseX, mouseY));
/*  667 */       if (cancelHov) this.hoveredFeedbackCancel = true; 
/*  668 */       ctx.method_25294(cancelX, btnY, cancelX + cancelW, btnY + 12, 
/*  669 */           cancelHov ? GuideColors.CARD_HOVER_BG : GuideColors.BUTTON_BG);
/*  670 */       ctx.method_49601(cancelX, btnY, cancelW, 12, 
/*  671 */           cancelHov ? GuideColors.BORDER_HIGHLIGHT : GuideColors.BUTTON_BORDER);
/*  672 */       ctx.method_51433(textRenderer, "Cancel", cancelX + 4, btnY + 2, 
/*  673 */           cancelHov ? GuideColors.TEXT_WHITE : GuideColors.TEXT_DIM, true);
/*      */       
/*  675 */       feedbackAreaHeight = inputH + 16;
/*      */     } 
/*      */     
/*  678 */     return bubbleY + bubbleH + feedbackAreaHeight;
/*      */   }
/*      */ 
/*      */   
/*      */   private void renderResultCard(class_332 ctx, class_327 textRenderer, SearchResult result, int cx, int cy, int cw, boolean hovered) {
/*  683 */     int bg = hovered ? GuideColors.CARD_HOVER_BG : GuideColors.CARD_BG;
/*  684 */     ctx.method_25294(cx, cy, cx + cw, cy + 24, bg);
/*      */     
/*  686 */     if (hovered) {
/*  687 */       ctx.method_49601(cx, cy, cw, 24, GuideColors.BORDER_HIGHLIGHT);
/*      */     }
/*      */ 
/*      */     
/*  691 */     int catColor = getCategoryColor(result.category);
/*  692 */     String catLabel = result.getCategoryLabel();
/*  693 */     int catW = textRenderer.method_1727(catLabel) + 6;
/*  694 */     ctx.method_25294(cx + 3, cy + 3, cx + 3 + catW, cy + 13, GuideColors.darken(catColor, 80));
/*  695 */     ctx.method_51433(textRenderer, catLabel, cx + 6, cy + 4, catColor, true);
/*      */ 
/*      */     
/*  698 */     int titleX = cx + 3 + catW + 6;
/*  699 */     int titleColor = hovered ? GuideColors.TEXT_WHITE : GuideColors.TEXT_PRIMARY;
/*  700 */     String title = result.title;
/*  701 */     int maxTitleWidth = cw - catW - 20;
/*  702 */     if (textRenderer.method_1727(title) > maxTitleWidth) {
/*  703 */       while (textRenderer.method_1727(title + "...") > maxTitleWidth && title.length() > 1) {
/*  704 */         title = title.substring(0, title.length() - 1);
/*      */       }
/*  706 */       title = title + "...";
/*      */     } 
/*  708 */     ctx.method_51433(textRenderer, title, titleX, cy + 4, titleColor, true);
/*      */ 
/*      */     
/*  711 */     if (!result.extra.isEmpty() && (result.category.equals("pokemon") || result.category.equals("move"))) {
/*  712 */       String[] types = result.extra.split(",\\s*");
/*  713 */       int typeX = cx + cw - 6;
/*  714 */       for (int i = types.length - 1; i >= 0; i--) {
/*  715 */         String type = types[i].trim();
/*  716 */         if (!type.isEmpty()) {
/*  717 */           int tw = GuideTypeBadge.draw(ctx, textRenderer, type, 0, 0);
/*  718 */           typeX -= tw + 2;
/*  719 */           GuideTypeBadge.draw(ctx, textRenderer, type, typeX, cy + 3);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  725 */     if (!result.snippet.isEmpty()) {
/*  726 */       String snippet = result.snippet;
/*  727 */       int maxW = cw - 12;
/*  728 */       if (textRenderer.method_1727(snippet) > maxW) {
/*  729 */         while (textRenderer.method_1727(snippet + "...") > maxW && snippet.length() > 1) {
/*  730 */           snippet = snippet.substring(0, snippet.length() - 1);
/*      */         }
/*  732 */         snippet = snippet + "...";
/*      */       } 
/*  734 */       ctx.method_51433(textRenderer, snippet, cx + 6, cy + 14, GuideColors.TEXT_DIM, true);
/*      */     } 
/*      */ 
/*      */     
/*  738 */     if (hovered) {
/*  739 */       ctx.method_51433(textRenderer, "→", cx + cw - 10, cy + 7, GuideColors.ACCENT_EMERALD, true);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static List<String> wrapText(class_327 textRenderer, String text, int maxWidth) {
/*  746 */     if (maxWidth <= 0) return List.of(text); 
/*  747 */     List<String> lines = new ArrayList<>();
/*  748 */     String[] words = text.split(" ");
/*  749 */     StringBuilder current = new StringBuilder();
/*      */     
/*  751 */     for (String word : words) {
/*  752 */       if (current.length() > 0) {
/*  753 */         String test = String.valueOf(current) + " " + String.valueOf(current);
/*  754 */         if (textRenderer.method_1727(test) > maxWidth) {
/*  755 */           lines.add(current.toString());
/*  756 */           current = new StringBuilder(word);
/*      */         } else {
/*  758 */           current.append(" ").append(word);
/*      */         } 
/*      */       } else {
/*  761 */         current.append(word);
/*      */       } 
/*      */     } 
/*  764 */     if (current.length() > 0) {
/*  765 */       lines.add(current.toString());
/*      */     }
/*  767 */     if (lines.isEmpty()) lines.add(""); 
/*  768 */     return lines;
/*      */   }
/*      */   
/*      */   private int calculateTotalContentHeight(class_327 textRenderer) {
/*  772 */     int total = 0;
/*  773 */     int maxBubbleW = this.width * 80 / 100;
/*  774 */     int lineHeight = 10;
/*      */     
/*  776 */     for (int i = 0; i < this.chatHistory.size(); i++) {
/*  777 */       ChatMessage msg = this.chatHistory.get(i);
/*      */       
/*  779 */       int wrapWidth = msg.isUser ? (maxBubbleW - 12) : (maxBubbleW - 12 - 16);
/*  780 */       List<String> lines = wrapText(textRenderer, msg.text, wrapWidth);
/*  781 */       int textH = lines.size() * lineHeight;
/*      */ 
/*      */       
/*  784 */       int resultsH = 0;
/*  785 */       if (!msg.isUser && !msg.results.isEmpty()) {
/*  786 */         int visCount = this.expandedResultMessages.contains(Integer.valueOf(i)) ? msg.results.size() : Math.min(3, msg.results.size());
/*  787 */         for (int ri = 0; ri < visCount; ri++) {
/*  788 */           resultsH += 26;
/*  789 */           String expandKey = "" + i + ":" + i;
/*  790 */           if (this.expandedInfoCards.contains(expandKey) && ((SearchResult)msg.results.get(ri)).category.equals("server_info")) {
/*  791 */             int expandedTextW = maxBubbleW - 12 - 8;
/*  792 */             List<String> expandedLines = wrapText(textRenderer, ((SearchResult)msg.results.get(ri)).snippet, expandedTextW);
/*  793 */             resultsH += expandedLines.size() * 10 + 8;
/*      */           } 
/*      */         } 
/*      */         
/*  797 */         if (msg.results.size() > 3) {
/*  798 */           resultsH += 14;
/*      */         }
/*      */       } 
/*      */       
/*  802 */       int bubbleH = 12 + textH + (msg.results.isEmpty() ? 0 : (4 + resultsH)) - 2;
/*      */       
/*  804 */       if (!msg.isUser && this.feedbackMessageIndex == i) {
/*  805 */         bubbleH += 30;
/*      */       }
/*  807 */       total += bubbleH + 6;
/*      */     } 
/*      */     
/*  810 */     return total;
/*      */   }
/*      */   
/*      */   private void scrollToBottom() {
/*  814 */     if (this.scrollArea != null) {
/*      */       
/*  816 */       this.scrollArea.setContentHeight(1073741823);
/*  817 */       this.scrollArea.setScrollOffset(this.scrollArea.getMaxScroll());
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
/*      */ 
/*      */   
/*      */   private String detectQueryIntent(String query) {
/*  833 */     String lower = query.toLowerCase();
/*      */ 
/*      */     
/*  836 */     if (lower.contains("spawn") || lower.contains("where") || wordBound(lower, "find") || lower
/*  837 */       .contains("catch") || lower.contains("location") || lower.contains("biome") || lower
/*  838 */       .contains("habitat") || lower.contains("obtain") || lower.contains("encounter")) {
/*  839 */       return "spawn";
/*      */     }
/*      */ 
/*      */     
/*  843 */     if (lower.contains("mega") || lower.contains("alola") || lower.contains("galar") || lower
/*  844 */       .contains("hisui") || lower.contains("paldea") || lower.contains("shiny") || lower
/*  845 */       .contains("variant") || wordBound(lower, "form") || wordBound(lower, "forms")) {
/*  846 */       return "form";
/*      */     }
/*      */ 
/*      */     
/*  850 */     if (lower.contains("evolv") || lower.contains("evolution")) {
/*  851 */       return "evolution";
/*      */     }
/*      */ 
/*      */     
/*  855 */     if (lower.contains("drop") || lower.contains("loot")) {
/*  856 */       return "drop";
/*      */     }
/*      */ 
/*      */     
/*  860 */     if (wordBound(lower, "skin") || wordBound(lower, "skins")) {
/*  861 */       return "skin";
/*      */     }
/*      */ 
/*      */     
/*  865 */     if (wordBound(lower, "ride") || lower.contains("riding") || lower.contains("rideable") || 
/*  866 */       wordBound(lower, "mount")) {
/*  867 */       return "rideable";
/*      */     }
/*      */ 
/*      */     
/*  871 */     if (lower.contains("learn") || lower.contains("moveset") || lower.contains("attack") || lower
/*  872 */       .contains("teach") || wordBound(lower, "move") || wordBound(lower, "moves")) {
/*  873 */       return "pokemon_moves";
/*      */     }
/*      */     
/*  876 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   private static boolean wordBound(String text, String word) {
/*  881 */     int idx = text.indexOf(word);
/*  882 */     while (idx >= 0) {
/*  883 */       boolean startOk = (idx == 0 || !Character.isLetter(text.charAt(idx - 1)));
/*  884 */       int end = idx + word.length();
/*  885 */       boolean endOk = (end >= text.length() || !Character.isLetter(text.charAt(end)));
/*  886 */       if (startOk && endOk) return true; 
/*  887 */       idx = text.indexOf(word, idx + 1);
/*      */     } 
/*  889 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void reorderByIntent(List<SearchResult> results, String intentCategory) {
/*  900 */     Set<String> intentSlugs = new HashSet<>();
/*  901 */     for (SearchResult r : results) {
/*  902 */       if (r.category.equals(intentCategory)) {
/*  903 */         intentSlugs.add(r.slug);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  908 */     results.removeIf(r -> (r.category.equals("pokemon") && intentSlugs.contains(r.slug)));
/*      */ 
/*      */     
/*  911 */     for (SearchResult r : results) {
/*  912 */       if (r.category.equals(intentCategory)) {
/*  913 */         r.relevance += 5.0D;
/*      */       }
/*      */     } 
/*  916 */     results.sort((a, b) -> Double.compare(b.relevance, a.relevance));
/*      */   }
/*      */   
/*      */   private void sendMessage(String text) {
/*  920 */     if (text.trim().isEmpty())
/*      */       return; 
/*  922 */     GuideSounds.playClick();
/*      */ 
/*      */     
/*  925 */     this.chatHistory.add(new ChatMessage(true, text.trim(), null));
/*      */ 
/*      */     
/*  928 */     this.lastSentQuery = text.trim();
/*  929 */     this.waitingForResponse = true;
/*  930 */     GuideData.getInstance().setSearchLoading(true);
/*  931 */     GuideData.getInstance().clearSearchResults();
/*  932 */     GuideNetwork.requestGuideData("search", 0, text.trim(), "");
/*      */ 
/*      */     
/*  935 */     this.inputBar.setText("");
/*      */ 
/*      */     
/*  938 */     scrollToBottom();
/*      */   }
/*      */   
/*      */   private static int getCategoryColor(String category) {
/*  942 */     switch (category) { case "pokemon": case "spawn": case "evolution": case "move": case "rideable": case "wiki": case "skin": case "form": case "drop": case "biome_spawn": case "server_info":  }  return 
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
/*  954 */       GuideColors.TEXT_DIM;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean mouseClicked(double mouseX, double mouseY, int button) {
/*  962 */     if (this.feedbackMessageIndex >= 0 && button == 0) {
/*  963 */       if (this.hoveredFeedbackSubmit) {
/*  964 */         GuideSounds.playClick();
/*  965 */         submitFeedback(this.feedbackMessageIndex);
/*  966 */         return true;
/*      */       } 
/*  968 */       if (this.hoveredFeedbackCancel) {
/*  969 */         GuideSounds.playClick();
/*  970 */         this.feedbackMessageIndex = -1;
/*  971 */         this.feedbackText = "";
/*  972 */         this.feedbackCursorPos = 0;
/*  973 */         return true;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  978 */     if (this.hoveredThumbsDownIndex >= 0 && button == 0) {
/*  979 */       ChatMessage msg = this.chatHistory.get(this.hoveredThumbsDownIndex);
/*  980 */       if (!msg.feedbackSubmitted) {
/*  981 */         GuideSounds.playClick();
/*  982 */         this.feedbackMessageIndex = this.hoveredThumbsDownIndex;
/*  983 */         this.feedbackText = "";
/*  984 */         this.feedbackCursorPos = 0;
/*  985 */         this.feedbackBlinkTime = System.currentTimeMillis();
/*  986 */         return true;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  991 */     if (this.hoveredShowMoreIndex >= 0 && button == 0) {
/*  992 */       GuideSounds.playClick();
/*  993 */       if (this.expandedResultMessages.contains(Integer.valueOf(this.hoveredShowMoreIndex))) {
/*  994 */         this.expandedResultMessages.remove(Integer.valueOf(this.hoveredShowMoreIndex));
/*      */       } else {
/*  996 */         this.expandedResultMessages.add(Integer.valueOf(this.hoveredShowMoreIndex));
/*      */       } 
/*  998 */       return true;
/*      */     } 
/*      */     
/* 1001 */     if (this.inputBar.mouseClicked(mouseX, mouseY, button)) return true;
/*      */ 
/*      */     
/* 1004 */     if (this.hoveredMessageIndex >= 0 && this.hoveredResultIndex >= 0 && button == 0) {
/* 1005 */       ChatMessage msg = this.chatHistory.get(this.hoveredMessageIndex);
/* 1006 */       if (this.hoveredResultIndex < msg.results.size()) {
/* 1007 */         SearchResult result = msg.results.get(this.hoveredResultIndex);
/*      */ 
/*      */         
/* 1010 */         if (result.category.equals("server_info")) {
/* 1011 */           GuideSounds.playClick();
/* 1012 */           String expandKey = "" + this.hoveredMessageIndex + ":" + this.hoveredMessageIndex;
/* 1013 */           if (this.expandedInfoCards.contains(expandKey)) {
/* 1014 */             this.expandedInfoCards.remove(expandKey);
/*      */           } else {
/* 1016 */             this.expandedInfoCards.add(expandKey);
/*      */           } 
/* 1018 */           return true;
/*      */         } 
/*      */ 
/*      */         
/* 1022 */         if (this.onResultSelected != null) {
/* 1023 */           GuideSounds.playNavigate();
/* 1024 */           String navCategory = result.category;
/*      */           
/* 1026 */           if (msg.intent != null && navCategory.equals("pokemon")) {
/* 1027 */             navCategory = msg.intent;
/*      */           }
/* 1029 */           this.onResultSelected.accept(navCategory, result.slug);
/* 1030 */           return true;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 1035 */     if (this.scrollArea != null) {
/* 1036 */       return this.scrollArea.handleMouseClicked(mouseX, mouseY, button);
/*      */     }
/* 1038 */     return false;
/*      */   }
/*      */   
/*      */   private void submitFeedback(int messageIndex) {
/* 1042 */     if (messageIndex < 0 || messageIndex >= this.chatHistory.size())
/* 1043 */       return;  ChatMessage msg = this.chatHistory.get(messageIndex);
/* 1044 */     msg.feedbackSubmitted = true;
/*      */ 
/*      */     
/* 1047 */     JsonObject feedbackObj = new JsonObject();
/*      */     
/* 1049 */     String aiResponse = msg.text;
/* 1050 */     if (aiResponse.length() > 500) aiResponse = aiResponse.substring(0, 500); 
/* 1051 */     feedbackObj.addProperty("aiResponse", aiResponse);
/* 1052 */     feedbackObj.addProperty("feedbackMessage", this.feedbackText);
/*      */     
/* 1054 */     String query = (msg.originalQuery != null) ? msg.originalQuery : "";
/* 1055 */     GuideNetwork.requestGuideData("search_feedback", 0, query, feedbackObj.toString());
/*      */     
/* 1057 */     this.feedbackMessageIndex = -1;
/* 1058 */     this.feedbackText = "";
/* 1059 */     this.feedbackCursorPos = 0;
/*      */   }
/*      */   
/*      */   public boolean mouseScrolled(double mouseX, double mouseY, double h, double v) {
/* 1063 */     if (this.scrollArea != null) return this.scrollArea.handleScroll(mouseX, mouseY, v); 
/* 1064 */     return false;
/*      */   }
/*      */   
/*      */   public boolean mouseDragged(double mouseX, double mouseY, int button) {
/* 1068 */     if (this.scrollArea != null) return this.scrollArea.handleMouseDragged(mouseX, mouseY, button); 
/* 1069 */     return false;
/*      */   }
/*      */   
/*      */   public boolean mouseReleased(double mouseX, double mouseY, int button) {
/* 1073 */     if (this.scrollArea != null) return this.scrollArea.handleMouseReleased(mouseX, mouseY, button); 
/* 1074 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
/* 1079 */     if (this.feedbackMessageIndex >= 0) {
/* 1080 */       if (keyCode == 257) {
/* 1081 */         submitFeedback(this.feedbackMessageIndex);
/* 1082 */         return true;
/*      */       } 
/* 1084 */       if (keyCode == 256) {
/* 1085 */         this.feedbackMessageIndex = -1;
/* 1086 */         this.feedbackText = "";
/* 1087 */         this.feedbackCursorPos = 0;
/* 1088 */         return true;
/*      */       } 
/* 1090 */       if (keyCode == 259 && this.feedbackCursorPos > 0) {
/* 1091 */         this.feedbackText = this.feedbackText.substring(0, this.feedbackCursorPos - 1) + this.feedbackText.substring(0, this.feedbackCursorPos - 1);
/* 1092 */         this.feedbackCursorPos--;
/* 1093 */         this.feedbackBlinkTime = System.currentTimeMillis();
/* 1094 */         return true;
/*      */       } 
/* 1096 */       if (keyCode == 261 && this.feedbackCursorPos < this.feedbackText.length()) {
/* 1097 */         this.feedbackText = this.feedbackText.substring(0, this.feedbackCursorPos) + this.feedbackText.substring(0, this.feedbackCursorPos);
/* 1098 */         this.feedbackBlinkTime = System.currentTimeMillis();
/* 1099 */         return true;
/*      */       } 
/* 1101 */       if (keyCode == 263 && this.feedbackCursorPos > 0) {
/* 1102 */         this.feedbackCursorPos--;
/* 1103 */         this.feedbackBlinkTime = System.currentTimeMillis();
/* 1104 */         return true;
/*      */       } 
/* 1106 */       if (keyCode == 262 && this.feedbackCursorPos < this.feedbackText.length()) {
/* 1107 */         this.feedbackCursorPos++;
/* 1108 */         this.feedbackBlinkTime = System.currentTimeMillis();
/* 1109 */         return true;
/*      */       } 
/* 1111 */       return true;
/*      */     } 
/*      */ 
/*      */     
/* 1115 */     if (keyCode == 257 && this.inputBar.isFocused()) {
/* 1116 */       String text = this.inputBar.getText();
/* 1117 */       if (!text.trim().isEmpty() && !this.waitingForResponse) {
/* 1118 */         sendMessage(text);
/* 1119 */         return true;
/*      */       } 
/* 1121 */       return true;
/*      */     } 
/*      */     
/* 1124 */     return this.inputBar.keyPressed(keyCode, scanCode, modifiers);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean charTyped(char chr, int modifiers) {
/* 1129 */     if (this.feedbackMessageIndex >= 0) {
/* 1130 */       if (chr >= ' ' && this.feedbackText.length() < 200) {
/* 1131 */         this.feedbackText = this.feedbackText.substring(0, this.feedbackCursorPos) + this.feedbackText.substring(0, this.feedbackCursorPos) + chr;
/* 1132 */         this.feedbackCursorPos++;
/* 1133 */         this.feedbackBlinkTime = System.currentTimeMillis();
/*      */       } 
/* 1135 */       return true;
/*      */     } 
/* 1137 */     return this.inputBar.charTyped(chr, modifiers);
/*      */   }
/*      */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\guide\screen\views\AskAiView.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */