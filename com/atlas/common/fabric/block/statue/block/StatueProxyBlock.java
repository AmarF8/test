/*     */ package com.atlas.common.fabric.block.statue.block;
/*     */ import com.atlas.common.fabric.block.statue.entity.StatueBlockEntity;
/*     */ import com.atlas.common.fabric.block.statue.system.StatueStructureHelper;
/*     */ import net.minecraft.class_1269;
/*     */ import net.minecraft.class_1657;
/*     */ import net.minecraft.class_1799;
/*     */ import net.minecraft.class_1922;
/*     */ import net.minecraft.class_1937;
/*     */ import net.minecraft.class_2248;
/*     */ import net.minecraft.class_2338;
/*     */ import net.minecraft.class_2350;
/*     */ import net.minecraft.class_2464;
/*     */ import net.minecraft.class_2586;
/*     */ import net.minecraft.class_259;
/*     */ import net.minecraft.class_265;
/*     */ import net.minecraft.class_2680;
/*     */ import net.minecraft.class_2689;
/*     */ import net.minecraft.class_2753;
/*     */ import net.minecraft.class_2769;
/*     */ import net.minecraft.class_3726;
/*     */ import net.minecraft.class_4970;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public final class StatueProxyBlock extends class_2248 {
/*  26 */   public static final class_2753 FACING = AnimatedBlockWithDirection.FACING;
/*     */   
/*     */   public StatueProxyBlock() {
/*  29 */     super(class_4970.class_2251.method_9637().method_9632(2.0F).method_22488());
/*  30 */     method_9590((class_2680)((class_2680)method_9595().method_11664()).method_11657((class_2769)FACING, (Comparable)class_2350.field_11043));
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public class_2464 method_9604(@NotNull class_2680 state) {
/*  35 */     return class_2464.field_11455;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void method_9515(@NotNull class_2689.class_2690<class_2248, class_2680> builder) {
/*  40 */     builder.method_11667(new class_2769[] { (class_2769)FACING });
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public class_2680 method_9576(@NotNull class_1937 world, @NotNull class_2338 pos, @NotNull class_2680 state, @NotNull class_1657 player) {
/*  46 */     if (!world.field_9236) {
/*  47 */       StatueStructureHelper.ResolvedProxy resolvedProxy = StatueStructureHelper.resolveProxy(world, pos, state);
/*  48 */       if (resolvedProxy != null) {
/*  49 */         StatueStructureHelper.beginBreak(world, resolvedProxy.corePos());
/*     */       }
/*     */     } 
/*  52 */     return super.method_9576(world, pos, state, player);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void method_9556(@NotNull class_1937 world, @NotNull class_1657 player, @NotNull class_2338 pos, @NotNull class_2680 state, @Nullable class_2586 blockEntity, @NotNull class_1799 tool) {
/*  58 */     if (!world.field_9236) {
/*  59 */       StatueStructureHelper.ResolvedProxy resolvedProxy = StatueStructureHelper.resolveProxy(world, pos, state);
/*  60 */       if (resolvedProxy != null && resolvedProxy.coreState().method_26204() instanceof StatueBlock) {
/*     */         try {
/*  62 */           StatueStructureHelper.emitBreakEffects(world, player, pos, resolvedProxy.coreState());
/*  63 */           StatueStructureHelper.breakStructure(world, resolvedProxy.corePos(), resolvedProxy.coreState(), player);
/*     */         } finally {
/*  65 */           StatueStructureHelper.finishBreak(world, resolvedProxy.corePos());
/*     */         } 
/*     */       }
/*     */     } 
/*  69 */     super.method_9556(world, player, pos, state, blockEntity, tool);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void method_9536(@NotNull class_2680 state, @NotNull class_1937 world, @NotNull class_2338 pos, @NotNull class_2680 newState, boolean moved) {
/*  75 */     if (!state.method_27852(newState.method_26204()) && !world.field_9236) {
/*  76 */       StatueStructureHelper.ResolvedProxy resolvedProxy = StatueStructureHelper.resolveProxy(world, pos, state);
/*  77 */       if (resolvedProxy != null && resolvedProxy.coreState().method_26204() instanceof StatueBlock && 
/*  78 */         !StatueStructureHelper.isBreakInProgress(world, resolvedProxy.corePos())) {
/*  79 */         StatueStructureHelper.clearStructure(world, resolvedProxy.corePos(), resolvedProxy.coreState(), pos);
/*     */       }
/*     */     } 
/*  82 */     super.method_9536(state, world, pos, newState, moved);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   protected class_1269 method_55766(@NotNull class_2680 state, @NotNull class_1937 world, @NotNull class_2338 pos, @NotNull class_1657 player, @NotNull class_3965 hit) {
/*  88 */     StatueStructureHelper.ResolvedProxy resolvedProxy = StatueStructureHelper.resolveProxy(world, pos, state);
/*  89 */     if (!world.field_9236 && resolvedProxy != null) {
/*  90 */       class_2586 class_2586 = world.method_8321(resolvedProxy.corePos()); if (class_2586 instanceof StatueBlockEntity) { StatueBlockEntity statueBlockEntity = (StatueBlockEntity)class_2586;
/*  91 */         statueBlockEntity.handleUse(player); }
/*     */     
/*  93 */     }  return class_1269.field_5812;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public class_265 method_9530(@NotNull class_2680 state, @NotNull class_1922 world, @NotNull class_2338 pos, @NotNull class_3726 context) {
/*  99 */     return class_259.method_1077();
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public class_265 method_9549(@NotNull class_2680 state, @NotNull class_1922 world, @NotNull class_2338 pos, @NotNull class_3726 context) {
/* 105 */     return getShape(world, pos, state);
/*     */   } @NotNull
/*     */   private class_265 getShape(@NotNull class_1922 world, @NotNull class_2338 pos, @NotNull class_2680 state) {
/*     */     class_1937 realWorld;
/* 109 */     if (world instanceof class_1937) { realWorld = (class_1937)world; }
/* 110 */     else { return class_259.method_1073(); }
/*     */ 
/*     */     
/* 113 */     StatueStructureHelper.ResolvedProxy resolvedProxy = StatueStructureHelper.resolveProxy(realWorld, pos, state);
/* 114 */     if (resolvedProxy == null) {
/* 115 */       return class_259.method_1073();
/*     */     }
/*     */     
/* 118 */     return resolvedProxy.definition().structure().getRotatedShape((class_2350)state.method_11654((class_2769)FACING), resolvedProxy.localOffset());
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\block\statue\block\StatueProxyBlock.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */