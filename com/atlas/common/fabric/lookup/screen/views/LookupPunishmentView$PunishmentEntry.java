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
/*    */ final class PunishmentEntry
/*    */   extends Record
/*    */ {
/*    */   private final String id;
/*    */   private final String type;
/*    */   private final String reason;
/*    */   private final String issuer;
/*    */   private final String duration;
/*    */   private final String expiry;
/*    */   private final boolean active;
/*    */   private final boolean permanent;
/*    */   private final String timestamp;
/*    */   private final boolean pardoned;
/*    */   
/*    */   public final String toString() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/lookup/screen/views/LookupPunishmentView$PunishmentEntry;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #56	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/fabric/lookup/screen/views/LookupPunishmentView$PunishmentEntry;
/*    */   }
/*    */   
/*    */   public final int hashCode() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/lookup/screen/views/LookupPunishmentView$PunishmentEntry;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #56	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/fabric/lookup/screen/views/LookupPunishmentView$PunishmentEntry;
/*    */   }
/*    */   
/*    */   public final boolean equals(Object o) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/lookup/screen/views/LookupPunishmentView$PunishmentEntry;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #56	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lcom/atlas/common/fabric/lookup/screen/views/LookupPunishmentView$PunishmentEntry;
/*    */     //   0	8	1	o	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   private PunishmentEntry(String id, String type, String reason, String issuer, String duration, String expiry, boolean active, boolean permanent, String timestamp, boolean pardoned) {
/* 56 */     this.id = id; this.type = type; this.reason = reason; this.issuer = issuer; this.duration = duration; this.expiry = expiry; this.active = active; this.permanent = permanent; this.timestamp = timestamp; this.pardoned = pardoned; } public String id() { return this.id; } public String type() { return this.type; } public String reason() { return this.reason; } public String issuer() { return this.issuer; } public String duration() { return this.duration; } public String expiry() { return this.expiry; } public boolean active() { return this.active; } public boolean permanent() { return this.permanent; } public String timestamp() { return this.timestamp; } public boolean pardoned() { return this.pardoned; }
/*    */ 
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\lookup\screen\views\LookupPunishmentView$PunishmentEntry.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */