/*    */ package com.atlas.common.fabric.block.station;
/*    */ 
/*    */ import com.cobblemon.mod.common.net.IntSize;
/*    */ import com.cobblemon.mod.common.util.NetExtensionsKt;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.class_2540;
/*    */ 
/*    */ public class StationPermissions {
/*    */   private final boolean canUnstationOthers;
/*    */   private final boolean canStation;
/*    */   private final int maxPokemon;
/*    */   
/*    */   public StationPermissions(boolean canUnstationOthers, boolean canStation, int maxPokemon) {
/* 14 */     this.canUnstationOthers = canUnstationOthers;
/* 15 */     this.canStation = canStation;
/* 16 */     this.maxPokemon = maxPokemon;
/*    */   }
/*    */   
/*    */   public static StationPermissions decode(class_2540 buffer) {
/* 20 */     return new StationPermissions(buffer
/* 21 */         .readBoolean(), buffer
/* 22 */         .readBoolean(), 
/* 23 */         NetExtensionsKt.readSizedInt((ByteBuf)buffer, IntSize.SHORT));
/*    */   }
/*    */ 
/*    */   
/*    */   public void encode(class_2540 buffer) {
/* 28 */     buffer.method_52964(this.canUnstationOthers);
/* 29 */     buffer.method_52964(this.canStation);
/* 30 */     NetExtensionsKt.writeSizedInt((ByteBuf)buffer, IntSize.SHORT, this.maxPokemon);
/*    */   }
/*    */   
/* 33 */   public boolean getCanUnstationOthers() { return this.canUnstationOthers; }
/* 34 */   public boolean getCanStation() { return this.canStation; } public int getMaxPokemon() {
/* 35 */     return this.maxPokemon;
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\block\station\StationPermissions.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */