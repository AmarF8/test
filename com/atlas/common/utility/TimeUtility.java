/*     */ package com.atlas.common.utility;
/*     */ 
/*     */ import java.text.DateFormat;
/*     */ import java.time.Duration;
/*     */ import java.util.Locale;
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
/*     */ public final class TimeUtility
/*     */ {
/*  18 */   public static final Locale LOCALE = (new Locale.Builder())
/*  19 */     .setLanguage("en")
/*  20 */     .setRegion("US")
/*  21 */     .build();
/*  22 */   public static final DateFormat DATE_FORMAT = DateFormat.getDateTimeInstance(3, 3, LOCALE);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static String getDateTime() {
/*  31 */     return DATE_FORMAT.format(Long.valueOf(System.currentTimeMillis()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static String format(long seconds) {
/*  43 */     if (seconds <= 0L) return "0s"; 
/*  44 */     long _days = seconds / 86400L;
/*  45 */     long _hours = seconds % 86400L / 3600L;
/*  46 */     long _minutes = seconds % 86400L % 3600L / 60L;
/*  47 */     long _seconds = seconds % 86400L % 3600L % 60L;
/*     */     
/*  49 */     StringBuilder string_builder = new StringBuilder();
/*  50 */     if (_days > 0L) string_builder.append(_days).append("d "); 
/*  51 */     if (_hours > 0L) string_builder.append(_hours).append("h "); 
/*  52 */     if (_minutes > 0L) string_builder.append(_minutes).append("m "); 
/*  53 */     if (_seconds > 0L) string_builder.append(_seconds).append("s"); 
/*  54 */     String result = string_builder.toString();
/*  55 */     return result.endsWith(" ") ? result.substring(0, string_builder.length() - 1) : result;
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
/*     */   @NotNull
/*     */   public static String format(long seconds, boolean showSeconds, boolean showMinutes, boolean showHours, boolean showDays) {
/*  71 */     if (seconds <= 0L) return "0s"; 
/*  72 */     long _days = seconds / 86400L;
/*  73 */     long _hours = seconds % 86400L / 3600L;
/*  74 */     long _minutes = seconds % 86400L % 3600L / 60L;
/*  75 */     long _seconds = seconds % 86400L % 3600L % 60L;
/*     */     
/*  77 */     StringBuilder string_builder = new StringBuilder();
/*  78 */     if (showDays && _days > 0L) string_builder.append(_days).append("d "); 
/*  79 */     if (showHours && _hours > 0L) string_builder.append(_hours).append("h "); 
/*  80 */     if (showMinutes && _minutes > 0L) string_builder.append(_minutes).append("m "); 
/*  81 */     if (showSeconds && _seconds > 0L) string_builder.append(_seconds).append("s"); 
/*  82 */     String result = string_builder.toString();
/*  83 */     return result.endsWith(" ") ? result.substring(0, string_builder.length() - 1) : result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static Duration fromReadableString(@NotNull String string) {
/*  95 */     long _days = string.contains("d") ? Long.parseLong(string.split("d")[0]) : 0L;
/*  96 */     long _hours = string.contains("h") ? Long.parseLong(string.split("h")[0]) : 0L;
/*  97 */     long _minutes = string.contains("m") ? Long.parseLong(string.split("m")[0]) : 0L;
/*  98 */     long _seconds = string.contains("s") ? Long.parseLong(string.split("s")[0]) : 0L;
/*  99 */     return 
/* 100 */       Duration.ofDays(_days)
/* 101 */       .plusHours(_hours)
/* 102 */       .plusMinutes(_minutes)
/* 103 */       .plusSeconds(_seconds);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static long getDuration(String input) {
/* 110 */     if (Character.isLetter(input.charAt(0))) return -1L;
/*     */ 
/*     */     
/* 113 */     long result = 0L;
/*     */ 
/*     */     
/* 116 */     StringBuilder number = new StringBuilder();
/*     */ 
/*     */     
/* 119 */     for (int i = 0; i < input.length(); i++) {
/* 120 */       char character = input.charAt(i);
/*     */ 
/*     */       
/* 123 */       if (Character.isDigit(character)) {
/* 124 */         number.append(character);
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */         
/* 130 */         String str = number.toString();
/*     */ 
/*     */         
/* 133 */         if (Character.isLetter(character) && !str.isEmpty()) {
/*     */           
/* 135 */           result += convert(Integer.parseInt(str), character);
/*     */ 
/*     */           
/* 138 */           number.setLength(0);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 143 */     return result;
/*     */   }
/*     */   
/*     */   private static long convert(int value, char charType) {
/* 147 */     switch (charType) { case 'y': case 'M': case 'w': case 'd': case 'h': case 'm': case 's':  }  return 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 155 */       -1L;
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\commo\\utility\TimeUtility.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */