/*     */ package com.atlas.common.fabric.battletower.data;
/*     */ import com.cobblemon.mod.common.pokemon.Pokemon;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.class_2487;
/*     */ import net.minecraft.class_2499;
/*     */ import net.minecraft.class_2520;
/*     */ import net.minecraft.class_5455;
/*     */ import net.minecraft.class_7225;
/*     */ 
/*     */ public class TeamSnapshot {
/*  14 */   private List<Pokemon> snapshotTeam = new ArrayList<>();
/*  15 */   private long snapshotTime = System.currentTimeMillis();
/*     */   
/*     */   public void captureTeam(List<Pokemon> party, UUID playerUUID) {
/*  18 */     captureTeam(party, playerUUID, null);
/*     */   }
/*     */   
/*     */   public void captureTeam(List<Pokemon> party, UUID playerUUID, class_7225.class_7874 registries) {
/*  22 */     this.snapshotTeam.clear();
/*  23 */     this.snapshotTime = System.currentTimeMillis();
/*  24 */     class_5455 registryAccess = (registries instanceof class_5455) ? (class_5455)registries : null;
/*  25 */     Iterator<Pokemon> var3 = party.iterator();
/*     */     
/*  27 */     while (var3.hasNext()) {
/*  28 */       Pokemon pokemon = var3.next();
/*  29 */       if (pokemon != null && !pokemon.isFainted()) {
/*  30 */         Pokemon cloned = clonePokemon(pokemon, registryAccess);
/*  31 */         cloned.heal();
/*  32 */         this.snapshotTeam.add(cloned);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Pokemon> getTeam(UUID playerUUID) {
/*  39 */     return getTeam(playerUUID, null);
/*     */   }
/*     */   
/*     */   public List<Pokemon> getTeam(UUID playerUUID, class_7225.class_7874 registries) {
/*  43 */     List<Pokemon> clones = new ArrayList<>();
/*  44 */     class_5455 registryAccess = (registries instanceof class_5455) ? (class_5455)registries : null;
/*  45 */     Iterator<Pokemon> var3 = this.snapshotTeam.iterator();
/*     */     
/*  47 */     while (var3.hasNext()) {
/*  48 */       Pokemon pokemon = var3.next();
/*  49 */       Pokemon clone = clonePokemon(pokemon, registryAccess);
/*  50 */       clones.add(clone);
/*     */     } 
/*     */     
/*  53 */     return clones;
/*     */   }
/*     */   
/*     */   private static Pokemon clonePokemon(Pokemon source, class_5455 registryAccess) {
/*  57 */     if (registryAccess != null) {
/*     */       try {
/*  59 */         Pokemon pokemon = new Pokemon();
/*  60 */         pokemon.loadFromNBT(registryAccess, source.saveToNBT(registryAccess, new class_2487()));
/*  61 */         return pokemon;
/*  62 */       } catch (Exception exception) {}
/*     */     }
/*     */ 
/*     */     
/*  66 */     Pokemon clone = new Pokemon();
/*  67 */     clone.copyFrom(source);
/*  68 */     return clone;
/*     */   }
/*     */   
/*     */   public List<Pokemon> getOriginalTeam() {
/*  72 */     return new ArrayList<>(this.snapshotTeam);
/*     */   }
/*     */   
/*     */   public boolean hasSnapshot() {
/*  76 */     return !this.snapshotTeam.isEmpty();
/*     */   }
/*     */   
/*     */   public int getTeamSize() {
/*  80 */     return this.snapshotTeam.size();
/*     */   }
/*     */   
/*     */   public void clear() {
/*  84 */     this.snapshotTeam.clear();
/*  85 */     this.snapshotTime = 0L;
/*     */   }
/*     */   
/*     */   public long getSnapshotTime() {
/*  89 */     return this.snapshotTime;
/*     */   }
/*     */   
/*     */   public class_2487 toNbt(class_7225.class_7874 registries) {
/*  93 */     class_2487 tag = new class_2487();
/*  94 */     tag.method_10544("snapshotTime", this.snapshotTime);
/*  95 */     class_2499 pokemonList = new class_2499();
/*  96 */     class_5455 registryAccess = (class_5455)registries;
/*  97 */     Iterator<Pokemon> var5 = this.snapshotTeam.iterator();
/*     */     
/*  99 */     while (var5.hasNext()) {
/* 100 */       Pokemon pokemon = var5.next();
/* 101 */       class_2487 pokemonTag = pokemon.saveToNBT(registryAccess, new class_2487());
/* 102 */       pokemonList.add(pokemonTag);
/*     */     } 
/*     */     
/* 105 */     tag.method_10566("team", (class_2520)pokemonList);
/* 106 */     return tag;
/*     */   }
/*     */   
/*     */   public static TeamSnapshot fromNbt(class_2487 tag, class_7225.class_7874 registries) {
/* 110 */     TeamSnapshot snapshot = new TeamSnapshot();
/* 111 */     snapshot.snapshotTime = tag.method_10537("snapshotTime");
/* 112 */     if (tag.method_10545("team")) {
/* 113 */       class_2499 pokemonList = tag.method_10554("team", 10);
/* 114 */       class_5455 registryAccess = (class_5455)registries;
/*     */       
/* 116 */       for (int i = 0; i < pokemonList.size(); i++) {
/* 117 */         class_2487 pokemonTag = pokemonList.method_10602(i);
/* 118 */         Pokemon pokemon = new Pokemon();
/* 119 */         pokemon.loadFromNBT(registryAccess, pokemonTag);
/* 120 */         snapshot.snapshotTeam.add(pokemon);
/*     */       } 
/*     */     } 
/*     */     
/* 124 */     return snapshot;
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\battletower\data\TeamSnapshot.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */