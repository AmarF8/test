/*     */ package com.atlas.common.fabric.safari.fishing;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.class_124;
/*     */ import net.minecraft.class_1799;
/*     */ import net.minecraft.class_2487;
/*     */ import net.minecraft.class_2520;
/*     */ import net.minecraft.class_2561;
/*     */ import net.minecraft.class_2583;
/*     */ import net.minecraft.class_5250;
/*     */ import net.minecraft.class_9279;
/*     */ import net.minecraft.class_9290;
/*     */ import net.minecraft.class_9334;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ public final class SafariRodData
/*     */ {
/*     */   private static final String ROOT_KEY = "SafariFishing";
/*     */   
/*     */   @Nullable
/*     */   public static String getUpgrade(@NotNull class_1799 stack, @NotNull SafariUpgradeSlot slot) {
/*  25 */     class_9279 component = (class_9279)stack.method_57825(class_9334.field_49628, class_9279.field_49302);
/*  26 */     if (!component.method_57450("SafariFishing")) return null;
/*     */     
/*  28 */     class_2487 root = component.method_57461().method_10562("SafariFishing");
/*  29 */     String value = root.method_10558(slot.getNbtKey());
/*  30 */     return value.isBlank() ? null : value;
/*     */   }
/*     */   
/*     */   public static boolean socketUpgrade(@NotNull class_1799 stack, @NotNull SafariUpgradeSlot slot, @NotNull String upgradeKey) {
/*  34 */     String existing = getUpgrade(stack, slot);
/*  35 */     if (upgradeKey.equals(existing)) return false;
/*     */     
/*  37 */     class_9279.method_57452(class_9334.field_49628, stack, nbt -> {
/*     */           class_2487 root = nbt.method_10545("SafariFishing") ? nbt.method_10562("SafariFishing") : new class_2487();
/*     */           root.method_10582(slot.getNbtKey(), upgradeKey);
/*     */           nbt.method_10566("SafariFishing", (class_2520)root);
/*     */         });
/*  42 */     refreshLore(stack);
/*  43 */     return true;
/*     */   }
/*     */   
/*     */   public static void refreshLore(@NotNull class_1799 stack) {
/*  47 */     List<class_2561> lines = new ArrayList<>();
/*  48 */     appendSlotLine(lines, SafariUpgradeSlot.BAIT, getUpgrade(stack, SafariUpgradeSlot.BAIT));
/*  49 */     appendSlotLine(lines, SafariUpgradeSlot.LINE, getUpgrade(stack, SafariUpgradeSlot.LINE));
/*  50 */     appendSlotLine(lines, SafariUpgradeSlot.REEL, getUpgrade(stack, SafariUpgradeSlot.REEL));
/*  51 */     stack.method_57379(class_9334.field_49632, new class_9290(lines));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void appendSlotLine(@NotNull List<class_2561> lines, @NotNull SafariUpgradeSlot slot, @Nullable String upgradeKey) {
/*  57 */     class_5250 line = class_2561.method_43470(slot.getDisplayName() + ": ").method_27692(class_124.field_1062).method_27694(style -> style.method_10978(Boolean.valueOf(false)));
/*  58 */     String value = (upgradeKey == null) ? "Empty" : prettyName(upgradeKey);
/*  59 */     class_124 valueColor = (upgradeKey == null) ? class_124.field_1080 : class_124.field_1075;
/*  60 */     line.method_10852((class_2561)class_2561.method_43470(value).method_27692(valueColor).method_27694(style -> style.method_10978(Boolean.valueOf(false))));
/*  61 */     lines.add(line);
/*     */   }
/*     */   
/*     */   public static RodLoadout getLoadout(@NotNull class_1799 stack) {
/*  65 */     String bait = getUpgrade(stack, SafariUpgradeSlot.BAIT);
/*  66 */     String line = getUpgrade(stack, SafariUpgradeSlot.LINE);
/*  67 */     String reel = getUpgrade(stack, SafariUpgradeSlot.REEL);
/*     */     
/*  69 */     double biteSpeed = 1.0D;
/*  70 */     double rareBonus = 1.0D;
/*  71 */     double deepBonus = 1.0D;
/*  72 */     double safeZoneBonus = 0.0D;
/*  73 */     double tensionRelief = 0.0D;
/*  74 */     double reelPower = 1.0D;
/*  75 */     double pumpPower = 1.0D;
/*     */     
/*  77 */     if ("kelp_bait".equals(bait)) {
/*  78 */       biteSpeed += 0.25D;
/*  79 */       rareBonus *= 0.92D;
/*  80 */     } else if ("luminous_bait".equals(bait)) {
/*  81 */       biteSpeed += 0.1D;
/*  82 */       rareBonus *= 1.18D;
/*  83 */       deepBonus *= 1.35D;
/*     */     } 
/*     */     
/*  86 */     if ("sturdy_line".equals(line)) {
/*  87 */       tensionRelief += 0.025D;
/*  88 */     } else if ("silk_line".equals(line)) {
/*  89 */       safeZoneBonus += 0.08D;
/*     */     } 
/*     */     
/*  92 */     if ("iron_reel".equals(reel)) {
/*  93 */       reelPower += 0.18D;
/*  94 */     } else if ("precision_reel".equals(reel)) {
/*  95 */       safeZoneBonus += 0.05D;
/*  96 */       pumpPower += 0.12D;
/*     */     } 
/*     */     
/*  99 */     return new RodLoadout(bait, line, reel, biteSpeed, rareBonus, deepBonus, safeZoneBonus, tensionRelief, reelPower, pumpPower);
/*     */   }
/*     */   
/*     */   public static String prettyName(@NotNull String raw) {
/* 103 */     String[] pieces = raw.split("_");
/* 104 */     StringBuilder builder = new StringBuilder();
/* 105 */     for (String piece : pieces) {
/* 106 */       if (!piece.isEmpty()) {
/* 107 */         if (!builder.isEmpty()) builder.append(' '); 
/* 108 */         builder.append(Character.toUpperCase(piece.charAt(0)));
/* 109 */         if (piece.length() > 1) builder.append(piece.substring(1)); 
/*     */       } 
/* 111 */     }  return builder.toString(); } public static final class RodLoadout extends Record { @Nullable
/*     */     private final String bait; @Nullable
/*     */     private final String line; @Nullable
/* 114 */     private final String reel; private final double biteSpeed; private final double rareBonus; private final double deepBonus; private final double safeZoneBonus; private final double tensionRelief; private final double reelPower; private final double pumpPower; public RodLoadout(@Nullable String bait, @Nullable String line, @Nullable String reel, double biteSpeed, double rareBonus, double deepBonus, double safeZoneBonus, double tensionRelief, double reelPower, double pumpPower) { this.bait = bait; this.line = line; this.reel = reel; this.biteSpeed = biteSpeed; this.rareBonus = rareBonus; this.deepBonus = deepBonus; this.safeZoneBonus = safeZoneBonus; this.tensionRelief = tensionRelief; this.reelPower = reelPower; this.pumpPower = pumpPower; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/safari/fishing/SafariRodData$RodLoadout;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #114	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/* 114 */       //   0	7	0	this	Lcom/atlas/common/fabric/safari/fishing/SafariRodData$RodLoadout; } @Nullable public String bait() { return this.bait; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/safari/fishing/SafariRodData$RodLoadout;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #114	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/safari/fishing/SafariRodData$RodLoadout; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/safari/fishing/SafariRodData$RodLoadout;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #114	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lcom/atlas/common/fabric/safari/fishing/SafariRodData$RodLoadout;
/* 114 */       //   0	8	1	o	Ljava/lang/Object; } @Nullable public String line() { return this.line; } @Nullable public String reel() { return this.reel; } public double biteSpeed() { return this.biteSpeed; } public double rareBonus() { return this.rareBonus; } public double deepBonus() { return this.deepBonus; } public double safeZoneBonus() { return this.safeZoneBonus; } public double tensionRelief() { return this.tensionRelief; } public double reelPower() { return this.reelPower; } public double pumpPower() { return this.pumpPower; }
/*     */      }
/*     */ 
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\safari\fishing\SafariRodData.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */