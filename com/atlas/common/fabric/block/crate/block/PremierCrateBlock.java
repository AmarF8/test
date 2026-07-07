/*    */ package com.atlas.common.fabric.block.crate.block;
/*    */ import com.atlas.common.fabric.block.crate.CrateBlock;
/*    */ import com.atlas.common.fabric.block.crate.entity.PremierCrateBlockEntity;
/*    */ import com.mojang.serialization.MapCodec;
/*    */ import java.util.function.Function;
/*    */ import net.minecraft.class_2237;
/*    */ import net.minecraft.class_4970;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class PremierCrateBlock extends CrateBlock {
/* 11 */   public static final MapCodec<PremierCrateBlock> CODEC = method_54094(PremierCrateBlock::new);
/*    */   
/*    */   public PremierCrateBlock() {
/* 14 */     this(class_4970.class_2251.method_9637().method_9634());
/*    */   }
/*    */   
/*    */   public PremierCrateBlock(@NotNull class_4970.class_2251 settings) {
/* 18 */     super(settings, PremierCrateBlockEntity.class);
/*    */   }
/*    */ 
/*    */   
/*    */   protected MapCodec<? extends class_2237> method_53969() {
/* 23 */     return (MapCodec)CODEC;
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\block\crate\block\PremierCrateBlock.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */