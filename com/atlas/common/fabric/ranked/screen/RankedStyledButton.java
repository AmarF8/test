/*    */ package com.atlas.common.fabric.ranked.screen;
/*    */ 
/*    */ import net.minecraft.class_2561;
/*    */ import net.minecraft.class_310;
/*    */ import net.minecraft.class_327;
/*    */ import net.minecraft.class_332;
/*    */ import net.minecraft.class_4185;
/*    */ 
/*    */ public class RankedStyledButton extends class_4185 {
/* 10 */   private static final int BTN_BG = color(35, 45, 65, 255);
/* 11 */   private static final int BTN_HOVER = color(50, 65, 95, 255);
/* 12 */   private static final int BTN_DISABLED = color(25, 30, 35, 255);
/* 13 */   private static final int BTN_ACTIVE = color(60, 80, 120, 255);
/* 14 */   private static final int BTN_BORDER = color(70, 90, 130, 255);
/* 15 */   private static final int BTN_BORDER_HOVER = color(110, 140, 200, 255);
/* 16 */   private static final int TEXT_PRIMARY = color(255, 255, 255, 255);
/* 17 */   private static final int TEXT_DIM = color(130, 140, 160, 255);
/* 18 */   public static final int COLOR_GREEN = color(100, 255, 150, 255);
/* 19 */   public static final int COLOR_RED = color(255, 100, 120, 255);
/* 20 */   public static final int ACCENT_CYAN = color(140, 200, 255, 255);
/* 21 */   public static final int ACCENT_GOLD = color(255, 200, 100, 255);
/* 22 */   public static final int ACCENT_PURPLE = color(200, 140, 255, 255);
/*    */   private final boolean isActiveStyle;
/*    */   private final class_327 font;
/*    */   
/*    */   public static int color(int r, int g, int b, int a) {
/* 27 */     return a << 24 | r << 16 | g << 8 | b;
/*    */   }
/*    */   
/*    */   public static int lighten(int color, int amount) {
/* 31 */     int a = color >> 24 & 0xFF;
/* 32 */     int r = Math.min(255, (color >> 16 & 0xFF) + amount);
/* 33 */     int g = Math.min(255, (color >> 8 & 0xFF) + amount);
/* 34 */     int b = Math.min(255, (color & 0xFF) + amount);
/* 35 */     return a << 24 | r << 16 | g << 8 | b;
/*    */   }
/*    */   
/*    */   public static int darken(int color, int amount) {
/* 39 */     int a = color >> 24 & 0xFF;
/* 40 */     int r = Math.max(0, (color >> 16 & 0xFF) - amount);
/* 41 */     int g = Math.max(0, (color >> 8 & 0xFF) - amount);
/* 42 */     int b = Math.max(0, (color & 0xFF) - amount);
/* 43 */     return a << 24 | r << 16 | g << 8 | b;
/*    */   }
/*    */   
/*    */   public RankedStyledButton(int x, int y, int width, int height, class_2561 message, class_4185.class_4241 onPress) {
/* 47 */     this(x, y, width, height, message, onPress, false);
/*    */   }
/*    */   
/*    */   public RankedStyledButton(int x, int y, int width, int height, class_2561 message, class_4185.class_4241 onPress, boolean isActiveStyle) {
/* 51 */     super(x, y, width, height, message, onPress, field_40754);
/* 52 */     this.isActiveStyle = isActiveStyle;
/* 53 */     this.font = (class_310.method_1551()).field_1772;
/*    */   }
/*    */ 
/*    */   
/*    */   public void method_48579(class_332 graphics, int mouseX, int mouseY, float partialTick) {
/* 58 */     boolean hovered = (this.field_22763 && method_49606());
/* 59 */     int x = method_46426();
/* 60 */     int y = method_46427();
/* 61 */     int w = this.field_22758;
/* 62 */     int h = this.field_22759;
/* 63 */     int bgColor = !this.field_22763 ? BTN_DISABLED : (this.isActiveStyle ? BTN_ACTIVE : (hovered ? BTN_HOVER : BTN_BG));
/* 64 */     graphics.method_25294(x + 1, y + 1, x + w - 1, y + h - 1, bgColor);
/* 65 */     if (this.field_22763) {
/* 66 */       graphics.method_25294(x + 1, y + h - 3, x + w - 1, y + h - 1, darken(bgColor, 15));
/*    */     }
/*    */     
/* 69 */     int borderColor = hovered ? BTN_BORDER_HOVER : BTN_BORDER;
/* 70 */     graphics.method_25294(x + 1, y, x + w - 1, y + 1, borderColor);
/* 71 */     graphics.method_25294(x + 1, y + h - 1, x + w - 1, y + h, darken(borderColor, 30));
/* 72 */     graphics.method_25294(x, y + 1, x + 1, y + h - 1, borderColor);
/* 73 */     graphics.method_25294(x + w - 1, y + 1, x + w, y + h - 1, darken(borderColor, 30));
/* 74 */     if (this.field_22763 && !this.isActiveStyle) {
/* 75 */       graphics.method_25294(x + 2, y + 1, x + w - 2, y + 2, lighten(bgColor, 20));
/* 76 */       graphics.method_25294(x + 1, y + 2, x + 2, y + h - 3, lighten(bgColor, 10));
/*    */     } 
/*    */     
/* 79 */     graphics.method_25294(x, y, x + 1, y + 1, color(0, 0, 0, 0));
/* 80 */     graphics.method_25294(x + w - 1, y, x + w, y + 1, color(0, 0, 0, 0));
/* 81 */     graphics.method_25294(x, y + h - 1, x + 1, y + h, color(0, 0, 0, 0));
/* 82 */     graphics.method_25294(x + w - 1, y + h - 1, x + w, y + h, color(0, 0, 0, 0));
/* 83 */     int textColor = this.field_22763 ? TEXT_PRIMARY : TEXT_DIM;
/* 84 */     graphics.method_27534(this.font, method_25369(), x + w / 2, y + (h - 8) / 2, textColor);
/*    */   }
/*    */   
/* 87 */   public static int getButtonBg() { return BTN_BG; }
/* 88 */   public static int getButtonHover() { return BTN_HOVER; }
/* 89 */   public static int getButtonDisabled() { return BTN_DISABLED; }
/* 90 */   public static int getButtonActive() { return BTN_ACTIVE; }
/* 91 */   public static int getButtonBorder() { return BTN_BORDER; }
/* 92 */   public static int getButtonBorderHover() { return BTN_BORDER_HOVER; }
/* 93 */   public static int getTextPrimary() { return TEXT_PRIMARY; } public static int getTextDim() {
/* 94 */     return TEXT_DIM;
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\ranked\screen\RankedStyledButton.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */