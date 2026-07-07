/*    */ package com.atlas.common.fabric.block.statue.entity;
/*    */ 
/*    */ import com.atlas.common.fabric.block.AtlasBlockRegistry;
/*    */ import net.minecraft.class_2338;
/*    */ import net.minecraft.class_2487;
/*    */ import net.minecraft.class_2586;
/*    */ import net.minecraft.class_2591;
/*    */ import net.minecraft.class_2596;
/*    */ import net.minecraft.class_2602;
/*    */ import net.minecraft.class_2622;
/*    */ import net.minecraft.class_2680;
/*    */ import net.minecraft.class_7225;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ public final class StatueProxyBlockEntity
/*    */   extends class_2586 {
/*    */   private static final String CORE_POS_KEY = "CorePos";
/*    */   private static final String OFFSET_X_KEY = "LocalOffsetX";
/*    */   private static final String OFFSET_Y_KEY = "LocalOffsetY";
/*    */   private static final String OFFSET_Z_KEY = "LocalOffsetZ";
/* 22 */   private class_2338 corePos = class_2338.field_10980;
/* 23 */   private class_2338 localOffset = class_2338.field_10980;
/*    */   
/*    */   public StatueProxyBlockEntity(@NotNull class_2591<?> type, @NotNull class_2338 pos, @NotNull class_2680 state) {
/* 26 */     super(type, pos, state);
/*    */   }
/*    */   
/*    */   public StatueProxyBlockEntity(@NotNull class_2338 pos, @NotNull class_2680 state) {
/* 30 */     this(AtlasBlockRegistry.STATUE_PROXY_BLOCK_ENTITY_TYPE, pos, state);
/*    */   }
/*    */   @NotNull
/*    */   public class_2338 getCorePos() {
/* 34 */     return this.corePos;
/*    */   }
/*    */   @NotNull
/*    */   public class_2338 getLocalOffset() {
/* 38 */     return this.localOffset;
/*    */   }
/*    */   
/*    */   public void setCoreData(@NotNull class_2338 corePos, @NotNull class_2338 localOffset) {
/* 42 */     this.corePos = corePos.method_10062();
/* 43 */     this.localOffset = localOffset.method_10062();
/* 44 */     method_5431();
/*    */   }
/*    */ 
/*    */   
/*    */   protected void method_11014(@NotNull class_2487 nbt, @NotNull class_7225.class_7874 registries) {
/* 49 */     super.method_11014(nbt, registries);
/* 50 */     this.corePos = class_2338.method_10092(nbt.method_10537("CorePos"));
/* 51 */     this.localOffset = new class_2338(nbt.method_10550("LocalOffsetX"), nbt.method_10550("LocalOffsetY"), nbt.method_10550("LocalOffsetZ"));
/*    */   }
/*    */ 
/*    */   
/*    */   protected void method_11007(@NotNull class_2487 nbt, @NotNull class_7225.class_7874 registries) {
/* 56 */     super.method_11007(nbt, registries);
/* 57 */     nbt.method_10544("CorePos", this.corePos.method_10063());
/* 58 */     nbt.method_10569("LocalOffsetX", this.localOffset.method_10263());
/* 59 */     nbt.method_10569("LocalOffsetY", this.localOffset.method_10264());
/* 60 */     nbt.method_10569("LocalOffsetZ", this.localOffset.method_10260());
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public class_2596<class_2602> method_38235() {
/* 65 */     return (class_2596<class_2602>)class_2622.method_38585(this);
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public class_2487 method_16887(@NotNull class_7225.class_7874 registries) {
/* 70 */     return method_38244(registries);
/*    */   }
/*    */ 
/*    */   
/*    */   public void method_5431() {
/* 75 */     super.method_5431();
/* 76 */     if (this.field_11863 != null && !this.field_11863.field_9236)
/* 77 */       this.field_11863.method_8413(this.field_11867, method_11010(), method_11010(), 2); 
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\block\statue\entity\StatueProxyBlockEntity.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */