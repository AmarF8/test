/*    */ package com.atlas.common.fabric.cardcollection.client;
/*    */ 
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
/*    */ public final class CardCollectionClientData
/*    */ {
/* 18 */   private static final CardCollectionClientData INSTANCE = new CardCollectionClientData();
/*    */ 
/*    */   
/*    */   public static CardCollectionClientData getInstance() {
/* 22 */     return INSTANCE;
/*    */   }
/*    */   
/* 25 */   private String overviewJson = "";
/* 26 */   private CardCollectionDto.Overview overview = new CardCollectionDto.Overview(List.of());
/*    */   
/*    */   public void setOverviewJson(String json) {
/* 29 */     this.overviewJson = (json == null) ? "" : json;
/* 30 */     this.overview = CardCollectionDto.parseOverview(this.overviewJson);
/*    */   }
/*    */   public CardCollectionDto.Overview getOverview() {
/* 33 */     return this.overview;
/*    */   } public boolean hasOverview() {
/* 35 */     return !this.overviewJson.isBlank();
/*    */   }
/*    */   
/* 38 */   private String binderJson = "";
/* 39 */   private CardCollectionDto.BinderData binder = null;
/*    */   
/*    */   public void setBinderJson(String json) {
/* 42 */     this.binderJson = (json == null) ? "" : json;
/* 43 */     this.binder = CardCollectionDto.parseBinder(this.binderJson);
/*    */   }
/*    */   public CardCollectionDto.BinderData getBinder() {
/* 46 */     return this.binder;
/*    */   }
/*    */   
/* 49 */   private String packJson = "";
/* 50 */   private CardCollectionDto.PackData pack = null;
/*    */   
/*    */   public void setPackJson(String json) {
/* 53 */     this.packJson = (json == null) ? "" : json;
/* 54 */     this.pack = CardCollectionDto.parsePack(this.packJson);
/*    */   }
/*    */   public CardCollectionDto.PackData getPack() {
/* 57 */     return this.pack;
/*    */   }
/*    */   
/* 60 */   private String skillTreeJson = "";
/* 61 */   private CardCollectionDto.SkillTreeData skillTree = new CardCollectionDto.SkillTreeData(0, 0, 0, List.of());
/*    */   
/*    */   public void setSkillTreeJson(String json) {
/* 64 */     this.skillTreeJson = (json == null) ? "" : json;
/* 65 */     this.skillTree = CardCollectionDto.parseSkillTree(this.skillTreeJson);
/*    */   }
/*    */   public CardCollectionDto.SkillTreeData getSkillTree() {
/* 68 */     return this.skillTree;
/*    */   }
/*    */   public void clearBinder() {
/* 71 */     this.binderJson = ""; this.binder = null;
/*    */   } public void clearPack() {
/* 73 */     this.packJson = ""; this.pack = null;
/*    */   } public void clearSkillTree() {
/* 75 */     this.skillTreeJson = ""; this.skillTree = new CardCollectionDto.SkillTreeData(0, 0, 0, List.of());
/*    */   }
/*    */   public void clearAll() {
/* 78 */     this.overviewJson = ""; this.overview = new CardCollectionDto.Overview(List.of());
/* 79 */     clearBinder();
/* 80 */     clearPack();
/* 81 */     clearSkillTree();
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\cardcollection\client\CardCollectionClientData.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */