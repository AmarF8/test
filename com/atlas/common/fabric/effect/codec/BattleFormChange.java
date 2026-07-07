/*    */ package com.atlas.common.fabric.effect.codec;public final class BattleFormChange extends Record { private final List<String> pokemons; private final String showdownFormChangeId; private final AspectConditions aspects;
/*    */   private final class_2960 effect;
/*    */   private final List<String> learnableMoves;
/*    */   private final Optional<String> item;
/*    */   private final Optional<String> forwardItem;
/*    */   private final Optional<Boolean> consumeItem;
/*    */   private final Optional<Boolean> equipForwardItem;
/*    */   public static final Codec<BattleFormChange> CODEC;
/*    */   
/* 10 */   public BattleFormChange(List<String> pokemons, String showdownFormChangeId, AspectConditions aspects, class_2960 effect, List<String> learnableMoves, Optional<String> item, Optional<String> forwardItem, Optional<Boolean> consumeItem, Optional<Boolean> equipForwardItem) { this.pokemons = pokemons; this.showdownFormChangeId = showdownFormChangeId; this.aspects = aspects; this.effect = effect; this.learnableMoves = learnableMoves; this.item = item; this.forwardItem = forwardItem; this.consumeItem = consumeItem; this.equipForwardItem = equipForwardItem; } public final String toString() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/effect/codec/BattleFormChange;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #10	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/* 10 */     //   0	7	0	this	Lcom/atlas/common/fabric/effect/codec/BattleFormChange; } public List<String> pokemons() { return this.pokemons; } public final int hashCode() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/effect/codec/BattleFormChange;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #10	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/fabric/effect/codec/BattleFormChange; } public final boolean equals(Object o) { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/effect/codec/BattleFormChange;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #10	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lcom/atlas/common/fabric/effect/codec/BattleFormChange;
/* 10 */     //   0	8	1	o	Ljava/lang/Object; } public String showdownFormChangeId() { return this.showdownFormChangeId; } public AspectConditions aspects() { return this.aspects; } public class_2960 effect() { return this.effect; } public List<String> learnableMoves() { return this.learnableMoves; } public Optional<String> item() { return this.item; } public Optional<String> forwardItem() { return this.forwardItem; } public Optional<Boolean> consumeItem() { return this.consumeItem; } public Optional<Boolean> equipForwardItem() { return this.equipForwardItem; }
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
/*    */   static {
/* 41 */     CODEC = RecordCodecBuilder.create(instance -> instance.group((App)Codec.STRING.listOf().fieldOf("pokemons").forGetter(BattleFormChange::pokemons), (App)Codec.STRING.fieldOf("showdown_form_change_id").forGetter(BattleFormChange::showdownFormChangeId), (App)AspectConditions.CODEC.fieldOf("aspect_conditions").forGetter(BattleFormChange::aspects), (App)class_2960.field_25139.optionalFieldOf("effect", class_2960.method_12829("atlas:empty")).forGetter(BattleFormChange::effect), (App)Codec.STRING.listOf().optionalFieldOf("learnable_moves", List.of()).forGetter(BattleFormChange::learnableMoves), (App)Codec.STRING.optionalFieldOf("item").forGetter(BattleFormChange::item), (App)Codec.STRING.optionalFieldOf("forward_item").forGetter(BattleFormChange::forwardItem), (App)Codec.BOOL.optionalFieldOf("consume_item").forGetter(BattleFormChange::consumeItem), (App)Codec.BOOL.optionalFieldOf("equip_forward_item").forGetter(BattleFormChange::equipForwardItem)).apply((Applicative)instance, BattleFormChange::new));
/*    */   } }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\effect\codec\BattleFormChange.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */