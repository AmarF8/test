/*     */ package com.atlas.common.fabric.cosmetic.packet;
/*     */ import com.atlas.common.fabric.cosmetic.BedrockCosmeticItemRenderingOptions;
/*     */ import com.atlas.common.fabric.cosmetic.BedrockCosmeticModel;
/*     */ import com.atlas.common.fabric.cosmetic.Cosmetic;
/*     */ import com.atlas.common.fabric.cosmetic.CosmeticCategory;
/*     */ import com.atlas.common.fabric.cosmetic.CosmeticRepository;
/*     */ import com.atlas.common.fabric.cosmetic.CosmeticType;
/*     */ import com.atlas.common.fabric.cosmetic.CosmeticVariant;
/*     */ import com.atlas.common.fabric.cosmetic.CustomModelDataCosmeticModel;
/*     */ import com.atlas.common.fabric.cosmetic.EquippedCosmetics;
/*     */ import com.atlas.common.fabric.cosmetic.PlayerModelType;
/*     */ import com.atlas.common.fabric.utility.Either;
/*     */ import com.cobblemon.mod.common.util.BufferUtilsKt;
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import net.minecraft.class_2540;
/*     */ import net.minecraft.class_2960;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.joml.Vector3f;
/*     */ 
/*     */ public final class CosmeticDecoders {
/*     */   @NotNull
/*     */   public static CustomModelDataCosmeticModel decodeCustomDataModel(@NotNull class_2540 buffer) {
/*  25 */     Objects.requireNonNull(buffer, "buffer");
/*  26 */     CosmeticType type = CosmeticType.values()[buffer.readInt()];
/*  27 */     int customModelData = buffer.readInt();
/*  28 */     int customItemModel = buffer.readInt();
/*     */     
/*  30 */     return new CustomModelDataCosmeticModel(type, customModelData, customItemModel);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static BedrockCosmeticModel decodeBedrockModel(@NotNull class_2540 buffer) {
/*  35 */     Objects.requireNonNull(buffer, "buffer");
/*     */     
/*  37 */     CosmeticType type = CosmeticType.values()[buffer.readInt()];
/*  38 */     PlayerModelType playerModelType = PlayerModelType.values()[buffer.readInt()];
/*     */     
/*  40 */     class_2960 modelLocation = buffer.method_10810();
/*  41 */     class_2960 textureLocation = buffer.method_10810();
/*     */     
/*  43 */     class_2960 geoLocation = (class_2960)buffer.method_43827(class_2540::method_10810);
/*  44 */     BedrockCosmeticItemRenderingOptions renderingOptions = (BedrockCosmeticItemRenderingOptions)buffer.method_43827(CosmeticDecoders::decodeBedrockItemRenderingOptions);
/*     */     
/*  46 */     return new BedrockCosmeticModel(type, playerModelType, modelLocation, textureLocation, geoLocation, renderingOptions);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static BedrockCosmeticItemRenderingOptions decodeBedrockItemRenderingOptions(@NotNull class_2540 buffer) {
/*  51 */     Objects.requireNonNull(buffer, "buffer");
/*     */     
/*  53 */     Vector3f translation = buffer.method_49069();
/*  54 */     Vector3f rotation = buffer.method_49069();
/*  55 */     Vector3f scale = buffer.method_49069();
/*  56 */     boolean runAnimation = buffer.readBoolean();
/*     */     
/*  58 */     float[] translationArray = { translation.x(), translation.y(), translation.z() };
/*  59 */     float[] rotationArray = { rotation.x(), rotation.y(), rotation.z() };
/*  60 */     float[] scaleArray = { scale.x(), scale.y(), scale.z() };
/*     */     
/*  62 */     return new BedrockCosmeticItemRenderingOptions(translationArray, rotationArray, scaleArray, runAnimation);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static Either<BedrockCosmeticModel, CustomModelDataCosmeticModel> decodeCosmeticModel(@NotNull class_2540 buffer) {
/*  67 */     Objects.requireNonNull(buffer, "buffer");
/*  68 */     int type = buffer.readInt();
/*     */     
/*  70 */     switch (type) {
/*     */       case 0:
/*     */       
/*     */       case 1:
/*     */       
/*  75 */     }  throw new IllegalArgumentException("Unknown cosmetic model type: " + type);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static Cosmetic decodeCosmetic(@NotNull class_2540 buffer) {
/*  81 */     Objects.requireNonNull(buffer, "buffer");
/*     */     
/*  83 */     String id = BufferUtilsKt.readString((ByteBuf)buffer);
/*  84 */     String name = BufferUtilsKt.readString((ByteBuf)buffer);
/*  85 */     CosmeticCategory category = CosmeticCategory.values()[buffer.readInt()];
/*  86 */     boolean isExclusive = buffer.readBoolean();
/*  87 */     Either<BedrockCosmeticModel, CustomModelDataCosmeticModel> model = decodeCosmeticModel(buffer);
/*     */     
/*  89 */     List<Either<BedrockCosmeticModel, CustomModelDataCosmeticModel>> dependencies = buffer.method_34066(CosmeticDecoders::decodeCosmeticModel);
/*  90 */     List<CosmeticVariant> variants = buffer.method_34066(CosmeticDecoders::decodeCosmeticVariant);
/*     */     
/*  92 */     Integer optionalInt1 = (Integer)buffer.method_43827(class_2540::readInt);
/*     */     
/*  94 */     return new Cosmetic(id, name, category, isExclusive, model, dependencies, variants, optionalInt1);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static CosmeticVariant decodeCosmeticVariant(@NotNull class_2540 buffer) {
/*  99 */     Objects.requireNonNull(buffer, "buffer");
/*     */     
/* 101 */     String id = BufferUtilsKt.readString((ByteBuf)buffer);
/* 102 */     String name = BufferUtilsKt.readString((ByteBuf)buffer);
/* 103 */     boolean isExclusive = buffer.readBoolean();
/* 104 */     Either<BedrockCosmeticModel, CustomModelDataCosmeticModel> model = decodeCosmeticModel(buffer);
/*     */     
/* 106 */     List<Either<BedrockCosmeticModel, CustomModelDataCosmeticModel>> dependencies = buffer.method_34066(CosmeticDecoders::decodeCosmeticModel);
/* 107 */     Integer optionalInt = (Integer)buffer.method_43827(class_2540::readInt);
/*     */     
/* 109 */     return new CosmeticVariant(id, name, isExclusive, model, dependencies, optionalInt);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static EquippedCosmetics decodeEquippedCosmetics(@NotNull class_2540 buffer) {
/* 114 */     Objects.requireNonNull(buffer, "buffer");
/*     */     
/* 116 */     UUID playerUUID = buffer.method_10790();
/* 117 */     EquippedCosmetics equippedCosmetics = new EquippedCosmetics(playerUUID);
/*     */     
/* 119 */     buffer.method_34066(buf -> {
/*     */           String cosmeticId = BufferUtilsKt.readString((ByteBuf)buf);
/*     */           
/*     */           String variantId = (String)buf.method_43827(BufferUtilsKt::readString);
/*     */           
/*     */           Cosmetic cosmetic = CosmeticRepository.INSTANCE.get(cosmeticId);
/*     */           
/*     */           if (cosmetic != null) {
/*     */             CosmeticVariant variant = null;
/*     */             
/*     */             if (variantId != null && cosmetic.getVariants() != null) {
/*     */               variant = cosmetic.getVariants().stream().filter(()).findFirst().orElse(null);
/*     */             }
/*     */             
/*     */             equippedCosmetics.equip(cosmetic, variant);
/*     */           } 
/*     */           
/*     */           return null;
/*     */         });
/* 138 */     List<String> disabledSlots = buffer.method_34066(BufferUtilsKt::readString);
/*     */ 
/*     */ 
/*     */     
/* 142 */     Objects.requireNonNull(equippedCosmetics); disabledSlots.stream().map(ArmorSlot::valueOf).forEach(equippedCosmetics::disableSlot);
/*     */     
/* 144 */     return equippedCosmetics;
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\cosmetic\packet\CosmeticDecoders.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */