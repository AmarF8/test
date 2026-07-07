/*    */ package com.atlas.common.fabric.skindex.data;
/*    */ public final class SkinDexLineInfo extends Record { private final String id;
/*    */   private final String name;
/*    */   private final String rawName;
/*    */   private final String color;
/*    */   private final String displayPokemon;
/*    */   private final String displayAspect;
/*    */   private final List<SkinPreview> skins;
/*    */   private final int completedCount;
/*    */   private final int totalCount;
/*    */   private final boolean claimed;
/*    */   private final List<String> rewardDescriptions;
/*    */   
/* 14 */   public SkinDexLineInfo(String id, String name, String rawName, String color, String displayPokemon, String displayAspect, List<SkinPreview> skins, int completedCount, int totalCount, boolean claimed, List<String> rewardDescriptions) { this.id = id; this.name = name; this.rawName = rawName; this.color = color; this.displayPokemon = displayPokemon; this.displayAspect = displayAspect; this.skins = skins; this.completedCount = completedCount; this.totalCount = totalCount; this.claimed = claimed; this.rewardDescriptions = rewardDescriptions; } public final String toString() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/skindex/data/SkinDexLineInfo;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #14	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/* 14 */     //   0	7	0	this	Lcom/atlas/common/fabric/skindex/data/SkinDexLineInfo; } public String id() { return this.id; } public final int hashCode() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/skindex/data/SkinDexLineInfo;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #14	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/fabric/skindex/data/SkinDexLineInfo; } public final boolean equals(Object o) { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/skindex/data/SkinDexLineInfo;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #14	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lcom/atlas/common/fabric/skindex/data/SkinDexLineInfo;
/* 14 */     //   0	8	1	o	Ljava/lang/Object; } public String name() { return this.name; } public String rawName() { return this.rawName; } public String color() { return this.color; } public String displayPokemon() { return this.displayPokemon; } public String displayAspect() { return this.displayAspect; } public List<SkinPreview> skins() { return this.skins; } public int completedCount() { return this.completedCount; } public int totalCount() { return this.totalCount; } public boolean claimed() { return this.claimed; } public List<String> rewardDescriptions() { return this.rewardDescriptions; }
/*    */ 
/*    */   
/*    */   public static final class SkinPreview
/*    */     extends Record
/*    */   {
/*    */     private final String id;
/*    */     private final String name;
/*    */     private final String pokemonId;
/*    */     private final String aspect;
/*    */     
/*    */     public SkinPreview(String id, String name, String pokemonId, String aspect)
/*    */     {
/* 27 */       this.id = id; this.name = name; this.pokemonId = pokemonId; this.aspect = aspect; } public final String toString() { // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/skindex/data/SkinDexLineInfo$SkinPreview;)Ljava/lang/String;
/*    */       //   6: areturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #27	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	7	0	this	Lcom/atlas/common/fabric/skindex/data/SkinDexLineInfo$SkinPreview; } public final int hashCode() { // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/skindex/data/SkinDexLineInfo$SkinPreview;)I
/*    */       //   6: ireturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #27	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	7	0	this	Lcom/atlas/common/fabric/skindex/data/SkinDexLineInfo$SkinPreview; } public final boolean equals(Object o) { // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: aload_1
/*    */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/skindex/data/SkinDexLineInfo$SkinPreview;Ljava/lang/Object;)Z
/*    */       //   7: ireturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #27	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	8	0	this	Lcom/atlas/common/fabric/skindex/data/SkinDexLineInfo$SkinPreview;
/* 27 */       //   0	8	1	o	Ljava/lang/Object; } public String id() { return this.id; } public String name() { return this.name; } public String pokemonId() { return this.pokemonId; } public String aspect() { return this.aspect; }
/*    */   
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static List<SkinDexLineInfo> fromJsonArray(String json) {
/*    */     try {
/* 39 */       JsonArray array = JsonParser.parseString(json).getAsJsonArray();
/* 40 */       List<SkinDexLineInfo> result = new ArrayList<>(array.size());
/* 41 */       for (JsonElement element : array) {
/* 42 */         JsonObject obj = element.getAsJsonObject();
/* 43 */         List<String> rewards = new ArrayList<>();
/* 44 */         if (obj.has("rewards") && obj.get("rewards").isJsonArray()) {
/* 45 */           for (JsonElement r : obj.getAsJsonArray("rewards")) {
/* 46 */             rewards.add(r.getAsString());
/*    */           }
/*    */         }
/*    */         
/* 50 */         List<SkinPreview> skins = new ArrayList<>();
/* 51 */         if (obj.has("skins") && obj.get("skins").isJsonArray()) {
/* 52 */           for (JsonElement skinElement : obj.getAsJsonArray("skins")) {
/* 53 */             JsonObject skinObj = skinElement.getAsJsonObject();
/* 54 */             skins.add(new SkinPreview(
/* 55 */                   skinObj.has("id") ? skinObj.get("id").getAsString() : "", 
/* 56 */                   skinObj.has("name") ? skinObj.get("name").getAsString() : "", 
/* 57 */                   skinObj.has("pokemonId") ? skinObj.get("pokemonId").getAsString() : "", 
/* 58 */                   skinObj.has("aspect") ? skinObj.get("aspect").getAsString() : ""));
/*    */           } 
/*    */         }
/*    */ 
/*    */         
/* 63 */         result.add(new SkinDexLineInfo(obj
/* 64 */               .get("id").getAsString(), obj
/* 65 */               .get("name").getAsString(), 
/* 66 */               obj.has("rawName") ? obj.get("rawName").getAsString() : obj.get("name").getAsString(), 
/* 67 */               obj.has("color") ? obj.get("color").getAsString() : "#FFFFFF", obj
/* 68 */               .get("displayPokemon").getAsString(), obj
/* 69 */               .get("displayAspect").getAsString(), 
/* 70 */               Collections.unmodifiableList(skins), obj
/* 71 */               .get("completedCount").getAsInt(), obj
/* 72 */               .get("totalCount").getAsInt(), obj
/* 73 */               .get("claimed").getAsBoolean(), 
/* 74 */               Collections.unmodifiableList(rewards)));
/*    */       } 
/*    */       
/* 77 */       return result;
/* 78 */     } catch (Exception e) {
/* 79 */       return Collections.emptyList();
/*    */     } 
/*    */   } }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\skindex\data\SkinDexLineInfo.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */