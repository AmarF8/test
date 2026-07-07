/*    */ package com.atlas.common.fabric.guide.screen.views;
/*    */ 
/*    */ import com.atlas.common.fabric.guide.data.SearchResult;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
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
/*    */ class ChatMessage
/*    */ {
/*    */   final boolean isUser;
/*    */   final String text;
/*    */   final List<SearchResult> results;
/*    */   final long timestamp;
/*    */   final String originalQuery;
/*    */   boolean feedbackSubmitted = false;
/* 75 */   String intent = null;
/*    */   
/*    */   ChatMessage(boolean isUser, String text, List<SearchResult> results) {
/* 78 */     this(isUser, text, results, null);
/*    */   }
/*    */   
/*    */   ChatMessage(boolean isUser, String text, List<SearchResult> results, String originalQuery) {
/* 82 */     this.isUser = isUser;
/* 83 */     this.text = text;
/* 84 */     this.results = (results != null) ? results : Collections.<SearchResult>emptyList();
/* 85 */     this.timestamp = System.currentTimeMillis();
/* 86 */     this.originalQuery = originalQuery;
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\guide\screen\views\AskAiView$ChatMessage.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */