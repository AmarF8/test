/*     */ package com.atlas.common.fabric.patchnotes.screen;
/*     */ 
/*     */ import com.atlas.common.fabric.patchnotes.network.PatchNotesNetwork;
/*     */ import java.net.URI;
/*     */ import java.time.Instant;
/*     */ import java.time.ZoneId;
/*     */ import java.time.format.DateTimeFormatter;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import net.fabricmc.api.EnvType;
/*     */ import net.fabricmc.api.Environment;
/*     */ import net.minecraft.class_156;
/*     */ import net.minecraft.class_2561;
/*     */ import net.minecraft.class_2583;
/*     */ import net.minecraft.class_332;
/*     */ import net.minecraft.class_3532;
/*     */ import net.minecraft.class_437;
/*     */ import net.minecraft.class_5348;
/*     */ import net.minecraft.class_5481;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Environment(EnvType.CLIENT)
/*     */ public final class PatchNotesScreen
/*     */   extends class_437
/*     */ {
/*     */   private static final int BACKDROP_TOP = -451668196;
/*     */   private static final int BACKDROP_BOTTOM = -268040439;
/*     */   private static final int PANEL_BG = -266724061;
/*     */   private static final int PANEL_BORDER = -12235687;
/*     */   private static final int HEADER_BG = -14670290;
/*     */   private static final int SIDEBAR_BG = -15459808;
/*     */   private static final int CONTENT_BG = -15722981;
/*     */   private static final int ENTRY_BG = -14867411;
/*     */   private static final int ENTRY_HOVER = -14077376;
/*     */   private static final int ENTRY_SELECTED = -13483432;
/*     */   private static final int BUTTON_BG = -14472138;
/*     */   private static final int BUTTON_HOVER = -13352625;
/*     */   private static final int ACCENT_GOLD = -735894;
/*     */   private static final int ACCENT_GREEN = -8460409;
/*     */   private static final int ACCENT_YELLOW = -865184;
/*     */   private static final int ACCENT_BLUE = -8734977;
/*     */   private static final int TEXT_PRIMARY = -659226;
/*     */   private static final int TEXT_SECONDARY = -3686998;
/*     */   private static final int TEXT_DIM = -6971218;
/*     */   private static final int TEXT_MUTED = -8747629;
/*     */   private static final int TEXT_INFO = -1643017;
/*     */   private static final int SCROLLBAR_TRACK = -15986668;
/*     */   private static final int SCROLLBAR_THUMB = -11379605;
/*     */   private static final int PANEL_WIDTH = 760;
/*     */   private static final int PANEL_HEIGHT = 430;
/*     */   private static final int HEADER_HEIGHT = 48;
/*     */   private static final int FOOTER_HEIGHT = 38;
/*     */   private static final int SIDEBAR_WIDTH = 228;
/*     */   private static final int NOTE_ENTRY_HEIGHT = 34;
/*     */   private static final int CONTENT_SCROLL_STEP = 22;
/*     */   private static final int CONTENT_LINE_HEIGHT = 11;
/*  62 */   private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.US);
/*     */   
/*     */   private final List<PatchNotesNetwork.PatchNoteEntry> patchNotes;
/*  65 */   private final List<RenderedLine> renderedLines = new ArrayList<>();
/*     */   
/*     */   private int panelX;
/*     */   
/*     */   private int panelY;
/*     */   private int panelWidth;
/*     */   private int panelHeight;
/*     */   private int selectedPatchIndex;
/*     */   private int noteListOffset;
/*     */   private int contentScroll;
/*     */   private int contentHeight;
/*     */   private boolean closeHovered;
/*     */   private boolean doneHovered;
/*     */   private boolean openDiscordHovered;
/*     */   
/*     */   public PatchNotesScreen(@NotNull List<PatchNotesNetwork.PatchNoteEntry> patchNotes) {
/*  81 */     super((class_2561)class_2561.method_43470("Patch Notes"));
/*  82 */     this.patchNotes = List.copyOf(patchNotes);
/*  83 */     this.selectedPatchIndex = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void method_25426() {
/*  88 */     super.method_25426();
/*  89 */     this.panelWidth = Math.min(760, this.field_22789 - 32);
/*  90 */     this.panelHeight = Math.min(430, this.field_22790 - 32);
/*  91 */     this.panelX = (this.field_22789 - this.panelWidth) / 2;
/*  92 */     this.panelY = (this.field_22790 - this.panelHeight) / 2;
/*  93 */     ensureSelectionVisible();
/*  94 */     rebuildRenderedLines();
/*     */   }
/*     */ 
/*     */   
/*     */   public void method_25394(@NotNull class_332 context, int mouseX, int mouseY, float delta) {
/*  99 */     this.closeHovered = false;
/* 100 */     this.doneHovered = false;
/* 101 */     this.openDiscordHovered = false;
/*     */     
/* 103 */     context.method_25296(0, 0, this.field_22789, this.field_22790, -451668196, -268040439);
/*     */     
/* 105 */     int panelRight = this.panelX + this.panelWidth;
/* 106 */     int panelBottom = this.panelY + this.panelHeight;
/* 107 */     context.method_25294(this.panelX, this.panelY, panelRight, panelBottom, -266724061);
/* 108 */     context.method_49601(this.panelX, this.panelY, this.panelWidth, this.panelHeight, -12235687);
/*     */     
/* 110 */     renderHeader(context, mouseX, mouseY);
/* 111 */     renderSidebar(context, mouseX, mouseY);
/* 112 */     renderContent(context, mouseX, mouseY);
/* 113 */     renderFooter(context, mouseX, mouseY);
/*     */   }
/*     */ 
/*     */   
/*     */   public void method_25420(@NotNull class_332 context, int mouseX, int mouseY, float delta) {}
/*     */ 
/*     */   
/*     */   private void renderHeader(class_332 context, int mouseX, int mouseY) {
/* 121 */     int headerX2 = this.panelX + this.panelWidth;
/* 122 */     context.method_25294(this.panelX, this.panelY, headerX2, this.panelY + 48, -14670290);
/* 123 */     context.method_51439(this.field_22793, (class_2561)class_2561.method_43470("PATCH NOTES").method_10862(class_2583.field_24360.method_10982(Boolean.valueOf(true))), this.panelX + 16, this.panelY + 12, -735894, false);
/* 124 */     context.method_51439(this.field_22793, (class_2561)class_2561.method_43470("Latest updates first. Click a release on the left to browse details."), this.panelX + 16, this.panelY + 27, -6971218, false);
/*     */     
/* 126 */     int closeX = this.panelX + this.panelWidth - 26;
/* 127 */     int closeY = this.panelY + 10;
/* 128 */     this.closeHovered = isInside(mouseX, mouseY, closeX, closeY, 16, 16);
/* 129 */     context.method_25294(closeX, closeY, closeX + 16, closeY + 16, this.closeHovered ? -13352625 : -14472138);
/* 130 */     context.method_49601(closeX, closeY, 16, 16, this.closeHovered ? -735894 : -12235687);
/* 131 */     context.method_27534(this.field_22793, (class_2561)class_2561.method_43470("x"), closeX + 8, closeY + 4, -659226);
/*     */   }
/*     */   
/*     */   private void renderSidebar(class_332 context, int mouseX, int mouseY) {
/* 135 */     int sidebarX = this.panelX + 12;
/* 136 */     int sidebarY = this.panelY + 48 + 10;
/* 137 */     int sidebarW = 228;
/* 138 */     int sidebarH = this.panelHeight - 48 - 38 - 20;
/* 139 */     context.method_25294(sidebarX, sidebarY, sidebarX + 228, sidebarY + sidebarH, -15459808);
/* 140 */     context.method_49601(sidebarX, sidebarY, 228, sidebarH, -12235687);
/* 141 */     context.method_51439(this.field_22793, (class_2561)class_2561.method_43470("Releases").method_10862(class_2583.field_24360.method_10982(Boolean.valueOf(true))), sidebarX + 10, sidebarY + 8, -659226, false);
/*     */     
/* 143 */     int bodyY = sidebarY + 24;
/* 144 */     int bodyH = sidebarH - 30;
/* 145 */     int visibleCount = Math.max(1, bodyH / 34);
/* 146 */     int endIndex = Math.min(this.patchNotes.size(), this.noteListOffset + visibleCount);
/*     */     
/* 148 */     for (int index = this.noteListOffset; index < endIndex; index++) {
/* 149 */       PatchNotesNetwork.PatchNoteEntry patchNote = this.patchNotes.get(index);
/* 150 */       int entryY = bodyY + (index - this.noteListOffset) * 34;
/* 151 */       boolean selected = (index == this.selectedPatchIndex);
/* 152 */       boolean hovered = isInside(mouseX, mouseY, sidebarX + 6, entryY + 2, 212, 30);
/* 153 */       int background = selected ? -13483432 : (hovered ? -14077376 : -14867411);
/*     */       
/* 155 */       context.method_25294(sidebarX + 6, entryY + 2, sidebarX + 228 - 10, entryY + 34 - 2, background);
/* 156 */       context.method_49601(sidebarX + 6, entryY + 2, 212, 30, selected ? -735894 : -12235687);
/*     */       
/* 158 */       String title = this.field_22793.method_27523(patchNote.title(), 194);
/* 159 */       context.method_51439(this.field_22793, (class_2561)class_2561.method_43470(title).method_10862(class_2583.field_24360.method_10982(Boolean.valueOf(selected))), sidebarX + 14, entryY + 8, -659226, false);
/* 160 */       context.method_51439(this.field_22793, (class_2561)class_2561.method_43470(formatDate(patchNote.publishedAt())), sidebarX + 14, entryY + 20, selected ? -3686998 : -8747629, false);
/*     */     } 
/*     */     
/* 163 */     if (this.patchNotes.size() > visibleCount) {
/* 164 */       int scrollbarX = sidebarX + 228 - 6;
/* 165 */       int scrollbarY = bodyY;
/* 166 */       int scrollbarH = bodyH;
/* 167 */       int maxOffset = Math.max(1, this.patchNotes.size() - visibleCount);
/* 168 */       int thumbH = Math.max(24, scrollbarH * visibleCount / this.patchNotes.size());
/* 169 */       int thumbY = scrollbarY + (scrollbarH - thumbH) * this.noteListOffset / maxOffset;
/* 170 */       context.method_25294(scrollbarX, scrollbarY, scrollbarX + 3, scrollbarY + scrollbarH, -15986668);
/* 171 */       context.method_25294(scrollbarX, thumbY, scrollbarX + 3, thumbY + thumbH, -11379605);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void renderContent(class_332 context, int mouseX, int mouseY) {
/* 176 */     int contentX = this.panelX + 228 + 28;
/* 177 */     int contentY = this.panelY + 48 + 10;
/* 178 */     int contentW = this.panelWidth - 228 - 40;
/* 179 */     int contentH = this.panelHeight - 48 - 38 - 20;
/* 180 */     context.method_25294(contentX, contentY, contentX + contentW, contentY + contentH, -15722981);
/* 181 */     context.method_49601(contentX, contentY, contentW, contentH, -12235687);
/*     */     
/* 183 */     if (this.patchNotes.isEmpty()) {
/* 184 */       context.method_27534(this.field_22793, (class_2561)class_2561.method_43470("No patch notes were available."), contentX + contentW / 2, contentY + contentH / 2 - 4, -659226);
/*     */       
/*     */       return;
/*     */     } 
/* 188 */     context.method_44379(contentX + 8, contentY + 8, contentX + contentW - 12, contentY + contentH - 8);
/* 189 */     int lineY = contentY + 12 - this.contentScroll;
/* 190 */     for (RenderedLine line : this.renderedLines) {
/* 191 */       if (line.text() != null && lineY + line.height() >= contentY + 8 && lineY <= contentY + contentH - 8) {
/* 192 */         context.method_51430(this.field_22793, line.text(), contentX + 12, lineY, line.color(), line.shadow());
/*     */       }
/* 194 */       lineY += line.height();
/*     */     } 
/* 196 */     context.method_44380();
/*     */     
/* 198 */     if (this.contentHeight > contentH - 16) {
/* 199 */       int trackX = contentX + contentW - 8;
/* 200 */       int trackY = contentY + 6;
/* 201 */       int trackH = contentH - 12;
/* 202 */       int thumbH = Math.max(24, trackH * (contentH - 16) / this.contentHeight);
/* 203 */       int maxScroll = Math.max(1, this.contentHeight - contentH - 16);
/* 204 */       int thumbY = trackY + (trackH - thumbH) * this.contentScroll / maxScroll;
/* 205 */       context.method_25294(trackX, trackY, trackX + 4, trackY + trackH, -15986668);
/* 206 */       context.method_25294(trackX, thumbY, trackX + 4, thumbY + thumbH, -11379605);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void renderFooter(class_332 context, int mouseX, int mouseY) {
/* 211 */     int footerY = this.panelY + this.panelHeight - 38;
/* 212 */     int footerX2 = this.panelX + this.panelWidth;
/* 213 */     context.method_25294(this.panelX, footerY, footerX2, footerY + 38, -14670290);
/*     */     
/* 215 */     PatchNotesNetwork.PatchNoteEntry selectedPatchNote = getSelectedPatchNote();
/* 216 */     if (selectedPatchNote != null) {
/* 217 */       context.method_51439(this.field_22793, 
/*     */           
/* 219 */           (class_2561)class_2561.method_43470("Added " + countLines(selectedPatchNote, 0) + "  Fixed " + countLines(selectedPatchNote, 1)), this.panelX + 16, footerY + 14, -3686998, false);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 227 */     int doneW = 72;
/* 228 */     int doneH = 20;
/* 229 */     int doneX = footerX2 - 72 - 16;
/* 230 */     int doneY = footerY + 9;
/* 231 */     this.doneHovered = isInside(mouseX, mouseY, doneX, doneY, 72, 20);
/* 232 */     context.method_25294(doneX, doneY, doneX + 72, doneY + 20, this.doneHovered ? -13352625 : -14472138);
/* 233 */     context.method_49601(doneX, doneY, 72, 20, this.doneHovered ? -735894 : -12235687);
/* 234 */     context.method_27534(this.field_22793, (class_2561)class_2561.method_43470("Done"), doneX + 36, doneY + 6, -659226);
/*     */     
/* 236 */     boolean hasLink = (selectedPatchNote != null && !selectedPatchNote.jumpUrl().isBlank());
/* 237 */     int linkW = 128;
/* 238 */     int linkH = 20;
/* 239 */     int linkX = doneX - 128 - 10;
/* 240 */     int linkY = doneY;
/* 241 */     this.openDiscordHovered = (hasLink && isInside(mouseX, mouseY, linkX, linkY, 128, 20));
/* 242 */     context.method_25294(linkX, linkY, linkX + 128, linkY + 20, hasLink ? (this.openDiscordHovered ? -13352625 : -14472138) : -15459808);
/* 243 */     context.method_49601(linkX, linkY, 128, 20, hasLink ? (this.openDiscordHovered ? -8734977 : -12235687) : -12235687);
/* 244 */     context.method_27534(this.field_22793, (class_2561)class_2561.method_43470("Open Discord Post"), linkX + 64, linkY + 6, hasLink ? -8734977 : -8747629);
/*     */   }
/*     */   
/*     */   private void rebuildRenderedLines() {
/* 248 */     this.renderedLines.clear();
/* 249 */     this.contentHeight = 0;
/*     */     
/* 251 */     PatchNotesNetwork.PatchNoteEntry patchNote = getSelectedPatchNote();
/* 252 */     if (patchNote == null)
/*     */       return; 
/* 254 */     addWrappedLine((class_2561)class_2561.method_43470(patchNote.title()).method_10862(class_2583.field_24360.method_10982(Boolean.valueOf(true))), getContentTextWidth(), -659226, true);
/* 255 */     addSpacer(6);
/* 256 */     addWrappedLine((class_2561)class_2561.method_43470(formatDate(patchNote.publishedAt())), getContentTextWidth(), -3686998, false);
/* 257 */     addSpacer(10);
/*     */     
/* 259 */     for (PatchNotesNetwork.PatchNoteLineEntry line : patchNote.lines()) {
/* 260 */       switch (line.kind()) { case 0: 
/*     */         case 1: 
/*     */         default:
/* 263 */           break; }  int color = -1643017;
/*     */       
/* 265 */       switch (line.kind()) { case 0: 
/*     */         case 1: 
/*     */         default:
/* 268 */           break; }  String prefix = "- ";
/*     */       
/* 270 */       addWrappedLine((class_2561)class_2561.method_43470(prefix + prefix), getContentTextWidth(), color, false);
/* 271 */       addSpacer(4);
/*     */     } 
/*     */     
/* 274 */     this.contentScroll = class_3532.method_15340(this.contentScroll, 0, getMaxContentScroll());
/*     */   }
/*     */   
/*     */   private void addWrappedLine(class_2561 text, int maxWidth, int color, boolean shadow) {
/* 278 */     for (class_5481 wrappedLine : this.field_22793.method_1728((class_5348)text, maxWidth)) {
/* 279 */       this.renderedLines.add(new RenderedLine(wrappedLine, color, 11, shadow));
/* 280 */       this.contentHeight += 11;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void addSpacer(int height) {
/* 285 */     this.renderedLines.add(new RenderedLine(null, 0, height, false));
/* 286 */     this.contentHeight += height;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean method_25402(double mouseX, double mouseY, int button) {
/* 291 */     if (button != 0) return super.method_25402(mouseX, mouseY, button);
/*     */     
/* 293 */     if (this.closeHovered || this.doneHovered) {
/* 294 */       method_25419();
/* 295 */       return true;
/*     */     } 
/*     */     
/* 298 */     if (this.openDiscordHovered) {
/* 299 */       PatchNotesNetwork.PatchNoteEntry patchNote = getSelectedPatchNote();
/* 300 */       if (patchNote != null && !patchNote.jumpUrl().isBlank()) {
/*     */         try {
/* 302 */           class_156.method_668().method_673(URI.create(patchNote.jumpUrl()));
/* 303 */         } catch (Exception exception) {}
/*     */         
/* 305 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/* 309 */     int sidebarX = this.panelX + 12;
/* 310 */     int sidebarY = this.panelY + 48 + 34;
/* 311 */     int sidebarW = 228;
/* 312 */     int sidebarH = this.panelHeight - 48 - 38 - 50;
/* 313 */     int visibleCount = Math.max(1, sidebarH / 34);
/* 314 */     int endIndex = Math.min(this.patchNotes.size(), this.noteListOffset + visibleCount);
/* 315 */     for (int index = this.noteListOffset; index < endIndex; index++) {
/* 316 */       int entryY = sidebarY + (index - this.noteListOffset) * 34;
/* 317 */       if (isInside(mouseX, mouseY, sidebarX + 6, entryY + 2, 212, 30)) {
/* 318 */         this.selectedPatchIndex = index;
/* 319 */         this.contentScroll = 0;
/* 320 */         rebuildRenderedLines();
/* 321 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/* 325 */     return super.method_25402(mouseX, mouseY, button);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean method_25401(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
/* 330 */     int sidebarX = this.panelX + 12;
/* 331 */     int sidebarY = this.panelY + 48 + 10;
/* 332 */     int sidebarW = 228;
/* 333 */     int sidebarH = this.panelHeight - 48 - 38 - 20;
/* 334 */     int contentX = this.panelX + 228 + 28;
/* 335 */     int contentY = this.panelY + 48 + 10;
/* 336 */     int contentW = this.panelWidth - 228 - 40;
/* 337 */     int contentH = this.panelHeight - 48 - 38 - 20;
/*     */     
/* 339 */     if (isInside(mouseX, mouseY, contentX, contentY, contentW, contentH)) {
/* 340 */       this.contentScroll = class_3532.method_15340(this.contentScroll - (int)Math.signum(verticalAmount) * 22, 0, getMaxContentScroll());
/* 341 */       return true;
/*     */     } 
/*     */     
/* 344 */     if (isInside(mouseX, mouseY, sidebarX, sidebarY, 228, sidebarH)) {
/* 345 */       int visibleCount = Math.max(1, (sidebarH - 30) / 34);
/* 346 */       int maxOffset = Math.max(0, this.patchNotes.size() - visibleCount);
/* 347 */       this.noteListOffset = class_3532.method_15340(this.noteListOffset - (int)Math.signum(verticalAmount), 0, maxOffset);
/* 348 */       return true;
/*     */     } 
/*     */     
/* 351 */     return super.method_25401(mouseX, mouseY, horizontalAmount, verticalAmount);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean method_25404(int keyCode, int scanCode, int modifiers) {
/* 356 */     if (keyCode == 256) {
/* 357 */       method_25419();
/* 358 */       return true;
/*     */     } 
/* 360 */     if (keyCode == 265) {
/* 361 */       moveSelection(-1);
/* 362 */       return true;
/*     */     } 
/* 364 */     if (keyCode == 264) {
/* 365 */       moveSelection(1);
/* 366 */       return true;
/*     */     } 
/* 368 */     if (keyCode == 266) {
/* 369 */       this.contentScroll = class_3532.method_15340(this.contentScroll - 120, 0, getMaxContentScroll());
/* 370 */       return true;
/*     */     } 
/* 372 */     if (keyCode == 267) {
/* 373 */       this.contentScroll = class_3532.method_15340(this.contentScroll + 120, 0, getMaxContentScroll());
/* 374 */       return true;
/*     */     } 
/* 376 */     if (keyCode == 268) {
/* 377 */       this.contentScroll = 0;
/* 378 */       return true;
/*     */     } 
/* 380 */     if (keyCode == 269) {
/* 381 */       this.contentScroll = getMaxContentScroll();
/* 382 */       return true;
/*     */     } 
/* 384 */     return super.method_25404(keyCode, scanCode, modifiers);
/*     */   }
/*     */   
/*     */   private void moveSelection(int delta) {
/* 388 */     if (this.patchNotes.isEmpty())
/* 389 */       return;  this.selectedPatchIndex = class_3532.method_15340(this.selectedPatchIndex + delta, 0, this.patchNotes.size() - 1);
/* 390 */     ensureSelectionVisible();
/* 391 */     this.contentScroll = 0;
/* 392 */     rebuildRenderedLines();
/*     */   }
/*     */   
/*     */   private void ensureSelectionVisible() {
/* 396 */     int visibleCount = Math.max(1, (this.panelHeight - 48 - 38 - 50) / 34);
/* 397 */     if (this.selectedPatchIndex < this.noteListOffset) {
/* 398 */       this.noteListOffset = this.selectedPatchIndex;
/* 399 */     } else if (this.selectedPatchIndex >= this.noteListOffset + visibleCount) {
/* 400 */       this.noteListOffset = this.selectedPatchIndex - visibleCount + 1;
/*     */     } 
/*     */   }
/*     */   
/*     */   private int getContentTextWidth() {
/* 405 */     return this.panelWidth - 228 - 70;
/*     */   }
/*     */   
/*     */   private int getMaxContentScroll() {
/* 409 */     int contentViewHeight = this.panelHeight - 48 - 38 - 36;
/* 410 */     return Math.max(0, this.contentHeight - contentViewHeight);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private PatchNotesNetwork.PatchNoteEntry getSelectedPatchNote() {
/* 415 */     if (this.patchNotes.isEmpty()) return null; 
/* 416 */     return this.patchNotes.get(class_3532.method_15340(this.selectedPatchIndex, 0, this.patchNotes.size() - 1));
/*     */   }
/*     */   
/*     */   private int countLines(@NotNull PatchNotesNetwork.PatchNoteEntry patchNote, int kind) {
/* 420 */     int count = 0;
/* 421 */     for (PatchNotesNetwork.PatchNoteLineEntry line : patchNote.lines()) {
/* 422 */       if (line.kind() == kind) count++; 
/*     */     } 
/* 424 */     return count;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   private String formatDate(long publishedAt) {
/* 429 */     if (publishedAt <= 0L) return "Unknown date"; 
/* 430 */     return DATE_FORMATTER.format(Instant.ofEpochMilli(publishedAt).atZone(ZoneId.systemDefault()));
/*     */   }
/*     */   
/*     */   private boolean isInside(double mouseX, double mouseY, int x, int y, int width, int height) {
/* 434 */     return (mouseX >= x && mouseX < (x + width) && mouseY >= y && mouseY < (y + height));
/*     */   }
/*     */ 
/*     */   
/*     */   public void method_25419() {
/* 439 */     if (this.field_22787 != null) {
/* 440 */       this.field_22787.method_1507(null);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean method_25421() {
/* 446 */     return false;
/*     */   } private static final class RenderedLine extends Record { @Nullable
/*     */     private final class_5481 text; private final int color; private final int height; private final boolean shadow;
/* 449 */     private RenderedLine(@Nullable class_5481 text, int color, int height, boolean shadow) { this.text = text; this.color = color; this.height = height; this.shadow = shadow; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/patchnotes/screen/PatchNotesScreen$RenderedLine;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #449	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/* 449 */       //   0	7	0	this	Lcom/atlas/common/fabric/patchnotes/screen/PatchNotesScreen$RenderedLine; } @Nullable public class_5481 text() { return this.text; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/patchnotes/screen/PatchNotesScreen$RenderedLine;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #449	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/patchnotes/screen/PatchNotesScreen$RenderedLine; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/patchnotes/screen/PatchNotesScreen$RenderedLine;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #449	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lcom/atlas/common/fabric/patchnotes/screen/PatchNotesScreen$RenderedLine;
/* 449 */       //   0	8	1	o	Ljava/lang/Object; } public int color() { return this.color; } public int height() { return this.height; } public boolean shadow() { return this.shadow; }
/*     */      }
/*     */ 
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\patchnotes\screen\PatchNotesScreen.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */