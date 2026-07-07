/*    */ package org.apache.logging.log4j.core.layout.internal;
/*    */ 
/*    */ import java.util.List;
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
/*    */ public class IncludeChecker
/*    */   implements ListChecker
/*    */ {
/*    */   private final List<String> list;
/*    */   
/*    */   public IncludeChecker(List<String> list) {
/* 28 */     this.list = list;
/*    */   }
/*    */   
/*    */   public boolean check(String key) {
/* 32 */     return this.list.contains(key);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 37 */     return "ThreadContextIncludes=" + this.list.toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\org\apache\logging\log4j\core\layout\internal\IncludeChecker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */