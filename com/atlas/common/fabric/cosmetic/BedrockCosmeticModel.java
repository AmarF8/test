/*    */ package com.atlas.common.fabric.cosmetic;
/*    */ 
/*    */ import com.google.common.base.Preconditions;
/*    */ import net.minecraft.class_2960;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ public final class BedrockCosmeticModel
/*    */   extends SimplePosableState {
/*    */   @NotNull
/*    */   private final CosmeticType type;
/*    */   @NotNull
/*    */   private final PlayerModelType playerModel;
/*    */   @NotNull
/*    */   private final class_2960 model;
/*    */   @NotNull
/*    */   private final class_2960 texture;
/*    */   @Nullable
/*    */   private final class_2960 animation;
/*    */   @Nullable
/*    */   private final BedrockCosmeticItemRenderingOptions itemRendering;
/*    */   
/*    */   public BedrockCosmeticModel(@NotNull CosmeticType type, @NotNull PlayerModelType playerModel, @NotNull class_2960 model, @NotNull class_2960 texture, @Nullable class_2960 animation, @Nullable BedrockCosmeticItemRenderingOptions itemRendering) {
/* 24 */     Preconditions.checkNotNull(type, "type");
/* 25 */     Preconditions.checkNotNull(playerModel, "playerModel");
/* 26 */     Preconditions.checkNotNull(model, "model");
/* 27 */     Preconditions.checkNotNull(texture, "texture");
/*    */     
/* 29 */     this.type = type;
/* 30 */     this.playerModel = playerModel;
/* 31 */     this.model = model;
/* 32 */     this.texture = texture;
/* 33 */     this.animation = animation;
/* 34 */     this.itemRendering = itemRendering;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public BedrockCosmeticModel(@NotNull CosmeticType type, @NotNull PlayerModelType playerModel, @NotNull class_2960 model, @NotNull class_2960 texture) {
/* 44 */     this(type, playerModel, model, texture, null, null);
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public CosmeticType getType() {
/* 49 */     return this.type;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public PlayerModelType getPlayerModel() {
/* 54 */     return this.playerModel;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public class_2960 getModel() {
/* 59 */     return this.model;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public class_2960 getTexture() {
/* 64 */     return this.texture;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public class_2960 getAnimation() {
/* 69 */     return this.animation;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public BedrockCosmeticItemRenderingOptions getItemRendering() {
/* 74 */     return this.itemRendering;
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\cosmetic\BedrockCosmeticModel.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */