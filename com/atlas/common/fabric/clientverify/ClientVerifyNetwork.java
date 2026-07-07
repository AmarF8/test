/*     */ package com.atlas.common.fabric.clientverify;
/*     */ 
/*     */ import java.util.Base64;
/*     */ import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
/*     */ import net.minecraft.class_2540;
/*     */ import net.minecraft.class_2960;
/*     */ import net.minecraft.class_8710;
/*     */ import net.minecraft.class_9139;
/*     */ import org.jetbrains.annotations.NotNull;
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
/*     */ public final class ClientVerifyNetwork
/*     */ {
/*     */   public static final class ChallengePayload
/*     */     implements class_8710
/*     */   {
/*     */     @NotNull
/*  33 */     public static final class_8710.class_9154<ChallengePayload> ID = new class_8710.class_9154(
/*  34 */         class_2960.method_60655("atlas", "client_verify_challenge"));
/*     */     @NotNull
/*  36 */     public static final class_9139<class_2540, ChallengePayload> CODEC = class_9139.method_56438(ChallengePayload::write, ChallengePayload::read);
/*     */     
/*     */     private final String nonce;
/*     */     private final byte[] hmacKey;
/*     */     private final boolean integrityEnabled;
/*     */     
/*     */     public ChallengePayload(@NotNull String nonce, byte[] hmacKey, boolean integrityEnabled) {
/*  43 */       this.nonce = nonce;
/*  44 */       this.hmacKey = hmacKey;
/*  45 */       this.integrityEnabled = integrityEnabled;
/*     */     }
/*     */     
/*     */     private static ChallengePayload read(class_2540 buf) {
/*  49 */       return new ChallengePayload(buf
/*  50 */           .method_19772(), 
/*  51 */           Base64.getDecoder().decode(buf.method_19772()), buf
/*  52 */           .method_19772().equals("1"));
/*     */     }
/*     */ 
/*     */     
/*     */     private void write(class_2540 buf) {
/*  57 */       buf.method_10814(this.nonce);
/*  58 */       buf.method_10814(Base64.getEncoder().encodeToString(this.hmacKey));
/*  59 */       buf.method_10814(this.integrityEnabled ? "1" : "0");
/*     */     }
/*     */     @NotNull
/*  62 */     public String getNonce() { return this.nonce; }
/*  63 */     public byte[] getHmacKey() { return this.hmacKey; } public boolean isIntegrityEnabled() {
/*  64 */       return this.integrityEnabled;
/*     */     } @NotNull
/*     */     public class_8710.class_9154<? extends class_8710> method_56479() {
/*  67 */       return (class_8710.class_9154)ID;
/*     */     }
/*     */   }
/*     */   
/*     */   public static final class RequestPayload implements class_8710 { @NotNull
/*  72 */     public static final class_8710.class_9154<RequestPayload> ID = new class_8710.class_9154(
/*  73 */         class_2960.method_60655("atlas", "client_verify_request"));
/*     */     @NotNull
/*  75 */     public static final class_9139<class_2540, RequestPayload> CODEC = class_9139.method_56438(RequestPayload::write, RequestPayload::read);
/*     */     
/*     */     private final String hash;
/*     */     private final String integrityHex;
/*     */     private final String baseHash;
/*     */     
/*     */     public RequestPayload(@NotNull String hash, @NotNull String integrityHex, @NotNull String baseHash) {
/*  82 */       this.hash = hash;
/*  83 */       this.integrityHex = integrityHex;
/*  84 */       this.baseHash = baseHash;
/*     */     }
/*     */     
/*     */     private static RequestPayload read(class_2540 buf) {
/*  88 */       return new RequestPayload(buf.method_19772(), buf.method_19772(), buf.method_19772());
/*     */     }
/*     */     
/*     */     private void write(class_2540 buf) {
/*  92 */       buf.method_10814(this.hash);
/*  93 */       buf.method_10814(this.integrityHex);
/*  94 */       buf.method_10814(this.baseHash);
/*     */     }
/*     */     @NotNull
/*  97 */     public String getHash() { return this.hash; } @NotNull
/*  98 */     public String getIntegrityHex() { return this.integrityHex; } @NotNull
/*  99 */     public String getBaseHash() { return this.baseHash; }
/*     */      @NotNull
/*     */     public class_8710.class_9154<? extends class_8710> method_56479() {
/* 102 */       return (class_8710.class_9154)ID;
/*     */     } }
/*     */   
/*     */   public static final class ResponsePayload implements class_8710 {
/*     */     @NotNull
/* 107 */     public static final class_8710.class_9154<ResponsePayload> ID = new class_8710.class_9154(
/* 108 */         class_2960.method_60655("atlas", "client_verify_response"));
/*     */     @NotNull
/* 110 */     public static final class_9139<class_2540, ResponsePayload> CODEC = class_9139.method_56438(ResponsePayload::write, ResponsePayload::read);
/*     */     private final boolean verified;
/*     */     
/*     */     public ResponsePayload(boolean verified) {
/* 114 */       this.verified = verified;
/*     */     }
/*     */     private static ResponsePayload read(class_2540 buf) {
/* 117 */       return new ResponsePayload(buf.readBoolean());
/*     */     }
/*     */     private void write(class_2540 buf) {
/* 120 */       buf.method_52964(this.verified);
/*     */     } public boolean isVerified() {
/* 122 */       return this.verified;
/*     */     } @NotNull
/*     */     public class_8710.class_9154<? extends class_8710> method_56479() {
/* 125 */       return (class_8710.class_9154)ID;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void init() {
/* 133 */     PayloadTypeRegistry.playS2C().register(ChallengePayload.ID, ChallengePayload.CODEC);
/* 134 */     PayloadTypeRegistry.playS2C().register(ResponsePayload.ID, ResponsePayload.CODEC);
/* 135 */     PayloadTypeRegistry.playC2S().register(RequestPayload.ID, RequestPayload.CODEC);
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\clientverify\ClientVerifyNetwork.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */