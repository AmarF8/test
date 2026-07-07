/*    */ package com.atlas.common.fabric.screen;
/*    */ 
/*    */ import com.cobblemon.mod.common.client.render.models.blockbench.FloatingState;
/*    */ import com.cobblemon.mod.common.entity.PoseType;
/*    */ import java.lang.reflect.Method;
/*    */ import net.minecraft.class_2960;
/*    */ import net.minecraft.class_4587;
/*    */ import org.joml.Quaternionf;
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
/*    */ public final class PokemonRenderHelper
/*    */ {
/* 21 */   private static Method drawProfilePokemonMethod = null;
/*    */ 
/*    */   
/*    */   private static boolean reflectionFailed = false;
/*    */ 
/*    */   
/*    */   public static void init() {
/* 28 */     if (drawProfilePokemonMethod != null || reflectionFailed)
/*    */       return;  try {
/* 30 */       Class<?> utilsClass = Class.forName("com.cobblemon.mod.common.client.gui.PokemonGuiUtilsKt");
/* 31 */       for (Method m : utilsClass.getDeclaredMethods()) {
/* 32 */         if (m.getName().equals("drawProfilePokemon")) {
/* 33 */           Class<?>[] params = m.getParameterTypes();
/* 34 */           if (params.length > 0 && params[0] == class_2960.class) {
/* 35 */             drawProfilePokemonMethod = m;
/* 36 */             drawProfilePokemonMethod.setAccessible(true);
/*    */             break;
/*    */           } 
/*    */         } 
/*    */       } 
/* 41 */       if (drawProfilePokemonMethod == null) reflectionFailed = true; 
/* 42 */     } catch (Exception e) {
/* 43 */       reflectionFailed = true;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static boolean isAvailable() {
/* 49 */     return (drawProfilePokemonMethod != null);
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
/*    */   public static void draw(class_2960 speciesId, class_4587 mat, Quaternionf rotation, FloatingState state, float delta) {
/* 65 */     draw(speciesId, mat, rotation, state, delta, PoseType.PROFILE, 4.5F);
/*    */   }
/*    */ 
/*    */   
/*    */   public static void draw(class_2960 speciesId, class_4587 mat, Quaternionf rotation, FloatingState state, float delta, PoseType pose, float scale) {
/* 70 */     if (drawProfilePokemonMethod == null)
/*    */       return;  try {
/* 72 */       Object[] args; int pc = drawProfilePokemonMethod.getParameterCount();
/*    */       
/* 74 */       if (pc == 16) {
/*    */         
/* 76 */         args = new Object[] { speciesId, mat, rotation, pose, state, Float.valueOf(delta), Float.valueOf(scale), Boolean.valueOf(true), Boolean.valueOf(false), Boolean.valueOf(false), Float.valueOf(1.0F), Float.valueOf(1.0F), Float.valueOf(1.0F), Float.valueOf(1.0F), Float.valueOf(0.0F), Float.valueOf(0.0F) };
/* 77 */       } else if (pc == 13) {
/*    */         
/* 79 */         args = new Object[] { speciesId, mat, rotation, pose, state, Float.valueOf(delta), Float.valueOf(scale), Boolean.valueOf(true), Boolean.valueOf(false), Float.valueOf(1.0F), Float.valueOf(1.0F), Float.valueOf(1.0F), Float.valueOf(1.0F) };
/*    */       } else {
/* 81 */         args = new Object[] { speciesId, mat, rotation, pose, state, Float.valueOf(delta) };
/*    */       } 
/* 83 */       drawProfilePokemonMethod.invoke(null, args);
/* 84 */     } catch (Exception exception) {}
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\screen\PokemonRenderHelper.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */