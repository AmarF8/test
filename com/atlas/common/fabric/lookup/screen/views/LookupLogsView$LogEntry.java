/*     */ package com.atlas.common.fabric.lookup.screen.views;
/*     */ 
/*     */ import com.google.gson.JsonObject;
/*     */ import java.util.List;
/*     */ import net.minecraft.class_1799;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class LogEntry
/*     */   extends Record
/*     */ {
/*     */   private final String timestamp;
/*     */   private final String type;
/*     */   private final String content;
/*     */   private final class_1799 stack;
/*     */   private final List<class_1799> extraStacks;
/*     */   private final List<class_1799> rewardStacks;
/*     */   private final String otherPlayerUuid;
/*     */   private final String otherPlayerName;
/*     */   private final String rerollStat;
/*     */   private final int rerollOldValue;
/*     */   private final int rerollNewValue;
/*     */   private final JsonObject primaryPokemon;
/*     */   private final List<JsonObject> extraPokemon;
/*     */   private final List<JsonObject> rewardPokemon;
/*     */   private final LookupLogsView.AuctionData auction;
/*     */   private final String rerollSubtype;
/*     */   private final String crateSourceType;
/*     */   private final String crateSourceDisplayName;
/*     */   
/*     */   public final String toString() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/lookup/screen/views/LookupLogsView$LogEntry;)Ljava/lang/String;
/*     */     //   6: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #123	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	7	0	this	Lcom/atlas/common/fabric/lookup/screen/views/LookupLogsView$LogEntry;
/*     */   }
/*     */   
/*     */   public final int hashCode() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/lookup/screen/views/LookupLogsView$LogEntry;)I
/*     */     //   6: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #123	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	7	0	this	Lcom/atlas/common/fabric/lookup/screen/views/LookupLogsView$LogEntry;
/*     */   }
/*     */   
/*     */   public final boolean equals(Object o) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/lookup/screen/views/LookupLogsView$LogEntry;Ljava/lang/Object;)Z
/*     */     //   7: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #123	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	8	0	this	Lcom/atlas/common/fabric/lookup/screen/views/LookupLogsView$LogEntry;
/*     */     //   0	8	1	o	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   private LogEntry(String timestamp, String type, String content, class_1799 stack, List<class_1799> extraStacks, List<class_1799> rewardStacks, String otherPlayerUuid, String otherPlayerName, String rerollStat, int rerollOldValue, int rerollNewValue, JsonObject primaryPokemon, List<JsonObject> extraPokemon, List<JsonObject> rewardPokemon, LookupLogsView.AuctionData auction, String rerollSubtype, String crateSourceType, String crateSourceDisplayName) {
/* 123 */     this.timestamp = timestamp; this.type = type; this.content = content; this.stack = stack; this.extraStacks = extraStacks; this.rewardStacks = rewardStacks; this.otherPlayerUuid = otherPlayerUuid; this.otherPlayerName = otherPlayerName; this.rerollStat = rerollStat; this.rerollOldValue = rerollOldValue; this.rerollNewValue = rerollNewValue; this.primaryPokemon = primaryPokemon; this.extraPokemon = extraPokemon; this.rewardPokemon = rewardPokemon; this.auction = auction; this.rerollSubtype = rerollSubtype; this.crateSourceType = crateSourceType; this.crateSourceDisplayName = crateSourceDisplayName; } public String timestamp() { return this.timestamp; } public String type() { return this.type; } public String content() { return this.content; } public class_1799 stack() { return this.stack; } public List<class_1799> extraStacks() { return this.extraStacks; } public List<class_1799> rewardStacks() { return this.rewardStacks; } public String otherPlayerUuid() { return this.otherPlayerUuid; } public String otherPlayerName() { return this.otherPlayerName; } public String rerollStat() { return this.rerollStat; } public int rerollOldValue() { return this.rerollOldValue; } public int rerollNewValue() { return this.rerollNewValue; } public JsonObject primaryPokemon() { return this.primaryPokemon; } public List<JsonObject> extraPokemon() { return this.extraPokemon; } public List<JsonObject> rewardPokemon() { return this.rewardPokemon; } public LookupLogsView.AuctionData auction() { return this.auction; } public String rerollSubtype() { return this.rerollSubtype; } public String crateSourceType() { return this.crateSourceType; } public String crateSourceDisplayName() { return this.crateSourceDisplayName; }
/*     */ 
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\lookup\screen\views\LookupLogsView$LogEntry.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */