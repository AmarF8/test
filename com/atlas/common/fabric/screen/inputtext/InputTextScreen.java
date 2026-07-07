/*     */ package com.atlas.common.fabric.screen.inputtext;
/*     */ 
/*     */ import com.atlas.common.fabric.packet.AtlasModPacket;
/*     */ import com.atlas.common.fabric.screen.inputtext.packet.InputTextPayloadPacket;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import net.fabricmc.api.EnvType;
/*     */ import net.fabricmc.api.Environment;
/*     */ import net.minecraft.class_2561;
/*     */ import net.minecraft.class_310;
/*     */ import net.minecraft.class_332;
/*     */ import net.minecraft.class_333;
/*     */ import net.minecraft.class_364;
/*     */ import net.minecraft.class_4185;
/*     */ import net.minecraft.class_437;
/*     */ import net.minecraft.class_5348;
/*     */ import net.minecraft.class_5481;
/*     */ import net.minecraft.class_7529;
/*     */ import net.minecraft.class_8710;
/*     */ import org.jetbrains.annotations.NotNull;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Environment(EnvType.CLIENT)
/*     */ public final class InputTextScreen
/*     */   extends class_437
/*     */ {
/*     */   private final String identifier;
/*     */   private final class_2561 title;
/*     */   private final class_2561 subtitle;
/*     */   private final class_2561 buttonText;
/*     */   private final int maximumTextLength;
/*     */   private final int textBoxWidth;
/*     */   private final int textBoxHeight;
/*     */   private final boolean allowEmpty;
/*     */   private class_7529 editBoxWidget;
/*     */   private class_4185 doneButton;
/*     */   
/*     */   public InputTextScreen(@NotNull String identifier, @NotNull class_2561 title, @NotNull class_2561 subtitle, @NotNull class_2561 buttonText, int maximumTextLength, int textBoxWidth, int textBoxHeight, boolean allowEmpty) {
/*  60 */     super(class_333.field_18967);
/*  61 */     this.identifier = Objects.<String>requireNonNull(identifier);
/*  62 */     this.title = Objects.<class_2561>requireNonNull(title);
/*  63 */     this.subtitle = Objects.<class_2561>requireNonNull(subtitle);
/*  64 */     this.buttonText = Objects.<class_2561>requireNonNull(buttonText);
/*  65 */     this.maximumTextLength = Math.max(maximumTextLength, 1);
/*  66 */     this.textBoxWidth = Math.max(textBoxWidth, 5);
/*  67 */     this.textBoxHeight = Math.max(textBoxHeight, 15);
/*  68 */     this.allowEmpty = allowEmpty;
/*     */   }
/*     */   
/*     */   private void updateButtonState() {
/*  72 */     this.doneButton.field_22763 = (this.allowEmpty || !this.editBoxWidget.method_44405().isEmpty());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void method_25426() {
/*  81 */     this
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  88 */       .editBoxWidget = new class_7529(this.field_22793, this.field_22789 / 2 - this.textBoxWidth / 2, this.field_22790 / 2 - this.textBoxHeight / 2 - 6, this.textBoxWidth, this.textBoxHeight, (class_2561)class_2561.method_43473(), (class_2561)class_2561.method_43473());
/*  89 */     this.editBoxWidget.method_44402(this.maximumTextLength);
/*  90 */     this.editBoxWidget.method_44401(s -> updateButtonState());
/*  91 */     this.editBoxWidget.method_25365(true);
/*     */     
/*  93 */     method_37063((class_364)this.editBoxWidget);
/*  94 */     method_48265((class_364)this.editBoxWidget);
/*     */ 
/*     */     
/*  97 */     int buttonWidth = 20 + this.field_22793.method_27525((class_5348)this.buttonText);
/*  98 */     this.doneButton = (class_4185)method_37063((class_364)class_4185.method_46430(this.buttonText, button -> class_310.method_1551().method_1507(null)).method_46434(this.field_22789 / 2 - buttonWidth / 2, this.field_22790 / 2 + this.textBoxHeight / 2 + 6, buttonWidth, 20)
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 103 */         .method_46431());
/*     */ 
/*     */     
/* 106 */     updateButtonState();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void method_25410(class_310 client, int width, int height) {
/* 115 */     method_25423(client, width, height);
/*     */     
/* 117 */     this.editBoxWidget.method_44400(this.editBoxWidget.method_44405());
/*     */     
/* 119 */     updateButtonState();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void method_25432() {
/* 128 */     AtlasModPacket.sendToServer((class_8710)new InputTextPayloadPacket(this.identifier, this.editBoxWidget.method_44405()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean method_25404(int keyCode, int scanCode, int modifiers) {
/* 136 */     if (super.method_25404(keyCode, scanCode, modifiers)) return true; 
/* 137 */     return this.editBoxWidget.method_25404(keyCode, scanCode, modifiers);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean method_25400(char chr, int modifiers) {
/* 145 */     return this.editBoxWidget.method_25400(chr, modifiers);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean method_25402(double mouseX, double mouseY, int button) {
/* 153 */     if (this.editBoxWidget.method_25405(mouseX, mouseY)) this.editBoxWidget.method_25365(true); 
/* 154 */     return super.method_25402(mouseX, mouseY, button);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void calculateAndRenderTitleAndSubtitle(@NotNull class_332 context) {
/* 161 */     List<class_5481> lines = this.subtitle.getString().isEmpty() ? new ArrayList<>(0) : this.field_22793.method_1728((class_5348)this.subtitle, this.editBoxWidget.method_25368() - 20);
/* 162 */     int calculatedTitleY = this.editBoxWidget.method_46427() - lines.size() * 10 - 20;
/*     */     
/* 164 */     if (!this.title.getString().isEmpty()) {
/* 165 */       context.method_27534(this.field_22793, this.title, this.field_22789 / 2, calculatedTitleY, 16777215);
/*     */     }
/* 167 */     for (int i = 0; i < lines.size(); i++) {
/* 168 */       context.method_35719(this.field_22793, lines.get(i), this.field_22789 / 2, calculatedTitleY + 10 + i * 10, 12632256);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void method_25394(@NotNull class_332 context, int mouseX, int mouseY, float delta) {
/* 177 */     method_25420(context, mouseX, mouseY, delta);
/*     */ 
/*     */     
/* 180 */     this.editBoxWidget.method_25394(context, mouseX, mouseY, delta);
/*     */ 
/*     */     
/* 183 */     super.method_25394(context, mouseX, mouseY, delta);
/*     */ 
/*     */     
/* 186 */     calculateAndRenderTitleAndSubtitle(context);
/* 187 */     context.method_27534(this.field_22793, class_2561.method_30163("(Press ESC to cancel)"), this.field_22789 / 2, this.doneButton.method_46427() + this.doneButton.method_25364() + 5, 12632256);
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\screen\inputtext\InputTextScreen.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */