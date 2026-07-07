/*    */ package com.atlas.common.fabric.block.station.block;
/*    */ 
/*    */ import com.mojang.serialization.MapCodec;
/*    */ import java.util.function.Function;
/*    */ import net.minecraft.class_2237;
/*    */ import net.minecraft.class_4970;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class GenericStationBlock extends StationBlock {
/* 10 */   public static final MapCodec<GenericStationBlock> CODEC = method_54094(GenericStationBlock::new);
/*    */   
/*    */   public GenericStationBlock() {
/* 13 */     this(class_4970.class_2251.method_9637().method_9634());
/*    */   }
/*    */   
/*    */   public GenericStationBlock(@NotNull class_4970.class_2251 settings) {
/* 17 */     super(com.atlas.common.fabric.block.station.entity.GenericStationBlockEntity::new);
/*    */   }
/*    */ 
/*    */   
/*    */   protected MapCodec<? extends class_2237> method_53969() {
/* 22 */     return (MapCodec)CODEC;
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\block\station\block\GenericStationBlock.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */