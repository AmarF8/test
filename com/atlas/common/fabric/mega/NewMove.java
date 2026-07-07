/*    */ package com.atlas.common.fabric.mega;
/*    */ 
/*    */ import com.atlas.common.fabric.mixin.content.accessor.MovesAccessor;
/*    */ import com.cobblemon.mod.common.api.moves.MoveTemplate;
/*    */ import com.cobblemon.mod.common.api.moves.categories.DamageCategories;
/*    */ import com.cobblemon.mod.common.api.moves.categories.DamageCategory;
/*    */ import com.cobblemon.mod.common.api.types.ElementalType;
/*    */ import com.cobblemon.mod.common.api.types.ElementalTypes;
/*    */ import com.cobblemon.mod.common.battles.MoveTarget;
/*    */ import com.google.gson.JsonArray;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonPrimitive;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ public final class NewMove
/*    */ {
/*    */   public static MoveTemplate createMoveTemplate(JsonObject moveData, String id) {
/* 21 */     int num = moveData.get("num").getAsInt();
/* 22 */     ElementalType elementalType = ElementalTypes.getOrException(moveData.get("type").getAsString());
/* 23 */     DamageCategory damageCategory = DamageCategories.INSTANCE.getOrException(moveData.get("category").getAsString());
/* 24 */     double power = moveData.get("basePower").getAsDouble();
/* 25 */     MoveTarget target = MoveTarget.Companion.fromShowdownId(moveData.get("target").getAsString());
/*    */     
/* 27 */     JsonPrimitive accuracyJson = moveData.get("accuracy").getAsJsonPrimitive();
/* 28 */     double accuracy = accuracyJson.isNumber() ? accuracyJson.getAsDouble() : -1.0D;
/*    */     
/* 30 */     int pp = moveData.get("pp").getAsInt();
/* 31 */     int priority = moveData.get("priority").getAsInt();
/*    */     
/* 33 */     JsonElement critEl = moveData.get("critRatio");
/* 34 */     double critRatio = (critEl != null && !critEl.isJsonNull()) ? critEl.getAsDouble() : 1.0D;
/*    */     
/* 36 */     List<Double> effectChances = new ArrayList<>();
/* 37 */     JsonElement secondaries = moveData.get("secondaries");
/* 38 */     JsonElement secondary = moveData.get("secondary");
/* 39 */     if (secondaries != null && secondaries.isJsonArray()) {
/* 40 */       JsonArray arr = secondaries.getAsJsonArray();
/* 41 */       for (int j = 0; j < arr.size(); j++) {
/* 42 */         JsonObject element = arr.get(j).getAsJsonObject();
/* 43 */         if (element.has("chance")) {
/* 44 */           effectChances.add(Double.valueOf(element.get("chance").getAsDouble()));
/*    */         }
/*    */       } 
/* 47 */     } else if (secondary != null && secondary.isJsonObject()) {
/* 48 */       JsonObject sec = secondary.getAsJsonObject();
/* 49 */       if (sec.has("chance")) {
/* 50 */         effectChances.add(Double.valueOf(sec.get("chance").getAsDouble()));
/*    */       }
/*    */     } 
/*    */     
/* 54 */     Double[] chancesArray = effectChances.<Double>toArray(new Double[0]);
/*    */     
/* 56 */     return new MoveTemplate(id, num, elementalType, damageCategory, power, target, accuracy, pp, priority, critRatio, chancesArray);
/*    */   }
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
/*    */   public static void register(MoveTemplate move) {
/* 72 */     MovesAccessor.getIdMapping().put(Integer.valueOf(move.getNum()), move);
/* 73 */     MovesAccessor.getAllMoves().put(move.getName(), move);
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\mega\NewMove.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */