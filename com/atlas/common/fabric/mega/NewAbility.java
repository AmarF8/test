/*    */ package com.atlas.common.fabric.mega;
/*    */ 
/*    */ import com.cobblemon.mod.common.api.Priority;
/*    */ import com.cobblemon.mod.common.api.abilities.Ability;
/*    */ import com.cobblemon.mod.common.api.abilities.AbilityTemplate;
/*    */ import kotlin.jvm.functions.Function3;
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class NewAbility
/*    */ {
/*    */   public static AbilityTemplate getAbility(String name) {
/* 13 */     Function3<AbilityTemplate, Boolean, Priority, Ability> builder = Ability::new;
/*    */ 
/*    */     
/* 16 */     return new AbilityTemplate(name, builder, "cobblemon.ability." + name, "cobblemon.ability." + name + ".desc");
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\mega\NewAbility.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */