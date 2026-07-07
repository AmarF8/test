/*     */ package com.atlas.common.fabric.cosmetic;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class EquippedCosmetics
/*     */ {
/*     */   @NotNull
/*     */   private final UUID player;
/*     */   
/*     */   public EquippedCosmetics(@NotNull UUID player) {
/*  22 */     this.player = Objects.<UUID>requireNonNull(player, "player UUID cannot be null");
/*  23 */     this.cosmetics = new LinkedHashMap<>();
/*  24 */     this.disabledArmorSlots = new LinkedHashSet<>();
/*     */   } @NotNull
/*     */   private final Map<CosmeticCategory, EquippedCosmetic> cosmetics; @NotNull
/*     */   private final Set<ArmorSlot> disabledArmorSlots; @NotNull
/*     */   public UUID getPlayer() {
/*  29 */     return this.player;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void equip(@NotNull Cosmetic cosmetic, @Nullable CosmeticVariant variant) {
/*  39 */     Objects.requireNonNull(cosmetic, "cosmetic cannot be null");
/*     */     
/*  41 */     this.cosmetics.put(cosmetic.getCategory(), new EquippedCosmetic(cosmetic, variant));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void equip(@NotNull Cosmetic cosmetic) {
/*  49 */     equip(cosmetic, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void unequip(@NotNull Cosmetic cosmetic) {
/*  58 */     Objects.requireNonNull(cosmetic, "cosmetic cannot be null");
/*  59 */     this.cosmetics.remove(cosmetic.getCategory());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public EquippedCosmetic get(@NotNull CosmeticCategory category) {
/*  70 */     Objects.requireNonNull(category, "category cannot be null");
/*  71 */     return this.cosmetics.get(category);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEquipped(@NotNull Cosmetic cosmetic) {
/*  81 */     Objects.requireNonNull(cosmetic, "cosmetic cannot be null");
/*  82 */     EquippedCosmetic equipped = this.cosmetics.get(cosmetic.getCategory());
/*  83 */     return (equipped != null && Objects.equals(equipped.getCosmetic(), cosmetic));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEquipped(@NotNull Cosmetic cosmetic, @Nullable CosmeticVariant variant) {
/*  94 */     Objects.requireNonNull(cosmetic, "cosmetic cannot be null");
/*  95 */     EquippedCosmetic equipped = this.cosmetics.get(cosmetic.getCategory());
/*     */     
/*  97 */     if (equipped == null) {
/*  98 */       return false;
/*     */     }
/*     */     
/* 101 */     return (Objects.equals(equipped.getCosmetic(), cosmetic) && 
/* 102 */       Objects.equals(equipped.getVariant(), variant));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDisabled(@NotNull ArmorSlot slot) {
/* 112 */     Objects.requireNonNull(slot, "slot cannot be null");
/* 113 */     return this.disabledArmorSlots.contains(slot);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void disableSlot(@NotNull ArmorSlot slot) {
/* 122 */     Objects.requireNonNull(slot, "slot cannot be null");
/* 123 */     this.disabledArmorSlots.add(slot);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void enableSlot(@NotNull ArmorSlot slot) {
/* 132 */     Objects.requireNonNull(slot, "slot cannot be null");
/* 133 */     this.disabledArmorSlots.remove(slot);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void toggleSlot(@NotNull ArmorSlot slot) {
/* 142 */     Objects.requireNonNull(slot, "slot cannot be null");
/* 143 */     if (isDisabled(slot)) {
/* 144 */       enableSlot(slot);
/*     */     } else {
/* 146 */       disableSlot(slot);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public List<EquippedCosmetic> allEquippedCosmetics() {
/* 157 */     return new ArrayList<>(this.cosmetics.values());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public List<ArmorSlot> allDisabledSlots() {
/* 167 */     return new ArrayList<>(this.disabledArmorSlots);
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\cosmetic\EquippedCosmetics.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */