/*    */ package net.kyori.adventure.key;
/*    */ 
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
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
/*    */ public final class InvalidKeyException
/*    */   extends RuntimeException
/*    */ {
/*    */   private static final long serialVersionUID = -5413304087321449434L;
/*    */   private final String keyNamespace;
/*    */   private final String keyValue;
/*    */   
/*    */   InvalidKeyException(@NotNull String keyNamespace, @NotNull String keyValue, @Nullable String message) {
/* 40 */     super(message);
/* 41 */     this.keyNamespace = keyNamespace;
/* 42 */     this.keyValue = keyValue;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public final String keyNamespace() {
/* 52 */     return this.keyNamespace;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public final String keyValue() {
/* 62 */     return this.keyValue;
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\net\kyori\adventure\key\InvalidKeyException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */