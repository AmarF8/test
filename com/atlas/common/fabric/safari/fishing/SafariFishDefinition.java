/*    */ package com.atlas.common.fabric.safari.fishing;
/*    */ public final class SafariFishDefinition extends Record { @NotNull
/*    */   private final String id; @NotNull
/*    */   private final String displayName; @NotNull
/*    */   private final SafariFishRarity rarity; @NotNull
/*    */   private final List<Variant> variants; private final double minSizeScale;
/*    */   private final double maxSizeScale;
/*    */   
/*  9 */   public SafariFishDefinition(@NotNull String id, @NotNull String displayName, @NotNull SafariFishRarity rarity, @NotNull List<Variant> variants, double minSizeScale, double maxSizeScale, double minWeightKg, double maxWeightKg, double reelDifficulty, double tensionDifficulty, double baseSafeZoneWidth, boolean deepWaterPreferred, @NotNull Set<String> biomeHints) { this.id = id; this.displayName = displayName; this.rarity = rarity; this.variants = variants; this.minSizeScale = minSizeScale; this.maxSizeScale = maxSizeScale; this.minWeightKg = minWeightKg; this.maxWeightKg = maxWeightKg; this.reelDifficulty = reelDifficulty; this.tensionDifficulty = tensionDifficulty; this.baseSafeZoneWidth = baseSafeZoneWidth; this.deepWaterPreferred = deepWaterPreferred; this.biomeHints = biomeHints; } private final double minWeightKg; private final double maxWeightKg; private final double reelDifficulty; private final double tensionDifficulty; private final double baseSafeZoneWidth; private final boolean deepWaterPreferred; @NotNull private final Set<String> biomeHints; public final String toString() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/safari/fishing/SafariFishDefinition;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #9	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/fabric/safari/fishing/SafariFishDefinition; } public final int hashCode() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/safari/fishing/SafariFishDefinition;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #9	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/fabric/safari/fishing/SafariFishDefinition; } public final boolean equals(Object o) { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/safari/fishing/SafariFishDefinition;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #9	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lcom/atlas/common/fabric/safari/fishing/SafariFishDefinition;
/*  9 */     //   0	8	1	o	Ljava/lang/Object; } @NotNull public String id() { return this.id; } @NotNull public String displayName() { return this.displayName; } @NotNull public SafariFishRarity rarity() { return this.rarity; } @NotNull public List<Variant> variants() { return this.variants; } public double minSizeScale() { return this.minSizeScale; } public double maxSizeScale() { return this.maxSizeScale; } public double minWeightKg() { return this.minWeightKg; } public double maxWeightKg() { return this.maxWeightKg; } public double reelDifficulty() { return this.reelDifficulty; } public double tensionDifficulty() { return this.tensionDifficulty; } public double baseSafeZoneWidth() { return this.baseSafeZoneWidth; } public boolean deepWaterPreferred() { return this.deepWaterPreferred; } @NotNull public Set<String> biomeHints() { return this.biomeHints; }
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
/*    */   public Variant pickVariant(@NotNull class_5819 random) {
/* 25 */     return this.variants.get(random.method_43048(this.variants.size()));
/*    */   }
/*    */   
/*    */   public double rollSize(@NotNull class_5819 random) {
/* 29 */     return this.minSizeScale + (this.maxSizeScale - this.minSizeScale) * random.method_43058();
/*    */   }
/*    */   
/*    */   public double rollWeightKg(@NotNull class_5819 random) {
/* 33 */     return this.minWeightKg + (this.maxWeightKg - this.minWeightKg) * random.method_43058();
/*    */   }
/*    */   
/*    */   public double biomeScore(@NotNull String biomePath) {
/* 37 */     if (this.biomeHints.isEmpty()) return 1.0D;
/*    */     
/* 39 */     double score = 0.55D;
/* 40 */     for (String hint : this.biomeHints) {
/* 41 */       if (biomePath.contains(hint)) {
/* 42 */         score += 0.4D;
/*    */       }
/*    */     } 
/* 45 */     if (this.deepWaterPreferred && biomePath.contains("deep")) {
/* 46 */       score += 0.35D;
/*    */     }
/* 48 */     return Math.min(1.8D, score);
/*    */   } public static final class Variant extends Record { @NotNull
/*    */     private final String modelKey; @NotNull
/* 51 */     private final String displayName; public Variant(@NotNull String modelKey, @NotNull String displayName) { this.modelKey = modelKey; this.displayName = displayName; } public final String toString() { // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/safari/fishing/SafariFishDefinition$Variant;)Ljava/lang/String;
/*    */       //   6: areturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #51	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	7	0	this	Lcom/atlas/common/fabric/safari/fishing/SafariFishDefinition$Variant; } public final int hashCode() { // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/safari/fishing/SafariFishDefinition$Variant;)I
/*    */       //   6: ireturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #51	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	7	0	this	Lcom/atlas/common/fabric/safari/fishing/SafariFishDefinition$Variant; } public final boolean equals(Object o) { // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: aload_1
/*    */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/safari/fishing/SafariFishDefinition$Variant;Ljava/lang/Object;)Z
/*    */       //   7: ireturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #51	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	8	0	this	Lcom/atlas/common/fabric/safari/fishing/SafariFishDefinition$Variant;
/* 51 */       //   0	8	1	o	Ljava/lang/Object; } @NotNull public String modelKey() { return this.modelKey; } @NotNull public String displayName() { return this.displayName; }
/*    */      }
/*    */    }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\safari\fishing\SafariFishDefinition.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */