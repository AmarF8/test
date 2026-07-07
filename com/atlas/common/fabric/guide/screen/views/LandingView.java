/*     */ package com.atlas.common.fabric.guide.screen.views;
/*     */ 
/*     */ import com.atlas.common.fabric.guide.data.GuideData;
/*     */ import com.atlas.common.fabric.guide.screen.GuideColors;
/*     */ import com.atlas.common.fabric.guide.screen.GuideSounds;
/*     */ import com.atlas.common.fabric.starterguide.PlayerStarterGuideData;
/*     */ import java.net.URI;
/*     */ import java.util.function.BiConsumer;
/*     */ import net.minecraft.class_156;
/*     */ import net.minecraft.class_327;
/*     */ import net.minecraft.class_332;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LandingView
/*     */ {
/*     */   private int x;
/*     */   private int y;
/*     */   private int width;
/*     */   private int height;
/*  24 */   private int hoveredCardIndex = -1; private boolean websiteButtonHovered = false; private BiConsumer<Integer, String> onCategorySelected;
/*     */   
/*     */   private static final class CategoryCard extends Record { private final String title;
/*     */     private final String subtitle;
/*     */     private final int accentColor;
/*     */     private final int tabIndex;
/*     */     private final String articlePath;
/*     */     
/*  32 */     private CategoryCard(String title, String subtitle, int accentColor, int tabIndex, String articlePath) { this.title = title; this.subtitle = subtitle; this.accentColor = accentColor; this.tabIndex = tabIndex; this.articlePath = articlePath; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/guide/screen/views/LandingView$CategoryCard;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #32	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*  32 */       //   0	7	0	this	Lcom/atlas/common/fabric/guide/screen/views/LandingView$CategoryCard; } public String title() { return this.title; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/guide/screen/views/LandingView$CategoryCard;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #32	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/guide/screen/views/LandingView$CategoryCard; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/guide/screen/views/LandingView$CategoryCard;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #32	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lcom/atlas/common/fabric/guide/screen/views/LandingView$CategoryCard;
/*  32 */       //   0	8	1	o	Ljava/lang/Object; } public String subtitle() { return this.subtitle; } public int accentColor() { return this.accentColor; } public int tabIndex() { return this.tabIndex; } public String articlePath() { return this.articlePath; }
/*     */      }
/*     */   
/*  35 */   private static final CategoryCard[] CARDS = new CategoryCard[] { new CategoryCard("Getting Started", "First steps & setup", GuideColors.ACCENT_EMERALD, 4, "getting-started/first-steps.md"), new CategoryCard("Pokedex", "Browse all Pokemon", 
/*     */ 
/*     */ 
/*     */         
/*  39 */         GuideColors.color(46, 204, 113, 255), 0, ""), new CategoryCard("Server Features", "Gyms, Raids, Scouts & more", GuideColors.ACCENT_TEAL, 4, "section:Server Features"), new CategoryCard("Pokemon Guides", "Spawns, Evolutions, Moves", 
/*     */ 
/*     */ 
/*     */         
/*  43 */         GuideColors.color(104, 144, 240, 255), 1, ""), new CategoryCard("Team Building", "Natures, EVs & strategies", 
/*     */         
/*  45 */         GuideColors.color(160, 100, 200, 255), 4, "team-building/natures.md"), new CategoryCard("Rideable Pokemon", "Mounts, flying & swimming", 
/*     */         
/*  47 */         GuideColors.color(240, 128, 48, 255), 5, ""), new CategoryCard("Economy & Progression", "Money, Crates & Shop", 
/*     */         
/*  49 */         GuideColors.color(248, 208, 48, 255), 4, "economy-progression/making-money.md"), new CategoryCard("Ask AI", "Search anything about the server", 
/*     */         
/*  51 */         GuideColors.color(26, 188, 156, 255), 6, "") };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void init(int x, int y, int width, int height, BiConsumer<Integer, String> onCategorySelected) {
/*  57 */     this.x = x;
/*  58 */     this.y = y;
/*  59 */     this.width = width;
/*  60 */     this.height = height;
/*  61 */     this.onCategorySelected = onCategorySelected;
/*     */   }
/*     */   
/*     */   public void updateBounds(int x, int y, int width, int height) {
/*  65 */     this.x = x;
/*  66 */     this.y = y;
/*  67 */     this.width = width;
/*  68 */     this.height = height;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(class_332 ctx, class_327 textRenderer, int mouseX, int mouseY, float delta) {
/*  75 */     ctx.method_25294(this.x, this.y, this.x + this.width, this.y + this.height, GuideColors.SECTION_BG);
/*     */ 
/*     */     
/*  78 */     int titleY = this.y + 16;
/*  79 */     ctx.method_25300(textRenderer, "Welcome to the " + GuideData.getInstance().getNetworkName() + " Guide", this.x + this.width / 2, titleY, GuideColors.TEXT_WHITE);
/*     */     
/*  81 */     titleY += 14;
/*  82 */     ctx.method_25300(textRenderer, "Choose a category to explore", this.x + this.width / 2, titleY, GuideColors.TEXT_DIM);
/*     */     
/*  84 */     titleY += 16;
/*     */ 
/*     */     
/*  87 */     String btnLabel = "View on Website →";
/*  88 */     int btnW = textRenderer.method_1727(btnLabel) + 12;
/*  89 */     int btnH = 14;
/*  90 */     int btnX = this.x + (this.width - btnW) / 2;
/*  91 */     int btnY = titleY;
/*  92 */     this.websiteButtonHovered = (mouseX >= btnX && mouseX < btnX + btnW && mouseY >= btnY && mouseY < btnY + btnH);
/*     */     
/*  94 */     if (this.websiteButtonHovered) GuideSounds.playHover("landing_website"); 
/*  95 */     int btnBg = this.websiteButtonHovered ? GuideColors.ACCENT_DARK_GREEN : GuideColors.CARD_BG;
/*  96 */     ctx.method_25294(btnX, btnY, btnX + btnW, btnY + btnH, btnBg);
/*  97 */     ctx.method_49601(btnX, btnY, btnW, btnH, 
/*  98 */         this.websiteButtonHovered ? GuideColors.ACCENT_EMERALD : GuideColors.BORDER_DIM);
/*  99 */     int btnTextColor = this.websiteButtonHovered ? GuideColors.TEXT_WHITE : GuideColors.TEXT_ACCENT;
/* 100 */     ctx.method_25300(textRenderer, btnLabel, btnX + btnW / 2, btnY + 3, btnTextColor);
/* 101 */     titleY += btnH + 8;
/*     */ 
/*     */     
/* 104 */     int margin = 10;
/* 105 */     int gap = 6;
/* 106 */     int cols = 2;
/* 107 */     int cardW = (this.width - margin * 2 - gap) / cols;
/* 108 */     int cardH = 38;
/* 109 */     int gridX = this.x + margin;
/* 110 */     int gridY = titleY;
/*     */     
/* 112 */     this.hoveredCardIndex = -1;
/*     */ 
/*     */     
/* 115 */     int highlightCardIndex = getGuideHighlightCardIndex();
/*     */     
/* 117 */     for (int i = 0; i < CARDS.length; i++) {
/* 118 */       int col = i % cols;
/* 119 */       int row = i / cols;
/* 120 */       int cx = gridX + col * (cardW + gap);
/* 121 */       int cy = gridY + row * (cardH + gap);
/*     */       
/* 123 */       boolean hovered = (mouseX >= cx && mouseX < cx + cardW && mouseY >= cy && mouseY < cy + cardH);
/*     */       
/* 125 */       if (hovered) {
/* 126 */         this.hoveredCardIndex = i;
/* 127 */         GuideSounds.playHover("landing:" + i);
/*     */       } 
/*     */ 
/*     */       
/* 131 */       if (i == highlightCardIndex && !hovered) {
/* 132 */         float pulse = (float)(Math.sin(System.currentTimeMillis() / 400.0D) * 0.5D + 0.5D);
/* 133 */         int outerAlpha = (int)(40.0F + 60.0F * pulse);
/* 134 */         int innerAlpha = (int)(80.0F + 120.0F * pulse);
/* 135 */         int outerGlow = GuideColors.color(255, 170, 20, outerAlpha);
/* 136 */         int innerGlow = GuideColors.color(255, 195, 50, innerAlpha);
/* 137 */         ctx.method_25294(cx - 2, cy - 2, cx + cardW + 2, cy + cardH + 2, outerGlow);
/* 138 */         ctx.method_25294(cx - 1, cy - 1, cx + cardW + 1, cy + cardH + 1, innerGlow);
/*     */       } 
/*     */       
/* 141 */       renderCard(ctx, textRenderer, CARDS[i], cx, cy, cardW, cardH, hovered);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderCard(class_332 ctx, class_327 textRenderer, CategoryCard card, int cx, int cy, int cw, int ch, boolean hovered) {
/* 148 */     int bg = hovered ? GuideColors.CARD_HOVER_BG : GuideColors.CARD_BG;
/* 149 */     ctx.method_25294(cx, cy, cx + cw, cy + ch, bg);
/*     */ 
/*     */     
/* 152 */     int borderColor = hovered ? GuideColors.BORDER_HIGHLIGHT : GuideColors.BORDER_DIM;
/* 153 */     ctx.method_49601(cx, cy, cw, ch, borderColor);
/*     */ 
/*     */     
/* 156 */     int accentColor = hovered ? card.accentColor : GuideColors.darken(card.accentColor, 60);
/* 157 */     ctx.method_25294(cx + 1, cy + 1, cx + 4, cy + ch - 1, accentColor);
/*     */ 
/*     */     
/* 160 */     int titleColor = hovered ? GuideColors.TEXT_WHITE : GuideColors.TEXT_PRIMARY;
/* 161 */     ctx.method_51433(textRenderer, card.title, cx + 8, cy + 8, titleColor, true);
/*     */ 
/*     */     
/* 164 */     ctx.method_51433(textRenderer, card.subtitle, cx + 8, cy + 22, GuideColors.TEXT_DIM, true);
/*     */ 
/*     */     
/* 167 */     if (hovered) {
/* 168 */       ctx.method_51433(textRenderer, "→", cx + cw - 12, cy + ch / 2 - 4, GuideColors.ACCENT_EMERALD, true);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int getGuideHighlightCardIndex() {
/* 176 */     PlayerStarterGuideData guide = PlayerStarterGuideData.getInstance();
/* 177 */     if (!guide.hasData() || guide.isCompleted()) return -1; 
/* 178 */     if (!"GUIDE_TAB".equals(guide.getStepType())) return -1; 
/* 179 */     switch (guide.getStepDetail()) { case "getting_started": case "pokedex":  }  return 
/*     */ 
/*     */       
/* 182 */       -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean mouseClicked(double mouseX, double mouseY, int button) {
/* 189 */     if (button == 0 && this.websiteButtonHovered) {
/* 190 */       GuideSounds.playClick(); 
/* 191 */       try { class_156.method_668().method_673(URI.create(GuideData.getInstance().getWikiUrl())); } catch (Exception exception) {}
/* 192 */       return true;
/*     */     } 
/* 194 */     if (button == 0 && this.hoveredCardIndex >= 0 && this.hoveredCardIndex < CARDS.length) {
/* 195 */       CategoryCard card = CARDS[this.hoveredCardIndex];
/* 196 */       if (this.onCategorySelected != null) {
/* 197 */         this.onCategorySelected.accept(Integer.valueOf(card.tabIndex), card.articlePath);
/*     */       }
/* 199 */       return true;
/*     */     } 
/* 201 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\guide\screen\views\LandingView.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */