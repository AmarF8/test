/*     */ package com.atlas.common.fabric.guide.screen.views;
/*     */ 
/*     */ import com.atlas.common.fabric.guide.data.GuideData;
/*     */ import com.atlas.common.fabric.guide.data.MoveDetail;
/*     */ import com.atlas.common.fabric.guide.network.GuideNetwork;
/*     */ import com.atlas.common.fabric.guide.screen.GuideColors;
/*     */ import com.atlas.common.fabric.guide.screen.GuideSounds;
/*     */ import com.atlas.common.fabric.guide.screen.widgets.GuideScrollableArea;
/*     */ import com.atlas.common.fabric.guide.screen.widgets.GuideSearchBar;
/*     */ import com.atlas.common.fabric.guide.screen.widgets.GuideTypeBadge;
/*     */ import com.atlas.common.fabric.screen.PokemonRenderHelper;
/*     */ import com.cobblemon.mod.common.client.render.models.blockbench.FloatingState;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.function.Consumer;
/*     */ import net.minecraft.class_2960;
/*     */ import net.minecraft.class_327;
/*     */ import net.minecraft.class_332;
/*     */ import net.minecraft.class_4587;
/*     */ import org.joml.Quaternionf;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MoveDetailView
/*     */ {
/*     */   private static final int HEADER_HEIGHT = 100;
/*     */   private static final int SEARCH_BAR_HEIGHT = 16;
/*     */   private static final int TOTAL_HEADER = 118;
/*     */   private static final int ROW_HEIGHT = 26;
/*     */   private int x;
/*     */   private int y;
/*     */   private int width;
/*     */   private int height;
/*     */   private GuideScrollableArea scrollArea;
/*     */   private GuideSearchBar pokemonSearchBar;
/*     */   private Consumer<String> onPokemonSelected;
/*     */   private Runnable onBack;
/*  43 */   private int hoveredRowIndex = -1;
/*     */   private boolean backHovered = false;
/*  45 */   private String pokemonFilter = "";
/*     */ 
/*     */   
/*  48 */   private final Map<String, FloatingState> modelStates = new HashMap<>();
/*  49 */   private final Set<String> failedModelSpecies = new HashSet<>();
/*     */   
/*     */   public MoveDetailView() {
/*  52 */     PokemonRenderHelper.init();
/*     */   }
/*     */   
/*     */   public void init(int x, int y, int width, int height, Consumer<String> onPokemonSelected, Runnable onBack) {
/*  56 */     this.x = x;
/*  57 */     this.y = y;
/*  58 */     this.width = width;
/*  59 */     this.height = height;
/*  60 */     this.onPokemonSelected = onPokemonSelected;
/*  61 */     this.onBack = onBack;
/*     */     
/*  63 */     this.pokemonSearchBar = new GuideSearchBar(x + 4, y + 100, width - 8, 16);
/*  64 */     this.pokemonSearchBar.setPlaceholder("Filter Pokemon...");
/*  65 */     this.pokemonSearchBar.setOnTextChanged(text -> {
/*     */           this.pokemonFilter = text.toLowerCase().trim();
/*     */           if (this.scrollArea != null)
/*     */             this.scrollArea.resetScroll(); 
/*     */         });
/*  70 */     int scrollTop = y + 118;
/*  71 */     int scrollHeight = height - 118;
/*  72 */     this.scrollArea = new GuideScrollableArea(x, scrollTop, width, scrollHeight);
/*     */   }
/*     */   
/*     */   public void updateBounds(int x, int y, int width, int height) {
/*  76 */     this.x = x; this.y = y; this.width = width; this.height = height;
/*  77 */     if (this.pokemonSearchBar != null) this.pokemonSearchBar.updateBounds(x + 4, y + 100, width - 8, 16); 
/*  78 */     if (this.scrollArea != null) this.scrollArea.updateBounds(x, y + 118, width, height - 118);
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void requestMoveDetail(String moveName) {
/*  85 */     GuideData.getInstance().setMoveDetailLoading(true);
/*  86 */     GuideNetwork.requestGuideData("move_detail", 0, moveName, "");
/*  87 */     this.modelStates.clear();
/*  88 */     this.pokemonFilter = "";
/*  89 */     if (this.pokemonSearchBar != null) this.pokemonSearchBar.setText(""); 
/*  90 */     if (this.scrollArea != null) this.scrollArea.resetScroll(); 
/*     */   }
/*     */   public void render(class_332 ctx, class_327 textRenderer, int mouseX, int mouseY, float delta) {
/*     */     List<MoveDetail.MovePokemon> filteredList;
/*  94 */     ctx.method_25294(this.x, this.y, this.x + this.width, this.y + this.height, GuideColors.SECTION_BG);
/*     */     
/*  96 */     GuideData data = GuideData.getInstance();
/*  97 */     MoveDetail detail = data.getCurrentMoveDetail();
/*     */ 
/*     */     
/* 100 */     this.backHovered = (mouseX >= this.x + 4 && mouseX < this.x + 44 && mouseY >= this.y + 3 && mouseY < this.y + 14);
/* 101 */     int backColor = this.backHovered ? GuideColors.TEXT_WHITE : GuideColors.TEXT_DIM;
/* 102 */     ctx.method_51433(textRenderer, "← Back", this.x + 4, this.y + 4, backColor, true);
/*     */     
/* 104 */     if (data.isMoveDetailLoading()) {
/* 105 */       ctx.method_25300(textRenderer, "Loading...", this.x + this.width / 2, this.y + this.height / 2, GuideColors.TEXT_DIM);
/*     */       
/*     */       return;
/*     */     } 
/* 109 */     if (detail == null) {
/* 110 */       ctx.method_25300(textRenderer, "Move not found", this.x + this.width / 2, this.y + this.height / 2, GuideColors.TEXT_DIM);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 115 */     int nameY = this.y + 16;
/* 116 */     ctx.method_51433(textRenderer, detail.name, this.x + 6, nameY, GuideColors.TEXT_WHITE, true);
/*     */ 
/*     */     
/* 119 */     int headerInfoY = nameY + 14;
/* 120 */     int hx = this.x + 6;
/* 121 */     if (!detail.type.isEmpty()) {
/* 122 */       int tw = GuideTypeBadge.draw(ctx, textRenderer, detail.type, hx, headerInfoY);
/* 123 */       hx += tw + 6;
/*     */     } 
/* 125 */     if (!detail.category.isEmpty()) {
/* 126 */       String cat = detail.category.substring(0, 1).toUpperCase() + detail.category.substring(0, 1).toUpperCase();
/* 127 */       ctx.method_51433(textRenderer, cat, hx, headerInfoY + 1, getCategoryColor(detail.category), true);
/* 128 */       hx += textRenderer.method_1727(cat) + 10;
/*     */     } 
/*     */ 
/*     */     
/* 132 */     if (detail.power > 0) {
/* 133 */       ctx.method_51433(textRenderer, "Pwr: " + detail.power, hx, headerInfoY + 1, GuideColors.TEXT_PRIMARY, true);
/* 134 */       hx += textRenderer.method_1727("Pwr: " + detail.power) + 8;
/*     */     } 
/* 136 */     if (detail.accuracy > 0) {
/* 137 */       ctx.method_51433(textRenderer, "Acc: " + detail.accuracy + "%", hx, headerInfoY + 1, GuideColors.TEXT_PRIMARY, true);
/* 138 */       hx += textRenderer.method_1727("Acc: " + detail.accuracy + "%") + 8;
/*     */     } 
/* 140 */     if (detail.pp > 0) {
/* 141 */       ctx.method_51433(textRenderer, "PP: " + detail.pp, hx, headerInfoY + 1, GuideColors.TEXT_PRIMARY, true);
/*     */     }
/*     */ 
/*     */     
/* 145 */     int descY = headerInfoY + 14;
/* 146 */     if (!detail.description.isEmpty()) {
/*     */       
/* 148 */       String desc = detail.description;
/* 149 */       int maxW = this.width - 12;
/* 150 */       int lineY = descY;
/* 151 */       int maxDescY = this.y + 100 - 24;
/* 152 */       while (!desc.isEmpty() && lineY < maxDescY) {
/* 153 */         String line = desc;
/* 154 */         while (textRenderer.method_1727(line) > maxW && line.contains(" ")) {
/* 155 */           line = line.substring(0, line.lastIndexOf(' '));
/*     */         }
/*     */         
/* 158 */         if (lineY + 10 >= maxDescY && !desc.substring(line.length()).trim().isEmpty() && 
/* 159 */           line.length() > 3) line = line.substring(0, line.length() - 3) + "...";
/*     */         
/* 161 */         ctx.method_51433(textRenderer, line, this.x + 6, lineY, GuideColors.TEXT_DIM, true);
/* 162 */         desc = desc.substring(Math.min(line.length(), desc.length())).trim();
/* 163 */         lineY += 10;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 168 */     ctx.method_25294(this.x + 4, this.y + 100 - 2, this.x + this.width - 4, this.y + 100 - 1, GuideColors.BORDER_DIM);
/*     */ 
/*     */     
/* 171 */     String countLabel = "" + detail.pokemon.size() + " Pokemon can learn this move";
/* 172 */     ctx.method_51433(textRenderer, countLabel, this.x + 6, this.y + 100 - 12, GuideColors.TEXT_ACCENT, true);
/*     */ 
/*     */     
/* 175 */     if (this.pokemonSearchBar != null) {
/* 176 */       this.pokemonSearchBar.render(ctx, textRenderer, mouseX, mouseY);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 181 */     if (this.pokemonFilter.isEmpty()) {
/* 182 */       filteredList = detail.pokemon;
/*     */     } else {
/* 184 */       filteredList = new ArrayList<>();
/* 185 */       for (MoveDetail.MovePokemon p : detail.pokemon) {
/* 186 */         if (p.name.toLowerCase().contains(this.pokemonFilter) || p.slug
/* 187 */           .toLowerCase().contains(this.pokemonFilter) || 
/* 188 */           String.valueOf(p.id).contains(this.pokemonFilter)) {
/* 189 */           filteredList.add(p);
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 195 */     int totalH = filteredList.size() * 26 + 4;
/* 196 */     this.scrollArea.setContentHeight(totalH);
/* 197 */     this.scrollArea.beginRender(ctx);
/*     */     
/* 199 */     this.hoveredRowIndex = -1;
/* 200 */     int cy = this.scrollArea.getY() + 2 - this.scrollArea.getScrollOffset();
/*     */     
/* 202 */     for (int i = 0; i < filteredList.size(); i++) {
/* 203 */       MoveDetail.MovePokemon poke = filteredList.get(i);
/*     */       
/* 205 */       if (cy > this.scrollArea.getY() + this.scrollArea.getHeight() + 30)
/* 206 */         break;  if (cy + 26 < this.scrollArea.getY()) {
/* 207 */         cy += 26;
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */         
/* 213 */         boolean isHovered = (mouseX >= this.x + 2 && mouseX < this.x + this.width - 2 && mouseY >= cy && mouseY < cy + 26 && this.scrollArea.isInBounds(mouseX, mouseY));
/* 214 */         if (isHovered) this.hoveredRowIndex = i;
/*     */         
/* 216 */         int bg = isHovered ? GuideColors.CARD_HOVER_BG : ((i % 2 == 0) ? GuideColors.SECTION_BG : GuideColors.CARD_BG);
/* 217 */         ctx.method_25294(this.x + 2, cy, this.x + this.width - 2, cy + 26, bg);
/*     */ 
/*     */         
/* 220 */         int modelSize = 22;
/* 221 */         renderSmallModel(ctx, poke.slug, this.x + 4, cy + 2, modelSize);
/*     */ 
/*     */         
/* 224 */         int textX = this.x + modelSize + 8;
/* 225 */         int textY = cy + 9;
/* 226 */         String dex = String.format("#%03d ", new Object[] { Integer.valueOf(poke.id) });
/* 227 */         ctx.method_51433(textRenderer, dex, textX, textY, GuideColors.TEXT_DIM, true);
/* 228 */         int nameColor = isHovered ? GuideColors.TEXT_WHITE : GuideColors.TEXT_PRIMARY;
/* 229 */         ctx.method_51433(textRenderer, poke.name, textX + textRenderer.method_1727(dex), textY, nameColor, true);
/*     */ 
/*     */         
/* 232 */         int typeX = textX + textRenderer.method_1727(dex + dex) + 6;
/* 233 */         if (!poke.types.isEmpty()) {
/* 234 */           for (String type : poke.types.split(",")) {
/* 235 */             int tw = GuideTypeBadge.draw(ctx, textRenderer, type.trim(), typeX, textY - 1);
/* 236 */             typeX += tw + 2;
/*     */           } 
/*     */         }
/*     */ 
/*     */         
/* 241 */         ctx.method_51433(textRenderer, poke.method, this.x + this.width - textRenderer.method_1727(poke.method) - 8, textY, GuideColors.TEXT_ACCENT, true);
/*     */ 
/*     */         
/* 244 */         cy += 26;
/*     */       } 
/*     */     } 
/* 247 */     this.scrollArea.endRender(ctx);
/*     */   }
/*     */   
/*     */   private void renderSmallModel(class_332 ctx, String slug, int sx, int sy, int ss) {
/* 251 */     if (!PokemonRenderHelper.isAvailable() || slug == null || slug.isEmpty())
/* 252 */       return;  if (this.failedModelSpecies.contains(slug))
/*     */       return;  try {
/* 254 */       class_2960 speciesId = class_2960.method_60654("cobblemon:" + slug);
/* 255 */       FloatingState state = this.modelStates.computeIfAbsent(slug, k -> new FloatingState());
/* 256 */       state.setCurrentAspects(Set.of());
/*     */       
/* 258 */       class_4587 mat = ctx.method_51448();
/* 259 */       mat.method_22903();
/* 260 */       mat.method_22904(sx + ss / 2.0D, sy + 2.0D, 0.0D);
/* 261 */       float scale = ss / 9.0F;
/* 262 */       mat.method_22905(scale, scale, 1.0F);
/*     */       
/* 264 */       Quaternionf rot = new Quaternionf();
/* 265 */       rot.rotationXYZ((float)Math.toRadians(13.0D), (float)Math.toRadians(35.0D), 0.0F);
/*     */       
/* 267 */       PokemonRenderHelper.draw(speciesId, mat, rot, state, 0.0F);
/* 268 */       mat.method_22909();
/* 269 */     } catch (Exception e) {
/* 270 */       this.failedModelSpecies.add(slug);
/*     */     } 
/*     */   }
/*     */   
/*     */   private int getCategoryColor(String category) {
/* 275 */     if (category == null) return GuideColors.TEXT_DIM; 
/* 276 */     switch (category.toLowerCase()) { case "physical": case "special": case "status":  }  return 
/*     */ 
/*     */ 
/*     */       
/* 280 */       GuideColors.TEXT_DIM;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean mouseClicked(double mouseX, double mouseY, int button) {
/* 285 */     if (button == 0 && this.backHovered && this.onBack != null) {
/* 286 */       GuideSounds.playClick();
/* 287 */       this.onBack.run();
/* 288 */       return true;
/*     */     } 
/*     */     
/* 291 */     if (this.pokemonSearchBar != null && this.pokemonSearchBar.mouseClicked(mouseX, mouseY, button)) return true;
/*     */ 
/*     */     
/* 294 */     if (this.scrollArea != null && this.scrollArea.handleMouseClicked(mouseX, mouseY, button)) return true;
/*     */     
/* 296 */     if (button == 0 && this.hoveredRowIndex >= 0) {
/* 297 */       MoveDetail detail = GuideData.getInstance().getCurrentMoveDetail();
/* 298 */       if (detail != null && this.hoveredRowIndex < detail.pokemon.size()) {
/*     */         List<MoveDetail.MovePokemon> filteredList;
/*     */         
/* 301 */         if (this.pokemonFilter.isEmpty()) {
/* 302 */           filteredList = detail.pokemon;
/*     */         } else {
/* 304 */           filteredList = new ArrayList<>();
/* 305 */           for (MoveDetail.MovePokemon p : detail.pokemon) {
/* 306 */             if (p.name.toLowerCase().contains(this.pokemonFilter) || p.slug
/* 307 */               .toLowerCase().contains(this.pokemonFilter) || 
/* 308 */               String.valueOf(p.id).contains(this.pokemonFilter)) {
/* 309 */               filteredList.add(p);
/*     */             }
/*     */           } 
/*     */         } 
/* 313 */         if (this.hoveredRowIndex < filteredList.size() && this.onPokemonSelected != null) {
/* 314 */           GuideSounds.playNavigate();
/* 315 */           this.onPokemonSelected.accept(((MoveDetail.MovePokemon)filteredList.get(this.hoveredRowIndex)).slug);
/* 316 */           return true;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 321 */     return false;
/*     */   }
/*     */   
/*     */   public boolean mouseScrolled(double mouseX, double mouseY, double h, double v) {
/* 325 */     return (this.scrollArea != null && this.scrollArea.handleScroll(mouseX, mouseY, v));
/*     */   }
/*     */   
/*     */   public boolean mouseDragged(double mouseX, double mouseY, int button) {
/* 329 */     return (this.scrollArea != null && this.scrollArea.handleMouseDragged(mouseX, mouseY, button));
/*     */   }
/*     */   
/*     */   public boolean mouseReleased(double mouseX, double mouseY, int button) {
/* 333 */     return (this.scrollArea != null && this.scrollArea.handleMouseReleased(mouseX, mouseY, button));
/*     */   }
/*     */   
/*     */   public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
/* 337 */     if (this.pokemonSearchBar != null) return this.pokemonSearchBar.keyPressed(keyCode, scanCode, modifiers); 
/* 338 */     return false;
/*     */   }
/*     */   
/*     */   public boolean charTyped(char chr, int modifiers) {
/* 342 */     if (this.pokemonSearchBar != null) return this.pokemonSearchBar.charTyped(chr, modifiers); 
/* 343 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\guide\screen\views\MoveDetailView.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */