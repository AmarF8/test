/*    */ package com.atlas.common.fabric.skindex.data;
/*    */ public final class SkinDexDetailInfo extends Record { private final String id;
/*    */   private final String name;
/*    */   private final String rawName;
/*    */   private final String color;
/*    */   private final List<SkinEntry> skins;
/*    */   private final int completedCount;
/*    */   private final int totalCount;
/*    */   private final boolean claimed;
/*    */   private final List<String> rewardDescriptions;
/*    */   private final List<AuraEntry> auras;
/*    */   
/* 13 */   public SkinDexDetailInfo(String id, String name, String rawName, String color, List<SkinEntry> skins, int completedCount, int totalCount, boolean claimed, List<String> rewardDescriptions, List<AuraEntry> auras) { this.id = id; this.name = name; this.rawName = rawName; this.color = color; this.skins = skins; this.completedCount = completedCount; this.totalCount = totalCount; this.claimed = claimed; this.rewardDescriptions = rewardDescriptions; this.auras = auras; } public final String toString() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/skindex/data/SkinDexDetailInfo;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #13	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/* 13 */     //   0	7	0	this	Lcom/atlas/common/fabric/skindex/data/SkinDexDetailInfo; } public String id() { return this.id; } public final int hashCode() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/skindex/data/SkinDexDetailInfo;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #13	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/fabric/skindex/data/SkinDexDetailInfo; } public final boolean equals(Object o) { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/skindex/data/SkinDexDetailInfo;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #13	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lcom/atlas/common/fabric/skindex/data/SkinDexDetailInfo;
/* 13 */     //   0	8	1	o	Ljava/lang/Object; } public String name() { return this.name; } public String rawName() { return this.rawName; } public String color() { return this.color; } public List<SkinEntry> skins() { return this.skins; } public int completedCount() { return this.completedCount; } public int totalCount() { return this.totalCount; } public boolean claimed() { return this.claimed; } public List<String> rewardDescriptions() { return this.rewardDescriptions; } public List<AuraEntry> auras() { return this.auras; }
/*    */ 
/*    */ 
/*    */   
/*    */   public static final class SkinEntry
/*    */     extends Record
/*    */   {
/*    */     private final String id;
/*    */     private final String name;
/*    */     private final String pokemonId;
/*    */     private final String aspect;
/*    */     private final boolean unlocked;
/*    */     
/*    */     public SkinEntry(String id, String name, String pokemonId, String aspect, boolean unlocked)
/*    */     {
/* 28 */       this.id = id; this.name = name; this.pokemonId = pokemonId; this.aspect = aspect; this.unlocked = unlocked; } public final String toString() { // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/skindex/data/SkinDexDetailInfo$SkinEntry;)Ljava/lang/String;
/*    */       //   6: areturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #28	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	7	0	this	Lcom/atlas/common/fabric/skindex/data/SkinDexDetailInfo$SkinEntry; } public final int hashCode() { // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/skindex/data/SkinDexDetailInfo$SkinEntry;)I
/*    */       //   6: ireturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #28	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	7	0	this	Lcom/atlas/common/fabric/skindex/data/SkinDexDetailInfo$SkinEntry; } public final boolean equals(Object o) { // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: aload_1
/*    */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/skindex/data/SkinDexDetailInfo$SkinEntry;Ljava/lang/Object;)Z
/*    */       //   7: ireturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #28	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	8	0	this	Lcom/atlas/common/fabric/skindex/data/SkinDexDetailInfo$SkinEntry;
/* 28 */       //   0	8	1	o	Ljava/lang/Object; } public String id() { return this.id; } public String name() { return this.name; } public String pokemonId() { return this.pokemonId; } public String aspect() { return this.aspect; } public boolean unlocked() { return this.unlocked; }
/*    */   
/*    */   }
/*    */   
/*    */   public static final class AuraEntry
/*    */     extends Record {
/*    */     private final String aspect;
/*    */     private final String name;
/*    */     private final boolean unlocked;
/*    */     
/*    */     public AuraEntry(String aspect, String name, boolean unlocked) {
/* 39 */       this.aspect = aspect; this.name = name; this.unlocked = unlocked; } public final String toString() { // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/skindex/data/SkinDexDetailInfo$AuraEntry;)Ljava/lang/String;
/*    */       //   6: areturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #39	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	7	0	this	Lcom/atlas/common/fabric/skindex/data/SkinDexDetailInfo$AuraEntry; } public final int hashCode() { // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/skindex/data/SkinDexDetailInfo$AuraEntry;)I
/*    */       //   6: ireturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #39	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	7	0	this	Lcom/atlas/common/fabric/skindex/data/SkinDexDetailInfo$AuraEntry; } public final boolean equals(Object o) { // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: aload_1
/*    */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/skindex/data/SkinDexDetailInfo$AuraEntry;Ljava/lang/Object;)Z
/*    */       //   7: ireturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #39	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	8	0	this	Lcom/atlas/common/fabric/skindex/data/SkinDexDetailInfo$AuraEntry;
/* 39 */       //   0	8	1	o	Ljava/lang/Object; } public String aspect() { return this.aspect; } public String name() { return this.name; } public boolean unlocked() { return this.unlocked; }
/*    */   
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static SkinDexDetailInfo fromJson(String json) {
/*    */     try {
/* 50 */       JsonObject obj = JsonParser.parseString(json).getAsJsonObject();
/*    */       
/* 52 */       List<SkinEntry> skins = new ArrayList<>();
/* 53 */       if (obj.has("skins") && obj.get("skins").isJsonArray()) {
/* 54 */         for (JsonElement element : obj.getAsJsonArray("skins")) {
/* 55 */           JsonObject skinObj = element.getAsJsonObject();
/* 56 */           skins.add(new SkinEntry(skinObj
/* 57 */                 .get("id").getAsString(), skinObj
/* 58 */                 .get("name").getAsString(), skinObj
/* 59 */                 .get("pokemonId").getAsString(), 
/* 60 */                 skinObj.has("aspect") ? skinObj.get("aspect").getAsString() : "", skinObj
/* 61 */                 .get("unlocked").getAsBoolean()));
/*    */         } 
/*    */       }
/*    */ 
/*    */       
/* 66 */       List<String> rewards = new ArrayList<>();
/* 67 */       if (obj.has("rewards") && obj.get("rewards").isJsonArray()) {
/* 68 */         for (JsonElement r : obj.getAsJsonArray("rewards")) {
/* 69 */           rewards.add(r.getAsString());
/*    */         }
/*    */       }
/*    */       
/* 73 */       List<AuraEntry> auras = new ArrayList<>();
/* 74 */       if (obj.has("auras") && obj.get("auras").isJsonArray()) {
/* 75 */         for (JsonElement el : obj.getAsJsonArray("auras")) {
/* 76 */           JsonObject a = el.getAsJsonObject();
/* 77 */           auras.add(new AuraEntry(a
/* 78 */                 .get("aspect").getAsString(), a
/* 79 */                 .get("name").getAsString(), a
/* 80 */                 .get("unlocked").getAsBoolean()));
/*    */         } 
/*    */       }
/*    */ 
/*    */       
/* 85 */       return new SkinDexDetailInfo(obj
/* 86 */           .get("id").getAsString(), obj
/* 87 */           .get("name").getAsString(), 
/* 88 */           obj.has("rawName") ? obj.get("rawName").getAsString() : obj.get("name").getAsString(), 
/* 89 */           obj.has("color") ? obj.get("color").getAsString() : "#FFFFFF", 
/* 90 */           Collections.unmodifiableList(skins), obj
/* 91 */           .get("completedCount").getAsInt(), obj
/* 92 */           .get("totalCount").getAsInt(), obj
/* 93 */           .get("claimed").getAsBoolean(), 
/* 94 */           Collections.unmodifiableList(rewards), 
/* 95 */           Collections.unmodifiableList(auras));
/*    */     }
/* 97 */     catch (Exception e) {
/* 98 */       return null;
/*    */     } 
/*    */   } }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\skindex\data\SkinDexDetailInfo.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */