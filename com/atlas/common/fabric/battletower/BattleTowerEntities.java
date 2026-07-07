/*    */ package com.atlas.common.fabric.battletower;
/*    */ 
/*    */ import com.atlas.common.fabric.AtlasMod;
/*    */ import com.atlas.common.fabric.battletower.entity.BattleTowerTrainerEntity;
/*    */ import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
/*    */ import net.minecraft.class_1299;
/*    */ import net.minecraft.class_1311;
/*    */ import net.minecraft.class_2378;
/*    */ import net.minecraft.class_2960;
/*    */ import net.minecraft.class_5617;
/*    */ import net.minecraft.class_7923;
/*    */ 
/*    */ 
/*    */ public class BattleTowerEntities
/*    */ {
/* 16 */   public static final class_1299<BattleTowerTrainerEntity> BATTLE_TOWER_TRAINER = (class_1299<BattleTowerTrainerEntity>)class_2378.method_10230((class_2378)class_7923.field_41177, 
/*    */       
/* 18 */       class_2960.method_60655("cobblemon_battle_tower", "battle_tower_trainer"), 
/* 19 */       class_1299.class_1300.method_5903(BattleTowerTrainerEntity::new, class_1311.field_17715)
/* 20 */       .method_17687(0.6F, 1.8F)
/* 21 */       .method_27299(64)
/* 22 */       .method_27300(1)
/* 23 */       .method_5905("battle_tower_trainer"));
/*    */ 
/*    */ 
/*    */   
/*    */   public static void init() {
/* 28 */     AtlasMod.LOGGER.info("Registering Battle Tower entities");
/*    */   }
/*    */ 
/*    */   
/*    */   public static void initClient() {
/* 33 */     EntityRendererRegistry.register(BATTLE_TOWER_TRAINER, com.atlas.common.fabric.battletower.renderer.BattleTowerTrainerRenderer::new);
/* 34 */     AtlasMod.LOGGER.info("Registered Battle Tower entity renderers");
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\battletower\BattleTowerEntities.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */