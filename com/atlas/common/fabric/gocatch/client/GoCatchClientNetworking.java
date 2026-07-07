/*    */ package com.atlas.common.fabric.gocatch.client;
/*    */ 
/*    */ import com.atlas.common.fabric.gocatch.GoCatchThrowQuality;
/*    */ import com.atlas.common.fabric.gocatch.network.GoCatchNetwork;
/*    */ import java.util.UUID;
/*    */ import net.fabricmc.api.EnvType;
/*    */ import net.fabricmc.api.Environment;
/*    */ import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
/*    */ import net.minecraft.class_8710;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Environment(EnvType.CLIENT)
/*    */ public final class GoCatchClientNetworking
/*    */ {
/*    */   public static void sendClientThrowQuality(UUID pokemonUuid, GoCatchThrowQuality quality, boolean isCurveBall) {
/* 20 */     if (pokemonUuid == null || quality == null)
/* 21 */       return;  ClientPlayNetworking.send((class_8710)new GoCatchNetwork.ClientThrowPayload(quality.name(), pokemonUuid, isCurveBall));
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\gocatch\client\GoCatchClientNetworking.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */