/*     */ package com.atlas.common.fabric.block.station;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.class_124;
/*     */ import net.minecraft.class_1747;
/*     */ import net.minecraft.class_1792;
/*     */ import net.minecraft.class_1799;
/*     */ import net.minecraft.class_2487;
/*     */ import net.minecraft.class_2561;
/*     */ import net.minecraft.class_2583;
/*     */ import net.minecraft.class_5250;
/*     */ import net.minecraft.class_9279;
/*     */ import net.minecraft.class_9290;
/*     */ import net.minecraft.class_9334;
/*     */ import org.jetbrains.annotations.NotNull;
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
/*     */ public final class StationItemBranding
/*     */ {
/*     */   public static final String UNTRADABLE_NBT_KEY = "Untradable";
/*     */   public static final String UNTRADABLE_LORE_TEXT = "Untradable";
/*     */   public static final String SELLBACK_LORE_TEXT = "Sell back at a Stations Trader for 70% of the value";
/*     */   
/*     */   public static boolean isStation(@NotNull class_1799 stack) {
/*     */     class_1747 blockItem;
/*  53 */     if (stack.method_7960()) return false; 
/*  54 */     class_1792 class_1792 = stack.method_7909(); if (class_1792 instanceof class_1747) { blockItem = (class_1747)class_1792; } else { return false; }
/*  55 */      return blockItem.method_7711() instanceof com.atlas.common.fabric.block.station.block.StationBlock;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean hasUntradableFlag(@NotNull class_1799 stack) {
/*  60 */     class_9279 component = (class_9279)stack.method_57825(class_9334.field_49628, class_9279.field_49302);
/*  61 */     return (component.method_57450("Untradable") && component
/*  62 */       .method_57461().method_10577("Untradable"));
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
/*     */   @NotNull
/*     */   public static class_1799 apply(@NotNull class_1799 stack) {
/*  75 */     class_9279.method_57452(class_9334.field_49628, stack, nbt -> nbt.method_10569("Untradable", 1));
/*     */ 
/*     */ 
/*     */     
/*  79 */     class_9290 current = (class_9290)stack.method_57824(class_9334.field_49632);
/*  80 */     List<class_2561> lines = (current != null) ? new ArrayList<>(current.comp_2400()) : new ArrayList<>();
/*  81 */     boolean hasUntradable = false;
/*  82 */     boolean hasSellback = false;
/*  83 */     for (class_2561 line : lines) {
/*  84 */       String text = line.getString();
/*  85 */       if ("Untradable".equals(text)) { hasUntradable = true; continue; }
/*  86 */        if ("Sell back at a Stations Trader for 70% of the value".equals(text)) hasSellback = true; 
/*     */     } 
/*  88 */     if (!hasUntradable) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  93 */       class_5250 untradableLine = class_2561.method_43470("Untradable").method_27692(class_124.field_1061).method_27694(style -> style.method_10978(Boolean.valueOf(false)));
/*  94 */       lines.add(untradableLine);
/*     */     } 
/*  96 */     if (!hasSellback) {
/*     */ 
/*     */       
/*  99 */       class_5250 sellbackLine = class_2561.method_43470("Sell back at a Stations Trader for 70% of the value").method_27692(class_124.field_1080).method_27694(style -> style.method_10978(Boolean.valueOf(false)));
/* 100 */       lines.add(sellbackLine);
/*     */     } 
/* 102 */     if (!hasUntradable || !hasSellback) {
/* 103 */       stack.method_57379(class_9334.field_49632, new class_9290(lines));
/*     */     }
/* 105 */     return stack;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean applyIfStationMissing(@NotNull class_1799 stack) {
/* 115 */     if (!isStation(stack)) return false; 
/* 116 */     if (hasUntradableFlag(stack)) return false; 
/* 117 */     apply(stack);
/* 118 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\block\station\StationItemBranding.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */