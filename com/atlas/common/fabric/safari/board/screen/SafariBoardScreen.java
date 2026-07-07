/*     */ package com.atlas.common.fabric.safari.board.screen;
/*     */ 
/*     */ import com.atlas.common.fabric.safari.board.SafariBoardClientData;
/*     */ import com.atlas.common.fabric.safari.board.SafariBoardModels;
/*     */ import com.atlas.common.fabric.safari.board.network.SafariBoardNetwork;
/*     */ import com.atlas.common.fabric.screen.PokemonRenderHelper;
/*     */ import com.cobblemon.mod.common.api.pokemon.PokemonSpecies;
/*     */ import com.cobblemon.mod.common.client.CobblemonResources;
/*     */ import com.cobblemon.mod.common.client.render.RenderHelperKt;
/*     */ import com.cobblemon.mod.common.client.render.models.blockbench.FloatingState;
/*     */ import com.cobblemon.mod.common.pokemon.Species;
/*     */ import java.text.NumberFormat;
/*     */ import java.time.Duration;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import net.minecraft.class_2561;
/*     */ import net.minecraft.class_2583;
/*     */ import net.minecraft.class_2960;
/*     */ import net.minecraft.class_332;
/*     */ import net.minecraft.class_437;
/*     */ import net.minecraft.class_4587;
/*     */ import net.minecraft.class_5250;
/*     */ import net.minecraft.class_5348;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.joml.Quaternionf;
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
/*     */ public final class SafariBoardScreen
/*     */   extends class_437
/*     */ {
/*  44 */   private static final class_2960 POKEDEX_BASE_GREEN = class_2960.method_60655("cobblemon", "textures/gui/pokedex/pokedex_base_green.png");
/*  45 */   private static final class_2960 POKEDEX_BASE_YELLOW = class_2960.method_60655("cobblemon", "textures/gui/pokedex/pokedex_base_yellow.png");
/*  46 */   private static final class_2960 POKEDEX_SCREEN = class_2960.method_60655("cobblemon", "textures/gui/pokedex/pokedex_screen.png");
/*  47 */   private static final class_2960 POKEBALL_TEXTURE = class_2960.method_60655("cobblemon", "textures/gui/pokedex/pokedex_screen_poke_ball.png");
/*  48 */   private static final class_2960 PLATFORM_BASE_TEXTURE = class_2960.method_60655("cobblemon", "textures/gui/pokedex/platform_base.png");
/*  49 */   private static final class_2960 PLATFORM_SHADOW_TEXTURE = class_2960.method_60655("cobblemon", "textures/gui/pokedex/platform_shadow.png");
/*     */ 
/*     */   
/*     */   private static final int GUI_WIDTH = 345;
/*     */ 
/*     */   
/*     */   private static final int GUI_HEIGHT = 207;
/*     */ 
/*     */   
/*     */   private static final int CARD_WIDTH = 102;
/*     */ 
/*     */   
/*     */   private static final int CARD_HEIGHT = 158;
/*     */ 
/*     */   
/*     */   private static final int CARD_GAP = 4;
/*     */ 
/*     */   
/*     */   private static final int CONTENT_X = 15;
/*     */ 
/*     */   
/*     */   private static final int CONTENT_Y = 24;
/*     */   
/*     */   private static final int REROLL_SIZE = 10;
/*     */   
/*     */   private static final int PORTRAIT_NATIVE_WIDTH = 137;
/*     */   
/*     */   private static final int PORTRAIT_NATIVE_HEIGHT = 68;
/*     */   
/*     */   private static final int PORTRAIT_START_Y = 25;
/*     */   
/*     */   private static final int PORTRAIT_POKE_BALL_W = 109;
/*     */   
/*     */   private static final int PORTRAIT_POKE_BALL_H = 68;
/*     */   
/*     */   private static final float DEX_HALF_SCALE = 0.5F;
/*     */   
/*     */   private static final float PORTRAIT_FIT_SCALE = 0.72F;
/*     */   
/*     */   private static final float MODEL_SCALE_AMOUNT = 5.5555553F;
/*     */   
/*  90 */   private static final class_2960 DEX_FONT = CobblemonResources.INSTANCE.getDEFAULT_LARGE();
/*     */   
/*     */   private static final int TEXT_PRIMARY = -593437;
/*     */   
/*     */   private static final int TEXT_MUTED = -2832990;
/*     */   
/*     */   private static final int TEXT_DIM = -6650531;
/*     */   
/*     */   private static final int OK = -4594053;
/*     */   
/*     */   private static final int FAIL = -29572;
/*     */   
/*     */   private static final int COMPLETE = -1061766;
/*     */   
/*     */   private static final int STAR_FILLED = -1061766;
/*     */   
/*     */   private static final int STAR_EMPTY = -9545158;
/*     */   private static final int CARD_FILL = -584178664;
/*     */   private static final int CARD_BORDER = -15002870;
/*     */   private static final int CARD_HIGHLIGHT = -10993623;
/*     */   private static final int SURFACE = -792110;
/*     */   private static final int SURFACE_DARK = -3556978;
/*     */   private static final int SURFACE_BORDER = -9744596;
/*     */   private static final int SURFACE_TEXT = -12767971;
/*     */   private final SafariBoardModels.BoardType boardType;
/* 115 */   private final Map<Integer, FloatingState> modelStates = new HashMap<>();
/* 116 */   private int animationTicks = 0;
/* 117 */   private int pokeballFrame = 0;
/*     */   private int guiLeft;
/*     */   private int guiTop;
/*     */   
/*     */   public SafariBoardScreen(@NotNull SafariBoardModels.BoardType boardType) {
/* 122 */     super((class_2561)class_2561.method_43470(boardType.displayName()));
/* 123 */     this.boardType = boardType;
/* 124 */     PokemonRenderHelper.init();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void method_25426() {
/* 129 */     super.method_25426();
/* 130 */     this.guiLeft = (this.field_22789 - 345) / 2;
/* 131 */     this.guiTop = (this.field_22790 - 207) / 2;
/*     */   }
/*     */   
/*     */   public boolean method_25421() {
/* 135 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void method_25420(@NotNull class_332 context, int mouseX, int mouseY, float delta) {}
/*     */ 
/*     */   
/*     */   public void method_25393() {
/* 144 */     super.method_25393();
/* 145 */     this.animationTicks++;
/* 146 */     if (this.animationTicks % 3 == 0) {
/* 147 */       this.pokeballFrame = (this.pokeballFrame + 1) % 16;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void method_25394(@NotNull class_332 context, int mouseX, int mouseY, float delta) {
/* 155 */     drawPokedexBase(context);
/*     */ 
/*     */ 
/*     */     
/* 159 */     String title = (this.boardType == SafariBoardModels.BoardType.CONTRACTS) ? "Hunting Board" : "Safari Hunts";
/* 160 */     drawDexText(context, bold(title), (this.guiLeft + 26), (this.guiTop + 14), 1.0F, -593437, false, true, 240);
/*     */     
/* 162 */     long remaining = Math.max(0L, SafariBoardClientData.getInstance().getNextRefreshAt(this.boardType) - System.currentTimeMillis());
/* 163 */     drawDexTextRight(context, bold("Reset in " + formatDuration(remaining)), this.guiLeft + 345 - 26, this.guiTop + 14, 1.0F, -2832990, true);
/*     */     
/* 165 */     if (this.boardType == SafariBoardModels.BoardType.CONTRACTS) {
/* 166 */       renderContracts(context, mouseX, mouseY);
/*     */     } else {
/* 168 */       renderHunts(context, mouseX, mouseY, delta);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 173 */     String rerollText = (SafariBoardClientData.getInstance().getFreeRerollsRemaining(this.boardType) > 0) ? "1 free reroll ready" : ("Reroll: " + formatNumber(SafariBoardClientData.getInstance().getNextRerollCost(this.boardType)) + " gems");
/* 174 */     drawDexText(context, plain(trim(rerollText, 30)), (this.guiLeft + 16), (this.guiTop + 207 - 12), 1.0F, -2832990, false, true, 240);
/*     */     
/* 176 */     drawToast(context);
/* 177 */     super.method_25394(context, mouseX, mouseY, delta);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawPokedexBase(@NotNull class_332 context) {
/* 193 */     class_2960 base = (this.boardType == SafariBoardModels.BoardType.CONTRACTS) ? POKEDEX_BASE_YELLOW : POKEDEX_BASE_GREEN;
/* 194 */     context.method_25290(POKEDEX_SCREEN, this.guiLeft, this.guiTop, 0.0F, 0.0F, 345, 207, 345, 207);
/* 195 */     context.method_25290(base, this.guiLeft, this.guiTop, 0.0F, 0.0F, 345, 207, 345, 207);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderContracts(@NotNull class_332 context, int mouseX, int mouseY) {
/* 201 */     List<SafariBoardModels.ContractCardData> contracts = SafariBoardClientData.getInstance().getContracts();
/* 202 */     for (int index = 0; index < 3; index++) {
/* 203 */       int cardX = this.guiLeft + 15 + index * 106;
/* 204 */       int cardY = this.guiTop + 24;
/* 205 */       SafariBoardModels.ContractCardData card = (index < contracts.size()) ? contracts.get(index) : null;
/* 206 */       renderContractCard(context, cardX, cardY, mouseX, mouseY, card);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void renderContractCard(@NotNull class_332 context, int x, int y, int mouseX, int mouseY, SafariBoardModels.ContractCardData card) {
/* 212 */     drawCardFrame(context, x, y);
/* 213 */     if (card == null) {
/* 214 */       drawDexText(context, plain("Loading..."), x + 51.0F, (y + 70), 1.0F, -2832990, true, true, 90);
/*     */       return;
/*     */     } 
/* 217 */     drawCardHeader(context, x, y, trim(card.difficulty().displayName(), 12), card.difficulty().stars(), mouseX, mouseY, card.rewarded());
/*     */     
/* 219 */     int lineY = y + 44;
/* 220 */     for (SafariBoardModels.MissionEntryData mission : card.missions()) {
/* 221 */       int color = mission.complete() ? -4594053 : -593437;
/* 222 */       String text = trim(mission.label(), 10) + "  " + trim(mission.label(), 10) + "/" + mission.clampedProgress();
/* 223 */       drawDexText(context, plain(trim(text, 18)), (x + 7), lineY, 1.0F, color, false, true, 88);
/* 224 */       lineY += 10;
/*     */     } 
/*     */     
/* 227 */     drawRewardsFooter(context, x, y, card.rewards());
/*     */     
/* 229 */     if (card.rewarded()) {
/* 230 */       drawBanner(context, x + 14, y + 158 - 13, 74, 10, "Done", -1061766);
/*     */     }
/*     */   }
/*     */   
/*     */   private void renderHunts(@NotNull class_332 context, int mouseX, int mouseY, float delta) {
/* 235 */     List<SafariBoardModels.HuntCardData> hunts = SafariBoardClientData.getInstance().getHunts();
/* 236 */     for (int index = 0; index < 3; index++) {
/* 237 */       int cardX = this.guiLeft + 15 + index * 106;
/* 238 */       int cardY = this.guiTop + 24;
/* 239 */       SafariBoardModels.HuntCardData hunt = (index < hunts.size()) ? hunts.get(index) : null;
/* 240 */       renderHuntCard(context, cardX, cardY, mouseX, mouseY, delta, hunt, index);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderHuntCard(@NotNull class_332 context, int x, int y, int mouseX, int mouseY, float delta, SafariBoardModels.HuntCardData hunt, int stateIndex) {
/* 247 */     drawCardFrame(context, x, y);
/* 248 */     if (hunt == null) {
/* 249 */       drawDexText(context, plain("Loading..."), x + 51.0F, (y + 70), 1.0F, -2832990, true, true, 90);
/*     */       return;
/*     */     } 
/* 252 */     drawCardHeader(context, x, y, trim(hunt.speciesName(), 12), hunt.difficulty().stars(), mouseX, mouseY, hunt.rewarded());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 259 */     float blockW = 98.64001F;
/* 260 */     float blockH = 48.960003F;
/*     */     
/* 262 */     int portraitAreaY = y + 38;
/* 263 */     int portraitAreaH = 56;
/* 264 */     float regionX = x + 1.6799965F;
/* 265 */     float regionY = portraitAreaY + 3.5199986F;
/*     */ 
/*     */     
/* 268 */     float widgetOriginX = regionX;
/* 269 */     float widgetOriginY = regionY - 18.0F;
/*     */     
/* 271 */     class_4587 matrices = context.method_51448();
/* 272 */     matrices.method_22903();
/* 273 */     matrices.method_46416(widgetOriginX, widgetOriginY, 0.0F);
/* 274 */     matrices.method_22905(0.72F, 0.72F, 1.0F);
/*     */ 
/*     */     
/* 277 */     context.method_44379(
/* 278 */         Math.round(regionX), 
/* 279 */         Math.round(regionY), 
/* 280 */         Math.round(regionX + 98.64001F), 
/* 281 */         Math.round(regionY + 48.960003F));
/*     */ 
/*     */     
/* 284 */     drawPokedexPortraitBlock(context, hunt.speciesId(), stateIndex, delta);
/*     */     
/* 286 */     context.method_44380();
/* 287 */     matrices.method_22909();
/*     */ 
/*     */     
/* 290 */     int progressY = y + 96;
/* 291 */     String progressLine = "" + hunt.clampedProgress() + "/" + hunt.clampedProgress() + " caught";
/* 292 */     drawDexText(context, bold(trim(progressLine, 16)), x + 51.0F, progressY, 1.0F, hunt.completed() ? -4594053 : -2832990, true, true, 94);
/*     */     
/* 294 */     drawRewardsFooter(context, x, y, hunt.rewards());
/*     */     
/* 296 */     if (hunt.rewarded()) {
/* 297 */       drawBanner(context, x + 14, y + 158 - 13, 74, 10, "Done", -1061766);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawCardFrame(@NotNull class_332 context, int x, int y) {
/* 304 */     context.method_25294(x, y, x + 102, y + 158, -15002870);
/* 305 */     context.method_25294(x + 1, y + 1, x + 102 - 1, y + 158 - 1, -584178664);
/* 306 */     context.method_25294(x + 1, y + 1, x + 102 - 1, y + 2, -10993623);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawCardHeader(@NotNull class_332 context, int x, int y, @NotNull String name, int starCount, int mouseX, int mouseY, boolean disabled) {
/* 313 */     int tabW = 42;
/* 314 */     int tabH = 10;
/* 315 */     drawSurface(context, x + 4, y + 3, 42, 10);
/* 316 */     drawDexText(context, bold("Target"), (x + 4) + 21.0F, (y + 4), 1.0F, -12767971, true, false, 38);
/*     */ 
/*     */     
/* 319 */     drawRerollButton(context, x + 102 - 4 - 10, y + 3, mouseX, mouseY, disabled);
/*     */ 
/*     */     
/* 322 */     int nameY = y + 15;
/* 323 */     drawSurface(context, x + 4, nameY, 94, 10);
/* 324 */     drawDexText(context, bold(name), (x + 7), (nameY + 1), 1.0F, -12767971, false, false, 66);
/*     */ 
/*     */     
/* 327 */     drawStars(context, x + 102 - 4 - 26, nameY + 1, starCount);
/*     */   }
/*     */   
/*     */   private void drawStars(@NotNull class_332 context, int x, int y, int starCount) {
/* 331 */     int pillW = 26;
/* 332 */     int pillH = 8;
/* 333 */     drawSurface(context, x, y, 26, 8);
/* 334 */     int starSize = 5;
/* 335 */     int gap = 1;
/* 336 */     int total = 3;
/* 337 */     int blockW = 17;
/* 338 */     int startX = x + 4;
/* 339 */     int startY = y + 1;
/* 340 */     for (int i = 0; i < 3; i++) {
/* 341 */       drawStar(context, startX + i * 6, startY, 5, (i < starCount) ? -1061766 : -9545158);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void drawStar(@NotNull class_332 context, int x, int y, int size, int color) {
/* 347 */     int[][] pattern = { { 0, 0, 1, 0, 0 }, { 0, 1, 1, 1, 0 }, { 1, 1, 1, 1, 1 }, { 0, 1, 1, 1, 0 }, { 1, 1, 0, 1, 1 } };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 354 */     for (int py = 0; py < size && py < pattern.length; py++) {
/* 355 */       for (int px = 0; px < size && px < (pattern[py]).length; px++) {
/* 356 */         if (pattern[py][px] == 1) {
/* 357 */           context.method_25294(x + px, y + py, x + px + 1, y + py + 1, color);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void drawRewardsFooter(@NotNull class_332 context, int x, int y, @NotNull List<SafariBoardModels.RewardEntryData> rewards) {
/* 365 */     int boxH = 32;
/* 366 */     int boxY = y + 158 - 32 - 3;
/* 367 */     drawSurface(context, x + 4, boxY, 94, 32);
/* 368 */     drawDexText(context, bold("Rewards"), x + 51.0F, (boxY + 1), 1.0F, -12767971, true, false, 90);
/* 369 */     int rewardY = boxY + 11;
/* 370 */     for (SafariBoardModels.RewardEntryData reward : rewards) {
/* 371 */       drawDexText(context, plain(trim(reward.label(), 18)), x + 51.0F, rewardY, 1.0F, -12767971, true, false, 92);
/* 372 */       rewardY += 10;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void drawSurface(@NotNull class_332 context, int x, int y, int width, int height) {
/* 377 */     context.method_25294(x, y, x + width, y + height, -9744596);
/* 378 */     context.method_25294(x + 1, y + 1, x + width - 1, y + height - 1, -792110);
/* 379 */     context.method_25294(x + 1, y + height - 2, x + width - 1, y + height - 1, -3556978);
/*     */   }
/*     */ 
/*     */   
/*     */   private void drawRerollButton(@NotNull class_332 context, int x, int y, int mouseX, int mouseY, boolean disabled) {
/* 384 */     boolean hovered = (!disabled && mouseX >= x && mouseX < x + 10 && mouseY >= y && mouseY < y + 10);
/* 385 */     drawSurface(context, x, y, 10, 10);
/* 386 */     if (hovered) {
/* 387 */       context.method_25294(x + 1, y + 1, x + 10 - 1, y + 10 - 1, 872415231);
/*     */     }
/* 389 */     drawDexText(context, bold("R"), x + 5.0F, (y + 1), 1.0F, disabled ? -6650531 : -12767971, true, false, 8);
/*     */   }
/*     */ 
/*     */   
/*     */   private void drawBanner(@NotNull class_332 context, int x, int y, int width, int height, @NotNull String text, int color) {
/* 394 */     context.method_25294(x, y, x + width, y + height, -1440802283);
/* 395 */     outline(context, x, y, width, height, color);
/* 396 */     drawDexText(context, bold(text), x + width / 2.0F, (y + 1), 1.0F, color, true, false, width - 6);
/*     */   }
/*     */   
/*     */   private void outline(@NotNull class_332 context, int x, int y, int width, int height, int color) {
/* 400 */     context.method_25294(x, y, x + width, y + 1, color);
/* 401 */     context.method_25294(x, y + height - 1, x + width, y + height, color);
/* 402 */     context.method_25294(x, y, x + 1, y + height, color);
/* 403 */     context.method_25294(x + width - 1, y, x + width, y + height, color);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawPokedexPortraitBlock(@NotNull class_332 context, @NotNull String speciesIdString, int stateIndex, float delta) {
/* 412 */     context.method_25293(POKEBALL_TEXTURE, 15, 25, 109, 68, 0.0F, this.pokeballFrame * 109.0F + 20.0F, 109, 68, 109, 1744);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 422 */     context.method_25293(PLATFORM_BASE_TEXTURE, 13, 69, 113, 24, 0.0F, 0.0F, 113, 24, 113, 30);
/*     */ 
/*     */     
/* 425 */     class_2960 typedPlatform = getPlatformTexture(speciesIdString);
/* 426 */     if (typedPlatform != null) {
/* 427 */       context.method_25293(typedPlatform, 13, 66, 113, 27, 0.0F, 0.0F, 113, 27, 113, 30);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 432 */     class_4587 matrices = context.method_51448();
/* 433 */     matrices.method_22903();
/* 434 */     matrices.method_22905(0.5F, 0.5F, 1.0F);
/* 435 */     context.method_25293(PLATFORM_SHADOW_TEXTURE, 
/*     */         
/* 437 */         Math.round(94.0F), 
/* 438 */         Math.round(153.0F), 90, 20, 0.0F, 0.0F, 90, 20, 90, 20);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 444 */     matrices.method_22909();
/*     */ 
/*     */     
/*     */     try {
/* 448 */       class_2960 speciesId = class_2960.method_60654(speciesIdString);
/* 449 */       FloatingState state = this.modelStates.computeIfAbsent(Integer.valueOf(stateIndex), ignored -> new FloatingState());
/* 450 */       state.setCurrentAspects(Set.of());
/*     */       
/* 452 */       matrices.method_22903();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 462 */       double anchorYAdjust = 21.77777468716669D;
/* 463 */       matrices.method_22904(69.5D, 34.77777468716669D, 1000.0D);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 468 */       matrices.method_22905(5.5555553F, 5.5555553F, 5.5555553F);
/* 469 */       Quaternionf rotation = (new Quaternionf()).rotationXYZ(
/* 470 */           (float)Math.toRadians(13.0D), 
/* 471 */           (float)Math.toRadians(30.0D), 0.0F);
/*     */ 
/*     */       
/* 474 */       if (PokemonRenderHelper.isAvailable()) {
/* 475 */         PokemonRenderHelper.draw(speciesId, matrices, rotation, state, delta);
/*     */       }
/* 477 */       matrices.method_22909();
/* 478 */     } catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private class_2960 getPlatformTexture(@NotNull String speciesIdString) {
/*     */     try {
/* 485 */       class_2960 speciesId = class_2960.method_60654(speciesIdString);
/* 486 */       Species species = PokemonSpecies.getByIdentifier(speciesId);
/* 487 */       if (species == null) {
/* 488 */         species = PokemonSpecies.getByName(speciesId.method_12832());
/*     */       }
/* 490 */       if (species == null || species.getPrimaryType() == null) {
/* 491 */         return null;
/*     */       }
/* 493 */       return class_2960.method_60655("cobblemon", "textures/gui/pokedex/platform_base_" + species.getPrimaryType().getName().toLowerCase(Locale.ROOT) + ".png");
/* 494 */     } catch (Exception ignored) {
/* 495 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawToast(@NotNull class_332 context) {
/* 502 */     SafariBoardClientData data = SafariBoardClientData.getInstance();
/* 503 */     if (data.getLastMessage().isEmpty())
/* 504 */       return;  if (System.currentTimeMillis() - data.getLastMessageAt() > 5000L)
/*     */       return; 
/* 506 */     int width = Math.min(220, this.field_22793.method_1727(data.getLastMessage()) + 18);
/* 507 */     int x = this.guiLeft + (345 - width) / 2;
/* 508 */     int y = this.guiTop + 207 - 28;
/* 509 */     context.method_25294(x, y, x + width, y + 14, data.isLastMessageSuccess() ? -869775841 : -866900445);
/* 510 */     outline(context, x, y, width, 14, data.isLastMessageSuccess() ? -4594053 : -29572);
/* 511 */     drawDexText(context, plain(trim(data.getLastMessage(), 32)), x + width / 2.0F, (y + 2), 1.0F, -593437, true, false, width - 8);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean method_25402(double mouseX, double mouseY, int button) {
/* 516 */     if (button == 0) {
/* 517 */       for (int index = 0; index < 3; index++) {
/* 518 */         int cardX = this.guiLeft + 15 + index * 106;
/* 519 */         int cardY = this.guiTop + 24;
/* 520 */         int rerollX = cardX + 102 - 4 - 10;
/* 521 */         int rerollY = cardY + 3;
/* 522 */         if (mouseX >= rerollX && mouseX < (rerollX + 10) && mouseY >= rerollY && mouseY < (rerollY + 10)) {
/* 523 */           SafariBoardNetwork.requestReroll(this.boardType, index);
/* 524 */           return true;
/*     */         } 
/*     */       } 
/*     */     }
/* 528 */     return super.method_25402(mouseX, mouseY, button);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   private static class_5250 bold(@NotNull String text) {
/* 536 */     return class_2561.method_43470(text).method_10862(class_2583.field_24360.method_10982(Boolean.valueOf(true)));
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   private static class_5250 plain(@NotNull String text) {
/* 541 */     return class_2561.method_43470(text);
/*     */   }
/*     */ 
/*     */   
/*     */   private void drawDexText(@NotNull class_332 context, @NotNull class_5250 text, float x, float y, float scale, int color, boolean centered, boolean shadow, int maxWidth) {
/* 546 */     RenderHelperKt.drawScaledText(context, DEX_FONT, text, 
/*     */ 
/*     */ 
/*     */         
/* 550 */         Float.valueOf(x), 
/* 551 */         Float.valueOf(y), scale, 
/*     */         
/* 553 */         Float.valueOf(1.0F), 
/* 554 */         Math.max(1, Math.round(maxWidth / scale)), color, centered, shadow, null, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawDexTextRight(@NotNull class_332 context, @NotNull class_5250 text, int x, int y, float scale, int color, boolean shadow) {
/* 565 */     int width = Math.round(this.field_22793.method_27525((class_5348)text.method_27661().method_10862(text.method_10866().method_27704(DEX_FONT))) * scale);
/* 566 */     drawDexText(context, text, (x - width), y, scale, color, false, shadow, width + 4);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   private static String trim(@NotNull String text, int maxLength) {
/* 571 */     return (text.length() <= maxLength) ? text : (text.substring(0, Math.max(0, maxLength - 3)) + "...");
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   private static String formatDuration(long millis) {
/* 576 */     Duration duration = Duration.ofMillis(millis);
/* 577 */     long hours = duration.toHours();
/* 578 */     long minutes = duration.toMinutesPart();
/* 579 */     long seconds = duration.toSecondsPart();
/* 580 */     if (hours > 0L) return "" + hours + "h " + hours + "m"; 
/* 581 */     if (minutes > 0L) return "" + minutes + "m " + minutes + "s"; 
/* 582 */     return "" + seconds + "s";
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   private static String formatNumber(int value) {
/* 587 */     return NumberFormat.getInstance(Locale.US).format(value);
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\safari\board\screen\SafariBoardScreen.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */