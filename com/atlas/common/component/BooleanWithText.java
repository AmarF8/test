/*    */ package com.atlas.common.component;
/*    */ 
/*    */ import java.util.Objects;
/*    */ import net.kyori.adventure.text.Component;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class BooleanWithText
/*    */ {
/*    */   @NotNull
/* 15 */   public static final BooleanWithText TRUE = new BooleanWithText(true, (Component)Component.empty()); @NotNull
/* 16 */   public static final BooleanWithText FALSE = new BooleanWithText(false, (Component)Component.empty());
/*    */ 
/*    */   
/*    */   private final boolean status;
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   private final Component component;
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public static BooleanWithText of(boolean value, @NotNull Component component) {
/* 28 */     return new BooleanWithText(value, component);
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public BooleanWithText(boolean status, @NotNull Component component) {
/* 47 */     this.status = status;
/* 48 */     this.component = Objects.<Component>requireNonNull(component);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean getStatus() {
/* 55 */     return this.status;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Component getText() {
/* 63 */     return this.component;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isTrue() {
/* 70 */     return this.status;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isFalse() {
/* 77 */     return !this.status;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public BooleanWithText withText(@NotNull Component component) {
/* 88 */     return new BooleanWithText(this.status, component);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public BooleanWithText withStatus(boolean status) {
/* 99 */     return new BooleanWithText(status, this.component);
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\component\BooleanWithText.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */