/*    */ package org.apache.logging.log4j.core.appender.nosql;
/*    */ 
/*    */ import java.util.concurrent.atomic.AtomicBoolean;
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
/*    */ 
/*    */ 
/*    */ public abstract class AbstractNoSqlConnection<W, T extends NoSqlObject<W>>
/*    */   implements NoSqlConnection<W, T>
/*    */ {
/* 32 */   private final AtomicBoolean closed = new AtomicBoolean(false);
/*    */ 
/*    */   
/*    */   public void close() {
/* 36 */     if (this.closed.compareAndSet(false, true)) {
/* 37 */       closeImpl();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   protected abstract void closeImpl();
/*    */   
/*    */   public boolean isClosed() {
/* 45 */     return this.closed.get();
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\org\apache\logging\log4j\core\appender\nosql\AbstractNoSqlConnection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */