/*    */ package com.atlas.common.fabric.block.station.render;
/*    */ 
/*    */ import net.minecraft.class_243;
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
/*    */ class WanderState
/*    */ {
/*    */   class_243 currentPos;
/*    */   class_243 targetPos;
/*    */   float currentYaw;
/* 37 */   int idleTimer = 0;
/*    */   
/*    */   WanderState(class_243 startPos) {
/* 40 */     this.currentPos = startPos;
/* 41 */     this.targetPos = startPos;
/*    */   }
/*    */   
/*    */   boolean isMoving() {
/* 45 */     return (this.currentPos.method_1022(this.targetPos) > 0.15D);
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\block\station\render\StationBlockEntityRenderer$WanderState.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */