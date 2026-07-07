/*    */ package com.atlas.common.fabric.effect.codec;
/*    */ public final class AspectConditions extends Record {
/*    */   private final Conditions aspectApply;
/*    */   private final Conditions aspectRevert;
/*    */   public static final Codec<AspectConditions> CODEC;
/*    */   
/*  7 */   public AspectConditions(Conditions aspectApply, Conditions aspectRevert) { this.aspectApply = aspectApply; this.aspectRevert = aspectRevert; } public final String toString() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/effect/codec/AspectConditions;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #7	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*  7 */     //   0	7	0	this	Lcom/atlas/common/fabric/effect/codec/AspectConditions; } public Conditions aspectApply() { return this.aspectApply; } public final int hashCode() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/effect/codec/AspectConditions;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #7	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/fabric/effect/codec/AspectConditions; } public final boolean equals(Object o) { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/effect/codec/AspectConditions;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #7	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lcom/atlas/common/fabric/effect/codec/AspectConditions;
/*  7 */     //   0	8	1	o	Ljava/lang/Object; } public Conditions aspectRevert() { return this.aspectRevert; }
/*    */ 
/*    */   
/*    */   static {
/* 11 */     CODEC = RecordCodecBuilder.create(instance -> instance.group((App)Conditions.CODEC.optionalFieldOf("apply", Conditions.DEFAULT()).forGetter(AspectConditions::aspectApply), (App)Conditions.CODEC.optionalFieldOf("revert", Conditions.DEFAULT()).forGetter(AspectConditions::aspectRevert)).apply((Applicative)instance, AspectConditions::new));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static AspectConditions DEFAULT() {
/* 17 */     return new AspectConditions(Conditions.DEFAULT(), Conditions.DEFAULT());
/*    */   }
/*    */   
/* 20 */   public boolean validate_apply(Pokemon pokemon) { return this.aspectApply.validate(pokemon); } public boolean validate_revert(Pokemon pokemon) {
/* 21 */     return this.aspectRevert.validate(pokemon);
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\effect\codec\AspectConditions.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */