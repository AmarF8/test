/*     */ package com.atlas.common.fabric.morph;
/*     */ 
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class MorphCatalog
/*     */ {
/*  15 */   private static final List<Integer> MOVE_SLOT_LEVELS = List.of(Integer.valueOf(1), Integer.valueOf(6), Integer.valueOf(14), Integer.valueOf(24));
/*  16 */   private static final List<MorphModels.MorphDefinition> DEFINITIONS = List.of(new MorphModels.MorphDefinition("cobblemon:pikachu", "Pikachu", "Burst Mage", "Quick electric caster built for snappy zoning and chain pressure.", true, 0, 1500, 220, 140, 128, 122, 
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
/*  29 */         List.of(new MorphModels.MorphMove("thundershock", "Thunder Shock", 1, "Fast poke that starts the kit online immediately.", 60, 4.0D), new MorphModels.MorphMove("thunderbolt", "Thunderbolt", 4, "Reliable ranged burst with strong target impact.", 95, 7.0D), new MorphModels.MorphMove("thunderwave", "Thunder Wave", 10, "Status pressure for catch and collapse moments.", 0, 9.0D), new MorphModels.MorphMove("thunder", "Thunder", 20, "Big commitment strike for wave clears and finishers.", 160, 16.0D))), new MorphModels.MorphDefinition("cobblemon:charizard", "Charizard", "Battle Bruiser", "Flexible fire brawler that transitions between siege pressure and aerial chase.", true, 0, 2100, 245, 205, 104, 110, 
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
/*  48 */         List.of(new MorphModels.MorphMove("wingattack", "Wing Attack", 1, "Mobility-first opener that keeps the dragon mobile.", 65, 4.0D), new MorphModels.MorphMove("flameburst", "Flame Burst", 5, "Clean ranged burst for lane and wave pressure.", 90, 6.0D), new MorphModels.MorphMove("flamethrower", "Flamethrower", 12, "Sustained breath attack for objective control.", 120, 10.0D), new MorphModels.MorphMove("fireblast", "Fire Blast", 24, "Heavy finisher built to punish clustered targets.", 175, 18.0D))), new MorphModels.MorphDefinition("cobblemon:garchomp", "Garchomp", "Diver", "Explosive melee chaser that thrives once it gets into the backline.", false, 650, 2300, 255, 190, 96, 125, 
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
/*  67 */         List.of(new MorphModels.MorphMove("bulldoze", "Bulldoze", 1, "Grounded engage that helps break formations.", 70, 5.0D), new MorphModels.MorphMove("crunch", "Crunch", 6, "Reliable bite for all-in follow through.", 95, 7.0D), new MorphModels.MorphMove("dragonclaw", "Dragon Claw", 12, "Signature slash with clean melee readability.", 130, 10.0D), new MorphModels.MorphMove("sandattack", "Sand Attack", 22, "Utility disruption that sells the shark fantasy.", 0, 12.0D))), new MorphModels.MorphDefinition("cobblemon:blaziken", "Blaziken", "Skirmisher", "High-tempo fighter that rewards spacing and cooldown timing.", false, 750, 2050, 248, 180, 118, 120, 
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
/*  86 */         List.of(new MorphModels.MorphMove("firepunch", "Fire Punch", 1, "Fast melee opener with clear impact.", 70, 4.0D), new MorphModels.MorphMove("flamecharge", "Flame Charge", 7, "Momentum tool for repositioning and engages.", 80, 6.0D), new MorphModels.MorphMove("closecombat", "Close Combat", 14, "Commitment burst for bruiser all-ins.", 150, 12.0D), new MorphModels.MorphMove("fireblast", "Fire Blast", 24, "Big spectacle cast for fight-ending pressure.", 175, 18.0D))), new MorphModels.MorphDefinition("cobblemon:cinderace", "Cinderace", "Marksman", "Ranged carry that leans on speed, poke, and clean objective damage.", false, 700, 1750, 236, 150, 132, 132, 
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
/* 105 */         List.of(new MorphModels.MorphMove("quickattack", "Quick Attack", 1, "Snap dash for kiting and confirms.", 55, 3.0D), new MorphModels.MorphMove("flamecharge", "Flame Charge", 6, "Fire-lined mobility to keep pressure up.", 85, 6.0D), new MorphModels.MorphMove("doubleteam", "Double Team", 12, "Utility cast that sells evasive striker play.", 0, 11.0D), new MorphModels.MorphMove("fireblast", "Fire Blast", 24, "Late-slot nuke for teamfight moments.", 170, 17.0D))), new MorphModels.MorphDefinition("cobblemon:ceruledge", "Ceruledge", "Assassin", "Blade-first ghost duelist with dramatic casts and execute windows.", false, 850, 1900, 252, 170, 114, 118, 
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
/* 124 */         List.of(new MorphModels.MorphMove("shadowclaw", "Shadow Claw", 1, "Quick slash to keep assassin patterns readable.", 70, 4.0D), new MorphModels.MorphMove("willowisp", "Will-O-Wisp", 6, "Burn pressure that fits the spectral blade theme.", 0, 9.0D), new MorphModels.MorphMove("shadowball", "Shadow Ball", 12, "Ranged poke for approach and finish.", 120, 9.0D), new MorphModels.MorphMove("swordsdance", "Swords Dance", 22, "Dramatic setup cast for highlight moments.", 0, 14.0D))), new MorphModels.MorphDefinition("cobblemon:gengar", "Gengar", "Control Mage", "Trickster caster with flashy projectile hits and oppressive zoning.", true, 0, 1680, 238, 146, 121, 126, 
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
/* 143 */         List.of(new MorphModels.MorphMove("lick", "Lick", 1, "Cheap opener that sells close-range menace.", 50, 3.0D), new MorphModels.MorphMove("shadowclaw", "Shadow Claw", 5, "Slash follow-up for scrappy duels.", 80, 5.0D), new MorphModels.MorphMove("shadowball", "Shadow Ball", 10, "Core ranged spell and strongest visual payoff.", 120, 9.0D), new MorphModels.MorphMove("hypnosis", "Hypnosis", 20, "Control slot that rounds out the champion fantasy.", 0, 14.0D))), new MorphModels.MorphDefinition("cobblemon:greninja", "Greninja", "Hybrid Duelist", "Nimble spell-slinger that can harass, vanish, and dart back in.", false, 900, 1825, 232, 155, 126, 136, 
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
/* 162 */         List.of(new MorphModels.MorphMove("quickattack", "Quick Attack", 1, "Fast engage that keeps the ninja in motion.", 60, 4.0D), new MorphModels.MorphMove("waterpulse", "Water Pulse", 5, "Core ranged projectile with strong center hits.", 100, 7.0D), new MorphModels.MorphMove("doubleteam", "Double Team", 12, "Illusion cast that sells the slippery playstyle.", 0, 11.0D), new MorphModels.MorphMove("shadowball", "Shadow Ball", 22, "Cross-element finisher for stylish burst.", 130, 10.0D))), new MorphModels.MorphDefinition("cobblemon:lucario", "Lucario", "Fighter", "Clean all-rounder with readable melee casts and simple upgrade paths.", false, 800, 1950, 242, 176, 112, 116, 
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
/* 181 */         List.of(new MorphModels.MorphMove("quickattack", "Quick Attack", 1, "Dash opener for stickiness and chase.", 60, 4.0D), new MorphModels.MorphMove("bulletpunch", "Bullet Punch", 6, "Straightforward combo extender.", 85, 5.0D), new MorphModels.MorphMove("closecombat", "Close Combat", 12, "Main melee burst tool for duels.", 145, 11.0D), new MorphModels.MorphMove("psychic", "Psychic", 22, "Late-slot utility pressure for ranged checks.", 130, 10.0D))));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 190 */   private static final Map<String, MorphModels.MorphDefinition> BY_ID = new LinkedHashMap<>();
/*     */   
/*     */   static {
/* 193 */     for (MorphModels.MorphDefinition definition : DEFINITIONS) {
/* 194 */       BY_ID.put(definition.speciesId(), definition);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static List<MorphModels.MorphDefinition> all() {
/* 201 */     return DEFINITIONS;
/*     */   }
/*     */   @NotNull
/*     */   public static String normalizeSpeciesId(@NotNull String speciesId) {
/* 205 */     String normalized = speciesId.toLowerCase(Locale.ENGLISH).trim();
/* 206 */     if (normalized.isEmpty()) {
/* 207 */       return normalized;
/*     */     }
/* 209 */     return normalized.contains(":") ? normalized : ("cobblemon:" + normalized);
/*     */   }
/*     */   @Nullable
/*     */   public static MorphModels.MorphDefinition get(@NotNull String speciesId) {
/* 213 */     return BY_ID.get(normalizeSpeciesId(speciesId));
/*     */   }
/*     */   @NotNull
/*     */   public static Optional<MorphModels.MorphDefinition> find(@NotNull String speciesId) {
/* 217 */     return Optional.ofNullable(get(speciesId));
/*     */   }
/*     */   
/*     */   public static boolean contains(@NotNull String speciesId) {
/* 221 */     return (get(speciesId) != null);
/*     */   }
/*     */   
/*     */   public static int moveSlotUnlockLevel(int slotIndex) {
/* 225 */     if (slotIndex < 0 || slotIndex >= MOVE_SLOT_LEVELS.size()) {
/* 226 */       return Integer.MAX_VALUE;
/*     */     }
/* 228 */     return ((Integer)MOVE_SLOT_LEVELS.get(slotIndex)).intValue();
/*     */   }
/*     */   
/*     */   public static int unlockedMoveSlots(int level) {
/* 232 */     int count = 0;
/* 233 */     for (int slot = 0; slot < MOVE_SLOT_LEVELS.size(); slot++) {
/* 234 */       if (level >= ((Integer)MOVE_SLOT_LEVELS.get(slot)).intValue()) {
/* 235 */         count++;
/*     */       }
/*     */     } 
/* 238 */     return Math.max(1, count);
/*     */   }
/*     */   @NotNull
/*     */   public static List<String> defaultLoadout(@NotNull MorphModels.MorphDefinition definition) {
/* 242 */     return definition.moves().stream()
/* 243 */       .limit(4L)
/* 244 */       .map(MorphModels.MorphMove::moveId)
/* 245 */       .toList();
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\morph\MorphCatalog.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */