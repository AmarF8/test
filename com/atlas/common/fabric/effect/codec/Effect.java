/*     */ package com.atlas.common.fabric.effect.codec;
/*     */ 
/*     */ import com.atlas.common.fabric.effect.AspectUtils;
/*     */ import com.cobblemon.mod.common.battles.pokemon.BattlePokemon;
/*     */ import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
/*     */ import com.cobblemon.mod.common.pokemon.Pokemon;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ 
/*     */ public final class Effect extends Record {
/*     */   private final Optional<MinecraftParticle> minecraft;
/*     */   private final Optional<SnowStormParticle> snowStorm;
/*     */   private final Optional<Float> battle_pause_apply;
/*     */   private final Optional<Float> battle_pause_revert;
/*     */   public static final Codec<Effect> CODEC;
/*     */   
/*  17 */   public Effect(Optional<MinecraftParticle> minecraft, Optional<SnowStormParticle> snowStorm, Optional<Float> battle_pause_apply, Optional<Float> battle_pause_revert) { this.minecraft = minecraft; this.snowStorm = snowStorm; this.battle_pause_apply = battle_pause_apply; this.battle_pause_revert = battle_pause_revert; } public final String toString() { // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/effect/codec/Effect;)Ljava/lang/String;
/*     */     //   6: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #17	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*  17 */     //   0	7	0	this	Lcom/atlas/common/fabric/effect/codec/Effect; } public Optional<MinecraftParticle> minecraft() { return this.minecraft; } public final int hashCode() { // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/effect/codec/Effect;)I
/*     */     //   6: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #17	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	7	0	this	Lcom/atlas/common/fabric/effect/codec/Effect; } public final boolean equals(Object o) { // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/effect/codec/Effect;Ljava/lang/Object;)Z
/*     */     //   7: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #17	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	8	0	this	Lcom/atlas/common/fabric/effect/codec/Effect;
/*  17 */     //   0	8	1	o	Ljava/lang/Object; } public Optional<SnowStormParticle> snowStorm() { return this.snowStorm; } public Optional<Float> battle_pause_apply() { return this.battle_pause_apply; } public Optional<Float> battle_pause_revert() { return this.battle_pause_revert; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/*  23 */     CODEC = RecordCodecBuilder.create(instance -> instance.group((App)MinecraftParticle.CODEC.optionalFieldOf("minecraft").forGetter(Effect::minecraft), (App)SnowStormParticle.CODEC.optionalFieldOf("snowstorm").forGetter(Effect::snowStorm), (App)Codec.FLOAT.optionalFieldOf("battle_pause_apply").forGetter(Effect::battle_pause_apply), (App)Codec.FLOAT.optionalFieldOf("battle_pause_revert").forGetter(Effect::battle_pause_revert)).apply((Applicative)instance, Effect::new));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Effect empty() {
/*  31 */     return new Effect(Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty());
/*     */   }
/*     */   
/*     */   public static Effect get(@Nullable String effectId) {
/*  35 */     Effect found = EffectRegistry.getEffect(effectId);
/*  36 */     return (found != null) ? found : empty();
/*     */   }
/*     */   
/*     */   public static Effect get(@Nullable class_2960 effectId) {
/*  40 */     if (effectId == null) return empty(); 
/*  41 */     Effect found = EffectRegistry.getEffect(effectId);
/*  42 */     return (found != null) ? found : empty();
/*     */   }
/*     */   
/*     */   public void applyEffects(Pokemon context, List<String> aspects, Optional<String> properties, @Nullable PokemonEntity other) {
/*  46 */     if (context.getEntity() == null) {
/*  47 */       AspectUtils.applyAspects(context, aspects);
/*  48 */       AspectUtils.applyProperties(context, properties);
/*     */       return;
/*     */     } 
/*  51 */     if (this.snowStorm.isPresent() && this.minecraft.isPresent()) {
/*  52 */       ((MinecraftParticle)this.minecraft.get()).apply(context.getEntity());
/*  53 */       ((SnowStormParticle)this.snowStorm.get()).apply(context.getEntity(), aspects, properties, other);
/*  54 */     } else if (this.minecraft.isPresent()) {
/*  55 */       ((MinecraftParticle)this.minecraft.get()).apply(context.getEntity());
/*  56 */       AspectUtils.applyAspects(context, aspects);
/*  57 */       AspectUtils.applyProperties(context, properties);
/*  58 */     } else if (this.snowStorm.isPresent()) {
/*  59 */       ((SnowStormParticle)this.snowStorm.get()).apply(context.getEntity(), aspects, properties, other);
/*     */     } else {
/*  61 */       AspectUtils.applyAspects(context, aspects);
/*  62 */       AspectUtils.applyProperties(context, properties);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void revertEffects(Pokemon context, List<String> aspects, Optional<String> properties, @Nullable PokemonEntity other) {
/*  67 */     if (context.getEntity() == null) {
/*  68 */       AspectUtils.applyAspects(context, aspects);
/*  69 */       AspectUtils.applyProperties(context, properties);
/*     */       return;
/*     */     } 
/*  72 */     if (this.snowStorm.isPresent() && this.minecraft.isPresent()) {
/*  73 */       ((MinecraftParticle)this.minecraft.get()).revert(context.getEntity());
/*  74 */       ((SnowStormParticle)this.snowStorm.get()).revert(context.getEntity(), aspects, properties, other);
/*  75 */     } else if (this.minecraft.isPresent()) {
/*  76 */       ((MinecraftParticle)this.minecraft.get()).revert(context.getEntity());
/*  77 */       AspectUtils.applyAspects(context, aspects);
/*  78 */       AspectUtils.applyProperties(context, properties);
/*  79 */     } else if (this.snowStorm.isPresent()) {
/*  80 */       ((SnowStormParticle)this.snowStorm.get()).revert(context.getEntity(), aspects, properties, other);
/*     */     } else {
/*  82 */       AspectUtils.applyAspects(context, aspects);
/*  83 */       AspectUtils.applyProperties(context, properties);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void applyEffectsBattle(Pokemon context, List<String> aspects, Optional<String> properties, @Nullable PokemonEntity other, BattlePokemon battlePokemon) {
/*  89 */     if (context.getEntity() == null) {
/*  90 */       AspectUtils.applyAspects(context, aspects);
/*  91 */       AspectUtils.applyProperties(context, properties);
/*  92 */       AspectUtils.updatePackets(battlePokemon);
/*     */       return;
/*     */     } 
/*  95 */     float pause = ((Float)this.battle_pause_apply.orElse(Float.valueOf(1.0F))).floatValue();
/*  96 */     if (this.snowStorm.isPresent() && this.minecraft.isPresent()) {
/*  97 */       ((MinecraftParticle)this.minecraft.get()).apply(context.getEntity());
/*  98 */       ((SnowStormParticle)this.snowStorm.get()).applyBattle(context.getEntity(), aspects, properties, other, battlePokemon, pause);
/*  99 */     } else if (this.minecraft.isPresent()) {
/* 100 */       ((MinecraftParticle)this.minecraft.get()).apply(context.getEntity());
/* 101 */       AspectUtils.applyAspects(context, aspects);
/* 102 */       AspectUtils.applyProperties(context, properties);
/* 103 */       AspectUtils.updatePackets(battlePokemon);
/* 104 */     } else if (this.snowStorm.isPresent()) {
/* 105 */       ((SnowStormParticle)this.snowStorm.get()).applyBattle(context.getEntity(), aspects, properties, other, battlePokemon, pause);
/*     */     } else {
/* 107 */       AspectUtils.applyAspects(context, aspects);
/* 108 */       AspectUtils.applyProperties(context, properties);
/* 109 */       AspectUtils.updatePackets(battlePokemon);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void revertEffectsBattle(Pokemon context, List<String> aspects, Optional<String> properties, @Nullable PokemonEntity other, BattlePokemon battlePokemon) {
/* 115 */     if (context.getEntity() == null) {
/* 116 */       AspectUtils.applyAspects(context, aspects);
/* 117 */       AspectUtils.applyProperties(context, properties);
/* 118 */       AspectUtils.updatePackets(battlePokemon);
/*     */       return;
/*     */     } 
/* 121 */     float pause = ((Float)this.battle_pause_revert.orElse(Float.valueOf(1.0F))).floatValue();
/* 122 */     if (this.snowStorm.isPresent() && this.minecraft.isPresent()) {
/* 123 */       ((MinecraftParticle)this.minecraft.get()).revert(context.getEntity());
/* 124 */       ((SnowStormParticle)this.snowStorm.get()).revertBattle(context.getEntity(), aspects, properties, other, battlePokemon, pause);
/* 125 */     } else if (this.minecraft.isPresent()) {
/* 126 */       ((MinecraftParticle)this.minecraft.get()).revert(context.getEntity());
/* 127 */       AspectUtils.applyAspects(context, aspects);
/* 128 */       AspectUtils.applyProperties(context, properties);
/* 129 */       AspectUtils.updatePackets(battlePokemon);
/* 130 */     } else if (this.snowStorm.isPresent()) {
/* 131 */       ((SnowStormParticle)this.snowStorm.get()).revertBattle(context.getEntity(), aspects, properties, other, battlePokemon, pause);
/*     */     } else {
/* 133 */       AspectUtils.applyAspects(context, aspects);
/* 134 */       AspectUtils.applyProperties(context, properties);
/* 135 */       AspectUtils.updatePackets(battlePokemon);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\effect\codec\Effect.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */