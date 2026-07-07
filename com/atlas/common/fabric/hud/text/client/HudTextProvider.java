/*     */ package com.atlas.common.fabric.hud.text.client;
/*     */ 
/*     */ import com.atlas.common.dictionary.Dictionary;
/*     */ import com.atlas.common.dictionary.DictionaryObject;
/*     */ import com.atlas.common.fabric.hud.text.packet.HudTextRemovePacket;
/*     */ import com.atlas.common.fabric.hud.text.packet.HudTextUpdateContentPacket;
/*     */ import com.atlas.common.fabric.hud.text.packet.HudTextUpdatePacket;
/*     */ import java.util.Collection;
/*     */ import java.util.Objects;
/*     */ import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
/*     */ import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
/*     */ import net.minecraft.class_310;
/*     */ import net.minecraft.class_332;
/*     */ import net.minecraft.class_634;
/*     */ import net.minecraft.class_9779;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ public final class HudTextProvider
/*     */ {
/*  21 */   private final Dictionary<String, ClientHudText> dictionary = new Dictionary(true);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public HudTextProvider() {
/*  27 */     HudRenderCallback.EVENT.register((drawContext, tickCounter) -> {
/*     */           if ((class_310.method_1551()).field_1724 == null) {
/*     */             return;
/*     */           }
/*     */           this.dictionary.getContent().forEach(());
/*     */         });
/*  33 */     ClientPlayConnectionEvents.DISCONNECT.register((handler, client) -> this.dictionary.cleanUp());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Collection<ClientHudText> getContent() {
/*  41 */     return this.dictionary.getContent();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void register(@NotNull ClientHudText hudText) {
/*  51 */     this.dictionary.add((DictionaryObject)hudText);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void unregister(@NotNull String identifier) {
/*  60 */     Objects.requireNonNull(this.dictionary); this.dictionary.findByIdentifier(identifier).ifPresent(this.dictionary::remove);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleUpdatePacket(@NotNull HudTextUpdatePacket packet) {
/*  70 */     ClientHudText hudText = this.dictionary.findByIdentifier(packet.getIdentifier()).orElse(null);
/*  71 */     if (hudText == null && !packet.isCreateIfNotExists())
/*  72 */       return;  if (hudText != null) {
/*  73 */       hudText.handleUpdatePacket(packet);
/*     */     } else {
/*  75 */       register(new ClientHudText(packet
/*  76 */             .getIdentifier(), packet
/*  77 */             .getText(), packet
/*  78 */             .getXPercentage(), packet
/*  79 */             .getYPercentage(), packet
/*  80 */             .getX(), packet
/*  81 */             .getY(), packet
/*  82 */             .hasShadow(), packet
/*  83 */             .isCentered(), packet
/*  84 */             .getMaximumWidth(), packet
/*  85 */             .getDefaultTextColor(), packet
/*  86 */             .getDefaultBackgroundColor()));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleUpdateContentPacket(@NotNull HudTextUpdateContentPacket packet) {
/*  98 */     this.dictionary.findByIdentifier(packet.getIdentifier()).ifPresent(hudText -> hudText.updateText(packet.getText()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleRemovePacket(@NotNull HudTextRemovePacket packet) {
/* 108 */     Objects.requireNonNull(this.dictionary); this.dictionary.findByIdentifier(packet.getIdentifier()).ifPresent(this.dictionary::remove);
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\hud\text\client\HudTextProvider.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */