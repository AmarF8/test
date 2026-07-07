/*    */ package com.atlas.common.fabric.mixin.content.accessor;
/*    */ 
/*    */ import com.cobblemon.mod.common.api.moves.MoveTemplate;
/*    */ import com.cobblemon.mod.common.api.moves.Moves;
/*    */ import java.util.Map;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.gen.Accessor;
/*    */ 
/*    */ 
/*    */ @Mixin(value = {Moves.class}, remap = false)
/*    */ public interface MovesAccessor
/*    */ {
/*    */   @Accessor("allMoves")
/*    */   static Map<String, MoveTemplate> getAllMoves() {
/* 15 */     throw new IllegalStateException("Mixin failed to apply");
/*    */   }
/*    */   
/*    */   @Accessor("idMapping")
/*    */   static Map<Integer, MoveTemplate> getIdMapping() {
/* 20 */     throw new IllegalStateException("Mixin failed to apply");
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\mixin\content\accessor\MovesAccessor.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */