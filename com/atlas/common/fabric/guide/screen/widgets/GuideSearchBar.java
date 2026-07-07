/*     */ package com.atlas.common.fabric.guide.screen.widgets;
/*     */ 
/*     */ import com.atlas.common.fabric.guide.screen.GuideColors;
/*     */ import java.util.function.Consumer;
/*     */ import net.minecraft.class_327;
/*     */ import net.minecraft.class_332;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GuideSearchBar
/*     */ {
/*     */   private int x;
/*     */   private int y;
/*     */   private int width;
/*     */   private int height;
/*  17 */   private String text = "";
/*     */   private boolean focused = false;
/*     */   private Consumer<String> onTextChanged;
/*  20 */   private String placeholder = "Search...";
/*  21 */   private int cursorPos = 0;
/*     */   private long lastBlinkTime;
/*  23 */   private int maxLength = 64;
/*     */ 
/*     */   
/*  26 */   private int selectionStart = -1;
/*     */   
/*     */   public GuideSearchBar(int x, int y, int width, int height) {
/*  29 */     this.x = x;
/*  30 */     this.y = y;
/*  31 */     this.width = width;
/*  32 */     this.height = height;
/*  33 */     this.lastBlinkTime = System.currentTimeMillis();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateBounds(int x, int y, int width, int height) {
/*  40 */     this.x = x;
/*  41 */     this.y = y;
/*  42 */     this.width = width;
/*  43 */     this.height = height;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public GuideSearchBar setPlaceholder(String placeholder) {
/*  49 */     this.placeholder = placeholder;
/*  50 */     return this;
/*     */   }
/*     */   
/*     */   public GuideSearchBar setOnTextChanged(Consumer<String> callback) {
/*  54 */     this.onTextChanged = callback;
/*  55 */     return this;
/*     */   }
/*     */   
/*     */   public GuideSearchBar setMaxLength(int maxLength) {
/*  59 */     this.maxLength = maxLength;
/*  60 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getText() {
/*  65 */     return this.text;
/*     */   }
/*     */   public void setText(String text) {
/*  68 */     this.text = text;
/*  69 */     this.cursorPos = text.length();
/*     */   }
/*     */   public boolean isFocused() {
/*  72 */     return this.focused;
/*     */   }
/*     */   public void setFocused(boolean focused) {
/*  75 */     this.focused = focused;
/*  76 */     if (focused) this.lastBlinkTime = System.currentTimeMillis();
/*     */   
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(class_332 ctx, class_327 textRenderer, int mouseX, int mouseY) {
/*  82 */     boolean hovered = (mouseX >= this.x && mouseX < this.x + this.width && mouseY >= this.y && mouseY < this.y + this.height);
/*     */ 
/*     */     
/*  85 */     ctx.method_25294(this.x, this.y, this.x + this.width, this.y + this.height, GuideColors.SEARCH_BG);
/*     */ 
/*     */ 
/*     */     
/*  89 */     int borderColor = this.focused ? GuideColors.SEARCH_BORDER_FOCUS : (hovered ? GuideColors.BORDER_HIGHLIGHT : GuideColors.SEARCH_BORDER);
/*  90 */     ctx.method_49601(this.x, this.y, this.width, this.height, borderColor);
/*     */ 
/*     */     
/*  93 */     int textX = this.x + 6;
/*  94 */     int textY = this.y + (this.height - 8) / 2;
/*     */     
/*  96 */     ctx.method_51433(textRenderer, "⚲", textX, textY, GuideColors.TEXT_DIM, false);
/*  97 */     textX += 12;
/*     */ 
/*     */     
/* 100 */     if (this.text.isEmpty() && !this.focused) {
/* 101 */       ctx.method_51433(textRenderer, this.placeholder, textX, textY, GuideColors.SEARCH_PLACEHOLDER, false);
/*     */     } else {
/*     */       
/* 104 */       String visibleText = this.text;
/* 105 */       int availWidth = this.width - 24 - (this.text.isEmpty() ? 0 : 14);
/*     */ 
/*     */       
/* 108 */       while (textRenderer.method_1727(visibleText) > availWidth && visibleText.length() > 0) {
/* 109 */         visibleText = visibleText.substring(1);
/*     */       }
/*     */       
/* 112 */       ctx.method_51433(textRenderer, visibleText, textX, textY, GuideColors.TEXT_PRIMARY, true);
/*     */ 
/*     */       
/* 115 */       if (this.focused) {
/* 116 */         long elapsed = (System.currentTimeMillis() - this.lastBlinkTime) % 1000L;
/* 117 */         if (elapsed < 500L) {
/* 118 */           String beforeCursor = this.text.substring(0, this.cursorPos);
/*     */           
/* 120 */           int trimmed = this.text.length() - visibleText.length();
/* 121 */           if (this.cursorPos >= trimmed) {
/* 122 */             String visibleBeforeCursor = this.text.substring(trimmed, this.cursorPos);
/* 123 */             int cursorX = textX + textRenderer.method_1727(visibleBeforeCursor);
/* 124 */             ctx.method_25294(cursorX, textY - 1, cursorX + 1, textY + 9, GuideColors.TEXT_PRIMARY);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 130 */       if (!this.text.isEmpty()) {
/* 131 */         int clearX = this.x + this.width - 14;
/* 132 */         int clearY = textY;
/* 133 */         boolean clearHovered = (mouseX >= clearX - 2 && mouseX < clearX + 8 && mouseY >= clearY - 2 && mouseY < clearY + 10);
/* 134 */         ctx.method_51433(textRenderer, "×", clearX, clearY, clearHovered ? GuideColors.TEXT_WHITE : GuideColors.TEXT_DIM, true);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean mouseClicked(double mouseX, double mouseY, int button) {
/* 142 */     boolean wasInside = (mouseX >= this.x && mouseX < (this.x + this.width) && mouseY >= this.y && mouseY < (this.y + this.height));
/* 143 */     setFocused(wasInside);
/*     */     
/* 145 */     if (wasInside) {
/*     */       
/* 147 */       if (!this.text.isEmpty() && mouseX >= (this.x + this.width - 14)) {
/* 148 */         this.text = "";
/* 149 */         this.cursorPos = 0;
/* 150 */         if (this.onTextChanged != null) this.onTextChanged.accept(this.text); 
/* 151 */         return true;
/*     */       } 
/* 153 */       return true;
/*     */     } 
/* 155 */     return false;
/*     */   }
/*     */   
/*     */   public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
/* 159 */     if (!this.focused) return false;
/*     */     
/* 161 */     switch (keyCode) {
/*     */       case 259:
/* 163 */         if (this.cursorPos > 0) {
/* 164 */           this.text = this.text.substring(0, this.cursorPos - 1) + this.text.substring(0, this.cursorPos - 1);
/* 165 */           this.cursorPos--;
/* 166 */           this.lastBlinkTime = System.currentTimeMillis();
/* 167 */           if (this.onTextChanged != null) this.onTextChanged.accept(this.text); 
/*     */         } 
/* 169 */         return true;
/*     */       
/*     */       case 261:
/* 172 */         if (this.cursorPos < this.text.length()) {
/* 173 */           this.text = this.text.substring(0, this.cursorPos) + this.text.substring(0, this.cursorPos);
/* 174 */           this.lastBlinkTime = System.currentTimeMillis();
/* 175 */           if (this.onTextChanged != null) this.onTextChanged.accept(this.text); 
/*     */         } 
/* 177 */         return true;
/*     */       
/*     */       case 263:
/* 180 */         if (this.cursorPos > 0) {
/* 181 */           this.cursorPos--;
/* 182 */           this.lastBlinkTime = System.currentTimeMillis();
/*     */         } 
/* 184 */         return true;
/*     */       
/*     */       case 262:
/* 187 */         if (this.cursorPos < this.text.length()) {
/* 188 */           this.cursorPos++;
/* 189 */           this.lastBlinkTime = System.currentTimeMillis();
/*     */         } 
/* 191 */         return true;
/*     */       
/*     */       case 268:
/* 194 */         this.cursorPos = 0;
/* 195 */         this.lastBlinkTime = System.currentTimeMillis();
/* 196 */         return true;
/*     */       
/*     */       case 269:
/* 199 */         this.cursorPos = this.text.length();
/* 200 */         this.lastBlinkTime = System.currentTimeMillis();
/* 201 */         return true;
/*     */       
/*     */       case 65:
/* 204 */         if ((modifiers & 0x2) != 0) {
/*     */           
/* 206 */           this.cursorPos = this.text.length();
/* 207 */           return true;
/*     */         } 
/*     */         break;
/*     */       case 256:
/* 211 */         setFocused(false);
/* 212 */         return false;
/*     */     } 
/*     */     
/* 215 */     return false;
/*     */   }
/*     */   
/*     */   public boolean charTyped(char chr, int modifiers) {
/* 219 */     if (!this.focused) return false; 
/* 220 */     if (chr < ' ') return false; 
/* 221 */     if (this.text.length() >= this.maxLength) return false;
/*     */     
/* 223 */     this.text = this.text.substring(0, this.cursorPos) + this.text.substring(0, this.cursorPos) + chr;
/* 224 */     this.cursorPos++;
/* 225 */     this.lastBlinkTime = System.currentTimeMillis();
/* 226 */     if (this.onTextChanged != null) this.onTextChanged.accept(this.text); 
/* 227 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getX() {
/* 232 */     return this.x; }
/* 233 */   public int getY() { return this.y; }
/* 234 */   public int getWidth() { return this.width; } public int getHeight() {
/* 235 */     return this.height;
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\guide\screen\widgets\GuideSearchBar.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */