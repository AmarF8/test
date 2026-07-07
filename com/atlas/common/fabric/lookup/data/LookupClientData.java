/*     */ package com.atlas.common.fabric.lookup.data;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class LookupClientData
/*     */ {
/*  13 */   private static final LookupClientData INSTANCE = new LookupClientData(); private String targetUuid;
/*     */   private String targetName;
/*     */   private String overviewJson;
/*     */   
/*     */   public static LookupClientData getInstance() {
/*  18 */     return INSTANCE;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean canEdit = false;
/*     */   
/*     */   private String pokemonJson;
/*     */   
/*     */   private int pokemonTotal;
/*     */   
/*     */   private int pokemonPage;
/*     */   
/*     */   private String myPokemonJson;
/*     */   
/*     */   private String inventoryJson;
/*     */   
/*     */   private String vaultJson;
/*     */   
/*     */   private int vaultTotal;
/*     */   
/*     */   private int vaultPage;
/*     */   
/*     */   private String vaultStorageListJson;
/*     */   
/*     */   private String vaultStorageItemsJson;
/*     */   
/*     */   private int vaultStorageItemsPage;
/*     */   
/*     */   private String logsJson;
/*     */   
/*     */   private int logsTotal;
/*     */   
/*     */   private int logsPage;
/*     */   
/*     */   private String ledgerJson;
/*     */   
/*     */   private int ledgerTotal;
/*     */   
/*     */   private int ledgerPage;
/*     */   
/*     */   private String punishmentJson;
/*     */   
/*     */   private String notesJson;
/*     */   
/*     */   private String auditJson;
/*     */   
/*     */   private int auditTotal;
/*     */   
/*     */   private int auditPage;
/*     */   
/*     */   private String snapshotsJson;
/*     */   private int snapshotsTotal;
/*     */   private int snapshotsPage;
/*     */   private String snapshotDetailJson;
/*  72 */   private final List<Runnable> listeners = new ArrayList<>();
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
/*     */   public void setOverviewFromJson(String json) {
/*  85 */     String newUuid = extractJsonString(json, "uuid");
/*  86 */     String newName = extractJsonString(json, "name");
/*  87 */     if (newUuid != null && this.targetUuid != null && !newUuid.equals(this.targetUuid)) {
/*  88 */       resetPerTargetData();
/*     */     }
/*  90 */     this.overviewJson = json;
/*  91 */     this.targetUuid = newUuid;
/*  92 */     this.targetName = newName;
/*  93 */     this.canEdit = (json != null && json.contains("\"canEdit\":true"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetPerTargetData() {
/* 105 */     this.canEdit = false;
/* 106 */     this.pokemonJson = null;
/* 107 */     this.pokemonTotal = 0;
/* 108 */     this.pokemonPage = 0;
/*     */     
/* 110 */     this.inventoryJson = null;
/*     */     
/* 112 */     this.vaultJson = null;
/* 113 */     this.vaultTotal = 0;
/* 114 */     this.vaultPage = 0;
/* 115 */     this.vaultStorageListJson = null;
/* 116 */     this.vaultStorageItemsJson = null;
/* 117 */     this.vaultStorageItemsPage = 0;
/*     */     
/* 119 */     this.logsJson = null;
/* 120 */     this.logsTotal = 0;
/* 121 */     this.logsPage = 0;
/*     */     
/* 123 */     this.ledgerJson = null;
/* 124 */     this.ledgerTotal = 0;
/* 125 */     this.ledgerPage = 0;
/*     */     
/* 127 */     this.punishmentJson = null;
/*     */     
/* 129 */     this.notesJson = null;
/*     */     
/* 131 */     this.auditJson = null;
/* 132 */     this.auditTotal = 0;
/* 133 */     this.auditPage = 0;
/*     */     
/* 135 */     this.snapshotsJson = null;
/* 136 */     this.snapshotsTotal = 0;
/* 137 */     this.snapshotsPage = 0;
/*     */     
/* 139 */     this.snapshotDetailJson = null;
/*     */     
/* 141 */     this.listeners.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOverview(String uuid, String name, String json) {
/* 148 */     this.targetUuid = uuid;
/* 149 */     this.targetName = name;
/* 150 */     this.overviewJson = json;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleData(String dataType, String json, int totalCount, int page) {
/* 159 */     switch (dataType) {
/*     */       case "pokemon":
/* 161 */         this.pokemonJson = json;
/* 162 */         this.pokemonTotal = totalCount;
/* 163 */         this.pokemonPage = page;
/*     */         break;
/*     */       case "give_my_pokemon":
/* 166 */         this.myPokemonJson = json;
/*     */         break;
/*     */       case "inventory":
/* 169 */         this.inventoryJson = json;
/*     */         break;
/*     */       case "vault":
/* 172 */         this.vaultJson = json;
/* 173 */         this.vaultTotal = totalCount;
/* 174 */         this.vaultPage = page;
/*     */         break;
/*     */       case "vault_storage":
/* 177 */         this.vaultStorageListJson = json;
/*     */         break;
/*     */       case "vault_storage_items":
/* 180 */         this.vaultStorageItemsJson = json;
/* 181 */         this.vaultStorageItemsPage = page;
/*     */         break;
/*     */       case "logs":
/* 184 */         this.logsJson = json;
/* 185 */         this.logsTotal = totalCount;
/* 186 */         this.logsPage = page;
/*     */         break;
/*     */       case "ledger":
/* 189 */         this.ledgerJson = json;
/* 190 */         this.ledgerTotal = totalCount;
/* 191 */         this.ledgerPage = page;
/*     */         break;
/*     */       case "punishments":
/* 194 */         this.punishmentJson = json;
/*     */         break;
/*     */       case "notes":
/* 197 */         this.notesJson = json;
/*     */         break;
/*     */       case "audit":
/* 200 */         this.auditJson = json;
/* 201 */         this.auditTotal = totalCount;
/* 202 */         this.auditPage = page;
/*     */         break;
/*     */       case "snapshots":
/* 205 */         this.snapshotsJson = json;
/* 206 */         this.snapshotsTotal = totalCount;
/* 207 */         this.snapshotsPage = page;
/*     */         break;
/*     */ 
/*     */ 
/*     */       
/*     */       case "snapshot_detail":
/* 213 */         this.snapshotDetailJson = json;
/*     */         break;
/*     */     } 
/* 216 */     notifyListeners();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addListener(Runnable listener) {
/* 222 */     this.listeners.add(listener);
/*     */   }
/*     */   
/*     */   public void removeListener(Runnable listener) {
/* 226 */     this.listeners.remove(listener);
/*     */   }
/*     */   
/*     */   private void notifyListeners() {
/* 230 */     for (Runnable listener : this.listeners) {
/* 231 */       listener.run();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/* 241 */     this.targetUuid = null;
/* 242 */     this.targetName = null;
/* 243 */     this.overviewJson = null;
/* 244 */     this.canEdit = false;
/*     */     
/* 246 */     this.pokemonJson = null;
/* 247 */     this.pokemonTotal = 0;
/* 248 */     this.pokemonPage = 0;
/*     */     
/* 250 */     this.inventoryJson = null;
/*     */     
/* 252 */     this.vaultJson = null;
/* 253 */     this.vaultTotal = 0;
/* 254 */     this.vaultPage = 0;
/*     */     
/* 256 */     this.vaultStorageListJson = null;
/* 257 */     this.vaultStorageItemsJson = null;
/* 258 */     this.vaultStorageItemsPage = 0;
/*     */     
/* 260 */     this.logsJson = null;
/* 261 */     this.logsTotal = 0;
/* 262 */     this.logsPage = 0;
/*     */     
/* 264 */     this.ledgerJson = null;
/* 265 */     this.ledgerTotal = 0;
/* 266 */     this.ledgerPage = 0;
/*     */     
/* 268 */     this.punishmentJson = null;
/*     */     
/* 270 */     this.notesJson = null;
/*     */     
/* 272 */     this.auditJson = null;
/* 273 */     this.auditTotal = 0;
/* 274 */     this.auditPage = 0;
/*     */     
/* 276 */     this.snapshotsJson = null;
/* 277 */     this.snapshotsTotal = 0;
/* 278 */     this.snapshotsPage = 0;
/*     */     
/* 280 */     this.snapshotDetailJson = null;
/*     */     
/* 282 */     this.listeners.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTargetUuid() {
/* 287 */     return this.targetUuid; }
/* 288 */   public String getTargetName() { return this.targetName; }
/* 289 */   public String getOverviewJson() { return this.overviewJson; } public boolean isCanEdit() {
/* 290 */     return this.canEdit;
/*     */   }
/* 292 */   public String getPokemonJson() { return this.pokemonJson; }
/* 293 */   public int getPokemonTotal() { return this.pokemonTotal; }
/* 294 */   public int getPokemonPage() { return this.pokemonPage; }
/* 295 */   public String getMyPokemonJson() { return this.myPokemonJson; } public void clearMyPokemonJson() {
/* 296 */     this.myPokemonJson = null;
/*     */   } public String getInventoryJson() {
/* 298 */     return this.inventoryJson;
/*     */   }
/* 300 */   public String getVaultJson() { return this.vaultJson; }
/* 301 */   public int getVaultTotal() { return this.vaultTotal; } public int getVaultPage() {
/* 302 */     return this.vaultPage;
/*     */   }
/* 304 */   public String getVaultStorageListJson() { return this.vaultStorageListJson; }
/* 305 */   public String getVaultStorageItemsJson() { return this.vaultStorageItemsJson; } public int getVaultStorageItemsPage() {
/* 306 */     return this.vaultStorageItemsPage;
/*     */   }
/* 308 */   public String getLogsJson() { return this.logsJson; }
/* 309 */   public int getLogsTotal() { return this.logsTotal; } public int getLogsPage() {
/* 310 */     return this.logsPage;
/*     */   }
/* 312 */   public String getLedgerJson() { return this.ledgerJson; }
/* 313 */   public int getLedgerTotal() { return this.ledgerTotal; } public int getLedgerPage() {
/* 314 */     return this.ledgerPage;
/*     */   } public String getPunishmentJson() {
/* 316 */     return this.punishmentJson;
/*     */   } public String getNotesJson() {
/* 318 */     return this.notesJson;
/*     */   }
/* 320 */   public String getAuditJson() { return this.auditJson; }
/* 321 */   public int getAuditTotal() { return this.auditTotal; } public int getAuditPage() {
/* 322 */     return this.auditPage;
/*     */   }
/* 324 */   public String getSnapshotsJson() { return this.snapshotsJson; }
/* 325 */   public int getSnapshotsTotal() { return this.snapshotsTotal; } public int getSnapshotsPage() {
/* 326 */     return this.snapshotsPage;
/*     */   } public String getSnapshotDetailJson() {
/* 328 */     return this.snapshotDetailJson;
/*     */   }
/*     */   
/*     */   public void clearSnapshotDetail() {
/* 332 */     this.snapshotDetailJson = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearVaultCaches() {
/* 341 */     this.vaultJson = null;
/* 342 */     this.vaultTotal = 0;
/* 343 */     this.vaultPage = 0;
/* 344 */     this.vaultStorageListJson = null;
/* 345 */     this.vaultStorageItemsJson = null;
/* 346 */     this.vaultStorageItemsPage = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearVaultStorageItems() {
/* 351 */     this.vaultStorageItemsJson = null;
/* 352 */     this.vaultStorageItemsPage = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String extractJsonString(String json, String key) {
/* 362 */     if (json == null) return null; 
/* 363 */     String search = "\"" + key + "\":\"";
/* 364 */     int start = json.indexOf(search);
/* 365 */     if (start == -1) {
/*     */       
/* 367 */       search = "\"" + key + "\":";
/* 368 */       start = json.indexOf(search);
/* 369 */       if (start == -1) return null; 
/* 370 */       int i = start + search.length();
/* 371 */       if (i < json.length() && json.charAt(i) == 'n') return null; 
/* 372 */       return null;
/*     */     } 
/* 374 */     int valueStart = start + search.length();
/* 375 */     int valueEnd = json.indexOf("\"", valueStart);
/* 376 */     if (valueEnd == -1) return null; 
/* 377 */     return json.substring(valueStart, valueEnd);
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\lookup\data\LookupClientData.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */