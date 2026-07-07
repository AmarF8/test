/*     */ package com.atlas.common.fabric.ranked.screen;
/*     */ 
/*     */ import com.atlas.common.fabric.ranked.data.RankedRank;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import net.minecraft.class_2561;
/*     */ import net.minecraft.class_2583;
/*     */ import net.minecraft.class_2960;
/*     */ import net.minecraft.class_332;
/*     */ import net.minecraft.class_5250;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class RankedIcon
/*     */ {
/*  22 */   public static final RankedIcon BADGE_BRONZE_3 = badge("bronze3", "ranked.badge.bronze3", "");
/*  23 */   public static final RankedIcon BADGE_BRONZE_2 = badge("bronze2", "ranked.badge.bronze2", "");
/*  24 */   public static final RankedIcon BADGE_BRONZE_1 = badge("bronze1", "ranked.badge.bronze1", "");
/*  25 */   public static final RankedIcon BADGE_SILVER_3 = badge("silver3", "ranked.badge.silver3", "");
/*  26 */   public static final RankedIcon BADGE_SILVER_2 = badge("silver2", "ranked.badge.silver2", "");
/*  27 */   public static final RankedIcon BADGE_SILVER_1 = badge("silver1", "ranked.badge.silver1", "");
/*  28 */   public static final RankedIcon BADGE_GOLD_3 = badge("gold3", "ranked.badge.gold3", "");
/*  29 */   public static final RankedIcon BADGE_GOLD_2 = badge("gold2", "ranked.badge.gold2", "");
/*  30 */   public static final RankedIcon BADGE_GOLD_1 = badge("gold1", "ranked.badge.gold1", "");
/*  31 */   public static final RankedIcon BADGE_DIAMOND_3 = badge("diamond3", "ranked.badge.diamond3", "");
/*  32 */   public static final RankedIcon BADGE_DIAMOND_2 = badge("diamond2", "ranked.badge.diamond2", "");
/*  33 */   public static final RankedIcon BADGE_DIAMOND_1 = badge("diamond1", "ranked.badge.diamond1", "");
/*  34 */   public static final RankedIcon BADGE_MASTER_3 = badge("master3", "ranked.badge.master3", "");
/*  35 */   public static final RankedIcon BADGE_MASTER_2 = badge("master2", "ranked.badge.master2", "");
/*  36 */   public static final RankedIcon BADGE_MASTER_1 = badge("master1", "ranked.badge.master1", "");
/*  37 */   public static final RankedIcon BADGE_CHAMPION_3 = badge("champion3", "ranked.badge.champion3", "");
/*  38 */   public static final RankedIcon BADGE_CHAMPION_2 = badge("champion2", "ranked.badge.champion2", "");
/*  39 */   public static final RankedIcon BADGE_CHAMPION_1 = badge("champion1", "ranked.badge.champion1", "");
/*  40 */   public static final RankedIcon BADGE_LEGEND = badge("legend", "ranked.badge.legend", "");
/*     */ 
/*     */   
/*  43 */   public static final RankedIcon TAG_BRONZE_3 = tag("tag_bronze3", "ranked.tag.bronze3", "", 52, 7);
/*  44 */   public static final RankedIcon TAG_BRONZE_2 = tag("tag_bronze2", "ranked.tag.bronze2", "", 49, 7);
/*  45 */   public static final RankedIcon TAG_BRONZE_1 = tag("tag_bronze1", "ranked.tag.bronze1", "", 46, 7);
/*  46 */   public static final RankedIcon TAG_BRONZE = tag("tag_bronze", "ranked.tag.bronze", "", 40, 7);
/*  47 */   public static final RankedIcon TAG_SILVER_3 = tag("tag_silver3", "ranked.tag.silver3", "", 51, 7);
/*  48 */   public static final RankedIcon TAG_SILVER_2 = tag("tag_silver2", "ranked.tag.silver2", "", 48, 7);
/*  49 */   public static final RankedIcon TAG_SILVER_1 = tag("tag_silver1", "ranked.tag.silver1", "", 45, 7);
/*  50 */   public static final RankedIcon TAG_SILVER = tag("tag_silver", "ranked.tag.silver", "", 39, 7);
/*  51 */   public static final RankedIcon TAG_GOLD_3 = tag("tag_gold3", "ranked.tag.gold3", "", 39, 7);
/*  52 */   public static final RankedIcon TAG_GOLD_2 = tag("tag_gold2", "ranked.tag.gold2", "", 36, 7);
/*  53 */   public static final RankedIcon TAG_GOLD_1 = tag("tag_gold1", "ranked.tag.gold1", "", 33, 7);
/*  54 */   public static final RankedIcon TAG_GOLD = tag("tag_gold", "ranked.tag.gold", "", 27, 7);
/*  55 */   public static final RankedIcon TAG_DIAMOND_3 = tag("tag_diamond3", "ranked.tag.diamond3", "", 58, 7);
/*  56 */   public static final RankedIcon TAG_DIAMOND_2 = tag("tag_diamond2", "ranked.tag.diamond2", "", 55, 7);
/*  57 */   public static final RankedIcon TAG_DIAMOND_1 = tag("tag_diamond1", "ranked.tag.diamond1", "", 52, 7);
/*  58 */   public static final RankedIcon TAG_DIAMOND = tag("tag_diamond", "ranked.tag.diamond", "", 46, 7);
/*  59 */   public static final RankedIcon TAG_MASTER_3 = tag("tag_master3", "ranked.tag.master3", "", 52, 7);
/*  60 */   public static final RankedIcon TAG_MASTER_2 = tag("tag_master2", "ranked.tag.master2", "", 49, 7);
/*  61 */   public static final RankedIcon TAG_MASTER_1 = tag("tag_master1", "ranked.tag.master1", "", 46, 7);
/*  62 */   public static final RankedIcon TAG_MASTER = tag("tag_master", "ranked.tag.master", "", 40, 7);
/*  63 */   public static final RankedIcon TAG_CHAMPION_3 = tag("tag_champion3", "ranked.tag.champion3", "", 64, 7);
/*  64 */   public static final RankedIcon TAG_CHAMPION_2 = tag("tag_champion2", "ranked.tag.champion2", "", 61, 7);
/*  65 */   public static final RankedIcon TAG_CHAMPION_1 = tag("tag_champion1", "ranked.tag.champion1", "", 58, 7);
/*  66 */   public static final RankedIcon TAG_CHAMPION = tag("tag_champion", "ranked.tag.champion", "", 52, 7);
/*  67 */   public static final RankedIcon TAG_LEGEND = tag("tag_legend", "ranked.tag.legend", "", 39, 7);
/*     */ 
/*     */   
/*  70 */   public static final RankedIcon TROPHY = new RankedIcon(
/*  71 */       class_2960.method_60655("atlas-common-fabric", "textures/ranked/badge/trophy.png"), "ranked", "ranked.trophy", "", 16, 16);
/*     */ 
/*     */ 
/*     */   
/*  75 */   public static final RankedIcon BATTLE_POINTS = new RankedIcon(
/*  76 */       class_2960.method_60655("atlas-common-fabric", "textures/ranked/badge/battle_points.png"), "ranked", "ranked.battle_points", "", 16, 16);
/*     */ 
/*     */ 
/*     */   
/*  80 */   public static final RankedIcon VALOR = new RankedIcon(
/*  81 */       class_2960.method_60655("atlas-common-fabric", "textures/ranked/badge/valor.png"), "ranked", "ranked.valor", "", 16, 16);
/*     */ 
/*     */ 
/*     */   
/*  85 */   private static final Map<String, RankedIcon> BADGE_MAP = new HashMap<>();
/*  86 */   private static final Map<String, RankedIcon> TAG_MAP = new HashMap<>(); private final class_2960 texture; private final String fontName; private final String langKey;
/*     */   
/*     */   static {
/*  89 */     BADGE_MAP.put("Bronze III", BADGE_BRONZE_3);
/*  90 */     BADGE_MAP.put("Bronze II", BADGE_BRONZE_2);
/*  91 */     BADGE_MAP.put("Bronze I", BADGE_BRONZE_1);
/*  92 */     BADGE_MAP.put("Silver III", BADGE_SILVER_3);
/*  93 */     BADGE_MAP.put("Silver II", BADGE_SILVER_2);
/*  94 */     BADGE_MAP.put("Silver I", BADGE_SILVER_1);
/*  95 */     BADGE_MAP.put("Gold III", BADGE_GOLD_3);
/*  96 */     BADGE_MAP.put("Gold II", BADGE_GOLD_2);
/*  97 */     BADGE_MAP.put("Gold I", BADGE_GOLD_1);
/*  98 */     BADGE_MAP.put("Diamond III", BADGE_DIAMOND_3);
/*  99 */     BADGE_MAP.put("Diamond II", BADGE_DIAMOND_2);
/* 100 */     BADGE_MAP.put("Diamond I", BADGE_DIAMOND_1);
/* 101 */     BADGE_MAP.put("Master III", BADGE_MASTER_3);
/* 102 */     BADGE_MAP.put("Master II", BADGE_MASTER_2);
/* 103 */     BADGE_MAP.put("Master I", BADGE_MASTER_1);
/* 104 */     BADGE_MAP.put("Champion III", BADGE_CHAMPION_3);
/* 105 */     BADGE_MAP.put("Champion II", BADGE_CHAMPION_2);
/* 106 */     BADGE_MAP.put("Champion I", BADGE_CHAMPION_1);
/* 107 */     BADGE_MAP.put("Legend", BADGE_LEGEND);
/*     */     
/* 109 */     TAG_MAP.put("Bronze III", TAG_BRONZE_3);
/* 110 */     TAG_MAP.put("Bronze II", TAG_BRONZE_2);
/* 111 */     TAG_MAP.put("Bronze I", TAG_BRONZE_1);
/* 112 */     TAG_MAP.put("Bronze", TAG_BRONZE);
/* 113 */     TAG_MAP.put("Silver III", TAG_SILVER_3);
/* 114 */     TAG_MAP.put("Silver II", TAG_SILVER_2);
/* 115 */     TAG_MAP.put("Silver I", TAG_SILVER_1);
/* 116 */     TAG_MAP.put("Silver", TAG_SILVER);
/* 117 */     TAG_MAP.put("Gold III", TAG_GOLD_3);
/* 118 */     TAG_MAP.put("Gold II", TAG_GOLD_2);
/* 119 */     TAG_MAP.put("Gold I", TAG_GOLD_1);
/* 120 */     TAG_MAP.put("Gold", TAG_GOLD);
/* 121 */     TAG_MAP.put("Diamond III", TAG_DIAMOND_3);
/* 122 */     TAG_MAP.put("Diamond II", TAG_DIAMOND_2);
/* 123 */     TAG_MAP.put("Diamond I", TAG_DIAMOND_1);
/* 124 */     TAG_MAP.put("Diamond", TAG_DIAMOND);
/* 125 */     TAG_MAP.put("Master III", TAG_MASTER_3);
/* 126 */     TAG_MAP.put("Master II", TAG_MASTER_2);
/* 127 */     TAG_MAP.put("Master I", TAG_MASTER_1);
/* 128 */     TAG_MAP.put("Master", TAG_MASTER);
/* 129 */     TAG_MAP.put("Champion III", TAG_CHAMPION_3);
/* 130 */     TAG_MAP.put("Champion II", TAG_CHAMPION_2);
/* 131 */     TAG_MAP.put("Champion I", TAG_CHAMPION_1);
/* 132 */     TAG_MAP.put("Champion", TAG_CHAMPION);
/* 133 */     TAG_MAP.put("Legend", TAG_LEGEND);
/*     */   }
/*     */ 
/*     */   
/*     */   private final String character;
/*     */   
/*     */   private final int width;
/*     */   
/*     */   private final int height;
/*     */ 
/*     */   
/*     */   private RankedIcon(class_2960 texture, String fontName, String langKey, String character, int width, int height) {
/* 145 */     this.texture = texture;
/* 146 */     this.fontName = fontName;
/* 147 */     this.langKey = langKey;
/* 148 */     this.character = character;
/* 149 */     this.width = width;
/* 150 */     this.height = height;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static RankedIcon badge(String name, String langKey, String character) {
/* 156 */     return new RankedIcon(
/* 157 */         class_2960.method_60655("atlas-common-fabric", "textures/ranked/badge/" + name + ".png"), "ranked", langKey, character, 16, 16);
/*     */   }
/*     */ 
/*     */   
/*     */   private static RankedIcon tag(String name, String langKey, String character, int w, int h) {
/* 162 */     return new RankedIcon(
/* 163 */         class_2960.method_60655("atlas-common-fabric", "textures/ranked/tags/" + name + ".png"), "ranked", langKey, character, w, h);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public class_2960 getTexture()
/*     */   {
/* 169 */     return this.texture; } @NotNull
/* 170 */   public String getFontName() { return this.fontName; } @NotNull
/* 171 */   public String getLangKey() { return this.langKey; } @NotNull
/* 172 */   public String getCharacter() { return this.character; }
/* 173 */   public int getWidth() { return this.width; } public int getHeight() {
/* 174 */     return this.height;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public class_5250 draw() {
/* 184 */     return class_2561.method_43471(this.langKey)
/* 185 */       .method_27694(style -> style.method_27704(class_2960.method_60654(this.fontName)).method_10978(Boolean.valueOf(false)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawTexture(@NotNull class_332 ctx, int x, int y, int renderW, int renderH) {
/* 194 */     ctx.method_25290(this.texture, x, y, 0.0F, 0.0F, renderW, renderH, renderW, renderH);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawTexture(@NotNull class_332 ctx, int x, int y) {
/* 201 */     drawTexture(ctx, x, y, this.width, this.height);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static RankedIcon badgeForRank(@NotNull RankedRank rank) {
/* 211 */     return BADGE_MAP.get(rank.getDisplayName());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static RankedIcon tagForRank(@NotNull RankedRank rank) {
/* 219 */     return TAG_MAP.get(rank.getDisplayName());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static RankedIcon tagForDisplayName(@NotNull String displayName) {
/* 227 */     return TAG_MAP.get(displayName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static RankedIcon badgeForDisplayName(@NotNull String displayName) {
/* 235 */     return BADGE_MAP.get(displayName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int rankColorToARGB(int hex) {
/* 242 */     int r = hex >> 16 & 0xFF;
/* 243 */     int g = hex >> 8 & 0xFF;
/* 244 */     int b = hex & 0xFF;
/* 245 */     return 0xFF000000 | r << 16 | g << 8 | b;
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\ranked\screen\RankedIcon.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */