/*     */ package com.atlas.common.fabric.guide.screen.widgets;
/*     */ 
/*     */ import com.atlas.common.fabric.guide.screen.GuideColors;
/*     */ import net.minecraft.class_332;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GuideScrollableArea
/*     */ {
/*     */   private int x;
/*     */   private int y;
/*     */   private int width;
/*     */   private int height;
/*     */   private int contentHeight;
/*     */   private double scrollOffset;
/*     */   private boolean draggingScrollbar = false;
/*     */   private double dragStartY;
/*     */   private double dragStartOffset;
/*  20 */   private int customThumbColor = 0;
/*  21 */   private int customThumbHoverColor = 0;
/*     */   
/*     */   private boolean hasCustomThumbColors = false;
/*     */   
/*     */   private static final int SCROLLBAR_WIDTH = 4;
/*     */   private static final int SCROLLBAR_PADDING = 2;
/*     */   
/*     */   public GuideScrollableArea(int x, int y, int width, int height) {
/*  29 */     this.x = x;
/*  30 */     this.y = y;
/*  31 */     this.width = width;
/*  32 */     this.height = height;
/*  33 */     this.contentHeight = height;
/*  34 */     this.scrollOffset = 0.0D;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setThumbColors(int thumbColor, int thumbHoverColor) {
/*  42 */     this.customThumbColor = thumbColor;
/*  43 */     this.customThumbHoverColor = thumbHoverColor;
/*  44 */     this.hasCustomThumbColors = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateBounds(int x, int y, int width, int height) {
/*  52 */     this.x = x;
/*  53 */     this.y = y;
/*  54 */     this.width = width;
/*  55 */     this.height = height;
/*  56 */     this.scrollOffset = Math.min(this.scrollOffset, getMaxScroll());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setContentHeight(int contentHeight) {
/*  62 */     this.contentHeight = Math.max(this.height, contentHeight);
/*     */     
/*  64 */     this.scrollOffset = Math.min(this.scrollOffset, getMaxScroll());
/*     */   }
/*     */   
/*     */   public int getScrollOffset() {
/*  68 */     return (int)this.scrollOffset;
/*     */   }
/*     */   
/*     */   public void setScrollOffset(double offset) {
/*  72 */     this.scrollOffset = Math.max(0.0D, Math.min(offset, getMaxScroll()));
/*     */   }
/*     */   
/*     */   public int getMaxScroll() {
/*  76 */     return Math.max(0, this.contentHeight - this.height);
/*     */   }
/*     */   
/*     */   public boolean needsScrollbar() {
/*  80 */     return (this.contentHeight > this.height);
/*     */   }
/*     */   
/*     */   public void resetScroll() {
/*  84 */     this.scrollOffset = 0.0D;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void beginRender(class_332 ctx) {
/*  93 */     ctx.method_44379(this.x, this.y, this.x + this.width, this.y + this.height);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void endRender(class_332 ctx) {
/* 100 */     ctx.method_44380();
/*     */     
/* 102 */     if (needsScrollbar()) {
/* 103 */       drawScrollbar(ctx);
/*     */     }
/*     */   }
/*     */   
/*     */   private void drawScrollbar(class_332 ctx) {
/* 108 */     int sbX = this.x + this.width - 4 - 2;
/* 109 */     int sbTop = this.y + 2;
/* 110 */     int sbHeight = this.height - 4;
/*     */ 
/*     */     
/* 113 */     ctx.method_25294(sbX, sbTop, sbX + 4, sbTop + sbHeight, GuideColors.SCROLLBAR_TRACK);
/*     */ 
/*     */     
/* 116 */     double ratio = this.height / this.contentHeight;
/* 117 */     int thumbHeight = Math.max(20, (int)(sbHeight * ratio));
/* 118 */     double scrollRatio = (getMaxScroll() > 0) ? (this.scrollOffset / getMaxScroll()) : 0.0D;
/* 119 */     int thumbY = sbTop + (int)((sbHeight - thumbHeight) * scrollRatio);
/*     */ 
/*     */ 
/*     */     
/* 123 */     int thumbColor = this.draggingScrollbar ? (this.hasCustomThumbColors ? this.customThumbHoverColor : GuideColors.SCROLLBAR_THUMB_HOVER) : (this.hasCustomThumbColors ? this.customThumbColor : GuideColors.SCROLLBAR_THUMB);
/* 124 */     ctx.method_25294(sbX, thumbY, sbX + 4, thumbY + thumbHeight, thumbColor);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean handleScroll(double mouseX, double mouseY, double verticalAmount) {
/* 133 */     if (mouseX >= this.x && mouseX < (this.x + this.width) && mouseY >= this.y && mouseY < (this.y + this.height)) {
/* 134 */       this.scrollOffset -= verticalAmount * 18.0D;
/* 135 */       this.scrollOffset = Math.max(0.0D, Math.min(this.scrollOffset, getMaxScroll()));
/* 136 */       return true;
/*     */     } 
/* 138 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean handleMouseClicked(double mouseX, double mouseY, int button) {
/* 145 */     if (button == 0 && needsScrollbar()) {
/* 146 */       int sbX = this.x + this.width - 4 - 2;
/* 147 */       if (mouseX >= sbX && mouseX < (sbX + 4 + 2) && mouseY >= this.y && mouseY < (this.y + this.height)) {
/*     */         
/* 149 */         this.draggingScrollbar = true;
/* 150 */         this.dragStartY = mouseY;
/* 151 */         this.dragStartOffset = this.scrollOffset;
/* 152 */         return true;
/*     */       } 
/*     */     } 
/* 155 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean handleMouseDragged(double mouseX, double mouseY, int button) {
/* 162 */     if (this.draggingScrollbar && button == 0) {
/* 163 */       double dy = mouseY - this.dragStartY;
/* 164 */       int sbHeight = this.height - 4;
/* 165 */       double ratio = this.height / this.contentHeight;
/* 166 */       int thumbHeight = Math.max(20, (int)(sbHeight * ratio));
/* 167 */       double scrollRange = (sbHeight - thumbHeight);
/*     */       
/* 169 */       if (scrollRange > 0.0D) {
/* 170 */         double scrollDelta = dy / scrollRange * getMaxScroll();
/* 171 */         this.scrollOffset = Math.max(0.0D, Math.min(this.dragStartOffset + scrollDelta, getMaxScroll()));
/*     */       } 
/* 173 */       return true;
/*     */     } 
/* 175 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean handleMouseReleased(double mouseX, double mouseY, int button) {
/* 182 */     if (this.draggingScrollbar) {
/* 183 */       this.draggingScrollbar = false;
/* 184 */       return true;
/*     */     } 
/* 186 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getX() {
/* 191 */     return this.x; }
/* 192 */   public int getY() { return this.y; }
/* 193 */   public int getWidth() { return this.width; } public int getHeight() {
/* 194 */     return this.height;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getContentWidth() {
/* 200 */     return needsScrollbar() ? (this.width - 4 - 4) : this.width;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInBounds(double mouseX, double mouseY) {
/* 207 */     return (mouseX >= this.x && mouseX < (this.x + this.width) && mouseY >= this.y && mouseY < (this.y + this.height));
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\guide\screen\widgets\GuideScrollableArea.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */