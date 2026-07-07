/*     */ package com.atlas.common.fabric.battle.ui;
/*     */ 
/*     */ import com.cobblemon.mod.common.api.gui.GuiUtilsKt;
/*     */ import com.cobblemon.mod.common.api.types.ElementalType;
/*     */ import com.cobblemon.mod.common.api.types.ElementalTypes;
/*     */ import com.cobblemon.mod.common.client.CobblemonClient;
/*     */ import com.cobblemon.mod.common.client.CobblemonResources;
/*     */ import com.cobblemon.mod.common.client.battle.ClientBattle;
/*     */ import com.cobblemon.mod.common.client.battle.ClientBattleActor;
/*     */ import com.cobblemon.mod.common.client.battle.ClientBattlePokemon;
/*     */ import com.cobblemon.mod.common.client.battle.ClientBattleSide;
/*     */ import com.cobblemon.mod.common.client.battle.SingleActionRequest;
/*     */ import com.cobblemon.mod.common.client.gui.TypeIcon;
/*     */ import com.cobblemon.mod.common.client.render.RenderHelperKt;
/*     */ import com.cobblemon.mod.common.client.render.models.blockbench.FloatingState;
/*     */ import com.cobblemon.mod.common.client.render.models.blockbench.PosableState;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import java.util.UUID;
/*     */ import kotlin.Pair;
/*     */ import net.fabricmc.api.EnvType;
/*     */ import net.fabricmc.api.Environment;
/*     */ import net.minecraft.class_124;
/*     */ import net.minecraft.class_2561;
/*     */ import net.minecraft.class_2960;
/*     */ import net.minecraft.class_310;
/*     */ import net.minecraft.class_327;
/*     */ import net.minecraft.class_332;
/*     */ import net.minecraft.class_3532;
/*     */ import net.minecraft.class_4587;
/*     */ import net.minecraft.class_5250;
/*     */ 
/*     */ @Environment(EnvType.CLIENT)
/*     */ public final class BattleUiOverlayRenderer {
/*     */   private static final String NS = "atlas-common-fabric";
/*     */   private static final int ACTIVE_TILE_WIDTH = 140;
/*     */   private static final int ACTIVE_TILE_HEIGHT = 40;
/*     */   private static final int ACTIVE_TILE_GAP = 2;
/*     */   private static final int PORTRAIT_DIAMETER = 28;
/*     */   private static final float PARTY_TILE_SCALE = 0.65F;
/*     */   private static final int PARTY_TILE_SOURCE_W = 41;
/*     */   private static final int PARTY_TILE_SOURCE_H = 39;
/*  48 */   private static final int PARTY_TILE_W = Math.round(26.65F);
/*  49 */   private static final int PARTY_TILE_H = Math.round(25.349998F);
/*     */   
/*     */   private static final int HORIZONTAL_INSET = 12;
/*     */   private static final int VERTICAL_INSET = 22;
/*  53 */   private static final class_2960 TURN_COUNTER = tex("turn_counter.png");
/*  54 */   private static final class_2960 BATTLE_INFO_BASE = tex("battle_info_base.png");
/*  55 */   private static final class_2960 BATTLE_INFO_BASE_FLIPPED = tex("battle_info_base_flipped.png");
/*  56 */   private static final class_2960 BATTLE_INFO_UNDERLAY = tex("battle_info_underlay.png");
/*  57 */   private static final class_2960 PARTY_TILE = tex("pokemon_tile.png");
/*  58 */   private static final class_2960 PARTY_TILE_REVERSED = tex("pokemon_tile_reversed.png");
/*  59 */   private static final class_2960 PARTY_TILE_DISABLED = tex("pokemon_tile_disabled.png");
/*  60 */   private static final class_2960 PARTY_TILE_DISABLED_REVERSED = tex("pokemon_tile_disabled_reversed.png");
/*  61 */   private static final class_2960 PARTY_TILE_SELECTED = tex("pokemon_tile_selected.png");
/*  62 */   private static final class_2960 PARTY_TILE_SELECTED_REVERSED = tex("pokemon_tile_selected_reversed.png");
/*  63 */   private static final class_2960 HEALTH_BAR = tex("health_bar.png");
/*  64 */   private static final class_2960 HEALTH_BAR_FLIPPED = tex("health_bar_flipped.png");
/*  65 */   private static final class_2960 EXPANDED_INACTIVE = tex("expanded_pokemon_info_center_inactive.png");
/*  66 */   private static final class_2960 EXPANDED_LEFT = tex("expanded_pokemon_info_left.png");
/*  67 */   private static final class_2960 EXPANDED_RIGHT = tex("expanded_pokemon_info_right.png");
/*  68 */   private static final class_2960 QUESTION_MARK = tex("question_mark.png");
/*     */   
/*  70 */   private static final Map<UUID, FloatingState> PORTRAIT_STATES = new HashMap<>();
/*  71 */   private static final Map<UUID, HpAnimation> HP_ANIMATIONS = new HashMap<>();
/*     */ 
/*     */   
/*     */   private static final long HP_ANIMATION_MILLIS = 650L;
/*     */ 
/*     */   
/*     */   public static void render(class_332 context, int mouseX, int mouseY) {
/*  78 */     class_310 client = class_310.method_1551();
/*  79 */     if (client.field_1724 == null)
/*  80 */       return;  ClientBattle battle = CobblemonClient.INSTANCE.getBattle();
/*  81 */     if (battle == null || battle.getMinimised())
/*     */       return; 
/*  83 */     Optional<BattleUiClientState.BattleState> maybeState = BattleUiClientState.currentBattle();
/*  84 */     if (maybeState.isEmpty())
/*     */       return; 
/*  86 */     BattleUiClientState.BattleState state = maybeState.get();
/*  87 */     boolean leftSideOne = leftIsSideOne(battle, client.field_1724.method_5667());
/*  88 */     int screenWidth = context.method_51421();
/*  89 */     UUID selectedActivePokemonId = selectedActivePokemonId(battle);
/*     */     
/*  91 */     drawTurnCounter(context, client.field_1772, state, screenWidth);
/*  92 */     drawFieldIcons(context, state.info(), screenWidth);
/*     */     
/*  94 */     Hover hover = null;
/*  95 */     hover = drawSide(context, client.field_1772, state.side(leftSideOne), 12, 22, false, selectedActivePokemonId, mouseX, mouseY);
/*     */     
/*  97 */     Hover rightHover = drawSide(context, client.field_1772, state.side(!leftSideOne), screenWidth - 12 - 140, 22, true, selectedActivePokemonId, mouseX, mouseY);
/*     */     
/*  99 */     if (rightHover != null) hover = rightHover;
/*     */     
/* 101 */     if (hover != null) {
/* 102 */       drawHoverPanel(context, client.field_1772, hover);
/*     */     }
/*     */   }
/*     */   
/*     */   private static boolean leftIsSideOne(ClientBattle battle, UUID playerId) {
/* 107 */     if (battle.getSpectating()) return true; 
/* 108 */     if (containsPlayer(battle.getSide1(), playerId)) return true; 
/* 109 */     if (containsPlayer(battle.getSide2(), playerId)) return false; 
/* 110 */     return true;
/*     */   }
/*     */   
/*     */   private static boolean containsPlayer(ClientBattleSide side, UUID playerId) {
/* 114 */     for (ClientBattleActor actor : side.getActors()) {
/* 115 */       if (actor.getUuid().equals(playerId)) return true; 
/*     */     } 
/* 117 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static Hover drawSide(class_332 context, class_327 textRenderer, List<BattleUiNetwork.TeamPayload> teams, int tileX, int tileY, boolean reversed, UUID selectedActivePokemonId, int mouseX, int mouseY) {
/* 123 */     Hover hover = null;
/* 124 */     int cursorY = tileY;
/* 125 */     for (BattleUiNetwork.TeamPayload team : teams) {
/* 126 */       List<BattleUiNetwork.PokemonEntry> activePokemon = activePokemon(team);
/* 127 */       int activeRows = Math.max(1, activePokemon.size());
/*     */       
/* 129 */       for (int activeIndex = 0; activeIndex < activePokemon.size(); activeIndex++) {
/* 130 */         BattleUiNetwork.PokemonEntry active = activePokemon.get(activeIndex);
/* 131 */         int activeTileY = cursorY + activeIndex * 42;
/* 132 */         boolean selected = active.pokemonId().equals(selectedActivePokemonId);
/* 133 */         drawActiveTile(context, textRenderer, active, tileX, activeTileY, reversed, selected);
/*     */ 
/*     */         
/* 136 */         int i = reversed ? (tileX + 140 - 5 - 28) : (tileX + 5);
/* 137 */         int portraitY = activeTileY + 8;
/* 138 */         if (mouseX >= i && mouseX <= i + 28 && mouseY >= portraitY && mouseY <= portraitY + 28)
/*     */         {
/* 140 */           hover = new Hover(active, i, portraitY, 28, true, reversed);
/*     */         }
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 146 */       int portraitX = reversed ? (tileX + 140 - 5 - 28) : (tileX + 5);
/* 147 */       int columnX = portraitX + (28 - PARTY_TILE_W) / 2;
/* 148 */       int columnY = cursorY + activeRows * 42 - 2 + 3;
/* 149 */       int benchedIndex = 0;
/* 150 */       for (BattleUiNetwork.PokemonEntry pokemon : team.pokemon()) {
/* 151 */         if (pokemon.active())
/* 152 */           continue;  int y = columnY + benchedIndex * (PARTY_TILE_H + 1);
/* 153 */         boolean hovered = (mouseX >= columnX && mouseX <= columnX + PARTY_TILE_W && mouseY >= y && mouseY <= y + PARTY_TILE_H);
/*     */         
/* 155 */         drawPartyTile(context, pokemon, columnX, y, reversed, hovered);
/* 156 */         if (hovered) {
/* 157 */           hover = new Hover(pokemon, columnX, y, PARTY_TILE_W, false, reversed);
/*     */         }
/* 159 */         benchedIndex++;
/*     */       } 
/*     */       
/* 162 */       int activeBlockHeight = activeRows * 42 - 2;
/* 163 */       int benchBlockHeight = (benchedIndex <= 0) ? 0 : (3 + benchedIndex * (PARTY_TILE_H + 1) - 1);
/* 164 */       cursorY += Math.max(80, activeBlockHeight + benchBlockHeight + 12);
/*     */     } 
/* 166 */     return hover;
/*     */   }
/*     */   
/*     */   private static List<BattleUiNetwork.PokemonEntry> activePokemon(BattleUiNetwork.TeamPayload team) {
/* 170 */     List<BattleUiNetwork.PokemonEntry> activePokemon = new ArrayList<>();
/* 171 */     for (BattleUiNetwork.PokemonEntry pokemon : team.pokemon()) {
/* 172 */       if (pokemon.active()) activePokemon.add(pokemon); 
/*     */     } 
/* 174 */     return activePokemon;
/*     */   }
/*     */ 
/*     */   
/*     */   private static void drawActiveTile(class_332 context, class_327 textRenderer, BattleUiNetwork.PokemonEntry pokemon, int x, int y, boolean reversed, boolean selected) {
/* 179 */     if (selected) {
/* 180 */       int color = -788551168;
/* 181 */       context.method_25294(x - 1, y - 1, x + 140 + 1, y, color);
/* 182 */       context.method_25294(x - 1, y + 40, x + 140 + 1, y + 40 + 1, color);
/* 183 */       context.method_25294(x - 1, y, x, y + 40, color);
/* 184 */       context.method_25294(x + 140, y, x + 140 + 1, y + 40, color);
/*     */     } 
/*     */     
/* 187 */     int portraitX = reversed ? (x + 140 - 5 - 28) : (x + 5);
/* 188 */     int portraitY = y + 8;
/* 189 */     drawTexture(context, BATTLE_INFO_UNDERLAY, portraitX, portraitY, 28, 28, 28, 28);
/* 190 */     drawPokemonPortrait(context, pokemon, portraitX, portraitY, 28, reversed, -5.0F);
/*     */     
/* 192 */     drawTexture(context, reversed ? BATTLE_INFO_BASE_FLIPPED : BATTLE_INFO_BASE, x, y, 140, 40, 140, 40);
/*     */ 
/*     */     
/* 195 */     int infoX = x + (!reversed ? 40 : 7);
/* 196 */     String name = trim(textRenderer, cleanPokemonName(pokemon.displayName()), 62);
/* 197 */     drawCobbleText(context, class_2561.method_43470(name).method_27692(class_124.field_1067), infoX, (y + 7), 0.0F, 16777215, false);
/* 198 */     drawGender(context, pokemon.gender(), (infoX + 63), (y + 7));
/* 199 */     drawCobbleText(context, class_2561.method_43470("Lv.").method_27692(class_124.field_1067), (infoX + 69), (y + 7), 0.0F, 16777215, false);
/* 200 */     drawCobbleText(context, class_2561.method_43470(String.valueOf(pokemon.level())).method_27692(class_124.field_1067), (infoX + 82), (y + 7), 0.0F, 16777215, false);
/*     */     
/* 202 */     float hpRatio = animatedHpRatio(pokemon);
/* 203 */     drawHealthBar(context, infoX - 2, y + 22, 99, 6, hpRatio, reversed);
/* 204 */     drawUiText(context, class_2561.method_43470("" + Math.round(hpRatio * 100.0F) + "%"), infoX + (reversed ? 44.5F : 39.5F), (y + 23), 0.5F, 16777215, true);
/*     */   }
/*     */   
/*     */   private static UUID selectedActivePokemonId(ClientBattle battle) {
/* 208 */     SingleActionRequest request = battle.getFirstUnansweredRequest();
/* 209 */     if (request == null || request.getActivePokemon() == null) return null; 
/* 210 */     ClientBattlePokemon battlePokemon = request.getActivePokemon().getBattlePokemon();
/* 211 */     return (battlePokemon == null) ? null : battlePokemon.getUuid();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void drawPartyTile(class_332 context, BattleUiNetwork.PokemonEntry pokemon, int x, int y, boolean reversed, boolean hovered) {
/* 219 */     class_2960 frame = pokemon.fainted() ? (reversed ? PARTY_TILE_DISABLED_REVERSED : PARTY_TILE_DISABLED) : (hovered ? (reversed ? PARTY_TILE_SELECTED_REVERSED : PARTY_TILE_SELECTED) : (reversed ? PARTY_TILE_REVERSED : PARTY_TILE));
/*     */     
/* 221 */     drawPartyPokemonPortrait(context, pokemon, x, y, reversed);
/* 222 */     drawTexture(context, frame, x, y, PARTY_TILE_W, PARTY_TILE_H, 41, 39);
/*     */     
/* 224 */     if (!pokemon.status().isBlank() && !pokemon.fainted()) {
/* 225 */       String status = pokemon.status().toUpperCase(Locale.ROOT);
/* 226 */       drawUiText(context, class_2561.method_43470(status).method_27692(class_124.field_1067), (x + 4), (y + PARTY_TILE_H - 8), 0.5F, 16777215, true);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static void drawPokemonPortrait(class_332 context, BattleUiNetwork.PokemonEntry pokemon, int x, int y, int size, boolean reversed) {
/* 232 */     drawPokemonPortrait(context, pokemon, x, y, size, reversed, -5.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   private static void drawPokemonPortrait(class_332 context, BattleUiNetwork.PokemonEntry pokemon, int x, int y, int size, boolean reversed, float yOffset) {
/* 237 */     class_2960 species = parseIdentifier(pokemon.speciesId());
/* 238 */     if (species == null) {
/* 239 */       drawTexture(context, QUESTION_MARK, x, y, size, size, 16, 16);
/*     */       
/*     */       return;
/*     */     } 
/* 243 */     FloatingState state = PORTRAIT_STATES.computeIfAbsent(pokemon.pokemonId(), ignored -> new FloatingState());
/* 244 */     state.setCurrentAspects(new HashSet<>(pokemon.aspects()));
/* 245 */     class_4587 matrices = context.method_51448();
/* 246 */     matrices.method_22903();
/* 247 */     context.method_44379(x, y, x + size, y + size);
/* 248 */     float relative = size / 28.0F;
/* 249 */     matrices.method_46416(x + size / 2.0F, y + yOffset * relative, 80.0F);
/* 250 */     GuiUtilsKt.drawPosablePortrait(species, matrices, 18.0F * relative, 1.0F, reversed, (PosableState)state, 0.0F);
/* 251 */     context.method_44380();
/* 252 */     matrices.method_22909();
/*     */   }
/*     */ 
/*     */   
/*     */   private static void drawPartyPokemonPortrait(class_332 context, BattleUiNetwork.PokemonEntry pokemon, int x, int y, boolean reversed) {
/* 257 */     class_2960 species = parseIdentifier(pokemon.speciesId());
/* 258 */     if (species == null) {
/* 259 */       int fallbackX = x + Math.round((reversed ? 8 : 5) * 0.65F);
/* 260 */       int fallbackY = y + Math.round(2.6F);
/* 261 */       int fallbackSize = Math.round(18.199999F);
/* 262 */       drawTexture(context, QUESTION_MARK, fallbackX, fallbackY, fallbackSize, fallbackSize, 16, 16);
/*     */       
/*     */       return;
/*     */     } 
/* 266 */     FloatingState state = PORTRAIT_STATES.computeIfAbsent(pokemon.pokemonId(), ignored -> new FloatingState());
/* 267 */     state.setCurrentAspects(new HashSet<>(pokemon.aspects()));
/*     */     
/* 269 */     int insetX = 5 + (reversed ? 3 : 0);
/* 270 */     class_4587 matrices = context.method_51448();
/* 271 */     matrices.method_22903();
/* 272 */     matrices.method_46416(x, y, 0.0F);
/* 273 */     matrices.method_22905(0.65F, 0.65F, 0.65F);
/* 274 */     drawTexture(context, BATTLE_INFO_UNDERLAY, insetX, 4, 28, 28, 28, 28);
/* 275 */     matrices.method_22909();
/*     */     
/* 277 */     context.method_44379(
/* 278 */         Math.round(x + insetX * 0.65F), 
/* 279 */         Math.round(y + 3.25F), 
/* 280 */         Math.round(x + (insetX + 28) * 0.65F), 
/* 281 */         Math.round(y + 21.449999F));
/*     */     
/* 283 */     matrices.method_22903();
/* 284 */     matrices.method_46416(x, y, 80.0F);
/* 285 */     matrices.method_22905(0.65F, 0.65F, 0.65F);
/* 286 */     matrices.method_46416(insetX + 14.0F, 0.0F, 0.0F);
/* 287 */     GuiUtilsKt.drawPosablePortrait(species, matrices, 18.0F, 1.0F, reversed, (PosableState)state, 0.0F);
/* 288 */     matrices.method_22909();
/* 289 */     context.method_44380();
/*     */   }
/*     */   
/*     */   private static void drawHoverPanel(class_332 context, class_327 textRenderer, Hover hover) {
/* 293 */     class_4587 matrices = context.method_51448();
/* 294 */     matrices.method_22903();
/* 295 */     matrices.method_46416(0.0F, 0.0F, 250.0F);
/* 296 */     if (hover.active()) {
/* 297 */       drawActiveHoverPanel(context, textRenderer, hover);
/*     */     } else {
/* 299 */       drawBenchHoverPanel(context, textRenderer, hover);
/*     */     } 
/* 301 */     matrices.method_22909();
/*     */     
/* 303 */     if (!hover.active()) {
/* 304 */       matrices.method_22903();
/* 305 */       matrices.method_46416(0.0F, 0.0F, 350.0F);
/* 306 */       drawPartyTile(context, hover.pokemon(), hover.x(), hover.y(), hover.reversed(), true);
/* 307 */       matrices.method_22909();
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void drawBenchHoverPanel(class_332 context, class_327 textRenderer, Hover hover) {
/* 312 */     BattleUiNetwork.PokemonEntry pokemon = hover.pokemon();
/* 313 */     int panelW = 137;
/* 314 */     int panelH = 112;
/* 315 */     int screenH = context.method_51443();
/* 316 */     int x = hover.reversed() ? (hover.x() - 136) : (hover.x() + hover.width() - 1);
/* 317 */     int y = class_3532.method_15340(hover.y(), 4, Math.max(4, screenH - panelH - 4));
/*     */     
/* 319 */     drawTexture(context, EXPANDED_INACTIVE, x, y, panelW, panelH, panelW, panelH);
/* 320 */     drawCommonHoverTop(context, pokemon, x, y, hover.reversed(), false);
/*     */     
/* 322 */     float hpRatio = animatedHpRatio(pokemon);
/* 323 */     drawUiText(context, label("Current HP"), (x + 36), y + 23.5F, 0.5F, 16777215, true);
/* 324 */     drawHoverHealthBar(context, x + 8, y + 30, 57, hpRatio);
/* 325 */     drawUiText(context, class_2561.method_43470("" + Math.round(hpRatio * pokemon.maxHp()) + "/" + Math.round(hpRatio * pokemon.maxHp()) + " (" + Math.round(pokemon.maxHp()) + "%)"), (x + 36), y + 31.0F, 0.5F, 16777215, true);
/*     */ 
/*     */     
/* 328 */     drawMovesAndSpeed(context, textRenderer, pokemon, x, y, 23.5F, 31.5F, 65.5F, 72.5F);
/* 329 */     drawHeldItemAndTypes(context, pokemon, x, y, 99.5F, 88);
/*     */   }
/*     */   
/*     */   private static void drawActiveHoverPanel(class_332 context, class_327 textRenderer, Hover hover) {
/* 333 */     BattleUiNetwork.PokemonEntry pokemon = hover.pokemon();
/* 334 */     int panelW = 137;
/* 335 */     int panelH = 123;
/* 336 */     int screenH = context.method_51443();
/* 337 */     int x = hover.x() - (hover.reversed() ? 103 : 6);
/* 338 */     int y = class_3532.method_15340(hover.y() + 20, 4, Math.max(4, screenH - panelH - 4));
/*     */     
/* 340 */     drawTexture(context, hover.reversed() ? EXPANDED_RIGHT : EXPANDED_LEFT, x, y, panelW, panelH, panelW, panelH);
/* 341 */     drawCommonHoverTop(context, pokemon, x, y, hover.reversed(), true);
/*     */     
/* 343 */     drawUiText(context, label("Buffs/Debuffs"), (x + 36), y + 34.5F, 0.5F, 16777215, true);
/* 344 */     drawStatRow(context, pokemon, "atk", "Attack", (x + 10), y + 42.5F, 16726579);
/* 345 */     drawStatRow(context, pokemon, "def", "Defense", (x + 10), y + 50.5F, 16744207);
/* 346 */     drawStatRow(context, pokemon, "spa", "Sp. Attack", (x + 10), y + 58.5F, 8790015);
/* 347 */     drawStatRow(context, pokemon, "spd", "Sp. Defense", (x + 10), y + 66.5F, 16773670);
/* 348 */     drawStatRow(context, pokemon, "spe", "Speed", (x + 10), y + 74.5F, 4581631);
/* 349 */     drawStatRow(context, pokemon, "accuracy", "Accuracy", (x + 10), y + 82.5F, 16777215);
/* 350 */     drawStatRow(context, pokemon, "evasion", "Evasion", (x + 10), y + 90.5F, 16777215);
/*     */     
/* 352 */     drawMovesAndSpeed(context, textRenderer, pokemon, x, y, 34.5F, 42.5F, 76.5F, 83.5F);
/* 353 */     drawHeldItemAndTypes(context, pokemon, x, y, 110.5F, 99);
/*     */   }
/*     */ 
/*     */   
/*     */   private static void drawCommonHoverTop(class_332 context, BattleUiNetwork.PokemonEntry pokemon, int x, int y, boolean reversed, boolean active) {
/* 358 */     drawUiText(context, label("Pokemon Form"), (x + 36), y + (active ? 17.5F : 6.5F), 0.5F, 16777215, true);
/* 359 */     drawUiText(context, class_2561.method_43470(formText(pokemon)), (x + 36), y + (active ? 24.5F : 13.5F), 0.5F, 16777215, true);
/* 360 */     drawUiText(context, label("Ability"), (x + 100), y + (active ? 17.5F : 6.5F), 0.5F, 16777215, true);
/* 361 */     drawUiText(context, class_2561.method_43470(pokemon.ability().isBlank() ? "?" : pokemon.ability()), (x + 100), y + (
/* 362 */         active ? 24.5F : 13.5F), 0.5F, 16777215, true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void drawMovesAndSpeed(class_332 context, class_327 textRenderer, BattleUiNetwork.PokemonEntry pokemon, int x, int y, float movesLabelY, float moveStartY, float speedLabelY, float speedValueY) {
/* 368 */     drawUiText(context, label("Moves"), (x + 100), y + movesLabelY, 0.5F, 16777215, true);
/* 369 */     for (int i = 0; i < 4; i++) {
/* 370 */       if (i >= pokemon.moves().size()) {
/* 371 */         drawUiText(context, class_2561.method_43470("?"), (x + 100), y + moveStartY + (i * 8), 0.5F, 16777215, true);
/*     */       } else {
/*     */         
/* 374 */         BattleUiNetwork.MoveEntry move = pokemon.moves().get(i);
/* 375 */         String name = trim(textRenderer, move.name(), 58);
/* 376 */         drawUiText(context, class_2561.method_43470(name), (x + 74), y + moveStartY + (i * 8), 0.5F, typeColor(move.type()), false);
/* 377 */         drawRightText(context, class_2561.method_43470(String.valueOf(move.timesUsed())), x + 127.5F, y + moveStartY + (i * 8), 0.5F, 16777215);
/*     */       } 
/*     */     } 
/* 380 */     drawUiText(context, class_2561.method_43470("Speed"), (x + 100), y + speedLabelY, 0.5F, 16777215, true);
/* 381 */     int speed = Math.max(0, Math.round(pokemon.speed() * statMultiplier(stageFor(pokemon, "spe"))));
/* 382 */     drawUiText(context, class_2561.method_43470(String.valueOf(speed)), (x + 100), y + speedValueY, 0.5F, 16777215, true);
/*     */   }
/*     */ 
/*     */   
/*     */   private static void drawHeldItemAndTypes(class_332 context, BattleUiNetwork.PokemonEntry pokemon, int x, int y, float heldLabelY, int typeY) {
/* 387 */     drawUiText(context, class_2561.method_43470("Held Item"), (x + 40), y + heldLabelY, 0.5F, 16777215, true);
/* 388 */     drawTexture(context, QUESTION_MARK, x + 6, Math.round(y + heldLabelY - 7.0F), 11, 10, 16, 16);
/*     */     
/* 390 */     ElementalType primary = type(pokemon.primaryType());
/* 391 */     ElementalType secondary = type(pokemon.secondaryType());
/* 392 */     if (primary != null) {
/* 393 */       (new TypeIcon(Float.valueOf(x + 100.5F), Integer.valueOf(y + typeY), primary, secondary, true, false, 0.0F, 0.0F, 1.0F)).render(context);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static void drawTurnCounter(class_332 context, class_327 textRenderer, BattleUiClientState.BattleState state, int screenWidth) {
/* 399 */     BattleUiNetwork.InfoPayload info = state.info();
/* 400 */     if (info == null)
/* 401 */       return;  int x = screenWidth / 2 - 41;
/* 402 */     drawTexture(context, TURN_COUNTER, x, 4, 83, 9, 83, 9);
/* 403 */     String label = "Turn " + Math.max(1, info.turn());
/* 404 */     RenderHelperKt.drawScaledText(context, null, 
/*     */ 
/*     */         
/* 407 */         class_2561.method_43470(label).method_27692(class_124.field_1067), 
/* 408 */         Float.valueOf(screenWidth / 2.0F), 
/* 409 */         Integer.valueOf(6), 0.75F, 
/*     */         
/* 411 */         Float.valueOf(1.0F), 2147483647, 16777215, true, true, null, null);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 420 */     int timerSeconds = state.remainingTurnTimerSeconds();
/* 421 */     if (timerSeconds <= 0)
/*     */       return; 
/* 423 */     drawTexture(context, TURN_COUNTER, x, 15, 83, 9, 83, 9);
/* 424 */     RenderHelperKt.drawScaledText(context, null, 
/*     */ 
/*     */         
/* 427 */         class_2561.method_43470(formatTimer(timerSeconds)).method_27692(class_124.field_1067), 
/* 428 */         Float.valueOf(screenWidth / 2.0F), 
/* 429 */         Integer.valueOf(17), 0.75F, 
/*     */         
/* 431 */         Float.valueOf(1.0F), 2147483647, 
/*     */         
/* 433 */         (timerSeconds <= 10) ? 16733525 : ((timerSeconds <= 30) ? 16755200 : 16777215), true, true, null, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String formatTimer(int seconds) {
/* 442 */     if (seconds >= 60) {
/* 443 */       return "" + seconds / 60 + ":" + seconds / 60;
/*     */     }
/* 445 */     return "" + seconds + "s";
/*     */   }
/*     */   
/*     */   private static void drawFieldIcons(class_332 context, BattleUiNetwork.InfoPayload info, int screenWidth) {
/* 449 */     if (info == null)
/* 450 */       return;  List<BattleUiNetwork.EffectEntry> effects = new ArrayList<>();
/* 451 */     if (info.weather().active()) effects.add(info.weather()); 
/* 452 */     if (info.terrain().active()) effects.add(info.terrain()); 
/* 453 */     if (info.room().active()) effects.add(info.room()); 
/* 454 */     int x = screenWidth / 2 + 48;
/* 455 */     for (BattleUiNetwork.EffectEntry effect : effects) {
/* 456 */       class_2960 icon = tex("effects/" + effect.id() + ".png");
/* 457 */       drawTexture(context, icon, x, 5, 16, 16, 16, 16);
/* 458 */       x += 18;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static void drawHealthBar(class_332 context, int x, int y, int width, int height, float hpRatio, boolean reversed) {
/* 464 */     float barWidth = class_3532.method_15363(width * hpRatio, 0.0F, width);
/* 465 */     if (barWidth <= 0.0F)
/*     */       return; 
/* 467 */     Pair<Float, Float> colors = RenderHelperKt.getDepletableRedGreen(hpRatio, 0.0F, 0.0F);
/* 468 */     float red = ((Float)colors.getFirst()).floatValue() * 0.8F;
/* 469 */     float green = ((Float)colors.getSecond()).floatValue() * 0.8F;
/*     */     
/* 471 */     if (reversed) {
/* 472 */       float barX = (x + width) - barWidth - 3.0F;
/* 473 */       GuiUtilsKt.blitk(context.method_51448(), HEALTH_BAR_FLIPPED, Float.valueOf(barX), Integer.valueOf(y), Integer.valueOf(height), Float.valueOf(Math.min(4.0F, barWidth)), 
/* 474 */           Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(99), Integer.valueOf(6), Integer.valueOf(0), Float.valueOf(red), Float.valueOf(green), Float.valueOf(0.27F), Float.valueOf(1.0F), false, 1.0F);
/* 475 */       GuiUtilsKt.blitk(context.method_51448(), HEALTH_BAR_FLIPPED, Float.valueOf(barX + 4.0F), Integer.valueOf(y), Integer.valueOf(height), 
/* 476 */           Float.valueOf(Math.max(barWidth - 3.0F, 0.0F)), Float.valueOf(Math.max(width - barWidth + 3.0F, 2.0F)), Integer.valueOf(0), Integer.valueOf(99), Integer.valueOf(6), 
/* 477 */           Integer.valueOf(0), Float.valueOf(red), Float.valueOf(green), Float.valueOf(0.27F), Float.valueOf(1.0F), false, 1.0F);
/*     */     } else {
/* 479 */       GuiUtilsKt.blitk(context.method_51448(), HEALTH_BAR, Integer.valueOf(x), Integer.valueOf(y), Integer.valueOf(height), Float.valueOf(Math.max(barWidth - 3.0F, 0.0F)), 
/* 480 */           Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(99), Integer.valueOf(6), Integer.valueOf(0), Float.valueOf(red), Float.valueOf(green), Float.valueOf(0.27F), Float.valueOf(1.0F), false, 1.0F);
/* 481 */       GuiUtilsKt.blitk(context.method_51448(), HEALTH_BAR, Float.valueOf(x + barWidth - 3.0F), Integer.valueOf(y), Integer.valueOf(height), Float.valueOf(Math.min(3.0F, barWidth)), 
/* 482 */           Integer.valueOf(width - 3), Integer.valueOf(0), Integer.valueOf(99), Integer.valueOf(6), Integer.valueOf(0), Float.valueOf(red), Float.valueOf(green), Float.valueOf(0.27F), Float.valueOf(1.0F), false, 1.0F);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void drawHoverHealthBar(class_332 context, int x, int y, int width, float hpRatio) {
/* 487 */     float barWidth = class_3532.method_15363(width * hpRatio, 0.0F, width);
/* 488 */     if (barWidth <= 0.0F)
/*     */       return; 
/* 490 */     Pair<Float, Float> colors = RenderHelperKt.getDepletableRedGreen(hpRatio, 0.0F, 0.0F);
/* 491 */     float red = ((Float)colors.getFirst()).floatValue() * 0.8F;
/* 492 */     float green = ((Float)colors.getSecond()).floatValue() * 0.8F;
/* 493 */     GuiUtilsKt.blitk(context.method_51448(), HEALTH_BAR, Integer.valueOf(x), Integer.valueOf(y), Integer.valueOf(6), Float.valueOf(barWidth), 
/* 494 */         Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(99), Integer.valueOf(6), Integer.valueOf(0), Float.valueOf(red), Float.valueOf(green), Float.valueOf(0.27F), Float.valueOf(1.0F), false, 1.0F);
/* 495 */     if (barWidth >= 1.0F) {
/* 496 */       GuiUtilsKt.blitk(context.method_51448(), HEALTH_BAR, Float.valueOf(x + barWidth - 1.0F), Integer.valueOf(y), Integer.valueOf(6), Float.valueOf(1.0F), 
/* 497 */           Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(99), Integer.valueOf(6), Integer.valueOf(0), Float.valueOf(red), Float.valueOf(green), Float.valueOf(0.27F), Float.valueOf(1.0F), false, 1.0F);
/*     */     }
/*     */   }
/*     */   
/*     */   private static float animatedHpRatio(BattleUiNetwork.PokemonEntry pokemon) {
/* 502 */     float target = rawHpRatio(pokemon);
/* 503 */     long now = System.currentTimeMillis();
/* 504 */     HpAnimation animation = HP_ANIMATIONS.get(pokemon.pokemonId());
/* 505 */     if (animation == null) {
/* 506 */       HP_ANIMATIONS.put(pokemon.pokemonId(), new HpAnimation(target, target, target, now));
/* 507 */       return target;
/*     */     } 
/*     */     
/* 510 */     float current = animation.value(now);
/* 511 */     if (Math.abs(animation.target() - target) > 0.001F) {
/* 512 */       animation = new HpAnimation(current, current, target, now);
/* 513 */       HP_ANIMATIONS.put(pokemon.pokemonId(), animation);
/* 514 */       return current;
/*     */     } 
/*     */     
/* 517 */     if (animation.done(now)) {
/* 518 */       animation.displayed = target;
/* 519 */       return target;
/*     */     } 
/* 521 */     return current;
/*     */   }
/*     */   
/*     */   private static float rawHpRatio(BattleUiNetwork.PokemonEntry pokemon) {
/* 525 */     return (pokemon.maxHp() <= 0.0F) ? 0.0F : class_3532.method_15363(pokemon.hp() / pokemon.maxHp(), 0.0F, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   private static void drawCobbleText(class_332 context, class_5250 text, float x, float y, float scale, int color, boolean centered) {
/* 530 */     RenderHelperKt.drawScaledText(context, CobblemonResources.INSTANCE
/*     */         
/* 532 */         .getDEFAULT_LARGE(), text, 
/*     */         
/* 534 */         Float.valueOf(x), 
/* 535 */         Float.valueOf(y), 
/* 536 */         (scale <= 0.0F) ? 1.0F : scale, 
/* 537 */         Float.valueOf(1.0F), 2147483647, color, centered, true, null, null);
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
/*     */   private static void drawUiText(class_332 context, class_5250 text, float x, float y, float scale, int color, boolean centered) {
/* 549 */     RenderHelperKt.drawScaledText(context, null, text, 
/*     */ 
/*     */ 
/*     */         
/* 553 */         Float.valueOf(x), 
/* 554 */         Float.valueOf(y), scale, 
/*     */         
/* 556 */         Float.valueOf(1.0F), 2147483647, color, centered, true, null, null);
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
/*     */   private static void drawRightText(class_332 context, class_5250 text, float x, float y, float scale, int color) {
/* 568 */     RenderHelperKt.drawScaledTextJustifiedRight(context, null, text, 
/*     */ 
/*     */ 
/*     */         
/* 572 */         Float.valueOf(x), 
/* 573 */         Float.valueOf(y), scale, 
/*     */         
/* 575 */         Float.valueOf(1.0F), 2147483647, color, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static class_5250 label(String text) {
/* 583 */     return class_2561.method_43470(text).method_27692(class_124.field_1067);
/*     */   }
/*     */   
/*     */   private static void drawGender(class_332 context, String gender, float x, float y) {
/* 587 */     String normalized = (gender == null) ? "" : gender.toUpperCase(Locale.ROOT);
/* 588 */     if ("GENDERLESS".equals(normalized) || normalized.isBlank())
/* 589 */       return;  boolean male = "MALE".equals(normalized);
/* 590 */     drawCobbleText(context, class_2561.method_43470(male ? "♂" : "♀").method_27692(class_124.field_1067), x, y, 0.0F, 
/* 591 */         male ? 3329023 : 16537300, false);
/*     */   }
/*     */ 
/*     */   
/*     */   private static void drawStatRow(class_332 context, BattleUiNetwork.PokemonEntry pokemon, String key, String label, float x, float y, int color) {
/* 596 */     drawUiText(context, class_2561.method_43470(label), x, y, 0.5F, color, false);
/* 597 */     int stage = stageFor(pokemon, key);
/* 598 */     if (stage != 0) {
/* 599 */       drawUiText(context, class_2561.method_43470(((stage > 0) ? "+" : "") + ((stage > 0) ? "+" : "")), x + 44.0F, y, 0.5F, 16777215, false);
/*     */     }
/*     */   }
/*     */   
/*     */   private static int stageFor(BattleUiNetwork.PokemonEntry pokemon, String key) {
/* 604 */     for (BattleUiNetwork.StatEntry boost : pokemon.boosts()) {
/* 605 */       if (statMatches(boost.key(), key)) return boost.stage(); 
/*     */     } 
/* 607 */     return 0;
/*     */   }
/*     */   
/*     */   private static boolean statMatches(String actual, String expected) {
/* 611 */     String key = (actual == null) ? "" : actual.toLowerCase(Locale.ROOT);
/* 612 */     return (key.equals(expected) || ("attack"
/* 613 */       .equals(key) && "atk".equals(expected)) || ("defense"
/* 614 */       .equals(key) && "def".equals(expected)) || ("defence"
/* 615 */       .equals(key) && "def".equals(expected)) || ("special-attack"
/* 616 */       .equals(key) && "spa".equals(expected)) || ("special_attack"
/* 617 */       .equals(key) && "spa".equals(expected)) || ("spattack"
/* 618 */       .equals(key) && "spa".equals(expected)) || ("special-defense"
/* 619 */       .equals(key) && "spd".equals(expected)) || ("special-defence"
/* 620 */       .equals(key) && "spd".equals(expected)) || ("special_defense"
/* 621 */       .equals(key) && "spd".equals(expected)) || ("special_defence"
/* 622 */       .equals(key) && "spd".equals(expected)) || ("spdefense"
/* 623 */       .equals(key) && "spd".equals(expected)) || ("speed"
/* 624 */       .equals(key) && "spe".equals(expected)));
/*     */   }
/*     */   
/*     */   private static float statMultiplier(int stage) {
/* 628 */     if (stage == 0) return 1.0F; 
/* 629 */     return (stage > 0) ? ((2.0F + stage) / 2.0F) : (2.0F / (2.0F - stage));
/*     */   }
/*     */   
/*     */   private static String formText(BattleUiNetwork.PokemonEntry pokemon) {
/* 633 */     return (pokemon.form() == null || pokemon.form().isBlank()) ? "?" : pokemon.form();
/*     */   }
/*     */   
/*     */   private static ElementalType type(String type) {
/* 637 */     if (type == null || type.isBlank()) return null; 
/* 638 */     return ElementalTypes.get(type.toLowerCase(Locale.ROOT));
/*     */   }
/*     */ 
/*     */   
/*     */   private static void drawTexture(class_332 context, class_2960 texture, int x, int y, int width, int height, int textureWidth, int textureHeight) {
/* 643 */     context.method_25293(texture, x, y, width, height, 0.0F, 0.0F, textureWidth, textureHeight, textureWidth, textureHeight);
/*     */   }
/*     */ 
/*     */   
/*     */   private static class_2960 parseIdentifier(String value) {
/*     */     try {
/* 649 */       if (value == null || value.isBlank()) return null; 
/* 650 */       return class_2960.method_60654(value);
/* 651 */     } catch (Exception ignored) {
/* 652 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static class_2960 tex(String path) {
/* 657 */     return class_2960.method_60655("atlas-common-fabric", "textures/gui/battle/" + path);
/*     */   }
/*     */   
/*     */   private static String trim(class_327 textRenderer, String value, int width) {
/* 661 */     if (value == null || value.isBlank()) return "?"; 
/* 662 */     if (textRenderer.method_1727(value) <= width) return value; 
/* 663 */     String trimmed = value;
/* 664 */     while (trimmed.length() > 1 && textRenderer.method_1727(trimmed + "...") > width) {
/* 665 */       trimmed = trimmed.substring(0, trimmed.length() - 1);
/*     */     }
/* 667 */     return trimmed + "...";
/*     */   }
/*     */   
/*     */   private static String cleanPokemonName(String name) {
/* 671 */     if (name == null) return ""; 
/* 672 */     int possessive = name.indexOf("'s ");
/* 673 */     if (possessive >= 0 && possessive + 3 < name.length()) {
/* 674 */       return name.substring(possessive + 3).trim();
/*     */     }
/* 676 */     return name;
/*     */   }
/*     */   
/*     */   private static int typeColor(String type) {
/* 680 */     switch (((type == null) ? "" : type).toLowerCase(Locale.ROOT)) { case "fire": case "water": case "grass": case "electric": case "ice": case "fighting": case "poison": case "ground": case "flying": case "psychic": case "bug": case "rock": case "ghost": case "dragon": case "dark": case "steel": case "fairy":  }  return 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 698 */       16777215;
/*     */   }
/*     */   
/*     */   private static final class HpAnimation
/*     */   {
/*     */     private float displayed;
/*     */     private final float start;
/*     */     private final float target;
/*     */     private final long startMillis;
/*     */     
/*     */     private HpAnimation(float displayed, float start, float target, long startMillis) {
/* 709 */       this.displayed = displayed;
/* 710 */       this.start = start;
/* 711 */       this.target = target;
/* 712 */       this.startMillis = startMillis;
/*     */     }
/*     */     
/*     */     private float target() {
/* 716 */       return this.target;
/*     */     }
/*     */     
/*     */     private boolean done(long now) {
/* 720 */       return (now - this.startMillis >= 650L);
/*     */     }
/*     */     
/*     */     private float value(long now) {
/* 724 */       float progress = class_3532.method_15363((float)(now - this.startMillis) / 650.0F, 0.0F, 1.0F);
/* 725 */       float eased = progress * progress * (3.0F - 2.0F * progress);
/* 726 */       this.displayed = class_3532.method_16439(eased, this.start, this.target);
/* 727 */       return this.displayed;
/*     */     }
/*     */   }
/*     */   private static final class Hover extends Record { private final BattleUiNetwork.PokemonEntry pokemon; private final int x; private final int y; private final int width; private final boolean active; private final boolean reversed;
/* 731 */     private Hover(BattleUiNetwork.PokemonEntry pokemon, int x, int y, int width, boolean active, boolean reversed) { this.pokemon = pokemon; this.x = x; this.y = y; this.width = width; this.active = active; this.reversed = reversed; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/battle/ui/BattleUiOverlayRenderer$Hover;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #731	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/battle/ui/BattleUiOverlayRenderer$Hover; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/battle/ui/BattleUiOverlayRenderer$Hover;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #731	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/battle/ui/BattleUiOverlayRenderer$Hover; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/battle/ui/BattleUiOverlayRenderer$Hover;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #731	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lcom/atlas/common/fabric/battle/ui/BattleUiOverlayRenderer$Hover;
/* 731 */       //   0	8	1	o	Ljava/lang/Object; } public BattleUiNetwork.PokemonEntry pokemon() { return this.pokemon; } public int x() { return this.x; } public int y() { return this.y; } public int width() { return this.width; } public boolean active() { return this.active; } public boolean reversed() { return this.reversed; }
/*     */      }
/*     */ 
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\battl\\ui\BattleUiOverlayRenderer.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */