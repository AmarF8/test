/*     */ package com.atlas.common.fabric.guide.data;
/*     */ 
/*     */ import com.atlas.common.fabric.guide.network.GuideNetwork;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class GuideData
/*     */ {
/*  16 */   private static final GuideData INSTANCE = new GuideData();
/*     */ 
/*     */ 
/*     */   
/*     */   public static GuideData getInstance() {
/*  21 */     return INSTANCE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  26 */   private List<GuideNetwork.PokedexEntry> pokedexEntries = Collections.emptyList();
/*  27 */   private int pokedexTotalCount = 0;
/*  28 */   private int pokedexCurrentPage = 0;
/*     */   
/*     */   private boolean pokedexLoading = false;
/*     */   
/*  32 */   private final List<GuideNetwork.PokedexEntry> allPokedexEntries = new ArrayList<>();
/*  33 */   private String lastSearch = "";
/*  34 */   private String lastTypeFilter = "";
/*     */ 
/*     */ 
/*     */   
/*  38 */   private PokemonDetail currentPokemonDetail = null;
/*     */ 
/*     */   
/*     */   private boolean detailLoading = false;
/*     */   
/*  43 */   private List<SpawnEntry> spawnEntries = Collections.emptyList();
/*  44 */   private int spawnTotalCount = 0;
/*  45 */   private int spawnCurrentPage = 0;
/*     */   private boolean spawnLoading = false;
/*  47 */   private final List<SpawnEntry> allSpawnEntries = new ArrayList<>();
/*     */ 
/*     */ 
/*     */   
/*  51 */   private List<EvolutionFamily> evolutionFamilies = Collections.emptyList();
/*  52 */   private int evolutionTotalCount = 0;
/*  53 */   private int evolutionCurrentPage = 0;
/*     */   private boolean evolutionLoading = false;
/*  55 */   private final List<EvolutionFamily> allEvolutionFamilies = new ArrayList<>();
/*     */ 
/*     */ 
/*     */   
/*  59 */   private List<MoveInfo> moveEntries = Collections.emptyList();
/*  60 */   private int moveTotalCount = 0;
/*  61 */   private int moveCurrentPage = 0;
/*     */   private boolean moveLoading = false;
/*  63 */   private final List<MoveInfo> allMoveEntries = new ArrayList<>();
/*     */ 
/*     */ 
/*     */   
/*  67 */   private List<RideableInfo> rideableEntries = Collections.emptyList();
/*  68 */   private int rideableTotalCount = 0;
/*  69 */   private int rideableCurrentPage = 0;
/*     */   private boolean rideableLoading = false;
/*  71 */   private final List<RideableInfo> allRideableEntries = new ArrayList<>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  76 */   private String networkName = "CobblemonGG";
/*     */   
/*  78 */   private String wikiUrl = "https://cobblemon.gg/wiki";
/*     */   
/*  80 */   public String getNetworkName() { return this.networkName; }
/*  81 */   public void setNetworkName(String name) { this.networkName = name; }
/*  82 */   public String getWikiUrl() { return this.wikiUrl; } public void setWikiUrl(String url) {
/*  83 */     this.wikiUrl = url;
/*     */   }
/*     */ 
/*     */   
/*  87 */   private List<WikiSection> wikiSections = Collections.emptyList();
/*  88 */   private String currentArticleContent = "";
/*     */ 
/*     */   
/*     */   private boolean wikiLoading = false;
/*     */   
/*  93 */   private MoveDetail currentMoveDetail = null;
/*     */ 
/*     */   
/*     */   private boolean moveDetailLoading = false;
/*     */   
/*  98 */   private List<SearchResult> searchResults = Collections.emptyList();
/*     */   
/*     */   private boolean searchLoading = false;
/*     */ 
/*     */   
/*     */   public void setPokedexPage(List<GuideNetwork.PokedexEntry> entries, int totalCount, int page) {
/* 104 */     this.pokedexEntries = entries;
/* 105 */     this.pokedexTotalCount = totalCount;
/* 106 */     this.pokedexCurrentPage = page;
/* 107 */     this.pokedexLoading = false;
/*     */     
/* 109 */     if (page == 0) {
/* 110 */       this.allPokedexEntries.clear();
/*     */     }
/* 112 */     this.allPokedexEntries.addAll(entries);
/*     */   }
/*     */   
/*     */   public void resetPokedex(String search, String typeFilter) {
/* 116 */     if (!search.equals(this.lastSearch) || !typeFilter.equals(this.lastTypeFilter)) {
/* 117 */       this.allPokedexEntries.clear();
/* 118 */       this.pokedexCurrentPage = 0;
/* 119 */       this.lastSearch = search;
/* 120 */       this.lastTypeFilter = typeFilter;
/*     */     } 
/*     */   }
/*     */   
/*     */   public List<GuideNetwork.PokedexEntry> getAllPokedexEntries() {
/* 125 */     return Collections.unmodifiableList(this.allPokedexEntries);
/*     */   }
/*     */   
/* 128 */   public List<GuideNetwork.PokedexEntry> getPokedexEntries() { return this.pokedexEntries; }
/* 129 */   public int getPokedexTotalCount() { return this.pokedexTotalCount; }
/* 130 */   public int getPokedexCurrentPage() { return this.pokedexCurrentPage; }
/* 131 */   public boolean isPokedexLoading() { return this.pokedexLoading; }
/* 132 */   public void setPokedexLoading(boolean loading) { this.pokedexLoading = loading; } public boolean hasMorePokedexPages() {
/* 133 */     return (this.allPokedexEntries.size() < this.pokedexTotalCount);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPokemonDetailFromJson(String json) {
/* 138 */     if (json == null || json.isEmpty()) {
/* 139 */       this.currentPokemonDetail = null;
/*     */     } else {
/* 141 */       this.currentPokemonDetail = PokemonDetail.fromJson(json);
/*     */     } 
/* 143 */     this.detailLoading = false;
/*     */   }
/*     */   
/*     */   public void setPokemonDetail(PokemonDetail detail) {
/* 147 */     this.currentPokemonDetail = detail;
/* 148 */     this.detailLoading = false;
/*     */   }
/*     */   
/* 151 */   public PokemonDetail getCurrentPokemonDetail() { return this.currentPokemonDetail; }
/* 152 */   public void clearPokemonDetail() { this.currentPokemonDetail = null; }
/* 153 */   public boolean isDetailLoading() { return this.detailLoading; } public void setDetailLoading(boolean loading) {
/* 154 */     this.detailLoading = loading;
/*     */   }
/*     */   
/*     */   public List<SpawnEntry> getAllSpawnEntries() {
/* 158 */     return Collections.unmodifiableList(this.allSpawnEntries);
/* 159 */   } public int getSpawnTotalCount() { return this.spawnTotalCount; }
/* 160 */   public int getSpawnCurrentPage() { return this.spawnCurrentPage; }
/* 161 */   public boolean isSpawnLoading() { return this.spawnLoading; }
/* 162 */   public void setSpawnLoading(boolean loading) { this.spawnLoading = loading; } public boolean hasMoreSpawnPages() {
/* 163 */     return (this.allSpawnEntries.size() < this.spawnTotalCount);
/*     */   }
/*     */   public void resetSpawns() {
/* 166 */     this.allSpawnEntries.clear();
/* 167 */     this.spawnCurrentPage = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<EvolutionFamily> getAllEvolutionFamilies() {
/* 172 */     return Collections.unmodifiableList(this.allEvolutionFamilies); }
/* 173 */   public int getEvolutionTotalCount() { return this.evolutionTotalCount; }
/* 174 */   public int getEvolutionCurrentPage() { return this.evolutionCurrentPage; }
/* 175 */   public boolean isEvolutionLoading() { return this.evolutionLoading; }
/* 176 */   public void setEvolutionLoading(boolean loading) { this.evolutionLoading = loading; } public boolean hasMoreEvolutionPages() {
/* 177 */     return (this.allEvolutionFamilies.size() < this.evolutionTotalCount);
/*     */   }
/*     */   public void resetEvolutions() {
/* 180 */     this.allEvolutionFamilies.clear();
/* 181 */     this.evolutionCurrentPage = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<MoveInfo> getAllMoveEntries() {
/* 186 */     return Collections.unmodifiableList(this.allMoveEntries); }
/* 187 */   public int getMoveTotalCount() { return this.moveTotalCount; }
/* 188 */   public int getMoveCurrentPage() { return this.moveCurrentPage; }
/* 189 */   public boolean isMoveLoading() { return this.moveLoading; }
/* 190 */   public void setMoveLoading(boolean loading) { this.moveLoading = loading; } public boolean hasMoreMovePages() {
/* 191 */     return (this.allMoveEntries.size() < this.moveTotalCount);
/*     */   }
/*     */   public void resetMoves() {
/* 194 */     this.allMoveEntries.clear();
/* 195 */     this.moveCurrentPage = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<RideableInfo> getAllRideableEntries() {
/* 200 */     return Collections.unmodifiableList(this.allRideableEntries); }
/* 201 */   public int getRideableTotalCount() { return this.rideableTotalCount; }
/* 202 */   public int getRideableCurrentPage() { return this.rideableCurrentPage; }
/* 203 */   public boolean isRideableLoading() { return this.rideableLoading; }
/* 204 */   public void setRideableLoading(boolean loading) { this.rideableLoading = loading; } public boolean hasMoreRideablePages() {
/* 205 */     return (this.allRideableEntries.size() < this.rideableTotalCount);
/*     */   }
/*     */   public void resetRideable() {
/* 208 */     this.allRideableEntries.clear();
/* 209 */     this.rideableCurrentPage = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<WikiSection> getWikiSections() {
/* 214 */     return this.wikiSections; }
/* 215 */   public String getCurrentArticleContent() { return this.currentArticleContent; }
/* 216 */   public boolean isWikiLoading() { return this.wikiLoading; } public void setWikiLoading(boolean loading) {
/* 217 */     this.wikiLoading = loading;
/*     */   }
/*     */   
/*     */   public MoveDetail getCurrentMoveDetail() {
/* 221 */     return this.currentMoveDetail;
/* 222 */   } public void setCurrentMoveDetail(MoveDetail detail) { this.currentMoveDetail = detail; this.moveDetailLoading = false; }
/* 223 */   public boolean isMoveDetailLoading() { return this.moveDetailLoading; }
/* 224 */   public void setMoveDetailLoading(boolean loading) { this.moveDetailLoading = loading; } public void clearMoveDetail() {
/* 225 */     this.currentMoveDetail = null;
/*     */   }
/*     */   
/*     */   public List<SearchResult> getSearchResults() {
/* 229 */     return this.searchResults;
/* 230 */   } public boolean isSearchLoading() { return this.searchLoading; }
/* 231 */   public void setSearchLoading(boolean loading) { this.searchLoading = loading; } public void clearSearchResults() {
/* 232 */     this.searchResults = Collections.emptyList();
/*     */   }
/*     */   
/*     */   public void handleGuideData(String dataType, String jsonData, int totalCount, int page) {
/*     */     List<SpawnEntry> list2;
/*     */     List<EvolutionFamily> list1;
/*     */     List<MoveInfo> list;
/*     */     List<RideableInfo> entries;
/* 240 */     switch (dataType) {
/*     */       case "spawns":
/* 242 */         list2 = SpawnEntry.fromJsonArray(jsonData);
/* 243 */         this.spawnEntries = list2;
/* 244 */         this.spawnTotalCount = totalCount;
/* 245 */         this.spawnCurrentPage = page;
/* 246 */         this.spawnLoading = false;
/* 247 */         if (page == 0) this.allSpawnEntries.clear(); 
/* 248 */         this.allSpawnEntries.addAll(list2);
/*     */         break;
/*     */       case "evolutions":
/* 251 */         list1 = EvolutionFamily.fromJsonArray(jsonData);
/* 252 */         this.evolutionFamilies = list1;
/* 253 */         this.evolutionTotalCount = totalCount;
/* 254 */         this.evolutionCurrentPage = page;
/* 255 */         this.evolutionLoading = false;
/* 256 */         if (page == 0) this.allEvolutionFamilies.clear(); 
/* 257 */         this.allEvolutionFamilies.addAll(list1);
/*     */         break;
/*     */       case "moves":
/* 260 */         list = MoveInfo.fromJsonArray(jsonData);
/* 261 */         this.moveEntries = list;
/* 262 */         this.moveTotalCount = totalCount;
/* 263 */         this.moveCurrentPage = page;
/* 264 */         this.moveLoading = false;
/* 265 */         if (page == 0) this.allMoveEntries.clear(); 
/* 266 */         this.allMoveEntries.addAll(list);
/*     */         break;
/*     */       case "rideable":
/* 269 */         entries = RideableInfo.fromJsonArray(jsonData);
/* 270 */         this.rideableEntries = entries;
/* 271 */         this.rideableTotalCount = totalCount;
/* 272 */         this.rideableCurrentPage = page;
/* 273 */         this.rideableLoading = false;
/* 274 */         if (page == 0) this.allRideableEntries.clear(); 
/* 275 */         this.allRideableEntries.addAll(entries);
/*     */         break;
/*     */       case "move_detail":
/* 278 */         this.currentMoveDetail = MoveDetail.fromJson(jsonData);
/* 279 */         this.moveDetailLoading = false;
/*     */         break;
/*     */       case "wiki_sections":
/* 282 */         this.wikiSections = WikiSection.fromJsonArray(jsonData);
/* 283 */         this.wikiLoading = false;
/*     */         break;
/*     */       case "wiki_article":
/* 286 */         this.currentArticleContent = jsonData;
/* 287 */         this.wikiLoading = false;
/*     */         break;
/*     */       case "search":
/* 290 */         this.searchResults = SearchResult.fromJsonArray(jsonData);
/* 291 */         this.searchLoading = false;
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearAll() {
/* 299 */     this.allPokedexEntries.clear();
/* 300 */     this.pokedexEntries = Collections.emptyList();
/* 301 */     this.pokedexTotalCount = 0;
/* 302 */     this.pokedexCurrentPage = 0;
/* 303 */     this.pokedexLoading = false;
/* 304 */     this.currentPokemonDetail = null;
/* 305 */     this.detailLoading = false;
/* 306 */     this.lastSearch = "";
/* 307 */     this.lastTypeFilter = "";
/*     */     
/* 309 */     this.allSpawnEntries.clear();
/* 310 */     this.spawnLoading = false;
/* 311 */     this.allEvolutionFamilies.clear();
/* 312 */     this.evolutionLoading = false;
/* 313 */     this.allMoveEntries.clear();
/* 314 */     this.moveLoading = false;
/* 315 */     this.allRideableEntries.clear();
/* 316 */     this.rideableLoading = false;
/* 317 */     this.wikiSections = Collections.emptyList();
/* 318 */     this.currentArticleContent = "";
/* 319 */     this.wikiLoading = false;
/* 320 */     this.searchResults = Collections.emptyList();
/* 321 */     this.searchLoading = false;
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\guide\data\GuideData.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */