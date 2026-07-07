/*    */ package com.atlas.common.fabric.safari.fishing;public final class SafariCatchProfile extends Record { @NotNull
/*    */   private final String fishId; @NotNull
/*    */   private final String fishDisplayName; @NotNull
/*    */   private final SafariFishDefinition.Variant variant; @NotNull
/*  5 */   private final SafariFishRarity rarity; private final double sizeScale; private final double weightKg; private final double reelDifficulty; private final double tensionDifficulty; private final double safeZoneWidth; public SafariCatchProfile(@NotNull String fishId, @NotNull String fishDisplayName, @NotNull SafariFishDefinition.Variant variant, @NotNull SafariFishRarity rarity, double sizeScale, double weightKg, double reelDifficulty, double tensionDifficulty, double safeZoneWidth) { this.fishId = fishId; this.fishDisplayName = fishDisplayName; this.variant = variant; this.rarity = rarity; this.sizeScale = sizeScale; this.weightKg = weightKg; this.reelDifficulty = reelDifficulty; this.tensionDifficulty = tensionDifficulty; this.safeZoneWidth = safeZoneWidth; } public final String toString() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/safari/fishing/SafariCatchProfile;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #5	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*  5 */     //   0	7	0	this	Lcom/atlas/common/fabric/safari/fishing/SafariCatchProfile; } @NotNull public String fishId() { return this.fishId; } public final int hashCode() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/safari/fishing/SafariCatchProfile;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #5	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/fabric/safari/fishing/SafariCatchProfile; } public final boolean equals(Object o) { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/safari/fishing/SafariCatchProfile;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #5	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lcom/atlas/common/fabric/safari/fishing/SafariCatchProfile;
/*  5 */     //   0	8	1	o	Ljava/lang/Object; } @NotNull public String fishDisplayName() { return this.fishDisplayName; } @NotNull public SafariFishDefinition.Variant variant() { return this.variant; } @NotNull public SafariFishRarity rarity() { return this.rarity; } public double sizeScale() { return this.sizeScale; } public double weightKg() { return this.weightKg; } public double reelDifficulty() { return this.reelDifficulty; } public double tensionDifficulty() { return this.tensionDifficulty; } public double safeZoneWidth() { return this.safeZoneWidth; }
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
/*    */   public String getDisplayLabel() {
/* 17 */     return this.variant.displayName();
/*    */   }
/*    */   
/*    */   public String getSizeLabel() {
/* 21 */     if (this.sizeScale < 0.92D) return "XS"; 
/* 22 */     if (this.sizeScale < 1.02D) return "S"; 
/* 23 */     if (this.sizeScale < 1.14D) return "M"; 
/* 24 */     if (this.sizeScale < 1.28D) return "L"; 
/* 25 */     return "XL";
/*    */   } }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\safari\fishing\SafariCatchProfile.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */