/*    */ package org.apache.logging.log4j.core.net.ssl;
/*    */ 
/*    */ import javax.net.ssl.HostnameVerifier;
/*    */ import javax.net.ssl.SSLSession;
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
/*    */ public final class LaxHostnameVerifier
/*    */   implements HostnameVerifier
/*    */ {
/* 29 */   public static final HostnameVerifier INSTANCE = new LaxHostnameVerifier();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean verify(String s, SSLSession sslSession) {
/* 36 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\org\apache\logging\log4j\core\net\ssl\LaxHostnameVerifier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */