/*     */ package com.atlas.common.fabric.safari.racing.packet;
/*     */ 
/*     */ import com.atlas.common.fabric.packet.AtlasModPacket;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.class_2540;
/*     */ import net.minecraft.class_2960;
/*     */ import net.minecraft.class_8710;
/*     */ import net.minecraft.class_9139;
/*     */ import net.minecraft.class_9141;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class RacingTimingPacket
/*     */   implements AtlasModPacket
/*     */ {
/*     */   @NotNull
/*  19 */   public static final class_8710.class_9154<RacingTimingPacket> PACKET_ID = new class_8710.class_9154(
/*  20 */       class_2960.method_60655("atlas", "racing_timing"));
/*     */ 
/*     */   
/*  23 */   public static final class_9139<class_2540, RacingTimingPacket> CODEC = class_9139.method_56438(RacingTimingPacket::write, RacingTimingPacket::new);
/*     */   
/*     */   private final boolean active;
/*     */   
/*     */   private final boolean waitingForStart;
/*     */   private final long currentLapTimeMillis;
/*     */   private final long personalBestMillis;
/*     */   private final long eventRemainingMillis;
/*     */   private final int currentCheckpoint;
/*     */   private final int totalCheckpoints;
/*     */   private final int playerPosition;
/*     */   private final int lapCount;
/*     */   private final List<CheckpointView> checkpoints;
/*     */   private final List<String> leaderboardNames;
/*     */   private final List<Long> leaderboardTimes;
/*     */   private final String playerName;
/*     */   
/*     */   public RacingTimingPacket(class_2540 buf) {
/*  41 */     this.active = buf.readBoolean();
/*  42 */     this.waitingForStart = buf.readBoolean();
/*  43 */     this.currentLapTimeMillis = buf.readLong();
/*  44 */     this.personalBestMillis = buf.readLong();
/*  45 */     this.eventRemainingMillis = buf.readLong();
/*  46 */     this.currentCheckpoint = buf.readInt();
/*  47 */     this.totalCheckpoints = buf.readInt();
/*  48 */     this.playerPosition = buf.readInt();
/*  49 */     this.lapCount = buf.readInt();
/*  50 */     int checkpointSize = buf.readInt();
/*  51 */     this.checkpoints = new ArrayList<>(checkpointSize);
/*  52 */     for (int i = 0; i < checkpointSize; i++) {
/*  53 */       this.checkpoints.add(new CheckpointView(buf
/*  54 */             .readDouble(), buf
/*  55 */             .readDouble(), buf
/*  56 */             .readDouble(), buf
/*  57 */             .readDouble()));
/*     */     }
/*     */     
/*  60 */     this.playerName = buf.method_19772();
/*  61 */     int lbSize = buf.readInt();
/*  62 */     this.leaderboardNames = new ArrayList<>(lbSize);
/*  63 */     this.leaderboardTimes = new ArrayList<>(lbSize);
/*  64 */     for (int j = 0; j < lbSize; j++) {
/*  65 */       this.leaderboardNames.add(buf.method_19772());
/*  66 */       this.leaderboardTimes.add(Long.valueOf(buf.readLong()));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RacingTimingPacket(boolean active, boolean waitingForStart, long currentLapTimeMillis, long personalBestMillis, long eventRemainingMillis, int currentCheckpoint, int totalCheckpoints, int playerPosition, int lapCount, @NotNull List<CheckpointView> checkpoints, @NotNull String playerName, @NotNull List<String> leaderboardNames, @NotNull List<Long> leaderboardTimes) {
/*  78 */     this.active = active;
/*  79 */     this.waitingForStart = waitingForStart;
/*  80 */     this.currentLapTimeMillis = currentLapTimeMillis;
/*  81 */     this.personalBestMillis = personalBestMillis;
/*  82 */     this.eventRemainingMillis = eventRemainingMillis;
/*  83 */     this.currentCheckpoint = currentCheckpoint;
/*  84 */     this.totalCheckpoints = totalCheckpoints;
/*  85 */     this.playerPosition = playerPosition;
/*  86 */     this.lapCount = lapCount;
/*  87 */     this.checkpoints = checkpoints;
/*  88 */     this.playerName = playerName;
/*  89 */     this.leaderboardNames = leaderboardNames;
/*  90 */     this.leaderboardTimes = leaderboardTimes;
/*     */   }
/*     */   
/*     */   public class_8710.class_9154<? extends class_8710> method_56479() {
/*  94 */     return (class_8710.class_9154)PACKET_ID;
/*     */   }
/*     */   
/*     */   public void write(class_2540 buf) {
/*  98 */     buf.method_52964(this.active);
/*  99 */     buf.method_52964(this.waitingForStart);
/* 100 */     buf.method_52974(this.currentLapTimeMillis);
/* 101 */     buf.method_52974(this.personalBestMillis);
/* 102 */     buf.method_52974(this.eventRemainingMillis);
/* 103 */     buf.method_53002(this.currentCheckpoint);
/* 104 */     buf.method_53002(this.totalCheckpoints);
/* 105 */     buf.method_53002(this.playerPosition);
/* 106 */     buf.method_53002(this.lapCount);
/* 107 */     buf.method_53002(this.checkpoints.size());
/* 108 */     for (CheckpointView checkpoint : this.checkpoints) {
/* 109 */       buf.method_52940(checkpoint.x());
/* 110 */       buf.method_52940(checkpoint.y());
/* 111 */       buf.method_52940(checkpoint.z());
/* 112 */       buf.method_52940(checkpoint.radius());
/*     */     } 
/* 114 */     buf.method_10814(this.playerName);
/* 115 */     buf.method_53002(this.leaderboardNames.size());
/* 116 */     for (int i = 0; i < this.leaderboardNames.size(); i++) {
/* 117 */       buf.method_10814(this.leaderboardNames.get(i));
/* 118 */       buf.method_52974(((Long)this.leaderboardTimes.get(i)).longValue());
/*     */     } 
/*     */   }
/*     */   
/* 122 */   public boolean isActive() { return this.active; }
/* 123 */   public boolean isWaitingForStart() { return this.waitingForStart; }
/* 124 */   public long getCurrentLapTimeMillis() { return this.currentLapTimeMillis; }
/* 125 */   public long getPersonalBestMillis() { return this.personalBestMillis; }
/* 126 */   public long getEventRemainingMillis() { return this.eventRemainingMillis; }
/* 127 */   public int getCurrentCheckpoint() { return this.currentCheckpoint; }
/* 128 */   public int getTotalCheckpoints() { return this.totalCheckpoints; }
/* 129 */   public int getPlayerPosition() { return this.playerPosition; }
/* 130 */   public int getLapCount() { return this.lapCount; } @NotNull
/* 131 */   public List<CheckpointView> getCheckpoints() { return this.checkpoints; } @NotNull
/* 132 */   public String getPlayerName() { return this.playerName; } @NotNull
/* 133 */   public List<String> getLeaderboardNames() { return this.leaderboardNames; } @NotNull
/* 134 */   public List<Long> getLeaderboardTimes() { return this.leaderboardTimes; }
/*     */    public static final class CheckpointView extends Record { private final double x; private final double y; private final double z; private final double radius;
/* 136 */     public CheckpointView(double x, double y, double z, double radius) { this.x = x; this.y = y; this.z = z; this.radius = radius; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lcom/atlas/common/fabric/safari/racing/packet/RacingTimingPacket$CheckpointView;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #136	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/* 136 */       //   0	7	0	this	Lcom/atlas/common/fabric/safari/racing/packet/RacingTimingPacket$CheckpointView; } public double x() { return this.x; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lcom/atlas/common/fabric/safari/racing/packet/RacingTimingPacket$CheckpointView;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #136	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lcom/atlas/common/fabric/safari/racing/packet/RacingTimingPacket$CheckpointView; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lcom/atlas/common/fabric/safari/racing/packet/RacingTimingPacket$CheckpointView;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #136	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lcom/atlas/common/fabric/safari/racing/packet/RacingTimingPacket$CheckpointView;
/* 136 */       //   0	8	1	o	Ljava/lang/Object; } public double y() { return this.y; } public double z() { return this.z; } public double radius() { return this.radius; }
/*     */      }
/*     */ 
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\safari\racing\packet\RacingTimingPacket.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */