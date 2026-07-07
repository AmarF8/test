/*     */ package com.atlas.common.fabric.clientverify;
/*     */ 
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
/*     */ public final class RequestPayload
/*     */   implements class_8710
/*     */ {
/*     */   @NotNull
/*  72 */   public static final class_8710.class_9154<RequestPayload> ID = new class_8710.class_9154(
/*  73 */       class_2960.method_60655("atlas", "client_verify_request"));
/*     */   @NotNull
/*  75 */   public static final class_9139<class_2540, RequestPayload> CODEC = class_9139.method_56438(RequestPayload::write, RequestPayload::read);
/*     */   
/*     */   private final String hash;
/*     */   private final String integrityHex;
/*     */   private final String baseHash;
/*     */   
/*     */   public RequestPayload(@NotNull String hash, @NotNull String integrityHex, @NotNull String baseHash) {
/*  82 */     this.hash = hash;
/*  83 */     this.integrityHex = integrityHex;
/*  84 */     this.baseHash = baseHash;
/*     */   }
/*     */   
/*     */   private static RequestPayload read(class_2540 buf) {
/*  88 */     return new RequestPayload(buf.method_19772(), buf.method_19772(), buf.method_19772());
/*     */   }
/*     */   
/*     */   private void write(class_2540 buf) {
/*  92 */     buf.method_10814(this.hash);
/*  93 */     buf.method_10814(this.integrityHex);
/*  94 */     buf.method_10814(this.baseHash);
/*     */   }
/*     */   @NotNull
/*  97 */   public String getHash() { return this.hash; } @NotNull
/*  98 */   public String getIntegrityHex() { return this.integrityHex; } @NotNull
/*  99 */   public String getBaseHash() { return this.baseHash; }
/*     */    @NotNull
/*     */   public class_8710.class_9154<? extends class_8710> method_56479() {
/* 102 */     return (class_8710.class_9154)ID;
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\clientverify\ClientVerifyNetwork$RequestPayload.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */