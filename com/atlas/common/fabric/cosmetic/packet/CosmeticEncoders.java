/*     */ package com.atlas.common.fabric.cosmetic.packet;
/*     */ 
/*     */ import com.atlas.common.fabric.cosmetic.BedrockCosmeticItemRenderingOptions;
/*     */ import com.atlas.common.fabric.cosmetic.BedrockCosmeticModel;
/*     */ import com.atlas.common.fabric.cosmetic.Cosmetic;
/*     */ import com.atlas.common.fabric.cosmetic.CosmeticVariant;
/*     */ import com.atlas.common.fabric.cosmetic.CustomModelDataCosmeticModel;
/*     */ import com.atlas.common.fabric.cosmetic.EquippedCosmetic;
/*     */ import com.atlas.common.fabric.cosmetic.EquippedCosmetics;
/*     */ import com.atlas.common.fabric.utility.Either;
/*     */ import com.cobblemon.mod.common.util.BufferUtilsKt;
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import net.minecraft.class_2540;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.joml.Vector3f;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class CosmeticEncoders
/*     */ {
/*     */   public static void encodeCustomDataModel(@NotNull class_2540 buffer, @NotNull CustomModelDataCosmeticModel model) {
/*  29 */     Objects.requireNonNull(buffer, "buffer cannot be null");
/*  30 */     Objects.requireNonNull(model, "model cannot be null");
/*     */     
/*  32 */     buffer.method_53002(model.getType().ordinal());
/*  33 */     buffer.method_53002(model.getSteveModelId());
/*  34 */     buffer.method_53002(model.getAlexModelId());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void encodeBedrockModel(@NotNull class_2540 buffer, @NotNull BedrockCosmeticModel model) {
/*  44 */     Objects.requireNonNull(buffer, "buffer cannot be null");
/*  45 */     Objects.requireNonNull(model, "model cannot be null");
/*     */     
/*  47 */     buffer.method_53002(model.getType().ordinal());
/*  48 */     buffer.method_53002(model.getPlayerModel().ordinal());
/*     */     
/*  50 */     buffer.method_10812(model.getModel());
/*  51 */     buffer.method_10812(model.getTexture());
/*     */     
/*  53 */     buffer.method_43826(model.getAnimation(), class_2540::method_10812);
/*  54 */     buffer.method_43826(model.getItemRendering(), CosmeticEncoders::encodeBedrockItemRenderingOptions);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void encodeBedrockItemRenderingOptions(@NotNull class_2540 buffer, @NotNull BedrockCosmeticItemRenderingOptions options) {
/*  65 */     Objects.requireNonNull(buffer, "buffer cannot be null");
/*  66 */     Objects.requireNonNull(options, "options cannot be null");
/*     */     
/*  68 */     buffer.method_49068(new Vector3f(options.getTranslation()[0], options.getTranslation()[1], options.getTranslation()[2]));
/*  69 */     buffer.method_49068(new Vector3f(options.getRotation()[0], options.getRotation()[1], options.getRotation()[2]));
/*  70 */     buffer.method_49068(new Vector3f(options.getScale()[0], options.getScale()[1], options.getScale()[2]));
/*  71 */     buffer.method_52964(options.isRunAnimation());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void encodeCosmeticModel(@NotNull class_2540 buffer, @NotNull Either<BedrockCosmeticModel, CustomModelDataCosmeticModel> model) {
/*  82 */     Objects.requireNonNull(buffer, "buffer cannot be null");
/*  83 */     Objects.requireNonNull(model, "model cannot be null");
/*     */     
/*  85 */     model.fold(bedrockModel -> {
/*     */           buffer.method_53002(0);
/*     */           encodeBedrockModel(buffer, bedrockModel);
/*     */           return null;
/*     */         }customModelDataModel -> {
/*     */           buffer.method_53002(1);
/*     */           encodeCustomDataModel(buffer, customModelDataModel);
/*     */           return null;
/*     */         });
/*     */   }
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
/*     */   public static void encodeCosmetic(@NotNull class_2540 buffer, @NotNull Cosmetic cosmetic) {
/* 106 */     Objects.requireNonNull(buffer, "buffer cannot be null");
/* 107 */     Objects.requireNonNull(cosmetic, "cosmetic cannot be null");
/*     */     
/* 109 */     BufferUtilsKt.writeString((ByteBuf)buffer, cosmetic.getId());
/* 110 */     BufferUtilsKt.writeString((ByteBuf)buffer, cosmetic.getName());
/* 111 */     buffer.method_53002(cosmetic.getCategory().ordinal());
/* 112 */     buffer.method_52964(cosmetic.isPurchaseable());
/* 113 */     encodeCosmeticModel(buffer, cosmetic.getDisplayModel());
/*     */     
/* 115 */     buffer.method_34062(cosmetic.getModels(), CosmeticEncoders::encodeCosmeticModel);
/* 116 */     buffer.method_34062((cosmetic.getVariants() != null) ? cosmetic.getVariants() : List.of(), CosmeticEncoders::encodeCosmeticVariant);
/* 117 */     buffer.method_43826(cosmetic.getPrice(), class_2540::method_53002);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void encodeCosmeticVariant(@NotNull class_2540 buffer, @NotNull CosmeticVariant variant) {
/* 127 */     Objects.requireNonNull(buffer, "buffer cannot be null");
/* 128 */     Objects.requireNonNull(variant, "variant cannot be null");
/*     */     
/* 130 */     BufferUtilsKt.writeString((ByteBuf)buffer, variant.getId());
/* 131 */     BufferUtilsKt.writeString((ByteBuf)buffer, variant.getName());
/* 132 */     buffer.method_52964(variant.isPurchaseable());
/* 133 */     encodeCosmeticModel(buffer, variant.getDisplayModel());
/*     */     
/* 135 */     buffer.method_34062(variant.getModels(), CosmeticEncoders::encodeCosmeticModel);
/* 136 */     buffer.method_43826(variant.getPrice(), class_2540::method_53002);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void encodeEquippedCosmetics(@NotNull class_2540 buffer, @NotNull EquippedCosmetics equipped) {
/* 146 */     Objects.requireNonNull(buffer, "buffer cannot be null");
/* 147 */     Objects.requireNonNull(equipped, "equipped cannot be null");
/*     */     
/* 149 */     buffer.method_10797(equipped.getPlayer());
/*     */     
/* 151 */     buffer.method_34062(equipped.allEquippedCosmetics(), (buf, equippedCosmetic) -> {
/*     */           BufferUtilsKt.writeString((ByteBuf)buf, equippedCosmetic.getCosmetic().getId());
/*     */ 
/*     */           
/*     */           CosmeticVariant variant = equippedCosmetic.getVariant();
/*     */ 
/*     */           
/*     */           buf.method_43826(variant, ());
/*     */         });
/*     */ 
/*     */     
/* 162 */     List<String> disabledSlots = equipped.allDisabledSlots().stream().map(Enum::name).toList();
/*     */     
/* 164 */     buffer.method_34062(disabledSlots, BufferUtilsKt::writeString);
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\cosmetic\packet\CosmeticEncoders.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */