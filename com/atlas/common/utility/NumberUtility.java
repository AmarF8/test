/*     */ package com.atlas.common.utility;
/*     */ 
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.DecimalFormatSymbols;
/*     */ import java.util.Locale;
/*     */ import java.util.concurrent.ThreadLocalRandom;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class NumberUtility
/*     */ {
/*  17 */   private static final DecimalFormat FORMAT = new DecimalFormat("###,###,###,###,###,###,###,###.##", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getOrdinalNumber(int number) {
/*  26 */     String[] suffixes = { "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th" };
/*  27 */     switch (number % 100) { case 11: case 12: case 13:  }  return "" + 
/*     */       
/*  29 */       number + number;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean calculateChanceOfXInY(int denominator) {
/*  40 */     if (denominator <= 0) throw new IllegalArgumentException("Denominator must be greater than zero.");
/*     */     
/*  42 */     return (ThreadLocalRandom.current().nextInt(denominator) + 1 == 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static String format(@NotNull Number number) {
/*  53 */     return FORMAT.format(number);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isNumeric(@NotNull String text) {
/*  64 */     return text.matches("-?\\d+(\\.\\d+)?");
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
/*     */   public static double parseAbbreviatedNumber(@NotNull String text) {
/*     */     double parsed;
/*  92 */     if (text.isEmpty()) throw new NumberFormatException("Invalid number format");
/*     */     
/*  94 */     text = text.replaceAll("_", "").toUpperCase(Locale.ENGLISH);
/*     */     
/*     */     try {
/*  97 */       parsed = Double.parseDouble(text.substring(0, text.length() - 1));
/*  98 */     } catch (NumberFormatException e) {
/*  99 */       throw new NumberFormatException("Invalid number format");
/*     */     } 
/*     */ 
/*     */     
/* 103 */     if (text.endsWith("K"))
/* 104 */       return parsed * 1000.0D; 
/* 105 */     if (text.endsWith("M"))
/* 106 */       return parsed * 1000000.0D; 
/* 107 */     if (text.endsWith("B"))
/* 108 */       return parsed * 1.0E9D; 
/* 109 */     if (text.endsWith("T"))
/* 110 */       return parsed * 1.0E12D; 
/* 111 */     if (text.endsWith("Q"))
/* 112 */       return parsed * 1.0E15D; 
/* 113 */     if (text.endsWith("P"))
/* 114 */       return parsed * 1.0E18D; 
/* 115 */     if (text.endsWith("E"))
/* 116 */       return parsed * Math.pow(10.0D, 18.0D); 
/* 117 */     if (text.endsWith("Z"))
/* 118 */       return parsed * Math.pow(10.0D, 21.0D); 
/* 119 */     if (text.endsWith("Y")) {
/* 120 */       return parsed * Math.pow(10.0D, 24.0D);
/*     */     }
/*     */     
/*     */     try {
/* 124 */       return Double.parseDouble(text);
/* 125 */     } catch (NumberFormatException e) {
/* 126 */       throw new NumberFormatException("Number exceeds the range for double");
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\commo\\utility\NumberUtility.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */