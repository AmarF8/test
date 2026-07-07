/*     */ package com.atlas.common.fabric.battle.ui;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
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
/*     */ public final class BattleState
/*     */ {
/*     */   private final UUID battleId;
/*  68 */   private final Map<UUID, BattleUiNetwork.TeamPayload> teams = new HashMap<>();
/*  69 */   private final Map<UUID, BattleUiClientState.HpOverride> healthOverrides = new HashMap<>();
/*     */   private BattleUiNetwork.InfoPayload info;
/*     */   private long turnTimerDeadlineMillis;
/*     */   
/*     */   private BattleState(UUID battleId) {
/*  74 */     this.battleId = battleId;
/*     */   }
/*     */   
/*     */   public UUID battleId() {
/*  78 */     return this.battleId;
/*     */   }
/*     */   
/*     */   public BattleUiNetwork.InfoPayload info() {
/*  82 */     return this.info;
/*     */   }
/*     */   
/*     */   public BattleUiNetwork.TeamPayload team(UUID actorId) {
/*  86 */     return this.teams.get(actorId);
/*     */   }
/*     */   
/*     */   public int remainingTurnTimerSeconds() {
/*  90 */     if (this.turnTimerDeadlineMillis <= 0L) return 0; 
/*  91 */     long remainingMillis = this.turnTimerDeadlineMillis - System.currentTimeMillis();
/*  92 */     if (remainingMillis <= 0L) return 0; 
/*  93 */     return (int)Math.ceil(remainingMillis / 1000.0D);
/*     */   }
/*     */   
/*     */   private void applyTimer(int remainingSeconds) {
/*  97 */     if (remainingSeconds <= 0) {
/*  98 */       this.turnTimerDeadlineMillis = 0L;
/*     */       return;
/*     */     } 
/* 101 */     this.turnTimerDeadlineMillis = System.currentTimeMillis() + remainingSeconds * 1000L;
/*     */   }
/*     */   
/*     */   private BattleUiNetwork.TeamPayload mergeHealthOverrides(BattleUiNetwork.TeamPayload payload) {
/* 105 */     long now = System.currentTimeMillis();
/* 106 */     List<BattleUiNetwork.PokemonEntry> pokemon = new ArrayList<>(payload.pokemon().size());
/* 107 */     boolean changed = false;
/* 108 */     for (BattleUiNetwork.PokemonEntry entry : payload.pokemon()) {
/* 109 */       BattleUiClientState.HpOverride override = this.healthOverrides.get(entry.pokemonId());
/* 110 */       if (override == null) {
/* 111 */         pokemon.add(entry);
/*     */         continue;
/*     */       } 
/* 114 */       if (now - override.updatedAtMillis() > 2500L) {
/* 115 */         this.healthOverrides.remove(entry.pokemonId());
/* 116 */         pokemon.add(entry);
/*     */         continue;
/*     */       } 
/* 119 */       if (Math.abs(entry.hp() - override.hp()) < 0.01F && 
/* 120 */         Math.abs(entry.maxHp() - override.maxHp()) < 0.01F && entry
/* 121 */         .flatHp() == override.flatHp()) {
/* 122 */         this.healthOverrides.remove(entry.pokemonId());
/* 123 */         pokemon.add(entry);
/*     */         continue;
/*     */       } 
/* 126 */       pokemon.add(BattleUiClientState.withHealth(entry, override.hp(), override.maxHp(), override.flatHp()));
/* 127 */       changed = true;
/*     */     } 
/* 129 */     if (!changed) return payload; 
/* 130 */     return new BattleUiNetwork.TeamPayload(payload.battleId(), payload.actorId(), payload.sideOne(), payload.actorName(), pokemon);
/*     */   }
/*     */ 
/*     */   
/*     */   private BattleUiNetwork.TeamPayload patchTeamHealth(BattleUiNetwork.TeamPayload payload, BattleUiNetwork.HealthPayload health) {
/* 135 */     List<BattleUiNetwork.PokemonEntry> pokemon = new ArrayList<>(payload.pokemon().size());
/* 136 */     boolean changed = false;
/* 137 */     for (BattleUiNetwork.PokemonEntry entry : payload.pokemon()) {
/* 138 */       if (entry.pokemonId().equals(health.pokemonId())) {
/* 139 */         pokemon.add(BattleUiClientState.withHealth(entry, health.hp(), health.maxHp(), health.flatHp()));
/* 140 */         changed = true; continue;
/*     */       } 
/* 142 */       pokemon.add(entry);
/*     */     } 
/*     */     
/* 145 */     if (!changed) return payload; 
/* 146 */     return new BattleUiNetwork.TeamPayload(payload.battleId(), payload.actorId(), payload.sideOne(), payload.actorName(), pokemon);
/*     */   }
/*     */   
/*     */   public List<BattleUiNetwork.TeamPayload> side(boolean sideOne) {
/* 150 */     List<BattleUiNetwork.TeamPayload> entries = new ArrayList<>();
/* 151 */     for (BattleUiNetwork.TeamPayload payload : this.teams.values()) {
/* 152 */       if (payload.sideOne() == sideOne) entries.add(payload); 
/*     */     } 
/* 154 */     entries.sort(Comparator.comparing(BattleUiNetwork.TeamPayload::actorName, String.CASE_INSENSITIVE_ORDER));
/* 155 */     return entries;
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\battl\\ui\BattleUiClientState$BattleState.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */