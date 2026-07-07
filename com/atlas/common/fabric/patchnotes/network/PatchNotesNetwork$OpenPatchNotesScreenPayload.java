/*    */ package com.atlas.common.fabric.patchnotes.network;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.class_8710;
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
/*    */ public final class OpenPatchNotesScreenPayload
/*    */   extends Record
/*    */   implements class_8710
/*    */ {
/*    */   private final List<PatchNotesNetwork.PatchNoteEntry> patchNotes;
/*    */   
/*    */   public OpenPatchNotesScreenPayload(List<PatchNotesNetwork.PatchNoteEntry> patchNotes) {
/* 47 */     this.patchNotes = patchNotes; } public final String toString() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/patchnotes/network/PatchNotesNetwork$OpenPatchNotesScreenPayload;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #47	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/* 47 */     //   0	7	0	this	Lcom/atlas/common/fabric/patchnotes/network/PatchNotesNetwork$OpenPatchNotesScreenPayload; } public List<PatchNotesNetwork.PatchNoteEntry> patchNotes() { return this.patchNotes; }
/*    */   public final int hashCode() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/patchnotes/network/PatchNotesNetwork$OpenPatchNotesScreenPayload;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #47	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/fabric/patchnotes/network/PatchNotesNetwork$OpenPatchNotesScreenPayload; }
/*    */   public final boolean equals(Object o) { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/patchnotes/network/PatchNotesNetwork$OpenPatchNotesScreenPayload;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #47	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lcom/atlas/common/fabric/patchnotes/network/PatchNotesNetwork$OpenPatchNotesScreenPayload;
/*    */     //   0	8	1	o	Ljava/lang/Object; } public class_8710.class_9154<? extends class_8710> method_56479() {
/* 50 */     return (class_8710.class_9154)PatchNotesNetwork.OPEN_SCREEN_TYPE;
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\patchnotes\network\PatchNotesNetwork$OpenPatchNotesScreenPayload.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */