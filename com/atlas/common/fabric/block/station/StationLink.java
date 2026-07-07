/*    */ package com.atlas.common.fabric.block.station;
/*    */ 
/*    */ import com.cobblemon.mod.common.Cobblemon;
/*    */ import com.cobblemon.mod.common.api.storage.pc.PCStore;
/*    */ import java.util.UUID;
/*    */ import net.minecraft.class_2338;
/*    */ import net.minecraft.class_2960;
/*    */ import net.minecraft.class_5455;
/*    */ 
/*    */ public class StationLink
/*    */ {
/*    */   private final UUID linkId;
/*    */   private final UUID pcId;
/*    */   private final class_2960 dimension;
/*    */   private final class_2338 pos;
/*    */   private final StationPermissions permissions;
/*    */   
/*    */   public StationLink(UUID linkId, UUID pcId, class_2960 dimension, class_2338 pos, StationPermissions permissions) {
/* 19 */     this.linkId = linkId;
/* 20 */     this.pcId = pcId;
/* 21 */     this.dimension = dimension;
/* 22 */     this.pos = pos;
/* 23 */     this.permissions = permissions;
/*    */   }
/*    */   
/*    */   public PCStore getPC(class_5455 registryAccess) {
/* 27 */     return Cobblemon.INSTANCE.getStorage().getPC(this.pcId, registryAccess);
/*    */   }
/*    */   
/* 30 */   public UUID getLinkId() { return this.linkId; }
/* 31 */   public UUID getPcId() { return this.pcId; }
/* 32 */   public class_2960 getDimension() { return this.dimension; }
/* 33 */   public class_2338 getPos() { return this.pos; } public StationPermissions getPermissions() {
/* 34 */     return this.permissions;
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\block\station\StationLink.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */