/*    */ package com.atlas.common;
/*    */ 
/*    */ import com.atlas.common.scheduler.SchedulerProvider;
/*    */ import java.util.Objects;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class AtlasCommon
/*    */ {
/*    */   @Nullable
/*    */   private static AtlasCommon INSTANCE;
/*    */   private final AtlasProjectType projectType;
/*    */   private final Logger logger;
/*    */   private final SchedulerProvider schedulerProvider;
/*    */   
/*    */   public static AtlasCommon getInstance() {
/* 27 */     return Objects.<AtlasCommon>requireNonNull(INSTANCE, "Atlas common has not been initialized.");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public static AtlasCommon initialize(@NotNull AtlasProjectType projectType) {
/* 39 */     return new AtlasCommon(projectType);
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
/*    */   public AtlasCommon(@NotNull AtlasProjectType projectType) {
/* 53 */     this(projectType, LoggerFactory.getLogger(AtlasCommon.class));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public AtlasCommon(@NotNull AtlasProjectType projectType, @NotNull Logger logger) {
/* 64 */     this.projectType = Objects.<AtlasProjectType>requireNonNull(projectType);
/* 65 */     this.logger = Objects.<Logger>requireNonNull(logger);
/*    */     
/* 67 */     INSTANCE = this;
/*    */     
/* 69 */     this.schedulerProvider = new SchedulerProvider();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public AtlasProjectType getProjectType() {
/* 79 */     return this.projectType;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Logger getLogger() {
/* 86 */     return this.logger;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public SchedulerProvider getSchedulerProvider() {
/* 93 */     return this.schedulerProvider;
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\AtlasCommon.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */