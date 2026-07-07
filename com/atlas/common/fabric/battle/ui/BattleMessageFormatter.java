/*    */ package com.atlas.common.fabric.battle.ui;
/*    */ import com.cobblemon.mod.common.api.text.TextKt;
/*    */ import com.cobblemon.mod.common.client.CobblemonClient;
/*    */ import com.cobblemon.mod.common.client.CobblemonResources;
/*    */ import com.cobblemon.mod.common.client.battle.ClientBattle;
/*    */ import com.cobblemon.mod.common.net.messages.client.battle.BattleMessagePacket;
/*    */ import java.util.HashMap;
/*    */ import java.util.HashSet;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import java.util.UUID;
/*    */ import java.util.regex.Matcher;
/*    */ import java.util.regex.Pattern;
/*    */ import net.fabricmc.api.EnvType;
/*    */ import net.fabricmc.api.Environment;
/*    */ import net.minecraft.class_124;
/*    */ import net.minecraft.class_2561;
/*    */ import net.minecraft.class_2588;
/*    */ import net.minecraft.class_310;
/*    */ import net.minecraft.class_5250;
/*    */ import net.minecraft.class_5348;
/*    */ import net.minecraft.class_7417;
/*    */ 
/*    */ @Environment(EnvType.CLIENT)
/*    */ public final class BattleMessageFormatter {
/* 26 */   private static final Pattern TURN_PATTERN = Pattern.compile("(\\d+)");
/* 27 */   private static final Map<UUID, LogState> LOG_STATES = new HashMap<>();
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean handle(BattleMessagePacket packet, class_310 client) {
/* 33 */     ClientBattle battle = CobblemonClient.INSTANCE.getBattle();
/* 34 */     if (battle == null) return false;
/*    */     
/* 36 */     LogState state = LOG_STATES.computeIfAbsent(battle.getBattleId(), ignored -> new LogState());
/* 37 */     for (class_2561 message : packet.getMessages()) {
/* 38 */       String key = key(message);
/* 39 */       if (isTurnMessage(key, message)) {
/* 40 */         int turn = turnNumber(message);
/* 41 */         if (state.markTurn(turn)) {
/* 42 */           addWrapped(battle, client, (class_2561)vanillaText((class_2561)turnDivider(turn)));
/*    */         }
/*    */         
/*    */         continue;
/*    */       } 
/* 47 */       addWrapped(battle, client, (class_2561)vanillaText(message));
/*    */     } 
/* 49 */     return true;
/*    */   }
/*    */   
/*    */   private static class_5250 turnDivider(int turnNumber) {
/* 53 */     String turn = (turnNumber > 0) ? Integer.toString(turnNumber) : "?";
/* 54 */     return class_2561.method_43470("---- Turn " + turn + " ----").method_27695(new class_124[] { class_124.field_1065, class_124.field_1067 });
/*    */   }
/*    */   
/*    */   private static boolean isTurnMessage(String key, class_2561 message) {
/* 58 */     return (key.contains("battle.turn") || key.contains("battle.message.turn") || message.getString().matches(".*\\bTurn\\s+\\d+\\b.*"));
/*    */   }
/*    */   
/*    */   private static int turnNumber(class_2561 message) {
/* 62 */     Matcher matcher = TURN_PATTERN.matcher(message.getString());
/* 63 */     if (!matcher.find()) return -1; 
/*    */     try {
/* 65 */       return Integer.parseInt(matcher.group(1));
/* 66 */     } catch (Exception ignored) {
/* 67 */       return -1;
/*    */     } 
/*    */   }
/*    */   
/*    */   private static String key(class_2561 text) {
/* 72 */     class_7417 class_7417 = text.method_10851(); if (class_7417 instanceof class_2588) { class_2588 translatable = (class_2588)class_7417;
/* 73 */       return translatable.method_11022(); }
/*    */     
/* 75 */     return "";
/*    */   }
/*    */   
/*    */   private static class_5250 vanillaText(class_2561 message) {
/* 79 */     return TextKt.font(TextKt.bold(message.method_27661()), CobblemonResources.INSTANCE.getDEFAULT_LARGE());
/*    */   }
/*    */   
/*    */   private static void addWrapped(ClientBattle battle, class_310 client, class_2561 message) {
/* 83 */     battle.getMessages().add(client.field_1772.method_1728((class_5348)message, 146));
/*    */   }
/*    */   
/*    */   private static final class LogState {
/* 87 */     private final Set<Integer> turns = new HashSet<>();
/*    */     
/*    */     private boolean markTurn(int turn) {
/* 90 */       return (turn <= 0 || this.turns.add(Integer.valueOf(turn)));
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\battl\\ui\BattleMessageFormatter.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */