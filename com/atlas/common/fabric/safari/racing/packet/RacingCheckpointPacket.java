/*    */ package com.atlas.common.fabric.safari.racing.packet;
/*    */ 
/*    */ import com.atlas.common.fabric.packet.AtlasModPacket;
/*    */ import net.minecraft.class_2540;
/*    */ import net.minecraft.class_2960;
/*    */ import net.minecraft.class_8710;
/*    */ import net.minecraft.class_9139;
/*    */ import net.minecraft.class_9141;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ public final class RacingCheckpointPacket
/*    */   implements AtlasModPacket
/*    */ {
/*    */   @NotNull
/* 16 */   public static final class_8710.class_9154<RacingCheckpointPacket> PACKET_ID = new class_8710.class_9154(
/* 17 */       class_2960.method_60655("atlas", "racing_checkpoint"));
/*    */ 
/*    */   
/* 20 */   public static final class_9139<class_2540, RacingCheckpointPacket> CODEC = class_9139.method_56438(RacingCheckpointPacket::write, RacingCheckpointPacket::new);
/*    */   
/*    */   private final int checkpointIndex;
/*    */   private final long splitTimeMillis;
/*    */   private final long deltaMillis;
/*    */   private final boolean purpleSector;
/*    */   private final boolean personalBest;
/*    */   
/*    */   public RacingCheckpointPacket(class_2540 buf) {
/* 29 */     this.checkpointIndex = buf.readInt();
/* 30 */     this.splitTimeMillis = buf.readLong();
/* 31 */     this.deltaMillis = buf.readLong();
/* 32 */     this.purpleSector = buf.readBoolean();
/* 33 */     this.personalBest = buf.readBoolean();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public RacingCheckpointPacket(int checkpointIndex, long splitTimeMillis, long deltaMillis, boolean purpleSector, boolean personalBest) {
/* 41 */     this.checkpointIndex = checkpointIndex;
/* 42 */     this.splitTimeMillis = splitTimeMillis;
/* 43 */     this.deltaMillis = deltaMillis;
/* 44 */     this.purpleSector = purpleSector;
/* 45 */     this.personalBest = personalBest;
/*    */   }
/*    */ 
/*    */   
/*    */   public class_8710.class_9154<? extends class_8710> method_56479() {
/* 50 */     return (class_8710.class_9154)PACKET_ID;
/*    */   }
/*    */ 
/*    */   
/*    */   public void write(class_2540 buf) {
/* 55 */     buf.method_53002(this.checkpointIndex);
/* 56 */     buf.method_52974(this.splitTimeMillis);
/* 57 */     buf.method_52974(this.deltaMillis);
/* 58 */     buf.method_52964(this.purpleSector);
/* 59 */     buf.method_52964(this.personalBest);
/*    */   }
/*    */   
/*    */   public int getCheckpointIndex() {
/* 63 */     return this.checkpointIndex;
/*    */   }
/*    */   
/*    */   public long getSplitTimeMillis() {
/* 67 */     return this.splitTimeMillis;
/*    */   }
/*    */   
/*    */   public long getDeltaMillis() {
/* 71 */     return this.deltaMillis;
/*    */   }
/*    */   
/*    */   public boolean isPurpleSector() {
/* 75 */     return this.purpleSector;
/*    */   }
/*    */   
/*    */   public boolean isPersonalBest() {
/* 79 */     return this.personalBest;
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\safari\racing\packet\RacingCheckpointPacket.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */