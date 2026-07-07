/*     */ package com.atlas.common.fabric.starterguide;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class PlayerStarterGuideData
/*     */ {
/*  10 */   private static final PlayerStarterGuideData INSTANCE = new PlayerStarterGuideData();
/*     */   
/*  12 */   private int stepIndex = 0;
/*  13 */   private double progress = 0.0D;
/*  14 */   private int target = 1;
/*  15 */   private String description = "";
/*  16 */   private String hint = "";
/*  17 */   private String rewardDescription = "";
/*  18 */   private int totalSteps = 0;
/*     */   private boolean completed = false;
/*     */   private boolean visible = true;
/*  21 */   private String stepType = "";
/*  22 */   private String stepDetail = "";
/*     */   
/*     */   private boolean showingCompletion = false;
/*     */   
/*  26 */   private long completionAnimStartTime = 0L;
/*  27 */   private String completedStepDescription = "";
/*     */   
/*     */   private static final long COMPLETION_ANIM_DURATION_MS = 2000L;
/*     */ 
/*     */   
/*     */   public static PlayerStarterGuideData getInstance() {
/*  33 */     return INSTANCE;
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
/*     */   public void updateFromSync(int stepIndex, double progress, int target, String description, String hint, String rewardDescription, int totalSteps, boolean completed, String stepType, String stepDetail) {
/*  45 */     this.stepIndex = stepIndex;
/*  46 */     this.progress = progress;
/*  47 */     this.target = target;
/*  48 */     this.description = description;
/*  49 */     this.hint = hint;
/*  50 */     this.rewardDescription = rewardDescription;
/*  51 */     this.totalSteps = totalSteps;
/*  52 */     this.completed = completed;
/*  53 */     this.stepType = stepType;
/*  54 */     this.stepDetail = stepDetail;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleStepComplete(int nextStepIndex, int nextTarget, String nextDescription, String nextHint, String nextRewardDescription, int totalSteps, String nextStepType, String nextStepDetail) {
/*  65 */     this.completedStepDescription = this.description;
/*     */ 
/*     */     
/*  68 */     this.visible = true;
/*     */ 
/*     */     
/*  71 */     this.showingCompletion = true;
/*  72 */     this.completionAnimStartTime = System.currentTimeMillis();
/*     */ 
/*     */     
/*  75 */     this.stepIndex = nextStepIndex;
/*  76 */     this.progress = 0.0D;
/*  77 */     this.target = nextTarget;
/*  78 */     this.description = nextDescription;
/*  79 */     this.hint = nextHint;
/*  80 */     this.rewardDescription = nextRewardDescription;
/*  81 */     this.totalSteps = totalSteps;
/*  82 */     this.stepType = nextStepType;
/*  83 */     this.stepDetail = nextStepDetail;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void toggleVisibility() {
/*  90 */     this.visible = !this.visible;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isShowingCompletion() {
/*  97 */     if (this.showingCompletion && System.currentTimeMillis() - this.completionAnimStartTime > 2000L) {
/*  98 */       this.showingCompletion = false;
/*     */     }
/* 100 */     return this.showingCompletion;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getCompletionAnimProgress() {
/* 107 */     if (!this.showingCompletion) return 0.0F; 
/* 108 */     long elapsed = System.currentTimeMillis() - this.completionAnimStartTime;
/* 109 */     return Math.min(1.0F, (float)elapsed / 2000.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getStepIndex() {
/* 114 */     return this.stepIndex; }
/* 115 */   public double getProgress() { return this.progress; }
/* 116 */   public int getTarget() { return this.target; }
/* 117 */   public String getDescription() { return this.description; }
/* 118 */   public String getHint() { return this.hint; }
/* 119 */   public String getRewardDescription() { return this.rewardDescription; }
/* 120 */   public int getTotalSteps() { return this.totalSteps; }
/* 121 */   public boolean isCompleted() { return this.completed; }
/* 122 */   public String getCompletedStepDescription() { return this.completedStepDescription; }
/* 123 */   public boolean isVisible() { return this.visible; }
/* 124 */   public String getStepType() { return this.stepType; } public String getStepDetail() {
/* 125 */     return this.stepDetail;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasData() {
/* 131 */     return (this.totalSteps > 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldRender() {
/* 138 */     return (this.visible && hasData() && !this.completed);
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\starterguide\PlayerStarterGuideData.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */