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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class ResponsePayload
/*     */   implements class_8710
/*     */ {
/*     */   @NotNull
/* 107 */   public static final class_8710.class_9154<ResponsePayload> ID = new class_8710.class_9154(
/* 108 */       class_2960.method_60655("atlas", "client_verify_response"));
/*     */   @NotNull
/* 110 */   public static final class_9139<class_2540, ResponsePayload> CODEC = class_9139.method_56438(ResponsePayload::write, ResponsePayload::read);
/*     */   private final boolean verified;
/*     */   
/*     */   public ResponsePayload(boolean verified) {
/* 114 */     this.verified = verified;
/*     */   }
/*     */   private static ResponsePayload read(class_2540 buf) {
/* 117 */     return new ResponsePayload(buf.readBoolean());
/*     */   }
/*     */   private void write(class_2540 buf) {
/* 120 */     buf.method_52964(this.verified);
/*     */   } public boolean isVerified() {
/* 122 */     return this.verified;
/*     */   } @NotNull
/*     */   public class_8710.class_9154<? extends class_8710> method_56479() {
/* 125 */     return (class_8710.class_9154)ID;
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\clientverify\ClientVerifyNetwork$ResponsePayload.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */