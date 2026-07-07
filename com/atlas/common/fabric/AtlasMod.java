/*     */ package com.atlas.common.fabric;
/*     */ 
/*     */ import com.atlas.common.fabric.auction.network.AuctionNetwork;
/*     */ import com.atlas.common.fabric.battle.ui.BattleUiNetwork;
/*     */ import com.atlas.common.fabric.battletower.BattleTower;
/*     */ import com.atlas.common.fabric.block.crate.packet.CrateOpenPacket;
/*     */ import com.atlas.common.fabric.block.station.packet.client.StationClosePacket;
/*     */ import com.atlas.common.fabric.block.station.packet.client.StationOpenPacket;
/*     */ import com.atlas.common.fabric.block.station.packet.client.StationPokemonAddedPacket;
/*     */ import com.atlas.common.fabric.block.station.packet.client.StationPokemonRemovedPacket;
/*     */ import com.atlas.common.fabric.block.station.packet.client.StationRenderStatePacket;
/*     */ import com.atlas.common.fabric.block.station.packet.server.StationAddPokemonPacket;
/*     */ import com.atlas.common.fabric.block.station.packet.server.StationRemoveAllPokemonPacket;
/*     */ import com.atlas.common.fabric.block.station.packet.server.StationRemovePokemonPacket;
/*     */ import com.atlas.common.fabric.cardcollection.network.CardCollectionNetwork;
/*     */ import com.atlas.common.fabric.cardmarket.network.CardMarketNetwork;
/*     */ import com.atlas.common.fabric.claim.network.ClaimMapNetwork;
/*     */ import com.atlas.common.fabric.clan.network.ClanNetwork;
/*     */ import com.atlas.common.fabric.clientverify.ClientVerifyNetwork;
/*     */ import com.atlas.common.fabric.cosmetic.packet.ArmorTogglePacket;
/*     */ import com.atlas.common.fabric.cosmetic.packet.ClientboundEquippedCosmeticsUpdatePacket;
/*     */ import com.atlas.common.fabric.cosmetic.packet.CosmeticManifestPacket;
/*     */ import com.atlas.common.fabric.cosmetic.preview.CosmeticPreviewNetwork;
/*     */ import com.atlas.common.fabric.effect.EffectRegistry;
/*     */ import com.atlas.common.fabric.emoji.network.EmojiNetwork;
/*     */ import com.atlas.common.fabric.foreverpack.network.ForeverPackNetwork;
/*     */ import com.atlas.common.fabric.gocatch.network.GoCatchNetwork;
/*     */ import com.atlas.common.fabric.gocatch.particle.GoCatchParticleTypes;
/*     */ import com.atlas.common.fabric.guide.network.GuideNetwork;
/*     */ import com.atlas.common.fabric.gymcircuit.network.GymCircuitNetwork;
/*     */ import com.atlas.common.fabric.hud.text.packet.HudTextRemovePacket;
/*     */ import com.atlas.common.fabric.hud.text.packet.HudTextUpdateContentPacket;
/*     */ import com.atlas.common.fabric.hud.text.packet.HudTextUpdatePacket;
/*     */ import com.atlas.common.fabric.item.AtlasItemRegistry;
/*     */ import com.atlas.common.fabric.lookup.network.LookupNetwork;
/*     */ import com.atlas.common.fabric.morph.network.MorphNetwork;
/*     */ import com.atlas.common.fabric.morph.runtime.network.MorphRuntimeNetwork;
/*     */ import com.atlas.common.fabric.packet.PokemonCosmeticPacket;
/*     */ import com.atlas.common.fabric.patchnotes.network.PatchNotesNetwork;
/*     */ import com.atlas.common.fabric.pc.packet.BulkSealPokemonPacket;
/*     */ import com.atlas.common.fabric.pc.packet.SealPokemonPacket;
/*     */ import com.atlas.common.fabric.ranked.network.RankedNetwork;
/*     */ import com.atlas.common.fabric.rustlingspots.network.RustlingSpotsNetwork;
/*     */ import com.atlas.common.fabric.rustlingspots.particle.RustlingParticleTypes;
/*     */ import com.atlas.common.fabric.safari.board.network.SafariBoardNetwork;
/*     */ import com.atlas.common.fabric.safari.expedition.entity.SafariExpeditionBalloonEntities;
/*     */ import com.atlas.common.fabric.safari.expedition.network.SafariExpeditionNetwork;
/*     */ import com.atlas.common.fabric.safari.fishing.SafariFishingManager;
/*     */ import com.atlas.common.fabric.safari.fishing.SafariFishingNetwork;
/*     */ import com.atlas.common.fabric.safari.fishing.entity.SafariFishEntities;
/*     */ import com.atlas.common.fabric.safari.meteor.entity.SafariMeteorEntities;
/*     */ import com.atlas.common.fabric.safari.racing.RacingNetworkHelper;
/*     */ import com.atlas.common.fabric.screen.inputtext.packet.InputTextOpenPacket;
/*     */ import com.atlas.common.fabric.screen.inputtext.packet.InputTextPayloadPacket;
/*     */ import com.atlas.common.fabric.shop.network.ShopNetwork;
/*     */ import com.atlas.common.fabric.skindex.network.SkinDexNetwork;
/*     */ import com.atlas.common.fabric.starterguide.network.StarterGuideNetwork;
/*     */ import com.atlas.common.fabric.vote.network.VoteNetwork;
/*     */ import net.fabricmc.api.ModInitializer;
/*     */ import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public final class AtlasMod
/*     */   implements ModInitializer
/*     */ {
/*     */   @NotNull
/*     */   public static final String MOD_ID = "atlas-common-fabric";
/*     */   @NotNull
/*  71 */   public static final Logger LOGGER = LoggerFactory.getLogger("atlas-common-fabric");
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
/*     */   public void onInitialize() {
/*  85 */     EffectRegistry.registerReloadListener();
/*     */ 
/*     */     
/*  88 */     AtlasItemRegistry.initialize();
/*  89 */     BattleTower.init();
/*  90 */     BattleUiNetwork.init();
/*     */     
/*  92 */     SafariFishEntities.init();
/*  93 */     SafariExpeditionBalloonEntities.init();
/*  94 */     SafariMeteorEntities.init();
/*  95 */     SafariFishingNetwork.init();
/*  96 */     SafariFishingManager.register();
/*  97 */     SafariBoardNetwork.init();
/*  98 */     SafariExpeditionNetwork.init();
/*  99 */     ClientVerifyNetwork.init();
/* 100 */     ClaimMapNetwork.init();
/* 101 */     RankedNetwork.init();
/* 102 */     ClanNetwork.init();
/* 103 */     GuideNetwork.init();
/* 104 */     GymCircuitNetwork.init();
/* 105 */     LookupNetwork.init();
/* 106 */     SkinDexNetwork.init();
/* 107 */     CardCollectionNetwork.init();
/* 108 */     CardMarketNetwork.init();
/* 109 */     CosmeticPreviewNetwork.init();
/* 110 */     EmojiNetwork.init();
/* 111 */     AuctionNetwork.init();
/* 112 */     VoteNetwork.init();
/* 113 */     PatchNotesNetwork.init();
/* 114 */     ForeverPackNetwork.init();
/* 115 */     MorphNetwork.init();
/* 116 */     MorphRuntimeNetwork.init();
/* 117 */     ShopNetwork.init();
/* 118 */     StarterGuideNetwork.init();
/* 119 */     RacingNetworkHelper.registerPacketTypes();
/* 120 */     RustlingSpotsNetwork.init();
/* 121 */     RustlingParticleTypes.register();
/* 122 */     GoCatchNetwork.init();
/* 123 */     GoCatchParticleTypes.register();
/*     */     
/* 125 */     PayloadTypeRegistry.playS2C().register(CrateOpenPacket.PACKET_ID, CrateOpenPacket.CODEC);
/* 126 */     PayloadTypeRegistry.playS2C().register(HudTextRemovePacket.PACKET_ID, HudTextRemovePacket.CODEC);
/* 127 */     PayloadTypeRegistry.playS2C().register(HudTextUpdateContentPacket.PACKET_ID, HudTextUpdateContentPacket.CODEC);
/* 128 */     PayloadTypeRegistry.playS2C().register(HudTextUpdatePacket.PACKET_ID, HudTextUpdatePacket.CODEC);
/* 129 */     PayloadTypeRegistry.playS2C().register(InputTextOpenPacket.PACKET_ID, InputTextOpenPacket.CODEC);
/* 130 */     PayloadTypeRegistry.playC2S().register(InputTextPayloadPacket.PACKET_ID, InputTextPayloadPacket.CODEC);
/* 131 */     PayloadTypeRegistry.playC2S().register(PokemonCosmeticPacket.PACKET_ID, PokemonCosmeticPacket.CODEC);
/* 132 */     PayloadTypeRegistry.playC2S().register(SealPokemonPacket.PACKET_ID, SealPokemonPacket.CODEC);
/* 133 */     PayloadTypeRegistry.playC2S().register(BulkSealPokemonPacket.PACKET_ID, BulkSealPokemonPacket.CODEC);
/* 134 */     PayloadTypeRegistry.playS2C().register(CosmeticManifestPacket.PACKET_ID, CosmeticManifestPacket.CODEC);
/* 135 */     PayloadTypeRegistry.playS2C().register(ClientboundEquippedCosmeticsUpdatePacket.PACKET_ID, ClientboundEquippedCosmeticsUpdatePacket.CODEC);
/* 136 */     PayloadTypeRegistry.playS2C().register(ArmorTogglePacket.PACKET_ID, ArmorTogglePacket.CODEC);
/*     */ 
/*     */ 
/*     */     
/* 140 */     PayloadTypeRegistry.playC2S().register(StationAddPokemonPacket.PACKET_ID, StationAddPokemonPacket.CODEC);
/* 141 */     PayloadTypeRegistry.playC2S().register(StationRemovePokemonPacket.PACKET_ID, StationRemovePokemonPacket.CODEC);
/* 142 */     PayloadTypeRegistry.playC2S().register(StationRemoveAllPokemonPacket.PACKET_ID, StationRemoveAllPokemonPacket.CODEC);
/*     */ 
/*     */     
/* 145 */     PayloadTypeRegistry.playS2C().register(StationOpenPacket.PACKET_ID, StationOpenPacket.CODEC);
/* 146 */     PayloadTypeRegistry.playS2C().register(StationClosePacket.PACKET_ID, StationClosePacket.CODEC);
/*     */ 
/*     */     
/* 149 */     PayloadTypeRegistry.playS2C().register(StationPokemonAddedPacket.PACKET_ID, StationPokemonAddedPacket.CODEC);
/* 150 */     PayloadTypeRegistry.playS2C().register(StationPokemonRemovedPacket.PACKET_ID, StationPokemonRemovedPacket.CODEC);
/* 151 */     PayloadTypeRegistry.playS2C().register(StationRenderStatePacket.PACKET_ID, StationRenderStatePacket.CODEC);
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\AtlasMod.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */