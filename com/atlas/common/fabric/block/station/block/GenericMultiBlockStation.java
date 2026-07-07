/*    */ package com.atlas.common.fabric.block.station.block;
/*    */ 
/*    */ import com.mojang.serialization.MapCodec;
/*    */ import java.util.function.Function;
/*    */ import net.minecraft.class_2237;
/*    */ import net.minecraft.class_4970;
/*    */ 
/*    */ public class GenericMultiBlockStation extends MultiBlockStationBlock {
/*  9 */   public static final MapCodec<GenericMultiBlockStation> CODEC = method_54094(GenericMultiBlockStation::new);
/*    */   
/*    */   public GenericMultiBlockStation() {
/* 12 */     super(com.atlas.common.fabric.block.station.entity.GenericStationBlockEntity::new);
/*    */   }
/*    */   
/*    */   public GenericMultiBlockStation(class_4970.class_2251 settings) {
/* 16 */     super(com.atlas.common.fabric.block.station.entity.GenericStationBlockEntity::new);
/*    */   }
/*    */ 
/*    */   
/*    */   protected MapCodec<? extends class_2237> method_53969() {
/* 21 */     return (MapCodec)CODEC;
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\block\station\block\GenericMultiBlockStation.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */