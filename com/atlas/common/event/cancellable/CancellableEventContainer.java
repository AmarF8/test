/*    */ package com.atlas.common.event.cancellable;
/*    */ 
/*    */ import com.atlas.common.event.AtlasEvent;
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
/*    */ public abstract class CancellableEventContainer
/*    */   implements AtlasEvent, CancellableEvent
/*    */ {
/*    */   protected boolean cancelled;
/*    */   
/*    */   public boolean isCancelled() {
/* 21 */     return this.cancelled;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancelled) {
/* 29 */     this.cancelled = cancelled;
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\event\cancellable\CancellableEventContainer.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */