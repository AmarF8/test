/*    */ package com.atlas.common.fabric.cosmetic;
/*    */ 
/*    */ import java.util.UUID;
/*    */ import java.util.concurrent.ConcurrentHashMap;
/*    */ import java.util.function.Function;
/*    */ import net.minecraft.class_2960;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class EquippedCosmeticsRepository
/*    */ {
/*    */   @NotNull
/* 17 */   public static final EquippedCosmeticsRepository INSTANCE = new EquippedCosmeticsRepository();
/*    */   
/*    */   @NotNull
/* 20 */   public static final ConcurrentHashMap<UUID, class_2960> cloakCache = new ConcurrentHashMap<>();
/*    */   
/*    */   @NotNull
/* 23 */   private static final ConcurrentHashMap<UUID, EquippedCosmetics> playerCosmetics = new ConcurrentHashMap<>();
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public ConcurrentHashMap<UUID, EquippedCosmetics> getPlayerCosmetics() {
/* 30 */     return playerCosmetics;
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
/*    */   @Nullable
/*    */   public static EquippedCosmetic getCosmetic(@NotNull UUID uuid, @NotNull CosmeticCategory category) {
/* 43 */     EquippedCosmetics equipped = playerCosmetics.get(uuid);
/* 44 */     return (equipped != null) ? equipped.get(category) : null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public EquippedCosmetics getOrCreate(@NotNull UUID uuid) {
/* 56 */     return playerCosmetics.computeIfAbsent(uuid, EquippedCosmetics::new);
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\cosmetic\EquippedCosmeticsRepository.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */