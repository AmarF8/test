/*     */ package com.atlas.common.fabric.block;
/*     */ 
/*     */ import com.atlas.common.fabric.block.animated.AnimatedBlockWithDirection;
/*     */ import com.atlas.common.fabric.block.crate.block.MonthlyCrateBlock;
/*     */ import com.atlas.common.fabric.block.crate.block.PokeSpinnerBlock;
/*     */ import com.atlas.common.fabric.block.crate.block.PremierCrateBlock;
/*     */ import com.atlas.common.fabric.block.crate.block.UltraCrateBlock;
/*     */ import com.atlas.common.fabric.block.crate.block.VictoryCrateBlock;
/*     */ import com.atlas.common.fabric.block.crate.entity.MonthlyCrateBlockEntity;
/*     */ import com.atlas.common.fabric.block.crate.entity.PokeSpinnerBlockEntity;
/*     */ import com.atlas.common.fabric.block.crate.entity.PremierCrateBlockEntity;
/*     */ import com.atlas.common.fabric.block.crate.entity.UltraCrateBlockEntity;
/*     */ import com.atlas.common.fabric.block.crate.entity.VictoryCrateBlockEntity;
/*     */ import com.atlas.common.fabric.block.station.block.GenericMultiBlockStation;
/*     */ import com.atlas.common.fabric.block.station.block.GenericStationBlock;
/*     */ import com.atlas.common.fabric.block.station.entity.GenericStationBlockEntity;
/*     */ import com.atlas.common.fabric.block.statue.block.StatueBlock;
/*     */ import com.atlas.common.fabric.block.statue.block.StatueProxyBlock;
/*     */ import com.atlas.common.fabric.block.statue.entity.StatueBlockEntity;
/*     */ import com.atlas.common.fabric.block.statue.entity.StatueProxyBlockEntity;
/*     */ import com.atlas.common.fabric.block.statue.system.StatueDefinition;
/*     */ import com.atlas.common.fabric.block.statue.system.StatueRegistry;
/*     */ import com.atlas.common.fabric.safari.meteor.SafariMeteorType;
/*     */ import java.util.ArrayList;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import net.minecraft.class_2246;
/*     */ import net.minecraft.class_2248;
/*     */ import net.minecraft.class_2378;
/*     */ import net.minecraft.class_2591;
/*     */ import net.minecraft.class_2960;
/*     */ import net.minecraft.class_4970;
/*     */ import net.minecraft.class_7923;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class AtlasBlockRegistry
/*     */ {
/*  43 */   public static final AnimatedBlockWithDirection POKE_SPINNER_BLOCK = (AnimatedBlockWithDirection)registerBlock("poke_spinner", new PokeSpinnerBlock());
/*  44 */   public static final class_2591<PokeSpinnerBlockEntity> POKE_SPINNER_BLOCK_ENTITY_TYPE = registerBlockEntity("poke_spinner", 
/*  45 */       class_2591.class_2592.method_20528(PokeSpinnerBlockEntity::new, new class_2248[] { (class_2248)POKE_SPINNER_BLOCK }));
/*     */ 
/*     */   
/*  48 */   public static final AnimatedBlockWithDirection MONTHLY_CRATE_BLOCK = (AnimatedBlockWithDirection)registerBlock("monthly_crate", new MonthlyCrateBlock());
/*  49 */   public static final class_2591<MonthlyCrateBlockEntity> MONTHLY_CRATE_BLOCK_ENTITY_TYPE = registerBlockEntity("monthly_crate", 
/*  50 */       class_2591.class_2592.method_20528(MonthlyCrateBlockEntity::new, new class_2248[] { (class_2248)MONTHLY_CRATE_BLOCK }));
/*     */ 
/*     */   
/*  53 */   public static final AnimatedBlockWithDirection PREMIER_CRATE_BLOCK = (AnimatedBlockWithDirection)registerBlock("premier_crate", new PremierCrateBlock());
/*  54 */   public static final class_2591<PremierCrateBlockEntity> PREMIER_CRATE_BLOCK_ENTITY_TYPE = registerBlockEntity("premier_crate", 
/*  55 */       class_2591.class_2592.method_20528(PremierCrateBlockEntity::new, new class_2248[] { (class_2248)PREMIER_CRATE_BLOCK }));
/*     */ 
/*     */   
/*  58 */   public static final AnimatedBlockWithDirection ULTRA_CRATE_BLOCK = (AnimatedBlockWithDirection)registerBlock("ultra_crate", new UltraCrateBlock());
/*  59 */   public static final class_2591<UltraCrateBlockEntity> ULTRA_CRATE_BLOCK_ENTITY_TYPE = registerBlockEntity("ultra_crate", 
/*  60 */       class_2591.class_2592.method_20528(UltraCrateBlockEntity::new, new class_2248[] { (class_2248)ULTRA_CRATE_BLOCK }));
/*     */ 
/*     */   
/*  63 */   public static final AnimatedBlockWithDirection VICTORY_CRATE_BLOCK = (AnimatedBlockWithDirection)registerBlock("victory_crate", new VictoryCrateBlock());
/*  64 */   public static final class_2591<VictoryCrateBlockEntity> VICTORY_CRATE_BLOCK_ENTITY_TYPE = registerBlockEntity("victory_crate", 
/*  65 */       class_2591.class_2592.method_20528(VictoryCrateBlockEntity::new, new class_2248[] { (class_2248)VICTORY_CRATE_BLOCK }));
/*     */ 
/*     */ 
/*     */   
/*  69 */   public static final Map<String, StatueBlock> STATUE_BLOCKS = registerStatueBlocks();
/*     */   
/*     */   @Nullable
/*  72 */   public static final StatueProxyBlock STATUE_PROXY_BLOCK = registerBlock("statue_proxy", new StatueProxyBlock());
/*     */ 
/*     */   
/*     */   static {
/*  76 */     STATUE_BLOCK_ENTITY_TYPE = registerBlockEntity("statue", class_2591.class_2592.method_20528(StatueBlockEntity::new, (class_2248[])STATUE_BLOCKS.values().toArray(x$0 -> new class_2248[x$0])));
/*     */   }
/*     */   @Nullable
/*     */   public static final class_2591<StatueBlockEntity> STATUE_BLOCK_ENTITY_TYPE; @Nullable
/*  80 */   public static final class_2591<StatueProxyBlockEntity> STATUE_PROXY_BLOCK_ENTITY_TYPE = registerBlockEntity("statue_proxy", class_2591.class_2592.method_20528(StatueProxyBlockEntity::new, new class_2248[] { (class_2248)STATUE_PROXY_BLOCK }));
/*     */ 
/*     */ 
/*     */   
/*  84 */   public static final Map<String, List<class_2248>> SAFARI_METEOR_ORE_VARIANT_BLOCKS = registerSafariMeteorOreVariantBlocks();
/*  85 */   public static final Map<String, List<class_2248>> SAFARI_METEOR_STORAGE_VARIANT_BLOCKS = registerSafariMeteorStorageVariantBlocks();
/*  86 */   public static final Map<String, class_2248> SAFARI_METEOR_ORE_BLOCKS = primarySafariMeteorBlocks(SAFARI_METEOR_ORE_VARIANT_BLOCKS);
/*  87 */   public static final Map<String, class_2248> SAFARI_METEOR_STORAGE_BLOCKS = primarySafariMeteorBlocks(SAFARI_METEOR_STORAGE_VARIANT_BLOCKS);
/*     */ 
/*     */   
/*  90 */   public static final GenericStationBlock HUT_BLOCK = registerBlock("hut", new GenericStationBlock());
/*  91 */   public static final GenericStationBlock CROP_YARD_BLOCK = registerBlock("crop_yard", new GenericStationBlock());
/*  92 */   public static final GenericStationBlock FISHING_POND_BLOCK = registerBlock("fishing_pond", new GenericStationBlock());
/*  93 */   public static final GenericStationBlock FORGE_BLOCK = registerBlock("forge", new GenericStationBlock());
/*  94 */   public static final GenericStationBlock ORE_MINE_BLOCK = registerBlock("ore_mine", new GenericStationBlock());
/*  95 */   public static final GenericStationBlock MOB_ARENA_BLOCK = registerBlock("mob_arena", new GenericStationBlock());
/*  96 */   public static final GenericStationBlock FLYING_COURSE_BLOCK = registerBlock("flying_course", new GenericStationBlock());
/*  97 */   public static final GenericStationBlock TESLA_COIL_BLOCK = registerBlock("tesla_coil", new GenericStationBlock());
/*  98 */   public static final GenericStationBlock IGLOO_BLOCK = registerBlock("igloo", new GenericStationBlock());
/*  99 */   public static final GenericStationBlock MUSHROOM_GARDEN_BLOCK = registerBlock("mushroom_garden", new GenericStationBlock());
/* 100 */   public static final GenericStationBlock DIGGING_SITE_BLOCK = registerBlock("digging_site", new GenericStationBlock());
/* 101 */   public static final GenericStationBlock ZEN_GARDEN_BLOCK = registerBlock("zen_garden", new GenericStationBlock());
/* 102 */   public static final GenericStationBlock BUG_NEST_BLOCK = registerBlock("bug_nest", new GenericStationBlock());
/* 103 */   public static final GenericStationBlock ABANDONED_FIELDS_BLOCK = registerBlock("abandoned_fields", new GenericStationBlock());
/* 104 */   public static final GenericStationBlock ROARING_MOUNTAINS_BLOCK = registerBlock("roaring_mountains", new GenericStationBlock());
/* 105 */   public static final GenericStationBlock DARK_RUINS_BLOCK = registerBlock("dark_ruins", new GenericStationBlock());
/* 106 */   public static final GenericStationBlock STEEL_MACHINE_BLOCK = registerBlock("steel_machine", new GenericStationBlock());
/* 107 */   public static final GenericStationBlock BUTTERFLY_FIELDS_BLOCK = registerBlock("butterfly_fields", new GenericStationBlock());
/*     */ 
/*     */   
/* 110 */   public static final GenericMultiBlockStation VILLAGE_BLOCK = registerBlock("village", new GenericMultiBlockStation());
/* 111 */   public static final GenericMultiBlockStation CROP_FARM_BLOCK = registerBlock("crop_farm", new GenericMultiBlockStation());
/* 112 */   public static final GenericMultiBlockStation FISHING_LAKE_BLOCK = registerBlock("fishing_lake", new GenericMultiBlockStation());
/* 113 */   public static final GenericMultiBlockStation MOLTEN_FORGE_BLOCK = registerBlock("molten_forge", new GenericMultiBlockStation());
/* 114 */   public static final GenericMultiBlockStation QUARRY_BLOCK = registerBlock("quarry", new GenericMultiBlockStation());
/* 115 */   public static final GenericMultiBlockStation MOB_STADIUM_BLOCK = registerBlock("mob_stadium", new GenericMultiBlockStation());
/* 116 */   public static final GenericMultiBlockStation GUSTY_HILLS_BLOCK = registerBlock("gusty_hills", new GenericMultiBlockStation());
/* 117 */   public static final GenericMultiBlockStation ELECTRIC_FIELD_BLOCK = registerBlock("electric_field", new GenericMultiBlockStation());
/* 118 */   public static final GenericMultiBlockStation GLACIERS_BLOCK = registerBlock("glaciers", new GenericMultiBlockStation());
/* 119 */   public static final GenericMultiBlockStation MUSHROOM_FOREST_BLOCK = registerBlock("mushroom_forest", new GenericMultiBlockStation());
/* 120 */   public static final GenericMultiBlockStation CRATER_BLOCK = registerBlock("crater", new GenericMultiBlockStation());
/* 121 */   public static final GenericMultiBlockStation TRANQUIL_FIELDS_BLOCK = registerBlock("tranquil_fields", new GenericMultiBlockStation());
/*     */ 
/*     */   
/* 124 */   public static final GenericMultiBlockStation INSECT_HIVE_BLOCK = registerBlock("insect_hive", new GenericMultiBlockStation());
/* 125 */   public static final GenericMultiBlockStation GRAVEYARD_BLOCK = registerBlock("graveyard", new GenericMultiBlockStation());
/* 126 */   public static final GenericMultiBlockStation DRAGON_TEMPLE_BLOCK = registerBlock("dragon_temple", new GenericMultiBlockStation());
/* 127 */   public static final GenericMultiBlockStation SHADOW_REALM_BLOCK = registerBlock("shadow_realm", new GenericMultiBlockStation());
/* 128 */   public static final GenericMultiBlockStation STEAMPUNK_FACTORY_BLOCK = registerBlock("steampunk_factory", new GenericMultiBlockStation());
/* 129 */   public static final GenericMultiBlockStation MYSTIC_GARDEN_BLOCK = registerBlock("mystic_garden", new GenericMultiBlockStation());
/*     */ 
/*     */   
/* 132 */   public static final GenericMultiBlockStation BATTLE_GROUNDS_BLOCK = registerBlock("battle_grounds", new GenericMultiBlockStation());
/* 133 */   public static final GenericMultiBlockStation FOREST_TEMPLE_BLOCK = registerBlock("forest_temple", new GenericMultiBlockStation());
/* 134 */   public static final GenericMultiBlockStation HAUNTED_MANOR_BLOCK = registerBlock("haunted_manor", new GenericMultiBlockStation());
/* 135 */   public static final GenericMultiBlockStation ICE_PALACE_BLOCK = registerBlock("ice_palace", new GenericMultiBlockStation());
/* 136 */   public static final GenericMultiBlockStation POISON_VALLEY_BLOCK = registerBlock("poison_valley", new GenericMultiBlockStation());
/* 137 */   public static final GenericMultiBlockStation THUNDERSTORM_VALLEY_BLOCK = registerBlock("thunderstorm_valley", new GenericMultiBlockStation());
/* 138 */   public static final GenericMultiBlockStation VOLCANO_BLOCK = registerBlock("volcano", new GenericMultiBlockStation());
/* 139 */   public static final GenericMultiBlockStation WYVERN_GROUNDS_BLOCK = registerBlock("wyvern_grounds", new GenericMultiBlockStation());
/*     */ 
/*     */   
/* 142 */   public static final GenericMultiBlockStation ENCHANTED_FOREST_BLOCK = registerBlock("enchanted_forest", new GenericMultiBlockStation());
/*     */   
/* 144 */   public static final class_2591<GenericStationBlockEntity> GENERIC_STATION_BLOCK_ENTITY_TYPE = registerBlockEntity("generic_station", 
/* 145 */       class_2591.class_2592.method_20528(GenericStationBlockEntity::new, new class_2248[] { 
/*     */           (class_2248)HUT_BLOCK, (class_2248)CROP_YARD_BLOCK, (class_2248)FISHING_POND_BLOCK, (class_2248)FORGE_BLOCK, (class_2248)ORE_MINE_BLOCK, (class_2248)MOB_ARENA_BLOCK, (class_2248)FLYING_COURSE_BLOCK, (class_2248)TESLA_COIL_BLOCK, (class_2248)IGLOO_BLOCK, (class_2248)MUSHROOM_GARDEN_BLOCK, 
/*     */           (class_2248)DIGGING_SITE_BLOCK, (class_2248)ZEN_GARDEN_BLOCK, (class_2248)BUG_NEST_BLOCK, (class_2248)ABANDONED_FIELDS_BLOCK, (class_2248)ROARING_MOUNTAINS_BLOCK, (class_2248)DARK_RUINS_BLOCK, (class_2248)STEEL_MACHINE_BLOCK, (class_2248)BUTTERFLY_FIELDS_BLOCK }));
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 152 */   public static final class_2591<GenericStationBlockEntity> MULTIBLOCK_STATION_BLOCK_ENTITY_TYPE = registerBlockEntity("multiblock_station", 
/* 153 */       class_2591.class_2592.method_20528(GenericStationBlockEntity::new, new class_2248[] { 
/*     */           (class_2248)VILLAGE_BLOCK, (class_2248)CROP_FARM_BLOCK, (class_2248)FISHING_LAKE_BLOCK, (class_2248)MOLTEN_FORGE_BLOCK, (class_2248)QUARRY_BLOCK, (class_2248)MOB_STADIUM_BLOCK, (class_2248)GUSTY_HILLS_BLOCK, (class_2248)ELECTRIC_FIELD_BLOCK, (class_2248)GLACIERS_BLOCK, (class_2248)MUSHROOM_FOREST_BLOCK, 
/*     */           (class_2248)CRATER_BLOCK, (class_2248)TRANQUIL_FIELDS_BLOCK, (class_2248)INSECT_HIVE_BLOCK, (class_2248)GRAVEYARD_BLOCK, (class_2248)DRAGON_TEMPLE_BLOCK, (class_2248)SHADOW_REALM_BLOCK, (class_2248)STEAMPUNK_FACTORY_BLOCK, (class_2248)MYSTIC_GARDEN_BLOCK, (class_2248)BATTLE_GROUNDS_BLOCK, (class_2248)FOREST_TEMPLE_BLOCK, 
/*     */           (class_2248)HAUNTED_MANOR_BLOCK, (class_2248)ICE_PALACE_BLOCK, (class_2248)POISON_VALLEY_BLOCK, (class_2248)THUNDERSTORM_VALLEY_BLOCK, (class_2248)VOLCANO_BLOCK, (class_2248)WYVERN_GROUNDS_BLOCK, (class_2248)ENCHANTED_FOREST_BLOCK }));
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
/*     */   public static <T extends class_2248> T registerBlock(@NotNull String name, @NotNull T block) {
/* 178 */     return (T)class_2378.method_10230((class_2378)class_7923.field_41175, class_2960.method_60655("atlas-common-fabric", Objects.<String>requireNonNull(name)), Objects.<class_2248>requireNonNull((class_2248)block));
/*     */   }
/*     */   @Nullable
/*     */   public static StatueBlock getStatueBlock(@NotNull String id) {
/* 182 */     return STATUE_BLOCKS.get(id);
/*     */   }
/*     */   @NotNull
/*     */   private static Map<String, StatueBlock> registerStatueBlocks() {
/* 186 */     Map<String, StatueBlock> statueBlocks = new LinkedHashMap<>();
/* 187 */     for (StatueDefinition definition : StatueRegistry.definitions()) {
/* 188 */       statueBlocks.put(definition.id(), registerBlock(definition.id(), new StatueBlock()));
/*     */     }
/* 190 */     return Map.copyOf(statueBlocks);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   private static Map<String, List<class_2248>> registerSafariMeteorOreVariantBlocks() {
/* 195 */     Map<String, List<class_2248>> blocks = new LinkedHashMap<>();
/* 196 */     for (SafariMeteorType type : SafariMeteorType.all()) {
/* 197 */       List<class_2248> variants = new ArrayList<>();
/* 198 */       for (int variant = 1; variant <= 4; variant++) {
/* 199 */         variants.add(registerBlock(type.oreBlockVariantId(variant), new class_2248(class_4970.class_2251.method_9630((class_4970)class_2246.field_29029).method_9629(55.0F, 1200.0F))));
/*     */       }
/* 201 */       blocks.put(type.id(), List.copyOf(variants));
/*     */     } 
/* 203 */     return Map.copyOf(blocks);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   private static Map<String, List<class_2248>> registerSafariMeteorStorageVariantBlocks() {
/* 208 */     Map<String, List<class_2248>> blocks = new LinkedHashMap<>();
/* 209 */     for (SafariMeteorType type : SafariMeteorType.all()) {
/* 210 */       List<class_2248> variants = new ArrayList<>();
/* 211 */       for (int variant = 1; variant <= 4; variant++) {
/* 212 */         variants.add(registerBlock(type.storageBlockVariantId(variant), new class_2248(class_4970.class_2251.method_9630((class_4970)class_2246.field_22108).method_9629(65.0F, 1200.0F))));
/*     */       }
/* 214 */       blocks.put(type.id(), List.copyOf(variants));
/*     */     } 
/* 216 */     return Map.copyOf(blocks);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   private static Map<String, class_2248> primarySafariMeteorBlocks(@NotNull Map<String, List<class_2248>> variantBlocks) {
/* 221 */     Map<String, class_2248> blocks = new LinkedHashMap<>();
/* 222 */     for (Map.Entry<String, List<class_2248>> entry : variantBlocks.entrySet()) {
/* 223 */       if (!((List)entry.getValue()).isEmpty()) {
/* 224 */         blocks.put(entry.getKey(), ((List<class_2248>)entry.getValue()).getFirst());
/*     */       }
/*     */     } 
/* 227 */     return Map.copyOf(blocks);
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
/*     */   @NotNull
/*     */   public static <T extends net.minecraft.class_2586> class_2591<T> registerBlockEntity(@NotNull String name, @NotNull class_2591.class_2592<T> builder) {
/* 241 */     return (class_2591<T>)class_2378.method_10230(class_7923.field_41181, class_2960.method_60655("atlas-common-fabric", Objects.<String>requireNonNull(name)), builder.method_11034(null));
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\block\AtlasBlockRegistry.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */