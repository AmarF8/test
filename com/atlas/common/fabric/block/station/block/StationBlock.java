/*     */ package com.atlas.common.fabric.block.station.block;
/*     */ 
/*     */ import com.atlas.common.fabric.block.animated.AnimatedBlockWithDirection;
/*     */ import com.atlas.common.fabric.block.station.StationItemBranding;
/*     */ import com.atlas.common.fabric.block.station.entity.StationBlockEntity;
/*     */ import java.util.function.BiConsumer;
/*     */ import java.util.function.Predicate;
/*     */ import net.minecraft.class_1269;
/*     */ import net.minecraft.class_1309;
/*     */ import net.minecraft.class_1657;
/*     */ import net.minecraft.class_1799;
/*     */ import net.minecraft.class_1935;
/*     */ import net.minecraft.class_1937;
/*     */ import net.minecraft.class_2248;
/*     */ import net.minecraft.class_2338;
/*     */ import net.minecraft.class_2586;
/*     */ import net.minecraft.class_2591;
/*     */ import net.minecraft.class_2680;
/*     */ import net.minecraft.class_3222;
/*     */ import net.minecraft.class_3965;
/*     */ import net.minecraft.class_4970;
/*     */ import net.minecraft.class_5558;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class StationBlock
/*     */   extends AnimatedBlockWithDirection
/*     */ {
/*     */   @NotNull
/*     */   private static Predicate<class_1657> breakPermissionOverride = player -> false;
/*     */   @NotNull
/*  38 */   private static BiConsumer<class_1657, class_1799> breakDropHandler = class_1657::method_7270;
/*     */ 
/*     */   
/*     */   private final BlockEntityFactory factory;
/*     */ 
/*     */   
/*     */   public static void setBreakPermissionOverride(@NotNull Predicate<class_1657> override) {
/*  45 */     breakPermissionOverride = override;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setBreakDropHandler(@NotNull BiConsumer<class_1657, class_1799> handler) {
/*  54 */     breakDropHandler = handler;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected StationBlock(@NotNull BlockEntityFactory factory) {
/*  65 */     super(class_4970.class_2251.method_9637().method_9632(1.5F).method_22488());
/*  66 */     this.factory = factory;
/*     */   }
/*     */ 
/*     */   
/*     */   public class_2586 method_10123(class_2338 pos, class_2680 state) {
/*  71 */     return this.factory.create(pos, state);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public <T extends class_2586> class_5558<T> method_31645(class_1937 world, class_2680 state, class_2591<T> type) {
/*  77 */     return (world1, pos, state1, blockEntity) -> {
/*     */         if (blockEntity instanceof StationBlockEntity) {
/*     */           StationBlockEntity stationBlockEntity = (StationBlockEntity)blockEntity;
/*     */           StationBlockEntity.tick(world1, pos, state1, stationBlockEntity);
/*     */         } 
/*     */       };
/*     */   }
/*     */   
/*     */   protected class_1269 method_55766(class_2680 state, class_1937 world, class_2338 pos, class_1657 player, class_3965 hit) {
/*  86 */     if (!world.field_9236 && player instanceof class_3222) { class_3222 serverPlayer = (class_3222)player;
/*  87 */       class_2586 be = world.method_8321(pos);
/*  88 */       if (be instanceof StationBlockEntity) { StationBlockEntity stationBlockEntity = (StationBlockEntity)be; } else { return class_1269.field_5812; }
/*     */        }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 118 */     return class_1269.field_5812;
/*     */   }
/*     */ 
/*     */   
/*     */   public void method_9567(class_1937 world, class_2338 pos, class_2680 state, @Nullable class_1309 placer, class_1799 itemStack) {
/* 123 */     super.method_9567(world, pos, state, placer, itemStack);
/* 124 */     if (!world.field_9236) {
/* 125 */       class_2586 be = world.method_8321(pos);
/* 126 */       if (be instanceof StationBlockEntity) { StationBlockEntity stationBlockEntity = (StationBlockEntity)be;
/* 127 */         if (placer instanceof class_1657) { class_1657 player = (class_1657)placer;
/* 128 */           stationBlockEntity.setOwnerUUID(player.method_5667()); }
/*     */          }
/*     */     
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void method_9536(class_2680 state, class_1937 world, class_2338 pos, class_2680 newState, boolean moved) {
/* 136 */     if (!state.method_27852(newState.method_26204())) {
/* 137 */       class_2586 be = world.method_8321(pos);
/* 138 */       if (be instanceof StationBlockEntity) { StationBlockEntity stationBlockEntity = (StationBlockEntity)be;
/*     */         
/* 140 */         stationBlockEntity.onBroken(); }
/*     */       
/* 142 */       super.method_9536(state, world, pos, newState, moved);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public class_2680 method_9576(class_1937 world, class_2338 pos, class_2680 state, class_1657 player) {
/* 148 */     if (!world.field_9236 && !player.method_7337()) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 153 */       class_2338 originPos = pos;
/* 154 */       class_2248 class_2248 = state.method_26204(); if (class_2248 instanceof MultiBlockStationBlock) { MultiBlockStationBlock multiBlock = (MultiBlockStationBlock)class_2248;
/* 155 */         originPos = multiBlock.getOriginPos(pos, state); }
/*     */       
/* 157 */       class_2586 be = world.method_8321(originPos);
/* 158 */       if (be instanceof StationBlockEntity) { StationBlockEntity station = (StationBlockEntity)be; if (station.getOwnerUUID() != null && 
/* 159 */           !player.method_5667().equals(station.getOwnerUUID()) && 
/* 160 */           !breakPermissionOverride.test(player))
/*     */         {
/*     */ 
/*     */ 
/*     */           
/* 165 */           return super.method_9576(world, pos, state, player);
/*     */         } }
/*     */       
/* 168 */       if (shouldDropOnBreak(state)) {
/* 169 */         breakDropHandler.accept(player, StationItemBranding.apply(new class_1799((class_1935)this)));
/*     */       }
/*     */     } 
/* 172 */     return super.method_9576(world, pos, state, player);
/*     */   }
/*     */   
/*     */   protected boolean shouldDropOnBreak(class_2680 state) {
/* 176 */     return true;
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface BlockEntityFactory {
/*     */     class_2586 create(class_2338 param1class_2338, class_2680 param1class_2680);
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\block\station\block\StationBlock.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */