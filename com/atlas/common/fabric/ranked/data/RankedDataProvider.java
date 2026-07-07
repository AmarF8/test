/*    */ package com.atlas.common.fabric.ranked.data;
/*    */ 
/*    */ import java.util.UUID;
/*    */ import java.util.function.Consumer;
/*    */ import java.util.function.Function;
/*    */ import net.minecraft.class_3222;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class RankedDataProvider
/*    */ {
/*    */   private static Consumer<class_3222> sendDataToClientProvider;
/*    */   private static Consumer<class_3222> sendOpponentsToClientProvider;
/*    */   private static Consumer<class_3222> sendShopItemsToClientProvider;
/*    */   private static Function<UUID, Integer> playerEloProvider;
/*    */   
/*    */   public static void setSendDataToClientProvider(Consumer<class_3222> provider) {
/* 22 */     sendDataToClientProvider = provider;
/*    */   }
/*    */   
/*    */   public static void setSendOpponentsToClientProvider(Consumer<class_3222> provider) {
/* 26 */     sendOpponentsToClientProvider = provider;
/*    */   }
/*    */   
/*    */   public static void setSendShopItemsToClientProvider(Consumer<class_3222> provider) {
/* 30 */     sendShopItemsToClientProvider = provider;
/*    */   }
/*    */   
/*    */   public static void setPlayerEloProvider(Function<UUID, Integer> provider) {
/* 34 */     playerEloProvider = provider;
/*    */   }
/*    */   
/*    */   public static void sendDataToClient(class_3222 player) {
/* 38 */     if (sendDataToClientProvider != null) sendDataToClientProvider.accept(player); 
/*    */   }
/*    */   
/*    */   public static void sendOpponentsToClient(class_3222 player) {
/* 42 */     if (sendOpponentsToClientProvider != null) sendOpponentsToClientProvider.accept(player); 
/*    */   }
/*    */   
/*    */   public static void sendShopItemsToClient(class_3222 player) {
/* 46 */     if (sendShopItemsToClientProvider != null) sendShopItemsToClientProvider.accept(player); 
/*    */   }
/*    */   
/*    */   public static int getPlayerElo(UUID playerUUID) {
/* 50 */     if (playerEloProvider != null) return ((Integer)playerEloProvider.apply(playerUUID)).intValue(); 
/* 51 */     return EloCalculator.getStartingElo();
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\ranked\data\RankedDataProvider.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */