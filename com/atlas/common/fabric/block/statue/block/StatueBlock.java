/*     */ package com.atlas.common.fabric.block.statue.block;
/*     */ import com.atlas.common.fabric.block.AtlasBlockRegistry;
/*     */ import com.atlas.common.fabric.block.animated.AnimatedBlockWithDirection;
/*     */ import com.atlas.common.fabric.block.statue.entity.StatueBlockEntity;
/*     */ import com.atlas.common.fabric.block.statue.system.StatueDefinition;
/*     */ import com.atlas.common.fabric.block.statue.system.StatueRegistry;
/*     */ import com.atlas.common.fabric.block.statue.system.StatueStructureHelper;
/*     */ import com.mojang.serialization.MapCodec;
/*     */ import java.util.function.Function;
/*     */ import net.minecraft.class_1269;
/*     */ import net.minecraft.class_1309;
/*     */ import net.minecraft.class_1657;
/*     */ import net.minecraft.class_1750;
/*     */ import net.minecraft.class_1799;
/*     */ import net.minecraft.class_1922;
/*     */ import net.minecraft.class_1937;
/*     */ import net.minecraft.class_2237;
/*     */ import net.minecraft.class_2338;
/*     */ import net.minecraft.class_2350;
/*     */ import net.minecraft.class_2382;
/*     */ import net.minecraft.class_2586;
/*     */ import net.minecraft.class_259;
/*     */ import net.minecraft.class_2591;
/*     */ import net.minecraft.class_265;
/*     */ import net.minecraft.class_2680;
/*     */ import net.minecraft.class_2769;
/*     */ import net.minecraft.class_3726;
/*     */ import net.minecraft.class_4970;
/*     */ import net.minecraft.class_5558;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public final class StatueBlock extends AnimatedBlockWithDirection implements class_2343 {
/*  34 */   public static final MapCodec<StatueBlock> CODEC = method_54094(StatueBlock::new);
/*     */   
/*     */   public StatueBlock() {
/*  37 */     this(class_4970.class_2251.method_9637().method_9632(2.0F).method_22488());
/*     */   }
/*     */   
/*     */   public StatueBlock(@NotNull class_4970.class_2251 settings) {
/*  41 */     super(settings);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   protected MapCodec<? extends class_2237> method_53969() {
/*  46 */     return (MapCodec)CODEC;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public class_2680 method_9605(@NotNull class_1750 ctx) {
/*  51 */     class_2680 baseState = super.method_9605(ctx);
/*  52 */     if (baseState == null) {
/*  53 */       return null;
/*     */     }
/*     */     
/*  56 */     StatueDefinition definition = StatueRegistry.get(baseState);
/*  57 */     if (definition == null) {
/*  58 */       return null;
/*     */     }
/*     */     
/*  61 */     class_2350 facing = (class_2350)baseState.method_11654((class_2769)FACING);
/*  62 */     for (class_2338 localOffset : definition.structure().getLocalOffsets()) {
/*  63 */       class_2338 checkPos = ctx.method_8037().method_10081((class_2382)definition.structure().rotateOffset(facing, localOffset));
/*  64 */       if (checkPos.equals(ctx.method_8037())) {
/*     */         continue;
/*     */       }
/*     */       
/*  68 */       if (!ctx.method_8045().method_8320(checkPos).method_26166(ctx)) {
/*  69 */         return null;
/*     */       }
/*     */     } 
/*  72 */     return baseState;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public class_2586 method_10123(@NotNull class_2338 pos, @NotNull class_2680 state) {
/*  77 */     return (class_2586)new StatueBlockEntity(AtlasBlockRegistry.STATUE_BLOCK_ENTITY_TYPE, pos, state);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public <T extends class_2586> class_5558<T> method_31645(@NotNull class_1937 world, @NotNull class_2680 state, @NotNull class_2591<T> type) {
/*  83 */     return (type == AtlasBlockRegistry.STATUE_BLOCK_ENTITY_TYPE) ? ((world1, pos, state1, blockEntity) -> StatueBlockEntity.tick(world1, pos, state1, (StatueBlockEntity)blockEntity)) : 
/*     */       
/*  85 */       null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void method_9567(@NotNull class_1937 world, @NotNull class_2338 pos, @NotNull class_2680 state, @Nullable class_1309 placer, @NotNull class_1799 itemStack) {
/*  91 */     super.method_9567(world, pos, state, placer, itemStack);
/*  92 */     if (!world.field_9236) {
/*  93 */       StatueStructureHelper.placeProxyBlocks(world, pos, state);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public class_2680 method_9576(@NotNull class_1937 world, @NotNull class_2338 pos, @NotNull class_2680 state, @NotNull class_1657 player) {
/* 100 */     if (!world.field_9236) {
/* 101 */       StatueStructureHelper.beginBreak(world, pos);
/*     */     }
/* 103 */     return super.method_9576(world, pos, state, player);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void method_9556(@NotNull class_1937 world, @NotNull class_1657 player, @NotNull class_2338 pos, @NotNull class_2680 state, @Nullable class_2586 blockEntity, @NotNull class_1799 tool) {
/* 109 */     if (!world.field_9236) {
/*     */       try {
/* 111 */         StatueStructureHelper.breakStructure(world, pos, state, player);
/*     */       } finally {
/* 113 */         StatueStructureHelper.finishBreak(world, pos);
/*     */       } 
/*     */     }
/* 116 */     super.method_9556(world, player, pos, state, blockEntity, tool);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void method_9536(@NotNull class_2680 state, @NotNull class_1937 world, @NotNull class_2338 pos, @NotNull class_2680 newState, boolean moved) {
/* 122 */     if (!state.method_27852(newState.method_26204()) && !world.field_9236 && !StatueStructureHelper.isBreakInProgress(world, pos)) {
/* 123 */       StatueStructureHelper.clearStructure(world, pos, state, pos);
/*     */     }
/* 125 */     super.method_9536(state, world, pos, newState, moved);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   protected class_1269 method_55766(@NotNull class_2680 state, @NotNull class_1937 world, @NotNull class_2338 pos, @NotNull class_1657 player, @NotNull class_3965 hit) {
/* 131 */     if (!world.field_9236) { class_2586 class_2586 = world.method_8321(pos); if (class_2586 instanceof StatueBlockEntity) { StatueBlockEntity statueBlockEntity = (StatueBlockEntity)class_2586;
/* 132 */         statueBlockEntity.handleUse(player); }
/*     */        }
/* 134 */      return class_1269.field_5812;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public class_265 method_9530(@NotNull class_2680 state, @NotNull class_1922 world, @NotNull class_2338 pos, @NotNull class_3726 context) {
/* 140 */     return class_259.method_1077();
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public class_265 method_9549(@NotNull class_2680 state, @NotNull class_1922 world, @NotNull class_2338 pos, @NotNull class_3726 context) {
/* 146 */     return getShape(state);
/*     */   }
/*     */   @NotNull
/*     */   private class_265 getShape(@NotNull class_2680 state) {
/* 150 */     StatueDefinition definition = StatueRegistry.get(state);
/* 151 */     if (definition == null) {
/* 152 */       return class_259.method_1077();
/*     */     }
/* 154 */     return definition.structure().getRotatedShape((class_2350)state.method_11654((class_2769)FACING), class_2338.field_10980);
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\block\statue\block\StatueBlock.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */