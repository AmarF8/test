/*    */ package com.atlas.common.fabric.shop.data;
/*    */ 
/*    */ import com.atlas.common.fabric.shop.network.ShopNetwork;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ShopClientData
/*    */ {
/* 12 */   private static final ShopClientData INSTANCE = new ShopClientData();
/*    */   
/* 14 */   private List<ShopNetwork.ShopCategoryEntry> categories = new ArrayList<>();
/* 15 */   private String selectedCategoryId = "";
/* 16 */   private List<ShopNetwork.ShopItemEntry> currentItems = new ArrayList<>();
/* 17 */   private int currentPage = 1;
/* 18 */   private int totalPages = 1;
/* 19 */   private double balance = 0.0D;
/*    */   
/*    */   private boolean loading = false;
/*    */   
/* 23 */   private Boolean lastPurchaseSuccess = null;
/* 24 */   private String lastPurchaseMessage = "";
/* 25 */   private long lastPurchaseResultTime = 0L;
/*    */   
/*    */   public static ShopClientData getInstance() {
/* 28 */     return INSTANCE;
/*    */   }
/*    */   
/*    */   public void updateCategories(ShopNetwork.SyncCategoriesPayload data) {
/* 32 */     this.categories = new ArrayList<>(data.categories());
/* 33 */     if (!this.categories.isEmpty() && this.selectedCategoryId.isEmpty()) {
/* 34 */       this.selectedCategoryId = ((ShopNetwork.ShopCategoryEntry)this.categories.get(0)).id();
/*    */     }
/*    */   }
/*    */   
/*    */   public void updateItems(ShopNetwork.SyncShopItemsPayload data) {
/* 39 */     this.selectedCategoryId = data.categoryId();
/* 40 */     this.currentItems = new ArrayList<>(data.items());
/* 41 */     this.currentPage = data.page();
/* 42 */     this.totalPages = data.totalPages();
/* 43 */     this.loading = false;
/*    */   }
/*    */   
/*    */   public void setBalance(double balance) {
/* 47 */     this.balance = balance;
/*    */   }
/*    */   
/*    */   public void handlePurchaseResult(ShopNetwork.ShopPurchaseResultPayload data) {
/* 51 */     this.lastPurchaseSuccess = Boolean.valueOf(data.success());
/* 52 */     this.lastPurchaseMessage = (data.message() != null) ? data.message() : "";
/* 53 */     this.lastPurchaseResultTime = System.currentTimeMillis();
/* 54 */     this.balance = data.newBalance();
/*    */   }
/*    */ 
/*    */   
/*    */   public List<ShopNetwork.ShopCategoryEntry> getCategories() {
/* 59 */     return this.categories; }
/* 60 */   public String getSelectedCategoryId() { return this.selectedCategoryId; }
/* 61 */   public void setSelectedCategoryId(String id) { this.selectedCategoryId = id; }
/* 62 */   public List<ShopNetwork.ShopItemEntry> getCurrentItems() { return this.currentItems; }
/* 63 */   public int getCurrentPage() { return this.currentPage; }
/* 64 */   public int getTotalPages() { return this.totalPages; }
/* 65 */   public double getBalance() { return this.balance; }
/* 66 */   public boolean isLoading() { return this.loading; } public void setLoading(boolean loading) {
/* 67 */     this.loading = loading;
/*    */   }
/* 69 */   public Boolean getLastPurchaseSuccess() { return this.lastPurchaseSuccess; }
/* 70 */   public String getLastPurchaseMessage() { return this.lastPurchaseMessage; }
/* 71 */   public long getLastPurchaseResultTime() { return this.lastPurchaseResultTime; } public void clearPurchaseResult() {
/* 72 */     this.lastPurchaseSuccess = null; this.lastPurchaseMessage = "";
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\shop\data\ShopClientData.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */