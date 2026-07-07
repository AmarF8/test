/*    */ package com.atlas.common.fabric.morph.runtime.client;
/*    */ 
/*    */ import java.util.UUID;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class Entry
/*    */ {
/*    */   public final UUID playerId;
/*    */   public final String species;
/*    */   public final String aspects;
/*    */   public final String moves;
/*    */   public final int mountEntityId;
/*    */   
/*    */   Entry(UUID playerId, String species, String aspects, String moves, int mountEntityId) {
/* 77 */     this.playerId = playerId;
/* 78 */     this.species = species;
/* 79 */     this.aspects = aspects;
/* 80 */     this.moves = moves;
/* 81 */     this.mountEntityId = mountEntityId;
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\morph\runtime\client\MorphedPlayerRegistry$Entry.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */