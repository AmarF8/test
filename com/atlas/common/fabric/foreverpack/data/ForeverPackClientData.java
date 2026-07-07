/*    */ package com.atlas.common.fabric.foreverpack.data;
/*    */ 
/*    */ import com.atlas.common.fabric.foreverpack.network.ForeverPackNetwork;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import net.fabricmc.api.EnvType;
/*    */ import net.fabricmc.api.Environment;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
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
/*    */ @Environment(EnvType.CLIENT)
/*    */ public final class ForeverPackClientData
/*    */ {
/* 26 */   private static final ForeverPackClientData INSTANCE = new ForeverPackClientData();
/*    */   
/*    */   public static ForeverPackClientData getInstance() {
/* 29 */     return INSTANCE;
/*    */   }
/*    */   
/* 32 */   private int currentLadderTier = 0;
/* 33 */   private long cycleEndAt = 0L;
/* 34 */   private String variant = "";
/* 35 */   private List<ForeverPackNetwork.TierEntry> tiers = Collections.emptyList();
/*    */   
/*    */   @Nullable
/*    */   private String lastResultMessage;
/*    */   
/*    */   private boolean lastResultSuccess;
/*    */   private long lastResultAt;
/* 42 */   private long lastSyncAt = 0L;
/*    */ 
/*    */ 
/*    */   
/*    */   public void applySync(@NotNull ForeverPackNetwork.SyncPayload payload) {
/* 47 */     this.currentLadderTier = payload.currentLadderTier();
/* 48 */     this.cycleEndAt = payload.cycleEndAt();
/* 49 */     this.variant = payload.variant();
/* 50 */     this.tiers = new ArrayList<>(payload.tiers());
/* 51 */     this.lastSyncAt = System.currentTimeMillis();
/*    */   }
/*    */   
/*    */   public void applyPurchaseResult(@NotNull ForeverPackNetwork.PurchaseResultPayload payload) {
/* 55 */     this.lastResultSuccess = payload.success();
/* 56 */     this.lastResultMessage = payload.message();
/* 57 */     this.lastResultAt = System.currentTimeMillis();
/*    */ 
/*    */     
/* 60 */     if (payload.success()) ForeverPackNetwork.requestSync(); 
/*    */   }
/*    */   
/* 63 */   public int getCurrentLadderTier() { return this.currentLadderTier; }
/* 64 */   public long getCycleEndAt() { return this.cycleEndAt; } @NotNull
/* 65 */   public String getVariant() { return (this.variant != null) ? this.variant : ""; } @NotNull
/* 66 */   public List<ForeverPackNetwork.TierEntry> getTiers() { return this.tiers; }
/* 67 */   public long getLastSyncAt() { return this.lastSyncAt; }
/*    */   @Nullable
/* 69 */   public String getLastResultMessage() { return this.lastResultMessage; }
/* 70 */   public boolean isLastResultSuccess() { return this.lastResultSuccess; } public long getLastResultAt() {
/* 71 */     return this.lastResultAt;
/*    */   }
/*    */   public void clearLastResult() {
/* 74 */     this.lastResultMessage = null;
/* 75 */     this.lastResultAt = 0L;
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\foreverpack\data\ForeverPackClientData.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */