/*    */ package com.atlas.common.fabric.safari.fishing.item;
/*    */ 
/*    */ import com.atlas.common.fabric.safari.fishing.SafariUpgradeSlot;
/*    */ import net.minecraft.class_1792;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class SafariUpgradeItem extends class_1792 {
/*    */   private final SafariUpgradeSlot slot;
/*    */   private final String upgradeKey;
/*    */   
/*    */   public SafariUpgradeItem(@NotNull SafariUpgradeSlot slot, @NotNull String upgradeKey, class_1792.class_1793 settings) {
/* 12 */     super(settings);
/* 13 */     this.slot = slot;
/* 14 */     this.upgradeKey = upgradeKey;
/*    */   }
/*    */   
/*    */   public SafariUpgradeSlot getSlot() {
/* 18 */     return this.slot;
/*    */   }
/*    */   
/*    */   public String getUpgradeKey() {
/* 22 */     return this.upgradeKey;
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\safari\fishing\item\SafariUpgradeItem.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */