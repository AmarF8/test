/*    */ package com.atlas.common.fabric.cosmetic;
/*    */ 
/*    */ import com.cobblemon.mod.common.api.scheduling.SchedulingTracker;
/*    */ import com.cobblemon.mod.common.client.render.models.blockbench.PosableState;
/*    */ import net.minecraft.class_1297;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ public class SimplePosableState extends PosableState {
/*    */   @NotNull
/* 11 */   private final SchedulingTracker schedulingTracker = new SchedulingTracker();
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public SchedulingTracker getSchedulingTracker() {
/* 16 */     return this.schedulingTracker;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public class_1297 getEntity() {
/* 21 */     return null;
/*    */   }
/*    */   
/*    */   public void updatePartialTicks(float partialTicks) {
/* 25 */     setCurrentPartialTicks(partialTicks);
/* 26 */     getSchedulingTracker().update(0.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\cosmetic\SimplePosableState.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */