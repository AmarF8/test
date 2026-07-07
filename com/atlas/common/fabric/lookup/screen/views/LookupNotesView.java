/*     */ package com.atlas.common.fabric.lookup.screen.views;
/*     */ 
/*     */ import com.atlas.common.fabric.guide.screen.widgets.GuideScrollableArea;
/*     */ import com.atlas.common.fabric.lookup.data.LookupClientData;
/*     */ import com.atlas.common.fabric.lookup.network.LookupNetwork;
/*     */ import com.atlas.common.fabric.lookup.screen.LookupColors;
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParser;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.InputStream;
/*     */ import java.net.URI;
/*     */ import java.net.http.HttpClient;
/*     */ import java.net.http.HttpRequest;
/*     */ import java.net.http.HttpResponse;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Base64;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.CompletableFuture;
/*     */ import net.minecraft.class_1011;
/*     */ import net.minecraft.class_1043;
/*     */ import net.minecraft.class_1044;
/*     */ import net.minecraft.class_2960;
/*     */ import net.minecraft.class_310;
/*     */ import net.minecraft.class_327;
/*     */ import net.minecraft.class_332;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LookupNotesView
/*     */   implements LookupView
/*     */ {
/*  41 */   private static final Logger LOGGER = LoggerFactory.getLogger("LookupNotesView");
/*     */   private static final int HEADER_HEIGHT = 22;
/*     */   private static final int COMPOSER_HEIGHT = 20;
/*     */   private static final int FOOTER_GAP = 4;
/*     */   private static final int PADDING = 6;
/*     */   private static final int HEAD_SIZE = 20;
/*     */   private static final int MAX_NOTE_LENGTH = 280;
/*     */   private int x;
/*     */   private int y;
/*     */   private int width;
/*     */   private int height;
/*     */   private boolean dataRequested = false;
/*     */   private String lastNotesJson;
/*     */   private GuideScrollableArea scrollArea;
/*  55 */   private final Runnable dataListener = this::onDataUpdated;
/*     */   
/*  57 */   private final List<NoteEntry> entries = new ArrayList<>();
/*  58 */   private final List<NoteLayout> layouts = new ArrayList<>();
/*  59 */   private final List<DeleteRegion> deleteRegions = new ArrayList<>();
/*  60 */   private int lastLayoutWidth = -1;
/*     */   
/*  62 */   private String inputText = "";
/*     */   
/*     */   private boolean inputFocused = false;
/*  65 */   private final Map<String, class_2960> headTextures = new HashMap<>();
/*  66 */   private final Set<String> loadingHeads = new HashSet<>();
/*  67 */   private final Set<String> failedHeads = new HashSet<>();
/*     */   private static final class NoteEntry extends Record { private final String id; private final String authorUuid; private final String authorName; private final String timestamp; private final String message; private final boolean canDelete; private final boolean overrideDelete;
/*  69 */     private NoteEntry(String id, String authorUuid, String authorName, String timestamp, String message, boolean canDelete, boolean overrideDelete) { this.id = id; this.authorUuid = authorUuid; this.authorName = authorName; this.timestamp = timestamp; this.message = message; this.canDelete = canDelete; this.overrideDelete = overrideDelete; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/lookup/screen/views/LookupNotesView$NoteEntry;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #69	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*  69 */       //   0	7	0	this	Lcom/atlas/common/fabric/lookup/screen/views/LookupNotesView$NoteEntry; } public String id() { return this.id; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/lookup/screen/views/LookupNotesView$NoteEntry;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #69	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/lookup/screen/views/LookupNotesView$NoteEntry; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/lookup/screen/views/LookupNotesView$NoteEntry;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #69	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lcom/atlas/common/fabric/lookup/screen/views/LookupNotesView$NoteEntry;
/*  69 */       //   0	8	1	o	Ljava/lang/Object; } public String authorUuid() { return this.authorUuid; } public String authorName() { return this.authorName; } public String timestamp() { return this.timestamp; } public String message() { return this.message; } public boolean canDelete() { return this.canDelete; } public boolean overrideDelete() { return this.overrideDelete; }
/*     */      }
/*     */ 
/*     */   
/*     */   private static final class NoteLayout extends Record {
/*     */     private final LookupNotesView.NoteEntry entry;
/*     */     private final List<String> lines;
/*     */     private final int height;
/*     */     private final String buttonLabel;
/*     */     
/*  79 */     private NoteLayout(LookupNotesView.NoteEntry entry, List<String> lines, int height, String buttonLabel) { this.entry = entry; this.lines = lines; this.height = height; this.buttonLabel = buttonLabel; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/lookup/screen/views/LookupNotesView$NoteLayout;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #79	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/lookup/screen/views/LookupNotesView$NoteLayout; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/lookup/screen/views/LookupNotesView$NoteLayout;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #79	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/lookup/screen/views/LookupNotesView$NoteLayout; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/lookup/screen/views/LookupNotesView$NoteLayout;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #79	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lcom/atlas/common/fabric/lookup/screen/views/LookupNotesView$NoteLayout;
/*  79 */       //   0	8	1	o	Ljava/lang/Object; } public LookupNotesView.NoteEntry entry() { return this.entry; } public List<String> lines() { return this.lines; } public int height() { return this.height; } public String buttonLabel() { return this.buttonLabel; }
/*     */      } private static final class DeleteRegion extends Record { private final int x; private final int y; private final int width; private final int height; private final String noteId;
/*  81 */     private DeleteRegion(int x, int y, int width, int height, String noteId) { this.x = x; this.y = y; this.width = width; this.height = height; this.noteId = noteId; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/lookup/screen/views/LookupNotesView$DeleteRegion;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #81	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/lookup/screen/views/LookupNotesView$DeleteRegion; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/lookup/screen/views/LookupNotesView$DeleteRegion;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #81	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/lookup/screen/views/LookupNotesView$DeleteRegion; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/lookup/screen/views/LookupNotesView$DeleteRegion;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #81	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lcom/atlas/common/fabric/lookup/screen/views/LookupNotesView$DeleteRegion;
/*  81 */       //   0	8	1	o	Ljava/lang/Object; } public int x() { return this.x; } public int y() { return this.y; } public int width() { return this.width; } public int height() { return this.height; } public String noteId() { return this.noteId; }
/*     */      }
/*     */   
/*     */   public void init(int x, int y, int width, int height) {
/*  85 */     this.x = x;
/*  86 */     this.y = y;
/*  87 */     this.width = width;
/*  88 */     this.height = height;
/*     */     
/*  90 */     int contentTop = y + 22 + 20 + 4;
/*  91 */     int contentHeight = height - 22 - 20 - 4;
/*  92 */     if (this.scrollArea == null) {
/*  93 */       this.scrollArea = new GuideScrollableArea(x, contentTop, width, contentHeight);
/*  94 */       this.scrollArea.setThumbColors(-12958368, -10777105);
/*     */     } else {
/*  96 */       this.scrollArea.updateBounds(x, contentTop, width, contentHeight);
/*     */     } 
/*     */     
/*  99 */     LookupClientData data = LookupClientData.getInstance();
/* 100 */     data.removeListener(this.dataListener);
/* 101 */     data.addListener(this.dataListener);
/*     */     
/* 103 */     if (!this.dataRequested) {
/* 104 */       this.dataRequested = true;
/* 105 */       requestNotes();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void requestNotes() {
/* 110 */     String uuid = LookupClientData.getInstance().getTargetUuid();
/* 111 */     LookupNetwork.requestLookupData("notes", 0, (uuid != null) ? uuid : "");
/*     */   }
/*     */   
/*     */   private void onDataUpdated() {
/* 115 */     String currentJson = LookupClientData.getInstance().getNotesJson();
/* 116 */     if (!Objects.equals(currentJson, this.lastNotesJson)) {
/* 117 */       parseData(currentJson);
/*     */     }
/*     */   }
/*     */   
/*     */   private void parseData(String json) {
/* 122 */     this.entries.clear();
/* 123 */     this.layouts.clear();
/* 124 */     this.deleteRegions.clear();
/* 125 */     this.lastLayoutWidth = -1;
/* 126 */     this.lastNotesJson = json;
/*     */     
/* 128 */     if (json == null || json.isEmpty()) {
/* 129 */       if (this.scrollArea != null) this.scrollArea.setContentHeight(6);
/*     */       
/*     */       return;
/*     */     } 
/*     */     try {
/* 134 */       JsonObject root = JsonParser.parseString(json).getAsJsonObject();
/*     */ 
/*     */       
/* 137 */       JsonArray notes = (root.has("notes") && root.get("notes").isJsonArray()) ? root.getAsJsonArray("notes") : new JsonArray();
/*     */       
/* 139 */       for (int i = 0; i < notes.size(); i++) {
/* 140 */         JsonObject note = notes.get(i).getAsJsonObject();
/* 141 */         this.entries.add(new NoteEntry(
/* 142 */               getString(note, "id"), 
/* 143 */               getString(note, "authorUuid"), 
/* 144 */               getString(note, "authorName", "Unknown Staff"), 
/* 145 */               getString(note, "timestamp"), 
/* 146 */               getString(note, "message", ""), 
/* 147 */               getBoolean(note, "canDelete"), 
/* 148 */               getBoolean(note, "overrideDelete")));
/*     */       }
/*     */     
/* 151 */     } catch (Exception e) {
/* 152 */       LOGGER.warn("Failed to parse lookup notes payload", e);
/*     */     } 
/*     */     
/* 155 */     if (this.scrollArea != null) {
/* 156 */       this.scrollArea.resetScroll();
/* 157 */       this.scrollArea.setContentHeight(6);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(class_332 ctx, int mouseX, int mouseY, float delta, class_327 textRenderer) {
/* 163 */     String currentJson = LookupClientData.getInstance().getNotesJson();
/* 164 */     if (this.lastNotesJson == null && currentJson != null) {
/* 165 */       parseData(currentJson);
/*     */     }
/*     */     
/* 168 */     rebuildLayouts(textRenderer);
/*     */     
/* 170 */     ctx.method_25294(this.x, this.y, this.x + this.width, this.y + 22, -15855080);
/* 171 */     ctx.method_51433(textRenderer, "§lStaff Notes", this.x + 6, this.y + 7, -10777105, false);
/* 172 */     String countLabel = "" + this.entries.size() + " note" + this.entries.size();
/* 173 */     ctx.method_51433(textRenderer, countLabel, this.x + this.width - textRenderer
/* 174 */         .method_1727(countLabel) - 6, this.y + 7, -9930592, false);
/* 175 */     ctx.method_25294(this.x, this.y + 22 - 1, this.x + this.width, this.y + 22, -14012352);
/*     */     
/* 177 */     renderComposer(ctx, mouseX, mouseY, textRenderer);
/*     */     
/* 179 */     this.deleteRegions.clear();
/*     */     
/* 181 */     if (this.entries.isEmpty()) {
/* 182 */       String msg = (currentJson == null) ? "Loading..." : "No staff notes yet";
/* 183 */       ctx.method_51433(textRenderer, msg, this.x + (this.width - textRenderer
/* 184 */           .method_1727(msg)) / 2, this.y + 22 + 20 + 18, -9930592, false);
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 190 */     this.scrollArea.beginRender(ctx);
/* 191 */     int rowY = this.scrollArea.getY() + 6 - this.scrollArea.getScrollOffset();
/* 192 */     int viewportBottom = this.scrollArea.getY() + this.scrollArea.getHeight();
/*     */     
/* 194 */     for (NoteLayout layout : this.layouts) {
/* 195 */       int boxHeight = layout.height();
/* 196 */       if (rowY + boxHeight >= this.scrollArea.getY() && rowY <= viewportBottom) {
/* 197 */         renderNote(ctx, textRenderer, mouseX, mouseY, rowY, layout);
/*     */       }
/* 199 */       rowY += boxHeight + 6;
/*     */     } 
/*     */     
/* 202 */     this.scrollArea.endRender(ctx);
/*     */   }
/*     */   
/*     */   private void renderComposer(class_332 ctx, int mouseX, int mouseY, class_327 textRenderer) {
/* 206 */     int rowY = this.y + 22 + 2;
/* 207 */     int postW = 44;
/* 208 */     int postX = this.x + this.width - 6 - postW;
/* 209 */     int inputX = this.x + 6;
/* 210 */     int inputW = postX - inputX - 4;
/*     */     
/* 212 */     int inputBg = this.inputFocused ? -14274480 : -14801866;
/* 213 */     int inputBorder = this.inputFocused ? -10777105 : -14012352;
/* 214 */     ctx.method_25294(inputX, rowY, inputX + inputW, rowY + 20, inputBg);
/* 215 */     ctx.method_49601(inputX, rowY, inputW, 20, inputBorder);
/*     */ 
/*     */ 
/*     */     
/* 219 */     String display = (this.inputText.isEmpty() && !this.inputFocused) ? "Write a staff note..." : truncateText(textRenderer, this.inputText + this.inputText, inputW - 8);
/* 220 */     int textColor = (this.inputText.isEmpty() && !this.inputFocused) ? LookupColors.withAlpha(-9930592, 110) : -1511169;
/* 221 */     ctx.method_51433(textRenderer, display, inputX + 4, rowY + 6, textColor, false);
/*     */     
/* 223 */     boolean canPost = !this.inputText.trim().isEmpty();
/* 224 */     boolean hovered = (mouseX >= postX && mouseX < postX + postW && mouseY >= rowY && mouseY < rowY + 20);
/* 225 */     int postBg = (hovered && canPost) ? -14142392 : -14801354;
/* 226 */     int postBorder = canPost ? (hovered ? -10777105 : -12958368) : -14012352;
/* 227 */     int postText = canPost ? -1511169 : -9930592;
/* 228 */     ctx.method_25294(postX, rowY, postX + postW, rowY + 20, postBg);
/* 229 */     ctx.method_49601(postX, rowY, postW, 20, postBorder);
/* 230 */     ctx.method_25300(textRenderer, "Post", postX + postW / 2, rowY + 6, postText);
/*     */   }
/*     */   
/*     */   private void renderNote(class_332 ctx, class_327 textRenderer, int mouseX, int mouseY, int rowY, NoteLayout layout) {
/* 234 */     NoteEntry entry = layout.entry();
/* 235 */     int cardX = this.x + 6;
/* 236 */     int cardW = this.width - 12 - 4;
/*     */ 
/*     */     
/* 239 */     boolean hovered = (mouseX >= cardX && mouseX < cardX + cardW && mouseY >= rowY && mouseY < rowY + layout.height());
/* 240 */     int bg = hovered ? -14274480 : -14801866;
/* 241 */     ctx.method_25294(cardX, rowY, cardX + cardW, rowY + layout.height(), bg);
/* 242 */     ctx.method_49601(cardX, rowY, cardW, layout.height(), -14012352);
/*     */     
/* 244 */     int contentX = cardX + 6;
/* 245 */     int contentY = rowY + 6;
/*     */     
/* 247 */     renderHead(ctx, textRenderer, entry.authorUuid(), entry.authorName(), contentX, contentY);
/*     */     
/* 249 */     int textX = contentX + 20 + 6;
/* 250 */     ctx.method_51433(textRenderer, entry.authorName(), textX, contentY, -1, true);
/* 251 */     ctx.method_51433(textRenderer, entry.timestamp(), textX, contentY + 10, -9930592, false);
/*     */     
/* 253 */     if (entry.canDelete()) {
/* 254 */       String buttonLabel = layout.buttonLabel();
/* 255 */       int buttonW = textRenderer.method_1727(buttonLabel) + 10;
/* 256 */       int buttonH = 16;
/* 257 */       int buttonX = cardX + cardW - buttonW - 6;
/* 258 */       int buttonY = rowY + 6;
/* 259 */       boolean buttonHovered = (mouseX >= buttonX && mouseX < buttonX + buttonW && mouseY >= buttonY && mouseY < buttonY + buttonH);
/*     */ 
/*     */ 
/*     */       
/* 263 */       int buttonBg = entry.overrideDelete() ? (buttonHovered ? -1030329 : LookupColors.withAlpha(-1030329, 180)) : (buttonHovered ? -14142392 : -14801354);
/* 264 */       int buttonBorder = entry.overrideDelete() ? -1030329 : -12958368;
/* 265 */       ctx.method_25294(buttonX, buttonY, buttonX + buttonW, buttonY + buttonH, buttonBg);
/* 266 */       ctx.method_49601(buttonX, buttonY, buttonW, buttonH, buttonBorder);
/* 267 */       ctx.method_25300(textRenderer, buttonLabel, buttonX + buttonW / 2, buttonY + 4, -1);
/* 268 */       this.deleteRegions.add(new DeleteRegion(buttonX, buttonY, buttonW, buttonH, entry.id()));
/*     */     } 
/*     */     
/* 271 */     int messageY = rowY + 28;
/* 272 */     for (int i = 0; i < layout.lines().size(); i++) {
/* 273 */       ctx.method_51433(textRenderer, layout.lines().get(i), textX, messageY + i * 10, -1511169, false);
/*     */     }
/*     */   }
/*     */   
/*     */   private void rebuildLayouts(class_327 textRenderer) {
/* 278 */     int wrapWidth = Math.max(120, this.width - 92);
/* 279 */     if (wrapWidth == this.lastLayoutWidth && this.layouts.size() == this.entries.size()) {
/*     */       return;
/*     */     }
/*     */     
/* 283 */     this.layouts.clear();
/* 284 */     this.lastLayoutWidth = wrapWidth;
/*     */     
/* 286 */     int totalHeight = 6;
/* 287 */     for (NoteEntry entry : this.entries) {
/* 288 */       String buttonLabel = entry.overrideDelete() ? "Override" : "Delete";
/* 289 */       int buttonW = entry.canDelete() ? (textRenderer.method_1727(buttonLabel) + 10) : 0;
/* 290 */       int textWidth = Math.max(80, this.width - 12 - 20 - 26 - buttonW - 10);
/* 291 */       List<String> wrapped = wrapText(textRenderer, entry.message(), textWidth);
/* 292 */       if (wrapped.isEmpty()) {
/* 293 */         wrapped = List.of("");
/*     */       }
/* 295 */       int height = 34 + wrapped.size() * 10 + 8;
/* 296 */       this.layouts.add(new NoteLayout(entry, wrapped, height, buttonLabel));
/* 297 */       totalHeight += height + 6;
/*     */     } 
/*     */     
/* 300 */     if (this.scrollArea != null) {
/* 301 */       this.scrollArea.setContentHeight(Math.max(totalHeight, this.scrollArea.getHeight()));
/*     */     }
/*     */   }
/*     */   
/*     */   private void renderHead(class_332 ctx, class_327 textRenderer, String uuid, String authorName, int x, int y) {
/* 306 */     class_2960 textureId = (uuid == null) ? null : this.headTextures.get(uuid);
/* 307 */     if (textureId == null && uuid != null && !this.loadingHeads.contains(uuid) && !this.failedHeads.contains(uuid)) {
/* 308 */       loadPlayerHead(uuid);
/*     */     }
/*     */     
/* 311 */     if (textureId != null) {
/* 312 */       ctx.method_25290(textureId, x, y, 0.0F, 0.0F, 20, 20, 20, 20);
/*     */       
/*     */       return;
/*     */     } 
/* 316 */     ctx.method_25294(x, y, x + 20, y + 20, -15328732);
/* 317 */     String initial = (authorName == null || authorName.isBlank()) ? "?" : authorName.substring(0, 1).toUpperCase();
/* 318 */     ctx.method_25300(textRenderer, initial, x + 10, y + 6, -1511169);
/*     */   }
/*     */   
/*     */   private void loadPlayerHead(String uuid) {
/* 322 */     this.loadingHeads.add(uuid);
/* 323 */     String url = "https://mc-heads.net/avatar/" + uuid + "/64";
/* 324 */     CompletableFuture.runAsync(() -> {
/*     */           try {
/*     */             HttpClient client = HttpClient.newHttpClient();
/*     */ 
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
/*     */             if (response.statusCode() != 200) {
/*     */               this.failedHeads.add(uuid);
/*     */ 
/*     */ 
/*     */               
/*     */               return;
/*     */             } 
/*     */ 
/*     */ 
/*     */             
/*     */             byte[] bytes = response.body();
/*     */ 
/*     */ 
/*     */             
/*     */             class_310.method_1551().execute(());
/* 354 */           } catch (Exception e) {
/*     */             this.failedHeads.add(uuid);
/*     */             this.loadingHeads.remove(uuid);
/*     */           } 
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean mouseClicked(double mouseX, double mouseY, int button) {
/* 363 */     if (button != 0) return false;
/*     */     
/* 365 */     if (this.scrollArea != null && this.scrollArea.handleMouseClicked(mouseX, mouseY, button)) {
/* 366 */       return true;
/*     */     }
/*     */     
/* 369 */     int rowY = this.y + 22 + 2;
/* 370 */     int postW = 44;
/* 371 */     int postX = this.x + this.width - 6 - postW;
/* 372 */     int inputX = this.x + 6;
/* 373 */     int inputW = postX - inputX - 4;
/*     */     
/* 375 */     if (mouseX >= inputX && mouseX < (inputX + inputW) && mouseY >= rowY && mouseY < (rowY + 20)) {
/* 376 */       this.inputFocused = true;
/* 377 */       return true;
/*     */     } 
/*     */     
/* 380 */     if (mouseX >= postX && mouseX < (postX + postW) && mouseY >= rowY && mouseY < (rowY + 20)) {
/* 381 */       submitNote();
/* 382 */       return true;
/*     */     } 
/*     */     
/* 385 */     for (DeleteRegion region : this.deleteRegions) {
/* 386 */       if (mouseX >= region.x() && mouseX < (region.x() + region.width()) && mouseY >= region
/* 387 */         .y() && mouseY < (region.y() + region.height())) {
/* 388 */         requestDelete(region.noteId());
/* 389 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/* 393 */     this.inputFocused = false;
/* 394 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
/* 399 */     return (this.scrollArea != null && this.scrollArea.handleScroll(mouseX, mouseY, verticalAmount));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
/* 404 */     return (this.scrollArea != null && this.scrollArea.handleMouseDragged(mouseX, mouseY, button));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean mouseReleased(double mouseX, double mouseY, int button) {
/* 409 */     return (this.scrollArea != null && this.scrollArea.handleMouseReleased(mouseX, mouseY, button));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
/* 414 */     if (!this.inputFocused) return false;
/*     */     
/* 416 */     if (ScreenControl.isPaste(keyCode, modifiers)) {
/* 417 */       String clipboard = (class_310.method_1551()).field_1774.method_1460();
/* 418 */       if (clipboard != null && !clipboard.isEmpty()) {
/* 419 */         appendInput(clipboard);
/*     */       }
/* 421 */       return true;
/*     */     } 
/*     */     
/* 424 */     if (keyCode == 257) {
/* 425 */       submitNote();
/* 426 */       return true;
/*     */     } 
/*     */     
/* 429 */     if (keyCode == 259 && !this.inputText.isEmpty()) {
/* 430 */       this.inputText = this.inputText.substring(0, this.inputText.length() - 1);
/* 431 */       return true;
/*     */     } 
/*     */     
/* 434 */     if (keyCode == 256) {
/* 435 */       this.inputFocused = false;
/* 436 */       return true;
/*     */     } 
/*     */     
/* 439 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean charTyped(char chr, int modifiers) {
/* 444 */     if (!this.inputFocused || chr < ' ') return false; 
/* 445 */     appendInput(String.valueOf(chr));
/* 446 */     return true;
/*     */   }
/*     */   
/*     */   private void appendInput(String incoming) {
/* 450 */     String normalized = incoming.replace('\n', ' ').replace('\r', ' ').replace('\t', ' ');
/* 451 */     String candidate = this.inputText + this.inputText;
/* 452 */     if (candidate.length() > 280) {
/* 453 */       candidate = candidate.substring(0, 280);
/*     */     }
/* 455 */     this.inputText = candidate;
/*     */   }
/*     */   
/*     */   private void submitNote() {
/* 459 */     String message = this.inputText.trim();
/* 460 */     if (message.isEmpty())
/*     */       return; 
/* 462 */     String targetUuid = LookupClientData.getInstance().getTargetUuid();
/* 463 */     if (targetUuid == null || targetUuid.isEmpty())
/*     */       return; 
/* 465 */     String encoded = Base64.getEncoder().encodeToString(message.getBytes(StandardCharsets.UTF_8));
/* 466 */     LookupNetwork.requestLookupData("add_note", 0, targetUuid + "|" + targetUuid);
/* 467 */     this.inputText = "";
/*     */   }
/*     */   
/*     */   private void requestDelete(String noteId) {
/* 471 */     if (noteId == null || noteId.isEmpty())
/* 472 */       return;  String targetUuid = LookupClientData.getInstance().getTargetUuid();
/* 473 */     if (targetUuid == null || targetUuid.isEmpty())
/* 474 */       return;  LookupNetwork.requestLookupData("delete_note", 0, targetUuid + "|" + targetUuid);
/*     */   }
/*     */   
/*     */   private static List<String> wrapText(class_327 textRenderer, String text, int maxWidth) {
/* 478 */     List<String> lines = new ArrayList<>();
/* 479 */     if (text == null || text.isEmpty()) return lines;
/*     */     
/* 481 */     String[] words = text.split(" ");
/* 482 */     StringBuilder line = new StringBuilder();
/* 483 */     for (String word : words) {
/* 484 */       String candidate = line.isEmpty() ? word : (String.valueOf(line) + " " + String.valueOf(line));
/* 485 */       if (textRenderer.method_1727(candidate) <= maxWidth) {
/* 486 */         line.setLength(0);
/* 487 */         line.append(candidate);
/*     */       }
/*     */       else {
/*     */         
/* 491 */         if (!line.isEmpty()) {
/* 492 */           lines.add(line.toString());
/* 493 */           line.setLength(0);
/*     */         } 
/*     */         
/* 496 */         if (textRenderer.method_1727(word) <= maxWidth) {
/* 497 */           line.append(word);
/*     */         }
/*     */         else {
/*     */           
/* 501 */           StringBuilder chunk = new StringBuilder();
/* 502 */           for (int i = 0; i < word.length(); i++) {
/* 503 */             String next = chunk.toString() + chunk.toString();
/* 504 */             if (textRenderer.method_1727(next) > maxWidth && !chunk.isEmpty()) {
/* 505 */               lines.add(chunk.toString());
/* 506 */               chunk.setLength(0);
/*     */             } 
/* 508 */             chunk.append(word.charAt(i));
/*     */           } 
/* 510 */           line.append(chunk);
/*     */         } 
/*     */       } 
/* 513 */     }  if (!line.isEmpty()) {
/* 514 */       lines.add(line.toString());
/*     */     }
/*     */     
/* 517 */     return lines;
/*     */   }
/*     */   
/*     */   private static String truncateText(class_327 textRenderer, String text, int maxWidth) {
/* 521 */     if (textRenderer.method_1727(text) <= maxWidth) return text; 
/* 522 */     String ellipsis = "...";
/* 523 */     int len = text.length();
/* 524 */     while (len > 0 && textRenderer.method_1727(text.substring(0, len) + text.substring(0, len)) > maxWidth) {
/* 525 */       len--;
/*     */     }
/* 527 */     return text.substring(0, Math.max(0, len)) + text.substring(0, Math.max(0, len));
/*     */   }
/*     */   
/*     */   private static String getString(JsonObject obj, String key) {
/* 531 */     return getString(obj, key, null);
/*     */   }
/*     */   
/*     */   private static String getString(JsonObject obj, String key, String fallback) {
/* 535 */     if (obj == null || !obj.has(key) || obj.get(key).isJsonNull()) return fallback; 
/*     */     try {
/* 537 */       return obj.get(key).getAsString();
/* 538 */     } catch (Exception ignored) {
/* 539 */       return fallback;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static boolean getBoolean(JsonObject obj, String key) {
/* 544 */     if (obj == null || !obj.has(key) || obj.get(key).isJsonNull()) return false; 
/*     */     try {
/* 546 */       return obj.get(key).getAsBoolean();
/* 547 */     } catch (Exception ignored) {
/* 548 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static final class ScreenControl
/*     */   {
/*     */     private static boolean isPaste(int keyCode, int modifiers) {
/* 556 */       return (keyCode == 86 && (modifiers & 0x2) != 0);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\lookup\screen\views\LookupNotesView.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */