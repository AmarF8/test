/*    */ package com.atlas.common.fabric.ranked.data;public final class RankedOpponent extends Record { private final UUID playerUUID; private final String playerName; private final int elo; private final String rankDisplayName;
/*    */   private final String firstPokemonSpecies;
/*    */   private final int avgLevel;
/*    */   private final String skinTextures;
/*    */   private final String skinSignature;
/*    */   private final boolean defeated;
/*    */   private final boolean won;
/*    */   
/*  9 */   public RankedOpponent(UUID playerUUID, String playerName, int elo, String rankDisplayName, String firstPokemonSpecies, int avgLevel, String skinTextures, String skinSignature, boolean defeated, boolean won) { this.playerUUID = playerUUID; this.playerName = playerName; this.elo = elo; this.rankDisplayName = rankDisplayName; this.firstPokemonSpecies = firstPokemonSpecies; this.avgLevel = avgLevel; this.skinTextures = skinTextures; this.skinSignature = skinSignature; this.defeated = defeated; this.won = won; } public final String toString() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/ranked/data/RankedOpponent;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #9	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*  9 */     //   0	7	0	this	Lcom/atlas/common/fabric/ranked/data/RankedOpponent; } public UUID playerUUID() { return this.playerUUID; } public final int hashCode() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/ranked/data/RankedOpponent;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #9	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/fabric/ranked/data/RankedOpponent; } public final boolean equals(Object o) { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/ranked/data/RankedOpponent;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #9	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lcom/atlas/common/fabric/ranked/data/RankedOpponent;
/*  9 */     //   0	8	1	o	Ljava/lang/Object; } public String playerName() { return this.playerName; } public int elo() { return this.elo; } public String rankDisplayName() { return this.rankDisplayName; } public String firstPokemonSpecies() { return this.firstPokemonSpecies; } public int avgLevel() { return this.avgLevel; } public String skinTextures() { return this.skinTextures; } public String skinSignature() { return this.skinSignature; } public boolean defeated() { return this.defeated; } public boolean won() { return this.won; }
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
/*    */   public RankedOpponent withResult(boolean defeated, boolean won) {
/* 26 */     return new RankedOpponent(this.playerUUID, this.playerName, this.elo, this.rankDisplayName, this.firstPokemonSpecies, this.avgLevel, this.skinTextures, this.skinSignature, defeated, won);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public RankedRank getRank() {
/* 34 */     return RankedRank.getRankForElo(this.elo);
/*    */   } }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\ranked\data\RankedOpponent.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */