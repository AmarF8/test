/*     */ package com.atlas.common.fabric.cardcollection.client;
/*     */ 
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParser;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class CardCollectionDto
/*     */ {
/*     */   public static final class MilestoneChip
/*     */     extends Record
/*     */   {
/*     */     private final int percent;
/*     */     private final boolean reached;
/*     */     
/*     */     public MilestoneChip(int percent, boolean reached) {
/*  26 */       this.percent = percent; this.reached = reached; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$MilestoneChip;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #26	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$MilestoneChip; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$MilestoneChip;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #26	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$MilestoneChip; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$MilestoneChip;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #26	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$MilestoneChip;
/*  26 */       //   0	8	1	o	Ljava/lang/Object; } public int percent() { return this.percent; } public boolean reached() { return this.reached; }
/*     */      }
/*     */   public static final class SetOverview extends Record { private final String id; private final String name; private final String coverTexture; private final int uniqueOwned; private final int totalUnique; private final int totalOwned; private final int packsOwned; private final int completion; private final int nextMilestone; private final List<CardCollectionDto.MilestoneChip> milestones;
/*  29 */     public SetOverview(String id, String name, String coverTexture, int uniqueOwned, int totalUnique, int totalOwned, int packsOwned, int completion, int nextMilestone, List<CardCollectionDto.MilestoneChip> milestones) { this.id = id; this.name = name; this.coverTexture = coverTexture; this.uniqueOwned = uniqueOwned; this.totalUnique = totalUnique; this.totalOwned = totalOwned; this.packsOwned = packsOwned; this.completion = completion; this.nextMilestone = nextMilestone; this.milestones = milestones; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$SetOverview;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #29	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*  29 */       //   0	7	0	this	Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$SetOverview; } public String id() { return this.id; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$SetOverview;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #29	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$SetOverview; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$SetOverview;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #29	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$SetOverview;
/*  29 */       //   0	8	1	o	Ljava/lang/Object; } public String name() { return this.name; } public String coverTexture() { return this.coverTexture; } public int uniqueOwned() { return this.uniqueOwned; } public int totalUnique() { return this.totalUnique; } public int totalOwned() { return this.totalOwned; } public int packsOwned() { return this.packsOwned; } public int completion() { return this.completion; } public int nextMilestone() { return this.nextMilestone; } public List<CardCollectionDto.MilestoneChip> milestones() { return this.milestones; }
/*     */      }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final class Overview
/*     */     extends Record
/*     */   {
/*     */     private final List<CardCollectionDto.SetOverview> sets;
/*     */ 
/*     */ 
/*     */     
/*     */     public Overview(List<CardCollectionDto.SetOverview> sets) {
/*  43 */       this.sets = sets; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$Overview;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #43	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$Overview; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$Overview;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #43	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$Overview; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$Overview;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #43	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$Overview;
/*  43 */       //   0	8	1	o	Ljava/lang/Object; } public List<CardCollectionDto.SetOverview> sets() { return this.sets; } public boolean isEmpty() {
/*  44 */       return this.sets.isEmpty();
/*     */     } }
/*     */   public static final class BinderCard extends Record { private final String id; private final int number; private final String name; private final String rarity; private final String rarityName; private final String texture; private final int owned; private final int karmaValue; private final boolean serialized; private final int serial; private final int minted; private final List<Integer> serials;
/*     */     
/*  48 */     public BinderCard(String id, int number, String name, String rarity, String rarityName, String texture, int owned, int karmaValue, boolean serialized, int serial, int minted, List<Integer> serials) { this.id = id; this.number = number; this.name = name; this.rarity = rarity; this.rarityName = rarityName; this.texture = texture; this.owned = owned; this.karmaValue = karmaValue; this.serialized = serialized; this.serial = serial; this.minted = minted; this.serials = serials; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$BinderCard;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #48	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$BinderCard; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$BinderCard;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #48	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$BinderCard; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$BinderCard;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #48	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$BinderCard;
/*  48 */       //   0	8	1	o	Ljava/lang/Object; } public String id() { return this.id; } public int number() { return this.number; } public String name() { return this.name; } public String rarity() { return this.rarity; } public String rarityName() { return this.rarityName; } public String texture() { return this.texture; } public int owned() { return this.owned; } public int karmaValue() { return this.karmaValue; } public boolean serialized() { return this.serialized; } public int serial() { return this.serial; } public int minted() { return this.minted; } public List<Integer> serials() { return this.serials; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean isOwned() {
/*  62 */       return (this.owned > 0);
/*     */     } public boolean hasSerial() {
/*  64 */       return (this.serialized && this.serial > 0);
/*     */     } public int serialCount() {
/*  66 */       return (this.serials == null) ? 0 : this.serials.size();
/*     */     } }
/*     */   public static final class BurnResult extends Record { private final boolean ok; private final int karma; private final String message; private final String cardId;
/*     */     
/*  70 */     public BurnResult(boolean ok, int karma, String message, String cardId) { this.ok = ok; this.karma = karma; this.message = message; this.cardId = cardId; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$BurnResult;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #70	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$BurnResult; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$BurnResult;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #70	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$BurnResult; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$BurnResult;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #70	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$BurnResult;
/*  70 */       //   0	8	1	o	Ljava/lang/Object; } public boolean ok() { return this.ok; } public int karma() { return this.karma; } public String message() { return this.message; } public String cardId() { return this.cardId; }
/*     */      }
/*     */   public static final class BinderData extends Record { private final String setId; private final String setName; private final int page; private final int totalPages; private final int pageSize; private final int uniqueOwned; private final int totalUnique; private final int totalOwned; private final int completion; private final CardCollectionDto.BurnResult burnResult; private final List<CardCollectionDto.BinderCard> cards;
/*  73 */     public BinderData(String setId, String setName, int page, int totalPages, int pageSize, int uniqueOwned, int totalUnique, int totalOwned, int completion, CardCollectionDto.BurnResult burnResult, List<CardCollectionDto.BinderCard> cards) { this.setId = setId; this.setName = setName; this.page = page; this.totalPages = totalPages; this.pageSize = pageSize; this.uniqueOwned = uniqueOwned; this.totalUnique = totalUnique; this.totalOwned = totalOwned; this.completion = completion; this.burnResult = burnResult; this.cards = cards; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$BinderData;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #73	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$BinderData; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$BinderData;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #73	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$BinderData; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$BinderData;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #73	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$BinderData;
/*  73 */       //   0	8	1	o	Ljava/lang/Object; } public String setId() { return this.setId; } public String setName() { return this.setName; } public int page() { return this.page; } public int totalPages() { return this.totalPages; } public int pageSize() { return this.pageSize; } public int uniqueOwned() { return this.uniqueOwned; } public int totalUnique() { return this.totalUnique; } public int totalOwned() { return this.totalOwned; } public int completion() { return this.completion; } public CardCollectionDto.BurnResult burnResult() { return this.burnResult; } public List<CardCollectionDto.BinderCard> cards() { return this.cards; }
/*     */      }
/*     */   public static final class PackCard extends Record { private final String id;
/*     */     private final int number;
/*     */     private final String name;
/*     */     private final String rarity;
/*     */     private final String rarityName;
/*     */     private final String texture;
/*     */     private final int owned;
/*     */     private final boolean isNew;
/*     */     private final int weight;
/*     */     private final boolean serialized;
/*     */     private final int serial;
/*     */     private final int minted;
/*     */     
/*  88 */     public PackCard(String id, int number, String name, String rarity, String rarityName, String texture, int owned, boolean isNew, int weight, boolean serialized, int serial, int minted) { this.id = id; this.number = number; this.name = name; this.rarity = rarity; this.rarityName = rarityName; this.texture = texture; this.owned = owned; this.isNew = isNew; this.weight = weight; this.serialized = serialized; this.serial = serial; this.minted = minted; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$PackCard;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #88	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$PackCard; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$PackCard;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #88	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$PackCard; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$PackCard;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #88	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$PackCard;
/*  88 */       //   0	8	1	o	Ljava/lang/Object; } public String id() { return this.id; } public int number() { return this.number; } public String name() { return this.name; } public String rarity() { return this.rarity; } public String rarityName() { return this.rarityName; } public String texture() { return this.texture; } public int owned() { return this.owned; } public boolean isNew() { return this.isNew; } public int weight() { return this.weight; } public boolean serialized() { return this.serialized; } public int serial() { return this.serial; } public int minted() { return this.minted; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean hasSerial() {
/* 102 */       return (this.serialized && this.serial > 0);
/*     */     } }
/*     */   public static final class PackData extends Record { private final String setId; private final String setName; private final String packTexture; private final String packKind; private final String packKindName; private final int packsRemaining; private final boolean canOpenAnother; private final List<CardCollectionDto.PackCard> cards;
/*     */     
/* 106 */     public PackData(String setId, String setName, String packTexture, String packKind, String packKindName, int packsRemaining, boolean canOpenAnother, List<CardCollectionDto.PackCard> cards) { this.setId = setId; this.setName = setName; this.packTexture = packTexture; this.packKind = packKind; this.packKindName = packKindName; this.packsRemaining = packsRemaining; this.canOpenAnother = canOpenAnother; this.cards = cards; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$PackData;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #106	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$PackData; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$PackData;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #106	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$PackData; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$PackData;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #106	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$PackData;
/* 106 */       //   0	8	1	o	Ljava/lang/Object; } public String setId() { return this.setId; } public String setName() { return this.setName; } public String packTexture() { return this.packTexture; } public String packKind() { return this.packKind; } public String packKindName() { return this.packKindName; } public int packsRemaining() { return this.packsRemaining; } public boolean canOpenAnother() { return this.canOpenAnother; } public List<CardCollectionDto.PackCard> cards() { return this.cards; }
/*     */      }
/*     */ 
/*     */   
/*     */   public static final class MilestoneInfo
/*     */     extends Record
/*     */   {
/*     */     private final String setId;
/*     */     private final String setName;
/*     */     private final int percent;
/*     */     private final String reward;
/*     */     
/* 118 */     public MilestoneInfo(String setId, String setName, int percent, String reward) { this.setId = setId; this.setName = setName; this.percent = percent; this.reward = reward; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$MilestoneInfo;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #118	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$MilestoneInfo; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$MilestoneInfo;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #118	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$MilestoneInfo; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$MilestoneInfo;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #118	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$MilestoneInfo;
/* 118 */       //   0	8	1	o	Ljava/lang/Object; } public String setId() { return this.setId; } public String setName() { return this.setName; } public int percent() { return this.percent; } public String reward() { return this.reward; }
/*     */      }
/*     */   public static final class SkillNode extends Record { private final String id; private final String name; private final String description; private final int level; private final int maxLevel; private final int cost; private final double percentPerLevel; private final double currentPercent; private final boolean unlocked; private final boolean available;
/* 121 */     public SkillNode(String id, String name, String description, int level, int maxLevel, int cost, double percentPerLevel, double currentPercent, boolean unlocked, boolean available) { this.id = id; this.name = name; this.description = description; this.level = level; this.maxLevel = maxLevel; this.cost = cost; this.percentPerLevel = percentPerLevel; this.currentPercent = currentPercent; this.unlocked = unlocked; this.available = available; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$SkillNode;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #121	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$SkillNode; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$SkillNode;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #121	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$SkillNode; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$SkillNode;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #121	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$SkillNode;
/* 121 */       //   0	8	1	o	Ljava/lang/Object; } public String id() { return this.id; } public String name() { return this.name; } public String description() { return this.description; } public int level() { return this.level; } public int maxLevel() { return this.maxLevel; } public int cost() { return this.cost; } public double percentPerLevel() { return this.percentPerLevel; } public double currentPercent() { return this.currentPercent; } public boolean unlocked() { return this.unlocked; } public boolean available() { return this.available; }
/*     */      }
/*     */ 
/*     */   
/*     */   public static final class SkillTreeData
/*     */     extends Record
/*     */   {
/*     */     private final int totalPoints;
/*     */     private final int spentPoints;
/*     */     private final int availablePoints;
/*     */     private final List<CardCollectionDto.SkillNode> nodes;
/*     */     
/*     */     public SkillTreeData(int totalPoints, int spentPoints, int availablePoints, List<CardCollectionDto.SkillNode> nodes)
/*     */     {
/* 135 */       this.totalPoints = totalPoints; this.spentPoints = spentPoints; this.availablePoints = availablePoints; this.nodes = nodes; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$SkillTreeData;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #135	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$SkillTreeData; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$SkillTreeData;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #135	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$SkillTreeData; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$SkillTreeData;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #135	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lcom/atlas/common/fabric/cardcollection/client/CardCollectionDto$SkillTreeData;
/* 135 */       //   0	8	1	o	Ljava/lang/Object; } public int totalPoints() { return this.totalPoints; } public int spentPoints() { return this.spentPoints; } public int availablePoints() { return this.availablePoints; } public List<CardCollectionDto.SkillNode> nodes() { return this.nodes; }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Overview parseOverview(String json) {
/* 145 */     List<SetOverview> sets = new ArrayList<>();
/* 146 */     JsonObject root = obj(json);
/* 147 */     if (root != null && root.has("sets") && root.get("sets").isJsonArray()) {
/* 148 */       for (JsonElement e : root.getAsJsonArray("sets")) {
/* 149 */         if (!e.isJsonObject())
/* 150 */           continue;  JsonObject s = e.getAsJsonObject();
/* 151 */         sets.add(new SetOverview(
/* 152 */               str(s, "id", ""), 
/* 153 */               str(s, "name", "Unknown Set"), 
/* 154 */               str(s, "coverTexture", ""), 
/* 155 */               intv(s, "uniqueOwned", 0), 
/* 156 */               intv(s, "totalUnique", 0), 
/* 157 */               intv(s, "totalOwned", 0), 
/* 158 */               intv(s, "packsOwned", 0), 
/* 159 */               intv(s, "completion", 0), 
/* 160 */               intv(s, "nextMilestone", -1), 
/* 161 */               parseMilestones(s)));
/*     */       } 
/*     */     }
/*     */     
/* 165 */     return new Overview(sets);
/*     */   }
/*     */   
/*     */   private static List<MilestoneChip> parseMilestones(JsonObject s) {
/* 169 */     List<MilestoneChip> chips = new ArrayList<>();
/* 170 */     if (s.has("milestones") && s.get("milestones").isJsonArray()) {
/* 171 */       for (JsonElement e : s.getAsJsonArray("milestones")) {
/* 172 */         if (!e.isJsonObject())
/* 173 */           continue;  JsonObject m = e.getAsJsonObject();
/* 174 */         chips.add(new MilestoneChip(intv(m, "percent", 0), bool(m, "reached", false)));
/*     */       } 
/*     */     }
/* 177 */     return chips;
/*     */   }
/*     */   
/*     */   public static BinderData parseBinder(String json) {
/* 181 */     JsonObject root = obj(json);
/* 182 */     if (root == null) return null; 
/* 183 */     List<BinderCard> cards = new ArrayList<>();
/* 184 */     if (root.has("cards") && root.get("cards").isJsonArray()) {
/* 185 */       for (JsonElement e : root.getAsJsonArray("cards")) {
/* 186 */         if (!e.isJsonObject())
/* 187 */           continue;  JsonObject c = e.getAsJsonObject();
/* 188 */         cards.add(new BinderCard(
/* 189 */               str(c, "id", ""), 
/* 190 */               intv(c, "number", 0), 
/* 191 */               str(c, "name", "???"), 
/* 192 */               str(c, "rarity", "common"), 
/* 193 */               str(c, "rarityName", "Common"), 
/* 194 */               str(c, "texture", ""), 
/* 195 */               intv(c, "owned", 0), 
/* 196 */               intv(c, "karmaValue", 0), 
/* 197 */               bool(c, "serialized", false), 
/* 198 */               intv(c, "serial", 0), 
/* 199 */               intv(c, "minted", 0), 
/* 200 */               intArray(c, "serials")));
/*     */       } 
/*     */     }
/*     */     
/* 204 */     BurnResult burn = null;
/* 205 */     if (root.has("burnResult") && root.get("burnResult").isJsonObject()) {
/* 206 */       JsonObject b = root.getAsJsonObject("burnResult");
/* 207 */       burn = new BurnResult(bool(b, "ok", false), intv(b, "karma", 0), str(b, "message", ""), str(b, "cardId", ""));
/*     */     } 
/* 209 */     return new BinderData(
/* 210 */         str(root, "setId", ""), 
/* 211 */         str(root, "setName", "Binder"), 
/* 212 */         intv(root, "page", 1), 
/* 213 */         intv(root, "totalPages", 1), 
/* 214 */         intv(root, "pageSize", 18), 
/* 215 */         intv(root, "uniqueOwned", 0), 
/* 216 */         intv(root, "totalUnique", 0), 
/* 217 */         intv(root, "totalOwned", 0), 
/* 218 */         intv(root, "completion", 0), burn, cards);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static PackData parsePack(String json) {
/* 225 */     JsonObject root = obj(json);
/* 226 */     if (root == null) return null; 
/* 227 */     List<PackCard> cards = new ArrayList<>();
/* 228 */     if (root.has("cards") && root.get("cards").isJsonArray()) {
/* 229 */       for (JsonElement e : root.getAsJsonArray("cards")) {
/* 230 */         if (!e.isJsonObject())
/* 231 */           continue;  JsonObject c = e.getAsJsonObject();
/* 232 */         cards.add(new PackCard(
/* 233 */               str(c, "id", ""), 
/* 234 */               intv(c, "number", 0), 
/* 235 */               str(c, "name", "???"), 
/* 236 */               str(c, "rarity", "common"), 
/* 237 */               str(c, "rarityName", "Common"), 
/* 238 */               str(c, "texture", ""), 
/* 239 */               intv(c, "owned", 0), 
/* 240 */               bool(c, "isNew", false), 
/* 241 */               intv(c, "weight", 1), 
/* 242 */               bool(c, "serialized", false), 
/* 243 */               intv(c, "serial", 0), 
/* 244 */               intv(c, "minted", 0)));
/*     */       } 
/*     */     }
/*     */     
/* 248 */     return new PackData(
/* 249 */         str(root, "setId", ""), 
/* 250 */         str(root, "setName", "Pack"), 
/* 251 */         str(root, "packTexture", ""), 
/* 252 */         str(root, "packKind", "regular"), 
/* 253 */         str(root, "packKindName", "Booster Pack"), 
/* 254 */         intv(root, "packsRemaining", 0), 
/* 255 */         bool(root, "canOpenAnother", false), cards);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static MilestoneInfo parseMilestone(String json) {
/* 261 */     JsonObject root = obj(json);
/* 262 */     if (root == null) return null; 
/* 263 */     return new MilestoneInfo(
/* 264 */         str(root, "setId", ""), 
/* 265 */         str(root, "setName", "Set"), 
/* 266 */         intv(root, "percent", 0), 
/* 267 */         str(root, "reward", "Reward coming soon!"));
/*     */   }
/*     */ 
/*     */   
/*     */   public static SkillTreeData parseSkillTree(String json) {
/* 272 */     JsonObject root = obj(json);
/* 273 */     if (root == null) return new SkillTreeData(0, 0, 0, List.of()); 
/* 274 */     List<SkillNode> nodes = new ArrayList<>();
/* 275 */     if (root.has("nodes") && root.get("nodes").isJsonArray()) {
/* 276 */       for (JsonElement e : root.getAsJsonArray("nodes")) {
/* 277 */         if (!e.isJsonObject())
/* 278 */           continue;  JsonObject n = e.getAsJsonObject();
/* 279 */         nodes.add(new SkillNode(
/* 280 */               str(n, "id", ""), 
/* 281 */               str(n, "name", "Skill"), 
/* 282 */               str(n, "description", ""), 
/* 283 */               intv(n, "level", 0), 
/* 284 */               intv(n, "maxLevel", 0), 
/* 285 */               intv(n, "cost", 1), 
/* 286 */               doublev(n, "percentPerLevel", 0.0D), 
/* 287 */               doublev(n, "currentPercent", 0.0D), 
/* 288 */               bool(n, "unlocked", false), 
/* 289 */               bool(n, "available", false)));
/*     */       } 
/*     */     }
/*     */     
/* 293 */     return new SkillTreeData(
/* 294 */         intv(root, "totalPoints", 0), 
/* 295 */         intv(root, "spentPoints", 0), 
/* 296 */         intv(root, "availablePoints", 0), nodes);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static JsonObject obj(String json) {
/* 304 */     if (json == null || json.isBlank()) return null; 
/*     */     try {
/* 306 */       JsonElement e = JsonParser.parseString(json);
/* 307 */       return e.isJsonObject() ? e.getAsJsonObject() : null;
/* 308 */     } catch (Exception ex) {
/* 309 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static String str(JsonObject o, String key, String def) {
/* 314 */     return (o.has(key) && o.get(key).isJsonPrimitive()) ? o.get(key).getAsString() : def;
/*     */   }
/*     */   
/*     */   private static int intv(JsonObject o, String key, int def) {
/*     */     try {
/* 319 */       return (o.has(key) && o.get(key).isJsonPrimitive()) ? o.get(key).getAsInt() : def;
/* 320 */     } catch (Exception e) {
/* 321 */       return def;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static double doublev(JsonObject o, String key, double def) {
/*     */     try {
/* 327 */       return (o.has(key) && o.get(key).isJsonPrimitive()) ? o.get(key).getAsDouble() : def;
/* 328 */     } catch (Exception e) {
/* 329 */       return def;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static List<Integer> intArray(JsonObject o, String key) {
/* 334 */     if (!o.has(key) || !o.get(key).isJsonArray()) return List.of(); 
/* 335 */     List<Integer> out = new ArrayList<>();
/* 336 */     for (JsonElement e : o.getAsJsonArray(key)) {
/*     */       try {
/* 338 */         if (e.isJsonPrimitive()) out.add(Integer.valueOf(e.getAsInt())); 
/* 339 */       } catch (Exception exception) {}
/*     */     } 
/*     */     
/* 342 */     return out;
/*     */   }
/*     */   
/*     */   private static boolean bool(JsonObject o, String key, boolean def) {
/*     */     try {
/* 347 */       return (o.has(key) && o.get(key).isJsonPrimitive()) ? o.get(key).getAsBoolean() : def;
/* 348 */     } catch (Exception e) {
/* 349 */       return def;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\cardcollection\client\CardCollectionDto.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */