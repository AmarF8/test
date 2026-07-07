/*     */ package com.atlas.common.fabric.block.statue.entity;
/*     */ import com.atlas.common.fabric.block.AtlasBlockRegistry;
/*     */ import com.atlas.common.fabric.block.statue.system.StatueDefinition;
/*     */ import com.atlas.common.fabric.block.statue.system.StatueRegistry;
/*     */ import com.atlas.common.fabric.block.statue.system.StatueStructureHelper;
/*     */ import com.atlas.common.fabric.item.statue.EnergyRuneItem;
/*     */ import net.minecraft.class_124;
/*     */ import net.minecraft.class_1268;
/*     */ import net.minecraft.class_1657;
/*     */ import net.minecraft.class_1792;
/*     */ import net.minecraft.class_1799;
/*     */ import net.minecraft.class_1937;
/*     */ import net.minecraft.class_2338;
/*     */ import net.minecraft.class_2487;
/*     */ import net.minecraft.class_2561;
/*     */ import net.minecraft.class_2586;
/*     */ import net.minecraft.class_2591;
/*     */ import net.minecraft.class_2596;
/*     */ import net.minecraft.class_2602;
/*     */ import net.minecraft.class_2622;
/*     */ import net.minecraft.class_2680;
/*     */ import net.minecraft.class_7225;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ import software.bernie.geckolib.animatable.GeoAnimatable;
/*     */ import software.bernie.geckolib.animatable.GeoBlockEntity;
/*     */ import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
/*     */ import software.bernie.geckolib.animation.AnimatableManager;
/*     */ import software.bernie.geckolib.animation.AnimationController;
/*     */ import software.bernie.geckolib.animation.AnimationState;
/*     */ import software.bernie.geckolib.animation.PlayState;
/*     */ import software.bernie.geckolib.animation.RawAnimation;
/*     */ 
/*     */ public final class StatueBlockEntity extends class_2586 implements GeoBlockEntity {
/*     */   private static final String ACTIVE_UNTIL_KEY = "ActiveUntil";
/*  36 */   private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache((GeoAnimatable)this); private static final String ACTIVE_RUNE_KEY = "ActiveRune"; private long activeUntilGameTime;
/*     */   @Nullable
/*     */   private String activeRuneId;
/*     */   
/*     */   public StatueBlockEntity(@NotNull class_2591<?> type, @NotNull class_2338 pos, @NotNull class_2680 state) {
/*  41 */     super(type, pos, state);
/*     */   }
/*     */   
/*     */   public StatueBlockEntity(@NotNull class_2338 pos, @NotNull class_2680 state) {
/*  45 */     this(AtlasBlockRegistry.STATUE_BLOCK_ENTITY_TYPE, pos, state);
/*     */   }
/*     */   @Nullable
/*     */   public StatueDefinition getDefinition() {
/*  49 */     return StatueRegistry.get(method_11010());
/*     */   }
/*     */   
/*     */   public boolean isActive() {
/*  53 */     return isActive((this.field_11863 == null) ? 0L : this.field_11863.method_8510());
/*     */   }
/*     */   
/*     */   public boolean isActive(long worldTime) {
/*  57 */     return (worldTime < this.activeUntilGameTime);
/*     */   }
/*     */   
/*     */   public long getActiveUntilGameTime() {
/*  61 */     return this.activeUntilGameTime;
/*     */   }
/*     */   @Nullable
/*     */   public String getActiveRuneId() {
/*  65 */     return this.activeRuneId;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void tick(@NotNull class_1937 world, @NotNull class_2338 pos, @NotNull class_2680 state, @NotNull StatueBlockEntity blockEntity) {
/*  70 */     if (world.field_9236 || world.method_8510() % 20L != 0L) {
/*     */       return;
/*     */     }
/*     */     
/*  74 */     StatueStructureHelper.ensureProxyBlocks(world, pos, state);
/*     */   }
/*     */   
/*     */   public void handleUse(@NotNull class_1657 player) {
/*  78 */     class_1937 world = method_10997();
/*  79 */     if (world == null || world.field_9236) {
/*     */       return;
/*     */     }
/*     */     
/*  83 */     class_1268 usedHand = null;
/*  84 */     class_1799 stack = class_1799.field_8037;
/*  85 */     if (player.method_5998(class_1268.field_5808).method_7909() instanceof EnergyRuneItem) {
/*  86 */       usedHand = class_1268.field_5808;
/*  87 */       stack = player.method_5998(class_1268.field_5808);
/*  88 */     } else if (player.method_5998(class_1268.field_5810).method_7909() instanceof EnergyRuneItem) {
/*  89 */       usedHand = class_1268.field_5810;
/*  90 */       stack = player.method_5998(class_1268.field_5810);
/*     */     } 
/*     */     
/*  93 */     if (usedHand != null) { class_1792 class_1792 = stack.method_7909(); if (class_1792 instanceof EnergyRuneItem) { EnergyRuneItem runeItem = (EnergyRuneItem)class_1792;
/*  94 */         long baseTime = Math.max(world.method_8510(), this.activeUntilGameTime);
/*  95 */         this.activeUntilGameTime = baseTime + runeItem.getDurationTicks();
/*  96 */         this.activeRuneId = stack.method_7909().toString();
/*     */         
/*  98 */         if (!player.method_7337()) {
/*  99 */           stack.method_7934(1);
/*     */         }
/*     */         
/* 102 */         method_5431();
/* 103 */         player.method_43496((class_2561)class_2561.method_43470(describeActivation(runeItem.getDurationTicks())).method_27692(class_124.field_1065));
/*     */         return; }
/*     */        }
/*     */     
/* 107 */     StatueDefinition definition = getDefinition();
/* 108 */     if (definition == null) {
/*     */       return;
/*     */     }
/*     */     
/* 112 */     if (isActive(world.method_8510())) {
/* 113 */       long remainingTicks = this.activeUntilGameTime - world.method_8510();
/* 114 */       long remainingSeconds = remainingTicks / 20L;
/* 115 */       player.method_43496((class_2561)class_2561.method_43470("%s is active for another %dm %02ds."
/*     */             
/* 117 */             .formatted(new Object[] { readableName(definition.id()), Long.valueOf(remainingSeconds / 60L), Long.valueOf(remainingSeconds % 60L)
/* 118 */               })).method_27692(class_124.field_1060));
/*     */     } else {
/* 120 */       player.method_43496((class_2561)class_2561.method_43470("%s is dormant. Insert an Energy Rune to activate its boost."
/*     */             
/* 122 */             .formatted(new Object[] { readableName(definition.id())
/* 123 */               })).method_27692(class_124.field_1054));
/*     */     } 
/*     */   }
/*     */   @NotNull
/*     */   private String describeActivation(int durationTicks) {
/* 128 */     StatueDefinition definition = getDefinition();
/* 129 */     String name = (definition == null) ? "Statue" : readableName(definition.id());
/* 130 */     long durationMinutes = durationTicks / 1200L;
/* 131 */     return "%s activated for %d minutes.".formatted(new Object[] { name, Long.valueOf(durationMinutes) });
/*     */   }
/*     */   @NotNull
/*     */   private static String readableName(@NotNull String id) {
/* 135 */     String[] words = id.replace('_', ' ').split(" ");
/* 136 */     StringBuilder builder = new StringBuilder();
/* 137 */     for (int i = 0; i < words.length; i++) {
/* 138 */       if (!words[i].isEmpty()) {
/*     */ 
/*     */ 
/*     */         
/* 142 */         if (builder.length() > 0) {
/* 143 */           builder.append(' ');
/*     */         }
/* 145 */         builder.append(Character.toUpperCase(words[i].charAt(0)));
/* 146 */         if (words[i].length() > 1)
/* 147 */           builder.append(words[i].substring(1)); 
/*     */       } 
/*     */     } 
/* 150 */     return builder.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void method_11014(@NotNull class_2487 nbt, @NotNull class_7225.class_7874 registries) {
/* 155 */     super.method_11014(nbt, registries);
/* 156 */     this.activeUntilGameTime = nbt.method_10537("ActiveUntil");
/* 157 */     this.activeRuneId = nbt.method_10545("ActiveRune") ? nbt.method_10558("ActiveRune") : null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void method_11007(@NotNull class_2487 nbt, @NotNull class_7225.class_7874 registries) {
/* 162 */     super.method_11007(nbt, registries);
/* 163 */     nbt.method_10544("ActiveUntil", this.activeUntilGameTime);
/* 164 */     if (this.activeRuneId != null) {
/* 165 */       nbt.method_10582("ActiveRune", this.activeRuneId);
/*     */     }
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public class_2596<class_2602> method_38235() {
/* 171 */     return (class_2596<class_2602>)class_2622.method_38585(this);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public class_2487 method_16887(@NotNull class_7225.class_7874 registries) {
/* 176 */     return method_38244(registries);
/*     */   }
/*     */ 
/*     */   
/*     */   public void method_5431() {
/* 181 */     super.method_5431();
/* 182 */     if (this.field_11863 != null && !this.field_11863.field_9236) {
/* 183 */       this.field_11863.method_8413(this.field_11867, method_11010(), method_11010(), 2);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void registerControllers(@NotNull AnimatableManager.ControllerRegistrar controllers) {
/* 189 */     controllers.add(new AnimationController((GeoAnimatable)this, "controller", 0, state -> state.setAndContinue(RawAnimation.begin().thenLoop("idle"))));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public AnimatableInstanceCache getAnimatableInstanceCache() {
/* 196 */     return this.cache;
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\block\statue\entity\StatueBlockEntity.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */