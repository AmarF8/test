/*    */ package net.kyori.adventure.util;
/*    */ 
/*    */ import java.time.Duration;
/*    */ import org.jetbrains.annotations.NotNull;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface Ticks
/*    */ {
/*    */   public static final int TICKS_PER_SECOND = 20;
/*    */   public static final long SINGLE_TICK_DURATION_MS = 50L;
/*    */   
/*    */   @NotNull
/*    */   static Duration duration(long ticks) {
/* 57 */     return Duration.ofMillis(ticks * 50L);
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\net\kyori\adventur\\util\Ticks.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */