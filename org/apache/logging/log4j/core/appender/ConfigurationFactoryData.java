/*    */ package org.apache.logging.log4j.core.appender;
/*    */ 
/*    */ import org.apache.logging.log4j.core.LoggerContext;
/*    */ import org.apache.logging.log4j.core.config.Configuration;
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
/*    */ 
/*    */ public class ConfigurationFactoryData
/*    */ {
/*    */   public final Configuration configuration;
/*    */   
/*    */   public ConfigurationFactoryData(Configuration configuration) {
/* 33 */     this.configuration = configuration;
/*    */   }
/*    */   
/*    */   public Configuration getConfiguration() {
/* 37 */     return this.configuration;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public LoggerContext getLoggerContext() {
/* 46 */     return (this.configuration != null) ? this.configuration.getLoggerContext() : null;
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\org\apache\logging\log4j\core\appender\ConfigurationFactoryData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */