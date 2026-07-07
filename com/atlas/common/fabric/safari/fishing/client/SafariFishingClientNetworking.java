/*    */ package com.atlas.common.fabric.safari.fishing.client;
/*    */ 
/*    */ import com.atlas.common.fabric.safari.fishing.SafariFishingNetwork;
/*    */ import net.fabricmc.api.EnvType;
/*    */ import net.fabricmc.api.Environment;
/*    */ import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
/*    */ import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
/*    */ import net.minecraft.class_310;
/*    */ import net.minecraft.class_8710;
/*    */ 
/*    */ 
/*    */ @Environment(EnvType.CLIENT)
/*    */ public final class SafariFishingClientNetworking
/*    */ {
/*    */   private static boolean lastAttackPressed;
/*    */   
/*    */   public static void register() {
/* 18 */     ClientTickEvents.END_CLIENT_TICK.register(SafariFishingClientNetworking::tick);
/*    */   }
/*    */   
/*    */   private static void tick(class_310 client) {
/* 22 */     SafariFishingHudState state = SafariFishingHudState.getInstance();
/* 23 */     if (!state.isActive()) {
/* 24 */       lastAttackPressed = false;
/*    */       return;
/*    */     } 
/* 27 */     if (client.field_1724 == null)
/*    */       return; 
/* 29 */     boolean attackPressed = client.field_1690.field_1886.method_1434();
/* 30 */     boolean pump = (attackPressed && !lastAttackPressed);
/* 31 */     lastAttackPressed = attackPressed;
/* 32 */     boolean hooked = "HOOKED".equals(state.getPhase());
/*    */     
/* 34 */     ClientPlayNetworking.send((class_8710)new SafariFishingNetwork.InputPayload((hooked && client.field_1690.field_1904
/* 35 */           .method_1434()), (hooked && client.field_1690.field_1832
/* 36 */           .method_1434()), (hooked && pump)));
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\safari\fishing\client\SafariFishingClientNetworking.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */