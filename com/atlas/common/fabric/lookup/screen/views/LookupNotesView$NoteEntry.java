/*    */ package com.atlas.common.fabric.lookup.screen.views;
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
/*    */ final class NoteEntry
/*    */   extends Record
/*    */ {
/*    */   private final String id;
/*    */   private final String authorUuid;
/*    */   private final String authorName;
/*    */   private final String timestamp;
/*    */   private final String message;
/*    */   private final boolean canDelete;
/*    */   private final boolean overrideDelete;
/*    */   
/*    */   public final String toString() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/lookup/screen/views/LookupNotesView$NoteEntry;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #69	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/fabric/lookup/screen/views/LookupNotesView$NoteEntry;
/*    */   }
/*    */   
/*    */   public final int hashCode() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/lookup/screen/views/LookupNotesView$NoteEntry;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #69	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/fabric/lookup/screen/views/LookupNotesView$NoteEntry;
/*    */   }
/*    */   
/*    */   public final boolean equals(Object o) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/lookup/screen/views/LookupNotesView$NoteEntry;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #69	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lcom/atlas/common/fabric/lookup/screen/views/LookupNotesView$NoteEntry;
/*    */     //   0	8	1	o	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   private NoteEntry(String id, String authorUuid, String authorName, String timestamp, String message, boolean canDelete, boolean overrideDelete) {
/* 69 */     this.id = id; this.authorUuid = authorUuid; this.authorName = authorName; this.timestamp = timestamp; this.message = message; this.canDelete = canDelete; this.overrideDelete = overrideDelete; } public String id() { return this.id; } public String authorUuid() { return this.authorUuid; } public String authorName() { return this.authorName; } public String timestamp() { return this.timestamp; } public String message() { return this.message; } public boolean canDelete() { return this.canDelete; } public boolean overrideDelete() { return this.overrideDelete; }
/*    */ 
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\lookup\screen\views\LookupNotesView$NoteEntry.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */