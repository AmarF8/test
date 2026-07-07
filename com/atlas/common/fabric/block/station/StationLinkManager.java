/*    */ package com.atlas.common.fabric.block.station;
/*    */ 
/*    */ import com.atlas.common.fabric.block.station.packet.client.StationClosePacket;
/*    */ import com.atlas.common.fabric.packet.AtlasModPacket;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import java.util.UUID;
/*    */ import net.minecraft.class_2338;
/*    */ import net.minecraft.class_2374;
/*    */ import net.minecraft.class_3218;
/*    */ import net.minecraft.class_3222;
/*    */ import net.minecraft.class_8710;
/*    */ 
/*    */ public class StationLinkManager {
/* 15 */   public static final Map<UUID, StationLink> links = new HashMap<>();
/*    */   
/*    */   public static StationLink getLinkByPlayerId(UUID playerId) {
/* 18 */     return links.get(playerId);
/*    */   }
/*    */   
/*    */   public static void createLink(UUID playerId, StationLink link) {
/* 22 */     links.put(playerId, link);
/*    */   }
/*    */   
/*    */   public static StationLink getLinkByPlayer(class_3222 player) {
/* 26 */     StationLink link = links.get(player.method_5667());
/* 27 */     if (link != null) {
/* 28 */       boolean sameDimension = player.method_37908().method_27983().method_29177().equals(link.getDimension());
/*    */       
/* 30 */       if (!sameDimension || !link.getPos().method_19769((class_2374)player.method_19538(), 10.0D)) {
/* 31 */         links.remove(player.method_5667());
/* 32 */         return null;
/*    */       } 
/*    */     } 
/* 35 */     return link;
/*    */   }
/*    */   
/*    */   public static void removeAt(class_3218 world, class_2338 pos) {
/* 39 */     links.entrySet().removeIf(entry -> {
/*    */           StationLink stationLink = (StationLink)entry.getValue();
/*    */           boolean isSameWorld = world.method_27983().method_29177().equals(stationLink.getDimension());
/* 42 */           boolean shouldRemove = (isSameWorld && stationLink.getPos().equals(pos));
/*    */           if (shouldRemove) {
/*    */             class_3222 player = world.method_8503().method_3760().method_14602((UUID)entry.getKey());
/*    */             if (player != null)
/*    */               AtlasModPacket.sendToClient(player, (class_8710)new StationClosePacket()); 
/*    */           } 
/*    */           return shouldRemove;
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\block\station\StationLinkManager.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */