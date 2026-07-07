/*    */ package com.atlas.common.fabric.gocatch;
/*    */ 
/*    */ import com.cobblemon.mod.common.pokemon.Pokemon;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class GoCatchRingTiming
/*    */ {
/*    */   public static double calculateRingSize(Pokemon pokemon, String tier, GoCatchConfig config, long currentTimeMillis) {
/* 21 */     double maxSize = config.ringMaxSize;
/* 22 */     double minSize = config.ringMinSize;
/* 23 */     if (maxSize <= minSize) return minSize;
/*    */     
/* 25 */     GoCatchConfig.ResolvedConfig resolved = config.resolve(tier);
/* 26 */     double effectiveSpeed = calculateEffectiveSpeed(pokemon, config, resolved);
/* 27 */     if (effectiveSpeed <= 0.0D) return maxSize;
/*    */     
/* 29 */     long shrinkDurationMillis = (long)(2000.0D / effectiveSpeed);
/* 30 */     if (shrinkDurationMillis <= 0L) return maxSize;
/*    */     
/* 32 */     long cycleIndex = currentTimeMillis / shrinkDurationMillis;
/* 33 */     double phase = (currentTimeMillis % shrinkDurationMillis) / shrinkDurationMillis;
/*    */     
/* 35 */     if (config.unpredictableRing) {
/*    */ 
/*    */       
/* 38 */       long seed = mix(pokemon.getUuid().getMostSignificantBits() ^ pokemon
/* 39 */           .getUuid().getLeastSignificantBits() ^ cycleIndex);
/*    */       
/* 41 */       if ((seed & 0x1L) == 0L)
/*    */       {
/* 43 */         phase = 1.0D - phase;
/*    */       }
/*    */     } 
/*    */     
/* 47 */     return maxSize - (maxSize - minSize) * phase;
/*    */   }
/*    */   
/*    */   private static long mix(long v) {
/* 51 */     v = (v ^ v >>> 30L) * -4658895280553007689L;
/* 52 */     v = (v ^ v >>> 27L) * -7723592293110705685L;
/* 53 */     return v ^ v >>> 31L;
/*    */   }
/*    */   
/*    */   private static double calculateEffectiveSpeed(Pokemon pokemon, GoCatchConfig config, GoCatchConfig.ResolvedConfig resolved) {
/* 57 */     int level = pokemon.getLevel();
/* 58 */     double baseSpeed = Math.max(0.01D, resolved.baseShrinkSpeed());
/* 59 */     double levelMultiplier = 1.0D + level * config.levelSpeedScaling;
/* 60 */     double speedMultiplier = Math.max(0.01D, resolved.speedMultiplier());
/* 61 */     return baseSpeed * Math.max(0.1D, levelMultiplier) * speedMultiplier;
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\gocatch\GoCatchRingTiming.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */