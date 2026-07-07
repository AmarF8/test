/*    */ package com.atlas.common.fabric.clientverify;
/*    */ 
/*    */ import java.util.Base64;
/*    */ import net.minecraft.class_2540;
/*    */ import net.minecraft.class_2960;
/*    */ import net.minecraft.class_8710;
/*    */ import net.minecraft.class_9139;
/*    */ import org.jetbrains.annotations.NotNull;
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
/*    */ public final class ChallengePayload
/*    */   implements class_8710
/*    */ {
/*    */   @NotNull
/* 33 */   public static final class_8710.class_9154<ChallengePayload> ID = new class_8710.class_9154(
/* 34 */       class_2960.method_60655("atlas", "client_verify_challenge"));
/*    */   @NotNull
/* 36 */   public static final class_9139<class_2540, ChallengePayload> CODEC = class_9139.method_56438(ChallengePayload::write, ChallengePayload::read);
/*    */   
/*    */   private final String nonce;
/*    */   private final byte[] hmacKey;
/*    */   private final boolean integrityEnabled;
/*    */   
/*    */   public ChallengePayload(@NotNull String nonce, byte[] hmacKey, boolean integrityEnabled) {
/* 43 */     this.nonce = nonce;
/* 44 */     this.hmacKey = hmacKey;
/* 45 */     this.integrityEnabled = integrityEnabled;
/*    */   }
/*    */   
/*    */   private static ChallengePayload read(class_2540 buf) {
/* 49 */     return new ChallengePayload(buf
/* 50 */         .method_19772(), 
/* 51 */         Base64.getDecoder().decode(buf.method_19772()), buf
/* 52 */         .method_19772().equals("1"));
/*    */   }
/*    */ 
/*    */   
/*    */   private void write(class_2540 buf) {
/* 57 */     buf.method_10814(this.nonce);
/* 58 */     buf.method_10814(Base64.getEncoder().encodeToString(this.hmacKey));
/* 59 */     buf.method_10814(this.integrityEnabled ? "1" : "0");
/*    */   }
/*    */   @NotNull
/* 62 */   public String getNonce() { return this.nonce; }
/* 63 */   public byte[] getHmacKey() { return this.hmacKey; } public boolean isIntegrityEnabled() {
/* 64 */     return this.integrityEnabled;
/*    */   } @NotNull
/*    */   public class_8710.class_9154<? extends class_8710> method_56479() {
/* 67 */     return (class_8710.class_9154)ID;
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\clientverify\ClientVerifyNetwork$ChallengePayload.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */