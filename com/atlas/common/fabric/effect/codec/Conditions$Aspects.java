/*    */ package com.atlas.common.fabric.effect.codec;
/*    */ 
/*    */ import com.cobblemon.mod.common.pokemon.Pokemon;
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.datafixers.kinds.Applicative;
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*    */ import java.util.List;
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
/*    */ public final class Aspects
/*    */   extends Record
/*    */ {
/*    */   private final List<List<String>> required_aspects;
/*    */   private final List<List<String>> blacklist_aspects;
/*    */   public static final Codec<Aspects> CODEC;
/*    */   
/*    */   public final String toString() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/effect/codec/Conditions$Aspects;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #77	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/fabric/effect/codec/Conditions$Aspects;
/*    */   }
/*    */   
/*    */   public final int hashCode() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/effect/codec/Conditions$Aspects;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #77	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/fabric/effect/codec/Conditions$Aspects;
/*    */   }
/*    */   
/*    */   public final boolean equals(Object o) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/effect/codec/Conditions$Aspects;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #77	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lcom/atlas/common/fabric/effect/codec/Conditions$Aspects;
/*    */     //   0	8	1	o	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public Aspects(List<List<String>> required_aspects, List<List<String>> blacklist_aspects) {
/* 77 */     this.required_aspects = required_aspects; this.blacklist_aspects = blacklist_aspects; } public List<List<String>> required_aspects() { return this.required_aspects; } public List<List<String>> blacklist_aspects() { return this.blacklist_aspects; } static {
/* 78 */     CODEC = RecordCodecBuilder.create(i -> i.group((App)Codec.list(Codec.list((Codec)Codec.STRING)).optionalFieldOf("required_aspects", List.of()).forGetter(Aspects::required_aspects), (App)Codec.list(Codec.list((Codec)Codec.STRING)).optionalFieldOf("blacklist_aspects", List.of()).forGetter(Aspects::blacklist_aspects)).apply((Applicative)i, Aspects::new));
/*    */   }
/*    */   
/*    */   public static Aspects DEFAULT() {
/* 82 */     return new Aspects(List.of(), List.of());
/*    */   } public boolean validate(Pokemon p) {
/* 84 */     if (!this.blacklist_aspects.isEmpty() && this.blacklist_aspects.stream().anyMatch(g -> p.getAspects().containsAll(g))) return false; 
/* 85 */     return (this.required_aspects.isEmpty() || this.required_aspects.stream().anyMatch(g -> p.getAspects().containsAll(g)));
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\effect\codec\Conditions$Aspects.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */