/*    */ package com.atlas.common.fabric.patchnotes.network;
/*    */ 
/*    */ import java.util.List;
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
/*    */ public final class PatchNoteEntry
/*    */   extends Record
/*    */ {
/*    */   private final String messageId;
/*    */   private final String title;
/*    */   private final List<PatchNotesNetwork.PatchNoteLineEntry> lines;
/*    */   private final String jumpUrl;
/*    */   private final long publishedAt;
/*    */   
/*    */   public final String toString() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/patchnotes/network/PatchNotesNetwork$PatchNoteEntry;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #44	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/fabric/patchnotes/network/PatchNotesNetwork$PatchNoteEntry;
/*    */   }
/*    */   
/*    */   public final int hashCode() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/patchnotes/network/PatchNotesNetwork$PatchNoteEntry;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #44	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/fabric/patchnotes/network/PatchNotesNetwork$PatchNoteEntry;
/*    */   }
/*    */   
/*    */   public final boolean equals(Object o) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/patchnotes/network/PatchNotesNetwork$PatchNoteEntry;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #44	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lcom/atlas/common/fabric/patchnotes/network/PatchNotesNetwork$PatchNoteEntry;
/*    */     //   0	8	1	o	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public PatchNoteEntry(String messageId, String title, List<PatchNotesNetwork.PatchNoteLineEntry> lines, String jumpUrl, long publishedAt) {
/* 44 */     this.messageId = messageId; this.title = title; this.lines = lines; this.jumpUrl = jumpUrl; this.publishedAt = publishedAt; } public String messageId() { return this.messageId; } public String title() { return this.title; } public List<PatchNotesNetwork.PatchNoteLineEntry> lines() { return this.lines; } public String jumpUrl() { return this.jumpUrl; } public long publishedAt() { return this.publishedAt; }
/*    */ 
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\patchnotes\network\PatchNotesNetwork$PatchNoteEntry.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */