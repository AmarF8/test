/*    */ package com.atlas.common.fabric.item.armor;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.minecraft.class_2561;
/*    */ import net.minecraft.class_2583;
/*    */ import net.minecraft.class_5250;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ public final class PokeArmorLore
/*    */ {
/*    */   @NotNull
/*    */   public static List<class_2561> getLore(@NotNull PokeArmorSet armorSet) {
/* 15 */     List<class_2561> lore = new ArrayList<>();
/*    */     
/* 17 */     lore.add(class_2561.method_43473());
/* 18 */     lore.add(line(10526880, false, "Armor forged from rare Pokemon remains"));
/* 19 */     lore.add(line(10526880, false, "and tempered with " + armorSet.displayName() + " power."));
/* 20 */     lore.add(class_2561.method_43473());
/* 21 */     lore.add(line(armorSet.colour(), true, "Piece Benefits"));
/* 22 */     lore.add(line(armorSet.colour(), false, " - Counts toward the " + armorSet.displayName() + " Poke Armor set"));
/* 23 */     lore.add(class_2561.method_43473());
/* 24 */     lore.add(line(armorSet.colour(), true, "Set Bonus"));
/*    */     
/* 26 */     for (String bonusLine : armorSet.setBonusLore()) {
/* 27 */       lore.add(line(armorSet.colour(), false, " - " + bonusLine));
/*    */     }
/*    */     
/* 30 */     return lore;
/*    */   }
/*    */   @NotNull
/*    */   private static class_5250 line(int colour, boolean bold, @NotNull String text) {
/* 34 */     return class_2561.method_43470(text).method_27694(style -> style.method_36139(colour).method_10982(Boolean.valueOf(bold)).method_10978(Boolean.valueOf(false)));
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\item\armor\PokeArmorLore.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */