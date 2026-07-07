/*    */ package com.atlas.common.metadata;
/*    */ 
/*    */ import java.time.Duration;
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
/*    */ public final class MetadataKeyInformation
/*    */   extends Record
/*    */ {
/*    */   @NotNull
/*    */   private final Object key;
/*    */   @NotNull
/*    */   private final Object value;
/*    */   @Nullable
/*    */   private final Duration duration;
/*    */   
/*    */   public final String toString() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lcom/atlas/common/metadata/Metadata$MetadataKeyInformation;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #56	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/metadata/Metadata$MetadataKeyInformation;
/*    */   }
/*    */   
/*    */   public final int hashCode() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lcom/atlas/common/metadata/Metadata$MetadataKeyInformation;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #56	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lcom/atlas/common/metadata/Metadata$MetadataKeyInformation;
/*    */   }
/*    */   
/*    */   public final boolean equals(Object o) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lcom/atlas/common/metadata/Metadata$MetadataKeyInformation;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #56	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lcom/atlas/common/metadata/Metadata$MetadataKeyInformation;
/*    */     //   0	8	1	o	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public MetadataKeyInformation(@NotNull Object key, @NotNull Object value, @Nullable Duration duration) {
/* 56 */     this.key = key; this.value = value; this.duration = duration; } @NotNull public Object key() { return this.key; } @NotNull public Object value() { return this.value; } @Nullable public Duration duration() { return this.duration; }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isExpirable() {
/* 61 */     return (this.duration != null);
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\metadata\Metadata$MetadataKeyInformation.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */