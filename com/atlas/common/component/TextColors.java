/*     */ package com.atlas.common.component;
/*     */ 
/*     */ import java.util.Objects;
/*     */ import net.kyori.adventure.text.format.TextColor;
/*     */ import org.jetbrains.annotations.NotNull;
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
/*     */ public final class TextColors
/*     */ {
/*     */   @NotNull
/*  22 */   public static final TextColor BLACK = hex("000000"); @NotNull
/*  23 */   public static final TextColor WHITE = hex("FFFFFF");
/*     */   
/*     */   @NotNull
/*  26 */   public static final TextColor PINK_100 = hex("FFC0CB"); @NotNull
/*  27 */   public static final TextColor PINK_200 = hex("FFA2B1"); @NotNull
/*  28 */   public static final TextColor PINK_300 = hex("FF8297"); @NotNull
/*  29 */   public static final TextColor PINK_400 = hex("FF627C"); @NotNull
/*  30 */   public static final TextColor PINK_500 = hex("FF4261"); @NotNull
/*  31 */   public static final TextColor PINK_600 = hex("FF2246"); @NotNull
/*  32 */   public static final TextColor PINK_700 = hex("FF012C"); @NotNull
/*  33 */   public static final TextColor PINK_800 = hex("E00025"); @NotNull
/*  34 */   public static final TextColor PINK_900 = hex("C00020");
/*     */   
/*     */   @NotNull
/*  37 */   public static final TextColor RED_100 = hex("FF0000"); @NotNull
/*  38 */   public static final TextColor RED_200 = hex("ED0000"); @NotNull
/*  39 */   public static final TextColor RED_300 = hex("DB0000"); @NotNull
/*  40 */   public static final TextColor RED_400 = hex("C80000"); @NotNull
/*  41 */   public static final TextColor RED_500 = hex("B60000"); @NotNull
/*  42 */   public static final TextColor RED_600 = hex("A40000"); @NotNull
/*  43 */   public static final TextColor RED_700 = hex("920000"); @NotNull
/*  44 */   public static final TextColor RED_800 = hex("800000"); @NotNull
/*  45 */   public static final TextColor RED_900 = hex("6D0000");
/*     */   
/*     */   @NotNull
/*  48 */   public static final TextColor MAROON_100 = hex("800000"); @NotNull
/*  49 */   public static final TextColor MAROON_200 = hex("760000"); @NotNull
/*  50 */   public static final TextColor MAROON_300 = hex("6D0000"); @NotNull
/*  51 */   public static final TextColor MAROON_400 = hex("640000"); @NotNull
/*  52 */   public static final TextColor MAROON_500 = hex("5B0000"); @NotNull
/*  53 */   public static final TextColor MAROON_600 = hex("520000"); @NotNull
/*  54 */   public static final TextColor MAROON_700 = hex("490000"); @NotNull
/*  55 */   public static final TextColor MAROON_800 = hex("400000"); @NotNull
/*  56 */   public static final TextColor MAROON_900 = hex("370000");
/*     */   @NotNull
/*  58 */   public static final TextColor DARK_GRAY = hex("555555");
/*     */   
/*     */   @NotNull
/*  61 */   public static final TextColor SILVER_100 = hex("C0C0C0"); @NotNull
/*  62 */   public static final TextColor SILVER_200 = hex("B2B2B2"); @NotNull
/*  63 */   public static final TextColor SILVER_300 = hex("A4A4A4"); @NotNull
/*  64 */   public static final TextColor SILVER_400 = hex("969696"); @NotNull
/*  65 */   public static final TextColor SILVER_500 = hex("898989"); @NotNull
/*  66 */   public static final TextColor SILVER_600 = hex("7B7B7B"); @NotNull
/*  67 */   public static final TextColor SILVER_700 = hex("6D6D6D"); @NotNull
/*  68 */   public static final TextColor SILVER_800 = hex("606060"); @NotNull
/*  69 */   public static final TextColor SILVER_900 = hex("525252");
/*     */   
/*     */   @NotNull
/*  72 */   public static final TextColor GRAY_100 = hex("808080"); @NotNull
/*  73 */   public static final TextColor GRAY_200 = hex("767676"); @NotNull
/*  74 */   public static final TextColor GRAY_300 = hex("6D6D6D"); @NotNull
/*  75 */   public static final TextColor GRAY_400 = hex("646464"); @NotNull
/*  76 */   public static final TextColor GRAY_500 = hex("5B5B5B"); @NotNull
/*  77 */   public static final TextColor GRAY_600 = hex("525252"); @NotNull
/*  78 */   public static final TextColor GRAY_700 = hex("494949"); @NotNull
/*  79 */   public static final TextColor GRAY_800 = hex("404040"); @NotNull
/*  80 */   public static final TextColor GRAY_900 = hex("373737");
/*     */   
/*     */   @NotNull
/*  83 */   public static final TextColor LIME_100 = hex("00FF00"); @NotNull
/*  84 */   public static final TextColor LIME_200 = hex("00ED00"); @NotNull
/*  85 */   public static final TextColor LIME_300 = hex("00DB00"); @NotNull
/*  86 */   public static final TextColor LIME_400 = hex("00C800"); @NotNull
/*  87 */   public static final TextColor LIME_500 = hex("00B600"); @NotNull
/*  88 */   public static final TextColor LIME_600 = hex("00A400"); @NotNull
/*  89 */   public static final TextColor LIME_700 = hex("009200"); @NotNull
/*  90 */   public static final TextColor LIME_800 = hex("008000"); @NotNull
/*  91 */   public static final TextColor LIME_900 = hex("006D00");
/*     */   
/*     */   @NotNull
/*  94 */   public static final TextColor GREEN_100 = hex("008000"); @NotNull
/*  95 */   public static final TextColor GREEN_200 = hex("007600"); @NotNull
/*  96 */   public static final TextColor GREEN_300 = hex("006D00"); @NotNull
/*  97 */   public static final TextColor GREEN_400 = hex("006400"); @NotNull
/*  98 */   public static final TextColor GREEN_500 = hex("005B00"); @NotNull
/*  99 */   public static final TextColor GREEN_600 = hex("005200"); @NotNull
/* 100 */   public static final TextColor GREEN_700 = hex("004900"); @NotNull
/* 101 */   public static final TextColor GREEN_800 = hex("004000"); @NotNull
/* 102 */   public static final TextColor GREEN_900 = hex("003700");
/*     */   
/*     */   @NotNull
/* 105 */   public static final TextColor MAGENTA_100 = hex("FF00FF"); @NotNull
/* 106 */   public static final TextColor MAGENTA_200 = hex("ED00ED"); @NotNull
/* 107 */   public static final TextColor MAGENTA_300 = hex("DB00DB"); @NotNull
/* 108 */   public static final TextColor MAGENTA_400 = hex("C800C8"); @NotNull
/* 109 */   public static final TextColor MAGENTA_500 = hex("B600B6"); @NotNull
/* 110 */   public static final TextColor MAGENTA_600 = hex("A400A4"); @NotNull
/* 111 */   public static final TextColor MAGENTA_700 = hex("920092"); @NotNull
/* 112 */   public static final TextColor MAGENTA_800 = hex("80007F"); @NotNull
/* 113 */   public static final TextColor MAGENTA_900 = hex("6D006D");
/*     */   
/*     */   @NotNull
/* 116 */   public static final TextColor DUNGEONS = hex("b618b6"); @NotNull
/* 117 */   public static final TextColor MYTHICAL = hex("9673D7"); @NotNull
/* 118 */   public static final TextColor PARADOX = hex("AA272E"); @NotNull
/* 119 */   public static final TextColor ULTRA_BEAST = hex("4FA2D5"); @NotNull
/* 120 */   public static final TextColor PURPLE_100 = hex("800080"); @NotNull
/* 121 */   public static final TextColor PURPLE_200 = hex("760076"); @NotNull
/* 122 */   public static final TextColor PURPLE_300 = hex("6D006D"); @NotNull
/* 123 */   public static final TextColor PURPLE_400 = hex("640064"); @NotNull
/* 124 */   public static final TextColor PURPLE_500 = hex("5B005B"); @NotNull
/* 125 */   public static final TextColor PURPLE_600 = hex("520052"); @NotNull
/* 126 */   public static final TextColor PURPLE_700 = hex("490049"); @NotNull
/* 127 */   public static final TextColor PURPLE_800 = hex("400040"); @NotNull
/* 128 */   public static final TextColor PURPLE_900 = hex("370037");
/*     */   
/*     */   @NotNull
/* 131 */   public static final TextColor AQUA_100 = hex("00FFFF"); @NotNull
/* 132 */   public static final TextColor AQUA_200 = hex("00EDED"); @NotNull
/* 133 */   public static final TextColor AQUA_300 = hex("00DBDB"); @NotNull
/* 134 */   public static final TextColor AQUA_400 = hex("00C8C8"); @NotNull
/* 135 */   public static final TextColor AQUA_500 = hex("00B6B6"); @NotNull
/* 136 */   public static final TextColor AQUA_600 = hex("00A4A4"); @NotNull
/* 137 */   public static final TextColor AQUA_700 = hex("009292"); @NotNull
/* 138 */   public static final TextColor AQUA_800 = hex("007F80"); @NotNull
/* 139 */   public static final TextColor AQUA_900 = hex("006D6D");
/*     */   
/*     */   @NotNull
/* 142 */   public static final TextColor TEAL_100 = hex("008080"); @NotNull
/* 143 */   public static final TextColor TEAL_200 = hex("007676"); @NotNull
/* 144 */   public static final TextColor TEAL_300 = hex("006D6D"); @NotNull
/* 145 */   public static final TextColor TEAL_400 = hex("006464"); @NotNull
/* 146 */   public static final TextColor TEAL_500 = hex("005B5B"); @NotNull
/* 147 */   public static final TextColor TEAL_600 = hex("005252"); @NotNull
/* 148 */   public static final TextColor TEAL_700 = hex("004949"); @NotNull
/* 149 */   public static final TextColor TEAL_800 = hex("004040"); @NotNull
/* 150 */   public static final TextColor TEAL_900 = hex("003737");
/*     */   
/*     */   @NotNull
/* 153 */   public static final TextColor BLUE_100 = hex("0000FF"); @NotNull
/* 154 */   public static final TextColor BLUE_200 = hex("0000ED"); @NotNull
/* 155 */   public static final TextColor BLUE_300 = hex("0000DB"); @NotNull
/* 156 */   public static final TextColor BLUE_400 = hex("0000C8"); @NotNull
/* 157 */   public static final TextColor BLUE_500 = hex("0000B6"); @NotNull
/* 158 */   public static final TextColor BLUE_600 = hex("0000A4"); @NotNull
/* 159 */   public static final TextColor BLUE_700 = hex("000092"); @NotNull
/* 160 */   public static final TextColor BLUE_800 = hex("000080"); @NotNull
/* 161 */   public static final TextColor BLUE_900 = hex("00006D");
/*     */   
/*     */   @NotNull
/* 164 */   public static final TextColor NAVY_100 = hex("000080"); @NotNull
/* 165 */   public static final TextColor NAVY_200 = hex("000076"); @NotNull
/* 166 */   public static final TextColor NAVY_300 = hex("00006D"); @NotNull
/* 167 */   public static final TextColor NAVY_400 = hex("000064"); @NotNull
/* 168 */   public static final TextColor NAVY_500 = hex("00005B"); @NotNull
/* 169 */   public static final TextColor NAVY_600 = hex("000052"); @NotNull
/* 170 */   public static final TextColor NAVY_700 = hex("000049"); @NotNull
/* 171 */   public static final TextColor NAVY_800 = hex("000040"); @NotNull
/* 172 */   public static final TextColor NAVY_900 = hex("000037");
/*     */   
/*     */   @NotNull
/* 175 */   public static final TextColor YELLOW_100 = hex("FFFF00"); @NotNull
/* 176 */   public static final TextColor YELLOW_200 = hex("EDED00"); @NotNull
/* 177 */   public static final TextColor YELLOW_300 = hex("DBDB00"); @NotNull
/* 178 */   public static final TextColor YELLOW_400 = hex("C8C800"); @NotNull
/* 179 */   public static final TextColor YELLOW_500 = hex("B6B600"); @NotNull
/* 180 */   public static final TextColor YELLOW_600 = hex("A4A400"); @NotNull
/* 181 */   public static final TextColor YELLOW_700 = hex("929200"); @NotNull
/* 182 */   public static final TextColor YELLOW_800 = hex("7F8000"); @NotNull
/* 183 */   public static final TextColor YELLOW_900 = hex("6D6D00");
/*     */   
/*     */   @NotNull
/* 186 */   public static final TextColor OLIVE_100 = hex("808000"); @NotNull
/* 187 */   public static final TextColor OLIVE_200 = hex("767600"); @NotNull
/* 188 */   public static final TextColor OLIVE_300 = hex("6D6D00"); @NotNull
/* 189 */   public static final TextColor OLIVE_400 = hex("646400"); @NotNull
/* 190 */   public static final TextColor OLIVE_500 = hex("5B5B00"); @NotNull
/* 191 */   public static final TextColor OLIVE_600 = hex("525200"); @NotNull
/* 192 */   public static final TextColor OLIVE_700 = hex("494900"); @NotNull
/* 193 */   public static final TextColor OLIVE_800 = hex("404000"); @NotNull
/* 194 */   public static final TextColor OLIVE_900 = hex("373700");
/*     */   
/*     */   @NotNull
/* 197 */   public static final TextColor AMBER_100 = hex("FFBF00"); @NotNull
/* 198 */   public static final TextColor AMBER_200 = hex("EDB200"); @NotNull
/* 199 */   public static final TextColor AMBER_300 = hex("DBA400"); @NotNull
/* 200 */   public static final TextColor AMBER_400 = hex("C89600"); @NotNull
/* 201 */   public static final TextColor AMBER_500 = hex("B68900"); @NotNull
/* 202 */   public static final TextColor AMBER_600 = hex("A47B00"); @NotNull
/* 203 */   public static final TextColor AMBER_700 = hex("926D00"); @NotNull
/* 204 */   public static final TextColor AMBER_800 = hex("806000"); @NotNull
/* 205 */   public static final TextColor AMBER_900 = hex("6D5200");
/*     */   
/*     */   @NotNull
/* 208 */   public static final TextColor ORANGE_100 = hex("FF7F00"); @NotNull
/* 209 */   public static final TextColor ORANGE_200 = hex("ED7600"); @NotNull
/* 210 */   public static final TextColor ORANGE_300 = hex("DB6D00"); @NotNull
/* 211 */   public static final TextColor ORANGE_400 = hex("C86400"); @NotNull
/* 212 */   public static final TextColor ORANGE_500 = hex("B65B00"); @NotNull
/* 213 */   public static final TextColor ORANGE_600 = hex("A45200"); @NotNull
/* 214 */   public static final TextColor ORANGE_700 = hex("924900"); @NotNull
/* 215 */   public static final TextColor ORANGE_800 = hex("804000"); @NotNull
/* 216 */   public static final TextColor ORANGE_900 = hex("6D3700");
/*     */   
/*     */   @NotNull
/* 219 */   public static final TextColor BROWN_100 = hex("88540B"); @NotNull
/* 220 */   public static final TextColor BROWN_200 = hex("7F4E0A"); @NotNull
/* 221 */   public static final TextColor BROWN_300 = hex("75480A"); @NotNull
/* 222 */   public static final TextColor BROWN_400 = hex("6B4209"); @NotNull
/* 223 */   public static final TextColor BROWN_500 = hex("623C08"); @NotNull
/* 224 */   public static final TextColor BROWN_600 = hex("583607"); @NotNull
/* 225 */   public static final TextColor BROWN_700 = hex("4E3006"); @NotNull
/* 226 */   public static final TextColor BROWN_800 = hex("442A06"); @NotNull
/* 227 */   public static final TextColor BROWN_900 = hex("3B2405");
/*     */   @NotNull
/* 229 */   public static final TextColor DISCORD = hex("7289dA"); @NotNull
/* 230 */   public static final TextColor CLAN = hex("cccccc");
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
/*     */   @NotNull
/*     */   public static TextColor hex(@NotNull String hex) {
/* 246 */     return Objects.<TextColor>requireNonNull(TextColor.fromHexString("#" + (String)Objects.<String>requireNonNull(hex.replaceAll("#", ""))));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static TextColor random() {
/* 256 */     return hex(String.format("%06x", new Object[] { Integer.valueOf((int)(Math.random() * 1.6777215E7D)) }));
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\component\TextColors.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */