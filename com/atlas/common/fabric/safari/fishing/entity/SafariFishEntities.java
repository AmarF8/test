/*    */ package com.atlas.common.fabric.safari.fishing.entity;
/*    */ 
/*    */ import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
/*    */ import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
/*    */ import net.minecraft.class_1299;
/*    */ import net.minecraft.class_1311;
/*    */ import net.minecraft.class_2378;
/*    */ import net.minecraft.class_2960;
/*    */ import net.minecraft.class_5617;
/*    */ import net.minecraft.class_7923;
/*    */ 
/*    */ public final class SafariFishEntities {
/* 13 */   public static final class_1299<SafariFishEntity> SAFARI_FISH = (class_1299<SafariFishEntity>)class_2378.method_10230((class_2378)class_7923.field_41177, 
/*    */       
/* 15 */       class_2960.method_60655("atlas-common-fabric", "safari_fish"), 
/* 16 */       class_1299.class_1300.method_5903(SafariFishEntity::new, class_1311.field_17715)
/* 17 */       .method_17687(0.9F, 0.6F)
/* 18 */       .method_27299(64)
/* 19 */       .method_27300(2)
/* 20 */       .method_5905("safari_fish"));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void init() {
/* 27 */     FabricDefaultAttributeRegistry.register(SAFARI_FISH, SafariFishEntity.createAttributes());
/*    */   }
/*    */   
/*    */   public static void initClient() {
/* 31 */     EntityRendererRegistry.register(SAFARI_FISH, SafariFishEntityRenderer::new);
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\safari\fishing\entity\SafariFishEntities.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */