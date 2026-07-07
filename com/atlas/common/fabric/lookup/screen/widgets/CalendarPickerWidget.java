/*     */ package com.atlas.common.fabric.lookup.screen.widgets;
/*     */ 
/*     */ import com.atlas.common.fabric.lookup.screen.LookupColors;
/*     */ import java.time.Instant;
/*     */ import java.time.LocalDate;
/*     */ import java.time.YearMonth;
/*     */ import java.time.ZoneId;
/*     */ import java.time.format.TextStyle;
/*     */ import java.util.Locale;
/*     */ import net.minecraft.class_327;
/*     */ import net.minecraft.class_332;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CalendarPickerWidget
/*     */ {
/*     */   private static final int BTN_HEIGHT = 16;
/*     */   private static final int POPUP_W = 168;
/*     */   private static final int DAY_CELL_W = 20;
/*     */   private static final int DAY_CELL_H = 14;
/*     */   private static final int POPUP_PADDING = 6;
/*     */   private static final int HEADER_H = 16;
/*     */   private static final int DOW_ROW_H = 12;
/*     */   private static final int GRID_ROWS = 6;
/*     */   private static final int PRESET_STRIP_H = 18;
/*     */   private static final int APPLY_BTN_H = 16;
/*     */   private static final int POPUP_H = 172;
/*     */   private int x;
/*     */   private int y;
/*     */   private int width;
/*     */   private boolean isOpen = false;
/*     */   private int viewYear;
/*     */   private int viewMonth;
/*  77 */   private long rangeFrom = -1L;
/*  78 */   private long rangeTo = -1L;
/*     */   
/*     */   private boolean selectingTo = false;
/*     */   
/*  82 */   private long pendingFrom = -1L;
/*  83 */   private long pendingTo = -1L;
/*     */ 
/*     */   
/*  86 */   private String activeLabel = "All time";
/*     */   
/*     */   private DateRangeListener listener;
/*     */   
/*     */   private int popupX;
/*     */   
/*     */   private int popupY;
/*     */   
/*  94 */   private static final String[] PRESET_LABELS = new String[] { "1d", "3d", "7d", "14d", "30d", "All" };
/*     */ 
/*     */ 
/*     */   
/*     */   public CalendarPickerWidget(int x, int y, int width, int height) {
/*  99 */     this.x = x;
/* 100 */     this.y = y;
/* 101 */     this.width = width;
/*     */     
/* 103 */     LocalDate now = LocalDate.now();
/* 104 */     this.viewYear = now.getYear();
/* 105 */     this.viewMonth = now.getMonthValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateBounds(int x, int y, int width, int height) {
/* 110 */     this.x = x;
/* 111 */     this.y = y;
/* 112 */     this.width = width;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setListener(DateRangeListener listener) {
/* 118 */     this.listener = listener;
/*     */   }
/*     */   
/*     */   public boolean isOpen() {
/* 122 */     return this.isOpen;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isMouseOverPopup(int mx, int my) {
/* 131 */     return (this.isOpen && mx >= this.popupX && mx < this.popupX + 168 && my >= this.popupY && my < this.popupY + 172);
/*     */   }
/*     */   
/*     */   public String getActiveLabel() {
/* 135 */     return this.activeLabel;
/*     */   }
/*     */   
/*     */   public void setCommittedRange(long fromMillis, long toMillis) {
/* 139 */     this.rangeFrom = fromMillis;
/* 140 */     this.rangeTo = toMillis;
/* 141 */     this.pendingFrom = fromMillis;
/* 142 */     this.pendingTo = toMillis;
/* 143 */     this.selectingTo = false;
/* 144 */     this.activeLabel = buildActiveLabel(fromMillis, toMillis);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(class_332 ctx, int mouseX, int mouseY, class_327 textRenderer) {
/* 151 */     this.popupX = this.x;
/* 152 */     this.popupY = this.y + 16 + 2;
/*     */ 
/*     */     
/* 155 */     boolean btnHovered = (!this.isOpen && mouseX >= this.x && mouseX < this.x + this.width && mouseY >= this.y && mouseY < this.y + 16);
/* 156 */     int btnBg = this.isOpen ? -14274480 : (btnHovered ? -14274480 : -14801866);
/* 157 */     ctx.method_25294(this.x, this.y, this.x + this.width, this.y + 16, btnBg);
/* 158 */     ctx.method_49601(this.x, this.y, this.width, 16, this.isOpen ? -10777105 : -14012352);
/*     */     
/* 160 */     String btnLabel = this.activeLabel + " ▼";
/* 161 */     int labelW = textRenderer.method_1727(btnLabel);
/* 162 */     int labelColor = this.isOpen ? -10777105 : -1511169;
/* 163 */     ctx.method_51433(textRenderer, btnLabel, this.x + (this.width - labelW) / 2, this.y + 4, labelColor, false);
/*     */     
/* 165 */     if (!this.isOpen) {
/*     */       return;
/*     */     }
/* 168 */     ctx.method_51448().method_22903();
/* 169 */     ctx.method_51448().method_46416(0.0F, 0.0F, 400.0F);
/*     */ 
/*     */     
/* 172 */     ctx.method_25294(this.popupX, this.popupY, this.popupX + 168, this.popupY + 172, -15328732);
/* 173 */     ctx.method_49601(this.popupX, this.popupY, 168, 172, -12958368);
/*     */     
/* 175 */     int cy = this.popupY + 6;
/*     */ 
/*     */     
/* 178 */     cy = renderHeader(ctx, textRenderer, mouseX, mouseY, cy);
/*     */ 
/*     */     
/* 181 */     cy = renderDowLabels(ctx, textRenderer, cy);
/*     */ 
/*     */     
/* 184 */     cy = renderDayGrid(ctx, textRenderer, mouseX, mouseY, cy);
/*     */ 
/*     */     
/* 187 */     cy = renderPresets(ctx, textRenderer, mouseX, mouseY, cy);
/*     */ 
/*     */     
/* 190 */     renderApplyButton(ctx, textRenderer, mouseX, mouseY, cy);
/*     */     
/* 192 */     ctx.method_51448().method_22909();
/*     */   }
/*     */ 
/*     */   
/*     */   private int renderHeader(class_332 ctx, class_327 textRenderer, int mouseX, int mouseY, int cy) {
/* 197 */     String monthName = YearMonth.of(this.viewYear, this.viewMonth).getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
/* 198 */     String header = monthName + " " + monthName;
/*     */ 
/*     */     
/* 201 */     int prevX = this.popupX + 6;
/* 202 */     boolean prevH = (mouseX >= prevX && mouseX < prevX + 12 && mouseY >= cy && mouseY < cy + 16);
/* 203 */     ctx.method_51433(textRenderer, "❮", prevX + 2, cy + 4, 
/* 204 */         prevH ? -10777105 : -9930592, false);
/*     */ 
/*     */     
/* 207 */     int hw = textRenderer.method_1727(header);
/* 208 */     ctx.method_51433(textRenderer, header, this.popupX + (168 - hw) / 2, cy + 4, -1511169, false);
/*     */ 
/*     */ 
/*     */     
/* 212 */     int nextX = this.popupX + 168 - 6 - 12;
/* 213 */     boolean nextH = (mouseX >= nextX && mouseX < nextX + 12 && mouseY >= cy && mouseY < cy + 16);
/* 214 */     ctx.method_51433(textRenderer, "❯", nextX + 2, cy + 4, 
/* 215 */         nextH ? -10777105 : -9930592, false);
/*     */     
/* 217 */     return cy + 16 + 4;
/*     */   }
/*     */   
/*     */   private int renderDowLabels(class_332 ctx, class_327 textRenderer, int cy) {
/* 221 */     String[] dow = { "S", "M", "T", "W", "T", "F", "S" };
/* 222 */     int gridLeft = this.popupX + 6;
/* 223 */     for (int d = 0; d < 7; d++) {
/* 224 */       int cx = gridLeft + d * 20;
/* 225 */       int lw = textRenderer.method_1727(dow[d]);
/* 226 */       ctx.method_51433(textRenderer, dow[d], cx + (20 - lw) / 2, cy + 2, -9930592, false);
/*     */     } 
/*     */     
/* 229 */     return cy + 12 + 2;
/*     */   }
/*     */   
/*     */   private int renderDayGrid(class_332 ctx, class_327 textRenderer, int mouseX, int mouseY, int cy) {
/* 233 */     LocalDate today = LocalDate.now();
/* 234 */     YearMonth ym = YearMonth.of(this.viewYear, this.viewMonth);
/* 235 */     LocalDate firstOfMonth = ym.atDay(1);
/*     */     
/* 237 */     int startDow = firstOfMonth.getDayOfWeek().getValue() % 7;
/* 238 */     int daysInMonth = ym.lengthOfMonth();
/*     */     
/* 240 */     int gridLeft = this.popupX + 6;
/*     */ 
/*     */     
/* 243 */     LocalDate selFrom = millisToLocalDate(this.pendingFrom);
/* 244 */     LocalDate selTo = (this.pendingTo >= 0L) ? millisToLocalDate(this.pendingTo) : selFrom;
/* 245 */     if (selFrom != null && selTo != null && selFrom.isAfter(selTo)) {
/* 246 */       LocalDate tmp = selFrom; selFrom = selTo; selTo = tmp;
/*     */     } 
/*     */     
/* 249 */     int dayOfMonth = 1;
/*     */     
/* 251 */     YearMonth prevYm = ym.minusMonths(1L);
/* 252 */     int prevMonthDays = prevYm.lengthOfMonth();
/*     */     
/* 254 */     for (int row = 0; row < 6; row++) {
/* 255 */       for (int col = 0; col < 7; col++) {
/* 256 */         int slot = row * 7 + col;
/* 257 */         int cellX = gridLeft + col * 20;
/* 258 */         int cellY = cy + row * 14;
/*     */         
/* 260 */         if (slot < startDow) {
/*     */           
/* 262 */           int prevDay = prevMonthDays - startDow - slot - 1;
/* 263 */           ctx.method_51433(textRenderer, String.valueOf(prevDay), cellX + (20 - textRenderer
/* 264 */               .method_1727(String.valueOf(prevDay))) / 2, cellY + 3, 
/*     */               
/* 266 */               LookupColors.withAlpha(-9930592, 80), false);
/*     */ 
/*     */         
/*     */         }
/* 270 */         else if (dayOfMonth > daysInMonth) {
/*     */           
/* 272 */           int nextDay = dayOfMonth - daysInMonth;
/* 273 */           ctx.method_51433(textRenderer, String.valueOf(nextDay), cellX + (20 - textRenderer
/* 274 */               .method_1727(String.valueOf(nextDay))) / 2, cellY + 3, 
/*     */               
/* 276 */               LookupColors.withAlpha(-9930592, 80), false);
/* 277 */           dayOfMonth++;
/*     */         }
/*     */         else {
/*     */           
/* 281 */           LocalDate cellDate = ym.atDay(dayOfMonth);
/* 282 */           boolean isToday = cellDate.equals(today);
/* 283 */           boolean inRange = (selFrom != null && !cellDate.isBefore(selFrom) && !cellDate.isAfter(selTo));
/* 284 */           boolean isEndpoint = (cellDate.equals(selFrom) || (selTo != null && cellDate.equals(selTo)));
/* 285 */           boolean hovered = (mouseX >= cellX && mouseX < cellX + 20 && mouseY >= cellY && mouseY < cellY + 14);
/*     */ 
/*     */ 
/*     */           
/* 289 */           if (isEndpoint) {
/* 290 */             ctx.method_25294(cellX + 1, cellY + 1, cellX + 20 - 1, cellY + 14 - 1, -10777105);
/*     */           }
/* 292 */           else if (inRange) {
/* 293 */             ctx.method_25294(cellX, cellY + 1, cellX + 20, cellY + 14 - 1, 
/* 294 */                 LookupColors.withAlpha(-10777105, 50));
/* 295 */           } else if (hovered) {
/* 296 */             ctx.method_25294(cellX + 1, cellY + 1, cellX + 20 - 1, cellY + 14 - 1, -14274480);
/*     */           }
/* 298 */           else if (isToday) {
/* 299 */             ctx.method_49601(cellX + 1, cellY + 1, 18, 12, 
/* 300 */                 LookupColors.withAlpha(-10777105, 100));
/*     */           } 
/*     */           
/* 303 */           String dayStr = String.valueOf(dayOfMonth);
/* 304 */           int dw = textRenderer.method_1727(dayStr);
/*     */ 
/*     */           
/* 307 */           int textColor = isEndpoint ? -1 : (isToday ? -10777105 : (hovered ? -1511169 : -9930592));
/* 308 */           ctx.method_51433(textRenderer, dayStr, cellX + (20 - dw) / 2, cellY + 3, textColor, false);
/*     */ 
/*     */           
/* 311 */           dayOfMonth++;
/*     */         } 
/*     */       } 
/*     */     } 
/* 315 */     return cy + 84 + 4;
/*     */   }
/*     */   
/*     */   private int renderPresets(class_332 ctx, class_327 textRenderer, int mouseX, int mouseY, int cy) {
/* 319 */     int totalPresetW = 156;
/* 320 */     int perPreset = totalPresetW / PRESET_LABELS.length;
/* 321 */     int px = this.popupX + 6;
/*     */     
/* 323 */     for (int i = 0; i < PRESET_LABELS.length; i++) {
/* 324 */       int pBtnW = (i < PRESET_LABELS.length - 1) ? perPreset : (totalPresetW - perPreset * (PRESET_LABELS.length - 1));
/* 325 */       boolean hovered = (mouseX >= px && mouseX < px + pBtnW && mouseY >= cy && mouseY < cy + 18 - 2);
/*     */       
/* 327 */       boolean active = isPresetActive(i);
/*     */       
/* 329 */       int bg = active ? LookupColors.withAlpha(-10777105, 60) : (hovered ? -14274480 : -14801866);
/* 330 */       ctx.method_25294(px, cy, px + pBtnW - 1, cy + 18 - 2, bg);
/* 331 */       if (active) {
/* 332 */         ctx.method_49601(px, cy, pBtnW - 1, 16, -10777105);
/*     */       }
/*     */       
/* 335 */       int lw = textRenderer.method_1727(PRESET_LABELS[i]);
/* 336 */       int textColor = active ? -10777105 : (hovered ? -1511169 : -9930592);
/*     */       
/* 338 */       if (lw > pBtnW - 4) {
/*     */         
/* 340 */         ctx.method_51433(textRenderer, PRESET_LABELS[i], px + 2, cy + 4, textColor, false);
/*     */       } else {
/* 342 */         ctx.method_51433(textRenderer, PRESET_LABELS[i], px + (pBtnW - 1 - lw) / 2, cy + 4, textColor, false);
/*     */       } 
/*     */       
/* 345 */       px += pBtnW;
/*     */     } 
/*     */     
/* 348 */     return cy + 18 + 4;
/*     */   }
/*     */   
/*     */   private void renderApplyButton(class_332 ctx, class_327 textRenderer, int mouseX, int mouseY, int cy) {
/* 352 */     int btnW = 156;
/* 353 */     int btnX = this.popupX + 6;
/* 354 */     boolean hovered = (mouseX >= btnX && mouseX < btnX + btnW && mouseY >= cy && mouseY < cy + 16);
/*     */     
/* 356 */     int bg = hovered ? -10777105 : -14801354;
/* 357 */     ctx.method_25294(btnX, cy, btnX + btnW, cy + 16, bg);
/* 358 */     ctx.method_49601(btnX, cy, btnW, 16, hovered ? -10777105 : -12958368);
/*     */     
/* 360 */     String applyLabel = "Apply";
/* 361 */     int lw = textRenderer.method_1727(applyLabel);
/* 362 */     ctx.method_51433(textRenderer, applyLabel, btnX + (btnW - lw) / 2, cy + 4, 
/* 363 */         hovered ? -1 : -1511169, false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean mouseClicked(double mouseX, double mouseY, int button) {
/* 369 */     if (button != 0) return false;
/*     */     
/* 371 */     int mx = (int)mouseX;
/* 372 */     int my = (int)mouseY;
/*     */ 
/*     */     
/* 375 */     if (mx >= this.x && mx < this.x + this.width && my >= this.y && my < this.y + 16) {
/* 376 */       this.isOpen = !this.isOpen;
/* 377 */       if (this.isOpen) {
/*     */         
/* 379 */         this.pendingFrom = this.rangeFrom;
/* 380 */         this.pendingTo = this.rangeTo;
/* 381 */         this.selectingTo = false;
/*     */       } 
/* 383 */       return true;
/*     */     } 
/*     */     
/* 386 */     if (!this.isOpen) return false;
/*     */ 
/*     */     
/* 389 */     if (mx < this.popupX || mx >= this.popupX + 168 || my < this.popupY || my >= this.popupY + 172) {
/* 390 */       this.isOpen = false;
/* 391 */       return false;
/*     */     } 
/*     */     
/* 394 */     int cy = this.popupY + 6;
/*     */ 
/*     */     
/* 397 */     int prevX = this.popupX + 6;
/* 398 */     if (mx >= prevX && mx < prevX + 12 && my >= cy && my < cy + 16) {
/* 399 */       navigatePrev();
/* 400 */       return true;
/*     */     } 
/* 402 */     int nextX = this.popupX + 168 - 6 - 12;
/* 403 */     if (mx >= nextX && mx < nextX + 12 && my >= cy && my < cy + 16) {
/* 404 */       navigateNext();
/* 405 */       return true;
/*     */     } 
/* 407 */     cy += 20;
/*     */ 
/*     */     
/* 410 */     cy += 14;
/*     */ 
/*     */     
/* 413 */     int gridLeft = this.popupX + 6;
/* 414 */     YearMonth ym = YearMonth.of(this.viewYear, this.viewMonth);
/* 415 */     int startDow = ym.atDay(1).getDayOfWeek().getValue() % 7;
/* 416 */     int daysInMonth = ym.lengthOfMonth();
/*     */     
/* 418 */     for (int row = 0; row < 6; row++) {
/* 419 */       for (int col = 0; col < 7; col++) {
/* 420 */         int slot = row * 7 + col;
/* 421 */         int cellX = gridLeft + col * 20;
/* 422 */         int cellY = cy + row * 14;
/*     */         
/* 424 */         if (mx >= cellX && mx < cellX + 20 && my >= cellY && my < cellY + 14) {
/* 425 */           int dayOfMonth = slot - startDow + 1;
/* 426 */           if (dayOfMonth < 1 || dayOfMonth > daysInMonth) return true; 
/* 427 */           LocalDate clicked = ym.atDay(dayOfMonth);
/* 428 */           handleDayClick(clicked);
/* 429 */           return true;
/*     */         } 
/*     */       } 
/*     */     } 
/* 433 */     cy += 88;
/*     */ 
/*     */     
/* 436 */     int totalPresetW = 156;
/* 437 */     int perPreset = totalPresetW / PRESET_LABELS.length;
/* 438 */     int px = this.popupX + 6;
/* 439 */     for (int i = 0; i < PRESET_LABELS.length; i++) {
/* 440 */       int pBtnW = (i < PRESET_LABELS.length - 1) ? perPreset : (totalPresetW - perPreset * (PRESET_LABELS.length - 1));
/* 441 */       if (mx >= px && mx < px + pBtnW && my >= cy && my < cy + 18 - 2) {
/* 442 */         applyPreset(i);
/* 443 */         return true;
/*     */       } 
/* 445 */       px += pBtnW;
/*     */     } 
/* 447 */     cy += 22;
/*     */ 
/*     */     
/* 450 */     int btnW = 156;
/* 451 */     int btnX = this.popupX + 6;
/* 452 */     if (mx >= btnX && mx < btnX + btnW && my >= cy && my < cy + 16) {
/* 453 */       commitRange();
/* 454 */       return true;
/*     */     } 
/*     */     
/* 457 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void navigatePrev() {
/* 463 */     this.viewMonth--;
/* 464 */     if (this.viewMonth < 1) { this.viewMonth = 12; this.viewYear--; }
/*     */   
/*     */   }
/*     */   private void navigateNext() {
/* 468 */     this.viewMonth++;
/* 469 */     if (this.viewMonth > 12) { this.viewMonth = 1; this.viewYear++; }
/*     */   
/*     */   }
/*     */ 
/*     */   
/*     */   private void handleDayClick(LocalDate date) {
/* 475 */     if (!this.selectingTo) {
/*     */       
/* 477 */       this.pendingFrom = localDateToStartMillis(date);
/* 478 */       this.pendingTo = -1L;
/* 479 */       this.selectingTo = true;
/*     */     } else {
/*     */       
/* 482 */       long clickedStartMs = localDateToStartMillis(date);
/* 483 */       long clickedEndMs = localDateToEndMillis(date);
/* 484 */       if (clickedStartMs < this.pendingFrom) {
/*     */         
/* 486 */         LocalDate fromDate = millisToLocalDate(this.pendingFrom);
/* 487 */         this.pendingTo = (fromDate != null) ? localDateToEndMillis(fromDate) : this.pendingFrom;
/* 488 */         this.pendingFrom = clickedStartMs;
/*     */       } else {
/* 490 */         this.pendingTo = clickedEndMs;
/*     */       } 
/* 492 */       this.selectingTo = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void applyPreset(int index) {
/* 499 */     LocalDate today = LocalDate.now();
/* 500 */     this.selectingTo = false;
/* 501 */     switch (index) {
/*     */       case 0:
/* 503 */         this.pendingFrom = localDateToStartMillis(today);
/* 504 */         this.pendingTo = localDateToEndMillis(today);
/* 505 */         this.activeLabel = "Last 1 day";
/*     */         break;
/*     */       case 1:
/* 508 */         this.pendingFrom = localDateToStartMillis(today.minusDays(2L));
/* 509 */         this.pendingTo = localDateToEndMillis(today);
/* 510 */         this.activeLabel = "Last 3 days";
/*     */         break;
/*     */       case 2:
/* 513 */         this.pendingFrom = localDateToStartMillis(today.minusDays(6L));
/* 514 */         this.pendingTo = localDateToEndMillis(today);
/* 515 */         this.activeLabel = "Last 7 days";
/*     */         break;
/*     */       case 3:
/* 518 */         this.pendingFrom = localDateToStartMillis(today.minusDays(13L));
/* 519 */         this.pendingTo = localDateToEndMillis(today);
/* 520 */         this.activeLabel = "Last 14 days";
/*     */         break;
/*     */       case 4:
/* 523 */         this.pendingFrom = localDateToStartMillis(today.minusDays(29L));
/* 524 */         this.pendingTo = localDateToEndMillis(today);
/* 525 */         this.activeLabel = "Last 30 days";
/*     */         break;
/*     */       case 5:
/* 528 */         this.pendingFrom = -1L;
/* 529 */         this.pendingTo = -1L;
/* 530 */         this.activeLabel = "All time";
/*     */         break;
/*     */     } 
/*     */     
/* 534 */     commitRange();
/*     */   }
/*     */   
/*     */   private void commitRange() {
/* 538 */     this.rangeFrom = this.pendingFrom;
/* 539 */     this.rangeTo = this.pendingTo;
/*     */ 
/*     */     
/* 542 */     if (this.rangeFrom >= 0L && this.rangeTo < 0L) {
/* 543 */       LocalDate fromDate = millisToLocalDate(this.rangeFrom);
/* 544 */       if (fromDate != null) {
/* 545 */         this.rangeTo = localDateToEndMillis(fromDate);
/*     */       }
/*     */     } 
/*     */     
/* 549 */     this.activeLabel = buildActiveLabel(this.rangeFrom, this.rangeTo);
/*     */     
/* 551 */     this.isOpen = false;
/* 552 */     this.selectingTo = false;
/*     */     
/* 554 */     if (this.listener != null) {
/* 555 */       this.listener.onRangeChanged(this.rangeFrom, this.rangeTo);
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean isPresetLabel(String label) {
/* 560 */     return (label.equals("Last 1 day") || label
/* 561 */       .equals("Last 3 days") || label.equals("Last 7 days") || label
/* 562 */       .equals("Last 14 days") || label.equals("Last 30 days") || label
/* 563 */       .equals("All time"));
/*     */   }
/*     */   
/*     */   private boolean isPresetActive(int index) {
/* 567 */     switch (index) { case 0: case 1: case 2: case 3: case 4: case 5:  }  return false;
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
/*     */   
/*     */   private String buildActiveLabel(long from, long to) {
/* 581 */     if (from < 0L) return "All time"; 
/* 582 */     LocalDate fromDate = millisToLocalDate(from);
/* 583 */     LocalDate toDate = millisToLocalDate((to >= 0L) ? to : from);
/* 584 */     LocalDate today = LocalDate.now();
/*     */     
/* 586 */     if (fromDate != null && toDate != null && toDate.equals(today)) {
/* 587 */       if (fromDate.equals(today)) return "Last 1 day"; 
/* 588 */       if (fromDate.equals(today.minusDays(2L))) return "Last 3 days"; 
/* 589 */       if (fromDate.equals(today.minusDays(6L))) return "Last 7 days"; 
/* 590 */       if (fromDate.equals(today.minusDays(13L))) return "Last 14 days"; 
/* 591 */       if (fromDate.equals(today.minusDays(29L))) return "Last 30 days";
/*     */     
/*     */     } 
/* 594 */     return buildManualLabel(from, to);
/*     */   }
/*     */   
/*     */   private String buildManualLabel(long from, long to) {
/* 598 */     if (from < 0L) return "All time"; 
/* 599 */     LocalDate fromDate = millisToLocalDate(from);
/* 600 */     LocalDate toDate = millisToLocalDate((to >= 0L) ? to : from);
/* 601 */     if (toDate == null || fromDate == null || fromDate.equals(toDate))
/*     */     {
/* 603 */       return formatDateShort(fromDate);
/*     */     }
/* 605 */     return formatDateShort(fromDate) + " - " + formatDateShort(fromDate);
/*     */   }
/*     */   
/*     */   private static String formatDateShort(LocalDate date) {
/* 609 */     if (date == null) return "?"; 
/* 610 */     String month = date.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
/* 611 */     return month + " " + month;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static long localDateToStartMillis(LocalDate date) {
/* 617 */     return date.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
/*     */   }
/*     */ 
/*     */   
/*     */   private static long localDateToEndMillis(LocalDate date) {
/* 622 */     return date.plusDays(1L).atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli() - 1L;
/*     */   }
/*     */   
/*     */   private static LocalDate millisToLocalDate(long millis) {
/* 626 */     if (millis < 0L) return null; 
/* 627 */     return Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()).toLocalDate();
/*     */   }
/*     */ 
/*     */   
/*     */   public long getRangeFrom() {
/* 632 */     return this.rangeFrom; } public long getRangeTo() {
/* 633 */     return this.rangeTo;
/*     */   }
/*     */   
/*     */   public static interface DateRangeListener {
/*     */     void onRangeChanged(long param1Long1, long param1Long2);
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\lookup\screen\widgets\CalendarPickerWidget.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */