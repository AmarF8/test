/*    */ package com.atlas.common.fabric.safari.fishing.entity;
/*    */ 
/*    */ import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
/*    */ import net.minecraft.class_1299;
/*    */ import net.minecraft.class_1311;
/*    */ import net.minecraft.class_2378;
/*    */ import net.minecraft.class_2960;
/*    */ import net.minecraft.class_5617;
/*    */ import net.minecraft.class_7923;
/*    */ 
/*    */ public final class SafariFishingBobberEntities {
/* 12 */   public static final class_1299<AtlasSafariBobberEntity> SAFARI_BOBBER = (class_1299<AtlasSafariBobberEntity>)class_2378.method_10230((class_2378)class_7923.field_41177, 
/*    */       
/* 14 */       class_2960.method_60655("atlas-common-fabric", "safari_bobber"), 
/* 15 */       class_1299.class_1300.method_5903(AtlasSafariBobberEntity::new, class_1311.field_17715)
/* 16 */       .method_17687(0.25F, 0.25F)
/* 17 */       .method_27299(64)
/* 18 */       .method_27300(2)
/* 19 */       .method_5905("safari_bobber"));
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void init() {}
/*    */ 
/*    */ 
/*    */   
/*    */   public static void initClient() {
/* 29 */     EntityRendererRegistry.register(SAFARI_BOBBER, AtlasSafariBobberEntityRenderer::new);
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\safari\fishing\entity\SafariFishingBobberEntities.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */