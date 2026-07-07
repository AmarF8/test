/*    */ package com.atlas.common.fabric.mega;
/*    */ 
/*    */ import com.cobblemon.mod.common.Cobblemon;
/*    */ import com.cobblemon.mod.common.api.Priority;
/*    */ import com.cobblemon.mod.common.api.data.DataRegistry;
/*    */ import com.cobblemon.mod.common.api.moves.MoveTemplate;
/*    */ import com.cobblemon.mod.common.api.reactive.SimpleObservable;
/*    */ import com.cobblemon.mod.common.battles.runner.ShowdownService;
/*    */ import com.cobblemon.mod.common.battles.runner.graal.GraalShowdownService;
/*    */ import com.cobblemon.mod.relocations.graalvm.polyglot.Value;
/*    */ import com.google.gson.Gson;
/*    */ import com.google.gson.JsonObject;
/*    */ import java.io.BufferedReader;
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStreamReader;
/*    */ import java.nio.charset.StandardCharsets;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import java.util.stream.Collectors;
/*    */ import kotlin.Unit;
/*    */ import net.minecraft.class_2960;
/*    */ import net.minecraft.class_3222;
/*    */ import net.minecraft.class_3264;
/*    */ import net.minecraft.class_3298;
/*    */ import net.minecraft.class_3300;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class Moves implements DataRegistry {
/* 31 */   private static final class_2960 ID = class_2960.method_60655("gg", "showdown/moves");
/* 32 */   private static final SimpleObservable<Moves> OBSERVABLE = new SimpleObservable();
/* 33 */   public static final Moves INSTANCE = new Moves();
/* 34 */   private final Map<String, String> moveScripts = new HashMap<>();
/* 35 */   Gson gson = new Gson();
/*    */   
/*    */   private Moves() {
/* 38 */     OBSERVABLE.subscribe(Priority.NORMAL, this::movesLoad);
/*    */   }
/*    */   
/*    */   public void movesLoad(Moves move) {
/* 42 */     Cobblemon.INSTANCE.getShowdownThread().queue(showdownService -> {
/*    */           if (showdownService instanceof GraalShowdownService) {
/*    */             GraalShowdownService service = (GraalShowdownService)showdownService;
/*    */             Value receiveMoveDataFn = service.context.getBindings("js").getMember("receiveMoveData");
/*    */             for (Map.Entry<String, String> entry : INSTANCE.getMoveScripts().entrySet()) {
/*    */               String moveId = entry.getKey();
/*    */               String js = ((String)entry.getValue()).replace("\n", " ");
/*    */               JsonObject moveData = (JsonObject)this.gson.fromJson(receiveMoveDataFn.execute(new Object[] { moveId, js }, ).asString(), JsonObject.class);
/*    */               MoveTemplate newMove = NewMove.createMoveTemplate(moveData, moveId);
/*    */               NewMove.register(newMove);
/*    */             } 
/*    */           } 
/*    */           return Unit.INSTANCE;
/*    */         });
/*    */   }
/*    */   public Map<String, String> getMoveScripts() {
/* 58 */     return this.moveScripts;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public class_2960 getId() {
/* 64 */     return ID;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public class_3264 getType() {
/* 70 */     return class_3264.field_14190;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public SimpleObservable<? extends DataRegistry> getObservable() {
/* 76 */     return (SimpleObservable)OBSERVABLE;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reload(@NotNull class_3300 resourceManager) {
/* 81 */     this.moveScripts.clear();
/* 82 */     resourceManager.method_14488("showdown/moves", path -> path.method_12832().endsWith(".js")).forEach((id, resource) -> { try { BufferedReader reader = new BufferedReader(new InputStreamReader(resource.method_14482(), StandardCharsets.UTF_8)); try { String js = reader.lines().collect(Collectors.joining("\n")); String moveId = (new File(id.method_12832())).getName().replace(".js", ""); this.moveScripts.put(moveId, js); reader.close(); }
/* 83 */             catch (Throwable throwable) { try { reader.close(); } catch (Throwable throwable1)
/*    */               { throwable.addSuppressed(throwable1); }
/*    */                throw throwable; }
/*    */              }
/* 87 */           catch (IOException e)
/*    */           { LogManager.getLogger().error("Failed to load move script: {} {}", id, e); }
/*    */         
/*    */         });
/*    */     
/* 92 */     OBSERVABLE.emit((Object[])new Moves[] { this });
/*    */   }
/*    */   
/*    */   public void sync(@NotNull class_3222 serverPlayer) {}
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\mega\Moves.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */