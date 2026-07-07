/*    */ package com.atlas.common.fabric.rustlingspots.client;
/*    */ 
/*    */ import com.atlas.common.fabric.rustlingspots.RustlingSpotFamily;
/*    */ import com.atlas.common.fabric.rustlingspots.network.RustlingSpotsNetwork;
/*    */ import com.atlas.common.fabric.rustlingspots.particle.RustlingParticles;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import java.util.UUID;
/*    */ import net.fabricmc.api.EnvType;
/*    */ import net.fabricmc.api.Environment;
/*    */ import net.minecraft.class_2338;
/*    */ import net.minecraft.class_310;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Environment(EnvType.CLIENT)
/*    */ public final class RustlingSpotClientHandler
/*    */ {
/* 22 */   private static final Map<UUID, ClientSpot> ACTIVE = new HashMap<>();
/*    */ 
/*    */ 
/*    */   
/*    */   public static void handleSpawn(RustlingSpotsNetwork.SpawnSpotPayload payload) {
/* 27 */     ACTIVE.put(payload.id(), new ClientSpot(payload
/* 28 */           .pos(), 
/* 29 */           RustlingSpotFamily.byOrdinal(payload.familyOrdinal()), payload
/* 30 */           .shiny()));
/*    */   }
/*    */ 
/*    */   
/*    */   public static void handleRemove(UUID id) {
/* 35 */     ACTIVE.remove(id);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void tick() {
/* 42 */     class_310 mc = class_310.method_1551();
/* 43 */     if (mc.field_1687 == null) {
/* 44 */       if (!ACTIVE.isEmpty()) ACTIVE.clear();
/*    */       
/*    */       return;
/*    */     } 
/* 48 */     long gameTime = mc.field_1687.method_8510();
/* 49 */     if (gameTime % 10L != 0L)
/*    */       return; 
/* 51 */     for (ClientSpot spot : ACTIVE.values()) {
/* 52 */       RustlingParticles.spawn(spot.family, spot.pos, spot.shiny);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void clear() {
/* 60 */     ACTIVE.clear();
/*    */   }
/*    */   private static final class ClientSpot extends Record { private final class_2338 pos; private final RustlingSpotFamily family; private final boolean shiny;
/* 63 */     private ClientSpot(class_2338 pos, RustlingSpotFamily family, boolean shiny) { this.pos = pos; this.family = family; this.shiny = shiny; } public final String toString() { // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/rustlingspots/client/RustlingSpotClientHandler$ClientSpot;)Ljava/lang/String;
/*    */       //   6: areturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #63	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/* 63 */       //   0	7	0	this	Lcom/atlas/common/fabric/rustlingspots/client/RustlingSpotClientHandler$ClientSpot; } public class_2338 pos() { return this.pos; } public final int hashCode() { // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/rustlingspots/client/RustlingSpotClientHandler$ClientSpot;)I
/*    */       //   6: ireturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #63	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	7	0	this	Lcom/atlas/common/fabric/rustlingspots/client/RustlingSpotClientHandler$ClientSpot; } public final boolean equals(Object o) { // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: aload_1
/*    */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/rustlingspots/client/RustlingSpotClientHandler$ClientSpot;Ljava/lang/Object;)Z
/*    */       //   7: ireturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #63	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	8	0	this	Lcom/atlas/common/fabric/rustlingspots/client/RustlingSpotClientHandler$ClientSpot;
/* 63 */       //   0	8	1	o	Ljava/lang/Object; } public RustlingSpotFamily family() { return this.family; } public boolean shiny() { return this.shiny; }
/*    */      }
/*    */ 
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\rustlingspots\client\RustlingSpotClientHandler.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */