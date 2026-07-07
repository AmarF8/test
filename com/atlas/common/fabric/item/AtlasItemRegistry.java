/*     */ package com.atlas.common.fabric.item;
/*     */ 
/*     */ import com.atlas.common.fabric.block.AtlasBlockRegistry;
/*     */ import com.atlas.common.fabric.block.statue.block.StatueBlock;
/*     */ import com.atlas.common.fabric.block.statue.system.StatueDefinition;
/*     */ import com.atlas.common.fabric.block.statue.system.StatueRegistry;
/*     */ import com.atlas.common.fabric.item.armor.PokeArmorItem;
/*     */ import com.atlas.common.fabric.item.armor.PokeArmorSet;
/*     */ import com.atlas.common.fabric.item.statue.EnergyRuneItem;
/*     */ import com.atlas.common.fabric.safari.fishing.SafariUpgradeSlot;
/*     */ import com.atlas.common.fabric.safari.fishing.item.SafariFishingRodItem;
/*     */ import com.atlas.common.fabric.safari.fishing.item.SafariUpgradeItem;
/*     */ import com.atlas.common.fabric.safari.meteor.SafariMeteorType;
/*     */ import com.cobblemon.mod.common.CobblemonItems;
/*     */ import com.cobblemon.mod.common.item.group.CobblemonItemGroups;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.function.Function;
/*     */ import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
/*     */ import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
/*     */ import net.minecraft.class_1738;
/*     */ import net.minecraft.class_1747;
/*     */ import net.minecraft.class_1792;
/*     */ import net.minecraft.class_1935;
/*     */ import net.minecraft.class_2248;
/*     */ import net.minecraft.class_2378;
/*     */ import net.minecraft.class_2960;
/*     */ import net.minecraft.class_7923;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class AtlasItemRegistry
/*     */ {
/*  38 */   public static final class_1792 MEGA_BRACELET = register("mega_bracelet", new MegaBraceletItem((new class_1792.class_1793()).method_7889(1)));
/*     */ 
/*     */   
/*  41 */   public static final class_1747 POKE_SPINNER_ITEM = register("poke_spinner_block_entity_item", new class_1747((class_2248)AtlasBlockRegistry.POKE_SPINNER_BLOCK, new class_1792.class_1793()));
/*  42 */   public static final class_1747 MONTHLY_CRATE_ITEM = register("monthly_crate_block_entity_item", new class_1747((class_2248)AtlasBlockRegistry.MONTHLY_CRATE_BLOCK, new class_1792.class_1793()));
/*  43 */   public static final class_1747 PREMIER_CRATE_ITEM = register("premier_crate_block_entity_item", new class_1747((class_2248)AtlasBlockRegistry.PREMIER_CRATE_BLOCK, new class_1792.class_1793()));
/*  44 */   public static final class_1747 ULTRA_CRATE_ITEM = register("ultra_crate_block_entity_item", new class_1747((class_2248)AtlasBlockRegistry.ULTRA_CRATE_BLOCK, new class_1792.class_1793()));
/*  45 */   public static final class_1747 VICTORY_CRATE_ITEM = register("victory_crate_block_entity_item", new class_1747((class_2248)AtlasBlockRegistry.VICTORY_CRATE_BLOCK, new class_1792.class_1793()));
/*     */ 
/*     */ 
/*     */   
/*  49 */   public static final Map<String, class_1747> STATUE_ITEMS = registerStatueItems();
/*     */ 
/*     */   
/*     */   @Nullable
/*  53 */   public static final class_1792 CRACKED_ENERGY_RUNE = (class_1792)register("cracked_energy_rune", new EnergyRuneItem(36000, new class_1792.class_1793()));
/*     */   
/*     */   @Nullable
/*  56 */   public static final class_1792 POLISHED_ENERGY_RUNE = (class_1792)register("polished_energy_rune", new EnergyRuneItem(72000, new class_1792.class_1793()));
/*     */   
/*     */   @Nullable
/*  59 */   public static final class_1792 PRISTINE_ENERGY_RUNE = (class_1792)register("pristine_energy_rune", new EnergyRuneItem(144000, new class_1792.class_1793()));
/*     */ 
/*     */ 
/*     */   
/*  63 */   public static final Map<String, class_1792> SAFARI_METEOR_RAW_ITEMS = registerSafariMeteorItems(SafariMeteorType::rawItemId);
/*  64 */   public static final Map<String, class_1792> SAFARI_METEOR_INGOT_ITEMS = registerSafariMeteorItems(SafariMeteorType::ingotItemId);
/*  65 */   public static final Map<String, class_1747> SAFARI_METEOR_ORE_ITEMS = registerSafariMeteorBlockItems(AtlasBlockRegistry.SAFARI_METEOR_ORE_BLOCKS, SafariMeteorType::oreBlockId);
/*  66 */   public static final Map<String, class_1747> SAFARI_METEOR_STORAGE_ITEMS = registerSafariMeteorBlockItems(AtlasBlockRegistry.SAFARI_METEOR_STORAGE_BLOCKS, SafariMeteorType::storageBlockId);
/*     */ 
/*     */   
/*  69 */   public static final class_1747 HUT_ITEM = register("hut_block_entity_item", new class_1747((class_2248)AtlasBlockRegistry.HUT_BLOCK, new class_1792.class_1793()));
/*  70 */   public static final class_1747 CROP_YARD_ITEM = register("crop_yard_block_entity_item", new class_1747((class_2248)AtlasBlockRegistry.CROP_YARD_BLOCK, new class_1792.class_1793()));
/*  71 */   public static final class_1747 FISHING_POND_ITEM = register("fishing_pond_block_entity_item", new class_1747((class_2248)AtlasBlockRegistry.FISHING_POND_BLOCK, new class_1792.class_1793()));
/*  72 */   public static final class_1747 FORGE_ITEM = register("forge_block_entity_item", new class_1747((class_2248)AtlasBlockRegistry.FORGE_BLOCK, new class_1792.class_1793()));
/*  73 */   public static final class_1747 ORE_MINE_ITEM = register("ore_mine_block_entity_item", new class_1747((class_2248)AtlasBlockRegistry.ORE_MINE_BLOCK, new class_1792.class_1793()));
/*  74 */   public static final class_1747 MOB_ARENA_ITEM = register("mob_arena_block_entity_item", new class_1747((class_2248)AtlasBlockRegistry.MOB_ARENA_BLOCK, new class_1792.class_1793()));
/*  75 */   public static final class_1747 FLYING_COURSE_ITEM = register("flying_course_block_entity_item", new class_1747((class_2248)AtlasBlockRegistry.FLYING_COURSE_BLOCK, new class_1792.class_1793()));
/*  76 */   public static final class_1747 TESLA_COIL_ITEM = register("tesla_coil_block_entity_item", new class_1747((class_2248)AtlasBlockRegistry.TESLA_COIL_BLOCK, new class_1792.class_1793()));
/*  77 */   public static final class_1747 IGLOO_ITEM = register("igloo_block_entity_item", new class_1747((class_2248)AtlasBlockRegistry.IGLOO_BLOCK, new class_1792.class_1793()));
/*  78 */   public static final class_1747 MUSHROOM_GARDEN_ITEM = register("mushroom_garden_block_entity_item", new class_1747((class_2248)AtlasBlockRegistry.MUSHROOM_GARDEN_BLOCK, new class_1792.class_1793()));
/*  79 */   public static final class_1747 DIGGING_SITE_ITEM = register("digging_site_block_entity_item", new class_1747((class_2248)AtlasBlockRegistry.DIGGING_SITE_BLOCK, new class_1792.class_1793()));
/*  80 */   public static final class_1747 ZEN_GARDEN_ITEM = register("zen_garden_block_entity_item", new class_1747((class_2248)AtlasBlockRegistry.ZEN_GARDEN_BLOCK, new class_1792.class_1793()));
/*  81 */   public static final class_1747 BUG_NEST_ITEM = register("bug_nest_block_entity_item", new class_1747((class_2248)AtlasBlockRegistry.BUG_NEST_BLOCK, new class_1792.class_1793()));
/*  82 */   public static final class_1747 ABANDONED_FIELDS_ITEM = register("abandoned_fields_block_entity_item", new class_1747((class_2248)AtlasBlockRegistry.ABANDONED_FIELDS_BLOCK, new class_1792.class_1793()));
/*  83 */   public static final class_1747 ROARING_MOUNTAINS_ITEM = register("roaring_mountains_block_entity_item", new class_1747((class_2248)AtlasBlockRegistry.ROARING_MOUNTAINS_BLOCK, new class_1792.class_1793()));
/*  84 */   public static final class_1747 DARK_RUINS_ITEM = register("dark_ruins_block_entity_item", new class_1747((class_2248)AtlasBlockRegistry.DARK_RUINS_BLOCK, new class_1792.class_1793()));
/*  85 */   public static final class_1747 STEEL_MACHINE_ITEM = register("steel_machine_block_entity_item", new class_1747((class_2248)AtlasBlockRegistry.STEEL_MACHINE_BLOCK, new class_1792.class_1793()));
/*  86 */   public static final class_1747 BUTTERFLY_FIELDS_ITEM = register("butterfly_fields_block_entity_item", new class_1747((class_2248)AtlasBlockRegistry.BUTTERFLY_FIELDS_BLOCK, new class_1792.class_1793()));
/*     */ 
/*     */   
/*  89 */   public static final class_1747 VILLAGE_ITEM = register("village_block_entity_item", new class_1747((class_2248)AtlasBlockRegistry.VILLAGE_BLOCK, new class_1792.class_1793()));
/*  90 */   public static final class_1747 CROP_FARM_ITEM = register("crop_farm_block_entity_item", new class_1747((class_2248)AtlasBlockRegistry.CROP_FARM_BLOCK, new class_1792.class_1793()));
/*  91 */   public static final class_1747 FISHING_LAKE_ITEM = register("fishing_lake_block_entity_item", new class_1747((class_2248)AtlasBlockRegistry.FISHING_LAKE_BLOCK, new class_1792.class_1793()));
/*  92 */   public static final class_1747 MOLTEN_FORGE_ITEM = register("molten_forge_block_entity_item", new class_1747((class_2248)AtlasBlockRegistry.MOLTEN_FORGE_BLOCK, new class_1792.class_1793()));
/*  93 */   public static final class_1747 QUARRY_ITEM = register("quarry_block_entity_item", new class_1747((class_2248)AtlasBlockRegistry.QUARRY_BLOCK, new class_1792.class_1793()));
/*  94 */   public static final class_1747 MOB_STADIUM_ITEM = register("mob_stadium_block_entity_item", new class_1747((class_2248)AtlasBlockRegistry.MOB_STADIUM_BLOCK, new class_1792.class_1793()));
/*  95 */   public static final class_1747 GUSTY_HILLS_ITEM = register("gusty_hills_block_entity_item", new class_1747((class_2248)AtlasBlockRegistry.GUSTY_HILLS_BLOCK, new class_1792.class_1793()));
/*  96 */   public static final class_1747 ELECTRIC_FIELD_ITEM = register("electric_field_block_entity_item", new class_1747((class_2248)AtlasBlockRegistry.ELECTRIC_FIELD_BLOCK, new class_1792.class_1793()));
/*  97 */   public static final class_1747 GLACIERS_ITEM = register("glaciers_block_entity_item", new class_1747((class_2248)AtlasBlockRegistry.GLACIERS_BLOCK, new class_1792.class_1793()));
/*  98 */   public static final class_1747 MUSHROOM_FOREST_ITEM = register("mushroom_forest_block_entity_item", new class_1747((class_2248)AtlasBlockRegistry.MUSHROOM_FOREST_BLOCK, new class_1792.class_1793()));
/*  99 */   public static final class_1747 CRATER_ITEM = register("crater_block_entity_item", new class_1747((class_2248)AtlasBlockRegistry.CRATER_BLOCK, new class_1792.class_1793()));
/* 100 */   public static final class_1747 TRANQUIL_FIELDS_ITEM = register("tranquil_fields_block_entity_item", new class_1747((class_2248)AtlasBlockRegistry.TRANQUIL_FIELDS_BLOCK, new class_1792.class_1793()));
/*     */ 
/*     */   
/* 103 */   public static final class_1747 INSECT_HIVE_ITEM = register("insect_hive_block_entity_item", new class_1747((class_2248)AtlasBlockRegistry.INSECT_HIVE_BLOCK, new class_1792.class_1793()));
/* 104 */   public static final class_1747 GRAVEYARD_ITEM = register("graveyard_block_entity_item", new class_1747((class_2248)AtlasBlockRegistry.GRAVEYARD_BLOCK, new class_1792.class_1793()));
/* 105 */   public static final class_1747 DRAGON_TEMPLE_ITEM = register("dragon_temple_block_entity_item", new class_1747((class_2248)AtlasBlockRegistry.DRAGON_TEMPLE_BLOCK, new class_1792.class_1793()));
/* 106 */   public static final class_1747 SHADOW_REALM_ITEM = register("shadow_realm_block_entity_item", new class_1747((class_2248)AtlasBlockRegistry.SHADOW_REALM_BLOCK, new class_1792.class_1793()));
/* 107 */   public static final class_1747 STEAMPUNK_FACTORY_ITEM = register("steampunk_factory_block_entity_item", new class_1747((class_2248)AtlasBlockRegistry.STEAMPUNK_FACTORY_BLOCK, new class_1792.class_1793()));
/* 108 */   public static final class_1747 MYSTIC_GARDEN_ITEM = register("mystic_garden_block_entity_item", new class_1747((class_2248)AtlasBlockRegistry.MYSTIC_GARDEN_BLOCK, new class_1792.class_1793()));
/*     */ 
/*     */   
/* 111 */   public static final class_1747 BATTLE_GROUNDS_ITEM = register("battle_grounds_block_entity_item", new class_1747((class_2248)AtlasBlockRegistry.BATTLE_GROUNDS_BLOCK, new class_1792.class_1793()));
/* 112 */   public static final class_1747 FOREST_TEMPLE_ITEM = register("forest_temple_block_entity_item", new class_1747((class_2248)AtlasBlockRegistry.FOREST_TEMPLE_BLOCK, new class_1792.class_1793()));
/* 113 */   public static final class_1747 HAUNTED_MANOR_ITEM = register("haunted_manor_block_entity_item", new class_1747((class_2248)AtlasBlockRegistry.HAUNTED_MANOR_BLOCK, new class_1792.class_1793()));
/* 114 */   public static final class_1747 ICE_PALACE_ITEM = register("ice_palace_block_entity_item", new class_1747((class_2248)AtlasBlockRegistry.ICE_PALACE_BLOCK, new class_1792.class_1793()));
/* 115 */   public static final class_1747 POISON_VALLEY_ITEM = register("poison_valley_block_entity_item", new class_1747((class_2248)AtlasBlockRegistry.POISON_VALLEY_BLOCK, new class_1792.class_1793()));
/* 116 */   public static final class_1747 THUNDERSTORM_VALLEY_ITEM = register("thunderstorm_valley_block_entity_item", new class_1747((class_2248)AtlasBlockRegistry.THUNDERSTORM_VALLEY_BLOCK, new class_1792.class_1793()));
/* 117 */   public static final class_1747 VOLCANO_ITEM = register("volcano_block_entity_item", new class_1747((class_2248)AtlasBlockRegistry.VOLCANO_BLOCK, new class_1792.class_1793()));
/* 118 */   public static final class_1747 WYVERN_GROUNDS_ITEM = register("wyvern_grounds_block_entity_item", new class_1747((class_2248)AtlasBlockRegistry.WYVERN_GROUNDS_BLOCK, new class_1792.class_1793()));
/*     */ 
/*     */   
/* 121 */   public static final class_1747 ENCHANTED_FOREST_ITEM = register("enchanted_forest_block_entity_item", new class_1747((class_2248)AtlasBlockRegistry.ENCHANTED_FOREST_BLOCK, new class_1792.class_1793()));
/*     */ 
/*     */   
/*     */   @Nullable
/* 125 */   public static final class_1792 SAFARI_FISHING_ROD = (class_1792)register("safari_rod", new SafariFishingRodItem((new class_1792.class_1793()).method_7889(1)));
/*     */   
/* 127 */   public static final class_1792 KELP_BAIT = (class_1792)register("kelp_bait", new SafariUpgradeItem(SafariUpgradeSlot.BAIT, "kelp_bait", new class_1792.class_1793()));
/* 128 */   public static final class_1792 LUMINOUS_BAIT = (class_1792)register("luminous_bait", new SafariUpgradeItem(SafariUpgradeSlot.BAIT, "luminous_bait", new class_1792.class_1793()));
/* 129 */   public static final class_1792 STURDY_LINE = (class_1792)register("sturdy_line", new SafariUpgradeItem(SafariUpgradeSlot.LINE, "sturdy_line", new class_1792.class_1793()));
/* 130 */   public static final class_1792 SILK_LINE = (class_1792)register("silk_line", new SafariUpgradeItem(SafariUpgradeSlot.LINE, "silk_line", new class_1792.class_1793()));
/* 131 */   public static final class_1792 IRON_REEL = (class_1792)register("iron_reel", new SafariUpgradeItem(SafariUpgradeSlot.REEL, "iron_reel", new class_1792.class_1793()));
/* 132 */   public static final class_1792 PRECISION_REEL = (class_1792)register("precision_reel", new SafariUpgradeItem(SafariUpgradeSlot.REEL, "precision_reel", new class_1792.class_1793()));
/*     */ 
/*     */   
/* 135 */   public static final Map<String, class_1792> POKE_ARMOR_MATERIAL_ITEMS = registerPokeArmorMaterialItems();
/* 136 */   public static final Map<String, PokeArmorItem> POKE_ARMOR_ITEMS = registerPokeArmorItems();
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
/*     */   public static <I extends class_1792> I register(@NotNull String name, @NotNull I item) {
/* 149 */     return (I)class_2378.method_10230((class_2378)class_7923.field_41178, class_2960.method_60655("atlas-common-fabric", Objects.<String>requireNonNull(name)), Objects.<class_1792>requireNonNull((class_1792)item));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static class_1747 getStationItem(@NotNull String stationId) {
/* 160 */     return (class_1747)class_7923.field_41178.method_10223(class_2960.method_60655("atlas-common-fabric", stationId + "_block_entity_item"));
/*     */   }
/*     */   @Nullable
/*     */   public static class_1747 getStatueItem(@NotNull String statueId) {
/* 164 */     return STATUE_ITEMS.get(statueId);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void initialize() {
/* 171 */     ItemGroupEvents.modifyEntriesEvent(CobblemonItemGroups.getHELD_ITEMS_KEY()).register(entries -> entries.addAfter((class_1935)CobblemonItems.LIGHT_BALL, new class_1935[] { (class_1935)MEGA_BRACELET }));
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   private static Map<String, class_1747> registerStatueItems() {
/* 177 */     Map<String, class_1747> statueItems = new LinkedHashMap<>();
/* 178 */     for (StatueDefinition definition : StatueRegistry.definitions()) {
/* 179 */       StatueBlock block = AtlasBlockRegistry.getStatueBlock(definition.id());
/* 180 */       if (block == null) {
/*     */         continue;
/*     */       }
/*     */       
/* 184 */       statueItems.put(definition.id(), register(definition.id(), new class_1747((class_2248)block, new class_1792.class_1793())));
/*     */     } 
/* 186 */     return Map.copyOf(statueItems);
/*     */   }
/*     */   @NotNull
/*     */   private static Map<String, class_1792> registerPokeArmorMaterialItems() {
/* 190 */     Map<String, class_1792> items = new LinkedHashMap<>();
/*     */     
/* 192 */     registerPokeArmorMaterial(items, "dragon_scale_ore");
/* 193 */     registerPokeArmorMaterial(items, "dragonite_wings");
/* 194 */     registerPokeArmorMaterial(items, "dragonite_horns");
/* 195 */     registerPokeArmorMaterial(items, "dragonite_tail");
/*     */     
/* 197 */     registerPokeArmorMaterial(items, "shadow_ore");
/* 198 */     registerPokeArmorMaterial(items, "gengar_soul");
/* 199 */     registerPokeArmorMaterial(items, "gengar_orb");
/* 200 */     registerPokeArmorMaterial(items, "gengar_shadow");
/*     */     
/* 202 */     registerPokeArmorMaterial(items, "solar_ore");
/* 203 */     registerPokeArmorMaterial(items, "solgaleo_mane");
/* 204 */     registerPokeArmorMaterial(items, "solgaleo_tail");
/* 205 */     registerPokeArmorMaterial(items, "solgaleo_core");
/*     */     
/* 207 */     registerPokeArmorMaterial(items, "astral_ore");
/* 208 */     registerPokeArmorMaterial(items, "lunala_wings");
/* 209 */     registerPokeArmorMaterial(items, "lunala_horns");
/* 210 */     registerPokeArmorMaterial(items, "lunala_core");
/*     */     
/* 212 */     registerPokeArmorMaterial(items, "soul_ore");
/* 213 */     registerPokeArmorMaterial(items, "necrozma_shell");
/* 214 */     registerPokeArmorMaterial(items, "necrozma_spikes");
/* 215 */     registerPokeArmorMaterial(items, "necrozma_core");
/*     */     
/* 217 */     return Map.copyOf(items);
/*     */   }
/*     */   
/*     */   private static void registerPokeArmorMaterial(@NotNull Map<String, class_1792> items, @NotNull String id) {
/* 221 */     items.put(id, register(id, new class_1792(new class_1792.class_1793())));
/*     */   }
/*     */   @NotNull
/*     */   private static Map<String, class_1792> registerSafariMeteorItems(@NotNull Function<SafariMeteorType, String> idProvider) {
/* 225 */     Map<String, class_1792> items = new LinkedHashMap<>();
/* 226 */     for (SafariMeteorType type : SafariMeteorType.all()) {
/* 227 */       items.put(type.id(), register(idProvider.apply(type), new class_1792(new class_1792.class_1793())));
/*     */     }
/* 229 */     return Map.copyOf(items);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   private static Map<String, class_1747> registerSafariMeteorBlockItems(@NotNull Map<String, class_2248> blocks, @NotNull Function<SafariMeteorType, String> idProvider) {
/* 234 */     Map<String, class_1747> items = new LinkedHashMap<>();
/* 235 */     for (SafariMeteorType type : SafariMeteorType.all()) {
/* 236 */       class_2248 block = blocks.get(type.id());
/* 237 */       if (block == null)
/* 238 */         continue;  items.put(type.id(), register(idProvider.apply(type), new class_1747(block, new class_1792.class_1793())));
/*     */     } 
/* 240 */     return Map.copyOf(items);
/*     */   }
/*     */   @NotNull
/*     */   private static Map<String, PokeArmorItem> registerPokeArmorItems() {
/* 244 */     Map<String, PokeArmorItem> items = new LinkedHashMap<>();
/*     */     
/* 246 */     for (PokeArmorSet armorSet : PokeArmorSet.values()) {
/* 247 */       registerPokeArmorItem(items, armorSet, class_1738.class_8051.field_41934, "helmet");
/* 248 */       registerPokeArmorItem(items, armorSet, class_1738.class_8051.field_41935, "chestplate");
/* 249 */       registerPokeArmorItem(items, armorSet, class_1738.class_8051.field_41936, "leggings");
/* 250 */       registerPokeArmorItem(items, armorSet, class_1738.class_8051.field_41937, "boots");
/*     */     } 
/*     */     
/* 253 */     return Map.copyOf(items);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void registerPokeArmorItem(@NotNull Map<String, PokeArmorItem> items, @NotNull PokeArmorSet armorSet, @NotNull class_1738.class_8051 type, @NotNull String typeName) {
/* 260 */     String id = armorSet.id() + "_armor_" + armorSet.id();
/* 261 */     items.put(id, register(id, new PokeArmorItem(armorSet, type, (new class_1792.class_1793()).method_7889(1))));
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\item\AtlasItemRegistry.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */