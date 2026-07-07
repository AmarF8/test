/*     */ package com.atlas.common.fabric.guide.data;
/*     */ 
/*     */ import com.google.gson.annotations.SerializedName;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BaseStats
/*     */ {
/*     */   public int hp;
/*     */   public int attack;
/*     */   public int defense;
/*     */   @SerializedName("specialAttack")
/*     */   public int spAtk;
/*     */   @SerializedName("specialDefense")
/*     */   public int spDef;
/*     */   public int speed;
/*     */   
/*     */   public int get(int index) {
/*  88 */     switch (index) { case 0: case 1: case 2: case 3: case 4: case 5:  }  return 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  95 */       0;
/*     */   }
/*     */ 
/*     */   
/*     */   public int total() {
/* 100 */     return this.hp + this.attack + this.defense + this.spAtk + this.spDef + this.speed;
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\guide\data\PokemonDetail$BaseStats.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */