/*    */ package com.atlas.common.fabric.block.animated.render;
/*    */ 
/*    */ import com.atlas.common.fabric.block.animated.entity.AnimatedBlockEntity;
/*    */ import com.atlas.common.fabric.block.animated.model.AnimatedBlockModel;
/*    */ import net.minecraft.class_2960;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import software.bernie.geckolib.model.GeoModel;
/*    */ import software.bernie.geckolib.renderer.GeoBlockRenderer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AnimatedBlockRenderer
/*    */   extends GeoBlockRenderer<AnimatedBlockEntity>
/*    */ {
/*    */   public AnimatedBlockRenderer(@NotNull class_2960 path) {
/* 23 */     super((GeoModel)new AnimatedBlockModel(path));
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\block\animated\render\AnimatedBlockRenderer.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */