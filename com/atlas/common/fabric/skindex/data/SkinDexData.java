/*     */ package com.atlas.common.fabric.skindex.data;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class SkinDexData
/*     */ {
/*  12 */   private static final SkinDexData INSTANCE = new SkinDexData();
/*     */ 
/*     */ 
/*     */   
/*     */   public static SkinDexData getInstance() {
/*  17 */     return INSTANCE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  22 */   private List<SkinDexLineInfo> lines = Collections.emptyList();
/*     */ 
/*     */   
/*     */   private boolean linesLoading = false;
/*     */   
/*  27 */   private SkinDexDetailInfo currentDetail = null;
/*     */ 
/*     */   
/*     */   private boolean detailLoading = false;
/*     */   
/*  32 */   private String lastClaimResult = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleData(String dataType, String jsonData) {
/*  40 */     if ("lines".equals(dataType)) {
/*  41 */       this.lines = SkinDexLineInfo.fromJsonArray(jsonData);
/*  42 */       this.linesLoading = false;
/*  43 */     } else if ("detail".equals(dataType)) {
/*  44 */       this.currentDetail = SkinDexDetailInfo.fromJson(jsonData);
/*  45 */       this.detailLoading = false;
/*  46 */     } else if (dataType.startsWith("claim_result:")) {
/*     */       
/*  48 */       this.lastClaimResult = dataType.substring("claim_result:".length());
/*  49 */       this.lines = SkinDexLineInfo.fromJsonArray(jsonData);
/*  50 */       this.linesLoading = false;
/*     */       
/*  52 */       if (this.currentDetail != null)
/*     */       {
/*  54 */         this.detailLoading = false;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<SkinDexLineInfo> getLines() {
/*  62 */     return this.lines;
/*     */   }
/*     */   
/*     */   public boolean isLinesLoading() {
/*  66 */     return this.linesLoading;
/*     */   }
/*     */   
/*     */   public void setLinesLoading(boolean loading) {
/*  70 */     this.linesLoading = loading;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public SkinDexDetailInfo getCurrentDetail() {
/*  76 */     return this.currentDetail;
/*     */   }
/*     */   
/*     */   public boolean isDetailLoading() {
/*  80 */     return this.detailLoading;
/*     */   }
/*     */   
/*     */   public void setDetailLoading(boolean loading) {
/*  84 */     this.detailLoading = loading;
/*     */   }
/*     */   
/*     */   public void clearDetail() {
/*  88 */     this.currentDetail = null;
/*  89 */     this.detailLoading = false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLastClaimResult() {
/*  95 */     return this.lastClaimResult;
/*     */   }
/*     */   
/*     */   public void clearClaimResult() {
/*  99 */     this.lastClaimResult = null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearAll() {
/* 105 */     this.lines = Collections.emptyList();
/* 106 */     this.linesLoading = false;
/* 107 */     this.currentDetail = null;
/* 108 */     this.detailLoading = false;
/* 109 */     this.lastClaimResult = null;
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\skindex\data\SkinDexData.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */