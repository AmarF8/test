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
/*    */ public final class Abilities
/*    */   extends Record
/*    */ {
/*    */   private final List<String> required_abilities;
/*    */   private final List<String> blacklist_abilities;
/*    */   public static final Codec<Abilities> CODEC;
/*    */   
/*    */   public final String toString() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/effect/codec/Conditions$Abilities;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #65	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/fabric/effect/codec/Conditions$Abilities;
/*    */   }
/*    */   
/*    */   public final int hashCode() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/effect/codec/Conditions$Abilities;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #65	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/fabric/effect/codec/Conditions$Abilities;
/*    */   }
/*    */   
/*    */   public final boolean equals(Object o) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/effect/codec/Conditions$Abilities;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #65	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lcom/atlas/common/fabric/effect/codec/Conditions$Abilities;
/*    */     //   0	8	1	o	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public Abilities(List<String> required_abilities, List<String> blacklist_abilities) {
/* 65 */     this.required_abilities = required_abilities; this.blacklist_abilities = blacklist_abilities; } public List<String> required_abilities() { return this.required_abilities; } public List<String> blacklist_abilities() { return this.blacklist_abilities; } static {
/* 66 */     CODEC = RecordCodecBuilder.create(i -> i.group((App)Codec.list((Codec)Codec.STRING).optionalFieldOf("required_abilities", List.of()).forGetter(Abilities::required_abilities), (App)Codec.list((Codec)Codec.STRING).optionalFieldOf("blacklist_abilities", List.of()).forGetter(Abilities::blacklist_abilities)).apply((Applicative)i, Abilities::new));
/*    */   }
/*    */   
/*    */   public static Abilities DEFAULT() {
/* 70 */     return new Abilities(List.of(), List.of());
/*    */   } public boolean validate(Pokemon p) {
/* 72 */     if (!this.blacklist_abilities.isEmpty() && this.blacklist_abilities.contains(p.getAbility().getName())) return false; 
/* 73 */     return (this.required_abilities.isEmpty() || this.required_abilities.contains(p.getAbility().getName()));
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\effect\codec\Conditions$Abilities.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */