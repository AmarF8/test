/*     */ package com.atlas.common.fabric.battle.ui;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.UUID;
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
/*     */ public final class PokemonEntry
/*     */   extends Record
/*     */ {
/*     */   private final UUID pokemonId;
/*     */   private final String displayName;
/*     */   private final String species;
/*     */   private final String speciesId;
/*     */   private final String form;
/*     */   private final int level;
/*     */   private final String gender;
/*     */   private final String primaryType;
/*     */   private final String secondaryType;
/*     */   private final float hp;
/*     */   private final float maxHp;
/*     */   private final boolean flatHp;
/*     */   private final String status;
/*     */   private final String ability;
/*     */   private final String heldItem;
/*     */   private final int speed;
/*     */   private final boolean active;
/*     */   private final boolean fainted;
/*     */   private final boolean revealed;
/*     */   private final List<String> aspects;
/*     */   private final List<BattleUiNetwork.MoveEntry> moves;
/*     */   private final List<BattleUiNetwork.StatEntry> boosts;
/*     */   
/*     */   public final String toString() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/battle/ui/BattleUiNetwork$PokemonEntry;)Ljava/lang/String;
/*     */     //   6: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #291	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	7	0	this	Lcom/atlas/common/fabric/battle/ui/BattleUiNetwork$PokemonEntry;
/*     */   }
/*     */   
/*     */   public final int hashCode() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/battle/ui/BattleUiNetwork$PokemonEntry;)I
/*     */     //   6: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #291	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	7	0	this	Lcom/atlas/common/fabric/battle/ui/BattleUiNetwork$PokemonEntry;
/*     */   }
/*     */   
/*     */   public final boolean equals(Object o) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/battle/ui/BattleUiNetwork$PokemonEntry;Ljava/lang/Object;)Z
/*     */     //   7: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #291	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	8	0	this	Lcom/atlas/common/fabric/battle/ui/BattleUiNetwork$PokemonEntry;
/*     */     //   0	8	1	o	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public PokemonEntry(UUID pokemonId, String displayName, String species, String speciesId, String form, int level, String gender, String primaryType, String secondaryType, float hp, float maxHp, boolean flatHp, String status, String ability, String heldItem, int speed, boolean active, boolean fainted, boolean revealed, List<String> aspects, List<BattleUiNetwork.MoveEntry> moves, List<BattleUiNetwork.StatEntry> boosts) {
/* 291 */     this.pokemonId = pokemonId; this.displayName = displayName; this.species = species; this.speciesId = speciesId; this.form = form; this.level = level; this.gender = gender; this.primaryType = primaryType; this.secondaryType = secondaryType; this.hp = hp; this.maxHp = maxHp; this.flatHp = flatHp; this.status = status; this.ability = ability; this.heldItem = heldItem; this.speed = speed; this.active = active; this.fainted = fainted; this.revealed = revealed; this.aspects = aspects; this.moves = moves; this.boosts = boosts; } public UUID pokemonId() { return this.pokemonId; } public String displayName() { return this.displayName; } public String species() { return this.species; } public String speciesId() { return this.speciesId; } public String form() { return this.form; } public int level() { return this.level; } public String gender() { return this.gender; } public String primaryType() { return this.primaryType; } public String secondaryType() { return this.secondaryType; } public float hp() { return this.hp; } public float maxHp() { return this.maxHp; } public boolean flatHp() { return this.flatHp; } public String status() { return this.status; } public String ability() { return this.ability; } public String heldItem() { return this.heldItem; } public int speed() { return this.speed; } public boolean active() { return this.active; } public boolean fainted() { return this.fainted; } public boolean revealed() { return this.revealed; } public List<String> aspects() { return this.aspects; } public List<BattleUiNetwork.MoveEntry> moves() { return this.moves; } public List<BattleUiNetwork.StatEntry> boosts() { return this.boosts; }
/*     */ 
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\battl\\ui\BattleUiNetwork$PokemonEntry.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */