/*     */ package com.atlas.common.fabric.effect.codec;
/*     */ 
/*     */ import com.cobblemon.mod.common.api.moves.Move;
/*     */ import com.cobblemon.mod.common.pokemon.Pokemon;
/*     */ import com.mojang.datafixers.kinds.App;
/*     */ import com.mojang.datafixers.kinds.Applicative;
/*     */ import com.mojang.serialization.Codec;
/*     */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import java.util.Set;
/*     */ import java.util.function.BiFunction;
/*     */ import java.util.stream.Collectors;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Moves
/*     */   extends Record
/*     */ {
/*     */   private final List<List<String>> required_moves;
/*     */   private final List<List<String>> blacklist_moves;
/*     */   public static final Codec<Moves> CODEC;
/*     */   
/*     */   public final String toString() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/effect/codec/Conditions$Moves;)Ljava/lang/String;
/*     */     //   6: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #89	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	7	0	this	Lcom/atlas/common/fabric/effect/codec/Conditions$Moves;
/*     */   }
/*     */   
/*     */   public final int hashCode() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/effect/codec/Conditions$Moves;)I
/*     */     //   6: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #89	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	7	0	this	Lcom/atlas/common/fabric/effect/codec/Conditions$Moves;
/*     */   }
/*     */   
/*     */   public final boolean equals(Object o) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/effect/codec/Conditions$Moves;Ljava/lang/Object;)Z
/*     */     //   7: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #89	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	8	0	this	Lcom/atlas/common/fabric/effect/codec/Conditions$Moves;
/*     */     //   0	8	1	o	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public Moves(List<List<String>> required_moves, List<List<String>> blacklist_moves) {
/*  89 */     this.required_moves = required_moves; this.blacklist_moves = blacklist_moves; } public List<List<String>> required_moves() { return this.required_moves; } public List<List<String>> blacklist_moves() { return this.blacklist_moves; } static {
/*  90 */     CODEC = RecordCodecBuilder.create(i -> i.group((App)Codec.list(Codec.list((Codec)Codec.STRING)).optionalFieldOf("required_moves", List.of()).forGetter(Moves::required_moves), (App)Codec.list(Codec.list((Codec)Codec.STRING)).optionalFieldOf("blacklist_moves", List.of()).forGetter(Moves::blacklist_moves)).apply((Applicative)i, Moves::new));
/*     */   }
/*     */   
/*     */   public static Moves DEFAULT() {
/*  94 */     return new Moves(List.of(), List.of());
/*     */   } public boolean validate(Pokemon p) {
/*  96 */     if (this.blacklist_moves.isEmpty() && this.required_moves.isEmpty()) return true; 
/*  97 */     Set<String> moveNames = (Set<String>)p.getMoveSet().getMoves().stream().map(Move::getName).collect(Collectors.toSet());
/*  98 */     Objects.requireNonNull(moveNames); if (!this.blacklist_moves.isEmpty() && this.blacklist_moves.stream().anyMatch(moveNames::containsAll)) return false; 
/*  99 */     if (!this.required_moves.isEmpty()) { Objects.requireNonNull(moveNames); return this.required_moves.stream().anyMatch(moveNames::containsAll); }
/* 100 */      return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\effect\codec\Conditions$Moves.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */