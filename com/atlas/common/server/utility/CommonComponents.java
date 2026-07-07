/*    */ package com.atlas.common.server.utility;
/*    */ 
/*    */ import com.atlas.common.component.TextColors;
/*    */ import com.atlas.common.component.TextComponent;
/*    */ import net.kyori.adventure.text.Component;
/*    */ import net.kyori.adventure.text.TextComponent;
/*    */ import net.kyori.adventure.text.format.TextColor;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class CommonComponents
/*    */ {
/* 16 */   public static final TextColor DEFAULT_CLICK_INFORMATION_COLOR = TextColors.hex("A396FF");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static Component clickInformation(@NotNull MouseClickType mouseClickType, @NotNull String action) {
/* 27 */     return clickInformation(mouseClickType, DEFAULT_CLICK_INFORMATION_COLOR, action);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public static Component clickInformation(@NotNull MouseClickType mouseClickType, @NotNull TextColor color, @NotNull String action) {
/* 41 */     return clickInformation(mouseClickType, color, TextComponent.of(action, color));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public static Component clickInformation(@NotNull MouseClickType mouseClickType, @NotNull Component action) {
/* 54 */     return clickInformation(mouseClickType, DEFAULT_CLICK_INFORMATION_COLOR, action);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public static Component clickInformation(@NotNull MouseClickType mouseClickType, @NotNull TextColor color, @NotNull Component action) {
/* 68 */     return ((TextComponent)((TextComponent)((TextComponent)Component.empty()
/* 69 */       .append(mouseClickType.getIcon().color(color)))
/* 70 */       .append(TextComponent.of(" ")))
/* 71 */       .append(TextComponent.of(mouseClickType.getText() + " to ", color)))
/* 72 */       .append(action);
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\serve\\utility\CommonComponents.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */