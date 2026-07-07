/*    */ package com.atlas.common.fabric.effect.codec;
/*    */ 
/*    */ public final class AnimationData extends Record {
/*    */   private final List<String> animations_apply;
/*    */   private final List<String> expressions_apply;
/*    */   private final List<String> animations_revert;
/*    */   private final List<String> expressions_revert;
/*    */   private final float applyDelay;
/*    */   private final float revertDelay;
/*    */   public static final Codec<AnimationData> CODEC;
/*    */   
/* 12 */   public AnimationData(List<String> animations_apply, List<String> expressions_apply, List<String> animations_revert, List<String> expressions_revert, float applyDelay, float revertDelay) { this.animations_apply = animations_apply; this.expressions_apply = expressions_apply; this.animations_revert = animations_revert; this.expressions_revert = expressions_revert; this.applyDelay = applyDelay; this.revertDelay = revertDelay; } public final String toString() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/effect/codec/AnimationData;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #12	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/* 12 */     //   0	7	0	this	Lcom/atlas/common/fabric/effect/codec/AnimationData; } public List<String> animations_apply() { return this.animations_apply; } public final int hashCode() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/effect/codec/AnimationData;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #12	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/fabric/effect/codec/AnimationData; } public final boolean equals(Object o) { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/effect/codec/AnimationData;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #12	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lcom/atlas/common/fabric/effect/codec/AnimationData;
/* 12 */     //   0	8	1	o	Ljava/lang/Object; } public List<String> expressions_apply() { return this.expressions_apply; } public List<String> animations_revert() { return this.animations_revert; } public List<String> expressions_revert() { return this.expressions_revert; } public float applyDelay() { return this.applyDelay; } public float revertDelay() { return this.revertDelay; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   static {
/* 20 */     CODEC = RecordCodecBuilder.create(instance -> instance.group((App)Codec.list((Codec)Codec.STRING).optionalFieldOf("animations_apply", List.of()).forGetter(AnimationData::animations_apply), (App)Codec.list((Codec)Codec.STRING).optionalFieldOf("expressions_apply", List.of()).forGetter(AnimationData::expressions_apply), (App)Codec.list((Codec)Codec.STRING).optionalFieldOf("animations_revert", List.of()).forGetter(AnimationData::animations_revert), (App)Codec.list((Codec)Codec.STRING).optionalFieldOf("expressions_revert", List.of()).forGetter(AnimationData::expressions_revert), (App)Codec.FLOAT.optionalFieldOf("apply_delay", Float.valueOf(0.0F)).forGetter(AnimationData::applyDelay), (App)Codec.FLOAT.optionalFieldOf("revert_delay", Float.valueOf(0.0F)).forGetter(AnimationData::revertDelay)).apply((Applicative)instance, AnimationData::new));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void applyAnimations(PokemonEntity context) {
/* 30 */     if (this.animations_apply.isEmpty() && this.expressions_apply.isEmpty())
/* 31 */       return;  context.after(this.applyDelay, () -> {
/*    */           sendAnimation(context, this.animations_apply, this.expressions_apply);
/*    */           return Unit.INSTANCE;
/*    */         });
/*    */   }
/*    */   
/*    */   public void revertAnimations(PokemonEntity context) {
/* 38 */     if (this.animations_revert.isEmpty() && this.expressions_revert.isEmpty())
/* 39 */       return;  context.after(this.revertDelay, () -> {
/*    */           sendAnimation(context, this.animations_revert, this.expressions_revert);
/*    */           return Unit.INSTANCE;
/*    */         });
/*    */   }
/*    */   
/*    */   private static void sendAnimation(PokemonEntity entity, List<String> animations, List<String> expressions) {
/* 46 */     PlayPosableAnimationPacket packet = new PlayPosableAnimationPacket(entity.method_5628(), new HashSet<>(animations), expressions);
/* 47 */     packet.sendToAllPlayers();
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\effect\codec\AnimationData.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */