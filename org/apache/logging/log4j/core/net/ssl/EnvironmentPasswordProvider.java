/*    */ package org.apache.logging.log4j.core.net.ssl;
/*    */ 
/*    */ import java.util.Objects;
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
/*    */ class EnvironmentPasswordProvider
/*    */   implements PasswordProvider
/*    */ {
/*    */   private final String passwordEnvironmentVariable;
/*    */   
/*    */   public EnvironmentPasswordProvider(String passwordEnvironmentVariable) {
/* 46 */     this.passwordEnvironmentVariable = Objects.<String>requireNonNull(passwordEnvironmentVariable, "passwordEnvironmentVariable");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public char[] getPassword() {
/* 52 */     String password = System.getenv(this.passwordEnvironmentVariable);
/* 53 */     return (password == null) ? null : password.toCharArray();
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\org\apache\logging\log4j\core\net\ssl\EnvironmentPasswordProvider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */