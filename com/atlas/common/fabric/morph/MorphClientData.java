/*    */ package com.atlas.common.fabric.morph;
/*    */ 
/*    */ import com.atlas.common.fabric.morph.network.MorphNetwork;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class MorphClientData
/*    */ {
/* 14 */   private static final MorphClientData INSTANCE = new MorphClientData();
/*    */   
/* 16 */   private String selectedMorphId = "cobblemon:pikachu";
/* 17 */   private String activeMorphId = "";
/* 18 */   private int pokeCoins = 0;
/* 19 */   private int pokeGems = 0;
/* 20 */   private List<MorphModels.MorphProgressSnapshot> progress = new ArrayList<>();
/* 21 */   private MorphModels.DefenseSnapshot defense = new MorphModels.DefenseSnapshot(false, 0, 0, 0, 100, 0L, "Idle");
/* 22 */   private String lastMessage = "";
/*    */   private boolean lastMessageSuccess = true;
/* 24 */   private long lastMessageAt = 0L;
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public static MorphClientData getInstance() {
/* 29 */     return INSTANCE;
/*    */   }
/*    */   
/*    */   public void applySync(@NotNull MorphNetwork.SyncPayload payload) {
/* 33 */     this.selectedMorphId = payload.selectedMorphId();
/* 34 */     this.activeMorphId = payload.activeMorphId();
/* 35 */     this.pokeCoins = payload.pokeCoins();
/* 36 */     this.pokeGems = payload.pokeGems();
/* 37 */     this.progress = new ArrayList<>(payload.progress());
/* 38 */     this.defense = payload.defense();
/*    */   }
/*    */   
/*    */   public void applyActionResult(@NotNull MorphNetwork.ActionResultPayload payload) {
/* 42 */     this.lastMessage = payload.message();
/* 43 */     this.lastMessageSuccess = payload.success();
/* 44 */     this.lastMessageAt = System.currentTimeMillis();
/*    */   }
/*    */   @NotNull
/*    */   public String getSelectedMorphId() {
/* 48 */     return this.selectedMorphId;
/*    */   }
/*    */   @NotNull
/*    */   public String getActiveMorphId() {
/* 52 */     return this.activeMorphId;
/*    */   }
/*    */   
/*    */   public int getPokeCoins() {
/* 56 */     return this.pokeCoins;
/*    */   }
/*    */   
/*    */   public int getPokeGems() {
/* 60 */     return this.pokeGems;
/*    */   }
/*    */   @NotNull
/*    */   public List<MorphModels.MorphProgressSnapshot> getProgress() {
/* 64 */     return this.progress;
/*    */   }
/*    */   @NotNull
/*    */   public MorphModels.DefenseSnapshot getDefense() {
/* 68 */     return this.defense;
/*    */   }
/*    */   @Nullable
/*    */   public MorphModels.MorphProgressSnapshot getProgress(@NotNull String speciesId) {
/* 72 */     for (MorphModels.MorphProgressSnapshot snapshot : this.progress) {
/* 73 */       if (snapshot.speciesId().equalsIgnoreCase(speciesId)) {
/* 74 */         return snapshot;
/*    */       }
/*    */     } 
/* 77 */     return null;
/*    */   }
/*    */   @NotNull
/*    */   public String getLastMessage() {
/* 81 */     return this.lastMessage;
/*    */   }
/*    */   
/*    */   public boolean isLastMessageSuccess() {
/* 85 */     return this.lastMessageSuccess;
/*    */   }
/*    */   
/*    */   public long getLastMessageAt() {
/* 89 */     return this.lastMessageAt;
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\morph\MorphClientData.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */