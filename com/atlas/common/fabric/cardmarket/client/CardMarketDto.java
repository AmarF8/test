/*     */ package com.atlas.common.fabric.cardmarket.client;
/*     */ 
/*     */ import com.google.gson.JsonObject;
/*     */ import java.util.List;
/*     */ 
/*     */ public final class CardMarketDto {
/*     */   public static final class Listing extends Record { private final String id;
/*     */     private final String sellerName;
/*     */     private final String cardId;
/*     */     private final int number;
/*     */     private final String cardName;
/*     */     private final String rarity;
/*     */     private final String rarityName;
/*     */     private final String texture;
/*     */     private final int serial;
/*     */     private final String kind;
/*     */     private final long priceCoins;
/*     */     private final long priceUsdCents;
/*     */     private final boolean mine;
/*     */     
/*  21 */     public Listing(String id, String sellerName, String cardId, int number, String cardName, String rarity, String rarityName, String texture, int serial, String kind, long priceCoins, long priceUsdCents, boolean mine) { this.id = id; this.sellerName = sellerName; this.cardId = cardId; this.number = number; this.cardName = cardName; this.rarity = rarity; this.rarityName = rarityName; this.texture = texture; this.serial = serial; this.kind = kind; this.priceCoins = priceCoins; this.priceUsdCents = priceUsdCents; this.mine = mine; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/cardmarket/client/CardMarketDto$Listing;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #21	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*  21 */       //   0	7	0	this	Lcom/atlas/common/fabric/cardmarket/client/CardMarketDto$Listing; } public String id() { return this.id; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/cardmarket/client/CardMarketDto$Listing;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #21	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/cardmarket/client/CardMarketDto$Listing; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/cardmarket/client/CardMarketDto$Listing;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #21	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lcom/atlas/common/fabric/cardmarket/client/CardMarketDto$Listing;
/*  21 */       //   0	8	1	o	Ljava/lang/Object; } public String sellerName() { return this.sellerName; } public String cardId() { return this.cardId; } public int number() { return this.number; } public String cardName() { return this.cardName; } public String rarity() { return this.rarity; } public String rarityName() { return this.rarityName; } public String texture() { return this.texture; } public int serial() { return this.serial; } public String kind() { return this.kind; } public long priceCoins() { return this.priceCoins; } public long priceUsdCents() { return this.priceUsdCents; } public boolean mine() { return this.mine; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean isCoins() {
/*  36 */       return "COINS".equals(this.kind);
/*     */     } }
/*     */   public static final class Snapshot extends Record { private final String filter; private final long viewerCoins; private final long viewerCreditCents; private final List<CardMarketDto.Listing> listings;
/*     */     
/*  40 */     public Snapshot(String filter, long viewerCoins, long viewerCreditCents, List<CardMarketDto.Listing> listings) { this.filter = filter; this.viewerCoins = viewerCoins; this.viewerCreditCents = viewerCreditCents; this.listings = listings; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/cardmarket/client/CardMarketDto$Snapshot;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #40	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/cardmarket/client/CardMarketDto$Snapshot; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/cardmarket/client/CardMarketDto$Snapshot;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #40	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/cardmarket/client/CardMarketDto$Snapshot; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/cardmarket/client/CardMarketDto$Snapshot;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #40	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lcom/atlas/common/fabric/cardmarket/client/CardMarketDto$Snapshot;
/*  40 */       //   0	8	1	o	Ljava/lang/Object; } public String filter() { return this.filter; } public long viewerCoins() { return this.viewerCoins; } public long viewerCreditCents() { return this.viewerCreditCents; } public List<CardMarketDto.Listing> listings() { return this.listings; }
/*     */      }
/*     */ 
/*     */   
/*     */   public static final class Result extends Record {
/*     */     private final boolean ok;
/*     */     private final String message;
/*     */     
/*  48 */     public Result(boolean ok, String message) { this.ok = ok; this.message = message; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/cardmarket/client/CardMarketDto$Result;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #48	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/cardmarket/client/CardMarketDto$Result; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/cardmarket/client/CardMarketDto$Result;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #48	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/cardmarket/client/CardMarketDto$Result; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/cardmarket/client/CardMarketDto$Result;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #48	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lcom/atlas/common/fabric/cardmarket/client/CardMarketDto$Result;
/*  48 */       //   0	8	1	o	Ljava/lang/Object; } public boolean ok() { return this.ok; } public String message() { return this.message; }
/*     */      }
/*     */   public static Snapshot parseSnapshot(String json) {
/*  51 */     JsonObject root = obj(json);
/*  52 */     List<Listing> listings = new ArrayList<>();
/*  53 */     String filter = "all";
/*  54 */     long coins = 0L, credit = 0L;
/*  55 */     if (root != null) {
/*  56 */       filter = str(root, "filter", "all");
/*  57 */       coins = lng(root, "viewerCoins");
/*  58 */       credit = lng(root, "viewerCreditCents");
/*  59 */       if (root.has("listings") && root.get("listings").isJsonArray()) {
/*  60 */         for (JsonElement e : root.getAsJsonArray("listings")) {
/*  61 */           if (!e.isJsonObject())
/*  62 */             continue;  JsonObject l = e.getAsJsonObject();
/*  63 */           listings.add(new Listing(
/*  64 */                 str(l, "id", ""), 
/*  65 */                 str(l, "sellerName", "?"), 
/*  66 */                 str(l, "cardId", ""), 
/*  67 */                 intv(l, "number", 0), 
/*  68 */                 str(l, "cardName", "???"), 
/*  69 */                 str(l, "rarity", "common"), 
/*  70 */                 str(l, "rarityName", "Common"), 
/*  71 */                 str(l, "texture", ""), 
/*  72 */                 intv(l, "serial", 0), 
/*  73 */                 str(l, "kind", "COINS"), 
/*  74 */                 lng(l, "priceCoins"), 
/*  75 */                 lng(l, "priceUsdCents"), 
/*  76 */                 bool(l, "mine", false)));
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/*  81 */     return new Snapshot(filter, coins, credit, listings);
/*     */   }
/*     */   
/*     */   public static Result parseResult(String json) {
/*  85 */     JsonObject root = obj(json);
/*  86 */     if (root == null) return new Result(false, ""); 
/*  87 */     return new Result(bool(root, "ok", false), str(root, "message", ""));
/*     */   }
/*     */ 
/*     */   
/*     */   private static JsonObject obj(String json) {
/*  92 */     if (json == null || json.isBlank()) return null; 
/*     */     try {
/*  94 */       JsonElement e = JsonParser.parseString(json);
/*  95 */       return e.isJsonObject() ? e.getAsJsonObject() : null;
/*  96 */     } catch (Exception ex) {
/*  97 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static String str(JsonObject o, String k, String d) {
/* 102 */     return (o.has(k) && o.get(k).isJsonPrimitive()) ? o.get(k).getAsString() : d;
/*     */   }
/*     */   private static int intv(JsonObject o, String k, int d) {
/*     */     
/* 106 */     try { return (o.has(k) && o.get(k).isJsonPrimitive()) ? o.get(k).getAsInt() : d; } catch (Exception e) { return d; }
/*     */   
/*     */   } private static long lng(JsonObject o, String k) {
/*     */     
/* 110 */     try { return (o.has(k) && o.get(k).isJsonPrimitive()) ? o.get(k).getAsLong() : 0L; } catch (Exception e) { return 0L; }
/*     */   
/*     */   } private static boolean bool(JsonObject o, String k, boolean d) {
/*     */     
/* 114 */     try { return (o.has(k) && o.get(k).isJsonPrimitive()) ? o.get(k).getAsBoolean() : d; } catch (Exception e) { return d; }
/*     */   
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\cardmarket\client\CardMarketDto.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */