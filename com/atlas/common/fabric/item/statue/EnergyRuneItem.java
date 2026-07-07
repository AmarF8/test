/*    */ package com.atlas.common.fabric.item.statue;
/*    */ 
/*    */ import net.minecraft.class_1792;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public final class EnergyRuneItem extends class_1792 {
/*    */   private final int durationTicks;
/*    */   
/*    */   public EnergyRuneItem(int durationTicks, @NotNull class_1792.class_1793 settings) {
/* 10 */     super(settings);
/* 11 */     this.durationTicks = durationTicks;
/*    */   }
/*    */   
/*    */   public int getDurationTicks() {
/* 15 */     return this.durationTicks;
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\item\statue\EnergyRuneItem.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */