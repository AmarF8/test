/*    */ package com.atlas.common.fabric.block.crate.block;
/*    */ import com.atlas.common.fabric.block.crate.CrateBlock;
/*    */ import com.atlas.common.fabric.block.crate.entity.MonthlyCrateBlockEntity;
/*    */ import com.mojang.serialization.MapCodec;
/*    */ import java.util.function.Function;
/*    */ import net.minecraft.class_2237;
/*    */ import net.minecraft.class_4970;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class MonthlyCrateBlock extends CrateBlock {
/* 11 */   public static final MapCodec<MonthlyCrateBlock> CODEC = method_54094(MonthlyCrateBlock::new);
/*    */   
/*    */   public MonthlyCrateBlock() {
/* 14 */     this(class_4970.class_2251.method_9637().method_9634());
/*    */   }
/*    */   
/*    */   public MonthlyCrateBlock(@NotNull class_4970.class_2251 settings) {
/* 18 */     super(settings, MonthlyCrateBlockEntity.class);
/*    */   }
/*    */ 
/*    */   
/*    */   protected MapCodec<? extends class_2237> method_53969() {
/* 23 */     return (MapCodec)CODEC;
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\block\crate\block\MonthlyCrateBlock.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */