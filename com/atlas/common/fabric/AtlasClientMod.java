/*     */ package com.atlas.common.fabric;
/*     */ import com.atlas.common.event.AtlasEvent;
/*     */ import com.atlas.common.event.AtlasEventBus;
/*     */ import com.atlas.common.event.tick.AtlasTickEvent;
/*     */ import com.atlas.common.fabric.auction.network.AuctionNetwork;
/*     */ import com.atlas.common.fabric.battle.ui.BattleUiNetwork;
/*     */ import com.atlas.common.fabric.battletower.BattleTowerEntities;
/*     */ import com.atlas.common.fabric.battletower.network.BattleTowerNetwork;
/*     */ import com.atlas.common.fabric.block.AtlasBlockRegistry;
/*     */ import com.atlas.common.fabric.block.animated.render.AnimatedBlockRenderer;
/*     */ import com.atlas.common.fabric.block.station.entity.GenericStationBlockEntity;
/*     */ import com.atlas.common.fabric.block.station.entity.StationBlockEntity;
/*     */ import com.atlas.common.fabric.block.station.render.StationBlockEntityRenderer;
/*     */ import com.atlas.common.fabric.block.statue.entity.StatueBlockEntity;
/*     */ import com.atlas.common.fabric.block.statue.render.StatueBlockEntityRenderer;
/*     */ import com.atlas.common.fabric.cardcollection.network.CardCollectionNetwork;
/*     */ import com.atlas.common.fabric.cardcollection.screen.CardCollectionToast;
/*     */ import com.atlas.common.fabric.cardmarket.network.CardMarketNetwork;
/*     */ import com.atlas.common.fabric.claim.network.ClaimMapNetwork;
/*     */ import com.atlas.common.fabric.clan.network.ClanNetwork;
/*     */ import com.atlas.common.fabric.cosmetic.preview.CosmeticPreviewNetwork;
/*     */ import com.atlas.common.fabric.emoji.network.EmojiNetwork;
/*     */ import com.atlas.common.fabric.foreverpack.network.ForeverPackNetwork;
/*     */ import com.atlas.common.fabric.gocatch.client.GoCatchClientThrowTracker;
/*     */ import com.atlas.common.fabric.gocatch.client.GoCatchCurveBallDetector;
/*     */ import com.atlas.common.fabric.gocatch.client.GoCatchCurveBallTrailTracker;
/*     */ import com.atlas.common.fabric.gocatch.client.GoCatchHudFeedbackRenderer;
/*     */ import com.atlas.common.fabric.gocatch.client.GoCatchTargetingTracker;
/*     */ import com.atlas.common.fabric.gocatch.client.GoCatchThrowEffectsHandler;
/*     */ import com.atlas.common.fabric.gocatch.client.GoCatchThrowFeedbackTracker;
/*     */ import com.atlas.common.fabric.gocatch.network.GoCatchNetwork;
/*     */ import com.atlas.common.fabric.guide.network.GuideNetwork;
/*     */ import com.atlas.common.fabric.gymcircuit.network.GymCircuitNetwork;
/*     */ import com.atlas.common.fabric.hud.text.client.HudTextProvider;
/*     */ import com.atlas.common.fabric.item.AtlasItemRegistry;
/*     */ import com.atlas.common.fabric.morph.network.MorphNetwork;
/*     */ import com.atlas.common.fabric.morph.runtime.client.MorphClientHandler;
/*     */ import com.atlas.common.fabric.morph.runtime.network.MorphRuntimeNetwork;
/*     */ import com.atlas.common.fabric.packet.AtlasModPacket;
/*     */ import com.atlas.common.fabric.packet.PokemonCosmeticPacket;
/*     */ import com.atlas.common.fabric.packet.listener.AtlasModClientPacketListener;
/*     */ import com.atlas.common.fabric.patchnotes.network.PatchNotesNetwork;
/*     */ import com.atlas.common.fabric.rustlingspots.client.RustlingSpotClientHandler;
/*     */ import com.atlas.common.fabric.rustlingspots.network.RustlingSpotsNetwork;
/*     */ import com.atlas.common.fabric.rustlingspots.particle.RustlingParticleTypes;
/*     */ import com.atlas.common.fabric.safari.expedition.entity.SafariExpeditionBalloonEntities;
/*     */ import com.atlas.common.fabric.safari.expedition.network.SafariExpeditionNetwork;
/*     */ import com.atlas.common.fabric.safari.fishing.SafariFishingNetwork;
/*     */ import com.atlas.common.fabric.safari.fishing.entity.SafariFishEntities;
/*     */ import com.atlas.common.fabric.safari.fishing.entity.SafariFishingBobberEntities;
/*     */ import com.atlas.common.fabric.safari.meteor.entity.SafariMeteorEntities;
/*     */ import com.atlas.common.fabric.safari.racing.RacingNetworkHelper;
/*     */ import com.atlas.common.fabric.safari.racing.client.RacingHudRenderer;
/*     */ import com.atlas.common.fabric.safari.racing.client.RacingUiSuppression;
/*     */ import com.atlas.common.fabric.safari.racing.client.RacingWorldRenderer;
/*     */ import com.atlas.common.fabric.skindex.network.SkinDexNetwork;
/*     */ import com.atlas.common.fabric.starterguide.PlayerStarterGuideData;
/*     */ import com.atlas.common.fabric.starterguide.network.StarterGuideNetwork;
/*     */ import com.atlas.common.fabric.vote.network.VoteNetwork;
/*     */ import com.cobblemon.mod.common.api.events.CobblemonEvents;
/*     */ import com.cobblemon.mod.common.api.events.pokemon.interaction.PokemonInteractionGUICreationEvent;
/*     */ import com.cobblemon.mod.common.client.gui.interact.wheel.InteractWheelOption;
/*     */ import com.cobblemon.mod.common.client.gui.interact.wheel.Orientation;
/*     */ import com.cobblemon.mod.common.pokemon.Pokemon;
/*     */ import com.cobblemon.mod.common.util.MiscUtilsKt;
/*     */ import com.mojang.brigadier.CommandDispatcher;
/*     */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*     */ import com.mojang.brigadier.context.CommandContext;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import java.util.List;
/*     */ import kotlin.Unit;
/*     */ import net.fabricmc.api.ClientModInitializer;
/*     */ import net.fabricmc.api.EnvType;
/*     */ import net.fabricmc.api.Environment;
/*     */ import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
/*     */ import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
/*     */ import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
/*     */ import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
/*     */ import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
/*     */ import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
/*     */ import net.minecraft.class_1747;
/*     */ import net.minecraft.class_1799;
/*     */ import net.minecraft.class_1935;
/*     */ import net.minecraft.class_2338;
/*     */ import net.minecraft.class_2561;
/*     */ import net.minecraft.class_2586;
/*     */ import net.minecraft.class_2960;
/*     */ import net.minecraft.class_310;
/*     */ import net.minecraft.class_4587;
/*     */ import net.minecraft.class_4597;
/*     */ import net.minecraft.class_5614;
/*     */ import net.minecraft.class_5616;
/*     */ import net.minecraft.class_7923;
/*     */ import net.minecraft.class_811;
/*     */ import net.minecraft.class_827;
/*     */ import net.minecraft.class_8710;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ import org.joml.Vector3f;
/*     */ 
/*     */ @Environment(EnvType.CLIENT)
/*     */ public final class AtlasClientMod implements ClientModInitializer {
/*     */   @Nullable
/* 105 */   public static class_2338 lastStationPos = null; private static HudTextProvider hudTextProvider;
/*     */   @Nullable
/* 107 */   public static List<Pokemon> lastStationPokemon = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static HudTextProvider getHudTextProvider() {
/* 115 */     return Objects.<HudTextProvider>requireNonNull(hudTextProvider);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onInitializeClient() {
/* 124 */     AtlasCommon.initialize(AtlasProjectType.FABRIC);
/*     */     
/* 126 */     ServerTickEvents.START_SERVER_TICK.register(server -> AtlasEventBus.post((AtlasEvent)AtlasTickEvent.INSTANCE));
/*     */     
/* 128 */     AtlasModClientPacketListener.register();
/* 129 */     hudTextProvider = new HudTextProvider();
/*     */     
/* 131 */     registerBlock();
/* 132 */     registerItemRenderers1x1();
/* 133 */     registerItemRenderers2x2();
/*     */     
/* 135 */     registerStatueItemRenderers();
/*     */ 
/*     */     
/* 138 */     BattleTowerNetwork.initClient();
/* 139 */     BattleUiNetwork.initClient();
/* 140 */     BattleTowerEntities.initClient();
/* 141 */     SafariFishingBobberEntities.initClient();
/* 142 */     SafariFishEntities.initClient();
/* 143 */     SafariExpeditionBalloonEntities.initClient();
/* 144 */     SafariMeteorEntities.initClient();
/* 145 */     SafariFishingNetwork.initClient();
/* 146 */     SafariBoardNetwork.initClient();
/* 147 */     SafariExpeditionNetwork.initClient();
/* 148 */     ClaimMapNetwork.initClient();
/* 149 */     RankedNetwork.initClient();
/* 150 */     ClanNetwork.initClient();
/* 151 */     GuideNetwork.initClient();
/* 152 */     GymCircuitNetwork.initClient();
/* 153 */     LookupNetwork.initClient();
/* 154 */     SkinDexNetwork.initClient();
/* 155 */     CardCollectionNetwork.initClient();
/* 156 */     CardMarketNetwork.initClient();
/* 157 */     CardCollectionToast.register();
/* 158 */     CosmeticPreviewNetwork.initClient();
/* 159 */     EmojiNetwork.initClient();
/* 160 */     AuctionNetwork.initClient();
/* 161 */     VoteNetwork.initClient();
/* 162 */     PatchNotesNetwork.initClient();
/* 163 */     ForeverPackNetwork.initClient();
/* 164 */     MorphNetwork.initClient();
/* 165 */     MorphRuntimeNetwork.initClient();
/* 166 */     MorphClientHandler.init();
/* 167 */     ShopNetwork.initClient();
/* 168 */     StarterGuideNetwork.initClient();
/* 169 */     RacingNetworkHelper.registerClientReceivers();
/* 170 */     RustlingSpotsNetwork.initClient();
/* 171 */     RustlingParticleTypes.registerClient();
/* 172 */     ClientTickEvents.END_CLIENT_TICK.register(client -> RustlingSpotClientHandler.tick());
/*     */ 
/*     */     
/* 175 */     GoCatchNetwork.initClient();
/* 176 */     GoCatchParticleTypes.registerClient();
/* 177 */     GoCatchThrowEffectsHandler.registerSounds();
/* 178 */     GoCatchCaptureRingRenderer.register();
/* 179 */     GoCatchHudFeedbackRenderer.register();
/* 180 */     SafariFishingClientNetworking.register();
/*     */     
/* 182 */     ClientTickEvents.START_CLIENT_TICK.register(client -> {
/*     */           GoCatchCurveBallDetector.getInstance().startTick(client);
/*     */           
/*     */           GoCatchTargetingTracker.getInstance().tick();
/*     */           GoCatchClientThrowTracker.getInstance().startTick(client);
/*     */         });
/* 188 */     ClientTickEvents.END_CLIENT_TICK.register(client -> {
/*     */           GoCatchCurveBallDetector.getInstance().tick(client);
/*     */           
/*     */           GoCatchThrowFeedbackTracker.getInstance().tick();
/*     */           GoCatchClientThrowTracker.getInstance().tick();
/*     */           GoCatchCurveBallTrailTracker.getInstance().tick(client);
/*     */           RacingUiSuppression.tick(client);
/*     */         });
/* 196 */     RacingHudRenderer.register();
/* 197 */     RacingWorldRenderer.register();
/* 198 */     SafariFishingHudRenderer.register();
/* 199 */     StarterGuideHudRenderer.register();
/*     */ 
/*     */     
/* 202 */     ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> dispatcher.register((LiteralArgumentBuilder)ClientCommandManager.literal("toggleguide").executes(())));
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
/* 213 */     CobblemonEvents.POKEMON_INTERACTION_GUI_CREATION.subscribe(Priority.NORMAL, event -> {
/*     */           InteractWheelOption cycleOption = new InteractWheelOption(MiscUtilsKt.cobblemonResource("textures/item/evolution/love_sweet.png"), null, true, "Select Skin", (), ());
/*     */           event.addOption(Orientation.SOUTHEAST, cycleOption);
/*     */           return Unit.INSTANCE;
/*     */         });
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
/*     */   private void registerBlock() {
/* 233 */     class_5616.method_32144(AtlasBlockRegistry.POKE_SPINNER_BLOCK_ENTITY_TYPE, context -> new AnimatedBlockRenderer(class_2960.method_60655("atlas-common-fabric", "poke_spinner")));
/*     */ 
/*     */     
/* 236 */     class_5616.method_32144(AtlasBlockRegistry.MONTHLY_CRATE_BLOCK_ENTITY_TYPE, context -> new AnimatedBlockRenderer(class_2960.method_60655("atlas-common-fabric", "monthly_crate")));
/*     */ 
/*     */     
/* 239 */     class_5616.method_32144(AtlasBlockRegistry.PREMIER_CRATE_BLOCK_ENTITY_TYPE, context -> new AnimatedBlockRenderer(class_2960.method_60655("atlas-common-fabric", "premier_crate")));
/*     */ 
/*     */     
/* 242 */     class_5616.method_32144(AtlasBlockRegistry.ULTRA_CRATE_BLOCK_ENTITY_TYPE, context -> new AnimatedBlockRenderer(class_2960.method_60655("atlas-common-fabric", "ultra_crate")));
/*     */ 
/*     */     
/* 245 */     class_5616.method_32144(AtlasBlockRegistry.VICTORY_CRATE_BLOCK_ENTITY_TYPE, context -> new AnimatedBlockRenderer(class_2960.method_60655("atlas-common-fabric", "victory_crate")));
/*     */ 
/*     */     
/* 248 */     class_5616.method_32144(AtlasBlockRegistry.GENERIC_STATION_BLOCK_ENTITY_TYPE, context -> new StationBlockEntityRenderer());
/*     */ 
/*     */ 
/*     */     
/* 252 */     class_5616.method_32144(AtlasBlockRegistry.MULTIBLOCK_STATION_BLOCK_ENTITY_TYPE, context -> new StationBlockEntityRenderer());
/*     */ 
/*     */ 
/*     */     
/* 256 */     if (AtlasBlockRegistry.STATUE_BLOCK_ENTITY_TYPE != null) {
/* 257 */       class_5616.method_32144(AtlasBlockRegistry.STATUE_BLOCK_ENTITY_TYPE, context -> new StatueBlockEntityRenderer());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void registerItemRenderers1x1() {
/* 264 */     List<class_1747> stations = List.of(new class_1747[] { AtlasItemRegistry.HUT_ITEM, AtlasItemRegistry.CROP_YARD_ITEM, AtlasItemRegistry.FISHING_POND_ITEM, AtlasItemRegistry.FORGE_ITEM, AtlasItemRegistry.ORE_MINE_ITEM, AtlasItemRegistry.MOB_ARENA_ITEM, AtlasItemRegistry.FLYING_COURSE_ITEM, AtlasItemRegistry.TESLA_COIL_ITEM, AtlasItemRegistry.IGLOO_ITEM, AtlasItemRegistry.MUSHROOM_GARDEN_ITEM, AtlasItemRegistry.DIGGING_SITE_ITEM, AtlasItemRegistry.ZEN_GARDEN_ITEM, AtlasItemRegistry.BUG_NEST_ITEM, AtlasItemRegistry.ABANDONED_FIELDS_ITEM, AtlasItemRegistry.ROARING_MOUNTAINS_ITEM, AtlasItemRegistry.DARK_RUINS_ITEM, AtlasItemRegistry.STEEL_MACHINE_ITEM, AtlasItemRegistry.BUTTERFLY_FIELDS_ITEM });
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
/* 286 */     StationBlockEntityRenderer renderer = new StationBlockEntityRenderer();
/*     */     
/* 288 */     for (class_1747 item : stations) {
/* 289 */       BuiltinItemRendererRegistry.INSTANCE.register((class_1935)item, (stack, mode, matrices, vertexConsumers, light, overlay) -> {
/*     */             GenericStationBlockEntity dummy = new GenericStationBlockEntity(AtlasBlockRegistry.GENERIC_STATION_BLOCK_ENTITY_TYPE, class_2338.field_10980, item.method_7711().method_9564());
/*     */             dummy.setStationId(class_7923.field_41175.method_10221(item.method_7711()).method_12832());
/*     */             dummy.getAnimatableInstanceCache();
/*     */             matrices.method_22903();
/*     */             matrices.method_22904(0.0D, 0.25D, 0.0D);
/*     */             renderer.render((StationBlockEntity)dummy, 0.0F, matrices, vertexConsumers, light, overlay);
/*     */             matrices.method_22909();
/*     */           });
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
/*     */   
/*     */   private void registerItemRenderers2x2() {
/* 310 */     List<class_1747> stations = List.of(new class_1747[] { AtlasItemRegistry.VILLAGE_ITEM, AtlasItemRegistry.CROP_FARM_ITEM, AtlasItemRegistry.FISHING_LAKE_ITEM, AtlasItemRegistry.MOLTEN_FORGE_ITEM, AtlasItemRegistry.QUARRY_ITEM, AtlasItemRegistry.MOB_STADIUM_ITEM, AtlasItemRegistry.GUSTY_HILLS_ITEM, AtlasItemRegistry.ELECTRIC_FIELD_ITEM, AtlasItemRegistry.GLACIERS_ITEM, AtlasItemRegistry.MUSHROOM_FOREST_ITEM, AtlasItemRegistry.CRATER_ITEM, AtlasItemRegistry.TRANQUIL_FIELDS_ITEM, AtlasItemRegistry.INSECT_HIVE_ITEM, AtlasItemRegistry.GRAVEYARD_ITEM, AtlasItemRegistry.DRAGON_TEMPLE_ITEM, AtlasItemRegistry.SHADOW_REALM_ITEM, AtlasItemRegistry.STEAMPUNK_FACTORY_ITEM, AtlasItemRegistry.MYSTIC_GARDEN_ITEM, AtlasItemRegistry.BATTLE_GROUNDS_ITEM, AtlasItemRegistry.FOREST_TEMPLE_ITEM, AtlasItemRegistry.HAUNTED_MANOR_ITEM, AtlasItemRegistry.ICE_PALACE_ITEM, AtlasItemRegistry.POISON_VALLEY_ITEM, AtlasItemRegistry.THUNDERSTORM_VALLEY_ITEM, AtlasItemRegistry.VOLCANO_ITEM, AtlasItemRegistry.WYVERN_GROUNDS_ITEM, AtlasItemRegistry.ENCHANTED_FOREST_ITEM });
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
/* 344 */     StationBlockEntityRenderer renderer = new StationBlockEntityRenderer();
/*     */     
/* 346 */     for (class_1747 item : stations) {
/* 347 */       BuiltinItemRendererRegistry.INSTANCE.register((class_1935)item, (stack, mode, matrices, vertexConsumers, light, overlay) -> {
/*     */             GenericStationBlockEntity dummy = new GenericStationBlockEntity(AtlasBlockRegistry.MULTIBLOCK_STATION_BLOCK_ENTITY_TYPE, class_2338.field_10980, item.method_7711().method_9564());
/*     */             dummy.setStationId(class_7923.field_41175.method_10221(item.method_7711()).method_12832());
/*     */             dummy.getAnimatableInstanceCache();
/*     */             matrices.method_22903();
/*     */             matrices.method_22905(0.55F, 0.55F, 0.55F);
/*     */             matrices.method_22904(1.25D, 0.5D, 0.5D);
/*     */             renderer.render((StationBlockEntity)dummy, 0.0F, matrices, vertexConsumers, light, overlay);
/*     */             matrices.method_22909();
/*     */           });
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
/*     */   
/*     */   private void registerStatueItemRenderers() {
/* 369 */     if (AtlasBlockRegistry.STATUE_BLOCK_ENTITY_TYPE == null) {
/*     */       return;
/*     */     }
/*     */     
/* 373 */     StatueBlockEntityRenderer renderer = new StatueBlockEntityRenderer();
/*     */     
/* 375 */     for (class_1747 item : AtlasItemRegistry.STATUE_ITEMS.values()) {
/* 376 */       BuiltinItemRendererRegistry.INSTANCE.register((class_1935)item, (stack, mode, matrices, vertexConsumers, light, overlay) -> {
/*     */             StatueBlockEntity dummy = new StatueBlockEntity(AtlasBlockRegistry.STATUE_BLOCK_ENTITY_TYPE, class_2338.field_10980, item.method_7711().method_9564());
/*     */             dummy.getAnimatableInstanceCache();
/*     */             matrices.method_22903();
/*     */             switch (mode) {
/*     */               case field_4321:
/*     */               case field_4322:
/*     */                 matrices.method_22905(0.22F, 0.22F, 0.22F);
/*     */                 matrices.method_46416(0.0F, -0.15F, 0.0F);
/*     */                 break;
/*     */               case field_4323:
/*     */               case field_4320:
/*     */                 matrices.method_22905(0.18F, 0.18F, 0.18F);
/*     */                 matrices.method_46416(0.0F, -0.05F, 0.0F);
/*     */                 break;
/*     */               case field_4318:
/*     */                 matrices.method_22905(0.18F, 0.18F, 0.18F);
/*     */                 matrices.method_46416(0.0F, 0.05F, 0.0F);
/*     */                 break;
/*     */               case field_4319:
/*     */                 matrices.method_22905(0.28F, 0.28F, 0.28F);
/*     */                 matrices.method_46416(0.0F, 0.05F, 0.0F);
/*     */                 break;
/*     */               default:
/*     */                 matrices.method_22905(0.34F, 0.34F, 0.34F);
/*     */                 matrices.method_46416(0.0F, 0.1F, 0.0F);
/*     */                 break;
/*     */             } 
/*     */             renderer.method_3569((class_2586)dummy, 0.0F, matrices, vertexConsumers, light, overlay);
/*     */             matrices.method_22909();
/*     */           });
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\AtlasClientMod.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */