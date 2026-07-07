/*     */ package com.atlas.common.fabric.auction.client;
/*     */ 
/*     */ import com.atlas.common.fabric.auction.data.AuctionClientData;
/*     */ import com.atlas.common.fabric.auction.network.AuctionNetwork;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import net.fabricmc.api.EnvType;
/*     */ import net.fabricmc.api.Environment;
/*     */ import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
/*     */ import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
/*     */ import net.minecraft.class_1799;
/*     */ import net.minecraft.class_2561;
/*     */ import net.minecraft.class_304;
/*     */ import net.minecraft.class_310;
/*     */ import net.minecraft.class_3675;
/*     */ 
/*     */ 
/*     */ 
/*     */ @Environment(EnvType.CLIENT)
/*     */ public final class AuctionTestKeybinds
/*     */ {
/*     */   private static final String CATEGORY = "category.atlas.auction_test";
/*     */   private static final double DEFAULT_TEST_PRICE = 3000000.0D;
/*     */   private static final double DEFAULT_BID_PRICE = 1000.0D;
/*     */   private static final String DEFAULT_DURATION = "10m";
/*     */   private static class_304 summarizePageKey;
/*     */   private static class_304 heldUndercutKey;
/*     */   private static class_304 listHeldBidKey;
/*     */   private static class_304 listHeldBackKey;
/*     */   private static class_304 listHeldHomeKey;
/*  32 */   private static int backListDelayTicks = -1;
/*  33 */   private static int backUnlistDelayTicks = -1;
/*  34 */   private static int homeListDelayTicks = -1;
/*  35 */   private static int homeUnlistDelayTicks = -1;
/*  36 */   private static final Set<String> backBeforeListingIds = new HashSet<>();
/*  37 */   private static final Set<String> homeBeforeListingIds = new HashSet<>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void init() {
/*  43 */     summarizePageKey = register("key.atlas.auction_test.summarize_page", 295);
/*  44 */     heldUndercutKey = register("key.atlas.auction_test.list_held_undercut", 296);
/*  45 */     listHeldBidKey = register("key.atlas.auction_test.list_held_bid", 297);
/*  46 */     listHeldBackKey = register("key.atlas.auction_test.list_held_back", 298);
/*  47 */     listHeldHomeKey = register("key.atlas.auction_test.list_held_home", 299);
/*     */     
/*  49 */     ClientTickEvents.END_CLIENT_TICK.register(AuctionTestKeybinds::tick);
/*     */   }
/*     */   
/*     */   private static class_304 register(String translationKey, int keyCode) {
/*  53 */     return KeyBindingHelper.registerKeyBinding(new class_304(translationKey, class_3675.class_307.field_1668, keyCode, "category.atlas.auction_test"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void tick(class_310 client) {
/*  62 */     while (summarizePageKey.method_1436()) {
/*  63 */       summarizeCurrentPage(client);
/*     */     }
/*  65 */     while (heldUndercutKey.method_1436()) {
/*  66 */       listHeldUndercut(client, "10m");
/*     */     }
/*  68 */     while (listHeldBidKey.method_1436()) {
/*  69 */       listHeldDirect(client, 1000.0D, "10m", "BID");
/*     */     }
/*  71 */     while (listHeldBackKey.method_1436()) {
/*  72 */       startTeleportListTest(client, "back");
/*     */     }
/*  74 */     while (listHeldHomeKey.method_1436()) {
/*  75 */       startTeleportListTest(client, "home");
/*     */     }
/*     */     
/*  78 */     tickTeleportList(client, true);
/*  79 */     tickTeleportList(client, false);
/*     */   }
/*     */   
/*     */   private static void summarizeCurrentPage(class_310 client) {
/*  83 */     if (!isReady(client))
/*     */       return; 
/*  85 */     AuctionClientData data = AuctionClientData.getInstance();
/*  86 */     List<AuctionNetwork.AuctionListingEntry> listings = data.getListings();
/*  87 */     List<AuctionNetwork.MyListingEntry> myListings = data.getMyListings();
/*  88 */     client.field_1724.method_7353((class_2561)class_2561.method_43470("[Auction Test] Page " + data.getCurrentPage() + "/" + data
/*  89 */           .getTotalPages() + " has " + listings
/*  90 */           .size() + " listings. My listings cache has " + myListings
/*  91 */           .size() + "."), false);
/*     */     
/*  93 */     if (listings.isEmpty()) {
/*  94 */       AuctionNetwork.requestAuctionPage(1, "newest", "ALL", "");
/*  95 */       client.field_1724.method_7353((class_2561)class_2561.method_43470("[Auction Test] Requested auction page 1 because the cache was empty."), false);
/*     */     } 
/*  97 */     AuctionNetwork.requestMyAuctions();
/*     */   }
/*     */   
/*     */   private static void listHeldUndercut(class_310 client, String duration) {
/* 101 */     if (!isReady(client))
/*     */       return; 
/* 103 */     class_1799 held = client.field_1724.method_31548().method_5438((client.field_1724.method_31548()).field_7545);
/* 104 */     if (held.method_7960()) {
/* 105 */       client.field_1724.method_7353((class_2561)class_2561.method_43470("[Auction Test] Hold an item in the selected hotbar slot first."), false);
/*     */       
/*     */       return;
/*     */     } 
/* 109 */     Double cheapestMatch = findCheapestBuyNowMatch(held);
/* 110 */     if (cheapestMatch == null) {
/* 111 */       AuctionNetwork.requestAuctionPage(1, "price_asc", "ALL", held.method_7964().getString());
/* 112 */       client.field_1724.method_7353((class_2561)class_2561.method_43470("[Auction Test] No matching BUY_NOW listing in cache. Requested a price lookup page first."), false);
/*     */       
/*     */       return;
/*     */     } 
/* 116 */     double price = Math.max(1.0D, cheapestMatch.doubleValue() - 1.0D);
/* 117 */     listHeldDirect(client, price, duration, "BUY_NOW");
/*     */   }
/*     */   
/*     */   private static Double findCheapestBuyNowMatch(class_1799 held) {
/* 121 */     Double cheapest = null;
/* 122 */     for (AuctionNetwork.AuctionListingEntry entry : AuctionClientData.getInstance().getListings()) {
/* 123 */       if (!"BUY_NOW".equalsIgnoreCase(entry.auctionType()))
/* 124 */         continue;  class_1799 displayStack = entry.displayStack();
/* 125 */       if (displayStack == null || displayStack.method_7960() || 
/* 126 */         !class_1799.method_31577(held, displayStack))
/*     */         continue; 
/* 128 */       if (cheapest == null || entry.price() < cheapest.doubleValue()) {
/* 129 */         cheapest = Double.valueOf(entry.price());
/*     */       }
/*     */     } 
/* 132 */     return cheapest;
/*     */   }
/*     */   
/*     */   private static void listHeldDirect(class_310 client, double price, String duration, String auctionType) {
/* 136 */     if (!isReady(client))
/*     */       return; 
/* 138 */     int slot = (client.field_1724.method_31548()).field_7545;
/* 139 */     class_1799 held = client.field_1724.method_31548().method_5438(slot);
/* 140 */     if (held.method_7960()) {
/* 141 */       client.field_1724.method_7353((class_2561)class_2561.method_43470("[Auction Test] Hold an item in the selected hotbar slot first."), false);
/*     */       
/*     */       return;
/*     */     } 
/* 145 */     AuctionNetwork.requestCreateListing(slot, price, duration, auctionType);
/* 146 */     client.field_1724.method_7353((class_2561)class_2561.method_43470("[Auction Test] Listed selected slot " + slot + " as " + auctionType + " for " + 
/* 147 */           formatPrice(price) + " / " + duration + "."), false);
/*     */   }
/*     */   
/*     */   private static void startTeleportListTest(class_310 client, String command) {
/* 151 */     if (!isReady(client))
/*     */       return; 
/* 153 */     client.method_1562().method_45731(command);
/* 154 */     if ("back".equals(command)) {
/* 155 */       backListDelayTicks = 3;
/* 156 */       backUnlistDelayTicks = -1;
/* 157 */       backBeforeListingIds.clear();
/* 158 */       snapshotMyListingIds(backBeforeListingIds);
/*     */     } else {
/* 160 */       homeListDelayTicks = 3;
/* 161 */       homeUnlistDelayTicks = -1;
/* 162 */       homeBeforeListingIds.clear();
/* 163 */       snapshotMyListingIds(homeBeforeListingIds);
/*     */     } 
/* 165 */     client.field_1724.method_7353((class_2561)class_2561.method_43470("[Auction Test] Sent /" + command + ", will list selected slot in 3 ticks."), false);
/*     */   }
/*     */   
/*     */   private static void tickTeleportList(class_310 client, boolean back) {
/* 169 */     if (back) {
/* 170 */       if (backListDelayTicks > 0 && --backListDelayTicks == 0) {
/* 171 */         createDelayedTeleportListing(client, true);
/*     */       }
/* 173 */       if (backUnlistDelayTicks > 0) {
/* 174 */         backUnlistDelayTicks--;
/* 175 */         tickDelayedUnlist(client, backBeforeListingIds, backUnlistDelayTicks);
/*     */       } 
/*     */     } else {
/* 178 */       if (homeListDelayTicks > 0 && --homeListDelayTicks == 0) {
/* 179 */         createDelayedTeleportListing(client, false);
/*     */       }
/* 181 */       if (homeUnlistDelayTicks > 0) {
/* 182 */         homeUnlistDelayTicks--;
/* 183 */         tickDelayedUnlist(client, homeBeforeListingIds, homeUnlistDelayTicks);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void createDelayedTeleportListing(class_310 client, boolean back) {
/* 189 */     if (!isReady(client))
/*     */       return; 
/* 191 */     int slot = (client.field_1724.method_31548()).field_7545;
/*     */     try {
/* 193 */       AuctionNetwork.requestCreateListing(slot, 3000000.0D, "10m", "BUY_NOW");
/* 194 */       if (back) {
/* 195 */         backUnlistDelayTicks = 20;
/*     */       } else {
/* 197 */         homeUnlistDelayTicks = 20;
/*     */       } 
/* 199 */       client.field_1724.method_7353((class_2561)class_2561.method_43470("[Auction Test] Sent delayed listing request for selected slot " + slot + "."), false);
/* 200 */     } catch (Exception exception) {
/* 201 */       client.field_1724.method_7353((class_2561)class_2561.method_43470("[Auction Test] Failed to request delayed listing: " + exception.getMessage()), false);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void tickDelayedUnlist(class_310 client, Set<String> beforeListingIds, int remainingTicks) {
/* 206 */     if (!isReady(client))
/*     */       return; 
/* 208 */     if (remainingTicks == 10) {
/* 209 */       AuctionNetwork.requestMyAuctions();
/*     */     }
/* 211 */     if (remainingTicks != 0) {
/*     */       return;
/*     */     }
/*     */     
/* 215 */     String targetAuctionId = null;
/* 216 */     for (AuctionNetwork.MyListingEntry entry : AuctionClientData.getInstance().getMyListings()) {
/* 217 */       if (!beforeListingIds.contains(entry.auctionId())) {
/* 218 */         targetAuctionId = entry.auctionId();
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 223 */     if (targetAuctionId != null) {
/* 224 */       AuctionNetwork.requestUnlist(targetAuctionId);
/* 225 */       client.field_1724.method_7353((class_2561)class_2561.method_43470("[Auction Test] Auto-unlisted item " + targetAuctionId + " 20 ticks after listing."), false);
/*     */     } else {
/*     */       
/* 228 */       client.field_1724.method_7353((class_2561)class_2561.method_43470("[Auction Test] No new listing found to auto-unlist."), false);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void snapshotMyListingIds(Set<String> target) {
/* 233 */     for (AuctionNetwork.MyListingEntry entry : AuctionClientData.getInstance().getMyListings()) {
/* 234 */       target.add(entry.auctionId());
/*     */     }
/*     */   }
/*     */   
/*     */   private static boolean isReady(class_310 client) {
/* 239 */     return (client.field_1724 != null && client.method_1562() != null);
/*     */   }
/*     */   
/*     */   private static String formatPrice(double price) {
/* 243 */     if (price == Math.rint(price)) {
/* 244 */       return Long.toString((long)price);
/*     */     }
/* 246 */     return Double.toString(price);
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\auction\client\AuctionTestKeybinds.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */