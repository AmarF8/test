/*     */ package com.atlas.common.fabric.cardcollection.screen;
/*     */ 
/*     */ import com.atlas.common.fabric.cardcollection.client.CardCollectionClientData;
/*     */ import com.atlas.common.fabric.cardcollection.client.CardCollectionDto;
/*     */ import com.atlas.common.fabric.cardcollection.network.CardCollectionNetwork;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import net.minecraft.class_1109;
/*     */ import net.minecraft.class_1113;
/*     */ import net.minecraft.class_2561;
/*     */ import net.minecraft.class_2583;
/*     */ import net.minecraft.class_310;
/*     */ import net.minecraft.class_332;
/*     */ import net.minecraft.class_3417;
/*     */ import net.minecraft.class_437;
/*     */ import net.minecraft.class_6880;
/*     */ 
/*     */ 
/*     */ public final class CardSkillTreeScreen
/*     */   extends class_437
/*     */ {
/*     */   private static final int GUI_W = 454;
/*     */   private static final int GUI_H = 368;
/*  25 */   private static final int[] NODE_X = new int[] { 210, 122, 298, 210, 97, 323, 185, 235, 122, 298 };
/*  26 */   private static final int[] NODE_Y = new int[] { 75, 100, 100, 128, 137, 137, 165, 165, 220, 220 };
/*     */   
/*     */   private int guiLeft;
/*     */   
/*     */   private int guiTop;
/*  31 */   private int hoveredNode = -1; private int backX; private int backY; private boolean backHover;
/*     */   
/*     */   public CardSkillTreeScreen() {
/*  34 */     super((class_2561)class_2561.method_43470("Card Skill Tree"));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void method_25426() {
/*  39 */     super.method_25426();
/*  40 */     this.guiLeft = (this.field_22789 - 454) / 2;
/*  41 */     this.guiTop = (this.field_22790 - 368) / 2;
/*  42 */     this.backX = this.guiLeft + 25;
/*  43 */     this.backY = this.guiTop + 323;
/*  44 */     CardCollectionNetwork.requestSkillTree();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean method_25421() {
/*  49 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void method_25420(class_332 ctx, int mouseX, int mouseY, float delta) {}
/*     */ 
/*     */   
/*     */   public void method_25394(class_332 ctx, int mouseX, int mouseY, float delta) {
/*  58 */     CardCollectionDto.SkillTreeData data = CardCollectionClientData.getInstance().getSkillTree();
/*     */     
/*  60 */     ctx.method_25294(0, 0, this.field_22789, this.field_22790, CardCollectionColors.SCRIM);
/*  61 */     CardMenuTextures.draw(ctx, "skill_tree/background", this.guiLeft, this.guiTop, 454, 368);
/*  62 */     CardMenuTextures.draw(ctx, "skill_tree/top", this.guiLeft + 20, this.guiTop + 38, 414, 24);
/*  63 */     CardMenuTextures.draw(ctx, "skill_tree/middle", this.guiLeft + 20, this.guiTop + 62, 414, 252);
/*  64 */     CardMenuTextures.draw(ctx, "skill_tree/lower_part", this.guiLeft + 20, this.guiTop + 318, 414, 30);
/*     */     
/*  66 */     ctx.method_51433(this.field_22793, "SKILL TREE", this.guiLeft + 31, this.guiTop + 25, CardCollectionColors.TEXT_WHITE, true);
/*  67 */     String points = "Points: " + data.availablePoints() + " / " + data.totalPoints();
/*  68 */     ctx.method_51433(this.field_22793, points, this.guiLeft + 424 - this.field_22793.method_1727(points), this.guiTop + 25, CardCollectionColors.ACCENT_GOLD, true);
/*     */     
/*  70 */     drawConnectors(ctx, data);
/*  71 */     drawNodes(ctx, data, mouseX, mouseY);
/*     */     
/*  73 */     this.backHover = inRect(mouseX, mouseY, this.backX, this.backY, 114, 20);
/*  74 */     CardMenuTextures.drawButton(ctx, "skill_tree/card_collection_button", this.backHover, this.backX, this.backY, 114, 20);
/*     */     
/*  76 */     if (this.hoveredNode >= 0 && this.hoveredNode < data.nodes().size()) {
/*  77 */       drawSkillTooltip(ctx, data.nodes().get(this.hoveredNode), mouseX, mouseY);
/*     */     }
/*  79 */     super.method_25394(ctx, mouseX, mouseY, delta);
/*     */   }
/*     */   
/*     */   private void drawConnectors(class_332 ctx, CardCollectionDto.SkillTreeData data) {
/*  83 */     for (int i = 0; i < data.nodes().size() && i < NODE_X.length; i++) {
/*  84 */       CardCollectionDto.SkillNode node = data.nodes().get(i);
/*  85 */       if (i >= 4) {
/*  86 */         String color = node.unlocked() ? "green" : (node.available() ? "blue" : "gray");
/*  87 */         int x = this.guiLeft + NODE_X[i] + 14;
/*  88 */         int y = this.guiTop + NODE_Y[i] - 34;
/*  89 */         CardMenuTextures.draw(ctx, "skill_tree/ways/" + color + "_vertical", x, y, 16, 16);
/*  90 */         CardMenuTextures.draw(ctx, "skill_tree/ways/" + color + "_vertical", x, y + 16, 16, 16);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void drawNodes(class_332 ctx, CardCollectionDto.SkillTreeData data, int mouseX, int mouseY) {
/*  96 */     this.hoveredNode = -1;
/*  97 */     for (int i = 0; i < data.nodes().size() && i < NODE_X.length; i++) {
/*  98 */       CardCollectionDto.SkillNode node = data.nodes().get(i);
/*  99 */       int size = 34;
/*     */       
/* 101 */       int x = this.guiLeft + NODE_X[i];
/* 102 */       int y = this.guiTop + NODE_Y[i];
/* 103 */       boolean hover = inRect(mouseX, mouseY, x, y, size, size);
/* 104 */       if (hover) this.hoveredNode = i;
/*     */       
/* 106 */       String base = "skill_x1";
/* 107 */       String suffix = node.unlocked() ? "_green" : (node.available() ? "" : "_gray");
/* 108 */       CardMenuTextures.draw(ctx, "skill_tree/" + base + suffix, x, y, size, size);
/* 109 */       ctx.method_25300(this.field_22793, String.valueOf(node.level()), x + size / 2, y + size / 2 - 4, node.unlocked() ? CardCollectionColors.TEXT_WHITE : CardCollectionColors.TEXT_DIM);
/*     */       
/* 111 */       String label = trim(node.name(), 78);
/* 112 */       drawCenteredText(ctx, label, x + size / 2, y + size + 4, hover ? CardCollectionColors.ACCENT_GOLD : CardCollectionColors.TEXT_DIM);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void drawCenteredText(class_332 ctx, String text, int centerX, int y, int color) {
/* 117 */     ctx.method_51433(this.field_22793, text, centerX - this.field_22793.method_1727(text) / 2, y, color, false);
/*     */   }
/*     */   
/*     */   private void drawSkillTooltip(class_332 ctx, CardCollectionDto.SkillNode node, int mouseX, int mouseY) {
/* 121 */     List<class_2561> lines = new ArrayList<>();
/* 122 */     lines.add(class_2561.method_43470(node.name()).method_27694(style -> style.method_36139(15655630).method_10982(Boolean.valueOf(true))));
/* 123 */     lines.add(class_2561.method_43470(node.description()).method_27694(style -> style.method_36139(11047542)));
/* 124 */     lines.add(class_2561.method_43470("Level " + node.level() + " / " + node.maxLevel()).method_27694(style -> style.method_36139(7915630)));
/* 125 */     lines.add(class_2561.method_43470("Current: +" + pct(node.currentPercent()) + "%").method_27694(style -> style.method_36139(16765518)));
/* 126 */     lines.add(class_2561.method_43470("Next: +" + pct(node.percentPerLevel()) + "% for " + node.cost() + " points").method_27694(style -> style.method_36139(11047542)));
/* 127 */     if (!node.available() && node.level() < node.maxLevel()) {
/* 128 */       lines.add(class_2561.method_43470("Requires points or an earlier branch.").method_27694(style -> style.method_36139(14047310)));
/*     */     }
/* 130 */     ctx.method_51434(this.field_22793, lines, mouseX, mouseY);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean method_25402(double mouseX, double mouseY, int button) {
/* 135 */     if (button != 0) return super.method_25402(mouseX, mouseY, button); 
/* 136 */     if (this.backHover) {
/* 137 */       play(0.9F);
/* 138 */       CardCollectionNetwork.requestOverview();
/* 139 */       class_310.method_1551().method_1507(new CardCollectionScreen());
/* 140 */       return true;
/*     */     } 
/* 142 */     CardCollectionDto.SkillTreeData data = CardCollectionClientData.getInstance().getSkillTree();
/* 143 */     if (this.hoveredNode >= 0 && this.hoveredNode < data.nodes().size()) {
/* 144 */       CardCollectionDto.SkillNode node = data.nodes().get(this.hoveredNode);
/* 145 */       if (node.available()) {
/* 146 */         play(1.0F);
/* 147 */         CardCollectionNetwork.unlockSkill(node.id());
/*     */       } else {
/* 149 */         play(1.5F);
/*     */       } 
/* 151 */       return true;
/*     */     } 
/* 153 */     return super.method_25402(mouseX, mouseY, button);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean method_25404(int keyCode, int scanCode, int modifiers) {
/* 158 */     if (keyCode == 256) {
/* 159 */       CardCollectionNetwork.requestOverview();
/* 160 */       class_310.method_1551().method_1507(new CardCollectionScreen());
/* 161 */       return true;
/*     */     } 
/* 163 */     return super.method_25404(keyCode, scanCode, modifiers);
/*     */   }
/*     */   
/*     */   private String trim(String text, int maxWidth) {
/* 167 */     if (this.field_22793.method_1727(text) <= maxWidth) return text; 
/* 168 */     String value = text;
/* 169 */     while (value.length() > 1 && this.field_22793.method_1727(value + "...") > maxWidth) {
/* 170 */       value = value.substring(0, value.length() - 1);
/*     */     }
/* 172 */     return value + "...";
/*     */   }
/*     */   
/*     */   private static String pct(double value) {
/* 176 */     return (value == Math.rint(value)) ? String.valueOf((int)value) : String.format(Locale.US, "%.1f", new Object[] { Double.valueOf(value) });
/*     */   }
/*     */   
/*     */   private static boolean inRect(double mx, double my, int x, int y, int w, int h) {
/* 180 */     return (mx >= x && mx < (x + w) && my >= y && my < (y + h));
/*     */   }
/*     */   
/*     */   private void play(float pitch) {
/* 184 */     class_310.method_1551().method_1483().method_4873((class_1113)class_1109.method_47978((class_6880)class_3417.field_15015, pitch));
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\cardcollection\screen\CardSkillTreeScreen.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */