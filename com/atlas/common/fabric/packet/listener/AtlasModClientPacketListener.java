/*     */ package com.atlas.common.fabric.packet.listener;
/*     */ 
/*     */ import com.atlas.common.event.AtlasEvent;
/*     */ import com.atlas.common.event.AtlasEventBus;
/*     */ import com.atlas.common.fabric.AtlasClientMod;
/*     */ import com.atlas.common.fabric.block.crate.packet.CrateOpenPacket;
/*     */ import com.atlas.common.fabric.block.station.StationPCGUIConfiguration;
/*     */ import com.atlas.common.fabric.block.station.client.StationRenderClientState;
/*     */ import com.atlas.common.fabric.block.station.packet.client.StationClosePacket;
/*     */ import com.atlas.common.fabric.block.station.packet.client.StationOpenPacket;
/*     */ import com.atlas.common.fabric.block.station.packet.client.StationPokemonAddedPacket;
/*     */ import com.atlas.common.fabric.block.station.packet.client.StationPokemonRemovedPacket;
/*     */ import com.atlas.common.fabric.block.station.packet.client.StationRenderStatePacket;
/*     */ import com.atlas.common.fabric.cosmetic.Cosmetic;
/*     */ import com.atlas.common.fabric.cosmetic.CosmeticRepository;
/*     */ import com.atlas.common.fabric.cosmetic.EquippedCosmetics;
/*     */ import com.atlas.common.fabric.cosmetic.EquippedCosmeticsRepository;
/*     */ import com.atlas.common.fabric.cosmetic.packet.ArmorTogglePacket;
/*     */ import com.atlas.common.fabric.cosmetic.packet.ClientboundEquippedCosmeticsUpdatePacket;
/*     */ import com.atlas.common.fabric.cosmetic.packet.CosmeticManifestPacket;
/*     */ import com.atlas.common.fabric.hud.text.packet.HudTextRemovePacket;
/*     */ import com.atlas.common.fabric.hud.text.packet.HudTextUpdateContentPacket;
/*     */ import com.atlas.common.fabric.hud.text.packet.HudTextUpdatePacket;
/*     */ import com.atlas.common.fabric.packet.AtlasModPacket;
/*     */ import com.atlas.common.fabric.packet.event.AtlasModPacketClientEvent;
/*     */ import com.atlas.common.fabric.screen.inputtext.InputTextScreen;
/*     */ import com.atlas.common.fabric.screen.inputtext.packet.InputTextOpenPacket;
/*     */ import com.cobblemon.mod.common.api.reactive.SettableObservable;
/*     */ import com.cobblemon.mod.common.client.CobblemonClient;
/*     */ import com.cobblemon.mod.common.client.gui.pc.PCGUI;
/*     */ import com.cobblemon.mod.common.client.gui.pc.PCGUIConfiguration;
/*     */ import com.cobblemon.mod.common.client.storage.ClientPC;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.List;
/*     */ import net.fabricmc.api.EnvType;
/*     */ import net.fabricmc.api.Environment;
/*     */ import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
/*     */ import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
/*     */ import net.fabricmc.fabric.api.networking.v1.PacketSender;
/*     */ import net.minecraft.class_310;
/*     */ import net.minecraft.class_437;
/*     */ import net.minecraft.class_634;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Environment(EnvType.CLIENT)
/*     */ public final class AtlasModClientPacketListener
/*     */ {
/*     */   public static void register() {
/*  55 */     ClientPlayConnectionEvents.DISCONNECT.register((handler, client) -> StationRenderClientState.reset());
/*     */     
/*  57 */     ClientPlayNetworking.registerGlobalReceiver(CosmeticManifestPacket.PACKET_ID, (packet, context) -> {
/*     */           for (Cosmetic cosmetic : packet.getCosmetics()) {
/*     */             CosmeticRepository.INSTANCE.register(cosmetic);
/*     */           }
/*     */         });
/*     */     
/*  63 */     ClientPlayNetworking.registerGlobalReceiver(ClientboundEquippedCosmeticsUpdatePacket.PACKET_ID, (packet, context) -> {
/*     */           for (EquippedCosmetics update : packet.getUpdates()) {
/*     */             EquippedCosmeticsRepository.INSTANCE.getPlayerCosmetics().put(update.getPlayer(), update);
/*     */           }
/*     */         });
/*     */     
/*  69 */     ClientPlayNetworking.registerGlobalReceiver(ArmorTogglePacket.PACKET_ID, (packet, context) -> {
/*     */           EquippedCosmetics equippedCosmetics = EquippedCosmeticsRepository.INSTANCE.getOrCreate(packet.getPlayerUuid());
/*     */           
/*     */           if (packet.isDisabled()) {
/*     */             equippedCosmetics.disableSlot(packet.getSlot());
/*     */           } else {
/*     */             equippedCosmetics.enableSlot(packet.getSlot());
/*     */           } 
/*     */         });
/*  78 */     ClientPlayNetworking.registerGlobalReceiver(CrateOpenPacket.PACKET_ID, (packet, context) -> {
/*     */           try {
/*     */             packet.getBlockEntity().open();
/*     */             callAtlasEvent((AtlasModPacket)packet, context);
/*  82 */           } catch (Exception exception) {
/*     */             throw new RuntimeException(exception);
/*     */           } 
/*     */         });
/*     */     
/*  87 */     ClientPlayNetworking.registerGlobalReceiver(HudTextRemovePacket.PACKET_ID, (packet, context) -> {
/*     */           try {
/*     */             AtlasClientMod.getHudTextProvider().handleRemovePacket(packet);
/*     */             callAtlasEvent((AtlasModPacket)packet, context);
/*  91 */           } catch (Exception exception) {
/*     */             throw new RuntimeException(exception);
/*     */           } 
/*     */         });
/*     */     
/*  96 */     ClientPlayNetworking.registerGlobalReceiver(HudTextUpdateContentPacket.PACKET_ID, (packet, context) -> {
/*     */           try {
/*     */             AtlasClientMod.getHudTextProvider().handleUpdateContentPacket(packet);
/*     */             callAtlasEvent((AtlasModPacket)packet, context);
/* 100 */           } catch (Exception exception) {
/*     */             throw new RuntimeException(exception);
/*     */           } 
/*     */         });
/*     */     
/* 105 */     ClientPlayNetworking.registerGlobalReceiver(HudTextUpdatePacket.PACKET_ID, (packet, context) -> {
/*     */           try {
/*     */             AtlasClientMod.getHudTextProvider().handleUpdatePacket(packet);
/*     */             callAtlasEvent((AtlasModPacket)packet, context);
/* 109 */           } catch (Exception exception) {
/*     */             throw new RuntimeException(exception);
/*     */           } 
/*     */         });
/*     */     
/* 114 */     ClientPlayNetworking.registerGlobalReceiver(InputTextOpenPacket.PACKET_ID, (packet, context) -> {
/*     */           try {
/*     */             class_310 client = class_310.method_1551();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*     */             client.execute(());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*     */             callAtlasEvent((AtlasModPacket)packet, context);
/* 129 */           } catch (Exception exception) {
/*     */             throw new RuntimeException(exception);
/*     */           } 
/*     */         });
/*     */     
/* 134 */     ClientPlayNetworking.registerGlobalReceiver(StationOpenPacket.PACKET_ID, (packet, context) -> {
/*     */           try {
/*     */             class_310 client = class_310.method_1551();
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
/*     */             client.execute(());
/* 157 */           } catch (Exception exception) {
/*     */             throw new RuntimeException(exception);
/*     */           } 
/*     */         });
/*     */     
/* 162 */     ClientPlayNetworking.registerGlobalReceiver(StationClosePacket.PACKET_ID, (packet, context) -> {
/*     */           try {
/*     */             class_310 client = class_310.method_1551();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*     */             client.execute(());
/* 172 */           } catch (Exception exception) {
/*     */             throw new RuntimeException(exception);
/*     */           } 
/*     */         });
/*     */     
/* 177 */     ClientPlayNetworking.registerGlobalReceiver(StationPokemonAddedPacket.PACKET_ID, (packet, context) -> {
/*     */           try {
/*     */             class_310 client = class_310.method_1551();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*     */             client.execute(());
/* 189 */           } catch (Exception exception) {
/*     */             throw new RuntimeException(exception);
/*     */           } 
/*     */         });
/*     */     
/* 194 */     ClientPlayNetworking.registerGlobalReceiver(StationPokemonRemovedPacket.PACKET_ID, (packet, context) -> {
/*     */           try {
/*     */             class_310 client = class_310.method_1551();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*     */             client.execute(());
/* 207 */           } catch (Exception exception) {
/*     */             throw new RuntimeException(exception);
/*     */           } 
/*     */         });
/*     */     
/* 212 */     ClientPlayNetworking.registerGlobalReceiver(StationRenderStatePacket.PACKET_ID, (packet, context) -> context.client().execute(()));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void callAtlasEvent(AtlasModPacket packet, ClientPlayNetworking.Context context) {
/* 218 */     class_310 client = context.client();
/* 219 */     PacketSender responseSender = context.responseSender();
/*     */ 
/*     */     
/* 222 */     AtlasEventBus.post((AtlasEvent)new AtlasModPacketClientEvent(packet, client, responseSender));
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\packet\listener\AtlasModClientPacketListener.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */