/*     */ package com.atlas.common.fabric.lookup.screen.views;
/*     */ 
/*     */ import com.atlas.common.fabric.guide.screen.widgets.GuideScrollableArea;
/*     */ import com.atlas.common.fabric.lookup.data.LookupClientData;
/*     */ import com.atlas.common.fabric.lookup.network.LookupNetwork;
/*     */ import com.atlas.common.fabric.lookup.screen.LookupColors;
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParser;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.class_327;
/*     */ import net.minecraft.class_332;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LookupPunishmentView
/*     */   implements LookupView
/*     */ {
/*     */   private int x;
/*     */   private int y;
/*     */   private int width;
/*     */   private int height;
/*     */   private boolean dataRequested = false;
/*     */   private boolean dataParsed = false;
/*     */   private GuideScrollableArea scrollArea;
/*  28 */   private final Runnable dataListener = this::onDataUpdated;
/*     */   
/*  30 */   private final List<PunishmentEntry> entries = new ArrayList<>();
/*  31 */   private int totalCount = 0;
/*  32 */   private int activeCount = 0;
/*     */ 
/*     */ 
/*     */   
/*  36 */   private int confirmIndex = -1;
/*  37 */   private String confirmAction = null;
/*  38 */   private long confirmTime = 0L;
/*     */   
/*     */   private static final long CONFIRM_TIMEOUT_MS = 5000L;
/*     */   
/*  42 */   private int hoveredPardonIndex = -1;
/*  43 */   private int hoveredDeleteIndex = -1; private static final int ROW_HEIGHT = 20; private static final int PADDING = 6; private static final int HEADER_HEIGHT = 22; private static final int SUMMARY_HEIGHT = 18; private static final int BTN_PARDON_W = 46; private static final int BTN_DELETE_W = 44; private static final int BTN_GAP = 3;
/*     */   
/*     */   private static final class PunishmentEntry extends Record { private final String id;
/*     */     private final String type;
/*     */     private final String reason;
/*     */     private final String issuer;
/*     */     private final String duration;
/*     */     private final String expiry;
/*     */     private final boolean active;
/*     */     private final boolean permanent;
/*     */     private final String timestamp;
/*     */     private final boolean pardoned;
/*     */     
/*  56 */     private PunishmentEntry(String id, String type, String reason, String issuer, String duration, String expiry, boolean active, boolean permanent, String timestamp, boolean pardoned) { this.id = id; this.type = type; this.reason = reason; this.issuer = issuer; this.duration = duration; this.expiry = expiry; this.active = active; this.permanent = permanent; this.timestamp = timestamp; this.pardoned = pardoned; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/lookup/screen/views/LookupPunishmentView$PunishmentEntry;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #56	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*  56 */       //   0	7	0	this	Lcom/atlas/common/fabric/lookup/screen/views/LookupPunishmentView$PunishmentEntry; } public String id() { return this.id; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/lookup/screen/views/LookupPunishmentView$PunishmentEntry;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #56	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/lookup/screen/views/LookupPunishmentView$PunishmentEntry; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/lookup/screen/views/LookupPunishmentView$PunishmentEntry;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #56	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lcom/atlas/common/fabric/lookup/screen/views/LookupPunishmentView$PunishmentEntry;
/*  56 */       //   0	8	1	o	Ljava/lang/Object; } public String type() { return this.type; } public String reason() { return this.reason; } public String issuer() { return this.issuer; } public String duration() { return this.duration; } public String expiry() { return this.expiry; } public boolean active() { return this.active; } public boolean permanent() { return this.permanent; } public String timestamp() { return this.timestamp; } public boolean pardoned() { return this.pardoned; }
/*     */      }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void init(int x, int y, int width, int height) {
/*  64 */     this.x = x;
/*  65 */     this.y = y;
/*  66 */     this.width = width;
/*  67 */     this.height = height;
/*     */     
/*  69 */     int contentTop = y + 22 + 18 + 4;
/*  70 */     int contentHeight = height - 22 - 18 - 4;
/*  71 */     if (this.scrollArea == null) {
/*  72 */       this.scrollArea = new GuideScrollableArea(x, contentTop, width, contentHeight);
/*  73 */       this.scrollArea.setThumbColors(-12958368, -10777105);
/*     */     } else {
/*  75 */       this.scrollArea.updateBounds(x, contentTop, width, contentHeight);
/*     */     } 
/*     */     
/*  78 */     LookupClientData data = LookupClientData.getInstance();
/*  79 */     data.removeListener(this.dataListener);
/*  80 */     data.addListener(this.dataListener);
/*     */     
/*  82 */     if (!this.dataRequested) {
/*  83 */       this.dataRequested = true;
/*  84 */       String uuid = data.getTargetUuid();
/*  85 */       LookupNetwork.requestLookupData("punishments", 0, (uuid != null) ? uuid : "");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void onDataUpdated() {
/*  92 */     this.dataParsed = false;
/*     */   }
/*     */   
/*     */   private void parseData() {
/*  96 */     this.entries.clear();
/*  97 */     this.activeCount = 0;
/*  98 */     String json = LookupClientData.getInstance().getPunishmentJson();
/*     */     
/* 100 */     if (json == null || json.isEmpty()) {
/* 101 */       this.dataParsed = true;
/*     */       
/*     */       return;
/*     */     } 
/*     */     try {
/* 106 */       JsonArray arr = JsonParser.parseString(json).getAsJsonArray();
/* 107 */       for (int i = 0; i < arr.size(); i++) {
/* 108 */         JsonObject obj = arr.get(i).getAsJsonObject();
/* 109 */         String id = obj.has("id") ? obj.get("id").getAsString() : String.valueOf(i);
/* 110 */         String type = obj.has("type") ? obj.get("type").getAsString() : "unknown";
/* 111 */         String reason = obj.has("reason") ? obj.get("reason").getAsString() : "No reason";
/* 112 */         String issuer = obj.has("issuer") ? obj.get("issuer").getAsString() : "Unknown";
/* 113 */         String duration = obj.has("duration") ? obj.get("duration").getAsString() : "";
/* 114 */         String expiry = obj.has("expiry") ? obj.get("expiry").getAsString() : "";
/* 115 */         boolean active = (obj.has("active") && obj.get("active").getAsBoolean());
/* 116 */         boolean permanent = (obj.has("permanent") && obj.get("permanent").getAsBoolean());
/* 117 */         String timestamp = obj.has("timestamp") ? obj.get("timestamp").getAsString() : "";
/* 118 */         boolean pardoned = (obj.has("pardoned") && obj.get("pardoned").getAsBoolean());
/*     */         
/* 120 */         if (active) this.activeCount++; 
/* 121 */         this.entries.add(new PunishmentEntry(id, type, reason, issuer, duration, expiry, active, permanent, timestamp, pardoned));
/*     */       } 
/* 123 */     } catch (Exception exception) {}
/*     */     
/* 125 */     this.totalCount = this.entries.size();
/* 126 */     this.dataParsed = true;
/* 127 */     this.scrollArea.setContentHeight(this.entries.size() * 20 + 6);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(class_332 ctx, int mouseX, int mouseY, float delta, class_327 textRenderer) {
/* 134 */     if (!this.dataParsed) {
/* 135 */       parseData();
/*     */     }
/*     */ 
/*     */     
/* 139 */     if (this.confirmIndex >= 0 && System.currentTimeMillis() - this.confirmTime > 5000L) {
/* 140 */       this.confirmIndex = -1;
/* 141 */       this.confirmAction = null;
/*     */     } 
/*     */ 
/*     */     
/* 145 */     ctx.method_51433(textRenderer, "§lPunishments", this.x + 6, this.y + 6, -10777105, false);
/*     */ 
/*     */     
/* 148 */     int summaryY = this.y + 22;
/* 149 */     String summary = "" + this.totalCount + " total";
/* 150 */     ctx.method_51433(textRenderer, summary, this.x + 6, summaryY + 4, -9930592, false);
/*     */ 
/*     */     
/* 153 */     if (this.activeCount > 0) {
/* 154 */       String activeLabel = "" + this.activeCount + " active";
/* 155 */       int alw = textRenderer.method_1727(activeLabel);
/* 156 */       ctx.method_51433(textRenderer, activeLabel, this.x + this.width - alw - 6, summaryY + 4, -1030329, false);
/*     */     }
/* 158 */     else if (this.totalCount > 0) {
/* 159 */       String cleanLabel = "Clean";
/* 160 */       int clw = textRenderer.method_1727(cleanLabel);
/* 161 */       ctx.method_51433(textRenderer, cleanLabel, this.x + this.width - clw - 6, summaryY + 4, -12339839, false);
/*     */     } 
/*     */ 
/*     */     
/* 165 */     if (this.entries.isEmpty()) {
/* 166 */       String msg = (LookupClientData.getInstance().getPunishmentJson() == null) ? "Loading..." : "No punishments found";
/* 167 */       int tw = textRenderer.method_1727(msg);
/* 168 */       ctx.method_51433(textRenderer, msg, this.x + (this.width - tw) / 2, this.y + this.height / 2, -9930592, false);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 173 */     this.hoveredPardonIndex = -1;
/* 174 */     this.hoveredDeleteIndex = -1;
/*     */ 
/*     */     
/* 177 */     this.scrollArea.beginRender(ctx);
/*     */     
/* 179 */     int contentTop = this.scrollArea.getY();
/* 180 */     int scrollOff = this.scrollArea.getScrollOffset();
/*     */     
/* 182 */     for (int i = 0; i < this.entries.size(); i++) {
/* 183 */       PunishmentEntry entry = this.entries.get(i);
/* 184 */       int rowY = contentTop + i * 20 - scrollOff;
/*     */       
/* 186 */       if (rowY + 20 >= this.scrollArea.getY() && rowY <= this.scrollArea.getY() + this.scrollArea.getHeight()) {
/*     */ 
/*     */         
/* 189 */         if (i % 2 == 0) {
/* 190 */           ctx.method_25294(this.x, rowY, this.x + this.width, rowY + 20, -14801866);
/*     */         }
/*     */ 
/*     */         
/* 194 */         if (entry.active) {
/* 195 */           ctx.method_25294(this.x, rowY, this.x + 3, rowY + 20, -1030329);
/*     */         }
/*     */ 
/*     */         
/* 199 */         if (mouseX >= this.x && mouseX < this.x + this.width && mouseY >= rowY && mouseY < rowY + 20) {
/* 200 */           ctx.method_25294(this.x, rowY, this.x + this.width, rowY + 20, -14274480);
/*     */         }
/*     */         
/* 203 */         int textY = rowY + 5;
/* 204 */         int cx = this.x + 6 + 6;
/*     */ 
/*     */         
/* 207 */         int btnRightEdge = this.x + this.width - 6;
/* 208 */         int deleteX = btnRightEdge - 44;
/* 209 */         int pardonX = deleteX - 3 - 46;
/*     */ 
/*     */         
/* 212 */         int typeColor = typeBadgeColor(entry.type);
/* 213 */         String typeLabel = entry.type.toUpperCase();
/* 214 */         int badgeW = textRenderer.method_1727(typeLabel) + 6;
/* 215 */         ctx.method_25294(cx, rowY + 3, cx + badgeW, rowY + 20 - 3, LookupColors.withAlpha(typeColor, 60));
/* 216 */         ctx.method_51433(textRenderer, typeLabel, cx + 3, textY, typeColor, false);
/* 217 */         cx += badgeW + 5;
/*     */ 
/*     */         
/* 220 */         String prettyReason = prettifyReason(entry.reason);
/* 221 */         int reasonColor = entry.active ? -1 : -1511169;
/*     */         
/* 223 */         int issuerW = textRenderer.method_1727(" • " + entry.issuer);
/* 224 */         String durLabel = buildDurationLabel(entry);
/* 225 */         int durW = durLabel.isEmpty() ? 0 : textRenderer.method_1727(" • " + durLabel);
/* 226 */         int btnsTotalW = 99;
/* 227 */         int maxReasonW = pardonX - 6 - cx - issuerW - durW - 4;
/* 228 */         String reason = truncateText(textRenderer, prettyReason, Math.max(20, maxReasonW));
/* 229 */         ctx.method_51433(textRenderer, reason, cx, textY, reasonColor, false);
/* 230 */         cx += textRenderer.method_1727(reason);
/*     */ 
/*     */         
/* 233 */         ctx.method_51433(textRenderer, " • ", cx, textY, -9930592, false);
/* 234 */         cx += textRenderer.method_1727(" • ");
/* 235 */         ctx.method_51433(textRenderer, entry.issuer, cx, textY, -9930592, false);
/* 236 */         cx += textRenderer.method_1727(entry.issuer);
/*     */ 
/*     */         
/* 239 */         if (!durLabel.isEmpty()) {
/* 240 */           ctx.method_51433(textRenderer, " • ", cx, textY, -9930592, false);
/* 241 */           cx += textRenderer.method_1727(" • ");
/* 242 */           int durColor = entry.permanent ? -1030329 : -1662404;
/* 243 */           ctx.method_51433(textRenderer, durLabel, cx, textY, durColor, false);
/*     */         } 
/*     */ 
/*     */         
/* 247 */         boolean isConfirmingPardon = (this.confirmIndex == i && "pardon".equals(this.confirmAction));
/* 248 */         boolean isConfirmingDelete = (this.confirmIndex == i && "delete".equals(this.confirmAction));
/*     */ 
/*     */         
/* 251 */         int btnTop = rowY + 3;
/* 252 */         int btnBottom = rowY + 20 - 3;
/* 253 */         int btnH = btnBottom - btnTop;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 259 */         boolean pardonHovered = (!entry.pardoned() && mouseX >= pardonX && mouseX < pardonX + 46 && mouseY >= btnTop && mouseY < btnBottom && this.scrollArea.isInBounds(mouseX, mouseY));
/* 260 */         if (pardonHovered) this.hoveredPardonIndex = i;
/*     */         
/* 262 */         int pardonBtnTextY = btnTop + (btnH - 9) / 2 + 1;
/* 263 */         String pardonLabel = entry.pardoned() ? "Pardoned" : (isConfirmingPardon ? "Confirm" : "Pardon");
/*     */ 
/*     */         
/* 266 */         int pardonBg = entry.pardoned() ? LookupColors.withAlpha(-14801866, 100) : (isConfirmingPardon ? LookupColors.withAlpha(-12339839, 100) : (pardonHovered ? LookupColors.withAlpha(-12339839, 70) : LookupColors.withAlpha(-14801866, 180)));
/*     */         
/* 268 */         int pardonBorder = entry.pardoned() ? LookupColors.withAlpha(-14012352, 80) : (isConfirmingPardon ? -12339839 : (pardonHovered ? -12339839 : -14012352));
/*     */         
/* 270 */         int pardonTextColor = entry.pardoned() ? LookupColors.withAlpha(-12339839, 180) : (isConfirmingPardon ? -1 : (pardonHovered ? -12339839 : -9930592));
/* 271 */         ctx.method_25294(pardonX, btnTop, pardonX + 46, btnBottom, pardonBg);
/* 272 */         ctx.method_49601(pardonX, btnTop, 46, btnH, pardonBorder);
/* 273 */         int plw = textRenderer.method_1727(pardonLabel);
/* 274 */         ctx.method_51433(textRenderer, pardonLabel, pardonX + (46 - plw) / 2, pardonBtnTextY, pardonTextColor, false);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 279 */         boolean deleteHovered = (mouseX >= deleteX && mouseX < deleteX + 44 && mouseY >= btnTop && mouseY < btnBottom && this.scrollArea.isInBounds(mouseX, mouseY));
/* 280 */         if (deleteHovered) this.hoveredDeleteIndex = i;
/*     */         
/* 282 */         String deleteLabel = isConfirmingDelete ? "Confirm" : "Delete";
/*     */         
/* 284 */         int deleteBg = isConfirmingDelete ? LookupColors.withAlpha(-1030329, 100) : (deleteHovered ? LookupColors.withAlpha(-1030329, 70) : LookupColors.withAlpha(-14801866, 180));
/* 285 */         int deleteBorder = isConfirmingDelete ? -1030329 : (deleteHovered ? -1030329 : -14012352);
/* 286 */         int deleteTextColor = isConfirmingDelete ? -1 : (deleteHovered ? -1030329 : -9930592);
/* 287 */         ctx.method_25294(deleteX, btnTop, deleteX + 44, btnBottom, deleteBg);
/* 288 */         ctx.method_49601(deleteX, btnTop, 44, btnH, deleteBorder);
/* 289 */         int dlw = textRenderer.method_1727(deleteLabel);
/* 290 */         ctx.method_51433(textRenderer, deleteLabel, deleteX + (44 - dlw) / 2, btnTop + (btnH - 9) / 2 + 1, deleteTextColor, false);
/*     */       } 
/*     */     } 
/* 293 */     this.scrollArea.endRender(ctx);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
/* 300 */     if (this.scrollArea != null) {
/* 301 */       return this.scrollArea.handleScroll(mouseX, mouseY, verticalAmount);
/*     */     }
/* 303 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
/* 308 */     if (this.scrollArea != null) return this.scrollArea.handleMouseDragged(mouseX, mouseY, button); 
/* 309 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean mouseReleased(double mouseX, double mouseY, int button) {
/* 314 */     if (this.scrollArea != null) return this.scrollArea.handleMouseReleased(mouseX, mouseY, button); 
/* 315 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean mouseClicked(double mouseX, double mouseY, int button) {
/* 320 */     if (button != 0) return false;
/*     */ 
/*     */     
/* 323 */     if (this.hoveredPardonIndex >= 0 && this.hoveredPardonIndex < this.entries.size() && 
/* 324 */       !((PunishmentEntry)this.entries.get(this.hoveredPardonIndex)).pardoned()) {
/* 325 */       int i = this.hoveredPardonIndex;
/* 326 */       if (this.confirmIndex == i && "pardon".equals(this.confirmAction)) {
/*     */         
/* 328 */         sendAction("pardon_punishment", ((PunishmentEntry)this.entries.get(i)).id());
/* 329 */         this.confirmIndex = -1;
/* 330 */         this.confirmAction = null;
/*     */       } else {
/*     */         
/* 333 */         this.confirmIndex = i;
/* 334 */         this.confirmAction = "pardon";
/* 335 */         this.confirmTime = System.currentTimeMillis();
/*     */       } 
/* 337 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 341 */     if (this.hoveredDeleteIndex >= 0 && this.hoveredDeleteIndex < this.entries.size()) {
/* 342 */       int i = this.hoveredDeleteIndex;
/* 343 */       if (this.confirmIndex == i && "delete".equals(this.confirmAction)) {
/*     */         
/* 345 */         sendAction("delete_punishment", ((PunishmentEntry)this.entries.get(i)).id());
/* 346 */         this.confirmIndex = -1;
/* 347 */         this.confirmAction = null;
/*     */       } else {
/*     */         
/* 350 */         this.confirmIndex = i;
/* 351 */         this.confirmAction = "delete";
/* 352 */         this.confirmTime = System.currentTimeMillis();
/*     */       } 
/* 354 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 358 */     if (this.confirmIndex >= 0) {
/* 359 */       this.confirmIndex = -1;
/* 360 */       this.confirmAction = null;
/*     */     } 
/*     */     
/* 363 */     if (this.scrollArea != null) {
/* 364 */       return this.scrollArea.handleMouseClicked(mouseX, mouseY, button);
/*     */     }
/* 366 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void sendAction(String actionType, String punishmentId) {
/* 372 */     String targetUuid = LookupClientData.getInstance().getTargetUuid();
/* 373 */     String filter = punishmentId + ":" + punishmentId;
/* 374 */     LookupNetwork.requestLookupData(actionType, 0, filter);
/*     */   }
/*     */ 
/*     */   
/*     */   private static String prettifyReason(String reason) {
/* 379 */     if (reason == null || reason.isEmpty()) return "No reason";
/*     */     
/* 381 */     String trimmed = reason.trim();
/* 382 */     if (trimmed.equals(trimmed.toUpperCase()) && trimmed.contains("_")) {
/* 383 */       StringBuilder sb = new StringBuilder();
/* 384 */       for (String word : trimmed.split("_")) {
/* 385 */         if (!word.isEmpty()) {
/* 386 */           if (sb.length() > 0) sb.append(' '); 
/* 387 */           sb.append(Character.toUpperCase(word.charAt(0)));
/* 388 */           if (word.length() > 1) sb.append(word.substring(1).toLowerCase()); 
/*     */         } 
/* 390 */       }  return sb.toString();
/*     */     } 
/* 392 */     return trimmed;
/*     */   }
/*     */   
/*     */   private static String buildDurationLabel(PunishmentEntry entry) {
/* 396 */     if (entry.permanent()) return "Permanent"; 
/* 397 */     if (!entry.duration().isEmpty()) return entry.duration(); 
/* 398 */     if (!entry.expiry().isEmpty()) return "until " + formatTimestamp(entry.expiry()); 
/* 399 */     return "";
/*     */   }
/*     */   
/*     */   private static int typeBadgeColor(String type) {
/* 403 */     if (type == null) return -9930592; 
/* 404 */     switch (type.toLowerCase()) { case "ban": case "mute": case "warn": case "kick":  }  return 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 409 */       -9930592;
/*     */   }
/*     */ 
/*     */   
/*     */   private static String formatTimestamp(String timestamp) {
/* 414 */     if (timestamp == null || timestamp.isEmpty()) return ""; 
/* 415 */     if (timestamp.length() > 16) return timestamp.substring(0, 16).replace('T', ' '); 
/* 416 */     return timestamp;
/*     */   }
/*     */   
/*     */   private static String truncateText(class_327 textRenderer, String text, int maxWidth) {
/* 420 */     if (maxWidth <= 0) return ""; 
/* 421 */     if (textRenderer.method_1727(text) <= maxWidth) return text; 
/* 422 */     String ellipsis = "...";
/* 423 */     int ellipsisW = textRenderer.method_1727(ellipsis);
/* 424 */     if (maxWidth <= ellipsisW) return ellipsis; 
/* 425 */     int target = maxWidth - ellipsisW;
/* 426 */     int len = text.length();
/* 427 */     while (len > 0 && textRenderer.method_1727(text.substring(0, len)) > target) {
/* 428 */       len--;
/*     */     }
/* 430 */     return text.substring(0, len) + text.substring(0, len);
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\lookup\screen\views\LookupPunishmentView.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */