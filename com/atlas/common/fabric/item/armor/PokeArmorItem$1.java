/*    */ package com.atlas.common.fabric.item.armor;
/*    */ 
/*    */ import net.minecraft.class_1304;
/*    */ import net.minecraft.class_1799;
/*    */ import net.minecraft.class_572;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ import software.bernie.geckolib.animatable.client.GeoRenderProvider;
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
/*    */ 
/*    */ class null
/*    */   implements GeoRenderProvider
/*    */ {
/*    */   private PokeArmorRenderer renderer;
/*    */   
/*    */   public <T extends net.minecraft.class_1309> class_572<?> getGeoArmorRenderer(@Nullable T livingEntity, @NotNull class_1799 itemStack, @Nullable class_1304 equipmentSlot, @Nullable class_572<T> original) {
/* 56 */     if (this.renderer == null) {
/* 57 */       this.renderer = new PokeArmorRenderer(PokeArmorItem.this.armorSet);
/*    */     }
/* 59 */     return (class_572<?>)this.renderer;
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\item\armor\PokeArmorItem$1.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */