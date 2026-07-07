/*     */ package com.atlas.common.fabric.auction.data;
/*     */ 
/*     */ import com.atlas.common.fabric.auction.network.AuctionNetwork;
/*     */ import java.io.IOException;
/*     */ import java.nio.file.Files;
/*     */ import java.nio.file.Path;
/*     */ import java.nio.file.attribute.FileAttribute;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import java.util.Set;
/*     */ import java.util.stream.Collector;
/*     */ import java.util.stream.Collectors;
/*     */ import net.minecraft.class_1799;
/*     */ import net.minecraft.class_310;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AuctionClientData
/*     */ {
/*  25 */   private static final AuctionClientData INSTANCE = new AuctionClientData();
/*     */   
/*  27 */   private List<AuctionNetwork.AuctionListingEntry> listings = new ArrayList<>();
/*  28 */   private int totalPages = 1;
/*  29 */   private int currentPage = 1;
/*  30 */   private double balance = 0.0D;
/*     */   
/*     */   private boolean loading = false;
/*     */   
/*     */   private AuctionNetwork.AuctionListingEntry selectedListing;
/*  35 */   private List<AuctionNetwork.SaleHistoryEntry> saleHistory = new ArrayList<>();
/*  36 */   private List<AuctionNetwork.BidHistoryEntry> bidHistory = new ArrayList<>();
/*  37 */   private List<AuctionNetwork.PricePoint> priceHistory = new ArrayList<>();
/*     */   
/*     */   private boolean detailLoading = false;
/*     */   
/*  41 */   private List<AuctionNetwork.RecentlySoldEntry> recentlySoldEntries = new ArrayList<>();
/*  42 */   private int recentlySoldTotalPages = 1;
/*  43 */   private int recentlySoldCurrentPage = 1;
/*     */   
/*     */   private boolean recentlySoldLoading = false;
/*     */   
/*  47 */   private List<AuctionNetwork.PriceLookupKeyEntry> priceLookupResults = new ArrayList<>();
/*  48 */   private List<AuctionNetwork.SaleHistoryEntry> priceLookupSaleHistory = new ArrayList<>();
/*  49 */   private List<AuctionNetwork.PricePoint> priceLookupPriceHistory = new ArrayList<>();
/*     */   
/*     */   private boolean priceLookupKeysLoading = false;
/*     */   
/*     */   private boolean priceLookupLoading = false;
/*     */   private boolean bidPending = false;
/*  55 */   private Boolean lastBidSuccess = null;
/*  56 */   private String lastBidError = "";
/*  57 */   private long lastBidResultTime = 0L;
/*     */   
/*     */   public static AuctionClientData getInstance() {
/*  60 */     return INSTANCE;
/*     */   }
/*     */   
/*     */   public void updateFromSync(AuctionNetwork.SyncAuctionPagePayload data) {
/*  64 */     this.listings = new ArrayList<>(data.listings());
/*  65 */     this.totalPages = data.totalPages();
/*  66 */     this.currentPage = data.currentPage();
/*  67 */     this.balance = data.balance();
/*  68 */     this.loading = false;
/*     */   }
/*     */   
/*     */   public void updateDetailFromSync(AuctionNetwork.SyncAuctionDetailPayload data) {
/*  72 */     this.saleHistory = new ArrayList<>(data.saleHistory());
/*  73 */     this.bidHistory = new ArrayList<>(data.bidHistory());
/*  74 */     this.priceHistory = new ArrayList<>(data.priceHistory());
/*  75 */     this.detailLoading = false;
/*     */   }
/*     */   
/*     */   public void addBidUpdate(AuctionNetwork.SyncBidUpdatePayload update) {
/*  79 */     this.bidHistory.add(0, new AuctionNetwork.BidHistoryEntry(update.timestamp(), update.bidderName(), update.bidderUuid(), update.amount()));
/*     */     
/*  81 */     if (this.selectedListing != null && this.selectedListing.auctionId().equals(update.auctionId())) {
/*  82 */       this
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
/*  94 */         .selectedListing = new AuctionNetwork.AuctionListingEntry(this.selectedListing.auctionId(), this.selectedListing.itemName(), this.selectedListing.sellerName(), update.amount(), this.selectedListing.auctionType(), this.selectedListing.itemType(), this.selectedListing.remainingSeconds(), this.selectedListing.itemCount(), this.selectedListing.displayStack(), this.selectedListing.speciesId(), update.expiresAt(), this.selectedListing.ivPercentage());
/*     */     }
/*     */   }
/*     */   
/*     */   public void setBalance(double balance) {
/*  99 */     this.balance = balance;
/*     */   }
/* 101 */   public List<AuctionNetwork.AuctionListingEntry> getListings() { return this.listings; }
/* 102 */   public int getTotalPages() { return this.totalPages; }
/* 103 */   public int getCurrentPage() { return this.currentPage; }
/* 104 */   public double getBalance() { return this.balance; }
/* 105 */   public boolean isLoading() { return this.loading; } public void setLoading(boolean loading) {
/* 106 */     this.loading = loading;
/*     */   }
/*     */   
/* 109 */   public AuctionNetwork.AuctionListingEntry getSelectedListing() { return this.selectedListing; }
/* 110 */   public void setSelectedListing(AuctionNetwork.AuctionListingEntry listing) { this.selectedListing = listing; }
/* 111 */   public List<AuctionNetwork.SaleHistoryEntry> getSaleHistory() { return this.saleHistory; }
/* 112 */   public List<AuctionNetwork.BidHistoryEntry> getBidHistory() { return this.bidHistory; }
/* 113 */   public List<AuctionNetwork.PricePoint> getPriceHistory() { return this.priceHistory; }
/* 114 */   public boolean isDetailLoading() { return this.detailLoading; } public void setDetailLoading(boolean detailLoading) {
/* 115 */     this.detailLoading = detailLoading;
/*     */   }
/*     */   
/*     */   public void setBidPending(boolean pending) {
/* 119 */     this.bidPending = pending;
/* 120 */     if (pending) {
/* 121 */       this.lastBidSuccess = null;
/* 122 */       this.lastBidError = "";
/*     */     } 
/*     */   } public boolean isBidPending() {
/* 125 */     return this.bidPending;
/*     */   }
/*     */   public void handleBidResult(AuctionNetwork.SyncBidResultPayload result) {
/* 128 */     this.bidPending = false;
/* 129 */     this.lastBidSuccess = Boolean.valueOf(result.success());
/* 130 */     this.lastBidError = (result.errorMessage() != null) ? result.errorMessage() : "";
/* 131 */     this.lastBidResultTime = System.currentTimeMillis();
/*     */   }
/*     */   
/* 134 */   public Boolean getLastBidSuccess() { return this.lastBidSuccess; }
/* 135 */   public String getLastBidError() { return this.lastBidError; }
/* 136 */   public long getLastBidResultTime() { return this.lastBidResultTime; } public void clearBidResult() {
/* 137 */     this.lastBidSuccess = null; this.lastBidError = "";
/*     */   }
/*     */   
/* 140 */   private List<AuctionNetwork.MyListingEntry> myListings = new ArrayList<>();
/* 141 */   private List<AuctionNetwork.ActiveBidEntry> myActiveBids = new ArrayList<>();
/* 142 */   private int maxSlots = 0;
/* 143 */   private int usedSlots = 0;
/*     */   
/*     */   private boolean myAuctionsLoading = false;
/*     */   
/* 147 */   private List<AuctionNetwork.HistoryEntry> historyEntries = new ArrayList<>();
/* 148 */   private int historyTotalPages = 1;
/* 149 */   private int historyCurrentPage = 1;
/*     */   
/*     */   private boolean historyLoading = false;
/*     */   
/* 153 */   private Boolean lastCreateSuccess = null;
/* 154 */   private String lastCreateMessage = "";
/* 155 */   private long lastCreateResultTime = 0L;
/*     */ 
/*     */   
/* 158 */   private Boolean lastUnlistSuccess = null;
/* 159 */   private String lastUnlistMessage = "";
/* 160 */   private long lastUnlistResultTime = 0L;
/*     */ 
/*     */   
/* 163 */   private final Set<String> pinnedIds = new HashSet<>();
/*     */   private boolean pinsLoaded = false;
/*     */   
/*     */   private Path getPinsFile() {
/* 167 */     return (class_310.method_1551()).field_1697.toPath()
/* 168 */       .resolve("config").resolve("atlas_auction_pins.txt");
/*     */   }
/*     */   
/*     */   private void loadPinsIfNeeded() {
/* 172 */     if (this.pinsLoaded)
/* 173 */       return;  this.pinsLoaded = true;
/*     */     try {
/* 175 */       Path file = getPinsFile();
/* 176 */       if (Files.exists(file, new java.nio.file.LinkOption[0])) {
/*     */ 
/*     */         
/* 179 */         Objects.requireNonNull(this.pinnedIds); Files.readAllLines(file).stream().map(String::trim).filter(s -> !s.isEmpty()).forEach(this.pinnedIds::add);
/*     */       } 
/* 181 */     } catch (IOException iOException) {}
/*     */   }
/*     */   
/*     */   private void savePins() {
/*     */     try {
/* 186 */       Path file = getPinsFile();
/* 187 */       Files.createDirectories(file.getParent(), (FileAttribute<?>[])new FileAttribute[0]);
/* 188 */       Files.writeString(file, this.pinnedIds.stream().collect((Collector)Collectors.joining("\n")), new java.nio.file.OpenOption[0]);
/* 189 */     } catch (IOException iOException) {}
/*     */   }
/*     */   
/*     */   public void togglePin(String auctionId) {
/* 193 */     loadPinsIfNeeded();
/* 194 */     if (!this.pinnedIds.remove(auctionId)) this.pinnedIds.add(auctionId); 
/* 195 */     savePins();
/*     */   }
/*     */   
/*     */   public boolean isPinned(String auctionId) {
/* 199 */     loadPinsIfNeeded();
/* 200 */     return this.pinnedIds.contains(auctionId);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<AuctionNetwork.AuctionListingEntry> getPinnedFirst(List<AuctionNetwork.AuctionListingEntry> listings) {
/* 205 */     loadPinsIfNeeded();
/* 206 */     if (this.pinnedIds.isEmpty()) return listings; 
/* 207 */     ArrayList<AuctionNetwork.AuctionListingEntry> pinned = new ArrayList<>();
/* 208 */     ArrayList<AuctionNetwork.AuctionListingEntry> rest = new ArrayList<>();
/* 209 */     for (AuctionNetwork.AuctionListingEntry l : listings) {
/* 210 */       (this.pinnedIds.contains(l.auctionId()) ? pinned : rest).add(l);
/*     */     }
/* 212 */     pinned.addAll(rest);
/* 213 */     return pinned;
/*     */   }
/*     */ 
/*     */   
/* 217 */   private List<class_1799> inventoryItems = new ArrayList<>();
/* 218 */   private int pcBoxCount = 1;
/* 219 */   private List<String> pcPokemonUuids = new ArrayList<>();
/* 220 */   private List<String> pcPokemonNames = new ArrayList<>();
/* 221 */   private List<class_1799> pcPokemonDisplayStacks = new ArrayList<>();
/*     */   
/*     */   public void updateMyAuctionsFromSync(AuctionNetwork.SyncMyAuctionsPayload data) {
/* 224 */     this.myListings = new ArrayList<>(data.listings());
/* 225 */     this.myActiveBids = new ArrayList<>(data.activeBids());
/* 226 */     this.maxSlots = data.maxSlots();
/* 227 */     this.usedSlots = data.usedSlots();
/* 228 */     this.balance = data.balance();
/* 229 */     this.myAuctionsLoading = false;
/*     */   }
/*     */   
/*     */   public void updateHistoryFromSync(AuctionNetwork.SyncAuctionHistoryPayload data) {
/* 233 */     this.historyEntries = new ArrayList<>(data.entries());
/* 234 */     this.historyTotalPages = data.totalPages();
/* 235 */     this.historyCurrentPage = data.currentPage();
/* 236 */     this.historyLoading = false;
/*     */   }
/*     */   
/*     */   public void handleCreateResult(AuctionNetwork.SyncCreateResultPayload data) {
/* 240 */     this.lastCreateSuccess = Boolean.valueOf(data.success());
/* 241 */     this.lastCreateMessage = (data.message() != null) ? data.message() : "";
/* 242 */     this.lastCreateResultTime = System.currentTimeMillis();
/*     */   }
/*     */   
/*     */   public void handleUnlistResult(AuctionNetwork.SyncUnlistResultPayload data) {
/* 246 */     this.lastUnlistSuccess = Boolean.valueOf(data.success());
/* 247 */     this.lastUnlistMessage = (data.message() != null) ? data.message() : "";
/* 248 */     this.lastUnlistResultTime = System.currentTimeMillis();
/*     */   }
/*     */   
/*     */   public void updateInventoryFromSync(AuctionNetwork.SyncInventoryPayload data) {
/* 252 */     this.inventoryItems = new ArrayList<>(data.items());
/* 253 */     this.pcBoxCount = Math.max(1, data.pcBoxCount());
/* 254 */     this.pcPokemonUuids = new ArrayList<>(data.pokemonUuids());
/* 255 */     this.pcPokemonNames = new ArrayList<>(data.pokemonNames());
/* 256 */     this.pcPokemonDisplayStacks = new ArrayList<>(data.pokemonDisplayStacks());
/*     */   }
/*     */   
/*     */   public List<AuctionNetwork.MyListingEntry> getMyListings() {
/* 260 */     return this.myListings;
/* 261 */   } public List<AuctionNetwork.ActiveBidEntry> getMyActiveBids() { return this.myActiveBids; }
/* 262 */   public int getMaxSlots() { return this.maxSlots; }
/* 263 */   public int getUsedSlots() { return this.usedSlots; }
/* 264 */   public boolean isMyAuctionsLoading() { return this.myAuctionsLoading; } public void setMyAuctionsLoading(boolean loading) {
/* 265 */     this.myAuctionsLoading = loading;
/*     */   }
/*     */   
/* 268 */   public List<AuctionNetwork.HistoryEntry> getHistoryEntries() { return this.historyEntries; }
/* 269 */   public int getHistoryTotalPages() { return this.historyTotalPages; }
/* 270 */   public int getHistoryCurrentPage() { return this.historyCurrentPage; }
/* 271 */   public boolean isHistoryLoading() { return this.historyLoading; } public void setHistoryLoading(boolean loading) {
/* 272 */     this.historyLoading = loading;
/*     */   }
/*     */   
/* 275 */   public Boolean getLastCreateSuccess() { return this.lastCreateSuccess; }
/* 276 */   public String getLastCreateMessage() { return this.lastCreateMessage; }
/* 277 */   public long getLastCreateResultTime() { return this.lastCreateResultTime; } public void clearCreateResult() {
/* 278 */     this.lastCreateSuccess = null; this.lastCreateMessage = "";
/*     */   }
/*     */   
/* 281 */   public Boolean getLastUnlistSuccess() { return this.lastUnlistSuccess; }
/* 282 */   public String getLastUnlistMessage() { return this.lastUnlistMessage; }
/* 283 */   public long getLastUnlistResultTime() { return this.lastUnlistResultTime; } public void clearUnlistResult() {
/* 284 */     this.lastUnlistSuccess = null; this.lastUnlistMessage = "";
/*     */   }
/*     */   
/* 287 */   public List<class_1799> getInventoryItems() { return this.inventoryItems; }
/* 288 */   public int getPcBoxCount() { return this.pcBoxCount; }
/* 289 */   public List<String> getPcPokemonUuids() { return this.pcPokemonUuids; }
/* 290 */   public List<String> getPcPokemonNames() { return this.pcPokemonNames; } public List<class_1799> getPcPokemonDisplayStacks() {
/* 291 */     return this.pcPokemonDisplayStacks;
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateRecentlySoldFromSync(AuctionNetwork.SyncRecentlySoldPayload data) {
/* 296 */     this.recentlySoldEntries = new ArrayList<>(data.entries());
/* 297 */     this.recentlySoldTotalPages = data.totalPages();
/* 298 */     this.recentlySoldCurrentPage = data.currentPage();
/* 299 */     this.recentlySoldLoading = false;
/*     */   }
/*     */   
/* 302 */   public List<AuctionNetwork.RecentlySoldEntry> getRecentlySoldEntries() { return this.recentlySoldEntries; }
/* 303 */   public int getRecentlySoldTotalPages() { return this.recentlySoldTotalPages; }
/* 304 */   public int getRecentlySoldCurrentPage() { return this.recentlySoldCurrentPage; }
/* 305 */   public boolean isRecentlySoldLoading() { return this.recentlySoldLoading; } public void setRecentlySoldLoading(boolean loading) {
/* 306 */     this.recentlySoldLoading = loading;
/*     */   }
/*     */ 
/*     */   
/*     */   public void updatePriceLookupKeysFromSync(AuctionNetwork.SyncPriceLookupKeysPayload data) {
/* 311 */     this.priceLookupResults = new ArrayList<>(data.results());
/* 312 */     this.priceLookupKeysLoading = false;
/*     */   }
/*     */   
/*     */   public void updatePriceLookupFromSync(AuctionNetwork.SyncPriceLookupPayload data) {
/* 316 */     this.priceLookupSaleHistory = new ArrayList<>(data.saleHistory());
/* 317 */     this.priceLookupPriceHistory = new ArrayList<>(data.priceHistory());
/* 318 */     this.priceLookupLoading = false;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 323 */     if (this.selectedListing != null && (this.selectedListing.displayStack() == null || this.selectedListing.displayStack().method_7960()) && 
/* 324 */       !this.priceLookupSaleHistory.isEmpty()) {
/* 325 */       AuctionNetwork.SaleHistoryEntry firstSale = this.priceLookupSaleHistory.get(0);
/* 326 */       if (firstSale.displayStack() != null && !firstSale.displayStack().method_7960()) {
/* 327 */         this
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
/* 339 */           .selectedListing = new AuctionNetwork.AuctionListingEntry(this.selectedListing.auctionId(), this.selectedListing.itemName(), this.selectedListing.sellerName(), this.selectedListing.price(), this.selectedListing.auctionType(), this.selectedListing.itemType(), this.selectedListing.remainingSeconds(), this.selectedListing.itemCount(), firstSale.displayStack(), this.selectedListing.speciesId(), this.selectedListing.expiresAt(), this.selectedListing.ivPercentage());
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public List<AuctionNetwork.PriceLookupKeyEntry> getPriceLookupResults() {
/* 346 */     return this.priceLookupResults;
/* 347 */   } public List<AuctionNetwork.SaleHistoryEntry> getPriceLookupSaleHistory() { return this.priceLookupSaleHistory; }
/* 348 */   public List<AuctionNetwork.PricePoint> getPriceLookupPriceHistory() { return this.priceLookupPriceHistory; }
/* 349 */   public boolean isPriceLookupKeysLoading() { return this.priceLookupKeysLoading; }
/* 350 */   public boolean isPriceLookupLoading() { return this.priceLookupLoading; }
/* 351 */   public void setPriceLookupKeysLoading(boolean loading) { this.priceLookupKeysLoading = loading; } public void setPriceLookupLoading(boolean loading) {
/* 352 */     this.priceLookupLoading = loading;
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\auction\data\AuctionClientData.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */