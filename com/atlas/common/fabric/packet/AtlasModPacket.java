/*    */ package com.atlas.common.fabric.packet;
/*    */ 
/*    */ import net.fabricmc.api.EnvType;
/*    */ import net.fabricmc.api.Environment;
/*    */ import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
/*    */ import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
/*    */ import net.minecraft.class_2338;
/*    */ import net.minecraft.class_2382;
/*    */ import net.minecraft.class_2540;
/*    */ import net.minecraft.class_310;
/*    */ import net.minecraft.class_3218;
/*    */ import net.minecraft.class_3222;
/*    */ import net.minecraft.class_8710;
/*    */ import org.jetbrains.annotations.NotNull;
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
/*    */ public interface AtlasModPacket
/*    */   extends class_8710
/*    */ {
/*    */   void write(class_2540 paramclass_2540);
/*    */   
/*    */   @Environment(EnvType.CLIENT)
/*    */   static void sendToServer(@NotNull class_8710 packet) {
/* 35 */     if (class_310.method_1551() == null) {
/* 36 */       throw new IllegalStateException("You cannot send a packet to the server on the server side.");
/*    */     }
/*    */     
/* 39 */     ClientPlayNetworking.send(packet);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Environment(EnvType.SERVER)
/*    */   static void sendToClient(@NotNull class_3222 player, @NotNull class_8710 packet) {
/* 51 */     ServerPlayNetworking.send(player, packet);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Environment(EnvType.SERVER)
/*    */   static void sendAround(@NotNull class_3218 world, @NotNull class_2338 blockPos, int radius, @NotNull AtlasModPacket packet) {
/* 63 */     world.method_18456().forEach(player -> {
/*    */           if (player.method_24515().method_19771((class_2382)blockPos, radius))
/*    */             sendToClient(player, packet); 
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\packet\AtlasModPacket.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */