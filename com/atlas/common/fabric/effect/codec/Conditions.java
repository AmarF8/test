/*     */ package com.atlas.common.fabric.effect.codec;
/*     */ 
/*     */ public final class Conditions extends Record {
/*     */   private final Forms form;
/*     */   private final Aspects aspect;
/*     */   private final Abilities ability;
/*     */   private final Moves moves;
/*     */   private final Friendship friendship;
/*     */   private final List<String> aspects;
/*     */   private final Optional<String> pokemonProperties;
/*     */   public static final Codec<Conditions> CODEC;
/*     */   
/*  13 */   public Conditions(Forms form, Aspects aspect, Abilities ability, Moves moves, Friendship friendship, List<String> aspects, Optional<String> pokemonProperties) { this.form = form; this.aspect = aspect; this.ability = ability; this.moves = moves; this.friendship = friendship; this.aspects = aspects; this.pokemonProperties = pokemonProperties; } public final String toString() { // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/effect/codec/Conditions;)Ljava/lang/String;
/*     */     //   6: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #13	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*  13 */     //   0	7	0	this	Lcom/atlas/common/fabric/effect/codec/Conditions; } public Forms form() { return this.form; } public final int hashCode() { // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/effect/codec/Conditions;)I
/*     */     //   6: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #13	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	7	0	this	Lcom/atlas/common/fabric/effect/codec/Conditions; } public final boolean equals(Object o) { // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/effect/codec/Conditions;Ljava/lang/Object;)Z
/*     */     //   7: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #13	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	8	0	this	Lcom/atlas/common/fabric/effect/codec/Conditions;
/*  13 */     //   0	8	1	o	Ljava/lang/Object; } public Aspects aspect() { return this.aspect; } public Abilities ability() { return this.ability; } public Moves moves() { return this.moves; } public Friendship friendship() { return this.friendship; } public List<String> aspects() { return this.aspects; } public Optional<String> pokemonProperties() { return this.pokemonProperties; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/*  22 */     CODEC = RecordCodecBuilder.create(instance -> instance.group((App)Forms.CODEC.optionalFieldOf("form", Forms.DEFAULT()).forGetter(Conditions::form), (App)Aspects.CODEC.optionalFieldOf("aspect", Aspects.DEFAULT()).forGetter(Conditions::aspect), (App)Abilities.CODEC.optionalFieldOf("ability", Abilities.DEFAULT()).forGetter(Conditions::ability), (App)Moves.CODEC.optionalFieldOf("move", Moves.DEFAULT()).forGetter(Conditions::moves), (App)Friendship.CODEC.optionalFieldOf("friendship", Friendship.DEFAULT()).forGetter(Conditions::friendship), (App)Codec.list((Codec)Codec.STRING).optionalFieldOf("aspects", List.of()).forGetter(Conditions::aspects), (App)Codec.STRING.optionalFieldOf("pokemon_properties").forGetter(Conditions::pokemonProperties)).apply((Applicative)instance, Conditions::new));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Conditions DEFAULT() {
/*  33 */     return new Conditions(Forms.DEFAULT(), Aspects.DEFAULT(), Abilities.DEFAULT(), Moves.DEFAULT(), Friendship.DEFAULT(), List.of(), Optional.empty());
/*     */   }
/*     */   
/*     */   public boolean validate(Pokemon pokemon) {
/*  37 */     if (!this.form.validate(pokemon)) return false; 
/*  38 */     if (!this.aspect.validate(pokemon)) return false; 
/*  39 */     if (!this.moves.validate(pokemon)) return false; 
/*  40 */     if (!this.friendship.validate(pokemon)) return false; 
/*  41 */     return this.ability.validate(pokemon);
/*     */   }
/*     */   public static final class Forms extends Record { private final List<String> required_forms; private final List<String> blacklist_forms; public static final Codec<Forms> CODEC;
/*  44 */     public Forms(List<String> required_forms, List<String> blacklist_forms) { this.required_forms = required_forms; this.blacklist_forms = blacklist_forms; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/effect/codec/Conditions$Forms;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #44	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/effect/codec/Conditions$Forms; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/effect/codec/Conditions$Forms;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #44	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/effect/codec/Conditions$Forms; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/effect/codec/Conditions$Forms;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #44	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lcom/atlas/common/fabric/effect/codec/Conditions$Forms;
/*  44 */       //   0	8	1	o	Ljava/lang/Object; } public List<String> required_forms() { return this.required_forms; } public List<String> blacklist_forms() { return this.blacklist_forms; } static {
/*  45 */       CODEC = RecordCodecBuilder.create(i -> i.group((App)Codec.list((Codec)Codec.STRING).optionalFieldOf("required_forms", List.of()).forGetter(Forms::required_forms), (App)Codec.list((Codec)Codec.STRING).optionalFieldOf("blacklist_forms", List.of()).forGetter(Forms::blacklist_forms)).apply((Applicative)i, Forms::new));
/*     */     }
/*     */     
/*     */     public static Forms DEFAULT() {
/*  49 */       return new Forms(List.of(), List.of());
/*     */     } public boolean validate(Pokemon p) {
/*  51 */       if (!this.blacklist_forms.isEmpty() && this.blacklist_forms.contains(p.getForm().getName())) return false; 
/*  52 */       return (this.required_forms.isEmpty() || this.required_forms.contains(p.getForm().getName()));
/*     */     } }
/*     */   public static final class Friendship extends Record { private final Integer min_friendship; private final Integer max_friendship; public static final Codec<Friendship> CODEC;
/*     */     
/*  56 */     public Friendship(Integer min_friendship, Integer max_friendship) { this.min_friendship = min_friendship; this.max_friendship = max_friendship; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/effect/codec/Conditions$Friendship;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #56	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/effect/codec/Conditions$Friendship; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/effect/codec/Conditions$Friendship;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #56	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/effect/codec/Conditions$Friendship; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/effect/codec/Conditions$Friendship;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #56	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lcom/atlas/common/fabric/effect/codec/Conditions$Friendship;
/*  56 */       //   0	8	1	o	Ljava/lang/Object; } public Integer min_friendship() { return this.min_friendship; } public Integer max_friendship() { return this.max_friendship; } static {
/*  57 */       CODEC = RecordCodecBuilder.create(i -> i.group((App)Codec.INT.optionalFieldOf("min_friendship", Integer.valueOf(0)).forGetter(Friendship::min_friendship), (App)Codec.INT.optionalFieldOf("max_friendship", Integer.valueOf(255)).forGetter(Friendship::max_friendship)).apply((Applicative)i, Friendship::new));
/*     */     }
/*     */     
/*     */     public static Friendship DEFAULT() {
/*  61 */       return new Friendship(Integer.valueOf(0), Integer.valueOf(255)); } public boolean validate(Pokemon p) {
/*  62 */       return (p.getFriendship() >= this.min_friendship.intValue() && p.getFriendship() <= this.max_friendship.intValue());
/*     */     } }
/*     */   public static final class Abilities extends Record { private final List<String> required_abilities; private final List<String> blacklist_abilities; public static final Codec<Abilities> CODEC;
/*  65 */     public Abilities(List<String> required_abilities, List<String> blacklist_abilities) { this.required_abilities = required_abilities; this.blacklist_abilities = blacklist_abilities; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/effect/codec/Conditions$Abilities;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #65	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/effect/codec/Conditions$Abilities; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/effect/codec/Conditions$Abilities;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #65	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/effect/codec/Conditions$Abilities; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/effect/codec/Conditions$Abilities;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #65	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lcom/atlas/common/fabric/effect/codec/Conditions$Abilities;
/*  65 */       //   0	8	1	o	Ljava/lang/Object; } public List<String> required_abilities() { return this.required_abilities; } public List<String> blacklist_abilities() { return this.blacklist_abilities; } static {
/*  66 */       CODEC = RecordCodecBuilder.create(i -> i.group((App)Codec.list((Codec)Codec.STRING).optionalFieldOf("required_abilities", List.of()).forGetter(Abilities::required_abilities), (App)Codec.list((Codec)Codec.STRING).optionalFieldOf("blacklist_abilities", List.of()).forGetter(Abilities::blacklist_abilities)).apply((Applicative)i, Abilities::new));
/*     */     }
/*     */     
/*     */     public static Abilities DEFAULT() {
/*  70 */       return new Abilities(List.of(), List.of());
/*     */     } public boolean validate(Pokemon p) {
/*  72 */       if (!this.blacklist_abilities.isEmpty() && this.blacklist_abilities.contains(p.getAbility().getName())) return false; 
/*  73 */       return (this.required_abilities.isEmpty() || this.required_abilities.contains(p.getAbility().getName()));
/*     */     } }
/*     */   public static final class Aspects extends Record { private final List<List<String>> required_aspects; private final List<List<String>> blacklist_aspects; public static final Codec<Aspects> CODEC;
/*     */     
/*  77 */     public Aspects(List<List<String>> required_aspects, List<List<String>> blacklist_aspects) { this.required_aspects = required_aspects; this.blacklist_aspects = blacklist_aspects; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/effect/codec/Conditions$Aspects;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #77	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/effect/codec/Conditions$Aspects; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/effect/codec/Conditions$Aspects;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #77	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/effect/codec/Conditions$Aspects; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/effect/codec/Conditions$Aspects;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #77	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lcom/atlas/common/fabric/effect/codec/Conditions$Aspects;
/*  77 */       //   0	8	1	o	Ljava/lang/Object; } public List<List<String>> required_aspects() { return this.required_aspects; } public List<List<String>> blacklist_aspects() { return this.blacklist_aspects; } static {
/*  78 */       CODEC = RecordCodecBuilder.create(i -> i.group((App)Codec.list(Codec.list((Codec)Codec.STRING)).optionalFieldOf("required_aspects", List.of()).forGetter(Aspects::required_aspects), (App)Codec.list(Codec.list((Codec)Codec.STRING)).optionalFieldOf("blacklist_aspects", List.of()).forGetter(Aspects::blacklist_aspects)).apply((Applicative)i, Aspects::new));
/*     */     }
/*     */     
/*     */     public static Aspects DEFAULT() {
/*  82 */       return new Aspects(List.of(), List.of());
/*     */     } public boolean validate(Pokemon p) {
/*  84 */       if (!this.blacklist_aspects.isEmpty() && this.blacklist_aspects.stream().anyMatch(g -> p.getAspects().containsAll(g))) return false; 
/*  85 */       return (this.required_aspects.isEmpty() || this.required_aspects.stream().anyMatch(g -> p.getAspects().containsAll(g)));
/*     */     } }
/*     */   public static final class Moves extends Record { private final List<List<String>> required_moves; private final List<List<String>> blacklist_moves; public static final Codec<Moves> CODEC;
/*     */     
/*  89 */     public Moves(List<List<String>> required_moves, List<List<String>> blacklist_moves) { this.required_moves = required_moves; this.blacklist_moves = blacklist_moves; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/effect/codec/Conditions$Moves;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #89	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/effect/codec/Conditions$Moves; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/effect/codec/Conditions$Moves;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #89	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/effect/codec/Conditions$Moves; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/effect/codec/Conditions$Moves;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #89	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lcom/atlas/common/fabric/effect/codec/Conditions$Moves;
/*  89 */       //   0	8	1	o	Ljava/lang/Object; } public List<List<String>> required_moves() { return this.required_moves; } public List<List<String>> blacklist_moves() { return this.blacklist_moves; } static {
/*  90 */       CODEC = RecordCodecBuilder.create(i -> i.group((App)Codec.list(Codec.list((Codec)Codec.STRING)).optionalFieldOf("required_moves", List.of()).forGetter(Moves::required_moves), (App)Codec.list(Codec.list((Codec)Codec.STRING)).optionalFieldOf("blacklist_moves", List.of()).forGetter(Moves::blacklist_moves)).apply((Applicative)i, Moves::new));
/*     */     }
/*     */     
/*     */     public static Moves DEFAULT() {
/*  94 */       return new Moves(List.of(), List.of());
/*     */     } public boolean validate(Pokemon p) {
/*  96 */       if (this.blacklist_moves.isEmpty() && this.required_moves.isEmpty()) return true; 
/*  97 */       Set<String> moveNames = (Set<String>)p.getMoveSet().getMoves().stream().map(Move::getName).collect(Collectors.toSet());
/*  98 */       Objects.requireNonNull(moveNames); if (!this.blacklist_moves.isEmpty() && this.blacklist_moves.stream().anyMatch(moveNames::containsAll)) return false; 
/*  99 */       if (!this.required_moves.isEmpty()) { Objects.requireNonNull(moveNames); return this.required_moves.stream().anyMatch(moveNames::containsAll); }
/* 100 */        return true;
/*     */     } }
/*     */ 
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\effect\codec\Conditions.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */