/*     */ package com.atlas.common.fabric.safari.fishing.item;
/*     */ import com.atlas.common.fabric.safari.fishing.SafariFishingManager;
/*     */ import com.atlas.common.fabric.safari.fishing.SafariRodData;
/*     */ import com.atlas.common.fabric.safari.fishing.SafariUpgradeSlot;
/*     */ import com.atlas.common.fabric.safari.fishing.entity.AtlasSafariBobberEntity;
/*     */ import java.util.List;
/*     */ import net.minecraft.class_124;
/*     */ import net.minecraft.class_1268;
/*     */ import net.minecraft.class_1271;
/*     */ import net.minecraft.class_1297;
/*     */ import net.minecraft.class_1309;
/*     */ import net.minecraft.class_1536;
/*     */ import net.minecraft.class_1657;
/*     */ import net.minecraft.class_1792;
/*     */ import net.minecraft.class_1799;
/*     */ import net.minecraft.class_1890;
/*     */ import net.minecraft.class_1937;
/*     */ import net.minecraft.class_2561;
/*     */ import net.minecraft.class_3218;
/*     */ import net.minecraft.class_3222;
/*     */ import net.minecraft.class_3417;
/*     */ import net.minecraft.class_3419;
/*     */ import net.minecraft.class_3468;
/*     */ import net.minecraft.class_5712;
/*     */ import net.minecraft.class_6880;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public class SafariFishingRodItem extends class_1787 {
/*     */   public SafariFishingRodItem(class_1792.class_1793 settings) {
/*  30 */     super(settings);
/*     */   }
/*     */ 
/*     */   
/*     */   public class_1271<class_1799> method_7836(class_1937 world, class_1657 user, class_1268 hand) {
/*  35 */     class_1799 rodStack = user.method_5998(hand);
/*  36 */     class_1799 offhand = user.method_6079();
/*     */     
/*  38 */     class_1792 class_1792 = offhand.method_7909(); if (class_1792 instanceof SafariUpgradeItem) { SafariUpgradeItem upgradeItem = (SafariUpgradeItem)class_1792; if (hand == class_1268.field_5808) {
/*  39 */         if (!world.field_9236) {
/*  40 */           boolean applied = SafariRodData.socketUpgrade(rodStack, upgradeItem.getSlot(), upgradeItem.getUpgradeKey());
/*  41 */           if (applied) {
/*  42 */             if (!user.method_7337()) {
/*  43 */               offhand.method_7934(1);
/*     */             }
/*  45 */             user.method_7353((class_2561)class_2561.method_43470("Socketed " + SafariRodData.prettyName(upgradeItem.getUpgradeKey()) + " into your rod.")
/*  46 */                 .method_27692(class_124.field_1075), true);
/*     */           } else {
/*  48 */             user.method_7353((class_2561)class_2561.method_43470("That upgrade is already fitted.")
/*  49 */                 .method_27692(class_124.field_1080), true);
/*     */           } 
/*     */         } 
/*  52 */         return class_1271.method_29237(rodStack, world.method_8608());
/*     */       }  }
/*     */     
/*  55 */     class_1536 class_1536 = user.field_7513; if (class_1536 instanceof AtlasSafariBobberEntity) { AtlasSafariBobberEntity bobber = (AtlasSafariBobberEntity)class_1536;
/*  56 */       if (user instanceof class_3222) { class_3222 serverPlayer = (class_3222)user;
/*  57 */         if (SafariFishingManager.isHooked((class_1657)serverPlayer)) {
/*  58 */           return class_1271.method_29237(rodStack, world.method_8608());
/*     */         }
/*     */         
/*  61 */         if (SafariFishingManager.hasPendingCast((class_1657)serverPlayer) || SafariFishingManager.hasActiveSession((class_1657)serverPlayer)) {
/*  62 */           SafariFishingManager.cancel(serverPlayer, user.method_5715() ? "You pull the line back in." : null);
/*  63 */         } else if (!world.method_8608()) {
/*  64 */           int damage = bobber.method_6957(rodStack);
/*  65 */           rodStack.method_7970(damage, (class_1309)user, class_1309.method_56079(hand));
/*     */         }  }
/*     */ 
/*     */       
/*  69 */       world.method_43128(null, user.method_23317(), user.method_23318(), user.method_23321(), class_3417.field_15093, class_3419.field_15254, 1.0F, 0.4F / (world.method_8409().method_43057() * 0.4F + 0.8F));
/*  70 */       user.method_32876((class_6880)class_5712.field_28146);
/*  71 */       return class_1271.method_29237(rodStack, world.method_8608()); }
/*     */ 
/*     */     
/*  74 */     world.method_43128(null, user.method_23317(), user.method_23318(), user.method_23321(), class_3417.field_14596, class_3419.field_15254, 0.5F, 0.4F / (world.method_8409().method_43057() * 0.4F + 0.8F));
/*     */     
/*  76 */     if (world instanceof class_3218) { class_3218 serverWorld = (class_3218)world; if (user instanceof class_3222) { class_3222 serverPlayer = (class_3222)user;
/*  77 */         int waitReductionTicks = (int)(class_1890.method_60158(serverWorld, rodStack, (class_1297)user) * 20.0F);
/*  78 */         int luckBonus = class_1890.method_8223(serverWorld, rodStack, (class_1297)user);
/*  79 */         AtlasSafariBobberEntity bobber = new AtlasSafariBobberEntity(user, world, luckBonus, waitReductionTicks);
/*  80 */         world.method_8649((class_1297)bobber);
/*  81 */         SafariFishingManager.queueCast(serverPlayer, rodStack, bobber); }
/*     */        }
/*     */     
/*  84 */     user.method_7259(class_3468.field_15372.method_14956(this));
/*  85 */     user.method_32876((class_6880)class_5712.field_28145);
/*  86 */     return class_1271.method_29237(rodStack, world.method_8608());
/*     */   }
/*     */ 
/*     */   
/*     */   public void method_7851(class_1799 stack, class_1792.class_9635 context, List<class_2561> tooltip, class_1836 type) {
/*  91 */     tooltip.add(class_2561.method_43470("Socket bait, line, and reel upgrades with the item in your offhand.")
/*  92 */         .method_27692(class_124.field_1080));
/*  93 */     String bait = SafariRodData.getUpgrade(stack, SafariUpgradeSlot.BAIT);
/*  94 */     String line = SafariRodData.getUpgrade(stack, SafariUpgradeSlot.LINE);
/*  95 */     String reel = SafariRodData.getUpgrade(stack, SafariUpgradeSlot.REEL);
/*  96 */     tooltip.add(slotLine("Bait", bait));
/*  97 */     tooltip.add(slotLine("Line", line));
/*  98 */     tooltip.add(slotLine("Reel", reel));
/*     */   }
/*     */   
/*     */   private static class_2561 slotLine(@NotNull String slot, String upgrade) {
/* 102 */     return (class_2561)class_2561.method_43470(slot + ": " + slot)
/* 103 */       .method_27692((upgrade == null) ? class_124.field_1063 : class_124.field_1075);
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\safari\fishing\item\SafariFishingRodItem.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */