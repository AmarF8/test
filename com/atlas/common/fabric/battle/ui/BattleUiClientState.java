/*     */ package com.atlas.common.fabric.battle.ui;
/*     */ 
/*     */ import com.cobblemon.mod.common.client.CobblemonClient;
/*     */ import com.cobblemon.mod.common.client.battle.ClientBattle;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import java.util.UUID;
/*     */ import java.util.function.Function;
/*     */ import net.fabricmc.api.EnvType;
/*     */ import net.fabricmc.api.Environment;
/*     */ 
/*     */ @Environment(EnvType.CLIENT)
/*     */ public final class BattleUiClientState {
/*     */   private static final long HEALTH_OVERRIDE_MILLIS = 2500L;
/*  19 */   private static final Map<UUID, BattleState> BATTLES = new HashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void apply(BattleUiNetwork.TeamPayload payload) {
/*  25 */     BattleState state = state(payload.battleId());
/*  26 */     state.teams.put(payload.actorId(), state.mergeHealthOverrides(payload));
/*     */   }
/*     */   
/*     */   public static void apply(BattleUiNetwork.InfoPayload payload) {
/*  30 */     BattleState state = state(payload.battleId());
/*  31 */     state.info = payload;
/*  32 */     if (payload.timerRemainingSeconds() >= 0) {
/*  33 */       state.applyTimer(payload.timerRemainingSeconds());
/*     */     }
/*     */   }
/*     */   
/*     */   public static void apply(BattleUiNetwork.TimerPayload payload) {
/*  38 */     state(payload.battleId()).applyTimer(payload.remainingSeconds());
/*     */   }
/*     */   
/*     */   public static void apply(BattleUiNetwork.HealthPayload payload) {
/*  42 */     BattleState state = state(payload.battleId());
/*  43 */     state.healthOverrides.put(payload
/*  44 */         .pokemonId(), new HpOverride(payload
/*  45 */           .hp(), payload.maxHp(), payload.flatHp(), System.currentTimeMillis()));
/*     */     
/*  47 */     state.teams.replaceAll((ignored, team) -> state.patchTeamHealth(team, payload));
/*     */   }
/*     */   
/*     */   public static void clear(UUID battleId) {
/*  51 */     BATTLES.remove(battleId);
/*     */   }
/*     */   
/*     */   public static Optional<BattleState> currentBattle() {
/*  55 */     ClientBattle battle = CobblemonClient.INSTANCE.getBattle();
/*  56 */     if (battle == null) return Optional.empty(); 
/*  57 */     BattleState state = BATTLES.get(battle.getBattleId());
/*  58 */     if (state == null) return Optional.empty(); 
/*  59 */     return Optional.of(state);
/*     */   }
/*     */   
/*     */   private static BattleState state(UUID battleId) {
/*  63 */     return BATTLES.computeIfAbsent(battleId, BattleState::new);
/*     */   }
/*     */   
/*     */   public static final class BattleState {
/*     */     private final UUID battleId;
/*  68 */     private final Map<UUID, BattleUiNetwork.TeamPayload> teams = new HashMap<>();
/*  69 */     private final Map<UUID, BattleUiClientState.HpOverride> healthOverrides = new HashMap<>();
/*     */     private BattleUiNetwork.InfoPayload info;
/*     */     private long turnTimerDeadlineMillis;
/*     */     
/*     */     private BattleState(UUID battleId) {
/*  74 */       this.battleId = battleId;
/*     */     }
/*     */     
/*     */     public UUID battleId() {
/*  78 */       return this.battleId;
/*     */     }
/*     */     
/*     */     public BattleUiNetwork.InfoPayload info() {
/*  82 */       return this.info;
/*     */     }
/*     */     
/*     */     public BattleUiNetwork.TeamPayload team(UUID actorId) {
/*  86 */       return this.teams.get(actorId);
/*     */     }
/*     */     
/*     */     public int remainingTurnTimerSeconds() {
/*  90 */       if (this.turnTimerDeadlineMillis <= 0L) return 0; 
/*  91 */       long remainingMillis = this.turnTimerDeadlineMillis - System.currentTimeMillis();
/*  92 */       if (remainingMillis <= 0L) return 0; 
/*  93 */       return (int)Math.ceil(remainingMillis / 1000.0D);
/*     */     }
/*     */     
/*     */     private void applyTimer(int remainingSeconds) {
/*  97 */       if (remainingSeconds <= 0) {
/*  98 */         this.turnTimerDeadlineMillis = 0L;
/*     */         return;
/*     */       } 
/* 101 */       this.turnTimerDeadlineMillis = System.currentTimeMillis() + remainingSeconds * 1000L;
/*     */     }
/*     */     
/*     */     private BattleUiNetwork.TeamPayload mergeHealthOverrides(BattleUiNetwork.TeamPayload payload) {
/* 105 */       long now = System.currentTimeMillis();
/* 106 */       List<BattleUiNetwork.PokemonEntry> pokemon = new ArrayList<>(payload.pokemon().size());
/* 107 */       boolean changed = false;
/* 108 */       for (BattleUiNetwork.PokemonEntry entry : payload.pokemon()) {
/* 109 */         BattleUiClientState.HpOverride override = this.healthOverrides.get(entry.pokemonId());
/* 110 */         if (override == null) {
/* 111 */           pokemon.add(entry);
/*     */           continue;
/*     */         } 
/* 114 */         if (now - override.updatedAtMillis() > 2500L) {
/* 115 */           this.healthOverrides.remove(entry.pokemonId());
/* 116 */           pokemon.add(entry);
/*     */           continue;
/*     */         } 
/* 119 */         if (Math.abs(entry.hp() - override.hp()) < 0.01F && 
/* 120 */           Math.abs(entry.maxHp() - override.maxHp()) < 0.01F && entry
/* 121 */           .flatHp() == override.flatHp()) {
/* 122 */           this.healthOverrides.remove(entry.pokemonId());
/* 123 */           pokemon.add(entry);
/*     */           continue;
/*     */         } 
/* 126 */         pokemon.add(BattleUiClientState.withHealth(entry, override.hp(), override.maxHp(), override.flatHp()));
/* 127 */         changed = true;
/*     */       } 
/* 129 */       if (!changed) return payload; 
/* 130 */       return new BattleUiNetwork.TeamPayload(payload.battleId(), payload.actorId(), payload.sideOne(), payload.actorName(), pokemon);
/*     */     }
/*     */ 
/*     */     
/*     */     private BattleUiNetwork.TeamPayload patchTeamHealth(BattleUiNetwork.TeamPayload payload, BattleUiNetwork.HealthPayload health) {
/* 135 */       List<BattleUiNetwork.PokemonEntry> pokemon = new ArrayList<>(payload.pokemon().size());
/* 136 */       boolean changed = false;
/* 137 */       for (BattleUiNetwork.PokemonEntry entry : payload.pokemon()) {
/* 138 */         if (entry.pokemonId().equals(health.pokemonId())) {
/* 139 */           pokemon.add(BattleUiClientState.withHealth(entry, health.hp(), health.maxHp(), health.flatHp()));
/* 140 */           changed = true; continue;
/*     */         } 
/* 142 */         pokemon.add(entry);
/*     */       } 
/*     */       
/* 145 */       if (!changed) return payload; 
/* 146 */       return new BattleUiNetwork.TeamPayload(payload.battleId(), payload.actorId(), payload.sideOne(), payload.actorName(), pokemon);
/*     */     }
/*     */     
/*     */     public List<BattleUiNetwork.TeamPayload> side(boolean sideOne) {
/* 150 */       List<BattleUiNetwork.TeamPayload> entries = new ArrayList<>();
/* 151 */       for (BattleUiNetwork.TeamPayload payload : this.teams.values()) {
/* 152 */         if (payload.sideOne() == sideOne) entries.add(payload); 
/*     */       } 
/* 154 */       entries.sort(Comparator.comparing(BattleUiNetwork.TeamPayload::actorName, String.CASE_INSENSITIVE_ORDER));
/* 155 */       return entries;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static BattleUiNetwork.PokemonEntry withHealth(BattleUiNetwork.PokemonEntry entry, float hp, float maxHp, boolean flatHp) {
/* 163 */     return new BattleUiNetwork.PokemonEntry(entry
/* 164 */         .pokemonId(), entry
/* 165 */         .displayName(), entry
/* 166 */         .species(), entry
/* 167 */         .speciesId(), entry
/* 168 */         .form(), entry
/* 169 */         .level(), entry
/* 170 */         .gender(), entry
/* 171 */         .primaryType(), entry
/* 172 */         .secondaryType(), 
/* 173 */         Math.max(0.0F, hp), 
/* 174 */         Math.max(1.0F, maxHp), flatHp, entry
/*     */         
/* 176 */         .status(), entry
/* 177 */         .ability(), entry
/* 178 */         .heldItem(), entry
/* 179 */         .speed(), entry
/* 180 */         .active(), (entry
/* 181 */         .fainted() || hp <= 0.0F), entry
/* 182 */         .revealed(), entry
/* 183 */         .aspects(), entry
/* 184 */         .moves(), entry
/* 185 */         .boosts());
/*     */   }
/*     */   private static final class HpOverride extends Record { private final float hp; private final float maxHp; private final boolean flatHp; private final long updatedAtMillis;
/*     */     
/* 189 */     private HpOverride(float hp, float maxHp, boolean flatHp, long updatedAtMillis) { this.hp = hp; this.maxHp = maxHp; this.flatHp = flatHp; this.updatedAtMillis = updatedAtMillis; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/battle/ui/BattleUiClientState$HpOverride;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #189	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/* 189 */       //   0	7	0	this	Lcom/atlas/common/fabric/battle/ui/BattleUiClientState$HpOverride; } public float hp() { return this.hp; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/battle/ui/BattleUiClientState$HpOverride;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #189	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/battle/ui/BattleUiClientState$HpOverride; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/battle/ui/BattleUiClientState$HpOverride;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #189	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lcom/atlas/common/fabric/battle/ui/BattleUiClientState$HpOverride;
/* 189 */       //   0	8	1	o	Ljava/lang/Object; } public float maxHp() { return this.maxHp; } public boolean flatHp() { return this.flatHp; } public long updatedAtMillis() { return this.updatedAtMillis; }
/*     */      }
/*     */ 
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\battl\\ui\BattleUiClientState.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */