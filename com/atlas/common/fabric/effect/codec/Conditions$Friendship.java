/*    */ package com.atlas.common.fabric.effect.codec;
/*    */ 
/*    */ import com.cobblemon.mod.common.pokemon.Pokemon;
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.datafixers.kinds.Applicative;
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*    */ import java.util.function.BiFunction;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class Friendship
/*    */   extends Record
/*    */ {
/*    */   private final Integer min_friendship;
/*    */   private final Integer max_friendship;
/*    */   public static final Codec<Friendship> CODEC;
/*    */   
/*    */   public final String toString() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/effect/codec/Conditions$Friendship;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #56	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/fabric/effect/codec/Conditions$Friendship;
/*    */   }
/*    */   
/*    */   public final int hashCode() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/effect/codec/Conditions$Friendship;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #56	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/fabric/effect/codec/Conditions$Friendship;
/*    */   }
/*    */   
/*    */   public final boolean equals(Object o) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/effect/codec/Conditions$Friendship;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #56	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lcom/atlas/common/fabric/effect/codec/Conditions$Friendship;
/*    */     //   0	8	1	o	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public Friendship(Integer min_friendship, Integer max_friendship) {
/* 56 */     this.min_friendship = min_friendship; this.max_friendship = max_friendship; } public Integer min_friendship() { return this.min_friendship; } public Integer max_friendship() { return this.max_friendship; } static {
/* 57 */     CODEC = RecordCodecBuilder.create(i -> i.group((App)Codec.INT.optionalFieldOf("min_friendship", Integer.valueOf(0)).forGetter(Friendship::min_friendship), (App)Codec.INT.optionalFieldOf("max_friendship", Integer.valueOf(255)).forGetter(Friendship::max_friendship)).apply((Applicative)i, Friendship::new));
/*    */   }
/*    */   
/*    */   public static Friendship DEFAULT() {
/* 61 */     return new Friendship(Integer.valueOf(0), Integer.valueOf(255)); } public boolean validate(Pokemon p) {
/* 62 */     return (p.getFriendship() >= this.min_friendship.intValue() && p.getFriendship() <= this.max_friendship.intValue());
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\effect\codec\Conditions$Friendship.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */