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
/*    */ public final class Forms
/*    */   extends Record
/*    */ {
/*    */   private final List<String> required_forms;
/*    */   private final List<String> blacklist_forms;
/*    */   public static final Codec<Forms> CODEC;
/*    */   
/*    */   public final String toString() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/effect/codec/Conditions$Forms;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #44	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/fabric/effect/codec/Conditions$Forms;
/*    */   }
/*    */   
/*    */   public final int hashCode() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/effect/codec/Conditions$Forms;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #44	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/fabric/effect/codec/Conditions$Forms;
/*    */   }
/*    */   
/*    */   public final boolean equals(Object o) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/effect/codec/Conditions$Forms;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #44	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lcom/atlas/common/fabric/effect/codec/Conditions$Forms;
/*    */     //   0	8	1	o	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public Forms(List<String> required_forms, List<String> blacklist_forms) {
/* 44 */     this.required_forms = required_forms; this.blacklist_forms = blacklist_forms; } public List<String> required_forms() { return this.required_forms; } public List<String> blacklist_forms() { return this.blacklist_forms; } static {
/* 45 */     CODEC = RecordCodecBuilder.create(i -> i.group((App)Codec.list((Codec)Codec.STRING).optionalFieldOf("required_forms", List.of()).forGetter(Forms::required_forms), (App)Codec.list((Codec)Codec.STRING).optionalFieldOf("blacklist_forms", List.of()).forGetter(Forms::blacklist_forms)).apply((Applicative)i, Forms::new));
/*    */   }
/*    */   
/*    */   public static Forms DEFAULT() {
/* 49 */     return new Forms(List.of(), List.of());
/*    */   } public boolean validate(Pokemon p) {
/* 51 */     if (!this.blacklist_forms.isEmpty() && this.blacklist_forms.contains(p.getForm().getName())) return false; 
/* 52 */     return (this.required_forms.isEmpty() || this.required_forms.contains(p.getForm().getName()));
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\effect\codec\Conditions$Forms.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */