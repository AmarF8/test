/*    */ package com.atlas.common.fabric.block.crate;
/*    */ 
/*    */ import com.atlas.common.fabric.block.animated.AnimatedBlockWithDirection;
/*    */ import java.util.Objects;
/*    */ import net.minecraft.class_1922;
/*    */ import net.minecraft.class_2338;
/*    */ import net.minecraft.class_2586;
/*    */ import net.minecraft.class_259;
/*    */ import net.minecraft.class_265;
/*    */ import net.minecraft.class_2680;
/*    */ import net.minecraft.class_3726;
/*    */ import net.minecraft.class_4970;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class CrateBlock
/*    */   extends AnimatedBlockWithDirection
/*    */ {
/*    */   private final Class<? extends class_2586> blockEntityClass;
/*    */   
/*    */   protected CrateBlock(@NotNull Class<? extends class_2586> blockEntityClass) {
/* 26 */     this(class_4970.class_2251.method_9637().method_9634(), Objects.<Class<? extends class_2586>>requireNonNull(blockEntityClass));
/*    */   }
/*    */   
/*    */   public CrateBlock(@NotNull class_4970.class_2251 settings, Class<? extends class_2586> blockEntityClass) {
/* 30 */     super(settings);
/* 31 */     this.blockEntityClass = blockEntityClass;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public class_2586 method_10123(class_2338 pos, class_2680 state) {
/*    */     try {
/* 41 */       return (class_2586)this.blockEntityClass.getConstructors()[0].newInstance(new Object[] { pos, state });
/* 42 */     } catch (Exception e) {
/* 43 */       throw new RuntimeException(e);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public class_265 method_9530(class_2680 state, class_1922 world, class_2338 pos, class_3726 context) {
/* 52 */     return class_259.method_1073();
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\block\crate\CrateBlock.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */