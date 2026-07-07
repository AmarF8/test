/*     */ package com.atlas.common.fabric.safari.fishing.client;
/*     */ 
/*     */ import com.atlas.common.fabric.safari.fishing.SafariFishingNetwork;
/*     */ import net.fabricmc.api.EnvType;
/*     */ import net.fabricmc.api.Environment;
/*     */ 
/*     */ @Environment(EnvType.CLIENT)
/*     */ public final class SafariFishingHudState {
/*   9 */   private static final SafariFishingHudState INSTANCE = new SafariFishingHudState();
/*     */   
/*     */   public static SafariFishingHudState getInstance() {
/*  12 */     return INSTANCE;
/*     */   }
/*     */   
/*     */   private boolean active;
/*  16 */   private String phase = "NONE";
/*  17 */   private String fishName = "";
/*  18 */   private String rarityName = "";
/*  19 */   private int rarityColor = -1;
/*     */   private float weightKg;
/*     */   private float sizeScale;
/*     */   private float pointer;
/*     */   private float zoneCenter;
/*     */   private float zoneWidth;
/*     */   private float progress;
/*     */   private float tension;
/*     */   private boolean thrashing;
/*     */   private int waitTicks;
/*  29 */   private String baitName = "";
/*  30 */   private String lineName = "";
/*  31 */   private String reelName = "";
/*  32 */   private String resultTitle = "";
/*  33 */   private String resultSubtitle = "";
/*  34 */   private int resultColor = -1;
/*     */ 
/*     */   
/*     */   private int resultTicks;
/*     */ 
/*     */   
/*     */   public void applyUpdate(SafariFishingNetwork.UpdatePayload payload) {
/*  41 */     this.active = payload.active();
/*  42 */     this.phase = payload.phase();
/*  43 */     this.fishName = payload.fishName();
/*  44 */     this.rarityName = payload.rarityName();
/*  45 */     this.rarityColor = payload.rarityColor();
/*  46 */     this.weightKg = payload.weightKg();
/*  47 */     this.sizeScale = payload.sizeScale();
/*  48 */     this.pointer = payload.pointer();
/*  49 */     this.zoneCenter = payload.zoneCenter();
/*  50 */     this.zoneWidth = payload.zoneWidth();
/*  51 */     this.progress = payload.progress();
/*  52 */     this.tension = payload.tension();
/*  53 */     this.thrashing = payload.thrashing();
/*  54 */     this.waitTicks = payload.waitTicks();
/*  55 */     this.baitName = payload.baitName();
/*  56 */     this.lineName = payload.lineName();
/*  57 */     this.reelName = payload.reelName();
/*     */   }
/*     */   
/*     */   public void applyResult(SafariFishingNetwork.ResultPayload payload) {
/*  61 */     this.active = false;
/*  62 */     this.phase = "NONE";
/*  63 */     this.resultTitle = payload.title();
/*  64 */     this.resultSubtitle = payload.subtitle();
/*  65 */     this.resultColor = payload.color();
/*  66 */     this.resultTicks = 60;
/*     */   }
/*     */   
/*     */   public void tick() {
/*  70 */     if (this.resultTicks > 0) this.resultTicks--; 
/*     */   }
/*     */   
/*     */   public boolean isActive() {
/*  74 */     return this.active;
/*     */   }
/*     */   
/*     */   public String getPhase() {
/*  78 */     return this.phase;
/*     */   }
/*     */   
/*     */   public String getFishName() {
/*  82 */     return this.fishName;
/*     */   }
/*     */   
/*     */   public String getRarityName() {
/*  86 */     return this.rarityName;
/*     */   }
/*     */   
/*     */   public int getRarityColor() {
/*  90 */     return this.rarityColor;
/*     */   }
/*     */   
/*     */   public float getWeightKg() {
/*  94 */     return this.weightKg;
/*     */   }
/*     */   
/*     */   public float getSizeScale() {
/*  98 */     return this.sizeScale;
/*     */   }
/*     */   
/*     */   public float getPointer() {
/* 102 */     return this.pointer;
/*     */   }
/*     */   
/*     */   public float getZoneCenter() {
/* 106 */     return this.zoneCenter;
/*     */   }
/*     */   
/*     */   public float getZoneWidth() {
/* 110 */     return this.zoneWidth;
/*     */   }
/*     */   
/*     */   public float getProgress() {
/* 114 */     return this.progress;
/*     */   }
/*     */   
/*     */   public float getTension() {
/* 118 */     return this.tension;
/*     */   }
/*     */   
/*     */   public boolean isThrashing() {
/* 122 */     return this.thrashing;
/*     */   }
/*     */   
/*     */   public int getWaitTicks() {
/* 126 */     return this.waitTicks;
/*     */   }
/*     */   
/*     */   public String getBaitName() {
/* 130 */     return this.baitName;
/*     */   }
/*     */   
/*     */   public String getLineName() {
/* 134 */     return this.lineName;
/*     */   }
/*     */   
/*     */   public String getReelName() {
/* 138 */     return this.reelName;
/*     */   }
/*     */   
/*     */   public String getResultTitle() {
/* 142 */     return this.resultTitle;
/*     */   }
/*     */   
/*     */   public String getResultSubtitle() {
/* 146 */     return this.resultSubtitle;
/*     */   }
/*     */   
/*     */   public int getResultColor() {
/* 150 */     return this.resultColor;
/*     */   }
/*     */   
/*     */   public int getResultTicks() {
/* 154 */     return this.resultTicks;
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\safari\fishing\client\SafariFishingHudState.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */