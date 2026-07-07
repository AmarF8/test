/*    */ package com.atlas.common.fabric.item;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.class_124;
/*    */ import net.minecraft.class_1792;
/*    */ import net.minecraft.class_1799;
/*    */ import net.minecraft.class_1836;
/*    */ import net.minecraft.class_2561;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public final class MegaBraceletItem
/*    */   extends class_1792 {
/*    */   public MegaBraceletItem(@NotNull class_1792.class_1793 settings) {
/* 14 */     super(settings);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void method_7851(@NotNull class_1799 stack, @NotNull class_1792.class_9635 context, @NotNull List<class_2561> tooltip, @NotNull class_1836 type) {
/* 22 */     tooltip.add(class_2561.method_43470("A Mega Keystone embedded in a stone bracelet").method_27692(class_124.field_1080));
/* 23 */     tooltip.add(class_2561.method_43470("Hold in your offhand to enable ")
/* 24 */         .method_27692(class_124.field_1080)
/* 25 */         .method_10852((class_2561)class_2561.method_43470("Mega evolution").method_27692(class_124.field_1054))
/* 26 */         .method_10852((class_2561)class_2561.method_43470(".").method_27692(class_124.field_1080)));
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\item\MegaBraceletItem.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */