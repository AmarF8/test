/*     */ package com.atlas.common.scheduler;
/*     */ 
/*     */ import com.atlas.common.AtlasCommon;
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
/*     */ class SchedulerDiagnosticCommand
/*     */ {
/*     */   private final SchedulerProvider provider;
/*     */   private final SchedulerMonitor monitor;
/*     */   
/*     */   public SchedulerDiagnosticCommand(SchedulerProvider provider, SchedulerMonitor monitor) {
/* 266 */     this.provider = provider;
/* 267 */     this.monitor = monitor;
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
/*     */   public void execute(String[] args) {
/* 280 */     if (args.length == 0) {
/* 281 */       showUsage();
/*     */       
/*     */       return;
/*     */     } 
/* 285 */     switch (args[0].toLowerCase()) { case "status":
/* 286 */         showStatus(); return;
/* 287 */       case "report": showDetailedReport(); return;
/* 288 */       case "health": forceHealthCheck(); return;
/*     */       case "scheduler":
/* 290 */         if (args.length > 1) {
/*     */           try {
/* 292 */             long id = Long.parseLong(args[1]);
/* 293 */             showSchedulerInfo(id);
/* 294 */           } catch (NumberFormatException e) {
/* 295 */             AtlasCommon.getInstance().getLogger().error("Invalid scheduler ID: {}", args[1]);
/*     */           } 
/*     */         } else {
/* 298 */           AtlasCommon.getInstance().getLogger().error("Scheduler ID required");
/*     */         }  return;
/*     */       case "cleanup":
/* 301 */         cleanup(); return; }
/* 302 */      showUsage();
/*     */   }
/*     */ 
/*     */   
/*     */   private void showUsage() {
/* 307 */     AtlasCommon.getInstance().getLogger().info("Scheduler Diagnostic Commands:\n  status - Show brief system status\n  report - Show detailed health report\n  health - Force immediate health check\n  scheduler <id> - Show info for specific scheduler\n  cleanup - Clean up old metrics\n");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void showStatus() {
/* 318 */     AtlasCommon.getInstance().getLogger().info("Scheduler Status: {}", this.provider.getDebugStats());
/*     */   }
/*     */   
/*     */   private void showDetailedReport() {
/* 322 */     String report = this.monitor.getDetailedReport();
/* 323 */     AtlasCommon.getInstance().getLogger().info("\n{}", report);
/*     */   }
/*     */   
/*     */   private void forceHealthCheck() {
/* 327 */     AtlasCommon.getInstance().getLogger().info("Performing forced health check...");
/* 328 */     this.monitor.performHealthCheck();
/*     */   }
/*     */   
/*     */   private void showSchedulerInfo(long id) {
/* 332 */     this.provider.getByIdentifier(id).ifPresentOrElse(scheduler -> {
/*     */           AtlasCommon.getInstance().getLogger().info("Scheduler Info: {}", scheduler.getDebugInfo());
/*     */           SchedulerMonitor.SchedulerMetrics metrics = this.monitor.getMetrics(id);
/*     */           if (metrics != null) {
/*     */             AtlasCommon.getInstance().getLogger().info("Health Metrics: {}/{} healthy checks ({}%)", new Object[] { Integer.valueOf(metrics.healthyChecks), Integer.valueOf(metrics.totalHealthChecks), String.format("%.1f", new Object[] { Double.valueOf(metrics.getHealthPercentage()) }) });
/*     */           }
/*     */         }() -> AtlasCommon.getInstance().getLogger().error("Scheduler {} not found", Long.valueOf(id)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void cleanup() {
/* 349 */     this.monitor.cleanupOldMetrics();
/* 350 */     AtlasCommon.getInstance().getLogger().info("Cleaned up old scheduler metrics");
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\scheduler\SchedulerDiagnosticCommand.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */