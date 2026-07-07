/*    */ package com.atlas.common.fabric.block.animated;
/*    */ 
/*    */ import com.atlas.common.fabric.block.animated.entity.AnimatedBlockEntity;
/*    */ import java.util.Objects;
/*    */ import net.minecraft.class_2237;
/*    */ import net.minecraft.class_2338;
/*    */ import net.minecraft.class_2464;
/*    */ import net.minecraft.class_2586;
/*    */ import net.minecraft.class_2591;
/*    */ import net.minecraft.class_2680;
/*    */ import net.minecraft.class_4970;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
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
/*    */ public abstract class AnimatedBlock
/*    */   extends class_2237
/*    */ {
/*    */   private final class_2591<? extends AnimatedBlockEntity> blockEntityType;
/*    */   
/*    */   public AnimatedBlock(@NotNull class_4970.class_2251 settings, @NotNull class_2591<? extends AnimatedBlockEntity> blockEntityType) {
/* 32 */     super(settings);
/* 33 */     this.blockEntityType = Objects.<class_2591<? extends AnimatedBlockEntity>>requireNonNull(blockEntityType);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public class_2586 method_10123(@NotNull class_2338 pos, @NotNull class_2680 state) {
/* 42 */     return (class_2586)new AnimatedBlockEntity(this.blockEntityType, pos, state);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public class_2464 method_9604(@NotNull class_2680 state) {
/* 50 */     return class_2464.field_11456;
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\block\animated\AnimatedBlock.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */