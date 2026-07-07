/*     */ package com.atlas.common.fabric.ranked.screen;
/*     */ import com.atlas.common.fabric.battletower.screen.CobblemonFontIcon;
/*     */ import com.atlas.common.fabric.ranked.data.PlayerRankedData;
/*     */ import com.atlas.common.fabric.ranked.network.RankedNetwork;
/*     */ import com.atlas.common.fabric.screen.PokemonRenderHelper;
/*     */ import com.cobblemon.mod.common.api.abilities.AbilityPool;
/*     */ import com.cobblemon.mod.common.api.abilities.PotentialAbility;
/*     */ import com.cobblemon.mod.common.api.moves.Move;
/*     */ import com.cobblemon.mod.common.api.pokeball.PokeBalls;
/*     */ import com.cobblemon.mod.common.api.pokemon.stats.Stat;
/*     */ import com.cobblemon.mod.common.api.pokemon.stats.Stats;
/*     */ import com.cobblemon.mod.common.api.types.ElementalType;
/*     */ import com.cobblemon.mod.common.client.render.models.blockbench.FloatingState;
/*     */ import com.cobblemon.mod.common.pokeball.PokeBall;
/*     */ import com.cobblemon.mod.common.pokemon.Gender;
/*     */ import com.cobblemon.mod.common.pokemon.Pokemon;
/*     */ import com.cobblemon.mod.common.pokemon.abilities.HiddenAbility;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.Optional;
/*     */ import java.util.Set;
/*     */ import net.minecraft.class_1109;
/*     */ import net.minecraft.class_1113;
/*     */ import net.minecraft.class_124;
/*     */ import net.minecraft.class_1799;
/*     */ import net.minecraft.class_1935;
/*     */ import net.minecraft.class_2487;
/*     */ import net.minecraft.class_2522;
/*     */ import net.minecraft.class_2561;
/*     */ import net.minecraft.class_2960;
/*     */ import net.minecraft.class_310;
/*     */ import net.minecraft.class_332;
/*     */ import net.minecraft.class_3417;
/*     */ import net.minecraft.class_437;
/*     */ import net.minecraft.class_4587;
/*     */ import net.minecraft.class_5250;
/*     */ import net.minecraft.class_6880;
/*     */ import org.joml.Quaternionf;
/*     */ 
/*     */ public class RankedDefenseScreen extends class_437 {
/*  47 */   private static final int PANEL_BG = c(10, 12, 18, 255);
/*  48 */   private static final int SECTION_BG = c(14, 16, 24, 255);
/*  49 */   private static final int BORDER_COLOR = c(50, 60, 100, 255);
/*  50 */   private static final int BORDER_HIGHLIGHT = c(80, 140, 220, 255);
/*  51 */   private static final int ACCENT_RED = c(220, 50, 50, 255);
/*  52 */   private static final int ACCENT_CRIMSON = c(60, 100, 180, 255);
/*  53 */   private static final int ACCENT_ORANGE = c(80, 200, 220, 255);
/*  54 */   private static final int ACCENT_WARM = c(100, 180, 240, 255);
/*  55 */   private static final int ACCENT_GREEN = c(80, 255, 150, 255);
/*  56 */   private static final int ACCENT_GOLD = c(255, 200, 80, 255);
/*  57 */   private static final int TEXT_PRIMARY = c(220, 230, 245, 255);
/*  58 */   private static final int TEXT_SECONDARY = c(160, 180, 210, 255);
/*  59 */   private static final int TEXT_DIM = c(100, 120, 160, 255);
/*  60 */   private static final int SLOT_FILLED = c(25, 30, 50, 255);
/*  61 */   private static final int SLOT_EMPTY = c(14, 16, 26, 255);
/*     */ 
/*     */   
/*  64 */   private static final int STAT_HP = c(255, 80, 80, 255);
/*  65 */   private static final int STAT_ATK = c(255, 160, 60, 255);
/*  66 */   private static final int STAT_DEF = c(255, 230, 80, 255);
/*  67 */   private static final int STAT_SPATK = c(100, 200, 255, 255);
/*  68 */   private static final int STAT_SPDEF = c(100, 255, 100, 255);
/*  69 */   private static final int STAT_SPD = c(200, 100, 255, 255);
/*     */   
/*     */   private static final int GUI_WIDTH = 256;
/*     */   private static final int GUI_HEIGHT = 272;
/*     */   private int guiLeft;
/*     */   private int guiTop;
/*     */   private boolean closeHovered;
/*     */   private boolean lockInHovered;
/*     */   private boolean unlockHovered;
/*     */   private boolean backHovered;
/*  79 */   private int hoveredSlotIndex = -1;
/*  80 */   private RankedNetwork.PartyEntry hoveredSlotPokemon = null;
/*     */   
/*     */   private boolean showPopup;
/*     */   
/*  84 */   private String popupMessage = "";
/*     */   
/*     */   private boolean popupOkHovered;
/*     */   
/*  88 */   private final Map<Integer, FloatingState> pokemonStates = new HashMap<>();
/*     */ 
/*     */   
/*  91 */   private final Map<Integer, Pokemon> pokemonCache = new HashMap<>();
/*  92 */   private final Map<Integer, TooltipCacheEntry> tooltipCache = new HashMap<>(); private static final class TooltipCacheEntry extends Record { private final String key; private final long expiresAt; private final List<class_2561> tooltip;
/*  93 */     private TooltipCacheEntry(String key, long expiresAt, List<class_2561> tooltip) { this.key = key; this.expiresAt = expiresAt; this.tooltip = tooltip; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/ranked/screen/RankedDefenseScreen$TooltipCacheEntry;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #93	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*  93 */       //   0	7	0	this	Lcom/atlas/common/fabric/ranked/screen/RankedDefenseScreen$TooltipCacheEntry; } public String key() { return this.key; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/ranked/screen/RankedDefenseScreen$TooltipCacheEntry;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #93	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*  93 */       //   0	7	0	this	Lcom/atlas/common/fabric/ranked/screen/RankedDefenseScreen$TooltipCacheEntry; } public long expiresAt() { return this.expiresAt; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/ranked/screen/RankedDefenseScreen$TooltipCacheEntry;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #93	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lcom/atlas/common/fabric/ranked/screen/RankedDefenseScreen$TooltipCacheEntry;
/*  93 */       //   0	8	1	o	Ljava/lang/Object; } public List<class_2561> tooltip() { return this.tooltip; }
/*     */      } private static int c(int r, int g, int b, int a) {
/*  95 */     return a << 24 | r << 16 | g << 8 | b;
/*     */   }
/*     */   public RankedDefenseScreen() {
/*  98 */     super((class_2561)class_2561.method_43471("screen.cobblemon_ranked.defense"));
/*  99 */     for (int i = 0; i < 6; ) { this.pokemonStates.put(Integer.valueOf(i), new FloatingState()); i++; }
/* 100 */      PokemonRenderHelper.init();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void method_25426() {
/* 105 */     super.method_25426();
/* 106 */     this.guiLeft = (this.field_22789 - 256) / 2;
/* 107 */     this.guiTop = (this.field_22790 - 272) / 2;
/*     */   }
/*     */   
/*     */   public void handleLockInResult(boolean success, String reason) {
/* 111 */     if (!success) { this.showPopup = true; this.popupMessage = (reason != null) ? reason : "Lock-in failed"; }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void method_25394(class_332 ctx, int mouseX, int mouseY, float delta) {
/* 120 */     ctx.method_25294(0, 0, this.field_22789, this.field_22790, c(0, 0, 0, 180));
/* 121 */     drawPanel(ctx);
/*     */     
/* 123 */     this.closeHovered = this.lockInHovered = this.unlockHovered = this.backHovered = false;
/* 124 */     this.hoveredSlotIndex = -1;
/* 125 */     this.hoveredSlotPokemon = null;
/*     */     
/* 127 */     PlayerRankedData data = PlayerRankedData.getInstance();
/*     */     
/* 129 */     drawHeader(ctx, data);
/* 130 */     drawSlots(ctx, data, mouseX, mouseY, delta);
/* 131 */     drawBottomButtons(ctx, data, mouseX, mouseY);
/*     */     
/* 133 */     super.method_25394(ctx, mouseX, mouseY, delta);
/*     */ 
/*     */     
/* 136 */     if (this.hoveredSlotPokemon != null && !this.showPopup) {
/* 137 */       drawRichTooltip(ctx, this.hoveredSlotPokemon, mouseX, mouseY);
/*     */     }
/*     */     
/* 140 */     if (this.showPopup) drawPopupOverlay(ctx, mouseX, mouseY);
/*     */   
/*     */   }
/*     */ 
/*     */   
/*     */   private void drawPanel(class_332 ctx) {
/* 146 */     int x = this.guiLeft, y = this.guiTop, w = 256, h = 272;
/* 147 */     ctx.method_25294(x, y, x + w, y + h, PANEL_BG);
/* 148 */     ctx.method_25294(x, y, x + w, y + 2, ACCENT_ORANGE);
/* 149 */     ctx.method_25294(x, y + h - 2, x + w, y + h, ACCENT_CRIMSON);
/* 150 */     ctx.method_25294(x, y, x + 2, y + h, BORDER_HIGHLIGHT);
/* 151 */     ctx.method_25294(x + w - 2, y, x + w, y + h, ACCENT_CRIMSON);
/* 152 */     ctx.method_25294(x + 2, y + 2, x + w - 2, y + 3, c(0, 0, 0, 100));
/* 153 */     ctx.method_25294(x + 2, y + 2, x + 3, y + h - 2, c(0, 0, 0, 100));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawHeader(class_332 ctx, PlayerRankedData data) {
/* 159 */     int headerY = this.guiTop + 2;
/* 160 */     int headerH = 18;
/*     */     
/* 162 */     for (int row = 0; row < headerH; row++) {
/* 163 */       float p = row / headerH;
/* 164 */       ctx.method_25294(this.guiLeft + 2, headerY + row, this.guiLeft + 256 - 2, headerY + row + 1, 
/* 165 */           c(15, 18, 30, 200 + (int)(p * 55.0F)));
/*     */     } 
/*     */     
/* 168 */     ctx.method_25300(this.field_22793, "§lDEFENSE TEAM", this.guiLeft + 128, headerY + 5, ACCENT_ORANGE);
/*     */ 
/*     */     
/* 171 */     long cooldown = data.getDefenseEditCooldownRemaining();
/* 172 */     if (cooldown > 0L && data.isDefenseLockedIn()) {
/* 173 */       long seconds = cooldown / 1000L;
/* 174 */       long minutes = seconds / 60L;
/* 175 */       long hours = minutes / 60L;
/* 176 */       String cdText = (hours > 0L) ? ("Edit: " + hours + "h " + minutes % 60L + "m") : ("Edit: " + minutes + "m " + seconds % 60L + "s");
/* 177 */       class_4587 mat = ctx.method_51448();
/* 178 */       mat.method_22903(); mat.method_22905(0.75F, 0.75F, 1.0F);
/* 179 */       int cdW = this.field_22793.method_1727(cdText);
/* 180 */       int cdX = (int)((this.guiLeft + 256 - 6) / 0.75F) - cdW;
/* 181 */       int cdY = (int)((headerY + 5) / 0.75F);
/* 182 */       ctx.method_51433(this.field_22793, cdText, cdX, cdY, ACCENT_RED, true);
/* 183 */       mat.method_22909();
/*     */     } 
/*     */ 
/*     */     
/* 187 */     int sepY = headerY + headerH - 1;
/* 188 */     for (int i = 0; i < 244; i++) {
/* 189 */       float p = i / 244.0F;
/* 190 */       int r = (int)(60.0F + p * 20.0F), g = (int)(180.0F - p * 60.0F), b = (int)(220.0F - p * 20.0F);
/* 191 */       ctx.method_25294(this.guiLeft + 6 + i, sepY, this.guiLeft + 7 + i, sepY + 1, 
/* 192 */           c(r, g, b, 150 + (int)(50.0D * Math.sin(i * 0.1D))));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawSlots(class_332 ctx, PlayerRankedData data, int mouseX, int mouseY, float delta) {
/* 199 */     List<RankedNetwork.PartyEntry> defense = data.getDefenseParty();
/* 200 */     boolean lockedIn = data.isDefenseLockedIn();
/* 201 */     int slotH = 34;
/* 202 */     int slotSpacing = 2;
/* 203 */     int slotX = this.guiLeft + 6;
/* 204 */     int slotW = 244;
/*     */     
/* 206 */     for (int i = 0; i < 6; i++) {
/* 207 */       int slotY = this.guiTop + 23 + i * (slotH + slotSpacing);
/* 208 */       RankedNetwork.PartyEntry entry = (i < defense.size()) ? defense.get(i) : null;
/* 209 */       boolean hasPokemon = (entry != null && entry.speciesName() != null && !entry.speciesName().isEmpty());
/* 210 */       boolean hovered = (mouseX >= slotX && mouseX < slotX + slotW && mouseY >= slotY && mouseY < slotY + slotH);
/*     */ 
/*     */       
/* 213 */       ctx.method_25294(slotX, slotY, slotX + slotW, slotY + slotH, hasPokemon ? SLOT_FILLED : SLOT_EMPTY);
/*     */ 
/*     */       
/* 216 */       int borderCol = BORDER_COLOR;
/* 217 */       if (lockedIn && hasPokemon) { borderCol = ACCENT_GREEN; }
/* 218 */       else if (hovered && hasPokemon) { borderCol = ACCENT_ORANGE; }
/* 219 */        ctx.method_25294(slotX, slotY, slotX + slotW, slotY + 1, borderCol);
/* 220 */       ctx.method_25294(slotX, slotY + slotH - 1, slotX + slotW, slotY + slotH, RankedStyledButton.darken(borderCol, 30));
/* 221 */       ctx.method_25294(slotX, slotY, slotX + 1, slotY + slotH, borderCol);
/* 222 */       ctx.method_25294(slotX + slotW - 1, slotY, slotX + slotW, slotY + slotH, RankedStyledButton.darken(borderCol, 30));
/*     */ 
/*     */       
/* 225 */       if (lockedIn && hasPokemon) {
/* 226 */         long now = System.currentTimeMillis();
/* 227 */         float breathe = (float)Math.sin(now / 400.0D) * 0.5F + 0.5F;
/* 228 */         int ga = (int)(40.0F + breathe * 60.0F);
/* 229 */         int gc = ACCENT_GREEN & 0xFFFFFF | ga << 24;
/* 230 */         int gs = 2;
/* 231 */         ctx.method_25294(slotX - gs, slotY - gs, slotX + slotW + gs, slotY - gs + 1, gc);
/* 232 */         ctx.method_25294(slotX - gs, slotY + slotH + gs - 1, slotX + slotW + gs, slotY + slotH + gs, gc);
/* 233 */         ctx.method_25294(slotX - gs, slotY - gs, slotX - gs + 1, slotY + slotH + gs, gc);
/* 234 */         ctx.method_25294(slotX + slotW + gs - 1, slotY - gs, slotX + slotW + gs, slotY + slotH + gs, gc);
/*     */       } 
/*     */ 
/*     */       
/* 238 */       if (hovered && hasPokemon && !lockedIn) {
/* 239 */         for (int g = 3; g > 0; g--) {
/* 240 */           int alpha = 20 * g;
/* 241 */           int gc = ACCENT_ORANGE & 0xFFFFFF | alpha << 24;
/* 242 */           ctx.method_25294(slotX - g, slotY - g, slotX + slotW + g, slotY - g + 1, gc);
/* 243 */           ctx.method_25294(slotX - g, slotY + slotH + g - 1, slotX + slotW + g, slotY + slotH + g, gc);
/* 244 */           ctx.method_25294(slotX - g, slotY - g, slotX - g + 1, slotY + slotH + g, gc);
/* 245 */           ctx.method_25294(slotX + slotW + g - 1, slotY - g, slotX + slotW + g, slotY + slotH + g, gc);
/*     */         } 
/*     */       }
/*     */       
/* 249 */       if (!hasPokemon) {
/* 250 */         ctx.method_25300(this.field_22793, "Empty", slotX + slotW / 2, slotY + slotH / 2 - 4, TEXT_DIM);
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 255 */         if (hovered) {
/* 256 */           this.hoveredSlotIndex = i;
/* 257 */           this.hoveredSlotPokemon = entry;
/*     */         } 
/*     */ 
/*     */         
/* 261 */         int modelX = slotX + 3;
/* 262 */         int modelY = slotY + 2;
/* 263 */         int modelSize = 27;
/* 264 */         ctx.method_25294(modelX, modelY, modelX + modelSize, modelY + modelSize, c(12, 15, 25, 255));
/* 265 */         ctx.method_25294(modelX, modelY, modelX + modelSize, modelY + 1, c(35, 45, 70, 255));
/* 266 */         renderPokemonInSlot(ctx, entry, modelX, modelY, modelSize, i, delta);
/*     */ 
/*     */         
/* 269 */         int infoX = modelX + modelSize + 6;
/* 270 */         ctx.method_51433(this.field_22793, "§l" + entry.speciesName(), infoX, slotY + 5, TEXT_PRIMARY, true);
/*     */         
/* 272 */         String levelText = "Lv. " + entry.level();
/* 273 */         ctx.method_51433(this.field_22793, levelText, infoX, slotY + 17, ACCENT_ORANGE, true);
/*     */         
/* 275 */         if (entry.shiny()) {
/* 276 */           int shinyX = infoX + this.field_22793.method_1727(levelText) + 6;
/* 277 */           ctx.method_51433(this.field_22793, "✦", shinyX, slotY + 17, ACCENT_GOLD, true);
/*     */         } 
/*     */ 
/*     */         
/* 281 */         int accentCol = lockedIn ? ACCENT_GREEN : ACCENT_ORANGE;
/* 282 */         ctx.method_25294(slotX + 1, slotY + slotH - 2, slotX + slotW - 1, slotY + slotH - 1, accentCol & 0xFFFFFF | 0x50000000);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void renderPokemonInSlot(class_332 ctx, RankedNetwork.PartyEntry entry, int sx, int sy, int ss, int slotIndex, float delta) {
/*     */     try {
/* 289 */       class_2960 speciesId = class_2960.method_60654(entry.speciesId());
/* 290 */       FloatingState state = this.pokemonStates.get(Integer.valueOf(slotIndex));
/*     */       
/* 292 */       Set<String> aspects = parseAspects(entry.aspects());
/* 293 */       Pokemon parsed = getPokemonFromEntry(entry);
/* 294 */       if (parsed != null) {
/* 295 */         aspects = aspects.isEmpty() ? parsed.getAspects() : aspects;
/* 296 */       } else if (entry.shiny()) {
/* 297 */         aspects = Set.of("shiny");
/*     */       } 
/* 299 */       state.setCurrentAspects(aspects);
/* 300 */       class_4587 mat = ctx.method_51448();
/* 301 */       mat.method_22903();
/* 302 */       mat.method_22904(sx + ss / 2.0D, sy + 4.0D, 0.0D);
/* 303 */       mat.method_22905(2.0F, 2.0F, 1.0F);
/* 304 */       Quaternionf rot = new Quaternionf();
/* 305 */       rot.rotationXYZ((float)Math.toRadians(13.0D), (float)Math.toRadians(35.0D), 0.0F);
/* 306 */       if (PokemonRenderHelper.isAvailable()) {
/* 307 */         PokemonRenderHelper.draw(speciesId, mat, rot, state, delta);
/*     */       } else {
/* 309 */         String initial = entry.speciesName().isEmpty() ? "?" : entry.speciesName().substring(0, 1);
/* 310 */         ctx.method_25300(this.field_22793, initial, sx + ss / 2, sy + ss / 2 - 4, TEXT_PRIMARY);
/*     */       } 
/* 312 */       mat.method_22909();
/* 313 */     } catch (Exception e) {
/* 314 */       String initial = (entry.speciesName() != null && !entry.speciesName().isEmpty()) ? entry.speciesName().substring(0, 1) : "?";
/* 315 */       ctx.method_25300(this.field_22793, initial, sx + ss / 2, sy + ss / 2 - 4, TEXT_PRIMARY);
/*     */     } 
/*     */   }
/*     */   
/*     */   private Set<String> parseAspects(String aspects) {
/* 320 */     if (aspects == null || aspects.isBlank()) return Set.of(); 
/* 321 */     Set<String> parsed = new HashSet<>();
/* 322 */     for (String aspect : aspects.split(",")) {
/* 323 */       String trimmed = aspect.trim();
/* 324 */       if (!trimmed.isEmpty()) parsed.add(trimmed); 
/*     */     } 
/* 326 */     return parsed;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private Pokemon getPokemonFromEntry(RankedNetwork.PartyEntry entry) {
/* 332 */     if (this.pokemonCache.containsKey(Integer.valueOf(entry.slot()))) return this.pokemonCache.get(Integer.valueOf(entry.slot())); 
/* 333 */     if (entry.pokemonData() == null || entry.pokemonData().isEmpty()) return null; 
/*     */     try {
/* 335 */       if ((class_310.method_1551()).field_1687 == null) return null; 
/* 336 */       class_2487 nbt = class_2522.method_10718(entry.pokemonData());
/* 337 */       Pokemon pokemon = new Pokemon();
/* 338 */       pokemon.loadFromNBT((class_310.method_1551()).field_1687.method_30349(), nbt);
/* 339 */       this.pokemonCache.put(Integer.valueOf(entry.slot()), pokemon);
/* 340 */       return pokemon;
/* 341 */     } catch (Exception e) {
/* 342 */       return null;
/*     */     } 
/*     */   }
/*     */   private void drawRichTooltip(class_332 ctx, RankedNetwork.PartyEntry entry, int mx, int my) {
/*     */     List<class_2561> tooltip;
/* 347 */     long now = System.currentTimeMillis();
/* 348 */     String key = entry.speciesId() + "|" + entry.speciesId() + "|" + entry.level() + "|" + entry.shiny() + "|" + entry.heldItem() + "|" + entry.caughtBall();
/* 349 */     TooltipCacheEntry cache = this.tooltipCache.get(Integer.valueOf(entry.slot()));
/*     */ 
/*     */     
/* 352 */     if (cache != null && cache.expiresAt >= now && cache.key.equals(key)) {
/* 353 */       tooltip = cache.tooltip;
/*     */     } else {
/* 355 */       tooltip = new ArrayList<>();
/* 356 */       Pokemon pokemon = getPokemonFromEntry(entry);
/*     */       
/* 358 */       if (pokemon != null) {
/*     */         
/* 360 */         Gender gender = pokemon.getGender();
/* 361 */         String genderSymbol = (gender == Gender.MALE) ? " ♂" : ((gender == Gender.FEMALE) ? " ♀" : " ⚲");
/* 362 */         class_124 genderFormat = (gender == Gender.MALE) ? class_124.field_1075 : ((gender == Gender.FEMALE) ? class_124.field_1076 : class_124.field_1068);
/*     */         
/* 364 */         class_5250 headerLine = pokemon.getSpecies().getTranslatedName().method_27661().method_27695(new class_124[] { class_124.field_1067, class_124.field_1068 });
/* 365 */         headerLine = headerLine.method_10852((class_2561)class_2561.method_43470(genderSymbol).method_27692(genderFormat));
/* 366 */         headerLine = headerLine.method_10852((class_2561)class_2561.method_43470(" Lv. " + pokemon.getLevel()).method_27692(class_124.field_1068));
/* 367 */         tooltip.add(headerLine);
/* 368 */         tooltip.add(class_2561.method_43473());
/*     */ 
/*     */         
/* 371 */         class_5250 tagsLine = null;
/* 372 */         if (pokemon.isLegendary()) {
/* 373 */           tagsLine = class_2561.method_43473().method_27661();
/* 374 */           tagsLine = tagsLine.method_10852((class_2561)CobblemonFontIcon.LEGENDARY.draw());
/*     */         } 
/* 376 */         if (pokemon.isUltraBeast()) {
/* 377 */           tagsLine = (tagsLine == null) ? class_2561.method_43473().method_27661() : tagsLine.method_10852((class_2561)class_2561.method_43470(" "));
/* 378 */           tagsLine = tagsLine.method_10852((class_2561)CobblemonFontIcon.ULTRA_BEAST.draw());
/*     */         } 
/* 380 */         if (pokemon.isMythical()) {
/* 381 */           tagsLine = (tagsLine == null) ? class_2561.method_43473().method_27661() : tagsLine.method_10852((class_2561)class_2561.method_43470(" "));
/* 382 */           tagsLine = tagsLine.method_10852((class_2561)CobblemonFontIcon.MYTHICAL.draw());
/*     */         } 
/* 384 */         if (pokemon.hasLabels(new String[] { "paradox" }) || pokemon.getSpecies().getName().equals("Miraidon") || pokemon.getSpecies().getName().equals("Koraidon")) {
/* 385 */           tagsLine = (tagsLine == null) ? class_2561.method_43473().method_27661() : tagsLine.method_10852((class_2561)class_2561.method_43470(" "));
/* 386 */           tagsLine = tagsLine.method_10852((class_2561)CobblemonFontIcon.getParadoxIcon(pokemon.getSpecies().getName()).draw());
/*     */         } 
/* 388 */         if (pokemon.getShiny()) {
/* 389 */           tagsLine = (tagsLine == null) ? class_2561.method_43473().method_27661() : tagsLine.method_10852((class_2561)class_2561.method_43470(" "));
/* 390 */           tagsLine = tagsLine.method_10852((class_2561)CobblemonFontIcon.SHINY.draw());
/*     */         } 
/* 392 */         if (tagsLine != null) tooltip.add(tagsLine);
/*     */ 
/*     */         
/* 395 */         class_5250 typesLine = class_2561.method_43473().method_27661();
/* 396 */         for (ElementalType type : pokemon.getTypes()) {
/* 397 */           typesLine = typesLine.method_10852((class_2561)CobblemonFontIcon.getTypeIcon(type.getName()).draw()).method_10852((class_2561)class_2561.method_43470(" "));
/*     */         }
/* 399 */         tooltip.add(typesLine);
/* 400 */         tooltip.add(class_2561.method_43473());
/*     */ 
/*     */         
/* 403 */         String ballName = pokemon.getCaughtBall().getName().toString();
/*     */         try {
/* 405 */           PokeBall ball = getPokeBallById(class_2960.method_60654(ballName));
/* 406 */           if (ball != null) {
/* 407 */             tooltip.add(class_2561.method_43470("Caught Ball: ").method_27692(class_124.field_1080)
/* 408 */                 .method_10852((class_2561)(new class_1799((class_1935)ball.item())).method_7964().method_27661().method_27692(class_124.field_1068)));
/*     */           }
/* 410 */         } catch (Exception e) {
/* 411 */           String fb = ballName.contains(":") ? ballName.substring(ballName.indexOf(":") + 1) : ballName;
/* 412 */           tooltip.add(class_2561.method_43470("Caught Ball: ").method_27692(class_124.field_1080)
/* 413 */               .method_10852((class_2561)class_2561.method_43470(capitalize(fb.replace("_", " "))).method_27692(class_124.field_1068)));
/*     */         } 
/*     */ 
/*     */         
/* 417 */         class_1799 heldItem = pokemon.heldItem();
/* 418 */         tooltip.add(class_2561.method_43470("Held Item: ").method_27692(class_124.field_1080)
/* 419 */             .method_10852((class_2561)class_2561.method_43470(heldItem.method_7960() ? "None" : heldItem.method_7964().getString()).method_27692(heldItem.method_7960() ? class_124.field_1063 : class_124.field_1068)));
/*     */ 
/*     */         
/* 422 */         String abilityName = pokemon.getAbility().getDisplayName();
/* 423 */         boolean hidden = hasHiddenAbility(pokemon);
/* 424 */         tooltip.add(class_2561.method_43470("Ability: ").method_27692(class_124.field_1080)
/* 425 */             .method_10852((class_2561)class_2561.method_43470(abilityName + abilityName).method_27692(class_124.field_1068)));
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 430 */         String natureName = (pokemon.getMintedNature() != null) ? (pokemon.getMintedNature().getDisplayName() + " (Minted)") : pokemon.getNature().getDisplayName();
/* 431 */         tooltip.add(class_2561.method_43470("Nature: ").method_27692(class_124.field_1080)
/* 432 */             .method_10852((class_2561)class_2561.method_43470(natureName).method_27692(class_124.field_1068)));
/*     */ 
/*     */         
/* 435 */         int ivHp = ((Integer)Objects.<Integer>requireNonNullElse(pokemon.getIvs().get((Stat)Stats.HP), Integer.valueOf(0))).intValue();
/* 436 */         int ivAtk = ((Integer)Objects.<Integer>requireNonNullElse(pokemon.getIvs().get((Stat)Stats.ATTACK), Integer.valueOf(0))).intValue();
/* 437 */         int ivDef = ((Integer)Objects.<Integer>requireNonNullElse(pokemon.getIvs().get((Stat)Stats.DEFENCE), Integer.valueOf(0))).intValue();
/* 438 */         int ivSpAtk = ((Integer)Objects.<Integer>requireNonNullElse(pokemon.getIvs().get((Stat)Stats.SPECIAL_ATTACK), Integer.valueOf(0))).intValue();
/* 439 */         int ivSpDef = ((Integer)Objects.<Integer>requireNonNullElse(pokemon.getIvs().get((Stat)Stats.SPECIAL_DEFENCE), Integer.valueOf(0))).intValue();
/* 440 */         int ivSpd = ((Integer)Objects.<Integer>requireNonNullElse(pokemon.getIvs().get((Stat)Stats.SPEED), Integer.valueOf(0))).intValue();
/* 441 */         int totalIVs = ivHp + ivAtk + ivDef + ivSpAtk + ivSpDef + ivSpd;
/* 442 */         double ivPct = totalIVs / 186.0D * 100.0D;
/*     */         
/* 444 */         tooltip.add(class_2561.method_43473());
/* 445 */         tooltip.add(class_2561.method_43470("IVs: ").method_27692(class_124.field_1080)
/* 446 */             .method_10852((class_2561)class_2561.method_43470("" + totalIVs).method_27692(class_124.field_1068))
/* 447 */             .method_10852((class_2561)class_2561.method_43470("/").method_27692(class_124.field_1080))
/* 448 */             .method_10852((class_2561)class_2561.method_43470("186").method_27692(class_124.field_1068))
/* 449 */             .method_10852((class_2561)class_2561.method_43470(String.format(" (%.0f%%)", new Object[] { Double.valueOf(ivPct) })).method_27692(class_124.field_1080)));
/*     */ 
/*     */ 
/*     */         
/* 453 */         int[][] ivStats = { { ivHp, STAT_HP }, { ivAtk, STAT_ATK }, { ivDef, STAT_DEF }, { ivSpAtk, STAT_SPATK }, { ivSpDef, STAT_SPDEF }, { ivSpd, STAT_SPD } };
/* 454 */         Stats[] statEnums = { Stats.HP, Stats.ATTACK, Stats.DEFENCE, Stats.SPECIAL_ATTACK, Stats.SPECIAL_DEFENCE, Stats.SPEED };
/* 455 */         class_5250 ivLine = class_2561.method_43473().method_27661();
/* 456 */         for (int si = 0; si < ivStats.length; si++) {
/* 457 */           int val = ivStats[si][0];
/* 458 */           int col = ivStats[si][1];
/*     */ 
/*     */ 
/*     */           
/* 462 */           ivLine = ivLine.method_10852((class_2561)CobblemonFontIcon.getStatIcon(statEnums[si].name()).draw()).method_10852((class_2561)class_2561.method_43470(" ")).method_10852((class_2561)class_2561.method_43470(String.valueOf(val)).method_54663((val == 31) ? ACCENT_GOLD : col)).method_10852((class_2561)class_2561.method_43470(" "));
/*     */         } 
/* 464 */         tooltip.add(ivLine);
/*     */ 
/*     */ 
/*     */         
/* 468 */         int evHp = ((Integer)Objects.<Integer>requireNonNullElse(pokemon.getEvs().get((Stat)Stats.HP), Integer.valueOf(0))).intValue();
/* 469 */         int evAtk = ((Integer)Objects.<Integer>requireNonNullElse(pokemon.getEvs().get((Stat)Stats.ATTACK), Integer.valueOf(0))).intValue();
/* 470 */         int evDef = ((Integer)Objects.<Integer>requireNonNullElse(pokemon.getEvs().get((Stat)Stats.DEFENCE), Integer.valueOf(0))).intValue();
/* 471 */         int evSpAtk = ((Integer)Objects.<Integer>requireNonNullElse(pokemon.getEvs().get((Stat)Stats.SPECIAL_ATTACK), Integer.valueOf(0))).intValue();
/* 472 */         int evSpDef = ((Integer)Objects.<Integer>requireNonNullElse(pokemon.getEvs().get((Stat)Stats.SPECIAL_DEFENCE), Integer.valueOf(0))).intValue();
/* 473 */         int evSpd = ((Integer)Objects.<Integer>requireNonNullElse(pokemon.getEvs().get((Stat)Stats.SPEED), Integer.valueOf(0))).intValue();
/* 474 */         int totalEVs = evHp + evAtk + evDef + evSpAtk + evSpDef + evSpd;
/* 475 */         double evPct = totalEVs / 510.0D * 100.0D;
/*     */         
/* 477 */         tooltip.add(class_2561.method_43473());
/* 478 */         tooltip.add(class_2561.method_43470("EVs: ").method_27692(class_124.field_1080)
/* 479 */             .method_10852((class_2561)class_2561.method_43470("" + totalEVs).method_27692(class_124.field_1068))
/* 480 */             .method_10852((class_2561)class_2561.method_43470("/").method_27692(class_124.field_1080))
/* 481 */             .method_10852((class_2561)class_2561.method_43470("510").method_27692(class_124.field_1068))
/* 482 */             .method_10852((class_2561)class_2561.method_43470(String.format(" (%.0f%%)", new Object[] { Double.valueOf(evPct) })).method_27692(class_124.field_1080)));
/*     */ 
/*     */ 
/*     */         
/* 486 */         int[] evVals = { evHp, evAtk, evDef, evSpAtk, evSpDef, evSpd };
/* 487 */         int[] evCols = { STAT_HP, STAT_ATK, STAT_DEF, STAT_SPATK, STAT_SPDEF, STAT_SPD };
/* 488 */         Stats[] arrayOfStats1 = { Stats.HP, Stats.ATTACK, Stats.DEFENCE, Stats.SPECIAL_ATTACK, Stats.SPECIAL_DEFENCE, Stats.SPEED };
/* 489 */         class_5250 evLine = class_2561.method_43473().method_27661();
/* 490 */         for (int i = 0; i < evVals.length; i++)
/*     */         {
/*     */ 
/*     */           
/* 494 */           evLine = evLine.method_10852((class_2561)CobblemonFontIcon.getStatIcon(arrayOfStats1[i].name()).draw()).method_10852((class_2561)class_2561.method_43470(" ")).method_10852((class_2561)class_2561.method_43470(String.valueOf(evVals[i])).method_54663(evCols[i])).method_10852((class_2561)class_2561.method_43470(" "));
/*     */         }
/* 496 */         tooltip.add(evLine);
/*     */ 
/*     */ 
/*     */         
/* 500 */         List<Move> moves = pokemon.getMoveSet().getMoves();
/* 501 */         if (!moves.isEmpty()) {
/* 502 */           tooltip.add(class_2561.method_43473());
/* 503 */           tooltip.add(class_2561.method_43470("Moves:").method_27692(class_124.field_1054));
/* 504 */           class_5250 moveComponent = class_2561.method_43473().method_27661();
/* 505 */           for (int idx = 0; idx < moves.size(); idx++) {
/* 506 */             Move move = moves.get(idx);
/* 507 */             boolean isLast = (idx == moves.size() - 1);
/*     */             
/* 509 */             moveComponent = moveComponent.method_10852((class_2561)class_2561.method_43470(move.getDisplayName().getString()).method_27692(class_124.field_1068)).method_10852(isLast ? (class_2561)class_2561.method_43473() : (class_2561)class_2561.method_43470(", ").method_27692(class_124.field_1080));
/*     */           } 
/* 511 */           tooltip.add(moveComponent);
/*     */         } 
/*     */       } else {
/*     */         
/* 515 */         tooltip.add(class_2561.method_43470(entry.speciesName()).method_27695(new class_124[] { class_124.field_1067, class_124.field_1068 }));
/* 516 */         tooltip.add(class_2561.method_43470("Lv. " + entry.level()).method_27692(class_124.field_1080));
/* 517 */         if (entry.shiny()) tooltip.add(class_2561.method_43470("✦ Shiny").method_27692(class_124.field_1065));
/*     */       
/*     */       } 
/* 520 */       this.tooltipCache.put(Integer.valueOf(entry.slot()), new TooltipCacheEntry(key, now + 500L, tooltip));
/*     */     } 
/*     */     
/* 523 */     ctx.method_51437(this.field_22793, tooltip, Optional.empty(), mx, my);
/*     */   }
/*     */   
/*     */   private static boolean hasHiddenAbility(Pokemon pokemon) {
/* 527 */     AbilityPool abilities = pokemon.getForm().getAbilities();
/* 528 */     for (PotentialAbility ability : abilities) {
/* 529 */       if (ability instanceof HiddenAbility) { HiddenAbility ha = (HiddenAbility)ability; if (ha.getTemplate() == pokemon.getAbility().getTemplate())
/* 530 */           return true;  }
/*     */     
/* 532 */     }  return false;
/*     */   }
/*     */   
/*     */   private PokeBall getPokeBallById(class_2960 id) {
/*     */     
/* 537 */     try { Method m = PokeBalls.class.getMethod("getPokeBall", new Class[] { class_2960.class });
/* 538 */       return Modifier.isStatic(m.getModifiers()) ? (PokeBall)m.invoke(null, new Object[] { id }) : (PokeBall)m.invoke(PokeBalls.INSTANCE, new Object[] { id }); }
/* 539 */     catch (Exception e) { return null; }
/*     */   
/*     */   }
/*     */   private static String capitalize(String str) {
/* 543 */     if (str == null || str.isEmpty()) return str; 
/* 544 */     StringBuilder sb = new StringBuilder();
/* 545 */     for (String w : str.split(" ")) {
/* 546 */       if (!w.isEmpty()) sb.append(Character.toUpperCase(w.charAt(0))).append(w.substring(1).toLowerCase()).append(" "); 
/*     */     } 
/* 548 */     return sb.toString().trim();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawBottomButtons(class_332 ctx, PlayerRankedData data, int mouseX, int mouseY) {
/* 554 */     int btnY = this.guiTop + 272 - 24;
/* 555 */     int btnH = 18;
/*     */ 
/*     */     
/* 558 */     int backW = this.field_22793.method_1727("◀ Back") + 14;
/* 559 */     this.backHovered = drawButton(ctx, this.guiLeft + 6, btnY, backW, btnH, "◀ Back", mouseX, mouseY, true);
/*     */ 
/*     */     
/* 562 */     boolean locked = data.isDefenseLockedIn();
/* 563 */     boolean canEdit = (data.getDefenseEditCooldownRemaining() <= 0L || !locked);
/* 564 */     String lockText = locked ? "Update Team" : "Lock In";
/* 565 */     int lockBtnW = this.field_22793.method_1727(lockText) + 14;
/* 566 */     int lockBtnX = this.guiLeft + (256 - lockBtnW) / 2;
/* 567 */     if (locked) {
/* 568 */       this.unlockHovered = drawButton(ctx, lockBtnX, btnY, lockBtnW, btnH, lockText, mouseX, mouseY, canEdit);
/*     */     } else {
/* 570 */       this.lockInHovered = drawButton(ctx, lockBtnX, btnY, lockBtnW, btnH, lockText, mouseX, mouseY, true);
/*     */     } 
/*     */ 
/*     */     
/* 574 */     int closeW = 40;
/* 575 */     this.closeHovered = drawButton(ctx, this.guiLeft + 256 - closeW - 6, btnY, closeW, btnH, "Close", mouseX, mouseY, true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawPopupOverlay(class_332 ctx, int mouseX, int mouseY) {
/* 581 */     class_4587 matrices = ctx.method_51448();
/* 582 */     matrices.method_22903();
/* 583 */     matrices.method_46416(0.0F, 0.0F, 400.0F);
/* 584 */     ctx.method_25294(0, 0, this.field_22789, this.field_22790, c(0, 0, 0, 150));
/*     */     
/* 586 */     int popupW = 220;
/* 587 */     int popupH = 70;
/* 588 */     int popupX = (this.field_22789 - popupW) / 2;
/* 589 */     int popupY = (this.field_22790 - popupH) / 2;
/*     */     
/* 591 */     ctx.method_25294(popupX, popupY, popupX + popupW, popupY + popupH, c(14, 16, 26, 255));
/* 592 */     ctx.method_25294(popupX, popupY, popupX + popupW, popupY + 2, ACCENT_RED);
/* 593 */     ctx.method_25294(popupX, popupY + popupH - 2, popupX + popupW, popupY + popupH, ACCENT_RED);
/* 594 */     ctx.method_25294(popupX, popupY, popupX + 2, popupY + popupH, ACCENT_RED);
/* 595 */     ctx.method_25294(popupX + popupW - 2, popupY, popupX + popupW, popupY + popupH, ACCENT_RED);
/*     */     
/* 597 */     ctx.method_25300(this.field_22793, this.popupMessage, popupX + popupW / 2, popupY + 14, ACCENT_RED);
/*     */     
/* 599 */     int btnW = 60, btnH = 16;
/* 600 */     int btnX = popupX + (popupW - btnW) / 2;
/* 601 */     int btnY = popupY + popupH - 24;
/* 602 */     this.popupOkHovered = (mouseX >= btnX && mouseX < btnX + btnW && mouseY >= btnY && mouseY < btnY + btnH);
/* 603 */     ctx.method_25294(btnX, btnY, btnX + btnW, btnY + btnH, this.popupOkHovered ? c(255, 80, 80, 255) : ACCENT_RED);
/* 604 */     ctx.method_25300(this.field_22793, "OK", btnX + btnW / 2, btnY + 4, TEXT_PRIMARY);
/*     */     
/* 606 */     matrices.method_22909();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean drawButton(class_332 ctx, int x, int y, int w, int h, String text, int mx, int my, boolean en) {
/* 612 */     boolean hov = (en && mx >= x && mx < x + w && my >= y && my < y + h);
/* 613 */     int bg = !en ? RankedStyledButton.getButtonDisabled() : (hov ? RankedStyledButton.getButtonHover() : RankedStyledButton.getButtonBg());
/* 614 */     ctx.method_25294(x + 1, y + 1, x + w - 1, y + h - 1, bg);
/* 615 */     if (en) ctx.method_25294(x + 1, y + h - 3, x + w - 1, y + h - 1, RankedStyledButton.darken(bg, 15)); 
/* 616 */     int bc = hov ? RankedStyledButton.getButtonBorderHover() : RankedStyledButton.getButtonBorder();
/* 617 */     if (!en) bc = c(30, 35, 50, 255); 
/* 618 */     ctx.method_25294(x + 1, y, x + w - 1, y + 1, bc);
/* 619 */     ctx.method_25294(x + 1, y + h - 1, x + w - 1, y + h, RankedStyledButton.darken(bc, 30));
/* 620 */     ctx.method_25294(x, y + 1, x + 1, y + h - 1, bc);
/* 621 */     ctx.method_25294(x + w - 1, y + 1, x + w, y + h - 1, RankedStyledButton.darken(bc, 30));
/* 622 */     if (en) {
/* 623 */       ctx.method_25294(x + 2, y + 1, x + w - 2, y + 2, RankedStyledButton.lighten(bg, 20));
/* 624 */       ctx.method_25294(x + 1, y + 2, x + 2, y + h - 3, RankedStyledButton.lighten(bg, 10));
/*     */     } 
/* 626 */     ctx.method_25294(x, y, x + 1, y + 1, 0); ctx.method_25294(x + w - 1, y, x + w, y + 1, 0);
/* 627 */     ctx.method_25294(x, y + h - 1, x + 1, y + h, 0); ctx.method_25294(x + w - 1, y + h - 1, x + w, y + h, 0);
/* 628 */     ctx.method_25300(this.field_22793, text, x + w / 2, y + (h - 8) / 2, 
/* 629 */         en ? RankedStyledButton.getTextPrimary() : RankedStyledButton.getTextDim());
/* 630 */     return hov;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean method_25402(double mouseX, double mouseY, int button) {
/* 637 */     if (button == 0) {
/* 638 */       if (this.showPopup) {
/* 639 */         if (this.popupOkHovered) { playClickSound(); this.showPopup = false; return true; }
/* 640 */          return true;
/*     */       } 
/* 642 */       if (this.lockInHovered) {
/* 643 */         playClickSound();
/* 644 */         RankedNetwork.sendLockInDefense();
/* 645 */         return true;
/*     */       } 
/* 647 */       if (this.unlockHovered) {
/* 648 */         playClickSound();
/* 649 */         PlayerRankedData data2 = PlayerRankedData.getInstance();
/* 650 */         long cooldownRemaining = data2.getDefenseEditCooldownRemaining();
/* 651 */         if (cooldownRemaining > 0L) {
/* 652 */           long minutes = cooldownRemaining / 60000L;
/* 653 */           long hours = minutes / 60L;
/* 654 */           this
/*     */             
/* 656 */             .popupMessage = (hours > 0L) ? ("Can't edit for " + hours + "h " + minutes % 60L + "m") : ("Can't edit for " + minutes + "m");
/* 657 */           this.showPopup = true;
/*     */         } else {
/* 659 */           RankedNetwork.sendUnlockDefense();
/*     */         } 
/* 661 */         return true;
/*     */       } 
/* 663 */       if (this.backHovered) {
/* 664 */         playClickSound();
/* 665 */         class_310.method_1551().method_1507(new RankedScreen());
/* 666 */         return true;
/*     */       } 
/* 668 */       if (this.closeHovered) { playClickSound(); method_25419(); return true; }
/*     */     
/* 670 */     }  return super.method_25402(mouseX, mouseY, button);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean method_25404(int keyCode, int scanCode, int modifiers) {
/* 675 */     if (keyCode == 256) {
/* 676 */       if (this.showPopup) { this.showPopup = false; return true; }
/* 677 */        method_25419();
/* 678 */       return true;
/*     */     } 
/* 680 */     return super.method_25404(keyCode, scanCode, modifiers);
/*     */   }
/*     */   
/*     */   private void playClickSound() {
/* 684 */     class_310.method_1551().method_1483().method_4873((class_1113)class_1109.method_47978((class_6880)class_3417.field_15015, 1.0F));
/*     */   }
/*     */   public void method_25420(class_332 context, int mouseX, int mouseY, float delta) {}
/*     */   public boolean method_25421() {
/* 688 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\ranked\screen\RankedDefenseScreen.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */