/*    */ package com.atlas.common.fabric.lookup.screen.views;
/*    */ 
/*    */ import net.minecraft.class_1799;
/*    */ import org.jetbrains.annotations.Nullable;
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
/*    */ final class LedgerEntry
/*    */   extends Record
/*    */ {
/*    */   private final String timestamp;
/*    */   private final String action;
/*    */   private final String objectName;
/*    */   private final String coords;
/*    */   private final String server;
/*    */   private final String world;
/*    */   private final int blockX;
/*    */   private final int blockY;
/*    */   private final int blockZ;
/*    */   private final String extraData;
/*    */   private final boolean rolledBack;
/*    */   @Nullable
/*    */   private final class_1799 itemStack;
/*    */   
/*    */   public final String toString() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/lookup/screen/views/LookupLedgerView$LedgerEntry;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #90	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/fabric/lookup/screen/views/LookupLedgerView$LedgerEntry;
/*    */   }
/*    */   
/*    */   public final int hashCode() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/lookup/screen/views/LookupLedgerView$LedgerEntry;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #90	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/fabric/lookup/screen/views/LookupLedgerView$LedgerEntry;
/*    */   }
/*    */   
/*    */   public final boolean equals(Object o) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/lookup/screen/views/LookupLedgerView$LedgerEntry;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #90	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lcom/atlas/common/fabric/lookup/screen/views/LookupLedgerView$LedgerEntry;
/*    */     //   0	8	1	o	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   private LedgerEntry(String timestamp, String action, String objectName, String coords, String server, String world, int blockX, int blockY, int blockZ, String extraData, boolean rolledBack, @Nullable class_1799 itemStack) {
/* 90 */     this.timestamp = timestamp; this.action = action; this.objectName = objectName; this.coords = coords; this.server = server; this.world = world; this.blockX = blockX; this.blockY = blockY; this.blockZ = blockZ; this.extraData = extraData; this.rolledBack = rolledBack; this.itemStack = itemStack; } public String timestamp() { return this.timestamp; } public String action() { return this.action; } public String objectName() { return this.objectName; } public String coords() { return this.coords; } public String server() { return this.server; } public String world() { return this.world; } public int blockX() { return this.blockX; } public int blockY() { return this.blockY; } public int blockZ() { return this.blockZ; } public String extraData() { return this.extraData; } public boolean rolledBack() { return this.rolledBack; } @Nullable public class_1799 itemStack() { return this.itemStack; }
/*    */ 
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\lookup\screen\views\LookupLedgerView$LedgerEntry.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */