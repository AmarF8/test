/*    */ package com.atlas.common.fabric.block.station;
/*    */ 
/*    */ import com.atlas.common.fabric.block.station.entity.StationBlockEntity;
/*    */ import com.cobblemon.mod.common.api.PrioritizedList;
/*    */ import net.minecraft.class_3222;
/*    */ 
/*    */ public class StationPermissionControllers
/*    */ {
/*  9 */   public static final PrioritizedList<StationPermissionController> controllers = new PrioritizedList();
/*    */   
/*    */   public static StationPermissions permit(class_3222 player, StationBlockEntity stationBlockEntity) {
/* 12 */     for (StationPermissionController controller : controllers) {
/* 13 */       StationPermissions permissions = controller.permit(player, stationBlockEntity);
/* 14 */       if (permissions != null) {
/* 15 */         return permissions;
/*    */       }
/*    */     } 
/*    */ 
/*    */     
/* 20 */     return new StationPermissions(true, true, stationBlockEntity
/*    */ 
/*    */         
/* 23 */         .getMaxTethered());
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\block\station\StationPermissionControllers.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */