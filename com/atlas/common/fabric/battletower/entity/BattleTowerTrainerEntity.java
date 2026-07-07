/*     */ package com.atlas.common.fabric.battletower.entity;
/*     */ 
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import com.mojang.authlib.properties.Property;
/*     */ import java.util.Optional;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.class_1297;
/*     */ import net.minecraft.class_1299;
/*     */ import net.minecraft.class_1937;
/*     */ import net.minecraft.class_2487;
/*     */ import net.minecraft.class_2561;
/*     */ import net.minecraft.class_2940;
/*     */ import net.minecraft.class_2943;
/*     */ import net.minecraft.class_2945;
/*     */ import net.minecraft.class_3222;
/*     */ import net.minecraft.class_4048;
/*     */ import net.minecraft.class_4050;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BattleTowerTrainerEntity
/*     */   extends class_1297
/*     */ {
/*  30 */   private static final class_2940<String> TRAINER_NAME = class_2945.method_12791(BattleTowerTrainerEntity.class, class_2943.field_13326);
/*  31 */   private static final class_2940<Float> ENTITY_YAW = class_2945.method_12791(BattleTowerTrainerEntity.class, class_2943.field_13320);
/*  32 */   private static final class_2940<String> CUSTOM_SKIN = class_2945.method_12791(BattleTowerTrainerEntity.class, class_2943.field_13326);
/*  33 */   private static final class_2940<String> OWNER_TEXTURE_VALUE = class_2945.method_12791(BattleTowerTrainerEntity.class, class_2943.field_13326);
/*  34 */   private static final class_2940<String> OWNER_TEXTURE_SIGNATURE = class_2945.method_12791(BattleTowerTrainerEntity.class, class_2943.field_13326);
/*  35 */   private static final class_2940<Optional<UUID>> OWNER_UUID = class_2945.method_12791(BattleTowerTrainerEntity.class, class_2943.field_13313);
/*     */ 
/*     */   
/*     */   public BattleTowerTrainerEntity(class_1299<?> type, class_1937 world) {
/*  39 */     super(type, world);
/*  40 */     this.field_5985 = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void method_5693(class_2945.class_9222 builder) {
/*  47 */     builder.method_56912(TRAINER_NAME, "Trainer");
/*  48 */     builder.method_56912(ENTITY_YAW, Float.valueOf(0.0F));
/*  49 */     builder.method_56912(CUSTOM_SKIN, "");
/*  50 */     builder.method_56912(OWNER_TEXTURE_VALUE, "");
/*  51 */     builder.method_56912(OWNER_TEXTURE_SIGNATURE, "");
/*  52 */     builder.method_56912(OWNER_UUID, Optional.empty());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public class_4048 method_18377(class_4050 pose) {
/*  59 */     return class_4048.method_18384(0.6F, 1.8F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTrainerName(String name) {
/*  65 */     this.field_6011.method_12778(TRAINER_NAME, name);
/*     */   }
/*     */   
/*     */   public String getTrainerName() {
/*  69 */     return (String)this.field_6011.method_12789(TRAINER_NAME);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCustomSkin(String skinName) {
/*  75 */     this.field_6011.method_12778(CUSTOM_SKIN, (skinName != null) ? skinName : "");
/*     */   }
/*     */   
/*     */   public String getCustomSkin() {
/*  79 */     String skin = (String)this.field_6011.method_12789(CUSTOM_SKIN);
/*  80 */     return (skin != null && !skin.isEmpty()) ? skin : null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEntityYaw(float yaw) {
/*  86 */     this.field_6011.method_12778(ENTITY_YAW, Float.valueOf(yaw));
/*  87 */     applyYaw(yaw);
/*     */   }
/*     */   
/*     */   private void applyYaw(float yaw) {
/*  91 */     method_36456(yaw);
/*  92 */     method_5636(yaw);
/*  93 */     method_5847(yaw);
/*  94 */     this.field_5982 = yaw;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void cacheOwnerTextureProperty(class_3222 owner) {
/*     */     try {
/* 101 */       GameProfile profile = owner.method_7334();
/* 102 */       if (profile == null) {
/*     */         return;
/*     */       }
/* 105 */       Property textures = profile.getProperties().get("textures").stream().findFirst().orElse(null);
/* 106 */       if (textures == null)
/*     */         return; 
/* 108 */       String value = textures.value();
/* 109 */       if (value != null && !value.isBlank()) {
/* 110 */         this.field_6011.method_12778(OWNER_TEXTURE_VALUE, value);
/*     */       }
/*     */       
/* 113 */       String sig = textures.signature();
/* 114 */       if (sig != null && !sig.isBlank()) {
/* 115 */         this.field_6011.method_12778(OWNER_TEXTURE_SIGNATURE, sig);
/*     */       }
/*     */       
/* 118 */       this.field_6011.method_12778(OWNER_UUID, Optional.of(owner.method_5667()));
/* 119 */     } catch (Throwable throwable) {}
/*     */   }
/*     */   
/*     */   public String getOwnerTextureValue() {
/* 123 */     return (String)this.field_6011.method_12789(OWNER_TEXTURE_VALUE);
/*     */   }
/*     */   
/*     */   public String getOwnerTextureSignature() {
/* 127 */     return (String)this.field_6011.method_12789(OWNER_TEXTURE_SIGNATURE);
/*     */   }
/*     */   
/*     */   public UUID getOwnerUUID() {
/* 131 */     return ((Optional<UUID>)this.field_6011.method_12789(OWNER_UUID)).orElse(null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GameProfile buildCachedOwnerProfile() {
/* 139 */     String value = getOwnerTextureValue();
/* 140 */     if (value == null || value.isBlank()) return null;
/*     */     
/*     */     try {
/* 143 */       UUID id = getOwnerUUID();
/* 144 */       String name = getTrainerName();
/*     */ 
/*     */       
/* 147 */       GameProfile profile = new GameProfile((id != null) ? id : new UUID(0L, 0L), (name != null) ? name : "");
/*     */ 
/*     */       
/* 150 */       String sig = getOwnerTextureSignature();
/* 151 */       if (sig != null && !sig.isBlank()) {
/* 152 */         profile.getProperties().put("textures", new Property("textures", value, sig));
/*     */       } else {
/* 154 */         profile.getProperties().put("textures", new Property("textures", value));
/*     */       } 
/*     */       
/* 157 */       return profile;
/* 158 */     } catch (Throwable e) {
/* 159 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void method_5773() {
/* 167 */     super.method_5773();
/*     */     try {
/* 169 */       float yaw = ((Float)this.field_6011.method_12789(ENTITY_YAW)).floatValue();
/* 170 */       if (Float.isFinite(yaw)) {
/* 171 */         applyYaw(yaw);
/*     */       }
/* 173 */     } catch (Throwable throwable) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void method_5749(class_2487 tag) {
/* 180 */     if (tag.method_10545("TrainerName")) {
/* 181 */       this.field_6011.method_12778(TRAINER_NAME, tag.method_10558("TrainerName"));
/*     */     }
/* 183 */     if (tag.method_10545("EntityYaw")) {
/* 184 */       float yaw = tag.method_10583("EntityYaw");
/* 185 */       this.field_6011.method_12778(ENTITY_YAW, Float.valueOf(yaw));
/* 186 */       applyYaw(yaw);
/*     */     } 
/* 188 */     if (tag.method_10545("CustomSkin")) {
/* 189 */       this.field_6011.method_12778(CUSTOM_SKIN, tag.method_10558("CustomSkin"));
/*     */     }
/* 191 */     if (tag.method_10545("OwnerTextureValue")) {
/* 192 */       this.field_6011.method_12778(OWNER_TEXTURE_VALUE, tag.method_10558("OwnerTextureValue"));
/*     */     }
/* 194 */     if (tag.method_10545("OwnerTextureSignature")) {
/* 195 */       this.field_6011.method_12778(OWNER_TEXTURE_SIGNATURE, tag.method_10558("OwnerTextureSignature"));
/*     */     }
/* 197 */     if (tag.method_10545("OwnerUUID")) {
/* 198 */       this.field_6011.method_12778(OWNER_UUID, Optional.of(tag.method_25926("OwnerUUID")));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void method_5652(class_2487 tag) {
/* 204 */     tag.method_10582("TrainerName", getTrainerName());
/* 205 */     tag.method_10548("EntityYaw", ((Float)this.field_6011.method_12789(ENTITY_YAW)).floatValue());
/*     */     
/* 207 */     String customSkin = getCustomSkin();
/* 208 */     if (customSkin != null) {
/* 209 */       tag.method_10582("CustomSkin", customSkin);
/*     */     }
/*     */     
/* 212 */     String textureValue = (String)this.field_6011.method_12789(OWNER_TEXTURE_VALUE);
/* 213 */     if (textureValue != null && !textureValue.isBlank()) {
/* 214 */       tag.method_10582("OwnerTextureValue", textureValue);
/*     */     }
/*     */     
/* 217 */     String textureSig = (String)this.field_6011.method_12789(OWNER_TEXTURE_SIGNATURE);
/* 218 */     if (textureSig != null && !textureSig.isBlank()) {
/* 219 */       tag.method_10582("OwnerTextureSignature", textureSig);
/*     */     }
/*     */     
/* 222 */     UUID ownerUUID = getOwnerUUID();
/* 223 */     if (ownerUUID != null) {
/* 224 */       tag.method_25927("OwnerUUID", ownerUUID);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean method_5863() {
/* 232 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean method_30948() {
/* 237 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public class_2561 method_5477() {
/* 242 */     return (class_2561)class_2561.method_43470(getTrainerName());
/*     */   }
/*     */ 
/*     */   
/*     */   public class_2561 method_5476() {
/* 247 */     return (class_2561)class_2561.method_43470(getTrainerName());
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\battletower\entity\BattleTowerTrainerEntity.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */