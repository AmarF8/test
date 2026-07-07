/*    */ package com.atlas.common.fabric.emoji.data;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class EmojiData
/*    */ {
/* 11 */   private static final EmojiData INSTANCE = new EmojiData();
/*    */   
/* 13 */   private List<EmojiEntry> emojis = Collections.emptyList();
/*    */ 
/*    */ 
/*    */   
/*    */   public static EmojiData getInstance() {
/* 18 */     return INSTANCE;
/*    */   }
/*    */   
/*    */   public List<EmojiEntry> getEmojis() {
/* 22 */     return this.emojis;
/*    */   }
/*    */   
/*    */   public void setEmojis(List<EmojiEntry> emojis) {
/* 26 */     this.emojis = Collections.unmodifiableList(new ArrayList<>(emojis));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public List<EmojiEntry> findByPrefix(String prefix, int limit) {
/* 37 */     if (prefix.isEmpty()) return this.emojis.subList(0, Math.min(limit, this.emojis.size())); 
/* 38 */     String lower = prefix.toLowerCase();
/* 39 */     List<EmojiEntry> results = new ArrayList<>();
/* 40 */     for (EmojiEntry entry : this.emojis) {
/* 41 */       if (entry.name().toLowerCase().startsWith(lower)) {
/* 42 */         results.add(entry);
/* 43 */         if (results.size() >= limit)
/*    */           break; 
/*    */       } 
/* 46 */     }  return results;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public EmojiEntry findByName(String name) {
/* 53 */     for (EmojiEntry entry : this.emojis) {
/* 54 */       if (entry.name().equalsIgnoreCase(name)) return entry; 
/*    */     } 
/* 56 */     return null;
/*    */   }
/*    */   public static final class EmojiEntry extends Record { private final String name; private final String font; private final String key;
/*    */     public final String toString() {
/*    */       // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/emoji/data/EmojiData$EmojiEntry;)Ljava/lang/String;
/*    */       //   6: areturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #66	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	7	0	this	Lcom/atlas/common/fabric/emoji/data/EmojiData$EmojiEntry;
/*    */     }
/*    */     public final int hashCode() {
/*    */       // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/emoji/data/EmojiData$EmojiEntry;)I
/*    */       //   6: ireturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #66	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	7	0	this	Lcom/atlas/common/fabric/emoji/data/EmojiData$EmojiEntry;
/*    */     }
/*    */     
/* 66 */     public EmojiEntry(String name, String font, String key) { this.name = name; this.font = font; this.key = key; } public String name() { return this.name; } public final boolean equals(Object o) { // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: aload_1
/*    */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/emoji/data/EmojiData$EmojiEntry;Ljava/lang/Object;)Z
/*    */       //   7: ireturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #66	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	8	0	this	Lcom/atlas/common/fabric/emoji/data/EmojiData$EmojiEntry;
/* 66 */       //   0	8	1	o	Ljava/lang/Object; } public String font() { return this.font; } public String key() { return this.key; }
/*    */      }
/*    */ 
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\emoji\data\EmojiData.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */