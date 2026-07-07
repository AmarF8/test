/*     */ package com.atlas.common.fabric.lookup.screen.widgets;
/*     */ 
/*     */ import com.atlas.common.fabric.battletower.screen.CobblemonFontIcon;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.class_2561;
/*     */ import net.minecraft.class_2583;
/*     */ import net.minecraft.class_2960;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class StatIcon
/*     */ {
/*  24 */   public static final String[] KEYS = new String[] { "hp", "attack", "defense", "special_attack", "special_defense", "speed" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  30 */   public static final int[] COLORS = new int[] { -65536, -33024, -256, -16711681, -16711936, -8388480 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  39 */   private static final String[] ICON_FONTS = new String[] { "cobblemon_stats", "cobblemon_stats", "cobblemon_stats", "cobblemon_stats", "cobblemon_stats", "cobblemon_stats" };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  44 */   private static final String[] ICON_KEYS = new String[] { "cobblemon.stats.health_point", "cobblemon.stats.attack", "cobblemon.stats.defense", "cobblemon.stats.special_attack", "cobblemon.stats.special_defense", "cobblemon.stats.speed" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class_2561 icon(int index) {
/*  60 */     return (class_2561)class_2561.method_43471(ICON_KEYS[index])
/*  61 */       .method_27694(s -> s.method_27704(class_2960.method_60654(ICON_FONTS[index])));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static List<class_2561> propertyIcons(JsonObject poke) {
/*  75 */     List<class_2561> icons = new ArrayList<>();
/*  76 */     if (poke == null) return icons;
/*     */ 
/*     */ 
/*     */     
/*  80 */     boolean shiny = boolField(poke, "shiny");
/*  81 */     if (!shiny && poke.has("aspects") && poke.get("aspects").isJsonArray())
/*  82 */       for (JsonElement el : poke.getAsJsonArray("aspects")) {
/*  83 */         if (!el.isJsonNull() && "shiny".equals(el.getAsString())) { shiny = true; break; }
/*     */       
/*     */       }  
/*  86 */     if (shiny) icons.add(CobblemonFontIcon.SHINY.draw()); 
/*  87 */     if (boolField(poke, "legendary")) icons.add(CobblemonFontIcon.LEGENDARY.draw()); 
/*  88 */     if (boolField(poke, "mythical")) icons.add(CobblemonFontIcon.MYTHICAL.draw()); 
/*  89 */     if (boolField(poke, "ultraBeast")) icons.add(CobblemonFontIcon.ULTRA_BEAST.draw()); 
/*  90 */     if (boolField(poke, "paradox")) {
/*  91 */       String pt = strField(poke, "paradoxType");
/*  92 */       icons.add("ancient".equals(pt) ? 
/*  93 */           CobblemonFontIcon.PARADOX_ANCIENT.draw() : 
/*  94 */           CobblemonFontIcon.PARADOX_FUTURE.draw());
/*     */     } 
/*  96 */     return icons;
/*     */   }
/*     */   
/*     */   private static boolean boolField(JsonObject obj, String key) {
/* 100 */     if (obj == null || !obj.has(key) || obj.get(key).isJsonNull()) return false;  
/* 101 */     try { return obj.get(key).getAsBoolean(); } catch (Exception e) { return false; }
/*     */   
/*     */   }
/*     */   private static String strField(JsonObject obj, String key) {
/* 105 */     if (obj == null || !obj.has(key) || obj.get(key).isJsonNull()) return null;  
/* 106 */     try { return obj.get(key).getAsString(); } catch (Exception e) { return null; }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int indexOf(String rawStat) {
/* 114 */     if (rawStat == null) return -1; 
/* 115 */     String s = rawStat.toLowerCase();
/* 116 */     switch (s) { case "hp": case "attack": case "atk": case "defense": case "defence": case "def": case "special_attack": case "special-attack": case "specialattack": case "spa": case "special_defense": case "special_defence": case "specialdefense": case "specialdefence": case "spd": case "speed": case "spe":  }  return 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 123 */       -1;
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\lookup\screen\widgets\StatIcon.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */