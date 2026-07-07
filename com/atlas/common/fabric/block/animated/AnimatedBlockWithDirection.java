/*    */ package com.atlas.common.fabric.block.animated;
/*    */ 
/*    */ import net.minecraft.class_1750;
/*    */ import net.minecraft.class_2237;
/*    */ import net.minecraft.class_2248;
/*    */ import net.minecraft.class_2350;
/*    */ import net.minecraft.class_2383;
/*    */ import net.minecraft.class_2415;
/*    */ import net.minecraft.class_2464;
/*    */ import net.minecraft.class_2470;
/*    */ import net.minecraft.class_2680;
/*    */ import net.minecraft.class_2689;
/*    */ import net.minecraft.class_2753;
/*    */ import net.minecraft.class_2769;
/*    */ import net.minecraft.class_4970;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class AnimatedBlockWithDirection
/*    */   extends class_2237
/*    */ {
/*    */   public AnimatedBlockWithDirection(@NotNull class_4970.class_2251 settings) {
/* 28 */     super(settings);
/* 29 */     method_9590((class_2680)((class_2680)this.field_10647.method_11664()).method_11657((class_2769)FACING, (Comparable)class_2350.field_11043));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public class_2464 method_9604(@NotNull class_2680 state) {
/* 37 */     return class_2464.field_11456;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public class_2680 method_9605(@NotNull class_1750 ctx) {
/* 49 */     return (class_2680)method_9564().method_11657((class_2769)FACING, (Comparable)ctx.method_8042().method_10153());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public class_2680 method_9598(@NotNull class_2680 state, @NotNull class_2470 rotation) {
/* 62 */     return (class_2680)state.method_11657((class_2769)FACING, (Comparable)rotation.method_10503((class_2350)state.method_11654((class_2769)FACING)));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public class_2680 method_9569(@NotNull class_2680 state, @NotNull class_2415 mirror) {
/* 75 */     return state.method_26186(mirror.method_10345((class_2350)state.method_11654((class_2769)FACING)));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void method_9515(@NotNull class_2689.class_2690<class_2248, class_2680> builder) {
/* 85 */     builder.method_11667(new class_2769[] { (class_2769)FACING });
/*    */   }
/*    */ 
/*    */   
/* 89 */   public static final class_2753 FACING = class_2383.field_11177;
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\block\animated\AnimatedBlockWithDirection.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */