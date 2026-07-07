/*    */ package com.atlas.common.fabric.cosmetic.preview;
/*    */ 
/*    */ import com.atlas.common.fabric.AtlasMod;
/*    */ import java.util.function.Function;
/*    */ import net.minecraft.class_2960;
/*    */ import net.minecraft.class_8710;
/*    */ import net.minecraft.class_9129;
/*    */ import net.minecraft.class_9135;
/*    */ import net.minecraft.class_9139;
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
/*    */ public final class CosmeticPreviewNetwork
/*    */ {
/* 23 */   public static final class_2960 OPEN_PREVIEW_ID = class_2960.method_60655("atlas_cosmetic", "open_preview");
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
/* 35 */   public static final class_8710.class_9154<OpenCosmeticPreviewPayload> OPEN_PREVIEW_TYPE = new class_8710.class_9154(OPEN_PREVIEW_ID);
/*    */ 
/*    */   
/* 38 */   public static final class_9139<class_9129, OpenCosmeticPreviewPayload> OPEN_PREVIEW_CODEC = class_9139.method_56434(class_9135.field_49675, OpenCosmeticPreviewPayload::dummy, OpenCosmeticPreviewPayload::new);
/*    */   public static final class OpenCosmeticPreviewPayload extends Record implements class_8710 { private final int dummy;
/*    */     public final String toString() {
/*    */       // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/cosmetic/preview/CosmeticPreviewNetwork$OpenCosmeticPreviewPayload;)Ljava/lang/String;
/*    */       //   6: areturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #47	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	7	0	this	Lcom/atlas/common/fabric/cosmetic/preview/CosmeticPreviewNetwork$OpenCosmeticPreviewPayload;
/*    */     }
/*    */     public final int hashCode() {
/*    */       // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/cosmetic/preview/CosmeticPreviewNetwork$OpenCosmeticPreviewPayload;)I
/*    */       //   6: ireturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #47	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	7	0	this	Lcom/atlas/common/fabric/cosmetic/preview/CosmeticPreviewNetwork$OpenCosmeticPreviewPayload;
/*    */     }
/*    */     
/* 47 */     public OpenCosmeticPreviewPayload(int dummy) { this.dummy = dummy; } public int dummy() { return this.dummy; }
/*    */     public final boolean equals(Object o) { // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: aload_1
/*    */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/cosmetic/preview/CosmeticPreviewNetwork$OpenCosmeticPreviewPayload;Ljava/lang/Object;)Z
/*    */       //   7: ireturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #47	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	8	0	this	Lcom/atlas/common/fabric/cosmetic/preview/CosmeticPreviewNetwork$OpenCosmeticPreviewPayload;
/* 48 */       //   0	8	1	o	Ljava/lang/Object; } public OpenCosmeticPreviewPayload() { this(0); } public class_8710.class_9154<? extends class_8710> method_56479() {
/* 49 */       return (class_8710.class_9154)CosmeticPreviewNetwork.OPEN_PREVIEW_TYPE;
/*    */     } }
/*    */ 
/*    */ 
/*    */   
/*    */   public static void init() {
/* 55 */     CosmeticPreviewNetworkHelper.registerPackets();
/* 56 */     AtlasMod.LOGGER.info("Cosmetic Preview network initialized");
/*    */   }
/*    */   
/*    */   public static void initClient() {
/* 60 */     CosmeticPreviewNetworkHelper.registerClientPackets();
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\cosmetic\preview\CosmeticPreviewNetwork.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */