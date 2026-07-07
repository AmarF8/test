/*    */ package com.atlas.common.fabric.effect.codec;
/*    */ 
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*    */ import net.minecraft.class_243;
/*    */ import net.minecraft.class_2960;
/*    */ import net.minecraft.class_3414;
/*    */ 
/*    */ public final class SoundCodec extends Record {
/*    */   private final String id;
/*    */   private final float vol;
/*    */   private final float pitch;
/*    */   public static final Codec<SoundCodec> CODEC;
/*    */   
/*    */   public final String toString() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/effect/codec/SoundCodec;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #24	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/fabric/effect/codec/SoundCodec;
/*    */   }
/*    */   
/*    */   public final int hashCode() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/effect/codec/SoundCodec;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #24	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/fabric/effect/codec/SoundCodec;
/*    */   }
/*    */   
/* 24 */   public SoundCodec(String id, float vol, float pitch) { this.id = id; this.vol = vol; this.pitch = pitch; } public String id() { return this.id; } public final boolean equals(Object o) { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/effect/codec/SoundCodec;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #24	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lcom/atlas/common/fabric/effect/codec/SoundCodec;
/* 24 */     //   0	8	1	o	Ljava/lang/Object; } public float vol() { return this.vol; } public float pitch() { return this.pitch; }
/*    */ 
/*    */ 
/*    */   
/*    */   static {
/* 29 */     CODEC = RecordCodecBuilder.create(instance -> instance.group((App)Codec.STRING.fieldOf("sound_id").forGetter(SoundCodec::id), (App)Codec.FLOAT.optionalFieldOf("vol", Float.valueOf(1.0F)).forGetter(SoundCodec::vol), (App)Codec.FLOAT.optionalFieldOf("pitch", Float.valueOf(1.0F)).forGetter(SoundCodec::pitch)).apply((Applicative)instance, SoundCodec::new));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void play(PokemonEntity context) {
/* 36 */     class_2960 soundId = class_2960.method_12829(this.id);
/* 37 */     if (soundId == null) {
/*    */       return;
/*    */     }
/*    */ 
/*    */     
/* 42 */     class_3414 registered = (class_3414)class_7923.field_41172.method_10223(soundId);
/* 43 */     class_3414 soundEvent = (registered != null) ? registered : class_3414.method_47908(soundId);
/*    */     
/* 45 */     class_243 pos = context.method_19538();
/* 46 */     context.method_37908().method_43128(null, pos.field_1352, pos.field_1351, pos.field_1350, soundEvent, class_3419.field_15248, this.vol, this.pitch);
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\effect\codec\SoundCodec.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */