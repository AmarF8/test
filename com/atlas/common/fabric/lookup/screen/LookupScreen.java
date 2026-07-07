/*     */ package com.atlas.common.fabric.lookup.screen;
/*     */ 
/*     */ import com.atlas.common.fabric.lookup.data.LookupClientData;
/*     */ import com.atlas.common.fabric.lookup.screen.views.LookupAuditView;
/*     */ import com.atlas.common.fabric.lookup.screen.views.LookupInventoryView;
/*     */ import com.atlas.common.fabric.lookup.screen.views.LookupLedgerView;
/*     */ import com.atlas.common.fabric.lookup.screen.views.LookupLogsView;
/*     */ import com.atlas.common.fabric.lookup.screen.views.LookupNotesView;
/*     */ import com.atlas.common.fabric.lookup.screen.views.LookupOverviewView;
/*     */ import com.atlas.common.fabric.lookup.screen.views.LookupPokemonView;
/*     */ import com.atlas.common.fabric.lookup.screen.views.LookupPunishmentView;
/*     */ import com.atlas.common.fabric.lookup.screen.views.LookupSnapshotsView;
/*     */ import com.atlas.common.fabric.lookup.screen.views.LookupVaultView;
/*     */ import com.atlas.common.fabric.lookup.screen.views.LookupView;
/*     */ import java.util.Set;
/*     */ import net.minecraft.class_2561;
/*     */ import net.minecraft.class_332;
/*     */ import net.minecraft.class_437;
/*     */ 
/*     */ 
/*     */ public class LookupScreen
/*     */   extends class_437
/*     */ {
/*     */   private static final int GUI_WIDTH = 480;
/*     */   private static final int GUI_HEIGHT = 340;
/*     */   private static final int HEADER_HEIGHT = 22;
/*     */   private static final int TAB_HEIGHT = 16;
/*     */   private int guiLeft;
/*     */   private int guiTop;
/*     */   private int contentX;
/*     */   private int contentY;
/*     */   private int contentW;
/*     */   private int contentH;
/*  34 */   private static final String[] TAB_NAMES = new String[] { "Overview", "Pokemon", "Inventory", "Vault", "Logs", "Ledger", "Punishments", "Notes", "Audit", "Snapshots" };
/*     */ 
/*     */   
/*  37 */   private int activeTab = 0;
/*  38 */   private int hoveredTab = -1;
/*  39 */   private final int[] tabXPositions = new int[TAB_NAMES.length];
/*  40 */   private final int[] tabWidths = new int[TAB_NAMES.length];
/*     */   
/*     */   private boolean closeButtonHovered = false;
/*     */   
/*     */   private final LookupOverviewView overviewView;
/*     */   
/*     */   private final LookupPokemonView pokemonView;
/*     */   
/*     */   private final LookupInventoryView inventoryView;
/*     */   
/*     */   private final LookupVaultView vaultView;
/*     */   
/*     */   private final LookupLogsView logsView;
/*     */   
/*     */   private final LookupLedgerView ledgerView;
/*     */   
/*     */   private final LookupPunishmentView punishmentView;
/*     */   private final LookupNotesView notesView;
/*     */   private final LookupAuditView auditView;
/*     */   private final LookupSnapshotsView snapshotsView;
/*     */   
/*     */   public LookupScreen() {
/*  62 */     super((class_2561)class_2561.method_43471("screen.atlas_lookup.title"));
/*     */     
/*  64 */     this.overviewView = new LookupOverviewView();
/*  65 */     this.pokemonView = new LookupPokemonView();
/*  66 */     this.inventoryView = new LookupInventoryView();
/*  67 */     this.vaultView = new LookupVaultView();
/*  68 */     this.logsView = new LookupLogsView();
/*  69 */     this.ledgerView = new LookupLedgerView();
/*  70 */     this.punishmentView = new LookupPunishmentView();
/*  71 */     this.notesView = new LookupNotesView();
/*  72 */     this.auditView = new LookupAuditView();
/*  73 */     this.snapshotsView = new LookupSnapshotsView();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void method_25426() {
/*  80 */     super.method_25426();
/*  81 */     this.guiLeft = (this.field_22789 - 480) / 2;
/*  82 */     this.guiTop = (this.field_22790 - 340) / 2;
/*     */ 
/*     */     
/*  85 */     this.contentX = this.guiLeft + 2;
/*  86 */     this.contentY = this.guiTop + 22 + 16 + 2;
/*  87 */     this.contentW = 476;
/*  88 */     this.contentH = 298;
/*     */ 
/*     */     
/*  91 */     this.overviewView.init(this.contentX, this.contentY, this.contentW, this.contentH);
/*  92 */     this.pokemonView.init(this.contentX, this.contentY, this.contentW, this.contentH);
/*  93 */     this.inventoryView.init(this.contentX, this.contentY, this.contentW, this.contentH);
/*  94 */     this.vaultView.init(this.contentX, this.contentY, this.contentW, this.contentH);
/*  95 */     this.logsView.init(this.contentX, this.contentY, this.contentW, this.contentH);
/*  96 */     this.ledgerView.init(this.contentX, this.contentY, this.contentW, this.contentH);
/*  97 */     this.punishmentView.init(this.contentX, this.contentY, this.contentW, this.contentH);
/*  98 */     this.notesView.init(this.contentX, this.contentY, this.contentW, this.contentH);
/*  99 */     this.auditView.init(this.contentX, this.contentY, this.contentW, this.contentH);
/* 100 */     this.snapshotsView.init(this.contentX, this.contentY, this.contentW, this.contentH);
/*     */ 
/*     */     
/* 103 */     computeTabLayout();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void method_25432() {
/* 113 */     super.method_25432();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean method_25421() {
/* 118 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void method_25394(class_332 ctx, int mouseX, int mouseY, float delta) {
/* 126 */     ctx.method_25294(0, 0, this.field_22789, this.field_22790, LookupColors.color(0, 0, 0, 120));
/*     */ 
/*     */     
/* 129 */     drawPanel(ctx);
/* 130 */     drawHeader(ctx, mouseX, mouseY);
/* 131 */     drawTabs(ctx, mouseX, mouseY);
/* 132 */     drawContent(ctx, mouseX, mouseY, delta);
/*     */     
/* 134 */     super.method_25394(ctx, mouseX, mouseY, delta);
/*     */   }
/*     */ 
/*     */   
/*     */   public void method_25420(class_332 context, int mouseX, int mouseY, float delta) {}
/*     */ 
/*     */   
/*     */   private void drawPanel(class_332 ctx) {
/* 142 */     ctx.method_25294(this.guiLeft - 1, this.guiTop - 1, this.guiLeft + 480 + 1, this.guiTop + 340 + 1, -14012352);
/*     */ 
/*     */     
/* 145 */     ctx.method_25294(this.guiLeft, this.guiTop, this.guiLeft + 480, this.guiTop + 340, -15789801);
/*     */   }
/*     */   
/*     */   private void drawHeader(class_332 ctx, int mouseX, int mouseY) {
/* 149 */     int hx = this.guiLeft;
/* 150 */     int hy = this.guiTop;
/* 151 */     int hw = 480;
/* 152 */     int hh = 22;
/*     */ 
/*     */     
/* 155 */     ctx.method_25294(hx, hy, hx + hw, hy + hh, -15855080);
/*     */ 
/*     */     
/* 158 */     ctx.method_51433(this.field_22793, "Lookup", hx + 8, hy + 7, -10777105, true);
/*     */ 
/*     */     
/* 161 */     String targetName = LookupClientData.getInstance().getTargetName();
/* 162 */     if (targetName != null && !targetName.isEmpty()) {
/* 163 */       int nameW = this.field_22793.method_1727(targetName);
/* 164 */       ctx.method_51433(this.field_22793, targetName, hx + (hw - nameW) / 2, hy + 7, -1511169, true);
/*     */     } 
/*     */ 
/*     */     
/* 168 */     String closeLabel = "×";
/* 169 */     int closeBtnW = this.field_22793.method_1727(closeLabel) + 8;
/* 170 */     int closeBtnH = 14;
/* 171 */     int closeBtnX = hx + hw - closeBtnW - 4;
/* 172 */     int closeBtnY = hy + (hh - closeBtnH) / 2;
/* 173 */     this.closeButtonHovered = (mouseX >= closeBtnX && mouseX < closeBtnX + closeBtnW && mouseY >= closeBtnY && mouseY < closeBtnY + closeBtnH);
/*     */     
/* 175 */     if (this.closeButtonHovered) LookupSounds.playHover("lookup_close");
/*     */     
/* 177 */     int closeBg = this.closeButtonHovered ? -1030329 : -14801354;
/* 178 */     ctx.method_25294(closeBtnX, closeBtnY, closeBtnX + closeBtnW, closeBtnY + closeBtnH, closeBg);
/* 179 */     ctx.method_49601(closeBtnX, closeBtnY, closeBtnW, closeBtnH, 
/* 180 */         this.closeButtonHovered ? -1030329 : -12958368);
/*     */     
/* 182 */     int closeTextColor = this.closeButtonHovered ? -1 : -9930592;
/* 183 */     ctx.method_51433(this.field_22793, closeLabel, closeBtnX + 4, closeBtnY + 3, closeTextColor, true);
/*     */ 
/*     */     
/* 186 */     ctx.method_25294(hx, hy + hh - 1, hx + hw, hy + hh, -10777105);
/*     */   }
/*     */ 
/*     */   
/* 190 */   private static final Set<Integer> EDIT_ONLY_TABS = Set.of(Integer.valueOf(8), Integer.valueOf(9));
/*     */   
/*     */   private void drawTabs(class_332 ctx, int mouseX, int mouseY) {
/* 193 */     this.hoveredTab = -1;
/* 194 */     int tabY = this.guiTop + 22;
/* 195 */     boolean canEdit = LookupClientData.getInstance().isCanEdit();
/*     */ 
/*     */     
/* 198 */     ctx.method_25294(this.guiLeft, tabY, this.guiLeft + 480, tabY + 16, -15789801);
/*     */     
/* 200 */     for (int i = 0; i < TAB_NAMES.length; i++) {
/* 201 */       int bg, textColor, tx = this.tabXPositions[i];
/* 202 */       int tw = this.tabWidths[i];
/* 203 */       boolean active = (i == this.activeTab);
/* 204 */       boolean locked = (EDIT_ONLY_TABS.contains(Integer.valueOf(i)) && !canEdit);
/* 205 */       boolean hovered = (!locked && mouseX >= tx && mouseX < tx + tw && mouseY >= tabY && mouseY < tabY + 16);
/* 206 */       if (hovered) {
/* 207 */         this.hoveredTab = i;
/* 208 */         if (!active) LookupSounds.playHover("lookup_tab:" + i);
/*     */       
/*     */       } 
/*     */ 
/*     */       
/* 213 */       if (active) {
/* 214 */         bg = -15063744;
/* 215 */       } else if (hovered) {
/* 216 */         bg = -15459800;
/*     */       } else {
/* 218 */         bg = -15789801;
/*     */       } 
/* 220 */       ctx.method_25294(tx, tabY, tx + tw, tabY + 16, bg);
/*     */ 
/*     */       
/* 223 */       if (active) {
/* 224 */         ctx.method_25294(tx, tabY + 16 - 2, tx + tw, tabY + 16, -10777105);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 229 */       if (locked) {
/* 230 */         textColor = LookupColors.withAlpha(-9930592, 80);
/* 231 */       } else if (active) {
/* 232 */         textColor = -10777105;
/* 233 */       } else if (hovered) {
/* 234 */         textColor = -1511169;
/*     */       } else {
/* 236 */         textColor = -9930592;
/*     */       } 
/* 238 */       ctx.method_25300(this.field_22793, TAB_NAMES[i], tx + tw / 2, tabY + 4, textColor);
/*     */     } 
/*     */ 
/*     */     
/* 242 */     ctx.method_25294(this.guiLeft, tabY + 16, this.guiLeft + 480, tabY + 16 + 1, -14012352);
/*     */   }
/*     */   
/*     */   private void drawContent(class_332 ctx, int mouseX, int mouseY, float delta) {
/* 246 */     getActiveView().render(ctx, mouseX, mouseY, delta, this.field_22793);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void computeTabLayout() {
/* 252 */     int totalPadding = 10 * TAB_NAMES.length;
/* 253 */     int availableWidth = 480;
/*     */ 
/*     */     
/* 256 */     int totalTextWidth = 0;
/* 257 */     for (String name : TAB_NAMES) {
/* 258 */       totalTextWidth += this.field_22793.method_1727(name);
/*     */     }
/*     */     
/* 261 */     int tabX = this.guiLeft;
/* 262 */     for (int i = 0; i < TAB_NAMES.length; i++) {
/* 263 */       int textW = this.field_22793.method_1727(TAB_NAMES[i]);
/*     */       
/* 265 */       int tw = (int)((textW + 10) / (totalTextWidth + totalPadding) * availableWidth);
/* 266 */       tw = Math.max(tw, textW + 8);
/* 267 */       this.tabXPositions[i] = tabX;
/* 268 */       this.tabWidths[i] = tw;
/* 269 */       tabX += tw;
/*     */     } 
/*     */ 
/*     */     
/* 273 */     int remaining = this.guiLeft + 480 - tabX;
/* 274 */     if (remaining > 0 && TAB_NAMES.length > 0) {
/* 275 */       this.tabWidths[TAB_NAMES.length - 1] = this.tabWidths[TAB_NAMES.length - 1] + remaining;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean method_25402(double mouseX, double mouseY, int button) {
/* 284 */     if (button == 0 && this.closeButtonHovered) {
/* 285 */       LookupSounds.playClick();
/* 286 */       method_25419();
/* 287 */       return true;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 292 */     if (button == 0 && this.hoveredTab >= 0 && this.hoveredTab < TAB_NAMES.length && (
/* 293 */       !EDIT_ONLY_TABS.contains(Integer.valueOf(this.hoveredTab)) || LookupClientData.getInstance().isCanEdit())) {
/* 294 */       if (this.hoveredTab != this.activeTab) {
/* 295 */         LookupSounds.playTabClick();
/* 296 */         this.activeTab = this.hoveredTab;
/* 297 */         LookupSounds.resetHover();
/*     */       } 
/* 299 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 303 */     return getActiveView().mouseClicked(mouseX, mouseY, button);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean method_25401(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
/* 308 */     return getActiveView().mouseScrolled(mouseX, mouseY, horizontalAmount, verticalAmount);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean method_25404(int keyCode, int scanCode, int modifiers) {
/* 313 */     boolean handled = getActiveView().keyPressed(keyCode, scanCode, modifiers);
/* 314 */     if (handled) return true; 
/* 315 */     return super.method_25404(keyCode, scanCode, modifiers);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean method_25400(char chr, int modifiers) {
/* 320 */     boolean handled = getActiveView().charTyped(chr, modifiers);
/* 321 */     if (handled) return true; 
/* 322 */     return super.method_25400(chr, modifiers);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean method_25403(double mouseX, double mouseY, int button, double dragX, double dragY) {
/* 327 */     if (getActiveView().mouseDragged(mouseX, mouseY, button, dragX, dragY)) return true; 
/* 328 */     return super.method_25403(mouseX, mouseY, button, dragX, dragY);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean method_25406(double mouseX, double mouseY, int button) {
/* 333 */     if (getActiveView().mouseReleased(mouseX, mouseY, button)) return true; 
/* 334 */     return super.method_25406(mouseX, mouseY, button);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private LookupView getActiveView() {
/* 340 */     switch (this.activeTab) { case 0: case 1: case 2: case 3: case 4: case 5: case 6: case 7: case 8: case 9:  }  return 
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
/* 351 */       (LookupView)this.overviewView;
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\lookup\screen\LookupScreen.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */