/*    */ package com.atlas.common.fabric.cosmetic;
/*    */ 
/*    */ import net.minecraft.class_1799;
/*    */ import net.minecraft.class_1802;
/*    */ import net.minecraft.class_1935;
/*    */ import net.minecraft.class_9280;
/*    */ import net.minecraft.class_9334;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class CustomModelDataCosmeticModel
/*    */ {
/*    */   @NotNull
/*    */   private final CosmeticType type;
/*    */   private final int steveModelId;
/*    */   private final int alexModelId;
/*    */   private class_1799 steveStack;
/*    */   private class_1799 alexStack;
/*    */   private boolean steveStackInitialized;
/*    */   private boolean alexStackInitialized;
/*    */   
/*    */   public CustomModelDataCosmeticModel(@NotNull CosmeticType type, int steveModelId, int alexModelId) {
/* 25 */     this.type = type;
/* 26 */     this.steveModelId = steveModelId;
/* 27 */     this.alexModelId = alexModelId;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public CosmeticType getType() {
/* 32 */     return this.type;
/*    */   }
/*    */   
/*    */   public int getSteveModelId() {
/* 36 */     return this.steveModelId;
/*    */   }
/*    */   
/*    */   public int getAlexModelId() {
/* 40 */     return this.alexModelId;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public class_1799 getSteveStack() {
/* 45 */     if (!this.steveStackInitialized) {
/* 46 */       this.steveStack = createItemStackWithModelData(this.steveModelId);
/* 47 */       this.steveStackInitialized = true;
/*    */     } 
/* 49 */     return this.steveStack;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public class_1799 getAlexStack() {
/* 54 */     if (!this.alexStackInitialized) {
/* 55 */       this.alexStack = createItemStackWithModelData(this.alexModelId);
/* 56 */       this.alexStackInitialized = true;
/*    */     } 
/* 58 */     return this.alexStack;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   private class_1799 createItemStackWithModelData(int modelId) {
/* 63 */     class_1799 stack = new class_1799((class_1935)class_1802.field_8145);
/* 64 */     class_9280 data = new class_9280(modelId);
/* 65 */     stack.method_57379(class_9334.field_49637, data);
/* 66 */     return stack;
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\cosmetic\CustomModelDataCosmeticModel.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */