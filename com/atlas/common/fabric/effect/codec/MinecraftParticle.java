/*    */ package com.atlas.common.fabric.effect.codec;
/*    */ 
/*    */ import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
/*    */ import java.util.Optional;
/*    */ 
/*    */ public final class MinecraftParticle extends Record {
/*    */   private final Optional<String> particle_apply;
/*    */   private final Optional<String> particle_revert;
/*    */   private final Optional<SoundCodec> sound_apply;
/*    */   private final Optional<SoundCodec> sound_revert;
/*    */   private final Optional<Float> particle_apply_amplifier;
/*    */   private final Optional<Float> particle_revert_amplifier;
/*    */   private final Optional<AnimationData> animations;
/*    */   public static final Codec<MinecraftParticle> CODEC;
/*    */   
/* 16 */   public MinecraftParticle(Optional<String> particle_apply, Optional<String> particle_revert, Optional<SoundCodec> sound_apply, Optional<SoundCodec> sound_revert, Optional<Float> particle_apply_amplifier, Optional<Float> particle_revert_amplifier, Optional<AnimationData> animations) { this.particle_apply = particle_apply; this.particle_revert = particle_revert; this.sound_apply = sound_apply; this.sound_revert = sound_revert; this.particle_apply_amplifier = particle_apply_amplifier; this.particle_revert_amplifier = particle_revert_amplifier; this.animations = animations; } public final String toString() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/effect/codec/MinecraftParticle;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #16	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/* 16 */     //   0	7	0	this	Lcom/atlas/common/fabric/effect/codec/MinecraftParticle; } public Optional<String> particle_apply() { return this.particle_apply; } public final int hashCode() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/effect/codec/MinecraftParticle;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #16	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/fabric/effect/codec/MinecraftParticle; } public final boolean equals(Object o) { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/effect/codec/MinecraftParticle;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #16	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lcom/atlas/common/fabric/effect/codec/MinecraftParticle;
/* 16 */     //   0	8	1	o	Ljava/lang/Object; } public Optional<String> particle_revert() { return this.particle_revert; } public Optional<SoundCodec> sound_apply() { return this.sound_apply; } public Optional<SoundCodec> sound_revert() { return this.sound_revert; } public Optional<Float> particle_apply_amplifier() { return this.particle_apply_amplifier; } public Optional<Float> particle_revert_amplifier() { return this.particle_revert_amplifier; } public Optional<AnimationData> animations() { return this.animations; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   static {
/* 25 */     CODEC = RecordCodecBuilder.create(instance -> instance.group((App)Codec.STRING.optionalFieldOf("particle_apply").forGetter(MinecraftParticle::particle_apply), (App)Codec.STRING.optionalFieldOf("particle_revert").forGetter(MinecraftParticle::particle_revert), (App)SoundCodec.CODEC.optionalFieldOf("sound_apply").forGetter(MinecraftParticle::sound_apply), (App)SoundCodec.CODEC.optionalFieldOf("sound_revert").forGetter(MinecraftParticle::sound_revert), (App)Codec.FLOAT.optionalFieldOf("particle_apply_amplifier").forGetter(MinecraftParticle::particle_apply_amplifier), (App)Codec.FLOAT.optionalFieldOf("particle_revert_amplifier").forGetter(MinecraftParticle::particle_revert_amplifier), (App)AnimationData.CODEC.optionalFieldOf("animations").forGetter(MinecraftParticle::animations)).apply((Applicative)instance, MinecraftParticle::new));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void apply(PokemonEntity context) {
/* 36 */     process(context, true);
/*    */   }
/*    */   
/*    */   public void revert(PokemonEntity context) {
/* 40 */     process(context, false);
/*    */   }
/*    */   private void process(PokemonEntity context, boolean isApply) {
/*    */     class_3218 serverWorld;
/* 44 */     if (context != null) { class_1937 class_1937 = context.method_37908(); if (class_1937 instanceof class_3218) { serverWorld = (class_3218)class_1937; } else { return; }  }
/*    */     else { return; }
/* 46 */      this.animations.ifPresent(anim -> { if (isApply) { anim.applyAnimations(context); }
/*    */           else
/*    */           { anim.revertAnimations(context); }
/*    */         
/* 50 */         }); Optional<SoundCodec> sound = isApply ? this.sound_apply : this.sound_revert;
/* 51 */     sound.ifPresent(s -> s.play(context));
/*    */     
/* 53 */     Optional<String> particleId = isApply ? this.particle_apply : this.particle_revert;
/* 54 */     Optional<Float> amplifierOpt = isApply ? this.particle_apply_amplifier : this.particle_revert_amplifier;
/*    */     
/* 56 */     particleId.ifPresent(id -> {
/*    */           class_2394 particle;
/*    */           class_2960 loc = class_2960.method_12829(id);
/*    */           if (loc == null)
/*    */             return; 
/*    */           class_2396<?> particleType = (class_2396)class_7923.field_41180.method_10223(loc);
/*    */           if (particleType instanceof class_2394) {
/*    */             particle = (class_2394)particleType;
/*    */           } else {
/*    */             LoggerFactory.getLogger(MinecraftParticle.class).warn("Unknown vanilla particle: {}", id);
/*    */             return;
/*    */           } 
/*    */           double w = context.method_17681();
/*    */           double h = context.method_17682();
/*    */           float amplifier = Math.max(1.0F, ((Float)amplifierOpt.orElse(Float.valueOf(1.0F))).floatValue());
/*    */           int count = (int)(100.0D * w * h * amplifier);
/*    */           double radius = w * (0.8D + amplifier * 0.1D);
/*    */           class_243 pos = context.method_19538();
/*    */           for (int i = 0; i < count; i++) {
/*    */             double angle = Math.random() * 2.0D * Math.PI;
/*    */             double xOff = Math.cos(angle) * radius;
/*    */             double zOff = Math.sin(angle) * radius;
/*    */             double yOff = Math.random() * h;
/*    */             serverWorld.method_14199(particle, pos.field_1352 + xOff, pos.field_1351 + yOff, pos.field_1350 + zOff, 1, 0.0D, 0.0D, 0.0D, 0.1D);
/*    */           } 
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\effect\codec\MinecraftParticle.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */