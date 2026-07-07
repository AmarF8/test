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
/*     */ import net.minecraft.class_2960;
/*     */ import net.minecraft.class_310;
/*     */ import net.minecraft.class_332;
/*     */ import net.minecraft.class_3417;
/*     */ import net.minecraft.class_342;
/*     */ import net.minecraft.class_364;
/*     */ import net.minecraft.class_437;
/*     */ import net.minecraft.class_6880;
/*     */ 
/*     */ 
/*     */ public class CardCollectionScreen
/*     */   extends class_437
/*     */ {
/*     */   private static final int GUI_W = 454;
/*     */   private static final int GUI_H = 368;
/*     */   private static final int SLOTS_PER_PAGE = 4;
/*  28 */   private static final int[] SLOT_X = new int[] { 42, 135, 228, 321 };
/*     */   
/*     */   private static final int SLOT_Y = 213;
/*     */   
/*     */   private static final int SLOT_SIZE = 89;
/*     */   
/*     */   private static final int COVER_SIZE = 64;
/*     */   private int guiLeft;
/*     */   private int guiTop;
/*     */   private int page;
/*     */   private int selectedOnPage;
/*     */   private int skillX;
/*  40 */   private String search = ""; private int skillY; private int packX; private int packY; private boolean skillHover; private boolean packHover; private boolean leftHover; private boolean rightHover; private class_342 searchField;
/*     */   
/*     */   public CardCollectionScreen() {
/*  43 */     super((class_2561)class_2561.method_43470("Card Collection"));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void method_25426() {
/*  48 */     super.method_25426();
/*  49 */     this.guiLeft = (this.field_22789 - 454) / 2;
/*  50 */     this.guiTop = (this.field_22790 - 368) / 2;
/*  51 */     this.skillX = this.guiLeft + 304;
/*  52 */     this.skillY = this.guiTop + 20;
/*  53 */     this.packX = this.guiLeft + 374;
/*  54 */     this.packY = this.guiTop + 20;
/*     */     
/*  56 */     this.searchField = new class_342(this.field_22793, this.guiLeft + 140, this.guiTop + 164, 282, 14, (class_2561)class_2561.method_43470("Search"));
/*  57 */     this.searchField.method_1880(32);
/*  58 */     this.searchField.method_1858(false);
/*  59 */     this.searchField.method_1852(this.search);
/*  60 */     this.searchField.method_1863(value -> this.search = (value == null) ? "" : value.toLowerCase(Locale.ENGLISH).trim());
/*  61 */     method_25429((class_364)this.searchField);
/*     */     
/*  63 */     if (!CardCollectionClientData.getInstance().hasOverview()) {
/*  64 */       CardCollectionNetwork.requestOverview();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean method_25421() {
/*  70 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void method_25420(class_332 ctx, int mouseX, int mouseY, float delta) {}
/*     */ 
/*     */   
/*     */   public void method_25394(class_332 ctx, int mouseX, int mouseY, float delta) {
/*  79 */     ctx.method_25294(0, 0, this.field_22789, this.field_22790, CardCollectionColors.SCRIM);
/*  80 */     CardMenuTextures.draw(ctx, "collection/background", this.guiLeft, this.guiTop, 454, 368);
/*  81 */     CardMenuTextures.draw(ctx, "collection/set_number", this.guiLeft + 20, this.guiTop + 20, 113, 27);
/*  82 */     CardMenuTextures.draw(ctx, "collection/progress_binders_wsearch", this.guiLeft + 20, this.guiTop + 56, 415, 257);
/*  83 */     CardMenuTextures.draw(ctx, "collection/lower_part", this.guiLeft + 20, this.guiTop + 318, 414, 30);
/*     */     
/*  85 */     List<CardCollectionDto.SetOverview> sets = filteredSets();
/*  86 */     int pages = Math.max(1, (sets.size() + 4 - 1) / 4);
/*  87 */     this.page = Math.max(0, Math.min(this.page, pages - 1));
/*  88 */     this.selectedOnPage = Math.max(0, Math.min(this.selectedOnPage, 3));
/*     */     
/*  90 */     drawProgress(ctx, sets);
/*  91 */     drawControls(ctx, mouseX, mouseY, pages);
/*  92 */     drawSlots(ctx, sets, mouseX, mouseY);
/*     */     
/*  94 */     this.searchField.method_25394(ctx, mouseX, mouseY, delta);
/*  95 */     super.method_25394(ctx, mouseX, mouseY, delta);
/*  96 */     CardCollectionToast.renderOverlay(ctx);
/*     */   }
/*     */   
/*     */   private void drawProgress(class_332 ctx, List<CardCollectionDto.SetOverview> sets) {
/* 100 */     CardCollectionDto.SetOverview selected = selectedSet(sets);
/* 101 */     int percent = (selected == null) ? 0 : selected.completion();
/* 102 */     drawProgressBarTexture(ctx, this.guiLeft + 147, this.guiTop + 138, 268, 7, percent);
/* 103 */     if (sets.isEmpty()) {
/* 104 */       ctx.method_25300(this.field_22793, "No matching binders", this.guiLeft + 227, this.guiTop + 252, CardCollectionColors.TEXT_DIM);
/*     */     }
/*     */   }
/*     */   
/*     */   private void drawControls(class_332 ctx, int mouseX, int mouseY, int pages) {
/* 109 */     this.skillHover = inRect(mouseX, mouseY, this.skillX, this.skillY, 65, 16);
/* 110 */     this.packHover = inRect(mouseX, mouseY, this.packX, this.packY, 60, 16);
/* 111 */     this.leftHover = (this.page > 0 && inRect(mouseX, mouseY, this.guiLeft + 21, this.guiTop + 244, 19, 25));
/* 112 */     this.rightHover = (this.page + 1 < pages && inRect(mouseX, mouseY, this.guiLeft + 414, this.guiTop + 244, 19, 25));
/*     */     
/* 114 */     CardMenuTextures.drawButton(ctx, "collection/skill_tree_button", this.skillHover, this.skillX, this.skillY, 65, 16);
/* 115 */     CardMenuTextures.drawButton(ctx, "collection/open_pack_button", this.packHover, this.packX, this.packY, 60, 16);
/* 116 */     CardMenuTextures.drawButton(ctx, "collection/left_arrow", this.leftHover, this.guiLeft + 21, this.guiTop + 244, 19, 25);
/* 117 */     CardMenuTextures.drawButton(ctx, "collection/right_arrow", this.rightHover, this.guiLeft + 414, this.guiTop + 244, 19, 25);
/*     */   }
/*     */   
/*     */   private void drawSlots(class_332 ctx, List<CardCollectionDto.SetOverview> sets, int mouseX, int mouseY) {
/* 121 */     int start = this.page * 4;
/* 122 */     for (int i = 0; i < 4; i++) {
/* 123 */       int sx = this.guiLeft + SLOT_X[i];
/* 124 */       int sy = this.guiTop + 213;
/* 125 */       boolean selected = (i == this.selectedOnPage);
/* 126 */       boolean hover = inRect(mouseX, mouseY, sx, sy, 89, 89);
/* 127 */       CardMenuTextures.draw(ctx, (selected || hover) ? "collection/binder_slot_select" : "collection/binder_slot", sx, sy, 89, 89);
/* 128 */       if (start + i < sets.size()) {
/*     */         
/* 130 */         CardCollectionDto.SetOverview set = sets.get(start + i);
/* 131 */         class_2960 cover = CardTextures.parse(set.coverTexture());
/* 132 */         if (CardTextures.exists(cover)) {
/* 133 */           CardTextures.drawAspectRotated(ctx, cover, sx + 12, sy + 8, 64, 64, CardTextures.imageAspect(cover, 1.0F), 1);
/*     */         }
/* 135 */         drawCenteredText(ctx, trim(set.name(), 82), sx + 44, sy + 72, selected ? CardCollectionColors.ACCENT_GOLD : CardCollectionColors.TEXT_PRIMARY);
/* 136 */         drawCenteredText(ctx, "" + set.completion() + "%", sx + 44, sy + 82, (set.completion() >= 100) ? CardCollectionColors.ACCENT_GREEN : CardCollectionColors.TEXT_DIM);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   private void drawCenteredText(class_332 ctx, String text, int centerX, int y, int color) {
/* 141 */     ctx.method_51433(this.field_22793, text, centerX - this.field_22793.method_1727(text) / 2, y, color, false);
/*     */   }
/*     */   
/*     */   private void drawProgressBarTexture(class_332 ctx, int x, int y, int w, int h, int percent) {
/* 145 */     CardMenuTextures.draw(ctx, "collection/experience_bar_empty", x - 1, y - 1, w + 2, h + 2);
/* 146 */     int fill = (int)(Math.max(0, Math.min(100, percent)) / 100.0D * w);
/* 147 */     if (fill <= 0)
/* 148 */       return;  ctx.method_44379(x, y, x + fill, y + h);
/* 149 */     CardMenuTextures.draw(ctx, "collection/experience", x, y, w, h);
/* 150 */     ctx.method_44380();
/*     */   }
/*     */   
/*     */   private CardCollectionDto.SetOverview selectedSet(List<CardCollectionDto.SetOverview> sets) {
/* 154 */     int selectedIndex = this.page * 4 + this.selectedOnPage;
/* 155 */     if (selectedIndex < 0 || selectedIndex >= sets.size()) return null; 
/* 156 */     return sets.get(selectedIndex);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean method_25402(double mouseX, double mouseY, int button) {
/* 161 */     if (button != 0) return super.method_25402(mouseX, mouseY, button); 
/* 162 */     List<CardCollectionDto.SetOverview> sets = filteredSets();
/* 163 */     int pages = Math.max(1, (sets.size() + 4 - 1) / 4);
/*     */     
/* 165 */     if (this.searchField.method_25402(mouseX, mouseY, button)) return true; 
/* 166 */     if (this.skillHover) {
/* 167 */       play(0.9F);
/* 168 */       CardCollectionNetwork.requestSkillTree();
/* 169 */       return true;
/*     */     } 
/* 171 */     if (this.leftHover) {
/* 172 */       play(0.9F);
/* 173 */       this.page = Math.max(0, this.page - 1);
/* 174 */       this.selectedOnPage = 0;
/* 175 */       return true;
/*     */     } 
/* 177 */     if (this.rightHover) {
/* 178 */       play(0.9F);
/* 179 */       this.page = Math.min(pages - 1, this.page + 1);
/* 180 */       this.selectedOnPage = 0;
/* 181 */       return true;
/*     */     } 
/* 183 */     int selectedIndex = this.page * 4 + this.selectedOnPage;
/* 184 */     if (this.packHover && selectedIndex < sets.size()) {
/* 185 */       play(0.9F);
/* 186 */       CardCollectionNetwork.openPack(((CardCollectionDto.SetOverview)sets.get(selectedIndex)).id());
/* 187 */       return true;
/*     */     } 
/* 189 */     for (int i = 0; i < 4; ) {
/* 190 */       int index = this.page * 4 + i;
/* 191 */       if (index >= sets.size() || 
/* 192 */         !inRect(mouseX, mouseY, this.guiLeft + SLOT_X[i], this.guiTop + 213, 89, 89)) { i++; continue; }
/* 193 */        play(0.8F);
/* 194 */       if (this.selectedOnPage == i) {
/* 195 */         CardCollectionNetwork.requestBinder(((CardCollectionDto.SetOverview)sets.get(index)).id(), 1);
/*     */       } else {
/* 197 */         this.selectedOnPage = i;
/*     */       } 
/* 199 */       return true;
/*     */     } 
/* 201 */     return super.method_25402(mouseX, mouseY, button);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean method_25404(int keyCode, int scanCode, int modifiers) {
/* 206 */     if (keyCode == 256) {
/* 207 */       method_25419();
/* 208 */       return true;
/*     */     } 
/* 210 */     if (this.searchField.method_25370() && this.searchField.method_25404(keyCode, scanCode, modifiers)) return true; 
/* 211 */     return super.method_25404(keyCode, scanCode, modifiers);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean method_25400(char chr, int modifiers) {
/* 216 */     if (this.searchField.method_25370() && this.searchField.method_25400(chr, modifiers)) return true; 
/* 217 */     return super.method_25400(chr, modifiers);
/*     */   }
/*     */   
/*     */   private List<CardCollectionDto.SetOverview> filteredSets() {
/* 221 */     CardCollectionDto.Overview overview = CardCollectionClientData.getInstance().getOverview();
/* 222 */     if (this.search.isBlank()) return overview.sets(); 
/* 223 */     List<CardCollectionDto.SetOverview> out = new ArrayList<>();
/* 224 */     for (CardCollectionDto.SetOverview set : overview.sets()) {
/* 225 */       if (set.name().toLowerCase(Locale.ENGLISH).contains(this.search) || set
/* 226 */         .id().toLowerCase(Locale.ENGLISH).contains(this.search)) {
/* 227 */         out.add(set);
/*     */       }
/*     */     } 
/* 230 */     return out;
/*     */   }
/*     */   
/*     */   private String trim(String text, int maxWidth) {
/* 234 */     if (this.field_22793.method_1727(text) <= maxWidth) return text; 
/* 235 */     String value = text;
/* 236 */     while (value.length() > 1 && this.field_22793.method_1727(value + "...") > maxWidth) {
/* 237 */       value = value.substring(0, value.length() - 1);
/*     */     }
/* 239 */     return value + "...";
/*     */   }
/*     */   
/*     */   private static boolean inRect(double mx, double my, int x, int y, int w, int h) {
/* 243 */     return (mx >= x && mx < (x + w) && my >= y && my < (y + h));
/*     */   }
/*     */   
/*     */   private void play(float pitch) {
/* 247 */     class_310.method_1551().method_1483().method_4873((class_1113)class_1109.method_47978((class_6880)class_3417.field_15015, pitch));
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\cardcollection\screen\CardCollectionScreen.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */