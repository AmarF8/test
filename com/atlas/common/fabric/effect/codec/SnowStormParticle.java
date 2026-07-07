/*     */ package com.atlas.common.fabric.effect.codec;
/*     */ 
/*     */ import com.cobblemon.mod.common.battles.pokemon.BattlePokemon;
/*     */ import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
/*     */ import com.mojang.datafixers.kinds.App;
/*     */ import com.mojang.serialization.Codec;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import kotlin.Unit;
/*     */ 
/*     */ public final class SnowStormParticle extends Record {
/*     */   private final Optional<List<String>> source_apply;
/*     */   private final Optional<List<String>> target_apply;
/*     */   private final Optional<List<String>> source_revert;
/*     */   private final Optional<List<String>> target_revert;
/*     */   private final Optional<String> particle_apply;
/*     */   private final Optional<Float> apply_after;
/*     */   
/*  19 */   public SnowStormParticle(Optional<List<String>> source_apply, Optional<List<String>> target_apply, Optional<List<String>> source_revert, Optional<List<String>> target_revert, Optional<String> particle_apply, Optional<Float> apply_after, Optional<String> particle_revert, Optional<Float> revert_after, Optional<SoundCodec> sound_apply, Optional<SoundCodec> sound_revert, Optional<AnimationData> animations) { this.source_apply = source_apply; this.target_apply = target_apply; this.source_revert = source_revert; this.target_revert = target_revert; this.particle_apply = particle_apply; this.apply_after = apply_after; this.particle_revert = particle_revert; this.revert_after = revert_after; this.sound_apply = sound_apply; this.sound_revert = sound_revert; this.animations = animations; } private final Optional<String> particle_revert; private final Optional<Float> revert_after; private final Optional<SoundCodec> sound_apply; private final Optional<SoundCodec> sound_revert; private final Optional<AnimationData> animations; public static final Codec<SnowStormParticle> CODEC; public final String toString() { // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/effect/codec/SnowStormParticle;)Ljava/lang/String;
/*     */     //   6: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #19	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	7	0	this	Lcom/atlas/common/fabric/effect/codec/SnowStormParticle; } public final int hashCode() { // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/effect/codec/SnowStormParticle;)I
/*     */     //   6: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #19	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	7	0	this	Lcom/atlas/common/fabric/effect/codec/SnowStormParticle; } public final boolean equals(Object o) { // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/effect/codec/SnowStormParticle;Ljava/lang/Object;)Z
/*     */     //   7: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #19	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	8	0	this	Lcom/atlas/common/fabric/effect/codec/SnowStormParticle;
/*  19 */     //   0	8	1	o	Ljava/lang/Object; } public Optional<List<String>> source_apply() { return this.source_apply; } public Optional<List<String>> target_apply() { return this.target_apply; } public Optional<List<String>> source_revert() { return this.source_revert; } public Optional<List<String>> target_revert() { return this.target_revert; } public Optional<String> particle_apply() { return this.particle_apply; } public Optional<Float> apply_after() { return this.apply_after; } public Optional<String> particle_revert() { return this.particle_revert; } public Optional<Float> revert_after() { return this.revert_after; } public Optional<SoundCodec> sound_apply() { return this.sound_apply; } public Optional<SoundCodec> sound_revert() { return this.sound_revert; } public Optional<AnimationData> animations() { return this.animations; }
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
/*     */   static {
/*  32 */     CODEC = RecordCodecBuilder.create(instance -> instance.group((App)Codec.list((Codec)Codec.STRING).optionalFieldOf("source_apply").forGetter(SnowStormParticle::source_apply), (App)Codec.list((Codec)Codec.STRING).optionalFieldOf("target_apply").forGetter(SnowStormParticle::target_apply), (App)Codec.list((Codec)Codec.STRING).optionalFieldOf("source_revert").forGetter(SnowStormParticle::source_revert), (App)Codec.list((Codec)Codec.STRING).optionalFieldOf("target_revert").forGetter(SnowStormParticle::target_revert), (App)Codec.STRING.optionalFieldOf("particle_apply").forGetter(SnowStormParticle::particle_apply), (App)Codec.FLOAT.optionalFieldOf("apply_after").forGetter(SnowStormParticle::apply_after), (App)Codec.STRING.optionalFieldOf("particle_revert").forGetter(SnowStormParticle::particle_revert), (App)Codec.FLOAT.optionalFieldOf("revert_after").forGetter(SnowStormParticle::revert_after), (App)SoundCodec.CODEC.optionalFieldOf("sound_apply").forGetter(SnowStormParticle::sound_apply), (App)SoundCodec.CODEC.optionalFieldOf("sound_revert").forGetter(SnowStormParticle::sound_revert), (App)AnimationData.CODEC.optionalFieldOf("animations").forGetter(SnowStormParticle::animations)).apply((Applicative)instance, SnowStormParticle::new));
/*     */   }
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
/*     */   public void apply(PokemonEntity context, List<String> aspects, Optional<String> properties, PokemonEntity other) {
/*  47 */     process(context, aspects, properties, other, null, true);
/*     */   }
/*     */   
/*     */   public void revert(PokemonEntity context, List<String> aspects, Optional<String> properties, PokemonEntity other) {
/*  51 */     process(context, aspects, properties, other, null, false);
/*     */   }
/*     */   
/*     */   public void applyBattle(PokemonEntity context, List<String> aspects, Optional<String> properties, PokemonEntity other, BattlePokemon battlePokemon, float battlePause) {
/*  55 */     battlePokemon.getActor().getBattle().dispatchWaitingToFront(battlePause, () -> Unit.INSTANCE);
/*  56 */     process(context, aspects, properties, other, battlePokemon, true);
/*     */   }
/*     */   
/*     */   public void revertBattle(PokemonEntity context, List<String> aspects, Optional<String> properties, PokemonEntity other, BattlePokemon battlePokemon, float battlePause) {
/*  60 */     battlePokemon.getActor().getBattle().dispatchWaitingToFront(battlePause, () -> Unit.INSTANCE);
/*  61 */     process(context, aspects, properties, other, battlePokemon, false);
/*     */   }
/*     */ 
/*     */   
/*     */   private void process(PokemonEntity context, List<String> aspects, Optional<String> properties, PokemonEntity other, BattlePokemon battlePokemon, boolean isApply) {
/*  66 */     context.method_5977(true);
/*     */     
/*  68 */     class_2487 persistentData = context.getPokemon().getPersistentData();
/*  69 */     persistentData.method_10556("form_changing", true);
/*     */     
/*  71 */     Optional<String> particle = isApply ? this.particle_apply : this.particle_revert;
/*  72 */     Optional<List<String>> sourceLocators = isApply ? this.source_apply : this.source_revert;
/*  73 */     Optional<List<String>> targetLocators = isApply ? this.target_apply : this.target_revert;
/*  74 */     Optional<Float> delay = isApply ? this.apply_after : this.revert_after;
/*     */     
/*  76 */     playSound(context, isApply);
/*  77 */     this.animations.ifPresent(anim -> { if (isApply) { anim.applyAnimations(context); }
/*     */           else
/*     */           { anim.revertAnimations(context); }
/*     */         
/*  81 */         }); if (particle.isPresent()) {
/*  82 */       class_2960 particleId = class_2960.method_12829(particle.get());
/*  83 */       if (particleId != null) {
/*  84 */         spawnParticle(context, particleId, sourceLocators.orElse(null), other, targetLocators.orElse(null));
/*     */       }
/*     */     } 
/*     */     
/*  88 */     if (delay.isPresent()) {
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
/*  99 */       SchedulingFunctionsKt.afterOnServer(((Float)delay.get()).floatValue(), () -> {
/*     */             applyAndCleanup(context, aspects, properties, persistentData, battlePokemon);
/*     */             return Unit.INSTANCE;
/*     */           });
/*     */     } else {
/* 104 */       applyAndCleanup(context, aspects, properties, persistentData, battlePokemon);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void spawnParticle(PokemonEntity entity, class_2960 particleId, List<String> sourceLocators, PokemonEntity targetEntity, List<String> targetLocators) {
/* 109 */     (new SpawnSnowstormEntityParticlePacket(particleId, entity
/*     */         
/* 111 */         .method_5628(), 
/* 112 */         (sourceLocators != null) ? sourceLocators : List.of(), 
/* 113 */         (targetEntity != null) ? Integer.valueOf(targetEntity.method_5628()) : null, targetLocators))
/*     */       
/* 115 */       .sendToAllPlayers();
/*     */   }
/*     */   
/* 118 */   private static final Logger LOGGER = LoggerFactory.getLogger("atlas-effect");
/*     */ 
/*     */   
/*     */   private void applyAndCleanup(PokemonEntity context, List<String> aspects, Optional<String> properties, class_2487 persistentData, BattlePokemon battlePokemon) {
/*     */     try {
/* 123 */       String species = context.getPokemon().getSpecies().getName();
/* 124 */       LOGGER.info("[effect][cleanup] {} (uuid={}) running applyAndCleanup aspects={} entityRemoved={}", new Object[] { species, context
/* 125 */             .getPokemon().getUuid(), aspects, Boolean.valueOf(context.method_31481()) });
/* 126 */       AspectUtils.applyAspects(context.getPokemon(), aspects);
/* 127 */       AspectUtils.applyProperties(context.getPokemon(), properties);
/* 128 */       if (battlePokemon != null) {
/* 129 */         AspectUtils.updatePackets(battlePokemon);
/*     */       }
/* 131 */       context.method_5977(false);
/* 132 */       persistentData.method_10551("form_changing");
/* 133 */       LOGGER.info("[effect][cleanup] {} (uuid={}) done. form={} aspects={}", new Object[] { species, context
/* 134 */             .getPokemon().getUuid(), context
/* 135 */             .getPokemon().getForm().getName(), context.getPokemon().getAspects() });
/* 136 */     } catch (Throwable t) {
/*     */ 
/*     */       
/* 139 */       LOGGER.error("[effect][cleanup] threw — pokemon will stay stuck in old form", t);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void playSound(PokemonEntity context, boolean isApply) {
/* 144 */     Optional<SoundCodec> sound = isApply ? this.sound_apply : this.sound_revert;
/* 145 */     sound.ifPresent(s -> s.play(context));
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\effect\codec\SnowStormParticle.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */