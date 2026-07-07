/*     */ package com.atlas.common.fabric.block.statue.system;
/*     */ import com.atlas.common.fabric.block.AtlasBlockRegistry;
/*     */ import com.atlas.common.fabric.block.animated.AnimatedBlockWithDirection;
/*     */ import com.atlas.common.fabric.block.statue.block.StatueProxyBlock;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import net.minecraft.class_1657;
/*     */ import net.minecraft.class_1799;
/*     */ import net.minecraft.class_1937;
/*     */ import net.minecraft.class_2246;
/*     */ import net.minecraft.class_2248;
/*     */ import net.minecraft.class_2338;
/*     */ import net.minecraft.class_2350;
/*     */ import net.minecraft.class_2382;
/*     */ import net.minecraft.class_2680;
/*     */ import net.minecraft.class_2769;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public final class StatueStructureHelper {
/*  22 */   private static final Map<String, ResolvedProxy> PROXY_CACHE = new ConcurrentHashMap<>();
/*  23 */   private static final Set<String> BREAKING_CORES = ConcurrentHashMap.newKeySet();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void placeProxyBlocks(@NotNull class_1937 world, @NotNull class_2338 corePos, @NotNull class_2680 coreState) {
/*  29 */     StatueDefinition definition = StatueRegistry.get(coreState);
/*  30 */     if (definition == null) {
/*     */       return;
/*     */     }
/*     */     
/*  34 */     class_2350 facing = (class_2350)coreState.method_11654((class_2769)AnimatedBlockWithDirection.FACING);
/*  35 */     for (class_2338 localOffset : definition.structure().getLocalOffsets()) {
/*  36 */       if (localOffset.equals(class_2338.field_10980)) {
/*     */         continue;
/*     */       }
/*     */       
/*  40 */       class_2338 actualPos = corePos.method_10081((class_2382)definition.structure().rotateOffset(facing, localOffset));
/*     */       
/*  42 */       class_2680 proxyState = (class_2680)AtlasBlockRegistry.STATUE_PROXY_BLOCK.method_9564().method_11657((class_2769)StatueProxyBlock.FACING, (Comparable)facing);
/*  43 */       world.method_8652(actualPos, proxyState, 3);
/*  44 */       world.method_8544(actualPos);
/*  45 */       cacheProxy(world, actualPos, new ResolvedProxy(corePos, coreState, definition, localOffset));
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void ensureProxyBlocks(@NotNull class_1937 world, @NotNull class_2338 corePos, @NotNull class_2680 coreState) {
/*  50 */     StatueDefinition definition = StatueRegistry.get(coreState);
/*  51 */     if (definition == null) {
/*     */       return;
/*     */     }
/*     */     
/*  55 */     class_2350 facing = (class_2350)coreState.method_11654((class_2769)AnimatedBlockWithDirection.FACING);
/*  56 */     Set<class_2338> desiredPositions = new HashSet<>();
/*  57 */     for (class_2338 localOffset : definition.structure().getLocalOffsets()) {
/*  58 */       if (localOffset.equals(class_2338.field_10980)) {
/*     */         continue;
/*     */       }
/*     */       
/*  62 */       class_2338 actualPos = corePos.method_10081((class_2382)definition.structure().rotateOffset(facing, localOffset));
/*  63 */       desiredPositions.add(actualPos);
/*  64 */       class_2680 currentState = world.method_8320(actualPos);
/*  65 */       if (!currentState.method_26215() && !currentState.method_27852((class_2248)AtlasBlockRegistry.STATUE_PROXY_BLOCK)) {
/*     */         continue;
/*     */       }
/*     */       
/*  69 */       if (!currentState.method_27852((class_2248)AtlasBlockRegistry.STATUE_PROXY_BLOCK)) {
/*     */         
/*  71 */         class_2680 proxyState = (class_2680)AtlasBlockRegistry.STATUE_PROXY_BLOCK.method_9564().method_11657((class_2769)StatueProxyBlock.FACING, (Comparable)facing);
/*  72 */         world.method_8652(actualPos, proxyState, 3);
/*     */       } 
/*  74 */       if (world.method_8321(actualPos) != null) {
/*  75 */         world.method_8544(actualPos);
/*     */       }
/*  77 */       cacheProxy(world, actualPos, new ResolvedProxy(corePos, coreState, definition, localOffset));
/*     */     } 
/*     */     
/*  80 */     for (int x = definition.cleanupMin().method_10263(); x <= definition.cleanupMax().method_10263(); x++) {
/*  81 */       for (int y = definition.cleanupMin().method_10264(); y <= definition.cleanupMax().method_10264(); y++) {
/*  82 */         for (int z = definition.cleanupMin().method_10260(); z <= definition.cleanupMax().method_10260(); z++) {
/*  83 */           class_2338 localPos = new class_2338(x, y, z);
/*  84 */           if (!localPos.equals(class_2338.field_10980)) {
/*     */ 
/*     */ 
/*     */             
/*  88 */             class_2338 actualPos = corePos.method_10081((class_2382)definition.structure().rotateOffset(facing, localPos));
/*  89 */             if (!desiredPositions.contains(actualPos)) {
/*     */ 
/*     */ 
/*     */               
/*  93 */               class_2680 stateAtPos = world.method_8320(actualPos);
/*  94 */               if (stateAtPos.method_27852((class_2248)AtlasBlockRegistry.STATUE_PROXY_BLOCK)) {
/*     */ 
/*     */ 
/*     */                 
/*  98 */                 ResolvedProxy resolvedProxy = resolveProxy(world, actualPos, stateAtPos);
/*  99 */                 if (resolvedProxy == null || resolvedProxy.corePos().equals(corePos)) {
/* 100 */                   world.method_8652(actualPos, class_2246.field_10124.method_9564(), 35);
/* 101 */                   PROXY_CACHE.remove(cacheKey(world, actualPos));
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }  } @Nullable
/* 109 */   public static ResolvedProxy resolveProxy(@NotNull class_1937 world, @NotNull class_2338 pos, @NotNull class_2680 proxyState) { ResolvedProxy cached = PROXY_CACHE.get(cacheKey(world, pos));
/* 110 */     if (cached != null) {
/* 111 */       class_2680 cachedCoreState = world.method_8320(cached.corePos());
/* 112 */       if (StatueRegistry.get(cachedCoreState) == cached.definition() && cachedCoreState
/* 113 */         .method_11654((class_2769)AnimatedBlockWithDirection.FACING) == proxyState.method_11654((class_2769)StatueProxyBlock.FACING)) {
/* 114 */         return new ResolvedProxy(cached.corePos(), cachedCoreState, cached.definition(), cached.localOffset());
/*     */       }
/* 116 */       PROXY_CACHE.remove(cacheKey(world, pos));
/*     */     } 
/*     */     
/* 119 */     class_2350 facing = (class_2350)proxyState.method_11654((class_2769)StatueProxyBlock.FACING);
/*     */     
/* 121 */     for (StatueDefinition definition : StatueRegistry.definitions()) {
/* 122 */       for (class_2338 localOffset : definition.structure().getLocalOffsets()) {
/* 123 */         if (localOffset.equals(class_2338.field_10980)) {
/*     */           continue;
/*     */         }
/*     */         
/* 127 */         class_2338 rotatedOffset = definition.structure().rotateOffset(facing, localOffset);
/* 128 */         class_2338 corePos = pos.method_10059((class_2382)rotatedOffset);
/* 129 */         class_2680 coreState = world.method_8320(corePos);
/* 130 */         if (StatueRegistry.get(coreState) != definition) {
/*     */           continue;
/*     */         }
/* 133 */         if (coreState.method_11654((class_2769)AnimatedBlockWithDirection.FACING) != facing) {
/*     */           continue;
/*     */         }
/*     */         
/* 137 */         ResolvedProxy resolvedProxy = new ResolvedProxy(corePos, coreState, definition, localOffset);
/* 138 */         cacheProxy(world, pos, resolvedProxy);
/* 139 */         return resolvedProxy;
/*     */       } 
/*     */     } 
/*     */     
/* 143 */     return null; }
/*     */   
/*     */   @NotNull
/*     */   public static class_2338 resolveMiningPos(@NotNull class_1937 world, @NotNull class_2338 pos) {
/* 147 */     class_2680 state = world.method_8320(pos);
/* 148 */     if (state.method_26204() instanceof com.atlas.common.fabric.block.statue.block.StatueBlock) {
/* 149 */       return pos;
/*     */     }
/*     */     
/* 152 */     if (!state.method_27852((class_2248)AtlasBlockRegistry.STATUE_PROXY_BLOCK)) {
/* 153 */       return pos;
/*     */     }
/*     */     
/* 156 */     ResolvedProxy resolvedProxy = resolveProxy(world, pos, state);
/* 157 */     return (resolvedProxy == null) ? pos : resolvedProxy.corePos();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void clearStructure(@NotNull class_1937 world, @NotNull class_2338 corePos, @NotNull class_2680 coreState, @Nullable class_2338 skipPos) {
/* 164 */     StatueDefinition definition = StatueRegistry.get(coreState);
/* 165 */     if (definition == null) {
/*     */       return;
/*     */     }
/*     */     
/* 169 */     class_2350 facing = (class_2350)coreState.method_11654((class_2769)AnimatedBlockWithDirection.FACING);
/* 170 */     for (int x = definition.cleanupMin().method_10263(); x <= definition.cleanupMax().method_10263(); x++) {
/* 171 */       for (int y = definition.cleanupMin().method_10264(); y <= definition.cleanupMax().method_10264(); y++) {
/* 172 */         for (int z = definition.cleanupMin().method_10260(); z <= definition.cleanupMax().method_10260(); z++) {
/* 173 */           class_2338 localPos = new class_2338(x, y, z);
/* 174 */           class_2338 actualPos = corePos.method_10081((class_2382)definition.structure().rotateOffset(facing, localPos));
/* 175 */           if (!actualPos.equals(skipPos)) {
/*     */ 
/*     */ 
/*     */             
/* 179 */             class_2680 placedState = world.method_8320(actualPos);
/* 180 */             if (placedState.method_27852(coreState.method_26204()) || placedState.method_27852((class_2248)AtlasBlockRegistry.STATUE_PROXY_BLOCK)) {
/* 181 */               world.method_8652(actualPos, class_2246.field_10124.method_9564(), 35);
/*     */             }
/* 183 */             PROXY_CACHE.remove(cacheKey(world, actualPos));
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   public static void beginBreak(@NotNull class_1937 world, @NotNull class_2338 corePos) {
/* 190 */     BREAKING_CORES.add(cacheKey(world, corePos));
/*     */   }
/*     */   
/*     */   public static void finishBreak(@NotNull class_1937 world, @NotNull class_2338 corePos) {
/* 194 */     BREAKING_CORES.remove(cacheKey(world, corePos));
/*     */   }
/*     */   
/*     */   public static boolean isBreakInProgress(@NotNull class_1937 world, @NotNull class_2338 corePos) {
/* 198 */     return BREAKING_CORES.contains(cacheKey(world, corePos));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void breakStructure(@NotNull class_1937 world, @NotNull class_2338 corePos, @NotNull class_2680 coreState, @NotNull class_1657 player) {
/* 205 */     StatueDefinition definition = StatueRegistry.get(coreState);
/* 206 */     if (definition == null) {
/*     */       return;
/*     */     }
/*     */     
/* 210 */     class_1799 statueStack = new class_1799((class_1935)coreState.method_26204());
/* 211 */     class_2350 facing = (class_2350)coreState.method_11654((class_2769)AnimatedBlockWithDirection.FACING);
/* 212 */     class_2680 currentCoreState = world.method_8320(corePos);
/*     */     
/* 214 */     for (int x = definition.cleanupMin().method_10263(); x <= definition.cleanupMax().method_10263(); x++) {
/* 215 */       for (int y = definition.cleanupMin().method_10264(); y <= definition.cleanupMax().method_10264(); y++) {
/* 216 */         for (int z = definition.cleanupMin().method_10260(); z <= definition.cleanupMax().method_10260(); z++) {
/* 217 */           class_2338 localPos = new class_2338(x, y, z);
/* 218 */           class_2338 actualPos = corePos.method_10081((class_2382)definition.structure().rotateOffset(facing, localPos));
/* 219 */           if (!actualPos.equals(corePos)) {
/*     */ 
/*     */ 
/*     */             
/* 223 */             class_2680 stateAtPos = world.method_8320(actualPos);
/* 224 */             if (stateAtPos.method_27852((class_2248)AtlasBlockRegistry.STATUE_PROXY_BLOCK)) {
/*     */ 
/*     */ 
/*     */               
/* 228 */               ResolvedProxy resolvedProxy = resolveProxy(world, actualPos, stateAtPos);
/* 229 */               if (resolvedProxy == null || resolvedProxy.corePos().equals(corePos)) {
/* 230 */                 world.method_8652(actualPos, class_2246.field_10124.method_9564(), 35);
/*     */               }
/* 232 */               PROXY_CACHE.remove(cacheKey(world, actualPos));
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/* 237 */     }  if (currentCoreState.method_27852(coreState.method_26204())) {
/* 238 */       world.method_8652(corePos, class_2246.field_10124.method_9564(), 35);
/*     */     }
/* 240 */     PROXY_CACHE.remove(cacheKey(world, corePos));
/* 241 */     if (!player.method_7337() && 
/* 242 */       !player.method_31548().method_7394(statueStack)) {
/* 243 */       player.method_7328(statueStack, false);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void emitBreakEffects(@NotNull class_1937 world, @NotNull class_1657 player, @NotNull class_2338 effectPos, @NotNull class_2680 displayState) {
/* 252 */     if (!world.field_9236) {
/* 253 */       world.method_8444(player, 2001, effectPos, class_2248.method_9507(displayState));
/*     */     }
/*     */   }
/*     */   
/*     */   private static void cacheProxy(@NotNull class_1937 world, @NotNull class_2338 pos, @NotNull ResolvedProxy resolvedProxy) {
/* 258 */     PROXY_CACHE.put(cacheKey(world, pos), resolvedProxy);
/*     */   }
/*     */   
/*     */   @NotNull
/* 262 */   private static String cacheKey(@NotNull class_1937 world, @NotNull class_2338 pos) { return String.valueOf(world.method_27983().method_29177()) + ":" + String.valueOf(world.method_27983().method_29177()); } public static final class ResolvedProxy extends Record { @NotNull private final class_2338 corePos; @NotNull
/*     */     private final class_2680 coreState; @NotNull
/*     */     private final StatueDefinition definition; @NotNull
/* 265 */     private final class_2338 localOffset; public ResolvedProxy(@NotNull class_2338 corePos, @NotNull class_2680 coreState, @NotNull StatueDefinition definition, @NotNull class_2338 localOffset) { this.corePos = corePos; this.coreState = coreState; this.definition = definition; this.localOffset = localOffset; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/block/statue/system/StatueStructureHelper$ResolvedProxy;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #265	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/* 265 */       //   0	7	0	this	Lcom/atlas/common/fabric/block/statue/system/StatueStructureHelper$ResolvedProxy; } @NotNull public class_2338 corePos() { return this.corePos; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/block/statue/system/StatueStructureHelper$ResolvedProxy;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #265	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/block/statue/system/StatueStructureHelper$ResolvedProxy; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/block/statue/system/StatueStructureHelper$ResolvedProxy;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #265	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lcom/atlas/common/fabric/block/statue/system/StatueStructureHelper$ResolvedProxy;
/* 265 */       //   0	8	1	o	Ljava/lang/Object; } @NotNull public class_2680 coreState() { return this.coreState; } @NotNull public StatueDefinition definition() { return this.definition; } @NotNull public class_2338 localOffset() { return this.localOffset; }
/*     */      }
/*     */ 
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\block\statue\system\StatueStructureHelper.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */